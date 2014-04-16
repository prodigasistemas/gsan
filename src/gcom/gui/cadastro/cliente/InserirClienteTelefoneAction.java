package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.ClienteFone;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

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
 * Description of the Class
 * 
 * @author Rodrigo
 */
public class InserirClienteTelefoneAction extends GcomAction {

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
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("gerenciadorProcesso");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;
		
		Collection colecaoFones = (Collection) sessao
				.getAttribute("colecaoClienteFone");
		
		clienteActionForm.set("botaoAdicionar", "");
		clienteActionForm.set("botaoClicado", "");
		clienteActionForm.set("ddd", "");
		clienteActionForm.set("telefone", "");
		clienteActionForm.set("idTipoTelefone", "");
		clienteActionForm.set("idMunicipio", "");
		clienteActionForm.set("ramal", "");
		clienteActionForm.set("contato", "" );
		clienteActionForm.set("descricaoMunicipio", "");


		if (colecaoFones != null && !colecaoFones.isEmpty()) {

			// Radio que indica qual o telefone principal
			Long indicadorTelefonePadrao = (Long) clienteActionForm
					.get("indicadorTelefonePadrao");

			// Se o telefone padrão for escolhido então o objeto deve ser
			// alterado
			if (indicadorTelefonePadrao != null
					&& indicadorTelefonePadrao.longValue() > 0) {
				Iterator iterator = colecaoFones.iterator();

				// Varre a colecão para descobrir o objeto que tem o telefone
				// principal
				while (iterator.hasNext()) {
					ClienteFone clienteFone = (ClienteFone) iterator.next();

					if (obterTimestampIdObjeto(clienteFone) == indicadorTelefonePadrao
							.longValue()) {
						// Indica que o objeto possui o telefone principal
						clienteFone
								.setIndicadorTelefonePadrao(ConstantesSistema.INDICADOR_TELEFONE_PRINCIPAL);
					} else {
						// Indica que o objeto não possui o telefone principal
						clienteFone
								.setIndicadorTelefonePadrao(ConstantesSistema.INDICADOR_NAO_TELEFONE_PRINCIPAL);
					}
				}

			} else {
				// Nenhum telefone foi indicado como principal
				// Mostra o erro
				throw new ActionServletException(
						"atencao.telefone_principal.nao_selecionado");
			}

		}
		return retorno;
	}
}
