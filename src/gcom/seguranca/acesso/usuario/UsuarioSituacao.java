package gcom.seguranca.acesso.usuario;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class UsuarioSituacao extends ObjetoTransacao implements Serializable {
	private static final long serialVersionUID = 1L;
	public static Integer ATIVO = new Integer(1);
	public static Integer PENDENTE_SENHA = new Integer(2);
	public static Integer SENHA_BLOQUEADA = new Integer(3);
	public static Integer INATIVO = new Integer(4);

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricaoUsuarioSituacao;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** nullable persistent field */
	private Short indicadorUsoSistema;

	/** full constructor */
	public UsuarioSituacao(String descricaoUsuarioSituacao,
			String descricaoAbreviada, Short indicadorUso,
			Date ultimaAlteracao, Short indicadorUsoSistema) {
		this.descricaoUsuarioSituacao = descricaoUsuarioSituacao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorUsoSistema = indicadorUsoSistema;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	/** default constructor */
	public UsuarioSituacao() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoUsuarioSituacao() {
		return this.descricaoUsuarioSituacao;
	}

	public void setDescricaoUsuarioSituacao(String descricaoUsuarioSituacao) {
		this.descricaoUsuarioSituacao = descricaoUsuarioSituacao;
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

	public Short getIndicadorUsoSistema() {
		return indicadorUsoSistema;
	}

	public void setIndicadorUsoSistema(Short indicadorUsoSistema) {
		this.indicadorUsoSistema = indicadorUsoSistema;
	}

	@Override
	public Filtro retornaFiltro() {
		
		return null;
	}
	public Filtro retornaFiltroRegistroOperacao() {
		FiltroUsuarioSituacao filtro = new FiltroUsuarioSituacao();

		filtro.adicionarParametro(
			new ParametroSimples(FiltroUsuarioSituacao.ID,this.getId()));
	
		return filtro;
	}
}
