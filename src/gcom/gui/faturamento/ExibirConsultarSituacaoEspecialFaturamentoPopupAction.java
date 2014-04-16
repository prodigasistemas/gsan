package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoHistorico;
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
public class ExibirConsultarSituacaoEspecialFaturamentoPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirConsultarSituacaoEspecialFaturamentoPopup");

		Fachada fachada = Fachada.getInstancia();
		
		ConsultarSituacaoEspecialFaturamentoPopupActionForm consultarSituacaoEspecialFaturamentoPopupActionForm = 
			(ConsultarSituacaoEspecialFaturamentoPopupActionForm) actionForm;
		

		String idFaturamentoSituacaoHistorico = httpServletRequest.getParameter("idFaturamentoSituacaoHistorico");
		
		if (idFaturamentoSituacaoHistorico != null){
			
			FiltroFaturamentoSituacaoHistorico filtroFaturamentoSituacaoHistorico = new FiltroFaturamentoSituacaoHistorico();
			filtroFaturamentoSituacaoHistorico.adicionarParametro(new ParametroSimples(FiltroFaturamentoSituacaoHistorico.ID, new Integer(idFaturamentoSituacaoHistorico)));
			filtroFaturamentoSituacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoTipo");
			filtroFaturamentoSituacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoMotivo");
			filtroFaturamentoSituacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoComandoInforma");
			filtroFaturamentoSituacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoComandoRetirada");

			Collection colecaoFaturamentoSituacaoHistorico = fachada.pesquisar(filtroFaturamentoSituacaoHistorico, FaturamentoSituacaoHistorico.class.getName());
			
			if (colecaoFaturamentoSituacaoHistorico != null && !colecaoFaturamentoSituacaoHistorico.isEmpty()) {

				FaturamentoSituacaoHistorico faturamentoSituacaoHistorico = (FaturamentoSituacaoHistorico) Util.retonarObjetoDeColecao(colecaoFaturamentoSituacaoHistorico);
				
				if (faturamentoSituacaoHistorico.getImovel() != null) {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setIdImovel(faturamentoSituacaoHistorico.getImovel().getId().toString());
				} else {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setIdImovel("");
				}

				if (faturamentoSituacaoHistorico.getFaturamentoSituacaoTipo() != null) {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setTipo(faturamentoSituacaoHistorico.getFaturamentoSituacaoTipo().getDescricao());
				} else {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setTipo("");
				}
				
				if (faturamentoSituacaoHistorico.getFaturamentoSituacaoMotivo() != null) {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setMotivo(faturamentoSituacaoHistorico.getFaturamentoSituacaoMotivo().getDescricao());
				} else {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setMotivo("");
				}
				
				if (faturamentoSituacaoHistorico.getNumeroConsumoAguaNaoMedido() != null) {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setConsumoFixoNaoMedido(faturamentoSituacaoHistorico.getNumeroConsumoAguaNaoMedido().toString());
				} else {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setConsumoFixoNaoMedido("");
				}
				
				if (faturamentoSituacaoHistorico.getNumeroConsumoAguaMedido() != null) {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setConsumoFixoMedido(faturamentoSituacaoHistorico.getNumeroConsumoAguaMedido().toString());
				} else {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setConsumoFixoMedido("");
				}
				
				if (faturamentoSituacaoHistorico.getNumeroVolumeEsgotoNaoMedido() != null) {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setVolumeFixoNaoMedido(faturamentoSituacaoHistorico.getNumeroVolumeEsgotoNaoMedido().toString());
				} else {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setVolumeFixoNaoMedido("");
				}
				
				if (faturamentoSituacaoHistorico.getNumeroVolumeEsgotoMedido() != null) {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setVolumeFixoMedido(faturamentoSituacaoHistorico.getNumeroVolumeEsgotoMedido().toString());
				} else {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setVolumeFixoMedido("");
				}
				
				if (faturamentoSituacaoHistorico.getAnoMesFaturamentoSituacaoInicio() != null) {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setMesAnoReferenciaFaturamentoInicial(Util.formatarAnoMesParaMesAno(faturamentoSituacaoHistorico.getAnoMesFaturamentoSituacaoInicio()));
				} else {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setMesAnoReferenciaFaturamentoInicial("");
				}
				
				if (faturamentoSituacaoHistorico.getAnoMesFaturamentoSituacaoFim() != null) {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setMesAnoReferenciaFaturamentoFinal(Util.formatarAnoMesParaMesAno(faturamentoSituacaoHistorico.getAnoMesFaturamentoSituacaoFim()));
				} else {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setMesAnoReferenciaFaturamentoFinal("");
				}
				
				if (faturamentoSituacaoHistorico.getAnoMesFaturamentoRetirada() != null) {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setMesAnoReferenciaRetirada(Util.formatarAnoMesParaMesAno(faturamentoSituacaoHistorico.getAnoMesFaturamentoRetirada()));
				} else {
					consultarSituacaoEspecialFaturamentoPopupActionForm.setMesAnoReferenciaRetirada("");
				}
				
				
				
				if (faturamentoSituacaoHistorico
						.getFaturamentoSituacaoComandoInforma() != null) {

				

						consultarSituacaoEspecialFaturamentoPopupActionForm
								.setObservacaoInforma(faturamentoSituacaoHistorico
										.getFaturamentoSituacaoComandoInforma()
										.getObservacao());

					
				}
				
				if (faturamentoSituacaoHistorico.getFaturamentoSituacaoComandoRetirada() != null) {
					consultarSituacaoEspecialFaturamentoPopupActionForm
							.setObservacaoRetira(faturamentoSituacaoHistorico
									.getFaturamentoSituacaoComandoRetirada().getObservacao());
				} 
				

				if (faturamentoSituacaoHistorico.getObservacaoInforma() != null 
						&& !faturamentoSituacaoHistorico.getObservacaoInforma().equals("")) {
						
					consultarSituacaoEspecialFaturamentoPopupActionForm
								.setObservacaoInforma(faturamentoSituacaoHistorico.getObservacaoInforma() );
					} 

				if (faturamentoSituacaoHistorico.getObservacaoRetira() != null) {
						consultarSituacaoEspecialFaturamentoPopupActionForm
								.setObservacaoRetira(faturamentoSituacaoHistorico
										.getObservacaoRetira());
					} 

				}
				
			
			
		}
				
		return retorno;

	}

}
