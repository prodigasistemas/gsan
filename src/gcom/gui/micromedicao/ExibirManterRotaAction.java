package gcom.gui.micromedicao;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de exibir manter a rota
 * @author Vivianne Sousa
 * @created 02/03/2006
 */

public class ExibirManterRotaAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("manterRota");
		HttpSession sessao = httpServletRequest.getSession(false);

        Collection collectionRota = null;

		// Parte da verificação do filtro
		FiltroRota filtroRota = null;

		// Verifica se o filtro foi informado pela página de filtragem de rota
		if (sessao.getAttribute("filtroRota") != null) {
			filtroRota = (FiltroRota) sessao
					.getAttribute("filtroRota");
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterRota"))) {
		
			filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
			filtroRota.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
			filtroRota.adicionarCaminhoParaCarregamentoEntidade("cobrancaGrupo");
			filtroRota.adicionarCaminhoParaCarregamentoEntidade("empresa");
			 
			filtroRota.setCampoOrderBy(FiltroRota.CODIGO_ROTA,FiltroRota.LOCALIDADE_ID,FiltroRota.SETOR_COMERCIAL_CODIGO);

			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroRota, Rota.class.getName());
			collectionRota = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			

			// [FS0003] Nenhum registro encontrado				
			if (collectionRota == null || collectionRota.isEmpty()) {
				// Nenhuma rota cadastrada
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (collectionRota.size()== 1 && identificadorAtualizar != null){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarRota");
				Rota rota = (Rota)collectionRota.iterator().next();
				sessao.setAttribute("idRegistroAtualizacao", new Integer (rota.getId()).toString());
			}else{
				sessao.setAttribute("collectionRota", collectionRota);
			}

		}

		sessao.removeAttribute("UseCase");
		return retorno;
    }

}
