package pl.bpiatek.kalkulator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bpiatek.kalkulator.countries.*;
import pl.bpiatek.kalkulator.gateways.nbpcurrency.CurrencyNotFoundException;
import pl.bpiatek.kalkulator.gateways.nbpcurrency.NBPGateway;
import pl.bpiatek.kalkulator.model.ExchangeRequestDTO;

import java.math.BigDecimal;

import javax.inject.Inject;

/**
 * Created by Bartosz Piatek on 04/03/2019
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ExchangeService {

  private final NBPGateway gateway;
  private final UK uk;
  private final DE de;
  private final PL pl;

  public BigDecimal finalSalary(ExchangeRequestDTO request) {
    switch (request.getCountry()) {
      case UK:
        return calculate(
            request.getDailyWage(),
            uk.getTax(),
            uk.getFixedCosts(),
            getExchangeRate(request)
        );
      case DE:
        return calculate(
            request.getDailyWage(),
            de.getTax(),
            de.getFixedCosts(),
            getExchangeRate(request)
        );
      case PL:
        return calculate(
            request.getDailyWage(),
            pl.getTax(),
            pl.getFixedCosts(),
            new BigDecimal("1")
        );
      default:
        throw new CurrencyNotFoundException("No currency for given country");
    }
  }

  private BigDecimal getExchangeRate(ExchangeRequestDTO request) {
    return gateway.getCurrencyDetails(request.getCountry().getKey()).getRates().get(0).getMid();
  }

  private BigDecimal calculate(
      BigDecimal dailyWage, BigDecimal tax,
      BigDecimal fixedCosts, BigDecimal rate
  ) {
    return dailyWage
        .multiply(new BigDecimal("22"))
        .multiply(tax)
        .subtract(fixedCosts)
        .multiply(rate);
  }
}
