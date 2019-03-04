package pl.bpiatek.kalkulator;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.bpiatek.kalkulator.gateways.nbpcurrency.CurrencyApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bartosz Piatek on 03/03/2019
 */
@Configuration
public class KalkulatorConfiguration {
  @Value("${api.url.nbp}")
  private String nbpUrl;

  @Bean
  public OkHttpClient okHttpClient() {
    return new OkHttpClient();
  }

  @Bean
  public Retrofit retrofitNBP() {
    return new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(nbpUrl)
        .client(okHttpClient())
        .build();
  }

  @Bean
  public CurrencyApi nbpApi() {
    return retrofitNBP().create(CurrencyApi.class);
  }
}
