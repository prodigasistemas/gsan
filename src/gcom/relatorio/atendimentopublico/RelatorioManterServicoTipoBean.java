package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Vinicius Medeiros
 * @created 23 de Marco de 2009
 */
public class RelatorioManterServicoTipoBean implements RelatorioBean {

	private String codigo;

	private String descricao;

	private String codigoServicoTipo;

	private String tempoMedio;

	private String indicadorAtualizaComercial;

	public RelatorioManterServicoTipoBean(String codigo, String descricao,
			String codigoServicoTipo, String tempoMedio, String indicadorAtualizaComercial){
		
		this.codigo = codigo;
		this.descricao = descricao;
		this.codigoServicoTipo = codigoServicoTipo;
		this.tempoMedio = tempoMedio;
		this.indicadorAtualizaComercial = indicadorAtualizaComercial;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoServicoTipo() {
		return codigoServicoTipo;
	}

	public void setCodigoServicoTipo(String codigoServicoTipo) {
		this.codigoServicoTipo = codigoServicoTipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIndicadorAtualizaComercial() {
		return indicadorAtualizaComercial;
	}

	public void setIndicadorAtualizaComercial(
			String indicadorAtualizaComercial) {
		this.indicadorAtualizaComercial = indicadorAtualizaComercial;
	}

	public String getTempoMedio() {
		return tempoMedio;
	}

	public void setTempoMedio(String tempoMedio) {
		this.tempoMedio = tempoMedio;
	}

	

}
