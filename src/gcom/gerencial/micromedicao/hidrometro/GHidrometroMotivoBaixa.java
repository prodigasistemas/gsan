package gcom.gerencial.micromedicao.hidrometro;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GHidrometroMotivoBaixa implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoHidrometroMotivoBaixa;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public GHidrometroMotivoBaixa(Integer id, String descricaoHidrometroMotivoBaixa, Short indicadorUso, Date ultimaAlteracao) {
        this.id = id;
        this.descricaoHidrometroMotivoBaixa = descricaoHidrometroMotivoBaixa;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public GHidrometroMotivoBaixa() {
    }

    /** minimal constructor */
    public GHidrometroMotivoBaixa(Integer id, Date ultimaAlteracao) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoHidrometroMotivoBaixa() {
        return this.descricaoHidrometroMotivoBaixa;
    }

    public void setDescricaoHidrometroMotivoBaixa(String descricaoHidrometroMotivoBaixa) {
        this.descricaoHidrometroMotivoBaixa = descricaoHidrometroMotivoBaixa;
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
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
