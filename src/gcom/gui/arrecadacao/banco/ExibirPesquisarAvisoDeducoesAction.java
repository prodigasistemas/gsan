package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.DeducaoTipo;
import gcom.arrecadacao.FiltroDeducaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirPesquisarAvisoDeducoesAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//Declaracao de Variaveis principais
		ActionForward retorno = actionMapping
				.findForward("exibirAvisoBancarioDeducoesPopup");		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		PesquisarAvisoDeducoesActionForm form = (PesquisarAvisoDeducoesActionForm) actionForm;
		
		//limpa as variaveis quando vez
		form.setTipoDeducaoInclusao("");
		form.setValorDeducaoInclusao("");
		//Declaracao de Variaveis principais
		
		//Populando Deducao Tipo
		FiltroDeducaoTipo filtroDeducaoTipo = new FiltroDeducaoTipo();
		Collection<DeducaoTipo> collectionDeducaoTipo = fachada.pesquisar(filtroDeducaoTipo, DeducaoTipo.class.getName());
		if(collectionDeducaoTipo != null && !collectionDeducaoTipo.isEmpty())
			sessao.setAttribute("collectionDeducaoTipo", collectionDeducaoTipo);
		//Populando Deducao Tipo
		
		
		
		return retorno;
	}
	
}
 
