package gcom.atendimentopublico.ordemservico;

import java.util.Date;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

public class OrdemServicoFoto extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private OrdemServico ordemServico;
	private FotoSituacaoOrdemServico fotoSituacao;
	private Date data;
	private String descricao;
	private String nomeFoto;
	private String caminhoFoto;
	private Date ultimaAlteracao;

	public OrdemServicoFoto() {
		super();
	}

	public OrdemServicoFoto(
			OrdemServico ordemServico,
			Date data, 
			String descricao,
			String nomeFoto) {
		
		super();
		
		this.ordemServico = ordemServico;
		this.data = data;
		this.descricao = descricao;
		this.nomeFoto = nomeFoto;
		this.ultimaAlteracao = new Date();
	}

	public OrdemServicoFoto(
			Integer id, 
			OrdemServico ordemServico, 
			String descricao, 
			String nomeFoto,
			String caminhoFoto) {
		
		super();
		
		this.id = id;
		this.ordemServico = ordemServico;
		this.descricao = descricao;
		this.nomeFoto = nomeFoto;
		this.caminhoFoto = caminhoFoto;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomeFoto() {
		return nomeFoto;
	}

	public void setNomeFoto(String nomeFoto) {
		this.nomeFoto = nomeFoto;
	}

	public String getCaminhoFoto() {
		return caminhoFoto;
	}

	public void setCaminhoFoto(String caminhoFoto) {
		this.caminhoFoto = caminhoFoto;
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
