package gcom.gui.cobranca;

import gcom.cobranca.FiltroCobrancaAcaoAtividadeCronograma;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarCobrancaCronogramaAction extends GcomAction {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("retornarFiltroCobrancaCronograma");

		CobrancaActionForm cobrancaActionForm = (CobrancaActionForm) actionForm;
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		// Variaveis
		String indicadorAtualizar = httpServletRequest
		.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar == null) {
			cobrancaActionForm.setIndicadorAtualizar("2");
		} else {
			cobrancaActionForm
					.setIndicadorAtualizar(indicadorAtualizar);
		}
		
		String idCobrancaGrupo;
		String mesAno;
		if(httpServletRequest.getAttribute("idCobrancaGrupo") == null){
			idCobrancaGrupo = (String) cobrancaActionForm.getIdGrupoCobrancaFiltro();
			mesAno = (String) cobrancaActionForm.getMesAnoFiltro();			
		}else{
			idCobrancaGrupo = (String)httpServletRequest.getAttribute("idCobrancaGrupo");
			mesAno = (String) httpServletRequest.getAttribute("mesAno");
		}
		
		if((cobrancaActionForm.getIdGrupoCobrancaFiltro() == null || cobrancaActionForm.getIdGrupoCobrancaFiltro().trim().equals(ConstantesSistema.NUMERO_NAO_INFORMADO+""))
				&& (cobrancaActionForm.getMesAnoFiltro() == null || cobrancaActionForm.getMesAnoFiltro().trim().equals(""))){
			throw new ActionServletException(
            "atencao.filtro.nenhum_parametro_informado");
		}
		 
		FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividade = fachada
				.filtrarCobrancaCronograma(idCobrancaGrupo, mesAno);
		filtroCobrancaAcaoAtividade.setCampoOrderBy(FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_GRUPO,
				FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_GRUPO_MES_ANO);
		

		filtroCobrancaAcaoAtividade
				.setCampoDistinct("objeto."+FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_GRUPO_CRONOGRAMA_MES);

		sessao.setAttribute("filtroCobrancaAcaoAtividade",
				filtroCobrancaAcaoAtividade);

		return retorno;
	}

}
