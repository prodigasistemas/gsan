package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.FiltroCorteTipo;
import gcom.atendimentopublico.ligacaoagua.FiltroMotivoCorte;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
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

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.LigacaoTipo;
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
 * Action responsável pela pre-exibição da pagina de efetuar corte de ligação de água
 * 
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 * 
 * Refeito
 * @author Leonardo Regis
 * @date 23/09/2006
 */
public class ExibirEfetuarCorteLigacaoAguaAction extends GcomAction {
	
	/**
	 * [UC0355] Efetuar Corte de Ligação de Água
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

		ActionForward retorno = actionMapping.findForward("efetuarCorteLigacaoAgua");
		HttpSession sessao = httpServletRequest.getSession(false);
		EfetuarCorteLigacaoAguaActionForm form = (EfetuarCorteLigacaoAguaActionForm) actionForm;
				
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Veio de Encerrar OS
		Boolean veioEncerrarOS = null;
		if(httpServletRequest.getAttribute("veioEncerrarOS") != null){
			veioEncerrarOS = Boolean.TRUE;
		}else{
			if (form.getVeioEncerrarOS() != null && 
				!form.getVeioEncerrarOS().equals("")) {
				
				if (form.getVeioEncerrarOS().toLowerCase().equals("true")) {
					veioEncerrarOS = veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = veioEncerrarOS = Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}
		
		// Seta Coleções
		getMotivoCorteCollection(sessao);
		getTipoCorteCollection(sessao);
		getMotivoNaoCobrancaCollection(sessao);
		
		String idOrdemServico = null;
		if(form.getIdOrdemServico() != null){
			idOrdemServico = form.getIdOrdemServico();
		}else{
			idOrdemServico = (String)httpServletRequest.getAttribute("veioEncerrarOS");
			form.setDataCorte((String) httpServletRequest.getAttribute("dataEncerramento"));
			sessao.setAttribute("caminhoRetornoIntegracaoComercial",httpServletRequest.getAttribute("caminhoRetornoIntegracaoComercial"));
		}
		
		if(httpServletRequest.getAttribute("semMenu") != null){
			sessao.setAttribute("semMenu", "SIM");
		}else{
			sessao.removeAttribute("semMenu");
		}
		
		// Testa OS
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {
			OrdemServico ordemServico = this.getFachada().recuperaOSPorId(new Integer(idOrdemServico));
			if (ordemServico != null) {
				
				sessao.setAttribute("ordemServico", ordemServico);
				
				// Valida Exibição de Corte de Ligação de Água
				this.getFachada().validarExibirCorteLigacaoAgua(ordemServico,veioEncerrarOS);
				form.setVeioEncerrarOS(""+veioEncerrarOS);
				
				// OS
				form.setIdOrdemServico(ordemServico.getId()+"");
				form.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
				
				// Preencher dados do imovel
				this.preencherDadosImovel(form, ordemServico);
				
				// Preencher dados do Corte da Ligação
				this.pesquisarDadosCorteLigacao(sessao, form, ordemServico);
				
				// Preencher dados da Geração
				// Tipo Débito
				if (ordemServico.getServicoTipo().getDebitoTipo() != null) {
					form.setIdTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getId()+"");
					form.setDescricaoTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getDescricao()+"");
				}else{
					form.setIdTipoDebito("");
					form.setDescricaoTipoDebito("");			
				}
				
				
				//[FS0013] - Alteração de Valor
				this.permitirAlteracaoValor(ordemServico.getServicoTipo(), form);
				
				String calculaValores = httpServletRequest.getParameter("calculaValores");
				
				BigDecimal valorDebito = new BigDecimal(0);
				SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
				Integer qtdeParcelas = null;
				
				if(calculaValores != null && calculaValores.equals("S")){
					
					//[UC0186] - Calcular Prestação
					BigDecimal  taxaJurosFinanciamento = null; 
					qtdeParcelas = new Integer(form.getQuantidadeParcelas());
					
					if(ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM.shortValue() && 
						qtdeParcelas.intValue() != 1){
						
						taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
					}else{
						taxaJurosFinanciamento = new BigDecimal(0);
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
				
					valorDebito = 
						this.getFachada().obterValorDebito(ordemServico.getServicoTipo().getId(), 
							ordemServico.getImovel().getId(), 
							new Short(LigacaoTipo.LIGACAO_AGUA+""));
					
					if (valorDebito != null) {
						String valorDebitoComVirgula = valorDebito+"";
						form.setValorDebito(valorDebitoComVirgula.replace(".",","));
					} else {
						form.setValorDebito("0,00");
					}
				}
				
				form.setQtdeMaxParcelas(sistemaParametro.getNumeroMaximoParcelasFinanciamento()+"");
				if(ordemServico.getServicoNaoCobrancaMotivo() != null){
					form.setMotivoNaoCobranca(ordemServico.getServicoNaoCobrancaMotivo().getId().toString());
				}
				if(ordemServico.getServicoNaoCobrancaMotivo() != null){
					form.setPercentualCobranca(ordemServico.getPercentualCobranca().toString());
				}
				// Verificar permissão especial
				boolean temPermissaoMotivoNaoCobranca = 
					this.getFachada().verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
				
				if (temPermissaoMotivoNaoCobranca) {
					httpServletRequest.setAttribute("permissaoMotivoNaoCobranca", temPermissaoMotivoNaoCobranca);
				}else{
					form.setPercentualCobranca("100");
					form.setQuantidadeParcelas("1");
					form.setValorParcelas(Util.formataBigDecimal(valorDebito,2,true));
				}

				sessao.setAttribute("osEncontrada", "true");
			} else {
				sessao.removeAttribute("osEncontrada");
				sessao.removeAttribute("ordemServico");
				form.setNomeOrdemServico("Ordem de Serviço inexistente");
				form.setIdOrdemServico("");
			}

		} else {
			httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
			form.reset();
		}
		
		return retorno;
	}
	
	
	/*
	 * [FS0013 - Alteração de Valor]
	 * 
	 * autor: Raphael Rossiter
	 * data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo, EfetuarCorteLigacaoAguaActionForm form){
		
		if (servicoTipo.getIndicadorPermiteAlterarValor() == 
			ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()){
			
			form.setAlteracaoValor("OK");
		}
		else{
			form.setAlteracaoValor("");
		}
		
	}

	/**
	 * Preencher dados do corte da ligação
	 * 
	 * @author Leonardo Regis
	 * @date 23/09/2006
	 *
	 * @param sessao
	 * @param form
	 * @param os
	 */
	private void pesquisarDadosCorteLigacao(HttpSession sessao, EfetuarCorteLigacaoAguaActionForm form, OrdemServico ordemServico) {
		//Data Encerramento
		if(ordemServico.getDataEncerramento() != null && !ordemServico.getDataEncerramento().equals("")){
		 form.setDataCorte(Util.formatarData(ordemServico.getDataEncerramento()));
		}
		
		//Comentado por Raphael Rossiter em 28/02/2007
		// Motivo do Corte
		/*if(ordemServico.getRegistroAtendimento().getImovel().getLigacaoAgua().getMotivoCorte() != null){
			form.setMotivoCorte(""+ordemServico.getRegistroAtendimento().getImovel().getLigacaoAgua().getMotivoCorte().getId());	
		}
		// Tipo do Corte
		if(ordemServico.getRegistroAtendimento().getImovel().getLigacaoAgua().getCorteTipo() != null) {
			form.setTipoCorte(""+ordemServico.getRegistroAtendimento().getImovel().getLigacaoAgua().getCorteTipo().getId());
		}
		// Leitura do Corte
		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = 
			ordemServico.getRegistroAtendimento().getImovel().getLigacaoAgua().getHidrometroInstalacaoHistorico();
		if(hidrometroInstalacaoHistorico != null && 
		   hidrometroInstalacaoHistorico.getNumeroLeituraCorte() != null){
			form.setNumLeituraCorte(""+hidrometroInstalacaoHistorico.getNumeroLeituraCorte());
		}
		// Número do Selo do Corte
		if(ordemServico.getRegistroAtendimento().getImovel().getLigacaoAgua().getNumeroSeloCorte() != null){
			form.setNumSeloCorte(""+ordemServico.getRegistroAtendimento().getImovel().getLigacaoAgua().getNumeroSeloCorte());	
		}*/
		
		
		if(ordemServico.getImovel().getLigacaoAgua().getMotivoCorte() != null){
			form.setMotivoCorte(""+ordemServico.getImovel().getLigacaoAgua().getMotivoCorte().getId());	
		}
		// Tipo do Corte
		if(ordemServico.getImovel().getLigacaoAgua().getCorteTipo() != null) {
			form.setTipoCorte(""+ordemServico.getImovel().getLigacaoAgua().getCorteTipo().getId());
		}
		// Leitura do Corte
		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = 
			ordemServico.getImovel().getLigacaoAgua().getHidrometroInstalacaoHistorico();
		if(hidrometroInstalacaoHistorico != null && 
		   hidrometroInstalacaoHistorico.getNumeroLeituraCorte() != null){
			form.setNumLeituraCorte(""+hidrometroInstalacaoHistorico.getNumeroLeituraCorte());
		}
		// Número do Selo do Corte
		if(ordemServico.getImovel().getLigacaoAgua().getNumeroSeloCorte() != null){
			form.setNumSeloCorte(""+ordemServico.getImovel().getLigacaoAgua().getNumeroSeloCorte());	
		}
	}
	
	/**
	 * Preencher dados do imóvel
	 * 
	 * @author Leonardo Regis
	 * @date 23/09/2006
	 * 
	 * @param form
	 * @param os
	 */
	private void preencherDadosImovel(EfetuarCorteLigacaoAguaActionForm form, OrdemServico ordemServico) {
		
		//Comentado por Raphael Rossiter em 28/02/2007
		//Imovel imovel= ordemServico.getRegistroAtendimento().getImovel();
		Imovel imovel= ordemServico.getImovel();
		
		// Matricula Imóvel
		form.setMatriculaImovel(imovel.getId().toString());
		// Inscrição Imóvel
		String inscricaoImovel = this.getFachada().pesquisarInscricaoImovel(imovel.getId());
		form.setInscricaoImovel(inscricaoImovel);
		// Situação da Ligação de Agua
		String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
		form.setSituacaoLigacaoAgua(situacaoLigacaoAgua);
		// Situação da Ligação de Esgoto
		String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
		form.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);
		// Cliente
		this.pesquisarCliente(form, ordemServico);
 	}		
	
	/**
	 * Carrega coleção de motivo do corte.
	 *
	 * @author Leonardo Regis
	 * @date 23/09/2006
	 *
	 * @param sessao
	 */
	private void getMotivoNaoCobrancaCollection(HttpSession sessao) {
		// Filtra Motivo da Não Cobrança
		FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();
		filtroServicoNaoCobrancaMotivo.setCampoOrderBy(FiltroServicoNaoCobrancaMotivo.DESCRICAO);

		Collection colecaoServicoNaoCobrancaMotivo = this.getFachada().pesquisar( filtroServicoNaoCobrancaMotivo, ServicoNaoCobrancaMotivo.class.getName());
		if (colecaoServicoNaoCobrancaMotivo != null && !colecaoServicoNaoCobrancaMotivo.isEmpty()) {
			sessao.setAttribute("colecaoMotivoNaoCobranca",	colecaoServicoNaoCobrancaMotivo);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Motivo Não Cobrança");
		}
	}
	
	/**
	 * Pesquisa Cliente
	 * 
	 * @author Leonardo Regis
	 * @date 23/09/2006
	 * 
	 * @param form
	 * @param os
	 */
	private void pesquisarCliente(EfetuarCorteLigacaoAguaActionForm form, OrdemServico ordemServico) {
		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		
		//Comentado por Raphael Rossiter em 28/02/2007
		//filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, ordemServico.getRegistroAtendimento().getImovel().getId()));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, ordemServico.getImovel().getId()));
		
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO));
		filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
		
		Collection colecaoClienteImovel = this.getFachada().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

			ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel.iterator().next();
			Cliente cliente = clienteImovel.getCliente();

			String documento = "";
			if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				documento = cliente.getCpfFormatado();
			} else {
				documento = cliente.getCnpjFormatado();
			}
			// Cliente Nome/CPF-CNPJ
			form.setClienteUsuario(cliente.getNome());
			form.setCpfCnpjCliente(documento);
		}else {
			throw new ActionServletException("atencao.naocadastrado", null, "Cliente");
		}
	}
	
	/**
	 * Carrega coleção de motivo do corte.
	 *
	 * @author Leonardo Regis
	 * @date 23/09/2006
	 *
	 * @param sessao
	 */
	private void getMotivoCorteCollection(HttpSession sessao) {
		// Filtro para o campo Motivo do Corte
		FiltroMotivoCorte filtroMotivoCorteLigacaoAgua = new FiltroMotivoCorte();
		filtroMotivoCorteLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroMotivoCorte.INDICADOR_USO,
														ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroMotivoCorteLigacaoAgua.setCampoOrderBy(FiltroMotivoCorte.DESCRICAO);

		Collection colecaoMotivoCorteLigacaoAgua = this.getFachada().pesquisar(filtroMotivoCorteLigacaoAgua, MotivoCorte.class.getName());
		if (colecaoMotivoCorteLigacaoAgua != null && !colecaoMotivoCorteLigacaoAgua.isEmpty()) {
			sessao.setAttribute("colecaoMotivoCorteLigacaoAgua",colecaoMotivoCorteLigacaoAgua);
		} else {
			throw new ActionServletException("atencao.naocadastrado",null, "Motivo do Corte");
		}
	}
	
	/**
	 * Carrega coleção de tipo do corte.
	 *
	 * @author Leonardo Regis
	 * @date 25/09/2006
	 *
	 * @param sessao
	 */
	private void getTipoCorteCollection(HttpSession sessao) {
		// Filtro para o campo Motivo do Corte
		FiltroCorteTipo filtroTipoCorteLigacaoAgua = new FiltroCorteTipo();
		filtroTipoCorteLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroCorteTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroTipoCorteLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroCorteTipo.INDICADOR_CORTE_ADMINISTRATIVO, ConstantesSistema.NAO));
		filtroTipoCorteLigacaoAgua.setCampoOrderBy(FiltroCorteTipo.DESCRICAO);

		Collection colecaoTipoCorteLigacaoAgua = 
			this.getFachada().pesquisar(filtroTipoCorteLigacaoAgua, CorteTipo.class.getName());
		
		if (colecaoTipoCorteLigacaoAgua != null && !colecaoTipoCorteLigacaoAgua.isEmpty()) {
			sessao.setAttribute("colecaoTipoCorteLigacaoAgua",colecaoTipoCorteLigacaoAgua);
		} else {
			throw new ActionServletException("atencao.naocadastrado",null, "Tipo do Corte");
		}
	}	
}
