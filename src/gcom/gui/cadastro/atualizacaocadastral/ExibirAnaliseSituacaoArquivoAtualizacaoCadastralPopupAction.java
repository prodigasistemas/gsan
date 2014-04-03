package gcom.gui.cadastro.atualizacaocadastral;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAnaliseSituacaoArquivoAtualizacaoCadastralPopupAction extends GcomAction{

	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("consultarArquivoTextoAtualizacaoCadastral");

		Integer idArquivoAtualizacaoCadastral =  null;
		ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm form = new ExibirAnaliseSituacaoArquivoAtualizacaoCadastralActionForm();
		
		if(httpServletRequest.getParameter("idArquivo")!=null){
			idArquivoAtualizacaoCadastral = new Integer(httpServletRequest.getParameter("idArquivo"));
		}
		
		HashMap<String, Integer> mapDadosAnalise = Fachada.getInstancia().obterDadosAnaliseSituacaoArquivoAtualizacaoCadastral(idArquivoAtualizacaoCadastral);
			
		if ( mapDadosAnalise != null && !mapDadosAnalise.isEmpty()){
			form.setMapDadosAnalise(mapDadosAnalise);
		}  
		
		return retorno;
	}
}
