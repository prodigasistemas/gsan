package gcom.util.tabelaauxiliar;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public abstract class TabelaAuxiliar extends TabelaAuxiliarAbstrata {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * identifier field
     */
    protected String descricao;

    /**
     * full constructor
     * 
     * @param codigo
     *            Descrição do parâmetro
     * @param descricao
     *            Descrição do parâmetro
     */
    public TabelaAuxiliar(Integer id, String descricao, Short indicadorUso,
            Date ultimaAlteracao) {
        super.setId(id);
        super.setIndicadorUso(indicadorUso);
        super.setUltimaAlteracao(ultimaAlteracao);
        this.descricao = descricao;
    }

    /**
     * default constructor
     */
    public TabelaAuxiliar() {
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
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     */
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).append(
                "descricao", getDescricao()).append("indicadorUso",
                getIndicadorUso()).toString();
    }

    /**
     * < <Descrição do método>>
     * 
     * @param other
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof TabelaAuxiliar)) {
            return false;
        }
        TabelaAuxiliar castOther = (TabelaAuxiliar) other;

        return new EqualsBuilder().append(this.getId(), castOther.getId())
                .append(this.getDescricao(), castOther.getDescricao()).append(
                        this.getIndicadorUso(), castOther.getIndicadorUso())
                .isEquals();
    }

    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).append(getDescricao())
                .toHashCode();
    }

}
