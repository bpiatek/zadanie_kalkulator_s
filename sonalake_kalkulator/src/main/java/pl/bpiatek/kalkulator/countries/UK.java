package pl.bpiatek.kalkulator.countries;

import lombok.Getter;
import pl.bpiatek.kalkulator.service.TaxAndFixedCostsProvider;

import java.math.BigDecimal;

/**
 * Created by Bartosz Piatek on 04/03/2019
 */
@Getter
public class UK implements Calculable {

  private final BigDecimal tax = new BigDecimal(TaxAndFixedCostsProvider.UK_TAX);
  private final BigDecimal fixedCosts = new BigDecimal(TaxAndFixedCostsProvider.UK_FIXED_COSTS);

  @Override
  public BigDecimal calculate(BigDecimal dailyWage, BigDecimal rate) {
    return calculateEarnings(
        dailyWage,
        this.tax,
        this.fixedCosts,
        rate
    );
  }
}
