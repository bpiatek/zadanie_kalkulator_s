package pl.bpiatek.kalkulator.model;

/**
 * Created by Bartosz Piatek on 03/03/2019
 */
public enum Country {
  PL(Keys.POLAND),
  UK(Keys.UNITED_KINGDOM),
  DE(Keys.GERMANY);

  private final String key;

  Country(String value) {
    this.key = value;
  }

  public String getKey() {
    return key;
  }

  public static class Keys {

    private static final String POLAND = "PLN";
    private static final String UNITED_KINGDOM = "GBP";
    private static final String GERMANY = "EUR";

    private Keys() { /* Class with key constants. */ }
  }
}
