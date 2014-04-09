package gcom.gui.faturamento.conta;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Inserir Débitos para as contas impressas via Impressão Simultânea de Contas que sairam com o valor da conta errada (Alguns grupos com tarifa proporcional
 *  que não estava levando em consideração a quantidade de economias)
 *
 * @author Sávio Luiz
 * @date 12/01/2011
 */
public class InserirDebitosContasComValorFaixasErradasAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        InserirDebitosContasComValorFaixasErradasActionForm inserirDebitosContasComValorFaixasErradasActionForm = (InserirDebitosContasComValorFaixasErradasActionForm) actionForm;
        
        
        
        Fachada fachada = Fachada.getInstancia();
        
    	Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
    	
    	
    	Integer referenciaConta = Util.formatarMesAnoComBarraParaAnoMes(inserirDebitosContasComValorFaixasErradasActionForm.getReferenciaConta());
    	
    	fachada.inserirDebitosContasComValorFaixasErradas(referenciaConta,usuarioLogado);
    	
    	
    	 montarPaginaSucesso(httpServletRequest, " Inserir Débitos para contas com valores das faixas erradas encaminhado para processamento. ","Voltar","/exibirInserirDebitosContasComValorFaixasErradasAction.do");
        
        
        return retorno;
	}
	
	
	
}
