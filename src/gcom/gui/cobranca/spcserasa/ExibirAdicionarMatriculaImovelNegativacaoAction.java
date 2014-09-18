package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para realização da inserção de um Comando de Negativação 
 * (Por Matrícula de Imóveis)
 * 
 * @author Ana Maria
 * @date 06/11/2007
 */
public class ExibirAdicionarMatriculaImovelNegativacaoAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirAdicionarMatriculaImovelNegativacao");
		
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		InserirComandoNegativacaoActionForm inserirComandoNegativacaoActionForm = (InserirComandoNegativacaoActionForm) actionForm;
		
		httpServletRequest.getParameter("indicadorBaixaRenda");
		httpServletRequest.getAttribute("indicadorBaixaRenda");
		
		String limparForm = httpServletRequest.getParameter("limparForm");
		
		//Limpar form
		if (limparForm != null && !limparForm.equals("")) {
			httpServletRequest.setAttribute("idImovelDebitosNaoEncontrado",
					null);
			inserirComandoNegativacaoActionForm.setMatriculaImovelDebitos(null);
			limparCampos(httpServletRequest, sessao, inserirComandoNegativacaoActionForm);

		}
		//Retorno Imóvel de PopUps
		if (httpServletRequest.getParameter("idCampoEnviarDados") != null &&
			httpServletRequest.getParameter("descricaoCampoEnviarDados") != null &&
			httpServletRequest.getParameter("tipoConsulta") != null){
			
			inserirComandoNegativacaoActionForm.setIdImovelDebitos(httpServletRequest.getParameter("idCampoEnviarDados"));
			inserirComandoNegativacaoActionForm.setMatriculaImovelDebitos(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			
			sessao.setAttribute("idImovelDebitosNaoEncontrado", "true");
		}
		
		//Atualizar Imóvel já adicionadao(link)
		Integer idCliente = null;
		Integer idClienteRelacaoTipo = null;
		if(httpServletRequest.getParameter("matriculaImovel") != null){
			sessao.removeAttribute("inserirComandoNegativacaoActionForm");
			if(httpServletRequest.getParameter("matriculaImovel") != null){
				inserirComandoNegativacaoActionForm.setIdImovelDebitos(httpServletRequest.getParameter("matriculaImovel"));
				idCliente = new Integer(httpServletRequest.getParameter("idCliente"));
				idClienteRelacaoTipo = new Integer(httpServletRequest.getParameter("idClienteRelacaoTipo"));
			}
			if(sessao.getAttribute("imovelDebitos") != null){
				sessao.removeAttribute("imovelDebitos");
			}
			
			httpServletRequest.setAttribute("atualizar", "atualizar");
		}
		
		//recupera o código do imóvel
		String idImovelDebitos = inserirComandoNegativacaoActionForm.getIdImovelDebitos();
		
		Imovel imovel = null;
		
		if ((idImovelDebitos != null && !idImovelDebitos.equalsIgnoreCase(""))) {
			if (sessao.getAttribute("imovelDebitos") != null) {
				imovel = (Imovel) sessao.getAttribute("imovelDebitos");
				if (!(imovel.getId().toString().equals(idImovelDebitos.trim()))) {
					imovel = fachada.consultarImovelHistoricoFaturamento(new Integer(
							idImovelDebitos.trim()));
				}
	
			} else {
				imovel = fachada.consultarImovelHistoricoFaturamento(new Integer(idImovelDebitos.trim()));
			}				
			
			if (imovel != null) {
				//CRC3323 - comentado por Vivianne Sousa - analista:Fatima Sampaio - 07/05/2010
//				//[FS0022] Verificar existência de situação de cobrança para o imóvel 
//				CobrancaSituacao cobrancaSituacao = fachada.pesquisarImovelCobrancaSituacao(imovel.getId());
//				
//				if(cobrancaSituacao != null && !cobrancaSituacao.equals("")){
//					throw new ActionServletException(
//							"atencao.eistencia_situacao_cobranca_imovel", imovel.getId().toString(), 
//							cobrancaSituacao.getDescricao());							
//				}
//				
//				Object[] dadosImovel = fachada.pesquisarDadosImovelParaNegativacao(imovel.getId());
//				
//				if(dadosImovel[0] != null){
//					FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
//					filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(FiltroCobrancaSituacao.ID,(Integer)dadosImovel[0]));
//					
//					Collection colCobrancaSituacao = Fachada.getInstancia().pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class.getName() );
//					CobrancaSituacao cobSituacao = (CobrancaSituacao) colCobrancaSituacao.iterator().next();
//					throw new ActionServletException(
//							"atencao.eistencia_situacao_cobranca_imovel", imovel.getId().toString(), 
//							cobSituacao.getDescricao());	
//				}
				
				// comentado por Vivianne Sousa  - 05/10/2010 - analista:Fatima Sampaio
				//CRC3323 - adicionado por Vivianne Sousa - analista:Fatima Sampaio - 06/05/2010
				//[FS0030] - Verificar existência de inclusão no negativador para o imóvel
//				boolean  existeInclusaoNoNegativadorParaImovel = fachada.
//					verificarExistenciaDeInclusaoNoNegativadorParaImovel(imovel.getId(),
//						new Integer(inserirComandoNegativacaoActionForm.getIdNegativador()));
//				
//				if(existeInclusaoNoNegativadorParaImovel){
//					throw new ActionServletException(
//							"atencao.imovel_negativado", imovel.getId().toString(), 
//							inserirComandoNegativacaoActionForm.getNomeNegativador());
//				}
				
				//[FS0024] Verificar imóvel na tarifa social
				ImovelPerfil imovelPerfil = fachada.obterImovelPerfil(imovel.getId());
				
				if(imovelPerfil.getId().equals(ImovelPerfil.TARIFA_SOCIAL)){
					throw new ActionServletException(
							"atencao.imovel_tarifa_social_nao_negativar", null, imovel.getId().toString());					
				}
				
				//[FS0025] Verificar imóvel baixa renda
				Integer idPerfilQuadra = fachada.obterQuadraPerfil(imovel.getId());
				//CRC4496 - adicionado por Vivianne Sousa - 29/06/2010 - Adriana Ribeiro
				if(inserirComandoNegativacaoActionForm.getIndicadorBaixaRenda().equals(ConstantesSistema.NAO.toString())){
					if(idPerfilQuadra.equals(2)){
						throw new ActionServletException(
								"atencao.imovel_baixa_renda_nao_negativar", null, imovel.getId().toString());	
					}
				}
				
				SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
				
				// RM3388 - Autor: Ivan Sergio; Analista: Adriana; 26/01/2011
				//[FS0028] Verificar imóvel órgão público
				if (inserirComandoNegativacaoActionForm.getIndicadorImovelCategoriaPublico().equals(ConstantesSistema.NAO_CONFIRMADA)) {
					int idCategoria = 0;
					Categoria categoria = null;
					categoria = fachada.obterPrincipalCategoriaImovel(imovel.getId());
					if (categoria != null &&
							!sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER)) {
						idCategoria = categoria.getId(); 
						if(idCategoria == Categoria.PUBLICO_INT){
							throw new ActionServletException(
									"atencao.imovel_orgao_publico_nao_negativar", null, imovel.getId().toString());	
						}
					}
				}
						
				//descomentado por Vivianne Sousa  - 05/10/2010 - analista:Fatima Sampaio
				//CRC3323 - comentado por Vivianne Sousa - analista:Fatima Sampaio - 07/05/2010
				//[FS0015] Verificar existência de negativação para o imóvel no negativador
				Boolean imovelNegativado = fachada.verificarExistenciaNegativacaoImovel(imovel.getId());
				if(imovelNegativado){
					throw new ActionServletException(
							"atencao.imovel_ja_negativado", null, imovel.getId().toString());					
				}
								
				sessao.setAttribute("imovelDebitos", imovel);
				inserirComandoNegativacaoActionForm.setCodigoImovel(imovel.getId().toString());
	
				//seta na tela a inscrição do imovel
				httpServletRequest.setAttribute("idImovelDebitosNaoEncontrado", null);

				inserirComandoNegativacaoActionForm.setMatriculaImovelDebitos(fachada
						.pesquisarInscricaoImovel(new Integer(
								idImovelDebitos.trim())));

				// seta a situação de agua
				inserirComandoNegativacaoActionForm.setSituacaoAguaDebitos(imovel
						.getLigacaoAguaSituacao().getDescricao());
				// seta a situação de esgoto
				inserirComandoNegativacaoActionForm.setSituacaoEsgotoDebitos(imovel
						.getLigacaoEsgotoSituacao().getDescricao());

				//pesquisa o endereço
				String enderecoFormatado = fachada.pesquisarEndereco(imovel.getId());
				
				//[SB0005] Obter Débito do Imóvel	

				//Período de referência do débito
				String referenciaInicial = Util.getAnoMesComoString(Util.subtrairNumeroAnosDeUmaData(new Date(), -5));
				String referenciaFinal = Util.getAnoMesComoString(new Date());
				inserirComandoNegativacaoActionForm.setReferenciaInicial(Util.formatarAnoMesParaMesAno(referenciaInicial));
				inserirComandoNegativacaoActionForm.setReferenciaFinal(Util.formatarAnoMesParaMesAno(referenciaFinal));
				
				//Período de vencimento do débito
					
				Integer numeroDiasVencimentoCobranca = new Integer(sistemaParametro.getNumeroDiasVencimentoCobranca());			
				Date dataVencimentoFinal = Util.subtrairNumeroDiasDeUmaData(new Date(), numeroDiasVencimentoCobranca);
				Date dataVencimentoInicial = Util.subtrairNumeroAnosDeUmaData(dataVencimentoFinal, -5);
				inserirComandoNegativacaoActionForm.setDataVencimentoInicial(Util.formatarData(dataVencimentoInicial));
				inserirComandoNegativacaoActionForm.setDataVencimentoFinal(Util.formatarData(dataVencimentoFinal));
				
				// seta valores constantes para chamar o metodo que consulta debitos do imovel
				Integer tipoImovel = new Integer(1);
				Integer indicadorPagamento = new Integer(1);
				Integer indicadorConta = new Integer(2);
				Integer indicadorDebito = new Integer(2);
				Integer indicadorCredito = new Integer(2);
				Integer indicadorNotas = new Integer(2);
				Integer indicadorGuias = new Integer(1);
				Integer indicadorAtualizar = new Integer(1);

				// Obtendo os débitos do imovel
				ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = fachada
						.obterDebitoImovelOuCliente(tipoImovel.intValue(),
								idImovelDebitos.trim(), null, null,
								referenciaInicial, referenciaFinal,
								dataVencimentoInicial,
								dataVencimentoFinal, indicadorPagamento
										.intValue(), indicadorConta
										.intValue(), indicadorDebito
										.intValue(), indicadorCredito
										.intValue(), indicadorNotas
										.intValue(), indicadorGuias
										.intValue(), indicadorAtualizar
										.intValue(), null);

				Collection<ContaValoresHelper> colecaoContaValores = new ArrayList<ContaValoresHelper>();
				Collection<ContaValoresHelper> colecaoContaValoresTotal= colecaoDebitoImovel
						.getColecaoContasValores();

				ContaValoresHelper dadosConta = null;

				BigDecimal valorConta = new BigDecimal("0.00");
				BigDecimal valorAcrescimo = new BigDecimal("0.00");
				BigDecimal valorAgua = new BigDecimal("0.00");
				BigDecimal valorEsgoto = new BigDecimal("0.00");
				BigDecimal valorDebito = new BigDecimal("0.00");
				BigDecimal valorCredito = new BigDecimal("0.00");
				BigDecimal valorImposto = new BigDecimal("0.00");

				Boolean existeParcelamento = false;
				Collection colecaoContasIds = null;
				if (colecaoContaValoresTotal != null
						&& !colecaoContaValoresTotal.isEmpty()) {
					colecaoContasIds = new ArrayList();
					//adicionado por Vivianne Sousa - 22/06/2010 - Fatíma Sampaio
					fachada.retirarContaPagaOuParceladaEEntradaParcelamento(colecaoContaValoresTotal);
					
					Iterator<ContaValoresHelper> colecaoContaValoresIterator = colecaoContaValoresTotal
							.iterator();
					// percorre a colecao de conta somando o valor para obter um valor total
					while (colecaoContaValoresIterator.hasNext()) {

						dadosConta = (ContaValoresHelper) colecaoContaValoresIterator.next();
						
						if(dadosConta.getValorPago() == null || dadosConta.getValorPago().equals("")
							|| dadosConta.getValorPago().floatValue() == 0.0){
							valorConta = valorConta.add(dadosConta.getConta().getValorTotal());
							valorAcrescimo = valorAcrescimo.add(dadosConta.getValorTotalContaValores());
							valorAgua = valorAgua.add(dadosConta.getConta().getValorAgua());
							valorEsgoto = valorEsgoto.add(dadosConta.getConta().getValorEsgoto());
							valorDebito = valorDebito.add(dadosConta.getConta().getDebitos());
							valorCredito = valorCredito.add(dadosConta.getConta().getValorCreditos());
							valorImposto = valorImposto.add(dadosConta.getConta().getValorImposto());
							//[FS0019] Verificar existência de parcelamento
							existeParcelamento = fachada.pesquisarExisteciaParcelamentoConta(dadosConta.getConta().getId());
							if(existeParcelamento){
								dadosConta.setExisteParcelamento(ConstantesSistema.SIM);
								existeParcelamento = true;
							}
							colecaoContaValores.add(dadosConta);
							colecaoContasIds.add(dadosConta.getConta().getId());
						}
					}
					sessao.setAttribute("colecaoContasIds",colecaoContasIds);
				}

				// Pesquisar cliente do imovel
				Collection clientesImovel = fachada.pesquisarClienteImovelParaNegativacao(new Integer(idImovelDebitos.trim()));
				
				//[FS0019] Verificar existência de parcelamento								
				if(existeParcelamento){
					Cliente cliente = fachada.pesquisarClienteResponsavelParcelamento(new Integer(idImovelDebitos.trim()));
					if(cliente != null && !cliente.equals("")){
						if(cliente.getCpf()!= null || cliente.getCnpj() != null){
					      ClienteImovel clienteParcelamento = new ClienteImovel();
					      clienteParcelamento.setCliente(cliente);
					      ClienteRelacaoTipo relacaoCliente = new ClienteRelacaoTipo();
					      relacaoCliente.setId(4);
					      relacaoCliente.setDescricao("Responsável pelo Parcelamento");
					      clienteParcelamento.setClienteRelacaoTipo(relacaoCliente);
					      
					      /*
					       * Felipe Santos - 06/12/2011
					       * 
					       * Seta o id 0 e o imóvel para ser recuperado por atributo de sessão
					       */
					      clienteParcelamento.setId(new Integer("0"));
					      clienteParcelamento.setImovel(imovel);
					      clientesImovel.add(clienteParcelamento);
					      
					      /*
					       * Felipe Santos - 06/12/2011
					       * 
					       * Seta o objeto como atributo de sessão
					       */
					      sessao.setAttribute("clienteParcelamento",clienteParcelamento);
						}
					}else{
						sessao.removeAttribute("clienteParcelamento");
					}
				}else{
					sessao.removeAttribute("clienteParcelamento");
				}
				
				if(idCliente != null && idClienteRelacaoTipo != null){
					Iterator colecaoClientesImovel = clientesImovel.iterator();
					while (colecaoClientesImovel.hasNext()) {
						ClienteImovel clienteImovel = (ClienteImovel) colecaoClientesImovel.next();
						if(clienteImovel.getCliente().getId().equals(idCliente) &&
								clienteImovel.getClienteRelacaoTipo().getId().equals(idClienteRelacaoTipo)){
							inserirComandoNegativacaoActionForm.setClienteSelecionado(clienteImovel.getId().toString());
						}
					}
				}else{
					//[FS0020] Verificar existência de cliente com CPF ou CNPJ
					boolean achouCpfCnpj = false;
					Integer clienteUsuario = null;
					Integer clienteRespParcelamento = null;
					Integer clientePropietario = null;
					Integer clienteResponsavel = null;
					Iterator colecaoClientesImovel = clientesImovel.iterator();
					while (colecaoClientesImovel.hasNext()) {
						ClienteImovel clienteImovel = (ClienteImovel) colecaoClientesImovel.next();
						if(clienteImovel.getCliente().getCpf() != null || clienteImovel.getCliente().getCnpj() != null){
							if(clienteImovel.getClienteRelacaoTipo().getId().shortValue() == (ClienteRelacaoTipo.USUARIO)){
								clienteUsuario = clienteImovel.getId();
							}else if (clienteImovel.getClienteRelacaoTipo().getId().shortValue() == (ClienteRelacaoTipo.RESPONSAVEL)){
								clienteResponsavel = clienteImovel.getId();
							}else if (clienteImovel.getClienteRelacaoTipo().getId().shortValue() == (ClienteRelacaoTipo.PROPRIETARIO)){
								clientePropietario = clienteImovel.getId();
							}else{
								clienteRespParcelamento = clienteImovel.getId();
							}
							achouCpfCnpj = true;
						}						
					}
					
					if(!achouCpfCnpj){
						throw new ActionServletException(
								"atencao.cliente_sem_cpf_cnpj_negativacao",null, idImovelDebitos);
					}
				

					if(clienteUsuario != null){
						inserirComandoNegativacaoActionForm.setClienteSelecionado(clienteUsuario.toString());	
					}else if(clienteRespParcelamento != null){
						inserirComandoNegativacaoActionForm.setClienteSelecionado(clienteRespParcelamento.toString());
					}else if(clientePropietario != null){
						inserirComandoNegativacaoActionForm.setClienteSelecionado(clientePropietario.toString());
					}else{
						inserirComandoNegativacaoActionForm.setClienteSelecionado(clienteResponsavel.toString());
					}
				}
				sessao.setAttribute("imovelClientes",clientesImovel);
				
				//Coleção de guia de pagamento
				Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = new ArrayList();
				Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValoresTotal = colecaoDebitoImovel
						.getColecaoGuiasPagamentoValores();

				BigDecimal valorGuiaPagamento = new BigDecimal("0.00");
				GuiaPagamentoValoresHelper dadosGuiaPagamentoValoresHelper = null;

				if (colecaoGuiaPagamentoValoresTotal != null
						&& !colecaoGuiaPagamentoValoresTotal.isEmpty()) {
					
					//adicionado por Vivianne Sousa - 22/06/2010 - Fatíma Sampaio
					fachada.retirarGuiaPagamentoDeEntradaParcelamento(colecaoGuiaPagamentoValoresTotal);
					
					java.util.Iterator<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValoresHelperIterator = colecaoGuiaPagamentoValoresTotal
							.iterator();
					// percorre a colecao de guia de pagamento somando o valor para obter um valor total
					while (colecaoGuiaPagamentoValoresHelperIterator
							.hasNext()) {

						dadosGuiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) colecaoGuiaPagamentoValoresHelperIterator
								.next();
						if(dadosGuiaPagamentoValoresHelper.getValorPago() == null || dadosGuiaPagamentoValoresHelper.getValorPago().equals("")){
							valorGuiaPagamento = valorGuiaPagamento
							.add(dadosGuiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
							colecaoGuiaPagamentoValores.add(dadosGuiaPagamentoValoresHelper);	
						}

					}
				}

				if((colecaoContaValores == null || colecaoContaValores.isEmpty())
						&& ((colecaoGuiaPagamentoValores == null) || colecaoGuiaPagamentoValores.isEmpty())){
					limparCampos(httpServletRequest, sessao, inserirComandoNegativacaoActionForm);
					throw new ActionServletException(
						"atencao.imovel_sem_debitos_negativacao",null, idImovelDebitos);
				}else{
					
					sessao.setAttribute("enderecoFormatado",enderecoFormatado);
					
					// Manda a colecao pelo request
					sessao.setAttribute("colecaoContaValores",
							colecaoContaValores);

					// Manda a colecao e os valores total de conta pelo request
					sessao.setAttribute("valorConta", Util
							.formatarMoedaReal(valorConta));
					sessao.setAttribute("valorAcrescimo", Util
							.formatarMoedaReal(valorAcrescimo));
					sessao.setAttribute("valorAgua", Util
							.formatarMoedaReal(valorAgua));
					sessao.setAttribute("valorEsgoto", Util
							.formatarMoedaReal(valorEsgoto));
					sessao.setAttribute("valorDebito", Util
							.formatarMoedaReal(valorDebito));
					sessao.setAttribute("valorCredito", Util
							.formatarMoedaReal(valorCredito));
					sessao.setAttribute("valorContaAcrescimo", Util
							.formatarMoedaReal(valorConta
									.add(valorAcrescimo)));
					sessao.setAttribute("valorImposto", Util
							.formatarMoedaReal(valorImposto));

					// Manda a colecao e o valor total de GuiaPagamento pelo request
					sessao.setAttribute("colecaoGuiaPagamentoValores",
							colecaoGuiaPagamentoValores);
					sessao.setAttribute("valorGuiaPagamento", Util
							.formatarMoedaReal(valorGuiaPagamento));

					// Soma o valor total dos debitos e subtrai dos creditos
					BigDecimal valorTotalSemAcrescimo = valorConta
									.add(valorGuiaPagamento);

					BigDecimal valorTotalComAcrescimo = valorTotalSemAcrescimo
							.add(valorAcrescimo);

					
					BigDecimal valorToralSemAcrescimoESemJurosParcelamento = 
						valorConta.add(valorGuiaPagamento);				
					
					sessao.setAttribute("valorTotalSemAcrescimo", Util
							.formatarMoedaReal(valorTotalSemAcrescimo));
					sessao.setAttribute("valorTotalComAcrescimo", Util
							.formatarMoedaReal(valorTotalComAcrescimo));
					sessao.setAttribute("valorToralSemAcrescimoESemJurosParcelamento", 
							Util.formatarMoedaReal(valorToralSemAcrescimoESemJurosParcelamento));
				}
	
			} else {
	
				//imovel não encontrado
				httpServletRequest.setAttribute("idImovelDebitosNaoEncontrado",
						"true");
	
				inserirComandoNegativacaoActionForm
						.setMatriculaImovelDebitos("IMÓVEL INEXISTENTE");
				limparCampos(httpServletRequest, sessao, inserirComandoNegativacaoActionForm);
	
			}
		} else {
			//matricula do imovel incorreta
			inserirComandoNegativacaoActionForm
					.setIdImovelDebitos(idImovelDebitos);

			httpServletRequest.setAttribute("idImovelDebitosNaoEncontrado",
					null);
			limparCampos(httpServletRequest, sessao, inserirComandoNegativacaoActionForm);

		}
		
		return retorno;

	}

	private void limparCampos(HttpServletRequest httpServletRequest, HttpSession sessao, InserirComandoNegativacaoActionForm inserirComandoNegativacaoActionForm) {
		//limpar os dados 
		sessao.removeAttribute("enderecoFormatado");
		sessao.removeAttribute("imovelDebitos");
		sessao.removeAttribute("colecaoContaValores");
		sessao.removeAttribute("idImovelPrincipalAba");
		sessao.removeAttribute("imovelClientes");

		sessao.removeAttribute("valorConta");
		sessao.removeAttribute("valorAcrescimo");
		sessao.removeAttribute("valorAgua");
		sessao.removeAttribute("valorEsgoto");
		sessao.removeAttribute("valorDebito");
		sessao.removeAttribute("valorCredito");
		sessao.removeAttribute("valorImposto");
		sessao.removeAttribute("valorContaAcrescimo");

		sessao.removeAttribute("colecaoGuiaPagamentoValores");
		sessao.removeAttribute("valorGuiaPagamento");

		sessao.removeAttribute("valorTotalSemAcrescimo");
		sessao.removeAttribute("valorTotalComAcrescimo");
		sessao.removeAttribute("valorToralSemAcrescimoESemJurosParcelamento");


		inserirComandoNegativacaoActionForm.setIdImovelDebitos(null);
		inserirComandoNegativacaoActionForm.setSituacaoEsgotoDebitos(null);
		inserirComandoNegativacaoActionForm.setSituacaoAguaDebitos(null);
		inserirComandoNegativacaoActionForm.setCodigoImovel(null);
		inserirComandoNegativacaoActionForm.setTipoRelacao(null);
		inserirComandoNegativacaoActionForm.setReferenciaInicial(null);
		inserirComandoNegativacaoActionForm.setReferenciaFinal(null);
		inserirComandoNegativacaoActionForm.setDataVencimentoInicial(null);
		inserirComandoNegativacaoActionForm.setDataVencimentoFinal(null);
		inserirComandoNegativacaoActionForm.setLigacaoAgua(null);
		inserirComandoNegativacaoActionForm.setLigacaoEsgoto(null);
		inserirComandoNegativacaoActionForm.setMaticula(null);
		inserirComandoNegativacaoActionForm.setInscricao(null);
		inserirComandoNegativacaoActionForm.setNomeCliente(null);
		inserirComandoNegativacaoActionForm.setTipoRelacaoCliente(null);
		inserirComandoNegativacaoActionForm.setCpf(null);
		inserirComandoNegativacaoActionForm.setCnpj(null);
		inserirComandoNegativacaoActionForm.setRefInicial(null);
		inserirComandoNegativacaoActionForm.setRefFinal(null);
		inserirComandoNegativacaoActionForm.setDtInicial(null);
		inserirComandoNegativacaoActionForm.setDtFinal(null);
	}
}
