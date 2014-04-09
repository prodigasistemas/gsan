package gcom.gui.cobranca;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
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
 * @author Rafael Corrêa
 * @since 22/08/2008
 */
public class ExibirConsultarTransferenciasDebitoAction extends GcomAction {

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
				.findForward("exibirConsultarTransferenciasDebito");
		
		ConsultarTransferenciasDebitoActionForm consultarTransferenciasDebitoActionForm = (ConsultarTransferenciasDebitoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		sessao.removeAttribute("colecaoContasTransferidas");
		sessao.removeAttribute("colecaoDebitosACobrarTransferidos");
		sessao.removeAttribute("colecaoGuiasPagamentoTransferidas");
		sessao.removeAttribute("colecaoCreditosARealizarTransferidos");
		sessao.removeAttribute("consultarTransferenciasDebitoHelper");
		
		String idImovelOrigem = consultarTransferenciasDebitoActionForm.getIdImovelOrigem();
		String idImovelDestino = consultarTransferenciasDebitoActionForm.getIdImovelDestino();
		String idUsuario = consultarTransferenciasDebitoActionForm.getIdUsuario();
		String loginUsuario = consultarTransferenciasDebitoActionForm.getLoginUsuario();
		
		// Pesquisa Imóvel Origem
		if (idImovelOrigem != null && !idImovelOrigem.trim().equals("")) {
			
			String inscricao = fachada.pesquisarInscricaoImovel(new Integer(idImovelOrigem));
			
			if (inscricao != null && !inscricao.trim().equals("")) {
				consultarTransferenciasDebitoActionForm.setInscricaoImovelOrigem(inscricao);
				httpServletRequest.setAttribute("nomeCampo", "idImovelDestino");
			} else {
				consultarTransferenciasDebitoActionForm.setIdImovelOrigem("");
				consultarTransferenciasDebitoActionForm.setInscricaoImovelOrigem("IMÓVEL INEXISTENTE");
				
				httpServletRequest.setAttribute("imovelOrigemInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idImovelOrigem");
			}
			
		} else {
			consultarTransferenciasDebitoActionForm.setInscricaoImovelOrigem("");
		}
		
		// Pesquisa Imóvel Destino
		if (idImovelDestino != null && !idImovelDestino.trim().equals("")) {
			
			String inscricao = fachada.pesquisarInscricaoImovel(new Integer(idImovelDestino));
			
			if (inscricao != null && !inscricao.trim().equals("")) {
				consultarTransferenciasDebitoActionForm.setInscricaoImovelDestino(inscricao);
				httpServletRequest.setAttribute("nomeCampo", "dataInicio");
			} else {
				consultarTransferenciasDebitoActionForm.setIdImovelDestino("");
				consultarTransferenciasDebitoActionForm.setInscricaoImovelDestino("IMÓVEL INEXISTENTE");
				
				httpServletRequest.setAttribute("imovelDestinoInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idImovelDestino");
			}
			
		} else {
			consultarTransferenciasDebitoActionForm.setInscricaoImovelDestino("");
		}
		
		// Pesquisa o usuário
		if ((loginUsuario != null && !loginUsuario.trim().equals("")) || (idUsuario != null && !idUsuario.trim().equals(""))) {
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			
			// Verifica se a pesquisa foi pelo enter
			if ((loginUsuario != null && !loginUsuario.trim().equals(""))) {
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, loginUsuario));
			} 
			// Verifica se a pesquisa foi feita pela lupa
			else {
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, idUsuario));
			}
			
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				consultarTransferenciasDebitoActionForm.setIdUsuario(usuario.getId().toString());
				consultarTransferenciasDebitoActionForm.setLoginUsuario(usuario.getLogin());
				consultarTransferenciasDebitoActionForm.setNomeUsuario(usuario.getNomeUsuario());
			} else {
				consultarTransferenciasDebitoActionForm.setIdUsuario("");
				consultarTransferenciasDebitoActionForm.setLoginUsuario("");
				consultarTransferenciasDebitoActionForm.setNomeUsuario("USUÁRIO INEXISTENTE");
				
				httpServletRequest.setAttribute("usuarioInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "loginUsuario");
			}
			
		} else {
			consultarTransferenciasDebitoActionForm.setNomeUsuario("");
		}

		return retorno;

	}

}
