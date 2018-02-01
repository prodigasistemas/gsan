package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class GerarArquivoTextoContasCobrancaEmpresaActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String idEmpresa;
	private String nomeEmpresa;
	private String periodoComandoInicial;
	private String periodoComandoFinal;
	private String dataExecucaoComando;
	private String periodoReferenciaContasInicial;
	private String periodoReferenciaContasFinal;
	private String periodoVencimentoContasInicial;
	private String periodoVencimentoContasFinal;
	private String intervaloValorContasInicial;
	private String intervaloValorContasFinal;
	private String qtdContasInicial;
	private String qtdContasFinal;
	private String qtdDeDiasVencimento;
	private String idImovel;
	private String idCliente;
	private String nomeCliente;
	private String idUnidadeNegocio;
	private String nomeUnidadeNegocio;
	private String idLigacaoAguaSituacao;
	private String nomeLigacaoAguaSituacao;
	private String intervaloLocalizacaoInicial;
	private String intervaloLocalizacaoFinal;
	private String intervaloSetorComercialInicial;
	private String intervaloSetorComercialFinal;
	private String intervaloRotasInicial;
	private String numeroSequencialRotaInicial;
	private String intervaloRotasFinal;
	private String numeroSequencialRotaFinal;
	private String qtdeTotalContasCobranca;
	private String valorTotalContasCobranca;
	private String qtdeContasCriterioComando;
	private String valorContasCriterioComando;
	private Integer[] idRegistros;
	private String idImovelPerfil;
	private String dsImovelPerfil;
	private String idGerenciaRegional;
	private String nomeGerenciaRegional;
	private String intervaloQuadraInicial;
	private String intervaloQuadraFinal;
	private String qtdContas;
	private String qtdClientes;
	private String valorTotal;

	public String getDataExecucaoComando() {
		return dataExecucaoComando;
	}

	public void setDataExecucaoComando(String dataExecucaoComando) {
		this.dataExecucaoComando = dataExecucaoComando;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getIntervaloLocalizacaoFinal() {
		return intervaloLocalizacaoFinal;
	}

	public void setIntervaloLocalizacaoFinal(String intervaloLocalizacaoFinal) {
		this.intervaloLocalizacaoFinal = intervaloLocalizacaoFinal;
	}

	public String getIntervaloLocalizacaoInicial() {
		return intervaloLocalizacaoInicial;
	}

	public void setIntervaloLocalizacaoInicial(String intervaloLocalizacaoInicial) {
		this.intervaloLocalizacaoInicial = intervaloLocalizacaoInicial;
	}

	public String getIntervaloRotasFinal() {
		return intervaloRotasFinal;
	}

	public void setIntervaloRotasFinal(String intervaloRotasFinal) {
		this.intervaloRotasFinal = intervaloRotasFinal;
	}

	public String getIntervaloRotasInicial() {
		return intervaloRotasInicial;
	}

	public void setIntervaloRotasInicial(String intervaloRotasInicial) {
		this.intervaloRotasInicial = intervaloRotasInicial;
	}

	public String getIntervaloSetorComercialInicial() {
		return intervaloSetorComercialInicial;
	}

	public void setIntervaloSetorComercialInicial(String intervaloSetorComercialInicial) {
		this.intervaloSetorComercialInicial = intervaloSetorComercialInicial;
	}

	public String getIntervaloValorContasFinal() {
		return intervaloValorContasFinal;
	}

	public void setIntervaloValorContasFinal(String intervaloValorContasFinal) {
		this.intervaloValorContasFinal = intervaloValorContasFinal;
	}

	public String getIntervaloValorContasInicial() {
		return intervaloValorContasInicial;
	}

	public void setIntervaloValorContasInicial(String intervaloValorContasInicial) {
		this.intervaloValorContasInicial = intervaloValorContasInicial;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

	public String getNumeroSequencialRotaFinal() {
		return numeroSequencialRotaFinal;
	}

	public void setNumeroSequencialRotaFinal(String numeroSequencialRotaFinal) {
		this.numeroSequencialRotaFinal = numeroSequencialRotaFinal;
	}

	public String getNumeroSequencialRotaInicial() {
		return numeroSequencialRotaInicial;
	}

	public void setNumeroSequencialRotaInicial(String numeroSequencialRotaInicial) {
		this.numeroSequencialRotaInicial = numeroSequencialRotaInicial;
	}

	public String getPeriodoComandoFinal() {
		return periodoComandoFinal;
	}

	public void setPeriodoComandoFinal(String periodoComandoFinal) {
		this.periodoComandoFinal = periodoComandoFinal;
	}

	public String getPeriodoComandoInicial() {
		return periodoComandoInicial;
	}

	public void setPeriodoComandoInicial(String periodoComandoInicial) {
		this.periodoComandoInicial = periodoComandoInicial;
	}

	public String getPeriodoReferenciaContasFinal() {
		return periodoReferenciaContasFinal;
	}

	public void setPeriodoReferenciaContasFinal(String periodoReferenciaContasFinal) {
		this.periodoReferenciaContasFinal = periodoReferenciaContasFinal;
	}

	public String getPeriodoReferenciaContasInicial() {
		return periodoReferenciaContasInicial;
	}

	public void setPeriodoReferenciaContasInicial(String periodoReferenciaContasInicial) {
		this.periodoReferenciaContasInicial = periodoReferenciaContasInicial;
	}

	public String getPeriodoVencimentoContasFinal() {
		return periodoVencimentoContasFinal;
	}

	public void setPeriodoVencimentoContasFinal(String periodoVencimentoContasFinal) {
		this.periodoVencimentoContasFinal = periodoVencimentoContasFinal;
	}

	public String getPeriodoVencimentoContasInicial() {
		return periodoVencimentoContasInicial;
	}

	public void setPeriodoVencimentoContasInicial(String periodoVencimentoContasInicial) {
		this.periodoVencimentoContasInicial = periodoVencimentoContasInicial;
	}

	public String getQtdeContasCriterioComando() {
		return qtdeContasCriterioComando;
	}

	public void setQtdeContasCriterioComando(String qtdeContasCriterioComando) {
		this.qtdeContasCriterioComando = qtdeContasCriterioComando;
	}

	public String getQtdeTotalContasCobranca() {
		return qtdeTotalContasCobranca;
	}

	public void setQtdeTotalContasCobranca(String qtdeTotalContasCobranca) {
		this.qtdeTotalContasCobranca = qtdeTotalContasCobranca;
	}

	public String getValorContasCriterioComando() {
		return valorContasCriterioComando;
	}

	public void setValorContasCriterioComando(String valorContasCriterioComando) {
		this.valorContasCriterioComando = valorContasCriterioComando;
	}

	public String getValorTotalContasCobranca() {
		return valorTotalContasCobranca;
	}

	public void setValorTotalContasCobranca(String valorTotalContasCobranca) {
		this.valorTotalContasCobranca = valorTotalContasCobranca;
	}

	public Integer[] getIdRegistros() {
		return idRegistros;
	}

	public void setIdRegistros(Integer[] idRegistros) {
		this.idRegistros = idRegistros;
	}

	public String getIntervaloSetorComercialFinal() {
		return intervaloSetorComercialFinal;
	}

	public void setIntervaloSetorComercialFinal(String intervaloSetorComercialFinal) {
		this.intervaloSetorComercialFinal = intervaloSetorComercialFinal;
	}

	public String getIdImovelPerfil() {
		return idImovelPerfil;
	}

	public void setIdImovelPerfil(String idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}

	public String getDsImovelPerfil() {
		return dsImovelPerfil;
	}

	public void setDsImovelPerfil(String dsImovelPerfil) {
		this.dsImovelPerfil = dsImovelPerfil;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	public String getIntervaloQuadraFinal() {
		return intervaloQuadraFinal;
	}

	public void setIntervaloQuadraFinal(String intervaloQuadraFinal) {
		this.intervaloQuadraFinal = intervaloQuadraFinal;
	}

	public String getIntervaloQuadraInicial() {
		return intervaloQuadraInicial;
	}

	public void setIntervaloQuadraInicial(String intervaloQuadraInicial) {
		this.intervaloQuadraInicial = intervaloQuadraInicial;
	}

	public String getQtdContasInicial() {
		return qtdContasInicial;
	}

	public void setQtdContasInicial(String qtdContasInicial) {
		this.qtdContasInicial = qtdContasInicial;
	}

	public String getQtdContasFinal() {
		return qtdContasFinal;
	}

	public void setQtdContasFinal(String qtdContasFinal) {
		this.qtdContasFinal = qtdContasFinal;
	}

	public String getQtdDeDiasVencimento() {
		return qtdDeDiasVencimento;
	}

	public void setQtdDeDiasVencimento(String qtdDeDiasVencimento) {
		this.qtdDeDiasVencimento = qtdDeDiasVencimento;
	}

	public String getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}

	public void setIdLigacaoAguaSituacao(String idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}

	public String getNomeLigacaoAguaSituacao() {
		return nomeLigacaoAguaSituacao;
	}

	public void setNomeLigacaoAguaSituacao(String nomeLigacaoAguaSituacao) {
		this.nomeLigacaoAguaSituacao = nomeLigacaoAguaSituacao;
	}

	public String getQtdContas() {
		return qtdContas;
	}

	public void setQtdContas(String qtdContas) {
		this.qtdContas = qtdContas;
	}

	public String getQtdClientes() {
		return qtdClientes;
	}

	public void setQtdClientes(String qtdClientes) {
		this.qtdClientes = qtdClientes;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.idEmpresa = null;
		this.nomeEmpresa = null;
		this.periodoComandoInicial = null;
		this.periodoComandoFinal = null;
		this.dataExecucaoComando = null;
		this.periodoReferenciaContasInicial = null;
		this.periodoReferenciaContasFinal = null;
		this.periodoVencimentoContasInicial = null;
		this.periodoVencimentoContasFinal = null;
		this.intervaloValorContasInicial = null;
		this.intervaloValorContasFinal = null;
		this.qtdContasInicial = null;
		this.qtdContasFinal = null;
		this.qtdDeDiasVencimento = null;
		this.idImovel = null;
		this.idCliente = null;
		this.nomeCliente = null;
		this.idUnidadeNegocio = null;
		this.nomeUnidadeNegocio = null;
		this.idLigacaoAguaSituacao = null;
		this.nomeLigacaoAguaSituacao = null;
		this.intervaloLocalizacaoInicial = null;
		this.intervaloLocalizacaoFinal = null;
		this.intervaloSetorComercialInicial = null;
		this.intervaloSetorComercialFinal = null;
		this.intervaloRotasInicial = null;
		this.numeroSequencialRotaInicial = null;
		this.intervaloRotasFinal = null;
		this.numeroSequencialRotaFinal = null;
		this.qtdeTotalContasCobranca = null;
		this.valorTotalContasCobranca = null;
		this.qtdeContasCriterioComando = null;
		this.valorContasCriterioComando = null;
		this.idRegistros = null;
		this.idImovelPerfil = null;
		this.dsImovelPerfil = null;
		this.idGerenciaRegional = null;
		this.nomeGerenciaRegional = null;
		this.intervaloQuadraInicial = null;
		this.intervaloQuadraFinal = null;
		this.qtdContas = null;
		this.qtdClientes = null;
		this.valorTotal = null;
	}
}
