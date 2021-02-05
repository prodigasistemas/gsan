package gcom.atendimentopublico.ordemservico;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class OrdemServicoFoto extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private OrdemServico ordemServico;
	private FotoSituacaoOrdemServico fotoSituacao;
	private Date data;
	private String descricao;
	private byte[] foto;
	private Date ultimaAlteracao;
	private String nomeArquivo;
	private String caminhoArquivo;

	public OrdemServicoFoto() {
		super();
	}

	public OrdemServicoFoto(Integer id, OrdemServico ordemServico, String descricao, byte[] foto) {
		this.id = id;
		this.ordemServico = ordemServico;
		this.descricao = descricao;
		this.foto = foto;
	}

	public OrdemServicoFoto(Integer id, OrdemServico ordemServico, String descricao, String nomeArquivo,
			String caminhoArquivo) {
		this.id = id;
		this.ordemServico = ordemServico;
		this.descricao = descricao;
		this.nomeArquivo = nomeArquivo;
		this.caminhoArquivo = caminhoArquivo;
	}

	public OrdemServicoFoto(OrdemServico ordemServico, Date data, String descricao, Date ultimaAlteracao,
			String nomeArquivo) {
		super();
		this.ordemServico = ordemServico;
		this.data = data;
		this.descricao = descricao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.nomeArquivo = nomeArquivo;
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

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
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
