package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoOperacao;
import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoBoletim;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoOperacao;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de encerra OS caso precise
 * entrar na tela
 * 
 * @author Sávio Luiz
 * @created 18/09/2006
 */
public class EncerrarOrdemServicoAction extends GcomAction {
	/**
	 * Execute do Consultar OS Popup
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		EncerrarOrdemServicoActionForm encerrarOrdemServicoActionForm = (EncerrarOrdemServicoActionForm) actionForm;

        if ("confirmaEncerramentoRA".equals(httpServletRequest.getParameter("tipoRelatorio"))) {
			sessao.setAttribute("confirmaEncerramentoRA", httpServletRequest.getParameter("confirmado"));
        }
        
        String valorConfirmacaoEncerramentoRA = (String) sessao.getAttribute("confirmaEncerramentoRA"); 
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////////

        if ("confirmaBoletimValorZero".equals(httpServletRequest.getParameter("tipoRelatorio"))) {
			sessao.setAttribute("confirmaBoletimValorZero", httpServletRequest.getParameter("confirmado"));
		}
        
        String valorConfirmacaoBoletimValorZero = (String) sessao.getAttribute("confirmaBoletimValorZero"); 
        ///////////////////////////////////////////////////////////////////////////////////////////////
        
        Integer idOS = null;
		Integer idOSDiagnostico = null;
		OrdemServicoBoletim ordemServicoBoletim = null;
		// parte da integração com o sistema comercial
		IntegracaoComercialHelper integracaoComercialHelper = (IntegracaoComercialHelper) sessao.getAttribute("integracaoComercialHelper");
		
		Short indicadorServicoAceito = null;
		if (encerrarOrdemServicoActionForm.getIndicadorServicoAceito() != null
				&& !encerrarOrdemServicoActionForm.getIndicadorServicoAceito().equals("")) {
			indicadorServicoAceito = new Short(encerrarOrdemServicoActionForm.getIndicadorServicoAceito());
		}
		
		/*
		 * Colocado por Raphael Rossiter em 09/05/2007
		 * Caso o id do motivo de não cobrança venha com o valor -1 não será gerado o objeto
		 * ServicoNaoCobrancaMotivo dentro da OS
		 */
        
		ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo  = null;
		BigDecimal valorDebito = null;
		if (integracaoComercialHelper != null &&
			integracaoComercialHelper.getOrdemServico() != null &&
			integracaoComercialHelper.getOrdemServico().getServicoNaoCobrancaMotivo() != null &&
			integracaoComercialHelper.getOrdemServico().getServicoNaoCobrancaMotivo()
			.getId().equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			integracaoComercialHelper.getOrdemServico().setServicoNaoCobrancaMotivo(null);
			
		}else if (integracaoComercialHelper != null &&
				integracaoComercialHelper.getOrdemServico() != null &&
				integracaoComercialHelper.getOrdemServico().getServicoNaoCobrancaMotivo() != null ) {

			servicoNaoCobrancaMotivo  = 
				integracaoComercialHelper.getOrdemServico().getServicoNaoCobrancaMotivo();
				
		}
		
		if(integracaoComercialHelper != null &&
			integracaoComercialHelper.getOrdemServico() != null){
			
			valorDebito = integracaoComercialHelper.getOrdemServico().getValorAtual();
		}
		
		
		if (valorConfirmacaoEncerramentoRA == null 	|| valorConfirmacaoEncerramentoRA.equals("")) {
			// valida enter
			String idServicoTipo = encerrarOrdemServicoActionForm.getIdServicoTipo();
			String descricaoServicoTipo = encerrarOrdemServicoActionForm.getDescricaoServicoTipo();

			Collection colecaoManterDadosAtividadesOrdemServicoHelper = (Collection) sessao.getAttribute("colecaoManutencao");
			if ((idServicoTipo != null && !idServicoTipo.equals("")) && (descricaoServicoTipo == null || descricaoServicoTipo.equals(""))) {

				FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

				filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, idServicoTipo));
				filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection servicoTipoEncontrado = fachada.pesquisar(filtroServicoTipo, ServicoTipo.class.getName());

				if (servicoTipoEncontrado != null && !servicoTipoEncontrado.isEmpty()) {
					ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(servicoTipoEncontrado);
					// O serviço tipo foi encontrado
					encerrarOrdemServicoActionForm.setIdServicoTipo("" + servicoTipo.getId());
					encerrarOrdemServicoActionForm.setDescricaoServicoTipo(servicoTipo.getDescricao());

				} else {
					throw new ActionServletException("atencao.label_inexistente", null, "Serviço Tipo");
				}

			}
			// caso seja a primeira vez
			if (integracaoComercialHelper == null || integracaoComercialHelper.equals("")) {

				// [FS0002] - Validar Tipo Serviço
				// [FS0004] - Verificar preenchimento dos campos
				// [FS0007] - Validar Data de Encerramento
				// [FS0008] - Validar Data do roteiro
				fachada.validarCamposEncerrarOS(
						encerrarOrdemServicoActionForm.getIndicadorExecucao(), 
						encerrarOrdemServicoActionForm.getNumeroOS(), 
						encerrarOrdemServicoActionForm.getIdMotivoEncerramento(),
						encerrarOrdemServicoActionForm.getDataEncerramento(),
						colecaoManterDadosAtividadesOrdemServicoHelper,
						encerrarOrdemServicoActionForm.getTipoServicoReferenciaId(),
						encerrarOrdemServicoActionForm.getIndicadorPavimento(),
						encerrarOrdemServicoActionForm.getPavimento(),
						encerrarOrdemServicoActionForm.getIdTipoRetornoReferida(),
						encerrarOrdemServicoActionForm.getIndicadorDeferimento(),
						encerrarOrdemServicoActionForm.getIndicadorTrocaServico(), idServicoTipo,
						encerrarOrdemServicoActionForm.getDataRoteiro(),
						encerrarOrdemServicoActionForm.getNumeroRA(),
						encerrarOrdemServicoActionForm.getIndicadorVistoriaServicoTipo(),
						encerrarOrdemServicoActionForm.getCodigoRetornoVistoriaOs(),
						encerrarOrdemServicoActionForm.getIndicadorDiagnostico(),
                        encerrarOrdemServicoActionForm.getObservacaoEncerramento(),
                        usuarioLogado);
				
				Map validacao = validarInformacoesBoletimMedicao(new Integer(encerrarOrdemServicoActionForm.getNumeroOS()), encerrarOrdemServicoActionForm);
				
				ordemServicoBoletim = (OrdemServicoBoletim) validacao.get("ordemServicoBoletim");
				Boolean exibirMsgConfirmacao = (Boolean) validacao.get("exibirMsgConfirmacao");				
				
				if (exibirMsgConfirmacao && (valorConfirmacaoBoletimValorZero == null || !valorConfirmacaoBoletimValorZero.equalsIgnoreCase("ok"))) {
					
					httpServletRequest.setAttribute("caminhoActionConclusao","/gsan/encerrarOrdemServicoAction.do");
					// httpServletRequest.setAttribute("cancelamento", "TRUE");
					httpServletRequest.setAttribute("nomeBotao1", "Sim");
					// httpServletRequest.setAttribute("nomeBotao2", "Não");
					httpServletRequest.setAttribute("tipoRelatorio", "confirmaBoletimValorZero");
					
					return montarPaginaConfirmacao("atencao.encerrar_OS_boletim", httpServletRequest, actionMapping);
				}
			} else {
				
				Map validacao = validarInformacoesBoletimMedicao(new Integer(encerrarOrdemServicoActionForm.getNumeroOS()), encerrarOrdemServicoActionForm);
				
				ordemServicoBoletim = (OrdemServicoBoletim) validacao.get("ordemServicoBoletim");
			}

			// indicador execução seja diferente de nulo
			if (encerrarOrdemServicoActionForm.getIndicadorExecucao() != null && !encerrarOrdemServicoActionForm.getIndicadorExecucao().equals("")) {
				short indicadorExecucao = Short.parseShort(encerrarOrdemServicoActionForm.getIndicadorExecucao());
				Integer numeroOS = Util.converterStringParaInteger(encerrarOrdemServicoActionForm.getNumeroOS());
				Date dataEncerramento = null;
				if (encerrarOrdemServicoActionForm.getHoraEncerramento() != null && !encerrarOrdemServicoActionForm.getHoraEncerramento().equals("")) {
					dataEncerramento = Util.converteStringParaDateHora(encerrarOrdemServicoActionForm.getDataEncerramento() 
											+ " "
											+ encerrarOrdemServicoActionForm.getHoraEncerramento() 
											+ ":00");
				} else {
					dataEncerramento = Util.converteStringParaDateHora(encerrarOrdemServicoActionForm.getDataEncerramento()
											+ " " 
											+ Util.formatarHoraSemSegundos(new Date()) 
											+ ":00");
				}
				Date dataUltimaAlteracao = encerrarOrdemServicoActionForm.getUltimaAlteracao();

				// indicador execução seja igual a não(2)
				if (indicadorExecucao == AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_NAO) {

					OrdemServico osFiscalizacao = null;
					
					if (sessao.getAttribute("ordemServicoFiscalizacao") != null) {
						osFiscalizacao = (OrdemServico) sessao.getAttribute("ordemServicoFiscalizacao");
					}
					
					// [SB0001] - Encerrar sem execução
					fachada.encerrarOSSemExecucao(numeroOS, 
												  dataEncerramento,
												  usuarioLogado, 
												  encerrarOrdemServicoActionForm.getIdMotivoEncerramento(),
												  dataUltimaAlteracao, 
												  encerrarOrdemServicoActionForm.getObservacaoEncerramento(),
												  osFiscalizacao, 
												  encerrarOrdemServicoActionForm.getIndicadorVistoriaServicoTipo(),
												  encerrarOrdemServicoActionForm.getCodigoRetornoVistoriaOs(),
												  ordemServicoBoletim,
												  indicadorServicoAceito);
				} else {
					// indicador execução seja igual a sim(1)
					if (indicadorExecucao == AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM) {
						if (encerrarOrdemServicoActionForm.getIndicadorAtualizaComercial() != null
								&& !encerrarOrdemServicoActionForm.getIndicadorAtualizaComercial().equals("")) {
							Short indicadorComercialAtualiza = new Short(encerrarOrdemServicoActionForm.getIndicadorAtualizaComercial());

							if (indicadorComercialAtualiza.equals(ServicoTipo.INDICADOR_ATUALIZA_COMERCIAL_SIM)) {
								// caso não exista o objeto helper na sessão
								// então é a primeira vez
								if (integracaoComercialHelper == null || integracaoComercialHelper.equals("")) {
									// caso exista tipo de serviço
									if (encerrarOrdemServicoActionForm.getTipoServicoOSId() != null
											&& !encerrarOrdemServicoActionForm.getTipoServicoOSId().equals("")) {

										FiltroServicoTipoOperacao filtroServicoTipoOperacao = new FiltroServicoTipoOperacao();
										filtroServicoTipoOperacao.adicionarCaminhoParaCarregamentoEntidade("operacao");
										filtroServicoTipoOperacao.adicionarParametro(new ParametroSimples(FiltroServicoTipoOperacao.ID_SERVICO_TIPO, encerrarOrdemServicoActionForm.getTipoServicoOSId()));
										Collection colecaoServicoTipoOperacao = fachada.pesquisar(filtroServicoTipoOperacao, ServicoTipoOperacao.class.getName());
										// caso exista uma funcionalidade assiciada ao serviço tipo
										if (colecaoServicoTipoOperacao != null && !colecaoServicoTipoOperacao.isEmpty()) {
											ServicoTipoOperacao servicoTipoOperacao = (ServicoTipoOperacao) Util.retonarObjetoDeColecao(colecaoServicoTipoOperacao);
											String caminhoOperacao = servicoTipoOperacao.getOperacao().getCaminhoUrl();
											// caso exista o caminho da operação
											if (caminhoOperacao != null && !caminhoOperacao.equals("")) {
												int tamanhoOperacao = caminhoOperacao.length();
												// seta o caminho no mapeamento para ser chamado
												String caminhoRetorno = caminhoOperacao.substring(0, tamanhoOperacao - 3);
												httpServletRequest.setAttribute("veioEncerrarOS", encerrarOrdemServicoActionForm.getNumeroOS());
												httpServletRequest.setAttribute("dataEncerramento", encerrarOrdemServicoActionForm.getDataEncerramento());
												httpServletRequest.setAttribute("caminhoRetornoIntegracaoComercial", "exibirEncerrarOrdemServicoAction.do?retornoConsulta=1");
												retorno = actionMapping.findForward(caminhoRetorno);
												if (retorno == null) {
													throw new ActionServletException("atencao.caminho_url_indisponivel");
												} else {
													/*
													 * caso seja chamado a integração então seta a
													 * OS na sessão com outro nome e remove a OS da
													 * sessão visto que a integração usa o mesmo
													 * nome da OS que está na sessão
													 */
													sessao.setAttribute("osFiscalizacao", sessao.getAttribute("ordemServicoFiscalizacao"));
													sessao.removeAttribute("ordemServicoFiscalizacao");
												}
											}
										}
									}
								}
							}
						}
						if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

							OrdemServico osFiscalizacao = null;

							if (integracaoComercialHelper == null
									|| integracaoComercialHelper.equals("")
									&& sessao.getAttribute("ordemServicoFiscalizacao") != null) {
								osFiscalizacao = (OrdemServico) sessao.getAttribute("ordemServicoFiscalizacao");
							}
							 /*
							  * caso a os fiscalização não esteja na sessão
							  * então não foi para integração e então pode
							  * pegar o OS mesmo
							  */
							if (sessao.getAttribute("osFiscalizacao") != null
									&& !sessao.getAttribute("osFiscalizacao").equals("")) {
								osFiscalizacao = (OrdemServico) sessao.getAttribute("osFiscalizacao");
								
								sessao.removeAttribute("osFiscalizacao");
							}
							// se o serviço tipo referencia seja igual a nulo
							if (encerrarOrdemServicoActionForm.getTipoServicoReferenciaId() == null || encerrarOrdemServicoActionForm.getTipoServicoReferenciaId().equals("")) {
								
								OrdemServicoPavimento ordemServicoPavimento = new OrdemServicoPavimento();								
								//Verifica o id pavimento rua.
								if (!"".equals(encerrarOrdemServicoActionForm.getIdPavimentoRua())
										&& encerrarOrdemServicoActionForm.getIdPavimentoRua() != null) {
									
									PavimentoRua pavimentoRua = new PavimentoRua();
									pavimentoRua.setId(Util.converterStringParaInteger(encerrarOrdemServicoActionForm.getIdPavimentoRua()));
									
									ordemServicoPavimento.setPavimentoRua(pavimentoRua);
								}
								//Verifica a metragem do pavimento rua se é nulo e maior que zero
								if (!"".equals(encerrarOrdemServicoActionForm.getMetragemPavimentoRua())
										&& encerrarOrdemServicoActionForm.getMetragemPavimentoRua() != null) {
									
									BigDecimal metragem = null;
									Integer metragemPavimento = Util.converterStringParaInteger(encerrarOrdemServicoActionForm.getMetragemPavimentoRua().replace(",", ""));
									if (metragemPavimento <= new Integer(0).intValue()) {
										throw new ActionServletException("atencao.metragemSomentePositiva");
									}
									metragem = Util.formatarMoedaRealparaBigDecimal(encerrarOrdemServicoActionForm.getMetragemPavimentoRua());
									ordemServicoPavimento.setAreaPavimentoRua(metragem);
								}
								
								if (!"".equals(encerrarOrdemServicoActionForm.getIdPavimentoCalcada())
										&& encerrarOrdemServicoActionForm.getIdPavimentoCalcada() != null) {
									PavimentoCalcada pavimentoCalcada = new PavimentoCalcada();
									pavimentoCalcada.setId(Util.converterStringParaInteger(encerrarOrdemServicoActionForm.getIdPavimentoCalcada()));
									ordemServicoPavimento.setPavimentoCalcada(pavimentoCalcada);
								}
								//Verifica a metragem do pavimento calcada se é nulo e maior que zero
								if (!"".equals(encerrarOrdemServicoActionForm.getMetragemPavimentoCalcada())
										&& encerrarOrdemServicoActionForm.getMetragemPavimentoCalcada() != null) {
									BigDecimal metragem = null;
									Integer metragemcalcada = Util.converterStringParaInteger(encerrarOrdemServicoActionForm.getMetragemPavimentoCalcada().replace(",",""));
									if (metragemcalcada.intValue() <= new Integer(0).intValue()) {
										throw new ActionServletException("atencao.metragemSomentePositiva");
									}
									metragem = Util.formatarMoedaRealparaBigDecimal(encerrarOrdemServicoActionForm.getMetragemPavimentoCalcada());
									
									ordemServicoPavimento.setAreaPavimentoCalcada(metragem);		
								}
								//Unidade Repavimentadora
								if (!"-1".equals(encerrarOrdemServicoActionForm.getIdUnidadeRepavimentadora())
										&& encerrarOrdemServicoActionForm.getIdUnidadeRepavimentadora() != null) {
									
									//[FS0011] – Verificar existência da unidade repavimentadora
									fachada.verificaUnidadeTipoRepavimentadora(encerrarOrdemServicoActionForm.getIdUnidadeRepavimentadora());
									
									UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
									unidadeOrganizacional.setId(new Integer(encerrarOrdemServicoActionForm.getIdUnidadeRepavimentadora()));

									ordemServicoPavimento.setUnidadeRepavimentadora(unidadeOrganizacional);
								}
								
								// [SB0002] - Encerrar com execução e sem
								// referência
								fachada.encerrarOSComExecucaoSemReferencia(
												numeroOS,
												dataEncerramento,
												usuarioLogado,
												encerrarOrdemServicoActionForm.getIdMotivoEncerramento(),
												dataUltimaAlteracao,
												encerrarOrdemServicoActionForm.getObservacaoEncerramento(),
												encerrarOrdemServicoActionForm.getIndicadorPavimento(),
												encerrarOrdemServicoActionForm.getPavimento(),
												colecaoManterDadosAtividadesOrdemServicoHelper,
												integracaoComercialHelper,
												encerrarOrdemServicoActionForm.getTipoServicoOSId(),
												osFiscalizacao,
												encerrarOrdemServicoActionForm.getIndicadorVistoriaServicoTipo(),
												encerrarOrdemServicoActionForm.getCodigoRetornoVistoriaOs(),
												ordemServicoPavimento,
												ordemServicoBoletim,
												indicadorServicoAceito);

							} else {

								 /*
						 		  * [SB0003] - Encerrar com execução e com
								  * referência
								  */
								idOSDiagnostico = fachada
										.encerrarOSComExecucaoComReferencia(
												numeroOS,
												dataEncerramento,
												usuarioLogado,
												encerrarOrdemServicoActionForm.getIdMotivoEncerramento(),
												dataUltimaAlteracao,
												encerrarOrdemServicoActionForm.getObservacaoEncerramento(),
												encerrarOrdemServicoActionForm.getIndicadorPavimento(),
												encerrarOrdemServicoActionForm.getPavimento(),
												encerrarOrdemServicoActionForm.getIdTipoRetornoReferida(),
												encerrarOrdemServicoActionForm.getIndicadorDeferimento(),
												encerrarOrdemServicoActionForm.getIndicadorTrocaServico(),
												encerrarOrdemServicoActionForm.getIdServicoTipo(),
												encerrarOrdemServicoActionForm.getNumeroOSReferencia(),
												encerrarOrdemServicoActionForm.getServicoTipoReferenciaOS(),
												colecaoManterDadosAtividadesOrdemServicoHelper,
												integracaoComercialHelper,
												encerrarOrdemServicoActionForm.getTipoServicoOSId(),
												osFiscalizacao,
												encerrarOrdemServicoActionForm.getIndicadorVistoriaServicoTipo(),
												encerrarOrdemServicoActionForm.getCodigoRetornoVistoriaOs(),
												ordemServicoBoletim,
												indicadorServicoAceito);
							}
						}
					}
				}
			}

			/**
			 * [RM5185] 
			 * @author Magno Gouveia
			 * @since 08/08/2011
			 */
			FiltroTipoServico filtroTipoServico = new FiltroTipoServico();
			filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.ID, encerrarOrdemServicoActionForm.getTipoServicoOSId()));
			ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroTipoServico, ServicoTipo.class.getName()));
			
			if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
				
				if (servicoTipo.getIndicadorEncAutomaticoRAQndEncOS().compareTo(ConstantesSistema.NAO) == 0) {

					boolean telaConfirmacao = fachada.tramitarOuEncerrarRADaOSEncerrada(
														Util.converterStringParaInteger(encerrarOrdemServicoActionForm.getNumeroOS()),
														usuarioLogado,
														encerrarOrdemServicoActionForm.getIdMotivoEncerramento(),
														encerrarOrdemServicoActionForm.getNumeroRA(),
														encerrarOrdemServicoActionForm.getDataRoteiro());
					// se for para ir para a tela de confirmação			
					if(telaConfirmacao){
						httpServletRequest.setAttribute("caminhoActionConclusao", "/gsan/encerrarOrdemServicoAction.do");
						httpServletRequest.setAttribute("cancelamento", "TRUE");
						httpServletRequest.setAttribute("nomeBotao1", "Sim");
						httpServletRequest.setAttribute("nomeBotao2", "Não");
		                httpServletRequest.setAttribute( "tipoRelatorio", "confirmaEncerramentoRA" );
		
						return montarPaginaConfirmacao("atencao.encerrar_RA_da_OS", httpServletRequest, actionMapping);
					} else {
						
					}
				} else {
					/**
					  * [UC0457] Encerrar Ordem de Serviço 5.4 do Fluxo
					  * Principal
					  * 
					  * @author Hugo Leonardo
					  * @created 17/02/2011
					  */
					if (encerrarOrdemServicoActionForm.getNumeroRA() != null && !encerrarOrdemServicoActionForm.getNumeroRA().equals("")) {
						
						FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
						filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, encerrarOrdemServicoActionForm.getNumeroRA()));
					
						Collection colecaoRegistroAtendimento = Fachada.getInstancia().pesquisar(filtroRegistroAtendimento, RegistroAtendimento.class.getName() );
						
						RegistroAtendimento registroAtendimento = (RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);
						
						AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
						atendimentoMotivoEncerramento.setId(Util.converterStringParaInteger(encerrarOrdemServicoActionForm.getIdMotivoEncerramento()));
						atendimentoMotivoEncerramento.setIndicadorExecucao(AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM);
						registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
						registroAtendimento.setCodigoSituacao(RegistroAtendimento.SITUACAO_ENCERRADO);
						
						this.setDataEncerramentoRA(encerrarOrdemServicoActionForm, registroAtendimento);
							
						registroAtendimento.setUltimaAlteracao(new Date());
	
						/*
						 * cria o objeto para a inserção do registro de atendimento unidade
						 */
						RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
						registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);
	
						if (usuarioLogado.getUnidadeOrganizacional() != null
								&& !usuarioLogado.getUnidadeOrganizacional().equals("")) {

							registroAtendimentoUnidade.setUnidadeOrganizacional(usuarioLogado.getUnidadeOrganizacional());
						}
						registroAtendimentoUnidade.setUsuario(usuarioLogado);
	
						// atendimento relação tipo
						AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
						atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
						registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
						registroAtendimentoUnidade.setUltimaAlteracao(new Date());
						
						// Colocado por Raphael Rossiter em 10/03/2008
						ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = Fachada.getInstancia().obterDadosRegistroAtendimento(registroAtendimento.getId());
						
						SolicitacaoTipoEspecificacao especificacao = registroAtendimentoHelper.getRegistroAtendimento().getSolicitacaoTipoEspecificacao();
				        
				        // Validamos se possivel criar um novo RA
				        Object[] arrayValidaGeracaoNovoRA = validarGeracaoNovoRA(registroAtendimentoHelper.getRegistroAtendimento(), httpServletRequest, actionMapping);
				        
				        if ((Boolean) arrayValidaGeracaoNovoRA[2]) {
							return (ActionForward) arrayValidaGeracaoNovoRA[0];
						}
				            
				        Boolean confirmadoGeracaoNovoRA = (Boolean) arrayValidaGeracaoNovoRA[1];                
				    	
				        if (especificacao.getDebitoTipo() != null
								&& servicoNaoCobrancaMotivo == null) {
							
				        	if (valorDebito == null) {
								valorDebito = especificacao.getValorDebito();
							}
				        	
							fachada.encerrarRegistroAtendimento(
									registroAtendimento,
									registroAtendimentoUnidade, 
									usuarioLogado,
									especificacao.getDebitoTipo().getId(),
									valorDebito, 
									1, 
									"100",
									confirmadoGeracaoNovoRA, 
									null, 
									false);
						} else {
							fachada.encerrarRegistroAtendimento(
								registroAtendimento,
								registroAtendimentoUnidade, 
								usuarioLogado, 
								null, 
								null, 
								null, 
								null, 
								confirmadoGeracaoNovoRA,
								null,
								false);
						}	
					}	
				}
			}
		} else {
			/*
			 * se o usuário confirmar o encerramento da RA da OS que está sendo
			 * encerrada
			 */
			if (valorConfirmacaoEncerramentoRA.equals("ok")) {
				FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
				filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, encerrarOrdemServicoActionForm.getNumeroRA()));
			
				Collection colecaoRegistroAtendimento = fachada.pesquisar(filtroRegistroAtendimento, RegistroAtendimento.class.getName());
				RegistroAtendimento registroAtendimento = (RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);
				
				
				AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
				atendimentoMotivoEncerramento.setId(Util.converterStringParaInteger(encerrarOrdemServicoActionForm.getIdMotivoEncerramento()));
				atendimentoMotivoEncerramento.setIndicadorExecucao(AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM);
				registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
				registroAtendimento.setCodigoSituacao(RegistroAtendimento.SITUACAO_ENCERRADO);
				
				setDataEncerramentoRA(encerrarOrdemServicoActionForm, registroAtendimento);
				
				if (encerrarOrdemServicoActionForm.getObservacaoEncerramento() != null && 
					!encerrarOrdemServicoActionForm.getObservacaoEncerramento().equals("")) {
					
					registroAtendimento.setObservacao(encerrarOrdemServicoActionForm.getObservacaoEncerramento());
				} else {
					registroAtendimento.setObservacao("ENCERRADO ATRAVÉS DA FUNCIONALIDADE ENCERRAMENTO DA ORDEM DE SERVIÇO");
				}
				
				registroAtendimento.setUltimaAlteracao(new Date());

				/*
				 * cria o objeto para a inserção do registro de atendimento
				 * unidade
				 */
				RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
				registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);

				if (usuarioLogado.getUnidadeOrganizacional() != null && 
					!usuarioLogado.getUnidadeOrganizacional().equals("")) {
					
					registroAtendimentoUnidade.setUnidadeOrganizacional(usuarioLogado.getUnidadeOrganizacional());
				}
				registroAtendimentoUnidade.setUsuario(usuarioLogado);

				// atendimento relação tipo
				AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
				atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
				registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
				registroAtendimentoUnidade.setUltimaAlteracao(new Date());
				
				//Colocado por Raphael Rossiter em 10/03/2008
				ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = fachada.obterDadosRegistroAtendimento(registroAtendimento.getId());
				
				SolicitacaoTipoEspecificacao especificacao = registroAtendimentoHelper.getRegistroAtendimento().getSolicitacaoTipoEspecificacao();
                
                // Validamos se possivel criar um novo RA
                Object[] arrayValidaGeracaoNovoRA = validarGeracaoNovoRA(
						registroAtendimentoHelper.getRegistroAtendimento(),
						httpServletRequest, actionMapping);
                
                if ((Boolean) arrayValidaGeracaoNovoRA[2]) {
					return (ActionForward) arrayValidaGeracaoNovoRA[0];
				}
                
                Boolean confirmadoGeracaoNovoRA = (Boolean) arrayValidaGeracaoNovoRA[1];                
            	
                if (especificacao.getDebitoTipo() != null
						&& servicoNaoCobrancaMotivo == null) {
					
                	if (valorDebito == null) {
						valorDebito = especificacao.getValorDebito();
					}
                	
					fachada.encerrarRegistroAtendimento(registroAtendimento,
							registroAtendimentoUnidade, 
							usuarioLogado, 
							especificacao.getDebitoTipo().getId(), 
							valorDebito, 
							1, 
							"100", 
							confirmadoGeracaoNovoRA,null,false );
				} else {
					idOS = fachada.encerrarRegistroAtendimento(
						registroAtendimento,
						registroAtendimentoUnidade, 
						usuarioLogado, 
						null, 
						null, 
						null, 
						null, 
						confirmadoGeracaoNovoRA,null,false );
				}
			}
		}

		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			// montando página de sucesso
			if (idOSDiagnostico != null ) {
				String msg = 
					"Ordem de Serviço de código " + encerrarOrdemServicoActionForm.getNumeroOS() 
					+ " encerrada com sucesso. Ordem de Serviço de código " 
					+ idOSDiagnostico 
					+ " gerada com sucesso";
				
				montarPaginaSucesso(
					httpServletRequest,
					msg,
					"Voltar",
					"exibirConsultarDadosOrdemServicoAction.do?menu=sim&numeroOS=" + encerrarOrdemServicoActionForm.getNumeroOS(),
					"gerarRelatorioOrdemServicoAction.do?menu=sim&idsOS=" + encerrarOrdemServicoActionForm.getNumeroOS(),
					"Imprimir Ordem de Serviço Encerrada",
					"Imprimir Ordem de Serviço Gerada",
					"gerarRelatorioOrdemServicoAction.do?idsOS=" + idOSDiagnostico);
				
			} else if(idOS != null ) {
				
				String msg = 
						"Ordem de Serviço de código " + encerrarOrdemServicoActionForm.getNumeroOS() 
						+ " encerrada com sucesso. Ordem de Serviço de código " 
						+ idOS 
						+ " gerada com sucesso";
					
					montarPaginaSucesso(
						httpServletRequest,
						msg,
						"Voltar",
						"exibirConsultarDadosOrdemServicoAction.do?menu=sim&numeroOS=" + encerrarOrdemServicoActionForm.getNumeroOS());
			} else {
				
				montarPaginaSucesso(httpServletRequest,
						"Ordem de Serviço de código " + encerrarOrdemServicoActionForm.getNumeroOS() + " encerrada com sucesso.", 
						"Voltar",
						"exibirConsultarDadosOrdemServicoAction.do?menu=sim&numeroOS=" + encerrarOrdemServicoActionForm.getNumeroOS());
				
			}
			sessao.removeAttribute("canceladoOSFiscalizacao");
			sessao.removeAttribute("integracaoComercialHelper");
			sessao.removeAttribute("ordemServico");
			sessao.removeAttribute("ordemServicoFiscalizacao");
			sessao.removeAttribute("confirmaEncerramentoRA");
		}
		
		return retorno;
	}

	private void setDataEncerramentoRA(EncerrarOrdemServicoActionForm encerrarOrdemServicoActionForm, RegistroAtendimento registroAtendimento) {
		if (encerrarOrdemServicoActionForm.getHoraEncerramento() != null && 
				!encerrarOrdemServicoActionForm.getHoraEncerramento().equals("")) {
				
			/*
			 * Verifica se a data de encerramento é menor do que
			 * a data de atendimento do RA, Caso seja encerra a
			 * RA com a data atual.
			 */ 
			if (Util.compararDataTime(registroAtendimento.getRegistroAtendimento(),
					Util.converteStringParaDateHora(encerrarOrdemServicoActionForm
						.getDataEncerramento() + " " + encerrarOrdemServicoActionForm.getHoraEncerramento() + ":00")) > 0) {
				
				registroAtendimento.setDataEncerramento(new Date());
			} else {
				registroAtendimento.setDataEncerramento(
					Util.converteStringParaDateHora(encerrarOrdemServicoActionForm
							.getDataEncerramento() + " " + encerrarOrdemServicoActionForm.getHoraEncerramento() + ":00"));
			}	
				
		} else {
			/*
			 * Verifica se a data de encerramento é menor do que
			 * a data de atendimento do RA, Caso seja encerra a
			 * RA com a data atual.
			 */
			if (Util.compararDataTime(registroAtendimento.getRegistroAtendimento(),
					Util.converteStringParaDateHora(encerrarOrdemServicoActionForm.getDataEncerramento()+ " " +
							Util.formatarHoraSemSegundos(new Date()) + ":00")) > 0 ) {
				
				registroAtendimento.setDataEncerramento(new Date());
			} else {
				registroAtendimento
						.setDataEncerramento(Util.converteStringParaDateHora(encerrarOrdemServicoActionForm.getDataEncerramento()
										+ " " + Util.formatarHoraSemSegundos(new Date()) + ":00"));
			}	
			
		}
	}
    
    /**
     * 
     * [UC0435] - Encerrar Registro de Atendimento
     * 
     * Método verifica se existe a possibilidade de ser gerada um novo RA.
     * Caso positivo, retorna uma tela de pergunta ao usuário para verificar
     * se ele deseja gerar esse novo RA ou não. Caso não seja necessária a pegun-
     * ta, retorna com a tela de sucesso normal ao fluxo.
     *
     * @author bruno
     * @date 15/04/2009
     *
     * @param registroAtendimento: Registro do atendimento.
     * @param request: Onde se serão informados os parametros para geração da página
     * @param actionMapping: Necessário para geração da página
     * 
     * @return Object[3]
     * 
     *      Object[0]: ActionFoward com a tela a ser mostrada
     *      Object[1]: Se o usuário confirmou ou não a inserção do novo ra
     *      Object[2]: Se será redirecionado ao usuário perguntando se será
     *                 inserido ou não o novo RA.
     */
    private Object[] validarGeracaoNovoRA(
			RegistroAtendimento registroAtendimento,
			HttpServletRequest request, ActionMapping actionMapping){
        
        Object[] retorno = new Object[3];
        
        // Verificamos se ja foi confimado...
        retorno[1] = ("confirmaInclusaoNovoRA".equals(request
				.getParameter("tipoRelatorio")) ? request.getParameter(
				"confirmado").equals("ok") : null);
        
        retorno[2] = new Boolean(Boolean.FALSE);
        
        if (retorno[1] == null) {
            Fachada fachada = Fachada.getInstancia();
            
            FiltroSolicitacaoTipoEspecificacao filtro = new FiltroSolicitacaoTipoEspecificacao();
            
            filtro.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipoEspecificacao.ID, registroAtendimento
							.getSolicitacaoTipoEspecificacao().getId()));
            
            filtro.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacaoNovoRA");
            Collection<SolicitacaoTipoEspecificacao> colSolicitacaoTipoEspecificacao = fachada.pesquisar(filtro, 
            																				SolicitacaoTipoEspecificacao.class.getName());
            
            SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util
					.retonarObjetoDeColecao(colSolicitacaoTipoEspecificacao);
            
            if ( solicitacaoTipoEspecificacao.
                    getSolicitacaoTipoEspecificacaoNovoRA()!= null ){
                retorno[2] = new Boolean( Boolean.TRUE );
                
                registroAtendimento.setSolicitacaoTipoEspecificacao( 
                        solicitacaoTipoEspecificacao );
                
                request.setAttribute("caminhoActionConclusao", "/gsan/encerrarOrdemServicoAction.do");
                request.setAttribute("cancelamento", "TRUE");
                request.setAttribute("nomeBotao1", "Sim");
                request.setAttribute("nomeBotao2", "Não");
                request.setAttribute( "tipoRelatorio", "confirmaInclusaoNovoRA" );
    
                retorno[0] = montarPaginaConfirmacao(
		                        "atencao.encerrar_ra_confirma_geracao_novo_ra",
		                        request, 
		                        actionMapping, 
		                        solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA().getDescricao());
                return retorno;
            }
        }       
        
        retorno[0] = actionMapping.findForward("telaSucesso");
        return retorno;
    }
    
    /**
     * [UC0457] Encerrar Ordem de Serviço
     * [SB0007]- Gerar Informações para Boletim de Medição.
     * 
     * @author Vivianne Sousa
     * @created 28/01/2011
     */
    private Map validarInformacoesBoletimMedicao(Integer idOrdemServico, EncerrarOrdemServicoActionForm form) {
    		
    	OrdemServicoBoletim ordemServicoBoletim = null;
		Boolean exibirMsgConfirmacao = false;
    	
    	if (form.getExibeIndicadorExistePavimento().equals("1")
				|| form.getExibeQtdeReposicaoAsfalto().equals("1")
				|| form.getExibeQtdeReposicaoCalcada().equals("1")
				|| form.getExibeQtdeReposicaoParalelo().equals("1")) {
    		
    		ordemServicoBoletim = new OrdemServicoBoletim();
    		ordemServicoBoletim.setId(idOrdemServico);
    		OrdemServico os = new OrdemServico();
    		os.setId(idOrdemServico);
    		ordemServicoBoletim.setOrdemServico(os);
    		
    		if (form.getExibeIndicadorExistePavimento().equals("1")) {
    			if (form.getIndicadorExistePavimento() == null) {
    				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Existe Pavimento");
    			} else {
    				ordemServicoBoletim.setIndicadorPavimento(new Short(form.getIndicadorExistePavimento()));
    			}
    		} else {
    			ordemServicoBoletim.setIndicadorPavimento(new Short(ConstantesSistema.NAO));
    		}
    		
    		if (form.getExibeQtdeReposicaoAsfalto().equals("1")) {
    			
    			if (form.getQtdeReposicaoAsfalto() == null
						|| form.getQtdeReposicaoAsfalto().equals("")) {

					form.setQtdeReposicaoAsfalto("0");
					ordemServicoBoletim.setNumeroReposicaoAsfalto(new BigDecimal(0));
					exibirMsgConfirmacao = true;
    				
    			} else if (form.getQtdeReposicaoAsfalto().equals("0")
						|| form.getQtdeReposicaoAsfalto().equals("00")
						|| form.getQtdeReposicaoAsfalto().equals("0,00")) {
					ordemServicoBoletim.setNumeroReposicaoAsfalto(new BigDecimal(0));
					exibirMsgConfirmacao = true;
    				
    			}else{
    				
    				ordemServicoBoletim.setNumeroReposicaoAsfalto(Util.formatarMoedaRealparaBigDecimal(form.getQtdeReposicaoAsfalto()));
    			}
    		}
    		
    		if (form.getExibeQtdeReposicaoParalelo().equals("1")) {
    			
    			if (form.getQtdeReposicaoParalelo() == null
						|| form.getQtdeReposicaoParalelo().equals("")) {
    				
    				form.setQtdeReposicaoParalelo("0");
    				ordemServicoBoletim.setNumeroReposicaoParalelo(new BigDecimal(0));
    				exibirMsgConfirmacao = true;
    				
    			} else if (form.getQtdeReposicaoParalelo().equals("0")
						|| form.getQtdeReposicaoParalelo().equals("00")
						|| form.getQtdeReposicaoParalelo().equals("0,00")) {
					ordemServicoBoletim
							.setNumeroReposicaoParalelo(new BigDecimal(0));
					exibirMsgConfirmacao = true;

				} else {
    				
    				ordemServicoBoletim.setNumeroReposicaoParalelo(Util.formatarMoedaRealparaBigDecimal(form.getQtdeReposicaoParalelo()));
    			}
    			
    			
    		}
    		
    		if (form.getExibeQtdeReposicaoCalcada().equals("1")) {
    			
    			if (form.getQtdeReposicaoCalcada() == null
						|| form.getQtdeReposicaoCalcada().equals("")) {
    				
    				form.setQtdeReposicaoCalcada("0");
    				ordemServicoBoletim.setNumeroReposicaoCalcada(new BigDecimal(0));
    				exibirMsgConfirmacao = true;
    				
    			} else if (form.getQtdeReposicaoCalcada().equals("0")
						|| form.getQtdeReposicaoCalcada().equals("00")
						|| form.getQtdeReposicaoCalcada().equals("0,00")) {
					ordemServicoBoletim.setNumeroReposicaoCalcada(new BigDecimal(0));
					exibirMsgConfirmacao = true;
    				
    			} else {
    				
    				ordemServicoBoletim.setNumeroReposicaoCalcada(Util.formatarMoedaRealparaBigDecimal(form.getQtdeReposicaoCalcada()));
    			}
    		}
    	
    		ordemServicoBoletim.setUltimaAlteracao(new Date());
    	}
    	
    	Map retorno = new HashMap();
    	retorno.put("ordemServicoBoletim",ordemServicoBoletim);
    	retorno.put("exibirMsgConfirmacao",exibirMsgConfirmacao);
    	
		return retorno;
	}
}
