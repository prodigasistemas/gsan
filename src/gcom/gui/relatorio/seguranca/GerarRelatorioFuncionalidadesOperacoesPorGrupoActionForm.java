package gcom.gui.relatorio.seguranca;


import org.apache.struts.action.ActionForm;

/**
 * [UC1039] Gerar Relatório de Funcionalidades e Operacoes por Grupo.
 * 
 * @author Hugo Leonardo
 *
 * @date 15/07/2010
 */

public class GerarRelatorioFuncionalidadesOperacoesPorGrupoActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String[] ids = null;
	private String grupo;
	private String funcionalidade;
	private String modulo;
	private String operacao;

	public void reset(){
		
		this.grupo = null;
		this.funcionalidade = null;
		this.modulo = null;
		this.operacao = null;
	}

	public String getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
}
