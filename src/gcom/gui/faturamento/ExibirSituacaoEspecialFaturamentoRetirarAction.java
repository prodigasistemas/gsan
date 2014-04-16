package gcom.gui.faturamento;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action para pre-exibição dos dados da situação especial de faturamento que
 * serão retirados
 * 
 * @author Sávio Luiz
 * @date 10/03/2006
 */
public class ExibirSituacaoEspecialFaturamentoRetirarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("situacaoEspecialRetirar");

		SituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm = (SituacaoEspecialFaturamentoActionForm) actionForm;

		// Pegar Quantidade de Imoveis que serao atualizados
		String COM = situacaoEspecialFaturamentoActionForm
				.getQuantidadeImoveisCOMSituacaoEspecialFaturamento();
		String SEM = situacaoEspecialFaturamentoActionForm
				.getQuantidadeImoveisSEMSituacaoEspecialFaturamento();
		String quantidadeDeImoveisAtualizados = Integer.toString(Integer
				.parseInt(COM)
				+ Integer.parseInt(SEM));

		if (quantidadeDeImoveisAtualizados.equals("0"))
			throw new ActionServletException(
					"atencao.imovel.sem.situacao.especial.faturamento", null,
					"");

		situacaoEspecialFaturamentoActionForm
				.setQuantidadeDeImoveis(quantidadeDeImoveisAtualizados);

		return retorno;
	}

}
