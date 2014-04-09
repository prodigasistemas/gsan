package gcom.cadastro.imovel.bean;

import gcom.cadastro.imovel.Imovel;

public class ImovelAbaCaracteristicasHelper {

	private Imovel imovelAtualizar;
	private String areaConstruida;
	private String idAreaConstruidaFaixa;
	
	private String volumeReservatorioInferior;
	private String volumeReservatorioSuperior;
	private String volumePiscinaMovel;

	private String idPavimentoCalcada;
	private String idPavimentoRua;
	private String idFonteAbastecimento;
	private String idLigacaoAguaSituacao;
	private String idLigacaoEsgotoSituacao;
	private String idLigacaoEsgotoEsgotamento;
	private String idImovelPerfil;
	
	//*************************************************
	// Autor: Ivan Sergio
	// Data: 23/04/2009
	// CRC1657
	//*************************************************
	private String idSetorComercial;
	private String idQuadra;
	//*************************************************
	
	// Autor: Nathalia Santos
	// Data: 12/07/2011
	// RR201106690
	private String idNivelInstalacaoEsgoto;
	//*************************************************
	
	
	/**
	 * @return Retorna o campo idImovelPerfil.
	 */
	public String getIdImovelPerfil() {
		return idImovelPerfil;
	}
	/**
	 * @param idImovelPerfil O idImovelPerfil a ser setado.
	 */
	public void setIdImovelPerfil(String idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}
	/**
	 * @return Retorna o campo idFonteAbastecimento.
	 */
	public String getIdFonteAbastecimento() {
		return idFonteAbastecimento;
	}
	/**
	 * @param idFonteAbastecimento O idFonteAbastecimento a ser setado.
	 */
	public void setIdFonteAbastecimento(String idFonteAbastecimento) {
		this.idFonteAbastecimento = idFonteAbastecimento;
	}
	/**
	 * @return Retorna o campo idLigacaoAguaSituacao.
	 */
	public String getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}
	/**
	 * @param idLigacaoAguaSituacao O idLigacaoAguaSituacao a ser setado.
	 */
	public void setIdLigacaoAguaSituacao(String idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}
	/**
	 * @return Retorna o campo idLigacaoEsgotoSituacao.
	 */
	public String getIdLigacaoEsgotoSituacao() {
		return idLigacaoEsgotoSituacao;
	}
	/**
	 * @param idLigacaoEsgotoSituacao O idLigacaoEsgotoSituacao a ser setado.
	 */
	public void setIdLigacaoEsgotoSituacao(String idLigacaoEsgotoSituacao) {
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
	}
	/**
	 * @return Retorna o campo idPavimentoRua.
	 */
	public String getIdPavimentoRua() {
		return idPavimentoRua;
	}
	/**
	 * @param idPavimentoRua O idPavimentoRua a ser setado.
	 */
	public void setIdPavimentoRua(String idPavimentoRua) {
		this.idPavimentoRua = idPavimentoRua;
	}
	/**
	 * @return Retorna o campo idPavimentoCalcada.
	 */
	public String getIdPavimentoCalcada() {
		return idPavimentoCalcada;
	}
	/**
	 * @param idPavimentoCalcada O idPavimentoCalcada a ser setado.
	 */
	public void setIdPavimentoCalcada(String idPavimentoCalcada) {
		this.idPavimentoCalcada = idPavimentoCalcada;
	}
	/**
	 * @return Retorna o campo idAreaConstruidaFaixa.
	 */
	public String getIdAreaConstruidaFaixa() {
		return idAreaConstruidaFaixa;
	}
	/**
	 * @param idAreaConstruidaFaixa O idAreaConstruidaFaixa a ser setado.
	 */
	public void setIdAreaConstruidaFaixa(String idAreaConstruidaFaixa) {
		this.idAreaConstruidaFaixa = idAreaConstruidaFaixa;
	}
	/**
	 * @return Retorna o campo areaConstruida.
	 */
	public String getAreaConstruida() {
		return areaConstruida;
	}
	/**
	 * @param areaConstruida O areaConstruida a ser setado.
	 */
	public void setAreaConstruida(String areaConstruida) {
		this.areaConstruida = areaConstruida;
	}
	/**
	 * @return Retorna o campo idLigacaoEsgotoEsgotamento.
	 */
	public String getIdLigacaoEsgotoEsgotamento() {
		return idLigacaoEsgotoEsgotamento;
	}
	/**
	 * @param idLigacaoEsgotoEsgotamento O idLigacaoEsgotoEsgotamento a ser setado.
	 */
	public void setIdLigacaoEsgotoEsgotamento(String idLigacaoEsgotoEsgotamento) {
		this.idLigacaoEsgotoEsgotamento = idLigacaoEsgotoEsgotamento;
	}
	/**
	 * @return Retorna o campo volumePiscinaMovel.
	 */
	public String getVolumePiscinaMovel() {
		return volumePiscinaMovel;
	}
	/**
	 * @param volumePiscinaMovel O volumePiscinaMovel a ser setado.
	 */
	public void setVolumePiscinaMovel(String volumePiscinaMovel) {
		this.volumePiscinaMovel = volumePiscinaMovel;
	}
	/**
	 * @return Retorna o campo volumeReservatorioInferior.
	 */
	public String getVolumeReservatorioInferior() {
		return volumeReservatorioInferior;
	}
	/**
	 * @param volumeReservatorioInferior O volumeReservatorioInferior a ser setado.
	 */
	public void setVolumeReservatorioInferior(String volumeReservatorioInferior) {
		this.volumeReservatorioInferior = volumeReservatorioInferior;
	}
	/**
	 * @return Retorna o campo volumeReservatorioSuperior.
	 */
	public String getVolumeReservatorioSuperior() {
		return volumeReservatorioSuperior;
	}
	/**
	 * @param volumeReservatorioSuperior O volumeReservatorioSuperior a ser setado.
	 */
	public void setVolumeReservatorioSuperior(String volumeReservatorioSuperior) {
		this.volumeReservatorioSuperior = volumeReservatorioSuperior;
	}
	/**
	 * @return Retorna o campo imovelAtualizar.
	 */
	public Imovel getImovelAtualizar() {
		return imovelAtualizar;
	}
	/**
	 * @param imovelAtualizar O imovelAtualizar a ser setado.
	 */
	public void setImovelAtualizar(Imovel imovelAtualizar) {
		this.imovelAtualizar = imovelAtualizar;
	}
	/**
	 * @return Retorna o campo idQuadra.
	 */
	public String getIdQuadra() {
		return idQuadra;
	}
	/**
	 * @param idQuadra O idQuadra a ser setado.
	 */
	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}
	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public String getIdNivelInstalacaoEsgoto() {
		return idNivelInstalacaoEsgoto;
	}
	public void setIdNivelInstalacaoEsgoto(String idNivelInstalacaoEsgoto) {
		this.idNivelInstalacaoEsgoto = idNivelInstalacaoEsgoto;
	}
	
}
