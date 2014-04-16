package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.bean.RegistroAtendimentoFaltaAguaGeneralizadaHelper;
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
 * atendimento pendentes para falta de água generalizada
 * 
 * @author Raphael Rossiter
 * @date 17/07/2006
 */
public class PesquisarRegistrosAtendimentoPendentesFaltaAguaEncerrarAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("pesquisarRegistrosAtendimentoPendentesFaltaAguaEncerrar");

		Fachada fachada = Fachada.getInstancia();
		
		PesquisarRegistrosAtendimentoPendentesFaltaAguaEncerrarActionForm form = 
		(PesquisarRegistrosAtendimentoPendentesFaltaAguaEncerrarActionForm) actionForm;

		Integer idEspecificacao = Util.converterStringParaInteger(httpServletRequest.getParameter("idEspecificacao"));
		Integer idBairroArea = Util.converterStringParaInteger(httpServletRequest.getParameter("idBairroArea"));
		
		RegistroAtendimentoFaltaAguaGeneralizadaHelper rasFaltaAguaGenerealizada = 
		fachada.verificarRegistroAtendimentoFaltaAguaGeneralizafa(idEspecificacao, idBairroArea);
		
		form.setIdSolicitacaoTipo(rasFaltaAguaGenerealizada.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo().getId().toString());
		form.setDescricaoSolicitacaoTipo(rasFaltaAguaGenerealizada.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo().getDescricao());
		form.setIdSolicitacaoTipoEspecificacao(rasFaltaAguaGenerealizada.getSolicitacaoTipoEspecificacao().getId().toString());
		form.setDescricaoSolicitacaoTipoEspecificacao(rasFaltaAguaGenerealizada.getSolicitacaoTipoEspecificacao().getDescricao());
		form.setCodigoBairro(String.valueOf(rasFaltaAguaGenerealizada.getBairroArea().getBairro().getCodigo()));
		form.setDescricaoBairro(rasFaltaAguaGenerealizada.getBairroArea().getBairro().getNome());
		form.setIdBairroArea(rasFaltaAguaGenerealizada.getBairroArea().getId().toString());
		form.setDescricaoBairroArea(rasFaltaAguaGenerealizada.getBairroArea().getNome());
		
		
		httpServletRequest.setAttribute("colecaoRegistroAtendimento", rasFaltaAguaGenerealizada.getColecaoRegistroAtendimento());
		
		return retorno;
	}

}
