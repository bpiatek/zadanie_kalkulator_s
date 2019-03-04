package pl.bpiatek.kalkulator.gateways.nbpcurrency;

import lombok.Value;

/**
 * Created by Bartosz Piatek on 03/03/2019
 */
@Value
public class CurrencyResponseDTO {
    private String currency;
    private String code;
}
