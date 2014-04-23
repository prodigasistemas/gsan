package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarLocalidadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("retornarFiltroLocalidade");

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarLocalidadeActionForm filtrarLocalidadeActionForm = (FiltrarLocalidadeActionForm) actionForm;

		FiltroLocalidade filtroLocalidade = null;
		
		
		String localidadeID = filtrarLocalidadeActionForm.getLocalidadeID();
		String localidadeNome = filtrarLocalidadeActionForm.getLocalidadeNome();
		String gerenciaRegionalID = filtrarLocalidadeActionForm.getGerenciaID();
		String idUnidadeNegocio = filtrarLocalidadeActionForm.getIdUnidadeNegocio();
		
		String indicadorUso = filtrarLocalidadeActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarLocalidadeActionForm.getTipoPesquisa();
		String ordernarPor = filtrarLocalidadeActionForm.getOrdernarPor();

		// 1 check --- null uncheck
		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");

		boolean peloMenosUmParametroInformado = false;
		
		if(ordernarPor != null && ordernarPor.equals(ConstantesSistema.ORDENAR_POR_CODIGO)){
		  filtroLocalidade = new FiltroLocalidade(
				FiltroLocalidade.ID);
		}else{
			filtroLocalidade = new FiltroLocalidade(
					FiltroLocalidade.DESCRICAO);
		}

		// Objetos que serão retornados pelo hibernate
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		filtroLocalidade
			.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");


		if (localidadeID != null && !localidadeID.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, new Integer(localidadeID)));
		}

		if (localidadeNome != null && !localidadeNome.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroLocalidade
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroLocalidade.DESCRICAO, localidadeNome));
			} else {
				filtroLocalidade.adicionarParametro(new ComparacaoTexto(
						FiltroLocalidade.DESCRICAO, localidadeNome));
			}
		}

		if (gerenciaRegionalID != null
				&& !gerenciaRegionalID.trim().equalsIgnoreCase(
						String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

			peloMenosUmParametroInformado = true;
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID_GERENCIA, gerenciaRegionalID));
		}

		//Unidade de Negocio
		if (idUnidadeNegocio != null
				&& !idUnidadeNegocio.trim().equalsIgnoreCase(
						String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

			peloMenosUmParametroInformado = true;
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID_UNIDADE_NEGOCIO, idUnidadeNegocio));
		}
		
		
		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("")
				&& !indicadorUso.equalsIgnoreCase("3")) {
			peloMenosUmParametroInformado = true;
			if (indicadorUso.equalsIgnoreCase(String
					.valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			} else {
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_DESATIVO));
			}
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		sessao.setAttribute("filtroLocalidade", filtroLocalidade);
		sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);

		// devolve o mapeamento de retorno
		return retorno;
	}

}
