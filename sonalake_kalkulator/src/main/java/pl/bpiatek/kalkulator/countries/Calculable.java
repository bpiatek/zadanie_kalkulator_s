package pl.bpiatek.kalkulator.countries;

import java.math.BigDecimal;

/**
 * Created by Bartosz Piatek on 04/03/2019
 */
public interface Calculable {

  BigDecimal calculate(BigDecimal dailyWage, BigDecimal rate);

  default BigDecimal calculateTax(BigDecimal tax) {
    return new BigDecimal("100").subtract(tax).divide(new BigDecimal("100"));
  }

  default BigDecimal calculateEarnings(
      BigDecimal dailyWage, BigDecimal tax, BigDecimal fixedCosts, BigDecimal rate
  ) {
    return dailyWage
        .multiply(new BigDecimal("22"))
        .multiply(calculateTax(tax))
        .subtract(fixedCosts)
        .multiply(rate);
  }
}
