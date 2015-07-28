package gcom.faturamento;

import gcom.faturamento.credito.CreditoARealizar;
import gcom.util.ConstantesSistema;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import org.jboss.logging.Logger;

public class FaturamentoUtil {

    private static Logger logger = Logger.getLogger(FaturamentoUtil.class);

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
        
        Iterator iteratorColecaoCreditosARealizar = creditos.iterator();

        CreditoARealizar creditoARealizar = null;
        
        while (iteratorColecaoCreditosARealizar.hasNext() && valorTotalACobrar.compareTo(ConstantesSistema.VALOR_ZERO) == 1) {

            creditoARealizar = (CreditoARealizar) iteratorColecaoCreditosARealizar.next();
            
            logger.info("*********************** Credito a realizar");
            logger.info("ID               : " + creditoARealizar.getId());
            logger.info("realizadas       : " + creditoARealizar.getNumeroPrestacaoRealizada());
            logger.info("residual anterior: " + creditoARealizar.getValorResidualMesAnterior());
            logger.info("concedido mes    : " + creditoARealizar.getValorResidualConcedidoMes());
            
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

            // Acumula o valor do crédito
            valorTotalCreditos = valorTotalCreditos.add(valorCredito);

            logger.info(". realizadas       : " + creditoARealizar.getNumeroPrestacaoRealizada());
            logger.info(". residual anterior: " + creditoARealizar.getValorResidualMesAnterior());
            logger.info(". concedido mes    : " + creditoARealizar.getValorResidualConcedidoMes());
            
            helper.addCreditoARealizar(valorCredito, creditoARealizar);
        }
        
        helper.setValorTotalCreditos(valorTotalCreditos);
        helper.setValorTotalDebitos(valorTotalACobrar);
        
        return helper;
    }
}