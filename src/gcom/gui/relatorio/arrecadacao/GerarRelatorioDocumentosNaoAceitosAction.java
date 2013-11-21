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
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.arrecadacao.RelatorioDocumentoNaoAceitos;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * @author Raimundo Martins
 * @date 17/08/2011
 */

public class GerarRelatorioDocumentosNaoAceitosAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * Action que gera o relatório de documentos não aceitos
	 * 
	 * [UC 1215] Gerar Relatório de Documentos não Aceitos
	 * 
	 * @author Raimundo Martins
	 * @date 18/08/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	private Fachada fachada = Fachada.getInstancia();

	private HttpSession sessao = null;

	private GerarRelatorioDocumentosNaoAceitosActionForm form = null;

	private TarefaRelatorio relatorio = null;

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		sessao = httpServletRequest.getSession(false);
		form = (GerarRelatorioDocumentosNaoAceitosActionForm) actionForm;

		Arrecadador arrecadador = pesquisarArrecadador();
		boolean dataPeriodoCorreta = validaVerificaPeriodo();
		AvisoBancario avisoBancario = pesquisarAvisoBancario();
		ArrecadacaoForma arrecadacaoForma = pesquisarArrecadacaoForma();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if (dataPeriodoCorreta) {
			relatorio = new RelatorioDocumentoNaoAceitos((Usuario) sessao.getAttribute("usuarioLogado"));

			relatorio.addParametro("arrecadador", arrecadador);
			relatorio.addParametro("periodoInicial", form.getPeriodoInicial());
			relatorio.addParametro("periodoFinal", form.getPeriodoFinal());

			if (avisoBancario != null) {
				relatorio.addParametro("avisoBancario", avisoBancario);
			}
			if (form.getMovimentoArrecadadorCodigo() != null && 
				!form.getMovimentoArrecadadorCodigo().equals("") && 
				!form.getMovimentoArrecadadorCodigo().equals(""
					+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				relatorio.addParametro("movimentoArrecadadorCodigo", form.getMovimentoArrecadadorCodigo());
			}
			if(arrecadacaoForma !=null){
				relatorio.addParametro("arrecadacaoForma", arrecadacaoForma);
			}
			if (tipoRelatorio  == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
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
		boolean dataValidaIni = Util.validarDataInvalida(form
				.getPeriodoInicial(), formato);
		boolean dataValidaFin = Util.validarDataInvalida(
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
}
