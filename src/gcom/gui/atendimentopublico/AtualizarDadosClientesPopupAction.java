package gcom.gui.atendimentopublico;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * @author Daniel Alves
 * @date 29/07/2010
 */
public class AtualizarDadosClientesPopupAction extends GcomAction {

	/**
	 * [UC1049] Atualizar dados Cadadastrais PROMAIS
	 * 
	 * 
	 * @author Daniel Alves
	 * @date 29/07/2010
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

		ExibirAtualizarDadosClientesPopupActionForm atualizarDadosClientesPopupActionForm = (ExibirAtualizarDadosClientesPopupActionForm) actionForm;
		
		HttpSession sessao = getSessao(httpServletRequest);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
	
		//caso a colecao de ClienteImovel nao esteja vazia
		if(atualizarDadosClientesPopupActionForm.getColecaoClienteImovel() != null &&
				!atualizarDadosClientesPopupActionForm.getColecaoClienteImovel().isEmpty()){

			//id do cliente imovel a ser altualizado
			String codClienteImovelAtualizado = atualizarDadosClientesPopupActionForm.getCodClienteImovel();

			//verifica se foi selecionado algum cliente
			if(codClienteImovelAtualizado != null && !codClienteImovelAtualizado.equals("")){

				Iterator iteratorClienteImovel = atualizarDadosClientesPopupActionForm.getColecaoClienteImovel().iterator();
		
				boolean isAlterado = false;
				ClienteImovel clienteImovel = null;
				Cliente cliente = null;

				//Procura o cliente que foi selecionado no popup
				while (iteratorClienteImovel.hasNext()) {
					
					clienteImovel = (ClienteImovel) iteratorClienteImovel.next();

					//se acho o clienteImovel que vai ser alterado na colecao
					if(clienteImovel.getId().toString().equals(codClienteImovelAtualizado)){
						cliente = clienteImovel.getCliente();
						
						//se o nome do cliente não estiver vazio.
						if(cliente.getNome() != null && !cliente.getNome().equals("")){							

							//Teste de alteração do nome do cliente menos que 5 digitos do nome
							Integer dif = StringUtils.getLevenshteinDistance(cliente.getNome() ,atualizarDadosClientesPopupActionForm.getNovoNomeCliente());
							
							if(dif > 5){						
								throw new ActionServletException("atencao.alteracao.maxima.caracteres");
							}else if(dif != 0){
								cliente.setNome(atualizarDadosClientesPopupActionForm.getNovoNomeCliente());
								isAlterado = true;
							}
								
							
							//caso não tenha informado o cpf/cnpj.
							if(atualizarDadosClientesPopupActionForm.getCpfCnpjCliente() != null &&
									atualizarDadosClientesPopupActionForm.getCpfCnpjCliente().equals("")){
								
								throw new ActionServletException("atencao.cpf_nao_informado", cliente.getId().toString());
								
							} else {
								
								//Pessoa Física
								if(atualizarDadosClientesPopupActionForm.getTipoPessoa().equals("1")){										
									//se houver alguma mudança no cpf
									if(!atualizarDadosClientesPopupActionForm.getCpfCnpjCliente().equals(cliente.getCpf())){
										
										String cpf = atualizarDadosClientesPopupActionForm.getCpfCnpjCliente();
										
										//valida o cpf
										if(Util.validacaoCPF(cpf)){
											
											//verifica se existe o cliente
											if(!existeClienteCpf(cpf)){													
												cliente.setCpf(atualizarDadosClientesPopupActionForm.getCpfCnpjCliente());
												isAlterado = true;
												
											}
											
										}else{
											throw new ActionServletException("atencao.digito_verificador_cpf_nao_confere");
										}
										
									}										
									

								//Pessoa Jurídica	
								}else if(atualizarDadosClientesPopupActionForm.getTipoPessoa().equals("2")){										
									//se houver alguma mudança no cnpj
									if(!atualizarDadosClientesPopupActionForm.getCpfCnpjCliente().equals(cliente.getCnpj())){
										
										String cnpj = atualizarDadosClientesPopupActionForm.getCpfCnpjCliente();
										
										//valida o cnpj
										if(Util.validacaoCNPJ(cnpj)){
											
											//verifica se existe o cliente
											if(!existeClienteCnpj(cnpj)){													
												cliente.setCnpj(atualizarDadosClientesPopupActionForm.getCpfCnpjCliente());
												isAlterado = true;
												
											}
											
										}else{
											throw new ActionServletException("atencao.digito_verificador_cnpj_nao_confere");
										}											
									}
									
								}
								
								
							}
							
							//Teste da remoção de telefones do cliente. As demais alterações do cliente, são realizadas no momento que são executadas.
							Collection colecaoRemoverFone = (Collection)atualizarDadosClientesPopupActionForm.getRemoverClienteFone();
							if(colecaoRemoverFone != null && colecaoRemoverFone.size() > 0){
								Iterator iteratorColecaoRemoverFone = colecaoRemoverFone.iterator();
								
								ClienteFone clienteFoneRemover = null;
								
								while(iteratorColecaoRemoverFone.hasNext()){
									clienteFoneRemover = (ClienteFone)iteratorColecaoRemoverFone.next();
									
									//se o telefone a ser removido for do cliente selecionado para atualização (então remove clienteFone)
									if(clienteFoneRemover.getCliente().getId().equals(atualizarDadosClientesPopupActionForm.getIdCliente())){
										getFachada().remover(clienteFoneRemover);
									}
																		
								}
								
								atualizarDadosClientesPopupActionForm.setRemoverClienteFone(null);
								isAlterado = true;
							}
							
							
							//Teste de alteração do telefone padrão.
							HashMap mapTelefonePadrao = atualizarDadosClientesPopupActionForm.getTelefonePrincipal();
							
							if(mapTelefonePadrao != null && mapTelefonePadrao.size() > 0){
								
								String idClienteFonePadrao = procurarTelefonePadrao(cliente.getId().toString() , mapTelefonePadrao);
								
								if(idClienteFonePadrao != null && !idClienteFonePadrao.equals("")){									
									getFachada().atualizarTelefonePadrao(cliente.getId().toString(), idClienteFonePadrao);										
									isAlterado = true;
								}
								
							}
						
						} else {
							throw new ActionServletException("atencao.nome_cliente_nao_informado", cliente.getId().toString());
						}

					}
				}
				
				//Se foi modificado algum dado do cliente, atualiza o cliente.
				if(isAlterado){	
					
					RegistradorOperacao registradorOperacao = new RegistradorOperacao(
							Operacao.OPERACAO_ATUALIZAR_DADOS_CLIENTE_PROMAIS,
							cliente.getId(), cliente.getId(), new UsuarioAcaoUsuarioHelper( usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
							
					registradorOperacao.registrarOperacao(cliente);		
					
					getFachada().atualizarCliente(cliente,null,null,usuarioLogado);
					
					montarPaginaSucesso(httpServletRequest,
							"Cliente alterado com sucesso.",
							"Fechar Pop-up.",
							"exibirAtualizarDadosClientesPopupAction.do?fecharPopup=true");
					
				}else{
					montarPaginaSucesso(httpServletRequest,
							"Cliente não foi atualizado.",
							"Fechar Pop-up.",
							"exibirAtualizarDadosClientesPopupAction.do?fecharPopup=true");
				}
				
			}else{
				
				throw new ActionServletException("atencao.cliente_nao_selecionado");					
			}
						
		}else{
		
			montarPaginaSucesso(httpServletRequest,
					"Cliente não foi atualizado.",
					"Fechar Pop-up.",
					"exibirAtualizarDadosClientesPopupAction.do?fecharPopup=true");
		}

		return retorno;
	}
	
	/**
	 * @author Daniel Alves
	 * @date 01/09/2010 
	 * @param cpf
	 * @return boolean
	 */
	private boolean existeClienteCpf(String cpf){
		
		boolean existeCliente = false;
		
		FiltroCliente filtroCliente = new FiltroCliente();
		
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.CPF, cpf));
		
		Collection colecao = (Collection) getFachada().pesquisar(filtroCliente, Cliente.class.getName());
		
		Cliente cliente = (Cliente)Util.retonarObjetoDeColecao(colecao);
		
		if(colecao != null && colecao.size() > 0){
			existeCliente = true;
			throw new ActionServletException("atencao.cpf.cliente.ja_cadastrado", cliente.getId().toString());
		}
		
		return existeCliente;		
	}
	
	
	/**
	 * @author Daniel Alves
	 * @date 01/09/2010 
	 * @param cnpj
	 * @return boolean
	 */
	private boolean existeClienteCnpj(String cnpj){
		
		boolean existeCliente = false;
		
		FiltroCliente filtroCliente = new FiltroCliente();
		
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.CNPJ, cnpj));
		
		Collection colecao = (Collection) getFachada().pesquisar(filtroCliente, Cliente.class.getName());
		
		Cliente cliente = (Cliente)Util.retonarObjetoDeColecao(colecao);
		
		if(colecao != null && colecao.size() > 0){
			existeCliente = true;
			throw new ActionServletException("atencao.cnpj.cliente.ja_cadastrado", cliente.getId().toString());
		}
		
		return existeCliente;		
	}
	
	private String procurarTelefonePadrao(String idCliente, HashMap mapTelefonePadrao){
		
		if(mapTelefonePadrao != null && mapTelefonePadrao.size() >0){
			return (String)mapTelefonePadrao.get("idCliente");
		}else{
			return "";
		}
	}
	

}
