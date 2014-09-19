package gcom.seguranca.acesso.usuario;

import gcom.interceptor.ObjetoTransacao;

import java.io.Serializable;
import java.util.Date;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import javax.jms.Session;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.CallbackException;
import org.hibernate.classic.Lifecycle;

/** @author Hibernate CodeGenerator */
public class UsuarioAbrangencia extends ObjetoTransacao implements Lifecycle {

	private static final long serialVersionUID = 1L;
	
	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private gcom.seguranca.acesso.usuario.UsuarioAbrangencia usuarioAbrangenciaSuperior;

	// CONTANTES
	public final static Integer ESTADO = new Integer("1");

	public final static Integer GERENCIA_REGIONAL = new Integer("2");

	public final static Integer ELO_POLO = new Integer("3");

	public final static Integer LOCALIDADE = new Integer("4");
	
	public final static Integer UNIDADE_NEGOCIO = new Integer("5");
    
     //CONTANTES
    public final static int ESTADO_INT =1;

    public final static int GERENCIA_REGIONAL_INT = 2;
    
    public final static int UNIDADE_NEGOCIO_INT = 5;

    public final static int ELO_POLO_INT = 3;

    public final static int LOCALIDADE_INT = 4;

	/** full constructor */
	public UsuarioAbrangencia(
			String descricao,
			String descricaoAbreviada,
			Short indicadorUso,
			Date ultimaAlteracao,
			gcom.seguranca.acesso.usuario.UsuarioAbrangencia usuarioAbrangenciaSuperior) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.usuarioAbrangenciaSuperior = usuarioAbrangenciaSuperior;

	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	public String getDescricaoParaRegistroTransacao() {
		return this.getDescricao();
	}
	/** default constructor */
	public UsuarioAbrangencia() {
	}

	/** minimal constructor */
	public UsuarioAbrangencia(
			gcom.seguranca.acesso.usuario.UsuarioAbrangencia usuarioAbrangenciaSuperior) {
		this.usuarioAbrangenciaSuperior = usuarioAbrangenciaSuperior;
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

	public gcom.seguranca.acesso.usuario.UsuarioAbrangencia getUsuarioAbrangenciaSuperior() {
		return this.usuarioAbrangenciaSuperior;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param session
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public boolean onUpdate(Session session) {
		return false;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param session
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public boolean onDelete(Session session) {
		return false;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param session
	 *            Descrição do parâmetro
	 * @param serializable
	 *            Descrição do parâmetro
	 */
	public void onLoad(Session session, Serializable serializable) {
	}

	public void setUsuarioAbrangenciaSuperior(
			gcom.seguranca.acesso.usuario.UsuarioAbrangencia usuarioAbrangenciaSuperior) {
		this.usuarioAbrangenciaSuperior = usuarioAbrangenciaSuperior;
	}

	public boolean onSave(org.hibernate.Session arg0) throws CallbackException {
		if (this.usuarioAbrangenciaSuperior == null) {
			UsuarioAbrangencia temp = new UsuarioAbrangencia();

			temp.setId(this.id);
			this.usuarioAbrangenciaSuperior = temp;
		}
		return false;
	}

	public boolean onUpdate(org.hibernate.Session arg0)
			throws CallbackException {
		
		return false;
	}

	public boolean onDelete(org.hibernate.Session arg0)
			throws CallbackException {
		
		return false;
	}

	public void onLoad(org.hibernate.Session arg0, Serializable arg1) {
		

	}
	
	public Filtro retornaFiltroRegistroOperacao() {
		FiltroUsuarioAbrangencia filtro = new FiltroUsuarioAbrangencia();

		filtro.adicionarParametro(
			new ParametroSimples(FiltroUsuarioAbrangencia.ID,this.getId()));
		return filtro;
	}
	
	@Override
	public Filtro retornaFiltro() {
		
		return null;
	}

}
