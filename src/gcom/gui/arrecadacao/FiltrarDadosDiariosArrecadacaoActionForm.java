package gcom.gui.arrecadacao;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe 
 *
 * @author Fernanda Paiva
 * @date 15/05/2006
 */
public class FiltrarDadosDiariosArrecadacaoActionForm extends ValidatorActionForm {
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
	private String[] imovelPerfil;
	private String[] documentoTipo;
	private String descricaoDocumentoTipo;
	private String[] ligacaoAgua;
	private String[] ligacaoEsgoto;
	private String[] categoria;
	private String[] esferaPoder;
	private BigDecimal valor;
	private String localidadeDoElo;
	private String eloDiferente;
	private String perfilImovel;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	private String categoria2;
	private String esferaPoder2;
	private String tipoDocumento2;
	/**
	 * @return Retorna o campo situacaoLigacaoAgua.
	 */
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	/**
	 * @param situacaoLigacaoAgua O situacaoLigacaoAgua a ser setado.
	 */
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
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
	 * @return Retorna o campo imovelPerfil.
	 */
	public String[] getImovelPerfil() {
		return imovelPerfil;
	}
	/**
	 * @param imovelPerfil O imovelPerfil a ser setado.
	 */
	public void setImovelPerfil(String[] imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}
	/**
	 * @return Retorna o campo ligacaoAgua.
	 */
	public String[] getLigacaoAgua() {
		return ligacaoAgua;
	}
	/**
	 * @param ligacaoAgua O ligacaoAgua a ser setado.
	 */
	public void setLigacaoAgua(String[] ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}
	/**
	 * @return Retorna o campo ligacaoEsgoto.
	 */
	public String[] getLigacaoEsgoto() {
		return ligacaoEsgoto;
	}
	/**
	 * @param ligacaoEsgoto O ligacaoEsgoto a ser setado.
	 */
	public void setLigacaoEsgoto(String[] ligacaoEsgoto) {
		this.ligacaoEsgoto = ligacaoEsgoto;
	}
	/**
	 * @return Retorna o campo categoria.
	 */
	public String[] getCategoria() {
		return categoria;
	}
	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(String[] categoria) {
		this.categoria = categoria;
	}
	/**
	 * @return Retorna o campo esferaPoder.
	 */
	public String[] getEsferaPoder() {
		return esferaPoder;
	}
	/**
	 * @param esferaPoder O esferaPoder a ser setado.
	 */
	public void setEsferaPoder(String[] esferaPoder) {
		this.esferaPoder = esferaPoder;
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
	 * @return Retorna o campo perfilImovel.
	 */
	public String getPerfilImovel() {
		return perfilImovel;
	}
	/**
	 * @param perfilImovel O perfilImovel a ser setado.
	 */
	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
	/**
	 * @return Retorna o campo categoria2.
	 */
	public String getCategoria2() {
		return categoria2;
	}
	/**
	 * @param categoria2 O categoria2 a ser setado.
	 */
	public void setCategoria2(String categoria2) {
		this.categoria2 = categoria2;
	}
	/**
	 * @return Retorna o campo esferaPoder2.
	 */
	public String getEsferaPoder2() {
		return esferaPoder2;
	}
	/**
	 * @param esferaPoder2 O esferaPoder2 a ser setado.
	 */
	public void setEsferaPoder2(String esferaPoder2) {
		this.esferaPoder2 = esferaPoder2;
	}
	/**
	 * @return Retorna o campo situacaoLigacaoEsgoto.
	 */
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	/**
	 * @param situacaoLigacaoEsgoto O situacaoLigacaoEsgoto a ser setado.
	 */
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
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
