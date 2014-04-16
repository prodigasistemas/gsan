package gcom.gerencial.bean;

import java.io.Serializable;


/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio para quadro de metas por Exercicio
 *
 * @author Bruno Barros
 * @date 07/03/2008
 */
public class FiltrarRelatorioQuadroMetasExercicioHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer opcaoTotalizacao;
	
	private Integer anoExercicio;
	private Integer localidade;
	private Integer unidadeNegocio;
	private Integer gerenciaRegional;

	private String groupBy = "";

	
	public Integer getAnoExercicio() {
	
		return anoExercicio;
	}

	
	public void setAnoExercicio(Integer anoExercicio) {
	
		this.anoExercicio = anoExercicio;
	}

	
	public Integer getGerenciaRegional() {
	
		return gerenciaRegional;
	}

	
	public void setGerenciaRegional(Integer gerenciaRegional) {
	
		this.gerenciaRegional = gerenciaRegional;
	}

	
	public String getGroupBy() {
	
		return groupBy;
	}

	
	public void setGroupBy(String groupBy) {
	
		this.groupBy = groupBy;
	}

	
	public Integer getLocalidade() {
	
		return localidade;
	}

	
	public void setLocalidade(Integer localidade) {
	
		this.localidade = localidade;
	}

	
	public Integer getOpcaoTotalizacao() {
	
		return opcaoTotalizacao;
	}

	
	public void setOpcaoTotalizacao(Integer opcaoTotalizacao) {
	
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	
	public Integer getUnidadeNegocio() {
	
		return unidadeNegocio;
	}

	
	public void setUnidadeNegocio(Integer unidadeNegocio) {
	
		this.unidadeNegocio = unidadeNegocio;
	}
	
}
