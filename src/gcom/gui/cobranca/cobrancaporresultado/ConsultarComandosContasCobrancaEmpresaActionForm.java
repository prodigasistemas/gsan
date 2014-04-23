package gcom.gui.cobranca.cobrancaporresultado;

import org.apache.struts.action.ActionForm;

/**
 * [UC1167] Consultar Comandos de Cobrança por Empresa
 * 
 * @author Mariana Victor
 * @since 03/05/2011
 */
public class ConsultarComandosContasCobrancaEmpresaActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String idEmpresa;

	private String nomeEmpresa;

	private String periodoCicloInicial;

	private String periodoCicloFinal;

	private String dataExecucaoComando;

	private String dataEncerramento;

	private String idImovel;
	
	private String inscricaoImovel;

	private String idCliente;

	private String nomeCliente;
	
	private String categoria;
	
	private String idImovelPerfil;
	
	private String dsImovelPerfil;
	
	private String idGerenciaRegional;
	
	private String nomeGerenciaRegional;

	private String idUnidadeNegocio;

	private String nomeUnidadeNegocio;

	private String intervaloLocalizacaoInicial;

	private String intervaloLocalizacaoFinal;

	private String intervaloSetorComercialInicial;
	
	private String intervaloSetorComercialFinal;
	
	private String intervaloQuadraInicial;
	
	private String intervaloQuadraFinal;

	private String periodoReferenciaContasInicial;

	private String periodoReferenciaContasFinal;

	private String periodoVencimentoContasInicial;

	private String periodoVencimentoContasFinal;

	private String intervaloValorContasInicial;

	private String intervaloValorContasFinal;
	
	private Integer[] idRegistros;

	private String qtdeTotalContasCobranca;

	private String valorTotalContasCobranca;

	private String arquivoTxtGerado;
	
	private String idRegistro;
	
	private String qtdContasInicial;
	
	private String qtdContasFinal;
	
	private String qtdDiasVencimento;
	
	private String situacaoAgua;

	public ConsultarComandosContasCobrancaEmpresaActionForm() {
		super();
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getDataEncerramento() {
		return dataEncerramento;
	}


	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}


	public String getDataExecucaoComando() {
		return dataExecucaoComando;
	}


	public void setDataExecucaoComando(String dataExecucaoComando) {
		this.dataExecucaoComando = dataExecucaoComando;
	}


	public String getDsImovelPerfil() {
		return dsImovelPerfil;
	}


	public void setDsImovelPerfil(String dsImovelPerfil) {
		this.dsImovelPerfil = dsImovelPerfil;
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


	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}


	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}


	public String getIdImovel() {
		return idImovel;
	}


	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}


	public String getIdImovelPerfil() {
		return idImovelPerfil;
	}


	public void setIdImovelPerfil(String idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}


	public Integer[] getIdRegistros() {
		return idRegistros;
	}


	public void setIdRegistros(Integer[] idRegistros) {
		this.idRegistros = idRegistros;
	}


	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}


	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}


	public String getInscricaoImovel() {
		return inscricaoImovel;
	}


	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
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


	public String getIntervaloSetorComercialFinal() {
		return intervaloSetorComercialFinal;
	}


	public void setIntervaloSetorComercialFinal(String intervaloSetorComercialFinal) {
		this.intervaloSetorComercialFinal = intervaloSetorComercialFinal;
	}


	public String getIntervaloSetorComercialInicial() {
		return intervaloSetorComercialInicial;
	}


	public void setIntervaloSetorComercialInicial(
			String intervaloSetorComercialInicial) {
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


	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}


	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}


	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}


	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}


	public String getPeriodoCicloFinal() {
		return periodoCicloFinal;
	}


	public void setPeriodoCicloFinal(String periodoCicloFinal) {
		this.periodoCicloFinal = periodoCicloFinal;
	}


	public String getPeriodoCicloInicial() {
		return periodoCicloInicial;
	}


	public void setPeriodoCicloInicial(String periodoCicloInicial) {
		this.periodoCicloInicial = periodoCicloInicial;
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


	public void setPeriodoReferenciaContasInicial(
			String periodoReferenciaContasInicial) {
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


	public void setPeriodoVencimentoContasInicial(
			String periodoVencimentoContasInicial) {
		this.periodoVencimentoContasInicial = periodoVencimentoContasInicial;
	}


	public String getQtdeTotalContasCobranca() {
		return qtdeTotalContasCobranca;
	}


	public void setQtdeTotalContasCobranca(String qtdeTotalContasCobranca) {
		this.qtdeTotalContasCobranca = qtdeTotalContasCobranca;
	}


	public String getValorTotalContasCobranca() {
		return valorTotalContasCobranca;
	}


	public void setValorTotalContasCobranca(String valorTotalContasCobranca) {
		this.valorTotalContasCobranca = valorTotalContasCobranca;
	}

	public String getArquivoTxtGerado() {
		return arquivoTxtGerado;
	}

	public void setArquivoTxtGerado(String arquivoTxtGerado) {
		this.arquivoTxtGerado = arquivoTxtGerado;
	}

	public String getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
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


	public String getQtdDiasVencimento() {
		return qtdDiasVencimento;
	}


	public void setQtdDiasVencimento(String qtdDiasVencimento) {
		this.qtdDiasVencimento = qtdDiasVencimento;
	}


	public String getSituacaoAgua() {
		return situacaoAgua;
	}


	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

}
