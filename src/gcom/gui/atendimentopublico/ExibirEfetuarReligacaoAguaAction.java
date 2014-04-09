package gcom.gui.atendimentopublico;

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
import java.util.Date;

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
 * @date 06/07/2006
 */
public class ExibirEfetuarReligacaoAguaAction extends GcomAction {
	/**
	 * [UC0357] Efetuar Religação de Água
	 * 
	 * Este caso de uso permite efetuar a religação de água, sendo chamada pela
	 * funcionalidade que encerra a execução da ordem de serviço ou chamada
	 * diretamente do menu.
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

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("efetuarReligacaoAgua");

		EfetuarReligacaoAguaActionForm efetuarReligacaoAguaActionForm = 
			(EfetuarReligacaoAguaActionForm) actionForm;

		Boolean veioEncerrarOS = null;
		if(httpServletRequest.getAttribute("veioEncerrarOS") != null){
			veioEncerrarOS = Boolean.TRUE;
		}else{
			if (efetuarReligacaoAguaActionForm.getVeioEncerrarOS() != null
					&& !efetuarReligacaoAguaActionForm
							.getVeioEncerrarOS().equals("")) {
				if (efetuarReligacaoAguaActionForm.getVeioEncerrarOS()
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
		if(efetuarReligacaoAguaActionForm.getIdOrdemServico() != null){
			idOrdemServico = efetuarReligacaoAguaActionForm.getIdOrdemServico();
		}else{
			idOrdemServico = (String)httpServletRequest.getAttribute("veioEncerrarOS");
			efetuarReligacaoAguaActionForm.setDataReligacao(
					(String) httpServletRequest.getAttribute("dataEncerramento"));
				
				sessao.setAttribute("caminhoRetornoIntegracaoComercial",
					httpServletRequest.getAttribute("caminhoRetornoIntegracaoComercial"));
		}
		
		if(httpServletRequest.getAttribute("semMenu") != null){
			sessao.setAttribute("semMenu", "SIM");
		}else{
			sessao.removeAttribute("semMenu");
		}
		
		OrdemServico ordemServico = null;

		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

			ordemServico =
				this.getFachada().recuperaOSPorId(new Integer(idOrdemServico));

			if (ordemServico != null) {
				
//				Filtro para o campo Tpo Debito
				Collection colecaoNaoCobranca = (Collection) 
					sessao.getAttribute("colecaoNaoCobranca");
				if(colecaoNaoCobranca == null){
					FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();
					
					filtroServicoNaoCobrancaMotivo.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);
					
					colecaoNaoCobranca = 
						this.getFachada().pesquisar(filtroServicoNaoCobrancaMotivo, 
							ServicoNaoCobrancaMotivo.class.getName());
					
					if (colecaoNaoCobranca != null && !colecaoNaoCobranca.isEmpty()) {
						sessao.setAttribute("colecaoNaoCobranca",colecaoNaoCobranca);
					} else {
						throw new ActionServletException("atencao.naocadastrado",null, "Motivo da Não Cobrança");
					}
				}
				
				this.getFachada().validarExibirReligacaoAgua(ordemServico,veioEncerrarOS);
				
				sessao.setAttribute("ordemServico", ordemServico);

				efetuarReligacaoAguaActionForm.setVeioEncerrarOS(""+veioEncerrarOS);
				
				Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
				
				efetuarReligacaoAguaActionForm.setIdOrdemServico(idOrdemServico);
				efetuarReligacaoAguaActionForm.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
				
				if(ordemServico.getServicoTipo() != null && ordemServico.getServicoTipo().getDebitoTipo() != null){
					efetuarReligacaoAguaActionForm.setIdTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getId().toString());
					efetuarReligacaoAguaActionForm.setDescricaoTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getDescricao());
				}
				
				
				//[FS0013] - Alteração de Valor
				this.permitirAlteracaoValor(ordemServico.getServicoTipo(), efetuarReligacaoAguaActionForm);
				
				String calculaValores = httpServletRequest.getParameter("calculaValores");
				
				BigDecimal valorDebito = new BigDecimal(0);
				SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
				Integer qtdeParcelas = null;
				
				if(calculaValores != null && calculaValores.equals("S")){
					
					//[UC0186] - Calcular Prestação
					BigDecimal  taxaJurosFinanciamento = null;
					qtdeParcelas = new Integer(efetuarReligacaoAguaActionForm.getQuantidadeParcelas());

					if(ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM.shortValue() &&
						qtdeParcelas.intValue() != 1){
						
						taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
					}else{
						taxaJurosFinanciamento = new BigDecimal(0);
					}
					
					BigDecimal valorPrestacao = null;
					if(taxaJurosFinanciamento != null){
						
						qtdeParcelas = new Integer(efetuarReligacaoAguaActionForm.getQuantidadeParcelas());
						valorDebito = new BigDecimal(efetuarReligacaoAguaActionForm.getValorDebito().replace(",","."));
						
						String percentualCobranca = efetuarReligacaoAguaActionForm.getPercentualCobranca();
						
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
						efetuarReligacaoAguaActionForm.setValorParcelas(valorPrestacaoComVirgula);
					} else {
						efetuarReligacaoAguaActionForm.setValorParcelas("0,00");
					}						
					
				}else{	
				
					valorDebito = 
						this.getFachada().obterValorDebito(
							ordemServico.getServicoTipo().getId(),
							imovel.getId(), 
							new Short(LigacaoTipo.LIGACAO_AGUA+""));				
					
					if (valorDebito != null) {
						String valorDebitoComVirgula = valorDebito+"";
						efetuarReligacaoAguaActionForm.setValorDebito(valorDebitoComVirgula.replace(".",","));
					} else {
						efetuarReligacaoAguaActionForm.setValorDebito("0,00");
					}
				}
				
				if(ordemServico.getServicoNaoCobrancaMotivo() != null){
					efetuarReligacaoAguaActionForm.setMotivoNaoCobranca(ordemServico.getServicoNaoCobrancaMotivo().getId().toString());
				}
				
				if(ordemServico.getPercentualCobranca() != null){
					efetuarReligacaoAguaActionForm.setPercentualCobranca(ordemServico.getPercentualCobranca().toString());
				}
				
				sessao.setAttribute("imovel", imovel);

				String matriculaImovel = imovel.getId().toString();
				efetuarReligacaoAguaActionForm.setMatriculaImovel("" + matriculaImovel);

				// Nome Ordem Servico
				efetuarReligacaoAguaActionForm.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
				
				// Matricula do Imovel
				matriculaImovel = imovel.getId().toString();
				efetuarReligacaoAguaActionForm.setMatriculaImovel(matriculaImovel);

				// Inscrição do Imovél
				String inscricaoImovel = imovel.getInscricaoFormatada();
				efetuarReligacaoAguaActionForm.setInscricaoImovel(inscricaoImovel);

				// Cliente Imovel
				this.pesquisarCliente(efetuarReligacaoAguaActionForm);

				// Situação da Ligação de Agua
				String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
				efetuarReligacaoAguaActionForm.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

				// Situação da Ligação de Esgoto
				String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
				efetuarReligacaoAguaActionForm.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

				Date dataReligacao = ordemServico.getDataEncerramento();
				if(dataReligacao != null && !dataReligacao.equals("")){
				 efetuarReligacaoAguaActionForm.setDataReligacao(""+ Util.formatarData(dataReligacao));
				}
				
				// -----------------------------------------------------------
				// Verificar permissão especial
				boolean temPermissaoMotivoNaoCobranca = 
					this.getFachada().verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
				// -----------------------------------------------------------
				
				if (temPermissaoMotivoNaoCobranca) {
					httpServletRequest.setAttribute("permissaoMotivoNaoCobranca", temPermissaoMotivoNaoCobranca);
				}else{
					efetuarReligacaoAguaActionForm.setPercentualCobranca("100");
					efetuarReligacaoAguaActionForm.setQuantidadeParcelas("1");
					efetuarReligacaoAguaActionForm.setValorParcelas(Util.formataBigDecimal(valorDebito,2,true));
				}

			} else {
				httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
				httpServletRequest.setAttribute("OrdemServicoInexistente", true);
				efetuarReligacaoAguaActionForm.setIdOrdemServico("");
				efetuarReligacaoAguaActionForm.setNomeOrdemServico("ORDEM DE SERVIÇO INEXISTENTE");

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
	private void permitirAlteracaoValor(ServicoTipo servicoTipo, EfetuarReligacaoAguaActionForm form){
		
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
	 * @date 25/08/2006
	 */
	private void pesquisarCliente(EfetuarReligacaoAguaActionForm efetuarReligacaoAguaActionForm) {
		
		//Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
	
		filtroClienteImovel.adicionarParametro(
			new ParametroSimples(FiltroClienteImovel.IMOVEL_ID,
					efetuarReligacaoAguaActionForm.getMatriculaImovel()));
	
		filtroClienteImovel
			.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,ClienteRelacaoTipo.USUARIO));
	
		filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
	
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
	
		Collection colecaoClienteImovel = 
			this.getFachada().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
	
		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
	
			ClienteImovel clienteImovel = 
				(ClienteImovel) colecaoClienteImovel.iterator().next();
			
			Cliente cliente = clienteImovel.getCliente();
	
			String documento = "";
	
			if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				documento = cliente.getCpfFormatado();
			} else {
				documento = cliente.getCnpjFormatado();
			}
			//Cliente Nome/CPF-CNPJ
			efetuarReligacaoAguaActionForm.setClienteUsuario(cliente.getNome());
			efetuarReligacaoAguaActionForm.setCpfCnpjCliente(documento);
	
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Cliente");
		}
	}

}
