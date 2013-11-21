package gcom.gui.portal;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Classe Responsável por exibir o formulário de cadastro para solicitação de
 * contas por e-mail na Loja Virtual da Compesa
 * </p>
 * 
 * @author Magno Gouveia
 * @date 18/05/2011
 */
public class ExibirInserirCadastroEmailClientePortalAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInserirCadastroEmailClientePortalAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		httpServletRequest.setAttribute("voltarServicos", true);
		
		Fachada fachada = Fachada.getInstancia();

		InserirCadastroEmailClientePortalActionForm 
			form = (InserirCadastroEmailClientePortalActionForm) actionForm;
		
		boolean possuiDocumento = false;
		
		//Desabilita os campos se a matricula do imovel nao for informada
		//ou so habilita se a matricula do imovel existir
		if (form.getMatricula() == null || form.getMatricula().equals("")){
			// Se 1 - DESABILITA se 2 - HABILITA
			form.setDesabilitaCampos("1");
		} 
		
		/*
		if (form.getMatricula() != null && this.jaSolicitouContaPorEmail(form.getMatricula())) {

			httpServletRequest.setAttribute("contaPorEmailJaSolicitada", true);

			return actionMapping.findForward("voltarParaServicos");
		}
		*/
		
		if ((httpServletRequest.getParameter("voltar") != null && httpServletRequest.getParameter("voltar").equals("sim")) 
			|| (httpServletRequest.getParameter("ok") == null || !httpServletRequest.getParameter("ok").equals("sim"))) {
			
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
		if (httpServletRequest.getParameter("ok") != null
				&& httpServletRequest.getParameter("ok").equals("sim")) {
			
			form.setDesabilitaCampos("2");
			
			form.setConfirmarNomeCliente("");
			form.setConfirmarCpfCnpjCliente("");

			form.setEmailAux("");
			
			form.setMatricula(String.valueOf(sessao.getAttribute("matricula")));
			
			if (form.getMatricula() != null && !form.getMatricula().equals("")) {
				
				Integer idImovel = new Integer(form.getMatricula());
				
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarParametro( new ParametroSimples( FiltroImovel.ID,idImovel ));
				
				Collection colecaoImovel = fachada.pesquisar
								(filtroImovel, Imovel.class.getName());
				
				if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
					
					boolean estaEmCobrancaJudicial = fachada.verificarSituacaoImovelCobrancaJudicial(idImovel);

					// boolean estaNegativado =
					// fachada.verificarSituacaoImovelNegativacao(idImovel);
					
					if (estaEmCobrancaJudicial == true) {
						httpServletRequest.setAttribute("clienteEmCobrancaJudicial", true);
						return retorno;
						// throw new
						// ActionServletException("atencao.cliente.em.cobranca.judicial",
						//								null, idImovel.toString());				
						
					}
					
					FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
					filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
					filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
					filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, 2));
					filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
					
										
					Collection colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
					
					if (colecaoClienteImovel != null
							&& !colecaoClienteImovel.isEmpty()) {
						
						ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
						
						FiltroCliente filtroCliente = new FiltroCliente();
						filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, clienteImovel.getCliente().getId()));
						filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
						
						Collection clientes = fachada.pesquisar( filtroCliente, Cliente.class.getName() );
						
						if (clientes != null && !clientes.isEmpty()){
							
							Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(clientes);
							
							form.setIdCliente(cliente.getId());
							
							form.setNomeCliente(cliente.getNome());
							form.setNomeClienteAux(cliente.getNome());
											
							if (cliente.getClienteTipo() != null) {
								
								if (cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica() != null
										&& cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica() == new Integer("1").shortValue()) {
									
									httpServletRequest.setAttribute("pessoaFisicaJuridica", "pessoaFisica");
									
									form.setCpfCnpjCliente(cliente.getCpf());
									form.setCpfCnpjClienteAux(cliente.getCpf());
									
									form.setIndicadorCpf(true);
									
									if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
										possuiDocumento = true;
									}
									
									
								} else if (cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica() != null
										&& cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica() == new Integer("2").shortValue() ){
									
									httpServletRequest.setAttribute("pessoaFisicaJuridica","pessoaJuridica");
									
									form.setCpfCnpjCliente(cliente.getCnpj());
									form.setCpfCnpjClienteAux(cliente.getCnpj());
									
									form.setIndicadorCnpj(true);
									
									if (cliente.getCnpj() != null && !cliente.getCnpj().equals("")) {
										possuiDocumento = true;
									}
								}
								
								if (cliente.getEmail() != null && !cliente.getEmail().equals("")) {
									form.setEmail(cliente.getEmail());
									form.setEmailAux(cliente.getEmail());
								}
							}
							
							if (possuiDocumento) {
								httpServletRequest.setAttribute("possuiDocumento", "true");
							} else {
								httpServletRequest.setAttribute("possuiDocumento", "false");
							}
						}
					} else {
						httpServletRequest.setAttribute("imovelInexistente", true);
						return retorno;
						// throw new ActionServletException("atencao.imovel.inexistente");
					}
				} else {
					httpServletRequest.setAttribute("imovelInexistente", true);
					
					return retorno;
					// throw new ActionServletException("atencao.imovel.inexistente");
				}
			}
		}
		
		return retorno;
	}
	
	/**
	 * @author Magno Gouveia
	 * @date 02/06/2011
	 * @param idImovel
	 */
	/*
	private boolean jaSolicitouContaPorEmail(String idImovel) {
		
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, ClienteRelacaoTipo.USUARIO));
		filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		Collection collection = this.getFachada().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
		
		ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(collection);
		
		FiltroEmailClienteAlterado filtro = new FiltroEmailClienteAlterado();
		filtro.adicionarParametro(new ParametroSimples(FiltroEmailClienteAlterado.ID_CLIENTE, clienteImovel.getCliente().getId()));
		Collection colecao = this.getFachada().pesquisar(filtro, EmailClienteAlterado.class.getName());
		
		if(colecao != null && colecao.size() > 0){
			return true;
		}
		
		return false;
	}
	*/
}