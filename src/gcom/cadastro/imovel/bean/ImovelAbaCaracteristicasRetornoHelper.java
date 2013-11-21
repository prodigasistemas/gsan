package gcom.cadastro.imovel.bean;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.imovel.PiscinaVolumeFaixa;
import gcom.cadastro.imovel.ReservatorioVolumeFaixa;

public class ImovelAbaCaracteristicasRetornoHelper {
	
	private AreaConstruidaFaixa areaConstruidaFaixa;
	private ReservatorioVolumeFaixa reservatorioVolumeFaixaInferior;
	private ReservatorioVolumeFaixa reservatorioVolumeFaixaSuperior;
	private PiscinaVolumeFaixa piscinaVolumeFaixa;
	
	private PavimentoCalcada pavimentoCalcada;
	private PavimentoRua pavimentoRua;
	private FonteAbastecimento fonteAbastecimento;
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	private LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento;
	private ImovelPerfil imovelPerfil;
	
	/**
	 * @return Retorna o campo areaConstruidaFaixa.
	 */
	public AreaConstruidaFaixa getAreaConstruidaFaixa() {
		return areaConstruidaFaixa;
	}
	/**
	 * @param areaConstruidaFaixa O areaConstruidaFaixa a ser setado.
	 */
	public void setAreaConstruidaFaixa(AreaConstruidaFaixa areaConstruidaFaixa) {
		this.areaConstruidaFaixa = areaConstruidaFaixa;
	}
	/**
	 * @return Retorna o campo fonteAbastecimento.
	 */
	public FonteAbastecimento getFonteAbastecimento() {
		return fonteAbastecimento;
	}
	/**
	 * @param fonteAbastecimento O fonteAbastecimento a ser setado.
	 */
	public void setFonteAbastecimento(FonteAbastecimento fonteAbastecimento) {
		this.fonteAbastecimento = fonteAbastecimento;
	}
	/**
	 * @return Retorna o campo imovelPerfil.
	 */
	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}
	/**
	 * @param imovelPerfil O imovelPerfil a ser setado.
	 */
	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}
	/**
	 * @return Retorna o campo ligacaoAguaSituacao.
	 */
	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}
	/**
	 * @param ligacaoAguaSituacao O ligacaoAguaSituacao a ser setado.
	 */
	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}
	/**
	 * @return Retorna o campo ligacaoEsgotoSituacao.
	 */
	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}
	/**
	 * @param ligacaoEsgotoSituacao O ligacaoEsgotoSituacao a ser setado.
	 */
	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}
	/**
	 * @return Retorna o campo pavimentoCalcada.
	 */
	public PavimentoCalcada getPavimentoCalcada() {
		return pavimentoCalcada;
	}
	/**
	 * @param pavimentoCalcada O pavimentoCalcada a ser setado.
	 */
	public void setPavimentoCalcada(PavimentoCalcada pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}
	/**
	 * @return Retorna o campo pavimentoRua.
	 */
	public PavimentoRua getPavimentoRua() {
		return pavimentoRua;
	}
	/**
	 * @param pavimentoRua O pavimentoRua a ser setado.
	 */
	public void setPavimentoRua(PavimentoRua pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}
	/**
	 * @return Retorna o campo ligacaoEsgotoEsgotamento.
	 */
	public LigacaoEsgotoEsgotamento getLigacaoEsgotoEsgotamento() {
		return ligacaoEsgotoEsgotamento;
	}
	/**
	 * @param ligacaoEsgotoEsgotamento O ligacaoEsgotoEsgotamento a ser setado.
	 */
	public void setLigacaoEsgotoEsgotamento(
			LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento) {
		this.ligacaoEsgotoEsgotamento = ligacaoEsgotoEsgotamento;
	}
	/**
	 * @return Retorna o campo piscinaVolumeFaixa.
	 */
	public PiscinaVolumeFaixa getPiscinaVolumeFaixa() {
		return piscinaVolumeFaixa;
	}
	/**
	 * @param piscinaVolumeFaixa O piscinaVolumeFaixa a ser setado.
	 */
	public void setPiscinaVolumeFaixa(PiscinaVolumeFaixa piscinaVolumeFaixa) {
		this.piscinaVolumeFaixa = piscinaVolumeFaixa;
	}
	/**
	 * @return Retorna o campo reservatorioVolumeFaixaInferior.
	 */
	public ReservatorioVolumeFaixa getReservatorioVolumeFaixaInferior() {
		return reservatorioVolumeFaixaInferior;
	}
	/**
	 * @param reservatorioVolumeFaixaInferior O reservatorioVolumeFaixaInferior a ser setado.
	 */
	public void setReservatorioVolumeFaixaInferior(
			ReservatorioVolumeFaixa reservatorioVolumeFaixaInferior) {
		this.reservatorioVolumeFaixaInferior = reservatorioVolumeFaixaInferior;
	}
	/**
	 * @return Retorna o campo reservatorioVolumeFaixaSuperior.
	 */
	public ReservatorioVolumeFaixa getReservatorioVolumeFaixaSuperior() {
		return reservatorioVolumeFaixaSuperior;
	}
	/**
	 * @param reservatorioVolumeFaixaSuperior O reservatorioVolumeFaixaSuperior a ser setado.
	 */
	public void setReservatorioVolumeFaixaSuperior(
			ReservatorioVolumeFaixa reservatorioVolumeFaixaSuperior) {
		this.reservatorioVolumeFaixaSuperior = reservatorioVolumeFaixaSuperior;
	}

}
