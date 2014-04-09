package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para realização da atualização de um R.A (Aba nº 03 - Dados do
 * solicitante)
 * 
 * @author Sávio Luiz
 * @date 10/08/2006
 */
public class ExibirAtualizarRegistroAtendimentoDadosSolicitanteAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("atualizarRegistroAtendimentoDadosSolicitante");

		AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm = (AtualizarRegistroAtendimentoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// caso a escolha seja de remover o registro de atendimento solicitante
		if (httpServletRequest.getParameter("idRASolicitante") != null) {
			// recupera o id do solicitante para ser removido
			long idRASolicitante = Long.parseLong(httpServletRequest
					.getParameter("idRASolicitante"));
			// cria ou recupera a coleção de RA solicitante removidos
			Collection colecaoRASolicitanteRemovidas = null;
			if (sessao.getAttribute("colecaoRASolicitanteRemovidas") != null) {
				colecaoRASolicitanteRemovidas = (Collection) sessao
						.getAttribute("colecaoRASolicitanteRemovidas");
			} else {
				colecaoRASolicitanteRemovidas = new ArrayList();
			}
			// recupera a coleção de RA solicitante
			Collection colecaoRASolicitante = (Collection) sessao
					.getAttribute("colecaoRASolicitante");
			Iterator iteratorRASolicitante = colecaoRASolicitante.iterator();
			while (iteratorRASolicitante.hasNext()) {
				RegistroAtendimentoSolicitante registroAtendimentoSolicitante = (RegistroAtendimentoSolicitante) iteratorRASolicitante
						.next();
				if (registroAtendimentoSolicitante.getUltimaAlteracao()
						.getTime() == idRASolicitante) {
					if (registroAtendimentoSolicitante.getID() != null
							&& !registroAtendimentoSolicitante.getID().equals(
									"")) {
						colecaoRASolicitanteRemovidas
								.add(registroAtendimentoSolicitante);
					}
					iteratorRASolicitante.remove();
					break;
				}
			}
			sessao.setAttribute("colecaoRASolicitanteRemovidas",
					colecaoRASolicitanteRemovidas);
		}

		Fachada fachada = Fachada.getInstancia();
		// caso seja a primeira vez então pesquisa a coleção de solicitante
		if (sessao.getAttribute("colecaoRASolicitante") == null) {

			FiltroRegistroAtendimentoSolicitante filtroRegistroAtendimentoSolicitante = new FiltroRegistroAtendimentoSolicitante();
			
			filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
			filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("funcionario");
			filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
			filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
			filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
			filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
			
			filtroRegistroAtendimentoSolicitante
					.adicionarParametro(new ParametroSimples(
							FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO_ID,
							atualizarRegistroAtendimentoActionForm
									.getNumeroRA()));

			Collection colecaoRASolicitante = fachada.pesquisar(
					filtroRegistroAtendimentoSolicitante,
					RegistroAtendimentoSolicitante.class.getName());

			/*Collection colecaoRASolicitanteComNome = null;

			if (colecaoRASolicitante != null && !colecaoRASolicitante.isEmpty()) {

				Iterator iteRASolicitante = colecaoRASolicitante.iterator();
				colecaoRASolicitanteComNome = new ArrayList();
				while (iteRASolicitante.hasNext()) {
					RegistroAtendimentoSolicitante registroAtendimentoSolicitante = (RegistroAtendimentoSolicitante) iteRASolicitante
							.next();
					String nomeRASolicitante = fachada
							.obterNomeSolicitanteRA(registroAtendimentoSolicitante
									.getID());
					registroAtendimentoSolicitante
							.setSolicitante(nomeRASolicitante);
					colecaoRASolicitanteComNome
							.add(registroAtendimentoSolicitante);
				}
			}*/

			sessao.setAttribute("colecaoRASolicitante",
					colecaoRASolicitante);
		}

		return retorno;
	}
}
