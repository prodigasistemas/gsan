package gcom.gui.cadastro.imovel;

import org.apache.struts.action.ActionForm;

public class GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

    private String localidade;
	private String nomeLocalidade;

	private String setorComercialID;
	private String setorComercialCD;
	private String nomeSetorComercial;

	private String quadraInicial;
	private String idQuadraInicial;
	
	private String quadraFinal;
	private String idQuadraFinal;

	private String idRota;
	private String codigoRota;
	
	private String idImovel;
	private String inscricaoImovel;
	
	private Integer tamanhoColecaoImovel;
	private String descricaoArquivo;
	private String idLeiturista;
	private String nomeLeiturista;
	private String idSituacaoTransmissao;
	
	
	
	/**
	 * @return Retorna o campo inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	/**
	 * @param inscricaoImovel O inscricaoImovel a ser setado.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	/**
	 * @return Retorna o campo idQuadraInicial.
	 */
	public String getIdQuadraInicial() {
		return idQuadraInicial;
	}
	/**
	 * @param idQuadraInicial O idQuadraInicial a ser setado.
	 */
	public void setIdQuadraInicial(String idQuadraInicial) {
		this.idQuadraInicial = idQuadraInicial;
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
	 * @return Retorna o campo nomeLocalidade.
	 */
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	/**
	 * @param nomeLocalidade O nomeLocalidade a ser setado.
	 */
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	/**
	 * @return Retorna o campo nomeSetorComercial.
	 */
	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}
	/**
	 * @param nomeSetorComercial O nomeSetorComercial a ser setado.
	 */
	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}
	/**
	 * @return Retorna o campo quadraInicial.
	 */
	public String getQuadraInicial() {
		return quadraInicial;
	}
	/**
	 * @param quadraInicial O quadraInicial a ser setado.
	 */
	public void setQuadraInicial(String quadraInicial) {
		this.quadraInicial = quadraInicial;
	}

	/**
	 * @return Retorna o campo idQuadraFinal.
	 */
	public String getIdQuadraFinal() {
		return idQuadraFinal;
	}
	/**
	 * @param idQuadraFinal O idQuadraFinal a ser setado.
	 */
	public void setIdQuadraFinal(String idQuadraFinal) {
		this.idQuadraFinal = idQuadraFinal;
	}
	/**
	 * @return Retorna o campo quadraFinal.
	 */
	public String getQuadraFinal() {
		return quadraFinal;
	}
	/**
	 * @param quadraFinal O quadraFinal a ser setado.
	 */
	public void setQuadraFinal(String quadraFinal) {
		this.quadraFinal = quadraFinal;
	}
	/**
	 * @return Retorna o campo setorComercialCD.
	 */
	public String getSetorComercialCD() {
		return setorComercialCD;
	}
	/**
	 * @param setorComercialCD O setorComercialCD a ser setado.
	 */
	public void setSetorComercialCD(String setorComercialCD) {
		this.setorComercialCD = setorComercialCD;
	}
	/**
	 * @return Retorna o campo setorComercialID.
	 */
	public String getSetorComercialID() {
		return setorComercialID;
	}
	/**
	 * @param setorComercialID O setorComercialID a ser setado.
	 */
	public void setSetorComercialID(String setorComercialID) {
		this.setorComercialID = setorComercialID;
	}
	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}
	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	/**
	 * @return Retorna o campo codigoRota.
	 */
	public String getCodigoRota() {
		return codigoRota;
	}
	/**
	 * @param codigoRota O codigoRota a ser setado.
	 */
	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}
	/**
	 * @return Retorna o campo idRota.
	 */
	public String getIdRota() {
		return idRota;
	}
	/**
	 * @param idRota O idRota a ser setado.
	 */
	public void setIdRota(String idRota) {
		this.idRota = idRota;
	}
	/**
	 * @return Retorna o campo descricaoArquivo.
	 */
	public String getDescricaoArquivo() {
		return descricaoArquivo;
	}
	/**
	 * @param descricaoArquivo O descricaoArquivo a ser setado.
	 */
	public void setDescricaoArquivo(String descricaoArquivo) {
		this.descricaoArquivo = descricaoArquivo;
	}
	/**
	 * @return Retorna o campo idSituacaoTransmissao.
	 */
	public String getIdSituacaoTransmissao() {
		return idSituacaoTransmissao;
	}
	/**
	 * @param idSituacaoTransmissao O idSituacaoTransmissao a ser setado.
	 */
	public void setIdSituacaoTransmissao(String idSituacaoTransmissao) {
		this.idSituacaoTransmissao = idSituacaoTransmissao;
	}
	/**
	 * @return Retorna o campo tamanhoColecaoImovel.
	 */
	public Integer getTamanhoColecaoImovel() {
		return tamanhoColecaoImovel;
	}
	/**
	 * @param tamanhoColecaoImovel O tamanhoColecaoImovel a ser setado.
	 */
	public void setTamanhoColecaoImovel(Integer tamanhoColecaoImovel) {
		this.tamanhoColecaoImovel = tamanhoColecaoImovel;
	}
	/**
	 * @return Retorna o campo idLeiturista.
	 */
	public String getIdLeiturista() {
		return idLeiturista;
	}
	/**
	 * @param idLeiturista O idLeiturista a ser setado.
	 */
	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
	}
	/**
	 * @return Retorna o campo nomeLeiturista.
	 */
	public String getNomeLeiturista() {
		return nomeLeiturista;
	}
	/**
	 * @param nomeLeiturista O nomeLeiturista a ser setado.
	 */
	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}



}
