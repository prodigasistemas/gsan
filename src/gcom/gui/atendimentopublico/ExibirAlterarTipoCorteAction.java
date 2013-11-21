package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.FiltroCorteTipo;
import gcom.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
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
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Atualiza o tipo de corte
 * 
 * Autor: Hugo Amorim
 * 
 * Data: 18/05/2009
 */
public class ExibirAlterarTipoCorteAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirAlterarTipoCorteAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		AlterarTipoCorteActionForm form = (AlterarTipoCorteActionForm) actionForm;
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		//Veio de Encerrar OS
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
		
		boolean menu = false;
		if(httpServletRequest.getParameter("menu") != null){
			sessao.setAttribute("menu", "sim");
			menu = true;
		}else{
			sessao.removeAttribute("menu");
			menu = false;
		}
		
		//Testa OS
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {
			
			OrdemServico ordemServico = this.getFachada().recuperaOSPorId(new Integer(idOrdemServico));
			if(ordemServico != null){
			//Caso OS esteja encerrada
			if(menu==true && ordemServico != null && ordemServico.getSituacao()!=OrdemServico.SITUACAO_ENCERRADO){
				
				sessao.removeAttribute("osEncontrada");
				sessao.removeAttribute("ordemServico");
				
				Short situacao = ordemServico.getSituacao();
				String msg = null;
				
				if(situacao.shortValue()==OrdemServico.SITUACAO_EXECUCAO_EM_ANDAMENTO.shortValue()){
					msg = OrdemServico.SITUACAO_DESCRICAO_EXECUCAO_EM_ANDAMENTO;
				}else if(situacao.shortValue()==OrdemServico.SITUACAO_AGUARDANDO_LIBERACAO.shortValue()){
					msg = OrdemServico.SITUACAO_DESCRICAO_AGUARDANDO_LIBERACAO;
				}else if(situacao.shortValue()==OrdemServico.SITUACAO_ENCERRADO.shortValue()){
					msg = OrdemServico.SITUACAO_DESCRICAO_ENCERRADO;
				}else if(situacao.shortValue()==OrdemServico.SITUACAO_PENDENTE.shortValue()){
					msg = OrdemServico.SITUACAO_DESCRICAO_PENDENTE;
				}
				throw new ActionServletException("atencao.os_situacao",null,msg);
			
				//Caso OS não possua imovel associado a mesma.	
			}else if(menu==true && ordemServico.getImovel()==null){
				
				sessao.removeAttribute("osEncontrada");
				sessao.removeAttribute("ordemServico");
				throw new ActionServletException("atencao.atencao.imovel_nao_associado_ra",null,ordemServico.getRegistroAtendimento().getId().toString());
				
				//Caso OS possua um imovel não ativo	
			}else if(ordemServico.getImovel().getIndicadorExclusao().shortValue()==1){
				sessao.removeAttribute("osEncontrada");
				sessao.removeAttribute("ordemServico");
	
				throw new ActionServletException("atencao.imovel_nao_ativo",null,ordemServico.getImovel().getId().toString());
				
				//Caso OS esteja encerrada porem não foi executada	
			}else if(menu==true && ordemServico.getAtendimentoMotivoEncerramento().getIndicadorExecucao()!=1){
				sessao.removeAttribute("osEncontrada");
				sessao.removeAttribute("ordemServico");
				throw new ActionServletException("atencao.os_encerrado_nao_executado");
				//Caso OS possuia ligação de agua diferente de CORTADA
			}else if(ordemServico.getImovel().getLigacaoAguaSituacao().getId().intValue()!=5){
				sessao.removeAttribute("osEncontrada");
				sessao.removeAttribute("ordemServico");
				throw new ActionServletException("atencao.agua_diferente_cortado");
				
			}else if (ordemServico != null) {
				
				sessao.setAttribute("ordemServico", ordemServico);
				
				form.setVeioEncerrarOS(""+veioEncerrarOS);
				// OS
				form.setIdOrdemServico(ordemServico.getId()+"");
				form.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
				
				String calculaValores = httpServletRequest.getParameter("calculaValores");
				
				if(calculaValores == null){
				// Preencher dados do imovel
				this.preencherDadosImovel(form, ordemServico);
				
				
				// Preencher dados do Corte da Ligação
				this.pesquisarDadosCorteLigacao(sessao, form, ordemServico);
				}
				
				//Preencher dados da geração do debito se houver
				if (ordemServico.getServicoTipo().getDebitoTipo() != null) {
					form.setIdTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getId()+"");
					form.setDescricaoTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getDescricao()+"");
					sessao.setAttribute("debitoEncontrado", "true");
					form.setDebitoEncontrado("true");
				}else{
					form.setDebitoEncontrado("false");
					form.setIdTipoDebito("");
					form.setDescricaoTipoDebito("");
					sessao.removeAttribute("debitoEncontrado");
				}
				
				
				
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
						
						valorDebito = new BigDecimal(form.getValorDebito().toString().replace(",","."));
						
						String percentualCobranca = form.getPercentualCobranca().toString();
						
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
				
			}
			}else{
				sessao.removeAttribute("osEncontrada");
				sessao.removeAttribute("ordemServico");
				form.setNomeOrdemServico("Ordem de Serviço inexistente");
				form.setIdOrdemServico("");
			}
			form.setDataConcorrencia(new Date());
			sessao.setAttribute("osEncontrada", "true");
		}
		return retorno;
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
	private void pesquisarDadosCorteLigacao(HttpSession sessao, AlterarTipoCorteActionForm form, OrdemServico ordemServico) {
		//Data Encerramento
		if(ordemServico.getDataEncerramento() != null && !ordemServico.getDataEncerramento().equals("")){
		 form.setDataCorte(Util.formatarData(ordemServico.getDataEncerramento()));
		}
		//Motivo do Corte
		if(ordemServico.getImovel().getLigacaoAgua().getMotivoCorte() != null){
			form.setMotivoCorte(""+ordemServico.getImovel().getLigacaoAgua().getMotivoCorte().getDescricao());
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
	private void preencherDadosImovel(AlterarTipoCorteActionForm form, OrdemServico ordemServico) {
		
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
	private void pesquisarCliente(AlterarTipoCorteActionForm form, OrdemServico ordemServico) {
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
