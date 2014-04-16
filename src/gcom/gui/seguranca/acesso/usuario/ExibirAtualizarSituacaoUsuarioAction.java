package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroSituacaoUsuario;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 15/05/2006
 */
public class ExibirAtualizarSituacaoUsuarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarSituacaoUsuario");

		AtualizarSituacaoUsuarioActionForm atualizarSituacaoUsuarioActionForm = (AtualizarSituacaoUsuarioActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);


				String usuarioSituacaoID = httpServletRequest
						.getParameter("idRegistroAtualizacao");

				FiltroSituacaoUsuario filtroSituacaoUsuario = new FiltroSituacaoUsuario();
				
				filtroSituacaoUsuario.adicionarParametro(new ParametroSimples(
						FiltroSituacaoUsuario.ID, usuarioSituacaoID));

				Collection<UsuarioSituacao> colecaoUsuarioSituacao = fachada
						.pesquisar(filtroSituacaoUsuario, UsuarioSituacao.class
								.getName());

				if (colecaoUsuarioSituacao == null
						|| colecaoUsuarioSituacao.isEmpty()) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}


				UsuarioSituacao usuarioSituacao = colecaoUsuarioSituacao.iterator().next();

				atualizarSituacaoUsuarioActionForm.setDescricaoUsuarioSituacao(usuarioSituacao
						.getDescricaoUsuarioSituacao());
				atualizarSituacaoUsuarioActionForm
						.setDescricaoAbreviada(usuarioSituacao
								.getDescricaoAbreviada());
				atualizarSituacaoUsuarioActionForm.setIndicadorUsoSistema(""
						+ usuarioSituacao.getIndicadorUsoSistema());
							
				atualizarSituacaoUsuarioActionForm.setIndicadorUso(""
						+ usuarioSituacao.getIndicadorUso());
				
				atualizarSituacaoUsuarioActionForm.setId(""
						+ usuarioSituacao.getId());
				
				sessao.setAttribute("AtualizarSituacaoUsuarioActionForm", atualizarSituacaoUsuarioActionForm);
				
				sessao.setAttribute("usuarioSituacaoAtualizar", usuarioSituacao);

//				// Seta o valor 1 no Indicador de Uso (Campo Obrigatório) 
//				if (atualizarSituacaoUsuarioActionForm.getIndicadorUso() == null
//						|| !atualizarSituacaoUsuarioActionForm.equals("")) {
//					atualizarSituacaoUsuarioActionForm.setIndicadorUso("1");
//				}


		return retorno;

	}
}
