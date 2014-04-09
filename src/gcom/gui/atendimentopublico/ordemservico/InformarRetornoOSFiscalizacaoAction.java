package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.bean.DadosAutoInfracaoRetornoOrdemFiscalizacaoHelper;
import gcom.atendimentopublico.ordemservico.bean.SituacaoEncontradaHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Sávio Luiz
 * @date 13/11/2006
 */
public class InformarRetornoOSFiscalizacaoAction extends GcomAction {
	/**
	 * 
	 * [UC0448] Informar Retorno Ordem de Fiscalização
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InformarRetornoOSFiscalizacaoActionForm informarRetornoOSFiscalizacaoActionForm = 
			(InformarRetornoOSFiscalizacaoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		String idOS = informarRetornoOSFiscalizacaoActionForm.getIdOrdemServico();
		
		String nomeOS = informarRetornoOSFiscalizacaoActionForm.getNomeOrdemServico();
		
		Integer idCobrancaDocumento = null;
		
		// parte que valida o enter
		if ((idOS != null && !idOS.trim().equals(""))
				&& (nomeOS == null || nomeOS.equals(""))) {

			// [FS0001 - Validar Ordem de Serviço]
			this.getFachada().validarOrdemServico(Util.converterStringParaInteger(idOS));

			/*
			 * Colocado por Raphael Rossiter em 26/01/2007 Verifica se a OS já
			 * foi fiscalizada
			 */
			this.getFachada().verificarOSJaFiscalizada(new Integer(idOS));

			sessao.setAttribute("idOS", idOS);

			Object[] parmsOS = this.getFachada().pesquisarParmsOS(Util
					.converterStringParaInteger(idOS));

			if (parmsOS != null) {

				String nomeOSPesquisar = "";
				Integer idImovel = null;
				String descricaoSituacaoAgua = "";
				String descricaoSituacaoEsgoto = "";
				String ocorrencia = "";
				

				if (parmsOS[0] != null) {
					nomeOSPesquisar = (String) parmsOS[0];
				}
				if (parmsOS[1] != null) {
					idImovel = (Integer) parmsOS[1];
				}
				if (parmsOS[2] != null) {
					descricaoSituacaoAgua = (String) parmsOS[2];
				}
				if (parmsOS[3] != null) {
					descricaoSituacaoEsgoto = (String) parmsOS[3];
				}
				if (parmsOS[4] != null) {
					ocorrencia = (String) parmsOS[4];
				}
				if (parmsOS[9] != null) {
					idCobrancaDocumento = (Integer) parmsOS[9];
				}
				informarRetornoOSFiscalizacaoActionForm.setNomeOrdemServico(nomeOSPesquisar);
				informarRetornoOSFiscalizacaoActionForm.setMatriculaImovel("" + idImovel);

				// Inscrição Imóvel
				String inscricaoImovel = this.getFachada().pesquisarInscricaoImovel(idImovel);
				informarRetornoOSFiscalizacaoActionForm.setInscricaoImovel(inscricaoImovel);

				// Situação da Ligação de Agua
				informarRetornoOSFiscalizacaoActionForm.setSituacaoLigacaoAgua(descricaoSituacaoAgua);

				// Situação da Ligação de Esgoto
				informarRetornoOSFiscalizacaoActionForm.setSituacaoLigacaoEsgoto(descricaoSituacaoEsgoto);

				// Cliente Usuário
				this.pesquisarCliente(informarRetornoOSFiscalizacaoActionForm);

				// ocorrencia
				informarRetornoOSFiscalizacaoActionForm.setOcorrencia(ocorrencia);

			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Ordem de Serviço inexistente");
			}

		} else {
			if (sessao.getAttribute("ordemServico") != null){
				OrdemServico ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");
				if (ordemServico != null && ordemServico.getCobrancaDocumento() != null){
					idCobrancaDocumento = ordemServico.getCobrancaDocumento().getId();	
				}				
			}
		}
		
		Collection colecaoFiscalizacaoSituacaoSelecionada =	(Collection)
		sessao.getAttribute("colecaoFiscalizacaoSituacaoSelecionada");
		
		if(colecaoFiscalizacaoSituacaoSelecionada == null ||
				colecaoFiscalizacaoSituacaoSelecionada.isEmpty()){
			 throw new ActionServletException("atencao.campo_selecionado.obrigatorio",
 					null,"Situação da Fiscalização Selecionada");	
		}
		
		
		// LigacaoAguaSituacao do Imóvel
		Integer idLigacaoAguaSituacaoImovel = this.getFachada()
				.pesquisarIdLigacaoAguaSituacao(Util
						.converterStringParaInteger(informarRetornoOSFiscalizacaoActionForm
								.getMatriculaImovel()));

		// LigacaoEsgotoSituacao do Imóvel
		Integer idLigacaoEsgotoSituacaoImovel = this.getFachada()
				.pesquisarIdLigacaoEsgotoSituacao(Util
						.converterStringParaInteger(informarRetornoOSFiscalizacaoActionForm
								.getMatriculaImovel()));

		// Usuário logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// Colocado por Raphael Rossiter em 03/03/2007
		if (informarRetornoOSFiscalizacaoActionForm.getIndicadorGeracaoDebito() == null
				|| informarRetornoOSFiscalizacaoActionForm
						.getIndicadorGeracaoDebito().equalsIgnoreCase("")) {

			informarRetornoOSFiscalizacaoActionForm
					.setIndicadorGeracaoDebito("1");
		}
		
		
		//Carregando os dados do auto de infração - Raphael Rossiter em 05/09/2008
		DadosAutoInfracaoRetornoOrdemFiscalizacaoHelper dadosAutoInfracao = carregarDadosAutoInfracao(
		informarRetornoOSFiscalizacaoActionForm,colecaoFiscalizacaoSituacaoSelecionada);
		
		//Validando os dados do auto de infração - Raphael Rossiter em 05/09/2008
		this.getFachada().validarDadosAutoInfracaoRetornoOSFiscalizacao(dadosAutoInfracao, 
		new Short(informarRetornoOSFiscalizacaoActionForm.getIndicadorDocumentoEntregue()));
		
		//Validando o Motivo de Encerramento da Ordem de Serviço
		if (!informarRetornoOSFiscalizacaoActionForm.getIndicadorEncerramentoOS().equals("2") &&
			(informarRetornoOSFiscalizacaoActionForm.getAtendimentoMotivoEncerramento() == null ||
			informarRetornoOSFiscalizacaoActionForm.getAtendimentoMotivoEncerramento().equalsIgnoreCase("-1"))) {
			
			throw new ActionServletException("atencao.Informe_entidade", null,
			"Motivo Encerramento Ordem Serviço");
		}
		
		LigacaoEsgoto ligacaoEsgoto = null;
		if(informarRetornoOSFiscalizacaoActionForm.getSituacaoLigacao() != null &&
			informarRetornoOSFiscalizacaoActionForm.getSituacaoLigacao().equals("3")){
			ligacaoEsgoto = this.setDadosLigacaoEsgoto(informarRetornoOSFiscalizacaoActionForm);
		}
		
		 Map indicadores = validarSituacaoLigacaoAguaEsgoto(
				 informarRetornoOSFiscalizacaoActionForm,
				 retorno, httpServletRequest, actionMapping);
		 retorno = (ActionForward)indicadores.get("retorno");
//		 Short indicadorAtualizacaoSituacaoLigacaoAgua = (Short)indicadores.get("indicadorAtualizacaoSituacaoLigacaoAgua");
//		 Short indicadorAtualizacaoSituacaoLigacaoEsgoto = (Short)indicadores.get("indicadorAtualizacaoSituacaoLigacaoEsgoto");
		 Short confirmaAtualizacaoSituacaoLigacaoAgua = (Short)indicadores.get("confirmaAtualizacaoSituacaoLigacaoAgua");
		 Short confirmaAtualizacaoSituacaoLigacaoEsgoto = (Short)indicadores.get("confirmaAtualizacaoSituacaoLigacaoEsgoto");
		 
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			
			// [UC0488] Informar Retorno Ordem de Fiscalização
			Integer[] idsGerados = this.getFachada().informarRetornoOSFiscalizacao(
				Util.converterStringParaInteger(informarRetornoOSFiscalizacaoActionForm.getIdOrdemServico()),
				informarRetornoOSFiscalizacaoActionForm.getIndicadorDocumentoEntregue(),
				idLigacaoAguaSituacaoImovel, 
				idLigacaoEsgotoSituacaoImovel,
				Util.converterStringParaInteger(informarRetornoOSFiscalizacaoActionForm.getMatriculaImovel()),
				informarRetornoOSFiscalizacaoActionForm.getIndicadorTipoMedicao(),
				informarRetornoOSFiscalizacaoActionForm.getIndicadorGeracaoDebito(), 
				idCobrancaDocumento, 
				usuario,
				dadosAutoInfracao,
				confirmaAtualizacaoSituacaoLigacaoAgua,
				confirmaAtualizacaoSituacaoLigacaoEsgoto,
				colecaoFiscalizacaoSituacaoSelecionada,
				ligacaoEsgoto,
				informarRetornoOSFiscalizacaoActionForm.getSituacaoLigacao());
			

			String msgFinal = "Ordem de Serviço de código " + idsGerados[0]	+ " atualizada com sucesso.";

			if (idsGerados[1] != null || idsGerados[2] != null) {

				// Registro de Atendimento gerado
				if (idsGerados[1] != null) {
					msgFinal = msgFinal
							+ " Registro(s) de atendimento(s) com código(s) "
							+ idsGerados[1];

					if (idsGerados[2] != null) {
						msgFinal = msgFinal + ", " + idsGerados[2];
					}
				} else {
					msgFinal = msgFinal
							+ " Registro(s) de atendimento(s) com código(s) "
							+ idsGerados[2];
				}

				if (idsGerados[3] != null || idsGerados[4] != null) {

					// Ordem de Serviço
					if (idsGerados[3] != null) {
						msgFinal = msgFinal
								+ " e ordem(ns) de serviço(s) com código(s) "
								+ idsGerados[3];

						if (idsGerados[4] != null) {
							msgFinal = msgFinal + ", " + idsGerados[4];
						}
					} else {
						msgFinal = msgFinal
								+ " e ordem(ns) de serviço(s) com código(s) "
								+ idsGerados[4];
					}
				}

				msgFinal = msgFinal + " gerado(s) com sucesso.";
			}

			sessao.setAttribute("msgFinal", msgFinal);
			sessao.setAttribute("idOS", idOS);

			/*
			 * retorno = montarPaginaConfirmacaoWizard(
			 * "atencao.deseja_encerrar_os", httpServletRequest, actionMapping, "");
			 */

			if (!informarRetornoOSFiscalizacaoActionForm
					.getIndicadorEncerramentoOS().equals("2")) {

				// String idOS = (String) sessao.getAttribute("idOS");

				Integer numeroOS = new Integer(idOS);
				Date dataAtual = new Date();
				if (informarRetornoOSFiscalizacaoActionForm.getAtendimentoMotivoEncerramento() != null) {
					
					if (!informarRetornoOSFiscalizacaoActionForm.getAtendimentoMotivoEncerramento().equalsIgnoreCase("-1")) {

						String indicadorExecucaoSim = 
							informarRetornoOSFiscalizacaoActionForm.getAtendimentoMotivoEncerramento();
						
						String parecerEncerramento = 
							"ORDEM DE SERVICO ENCERRADA ATRAVES DA FUNCIONALIDADE DE FISCALIZACAO.";
						
						if(informarRetornoOSFiscalizacaoActionForm.getParecerEncerramento() != null &&
							!informarRetornoOSFiscalizacaoActionForm.getParecerEncerramento().equals("")){
							
							parecerEncerramento = informarRetornoOSFiscalizacaoActionForm.getParecerEncerramento();
						}
						

						this.getFachada().encerrarOSComExecucaoSemReferencia(
							numeroOS,
							dataAtual,
							usuario,
							indicadorExecucaoSim,
							dataAtual,
							parecerEncerramento,
							ServicoTipo.INDICADOR_PAVIMENTO_NAO,
							null,
							null,
							null,
							null,
							null,
							ServicoTipo.INDICADOR_VISTORIA_SERVICO_TIPO_NAO,
							null,
							null,
							null,
							null);
					} else {
						throw new ActionServletException(
								"atencao.Informe_entidade", null,
								"Motivo Encerramento Ordem Serviço");
					}
				}
			}	
			
		
			// montando página de sucesso
			montarPaginaSucesso(httpServletRequest, msgFinal,
					"Informar outra fiscalização",
					"/exibirInformarRetornoOSFiscalizacaoAction.do?menu=sim");
		}

		return retorno;
	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Sávio Luiz
	 * @date 13/11/2006
	 */
	private void pesquisarCliente(
			InformarRetornoOSFiscalizacaoActionForm informarRetornoOSFiscalizacaoActionForm) {

		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID,
				informarRetornoOSFiscalizacaoActionForm.getMatriculaImovel()));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
				ClienteRelacaoTipo.USUARIO));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.DATA_FIM_RELACAO));

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection<ClienteImovel> colecaoClienteImovel = Fachada.getInstancia().pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());

		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

			ClienteImovel clienteImovel = colecaoClienteImovel.iterator().next();

			Cliente cliente = clienteImovel.getCliente();

			String documento = "";

			if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				documento = cliente.getCpfFormatado();
			} else {
				documento = cliente.getCnpjFormatado();
			}
			// Cliente Nome/CPF-CNPJ
			informarRetornoOSFiscalizacaoActionForm.setClienteUsuario(cliente
					.getNome());
			informarRetornoOSFiscalizacaoActionForm
					.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}
	}
	
	private DadosAutoInfracaoRetornoOrdemFiscalizacaoHelper carregarDadosAutoInfracao(
			InformarRetornoOSFiscalizacaoActionForm form,Collection colecaoFiscalizacaoSituacaoSelecionada){
        
		DadosAutoInfracaoRetornoOrdemFiscalizacaoHelper retorno = null;
		
		retorno = new DadosAutoInfracaoRetornoOrdemFiscalizacaoHelper();
        
        if (Fachada.getInstancia().pesquisarParametrosDoSistema().getIndicadorControlaAutoInfracao() == 
            ConstantesSistema.SIM.shortValue() && form.getIndicadorDocumentoEntregue() != null && 
            form.getIndicadorDocumentoEntregue().equals(ConstantesSistema.DOCUMENTO_ENTREGUE_AUTO_INFRACAO.toString())){			
        	
    		retorno.setIdResponsavel(new Integer(form.getIdFuncionarioResponsavel()));
    		retorno.setIdAutoInfracaoSituacao(new Integer(form.getIdAutoInfracaoSituacao()));
    		retorno.setQuantidadeParcelas(new Integer(form.getQuantidadeParcelas()));
    			
    		if (form.getDataEmissao() != null){
    			retorno.setDataEmissao(Util.converteStringParaDate(form.getDataEmissao()));
    		}
    			
    		if (form.getDataInicioRecurso() != null){
    			retorno.setDataInicioRecurso(Util.converteStringParaDate(form.getDataInicioRecurso()));
    		}
    			
    		if (form.getDataTerminoRecurso() != null){
    			retorno.setDataTerminoRecurso(Util.converteStringParaDate(form.getDataTerminoRecurso()));
    		}
    		
    		
    		String indicadorGerarDebito = null;
    		Iterator iteratorFiscalizacaoSituacaoSelecionada = colecaoFiscalizacaoSituacaoSelecionada.iterator();
    		while (iteratorFiscalizacaoSituacaoSelecionada.hasNext()) {
				SituacaoEncontradaHelper situacaoEncontradaHelper = (SituacaoEncontradaHelper) iteratorFiscalizacaoSituacaoSelecionada.next();
				Short indicadorAtualizacaoAutosInfracao = situacaoEncontradaHelper.getFiscalizacaoSituacao().getIndicadorAtualizacaoAutosInfracao();
				
				if(situacaoEncontradaHelper.getGeracaoDebito() == 1 && 
					indicadorAtualizacaoAutosInfracao != null && 
					indicadorAtualizacaoAutosInfracao.equals(ConstantesSistema.SIM)){
					
					Short indicadorDebito = Fachada.getInstancia().recuperaIndicadorDebitoDaOrdemServicoFiscSit(
						new Integer(form.getIdOrdemServico()), situacaoEncontradaHelper.getFiscalizacaoSituacao().getId());
					
					if(indicadorDebito != null && indicadorDebito.equals(ConstantesSistema.SIM)){
						indicadorGerarDebito = "1";
					}
				}
			}
//    		retorno.setIndicadorGerarDebito(indicadorGerarDebito);
    		
    		if(indicadorGerarDebito == null){
    			retorno.setIndicadorGerarDebito(form.getIndicadorGeracaoDebito());
    		}else{
    			retorno.setIndicadorGerarDebito(indicadorGerarDebito);
    		}
    			
    		retorno.setObservacao(form.getObservacao());
        }
		
		return retorno;
	}

	private Map validarSituacaoLigacaoAguaEsgoto(InformarRetornoOSFiscalizacaoActionForm form,
			ActionForward retorno,HttpServletRequest httpServletRequest,ActionMapping actionMapping){
		
		OrdemServico ordemServico = this.getFachada().recuperaOrdemServico(
				new Integer(form.getIdOrdemServico()));
		
//		Short indicadorAtualizacaoSituacaoLigacaoAgua = ordemServico.getIndicadorAtualizaAgua();
//		Short indicadorAtualizacaoSituacaoLigacaoEsgoto = ordemServico.getIndicadorAtualizaEsgoto();
		boolean paginaConfirmacaoAgua = false;
		boolean paginaConfirmacaoEsgoto = false;
		String confirmado = httpServletRequest.getParameter("confirmado");
		
		Short confirmaAtualizacaoSituacaoLigacaoAgua = null;
		Short confirmaAtualizacaoSituacaoLigacaoEsgoto = null;
		
		if(form.getIndicadorAguaSituacao() != null &&
			form.getIndicadorAguaSituacao().equals("1") && 
			(ordemServico.getIndicadorAtualizaAgua() == null || 
			 ordemServico.getIndicadorAtualizaAgua().equals(ConstantesSistema.NAO))){
			//6.1. Caso seja necessário validar a situação da ligação de água do imóvel fiscalizado
			//E caso a atualização da situação da ligação de água do imóvel ainda não tenha sido realizada 
			
//			if(form.getIndicadorAtualizarSituacaoLigacaoAguaEsgoto().equals("2")){
//				//6.1.1. Caso a opção de atualizar a situação da ligação de 
//				//água/esgoto do imóvel fiscalizado corresponda a “Não”
//				
//				indicadorAtualizacaoSituacaoLigacaoAgua = ConstantesSistema.NAO;
//				
//			}else 
				if (form.getIndicadorAtualizarSituacaoLigacaoAguaEsgoto().equals("1")) {
				
				if(confirmado == null){
					paginaConfirmacaoAgua = true;
//					indicadorAtualizacaoSituacaoLigacaoAgua = ConstantesSistema.NAO;
					
				}else if(confirmado.equals("ok")){
					// Caso o usuário confirme
//					indicadorAtualizacaoSituacaoLigacaoAgua = ConstantesSistema.SIM;
					confirmaAtualizacaoSituacaoLigacaoAgua = ConstantesSistema.SIM;
					
				}else if(confirmado.equals("cancelar")){
					confirmaAtualizacaoSituacaoLigacaoAgua = ConstantesSistema.NAO;
				}
			}
		}
		
		
		if(form.getIndicadorEsgotoSituacao() != null &&
				form.getIndicadorEsgotoSituacao().equals("1") && 
				(ordemServico.getIndicadorAtualizaEsgoto() == null ||
					ordemServico.getIndicadorAtualizaEsgoto().equals(ConstantesSistema.NAO))){
			//6.2.	Caso seja necessário validar a situação da ligação de esgoto do imóvel fiscalizado
			//E caso a atualização da situação da ligação de esgoto do imóvel ainda não tenha sido realizada 
			
//			if(form.getIndicadorAtualizarSituacaoLigacaoAguaEsgoto().equals("2")){
//				//6.2.1. Caso a opção de atualizar a situação da ligação de 
//				//água/esgoto do imóvel fiscalizado corresponda a “Não” 
//				
//				indicadorAtualizacaoSituacaoLigacaoEsgoto = ConstantesSistema.NAO;
//				
//			}else 
				if (form.getIndicadorAtualizarSituacaoLigacaoAguaEsgoto().equals("1")) {
				
				if(confirmado == null){
					paginaConfirmacaoEsgoto = true;
//					indicadorAtualizacaoSituacaoLigacaoEsgoto = ConstantesSistema.NAO;
					
				}else if(confirmado.equals("ok")){
					// Caso o usuário confirme
//					indicadorAtualizacaoSituacaoLigacaoEsgoto = ConstantesSistema.SIM;
					confirmaAtualizacaoSituacaoLigacaoEsgoto = ConstantesSistema.SIM;
					
				}else if(confirmado.equals("cancelar")){
					confirmaAtualizacaoSituacaoLigacaoEsgoto = ConstantesSistema.NAO;	
				}
			}
		}
		
		httpServletRequest.setAttribute("cancelamento", "TRUE");
		httpServletRequest.setAttribute("nomeBotao1", "Sim");
		httpServletRequest.setAttribute("nomeBotao2", "Não");
        httpServletRequest.setAttribute( "tipoRelatorio", "confirma" );
        
		if(paginaConfirmacaoAgua && paginaConfirmacaoEsgoto){
			 retorno = montarPaginaConfirmacao(
					 "atencao.confirma_alteracao_ligacao_agua_esgoto", httpServletRequest, actionMapping, "");
		}else if(paginaConfirmacaoAgua){
			 retorno = montarPaginaConfirmacao(
					 "atencao.confirma_alteracao_ligacao_agua", httpServletRequest, actionMapping, "");
		}else if(paginaConfirmacaoEsgoto){
			 retorno = montarPaginaConfirmacao(
					 "atencao.confirma_alteracao_ligacao_esgoto", httpServletRequest, actionMapping, "");
		}
		
		 Map<String, Object> indicadores = new HashMap<String, Object>();
		 indicadores.put("retorno",retorno);
//		 indicadores.put("indicadorAtualizacaoSituacaoLigacaoAgua",indicadorAtualizacaoSituacaoLigacaoAgua);
//		 indicadores.put("indicadorAtualizacaoSituacaoLigacaoEsgoto",indicadorAtualizacaoSituacaoLigacaoEsgoto);
		 indicadores.put("confirmaAtualizacaoSituacaoLigacaoAgua",confirmaAtualizacaoSituacaoLigacaoAgua);
		 indicadores.put("confirmaAtualizacaoSituacaoLigacaoEsgoto",confirmaAtualizacaoSituacaoLigacaoEsgoto);
		 
		
		return indicadores;
	}
	
	//[SB0008] - Inserir Dados da Ligação de Esgoto
	private LigacaoEsgoto setDadosLigacaoEsgoto(InformarRetornoOSFiscalizacaoActionForm form){

		LigacaoEsgoto ligacaoEsgoto = null;
		
		FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();
		filtroLigacaoEsgoto.adicionarParametro(
			new ParametroSimples(
				FiltroLigacaoEsgoto.ID, 
				new Integer(form.getMatriculaImovel())));
		
		Collection<LigacaoEsgoto> colecaoLigacaoEsgotoBase = Fachada.getInstancia().pesquisar(
				filtroLigacaoEsgoto, LigacaoEsgoto.class.getName());

		if (!Util.isVazioOrNulo(colecaoLigacaoEsgotoBase)) {
			ligacaoEsgoto = (LigacaoEsgoto) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoBase); 
		} else {
			ligacaoEsgoto = new LigacaoEsgoto();
			ligacaoEsgoto.setId(new Integer(form.getMatriculaImovel()));
		}
		
		// Data de Ligacao
		String dataLigacao = form.getDataLigacaoEsgoto();
		if (Util.verificarNaoVazio(dataLigacao)){
			Date data = Util.converteStringParaDate(dataLigacao);
			if(data != null){
				ligacaoEsgoto.setDataLigacao(data);
			}else{
				throw new ActionServletException("atencao.gsan.campo_formato_invalido", "Data de Ligação");
			}
		}else{
			throw new ActionServletException("atencao.informe_campo_obrigatorio",null,"Data da Ligação");
		}
		
		//Indicador Ligação
		String indicadorLigacaoEsgoto = form.getIndicadorLigacaoEsgoto(); 
		if(Util.verificarNaoVazio(indicadorLigacaoEsgoto)){
			ligacaoEsgoto.setIndicadorLigacaoEsgoto(new Short(indicadorLigacaoEsgoto));
		}else{
			throw new ActionServletException("atencao.informe_campo_obrigatorio",null,"Ligação");
		}
		
		// Diâmetro
		if(form.getDiametroLigacaoEsgoto() != null && !form.getDiametroLigacaoEsgoto().equals("")
		&& !form.getDiametroLigacaoEsgoto().equals(""+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
			LigacaoEsgotoDiametro ligacaoEsgotoDiametro = new LigacaoEsgotoDiametro();
			ligacaoEsgotoDiametro.setId(new Integer(form.getDiametroLigacaoEsgoto()));
			ligacaoEsgoto.setLigacaoEsgotoDiametro(ligacaoEsgotoDiametro);
		}else{
			throw new ActionServletException("atencao.informe_campo_obrigatorio",null,"Diâmetro da Ligação");
		}
	
		// Material
		if(form.getMaterialLigacaoEsgoto() != null && !form.getMaterialLigacaoEsgoto().equals("")
		&& !form.getMaterialLigacaoEsgoto().equals(""+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
			LigacaoEsgotoMaterial ligacaoEsgotoMaterialMaterial = new LigacaoEsgotoMaterial();
			ligacaoEsgotoMaterialMaterial.setId(new Integer(form.getMaterialLigacaoEsgoto()));
			ligacaoEsgoto.setLigacaoEsgotoMaterial(ligacaoEsgotoMaterialMaterial);
		}else{
			throw new ActionServletException("atencao.informe_campo_obrigatorio",null,"Material da Ligação");
		}

		// Perfil
		if(form.getPerfilLigacaoEsgoto() != null && !form.getPerfilLigacaoEsgoto().equals("")
		&& !form.getPerfilLigacaoEsgoto().equals(""+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
			LigacaoEsgotoPerfil ligacaoEsgotoPerfil = new LigacaoEsgotoPerfil();
			ligacaoEsgotoPerfil.setId(new Integer(form.getPerfilLigacaoEsgoto()));
			ligacaoEsgoto.setLigacaoEsgotoPerfil(ligacaoEsgotoPerfil);
			
			//percentual de esgoto
			FiltroLigacaoEsgotoPerfil filtroLigacaoPercentualEsgoto = new FiltroLigacaoEsgotoPerfil();
			filtroLigacaoPercentualEsgoto.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoPerfil.ID, form.getPerfilLigacaoEsgoto()));
			Collection<LigacaoEsgotoPerfil> colecaoPercentualEsgoto = this.getFachada().pesquisar(
					filtroLigacaoPercentualEsgoto, LigacaoEsgotoPerfil.class.getName());
			if (colecaoPercentualEsgoto != null && !colecaoPercentualEsgoto.isEmpty()) {
				LigacaoEsgotoPerfil percentualEsgotoPerfil = (LigacaoEsgotoPerfil) colecaoPercentualEsgoto.iterator().next();
				ligacaoEsgoto.setPercentual(percentualEsgotoPerfil.getPercentualEsgotoConsumidaColetada());
			}
		}else{
			throw new ActionServletException(
					"atencao.informe_campo_obrigatorio",null,"Perfil da Ligação");
		}
		
		//Percentual coleta
		String percentualColeta = form.getPercentualColeta();
		if (Util.verificarNaoVazio(percentualColeta)) {
			try{
				BigDecimal percentualInformadoColeta = new BigDecimal(percentualColeta.replace(",", "."));
				if (percentualInformadoColeta.compareTo(new BigDecimal(ConstantesSistema.NUMERO_MAXIMO_CONSUMO_MINIMO_FIXADO)) < 0){
					ligacaoEsgoto.setPercentualAguaConsumidaColetada(percentualInformadoColeta);
				}else{
					throw new ActionServletException("atencao.atualizar_percentual_informado_maior_maximo_permitido");
				}
			}catch (NumberFormatException e) {
				throw new ActionServletException("atencao.gsan.campo_formato_invalido", "Percentual Coleta");
			}
			
		} else {
			throw new ActionServletException("atencao.informe_campo_obrigatorio", null,"Percentual de Coleta");
		}
		Imovel imovel = new Imovel(new Integer(form.getMatriculaImovel()));
		//ligacaoEsgoto.setImovel(imovel);
		ligacaoEsgoto.setIndicadorCaixaGordura(ConstantesSistema.NAO);
		ligacaoEsgoto.setUltimaAlteracao(new Date());

		return ligacaoEsgoto;
	}		
}
