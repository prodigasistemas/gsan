package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroRaDadosAgenciaReguladora;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0538] Filtrar RAs na Agencia Reguladora
 *
 * @author Kássia Albuquerque
 * @date 02/05/2007
 */

public class FiltrarRaDadosAgenciaReguladoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
			ActionForward retorno = actionMapping.findForward("exibirListarRaDadosAgenciaReguladora");
			
			FiltrarRaDadosAgenciaReguladoraActionForm form =(FiltrarRaDadosAgenciaReguladoraActionForm) actionForm;
			
			HttpSession sessao = httpServletRequest.getSession(false);		
			
			String numeroRA = form.getNumeroRa();
			String motivoReclamacao = form.getMotivoReclamacao();
			String motivoEncerramentoAtendimento = form.getMotivoEncerramentoAtendimento();
			String indicadorSituacaoAgencia = form.getIndicadorSituacaoAgencia();
			String indicadorSituacaoRA = form.getIndicadorSituacaoRA();
			String periodoReclamacaoInicio = form.getPeriodoReclamacaoInicio();
			String periodoReclamacaoFim = form.getPeriodoReclamacaoFim();
			String periodoRetornoInicio = form.getPeriodoRetornoInicio();
			String periodoRetornoFim = form.getPeriodoRetornoFim();
			String motivoRetornoAgencia = form.getMotivoRetornoAgencia();
			
			FiltroRaDadosAgenciaReguladora filtroRaDadosAgenciaReguladora = new FiltroRaDadosAgenciaReguladora();
			
			filtroRaDadosAgenciaReguladora.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
			filtroRaDadosAgenciaReguladora.adicionarCaminhoParaCarregamentoEntidade("agenciaReguladoraMotReclamacao");
			filtroRaDadosAgenciaReguladora.adicionarCaminhoParaCarregamentoEntidade("atendimentoMotivoEncerramento");
			filtroRaDadosAgenciaReguladora.adicionarCaminhoParaCarregamentoEntidade("agenciaReguladoraMotRetorno");
			
			
			boolean peloMenosUmParametroInformado = false;
			
			
			// Número do RA
			
			if (numeroRA != null && !numeroRA.equals("")){
				
				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples
										(FiltroRaDadosAgenciaReguladora.REGISTRO_ATENDIMENTO_ID, numeroRA));
			}
			
			
			//	Motivo Reclamação da Agência
			
			if (motivoReclamacao != null && !motivoReclamacao.trim().equalsIgnoreCase(String.valueOf
					(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples
										(FiltroRaDadosAgenciaReguladora.MOTIVO_RECLAMACAO_ID, motivoReclamacao));
			}
			
			
			//	Motivo Encerramento do Atendimento
			
			if (motivoEncerramentoAtendimento != null && !motivoEncerramentoAtendimento.trim().equalsIgnoreCase(String.valueOf
					(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples
										(FiltroRaDadosAgenciaReguladora.MOTIVO_ENCERRAMENTO_ID, motivoEncerramentoAtendimento));
			}
			
			
			//	Indicador da Situacao da Agencia Reguladora
			
			if (indicadorSituacaoAgencia != null && !indicadorSituacaoAgencia.equalsIgnoreCase("")&&
					!indicadorSituacaoAgencia.trim().equalsIgnoreCase("" + ConstantesSistema.SITUACAO_AGENCIA_TODOS)) {
				
				peloMenosUmParametroInformado = true;
					
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples(FiltroRaDadosAgenciaReguladora.INDICADOR_SITUACAO_AGENCIA,
							indicadorSituacaoAgencia));
				
			}
			
			
			//	Indicador da Situacao do RA antes da Agencia Reguladora
			
			if (indicadorSituacaoRA != null && !indicadorSituacaoRA.equalsIgnoreCase("")
					&& !indicadorSituacaoRA.trim().equalsIgnoreCase("" + ConstantesSistema.SITUACAO_RA_AGENCIA_TODOS)) {
				
				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples(FiltroRaDadosAgenciaReguladora.INDICADOR_SITUACAO_RA,
							indicadorSituacaoRA));
			}
			
			
			//  Período da Reclamação
			
			if (periodoReclamacaoInicio != null && !periodoReclamacaoInicio.trim().equals("") 
									&& periodoReclamacaoFim != null && !periodoReclamacaoFim.trim().equals("")) {
				
				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new Intervalo(FiltroRaDadosAgenciaReguladora.DATA_RECLAMACAO, 
							Util.converteStringParaDate(periodoReclamacaoInicio),Util.converteStringParaDate(periodoReclamacaoFim)));
				
			}
			
			
			//  Período do Retorno
			
			if (periodoRetornoInicio != null && !periodoRetornoInicio.trim().equals("") 
									&& periodoRetornoFim != null && !periodoRetornoFim.trim().equals("")) {
				
				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new Intervalo(FiltroRaDadosAgenciaReguladora.DATA_RETORNO, 
							Util.converteStringParaDate(periodoRetornoInicio),Util.converteStringParaDate(periodoRetornoFim)));
				
			}	
			
			
			//	Motivo do Retorno para Agência Reguladora
			
			if (motivoRetornoAgencia != null && !motivoRetornoAgencia.trim().equalsIgnoreCase(String.valueOf
					(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples
										(FiltroRaDadosAgenciaReguladora.MOTIVO_RETORNO_ID, motivoRetornoAgencia));
			}
			
			
			
			if (!peloMenosUmParametroInformado) {
				throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			}
			
			sessao.setAttribute("filtroRaDadosAgenciaReguladora", filtroRaDadosAgenciaReguladora);
			
			return retorno;
			}
}
