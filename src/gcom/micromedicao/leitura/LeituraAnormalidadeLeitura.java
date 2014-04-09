package gcom.micromedicao.leitura;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 */
public class LeituraAnormalidadeLeitura implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * identifier field
     */
    private Integer id;

    /**
     * nullable persistent field
     */
    private String descricaoFaturamento;

    /**
     * nullable persistent field
     */
    private Short indicadorSemLeitura;

    /**
     * nullable persistent field
     */
    private Short indicadorComLeitura;

    /**
     * nullable persistent field
     */
    private Short indicadorUso;

    /**
     * nullable persistent field
     */
    private Date ultimaAlteracao;

    //--CONSTANTES
    /**
     * Description of the Field
     */
    public final static Integer ANTERIOR_MAIS_MEDIA = new Integer(0);

    /**
     * Description of the Field
     */
    public final static Integer ANTERIOR = new Integer(1);

    /**
     * Description of the Field
     */
    public final static Integer ANTERIOR_MAIS_CONSUMO = new Integer(2);

    /**
     * Description of the Field
     */
    public final static Integer INFORMADA = new Integer(3);
    
    public final static Integer NORMAL = new Integer(4);

    /**
     * full constructor
     * 
     * @param descricaoFaturamento
     *            Descrição do parâmetro
     * @param indicadorSemLeitura
     *            Descrição do parâmetro
     * @param indicadorComLeitura
     *            Descrição do parâmetro
     * @param indicadorUso
     *            Descrição do parâmetro
     * @param ultimaAlteracao
     *            Descrição do parâmetro
     */
    public LeituraAnormalidadeLeitura(String descricaoFaturamento,
            Short indicadorSemLeitura, Short indicadorComLeitura,
            Short indicadorUso, Date ultimaAlteracao) {
        this.descricaoFaturamento = descricaoFaturamento;
        this.indicadorSemLeitura = indicadorSemLeitura;
        this.indicadorComLeitura = indicadorComLeitura;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * default constructor
     */
    public LeituraAnormalidadeLeitura() {
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
     * Retorna o valor de descricaoFaturamento
     * 
     * @return O valor de descricaoFaturamento
     */
    public String getDescricaoFaturamento() {
        return this.descricaoFaturamento;
    }

    /**
     * Seta o valor de descricaoFaturamento
     * 
     * @param descricaoFaturamento
     *            O novo valor de descricaoFaturamento
     */
    public void setDescricaoFaturamento(String descricaoFaturamento) {
        this.descricaoFaturamento = descricaoFaturamento;
    }

    /**
     * Retorna o valor de indicadorSemLeitura
     * 
     * @return O valor de indicadorSemLeitura
     */
    public Short getIndicadorSemLeitura() {
        return this.indicadorSemLeitura;
    }

    /**
     * Seta o valor de indicadorSemLeitura
     * 
     * @param indicadorSemLeitura
     *            O novo valor de indicadorSemLeitura
     */
    public void setIndicadorSemLeitura(Short indicadorSemLeitura) {
        this.indicadorSemLeitura = indicadorSemLeitura;
    }

    /**
     * Retorna o valor de indicadorComLeitura
     * 
     * @return O valor de indicadorComLeitura
     */
    public Short getIndicadorComLeitura() {
        return this.indicadorComLeitura;
    }

    /**
     * Seta o valor de indicadorComLeitura
     * 
     * @param indicadorComLeitura
     *            O novo valor de indicadorComLeitura
     */
    public void setIndicadorComLeitura(Short indicadorComLeitura) {
        this.indicadorComLeitura = indicadorComLeitura;
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

}
