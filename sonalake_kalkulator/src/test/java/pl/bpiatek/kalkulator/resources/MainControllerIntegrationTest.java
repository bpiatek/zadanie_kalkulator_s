package pl.bpiatek.kalkulator.resources;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import pl.bpiatek.kalkulator.model.Country;
import pl.bpiatek.kalkulator.model.ExchangeRequestDTO;

import java.math.BigDecimal;

/**
 * Created by Bartosz Piatek on 05/03/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainControllerIntegrationTest {

  @LocalServerPort
  private int port;

  @Test
  public void shouldNotAllowGET() {
    //given
    ExchangeRequestDTO requestDTO = new ExchangeRequestDTO(new BigDecimal("300"), Country.UK);

    //when
    //then
    RestAssured
        .with()
        .body(requestDTO)
        .port(port)
        .contentType("application/json")
        .when()
        .request(Method.GET, "/api/exchange")
        .then()
        .statusCode(405);
  }

  @Test
  public void shouldReturn200withCorrectBody() {
    //given
    ExchangeRequestDTO requestDTO = new ExchangeRequestDTO(new BigDecimal("300"), Country.UK);

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
        .statusCode(200);
  }

  @Test
  public void shouldReturn415whenIncorrectContentType() {
    //given
    ExchangeRequestDTO requestDTO = new ExchangeRequestDTO(new BigDecimal("300"), Country.UK);

    //when
    //then
    RestAssured
        .with()
        .body(requestDTO)
        .port(port)
        .when()
        .request(Method.POST, "/api/exchange")
        .then()
        .statusCode(415);
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
}