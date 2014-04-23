package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoGrupo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
 * @date 07/11/2006
 */
public class ExibirAtualizarTipoSolicitacaoEspecificacaoAction extends
		GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarTipoSolicitacaoEspecificacao");

		AtualizarTipoSolicitacaoEspecificacaoActionForm atualizarTipoSolicitacaoEspecificacaoActionForm = (AtualizarTipoSolicitacaoEspecificacaoActionForm) actionForm;

		String id = null;

		String idSolicitacaoTipo = null;

		if (httpServletRequest.getParameter("idTipoSolicitacao") != null
				&& !httpServletRequest.getParameter("idTipoSolicitacao")
						.equals("")) {

			if (sessao.getAttribute("adicionar") != null) {

				sessao.removeAttribute("objetoTipoSolicitacao");
				sessao.removeAttribute("adicionar");

			} else {
				sessao.removeAttribute("objetoTipoSolicitacao");
				sessao.removeAttribute("colecaoTipoSolicitacao");
			}

		}

		// Verifica se veio do filtrar ou do manter

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		Fachada fachada = Fachada.getInstancia();
		
		// Verificar as permissão especial para alterar o indicador de uso do sistema 
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		boolean temPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao = fachada
				.verificarPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao(usuario);
		if (temPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao) {
			httpServletRequest.setAttribute("temPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao",
				temPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao);
		}

		FiltroSolicitacaoTipoGrupo filtroSolicitacaoTipoGrupo = new FiltroSolicitacaoTipoGrupo();
		filtroSolicitacaoTipoGrupo.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipoGrupo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoSolicitacaoTipoGrupo = fachada.pesquisar(
				filtroSolicitacaoTipoGrupo, SolicitacaoTipoGrupo.class
						.getName());
		httpServletRequest.setAttribute("colecaoSolicitacaoTipoGrupo",
				colecaoSolicitacaoTipoGrupo);

		// Verifica se o servicoCobrancaValor já está na sessão, em caso
		// afirmativo
		// significa que o usuário já entrou na tela e apenas selecionou algum
		// item que deu um reload na tela e em caso negativo significa que ele
		// está entrando pela primeira vez

		if (sessao.getAttribute("colecaoTipoSolicitacaoTela") == null) {

			if (sessao.getAttribute("objetoSolicitacaoTipo") != null) {

				SolicitacaoTipo solicitacaoTipo = (SolicitacaoTipo) sessao
						.getAttribute("objetoSolicitacaoTipo");

				sessao.setAttribute("idTipoSolicitacao", solicitacaoTipo
						.getId().toString());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIdTipoSolicitacao(solicitacaoTipo.getId()
								.toString());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setDescricao(solicitacaoTipo.getDescricao());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIdgrupoTipoSolicitacao(solicitacaoTipo
								.getSolicitacaoTipoGrupo().getId().toString());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorFaltaAgua(""
								+ solicitacaoTipo.getIndicadorFaltaAgua());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorTarifaSocial(""
								+ solicitacaoTipo.getIndicadorTarifaSocial());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorUso("" + solicitacaoTipo.getIndicadorUso());
				
				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorUsoSistema(""
								+ solicitacaoTipo.getIndicadorUsoSistema());

				id = solicitacaoTipo.getId().toString();

				sessao.setAttribute("idSolicitacaoTipo", solicitacaoTipo
						.getId().toString());

				sessao
						.setAttribute("solicitacaoTipoAtualizar",
								solicitacaoTipo);
				sessao.removeAttribute("objetoSolicitacaoTipo");

				/*
				 * Faz o filtro pesquisando o tipo de especificação da
				 * solicitação
				 */
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO,
								solicitacaoTipo.getId().toString()));

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipo");
				
				filtroSolicitacaoTipoEspecificacao
				.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("especificacaoImovelSituacao");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("especificacaoServicoTipos");
				
				filtroSolicitacaoTipoEspecificacao
				.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ESPECIFICACAO_NOVO_RA);

				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO,
								solicitacaoTipo.getId().toString()));

				filtroSolicitacaoTipoEspecificacao
				.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO);
				
				Collection colecaoSolicitacaoTipoEspecificacao = fachada
						.pesquisar(filtroSolicitacaoTipoEspecificacao,
								SolicitacaoTipoEspecificacao.class.getName());

				
				if (colecaoSolicitacaoTipoEspecificacao == null
						|| colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
					colecaoSolicitacaoTipoEspecificacao = new ArrayList();

				}

				sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
						colecaoSolicitacaoTipoEspecificacao);

			} else {

				SolicitacaoTipo solicitacaoTipo = null;

				// idSolicitacaoTipo = null;

				if (httpServletRequest.getParameter("idTipoSolicitacao") == null
						|| httpServletRequest.getParameter("idTipoSolicitacao")
								.equals("")) {
					solicitacaoTipo = (SolicitacaoTipo) sessao
							.getAttribute("objetoSolicitacaoTipo");
				} else {
					idSolicitacaoTipo = (String) httpServletRequest
							.getParameter("idTipoSolicitacao");
					sessao.setAttribute("idTipoSolicitacao", idSolicitacaoTipo);
				}

				httpServletRequest.setAttribute("idTipoSolicitacao",
						idSolicitacaoTipo);

				if (idSolicitacaoTipo != null) {

					id = idSolicitacaoTipo;

					FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();

					filtroSolicitacaoTipo
							.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoGrupo");

					filtroSolicitacaoTipo
							.adicionarParametro(new ParametroSimples(
									FiltroSolicitacaoTipo.ID, idSolicitacaoTipo));

					Collection<SolicitacaoTipo> colecaoSolicitacaoTipo = fachada
							.pesquisar(filtroSolicitacaoTipo,
									SolicitacaoTipo.class.getName());

					if (colecaoSolicitacaoTipo == null
							|| colecaoSolicitacaoTipo.isEmpty()) {
						throw new ActionServletException(
								"atencao.atualizacao.timestamp");
					}

					httpServletRequest.setAttribute("colecaoSolicitacaoTipo",
							colecaoSolicitacaoTipo);

					solicitacaoTipo = (SolicitacaoTipo) colecaoSolicitacaoTipo
							.iterator().next();

				}

				if (solicitacaoTipo == null) {

					FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
					filtroSolicitacaoTipo
							.adicionarParametro(new ParametroSimples(
									FiltroSolicitacaoTipo.ID, sessao
											.getAttribute("idTipoSolicitacao")));

					Collection colecaoSolicitacaoTipo = fachada.pesquisar(
							filtroSolicitacaoTipo, SolicitacaoTipo.class
									.getName());

					solicitacaoTipo = (SolicitacaoTipo) colecaoSolicitacaoTipo
							.iterator().next();

				}

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIdTipoSolicitacao(solicitacaoTipo.getId()
								.toString());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setDescricao(solicitacaoTipo.getDescricao());

				if (solicitacaoTipo.getSolicitacaoTipoGrupo() != null) {
					atualizarTipoSolicitacaoEspecificacaoActionForm
							.setIdgrupoTipoSolicitacao(solicitacaoTipo
									.getSolicitacaoTipoGrupo().getId()
									.toString());
				} else {
					atualizarTipoSolicitacaoEspecificacaoActionForm
							.setIdgrupoTipoSolicitacao("");
				}

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorFaltaAgua(""
								+ solicitacaoTipo.getIndicadorFaltaAgua());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorTarifaSocial(""
								+ solicitacaoTipo.getIndicadorTarifaSocial());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorUso("" + solicitacaoTipo.getIndicadorUso());
				
				atualizarTipoSolicitacaoEspecificacaoActionForm
				.setIndicadorUsoSistema(""
						+ solicitacaoTipo.getIndicadorUsoSistema());

				sessao.setAttribute("idSolicitacaoTipo", solicitacaoTipo
						.getId().toString());

				sessao
						.setAttribute("solicitacaoTipoAtualizar",
								solicitacaoTipo);

				Collection colecaoSolicitacaoTipoEspecificacao = (Collection) sessao
						.getAttribute("colecaoSolicitacaoTipoEspecificacao");

				if (colecaoSolicitacaoTipoEspecificacao == null
						|| colecaoSolicitacaoTipoEspecificacao.isEmpty()) {

					/*
					 * Faz o filtro pesquisando o tipo de especificação da
					 * solicitação
					 */
					FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
					filtroSolicitacaoTipoEspecificacao
							.adicionarParametro(new ParametroSimples(
									FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO,
									solicitacaoTipo.getId().toString()));

					filtroSolicitacaoTipoEspecificacao
							.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipo");
					
					filtroSolicitacaoTipoEspecificacao
					.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");

					filtroSolicitacaoTipoEspecificacao
							.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");

					filtroSolicitacaoTipoEspecificacao
							.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");

					filtroSolicitacaoTipoEspecificacao
							.adicionarCaminhoParaCarregamentoEntidade("especificacaoImovelSituacao");

					filtroSolicitacaoTipoEspecificacao
							.adicionarCaminhoParaCarregamentoEntidade("especificacaoServicoTipos");
                    
                    filtroSolicitacaoTipoEspecificacao
                        .adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacaoNovoRA.solicitacaoTipo");

					filtroSolicitacaoTipoEspecificacao
							.adicionarParametro(new ParametroSimples(
									FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO,
									solicitacaoTipo.getId().toString()));

					colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(
							filtroSolicitacaoTipoEspecificacao,
							SolicitacaoTipoEspecificacao.class.getName());

					if (colecaoSolicitacaoTipoEspecificacao == null
							|| colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
						colecaoSolicitacaoTipoEspecificacao = new ArrayList();

					}

					sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
							colecaoSolicitacaoTipoEspecificacao);

				}
			}
		}

		// Verifica se o usuário removeu um componente e em caso afirmativo
		// remove o componente da coleção
		if (httpServletRequest.getParameter("deleteComponente") != null
				&& !httpServletRequest.getParameter("deleteComponente").equals(
						"")) {

			Collection colecaoSolicitacaoTipoEspecificacaoRemovidos = null;

			Collection colecaoSolicitacaoTipoEspecificacao = (Collection) sessao
					.getAttribute("colecaoSolicitacaoTipoEspecificacao");

			if (colecaoSolicitacaoTipoEspecificacao != null
					&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {

				int posicaoComponente = new Integer(httpServletRequest
						.getParameter("deleteComponente")).intValue();

				int index = 0;

				Iterator colecaoSolicitacaoTipoEspecificacaoIterator = colecaoSolicitacaoTipoEspecificacao
						.iterator();

				while (colecaoSolicitacaoTipoEspecificacaoIterator.hasNext()) {

					index++;

					SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) colecaoSolicitacaoTipoEspecificacaoIterator
							.next();

					if (index == posicaoComponente) {

						if (solicitacaoTipoEspecificacao.getId() != null) {

							if (sessao
									.getAttribute("colecaoSolicitacaoTipoEspecificacaoRemovidos") == null
									|| Util.isVazioOrNulo(colecaoSolicitacaoTipoEspecificacaoRemovidos)) {
								
								colecaoSolicitacaoTipoEspecificacaoRemovidos = new ArrayList();
							} else {
								colecaoSolicitacaoTipoEspecificacaoRemovidos = (Collection) sessao
										.getAttribute("colecaoSolicitacaoTipoEspecificacaoRemovidos");
							}

							colecaoSolicitacaoTipoEspecificacaoRemovidos
									.add(solicitacaoTipoEspecificacao);
							sessao
									.setAttribute(
											"colecaoSolicitacaoTipoEspecificacaoRemovidos",
											colecaoSolicitacaoTipoEspecificacaoRemovidos);
						}

						colecaoSolicitacaoTipoEspecificacao
								.remove(solicitacaoTipoEspecificacao);

						atualizarTipoSolicitacaoEspecificacaoActionForm
								.setTamanhoColecao(""
										+ colecaoSolicitacaoTipoEspecificacao
												.size());

						sessao.setAttribute(
								"colecaoSolicitacaoTipoEspecificacao",
								colecaoSolicitacaoTipoEspecificacao);

						break;
					}
				}
			}
		}

		// -------------- bt DESFAZER ---------------

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			sessao.removeAttribute("colecaoSolicitacaoTipoTela");

			String solicitacaoTipoID = null;

			if (httpServletRequest.getParameter("idTipoSolicitacao") == null
					|| httpServletRequest.getParameter("idTipoSolicitacao")
							.equals("")) {
				solicitacaoTipoID = (String) sessao
						.getAttribute("idTipoSolicitacao");
			} else {
				solicitacaoTipoID = (String) httpServletRequest
						.getParameter("idTipoSolicitacao");
				sessao.setAttribute("idTipoSolicitacao", solicitacaoTipoID);
			}

			if (solicitacaoTipoID.equalsIgnoreCase("")) {
				solicitacaoTipoID = null;
			}

			if ((solicitacaoTipoID == null) && (id == null)) {

				SolicitacaoTipo solicitacaoTipo = (SolicitacaoTipo) sessao
						.getAttribute("objetoSolicitacaoTipo");

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIdTipoSolicitacao(solicitacaoTipo.getId()
								.toString());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setDescricao(solicitacaoTipo.getDescricao());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorFaltaAgua(""
								+ solicitacaoTipo.getIndicadorFaltaAgua());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorTarifaSocial(""
								+ solicitacaoTipo.getIndicadorTarifaSocial());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorUso("" + solicitacaoTipo.getIndicadorUso());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIdgrupoTipoSolicitacao(solicitacaoTipo
								.getSolicitacaoTipoGrupo().getId().toString());

				sessao.setAttribute("idSolicitacaoTipo", solicitacaoTipo
						.getId().toString());

				sessao
						.setAttribute("solicitacaoTipoAtualizar",
								solicitacaoTipo);
				sessao.removeAttribute("solicitacaoTipo");

				/*
				 * Faz o filtro pesquisando o tipo de especificação da
				 * solicitação
				 */
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO,
								solicitacaoTipo.getId().toString()));

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipo");
				
				filtroSolicitacaoTipoEspecificacao
				.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("especificacaoImovelSituacao");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("especificacaoServicoTipos");
				
				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ESPECIFICACAO_NOVO_RA);

				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO,
								solicitacaoTipo.getId().toString()));

				Collection colecaoSolicitacaoTipoEspecificacao = fachada
						.pesquisar(filtroSolicitacaoTipoEspecificacao,
								SolicitacaoTipoEspecificacao.class.getName());

				if (colecaoSolicitacaoTipoEspecificacao == null
						|| colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
					colecaoSolicitacaoTipoEspecificacao = new ArrayList();

				}

				sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
						colecaoSolicitacaoTipoEspecificacao);

			}

			if ((solicitacaoTipoID == null) && (id != null)) {

				solicitacaoTipoID = id;
			}

			if (solicitacaoTipoID != null) {

				FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();

				filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
						FiltroSolicitacaoTipo.ID, solicitacaoTipoID));

				Collection<SolicitacaoTipo> colecaoSolicitacaoTipo = fachada
						.pesquisar(filtroSolicitacaoTipo, SolicitacaoTipo.class
								.getName());

				if (colecaoSolicitacaoTipo == null
						|| colecaoSolicitacaoTipo.isEmpty()) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}

				httpServletRequest.setAttribute("colecaoSolicitacaoTipo",
						colecaoSolicitacaoTipo);

				SolicitacaoTipo solicitacaoTipo = (SolicitacaoTipo) colecaoSolicitacaoTipo
						.iterator().next();

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIdTipoSolicitacao(solicitacaoTipo.getId()
								.toString());

				sessao.setAttribute("idSolicitacaoTipo", solicitacaoTipo
						.getId().toString());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIdgrupoTipoSolicitacao(solicitacaoTipo
								.getSolicitacaoTipoGrupo().getId().toString());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setDescricao(solicitacaoTipo.getDescricao());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorFaltaAgua(""
								+ solicitacaoTipo.getIndicadorFaltaAgua());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorTarifaSocial(""
								+ solicitacaoTipo.getIndicadorTarifaSocial());

				atualizarTipoSolicitacaoEspecificacaoActionForm
						.setIndicadorUso("" + solicitacaoTipo.getIndicadorUso());
				
				atualizarTipoSolicitacaoEspecificacaoActionForm
				.setIndicadorUsoSistema(""
						+ solicitacaoTipo.getIndicadorUsoSistema());

				httpServletRequest.setAttribute("idSolicitacaoTipo",
						solicitacaoTipoID);
				sessao
						.setAttribute("solicitacaoTipoAtualizar",
								solicitacaoTipo);

				// if
				// (sessao.getAttribute("colecaoSolicitacaoTipoEspecificacao")
				// == null) {

				/*
				 * Faz o filtro pesquisando o tipo de especificação da
				 * solicitação
				 */
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO,
								solicitacaoTipo.getId().toString()));

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipo");
				
				filtroSolicitacaoTipoEspecificacao
				.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("especificacaoImovelSituacao");

				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade("especificacaoServicoTipos");
				
				filtroSolicitacaoTipoEspecificacao
						.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ESPECIFICACAO_NOVO_RA);

				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO,
								solicitacaoTipo.getId().toString()));

				Collection colecaoSolicitacaoTipoEspecificacao = fachada
						.pesquisar(filtroSolicitacaoTipoEspecificacao,
								SolicitacaoTipoEspecificacao.class.getName());

				if (colecaoSolicitacaoTipoEspecificacao == null
						|| colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
					colecaoSolicitacaoTipoEspecificacao = new ArrayList();

				}

				sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
						colecaoSolicitacaoTipoEspecificacao);

				// }
			}
		}
		// -------------- bt DESFAZER ---------------

		httpServletRequest.setAttribute("colecaoSolicitacaoTipoTela", sessao
				.getAttribute("colecaoSolicitacaoTipoTela"));

		return retorno;
	}

}
