package gcom.gui.atendimentopublico.ordemservico;
import gcom.atendimentopublico.ordemservico.FiltroOSPriorizacaoTipo;
import gcom.atendimentopublico.ordemservico.OSPriorizacaoTipo;
import gcom.atendimentopublico.ordemservico.OSProgramacaoCalibragem;
import gcom.cadastro.endereco.FiltroOSProgramaCalibragem;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que atualiza a calibragem
 * 
 * @author Thúlio Araújo
 * @created 20/06/2011
 */
public class ExibirInformarCalibragemAction extends GcomAction {
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
		ActionForward retorno = actionMapping.findForward("exibirInformarCalibragem");
		
		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String idPriorizacaoTipo = "";
		if ( httpServletRequest.getParameter("priorizacaoTipo") != null ) {
			idPriorizacaoTipo = (String)httpServletRequest.getParameter("priorizacaoTipo");
		}

		FiltroOSPriorizacaoTipo filtroOSPriorizacaoTipo = new FiltroOSPriorizacaoTipo();
		Collection colecaoPriorizacaoTipo = fachada.pesquisar(filtroOSPriorizacaoTipo,OSPriorizacaoTipo.class.getName());
		
		FiltroOSProgramaCalibragem filtroOSProgramaCalibragem = new FiltroOSProgramaCalibragem();
		if (idPriorizacaoTipo != ""){
			filtroOSProgramaCalibragem.adicionarParametro( new ParametroSimples(FiltroOSProgramaCalibragem.PRIORIZACAO_TIPO_ID, idPriorizacaoTipo));
		}else{
			filtroOSProgramaCalibragem.adicionarParametro( new ParametroSimples(FiltroOSProgramaCalibragem.PRIORIZACAO_TIPO_ID, 1));
		}
		
		Collection colecaoProgramaCalibragem = fachada.pesquisar(filtroOSProgramaCalibragem, OSProgramacaoCalibragem.class.getName());
		sessao.setAttribute("colecaoProgramaCalibragem",colecaoProgramaCalibragem);
		sessao.setAttribute("colecaoPriorizacaoTipo",colecaoPriorizacaoTipo);
				
		return retorno;
	}
}
