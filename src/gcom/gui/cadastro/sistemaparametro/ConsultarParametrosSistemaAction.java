package gcom.gui.cadastro.sistemaparametro;

import gcom.arrecadacao.banco.ContaBancaria;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 10/01/2007
 */
public class ConsultarParametrosSistemaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = this.getSessao(httpServletRequest);

		ConsultarParametrosSistemaActionForm form = (ConsultarParametrosSistemaActionForm) actionForm;

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		SistemaParametro sistemaParametro = (SistemaParametro) sessao
				.getAttribute("sistemaParametro");

		this.montarSistemaParametro(form, sistemaParametro);

		// Recupera os dados
		if (form.getIndicadorSugestaoTramite() != null
				&& !form.getIndicadorSugestaoTramite().trim()
						.equalsIgnoreCase("")) {

			sistemaParametro.setIndicadorSugestaoTramite(new Short(form
					.getIndicadorSugestaoTramite()));

		}

		if (form.getDiasMaximoReativarRA() != null
				&& !form.getDiasMaximoReativarRA().trim().equalsIgnoreCase("")) {

			sistemaParametro.setDiasReativacao(new Short(form
					.getDiasMaximoReativarRA()));

		}

		if (form.getDiasMaximoAlterarOS() != null
				&& !form.getDiasMaximoAlterarOS().trim().equalsIgnoreCase("")) {

			sistemaParametro.setDiasMaximoAlterarOS(new Integer(form
					.getDiasMaximoAlterarOS()));

		}

		if (form.getUltimoIDGeracaoRA() != null
				&& !form.getUltimoIDGeracaoRA().trim().equalsIgnoreCase("")) {

			sistemaParametro.setUltimoRAManual(new Integer(form
					.getUltimoIDGeracaoRA()));

		}

		if (form.getDiasMaximoExpirarAcesso() != null
				&& !form.getDiasMaximoExpirarAcesso().trim()
						.equalsIgnoreCase("")) {

			sistemaParametro.setNumeroDiasExpiracaoAcesso(new Short(form
					.getDiasMaximoExpirarAcesso()));

		}

		if (form.getDiasMensagemExpiracaoSenha() != null
				&& !form.getDiasMensagemExpiracaoSenha().trim()
						.equalsIgnoreCase("")) {

			sistemaParametro.setNumeroDiasMensagemExpiracao(new Short(form
					.getDiasMensagemExpiracaoSenha()));

		}

		if (form.getNumeroMaximoTentativasAcesso() != null
				&& !form.getNumeroMaximoTentativasAcesso().trim()
						.equalsIgnoreCase("")) {

			sistemaParametro.setNumeroMaximoLoginFalho(new Short(form
					.getNumeroMaximoTentativasAcesso()));

		}

		if (form.getNumeroMaximoFavoritosMenu() != null
				&& !form.getNumeroMaximoFavoritosMenu().trim()
						.equalsIgnoreCase("")) {
			sistemaParametro.setNumeroMaximoFavorito(new Integer(form
					.getNumeroMaximoFavoritosMenu()));
		}

		if (form.getIpServidorSmtp() != null
				&& !form.getIpServidorSmtp().trim().equalsIgnoreCase("")) {
			sistemaParametro.setIpServidorSmtp(form.getIpServidorSmtp());
		}

		if (form.getEmailResponsavel() != null
				&& !form.getEmailResponsavel().trim().equalsIgnoreCase("")) {
			sistemaParametro.setDsEmailResponsavel(form.getEmailResponsavel());
		}

		this.getFachada().informarParametrosSistema(sistemaParametro, usuario);

		montarPaginaSucesso(httpServletRequest,
				"Par�metro do Sistema informado com sucesso.",
				"Informar outro Parametro do Sistema",
				"exibirInformarParametrosSistemaAction.do?menu=sim");

		return retorno;

	}

	/**
	 * Valida o Campo
	 * 
	 * @author Vinicius Medeiros
	 * @date 30/07/2008
	 */
	private boolean validaCampo(String campo) {
		boolean retorno = false;

		if (campo != null && !campo.equals("") && !campo.equals("-1")) {
			retorno = true;
		}

		return retorno;
	}

	/**
	 * Cria os objetos na tela
	 * 
	 * @author Vinicius Medeiros
	 * @date 30/07/2008
	 */
	private void montarSistemaParametro(
			ConsultarParametrosSistemaActionForm form,
			SistemaParametro sistemaParametro) {

		// Nome do Estado
		if (validaCampo(form.getNomeEstado())) {
			sistemaParametro.setNomeEstado(form.getNomeEstado());
		}

		// Nome da Empresa
		if (validaCampo(form.getNomeEmpresa())) {
			sistemaParametro.setNomeEmpresa(form.getNomeEmpresa());
		}

		// Abreviatura da Empresa
		if (validaCampo(form.getAbreviaturaEmpresa())) {
			sistemaParametro.setNomeAbreviadoEmpresa(form
					.getAbreviaturaEmpresa());
		}

		// CNPJ da Empresa
		if (validaCampo(form.getCnpj())) {
			sistemaParametro.setCnpjEmpresa(form.getCnpj());
		}

		// Inscricao Estadual
		if (validaCampo(form.getInscricaoEstadual())) {
			sistemaParametro.setInscricaoEstadual(form.getInscricaoEstadual());
		}

		// Inscricao Municipal
		if (validaCampo(form.getInscricaoMunicipal())) {
			sistemaParametro
					.setInscricaoMunicipal(form.getInscricaoMunicipal());
		}

		// N�mero do Contrato
		if (validaCampo(form.getNumeroContrato())) {
			sistemaParametro.setNumeroContratoPrestacaoServico(form
					.getNumeroContrato());
		}

		// Unidade Organizacional
		if (validaCampo(form.getUnidadeOrganizacionalPresidencia())) {

			UnidadeOrganizacional unidade = new UnidadeOrganizacional();
			unidade.setId(new Integer(form
					.getUnidadeOrganizacionalPresidencia()));

			sistemaParametro.setUnidadeOrganizacionalIdPresidencia(unidade);
		}

		// Presidente
		if (validaCampo(form.getPresidente())) {
			Cliente cliente = new Cliente();
			cliente.setId(new Integer(form.getPresidente()));

			sistemaParametro.setClientePresidente(cliente);
		}

		// Diretor Comercial
		if (validaCampo(form.getDiretorComercial())) {

			Cliente cliente = new Cliente();
			cliente.setId(new Integer(form.getDiretorComercial()));

			sistemaParametro.setClienteDiretorComercial(cliente);
		}

		// Numero
		if (validaCampo(form.getNumero())) {
			sistemaParametro.setNumeroImovel(form.getNumero());
		}

		// Complemento
		if (validaCampo(form.getComplemento())) {
			sistemaParametro.setComplementoEndereco(form.getComplemento());
		}

		// Endereco Referencia
		if (validaCampo(form.getEnderecoReferencia())) {

			EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
			enderecoReferencia.setId(new Integer(form.getEnderecoReferencia()));

			sistemaParametro.setEnderecoReferencia(enderecoReferencia);
		}

		// Numero Telefone
		if (validaCampo(form.getNumeroTelefone())) {
			sistemaParametro.setNumeroTelefone(form.getNumeroTelefone());
		}

		// Ramal
		if (validaCampo(form.getRamal())) {
			sistemaParametro.setNumeroRamal(form.getRamal());
		}

		// Fax
		if (validaCampo(form.getFax())) {
			sistemaParametro.setNumeroFax(form.getFax());
		}

		// Email
		if (validaCampo(form.getEmail())) {
			sistemaParametro.setDescricaoEmail(form.getEmail());
		}

		// Numero de Atendimento
		if (validaCampo(form.getNumeroTelefoneAtendimento())) {
			sistemaParametro.setNumero0800Empresa(form
					.getNumeroTelefoneAtendimento());
		}

		// Variar Hierarquia da Unidade Organizacional
		if (validaCampo(form.getIndicadorVariaHierarquiaUnidade())){
			sistemaParametro.setIndicadorVariaHierarquiaUnidade(new Short(form.getIndicadorVariaHierarquiaUnidade()));
		}
		//Cliente Ficticio  Associar Pagamentos  Nao Identificados  em Curso
		if (validaCampo(form.getClienteFicticioAssociarPagamentosNaoIdentificados())) {
			Cliente cliente = new Cliente();
			cliente.setId(new Integer(form.getClienteFicticioAssociarPagamentosNaoIdentificados()));

			sistemaParametro.setClienteFicticioParaAssociarOsPagamentosNaoIdentificados(cliente);
		}
		
		// Titulo do Relatorio
		if (validaCampo(form.getTitulosRelatorio())) {
			sistemaParametro.setTituloPagina(form.getTitulosRelatorio());
		}

		// Caminho Imagem da Logomarca
		if (validaCampo(form.getImagemLogomarca())) {
			sistemaParametro.setImagemLogomarca(form.getImagemLogomarca());
		}

		// Caminho Imagem do Relatorio
		if (validaCampo(form.getImagemRelatorio())) {
			sistemaParametro.setImagemRelatorio(form.getImagemRelatorio());
		}

		// Caminho Imagem da Conta
		if (validaCampo(form.getImagemConta())) {
			sistemaParametro.setImagemConta(form.getImagemConta());
		}

		// M�s/Ano Referencia
		if (validaCampo(form.getMesAnoReferencia())) {

			boolean mesAnoValido = Util.validarMesAno(form
					.getMesAnoReferencia());

			if (mesAnoValido == false) {
				throw new ActionServletException(
						"atencao.ano_mes_referencia.invalida");
			}

			String mes = form.getMesAnoReferencia().substring(0, 2);
			String ano = form.getMesAnoReferencia().substring(3, 7);

			Integer anoMesReferenciaFaturamento = new Integer(ano + mes);

			sistemaParametro.setAnoMesFaturamento(anoMesReferenciaFaturamento);
		}

		// Menor Consumo para ser Grande Usuario
		if (validaCampo(form.getMenorConsumo())) {
			sistemaParametro.setMenorConsumoGrandeUsuario(new Integer(form
					.getMenorConsumo()));
		}

		// Menor Valor para Emissao de Contas
		if (validaCampo(form.getMenorValor())) {

			BigDecimal valorMinimoEmissaoConta = new BigDecimal(0);

			String valorAux = form.getMenorValor().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			valorMinimoEmissaoConta = new BigDecimal(valorAux);
			sistemaParametro
					.setValorMinimoEmissaoConta(valorMinimoEmissaoConta);
		}

		// Qtde de Economias para Ser Grande Usuario
		if (validaCampo(form.getQtdeEconomias())) {
			sistemaParametro.setMenorEconomiasGrandeUsuario(new Short(form
					.getQtdeEconomias()));
		}

		// Meses para Calculo de Media de Consumo
		if (validaCampo(form.getMesesCalculoMedio())) {
			sistemaParametro.setMesesMediaConsumo(new Short(form
					.getMesesCalculoMedio()));
		}

		// Dias Minimo para Calcular Vencimento
		if (validaCampo(form.getDiasMinimoVencimento())) {
			sistemaParametro.setNumeroMinimoDiasEmissaoVencimento(new Short(
					form.getDiasMinimoVencimento()));
		}

		// Dias Minimo para Caluar Vencimento se entrega para os correios
		if (validaCampo(form.getDiasMinimoVencimentoCorreio())) {
			sistemaParametro.setNumeroDiasAdicionaisCorreios(new Short(form
					.getDiasMinimoVencimentoCorreio()));
		}

		// Numero de meses para validade de conta
		if (validaCampo(form.getNumeroMesesValidadeConta())) {
			sistemaParametro.setNumeroMesesValidadeConta(new Short(form
					.getNumeroMesesValidadeConta()));
		}

		// Numero de meses para alteracao do vencimento para outro
		if (validaCampo(form.getNumeroMesesAlteracaoVencimento())) {
			sistemaParametro.setNumeroMesesMinimoAlteracaoVencimento(new Short(
					form.getNumeroMesesAlteracaoVencimento()));
		}

		// Indicador Alteracao do Vencimento mais de uma vez
		if (validaCampo(form.getIndicadorLimiteAlteracaoVencimento())) {
			sistemaParametro.setIndicadorLimiteAlteracaoVencimento(new Short(
					form.getIndicadorLimiteAlteracaoVencimento()));
		}

		// Indicador Calculo feito pelo sistema
		if (validaCampo(form.getIndicadorCalculoVencimento())) {
			sistemaParametro.setIndicadorCalculaVencimento(new Short(form
					.getIndicadorCalculoVencimento()));
		}

		// Indicador tipo de tarifa de consumo
		if (validaCampo(form.getIndicadorTarifaCategoria())) {
			sistemaParametro.setIndicadorTarifaCategoria(new Short(form
					.getIndicadorTarifaCategoria()));
		}

		// Indicador de Atualiza��o Tarif�ria
		if (validaCampo(form.getIndicadorAtualizacaoTarifaria())) {
			sistemaParametro.setIndicadorAtualizacaoTarifaria(new Short(form
					.getIndicadorAtualizacaoTarifaria()));
		}

		// Indicador Usa Rota
		if (validaCampo(form.getIndicadorUsaRota())) {
			sistemaParametro.setIndicadorUsaRota(new Short(form
					.getIndicadorUsaRota()));
		}

		// Indicador Login Unico
		if (validaCampo(form.getIndicadorLoginUnico())) {
			sistemaParametro.setIndicadorLoginUnico(new Short(form
					.getIndicadorLoginUnico()));
		}

		// M�s/Ano Atualiza��o Tarif�ria
		if (validaCampo(form.getMesAnoAtualizacaoTarifaria())) {

			boolean mesAnoValido = Util.validarMesAno(form
					.getMesAnoAtualizacaoTarifaria());

			if (mesAnoValido == false) {
				throw new ActionServletException(
						"atencao.ano_mes_referencia.invalida");
			}

			String mes = form.getMesAnoReferencia().substring(0, 2);
			String ano = form.getMesAnoReferencia().substring(3, 7);

			Integer anoMes = new Integer(ano + mes);

			sistemaParametro.setAnoMesAtualizacaoTarifaria(anoMes);
		}

		// Indicador de Faturamento Antecipado
		if (validaCampo(form.getIndicadorFaturamentoAntecipado())) {
			sistemaParametro.setIndicadorFaturamentoAntecipado(new Short(form
					.getIndicadorFaturamentoAntecipado()));
		}

		// Salario Minimo
		if (validaCampo(form.getSalarioMinimo())) {

			BigDecimal valorValorSalarioMinimo = new BigDecimal(0);

			String valorAux = form.getSalarioMinimo().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");
			valorValorSalarioMinimo = new BigDecimal(valorAux);

			sistemaParametro.setValorSalarioMinimo(valorValorSalarioMinimo);
		}

		// Area Maxima do Imovel tarifa social
		if (validaCampo(form.getAreaMaxima())) {
			sistemaParametro.setAreaMaximaTarifaSocial(new Integer(form
					.getAreaMaxima()));
		}

		// Consumo de Energia Maxima
		if (validaCampo(form.getConsumoMaximo())) {
			sistemaParametro.setConsumoEnergiaMaximoTarifaSocial(new Integer(
					form.getConsumoMaximo()));
		}

		// M�s e Ano de Referencia
		if (validaCampo(form.getMesAnoReferenciaArrecadacao())) {

			boolean mesAnoValido = Util.validarMesAno(form
					.getMesAnoReferenciaArrecadacao());

			if (mesAnoValido == false) {
				throw new ActionServletException(
						"atencao.ano_mes_referencia.invalida");
			}

			String mes = form.getMesAnoReferenciaArrecadacao().substring(0, 2);
			String ano = form.getMesAnoReferenciaArrecadacao().substring(3, 7);

			Integer anoMesReferenciaArrecadacao = new Integer(ano + mes);

			sistemaParametro.setAnoMesArrecadacao(anoMesReferenciaArrecadacao);
		}

		// C�digo da Empresa para FEBRABAN
		if (validaCampo(form.getCodigoEmpresaFebraban())) {
			sistemaParametro.setCodigoEmpresaFebraban(new Short(form
					.getCodigoEmpresaFebraban()));
		}

		// N�mero do Lay-out
		if (validaCampo(form.getNumeroLayOut())) {
			sistemaParametro.setNumeroLayoutFebraban(new Short(form
					.getNumeroLayOut()));
		}

		// Identificador de Conta Bancaria
		if (validaCampo(form.getIndentificadorContaDevolucao())) {

			ContaBancaria contaBancaria = new ContaBancaria();

			contaBancaria.setId(new Integer(form
					.getIndentificadorContaDevolucao()));
			sistemaParametro.setContaBancaria(contaBancaria);
		}

		// Percentual de Entrada Minima
		if (validaCampo(form.getPercentualEntradaMinima())) {

			BigDecimal percentualEntradaMinima = new BigDecimal(0);

			String valorAux = form.getPercentualEntradaMinima().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualEntradaMinima = new BigDecimal(valorAux);
			sistemaParametro
					.setPercentualFinanciamentoEntradaMinima(percentualEntradaMinima);
		}

		// Maximo de Parcelas
		if (validaCampo(form.getMaximoParcelas())) {
			sistemaParametro.setNumeroMaximoParcelasFinanciamento(new Short(
					form.getMaximoParcelas()));
		}

		// Percentual Maximo
		if (validaCampo(form.getPercentualMaximoAbatimento())) {

			BigDecimal percentualMaximoAbatimento = new BigDecimal(0);

			String valorAux = form.getPercentualMaximoAbatimento().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualMaximoAbatimento = new BigDecimal(valorAux);
			sistemaParametro
					.setPercentualMaximoAbatimento(percentualMaximoAbatimento);
		}

		// Percentual de Taxa
		if (validaCampo(form.getPercentualTaxaFinanciamento())) {

			BigDecimal percentualTaxaFinanciamento = new BigDecimal(0);

			String valorAux = form.getPercentualTaxaFinanciamento().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualTaxaFinanciamento = new BigDecimal(valorAux);
			sistemaParametro
					.setPercentualTaxaJurosFinanciamento(percentualTaxaFinanciamento);
		}

		// Numero Maximo de Parcelas
		if (validaCampo(form.getNumeroMaximoParcelaCredito())) {
			sistemaParametro.setNumeroMaximoParcelaCredito(new Short(form
					.getNumeroMaximoParcelaCredito()));
		}

		// Percentual da M�dia do �ndice
		if (validaCampo(form.getPercentualCalculoIndice())) {

			BigDecimal percentualCalculoIndice = new BigDecimal(0);

			String valorAux = form.getPercentualCalculoIndice().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualCalculoIndice = new BigDecimal(valorAux);
			sistemaParametro.setPercentualMediaIndice(percentualCalculoIndice);
		}

		// Codigo da Menor Capacidade
		if (validaCampo(form.getCodigoMenorCapacidade())) {

			HidrometroCapacidade hidrometroCapacidade = new HidrometroCapacidade();
			hidrometroCapacidade.setId(new Integer(form
					.getCodigoMenorCapacidade()));

			sistemaParametro.setHidrometroCapacidade(hidrometroCapacidade);
		}

		// Indicador de Gera��o de Faixa Falsa
		if (validaCampo(form.getIndicadorGeracaoFaixaFalsa())) {
			sistemaParametro.setIndicadorFaixaFalsa(new Short(form
					.getIndicadorGeracaoFaixaFalsa()));
		}

		// Indicador do Percentual para Gera��o
		if (validaCampo(form.getIndicadorPercentualGeracaoFaixaFalsa())) {
			sistemaParametro.setIndicadorUsoFaixaFalsa(new Short(form
					.getIndicadorPercentualGeracaoFaixaFalsa()));
		}

		// Percentual de Gera��o de Faixa
		if (validaCampo(form.getPercentualGeracaoFaixaFalsa())) {

			BigDecimal percentualGeracaoFaixaFalsa = new BigDecimal(0);

			String valorAux = form.getPercentualGeracaoFaixaFalsa().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualGeracaoFaixaFalsa = new BigDecimal(valorAux);
			sistemaParametro
					.setPercentualFaixaFalsa(percentualGeracaoFaixaFalsa);
		}

		// Indicador de Gera��o de Fiscaliza��o
		if (validaCampo(form.getIndicadorGeracaoFiscalizacaoLeitura())) {
			sistemaParametro
					.setIndicadorPercentualFiscalizacaoLeitura(new Short(form
							.getIndicadorPercentualGeracaoFiscalizacaoLeitura()));
		}

		// Indicador do Percentual Gera��o
		if (validaCampo(form.getIndicadorPercentualGeracaoFiscalizacaoLeitura())) {
			sistemaParametro.setIndicadorUsoFiscalizadorLeitura(new Short(form
					.getIndicadorGeracaoFiscalizacaoLeitura()));
		}

		// Percentual de Gera��o de Fiscaliza��o
		if (validaCampo(form.getPercentualGeracaoFiscalizacaoLeitura())) {

			BigDecimal percentualGeracaoFiscalizacaoLeitura = new BigDecimal(0);

			String valorAux = form.getPercentualGeracaoFiscalizacaoLeitura()
					.toString().replace(".", "");

			valorAux = valorAux.replace(",", ".");

			percentualGeracaoFiscalizacaoLeitura = new BigDecimal(valorAux);

			sistemaParametro
					.setPercentualFiscalizacaoLeitura(percentualGeracaoFiscalizacaoLeitura);

		}

		// Incremento M�ximo de Consumo
		if (validaCampo(form.getIncrementoMaximoConsumo())) {
			sistemaParametro.setIncrementoMaximoConsumoRateio(new Integer(form
					.getIncrementoMaximoConsumo()));
		}

		// Decremento M�ximo de Consumo
		if (validaCampo(form.getDecrementoMaximoConsumo())) {
			sistemaParametro.setDecrementoMaximoConsumoRateio(new Integer(form
					.getDecrementoMaximoConsumo()));
		}

		// Percentual de Tolerancia
		if (validaCampo(form.getPercentualToleranciaRateioConsumo())) {

			BigDecimal percentualToleranciaRateioConsumo = new BigDecimal(0);

			String valorAux = form.getPercentualToleranciaRateioConsumo()
					.toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualToleranciaRateioConsumo = new BigDecimal(valorAux);
			sistemaParametro
					.setPercentualToleranciaRateio(percentualToleranciaRateioConsumo);
		}

		// Numero de Dias entre o Vencimento
		if (validaCampo(form.getDiasVencimentoCobranca())) {
			sistemaParametro.setNumeroDiasVencimentoCobranca(new Short(form
					.getDiasVencimentoCobranca()));
		}

		// N�mero M�ximo de Meses de San��es
		if (validaCampo(form.getNumeroMaximoMesesSancoes())) {
			sistemaParametro.setNumeroMaximoMesesSancoes(new Short(form
					.getNumeroMaximoMesesSancoes()));
		}

		// Valor da Segunda Via
		if (validaCampo(form.getValorSegundaVia())) {

			String valorAux = form.getValorSegundaVia().toString()
					.replace(".", "");
			valorAux = valorAux.replace(",", ".");

			sistemaParametro.setPercentualToleranciaRateio(new BigDecimal(
					valorAux));
		}

		// Indicador de Cobran�a da Taxa de Extrato
		if (validaCampo(form.getIndicadorCobrarTaxaExtrato())) {
			sistemaParametro.setIndicadorCobrarTaxaExtrato(new Short(form
					.getIndicadorCobrarTaxaExtrato()));
		}

		// Indicador de Cobran�a da Taxa de Extrato
		if (validaCampo(form.getNumeroDiasVariacaoConsumo())) {
			sistemaParametro.setNumeroDiasVariacaoConsumo(new Short(form
					.getNumeroDiasVariacaoConsumo()));
		}

		// C�digo da Periodicidade da Negativacao
		if (validaCampo(form.getCodigoPeriodicidadeNegativacao())) {
			sistemaParametro.setCodigoPeriodicidadeNegativacao(new Short(form
					.getCodigoPeriodicidadeNegativacao()));
		}

		// N�mero de Dias para Calculo de Adicionais de Impontualidade
		if (validaCampo(form.getNumeroDiasCalculoAcrescimos())) {
			sistemaParametro.setNumeroDiasCalculoAcrescimos(new Short(form
					.getNumeroDiasCalculoAcrescimos()));
		}

		// N�mero de dias de prazo para entrada de recurso do auto de infra��o
		if (validaCampo(form.getNnDiasPrazoRecursoAutoInfracao())) {
			sistemaParametro.setNumeroDiasPrazoRecursoAutoInfracao(new Integer(
					form.getNnDiasPrazoRecursoAutoInfracao()));
		}

		// Indicador do valor do movimento arrecadador.
		if (validaCampo(form.getIndicadorValorMovimentoArrecadador())) {
			sistemaParametro.setIndicadorValorMovimentoArrecadador(new Integer(
					form.getIndicadorValorMovimentoArrecadador()));
		}

		// Indicador do numero de Dias do Bloqueio Celular
		if (validaCampo(form.getNumeroDiasBloqueioCelular())) {
			sistemaParametro.setNumeroDiasBloqueioCelular(new Integer(form
					.getNumeroDiasBloqueioCelular()));
		}
		
		// N�mero de dias para validade ordem de fiscaliza��o
		if(validaCampo(form.getQtdeDiasValidadeOSFiscalizacao())){
			sistemaParametro.setQtdeDiasValidadeOSFiscalizacao(new Integer(form
					.getQtdeDiasValidadeOSFiscalizacao()));
		}

		// N�mero m�ximo de dias para uma ordem de servi�o ser fiscalizada
		if(validaCampo(form.getQtdeDiasEncerraOSFiscalizacao())){
			sistemaParametro.setQtdeDiasEncerraOSFiscalizacao(new Integer(form
					.getQtdeDiasEncerraOSFiscalizacao()));
		}
		
		//	N�mero de dias para envio de conta por email
		if(validaCampo(form.getQtdeDiasEnvioEmailConta())){
			sistemaParametro.setQtdeDiasEnvioEmailConta(new Integer(form
					.getQtdeDiasEnvioEmailConta()));
		}		
	}
}
