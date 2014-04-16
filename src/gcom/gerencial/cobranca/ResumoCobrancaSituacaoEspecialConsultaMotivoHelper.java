package gcom.gerencial.cobranca;

import java.math.BigDecimal;

/** 
 *
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoCobrancaSituacaoEspecialConsultaMotivoHelper {

	private Integer idMotivo;
	private String motivoDescricao;
	private Integer anoMesInicio;
	private Integer anoMesFim;
	private Integer qtParalisada;
	private Integer qtLigacoes;
	private BigDecimal percentual;
	private BigDecimal faturamentoEstimado;
	private String valorFaturamentoEstimadoFormatado;
	
	public Integer getAnoMesFim() {
		return anoMesFim;
	}
	public void setAnoMesFim(Integer anoMesFim) {
		this.anoMesFim = anoMesFim;
	}
	public Integer getAnoMesInicio() {
		return anoMesInicio;
	}
	public void setAnoMesInicio(Integer anoMesInicio) {
		this.anoMesInicio = anoMesInicio;
	}
	public BigDecimal getFaturamentoEstimado() {
		return faturamentoEstimado;
	}
	public void setFaturamentoEstimado(BigDecimal faturamentoEstimado) {
		this.faturamentoEstimado = faturamentoEstimado;
	}
	public Integer getIdMotivo() {
		return idMotivo;
	}
	public void setIdMotivo(Integer idMotivo) {
		this.idMotivo = idMotivo;
	}
	public String getMotivoDescricao() {
		return motivoDescricao;
	}
	public void setMotivoDescricao(String motivoDescricao) {
		this.motivoDescricao = motivoDescricao;
	}
	public BigDecimal getPercentual() {
		return percentual;
	}
	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}
	public Integer getQtLigacoes() {
		return qtLigacoes;
	}
	public void setQtLigacoes(Integer qtLigacoes) {
		this.qtLigacoes = qtLigacoes;
	}
	public Integer getQtParalisada() {
		return qtParalisada;
	}
	public void setQtParalisada(Integer qtParalisada) {
		this.qtParalisada = qtParalisada;
	}
	
	public ResumoCobrancaSituacaoEspecialConsultaMotivoHelper() {}
	
	public ResumoCobrancaSituacaoEspecialConsultaMotivoHelper(Integer idMotivo, String motivoDescricao, Integer anoMesInicio, Integer anoMesFim, Integer qtParalisada) {
		super();
		// TODO Auto-generated constructor stub
		this.idMotivo = idMotivo;
		this.motivoDescricao = motivoDescricao;
		this.anoMesInicio = anoMesInicio;
		this.anoMesFim = anoMesFim;
		this.qtParalisada = qtParalisada;
	}
	
	public String getValorFaturamentoEstimadoFormatado() {
		return valorFaturamentoEstimadoFormatado;
	}
	public void setValorFaturamentoEstimadoFormatado(
			String valorFaturamentoEstimadoFormatado) {
		this.valorFaturamentoEstimadoFormatado = valorFaturamentoEstimadoFormatado;
	}
	
	public String getFormatarAnoMesParaMesAnoInicio() {

		String anoMesRecebido = "" + this.getAnoMesInicio();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatadoInicio = mes + "/" + ano;

		return anoMesFormatadoInicio.toString();
	}
	
	public String getFormatarAnoMesParaMesAnoFim() {

		String anoMesRecebido = "" + this.getAnoMesFim();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatadoFim = mes + "/" + ano;

		return anoMesFormatadoFim.toString();
	}
}
