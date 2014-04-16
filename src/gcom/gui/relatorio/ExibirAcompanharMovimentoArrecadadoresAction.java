package gcom.gui.relatorio;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite filtrar os movimentos dos arrecadadores que serão exibidos no relatório
 * 
 * @author Rafael Corrêa
 * @since 02/04/2007
 */
public class ExibirAcompanharMovimentoArrecadadoresAction extends GcomAction {

	/**
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAcompanharMovimentoArrecadadores");
		
		AcompanharMovimentoArrecadadoresActionForm acompanharMovimentoArrecadadoresActionForm = (AcompanharMovimentoArrecadadoresActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
		filtroArrecadacaoForma.setCampoOrderBy(FiltroArrecadacaoForma.DESCRICAO);
		
		Collection colecaoArrecadacaoForma = fachada.pesquisar(
				filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
		
		sessao.setAttribute("colecaoArrecadacaoForma", colecaoArrecadacaoForma);
		
		String idArrecadador = acompanharMovimentoArrecadadoresActionForm.getIdArrecadador();
		
		if (idArrecadador != null && !idArrecadador.trim().equals("")) {
			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.ID, idArrecadador));
			filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			
			Collection colecaoArrecadadores = fachada.pesquisar(filtroArrecadador, Arrecadador.class.getName());
			
			if (colecaoArrecadadores != null && !colecaoArrecadadores.isEmpty()) {
				Arrecadador arrecadador = (Arrecadador) Util.retonarObjetoDeColecao(colecaoArrecadadores);
				
				acompanharMovimentoArrecadadoresActionForm.setIdArrecadador(arrecadador.getId().toString());
				acompanharMovimentoArrecadadoresActionForm.setNomeArrecadador(arrecadador.getCliente().getNome());
				
			} else {
				httpServletRequest.setAttribute("idArrecadadorNaoEncontrado", true);
				acompanharMovimentoArrecadadoresActionForm.setIdArrecadador("");
				acompanharMovimentoArrecadadoresActionForm.setNomeArrecadador("Arrecadador Inexistente");
			}
		}

		return retorno;

	}

}
