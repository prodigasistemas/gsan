package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.ImovelRamoAtividade;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverImovelRamoAtividadeAction extends GcomAction {
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
		ActionForward retorno = actionMapping
				.findForward("inserirImovelSubCategoria");

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		//DynaValidatorForm inserirImovelActionForm = (DynaValidatorForm) actionForm;

		// Cria variaveis
		Collection colecaoImovelRamosAtividades = (Collection) sessao
				.getAttribute("colecaoImovelRamosAtividade");

		Collection colecaoImovelRamosAtividadeRemovidos = new ArrayList();

		ImovelRamoAtividade imovelRamoAtividade = null;

		// Obtém os ids de remoção
		String[] removerImovelRamoAtividade = httpServletRequest.getParameterValues("removerImovelRamoAtividade");

		if (colecaoImovelRamosAtividades != null
				&& !colecaoImovelRamosAtividades.equals("")) {

			Iterator imovelRamosAtividadesIterator = colecaoImovelRamosAtividades.iterator();

			while (imovelRamosAtividadesIterator.hasNext()) {
				imovelRamoAtividade = (ImovelRamoAtividade) imovelRamosAtividadesIterator.next();
				for (int i = 0; i < removerImovelRamoAtividade.length; i++) {
					if (imovelRamoAtividade.getUltimaAlteracao().getTime() == Long
							.parseLong(removerImovelRamoAtividade[i])) {
						if(!(colecaoImovelRamosAtividadeRemovidos.contains(imovelRamoAtividade))){
							colecaoImovelRamosAtividadeRemovidos
									.add(imovelRamoAtividade);
							imovelRamosAtividadesIterator.remove();
						}
					}
				}
			}
			sessao.setAttribute(
					"colecaoImovelRamosAtividadesRemovidos",
					colecaoImovelRamosAtividadeRemovidos);

		}


		return retorno;
	}

}
