package gcom.cobranca;

import java.math.BigDecimal;
import java.util.TreeMap;

/**
 * Classe Helper para auxiliar no gerenciamento dos dados 
 * da tela de Informar Ciclo Meta Grupo
 * 
 * @author Francisco do Nascimento
 * @since 23/04/2009
 */
public class InformarCicloMetaGrupoHelper implements Comparable{

	// P - Principal
	// G - Gerencia regional
	// U - Unidade de negocio
	// L - localidade
	private String tipoItem; 
	
	private Integer idItem;
	
	private String nomeItem;
	
	private Integer metaOriginal;
	
	private Integer metaAtual;
	
	private Integer qtdImoveisSituacao;
	
	//novo campo para o analisar metas do ciclo
	private Integer qtdTotalRealizada;
	
	//novo campo para o analisar metas do ciclo
	private BigDecimal valorTotalRealizada;
	
	//novo campo para o analisar metas do ciclo
	private Integer qtdTotalRestante;
	
	//novo campo para o analisar metas do ciclo
	private BigDecimal valorTotalRestante;
	

	private TreeMap<String, InformarCicloMetaGrupoHelper> subItens;

	/**
	 * @return Retorna o campo idItem.
	 */
	public Integer getIdItem() {
		return idItem;
	}

	/**
	 * @param idItem O idItem a ser setado.
	 */
	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	/**
	 * @return Retorna o campo metaAtual.
	 */
	public Integer getMetaAtual() {
		return metaAtual;
	}

	/**
	 * @param metaAtual O metaAtual a ser setado.
	 */
	public void setMetaAtual(Integer metaAtual) {
		this.metaAtual = metaAtual;
	}

	/**
	 * @return Retorna o campo metaOriginal.
	 */
	public Integer getMetaOriginal() {
		return metaOriginal;
	}

	/**
	 * @param metaOriginal O metaOriginal a ser setado.
	 */
	public void setMetaOriginal(Integer metaOriginal) {
		this.metaOriginal = metaOriginal;
	}

	/**
	 * @return Retorna o campo nomeItem.
	 */
	public String getNomeItem() {
		return nomeItem;
	}

	/**
	 * @param nomeItem O nomeItem a ser setado.
	 */
	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

	/**
	 * @return Retorna o campo subItens.
	 */
	public TreeMap<String, InformarCicloMetaGrupoHelper> getSubItens() {
		return subItens;
	}

	/**
	 * @param subItens O subItens a ser setado.
	 */
	public void setSubItens(TreeMap<String, InformarCicloMetaGrupoHelper> subItens) {
		this.subItens = subItens;
	}

	/**
	 * @return Retorna o campo tipoItem.
	 */
	public String getTipoItem() {
		return tipoItem;
	}

	/**
	 * @param tipoItem O tipoItem a ser setado.
	 */
	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}

	public int compareTo(Object arg) {
		if (arg instanceof InformarCicloMetaGrupoHelper){
			InformarCicloMetaGrupoHelper obj = (InformarCicloMetaGrupoHelper) arg;
			return obj.getNomeItem().compareTo(this.nomeItem);	
		} 
		return 0;
	}

	/**
	 * @return Retorna o campo qtdImoveisSituacao.
	 */
	public Integer getQtdImoveisSituacao() {
		return qtdImoveisSituacao;
	}

	/**
	 * @param qtdImoveisSituacao O qtdImoveisSituacao a ser setado.
	 */
	public void setQtdImoveisSituacao(Integer qtdImoveisSituacao) {
		this.qtdImoveisSituacao = qtdImoveisSituacao;
	}

	/**
	 * @return Retorna o campo qtdTotalRealizada.
	 */
	public Integer getQtdTotalRealizada() {
		return qtdTotalRealizada;
	}

	/**
	 * @param qtdTotalRealizada O qtdTotalRealizada a ser setado.
	 */
	public void setQtdTotalRealizada(Integer qtdTotalRealizada) {
		this.qtdTotalRealizada = qtdTotalRealizada;
	}

	/**
	 * @return Retorna o campo qtdTotalRestante.
	 */
	public Integer getQtdTotalRestante() {
		return qtdTotalRestante;
	}

	/**
	 * @param qtdTotalRestante O qtdTotalRestante a ser setado.
	 */
	public void setQtdTotalRestante(Integer qtdTotalRestante) {
		this.qtdTotalRestante = qtdTotalRestante;
	}

	/**
	 * @return Retorna o campo valorTotalRestante.
	 */
	public BigDecimal getValorTotalRestante() {
		return valorTotalRestante;
	}

	/**
	 * @param valorTotalRestante O valorTotalRestante a ser setado.
	 */
	public void setValorTotalRestante(BigDecimal valorTotalRestante) {
		this.valorTotalRestante = valorTotalRestante;
	}

	public BigDecimal getValorTotalRealizada() {
		return valorTotalRealizada;
	}

	public void setValorTotalRealizada(BigDecimal valorTotalRealizada) {
		this.valorTotalRealizada = valorTotalRealizada;
	}
	
}
