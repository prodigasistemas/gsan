package gcom.gui.relatorio.cadastro;

import org.apache.struts.action.ActionForm;

/**
 * [UC1164] Gerar Resumo dos Imóveis Excluídos da Tarifa Social
 * 
 * @author Vivianne Sousa
 * @date 07/04/2011
 */
public class GerarResumoImoveisExcluidosTarifaSocialActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
    
	private String codigoLocalidade;
	
	private String descricaoLocalidade;
	
	private String unidadeNegocioId;
	
	private String gerenciaRegionalId;
	
    private String motivoExclusao;
    
    private String anoMesPesquisaInicial;
    
    private String anoMesPesquisaFinal;

	public String getAnoMesPesquisaFinal() {
		return anoMesPesquisaFinal;
	}

	public void setAnoMesPesquisaFinal(String anoMesPesquisaFinal) {
		this.anoMesPesquisaFinal = anoMesPesquisaFinal;
	}

	public String getAnoMesPesquisaInicial() {
		return anoMesPesquisaInicial;
	}

	public void setAnoMesPesquisaInicial(String anoMesPesquisaInicial) {
		this.anoMesPesquisaInicial = anoMesPesquisaInicial;
	}

	public String getCodigoLocalidade() {
		return codigoLocalidade;
	}

	public void setCodigoLocalidade(String codigoLocalidade) {
		this.codigoLocalidade = codigoLocalidade;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getGerenciaRegionalId() {
		return gerenciaRegionalId;
	}

	public void setGerenciaRegionalId(String gerenciaRegionalId) {
		this.gerenciaRegionalId = gerenciaRegionalId;
	}

	public String getMotivoExclusao() {
		return motivoExclusao;
	}

	public void setMotivoExclusao(String motivoExclusao) {
		this.motivoExclusao = motivoExclusao;
	}

	public String getUnidadeNegocioId() {
		return unidadeNegocioId;
	}

	public void setUnidadeNegocioId(String unidadeNegocioId) {
		this.unidadeNegocioId = unidadeNegocioId;
	}

	
	
}
