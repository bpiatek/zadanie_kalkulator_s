package pl.bpiatek.kalkulator.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.bpiatek.kalkulator.model.ExchangeRequestDTO;
import pl.bpiatek.kalkulator.service.ExchangeService;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * Created by Bartosz Piatek on 03/03/2019
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Inject))
class MainController {

  private final ExchangeService exchangeService;

  @PostMapping("api/exchange")
  public ResponseEntity<NettEarnings> calculateNett(@Valid @RequestBody ExchangeRequestDTO request) {
    NettEarnings nettEarnings = mapToNettEarnings(request);

    return new ResponseEntity<>(nettEarnings, HttpStatus.OK);
  }

  @Value
  class NettEarnings {
    @JsonProperty("nettSalary")
    BigDecimal nettSalary;
  }

  private NettEarnings mapToNettEarnings(ExchangeRequestDTO request) {
    return new NettEarnings(exchangeService.finalSalary(request)
                               .setScale(2, RoundingMode.HALF_UP));
  }
}
