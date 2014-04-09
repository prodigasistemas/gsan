package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.seguranca.acesso.Modulo;
import gcom.util.ConstantesSistema;

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
 * @author Rômulo Aurélio
 * @date 15/05/2006
 */
public class AtualizarFuncionalidadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarFuncionalidadeActionForm atualizarFuncionalidadeActionForm = (AtualizarFuncionalidadeActionForm) actionForm;

		String idModulo = atualizarFuncionalidadeActionForm.getModulo();

		Funcionalidade funcionalidade = (Funcionalidade) sessao
				.getAttribute("funcionalidadeAtualizar");

		funcionalidade.setDescricao(atualizarFuncionalidadeActionForm
				.getDescricao());

		funcionalidade.setDescricaoAbreviada(atualizarFuncionalidadeActionForm
				.getDescricaoAbreviada());

		funcionalidade.setCaminhoMenu(atualizarFuncionalidadeActionForm
				.getCaminhoMenu());

		funcionalidade.setCaminhoUrl(atualizarFuncionalidadeActionForm
				.getCaminhoUrl());

		funcionalidade.setIndicadorPontoEntrada(new Short(
				atualizarFuncionalidadeActionForm.getIndicadorPontoEntrada()));

		if (atualizarFuncionalidadeActionForm.getIndicadorPontoEntrada().equals(
				ConstantesSistema.INDICADOR_USO_ATIVO.toString())) {

			FuncionalidadeCategoria funcionalidadeCategoria = new FuncionalidadeCategoria();

			funcionalidadeCategoria.setId(new Integer(
					atualizarFuncionalidadeActionForm
							.getIdFuncionalidadeCategoria()));

			funcionalidade.setFuncionalidadeCategoria(funcionalidadeCategoria);
		}

		funcionalidade.setNumeroOrdemMenu(new Long(
				atualizarFuncionalidadeActionForm.getNumeroOrdemMenu()));

		funcionalidade.setIndicadorOlap(new Integer(
				atualizarFuncionalidadeActionForm.getIndicadorOlap())
				.shortValue());

		funcionalidade.setIndicadorNovaJanela(new Integer(
				atualizarFuncionalidadeActionForm.getIndicadorNovaJanela())
				.shortValue());

		if (idModulo != null
				&& !idModulo.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			Modulo modulo = new Modulo();

			modulo.setId(new Integer(idModulo));

			funcionalidade.setModulo(modulo);

		}

		Collection<Funcionalidade> colecaoFuncionalidade = null;

		if (sessao.getAttribute("colecaoFuncionalidadeTela") != null) {
			colecaoFuncionalidade = (Collection) sessao
					.getAttribute("colecaoFuncionalidadeTela");
		}

		fachada.atualizarFuncionalidade(funcionalidade, colecaoFuncionalidade);

		sessao.removeAttribute("colecaoFuncionalidadeTela");

		montarPaginaSucesso(httpServletRequest, "Funcionalidade "
				+ funcionalidade.getId() + " atualizado com sucesso.",
				"Realizar outra Manutenção Funcionalidade",
				"exibirFiltrarFuncionalidadeAction.do?menu=sim");

		return retorno;

	}

}
