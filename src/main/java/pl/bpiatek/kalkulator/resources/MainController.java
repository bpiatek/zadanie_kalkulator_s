package pl.bpiatek.kalkulator.resources;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bpiatek.kalkulator.gateways.nbpcurrency.CurrencyGateway;
import pl.bpiatek.kalkulator.gateways.nbpcurrency.CurrencyResponseDTO;

import javax.inject.Inject;

/**
 * Created by Bartosz Piatek on 03/03/2019
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class MainController {

  private final CurrencyGateway gateway;

  @GetMapping("api/{currency}")
  public ResponseEntity<CurrencyResponseDTO> test2(
      @PathVariable String currency
//      @PathVariable("country") Country country
      ) {
    CurrencyResponseDTO currencyDetails = gateway.getCurrencyDetails(currency);

    return new ResponseEntity<>(currencyDetails, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity calculate() {
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
