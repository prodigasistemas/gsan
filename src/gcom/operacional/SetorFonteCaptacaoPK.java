package gcom.operacional;

import gcom.cadastro.localidade.SetorComercial;
import gcom.interceptor.ObjetoGcom;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class SetorFonteCaptacaoPK extends ObjetoGcom{
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private SetorComercial setorComercial;

    /** identifier field */
    private FonteCaptacao fonteCaptacao;

    /** full constructor */
    public SetorFonteCaptacaoPK(SetorComercial setorComercial,
    		FonteCaptacao fonteCaptacao) {
    	
        this.setorComercial = setorComercial;
        this.fonteCaptacao = fonteCaptacao;
    }

    /** default constructor */
    public SetorFonteCaptacaoPK() {
    }


    public FonteCaptacao getFonteCaptacao() {
		return fonteCaptacao;
	}

	public void setFonteCaptacao(FonteCaptacao fonteCaptacao) {
		this.fonteCaptacao = fonteCaptacao;
	}

	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String toString() {
        return new ToStringBuilder(this).
        	append("setorComercial", getSetorComercial()).
        	append("fonteCaptacao", getFonteCaptacao()).toString();
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if (!(other instanceof SetorFonteCaptacaoPK))
            return false;
        SetorFonteCaptacaoPK castOther = (SetorFonteCaptacaoPK) other;
        
        return new EqualsBuilder().
        	append(this.getSetorComercial(),castOther.getSetorComercial()).
        	append(this.getFonteCaptacao(),castOther.getFonteCaptacao()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getSetorComercial()).append(
        		getFonteCaptacao()).toHashCode();
    }

	@Override
	public String[] retornaCamposChavePrimaria() {
 		String[] retorno = new String[2];
 		retorno[0] = "setorComercial";
 		retorno[1] = "fonteCaptacao";
 		return retorno;		
	}

}
