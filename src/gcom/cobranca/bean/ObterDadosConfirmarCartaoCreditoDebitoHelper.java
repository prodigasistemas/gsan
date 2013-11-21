package gcom.cobranca.bean;

import java.io.Serializable;
import java.util.Collection;

/**
 * Encapsula as informações necessárias para confirmar pagamento(s) por cartão de crédito ou débito
 * 
 * @author Raphael Rossiter
 * @date 05/01/2010
 */
public class ObterDadosConfirmarCartaoCreditoDebitoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Short modalidade;
	
	private Collection colecaoParcelamentosModalidadeCredito;
	
	private ObterDebitoImovelOuClienteHelper debitosImovelModalidadeDebito;
	
	public ObterDadosConfirmarCartaoCreditoDebitoHelper(){}

	
	public Short getModalidade() {
		return modalidade;
	}

	public void setModalidade(Short modalidade) {
		this.modalidade = modalidade;
	}

	public Collection getColecaoParcelamentosModalidadeCredito() {
		return colecaoParcelamentosModalidadeCredito;
	}

	public void setColecaoParcelamentosModalidadeCredito(
			Collection colecaoParcelamentosModalidadeCredito) {
		this.colecaoParcelamentosModalidadeCredito = colecaoParcelamentosModalidadeCredito;
	}

	public ObterDebitoImovelOuClienteHelper getDebitosImovelModalidadeDebito() {
		return debitosImovelModalidadeDebito;
	}

	public void setDebitosImovelModalidadeDebito(
			ObterDebitoImovelOuClienteHelper debitosImovelModalidadeDebito) {
		this.debitosImovelModalidadeDebito = debitosImovelModalidadeDebito;
	}
}
