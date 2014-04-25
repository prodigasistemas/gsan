package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.FiltroNegativadorRegistroTipo;
import gcom.cobranca.NegativadorRegistroTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de Exibir Atualizar Negativador Registro Tipo
 * 
 * @author Yara Taciane
 * @created 08/01/2008
 */

public class ExibirAtualizarNegativadorRegistroTipoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Inicializando Variaveis
		ActionForward retorno = actionMapping.findForward("atualizarNegativadorRegistroTipo");
		AtualizarNegativadorRegistroTipoActionForm atualizarNegativadorRegistroTipoActionForm = (AtualizarNegativadorRegistroTipoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		// volta da msg de Negativador Exclusao Motivo já utilizado, não pode ser
		// alterado nem excluído.
		String confirmado = httpServletRequest.getParameter("confirmado");

		String idNegativadorRegistroTipo = null;
		if (httpServletRequest.getParameter("reload") == null
				|| httpServletRequest.getParameter("reload").equalsIgnoreCase(
						"") && (confirmado == null || confirmado.equals(""))) {
			// Recupera o id do Negativador Exclusao Motivo que vai ser atualizado

			if (httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null) {
				idNegativadorRegistroTipo = httpServletRequest.getParameter("idRegistroInseridoAtualizar");
				// Definindo a volta do botão Voltar p Filtrar Negativador Registro Tipo			
				httpServletRequest.setAttribute("voltar", "filtrar");
				sessao.setAttribute("idRegistroAtualizacao",idNegativadorRegistroTipo);
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") == null) {
				idNegativadorRegistroTipo = (String) sessao.getAttribute("idRegistroAtualizacao");
				// Definindo a volta do botão Voltar p Filtrar Negativador Exclusao Motivo				
				httpServletRequest.setAttribute("voltar", "filtrar");
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				idNegativadorRegistroTipo = httpServletRequest.getParameter("idRegistroAtualizacao");
				// Definindo a volta do botão Voltar p Manter Negativador Exclusao Motivo		
				httpServletRequest.setAttribute("voltar", "manter");
				sessao.setAttribute("idRegistroAtualizacao",idNegativadorRegistroTipo);
			}
		} else {
			idNegativadorRegistroTipo = (String) sessao.getAttribute("idRegistroAtualizacao");
		}

		// Verifica se o usuário está selecionando o Negativador Registro Tipo da
		// página de manter
		// Caso contrário o usuário está teclando enter na página de atualizar
		if ((idNegativadorRegistroTipo != null && !idNegativadorRegistroTipo.equals(""))
				&& (httpServletRequest.getParameter("desfazer") == null)
				&& (httpServletRequest.getParameter("reload") == null || httpServletRequest
						.getParameter("reload").equalsIgnoreCase(""))) {
			exibirNegativadorRegistroTipo(idNegativadorRegistroTipo,
					atualizarNegativadorRegistroTipoActionForm, fachada, sessao,
					httpServletRequest);

		}

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			// -------------- bt DESFAZER ---------------

			exibirNegativadorRegistroTipo(idNegativadorRegistroTipo,
					atualizarNegativadorRegistroTipoActionForm, fachada, sessao,
					httpServletRequest);

		}

		return retorno;

	}

	private void exibirNegativadorRegistroTipo(
			String idNegativadorRegistroTipo,
			AtualizarNegativadorRegistroTipoActionForm atualizarNegativadorRegistroTipoActionForm,
			Fachada fachada, HttpSession sessao,
			HttpServletRequest httpServletRequest) {

		// Cria a variável que vai armazenar o negativador registro tipo para ser
		// atualizado
		NegativadorRegistroTipo negativadorRegistroTipo = null;

		// Cria o filtro de NegativadorRegistroTipo e seta o id do
		// NegativadorRegistroTipo para ser atualizado no filtro
		// e indica quais objetos devem ser retornados pela pesquisa
		FiltroNegativadorRegistroTipo filtroNegativadorRegistroTipo = new FiltroNegativadorRegistroTipo();
		filtroNegativadorRegistroTipo.adicionarParametro(new ParametroSimples(
				FiltroNegativadorRegistroTipo.ID, idNegativadorRegistroTipo));

		filtroNegativadorRegistroTipo.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");

		Collection<NegativadorRegistroTipo> collectionNegativadorRegistroTipo = fachada
				.pesquisar(filtroNegativadorRegistroTipo, NegativadorRegistroTipo.class
						.getName());

		// Caso a pesquisa tenha retornado o NegativadorRegistroTipo
		if (collectionNegativadorRegistroTipo != null
				&& !collectionNegativadorRegistroTipo.isEmpty()) {

			// Recupera da coleção o NegativadorRegistroTipo que vai ser atualizado
			negativadorRegistroTipo = (NegativadorRegistroTipo) Util
					.retonarObjetoDeColecao(collectionNegativadorRegistroTipo);
	
			// Seta no form os dados de NegativadorRegistroTipo
			if (negativadorRegistroTipo.getNegativador() != null
					&& !negativadorRegistroTipo.getNegativador().equals("")) {

				atualizarNegativadorRegistroTipoActionForm.setIdNegativador(""
						+ negativadorRegistroTipo.getNegativador());

				atualizarNegativadorRegistroTipoActionForm.setNegativadorCliente(""
						+ negativadorRegistroTipo.getNegativador().getCliente()
								.getNome());
			} else {
				atualizarNegativadorRegistroTipoActionForm
						.setNegativadorCliente("");
			}
			
			if (negativadorRegistroTipo.getCodigoRegistro() != null
					&& !negativadorRegistroTipo.getCodigoRegistro().equals("")) {

				atualizarNegativadorRegistroTipoActionForm.setCodigoRegistro(""
						+ negativadorRegistroTipo.getCodigoRegistro());

			} else {
				atualizarNegativadorRegistroTipoActionForm
						.setCodigoRegistro("");
			}
			
			if (negativadorRegistroTipo.getDescricaoRegistroTipo() != null
					&& !negativadorRegistroTipo.getDescricaoRegistroTipo().equals("")) {

				atualizarNegativadorRegistroTipoActionForm.setDescricaoRegistroTipo(""
						+ negativadorRegistroTipo.getDescricaoRegistroTipo());

			} else {
				atualizarNegativadorRegistroTipoActionForm
						.setDescricaoRegistroTipo("");
			}			
	
					
			atualizarNegativadorRegistroTipoActionForm.setTime(Long
					.toString(negativadorRegistroTipo.getUltimaAlteracao()
							.getTime()));

			sessao.setAttribute("negativadorRegistroTipo", negativadorRegistroTipo);

		}

	}

}
