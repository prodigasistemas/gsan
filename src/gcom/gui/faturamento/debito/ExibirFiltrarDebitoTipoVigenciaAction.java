package gcom.gui.faturamento.debito;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Josenildo Neves
 * @date 22/02/2010
 */
public class ExibirFiltrarDebitoTipoVigenciaAction extends GcomAction {

	/**
	 * [UC0984] Filtrar tipo de débito vigência
	 * 
	 * Este caso de uso cria um filtro que será usado na pesquisa do Debito Tipo Vigencia
	 * 
	 * @author Josenildo Neves - Hugo Leonardo
	 * @date 22/02/2010 - 16/04/2010
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarDebitoTipoVigencia");

		FiltrarDebitoTipoVigenciaActionForm filtrarDebitoTipoVigenciaActionForm = (FiltrarDebitoTipoVigenciaActionForm) actionForm;

		filtrarDebitoTipoVigenciaActionForm.setAtualizar("1");

		if (filtrarDebitoTipoVigenciaActionForm.getDebitoTipo() != null
				&& !filtrarDebitoTipoVigenciaActionForm.getDebitoTipo()
						.equals("")) {
			this.getDebitoTipo(filtrarDebitoTipoVigenciaActionForm,
					httpServletRequest);
		}

		return retorno;
	}

	/**
	 * Recupera Debito Tipo
	 * 
	 * [FS0001] - Verificar Debito Tipo
	 * 
	 * @author Josenildo Neves - Hugo Leonardo
	 * @date 22/02/2010 - 16/04/2010
	 * 
	 * @param filtrarDebitoTipoVigenciaActionForm
	 * @param fachada
	 */
	private void getDebitoTipo(FiltrarDebitoTipoVigenciaActionForm form,
			HttpServletRequest httpServletRequest) {
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Filtra Tipo de Serviço
		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
		
		filtroDebitoTipo.adicionarParametro(new ParametroSimples(
				FiltroDebitoTipo.ID, form.getDebitoTipo()));
		
		// Recupera Tipo de Serviço
		Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
		
		if (colecaoDebitoTipo != null && !colecaoDebitoTipo.isEmpty()) {
			sessao.setAttribute("debitoTipoEncontrada", "true");
			DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo.iterator().next();
			form.setNomeDebitoTipo(debitoTipo.getDescricao());
			
		} else {
			sessao.removeAttribute("debitoTipoEncontrada");
			form.setDebitoTipo("");
			form.setNomeDebitoTipo("Tipo de Débito inexistente");
		}
	}

}
