package gcom.relatorio.seguranca;

import gcom.relatorio.RelatorioBean;

/**
 * classe responsável por criar o relatório de Funcionalidades e Operacoes por Grupo
 * 
 * [UC1039] Gerar Relatório de Funcionalidades e Operacoes por Grupo
 * 
 * @author Hugo Leonardo
 *
 * @date 15/07/2010
 */
public class RelatorioFuncionalidadeOperacoesPorGrupoBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String grupo;
	private String modulo;
	private String funcionalidade;
	private String operacao;

	public RelatorioFuncionalidadeOperacoesPorGrupoBean(String grupo, String modulo, 
			String funcionalidade, String operacao ) {
		
		this.grupo = grupo;
		this.modulo = modulo;
		this.funcionalidade = funcionalidade;
		this.operacao = operacao;
	}

	public String getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
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

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

}
