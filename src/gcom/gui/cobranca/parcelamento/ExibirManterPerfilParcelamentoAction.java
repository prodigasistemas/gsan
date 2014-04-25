package gcom.gui.cobranca.parcelamento;

import gcom.cobranca.parcelamento.FiltroParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
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

/**
 * Action de exibir manter o Perfil de Parcelamento
 * @author Vivianne Sousa
 * @created 11/05/2006
 */

public class ExibirManterPerfilParcelamentoAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("manterPerfilParcelamento");
		HttpSession sessao = httpServletRequest.getSession(false);
		//Fachada fachada = Fachada.getInstancia();
        Collection collectionParcelamentoPerfil = null;

		// Parte da verificação do filtro
        FiltroParcelamentoPerfil filtroParcelamentoPerfil = null;

		// Verifica se o filtro foi informado pela página de filtragem de Perfil de Parcelamento
		if (sessao.getAttribute("filtroParcelamentoPerfil") != null) {
			filtroParcelamentoPerfil = (FiltroParcelamentoPerfil) sessao
					.getAttribute("filtroParcelamentoPerfil");
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterPerfilParcelamento"))) {
	
	    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("resolucaoDiretoria");
	    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelSituacaoTipo");
	    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
	    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("subcategoria");
	    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("categoria");
			
			filtroParcelamentoPerfil.setCampoOrderBy(FiltroParcelamentoPerfil.RESOLUCAO_DIRETORIA_NUMERO);

			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroParcelamentoPerfil, ParcelamentoPerfil.class.getName());
			collectionParcelamentoPerfil = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			

			// [FS0004] Nenhum registro encontrado				
			if (collectionParcelamentoPerfil == null || collectionParcelamentoPerfil.isEmpty()) {
				// Nenhuma Perfil de Parcelamento cadastrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (collectionParcelamentoPerfil.size()== 1 && identificadorAtualizar != null){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarPerfilParcelamento");
				ParcelamentoPerfil parcelamentoPerfil = (ParcelamentoPerfil)collectionParcelamentoPerfil.iterator().next();
				sessao.setAttribute("idRegistroAtualizacao", new Integer (parcelamentoPerfil.getId()).toString());
			}else{
				sessao.setAttribute("collectionParcelamentoPerfil", collectionParcelamentoPerfil);
			}

		}
		
		sessao.removeAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
		sessao.removeAttribute("collectionParcelamentoDescontoInatividade");
		sessao.removeAttribute("collectionParcelamentoDescontoAntiguidade");
		sessao.removeAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas");
		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeLinhaRemovidas");
		sessao.removeAttribute("collectionParcelamentoDescontoAntiguidadeLinhaRemovidas");
		sessao.removeAttribute("collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas");
		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeAVista");
		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas");
        return retorno;
        
    }

}
