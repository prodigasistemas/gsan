package gcom.gui.operacional;


import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.FiltroDivisaoEsgoto;
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
 * @date 10/06/08
 */

public class FiltrarDivisaoEsgotoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterDivisaoEsgoto");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarDivisaoEsgotoActionForm filtrarDivisaoEsgotoActionForm = (FiltrarDivisaoEsgotoActionForm) actionForm;

		FiltroDivisaoEsgoto filtroDivisaoEsgoto = new FiltroDivisaoEsgoto();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarDivisaoEsgotoActionForm.getId();
		String descricao = filtrarDivisaoEsgotoActionForm.getDescricao();
		String indicadorUso= filtrarDivisaoEsgotoActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarDivisaoEsgotoActionForm.getTipoPesquisa();
		String idUnidadeOrganizacional = filtrarDivisaoEsgotoActionForm.getUnidadeOrganizacionalId();
		
		// ID
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroDivisaoEsgoto.adicionarParametro(new ParametroSimples(
						FiltroDivisaoEsgoto.ID, id));
			}
		}
		
		//	Unidade organizacional
		if (idUnidadeOrganizacional != null && !idUnidadeOrganizacional.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroDivisaoEsgoto.adicionarParametro(new ParametroSimples(FiltroDivisaoEsgoto.UNIDADE_ORGANIZACIONAL_ID, idUnidadeOrganizacional));
		}
		
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroDivisaoEsgoto
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroDivisaoEsgoto.DESCRICAO, descricao));
			} else {

				filtroDivisaoEsgoto.adicionarParametro(new ComparacaoTexto(
						FiltroDivisaoEsgoto.DESCRICAO, descricao));
			}
		}

		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroDivisaoEsgoto.adicionarParametro(new ParametroSimples(
					FiltroDivisaoEsgoto.INDICADOR_USO, indicadorUso));
		}
		
		Collection <DivisaoEsgoto> colecaoDivisaoEsgoto = fachada
				.pesquisar(filtroDivisaoEsgoto, DivisaoEsgoto.class
						.getName());

		// Verificar a existencia de um Grupo de cadastro
		if (colecaoDivisaoEsgoto.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Divisao de Esgoto");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoDivisaoEsgoto == null
				|| colecaoDivisaoEsgoto.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "DivisaoEsgoto");
		} else {
			httpServletRequest.setAttribute("colecaoDivisaoEsgoto",
					colecaoDivisaoEsgoto);
			DivisaoEsgoto divisaoEsgoto = new DivisaoEsgoto();
			divisaoEsgoto = (DivisaoEsgoto) Util
					.retonarObjetoDeColecao(colecaoDivisaoEsgoto);
			String idRegistroAtualizacao = divisaoEsgoto.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroDivisaoEsgoto", filtroDivisaoEsgoto);

		httpServletRequest.setAttribute("filtroDivisaoEsgoto",
				filtroDivisaoEsgoto);

		
		return retorno;
	}
}
