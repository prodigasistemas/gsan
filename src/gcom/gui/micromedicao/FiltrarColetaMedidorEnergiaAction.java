package gcom.gui.micromedicao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1000] Informar Medidor de Energia por Rota
 * 
 * @author Hugo Leonardo
 *
 * @date 10/03/2010
 * 
 */

public class FiltrarColetaMedidorEnergiaAction extends Action {
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarColetaMedidorEnergia");

		FiltrarColetaMedidorEnergiaActionForm form = (FiltrarColetaMedidorEnergiaActionForm) actionForm;
		ColetaMedidorEnergiaHelper helper = new ColetaMedidorEnergiaHelper();

		// Localidade
		if(form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")){
			helper.setLocalidadeId(form.getIdLocalidade());
		}
		
		// Setor Comercial
		if(form.getCodigoSetorComercial() != null && !form.getCodigoSetorComercial().equals("")){
			helper.setSetorComercialId(form.getCodigoSetorComercial());
		}
		
		// ROTA
		if(form.getRota() != null && !form.getRota().equals("")){
			helper.setRotaId(form.getRota());
		}
		
		// Tipo: 1- Manter; 2- Inserir
		if(form.getTipo() != null && !form.getTipo().equals("")){
			helper.setTipo(form.getTipo());
		}

		//Manda a Collection pelo request para o
		// Faturamento Imediato Ajuste
		httpServletRequest.setAttribute("helper", helper);

		//Devolve o mapeamento de retorno
		return retorno;
	}
}
