package gcom.relatorio.cadastro.imovel;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.bean.ImovelRelatorioHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de imóvel manter
 * 
 * @author Rafael Corrêa
 * @created 11 de Julho de 2005
 */
public class RelatorioImovelEndereco extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAgua
	 */
	public RelatorioImovelEndereco(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_IMOVEL_ENDERECO);
	}
	
	@Deprecated
	public RelatorioImovelEndereco() {
		super(null, "");
	}

	/**
	 * <<Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// Recupera os parâmetros utilizados na formação da query

		// id da genrencia regional
		String gerenciaRegionalPesquisa = (String) getParametro("gerenciaRegional");
		// id da unidade negocio
		String idUnidadeNegoio = (String) getParametro("unidadeNegocio");

		// numero da quadra origem
		String qudraOrigem = (String) getParametro("quadraOrigem");
		// numero quadra destino
		String quadraDestino = (String) getParametro("quadraDestino");
		// lote origem
		String loteOrigem = (String) getParametro("loteOrigem");
		// lote destino
		String loteDestino = (String) getParametro("loteDestino");
		// cep
		String cep = (String) getParametro("cep");
		// id localidade origem
		String localidadeOrigem = (String) getParametro("localidadeOrigem");
		// id localidade destino
		String localidadeDestino = (String) getParametro("localidadeDestino");
		// setor comercial origem CD
		String setorComercialOrigemCD = (String) getParametro("setorComercialOrigemCD");
		// setor comercial destino CD
		String setorComercialDestinoCD = (String) getParametro("setorComercialDestinoCD");
		// cliente ID
		String clienteID = (String) getParametro("clienteID");
		// municipio ID
		String municipioID = (String) getParametro("municipioID");
		// bairro ID
		String bairroID = (String) getParametro("bairroID");
		// logradouro ID
		String logradouroID = (String) getParametro("logradouroID");
		// cliente relacao tipo ID
		String clienteRelacaoTipoID = (String) getParametro("clienteRelacaoTipoID");
		// cliente tipo ID
		String clienteTipoID = (String) getParametro("clienteTipoID");
		// imovel condominio ID
		String imovelCondominioID = (String) getParametro("imovelCondominioID");
		// imovel Principal ID
		String imovelPrincipalID = (String) getParametro("imovelPrincipalID");
		// nome Conta ID
		// String nomeContaID = (String) getParametro("nomeContaID");
		// situacao Agua
		String situacaoAgua = (String) getParametro("situacaoAgua");
		// situacao Ligacao Esgoto
		String situacaoLigacaoEsgoto = (String) getParametro("situacaoLigacaoEsgoto");
		// consumo Minimo Inicial
		String consumoMinimoInicial = (String) getParametro("consumoMinimoInicial");
		// consumo Minimo Final
		String consumoMinimoFinal = (String) getParametro("consumoMinimoFinal");
		// consumo Minimo Fixado Esgoto Inicial
		String consumoMinimoFixadoEsgotoInicial = (String) getParametro("consumoMinimoFixadoEsgotoInicial");
		// consumo Minimo Fixado Esgoto Final
		String consumoMinimoFixadoEsgotoFinal = (String) getParametro("consumoMinimoFixadoEsgotoFinal");
		// intervalo Percentual Esgoto Inicial
		String intervaloPercentualEsgotoInicial = (String) getParametro("intervaloPercentualEsgotoInicial");
		// intervalor Percentual Esgoto Final
		String intervaloPercentualEsgotoFinal = (String) getParametro("intervaloPercentualEsgotoFinal");
		// indicador Medicao
		String indicadorMedicaoPesquisa = (String) getParametro("indicadorMedicao");
		// tipo Medicao ID
		String tipoMedicaoID = (String) getParametro("tipoMedicaoID");
		// intervalo Media Minima Imovel Inicial
		String intervaloMediaMinimaImovelInicial = (String) getParametro("intervaloMediaMinimaImovelInicial");
		// intervalo Media Minima Imovel Final
		String intervaloMediaMinimaImoveFinal = (String) getParametro("intervaloMediaMinimaImoveFinal");
		// intervalo Media Minima Hidrometro Inicial
		String intervaloMediaMinimaHidrometroInicial = (String) getParametro("intervaloMediaMinimaHidrometroInicial");
		// intervalo Media Minima Hidrometro Final
		String intervaloMediaMinimaHidrometroFinal = (String) getParametro("intervaloMediaMinimaHidrometroFinal");
		// perfil Imovel ID
		String perfilImovelID = (String) getParametro("perfilImovelID");
		// categoria Imovel ID
		String categoriaImovelID = (String) getParametro("categoriaImovelID");
		// sub categoria ID
		String subCategoriaID = (String) getParametro("subCategoriaID");
		// quantidade Economias Inicial
		String quantidadeEconomiasInicial = (String) getParametro("quantidadeEconomiasInicial");
		// quantidade Economias Final
		String quantidadeEconomiasFinal = (String) getParametro("quantidadeEconomiasFinal");
		// numero Pontos Inicial
		String numeroPontosInicial = (String) getParametro("numeroPontosInicial");
		// numero Pontos Final
		String numeroPontosFinal = (String) getParametro("numeroPontosFinal");
		// numero Moradores Inicial
		String numeroMoradoresInicial = (String) getParametro("numeroMoradoresInicial");
		// numero Moradoras Final
		String numeroMoradoresFinal = (String) getParametro("numeroMoradoresFinal");
		// area Construida Inicial
		String areaConstruidaInicial = (String) getParametro("areaConstruidaInicial");
		// area Construida Final
		String areaConstruidaFinal = (String) getParametro("areaConstruidaFinal");
		// area Construida Faixa
		String areaConstruidaFaixa = (String) getParametro("areaConstruidaFaixa");
		// poco Tipo ID
		String pocoTipoID = (String) getParametro("pocoTipoID");
		// tipo Situacao Faturamento ID
		String tipoSituacaoFaturamentoID = (String) getParametro("tipoSituacaoFaturamentoID");
		// tipo Situacao Especial Cobranca ID
		String tipoSituacaoEspecialCobrancaID = (String) getParametro("tipoSituacaoEspecialCobrancaID");
		// situacao Cobranca ID
		String situacaoCobrancaID = (String) getParametro("situacaoCobrancaID");
		// dia Vencimento Alternativo
		String diaVencimentoAlternativo = (String) getParametro("diaVencimentoAlternativo");
		// ocorrencia Cadastro
		String ocorrenciaCadastro = (String) getParametro("ocorrenciaCadastro");
		// tarifa Consumo
		String tarifaConsumo = (String) getParametro("tarifaConsumo");
		// anormalidade Elo
		String anormalidadeElo = (String) getParametro("anormalidadeElo");
		// numero da sequencia da rota inicial
		String seqRotaInicial = (String) getParametro("sequencialRotaInicial");
		// numero da sequencia da rota final
		String seqRotaFinal = (String) getParametro("sequencialRotaFinal");		
		//Ordenacao do Relatorio
		String ordenacaoRelatorio = (String) getParametro("ordenacaoRelatorio");
		// Rota Inicial
		String rotaInicial = (String) getParametro("rotaInicial");
		// Rota Final
		String rotaFinal = (String) getParametro("rotaFinal");
		

		
		Fachada fachada = Fachada.getInstancia();

		Collection imoveisRelatoriosHelper = fachada
				.gerarRelatorioImovelEnderecoOutrosCriterios(imovelCondominioID,
						imovelPrincipalID, situacaoAgua, consumoMinimoInicial,
						consumoMinimoFinal, situacaoLigacaoEsgoto,
						consumoMinimoFixadoEsgotoInicial,
						consumoMinimoFixadoEsgotoFinal,
						intervaloPercentualEsgotoInicial,
						intervaloPercentualEsgotoFinal,
						intervaloMediaMinimaImovelInicial,
						intervaloMediaMinimaImoveFinal,
						intervaloMediaMinimaHidrometroInicial,
						intervaloMediaMinimaHidrometroFinal, perfilImovelID,
						pocoTipoID, tipoSituacaoFaturamentoID,
						situacaoCobrancaID, tipoSituacaoEspecialCobrancaID,
						anormalidadeElo, areaConstruidaInicial,
						areaConstruidaFinal, ocorrenciaCadastro, tarifaConsumo,
						gerenciaRegionalPesquisa, localidadeOrigem,
						localidadeDestino, setorComercialOrigemCD,
						setorComercialDestinoCD, qudraOrigem, quadraDestino,
						loteOrigem, loteDestino, cep, logradouroID, bairroID,
						municipioID, tipoMedicaoID, indicadorMedicaoPesquisa,
						subCategoriaID, categoriaImovelID,
						quantidadeEconomiasInicial, quantidadeEconomiasFinal,
						diaVencimentoAlternativo, clienteID, clienteTipoID,
						clienteRelacaoTipoID, numeroPontosInicial,
						numeroPontosFinal, numeroMoradoresInicial,
						numeroMoradoresFinal, areaConstruidaFaixa,idUnidadeNegoio, 
						seqRotaInicial, seqRotaFinal, rotaInicial,
						rotaFinal, ordenacaoRelatorio);

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioImovelEnderecoBean relatorioBean = null;

		// se a coleção de parâmetros da analise não for vazia
		if (imoveisRelatoriosHelper != null
				&& !imoveisRelatoriosHelper.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			Iterator imovelRelatorioIterator = imoveisRelatoriosHelper
					.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (imovelRelatorioIterator.hasNext()) {

				ImovelRelatorioHelper helper = (ImovelRelatorioHelper) imovelRelatorioIterator.next();

				relatorioBean = new RelatorioImovelEnderecoBean();

						// Código da Gerência Regional
						relatorioBean.setIdGerenciaRegional(X(helper.getIdGerenciaRegional()));
						// Descrição da Gerência Regional
						relatorioBean.setNomeGerenciaRegional(X(helper.getDescricaoGerenciaRegional()));
						// Código da Localidade
						relatorioBean.setIdLocalidade(X(helper.getIdLocalidade()));
						// Descrição da Localidade
						relatorioBean.setNomeLocalidade(X(helper.getDescricaoLocalidade()));
						// Código do Setor Comercial
						relatorioBean.setIdSetorComercial(X(helper.getCodigoSetorComercial()));
						// Descrição do Setor Comercial
						relatorioBean.setNomeSetorComercial(X(helper.getDescricaoSetorComercial()));

						// Endereço
						relatorioBean.setEndereco(helper.getEndereco());
						// Matrícula do Imóvel
						relatorioBean.setMatricula(X(helper.getMatriculaImovel()));
						// Nome do Cliente Usuário
						relatorioBean.setNomeCliente(X(helper.getClienteUsuarioNome()));
						// Quadra.Lote.SubLote
						relatorioBean.setQuadraLoteSubLote(format(helper.getNumeroQuadra(), 4) + "." + format(helper.getNumeroLote(), 4) + "." + format(helper.getNumeroSubLote(), 3));
						//Quadra.Lote.SubLote.Rota.SequenciaRota
						//relatorioBean.setQuadraLoteSubLoteRotaSegRota(fachada.pesquisarInscricaoImovel(helper.getMatriculaImovel()));
						if (helper.getNumeroSequencialRota() != null){
							relatorioBean.setQuadraLoteSubLoteRotaSegRota(format(helper.getNumeroQuadra(), 4) + "." + format(helper.getNumeroLote(), 4) 
									+ "." + format(helper.getNumeroSubLote(), 3) + "." + format(helper.getCodigoRota(), 2) + "." + format(helper.getNumeroSequencialRota(),4));
						}else{
							relatorioBean.setQuadraLoteSubLoteRotaSegRota(format(helper.getNumeroQuadra(), 4) + "." + format(helper.getNumeroLote(), 4) 
									+ "." + format(helper.getNumeroSubLote(), 3) + "." + format(helper.getCodigoRota(), 2) + "." + format(0, 4));
						}
						// Categoria
						relatorioBean.setCategoria(X(helper.getDescricaoAbreviadaCategoria()));
						// Economias
						relatorioBean.setEconomias(X(helper.getQuantidadeEconomia()));
						// Situação da Ligação de Água
						relatorioBean.setSituacaoAgua(X(helper.getLigacaoAguaSituacao()));
						// Situação da Ligação de Esgoto
						relatorioBean.setSituacaoEsgoto(X(helper.getLigacaoEsgotoSituacao()));
						// Indicador Medido de Esgoto
						relatorioBean.setIndicadorMedidoEsgoto(X(helper.getIndicadorExistenciaHidrometro() == 1 ? "SIM" : "NÃO"));
						// Percentual de Esgoto
						relatorioBean.setPercentualEsgoto(X(helper.getPercentual()));
						// Consumo Mínimo Fixado de Esgoto
						relatorioBean.setConsumoMinimoEsgoto(X(helper.getConsumoMinimoFixadoLigacaoEsgoto()));

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("tipoFormatoRelatorio", "R0608");

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		// exporta o relatório em pdf e retorna o array de bytes
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEL_ENDERECO, parametros, ds, tipoFormatoRelatorio);
		
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.IMOVEL_ENDERECO, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
//		 id da genrencia regional
		String gerenciaRegionalPesquisa = (String) getParametro("gerenciaRegional");
		// id da unidade negocio
		String idUnidadeNegoio = (String) getParametro("unidadeNegocio");

		// numero da quadra origem
		String qudraOrigem = (String) getParametro("quadraOrigem");
		// numero quadra destino
		String quadraDestino = (String) getParametro("quadraDestino");
		// lote origem
		String loteOrigem = (String) getParametro("loteOrigem");
		// lote destino
		String loteDestino = (String) getParametro("loteDestino");
		// cep
		String cep = (String) getParametro("cep");
		// id localidade origem
		String localidadeOrigem = (String) getParametro("localidadeOrigem");
		// id localidade destino
		String localidadeDestino = (String) getParametro("localidadeDestino");
		// setor comercial origem CD
		String setorComercialOrigemCD = (String) getParametro("setorComercialOrigemCD");
		// setor comercial destino CD
		String setorComercialDestinoCD = (String) getParametro("setorComercialDestinoCD");
		// cliente ID
		String clienteID = (String) getParametro("clienteID");
		// municipio ID
		String municipioID = (String) getParametro("municipioID");
		// bairro ID
		String bairroID = (String) getParametro("bairroID");
		// logradouro ID
		String logradouroID = (String) getParametro("logradouroID");
		// cliente relacao tipo ID
		String clienteRelacaoTipoID = (String) getParametro("clienteRelacaoTipoID");
		// cliente tipo ID
		String clienteTipoID = (String) getParametro("clienteTipoID");
		// imovel condominio ID
		String imovelCondominioID = (String) getParametro("imovelCondominioID");
		// imovel Principal ID
		String imovelPrincipalID = (String) getParametro("imovelPrincipalID");
		// nome Conta ID
		// String nomeContaID = (String) getParametro("nomeContaID");
		// situacao Agua
		String situacaoAgua = (String) getParametro("situacaoAgua");
		// situacao Ligacao Esgoto
		String situacaoLigacaoEsgoto = (String) getParametro("situacaoLigacaoEsgoto");
		// consumo Minimo Inicial
		String consumoMinimoInicial = (String) getParametro("consumoMinimoInicial");
		// consumo Minimo Final
		String consumoMinimoFinal = (String) getParametro("consumoMinimoFinal");
		// consumo Minimo Fixado Esgoto Inicial
		String consumoMinimoFixadoEsgotoInicial = (String) getParametro("consumoMinimoFixadoEsgotoInicial");
		// consumo Minimo Fixado Esgoto Final
		String consumoMinimoFixadoEsgotoFinal = (String) getParametro("consumoMinimoFixadoEsgotoFinal");
		// intervalo Percentual Esgoto Inicial
		String intervaloPercentualEsgotoInicial = (String) getParametro("intervaloPercentualEsgotoInicial");
		// intervalor Percentual Esgoto Final
		String intervaloPercentualEsgotoFinal = (String) getParametro("intervaloPercentualEsgotoFinal");
		// indicador Medicao
		String indicadorMedicaoPesquisa = (String) getParametro("indicadorMedicao");
		// tipo Medicao ID
		String tipoMedicaoID = (String) getParametro("tipoMedicaoID");
		// intervalo Media Minima Imovel Inicial
		String intervaloMediaMinimaImovelInicial = (String) getParametro("intervaloMediaMinimaImovelInicial");
		// intervalo Media Minima Imovel Final
		String intervaloMediaMinimaImoveFinal = (String) getParametro("intervaloMediaMinimaImoveFinal");
		// intervalo Media Minima Hidrometro Inicial
		String intervaloMediaMinimaHidrometroInicial = (String) getParametro("intervaloMediaMinimaHidrometroInicial");
		// intervalo Media Minima Hidrometro Final
		String intervaloMediaMinimaHidrometroFinal = (String) getParametro("intervaloMediaMinimaHidrometroFinal");
		// perfil Imovel ID
		String perfilImovelID = (String) getParametro("perfilImovelID");
		// categoria Imovel ID
		String categoriaImovelID = (String) getParametro("categoriaImovelID");
		// sub categoria ID
		String subCategoriaID = (String) getParametro("subCategoriaID");
		// quantidade Economias Inicial
		String quantidadeEconomiasInicial = (String) getParametro("quantidadeEconomiasInicial");
		// quantidade Economias Final
		String quantidadeEconomiasFinal = (String) getParametro("quantidadeEconomiasFinal");
		// numero Pontos Inicial
		String numeroPontosInicial = (String) getParametro("numeroPontosInicial");
		// numero Pontos Final
		String numeroPontosFinal = (String) getParametro("numeroPontosFinal");
		// numero Moradores Inicial
		String numeroMoradoresInicial = (String) getParametro("numeroMoradoresInicial");
		// numero Moradoras Final
		String numeroMoradoresFinal = (String) getParametro("numeroMoradoresFinal");
		// area Construida Inicial
		String areaConstruidaInicial = (String) getParametro("areaConstruidaInicial");
		// area Construida Final
		String areaConstruidaFinal = (String) getParametro("areaConstruidaFinal");
		// area Construida Faixa
		String areaConstruidaFaixa = (String) getParametro("areaConstruidaFaixa");
		// poco Tipo ID
		String pocoTipoID = (String) getParametro("pocoTipoID");
		// tipo Situacao Faturamento ID
		String tipoSituacaoFaturamentoID = (String) getParametro("tipoSituacaoFaturamentoID");
		// tipo Situacao Especial Cobranca ID
		String tipoSituacaoEspecialCobrancaID = (String) getParametro("tipoSituacaoEspecialCobrancaID");
		// situacao Cobranca ID
		String situacaoCobrancaID = (String) getParametro("situacaoCobrancaID");
		// dia Vencimento Alternativo
		String diaVencimentoAlternativo = (String) getParametro("diaVencimentoAlternativo");
		// ocorrencia Cadastro
		String ocorrenciaCadastro = (String) getParametro("ocorrenciaCadastro");
		// tarifa Consumo
		String tarifaConsumo = (String) getParametro("tarifaConsumo");
		// anormalidade Elo
		String anormalidadeElo = (String) getParametro("anormalidadeElo");
		// Rota Inicial
		String rotaInicial = (String) getParametro("rotaInicial");
		// Rota Final
		String rotaFinal = (String) getParametro("rotaFinal");
		//Ordenacao do Relatorio
		String ordenacaoRelatorio = (String) getParametro("ordenacaoRelatorio");
		//numero da sequencia da rota inicial
		String seqRotaInicial = (String) getParametro("sequencialRotaInicial");
		// numero da sequencia da rota final
		String seqRotaFinal = (String) getParametro("sequencialRotaFinal");	
		
		Integer retorno = Fachada.getInstancia().gerarRelatorioImovelEnderecoOutrosCriteriosCount(
				imovelCondominioID,
				imovelPrincipalID, situacaoAgua, consumoMinimoInicial,
				consumoMinimoFinal, situacaoLigacaoEsgoto,
				consumoMinimoFixadoEsgotoInicial,
				consumoMinimoFixadoEsgotoFinal,
				intervaloPercentualEsgotoInicial,
				intervaloPercentualEsgotoFinal,
				intervaloMediaMinimaImovelInicial,
				intervaloMediaMinimaImoveFinal,
				intervaloMediaMinimaHidrometroInicial,
				intervaloMediaMinimaHidrometroFinal, perfilImovelID,
				pocoTipoID, tipoSituacaoFaturamentoID,
				situacaoCobrancaID, tipoSituacaoEspecialCobrancaID,
				anormalidadeElo, areaConstruidaInicial,
				areaConstruidaFinal, ocorrenciaCadastro, tarifaConsumo,
				gerenciaRegionalPesquisa, localidadeOrigem,
				localidadeDestino, setorComercialOrigemCD,
				setorComercialDestinoCD, qudraOrigem, quadraDestino,
				loteOrigem, loteDestino, cep, logradouroID, bairroID,
				municipioID, tipoMedicaoID, indicadorMedicaoPesquisa,
				subCategoriaID, categoriaImovelID,
				quantidadeEconomiasInicial, quantidadeEconomiasFinal,
				diaVencimentoAlternativo, clienteID, clienteTipoID,
				clienteRelacaoTipoID, numeroPontosInicial,
				numeroPontosFinal, numeroMoradoresInicial,
				numeroMoradoresFinal, areaConstruidaFaixa,idUnidadeNegoio,
				rotaInicial, rotaFinal, ordenacaoRelatorio,seqRotaInicial, seqRotaFinal);
		
		if (retorno == 0) {
			// Caso a pesquisa não retorne nenhum resultado comunica ao
			// usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");

		}
		
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImovelEndereco", this);
	}
	
	private String X(Object o) {
		return o == null ? "" : o.toString();
	}
	
	private String format(Number number, int integer) {
		String retorno = "";
		if (number != null){
			NumberFormat format = NumberFormat.getIntegerInstance();
			format.setMinimumIntegerDigits(integer);
			format.setMaximumIntegerDigits(integer);
			retorno = format.format(number).replace(".","");
		}
		return retorno;
	}

}
