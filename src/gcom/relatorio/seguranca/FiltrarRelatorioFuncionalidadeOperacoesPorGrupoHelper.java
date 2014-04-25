package gcom.relatorio.seguranca;

import java.io.Serializable;
import java.util.Collection;


/**
 * [UC1039] Gerar Relatório de Funcionalidades e Operações por Grupo.
 * 
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatório de Funcionalidades e Operações por Grupo
 *
 * @author Hugo Leonardo
 * @date 15/07/2010
 * 
 */
public class FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Collection<Integer> grupo;
	private String funcionalidade;
	private String modulo;
	private String operacao;
	
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

	public Collection<Integer> getGrupo() {
		return grupo;
	}

	public void setGrupo(Collection<Integer> grupo) {
		this.grupo = grupo;
	}
	
}
