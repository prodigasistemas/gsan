package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
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
 * [UC0758]FILTRAR FORMA DE ARRECADACAO
 * 
 * @author Vinícius Medeiros
 * @date 09/04/2008
 */

public class FiltrarArrecadacaoFormaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho do retorno
		ActionForward retorno = actionMapping.findForward("exibirManterArrecadacaoForma");

		// Mudar isso quando for implementado o esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		FiltrarArrecadacaoFormaActionForm filtrarArrecadacaoFormaActionForm = (FiltrarArrecadacaoFormaActionForm) actionForm;

		FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();

		boolean peloMenosUmParametroInformado = false;

		String idArrecadacaoForma = filtrarArrecadacaoFormaActionForm.getIdArrecadacaoForma();
		String descricao = filtrarArrecadacaoFormaActionForm.getDescricao();
		String codigoArrecadacaoForma = filtrarArrecadacaoFormaActionForm.getCodigoArrecadacaoForma();
		String tipoPesquisa = filtrarArrecadacaoFormaActionForm.getTipoPesquisa();

		// Filtro pelo ID
		if (idArrecadacaoForma != null && !idArrecadacaoForma.trim().equals("")) {
			
			boolean achou = fachada.verificarExistenciaAgente(new Integer(idArrecadacaoForma));
			
			if (achou) {
			
				peloMenosUmParametroInformado = true;
				filtroArrecadacaoForma.adicionarParametro(
						new ParametroSimples(FiltroArrecadacaoForma.CODIGO, 
								idArrecadacaoForma));
			}
		}

		// Filtro pela Descrição
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null && tipoPesquisa.equals(
					ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {

				filtroArrecadacaoForma.adicionarParametro(
						new ComparacaoTextoCompleto(FiltroArrecadacaoForma.DESCRICAO, 
								descricao));
			
			} else {

				filtroArrecadacaoForma.adicionarParametro(
						new ComparacaoTexto(FiltroArrecadacaoForma.DESCRICAO, 
								descricao));
			}
		}
			

		// Filtro pelo Código
		if (codigoArrecadacaoForma != null && !codigoArrecadacaoForma.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			filtroArrecadacaoForma.adicionarParametro(
					new ParametroSimples(FiltroArrecadacaoForma.CODIGO_ARRECADACAO_FORMA, 
							codigoArrecadacaoForma));
		}
		
		Collection<ArrecadacaoForma> colecaoArrecadacaoForma = fachada.pesquisar(
				filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
		
		//Verificar a existencia de uma Forma de Arrecadacao
		if (colecaoArrecadacaoForma.isEmpty()) {
			
			throw new ActionServletException(
					"atencao.pesquisa.arrecadacaoforma_inexistente");
		}

		// Filtragem sem parametros
		 if (!peloMenosUmParametroInformado) {
	        
			throw new ActionServletException(
	                    "atencao.filtro.nenhum_parametro_informado");
        } else {

        	httpServletRequest.setAttribute("filtroArrecadacaoForma",filtroArrecadacaoForma);
	        sessao.setAttribute("filtroArrecadacaoForma",filtroArrecadacaoForma);	            
	        sessao.setAttribute("idArrecadacaoForma",idArrecadacaoForma);
	            
	        }
		 
		// Pesquisa sem registros 
		if (colecaoArrecadacaoForma == null || colecaoArrecadacaoForma.isEmpty()) {
		
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null,"Forma de Arrecadação");
		
		} else {
		
			httpServletRequest.setAttribute("colecaoArrecadacaoForma",colecaoArrecadacaoForma);
			ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
			arrecadacaoForma = (ArrecadacaoForma) Util.retonarObjetoDeColecao(colecaoArrecadacaoForma);
			String idRegistroAtualizacao = arrecadacaoForma.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroArrecadacaoForma", filtroArrecadacaoForma);
		
		httpServletRequest.setAttribute("filtroArrecadacaoForma", filtroArrecadacaoForma);

		return retorno;

	}
}
