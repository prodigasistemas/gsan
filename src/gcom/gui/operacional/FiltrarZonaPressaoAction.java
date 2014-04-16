package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.operacional.FiltroZonaPressao;
import gcom.operacional.ZonaPressao;
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
 * [UC0797]Filtrar Zona Pressao
 * 
 * @author Vinícius Medeiros
 * @date 20/05/2008
 */

public class FiltrarZonaPressaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterZonaPressao");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarZonaPressaoActionForm filtrarZonaPressaoActionForm = (FiltrarZonaPressaoActionForm) actionForm;

		FiltroZonaPressao filtroZonaPressao = new FiltroZonaPressao();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarZonaPressaoActionForm.getId();
		String descricao = filtrarZonaPressaoActionForm.getDescricao();
		String descricaoAbreviada = filtrarZonaPressaoActionForm.getDescricaoAbreviada();
		String distritoOperacionalID = filtrarZonaPressaoActionForm.getDistritoOperacionalID();
		String indicadorUso = filtrarZonaPressaoActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarZonaPressaoActionForm.getTipoPesquisa();

		// ID
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroZonaPressao.adicionarParametro(new ParametroSimples(
						FiltroZonaPressao.ID, id));
			}
		}
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroZonaPressao
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroZonaPressao.DESCRICAO, descricao));
			} else {

				filtroZonaPressao.adicionarParametro(new ComparacaoTexto(
						FiltroZonaPressao.DESCRICAO, descricao));
			}
		}
		
		// Descricao Abreviada
		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroZonaPressao
					.adicionarParametro(new ComparacaoTextoCompleto(
							FiltroZonaPressao.DESCRICAO_ABREVIADA,
							descricaoAbreviada));
		} 

		// Distrito Operacional
		if (distritoOperacionalID != null && !distritoOperacionalID.equalsIgnoreCase("")) {
			
			pesquisarDistritoOperacional(filtrarZonaPressaoActionForm,fachada,distritoOperacionalID);
			
			peloMenosUmParametroInformado = true;
			filtroZonaPressao.adicionarParametro(new ParametroSimples(
					FiltroZonaPressao.DISTRITO_OPERACIONAL_ID, new Integer(distritoOperacionalID)));
		}

		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroZonaPressao.adicionarParametro(new ParametroSimples(
					FiltroZonaPressao.INDICADOR_USO, indicadorUso));
		}
		
		Collection colecaoZonaPressao = fachada
				.pesquisar(filtroZonaPressao, ZonaPressao.class
						.getName());

		// Verificar a existencia de uma zona de pressao
		if (colecaoZonaPressao.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Zona de Pressão");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoZonaPressao == null
				|| colecaoZonaPressao.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Zona de Pressão");
		} else {
			httpServletRequest.setAttribute("colecaoZonaPressao",
					colecaoZonaPressao);
			ZonaPressao zonaPressao = new ZonaPressao();
			zonaPressao = (ZonaPressao) Util
					.retonarObjetoDeColecao(colecaoZonaPressao);
			String idRegistroAtualizacao = zonaPressao.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroZonaPressao", filtroZonaPressao);

		httpServletRequest.setAttribute("filtroZonaPressao",
				filtroZonaPressao);

		return retorno;

	}
	
	private void pesquisarDistritoOperacional(
	    		FiltrarZonaPressaoActionForm filtrarZonaPressaoActionForm,
	            Fachada fachada, String distritoOperacionalID) {
		 
		Collection colecaoPesquisa;
		 
     FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

     filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
    		 FiltroDistritoOperacional.ID, distritoOperacionalID));

     //Retorna localidade
     colecaoPesquisa = fachada.pesquisar(filtroDistritoOperacional,
             DistritoOperacional.class.getName());

     if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
         //DistritoOperacional nao encontrada
         //Limpa o campo distritoOperacionalID do formulário
    	 filtrarZonaPressaoActionForm.setDistritoOperacionalID("");
    	 filtrarZonaPressaoActionForm.setDistritoOperacionalDescricao("Distrito Operacional inexistente.");
     	
     	throw new ActionServletException("atencao.pesquisa_inexistente",
					null,"Distrito Operacional");	

     }else {
         DistritoOperacional objetoDistritoOperacional = (DistritoOperacional) Util
         .retonarObjetoDeColecao(colecaoPesquisa);
         filtrarZonaPressaoActionForm.setDistritoOperacionalID(String
		            .valueOf(objetoDistritoOperacional.getId()));
         filtrarZonaPressaoActionForm
		            .setDistritoOperacionalDescricao(objetoDistritoOperacional.getDescricao());
		
		}
	
	}

}
