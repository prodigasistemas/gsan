package gcom.gui.cadastro;

import gcom.batch.Processo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.util.ConstantesSistema;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0925] Emitir Boletos
 * 
 * @author Vivianne Sousa
 * @data 13/07/2009
 */

public class EmitirBoletosAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        EmitirBoletosActionForm form = (EmitirBoletosActionForm) actionForm;

        //Este map levará todos os parâmetros para a inicialização do relatório
        Map parametros = new HashMap();
        
        
        if(form.getGrupoFaturamento() == null 
        		|| form.getGrupoFaturamento().equals("")
        		|| form.getGrupoFaturamento().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	throw new ActionServletException("atencao.campo.informado", null, "Grupo de Faturamento");
        }
        String idGrupoFaturamento  = form.getGrupoFaturamento();
        parametros.put("idGrupoFaturamento",idGrupoFaturamento);


    	Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, 
         		Processo.EMITIR_BOLETOS,
         		this.getUsuarioLogado(httpServletRequest));
   
        
        montarPaginaSucesso(httpServletRequest, "Processo Inserido.", "", "");
        
		return retorno;
	}
	
}
