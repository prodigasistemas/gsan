package gcom.gui.cadastro.sistemaparametro;

import gcom.arrecadacao.banco.ContaBancaria;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class InformarParametrosSistemaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = this.getSessao(httpServletRequest);
		InformarSistemaParametrosActionForm form = (InformarSistemaParametrosActionForm) actionForm;

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		SistemaParametro sistemaParametro = (SistemaParametro) sessao.getAttribute("sistemaParametro");

		// 1ª Aba
		this.montarSistemaParametro1Aba(form, sistemaParametro);

		// 2ª aba
		this.montarSistemaParametro2Aba(form, sistemaParametro);

		// 3ª aba
		this.montarSistemaParametro3Aba(form, sistemaParametro);

		// 4ª Aba
		this.montarSistemaParametro4Aba(form, sistemaParametro);

		// 5ª aba
		this.montarSistemaParametro5Aba(form, sistemaParametro);

		this.getFachada().informarParametrosSistema(sistemaParametro, usuario);

		montarPaginaSucesso(httpServletRequest, "Parâmetro do Sistema informado com sucesso.",
				"Informar outro Parametro do Sistema", "exibirInformarParametrosSistemaAction.do?menu=sim");

		return retorno;
	}

	private boolean validaCampo(String campo) {
		boolean retorno = false;

		if (campo != null && !campo.equals("") && !campo.equals("-1")) {
			retorno = true;
		}

		return retorno;
	}

	private void montarSistemaParametro1Aba(InformarSistemaParametrosActionForm form, SistemaParametro sistemaParametro) {
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
			sistemaParametro.setNomeAbreviadoEmpresa(form.getAbreviaturaEmpresa());
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
			sistemaParametro.setInscricaoMunicipal(form.getInscricaoMunicipal());
		}

		// Número do Contrato
		if (validaCampo(form.getNumeroContrato())) {
			sistemaParametro.setNumeroContratoPrestacaoServico(form.getNumeroContrato());
		}

		// Unidade Organizacional
		if (validaCampo(form.getUnidadeOrganizacionalPresidencia())) {

			UnidadeOrganizacional unidade = new UnidadeOrganizacional();
			unidade.setId(new Integer(form.getUnidadeOrganizacionalPresidencia()));

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

		// Logradouro Bairro
		if (validaCampo(form.getLogradouroBairro())) {

			LogradouroBairro logradouroBairro = new LogradouroBairro();
			logradouroBairro.setId(new Integer(form.getLogradouroBairro()));

			sistemaParametro.setLogradouroBairro(logradouroBairro);
		}

		// Logradouro Cep
		if (validaCampo(form.getLogradouroCep())) {

			LogradouroCep logradouroCep = new LogradouroCep();
			logradouroCep.setId(new Integer(form.getLogradouroCep()));

			sistemaParametro.setLogradouroCep(logradouroCep);
		}

		// Numero
		if (validaCampo(form.getNumero())) {
			sistemaParametro.setNumeroImovel(form.getNumero());
		}

		// Complemento
		if (validaCampo(form.getComplemento())) {
			sistemaParametro.setComplementoEndereco(form.getComplemento());
		}

		// DDD Telefone
		if (validaCampo(form.getDddTelefone())) {
			sistemaParametro.setDddTelefone(form.getDddTelefone());
		}
		
		// Numero Telefone
		if (validaCampo(form.getNumeroTelefone())) {
			sistemaParametro.setNumeroTelefone(form.getNumeroTelefone());
		}

		// Quantidade de Dígitos da Quadra
		if (validaCampo(form.getQuantidadeDigitosQuadra())) {
			if (form.getQuantidadeDigitosQuadra().equals("3") || form.getQuantidadeDigitosQuadra().equals("4")) {
				sistemaParametro.setNumeroDigitosQuadra(new Integer(form.getQuantidadeDigitosQuadra()).shortValue());
			} else {
				throw new ActionServletException("atencao.campo_com_quantidade_digitos_invalida");
			}
		}

		// Indicador não medido por tarifa de consumo
		if (validaCampo(form.getIndicadorQuadraFace())) {
			sistemaParametro.setIndicadorQuadraFace(new Short(form.getIndicadorQuadraFace()));
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
			sistemaParametro.setNumero0800Empresa(form.getNumeroTelefoneAtendimento());
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

		// Numero execucao do resumo de negativacao
		if (validaCampo(form.getNumeroExecucaoResumoNegativacao())) {
			sistemaParametro.setNumeroExecucaoResumoNegativacao(new Integer(form.getNumeroExecucaoResumoNegativacao()));
		}

		// Indicador para controlar os autos de infracao
		if (validaCampo(form.getIndicadorControlaAutoInfracao())) {
			sistemaParametro.setIndicadorControlaAutoInfracao(new Short(form.getIndicadorControlaAutoInfracao()));
		}

		// Indicador Usa Rota
		if (validaCampo(form.getIndicadorUsaRota())) {
			sistemaParametro.setIndicadorUsaRota(new Short(form.getIndicadorUsaRota()));
		}

		// Indicador Duplicidade Cliente
		if (validaCampo(form.getIndicadorDuplicidadeCliente())) {
			sistemaParametro.setIndicadorDuplicidadeCliente(new Short(form.getIndicadorDuplicidadeCliente()));
		}

		// Indicador Nome Menor Que Dez
		if (validaCampo(form.getIndicadorNomeMenorDez())) {
			sistemaParametro.setIndicadorNomeMenorDez(new Short(form.getIndicadorNomeMenorDez()));
		}

		// Indicador Nome Cliente Generico
		if (validaCampo(form.getIndicadorNomeClienteGenerico())) {
			sistemaParametro.setIndicadorNomeClienteGenerico(new Short(form.getIndicadorNomeClienteGenerico()));
		}

		if (validaCampo(form.getVersaoCelular())) {
			sistemaParametro.setVersaoCelular(form.getVersaoCelular());
		}

		// Indicador exibir mensagem
		if (validaCampo(form.getIndicadorExibirMensagem())) {
			sistemaParametro.setIndicadorExibeMensagemNaoReceberPagamento(new Short(form.getIndicadorExibirMensagem()));
		}

		// Cliente Responsavel Programa Especial
		if (validaCampo(form.getIdClienteResponsavelProgramaEspecial())) {
			Cliente clienteResponsavelProgramaEspecial = new Cliente();
			clienteResponsavelProgramaEspecial.setId(new Integer(form.getIdClienteResponsavelProgramaEspecial()));
			sistemaParametro.setClienteResponsavelProgramaEspecial(clienteResponsavelProgramaEspecial);
		}
		// Perfil Programa Especial
		if (validaCampo(form.getPerfilProgramaEspecial())) {
			ImovelPerfil perfilPrograma = new ImovelPerfil();
			perfilPrograma.setId(new Integer(form.getPerfilProgramaEspecial()));
			sistemaParametro.setPerfilProgramaEspecial(perfilPrograma);
		}

		// Percentual de Convergencia Repavimentacao
		if (validaCampo(form.getPercentualConvergenciaRepavimentacao())) {

			BigDecimal percentual = new BigDecimal(0);

			String valorAux = form.getPercentualConvergenciaRepavimentacao().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentual = new BigDecimal(valorAux);
			sistemaParametro.setPercentualConvergenciaRepavimentacao(percentual);
		}

		// Indicador Documento Obrigatorio
		if (validaCampo(form.getIndicadorDocumentoObrigatorio())) {
			sistemaParametro.setIndicadorDocumentoObrigatorio(new Short(form.getIndicadorDocumentoObrigatorio()));
		}

		// Indicador de verificação do CPF e CPJ no CDL
		if (validaCampo(form.getIndicadorCpfCnpj())) {
			sistemaParametro.setIndicadorConsultaDocumentoReceita(new Short(form.getIndicadorCpfCnpj()));
		}

		// Indicador de Exibição Automática do Popup de Atualização Cadastral
		if (validaCampo(form.getIndicadorPopupAtualizacaoCadastral())) {
			sistemaParametro.setIndicadorPopupAtualizacaoCadastral(new Short(form.getIndicadorPopupAtualizacaoCadastral()));
		}

		// Valor para Emissão de Extrato no Formato Ficha de Compensação
		if (validaCampo(form.getValorExtratoFichaComp())) {

			BigDecimal valorExtratoFichaComp = new BigDecimal(0);

			String valorAux = form.getValorExtratoFichaComp().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			valorExtratoFichaComp = new BigDecimal(valorAux);
			sistemaParametro.setValorExtratoFichaComp(valorExtratoFichaComp);
		}

		if (validaCampo(form.getNumeroDiasBloqueioCelular())) {
			sistemaParametro.setNumeroDiasBloqueioCelular(new Integer(form.getNumeroDiasBloqueioCelular()));
		}

		// Valor para Emissão de Extrato no Formato Ficha de Compensação
		if (form.getValorExtratoFichaComp() != null && !form.getValorExtratoFichaComp().equals("")) {

			BigDecimal valorExtratoFichaComp = new BigDecimal(0);

			String valorAux = form.getValorExtratoFichaComp().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			valorExtratoFichaComp = new BigDecimal(valorAux);
			sistemaParametro.setValorExtratoFichaComp(valorExtratoFichaComp);
		} else {
			sistemaParametro.setValorExtratoFichaComp(null);
		}

		// Valor para Emissão de Guia de Pagamento no Formato Ficha de
		// Compensação
		if (form.getValorGuiaFichaComp() != null && !form.getValorGuiaFichaComp().equals("")) {

			BigDecimal valorGuiaFichaComp = new BigDecimal(0);

			String valorAux = form.getValorGuiaFichaComp().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			valorGuiaFichaComp = new BigDecimal(valorAux);
			sistemaParametro.setValorGuiaFichaComp(valorGuiaFichaComp);
		} else {
			sistemaParametro.setValorGuiaFichaComp(null);
		}

		// Valor para Emissão de Demonstrativo de Parcelamento no Formato Ficha
		// de Compensação
		if (form.getValorDemonstrativoParcelamentoFichaComp() != null && !form.getValorDemonstrativoParcelamentoFichaComp().equals("")) {

			BigDecimal valorDemonstrativoParcelamentoFichaComp = new BigDecimal(0);

			String valorAux = form.getValorDemonstrativoParcelamentoFichaComp().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			valorDemonstrativoParcelamentoFichaComp = new BigDecimal(valorAux);
			sistemaParametro.setValorDemonstrativoParcelamentoFichaComp(valorDemonstrativoParcelamentoFichaComp);
		} else {
			sistemaParametro.setValorDemonstrativoParcelamentoFichaComp(null);
		}

		// Indicador de Exibição Automática do Popup de Atualização Cadastral
		if (validaCampo(form.getIndicadorUsoNMCliReceitaFantasia())) {
			sistemaParametro.setIndicadorUsoNMCliReceitaFantasia(new Short(form.getIndicadorUsoNMCliReceitaFantasia()));
		}

		// Indicador Variar Hierarquia de Unidade Oragnizacional
		if (validaCampo(form.getIndicadorVariaHierarquiaUnidade())) {
			sistemaParametro.setIndicadorVariaHierarquiaUnidade(new Short(form.getIndicadorVariaHierarquiaUnidade()));
		}
		// Cliente Ficiticio Associar Pagamentos Não Identificados
		if (validaCampo(form.getClienteFicticioAssociarPagamentosNaoIdentificados())) {
			Cliente cliente = new Cliente();
			cliente.setId(new Integer(form.getClienteFicticioAssociarPagamentosNaoIdentificados()));

			sistemaParametro.setClienteFicticioParaAssociarOsPagamentosNaoIdentificados(cliente);
		}

	}

	private void montarSistemaParametro2Aba(InformarSistemaParametrosActionForm form, SistemaParametro sistemaParametro) {

		// Mês/Ano Referencia
		if (validaCampo(form.getMesAnoReferencia())) {

			boolean mesAnoValido = Util.validarMesAno(form.getMesAnoReferencia());

			if (mesAnoValido == false) {
				throw new ActionServletException("atencao.ano_mes_referencia.invalida");
			}

			String mes = form.getMesAnoReferencia().substring(0, 2);
			String ano = form.getMesAnoReferencia().substring(3, 7);

			Integer anoMesReferenciaFaturamento = new Integer(ano + mes);

			sistemaParametro.setAnoMesFaturamento(anoMesReferenciaFaturamento);
		}

		// if (validaCampo(form.getQtdeContasRetificadas())) {
		// sistemaParametro.setQtdMaxContasRetificadas(Integer.parseInt(form
		// .getQtdeContasRetificadas()));
		// } else {
		// throw new ActionServletException(
		// "atencao.campo_com_quantidade_maxima_contas_retificada_invalido");
		// }

		// Menor Consumo para ser Grande Usuario
		if (validaCampo(form.getMenorConsumo())) {
			sistemaParametro.setMenorConsumoGrandeUsuario(new Integer(form.getMenorConsumo()));
		}

		// Menor Valor para Emissao de Contas
		if (validaCampo(form.getMenorValor())) {

			BigDecimal valorMinimoEmissaoConta = new BigDecimal(0);

			String valorAux = form.getMenorValor().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			valorMinimoEmissaoConta = new BigDecimal(valorAux);
			sistemaParametro.setValorMinimoEmissaoConta(valorMinimoEmissaoConta);
		}

		// Valor para Emissão de Conta no Formato Ficha de Compensação
		if (validaCampo(form.getValorContaFichaComp())) {

			BigDecimal valorContaFichaComp = new BigDecimal(0);

			String valorAux = form.getValorContaFichaComp().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			valorContaFichaComp = new BigDecimal(valorAux);
			sistemaParametro.setValorContaFichaComp(valorContaFichaComp);
		}

		// Qtde de Economias para Ser Grande Usuario
		if (validaCampo(form.getQtdeEconomias())) {
			sistemaParametro.setMenorEconomiasGrandeUsuario(new Short(form.getQtdeEconomias()));
		}

		// Meses para Calculo de Media de Consumo
		if (validaCampo(form.getMesesCalculoMedio())) {
			sistemaParametro.setMesesMediaConsumo(new Short(form.getMesesCalculoMedio()));
		}

		// Dias Minimo para Calcular Vencimento
		if (validaCampo(form.getDiasMinimoVencimento())) {
			sistemaParametro.setNumeroMinimoDiasEmissaoVencimento(new Short(form.getDiasMinimoVencimento()));
		}

		// Dias Minimo para Caluar Vencimento se entrega para os correios
		if (validaCampo(form.getDiasMinimoVencimentoCorreio())) {
			sistemaParametro.setNumeroDiasAdicionaisCorreios(new Short(form.getDiasMinimoVencimentoCorreio()));
		}

		if (validaCampo(form.getDiasVencimentoAlternativo())) {
			Matcher validarCaracteresDiferenteVirgulaNumero = Pattern.compile("[^;0-9]").matcher(form.getDiasVencimentoAlternativo());

			if (validarCaracteresDiferenteVirgulaNumero.find()) {
				throw new ActionServletException("atencao.informar_sistema_parametro.dia_vencimento_alternativo_separado_virgula_sem_espaco_branco");
			}

			Matcher validarMaisDeUmaVirgulaJunta = Pattern.compile(";;+").matcher(form.getDiasVencimentoAlternativo());

			if (validarMaisDeUmaVirgulaJunta.find()) {
				throw new ActionServletException("atencao.informar_sistema_parametro.dia_vencimento_alternativo_separado_apenas_uma_virgula");
			}

			String[] diasString = form.getDiasVencimentoAlternativo().split(";");

			ArrayList<Integer> diasJaValidados = new ArrayList<Integer>();

			if (!Util.isVazioOrNulo(diasString)) {
				for (String diaAtualString : diasString) {
					if (!validaCampo(diaAtualString)) {
						throw new ActionServletException("atencao.gsan.campo_formato_invalido", "Dias para vencimento alternativo");
					}

					Integer diaAtual = new Integer(diaAtualString.trim());

					if (diaAtual < 1 || diaAtual > 31) {
						throw new ActionServletException("atencao.informar_sistema_parametro.dia_vencimento_alternativo_entre_um_trinta_um");
					}

					if (diasJaValidados.contains(diaAtual)) {
						throw new ActionServletException("atencao.informar_sistema_parametro.dia_vencimento_alternativo_duplicado");
					}

					diasJaValidados.add(diaAtual);

					for (Integer diaValidado : diasJaValidados) {
						if (diaValidado > diaAtual) {
							throw new ActionServletException("atencao.informar_sistema_parametro.dia_vencimento_alternativo_desordenado");
						}
					}
				}
			}

			sistemaParametro.setDiasVencimentoAlternativo(form.getDiasVencimentoAlternativo());
		}

		// Numero de meses para validade de conta
		if (validaCampo(form.getNumeroMesesValidadeConta())) {
			sistemaParametro.setNumeroMesesValidadeConta(new Short(form.getNumeroMesesValidadeConta()));
		}

		// Numero de meses para alteracao do vencimento para outro
		if (validaCampo(form.getNumeroMesesAlteracaoVencimento())) {
			sistemaParametro.setNumeroMesesMinimoAlteracaoVencimento(new Short(form.getNumeroMesesAlteracaoVencimento()));
		}

		// Indicador Alteracao do Vencimento mais de uma vez
		if (validaCampo(form.getIndicadorLimiteAlteracaoVencimento())) {
			sistemaParametro.setIndicadorLimiteAlteracaoVencimento(new Short(form.getIndicadorLimiteAlteracaoVencimento()));
		}

		// Indicador Calculo feito pelo sistema
		if (validaCampo(form.getIndicadorCalculaVencimento())) {
			sistemaParametro.setIndicadorCalculaVencimento(new Short(form.getIndicadorCalculaVencimento()));
		}

		// Indicador tipo de tarifa de consumo
		if (validaCampo(form.getIndicadorTarifaCategoria())) {
			sistemaParametro.setIndicadorTarifaCategoria(new Short(form.getIndicadorTarifaCategoria()));
		}

		// Indicador Para Retificar com um valor Menor
		if (validaCampo(form.getIndicadorRetificacaoValorMenor())) {
			sistemaParametro.setIndicadorRetificacaoValorMenor(new Short(form.getIndicadorRetificacaoValorMenor()));
		}

		// Indicador Transferência com débito
		if (validaCampo(form.getIndicadorTransferenciaComDebito())) {
			sistemaParametro.setIndicadorTransferenciaComDebito(new Short(form.getIndicadorTransferenciaComDebito()));
		}

		// Indicador não medido por tarifa de consumo
		if (validaCampo(form.getIndicadorNaoMedidoTarifa())) {
			sistemaParametro.setIndicadorNaoMedidoTarifa(new Short(form.getIndicadorNaoMedidoTarifa()));
		}

		// Indicador de Atualização Tarifária
		if (validaCampo(form.getIndicadorAtualizacaoTarifaria())) {
			sistemaParametro.setIndicadorAtualizacaoTarifaria(new Short(form.getIndicadorAtualizacaoTarifaria()));
		}

		// Mês/Ano Atualização Tarifária
		if (validaCampo(form.getMesAnoAtualizacaoTarifaria())) {

			boolean mesAnoValido = Util.validarMesAno(form.getMesAnoAtualizacaoTarifaria());

			if (mesAnoValido == false) {
				throw new ActionServletException("atencao.ano_mes_referencia.invalida");
			}

			String mes = form.getMesAnoReferencia().substring(0, 2);
			String ano = form.getMesAnoReferencia().substring(3, 7);

			Integer anoMes = new Integer(ano + mes);

			sistemaParametro.setAnoMesAtualizacaoTarifaria(anoMes);
		}

		// Indicador de Faturamento Antecipado
		if (validaCampo(form.getIndicadorFaturamentoAntecipado())) {
			sistemaParametro.setIndicadorFaturamentoAntecipado(new Short(form.getIndicadorFaturamentoAntecipado()));
		}

		// Numero de dias de Variação de Consumo
		if (validaCampo(form.getNumeroDiasVariacaoConsumo())) {
			sistemaParametro.setNumeroDiasVariacaoConsumo(new Short(form.getNumeroDiasVariacaoConsumo()));
		}

		// Salario Minimo
		if (validaCampo(form.getSalarioMinimo())) {

			BigDecimal valorValorSalarioMinimo = new BigDecimal(0);

			String valorAux = form.getSalarioMinimo().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");
			valorValorSalarioMinimo = new BigDecimal(valorAux);

			sistemaParametro.setValorSalarioMinimo(valorValorSalarioMinimo);
		}

		// Area Maxima do Imovel tarifa social
		if (validaCampo(form.getAreaMaxima())) {
			sistemaParametro.setAreaMaximaTarifaSocial(new Integer(form.getAreaMaxima()));
		}

		// Consumo de Energia Maxima
		if (validaCampo(form.getConsumoMaximo())) {
			sistemaParametro.setConsumoEnergiaMaximoTarifaSocial(new Integer(form.getConsumoMaximo()));
		}

		// Consumo de Energia Maxima
		if (validaCampo(form.getNumeroMesesCalculoCorrecao())) {
			sistemaParametro.setNumeroMesesCalculoCorrecao(new Short(form.getNumeroMesesCalculoCorrecao()));
		}

		// Numero de vezes de suspensao de leitura
		if (validaCampo(form.getNumeroVezesSuspendeLeitura())) {
			sistemaParametro.setNumeroVezesSuspendeLeitura(new Integer(form.getNumeroVezesSuspendeLeitura()));
		}

		// Caso não informado recebe o valor nulo
		if (!validaCampo(form.getNumeroVezesSuspendeLeitura())) {
			sistemaParametro.setNumeroVezesSuspendeLeitura(null);
		}

		// Numero de meses da leitura suspensa
		if (validaCampo(form.getNumeroMesesLeituraSuspensa())) {
			sistemaParametro.setNumeroMesesLeituraSuspensa(new Integer(form.getNumeroMesesLeituraSuspensa()));
		}

		// Caso não informado recebe o valor nulo
		if (!validaCampo(form.getNumeroMesesLeituraSuspensa())) {
			sistemaParametro.setNumeroMesesLeituraSuspensa(null);
		}

		// Numero de meses de reinicio situacao especial do faturamento
		if (validaCampo(form.getNumeroMesesReinicioSitEspFatu())) {
			sistemaParametro.setNumeroMesesReinicioSitEspFaturamento(new Integer(form.getNumeroMesesReinicioSitEspFatu()));
		}

		// Caso não informado recebe o valor nulo
		if (!validaCampo(form.getNumeroMesesReinicioSitEspFatu())) {
			sistemaParametro.setNumeroMesesReinicioSitEspFaturamento(null);
		}

		// Numero de dias de prazo para entrada de recurso do auto de infracao
		if (validaCampo(form.getNnDiasPrazoRecursoAutoInfracao())) {
			sistemaParametro.setNumeroDiasPrazoRecursoAutoInfracao(new Integer(form.getNnDiasPrazoRecursoAutoInfracao()));
		}
		// Percentual de Bonus Social
		if (validaCampo(form.getPercentualBonusSocial())) {
			BigDecimal percentualBonusSocial = new BigDecimal(0);

			String valorAux = form.getPercentualBonusSocial().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualBonusSocial = new BigDecimal(valorAux);
			sistemaParametro.setPercentualBonusSocial(percentualBonusSocial);
		}
		// Indicador de bloqueio de recalculo e reemissão de conta na impressão
		// simultânea
		if (validaCampo(form.getIndicadorBloqueioContaMobile())) {
			sistemaParametro.setIndicadorBloqueioContaMobile(new Short(form.getIndicadorBloqueioContaMobile()));
		}
		// Número de meses para retificar uma conta
		if (form.getNumeroMesesRetificarConta() != null) {
			if (form.getNumeroMesesRetificarConta().toString().equals("")) {
				sistemaParametro.setNumeroMesesRetificarConta(null);
			} else {
				sistemaParametro.setNumeroMesesRetificarConta(new Integer(form.getNumeroMesesRetificarConta()));
			}

		}

		// Está na Norma de Retificação da Conta
		if (validaCampo(form.getIndicadorNormaRetificacao())) {
			sistemaParametro.setIndicadorNormaRetificacao(new Short(form.getIndicadorNormaRetificacao()));
		}

		// Mensagem Pedido Conta BRAILE
		if (validaCampo(form.getMensagemContaBraile())) {

			sistemaParametro.setMensagemContaBraile(form.getMensagemContaBraile().trim());
		} else {
			sistemaParametro.setMensagemContaBraile(null);
		}

		if (validaCampo(form.getCodigoTipoCalculoNaoMedido())) {
			sistemaParametro.setCodigoTipoCalculoNaoMedido(new Integer(form.getCodigoTipoCalculoNaoMedido()));
		}

	}

	private void montarSistemaParametro3Aba(InformarSistemaParametrosActionForm form, SistemaParametro sistemaParametro) {

		// Mês e Ano de Referencia
		if (validaCampo(form.getMesAnoReferenciaArrecadacao())) {

			boolean mesAnoValido = Util.validarMesAno(form.getMesAnoReferenciaArrecadacao());

			if (mesAnoValido == false) {
				throw new ActionServletException("atencao.ano_mes_referencia.invalida");
			}

			String mes = form.getMesAnoReferenciaArrecadacao().substring(0, 2);
			String ano = form.getMesAnoReferenciaArrecadacao().substring(3, 7);

			Integer anoMesReferenciaArrecadacao = new Integer(ano + mes);

			sistemaParametro.setAnoMesArrecadacao(anoMesReferenciaArrecadacao);
		}

		// Código da Empresa para FEBRABAN
		if (validaCampo(form.getCodigoEmpresaFebraban())) {
			sistemaParametro.setCodigoEmpresaFebraban(new Short(form.getCodigoEmpresaFebraban()));
		}

		// Número do Lay-out
		if (validaCampo(form.getNumeroLayOut())) {
			sistemaParametro.setNumeroLayoutFebraban(new Short(form.getNumeroLayOut()));
		}

		// Identificador de Conta Bancaria
		if (validaCampo(form.getIndentificadorContaDevolucao())) {

			ContaBancaria contaBancaria = new ContaBancaria();

			contaBancaria.setId(new Integer(form.getIndentificadorContaDevolucao()));
			sistemaParametro.setContaBancaria(contaBancaria);
		}

		// Percentual de Entrada Minima
		if (validaCampo(form.getPercentualEntradaMinima())) {

			BigDecimal percentualEntradaMinima = new BigDecimal(0);

			String valorAux = form.getPercentualEntradaMinima().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualEntradaMinima = new BigDecimal(valorAux);
			sistemaParametro.setPercentualFinanciamentoEntradaMinima(percentualEntradaMinima);
		}

		// Maximo de Parcelas
		if (validaCampo(form.getMaximoParcelas())) {
			sistemaParametro.setNumeroMaximoParcelasFinanciamento(new Short(form.getMaximoParcelas()));
		}

		// Percentual Maximo
		if (validaCampo(form.getPercentualMaximoAbatimento())) {

			BigDecimal percentualMaximoAbatimento = new BigDecimal(0);

			String valorAux = form.getPercentualMaximoAbatimento().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualMaximoAbatimento = new BigDecimal(valorAux);
			sistemaParametro.setPercentualMaximoAbatimento(percentualMaximoAbatimento);
		}

		// Percentual de Taxa
		if (validaCampo(form.getPercentualTaxaFinanciamento())) {

			BigDecimal percentualTaxaFinanciamento = new BigDecimal(0);

			String valorAux = form.getPercentualTaxaFinanciamento().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualTaxaFinanciamento = new BigDecimal(valorAux);
			sistemaParametro.setPercentualTaxaJurosFinanciamento(percentualTaxaFinanciamento);
		}

		// Numero Maximo de Parcelas
		if (validaCampo(form.getNumeroMaximoParcelaCredito())) {
			sistemaParametro.setNumeroMaximoParcelaCredito(new Short(form.getNumeroMaximoParcelaCredito()));
		}

		// Percentual da Média do Índice
		if (validaCampo(form.getPercentualCalculoIndice())) {

			BigDecimal percentualCalculoIndice = new BigDecimal(0);

			String valorAux = form.getPercentualCalculoIndice().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualCalculoIndice = new BigDecimal(valorAux);
			sistemaParametro.setPercentualMediaIndice(percentualCalculoIndice);
		}
		// Número do módulo do dígito verificador
		if (validaCampo(form.getNumeroModuloDigitoVerificador())) {

			sistemaParametro.setNumeroModuloDigitoVerificador(new Short(form.getNumeroModuloDigitoVerificador()));

			if (sistemaParametro.getNumeroModuloDigitoVerificador().compareTo(ConstantesSistema.MODULO_VERIFICADOR_10) == 0
					&& sistemaParametro.getNumeroModuloDigitoVerificador().compareTo(ConstantesSistema.MODULO_VERIFICADOR_11) == 0) {
				throw new ActionServletException("atencao.digito_verificador_invalido");
			}

		}
		// Número meses para pesquisa de imoveis com ramais suprimidos
		if (validaCampo(form.getNumeroMesesPesquisaImoveisRamaisSuprimidos())) {

			sistemaParametro.setNumeroMesesPesquisaImoveisRamaisSuprimidos(new Integer(form.getNumeroMesesPesquisaImoveisRamaisSuprimidos()));

		}
		// Número anos para Geração da declaração quitação de debitos anual
		if (validaCampo(form.getNumeroAnoQuitacao())) {
			sistemaParametro.setNumeroAnoQuitacao(new Integer(form.getNumeroAnoQuitacao()));
		}
		// Indicador de verificação de contas em cobrança judicial,
		// para geração da declaração quitação de debitos anual
		if (validaCampo(form.getIndicadorCobrancaJudical())) {
			sistemaParametro.setIndicadorCobrancaJudical(new Short(form.getIndicadorCobrancaJudical()));
		}
		// Indicador de verificação de contas parceladas,
		// para geração da declaração quitação de debitos anual
		if (validaCampo(form.getIndicadorContaParcelada())) {
			sistemaParametro.setIndicadorContaParcelada(new Short(form.getIndicadorContaParcelada()));
		}
		// Numero meses para calculo de meses
		// para geração da declaração quitação de debitos anual
		if (validaCampo(form.getNumeroMesesAnterioresParaDeclaracaoQuitacao())) {
			sistemaParametro.setNumeroMesesAnterioresParaDeclaracaoQuitacao(new Integer(form.getNumeroMesesAnterioresParaDeclaracaoQuitacao()));
		}
		// Indicador de verificação do valor do movimento arrecadador
		if (validaCampo(form.getIndicadorValorMovimentoArrecadador())) {
			sistemaParametro.setIndicadorValorMovimentoArrecadador(Integer.parseInt(form.getIndicadorValorMovimentoArrecadador()));
		}
		// Codigo de exibição do Relatório de Dados Diários da Arrecadação por
		// Gerência
		if (validaCampo(form.getCdDadosDiarios())) {
			sistemaParametro.setCdDadosDiarios(new Integer(form.getCdDadosDiarios()));
		}
	}

	private void montarSistemaParametro4Aba(InformarSistemaParametrosActionForm form, SistemaParametro sistemaParametro) {
		// Codigo da Menor Capacidade
		if (validaCampo(form.getCodigoMenorCapacidade())) {

			HidrometroCapacidade hidrometroCapacidade = new HidrometroCapacidade();
			hidrometroCapacidade.setId(new Integer(form.getCodigoMenorCapacidade()));

			sistemaParametro.setHidrometroCapacidade(hidrometroCapacidade);
		}

		// Indicador de Geração de Faixa Falsa
		if (validaCampo(form.getIndicadorGeracaoFaixaFalsa())) {
			sistemaParametro.setIndicadorFaixaFalsa(new Short(form.getIndicadorGeracaoFaixaFalsa()));
		}

		// Indicador do Percentual para Geração
		if (validaCampo(form.getIndicadorPercentualGeracaoFaixaFalsa())) {
			sistemaParametro.setIndicadorUsoFaixaFalsa(new Short(form.getIndicadorPercentualGeracaoFaixaFalsa()));
		}

		// Percentual de Geração de Faixa
		if (validaCampo(form.getPercentualGeracaoFaixaFalsa())) {

			BigDecimal percentualGeracaoFaixaFalsa = new BigDecimal(0);

			String valorAux = form.getPercentualGeracaoFaixaFalsa().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualGeracaoFaixaFalsa = new BigDecimal(valorAux);
			sistemaParametro.setPercentualFaixaFalsa(percentualGeracaoFaixaFalsa);
		}

		// Indicador de Geração de Fiscalização
		if (validaCampo(form.getIndicadorGeracaoFiscalizacaoLeitura())) {
			sistemaParametro.setIndicadorPercentualFiscalizacaoLeitura(new Short(form.getIndicadorPercentualGeracaoFiscalizacaoLeitura()));
		}

		// Indicador do Percentual Geração
		if (validaCampo(form.getIndicadorPercentualGeracaoFiscalizacaoLeitura())) {
			sistemaParametro.setIndicadorUsoFiscalizadorLeitura(new Short(form.getIndicadorGeracaoFiscalizacaoLeitura()));
		}

		// Percentual de Tolerancia
		if (validaCampo(form.getPercentualToleranciaRateioConsumo())) {

			BigDecimal percentualToleranciaRateioConsumo = new BigDecimal(0);

			String valorAux = form.getPercentualToleranciaRateioConsumo().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			percentualToleranciaRateioConsumo = new BigDecimal(valorAux);
			sistemaParametro.setPercentualToleranciaRateio(percentualToleranciaRateioConsumo);
		}

		// Percentual de Geração de Fiscalização
		if (validaCampo(form.getPercentualGeracaoFiscalizacaoLeitura())) {

			BigDecimal percentualGeracaoFiscalizacaoLeitura = new BigDecimal(0);

			String valorAux = form.getPercentualGeracaoFiscalizacaoLeitura().toString().replace(".", "");

			valorAux = valorAux.replace(",", ".");

			percentualGeracaoFiscalizacaoLeitura = new BigDecimal(valorAux);

			sistemaParametro.setPercentualFiscalizacaoLeitura(percentualGeracaoFiscalizacaoLeitura);

		}

		// Incremento Máximo de Consumo
		if (validaCampo(form.getIncrementoMaximoConsumo())) {
			sistemaParametro.setIncrementoMaximoConsumoRateio(new Integer(form.getIncrementoMaximoConsumo()));
		}

		// Decremento Máximo de Consumo
		if (validaCampo(form.getDecrementoMaximoConsumo())) {
			sistemaParametro.setDecrementoMaximoConsumoRateio(new Integer(form.getDecrementoMaximoConsumo()));
		}

		// Numero de Dias entre o Vencimento
		if (validaCampo(form.getDiasVencimentoCobranca())) {
			sistemaParametro.setNumeroDiasVencimentoCobranca(new Short(form.getDiasVencimentoCobranca()));
		}

		// Número Máximo de Meses de Sanções
		if (validaCampo(form.getNumeroMaximoMesesSancoes())) {
			sistemaParametro.setNumeroMaximoMesesSancoes(new Short(form.getNumeroMaximoMesesSancoes()));
		}

		// Valor da Segunda Via
		if (validaCampo(form.getValorSegundaVia())) {

			String valorAux = form.getValorSegundaVia().toString().replace(".", "");
			valorAux = valorAux.replace(",", ".");

			sistemaParametro.setValorSegundaVia(new BigDecimal(valorAux));
		}

		// Indicador de Cobrança da Taxa de Extrato
		if (validaCampo(form.getIndicadorCobrarTaxaExtrato())) {
			sistemaParametro.setIndicadorCobrarTaxaExtrato(new Short(form.getIndicadorCobrarTaxaExtrato()));
		}

		// Código da Periodicidade da Negativacao
		if (validaCampo(form.getCodigoPeriodicidadeNegativacao())) {
			sistemaParametro.setCodigoPeriodicidadeNegativacao(new Short(form.getCodigoPeriodicidadeNegativacao()));
		}

		// Número de Dias para Calculo de Adicionais de Impontualidade
		if (validaCampo(form.getNumeroDiasCalculoAcrescimos())) {
			sistemaParametro.setNumeroDiasCalculoAcrescimos(new Short(form.getNumeroDiasCalculoAcrescimos()));
		}

		// Número de Dias de Validade do Extrato de Débito
		if (validaCampo(form.getNumeroDiasValidadeExtrato())) {
			sistemaParametro.setNumeroDiasValidadeExtrato(new Short(form.getNumeroDiasValidadeExtrato()));
		}

		// Número de Dias de Validade do Extrato de Débito para quem possui
		// Permissão Especial
		if (validaCampo(form.getNumeroDiasValidadeExtratoPermissaoEspecial())) {
			sistemaParametro.setNumeroDiasValidadeExtratoPermissaoEspecial(new Short(form.getNumeroDiasValidadeExtratoPermissaoEspecial()));
		} else {
			sistemaParametro.setNumeroDiasValidadeExtratoPermissaoEspecial(null);
		}

		// Indicador Parcelamento Confirmado
		if (validaCampo(form.getIndicadorParcelamentoConfirmado())) {
			sistemaParametro.setIndicadorParcelamentoConfirmado(new Short(form.getIndicadorParcelamentoConfirmado()));
		}

		// Número de dias úteis para que a OS de Fiscalização seja encerrada por
		// Decurso de Prazo
		if (validaCampo(form.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo())) {
			sistemaParametro.setNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo(new Short(form.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo()));
		} else {
			sistemaParametro.setNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo(null);
		}

		// Indicador Calculo Juros Parcelamento Tabela Price
		if (validaCampo(form.getIndicadorTabelaPrice())) {
			sistemaParametro.setIndicadorTabelaPrice(new Short(form.getIndicadorTabelaPrice()));
		}

		// Indicador Divida ativa
		if (validaCampo(form.getIndicadorControleDividaAtiva())) {
			sistemaParametro.setIndicadorDividaAtiva(new Short(form.getIndicadorControleDividaAtiva()));
		}

		// Número de Dias para o Vencimento da Guia de pagamento de Entrada de
		// Parcelamento
		if (validaCampo(form.getNumeroDiasVencimentoEntradaParcelamento())) {
			sistemaParametro.setNumeroDiasVencimentoEntradaParcelamento(new Short(form.getNumeroDiasVencimentoEntradaParcelamento()));
		}

		// Numero de Dias para Encerramento da OS
		if (validaCampo(form.getNumeroDiasEncerramentoOrdemServico())) {
			sistemaParametro.setNumeroDiasEncerramentoOrdemServico(new Short(form.getNumeroDiasEncerramentoOrdemServico()));
		}

		// Numero de Dias para Encerramento da OS Seletiva
		if (validaCampo(form.getNumeroDiasEncerramentoOSSeletiva())) {
			sistemaParametro.setNumeroDiasEncerramentoOSSeletiva(new Short(form.getNumeroDiasEncerramentoOSSeletiva()));
		}

		// Resolução de Diretoria para Cálculo de Descontos para pagamento à
		// vista
		if (validaCampo(form.getIdResolucaoDiretoria())) {
			ResolucaoDiretoria resolucaoDiretoria = new ResolucaoDiretoria();
			resolucaoDiretoria.setId(new Integer(form.getIdResolucaoDiretoria()));
			sistemaParametro.setResolucaoDiretoria(resolucaoDiretoria);
		} else {
			sistemaParametro.setResolucaoDiretoria(null);
		}

		// Retirar Contas Vinculadas a Contrato de Parcelamento da Composição do
		// Débito do Imóvel ou do Cliente
		if (validaCampo(form.getIndicadorBloqueioContasContratoParcelDebitos())) {
			sistemaParametro.setIndicadorBloqueioContasContratoParcelDebitos(new Short(form.getIndicadorBloqueioContasContratoParcelDebitos()));
		}

		// Retirar Guias Vinculadas a Contrato de Parcelamento da Composição do
		// Débito do Imóvel ou do Cliente
		if (validaCampo(form.getIndicadorBloqueioGuiasOuAcresContratoParcelDebito())) {
			sistemaParametro.setIndicadorBloqueioGuiasOuAcresContratoParcelDebito(new Short(form.getIndicadorBloqueioGuiasOuAcresContratoParcelDebito()));
		}

		// Bloquear Contas Vinculadas a Contrato de Parcelamento na tela de
		// Manter Conta
		if (validaCampo(form.getIndicadorBloqueioContasContratoParcelManterConta())) {
			sistemaParametro.setIndicadorBloqueioContasContratoParcelManterConta(new Short(form.getIndicadorBloqueioContasContratoParcelManterConta()));
		}
		/*
		 * Adicionado por: Raimundo Martins Data: 19/07/2011 Indicador de
		 * Bloqueio de Débitos a Cobrar Vinculados ao Contrato de Parcelamento
		 * na Composição do Débito do Imóvel ou Cliente – Obter Débito.
		 */
		if (validaCampo(form.getIndicadorBloqueioDebitoACobrarContratoParcelDebito())) {
			sistemaParametro.setIndicadorBloqueioDebitoACobrarContratoParcelDebito(new Short(form.getIndicadorBloqueioDebitoACobrarContratoParcelDebito()));
		}

		// Vinculadas a Contrato de Parcelamento na tela de Manter Guia
		if (validaCampo(form.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta())) {
			sistemaParametro.setIndicadorBloqueioGuiasOuAcresContratoParcelManterConta(new Short(form
					.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta()));
		}

		//Indicador de Bloqueio de Débitos a Cobrar Vinculados ao Contrato de Parcelamento no Manter Débitos a Cobrar
		if (validaCampo(form.getIndicadorBloqueioDebitoACobrarContratoParcelManterDebito())) {
			sistemaParametro.setIndicadorBloqueioDebitoACobrarContratoParcelManterDebito(new Short(form
					.getIndicadorBloqueioDebitoACobrarContratoParcelManterDebito()));
		}

		// Número Máximo de Parcelas para os Contratos de Parcelamento por Cliente
		if (validaCampo(form.getNumeroMaximoParcelasContratosParcelamento())) {
			sistemaParametro.setNumeroMaximoParcelasContratosParcelamento(new Integer(form.getNumeroMaximoParcelasContratosParcelamento()));
		} else {
			sistemaParametro.setNumeroMaximoParcelasContratosParcelamento(null);
		}
		
		// Responsavel Negativacao
		if (validaCampo(form.getIdClienteResponsavelNegativacao())) {
			Cliente cliente = new Cliente();
			cliente.setId(new Integer(form.getIdClienteResponsavelNegativacao()));

			sistemaParametro.setClienteResponsavelNegativacao(cliente);
		}
	}

	private void montarSistemaParametro5Aba(InformarSistemaParametrosActionForm form, SistemaParametro sistemaParametro) {
		Fachada fachada = Fachada.getInstancia();

		// Indicador de Sugestão de Tramite
		if (validaCampo(form.getIndicadorSugestaoTramite())) {
			sistemaParametro.setIndicadorSugestaoTramite(new Short(form.getIndicadorSugestaoTramite()));
		}

		// Indicador de controle de autorizacao para a tramitacao do RA
		if (validaCampo(form.getIndicadorControleTramitacaoRA())) {
			sistemaParametro.setIndicadorControleTramitacaoRA(new Short(form.getIndicadorControleTramitacaoRA()));
		}

		// Indicador de calculo da data prevista do RA em dias uteis
		if (validaCampo(form.getIndicadorCalculoPrevisaoRADiasUteis())) {
			sistemaParametro.setIndicadorCalculoPrevisaoRADiasUteis(new Short(form.getIndicadorCalculoPrevisaoRADiasUteis()));
		}

		// Indicador de documento obrigatorio para segunda via da conta
		if (validaCampo(form.getIndicadorDocumentoValido())) {
			sistemaParametro.setIndicadorDocumentoValido(new Short(form.getIndicadorDocumentoValido()));
		}

		// Dias Maximo para Reativar RA
		if (validaCampo(form.getDiasMaximoReativarRA())) {
			sistemaParametro.setDiasReativacao(new Short(form.getDiasMaximoReativarRA()));
		}

		// Dias Maximo para alterar Dados da OS
		if (validaCampo(form.getDiasMaximoAlterarOS())) {
			sistemaParametro.setDiasMaximoAlterarOS(new Integer(form.getDiasMaximoAlterarOS()));
		}

		// Ultimo ID Utilizado para Geração de RA Manual
		if (validaCampo(form.getUltimoIDGeracaoRA())) {
			sistemaParametro.setUltimoRAManual(new Integer(form.getUltimoIDGeracaoRA()));
		}

		// Dias MAximo para Expirar Acesso
		if (validaCampo(form.getDiasMaximoExpirarAcesso())) {
			sistemaParametro.setNumeroDiasExpiracaoAcesso(new Short(form.getDiasMaximoExpirarAcesso()));
		}

		// Dias para Começar Aparecer a Msg da Expiracao da Senha
		if (validaCampo(form.getDiasMensagemExpiracaoSenha())) {
			sistemaParametro.setNumeroDiasMensagemExpiracao(new Short(form.getDiasMensagemExpiracaoSenha()));
		}

		// Indicador certidao negativa com efeito positivo
		if (validaCampo(form.getIndicadorCertidaoNegativaEfeitoPositivo())) {
			sistemaParametro.setIndicadorCertidaoNegativaEfeitoPositivo(new Short(form.getIndicadorCertidaoNegativaEfeitoPositivo()));
		}

		// Indicador debito a cobrar valido certidao negativa
		if (validaCampo(form.getIndicadorDebitoACobrarValidoCertidaoNegativa())) {
			sistemaParametro.setIndicadorDebitoACobrarValidoCertidaoNegativa(new Short(form.getIndicadorDebitoACobrarValidoCertidaoNegativa()));
		}

		// Numero Dias de Vencimento para gerar Certidao Negativa
		if (validaCampo(form.getDiasVencimentoCertidaoNegativa())) {
			sistemaParametro.setNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos(new Short(form.getDiasVencimentoCertidaoNegativa()));
		}

		// Numero Maximo de Tentativas de Acesso
		if (validaCampo(form.getNumeroMaximoTentativasAcesso())) {
			sistemaParametro.setNumeroMaximoLoginFalho(new Short(form.getNumeroMaximoTentativasAcesso()));
		}

		// Numero Maximo de Favoritos no Menu do Sistema
		if (validaCampo(form.getNumeroMaximoFavoritosMenu())) {
			sistemaParametro.setNumeroMaximoFavorito(new Integer(form.getNumeroMaximoFavoritosMenu()));
		}

		// IP do Servidor SMTP
		if (validaCampo(form.getIpServidorSmtp())) {
			sistemaParametro.setIpServidorSmtp(form.getIpServidorSmtp());
		}

		// IP do Servidor Gerencial
		if (validaCampo(form.getIpServidorGerencial())) {
			sistemaParametro.setIpServidorModuloGerencial(form.getIpServidorGerencial());
		}

		// E-mail do Responsavel
		if (validaCampo(form.getEmailResponsavel())) {
			sistemaParametro.setDsEmailResponsavel(form.getEmailResponsavel());
		}

		// Mensagem do Sistema
		if (validaCampo(form.getMensagemSistema())) {
			sistemaParametro.setMensagemSistema(form.getMensagemSistema());
		}

		// Indicador Login Unico
		if (validaCampo(form.getIndicadorLoginUnico())) {
			sistemaParametro.setIndicadorLoginUnico(new Short(form.getIndicadorLoginUnico()));
		}

		// Indicador de validação da localidade no encerramento da OS Seletiva
		if (validaCampo(form.getIndicadorValidacaoLocalidadeEncerramentoOS())) {
			sistemaParametro.setIndicadorValidarLocalizacaoEncerramentoOS(new Short(form.getIndicadorValidacaoLocalidadeEncerramentoOS()));
		}
		// Indicador de controle de dias de expiração de senha por Grupo
		if (validaCampo(form.getIndicarControleExpiracaoSenhaPorGrupo())) {
			sistemaParametro.setIndicadorControleExpiracaoSenhaPorGrupo(new Integer(form.getIndicarControleExpiracaoSenhaPorGrupo()));
		}
		// Indicador de controle de bloqueio de senhas usadas anteriormente
		if (validaCampo(form.getIndicarControleBloqueioSenha())) {
			sistemaParametro.setIndicadorControleBloqueioSenhaAnterior(new Integer(form.getIndicarControleBloqueioSenha()));
		}
		// Indicador de controle de senha forte
		if (validaCampo(form.getIndicadorSenhaForte())) {
			sistemaParametro.setIndicadorSenhaForte(new Integer(form.getIndicadorSenhaForte()));
		}
		// Unidade Organizacional Tramite Grande Consumidor
		if (validaCampo(form.getIdUnidadeDestinoGrandeConsumidor())) {

			FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();

			filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, form.getIdUnidadeDestinoGrandeConsumidor()));

			Collection<UnidadeOrganizacional> colecaoUnidade = fachada.pesquisar(filtroUnidadeEmpresa, UnidadeOrganizacional.class.getName());

			UnidadeOrganizacional unidadeOrganizacionalTramiteGrandeConsumidor = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);

			if (unidadeOrganizacionalTramiteGrandeConsumidor != null) {

				if (new Short(unidadeOrganizacionalTramiteGrandeConsumidor.getIndicadorTramite()).compareTo(ConstantesSistema.NAO) == 0) {
					throw new ActionServletException("atencao.unidade.nao.aceita.tramite");
				}
				if (new Short(unidadeOrganizacionalTramiteGrandeConsumidor.getIndicadorUso()).compareTo(ConstantesSistema.NAO) == 0) {
					throw new ActionServletException("atencao.unidade.nao.ativa");
				}
				sistemaParametro.setUnidadeOrganizacionalTramiteGrandeConsumidor(unidadeOrganizacionalTramiteGrandeConsumidor);
			}
		}

		if (validaCampo(form.getNumeroDiasRevisaoConta())) {
			sistemaParametro.setNumeroDiasRevisaoComPermEspecial(new Integer(form.getNumeroDiasRevisaoConta()));
		}

		// Número de dias para validade ordem de fiscalização
		if (validaCampo(form.getQtdeDiasValidadeOSFiscalizacao())) {
			sistemaParametro.setQtdeDiasValidadeOSFiscalizacao(new Integer(form.getQtdeDiasValidadeOSFiscalizacao()));
		}

		// Número máximo de dias para uma ordem de serviço ser fiscalizada
		if (validaCampo(form.getQtdeDiasEncerraOSFiscalizacao())) {
			sistemaParametro.setQtdeDiasEncerraOSFiscalizacao(new Integer(form.getQtdeDiasEncerraOSFiscalizacao()));
		}

		// Número de dias para envio de conta por email
		if (validaCampo(form.getQtdeDiasEnvioEmailConta())) {
			sistemaParametro.setQtdeDiasEnvioEmailConta(new Integer(form.getQtdeDiasEnvioEmailConta()));
		}

		// Descrição do Decreto para Loja Virtual
		if (validaCampo(form.getDescricaoDecreto())) {
			sistemaParametro.setDescricaoDecreto(form.getDescricaoDecreto());
		}

		// Arquivo do Decreto para Loja Virtual
		if (form.getArquivoDecreto() != null) {
			try {
				if (form.getArquivoDecreto().getFileData().length != 0) {
					fachada.validarSistemaParametroLojaVirtual(form.getArquivoDecreto().getFileData(), retornarExtensaoArquivo(form.getArquivoDecreto()));
					sistemaParametro.setArquivoDecreto(form.getArquivoDecreto().getFileData());
				}
			} catch (IOException e) {

			}
		}

		// Descrição da Lei de Estrutura Tarifaria para Loja Virtual
		if (validaCampo(form.getDescricaoLeiEstTarif())) {
			sistemaParametro.setDescricaoLeiEstTarif(form.getDescricaoLeiEstTarif());
		}

		// Arquivo da Lei de Estrutura Tarifaria para Loja Virtual
		if (form.getArquivoLeiEstTarif() != null) {
			try {
				if (form.getArquivoLeiEstTarif().getFileData().length != 0) {
					fachada.validarSistemaParametroLojaVirtual(form.getArquivoLeiEstTarif().getFileData(),
							retornarExtensaoArquivo(form.getArquivoLeiEstTarif()));
					sistemaParametro.setArquivoLeiEstTarif(form.getArquivoLeiEstTarif().getFileData());

				}
			} catch (IOException e) {

			}
		}

		// Descrição da Lei de Individualização Predial para Loja Virtual
		if (validaCampo(form.getDescricaoLeiIndividualizacao())) {
			sistemaParametro.setDescricaoLeiIndividualizacao(form.getDescricaoLeiIndividualizacao());
		}

		// Arquivo da Lei de Individualização Predial para Loja Virtual
		if (form.getArquivoLeiIndividualizacao() != null) {
			try {
				if (form.getArquivoLeiIndividualizacao().getFileData().length != 0) {
					fachada.validarSistemaParametroLojaVirtual(form.getArquivoLeiIndividualizacao().getFileData(),
							retornarExtensaoArquivo(form.getArquivoLeiIndividualizacao()));
					sistemaParametro.setArquivoLeiIndividualizacao(form.getArquivoLeiIndividualizacao().getFileData());

				}
			} catch (IOException e) {

			}
		}

		// Descrição da Norma CO para Loja Virtual
		if (validaCampo(form.getDescricaoNormaCO())) {
			sistemaParametro.setDescricaoNormaCO(form.getDescricaoNormaCO());
		}

		// Arquivo da Norma CO para Loja Virtual
		if (form.getArquivoNormaCO() != null) {
			try {
				if (form.getArquivoNormaCO().getFileData().length != 0) {
					fachada.validarSistemaParametroLojaVirtual(form.getArquivoNormaCO().getFileData(), retornarExtensaoArquivo(form.getArquivoNormaCO()));
					sistemaParametro.setArquivoNormaCO(form.getArquivoNormaCO().getFileData());

				}
			} catch (IOException e) {

			}
		}

		// Descrição da Norma CM para Loja Virtual
		if (validaCampo(form.getDescricaoNormaCM())) {
			sistemaParametro.setDescricaoNormaCM(form.getDescricaoNormaCM());
		}

		// Arquivo da Norma CM para Loja Virtual
		if (form.getArquivoNormaCM() != null) {
			try {
				if (form.getArquivoNormaCM().getFileData().length != 0) {
					fachada.validarSistemaParametroLojaVirtual(form.getArquivoNormaCM().getFileData(), retornarExtensaoArquivo(form.getArquivoNormaCM()));

					sistemaParametro.setArquivoNormaCM(form.getArquivoNormaCM().getFileData());

				}
			} catch (IOException e) {

			}
		}
	}

	private String retornarExtensaoArquivo(FormFile formFile) {
		String[] nomeArquivoPartido = formFile.getFileName().split("\\.");
		String formato = nomeArquivoPartido[1];

		return formato;
	}
}
