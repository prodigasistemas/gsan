package gcom.cobranca;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DocumentoEmissaoForma implements Serializable {
	private static final long serialVersionUID = 1L;

	
	//Constantes de forma de emissão de documeto >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
    public final static Integer CRONOGRAMA = new Integer(1);
    public final static Integer EVENTUAL = new Integer(2);
    public final static Integer INDIVIDUAL = new Integer(3);
    public final static Integer IMPRESSAO_SIMULTANEA = new Integer(4);
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
    
    
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoDocumentoEmissaoForma;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public DocumentoEmissaoForma(String descricaoDocumentoEmissaoForma, Date ultimaAlteracao) {
        this.descricaoDocumentoEmissaoForma = descricaoDocumentoEmissaoForma;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public DocumentoEmissaoForma() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoDocumentoEmissaoForma() {
        return this.descricaoDocumentoEmissaoForma;
    }

    public void setDescricaoDocumentoEmissaoForma(String descricaoDocumentoEmissaoForma) {
        this.descricaoDocumentoEmissaoForma = descricaoDocumentoEmissaoForma;
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
