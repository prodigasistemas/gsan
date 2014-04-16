package gcom.gui.operacional;



import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.Bacia;
import gcom.operacional.FiltroBacia;
import gcom.operacional.FiltroSistemaEsgoto;
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
 * [UC0133]FILTRAR BACIA
 * 
 * @author Arthur Carvalho
 * @date 21/05/08
 */

public class FiltrarBaciaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterBacia");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarBaciaActionForm filtrarBaciaActionForm = (FiltrarBaciaActionForm) actionForm;

		FiltroBacia filtroBacia = new FiltroBacia();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarBaciaActionForm.getId();
		String descricao = filtrarBaciaActionForm.getDescricao();
		String indicadorUso = filtrarBaciaActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarBaciaActionForm.getTipoPesquisa();
		String sistemaEsgoto	=  filtrarBaciaActionForm.getSistemaEsgoto();
		
//		Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {			
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {			
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto(FiltroSistemaEsgoto.DESCRICAO);
		
		filtroSistemaEsgoto.adicionarCaminhoParaCarregamentoEntidade("sistemaEsgoto");
		
		
		// ID
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroBacia.adicionarParametro(new ParametroSimples(
						FiltroBacia.ID, id));
			}
		}
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroBacia
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroBacia.DESCRICAO, descricao));
			} else {

				filtroBacia.adicionarParametro(new ComparacaoTexto(
						FiltroBacia.DESCRICAO, descricao));
			}
		}
		if (sistemaEsgoto!= null && 
				!sistemaEsgoto.trim().equals("-1")) {
				
				peloMenosUmParametroInformado = true;
				
				filtroBacia.adicionarParametro(
					new ParametroSimples(FiltroBacia.SISTEMA_ESGOTO_ID, 
						sistemaEsgoto));
			}
		
		
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroBacia.adicionarParametro(new ParametroSimples(
					FiltroBacia.INDICADORUSO, indicadorUso));
		}
		
		Collection <Bacia> colecaoBacia = fachada
				.pesquisar(filtroBacia, Bacia.class
						.getName());

		// Verificar a existencia de um Grupo de cadastro
		if (colecaoBacia.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Bacia");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoBacia == null
				|| colecaoBacia.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Bacia");
		} else {
			httpServletRequest.setAttribute("colecaoBacia",
					colecaoBacia);
			Bacia Bacia = new Bacia();
			Bacia = (Bacia) Util
					.retonarObjetoDeColecao(colecaoBacia);
			String idRegistroAtualizar = Bacia.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroBacia", filtroBacia);

		httpServletRequest.setAttribute("filtroBacia",
				filtroBacia);

		
		return retorno;
	}
}
