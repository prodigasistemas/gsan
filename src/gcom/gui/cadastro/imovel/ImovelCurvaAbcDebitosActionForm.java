package gcom.gui.cadastro.imovel;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Ivan Sérgio
 * @created 17/07/2007
 */
public class ImovelCurvaAbcDebitosActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	// Parametros
	private String classificacao;
	private String referenciaCobrancaInicial;
	private String referenciaCobrancaFinal;
	private String anoMesReferenciaArrecadacaoAtual;
	private String indicadorImovelMedicaoIndividualizada;
	private String indicadorImovelParalizacaoFaturamentoCobranca;
	
	// Localidade
	private String[] gerenciaRegional;
	private String idLocalidadeInicial;
	private String idLocalidadeFinal;
	private String idSetorComercialInicial;
	private String idSetorComercialFinal;
	
	private String inscricaoTipo;
	private String nomeLocalidadeInicial;
	private String nomeLocalidadeFinal;
	
	private String codigoSetorComercialInicial;
	private String nomeSetorComercialInicial;
	private String codigoSetorComercialFinal;
	private String nomeSetorComercialFinal;
	
	private String idMunicipio;
	private String nomeMunicipio;
	
	// Ligacoes Agua/Esgoto
	private String[] situacaoLigacaoAgua;
	private String[] situacaoLigacaoEsgoto;
	private String intervaloMesesCortadoSuprimidoInicial;
	private String intervaloMesesCortadoSuprimidoFinal;
	private String intervaloConsumoMinimoFixadoEsgotoInicial;
	private String intervaloConsumoMinimoFixadoEsgotoFinal;
	private String indicadorMedicao;
	private String idTipoMedicao;
	
	// Caracteristicas
	private String idPerfilImovel;
	private String idTipoCategoria;
	private String[] categoria;
	private	String idSubCategoria;
	
	// Debito
	private String valorMinimoDebito;
	private String intervaloQuantidadeDocumentosInicial;
	private String intervaloQuantidadeDocumentosFinal;
	private String indicadorPagamentosNaoClassificados;
	
	
	public String[] getCategoria() {
		return categoria;
	}
	public void setCategoria(String[] categoria) {
		this.categoria = categoria;
	}
	public String getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	public String[] getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String[] gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}
	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}
	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}
	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}
	public String getIdPerfilImovel() {
		return idPerfilImovel;
	}
	public void setIdPerfilImovel(String idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}
	public String getIdSetorComercialFinal() {
		return idSetorComercialFinal;
	}
	public void setIdSetorComercialFinal(String idSetorComercialFinal) {
		this.idSetorComercialFinal = idSetorComercialFinal;
	}
	public String getIdSetorComercialInicial() {
		return idSetorComercialInicial;
	}
	public void setIdSetorComercialInicial(String idSetorComercialInicial) {
		this.idSetorComercialInicial = idSetorComercialInicial;
	}
	public String getIdSubCategoria() {
		return idSubCategoria;
	}
	public void setIdSubCategoria(String idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}
	public String getIdTipoCategoria() {
		return idTipoCategoria;
	}
	public void setIdTipoCategoria(String idTipoCategoria) {
		this.idTipoCategoria = idTipoCategoria;
	}
	public String getIdTipoMedicao() {
		return idTipoMedicao;
	}
	public void setIdTipoMedicao(String idTipoMedicao) {
		this.idTipoMedicao = idTipoMedicao;
	}
	public String getIndicadorImovelMedicaoIndividualizada() {
		return indicadorImovelMedicaoIndividualizada;
	}
	public void setIndicadorImovelMedicaoIndividualizada(
			String indicadorImovelMedicaoIndividualizada) {
		this.indicadorImovelMedicaoIndividualizada = indicadorImovelMedicaoIndividualizada;
	}
	public String getIndicadorImovelParalizacaoFaturamentoCobranca() {
		return indicadorImovelParalizacaoFaturamentoCobranca;
	}
	public void setIndicadorImovelParalizacaoFaturamentoCobranca(
			String indicadorImovelParalizacaoFaturamentoCobranca) {
		this.indicadorImovelParalizacaoFaturamentoCobranca = indicadorImovelParalizacaoFaturamentoCobranca;
	}
	public String getIndicadorMedicao() {
		return indicadorMedicao;
	}
	public void setIndicadorMedicao(String indicadorMedicao) {
		this.indicadorMedicao = indicadorMedicao;
	}
	public String getIndicadorPagamentosNaoClassificados() {
		return indicadorPagamentosNaoClassificados;
	}
	public void setIndicadorPagamentosNaoClassificados(
			String indicadorPagamentosNaoClassificados) {
		this.indicadorPagamentosNaoClassificados = indicadorPagamentosNaoClassificados;
	}
	public String getIntervaloConsumoMinimoFixadoEsgotoFinal() {
		return intervaloConsumoMinimoFixadoEsgotoFinal;
	}
	public void setIntervaloConsumoMinimoFixadoEsgotoFinal(
			String intervaloConsumoMinimoFixadoEsgotoFinal) {
		this.intervaloConsumoMinimoFixadoEsgotoFinal = intervaloConsumoMinimoFixadoEsgotoFinal;
	}
	public String getIntervaloConsumoMinimoFixadoEsgotoInicial() {
		return intervaloConsumoMinimoFixadoEsgotoInicial;
	}
	public void setIntervaloConsumoMinimoFixadoEsgotoInicial(
			String intervaloConsumoMinimoFixadoEsgotoInicial) {
		this.intervaloConsumoMinimoFixadoEsgotoInicial = intervaloConsumoMinimoFixadoEsgotoInicial;
	}
	public String getIntervaloMesesCortadoSuprimidoFinal() {
		return intervaloMesesCortadoSuprimidoFinal;
	}
	public void setIntervaloMesesCortadoSuprimidoFinal(
			String intervaloMesesCortadoSuprimidoFinal) {
		this.intervaloMesesCortadoSuprimidoFinal = intervaloMesesCortadoSuprimidoFinal;
	}
	public String getIntervaloMesesCortadoSuprimidoInicial() {
		return intervaloMesesCortadoSuprimidoInicial;
	}
	public void setIntervaloMesesCortadoSuprimidoInicial(
			String intervaloMesesCortadoSuprimidoInicial) {
		this.intervaloMesesCortadoSuprimidoInicial = intervaloMesesCortadoSuprimidoInicial;
	}
	public String getIntervaloQuantidadeDocumentosFinal() {
		return intervaloQuantidadeDocumentosFinal;
	}
	public void setIntervaloQuantidadeDocumentosFinal(
			String intervaloQuantidadeDocumentosFinal) {
		this.intervaloQuantidadeDocumentosFinal = intervaloQuantidadeDocumentosFinal;
	}
	public String getIntervaloQuantidadeDocumentosInicial() {
		return intervaloQuantidadeDocumentosInicial;
	}
	public void setIntervaloQuantidadeDocumentosInicial(
			String intervaloQuantidadeDocumentosInicial) {
		this.intervaloQuantidadeDocumentosInicial = intervaloQuantidadeDocumentosInicial;
	}
	public String getReferenciaCobrancaInicial() {
		return referenciaCobrancaInicial;
	}
	public void setReferenciaCobrancaInicial(String referenciaCobrancaInicial) {
		this.referenciaCobrancaInicial = referenciaCobrancaInicial;
	}
	public String getReferenciaCobrancaFinal() {
		return referenciaCobrancaFinal;
	}
	public void setReferenciaCobrancaFinal(String referenciaCobrancaFinal) {
		this.referenciaCobrancaFinal = referenciaCobrancaFinal;
	}
	public String[] getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	public void setSituacaoLigacaoAgua(String[] situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	public String[] getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	public void setSituacaoLigacaoEsgoto(String[] situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
	public String getValorMinimoDebito() {
		return valorMinimoDebito;
	}
	public void setValorMinimoDebito(String valorMinimoDebito) {
		this.valorMinimoDebito = valorMinimoDebito;
	}
	public String getInscricaoTipo() {
		return inscricaoTipo;
	}
	public void setInscricaoTipo(String inscricaoTipo) {
		this.inscricaoTipo = inscricaoTipo;
	}
	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}
	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}
	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}
	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}
	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}
	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}
	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}
	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}
	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}
	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}
	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}
	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}
	public String getAnoMesReferenciaArrecadacaoAtual() {
		return anoMesReferenciaArrecadacaoAtual;
	}
	public void setAnoMesReferenciaArrecadacaoAtual(
			String anoMesReferenciaArrecadacaoAtual) {
		this.anoMesReferenciaArrecadacaoAtual = anoMesReferenciaArrecadacaoAtual;
	}
	
	public String getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
	/**
	 * Classificacao: Caso o usuario selecione a opcao ESTADO inibir todo os
	 * 				  os campos da Aba Localizacao;
	 * 				  Caso o usuario selecione a opcao REGIONAL inibir os
	 * 				  os campos Localidade e Setor Comercial da Aba Localizacao;
	 */
	public void limparCamposPorEstado() {
		this.setGerenciaRegional(null);
		this.setIdLocalidadeInicial(null);
		this.setIdLocalidadeFinal(null);
		this.setIdSetorComercialInicial(null);
		this.setIdSetorComercialFinal(null);
		this.setNomeLocalidadeInicial(null);
		this.setNomeLocalidadeFinal(null);
		this.setNomeSetorComercialInicial(null);
		this.setNomeSetorComercialFinal(null);
		this.setCodigoSetorComercialInicial(null);
		this.setCodigoSetorComercialFinal(null);
		this.setIdMunicipio(null);
		this.setNomeMunicipio(null);
	}
	
	public void limparCamposPorRegional() {
		this.setIdLocalidadeInicial(null);
		this.setIdLocalidadeFinal(null);
		this.setIdSetorComercialInicial(null);
		this.setIdSetorComercialFinal(null);
		this.setNomeLocalidadeInicial(null);
		this.setNomeLocalidadeFinal(null);
		this.setNomeSetorComercialInicial(null);
		this.setNomeSetorComercialFinal(null);
		this.setCodigoSetorComercialInicial(null);
		this.setCodigoSetorComercialFinal(null);
		this.setIdMunicipio(null);
		this.setNomeMunicipio(null);
	}
}

