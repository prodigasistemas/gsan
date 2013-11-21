package gcom.gui.cadastro;

import gcom.cadastro.ContaBraileHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * [UC1128] Solicitar Conta Braile
 * 
 * @author Hugo Leonardo
 * @Date 02/03/2011
 *
 */

public class InserirCadastroContaBraileAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		Fachada fachada = Fachada.getInstancia();
		
		InserirCadastroContaBraileActionForm form = 
			(InserirCadastroContaBraileActionForm) actionForm;
	
		ContaBraileHelper contaBraileHelper = new ContaBraileHelper();
		
		//Acessando direto pelo link, sem ser pelo link do email
		if ( httpServletRequest.getParameter("confirmar") == null || 
				!httpServletRequest.getParameter("confirmar").equals("sim") ){
			
			//Valida o CPF/CNPJ do Cliente
			if ( form.getCpfCnpjCliente() != null && !form.getCpfCnpjCliente().equals("")){
				
				if ( form.isIndicadorCpf() ){
					
					if ( form.getCpfCnpjCliente().length() < 11 ){
						
						throw new ActionServletException("atencao.cliente_cpf_cnpj_invalido");
					}
					
					//É um CPF
					boolean valido = gcom.util.Util.validacaoCPF(form.getCpfCnpjCliente());
					form.setIndicadorCpf(true);
					
					if ( !valido ){
						
						throw new ActionServletException("atencao.cliente_cpf_cnpj_invalido");
					}else{
						
						//Verifica se o cpf ja esta associado a outro cliente
						String cpf = form.getCpfCnpjCliente();
						FiltroCliente filtroCliente = new FiltroCliente();
						filtroCliente.adicionarParametro( new ParametroSimples( FiltroCliente.CPF, cpf));
						Collection clientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
						
						if ( clientes != null && !clientes.isEmpty() ){
							
							Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(clientes);
							
							if ( clientes.size() >= 1 && !( cliente.getId().equals(form.getIdCliente())) ){
								
								throw new ActionServletException("atencao.ja.existe.cliente.com.cpfCnpj");
							}
						}
					}
					
				}else if ( form.isIndicadorCnpj() ){
					
					if ( form.getCpfCnpjCliente().length() < 14){
						
						throw new ActionServletException("atencao.cliente_cpf_cnpj_invalido");
					}
					
					boolean valido = gcom.util.Util.validacaoCNPJ(form.getCpfCnpjCliente());
					form.setIndicadorCnpj(true);
					if ( !valido ){
						
						throw new ActionServletException("atencao.cliente_cpf_cnpj_invalido");
					}else{
						
						FiltroCliente filtroCliente = new FiltroCliente();
						filtroCliente.adicionarParametro( new ParametroSimples( FiltroCliente.CNPJ, form.getCpfCnpjCliente()));
						Collection clientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
						if ( clientes != null && !clientes.isEmpty() ){
							
							Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(clientes);
							
							if ( clientes.size() >= 1 && !( cliente.getId().equals(form.getIdCliente()) ) ){
								
								throw new ActionServletException("atencao.ja.existe.cliente.com.cpfCnpj");
							}
						}
					}
			
				}else{
					
					throw new ActionServletException("atencao.cliente_cpf_cnpj_invalido");
				}
			}else {
				
				//throw new ActionServletException("atencao.cpf_cpnf_obrigatorio");
			}
			
			if ( httpServletRequest.getParameter("possuiDocumento") != null && 
					(!httpServletRequest.getParameter("possuiDocumento").equals("true")
							|| httpServletRequest.getParameter("possuiDocumento").equals("false"))){
				if( form.getConfirmarCpfCnpjCliente() == null || 
					!form.getConfirmarCpfCnpjCliente().equals("confirmado") ){
				
					throw new ActionServletException("atencao.necessario.confirmar.cpfCnpj.cliente");
				}
			}
			
			// Valida o CPF do Solicitante
			if ( form.getCpfSolicitante() != null && !form.getCpfSolicitante().equals("")){
				
				if ( form.getCpfSolicitante().length() < 11 ){
					
					throw new ActionServletException("atencao.solicitante_cpf_invalido");
				}
				
				//É um CPF
				boolean valido = gcom.util.Util.validacaoCPF(form.getCpfSolicitante());
				
				if ( !valido ){
					
					throw new ActionServletException("atencao.solicitante_cpf_invalido");
				}
			}else {
				
				throw new ActionServletException("atencao.cpf_solicitante_obrigatorio");
			}
			
			// Montar Helper
			contaBraileHelper.setMatricula(form.getMatricula());
			contaBraileHelper.setCpfCnpjCliente(form.getCpfCnpjCliente());
			contaBraileHelper.setCpfSolicitante(form.getCpfSolicitante());
			contaBraileHelper.setDataExpedicao(form.getDataExpedicao());
			contaBraileHelper.setEmail(form.getEmail());
			contaBraileHelper.setNomeCliente(form.getNomeCliente());
			contaBraileHelper.setNomeSolicitante(form.getNomeSolicitante());
			contaBraileHelper.setOrgaoExpeditor(form.getOrgaoExpeditor());
			contaBraileHelper.setRg(form.getRg());
			contaBraileHelper.setTelefoneContato(form.getTelefoneContato());
			contaBraileHelper.setUnidadeFederacao(form.getUnidadeFederacao());
			contaBraileHelper.setIndicadorCpf(form.isIndicadorCpf());
			contaBraileHelper.setIndicadorCnpj(form.isIndicadorCnpj());
			
			String protocolo = this.getFachada().obterProtocoloAtendimento();
			contaBraileHelper.setProtocoloAtendimento(protocolo);
			
			this.getFachada().inserirSolicitacaoContaBraile(contaBraileHelper);
			
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			String mensagemsucesso = sistemaParametro.getMensagemContaBraile() 
				+ ". Registro Atendimento: " + protocolo + ".";
			
			httpServletRequest.setAttribute("desabilitaMenu",true);
			
			montarPaginaSucesso(httpServletRequest, mensagemsucesso, 
				"Solicitar outra Conta em BRAILE.",
				"exibirInserirCadastroContaBraileAction.do?voltar=sim");
		}
		
		return retorno;
	}
}
