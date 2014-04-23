package gcom.gui.arrecadacao;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarDadosDiariosArrecadacaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		//FachadaBatch fachadaBatch = FachadaBatch.getInstancia();
		
		//fachadaBatch.encerrarArrecadacaoMes();
		
		/*fachada.obterPrincipalCategoriaImovel(new Integer("18379112"));*/
		
		/*
		fachada.gerarDadosDiariosArrecadacao();
		*/
		/*try{
		Util.obterRepresentacaoNumericaCodigoBarra(new Integer(3),new BigDecimal("100.69"),new Integer(339),new Integer(55000074),"042006",new Integer(1),new Integer(1),null,null,null,null,null);
		}catch(ParametroNaoInformadoException e){
			
		}*/
		montarPaginaSucesso(httpServletRequest,"sucesso.", "Inserir outra Devolução",
				"exibirInserirDevolucoesAction.do");
		
		return retorno;
	}
}
