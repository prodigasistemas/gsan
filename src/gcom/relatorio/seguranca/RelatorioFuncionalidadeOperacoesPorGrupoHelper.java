package gcom.relatorio.seguranca;

import java.io.Serializable;

/**
 * [UC1039] Gerar Relatório de Funcionalidades e Operações por Grupo.
 * 
 * @author Hugo Leonardo
 *
 * @date 15/07/2010
 */
public class RelatorioFuncionalidadeOperacoesPorGrupoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String funcionalidade;
	private String modulo;
	private String operacao;
	private String grupo;
	
	
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

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
}
