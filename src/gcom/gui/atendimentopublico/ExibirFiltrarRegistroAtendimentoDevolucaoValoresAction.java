package gcom.gui.atendimentopublico;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarRegistroAtendimentoDevolucaoValoresAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarRegistroAtendimentoDevolucaoValores");

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarRegistroAtendimentoDevolucaoValoresActionForm form = (FiltrarRegistroAtendimentoDevolucaoValoresActionForm) actionForm;

		// Limpa o atributo se o usuário voltou o filtrar
		if (sessao.getAttribute("guiaDevolucaoAtualizar") != null) {
			sessao.removeAttribute("guiaDevolucaoAtualizar");
		}

		// Pegando valores para Tipo de Relacao do Cliente
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(
				FiltroImovelPerfil.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
		Collection colecaoPerfilImovel = fachada.pesquisar(
				filtroImovelPerfil, ImovelPerfil.class.getName());

		if (colecaoPerfilImovel != null	&& !colecaoPerfilImovel.isEmpty()) {

			httpServletRequest.setAttribute("colecaoPerfilImovel",colecaoPerfilImovel);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Imóvel Perfil");
		}

//		if (httpServletRequest.getParameter("menu") != null
//				&& httpServletRequest.getParameter("menu").equalsIgnoreCase(
//						"sim")) {
//			form.setAtualizar("1");
//		}
//		
//		 Seta o cursor para o primeiro campo do tipo texto
//		httpServletRequest.setAttribute("nomeCampo", "idImovel");
		
		// Imóvel
		String idImovel = "";
		
		if (httpServletRequest.getParameter("paginacao") != null) {
			idImovel = (String) form.getIdImovelHidden();
		} else {
			idImovel = (String) form.getIdImovel();
		}

		if (idImovel != null && !idImovel.equals("")) {
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));

			Collection imoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());

			if (imoveis != null && !imoveis.isEmpty()) {
				Imovel imovel = (Imovel) ((List) imoveis).get(0);
				httpServletRequest.setAttribute("imovel", imovel);
				form.setDescricaoImovel(imovel.getInscricaoFormatada());
			} else {
				httpServletRequest.setAttribute("matriculaInexistente", "true");
				form.setIdImovel("");
				form.setDescricaoImovel("MATRÍCULA INEXISTENTE");
			}
		} else {
			form.setIdImovel("");
			form.setDescricaoImovel("");
		}
		
		return retorno;
	}

}
