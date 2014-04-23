package gcom.gui.cobranca;

import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.FiltroUnidadeNegocioTestemunha;
import gcom.cobranca.UnidadeNegocioTestemunha;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
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
public class ExibirInformarUnidadeNegocioTestemunhaAction extends GcomAction {

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
				.findForward("exibirInformarUnidadeNegocioTestemunha");
		
		InformarUnidadeNegocioTestemunhaActionForm informarUnidadeNegocioTestemunhaActionForm = (InformarUnidadeNegocioTestemunhaActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Carrega as coleções que serão exibidas na tela
		
		if (sessao.getAttribute("colecaoUnidadeNegocio") == null) {
			
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUnidadeNegocio = fachada.pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());

			if (colecaoUnidadeNegocio != null
					&& !colecaoUnidadeNegocio.isEmpty()) {
				sessao.setAttribute("colecaoUnidadeNegocio",
						colecaoUnidadeNegocio);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa.nenhum_registro_tabela", null,
						"UNIDADE_NEGOCIO");
			}
		}
		
		String idUnidadeNegocio = informarUnidadeNegocioTestemunhaActionForm.getIdUnidadeNegocio();
		String idTestemunha = informarUnidadeNegocioTestemunhaActionForm.getIdTestemunha();
		String loginTestemunha = informarUnidadeNegocioTestemunhaActionForm.getLoginTestemunha();
		
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
				informarUnidadeNegocioTestemunhaActionForm.setIdTestemunha(usuario.getId().toString());
				informarUnidadeNegocioTestemunhaActionForm.setLoginTestemunha(usuario.getLogin());
				informarUnidadeNegocioTestemunhaActionForm.setNomeTestemunha(usuario.getNomeUsuario());
			} else {
				informarUnidadeNegocioTestemunhaActionForm.setIdTestemunha("");
				informarUnidadeNegocioTestemunhaActionForm.setLoginTestemunha("");
				informarUnidadeNegocioTestemunhaActionForm.setNomeTestemunha("TESTEMUNHA INEXISTENTE");
				
				httpServletRequest.setAttribute("testemunhaInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idTestemunha");
			}
			
		} else {
			informarUnidadeNegocioTestemunhaActionForm.setNomeTestemunha("");
		}
		
		// Coleção retornada na consulta
		Collection<UnidadeNegocioTestemunha> colecaoUnidadeNegocioTestemunha = (Collection<UnidadeNegocioTestemunha>) sessao.getAttribute("colecaoUnidadeNegocioTestemunha");
		
		// Coleção com os objetos adicionados pelo usuário
		Collection<UnidadeNegocioTestemunha> colecaoUnidadeNegocioTestemunhaAdicionadas = (Collection<UnidadeNegocioTestemunha>) sessao.getAttribute("colecaoUnidadeNegocioTestemunhaAdicionadas");
		
		// Coleção com os objetos removidos pelo usuário
		Collection<UnidadeNegocioTestemunha> colecaoUnidadeNegocioTestemunhaRemovidas = (Collection<UnidadeNegocioTestemunha>) sessao
		.getAttribute("colecaoUnidadeNegocioTestemunhaRemovidas");
		
		// Consulta as Unidades de Negócio Testemunha
		if (httpServletRequest.getParameter("consultar") != null && !httpServletRequest.getParameter("consultar").equals("")) {
			
			colecaoUnidadeNegocioTestemunhaAdicionadas = new ArrayList<UnidadeNegocioTestemunha>();
			colecaoUnidadeNegocioTestemunhaRemovidas = new ArrayList<UnidadeNegocioTestemunha>();
			
			sessao.setAttribute("colecaoUnidadeNegocioTestemunhaAdicionadas", colecaoUnidadeNegocioTestemunhaAdicionadas);
			sessao.setAttribute("colecaoUnidadeNegocioTestemunhaRemovidas", colecaoUnidadeNegocioTestemunhaRemovidas);
			
			colecaoUnidadeNegocioTestemunha = consultarUnidadeNegocioTestemunha(idUnidadeNegocio);
			
			if (colecaoUnidadeNegocioTestemunha == null) {
				colecaoUnidadeNegocioTestemunha = new ArrayList<UnidadeNegocioTestemunha>();
			}
			
			sessao.setAttribute("colecaoUnidadeNegocioTestemunha", colecaoUnidadeNegocioTestemunha);
		}
		
		// Adiciona a Unidade de Negócio Testemunha
		if (httpServletRequest.getParameter("adicionar") != null && !httpServletRequest.getParameter("adicionar").equals("")) {
			
			UnidadeNegocioTestemunha unidadeNegocioTestemunha = criarUnidadeNegocioTestemunha(idUnidadeNegocio, loginTestemunha);
			
			colecaoUnidadeNegocioTestemunhaAdicionadas = adicionarUnidadeNegocioTestemunha(colecaoUnidadeNegocioTestemunha, colecaoUnidadeNegocioTestemunhaAdicionadas, unidadeNegocioTestemunha);
			
			sessao.setAttribute("colecaoUnidadeNegocioTestemunhaAdicionadas",
					colecaoUnidadeNegocioTestemunhaAdicionadas);
			
		}
		
		// Remove a Unidade de Negócio Testemunha
		if (httpServletRequest.getParameter("removerUnidadeNegocioTestemunha") != null && !httpServletRequest.getParameter("removerUnidadeNegocioTestemunha").equals("")) {
			
			Integer posicaoRemocao = new Integer(httpServletRequest
					.getParameter("removerUnidadeNegocioTestemunha"));

			removerUnidadeNegocioTestemunha(colecaoUnidadeNegocioTestemunha,
					posicaoRemocao, colecaoUnidadeNegocioTestemunhaRemovidas,
					colecaoUnidadeNegocioTestemunhaAdicionadas);

			sessao.setAttribute("colecaoUnidadeNegocioTestemunhaRemovidas",
					colecaoUnidadeNegocioTestemunhaRemovidas);
			
		}

		return retorno;

	}

	/**
	 * Adiciona a Unidade de Negócio Testemunha selecionada pelo usuário 
	 *
	 * @author Rafael Corrêa
	 * @date 19/05/2008
	 *
	 * @param colecaoUnidadeNegocioTestemunha
	 * @param colecaoUnidadeNegocioTestemunhaAdicionadas
	 * @param unidadeNegocioTestemunha
	 * @return
	 */
	private Collection<UnidadeNegocioTestemunha> adicionarUnidadeNegocioTestemunha(
			Collection<UnidadeNegocioTestemunha> colecaoUnidadeNegocioTestemunha,
			Collection<UnidadeNegocioTestemunha> colecaoUnidadeNegocioTestemunhaAdicionadas,
			UnidadeNegocioTestemunha unidadeNegocioTestemunhaAdicionar) {
		if (colecaoUnidadeNegocioTestemunhaAdicionadas == null) {
			colecaoUnidadeNegocioTestemunhaAdicionadas = new ArrayList<UnidadeNegocioTestemunha>();
		} else {
			// [FS0001] - Validar data relação fim
			for (UnidadeNegocioTestemunha unidadeNegocioTestemunha : colecaoUnidadeNegocioTestemunha) {
				if (unidadeNegocioTestemunha.getDataFimRelacao() == null
						&& unidadeNegocioTestemunha.getUsuario().getId()
								.equals(
										unidadeNegocioTestemunhaAdicionar
												.getUsuario().getId())) {
					throw new ActionServletException(
							"atencao.testemunha.ja.existente",
							unidadeNegocioTestemunhaAdicionar
									.getUnidadeNegocio().getNome(),
							unidadeNegocioTestemunhaAdicionar.getUsuario()
									.getNomeUsuario());
				}
			}
		}
		
		colecaoUnidadeNegocioTestemunhaAdicionadas.add(unidadeNegocioTestemunhaAdicionar);
		colecaoUnidadeNegocioTestemunha.add(unidadeNegocioTestemunhaAdicionar);
		
		return colecaoUnidadeNegocioTestemunhaAdicionadas;
	}

	/**
	 * Executa a consulta feita pelo usuário
	 *
	 * @author Rafa
	 * @date 16/05/2008
	 *
	 * @param idUnidadeNegocio
	 * @param idTestemunha
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Collection<UnidadeNegocioTestemunha> consultarUnidadeNegocioTestemunha(String idUnidadeNegocio) {
		Collection<UnidadeNegocioTestemunha> colecaoUnidadeNegocioTestemunha;
		FiltroUnidadeNegocioTestemunha filtroUnidadeNegocioTestemunha = new FiltroUnidadeNegocioTestemunha();
		filtroUnidadeNegocioTestemunha.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeNegocioTestemunha.UNIDADE_NEGOCIO);
		filtroUnidadeNegocioTestemunha.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeNegocioTestemunha.USUARIO);
		
		filtroUnidadeNegocioTestemunha.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocioTestemunha.UNIDADE_NEGOCIO_ID, idUnidadeNegocio));
		
		colecaoUnidadeNegocioTestemunha = Fachada.getInstancia().pesquisar(filtroUnidadeNegocioTestemunha, UnidadeNegocioTestemunha.class.getName());
		
		return colecaoUnidadeNegocioTestemunha;
	}
	
	/**
	 * Remove a Unidade de Negócio Testemunha  
	 *
	 * @author Rafael Corrêa
	 * @date 16/05/2008
	 *
	 * @param colecaoUnidadeNegocioTestemunha
	 * @param posicao
	 */
	private void removerUnidadeNegocioTestemunha(Collection<UnidadeNegocioTestemunha> colecaoUnidadeNegocioTestemunha, int posicaoRemocao, Collection<UnidadeNegocioTestemunha> colecaoUnidadeNegocioTestemunhaRemovidas, Collection<UnidadeNegocioTestemunha> colecaoUnidadeNegocioTestemunhaAdicionadas) {
		int i = 0;
		
		for (UnidadeNegocioTestemunha unidadeNegocioTestemunha : colecaoUnidadeNegocioTestemunha) {
			i++;
			
			// Verifica se é este o objeto a ser removido
			if (i == posicaoRemocao) {
				
				if (unidadeNegocioTestemunha.getId() == null) {
					colecaoUnidadeNegocioTestemunha.remove(unidadeNegocioTestemunha);
					colecaoUnidadeNegocioTestemunhaAdicionadas.remove(unidadeNegocioTestemunha);
				} else {
					unidadeNegocioTestemunha.setDataFimRelacao(new Date());
					
					if (colecaoUnidadeNegocioTestemunhaRemovidas == null) {
						colecaoUnidadeNegocioTestemunhaRemovidas = new ArrayList<UnidadeNegocioTestemunha>();
					}
					
					colecaoUnidadeNegocioTestemunhaRemovidas.add(unidadeNegocioTestemunha);
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
	 * @param idUnidadeNegocio
	 * @param idUsuario
	 */
	private UnidadeNegocioTestemunha criarUnidadeNegocioTestemunha(String idUnidadeNegocio, String loginUsuario) {
		
		Fachada fachada = Fachada.getInstancia();
		
		UnidadeNegocioTestemunha retorno = new UnidadeNegocioTestemunha();
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, idUnidadeNegocio));
		
		Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
		
		UnidadeNegocio unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
		
		retorno.setUnidadeNegocio(unidadeNegocio);
		
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
