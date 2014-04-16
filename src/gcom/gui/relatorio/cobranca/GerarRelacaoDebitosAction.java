package gcom.gui.relatorio.cobranca;

import gcom.gui.cadastro.imovel.ImovelOutrosCriteriosActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioGerarRelacaoDebitos;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelacaoDebitosAction  extends ExibidorProcessamentoTarefaRelatorio  {

	/**
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

		ImovelOutrosCriteriosActionForm imovelOutrosCriteriosActionForm = (ImovelOutrosCriteriosActionForm) actionForm;
		
		String indicadorCodigoBarra = imovelOutrosCriteriosActionForm.getIndicadorCodigoBarra();
		if (indicadorCodigoBarra == null) {
			indicadorCodigoBarra = "1";
		}

		RelatorioGerarRelacaoDebitos relatorioGerarRelacaoDebitos = new RelatorioGerarRelacaoDebitos(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		// id da genrencia regional
		relatorioGerarRelacaoDebitos.addParametro("gerenciaRegional",
				imovelOutrosCriteriosActionForm.getIdGerenciaRegional());
		// numero da quadra origem
		relatorioGerarRelacaoDebitos.addParametro("quadraOrigem",
				imovelOutrosCriteriosActionForm.getQuadraOrigemNM());
		// numero quadra destino
		relatorioGerarRelacaoDebitos.addParametro("quadraDestino",
				imovelOutrosCriteriosActionForm.getQuadraDestinoNM());
		// lote origem
		relatorioGerarRelacaoDebitos.addParametro("loteOrigem",
				imovelOutrosCriteriosActionForm.getLoteOrigem());
		// lote destino
		relatorioGerarRelacaoDebitos.addParametro("loteDestino",
				imovelOutrosCriteriosActionForm.getLoteDestino());
		// cep
		relatorioGerarRelacaoDebitos.addParametro("cep",
				imovelOutrosCriteriosActionForm.getCEP());
		// id localidade origem
		relatorioGerarRelacaoDebitos.addParametro("localidadeOrigem",
				imovelOutrosCriteriosActionForm.getLocalidadeOrigemID());
		// id localidade destino
		relatorioGerarRelacaoDebitos.addParametro("localidadeDestino",
				imovelOutrosCriteriosActionForm.getLocalidadeDestinoID());
		// setor comercial origem ID
		relatorioGerarRelacaoDebitos.addParametro("setorComercialOrigemCD",
				imovelOutrosCriteriosActionForm.getSetorComercialOrigemCD());
		// setor comercial destino ID
		relatorioGerarRelacaoDebitos.addParametro("setorComercialDestinoCD",
				imovelOutrosCriteriosActionForm.getSetorComercialDestinoCD());
		// cliente ID
		relatorioGerarRelacaoDebitos.addParametro("clienteID",
				imovelOutrosCriteriosActionForm.getIdCliente());
		// municipio ID
		relatorioGerarRelacaoDebitos.addParametro("municipioID",
				imovelOutrosCriteriosActionForm.getIdMunicipio());
		// bairro ID
		relatorioGerarRelacaoDebitos.addParametro("bairroID",
				imovelOutrosCriteriosActionForm.getIdBairro());
		// logradouro ID
		relatorioGerarRelacaoDebitos.addParametro("logradouroID",
				imovelOutrosCriteriosActionForm.getIdLogradouro());
		
		// cliente tipo ID
		relatorioGerarRelacaoDebitos.addParametro("clienteTipoID",
				imovelOutrosCriteriosActionForm.getDescricao());
		
		// cliente relacao tipo ID
		relatorioGerarRelacaoDebitos.addParametro("clienteRelacaoTipoID",
				imovelOutrosCriteriosActionForm.getIndicadorUso());
		
		// imovel condominio ID
		relatorioGerarRelacaoDebitos.addParametro("imovelCondominioID",
				imovelOutrosCriteriosActionForm.getIdImovelCondominio());
		// imovel Principal ID
		relatorioGerarRelacaoDebitos.addParametro("imovelPrincipalID",
				imovelOutrosCriteriosActionForm.getIdImovelPrincipal());
		// nome Conta ID
		relatorioGerarRelacaoDebitos.addParametro("nomeContaID",
				imovelOutrosCriteriosActionForm.getIdNomeConta());
		// situacao ligacao Agua
		relatorioGerarRelacaoDebitos.addParametro("situacaoAgua",
				imovelOutrosCriteriosActionForm.getSituacaoAgua());
		// consumo Minimo Inicial agua
		relatorioGerarRelacaoDebitos.addParametro("consumoMinimoInicial",
				imovelOutrosCriteriosActionForm.getConsumoMinimoInicial());
		// consumo Minimo Final agua
		relatorioGerarRelacaoDebitos.addParametro("consumoMinimoFinal",
				imovelOutrosCriteriosActionForm.getConsumoMinimoFinal());

		// situacao Ligacao Esgoto
		relatorioGerarRelacaoDebitos.addParametro("situacaoLigacaoEsgoto",
				imovelOutrosCriteriosActionForm.getSituacaoLigacaoEsgoto());
		// consumo Minimo Fixado Esgoto Inicial
		relatorioGerarRelacaoDebitos.addParametro(
				"consumoMinimoFixadoEsgotoInicial",
				imovelOutrosCriteriosActionForm
						.getConsumoMinimoFixadoEsgotoInicial());
		// consumo Minimo Fixado Esgoto Final
		relatorioGerarRelacaoDebitos.addParametro(
				"consumoMinimoFixadoEsgotoFinal",
				imovelOutrosCriteriosActionForm
						.getConsumoMinimoFixadoEsgotoFinal());

		// intervalo Percentual Esgoto Inicial
		relatorioGerarRelacaoDebitos.addParametro(
				"intervaloPercentualEsgotoInicial",
				imovelOutrosCriteriosActionForm
						.getIntervaloPercentualEsgotoInicial());
		// intervalor Percentual Esgoto Final
		relatorioGerarRelacaoDebitos.addParametro(
				"intervaloPercentualEsgotoFinal",
				imovelOutrosCriteriosActionForm
						.getIntervaloPercentualEsgotoFinal());
		// indicador Medicao
		relatorioGerarRelacaoDebitos.addParametro("indicadorMedicao",
				imovelOutrosCriteriosActionForm.getIndicadorMedicao());
		// tipo Medicao ID
		relatorioGerarRelacaoDebitos.addParametro("tipoMedicaoID",
				imovelOutrosCriteriosActionForm.getTipoMedicao());
		// intervalo Media Minima Imovel Inicial
		relatorioGerarRelacaoDebitos.addParametro(
				"intervaloMediaMinimaImovelInicial",
				imovelOutrosCriteriosActionForm
						.getIntervaloMediaMinimaImovelInicio());
		// intervalo Media Minima Imovel Final
		relatorioGerarRelacaoDebitos.addParametro(
				"intervaloMediaMinimaImoveFinal",
				imovelOutrosCriteriosActionForm
						.getIntervaloMediaMinimaImovelFinal());
		// intervalo Media Minima Hidrometro Inicial
		relatorioGerarRelacaoDebitos.addParametro(
				"intervaloMediaMinimaHidrometroInicial",
				imovelOutrosCriteriosActionForm
						.getIntervaloMediaMinimaHidrometroInicio());
		// intervalo Media Minima Hidrometro Final
		relatorioGerarRelacaoDebitos.addParametro(
				"intervaloMediaMinimaHidrometroFinal",
				imovelOutrosCriteriosActionForm
						.getIntervaloMediaMinimaHidrometroFinal());
		// perfil Imovel ID
		relatorioGerarRelacaoDebitos.addParametro("perfilImovelID",
				imovelOutrosCriteriosActionForm.getPerfilImovel());
		// categoria Imovel ID
		relatorioGerarRelacaoDebitos.addParametro("categoriaImovelID",
				imovelOutrosCriteriosActionForm.getCategoriaImovel());
		// sub categoria ID
		relatorioGerarRelacaoDebitos.addParametro("subCategoriaID",
				imovelOutrosCriteriosActionForm.getSubcategoria());
		// quantidade Economias Inicial
		relatorioGerarRelacaoDebitos
				.addParametro("quantidadeEconomiasInicial",
						imovelOutrosCriteriosActionForm
								.getQuantidadeEconomiasInicial());
		// quantidade Economias Final
		relatorioGerarRelacaoDebitos.addParametro("quantidadeEconomiasFinal",
				imovelOutrosCriteriosActionForm.getQuantidadeEconomiasFinal());
		// numero Pontos Inicial
		relatorioGerarRelacaoDebitos.addParametro("numeroPontosInicial",
				imovelOutrosCriteriosActionForm.getNumeroPontosInicial());
		// numero Pontos Final
		relatorioGerarRelacaoDebitos.addParametro("numeroPontosFinal",
				imovelOutrosCriteriosActionForm.getNumeroPontosFinal());
		// numero Moradores Inicial
		relatorioGerarRelacaoDebitos.addParametro("numeroMoradoresInicial",
				imovelOutrosCriteriosActionForm.getNumeroMoradoresInicial());
		// numero Moradoras Final
		relatorioGerarRelacaoDebitos.addParametro("numeroMoradoresFinal",
				imovelOutrosCriteriosActionForm.getNumeroMoradoresFinal());
		// area Construida Inicial
		relatorioGerarRelacaoDebitos.addParametro("areaConstruidaInicial",
				imovelOutrosCriteriosActionForm.getAreaConstruidaInicial());
		// area Construida Final
		relatorioGerarRelacaoDebitos.addParametro("areaConstruidaFinal",
				imovelOutrosCriteriosActionForm.getAreaConstruidaFinal());
		// area Construida Faixa
		relatorioGerarRelacaoDebitos.addParametro("areaConstruidaFaixa",
				imovelOutrosCriteriosActionForm.getAreaConstruidaFaixa());
		// poco Tipo ID
		relatorioGerarRelacaoDebitos.addParametro("pocoTipoID",
				imovelOutrosCriteriosActionForm.getTipoPoco());
		// tipo Situacao Faturamento ID
		relatorioGerarRelacaoDebitos.addParametro("tipoSituacaoFaturamentoID",
				imovelOutrosCriteriosActionForm
						.getTipoSituacaoEspecialFaturamento());
		// tipo Situacao Especial Cobranca ID
		relatorioGerarRelacaoDebitos.addParametro(
				"tipoSituacaoEspecialCobrancaID",
				imovelOutrosCriteriosActionForm
						.getTipoSituacaoEspecialCobranca());
		// situacao Cobranca ID
		relatorioGerarRelacaoDebitos.addParametro("situacaoCobrancaID",
				imovelOutrosCriteriosActionForm.getSituacaoCobranca());
		// dia Vencimento Alternativo
		relatorioGerarRelacaoDebitos.addParametro("diaVencimentoAlternativo",
				imovelOutrosCriteriosActionForm.getDiaVencimentoAlternativo());
		// ocorrencia Cadastro
		relatorioGerarRelacaoDebitos.addParametro("ocorrenciaCadastro",
				imovelOutrosCriteriosActionForm.getOcorrenciaCadastro());
		// tarifa Consumo
		relatorioGerarRelacaoDebitos.addParametro("tarifaConsumo",
				imovelOutrosCriteriosActionForm.getTarifaConsumo());
		// anormalidade Elo
		relatorioGerarRelacaoDebitos.addParametro("anormalidadeElo",
				imovelOutrosCriteriosActionForm.getAnormalidadeElo());
		
		
		//Aba de Débito
		relatorioGerarRelacaoDebitos.addParametro("tipoDebito",
				imovelOutrosCriteriosActionForm.getTipoDebito());
		relatorioGerarRelacaoDebitos.addParametro("valorDebitoInicial", 
				imovelOutrosCriteriosActionForm.getValorDebitoInicial());
		relatorioGerarRelacaoDebitos.addParametro("valorDebitoFinal", 
				imovelOutrosCriteriosActionForm.getValorDebitoFinal());
		relatorioGerarRelacaoDebitos.addParametro("qtdContasInicial", 
				imovelOutrosCriteriosActionForm.getQtdContasInicial());
		relatorioGerarRelacaoDebitos.addParametro("qtdContasFinal", 
				imovelOutrosCriteriosActionForm.getQtdContasFinal());
		relatorioGerarRelacaoDebitos.addParametro("referenciaFaturaInicial", 
				imovelOutrosCriteriosActionForm.getReferenciaFaturaInicial());
		relatorioGerarRelacaoDebitos.addParametro("referenciaFaturaFinal", 
				imovelOutrosCriteriosActionForm.getReferenciaFaturaFinal());		
		relatorioGerarRelacaoDebitos.addParametro("vencimentoInicial", 
				imovelOutrosCriteriosActionForm.getVencimentoInicial());
		relatorioGerarRelacaoDebitos.addParametro("vencimentoFinal", 
				imovelOutrosCriteriosActionForm.getVencimentoFinal());		
		relatorioGerarRelacaoDebitos.addParametro("qtdImoveis", 
				imovelOutrosCriteriosActionForm.getQtdImoveis());
		relatorioGerarRelacaoDebitos.addParametro("qtdMaiores", 
				imovelOutrosCriteriosActionForm.getQtdMaiores());
		relatorioGerarRelacaoDebitos.addParametro("indicadorCodigoBarra",
				indicadorCodigoBarra);
		// ordenação
		relatorioGerarRelacaoDebitos.addParametro(
				"ordenacao", imovelOutrosCriteriosActionForm
						.getOrdenacaoRelatorio());
		
		relatorioGerarRelacaoDebitos.addParametro("indicadorCpfCnpj", 
				imovelOutrosCriteriosActionForm.getIndicadorCpfCnpjInformado());
		relatorioGerarRelacaoDebitos.addParametro("cpfCnpj",
				imovelOutrosCriteriosActionForm.getCpfCnpj());
		
		//try {
			/*byte[] relatorioRetorno = null;

			OutputStream out = null;

			// chama o metódo de gerar relatório
			relatorioRetorno = (byte[]) relatorioGerarRelacaoDebitos.executar();

			if (retorno == null) {
				// prepara a resposta para o popup
				httpServletResponse.setContentType("application/pdf");
				out = httpServletResponse.getOutputStream();
				out.write(relatorioRetorno);
				out.flush();
				out.close();
			}
*/
/*		} catch (IOException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

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
		*/
		
		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorioGerarRelacaoDebitos.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));

		
		retorno = processarExibicaoRelatorio(relatorioGerarRelacaoDebitos, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
		
		// devolve o mapeamento contido na variável retorno
		return retorno;
	}
}
