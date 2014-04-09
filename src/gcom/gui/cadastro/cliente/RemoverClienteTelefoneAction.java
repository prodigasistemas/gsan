package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.IClienteFone;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class RemoverClienteTelefoneAction extends GcomAction {
	/**
	 * < <Descrição do método>>
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
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da Ação
		ActionForward retorno = null;

		if (httpServletRequest.getParameter("tela").trim().equals("inserir")) {
			retorno = actionMapping.findForward("inserirClienteTelefone");
		} else if (httpServletRequest.getParameter("tela").trim().equals(
				"atualizar")) {
			retorno = actionMapping.findForward("atualizarClienteTelefone");
		}

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;

		Collection colecaoClienteFone = (Collection) sessao
				.getAttribute("colecaoClienteFone");

		ClienteFone clienteFone = null;

		// Obtém os ids de remoção
		String[] ids = (String[]) clienteActionForm.get("idRegistrosRemocao");

		if (ids != null && ids.length != 0) {
			if (colecaoClienteFone != null && !colecaoClienteFone.isEmpty()) {

				Iterator iteratorColecaoClienteFone = colecaoClienteFone
						.iterator();

				while (iteratorColecaoClienteFone.hasNext()) {
					clienteFone = (ClienteFone) iteratorColecaoClienteFone
							.next();
					for (int i = 0; i < ids.length; i++) {
						if (clienteFone.getDddTelefone().equals(ids[i])) {
							// Verifica se o clienteFone removido era o
							// principal para adicionar o indicador de principal
							// para o primeiro da lista
							if (obterTimestampIdObjeto(clienteFone) == (((Long) clienteActionForm
									.get("indicadorTelefonePadrao"))
									.longValue())
									&& colecaoClienteFone.size() > 1) {

								Iterator iteratorTemp = colecaoClienteFone
										.iterator();
								// Pega o primeiro da lista
								IClienteFone clienteFonePrimeiroDaLista = (IClienteFone) iteratorTemp
										.next();

								// Verifica se o primeiro da lista é o mesmo que
								// será removido
								if (clienteFonePrimeiroDaLista
										.equals(clienteFone)) {
									// Seta como principal o segundo da lista
									clienteFonePrimeiroDaLista = (IClienteFone) iteratorTemp
											.next();
								}

								// Seta o indicador no form
								clienteActionForm
										.set(
												"indicadorTelefonePadrao",
												new Long(
														obterTimestampIdObjeto(clienteFonePrimeiroDaLista)));
							}
							iteratorColecaoClienteFone.remove();
						}
					}

				}

			}

			clienteActionForm.set("botaoClicado", null);

			// Se a coleção de telefones tiver apenas um item, então este item
			// tem que estar selecionado
			// como telefone principal
			Iterator iterator = colecaoClienteFone.iterator();

			if (colecaoClienteFone != null && colecaoClienteFone.size() == 1) {

				clienteFone = (ClienteFone) iterator.next();

				clienteActionForm.set("indicadorTelefonePadrao", new Long(
						obterTimestampIdObjeto(clienteFone)));

			}
		}
		return retorno;
	}
}
