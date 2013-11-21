package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;

public class RelatorioManterImovelPerfilBean implements RelatorioBean{
	
	private String codigo;
	
	private String descricao;
	
	/**
	 * Construtor da classe RelatorioManterImovelPerfilBean
	 * 
	 * @param codigo
	 *            Descrição do parâmetro
	 * @param descricao
	 *            Descrição do parâmetro
	 * 
	 */
	public RelatorioManterImovelPerfilBean(String codigo, String descricao) {
		
		this.codigo = codigo;
		this.descricao = descricao;
		
	}
	
	public String getCodigo(){
		return codigo;
	}
	
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}

}
