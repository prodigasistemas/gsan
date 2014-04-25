package gcom.gui.micromedicao;



import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
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
 * [UC0835]FILTRAR Local de Armazenagem do hidrômetro
 * 
 * @author Arthur Carvalho
 * @date 07/08/08
 */

public class FiltrarLocalArmazenagemHidrometroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterLocalArmazenagemHidrometro");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarLocalArmazenagemHidrometroActionForm filtrarLocalArmazenagemHidrometroActionForm = (FiltrarLocalArmazenagemHidrometroActionForm) actionForm;

		FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem= new FiltroHidrometroLocalArmazenagem();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarLocalArmazenagemHidrometroActionForm.getId();
		String descricao = filtrarLocalArmazenagemHidrometroActionForm.getDescricao();
		String indicadorUso = filtrarLocalArmazenagemHidrometroActionForm.getIndicadorUso();
        String indicadorOficina = filtrarLocalArmazenagemHidrometroActionForm.getIndicadorOficina(); 
		String descricaoAbreviada	=  filtrarLocalArmazenagemHidrometroActionForm.getDescricaoAbreviada();
		String tipoPesquisa = filtrarLocalArmazenagemHidrometroActionForm.getTipoPesquisa();
		
//		Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {			
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {			
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		// ID
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				
				filtroHidrometroLocalArmazenagem.adicionarParametro(new ParametroSimples(
						FiltroHidrometroLocalArmazenagem.ID, id));
			}
		}
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroHidrometroLocalArmazenagem
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroHidrometroLocalArmazenagem.DESCRICAO, descricao));
			} else {

				filtroHidrometroLocalArmazenagem.adicionarParametro(new ComparacaoTexto(
						FiltroHidrometroLocalArmazenagem.DESCRICAO, descricao));
			}
		}
		//Descricao Abreviada
		
		if (descricaoAbreviada != null && !descricaoAbreviada.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroHidrometroLocalArmazenagem
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroHidrometroLocalArmazenagem.DESCRICAO_ABREVIADA, descricaoAbreviada));
			} else {

				filtroHidrometroLocalArmazenagem.adicionarParametro(new ComparacaoTexto(
						FiltroHidrometroLocalArmazenagem.DESCRICAO_ABREVIADA, descricaoAbreviada));
			}
		}
		
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroHidrometroLocalArmazenagem.adicionarParametro(new ParametroSimples(
					FiltroHidrometroLocalArmazenagem.INDICADOR_USO, indicadorUso));
		}
		
		// Indicador de Oficina
		if (indicadorOficina != null && !indicadorOficina.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroHidrometroLocalArmazenagem.adicionarParametro(new ParametroSimples(
					FiltroHidrometroLocalArmazenagem.INDICADOR_OFICINA, indicadorOficina));
		}
		
		Collection <HidrometroLocalArmazenagem> colecaoHidrometroLocalArmazenagem = fachada
				.pesquisar(filtroHidrometroLocalArmazenagem, HidrometroLocalArmazenagem.class
						.getName());

		// Verificar a existencia de um Grupo de cadastro
		if (colecaoHidrometroLocalArmazenagem.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Local de Armazenagem do Hidrômetro");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoHidrometroLocalArmazenagem == null
				|| colecaoHidrometroLocalArmazenagem.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "HidrometroLocalArmazenagem");
		} else {
			httpServletRequest.setAttribute("colecaoHidrometroLocalArmazenagem",
					colecaoHidrometroLocalArmazenagem);
			HidrometroLocalArmazenagem hidrometroLocalArmazenagem= new HidrometroLocalArmazenagem();
			hidrometroLocalArmazenagem = (HidrometroLocalArmazenagem) Util
					.retonarObjetoDeColecao(colecaoHidrometroLocalArmazenagem);
			String idRegistroAtualizar = hidrometroLocalArmazenagem.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroHidrometroLocalArmazenagem", filtroHidrometroLocalArmazenagem);

		httpServletRequest.setAttribute("filtroHidrometroLocalArmazenagem",
				filtroHidrometroLocalArmazenagem);

		
		return retorno;
	}
}
