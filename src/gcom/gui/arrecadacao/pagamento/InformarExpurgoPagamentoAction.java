package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.pagamento.Pagamento;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * [UC0247] Consultar Pagamentos
 * 
 * @author Sávio Luiz
 * @date 04/01/2007
 */
public class InformarExpurgoPagamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		

		Collection colecaoExpurgado = (Collection) sessao
				.getAttribute("colecaoExpurgado");
		Collection colecaoNaoExpurgado = (Collection) sessao
				.getAttribute("colecaoNaoExpurgado");

		Collection colecaoPagamento = new ArrayList();

		if (colecaoExpurgado != null && !colecaoExpurgado.isEmpty()) {
			Iterator iteExpurgado = colecaoExpurgado.iterator();
			while (iteExpurgado.hasNext()) {
				Pagamento pagamento = (Pagamento) iteExpurgado.next();
				String indicadorExpurgadoID = httpServletRequest.getParameter("indicadorExpurgado"+pagamento.getId());
				pagamento
						.setIndicadorExpurgado(new Short(indicadorExpurgadoID));
				colecaoPagamento.add(pagamento);
			}
		}

		if (colecaoNaoExpurgado != null && !colecaoNaoExpurgado.isEmpty()) {
			Iterator iteNaoExpurgado = colecaoNaoExpurgado.iterator();
			while (iteNaoExpurgado.hasNext()) {
				Pagamento pagamento = (Pagamento) iteNaoExpurgado.next();
				String indicadorExpurgadoID = httpServletRequest.getParameter("indicadorExpurgado"+pagamento.getId());
				pagamento
						.setIndicadorExpurgado(new Short(indicadorExpurgadoID));
				colecaoPagamento.add(pagamento);
			}
		}

		fachada.atualizarSituacaoExpurgoPagamento(colecaoPagamento);
		
		sessao.removeAttribute("colecaoExpurgado");
		sessao.removeAttribute("colecaoNaoExpurgado");


		// montando página de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Situação de Expurgado do pagamento atualizado com sucesso.",
				"Informar outra Situação de Expurgado do Pagamento",
				"exibirInformarExpurgoPagamentoAction.do?limpar=sim");

		return retorno;
	}
}
