package gcom.atendimentopublico.ordemservico.bean;

import java.math.BigDecimal;


/**
 * [UC0639] Filtrar Ordens de Processo de Repavimentação
 * 
 * Classe facilitadora para o retorno do filtro a ser usado no manter.
 * 
 * @author Yara Taciane
 * @date 02/06/2008
 */
public class OSTipoPavimentoHelper {

	private Integer id ;
	private Integer quantidade;
	private String descricao;
	private BigDecimal somatorioArea;
	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return Retorna o campo quantidade.
	 */
	public Integer getQuantidade() {
		return quantidade;
	}
	/**
	 * @param quantidade O quantidade a ser setado.
	 */
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	/**
	 * @return Retorna o campo somatorioArea.
	 */
	public BigDecimal getSomatorioArea() {
		return somatorioArea;
	}
	/**
	 * @param somatorioArea O somatorioArea a ser setado.
	 */
	public void setSomatorioArea(BigDecimal somatorioArea) {
		this.somatorioArea = somatorioArea;
	}
    
   
}
