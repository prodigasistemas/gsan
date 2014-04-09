package gcom.gui.faturamento.debito;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * Form utilizado na pesquisa de débitos a cobrar de um imóvel 
 *
 * @author Pedro Alexandre
 * @date 13/03/2006
 */
public class PesquisarDebitoACobrarActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String referenciaDebitoInicial;

	private String referenciaDebitoFinal;

	private String dataGeracaoDebitoInicial;

	private String dataGeracaoDebitoFinal;

	private String[] idSituacaoDebitoACobrar;
	
	private String[] idTipoDebito;
	
	private String[] idTipoDebitoSelecionados;

	private String idImovel;

    private String inscricaoImovel;
    
    private String nomeClienteUsuario;
	
    private String situacaoAguaImovel;
	
    private String situacaoEsgotoImovel;

	public String getDataGeracaoDebitoFinal() {
		return dataGeracaoDebitoFinal;
	}

	public void setDataGeracaoDebitoFinal(String dataGeracaoDebitoFinal) {
		this.dataGeracaoDebitoFinal = dataGeracaoDebitoFinal;
	}

	public String getDataGeracaoDebitoInicial() {
		return dataGeracaoDebitoInicial;
	}

	public void setDataGeracaoDebitoInicial(String dataGeracaoDebitoInicial) {
		this.dataGeracaoDebitoInicial = dataGeracaoDebitoInicial;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String[] getIdSituacaoDebitoACobrar() {
		return idSituacaoDebitoACobrar;
	}

	public void setIdSituacaoDebitoACobrar(String[] idSituacaoDebitoACobrar) {
		this.idSituacaoDebitoACobrar = idSituacaoDebitoACobrar;
	}

	public String[] getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(String[] idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	public String getReferenciaDebitoFinal() {
		return referenciaDebitoFinal;
	}

	public void setReferenciaDebitoFinal(String referenciaDebitoFinal) {
		this.referenciaDebitoFinal = referenciaDebitoFinal;
	}

	public String getReferenciaDebitoInicial() {
		return referenciaDebitoInicial;
	}

	public void setReferenciaDebitoInicial(String referenciaDebitoInicial) {
		this.referenciaDebitoInicial = referenciaDebitoInicial;
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

	public String[] getIdTipoDebitoSelecionados() {
		return idTipoDebitoSelecionados;
	}

	public void setIdTipoDebitoSelecionados(String[] idTipoDebitoSelecionados) {
		this.idTipoDebitoSelecionados = idTipoDebitoSelecionados;
	}
    
	

}
