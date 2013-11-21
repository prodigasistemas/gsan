package gcom.gui.portal;

import gcom.cadastro.ContaBraileHelper;
import gcom.cadastro.cliente.FiltroOrgaoExpedidorRg;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * [RM2923] Solicitar Conta Braile - Loja Virtual da Compesa
 * 
 * @author Magno Gouveia
 * @date 19/05/2011
 *
 */

public class InserirCadastroContaBrailePortalAction extends GcomAction {
	
	private static final String EXCEPTION = "exception";
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("validarDados");
		
		Fachada fachada = Fachada.getInstancia();
		
		httpServletRequest.setAttribute("voltarServicos", true);
		
		InserirCadastroContaBrailePortalActionForm form = (InserirCadastroContaBrailePortalActionForm) actionForm;
	
		ContaBraileHelper contaBraileHelper = new ContaBraileHelper();
		
		FiltroOrgaoExpedidorRg filtroOrgaoExpedidor = new FiltroOrgaoExpedidorRg(FiltroOrgaoExpedidorRg.DESCRICAO_ABREVIADA);		
		filtroOrgaoExpedidor.adicionarParametro(new ParametroSimples(FiltroOrgaoExpedidorRg.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection orgaosExpedidores = fachada.pesquisar(filtroOrgaoExpedidor, OrgaoExpedidorRg.class.getName());
		httpServletRequest.setAttribute("orgaosExpedidores", orgaosExpedidores);
		
		FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao(FiltroUnidadeFederacao.SIGLA);		
		Collection unidadesFederacao = fachada.pesquisar(filtroUnidadeFederacao, UnidadeFederacao.class.getName());		
		httpServletRequest.setAttribute("unidadesFederacao", unidadesFederacao);
		
		if(httpServletRequest.getAttribute("cpfInvalido") != null){
			httpServletRequest.removeAttribute("cpfInvalido");
		}
		if(httpServletRequest.getAttribute("cpfJaExiste") != null){
			httpServletRequest.removeAttribute("cpfJaExiste");
		}
		if(httpServletRequest.getAttribute("cpfObrigatorio") != null){
			httpServletRequest.removeAttribute("cpfObrigatorio");
		}
		
		//Acessando direto pelo link, sem ser pelo link do email
		if (httpServletRequest.getParameter("confirmar") == null
				|| !httpServletRequest.getParameter("confirmar").equals("sim")) {
			
			// Valida o CPF/CNPJ do Cliente
//			if (form.getCpfCnpjCliente() != null
//					&& !form.getCpfCnpjCliente().equals("")) {
//				
//				if (form.isIndicadorCpf()) {
//					
//					if (form.getCpfCnpjCliente().length() < 11) {
//						
//						httpServletRequest.setAttribute("exception", "CPF/CNPJ não é válido!");
//						return retorno;
//					}
//					
//					//É um CPF
//					boolean valido = gcom.util.Util.validacaoCPF(form.getCpfCnpjCliente());
//					form.setIndicadorCpf(true);
//					
//					if (!valido) {
//						
//						httpServletRequest.setAttribute("exception", "CPF/CNPJ não é válido!");
//						return retorno;
//					} else {
//						
//						//Verifica se o cpf ja esta associado a outro cliente
//						String cpf = form.getCpfCnpjCliente();
//						FiltroCliente filtroCliente = new FiltroCliente();
//						filtroCliente.adicionarParametro( new ParametroSimples( FiltroCliente.CPF, cpf));
//						Collection clientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
//						
//						if (clientes != null && !clientes.isEmpty()) {
//							
//							Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(clientes);
//							
//							if (clientes.size() >= 1
//									&& !(cliente.getId().equals(form.getIdCliente()))) {
//								httpServletRequest.setAttribute("exception", "CPF/CNPJ já está cadastrado!");
//								return retorno;
//							}
//						}
//					}
//				} else if (form.isIndicadorCnpj()) {
//					
//					if (form.getCpfCnpjCliente().length() < 14) {
//						
//						httpServletRequest.setAttribute("exception", "CPF/CNPJ não é válido!");
//						return retorno;
//					}
//					
//					boolean valido = gcom.util.Util.validacaoCNPJ(form.getCpfCnpjCliente());
//					form.setIndicadorCnpj(true);
//					
//					if (!valido) {
//
//						httpServletRequest.setAttribute("exception", "CPF/CNPJ não é válido!");
//						return retorno;
//						
//					} else {
//						
//						FiltroCliente filtroCliente = new FiltroCliente();
//						filtroCliente.adicionarParametro( new ParametroSimples( FiltroCliente.CNPJ, form.getCpfCnpjCliente()));
//						Collection clientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
//						if (clientes != null && !clientes.isEmpty()) {
//							
//							Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(clientes);
//							
//							if (clientes.size() >= 1
//									&& !(cliente.getId().equals(form.getIdCliente()))) {
//								
//								httpServletRequest.setAttribute("exception", "CPF/CNPJ já está cadastrado!");
//								return retorno;
//							}
//						}
//					}
//				} else {
//					httpServletRequest.setAttribute("exception", "CPF/CNPJ não é válido!");
//					return retorno;
//				}
//			} else {
//				httpServletRequest.setAttribute("exception", "CPF/CNPJ é obrigatório!");
//				return retorno;
//			}
			
			//	Valida o email
			
			if (form.getEmail() != null && !form.getEmail().equals("")) {
				
				String email = form.getEmail();
				
				if (!email.contains("@") || email.contains(" ")) {
					httpServletRequest.setAttribute(EXCEPTION, "E-mail inválido!");
					return retorno;
				}
			} else {
				httpServletRequest.setAttribute(EXCEPTION, "E-mail é obrigatório!");
				return retorno;
			}
			
			if ( httpServletRequest.getParameter("possuiDocumento") != null && 
					(!httpServletRequest.getParameter("possuiDocumento").equals("true")
							|| httpServletRequest.getParameter("possuiDocumento").equals("false"))){
				
				if( form.getConfirmarCpfCnpjCliente() == null || 
					!form.getConfirmarCpfCnpjCliente().equals("confirmado") ){
					
					httpServletRequest.setAttribute(EXCEPTION, "É necessário confirma o CPF/CNPJ do cliente!");
					
					return retorno;
				}
			}
			
			// Valida o CPF do Solicitante
			if (Util.verificarNaoVazio(form.getCpfSolicitante())) {
				if (form.getCpfSolicitante().length() < 11) {
					httpServletRequest.setAttribute(EXCEPTION, "CPF/CNPJ não é válido!");
					return retorno;
				}
				
				//É um CPF
				boolean valido = Util.validacaoCPF(form.getCpfSolicitante());
				
				if (!valido) {

					httpServletRequest.setAttribute(EXCEPTION, "CPF/CNPJ não é válido!");
					return retorno;
				}
			} else {

				httpServletRequest.setAttribute(EXCEPTION, "CPF/CNPJ é obrigatório!");
				return retorno;
			}
			
			// Montar Helper
			contaBraileHelper.setMatricula(form.getMatricula());
			contaBraileHelper.setCpfCnpjCliente((String)this.getSessao(httpServletRequest).getAttribute("cpfCnpj"));
			contaBraileHelper.setCpfSolicitante(form.getCpfSolicitante());
			contaBraileHelper.setDataExpedicao(form.getDataExpedicao());
			contaBraileHelper.setEmail(form.getEmail());
			contaBraileHelper.setNomeCliente(form.getNomeCliente());
			contaBraileHelper.setNomeSolicitante(form.getNomeSolicitante());
			contaBraileHelper.setOrgaoExpeditor(form.getOrgaoExpeditor());
			contaBraileHelper.setRg(form.getRg());
			
			if(form.getTelefoneContato() != null && !form.getTelefoneContato().trim().equals("")){
				String telefone = form.getTelefoneContato().trim();
				telefone = telefone.replace("(", "");
				telefone = telefone.replace(")", "");
				telefone = telefone.replace("-", "");
				telefone = telefone.replace(" ", "");
				contaBraileHelper.setTelefoneContato(telefone);
			}
			
			contaBraileHelper.setUnidadeFederacao(form.getUnidadeFederacao());
			contaBraileHelper.setIndicadorCpf(form.isIndicadorCpf());
			contaBraileHelper.setIndicadorCnpj(form.isIndicadorCnpj());
			
			String protocolo = this.getFachada().obterProtocoloAtendimento();
			contaBraileHelper.setProtocoloAtendimento(protocolo);
			
			this.getFachada().inserirSolicitacaoContaBraile(contaBraileHelper);
			
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			httpServletRequest.setAttribute("contaBraileSolicitadaComSucesso", true);
			httpServletRequest.setAttribute("mensagemBraileSolicitadaComSucesso", sistemaParametro
					.getMensagemContaBraile()
					+ ". Registro Atendimento: " + protocolo + "."); 
			
			retorno = actionMapping.findForward("contaBraileSolicitadaComSucesso");
			
			/*
			String mensagemsucesso = ;
			
			httpServletRequest.setAttribute("desabilitaMenu", true);
			
			montarPaginaSucesso(httpServletRequest, mensagemsucesso,
					"Solicitar outra Conta em BRAILE.",
					"exibirInserirCadastroContaBrailePortalAction.do?voltar=sim");
			*/
		}
		
		return retorno;
	}
}
