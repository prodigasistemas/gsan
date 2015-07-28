package gcom.faturamento;

import gcom.faturamento.AtualizacaoCreditoARealizarHelper.ItemCreditoARealizar;
import gcom.faturamento.credito.CreditoARealizar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;

public class TestaAtualizacaoCreditosARealizar extends TestCase{
    
    FaturamentoUtil controlador = null;
    
    Integer anoMesFaturamento = 201507;
    
    BigDecimal valorAgua = BigDecimal.ZERO;
    
    BigDecimal valorEsgoto = BigDecimal.ZERO;
    
    BigDecimal valorDebitos = BigDecimal.ZERO;
    
    public void testaParcela4de6PreFaturamento(){
        controlador = new FaturamentoUtil();
        
        Collection<CreditoARealizar> creditos = new ArrayList<CreditoARealizar>();
        CreditoARealizar c1 = new CreditoARealizar();
        c1.setValorCredito(new BigDecimal(35));
        c1.setNumeroPrestacaoCredito((short) 6);
        c1.setNumeroPrestacaoRealizada((short) 4);
        c1.setValorResidualMesAnterior(BigDecimal.ZERO);
        c1.setAnoMesReferenciaPrestacao(201506);
        
        
        creditos.add(c1);
        
        AtualizacaoCreditoARealizarHelper helper = controlador.atualizarCreditosARealizar(anoMesFaturamento, creditos, valorAgua, valorEsgoto, valorDebitos, true);
        
        assertEquals(1, helper.getCreditos().size());
        
        ItemCreditoARealizar item = helper.getCreditos().iterator().next();
        
        CreditoARealizar credito = item.getCreditoARelizar();
        
        assertEquals(5, credito.getNumeroPrestacaoRealizada().intValue());
        assertEquals(5.83, item.getCreditoCalculado().doubleValue(), 0);
    }
    public void testaTotalContaCreditoPreFaturamento(){
        controlador = new FaturamentoUtil();
        
        Collection<CreditoARealizar> creditos = new ArrayList<CreditoARealizar>();
        CreditoARealizar c1 = new CreditoARealizar();
        c1.setValorCredito(new BigDecimal(35));
        c1.setNumeroPrestacaoCredito((short) 6);
        c1.setNumeroPrestacaoRealizada((short) 5);
        c1.setValorResidualMesAnterior(BigDecimal.ZERO);
        c1.setAnoMesReferenciaPrestacao(201506);
        
        
        creditos.add(c1);
        
        AtualizacaoCreditoARealizarHelper helper = controlador.atualizarCreditosARealizar(anoMesFaturamento, creditos, valorAgua, valorEsgoto, valorDebitos, true);
        
        assertEquals(1, helper.getCreditos().size());
        
        ItemCreditoARealizar item = helper.getCreditos().iterator().next();
        
        CreditoARealizar credito = item.getCreditoARelizar();
        
        assertEquals(6, credito.getNumeroPrestacaoRealizada().intValue());
        assertEquals(5.85, item.getCreditoCalculado().doubleValue(), 0);
    }
    
    public void testaTotalContaCreditoFaturamento(){
        controlador = new FaturamentoUtil();
        
        Collection<CreditoARealizar> creditos = new ArrayList<CreditoARealizar>();
        CreditoARealizar c1 = new CreditoARealizar();
        c1.setValorCredito(new BigDecimal(35));
        c1.setNumeroPrestacaoCredito((short) 6);
        c1.setNumeroPrestacaoRealizada((short) 6);
        c1.setValorResidualMesAnterior(BigDecimal.ZERO);
        c1.setAnoMesReferenciaPrestacao(201507);
        
        creditos.add(c1);
        
        valorAgua = new BigDecimal(14);
        
        AtualizacaoCreditoARealizarHelper helper = controlador.atualizarCreditosARealizar(anoMesFaturamento, creditos, valorAgua, valorEsgoto, valorDebitos, false);
        
        assertEquals(1, helper.getCreditos().size());
        
        ItemCreditoARealizar item = helper.getCreditos().iterator().next();
        
        CreditoARealizar credito = item.getCreditoARelizar();
        
        assertEquals(6, credito.getNumeroPrestacaoRealizada().intValue());
        assertEquals(5.85, item.getCreditoCalculado().doubleValue(), 0);
    }
    
    public void testaParcela5de6Faturamento(){
        controlador = new FaturamentoUtil();
        
        Collection<CreditoARealizar> creditos = new ArrayList<CreditoARealizar>();
        CreditoARealizar c1 = new CreditoARealizar();
        c1.setValorCredito(new BigDecimal(35));
        c1.setNumeroPrestacaoCredito((short) 6);
        c1.setNumeroPrestacaoRealizada((short) 5);
        c1.setValorResidualMesAnterior(BigDecimal.ZERO);
        c1.setAnoMesReferenciaPrestacao(201507);
        
        creditos.add(c1);
        
        valorAgua = new BigDecimal(14);
        
        AtualizacaoCreditoARealizarHelper helper = controlador.atualizarCreditosARealizar(anoMesFaturamento, creditos, valorAgua, valorEsgoto, valorDebitos, false);
        
        assertEquals(1, helper.getCreditos().size());
        
        ItemCreditoARealizar item = helper.getCreditos().iterator().next();
        
        CreditoARealizar credito = item.getCreditoARelizar();
        
        assertEquals(5, credito.getNumeroPrestacaoRealizada().intValue());
        assertEquals(5.83, item.getCreditoCalculado().doubleValue(), 0);
    }    
    
    public void testaParcela4de6Faturamento(){
        controlador = new FaturamentoUtil();
        
        Collection<CreditoARealizar> creditos = new ArrayList<CreditoARealizar>();
        CreditoARealizar c1 = new CreditoARealizar();
        c1.setValorCredito(new BigDecimal(35));
        c1.setNumeroPrestacaoCredito((short) 6);
        c1.setNumeroPrestacaoRealizada((short) 4);
        c1.setValorResidualMesAnterior(BigDecimal.ZERO);
        c1.setAnoMesReferenciaPrestacao(201507);
        
        creditos.add(c1);
        
        valorAgua = new BigDecimal(14);
        
        AtualizacaoCreditoARealizarHelper helper = controlador.atualizarCreditosARealizar(anoMesFaturamento, creditos, valorAgua, valorEsgoto, valorDebitos, false);
        
        assertEquals(1, helper.getCreditos().size());
        
        ItemCreditoARealizar item = helper.getCreditos().iterator().next();
        
        CreditoARealizar credito = item.getCreditoARelizar();
        
        assertEquals(4, credito.getNumeroPrestacaoRealizada().intValue());
        assertEquals(5.83, item.getCreditoCalculado().doubleValue(), 0);
    }    
}