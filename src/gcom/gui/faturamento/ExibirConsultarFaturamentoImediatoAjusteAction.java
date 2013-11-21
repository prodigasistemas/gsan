package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarFaturamentoImediatoAjusteAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("exibirConsultarFaturamentoImediatoAjuste");
		
		//HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera os parâmetros da sessão para ser efetuada a pesquisa
		
		FaturamentoImediatoAjusteHelper helper = (FaturamentoImediatoAjusteHelper)httpServletRequest.getAttribute("helper");
		
		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		Integer qtdImoveis = fachada.contarFaturamentoImediatoAjuste(helper);
	
		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		retorno =  controlarPaginacao(httpServletRequest, retorno, qtdImoveis);
		int indice = (Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa");

		// cria a collecao de Faturamento Imediato Ajuste
		Collection colecaoFaturamentoImediatoAjuste =  (Collection) fachada.pesquisarFaturamentoImediatoAjuste(helper, indice);
		
		//Verifica se a coleção retornada pela pesquisa é nula, em caso
		// afirmativo comunica ao usuário que não existe nenhuma equipe
		// cadastrada para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condições seta o retorno para o
		// ExibirAtualizarEquipeAction, se não atender manda a
		// coleção pelo request para ser recuperado e exibido pelo jsp.
		if (colecaoFaturamentoImediatoAjuste != null
				&& !colecaoFaturamentoImediatoAjuste.isEmpty()) {

			httpServletRequest.setAttribute(
					"colecaoFaturamentoImediatoAjuste", colecaoFaturamentoImediatoAjuste);

		} else {
			// Caso a pesquisa não retorne nenhum objeto comunica ao usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		return retorno;
		
	}

}
