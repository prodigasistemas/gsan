package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroRetornoControleHidrometro;
import gcom.micromedicao.hidrometro.RetornoControleHidrometro;
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
 * FILTRAR Retorno Controle Hidrometro
 * 
 * @author Wallace Thierre
 * @date 03/08/2010
 */

public class FiltrarRetornoControleHidrometroAction extends GcomAction{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){

		ActionForward retorno = actionMapping
		.findForward("exibirManterRetornoControleHidrometro");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarRetornoControleHidrometroActionForm filtrarRetornoControleHidrometroActionForm = (FiltrarRetornoControleHidrometroActionForm) actionForm;

		FiltroRetornoControleHidrometro filtroRetornoControleHidrometro = new FiltroRetornoControleHidrometro();

		boolean peloMenosUmParametroInformado = false;

//		String id = filtrarRetornoControleHidrometroActionForm.getId();
		String descricao = filtrarRetornoControleHidrometroActionForm.getDescricao();
		String indicadorUso = filtrarRetornoControleHidrometroActionForm.getIndicadorUso();
		String indicadorGeracao = filtrarRetornoControleHidrometroActionForm.getIndicadorGeracao();
		String tipoPesquisa = filtrarRetornoControleHidrometroActionForm.getTipoPesquisa();
		
		//ID
//		if(id != null && id.equals("")){
//			peloMenosUmParametroInformado = true;
//			filtroRetornoControleHidrometro.adicionarParametro(new ParametroSimples
//					(FiltroRetornoControleHidrometro.ID, id));			
//		}
		
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroRetornoControleHidrometro
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroRetornoControleHidrometro.DESCRICAO, descricao));
			} else {

				filtroRetornoControleHidrometro.adicionarParametro(new ComparacaoTexto(
						FiltroRetornoControleHidrometro.DESCRICAO, descricao));
			}
		}
		
		//Indicador de Uso
		if(indicadorUso != null && !indicadorUso.trim().equals("")){
			peloMenosUmParametroInformado = true;
			filtroRetornoControleHidrometro.adicionarParametro
					(new ParametroSimples(FiltroRetornoControleHidrometro.INDICADOR_USO, indicadorUso));
		}

		//Indicador de Geração
		if(indicadorGeracao!= null && !indicadorGeracao.trim().equals("")){
			peloMenosUmParametroInformado = true;
			filtroRetornoControleHidrometro.adicionarParametro
					(new ParametroSimples(FiltroRetornoControleHidrometro.INDICADOR_GERACAO, indicadorGeracao));
		}
		
		Collection colecaoRetornoControleHidrometro = null;
		if(peloMenosUmParametroInformado == true){
			
			colecaoRetornoControleHidrometro = fachada.pesquisar
				(filtroRetornoControleHidrometro, RetornoControleHidrometro.class.getName());
		}
		
		//Filtragem sem parametros
		if(!peloMenosUmParametroInformado){
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
			
		
		//Verificar a existência de um retorno controle hidrometro 
		if(colecaoRetornoControleHidrometro == null 
				|| colecaoRetornoControleHidrometro.isEmpty()){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null,"Retorno de Controle de Hidrômetro");
		}else {
			httpServletRequest.setAttribute("colecaoRetornoControleHidrometro",
					colecaoRetornoControleHidrometro);
			RetornoControleHidrometro retornoControleHidrometro= new RetornoControleHidrometro();
			retornoControleHidrometro = (RetornoControleHidrometro) Util
					.retonarObjetoDeColecao(colecaoRetornoControleHidrometro);
			String idRegistroAtualizacao = retornoControleHidrometro.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}
				
		
		sessao.setAttribute("filtroRetornoControleHidrometro", filtroRetornoControleHidrometro);
	
		httpServletRequest.setAttribute("filtroRetornoControleHidrometro",
				filtroRetornoControleHidrometro);
		
		return retorno;

	}

}
