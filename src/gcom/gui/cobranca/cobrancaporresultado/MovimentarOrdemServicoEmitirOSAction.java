package gcom.gui.cobranca.cobrancaporresultado;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioOrdemServico;
import gcom.relatorio.cobranca.cobrancaporresultado.RelatorioDocumentoVisitaCobranca;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import java.util.Collection;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável por validar os dados informados na página de Emitir OS do processo 
 * de movimentar ordem de serviço de cobrança por resultado.
 *
 * @author Mariana Victor
 * @date 10/05/2011
 */
public class MovimentarOrdemServicoEmitirOSAction extends ExibidorProcessamentoTarefaRelatorio {
   
    /**
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        //Cria uma instância da sessão 
        HttpSession sessao = httpServletRequest.getSession(false);
		
        MovimentarOrdemServicoActionForm form = (MovimentarOrdemServicoActionForm) actionForm;

		if (httpServletRequest.getParameter("confirmado") != null
				&& httpServletRequest.getParameter("confirmado").equalsIgnoreCase("ok")) {
			
	    	httpServletRequest.setAttribute("concluir", "true");
	    	
		}
		
        if ((httpServletRequest.getParameter("concluir") != null
				&& (httpServletRequest.getParameter("concluir")).toString().equalsIgnoreCase("true"))
				|| (httpServletRequest.getAttribute("concluir") != null
						&& (httpServletRequest.getAttribute("concluir")).toString().equalsIgnoreCase("true"))) {
			//Se clicou no botão de "Emitir"
			
        	//Se selecionou a div "O.S. Gerada Tipo Visita para Cobrança"
			if (form.getTipoDivEscolhida() != null
					&& form.getTipoDivEscolhida().equalsIgnoreCase("1")) {
				
				String mensagem = validarFormEmitirOSComando(form);
				
				if (mensagem != null && !mensagem.equals("")) {
					throw new ActionServletException(
							mensagem);
				}
				
				MovimentarOrdemServicoEmitirOSHelper movimentarOrdemServicoEmitirOSHelper = 
						(MovimentarOrdemServicoEmitirOSHelper) sessao.getAttribute("movimentarOrdemServicoEmitirOSHelper");
				
				Collection<Integer[]> idsImovel = Fachada.getInstancia().pesquisarOSComandoSelecionado(movimentarOrdemServicoEmitirOSHelper);

				if (idsImovel == null || idsImovel.isEmpty()){
					throw new ActionServletException(
							"atencao.ordens_servico.inexistente");
				} else {
					TarefaRelatorio relatorio = new RelatorioDocumentoVisitaCobranca(
							(Usuario) (httpServletRequest.getSession(false))
									.getAttribute("usuarioLogado"));
						
					relatorio.addParametro("idsImovel",	idsImovel);

					relatorio.addParametro("tipoFormatoRelatorio", TarefaRelatorio.TIPO_PDF);
					sessao.setAttribute("tipoRelatorio",TarefaRelatorio.TIPO_PDF + "");

					if (httpServletRequest.getAttribute("concluir") == null
						|| !httpServletRequest.getAttribute("concluir").toString().equalsIgnoreCase("true")) {
						httpServletRequest.setAttribute("telaSucessoRelatorio",true);
					}

					retorno = processarExibicaoRelatorio(relatorio, TarefaRelatorio.TIPO_PDF,
							httpServletRequest, httpServletResponse, actionMapping);

					if (httpServletRequest.getAttribute("concluir") == null
						|| !httpServletRequest.getAttribute("concluir").toString().equalsIgnoreCase("true")) {
					
						httpServletRequest.removeAttribute("confirmacaoNormal");
	
						httpServletRequest.setAttribute("nomeBotao1", "Confirmar");
						sessao.setAttribute("nomeBotao1", "Confirmar");
						httpServletRequest.setAttribute("caminhoConfirmacao", "movimentarOrdemServicoEmitirOSAction");
					}
					//StatusWizard statusWizard = (StatusWizard) this.getSessao(httpServletRequest).getAttribute("statusWizard");
			        
					//this.getSessao(httpServletRequest).setAttribute("statusWizard", statusWizard);
					
					// devolve o mapeamento contido na variável retorno
					return retorno;
					
				}
				
			} else if (form.getTipoDivEscolhida() != null
						&& form.getTipoDivEscolhida().equalsIgnoreCase("2")) {
	        	
				String mensagem = validarFormEmitirOSEmpresa(form);
				
				if (mensagem != null && !mensagem.equals("")) {
					throw new ActionServletException(
							mensagem);
				}

				String[] idsOS = form.getNumerosOSEmpresaCobranca();
				
				String chave = "";
				for (int j=0; j< idsOS.length; j++) {
					chave = chave+"$"+idsOS[j];
				}
				StringTokenizer idsOrdemServico = null;
				idsOrdemServico = new StringTokenizer(chave, "$");
				
				String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

				RelatorioOrdemServico relatorioOrdemServico = new RelatorioOrdemServico(
						(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
				relatorioOrdemServico.addParametro("idsOrdemServico", idsOrdemServico);
				
				if (tipoRelatorio == null) {
					tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
				}

				relatorioOrdemServico.addParametro("tipoFormatoRelatorio", Integer
						.parseInt(tipoRelatorio));
				retorno = processarExibicaoRelatorio(relatorioOrdemServico,
						tipoRelatorio, httpServletRequest, httpServletResponse,
						actionMapping);
				
				// devolve o mapeamento contido na variável retorno
				return retorno;
				
			} else if (form.getTipoDivEscolhida() != null
						&& form.getTipoDivEscolhida().equalsIgnoreCase("3")) {
	        	
				String mensagem = validarFormEmitirOSRA(form);
				
				if (mensagem != null && !mensagem.equals("")) {
					throw new ActionServletException(
							mensagem);
				}


				String[] idsOS = form.getNumerosOSRegistroAtendimento();
				

				String chave = "";
				for (int j=0; j< idsOS.length; j++) {
					chave = chave+"$"+idsOS[j];
				}
				StringTokenizer idsOrdemServico = null;
				idsOrdemServico = new StringTokenizer(chave, "$");
				
				String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

				RelatorioOrdemServico relatorioOrdemServico = new RelatorioOrdemServico(
						(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
				relatorioOrdemServico.addParametro("idsOrdemServico", idsOrdemServico);
				
				if (tipoRelatorio == null) {
					tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
				}

				relatorioOrdemServico.addParametro("tipoFormatoRelatorio", Integer
						.parseInt(tipoRelatorio));
				retorno = processarExibicaoRelatorio(relatorioOrdemServico,
						tipoRelatorio, httpServletRequest, httpServletResponse,
						actionMapping);

				// devolve o mapeamento contido na variável retorno
				return retorno;
				
			} else {
				throw new ActionServletException(
						"atencao.selecione.um_tipo.emissao.ordem_servico");
			}
        }

        // Monta página de sucesso
    	montarPaginaSucesso(httpServletRequest, "Ordem(ns) de Serviço emitida(s) com sucesso!",
			"Voltar",
			"exibirMovimentarOrdemServicoAction.do?comando=" + form.getIdComandoContaCobranca());
        
        //Retorna o mapemaneto na variável "retorno"
        return retorno;
    }

    public String validarFormEmitirOSComando(MovimentarOrdemServicoActionForm form) {
		
		if (form.getNumeroOSInicial() != null && !form.getNumeroOSInicial().equals("")
				&& form.getNumeroOSFinal() != null && !form.getNumeroOSFinal().equals("")
				&& (new Integer(form.getNumeroOSInicial())).compareTo(new Integer(form.getNumeroOSFinal())) > 0){
			return "atencao.numero_ordem_servico_final.maior.inicial";
		} 

		String retorno = validarLocalidade(form);
		
		if (retorno != null) {
			return retorno;
		} else {
			retorno = validarSetorComercial(form);
			if (retorno != null) {
				return retorno;
			}
		}
			
		//return "Nenhum filtro selecionado.";
		if (form.getColecaoInformada() == null || form.getColecaoInformada().equals("")) {
			return "atencao.selecione.ordem_servido.gerada.visita_cobranca";
		}
		
		return null;
    }

    public String validarFormEmitirOSEmpresa(MovimentarOrdemServicoActionForm form) {

    	if (form.getNumerosOSEmpresaCobranca() == null
    			|| form.getNumerosOSEmpresaCobranca().length == 0) {
    		return "atencao.selecione.alguma.ordens_servico";
    	}
    	
		return null;
    }

    public String validarFormEmitirOSRA(MovimentarOrdemServicoActionForm form) {

    	if (form.getNumerosOSRegistroAtendimento() == null
    			|| form.getNumerosOSRegistroAtendimento().length == 0) {
    		return "atencao.selecione.alguma.ordens_servico";
    	}
    	
		return null;
    }
    
    public String validarLocalidade(MovimentarOrdemServicoActionForm form) {
    	String retorno = null;
    			
		if(form.getIdLocalidadeOrigem() != null && !form.getIdLocalidadeOrigem().equals("") 
			&& (form.getIdLocalidadeDestino() == null || form.getIdLocalidadeDestino().equals(""))){
				
			retorno = "atencao.informe.localidade_final";	
		}
    	
    	return retorno;
    }
    
    public String validarSetorComercial(MovimentarOrdemServicoActionForm form) {
    	String retorno = null;
    	
		if(form.getCodigoSetorComercialOrigem() != null && !form.getCodigoSetorComercialOrigem().equals("") 
				&& (form.getCodigoSetorComercialDestino() == null || form.getCodigoSetorComercialDestino().equals(""))){
				
			retorno = "atencao.informe.setor_comercial_final";
				
		}
    	
    	return retorno;
    }
}
