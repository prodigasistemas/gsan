package gcom.gui.arrecadacao;


import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os parâmetros para realização
 * da filtragem dos movimentos dos arrecadadores 
 *
 * @author Raphael Rossiter, Pedro Alexandre
 * @date 23/02/2006, 04/07/2007
 */
public class ExibirFiltrarMovimentoArrecadadoresAction extends GcomAction {
    
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, 
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirFiltrarMovimentoArrecadadores");
        
        Fachada fachada = Fachada.getInstancia();
        
        FiltrarMovimentoArrecadadoresActionForm filtrarMovimentoArrecadadoresActionForm = (FiltrarMovimentoArrecadadoresActionForm) actionForm;
        
        
        //Carregar a data corrente do sistema
        //====================================
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataCorrente = new GregorianCalendar();
        
        String indicadorRelatorio = httpServletRequest.getParameter("relatorio");
        
        if(filtrarMovimentoArrecadadoresActionForm.getIndicadorRelatorio() == null || filtrarMovimentoArrecadadoresActionForm.getIndicadorRelatorio().equals("")){
	        if(indicadorRelatorio != null && indicadorRelatorio.equals("sim")){
	        	//httpServletRequest.setAttribute("relatorio",ConstantesSistema.SIM);
	        	filtrarMovimentoArrecadadoresActionForm.setIndicadorRelatorio(""+ConstantesSistema.SIM);
	        	filtrarMovimentoArrecadadoresActionForm.setRemessa(""+ConstantesSistema.CODIGO_RETORNO);
	        }else{
	        	filtrarMovimentoArrecadadoresActionForm.setIndicadorRelatorio(""+ConstantesSistema.NAO);
	        	//httpServletRequest.setAttribute("relatorio",ConstantesSistema.NAO);
	        }
        }
        
        //Data Corrente
        httpServletRequest.setAttribute("dataAtual", formatoData.format(dataCorrente.getTime()));
        
        FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
        Collection colecaoArrecadacaoForma = fachada.pesquisar(filtroArrecadacaoForma,ArrecadacaoForma.class.getName());
        httpServletRequest.setAttribute("colecaoArrecadacaoForma",colecaoArrecadacaoForma);
        
        
        if (filtrarMovimentoArrecadadoresActionForm.getBanco() != null &&
            	!filtrarMovimentoArrecadadoresActionForm.getBanco().equals("")){	
        
        	FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

        	filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
        	
        	filtroArrecadador.adicionarParametro(new ParametroSimples(
        	FiltroArrecadador.CODIGO_AGENTE, filtrarMovimentoArrecadadoresActionForm.getBanco()));

            
            Collection colecaoArrecadador = fachada.pesquisar(filtroArrecadador,
            Arrecadador.class.getName());

            if (colecaoArrecadador == null || colecaoArrecadador.isEmpty()) {
                
            	httpServletRequest.setAttribute("corBanco", "exception");
                
            	filtrarMovimentoArrecadadoresActionForm.setBanco("");
            	
            	filtrarMovimentoArrecadadoresActionForm.setDescricaoBanco("ARRECADADOR INEXISTENTE");
                
            	httpServletRequest.setAttribute("nomeCampo", "banco");
            	
            } else {
                Arrecadador arrecadador = (Arrecadador) Util.retonarObjetoDeColecao(colecaoArrecadador);
                
                filtrarMovimentoArrecadadoresActionForm.setBanco(String.valueOf(arrecadador.getCodigoAgente()));
                
                filtrarMovimentoArrecadadoresActionForm.setDescricaoBanco(arrecadador.getCliente().getNome());
                
                httpServletRequest.setAttribute("corBanco", "valor");
                
                httpServletRequest.setAttribute("nomeCampo", "identificacaoServico");
            }
        }
      
        if(httpServletRequest.getAttribute("nomeCampo") == null)
        {
        	httpServletRequest.setAttribute("nomeCampo", "banco");
        }
        return retorno;
    }
}

