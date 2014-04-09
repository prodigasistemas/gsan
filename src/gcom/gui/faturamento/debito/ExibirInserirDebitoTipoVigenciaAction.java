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
 * [UC0985] Inserir tipo de débito com vigência
 * 
 * Este caso de uso permite a inclusão de um novo valor e uma vigência de um tipo de débito.
 *
 * @author Josenildo Neves - Hugo Leonardo
 * @date 11/02/2010 - 16/04/2010
 */
public class ExibirInserirDebitoTipoVigenciaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("inserirDebitoTipoVigenciaAction");

		InserirDebitoTipoVigenciaActionForm debitoTipoVigenciaActionForm = (InserirDebitoTipoVigenciaActionForm) actionForm;
		
		if(debitoTipoVigenciaActionForm.getTipoDebito() != null &&	!debitoTipoVigenciaActionForm.getTipoDebito().equals("")) {
			this.getDebitoTipo(debitoTipoVigenciaActionForm, httpServletRequest);			
		}
		return retorno;
	}

	/**
	 * Recupera Tipo de Débito
	 * 
	 * [FS0001 - Verificar tipo de débito]
	 * 
	 * @author Josenildo Neves - Hugo Leonardo	
	 * @date 11/02/2010 - 16/04/2010
	 *
	 * @param InserirDebitoTipoVigenciaActionForm
	 * @param HttpServletRequest
	 */
	private void getDebitoTipo(InserirDebitoTipoVigenciaActionForm form, HttpServletRequest httpServletRequest) {
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Filtra Tipo de Debito
		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
		filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, form.getTipoDebito()));
		//filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		// Recupera Tipo de Debito
		Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
		
		if (colecaoDebitoTipo != null && !colecaoDebitoTipo.isEmpty()) {
			sessao.setAttribute("debitoTipoEncontrada", "true");
			DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo.iterator().next();
			form.setNomeTipoDebito(debitoTipo.getDescricao());
		} else {
			sessao.removeAttribute("debitoTipoEncontrada");
			form.setTipoDebito("");
			form.setNomeTipoDebito("Tipo de Débito inexistente");
		}
		
	}	

}
