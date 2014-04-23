package gcom.gui.financeiro;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 28 de Junho de 2004
 */
public class GerarIntegracaoContabilidadeActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idLacamentoOrigem;

    private String dataLancamento;
    
    private String descricao;

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        idLacamentoOrigem = null;
        dataLancamento = null;
        descricao = null;
    }

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getIdLacamentoOrigem() {
		return idLacamentoOrigem;
	}

	public void setIdLacamentoOrigem(String idLacamentoOrigem) {
		this.idLacamentoOrigem = idLacamentoOrigem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
