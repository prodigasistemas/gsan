package gcom.gui.faturamento.conta;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Retificação de um conjunto de contas que foram pagas e que o pagamento não estava o débito e/ou crédito (Conta paga via Impressão Simultânea) 
 *
 * @author Sávio Luiz
 * @date 27/12/2010
 */
public class ExibirRetificarContasPagasSemDebitoCreditoAction extends GcomAction {

	 public ActionForward execute(ActionMapping actionMapping,
	            ActionForm actionForm, HttpServletRequest httpServletRequest,
	            HttpServletResponse httpServletResponse) {

	    	ActionForward retorno = actionMapping.findForward("exibirRetificarContasPagas");
	        
	    	RetificarContasPagasSemDebitoCreditoActionForm retificarContasPagasSemDebitoCreditoActionForm = (RetificarContasPagasSemDebitoCreditoActionForm) actionForm;
	        
	        	        
	        Fachada fachada = Fachada.getInstancia();
	        
	        	        
        
	        
	        HttpSession sessao = httpServletRequest.getSession(false);
	        
	        String selecionarContas = httpServletRequest.getParameter("quantidadeConta");
	        
	        if(selecionarContas != null && !selecionarContas.equals("")){
	        	Integer referenciaConta = Util.formatarMesAnoComBarraParaAnoMes(retificarContasPagasSemDebitoCreditoActionForm.getReferenciaConta());
	        	Integer idGrupoFaturamento = null;
	        	if(retificarContasPagasSemDebitoCreditoActionForm.getIdGrupo() != null && !retificarContasPagasSemDebitoCreditoActionForm.getIdGrupo().equals("")){
	        		idGrupoFaturamento = Util.converterStringParaInteger(retificarContasPagasSemDebitoCreditoActionForm.getIdGrupo());
	        	}
	        	Collection colecaoContasPagas = fachada.pesquisarContasPagasSemDebitoCreditoPago(referenciaConta,idGrupoFaturamento);
	        	retificarContasPagasSemDebitoCreditoActionForm.setQuatidadeConta(""+colecaoContasPagas.size());
	        	sessao.setAttribute("colecaoContasPagas",colecaoContasPagas);
	        }else{
		        //então é a primeira vez.
		        
		        
		        /*CARREGAMENTO INICIAL DO FORMULÁRIO
		        ========================================================================================== */
		        
		        //Pesquisando grupo de faturamento
		        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		        filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.INDICADOR_USO,ConstantesSistema.SIM));
		        filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
	
		        Collection<FaturamentoGrupo> collectionFaturamentoGrupo = 
		        	this.getFachada().pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
	
		        sessao.setAttribute("collectionFaturamentoGrupo", collectionFaturamentoGrupo);
		        
	        }

	       	        
	        
	        return retorno;
	 }
	 
}
