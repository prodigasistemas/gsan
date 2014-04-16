package gcom.gui.util.tabelaauxiliar.faixa;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class TabelaAuxiliarFaixaActionForm extends ValidatorActionForm {
    private String faixaInicial;
    private static final long serialVersionUID = 1L;
    private String faixaFinal;

    /**
     * Retorna o valor de faixaFinal
     * 
     * @return O valor de faixaFinal
     */
    public String getFaixaFinal() {
        return faixaFinal;
    }

    /**
     * Seta o valor de faixaFinal
     * 
     * @param faixaFinal
     *            O novo valor de faixaFinal
     */
    public void setFaixaFinal(String faixaFinal) {
        this.faixaFinal = faixaFinal;
    }

    /**
     * Retorna o valor de faixaInicial
     * 
     * @return O valor de faixaInicial
     */
    public String getFaixaInicial() {
        return faixaInicial;
    }

    /**
     * Seta o valor de faixaInicial
     * 
     * @param faixaInicial
     *            O novo valor de faixaInicial
     */
    public void setFaixaInicial(String faixaInicial) {
        this.faixaInicial = faixaInicial;
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }
}
