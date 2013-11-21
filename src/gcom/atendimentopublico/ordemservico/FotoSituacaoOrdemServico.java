package gcom.atendimentopublico.ordemservico;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class FotoSituacaoOrdemServico extends ObjetoTransacao{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String descricao;
	private Date ultimaAlteracao;

	/**
	 * Full Constructor
	 * 
	 * @param id - ID da situação da foto da ordem de serviço
	 * @param descricao - Descrição da situação da foto da ordem de serviço
	 * @param alteracao - Última alteração realizada na situação da foto da ordem de serviço
	 */
	public FotoSituacaoOrdemServico(Integer id, String descricao, Date alteracao){
		this.id = id;
		this.descricao = descricao;
		this.ultimaAlteracao = alteracao;
	}
	
	/**
	 * Minimal Constructor
	 * 
	 * @param descricao - Descrição da situação da foto da ordem de serviço
	 */
	public FotoSituacaoOrdemServico(String descricao){
		this.descricao = descricao;
	}
	
	/**
	 * Minimal Constructor
	 * 
	 * @param descricao - Descrição da situação da foto da ordem de serviço
	 */
	public FotoSituacaoOrdemServico(){
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
}