package pl.bpiatek.kalkulator.countries;

import java.math.BigDecimal;

/**
 * Created by Bartosz Piatek on 04/03/2019
 */
public interface Tax {
  static BigDecimal calculateTax(BigDecimal tax) {
    return new BigDecimal("100").subtract(tax).divide(new BigDecimal("100"));
  }
}
