package pl.bpiatek.kalkulator.gateways.nbpcurrency;

import javax.ws.rs.WebApplicationException;

/**
 * Created by Bartosz Piatek on 03/03/2019
 */
public class CouldNotGetCurrencyDetailsException extends WebApplicationException {

  public CouldNotGetCurrencyDetailsException(String message, int status) {
    super(message, status);
  }
}
