package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.bean.ImovelFaltandoSituacaoLeituraHelper;
import gcom.micromedicao.bean.SituacaoLeituraHelper;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * 
 * [UC0629] Consultar Arquivo Texto para Leitura
 * 	
 * 	[FS0011 - Verificar Leituras];
 * 
 * @author Hugo Amorim
 * @date 19/08/2010
 *
 */
public class ExibirConsultarSituacaoLeituraPopupAction extends GcomAction {
	/**
	 * 
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
				.findForward("exibirConsultarSituacaoLeituraPopupAction");

		//HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarArquivoTextoLeituraActionForm form = 
			(ConsultarArquivoTextoLeituraActionForm) actionForm;
		
		Integer idRota =  null;
		
		if(httpServletRequest.getParameter("idRota")!=null){
			idRota = new Integer(httpServletRequest.getParameter("idRota"));
		}
		
		SituacaoLeituraHelper helper = 
			Fachada.getInstancia().pesquisarSituacaoLeitura(
					Util.formatarMesAnoComBarraParaAnoMes(form.getMesAno()),
					new Integer(form.getGrupoFaturamentoID()),
					idRota);
		
		// Caso haja algum imóvel faltando, pesquisamos para apresentarmos na tela
		if ( helper != null && ( !helper.getDiferencaMedidosEnvRec().equals( "0" ) || !helper.getDiferencanaoMedidosEnvRec().equals( "0" ) ) ){
			Collection<ImovelFaltandoSituacaoLeituraHelper> colImoveisFaltandoHelper= 
				Fachada.getInstancia().pesquisarImoveisFaltandoSituacaoLeitura( idRota, Util.formatarMesAnoComBarraParaAnoMes(form.getMesAno()) );
			
			httpServletRequest.setAttribute( "colImoveisFaltandoHelper", colImoveisFaltandoHelper );
		}  
		
		if(helper!=null){
			httpServletRequest.setAttribute("helper", helper);
		}
		
		return retorno;
	}

}
