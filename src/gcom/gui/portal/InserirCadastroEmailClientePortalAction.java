package gcom.gui.portal;

import gcom.cadastro.EmailClienteAlterado;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.FiltroEmailClienteAlterado;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * [RM2923] Inserir Cadastro Email Cliente - Loja Virtual da Compesa
 * 
 * @author Magno Gouveia
 * @date 19/05/2011
 *
 */
public class InserirCadastroEmailClientePortalAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("validarDados");

		Fachada fachada = Fachada.getInstancia();
		
		InserirCadastroEmailClientePortalActionForm form = (InserirCadastroEmailClientePortalActionForm) actionForm;
		
		EmailClienteAlterado emailClienteAlterado = new EmailClienteAlterado();
		
		httpServletRequest.setAttribute("voltarServicos", true);
		
		//Acessando direto pelo link, sem ser pelo link do email
		if (httpServletRequest.getParameter("confirmar") == null
				|| !httpServletRequest.getParameter("confirmar").equals("sim")) {
			
			// [FS0008] Verificar se os dados foram alterados
			if (!this.verificarAlteracaoDados(form)) {
				httpServletRequest.setAttribute("naoHouveAlteracao", true);
				
				return retorno;
				// throw new ActionServletException("atencao.nao_houve_alteracao");
			}
			
			//Valida se foram alterados mais de 5 digitos no nome do cliente
			if (form.getNomeCliente() != null
					&& !form.getNomeCliente().equals("") 
					&& form.getNomeClienteAux() != null && !form.getNomeClienteAux().equals("")){
				
				if (!form.getNomeCliente().equals(form.getNomeClienteAux())) {
					Integer dif = StringUtils.getLevenshteinDistance(form.getNomeCliente(), form.getNomeClienteAux());
					if (dif > 5) {
						httpServletRequest.setAttribute("alteracaoMaximaCaracteres", true);
						
						return retorno;
						// throw new ActionServletException("atencao.alteracao.maxima.caracteres");
					}
				}
			}
			
			//Valida o CPF/CNPJ do Cliente
			if (form.getCpfCnpjCliente() != null
					&& !form.getCpfCnpjCliente().equals("")) {
				
				if (form.isIndicadorCpf()) {
					
					if (form.getCpfCnpjCliente().length() < 11) {
						httpServletRequest.setAttribute("clienteCpfCnpjInvalido", true);
						
						return retorno;
						// throw new ActionServletException("atencao.cliente_cpf_cnpj_invalido");
					}
					
					Integer count = 0;
					
					for (int i = 0; i < 10; i++) {
						char cpf = form.getCpfCnpjCliente().charAt(i); 
						char cpfAux = form.getCpfCnpjCliente().charAt(i+1);
						if (cpf == cpfAux) {
							count = count + 1;
						}
					}
					
					if (count >= 10) {
						httpServletRequest.setAttribute("clienteCpfCnpjInvalido", true);
						
						return retorno;
						//throw new ActionServletException("atencao.cliente_cpf_cnpj_invalido");
					}
					
					//É um CPF
					boolean valido = gcom.util.Util.validacaoCPF(form.getCpfCnpjCliente());
					
					form.setIndicadorCpf(true);
					
					if (!valido) {
						httpServletRequest.setAttribute("clienteCpfCnpjInvalido", true);
						
						return retorno;
						// throw new ActionServletException("atencao.cliente_cpf_cnpj_invalido");
					} else {
						
						//Verifica se o cpf ja esta associado a outro cliente
						String cpf = form.getCpfCnpjCliente();
						
						FiltroCliente filtroCliente = new FiltroCliente();
						
						filtroCliente.adicionarParametro( new ParametroSimples( FiltroCliente.CPF, cpf));
						
						Collection clientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
						
						if (clientes != null && !clientes.isEmpty()) {
							
							Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(clientes);
							if (clientes.size() >= 1
									&& !(cliente.getId().equals(form.getIdCliente()))) {
								httpServletRequest.setAttribute("clienteCpfCnpjJaExiste", true);
								
								return retorno;
								// throw new ActionServletException("atencao.ja.existe.cliente.com.cpfCnpj");
							}
						}
					}
					
				} else if (form.isIndicadorCnpj()) {

					if (form.getCpfCnpjCliente().length() < 14) {
						httpServletRequest.setAttribute("clienteCpfCnpjInvalido", true);
						
						return retorno;
						// throw new ActionServletException("atencao.cliente_cpf_cnpj_invalido");
					}
					
					//É um CNPJ
					Integer count = 0;
					
					for (int i = 0; i < 13; i++) {
						char cpf = form.getCpfCnpjCliente().charAt(i); 
						char cpfAux = form.getCpfCnpjCliente().charAt(i+1);
						
						if (cpf == cpfAux) {
							count++;
						}
					}	
					
					if (count >= 13) {
						httpServletRequest.setAttribute("clienteCpfCnpjInvalido", true);
						
						return retorno;
						// throw new ActionServletException("atencao.cliente_cpf_cnpj_invalido");
					}
					
					boolean valido = gcom.util.Util.validacaoCNPJ(form.getCpfCnpjCliente());
					
					form.setIndicadorCnpj(true);
					
					if (!valido) {
						httpServletRequest.setAttribute("clienteCpfCnpjInvalido", true);
						return retorno;
						// throw new ActionServletException("atencao.cliente_cpf_cnpj_invalido");
						
					} else {
						
						FiltroCliente filtroCliente = new FiltroCliente();
						
						filtroCliente.adicionarParametro( new ParametroSimples( FiltroCliente.CNPJ, form.getCpfCnpjCliente()));
						
						Collection clientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
						
						if (clientes != null && !clientes.isEmpty()) {
							
							Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(clientes);
							
							if (clientes.size() >= 1
									&& !(cliente.getId().equals(form.getIdCliente()))) {
								httpServletRequest.setAttribute("clienteCpfCnpjJaExiste", true);
								
								return retorno;
								// throw new ActionServletException("atencao.ja.existe.cliente.com.cpfCnpj");
							}
						}
					}
				} else {
					httpServletRequest.setAttribute("clienteCpfCnpjInvalido", true);
					
					return retorno;
					//throw new ActionServletException("atencao.cliente_cpf_cnpj_invalido");
				}
			} else {
				httpServletRequest.setAttribute("cpfCnpjObrigatorio", true);
				
				return retorno;
				// throw new ActionServletException("atencao.cpf_cpnf_obrigatorio");
			}
			
			//Valida o CPF do Solicitante
			if (form.getCpfSolicitante() != null
					&& !form.getCpfSolicitante().equals("")) {
				
				if (form.getCpfSolicitante().length() == 11) {
					
					//É um CPF
					boolean valido = gcom.util.Util.validacaoCPF(form.getCpfSolicitante());
					
					if (!valido) {
						httpServletRequest.setAttribute("clienteCpfSolicitanteInvalido", true);
						
						return retorno;
						// throw new ActionServletException("atencao.cliente_cpf_solicitante_invalido");
					}
				} else {
					httpServletRequest.setAttribute("clienteCpfSolicitanteInvalido", true);
					
					return retorno;
					// throw new ActionServletException("atencao.cliente_cpf_solicitante_invalido");
				}
			} else {
				httpServletRequest.setAttribute("clienteCpfSolicitanteInvalido", true);
				
				return retorno;
				// throw new ActionServletException("atencao.cpf_solicitante_obrigatorio");
			}
			
			//Valida o email
			if (form.getEmail() != null && !form.getEmail().equals("")) {
				
				String email = form.getEmail();
				
				if (!email.contains("@") || email.contains(" ")) {
					httpServletRequest.setAttribute("emailInvalido", true);
					
					return retorno;
					// throw new ActionServletException("atencao.email.invalido");
				}
			} else {
				httpServletRequest.setAttribute("usuarioEmailNulo", true);
				
				return retorno;
				// throw new ActionServletException("atencao.usuario.email.nula");
			}
			
			//Preparar dados para armazenar na tabela
			Cliente idCliente = new Cliente(); 
			idCliente.setId(form.getIdCliente());
			
			//Dados Anteriores
			String nomeClienteAnterior = form.getNomeClienteAux().toUpperCase();
			
			String cpfAnterior = "";
			if (form.isIndicadorCpf()) {
				cpfAnterior = form.getCpfCnpjClienteAux();
			}
			
			String cnpjAnterior = "";

			if (form.isIndicadorCnpj()) {
				cnpjAnterior = form.getCpfCnpjClienteAux();
			}
			
			String emailAnterior = form.getEmailAux().toUpperCase();
			
			String nomeSolicitante = form.getNomeSolicitante().toUpperCase();
			String cpfSolicitante = form.getCpfSolicitante();
			
			String nomeClienteAtual = form.getNomeCliente().toUpperCase();
			String cpfClienteAtual = "";
			
			if (form.isIndicadorCpf()) {
				cpfClienteAtual = form.getCpfCnpjCliente();
			}
			
			String cnpjClienteAtual = "";
			if (form.isIndicadorCnpj()) {
				cnpjClienteAtual = form.getCpfCnpjCliente();
			}
			
			if (httpServletRequest.getParameter("possuiDocumento") != null
					&& (!httpServletRequest.getParameter("possuiDocumento").equals("true") || httpServletRequest.getParameter("possuiDocumento").equals("false"))) {
				if (form.getConfirmarCpfCnpjCliente() == null
						|| !form.getConfirmarCpfCnpjCliente().equals("confirmado")) {
					httpServletRequest.setAttribute("necessarioConfirmarCpfCnpjCliente", true);
					
					return retorno;
					// throw new ActionServletException("atencao.necessario.confirmar.cpfCnpj.cliente");
				}
			}

			String emailAtual = form.getEmail();
			
			emailClienteAlterado = new EmailClienteAlterado(idCliente, nomeClienteAnterior,
					cpfAnterior, cnpjAnterior, emailAnterior, nomeSolicitante, cpfSolicitante, nomeClienteAtual, 
					cpfClienteAtual, cnpjClienteAtual, emailAtual, new Date());
			
			//Seta o telefone para contato se o mesmo estiver preenchido
			if (form.getTelefoneContato() != null && !form.getTelefoneContato().equals("")) {
				String telefone = form.getTelefoneContato().trim();
				telefone = telefone.replace("(", "");
				telefone = telefone.replace(")", "");
				telefone = telefone.replace("-", "");
				telefone = telefone.replace(" ", "");
				emailClienteAlterado.setTelefoneContato(Util.converterStringParaInteger(telefone));
			}
			
			emailClienteAlterado.setUltimaAlteracao(new Date());
			
			if (httpServletRequest.getParameter("enviarEmail") == null
				|| !httpServletRequest.getParameter("enviarEmail").equals("sim")) {
				httpServletRequest.setAttribute("enviarConfirmacao", "OK");
			}
			
			if (httpServletRequest.getParameter("enviarEmail") != null
				&& httpServletRequest.getParameter("enviarEmail").equals("sim")) {
				
				retorno = actionMapping.findForward("emailCadastradoComSucesso");
				
				Integer idIncluido = (Integer)fachada.inserir(emailClienteAlterado);
				
				EnvioEmail envioEmail = fachada.pesquisarEnvioEmail(EnvioEmail.INSERIR_CADASTRO_EMAIL_CLIENTE);
				String emailRemetente = envioEmail.getEmailRemetente();
				String tituloMensagem = "Cadastramento de Email";
				String emailReceptor = form.getEmail();
				String mensagem = envioEmail.getCorpoMensagem();
				
				SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
				
				String urlAcessoInternet = sistemaParametro.getUrlAcessoInternet();
				
				String url = urlAcessoInternet + "/inserirCadastroEmailClientePortalAction.do?id=" + idIncluido
				       	   + "&confirmar=sim&idImovel=" + form.getMatricula();
				
				mensagem = mensagem
						+ " - Você acessou o site da Compesa para cadastrar seu email e/ou alterar os dados cadastrais ? Caso positivo clique no link abaixo:  \n \n \n"
						+ url
						+ "\n\n\n Esse é um e-mail automático. Não é necessário respondê-lo.";
				
				httpServletRequest.setAttribute("desabilitaMenu",true);
				
				try {
					ServicosEmail.enviarMensagem(emailRemetente, emailReceptor, tituloMensagem, mensagem);
					
					httpServletRequest.setAttribute("emailCadastradoComSucesso", true);
					httpServletRequest.setAttribute("respostaCliente", "Você receberá um e-mail para confirmação dos dados alterados.");
							
				} catch (ErroEmailException erroEnviarEmail) {
					erroEnviarEmail.printStackTrace();
				}
				
			}
			
		}
		
		//Quando o usuário clicar no link enviado por email
		if (httpServletRequest.getParameter("confirmar") != null
				&& httpServletRequest.getParameter("confirmar").equals("sim")) {
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			if (httpServletRequest.getParameter("id") != null) {
				
				Integer idEmailConfirmar = Util.converterStringParaInteger(httpServletRequest.getParameter("id"));
				
				FiltroEmailClienteAlterado filtroEmailCliente = new FiltroEmailClienteAlterado();
				
				filtroEmailCliente.adicionarParametro(new ParametroSimples(FiltroEmailClienteAlterado.ID, idEmailConfirmar));
				
				Collection dadosCliente = fachada.pesquisar(filtroEmailCliente, EmailClienteAlterado.class.getName());
				
				if (dadosCliente != null && !dadosCliente.isEmpty()) {
					
					EmailClienteAlterado dadoCliente = (EmailClienteAlterado) gcom.util.Util.retonarObjetoDeColecao(dadosCliente);
					
					FiltroCliente filtroCliente = new FiltroCliente();
					
					filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, dadoCliente.getIdCliente()));
					
					Collection clientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
					
					if (clientes != null && !clientes.isEmpty()) {
						
						Cliente cliente = (Cliente) gcom.util.Util.retonarObjetoDeColecao(clientes);
						
						cliente.setNome(dadoCliente.getNomeClienteAtual());
						cliente.setCpf(dadoCliente.getCpfAtual());
						cliente.setCnpj(dadoCliente.getCnpjAtual());
						cliente.setEmail(dadoCliente.getEmailAtual());
						//Atualiza os dados do cliente que foram alterados
						fachada.atualizar(cliente);
						
						FiltroImovel filtroImovel = new FiltroImovel();
						
						filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,httpServletRequest.getParameter("idImovel")));
						
						Collection imoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
						
						if (imoveis != null && !imoveis.isEmpty()) {
							
							ImovelContaEnvio imovelContaEnvio = new ImovelContaEnvio();
							imovelContaEnvio.setId(ImovelContaEnvio.ENVIAR_PARA_IMOVEL_E_PARA_EMAIL);

							Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(imoveis);
							imovel.setImovelContaEnvio(imovelContaEnvio);
							
							fachada.atualizar(imovel);
						}
						
						//Atualiza a hr da confirmacao e a ultima alteracao
						dadoCliente.setConfirmacaoOnline(new Date());
						dadoCliente.setUltimaAlteracao(new Date());
						fachada.atualizar(dadoCliente);
						
						httpServletRequest.setAttribute("desabilitaMenu",true);
						
						String mensagemSucesso = "Os dados do cliente "
								+ cliente.getNome().toUpperCase()
								+ " foram atualizados com sucesso.";

						sessao.setAttribute("mensagemSucesso", mensagemSucesso);
					}
				}
			}
		}

		return retorno;
	}

	private boolean verificarAlteracaoDados(InserirCadastroEmailClientePortalActionForm form) {
		
		if (form.getNomeCliente() != null && form.getNomeClienteAux() != null
				&& !form.getNomeCliente().equals(form.getNomeClienteAux()))
			return true;
		
		if (form.getCpfCnpjCliente() != null
				&& form.getCpfCnpjClienteAux() != null
				&& !form.getCpfCnpjCliente().equals(form.getCpfCnpjClienteAux()))
			return true;
		
		if (form.getEmail() != null && form.getEmailAux() != null
				&& !form.getEmail().equals(form.getEmailAux()))
			return true;
		
		if (form.getNomeCliente() != null && form.getNomeClienteAux() == null)
			return true;
			
		if (form.getCpfCnpjCliente() != null
				&& form.getCpfCnpjClienteAux() == null)
			return true;
			
		if (form.getEmail() != null && form.getEmailAux() == null)
			return true;
		
		return false;
	}
}
