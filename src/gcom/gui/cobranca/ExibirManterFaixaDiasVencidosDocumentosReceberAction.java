package gcom.gui.cobranca;

import gcom.cobranca.DocumentosReceberFaixaDiasVencidos;
import gcom.cobranca.FiltroDocumentosReceberFaixaDiasVencidos;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterFaixaDiasVencidosDocumentosReceberAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("exibirManterFaixaDiasVencidosDocumentosReceberAction");
		
		//HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera os parâmetros da sessão para ser efetuada a pesquisa
		FiltroDocumentosReceberFaixaDiasVencidos filtroDocumentosReceberFaixaDiasVencidos = new FiltroDocumentosReceberFaixaDiasVencidos();
		filtroDocumentosReceberFaixaDiasVencidos.setCampoOrderBy(FiltroDocumentosReceberFaixaDiasVencidos.VALOR_MENOR_FAIXA);
		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroDocumentosReceberFaixaDiasVencidos, DocumentosReceberFaixaDiasVencidos.class.getName());
		
		Collection colecaoDocumentosReceberFaixaDiasVencidos = (Collection) resultado
				.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		//Verifica se a coleção retornada pela pesquisa é nula, em caso
		// afirmativo comunica ao usuário que não existe nenhuma equipe
		// cadastrada para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condições seta o retorno para o
		// ExibirAtualizarEquipeAction, se não atender manda a
		// coleção pelo request para ser recuperado e exibido pelo jsp.
		if (colecaoDocumentosReceberFaixaDiasVencidos != null
				&& !colecaoDocumentosReceberFaixaDiasVencidos.isEmpty()) {

			httpServletRequest.setAttribute(
					"colecaoDocumentosReceberFaixaDiasVencidos",
					colecaoDocumentosReceberFaixaDiasVencidos);

		} else {
			// Caso a pesquisa não retorne nenhum objeto comunica ao usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		return retorno;
		
	}

}
