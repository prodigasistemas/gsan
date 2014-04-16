package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.FiltroAtividade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0438] Pesquisar Atividade
 * 
 * @author Ana Maria
 * @date 03/08/2006
 * 
 */
public class PesquisarAtividadeAction extends GcomAction {
	/**
	 * [UC0438] Esse caso de uso efetua pesquisa de Atividade
	 * 
	 * @author Ana Maria
	 * @date 03/08/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return ActionForward
	 */
	   public ActionForward execute(ActionMapping actionMapping,
	            ActionForm actionForm, HttpServletRequest httpServletRequest,
	            HttpServletResponse httpServletResponse) {

	        ActionForward retorno = actionMapping.findForward("listaAtividade");
	        
	        //Obtém a instância da Fachada
	        Fachada fachada = Fachada.getInstancia();
	        
	        //HttpSession sessao = httpServletRequest.getSession(false);
	        
			// Obtém o action form
	        PesquisarAtividadeActionForm pesquisarAtividadeActionForm = (PesquisarAtividadeActionForm) actionForm;

			// Recupera os parâmetros do form
	        String idAtividade = (String) pesquisarAtividadeActionForm.getIdAtividade();
	        String descricaoAtividade = (String) pesquisarAtividadeActionForm.getDescricaoAtividade();
	        String abreviaturaAtividade = (String) pesquisarAtividadeActionForm.getAbreviaturaAtividade();	  
	        String tipoPesquisa = (String) pesquisarAtividadeActionForm.getTipoPesquisa();
	        String tipoPesquisaAbreviada = (String) pesquisarAtividadeActionForm.getTipoPesquisaAbreviada();

	        boolean peloMenosUmParametroInformado = false;

	         FiltroAtividade filtroAtividade = new FiltroAtividade(FiltroAtividade.DESCRICAO);	        

	        if (idAtividade != null && !idAtividade.trim().equalsIgnoreCase("")) {
	        	filtroAtividade.adicionarParametro(new ParametroSimples(
	        			FiltroAtividade.ID, new Integer(idAtividade)));
                peloMenosUmParametroInformado = true;
	        }
	        
	        if (descricaoAtividade != null && !descricaoAtividade.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true; 
    			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
    				filtroAtividade.adicionarParametro(new ComparacaoTextoCompleto(
    						FiltroAtividade.DESCRICAO, descricaoAtividade));
    			} else {
    				filtroAtividade.adicionarParametro(new ComparacaoTexto(
    						FiltroAtividade.DESCRICAO, descricaoAtividade));
    			}
	        }
	        
	        if (abreviaturaAtividade != null && !abreviaturaAtividade.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;
    			if (tipoPesquisaAbreviada != null && tipoPesquisaAbreviada.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
    				filtroAtividade.adicionarParametro(new ComparacaoTextoCompleto(
    						FiltroAtividade.DESCRICAOABREVIADA, abreviaturaAtividade));
    			} else {
    				filtroAtividade.adicionarParametro(new ComparacaoTexto(
    						FiltroAtividade.DESCRICAOABREVIADA, abreviaturaAtividade));
    			}     
	        }	      
			// Erro caso o usuário mandou filtrar sem nenhum parâmetro
	        if (!peloMenosUmParametroInformado) {
	            throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
	        }
	        
	        filtroAtividade.adicionarParametro(new ParametroSimples(
					FiltroAtividade.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));
	        
	        filtroAtividade.adicionarParametro(new ParametroSimples(
					FiltroAtividade.INDICADORATIVIDADEUNICA, Atividade.INDICADOR_ATIVIDADE_UNICA_NAO));
	        
			// Faz a pesquisa baseada no filtro
	        Collection atividades = fachada.pesquisar(filtroAtividade, Atividade.class.getName());
	        
			// Verificar se a pesquisa de atividade não está vazia
	        if (atividades != null && !atividades.isEmpty()) {
                 // Aciona o controle de paginação para que sejam pesquisados apenas
				// os registros que aparecem na página
				Map resultado = controlarPaginacao(httpServletRequest, retorno,
						filtroAtividade, Atividade.class.getName());
				atividades = (Collection) resultado.get("colecaoRetorno");
				retorno = (ActionForward) resultado.get("destinoActionForward");
				
				//sessao.setAttribute("atividades", atividades);
				// Manda a coleção das Atividade pesquisadas para o request
				httpServletRequest.getSession(false).setAttribute("atividades",
						atividades);
				
	        } else {
	            throw new ActionServletException(
	                    "atencao.pesquisa.nenhumresultado", null, "atividade");
	        }
	        
	        return retorno;
	    }

	}
