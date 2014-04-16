package gcom.cadastro.unidade;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnidadeTipo implements Serializable {

	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Short nivel;

    /** nullable persistent field */
    private String codigoTipo;
    
    public final static String UNIDADE_TIPO_CENTRALIZADORA = new String("C");
    
    public final static String UNIDADE_TIPO_LOCALIDADE = new String("L");
    
    public final static String UNIDADE_TIPO_GERENCIA_REGIONAL = new String("G");
    
    public final static String UNIDADE_TIPO_TERCERIZADO = new String("T");
    
    public final static String UNIDADE_TIPO_REPAVIMENTADORA = new String("R");
    
    public final static String UNIDADE_TIPO_UNIDADE_NEGOCIO = new String("U");
    
    /** CONSTANTES REPRESENTANDO O ID DO TIPO DE UNIDADE */
    public final static Integer UNIDADE_TIPO_TERCERIZADO_ID = new Integer("16");
    public final static Integer UNIDADE_TIPO_CENTRALIZADORA_ID = new Integer("13");    
    public final static Integer UNIDADE_TIPO_REPAVIMENTADORA_ID = new Integer("18");    
    /** FIM CONSTANTES */
    
    /** full constructor */
    public UnidadeTipo(String descricao, String descricaoAbreviada, short indicadorUso, Date ultimaAlteracao, Short nivel, String codigoTipo) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.nivel = nivel;
        this.codigoTipo = codigoTipo;
    }

    /** default constructor */
    public UnidadeTipo() {
    }

    /** minimal constructor */
    public UnidadeTipo(short indicadorUso, Date ultimaAlteracao) {
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
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

    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Short getNivel() {
        return this.nivel;
    }

    public void setNivel(Short nivel) {
        this.nivel = nivel;
    }

    public String getCodigoTipo() {
        return this.codigoTipo;
    }

    public void setCodigoTipo(String codigoTipo) {
        this.codigoTipo = codigoTipo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
