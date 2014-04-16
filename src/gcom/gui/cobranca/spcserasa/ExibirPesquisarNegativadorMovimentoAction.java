package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0674] Pesquisar Movimento Negativador
 * 
 * @author Yara Taciane
 * @date 27/12/2007
 * 
 */
public class ExibirPesquisarNegativadorMovimentoAction extends GcomAction {

	/**
	 * 
	 * [UC0438] Este caso de uso efetua pesquisa de Movimento do Negativador
	 * 
	 * 
	 * @author Yara Taciane
	 * @date 03/08/2006
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

		HttpSession sessao = httpServletRequest.getSession(false);	
		
		Fachada fachada = Fachada.getInstancia();
		
		
		PesquisarNegativadorMovimentoActionForm form = (PesquisarNegativadorMovimentoActionForm) actionForm;
		
		form.setIdNegativador("");
		form.setTipoMovimento("");
		form.setNumeroSequencialEnvio("");
		form.setDataProcessamentoInicial("");
		form.setDataProcessamentoFinal("");
		
		ActionForward retorno = actionMapping.findForward("negativadorMovimentoPesquisar");
		
		     
		Collection colecaoNegativador = (Collection) sessao.getAttribute("colecaoNegativador");

		if (colecaoNegativador == null) {

			FiltroNegativador filtroNegativador = new FiltroNegativador();			
			filtroNegativador.setCampoOrderBy(FiltroNegativador.CLIENTE);			
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroNegativador.setConsultaSemLimites(true);

			colecaoNegativador = fachada.pesquisar(filtroNegativador,
					Negativador.class.getName());

			if (colecaoNegativador == null || colecaoNegativador.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"NEGATIVADOR");
			} else {
				sessao.setAttribute("colecaoNegativador", colecaoNegativador);
			}
		}
		
		
		
		
		
		
		
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaNegativadorMovimento") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaNegativadorMovimento",httpServletRequest.getParameter("caminhoRetornoTelaPesquisaNegativadorMovimento"));
		}
		
		return retorno;
	}
	
}
