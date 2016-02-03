package gcom.gui.faturamento.conta;

import gcom.arrecadacao.ContratoDemanda;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gcom.arrecadacao.debitoautomatico.FiltroDebitoAutomaticoMovimento;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroContratoDemanda;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaCategoriaHistorico;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.ContaImpostosDeduzidos;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.FiltroContaCategoria;
import gcom.faturamento.conta.FiltroContaCategoriaHistorico;
import gcom.faturamento.conta.FiltroContaHistorico;
import gcom.faturamento.conta.FiltroContaImpostosDeduzidos;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Operacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição da tela de consultar conta
 * 
 * @author pedro alexandre
 * @created 04 de Janeiro de 2006
 */
public class ExibirConsultarContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirConsultarConta");

		HttpSession sessao = this.getSessao(httpServletRequest);
		Fachada fachada = Fachada.getInstancia();

		String limparForm = httpServletRequest.getParameter("limparForm");

		// Removendo coleções da sessão
		if (limparForm != null && !limparForm.equalsIgnoreCase("")) {
			sessao.removeAttribute("colecaoContaImovel");
		}

		String tipoConsulta = httpServletRequest.getParameter("tipoConsulta");

		sessao.removeAttribute("idContaHistorico");
		sessao.removeAttribute("idConta");
		
		Integer idImovel = null;
		String referenciaConta = null;
		Integer situacaoAtualConta = null;
		
		if (tipoConsulta.equalsIgnoreCase("conta")) {

			String idConta = httpServletRequest.getParameter("contaID");

			// vai ser recuperado na geração do relatório 2ª Via da Conta
			sessao.setAttribute("idConta", idConta);

			Conta conta = null;

			if (idConta != null && !idConta.equalsIgnoreCase("")) {

				// faz a consulta da conta por hql. Fernanda Paiva
				Collection colecaoConta = 
					this.getFachada().consultarConta(new Integer(idConta));

				if (colecaoConta == null || colecaoConta.isEmpty()) {
					throw new ActionServletException("atencao.pesquisa.conta.inexistente");
				}

				conta = (Conta) Util.retonarObjetoDeColecao(colecaoConta);

				idImovel = conta.getImovel().getId();
				referenciaConta = conta.getReferenciaFormatada();
				situacaoAtualConta = conta.getDebitoCreditoSituacaoAtual().getId();
				
				// Colocando o objeto conta selecionado na sessão
				sessao.setAttribute("conta", conta);
				sessao.removeAttribute("contaHistorico");
				sessao.removeAttribute("colecaoContaCategoriaHistorico");
				
				verificarEmissao2Via(conta.getDebitoCreditoSituacaoAtual().getId(), conta.getDataRevisao(), conta.getContaMotivoRevisao(), conta.getImovel().getId(), httpServletRequest);

			} else if (sessao.getAttribute("conta") != null) {
				conta = (Conta) sessao.getAttribute("conta");
			} else {
				throw new ActionServletException("atencao.pesquisa.conta.inexistente");
			}

			Collection colecaoContaCategoria, colecaoContaImpostosDeduzidos;

			// Removendo coleções da sessão
			if (idConta != null && !idConta.equalsIgnoreCase("")) {

				sessao.removeAttribute("colecaoContaCategoria");
				sessao.removeAttribute("colecaoContaCategoriaHistorico");
				sessao.removeAttribute("colecaoContaImpostosDeduzidos");
				sessao.removeAttribute("contaImpostosDeduzidos");
			}

			/*
			 * Categorias (Carregar coleção)
			 */
			if (sessao.getAttribute("colecaoContaCategoria") == null) {

				FiltroContaCategoria filtroContaCategoria = new FiltroContaCategoria();
				filtroContaCategoria.adicionarCaminhoParaCarregamentoEntidade("comp_id.conta");
				filtroContaCategoria.adicionarCaminhoParaCarregamentoEntidade("comp_id.categoria");

				filtroContaCategoria.adicionarParametro(
					new ParametroSimples(FiltroContaCategoria.CONTA_ID, conta.getId()));

				colecaoContaCategoria = 
					this.getFachada().pesquisar(filtroContaCategoria,
						ContaCategoria.class.getName());

				sessao.setAttribute("colecaoContaCategoria",colecaoContaCategoria);
			}

			/*
			 * Impostos Deduzidos (Pesquisar o registrode impostos deduzidos)
			 */
			if (sessao.getAttribute("contaImpostosDeduzidos") == null) {

				FiltroContaImpostosDeduzidos filtroContaImpostosDeduzidos = new FiltroContaImpostosDeduzidos();

				filtroContaImpostosDeduzidos.adicionarCaminhoParaCarregamentoEntidade("conta");
				filtroContaImpostosDeduzidos.adicionarCaminhoParaCarregamentoEntidade("impostoTipo");
				filtroContaImpostosDeduzidos.adicionarParametro(new ParametroSimples(FiltroContaImpostosDeduzidos.CONTA_ID, conta.getId()));

				colecaoContaImpostosDeduzidos = 
					this.getFachada().pesquisar(filtroContaImpostosDeduzidos,
						ContaImpostosDeduzidos.class.getName());

				ContaImpostosDeduzidos contaImpostosDeduzidos = (ContaImpostosDeduzidos) Util.retonarObjetoDeColecao(colecaoContaImpostosDeduzidos);

				sessao.setAttribute("contaImpostosDeduzidos",contaImpostosDeduzidos);
				sessao.setAttribute("colecaoContaImpostosDeduzidos",colecaoContaImpostosDeduzidos);
			}
			
			//Pesquisa O Debito Automatico Movimento
			if(conta != null && conta.getIndicadorDebitoConta() != null && 
				conta.getIndicadorDebitoConta().equals(ConstantesSistema.SIM)){
				
				this.pesquisarDebitoAutomaticoMovimento(conta.getId(),httpServletRequest);
			}

		} else if (tipoConsulta.equalsIgnoreCase("contaHistorico")) {

			String idContaHistorico = httpServletRequest.getParameter("contaID");
			
			//vai ser recuperado na geração do relatório 2ª Via da Conta
			sessao.setAttribute("idContaHistorico",idContaHistorico);

			/*
			 * Pesquisando a conta a partir do id recebido
			 */
			ContaHistorico contaHistorico = null;

			if (idContaHistorico != null && !idContaHistorico.equalsIgnoreCase("")) {
	
				// faz a consulta da conta por hql.
				Collection colecaoContaHistorico = 
					this.getFachada().consultarContaHistorico(new Integer(idContaHistorico));
				
	
				if (colecaoContaHistorico == null || colecaoContaHistorico.isEmpty()) {
					throw new ActionServletException("atencao.pesquisa.conta.inexistente");
				}
	
				contaHistorico = (ContaHistorico) Util
						.retonarObjetoDeColecao(colecaoContaHistorico);
	
				idImovel = contaHistorico.getImovel().getId();
				referenciaConta = contaHistorico.getFormatarAnoMesParaMesAno();
				situacaoAtualConta = contaHistorico.getDebitoCreditoSituacaoAtual().getId();
				
				if (contaHistorico.getValorRateioAgua() == null){
					contaHistorico.setValorRateioAgua(new BigDecimal("0.00"));
				}
				
				// Colocando o objeto conta selecionado na sessão
				sessao.setAttribute("contaHistorico", contaHistorico);
	
				sessao.removeAttribute("conta");
				sessao.removeAttribute("colecaoContaCategoria");
				sessao.removeAttribute("colecaoContaImpostosDeduzidos");
				sessao.removeAttribute("contaImpostosDeduzidos");
				
				sessao.removeAttribute("colecaoContaCategoriaHistorico");
				
				verificarEmissao2Via(contaHistorico.getDebitoCreditoSituacaoAtual().getId(), 
						contaHistorico.getDataRevisao(), contaHistorico.getContaMotivoRevisao(), contaHistorico.getImovel().getId(), httpServletRequest);
	
			} else if (sessao.getAttribute("contaHistorico") != null) {
				contaHistorico = (ContaHistorico) sessao
						.getAttribute("contaHistorico");
			} else {
				throw new ActionServletException(
						"atencao.pesquisa.conta.inexistente");
			}

			/* 
			 * Categorias (Carregar coleção)
			 */
			if (sessao.getAttribute("colecaoContaCategoriaHistorico") == null) {
			 
				FiltroContaCategoriaHistorico filtroContaHistoricoCategoria = new
				FiltroContaCategoriaHistorico();
				
				filtroContaHistoricoCategoria.adicionarCaminhoParaCarregamentoEntidade("comp_id.contaHistorico");
				filtroContaHistoricoCategoria.adicionarCaminhoParaCarregamentoEntidade("comp_id.categoria");
			  
				filtroContaHistoricoCategoria.adicionarParametro(new
					ParametroSimples(FiltroContaCategoriaHistorico.CONTA_ID,
							contaHistorico.getId()));
			  
				Collection colecaoContaCategoriaHistorico = 
					this.getFachada().pesquisar(filtroContaHistoricoCategoria,
							ContaCategoriaHistorico.class.getName());
			  
				sessao.setAttribute("colecaoContaCategoriaHistorico",colecaoContaCategoriaHistorico); 
			}
			
			//Pesquisa O Debito Automatico Movimento
			if(contaHistorico != null && contaHistorico.getIndicadorDebitoConta() != null && 
				contaHistorico.getIndicadorDebitoConta().equals(ConstantesSistema.SIM)){
				
				this.pesquisarDebitoAutomaticoMovimento(contaHistorico.getId(),httpServletRequest);
			}

		}

		// utilizado para saber se no emitir 2 via de conta, mostrará o código
		// de barras ou naum
		if (httpServletRequest.getParameter("contaSemCodigoBarras") != null) {
			httpServletRequest.setAttribute("contaSemCodigoBarras", 1);
		}

		sessao.removeAttribute("contaSemCodigoBarras");
		
		// Verifica se deve ser cobrada taxa por emissão de 2 via
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		if (sistemaParametro.getValorSegundaVia().equals(new BigDecimal("0.00"))) {
			httpServletRequest.setAttribute("naoCobrarTaxa", true);
		}
			
		// envia uma flag que será verificado no cliente_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest.getParameter("caminhoRetornoTelaConsulta") != null) {
			
			sessao.setAttribute("caminhoRetornoTelaConsultaConta",
				httpServletRequest.getParameter("caminhoRetornoTelaConsulta"));
		}
		
		sessao.removeAttribute("colecaoUsuariosContaRetificada"); 
		sessao.removeAttribute("colecaoUsuariosContaEmRevisao"); 
		sessao.removeAttribute("colecaoUsuariosContaCancelada"); 
		
		
		//adicionado por Vivianne Sousa 18/11/2010 - analista:Adriana Ribeiro
		if(!situacaoAtualConta.equals(DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)){
			//pesquisar usuários que retificaram a conta 
			Collection colecaoUsuariosContaRetificada =  fachada.pesquisarUsuario(
					Operacao.OPERACAO_CONTA_RETIFICAR,idImovel,referenciaConta);
			if(colecaoUsuariosContaRetificada != null && !colecaoUsuariosContaRetificada.isEmpty()){
				sessao.setAttribute("colecaoUsuariosContaRetificada",colecaoUsuariosContaRetificada); 
			}
			//pesquisar usuários que colocaram a conta em revisão 
			Collection colecaoUsuariosContaEmRevisao =  fachada.pesquisarUsuario(
					Operacao.OPERACAO_COLOCAR_CONTA_REVISAO,idImovel,referenciaConta);
			if(colecaoUsuariosContaEmRevisao != null && !colecaoUsuariosContaEmRevisao.isEmpty()){
				sessao.setAttribute("colecaoUsuariosContaEmRevisao",colecaoUsuariosContaEmRevisao); 
			}
			//pesquisar usuários que cancelaram a conta 
			Collection colecaoUsuariosContaCancelada =  fachada.pesquisarUsuario(
					Operacao.OPERACAO_CANCELAR_CONTA,idImovel,referenciaConta);
			if(colecaoUsuariosContaCancelada != null && !colecaoUsuariosContaCancelada.isEmpty()){
				sessao.setAttribute("colecaoUsuariosContaCancelada",colecaoUsuariosContaCancelada); 
			}
		}
		
		// Adicionado por Mariana Victor 06/01/2011
		FiltroContratoDemanda filtroContratoDemanda = new FiltroContratoDemanda();
		filtroContratoDemanda.adicionarParametro(
				new ParametroSimples(FiltroContratoDemanda.IMOVEL, idImovel));
		filtroContratoDemanda.adicionarParametro(
				new ParametroNulo(FiltroContratoDemanda.DATACONTRATOENCERRAMENTO));
		Collection colecaoContratoDemanda = fachada.pesquisar(
				filtroContratoDemanda, ContratoDemanda.class.getName());
		
		if (colecaoContratoDemanda != null && !colecaoContratoDemanda.isEmpty()) {
			Object[] consumoContratado = fachada.consultarConsumoCadastrado(idImovel);
			if (consumoContratado != null) {
				sessao.setAttribute("consumoContratado", consumoContratado[0]);
			} else {
				sessao.removeAttribute("consumoContratado");
			}
		}
		return retorno;
	}
	
	/**
	 * Verifica se emite 2º via
	 * 
	 * @author Vivianne Sousa
	 * @date 06/07/2007
	 * 
	 * @param situacaoAtualConta
	 * @param dtRevisaoConta
	 */
	private void verificarEmissao2Via(Integer situacaoAtualConta, Date dtRevisaoConta, ContaMotivoRevisao contaMotivoRevisao, Integer idImovel,
			HttpServletRequest httpServletRequest) {

		// Caso o imóvel não tenha sido excluído o usuario tem a opção de emitir a 2 via
		String inscricao = Fachada.getInstancia().pesquisarInscricaoImovel(idImovel);
		
		if (inscricao != null) {
		
			//Caso a situação atual da conta corresponda a normal, incluirda ou retificada
			// e a data de revisao esteja nula
			// o usuário tem a opção de emitir a 2 via
			if (situacaoAtualConta.equals(DebitoCreditoSituacao.NORMAL) || 
				situacaoAtualConta.equals(DebitoCreditoSituacao.RETIFICADA) || 
				situacaoAtualConta.equals(DebitoCreditoSituacao.INCLUIDA)){
			
				if(dtRevisaoConta == null || (contaMotivoRevisao != null && contaMotivoRevisao.getId().equals(ContaMotivoRevisao.REVISAO_ENTRADA_DE_PARCELAMENTO))){
					httpServletRequest.setAttribute("emitirSegundaVia", 2);
				} 
			}
		}
	}
	
	/**
	 * Pesquisa O Debito Automatico Movimento
	 * 
	 * @author Rafael Pinto
	 * @date 09/07/2008
	 * 
	 * @param idConta
	 * @param httpServletRequest
	 */
	private void pesquisarDebitoAutomaticoMovimento(Integer idConta,HttpServletRequest httpServletRequest){
		
		FiltroDebitoAutomaticoMovimento filtroDebitoAutomaticoMovimento = new FiltroDebitoAutomaticoMovimento();

		filtroDebitoAutomaticoMovimento.adicionarCaminhoParaCarregamentoEntidade("debitoAutomaticoRetornoCodigo");
		filtroDebitoAutomaticoMovimento.adicionarParametro(
			new ParametroSimples(FiltroDebitoAutomaticoMovimento.CONTA_GERAL_ID, idConta));

		Collection colecaoDebitoAutomaticoMovimento = 
			this.getFachada().pesquisar(filtroDebitoAutomaticoMovimento,
					DebitoAutomaticoMovimento.class.getName());

		DebitoAutomaticoMovimento debitoAutomaticoMovimento = 
			(DebitoAutomaticoMovimento) Util.retonarObjetoDeColecao(colecaoDebitoAutomaticoMovimento);
		
		httpServletRequest.setAttribute("debitoAutomaticoMovimento",debitoAutomaticoMovimento);
	}
}
