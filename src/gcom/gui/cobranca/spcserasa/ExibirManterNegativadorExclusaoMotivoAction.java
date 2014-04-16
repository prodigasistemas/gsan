package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.FiltroNegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorExclusaoMotivo;
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
 * Action de exibir manter o Negativador Exclusao Motivo
 * @author Yara Taciane de Souza
 * @created 03/01/2008
 */

public class ExibirManterNegativadorExclusaoMotivoAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("manterNegativadorExclusaoMotivo");
		HttpSession sessao = httpServletRequest.getSession(false);
		//Fachada fachada = Fachada.getInstancia();
        Collection collectionNegativadorExclusaoMotivo = null;

		// Parte da verificação do filtro
        FiltroNegativadorExclusaoMotivo filtroNegativadorExclusaoMotivo = new FiltroNegativadorExclusaoMotivo();

		// Verifica se o filtro foi informado pela página de filtragem de Negativador Exclusao Motivo
		if (sessao.getAttribute("filtroNegativadorExclusaoMotivo") != null) {
			filtroNegativadorExclusaoMotivo = (FiltroNegativadorExclusaoMotivo) sessao.getAttribute("filtroNegativadorExclusaoMotivo");
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterNegativadorExclusaoMotivo"))) {
				
			// aqui 
			filtroNegativadorExclusaoMotivo.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
			
			filtroNegativadorExclusaoMotivo.setCampoOrderBy(FiltroNegativadorContrato.ID);

			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroNegativadorExclusaoMotivo, NegativadorExclusaoMotivo.class.getName());
			
			
			collectionNegativadorExclusaoMotivo = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			

			// [FS0004] Nenhum registro encontrado				
			if (collectionNegativadorExclusaoMotivo == null || collectionNegativadorExclusaoMotivo.isEmpty()) {
				// Nenhuma Contrato Negativador cadastrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (collectionNegativadorExclusaoMotivo.size()== 1 && identificadorAtualizar != null){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarNegativadorExclusaoMotivo");
				
				NegativadorExclusaoMotivo negativadorExclusaoMotivo = (NegativadorExclusaoMotivo)collectionNegativadorExclusaoMotivo.iterator().next();
				sessao.setAttribute("idRegistroAtualizacao", new Integer (negativadorExclusaoMotivo.getId()).toString());
			}else{
				sessao.setAttribute("collectionNegativadorExclusaoMotivo", collectionNegativadorExclusaoMotivo);
			}

		}
		
		
		
        return retorno;
        
    }

}
