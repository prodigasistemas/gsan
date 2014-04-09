package gcom.gerencial.bean;

import gcom.util.Util;

import java.io.Serializable;


/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio para quadro de metas acumulado 
 *
 * @author Bruno Barros
 * @date 20/12/2007
 */
public class FiltrarRelatorioQuadroMetasAcumuladoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer opcaoTotalizacao;
	
	private Integer mesAnoReferencia;
	private Integer localidade;
	private Integer unidadeNegocio;
	private Integer gerenciaRegional;

	private String groupBy = "";
	
	public Integer getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(Integer mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public Integer getMesAnoReferenciaAnterior() {
		return Util.subtrairMesDoAnoMes(this.mesAnoReferencia,1);
	}

	public Integer getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(Integer gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Integer getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Integer localidade) {
		this.localidade = localidade;
	}

	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public Integer getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(Integer opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}	
}
