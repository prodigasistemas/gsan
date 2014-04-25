package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.bean.ExibirRAFaltaAguaImovelHelper;
import gcom.atendimentopublico.registroatendimento.bean.RAFaltaAguaPendenteHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela com os registros
 * de atendimento de falta de água no imóvel da mesma área do bairro (Aba nº 02 -
 * Dados do local de ocorrência)
 * 
 * @author Sávio Luiz
 * @date 17/07/2006
 */
public class PesquisarRegitrosAtendimentoFaltaAguaImovelLocalOcorrenciaAction
		extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("pesquisarRAsFaltaAguaOcorrencia");

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm = (AtualizarRegistroAtendimentoActionForm) actionForm;

		Integer idRegistroAtendimento = new Integer(
				atualizarRegistroAtendimentoActionForm.getNumeroRA());

		Integer idBairroArea = new Integer(
				atualizarRegistroAtendimentoActionForm.getIdBairroArea());

		Integer idEspecificacao = new Integer(
				atualizarRegistroAtendimentoActionForm.getEspecificacao());

		RAFaltaAguaPendenteHelper rAFaltaAguaPendenteHelper = fachada
				.carregarObjetoRAFaltaAguaPendente(idRegistroAtendimento,
						idBairroArea, idEspecificacao);

		if (rAFaltaAguaPendenteHelper.getColecaoExibirRAFaltaAguaHelper() != null
				&& !rAFaltaAguaPendenteHelper
						.getColecaoExibirRAFaltaAguaHelper().isEmpty()) {
			Iterator iter = rAFaltaAguaPendenteHelper
					.getColecaoExibirRAFaltaAguaHelper().iterator();
			while (iter.hasNext()) {
				ExibirRAFaltaAguaImovelHelper exibirRAFaltaAguaImovelHelper = (ExibirRAFaltaAguaImovelHelper) iter
						.next();
				String enderecoOcorrencia = fachada
						.obterEnderecoOcorrenciaRA(exibirRAFaltaAguaImovelHelper
								.getIdRA());
				exibirRAFaltaAguaImovelHelper
						.setEnderecoOcorrencia(enderecoOcorrencia);
			}
		}

		sessao.setAttribute("rAFaltaAguaPendenteHelper",
				rAFaltaAguaPendenteHelper);

		return retorno;
	}

}
