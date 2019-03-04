package pl.bpiatek.kalkulator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

/**
 * Created by Bartosz Piatek on 03/03/2019
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {
  private String currency;
  private String code;
}
