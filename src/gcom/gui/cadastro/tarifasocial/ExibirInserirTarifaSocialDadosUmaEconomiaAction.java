package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovelEconomia;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author rodrigo
 */
public class ExibirInserirTarifaSocialDadosUmaEconomiaAction extends GcomAction {
	
	
    /**
     * < <Descrição do método>>
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
                .findForward("inserirTarifaSocialUmaEconomia");

        // Instancia da Fachada
        Fachada fachada = Fachada.getInstancia();

        //Pega uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        //Pega uma instancia do actionform
        DynaValidatorForm inserirTarifaSocialActionForm = (DynaValidatorForm) actionForm;

        String valorConfirmacao = httpServletRequest.getParameter("confirmado");
        
        if (valorConfirmacao == null || valorConfirmacao.equals("")) {
	        
	        //Pega o id do imovel selecionado
	        String idImovel = (String) inserirTarifaSocialActionForm
	                .get("idImovel");
	        
	        Collection colecaoTarifaSocialDadoEconomia = null;
	
	        //Faz a verificação de [FS00004]
	        fachada.verificarProprietarioImovel(new Integer(idImovel));
	
	        //Faz a verificação de [FS0005]
	        Cliente cliente = fachada.verificarUsuarioImovel(new Integer(idImovel));
	        
			// Realiza uma pesquisa pelos parametros fornecidos
			Collection clienteImoveis = fachada.pesquisarClienteImovelPeloClienteTarifaSocial(cliente.getId());
	        
			Collection colecaoIdImovel = new ArrayList();
			Collection colecaoIdImovelEconomia = new ArrayList();

			//colecao com os ids dos imoveis
	        if(clienteImoveis != null && !clienteImoveis.isEmpty()){
	        	Iterator iteratorClienteImoveis = clienteImoveis.iterator();
	        	
	        	while (iteratorClienteImoveis.hasNext()) {
					ClienteImovel clienteImovel = (ClienteImovel) iteratorClienteImoveis.next();
					
					sessao.setAttribute("clienteImovel", clienteImovel);
					
					colecaoIdImovel.add(clienteImovel.getImovel().getId());	
				}
	        }

	        
	        FiltroClienteImovelEconomia filtroClienteImovelEconomia = new FiltroClienteImovelEconomia();
	        
			filtroClienteImovelEconomia
				.adicionarCaminhoParaCarregamentoEntidade("imovelEconomia.imovelSubcategoria.comp_id.imovel");

			filtroClienteImovelEconomia
				.adicionarParametro(new ParametroSimples("imovelEconomia.imovelSubcategoria.comp_id.imovel.imovelPerfil.id",ImovelPerfil.TARIFA_SOCIAL));
			filtroClienteImovelEconomia
				.adicionarParametro(new ParametroSimples("cliente.id",cliente.getId()));

			
			filtroClienteImovelEconomia.adicionarParametro(new ParametroNulo(
					FiltroClienteImovelEconomia.FIM_RELACAO_MOTIVO));
			filtroClienteImovelEconomia
					.adicionarParametro(new ParametroSimples(
							FiltroClienteImovelEconomia.CLIENTE_RELACAO_TIPO,
							ClienteRelacaoTipo.USUARIO));
			
			// Realiza uma pesquisa pelos parametros fornecidos
			Collection clienteImoveisEconomias = fachada.pesquisar(
					filtroClienteImovelEconomia, ClienteImovelEconomia.class.getName());
	        
			//colecao com os ids dos imoveis
	        if(clienteImoveisEconomias != null && !clienteImoveisEconomias.isEmpty()){
	        	
	        	Iterator iteratorClienteImoveisEconomias = clienteImoveisEconomias.iterator();
	        	
	        	while (iteratorClienteImoveisEconomias.hasNext()) {
	        		ClienteImovelEconomia clienteImovelEconomia = (ClienteImovelEconomia) iteratorClienteImoveisEconomias.next();
					
					colecaoIdImovelEconomia.add(clienteImovelEconomia.getImovelEconomia().getId());	
				}
	        }
	        
	        colecaoTarifaSocialDadoEconomia = fachada.pesquisarTarifaSocialDadoEconomia(
					new Integer(idImovel));
	        
	        if(colecaoIdImovel != null && !colecaoIdImovel.isEmpty()){
	        	
	        	Iterator iteratorColecaoImovel = colecaoIdImovel.iterator();
	        	
	        	while (iteratorColecaoImovel.hasNext()) {
					Integer matriculaImovel = (Integer) iteratorColecaoImovel.next();
					
					// Realiza uma pesquisa pelos parametros fornecidos
					Collection colecaoTarifaSocialDadoEconomiaVerificar = null;
					
					colecaoTarifaSocialDadoEconomiaVerificar = fachada.pesquisarTarifaSocialDadoEconomia(
							matriculaImovel);
					
					if (colecaoTarifaSocialDadoEconomiaVerificar != null && !colecaoTarifaSocialDadoEconomiaVerificar.isEmpty()) {

					TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) colecaoTarifaSocialDadoEconomiaVerificar.iterator().next();

					fachada.verificarClienteMotivoExclusaoRecadastramento(cliente.getId());
					
					if (tarifaSocialDadoEconomia
								.getTarifaSocialExclusaoMotivo() != null
								&& tarifaSocialDadoEconomia
										.getTarifaSocialExclusaoMotivo()
										.getIndicadorPermiteRecadastramentoImovel() == 2) {
							throw new ActionServletException(
									"atencao.motivo_exclusao_imovel_nao_permite_recadastramento");
					}
					
					
					if (tarifaSocialDadoEconomia
								.getTarifaSocialRevisaoMotivo() == null
								&& tarifaSocialDadoEconomia
										.getTarifaSocialExclusaoMotivo() == null) {

						sessao.setAttribute("prerequisito","");
						
						httpServletRequest.setAttribute("atencao.cliente.usuario.tarifa.social",
						"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");

						sessao.setAttribute("prerequisito","");
						
						return montarPaginaConfirmacaoWizard("atencao.cliente.usuario.tarifa.social",
								httpServletRequest, actionMapping,matriculaImovel.toString());
					}else{
						
						if (tarifaSocialDadoEconomia
									.getTarifaSocialExclusaoMotivo() == null
									&& tarifaSocialDadoEconomia
											.getTarifaSocialRevisaoMotivo()
											.getIndicadorPermiteRecadastramento()
											.intValue() == 2) {
							
							sessao.setAttribute("prerequisito","");
							
							httpServletRequest.setAttribute("caminhoActionConclusao",
							"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
							
							sessao.setAttribute("prerequisito","");
							
							return montarPaginaConfirmacaoWizard("atencao.cliente.usuario.tarifa.social",
									httpServletRequest, actionMapping,matriculaImovel.toString());
						}
					}
					}
				}
	        }
	        
	        if(colecaoIdImovelEconomia != null && !colecaoIdImovelEconomia.isEmpty()){
	        	
	        	Iterator iteratorColecaoIdImovelEconomia = colecaoIdImovelEconomia.iterator();
	        	
	        	while (iteratorColecaoIdImovelEconomia.hasNext()) {
					Integer idImovelEconomia = (Integer) iteratorColecaoIdImovelEconomia.next();
					
					// Realiza uma pesquisa pelos parametros fornecidos
					Collection colecaoTarifaSocialDadoEconomiaVerificar = null;
					
					colecaoTarifaSocialDadoEconomiaVerificar = fachada.pesquisarTarifaSocialDadoEconomiaImovelEconomia(
							idImovelEconomia);
					
					if (colecaoTarifaSocialDadoEconomiaVerificar != null && !colecaoTarifaSocialDadoEconomiaVerificar.isEmpty()) {

					TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) colecaoTarifaSocialDadoEconomiaVerificar.iterator().next();

					fachada.verificarClienteMotivoExclusaoRecadastramento(cliente.getId());
					
					if (tarifaSocialDadoEconomia
								.getTarifaSocialExclusaoMotivo() != null
								&& tarifaSocialDadoEconomia
										.getTarifaSocialExclusaoMotivo()
										.getIndicadorPermiteRecadastramentoImovel() == 2) {
							throw new ActionServletException(
									"atencao.motivo_exclusao_imovel_nao_permite_recadastramento");
					}
					
					
					if (tarifaSocialDadoEconomia
								.getTarifaSocialRevisaoMotivo() == null
								&& tarifaSocialDadoEconomia
										.getTarifaSocialExclusaoMotivo() == null) {

						sessao.setAttribute("prerequisito","");
						
						httpServletRequest.setAttribute("atencao.cliente.usuario.tarifa.social",
						"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");

						sessao.setAttribute("prerequisito","");
						
						return montarPaginaConfirmacaoWizard("atencao.cliente.usuario.tarifa.social",
								httpServletRequest, actionMapping, tarifaSocialDadoEconomia.getImovel().getId().toString());
					}else{
						
						if (tarifaSocialDadoEconomia
									.getTarifaSocialExclusaoMotivo() == null
									&& tarifaSocialDadoEconomia
											.getTarifaSocialRevisaoMotivo()
											.getIndicadorPermiteRecadastramento()
											.intValue() == 2) {
							
							sessao.setAttribute("prerequisito","");
							
							httpServletRequest.setAttribute("caminhoActionConclusao",
							"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
							
							sessao.setAttribute("prerequisito","");
							
							return montarPaginaConfirmacaoWizard("atencao.cliente.usuario.tarifa.social",
									httpServletRequest, actionMapping, tarifaSocialDadoEconomia.getImovel().getId().toString());
						}
					}
					}
				}
	        }
	        
	        //[FS0003] - Verificar pré-requisitos para cadastramento na tarifa
	        // social
	        String[] dados = fachada.verificarPreRequisitosCadastramentoTarifaSocial(new Integer(idImovel));
	        
	        int prerequisito = new Integer(dados[0]).intValue();
	        
	        String parametroMensagem = dados[1];
	        
	        switch (prerequisito) {
	        
	        	//CATEGORIA RESIDENCIAL - E
				case 1:
					sessao.setAttribute("prerequisito","1");
					
					httpServletRequest.setAttribute("caminhoActionConclusao",
						"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
					
					return montarPaginaConfirmacaoWizard("atencao.tarifasocial.nao_imovel_residencial",
							httpServletRequest, actionMapping);
					
				//SUBCATEGORIA CASA DE VERANEIO - E	
				case 2:
					sessao.setAttribute("prerequisito","2");

					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifasocial.nao_imovel.com.subcategorias",
						httpServletRequest, actionMapping, parametroMensagem, "provoca o encerramento","Encerramento");
					
					
				//SUBCATEGORIA IGREJA, CHAFARIZ, TERRENO - T
				case 3:
					sessao.setAttribute("prerequisito","3");

					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifasocial.nao_imovel.com.subcategorias",
						httpServletRequest, actionMapping, parametroMensagem, "exige a tramitação", "Tramitação");
					
					
				//PERIL GRANDE CONSUMIDOR	
				case 4:
					sessao.setAttribute("prerequisito","4");
					
					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifasocial.imovel.perfil.grande_consumidor",
						httpServletRequest, actionMapping);

					
				//LIGACAO DIFERENTE DE CORTADO OU SUPRIMIDO - T	
				case 5:
					sessao.setAttribute("prerequisito","5");
					
					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifasocial.agua_nao_ligada",
						httpServletRequest, actionMapping);
					
					
				//ANORMAIDADE DE LEITURA - T	
				case 6:
					sessao.setAttribute("prerequisito","6");
					
					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.anormalidade.leitura.tarifasocial",
						httpServletRequest, actionMapping, parametroMensagem);
					
					
				//EXISTENCIA DE DEBITOS DO CLIENTE	
				case 7:
					sessao.setAttribute("prerequisito","7");

					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifa_social.permitida.apenas.imovel.sem_debitos",
						httpServletRequest, actionMapping);
					
					
				//CONSUMO MEDIO MAIOR QUE 10M3 - E	
				case 8:
					sessao.setAttribute("prerequisito","8");

					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifa_social.permitida.imoveis.consumo_medio",
						httpServletRequest, actionMapping, parametroMensagem);
					
				// CONSUMO MÍNIMO FIXADO MAIOR QUE 10M3 - E	
				case 9:
					sessao.setAttribute("prerequisito","9");

					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosMultiplasEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifa_social.permitida.imoveis.consumo_minimo_fixado",
						httpServletRequest, actionMapping, parametroMensagem);
					

				default:
					break;
			}
	
	        //Pesquisa na fachada a coleção
	        Collection colecaoClienteImovel = fachada.pesquisarClienteImovelPeloImovelTarifaSocial(new Integer(idImovel));
	        
	        //Carregando todos os objetos da coleção de TarifaSocialDadoEconomia 
	        carregarObjetosClienteImovel(clienteImoveis);
	        
	        ClienteImovel clienteImovel = null;
	        
	        Iterator colecaoClienteImovelIterator = colecaoClienteImovel.iterator();
	        
	        while (colecaoClienteImovelIterator.hasNext()) {
	        	
	        	clienteImovel = (ClienteImovel) colecaoClienteImovelIterator.next();
	        	
	        	if (clienteImovel.getClienteRelacaoTipo().getId().toString()
						.equals(ClienteRelacaoTipo.USUARIO.toString())) {
					break;
				}
	        	
	        }
	        
	        //Manda pela sessao o clienteImovel para a página
	        sessao.setAttribute("clienteImovel", clienteImovel);

	        if(colecaoTarifaSocialDadoEconomia != null && !colecaoTarifaSocialDadoEconomia.isEmpty()){
	        	sessao.setAttribute("colecaoTarifaSocialDadoEconomia", colecaoTarifaSocialDadoEconomia);
	        }
	        
	        sessao.setAttribute("prerequisito",null);
	        
        }else{
        	
        	if(sessao.getAttribute("prerequisito") != null ){
        	
        		String prerequisito = (String)sessao.getAttribute("prerequisito");
        		
        		httpServletRequest.setAttribute("numeroRA", inserirTarifaSocialActionForm.get("registroAtendimento"));

        		//[FS0005] Verificar o cliente usuário do imóvel
        		if(prerequisito.equals("")){
        			
        			retorno = actionMapping
        				.findForward("exibirTramitarRegistroAtendimentoAction");
        		}else if(prerequisito.equals("1")){
        			//CATEGORIA RESIDENCIAL - E
        			
        			retorno = actionMapping
    				.findForward("exibirEncerrarRegistroAtendimentoAction");
        			
        		}else if(prerequisito.equals("2")){
        			//CATEGORIA RESIDENCIAL - E
        			
        			retorno = actionMapping
    					.findForward("exibirEncerrarRegistroAtendimentoAction");

        			
        		}else if(prerequisito.equals("3")){
        			//SUBCATEGORIA IGREJA, CHAFARIZ, TERRENO - T
        			
        			retorno = actionMapping
    					.findForward("exibirTramitarRegistroAtendimentoAction");
        		
        		}else if(prerequisito.equals("4")){
        			//PERIFL GRANDE CONSUMIDOR
        			retorno = actionMapping
    				.findForward("exibirTramitarRegistroAtendimentoAction");
        		
        		}else if(prerequisito.equals("5")){
        			//LIGACAO DIFERENTE DE CORTADO OU SUPRIMIDO - T
        			retorno = actionMapping
    					.findForward("exibirTramitarRegistroAtendimentoAction");
        		
        		}else if(prerequisito.equals("6")){
        			//ANORMALIDAE DE LEITURA - T
        			retorno = actionMapping
					.findForward("exibirTramitarRegistroAtendimentoAction");
        		
        		}else if(prerequisito.equals("7")){
        			//EXISTENCIA DE DEBITOS DO CLIENTE - T
        			
        			retorno = actionMapping
					.findForward("exibirTramitarRegistroAtendimentoAction");
        		
        		}else if(prerequisito.equals("8")){

        			//CONSUMO MÉDIO MAIOR QUE 10M3 - E
        			retorno = actionMapping
					.findForward("exibirEncerrarRegistroAtendimentoAction");
        		}else if(prerequisito.equals("9")){
				
        			// CONSUMO MÍNIMO FIXADO MAIOR QUE 10M3 - E
					retorno = actionMapping
							.findForward("exibirEncerrarRegistroAtendimentoAction");
        		}else if(prerequisito.equals("10")){
        			
        			retorno = actionMapping
					.findForward("exibirEncerrarRegistroAtendimentoAction");
        		
        		}else if(prerequisito.equals("11")){
        			
        			retorno = actionMapping
					.findForward("exibirEncerrarRegistroAtendimentoAction");
        			
        		}else if(prerequisito.equals("12")){
        			
        			retorno = actionMapping
					.findForward("exibirEncerrarRegistroAtendimentoAction");
        			
        		}else if(prerequisito.equals("13")){
        			
        			retorno = actionMapping
        			.findForward("exibirEncerrarRegistroAtendimentoAction");
    			
    		}
			
        	}
        }
        
        String codigo = httpServletRequest.getParameter("codigo");
        String valor = httpServletRequest.getParameter("valor");
        
        if(codigo != null && !codigo.equals("")){

        	if(codigo.equals("9")){
				sessao.setAttribute("prerequisito","9");
        		
        	}else if(codigo.equals("10")){
				sessao.setAttribute("prerequisito","10");
	
				httpServletRequest.setAttribute("caminhoActionConclusao",
				"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
			
				return montarPaginaConfirmacaoWizard("atencao.tarifa_social.renda_familiar.maior.salario_minimo",
					httpServletRequest, actionMapping,valor);
			
        	}else if(codigo.equals("11")){
				sessao.setAttribute("prerequisito","11");
				
				httpServletRequest.setAttribute("caminhoActionConclusao",
				"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
			
				return montarPaginaConfirmacaoWizard("atencao.preencher.tarifa_social.valor.consumo_energia",
					httpServletRequest, actionMapping,valor);

        	}else if(codigo.equals("12")){
				sessao.setAttribute("prerequisito","12");
				
				httpServletRequest.setAttribute("caminhoActionConclusao",
				"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
			
				return montarPaginaConfirmacaoWizard("atencao.preencher.tarifa_social.requisitos",
					httpServletRequest, actionMapping,valor);
        	}else if(codigo.equals("13")){
				sessao.setAttribute("prerequisito","13");
				
				httpServletRequest.setAttribute("caminhoActionConclusao",
				"/gsan/inserirTarifaSocialWizardAction.do&action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
			
				return montarPaginaConfirmacaoWizard("atencao.preencher.tarifa_social.requisitos",
					httpServletRequest, actionMapping,valor);
        	}
        	
        	
        }
        
        return retorno;
    }
    
    
    private void carregarObjetosClienteImovel(Collection clienteImoveis){
    	
    	Fachada fachada = Fachada.getInstancia();
    	
    	Iterator clienteImoveisIt = clienteImoveis.iterator();
    	
    	while (clienteImoveisIt.hasNext()){
    		ClienteImovel clienteImovelObject =  (ClienteImovel) clienteImoveisIt.next();
    		
    			
   			FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = new FiltroTarifaSocialDadoEconomia();
    			
   			filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroSimples("imovel.id", clienteImovelObject.getImovel().getId()));
			filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade("tarifaSocialCartaoTipo");
			filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade("rendaTipo");
    	
   			
   			Collection tarifaSocialDadoEconomias = fachada.pesquisar(filtroTarifaSocialDadoEconomia,TarifaSocialDadoEconomia.class.getName());
    			
   			if(tarifaSocialDadoEconomias != null && !tarifaSocialDadoEconomias.isEmpty()){
    			
    			Iterator tarifaSocialDadoEconomiasIt = tarifaSocialDadoEconomias.iterator();
    		
    			while (tarifaSocialDadoEconomiasIt.hasNext()){
    				TarifaSocialDadoEconomia tarifaSocialDadoEconomiaObject = 
    					(TarifaSocialDadoEconomia) tarifaSocialDadoEconomiasIt.next();
	    			
	    			//TarifaSocialCartaoTipo
	    			tarifaSocialDadoEconomiaObject.setTarifaSocialCartaoTipo(
	    					tarifaSocialDadoEconomiaObject.getTarifaSocialCartaoTipo());
	    			
	    			//RendaTipo
	    			tarifaSocialDadoEconomiaObject.setRendaTipo(tarifaSocialDadoEconomiaObject.getRendaTipo());
	    		}
    		
    		}
    	}
    }
}
