package gcom.gui.portal;

/**
 * [UC1194] Consultar Estrutura Tarifária Loja Virtual
 * 
 * Helper responsável pela exibição das estruturas tarifárias 
 * da tela informacoes_estrutura_tarifaria_porta_consultar.jsp
 * 
 * @author Diogo Peixoto
 * @since 15/07/2011
 *
 */

public class ConsultarEstruturaTarifariaPortalHelper {

	private String categoria;
	private String valor;
	private String quantidade;
	private Integer indiceConsumo;
	
	public ConsultarEstruturaTarifariaPortalHelper(String categoria, String valor, Integer indice){
		this.setCategoria(categoria);
		this.setValor(valor);
		this.indiceConsumo = indice;
	}

	public ConsultarEstruturaTarifariaPortalHelper(String categoria, String quantidade, String valor, Integer indice){
		this.setCategoria(categoria);
		this.setValor(valor);
		this.quantidade = quantidade;
		this.indiceConsumo = indice;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		if(categoria != null){
			this.categoria = categoria;
		}else{
			this.categoria = "";
		}
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		if(valor != null){
			this.valor = valor;
		}else{
			this.valor = "";
		}
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		if(quantidade != null){
			this.quantidade = quantidade;
		}else{
			this.quantidade = "";
		}
	}

	public Integer getIndiceConsumo() {
		return indiceConsumo;
	}

	public void setIndiceConsumo(Integer indiceConsumo) {
		this.indiceConsumo = indiceConsumo;
	}
}
