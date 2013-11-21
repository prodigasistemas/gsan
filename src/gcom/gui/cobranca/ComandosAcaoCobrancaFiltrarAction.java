package gcom.gui.cobranca;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoOrdemServicoNaoAceitas;
import gcom.cobranca.CobrancaAcaoOrdemServicoNaoAceitasPK;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaAcaoOrdemServicoNaoAceitas;
import gcom.cobranca.MotivoNaoAceitacaoEncerramentoOS;
import gcom.cobranca.bean.CobrancaAcaoOrdemServicoNaoAceitasHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite consultar comandos de ação de cobrança 
 * [UC1098] Informar Não Aceitação de Motivo de Encerramento Ordem de Serviço
 * @author Mariana Victor
 * @since 13/12/2010
 */
public class ComandosAcaoCobrancaFiltrarAction extends GcomAction{
	
	
	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("retornarComandosAcaoCobranca");
		Fachada fachada = Fachada.getInstancia();
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ComandosAcaoCobrancaFiltrarActionForm comandosAcaoCobrancaFiltrarActionForm = (ComandosAcaoCobrancaFiltrarActionForm)actionForm;
		FiltroOrdemServico filtroOrdemServico;
		List<CobrancaAcaoOrdemServicoNaoAceitasHelper> colecaoCobrancaAcaoOrdemServicoNaoAceitasHelper = new ArrayList();
		List<CobrancaAcaoOrdemServicoNaoAceitasHelper> colecaoHelperOSAteradas = new ArrayList();
		boolean primeiraVez = false;
		
		if (httpServletRequest.getParameter("carregando") != null) {
        	primeiraVez = true;
        	sessao.removeAttribute("colecaoHelperOSAteradas");
		}
		
		if (sessao.getAttribute("colecaoHelperOSAteradas") != null) {
			colecaoHelperOSAteradas = (List<CobrancaAcaoOrdemServicoNaoAceitasHelper>) sessao.getAttribute("colecaoHelperOSAteradas");
		}
		
        
		//Caso a pesquisa seja feita a partir de uma ordem de serviço
        if (comandosAcaoCobrancaFiltrarActionForm.getIdOrdemServicoConsulta() != null
        		&& !comandosAcaoCobrancaFiltrarActionForm.getIdOrdemServicoConsulta().equals("")) {

			//Caso o usuário selecione um ordem de serviço para alterar
			if (httpServletRequest.getParameter("carregando") != null && 
	        		httpServletRequest.getParameter("carregando").equals("SIM")) {
				
	        	OrdemServico ordemServico = fachada.pesquisarOS(new Integer(comandosAcaoCobrancaFiltrarActionForm.getIdOrdemServicoConsulta()));

	        	// Caso não exista a Ordem de Serviço
	        	if (ordemServico == null) {
	    			throw new ActionServletException("atencao.ordens.servico.inexistente",
	    					null, "Ordem de Serviço");
				}
	        	// Caso a Ordem de Serviço não esteja encerrada 
				if (ordemServico.getSituacao() == ConstantesSistema.INDICADOR_OS_NAO_ENCERRADA) {
	    			throw new ActionServletException("atencao.ordens.servico.nao.encerrada",
	    					null, "Ordem de Serviço");
				}
	        	// Caso a Ordem de Serviço não esteja associada à ação de cobrança selecionada
				if(!Util.verificarIdNaoVazio(comandosAcaoCobrancaFiltrarActionForm.getIdOrdemServicoConsulta())){
					if (ordemServico.getCobrancaDocumento() == null 
							|| ordemServico.getCobrancaDocumento().getCobrancaAcaoAtividadeCronograma() == null
							|| !ordemServico.getCobrancaDocumento().getCobrancaAcaoAtividadeCronograma()
								.getCobrancaAcaoCronograma().getCobrancaAcao().getId()
									.equals(new Integer(comandosAcaoCobrancaFiltrarActionForm.getIdAcaoCobranca()))) {
						throw new ActionServletException("atencao.ordens.servico.acao.cobranca",
		    					null, "Ordem de Serviço");
					}
				}
	        	// Caso a Ordem de Serviço tenha sido encerrada por "Decurso de Prazo"
				if (ordemServico.getAtendimentoMotivoEncerramento().getId()
						.equals(new Integer(AtendimentoMotivoEncerramento.CANCELADO_POR_DERCURSO_DE_PRAZO))) {
					throw new ActionServletException("atencao.ordens.servico.decurso.prazo",
	    					null, "Ordem de Serviço");
				}
				
				FiltroCobrancaAcaoOrdemServicoNaoAceitas filtroCobrancaAcaoOrdemServicoNaoAceitas = new FiltroCobrancaAcaoOrdemServicoNaoAceitas();
	    		
	    		filtroCobrancaAcaoOrdemServicoNaoAceitas.adicionarParametro(new ParametroSimples(
	    				FiltroCobrancaAcaoOrdemServicoNaoAceitas.ORDEM_SERVICO, ordemServico));
	    		filtroCobrancaAcaoOrdemServicoNaoAceitas
	    				.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoOrdemServicoNaoAceitas.ORDEM_SERVICO);
	    		filtroCobrancaAcaoOrdemServicoNaoAceitas
				.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoOrdemServicoNaoAceitas.MOTIVO_NAO_ACEITACAO);
	    		
	    		Collection<CobrancaAcaoOrdemServicoNaoAceitas> colecao = fachada
	    			.pesquisar(filtroCobrancaAcaoOrdemServicoNaoAceitas, CobrancaAcaoOrdemServicoNaoAceitas.class.getName());
	    		
	    		if (colecao != null && colecao.size() > 0) {
	    			CobrancaAcaoOrdemServicoNaoAceitas cobrancaAcaoOrdemServicoNaoAceitas = colecao.iterator().next();
					comandosAcaoCobrancaFiltrarActionForm.setMotivoNaoAceitacao(
							String.valueOf(cobrancaAcaoOrdemServicoNaoAceitas.getMotivoNaoAceitacao().getId()));
					if (cobrancaAcaoOrdemServicoNaoAceitas.getObservacao() != null) {
						comandosAcaoCobrancaFiltrarActionForm.setObservacao(cobrancaAcaoOrdemServicoNaoAceitas.getObservacao());
					} else {
						comandosAcaoCobrancaFiltrarActionForm.setObservacao("");
					}
					sessao.removeAttribute("indicadorNaoAceitacao");
	    		} else {
					comandosAcaoCobrancaFiltrarActionForm.setMotivoNaoAceitacao("");
					comandosAcaoCobrancaFiltrarActionForm.setObservacao("");
	    			sessao.setAttribute("indicadorNaoAceitacao", true);
	    		}
	    		
	    		try {
	    			CobrancaAcao cobrancaAcao = ordemServico.getCobrancaDocumento().getCobrancaAcaoAtividadeCronograma().getCobrancaAcaoCronograma().getCobrancaAcao();
	    			sessao.setAttribute("objetoCobrancaAcao", cobrancaAcao);
	    		} catch (Exception e) {
	    			e.printStackTrace();
				}
	    		
	    		try {
	    			CobrancaGrupo cobrancaGrupo = ordemServico.getCobrancaDocumento().getCobrancaAcaoAtividadeCronograma().getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes().getCobrancaGrupo();
	    			sessao.setAttribute("objetoCobrancaGrupo",cobrancaGrupo);
				} catch (Exception e) {
					e.printStackTrace();
				}

				sessao.setAttribute("ordemServicoConsulta", ordemServico);
				sessao.setAttribute("ordemServicoSelecionada", true);
			}

			// Caso o usuário clique no botão "Concluir"
			if (httpServletRequest.getParameter("acao") != null && 
	        		httpServletRequest.getParameter("acao").equals("concluir")) {
				
				CobrancaAcao cobrancaAcao = (CobrancaAcao) sessao.getAttribute("objetoCobrancaAcao");
				OrdemServico ordemServico = (OrdemServico) sessao.getAttribute("ordemServicoConsulta");
				CobrancaAcaoOrdemServicoNaoAceitas cobrancaAcaoOrdemServicoNaoAceitas = new CobrancaAcaoOrdemServicoNaoAceitas();
				CobrancaAcaoOrdemServicoNaoAceitasPK cobrancaAcaoOrdemServicoNaoAceitasPK = new CobrancaAcaoOrdemServicoNaoAceitasPK();
				Date date = new Date();

				cobrancaAcaoOrdemServicoNaoAceitas.setUltimaAlteracao(date);
				cobrancaAcaoOrdemServicoNaoAceitas.setIndicadorNaoAceitacao(new Short("2"));
				cobrancaAcaoOrdemServicoNaoAceitas.setIndicadorDescontoEfetuado(new Short("2"));
				cobrancaAcaoOrdemServicoNaoAceitas.setOrdemServico(ordemServico);
				cobrancaAcaoOrdemServicoNaoAceitas.setCobrancaAcao(cobrancaAcao);
				cobrancaAcaoOrdemServicoNaoAceitasPK.setOrdemServicoId(ordemServico.getId());
				//	cobrancaAcaoOrdemServicoNaoAceitasPK.setCobrancaAcaoId(cobrancaAcao.getId());
				cobrancaAcaoOrdemServicoNaoAceitas.setComp_id(cobrancaAcaoOrdemServicoNaoAceitasPK);
				
				if (comandosAcaoCobrancaFiltrarActionForm.getObservacao() != null
						&& !comandosAcaoCobrancaFiltrarActionForm.getObservacao().equals("")) {
					cobrancaAcaoOrdemServicoNaoAceitas.setObservacao(comandosAcaoCobrancaFiltrarActionForm.getObservacao());
				} else {
					cobrancaAcaoOrdemServicoNaoAceitas.setObservacao(null);
				}

				if (Util.verificarNaoVazio(comandosAcaoCobrancaFiltrarActionForm.getMotivoNaoAceitacao())) {

					MotivoNaoAceitacaoEncerramentoOS motivoNaoAceitacaoEncerramentoOS = new MotivoNaoAceitacaoEncerramentoOS();
					motivoNaoAceitacaoEncerramentoOS.setId(Integer.parseInt(comandosAcaoCobrancaFiltrarActionForm.getMotivoNaoAceitacao()));
					cobrancaAcaoOrdemServicoNaoAceitas.setMotivoNaoAceitacao(motivoNaoAceitacaoEncerramentoOS);
					
				} else {
					throw new ActionServletException("atencao.motivo_nao_aceitacao_encerramento_os", null, "Motivo de Não Aceitação");
				}
				
				FiltroCobrancaAcaoOrdemServicoNaoAceitas filtro = new FiltroCobrancaAcaoOrdemServicoNaoAceitas();
				filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoOrdemServicoNaoAceitas.COBRANCA_ACAO_ID, cobrancaAcao.getId()));
				filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoOrdemServicoNaoAceitas.ORDEM_SERVICO_ID, ordemServico.getId()));
				filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoOrdemServicoNaoAceitas.COBRANCA_ACAO);
				filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoOrdemServicoNaoAceitas.ORDEM_SERVICO);
				
				Collection <CobrancaAcaoOrdemServicoNaoAceitas> colecao = fachada.pesquisar(filtro, CobrancaAcaoOrdemServicoNaoAceitas.class.getName());

				// Monta a página de sucesso
				montarPaginaSucesso(httpServletRequest, "Atualizações efetuadas com sucesso.",
						"Consultar Comandos de Ação de Cobrança",
						"exibirFiltrarComandosAcaoCobrancaAction.do?menu=sim");
				
				if (colecao != null && !colecao.isEmpty()) {
					if (colecao.iterator().next().getIndicadorDescontoEfetuado().equals(new Short("1"))) {
						cobrancaAcaoOrdemServicoNaoAceitas.setIndicadorDescontoEfetuado(new Short("1"));
						// Monta a página de sucesso
						montarPaginaSucesso(httpServletRequest, "Situação não pode ser alterada, desconto já efetuado."
								+ " Atualizações efetuadas com sucesso.",
								"Consultar Comandos de Ação de Cobrança",
								"exibirFiltrarComandosAcaoCobrancaAction.do?menu=sim");
					}
					
					fachada.atualizar(cobrancaAcaoOrdemServicoNaoAceitas);
					
				} else {
					fachada.inserir(cobrancaAcaoOrdemServicoNaoAceitas);
				}
					

				retorno = actionMapping.findForward("telaSucesso");
			}
			
        } else { //Caso a pesquisa seja feita a partir de ação de cobrança/grupo de cobrança
        	sessao.removeAttribute("ordemServicoConsulta");
        	sessao.removeAttribute("indicadorNaoAceitacao");
			//Carregar o filtro
	        if (sessao.getAttribute("filtroOrdemServico") == null || primeiraVez) {
	        	
	        	filtroOrdemServico = 
			        	fachada.construirFiltroOrdemServico(
			        			comandosAcaoCobrancaFiltrarActionForm.getIdGrupoCobranca(),
			        			comandosAcaoCobrancaFiltrarActionForm.getIdAcaoCobranca(),
			        			comandosAcaoCobrancaFiltrarActionForm.getPeriodoReferenciaContaInicial(),
			        			comandosAcaoCobrancaFiltrarActionForm.getPeriodoReferenciaContaFinal()
			        	);
				  
		        sessao.setAttribute("filtroOrdemServico", filtroOrdemServico);
		        
	        }
	        
	        //Consultar as Ordens de Serviço
	        if (sessao.getAttribute("filtroOrdemServico") != null 
	        		&& (primeiraVez || httpServletRequest.getParameter("page.offset") != null)) {
	
	    		if (!(httpServletRequest.getParameter("acao") != null
    				&& (httpServletRequest.getParameter("acao").equals("selecionar")
					|| httpServletRequest.getParameter("acao").equals("adicionar")))) {
	    			
	    			comandosAcaoCobrancaFiltrarActionForm.setIdOrdemServico("");
	    		}
	        	
	        	filtroOrdemServico = (FiltroOrdemServico) sessao.getAttribute("filtroOrdemServico");
	    		
	    		Map resultado = controlarPaginacao(httpServletRequest, retorno,
	    				filtroOrdemServico, OrdemServico.class.getName());
	   	     
	    		Collection colecaoOrdemServico  = (Collection) resultado.get("colecaoRetorno");
		   	     retorno = (ActionForward) resultado.get("destinoActionForward");
	   		
	    		if (colecaoOrdemServico == null || colecaoOrdemServico.isEmpty()) {
	    			throw new ActionServletException("atencao.ordens.servico.inexistente",
	    					null, "Ordem de Serviço");
	    		}
	
	    		Iterator iterator = colecaoOrdemServico.iterator();
	    		
	    		while (iterator.hasNext()) {
	    			OrdemServico ordemServico = (OrdemServico) iterator.next();
	    			CobrancaAcaoOrdemServicoNaoAceitasHelper cobrancaAcaoOrdemServicoNaoAceitasHelper = new CobrancaAcaoOrdemServicoNaoAceitasHelper();
	    			CobrancaAcaoOrdemServicoNaoAceitas cobrancaAcaoOrdemServicoNaoAceitas = new CobrancaAcaoOrdemServicoNaoAceitas();
	    			FiltroCobrancaAcaoOrdemServicoNaoAceitas filtroCobrancaAcaoOrdemServicoNaoAceitas = new FiltroCobrancaAcaoOrdemServicoNaoAceitas();
	        		
	        		filtroCobrancaAcaoOrdemServicoNaoAceitas.adicionarParametro(new ParametroSimples(
	        				FiltroCobrancaAcaoOrdemServicoNaoAceitas.ORDEM_SERVICO, ordemServico));
	        		filtroCobrancaAcaoOrdemServicoNaoAceitas.adicionarParametro(new ParametroSimples(
	        				FiltroCobrancaAcaoOrdemServicoNaoAceitas.COBRANCA_ACAO, ordemServico.getCobrancaDocumento().getCobrancaAcaoAtividadeCronograma().getCobrancaAcaoCronograma().getCobrancaAcao()));
	        		
	        		filtroCobrancaAcaoOrdemServicoNaoAceitas.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoOrdemServicoNaoAceitas.ORDEM_SERVICO);
	        		filtroCobrancaAcaoOrdemServicoNaoAceitas.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoOrdemServicoNaoAceitas.COBRANCA_ACAO);
	        		
	        		Collection<CobrancaAcaoOrdemServicoNaoAceitas> colecaoCobrancaAcaoOrdemServicoNaoAceitas = fachada
	        			.pesquisar(filtroCobrancaAcaoOrdemServicoNaoAceitas, CobrancaAcaoOrdemServicoNaoAceitas.class.getName());
	        		
	        		int acaoOrdemServicoNaoAceitasHelper =  posicaoOrdemServicoAlterada(ordemServico, colecaoHelperOSAteradas);
	        		if (acaoOrdemServicoNaoAceitasHelper != 0) {
	        			cobrancaAcaoOrdemServicoNaoAceitas = colecaoHelperOSAteradas.get(acaoOrdemServicoNaoAceitasHelper - 1).getCobrancaAcaoOrdemServicoNaoAceitas();
	        		} else if (colecaoCobrancaAcaoOrdemServicoNaoAceitas != null && !colecaoCobrancaAcaoOrdemServicoNaoAceitas.isEmpty()) {
	        			cobrancaAcaoOrdemServicoNaoAceitas = colecaoCobrancaAcaoOrdemServicoNaoAceitas.iterator().next();
	        		}
	        		cobrancaAcaoOrdemServicoNaoAceitasHelper.setCobrancaAcaoOrdemServicoNaoAceitas(cobrancaAcaoOrdemServicoNaoAceitas);
	    			cobrancaAcaoOrdemServicoNaoAceitasHelper.setOrdemServico(ordemServico);
	    			colecaoCobrancaAcaoOrdemServicoNaoAceitasHelper.add(cobrancaAcaoOrdemServicoNaoAceitasHelper);
	    		
	    		}
	    		CobrancaAcao cobrancaAcao = ((OrdemServico)colecaoOrdemServico.iterator().next()).getCobrancaDocumento().getCobrancaAcaoAtividadeCronograma().getCobrancaAcaoCronograma().getCobrancaAcao();
	    		CobrancaGrupo cobrancaGrupo = ((OrdemServico)colecaoOrdemServico.iterator().next()).getCobrancaDocumento().getCobrancaAcaoAtividadeCronograma().getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes().getCobrancaGrupo();
	    		sessao.setAttribute("objetoCobrancaAcao", cobrancaAcao);
	        	sessao.setAttribute("objetoCobrancaGrupo",cobrancaGrupo);
	        	sessao.setAttribute("colecaoCobrancaAcaoOrdemServicoNaoAceitasHelper", colecaoCobrancaAcaoOrdemServicoNaoAceitasHelper);
	        } else {
	        	colecaoCobrancaAcaoOrdemServicoNaoAceitasHelper = (List<CobrancaAcaoOrdemServicoNaoAceitasHelper>) sessao.getAttribute("colecaoCobrancaAcaoOrdemServicoNaoAceitasHelper");
	        }
			
	        //Caso selecione uma ordem de serviço
			if (httpServletRequest.getParameter("acao") != null 
					&& httpServletRequest.getParameter("acao").equals("selecionar")
	        		&& comandosAcaoCobrancaFiltrarActionForm.getIdOrdemServico() != null) {
	    	        
				int posicaoComponente = new Integer(comandosAcaoCobrancaFiltrarActionForm.getIdOrdemServico());
				
				if (colecaoCobrancaAcaoOrdemServicoNaoAceitasHelper.size() >= posicaoComponente) {
					
					@SuppressWarnings("unused") 
					CobrancaAcaoOrdemServicoNaoAceitasHelper cobrancaAcaoOrdemServicoNaoAceitasHelper =
						(CobrancaAcaoOrdemServicoNaoAceitasHelper) colecaoCobrancaAcaoOrdemServicoNaoAceitasHelper.get(posicaoComponente-1);
					
					OrdemServico ordemServico = fachada.pesquisarOS(new Integer(cobrancaAcaoOrdemServicoNaoAceitasHelper.getOrdemServico().getId()));
					
					FiltroCobrancaAcaoOrdemServicoNaoAceitas filtroCobrancaAcaoOrdemServicoNaoAceitas = new FiltroCobrancaAcaoOrdemServicoNaoAceitas();
		    		
		    		filtroCobrancaAcaoOrdemServicoNaoAceitas.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoOrdemServicoNaoAceitas.ORDEM_SERVICO, ordemServico));
		    		filtroCobrancaAcaoOrdemServicoNaoAceitas.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoOrdemServicoNaoAceitas.ORDEM_SERVICO);
		    		filtroCobrancaAcaoOrdemServicoNaoAceitas.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoOrdemServicoNaoAceitas.MOTIVO_NAO_ACEITACAO);
		    		
		    		Collection<CobrancaAcaoOrdemServicoNaoAceitas> colecao = fachada.pesquisar(filtroCobrancaAcaoOrdemServicoNaoAceitas, CobrancaAcaoOrdemServicoNaoAceitas.class.getName());
		    		
		    		if (colecao != null && colecao.size() > 0) {
		    			CobrancaAcaoOrdemServicoNaoAceitas cobrancaAcaoOrdemServicoNaoAceitas = colecao.iterator().next();
						comandosAcaoCobrancaFiltrarActionForm.setMotivoNaoAceitacao(String.valueOf(cobrancaAcaoOrdemServicoNaoAceitas.getMotivoNaoAceitacao().getId()));
						if (cobrancaAcaoOrdemServicoNaoAceitas.getObservacao() != null) {
							comandosAcaoCobrancaFiltrarActionForm.setObservacao(cobrancaAcaoOrdemServicoNaoAceitas.getObservacao());
						} else {
							comandosAcaoCobrancaFiltrarActionForm.setObservacao("");
						}
		    		} else {
						comandosAcaoCobrancaFiltrarActionForm.setMotivoNaoAceitacao("");
						comandosAcaoCobrancaFiltrarActionForm.setObservacao("");
		    		}
					
					sessao.setAttribute("ordemServicoSelecionada", true);
				}
			
			} else {
				sessao.removeAttribute("ordemServicoSelecionada");
			}
		
			// Caso o usuário clique no botão "Adicionar"
			if (httpServletRequest.getParameter("acao") != null 
					&& httpServletRequest.getParameter("acao").equals("adicionar")) {
				
				sessao.setAttribute("ordemServicoSelecionada", true);
				int posicaoComponente = new Integer(comandosAcaoCobrancaFiltrarActionForm.getIdOrdemServico());
	
				if (colecaoCobrancaAcaoOrdemServicoNaoAceitasHelper.size() >= posicaoComponente) {
					
					CobrancaAcaoOrdemServicoNaoAceitasHelper cobrancaAcaoOrdemServicoNaoAceitasHelper =
						(CobrancaAcaoOrdemServicoNaoAceitasHelper) colecaoCobrancaAcaoOrdemServicoNaoAceitasHelper.get(posicaoComponente-1);
					
					
					MotivoNaoAceitacaoEncerramentoOS motivoNaoAceitacaoEncerramentoOS = new MotivoNaoAceitacaoEncerramentoOS();
					motivoNaoAceitacaoEncerramentoOS.setId(Integer.parseInt(comandosAcaoCobrancaFiltrarActionForm.getMotivoNaoAceitacao()));
					cobrancaAcaoOrdemServicoNaoAceitasHelper.getCobrancaAcaoOrdemServicoNaoAceitas().setMotivoNaoAceitacao(motivoNaoAceitacaoEncerramentoOS);
					
					cobrancaAcaoOrdemServicoNaoAceitasHelper.getCobrancaAcaoOrdemServicoNaoAceitas().setObservacao(comandosAcaoCobrancaFiltrarActionForm.getObservacao());
					
					colecaoCobrancaAcaoOrdemServicoNaoAceitasHelper.remove(posicaoComponente-1);
					colecaoCobrancaAcaoOrdemServicoNaoAceitasHelper.add(cobrancaAcaoOrdemServicoNaoAceitasHelper);
		    		
		    		// O sistema classifica a lista de CobrancaAcaoOrdemServicoNaoAceitasHelper por tipo de serviço
					Collections.sort((List) colecaoCobrancaAcaoOrdemServicoNaoAceitasHelper,
							new Comparator() {
								public int compare(Object a, Object b) {
									Integer codigo1 = ((CobrancaAcaoOrdemServicoNaoAceitasHelper) a)
											.getOrdemServico().getId();
									Integer codigo2 = ((CobrancaAcaoOrdemServicoNaoAceitasHelper) b)
											.getOrdemServico().getId();
									if (codigo1 == null || codigo1.equals("")) {
										return -1;
									} else {
										return codigo1.compareTo(codigo2);
									}
								}
							});
	
		        	sessao.setAttribute("colecaoCobrancaAcaoOrdemServicoNaoAceitasHelper", colecaoCobrancaAcaoOrdemServicoNaoAceitasHelper);
		        	
		        	int posicaoOSAlterada = posicaoOrdemServicoAlterada(cobrancaAcaoOrdemServicoNaoAceitasHelper.getOrdemServico(), colecaoHelperOSAteradas);
					if (posicaoOSAlterada != 0) {
						colecaoHelperOSAteradas.remove(posicaoOSAlterada - 1);
					}
					colecaoHelperOSAteradas.add(cobrancaAcaoOrdemServicoNaoAceitasHelper);
					
					// O sistema classifica a lista de CobrancaAcaoOrdemServicoNaoAceitasHelper por tipo de serviço
					Collections.sort((List) colecaoHelperOSAteradas,
							new Comparator() {
								public int compare(Object a, Object b) {
									Integer codigo1 = ((CobrancaAcaoOrdemServicoNaoAceitasHelper) a)
											.getOrdemServico().getId();
									Integer codigo2 = ((CobrancaAcaoOrdemServicoNaoAceitasHelper) b)
											.getOrdemServico().getId();
									if (codigo1 == null || codigo1.equals("")) {
										return -1;
									} else {
										return codigo1.compareTo(codigo2);
									}
								} 	
							});
					sessao.setAttribute("colecaoHelperOSAteradas", colecaoHelperOSAteradas);
				}
			}
			
			// Caso o usuário clique no botão "Concluir"
			if (httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("concluir")) {
				
				Iterator iterator = colecaoHelperOSAteradas.iterator();
				CobrancaAcao cobrancaAcao = (CobrancaAcao) sessao.getAttribute("objetoCobrancaAcao");
				String ids = "";
				
				while (iterator.hasNext()) {
					CobrancaAcaoOrdemServicoNaoAceitasHelper cobrancaAcaoOrdemServicoNaoAceitasHelper = (CobrancaAcaoOrdemServicoNaoAceitasHelper) iterator.next();
					
					OrdemServico ordemServico = cobrancaAcaoOrdemServicoNaoAceitasHelper.getOrdemServico();
					CobrancaAcaoOrdemServicoNaoAceitas cobrancaAcaoOrdemServicoNaoAceitas = cobrancaAcaoOrdemServicoNaoAceitasHelper.getCobrancaAcaoOrdemServicoNaoAceitas();
					CobrancaAcaoOrdemServicoNaoAceitasPK cobrancaAcaoOrdemServicoNaoAceitasPK = new CobrancaAcaoOrdemServicoNaoAceitasPK();
					Date date = new Date();
					
					cobrancaAcaoOrdemServicoNaoAceitas.setUltimaAlteracao(date);
					cobrancaAcaoOrdemServicoNaoAceitas.setIndicadorNaoAceitacao(new Short("2"));
					cobrancaAcaoOrdemServicoNaoAceitas.setIndicadorDescontoEfetuado(new Short("2"));
					cobrancaAcaoOrdemServicoNaoAceitas.setCobrancaAcao(cobrancaAcao);
					cobrancaAcaoOrdemServicoNaoAceitas.setOrdemServico(ordemServico);
					cobrancaAcaoOrdemServicoNaoAceitasPK.setOrdemServicoId(ordemServico.getId());
					cobrancaAcaoOrdemServicoNaoAceitas.setComp_id(cobrancaAcaoOrdemServicoNaoAceitasPK);
					
					if (!Util.verificarNaoVazio(cobrancaAcaoOrdemServicoNaoAceitas.getObservacao())) {
						cobrancaAcaoOrdemServicoNaoAceitas.setObservacao(null);
					}
					
					FiltroCobrancaAcaoOrdemServicoNaoAceitas filtro = new FiltroCobrancaAcaoOrdemServicoNaoAceitas();
					// filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoOrdemServicoNaoAceitas.COBRANCA_ACAO_ID, cobrancaAcao.getId()));
					filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoOrdemServicoNaoAceitas.ORDEM_SERVICO_ID, ordemServico.getId()));
					// filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoOrdemServicoNaoAceitas.COBRANCA_ACAO);
					filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoOrdemServicoNaoAceitas.ORDEM_SERVICO);
					
					Collection <CobrancaAcaoOrdemServicoNaoAceitas> colecao = fachada.pesquisar(filtro, CobrancaAcaoOrdemServicoNaoAceitas.class.getName());
					
					if (colecao != null && !colecao.isEmpty()) {
						if (colecao.iterator().next().getIndicadorDescontoEfetuado().equals(new Short("1"))) {
							cobrancaAcaoOrdemServicoNaoAceitas.setIndicadorDescontoEfetuado(new Short("1"));
							ids += ordemServico.getId() + ", ";
						}
						fachada.atualizar(cobrancaAcaoOrdemServicoNaoAceitas);
					} else {
						fachada.inserir(cobrancaAcaoOrdemServicoNaoAceitas);
					}
					
				}

				//Monta a página de sucesso
				if (!ids.equals("")) {
					ids = Util.removerUltimosCaracteres(ids, 2);
					montarPaginaSucesso(httpServletRequest, "Situação da(s) ordem(ns) de serviço " +
							ids + " não pode ser alterada, desconto já efetuado."
							+ " Atualizações efetuadas com sucesso.",
							"Consultar Comandos de Ação de Cobrança",
							"exibirFiltrarComandosAcaoCobrancaAction.do?menu=sim");
				} else {
					montarPaginaSucesso(httpServletRequest, "Atualizações efetuadas com sucesso.",
							"Consultar Comandos de Ação de Cobrança",
							"exibirFiltrarComandosAcaoCobrancaAction.do?menu=sim");
				}
				
				retorno = actionMapping.findForward("telaSucesso");
			}
        
        }
        
		
        return retorno;
    }
    
    
    private int posicaoOrdemServicoAlterada(OrdemServico ordemServico, List<CobrancaAcaoOrdemServicoNaoAceitasHelper> colecaoHelper){
    	Iterator iterator = colecaoHelper.iterator();
    	int posicao = 1;
    	
    	while (iterator.hasNext()) {
    		CobrancaAcaoOrdemServicoNaoAceitasHelper cobrancaAcaoOrdemServicoNaoAceitasHelper = (CobrancaAcaoOrdemServicoNaoAceitasHelper) iterator.next();
    		
    		if (cobrancaAcaoOrdemServicoNaoAceitasHelper.getOrdemServico().getId().equals(ordemServico.getId()))
    			return posicao;
    		
    		posicao++;
    	}
    	
    	return 0;
    }    
}