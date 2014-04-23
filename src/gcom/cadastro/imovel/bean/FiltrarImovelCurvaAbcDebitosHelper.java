package gcom.cadastro.imovel.bean;

import java.util.ArrayList;
import java.util.Collection;

public class FiltrarImovelCurvaAbcDebitosHelper {
	
	// Parametros
	private String classificacao;
	private Integer referenciaCobrancaInicial;
	private Integer referenciaCobrancaFinal;
	private Integer AnoMesReferenciaFaturamentoAtual;
	private Short indicadorImovelMedicaoIndividualizada;
	private Short indicadorImovelParalizacaoFaturamentoCobranca;
	
	// Localidade
	private String[] gerenciaRegional;
	private Collection colecaoGerenciaRegional;
	
	private Integer idLocalidadeInicial;
	private Integer idLocalidadeFinal;
	private Integer	idSetorComercialInicial;
	private Integer	idSetorComercialFinal;
	
	// Ligacoes Agua/Esgoto
	private String[] situacaoLigacaoAgua;
	private Collection colecaoSituacaoLigacaoAgua;
	
	private String[] situacaoLigacaoEsgoto;
	private Collection colecaoSituacaoLigacaoEsgoto;	
	
	private Integer intervaloMesesCortadoSuprimidoInicial;
	private Integer intervaloMesesCortadoSuprimidoFinal;
	private Integer intervaloConsumoMinimoFixadoEsgotoInicial;
	private Integer intervaloConsumoMinimoFixadoEsgotoFinal;
	private Short indicadorMedicao;
	private Integer idTipoMedicao;
	
	// Caracteristicas
	private Integer idPerfilImovel;
	private Integer idTipoCategoria;
	
	private String[] categoria;
	private Collection colecaoCategoria;
	
	private	Integer	idSubCategoria;
	
	// Debito
	private Integer valorMinimoDebito;
	private Integer intervaloQuantidadeDocumentosInicial;
	private Integer intervaloQuantidadeDocumentosFinal;
	private Short indicadorPagamentosNaoClassificados;
	
	
	public String getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	
	public Collection getColecaoCategoria() {
		if(this.categoria != null && !this.categoria.equals("")){
			colecaoCategoria = new ArrayList();
			for (int i = 0; i < this.categoria.length; i++) {
				int idCategoria = Integer.parseInt(this.categoria[i]);
				colecaoCategoria.add(idCategoria);
			}
		}
		return colecaoCategoria;
	}

	public void setColecaoCategoria(Collection colecaoCategoria) {
		this.colecaoCategoria = colecaoCategoria;
	}
	
	public Collection getColecaoGerenciaRegional() {
		if(this.gerenciaRegional != null && !this.gerenciaRegional.equals("")){
			colecaoGerenciaRegional = new ArrayList();
			for (int i = 0; i < this.gerenciaRegional.length; i++) {
				int idGerenciaRegional = Integer.parseInt(this.gerenciaRegional[i]);
				colecaoGerenciaRegional.add(idGerenciaRegional);
			}
		}
		return colecaoGerenciaRegional;
	}

	public void setColecaoGerenciaRegional(Collection colecaoGerenciaRegional) {
		this.colecaoGerenciaRegional = colecaoGerenciaRegional;
	}
	
	public Integer getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}
	public void setIdLocalidadeFinal(Integer idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}
	public Integer getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}
	public void setIdLocalidadeInicial(Integer idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}
	public Integer getIdPerfilImovel() {
		return idPerfilImovel;
	}
	public void setIdPerfilImovel(Integer idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}
	public Integer getIdSetorComercialFinal() {
		return idSetorComercialFinal;
	}
	public void setIdSetorComercialFinal(Integer idSetorComercialFinal) {
		this.idSetorComercialFinal = idSetorComercialFinal;
	}
	public Integer getIdSetorComercialInicial() {
		return idSetorComercialInicial;
	}
	public void setIdSetorComercialInicial(Integer idSetorComercialInicial) {
		this.idSetorComercialInicial = idSetorComercialInicial;
	}
	
	public Collection getColecaoSituacaoLigacaoAgua() {
		if(this.situacaoLigacaoAgua != null && !this.situacaoLigacaoAgua.equals("")){
			colecaoSituacaoLigacaoAgua = new ArrayList();
			for (int i = 0; i < this.situacaoLigacaoAgua.length; i++) {
				int idSituacaoLigacaoAgua = Integer.parseInt(this.situacaoLigacaoAgua[i]);
				colecaoSituacaoLigacaoAgua.add(idSituacaoLigacaoAgua);
			}
		}
		return colecaoSituacaoLigacaoAgua;
	}

	public void setColecaoSituacaoLigacaoAgua(Collection colecaoSituacaoLigacaoAgua) {
		this.colecaoSituacaoLigacaoAgua = colecaoSituacaoLigacaoAgua;
	}
	
	public Collection getColecaoSituacaoLigacaoEsgoto() {
		if(this.situacaoLigacaoEsgoto != null && !this.situacaoLigacaoEsgoto.equals("")){
			colecaoSituacaoLigacaoEsgoto = new ArrayList();
			for (int i = 0; i < this.situacaoLigacaoEsgoto.length; i++) {
				int idSituacaoLigacaoEsgoto = Integer.parseInt(this.situacaoLigacaoEsgoto[i]);
				colecaoSituacaoLigacaoEsgoto.add(idSituacaoLigacaoEsgoto);
			}
		}
		return colecaoSituacaoLigacaoEsgoto;
	}

	public void setColecaoSituacaoLigacaoEsgoto(Collection colecaoSituacaoLigacaoEsgoto) {
		this.colecaoSituacaoLigacaoEsgoto = colecaoSituacaoLigacaoEsgoto;
	}
	
	public Integer getIdSubCategoria() {
		return idSubCategoria;
	}
	public void setIdSubCategoria(Integer idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}
	public Integer getIdTipoCategoria() {
		return idTipoCategoria;
	}
	public void setIdTipoCategoria(Integer idTipoCategoria) {
		this.idTipoCategoria = idTipoCategoria;
	}
	public Integer getIdTipoMedicao() {
		return idTipoMedicao;
	}
	public void setIdTipoMedicao(Integer idTipoMedicao) {
		this.idTipoMedicao = idTipoMedicao;
	}
	public Short getIndicadorImovelMedicaoIndividualizada() {
		return indicadorImovelMedicaoIndividualizada;
	}
	public void setIndicadorImovelMedicaoIndividualizada(
			Short indicadorImovelMedicaoIndividualizada) {
		this.indicadorImovelMedicaoIndividualizada = indicadorImovelMedicaoIndividualizada;
	}
	public Short getIndicadorImovelParalizacaoFaturamentoCobranca() {
		return indicadorImovelParalizacaoFaturamentoCobranca;
	}
	public void setIndicadorImovelParalizacaoFaturamentoCobranca(
			Short indicadorImovelParalizacaoFaturamentoCobranca) {
		this.indicadorImovelParalizacaoFaturamentoCobranca = indicadorImovelParalizacaoFaturamentoCobranca;
	}
	public Short getIndicadorMedicao() {
		return indicadorMedicao;
	}
	public void setIndicadorMedicao(Short indicadorMedicao) {
		this.indicadorMedicao = indicadorMedicao;
	}
	public Short getIndicadorPagamentosNaoClassificados() {
		return indicadorPagamentosNaoClassificados;
	}
	public void setIndicadorPagamentosNaoClassificados(
			Short indicadorPagamentosNaoClassificados) {
		this.indicadorPagamentosNaoClassificados = indicadorPagamentosNaoClassificados;
	}
	public Integer getIntervaloConsumoMinimoFixadoEsgotoFinal() {
		return intervaloConsumoMinimoFixadoEsgotoFinal;
	}
	public void setIntervaloConsumoMinimoFixadoEsgotoFinal(
			Integer intervaloConsumoMinimoFixadoEsgotoFinal) {
		this.intervaloConsumoMinimoFixadoEsgotoFinal = intervaloConsumoMinimoFixadoEsgotoFinal;
	}
	public Integer getIntervaloConsumoMinimoFixadoEsgotoInicial() {
		return intervaloConsumoMinimoFixadoEsgotoInicial;
	}
	public void setIntervaloConsumoMinimoFixadoEsgotoInicial(
			Integer intervaloConsumoMinimoFixadoEsgotoInicial) {
		this.intervaloConsumoMinimoFixadoEsgotoInicial = intervaloConsumoMinimoFixadoEsgotoInicial;
	}
	public Integer getIntervaloMesesCortadoSuprimidoFinal() {
		return intervaloMesesCortadoSuprimidoFinal;
	}
	public void setIntervaloMesesCortadoSuprimidoFinal(
			Integer intervaloMesesCortadoSuprimidoFinal) {
		this.intervaloMesesCortadoSuprimidoFinal = intervaloMesesCortadoSuprimidoFinal;
	}
	public Integer getIntervaloMesesCortadoSuprimidoInicial() {
		return intervaloMesesCortadoSuprimidoInicial;
	}
	public void setIntervaloMesesCortadoSuprimidoInicial(
			Integer intervaloMesesCortadoSuprimidoInicial) {
		this.intervaloMesesCortadoSuprimidoInicial = intervaloMesesCortadoSuprimidoInicial;
	}
	public Integer getIntervaloQuantidadeDocumentosFinal() {
		return intervaloQuantidadeDocumentosFinal;
	}
	public void setIntervaloQuantidadeDocumentosFinal(
			Integer intervaloQuantidadeDocumentosFinal) {
		this.intervaloQuantidadeDocumentosFinal = intervaloQuantidadeDocumentosFinal;
	}
	public Integer getIntervaloQuantidadeDocumentosInicial() {
		return intervaloQuantidadeDocumentosInicial;
	}
	public void setIntervaloQuantidadeDocumentosInicial(
			Integer intervaloQuantidadeDocumentosInicial) {
		this.intervaloQuantidadeDocumentosInicial = intervaloQuantidadeDocumentosInicial;
	}
	public Integer getValorMinimoDebito() {
		return valorMinimoDebito;
	}
	public void setValorMinimoDebito(Integer valorMinimoDebito) {
		this.valorMinimoDebito = valorMinimoDebito;
	}
	public String[] getCategoria() {
		return categoria;
	}
	public void setCategoria(String[] categoria) {
		this.categoria = categoria;
	}
	public String[] getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String[] gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
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
	public Integer getAnoMesReferenciaFaturamentoAtual() {
		return AnoMesReferenciaFaturamentoAtual;
	}
	public void setAnoMesReferenciaFaturamentoAtual(
			Integer anoMesReferenciaFaturamentoAtual) {
		AnoMesReferenciaFaturamentoAtual = anoMesReferenciaFaturamentoAtual;
	}
	public Integer getReferenciaCobrancaFinal() {
		return referenciaCobrancaFinal;
	}
	public void setReferenciaCobrancaFinal(Integer referenciaCobrancaFinal) {
		this.referenciaCobrancaFinal = referenciaCobrancaFinal;
	}
	public Integer getReferenciaCobrancaInicial() {
		return referenciaCobrancaInicial;
	}
	public void setReferenciaCobrancaInicial(Integer referenciaCobrancaInicial) {
		this.referenciaCobrancaInicial = referenciaCobrancaInicial;
	}

}
