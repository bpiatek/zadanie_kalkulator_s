package pl.bpiatek.kalkulator.gateways.nbpcurrency;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import okhttp3.OkHttpClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.ws.rs.core.Response;

/**
 * Created by Bartosz Piatek on 03/03/2019
 */

public class NBPGatewayTest {

  private NBPGateway sut;

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(Options.DYNAMIC_PORT);

  @Before
  public void setup() {
    Retrofit retrofit = new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://localhost:" + wireMockRule.port())
        .client(new OkHttpClient())
        .build();

    sut = new NBPGateway(retrofit.create(NBPApi.class));
  }

  @Test
  public void shouldGetCurrencyDetails() {
    //given
    String json = "{\"currency\": \"dolar amerykański\", \"code\": \"USD\"}";
    stubFor(
        get(urlEqualTo("/rates/A/USD?format=json"))
            .willReturn(aResponse()
                            .withBody(json)
            ));

    //when
    NBPResponseDTO usd = sut.getCurrencyDetails("USD");

    //then
    assertThat(usd.getCode()).isEqualTo("USD");
    assertThat(usd.getCurrency()).isEqualTo("dolar amerykański");
  }

  @Test
  public void shouldThrowExceptionWhenCurrencyNotFound() {
    //given
    stubFor(
        get(urlEqualTo("/rates/A/XYZ?format=json"))
            .willReturn(aResponse()
                            .withStatus(Response.Status.NOT_FOUND.getStatusCode())
            ));

    //when
    //then
    assertThatExceptionOfType(CurrencyNotFoundException.class).isThrownBy(
        () -> sut.getCurrencyDetails("XYZ")
    );
  }
}