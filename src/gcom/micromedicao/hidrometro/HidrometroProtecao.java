package gcom.micromedicao.hidrometro;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class HidrometroProtecao extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	public final static Integer SMA_PISO = new Integer(6);
	public final static Integer SMA_PAREDE = new Integer(7);
	public final static Integer SMA_INTERNO = new Integer(8);
	public final static Integer OUTROS = new Integer(9);

	private Integer id;

	private String descricao;

	private String descricaoAbreviada;

	private Short indicadorUso;

	private Date ultimaAlteracao;

	public HidrometroProtecao() {
	}

	public HidrometroProtecao(Integer id) {
		super();
		this.id = id;
	}

	public HidrometroProtecao(String descricao, String descricaoAbreviada, Short indicadorUso, Date ultimaAlteracao) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public HidrometroProtecao(String descricao, String descricaoAbreviada) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
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

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
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

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroHidrometroProtecao filtro = new FiltroHidrometroProtecao();
		filtro.adicionarParametro(new ParametroSimples(FiltroHidrometroProtecao.ID, this.getId()));
		return filtro;
	}

	@Override
	public void initializeLazy() {
		getDescricao();
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
}
