package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0391] Inserir Valor de Cobrança de Serviço
 * 
 * Este caso de uso permite a inclusão de um novo valor de cobrança de serviço 
 *
 * @author Leonardo Regis
 * @date 28/09/2006
 */
public class ExibirReplicarValorCobrancaServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("replicarValorCobrancaServico");

		ReplicarValorCobrancaServicoActionForm replicarCobrancaServicoActionForm = (ReplicarValorCobrancaServicoActionForm) actionForm;

		retorno = this.getServicoCobracaValor(replicarCobrancaServicoActionForm,httpServletRequest, retorno);
		
		return retorno;
		
	}
	
	/**
	 * [SB0002] – Replicar os serviços existentes para uma nova vigência e valor
	 * 
	 * Este método exibe a coleção de Valor Serviço Cobrança que tem a última data de vigência. 
	 *
	 * @author Josenildo Neves
	 * @date 04/02/2010
	 */
	private ActionForward getServicoCobracaValor(ReplicarValorCobrancaServicoActionForm form, HttpServletRequest httpServletRequest, ActionForward retorno) {
		
		Collection<ServicoCobrancaValor> colecaoServicoCobrancaValor = null;
		
		// 1º Passo - Pegar o total de registros através de um count da
		// consulta que aparecerá na tela
		Integer totalRegistros = Fachada.getInstancia().pesquisarServicoCobrancaValorUltimaVigenciaTotal();

		// 2º Passo - Chamar a função de Paginação passando o total de
		// registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);

		colecaoServicoCobrancaValor = Fachada.getInstancia().pesquisarServicoCobrancaValorUltimaVigencia(
				(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));

		if (colecaoServicoCobrancaValor != null && !colecaoServicoCobrancaValor.isEmpty()) {
			
			form.setMensagem(true);
			form.setCollServicoCobrancaValor(colecaoServicoCobrancaValor);			
			
		} else {
			
			form.setMensagem(false);
			form.setCollServicoCobrancaValor(null);
		}
		
		return retorno;
		
	}
	
	
}
