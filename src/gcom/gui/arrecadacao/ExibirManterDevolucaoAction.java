package gcom.gui.arrecadacao;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.FiltroDevolucao;
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
 * Description of the Class
 * 
 * @author Fernanda Karla
 * @created 07 de Março de 2006
 */
public class ExibirManterDevolucaoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterDevolucao");

		// Mudar isso quando implementar a parte de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoDevolucoes = null;

		if (sessao.getAttribute("colecaoImoveisDevolucoes") != null) {
			colecaoDevolucoes = (Collection) sessao
					.getAttribute("colecaoImoveisDevolucoes");
		} else if (sessao.getAttribute("colecaoClientesDevolucoes") != null) {
			colecaoDevolucoes = (Collection) sessao
					.getAttribute("colecaoClientesDevolucoes");
		} else if (sessao.getAttribute("colecaoAvisosBancariosDevolucoes") != null) {
			colecaoDevolucoes = (Collection) sessao
					.getAttribute("colecaoAvisosBancariosDevolucoes");
		}

		sessao.setAttribute("colecaoDevolucoes",colecaoDevolucoes);
		
		Collection devolucoes = null;
		//Parte da verificação do filtro
        FiltroDevolucao filtroDevolucao = new FiltroDevolucao();
        
		// Verifica se o filtro foi informado pela página de filtragem de devolucao
        if(httpServletRequest.getAttribute("filtroDevolucao") != null){
            filtroDevolucao = (FiltroDevolucao) httpServletRequest
                    .getAttribute("filtroDevolucao");
            
        }else if(sessao.getAttribute("filtroDevolucao")!= null){
        	filtroDevolucao = (FiltroDevolucao) sessao
            		.getAttribute("filtroDevolucao");
    	}else{
            //Se o limite de registros foi atingido, a página de filtragem é chamada
            retorno = actionMapping.findForward("exibirManterDevolucao");
        }
		
        if(retorno.getName().equalsIgnoreCase("exibirManterDevolucao")){
            //Seta a ordenação desejada do filtro
            filtroDevolucao.setCampoOrderBy(FiltroDevolucao.DATA_DEVOLUCAO);
            
            //Informa ao filtro para ele buscar objetos associados a Devolucao
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("avisoBancario.arrecadador");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.documentoTipo");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.conta");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAtual");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAnterior");
            filtroDevolucao
            	.adicionarCaminhoParaCarregamentoEntidade("imovel");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.conta");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.guiaPagamento.debitoTipo");
            filtroDevolucao
            	.adicionarCaminhoParaCarregamentoEntidade("cliente");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo");
            filtroDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel");

            // Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroDevolucao, Devolucao.class.getName());
			devolucoes = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			if (devolucoes == null || devolucoes.isEmpty()) {
				//Nenhuma Devolucao cadastrada
                throw new ActionServletException("atencao.pesquisa.nenhumresultado");
            }
            
            sessao.setAttribute("colecaoDevolucoes", devolucoes);
            
            sessao.setAttribute("filtroDevolucao", filtroDevolucao);
            sessao.setAttribute("telaManter","telaManter");
        }
		sessao.removeAttribute("colecaoImoveisDevolucoes");
		sessao.removeAttribute("colecaoClientesDevolucoes");
		sessao.removeAttribute("colecaoAvisosBancariosDevolucoes");
		
		sessao.setAttribute("tela","manterDevolucao");
		
		return retorno;
	}
}
