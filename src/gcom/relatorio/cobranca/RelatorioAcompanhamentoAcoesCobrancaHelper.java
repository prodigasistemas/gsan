package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * [UCXXXX] - Gerar Relatório Analisar Metas Ciclo
 * 
 * @author Genival Barbosa
 * @date 21/07/2009
 */
public class RelatorioAcompanhamentoAcoesCobrancaHelper implements RelatorioBean {

	private String idCobrancaAcao;
	
	private String dataInicial;
	
	private String dataFinal;
	
	private String chkEstado;
	
	private String chkGerencia;
	
	private String idGerenciaRegional;
	
	private String chkUnidade;
	
	private String idUnidadeNegocio;
	
	private String chkLocalidade;
	
	private String idLocalidade;
		
	private String idEmpresa;
	
	
	public RelatorioAcompanhamentoAcoesCobrancaHelper(
			String idCobrancaAcao, String dataInicial, 
			String dataFinal, String chkEstado, 
			String chkGerencia, String idGerenciaRegional, 
			String chkUnidade, String idUnidadeNegocio, 
			String chkLocalidade, String idLocalidade, 
			String idEmpresa) {
		super();
		
		this.idCobrancaAcao = idCobrancaAcao;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.chkEstado = chkEstado;
		this.chkGerencia = chkGerencia;
		this.idGerenciaRegional = idGerenciaRegional;
		this.chkUnidade = chkUnidade;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.chkLocalidade = chkLocalidade;
		this.idLocalidade = idLocalidade;
		this.idEmpresa = idEmpresa;
	}


	public String getChkEstado() {
		return chkEstado;
	}


	public void setChkEstado(String chkEstado) {
		this.chkEstado = chkEstado;
	}


	public String getChkGerencia() {
		return chkGerencia;
	}


	public void setChkGerencia(String chkGerencia) {
		this.chkGerencia = chkGerencia;
	}


	public String getChkLocalidade() {
		return chkLocalidade;
	}


	public void setChkLocalidade(String chkLocalidade) {
		this.chkLocalidade = chkLocalidade;
	}


	public String getChkUnidade() {
		return chkUnidade;
	}


	public void setChkUnidade(String chkUnidade) {
		this.chkUnidade = chkUnidade;
	}


	public String getDataFinal() {
		return dataFinal;
	}


	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}


	public String getDataInicial() {
		return dataInicial;
	}


	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}


	public String getIdCobrancaAcao() {
		return idCobrancaAcao;
	}


	public void setIdCobrancaAcao(String idCobrancaAcao) {
		this.idCobrancaAcao = idCobrancaAcao;
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


	public String getIdLocalidade() {
		return idLocalidade;
	}


	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}


	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}


	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	
}
