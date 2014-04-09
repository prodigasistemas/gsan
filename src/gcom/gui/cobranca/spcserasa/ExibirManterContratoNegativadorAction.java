package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.NegativadorContrato;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativadorContrato;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de exibir manter o Contrato do Negativador
 * @author Yara Taciane de Souza
 * @created 20/12/2007
 */

public class ExibirManterContratoNegativadorAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("manterContratoNegativador");
		HttpSession sessao = httpServletRequest.getSession(false);
		//Fachada fachada = Fachada.getInstancia();
        Collection collectionContratoNegativador = null;

		// Parte da verificação do filtro
        FiltroNegativadorContrato filtroNegativadorContrato = new FiltroNegativadorContrato();

		// Verifica se o filtro foi informado pela página de filtragem de Contrato de Negativador
		if (sessao.getAttribute("filtroNegativadorContrato") != null) {
			filtroNegativadorContrato = (FiltroNegativadorContrato) sessao.getAttribute("filtroNegativadorContrato");
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterContratoNegativador"))) {
				
			// aqui 
			filtroNegativadorContrato.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
			
			filtroNegativadorContrato.setCampoOrderBy(FiltroNegativadorContrato.ID);

			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroNegativadorContrato, NegativadorContrato.class.getName());
			
			
			collectionContratoNegativador = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			

			// [FS0004] Nenhum registro encontrado				
			if (collectionContratoNegativador == null || collectionContratoNegativador.isEmpty()) {
				// Nenhuma Contrato Negativador cadastrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (collectionContratoNegativador.size()== 1 && identificadorAtualizar != null){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarContratoNegativador");
				
				NegativadorContrato negativadorContrato = (NegativadorContrato)collectionContratoNegativador.iterator().next();
				sessao.setAttribute("idRegistroAtualizacao", new Integer (negativadorContrato.getId()).toString());
			}else{
				sessao.setAttribute("collectionContratoNegativador", collectionContratoNegativador);
			}

		}
	
		
        return retorno;
        
    }

}
