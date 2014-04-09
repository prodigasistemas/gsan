package gcom.gui.seguranca;


import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0838]FILTRAR FONTE ABASTECIMENTO
 * 
 * @author Arthur Carvalho
 * @date 14/08/08
 */

public class FiltrarUsuarioTipoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterUsuarioTipo");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarUsuarioTipoActionForm filtrarUsuarioTipoActionForm = (FiltrarUsuarioTipoActionForm) actionForm;

		FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarUsuarioTipoActionForm.getId();
		String indicadorFuncionario = filtrarUsuarioTipoActionForm.getIndicadorFuncionario();
		String descricao = filtrarUsuarioTipoActionForm.getDescricao();
		String indicadorUso = filtrarUsuarioTipoActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarUsuarioTipoActionForm.getTipoPesquisa();
		
		//Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {			
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {			
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		//CODIGO
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				
				filtroUsuarioTipo.adicionarParametro(new ParametroSimples(
						FiltroUsuarioTipo.ID, id));
			}
		}
		
		//Indicador Funcionario
		if (indicadorFuncionario != null && !indicadorFuncionario.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroUsuarioTipo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioTipo.INDICADOR_FUNCIONARIO, indicadorFuncionario));
		}
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroUsuarioTipo
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroUsuarioTipo.DESCRICAO, descricao));
			} else {

				filtroUsuarioTipo.adicionarParametro(new ComparacaoTexto(
						FiltroUsuarioTipo.DESCRICAO, descricao));
			}
		}
		
		
		
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroUsuarioTipo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioTipo.INDICADOR_USO, indicadorUso));
		}
		
		
		Collection <UsuarioTipo> colecaoUsuarioTipo = fachada
				.pesquisar(filtroUsuarioTipo, UsuarioTipo.class
						.getName());

		// Verificar a existencia de um Grupo de cadastro
		if (colecaoUsuarioTipo.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Tipo de Usuário");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoUsuarioTipo == null
				|| colecaoUsuarioTipo.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Tipo de Usuário");
		} else {
			httpServletRequest.setAttribute("colecaoUsuarioTipo",
					colecaoUsuarioTipo);
			UsuarioTipo usuarioTipo= new UsuarioTipo();
			usuarioTipo = (UsuarioTipo) Util
					.retonarObjetoDeColecao(colecaoUsuarioTipo);
			String idRegistroAtualizar = usuarioTipo.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroUsuarioTipo", filtroUsuarioTipo);

		httpServletRequest.setAttribute("filtroUsuarioTipo",
				filtroUsuarioTipo);
		
		return retorno;
	}
}
