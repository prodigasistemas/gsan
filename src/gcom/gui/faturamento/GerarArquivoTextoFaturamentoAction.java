package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class GerarArquivoTextoFaturamentoAction extends GcomAction {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		GerarArquivoTextoFaturamentoActionForm form = (GerarArquivoTextoFaturamentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		String mes = form.getMesAno().substring(0, 2);
		String ano = form.getMesAno().substring(3, 7);

		String anoMes = ano + mes;

		fachada.chamarGerarArquivoTextoFaturamento(new Integer(anoMes), form.getIdCliente(), (Collection) sessao.getAttribute("colecaoCliente"));

		// montando página de sucesso
		montarPaginaSucesso(httpServletRequest, "Gerar Arquivo Texto do Faturamento", "Voltar", "/exibirGerarArquivoTextoFaturamentoAction.do");

		return retorno;

	}
}
