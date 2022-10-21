package gcom.faturamento;

import gcom.faturamento.credito.CreditoARealizar;
import gcom.util.ConstantesSistema;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import org.jboss.logging.Logger;

public class FaturamentoUtil {
	
	private static Logger logger = Logger.getLogger(ControladorFaturamentoFINAL.class);
	
    public AtualizacaoCreditoARealizarHelper atualizarCreditosARealizar(Integer anoMesFaturamento
            , Collection<CreditoARealizar> creditos
            , BigDecimal valorAgua
            , BigDecimal valorEsgoto
            , BigDecimal valorDebitos
            , boolean preFaturamento){
        
        AtualizacaoCreditoARealizarHelper helper = new AtualizacaoCreditoARealizarHelper();
        
        BigDecimal valorTotalCreditos = ConstantesSistema.VALOR_ZERO;
        BigDecimal valorTotalACobrar = ConstantesSistema.VALOR_ZERO;

        BigDecimal parte1 = valorTotalACobrar.add(valorAgua);
        BigDecimal parte2 = parte1.add(valorEsgoto);
        valorTotalACobrar = parte2.add(valorDebitos);

        if (preFaturamento) {
            valorTotalACobrar = BigDecimal.ONE;
        }
        
	        Iterator<CreditoARealizar> iteratorColecaoCreditosARealizar = creditos.iterator();
	
	        CreditoARealizar creditoARealizar = null;
	        
	        CreditoARealizar creditoBolsaAgua = obterCreditoBolsaAgua(creditos, anoMesFaturamento);
	        
	        if (creditoBolsaAgua != null) {
	        	
	        	logger.info("  BOLSA AGUA ANTES -----> " + creditoBolsaAgua.getId() + " : " 
	                    + creditoBolsaAgua.getValorTotal() + " credito : "
	                    + creditoBolsaAgua.getValorResidualMesAnterior() + " resíduo : " 
	                    + creditoBolsaAgua.getAnoMesCobrancaCredito() + " cobranca : " 
	                    + creditoBolsaAgua.getAnoMesReferenciaPrestacao() + " prestacao <-----      ");
	        	BigDecimal valorCreditoBolsaAgua = creditoBolsaAgua.getValorTotal();	        	
	        	
	        	if (!preFaturamento) {
	        		valorCreditoBolsaAgua = creditoBolsaAgua.getValorCredito();
	        			        		        		
	        		creditoBolsaAgua.setValorCredito(valorCreditoBolsaAgua);
	        		creditoBolsaAgua.setValorResidualMesAnterior(BigDecimal.ZERO);
	        		creditoBolsaAgua.setAnoMesReferenciaPrestacao(anoMesFaturamento);
	        		
	        		valorTotalACobrar = valorTotalACobrar.subtract(valorCreditoBolsaAgua);
	        		
	        		if (creditoBolsaAgua.getNumeroPrestacaoRealizada().intValue() < 1) {
		        		creditoBolsaAgua.setNumeroPrestacaoRealizada(new Short("1"));
		        	}
	        	}
	        	
	        	valorTotalCreditos = valorTotalCreditos.add(valorCreditoBolsaAgua);
	        	helper.addCreditoARealizar(valorCreditoBolsaAgua, creditoBolsaAgua);
	        	
	        	logger.info("  BOLSA AGUA DEPOIS -----> " + creditoBolsaAgua.getId() + " : " 
	                    + creditoBolsaAgua.getValorTotal() + " credito : "
	                    + creditoBolsaAgua.getValorResidualMesAnterior() + " resíduo : " 
	                    + creditoBolsaAgua.getAnoMesCobrancaCredito() + " cobranca : " 
	                    + creditoBolsaAgua.getAnoMesReferenciaPrestacao() + " prestacao <-----      ");
	        }
	        
	        while (iteratorColecaoCreditosARealizar.hasNext() && valorTotalACobrar.compareTo(ConstantesSistema.VALOR_ZERO) == 1) {
	
	            creditoARealizar = (CreditoARealizar) iteratorColecaoCreditosARealizar.next();
	            
	            if (creditoARealizar.isCreditoBolsaAgua()) {
	            	continue;
	            }
	            
	            logger.info("      ANTES -----> " + creditoARealizar.getId() + " : " 
	            		+ creditoARealizar.getNumeroPrestacaoRealizada() + " de " + creditoARealizar.getNumeroPrestacaoCredito() + " : "
	                    + creditoARealizar.getValorTotal() + " credito : "
	                    + creditoARealizar.getValorResidualMesAnterior() + " resíduo : " 
	                    + creditoARealizar.getAnoMesCobrancaCredito() + " cobranca : " 
	                    + creditoARealizar.getAnoMesReferenciaPrestacao() + " prestacao <-----      ");
	            
	            BigDecimal valorCredito = creditoARealizar.calculaValorParcelaIntermediaria(preFaturamento).add(creditoARealizar.getValorResidualMesAnterior());
	            
	            if (creditoARealizar.concedidoNaReferenciaAtual(anoMesFaturamento.intValue()) && creditoARealizar.isUltimaPrestacao()){
	                valorCredito = creditoARealizar.calculaCreditoOuResiduo();
	                logger.info("      DURANTE 1 -----> " + creditoARealizar.getId() + " |  valorCredito: " + valorCredito + " <-----      ");
	            }
	            logger.info("      DURANTE 2 -----> " + creditoARealizar.getId() + " |  valorCredito: " + valorCredito + " <-----      ");
	            
	            if (creditoARealizar.nuncaFoiConcedido() || !creditoARealizar.concedidoNaReferenciaAtual(anoMesFaturamento.intValue())) {
	                creditoARealizar.incrementaPrestacoesRealizadas();
	                creditoARealizar.setValorResidualConcedidoMes(creditoARealizar.getValorResidualMesAnterior());
	                creditoARealizar.setAnoMesReferenciaPrestacao(anoMesFaturamento);
	                logger.info("      DURANTE 3 -----> " + creditoARealizar.getId() + " |  valorCredito: " + valorCredito + " <-----      ");
	            }
	            
	            if (!preFaturamento) {
	                valorTotalACobrar = valorTotalACobrar.subtract(valorCredito);
	                logger.info("      DURANTE 4 -----> " + creditoARealizar.getId() + " |  ValorResidualMesAnterior: " + creditoARealizar.getValorResidualMesAnterior() + " <-----      ");
	            }
	
	            if (valorTotalACobrar.compareTo(ConstantesSistema.VALOR_ZERO) == -1) {
	
	                creditoARealizar.setValorResidualMesAnterior(valorTotalACobrar.multiply(new BigDecimal("-1")));
	
	                valorCredito = valorCredito.subtract(creditoARealizar.getValorResidualMesAnterior());
	
	                valorTotalACobrar = ConstantesSistema.VALOR_ZERO;
	                
	                logger.info("      DURANTE 5 -----> " + creditoARealizar.getId() + " |  ValorResidualMesAnterior: " + creditoARealizar.getValorResidualMesAnterior() + " <-----      ");
	
	            } else {
	
	                if (!preFaturamento) {
	                    creditoARealizar.setValorResidualMesAnterior(ConstantesSistema.VALOR_ZERO);
	                    logger.info("      DURANTE 6 -----> " + creditoARealizar.getId() + " |  ValorResidualMesAnterior: " + creditoARealizar.getValorResidualMesAnterior() + " <-----      ");
	                }
	            }
	
	            valorTotalCreditos = valorTotalCreditos.add(valorCredito);
	
	            logger.info("      DEPOIS -----> " + creditoARealizar.getId() + " : " + creditoARealizar.getNumeroPrestacaoRealizada() + " de " + creditoARealizar.getNumeroPrestacaoCredito() + " : "
	                    + creditoARealizar.getValorTotal() + " credito : "
	                    + creditoARealizar.getValorResidualMesAnterior() + " resíduo : " + creditoARealizar.getAnoMesCobrancaCredito() + " cobranca : " 
	                    + creditoARealizar.getAnoMesReferenciaPrestacao() + " prestacao <-----      ");
	            
	            helper.addCreditoARealizar(valorCredito, creditoARealizar);
	        }
        
        helper.setValorTotalCreditos(valorTotalCreditos);
        helper.setValorTotalDebitos(valorTotalACobrar);
        
        return helper;
    }
    
    private CreditoARealizar obterCreditoBolsaAgua(Collection<CreditoARealizar> collectionCreditos, Integer anoMes) {
    	Iterator<CreditoARealizar> iteratorColecaoCreditosARealizar = collectionCreditos.iterator();

        while (iteratorColecaoCreditosARealizar.hasNext()) {
        	CreditoARealizar credito = iteratorColecaoCreditosARealizar.next();
        	
        	if (credito.isCreditoBolsaAgua() && credito.getAnoMesReferenciaCredito().equals(anoMes)) {
        		return credito;
        	}
        }
        
        return null;
    }
}