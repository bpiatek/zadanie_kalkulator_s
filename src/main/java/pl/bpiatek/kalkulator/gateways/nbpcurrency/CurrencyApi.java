package pl.bpiatek.kalkulator.gateways.nbpcurrency;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Bartosz Piatek on 03/03/2019
 */
public interface CurrencyApi {
  @GET("rates/A/{currency}?format=json")
  Call<CurrencyResponseDTO> getCurrencyFromNBP(@Path("currency") String currency);
}
