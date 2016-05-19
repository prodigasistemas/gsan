package gcom.faturamento.bean;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class DeterminarValoresFaturamentoTest {

    @Test
    public void imovelSemConsumo(){
        DeterminarValoresFaturamentoAguaEsgotoHelper helper = new DeterminarValoresFaturamentoAguaEsgotoHelper();
        assertTrue(helper.imovelSemConsumo());
    }
    
    @Test
    public void imovelComConsumo(){
        DeterminarValoresFaturamentoAguaEsgotoHelper helper = new DeterminarValoresFaturamentoAguaEsgotoHelper();
        helper.setValorTotalAgua(BigDecimal.TEN);
        assertFalse(helper.imovelSemConsumo());
    }
}
