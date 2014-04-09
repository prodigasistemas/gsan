package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de exibir manter motivo de retorno do registro do negativador
 * @author Thiago Vieira
 * @created 07/01/2008
 */

public class ExibirManterNegativadorRetornoMotivoAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("manterNegativadorRetornoMotivo");
		HttpSession sessao = httpServletRequest.getSession(false);
        Collection collectionNegativadorRetornoMotivo = null;

        FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = null;
        
		// Verifica se o filtro foi informado pela página de filtragem do Negativador
		if (sessao.getAttribute("filtroNegativadorRetornoMotivo") != null) {
			filtroNegativadorRetornoMotivo = (FiltroNegativadorRetornoMotivo) sessao.getAttribute("filtroNegativadorRetornoMotivo");
		}
		
		String checkAtualizar = "";
		
		if (sessao.getAttribute("checkAtualizar") != null) {
			checkAtualizar = (String) sessao.getAttribute("checkAtualizar");
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterNegativadorRetornoMotivo"))) {
				
			filtroNegativadorRetornoMotivo.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
			filtroNegativadorRetornoMotivo.setCampoOrderBy(FiltroNegativadorRetornoMotivo.ID);

			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroNegativadorRetornoMotivo, NegativadorRetornoMotivo.class.getName());
			
			
			collectionNegativadorRetornoMotivo = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			// [FS0004] Nenhum registro encontrado				
			if (collectionNegativadorRetornoMotivo == null || collectionNegativadorRetornoMotivo.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			//carrega nome do negativador escolhido
			Iterator i = collectionNegativadorRetornoMotivo.iterator();
			NegativadorRetornoMotivo nrm = (NegativadorRetornoMotivo)i.next();
			String nomeNegativador = nrm.getNegativador().getCliente().getNome();
			
			if (collectionNegativadorRetornoMotivo.size()== 1 && checkAtualizar != null && !checkAtualizar.equals("")){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarNegativadorRetornoMotivo");
				
				NegativadorRetornoMotivo negativadorRetornoMotivo = (NegativadorRetornoMotivo)collectionNegativadorRetornoMotivo.iterator().next();
				sessao.setAttribute("idRegistroAtualizacao", new Integer (negativadorRetornoMotivo.getId()).toString());
			}else{
				sessao.setAttribute("collectionNegativadorRetornoMotivo", collectionNegativadorRetornoMotivo);
				sessao.setAttribute("nomeNegativador", nomeNegativador);
			}

		}
		
		return retorno;
        
    }

}
