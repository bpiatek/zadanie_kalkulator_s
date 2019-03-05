package pl.bpiatek.kalkulator.gateways.nbpcurrency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;
import pl.bpiatek.kalkulator.model.Rates;

import java.util.List;

/**
 * Created by Bartosz Piatek on 03/03/2019
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class NBPResponseDTO {
    private String currency;
    private String code;
    private List<Rates> rates;
}
