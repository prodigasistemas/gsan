package gcom.gui.financeiro;

import gcom.fachada.Fachada;
import gcom.financeiro.FiltroLancamentoOrigem;
import gcom.financeiro.lancamento.LancamentoOrigem;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Gerar integração para contabilidade.
 *
 * @author Flávio Leonardo
 * @date 13/06/2007
 */
public class ExibirGerarIntegracaoContabilidadeCaernAction extends GcomAction {
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
			ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//seta o retorno para a página de gerar integração para a contabilidade
		ActionForward retorno = actionMapping.findForward("exibirGerarIntegracaoContabilidadeCaern");

		//cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();

		//recupera a sessão do usuário
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Pesquisa as origens de lançamentos cadastradas no sistema    
		FiltroLancamentoOrigem filtroLancamentoOrigem = new FiltroLancamentoOrigem();
		Collection colecaoLancamentoOrigem = fachada.pesquisar(filtroLancamentoOrigem, LancamentoOrigem.class.getName());

		//[FS0001 - Verificar existência de dados]
		//caso não exista nenhuma origem de lançamento cadastrada no sistema 
		//levanta a exceção para o usuário
		if(colecaoLancamentoOrigem == null || colecaoLancamentoOrigem.isEmpty()){
			throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,"Lançamento Origem");
		}else{
			sessao.setAttribute("colecaoLancamentoOrigem", colecaoLancamentoOrigem);
		}
		return retorno;
	}
}
