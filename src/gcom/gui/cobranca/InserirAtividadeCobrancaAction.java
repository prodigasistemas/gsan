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
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
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
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class InserirAtividadeCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {


		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirAtividadeCobrancaActionForm inserirAtividadeCobrancaActionForm = (InserirAtividadeCobrancaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		//Recuperando os dados do formulário
		String descricaoAtividadeCobranca = inserirAtividadeCobrancaActionForm.getDescricaoAtividadeCobranca();
		String atividadePredecessora = inserirAtividadeCobrancaActionForm.getIdAtividadePredecessora();
		String ordemCronograma = inserirAtividadeCobrancaActionForm.getOrdemCronograma();
		String processoAssociado = inserirAtividadeCobrancaActionForm.getIdProcessoAssociado();
		String opcaoCompoeCronograma = inserirAtividadeCobrancaActionForm.getOpcaoCompoeCronograda();
		String opcaoAtividadeObrigatoria = inserirAtividadeCobrancaActionForm.getOpcaoAtividadeObrigatoria();
		String opcaoComandada = inserirAtividadeCobrancaActionForm.getOpcaoPodeSerComandada();
		String opcaoExecutada = inserirAtividadeCobrancaActionForm.getOpcaoPodeSerExecutada();
		String quantidadeDiasExecucao = inserirAtividadeCobrancaActionForm.getQuantidadeDiasExecucao();
		String cobrancaAcao = inserirAtividadeCobrancaActionForm.getCobrancaAcao();
		
		FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
		
		/*
		 * [UC0107] Registrar Transação
		 * 
		 */

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_ATIVIDADE_COBRANCA,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_INSERIR_ATIVIDADE_COBRANCA);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		// [UC0107] -Fim- Registrar Transação
		
		//FS0002 - Numero de ordem já utilizado
		filtroCobrancaAtividade.adicionarParametro(
				new ParametroSimples(FiltroCobrancaAtividade.ORDEM_REALIZACAO, ordemCronograma));
		
		Collection colecaoCobrancaAtividade = fachada.pesquisar(
				filtroCobrancaAtividade, CobrancaAtividade.class.getName());
		
		if (colecaoCobrancaAtividade != null && !colecaoCobrancaAtividade.isEmpty()){
			throw new ActionServletException("atencao.numero_ordem_informado");
		}
		//FS0002 - Fim do Bloco
		
		filtroCobrancaAtividade.limparListaParametros();
		CobrancaAtividade cobrancaAtividadePredecessora = new CobrancaAtividade();
		// FS0003 - Numero de ordem menor que o da predecessora
		if (atividadePredecessora != null && !atividadePredecessora.equalsIgnoreCase("")){
			filtroCobrancaAtividade.adicionarParametro(
					new ParametroSimples(FiltroCobrancaAtividade.ID, atividadePredecessora));
			cobrancaAtividadePredecessora = (CobrancaAtividade) fachada.pesquisar(
					filtroCobrancaAtividade, CobrancaAtividade.class.getName()).iterator().next();
			
			int ordemRealizacao = new Integer(ordemCronograma);
			int ordemPredecessora = cobrancaAtividadePredecessora.getOrdemRealizacao();
			
			if (ordemRealizacao < ordemPredecessora){
				throw new ActionServletException("atencao.predecessota_menor_atual");
			}
		}
		//FS0003 - Fim do Bloco
		
		//FS0005 - Atividade Obrigatória deve compor Cronograma
		if (opcaoCompoeCronograma.equals(ConstantesSistema.NAO.toString())){
			if (opcaoAtividadeObrigatoria.equals(ConstantesSistema.SIM.toString())){
				throw new ActionServletException("atencao.atividade_obrigatoria_compoe_cronograma");
			}
		}
		//FS0005 - Fim do Bloco
		
		//Montando o Objeto a ser Inserido
		CobrancaAtividade cobrancaAtividade = new CobrancaAtividade();
		if (descricaoAtividadeCobranca != null && !descricaoAtividadeCobranca.equalsIgnoreCase("")){
			cobrancaAtividade.setDescricaoCobrancaAtividade(descricaoAtividadeCobranca);
		}
		
		if (atividadePredecessora != null && !atividadePredecessora.equalsIgnoreCase("")){
			cobrancaAtividade.setCobrancaAtividadePredecessora(cobrancaAtividadePredecessora);	
		}
		
		if (ordemCronograma != null && !ordemCronograma.equalsIgnoreCase("")){
			cobrancaAtividade.setOrdemRealizacao(new Short(ordemCronograma));
		}
		
		if (processoAssociado != null && !processoAssociado.equalsIgnoreCase("")){
			FiltroProcesso filtroProcesso = new FiltroProcesso();
			filtroProcesso.adicionarParametro(new ParametroSimples(FiltroProcesso.ID, processoAssociado));
			
			Collection processoNaBase = fachada.pesquisar(filtroProcesso, Processo.class.getName());
			
			if (processoNaBase != null && !processoNaBase.isEmpty()) {
				Processo processo = new Processo();
				processo.setId(new Integer(processoAssociado));
				cobrancaAtividade.setProcesso(processo);
			} else {
				
			}
	
		}
		
		if (opcaoCompoeCronograma != null && !opcaoCompoeCronograma.equalsIgnoreCase("")){
			cobrancaAtividade.setIndicadorCronograma(new Short(opcaoCompoeCronograma));
		}
		
		if (opcaoAtividadeObrigatoria != null && !opcaoAtividadeObrigatoria.equalsIgnoreCase("")){
			cobrancaAtividade.setIndicadorObrigatoriedade(new Short(opcaoAtividadeObrigatoria));
		}
		
		if (opcaoComandada != null && !opcaoComandada.equalsIgnoreCase("")){
			cobrancaAtividade.setIndicadorComando(new Short(opcaoComandada));
		}
		
		if (opcaoExecutada != null && !opcaoExecutada.equalsIgnoreCase("")){
			cobrancaAtividade.setIndicadorExecucao(new Short(opcaoExecutada));
		}
		
		if (quantidadeDiasExecucao != null && !quantidadeDiasExecucao.equalsIgnoreCase("")){
			cobrancaAtividade.setNumeroDiasExecucao(new Integer(quantidadeDiasExecucao));
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
		
		cobrancaAtividade.setUltimaAlteracao(new Date());
		
		// Regitrando operacao
		cobrancaAtividade.setOperacaoEfetuada(operacaoEfetuada);
		cobrancaAtividade.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(cobrancaAtividade);
		
		//FS0007 - Inserindo na base
		Integer id = (Integer) fachada.inserir(cobrancaAtividade);
			
		montarPaginaSucesso(httpServletRequest, "Atividade de Cobranca "+ id + " inserida com sucesso.",
				"Inserir outra Atividade de Cobranca",
				"exibirInserirAtividadeCobrancaAction.do?menu=sim",
				"exibirAtualizarAtividadeCobrancaAction.do?idRegistroAtualizar=" + id,
				"Atualizar Atividade de Cobranca Inserida");
		
		
		return retorno;
	}
}
