package pl.bpiatek.kalkulator.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.bpiatek.kalkulator.model.Country;
import pl.bpiatek.kalkulator.model.ExchangeRequestDTO;
import pl.bpiatek.kalkulator.service.ExchangeService;

import java.math.BigDecimal;

/**
 * Created by Bartosz Piatek on 05/03/2019
 */
public class MainControllerUnitTest {

  @InjectMocks
  MainController controller;

  @Mock
  ExchangeService service;

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
}