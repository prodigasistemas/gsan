package gcom.gui.atendimentopublico.registroatendimento;

import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 05/09/2006
 */
public class ResultadoConsultarProgramacaoAbastecimentoManutencaoAction extends
		GcomAction {
	/**
	 * Este caso de uso permite a programaçao de abastecimento e manutencao de
	 * uma determinada área de bairro
	 * 
	 * [UC0440] Consultar Programação de Abastecimento e Manutenção
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 05/09/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("programacaoAbastecimentoManutencaoResultadoDetalhado");

		HttpSession sessao = httpServletRequest.getSession(false);

		// Recebe a Colecao

		Collection colecaoProgramacaoAbastecimento = (Collection) sessao
				.getAttribute("colecaoProgramacaoAbastecimento");

		//Collection colecaoProgramacaoManutencao = (Collection) sessao
				//.getAttribute("colecaoProgramacaoManutencao");

		if (colecaoProgramacaoAbastecimento != null
				&& !colecaoProgramacaoAbastecimento.isEmpty()) {

			//if(dataFim.after(dataInicio));
			
			// String dataInicioAbastecimento;
			//
			// String dataFimAbastecimento;
			//
			// String dia;
			//
			// dataInicioAbastecimento =
			// abastecimentoProgramacao.getDataInicio()
			// .toString();
			//
			// dataFimAbastecimento = abastecimentoProgramacao.getDataFim()
			// .toString();
			//
			// if (dataInicioAbastecimento.equals(dataFimAbastecimento)) {
			//
			// dia = dataInicioAbastecimento.substring(7, 9);
			// }
		}
		return retorno;
	}
}
