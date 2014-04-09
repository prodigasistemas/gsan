package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de exibir atualizar o aviso bancario
 *
 * @author thiago toscano
 * @date 10/03/2006
 */
public class ExibirPesquisarAvisoAcertoAction extends GcomAction {

    /**
     * Método responsavel por responder a requisicao
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse httpServletResponse) {

    	
        ActionForward retorno = actionMapping.findForward("exibirAvisoBancarioAcertoPopup");
        
        HttpSession sessao = request.getSession(false);
        
        AvisoBancario avisoBancario = (AvisoBancario)sessao.getAttribute("avisoBancario");
        
        String idContaBancaria = avisoBancario.getContaBancaria().getId().toString();
        
        String nomeBanco = avisoBancario.getContaBancaria().getAgencia().getBanco().getId().toString();
        
        String nomeAgencia = avisoBancario.getContaBancaria().getAgencia().getCodigoAgencia();
        
        String numeroContaBancaria = avisoBancario.getContaBancaria().getNumeroConta();
        
        request.setAttribute("idContaBancaria", idContaBancaria);
        request.setAttribute("nomeBanco", nomeBanco);
        request.setAttribute("nomeAgencia", nomeAgencia);
        request.setAttribute("numeroContaBancaria", numeroContaBancaria);
        
        //AvisoBancarioActionForm form = (AvisoBancarioActionForm) actionForm;
        
        /*if(form.getDataAcerto() != null && !form.getDataAcerto().equalsIgnoreCase("")){
        	String dataRealizacaoAcerto = form.getDataAcerto();
        	if (Util.converteStringParaDate(dataRealizacaoAcerto).getTime() > new Date(System.currentTimeMillis()).getTime()) {
        		throw new ActionServletException("atencao.avisoBancario.data_realizacao_acerto_menor_que_hoje",null,Util.formatarData(new Date(System.currentTimeMillis())));
        	}else{
        		request.setAttribute("fecharPopup","1");
        	}
        }*/
       request.getSession(false).removeAttribute("tipoPesquisa");
        return retorno;
    }
}
