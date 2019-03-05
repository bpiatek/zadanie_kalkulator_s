package pl.bpiatek.kalkulator.resources;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import pl.bpiatek.kalkulator.model.Country;
import pl.bpiatek.kalkulator.model.ExchangeRequestDTO;
import pl.bpiatek.kalkulator.service.ExchangeService;

import java.math.BigDecimal;

/**
 * Created by Bartosz Piatek on 05/03/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainControllerTest {

  @InjectMocks
  MainController controller;

  @Mock
  ExchangeService service;

  @LocalServerPort
  private int port;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldGetCorrectNettSalary() {
    //given
    ExchangeRequestDTO requestDTO = new ExchangeRequestDTO(new BigDecimal("300"), Country.UK);
    when(service.finalSalary(requestDTO)).thenReturn(new BigDecimal("2500"));

    //when
    MainController.NettEarnings response = controller.calculateNett(requestDTO).getBody();

    //then
    assertThat(response.getNettSalary()).isEqualTo(new BigDecimal("2500.00"));
  }

  @Test
  public void shouldReturn400whenDailyWageIsNull() {
    //given
    ExchangeRequestDTO requestDTO = new ExchangeRequestDTO(null, Country.UK);

    //when
    //then
    RestAssured
        .with()
        .body(requestDTO)
        .port(port)
        .contentType("application/json")
        .when()
        .request(Method.POST, "/api/exchange")
        .then()
        .statusCode(400);
  }

  @Test
  public void shouldReturn400whenDailyWageIsNegativeNumber() {
    //given
    ExchangeRequestDTO requestDTO = new ExchangeRequestDTO(null, Country.UK);

    //when
    //then
    RestAssured
        .with()
        .body(requestDTO)
        .port(port)
        .contentType("application/json")
        .when()
        .request(Method.POST, "/api/exchange")
        .then()
        .statusCode(400);
  }

//  @Test
//  public void shouldReturn400whenDailyWageIsNull() {
//    //given
//    ExchangeRequestDTO requestDTO = new ExchangeRequestDTO(null, Country.UK);
//    System.out.println("PORT: " + port);
////    RestAssured.port = 8080;
////    RestAssured.baseURI = "http://localhost:8080";
//    //when
//    //then
//    RestAssured
//        .with()
//        .port(8080)
//        .body(requestDTO)
//        .contentType("application/json")
//        .when()
//        .request(Method.POST, "/api/exchange")
//        .then()
//        .statusCode(400);
//  }
//
//  @Test
//  public void shouldReturn400whenDailyWageIsNegativeNumber() {
//    //given
//    ExchangeRequestDTO requestDTO = new ExchangeRequestDTO(new BigDecimal("-1"), Country.UK);
//
//    //when
//    //then
//    RestAssured
//        .with()
//        .body(requestDTO)
//        .contentType("application/json")
//        .when()
//        .request(Method.POST, "/api/exchange")
//        .then()
//        .statusCode(400);
//  }

}