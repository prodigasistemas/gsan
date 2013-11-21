package gcom.gui.cobranca.contratoparcelamento;

import gcom.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoCliente;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de Manter para Contrato pro Cliente
 * 
 * @author Paulo Diniz
 * @created 01/05/2011
 */
public class ExibirManterContratoParcelamentoClienteAction extends GcomAction {

	Fachada fachada = Fachada.getInstancia();
	
	/**
	 * [UC1137] Manter para Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 01/05/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	//Seta o retorno
        ActionForward retorno = actionMapping.findForward("exibirManter");
        HttpSession sessao = httpServletRequest.getSession(false);
        FiltroContratoParcelamentoCliente filtroContratoParcelamentoCliente = (FiltroContratoParcelamentoCliente) sessao.getAttribute("filtroContratoParcelamentoCliente");
        Integer totalRegistros = 0;
        totalRegistros = this.getFachada().totalRegistrosPesquisa(filtroContratoParcelamentoCliente,ContratoParcelamentoCliente.class.getName());
        retorno = this.controlarPaginacao(httpServletRequest, retorno,	totalRegistros);
        Collection<ContratoParcelamentoCliente> coll = this.getFachada().pesquisar(filtroContratoParcelamentoCliente, (Integer)httpServletRequest
				.getAttribute("page.offset") - 1, ContratoParcelamentoCliente.class.getName());
		sessao.setAttribute("collectionContratoParcelamentoCliente", coll);
		sessao.setAttribute("filtroContratoParcelamentoCliente", filtroContratoParcelamentoCliente);
        return retorno;
    }
    
}
