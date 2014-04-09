package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade exibir a tela de pesquisa dos itens do movimento do arrecadador
 *
 * @author Vivianne Sousa	
 * @date 05/12/2006
 */
public class ExibirPesquisarItensMovimentoArrecadadorAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirPesquisarItensMovimentoArrecadador");

        Fachada fachada = Fachada.getInstancia();
        String idArrecadadorMovimento = httpServletRequest.getParameter("arrecadadorMovimentoID");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        PesquisarItensMovimentoArrecadadorActionForm pesquisarItensMovimentoArrecadadorActionForm = 
        	(PesquisarItensMovimentoArrecadadorActionForm) actionForm;
        
        if (idArrecadadorMovimento != null){
        	pesquisarItensMovimentoArrecadadorActionForm.setIdArrecadadorMovimento(idArrecadadorMovimento);
        	pesquisarItensMovimentoArrecadadorActionForm.setDescricaoOcorrencia("3");
        	pesquisarItensMovimentoArrecadadorActionForm.setIndicadorAceitacao("3");
        	pesquisarItensMovimentoArrecadadorActionForm.setIndicadorDiferencaValorMovimentoValorPagamento("3");
        	pesquisarItensMovimentoArrecadadorActionForm.setInscricaoImovel("");
        	pesquisarItensMovimentoArrecadadorActionForm.setMatriculaImovel("");
        	pesquisarItensMovimentoArrecadadorActionForm.setNomeAgencia("");
        	pesquisarItensMovimentoArrecadadorActionForm.setNomeBanco("");
        	
        	if (sessao.getAttribute("formaArrecadacao")!= null && !sessao.getAttribute("formaArrecadacao").equals("")){
     		   
      		  pesquisarItensMovimentoArrecadadorActionForm.setFormaArrecadacao("-1");
      	   }
        }
        
        String idImovel = pesquisarItensMovimentoArrecadadorActionForm.getMatriculaImovel();
        
        if (idImovel!= null && !idImovel.equals("")){
        	String inscricaoImovel = fachada.pesquisarInscricaoImovel(new Integer(idImovel));
        
        	if (inscricaoImovel != null && !inscricaoImovel.equals("")) {
        		pesquisarItensMovimentoArrecadadorActionForm.setInscricaoImovel(inscricaoImovel);
				httpServletRequest.setAttribute("idImovelNaoEncontrado","true");

			} else {
				pesquisarItensMovimentoArrecadadorActionForm.setMatriculaImovel("");
				httpServletRequest.setAttribute("idImovelNaoEncontrado","exception");
				pesquisarItensMovimentoArrecadadorActionForm.setInscricaoImovel("IMÓVEL INEXISTENTE");

			}
        }
        
        if (httpServletRequest.getParameter("tipoConsulta") != null
                && !httpServletRequest.getParameter("tipoConsulta").equals("")) {
            //se for os parametros de enviarDadosParametros serão mandados para
            // a pagina .jsp
        	pesquisarItensMovimentoArrecadadorActionForm.setMatriculaImovel(
                        httpServletRequest.getParameter("idCampoEnviarDados"));
        	pesquisarItensMovimentoArrecadadorActionForm.setInscricaoImovel(
        			    httpServletRequest.getParameter("descricaoCampoEnviarDados"));
        }
        
        // ------------- Forma de Arrecadacao ----------  Kassia Albuquerque##
        if (sessao.getAttribute("formaArrecadacao")!= null && !sessao.getAttribute("formaArrecadacao").equals("")){
        	
        	if (sessao.getAttribute("formaArrecadacao").equals("Retorno")){
            	
            	FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
            	
            	filtroArrecadacaoForma.setCampoOrderBy(FiltroArrecadacaoForma.DESCRICAO);
    						
    			Collection<ArrecadacaoForma> colecaoArrecadacaoForma = fachada.pesquisar
    						(filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
    	
    			if (colecaoArrecadacaoForma == null || colecaoArrecadacaoForma.isEmpty()) {
    				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Forma de Arrecadação");
    			}
    	
    			httpServletRequest.setAttribute("colecaoArrecadacaoForma",colecaoArrecadacaoForma);
    			
            	
            }
        }
        
        
        return retorno;
    }

}
