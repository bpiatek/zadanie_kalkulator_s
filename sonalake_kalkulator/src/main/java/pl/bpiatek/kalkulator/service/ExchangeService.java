package pl.bpiatek.kalkulator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bpiatek.kalkulator.countries.*;
import pl.bpiatek.kalkulator.gateways.nbpcurrency.NBPGateway;
import pl.bpiatek.kalkulator.model.Country;
import pl.bpiatek.kalkulator.model.ExchangeRequestDTO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Bartosz Piatek on 04/03/2019
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ExchangeService {

  private final NBPGateway gateway;
  private Map<String, Calculable> map = new HashMap<>();

  private void initializeMap() {
    map.put("GBP", new UK());
    map.put("PLN", new PL());
    map.put("EUR", new DE());
  }

  public BigDecimal finalSalary(ExchangeRequestDTO request) {
    initializeMap();

    return map.get(request.getCountry().getKey())
        .calculate(request.getDailyWage(), getExchangeRate(request));
  }

  private BigDecimal getExchangeRate(ExchangeRequestDTO request) {
    if (Country.PL == request.getCountry()) {
      return new BigDecimal("1");
    }
    return gateway.getCurrencyDetails(request.getCountry().getKey()).getRates().get(0).getMid();
  }
}
