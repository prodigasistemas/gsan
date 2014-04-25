package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.FiltroServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite filtrar resoluções de diretoria [UC0219] Filtrar Resolução de
 * Diretoria
 * 
 * @author Rafael Corrêa
 * @since 31/03/2006
 */
public class ExibirFiltrarEquipeAction extends GcomAction {

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
		ActionForward retorno = actionMapping
				.findForward("exibirFiltrarEquipe");

		FiltrarEquipeActionForm filtrarEquipeActionForm = (FiltrarEquipeActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarEquipeActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}	
		Fachada fachada = Fachada.getInstancia();

		
		
		sessao.removeAttribute("equipeAtualizar");
		// -Erivan- Verifica se o usuário solicitou pesquisa de usuario responsável pela execussão do serviço;
		if(httpServletRequest.getParameter("tipoPesquisa") != null &&
				!httpServletRequest.getParameter("tipoPesquisa").equals("")){
			
			if(httpServletRequest.getParameter("tipoPesquisa").equals("usuario")){
				//Procura pelo id de usuário informado
				Usuario usuario = buscarUsuario(filtrarEquipeActionForm.getCdUsuarioRespExecServico());
				if(usuario != null){
					filtrarEquipeActionForm.setCdUsuarioRespExecServico(usuario.getId().toString());
					filtrarEquipeActionForm.setNomeUsuarioRespExecServico(usuario.getNomeUsuario().toUpperCase());
					sessao.setAttribute("usuarioRespExecServicoEncontrado", true);
				}else{
					filtrarEquipeActionForm.setNomeUsuarioRespExecServico("USUÁRIO INEXISTENTE");
					sessao.removeAttribute("usuarioRespExecServicoEncontrado");
				}
			}
			
		}else{
			if(filtrarEquipeActionForm.getNomeUsuarioRespExecServico() != null){
				if(filtrarEquipeActionForm.getNomeUsuarioRespExecServico().equals("USUÁRIO INEXISTENTE")){
					filtrarEquipeActionForm.setCdUsuarioRespExecServico("");
					filtrarEquipeActionForm.setNomeUsuarioRespExecServico("");
				}
			}
		}
		
		if (httpServletRequest.getParameter("menu") != null) {

			filtrarEquipeActionForm.setAtualizar("1");
			filtrarEquipeActionForm.setIndicadorUso("");
			filtrarEquipeActionForm.setIndicadorProgramacaoAutomatica("");
			httpServletRequest.setAttribute("nomeCampo", "codigo");
			sessao.setAttribute("atualizar", "1");

		} else {

			if (httpServletRequest.getParameter("paginacao") == null && sessao.getAttribute("filtrar") == null) {

				String atualizar = httpServletRequest.getParameter("atualizar");

				if (atualizar != null && !atualizar.equals("")) {
					sessao.setAttribute("atualizar", atualizar);
				} else {
					sessao.removeAttribute("atualizar");
				}

			}
		}
		
		if (sessao.getAttribute("filtrar") != null) {
			httpServletRequest.setAttribute("nomeCampo", "codigo");
		}

		sessao.removeAttribute("filtroEquipe");

		// Recupera os valores da unidade do form
		String idUnidade = filtrarEquipeActionForm.getIdUnidade();
		String nomeUnidade = filtrarEquipeActionForm.getNomeUnidade();

		// Verifica se o usuário solicitou a pesquisa de unidade
		if (idUnidade != null && !idUnidade.trim().equals("")
				&& (nomeUnidade == null || nomeUnidade.trim().equals(""))) {
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID, idUnidade));

			Collection colecaoUnidade = fachada.pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class
							.getName());

			if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {

				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util
						.retonarObjetoDeColecao(colecaoUnidade);

				filtrarEquipeActionForm.setNomeUnidade(unidadeOrganizacional
						.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "idFuncionario");

			} else {

				filtrarEquipeActionForm.setIdUnidade("");
				filtrarEquipeActionForm.setNomeUnidade("Unidade inexistente");
				httpServletRequest.setAttribute("idUnidadeNaoEncontrado",
						"true");
				httpServletRequest.setAttribute("nomeCampo", "idUnidade");

			}

		} else if (nomeUnidade != null && !nomeUnidade.trim().equals("")
				&& (idUnidade == null || idUnidade.trim().equals(""))) {
			filtrarEquipeActionForm.setNomeUnidade("");
		}

		// Recupera os valores do funcionário do form
		String idFuncionario = filtrarEquipeActionForm.getIdFuncionario();
		String nomeFuncionario = filtrarEquipeActionForm.getNomeFuncionario();

		// Verifica se o usuário solicitou a pesquisa de funcionário
		if (idFuncionario != null
				&& !idFuncionario.trim().equals("")
				&& (nomeFuncionario == null || nomeFuncionario.trim()
						.equals(""))) {
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, idFuncionario));

			Collection colecaoFuncionario = fachada.pesquisar(
					filtroFuncionario, Funcionario.class.getName());

			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

				Funcionario funcionario = (Funcionario) Util
						.retonarObjetoDeColecao(colecaoFuncionario);

				filtrarEquipeActionForm.setNomeFuncionario(funcionario
						.getNome());
				httpServletRequest.setAttribute("nomeCampo", "idPerfilServico");

			} else {

				filtrarEquipeActionForm.setIdFuncionario("");
				filtrarEquipeActionForm
						.setNomeFuncionario("Funcionário inexistente");
				httpServletRequest.setAttribute("idFuncionarioNaoEncontrado",
						"true");
				httpServletRequest.setAttribute("nomeCampo", "idFuncionario");

			}

		} else if (nomeFuncionario != null
				&& !nomeFuncionario.trim().equals("")
				&& (idFuncionario == null || idFuncionario.trim().equals(""))) {
			filtrarEquipeActionForm.setNomeFuncionario("");
		}

		// Recupera os valores do serviço perfil tipo do form
		String idPerfilServico = filtrarEquipeActionForm.getIdPerfilServico();
		String descricaoPerfilServico = filtrarEquipeActionForm
				.getDescricaoPerfilServico();

		// Verifica se o usuário solicitou a pesquisa de funcionário
		if (idPerfilServico != null
				&& !idPerfilServico.trim().equals("")
				&& (descricaoPerfilServico == null || descricaoPerfilServico
						.trim().equals(""))) {
			FiltroServicoPerfilTipo filtroServicoPerfilTipo = new FiltroServicoPerfilTipo();
			filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoPerfilTipo.ID, idPerfilServico));

			Collection colecaoPerfilServico = fachada.pesquisar(
					filtroServicoPerfilTipo, ServicoPerfilTipo.class.getName());

			if (colecaoPerfilServico != null && !colecaoPerfilServico.isEmpty()) {

				ServicoPerfilTipo servicoPerfilTipo = (ServicoPerfilTipo) Util
						.retonarObjetoDeColecao(colecaoPerfilServico);

				filtrarEquipeActionForm
						.setDescricaoPerfilServico(servicoPerfilTipo
								.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "indicadorUso");

			} else {

				filtrarEquipeActionForm.setIdPerfilServico("");
				filtrarEquipeActionForm
						.setDescricaoPerfilServico("Serviço Tipo Perfil inexistente");
				httpServletRequest.setAttribute("idServicoPerfilNaoEncontrado",
						"true");
				httpServletRequest.setAttribute("nomeCampo", "idPerfilServico");

			}

		} else if (descricaoPerfilServico != null
				&& !descricaoPerfilServico.trim().equals("")
				&& (idPerfilServico == null || idPerfilServico.trim()
						.equals(""))) {
			filtrarEquipeActionForm.setDescricaoPerfilServico("");
		}
		
		FiltroEquipamentosEspeciais filtro = new FiltroEquipamentosEspeciais();
		filtro.adicionarParametro(
			new ParametroSimples(
				FiltroEquipamentosEspeciais.INDICADORUSO, 
				ConstantesSistema.SIM));
		
		Collection colecaoEquipamentosEspeciais = 
			this.getFachada().pesquisar(filtro, EquipamentosEspeciais.class.getName());
		
		httpServletRequest.setAttribute("colecaoEquipamentosEspeciais",colecaoEquipamentosEspeciais);

		return retorno;

	}
	
	/**
	 * Pequisa o usuário com o id correspondente ao informado
	 * @author Erivan
	 * @param String codigoUsuario
	 * @return Usuario
	 */
	private Usuario buscarUsuario(String codigoUsuario){
		Usuario usuario = null;
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, codigoUsuario));
		
		Collection colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
		
		if(colecaoUsuario != null && !colecaoUsuario.isEmpty()){
			usuario = (Usuario) colecaoUsuario.iterator().next();
		}else{
			filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.FUNCIONARIO_ID, codigoUsuario));
			colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
			
			if(colecaoUsuario != null && !colecaoUsuario.isEmpty()){
				usuario = (Usuario) colecaoUsuario.iterator().next();
			}else{
				filtroUsuario = new FiltroUsuario();
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, codigoUsuario));
				colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
				
			}
		}
		
		return usuario;
	}
}
