package gcom.gui.cobranca;

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
 * 
 * @author Vivianne Sousa
 * @data 10/11/2009
 */

public class GerarCartasFinalDeAnoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        GerarCartasFinalDeAnoActionForm form = (GerarCartasFinalDeAnoActionForm) actionForm;

        //Este map levará todos os parâmetros para a inicialização do relatório
        Map parametros = new HashMap();
        
        
        if(form.getGrupoFaturamento() == null 
        		|| form.getGrupoFaturamento().equals("")
        		|| form.getGrupoFaturamento().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	throw new ActionServletException("atencao.campo.informado", null, "Grupo de Faturamento");
        }
        String idGrupoFaturamento  = form.getGrupoFaturamento();
        parametros.put("idGrupoFaturamento",idGrupoFaturamento);


        if(form.getAcao() == null 
        		|| form.getAcao().equals("")
        		|| form.getAcao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	throw new ActionServletException("atencao.campo.informado", null, "Ação");
        }
        String acao  = form.getAcao();
        parametros.put("acao",acao);
        
        if(acao.equals("1")){
        	 Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, 
             		Processo.GERAR_CARTAS_DE_FINAL_DE_ANO ,
             		this.getUsuarioLogado(httpServletRequest));
        }else{
        	 Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, 
             		Processo.EMITIR_CARTAS_DE_FINAL_DE_ANO,
             		this.getUsuarioLogado(httpServletRequest));
        }
        
        montarPaginaSucesso(httpServletRequest, "Processo Inserido.", "", "");
        
		return retorno;
	}
	
}
