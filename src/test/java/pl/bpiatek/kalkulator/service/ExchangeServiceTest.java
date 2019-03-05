package pl.bpiatek.kalkulator.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.bpiatek.kalkulator.countries.UK;
import pl.bpiatek.kalkulator.gateways.nbpcurrency.NBPGateway;
import pl.bpiatek.kalkulator.gateways.nbpcurrency.NBPResponseDTO;
import pl.bpiatek.kalkulator.model.Country;
import pl.bpiatek.kalkulator.model.ExchangeRequestDTO;
import pl.bpiatek.kalkulator.model.Rates;

import java.math.BigDecimal;
import java.util.Collections;

/**
 * Created by Bartosz Piatek on 04/03/2019
 */
public class ExchangeServiceTest {

  private static final BigDecimal UK_TAX_25 = new BigDecimal("0.75");
  private static final BigDecimal UK_FIXEDCOSTS = new BigDecimal("600");

  @InjectMocks
  private ExchangeService exchangeService;

  @Mock
  private UK uk;

  @Mock
  private NBPGateway gateway;

  @Before
  public void initMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldCalculateMonthlyProfitUK() {
    //given
    ExchangeRequestDTO exchangeRequestDTO = new ExchangeRequestDTO(new BigDecimal("300"), Country.UK);
    NBPResponseDTO nbpResponseDTO = new NBPResponseDTO("pound", "GBP",
                                                       Collections.singletonList(new Rates(new BigDecimal("5")))
    );
    when(gateway.getCurrencyDetails("GBP")).thenReturn(nbpResponseDTO);

    when(uk.getFixedCosts()).thenReturn(UK_FIXEDCOSTS);
    when(uk.getTax()).thenReturn(UK_TAX_25);

    //when
    BigDecimal salary = exchangeService.finalSalary(exchangeRequestDTO);

    //then
    assertThat(salary).isEqualTo(new BigDecimal("21750.00"));
  }
}