package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeDependencia;
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
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 03/05/2006
 */
public class AtualizarAdicionarFuncionalidadeDependenciaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("atualizarFuncionalidadeDependenciaAction");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarAdicionarFuncionalidadeDependenciaActionForm atualizarAdicionarFuncionalidadeDependenciaActionForm = (AtualizarAdicionarFuncionalidadeDependenciaActionForm) actionForm;

		String idFuncionalidade = atualizarAdicionarFuncionalidadeDependenciaActionForm
				.getComp_id();

		Collection colecaoFuncionalidadeDependencia = null;
		Collection colecaoFuncionalidade = null;

		if (sessao.getAttribute("colecaoFuncionalidadeTela") != null) {
			colecaoFuncionalidadeDependencia = (Collection) sessao
					.getAttribute("colecaoFuncionalidadeTela");
		} else {
			colecaoFuncionalidadeDependencia = new ArrayList();
		}

		// testa se a funcionalidade ja foi adicionada
		if (idFuncionalidade != null && !idFuncionalidade.equalsIgnoreCase("")) {

			if (colecaoFuncionalidadeDependencia != null
					&& !colecaoFuncionalidadeDependencia.isEmpty()) {

				FuncionalidadeDependencia funcionalidade = null;
				Iterator iterator = colecaoFuncionalidadeDependencia.iterator();

				while (iterator.hasNext()) {

					funcionalidade = (FuncionalidadeDependencia) iterator.next();

					if(funcionalidade.getComp_id().getFuncionalidadeId().equals(
							new Integer(idFuncionalidade))) {
						// Esta funcionalidade é igual a principal
						throw new ActionServletException(
								"atencao.funcionalidade.igual_principal");
					}
					
					
					if (funcionalidade.getComp_id().getFuncionalidadeDependenciaId().equals(
							new Integer(idFuncionalidade))) {
						// Esta funcionalidade ja foi informada
						throw new ActionServletException(
								"atencao.funcionalidade.ja_informada");
					}

				}

			}

			FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
			filtroFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroFuncionalidade.ID,
							idFuncionalidade));

			colecaoFuncionalidade = fachada.pesquisar(
					filtroFuncionalidade,
					Funcionalidade.class.getName());
		}
		
		
		if (colecaoFuncionalidade != null && !colecaoFuncionalidade.isEmpty()) {
			FuncionalidadeDependencia funcionalidadeDependencia = new FuncionalidadeDependencia();
			funcionalidadeDependencia.setFuncionalidadeDependencia((Funcionalidade) colecaoFuncionalidade.iterator().next());
			colecaoFuncionalidadeDependencia.add(funcionalidadeDependencia);
			sessao.setAttribute("colecaoFuncionalidadeTela", colecaoFuncionalidadeDependencia);
			httpServletRequest.setAttribute("reload", true);
		}
		
		return retorno;
	}

}
