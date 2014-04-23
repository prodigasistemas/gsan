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
public class AdicionarFuncionalidadeDependenciaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		AdicionarFuncionalidadeDependenciaActionForm adicionarFuncionalidadeDependenciaActionForm = (AdicionarFuncionalidadeDependenciaActionForm) actionForm;

		String idFuncionalidade = adicionarFuncionalidadeDependenciaActionForm
				.getComp_id();

		Collection colecaoFuncionalidade = null;
		Collection colecaoFuncionalidadeDependencia = null;

		String funcionalidadeID = adicionarFuncionalidadeDependenciaActionForm
				.getFuncionalidadeID();

		if (sessao.getAttribute("funcionalidade").equals("inserir")) {

			retorno = actionMapping
					.findForward("inserirFuncionalidadeDependenciaAction");

			// setar no request um atributo informando qdo ele vem do inserir e
			// qdo
			// ele vem do atualizar pra seguir
			// passos diferentes

			if (sessao.getAttribute("colecaoFuncionalidadeTela") != null) {
				colecaoFuncionalidade = (Collection) sessao
						.getAttribute("colecaoFuncionalidadeTela");
			} else {
				colecaoFuncionalidade = new ArrayList();
			}

			// testa se a funcionalidade ja foi adicionada
			if (idFuncionalidade != null
					&& !idFuncionalidade.equalsIgnoreCase("")) {

				if (colecaoFuncionalidade != null
						&& !colecaoFuncionalidade.isEmpty()) {

					Funcionalidade funcionalidade = null;
					Iterator iterator = colecaoFuncionalidade.iterator();

					while (iterator.hasNext()) {

						funcionalidade = (Funcionalidade) iterator.next();

						if (funcionalidade.getId().equals(
								new Integer(idFuncionalidade))) {
							// Esta funcionalidade ja foi informada
							throw new ActionServletException(
									"atencao.funcionalidade.ja_informada");
						}

					}

				}

				FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
				filtroFuncionalidade.adicionarParametro(new ParametroSimples(
						FiltroFuncionalidade.ID, idFuncionalidade));

				colecaoFuncionalidade = fachada.pesquisar(filtroFuncionalidade,
						Funcionalidade.class.getName());
			}

			if (colecaoFuncionalidade != null
					&& !colecaoFuncionalidade.isEmpty()) {
				sessao.setAttribute("colecaoFuncionalidade",
						colecaoFuncionalidade);
				httpServletRequest.setAttribute("reload", true);
			}

		} else {

			retorno = actionMapping
					.findForward("atualizarFuncionalidadeDependenciaAction");

			httpServletRequest.setAttribute("idFuncionalidade",
					funcionalidadeID);

			if (sessao.getAttribute("colecaoFuncionalidadeTela") != null) {
				colecaoFuncionalidadeDependencia = (Collection) sessao
						.getAttribute("colecaoFuncionalidadeTela");
			} else {
				colecaoFuncionalidadeDependencia = new ArrayList();
			}

			Funcionalidade funcionalidadePrincipal = (Funcionalidade) sessao
					.getAttribute("funcionalidadeAtualizar");
 
			// testa se a funcionalidade ja foi adicionada
			if (idFuncionalidade != null
					&& !idFuncionalidade.equalsIgnoreCase("")) {

				if (funcionalidadePrincipal.getId().equals(
						new Integer(idFuncionalidade))) {
					// Esta funcionalidade é igual a principal
					throw new ActionServletException(
							"atencao.funcionalidade.igual_principal");
				}

				if (colecaoFuncionalidadeDependencia != null
						&& !colecaoFuncionalidadeDependencia.isEmpty()) {

					FuncionalidadeDependencia funcionalidade = null;
					Iterator iterator = colecaoFuncionalidadeDependencia
							.iterator();

					while (iterator.hasNext()) {

						funcionalidade = (FuncionalidadeDependencia) iterator
								.next();

						Integer idFuncionalidadeDependencia = null;

						if (funcionalidade.getComp_id() != null) {
							idFuncionalidadeDependencia = funcionalidade
									.getComp_id()
									.getFuncionalidadeDependenciaId();
						} else {
							idFuncionalidadeDependencia = funcionalidade
									.getFuncionalidadeDependencia().getId();
						}

						if (idFuncionalidadeDependencia.equals(new Integer(
								idFuncionalidade))) {
							// Esta funcionalidade ja foi informada
							throw new ActionServletException(
									"atencao.funcionalidade.ja_informada");
						}

					}

				}

				FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
				filtroFuncionalidade.adicionarParametro(new ParametroSimples(
						FiltroFuncionalidade.ID, idFuncionalidade));

				colecaoFuncionalidade = fachada.pesquisar(filtroFuncionalidade,
						Funcionalidade.class.getName());
			}

			if (colecaoFuncionalidade != null
					&& !colecaoFuncionalidade.isEmpty()) {
				FuncionalidadeDependencia funcionalidadeDependencia = new FuncionalidadeDependencia();
				funcionalidadeDependencia
						.setFuncionalidadeDependencia((Funcionalidade) colecaoFuncionalidade
								.iterator().next());
				colecaoFuncionalidadeDependencia.add(funcionalidadeDependencia);
				sessao.setAttribute("colecaoFuncionalidadeTela",
						colecaoFuncionalidadeDependencia);
				httpServletRequest.setAttribute("reload", true);
			}

		}

		sessao.setAttribute("adicionar","sim");
		
		return retorno;
	}
}
