package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0637] - Gerar Relatórios Volumes Faturados
 * 
 * @author Rafael Corrêa
 * @date 07/03/2007
 */
public class RelatorioContasEmRevisaoBean implements RelatorioBean {

	private String gerenciaRegional;
	
	private String unidadeNegocio;

	private String elo;
	
	private String localidade;

	private String inscricao;

	private String imovel;

	private String usuario;

	private String telefone;

	private String mesAnoFatura;

	private String dataReclamacao;

	private String valorConta;

	private String motivoReclamacao;
	
	private String totaisPorFaixaSetorComercial;
	private String qtdeTotalContasEmRevisaoSetorComercial;
	private String valorTotalContasEmRevisaoSetorComercial;

	private String totaisPorFaixaLocalidade;
	private String qtdeTotalContasEmRevisaoLocalidade;
	private String valorTotalContasEmRevisaoLocalidade;

	private String totaisPorFaixaElo;
	private String qtdeTotalContasEmRevisaoElo;
	private String valorTotalContasEmRevisaoElo;
	
	private String totaisPorFaixaUnidadeNegocio;
	private String qtdeTotalContasEmRevisaoUnidadeNegocio;
	private String valorTotalContasEmRevisaoUnidadeNegocio;
	
	private String totaisPorFaixaGerenciaRegional;
	private String qtdeTotalContasEmRevisaoGerenciaRegional;
	private String valorTotalContasEmRevisaoGerenciaRegional;
	
	private String totaisPorFaixaGeral;
	private String qtdeTotalContasEmRevisaoGeral;
	private String valorTotalContasEmRevisaoGeral;
	
	private String qtdeContas;
	
	private String motivoLocalidade;
	
	private String motivoLocalidadeAnterior;
	
	private String setorComercial;
	
	private String imprimeGerenciaRegional;
	private String imprimeUnidadeNegocio;
	private String imprimeElo;
	private String imprimeLocalidade;
	
	public String getImprimeLocalidade() {
		return imprimeLocalidade;
	}

	public void setImprimeLocalidade(String imprimeLocalidade) {
		this.imprimeLocalidade = imprimeLocalidade;
	}

	public RelatorioContasEmRevisaoBean() { }

	public RelatorioContasEmRevisaoBean(String gerenciaRegional, String unidadeNegocio,
			String localidade, String setorComercial, String inscricao, String imovel, String usuario,
			String telefone, String mesAnoFatura, String dataReclamacao,
			String valorConta, String motivoReclamacao,
			String qtdeTotalContasEmRevisaoLocalidade,
			String valorTotalContasEmRevisaoLocalidade,
			String totaisPorFaixaLocalidade, 
			String qtdeTotalContasEmRevisaoElo,
			String valorTotalContasEmRevisaoElo,
			String totaisPorFaixaElo,
			String qtdeTotalContasEmRevisaoGerenciaRegional,
			String valorTotalContasEmRevisaoGerenciaRegional,
			String totaisPorFaixaGerenciaRegional, 
			String qtdeTotalContasEmRevisaoGeral,
			String valorTotalContasEmRevisaoGeral,
			String totaisPorFaixaGeral,
			String imprimeGerenciaRegional,
			String imprimeUnidadeNegocio) {

		this.gerenciaRegional = gerenciaRegional; 
//		this.elo = elo;
		this.localidade = localidade; 
		this.inscricao = inscricao; 
		this.imovel = imovel; 
		this.usuario = usuario; 
		this.telefone = telefone; 
		this.mesAnoFatura = mesAnoFatura; 
		this.dataReclamacao = dataReclamacao; 
		this.valorConta = valorConta; 
		this.motivoReclamacao = motivoReclamacao; 
		this.qtdeTotalContasEmRevisaoLocalidade = qtdeTotalContasEmRevisaoLocalidade; 
		this.valorTotalContasEmRevisaoLocalidade = valorTotalContasEmRevisaoLocalidade;
		this.totaisPorFaixaLocalidade = totaisPorFaixaLocalidade;
		this.qtdeTotalContasEmRevisaoElo = qtdeTotalContasEmRevisaoElo;
		this.valorTotalContasEmRevisaoElo = valorTotalContasEmRevisaoElo;
		this.totaisPorFaixaElo = totaisPorFaixaElo;
		this.qtdeTotalContasEmRevisaoGerenciaRegional = qtdeTotalContasEmRevisaoGerenciaRegional; 
		this.valorTotalContasEmRevisaoGerenciaRegional = valorTotalContasEmRevisaoGerenciaRegional;
		this.totaisPorFaixaGerenciaRegional = totaisPorFaixaGerenciaRegional;
		this.qtdeTotalContasEmRevisaoGeral = qtdeTotalContasEmRevisaoGeral; 
		this.valorTotalContasEmRevisaoGeral = valorTotalContasEmRevisaoGeral;
		this.totaisPorFaixaGeral = totaisPorFaixaGeral;
//		this.imprimeElo = imprimeElo;
		this.imprimeGerenciaRegional = imprimeGerenciaRegional;
		this.imprimeUnidadeNegocio = imprimeUnidadeNegocio;

	}
	
	public RelatorioContasEmRevisaoBean(String gerenciaRegional, String elo,
			String localidade, String mesAnoFatura, String qtdeContas,
			String valorConta, String motivoReclamacao,
			String qtdeTotalContasEmRevisaoLocalidade,
			String valorTotalContasEmRevisaoLocalidade,
			String qtdeTotalContasEmRevisaoElo,
			String valorTotalContasEmRevisaoElo,
			String qtdeTotalContasEmRevisaoGerenciaRegional,
			String valorTotalContasEmRevisaoGerenciaRegional,
			String imprimeElo, String imprimeGerenciaRegional,
			String motivoLocalidadeAnterior, String motivoLocalidade) {

		this.gerenciaRegional = gerenciaRegional;
		this.elo = elo;
		this.localidade = localidade;
		this.mesAnoFatura = mesAnoFatura;
		this.qtdeContas = qtdeContas;
		this.valorConta = valorConta;
		this.motivoReclamacao = motivoReclamacao;
		this.qtdeTotalContasEmRevisaoLocalidade = qtdeTotalContasEmRevisaoLocalidade;
		this.valorTotalContasEmRevisaoLocalidade = valorTotalContasEmRevisaoLocalidade;
		this.qtdeTotalContasEmRevisaoElo = qtdeTotalContasEmRevisaoElo;
		this.valorTotalContasEmRevisaoElo = valorTotalContasEmRevisaoElo;
		this.qtdeTotalContasEmRevisaoGerenciaRegional = qtdeTotalContasEmRevisaoGerenciaRegional; 
		this.valorTotalContasEmRevisaoGerenciaRegional = valorTotalContasEmRevisaoGerenciaRegional;
		this.imprimeElo = imprimeElo;
		this.imprimeGerenciaRegional = imprimeGerenciaRegional;
		this.motivoLocalidadeAnterior = motivoLocalidadeAnterior;
		this.motivoLocalidade = motivoLocalidade;
	}

	/**
	 * @return Retorna o campo dataReclamacao.
	 */
	public String getDataReclamacao() {
		return dataReclamacao;
	}

	/**
	 * @param dataReclamacao O dataReclamacao a ser setado.
	 */
	public void setDataReclamacao(String dataReclamacao) {
		this.dataReclamacao = dataReclamacao;
	}

	/**
	 * @return Retorna o campo elo.
	 */
	public String getElo() {
		return elo;
	}

	/**
	 * @param elo O elo a ser setado.
	 */
	public void setElo(String elo) {
		this.elo = elo;
	}

	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	/**
	 * @return Retorna o campo imovel.
	 */
	public String getImovel() {
		return imovel;
	}

	/**
	 * @param imovel O imovel a ser setado.
	 */
	public void setImovel(String imovel) {
		this.imovel = imovel;
	}

	/**
	 * @return Retorna o campo inscricao.
	 */
	public String getInscricao() {
		return inscricao;
	}

	/**
	 * @param inscricao O inscricao a ser setado.
	 */
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
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
	 * @return Retorna o campo mesAnoFatura.
	 */
	public String getMesAnoFatura() {
		return mesAnoFatura;
	}

	/**
	 * @param mesAnoFatura O mesAnoFatura a ser setado.
	 */
	public void setMesAnoFatura(String mesAnoFatura) {
		this.mesAnoFatura = mesAnoFatura;
	}

	/**
	 * @return Retorna o campo motivoReclamacao.
	 */
	public String getMotivoReclamacao() {
		return motivoReclamacao;
	}

	/**
	 * @param motivoReclamacao O motivoReclamacao a ser setado.
	 */
	public void setMotivoReclamacao(String motivoReclamacao) {
		this.motivoReclamacao = motivoReclamacao;
	}

	/**
	 * @return Retorna o campo qtdeTotalContasEmRevisaoElo.
	 */
	public String getQtdeTotalContasEmRevisaoElo() {
		return qtdeTotalContasEmRevisaoElo;
	}

	/**
	 * @param qtdeTotalContasEmRevisaoElo O qtdeTotalContasEmRevisaoElo a ser setado.
	 */
	public void setQtdeTotalContasEmRevisaoElo(String qtdeTotalContasEmRevisaoElo) {
		this.qtdeTotalContasEmRevisaoElo = qtdeTotalContasEmRevisaoElo;
	}

	/**
	 * @return Retorna o campo qtdeTotalContasEmRevisaoGeral.
	 */
	public String getQtdeTotalContasEmRevisaoGeral() {
		return qtdeTotalContasEmRevisaoGeral;
	}

	/**
	 * @param qtdeTotalContasEmRevisaoGeral O qtdeTotalContasEmRevisaoGeral a ser setado.
	 */
	public void setQtdeTotalContasEmRevisaoGeral(
			String qtdeTotalContasEmRevisaoGeral) {
		this.qtdeTotalContasEmRevisaoGeral = qtdeTotalContasEmRevisaoGeral;
	}

	/**
	 * @return Retorna o campo qtdeTotalContasEmRevisaoGerenciaRegional.
	 */
	public String getQtdeTotalContasEmRevisaoGerenciaRegional() {
		return qtdeTotalContasEmRevisaoGerenciaRegional;
	}

	/**
	 * @param qtdeTotalContasEmRevisaoGerenciaRegional O qtdeTotalContasEmRevisaoGerenciaRegional a ser setado.
	 */
	public void setQtdeTotalContasEmRevisaoGerenciaRegional(
			String qtdeTotalContasEmRevisaoGerenciaRegional) {
		this.qtdeTotalContasEmRevisaoGerenciaRegional = qtdeTotalContasEmRevisaoGerenciaRegional;
	}

	/**
	 * @return Retorna o campo qtdeTotalContasEmRevisaoLocalidade.
	 */
	public String getQtdeTotalContasEmRevisaoLocalidade() {
		return qtdeTotalContasEmRevisaoLocalidade;
	}

	/**
	 * @param qtdeTotalContasEmRevisaoLocalidade O qtdeTotalContasEmRevisaoLocalidade a ser setado.
	 */
	public void setQtdeTotalContasEmRevisaoLocalidade(
			String qtdeTotalContasEmRevisaoLocalidade) {
		this.qtdeTotalContasEmRevisaoLocalidade = qtdeTotalContasEmRevisaoLocalidade;
	}

	/**
	 * @return Retorna o campo telefone.
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone O telefone a ser setado.
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return Retorna o campo usuario.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return Retorna o campo valorConta.
	 */
	public String getValorConta() {
		return valorConta;
	}

	/**
	 * @param valorConta O valorConta a ser setado.
	 */
	public void setValorConta(String valorConta) {
		this.valorConta = valorConta;
	}

	/**
	 * @return Retorna o campo valorTotalContasEmRevisaoElo.
	 */
	public String getValorTotalContasEmRevisaoElo() {
		return valorTotalContasEmRevisaoElo;
	}

	/**
	 * @param valorTotalContasEmRevisaoElo O valorTotalContasEmRevisaoElo a ser setado.
	 */
	public void setValorTotalContasEmRevisaoElo(String valorTotalContasEmRevisaoElo) {
		this.valorTotalContasEmRevisaoElo = valorTotalContasEmRevisaoElo;
	}

	/**
	 * @return Retorna o campo valorTotalContasEmRevisaoGeral.
	 */
	public String getValorTotalContasEmRevisaoGeral() {
		return valorTotalContasEmRevisaoGeral;
	}

	/**
	 * @param valorTotalContasEmRevisaoGeral O valorTotalContasEmRevisaoGeral a ser setado.
	 */
	public void setValorTotalContasEmRevisaoGeral(
			String valorTotalContasEmRevisaoGeral) {
		this.valorTotalContasEmRevisaoGeral = valorTotalContasEmRevisaoGeral;
	}

	/**
	 * @return Retorna o campo valorTotalContasEmRevisaoGerenciaRegional.
	 */
	public String getValorTotalContasEmRevisaoGerenciaRegional() {
		return valorTotalContasEmRevisaoGerenciaRegional;
	}

	/**
	 * @param valorTotalContasEmRevisaoGerenciaRegional O valorTotalContasEmRevisaoGerenciaRegional a ser setado.
	 */
	public void setValorTotalContasEmRevisaoGerenciaRegional(
			String valorTotalContasEmRevisaoGerenciaRegional) {
		this.valorTotalContasEmRevisaoGerenciaRegional = valorTotalContasEmRevisaoGerenciaRegional;
	}

	/**
	 * @return Retorna o campo valorTotalContasEmRevisaoLocalidade.
	 */
	public String getValorTotalContasEmRevisaoLocalidade() {
		return valorTotalContasEmRevisaoLocalidade;
	}

	/**
	 * @param valorTotalContasEmRevisaoLocalidade O valorTotalContasEmRevisaoLocalidade a ser setado.
	 */
	public void setValorTotalContasEmRevisaoLocalidade(
			String valorTotalContasEmRevisaoLocalidade) {
		this.valorTotalContasEmRevisaoLocalidade = valorTotalContasEmRevisaoLocalidade;
	}
	
	/**
	 * @return Retorna o campo totaisPorFaixaElo.
	 */
	public String getTotaisPorFaixaElo() {
		return totaisPorFaixaElo;
	}

	/**
	 * @param totaisPorFaixaElo O totaisPorFaixaElo a ser setado.
	 */
	public void setTotaisPorFaixaElo(String totaisPorFaixaElo) {
		this.totaisPorFaixaElo = totaisPorFaixaElo;
	}

	/**
	 * @return Retorna o campo totaisPorFaixaGeral.
	 */
	public String getTotaisPorFaixaGeral() {
		return totaisPorFaixaGeral;
	}

	/**
	 * @param totaisPorFaixaGeral O totaisPorFaixaGeral a ser setado.
	 */
	public void setTotaisPorFaixaGeral(String totaisPorFaixaGeral) {
		this.totaisPorFaixaGeral = totaisPorFaixaGeral;
	}

	/**
	 * @return Retorna o campo totaisPorFaixaGerenciaRegional.
	 */
	public String getTotaisPorFaixaGerenciaRegional() {
		return totaisPorFaixaGerenciaRegional;
	}

	/**
	 * @param totaisPorFaixaGerenciaRegional O totaisPorFaixaGerenciaRegional a ser setado.
	 */
	public void setTotaisPorFaixaGerenciaRegional(
			String totaisPorFaixaGerenciaRegional) {
		this.totaisPorFaixaGerenciaRegional = totaisPorFaixaGerenciaRegional;
	}

	/**
	 * @return Retorna o campo totaisPorFaixaLocalidade.
	 */
	public String getTotaisPorFaixaLocalidade() {
		return totaisPorFaixaLocalidade;
	}

	/**
	 * @param totaisPorFaixaLocalidade O totaisPorFaixaLocalidade a ser setado.
	 */
	public void setTotaisPorFaixaLocalidade(String totaisPorFaixaLocalidade) {
		this.totaisPorFaixaLocalidade = totaisPorFaixaLocalidade;
	}

	/**
	 * @return Retorna o campo imprimeGerenciaRegional.
	 */
	public String getImprimeGerenciaRegional() {
		return imprimeGerenciaRegional;
	}

	/**
	 * @param imprimeGerenciaRegional O imprimeGerenciaRegional a ser setado.
	 */
	public void setImprimeGerenciaRegional(String imprimeGerenciaRegional) {
		this.imprimeGerenciaRegional = imprimeGerenciaRegional;
	}

	/**
	 * @return Retorna o campo qtdeContas.
	 */
	public String getQtdeContas() {
		return qtdeContas;
	}

	/**
	 * @param qtdeContas O qtdeContas a ser setado.
	 */
	public void setQtdeContas(String qtdeContas) {
		this.qtdeContas = qtdeContas;
	}

	/**
	 * @return Retorna o campo motivoLocalidade.
	 */
	public String getMotivoLocalidade() {
		return motivoLocalidade;
	}

	/**
	 * @param motivoLocalidade O motivoLocalidade a ser setado.
	 */
	public void setMotivoLocalidade(String motivoLocalidade) {
		this.motivoLocalidade = motivoLocalidade;
	}

	/**
	 * @return Retorna o campo motivoLocalidadeAnterior.
	 */
	public String getMotivoLocalidadeAnterior() {
		return motivoLocalidadeAnterior;
	}

	/**
	 * @param motivoLocalidadeAnterior O motivoLocalidadeAnterior a ser setado.
	 */
	public void setMotivoLocalidadeAnterior(String motivoLocalidadeAnterior) {
		this.motivoLocalidadeAnterior = motivoLocalidadeAnterior;
	}
	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getImprimeElo() {
		return imprimeElo;
	}

	public void setImprimeElo(String imprimeElo) {
		this.imprimeElo = imprimeElo;
	}

	public String getImprimeUnidadeNegocio() {
		return imprimeUnidadeNegocio;
	}

	public void setImprimeUnidadeNegocio(String imprimeUnidadeNegocio) {
		this.imprimeUnidadeNegocio = imprimeUnidadeNegocio;
	}

	public String getQtdeTotalContasEmRevisaoSetorComercial() {
		return qtdeTotalContasEmRevisaoSetorComercial;
	}

	public void setQtdeTotalContasEmRevisaoSetorComercial(
			String qtdeTotalContasEmRevisaoSetorComercial) {
		this.qtdeTotalContasEmRevisaoSetorComercial = qtdeTotalContasEmRevisaoSetorComercial;
	}

	public String getQtdeTotalContasEmRevisaoUnidadeNegocio() {
		return qtdeTotalContasEmRevisaoUnidadeNegocio;
	}

	public void setQtdeTotalContasEmRevisaoUnidadeNegocio(
			String qtdeTotalContasEmRevisaoUnidadeNegocio) {
		this.qtdeTotalContasEmRevisaoUnidadeNegocio = qtdeTotalContasEmRevisaoUnidadeNegocio;
	}

	public String getTotaisPorFaixaSetorComercial() {
		return totaisPorFaixaSetorComercial;
	}

	public void setTotaisPorFaixaSetorComercial(String totaisPorFaixaSetorComercial) {
		this.totaisPorFaixaSetorComercial = totaisPorFaixaSetorComercial;
	}

	public String getTotaisPorFaixaUnidadeNegocio() {
		return totaisPorFaixaUnidadeNegocio;
	}

	public void setTotaisPorFaixaUnidadeNegocio(String totaisPorFaixaUnidadeNegocio) {
		this.totaisPorFaixaUnidadeNegocio = totaisPorFaixaUnidadeNegocio;
	}

	public String getValorTotalContasEmRevisaoSetorComercial() {
		return valorTotalContasEmRevisaoSetorComercial;
	}

	public void setValorTotalContasEmRevisaoSetorComercial(
			String valorTotalContasEmRevisaoSetorComercial) {
		this.valorTotalContasEmRevisaoSetorComercial = valorTotalContasEmRevisaoSetorComercial;
	}

	public String getValorTotalContasEmRevisaoUnidadeNegocio() {
		return valorTotalContasEmRevisaoUnidadeNegocio;
	}

	public void setValorTotalContasEmRevisaoUnidadeNegocio(
			String valorTotalContasEmRevisaoUnidadeNegocio) {
		this.valorTotalContasEmRevisaoUnidadeNegocio = valorTotalContasEmRevisaoUnidadeNegocio;
	}

}
