package gcom.gui.cadastro;


import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * [UC0774]FILTRAR Situacao de Pagamento
 * 
 * @author Arthur Carvalho
 * @date 08/05/08
 */

public class FiltrarEmpresaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterEmpresa");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarEmpresaActionForm filtrarEmpresaActionForm = (FiltrarEmpresaActionForm) actionForm;

		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarEmpresaActionForm.getId();
		String descricao = filtrarEmpresaActionForm.getDescricao();
		String descricaoAbreviada = filtrarEmpresaActionForm.getDescricaoAbreviada();
		String email = filtrarEmpresaActionForm.getEmail();
		String indicadorEmpresaPrincipal = filtrarEmpresaActionForm.getIndicadorEmpresaPrincipal();	
		String indicadorUso = filtrarEmpresaActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarEmpresaActionForm.getTipoPesquisa();

		
		// ID
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroEmpresa.adicionarParametro(new ParametroSimples(
						FiltroEmpresa.ID, id));
			}
		}
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroEmpresa
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroEmpresa.DESCRICAO, descricao));
			} else {

				filtroEmpresa.adicionarParametro(new ComparacaoTexto(
						FiltroEmpresa.DESCRICAO, descricao));
			}
		}
		
		// Descricao Abreviada
		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroEmpresa
					.adicionarParametro(new ComparacaoTextoCompleto(
							FiltroEmpresa.DESCRICAO_ABREVIADA, descricaoAbreviada));
		} 
		//	E-mail
		if (email != null
				&& !email.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroEmpresa
					.adicionarParametro(new ComparacaoTextoCompleto(
							FiltroEmpresa.EMAIL,
							email));
		}
		
		//	Indicador Empresa Principal
		if (indicadorEmpresaPrincipal != null
					&& !indicadorEmpresaPrincipal.trim().equals("")) {

				peloMenosUmParametroInformado = true;
				filtroEmpresa
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroEmpresa.INDICADOR_EMPRESA_PRINCIPAL,
								indicadorEmpresaPrincipal));

		}	
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.INDICADORUSO, indicadorUso));
		}
		
		Collection <Empresa> colecaoEmpresa = fachada
				.pesquisar(filtroEmpresa, Empresa.class
						.getName());

		// Verificar a existencia de um Grupo de cadastro
		if (colecaoEmpresa.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Empresa");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoEmpresa == null
				|| colecaoEmpresa.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Empresa");
		} else {
			httpServletRequest.setAttribute("colecaoEmpresa",
					colecaoEmpresa);
			Empresa Empresa = new Empresa();
			Empresa = (Empresa) Util
					.retonarObjetoDeColecao(colecaoEmpresa);
			String idRegistroAtualizar = Empresa.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroEmpresa", filtroEmpresa);

		httpServletRequest.setAttribute("filtroEmpresa",
				filtroEmpresa);

		
		return retorno;
	}
}
