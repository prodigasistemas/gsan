package gcom.gui.cadastro;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInserirCadastroEmailClienteAction extends
		GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInserirCadastroEmailClienteActionAction");
		
//		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		InserirCadastroEmailClienteActionForm 
			form = (InserirCadastroEmailClienteActionForm) actionForm;
		
		boolean possuiDocumento = false;
		
		//Desabilita os campos se a matricula do imovel nao for informada
		//e so habilita se a matricula do imovel existir
		if ( form.getMatricula() == null || form.getMatricula().equals("")){
			// Se 1 - DESABILITA se 2 - HABILITA
			form.setDesabilitaCampos("1");
			
		}
		
		if ( (httpServletRequest.getParameter("voltar") != null && 
				httpServletRequest.getParameter("voltar").equals("sim")) 
				|| (httpServletRequest.getParameter("ok") == null || 
				!httpServletRequest.getParameter("ok").equals("sim")) ){
			
			form.setConfirmarCpfCnpjCliente("");
			form.setConfirmarNomeCliente("");
			form.setCpfCnpjCliente("");
			form.setCpfCnpjClienteAux("");
			form.setCpfSolicitante("");
			form.setEmail("");
			form.setEmailAux("");
			form.setIdCliente(null);
			form.setIndicadorCnpj(false);
			form.setIndicadorCpf(false);
			form.setMatricula("");
			form.setNomeCliente("");
			form.setNomeClienteAux("");
			form.setNomeSolicitante("");
			form.setTelefoneContato("");
			
		}
		
		//[FS0001] - Verificar Cliente em Cobrança Judicial ou Negativação.
		if ( httpServletRequest.getParameter("ok") != null && 
				httpServletRequest.getParameter("ok").equals("sim") ){
			
			form.setDesabilitaCampos("2");
			
			form.setConfirmarNomeCliente("");
			form.setConfirmarCpfCnpjCliente("");
			
			if ( form.getMatricula() != null && !form.getMatricula().equals("") ){
				
				Integer idImovel = new Integer(form.getMatricula());
				
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarParametro( new ParametroSimples( FiltroImovel.ID,idImovel ));
				
				Collection colecaoImovel = fachada.pesquisar
								(filtroImovel, Imovel.class.getName());
				
				if ( colecaoImovel != null && !colecaoImovel.isEmpty() ){
					
					boolean estaEmCobrancaJudicial = fachada.verificarSituacaoImovelCobrancaJudicial(idImovel);
					
//					boolean estaNegativado = fachada.verificarSituacaoImovelNegativacao(idImovel);
					
					if ( estaEmCobrancaJudicial == true ){
						
						throw new ActionServletException("atencao.cliente.em.cobranca.judicial",
								null, idImovel.toString());				
						
					}
					
//					if ( estaNegativado == true ){
//						
//						throw new ActionServletException("atencao.cliente.negativado", null, idImovel.toString());				
//						
//					}	
					
					FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
					filtroClienteImovel.adicionarParametro( new ParametroSimples(
											FiltroClienteImovel.IMOVEL_ID, idImovel));
					
					filtroClienteImovel.adicionarParametro( new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
					
					filtroClienteImovel.adicionarParametro( new ParametroSimples(
							FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, 2));
					
					filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
					
										
					Collection colecaoClienteImovel = fachada.pesquisar
												(filtroClienteImovel, ClienteImovel.class.getName());
					
					if ( colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty() ){
						
						ClienteImovel clienteImovel = (ClienteImovel) Util.
											retonarObjetoDeColecao(colecaoClienteImovel);
						
						FiltroCliente filtroCliente = new FiltroCliente();
						
						filtroCliente.adicionarParametro( new ParametroSimples(
								FiltroCliente.ID, clienteImovel.getCliente().getId()) );
						
						filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
						
						Collection clientes = fachada.pesquisar( filtroCliente, Cliente.class.getName() );
						
						if ( clientes != null && !clientes.isEmpty() ){
							
							Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(clientes);
							
							form.setIdCliente(cliente.getId());
							
							form.setNomeCliente(cliente.getNome());
							form.setNomeClienteAux(cliente.getNome());
											
							if ( cliente.getClienteTipo() != null ){
								
								if ( cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica() != null
										&& cliente.getClienteTipo()
											.getIndicadorPessoaFisicaJuridica() == new Integer("1").shortValue() ){
									
									httpServletRequest.setAttribute("pessoaFisicaJuridica","pessoaFisica");
									
									form.setCpfCnpjCliente(cliente.getCpf());
									form.setCpfCnpjClienteAux(cliente.getCpf());
									
									form.setIndicadorCpf(true);
									
									if ( cliente.getCpf() != null && !cliente.getCpf().equals("") ){
										
										possuiDocumento = true;
										
									}
									
									
								}else if (cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica() != null
										&& cliente.getClienteTipo()
										.getIndicadorPessoaFisicaJuridica() == new Integer("2").shortValue() ){
									
									httpServletRequest.setAttribute("pessoaFisicaJuridica","pessoaJuridica");
									
									form.setCpfCnpjCliente(cliente.getCnpj());
									form.setCpfCnpjClienteAux(cliente.getCnpj());
									
									form.setIndicadorCnpj(true);
									
									if ( cliente.getCnpj() != null && !cliente.getCnpj().equals("") ){
										
										possuiDocumento = true;
										
									}
									
								}
								
								if ( cliente.getEmail() != null && !cliente.getEmail().equals("") ){
									
									form.setEmail(cliente.getEmail());
									form.setEmailAux(cliente.getEmail());
									
								}
								
							}
							
							if ( possuiDocumento ){
								
								httpServletRequest.setAttribute("possuiDocumento", "true");
								
							}else{
								
								httpServletRequest.setAttribute("possuiDocumento", "false");
								
							}
						}
						
//						form.setIdCliente(clienteImovel.getCliente().getId());
//						
//						form.setNomeCliente(clienteImovel.getCliente().getNome());
//						form.setNomeClienteAux(clienteImovel.getCliente().getNome());
//						
//						if ( clienteImovel.getCliente().getCpf() != null && 
//								!clienteImovel.getCliente().getCpf().equals("") ){
//							
//							form.setCpfCnpjCliente(clienteImovel.getCliente().getCpf());
//							form.setCpfCnpjClienteAux(clienteImovel.getCliente().getCpf());
//							
//							form.setIndicadorCpf(true);
//							
//						}else if ( clienteImovel.getCliente().getCnpj() != null &&
//								!clienteImovel.getCliente().getCnpj().equals("") ){
//							
//							form.setCpfCnpjCliente(clienteImovel.getCliente().getCnpj());
//							form.setCpfCnpjClienteAux(clienteImovel.getCliente().getCnpjFormatado());
//							
//							form.setIndicadorCnpj(true);
//							
//						}
//						
//						if ( clienteImovel.getCliente().getEmail() != null && 
//								!clienteImovel.getCliente().getEmail().equals("") ){
//							
//							form.setEmail(clienteImovel.getCliente().getEmail());
//							form.setEmailAux(clienteImovel.getCliente().getEmail());
//							
//						}
					}else{
						
						throw new ActionServletException("atencao.imovel.inexistente");
						
					}
				
				
					
				}else{
					
					throw new ActionServletException("atencao.imovel.inexistente");
					
				}
				
			}
			
		}
		
		return retorno;
	}

}
