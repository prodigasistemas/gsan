package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.ConstantesRelatorios;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class RelatorioDadosTarifaSocial extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe RelatorioDadosEconomiaImovel
	 */
	public RelatorioDadosTarifaSocial(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DADOS_TARIFA_SOCIAL);
	}
	
	@Deprecated
	public RelatorioDadosTarifaSocial() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		/*// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Collection imoveisRelatoriosHelper = (Collection) getParametro("imoveisRelatoriosHelper");
		Imovel imovelParametrosInicial = (Imovel) getParametro("imovelParametrosInicial");
		Imovel imovelParametrosFinal = (Imovel) getParametro("imovelParametrosFinal");
		ClienteImovel clienteImovelParametros = (ClienteImovel) getParametro("clienteImovelParametros");
		Municipio municipio = (Municipio) getParametro("municipio");
		Bairro bairro = (Bairro) getParametro("bairro");
		MedicaoHistorico medicaoHistoricoParametrosInicial = (MedicaoHistorico) getParametro("medicaoHistoricoParametrosInicial");
		MedicaoHistorico medicaoHistoricoParametrosFinal = (MedicaoHistorico) getParametro("medicaoHistoricoParametrosFinal");
		ConsumoHistorico consumoHistoricoParametrosInicial = (ConsumoHistorico) getParametro("consumoHistoricoParametrosInicial");
		ConsumoHistorico consumoHistoricoParametrosFinal = (ConsumoHistorico) getParametro("consumoHistoricoParametrosFinal");
		GerenciaRegional gerenciaRegional = (GerenciaRegional) getParametro("gerenciaRegional");
		Categoria categoria = (Categoria) getParametro("categoria");
		Subcategoria subcategoria = (Subcategoria) getParametro("subcategoria");
		CobrancaSituacao cobrancaSituacao = (CobrancaSituacao) getParametro("cobrancaSituacao");
		Short indicadorMedicao = (Short) getParametro("indicadorMedicao");
		Short indicadorSituacaoImovelTarifaSocial = (Short) getParametro("indicadorSituacaoImovelTarifaSocial");
		TarifaSocialDadoEconomia tarifaSocialDadoEconomiaInicial = (TarifaSocialDadoEconomia) getParametro("tarifaSocialDadoEconomiaInicial");
		TarifaSocialDadoEconomia tarifaSocialDadoEconomiaFinal = (TarifaSocialDadoEconomia) getParametro("tarifaSocialDadoEconomiaFinal");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioDadosTarifaSocialBean relatorioBean = null;

		// se a cole��o de par�metros da analise n�o for vazia
		if (imoveisRelatoriosHelper != null
				&& !imoveisRelatoriosHelper.isEmpty()) {
			// coloca a cole��o de par�metros da analise no iterator
			Iterator imovelRelatorioIterator = imoveisRelatoriosHelper
					.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (imovelRelatorioIterator.hasNext()) {

				ImovelRelatorioHelper imovelRelatorioHelper = (ImovelRelatorioHelper) imovelRelatorioIterator
						.next();

				ImovelRelatorioHelper cliente = null;
				ImovelRelatorioHelper clienteUsuario = null;
				ImovelRelatorioHelper clienteProprietario = null;

				if (imovelRelatorioHelper.getClientes() != null
						&& !imovelRelatorioHelper.getClientes().isEmpty()) {

					// O Cliente Imovel foi encontrado
					Iterator clienteImovelIterator = imovelRelatorioHelper
							.getClientes().iterator();

					while (clienteImovelIterator.hasNext()) {
						cliente = (ImovelRelatorioHelper) clienteImovelIterator
								.next();

						// Trazer o cliente usu�rio do im�vel
						if (cliente.getClienteRelacaoTipo().equalsIgnoreCase(
								"USUARIO")) {
							clienteUsuario = cliente;
						}

						// Trazer o cliente respons�vel do im�vel
						if (cliente.getClienteRelacaoTipo().equalsIgnoreCase(
								"PROPRIETARIO")) {
							clienteProprietario = cliente;
						}
					}

				}

				// In�cio da Constru��o do objeto
				// RelatorioDadosTarifaSocialBean
				// para ser colocado no relat�rio
				relatorioBean = new RelatorioDadosTarifaSocialBean(

						// C�digo da Ger�ncia Regional
						imovelRelatorioHelper.getIdGerenciaRegional() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getIdGerenciaRegional(),

						// Descri��o da Ger�ncia Regional
						imovelRelatorioHelper.getDescricaoGerenciaRegional() == null ? ""
								: imovelRelatorioHelper
										.getDescricaoGerenciaRegional(),

						// C�digo da Localidade
						imovelRelatorioHelper.getIdLocalidade() == null ? ""
								: "" + imovelRelatorioHelper.getIdLocalidade(),

						// Descri��o da Localidade
						imovelRelatorioHelper.getDescricaoLocalidade() == null ? ""
								: imovelRelatorioHelper
										.getDescricaoLocalidade(),

						// C�digo do Setor Comercial
						imovelRelatorioHelper.getCodigoSetorComercial() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getCodigoSetorComercial(),

						// Descri��o do Setor Comercial
						imovelRelatorioHelper.getDescricaoSetorComercial() == null ? ""
								: imovelRelatorioHelper
										.getDescricaoSetorComercial(),

						// Matr�cula do Im�vel
						"" + imovelRelatorioHelper.getMatriculaImovel(),

						// Endere�o
						imovelRelatorioHelper.getEnderecoFormatado(),

						// Nome do Cliente Proprietario
						clienteProprietario == null ? "" : clienteProprietario
								.getClienteNome(),

						// Cpf do Cliente Proprietario
						clienteProprietario == null ? "" : ""
								+ clienteProprietario.getClienteCpf(),

						// Nome do Cliente Usu�rio
						clienteUsuario == null ? "" : clienteUsuario
								.getClienteNome(),

						// Cpf do Cliente Usu�rio
						clienteUsuario == null ? "" : ""
								+ clienteUsuario.getClienteCpf(),

						// Data da Implanta��o
						imovelRelatorioHelper.getDataImplantacao(),

						// Data da Exclus�o
						imovelRelatorioHelper.getDataExclusao(),

						// Motivo da Exclus�o
						imovelRelatorioHelper.getMotivoExclusao() == null ? ""
								: imovelRelatorioHelper.getMotivoExclusao(),

						// Data do �ltimo Recadastramento
						imovelRelatorioHelper.getUltimoRecadastramento(),

						// N�mero de Recadastramentos
						imovelRelatorioHelper.getNumeroRecadastramento() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getNumeroRecadastramento(),

						// �rea Constru�da
						imovelRelatorioHelper.getAreaConstruida() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getAreaConstruida(),

						// N�mero do IPTU
						imovelRelatorioHelper.getNumeroIptu() == null ? "" : ""
								+ imovelRelatorioHelper.getNumeroIptu()
										.toString(),

						// N�mero do Contrato da Celpe
						imovelRelatorioHelper.getNumeroCelpe() == null ? ""
								: "" + imovelRelatorioHelper.getNumeroCelpe(),

						// N�mero do Cart�o do Programa Social
						imovelRelatorioHelper.getNumeroCartaoTarifaSocial() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getNumeroCartaoTarifaSocial(),

						// Tipo do Cart�o do Programa Social
						imovelRelatorioHelper.getTipoCartaoTarifaSocial() == null ? ""
								: imovelRelatorioHelper
										.getTipoCartaoTarifaSocial(),

						// Data Validade do Cart�o do Programa Social
						imovelRelatorioHelper.getValidadeCartao(),

						// N�mero de Meses de Ades�o do Cart�o do Programa
						// Social
						imovelRelatorioHelper.getNumeroMesesAdesao() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getNumeroMesesAdesao(),

						// Consumo M�dio da Celpe
						imovelRelatorioHelper.getConsumoCelpe() == null ? ""
								: "" + imovelRelatorioHelper.getConsumoCelpe(),

						// Valor da Renda Familiar
						imovelRelatorioHelper.getValorRendaFamiliar() == null ? ""
								: imovelRelatorioHelper.getValorRendaFamiliar()
										.toString(),

						// Tipo da Renda Familiar
						imovelRelatorioHelper.getRendaTipo() == null ? ""
								: imovelRelatorioHelper.getRendaTipo());

				// Fim da Constru��o do objeto RelatorioManterImovelBean
				// para ser colocado no relat�rio

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		String dataImplantacaoInicial = null;
		if (tarifaSocialDadoEconomiaInicial.getTarifaSocialDado()
				.getDataImplantacao() != null
				&& !tarifaSocialDadoEconomiaInicial.getTarifaSocialDado()
						.getDataImplantacao().equals("")) {
			dataImplantacaoInicial = dataFormatada
					.format(tarifaSocialDadoEconomiaInicial
							.getTarifaSocialDado().getDataImplantacao());
		}

		String dataImplantacaoFinal = null;
		if (tarifaSocialDadoEconomiaFinal.getTarifaSocialDado()
				.getDataImplantacao() != null
				&& !tarifaSocialDadoEconomiaFinal.getTarifaSocialDado()
						.getDataImplantacao().equals("")) {
			dataImplantacaoFinal = dataFormatada
					.format(tarifaSocialDadoEconomiaFinal.getTarifaSocialDado()
							.getDataImplantacao());
		}

		String dataExclusaoInicial = null;
		if (tarifaSocialDadoEconomiaInicial.getTarifaSocialDado()
				.getDataExclusao() != null
				&& !tarifaSocialDadoEconomiaInicial.getTarifaSocialDado()
						.getDataExclusao().equals("")) {
			dataExclusaoInicial = dataFormatada
					.format(tarifaSocialDadoEconomiaInicial
							.getTarifaSocialDado().getDataExclusao());
		}

		String dataExclusaoFinal = null;
		if (tarifaSocialDadoEconomiaFinal.getTarifaSocialDado()
				.getDataExclusao() != null
				&& !tarifaSocialDadoEconomiaFinal.getTarifaSocialDado()
						.getDataExclusao().equals("")) {
			dataExclusaoFinal = dataFormatada
					.format(tarifaSocialDadoEconomiaFinal.getTarifaSocialDado()
							.getDataExclusao());
		}

		String dataValidadeCartaoInicial = null;
		if (tarifaSocialDadoEconomiaInicial.getDataValidadeCartao() != null
				&& !tarifaSocialDadoEconomiaInicial.getDataValidadeCartao()
						.equals("")) {
			dataValidadeCartaoInicial = dataFormatada
					.format(tarifaSocialDadoEconomiaInicial
							.getDataValidadeCartao());
		}

		String dataValidadeCartaoFinal = null;
		if (tarifaSocialDadoEconomiaFinal.getDataValidadeCartao() != null
				&& !tarifaSocialDadoEconomiaFinal.getDataValidadeCartao()
						.equals("")) {
			dataValidadeCartaoFinal = dataFormatada
					.format(tarifaSocialDadoEconomiaFinal
							.getDataValidadeCartao());
		}

		String dataRecadastramentoInicial = null;
		if (tarifaSocialDadoEconomiaInicial.getTarifaSocialDado()
				.getDataRecadastramento() != null
				&& !tarifaSocialDadoEconomiaInicial.getTarifaSocialDado()
						.getDataRecadastramento().equals("")) {
			dataRecadastramentoInicial = dataFormatada
					.format(tarifaSocialDadoEconomiaInicial
							.getTarifaSocialDado().getDataRecadastramento());
		}

		String dataRecadastramentoFinal = null;
		if (tarifaSocialDadoEconomiaFinal.getTarifaSocialDado()
				.getDataRecadastramento() != null
				&& !tarifaSocialDadoEconomiaFinal.getTarifaSocialDado()
						.getDataRecadastramento().equals("")) {
			dataRecadastramentoFinal = dataFormatada
					.format(tarifaSocialDadoEconomiaFinal.getTarifaSocialDado()
							.getDataRecadastramento());
		}

		parametros.put("gerenciaRegional", gerenciaRegional.getNomeAbreviado());
		parametros.put("idLocalidadeOrigem", imovelParametrosInicial
				.getLocalidade().getId() == null ? "" : ""
				+ imovelParametrosInicial.getLocalidade().getId());
		parametros.put("idLocalidadeDestino", imovelParametrosFinal
				.getLocalidade().getId() == null ? "" : ""
				+ imovelParametrosFinal.getLocalidade().getId());
		parametros.put("nomeLocalidadeOrigem", imovelParametrosInicial
				.getLocalidade().getDescricao());
		parametros.put("nomeLocalidadeDestino", imovelParametrosFinal
				.getLocalidade().getDescricao());
		parametros.put("idSetorComercialOrigem", imovelParametrosInicial
				.getSetorComercial().getId() == null ? "" : ""
				+ imovelParametrosInicial.getSetorComercial().getCodigo());
		parametros.put("idSetorComercialDestino", imovelParametrosFinal
				.getSetorComercial().getId() == null ? "" : ""
				+ imovelParametrosFinal.getSetorComercial().getCodigo());
		parametros.put("nomeSetorComercialOrigem", imovelParametrosInicial
				.getSetorComercial().getDescricao());
		parametros.put("nomeSetorComercialDestino", imovelParametrosFinal
				.getSetorComercial().getDescricao());
		parametros.put("numeroQuadraOrigem", imovelParametrosInicial
				.getQuadra().getNumeroQuadra() == 0 ? "" : ""
				+ imovelParametrosInicial.getQuadra().getNumeroQuadra());
		parametros.put("numeroQuadraDestino", imovelParametrosFinal.getQuadra()
				.getNumeroQuadra() == 0 ? "" : ""
				+ imovelParametrosFinal.getQuadra().getNumeroQuadra());
		parametros.put("loteOrigem",
				imovelParametrosInicial.getLote() == 0 ? "" : ""
						+ imovelParametrosInicial.getLote());
		parametros.put("loteDestino", imovelParametrosFinal.getLote() == 0 ? ""
				: "" + imovelParametrosFinal.getLote());
		parametros.put("idMunicipio", municipio.getId() == null ? "" : ""
				+ municipio.getId());
		parametros.put("nomeMunicipio", municipio.getNome());
		parametros.put("idBairro", bairro.getCodigo() == 0 ? "" : ""
				+ bairro.getCodigo());
		parametros.put("nomeBairro", bairro.getNome());
		parametros.put("cep",
				imovelParametrosInicial.getLogradouroCep().getCep().getCodigo() == null ? "" : ""
						+ imovelParametrosInicial.getLogradouroCep().getCep().getCodigo());
		parametros.put("idLogradouro", imovelParametrosInicial.getLogradouroCep().getLogradouro()
				.getId() == null ? "" : ""
				+ imovelParametrosInicial.getLogradouroCep().getLogradouro().getId());
		parametros.put("nomeLogradouro", imovelParametrosInicial
				.getLogradouroCep().getLogradouro().getNome());
		parametros.put("idCliente", clienteImovelParametros.getCliente()
				.getId() == null ? "" : ""
				+ clienteImovelParametros.getCliente().getId());
		parametros.put("nomeCliente", clienteImovelParametros.getCliente()
				.getNome());
		parametros.put("tipoRelacao", clienteImovelParametros
				.getClienteRelacaoTipo().getDescricao());
		parametros.put("tipoCliente", clienteImovelParametros.getCliente()
				.getClienteTipo().getDescricao());
		parametros.put("imovelCondominio", imovelParametrosInicial
				.getImovelCondominio().getId() == null ? "" : ""
				+ imovelParametrosInicial.getImovelCondominio().getId());
		parametros.put("imovelPrincipal", imovelParametrosInicial
				.getImovelPrincipal().getId() == null ? "" : ""
				+ imovelParametrosInicial.getImovelPrincipal().getId());
//		parametros.put("nomeConta", imovelParametrosInicial.getNomeConta()
//				.getNomeConta());
		parametros.put("situacaoLigacaoAgua", imovelParametrosInicial
				.getLigacaoAguaSituacao().getDescricao());
		parametros.put("situacaoLigacaoEsgoto", imovelParametrosInicial
				.getLigacaoEsgotoSituacao().getDescricao());
		parametros.put("consumoMinimoFixadoAguaInicial",
				imovelParametrosInicial.getLigacaoAgua()
						.getNumeroConsumoMinimoAgua() == null ? null : ""
						+ imovelParametrosInicial.getLigacaoAgua()
								.getNumeroConsumoMinimoAgua());
		parametros.put("consumoMinimoFixadoAguaFinal", imovelParametrosFinal
				.getLigacaoAgua().getNumeroConsumoMinimoAgua() == null ? null
				: ""
						+ imovelParametrosFinal.getLigacaoAgua()
								.getNumeroConsumoMinimoAgua());
		parametros.put("percentualEsgotoInicial", imovelParametrosInicial
				.getLigacaoEsgoto().getPercentual() == null ? null
				: imovelParametrosInicial.getLigacaoEsgoto().getPercentual()
						.toString());
		parametros.put("percentualEsgotoFinal", imovelParametrosFinal
				.getLigacaoEsgoto().getPercentual() == null ? null
				: imovelParametrosFinal.getLigacaoEsgoto().getPercentual()
						.toString());
		parametros
				.put("consumoMinimoFixadoEsgotoInicial",
						imovelParametrosInicial.getLigacaoEsgoto()
								.getConsumoMinimo() == null ? null : ""
								+ imovelParametrosInicial.getLigacaoEsgoto()
										.getConsumoMinimo());
		parametros.put("consumoMinimoFixadoEsgotoFinal", imovelParametrosFinal
				.getLigacaoEsgoto().getConsumoMinimo() == null ? null : ""
				+ imovelParametrosFinal.getLigacaoEsgoto().getConsumoMinimo());
		parametros.put("indicadorMedicao", indicadorMedicao == null ? ""
				: indicadorMedicao.equals(new Short("0")) ? "SEM MEDI��O"
						: "COM MEDI��O");
		parametros.put("tipoMedicao", medicaoHistoricoParametrosInicial
				.getMedicaoTipo().getDescricao());
		parametros
				.put(
						"mediaMinimaImovelInicial",
						consumoHistoricoParametrosInicial.getConsumoMedio() == null ? null
								: ""
										+ consumoHistoricoParametrosInicial
												.getConsumoMedio());
		parametros
				.put("mediaMinimaImovelFinal", consumoHistoricoParametrosFinal
						.getConsumoMedio() == null ? null : ""
						+ consumoHistoricoParametrosFinal.getConsumoMedio());
		parametros
				.put("mediaMinimaHidrometroInicial",
						medicaoHistoricoParametrosInicial
								.getConsumoMedioHidrometro() == null ? null
								: ""
										+ medicaoHistoricoParametrosInicial
												.getConsumoMedioHidrometro());
		parametros
				.put("mediaMinimaHidrometroFinal",
						medicaoHistoricoParametrosFinal
								.getConsumoMedioHidrometro() == null ? null
								: ""
										+ medicaoHistoricoParametrosFinal
												.getConsumoMedioHidrometro());
		parametros.put("perfilImovel", imovelParametrosInicial
				.getImovelPerfil().getDescricao());
		parametros.put("categoria", categoria.getDescricao());
		parametros.put("subCategoria", subcategoria.getDescricao());
		parametros.put("qtdeEconomiaInicial", imovelParametrosInicial
				.getQuantidadeEconomias() == null ? null : ""
				+ imovelParametrosInicial.getQuantidadeEconomias());
		parametros.put("qtdeEconomiaFinal", imovelParametrosFinal
				.getQuantidadeEconomias() == null ? null : ""
				+ imovelParametrosFinal.getQuantidadeEconomias());
		parametros.put("numeroPontosInicial", imovelParametrosInicial
				.getNumeroPontosUtilizacao() == 0 ? null : ""
				+ imovelParametrosInicial.getNumeroPontosUtilizacao());
		parametros.put("numeroPontosFinal", imovelParametrosFinal
				.getNumeroPontosUtilizacao() == 0 ? null : ""
				+ imovelParametrosFinal.getNumeroPontosUtilizacao());
		parametros.put("numeroMoradoresInicial", imovelParametrosInicial
				.getNumeroMorador() == 0 ? null : ""
				+ imovelParametrosInicial.getNumeroMorador());
		parametros.put("numeroMoradoresFinal", imovelParametrosFinal
				.getNumeroMorador() == 0 ? null : ""
				+ imovelParametrosFinal.getNumeroMorador());
		parametros.put("areaConstruidaInicial", imovelParametrosInicial
				.getAreaConstruida().equals(new Short("0")) ? null : ""
				+ imovelParametrosInicial.getAreaConstruida());
		parametros.put("areaConstruidaFinal", imovelParametrosFinal
				.getAreaConstruida().equals(new Short("0")) ? null : ""
				+ imovelParametrosFinal.getAreaConstruida());
		parametros.put("tipoPoco", imovelParametrosInicial.getPocoTipo()
				.getDescricao());
		parametros.put("tipoSituacaoEspecialFaturamento",
				imovelParametrosInicial.getFaturamentoSituacaoTipo()
						.getDescricao());
		parametros.put("tipoSituacaoEspecialCobranca", imovelParametrosInicial
				.getCobrancaSituacaoTipo().getDescricao());
		parametros.put("situacaoCobranca", cobrancaSituacao == null ? ""
				: cobrancaSituacao.getDescricao());
		parametros.put("diaVencimentoAlternativo", imovelParametrosInicial
				.getDiaVencimento() == null ? "" : ""
				+ imovelParametrosInicial.getDiaVencimento());
		parametros.put("anormalidadeElo", imovelParametrosInicial
				.getEloAnormalidade() == null ? "" : imovelParametrosInicial
				.getEloAnormalidade().getDescricao());
		parametros.put("ocorrenciaCadastro", imovelParametrosInicial
				.getCadastroOcorrencia().getDescricao());
		parametros.put("tarifaConsumo", imovelParametrosInicial
				.getConsumoTarifa().getDescricao());
		parametros.put("indicadorSituacaoImovelTarifaSocial",
				indicadorSituacaoImovelTarifaSocial == null ? ""
						: indicadorSituacaoImovelTarifaSocial == 1 ? "Ativo"
								: "Exclu�do");
		parametros.put("dataImplantacaoInicial", dataImplantacaoInicial);
		parametros.put("dataImplantacaoFinal", dataImplantacaoFinal);
		parametros.put("dataExclusaoInicial", dataExclusaoInicial);
		parametros.put("dataExclusaoFinal", dataExclusaoFinal);
		parametros.put("dataValidadeCartaoInicial", dataValidadeCartaoInicial);
		parametros.put("dataValidadeCartaoFinal", dataValidadeCartaoFinal);
		parametros
				.put("motivoExclusao",
						tarifaSocialDadoEconomiaInicial.getTarifaSocialDado()
								.getTarifaSocialExclusaoMotivo() == null ? ""
								: tarifaSocialDadoEconomiaInicial
										.getTarifaSocialDado()
										.getTarifaSocialExclusaoMotivo()
										.getDescricao());
		parametros
				.put(
						"numeroMesesAdesaoInicial",
						tarifaSocialDadoEconomiaInicial.getNumeroMesesAdesao() == 0 ? null
								: ""
										+ tarifaSocialDadoEconomiaInicial
												.getNumeroMesesAdesao());
		parametros.put("numeroMesesAdesaoFinal", tarifaSocialDadoEconomiaFinal
				.getNumeroMesesAdesao() == 0 ? null : ""
				+ tarifaSocialDadoEconomiaFinal.getNumeroMesesAdesao());
		parametros.put("tipoCartao", tarifaSocialDadoEconomiaInicial
				.getTarifaSocialCartaoTipo().getDescricao() == null ? ""
				: tarifaSocialDadoEconomiaInicial.getTarifaSocialCartaoTipo()
						.getDescricao());
		parametros
				.put("tipoRenda", tarifaSocialDadoEconomiaInicial
						.getRendaTipo().getDescricao() == null ? ""
						: tarifaSocialDadoEconomiaInicial.getRendaTipo()
								.getDescricao());
		parametros.put("rendaFamiliarInicial", tarifaSocialDadoEconomiaInicial
				.getValorRendaFamiliar() == null ? null
				: tarifaSocialDadoEconomiaInicial.getValorRendaFamiliar()
						.toString());
		parametros.put("rendaFamiliarFinal", tarifaSocialDadoEconomiaFinal
				.getValorRendaFamiliar() == null ? null
				: tarifaSocialDadoEconomiaFinal.getValorRendaFamiliar()
						.toString());
		parametros.put("consumoCelpeInicial", tarifaSocialDadoEconomiaInicial
				.getConsumoCelpe() == null ? null
				: tarifaSocialDadoEconomiaInicial.getConsumoCelpe().toString());
		parametros.put("consumoCelpeFinal", tarifaSocialDadoEconomiaFinal
				.getConsumoCelpe() == null ? null
				: tarifaSocialDadoEconomiaFinal.getConsumoCelpe().toString());
		parametros
				.put("dataRecadastramentoInicial", dataRecadastramentoInicial);
		parametros.put("dataRecadastramentoFinal", dataRecadastramentoFinal);
		parametros.put("numeroRecadastramentoInicial",
				tarifaSocialDadoEconomiaInicial.getTarifaSocialDado()
						.getQuantidadeRecadastramento() == null ? null : ""
						+ tarifaSocialDadoEconomiaInicial.getTarifaSocialDado()
								.getQuantidadeRecadastramento());
		parametros.put("numeroRecadastramentoFinal",
				tarifaSocialDadoEconomiaFinal.getTarifaSocialDado()
						.getQuantidadeRecadastramento() == null ? null : ""
						+ tarifaSocialDadoEconomiaFinal.getTarifaSocialDado()
								.getQuantidadeRecadastramento());
		parametros.put("tipoFormatoRelatorio", "R0162");

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		// exporta o relat�rio em pdf e retorna o array de bytes
		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_DADOS_TARIFA_SOCIAL, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.DADOS_TARIFA_SOCIAL,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;*/
		return null;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDadosTarifaSocial", this);
	}

}
