package org.tmplcl.qs;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmplcl.qs.app.stock.Stock;
import org.tmplcl.qs.app.ticker.Ticker;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.stream.Stream;

@ApplicationScoped
public class AppLifecycleBean {

    private static final Logger LOGGER = LoggerFactory.getLogger("ListenerBean");

    @Inject
    EntityManager entityManager;

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("The application is starting...");

        final Ticker apple = new Ticker("AAPL", "Apple");
        final Ticker msft = new Ticker("MSFT", "Microsoft");
        final Ticker amzn = new Ticker("AMZN", "Amazon");
        apple.persist();
        msft.persist();
        amzn.persist();

        Stream.iterate(LocalDate.of(2018, 1, 1), localDate -> localDate.plusDays(1))
                .filter(localDate -> !(localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY))
                .limit(200)
                .forEach(localDate -> {
                    Stock s = new Stock();
                    s.date = localDate;
                    s.close = new BigDecimal("100.00");
                    entityManager.persist(s);
                    apple.stocks.add(s);
                });

    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application is stopping...");
    }

}
