package gcom.gui.arrecadacao;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorActionForm;

/**TODO:COSANPA
 * @author Adriana Muniz
 * @date 10/12/2012
 * 
 * Consulta por dados diários da arrecadação com as informações de tarifas
 * 
 */
public class FiltrarDadosDiariosArrecadacaoComTarifaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String periodoArrecadacaoInicio;
	private String periodoArrecadacaoFim;
	private String idGerenciaRegional;
	private String nomeGerenciaRegional;
	private String localidade;
	private String descricaoLocalidade;
	private String idElo;
	private String nomeElo;
	private String idArrecadador;
	private String nomeArrecadador;
	private String[] documentoTipo;
	private String descricaoDocumentoTipo;
	private BigDecimal valor;
	private String localidadeDoElo;
	private String eloDiferente;
	private String tipoDocumento2;
	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	/**
	 * @return Retorna o campo periodoArrecadacaoFim.
	 */
	public String getPeriodoArrecadacaoFim() {
		return periodoArrecadacaoFim;
	}
	/**
	 * @param periodoArrecadacaoFim O periodoArrecadacaoFim a ser setado.
	 */
	public void setPeriodoArrecadacaoFim(String periodoArrecadacaoFim) {
		this.periodoArrecadacaoFim = periodoArrecadacaoFim;
	}
	/**
	 * @return Retorna o campo periodoArrecadacaoInicio.
	 */
	public String getPeriodoArrecadacaoInicio() {
		return periodoArrecadacaoInicio;
	}
	/**
	 * @param periodoArrecadacaoInicio O periodoArrecadacaoInicio a ser setado.
	 */
	public void setPeriodoArrecadacaoInicio(String periodoArrecadacaoInicio) {
		this.periodoArrecadacaoInicio = periodoArrecadacaoInicio;
	}
	/**
	 * @return Retorna o campo descricaoLocalidade.
	 */
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	/**
	 * @param descricaoLocalidade O descricaoLocalidade a ser setado.
	 */
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	/**
	 * @return Retorna o campo localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}
	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	/**
	 * @return Retorna o campo idElo.
	 */
	public String getIdElo() {
		return idElo;
	}
	/**
	 * @param idElo O idElo a ser setado.
	 */
	public void setIdElo(String idElo) {
		this.idElo = idElo;
	}
	/**
	 * @return Retorna o campo nomeElo.
	 */
	public String getNomeElo() {
		return nomeElo;
	}
	/**
	 * @param nomeElo O nomeElo a ser setado.
	 */
	public void setNomeElo(String nomeElo) {
		this.nomeElo = nomeElo;
	}
	/**
	 * @return Retorna o campo idArrecadador.
	 */
	public String getIdArrecadador() {
		return idArrecadador;
	}
	/**
	 * @param idArrecadador O idArrecadador a ser setado.
	 */
	public void setIdArrecadador(String idArrecadador) {
		this.idArrecadador = idArrecadador;
	}
	/**
	 * @return Retorna o campo nomeArrecadador.
	 */
	public String getNomeArrecadador() {
		return nomeArrecadador;
	}
	/**
	 * @param nomeArrecadador O nomeArrecadador a ser setado.
	 */
	public void setNomeArrecadador(String nomeArrecadador) {
		this.nomeArrecadador = nomeArrecadador;
	}
	/**
	 * @return Retorna o campo descricaoDocumentoTipo.
	 */
	public String getDescricaoDocumentoTipo() {
		return descricaoDocumentoTipo;
	}
	/**
	 * @param descricaoDocumentoTipo O descricaoDocumentoTipo a ser setado.
	 */
	public void setDescricaoDocumentoTipo(String descricaoDocumentoTipo) {
		this.descricaoDocumentoTipo = descricaoDocumentoTipo;
	}
	/**
	 * @return Retorna o campo documentoTipo.
	 */
	public String[] getDocumentoTipo() {
		return documentoTipo;
	}
	/**
	 * @param documentoTipo O documentoTipo a ser setado.
	 */
	public void setDocumentoTipo(String[] documentoTipo) {
		this.documentoTipo = documentoTipo;
	}
	
	/**
	 * @return Retorna o campo nomeGerenciaRegional.
	 */
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	/**
	 * @param nomeGerenciaRegional O nomeGerenciaRegional a ser setado.
	 */
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	/**
	 * @return Retorna o campo valor.
	 */
	public BigDecimal getValor() {
		return valor;
	}
	/**
	 * @param valor O valor a ser setado.
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	/**
	 * @return Retorna o campo localidadeDoElo.
	 */
	public String getLocalidadeDoElo() {
		return localidadeDoElo;
	}
	/**
	 * @param localidadeDoElo O localidadeDoElo a ser setado.
	 */
	public void setLocalidadeDoElo(String localidadeDoElo) {
		this.localidadeDoElo = localidadeDoElo;
	}
	/**
	 * @return Retorna o campo eloDiferente.
	 */
	public String getEloDiferente() {
		return eloDiferente;
	}
	/**
	 * @param eloDiferente O eloDiferente a ser setado.
	 */
	public void setEloDiferente(String eloDiferente) {
		this.eloDiferente = eloDiferente;
	}
	/**
	 * @return Retorna o campo tipoDocumento2.
	 */
	public String getTipoDocumento2() {
		return tipoDocumento2;
	}
	/**
	 * @param tipoDocumento2 O tipoDocumento2 a ser setado.
	 */
	public void setTipoDocumento2(String tipoDocumento2) {
		this.tipoDocumento2 = tipoDocumento2;
	}
}
