package pl.bpiatek.kalkulator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

import java.math.BigDecimal;

/**
 * Created by Bartosz Piatek on 04/03/2019
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {

  BigDecimal mid;
}