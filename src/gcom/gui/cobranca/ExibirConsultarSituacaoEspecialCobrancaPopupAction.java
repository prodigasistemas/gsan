package gcom.gui.cobranca;

import gcom.cobranca.CobrancaSituacaoHistorico;
import gcom.cobranca.FiltroCobrancaSituacaoHistorico;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroFaturamentoSituacaoHistorico;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Rafael Corrêa
 * @created 22/09/2008
 */
public class ExibirConsultarSituacaoEspecialCobrancaPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirConsultarSituacaoEspecialCobrancaPopup");

		Fachada fachada = Fachada.getInstancia();
		
		ConsultarSituacaoEspecialCobrancaPopupActionForm consultarSituacaoEspecialCobrancaPopupActionForm = 
			(ConsultarSituacaoEspecialCobrancaPopupActionForm) actionForm;
		

		String idCobrancaSituacaoHistorico = httpServletRequest.getParameter("idCobrancaSituacaoHistorico");
		
		if (idCobrancaSituacaoHistorico != null){
			
			FiltroCobrancaSituacaoHistorico filtroCobrancaSituacaoHistorico = new FiltroCobrancaSituacaoHistorico();
			filtroCobrancaSituacaoHistorico.adicionarParametro(new ParametroSimples(FiltroFaturamentoSituacaoHistorico.ID, new Integer(idCobrancaSituacaoHistorico)));
			filtroCobrancaSituacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacaoTipo");
			filtroCobrancaSituacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacaoMotivo");

			Collection colecaoCobrancaSituacaoHistorico = fachada.pesquisar(filtroCobrancaSituacaoHistorico, CobrancaSituacaoHistorico.class.getName());
			
			if (colecaoCobrancaSituacaoHistorico != null && !colecaoCobrancaSituacaoHistorico.isEmpty()) {

				CobrancaSituacaoHistorico cobrancaSituacaoHistorico = (CobrancaSituacaoHistorico) Util.retonarObjetoDeColecao(colecaoCobrancaSituacaoHistorico);
				
				if (cobrancaSituacaoHistorico.getImovel() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setIdImovel(cobrancaSituacaoHistorico.getImovel().getId().toString());
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setIdImovel("");
				}

				if (cobrancaSituacaoHistorico.getCobrancaSituacaoTipo() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setTipo(cobrancaSituacaoHistorico.getCobrancaSituacaoTipo().getDescricao());
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setTipo("");
				}
				
				if (cobrancaSituacaoHistorico.getCobrancaSituacaoMotivo() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMotivo(cobrancaSituacaoHistorico.getCobrancaSituacaoMotivo().getDescricao());
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMotivo("");
				}
				
				if (cobrancaSituacaoHistorico.getAnoMesCobrancaSituacaoInicio() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMesAnoReferenciaCobrancaInicial(Util.formatarAnoMesParaMesAno(cobrancaSituacaoHistorico.getAnoMesCobrancaSituacaoInicio()));
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMesAnoReferenciaCobrancaInicial("");
				}
				
				if (cobrancaSituacaoHistorico.getAnoMesCobrancaSituacaoFim() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMesAnoReferenciaCobrancaFinal(Util.formatarAnoMesParaMesAno(cobrancaSituacaoHistorico.getAnoMesCobrancaSituacaoFim()));
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMesAnoReferenciaCobrancaFinal("");
				}
				
				if (cobrancaSituacaoHistorico.getAnoMesCobrancaRetirada() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMesAnoReferenciaRetirada(Util.formatarAnoMesParaMesAno(cobrancaSituacaoHistorico.getAnoMesCobrancaRetirada()));
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMesAnoReferenciaRetirada("");
				}
				
				if (cobrancaSituacaoHistorico.getObservacaoInforma() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setObservacaoInforma(cobrancaSituacaoHistorico.getObservacaoInforma());
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setObservacaoInforma("");
				}
				
				if (cobrancaSituacaoHistorico.getObservacaoRetira() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setObservacaoRetira(cobrancaSituacaoHistorico.getObservacaoRetira());
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setObservacaoRetira("");
				}
				
			}
			
		}
				
		return retorno;

	}

}
