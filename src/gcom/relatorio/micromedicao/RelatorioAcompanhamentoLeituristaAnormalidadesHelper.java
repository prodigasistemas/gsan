package gcom.relatorio.micromedicao;

import java.io.Serializable;

public class RelatorioAcompanhamentoLeituristaAnormalidadesHelper implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String anormalidade;
	
	private Integer quantidade;

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getAnormalidade() {
		return anormalidade;
	}

	public void setAnormalidade(String anormalidade) {
		this.anormalidade = anormalidade;
	}

	@Override
	public boolean equals(Object obj) {
		boolean retorno = false;
		if (obj instanceof RelatorioAcompanhamentoLeituristaAnormalidadesHelper) {
			RelatorioAcompanhamentoLeituristaAnormalidadesHelper new_name = (RelatorioAcompanhamentoLeituristaAnormalidadesHelper) obj;
			
			retorno = new_name.getAnormalidade().toString().equals(this.getAnormalidade().toString());
			
		}
		
		return retorno;
	}

	@Override
	public int hashCode() {
		return this.getAnormalidade().hashCode();
	}
	
	

	
	

}
