package gcom.micromedicao.consumo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 */
public class ConsumoAnormalidade implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * identifier field
     */
    private Integer id;

    /**
     * nullable persistent field
     */
    private String descricao;

    /**
     * nullable persistent field
     */
    private String descricaoAbreviada;

    /**
     * nullable persistent field
     */
    private String mensagemConta;
    
    /**
     * nullable persistent field
     */
    private Short indicadorUso;

    /**
     * nullable persistent field
     */
    private Date ultimaAlteracao;
    
    private Short indicadorRevisaoPermissaoEspecial;

    //--CONTANTES
    /**
     * Description of the Field
     */
    public final static Integer ESTOURO_CONSUMO = new Integer(5);

    /**
     * Description of the Field
     */
    public final static Integer FORA_FAIXA = new Integer(13);

    /**
     * Description of the Field
     */
    public final static Integer HIDROMETRO_SUBSTITUIDO_INFORMADO = new Integer(9);

    /**
     * Description of the Field
     */
    public final static Integer BAIXO_CONSUMO = new Integer(4);

    /**
     * Description of the Field
     */
    /**
     * Description of the Field
     */
    public final static Integer LEITURA_ATUAL_MENOR_ANTERIOR = new Integer(8);

    /**
     * Description of the Field
     */
    public final static Integer LEITURA_ATUAL_MENOR_PROJETADA = new Integer(7);

    /**
     * Description of the Field
     */
    public final static Integer ESTOURO_CONSUMO_COBRANCA_MEDIA = new Integer(11);

    /**
     * Description of the Field
     */
    public final static Integer ALTO_CONSUMO = new Integer(6);

    public final static Integer CONSUMO_MINIMO_FIXADO = new Integer(12);

    public final static Integer CONSUMO_INFORMADO = new Integer(2);

    public final static Integer LEITURA_NAO_INFORMADA = new Integer(10);

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

	/**
     * full constructor
     * 
     * @param descricao
     *            Descrição do parâmetro
     * @param descricaoAbreviada
     *            Descrição do parâmetro
     * @param indicadorUso
     *            Descrição do parâmetro
     * @param ultimaAlteracao
     *            Descrição do parâmetro
     */
    public ConsumoAnormalidade(String descricao, String descricaoAbreviada,
            Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * default constructor
     */
    public ConsumoAnormalidade() {
    }

    /**
     * Retorna o valor de id
     * 
     * @return O valor de id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Seta o valor de id
     * 
     * @param id
     *            O novo valor de id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o valor de descricao
     * 
     * @return O valor de descricao
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Seta o valor de descricao
     * 
     * @param descricao
     *            O novo valor de descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna o valor de descricaoAbreviada
     * 
     * @return O valor de descricaoAbreviada
     */
    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    /**
     * Seta o valor de descricaoAbreviada
     * 
     * @param descricaoAbreviada
     *            O novo valor de descricaoAbreviada
     */
    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    
    public String getMensagemConta() {
		return mensagemConta;
	}

	public void setMensagemConta(String mensagemConta) {
		this.mensagemConta = mensagemConta;
	}

	/**
     * Retorna o valor de indicadorUso
     * 
     * @return O valor de indicadorUso
     */
    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    /**
     * Seta o valor de indicadorUso
     * 
     * @param indicadorUso
     *            O novo valor de indicadorUso
     */
    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    /**
     * Retorna o valor de ultimaAlteracao
     * 
     * @return O valor de ultimaAlteracao
     */
    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    /**
     * Seta o valor de ultimaAlteracao
     * 
     * @param ultimaAlteracao
     *            O novo valor de ultimaAlteracao
     */
    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     */
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

	public Short getIndicadorRevisaoPermissaoEspecial() {
		return indicadorRevisaoPermissaoEspecial;
	}

	public void setIndicadorRevisaoPermissaoEspecial(
			Short indicadorRevisaoPermissaoEspecial) {
		this.indicadorRevisaoPermissaoEspecial = indicadorRevisaoPermissaoEspecial;
	}

}
