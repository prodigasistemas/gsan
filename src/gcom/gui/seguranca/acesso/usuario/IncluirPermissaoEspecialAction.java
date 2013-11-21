package gcom.gui.seguranca.acesso.usuario;

import java.util.Collection;
import java.util.Date;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecialPK;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1115] Incluir Permissão Especial por Unidade Organizacional
 *
 * @author Mariana Victor
 * @date 29/12/2010
 * 
 */
public class IncluirPermissaoEspecialAction  extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();
		
		IncluirPermissaoEspecialActionForm form = (IncluirPermissaoEspecialActionForm) actionForm;
		
		String[] usuariosSelecionados = form.getIdUsuario();
		
		String[] permissoesSelecionadas = form.getIdPermissao();
		
		if (usuariosSelecionados == null || usuariosSelecionados.length < 1) {
            throw new ActionServletException("Nenhum Usuário selecionado.");
        }
	
		if (permissoesSelecionadas == null || permissoesSelecionadas.length < 1) {
            throw new ActionServletException("Nenhuma Permissão Especial selecionada.");
        }
		
		Date data = new Date();
		
		for (int i = 0; i < usuariosSelecionados.length; i++) {
			for (int j = 0; j < permissoesSelecionadas.length; j++) {
				FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
				filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(
						FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_COMP_ID, permissoesSelecionadas[j]));
				filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(
						FiltroUsuarioPemissaoEspecial.USUARIO_COMP_ID, usuariosSelecionados[i]));

				Collection colecaoUsuarioPemissaoEspecial = fachada.pesquisar(
						filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
				
				if (colecaoUsuarioPemissaoEspecial == null || colecaoUsuarioPemissaoEspecial.isEmpty()) {
					UsuarioPermissaoEspecial usuarioPermissaoEspecial = new UsuarioPermissaoEspecial();
					UsuarioPermissaoEspecialPK usuarioPermissaoEspecialPK = new UsuarioPermissaoEspecialPK();
					
					usuarioPermissaoEspecialPK.setPermissaoEspecialId(new Integer(permissoesSelecionadas[j]));
					usuarioPermissaoEspecialPK.setUsuarioId(new Integer(usuariosSelecionados[i]));
					usuarioPermissaoEspecialPK.setUltimaAlteracao(data);
					
					usuarioPermissaoEspecial.setComp_id(usuarioPermissaoEspecialPK);
					usuarioPermissaoEspecial.setUltimaAlteracao(data);
					
					fachada.inserir(usuarioPermissaoEspecial);
				}
			}
		}
		
		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Permissão(ões) Especial(is) por Unidade Organizacional informada(s) com sucesso.",
				"Informar Outra Permissão Especial por Unidade Organizacional",
				"exibirIncluirPermissaoEspecialAction.do?menu=sim");

		
		
		return retorno;
	}

}
