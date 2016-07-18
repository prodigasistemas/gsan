package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.ArrayList;
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
 * Action que remove o objeto selecionado de cliente imovel
 * 
 * @author Administrador
 * @created 20 de Maio de 2004
 */
public class RemoverInserirImovelClienteAction extends GcomAction {

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
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("inserirImovelCliente");

		// obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm inserirImovelClienteActionForm = (DynaValidatorForm) actionForm;

		// Cria variaveis
		Collection imovelClientesNovos = (Collection) sessao.getAttribute("imovelClientesNovos");

		// atribui os valores q vem pelo request as variaveis
		String[] clientesImoveis = httpServletRequest.getParameterValues("idRemocaoClienteImovel");

		String verificaAtualizar = httpServletRequest.getParameter("atualizar");

		Imovel imovel = null;

		if (verificaAtualizar != null && verificaAtualizar.trim().equalsIgnoreCase("1")) {
			retorno = actionMapping.findForward("atualizarImovelCliente");
			imovel = (Imovel) sessao.getAttribute("imovelAtualizacao");
		}

		// instancia cliente
		ClienteImovel clienteImovel = null;

		if (imovelClientesNovos != null && !imovelClientesNovos.equals("")) {

			Iterator clienteImovelIterator = imovelClientesNovos.iterator();

			Collection colecaoClientesImoveisRemovidos = new ArrayList();

			while (clienteImovelIterator.hasNext()) {
				clienteImovel = (ClienteImovel) clienteImovelIterator.next();
				for (int i = 0; i < clientesImoveis.length; i++) {
					if (clienteImovel.getUltimaAlteracao().getTime() == Long.parseLong(clientesImoveis[i])) {

						if (imovel == null) {

							colecaoClientesImoveisRemovidos.add(clienteImovel);
							clienteImovelIterator.remove();

							if (clienteImovel.isClienteUsuario()) {
								inserirImovelClienteActionForm.set("idClienteImovelUsuario", null);
								sessao.removeAttribute("idClienteImovelUsuario");
							}
							if (clienteImovel.isClienteResponsavel()) {
								inserirImovelClienteActionForm.set("idClienteImovelResponsavel", null);
								sessao.removeAttribute("idClienteImovelResponsavel");
							}
						} else {
							if (!imovel.getImovelPerfil().getId().equals(ConstantesSistema.INDICADOR_TARIFA_SOCIAL)) {
								clienteImovelIterator.remove();
								inserirImovelClienteActionForm.set("idClienteImovelUsuario", null);
							}
						}
					}
				}
			}
			sessao.setAttribute("colecaoClientesImoveisRemovidos", colecaoClientesImoveisRemovidos);
		}

		return retorno;
	}
}