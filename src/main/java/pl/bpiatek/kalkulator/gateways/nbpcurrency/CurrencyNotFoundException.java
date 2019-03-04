package pl.bpiatek.kalkulator.gateways.nbpcurrency;

/**
 * Created by Bartosz Piatek on 03/03/2019
 */
public class CurrencyNotFoundException extends RuntimeException {

  public CurrencyNotFoundException(String message) {
    super(message);
  }
}
