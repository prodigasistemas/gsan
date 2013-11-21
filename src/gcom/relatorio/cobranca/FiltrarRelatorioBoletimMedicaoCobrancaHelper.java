package gcom.relatorio.cobranca;

import java.io.Serializable;


/**
 * [UC1152] Emissão Boletim Medição Cobrança
 * 
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio de Boletim de Medição de Cobrança
 * 
 * @author Mariana Victor
 * @date 21/03/2011
 */
public class FiltrarRelatorioBoletimMedicaoCobrancaHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String mesAnoReferencia;
	
	private String grupoCobranca;
	
	private String nomeGrupoCobranca;
	
	private String gerenciaRegional;
	
	private String unidadeNegocio;
	
	private String localidadeInicial;
	
	private String localidadeFinal;
	
	
	public String getGrupoCobranca() {
		return grupoCobranca;
	}
	
	public void setGrupoCobranca(String grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}
	
	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}
	
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public String getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public String getNomeGrupoCobranca() {
		return nomeGrupoCobranca;
	}

	public void setNomeGrupoCobranca(String nomeGrupoCobranca) {
		this.nomeGrupoCobranca = nomeGrupoCobranca;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

}
