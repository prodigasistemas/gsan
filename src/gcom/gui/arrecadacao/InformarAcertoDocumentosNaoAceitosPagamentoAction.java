package gcom.gui.arrecadacao;

import java.math.BigDecimal;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InformarAcertoDocumentosNaoAceitosPagamentoAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//cria a variável que vai armazenar o retorno
    	ActionForward retorno = actionMapping.findForward("informarAcertoDocumentosNaoAceitosDebitos");
    	
    	//cria uma instÂncia da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        InformarAcertoDocumentosNaoAceitosActionForm form = (InformarAcertoDocumentosNaoAceitosActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
        //recupera o status atual do wizard
        StatusWizard statusWizard = (StatusWizard) sessao.getAttribute("statusWizard");
        
        BigDecimal valorPagamento = fachada.pesquisarValorPagamento(
        		new Integer(form.getIdPagamentoSelecionado()));
        
        form.setTotalPagamento(Util.formatarMoedaReal(valorPagamento));
        form.setTotalDebitoSelecionado(Util.formatarMoedaReal(BigDecimal.ZERO));
        form.setTotalDebitos(Util.formatarMoedaReal(BigDecimal.ZERO));

        form.setIdImovel("");
        form.setDescricaoImovel("");
        
		sessao.removeAttribute("colecaoContaValores");
		sessao.removeAttribute("valorConta");
		sessao.removeAttribute("valorAcrescimo");
		sessao.removeAttribute("valorContaAcrescimo");
		sessao.removeAttribute("colecaoDebitoACobrar");
		sessao.removeAttribute("valorDebitoACobrar");							
		sessao.removeAttribute("colecaoCreditoARealizar");
		sessao.removeAttribute("valorCreditoARealizar");
		sessao.removeAttribute("valorCreditoARealizarSemDescontosParcelamento");							
		sessao.removeAttribute("colecaoGuiaPagamentoValores");
		sessao.removeAttribute("valorGuiaPagamento");						
		sessao.removeAttribute("valorTotalDebito");
		sessao.removeAttribute("valorTotalDebitoAtualizado");		
        
        //seta o status do wizard na sessão
        sessao.setAttribute("statusWizard",statusWizard);
        
        //retorna o mapeamento contido na variável retorno
        return retorno;
    }

}
