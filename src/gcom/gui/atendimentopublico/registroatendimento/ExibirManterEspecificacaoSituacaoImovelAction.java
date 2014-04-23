package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao;
import gcom.atendimentopublico.registroatendimento.FiltroEspecificacaoImovelSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0404] - Manter Especificação da Situação do Imóvel
 * 
 * @author Rafael Francisco Pinto
 * @date 08/11/2006
 */

public class ExibirManterEspecificacaoSituacaoImovelAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("manterEspecificacaoSituacaoImovel");

    	//Inicializando Variaveis
		HttpSession sessao = this.getSessao(httpServletRequest);

        Collection colecaoEspecificacaoSituacaoImovel = null;

		// Parte da verificação do filtro
        FiltroEspecificacaoImovelSituacao filtro = null;

		// Verifica se o filtro foi informado pela página de filtragem de Localidade
		if (sessao.getAttribute("filtroEspecificacaoSituacaoImovel") != null) {
			filtro = (FiltroEspecificacaoImovelSituacao) sessao.getAttribute("filtroEspecificacaoSituacaoImovel");
		}

		Map resultado = 
			controlarPaginacao(httpServletRequest, retorno,filtro, EspecificacaoImovelSituacao.class.getName());
		
		colecaoEspecificacaoSituacaoImovel = (Collection) resultado.get("colecaoRetorno");

		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		if (colecaoEspecificacaoSituacaoImovel == null || colecaoEspecificacaoSituacaoImovel.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
		
		if (colecaoEspecificacaoSituacaoImovel.size()== 1 && identificadorAtualizar != null){

			// caso o resultado do filtro só retorne um registro 
			// e o check box Atualizar estiver selecionado
			//o sistema não exibe a tela de manter, exibe a de atualizar 
			retorno = actionMapping.findForward("atualizarEspecificacaoSituacaoImovel");
			
			EspecificacaoImovelSituacao especificacaoImovelSituacao = 
				(EspecificacaoImovelSituacao) colecaoEspecificacaoSituacaoImovel.iterator().next();
			
			sessao.setAttribute("idRegistroAtualizar",especificacaoImovelSituacao.getId().toString());
			
		}else{
			sessao.setAttribute("colecaoEspecificacaoSituacaoImovel", colecaoEspecificacaoSituacaoImovel);
		}
		
		sessao.removeAttribute("tipoPesquisaRetorno");
		sessao.setAttribute("ultimaAlteracao",new Date());
		
        return retorno;
		
	} 
	
}
