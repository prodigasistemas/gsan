package gcom.seguranca.transacao;

import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */


public class AlteracaoTipo extends TabelaAuxiliarAbreviada implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	/** constantes que indica o codigo do ALTERACAO */
	public static Integer ALTERACAO = new Integer(1);

	/** constantes que indica o codigo do INCLUSAO */
	public static Integer INCLUSAO = new Integer(2);

	/** constantes que indica o codigo do EXCLUSAO */
	public static Integer EXCLUSAO = new Integer(3);

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricao;

    /** persistent field */
    private String descricaoAbreviada;

    /** persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public AlteracaoTipo (Integer id, String descricao, String descricaoAbreviada, Date ultimaAlteracao) {
        this.id = id;
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public AlteracaoTipo () {
    }

	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
}
