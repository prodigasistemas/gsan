package gcom.gui.relatorio.cobranca;

import gcom.gui.cadastro.imovel.ImovelCurvaAbcDebitosActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioGerarCurvaAbcDebitos;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarCurvaAbcDebitosAction  extends ExibidorProcessamentoTarefaRelatorio  {

	/**
	 * @author Ivan Sérgio
	 * @created 07/08/2007
	 * 
	 * <<Descrição do método>>
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
		
		HttpSession sessao = httpServletRequest.getSession(false);

		ImovelCurvaAbcDebitosActionForm imovelCurvaAbcDebitosActionForm = (ImovelCurvaAbcDebitosActionForm) actionForm;

		RelatorioGerarCurvaAbcDebitos relatorioGerarCurvaAbcDebitos = new RelatorioGerarCurvaAbcDebitos(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorioGerarCurvaAbcDebitos.addParametro("classificacao",
				imovelCurvaAbcDebitosActionForm.getClassificacao());
		relatorioGerarCurvaAbcDebitos.addParametro("referenciaCobrancaInicial",
				imovelCurvaAbcDebitosActionForm.getReferenciaCobrancaInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("referenciaCobrancaFinal",
				imovelCurvaAbcDebitosActionForm.getReferenciaCobrancaFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("indicadorImovelMedicaoIndividualizada",
				imovelCurvaAbcDebitosActionForm.getIndicadorImovelMedicaoIndividualizada());
		relatorioGerarCurvaAbcDebitos.addParametro("indicadorImovelParalizacaoFaturamentoCobranca",
				imovelCurvaAbcDebitosActionForm.getIndicadorImovelParalizacaoFaturamentoCobranca());
		relatorioGerarCurvaAbcDebitos.addParametro("idMunicipio",
				imovelCurvaAbcDebitosActionForm.getIdMunicipio());
		relatorioGerarCurvaAbcDebitos.addParametro("nomeMunicipio",
				imovelCurvaAbcDebitosActionForm.getNomeMunicipio());
		relatorioGerarCurvaAbcDebitos.addParametro("gerenciaRegional",
				imovelCurvaAbcDebitosActionForm.getGerenciaRegional());
		relatorioGerarCurvaAbcDebitos.addParametro("idLocalidadeInicial",
				imovelCurvaAbcDebitosActionForm.getIdLocalidadeInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("idLocalidadeFinal",
				imovelCurvaAbcDebitosActionForm.getIdLocalidadeFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("idSetorComercialInicial",
				imovelCurvaAbcDebitosActionForm.getIdSetorComercialInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("idSetorComercialFinal",
				imovelCurvaAbcDebitosActionForm.getIdSetorComercialFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("nomeLocalidadeInicial",
				imovelCurvaAbcDebitosActionForm.getNomeLocalidadeInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("nomeLocalidadeFinal",
				imovelCurvaAbcDebitosActionForm.getNomeLocalidadeFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("codigoSetorComercialInicial",
				imovelCurvaAbcDebitosActionForm.getCodigoSetorComercialInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("codigoSetorComercialFinal",
				imovelCurvaAbcDebitosActionForm.getCodigoSetorComercialFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("nomeSetorComercialInicial",
				imovelCurvaAbcDebitosActionForm.getNomeSetorComercialInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("nomeSetorComercialFinal",
				imovelCurvaAbcDebitosActionForm.getNomeSetorComercialFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("idMunicipio", 
				imovelCurvaAbcDebitosActionForm.getIdMunicipio());
		relatorioGerarCurvaAbcDebitos.addParametro("nomeMunicipio", 
				imovelCurvaAbcDebitosActionForm.getNomeMunicipio());
		
		relatorioGerarCurvaAbcDebitos.addParametro("situacaoLigacaoAgua",
				imovelCurvaAbcDebitosActionForm.getSituacaoLigacaoAgua());
		relatorioGerarCurvaAbcDebitos.addParametro("situacaoLigacaoEsgoto",
				imovelCurvaAbcDebitosActionForm.getSituacaoLigacaoEsgoto());
		relatorioGerarCurvaAbcDebitos.addParametro("intervaloMesesCortadoSuprimidoInicial",
				imovelCurvaAbcDebitosActionForm.getIntervaloMesesCortadoSuprimidoInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("intervaloMesesCortadoSuprimidoFinal",
				imovelCurvaAbcDebitosActionForm.getIntervaloMesesCortadoSuprimidoFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("intervaloConsumoMinimoFixadoEsgotoInicial",
				imovelCurvaAbcDebitosActionForm.getIntervaloConsumoMinimoFixadoEsgotoInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("intervaloConsumoMinimoFixadoEsgotoFinal",
				imovelCurvaAbcDebitosActionForm.getIntervaloConsumoMinimoFixadoEsgotoFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("indicadorMedicao",
				imovelCurvaAbcDebitosActionForm.getIndicadorMedicao());
		relatorioGerarCurvaAbcDebitos.addParametro("idTipoMedicao",
				imovelCurvaAbcDebitosActionForm.getIdTipoMedicao());
		
		relatorioGerarCurvaAbcDebitos.addParametro("idPerfilImovel",
				imovelCurvaAbcDebitosActionForm.getIdPerfilImovel());
		relatorioGerarCurvaAbcDebitos.addParametro("idTipoCategoria",
				imovelCurvaAbcDebitosActionForm.getIdTipoCategoria());
		relatorioGerarCurvaAbcDebitos.addParametro("categoria",
				imovelCurvaAbcDebitosActionForm.getCategoria());
		relatorioGerarCurvaAbcDebitos.addParametro("idSubCategoria",
				imovelCurvaAbcDebitosActionForm.getIdSubCategoria());
		
		relatorioGerarCurvaAbcDebitos.addParametro("valorMinimoDebito",
				imovelCurvaAbcDebitosActionForm.getValorMinimoDebito());
		relatorioGerarCurvaAbcDebitos.addParametro("intervaloQuantidadeDocumentosInicial",
				imovelCurvaAbcDebitosActionForm.getIntervaloQuantidadeDocumentosInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("intervaloQuantidadeDocumentosFinal",
				imovelCurvaAbcDebitosActionForm.getIntervaloQuantidadeDocumentosFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("indicadorPagamentosNaoClassificados",
				imovelCurvaAbcDebitosActionForm.getIndicadorPagamentosNaoClassificados());
		
		
		relatorioGerarCurvaAbcDebitos.addParametro("colecaoGerenciasRegionais",
				sessao.getAttribute("colecaoGerenciasRegionais"));
		relatorioGerarCurvaAbcDebitos.addParametro("collectionsLigacaoAguaSituacao",
				sessao.getAttribute("collectionsLigacaoAguaSituacao"));
		relatorioGerarCurvaAbcDebitos.addParametro("collectionLigacaoEsgotoSituacao",
				sessao.getAttribute("collectionLigacaoEsgotoSituacao"));
		
		
		relatorioGerarCurvaAbcDebitos.addParametro("collectionImovelPerfil",
				sessao.getAttribute("collectionImovelPerfil"));
		relatorioGerarCurvaAbcDebitos.addParametro("collectionCategoriaTipo",
				sessao.getAttribute("collectionCategoriaTipo"));
		relatorioGerarCurvaAbcDebitos.addParametro("collectionImovelCategoria",
				sessao.getAttribute("collectionImovelCategoria"));
		relatorioGerarCurvaAbcDebitos.addParametro("collectionImovelSubcategoria",
				sessao.getAttribute("collectionImovelSubcategoria"));
		
		relatorioGerarCurvaAbcDebitos.addParametro("localidadeInicial",
				sessao.getAttribute("localidadeInicial"));
		relatorioGerarCurvaAbcDebitos.addParametro("localidadeFinal",
				sessao.getAttribute("localidadeFinal"));
		relatorioGerarCurvaAbcDebitos.addParametro("setorComercialInicial",
				sessao.getAttribute("setorComercialInicial"));
		relatorioGerarCurvaAbcDebitos.addParametro("setorComercialFinal",
				sessao.getAttribute("setorComercialFinal"));
		
		relatorioGerarCurvaAbcDebitos.addParametro("idMunicipio",
				sessao.getAttribute("idMunicipio"));
		relatorioGerarCurvaAbcDebitos.addParametro("nomeMunicipio",
				sessao.getAttribute("nomeMunicipio"));
		
        // Flag para tela de sucesso apos a tela de espera de processamento de relatorio
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		
		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorioGerarCurvaAbcDebitos.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		
		retorno = processarExibicaoRelatorio(relatorioGerarCurvaAbcDebitos,
				tipoRelatorio, httpServletRequest,
				httpServletResponse, actionMapping);
		
		// devolve o mapeamento contido na variável retorno
		return retorno;
	}
}
