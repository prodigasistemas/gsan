package gcom.relatorio;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioEmitirOrdemServicoSeletivaAnaliticoSubrelatorioBean
		implements RelatorioBean {
	private String categoria;
	private JRBeanCollectionDataSource colecaoSubcategorias;
	private String subcategoria;
	
	public RelatorioEmitirOrdemServicoSeletivaAnaliticoSubrelatorioBean(
			String categoria, JRBeanCollectionDataSource colecaoSubcategorias) {
		this.categoria = categoria;
		this.colecaoSubcategorias = colecaoSubcategorias;
	}
	public RelatorioEmitirOrdemServicoSeletivaAnaliticoSubrelatorioBean(
			String subcategoria) {
		this.subcategoria = subcategoria;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public JRBeanCollectionDataSource getColecaoSubcategorias() {
		return colecaoSubcategorias;
	}
	public void setColecaoSubcategorias(
			JRBeanCollectionDataSource colecaoSubcategorias) {
		this.colecaoSubcategorias = colecaoSubcategorias;
	}
	public String getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}
	
	



}
