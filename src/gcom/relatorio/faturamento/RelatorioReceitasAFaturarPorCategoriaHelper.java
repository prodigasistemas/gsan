package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioReceitasAFaturarPorCategoriaHelper implements RelatorioBean {
	
	private String descricaoCategoria;
	private Collection<RelatorioReceitasAFaturarHelper> relatorioReceitasAFaturarHelpers;
	
	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}
	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}
	public Collection<RelatorioReceitasAFaturarHelper> getRelatorioReceitasAFaturarHelpers() {
		return relatorioReceitasAFaturarHelpers;
	}
	public void setRelatorioReceitasAFaturarHelpers(
			Collection<RelatorioReceitasAFaturarHelper> relatorioReceitasAFaturarHelpers) {
		this.relatorioReceitasAFaturarHelpers = relatorioReceitasAFaturarHelpers;
	}
	
	public JRBeanCollectionDataSource getRelatorioReceitasAFaturarHelpersDS() {
		return new JRBeanCollectionDataSource(this.relatorioReceitasAFaturarHelpers);
	}
	
}
