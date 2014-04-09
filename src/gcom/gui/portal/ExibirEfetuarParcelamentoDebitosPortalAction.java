package gcom.gui.portal;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelSituacao;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.IndicadoresParcelamentoHelper;
import gcom.cobranca.bean.NegociacaoOpcoesParcelamentoHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.bean.ObterOpcoesDeParcelamentoHelper;
import gcom.cobranca.bean.OpcoesParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1191] Efetuar Parcelamento de Débitos Através da Loja Virtual
 * 
 * Classe Responsável por carregar todos os dados necessários para exibir
 * a tela parcelamento_debitos_portal_efetuar.jsp 
 * 
 * @author Diogo Peixoto
 * @since 28/06/2011
 */

public class ExibirEfetuarParcelamentoDebitosPortalAction extends GcomAction {

	private ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = null;
	private boolean reativacaoDaligacaoAgua = false;
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);
		ActionForward retorno = actionMapping.findForward("efetuarParcelamento");
		Fachada fachada = Fachada.getInstancia();
		httpServletRequest.setAttribute("voltarServicos", true);
		String method = httpServletRequest.getParameter("method");
		String erroEfetuarParcelamento = (String)httpServletRequest.getAttribute("erroEfetuarParcelamento");
		Boolean mensagemReativacao = (Boolean)httpServletRequest.getAttribute("mensagemReativacao");
		Integer matricula = (Integer) sessao.getAttribute("matricula");		
		EfetuarParcelamentoDebitosPortalActionForm form = (EfetuarParcelamentoDebitosPortalActionForm) actionForm;
		
		/*
		 * Se houver não houver erro no parcelamento e não houver uma mesangem de reativação do ramal
		 * (quando o efetuar parcelamento for bem sucedido e tiver religação de água).
		 */
		if(!Util.verificarNaoVazio(erroEfetuarParcelamento) && (mensagemReativacao == null || (mensagemReativacao != null && !mensagemReativacao))){
			//[FS0003] – Verificar existência de parcelamento ativo
			Collection<Parcelamento> parcelamentos = fachada.pesquisarParcelamentosSituacaoNormal(matricula);
			if(Util.isVazioOrNulo(parcelamentos)){
				// [FS0012] Verificar existência de parcelamento no mês
				parcelamentos = fachada.verificarParcelamentoMesImovel(matricula);
				if(Util.isVazioOrNulo(parcelamentos)){
					//Caso a solicitação tenha vindo da página de serviços, limpar o form.
					String paginaServicos = httpServletRequest.getParameter("paginaServicos");
					if(Util.verificarNaoVazio(paginaServicos) && paginaServicos.equalsIgnoreCase("sim")){
						form.limparForm();
					}
					Imovel imovel = fachada.pesquisarImovel(matricula);
					sessao.setAttribute("imovel", imovel);
					form.setMatriculaImovel(matricula.toString());
					form.setInscricaoImovel(imovel.getInscricaoFormatada());
					//[UC0067] Obter Débito do Imóvel ou Cliente - [FS0014] Verificar existência de débitos para o imóvel
					if(this.obterDebitosImovel(matricula)){
						//Validações relativas ao cpf/cliente.
						ParcelamentoPerfil parcelamentoPerfil = this.obterPerfilParcelamentoImovel(imovel, form);
						//Verifica se o imóvel possui perfil de parcelamento
						if(parcelamentoPerfil != null){
							
							//As três situações onde pode ter reativação da situação de água.
							if(imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.SUPRIMIDO ||
									imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.SUPR_PARC ||
									imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.SUPR_PARC_PEDIDO ){
								reativacaoDaligacaoAgua = true;
								httpServletRequest.setAttribute("reativacaoLigacaoAgua", reativacaoDaligacaoAgua);
								
								/*
								 * Caso tenha sido a primeira vez que a página foi carrega, isto é: que
								 * o indicador do restabelecimento for nulo, por padrão colocar o indicador
								 * como 2.
								 */
								if(form.getIndicadorRestabelecimento() == null){
									form.setIndicadorRestabelecimento("2");
								}
							/*
							 * Caso não esteja dentro das três situações acima, o indicador de restabelecimento será
							 * por padrão 2.
							 */
							}else{
								form.setIndicadorRestabelecimento("2");
							}
							
							if(method != null && method.equalsIgnoreCase("pesquisarCliente")){
								String nomeCliente = this.verificaExistenciaCpf(form.getCpfCliente(), matricula, httpServletRequest); 
								if(Util.verificarNaoVazio(nomeCliente)){
									form.setNomeCliente(nomeCliente);
									this.calcularDebitos(this.colecaoDebitoImovel, imovel, form, sessao, httpServletRequest, false, parcelamentoPerfil);
									httpServletRequest.setAttribute("exibirDetalhesDebito", true);
								}
							}else{
								String calcularParcelas = httpServletRequest.getParameter("calcularParcelas");
								if(Util.verificarNaoVazio(calcularParcelas) && calcularParcelas.equalsIgnoreCase("sim")){
									if(form.getValorEntradaInformado().equals("")){
										httpServletRequest.setAttribute("valorEntradaNaoDigitado", true);
									}else{
										this.calcularDebitos(this.colecaoDebitoImovel, imovel, form, sessao, httpServletRequest, true, parcelamentoPerfil);
									}
									httpServletRequest.setAttribute("exibirDetalhesDebito", true);
								}
								String endereco = fachada.pesquisarEndereco(matricula);
								form.setEnderecoImovel(endereco);
							}
						}else{
							retorno = actionMapping.findForward("imovelNaoPossuiPerfilParcelamento");
							httpServletRequest.setAttribute("imovelNaoPossuiPerfilParcelamento", true);
						}
					}else{
						retorno = actionMapping.findForward("imovelSemDebitos");
						httpServletRequest.setAttribute("imovelSemDebitos", true);
					}
				}else{
					retorno = actionMapping.findForward("debitoParceladoMesCorrente");
					httpServletRequest.setAttribute("debitoParceladoMesCorrente", true);
				}
			}else{
				retorno = actionMapping.findForward("matriculaPossuiParcelamentoNaoQuitado");
				httpServletRequest.setAttribute("imovelParcelamentoAtivo", true);
			}
		}else{
			httpServletRequest.setAttribute("exibirDetalhesDebito", true);
			httpServletRequest.setAttribute("reativacaoLigacaoAgua", reativacaoDaligacaoAgua);
		}
		return retorno;
	}

	private String verificaExistenciaCpf(String cpf, Integer matricula, HttpServletRequest request){
		Fachada fachada = Fachada.getInstancia();
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.CPF, cpf));
		Collection<Cliente> clientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
		String nomeCliente = null;

		//[FS0001] - Verificar existência do CPF do cliente
		if(!Util.isVazioOrNulo(clientes)){
			nomeCliente = fachada.validarCliente(cpf, matricula);
			//[FS0002] – Validar cliente
			if(!Util.verificarNaoVazio(nomeCliente)){
				request.setAttribute("clienteInvalido", true);
			}
		}else{
			request.setAttribute("cpfInvalido", true);
		}
		return nomeCliente;
	}

	private boolean obterDebitosImovel(Integer matricula){
		boolean possuiDebitos = false;
		this.colecaoDebitoImovel = Fachada.getInstancia().obterDebitoImovelOuCliente(
				1,// Indicador débito imóvel 
				matricula.toString(), // Matrícula do imóvel
				null, // Código do cliente
				null, // Tipo de relação do cliento com o imóvel
				"000101", // Referência inicial do débito
				"999912", // Referência final do débito
				Util.converteStringParaDate("01/01/0001"), // Inicio Vencimento
				Util.converteStringParaDate("31/12/9999"), // Final Vencimento
				1, // Indicador pagamento
				2, // Indicador conta em revisão
				1, // Indicador débito a cobrar
				1, // Indicador crédito a realizar
				1, // Indicador notas promissórias
				1, // Indicador guias de pagamento
				1, // Indicador acréscimos por impontualidade
				null); // Indicador Contas
		if (this.existeDebitos(colecaoDebitoImovel)) {
			possuiDebitos = true;
		}
		return possuiDebitos;
	}

	private boolean existeDebitos(ObterDebitoImovelOuClienteHelper debitos){
		boolean existe = true;
		if(Util.isVazioOrNulo(debitos.getColecaoContasValoresImovel()) 
				&& Util.isVazioOrNulo(debitos.getColecaoGuiasPagamentoValores())
				&& Util.isVazioOrNulo(debitos.getColecaoDebitoACobrar())){
			existe = false;
		}
		return existe;
	}

	private void calcularDebitos(ObterDebitoImovelOuClienteHelper debitos, Imovel imovel, EfetuarParcelamentoDebitosPortalActionForm form,
			HttpSession sessao, HttpServletRequest request, boolean recalcularOpcaoParcelamento, ParcelamentoPerfil parcelamentoPerfil){
		Fachada fachada = Fachada.getInstancia();

		/*
		 * Verifica se o usuário digitou um novo valor de entrada para o calculo do parcelamento. Caso
		 * tenha digitado, não será necessário calcular os débitos novamente, apenas o novo valor do
		 * parcelamento.
		 */
		if(!recalcularOpcaoParcelamento){

			// Para o cálculo do Débito Total Atualizado
			BigDecimal valorTotalContas = new BigDecimal("0.00");
			BigDecimal valorTotalAcrescimoImpontualidade = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteServicosACobrar = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteServicosACobrarCurtoPrazo = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteServicosACobrarLongoPrazo = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteParcelamentosACobrar = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteParcelamentosACobrarCurtoPrazo = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteParcelamentosACobrarLongoPrazo = new BigDecimal("0.00");
			BigDecimal valorTotalGuiasPagamento = new BigDecimal("0.00");
			BigDecimal valorTotalAcrescimoImpontualidadeContas = new BigDecimal("0.00");
			BigDecimal valorTotalAcrescimoImpontualidadeGuias = new BigDecimal("0.00");
			BigDecimal valorCreditoARealizar = new BigDecimal("0.00");
			BigDecimal valorRestanteACobrar = new BigDecimal("0.00");
			BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
			BigDecimal valorJurosMora = new BigDecimal("0.00");
			BigDecimal valorMulta = new BigDecimal("0.00");

			// Dados do Débito do Imóvel - Contas
			Collection<ContaValoresHelper> colecaoContaValores = debitos.getColecaoContasValores();
			//[SB0011] Verificar Única Fatura
			fachada.verificarUnicaFatura(colecaoContaValores,parcelamentoPerfil);

			if (!Util.isVazioOrNulo(colecaoContaValores)) {

				int quantidadeMinimaMesesAntiguidade = 0;
				int maiorQuantidadeMinimaMesesAntiguidade = 0;
				Iterator<ContaValoresHelper> contaValores = colecaoContaValores.iterator();

				while (contaValores.hasNext()) {

					ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();

					//Colocado por Raphael Rossiter em 04/12/2008
					//=============================================================================================
					Collection<ParcelamentoDescontoAntiguidade> colecaoParcelamentoDescontoAntiguidade = 
						fachada.obterParcelamentoDescontoAntiguidadeParaConta(parcelamentoPerfil, contaValoresHelper.getConta());

					ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidadeMaior = new ParcelamentoDescontoAntiguidade();

					// Caso nenhuma ocorrência tenha sido selecionada passar para a próxima conta
					if (!Util.isVazioOrNulo(colecaoParcelamentoDescontoAntiguidade)) {

						Iterator<ParcelamentoDescontoAntiguidade> parcelamentoDescontoAntiguidadeValores = colecaoParcelamentoDescontoAntiguidade.iterator();
						quantidadeMinimaMesesAntiguidade = 0;
						maiorQuantidadeMinimaMesesAntiguidade = 0;

						//Determina o percentual de desconto por antiguidade do débito
						while (parcelamentoDescontoAntiguidadeValores.hasNext()) {
							ParcelamentoDescontoAntiguidade descoAnt = (ParcelamentoDescontoAntiguidade) parcelamentoDescontoAntiguidadeValores.next();
							quantidadeMinimaMesesAntiguidade = descoAnt.getQuantidadeMinimaMesesDebito();
							if (quantidadeMinimaMesesAntiguidade > maiorQuantidadeMinimaMesesAntiguidade) {
								maiorQuantidadeMinimaMesesAntiguidade = quantidadeMinimaMesesAntiguidade;
								parcelamentoDescontoAntiguidadeMaior = descoAnt;
							}
						}

						/*
						 * Colocado por Raphael Rossiter em 03/12/2008
						 * As contas onde o perfil de parcelamento para desconto de antiguidade estiver com
						 * o motivo de revisão informado NÃO entrarão no parcelamento.
						 */
						valorTotalContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalContas = valorTotalContas.add(contaValoresHelper.getValorTotalConta());

						if (contaValoresHelper.getValorAtualizacaoMonetaria() != null && !contaValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(contaValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorJurosMora() != null	&& !contaValoresHelper.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(contaValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorMulta() != null && !contaValoresHelper.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(contaValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}

						// Para cálculo do Acrescimo de Impontualidade
						valorTotalAcrescimoImpontualidadeContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalAcrescimoImpontualidadeContas = valorTotalAcrescimoImpontualidadeContas.add(contaValoresHelper.getValorTotalContaValoresParcelamento());

						if (parcelamentoDescontoAntiguidadeMaior.getContaMotivoRevisao() != null){
							//CONTA ENTRARÁ EM REVISÃO
							contaValoresHelper.setRevisao(1);
						}
					}else{

						valorTotalContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalContas = valorTotalContas.add(contaValoresHelper.getValorTotalConta());

						if (contaValoresHelper.getValorAtualizacaoMonetaria() != null && !contaValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(contaValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorJurosMora() != null	&& !contaValoresHelper.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(contaValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorMulta() != null && !contaValoresHelper.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(contaValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}

						// Para cálculo do Acrescimo de Impontualidade
						valorTotalAcrescimoImpontualidadeContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalAcrescimoImpontualidadeContas = valorTotalAcrescimoImpontualidadeContas.add(contaValoresHelper.getValorTotalContaValoresParcelamento());
					}
					//=============================================================================================
				}

				form.setValorTotalContaValores(Util.formatarMoedaReal(valorTotalContas));
				sessao.setAttribute("valorTotalContaValores", valorTotalContas);
				sessao.setAttribute("colecaoContaValores", colecaoContaValores);
			}

			//----------------------------- Guia Pagamento -----------------------------//

			Collection<GuiaPagamentoValoresHelper> guiasPagamentoValores = debitos.getColecaoGuiasPagamentoValores();
			if (!Util.isVazioOrNulo(guiasPagamentoValores)){
				Iterator<GuiaPagamentoValoresHelper> guiaPagamentoValores = guiasPagamentoValores.iterator();
				while (guiaPagamentoValores.hasNext()) {
					GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) guiaPagamentoValores.next();
					valorTotalGuiasPagamento.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
					valorTotalGuiasPagamento = valorTotalGuiasPagamento.add(guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());

					if (guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria() != null && !guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
						valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
						valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
					}
					if (guiaPagamentoValoresHelper.getValorJurosMora() != null && !guiaPagamentoValoresHelper.getValorJurosMora().equals("")) {
						valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
						valorJurosMora = valorJurosMora.add(guiaPagamentoValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
					}
					if (guiaPagamentoValoresHelper.getValorMulta() != null	&& !guiaPagamentoValoresHelper.getValorMulta().equals("")) {
						valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
						valorMulta = valorMulta.add(guiaPagamentoValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
					}

					// Para cálculo do Acrescimo de Impontualidade
					valorTotalAcrescimoImpontualidadeGuias.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
					valorTotalAcrescimoImpontualidadeGuias = valorTotalAcrescimoImpontualidadeGuias.add(guiaPagamentoValoresHelper.getValorAcrescimosImpontualidade());
				}
				form.setValorGuiasPagamento(Util.formatarMoedaReal(valorTotalGuiasPagamento));

				// Seta as Guias de Pagamento em Débito
				sessao.setAttribute("guiasPagamentoValores", guiasPagamentoValores);
			} else {
				form.setValorGuiasPagamento("0.00");
			}

			//----------------------------- Acrescimos por Impontualidade -----------------------------//
			BigDecimal retornoSoma = new BigDecimal("0.00");
			retornoSoma.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
			retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeContas);
			retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeGuias);

			form.setValorAcrescimosImpontualidade(Util.formatarMoedaReal(retornoSoma));
			sessao.setAttribute("valorAcrescimosImpontualidade", retornoSoma);

			form.setValorAtualizacaoMonetaria(Util.formatarMoedaReal(valorAtualizacaoMonetaria));
			form.setValorJurosMora(Util.formatarMoedaReal(valorJurosMora));
			form.setValorMulta(Util.formatarMoedaReal(valorMulta));

			valorTotalAcrescimoImpontualidade = retornoSoma;


			//----------------------------- Débitos a cobrar -----------------------------//
			//[FS0022]-Verificar existência de juros sobre imóvel
			Collection<DebitoACobrar> colecaoDebitoACobrar = debitos.getColecaoDebitoACobrar();

			if (!Util.isVazioOrNulo(colecaoDebitoACobrar)) {
				Iterator<DebitoACobrar> debitoACobrarValores = colecaoDebitoACobrar.iterator();

				final int indiceCurtoPrazo = 0;
				final int indiceLongoPrazo = 1;

				while (debitoACobrarValores.hasNext()) {
					DebitoACobrar debitoACobrar = debitoACobrarValores.next();

					//[FS0022]-Verificar existência de juros sobre imóvel
					if(debitoACobrar.getDebitoTipo().getId() != null && !debitoACobrar.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)){

						// Debitos A Cobrar - Serviço
						if (debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.SERVICO_NORMAL)) {
							// [SB0001] Obter Valores de Curto e Longo Prazo
							valorRestanteACobrar = debitoACobrar.getValorTotalComBonus();

							BigDecimal[] valoresDeCurtoELongoPrazo = fachada.obterValorACobrarDeCurtoELongoPrazo(
									debitoACobrar.getNumeroPrestacaoDebito(), debitoACobrar.getNumeroPrestacaoCobradasMaisBonus(),
									valorRestanteACobrar);
							valorTotalRestanteServicosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalRestanteServicosACobrarCurtoPrazo = valorTotalRestanteServicosACobrarCurtoPrazo.add(
									valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
							form.setValorDebitoACobrarServicoCurtoPrazo(Util.formatarMoedaReal(valorTotalRestanteServicosACobrarCurtoPrazo));
							
							valorTotalRestanteServicosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalRestanteServicosACobrarLongoPrazo = valorTotalRestanteServicosACobrarLongoPrazo.add(
									valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
							form.setValorDebitoACobrarServicoLongoPrazo(Util.formatarMoedaReal(valorTotalRestanteServicosACobrarLongoPrazo));
						}

						// Debitos A Cobrar - Parcelamento
						if (debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_AGUA)
								|| debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_ESGOTO)
								|| debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_SERVICO)) {
							// [SB0001] Obter Valores de Curto e Longo Prazo
							valorRestanteACobrar = debitoACobrar.getValorTotalComBonus();

							BigDecimal[] valoresDeCurtoELongoPrazo = fachada.obterValorACobrarDeCurtoELongoPrazo(
									debitoACobrar.getNumeroPrestacaoDebito(),
									debitoACobrar.getNumeroPrestacaoCobradasMaisBonus(), valorRestanteACobrar);
							valorTotalRestanteParcelamentosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalRestanteParcelamentosACobrarCurtoPrazo = valorTotalRestanteParcelamentosACobrarCurtoPrazo.add(
									valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
							valorTotalRestanteParcelamentosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalRestanteParcelamentosACobrarLongoPrazo = valorTotalRestanteParcelamentosACobrarLongoPrazo.add(
									valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
						}
					}
				}
				sessao.setAttribute("colecaoDebitoACobrar", colecaoDebitoACobrar);

				// Serviços
				valorTotalRestanteServicosACobrar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				valorTotalRestanteServicosACobrar = valorTotalRestanteServicosACobrarCurtoPrazo.add(valorTotalRestanteServicosACobrarLongoPrazo);
				form.setValorDebitoACobrarServico(Util.formatarMoedaReal(valorTotalRestanteServicosACobrar));
				// Parcelamentos
				valorTotalRestanteParcelamentosACobrar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				valorTotalRestanteParcelamentosACobrar = valorTotalRestanteParcelamentosACobrarCurtoPrazo
					.add(valorTotalRestanteParcelamentosACobrarLongoPrazo);
				form.setValorDebitoACobrarParcelamento(Util.formatarMoedaReal(valorTotalRestanteParcelamentosACobrar));
			}else{
				form.setValorDebitoACobrarServico("0,00");
				form.setValorDebitoACobrarParcelamento("0,00");
				form.setValorDebitoACobrarServicoLongoPrazo("0,00");
				form.setValorDebitoACobrarServicoCurtoPrazo("0,00");
				form.setValorDebitoACobrarParcelamentoLongoPrazo("0,00");
				form.setValorDebitoACobrarParcelamentoCurtoPrazo("0,00");
			}

			//----------------------------- Créditos a realizar -----------------------------//
			Collection<CreditoARealizar> colecaoCreditoARealizar = debitos.getColecaoCreditoARealizar();
			if (Util.isVazioOrNulo(colecaoCreditoARealizar)) {
				Iterator<CreditoARealizar> creditoARealizarValores = colecaoCreditoARealizar.iterator();
				while (creditoARealizarValores.hasNext()) {
					CreditoARealizar creditoARealizar = creditoARealizarValores.next();
					valorCreditoARealizar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
					valorCreditoARealizar = valorCreditoARealizar.add(creditoARealizar.getValorTotalComBonus());
				}
				sessao.setAttribute("colecaoCreditoARealizar",colecaoCreditoARealizar);
				form.setValorCreditoARealizar(Util.formatarMoedaReal(valorCreditoARealizar));
			}else{
				form.setValorCreditoARealizar("0,00");
			}

			//----------------------------- Débito total atualizado -----------------------------//
			BigDecimal debitoTotalAtualizado = new BigDecimal("0.00");

			debitoTotalAtualizado.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);

			debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalContas);
			debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalGuiasPagamento);
			debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalAcrescimoImpontualidade);
			debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalRestanteServicosACobrar);
			debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalRestanteParcelamentosACobrar);
			debitoTotalAtualizado = debitoTotalAtualizado.subtract(valorCreditoARealizar);

			form.setValorDebitoTotalAtualizado(Util.formatarMoedaReal(debitoTotalAtualizado));
			sessao.setAttribute("valorDebitoTotalAtualizado", debitoTotalAtualizado);
		}
		/*
		 * Caso o usuário tenha digitado um novo valor ou seja a primeira vez que esteja calculando os débitos do imóvel,
		 * será necessário obter as opções de parcelamento. A coleção de conta valores será setada na sessao dentro do
		 * calcular débitos. Logo acima.
		 */
		this.obterOpcaoParcelamento((Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContaValores"), 
				debitos.getColecaoGuiasPagamentoValores(), imovel, form, sessao, request);
		
		sessao.setAttribute("formParcelamento", form);
	}


	//Método auxiliar que vai retornar o perfil do parcelamento
	private ParcelamentoPerfil obterPerfilParcelamentoImovel(Imovel imovel, EfetuarParcelamentoDebitosPortalActionForm form){
		Fachada fachada =  Fachada.getInstancia();

		ParcelamentoPerfil parcelamentoPerfil;

		//[FS004] Verificar existência da situação do imóvel.
		ImovelSituacao imovelSituacao = null;
		Integer situacaoAguaId = imovel.getLigacaoAguaSituacao().getId();
		Integer situacaoEsgotoId = imovel.getLigacaoEsgotoSituacao().getId();
		// Condição 1
		if (situacaoAguaId != null && situacaoEsgotoId != null) {
			imovelSituacao = fachada.obterSituacaoImovel(situacaoAguaId, situacaoEsgotoId);
			// Condição 2
			if (imovelSituacao == null) {
				imovelSituacao = fachada.obterSituacaoImovel(situacaoAguaId, null);
			}
		}

		//Obtém o perfil do parcelamento para o imóvel
		parcelamentoPerfil = null;
		
		FiltroResolucaoDiretoria filtro = new FiltroResolucaoDiretoria();
		filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.INDICADOR_PARCELAMENTO_LOJA_VIRTUAL, ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<ResolucaoDiretoria> colecaoResolucaoDiretoria = fachada.pesquisar(filtro, ResolucaoDiretoria.class.getName());
		if(!Util.isVazioOrNulo(colecaoResolucaoDiretoria)){
			ResolucaoDiretoria resolucaoDiretoria = (ResolucaoDiretoria)Util.retonarObjetoDeColecao(colecaoResolucaoDiretoria);
			Integer idResolucao = resolucaoDiretoria.getId();
			form.setResolucaoDiretoria(idResolucao.toString());
			if (imovelSituacao != null) {
				// Pega a subcategoria do imóvel
				Collection<ImovelSubcategoria> colecaoImovelSubCategoria = fachada.obterColecaoImovelSubcategorias(imovel, 1);
				Subcategoria subcategoria = null;
				Categoria categoria = null;

				if (colecaoImovelSubCategoria != null && !colecaoImovelSubCategoria.isEmpty()) {
					Iterator<ImovelSubcategoria> iteretorImovelSubCategoria = colecaoImovelSubCategoria.iterator();
					int quantidadeEconomisas = 0;
					int maiorQuantidadeEconomisas = 0;

					while (iteretorImovelSubCategoria.hasNext()) {
						ImovelSubcategoria imovelSubCategoria = (ImovelSubcategoria) iteretorImovelSubCategoria	.next();
						quantidadeEconomisas = imovelSubCategoria.getQuantidadeEconomias();
						if (quantidadeEconomisas > maiorQuantidadeEconomisas) {
							maiorQuantidadeEconomisas = quantidadeEconomisas;
							subcategoria = imovelSubCategoria.getComp_id().getSubcategoria();
							categoria = subcategoria.getCategoria();

						}
					}
				}
				parcelamentoPerfil = fachada.obterPerfilParcelamento(imovel.getId(), imovelSituacao.getImovelSituacaoTipo().getId(),
						imovel.getImovelPerfil().getId(), subcategoria.getId(), idResolucao, categoria.getId());

				if(parcelamentoPerfil == null){
					//Condição 1 - iper_id = iper_id do imovel e scat_id = scat_id do imovel
					parcelamentoPerfil = fachada.obterPerfilParcelamento(imovel.getId(),imovelSituacao.getImovelSituacaoTipo().getId(),
							imovel.getImovelPerfil().getId(), subcategoria.getId(), idResolucao, null);

					if (parcelamentoPerfil == null) {
						parcelamentoPerfil = fachada.obterPerfilParcelamento(imovel.getId(),imovelSituacao.getImovelSituacaoTipo().getId(),
								imovel.getImovelPerfil().getId(), null, idResolucao, categoria.getId());

						//Condição 2 - iper_id = iper_id do imovel e scat_id = null do imovel
						if (parcelamentoPerfil == null) {
							parcelamentoPerfil = fachada.obterPerfilParcelamento(imovel.getId(),imovelSituacao.getImovelSituacaoTipo().getId(),
									imovel.getImovelPerfil().getId(), null, idResolucao, null);

							if (parcelamentoPerfil == null) {
								parcelamentoPerfil = fachada.obterPerfilParcelamento(imovel.getId(),imovelSituacao.getImovelSituacaoTipo().getId(),
										null, subcategoria.getId(), idResolucao,categoria.getId());

								//Condição 3 - iper_id = null do imovel e scat_id = scat_id do imovel
								if (parcelamentoPerfil == null) {
									parcelamentoPerfil = fachada.obterPerfilParcelamento(imovel.getId(),imovelSituacao.getImovelSituacaoTipo().getId(),
											null, subcategoria.getId(), idResolucao,null);

									if(parcelamentoPerfil == null){
										parcelamentoPerfil = fachada.obterPerfilParcelamento(imovel.getId(),imovelSituacao.getImovelSituacaoTipo().getId(), 
												null, null, idResolucao, categoria.getId());

										//Condição 4 - iper_id = null do imovel e scat_id = null
										if (parcelamentoPerfil == null) {
											parcelamentoPerfil = fachada.obterPerfilParcelamento(imovel.getId(),imovelSituacao.getImovelSituacaoTipo().getId(), 
													null, null, idResolucao, null);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return parcelamentoPerfil;
	}

	private void obterOpcaoParcelamento(Collection<ContaValoresHelper> colecaoContaValores, Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamento,
			Imovel imovel, EfetuarParcelamentoDebitosPortalActionForm form, HttpSession sessao, HttpServletRequest request){

		//Configurando o helper dos indicadores de parcelamento
		IndicadoresParcelamentoHelper indicadoresParcelamentoHelper = new IndicadoresParcelamentoHelper();
		indicadoresParcelamentoHelper.setIndicadorContasRevisao(new Integer(2));
		indicadoresParcelamentoHelper.setIndicadorDebitosACobrar(new Integer(1));
		indicadoresParcelamentoHelper.setIndicadorCreditoARealizar(new Integer(1));
		indicadoresParcelamentoHelper.setIndicadorGuiasPagamento(new Integer(1));
		indicadoresParcelamentoHelper.setIndicadorAcrescimosImpotualidade(new Integer(1));
		indicadoresParcelamentoHelper.setIndicadorDividaAtiva(new Integer(3));

		///Configurando os parâmetros para obter opções de parcelamento 
		Integer resolucaoDiretoria = 14;
		Integer codigoImovel = imovel.getId();
		BigDecimal valorEntradaInformado = null;
		/*
		 * Caso seja uma mudança no indicador de restabelecimento o valor entrada informado vai ter
		 * null, pois o sitema vai calcular o valor mínimo e atribuir ao valor informado
		 */
		String mudancaIndicadorRestabelecimento = request.getParameter("mudancaIndicadorRestabelecimento");
		if(mudancaIndicadorRestabelecimento != null){
			form.setValorEntradaInformado(null);
		}else{
			if(form.getValorEntradaInformado() != null){
				valorEntradaInformado = Util.formatarMoedaRealparaBigDecimal(form.getValorEntradaInformado());
			}
		}
		Integer situacaoAguaId = imovel.getLigacaoAguaSituacao().getId();
		Integer situacaoEsgotoId = imovel.getLigacaoEsgotoSituacao().getId();
		Integer perfilImovel = imovel.getImovelPerfil().getId();
		Integer indicadorRestabelecimento = null;
		indicadorRestabelecimento = (form.getIndicadorRestabelecimento().equals("1") ? 1 : 2);
		BigDecimal valorDebitoTotalAtualizado = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoTotalAtualizado());
		BigDecimal valorTotalMultas = Util.formatarMoedaRealparaBigDecimal(form.getValorMulta());
		BigDecimal valorTotalJurosMora = Util.formatarMoedaRealparaBigDecimal(form.getValorJurosMora());
		BigDecimal valorTotalAtualizacoesMonetarias = Util.formatarMoedaRealparaBigDecimal(form.getValorAtualizacaoMonetaria());
		Integer numeroReparcelamentoConsecutivos = (imovel.getNumeroReparcelamentoConsecutivos() == null) ? 0 :imovel.getNumeroReparcelamentoConsecutivos().intValue();
		//Usuário
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, "INTERNET"));
		Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName()));
		//Fim Usuario
		BigDecimal valorDebitoACobrarParcelamentoImovelBigDecimal = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoACobrarParcelamento());
		String inicioIntervaloParcelamento = "01/0001";
		Integer inicioIntervaloParcelamentoFormatado = 101;
		Integer fimIntervaloParcelamentoFormatado = 999912;
		BigDecimal valorCreditoARealizar = Util.formatarMoedaRealparaBigDecimal(form.getValorCreditoARealizar());

		//Configurando os indicadores para o concluir parcelamento (valores explicitados no caso de uso)
		form.setIndicadorAcrescimosImpotualidade("1");
		form.setIndicadorContasRevisao("2");
		form.setIndicadorCreditoARealizar("1");
		form.setIndicadorDebitosACobrar("1");
		form.setIndicadorDividaAtiva("3");
		form.setIndicadorGuiasPagamento("1");
				
		/*
		 * [SB0002] - Obter Opções de Parcelamento de acordo com a entrada informada
		 * CARREGANDO O HELPER COM AS INFORMAÇÕES DO PARCELAMENTO
		 */
		ObterOpcoesDeParcelamentoHelper helper = new ObterOpcoesDeParcelamentoHelper(
				new Integer(resolucaoDiretoria), new Integer(codigoImovel), valorEntradaInformado, situacaoAguaId, 
				situacaoEsgotoId, perfilImovel, inicioIntervaloParcelamento, indicadorRestabelecimento, 
				colecaoContaValores, valorDebitoTotalAtualizado, valorTotalMultas, valorTotalJurosMora, 
				valorTotalAtualizacoesMonetarias, numeroReparcelamentoConsecutivos, colecaoGuiaPagamento, usuario, 
				valorDebitoACobrarParcelamentoImovelBigDecimal, inicioIntervaloParcelamentoFormatado,
				fimIntervaloParcelamentoFormatado, indicadoresParcelamentoHelper,valorCreditoARealizar);

		NegociacaoOpcoesParcelamentoHelper opcoesParcelamento = Fachada.getInstancia().obterOpcoesDeParcelamento(helper);


		BigDecimal descontoAcrescimosImpontualidade = opcoesParcelamento.getValorDescontoAcrecismosImpotualidade();
		BigDecimal descontoAntiguidadeDebito = opcoesParcelamento.getValorDescontoAntiguidade();
		BigDecimal descontoInatividadeLigacaoAgua = opcoesParcelamento.getValorDescontoInatividade();
		BigDecimal percentualDescontoAcrescimosImpontualidade = opcoesParcelamento.getPercentualDescontoAcrescimosImpontualidade();
		BigDecimal percentualDescontoAntiguidadeDebito = opcoesParcelamento.getPercentualDescontoAntiguidadeDebito();
		BigDecimal percentualDescontoInatividadeLigacaoAgua = opcoesParcelamento.getPercentualDescontoInatividadeLigacaoAgua();
		BigDecimal valorPagamentoAVista = new BigDecimal("0.00");
		ParcelamentoPerfil parcelamentoPerfil = opcoesParcelamento.getParcelamentoPerfil();
		BigDecimal valorMinimoPrestacao = opcoesParcelamento.getValorMinimoPrestacao();
		BigDecimal valorTotalImpostosConta = new BigDecimal("0.00");
		BigDecimal descontoSancoesRDEspecial = opcoesParcelamento.getValorDescontoSancoesRDEspecial();
		BigDecimal descontoTarifaSocialRDEspecial = opcoesParcelamento.getValorDescontoTarifaSocialRDEspecial();
		BigDecimal descontoTotalPagamentoAVista = opcoesParcelamento.getValorTotalDescontoPagamentoAVista();
		BigDecimal valorEntradaMinima = opcoesParcelamento.getValorEntradaMinima();
		BigDecimal valorDebitoDesconto = opcoesParcelamento.getValorDebitoDesconto();
		valorMinimoPrestacao = opcoesParcelamento.getValorMinimoPrestacao();

		Collection<OpcoesParcelamentoHelper> colecaoOpcoesParcelamento = opcoesParcelamento.getOpcoesParcelamento();
		
		if (valorEntradaInformado != null && valorEntradaInformado.compareTo(valorEntradaMinima.setScale(
				Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO)) == -1) {
			request.setAttribute("entradaInformadaMenorMinima", true);
			request.setAttribute("entradaMinima", valorEntradaMinima);
		}else{
			if(valorEntradaInformado == null || valorEntradaInformado.intValue() == 0){
				form.setValorEntradaInformado(Util.formatarBigDecimalParaStringComVirgula(valorEntradaMinima));
			}
			
			sessao.setAttribute("opcoesParcelamento",opcoesParcelamento);
			sessao.setAttribute("colecaoOpcoesParcelamento",colecaoOpcoesParcelamento);

			// Limpa os EP da Coleção de Contas
			if(colecaoContaValores != null && !colecaoContaValores.isEmpty()){
				Iterator<ContaValoresHelper> contaValores = colecaoContaValores.iterator();
				while(contaValores.hasNext()) {
					ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();
					if (contaValoresHelper.getIndicadorContasDebito() != null && !contaValoresHelper.getIndicadorContasDebito().equals(new Integer("2"))){
						contaValoresHelper.setIndicadorContasDebito(null);
					}
				}
			}

			// Limpando a opção de parcelamento
			if(!Util.isVazioOrNulo(colecaoOpcoesParcelamento)){
				Iterator<OpcoesParcelamentoHelper> opcoesParcelamentoValores = colecaoOpcoesParcelamento.iterator();
				while(opcoesParcelamentoValores.hasNext()) {
					OpcoesParcelamentoHelper opcoesParcelamentoHelper = (OpcoesParcelamentoHelper) opcoesParcelamentoValores.next();
					opcoesParcelamentoHelper.setIndicadorQuantidadeParcelas(null);
					form.setIndicadorQuantidadeParcelas(null);
				}
			}else{
				request.setAttribute("valorEntradaInvalido", true);
			}

			// Verifica se o valor do débito menos o valor dos descontos é menor que o valor minimo da parcela
			BigDecimal valorTotalDescontos = new BigDecimal("0.00");
			BigDecimal resultadoDiferenca = new BigDecimal("0.00");
			valorTotalDescontos.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
			valorTotalDescontos = descontoAcrescimosImpontualidade.add(descontoAntiguidadeDebito);
			valorTotalDescontos = valorTotalDescontos.add(descontoInatividadeLigacaoAgua);
			valorTotalDescontos = valorTotalDescontos.add(descontoSancoesRDEspecial);
			valorTotalDescontos = valorTotalDescontos.add(descontoTarifaSocialRDEspecial);

			resultadoDiferenca.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
			resultadoDiferenca = valorDebitoTotalAtualizado.subtract(valorTotalDescontos);


			if (!Fachada.getInstancia().verificarQtdeReparcelamentoPerfil(parcelamentoPerfil.getId(),new Short(numeroReparcelamentoConsecutivos.shortValue()))){
				request.setAttribute("numeroReparcelamentos", true);
			}else{

				// Coloca os valores no formulário
				form.setDescontoAcrescimosImpontualidade(Util.formatarMoedaReal(descontoAcrescimosImpontualidade));
				form.setDescontoAntiguidadeDebito(Util.formatarMoedaReal(descontoAntiguidadeDebito));
				form.setDescontoInatividadeLigacaoAgua(Util.formatarMoedaReal(descontoInatividadeLigacaoAgua));
				form.setPercentualDescontoAcrescimosImpontualidade(Util.formatarMoedaReal(percentualDescontoAcrescimosImpontualidade));
				form.setValorTotalDescontos(Util.formatarMoedaReal(valorTotalDescontos));
				form.setDescontoSancoesRDEspecial(Util.formatarMoedaReal(descontoSancoesRDEspecial));
				form.setDescontoTarifaSocialRDEspecial(Util.formatarMoedaReal(descontoTarifaSocialRDEspecial));

				valorTotalImpostosConta = obterValorImpostosDasContasDoParcelamento(colecaoContaValores);

				valorPagamentoAVista.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				valorPagamentoAVista = valorDebitoTotalAtualizado.subtract(descontoTotalPagamentoAVista);

				valorPagamentoAVista = valorPagamentoAVista.subtract(valorTotalImpostosConta);

				// Colocando os valores no formulário
				form.setValorDebitoTotalAtualizado(Util.formatarMoedaReal(valorDebitoTotalAtualizado));
				form.setValorDescontoPagamentoAVista(Util.formatarMoedaReal(descontoTotalPagamentoAVista));
				form.setValorPagamentoAVista(Util.formatarMoedaReal(valorPagamentoAVista));
				form.setValorTotalImpostos(Util.formatarMoedaReal(valorTotalImpostosConta));
				form.setValorDesconto(Util.formatarMoedaReal(valorDebitoDesconto));
				
				if (percentualDescontoAntiguidadeDebito != null) {
					form.setPercentualDescontoAntiguidadeDebito(Util.formatarMoedaReal(percentualDescontoAntiguidadeDebito));
				} else {
					form.setPercentualDescontoAntiguidadeDebito("0.00");
				}

				if (percentualDescontoInatividadeLigacaoAgua != null) {
					form.setPercentualDescontoInatividadeLigacaoAgua(Util.formatarMoedaReal(percentualDescontoInatividadeLigacaoAgua));
				} else {
					form.setPercentualDescontoInatividadeLigacaoAgua("0.00");
				}

				if (parcelamentoPerfil != null) {
					form.setParcelamentoPerfilId(parcelamentoPerfil.getId().toString());
					sessao.setAttribute("parcelamentoPerfil", parcelamentoPerfil);
				}

				// O valor do débito é menor que o valor da parcela mínima permitida.
				// Utilizar a opção Pagamento à Vista. 
				if (valorDebitoTotalAtualizado.compareTo(valorMinimoPrestacao) == -1){
					request.setAttribute("vlDebitoMenor","1");
				}
				form.setValorMinimoPrestacao(Util.formatarMoedaReal(valorMinimoPrestacao));

				String habilitaPagamentoAVista = "1";
				if (colecaoContaValores == null || colecaoContaValores.isEmpty()) {
					habilitaPagamentoAVista = "2";
				}
				sessao.setAttribute("habilitaPagamentoAVista", habilitaPagamentoAVista);

				sessao.setAttribute("colecaoContaValoresNegociacao",colecaoContaValores);
				sessao.setAttribute("colecaoGuiaPagamentoNegociacao", colecaoGuiaPagamento);
				sessao.setAttribute("valorAcrescimosImpontualidadeNegociacao", 
						Util.formatarMoedaRealparaBigDecimal(form.getValorAcrescimosImpontualidade()));
			}
		}
	}

	private BigDecimal obterValorImpostosDasContasDoParcelamento(Collection<ContaValoresHelper> colecaoContas){
		BigDecimal valorTotalImpostos = BigDecimal.ZERO;
		if (colecaoContas != null && !colecaoContas.isEmpty()) {
			Iterator<ContaValoresHelper> contas = colecaoContas.iterator();
			while (contas.hasNext()) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contas.next();

				if (contaValoresHelper.getConta().getValorImposto() != null) {
					valorTotalImpostos = valorTotalImpostos.add(contaValoresHelper.getConta().getValorImposto());
				}
			}
		}
		return valorTotalImpostos;
	}
}
