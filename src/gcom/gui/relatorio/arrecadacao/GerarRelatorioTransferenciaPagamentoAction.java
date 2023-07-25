package gcom.gui.relatorio.arrecadacao;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.arrecadacao.RelatorioTransferenciaPagamento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;


/**
 * @author Raimundo Martins
 * @date 24/08/2011
 */
public class GerarRelatorioTransferenciaPagamentoAction extends ExibidorProcessamentoTarefaRelatorio{
	
	/**
	 * Action que gera o relatório de transferencia de pagamento
	 * 
	 * [UC 1217] Gerar Relatório Transferencia Pagamento
	 * 
	 * @author Raimundo Martins
	 * @date 24/08/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	private Fachada fachada = Fachada.getInstancia();

	private HttpSession sessao = null;

	private GerarRelatorioTranferenciaPagamentoActionForm form = null;

	private TarefaRelatorio relatorio = null;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		sessao = httpServletRequest.getSession(false);
		form = (GerarRelatorioTranferenciaPagamentoActionForm) actionForm;

		Arrecadador arrecadador = pesquisarArrecadador();
		boolean dataPeriodoCorreta = validaVerificaPeriodo();
		AvisoBancario avisoBancario = pesquisarAvisoBancario();
		ArrecadacaoForma arrecadacaoForma = pesquisarArrecadacaoForma();
		DebitoTipo debitoTipo = pesquisarDebitoTipo();
		DocumentoTipo documentoTipo = pesquisarDocumentoTipo();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if (dataPeriodoCorreta) {
			relatorio = new RelatorioTransferenciaPagamento((Usuario) sessao.getAttribute("usuarioLogado"));			
			relatorio.addParametro("periodoInicial", form.getPeriodoInicial());
			relatorio.addParametro("periodoFinal", form.getPeriodoFinal());

			if(arrecadador !=null){
				relatorio.addParametro("arrecadador", arrecadador);
			}
			if (avisoBancario != null) {
				relatorio.addParametro("avisoBancario", avisoBancario);
			}			
			if(arrecadacaoForma !=null){
				relatorio.addParametro("arrecadacaoForma", arrecadacaoForma);
			}
			if (tipoRelatorio  == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
			if(debitoTipo !=null){
				relatorio.addParametro("tipoDebito",debitoTipo);
			}
			if(documentoTipo !=null){
				relatorio.addParametro("tipoDocumento",documentoTipo);
			}
			
			relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
			
			retorno = processarExibicaoRelatorio(relatorio, Integer.parseInt(tipoRelatorio), httpServletRequest, httpServletResponse, actionMapping);
			
		} else {
			throw new ActionServletException("atencao.preencha.campos.obrigatorio");
		}

		return retorno;
	}

	// [FS0001] Verificar a existencia do Arrecadador
	private Arrecadador pesquisarArrecadador() {
		String idArrecadador = form.getArrecadadorCodigo();
		Arrecadador arrecadador = null;
		if (idArrecadador != null && !idArrecadador.trim().equals("")) {
			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.ID, idArrecadador));
			filtroArrecadador
					.adicionarCaminhoParaCarregamentoEntidade("cliente");

			Collection colecaoArrecadadores = fachada.pesquisar(
					filtroArrecadador, Arrecadador.class.getName());

			if (colecaoArrecadadores != null && !colecaoArrecadadores.isEmpty()) {
				arrecadador = (Arrecadador) Util
						.retonarObjetoDeColecao(colecaoArrecadadores);
			}
		}
		return arrecadador;
	}

	// [FS0002] Validar data
	// [FS0003] Verificar data

	private Boolean validaVerificaPeriodo() {
		String formato = "dd/MM/yyyy";
		boolean dataValidaIni = Util.dataInvalida(form
				.getPeriodoInicial(), formato);
		boolean dataValidaFin = Util.dataInvalida(
				form.getPeriodoFinal(), formato);

		if (dataValidaIni && dataValidaFin) {
			Date periodoInicial = Util.converteStringParaDate(form
					.getPeriodoInicial());
			Date periodoFinal = Util.converteStringParaDate(form
					.getPeriodoFinal());

			if (periodoInicial.getTime() <= periodoFinal.getTime()) {
				return true;
			} else {
				throw new ActionServletException(
						"atencao.data_inicio_maior_final");
			}
		}
		return false;
	}

	private AvisoBancario pesquisarAvisoBancario() {

		String idAvisoBancario = form.getIdAvisoBancario();
		AvisoBancario avisoBancario = null;
		if (idAvisoBancario != null && !idAvisoBancario.trim().equals("")) {
			FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
			filtroAvisoBancario
					.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADADOR);
			filtroAvisoBancario.adicionarParametro(new ParametroSimples(
					FiltroAvisoBancario.ID, idAvisoBancario));

			Collection colecaoAvisoBancario = fachada.pesquisar(
					filtroAvisoBancario, AvisoBancario.class.getName());

			if (colecaoAvisoBancario != null && !colecaoAvisoBancario.isEmpty()) {
				avisoBancario = (AvisoBancario) Util
						.retonarObjetoDeColecao(colecaoAvisoBancario);
			}
		}
		return avisoBancario;
	}

	private ArrecadacaoForma pesquisarArrecadacaoForma() {
		String idArrecadacaoForma = form.getIdFormaArrecadacao();
		ArrecadacaoForma arrecadacaoForma = null;

		if (idArrecadacaoForma != null
				&& !idArrecadacaoForma.equals("")
				&& !idArrecadacaoForma.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();

			filtroArrecadacaoForma.adicionarParametro(new ParametroSimples(
					FiltroArrecadacaoForma.CODIGO, idArrecadacaoForma));

			Collection colecaoArrecadacaoForma = fachada.pesquisar(
					filtroArrecadacaoForma, ArrecadacaoForma.class.getName());

			if (colecaoArrecadacaoForma != null
					&& !colecaoArrecadacaoForma.isEmpty()) {
				// O arrecadador foi encontrado
				arrecadacaoForma = (ArrecadacaoForma) Util
						.retonarObjetoDeColecao(colecaoArrecadacaoForma);
			}
		}
		return arrecadacaoForma;
	}
	
	private DocumentoTipo pesquisarDocumentoTipo() {
		String idDocumentoTipo = form.getIdTipoDocumento();
		DocumentoTipo documentoTipo = null;

		if (idDocumentoTipo != null
				&& !idDocumentoTipo.equals("")
				&& !idDocumentoTipo.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();

			filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
					FiltroDocumentoTipo.ID, idDocumentoTipo));

			Collection colecaoDocumentoTipo = fachada.pesquisar(
					filtroDocumentoTipo, DocumentoTipo.class.getName());

			if (colecaoDocumentoTipo != null
					&& !colecaoDocumentoTipo.isEmpty()) {
				// O tipo de debito foi encontrado
				documentoTipo= (DocumentoTipo) Util
						.retonarObjetoDeColecao(colecaoDocumentoTipo);
			}
		}
		return documentoTipo;
	}
	
	private DebitoTipo pesquisarDebitoTipo() {
		String idDebitoTipo = form.getIdTipoDebito();
		DebitoTipo debitoTipo = null;

		if (idDebitoTipo != null
				&& !idDebitoTipo.equals("")
				&& !idDebitoTipo.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

			filtroDebitoTipo.adicionarParametro(new ParametroSimples(
					FiltroDebitoTipo.ID, idDebitoTipo));

			Collection colecaoDebitoTipo = fachada.pesquisar(
					filtroDebitoTipo, DocumentoTipo.class.getName());

			if (colecaoDebitoTipo != null
					&& !colecaoDebitoTipo.isEmpty()) {
				// O tipo de debito foi encontrado
				debitoTipo= (DebitoTipo) Util
						.retonarObjetoDeColecao(colecaoDebitoTipo);
			}
		}
		return debitoTipo;
	}
	
	

}
