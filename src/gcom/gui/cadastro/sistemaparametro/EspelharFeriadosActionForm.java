package gcom.gui.cadastro.sistemaparametro;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0535]MANTER FERIADO
 * [SB0003] Criar espelhos dos feriados existentes
 * 
 * @author Bruno Barros
 * @date 12/01/2009
 */
public class EspelharFeriadosActionForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;
    
	private String indicadorTipoFeriado;
    private String anoOrigemFeriado;
    private String anoDestinoFeriado;
    
    public String getAnoDestinoFeriado() {
        return anoDestinoFeriado;
    }
    
    public void setAnoDestinoFeriado(String anoDestinoFeriado) {
        this.anoDestinoFeriado = anoDestinoFeriado;
    }
    
    public String getAnoOrigemFeriado() {
        return anoOrigemFeriado;
    }
    
    public void setAnoOrigemFeriado(String anoOrigemFeriado) {
        this.anoOrigemFeriado = anoOrigemFeriado;
    }

    
    public String getIndicadorTipoFeriado() {
        return indicadorTipoFeriado;
    }

    
    public void setIndicadorTipoFeriado(String indicadorTipoFeriado) {
        this.indicadorTipoFeriado = indicadorTipoFeriado;
    }

}
