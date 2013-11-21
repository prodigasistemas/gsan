package gcom.gui.portal;

import gcom.cadastro.ContaBraile;
import gcom.cadastro.FiltroContaBraile;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.FiltroOrgaoExpedidorRg;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Classe Responsável por exibir o formulário de cadastro para solicitação de
 * contas em braile na Loja Virtual da Compesa
 * </p>
 * 
 * @author Magno Gouveia
 * @date 18/05/2011
 */
public class ExibirInserirCadastroContaBrailePortalAction extends
		GcomAction {
	
	/**
	 * 
	 * [UC1128] Solicitar Conta Braile
	 * 
	 * @author Hugo Leonardo
	 * @Date 02/03/2011
	 *
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirInserirCadastroContaBrailePortal");
		
		Fachada fachada = Fachada.getInstancia();
		
		InserirCadastroContaBrailePortalActionForm form = (InserirCadastroContaBrailePortalActionForm) actionForm;
		
		FiltroOrgaoExpedidorRg filtroOrgaoExpedidor = new FiltroOrgaoExpedidorRg(FiltroOrgaoExpedidorRg.DESCRICAO_ABREVIADA);
		filtroOrgaoExpedidor.adicionarParametro(new ParametroSimples(FiltroOrgaoExpedidorRg.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection orgaosExpedidores = fachada.pesquisar(filtroOrgaoExpedidor, OrgaoExpedidorRg.class.getName());
		httpServletRequest.setAttribute("orgaosExpedidores", orgaosExpedidores);
		
		FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao(FiltroUnidadeFederacao.SIGLA);
		Collection unidadesFederacao = fachada.pesquisar(filtroUnidadeFederacao, UnidadeFederacao.class.getName());
		httpServletRequest.setAttribute("unidadesFederacao", unidadesFederacao);
		
		httpServletRequest.setAttribute("voltarServicos", true);
		
		form.setMatricula(this.getSessao(httpServletRequest).getAttribute("matricula").toString());
		
		//Desabilita os campos se a matricula do imovel nao for informada
		//e so habilita se a matricula do imovel existir
		if (form.getMatricula() == null || form.getMatricula().equals("")) {
			// Se 1 - DESABILITA se 2 - HABILITA
			form.setDesabilitaCampos("1");
		} else {
			
			if(this.jaSolicitouContaBraile(form.getMatricula())){
				
				httpServletRequest.setAttribute("contaBraileJaSolicitada", true);
				
				return retorno;			
			}
		}
		
		if ((httpServletRequest.getParameter("voltar") != null 
				&& httpServletRequest.getParameter("voltar").equals("sim"))
				|| (httpServletRequest.getParameter("ok") == null 
						|| !httpServletRequest.getParameter("ok").equals("sim"))) {
			
			form.setConfirmarCpfCnpjCliente("");
			form.setConfirmarNomeCliente("");
			form.setCpfCnpjCliente("");
			form.setCpfCnpjClienteAux("");
			form.setCpfSolicitante("");
			form.setEmail("");
			form.setEmailAux("");
			form.setIndicadorCnpj(false);
			form.setIndicadorCpf(false);
			form.setNomeCliente("");
			form.setNomeClienteAux("");
			form.setNomeSolicitante("");
			form.setTelefoneContato("");
			form.setRg("");
			form.setOrgaoExpeditor("-1");
			form.setUnidadeFederacao("-1");
			form.setDataExpedicao("");
		}
		
		// if (httpServletRequest.getParameter("ok") != null
		// && httpServletRequest.getParameter("ok").equals("sim")) {
			
		form.setDesabilitaCampos("2");
		
		if (form.getMatricula() != null && !form.getMatricula().equals("")) {
			
			Integer idImovel = new Integer(form.getMatricula());
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro( new ParametroSimples( FiltroImovel.ID, idImovel));
			
			Collection colecaoImovel = fachada.pesquisar
							(filtroImovel, Imovel.class.getName());
			
			if (!Util.isVazioOrNulo(colecaoImovel)) {
					
				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel.adicionarParametro( new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
				filtroClienteImovel.adicionarParametro( new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
				filtroClienteImovel.adicionarParametro( new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, 2));
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
				
				Collection colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
				
				if (!Util.isVazioOrNulo(colecaoClienteImovel)){
					
					ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
					FiltroCliente filtroCliente = new FiltroCliente();
					filtroCliente.adicionarParametro( new ParametroSimples(FiltroCliente.ID, clienteImovel.getCliente().getId()) );
					filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
					Collection colecaoClientes = fachada.pesquisar( filtroCliente, Cliente.class.getName() );
					
					if (!Util.isVazioOrNulo(colecaoClientes)){
						
						Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoClientes);
						
						form.setIdCliente(cliente.getId());
						form.setNomeCliente(cliente.getNome());
						form.setNomeClienteAux(cliente.getNome());
										
						if (cliente.getClienteTipo() != null) {
							
							if (cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica() != null
									&& cliente
											.getClienteTipo()
											.getIndicadorPessoaFisicaJuridica()
											.compareTo(ConstantesSistema.SIM) == 0) {
								
								form.setCpfCnpjCliente(cliente.getCpf());
								form.setCpfCnpjClienteAux(cliente.getCpf());
								form.setIndicadorCpf(true);
								
							} else if (cliente.getClienteTipo()
									.getIndicadorPessoaFisicaJuridica() != null
									&& cliente
											.getClienteTipo()
											.getIndicadorPessoaFisicaJuridica()
											.compareTo(ConstantesSistema.NAO) == 0) {
								
								form.setCpfCnpjCliente(cliente.getCnpj());
								form.setCpfCnpjClienteAux(cliente.getCnpj());
								form.setIndicadorCnpj(true);
							}
							
							if (cliente.getEmail() != null
									&& !cliente.getEmail().equals("")) {
								
								form.setEmail(cliente.getEmail());
								form.setEmailAux(cliente.getEmail());
							}
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
			// }
		
		return retorno;
	}
	
	/**
	 * @author Magno Gouveia
	 * @date 02/06/2011
	 * @param idImovel
	 */
	private boolean jaSolicitouContaBraile(String idImovel) {

		FiltroContaBraile filtro = new FiltroContaBraile();
		filtro.adicionarParametro(new ParametroSimples(FiltroContaBraile.IMOVEL_ID, idImovel));
		
		Collection colecao = this.getFachada().pesquisar(filtro, ContaBraile.class.getName());
		
		if(colecao != null && colecao.size() > 0){
			return true;
		}
		
		return false;
	}
}
