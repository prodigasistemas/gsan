package gcom.gui.cobranca.spcserasa;

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
 * Permite a inclusão de um novo contrato do negativador.
 * 
 * @author Yara Taciane de Souza
 * @date 18/12/2007
 */
public class ExibirInserirContratoNegativadorAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("inserirContratoNegativador");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		InserirContratoNegativadorActionForm inserirContratoNegativadorActionForm = (InserirContratoNegativadorActionForm) actionForm;
		if (httpServletRequest.getParameter("limpaSessao") != null
				&& !httpServletRequest.getParameter("limpaSessao").equals("")) {
			
			inserirContratoNegativadorActionForm.setNumeroContrato("");
			inserirContratoNegativadorActionForm.setDescricaoEmailEnvioArquivo("");
			inserirContratoNegativadorActionForm.setCodigoConvenio("");	
			inserirContratoNegativadorActionForm.setValorContrato("");	
			inserirContratoNegativadorActionForm.setValorTarifaInclusao("");	
			inserirContratoNegativadorActionForm.setNumeroPrazoInclusao("");	
			inserirContratoNegativadorActionForm.setDataContratoInicio("");	
			inserirContratoNegativadorActionForm.setDataContratoFim("");	
		
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
		
			

		return retorno;
	}
}
