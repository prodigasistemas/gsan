package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioComandoDocumentoCobranca;
import gcom.relatorio.cobranca.RelatorioNotificacaoDebito;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioComandoDocumentoCobrancaAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno
		ActionForward retorno = null;

		String idCobrancaAcaoAtividadeCronograma = httpServletRequest
				.getParameter("idCobrancaAcaoAtividadeCronograma");
		
		String tipoEndRelatorio = httpServletRequest
			.getParameter("tipoEndRelatorio");

		Integer idCobrancaAcaoCronograma = null;

		if (idCobrancaAcaoAtividadeCronograma != null
				&& !idCobrancaAcaoAtividadeCronograma.trim().equals("")) {
			idCobrancaAcaoCronograma = new Integer(
					idCobrancaAcaoAtividadeCronograma);
		}
		
		

		String idCobrancaAcaoAtividadeComando = httpServletRequest
				.getParameter("idCobrancaAcaoAtividadeComando");

		Integer idCobrancaAcaoComando = null;

		if (idCobrancaAcaoAtividadeComando != null
				&& !idCobrancaAcaoAtividadeComando.trim().equals("")) {
			idCobrancaAcaoComando = new Integer(idCobrancaAcaoAtividadeComando);
		}

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		TarefaRelatorio relatorio = null;
		
		Fachada fachada = Fachada.getInstancia();

		CobrancaAcao cobrancaAcao = fachada
			.pesquisarAcaoCobrancaParaRelatorio(idCobrancaAcaoComando,idCobrancaAcaoCronograma);

		DocumentoTipo documentoTipo = fachada
			.pesquisarTipoAcaoCobrancaParaRelatorio(idCobrancaAcaoComando,idCobrancaAcaoCronograma);

		if (documentoTipo != null && (documentoTipo.getId().intValue() == DocumentoTipo.AVISO_CORTE
				|| documentoTipo.getId().intValue() == DocumentoTipo.CORTE_ADMINISTRATIVO)) {

			relatorio = new RelatorioNotificacaoDebito(
					(Usuario) (httpServletRequest.getSession(false))
							.getAttribute("usuarioLogado"));

		} else {
			relatorio = new RelatorioComandoDocumentoCobranca(
					(Usuario) (httpServletRequest.getSession(false))
							.getAttribute("usuarioLogado"));
		}
		
		relatorio.addParametro("tipoEndRelatorio", tipoEndRelatorio);
		relatorio.addParametro("idCobrancaAcaoCronograma",
				idCobrancaAcaoCronograma);
		
		relatorio.addParametro("idCobrancaAcaoComando", idCobrancaAcaoComando);
		
		relatorio.addParametro("cobrancaAcao", cobrancaAcao);

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		
		/*
		 * 
		 * Altera��o feita para gerar avisos de corte. N�o estavam 
		 * sendo gerados pois faltava esse par�metro, que indica a qtd de documentos
		 * que v�o ser gerados por p�gina.
		 */
		relatorio.addParametro("quantidadeRelatorios", "2");
		
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}
