package gcom.faturamento.conta;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class MotivoNaoEntregaDocumento implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private String motivoNaoeEntregaDocumento;

    /** persistent field */
    private String abreviado;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public MotivoNaoEntregaDocumento(String motivoNaoeEntregaDocumento, String abreviado, Short indicadorUso, Date ultimaAlteracao) {
        this.motivoNaoeEntregaDocumento = motivoNaoeEntregaDocumento;
        this.abreviado = abreviado;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public MotivoNaoEntregaDocumento() {
    }

    /** minimal constructor */
    public MotivoNaoEntregaDocumento(String motivoNaoeEntregaDocumento, String abreviado) {
        this.motivoNaoeEntregaDocumento = motivoNaoeEntregaDocumento;
        this.abreviado = abreviado;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMotivoNaoeEntregaDocumento() {
        return this.motivoNaoeEntregaDocumento;
    }

    public void setMotivoNaoeEntregaDocumento(String motivoNaoeEntregaDocumento) {
        this.motivoNaoeEntregaDocumento = motivoNaoeEntregaDocumento;
    }

    public String getAbreviado() {
        return this.abreviado;
    }

    public void setAbreviado(String abreviado) {
        this.abreviado = abreviado;
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
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
}
