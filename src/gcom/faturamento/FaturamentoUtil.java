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
        
        while (iteratorColecaoCreditosARealizar.hasNext() && valorTotalACobrar.compareTo(ConstantesSistema.VALOR_ZERO) == 1) {

            creditoARealizar = (CreditoARealizar) iteratorColecaoCreditosARealizar.next();
            
            logger.info("      ANTES -----> " + creditoARealizar.getId() + " : " + creditoARealizar.getNumeroPrestacaoRealizada() + " de " + creditoARealizar.getNumeroPrestacaoCredito() + " : "
                    + creditoARealizar.getValorTotal() + " credito : "
                    + creditoARealizar.getValorResidualMesAnterior() + " resíduo : " + creditoARealizar.getAnoMesCobrancaCredito() + " cobranca : " 
                    + creditoARealizar.getAnoMesReferenciaPrestacao() + " prestacao <-----      ");
            
            BigDecimal valorCredito = creditoARealizar.calculaValorParcelaIntermediaria(preFaturamento).add(creditoARealizar.getValorResidualMesAnterior());
            
            if (creditoARealizar.concedidoNaReferenciaAtual(anoMesFaturamento.intValue()) && creditoARealizar.isUltimaPrestacao()){
                valorCredito = creditoARealizar.calculaCreditoOuResiduo();
            }

            if (creditoARealizar.nuncaFoiConcedido() || !creditoARealizar.concedidoNaReferenciaAtual(anoMesFaturamento.intValue())) {
                creditoARealizar.incrementaPrestacoesRealizadas();
                creditoARealizar.setValorResidualConcedidoMes(creditoARealizar.getValorResidualMesAnterior());
                creditoARealizar.setAnoMesReferenciaPrestacao(anoMesFaturamento);
            }
            
            if (!preFaturamento) {
                valorTotalACobrar = valorTotalACobrar.subtract(valorCredito);
            }

            if (valorTotalACobrar.compareTo(ConstantesSistema.VALOR_ZERO) == -1) {

                creditoARealizar.setValorResidualMesAnterior(valorTotalACobrar.multiply(new BigDecimal("-1")));

                valorCredito = valorCredito.subtract(creditoARealizar.getValorResidualMesAnterior());

                valorTotalACobrar = ConstantesSistema.VALOR_ZERO;

            } else {

                if (!preFaturamento) {
                    creditoARealizar.setValorResidualMesAnterior(ConstantesSistema.VALOR_ZERO);
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
}