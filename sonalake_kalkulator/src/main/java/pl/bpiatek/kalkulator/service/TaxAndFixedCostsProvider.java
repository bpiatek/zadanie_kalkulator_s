package pl.bpiatek.kalkulator.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Bartosz Piatek on 12/03/2019
 */
@Component
public class TaxAndFixedCostsProvider {

  public static String UK_TAX;
  public static String UK_FIXED_COSTS;

  public static String DE_TAX;
  public static String DE_FIXED_COSTS;

  public static String PL_TAX;
  public static String PL_FIXED_COSTS;

  @Value("${uk.fixcosts}")
  private void setUkFixedCosts(String ukFixedCosts) {
    UK_FIXED_COSTS = ukFixedCosts;
  }

  @Value("${uk.tax}")
  private void setUkTax(String ukTax) {
    UK_TAX = ukTax;
  }

  @Value("${de.fixcosts}")
  private void setDeFixedCosts(String deFixedCosts) {
    DE_FIXED_COSTS = deFixedCosts;
  }

  @Value("${de.tax}")
  private void setDeTax(String deTax) {
    DE_TAX = deTax;
  }

  @Value("${pl.tax}")
  private void setPlTax(String plTax) {
    PL_TAX = plTax;
  }

  @Value("${pl.fixcosts}")
  private void setPlFixedCosts(String plFixedCosts) {
    PL_FIXED_COSTS = plFixedCosts;
  }
}
