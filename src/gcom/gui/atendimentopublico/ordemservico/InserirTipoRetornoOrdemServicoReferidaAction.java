package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action para inserir o tipo retorno da os referida.
 * 
 * @author lms
 * @date 28/07/2006
 */
public class InserirTipoRetornoOrdemServicoReferidaAction extends GcomAction {

	/**
	 * Este caso de uso permite inserir tipo retorno da os referida
	 * 
	 * [UC0396] Inserir Tipo de Retorno da OS Referida
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		InserirTipoRetornoOrdemServicoReferidaActionForm form = (InserirTipoRetornoOrdemServicoReferidaActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		// HttpSession sessao = httpServletRequest.getSession(false);

		// Atualiza a entidade com os valores do formulário
		OsReferidaRetornoTipo osReferidaRetornoTipo = form
				.getOsReferidaRetornoTipo();

		if (Util.validarNumeroMaiorQueZERO(form.getServicoTipoReferencia())) {
			// Constrói o filtro para pesquisa do serviço tipo referência
			FiltroServicoTipoReferencia filtroServicoTipoReferencia = new FiltroServicoTipoReferencia();
			filtroServicoTipoReferencia
					.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
							form.getServicoTipoReferencia()));
			// Realiza a pesquisa serviço tipo referência
			ServicoTipoReferencia servicoTipoReferencia = (ServicoTipoReferencia) fachada
					.pesquisar(filtroServicoTipoReferencia,
							ServicoTipoReferencia.class.getName()).iterator()
					.next();
			osReferidaRetornoTipo
					.setServicoTipoReferencia(servicoTipoReferencia);
		}

		if (Util.validarNumeroMaiorQueZERO(form
				.getAtendimentoMotivoEncerramento())) {
			// Constrói o filtro para pesquisa do atendimento motivo
			// encerramento
			FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
			filtroAtendimentoMotivoEncerramento
					.adicionarParametro(new ParametroSimples(
							FiltroAtendimentoMotivoEncerramento.ID, form
									.getAtendimentoMotivoEncerramento()));
			// Realiza a pesquisa serviço atendimento motivo encerramento
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = (AtendimentoMotivoEncerramento) fachada
					.pesquisar(filtroAtendimentoMotivoEncerramento,
							AtendimentoMotivoEncerramento.class.getName())
					.iterator().next();
			osReferidaRetornoTipo
					.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
		}
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		// Registrando a operação
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO__TIPO_RETORNO_OS_REFERIDA_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO__TIPO_RETORNO_OS_REFERIDA_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		osReferidaRetornoTipo.setOperacaoEfetuada(operacaoEfetuada);
		osReferidaRetornoTipo.adicionarUsuario(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
    	registradorOperacao.registrarOperacao(osReferidaRetornoTipo);

		// Atualiza a base de dados com as alterações da instalação hidrômetro
		fachada.inserirOSReferidaRetornoTipo(osReferidaRetornoTipo);

		// Exibe a página de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Tipo de Retorno da OS Referida "
						+ osReferidaRetornoTipo.getDescricao()
						+ " inserido com sucesso.", "Inserir outro Tipo de Retorno da OS Referida",
				"exibirInserirTipoRetornoOrdemServicoReferidaAction.do");

		return retorno;

	}

}
