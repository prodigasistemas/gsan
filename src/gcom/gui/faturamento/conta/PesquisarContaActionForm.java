package gcom.gui.faturamento.conta;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * Form utilizado na pesquisa de contas de um imóvel 
 *
 * @author Pedro Alexandre 
 * @date 02/03/2006
 */
public class PesquisarContaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String referenciaContaInicial;

	private String referenciaContaFinal;

	private String dataEmissaoContaInicial;

	private String dataEmissaoContaFinal;

	private String dataVencimentoContaInicial;

	private String dataVencimentoContaFinal;
	
	private String idImovel;

	private String[] idSituacaoConta;

    private String inscricaoImovel;
    
    private String nomeClienteUsuario;
	
    private String situacaoAguaImovel;
	
    private String situacaoEsgotoImovel;
    
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getSituacaoAguaImovel() {
		return situacaoAguaImovel;
	}

	public void setSituacaoAguaImovel(String situacaoAguaImovel) {
		this.situacaoAguaImovel = situacaoAguaImovel;
	}

	public String getSituacaoEsgotoImovel() {
		return situacaoEsgotoImovel;
	}

	public void setSituacaoEsgotoImovel(String situacaoEsgotoImovel) {
		this.situacaoEsgotoImovel = situacaoEsgotoImovel;
	}

	public String getDataEmissaoContaFinal() {
		return dataEmissaoContaFinal;
	}

	public void setDataEmissaoContaFinal(String dataEmissaoContaFinal) {
		this.dataEmissaoContaFinal = dataEmissaoContaFinal;
	}

	public String getDataEmissaoContaInicial() {
		return dataEmissaoContaInicial;
	}

	public void setDataEmissaoContaInicial(String dataEmissaoContaInicial) {
		this.dataEmissaoContaInicial = dataEmissaoContaInicial;
	}

	public String getDataVencimentoContaFinal() {
		return dataVencimentoContaFinal;
	}

	public void setDataVencimentoContaFinal(String dataVencimentoContaFinal) {
		this.dataVencimentoContaFinal = dataVencimentoContaFinal;
	}

	public String getDataVencimentoContaInicial() {
		return dataVencimentoContaInicial;
	}

	public void setDataVencimentoContaInicial(String dataVencimentoContaInicial) {
		this.dataVencimentoContaInicial = dataVencimentoContaInicial;
	}

	public String[] getIdSituacaoConta() {
		return idSituacaoConta;
	}

	public void setIdSituacaoConta(String[] idSituacaoConta) {
		this.idSituacaoConta = idSituacaoConta;
	}

	public String getReferenciaContaFinal() {
		return referenciaContaFinal;
	}

	public void setReferenciaContaFinal(String referenciaContaFinal) {
		this.referenciaContaFinal = referenciaContaFinal;
	}

	public String getReferenciaContaInicial() {
		return referenciaContaInicial;
	}

	public void setReferenciaContaInicial(String referenciaContaInicial) {
		this.referenciaContaInicial = referenciaContaInicial;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}


}
