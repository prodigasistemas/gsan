package gcom.relatorio.gerencial.faturamento;

import gcom.relatorio.RelatorioBean;

public class RelatorioResumoFaturamentoSituacaoEspecialBean implements RelatorioBean{

	private String faturamentoEstimadoGeral;
	private String faturamentoEstimadoGerencia;
	private String faturamentoEstimadoUnidadeNegocio;
	private String faturamentoEstimadoLocalidade;
	private String faturamentoEstimadoSetorComercial;
	private String faturamentoEstimadoMotivo;
	private String faturamentoEstimadoSituacao;
	private String gerenciaRegional;
	private String unidadeNegocio;
	private String localidade;
	private String setorComercial;
	private String mesAnoFim;
	private String mesAnoInicio;
	private String motivo;
	private String percentualGeral;
	private String percentualGerencia;
	private String percentualUnidadeNegocio;
	private String percentualLocalidade;
	private String percentualSetorComercial;
	private String percentualMotivo;
	private String percentualSituacao;
	private String qtdeImovelGeral;
	private String qtdeImovelGerencia;
	private String qtdeImovelUnidadeNegocio;
	private String qtdeImovelLocalidade;
	private String qtdeImovelSetorComercial;
	private String qtdeImovelMotivo;
	private String qtdeImovelSituacao;
	private String qtdeParalisadaGeral;
	private String qtdeParalisadaGerencia;
	private String qtdeParalisadaUnidadeNegocio;
	private String qtdeParalisadaMotivo;
	private String qtdeParalisadaSituacao;
	private String qtdeParalisadaLocalidade;
	private String qtdeParalisadaSetorComercial;
	private String situacao;
	
//	public RelatorioResumoFaturamentoSituacaoEspecialBean(
//			String faturamentoEstimadoGeral,
//			String faturamentoEstimadoGerencia,
//			String faturamentoEstimadoLocalidade,
//			String faturamentoEstimadoMotivo,
//			String faturamentoEstimadoSituacao, String gerenciaRegional,
//			String localidade,
//			String mesAnoFim, String mesAnoInicio, String motivo,
//			String percentualGeral, String percentualGerencia,
//			String percentualLocalidade,
//			String percentualMotivo,
//			String percentualSituacao, String qtdeImovelGeral,
//			String qtdeImovelGerencia,
//			String qtdeImovelLocalidade,
//			String qtdeImovelMotivo, String qtdeImovelSituacao,
//			String qtdeParalisadaGeral, String qtdeParalisadaGerencia,
//			String qtdeParalisadaMotivo,
//			String qtdeParalisadaSituacao, String qtdeParalisadaLocalidade,
//			String situacao) {
//
//		this.faturamentoEstimadoGeral = faturamentoEstimadoGeral;
//		this.faturamentoEstimadoGerencia = faturamentoEstimadoGerencia;
//		this.faturamentoEstimadoLocalidade = faturamentoEstimadoLocalidade;
//		this.faturamentoEstimadoMotivo = faturamentoEstimadoMotivo;
//		this.faturamentoEstimadoSituacao = faturamentoEstimadoSituacao;
//		this.gerenciaRegional = gerenciaRegional;
//		this.localidade = localidade;
//		this.mesAnoFim = mesAnoFim;
//		this.mesAnoInicio = mesAnoInicio;
//		this.motivo = motivo;
//		this.percentualGeral = percentualGeral;
//		this.percentualGerencia = percentualGerencia;
//		this.percentualLocalidade = percentualLocalidade;
//		this.percentualMotivo = percentualMotivo;
//		this.percentualSituacao = percentualSituacao;
//		this.qtdeImovelGeral = qtdeImovelGeral;
//		this.qtdeImovelGerencia = qtdeImovelGerencia;
//		this.qtdeImovelLocalidade = qtdeImovelLocalidade;
//		this.qtdeImovelMotivo = qtdeImovelMotivo;
//		this.qtdeImovelSituacao = qtdeImovelSituacao;
//		this.qtdeParalisadaGeral = qtdeParalisadaGeral;
//		this.qtdeParalisadaGerencia = qtdeParalisadaGerencia;
//		this.qtdeParalisadaMotivo = qtdeParalisadaMotivo;
//		this.qtdeParalisadaSituacao = qtdeParalisadaSituacao;
//		this.qtdeParalisadaLocalidade = qtdeParalisadaLocalidade;
//		this.situacao = situacao;
//	}
	
	public RelatorioResumoFaturamentoSituacaoEspecialBean
		(String faturamentoEstimadoGeral, String faturamentoEstimadoGerencia, String faturamentoEstimadoUnidadeNegocio,
		 String faturamentoEstimadoLocalidade, String faturamentoEstimadoSetorComercial, String faturamentoEstimadoMotivo,
		 String faturamentoEstimadoSituacao, String gerenciaRegional, String unidadeNegocio, String localidade, String setorComercial,
		 String mesAnoFim, String mesAnoInicio, String motivo, String percentualGeral,
		 String percentualGerencia, String percentualUnidadeNegocio, String percentualLocalidade, String percentualSetorComercial, String percentualMotivo,
		 String percentualSituacao, String qtdeImovelGeral, String qtdeImovelGerencia, String qtdeImovelUnidadeNegocio,
		 String qtdeImovelLocalidade, String qtdeImovelSetorComercial, String qtdeImovelMotivo, String qtdeImovelSituacao,
		 String qtdeParalisadaGeral, String qtdeParalisadaGerencia, String qtdeParalisadaUnidadeNegocio, String qtdeParalisadaMotivo,
		 String qtdeParalisadaSituacao, String qtdeParalisadaLocalidade, String qtdeParalisadaSetorComercial, String situacao) {

		this.faturamentoEstimadoGeral = faturamentoEstimadoGeral;
		this.faturamentoEstimadoGerencia = faturamentoEstimadoGerencia;
		this.faturamentoEstimadoUnidadeNegocio = faturamentoEstimadoUnidadeNegocio;
		this.faturamentoEstimadoLocalidade = faturamentoEstimadoLocalidade;
		this.faturamentoEstimadoSetorComercial = faturamentoEstimadoSetorComercial;
		this.faturamentoEstimadoMotivo = faturamentoEstimadoMotivo;
		this.faturamentoEstimadoSituacao = faturamentoEstimadoSituacao;
		this.gerenciaRegional = gerenciaRegional;
		this.unidadeNegocio = unidadeNegocio;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.mesAnoFim = mesAnoFim;
		this.mesAnoInicio = mesAnoInicio;
		this.motivo = motivo;
		this.percentualGeral = percentualGeral;
		this.percentualGerencia = percentualGerencia;
		this.percentualUnidadeNegocio = percentualUnidadeNegocio;
		this.percentualLocalidade = percentualLocalidade;
		this.percentualSetorComercial = percentualSetorComercial;
		this.percentualMotivo = percentualMotivo;
		this.percentualSituacao = percentualSituacao;
		this.qtdeImovelGeral = qtdeImovelGeral;
		this.qtdeImovelGerencia = qtdeImovelGerencia;
		this.qtdeImovelUnidadeNegocio = qtdeImovelUnidadeNegocio;
		this.qtdeImovelLocalidade = qtdeImovelLocalidade;
		this.qtdeImovelSetorComercial = qtdeImovelSetorComercial;
		this.qtdeImovelMotivo = qtdeImovelMotivo;
		this.qtdeImovelSituacao = qtdeImovelSituacao;
		this.qtdeParalisadaGeral = qtdeParalisadaGeral;
		this.qtdeParalisadaGerencia = qtdeParalisadaGerencia;
		this.qtdeParalisadaUnidadeNegocio = qtdeParalisadaUnidadeNegocio;
		this.qtdeParalisadaMotivo = qtdeParalisadaMotivo;
		this.qtdeParalisadaSituacao = qtdeParalisadaSituacao;
		this.qtdeParalisadaLocalidade = qtdeParalisadaLocalidade;
		this.qtdeParalisadaSetorComercial = qtdeParalisadaSetorComercial;
		this.situacao = situacao;
	}

	public String getFaturamentoEstimadoGeral() {
		return faturamentoEstimadoGeral;
	}

	public void setFaturamentoEstimadoGeral(String faturamentoEstimadoGeral) {
		this.faturamentoEstimadoGeral = faturamentoEstimadoGeral;
	}

	public String getFaturamentoEstimadoGerencia() {
		return faturamentoEstimadoGerencia;
	}

	public void setFaturamentoEstimadoGerencia(String faturamentoEstimadoGerencia) {
		this.faturamentoEstimadoGerencia = faturamentoEstimadoGerencia;
	}

	public String getFaturamentoEstimadoLocalidade() {
		return faturamentoEstimadoLocalidade;
	}

	public void setFaturamentoEstimadoLocalidade(
			String faturamentoEstimadoLocalidade) {
		this.faturamentoEstimadoLocalidade = faturamentoEstimadoLocalidade;
	}

	public String getFaturamentoEstimadoMotivo() {
		return faturamentoEstimadoMotivo;
	}

	public void setFaturamentoEstimadoMotivo(String faturamentoEstimadoMotivo) {
		this.faturamentoEstimadoMotivo = faturamentoEstimadoMotivo;
	}

	public String getFaturamentoEstimadoSituacao() {
		return faturamentoEstimadoSituacao;
	}

	public void setFaturamentoEstimadoSituacao(String faturamentoEstimadoSituacao) {
		this.faturamentoEstimadoSituacao = faturamentoEstimadoSituacao;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getMesAnoFim() {
		return mesAnoFim;
	}

	public void setMesAnoFim(String mesAnoFim) {
		this.mesAnoFim = mesAnoFim;
	}

	public String getMesAnoInicio() {
		return mesAnoInicio;
	}

	public void setMesAnoInicio(String mesAnoInicio) {
		this.mesAnoInicio = mesAnoInicio;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getPercentualGeral() {
		return percentualGeral;
	}

	public void setPercentualGeral(String percentualGeral) {
		this.percentualGeral = percentualGeral;
	}

	public String getPercentualGerencia() {
		return percentualGerencia;
	}

	public void setPercentualGerencia(String percentualGerencia) {
		this.percentualGerencia = percentualGerencia;
	}

	public String getPercentualLocalidade() {
		return percentualLocalidade;
	}

	public void setPercentualLocalidade(String percentualLocalidade) {
		this.percentualLocalidade = percentualLocalidade;
	}

	public String getPercentualMotivo() {
		return percentualMotivo;
	}

	public void setPercentualMotivo(String percentualMotivo) {
		this.percentualMotivo = percentualMotivo;
	}

	public String getPercentualSituacao() {
		return percentualSituacao;
	}

	public void setPercentualSituacao(String percentualSituacao) {
		this.percentualSituacao = percentualSituacao;
	}

	public String getQtdeImovelGeral() {
		return qtdeImovelGeral;
	}

	public void setQtdeImovelGeral(String qtdeImovelGeral) {
		this.qtdeImovelGeral = qtdeImovelGeral;
	}

	public String getQtdeImovelGerencia() {
		return qtdeImovelGerencia;
	}

	public void setQtdeImovelGerencia(String qtdeImovelGerencia) {
		this.qtdeImovelGerencia = qtdeImovelGerencia;
	}

	public String getQtdeImovelLocalidade() {
		return qtdeImovelLocalidade;
	}

	public void setQtdeImovelLocalidade(String qtdeImovelLocalidade) {
		this.qtdeImovelLocalidade = qtdeImovelLocalidade;
	}

	public String getQtdeImovelMotivo() {
		return qtdeImovelMotivo;
	}

	public void setQtdeImovelMotivo(String qtdeImovelMotivo) {
		this.qtdeImovelMotivo = qtdeImovelMotivo;
	}

	public String getQtdeImovelSituacao() {
		return qtdeImovelSituacao;
	}

	public void setQtdeImovelSituacao(String qtdeImovelSituacao) {
		this.qtdeImovelSituacao = qtdeImovelSituacao;
	}

	public String getQtdeParalisadaGeral() {
		return qtdeParalisadaGeral;
	}

	public void setQtdeParalisadaGeral(String qtdeParalisadaGeral) {
		this.qtdeParalisadaGeral = qtdeParalisadaGeral;
	}

	public String getQtdeParalisadaGerencia() {
		return qtdeParalisadaGerencia;
	}

	public void setQtdeParalisadaGerencia(String qtdeParalisadaGerencia) {
		this.qtdeParalisadaGerencia = qtdeParalisadaGerencia;
	}

	public String getQtdeParalisadaMotivo() {
		return qtdeParalisadaMotivo;
	}

	public void setQtdeParalisadaMotivo(String qtdeParalisadaMotivo) {
		this.qtdeParalisadaMotivo = qtdeParalisadaMotivo;
	}

	public String getQtdeParalisadaSituacao() {
		return qtdeParalisadaSituacao;
	}

	public void setQtdeParalisadaSituacao(String qtdeParalisadaSituacao) {
		this.qtdeParalisadaSituacao = qtdeParalisadaSituacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getQtdeParalisadaLocalidade() {
		return qtdeParalisadaLocalidade;
	}

	public void setQtdeParalisadaLocalidade(String qtdeParalisadaLocalidade) {
		this.qtdeParalisadaLocalidade = qtdeParalisadaLocalidade;
	}

	/**
	 * @return Retorna o campo faturamentoEstimadoSetorComercial.
	 */
	public String getFaturamentoEstimadoSetorComercial() {
		return faturamentoEstimadoSetorComercial;
	}

	/**
	 * @param faturamentoEstimadoSetorComercial O faturamentoEstimadoSetorComercial a ser setado.
	 */
	public void setFaturamentoEstimadoSetorComercial(
			String faturamentoEstimadoSetorComercial) {
		this.faturamentoEstimadoSetorComercial = faturamentoEstimadoSetorComercial;
	}

	/**
	 * @return Retorna o campo faturamentoEstimadoUnidadeNegocio.
	 */
	public String getFaturamentoEstimadoUnidadeNegocio() {
		return faturamentoEstimadoUnidadeNegocio;
	}

	/**
	 * @param faturamentoEstimadoUnidadeNegocio O faturamentoEstimadoUnidadeNegocio a ser setado.
	 */
	public void setFaturamentoEstimadoUnidadeNegocio(
			String faturamentoEstimadoUnidadeNegocio) {
		this.faturamentoEstimadoUnidadeNegocio = faturamentoEstimadoUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo percentualSetorComercial.
	 */
	public String getPercentualSetorComercial() {
		return percentualSetorComercial;
	}

	/**
	 * @param percentualSetorComercial O percentualSetorComercial a ser setado.
	 */
	public void setPercentualSetorComercial(String percentualSetorComercial) {
		this.percentualSetorComercial = percentualSetorComercial;
	}

	/**
	 * @return Retorna o campo percentualUnidadeNegocio.
	 */
	public String getPercentualUnidadeNegocio() {
		return percentualUnidadeNegocio;
	}

	/**
	 * @param percentualUnidadeNegocio O percentualUnidadeNegocio a ser setado.
	 */
	public void setPercentualUnidadeNegocio(String percentualUnidadeNegocio) {
		this.percentualUnidadeNegocio = percentualUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo qtdeImovelSetorComercial.
	 */
	public String getQtdeImovelSetorComercial() {
		return qtdeImovelSetorComercial;
	}

	/**
	 * @param qtdeImovelSetorComercial O qtdeImovelSetorComercial a ser setado.
	 */
	public void setQtdeImovelSetorComercial(String qtdeImovelSetorComercial) {
		this.qtdeImovelSetorComercial = qtdeImovelSetorComercial;
	}

	/**
	 * @return Retorna o campo qtdeImovelUnidadeNegocio.
	 */
	public String getQtdeImovelUnidadeNegocio() {
		return qtdeImovelUnidadeNegocio;
	}

	/**
	 * @param qtdeImovelUnidadeNegocio O qtdeImovelUnidadeNegocio a ser setado.
	 */
	public void setQtdeImovelUnidadeNegocio(String qtdeImovelUnidadeNegocio) {
		this.qtdeImovelUnidadeNegocio = qtdeImovelUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo qtdeParalisadaSetorComercial.
	 */
	public String getQtdeParalisadaSetorComercial() {
		return qtdeParalisadaSetorComercial;
	}

	/**
	 * @param qtdeParalisadaSetorComercial O qtdeParalisadaSetorComercial a ser setado.
	 */
	public void setQtdeParalisadaSetorComercial(String qtdeParalisadaSetorComercial) {
		this.qtdeParalisadaSetorComercial = qtdeParalisadaSetorComercial;
	}

	/**
	 * @return Retorna o campo qtdeParalisadaUnidadeNegocio.
	 */
	public String getQtdeParalisadaUnidadeNegocio() {
		return qtdeParalisadaUnidadeNegocio;
	}

	/**
	 * @param qtdeParalisadaUnidadeNegocio O qtdeParalisadaUnidadeNegocio a ser setado.
	 */
	public void setQtdeParalisadaUnidadeNegocio(String qtdeParalisadaUnidadeNegocio) {
		this.qtdeParalisadaUnidadeNegocio = qtdeParalisadaUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo setorComercial.
	 */
	public String getSetorComercial() {
		return setorComercial;
	}

	/**
	 * @param setorComercial O setorComercial a ser setado.
	 */
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	
}
