package gcom.gui.relatorio.cobranca;

import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.fachada.Fachada;
import gcom.gui.cobranca.ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.RelatorioEmitirProtocoloDocumentoCobranca;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório 
 * [UC0580]Emitir Protocolo de Documento de Cobrança do Cronogrma
 * 
 * @author Ana Maria
 * @date 15/05/2007
 */
public class GerarRelatorioEmitirProtocoloDocumentoCobrancaAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		// cria a variável de retorno
		ActionForward retorno = null;

		Fachada fachada =  Fachada.getInstancia();
		
		 HttpSession sessao = httpServletRequest.getSession(false);    
		
		// cria uma instância da classe do relatório
		RelatorioEmitirProtocoloDocumentoCobranca relatorioEmitirProtocoloDocumentoCobranca = new RelatorioEmitirProtocoloDocumentoCobranca((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));		
		
		Collection protocoloDocumentoCobranca = null;

		if(sessao.getAttribute("cobrancaAcaoAtividadeCronograma")!= null){
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma)sessao.getAttribute("cobrancaAcaoAtividadeCronograma");
			Integer idCobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma.getId();
			protocoloDocumentoCobranca = fachada.pesquisarProtocoloDocumentoCobrancaCronograma(idCobrancaAcaoAtividadeCronograma);
			String primeiroTitulo = "COBRANÇA: "+ Util.formatarAnoMesParaMesAno(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes().getAnoMesReferencia())+"    "+
									"DATA DA REALIZAÇÃO: "+ Util.formatarData(cobrancaAcaoAtividadeCronograma.getRealizacao())+"    "+
									"HORA DA REALIZAÇÃO: "+ Util.formatarHoraSemData(cobrancaAcaoAtividadeCronograma.getRealizacao());
			relatorioEmitirProtocoloDocumentoCobranca.addParametro("grupo", cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes().getCobrancaGrupo().getDescricao());
			relatorioEmitirProtocoloDocumentoCobranca.addParametro("primeiroTitulo",primeiroTitulo);
			relatorioEmitirProtocoloDocumentoCobranca.addParametro("acaoCobranca", "RESUMO DAS ORDENS DE COBRANÇA EMITIDAS "+cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaAcao().getDescricaoCobrancaAcao());
			relatorioEmitirProtocoloDocumentoCobranca.addParametro("R0000","R0580");
		}else{
			ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm form = (ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm)actionForm;
			Integer idCobrancaAcaoAtividadeComand = new Integer(form.getIdCobrancaAcaoAtividadeComando());
			protocoloDocumentoCobranca = fachada.pesquisarProtocoloDocumentoCobrancaEventual(idCobrancaAcaoAtividadeComand);
			relatorioEmitirProtocoloDocumentoCobranca.addParametro("grupo", null);
			String primeiroTitulo = "DATA DA REALIZAÇÃO: "+ form.getDataRealizacao()+"    "+
									"HORA DA REALIZAÇÃO: "+ form.getHoraRealizacao();
			relatorioEmitirProtocoloDocumentoCobranca.addParametro("primeiroTitulo",primeiroTitulo);
			relatorioEmitirProtocoloDocumentoCobranca.addParametro("acaoCobranca", "RESUMO DAS ORDENS DE COBRANÇA EMITIDAS "+form.getAcaoCobranca()+ " - EVENTUAL");
			relatorioEmitirProtocoloDocumentoCobranca.addParametro("R0000","R0581");
		}
		
		relatorioEmitirProtocoloDocumentoCobranca.addParametro("protocoloDocumentoCobranca",protocoloDocumentoCobranca);
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioEmitirProtocoloDocumentoCobranca.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		try {
			
			retorno = processarExibicaoRelatorio(relatorioEmitirProtocoloDocumentoCobranca,
					tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
			
		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
