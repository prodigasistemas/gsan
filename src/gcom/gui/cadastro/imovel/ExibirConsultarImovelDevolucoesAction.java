package gcom.gui.cadastro.imovel;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.DevolucaoHistorico;
import gcom.arrecadacao.FiltroDevolucao;
import gcom.arrecadacao.FiltroDevolucaoHistorico;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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

/**
 * 10° Aba - Registro de Atendimento
 * 
 * @author Rafael Santos
 * @since 21/09/2006
 */
public class ExibirConsultarImovelDevolucoesAction extends GcomAction {

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

        ActionForward retorno = actionMapping.findForward("consultarImovelDevolucoes");

        //Obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;

        //id do imovel da aba documento de cobranca
        String idImovelDevolucoesImovel = consultarImovelActionForm.getIdImovelDevolucoesImovel();
        String limparForm = httpServletRequest.getParameter("limparForm");
        
        String indicadorNovo = httpServletRequest.getParameter("indicadorNovo");
		String idImovelPrincipalAba = null;
		if(sessao.getAttribute("idImovelPrincipalAba") != null){
			idImovelPrincipalAba = (String)sessao.getAttribute("idImovelPrincipalAba");
		}          
                
        
        if(limparForm != null && !limparForm.equals("")){
            //limpar os dados 
        	httpServletRequest.setAttribute("idImovelDevolucoesImovelNaoEncontrado", null);

        	sessao.removeAttribute("imovelDevolucoesImovel");
        	sessao.removeAttribute("idImovelPrincipalAba");
            sessao.removeAttribute("colecaoDevolucaoImovelConta");
            sessao.removeAttribute("colecaoDevolucaoHistoricoImovelDebitoACobrar");
            sessao.removeAttribute("colecaoDevolucaoImovelDevolucaoValor");
            sessao.removeAttribute("colecaoDevolucaoHistoricoImovelDevolucaoValor");
            sessao.removeAttribute("colecaoDevolucaoHistoricoImovelConta");
            sessao.removeAttribute("colecaoDevolucaoImovelGuiaPagamento");
            sessao.removeAttribute("colecaoDevolucaoHistoricoImovelGuiaPagamento");
            sessao.removeAttribute("colecaoDevolucaoImovelDebitoACobrar");
            
            sessao.removeAttribute("qtdeDevContas");
            sessao.removeAttribute("qtdeDevGuiaPagamento");
            sessao.removeAttribute("qtdeDevDebitoACobrar");
            sessao.removeAttribute("qtdeDevDevolucaoValores");
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
        	
        	
        	consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
        	consultarImovelActionForm.setMatriculaImovelDevolucoesImovel(null);
        	consultarImovelActionForm.setSituacaoAguaDevolucoesImovel(null);
        	consultarImovelActionForm.setSituacaoEsgotoDevolucoesImovel(null);
        	sessao.removeAttribute("colecaoConsultarImovelDevolucoesImovelHelper");
            
        }else if( (idImovelDevolucoesImovel != null && !idImovelDevolucoesImovel.equalsIgnoreCase(""))
            	|| (idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase("")) ){
            	
        	if(idImovelDevolucoesImovel != null && !idImovelDevolucoesImovel.equalsIgnoreCase("")){
        		
        		
        		if(idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase("")){
            		
        			if(indicadorNovo != null && !indicadorNovo.equals("")){
            			
        				consultarImovelActionForm.setIdImovelDevolucoesImovel(idImovelDevolucoesImovel);            		

        				
        			}else if(!(idImovelDevolucoesImovel.equals(idImovelPrincipalAba))){
            			consultarImovelActionForm.setIdImovelDevolucoesImovel(idImovelPrincipalAba);            		
                		idImovelDevolucoesImovel = idImovelPrincipalAba;
            		}
            		
            		
            	}
        	}else if(idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase("")){
            		consultarImovelActionForm.setIdImovelDevolucoesImovel(idImovelPrincipalAba);            		
            		idImovelDevolucoesImovel = idImovelPrincipalAba;
            }	                	
        	
	        //Obtém a instância da Fachada
	        Fachada fachada = Fachada.getInstancia();
	        Imovel imovel = null;
	        //verifica se o objeto imovel é o mesmo ja pesquisado, para não precisar pesquisar mas.
	        boolean imovelNovoPesquisado = false;
	        if(sessao.getAttribute("imovelDevolucoesImovel") != null){
	        	imovel = (Imovel) sessao.getAttribute("imovelDevolucoesImovel");
	        	if(!(imovel.getId().toString().equals(idImovelDevolucoesImovel.trim()))){
	        		imovel = fachada.consultarImovelHistoricoFaturamento(new Integer(idImovelDevolucoesImovel.trim()));
	        		imovelNovoPesquisado = true;
	        	}
	        }else{
	        	imovel = fachada.consultarImovelHistoricoFaturamento(new Integer(idImovelDevolucoesImovel.trim()));
	        	imovelNovoPesquisado = true;
	        }
	
            if (imovel != null) {
                sessao.setAttribute("imovelDevolucoesImovel", imovel);
                sessao.setAttribute("idImovelPrincipalAba", imovel.getId().toString());
                consultarImovelActionForm.setIdImovelDevolucoesImovel(imovel.getId().toString());
               
				if (imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
					httpServletRequest.setAttribute("imovelExcluido", true);
				}

                //caso o imovel pesquisado seja diferente do pesquisado anterior ou seja a primeira vez que se esteja pesquisando
                if(imovelNovoPesquisado){
                	
                	//limpa campos para setar com valores do novo imóvel
                    sessao.removeAttribute("colecaoDevolucaoImovelConta");
                    sessao.removeAttribute("colecaoDevolucaoHistoricoImovelDebitoACobrar");
                    sessao.removeAttribute("colecaoDevolucaoImovelDevolucaoValor");
                    sessao.removeAttribute("colecaoDevolucaoHistoricoImovelDevolucaoValor");
                    sessao.removeAttribute("colecaoDevolucaoHistoricoImovelConta");
                    sessao.removeAttribute("colecaoDevolucaoImovelGuiaPagamento");
                    sessao.removeAttribute("colecaoDevolucaoHistoricoImovelGuiaPagamento");
                    sessao.removeAttribute("colecaoDevolucaoImovelDebitoACobrar");
                    
                    sessao.removeAttribute("qtdeDevContas");
                    sessao.removeAttribute("qtdeDevGuiaPagamento");
                    sessao.removeAttribute("qtdeDevDebitoACobrar");
                    sessao.removeAttribute("qtdeDevDevolucaoValores");
                    sessao.removeAttribute("imovelClientes");
                    
                    
                    consultarImovelActionForm.setIdImovelDadosComplementares(null);
        			consultarImovelActionForm.setIdImovelDadosCadastrais(null);
        			consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
        			consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
        			consultarImovelActionForm.setIdImovelDebitos(null);
        			consultarImovelActionForm.setIdImovelPagamentos(null);
        			consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
        			consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
        			consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
        			consultarImovelActionForm.setImovIdAnt(null);
        			consultarImovelActionForm.setSituacaoAguaDevolucoesImovel(null);
                	consultarImovelActionForm.setSituacaoEsgotoDevolucoesImovel(null);
                	
	            	//seta na tela a inscrição do imovel
	                httpServletRequest.setAttribute(
	                        "idImovelDevolucoesImovelNaoEncontrado", null);
	                
	                consultarImovelActionForm.setMatriculaImovelDevolucoesImovel(fachada.pesquisarInscricaoImovelExcluidoOuNao(new Integer(idImovelDevolucoesImovel.trim())));
	                
					//seta a situação de agua
					if(imovel.getLigacaoAguaSituacao() != null){
						consultarImovelActionForm.setSituacaoAguaDevolucoesImovel(imovel.getLigacaoAguaSituacao().getDescricao());
					}
					//seta a situação de esgoto
					if(imovel.getLigacaoEsgotoSituacao() != null){
						consultarImovelActionForm.setSituacaoEsgotoDevolucoesImovel(imovel.getLigacaoEsgotoSituacao().getDescricao());
					}
					
				    FiltroDevolucao filtroDevolucao = new FiltroDevolucao();
				    filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("avisoBancario.arrecadador");
					filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
					filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao");
					filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.documentoTipo");
					filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.conta");
					filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.conta.clienteContas");
					filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.guiaPagamento.debitoTipo");
					filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAtual");
					filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAnterior");
					filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral");
					filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo");
					filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel");
				    filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("cliente");
					
					filtroDevolucao.adicionarParametro(new ParametroSimples("imovel.id",idImovelDevolucoesImovel));
					
					Collection colecaoImoveisDevolucoes = fachada.pesquisarDevolucao(filtroDevolucao);
					
					if (colecaoImoveisDevolucoes != null
							&& !colecaoImoveisDevolucoes.isEmpty()) {
						sessao.setAttribute("colecaoImoveisDevolucoes",
								colecaoImoveisDevolucoes);
					}
				
					FiltroDevolucaoHistorico filtroDevolucaoHistorico = new FiltroDevolucaoHistorico();	
					filtroDevolucaoHistorico.adicionarCaminhoParaCarregamentoEntidade("avisoBancario.arrecadador");
					filtroDevolucaoHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
					filtroDevolucaoHistorico.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao");
					filtroDevolucaoHistorico.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.documentoTipo");
					filtroDevolucaoHistorico.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.conta");
					filtroDevolucaoHistorico.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.guiaPagamento.debitoTipo");
					filtroDevolucaoHistorico.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAtual");
					filtroDevolucaoHistorico.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAnterior");
					filtroDevolucaoHistorico.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo");
					filtroDevolucaoHistorico.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel");
					filtroDevolucaoHistorico.adicionarCaminhoParaCarregamentoEntidade("cliente");
					
					
					filtroDevolucaoHistorico.adicionarParametro(new ParametroSimples("imovel.id",idImovelDevolucoesImovel));
					
					Collection colecaoImoveisDevolucoesHistorico = fachada.pesquisarDevolucaoHistorico(filtroDevolucaoHistorico);
					
					if (colecaoImoveisDevolucoesHistorico != null
							&& !colecaoImoveisDevolucoesHistorico.isEmpty()) {
						sessao.setAttribute("colecaoImoveisDevolucoesHistorico",
								colecaoImoveisDevolucoesHistorico);
					}
					
					
					Collection<Devolucao> colecaoDevolucaoImovelConta = new ArrayList<Devolucao>();
					Collection<Devolucao> colecaoDevolucaoImovelGuiaPagamento = new ArrayList<Devolucao>();
					Collection<Devolucao> colecaoDevolucaoImovelDebitoACobrar = new ArrayList<Devolucao>();
					Collection<Devolucao> colecaoDevolucaoImovelDevolucaoValor = new ArrayList<Devolucao>();
					Collection<DevolucaoHistorico> colecaoDevolucaoHistoricoImovelConta = new ArrayList<DevolucaoHistorico>();
					Collection<DevolucaoHistorico> colecaoDevolucaoHistoricoImovelGuiaPagamento = new ArrayList<DevolucaoHistorico>();
					Collection<DevolucaoHistorico> colecaoDevolucaoHistoricoImovelDebitoACobrar = new ArrayList<DevolucaoHistorico>();
					Collection<DevolucaoHistorico> colecaoDevolucaoHistoricoImovelDevolucaoValor = new ArrayList<DevolucaoHistorico>();
					
					
					Integer qtdeDevContas = 0;
					Integer qtdeDevGuiaPagamento = 0;
					Integer qtdeDevDebitoACobrar = 0;
					Integer qtdeDevDevolucaoValores = 0;					
					
					// Consultar Devolução do Imóvel

					if (colecaoImoveisDevolucoes != null ||
							colecaoImoveisDevolucoesHistorico != null) {

						//colecaoDevolucao = colecaoImoveisDevolucoes;
						
						//colecaoDevolucaoHistorico = colecaoImoveisDevolucoesHistorico;

						if (colecaoImoveisDevolucoes != null){
							Iterator colecaoDevolucaoIterator = colecaoImoveisDevolucoes.iterator();
				
							while (colecaoDevolucaoIterator.hasNext()) {
				
								Devolucao devolucao = ((Devolucao) colecaoDevolucaoIterator
										.next());
				

                                 if (devolucao.getGuiaDevolucao() != null) {

                				    //devolucao.setGuiaDevolucao(fachada.pesquisarGuiaDevolucao(devolucao.getGuiaDevolucao().getId()));
                				    
                				    if (devolucao.getGuiaDevolucao().getDocumentoTipo() != null) {
									
  									   if (devolucao.getGuiaDevolucao().getDocumentoTipo().getId()
											.equals(DocumentoTipo.CONTA)) {
				
										  colecaoDevolucaoImovelConta.add(devolucao);
				
  									      } else if (devolucao.getGuiaDevolucao().getDocumentoTipo().getId().
  									    		  equals(DocumentoTipo.DEBITO_A_COBRAR)) {
				
 										    colecaoDevolucaoImovelDebitoACobrar.add(devolucao);
				
									      } else if (devolucao.getGuiaDevolucao().getDocumentoTipo()
											.getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
				
										    colecaoDevolucaoImovelGuiaPagamento.add(devolucao);
				
  									      } else if (devolucao.getGuiaDevolucao().getDocumentoTipo()
											.getId().equals(DocumentoTipo.DEVOLUCAO_VALOR)) {
										    colecaoDevolucaoImovelDevolucaoValor.add(devolucao);
   									    }
  									   
									}
  									   
								} else {
									if (devolucao.getAnoMesReferenciaDevolucao() != null) {
										colecaoDevolucaoImovelConta.add(devolucao);
									} else {
										colecaoDevolucaoImovelDevolucaoValor.add(devolucao);
									}
								}
							}	
				
							}

						if (colecaoImoveisDevolucoesHistorico!= null){
						
							Iterator colecaoDevolucaoHistoricoIterator = colecaoImoveisDevolucoesHistorico.iterator();
				
							while (colecaoDevolucaoHistoricoIterator.hasNext()) {
				
								DevolucaoHistorico devolucaoHistorico = ((DevolucaoHistorico) colecaoDevolucaoHistoricoIterator
										.next());
				
								if (devolucaoHistorico.getGuiaDevolucao() != null) {
				
									if (devolucaoHistorico.getGuiaDevolucao().getDocumentoTipo().getId()
											.equals(DocumentoTipo.CONTA)) {
				
										colecaoDevolucaoHistoricoImovelConta.add(devolucaoHistorico);
				
									} else if (devolucaoHistorico.getGuiaDevolucao().getDocumentoTipo()
											.getId().equals(DocumentoTipo.DEBITO_A_COBRAR)) {
				
										colecaoDevolucaoHistoricoImovelDebitoACobrar.add(devolucaoHistorico);
				
									} else if (devolucaoHistorico.getGuiaDevolucao().getDocumentoTipo()
											.getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
				
										colecaoDevolucaoHistoricoImovelGuiaPagamento.add(devolucaoHistorico);
				
									} else if (devolucaoHistorico.getGuiaDevolucao().getDocumentoTipo()
											.getId().equals(DocumentoTipo.DEVOLUCAO_VALOR)) {
										colecaoDevolucaoHistoricoImovelDevolucaoValor.add(devolucaoHistorico);
									}
								} else {
									if (devolucaoHistorico.getAnoMesReferenciaDevolucao() != null) {
										colecaoDevolucaoHistoricoImovelConta.add(devolucaoHistorico);
									} else {
										colecaoDevolucaoHistoricoImovelDevolucaoValor.add(devolucaoHistorico);
									}
								}
							}
						}

						
						if (colecaoDevolucaoImovelConta != null
								&& !colecaoDevolucaoImovelConta.isEmpty()) {

							// [SB0005] - Ordenar Devoluções de Acordo com o Tipo de
							// Documento
							// Ordena a coleção pela localidade, imovel, ano/mês referência
							// da devolução e
							// pela data da devolução
							Collections.sort((List) colecaoDevolucaoImovelConta,
									new Comparator() {
										public int compare(Object a, Object b) {

											// Cria as chaves usadas para fazer as
											// comparações

											String chave1 = "";
											String chave2 = "";

											// Verifica se os campos são nulos, em caso
											// afirmativo seta na chave um espaço em branco
											// para servir de diferenciação entre as
											// strings, evitando assim que uma devolução sem
											// localidade venha no meio de outras com
											// localidade e em caso negativo coloca-o na
											// chave

											// Localidade
											if (((Devolucao) a).getLocalidade() != null) {
												chave1 = chave1
														+ ((Devolucao) a).getLocalidade()
																.getId().toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Imóvel
											if (((Devolucao) a).getImovel() != null) {
												chave1 = chave1
														+ ((Devolucao) a).getImovel()
																.getId().toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Ano/mês referência da devolução
											if (((Devolucao) a)
													.getAnoMesReferenciaDevolucao() != null) {
												chave1 = chave1
														+ ((Devolucao) a)
																.getAnoMesReferenciaDevolucao()
																.toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Data da devolução
											if (((Devolucao) a).getDataDevolucao() != null) {
												chave1 = chave1
														+ Util
																.recuperaDataInvertida(((Devolucao) a)
																		.getDataDevolucao());
											} else {
												chave1 = chave1 + " ";
											}

											// Localidade
											if (((Devolucao) b).getLocalidade() != null) {
												chave2 = chave2
														+ ((Devolucao) b).getLocalidade()
																.getId().toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Imóvel
											if (((Devolucao) b).getImovel() != null) {
												chave2 = chave2
														+ ((Devolucao) b).getImovel()
																.getId().toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Ano/mês referência da devolução
											if (((Devolucao) b)
													.getAnoMesReferenciaDevolucao() != null) {
												chave2 = chave2
														+ ((Devolucao) b)
																.getAnoMesReferenciaDevolucao()
																.toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Data da devolução
											if (((Devolucao) b).getDataDevolucao() != null) {
												chave2 = chave2
														+ Util
																.recuperaDataInvertida(((Devolucao) a)
																		.getDataDevolucao());
											} else {
												chave2 = chave2 + " ";
											}

											return chave1.compareTo(chave2);

										}

									});

							sessao.setAttribute("colecaoDevolucaoImovelConta",
									colecaoDevolucaoImovelConta);
							
							qtdeDevContas = colecaoDevolucaoImovelConta.size();
						}
						
						if (colecaoDevolucaoHistoricoImovelConta != null
								&& !colecaoDevolucaoHistoricoImovelConta.isEmpty()) {

							// [SB0005] - Ordenar Devoluções Histórico de Acordo com o Tipo de
							// Documento
							// Ordena a coleção pela localidade, imovel, ano/mês referência
							// da devolução e
							// pela data da devolução
							Collections.sort((List) colecaoDevolucaoHistoricoImovelConta,
									new Comparator() {
										public int compare(Object a, Object b) {

											// Cria as chaves usadas para fazer as
											// comparações

											String chave1 = "";
											String chave2 = "";

											// Verifica se os campos são nulos, em caso
											// afirmativo seta na chave um espaço em branco
											// para servir de diferenciação entre as
											// strings, evitando assim que uma devolução sem
											// localidade venha no meio de outras com
											// localidade e em caso negativo coloca-o na
											// chave

											// Localidade
											if (((DevolucaoHistorico) a).getLocalidade() != null) {
												chave1 = chave1
														+ ((DevolucaoHistorico) a).getLocalidade()
																.getId().toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Imóvel
											if (((DevolucaoHistorico) a).getImovel() != null) {
												chave1 = chave1
														+ ((DevolucaoHistorico) a).getImovel()
																.getId().toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Ano/mês referência da devolução
											if (((DevolucaoHistorico) a)
													.getAnoMesReferenciaDevolucao() != null) {
												chave1 = chave1
														+ ((DevolucaoHistorico) a)
																.getAnoMesReferenciaDevolucao()
																.toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Data da devolução
											if (((DevolucaoHistorico) a).getDataDevolucao() != null) {
												chave1 = chave1
														+ Util
																.recuperaDataInvertida(((DevolucaoHistorico) a)
																		.getDataDevolucao());
											} else {
												chave1 = chave1 + " ";
											}

											// Localidade
											if (((DevolucaoHistorico) b).getLocalidade() != null) {
												chave2 = chave2
														+ ((DevolucaoHistorico) b).getLocalidade()
																.getId().toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Imóvel
											if (((DevolucaoHistorico) b).getImovel() != null) {
												chave2 = chave2
														+ ((DevolucaoHistorico) b).getImovel()
																.getId().toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Ano/mês referência da devolução
											if (((DevolucaoHistorico) b)
													.getAnoMesReferenciaDevolucao() != null) {
												chave2 = chave2
														+ ((DevolucaoHistorico) b)
																.getAnoMesReferenciaDevolucao()
																.toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Data da devolução
											if (((DevolucaoHistorico) b).getDataDevolucao() != null) {
												chave2 = chave2
														+ Util
																.recuperaDataInvertida(((DevolucaoHistorico) a)
																		.getDataDevolucao());
											} else {
												chave2 = chave2 + " ";
											}

											return chave1.compareTo(chave2);

										}

									});

							sessao.setAttribute("colecaoDevolucaoHistoricoImovelConta",
									colecaoDevolucaoHistoricoImovelConta);
							qtdeDevContas = qtdeDevContas + colecaoDevolucaoHistoricoImovelConta.size();
						}
						
						
						if (colecaoDevolucaoImovelGuiaPagamento != null
								&& !colecaoDevolucaoImovelGuiaPagamento.isEmpty()) {
							// [SB0005] - Ordenar Devoluções de Acordo com o Tipo de
							// Documento
							// Ordena a coleção pela localidade, imovel, tipo de débito e
							// pela data da devolução
							Collections.sort((List) colecaoDevolucaoImovelGuiaPagamento,
									new Comparator() {
										public int compare(Object a, Object b) {

											// Cria as chaves usadas para fazer as
											// comparações

											String chave1 = "";
											String chave2 = "";

											// Verifica se os campos são nulos, em caso
											// afirmativo seta na chave um espaço em branco
											// para servir de diferenciação entre as
											// strings, evitando assim que uma devolução sem
											// localidade venha no meio de outras com
											// localidade e em caso negativo coloca-o na
											// chave

											// Localidade
											if (((Devolucao) a).getLocalidade() != null) {
												chave1 = chave1
														+ ((Devolucao) a).getLocalidade()
																.getId().toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Imóvel
											if (((Devolucao) a).getImovel() != null) {
												chave1 = chave1
														+ ((Devolucao) a).getImovel()
																.getId().toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Tipo de Débito
											if (((Devolucao) a).getDebitoTipo() != null) {
												chave1 = chave1
														+ ((Devolucao) a).getDebitoTipo()
																.getId().toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Data da devolução
											if (((Devolucao) a).getDataDevolucao() != null) {
												chave1 = chave1
														+ Util
																.recuperaDataInvertida(((Devolucao) a)
																		.getDataDevolucao());
											} else {
												chave1 = chave1 + " ";
											}

											// Localidade
											if (((Devolucao) b).getLocalidade() != null) {
												chave2 = chave2
														+ ((Devolucao) b).getLocalidade()
																.getId().toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Imóvel
											if (((Devolucao) b).getImovel() != null) {
												chave2 = chave2
														+ ((Devolucao) b).getImovel()
																.getId().toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Tipo de Débito
											if (((Devolucao) b).getDebitoTipo() != null) {
												chave2 = chave2
														+ ((Devolucao) b).getDebitoTipo()
																.getId().toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Data da devolução
											if (((Devolucao) b).getDataDevolucao() != null) {
												chave2 = chave2
														+ Util
																.recuperaDataInvertida(((Devolucao) a)
																		.getDataDevolucao());
											} else {
												chave2 = chave2 + " ";
											}

											return chave1.compareTo(chave2);

										}
									});
							sessao.setAttribute(
									"colecaoDevolucaoImovelGuiaPagamento",
									colecaoDevolucaoImovelGuiaPagamento);
							qtdeDevGuiaPagamento = colecaoDevolucaoImovelGuiaPagamento.size();

						}
						
						if (colecaoDevolucaoHistoricoImovelGuiaPagamento != null
								&& !colecaoDevolucaoHistoricoImovelGuiaPagamento.isEmpty()) {
							// [SB0005] - Ordenar Devoluções Histórico de Acordo com o Tipo de Documento
							// Ordena a coleção pela localidade, imovel, tipo de débito e
							// pela data da devolução
							Collections.sort((List) colecaoDevolucaoHistoricoImovelGuiaPagamento,
									new Comparator() {
										public int compare(Object a, Object b) {

											// Cria as chaves usadas para fazer as
											// comparações

											String chave1 = "";
											String chave2 = "";

											// Verifica se os campos são nulos, em caso
											// afirmativo seta na chave um espaço em branco
											// para servir de diferenciação entre as
											// strings, evitando assim que uma devolução sem
											// localidade venha no meio de outras com
											// localidade e em caso negativo coloca-o na
											// chave

											// Localidade
											if (((DevolucaoHistorico) a).getLocalidade() != null) {
												chave1 = chave1
														+ ((DevolucaoHistorico) a).getLocalidade()
																.getId().toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Imóvel
											if (((DevolucaoHistorico) a).getImovel() != null) {
												chave1 = chave1
														+ ((DevolucaoHistorico) a).getImovel()
																.getId().toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Tipo de Débito
											if (((DevolucaoHistorico) a).getDebitoTipo() != null) {
												chave1 = chave1
														+ ((DevolucaoHistorico) a).getDebitoTipo()
																.getId().toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Data da devolução
											if (((DevolucaoHistorico) a).getDataDevolucao() != null) {
												chave1 = chave1
														+ Util
																.recuperaDataInvertida(((DevolucaoHistorico) a)
																		.getDataDevolucao());
											} else {
												chave1 = chave1 + " ";
											}

											// Localidade
											if (((DevolucaoHistorico) b).getLocalidade() != null) {
												chave2 = chave2
														+ ((DevolucaoHistorico) b).getLocalidade()
																.getId().toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Imóvel
											if (((DevolucaoHistorico) b).getImovel() != null) {
												chave2 = chave2
														+ ((DevolucaoHistorico) b).getImovel()
																.getId().toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Tipo de Débito
											if (((DevolucaoHistorico) b).getDebitoTipo() != null) {
												chave2 = chave2
														+ ((DevolucaoHistorico) b).getDebitoTipo()
																.getId().toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Data da devolução
											if (((DevolucaoHistorico) b).getDataDevolucao() != null) {
												chave2 = chave2
														+ Util
																.recuperaDataInvertida(((DevolucaoHistorico) a)
																		.getDataDevolucao());
											} else {
												chave2 = chave2 + " ";
											}

											return chave1.compareTo(chave2);

										}
									});
							sessao.setAttribute(
									"colecaoDevolucaoHistoricoImovelGuiaPagamento",
									colecaoDevolucaoHistoricoImovelGuiaPagamento);
							qtdeDevGuiaPagamento = qtdeDevGuiaPagamento + colecaoDevolucaoHistoricoImovelGuiaPagamento.size();
							
						}
						
						if (colecaoDevolucaoImovelDebitoACobrar != null
								&& !colecaoDevolucaoImovelDebitoACobrar.isEmpty()) {
							// [SB0005] - Ordenar Devoluções de Acordo com o Tipo de
							// Documento
							// Ordena a coleção pela localidade, imovel, tipo de débito e
							// pela data da devolução
							Collections.sort((List) colecaoDevolucaoImovelDebitoACobrar,
									new Comparator() {
										public int compare(Object a, Object b) {

											// Cria as chaves usadas para fazer as
											// comparações

											String chave1 = "";
											String chave2 = "";

											// Verifica se os campos são nulos, em caso
											// afirmativo seta na chave um espaço em branco
											// para servir de diferenciação entre as
											// strings, evitando assim que uma devolução sem
											// localidade venha no meio de outras com
											// localidade e em caso negativo coloca-o na
											// chave

											// Localidade
											if (((Devolucao) a).getLocalidade() != null) {
												chave1 = chave1
														+ ((Devolucao) a).getLocalidade()
																.getId().toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Imóvel
											if (((Devolucao) a).getImovel() != null) {
												chave1 = chave1
														+ ((Devolucao) a).getImovel()
																.getId().toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Tipo de Débito
											if (((Devolucao) a).getDebitoTipo() != null) {
												chave1 = chave1
														+ ((Devolucao) a).getDebitoTipo()
																.getId();
											} else {
												chave1 = chave1 + " ";
											}

											// Data da devolução
											if (((Devolucao) a).getDataDevolucao() != null) {
												chave1 = chave1
														+ Util
																.recuperaDataInvertida(((Devolucao) a)
																		.getDataDevolucao());
											} else {
												chave1 = chave1 + " ";
											}

											// Localidade
											if (((Devolucao) b).getLocalidade() != null) {
												chave2 = chave2
														+ ((Devolucao) b).getLocalidade()
																.getId().toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Imóvel
											if (((Devolucao) b).getImovel() != null) {
												chave2 = chave2
														+ ((Devolucao) b).getImovel()
																.getId().toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Tipo de Débito
											if (((Devolucao) b).getDebitoTipo() != null) {
												chave2 = chave2
														+ ((Devolucao) b).getDebitoTipo()
																.getId();
											} else {
												chave2 = chave2 + " ";
											}

											// Data da Devolução
											if (((Devolucao) b).getDataDevolucao() != null) {
												chave2 = chave2
														+ Util
																.recuperaDataInvertida(((Devolucao) a)
																		.getDataDevolucao());
											} else {
												chave2 = chave2 + " ";
											}

											return chave1.compareTo(chave2);

										}
									});
							sessao.setAttribute(
									"colecaoDevolucaoImovelDebitoACobrar",
									colecaoDevolucaoImovelDebitoACobrar);
							
							qtdeDevDebitoACobrar = colecaoDevolucaoImovelDebitoACobrar.size();
						}
						
						
						if (colecaoDevolucaoHistoricoImovelDebitoACobrar != null
								&& !colecaoDevolucaoHistoricoImovelDebitoACobrar.isEmpty()) {
							// [SB0005] - Ordenar Devoluções Histórico de Acordo com o Tipo de
							// Documento
							// Ordena a coleção pela localidade, imovel, tipo de débito e
							// pela data da devolução
							Collections.sort((List) colecaoDevolucaoHistoricoImovelDebitoACobrar,
									new Comparator() {
										public int compare(Object a, Object b) {

											// Cria as chaves usadas para fazer as
											// comparações

											String chave1 = "";
											String chave2 = "";

											// Verifica se os campos são nulos, em caso
											// afirmativo seta na chave um espaço em branco
											// para servir de diferenciação entre as
											// strings, evitando assim que uma devolução sem
											// localidade venha no meio de outras com
											// localidade e em caso negativo coloca-o na
											// chave

											// Localidade
											if (((DevolucaoHistorico) a).getLocalidade() != null) {
												chave1 = chave1
														+ ((DevolucaoHistorico) a).getLocalidade()
																.getId().toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Imóvel
											if (((DevolucaoHistorico) a).getImovel() != null) {
												chave1 = chave1
														+ ((DevolucaoHistorico) a).getImovel()
																.getId().toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Tipo de Débito
											if (((DevolucaoHistorico) a).getDebitoTipo() != null) {
												chave1 = chave1
														+ ((DevolucaoHistorico) a).getDebitoTipo()
																.getId();
											} else {
												chave1 = chave1 + " ";
											}

											// Data da devolução
											if (((DevolucaoHistorico) a).getDataDevolucao() != null) {
												chave1 = chave1
														+ Util
																.recuperaDataInvertida(((DevolucaoHistorico) a)
																		.getDataDevolucao());
											} else {
												chave1 = chave1 + " ";
											}

											// Localidade
											if (((DevolucaoHistorico) b).getLocalidade() != null) {
												chave2 = chave2
														+ ((DevolucaoHistorico) b).getLocalidade()
																.getId().toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Imóvel
											if (((DevolucaoHistorico) b).getImovel() != null) {
												chave2 = chave2
														+ ((DevolucaoHistorico) b).getImovel()
																.getId().toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Tipo de Débito
											if (((DevolucaoHistorico) b).getDebitoTipo() != null) {
												chave2 = chave2
														+ ((DevolucaoHistorico) b).getDebitoTipo()
																.getId();
											} else {
												chave2 = chave2 + " ";
											}

											// Data da Devolução
											if (((DevolucaoHistorico) b).getDataDevolucao() != null) {
												chave2 = chave2
														+ Util
																.recuperaDataInvertida(((DevolucaoHistorico) a)
																		.getDataDevolucao());
											} else {
												chave2 = chave2 + " ";
											}

											return chave1.compareTo(chave2);

										}
									});
							sessao.setAttribute(
									"colecaoDevolucaoHistoricoImovelDebitoACobrar",
									colecaoDevolucaoHistoricoImovelDebitoACobrar);
							qtdeDevDebitoACobrar = qtdeDevDebitoACobrar + colecaoDevolucaoHistoricoImovelDebitoACobrar.size();
							
						}

						if (colecaoDevolucaoImovelDevolucaoValor != null
								&& !colecaoDevolucaoImovelDevolucaoValor.isEmpty()) {

							// [SB0005] - Ordenar Devoluções de Acordo com o Tipo de
							// Documento
							// Ordena a coleção pela localidade, imovel, tipo de débito e
							// pela data da devolução
							Collections.sort((List) colecaoDevolucaoImovelDevolucaoValor,
									new Comparator() {
										public int compare(Object a, Object b) {

											// Cria as chaves usadas para fazer as
											// comparações

											String chave1 = "";
											String chave2 = "";

											// Verifica se os campos são nulos, em caso
											// afirmativo seta na chave um espaço em branco
											// para servir de diferenciação entre as
											// strings, evitando assim que uma devolução sem
											// localidade venha no meio de outras com
											// localidade e em caso negativo coloca-o na
											// chave

											// Localidade
											if (((Devolucao) a).getLocalidade() != null) {
												chave1 = chave1
														+ ((Devolucao) a).getLocalidade()
																.getId().toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Imóvel
											if (((Devolucao) a).getImovel() != null) {
												chave1 = chave1
														+ ((Devolucao) a).getImovel()
																.getId().toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Tipo de Débito
											if (((Devolucao) a).getDebitoTipo() != null) {
												chave1 = chave1
														+ ((Devolucao) a).getDebitoTipo()
																.getId();
											} else {
												chave1 = chave1 + " ";
											}

											// Data da devolução
											if (((Devolucao) a).getDataDevolucao() != null) {
												chave1 = chave1
														+ Util
																.recuperaDataInvertida(((Devolucao) a)
																		.getDataDevolucao());
											} else {
												chave1 = chave1 + " ";
											}

											// Localidade
											if (((Devolucao) b).getLocalidade() != null) {
												chave2 = chave2
														+ ((Devolucao) b).getLocalidade()
																.getId().toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Imóvel
											if (((Devolucao) b).getImovel() != null) {
												chave2 = chave2
														+ ((Devolucao) b).getImovel()
																.getId().toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Tipo de Débito
											if (((Devolucao) b).getDebitoTipo() != null) {
												chave2 = chave2
														+ ((Devolucao) b).getDebitoTipo()
																.getId();
											} else {
												chave2 = chave2 + " ";
											}

											// Data da devolução
											if (((Devolucao) b).getDataDevolucao() != null) {
												chave2 = chave2
														+ Util
																.recuperaDataInvertida(((Devolucao) a)
																		.getDataDevolucao());
											} else {
												chave2 = chave2 + " ";
											}

											return chave1.compareTo(chave2);

										}
									});
							sessao.setAttribute(
									"colecaoDevolucaoImovelDevolucaoValor",
									colecaoDevolucaoImovelDevolucaoValor);
							qtdeDevDevolucaoValores = colecaoDevolucaoImovelDevolucaoValor.size();
						}

						if (colecaoDevolucaoHistoricoImovelDevolucaoValor != null
								&& !colecaoDevolucaoHistoricoImovelDevolucaoValor.isEmpty()) {

							// [SB0005] - Ordenar Devoluções Histórico de Acordo com o Tipo de
							// Documento
							// Ordena a coleção pela localidade, imovel, tipo de débito e
							// pela data da devolução
							Collections.sort((List) colecaoDevolucaoHistoricoImovelDevolucaoValor,
									new Comparator() {
										public int compare(Object a, Object b) {

											// Cria as chaves usadas para fazer as
											// comparações

											String chave1 = "";
											String chave2 = "";

											// Verifica se os campos são nulos, em caso
											// afirmativo seta na chave um espaço em branco
											// para servir de diferenciação entre as
											// strings, evitando assim que uma devolução sem
											// localidade venha no meio de outras com
											// localidade e em caso negativo coloca-o na
											// chave

											// Localidade
											if (((DevolucaoHistorico) a).getLocalidade() != null) {
												chave1 = chave1
														+ ((DevolucaoHistorico) a).getLocalidade()
																.getId().toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Imóvel
											if (((DevolucaoHistorico) a).getImovel() != null) {
												chave1 = chave1
														+ ((DevolucaoHistorico) a).getImovel()
																.getId().toString();
											} else {
												chave1 = chave1 + " ";
											}

											// Tipo de Débito
											if (((DevolucaoHistorico) a).getDebitoTipo() != null) {
												chave1 = chave1
														+ ((DevolucaoHistorico) a).getDebitoTipo()
																.getId();
											} else {
												chave1 = chave1 + " ";
											}

											// Data da devolução
											if (((DevolucaoHistorico) a).getDataDevolucao() != null) {
												chave1 = chave1
														+ Util
																.recuperaDataInvertida(((DevolucaoHistorico) a)
																		.getDataDevolucao());
											} else {
												chave1 = chave1 + " ";
											}

											// Localidade
											if (((DevolucaoHistorico) b).getLocalidade() != null) {
												chave2 = chave2
														+ ((DevolucaoHistorico) b).getLocalidade()
																.getId().toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Imóvel
											if (((DevolucaoHistorico) b).getImovel() != null) {
												chave2 = chave2
														+ ((DevolucaoHistorico) b).getImovel()
																.getId().toString();
											} else {
												chave2 = chave2 + " ";
											}

											// Tipo de Débito
											if (((DevolucaoHistorico) b).getDebitoTipo() != null) {
												chave2 = chave2
														+ ((DevolucaoHistorico) b).getDebitoTipo()
																.getId();
											} else {
												chave2 = chave2 + " ";
											}

											// Data da devolução
											if (((DevolucaoHistorico) b).getDataDevolucao() != null) {
												chave2 = chave2
														+ Util
																.recuperaDataInvertida(((DevolucaoHistorico) a)
																		.getDataDevolucao());
											} else {
												chave2 = chave2 + " ";
											}

											return chave1.compareTo(chave2);

										}
									});
							sessao.setAttribute(
									"colecaoDevolucaoHistoricoImovelDevolucaoValor",
									colecaoDevolucaoHistoricoImovelDevolucaoValor);
							
							qtdeDevDevolucaoValores = qtdeDevDevolucaoValores + colecaoDevolucaoHistoricoImovelDevolucaoValor.size();
						}

						
					}
					
					sessao.setAttribute("qtdeDevContas",qtdeDevContas);
					sessao.setAttribute("qtdeDevGuiaPagamento",qtdeDevGuiaPagamento);
					sessao.setAttribute("qtdeDevDebitoACobrar",qtdeDevDebitoACobrar);
					sessao.setAttribute("qtdeDevDevolucaoValores",qtdeDevDevolucaoValores);
					
					
					
					
                }
            } else {
                httpServletRequest.setAttribute(
                        "idImovelDevolucoesImovelNaoEncontrado", "true");
                consultarImovelActionForm.setMatriculaImovelDevolucoesImovel("IMÓVEL INEXISTENTE");
                
                //limpar os dados pesquisados
                sessao.removeAttribute("imovelDevolucoesImovel");
                sessao.removeAttribute("idImovelPrincipalAba");
                
                sessao.removeAttribute("colecaoDevolucaoImovelConta");
                sessao.removeAttribute("colecaoDevolucaoHistoricoImovelDebitoACobrar");
                sessao.removeAttribute("colecaoDevolucaoImovelDevolucaoValor");
                sessao.removeAttribute("colecaoDevolucaoHistoricoImovelDevolucaoValor");
                sessao.removeAttribute("colecaoDevolucaoHistoricoImovelConta");
                sessao.removeAttribute("colecaoDevolucaoImovelGuiaPagamento");
                sessao.removeAttribute("colecaoDevolucaoHistoricoImovelGuiaPagamento");
                sessao.removeAttribute("colecaoDevolucaoImovelDebitoACobrar");
            	
                sessao.removeAttribute("qtdeDevContas");
                sessao.removeAttribute("qtdeDevGuiaPagamento");
                sessao.removeAttribute("qtdeDevDebitoACobrar");
                sessao.removeAttribute("qtdeDevDevolucaoValores");
				
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
                
            	consultarImovelActionForm.setSituacaoAguaDevolucoesImovel(null);
            	consultarImovelActionForm.setSituacaoEsgotoDevolucoesImovel(null);
            	
            }
        }else{
        	 consultarImovelActionForm.setIdImovelDevolucoesImovel(idImovelDevolucoesImovel);

         	httpServletRequest.setAttribute(
                    "idImovelDevolucoesImovelNaoEncontrado", null);

        	sessao.removeAttribute("imovelDevolucoesImovel");
        	sessao.removeAttribute("idImovelPrincipalAba");
            sessao.removeAttribute("colecaoDevolucaoImovelConta");
            sessao.removeAttribute("colecaoDevolucaoHistoricoImovelDebitoACobrar");
            sessao.removeAttribute("colecaoDevolucaoImovelDevolucaoValor");
            sessao.removeAttribute("colecaoDevolucaoHistoricoImovelDevolucaoValor");
            sessao.removeAttribute("colecaoDevolucaoHistoricoImovelConta");
            sessao.removeAttribute("colecaoDevolucaoImovelGuiaPagamento");
            sessao.removeAttribute("colecaoDevolucaoHistoricoImovelGuiaPagamento");
            sessao.removeAttribute("colecaoDevolucaoImovelDebitoACobrar");        	
        	
            sessao.removeAttribute("qtdeDevContas");
            sessao.removeAttribute("qtdeDevGuiaPagamento");
            sessao.removeAttribute("qtdeDevDebitoACobrar");
            sessao.removeAttribute("qtdeDevDevolucaoValores");
            
            
        	consultarImovelActionForm.setMatriculaImovelDevolucoesImovel(null);
        	consultarImovelActionForm.setSituacaoAguaDevolucoesImovel(null);
        	consultarImovelActionForm.setSituacaoEsgotoDevolucoesImovel(null);
        	sessao.removeAttribute("colecaoConsultarImovelDevolucoesImovelHelper");
        
        }

        return retorno;
    }

}
