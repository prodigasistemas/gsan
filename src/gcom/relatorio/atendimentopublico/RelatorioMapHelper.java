package gcom.relatorio.atendimentopublico;

import java.io.Serializable;

/**
 * classe responsável por auxiliar as totalizações das quebras do relatório.
 * 
 * @author Hugo Leonardo
 *
 * @date 30/09/2010
 */
public class RelatorioMapHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String descricao;
	private Integer quantidade;
	
	public RelatorioMapHelper(){
		
	}
	
	public RelatorioMapHelper(Integer id, Integer quantidade){
		
		this.id = id;
		this.quantidade = quantidade;
	}
	
	public RelatorioMapHelper(String descricao, Integer quantidade){
		
		this.descricao = descricao;
		this.quantidade = quantidade;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
