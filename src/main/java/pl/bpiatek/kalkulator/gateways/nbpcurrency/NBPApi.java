package pl.bpiatek.kalkulator.gateways.nbpcurrency;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Bartosz Piatek on 03/03/2019
 */
public interface NBPApi {
  @GET("rates/A/{currency}?format=json")
  Call<NBPResponseDTO> getCurrencyFromNBP(@Path("currency") String currency);
}
