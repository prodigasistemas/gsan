package gcom.gui.cobranca;

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.FiltroUnidadeOrganizacionalTestemunha;
import gcom.cobranca.UnidadeOrganizacionalTestemunha;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
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
 * que ele insera uma resolução de diretoria [UC0217] Inserir Resolução de
 * Diretoria
 * 
 * @author Rafael Corrêa
 * @since 30/03/2006
 */
public class ExibirInformarUnidadeOrganizacionalTestemunhaAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInformarUnidadeOrganizacionalTestemunha");
		
		InformarUnidadeOrganizacionalTestemunhaActionForm informarUnidadeOrganizacionalTestemunhaActionForm = (InformarUnidadeOrganizacionalTestemunhaActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String idUnidadeOrganizacional = informarUnidadeOrganizacionalTestemunhaActionForm.getIdUnidadeOrganizacional();
		String idTestemunha = informarUnidadeOrganizacionalTestemunhaActionForm.getIdTestemunha();
		String loginTestemunha = informarUnidadeOrganizacionalTestemunhaActionForm.getLoginTestemunha();
		
		// Pesquisa a unidade organizacional
		if (idUnidadeOrganizacional != null && !idUnidadeOrganizacional.trim().equals("")) {
			
			// Verifica se deve desabilitar o botão adicionar e a testemunha
			if (httpServletRequest.getParameter("desabilitaCampos") != null
					&& !httpServletRequest.getParameter("desabilitaCampos")
							.trim().equals("")) {
				httpServletRequest.setAttribute("desabilitaCampos", "sim");
			}
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidadeOrganizacional));
			
			Collection colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {
				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
				informarUnidadeOrganizacionalTestemunhaActionForm.setIdUnidadeOrganizacional(unidadeOrganizacional.getId().toString());
				informarUnidadeOrganizacionalTestemunhaActionForm.setNomeUnidadeOrganizacional(unidadeOrganizacional.getDescricao());
			} else {
				informarUnidadeOrganizacionalTestemunhaActionForm.setIdUnidadeOrganizacional("");
				informarUnidadeOrganizacionalTestemunhaActionForm.setNomeUnidadeOrganizacional("UNIDADE INEXISTENTE");
				
				httpServletRequest.setAttribute("unidadeOrganizacionalInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeOrganizacional");
			}
			
		} else {
			informarUnidadeOrganizacionalTestemunhaActionForm.setNomeUnidadeOrganizacional("");
		}
		
		// Pesquisa a testemunha
		if ((loginTestemunha != null && !loginTestemunha.trim().equals("")) || (idTestemunha != null && !idTestemunha.trim().equals(""))) {
			
			// Verifica se deve desabilitar o botão adicionar e a testemunha,
			// caso o usuário
			// tenha alterado a unidade de negócio deixa o botão desabilitado
			if (httpServletRequest.getParameter("desabilitaCampos") != null
					&& !httpServletRequest.getParameter("desabilitaCampos")
							.trim().equals("")) {
				httpServletRequest.setAttribute("desabilitaCampos", "sim");
			}
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.UNIDADE_ORGANIZACIONAL_ID, idUnidadeOrganizacional));
			
			// Verifica se a pesquisa foi pelo enter
			if ((loginTestemunha != null && !loginTestemunha.trim().equals(""))) {
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, loginTestemunha));
			} 
			// Verifica se a pesquisa foi feita pela lupa
			else {
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, idTestemunha));
			}
			
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				informarUnidadeOrganizacionalTestemunhaActionForm.setIdTestemunha(usuario.getId().toString());
				informarUnidadeOrganizacionalTestemunhaActionForm.setLoginTestemunha(usuario.getLogin());
				informarUnidadeOrganizacionalTestemunhaActionForm.setNomeTestemunha(usuario.getNomeUsuario());
			} else {
				informarUnidadeOrganizacionalTestemunhaActionForm.setIdTestemunha("");
				informarUnidadeOrganizacionalTestemunhaActionForm.setLoginTestemunha("");
				informarUnidadeOrganizacionalTestemunhaActionForm.setNomeTestemunha("TESTEMUNHA INEXISTENTE");
				
				httpServletRequest.setAttribute("testemunhaInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "loginUsuario");
			}
			
		} else {
			informarUnidadeOrganizacionalTestemunhaActionForm.setNomeTestemunha("");
		}
		
		// Coleção retornada na consulta
		Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunha = (Collection<UnidadeOrganizacionalTestemunha>) sessao.getAttribute("colecaoUnidadeOrganizacionalTestemunha");
		
		// Coleção com os objetos adicionados pelo usuário
		Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunhaAdicionadas = (Collection<UnidadeOrganizacionalTestemunha>) sessao.getAttribute("colecaoUnidadeOrganizacionalTestemunhaAdicionadas");
		
		// Coleção com os objetos removidos pelo usuário
		Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunhaRemovidas = (Collection<UnidadeOrganizacionalTestemunha>) sessao
		.getAttribute("colecaoUnidadeOrganizacionalTestemunhaRemovidas");
		
		// Consulta as Unidades de Negócio Testemunha
		if (httpServletRequest.getParameter("consultar") != null && !httpServletRequest.getParameter("consultar").equals("")) {
			
			colecaoUnidadeOrganizacionalTestemunhaAdicionadas = new ArrayList<UnidadeOrganizacionalTestemunha>();
			colecaoUnidadeOrganizacionalTestemunhaRemovidas = new ArrayList<UnidadeOrganizacionalTestemunha>();
			
			sessao.setAttribute("colecaoUnidadeOrganizacionalTestemunhaAdicionadas", colecaoUnidadeOrganizacionalTestemunhaAdicionadas);
			sessao.setAttribute("colecaoUnidadeOrganizacionalTestemunhaRemovidas", colecaoUnidadeOrganizacionalTestemunhaRemovidas);
			
			colecaoUnidadeOrganizacionalTestemunha = consultarUnidadeOrganizacionalTestemunha(idUnidadeOrganizacional);
			
			if (colecaoUnidadeOrganizacionalTestemunha == null) {
				colecaoUnidadeOrganizacionalTestemunha = new ArrayList<UnidadeOrganizacionalTestemunha>();
			}
			
			sessao.setAttribute("colecaoUnidadeOrganizacionalTestemunha", colecaoUnidadeOrganizacionalTestemunha);
		}
		
		// Adiciona a Unidade de Negócio Testemunha
		if (httpServletRequest.getParameter("adicionar") != null && !httpServletRequest.getParameter("adicionar").equals("")) {
			
			UnidadeOrganizacionalTestemunha unidadeNegocioTestemunha = criarUnidadeOrganizacionalTestemunha(idUnidadeOrganizacional, loginTestemunha);
			
			colecaoUnidadeOrganizacionalTestemunhaAdicionadas = adicionarUnidadeOrganizacionalTestemunha(colecaoUnidadeOrganizacionalTestemunha, colecaoUnidadeOrganizacionalTestemunhaAdicionadas, unidadeNegocioTestemunha);
			
			sessao.setAttribute("colecaoUnidadeOrganizacionalTestemunhaAdicionadas",
					colecaoUnidadeOrganizacionalTestemunhaAdicionadas);
			
		}
		
		// Remove a Unidade de Negócio Testemunha
		if (httpServletRequest.getParameter("removerUnidadeOrganizacionalTestemunha") != null && !httpServletRequest.getParameter("removerUnidadeOrganizacionalTestemunha").equals("")) {
			
			Integer posicaoRemocao = new Integer(httpServletRequest
					.getParameter("removerUnidadeOrganizacionalTestemunha"));

			removerUnidadeOrganizacionalTestemunha(colecaoUnidadeOrganizacionalTestemunha,
					posicaoRemocao, colecaoUnidadeOrganizacionalTestemunhaRemovidas,
					colecaoUnidadeOrganizacionalTestemunhaAdicionadas);

			sessao.setAttribute("colecaoUnidadeOrganizacionalTestemunhaRemovidas",
					colecaoUnidadeOrganizacionalTestemunhaRemovidas);
			
		}

		return retorno;

	}

	/**
	 * Adiciona a Unidade de Negócio Testemunha selecionada pelo usuário 
	 *
	 * @author Rafael Corrêa
	 * @date 19/05/2008
	 *
	 * @param colecaoUnidadeOrganizacionalTestemunha
	 * @param colecaoUnidadeOrganizacionalTestemunhaAdicionadas
	 * @param unidadeNegocioTestemunha
	 * @return
	 */
	private Collection<UnidadeOrganizacionalTestemunha> adicionarUnidadeOrganizacionalTestemunha(
			Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunha,
			Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunhaAdicionadas,
			UnidadeOrganizacionalTestemunha unidadeOrganizacionalTestemunhaAdicionar) {
		if (colecaoUnidadeOrganizacionalTestemunhaAdicionadas == null) {
			colecaoUnidadeOrganizacionalTestemunhaAdicionadas = new ArrayList<UnidadeOrganizacionalTestemunha>();
		} else {
			// [FS0001] - Validar data relação fim
			for (UnidadeOrganizacionalTestemunha unidadeNegocioTestemunha : colecaoUnidadeOrganizacionalTestemunha) {
				if (unidadeNegocioTestemunha.getDataFimRelacao() == null
						&& unidadeNegocioTestemunha.getUsuario().getId()
								.equals(
										unidadeOrganizacionalTestemunhaAdicionar
												.getUsuario().getId())) {
					throw new ActionServletException(
							"atencao.testemunha.ja.existente",
							unidadeOrganizacionalTestemunhaAdicionar
									.getUnidadeOrganizacional().getDescricao(),
							unidadeOrganizacionalTestemunhaAdicionar.getUsuario()
									.getNomeUsuario());
				}
			}
		}
		
		colecaoUnidadeOrganizacionalTestemunhaAdicionadas.add(unidadeOrganizacionalTestemunhaAdicionar);
		colecaoUnidadeOrganizacionalTestemunha.add(unidadeOrganizacionalTestemunhaAdicionar);
		
		return colecaoUnidadeOrganizacionalTestemunhaAdicionadas;
	}

	/**
	 * Executa a consulta feita pelo usuário
	 *
	 * @author Rafa
	 * @date 16/05/2008
	 *
	 * @param idUnidadeOrganizacional
	 * @param idTestemunha
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Collection<UnidadeOrganizacionalTestemunha> consultarUnidadeOrganizacionalTestemunha(String idUnidadeOrganizacional) {
		Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunha;
		FiltroUnidadeOrganizacionalTestemunha filtroUnidadeOrganizacionalTestemunha = new FiltroUnidadeOrganizacionalTestemunha();
		filtroUnidadeOrganizacionalTestemunha.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacionalTestemunha.UNIDADE_ORGANIZACIONAL);
		filtroUnidadeOrganizacionalTestemunha.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacionalTestemunha.USUARIO);
		
		filtroUnidadeOrganizacionalTestemunha.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacionalTestemunha.UNIDADE_ORGANIZACIONAL_ID, idUnidadeOrganizacional));
		
		colecaoUnidadeOrganizacionalTestemunha = Fachada.getInstancia().pesquisar(filtroUnidadeOrganizacionalTestemunha, UnidadeOrganizacionalTestemunha.class.getName());
		
		return colecaoUnidadeOrganizacionalTestemunha;
	}
	
	/**
	 * Remove a Unidade de Negócio Testemunha  
	 *
	 * @author Rafael Corrêa
	 * @date 16/05/2008
	 *
	 * @param colecaoUnidadeOrganizacionalTestemunha
	 * @param posicao
	 */
	private void removerUnidadeOrganizacionalTestemunha(Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunha, int posicaoRemocao, Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunhaRemovidas, Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunhaAdicionadas) {
		int i = 0;
		
		for (UnidadeOrganizacionalTestemunha unidadeOrganizacionalTestemunha : colecaoUnidadeOrganizacionalTestemunha) {
			i++;
			
			// Verifica se é este o objeto a ser removido
			if (i == posicaoRemocao) {
				
				if (unidadeOrganizacionalTestemunha.getId() == null) {
					colecaoUnidadeOrganizacionalTestemunha.remove(unidadeOrganizacionalTestemunha);
					colecaoUnidadeOrganizacionalTestemunhaAdicionadas.remove(unidadeOrganizacionalTestemunha);
				} else {
					unidadeOrganizacionalTestemunha.setDataFimRelacao(new Date());
					
					if (colecaoUnidadeOrganizacionalTestemunhaRemovidas == null) {
						colecaoUnidadeOrganizacionalTestemunhaRemovidas = new ArrayList<UnidadeOrganizacionalTestemunha>();
					}
					
					colecaoUnidadeOrganizacionalTestemunhaRemovidas.add(unidadeOrganizacionalTestemunha);
				}
				
				break;
			}
		}
			
	}
	
	/**
	 * Cria a Unidade de Negócio Testemunha  
	 *
	 * @author Rafael Corrêa
	 * @date 16/05/2008
	 *
	 * @param idUnidadeOrganizacional
	 * @param idUsuario
	 */
	private UnidadeOrganizacionalTestemunha criarUnidadeOrganizacionalTestemunha(String idUnidadeOrganizacional, String loginUsuario) {
		
		Fachada fachada = Fachada.getInstancia();
		
		UnidadeOrganizacionalTestemunha retorno = new UnidadeOrganizacionalTestemunha();
		
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidadeOrganizacional));
		
		Collection colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		
		UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
		
		retorno.setUnidadeOrganizacional(unidadeOrganizacional);
		
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, loginUsuario));
		
		Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
		
		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
			Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
			retorno.setUsuario(usuario);
			retorno.setDataInicioRelacao(new Date());
		} else {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Testemunha");
		}
		
		return retorno;
		
	}

}
