package pl.bpiatek.kalkulator.model;

import lombok.Value;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * Created by Bartosz Piatek on 04/03/2019
 */
@Value
public class ExchangeRequestDTO {

  @NotNull
  @PositiveOrZero
  BigDecimal dailyWage;
  @NotNull
  Country country;
}
