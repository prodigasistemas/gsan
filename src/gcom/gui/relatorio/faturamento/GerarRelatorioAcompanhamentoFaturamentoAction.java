package gcom.gui.relatorio.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.cadastro.imovel.ImovelOutrosCriteriosActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioAcompanhamentoFaturamento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Title: GCOM
 * Description: Sistema de Gestão Comercial
 * Copyright: Copyright (c) 2004
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * 
 * @author Fernanda Paiva
 * @created 09 de Maio de 2006
 * @version 1.0
 */

public class GerarRelatorioAcompanhamentoFaturamentoAction extends ExibidorProcessamentoTarefaRelatorio {

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

		/* temporario enquanto o filtro naum ta pronto */
		//Collection imoveis = new ArrayList();

		/*ImovelRelatorioHelper imovel1 = new ImovelRelatorioHelper();
		imovel1.setMatriculaImovel(55000098);
		imoveis.add(imovel1);

		ImovelRelatorioHelper imovel2 = new ImovelRelatorioHelper();
		imovel2.setMatriculaImovel(55000072);
		imoveis.add(imovel2);

		ImovelRelatorioHelper imovel3 = new ImovelRelatorioHelper();
		imovel3.setMatriculaImovel(55000114);
		imoveis.add(imovel3);

		ImovelRelatorioHelper imovel4 = new ImovelRelatorioHelper();
		imovel4.setMatriculaImovel(55000115);
		imoveis.add(imovel4);*/

		//Collection imoveisRelatoriosHelper = imoveis;
		
		//Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoImoveis = null;

		ImovelOutrosCriteriosActionForm imovelOutrosCriteriosActionForm = (ImovelOutrosCriteriosActionForm) actionForm;

		// cria uma instância da classe do relatório
		RelatorioAcompanhamentoFaturamento relatorioAcompanhamentoFaturamento = new RelatorioAcompanhamentoFaturamento(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		/*relatorioAcompanhamentoFaturamento.addParametro(
				"imoveisRelatoriosHelper", imoveisRelatoriosHelper);*/
		
		//	colecao imoveis
		relatorioAcompanhamentoFaturamento.addParametro("colecaoImoveis",
				colecaoImoveis);
		// id da genrencia regional
		relatorioAcompanhamentoFaturamento.addParametro("gerenciaRegional",
				imovelOutrosCriteriosActionForm.getIdGerenciaRegional());
		// numero da quadra origem
		relatorioAcompanhamentoFaturamento.addParametro("quadraOrigem",
				imovelOutrosCriteriosActionForm.getQuadraOrigemNM());
		// numero quadra destino
		relatorioAcompanhamentoFaturamento.addParametro("quadraDestino",
				imovelOutrosCriteriosActionForm.getQuadraDestinoNM());
		// lote origem
		relatorioAcompanhamentoFaturamento.addParametro("loteOrigem",
				imovelOutrosCriteriosActionForm.getLoteOrigem());
		// lote destino
		relatorioAcompanhamentoFaturamento.addParametro("loteDestino",
				imovelOutrosCriteriosActionForm.getLoteDestino());
		// cep
		relatorioAcompanhamentoFaturamento.addParametro("cep",
				imovelOutrosCriteriosActionForm.getCEP());
		// id localidade origem
		relatorioAcompanhamentoFaturamento.addParametro("localidadeOrigem",
				imovelOutrosCriteriosActionForm.getLocalidadeOrigemID());
		// id localidade destino
		relatorioAcompanhamentoFaturamento.addParametro("localidadeDestino",
				imovelOutrosCriteriosActionForm.getLocalidadeDestinoID());
		// setor comercial origem ID
		relatorioAcompanhamentoFaturamento.addParametro("setorComercialOrigemCD",
				imovelOutrosCriteriosActionForm.getSetorComercialOrigemCD());
		// setor comercial destino ID
		relatorioAcompanhamentoFaturamento.addParametro("setorComercialDestinoCD",
				imovelOutrosCriteriosActionForm.getSetorComercialDestinoCD());
		// cliente ID
		relatorioAcompanhamentoFaturamento.addParametro("clienteID",
				imovelOutrosCriteriosActionForm.getIdCliente());
		// municipio ID
		relatorioAcompanhamentoFaturamento.addParametro("municipioID",
				imovelOutrosCriteriosActionForm.getIdMunicipio());
		// bairro ID
		relatorioAcompanhamentoFaturamento.addParametro("bairroID",
				imovelOutrosCriteriosActionForm.getIdBairro());
		// logradouro ID
		relatorioAcompanhamentoFaturamento.addParametro("logradouroID",
				imovelOutrosCriteriosActionForm.getIdLogradouro());
		
		// cliente tipo ID
		relatorioAcompanhamentoFaturamento.addParametro("clienteTipoID",
				imovelOutrosCriteriosActionForm.getDescricao());
		
		// cliente relacao tipo ID
		relatorioAcompanhamentoFaturamento.addParametro("clienteRelacaoTipoID",
				imovelOutrosCriteriosActionForm.getIndicadorUso());
		
		// imovel condominio ID
		relatorioAcompanhamentoFaturamento.addParametro("imovelCondominioID",
				imovelOutrosCriteriosActionForm.getIdImovelCondominio());
		// imovel Principal ID
		relatorioAcompanhamentoFaturamento.addParametro("imovelPrincipalID",
				imovelOutrosCriteriosActionForm.getIdImovelPrincipal());
		// nome Conta ID
		relatorioAcompanhamentoFaturamento.addParametro("nomeContaID",
				imovelOutrosCriteriosActionForm.getIdNomeConta());
		// situacao ligacao Agua
		relatorioAcompanhamentoFaturamento.addParametro("situacaoAgua",
				imovelOutrosCriteriosActionForm.getSituacaoAgua());
		// consumo Minimo Inicial agua
		relatorioAcompanhamentoFaturamento.addParametro("consumoMinimoInicial",
				imovelOutrosCriteriosActionForm.getConsumoMinimoInicial());
		// consumo Minimo Final agua
		relatorioAcompanhamentoFaturamento.addParametro("consumoMinimoFinal",
				imovelOutrosCriteriosActionForm.getConsumoMinimoFinal());

		// situacao Ligacao Esgoto
		relatorioAcompanhamentoFaturamento.addParametro("situacaoLigacaoEsgoto",
				imovelOutrosCriteriosActionForm.getSituacaoLigacaoEsgoto());
		// consumo Minimo Fixado Esgoto Inicial
		relatorioAcompanhamentoFaturamento.addParametro(
				"consumoMinimoFixadoEsgotoInicial",
				imovelOutrosCriteriosActionForm
						.getConsumoMinimoFixadoEsgotoInicial());
		// consumo Minimo Fixado Esgoto Final
		relatorioAcompanhamentoFaturamento.addParametro(
				"consumoMinimoFixadoEsgotoFinal",
				imovelOutrosCriteriosActionForm
						.getConsumoMinimoFixadoEsgotoFinal());

		// intervalo Percentual Esgoto Inicial
		relatorioAcompanhamentoFaturamento.addParametro(
				"intervaloPercentualEsgotoInicial",
				imovelOutrosCriteriosActionForm
						.getIntervaloPercentualEsgotoInicial());
		// intervalor Percentual Esgoto Final
		relatorioAcompanhamentoFaturamento.addParametro(
				"intervaloPercentualEsgotoFinal",
				imovelOutrosCriteriosActionForm
						.getIntervaloPercentualEsgotoFinal());
		// indicador Medicao
		relatorioAcompanhamentoFaturamento.addParametro("indicadorMedicao",
				imovelOutrosCriteriosActionForm.getIndicadorMedicao());
		// tipo Medicao ID
		relatorioAcompanhamentoFaturamento.addParametro("tipoMedicaoID",
				imovelOutrosCriteriosActionForm.getTipoMedicao());
		// intervalo Media Minima Imovel Inicial
		relatorioAcompanhamentoFaturamento.addParametro(
				"intervaloMediaMinimaImovelInicial",
				imovelOutrosCriteriosActionForm
						.getIntervaloMediaMinimaImovelInicio());
		// intervalo Media Minima Imovel Final
		relatorioAcompanhamentoFaturamento.addParametro(
				"intervaloMediaMinimaImoveFinal",
				imovelOutrosCriteriosActionForm
						.getIntervaloMediaMinimaImovelFinal());
		// intervalo Media Minima Hidrometro Inicial
		relatorioAcompanhamentoFaturamento.addParametro(
				"intervaloMediaMinimaHidrometroInicial",
				imovelOutrosCriteriosActionForm
						.getIntervaloMediaMinimaHidrometroInicio());
		// intervalo Media Minima Hidrometro Final
		relatorioAcompanhamentoFaturamento.addParametro(
				"intervaloMediaMinimaHidrometroFinal",
				imovelOutrosCriteriosActionForm
						.getIntervaloMediaMinimaHidrometroFinal());
		// perfil Imovel ID
		relatorioAcompanhamentoFaturamento.addParametro("perfilImovelID",
				imovelOutrosCriteriosActionForm.getPerfilImovel());
		// categoria Imovel ID
		relatorioAcompanhamentoFaturamento.addParametro("categoriaImovelID",
				imovelOutrosCriteriosActionForm.getCategoriaImovel());
		// sub categoria ID
		relatorioAcompanhamentoFaturamento.addParametro("subCategoriaID",
				imovelOutrosCriteriosActionForm.getSubcategoria());
		// quantidade Economias Inicial
		relatorioAcompanhamentoFaturamento
				.addParametro("quantidadeEconomiasInicial",
						imovelOutrosCriteriosActionForm
								.getQuantidadeEconomiasInicial());
		// quantidade Economias Final
		relatorioAcompanhamentoFaturamento.addParametro("quantidadeEconomiasFinal",
				imovelOutrosCriteriosActionForm.getQuantidadeEconomiasFinal());
		// numero Pontos Inicial
		relatorioAcompanhamentoFaturamento.addParametro("numeroPontosInicial",
				imovelOutrosCriteriosActionForm.getNumeroPontosInicial());
		// numero Pontos Final
		relatorioAcompanhamentoFaturamento.addParametro("numeroPontosFinal",
				imovelOutrosCriteriosActionForm.getNumeroPontosFinal());
		// numero Moradores Inicial
		relatorioAcompanhamentoFaturamento.addParametro("numeroMoradoresInicial",
				imovelOutrosCriteriosActionForm.getNumeroMoradoresInicial());
		// numero Moradoras Final
		relatorioAcompanhamentoFaturamento.addParametro("numeroMoradoresFinal",
				imovelOutrosCriteriosActionForm.getNumeroMoradoresFinal());
		// area Construida Inicial
		relatorioAcompanhamentoFaturamento.addParametro("areaConstruidaInicial",
				imovelOutrosCriteriosActionForm.getAreaConstruidaInicial());
		// area Construida Final
		relatorioAcompanhamentoFaturamento.addParametro("areaConstruidaFinal",
				imovelOutrosCriteriosActionForm.getAreaConstruidaFinal());
		// area Construida Faixa
		relatorioAcompanhamentoFaturamento.addParametro("areaConstruidaFaixa",
				imovelOutrosCriteriosActionForm.getAreaConstruidaFaixa());
		// poco Tipo ID
		relatorioAcompanhamentoFaturamento.addParametro("pocoTipoID",
				imovelOutrosCriteriosActionForm.getTipoPoco());
		// tipo Situacao Faturamento ID
		relatorioAcompanhamentoFaturamento.addParametro("tipoSituacaoFaturamentoID",
				imovelOutrosCriteriosActionForm
						.getTipoSituacaoEspecialFaturamento());
		// tipo Situacao Especial Cobranca ID
		relatorioAcompanhamentoFaturamento.addParametro(
				"tipoSituacaoEspecialCobrancaID",
				imovelOutrosCriteriosActionForm
						.getTipoSituacaoEspecialCobranca());
		// situacao Cobranca ID
		relatorioAcompanhamentoFaturamento.addParametro("situacaoCobrancaID",
				imovelOutrosCriteriosActionForm.getSituacaoCobranca());
		// dia Vencimento Alternativo
		relatorioAcompanhamentoFaturamento.addParametro("diaVencimentoAlternativo",
				imovelOutrosCriteriosActionForm.getDiaVencimentoAlternativo());
		// ocorrencia Cadastro
		relatorioAcompanhamentoFaturamento.addParametro("ocorrenciaCadastro",
				imovelOutrosCriteriosActionForm.getOcorrenciaCadastro());
		// tarifa Consumo
		relatorioAcompanhamentoFaturamento.addParametro("tarifaConsumo",
				imovelOutrosCriteriosActionForm.getTarifaConsumo());
		// anormalidade Elo
		relatorioAcompanhamentoFaturamento.addParametro("anormalidadeElo",
				imovelOutrosCriteriosActionForm.getAnormalidadeElo());

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametros = fachada
		.pesquisarParametrosDoSistema();
		
		int anoMesReferencia = sistemaParametros
		.getAnoMesFaturamento();
		
		Integer relacaoAcompanhamentoFaturamento = fachada.gerarRelacaoAcompanhamentoFaturamentoCount( 
				imovelOutrosCriteriosActionForm.getIdImovelCondominio(), 
				imovelOutrosCriteriosActionForm.getIdImovelPrincipal(), 
				imovelOutrosCriteriosActionForm.getIdNomeConta(),
				imovelOutrosCriteriosActionForm.getSituacaoAgua(),
				imovelOutrosCriteriosActionForm.getConsumoMinimoInicial(),
				imovelOutrosCriteriosActionForm.getConsumoMinimoFinal(),
				imovelOutrosCriteriosActionForm.getSituacaoLigacaoEsgoto(),
				imovelOutrosCriteriosActionForm.getConsumoMinimoFixadoEsgotoInicial(),
				imovelOutrosCriteriosActionForm.getConsumoMinimoFixadoEsgotoFinal(),
				imovelOutrosCriteriosActionForm.getIntervaloPercentualEsgotoInicial(),
				imovelOutrosCriteriosActionForm.getIntervaloPercentualEsgotoFinal(),
				imovelOutrosCriteriosActionForm.getIntervaloMediaMinimaImovelInicio(),
				imovelOutrosCriteriosActionForm.getIntervaloMediaMinimaImovelFinal(),
				imovelOutrosCriteriosActionForm.getIntervaloMediaMinimaHidrometroInicio(),
				imovelOutrosCriteriosActionForm.getIntervaloMediaMinimaHidrometroFinal(),
				imovelOutrosCriteriosActionForm.getPerfilImovel(),
				imovelOutrosCriteriosActionForm.getTipoPoco(),
				imovelOutrosCriteriosActionForm.getTipoSituacaoEspecialFaturamento(),
				imovelOutrosCriteriosActionForm.getSituacaoCobranca(),
				imovelOutrosCriteriosActionForm.getTipoSituacaoEspecialCobranca(),
				imovelOutrosCriteriosActionForm.getAnormalidadeElo(),
				imovelOutrosCriteriosActionForm.getAreaConstruidaInicial(),
				imovelOutrosCriteriosActionForm.getAreaConstruidaFinal(),
				imovelOutrosCriteriosActionForm.getOcorrenciaCadastro(),
				imovelOutrosCriteriosActionForm.getTarifaConsumo(),
				imovelOutrosCriteriosActionForm.getIdGerenciaRegional(),
				imovelOutrosCriteriosActionForm.getLocalidadeOrigemID(),
				imovelOutrosCriteriosActionForm.getLocalidadeDestinoID(),
				imovelOutrosCriteriosActionForm.getSetorComercialOrigemCD(),
				imovelOutrosCriteriosActionForm.getSetorComercialDestinoCD(),
				imovelOutrosCriteriosActionForm.getQuadraOrigemNM(),
				imovelOutrosCriteriosActionForm.getQuadraDestinoNM(),
	             imovelOutrosCriteriosActionForm.getLoteOrigem(),
	             imovelOutrosCriteriosActionForm.getLoteDestino(),
	             imovelOutrosCriteriosActionForm.getCEP(),
	             imovelOutrosCriteriosActionForm.getIdLogradouro(),
	             imovelOutrosCriteriosActionForm.getIdBairro(),
	             imovelOutrosCriteriosActionForm.getIdMunicipio(),
	             imovelOutrosCriteriosActionForm.getTipoMedicao(),
	             imovelOutrosCriteriosActionForm.getIndicadorMedicao(),
	             imovelOutrosCriteriosActionForm.getSubcategoria(),
	             imovelOutrosCriteriosActionForm.getCategoriaImovel(),
	             imovelOutrosCriteriosActionForm.getQuantidadeEconomiasInicial(),
	             imovelOutrosCriteriosActionForm.getQuantidadeEconomiasFinal(),
	             imovelOutrosCriteriosActionForm.getDiaVencimentoAlternativo(),
	             imovelOutrosCriteriosActionForm.getIdCliente(),
	             imovelOutrosCriteriosActionForm.getDescricao(),
	             imovelOutrosCriteriosActionForm.getIndicadorUso(),
	             imovelOutrosCriteriosActionForm.getNumeroPontosInicial(),
	             imovelOutrosCriteriosActionForm.getNumeroPontosFinal(),
	             imovelOutrosCriteriosActionForm.getNumeroMoradoresInicial(),
	             imovelOutrosCriteriosActionForm.getNumeroMoradoresFinal(),
	             imovelOutrosCriteriosActionForm.getAreaConstruidaFaixa(),
	             anoMesReferencia);
		
		if ( relacaoAcompanhamentoFaturamento == null || relacaoAcompanhamentoFaturamento == 0 ) {
			//Caso a pesquisa não retorne nenhum objeto comunica ao usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		relatorioAcompanhamentoFaturamento.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioAcompanhamentoFaturamento,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");

		}

		return retorno;
	}
	
}
