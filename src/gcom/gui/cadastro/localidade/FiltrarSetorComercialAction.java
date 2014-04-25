package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroSetorComercial;
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

public class FiltrarSetorComercialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Obtém a instância da fachada
		//Fachada fachada = Fachada.getInstancia();

		ActionForward retorno = actionMapping
				.findForward("retornarFiltroSetorComercial");

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarFiltrarSetorComercialActionForm pesquisarFiltrarSetorComercialActionForm = (PesquisarFiltrarSetorComercialActionForm) actionForm;

		//String atualizar = pesquisarFiltrarSetorComercialActionForm
		//		.getAtualizar();

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		// Objetos que serã retornados pelo hibernate
		filtroSetorComercial.setCampoOrderBy(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL
				,FiltroSetorComercial.DESCRICAO_LOCALIDADE);
		filtroSetorComercial
				.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroSetorComercial
				.adicionarCaminhoParaCarregamentoEntidade("municipio");

		String localidadeID = pesquisarFiltrarSetorComercialActionForm
				.getLocalidadeID();
		String setorComercialCD = pesquisarFiltrarSetorComercialActionForm
				.getSetorComercialCD();
		String setorComercialNome = pesquisarFiltrarSetorComercialActionForm
				.getSetorComercialNome();
		String municipioID = pesquisarFiltrarSetorComercialActionForm
				.getMunicipioID();
		String indicadorUso = pesquisarFiltrarSetorComercialActionForm
				.getIndicadorUso();
		String tipoPesquisa = pesquisarFiltrarSetorComercialActionForm
				.getTipoPesquisa();
		String indicadorSetorAlternativo = pesquisarFiltrarSetorComercialActionForm.getIndicadorSetorAlternativo();

		// 1 check --- null uncheck
		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");

		boolean peloMenosUmParametroInformado = false;

		if (localidadeID != null && !localidadeID.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, new Integer(localidadeID)));
		}

		if (setorComercialCD != null && !setorComercialCD.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
					setorComercialCD));
		}

		if (setorComercialNome != null
				&& !setorComercialNome.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroSetorComercial
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroSetorComercial.DESCRICAO,
								setorComercialNome));
			} else {
				filtroSetorComercial.adicionarParametro(new ComparacaoTexto(
						FiltroSetorComercial.DESCRICAO, setorComercialNome));
			}
		}

		if (municipioID != null && !municipioID.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_MUNICIPIO, municipioID));
		}

		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("")
				&& !indicadorUso.equalsIgnoreCase("3")) {
			peloMenosUmParametroInformado = true;
			if (indicadorUso.equalsIgnoreCase(String
					.valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			} else {
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_DESATIVO));
			}
		}
		
		if ( indicadorSetorAlternativo != null && !indicadorSetorAlternativo.equalsIgnoreCase("") &&
				!indicadorSetorAlternativo.equalsIgnoreCase("3") ) {
			peloMenosUmParametroInformado = true;
			if ( indicadorSetorAlternativo.equalsIgnoreCase( String
					.valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
				filtroSetorComercial.adicionarParametro( new ParametroSimples( 
						FiltroSetorComercial.INDICADOR_SETOR_ALTERNATIVO, 
						ConstantesSistema.INDICADOR_USO_ATIVO));
			} else {
				filtroSetorComercial.adicionarParametro( new ParametroSimples(
						FiltroSetorComercial.INDICADOR_SETOR_ALTERNATIVO,
						ConstantesSistema.INDICADOR_USO_DESATIVO ) );
			}
		}
		// Está consulta irá retornar todos os setores comerciais, inclusive os
		// inativos

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		sessao.setAttribute("filtroSetorComercial", filtroSetorComercial);
		sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		sessao.setAttribute("voltar", "manter");
		
		// devolve o mapeamento de retorno
		return retorno;
	}

}
