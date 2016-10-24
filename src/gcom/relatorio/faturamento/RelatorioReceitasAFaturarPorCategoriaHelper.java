package gcom.relatorio.faturamento;

import java.util.Collection;

public class RelatorioReceitasAFaturarPorCategoriaHelper {
	
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
	
}
