package org.tmplcl.qs.app.ticker;

import io.quarkus.panache.common.Page;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.tmplcl.qs.app.stock.Stock;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("api/ticker")
public class TickerResource {


    @APIResponse(
            responseCode = "200",
            description = "Ticker",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(type = SchemaType.ARRAY, implementation = Ticker.class)))
    @GET
    @Path("")
    public List<Ticker> getTicker(@QueryParam("page") @DefaultValue("0") Integer page, @DefaultValue("100") @Max(1000) @QueryParam("size") Integer size) {
        return Ticker.findAll().page(Page.of(page, size)).list();
    }

    @APIResponse(
            responseCode = "200",
            description = "Ticker",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Ticker.class)))
    @GET
    @Path("/{symbol}")
    public Ticker getTickerBySymbol(@NotEmpty @PathParam("symbol") String symbol) {
        return Ticker.findBySymbol(symbol);
    }

    @APIResponse(
            responseCode = "200",
            description = "Stock",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Stock.class)))
    @GET
    @Path("/{symbol}/stocks/{date}")
    public Stock getTickerBySymbol(@NotEmpty @PathParam("symbol") String symbol,
                                   @NotEmpty @PathParam("date") @Schema(type = SchemaType.STRING, format = "date" ) String date) {
        return Stock.find("ticker.symbol = ?1 and date = ?2", symbol, LocalDate.parse(date)).firstResult();
    }

    @APIResponse(
            responseCode = "200",
            description = "Ticker",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(type = SchemaType.ARRAY, implementation = Stock.class)))
    @GET
    @Path("/{symbol}/stocks")
    public List<Stock> getStock(@NotEmpty @PathParam("symbol") @DefaultValue("AAPL") String symbol,
                                @QueryParam("from") @DefaultValue("2018-01-01") @Schema(type = SchemaType.STRING, format = "date" ) String from,
                                @QueryParam("to") @DefaultValue("2019-01-01") @Schema(type = SchemaType.STRING, format = "date" ) String to) {
        return Stock.find("ticker.symbol = ?1 and date >= ?2 and date <= ?3", symbol, LocalDate.parse(from), LocalDate.parse(to)).list();
    }

}
