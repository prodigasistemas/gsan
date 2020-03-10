package gcom.cadastro.endereco;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class EnderecoTipo extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
	public static final int ALTERNATIVO = 3;

	private Integer id;

	private String descricao;

	private Short indicadorUso;

	private Date ultimaAlteracao;

	private String descricaoComId;

	public EnderecoTipo(String descricao, Short indicadorUso, Date ultimaAlteracao) {
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
	public EnderecoTipo() {
	}

	public EnderecoTipo(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String getDescricaoComId() {

		if (this.getId().compareTo(10) == -1) {
			descricaoComId = "0" + getId() + " - " + getDescricao();
		} else {
			descricaoComId = getId() + " - " + getDescricao();
		}

		return descricaoComId;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroEnderecoTipo filtroEnderecoTipo = new FiltroEnderecoTipo();
		filtroEnderecoTipo.adicionarParametro(new ParametroSimples(FiltroEnderecoTipo.ID, this.getId()));
		return filtroEnderecoTipo;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
}
