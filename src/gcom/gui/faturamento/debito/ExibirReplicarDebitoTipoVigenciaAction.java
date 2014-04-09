package gcom.gui.faturamento.debito;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0985] Inserir tipo de débito com vigência
 * 
 * Este caso de uso permite a inclusão de um novo debito tipo vigencia 
 *
 * @author Josenildo Neves
 * @date 22/02/2010
 */
public class ExibirReplicarDebitoTipoVigenciaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("replicarDebitoTipoVigencia");

		ReplicarDebitoTipoVigenciaActionForm replicarDebitoTipoVigenciaActionForm = (ReplicarDebitoTipoVigenciaActionForm) actionForm;

		retorno = this.getDebitoTipoVigencia(replicarDebitoTipoVigenciaActionForm,httpServletRequest, retorno);
		
		return retorno;
		
	}
	
	/**
	 * [SB0002] – Replicar os serviços existentes para uma nova vigência e valor
	 * 
	 * Este método exibe a coleção de Débito Tipo Vigência que tem a última data de vigência. 
	 *
	 * @author Josenildo Neves
	 * @date 22/02/2010
	 */
	private ActionForward getDebitoTipoVigencia(ReplicarDebitoTipoVigenciaActionForm form, HttpServletRequest httpServletRequest, ActionForward retorno) {
		
		Collection<DebitoTipoVigencia> colecaoDebitoTipoVigencia = null;
		
		// 1º Passo - Pegar o total de registros através de um count da
		// consulta que aparecerá na tela
		Integer totalRegistros = Fachada.getInstancia().pesquisarDebitoTipoVigenciaUltimaVigenciaTotal();

		// 2º Passo - Chamar a função de Paginação passando o total de
		// registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);
		
		if (httpServletRequest.getParameter("page.offset") != null) {
			colecaoDebitoTipoVigencia = Fachada.getInstancia().pesquisarDebitoTipoVigenciaUltimaVigencia(Integer.parseInt(httpServletRequest.getParameter("page.offset")) - 1);
		
		}else{
			colecaoDebitoTipoVigencia = Fachada.getInstancia().pesquisarDebitoTipoVigenciaUltimaVigencia(0);
		
		}
		
		if (colecaoDebitoTipoVigencia != null && !colecaoDebitoTipoVigencia.isEmpty()) {
			
			form.setMensagem(true);
			form.setCollDebitoTipoVigencia(colecaoDebitoTipoVigencia);			
			
		} else {
			form.setMensagem(false);
			form.setCollDebitoTipoVigencia(null);
		}
		
		return retorno;
	}
	
}
