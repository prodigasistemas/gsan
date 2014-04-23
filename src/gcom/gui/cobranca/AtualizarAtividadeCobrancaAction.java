package gcom.gui.cobranca;

import gcom.batch.FiltroProcesso;
import gcom.batch.Processo;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Tiago Moreno, Mariana Victor
 */
public class AtualizarAtividadeCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarAtividadeCobrancaActionForm atualizarAtividadeCobrancaActionForm = (AtualizarAtividadeCobrancaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Recuperando os dados do formulário
		String id = (String) sessao.getAttribute("idAtividadeCobranca");
		String descricaoAtividadeCobranca = atualizarAtividadeCobrancaActionForm
				.getDescricaoAtividadeCobranca();
		String atividadePredecessora = atualizarAtividadeCobrancaActionForm
				.getIdAtividadePredecessora();
		String ordemCronograma = atualizarAtividadeCobrancaActionForm
				.getOrdemCronograma();
		String processoAssociado = atualizarAtividadeCobrancaActionForm
				.getIdProcessoAssociado();
		String opcaoCompoeCronograma = atualizarAtividadeCobrancaActionForm
				.getOpcaoCompoeCronograda();
		String opcaoAtividadeObrigatoria = atualizarAtividadeCobrancaActionForm
				.getOpcaoAtividadeObrigatoria();
		String opcaoComandada = atualizarAtividadeCobrancaActionForm
				.getOpcaoPodeSerComandada();
		String opcaoExecutada = atualizarAtividadeCobrancaActionForm
				.getOpcaoPodeSerExecutada();
		Date ultimaAlteracaoAtividade = atualizarAtividadeCobrancaActionForm
				.getUltimaAlteracao();
		String diasExecucao = atualizarAtividadeCobrancaActionForm
				.getQuantidadeDiasExecucao();
		String cobrancaAcao = atualizarAtividadeCobrancaActionForm
				.getCobrancaAcao();

		FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();

		// FS0002 - Numero de ordem já utilizado
		filtroCobrancaAtividade
				.adicionarParametro(new ParametroSimplesDiferenteDe(
						FiltroCobrancaAtividade.ID, id));
		filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAtividade.ORDEM_REALIZACAO, ordemCronograma));

		Collection colecaoCobrancaAtividade = fachada.pesquisar(
				filtroCobrancaAtividade, CobrancaAtividade.class.getName());

		if (colecaoCobrancaAtividade != null
				&& !colecaoCobrancaAtividade.isEmpty()) {
			throw new ActionServletException("atencao.numero_ordem_informado");
		}
		// FS0002 - Fim do Bloco

		filtroCobrancaAtividade.limparListaParametros();
		CobrancaAtividade cobrancaAtividadePredecessora = new CobrancaAtividade();
		// FS0003 - Numero de ordem menor que o da predecessora
		if (atividadePredecessora != null
				&& !atividadePredecessora.equalsIgnoreCase("")) {
			filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(
					FiltroCobrancaAtividade.ID, atividadePredecessora));
			cobrancaAtividadePredecessora = (CobrancaAtividade) fachada
					.pesquisar(filtroCobrancaAtividade,
							CobrancaAtividade.class.getName()).iterator()
					.next();

			int ordemRealizacao = new Integer(ordemCronograma);
			int ordemPredecessora = cobrancaAtividadePredecessora
					.getOrdemRealizacao();

			if (ordemRealizacao < ordemPredecessora) {
				throw new ActionServletException(
						"atencao.predecessota_menor_atual");
			}
		}
		// FS0003 - Fim do Bloco

		// FS0005 - Atividade Obrigatória deve compor Cronograma
		if (opcaoCompoeCronograma.equals(ConstantesSistema.NAO.toString())) {
			if (opcaoAtividadeObrigatoria.equals(ConstantesSistema.SIM
					.toString())) {
				throw new ActionServletException(
						"atencao.atividade_obrigatoria_compoe_cronograma");
			}
		}
		// FS0005 - Fim do Bloco
		
		
		// FS0006 - Atividade Obrigatória deve compor Cronograma
		if (atividadePredecessora != null) {
			if (id != null && id.equals(atividadePredecessora)) {
				throw new ActionServletException(
						"atencao.atividade_predecessora_nao_igual_atividade");
			}
		}
		// FS0006 - Fim do Bloco

		// Montando o Objeto a ser Inserido
		CobrancaAtividade cobrancaAtividade = new CobrancaAtividade();

		FiltroCobrancaAtividade filtroCobrancaAtividadeNoBanco = new FiltroCobrancaAtividade();

		filtroCobrancaAtividadeNoBanco.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAtividade.ID, id));

		filtroCobrancaAtividadeNoBanco
				.adicionarCaminhoParaCarregamentoEntidade("processo");

		cobrancaAtividade = (CobrancaAtividade) fachada.pesquisar(
				filtroCobrancaAtividadeNoBanco,
				CobrancaAtividade.class.getName()).iterator().next();

		if (descricaoAtividadeCobranca != null
				&& !descricaoAtividadeCobranca.equalsIgnoreCase("")) {
			cobrancaAtividade
					.setDescricaoCobrancaAtividade(descricaoAtividadeCobranca);
		}

		if (atividadePredecessora != null
				&& !atividadePredecessora.equalsIgnoreCase("")) {
			cobrancaAtividade
					.setCobrancaAtividadePredecessora(cobrancaAtividadePredecessora);
		}

		if (ordemCronograma != null && !ordemCronograma.equalsIgnoreCase("")) {
			cobrancaAtividade.setOrdemRealizacao(new Short(ordemCronograma));
		}

		if (processoAssociado != null
				&& !processoAssociado.equalsIgnoreCase("")) {
			FiltroProcesso filtroProcesso = new FiltroProcesso();
			filtroProcesso.adicionarParametro(new ParametroSimples(
					FiltroProcesso.ID, processoAssociado));

			Collection processoNaBase = fachada.pesquisar(filtroProcesso,
					Processo.class.getName());

			if (processoNaBase != null && !processoNaBase.isEmpty()) {
				Processo processo = new Processo();
				processo.setId(new Integer(processoAssociado));
				cobrancaAtividade.setProcesso(processo);
			}

		}

		if (opcaoCompoeCronograma != null
				&& !opcaoCompoeCronograma.equalsIgnoreCase("")) {
			cobrancaAtividade.setIndicadorCronograma(new Short(
					opcaoCompoeCronograma));
		}

		if (opcaoAtividadeObrigatoria != null
				&& !opcaoAtividadeObrigatoria.equalsIgnoreCase("")) {
			cobrancaAtividade.setIndicadorObrigatoriedade(new Short(
					opcaoAtividadeObrigatoria));
		}

		if (opcaoComandada != null && !opcaoComandada.equalsIgnoreCase("")) {
			cobrancaAtividade.setIndicadorComando(new Short(opcaoComandada));
		}

		if (opcaoExecutada != null && !opcaoExecutada.equalsIgnoreCase("")) {
			cobrancaAtividade.setIndicadorExecucao(new Short(opcaoExecutada));
		}

		if (diasExecucao != null && !diasExecucao.equalsIgnoreCase("")) {
			cobrancaAtividade.setNumeroDiasExecucao(new Integer(diasExecucao));
		}
		
		if (cobrancaAcao != null && !cobrancaAcao.equalsIgnoreCase("")){
			FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
			filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.ID, cobrancaAcao));
			
			Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
			
			if (colecaoCobrancaAcao != null && !colecaoCobrancaAcao.isEmpty()) {
				CobrancaAcao acaoCobranca = new CobrancaAcao();
				acaoCobranca.setId(new Integer(cobrancaAcao));
				cobrancaAtividade.setCobrancaAcao(acaoCobranca);
			}
		}

		cobrancaAtividade.setIndicadorUso(new Short("1"));
		
		// Verificar se o cobrança atividade já foi atualizado por outro usuário
		// durante
		// esta atualização
		if (cobrancaAtividade == null
				|| cobrancaAtividade.getUltimaAlteracao().after(
						ultimaAlteracaoAtividade)) {
			throw new ActionServletException(
			"atencao.atualizacao.timestamp");
			
		}

		cobrancaAtividade.setUltimaAlteracao(new Date());

		// FS0007 - Inserindo na base
		fachada.atualizar(cobrancaAtividade);

		montarPaginaSucesso(httpServletRequest, "Atividade de Cobranca " + id
				+ " atualizada com sucesso.",
				"Atualizar outra Atividade de Cobranca",
				"exibirManterAtividadeCobrancaAction.do?menu=sim");

		return retorno;
	}
}
