package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.NegativadorMovimentoReg;

import java.io.Serializable;

/** 
 * @author Vivianne Sousa
 * @created 27/08/2009
 */

public class RelatorioAcompanhamentoClientesNegativadosHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Short indicadorExcluidoNgim;
	private NegativadorMovimentoReg negativadorMovimentoReg;
	
	public Short getIndicadorExcluidoNgim() {
		return indicadorExcluidoNgim;
	}
	public void setIndicadorExcluidoNgim(Short indicadorExcluidoNgim) {
		this.indicadorExcluidoNgim = indicadorExcluidoNgim;
	}
	public NegativadorMovimentoReg getNegativadorMovimentoReg() {
		return negativadorMovimentoReg;
	}
	public void setNegativadorMovimentoReg(
			NegativadorMovimentoReg negativadorMovimentoReg) {
		this.negativadorMovimentoReg = negativadorMovimentoReg;
	}  
	
    public RelatorioAcompanhamentoClientesNegativadosHelper(
    		Short indicadorExcluidoNgim, 
    		NegativadorMovimentoReg negativadorMovimentoReg ) {
        this.indicadorExcluidoNgim = indicadorExcluidoNgim;
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    /** default constructor */
    public RelatorioAcompanhamentoClientesNegativadosHelper() {
    }
	
}
