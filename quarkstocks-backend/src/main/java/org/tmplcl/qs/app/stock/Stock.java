package org.tmplcl.qs.app.stock;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.tmplcl.qs.app.ticker.Ticker;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Stock extends PanacheEntity {

    public BigDecimal close;

    @Schema(type = SchemaType.STRING, format = "date")
    @JsonbDateFormat
    public LocalDate date;

    @ManyToOne
    @JoinColumn
    public Ticker ticker;

    public static List<Stock> findBySymbol(Ticker ticker) {
        return Stock.find("ticker", ticker).list();
    }
}
