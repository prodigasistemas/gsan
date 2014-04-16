package gcom.gui.arrecadacao.aviso;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action que define o pré-processamento da página de filtrar aviso bancário
 * 
 * @author Vivianne Sousa
 * @created 09/03/2006
 */

public class ExibirFiltrarAvisoBancarioAction extends GcomAction {

	 /**
     * Este caso de uso cria um filtro q será usado na pesquisa de aviso bancário
     *
     * [UC0239] Filtrar Aviso Bancário
     *
     * @author Vivianne Sousa
     * @date 09/03/2006
     *
     * @param actionMapping 
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return 
     */

	 public ActionForward execute(ActionMapping actionMapping,
	            ActionForm actionForm, HttpServletRequest httpServletRequest,
	            HttpServletResponse httpServletResponse) {

	        //Seta o mapeamento de retorno
	        ActionForward retorno = actionMapping.findForward("filtrarAvisoBancario");
	        
	        FiltrarAvisoBancarioActionForm filtrarAvisoBancarioActionForm = (FiltrarAvisoBancarioActionForm) actionForm;
	        Fachada fachada = Fachada.getInstancia();
	        HttpSession sessao = httpServletRequest.getSession(false);
	        
	        httpServletRequest.removeAttribute("i");
	        
	        String atualizar = httpServletRequest.getParameter("atualizar");
	        
	        String menu = httpServletRequest.getParameter("menu");
	        
	        sessao.removeAttribute("manter");
	        sessao.removeAttribute("filtrar_manter");
			
			if (atualizar == null && menu == null){
				boolean i = true;
				httpServletRequest.setAttribute("i",i);
			}
			
			// Carregar a data corrente do sistema
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataCorrente = new GregorianCalendar();

			// Data Corrente
			httpServletRequest.setAttribute("dataAtual", formatoData
					.format(dataCorrente.getTime()));
	        
	        /*
	         * Trecho de código colocado por Raphael Rossiter em 22/03/2006
	         * Objetivo: Diferenciar o layout que será disponibilizado para o usuário de acordo com
	         * o parâmetro "acao" que está localizado no menu (Aviso Bancário - Efetuar Análise)
	         */
	        if (httpServletRequest.getParameter("acao") != null){
	        	sessao.setAttribute("acao", "EFETUAR_ANALISE");
	        }
	        
	        
	        if (httpServletRequest.getParameter("objetoConsulta") == null
	                && httpServletRequest.getParameter("tipoConsulta") == null) {

	        	sessao.removeAttribute("caminhoRetornoTelaPesquisa");
	        }
        
	        
	        //-------Parte que trata do código quando o usuário tecla enter
	        String idDigitadoEnterArrecadador = filtrarAvisoBancarioActionForm.getArrecadadorCodAgente();

	        
	        if (idDigitadoEnterArrecadador != null
					&& !idDigitadoEnterArrecadador.trim().equals("")
					&& Integer.parseInt(idDigitadoEnterArrecadador) > 0) {
				FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

				filtroArrecadador.adicionarParametro(new ParametroSimples(
						FiltroArrecadador.CODIGO_AGENTE, idDigitadoEnterArrecadador));
				
				filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
				
				Collection<Arrecadador> arrecadadorEncontrado = fachada.pesquisar(filtroArrecadador,
						Arrecadador.class.getName());

				if (arrecadadorEncontrado != null && !arrecadadorEncontrado.isEmpty()) {
					// O arrecadador foi encontrado
					filtrarAvisoBancarioActionForm.setArrecadadorCodAgente(""
							+ ((Arrecadador) ((List) arrecadadorEncontrado).get(0))
									.getCodigoAgente());
					filtrarAvisoBancarioActionForm
							.setArrecadadorNomeCliente(((Arrecadador) ((List) arrecadadorEncontrado)
									.get(0)).getCliente().getNome());
					httpServletRequest.setAttribute("idArrecadadorNaoEncontrado",
							"true");

				} else {
					
					filtrarAvisoBancarioActionForm.setArrecadadorCodAgente("");
					httpServletRequest.setAttribute("idArrecadadorNaoEncontrado",
							"exception");
					filtrarAvisoBancarioActionForm
							.setArrecadadorNomeCliente("ARRECADADOR INEXISTENTE");

				}

			}
	        //-------Fim de parte que trata do código quando o usuário tecla enter
	                           

	   
	        if (httpServletRequest.getParameter("tipoConsulta") != null
	                && !httpServletRequest.getParameter("tipoConsulta").equals("")) {

        		//se for os parametros de enviarDadosParametros serão mandados para
        		// a pagina aviso_bancario_filtrar.jsp
				if (httpServletRequest.getParameter("tipoConsulta").equals(
				"arrecadador")) {
	        		filtrarAvisoBancarioActionForm.setArrecadadorCodAgente(
	                        httpServletRequest.getParameter("idCampoEnviarDados"));
	        		filtrarAvisoBancarioActionForm.setArrecadadorNomeCliente(
	        			    httpServletRequest.getParameter("descricaoCampoEnviarDados"));
	        	}
				
	        }	
	        
	        
            sessao.removeAttribute("caminhoRetornoTelaPesquisaArrecadador");
	        httpServletRequest.setAttribute("nomeCampo","arrecadadorCodAgente");
        
	        return retorno;
	    }
}
