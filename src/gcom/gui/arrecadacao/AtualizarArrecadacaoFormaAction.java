package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarArrecadacaoFormaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarArrecadacaoFormaActionForm atualizarArrecadacaoFormaActionForm = (AtualizarArrecadacaoFormaActionForm) actionForm;

		ArrecadacaoForma arrecadacaoForma = (ArrecadacaoForma) sessao.getAttribute("arrecadacaoFormaAtualizar");

		arrecadacaoForma.setDescricao(atualizarArrecadacaoFormaActionForm.getDescricao());
		arrecadacaoForma.setCodigoArrecadacaoForma(atualizarArrecadacaoFormaActionForm.getCodigoArrecadacaoForma());
		
		String descricaoArrecadacaoForma = atualizarArrecadacaoFormaActionForm
        	.getDescricao();
        String codigodaArrecadacaoForma = atualizarArrecadacaoFormaActionForm
        	.getCodigoArrecadacaoForma();
		
        arrecadacaoForma.setDescricao(descricaoArrecadacaoForma);
        arrecadacaoForma.setUltimaAlteracao( new Date() );	
        arrecadacaoForma.setCodigoArrecadacaoForma(codigodaArrecadacaoForma);
		
		fachada.atualizar(arrecadacaoForma);
					
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

		montarPaginaSucesso(httpServletRequest, "Forma de Arrecadação "
				+ atualizarArrecadacaoFormaActionForm.getIdArrecadacaoForma().toString() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Forma de Arrecadação ",
				"exibirFiltrarArrecadacaoFormaAction.do?menu=sim");
        }
        
        
		return retorno;
	}
}
