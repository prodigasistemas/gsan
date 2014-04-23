package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author Vivianne Sousa
 * @date 10/05/2011
 */
public class ReiterarRegistroAtendimentoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String numeroRA;
	private String dataPrevista;
	private String idTipoSolicitacao;
	private String descTipoSolicitacao;
	private String idEspecificacao;
	private String descEspecificacao;
	private String idUnidadeAtual;
	private String descUnidadeAtual;
	private String nomeSolicitante;
	private String idClienteSolicitante;
	private String idUnidadeSolicitante;	
	private String pontoReferencia;
	private String observacao;
	
	private String clienteEnderecoSelected;
	private String clienteFoneSelected;
	
	public String getClienteFoneSelected() {
		return clienteFoneSelected;
	}
	public void setClienteFoneSelected(String clienteFoneSelected) {
		this.clienteFoneSelected = clienteFoneSelected;
	}
	public String getClienteEnderecoSelected() {
		return clienteEnderecoSelected;
	}
	public void setClienteEnderecoSelected(String clienteEnderecoSelected) {
		this.clienteEnderecoSelected = clienteEnderecoSelected;
	}
	
	public ReiterarRegistroAtendimentoActionForm(String numeroRA, String dataPrevista, String idTipoSolicitacao, String descTipoSolicitacao, String idEspecificacao, String descEspecificacao, String idUnidadeAtual, String descUnidadeAtual, String nomeSolicitante, String idClienteSolicitante, String idUnidadeSolicitante, String pontoReferencia, String observacao, String clienteEnderecoSelected, String clienteFoneSelected) {
		super();
		// TODO Auto-generated constructor stub
		this.numeroRA = numeroRA;
		this.dataPrevista = dataPrevista;
		this.idTipoSolicitacao = idTipoSolicitacao;
		this.descTipoSolicitacao = descTipoSolicitacao;
		this.idEspecificacao = idEspecificacao;
		this.descEspecificacao = descEspecificacao;
		this.idUnidadeAtual = idUnidadeAtual;
		this.descUnidadeAtual = descUnidadeAtual;
		this.nomeSolicitante = nomeSolicitante;
		this.idClienteSolicitante = idClienteSolicitante;
		this.idUnidadeSolicitante = idUnidadeSolicitante;
		this.pontoReferencia = pontoReferencia;
		this.observacao = observacao;
		this.clienteEnderecoSelected = clienteEnderecoSelected;
		this.clienteFoneSelected = clienteFoneSelected;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public ReiterarRegistroAtendimentoActionForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	public String getDescEspecificacao() {
		return descEspecificacao;
	}
	public void setDescEspecificacao(String descEspecificacao) {
		this.descEspecificacao = descEspecificacao;
	}
	public String getDescTipoSolicitacao() {
		return descTipoSolicitacao;
	}
	public void setDescTipoSolicitacao(String descTipoSolicitacao) {
		this.descTipoSolicitacao = descTipoSolicitacao;
	}
	public String getDescUnidadeAtual() {
		return descUnidadeAtual;
	}
	public void setDescUnidadeAtual(String descUnidadeAtual) {
		this.descUnidadeAtual = descUnidadeAtual;
	}
	public String getIdClienteSolicitante() {
		return idClienteSolicitante;
	}
	public void setIdClienteSolicitante(String idClienteSolicitante) {
		this.idClienteSolicitante = idClienteSolicitante;
	}
	public String getIdEspecificacao() {
		return idEspecificacao;
	}
	public void setIdEspecificacao(String idEspecificacao) {
		this.idEspecificacao = idEspecificacao;
	}
	public String getIdTipoSolicitacao() {
		return idTipoSolicitacao;
	}
	public void setIdTipoSolicitacao(String idTipoSolicitacao) {
		this.idTipoSolicitacao = idTipoSolicitacao;
	}
	public String getIdUnidadeAtual() {
		return idUnidadeAtual;
	}
	public void setIdUnidadeAtual(String idUnidadeAtual) {
		this.idUnidadeAtual = idUnidadeAtual;
	}
	public String getIdUnidadeSolicitante() {
		return idUnidadeSolicitante;
	}
	public void setIdUnidadeSolicitante(String idUnidadeSolicitante) {
		this.idUnidadeSolicitante = idUnidadeSolicitante;
	}
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}
	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}
	public String getNumeroRA() {
		return numeroRA;
	}
	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}
	public String getPontoReferencia() {
		return pontoReferencia;
	}
	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}
	
	
	
	
	
}
