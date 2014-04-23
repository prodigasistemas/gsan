package gcom.gui.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de efetuar corte de ligação de
 * água
 * 
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 */
public class ExibirEfetuarMudancaSituacaoFaturamentoLigacaoEsgotoAction extends
		GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);
		ActionForward retorno = actionMapping
				.findForward("efetuarMudancaSituacaoFaturamentoLigacaoEsgoto");
		EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoActionForm mudancaFaturamentoLigacaoAguaActionForm = (EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		mudancaFaturamentoLigacaoAguaActionForm.setMostrarVolume(true);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		String idOrdemServico = null;
		if(mudancaFaturamentoLigacaoAguaActionForm.getIdOrdemServico() != null){
			idOrdemServico = mudancaFaturamentoLigacaoAguaActionForm.getIdOrdemServico();
		}else{
			idOrdemServico = (String)httpServletRequest.getAttribute("veioEncerrarOS");
			mudancaFaturamentoLigacaoAguaActionForm
					.setDataMudanca(
					(String) httpServletRequest.getAttribute("dataEncerramento"));
				
				sessao.setAttribute("caminhoRetornoIntegracaoComercial",
					httpServletRequest.getAttribute("caminhoRetornoIntegracaoComercial"));
		}
		
		OrdemServico ordemServico = null;
		
		if(httpServletRequest.getAttribute("semMenu") != null){
			sessao.setAttribute("semMenu", "SIM");
		}else{
			sessao.removeAttribute("semMenu");
		}
		
		mudancaFaturamentoLigacaoAguaActionForm.setVolumeMinimoFixado(null);
		
		Boolean veioEncerrarOS = null;
		if(httpServletRequest.getAttribute("veioEncerrarOS") != null){
			veioEncerrarOS = Boolean.TRUE;
		}else{
			if (mudancaFaturamentoLigacaoAguaActionForm.getVeioEncerrarOS() != null
					&& !mudancaFaturamentoLigacaoAguaActionForm
							.getVeioEncerrarOS().equals("")) {
				if (mudancaFaturamentoLigacaoAguaActionForm.getVeioEncerrarOS()
						.toLowerCase().equals("true")) {
					veioEncerrarOS = veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = veioEncerrarOS = Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}
		
		mudancaFaturamentoLigacaoAguaActionForm.setVeioEncerrarOS(""+veioEncerrarOS);
		
		//Filtro para o campo Tpo Debito
		Collection colecaoNaoCobranca = (Collection) 
			sessao.getAttribute("colecaoNaoCobranca");
		if(colecaoNaoCobranca == null){
			FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();
			
			filtroServicoNaoCobrancaMotivo.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);
			
			colecaoNaoCobranca = fachada.pesquisar(filtroServicoNaoCobrancaMotivo, ServicoNaoCobrancaMotivo.class.getName());
			
			if (colecaoNaoCobranca != null && !colecaoNaoCobranca.isEmpty()) {
				sessao.setAttribute("colecaoNaoCobranca",colecaoNaoCobranca);
			} else {
				throw new ActionServletException("atencao.naocadastrado",null, "Motivo da Não Cobrança");
			}
		}

		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

//			FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
//			filtroOrdemServico.adicionarParametro(new ParametroSimples(
//					FiltroOrdemServico.ID, idOrdemServico));
//			filtroOrdemServico
//					.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
//			filtroOrdemServico
//					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel");
//			filtroOrdemServico
//					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.localidade");
//			filtroOrdemServico
//					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.setorComercial");
//			filtroOrdemServico
//					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.quadra");
//			filtroOrdemServico
//					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.ligacaoAgua");
//			filtroOrdemServico
//					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.ligacaoAguaSituacao");
//			filtroOrdemServico
//					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.ligacaoEsgoto");
//			filtroOrdemServico
//					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.ligacaoEsgotoSituacao");
//			filtroOrdemServico
//					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.ligacaoAgua.hidrometroInstalacaoHistorico");
//
//			Collection colecaoOrdemServico = fachada.pesquisar(
//					filtroOrdemServico, OrdemServico.class.getName());
			
			ordemServico = fachada.recuperaOSPorId(
					new Integer(idOrdemServico));

			if (ordemServico != null) {
//				ordemServico = (OrdemServico) colecaoOrdemServico
//						.iterator().next();
//				sessao.setAttribute("colecaoOrdemServico", colecaoOrdemServico);
				
				String tipoResultado  = fachada.validarMudancaSituacaoFaturamentoLigacaoesgotoExibir(ordemServico,veioEncerrarOS);
				if (tipoResultado!= null){
					if (tipoResultado.trim().equalsIgnoreCase("TAMPONADO")){
							mudancaFaturamentoLigacaoAguaActionForm.setNovaSitLigacaoEsgoto("TAMPONADO");
							mudancaFaturamentoLigacaoAguaActionForm.setMostrarVolume(false);
						}else if (tipoResultado.trim().equalsIgnoreCase("LIGADO FORA DE USO")){
							mudancaFaturamentoLigacaoAguaActionForm.setMostrarVolume(false);
							mudancaFaturamentoLigacaoAguaActionForm.setNovaSitLigacaoEsgoto("LIGADO FORA DE USO");
						}else{
							mudancaFaturamentoLigacaoAguaActionForm.setMostrarVolume(true);
							mudancaFaturamentoLigacaoAguaActionForm.setNovaSitLigacaoEsgoto("LIGADO");
						}
				}
							mudancaFaturamentoLigacaoAguaActionForm
									.setIdOrdemServico(idOrdemServico);
							mudancaFaturamentoLigacaoAguaActionForm
									.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
		
				/*-------------- Início dados do Imóvel---------------*/
					Imovel imovel = ordemServico.getRegistroAtendimento()
					.getImovel();
					sessao.setAttribute("imovel", ordemServico
							.getRegistroAtendimento().getImovel());
					
					FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();
					filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgoto.ID, imovel.getId()));
					
					Collection colecaoLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgoto, LigacaoEsgoto.class.getName());
					if(!colecaoLigacaoEsgoto.isEmpty()){
						LigacaoEsgoto ligacaoEsgoto = (LigacaoEsgoto)Util.retonarObjetoDeColecao(colecaoLigacaoEsgoto);
						
						sessao.setAttribute("ligacaoEsgoto", ligacaoEsgoto);
					}
					
					sessao.setAttribute("ordemServico", ordemServico);
					
					BigDecimal valorDebito = new BigDecimal(0.00);
					if(ordemServico.getServicoTipo() != null && ordemServico.getServicoTipo().getDebitoTipo() != null){
						mudancaFaturamentoLigacaoAguaActionForm.setIdTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getId().toString());
						mudancaFaturamentoLigacaoAguaActionForm.setDescricaoTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getDescricao());
						
						 
						//[FS0013] - Alteração de Valor
						this.permitirAlteracaoValor(ordemServico.getServicoTipo(), mudancaFaturamentoLigacaoAguaActionForm);
						
						//Colocado por Raphael Rossiter em 04/05/2007 (Analista: Rosana Carvalho)
						valorDebito = this.calcularValores(httpServletRequest, ordemServico, 
						mudancaFaturamentoLigacaoAguaActionForm);
						
					
						if(ordemServico.getServicoNaoCobrancaMotivo() != null){
							mudancaFaturamentoLigacaoAguaActionForm.setMotivoNaoCobranca(ordemServico.getServicoNaoCobrancaMotivo().getId().toString());
						}
						
						if(ordemServico.getPercentualCobranca() != null){
							mudancaFaturamentoLigacaoAguaActionForm.setPercentualCobranca(ordemServico.getPercentualCobranca().toString());
						}
					}
					
					if (imovel != null) {
						
						//Matricula Imóvel
						String matriculaImovel = ordemServico
								.getRegistroAtendimento().getImovel().getId()
								.toString();
						mudancaFaturamentoLigacaoAguaActionForm
								.setMatriculaImovel(matriculaImovel);
	
						//Inscrição Imóvel
						String inscricaoImovel = fachada.pesquisarInscricaoImovel(ordemServico
								.getRegistroAtendimento().getImovel()
								.getId());
						mudancaFaturamentoLigacaoAguaActionForm
								.setInscricaoImovel(inscricaoImovel);
	
						// Situação da Ligação de Agua
						String situacaoLigacaoAgua = imovel
								.getLigacaoAguaSituacao().getDescricao();
						mudancaFaturamentoLigacaoAguaActionForm
								.setSituacaoLigacaoAgua(situacaoLigacaoAgua);
	
						// Situação da Ligação de Esgoto
						String situacaoLigacaoEsgoto = imovel
								.getLigacaoEsgotoSituacao().getDescricao();
						mudancaFaturamentoLigacaoAguaActionForm
								.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);
	
						//Filtro para carregaar o Cliente	
						FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
	
						filtroClienteImovel
								.adicionarParametro(new ParametroSimples(
										FiltroClienteImovel.IMOVEL_ID,
										matriculaImovel));
	
						filtroClienteImovel
								.adicionarParametro(new ParametroSimples(
										FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
										ClienteRelacaoTipo.USUARIO));
	
						filtroClienteImovel.adicionarParametro(new ParametroNulo(
								FiltroClienteImovel.DATA_FIM_RELACAO));
	
						filtroClienteImovel
								.adicionarCaminhoParaCarregamentoEntidade("cliente");
						filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
	
						Collection colecaoClienteImovel = fachada.pesquisar(
								filtroClienteImovel, ClienteImovel.class.getName());
	
						if (colecaoClienteImovel != null
								&& !colecaoClienteImovel.isEmpty()) {
	
							ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel
									.iterator().next();
							Cliente cliente = clienteImovel.getCliente();
	
							String documento = "";
	
							if (cliente.getCpf() != null
									&& !cliente.getCpf().equals("")) {
								documento = cliente.getCpfFormatado();
							} else {
								documento = cliente.getCnpjFormatado();
							}
							//Cliente Nome/CPF-CNPJ
							mudancaFaturamentoLigacaoAguaActionForm
									.setClienteUsuario(cliente.getNome());
							mudancaFaturamentoLigacaoAguaActionForm
									.setCpfCnpjCliente(documento);
	
						} else {
							throw new ActionServletException(
									"atencao.naocadastrado", null, "Cliente");
						}
					}
					/*-------------- Fim dados do Imóvel---------------*/
	
					/*-------------- Dados da Ligação ----------------------------*/
					
					// Carregando Data do Corte: Data recebida da execução da
							
					String  dataEncerramentoOdServico= Util.formatarData(ordemServico.getDataEncerramento());
					if(dataEncerramentoOdServico != null && !dataEncerramentoOdServico.equals("")){
					  mudancaFaturamentoLigacaoAguaActionForm
							.setDataMudanca(dataEncerramentoOdServico);
					}
	
					// Carregando campo Volume Mínimo Fixado
	
					if (imovel.getQuantidadeEconomias() != null) {
						mudancaFaturamentoLigacaoAguaActionForm
								.setQtdeEconomia(imovel.getQuantidadeEconomias()
										.toString());
					} else {
						// Se entrar aqui é porque a Base está inconsistente.
						mudancaFaturamentoLigacaoAguaActionForm
								.setQtdeEconomia("1");
					}
	
					// -----------------------------------------------------------
					// Verificar permissão especial
					boolean temPermissaoMotivoNaoCobranca = fachada.verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
					// -----------------------------------------------------------
					
					if (temPermissaoMotivoNaoCobranca) {
						httpServletRequest.setAttribute("permissaoMotivoNaoCobranca", temPermissaoMotivoNaoCobranca);
					}else{
						mudancaFaturamentoLigacaoAguaActionForm.setPercentualCobranca("100");
						mudancaFaturamentoLigacaoAguaActionForm.setQuantidadeParcelas("1");
						mudancaFaturamentoLigacaoAguaActionForm.setValorParcelas(Util.formataBigDecimal(valorDebito,2,true));
					}

				} else {
					mudancaFaturamentoLigacaoAguaActionForm.setNomeOrdemServico("Ordem de Serviço inexistente");
					mudancaFaturamentoLigacaoAguaActionForm.setIdOrdemServico("");
					httpServletRequest.setAttribute("OrdemServioInexistente", true);
				}
				/*-------------------- Fim Dados da Ligação ----------------------------*/
			} else {
				httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
				mudancaFaturamentoLigacaoAguaActionForm.setIdOrdemServico("");
				mudancaFaturamentoLigacaoAguaActionForm.setMatriculaImovel("");
				mudancaFaturamentoLigacaoAguaActionForm.setInscricaoImovel("");
				mudancaFaturamentoLigacaoAguaActionForm.setClienteUsuario("");
				mudancaFaturamentoLigacaoAguaActionForm.setCpfCnpjCliente("");
				mudancaFaturamentoLigacaoAguaActionForm.setSituacaoLigacaoAgua("");
				mudancaFaturamentoLigacaoAguaActionForm.setSituacaoLigacaoEsgoto("");
				mudancaFaturamentoLigacaoAguaActionForm.setNomeOrdemServico("");
				mudancaFaturamentoLigacaoAguaActionForm.setIdTipoDebito("");
				mudancaFaturamentoLigacaoAguaActionForm.setDescricaoTipoDebito("");
				mudancaFaturamentoLigacaoAguaActionForm.setQuantidadeParcelas("");
				mudancaFaturamentoLigacaoAguaActionForm.setValorParcelas("");
				mudancaFaturamentoLigacaoAguaActionForm.setPercentualCobranca("-1");
				mudancaFaturamentoLigacaoAguaActionForm.setMotivoNaoCobranca("-1");
			}
			return retorno;
		}
	
	
	/*
	 * [FS0013 - Alteração de Valor]
	 * 
	 * autor: Raphael Rossiter
	 * data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo, EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoActionForm form){
		
		if (servicoTipo.getIndicadorPermiteAlterarValor() == 
			ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()){
			
			form.setAlteracaoValor("OK");
		}
		else{
			form.setAlteracaoValor("");
		}
		
	}
	
	
	/*
	 * Calcular valor da prestação com juros
	 * 
	 * return: Retorna o valor total do débito
	 * 
	 * autor: Raphael Rossiter
	 * data: 04/05/2007
	 */
	private BigDecimal calcularValores(HttpServletRequest httpServletRequest, OrdemServico ordemServico,
			EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoActionForm form){
		
		String calculaValores = httpServletRequest.getParameter("calculaValores");
		
		BigDecimal valorDebito = new BigDecimal(0);
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		Integer qtdeParcelas = null;
		
		if(calculaValores != null && calculaValores.equals("S")){
			
			//[UC0186] - Calcular Prestação
			BigDecimal  taxaJurosFinanciamento = null; 
			qtdeParcelas = new Integer(form.getQuantidadeParcelas());
			
			if(ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM.shortValue() && 
					qtdeParcelas.intValue() > 1){
				
				taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
			}else{
				taxaJurosFinanciamento = new BigDecimal(0);
				qtdeParcelas = 1;
			}
			
			BigDecimal valorPrestacao = null;
			if(taxaJurosFinanciamento != null){
				
				valorDebito = new BigDecimal(form.getValorDebito().replace(",","."));
				
				String percentualCobranca = form.getPercentualCobranca();
				
				if(percentualCobranca.equals("70")){
					valorDebito = valorDebito.multiply(new BigDecimal(0.7));
				}else if (percentualCobranca.equals("50")){
					valorDebito = valorDebito.multiply(new BigDecimal(0.5));
				}
				
				valorPrestacao =
					this.getFachada().calcularPrestacao(
						taxaJurosFinanciamento,
						qtdeParcelas, 
						valorDebito, 
						new BigDecimal("0.00"));
				
				valorPrestacao.setScale(2,BigDecimal.ROUND_HALF_UP);
			}
			
			if (valorPrestacao != null) {
				String valorPrestacaoComVirgula = Util.formataBigDecimal(valorPrestacao,2,true);
				form.setValorParcelas(valorPrestacaoComVirgula);
			} else {
				form.setValorParcelas("0,00");
			}						
			
		}else{
			
			valorDebito = Fachada.getInstancia().obterValorDebito(ordemServico.getServicoTipo().getId(),
			ordemServico.getRegistroAtendimento().getImovel().getId(), new Short(LigacaoTipo.LIGACAO_AGUA+""));
			
			form.setValorDebito(Util.formataBigDecimal(valorDebito,2,true));
		}
		
		
		return valorDebito;
	}
}
