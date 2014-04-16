package gcom.atendimentopublico.registroatendimento;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RaEnderecoDescritivo implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoBairro;

    /** persistent field */
    private gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento;

    /** full constructor */
    public RaEnderecoDescritivo(String descricao, String descricaoBairro, gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento) {
        this.descricao = descricao;
        this.descricaoBairro = descricaoBairro;
        this.registroAtendimento = registroAtendimento;
    }

    /** default constructor */
    public RaEnderecoDescritivo() {
    }

    /** minimal constructor */
    public RaEnderecoDescritivo(gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento) {
        this.registroAtendimento = registroAtendimento;
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

    public String getDescricaoBairro() {
        return this.descricaoBairro;
    }

    public void setDescricaoBairro(String descricaoBairro) {
        this.descricaoBairro = descricaoBairro;
    }

    public gcom.atendimentopublico.registroatendimento.RegistroAtendimento getRegistroAtendimento() {
        return this.registroAtendimento;
    }

    public void setRegistroAtendimento(gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento) {
        this.registroAtendimento = registroAtendimento;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
