package gcom.gui.atendimentopublico;


import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
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
 * [UC0861]FILTRAR Perfil da Ligacao de Esgoto
 * 
 * @author Arthur Carvalho
 * @date 17/10/08
 */

public class FiltrarPerfilLigacaoEsgotoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterPerfilLigacaoEsgoto");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarPerfilLigacaoEsgotoActionForm filtrarPerfilLigacaoEsgotoActionForm = (FiltrarPerfilLigacaoEsgotoActionForm) actionForm;

		FiltroLigacaoEsgotoPerfil filtroLigacaoEsgotoPerfil = new FiltroLigacaoEsgotoPerfil();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarPerfilLigacaoEsgotoActionForm.getId();
		String descricao = filtrarPerfilLigacaoEsgotoActionForm.getDescricao();
		String indicadorUso = filtrarPerfilLigacaoEsgotoActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarPerfilLigacaoEsgotoActionForm.getTipoPesquisa();
		
		String percentualEsgotoConsumidaColetada = filtrarPerfilLigacaoEsgotoActionForm.getPercentualEsgotoConsumidaColetada().replace(",", ".");
		

		
		// ID
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroLigacaoEsgotoPerfil.adicionarParametro(new ParametroSimples(
						FiltroLigacaoEsgotoPerfil.ID, id));
			}
		}
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroLigacaoEsgotoPerfil
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroLigacaoEsgotoPerfil.DESCRICAO, descricao));
			} else {

				filtroLigacaoEsgotoPerfil.adicionarParametro(new ComparacaoTexto(
						FiltroLigacaoEsgotoPerfil.DESCRICAO, descricao));
			}
		}
		
		// Percentual de Esgoto
		if (percentualEsgotoConsumidaColetada != null
				&& !percentualEsgotoConsumidaColetada.trim().equals("")) {

			peloMenosUmParametroInformado = true;
			filtroLigacaoEsgotoPerfil
					.adicionarParametro(new ParametroSimples(
							FiltroLigacaoEsgotoPerfil.PERCENTUAL, percentualEsgotoConsumidaColetada));
		} 
	
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroLigacaoEsgotoPerfil.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoPerfil.INDICADOR_USO, indicadorUso));
		}
		
		Collection <LigacaoEsgotoPerfil> colecaoLigacaoEsgotoPerfil = fachada
				.pesquisar(filtroLigacaoEsgotoPerfil, LigacaoEsgotoPerfil.class
						.getName());

		// Verificar a existencia de um Grupo de cadastro
		if (colecaoLigacaoEsgotoPerfil.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Perfil da Ligação de Esgoto");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoLigacaoEsgotoPerfil == null
				|| colecaoLigacaoEsgotoPerfil.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Perfil da Ligação de Esgoto");
		} else {
			httpServletRequest.setAttribute("colecaoLigacaoEsgotoPerfil",
					colecaoLigacaoEsgotoPerfil);
			LigacaoEsgotoPerfil ligacaoEsgotoPerfil = new LigacaoEsgotoPerfil();
			ligacaoEsgotoPerfil = (LigacaoEsgotoPerfil) Util
					.retonarObjetoDeColecao(colecaoLigacaoEsgotoPerfil);
			String idRegistroAtualizar = ligacaoEsgotoPerfil.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroLigacaoEsgotoPerfil", filtroLigacaoEsgotoPerfil);

		httpServletRequest.setAttribute("filtroLigacaoEsgotoPerfil",
				filtroLigacaoEsgotoPerfil);

		
		return retorno;
	}
}
