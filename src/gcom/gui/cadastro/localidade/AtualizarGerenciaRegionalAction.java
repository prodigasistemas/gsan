package gcom.gui.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarGerenciaRegionalAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarGerenciaRegionalActionForm atualizarGerenciaRegionalActionForm = (AtualizarGerenciaRegionalActionForm) actionForm;

		GerenciaRegional gerenciaRegional = (GerenciaRegional) sessao.getAttribute("gerenciaRegionalAtualizar");

		gerenciaRegional.setNome(atualizarGerenciaRegionalActionForm.getNome());
		gerenciaRegional.setCnpjGerenciaRegional(atualizarGerenciaRegionalActionForm.getCnpjGerenciaRegional());
		gerenciaRegional.setNomeAbreviado(atualizarGerenciaRegionalActionForm.getNomeAbreviado());
		
		gerenciaRegional.setFone(atualizarGerenciaRegionalActionForm.getTelefone());
		gerenciaRegional.setRamalFone(atualizarGerenciaRegionalActionForm.getRamal());
		gerenciaRegional.setFax(atualizarGerenciaRegionalActionForm.getFax());
		gerenciaRegional.setEmail(atualizarGerenciaRegionalActionForm.getEmail());
		
		Short indicadorUso = Short.decode(atualizarGerenciaRegionalActionForm.getIndicadorUso());
		gerenciaRegional.setIndicadorUso(indicadorUso);
		
		Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");
		
		// O Endereço é obrigatório.
		if (colecaoEnderecos == null || colecaoEnderecos.equals("")) {
			throw new ActionServletException("atencao.required", null, "Endereço ");
		}
		
		Localidade endereco = (Localidade) colecaoEnderecos.iterator().next();

		// Atualização do Endereço 
	    if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
	    	gerenciaRegional.setLogradouroCep(endereco.getLogradouroCep());
	    	gerenciaRegional.setLogradouroBairro(endereco.getLogradouroBairro());
	    	gerenciaRegional.setComplementoEndereco(endereco.getComplementoEndereco());
	    	gerenciaRegional.setNumeroImovel(endereco.getNumeroImovel());
	    	gerenciaRegional.setEnderecoReferencia(endereco.getEnderecoReferencia());
	    }
	    
	    // Cliente
	    if (atualizarGerenciaRegionalActionForm.getIdCliente() != null && !atualizarGerenciaRegionalActionForm.getIdCliente().equalsIgnoreCase("") ){
			FiltroCliente filtroCliente = new FiltroCliente();
	
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
					atualizarGerenciaRegionalActionForm.getIdCliente()));
	
			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());
	
			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
	
				Cliente cliente = (Cliente) colecaoCliente.iterator().next();
	
				Integer clienteFuncionario = fachada.verificarClienteSelecionadoFuncionario(new Integer(atualizarGerenciaRegionalActionForm.getIdCliente()));
				
				if(clienteFuncionario == null){
					throw new ActionServletException("atencao.cliente_selecionado_nao_e_funcionario");
				}
				
				gerenciaRegional.setCliente(cliente);
				
			} else {
				throw new ActionServletException("atencao.cliente.inexistente");
			}
	    }
		fachada.atualizarGerenciaRegional(gerenciaRegional);

		montarPaginaSucesso(httpServletRequest, "Gerência Regional de código "
				+ gerenciaRegional.getId().toString() + " realizada com sucesso.",
				"Realizar outra Manutenção de Gerência Regional",
				"exibirFiltrarGerenciaRegionalAction.do?menu=sim");
		return retorno;
	}
}
