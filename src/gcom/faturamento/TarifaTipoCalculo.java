package gcom.faturamento;

import java.util.Date;

/**
 * 
 * Tipo de imposto 
 *
 * @author Sávio Luiz
 * @date 17/06/2009
 */
public class TarifaTipoCalculo {
	
	
	
    private Integer id;

    private String descricaoTarifaTipoCalculo;

    private String descricaoAbreviada;

    private Short indicadorUso;

    private Date ultimaAlteracao;
    
   
    public final static Integer CALCULO_PROPORCIONAL = 1;
    public final static Integer CALCULO_SEM_FAIXA_CAER = 2;
    public final static Integer CALCULO_POR_REFERENCIA = 3;
    public final static Integer CALCULO_DIRETO_NA_FAIXA = 4;

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


	public String getDescricaoTarifaTipoCalculo() {
		return descricaoTarifaTipoCalculo;
	}

	public void setDescricaoTarifaTipoCalculo(String descricaoTarifaTipoCalculo) {
		this.descricaoTarifaTipoCalculo = descricaoTarifaTipoCalculo;
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

	public TarifaTipoCalculo() {

	}

	public TarifaTipoCalculo(Integer id, String descricaoTarifaTipoCalculo, String descricaoAbreviada, Short indicadorUso, Date ultimaAlteracao) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.descricaoTarifaTipoCalculo = descricaoTarifaTipoCalculo;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
