package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
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
 * Action que faz a exibição da tela para o usuário setar os campos e permitir
 * que ele comande o encerramento dos registros de atendimentos 
 * [UC0735] Comandar Encerramento de Registros de Atendimento
 * 
 * @author Rafael Corrêa, Pedro Alexandre
 * @since 25/01/2008, 10/06/2008
 */
public class ExibirComandarEncerramentoRegistroAtendimentoAction extends GcomAction {

	/**
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirComandarEncerramentoRA");
		
		Fachada fachada = Fachada.getInstancia();
		
		ComandarEncerramentoRegistroAtendimentoActionForm form = (ComandarEncerramentoRegistroAtendimentoActionForm)actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Date dataAtual = new Date();
		if(form.getDataAtendimentoFinal() == null || form.getDataAtendimentoFinal().trim().equals("")){
			form.setDataAtendimentoFinal(Util.formatarData(dataAtual));
		}
		httpServletRequest.setAttribute("dataAtual",Util.formatarData(dataAtual));
		
		//-------[FS0001 - VERIFICAR EXISTENCIA DE DADOS]  ----  Motivo do Encerramento
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroAtendimentoMotivoEncerramento.INDICADOR_DUPLICIDADE, AtendimentoMotivoEncerramento.INDICADOR_DUPLICIDADE_ATIVO));
		filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroAtendimentoMotivoEncerramento.INDICADOR_EXECUCAO, AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM));
		
		Collection<AtendimentoMotivoEncerramento> colecaoMotivoEncerramento = fachada.pesquisar(filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());

		if (colecaoMotivoEncerramento == null || colecaoMotivoEncerramento.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Motivo de Encerramento");
		}
		
		sessao.setAttribute("colecaoMotivoEncerramento", colecaoMotivoEncerramento);
		
		//-------[FS0001 - VERIFICAR EXISTENCIA DE DADOS]  ----  Motivo do Encerramento
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		filtroSolicitacaoTipoEspecificacao.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_ENCERRAMENTO_AUTOMATICO, SolicitacaoTipoEspecificacao.INDICADOR_SEM_ENCERRAMENTO_AUTOMATICO));
		
		Collection<SolicitacaoTipoEspecificacao> colecaoSolicitacaoTipoEspecificacao = fachada
				.pesquisar(filtroSolicitacaoTipoEspecificacao,
						SolicitacaoTipoEspecificacao.class.getName());

		if (colecaoSolicitacaoTipoEspecificacao == null || colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Especificação do Tipo da Solicitação");
		}
		
		sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao", colecaoSolicitacaoTipoEspecificacao);
		
		String idUsuario = form.getIdUsuario();
		
		if (idUsuario != null && !idUsuario.trim().equals("")) {
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, idUsuario));
			
			Collection<Usuario> colecaoUsuarios = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuarios != null && !colecaoUsuarios.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuarios);
				form.setIdUsuario(usuario.getId().toString());
				form.setNomeUsuario(usuario.getNomeUsuario());
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeAtendimento");
				
			} else {
				form.setIdUsuario("");
				form.setNomeUsuario("USUARIO INEXISTENTE");
				httpServletRequest.setAttribute("usuarioInexistente",true);
				httpServletRequest.setAttribute("nomeCampo", "idUsuario");
			}
		}
		
		String idUnidadeAtendimento = form.getIdUnidadeAtendimento();
		
		if (idUnidadeAtendimento != null && !idUnidadeAtendimento.trim().equals("")) {
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidadeAtendimento));
			
			Collection<UnidadeOrganizacional> colecaoUnidadesAtendimento = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadesAtendimento != null && !colecaoUnidadesAtendimento.isEmpty()) {
				UnidadeOrganizacional unidadeAtendimento = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadesAtendimento);
				form.setIdUnidadeAtendimento(unidadeAtendimento.getId().toString());
				form.setNomeUnidadeAtendimento(unidadeAtendimento.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeAtual");
				
			} else {
				form.setIdUnidadeAtendimento("");
				form.setNomeUnidadeAtendimento("UNIDADE INEXISTENTE");
				httpServletRequest.setAttribute("unidadeAtendimentoInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeAtendimento");
			}
		}
		
		String idUnidadeAtual = form.getIdUnidadeAtual();
		
		if (idUnidadeAtual != null && !idUnidadeAtual.trim().equals("")) {
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidadeAtual));
			
			Collection<UnidadeOrganizacional> colecaoUnidadesAtual = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadesAtual != null && !colecaoUnidadesAtual.isEmpty()) {
				UnidadeOrganizacional unidadeAtual = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadesAtual);
				form.setIdUnidadeAtual(unidadeAtual.getId().toString());
				form.setNomeUnidadeAtual(unidadeAtual.getDescricao());
			} else {
				form.setIdUnidadeAtual("");
				form.setNomeUnidadeAtual("UNIDADE INEXISTENTE");
				httpServletRequest.setAttribute("unidadeAtualInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeAtual");
			}
		}
		
		String idUnidadeSuperior = form.getIdUnidadeSuperior();
		
		if (idUnidadeSuperior != null && !idUnidadeSuperior.trim().equals("")) {
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidadeSuperior));
			
			Collection<UnidadeOrganizacional> colecaoUnidadeSuperior = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadeSuperior != null && !colecaoUnidadeSuperior.isEmpty()) {
				UnidadeOrganizacional unidadeSuperior = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeSuperior);
				form.setIdUnidadeSuperior(unidadeSuperior.getId().toString());
				form.setNomeUnidadeSuperior(unidadeSuperior.getDescricao());
			} else {
				form.setIdUnidadeSuperior("");
				form.setNomeUnidadeSuperior("UNIDADE SUPERIOR INEXISTENTE");
				httpServletRequest.setAttribute("unidadeSuperiorInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeSuperior");
			}
		}

		return retorno;

	}
	

}
