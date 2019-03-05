package pl.bpiatek.kalkulator.gateways.nbpcurrency;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by Bartosz Piatek on 03/03/2019
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class NBPGateway {

  private final NBPApi api;

  public NBPResponseDTO getCurrencyDetails(String currency) {
    Response<NBPResponseDTO> response;
    Call<NBPResponseDTO> currencyFromNBP = api.getCurrencyFromNBP(currency);

    try {
      response = currencyFromNBP.execute();
    } catch (IOException e) {
      String message = "Unable to send request to get: " + currency + " details";
      log.error(message, e);
      throw new CouldNotGetCurrencyDetailsException(message, HttpStatus.SERVICE_UNAVAILABLE.value());
    }

    if (response.isSuccessful()) {
      return response.body();
    } else if (response.code() == HttpStatus.SERVICE_UNAVAILABLE.value()) {
      throw new CouldNotGetCurrencyDetailsException("Service unavailable.", HttpStatus.SERVICE_UNAVAILABLE.value());
    } else if (response.code() == HttpStatus.NOT_FOUND.value()) {
      throw new CurrencyNotFoundException("Currency: " + currency + " does not exist");
    } else {
      throw new CouldNotGetCurrencyDetailsException(
          "Could not get '" + currency + "' from NBP",
          HttpStatus.INTERNAL_SERVER_ERROR.value()
      );
    }
  }
}
