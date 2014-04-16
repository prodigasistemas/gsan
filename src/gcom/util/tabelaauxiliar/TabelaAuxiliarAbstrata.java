package gcom.util.tabelaauxiliar;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Classe abstrata que serve como base para todas as tabelas auxiliares do
 * sistema. Classes abstratas não podem ser instanciadas.
 * 
 * @author Administrador
 */
public abstract class TabelaAuxiliarAbstrata extends ObjetoTransacao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     * De acordo com o manual do hibernate o id deve ser do tipo Integer
     */
    protected Integer id;

    /**
     * Description of the Field
     */
    protected Short indicadorUso;

    /**
     * persistent field
     */
    protected Date ultimaAlteracao;

    /**
     * Construtor da classe TabelaAuxiliarAbstrata
     * 
     * @param id
     *            Descrição do parâmetro
     * @param indicadorUso
     *            Descrição do parâmetro
     * @param ultimaAlteracao
     *            Descrição do parâmetro
     */
    public TabelaAuxiliarAbstrata(Integer id, Short indicadorUso,
            Date ultimaAlteracao) {
        this.id = id;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * default constructor
     */
    public TabelaAuxiliarAbstrata() {
    }

    /**
     * Retorna o valor de codigo
     * 
     * @return O valor de codigo
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Seta o valor de codigo
     * 
     * @param id
     *            O novo valor de id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o valor de indicadorUso
     * 
     * @return O valor de indicadorUso
     */
    public Short getIndicadorUso() {
        return indicadorUso;
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
        return ultimaAlteracao;
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
        if (!(other instanceof TabelaAuxiliarAbstrata)) {
            return false;
        }
        TabelaAuxiliarAbstrata castOther = (TabelaAuxiliarAbstrata) other;

        return new EqualsBuilder().append(this.getId(), castOther.getId())
                .isEquals();
    }

    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

	@Override
	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroTabelaAuxiliar();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroTabelaAuxiliar.ID, this.getId()));
		return filtro;
	}
	
	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
	}
}
