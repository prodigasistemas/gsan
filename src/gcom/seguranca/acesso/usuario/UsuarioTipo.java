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
public class UsuarioTipo extends ObjetoTransacao implements Serializable {


	private static final long serialVersionUID = 1L;

	
	public static final Integer USUARIO_TIPO_ADMINISTRADOR = new Integer(1);
	
	public static final short INDICADOR_FUNCIONARIO = new Short("1");
	
	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	private Short indicadorFuncionario;

	/** full constructor */
	public UsuarioTipo(String descricao, Short indicadorUso,
			Date ultimaAlteracao) {
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	/** default constructor */
	public UsuarioTipo() {
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

	public Short getIndicadorFuncionario() {
		return indicadorFuncionario;
	}

	public void setIndicadorFuncionario(Short indicadorFuncionario) {
		this.indicadorFuncionario = indicadorFuncionario;
	}

	@Override
	public Filtro retornaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}
	public Filtro retornaFiltroRegistroOperacao() {
		FiltroUsuarioTipo filtro = new FiltroUsuarioTipo();

		filtro.adicionarParametro(
			new ParametroSimples(FiltroUsuarioTipo.ID,this.getId()));
	
		return filtro;
	}
	

}
