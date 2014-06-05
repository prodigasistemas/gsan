package gcom.micromedicao.consumo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ConsumoTipo implements Serializable {
	private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private String descricaoAbreviada;
    private Short indicadorUso;
    private Date ultimaAlteracao;
    private short indicadorCalculoMedia;

    public final static Integer INDEFINIDO = new Integer(0);
    public final static Integer REAL = new Integer(1);
    public final static Integer CONSUMO_MEDIO_AJUSTADO = new Integer(2);
    public final static Integer MEDIA_HIDROMETRO = new Integer(3);
    public final static Integer INFORMADO = new Integer(4);
    public final static Integer NAO_MEDIDO = new Integer(5);
    public final static Integer ESTIMADO = new Integer(6);
    public final static Integer CONSUMO_MINIMO_FIXADO = new Integer(7);
    public final static Integer SEM_CONSUMO = new Integer(8);
    public final static Integer MEDIA_IMOVEL = new Integer(9);

    public final static short INDICADOR_CALCULO_MEDIA_CONSUMO_SIM = 1;
    public final static short INDICADOR_CALCULO_MEDIA_CONSUMO_NAO = 2;
    
    public final static Integer FIXO_SITUACAO_ESPECIAL = new Integer(10);
    
    public ConsumoTipo(String descricao, String descricaoAbreviada, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public ConsumoTipo() {
    }
    
    public ConsumoTipo(Integer id) {
    	this.id = id;
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

	public short getIndicadorCalculoMedia() {
		return indicadorCalculoMedia;
	}

	public void setIndicadorCalculoMedia(short indicadorCalculoMedia) {
		this.indicadorCalculoMedia = indicadorCalculoMedia;
	}
}
