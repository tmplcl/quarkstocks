package org.tmplcl.qs.app.ticker;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.tmplcl.qs.app.stock.Stock;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Schema(name="Ticker")
@Entity
public class Ticker extends PanacheEntity {

    public Ticker(String symbol, String issuer) {
        this.symbol = symbol;
        this.issuer = issuer;
        this.stocks = new ArrayList<>();
    }

    public Ticker() {
    }

    @Schema(required = true, example = "AAPL")
    public String symbol;

    @Schema(readOnly = true, example = "Apple")
    public String issuer;

    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ticker_id")
    public List<Stock> stocks;

    public static Ticker findBySymbol(String symbol){
        return Ticker.find("symbol", symbol).firstResult();
    }

}
