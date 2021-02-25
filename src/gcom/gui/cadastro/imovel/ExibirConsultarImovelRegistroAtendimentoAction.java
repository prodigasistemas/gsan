package gcom.gui.cadastro.imovel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.bean.ObterDescricaoSituacaoOSHelper;
import gcom.atendimentopublico.ordemservico.bean.OrdemServicoHelper;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ConsultarImovelRegistroAtendimentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * 10° Aba - Registro de Atendimento
 * 
 * @author Rafael Santos
 * @since 21/09/2006
 */
public class ExibirConsultarImovelRegistroAtendimentoAction extends GcomAction {

    /**
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping
                .findForward("consultarImovelRegistroAtendimento");

        //Obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;

        //id do imovel da aba documento de cobranca
        String idImovelRegistroAtendimento = consultarImovelActionForm.getIdImovelRegistroAtendimento();
        String limparForm = httpServletRequest.getParameter("limparForm");
        
        String indicadorNovo = httpServletRequest.getParameter("indicadorNovo");
		String idImovelPrincipalAba = null;
		if(sessao.getAttribute("idImovelPrincipalAba") != null){
			idImovelPrincipalAba = (String)sessao.getAttribute("idImovelPrincipalAba");
		}          
                
        
        if(limparForm != null && !limparForm.equals("")){
            //limpar os dados 
        	httpServletRequest.setAttribute(
                    "idImovelRegistroAtendimentoNaoEncontrado", null);
        	
        	sessao.removeAttribute("imovelRegistroAtendimento");
        	sessao.removeAttribute("idImovelPrincipalAba");
        	sessao.removeAttribute("imovelClientes");
        	
			consultarImovelActionForm.setIdImovelDadosComplementares(null);
			consultarImovelActionForm.setIdImovelDadosCadastrais(null);
			consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
			consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
			consultarImovelActionForm.setIdImovelDebitos(null);
			consultarImovelActionForm.setIdImovelPagamentos(null);
			consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
			consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
			consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
			consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
			consultarImovelActionForm.setImovIdAnt(null);
        	
        	consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
        	consultarImovelActionForm.setMatriculaImovelRegistroAtendimento(null);
        	consultarImovelActionForm.setSituacaoAguaRegistroAtendimento(null);
        	consultarImovelActionForm.setSituacaoEsgotoRegistroAtendimento(null);
        	sessao.removeAttribute("colecaoConsultarImovelRegistroAtendimentoHelper");
        	sessao.removeAttribute("colecaoOrdemServicoHelper");
            
//        }else if(idImovelRegistroAtendimento != null && !idImovelRegistroAtendimento.equalsIgnoreCase("")){
        }else if( (idImovelRegistroAtendimento != null && !idImovelRegistroAtendimento.equalsIgnoreCase(""))
            	|| (idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase("")) ){
            	
//            	if(	idImovelRegistroAtendimento != null && idImovelPrincipalAba != null 
  //          			&& idImovelPrincipalAba.equals(idImovelRegistroAtendimento)){
        	
        	
        	if(idImovelRegistroAtendimento != null && !idImovelRegistroAtendimento.equalsIgnoreCase("")){
        		
        		if(idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase("")){
            		
        			if(indicadorNovo != null && !indicadorNovo.equals("")){
            			consultarImovelActionForm.setIdImovelRegistroAtendimento(idImovelRegistroAtendimento);            		
        				
        			}else if(!(idImovelRegistroAtendimento.equals(idImovelPrincipalAba))){
            			consultarImovelActionForm.setIdImovelRegistroAtendimento(idImovelPrincipalAba);            		
                		idImovelRegistroAtendimento = idImovelPrincipalAba;
            		}
            		
            		
            	}
        	}else if(idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase("")){
            		consultarImovelActionForm.setIdImovelRegistroAtendimento(idImovelPrincipalAba);            		
            		idImovelRegistroAtendimento = idImovelPrincipalAba;
            }	                	
        	
	        //Obtém a instância da Fachada
	        Fachada fachada = Fachada.getInstancia();
	        Imovel imovel = null;
	        //verifica se o objeto imovel é o mesmo ja pesquisado, para não precisar pesquisar mas.
	        boolean imovelNovoPesquisado = false;
	        if(sessao.getAttribute("imovelRegistroAtendimento") != null){
	        	imovel = (Imovel) sessao.getAttribute("imovelRegistroAtendimento");
	        	if(!(imovel.getId().toString().equals(idImovelRegistroAtendimento.trim()))){
	        		imovel = fachada.consultarImovelHistoricoFaturamento(new Integer(idImovelRegistroAtendimento.trim()));
	        		imovelNovoPesquisado = true;
	        	}
	        }else{
	        	imovel = fachada.consultarImovelHistoricoFaturamento(new Integer(idImovelRegistroAtendimento.trim()));
	        	imovelNovoPesquisado = true;
	        }
	
            if (imovel != null) {
                sessao.setAttribute("imovelRegistroAtendimento", imovel);
                sessao.setAttribute("idImovelPrincipalAba", imovel.getId().toString());
                consultarImovelActionForm.setIdImovelRegistroAtendimento(imovel.getId().toString());
                
				if (imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
					httpServletRequest.setAttribute("imovelExcluido", true);
				}

                //caso o imovel pesquisado seja diferente do pesquisado anterior ou seja a primeira vez que se esteja pesquisando
                if(imovelNovoPesquisado){
	            	//seta na tela a inscrição do imovel
	                httpServletRequest.setAttribute(
	                        "idImovelRegistroAtendimentoNaoEncontrado", null);
	                
	                consultarImovelActionForm.setMatriculaImovelRegistroAtendimento(fachada.pesquisarInscricaoImovelExcluidoOuNao(new Integer(idImovelRegistroAtendimento.trim())));
	                
					//seta a situação de agua
					if(imovel.getLigacaoAguaSituacao() != null){
						consultarImovelActionForm.setSituacaoAguaRegistroAtendimento(imovel.getLigacaoAguaSituacao().getDescricao());
					}
					//seta a situação de esgoto
					if(imovel.getLigacaoEsgotoSituacao() != null){
						consultarImovelActionForm.setSituacaoEsgotoRegistroAtendimento(imovel.getLigacaoEsgotoSituacao().getDescricao());
					}
					
					Collection colecaoRegistroAtendimento = fachada.consultarRegistroAtendimentoImovel(new Integer(idImovelRegistroAtendimento.trim()),null);
					
					/*if (colecaoRegistroAtendimento == null || colecaoRegistroAtendimento.isEmpty()){
			        	httpServletRequest.setAttribute(
			                    "idImovelRegistroAtendimentoNaoEncontrado", null);

			        	sessao.removeAttribute("imovelRegistroAtendimento");
			        	sessao.removeAttribute("idImovelPrincipalAba");
			        	consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
			        	consultarImovelActionForm.setMatriculaImovelRegistroAtendimento(null);
			        	consultarImovelActionForm.setSituacaoAguaRegistroAtendimento(null);
			        	consultarImovelActionForm.setSituacaoEsgotoRegistroAtendimento(null);
			        	sessao.removeAttribute("colecaoConsultarImovelRegistroAtendimentoHelper");
						throw new ActionServletException("atencao.imovel.registro_atendimento.inexistente");
					}
					*/					
					
					Collection colecaoConsultarImovelRegistroAtendimentoHelper  = null;
					
					if(colecaoRegistroAtendimento != null &&
							!colecaoRegistroAtendimento.isEmpty()){
						
						Iterator iteratorColecaoRegistroAtendimento = colecaoRegistroAtendimento.iterator();
						
						colecaoConsultarImovelRegistroAtendimentoHelper = new ArrayList();
						
						while (iteratorColecaoRegistroAtendimento.hasNext()) {
							RegistroAtendimento registroAtendimento = (RegistroAtendimento) iteratorColecaoRegistroAtendimento.next();
							
							ConsultarImovelRegistroAtendimentoHelper consultarImovelRegistroAtendimentoHelper = new ConsultarImovelRegistroAtendimentoHelper();

							//id registro atendimento
							if(registroAtendimento != null  && registroAtendimento.getId() != null ){
								consultarImovelRegistroAtendimentoHelper.setIdRegistroAtendimento(registroAtendimento.getId().toString());
							}
							
							//tipo de solicitação
							if(registroAtendimento != null && registroAtendimento.getSolicitacaoTipoEspecificacao() != null 
									&& registroAtendimento.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo() != null){
								
								consultarImovelRegistroAtendimentoHelper.setTipoSolicitacao(registroAtendimento.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo().getDescricao());
							}
							
							//especificação
							if(registroAtendimento != null && registroAtendimento.getSolicitacaoTipoEspecificacao() != null){
								consultarImovelRegistroAtendimentoHelper.setEspecificacao(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
							}
							
							//data de atendimento
							if(registroAtendimento != null && registroAtendimento.getRegistroAtendimento() != null ){
								consultarImovelRegistroAtendimentoHelper.setDataAtendimento(Util.formatarData(registroAtendimento.getRegistroAtendimento()));
							}
							
							//situacao
							if(registroAtendimento != null && registroAtendimento.getId() != null){
								ObterDescricaoSituacaoRAHelper obterDescricaoSituacaoRAHelper = 
									fachada.obterDescricaoSituacaoRA(registroAtendimento.getId());
								consultarImovelRegistroAtendimentoHelper.setSituacao(obterDescricaoSituacaoRAHelper.getDescricaoSituacao());
								
							}
							
							//PROTOCOLO
							if(registroAtendimento != null  && registroAtendimento.getId() != null ){
								
								FiltroRegistroAtendimentoSolicitante filtroRegistroAtendimentoSolicitante = 
								new FiltroRegistroAtendimentoSolicitante();
								
								filtroRegistroAtendimentoSolicitante.adicionarParametro(
								new ParametroSimples(FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO_ID, 
								registroAtendimento.getId()));
								
								filtroRegistroAtendimentoSolicitante.adicionarParametro(
								new ParametroSimples(FiltroRegistroAtendimentoSolicitante.INDICADOR_SOLICITANTE_PRINCIPAL, 
								ConstantesSistema.SIM));
								
								Collection colecaoRegistroAtendimentoSolicitante = fachada.pesquisar(filtroRegistroAtendimentoSolicitante,
								RegistroAtendimentoSolicitante.class.getName());
								
								if (colecaoRegistroAtendimentoSolicitante != null &&
									!colecaoRegistroAtendimentoSolicitante.isEmpty()){
									
									RegistroAtendimentoSolicitante solicitante = (RegistroAtendimentoSolicitante)
									Util.retonarObjetoDeColecao(colecaoRegistroAtendimentoSolicitante);
									
									if (solicitante.getNumeroProtocoloAtendimento() != null){
										
										consultarImovelRegistroAtendimentoHelper.setNumeroProtocolo(
										solicitante.getNumeroProtocoloAtendimento());
									}
								}	
							}
							
							//Date Encerramento
							if(registroAtendimento != null && registroAtendimento.getDataEncerramento() != null ){
								
								consultarImovelRegistroAtendimentoHelper.setDataEncerramento(
										Util.formatarData(registroAtendimento.getDataEncerramento()));
							}
							
							//Motivo do encerramento
							if(registroAtendimento != null && registroAtendimento.getAtendimentoMotivoEncerramento() != null ){
								
								consultarImovelRegistroAtendimentoHelper.setMotivoEncerramento(
									registroAtendimento.getAtendimentoMotivoEncerramento().getDescricao());
							}
							
							
							colecaoConsultarImovelRegistroAtendimentoHelper.add(consultarImovelRegistroAtendimentoHelper);
						}
						
						// Track No. 644 : Consultar Imóvel - Aba RA - Ordenação
						Collections.sort((List) colecaoConsultarImovelRegistroAtendimentoHelper, new Comparator() {
							public int compare(Object a, Object b) {
								String data1 = ((ConsultarImovelRegistroAtendimentoHelper) a).getDataAtendimento();
								String data2 = ((ConsultarImovelRegistroAtendimentoHelper) b).getDataAtendimento();
								
								data1 = data1.substring(6, 10) + data1.substring(3, 5) + data1.substring(0, 2);
								data2 = data2.substring(6, 10) + data2.substring(3, 5) + data2.substring(0, 2);
								
								Integer dtAtendimento1 = Integer.decode(data1);
								Integer dtAtendimento2 = Integer.decode(data2);

								return dtAtendimento2.compareTo(dtAtendimento1);
							}
						});
						
						sessao.setAttribute("colecaoConsultarImovelRegistroAtendimentoHelper", colecaoConsultarImovelRegistroAtendimentoHelper);
					}
					
					
					Collection colecaoOrdensServico = fachada.consultarDadosOrdensServicoSeletivas(new Integer(idImovelRegistroAtendimento.trim()));
					
					Collection colecaoOrdensServicoHelper  = null;
					
					if(colecaoOrdensServico != null &&
							!colecaoOrdensServico.isEmpty()){
						
						Iterator iteratorColecaoOrdensServico = colecaoOrdensServico.iterator();
						
						colecaoOrdensServicoHelper = new ArrayList();
						
						while (iteratorColecaoOrdensServico.hasNext()) {
							OrdemServico os = (OrdemServico) iteratorColecaoOrdensServico.next();
							
							OrdemServicoHelper ordemServicoHelper = new OrdemServicoHelper();

							//id registro atendimento
							if(os != null  && os.getId() != null ){
								ordemServicoHelper.setNumeroOrdemServico(os.getId().toString());
							}
							
							//descricao servico tipo
							if(os != null && os.getServicoTipo() != null){ 								
								ordemServicoHelper.setDescricaoServicoTipo(os.getServicoTipo().getDescricao());
							}
							sessao.removeAttribute("colecaoOrdemServicoHelper");
							//data de geracao
							if(os != null && os.getDataGeracao() != null ) {
								ordemServicoHelper.setDataGeracao(Util.formatarData(os.getDataGeracao()));
							}
							
							//Date Encerramento
							if(os != null && os.getDataEncerramento() != null ){
								
								ordemServicoHelper.setDataEncerramento(Util.formatarData(os.getDataEncerramento()));
							}
							
							//situacao
							if(os != null && os.getId() != null){
								ObterDescricaoSituacaoOSHelper obterDescricaoSituacaoOSHelper = 
									fachada.obterDescricaoSituacaoOS(os.getId());
								ordemServicoHelper.setSituacao(obterDescricaoSituacaoOSHelper.getDescricaoSituacao());
								
							}													
							
							//Motivo do encerramento
							if(os != null && os.getAtendimentoMotivoEncerramento() != null ){
								
								ordemServicoHelper.setParecerEncerramento(os.getAtendimentoMotivoEncerramento().getDescricao());
							}
							
								colecaoOrdensServicoHelper.add(ordemServicoHelper);
							
						}
						
						// Track No. 644 : Consultar Imóvel - Aba RA - Ordenação de OS
						Collections.sort((List) colecaoOrdensServicoHelper, new Comparator() {
							public int compare(Object a, Object b) {
								String data1 = ((OrdemServicoHelper) a).getDataGeracao();
								String data2 = ((OrdemServicoHelper) b).getDataGeracao();
								
								data1 = data1.substring(6, 10) + data1.substring(3, 5) + data1.substring(0, 2);
								data2 = data2.substring(6, 10) + data2.substring(3, 5) + data2.substring(0, 2);
								
								Integer dtGeracao1 = Integer.decode(data1);
								Integer dtGeracao2 = Integer.decode(data2);

								return dtGeracao2.compareTo(dtGeracao1);
							}
						});
					}
					
					sessao.setAttribute("colecaoOrdemServicoHelper", colecaoOrdensServicoHelper);
					
                }
            } else {
                httpServletRequest.setAttribute(
                        "idImovelRegistroAtendimentoNaoEncontrado", "true");
                consultarImovelActionForm.setMatriculaImovelRegistroAtendimento("IMÓVEL INEXISTENTE");
                
                //limpar os dados pesquisados
                sessao.removeAttribute("imovelRegistroAtendimento");
                sessao.removeAttribute("idImovelPrincipalAba");
                consultarImovelActionForm.setIdImovelDadosComplementares(null);
				consultarImovelActionForm.setIdImovelDadosCadastrais(null);
				consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
				consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
				consultarImovelActionForm.setIdImovelDebitos(null);
				consultarImovelActionForm.setIdImovelPagamentos(null);
				consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
				consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
				consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
				consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
				consultarImovelActionForm.setImovIdAnt(null);
            	consultarImovelActionForm.setSituacaoAguaRegistroAtendimento(null);
            	consultarImovelActionForm.setSituacaoEsgotoRegistroAtendimento(null);
            	sessao.removeAttribute("colecaoConsultarImovelRegistroAtendimentoHelper");
            	sessao.removeAttribute("colecaoOrdemServicoHelper");
            }
        }else{
        	 consultarImovelActionForm.setIdImovelRegistroAtendimento(idImovelRegistroAtendimento);

         	httpServletRequest.setAttribute(
                    "idImovelRegistroAtendimentoNaoEncontrado", null);

        	sessao.removeAttribute("imovelRegistroAtendimento");
        	sessao.removeAttribute("idImovelPrincipalAba");
        	
        	consultarImovelActionForm.setMatriculaImovelRegistroAtendimento(null);
        	consultarImovelActionForm.setSituacaoAguaRegistroAtendimento(null);
        	consultarImovelActionForm.setSituacaoEsgotoRegistroAtendimento(null);
        	sessao.removeAttribute("colecaoConsultarImovelRegistroAtendimentoHelper");
        	sessao.removeAttribute("colecaoOrdemServicoHelper");
        
        }

        return retorno;
    }

}
