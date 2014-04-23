package gcom.gui.atendimentopublico;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 22/01/2007
 */
public class ExibirSelecionarBancoAction extends GcomAction {
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirSelecionarBancoAction");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String idConta = httpServletRequest.getParameter("idConta");

		sessao.setAttribute("idConta", idConta);

		Collection idsContaEP = new ArrayList();
		if (httpServletRequest.getParameter("idConta") != null
				&& !httpServletRequest.getParameter("idConta").equals("")) {
			idsContaEP.add(new Integer(""
					+ httpServletRequest.getParameter("idConta")));
		}

		Collection<EmitirContaHelper> colecaoEmitirContaHelper = fachada
				.emitir2ViaContas(idsContaEP, false,ConstantesSistema.NAO);

		EmitirContaHelper emitirContaHelper = colecaoEmitirContaHelper
				.iterator().next();
		
		/*
		 * Caso o documento selecionado seja uma ficha de compensação, valor maior que mil reais,
		 * o sistema irá chamar a tela inicial do banco.
		 */
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		if (sistemaParametro.getValorContaFichaComp() != null){
			
			if (emitirContaHelper.getValorConta().compareTo(sistemaParametro.getValorContaFichaComp()) <= 0){
				
				String representacaoNumericaCodBarraFormatada = emitirContaHelper
				.getRepresentacaoNumericaCodBarraFormatada();

				// Cortar a representacaoNumericaCodBarraFormatada separando de 12 em
				// 12, mas tirando os espacos e traços
			
				String cAux1 = representacaoNumericaCodBarraFormatada.substring(0, 10)
						+ representacaoNumericaCodBarraFormatada.substring(12, 13);
			
				String cAux2 = representacaoNumericaCodBarraFormatada.substring(14, 25)
						+ representacaoNumericaCodBarraFormatada.substring(26, 27);
			
				String cAux3 = representacaoNumericaCodBarraFormatada.substring(28, 39)
						+ representacaoNumericaCodBarraFormatada.substring(40, 41);
			
				String cAux4 = representacaoNumericaCodBarraFormatada.substring(42, 53)
						+ representacaoNumericaCodBarraFormatada.substring(54, 55);
				//		
				// String cAux2 =
				// String cAux3 =
				// String cAux4 =
				// codigo de barras passado pela 2. via de conta
				String codigo = cAux1 + cAux2 + cAux3 + cAux4;
			
				httpServletRequest.setAttribute("codigo", codigo);
				httpServletRequest.setAttribute("fichaCompensacao", "2");
			}
			else{
				
				httpServletRequest.setAttribute("fichaCompensacao", "1");
			}
		}
		else{
			
			String representacaoNumericaCodBarraFormatada = emitirContaHelper
			.getRepresentacaoNumericaCodBarraFormatada();

			// Cortar a representacaoNumericaCodBarraFormatada separando de 12 em
			// 12, mas tirando os espacos e traços
		
			String cAux1 = representacaoNumericaCodBarraFormatada.substring(0, 10)
					+ representacaoNumericaCodBarraFormatada.substring(12, 13);
		
			String cAux2 = representacaoNumericaCodBarraFormatada.substring(14, 25)
					+ representacaoNumericaCodBarraFormatada.substring(26, 27);
		
			String cAux3 = representacaoNumericaCodBarraFormatada.substring(28, 39)
					+ representacaoNumericaCodBarraFormatada.substring(40, 41);
		
			String cAux4 = representacaoNumericaCodBarraFormatada.substring(42, 53)
					+ representacaoNumericaCodBarraFormatada.substring(54, 55);
			//		
			// String cAux2 =
			// String cAux3 =
			// String cAux4 =
			// codigo de barras passado pela 2. via de conta
			String codigo = cAux1 + cAux2 + cAux3 + cAux4;
		
			httpServletRequest.setAttribute("codigo", codigo);
			httpServletRequest.setAttribute("fichaCompensacao", "2");
		}
		

		return retorno;

	}
}
