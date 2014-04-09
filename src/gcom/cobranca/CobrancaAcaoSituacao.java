package gcom.cobranca;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CobrancaAcaoSituacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricao;

    /** persistent field */
    private String descricaoAbreviada;

    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Set resumoCobrancaAcaos;

    /** persistent field */
    private Set cobrancaDocumentos;

    
    public static final Integer PENDENTE = new Integer("1");
    public static final Integer EXECUTADA  = new Integer("2");
    public static final Integer CANCELADA = new Integer("3");
    public static final Integer CANCELADA_PRAZO = new Integer("4");
    public static final Integer FISCALIZADA = new Integer("5");
    public static final Integer ENVIADOS = new Integer("6");
    public static final Integer SUSPENSA_POR_PAG_PARC_CANC = new Integer(7);
    public static final Integer SUSPENSA_POR_PAG_PARC_CANC_ATE = new Integer(8);
    public static final Integer SUSPENSA_POR_PAG_PARC_CANC_APOS = new Integer(9);
    
    /** default constructor */
    public CobrancaAcaoSituacao() {
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("castId", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo castId.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param castId O castId a ser setado.
	 */
	public void setId(Integer castId) {
		this.id = castId;
	}

	/**
	 * @return Retorna o campo cobrancaDocumentos.
	 */
	public Set getCobrancaDocumentos() {
		return cobrancaDocumentos;
	}

	/**
	 * @param cobrancaDocumentos O cobrancaDocumentos a ser setado.
	 */
	public void setCobrancaDocumentos(Set cobrancaDocumentos) {
		this.cobrancaDocumentos = cobrancaDocumentos;
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
	 * @return Retorna o campo indicadorUso.
	 */
	public short getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * @return Retorna o campo resumoCobrancaAcaos.
	 */
	public Set getResumoCobrancaAcaos() {
		return resumoCobrancaAcaos;
	}

	/**
	 * @param resumoCobrancaAcaos O resumoCobrancaAcaos a ser setado.
	 */
	public void setResumoCobrancaAcaos(Set resumoCobrancaAcaos) {
		this.resumoCobrancaAcaos = resumoCobrancaAcaos;
	}

	/**
	 * @return Retorna o campo ultimaAltercao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAltercao O ultimaAltercao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

}

