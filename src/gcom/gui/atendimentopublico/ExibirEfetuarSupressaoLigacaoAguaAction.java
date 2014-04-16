package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.FiltroSupressaoTipo;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.SupressaoTipo;
import gcom.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.FiltroSupressaoMotivo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.SupressaoMotivo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
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
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 14/07/2006
 */
public class ExibirEfetuarSupressaoLigacaoAguaAction extends GcomAction {

	/**
	 * [UC0360] Efetuar Supressao de Água
	 * 
	 * Este caso de uso permite efetuar supressão da ligação de água, sendo
	 * chamada pela funcionalidade que encerra a execução da ordem de serviço ou
	 * chamada diretamente do menu.
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

		ActionForward retorno = actionMapping.findForward("efetuarSupressaoLigacaoAgua");

		EfetuarSupressaoLigacaoAguaActionForm efetuarSupressaoLigacaoAguaActionForm = (EfetuarSupressaoLigacaoAguaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		Boolean veioEncerrarOS = null;
		if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			if (efetuarSupressaoLigacaoAguaActionForm.getVeioEncerrarOS() != null
					&& !efetuarSupressaoLigacaoAguaActionForm
							.getVeioEncerrarOS().equals("")) {
				if (efetuarSupressaoLigacaoAguaActionForm.getVeioEncerrarOS()
						.toLowerCase().equals("true")) {
					veioEncerrarOS = veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = veioEncerrarOS = Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		String idOrdemServico = null;
		if (efetuarSupressaoLigacaoAguaActionForm.getIdOrdemServico() != null) {
			idOrdemServico = efetuarSupressaoLigacaoAguaActionForm
					.getIdOrdemServico();
		} else {
			idOrdemServico = (String) httpServletRequest
					.getAttribute("veioEncerrarOS");
			efetuarSupressaoLigacaoAguaActionForm.setDataSupressao(
					(String) httpServletRequest.getAttribute("dataEncerramento"));
				
				sessao.setAttribute("caminhoRetornoIntegracaoComercial",
					httpServletRequest.getAttribute("caminhoRetornoIntegracaoComercial"));
		}

		if (httpServletRequest.getAttribute("semMenu") != null) {
			sessao.setAttribute("semMenu", "SIM");
		} else {
			sessao.removeAttribute("semMenu");
		}

		this.pesquisarSelectObrigatorio(httpServletRequest,
				efetuarSupressaoLigacaoAguaActionForm);

		/*
		 * // Verifica se o id da Ordem de servico vem da sessao. if
		 * (sessao.getAttribute("idOrdemServico") != null) { idOrdemServico =
		 * (String) sessao.getAttribute("idOrdemServico"); } else {
		 * idOrdemServico = efetuarSupressaoLigacaoAguaActionForm
		 * .getIdOrdemServico(); }
		 */

		OrdemServico ordemServico = null;

		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

			ordemServico = fachada.recuperaOSPorId(new Integer(idOrdemServico));

			if (ordemServico != null) {

				fachada.validarExibirSupressaoLigacaoAgua(ordemServico,
						veioEncerrarOS);

				efetuarSupressaoLigacaoAguaActionForm.setIdOrdemServico(idOrdemServico);
				efetuarSupressaoLigacaoAguaActionForm.setVeioEncerrarOS(""
						+ veioEncerrarOS);

				efetuarSupressaoLigacaoAguaActionForm
						.setNomeOrdemServico(ordemServico.getServicoTipo()
								.getDescricao());

				sessao.setAttribute("ordemServico", ordemServico);

				//Comentado por Raphael Rossiter em 28/02/2007
				//Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
				Imovel imovel = ordemServico.getImovel();
				BigDecimal valorDebito = new BigDecimal(0);
				
				if (ordemServico.getServicoTipo() != null
						&& ordemServico.getServicoTipo().getDebitoTipo() != null) {
					efetuarSupressaoLigacaoAguaActionForm
							.setIdTipoDebito(ordemServico.getServicoTipo()
									.getDebitoTipo().getId().toString());
					efetuarSupressaoLigacaoAguaActionForm
							.setDescricaoTipoDebito(ordemServico
									.getServicoTipo().getDebitoTipo()
									.getDescricao());
					
					
					//[FS0013] - Alteração de Valor
					this.permitirAlteracaoValor(ordemServico.getServicoTipo(), efetuarSupressaoLigacaoAguaActionForm);
					
					String calculaValores = httpServletRequest.getParameter("calculaValores");
					
					
					SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
					Integer qtdeParcelas = null;
					
					if(calculaValores != null && calculaValores.equals("S")){
						
						//[UC0186] - Calcular Prestação
						BigDecimal  taxaJurosFinanciamento = null; 
						qtdeParcelas = new Integer(efetuarSupressaoLigacaoAguaActionForm.getQuantidadeParcelas());
						
						if(ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM.shortValue() && 
							qtdeParcelas.intValue() != 1){
							
							taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
						}else{
							taxaJurosFinanciamento = new BigDecimal(0);
						}
						
						BigDecimal valorPrestacao = null;
						if(taxaJurosFinanciamento != null){
							
							valorDebito = Util.formatarMoedaRealparaBigDecimal(efetuarSupressaoLigacaoAguaActionForm.getValorDebito());
							
							String percentualCobranca = efetuarSupressaoLigacaoAguaActionForm.getPercentualCobranca();
							
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
							efetuarSupressaoLigacaoAguaActionForm.setValorParcelas(valorPrestacaoComVirgula);
						} else {
							efetuarSupressaoLigacaoAguaActionForm.setValorParcelas("0,00");
						}						
						
					}else{
						
						// Valor do Débitou
						short tipoMedicao = 1;
						
						valorDebito = Fachada.getInstancia().obterValorDebito(
								ordemServico.getServicoTipo().getId(),
								ordemServico.getImovel().getId(), tipoMedicao);

						efetuarSupressaoLigacaoAguaActionForm.setValorDebito(Util
								.formataBigDecimal(valorDebito, 2, true));
						
					}

					if (ordemServico.getServicoNaoCobrancaMotivo() != null) {
						efetuarSupressaoLigacaoAguaActionForm
								.setMotivoNaoCobranca(ordemServico
										.getServicoNaoCobrancaMotivo().getId()
										.toString());
					}

					if (ordemServico.getPercentualCobranca() != null) {
						efetuarSupressaoLigacaoAguaActionForm
								.setPercentualCobranca(ordemServico
										.getPercentualCobranca().toString());
					}
				}

				String matriculaImovel = imovel.getId().toString();
				efetuarSupressaoLigacaoAguaActionForm.setMatriculaImovel(""
						+ matriculaImovel);

				/*-------------- Início dados do Imóvel---------------*/

				//Comentado por Raphael Rossiter em 28/02/2007
				//sessao.setAttribute("imovel", ordemServico.getRegistroAtendimento().getImovel());
				sessao.setAttribute("imovel", ordemServico.getImovel());

				if (imovel != null) {

					// Matricula Imóvel
					efetuarSupressaoLigacaoAguaActionForm
							.setMatriculaImovel(imovel.getId().toString());

					// Inscrição Imóvel
					String inscricaoImovel = fachada
							.pesquisarInscricaoImovel(imovel.getId());
					efetuarSupressaoLigacaoAguaActionForm
							.setInscricaoImovel(inscricaoImovel);

					// Situação da Ligação de Agua

					String situacaoLigacaoAgua = imovel
							.getLigacaoAguaSituacao().getDescricao();
					efetuarSupressaoLigacaoAguaActionForm
							.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

					// Situação da Ligação de Esgoto
					String situacaoLigacaoEsgoto = imovel
							.getLigacaoEsgotoSituacao().getDescricao();
					efetuarSupressaoLigacaoAguaActionForm
							.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

					this.pesquisarCliente(
							efetuarSupressaoLigacaoAguaActionForm, new Integer(
									matriculaImovel));
				}

				// Data Encerramento
				String dataEncerramentoOdServico = Util
						.formatarData(ordemServico.getDataEncerramento());
                if(dataEncerramentoOdServico != null && !dataEncerramentoOdServico.equals("")){
				  efetuarSupressaoLigacaoAguaActionForm
						.setDataSupressao(dataEncerramentoOdServico);
                }

				LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();

				// Supressao Motivo
				if (ligacaoAgua.getSupressaoMotivo() != null) {

					String supressaoMotivo = ligacaoAgua.getSupressaoMotivo()
							.getId().toString();
					efetuarSupressaoLigacaoAguaActionForm
							.setMotivoSupressao(supressaoMotivo);
				}

				// Supressao Tipo

				if (httpServletRequest.getParameter("combo") == null) {
					if (ligacaoAgua.getSupressaoTipo() != null) {

						String supressaoTipo = ligacaoAgua.getSupressaoTipo()
								.getId().toString();
						efetuarSupressaoLigacaoAguaActionForm
								.setTipoSupressao(supressaoTipo);

						if (ligacaoAgua.getSupressaoTipo().getIndicadorTotal() == ConstantesSistema.INDICADOR_USO_ATIVO
								.shortValue()) {
							efetuarSupressaoLigacaoAguaActionForm
									.setIndicadorTipoSupressao(ConstantesSistema.INDICADOR_USO_ATIVO
											.toString());
						} else {
							efetuarSupressaoLigacaoAguaActionForm
									.setIndicadorTipoSupressao(ConstantesSistema.INDICADOR_USO_DESATIVO
											.toString());
						}

					}
				}
				// Selo Supressão
				if (ligacaoAgua.getNumeroSeloSupressao() != null) {

					String NumeroSeloSupressao = ligacaoAgua
							.getNumeroSeloSupressao().toString();
					efetuarSupressaoLigacaoAguaActionForm
							.setNumeroSeloSupressao(NumeroSeloSupressao);

				} else {
					efetuarSupressaoLigacaoAguaActionForm
							.setNumeroSeloSupressao("");
				}
				
				if (ligacaoAgua.getHidrometroInstalacaoHistorico() != null) {

					FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();

					filtroHidrometroInstalacaoHistorico
							.adicionarParametro(new ParametroSimples(
									FiltroHidrometroInstalacaoHistorico.ID,
									ligacaoAgua
											.getHidrometroInstalacaoHistorico()
											.getId()));

					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("hidrometroProtecao");

					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("hidrometro");
					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("rateioTipo");
					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalInstalacao");
					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("medicaoTipo");

					Collection colecaoHidrometroInstalacaoHistorico = fachada
							.pesquisar(filtroHidrometroInstalacaoHistorico,
									HidrometroInstalacaoHistorico.class
											.getName());

					HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = (HidrometroInstalacaoHistorico) colecaoHidrometroInstalacaoHistorico
							.iterator().next();

					if (hidrometroInstalacaoHistorico != null && hidrometroInstalacaoHistorico
							.getNumeroLeituraSupressao() != null) {
						efetuarSupressaoLigacaoAguaActionForm
								.setNumeroLeituraSupressao(""
										+ hidrometroInstalacaoHistorico
												.getNumeroLeituraSupressao());
					} else {
						efetuarSupressaoLigacaoAguaActionForm
								.setNumeroLeituraSupressao("");
					}

				} else {
					efetuarSupressaoLigacaoAguaActionForm
							.setNumeroLeituraSupressao("");
				}

				// Filtro para o campo Tipo Debito
				Collection colecaoNaoCobranca = (Collection) sessao
						.getAttribute("colecaoNaoCobranca");
				if (colecaoNaoCobranca == null) {
					FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();

					filtroServicoNaoCobrancaMotivo
							.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

					colecaoNaoCobranca = fachada.pesquisar(
							filtroServicoNaoCobrancaMotivo,
							ServicoNaoCobrancaMotivo.class.getName());

					if (colecaoNaoCobranca != null
							&& !colecaoNaoCobranca.isEmpty()) {
						sessao.setAttribute("colecaoNaoCobranca",
								colecaoNaoCobranca);
					} else {
						throw new ActionServletException(
								"atencao.naocadastrado", null,
								"Motivo da Não Cobrança");
					}
				}
				// Dados da Geração de Débito
				//this.pesquisarDadosGeracaoDebito(efetuarSupressaoLigacaoAguaActionForm, ordemServico);
				FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

				filtroServicoTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoTipo.ID, ordemServico.getServicoTipo().getId()));

				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia");
				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");
				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoPerfilTipo");
				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoTipoSubgrupo");
				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoTipoPrioridade");
				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoTipoAtividades");
				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoTipoMateriais");

				Collection colecaoServicoTipo = Fachada.getInstancia().pesquisar(
						filtroServicoTipo, ServicoTipo.class.getName());

				ServicoTipo servicoTipo = (ServicoTipo) colecaoServicoTipo.iterator().next();

				// Filtro para carregar o Cliente
				if (servicoTipo.getDebitoTipo() != null){
					
					FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

					filtroDebitoTipo.adicionarParametro(new ParametroSimples(
							FiltroDebitoTipo.ID, servicoTipo.getDebitoTipo().getId().toString()));

					Collection colecaoDebitoTipo = Fachada.getInstancia().pesquisar(
							filtroDebitoTipo, DebitoTipo.class.getName());

					DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo.iterator().next();

					if (debitoTipo.getId() != null && !debitoTipo.getId().equals("")) {

						// Codigo/descricao
						efetuarSupressaoLigacaoAguaActionForm.setIdTipoDebito(debitoTipo
								.getId().toString());
						efetuarSupressaoLigacaoAguaActionForm
								.setDescricaoTipoDebito(debitoTipo.getDescricao());
					} else {
						// Codigo/descricao
						efetuarSupressaoLigacaoAguaActionForm.setIdTipoDebito("");
						efetuarSupressaoLigacaoAguaActionForm.setDescricaoTipoDebito("");

					}
				}

				
				// -----------------------------------------------------------
				// Verificar permissão especial
				boolean temPermissaoMotivoNaoCobranca = fachada.verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
				// -----------------------------------------------------------
				
				if (temPermissaoMotivoNaoCobranca) {
					httpServletRequest.setAttribute("permissaoMotivoNaoCobranca", temPermissaoMotivoNaoCobranca);
				}else{
					efetuarSupressaoLigacaoAguaActionForm.setPercentualCobranca("100");
					efetuarSupressaoLigacaoAguaActionForm.setQuantidadeParcelas("1");
					efetuarSupressaoLigacaoAguaActionForm.setValorParcelas(Util.formataBigDecimal(valorDebito,2,true));
				}

			} else {

				httpServletRequest
						.setAttribute("OrdemServicoInexistente", true);
				efetuarSupressaoLigacaoAguaActionForm.setIdOrdemServico("");
				efetuarSupressaoLigacaoAguaActionForm
						.setNomeOrdemServico("ORDEM DE SERVIÇO INEXISTENTE");

			}
		}

		return retorno;
	}
	
	
	/*
	 * [FS0013 - Alteração de Valor]
	 * 
	 * autor: Raphael Rossiter
	 * data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo, EfetuarSupressaoLigacaoAguaActionForm form){
		
		if (servicoTipo.getIndicadorPermiteAlterarValor() == 
			ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()){
			
			form.setAlteracaoValor("OK");
		}
		else{
			form.setAlteracaoValor("");
		}
		
	}
	

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Pinto
	 * @date 22/08/2006
	 */
	private void pesquisarCliente(
			EfetuarSupressaoLigacaoAguaActionForm efetuarSupressaoLigacaoAguaActionForm,
			Integer matriculaImovel) {

		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID, matriculaImovel));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
				ClienteRelacaoTipo.USUARIO));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.DATA_FIM_RELACAO));

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection colecaoClienteImovel = Fachada.getInstancia().pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());

		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

			ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel
					.iterator().next();

			Cliente cliente = clienteImovel.getCliente();

			String documento = "";

			if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				documento = cliente.getCpfFormatado();
			} else {
				documento = cliente.getCnpjFormatado();
			}
			// Cliente Nome/CPF-CNPJ
			efetuarSupressaoLigacaoAguaActionForm.setClienteUsuario(cliente
					.getNome());
			efetuarSupressaoLigacaoAguaActionForm.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}
	}

	// Dados da Geração de Débito
/*	private void pesquisarDadosGeracaoDebito(
			EfetuarSupressaoLigacaoAguaActionForm efetuarSupressaoLigacaoAguaActionForm,
			OrdemServico ordemServico) {



	}*/

	/**
	 * Pesquisa o local de instalação Pesquisa hidrometro proteção
	 */
	private void pesquisarSelectObrigatorio(
			HttpServletRequest httpServletRequest,
			EfetuarSupressaoLigacaoAguaActionForm efetuarSupressaoLigacaoAguaActionForm) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// ************ Tipo Supressao***************

		// Vericacao dequal o tipo de supressao informada
		// pelo usuario para
		// habilitar no combo box as opcoes correspondentes
		// Parte relativa ao campo Tipo da Supressao Parcial

		FiltroSupressaoTipo filtroSupressaoTipo = new FiltroSupressaoTipo();

		if (efetuarSupressaoLigacaoAguaActionForm.getIndicadorTipoSupressao() != null) {

			if (efetuarSupressaoLigacaoAguaActionForm
					.getIndicadorTipoSupressao().equals("1")) {

				filtroSupressaoTipo.adicionarParametro(new ParametroSimples(
						FiltroSupressaoTipo.INDICADOR_TOTAL,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection<SupressaoTipo> colecaoSupressaoTipo = fachada
						.pesquisar(filtroSupressaoTipo, SupressaoTipo.class
								.getName());

				if (colecaoSupressaoTipo == null
						|| colecaoSupressaoTipo.isEmpty()) {
					throw new ActionServletException(
							"atencao.entidade_sem_dados_para_selecao", null,
							"Tabela Supressão Tipo ");
				}

				efetuarSupressaoLigacaoAguaActionForm
						.setIndicadorTipoSupressao("1");
				httpServletRequest.setAttribute("colecaoSupressaoTipo",
						colecaoSupressaoTipo);

			} else {
				if (efetuarSupressaoLigacaoAguaActionForm
						.getIndicadorTipoSupressao().equals("2")) {

					filtroSupressaoTipo
							.adicionarParametro(new ParametroSimples(
									FiltroSupressaoTipo.INDICADOR_PARCIAL,
									ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection<SupressaoTipo> colecaoSupressaoTipo = fachada
							.pesquisar(filtroSupressaoTipo, SupressaoTipo.class
									.getName());

					if (colecaoSupressaoTipo == null
							|| colecaoSupressaoTipo.isEmpty()) {
						throw new ActionServletException(
								"atencao.entidade_sem_dados_para_selecao",
								null, "Tabela Supressão Tipo ");
					}
					efetuarSupressaoLigacaoAguaActionForm
							.setIndicadorTipoSupressao("2");
					httpServletRequest.setAttribute("colecaoSupressaoTipo",
							colecaoSupressaoTipo);
				}
			}
		} else {

			efetuarSupressaoLigacaoAguaActionForm
					.setIndicadorTipoSupressao("1");

			filtroSupressaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSupressaoTipo.INDICADOR_TOTAL,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection<SupressaoTipo> colecaoSupressaoTipo = fachada.pesquisar(
					filtroSupressaoTipo, SupressaoTipo.class.getName());

			if (colecaoSupressaoTipo == null || colecaoSupressaoTipo.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Tabela Supressão Tipo ");
			}
			httpServletRequest.setAttribute("colecaoSupressaoTipo",
					colecaoSupressaoTipo);

		}
		// Supressao Motivo
		FiltroSupressaoMotivo filtroSupressaoMotivo = new FiltroSupressaoMotivo();

		Collection<SupressaoMotivo> colecaoSupressaoMotivo = fachada.pesquisar(
				filtroSupressaoMotivo, SupressaoMotivo.class.getName());

		httpServletRequest.setAttribute("colecaoMotivoSupressao",
				colecaoSupressaoMotivo);

		// Filtro para o campo Tpo Debito
		Collection colecaoNaoCobranca = (Collection) sessao
				.getAttribute("colecaoNaoCobranca");
		if (colecaoNaoCobranca == null) {
			FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();

			filtroServicoNaoCobrancaMotivo
					.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

			colecaoNaoCobranca = Fachada.getInstancia().pesquisar(
					filtroServicoNaoCobrancaMotivo,
					ServicoNaoCobrancaMotivo.class.getName());

			if (colecaoNaoCobranca != null && !colecaoNaoCobranca.isEmpty()) {
				sessao.setAttribute("colecaoNaoCobranca", colecaoNaoCobranca);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Motivo da Não Cobrança");
			}
		}
	}

}
