package gcom.gui;

import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Controlador ActionForm 
 * 
 * @author thiago
 * @created 20/12/2005
 */ 
public class ControladorGcomActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String acao = null;

	private Collection collObjeto = null;

	private String caminhoFuncionalidade;
	private String labelPaginaSucesso;
	private String mensagemPaginaSucesso;
	

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    	this.collObjeto = new Vector();
    	this.caminhoFuncionalidade = "";
    	this.labelPaginaSucesso = "";
    	this.mensagemPaginaSucesso = "";    	
    }

    public ObjetoTransmissaoDados getOTD(HttpServletRequest request) {
    	return (ObjetoTransmissaoDados) request.getSession(false).getAttribute(ObjetoTransmissaoDados.OBJETO_TRANSMISSAO_DADOS);
    }

    public void resetOTD(HttpServletRequest request) {
    	request.setAttribute(ObjetoTransmissaoDados.OBJETO_TRANSMISSAO_DADOS,null);
    }

	public String getCaminhoFuncionalidade() {
		return caminhoFuncionalidade;
	}

	public void setCaminhoFuncionalidade(String caminhoFuncionalidade) {
		this.caminhoFuncionalidade = caminhoFuncionalidade;
	}

	public String getLabelPaginaSucesso() {
		return labelPaginaSucesso;
	}

	public void setLabelPaginaSucesso(String labelPaginaSucesso) {
		this.labelPaginaSucesso = labelPaginaSucesso;
	}
	public String getMensagemPaginaSucesso() {
		return mensagemPaginaSucesso;
	}

	public void setMensagemPaginaSucesso(String mensagemPaginaSucesso) {
		this.mensagemPaginaSucesso = mensagemPaginaSucesso;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Collection getCollObjeto() {
		return collObjeto;
	}

	public void setCollObjeto(Collection collObjeto) {
		this.collObjeto = collObjeto;
	}
}
