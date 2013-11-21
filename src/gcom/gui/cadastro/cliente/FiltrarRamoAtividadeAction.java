package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.FiltroRamoAtividade;
import gcom.cadastro.cliente.RamoAtividade;
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
 * FILTRAR Sistema Abastecimento
 * 
 * @author Fernando Fontelles Filho
 * @date 02/12/2009
 */

public class FiltrarRamoAtividadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterRamoAtividade");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarRamoAtividadeActionForm form = (FiltrarRamoAtividadeActionForm) actionForm;

		// Recupera todos os campos da página para ser colocada no filtro
		// posteriormente

		String codigo = form.getCodigo();
		String descricao = form.getDescricao();
		String indicadorUso = form.getIndicadorUso();
		String tipoPesquisa = form.getTipoPesquisa();

		// Indicador Atualizar
		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {

			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {
			
				sessao.removeAttribute("indicadorAtualizar");
			
		}

		boolean peloMenosUmParametroInformado = false;
		FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade();
		
		// Código Ramo de Atividade
		if (codigo != null && !codigo.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroRamoAtividade.adicionarParametro(new ParametroSimples(
					FiltroRamoAtividade.CODIGO, codigo));
		}

		//Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroRamoAtividade
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroRamoAtividade.DESCRICAO, descricao));
			} else {

				filtroRamoAtividade.adicionarParametro(new ComparacaoTexto(
						FiltroRamoAtividade.DESCRICAO, descricao));
			}
		}

		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            if (indicadorUso.equalsIgnoreCase(String
                    .valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
            	filtroRamoAtividade.adicionarParametro(new ParametroSimples(
                		FiltroRamoAtividade.INDICADOR_USO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));
            } else {
            	filtroRamoAtividade.adicionarParametro(new ParametroSimples(
            			FiltroRamoAtividade.INDICADOR_USO,
                        ConstantesSistema.INDICADOR_USO_DESATIVO));
            }
        }

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		filtroRamoAtividade.setCampoOrderBy(FiltroRamoAtividade.CODIGO);

		Collection<RamoAtividade> colecaoRamoAtividade = fachada.pesquisar(
				filtroRamoAtividade, RamoAtividade.class.getName());

		if (colecaoRamoAtividade == null || colecaoRamoAtividade.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,
					"Ramo de Atividade");
		} else {
			httpServletRequest.setAttribute("colecaoRamoAtividade",
					colecaoRamoAtividade);
			RamoAtividade ramoAtividade = new RamoAtividade();
			ramoAtividade = (RamoAtividade) Util
					.retonarObjetoDeColecao(colecaoRamoAtividade);
			String idRegistroAtualizacao = ""+ramoAtividade.getCodigo();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroRamoAtividade", filtroRamoAtividade);

		httpServletRequest.setAttribute("filtroRamoAtividade", filtroRamoAtividade);

		return retorno;

	}
}
