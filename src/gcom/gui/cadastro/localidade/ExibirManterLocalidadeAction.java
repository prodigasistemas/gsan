package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterLocalidadeAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("manterLocalidade");
		HttpSession sessao = httpServletRequest.getSession(false);

        Collection colecaoLocalidade = null;

		// Parte da verificação do filtro
        FiltroLocalidade filtroLocalidade = null;

		// Verifica se o filtro foi informado pela página de filtragem de Localidade
		if (sessao.getAttribute("filtroLocalidade") != null) {
			filtroLocalidade = (FiltroLocalidade) sessao
					.getAttribute("filtroLocalidade");
			//qd volta da tela do atualizar estava perdendo 
			//a coleção de caminho para carregamento da entidade
			if (filtroLocalidade.getColecaoCaminhosParaCarregamentoEntidades() == null ||
					filtroLocalidade.getColecaoCaminhosParaCarregamentoEntidades().isEmpty()){
				filtroLocalidade
					.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
				filtroLocalidade
					.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
			}
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterLocalidade"))) {
	

			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroLocalidade, Localidade.class.getName());
			colecaoLocalidade = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			

			// [FS0004] Nenhum registro encontrado				
			if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
				// Nenhuma Localidade cadastrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (colecaoLocalidade.size()== 1 && identificadorAtualizar != null){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarLocalidade");
				Localidade localidade = (Localidade)colecaoLocalidade.iterator().next();
				sessao.setAttribute("idRegistroAtualizar", new Integer (localidade.getId()).toString());
			}else{
				sessao.setAttribute("colecaoLocalidade", colecaoLocalidade);
			}

		}
		sessao.removeAttribute("tipoPesquisaRetorno");//
        return retorno;
        
    }

}
