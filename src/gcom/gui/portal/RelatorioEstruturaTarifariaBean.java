package gcom.gui.portal;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável pelo armazenamento dos campos do relatório
 * relatorioEstruturaTarifariaLojaVirtual.jasper.
 * 
 * @author Diogo Peixoto
 * @since 15/07/2011
 *
 */
public class RelatorioEstruturaTarifariaBean implements RelatorioBean {

	private String categoria;
	private String consumo;
	private String valor;
	private Integer indiceConsumo;
	
	public RelatorioEstruturaTarifariaBean(String categoria, String consumo, String valor, Integer indice){
		this.categoria = categoria;
		this.consumo = consumo;
		this.valor = valor;
		this.indiceConsumo = indice;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getConsumo() {
		return consumo;
	}
	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

	public Integer getIndiceConsumo() {
		return indiceConsumo;
	}

	public void setIndiceConsumo(Integer indiceConsumo) {
		this.indiceConsumo = indiceConsumo;
	}
}