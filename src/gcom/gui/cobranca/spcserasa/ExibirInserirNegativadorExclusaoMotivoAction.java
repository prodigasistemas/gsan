package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.FiltroCobrancaDebitoSituacao;
import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import gcom.spcserasa.FiltroNegativador;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite a inclusão de um novo motivo de exclusão do Negativador.
 * 
 * @author Yara Taciane de Souza
 * @date 02/01/2008
 */
public class ExibirInserirNegativadorExclusaoMotivoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("inserirNegativadorExclusaoMotivo");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		InserirNegativadorExclusaoMotivoActionForm form = (InserirNegativadorExclusaoMotivoActionForm) actionForm;
		
		
		if (httpServletRequest.getParameter("limpaSessao") != null
				&& !httpServletRequest.getParameter("limpaSessao").equals("")) {
			
			form.setIdNegativador("-1");
			form.setDescricaoExclusaoMotivo("");
			form.setCodigoMotivo("");
			form.setIdCobrancaDebitoSituacao("-1");

		
		}
		Collection colecaoNegativador = (Collection) sessao.getAttribute("colecaoNegativador");

		if (colecaoNegativador == null) {

			FiltroNegativador filtroNegativador = new FiltroNegativador();			
			filtroNegativador.setCampoOrderBy(FiltroNegativador.CLIENTE);			
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroNegativador.adicionarParametro(
					new ParametroSimples(FiltroNegativador.INDICADOR_USO, 
					ConstantesSistema.INDICADOR_USO_ATIVO));	
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
		
		
		
		Collection colecaoCobrancaDebitoSituacao = (Collection) sessao.getAttribute("colecaoCobrancaDebitoSituacao");

		if (colecaoCobrancaDebitoSituacao == null) {

			FiltroCobrancaDebitoSituacao filtroCobrancaDebitoSituacao = new FiltroCobrancaDebitoSituacao();	
			
			filtroCobrancaDebitoSituacao.setConsultaSemLimites(true);

			colecaoCobrancaDebitoSituacao = fachada.pesquisar(filtroCobrancaDebitoSituacao,
					CobrancaDebitoSituacao.class.getName());

			if (colecaoCobrancaDebitoSituacao == null || colecaoCobrancaDebitoSituacao.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"COBRANCA DEBITO SITUACAO");
			} else {
				sessao.setAttribute("colecaoCobrancaDebitoSituacao", colecaoCobrancaDebitoSituacao);
			}
		}
		
		
			

		return retorno;
	}
}
