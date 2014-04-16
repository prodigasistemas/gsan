package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.seguranca.transacao.Tabela;
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

public class PesquisarTabelaAction extends GcomAction {
	/**
	 * Pesquisar Tabela
	 * 
	 * @author Vinicius Medeiros
	 * @date 12/05/2008
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

	        ActionForward retorno = actionMapping.findForward("pesquisarTabela");
	        
	        //Obtém a instância da Fachada
	        Fachada fachada = Fachada.getInstancia();
	        
	        //HttpSession sessao = httpServletRequest.getSession(false);
	        
			// Obtém o action form
	        PesquisarTabelaActionForm pesquisarTabelaActionForm = (PesquisarTabelaActionForm) actionForm;

			// Recupera os parâmetros do form
	        String id = (String) pesquisarTabelaActionForm.getId();
	        String descricao = (String) pesquisarTabelaActionForm.getDescricao();
	        String nomeTabela = (String) pesquisarTabelaActionForm.getNomeTabela();	  
	        String tipoPesquisa = (String) pesquisarTabelaActionForm.getTipoPesquisa();

	        boolean peloMenosUmParametroInformado = false;

	         FiltroTabela filtroTabela = new FiltroTabela(FiltroTabela.DESCRICAO);	        

	        if (id != null && !id.trim().equalsIgnoreCase("")) {
	        	filtroTabela.adicionarParametro(new ParametroSimples(
	        			FiltroTabela.ID, new Integer(id)));
                peloMenosUmParametroInformado = true;
	        }
	        
	        if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true; 
    			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
    				filtroTabela.adicionarParametro(new ComparacaoTextoCompleto(
    						FiltroTabela.DESCRICAO, descricao));
    			} else {
    				filtroTabela.adicionarParametro(new ComparacaoTexto(
    						FiltroTabela.DESCRICAO, descricao));
    			}
	        }
	        
	        if (nomeTabela != null && !nomeTabela.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;
	            filtroTabela.adicionarParametro(new ComparacaoTextoCompleto(
	            		FiltroTabela.NOME, nomeTabela));
	        }	      
			// Erro caso o usuário mandou filtrar sem nenhum parâmetro
	        if (!peloMenosUmParametroInformado) {
	            throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
	        }
	        
			// Faz a pesquisa baseada no filtro
	        Collection tabelas = fachada.pesquisar(filtroTabela, Tabela.class.getName());
	        
			// Verificar se a pesquisa de atividade não está vazia
	        if (tabelas != null && !tabelas.isEmpty()) {
                 // Aciona o controle de paginação para que sejam pesquisados apenas
				// os registros que aparecem na página
				Map resultado = controlarPaginacao(httpServletRequest, retorno,
						filtroTabela, Tabela.class.getName());
				tabelas = (Collection) resultado.get("colecaoRetorno");
				retorno = (ActionForward) resultado.get("destinoActionForward");
				
				//sessao.setAttribute("atividades", atividades);
				// Manda a coleção das Atividade pesquisadas para o request
				httpServletRequest.getSession(false).setAttribute("tabelas",
						tabelas);
				
	        } else {
	            throw new ActionServletException(
	                    "atencao.pesquisa.nenhumresultado", null, "tabela");
	        }
	        
	        return retorno;
	    }

	}
