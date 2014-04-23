package gcom.gui.atendimentopublico.hidrometro;

import gcom.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroPocoTipo;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PocoTipo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroProtecao;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.medicao.MedicaoTipo;
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
 * < <Descrição da Classe>>
 * 
 * @author Ana Maria
 *  @date 30/06/2006
 */
public class ExibirEfetuarInstalacaoHidrometroAction extends GcomAction {
	/**
	 * Este caso de uso permite efetuar instalação de hidrômetro, sendo chamado pela funcionalidade que encerra 
	 * a execução da ordem de serviço ou chamada diretamente do Menu.
	 * 
	 * [UC0362] Efetuar Instalação de Hidrômetro
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("efetuarInstalacaoHidrometro");
		EfetuarInstalacaoHidrometroActionForm efetuarInstalacaoHidrometroActionForm = 
			(EfetuarInstalacaoHidrometroActionForm) actionForm;
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
/*		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");		
		String tipoConsulta = httpServletRequest.getParameter("tipoConsulta");*/
		
		Fachada fachada = Fachada.getInstancia();
		
		Boolean veioEncerrarOS = null;
		
		if (httpServletRequest.getAttribute("veioEncerrarOS") != null ||
			(efetuarInstalacaoHidrometroActionForm.getVeioEncerrarOS() != null &&
			efetuarInstalacaoHidrometroActionForm.getVeioEncerrarOS().equalsIgnoreCase("true"))) {
				
			veioEncerrarOS = Boolean.TRUE;
		} else {
			veioEncerrarOS = Boolean.FALSE;
		}
		
        
        // Colocado por Vivianne Sousa em 05/12/2007
        // ------------------------------------------------------------
		if(efetuarInstalacaoHidrometroActionForm.getIndicadorTrocaProtecao() == null){
            efetuarInstalacaoHidrometroActionForm.setIndicadorTrocaProtecao(ConstantesSistema.NAO.toString());
        }
        
        if(efetuarInstalacaoHidrometroActionForm.getIndicadorTrocaRegistro() == null){
            efetuarInstalacaoHidrometroActionForm.setIndicadorTrocaRegistro(ConstantesSistema.NAO.toString());
        }
        // ------------------------------------------------------------
        
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		this.pesquisarSelectObrigatorio(httpServletRequest);	
		
		this.pesquisarTipoPoco(httpServletRequest);
		
		String idOrdemServico = null;
		Imovel imovelComLocalidade = null;
		
		if(efetuarInstalacaoHidrometroActionForm.getIdOrdemServico() != null){
			idOrdemServico = efetuarInstalacaoHidrometroActionForm.getIdOrdemServico();
		}else{
			idOrdemServico = (String)httpServletRequest.getAttribute("veioEncerrarOS");
			efetuarInstalacaoHidrometroActionForm.setDataInstalacao((String) httpServletRequest
					.getAttribute("dataEncerramento"));
			sessao.setAttribute("caminhoRetornoIntegracaoComercial",httpServletRequest
					.getAttribute("caminhoRetornoIntegracaoComercial"));
		}
		
		if(httpServletRequest.getAttribute("semMenu") != null || sessao.getAttribute("semMenu")!=null){
			sessao.setAttribute("semMenu", "SIM");
		}else{
			sessao.removeAttribute("semMenu");
		}
		/* Alteração feita por Arthur Acarvalho
		 * Serve para tirar o menu da funcionalidade quando a mesma é chamada a partir
		 * do encerrar OS (Manter RA - encerrar OS).
		 * DATA: 24/03/2010
		 */
		if(httpServletRequest.getAttribute("veioEncerrarOSFuncManterRA") != null || sessao.getAttribute("semMenu")!=null){
			sessao.setAttribute("semMenu", "SIM");
		}else{
			sessao.removeAttribute("semMenu");
		}
		
		
		OrdemServico ordemServico = null;
		String matriculaImovel = null;
		
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {			
			ordemServico = 
				fachada.recuperaOSPorId(new Integer(idOrdemServico));

			if (ordemServico != null) {
				
				httpServletRequest.setAttribute("ordemServicoEncontrado", "true");
				efetuarInstalacaoHidrometroActionForm.setUsuarioNaoEncontrado( "false" );
				
				fachada.validarExibirInstalacaoHidrometro(ordemServico,veioEncerrarOS);

				sessao.setAttribute("ordemServico", ordemServico);
				
				efetuarInstalacaoHidrometroActionForm.setIdOrdemServico(""+ordemServico.getId());

				efetuarInstalacaoHidrometroActionForm.setVeioEncerrarOS(""+veioEncerrarOS);
				efetuarInstalacaoHidrometroActionForm.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());

                Imovel imovel = null;
                
                if(ordemServico.getImovel() != null){
                    imovel = ordemServico.getImovel();
                    
                    FiltroImovel filtroImovel = new FiltroImovel();
					filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovel.getId()));
					
					Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
					
					imovelComLocalidade = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
					
					if (imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem() != null) {
						efetuarInstalacaoHidrometroActionForm
								.setIdLocalArmazenagem(imovelComLocalidade
										.getLocalidade()
										.getHidrometroLocalArmazenagem()
										.getId().toString());
					}
					
                }else{
                    imovel = ordemServico.getRegistroAtendimento()
                    .getImovel();
                }
												
				// Matricula Imóvel
				matriculaImovel = imovel.getId().toString();
				efetuarInstalacaoHidrometroActionForm.setMatriculaImovel(matriculaImovel);

				// Inscrição Imóvel
				String inscricaoImovel = fachada.pesquisarInscricaoImovel(imovel.getId());
				efetuarInstalacaoHidrometroActionForm.setInscricaoImovel(inscricaoImovel);

				// Situação da Ligação de Agua
				String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
				efetuarInstalacaoHidrometroActionForm.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

				// Situação da Ligação de Esgoto
				String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
				efetuarInstalacaoHidrometroActionForm.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

				// Cliente Usuário
				this.pesquisarCliente(efetuarInstalacaoHidrometroActionForm);
				
				// Tipo medição - Ligação Água
				if (ordemServico.getRegistroAtendimento() == null ||
                        ordemServico.getRegistroAtendimento().getSolicitacaoTipoEspecificacao().getIndicadorLigacaoAgua().
						equals(MedicaoTipo.LIGACAO_AGUA.shortValue())) {		
					efetuarInstalacaoHidrometroActionForm.setTipoMedicao(MedicaoTipo.LIGACAO_AGUA.toString());

				// Tipo medição- Poço
				} else {										
					efetuarInstalacaoHidrometroActionForm.setTipoMedicao(MedicaoTipo.POCO.toString());
					httpServletRequest.setAttribute("medicaoTipoPoco", "true");
				}	
				
				//Data recibida da execução da OS
				Date dataInstalacao = ordemServico.getDataEncerramento();
				if(dataInstalacao != null && !dataInstalacao.equals("")){
				 efetuarInstalacaoHidrometroActionForm.setDataInstalacao(Util.formatarData(dataInstalacao));
				}
				
				// Tipo Débito
				if (ordemServico.getServicoTipo().getDebitoTipo() != null) {
					efetuarInstalacaoHidrometroActionForm.setIdTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getId()+"");
					efetuarInstalacaoHidrometroActionForm.setDescricaoTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getDescricao()+"");
				}else{
					efetuarInstalacaoHidrometroActionForm.setIdTipoDebito("");
					efetuarInstalacaoHidrometroActionForm.setDescricaoTipoDebito("");
				}
				
				
				//[FS0013] - Alteração de Valor
				this.permitirAlteracaoValor(ordemServico.getServicoTipo(), efetuarInstalacaoHidrometroActionForm);
				
				//Colocado por Raphael Rossiter em 04/05/2007 (Analista: Rosana Carvalho)
				BigDecimal valorDebito = this.calcularValores(httpServletRequest, ordemServico, 
				efetuarInstalacaoHidrometroActionForm);
				
				
				SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
				efetuarInstalacaoHidrometroActionForm.setQtdeMaxParcelas(sistemaParametro.getNumeroMaximoParcelasFinanciamento()+"");
				
				// -----------------------------------------------------------
				// Verificar permissão especial
				boolean temPermissaoMotivoNaoCobranca = fachada.verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
				// -----------------------------------------------------------
				
				if (temPermissaoMotivoNaoCobranca) {
					httpServletRequest.setAttribute("permissaoMotivoNaoCobranca", temPermissaoMotivoNaoCobranca);
				}else{
					efetuarInstalacaoHidrometroActionForm.setPercentualCobranca("100");
					efetuarInstalacaoHidrometroActionForm.setQuantidadeParcelas("1");
					efetuarInstalacaoHidrometroActionForm.setValorParcelas(Util.formataBigDecimal(valorDebito,2,true));
				}
			}else{
				httpServletRequest.setAttribute("ordemServicoEncontrado", "exception");
				efetuarInstalacaoHidrometroActionForm.setNomeOrdemServico("Ordem de Serviço inexistente");
				efetuarInstalacaoHidrometroActionForm.setIdOrdemServico("");
			}
		} else {
			httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");

			efetuarInstalacaoHidrometroActionForm.setIdOrdemServico("");
			efetuarInstalacaoHidrometroActionForm.setMatriculaImovel("");
			efetuarInstalacaoHidrometroActionForm.setInscricaoImovel("");
			efetuarInstalacaoHidrometroActionForm.setClienteUsuario("");
			efetuarInstalacaoHidrometroActionForm.setCpfCnpjCliente("");
			efetuarInstalacaoHidrometroActionForm.setSituacaoLigacaoAgua("");
			efetuarInstalacaoHidrometroActionForm.setSituacaoLigacaoEsgoto("");
			efetuarInstalacaoHidrometroActionForm.setNomeOrdemServico("");
			efetuarInstalacaoHidrometroActionForm.setIdTipoDebito("");
			efetuarInstalacaoHidrometroActionForm.setDescricaoTipoDebito("");
		}

		String numeroHidrometro = efetuarInstalacaoHidrometroActionForm.getNumeroHidrometro();
		
		if(numeroHidrometro==null 
				&& httpServletRequest.getParameter("idCampoEnviarDados")!=null
				&& !httpServletRequest.getParameter("idCampoEnviarDados").equals("")){
			numeroHidrometro = httpServletRequest.getParameter("idCampoEnviarDados");
		}
		
		// Verificar se o número do hidrômetro não está cadastrado
		if (numeroHidrometro != null && !numeroHidrometro.trim().equals("")) {

			// Filtro para descobrir id do Hidrometro
			FiltroHidrometro filtroHidrometro = new FiltroHidrometro();

			filtroHidrometro.adicionarParametro(new ParametroSimples(
				FiltroHidrometro.NUMERO_HIDROMETRO,numeroHidrometro));
			
			filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");			

			Collection colecaoHidrometro = fachada.pesquisar(filtroHidrometro,Hidrometro.class.getName());
	
			if (colecaoHidrometro == null || colecaoHidrometro.isEmpty()) {
				efetuarInstalacaoHidrometroActionForm.setNumeroHidrometro("");
				throw new ActionServletException("atencao.hidrometro_inexistente");
			}else{
				Hidrometro hidro = (Hidrometro) Util.retonarObjetoDeColecao(colecaoHidrometro);
				
				if (imovelComLocalidade != null && imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem() != null &&
						!hidro.getHidrometroLocalArmazenagem().getId().equals(imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem().getId())) {
						throw new ActionServletException("atencao.hidrometro_local_armazenagem_imovel_diferente_hidrometro_local_armazenagem_hidrometro");
				}
				
				efetuarInstalacaoHidrometroActionForm.setNumeroHidrometro(hidro.getNumero());
				
				if(ordemServico != null){

					//Colocado por Raphael Rossiter em 04/05/2007 (Analista: Rosana Carvalho)
					BigDecimal valorDebito = this.calcularValores(httpServletRequest, ordemServico, 
					efetuarInstalacaoHidrometroActionForm);
					
					if (valorDebito != null) {
						efetuarInstalacaoHidrometroActionForm.setValorDebito(Util.formataBigDecimal(valorDebito,2,true));
					} else {
						efetuarInstalacaoHidrometroActionForm.setValorDebito("0");
					}
				}
				
			}
		}
		// Seta coleção de motivo de não cobrança
		getMotivoNaoCobrancaCollection(sessao);
		
		return retorno;
	}
	
	
	/*
	 * [FS0013 - Alteração de Valor]
	 * 
	 * autor: Raphael Rossiter
	 * data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo, EfetuarInstalacaoHidrometroActionForm form){
		
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
			EfetuarInstalacaoHidrometroActionForm form){
		
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
			
        }else if(ordemServico.getRegistroAtendimento() != null){
			

        	
        	if ( form.getNumeroHidrometro() != null && !form.getNumeroHidrometro().equals("") ) {
        		
				HidrometroCapacidade hidrometroCapacidade = 
					Fachada.getInstancia().pesquisarCapacidadeHidrometro(form.getNumeroHidrometro());
			
				valorDebito = Fachada.getInstancia().obterValorDebito(
						ordemServico.getServicoTipo().getId(),
						ordemServico.getRegistroAtendimento().getImovel().getId(),
						hidrometroCapacidade);
			} else {
				valorDebito = Fachada.getInstancia().obterValorDebito(
						ordemServico.getServicoTipo().getId(),
						ordemServico.getRegistroAtendimento().getImovel().getId(),
						new Short(LigacaoTipo.LIGACAO_AGUA + ""));
			}
        	
//        	valorDebito = Fachada.getInstancia().obterValorDebito(ordemServico.getServicoTipo().getId(), 
//			ordemServico.getRegistroAtendimento().getImovel().getId(), new Short("3"));
			
			if (valorDebito != null) {
				form.setValorDebito(Util.formataBigDecimal(valorDebito,2,true));
			} else {
				form.setValorDebito("0");
			}
		}
		
		
		return valorDebito;
	}

	/**
	 * Pesquisa o local de instalação
	 * Pesquisa hidrometro proteção
	 * */	
	private void pesquisarSelectObrigatorio(HttpServletRequest httpServletRequest){

		//Pesquisando local de instalação
		FiltroHidrometroLocalInstalacao filtroHidrometroLocalInstalacao = new FiltroHidrometroLocalInstalacao();
		filtroHidrometroLocalInstalacao.setCampoOrderBy(FiltroHidrometroLocalInstalacao.DESCRICAO);
		filtroHidrometroLocalInstalacao.adicionarParametro(
			new ParametroSimples(
				FiltroHidrometroLocalInstalacao.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
	    
		Collection colecaoHidrometroLocalInstalacao = 
	    	Fachada.getInstancia().pesquisar(filtroHidrometroLocalInstalacao, 
	    		HidrometroLocalInstalacao.class.getName());       
		
		 httpServletRequest.setAttribute("colecaoHidrometroLocalInstalacao", colecaoHidrometroLocalInstalacao);
		 
	    //Pesquisando proteção
		FiltroHidrometroProtecao filtroHidrometroProtecao = new FiltroHidrometroProtecao();
		filtroHidrometroProtecao.setCampoOrderBy(FiltroHidrometroProtecao.DESCRICAO);
		filtroHidrometroProtecao.adicionarParametro(new ParametroSimples(FiltroHidrometroProtecao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));	
	    Collection colecaoHidrometroProtecao = Fachada.getInstancia().pesquisar(filtroHidrometroProtecao, HidrometroProtecao.class.getName());       
			
		httpServletRequest.setAttribute("colecaoHidrometroProtecao", colecaoHidrometroProtecao);
	}	
	/**
	 * Pesquisa Cliente 
	 *
	 * @author Rafael Pinto
	 * @date 01/09/2006
	 */
	private void pesquisarCliente(EfetuarInstalacaoHidrometroActionForm efetuarInstalacaoHidrometroActionForm) {
		
		//Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(
			new ParametroSimples(FiltroClienteImovel.IMOVEL_ID,
					efetuarInstalacaoHidrometroActionForm.getMatriculaImovel()));

		filtroClienteImovel
			.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,ClienteRelacaoTipo.USUARIO));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection colecaoClienteImovel = 
			Fachada.getInstancia().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());

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
			efetuarInstalacaoHidrometroActionForm.setClienteUsuario(cliente.getNome());
			efetuarInstalacaoHidrometroActionForm.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Cliente");
		}
	}
	
	/**
	 * Carrega coleção de motivo da não cobrança.
	 *
	 * @author Leonardo Regis
	 * @date 16/09/2006
	 *
	 * @param sessao
	 */
	private void getMotivoNaoCobrancaCollection(HttpSession sessao) {
		Fachada fachada = Fachada.getInstancia();
		// Filtra Motivo da Não Cobrança
		FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();
		filtroServicoNaoCobrancaMotivo.setCampoOrderBy(FiltroServicoNaoCobrancaMotivo.DESCRICAO);

		Collection colecaoServicoNaoCobrancaMotivo = fachada.pesquisar( filtroServicoNaoCobrancaMotivo, ServicoNaoCobrancaMotivo.class.getName());
		if (colecaoServicoNaoCobrancaMotivo != null && !colecaoServicoNaoCobrancaMotivo.isEmpty()) {
			sessao.setAttribute("colecaoMotivoNaoCobranca",	colecaoServicoNaoCobrancaMotivo);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Motivo Não Cobrança");
		}
	}
	
	/**
	 * Pesquisa o tipo de poço
	 * */	
	private void pesquisarTipoPoco(HttpServletRequest httpServletRequest){

		//Pesquisando tipo de poço
		FiltroPocoTipo filtroPocoTipo = new FiltroPocoTipo();
		filtroPocoTipo.setCampoOrderBy(FiltroPocoTipo.DESCRICAO);
		filtroPocoTipo.adicionarParametro(
				new ParametroSimples(
						FiltroPocoTipo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroPocoTipo.adicionarParametro(
				new ParametroSimples(
						FiltroPocoTipo.INDICADOR_HIDROMETRO_TIPO_POCO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoTipoPoco =
			Fachada.getInstancia().pesquisar(filtroPocoTipo,
					PocoTipo.class.getName());
		
		 httpServletRequest.setAttribute("colecaoTipoPoco", colecaoTipoPoco);
		
	}
}
