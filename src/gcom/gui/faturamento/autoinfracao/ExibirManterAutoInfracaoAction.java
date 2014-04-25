package gcom.gui.faturamento.autoinfracao;

import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.faturamento.autoinfracao.FiltroAutosInfracao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterAutoInfracaoAction extends GcomAction {

	/**
	 * Filtrar Autos de Infração
	 * 
	 * @author Rômulo Aurélio - 22/04/2009
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterAutoInfracaoAction");

		// Obtém a instância da fachada
		// Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Limpa o atributo se o usuário voltou para o manter
		if (sessao.getAttribute("colecaoAutosInfracaoTela") != null) {
			sessao.removeAttribute("colecaoAutosInfracaoTela");
		}
		if(sessao.getAttribute("objetoAutosInfracao")!=null){
			sessao.removeAttribute("objetoAutosInfracao");
		}
		// Recupera o filtro passado pelo FiltrarAutosInfracaoAction para
		// ser efetuada pesquisa
		FiltroAutosInfracao filtroAutosInfracao = (FiltroAutosInfracao) sessao
				.getAttribute("filtroAutosInfracao");

		filtroAutosInfracao
				.adicionarCaminhoParaCarregamentoEntidade(FiltroAutosInfracao.AUTO_INFRACAO_SITUACAO);

		filtroAutosInfracao
				.adicionarCaminhoParaCarregamentoEntidade(FiltroAutosInfracao.FISCALIZACAO_SITUACAO);

		filtroAutosInfracao
				.adicionarCaminhoParaCarregamentoEntidade(FiltroAutosInfracao.FUNCIONARIO);
		
		filtroAutosInfracao.adicionarCaminhoParaCarregamentoEntidade(FiltroAutosInfracao.ORDEM_SERVICO);
		
		sessao.setAttribute("filtroAutosInfracao",filtroAutosInfracao);

		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroAutosInfracao, AutosInfracao.class.getName());
		Collection colecaoAutosInfracao = (Collection) resultado
				.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");

		// Verifica se a coleção retornada pela pesquisa é nula, em caso
		// afirmativo comunica ao usuário que não existe nenhuma fuuncionalidade
		// cadastrada
		// para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condições seta o retorno para o
		// ExibirAtualizarAutosInfracaoAction, se não atender manda a
		// coleção pelo request para ser recuperado e exibido pelo jsp.

		if (colecaoAutosInfracao != null && !colecaoAutosInfracao.isEmpty()) {

			// Verifica se a coleção contém apenas um objeto, se está retornando
			// da paginação (devido ao esquema de paginação de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usuário selecione o link para ir para a
			// segunda página ele não vá para tela de atualizar.

			if (colecaoAutosInfracao.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {

				// Verifica se o usuário marcou o checkbox de atualizar no jsp
				// funcionario_filtrar. Caso todas as condições sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarFuncionarioAction e em caso negativo
				// manda a coleção pelo request.

				if (httpServletRequest.getParameter("atualizar") != null) {
					retorno = actionMapping
							.findForward("atualizarAutoInfracao");
					AutosInfracao autosInfracao = (AutosInfracao) colecaoAutosInfracao
							.iterator().next();
					sessao.setAttribute("objetoAutosInfracao", autosInfracao);

				} else {
					httpServletRequest.setAttribute("colecaoAutosInfracao",
							colecaoAutosInfracao);
				}
			} else {
				httpServletRequest.setAttribute("colecaoAutosInfracao",
						colecaoAutosInfracao);
			}
		} else {
			// Nenhuma funcionario cadastrado
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}

}
