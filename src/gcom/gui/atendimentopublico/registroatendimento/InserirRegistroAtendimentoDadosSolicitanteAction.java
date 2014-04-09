package gcom.gui.atendimentopublico.registroatendimento;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

/**
 * Esta classe tem por finalidade validar as informações da terceira aba do processo de inserção
 * de um registro de atendimento
 *
 * @author Raphael Rossiter
 * @date 25/07/2006
 */
public class InserirRegistroAtendimentoDadosSolicitanteAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("");
        
        Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("gis");	
		
		InserirRegistroAtendimentoActionForm form = 
	    (InserirRegistroAtendimentoActionForm) actionForm;
		
		String nomeSolicitante = null;
		if (form.getNomeSolicitante() != null && 
			!form.getNomeSolicitante().equalsIgnoreCase("")){
			nomeSolicitante = form.getNomeSolicitante();
		}
		
		Collection colecaoEndereco = null;
		if (sessao.getAttribute("colecaoEnderecosAbaSolicitante") != null){
			colecaoEndereco = (Collection) sessao.getAttribute("colecaoEnderecosAbaSolicitante");
		}
		
		Collection colecaoFone = null;
		if (sessao.getAttribute("colecaoFonesAbaSolicitante") != null){
			colecaoFone = (Collection) sessao.getAttribute("colecaoFonesAbaSolicitante");
		}
		
		Short indicadorClienteEspecificacao = null;
		if (form.getIndicadorClienteEspecificacao() != null && 
			!form.getIndicadorClienteEspecificacao().equalsIgnoreCase("")){
			indicadorClienteEspecificacao = new Short(form.getIndicadorClienteEspecificacao());
		}
		
		//[FS0030] - Verificar preenchimento dos dados de identificação do solicitante
		fachada.verificaDadosSolicitante(Util.converterStringParaInteger(form.getIdCliente()), 
		Util.converterStringParaInteger(form.getIdUnidadeSolicitante()),
		Util.converterStringParaInteger(form.getIdFuncionarioResponsavel()), nomeSolicitante,
		colecaoEndereco, colecaoFone, indicadorClienteEspecificacao, 
		Util.converterStringParaInteger(form.getIdImovel()), null,
		Util.converterStringParaInteger(form.getEspecificacao()),
		Util.converterStringParaInteger(form.getMeioSolicitacao()) );
		
        return retorno;
	}

}
