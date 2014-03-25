package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarVencimentoFaturaClienteResponsavelAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirAtualizarVencimentoFaturaClienteResponsavel");
		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarVencimentoFaturaClienteResponsavelActionForm form = (AtualizarVencimentoFaturaClienteResponsavelActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		Date dataVencimento = Util.converteStringParaDate(form.getDataVencimento());
		String anoMesReferencia = Util.formatarMesAnoParaAnoMesSemBarra(form.getAnoMesReferencia());
		Integer qtdeContas = 0;
		
		if(dataVencimento == null) {
			throw new ActionServletException("atencao.informe_campo_obrigatorio", null,
					"Data vencimento");
		}
		
		if(anoMesReferencia == null) {
			throw new ActionServletException("atencao.informe_campo_obrigatorio", null,
					"Mês/Ano de Referência");
		}
		
		qtdeContas = fachada.countFaturasClienteResponsaveis(anoMesReferencia);
		
		//Controlamos o cancelamento de contas pagas
        Object[] arrayValidaQtdeFaturas = validarQtdeFaturas( 
                qtdeContas,
                httpServletRequest,
                actionMapping );
		
        if ( ( Boolean ) arrayValidaQtdeFaturas[2] ){
            return ( ActionForward ) arrayValidaQtdeFaturas[0];
        }
        
        Boolean confirmadoCancelamentoContasPagas = ( Boolean )arrayValidaQtdeFaturas[1];
        
        if ( confirmadoCancelamentoContasPagas != null && !confirmadoCancelamentoContasPagas ){
            return retorno;
        }
		
		fachada.atualizarVecimentoFaturaClienteResponsavel(dataVencimento, anoMesReferencia);

		montarPaginaSucesso(httpServletRequest, "Atualização do vencimento de faturas realizada com sucesso",
				"Voltar", "/exibirAtualizarVencimentoFaturaClienteResponsavel.do");

		retorno =  ( ActionForward ) arrayValidaQtdeFaturas[0];
		return retorno;
	}
	
	 private Object[] validarQtdeFaturas( 
	            Integer qtde, 
	            HttpServletRequest request,
	            ActionMapping actionMapping ){
	        
	        Object[] retorno = new Object[3];
	        String[] qtdeArray = new String[1];
	        qtdeArray[0] = qtde.toString();
	        
	        retorno[1] = ( request.getParameter("confirmado") != null ? 
	                    request.getParameter("confirmado").equals("ok") : null );
	        
	        retorno[2] = new Boolean( Boolean.FALSE );
	        
	        if ( retorno[1] == null ){

	        	request.setAttribute("caminhoActionConclusao",
	        			"/gsan/atualizarVencimentoFaturaClienteResponsavelAction.do");
	        	request.setAttribute("cancelamento", "TRUE");
	        	request.setAttribute("nomeBotao1", "Sim");
	        	request.setAttribute("nomeBotao2", "Não");

	        	retorno[0] = montarPaginaConfirmacao(
	        			"atencao.qtde.faturas.atualizar.vencimento",
	        			request, 
	        			actionMapping, 
	        			qtdeArray );

	        	retorno[2] = new Boolean( Boolean.TRUE );
	        	return retorno;
	        }       
	        
	        retorno[0] = actionMapping.findForward("telaSucesso");
	        return retorno;
	    }
}
