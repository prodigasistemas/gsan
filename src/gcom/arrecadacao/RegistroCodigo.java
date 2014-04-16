package gcom.arrecadacao;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RegistroCodigo implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String codigo;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    
    public final static String CODIGO_B = "B";
    public final static String CODIGO_C = "C";
    public final static String CODIGO_E = "E";
    public final static String CODIGO_F = "F";
    public final static String CODIGO_G = "G";
    public final static String CODIGO_X = "X";
    public final static String CODIGO_K = "K";
    public final static Integer CODIGO_SETE = 7;
    public final static Integer CODIGO_SEIS = 6;
    public final static Integer FICHA_COMPENSACAO_ID = 15;
    public final static String CODIGO_W = "W";
    
    public final static Integer CODIGO_CARTAO_CREDITO_TIPO_1 = 1;
    public final static Integer CODIGO_CARTAO_CREDITO_TIPO_2 = 2;
    
    /** full constructor */
    public RegistroCodigo(String descricao, String codigo, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.codigo = codigo;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public RegistroCodigo() {
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

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
