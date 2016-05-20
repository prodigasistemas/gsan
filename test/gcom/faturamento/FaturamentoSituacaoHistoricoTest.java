package gcom.faturamento;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class FaturamentoSituacaoHistoricoTest {

    FaturamentoSituacaoHistorico alvo;
    
    @Before
    public void init(){
        alvo = new FaturamentoSituacaoHistorico();
    }
    
    @Test
    public void faturamentoDentroDoIntervalo(){
        alvo.setAnoMesFaturamentoSituacaoInicio(201601);
        alvo.setAnoMesFaturamentoSituacaoFim(201605);
        assertTrue(alvo.dentroIntervaloFaturamento(201601));
        assertTrue(alvo.dentroIntervaloFaturamento(201602));
        assertTrue(alvo.dentroIntervaloFaturamento(201605));
    }
    
    @Test
    public void faturamentoForaDoIntervalo(){
        alvo.setAnoMesFaturamentoSituacaoInicio(201601);
        alvo.setAnoMesFaturamentoSituacaoFim(201605);
        assertFalse(alvo.dentroIntervaloFaturamento(201606));
    }
}
