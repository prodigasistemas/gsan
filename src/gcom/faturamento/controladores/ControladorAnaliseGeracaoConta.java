package gcom.faturamento.controladores;

import java.util.Collection;

import javax.ejb.CreateException;

import gcom.arrecadacao.repositorio.RepositorioDevolucao;
import gcom.cadastro.imovel.Imovel;
import gcom.enums.Status;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.repositorio.CreditoARealizarRepositorio;
import gcom.util.ControladorComum;
import gcom.util.ControladorException;

public class ControladorAnaliseGeracaoConta extends ControladorComum {
    private static final long serialVersionUID = 5517389533745886938L;

	private CreditoARealizarRepositorio creditoRealizarRepositorio;
	
	private RepositorioDevolucao repositorioDevolucao;
	
	public ControladorAnaliseGeracaoConta(){}
	
	public void ejbCreate() throws CreateException {
	    creditoRealizarRepositorio = CreditoARealizarRepositorio.getInstance();
	    repositorioDevolucao       = RepositorioDevolucao.getInstance();
	}
		
	public boolean verificarGeracaoConta(boolean aguaEsgotoZerados, int anoMesFaturamento, Imovel imovel) throws ControladorException {
		return verificarSituacaoImovelParaGerarConta(aguaEsgotoZerados, imovel) || verificarDebitosECreditosParaGerarConta(anoMesFaturamento, imovel) || verificarSituacaoDeCondominio(aguaEsgotoZerados, imovel);
	}

	public boolean verificarSituacaoDeCondominio(boolean aguaEsgotoZerados, Imovel imovel) {
        return aguaEsgotoZerados && !imovel.aguaLigada() && !imovel.esgotoLigado() && imovel.pertenceACondominio();
    }

    public boolean verificarDebitosECreditosParaGerarConta(int anoMesFaturamento, Imovel imovel) throws ControladorException{
	    boolean segundaCondicaoGerarConta = true;
	    
	    try {
	        Collection<DebitoACobrar> debitosACobrar = getControladorDebitoACobrar().debitosCobrarSemPagamentos(imovel.getId());
	        if (naoHaDebitosACobrar(debitosACobrar) || imovel.paralisacaoFaturamento()) {
	            return false;
	        }
	        
	        Collection<CreditoARealizar> creditosARealizar = creditoRealizarRepositorio.buscarCreditoRealizarPorImovel(imovel.getId(), 
	                DebitoCreditoSituacao.NORMAL, 
	                anoMesFaturamento);
	        
	        if (naoHaCreditoARealizar(creditosARealizar) || repositorioDevolucao.existeCreditoComDevolucao(creditosARealizar)) {
	            segundaCondicaoGerarConta = haDebitosCobrarAtivos(debitosACobrar);
	        }
        } catch (Exception e) {
            throw new ControladorException("Erro ao verificar debitos e creditos para gerar conta", e);
        }
		

		return segundaCondicaoGerarConta;
	}

	public boolean verificarSituacaoImovelParaGerarConta(boolean valoresAguaEsgotoZerados, Imovel imovel) {
		return !(valoresAguaEsgotoZerados || (!valoresAguaEsgotoZerados && !imovel.aguaLigada() && !imovel.esgotoLigado() && !imovel.pertenceACondominio()));
	}

	private boolean haDebitosCobrarAtivos(Collection<DebitoACobrar> debitosACobrar) {
		boolean haDebitosCobrarAtivos = false;
		for (DebitoACobrar debitoACobrar: debitosACobrar) {
			if (debitoACobrar.getDebitoTipo().getIndicadorGeracaoConta().shortValue() == Status.ATIVO.getId()) {
				haDebitosCobrarAtivos = true;
				break;
			}
		}
		return haDebitosCobrarAtivos;
	}

	private boolean naoHaCreditoARealizar(Collection<CreditoARealizar> creditosRealizar) {
		return creditosRealizar == null || creditosRealizar.isEmpty();
	}

	private boolean naoHaDebitosACobrar(Collection<DebitoACobrar> debitosACobrar) {
		return debitosACobrar == null || debitosACobrar.isEmpty();
	}
}
