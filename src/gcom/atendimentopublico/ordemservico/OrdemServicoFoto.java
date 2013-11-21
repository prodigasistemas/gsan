package gcom.atendimentopublico.ordemservico;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class OrdemServicoFoto extends ObjetoTransacao{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private OrdemServico ordemServico;
	private FotoSituacaoOrdemServico fotoSituacao;
	private Date dataFoto;
	private String descricaoFoto;
	private byte[] fotoOrdemServico;
	private Date ultimaAlteracao;
	
	public OrdemServicoFoto(){

	}
	
	/**
	 * [UC1199] – Acompanhar Arquivos de Roteiro
	 * [SB0003] – Pesquisar Fotos da OS
	 * 
	 * @param id
	 * @param os
	 * @param descricaoFoto
	 * @param foto
	 */
	public OrdemServicoFoto(Integer id, OrdemServico os, String descricaoFoto, byte[] foto){
		this.id = id;
		this.ordemServico = os;
		this.descricaoFoto = descricaoFoto;
		this.fotoOrdemServico = foto;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}
	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	public FotoSituacaoOrdemServico getFotoSituacao() {
		return fotoSituacao;
	}
	public void setFotoSituacao(FotoSituacaoOrdemServico fotoSituacao) {
		this.fotoSituacao = fotoSituacao;
	}
	public Date getDataFoto() {
		return dataFoto;
	}
	public void setDataFoto(Date dataFoto) {
		this.dataFoto = dataFoto;
	}
	public String getDescricaoFoto() {
		return descricaoFoto;
	}
	public void setDescricaoFoto(String descricaoFoto) {
		this.descricaoFoto = descricaoFoto;
	}
	public byte[] getFoto() {
		return fotoOrdemServico;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public byte[] getFotoOrdemServico() {
		return fotoOrdemServico;
	}
	public void setFotoOrdemServico(byte[] fotoOrdemServico) {
		this.fotoOrdemServico = fotoOrdemServico;
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