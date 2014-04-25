package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form responsável pelas propiedades do caso de uso de inserir os
 * critérios da cobrança.
 * 
 * @author Sávio Luiz
 * @date 29/05/2006
 */
public class CriterioCobrancaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String descricaoCriterio;

	private String dataInicioVigencia;

	private String numeroAnoContaAntiga;

	private String opcaoAcaoImovelSitEspecial;

	private String opcaoAcaoImovelSit;

	private String opcaoContasRevisao;

	private String opcaoAcaoImovelDebitoMesConta;

	private String opcaoAcaoInquilinoDebitoMesConta;

	private String opcaoAcaoImovelDebitoContasAntigas;

	private String[] parImovelPerfil;

	private String[] parCategoria;

	private String valorDebitoMinimo;

	private String valorDebitoMaximo;

	private String qtdContasMinima;

	private String qtdContasMaxima;

	private String vlMinimoDebitoCliente;

	private String qtdMinContasCliente;

	private String vlMinimoContasMes;
	
	private String indicadorUso;
	
	private String descricaoImovelPerfil;
	
	private String descricaoCategoria;
	
	private String percentualValorMinimoPagoParceladoCancelado;
	
	private String percentualQuantidadeMinimoPagoParceladoCancelado;
	
	private String valorLimitePrioridade;
	
	private String quantidadeMinimaParcelasAtraso;
	
	private String[] idsCobrancaSituacao;
	
	private String[] idsSituacaoLigacaoAgua;
	
	private String[] idsSituacaoLigacaoEsgoto;

	public String[] getIdsCobrancaSituacao() {
		return idsCobrancaSituacao;
	}

	public void setIdsCobrancaSituacao(String[] idsCobrancaSituacao) {
		this.idsCobrancaSituacao = idsCobrancaSituacao;
	}

	public String[] getIdsSituacaoLigacaoAgua() {
		return idsSituacaoLigacaoAgua;
	}

	public void setIdsSituacaoLigacaoAgua(String[] idsSituacaoLigacaoAgua) {
		this.idsSituacaoLigacaoAgua = idsSituacaoLigacaoAgua;
	}

	public String[] getIdsSituacaoLigacaoEsgoto() {
		return idsSituacaoLigacaoEsgoto;
	}

	public void setIdsSituacaoLigacaoEsgoto(String[] idsSituacaoLigacaoEsgoto) {
		this.idsSituacaoLigacaoEsgoto = idsSituacaoLigacaoEsgoto;
	}

	public String getDataInicioVigencia() {
		return dataInicioVigencia;
	}

	public void setDataInicioVigencia(String dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}

	public String getDescricaoCriterio() {
		return descricaoCriterio;
	}

	public void setDescricaoCriterio(String descricaoCriterio) {
		this.descricaoCriterio = descricaoCriterio;
	}

	public String getNumeroAnoContaAntiga() {
		return numeroAnoContaAntiga;
	}

	public void setNumeroAnoContaAntiga(String numeroAnoContaAntiga) {
		this.numeroAnoContaAntiga = numeroAnoContaAntiga;
	}

	public String getOpcaoAcaoImovelDebitoMesConta() {
		return opcaoAcaoImovelDebitoMesConta;
	}

	public void setOpcaoAcaoImovelDebitoMesConta(
			String opcaoAcaoImovelDebitoMesConta) {
		this.opcaoAcaoImovelDebitoMesConta = opcaoAcaoImovelDebitoMesConta;
	}

	public String getOpcaoAcaoImovelSit() {
		return opcaoAcaoImovelSit;
	}

	public void setOpcaoAcaoImovelSit(String opcaoAcaoImovelSit) {
		this.opcaoAcaoImovelSit = opcaoAcaoImovelSit;
	}

	public String getOpcaoAcaoImovelSitEspecial() {
		return opcaoAcaoImovelSitEspecial;
	}

	public void setOpcaoAcaoImovelSitEspecial(String opcaoAcaoImovelSitEspecial) {
		this.opcaoAcaoImovelSitEspecial = opcaoAcaoImovelSitEspecial;
	}

	public String getOpcaoAcaoInquilinoDebitoMesConta() {
		return opcaoAcaoInquilinoDebitoMesConta;
	}

	public void setOpcaoAcaoInquilinoDebitoMesConta(
			String opcaoAcaoInquilinoDebitoMesConta) {
		this.opcaoAcaoInquilinoDebitoMesConta = opcaoAcaoInquilinoDebitoMesConta;
	}

	public String getOpcaoContasRevisao() {
		return opcaoContasRevisao;
	}

	public void setOpcaoContasRevisao(String opcaoContasRevisao) {
		this.opcaoContasRevisao = opcaoContasRevisao;
	}

	public String getQtdContasMaxima() {
		return qtdContasMaxima;
	}

	public void setQtdContasMaxima(String qtdContasMaxima) {
		this.qtdContasMaxima = qtdContasMaxima;
	}

	public String getQtdContasMinima() {
		return qtdContasMinima;
	}

	public void setQtdContasMinima(String qtdContasMinima) {
		this.qtdContasMinima = qtdContasMinima;
	}

	public String getQtdMinContasCliente() {
		return qtdMinContasCliente;
	}

	public void setQtdMinContasCliente(String qtdMinContasCliente) {
		this.qtdMinContasCliente = qtdMinContasCliente;
	}

	public String getValorDebitoMaximo() {
		return valorDebitoMaximo;
	}

	public void setValorDebitoMaximo(String valorDebitoMaximo) {
		this.valorDebitoMaximo = valorDebitoMaximo;
	}

	public String getValorDebitoMinimo() {
		return valorDebitoMinimo;
	}

	public void setValorDebitoMinimo(String valorDebitoMinimo) {
		this.valorDebitoMinimo = valorDebitoMinimo;
	}

	public String getVlMinimoContasMes() {
		return vlMinimoContasMes;
	}

	public void setVlMinimoContasMes(String vlMinimoContasMes) {
		this.vlMinimoContasMes = vlMinimoContasMes;
	}

	public String getVlMinimoDebitoCliente() {
		return vlMinimoDebitoCliente;
	}

	public void setVlMinimoDebitoCliente(String vlMinimoDebitoCliente) {
		this.vlMinimoDebitoCliente = vlMinimoDebitoCliente;
	}

	public String getOpcaoAcaoImovelDebitoContasAntigas() {
		return opcaoAcaoImovelDebitoContasAntigas;
	}

	public void setOpcaoAcaoImovelDebitoContasAntigas(
			String opcaoAcaoImovelDebitoContasAntigas) {
		this.opcaoAcaoImovelDebitoContasAntigas = opcaoAcaoImovelDebitoContasAntigas;
	}

	public String[] getParCategoria() {
		return parCategoria;
	}

	public void setParCategoria(String[] parCategoria) {
		this.parCategoria = parCategoria;
	}

	public String[] getParImovelPerfil() {
		return parImovelPerfil;
	}

	public void setParImovelPerfil(String[] parImovelPerfil) {
		this.parImovelPerfil = parImovelPerfil;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public String getDescricaoImovelPerfil() {
		return descricaoImovelPerfil;
	}

	public void setDescricaoImovelPerfil(String descricaoImovelPerfil) {
		this.descricaoImovelPerfil = descricaoImovelPerfil;
	}

	public String getPercentualQuantidadeMinimoPagoParceladoCancelado() {
		return percentualQuantidadeMinimoPagoParceladoCancelado;
	}

	public void setPercentualQuantidadeMinimoPagoParceladoCancelado(
			String percentualQuantidadeMinimoPagoParceladoCancelado) {
		this.percentualQuantidadeMinimoPagoParceladoCancelado = percentualQuantidadeMinimoPagoParceladoCancelado;
	}

	public String getPercentualValorMinimoPagoParceladoCancelado() {
		return percentualValorMinimoPagoParceladoCancelado;
	}

	public void setPercentualValorMinimoPagoParceladoCancelado(
			String percentualValorMinimoPagoParceladoCancelado) {
		this.percentualValorMinimoPagoParceladoCancelado = percentualValorMinimoPagoParceladoCancelado;
	}

	public String getValorLimitePrioridade() {
		return valorLimitePrioridade;
	}

	public void setValorLimitePrioridade(String valorLimitePrioridade) {
		this.valorLimitePrioridade = valorLimitePrioridade;
	}

	public String getQuantidadeMinimaParcelasAtraso() {
		return quantidadeMinimaParcelasAtraso;
	}

	public void setQuantidadeMinimaParcelasAtraso(
			String quantidadeMinimaParcelasAtraso) {
		this.quantidadeMinimaParcelasAtraso = quantidadeMinimaParcelasAtraso;
	}

	

	
}
