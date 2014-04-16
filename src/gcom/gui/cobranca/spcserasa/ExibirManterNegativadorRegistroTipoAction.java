package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.FiltroNegativadorRegistroTipo;
import gcom.cobranca.NegativadorRegistroTipo;
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
 * Action de exibir manter o Tipo do Registro do Negativador
 * @author Yara Taciane de Souza
 * @created 073/01/2008
 */

public class ExibirManterNegativadorRegistroTipoAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("manterNegativadorRegistroTipo");
		HttpSession sessao = httpServletRequest.getSession(false);
		//Fachada fachada = Fachada.getInstancia();
        Collection collectionNegativadorRegistroTipo = null;

		// Parte da verificação do filtro
        FiltroNegativadorRegistroTipo filtroNegativadorRegistroTipo = new FiltroNegativadorRegistroTipo();

		// Verifica se o filtro foi informado pela página de filtragem de Negativador Exclusao Motivo
		if (sessao.getAttribute("filtroNegativadorRegistroTipo") != null) {
			filtroNegativadorRegistroTipo = (FiltroNegativadorRegistroTipo) sessao.getAttribute("filtroNegativadorRegistroTipo");
		}
		
		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterNegativadorRegistroTipo"))) {
				
			// aqui 
			filtroNegativadorRegistroTipo.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
			
			filtroNegativadorRegistroTipo.setCampoOrderBy(FiltroNegativadorContrato.ID);

			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroNegativadorRegistroTipo, NegativadorRegistroTipo.class.getName());
			
			
			collectionNegativadorRegistroTipo = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			

			// [FS0004] Nenhum registro encontrado				
			if (collectionNegativadorRegistroTipo == null || collectionNegativadorRegistroTipo.isEmpty()) {
				// Nenhuma Contrato Negativador cadastrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (collectionNegativadorRegistroTipo.size()== 1 && identificadorAtualizar != null){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarNegativadorRegistroTipo");
				
				NegativadorRegistroTipo negativadorRegistroTipo = (NegativadorRegistroTipo)collectionNegativadorRegistroTipo.iterator().next();
				sessao.setAttribute("idRegistroAtualizacao", new Integer (negativadorRegistroTipo.getId()).toString());
			}else{						
				sessao.setAttribute("collectionNegativadorRegistroTipo", collectionNegativadorRegistroTipo);
			}

		}
		
		
        return retorno;
        
    }

}
