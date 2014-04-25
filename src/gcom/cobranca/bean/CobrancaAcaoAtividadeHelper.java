package gcom.cobranca.bean;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAtividade;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * [UC0312] Inserir Cromograma de Cobrança
 * 
 * @author Sávio Luiz
 * @date 04/07/2007
 */
public class CobrancaAcaoAtividadeHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	private CobrancaAcao cobrancaAcao;

	private CobrancaAtividade cobrancaAtividade;

	public CobrancaAcao getCobrancaAcao() {
		return cobrancaAcao;
	}

	public void setCobrancaAcao(CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

	public CobrancaAtividade getCobrancaAtividade() {
		return cobrancaAtividade;
	}

	public void setCobrancaAtividade(CobrancaAtividade cobrancaAtividade) {
		this.cobrancaAtividade = cobrancaAtividade;
	}

	
	/**
	 * Description of the Method
	 * 
	 * @param other
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof CobrancaAcaoAtividadeHelper)) {
			return false;
		}
		CobrancaAcaoAtividadeHelper castOther = (CobrancaAcaoAtividadeHelper) other;

		return new EqualsBuilder().append(this.getCobrancaAcao().getId(),
				castOther.getCobrancaAcao().getId()).append(
				this.getCobrancaAtividade().getId(),
				castOther.getCobrancaAtividade().getId()).isEquals();
	}
	
	
    public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getCobrancaAcao().getId())
            .append(this.getCobrancaAtividade().getId())
            .toHashCode();
    }

}
