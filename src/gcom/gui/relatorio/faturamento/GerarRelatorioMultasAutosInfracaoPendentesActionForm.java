package gcom.gui.relatorio.faturamento;


import org.apache.struts.action.ActionForm;

/**
 * [UC1198] Gerar Relatório das Multas de Autos de Infração Pendentes
 * 
 * @author Hugo Azevedo
 *
 * @date 10/03/2011
 */

public class GerarRelatorioMultasAutosInfracaoPendentesActionForm extends ActionForm {
	
	private String grupo;
	private String idFuncionario;
	private String descricaoFuncionario;
	
	public GerarRelatorioMultasAutosInfracaoPendentesActionForm(){}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String funcionario) {
		this.idFuncionario = funcionario;
	}

	public String getDescricaoFuncionario() {
		return descricaoFuncionario;
	}

	public void setDescricaoFuncionario(String descricaoFuncionario) {
		this.descricaoFuncionario = descricaoFuncionario;
	}
	
	
}
