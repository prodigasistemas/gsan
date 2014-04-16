package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoRetornoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1020] Exibir Ordens de Repavimentação em Processo de Aceite.
 * 
 * @author Hugo Leonardo		
 * @date 17/05/2010
 */
public class ExibirAtualizarOrdemRepavimentacaoProcessoAceitePopupAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta a ação de retorno
		ActionForward retorno = actionMapping.findForward("exibirAtualizarOrdemRepavimentacaoProcessoAceitePopUp");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarOrdemRepavimentacaoProcessoAceiteActionForm form = (FiltrarOrdemRepavimentacaoProcessoAceiteActionForm) actionForm;
		
		if ( sessao.getAttribute("numeroPaginasPesquisa") != null ) {
    		 
    		String paginaAtual = (String) sessao.getAttribute("numeroPaginasPesquisa"); 
    		form.setManterPaginaAux(  paginaAtual );
		}

		form.setDataAceite("");
		form.setIndicadorSituacaoAceite("");
		form.setMotivo("");
	
	    Collection collOrdemServicoPavimento = (Collection) sessao.getAttribute("collOrdemServicoPavimentoAceite");	    
	  
	   	// Perguntar se posso encontrar apenas pelo numero da Os ?
       	String numeroOS = null;
       	Integer idOrdemServico = null;
        if (httpServletRequest.getParameter("numeroOS") != null) {
        	numeroOS = httpServletRequest.getParameter("numeroOS"); 
        	idOrdemServico= Util.converterStringParaInteger(numeroOS);       	
        	
        	if ( idOrdemServico != null ) {
	        	FiltroOrdemServicoPavimento filtroOrdemServicoPavimento = new FiltroOrdemServicoPavimento();
				filtroOrdemServicoPavimento.adicionarParametro(new ParametroSimples( FiltroOrdemServicoPavimento.ORDEM_SERVICO_ID,
							idOrdemServico));
		
				Collection colecaoOrdemServicoPavimento = fachada.pesquisar(filtroOrdemServicoPavimento, 
						OrdemServicoPavimento.class.getName());
				OrdemServicoPavimento ordemServicoPavimento = (OrdemServicoPavimento) 
						Util.retonarObjetoDeColecao(colecaoOrdemServicoPavimento);
				
				//SB0001 
				//1.1
				if ( ordemServicoPavimento.getIndicadorAceite() != null ) {
					form.setIndicadorSituacaoAceite("2");
					form.setDataAceite(Util.formatarData(ordemServicoPavimento.getDataAceite()) );
					form.setMotivo(ordemServicoPavimento.getDescricaoMotivoAceite());
				
				} else {
					
					form.setIndicadorSituacaoAceite("1");
					form.setDataAceite(Util.formatarData(new Date()) );
				}
        	
        	} 
			
        }
     
        if (httpServletRequest.getParameter("acao") != null && 
        		(httpServletRequest.getParameter("acao").equals("link") 
        		|| httpServletRequest.getParameter("acao").equals("botao"))) {
       
        	OrdemServico os = fachada.pesquisarOrdemServico(idOrdemServico);
        
        	OrdemServicoPavimento ordemServicoPavimento = null;
        
			if (sessao.getAttribute("collOrdemServicoPavimentoAceite") != null
				&& !sessao.getAttribute("collOrdemServicoPavimentoAceite").equals("")) {
				
					OSPavimentoRetornoHelper oSPavimentoRetornoHelper = null;
		
					Iterator iterator = collOrdemServicoPavimento.iterator();
					
					while (iterator.hasNext()) {
					
						oSPavimentoRetornoHelper = (OSPavimentoRetornoHelper) iterator.next();
		
						if(oSPavimentoRetornoHelper.getOrdemServico().getId().equals(os.getId())){	
							
							ordemServicoPavimento = oSPavimentoRetornoHelper.getOrdemServicoPavimento();	
							
							httpServletRequest.setAttribute("ordemServicoPavimentoAtualizar", ordemServicoPavimento);
							sessao.setAttribute("ordemServicoPavimentoAtualizar", ordemServicoPavimento);						
						}
					}	
			}	 
				
        	sessao.setAttribute("acao", "link");       	
		}else if (httpServletRequest.getParameter("acao") != null && 
        		httpServletRequest.getParameter("acao").equals("aceitarOSConvergente") ) {
			
			httpServletRequest.setAttribute("ordemServicoPavimentoAceitarOSConvergente", true);
			httpServletRequest.setAttribute("OSConvergente", "true");
			sessao.setAttribute("ordemServicoPavimentoAceitarOSConvergente", true);
		}
        
		if (httpServletRequest.getParameter("page.offset") != null 
				&& !httpServletRequest.getParameter("page.offset").equals("")) {
			
        	String numeroPagina = httpServletRequest.getParameter("page.offset");   
        	form.setManterPaginaAux(numeroPagina);
		}

		return retorno;
	}
}
