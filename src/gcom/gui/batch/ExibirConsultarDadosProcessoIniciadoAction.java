package gcom.gui.batch;

import gcom.batch.FiltroFuncionalidadeIniciada;
import gcom.batch.FuncionalidadeIniciada;
import gcom.batch.ProcessoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de dados do processo inserido
 * 
 * @author Rodrigo Silveira
 * @created 22/07/2006
 */
public class ExibirConsultarDadosProcessoIniciadoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarDadosProcessoIniciado");

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		
		Integer idProcessoIniciado = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
			idProcessoIniciado = Integer.parseInt(httpServletRequest
					.getParameter("idRegistroAtualizacao"));
			sessao.setAttribute("idProcessoIniciado", idProcessoIniciado);
		} else {
			idProcessoIniciado = (Integer) sessao
					.getAttribute("idProcessoIniciado");
		}
		
		
		
		
		FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada(
				FiltroFuncionalidadeIniciada.SEQUENCIAL_EXECUCAO);
		filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(
				FiltroFuncionalidadeIniciada.PROCESSO_INICIADO_ID,
				idProcessoIniciado));

		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("processoIniciado.processoSituacao");

		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("processoIniciado.processo");

		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeSituacao");

		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("processoFuncionalidade.funcionalidade");

		Collection<FuncionalidadeIniciada> colecaoFuncionalidadeIniciada = fachada
				.pesquisar(filtroFuncionalidadeIniciada,
						FuncionalidadeIniciada.class.getName());

		if (colecaoFuncionalidadeIniciada == null
				|| colecaoFuncionalidadeIniciada.isEmpty()) {
			throw new ActionServletException(
					"atencao.processo_iniciado.funcionalidade_iniciada.inexistente");

		}
		
		// Abre o popup de informacao para informar o usuário sobre a
		// Orientacao para o operador continuar ou reiniciar o batch.
		if(httpServletRequest.getParameter("informacaoPopup") != null && 
				httpServletRequest.getParameter("informacaoPopup").equalsIgnoreCase("sim")){
			
			String orientacaoProcessoFunc = colecaoFuncionalidadeIniciada.iterator().
			next().getProcessoFuncionalidade().getOrientacao();
			
			httpServletRequest.setAttribute("tituloInformacao", "Orientação para continuar ou reiniciar o batch");
			httpServletRequest.setAttribute("descricaoInformacao",orientacaoProcessoFunc);
			
			
			return actionMapping.findForward("telaInformacao");
		}
		

		httpServletRequest
				.setAttribute("mesAnoReferencia", Util
						.formatarAnoMesParaMesAno(fachada
								.pesquisarParametrosDoSistema()
								.getAnoMesFaturamento()));

		httpServletRequest.setAttribute("dataCorrente", new Date());

		httpServletRequest.setAttribute("processoIniciado",
				((FuncionalidadeIniciada) Util
						.retonarObjetoDeColecao(colecaoFuncionalidadeIniciada))
						.getProcessoIniciado());
		
		httpServletRequest.setAttribute("concluido",
				new Integer(ProcessoSituacao.CONCLUIDO));
		httpServletRequest.setAttribute("concluidoComErro",
				new Integer(ProcessoSituacao.CONCLUIDO_COM_ERRO));
		httpServletRequest.setAttribute("execucaoCancelada",
				new Integer(ProcessoSituacao.EXECUCAO_CANCELADA));

		httpServletRequest.setAttribute("colecaoFuncionalidadeIniciada",
				colecaoFuncionalidadeIniciada);
		
		// Verificar as permissão especial para reiniciar o batch
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		boolean temPermissaoReiniciarBatch = fachada.verificarPermissaoReiniciarBatch(usuario);
		httpServletRequest.setAttribute("temPermissaoReiniciarBatch", temPermissaoReiniciarBatch);


		return retorno;
	}

}
