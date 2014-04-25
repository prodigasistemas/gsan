package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.FiltroArrecadadorContrato;
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
 * [UC0507] MANTER ARRECADADOR
 * 
 * @author Marcio Roberto	
 * @date 08/02/2007
 */

public class ExibirManterContratoArrecadadorAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("manterContratoArrecadador");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
//		Fachada fachada = Fachada.getInstancia();
		
		Collection<ArrecadadorContrato> colecaoContratoArrecadador = null;

		// Parte da verificação do filtro
        FiltroArrecadadorContrato  filtroArrecadadorContrato = (FiltroArrecadadorContrato) sessao
		.getAttribute("filtroArrecadadorContrato");
        			   
        
		// Verifica se o filtro foi informado pela página de filtragem 
		if (sessao.getAttribute("filtroArrecadadorContrato") != null) {
			filtroArrecadadorContrato = (FiltroArrecadadorContrato) sessao.getAttribute("filtroArrecadadorContrato");
            filtroArrecadadorContrato.adicionarCaminhoParaCarregamentoEntidade("arrecadador.cliente");
            filtroArrecadadorContrato.adicionarCaminhoParaCarregamentoEntidade("cliente");
            filtroArrecadadorContrato.adicionarCaminhoParaCarregamentoEntidade("contaBancariaDepositoArrecadacao");
		}
		
		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterContratoArrecadador"))) {

			Map resultado = controlarPaginacao(httpServletRequest, retorno,	filtroArrecadadorContrato, ArrecadadorContrato.class.getName());
			colecaoContratoArrecadador = (Collection<ArrecadadorContrato>) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			// [FS0002] Nenhum registro encontrado				
			if (colecaoContratoArrecadador == null || colecaoContratoArrecadador.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (colecaoContratoArrecadador.size()== 1 && identificadorAtualizar != null && !identificadorAtualizar.equals("")){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("exibirAtualizarContratoArrecadador");
				ArrecadadorContrato arrecadadorContrato = (ArrecadadorContrato) colecaoContratoArrecadador.iterator().next();
				sessao.setAttribute("menu" , "sim");
				sessao.setAttribute("idRegistroAtualizar", arrecadadorContrato);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarContratoArrecadadorAction.do");
			}else{
				sessao.setAttribute("menu" , "sim");
				sessao.setAttribute("colecaoContratoArrecadador", colecaoContratoArrecadador);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterContratoArrecadadorAction.do");
			}

		}
		sessao.removeAttribute("tipoPesquisaRetorno");
		
		return retorno;
		
		
	} 
	
}
