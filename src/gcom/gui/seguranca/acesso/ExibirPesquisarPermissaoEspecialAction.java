package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.util.ConstantesSistema;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirPesquisarPermissaoEspecialAction extends GcomAction {

	/**
	 * Este caso de uso efetua pesquisa de Permissão Especial
	 * 
	 * [UC1046] Pesquisar Permissão Especial
	 * 
	 * 
	 * @author Daniel Alves
	 * @date 22/07/2010
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
				.findForward("permissaoEspecialPesquisar");
		
		PesquisarPermissaoEspecialActionForm form = (PesquisarPermissaoEspecialActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltroOperacao filtroOperacao = new FiltroOperacao();

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<Operacao> colecaoOperacao = fachada.pesquisar(filtroOperacao,
				Operacao.class.getName());

		if (colecaoOperacao == null || colecaoOperacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Operação");
		}

		httpServletRequest.setAttribute("colecaoOperacao", colecaoOperacao);

		// envia uma flag que será verificado no cliente_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaPermissaoEspecial") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaPesquisaPermissaoEspecial",
							httpServletRequest
									.getParameter("caminhoRetornoTelaPesquisaPermissaoEspecial"));
		}

		form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		return retorno;
	}

}
