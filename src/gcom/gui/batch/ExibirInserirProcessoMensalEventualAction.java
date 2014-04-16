package gcom.gui.batch;

import gcom.batch.FiltroProcesso;
import gcom.batch.FiltroProcessoFuncionalidade;
import gcom.batch.FiltroProcessoIniciado;
import gcom.batch.Processo;
import gcom.batch.ProcessoFuncionalidade;
import gcom.batch.ProcessoIniciado;
import gcom.batch.ProcessoSituacao;
import gcom.batch.ProcessoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de inserir processo
 * 
 * @author Rodrigo Silveira
 * @created 22/07/2006
 */
public class ExibirInserirProcessoMensalEventualAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("inserirProcessoMensalEventual");

		// Pesquisa as situações do processos para o select da página
		// this.pesquisarProcessoSituacao(httpServletRequest);

		// Pesquisa o Processo digitado e os ProcessoFuncionalidade relacionados
		this.pesquisarProcessoDigitado(httpServletRequest,
				(InserirProcessoMensalEventualActionForm) actionForm);

		// Pesquisa o Processo iniciado precedente digitado e os
		// ProcessoFuncionalidade relacionados
		this.pesquisarProcessoIniciadoPrecedenteDigitado(httpServletRequest,
				(InserirProcessoMensalEventualActionForm) actionForm);

		return retorno;
	}

	/**
	 * Pesquisa todas as Situações do Processo para popular o select da página
	 * de inserir processo
	 * 
	 * @author Rodrigo Silveira
	 * @date 22/07/2006
	 * 
	 * @param httpServletRequest
	 */
	// private void pesquisarProcessoSituacao(HttpServletRequest
	// httpServletRequest) {
	// FiltroProcessoSituacao filtroProcessoSituacao = new
	// FiltroProcessoSituacao();
	// Collection<ProcessoSituacao> colecao = Fachada.getInstancia()
	// .pesquisar(filtroProcessoSituacao,
	// ProcessoSituacao.class.getName());
	// httpServletRequest.setAttribute("colecaoProcessoSituacao", colecao);
	//
	// }
	/**
	 * Função que pesquisa um Processo digitado e pesquisa todos os
	 * ProcessoFuncionalidade relacionados
	 * 
	 * @author Rodrigo Silveira
	 * @date 24/07/2006
	 * 
	 * @param httpServletRequest
	 * @param actionForm
	 */
	private void pesquisarProcessoDigitado(
			HttpServletRequest httpServletRequest,
			InserirProcessoMensalEventualActionForm actionForm) {

		String idDigitadoEnterProcesso = (String) actionForm.getIdProcesso();

		actionForm.setDescricaoProcesso("");
		actionForm.setIdProcesso("");

		// Verifica se o código foi digitado
		if (idDigitadoEnterProcesso != null
				&& !idDigitadoEnterProcesso.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterProcesso) > 0) {
			Fachada fachada = Fachada.getInstancia();

			// Este trecho pesquisa todos do processoFuncionalidade relacionados
			// com o processo digitado

			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.ID_PROCESSO,
							idDigitadoEnterProcesso));
			
			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));


			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.PROCESSO_PROCESSO_TIPO_ID,
							ProcessoTipo.MENSAL, FiltroParametro.CONECTOR_OR, 4));

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.PROCESSO_PROCESSO_TIPO_ID,
							ProcessoTipo.EVENTUAL, FiltroParametro.CONECTOR_OR));

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.PROCESSO_PROCESSO_TIPO_ID,
							ProcessoTipo.DIARIO, FiltroParametro.CONECTOR_OR));

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.PROCESSO_PROCESSO_TIPO_ID,
							ProcessoTipo.SEMANAL, FiltroParametro.CONECTOR_OR));

			filtroProcessoFuncionalidade
					.adicionarCaminhoParaCarregamentoEntidade("funcionalidade");
			filtroProcessoFuncionalidade
					.adicionarCaminhoParaCarregamentoEntidade("unidadeProcessamento");

			filtroProcessoFuncionalidade
					.setCampoOrderBy(FiltroProcessoFuncionalidade.SEQUENCIAL_EXECUCAO);

			Collection processosFuncionaliadade = fachada.pesquisar(
					filtroProcessoFuncionalidade, ProcessoFuncionalidade.class
							.getName());

			httpServletRequest.setAttribute("colecaoProcessoFuncionalidade",
					processosFuncionaliadade);

			// Este trecho pesquisa o processo digitado e altera o form para
			// refletir o resultado da busca na página de inserir processo

			FiltroProcesso filtroProcesso = new FiltroProcesso();

			filtroProcesso.adicionarParametro(new ParametroSimples(
					FiltroProcesso.ID, idDigitadoEnterProcesso));

			filtroProcesso.adicionarParametro(new ParametroSimples(
					FiltroProcesso.PROCESSO_TIPO, ProcessoTipo.MENSAL,
					FiltroParametro.CONECTOR_OR, 5));
			filtroProcesso.adicionarParametro(new ParametroSimples(
					FiltroProcesso.PROCESSO_TIPO, ProcessoTipo.EVENTUAL,
					FiltroParametro.CONECTOR_OR));
			filtroProcesso.adicionarParametro(new ParametroSimples(
					FiltroProcesso.PROCESSO_TIPO, ProcessoTipo.RELATORIO,
					FiltroParametro.CONECTOR_OR));
			filtroProcesso.adicionarParametro(new ParametroSimples(
					FiltroProcesso.PROCESSO_TIPO, ProcessoTipo.SEMANAL,
					FiltroParametro.CONECTOR_OR));
			filtroProcesso.adicionarParametro(new ParametroSimples(
					FiltroProcesso.PROCESSO_TIPO, ProcessoTipo.DIARIO,
					FiltroParametro.CONECTOR_OR));

			Collection processos = fachada.pesquisar(filtroProcesso,
					Processo.class.getName());

			if (processos != null && !processos.isEmpty()) {
				// O processo foi encontrado
				actionForm.setIdProcesso(((Processo) ((List) processos).get(0))
						.getId().toString());
				actionForm.setDescricaoProcesso(((Processo) ((List) processos)
						.get(0)).getDescricaoProcesso());

			} else {
				actionForm.setIdProcesso("");
				httpServletRequest.setAttribute("idProcessoNaoEncontrado",
						"true");
				actionForm.setDescricaoProcesso("Processo inexistente");

			}
		}

	}

	/**
	 * Função que pesquisa um Processo iniciado precedente digitado
	 * 
	 * @author Rodrigo Silveira
	 * @date 24/07/2006
	 * 
	 * @param httpServletRequest
	 * @param actionForm
	 */
	private void pesquisarProcessoIniciadoPrecedenteDigitado(
			HttpServletRequest httpServletRequest,
			InserirProcessoMensalEventualActionForm actionForm) {

		String idDigitadoEnterProcesso = (String) actionForm
				.getIdProcessoIniciadoPrecedente();

		actionForm.setDescricaoProcessoIniciadoPrecedente("");
		actionForm.setIdProcessoIniciadoPrecedente("");

		// Verifica se o código foi digitado
		if (idDigitadoEnterProcesso != null
				&& !idDigitadoEnterProcesso.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterProcesso) > 0
				&& !idDigitadoEnterProcesso.trim().equals(
						actionForm.getIdProcesso())) {
			Fachada fachada = Fachada.getInstancia();

			// Este trecho pesquisa o processo digitado e altera o form para
			// refletir o resultado da busca na página de inserir processo

			FiltroProcessoIniciado filtroProcessoIniciado = new FiltroProcessoIniciado();

			filtroProcessoIniciado.adicionarParametro(new ParametroSimples(
					FiltroProcessoIniciado.ID, idDigitadoEnterProcesso));

			filtroProcessoIniciado.adicionarParametro(new ParametroSimples(
					FiltroProcessoIniciado.PROCESSO_SITUACAO_ID,
					ProcessoSituacao.AGENDADO, FiltroParametro.CONECTOR_OR));

			filtroProcessoIniciado.adicionarParametro(new ParametroSimples(
					FiltroProcessoIniciado.PROCESSO_SITUACAO_ID,
					ProcessoSituacao.EM_ESPERA, FiltroParametro.CONECTOR_OR));

			filtroProcessoIniciado.adicionarParametro(new ParametroSimples(
					FiltroProcessoIniciado.PROCESSO_SITUACAO_ID,
					ProcessoSituacao.EM_PROCESSAMENTO,
					FiltroParametro.CONECTOR_OR));

			filtroProcessoIniciado.adicionarParametro(new ParametroSimples(
					FiltroProcessoIniciado.PROCESSO_SITUACAO_ID,
					ProcessoSituacao.INICIO_A_COMANDAR));

			filtroProcessoIniciado
					.adicionarCaminhoParaCarregamentoEntidade("processo");

			Collection processos = fachada.pesquisar(filtroProcessoIniciado,
					ProcessoIniciado.class.getName());

			if (processos != null && !processos.isEmpty()) {
				// O processo foi encontrado
				actionForm
						.setIdProcessoIniciadoPrecedente(((ProcessoIniciado) ((List) processos)
								.get(0)).getId().toString());
				actionForm
						.setDescricaoProcessoIniciadoPrecedente(((ProcessoIniciado) ((List) processos)
								.get(0)).getProcesso().getDescricaoProcesso());

			} else {
				actionForm.setIdProcessoIniciadoPrecedente("");
				httpServletRequest.setAttribute(
						"idProcessoIniciadoPrecedenteNaoEncontrado", "true");
				actionForm
						.setDescricaoProcessoIniciadoPrecedente("Processo inexistente");

			}
		}

	}

}
