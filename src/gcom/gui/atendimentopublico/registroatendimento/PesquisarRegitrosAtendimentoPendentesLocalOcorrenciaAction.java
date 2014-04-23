package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.bean.RegistroAtendimentoPendenteLocalOcorrenciaHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela com os registros de 
 * atendimento pendentes para um mesmo local de ocorrência (Aba nº 02 - Dados do local de ocorrência)
 * 
 * @author Raphael Rossiter
 * @date 17/07/2006
 */
public class PesquisarRegitrosAtendimentoPendentesLocalOcorrenciaAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("pesquisarRAsPendentesLocalOcorrencia");

		Fachada fachada = Fachada.getInstancia();
		
		PesquisarRegistrosAtendimentoPendentesLocalOcorrenciaActionForm form = 
		(PesquisarRegistrosAtendimentoPendentesLocalOcorrenciaActionForm) actionForm;

		Integer idEspecificacao = Util.converterStringParaInteger(httpServletRequest.getParameter("idEspecificacao"));
		Integer idLogradouroBairro = Util.converterStringParaInteger(httpServletRequest.getParameter("idLogradouroBairro"));
		Integer idLogradouroCep = Util.converterStringParaInteger(httpServletRequest.getParameter("idLogradouroCep"));
		
		RegistroAtendimentoPendenteLocalOcorrenciaHelper rasPendentes = 
		fachada.pesquisarRAPendenteLocalOcorrencia(idEspecificacao, idLogradouroCep, idLogradouroBairro);
		
		form.setIdSolicitacaoTipo(rasPendentes.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo().getId().toString());
		form.setDescricaoSolicitacaoTipo(rasPendentes.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo().getDescricao());
		form.setIdSolicitacaTipoEspecificacao(rasPendentes.getSolicitacaoTipoEspecificacao().getId().toString());
		form.setDescricaoSolicitacaTipoEspecificacao(rasPendentes.getSolicitacaoTipoEspecificacao().getDescricao());
		
		httpServletRequest.setAttribute("enderecoOcorrencia", rasPendentes.getEnderecoOcorrencia());
		
		httpServletRequest.setAttribute("colecaoRegistroAtendimento", rasPendentes.getColecaoRegistroAtendimento());
		
		return retorno;
	}

}
