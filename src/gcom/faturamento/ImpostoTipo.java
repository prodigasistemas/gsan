package gcom.faturamento;

import java.util.Date;

/**
 * 
 * Tipo de imposto 
 *
 * @author Administrador
 * @date 24/05/2006
 */
public class ImpostoTipo {

	//constantes
	public final static Integer IR = new Integer(1);

	public final static Integer CSLL = new Integer(2);

	public final static Integer COFINS = new Integer(3);

	public final static Integer PIS_PASEP = new Integer(4);
	//fim constantes **************
	
	
	
    private Integer id;

    private String descricaoImposto;

    private String descricaoAbreviada;

    private Short indicadorUso;

    private Date ultimaAlteracao;

	/**
	 * @return Retorna o campo descricaoAbreviada.
	 */
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	/**
	 * @param descricaoAbreviada O descricaoAbreviada a ser setado.
	 */
	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	/**
	 * @return Retorna o campo descricaoImposto.
	 */
	public String getDescricaoImposto() {
		return descricaoImposto;
	}

	/**
	 * @param descricaoImposto O descricaoImposto a ser setado.
	 */
	public void setDescricaoImposto(String descricaoImposto) {
		this.descricaoImposto = descricaoImposto;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public Short getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ImpostoTipo() {

	}

	public ImpostoTipo(Integer id, String descricaoImposto, String descricaoAbreviada, Short indicadorUso, Date ultimaAlteracao) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.descricaoImposto = descricaoImposto;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
