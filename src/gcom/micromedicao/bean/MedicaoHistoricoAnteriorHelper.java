package gcom.micromedicao.bean;

import java.util.Date;

/**
 * Coleção dos dados do Consumo Historico da Medicao Individualizada
 * @author Sávio Luiz
 * @since 19/01/2006
 *
 */
public class MedicaoHistoricoAnteriorHelper {

	private Date dataLeituraAnteriorFaturamento;
	private Date dataInstacao;
	private int leituraAtualMesAnteriorFaturamento;
	private int leituraAtualMesAnteriorInformada;
	private Integer idLeituraSituacaoAnterior;
	private Integer idHidrometroInstalacaoHistorico;
	
	
	

	/**
	 * 
	 */
	public MedicaoHistoricoAnteriorHelper() {
		
	}




	public Date getDataInstacao() {
		return dataInstacao;
	}




	public void setDataInstacao(Date dataInstacao) {
		this.dataInstacao = dataInstacao;
	}




	public Date getDataLeituraAnteriorFaturamento() {
		return dataLeituraAnteriorFaturamento;
	}




	public void setDataLeituraAnteriorFaturamento(
			Date dataLeituraAnteriorFaturamento) {
		this.dataLeituraAnteriorFaturamento = dataLeituraAnteriorFaturamento;
	}




	public Integer getIdHidrometroInstalacaoHistorico() {
		return idHidrometroInstalacaoHistorico;
	}




	public void setIdHidrometroInstalacaoHistorico(
			Integer idHidrometroInstalacaoHistorico) {
		this.idHidrometroInstalacaoHistorico = idHidrometroInstalacaoHistorico;
	}




	public Integer getIdLeituraSituacaoAnterior() {
		return idLeituraSituacaoAnterior;
	}




	public void setIdLeituraSituacaoAnterior(Integer idLeituraSituacaoAnterior) {
		this.idLeituraSituacaoAnterior = idLeituraSituacaoAnterior;
	}




	public int getLeituraAtualMesAnteriorFaturamento() {
		return leituraAtualMesAnteriorFaturamento;
	}




	public void setLeituraAtualMesAnteriorFaturamento(
			int leituraAtualMesAnteriorFaturamento) {
		this.leituraAtualMesAnteriorFaturamento = leituraAtualMesAnteriorFaturamento;
	}




	public int getLeituraAtualMesAnteriorInformada() {
		return leituraAtualMesAnteriorInformada;
	}




	public void setLeituraAtualMesAnteriorInformada(
			int leituraAtualMesAnteriorInformada) {
		this.leituraAtualMesAnteriorInformada = leituraAtualMesAnteriorInformada;
	}
	
	

}
