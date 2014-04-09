package gcom.gui.batch;

import java.util.Collection;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de inserir processo
 * faturamento
 * 
 * @author Rodrigo Silveira
 * @created 11/08/2006
 */
public class ExibirInserirProcessoFaturamentoComandadoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("inserirProcessoFaturamentoComandado");

		Fachada fachada = Fachada.getInstancia();

		// 1º Passo - Pegar o total de registros através de um count da
		// consulta que aparecerá na tela
		Integer totalRegistros = fachada
				.pesquisarFaturamentoAtividadeCronogramaComandadasNaoRealizadasCount();
		if (totalRegistros.intValue() <= 0 || totalRegistros == null) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		
		// 2º Passo - Chamar a função de Paginação passando o total de
		// registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);

		// 3º Passo - Obter a coleção da consulta que aparecerá na tela
		// passando o numero de paginas
		// da pesquisa que está no request
		Collection<FaturamentoAtividadeCronograma> colecaoFaturamentoAtividadeCronograma = fachada
				.pesquisarFaturamentoAtividadeCronogramaComandadasNaoRealizadas((Integer) httpServletRequest
						.getAttribute("numeroPaginasPesquisa"));

		httpServletRequest.setAttribute(
				"colecaoFaturamentoAtividadeCronograma",
				colecaoFaturamentoAtividadeCronograma);

		return retorno;
	}

}
