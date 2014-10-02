package gcom.cadastro.funcionario;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

/**
 * @author Hibernate CodeGenerator
 */
public class FuncionarioCargo extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor de FuncionarioCargo 
	 * 
	 * @param id
	 * @param codigo
	 * @param descricao
	 * @param descricaoAbreviada
	 * @param indicadorUso
	 * @param ultimaAlteracao
	 */
	public FuncionarioCargo(Integer id, String descricao, Integer codigo, String descricaoAbreviada, Short indicadorUso, Date ultimaAlteracao) {
		
		this.id = id;
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.codigo = codigo;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	/** default constructor */
	public FuncionarioCargo(){
		
	}
	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** nullable persistent field */
	private Integer codigo;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** full constructor */
	public FuncionarioCargo(String descricao, String descricaoAbreviada,
			Integer codigo, Date ultimaAlteracao, Short indicadorUso) {

		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.codigo = codigo;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}



	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getDescricao();
	}

	@Override
	public Filtro retornaFiltro() {
		
		return null;
	}

}
