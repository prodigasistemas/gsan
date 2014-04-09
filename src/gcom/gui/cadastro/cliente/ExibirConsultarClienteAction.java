package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.bean.ConsultarClienteHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de consultar de cliente
 * [UC0474] Consultar Imóvel
 * 
 * @author Rafael Santos
 * @date 12/09/2006
 */
public class ExibirConsultarClienteAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirConsultarCliente");
		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();
		
        //Obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);
		

		ConsultarClienteActionForm consultarClienteActionForm  = (ConsultarClienteActionForm) actionForm;

		String codigoCliente = null;
		
		String desabilitarPesquisaCliente = httpServletRequest.getParameter("desabilitarPesquisaCliente");
		
		if(desabilitarPesquisaCliente != null && !desabilitarPesquisaCliente.equals("")){
			httpServletRequest.setAttribute("desabilitarPesquisaCliente",desabilitarPesquisaCliente);
		}
		
		if(httpServletRequest.getParameter("tipoConsulta") != null && !httpServletRequest.getParameter("tipoConsulta").equals("")){
			codigoCliente = httpServletRequest.getParameter("idCampoEnviarDados");	
		}
		
		String limparForm = httpServletRequest.getParameter("limparForm");
		if(limparForm != null && !limparForm.equals("")){
			sessao.removeAttribute("colecaoTelefones");
			sessao.removeAttribute("colecaoClienteEnderecosHelper");
            sessao.removeAttribute("colecaoClienteFone");

			consultarClienteActionForm.setCodigoCliente(null);
			consultarClienteActionForm.setNomeCliente(null);	
			consultarClienteActionForm.setNomeAbreviado(null);
			consultarClienteActionForm.setDataVencimentoContas(null);
			consultarClienteActionForm.setIndicaorExecucao(null);
			consultarClienteActionForm.setTipoCliente(null);
			consultarClienteActionForm.setEmail(null);
			consultarClienteActionForm.setCpfCliente(null);
			consultarClienteActionForm.setRgCliente(null);
			consultarClienteActionForm.setDataEmissaoRGCliente(null);
			consultarClienteActionForm.setOrgaoEmissorRGCliente(null);
			consultarClienteActionForm.setDataNascimentoCliente(null);
			consultarClienteActionForm.setSexoCliente(null);
			consultarClienteActionForm.setProfissaoCliente(null);
			consultarClienteActionForm.setCnpjCliente(null);
			consultarClienteActionForm.setRamoAtividadeCliente(null);
			
		}
		
		if(!(httpServletRequest.getParameter("tipoConsulta") != null && !httpServletRequest.getParameter("tipoConsulta").equals(""))){
			codigoCliente = consultarClienteActionForm.getCodigoCliente();
		}
		
		if(codigoCliente != null && !codigoCliente.equals("")){
			 Cliente cliente = fachada.consultarCliente(new Integer(codigoCliente.trim()));
			 
			 if(cliente != null){

                httpServletRequest.setAttribute(
                        "codigoClienteNaoEncontrado", null);
                
                //nome do cliente
                consultarClienteActionForm.setCodigoCliente(codigoCliente);
                consultarClienteActionForm.setNomeCliente(cliente.getNome());

                //nome do cliente abreviado
                consultarClienteActionForm.setNomeAbreviado(cliente.getNomeAbreviado());
                
                //dia vencimento contas
                if(cliente.getDataVencimento() != null){
                	consultarClienteActionForm.setDataVencimentoContas(cliente.getDataVencimento().toString());
                }

                //tipo do cliente
                if(cliente.getClienteTipo() != null){
                	consultarClienteActionForm.setTipoCliente(cliente.getClienteTipo().getDescricao());
                }

                //indicador de cobranca
                if(cliente.getIndicadorAcaoCobranca() != null){
                	if(cliente.getIndicadorAcaoCobranca().shortValue() == 1){
                		consultarClienteActionForm.setIndicaorExecucao("SIM");	
                	}else{
                		consultarClienteActionForm.setIndicaorExecucao("NÃO");
                	}
                }
                
                //e-mail
                consultarClienteActionForm.setEmail(cliente.getEmail());
                
                //tipo de pessoa
                if(cliente.getClienteTipo() != null && cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica() != null){
                	//pessoa fisica
                	if(cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().equals(ClienteTipo.INDICADOR_PESSOA_FISICA)){
                		httpServletRequest.setAttribute("indicadorTipoCliente",ClienteTipo.INDICADOR_PESSOA_FISICA.shortValue());
                		
                		//cpf
                		consultarClienteActionForm.setCpfCliente((cliente.getCpfFormatado()));
                		
                		//rg
                		if(cliente.getOrgaoExpedidorRg() != null && cliente.getOrgaoExpedidorRg().getDescricaoAbreviada() != null && cliente.getUnidadeFederacao() != null && cliente.getUnidadeFederacao().getSigla()!= null){
                			consultarClienteActionForm.setRgCliente(Util.formatarRGApresentacao(cliente.getRg(),cliente.getOrgaoExpedidorRg().getDescricaoAbreviada(),cliente.getUnidadeFederacao().getSigla()));
                		} else {
                			consultarClienteActionForm.setRgCliente(null);
                		}
                		
                		//datade emissao
                		if(cliente.getDataEmissaoRg() != null){
                			consultarClienteActionForm.setDataEmissaoRGCliente(Util.formatarData(cliente.getDataEmissaoRg()));
                		} else {
                			consultarClienteActionForm.setDataEmissaoRGCliente(null);
                		}
                			
                		//orgao expedidor
                		if(cliente.getOrgaoExpedidorRg() != null && cliente.getOrgaoExpedidorRg().getDescricaoAbreviada() != null && cliente.getUnidadeFederacao() != null && cliente.getUnidadeFederacao().getSigla()!= null){
                			consultarClienteActionForm.setOrgaoEmissorRGCliente(cliente.getOrgaoExpedidorRg().getDescricaoAbreviada()+"/"+cliente.getUnidadeFederacao().getSigla());
                		} else {
                			consultarClienteActionForm.setOrgaoEmissorRGCliente(null);
                		}
                		
                		//data nascimento
                		consultarClienteActionForm.setDataNascimentoCliente(Util.formatarData(cliente.getDataNascimento()));
                		
                		//sexo
                		if(cliente.getPessoaSexo() != null){
                			consultarClienteActionForm.setSexoCliente(cliente.getPessoaSexo().getDescricao());
                		}
                		
                		//profissao
                		if(cliente.getProfissao() != null){
                			consultarClienteActionForm.setProfissaoCliente(cliente.getProfissao().getDescricao());
                		}
                	//pessoa juridica	
                	}else{
                		httpServletRequest.setAttribute("indicadorTipoCliente",ClienteTipo.INDICADOR_PESSOA_JURIDICA.shortValue());
                		
                		//cnpj
                		consultarClienteActionForm.setCnpjCliente(cliente.getCnpjFormatado());
                		
                		//ramo atividade
                		if(cliente.getRamoAtividade() != null){
                			consultarClienteActionForm.setRamoAtividadeCliente(cliente.getRamoAtividade().getDescricao());
                		}
                		
                	}
                }
                
                //coleção dos telefones
                Collection colecaoTelefones = fachada.pesquisarClienteFone(new Integer(codigoCliente.trim()));
                sessao.setAttribute("colecaoTelefones",colecaoTelefones);
                
                
                Collection colecaoEndereco = fachada.pesquisarEnderecoCliente(new Integer(codigoCliente.trim()));
                
                Collection colecaoEnderecoCliente = fachada.pesquisarClientesEnderecosAbreviado(new Integer(codigoCliente.trim()));
                
                
                
                Collection colecaoClienteEnderecosHelper = new ArrayList();
                
                //
                if(colecaoEndereco != null && !colecaoEndereco.isEmpty() ){
                	
                	ConsultarClienteHelper consultarClienteHelper = null;
                	
                	Iterator iterator = colecaoEndereco.iterator();
                	Iterator iteratorColecaoEnderecoCliente = colecaoEnderecoCliente.iterator();
                	
                	while (iterator.hasNext() || iteratorColecaoEnderecoCliente.hasNext() ) {
						ClienteEndereco clienteEndereco = (ClienteEndereco) iterator.next();
						
						String endereco = (String) iteratorColecaoEnderecoCliente.next();
						
						consultarClienteHelper = new ConsultarClienteHelper();
						//endereco tipo 
						if(clienteEndereco.getEnderecoTipo() != null){
							consultarClienteHelper.setTipoEndereco(clienteEndereco.getEnderecoTipo().getDescricao());
						}
						
						//indicador de endereço
						if(clienteEndereco.getIndicadorEnderecoCorrespondencia().equals(ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA)){
							consultarClienteHelper.setIndicadorEndereco("SIM");	
						}else{
							consultarClienteHelper.setIndicadorEndereco("NÃO");
						}
					
						consultarClienteHelper.setEnderecoClliente(endereco);
						
						colecaoClienteEnderecosHelper.add(consultarClienteHelper);
					}
                	
                }

                sessao.setAttribute("colecaoClienteEnderecosHelper",colecaoClienteEnderecosHelper);
                

                Collection colecaoClienteFone = fachada.pesquisarClienteFone(new Integer(codigoCliente.trim()));
                sessao.setAttribute("colecaoClienteFone",colecaoClienteFone);
			 }else{
                httpServletRequest.setAttribute(
                        "codigoClienteNaoEncontrado", "true");
                
                consultarClienteActionForm.setNomeCliente("CLIENTE INEXISTENTE");

			 }
		}
		

		return retorno;
	}
}
