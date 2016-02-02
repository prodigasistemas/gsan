package gcom.faturamento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import gcom.faturamento.AtualizacaoCreditoARealizarHelper.ItemCreditoARealizar;
import gcom.faturamento.credito.CreditoARealizar;

public class TesteAtualizacaoCreditosARealizarPreFaturamento{
    
    FaturamentoUtil controlador = new FaturamentoUtil();
    
    Integer jul2015 = 201507;
    Integer jun2015 = 201506;
    
    BigDecimal valorAgua = BigDecimal.ZERO;
    
    BigDecimal valorEsgoto = BigDecimal.ZERO;
    
    BigDecimal valorDebitos = BigDecimal.ZERO;
    
    Collection<CreditoARealizar> creditos = new ArrayList<CreditoARealizar>();
    
    CreditoARealizar credito01 = new CreditoARealizar();
    
    @Before
    public void setUp(){
    	credito01.setValorCredito(new BigDecimal(35));
    	credito01.setNumeroPrestacaoCredito((short) 6);
    	credito01.setValorResidualMesAnterior(BigDecimal.ZERO);
    	credito01.setAnoMesReferenciaPrestacao(jun2015);
    	creditos.add(credito01);
    }
    
    @Test
    public void testaParcela4de6PreFaturamento(){
    	credito01.setNumeroPrestacaoRealizada((short) 4);
        
        AtualizacaoCreditoARealizarHelper helper = controlador.atualizarCreditosARealizar(jul2015, creditos, valorAgua, valorEsgoto, valorDebitos, true);
        
        assertEquals(1, helper.getCreditos().size());
        
        ItemCreditoARealizar item = helper.getCreditos().iterator().next();
        
        CreditoARealizar credito = item.getCreditoARelizar();
        
        assertEquals(5, credito.getNumeroPrestacaoRealizada().intValue());
        assertEquals(5.83, item.getCreditoCalculado().doubleValue(), 0);
    }
    
    @Test
    public void testaTotalContaCreditoPreFaturamento(){
        credito01.setNumeroPrestacaoRealizada((short) 5);
        
        AtualizacaoCreditoARealizarHelper helper = controlador.atualizarCreditosARealizar(jul2015, creditos, valorAgua, valorEsgoto, valorDebitos, true);
        
        assertEquals(1, helper.getCreditos().size());
        
        ItemCreditoARealizar item = helper.getCreditos().iterator().next();
        
        CreditoARealizar credito = item.getCreditoARelizar();
        
        assertEquals(6, credito.getNumeroPrestacaoRealizada().intValue());
        assertEquals(5.85, item.getCreditoCalculado().doubleValue(), 0);
    }
    
    @Test
    public void testaPreFaturamentoComParcela0de1(){
    	credito01.setNumeroPrestacaoCredito((short) 1);
        credito01.setNumeroPrestacaoRealizada((short) 0);
        
        AtualizacaoCreditoARealizarHelper helper = controlador.atualizarCreditosARealizar(jul2015, creditos, valorAgua, valorEsgoto, valorDebitos, true);
        
        assertEquals(1, helper.getCreditos().size());
        
        ItemCreditoARealizar item = helper.getCreditos().iterator().next();
        
        CreditoARealizar credito = item.getCreditoARelizar();
        
        assertEquals(1, credito.getNumeroPrestacaoRealizada().intValue());
        assertEquals(35, item.getCreditoCalculado().doubleValue(), 0);
        assertFalse(credito.possuiResiduo());
    }

    @Test
    public void testaPreFaturamentoComParcela0de1EResiduo(){
    	credito01.setNumeroPrestacaoCredito((short) 1);
    	credito01.setNumeroPrestacaoRealizada((short) 1);
    	credito01.setValorResidualMesAnterior(new BigDecimal(8.40));
    	
    	AtualizacaoCreditoARealizarHelper helper = controlador.atualizarCreditosARealizar(jul2015, creditos, valorAgua, valorEsgoto, valorDebitos, true);
    	
    	assertEquals(1, helper.getCreditos().size());
    	
    	ItemCreditoARealizar item = helper.getCreditos().iterator().next();
    	
    	CreditoARealizar credito = item.getCreditoARelizar();
    	
    	assertEquals(1, credito.getNumeroPrestacaoRealizada().intValue());
    	assertEquals(8.40, item.getCreditoCalculado().doubleValue(), 0);
    	assertTrue(credito.possuiResiduo());
    	assertEquals(8.40, credito.getValorResidualMesAnterior().doubleValue(), 0);
    }
    
    @Test
    public void testaTotalContaCreditoFaturamento(){
        credito01.setNumeroPrestacaoRealizada((short) 6);
        credito01.setAnoMesReferenciaPrestacao(jul2015);
        
        valorAgua = new BigDecimal(14);
        
        AtualizacaoCreditoARealizarHelper helper = controlador.atualizarCreditosARealizar(jul2015, creditos, valorAgua, valorEsgoto, valorDebitos, false);
        
        assertEquals(1, helper.getCreditos().size());
        
        ItemCreditoARealizar item = helper.getCreditos().iterator().next();
        
        CreditoARealizar credito = item.getCreditoARelizar();
        
        assertEquals(6, credito.getNumeroPrestacaoRealizada().intValue());
        assertEquals(5.85, item.getCreditoCalculado().doubleValue(), 0);
    }
    
    @Test
    public void testaParcela5de6Faturamento(){
    	credito01.setNumeroPrestacaoRealizada((short) 5);
    	credito01.setAnoMesReferenciaPrestacao(jul2015);
        
        valorAgua = new BigDecimal(14);
        
        AtualizacaoCreditoARealizarHelper helper = controlador.atualizarCreditosARealizar(jul2015, creditos, valorAgua, valorEsgoto, valorDebitos, false);
        
        assertEquals(1, helper.getCreditos().size());
        
        ItemCreditoARealizar item = helper.getCreditos().iterator().next();
        
        CreditoARealizar credito = item.getCreditoARelizar();
        
        assertEquals(5, credito.getNumeroPrestacaoRealizada().intValue());
        assertEquals(5.83, item.getCreditoCalculado().doubleValue(), 0);
    }    
    
    @Test
    public void testaParcela4de6Faturamento(){
    	credito01.setNumeroPrestacaoRealizada((short) 4);
    	credito01.setAnoMesReferenciaPrestacao(jul2015);
        
        valorAgua = new BigDecimal(14);
        
        AtualizacaoCreditoARealizarHelper helper = controlador.atualizarCreditosARealizar(jul2015, creditos, valorAgua, valorEsgoto, valorDebitos, false);
        
        assertEquals(1, helper.getCreditos().size());
        
        ItemCreditoARealizar item = helper.getCreditos().iterator().next();
        
        CreditoARealizar credito = item.getCreditoARelizar();
        
        assertEquals(4, credito.getNumeroPrestacaoRealizada().intValue());
        assertEquals(5.83, item.getCreditoCalculado().doubleValue(), 0);
    }
    
    @Test
    public void testaParcela1de1NuncaConcedido(){
    	credito01.setNumeroPrestacaoCredito((short) 1);
    	credito01.setNumeroPrestacaoRealizada((short) 1);
    	credito01.setAnoMesReferenciaPrestacao(jul2015);
        
        valorAgua = new BigDecimal(45);
        
        AtualizacaoCreditoARealizarHelper helper = controlador.atualizarCreditosARealizar(jul2015, creditos, valorAgua, valorEsgoto, valorDebitos, false);
        
        assertEquals(1, helper.getCreditos().size());
        
        ItemCreditoARealizar item = helper.getCreditos().iterator().next();
        
        CreditoARealizar credito = item.getCreditoARelizar();
        
        assertEquals(1, credito.getNumeroPrestacaoRealizada().intValue());
        assertEquals(35, item.getCreditoCalculado().doubleValue(), 0);
        assertFalse(credito.possuiResiduo());
    }
    
    @Test
    public void testaParcela1de1ConcedidoComResiduo(){
    	credito01.setNumeroPrestacaoCredito((short) 1);
    	credito01.setNumeroPrestacaoRealizada((short) 1);
    	credito01.setAnoMesReferenciaPrestacao(jul2015);
    	credito01.setValorResidualMesAnterior(new BigDecimal(21));
        
        valorAgua = new BigDecimal(14.70);
        
        AtualizacaoCreditoARealizarHelper helper = controlador.atualizarCreditosARealizar(jul2015, creditos, valorAgua, valorEsgoto, valorDebitos, false);
        
        assertEquals(1, helper.getCreditos().size());
        
        ItemCreditoARealizar item = helper.getCreditos().iterator().next();
        
        CreditoARealizar credito = item.getCreditoARelizar();
        
        assertEquals(1, credito.getNumeroPrestacaoRealizada().intValue());
        assertEquals(14.70, item.getCreditoCalculado().doubleValue(), 0);
        assertTrue(credito.possuiResiduo());
        assertEquals(6.30, credito.getValorResidualMesAnterior().doubleValue(), 0.01);
    }    
}