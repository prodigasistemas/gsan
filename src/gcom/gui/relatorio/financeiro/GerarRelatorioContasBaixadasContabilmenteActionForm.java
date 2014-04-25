package gcom.gui.relatorio.financeiro;

import org.apache.struts.action.ActionForm;

/**
 * [UC0726] Gerar Relatório das Contas Baixadas Contabilmente
 * 
 * @author Vivianne Sousa
 * @data 08/04/2008
 */
public class GerarRelatorioContasBaixadasContabilmenteActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
    
    private String tipo;
    private String periodicidade;
    private String referenciaInicial;
    private String referenciaFinal;
    private String tipoFormato;
    
//	public void reset(ActionMapping actionMapping,
//			HttpServletRequest httpServletRequest) {
//        tipo = null;
//        periodicidade = null;
//        referenciaInicial = null;
//        referenciaFinal = null;
//	}

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public String getReferenciaFinal() {
        return referenciaFinal;
    }

    public void setReferenciaFinal(String referenciaFinal) {
        this.referenciaFinal = referenciaFinal;
    }

    public String getReferenciaInicial() {
        return referenciaInicial;
    }

    public void setReferenciaInicial(String referenciaInicial) {
        this.referenciaInicial = referenciaInicial;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

	public String getTipoFormato() {
		return tipoFormato;
	}

	public void setTipoFormato(String tipoFormato) {
		this.tipoFormato = tipoFormato;
	}
	
    
}
