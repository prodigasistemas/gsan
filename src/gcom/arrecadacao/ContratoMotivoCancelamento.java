package gcom.arrecadacao;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


public class ContratoMotivoCancelamento implements Serializable {

	private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricaoMotivoCancelContrato;
    private Short indicadorUso;
    private Date ultimaAlteracao;

    public ContratoMotivoCancelamento(String descricaoMotivoCancelContrato, Short indicadorUso, Date ultimaAlteracao) {
        this.descricaoMotivoCancelContrato = descricaoMotivoCancelContrato;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public ContratoMotivoCancelamento() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoMotivoCancelContrato() {
        return this.descricaoMotivoCancelContrato;
    }

    public void setDescricaoMotivoCancelContrato(String descricaoMotivoCancelContrato) {
        this.descricaoMotivoCancelContrato = descricaoMotivoCancelContrato;
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

}
