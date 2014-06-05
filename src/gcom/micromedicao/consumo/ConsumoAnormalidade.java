package gcom.micromedicao.consumo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ConsumoAnormalidade implements Serializable {
	private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private String descricaoAbreviada;
    private String mensagemConta;
    private Short indicadorUso;
    private Date ultimaAlteracao;
    private Short indicadorRevisaoPermissaoEspecial;

    public final static Integer CONSUMO_INFORMADO = new Integer(2);
    public final static Integer BAIXO_CONSUMO = new Integer(4);
    public final static Integer ESTOURO_CONSUMO = new Integer(5);
    public final static Integer ALTO_CONSUMO = new Integer(6);
    public final static Integer LEITURA_ATUAL_MENOR_PROJETADA = new Integer(7);
    public final static Integer LEITURA_ATUAL_MENOR_ANTERIOR = new Integer(8);
    public final static Integer HIDROMETRO_SUBSTITUIDO_INFORMADO = new Integer(9);
    public final static Integer LEITURA_NAO_INFORMADA = new Integer(10);
    public final static Integer ESTOURO_CONSUMO_COBRANCA_MEDIA = new Integer(11);
    public final static Integer CONSUMO_MINIMO_FIXADO = new Integer(12);
    public final static Integer FORA_FAIXA = new Integer(13);
    public final static Integer HIDROMETRO_SUBSTITUIDO_NAO_INFORMADO = new Integer(14);
    public final static Integer FATURAMENTO_ANTECIPADO = new Integer(15);
    public final static Integer VIRADA_HIDROMETRO = new Integer(16);
    public final static Integer ANORMALIDADE_DE_LEITURA = new Integer(17);
    
    private short indicadorCalcularMedia;
    
    
    public short getIndicadorCalcularMedia() {
		return indicadorCalcularMedia;
	}

	public void setIndicadorCalcularMedia(short indicadorCalcularMedia) {
		this.indicadorCalcularMedia = indicadorCalcularMedia;
	}

    public ConsumoAnormalidade(String descricao, String descricaoAbreviada, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public ConsumoAnormalidade() {
    }

    public ConsumoAnormalidade(Integer id) {
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
    
    public String getMensagemConta() {
		return mensagemConta;
	}

	public void setMensagemConta(String mensagemConta) {
		this.mensagemConta = mensagemConta;
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

	public Short getIndicadorRevisaoPermissaoEspecial() {
		return indicadorRevisaoPermissaoEspecial;
	}

	public void setIndicadorRevisaoPermissaoEspecial(Short indicadorRevisaoPermissaoEspecial) {
		this.indicadorRevisaoPermissaoEspecial = indicadorRevisaoPermissaoEspecial;
	}

}
