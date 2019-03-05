package pl.bpiatek.kalkulator.countries;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by Bartosz Piatek on 04/03/2019
 */
@Service
@Getter
public class DE {
  private final BigDecimal tax;
  private final BigDecimal fixedCosts;

  @Autowired
  public DE(@Value("${de.tax}") String tax, @Value("${de.fixcosts}") String fixCosts) {
    this.tax = Tax.calculateTax(new BigDecimal(tax));
    this.fixedCosts = new BigDecimal(fixCosts);
  }
}
