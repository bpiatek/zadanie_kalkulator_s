package pl.bpiatek.kalkulator.gateways.nbpcurrency;

import org.springframework.http.HttpStatus;

import javax.ws.rs.WebApplicationException;

/**
 * Created by Bartosz Piatek on 03/03/2019
 */
public class CurrencyNotFoundException extends WebApplicationException {

  public CurrencyNotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND.value());
  }
}
