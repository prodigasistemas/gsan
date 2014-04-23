package gcom.gui.cobranca;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Fernanda Paiva
 * @created 10 de Janeiro de 2006
 */
public class ExibirConsultarDebitoAction extends GcomAction {
    /**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        
    	// Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("consultarDebito");

        // Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);
        
        ConsultarDebitoActionForm consultarDebitoActionForm = (ConsultarDebitoActionForm) actionForm;
        
        Fachada fachada = Fachada.getInstancia();
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
        
        if (httpServletRequest.getParameter("menu") != null && !httpServletRequest.getParameter("menu").equals("")) {
        	consultarDebitoActionForm.setResponsavel("1");
        }
        
        if (httpServletRequest.getParameter("voltar") != null && !httpServletRequest.getParameter("voltar").equals("")) {
        	consultarDebitoActionForm.setTipoRelacao("-1");
        }
        
//        if (httpServletRequest.getParameter("voltar") != null && !httpServletRequest.getParameter("voltar").equals("")) {
//        	
//        	if (sessao.getAttribute("tipoPesquisa") != null && sessao.getAttribute("tipoPesquisa").equals("cliente")) {
//        		consultarDebitoActionForm.setCodigoClienteSuperior(null);
//        		consultarDebitoActionForm.setCodigoClienteSuperiorClone(null);
//        	} else if (sessao.getAttribute("tipoPesquisa") != null && sessao.getAttribute("tipoPesquisa").equals("clienteSuperior")) {
//        		consultarDebitoActionForm.setCodigoCliente(null);
//        		consultarDebitoActionForm.setCodigoClienteClone(null);
//        		consultarDebitoActionForm.setTipoRelacao("-1");
//        	} else {
//        		consultarDebitoActionForm.setCodigoClienteSuperior(null);
//        		consultarDebitoActionForm.setCodigoClienteSuperiorClone(null);
//        		consultarDebitoActionForm.setCodigoCliente(null);
//        		consultarDebitoActionForm.setCodigoClienteClone(null);
//        		consultarDebitoActionForm.setTipoRelacao("-1");        		
//        	}
//        	
//        	sessao.removeAttribute("tipoPesquisa");
//        	
//        	if (consultarDebitoActionForm.getCodigoClienteSuperior() != null && !consultarDebitoActionForm.getCodigoClienteSuperior().trim().equals("")) {
//        		consultarDebitoActionForm.setTipoRelacao("-1");
//        	}
//        }
        
        if (!fachada.verificarPermissaoConsultarDebitosIndicadoNaContaOuTodos(usuario)) {
			sessao.setAttribute("semPermissao", true);
		}
        
        // Remove as coleções e os valores da sessão
		sessao.removeAttribute("colecaoContaValores");
		sessao.removeAttribute("valorConta");
		sessao.removeAttribute("valorAcrescimo");
		sessao.removeAttribute("valorAgua");
		sessao.removeAttribute("valorEsgoto");
		sessao.removeAttribute("valorDebito");
		sessao.removeAttribute("valorCredito");
		sessao.removeAttribute("valorContaAcrescimo");
		sessao.removeAttribute("colecaoDebitoACobrar");
		sessao.removeAttribute("valorDebitoACobrar");
		sessao.removeAttribute("colecaoCreditoARealizar");
		sessao.removeAttribute("valorCreditoARealizar");
		sessao.removeAttribute("colecaoGuiaPagamentoValores");
		sessao.removeAttribute("valorGuiaPagamento");
		sessao.removeAttribute("valorTotalSemAcrescimo");
		sessao.removeAttribute("valorTotalComAcrescimo");
		sessao.removeAttribute("colecaoDebitoCliente");
		sessao.removeAttribute("tipoRelacao");
		sessao.removeAttribute("valorImposto");
        
        String idDigitadoEnterCliente = (String) consultarDebitoActionForm.getCodigoCliente();
        String idDigitadoEnterImovel = (String) consultarDebitoActionForm.getCodigoImovel();
        String idDigitadoEnterClienteSuperior = (String) consultarDebitoActionForm.getCodigoClienteSuperior();
      
        // verifica se o codigo do cliente foi digitado
        if (idDigitadoEnterCliente != null
                && !idDigitadoEnterCliente.trim().equals("")
                && Integer.parseInt(idDigitadoEnterCliente) > 0) {
            
        	FiltroCliente filtroCliente = new FiltroCliente();
			
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idDigitadoEnterCliente));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
				// O Cliente foi encontrado
				if (((Cliente) ((List) clienteEncontrado).get(0))
						.getIndicadorUso().equals(
								ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.cliente.inativo",
							null, ""
									+ ((Cliente) ((List) clienteEncontrado)
											.get(0)).getId());
				}

				consultarDebitoActionForm
						.setCodigoCliente(((Cliente) ((List) clienteEncontrado)
								.get(0)).getId().toString());
				consultarDebitoActionForm
						.setCodigoClienteClone(((Cliente) ((List) clienteEncontrado)
							.get(0)).getId().toString());
				consultarDebitoActionForm
						.setNomeCliente(((Cliente) ((List) clienteEncontrado)
								.get(0)).getNome());

			} else {
				httpServletRequest.setAttribute("corCliente","exception");
               	consultarDebitoActionForm
               			.setNomeCliente(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);

			}
        }
        
        // verifica se o codigo do cliente superior foi digitado
        if (idDigitadoEnterClienteSuperior != null
                && !idDigitadoEnterClienteSuperior.trim().equals("")
                && Integer.parseInt(idDigitadoEnterClienteSuperior) > 0) {
            
        	FiltroCliente filtroCliente = new FiltroCliente();
			
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idDigitadoEnterClienteSuperior));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
				// O Cliente foi encontrado
				if (((Cliente) ((List) clienteEncontrado).get(0))
						.getIndicadorUso().equals(
								ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.cliente.inativo",
							null, ""
									+ ((Cliente) ((List) clienteEncontrado)
											.get(0)).getId());
				}

				consultarDebitoActionForm
						.setCodigoClienteSuperior(((Cliente) ((List) clienteEncontrado)
								.get(0)).getId().toString());
				consultarDebitoActionForm
						.setCodigoClienteSuperiorClone(((Cliente) ((List) clienteEncontrado)
							.get(0)).getId().toString());
				consultarDebitoActionForm
						.setNomeClienteSuperior(((Cliente) ((List) clienteEncontrado)
								.get(0)).getNome());

			} else {
				httpServletRequest.setAttribute("corClienteSuperior","exception");
               	consultarDebitoActionForm
               			.setNomeClienteSuperior(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);

			}
        }
        
        if (idDigitadoEnterImovel != null && !idDigitadoEnterImovel.trim().equals("")) {
			// Pesquisa o imovel na base
			String imovelEncontrado = fachada.pesquisarInscricaoImovel(new Integer(idDigitadoEnterImovel));
        	
			if (imovelEncontrado != null && !imovelEncontrado.equalsIgnoreCase("")) {
				
				// O imovel foi encontrado
				consultarDebitoActionForm.setCodigoImovel(idDigitadoEnterImovel);
				consultarDebitoActionForm.setCodigoImovelClone(idDigitadoEnterImovel);
				consultarDebitoActionForm.setInscricaoImovel(imovelEncontrado);
			} else {
					httpServletRequest.setAttribute("corImovel","exception");
               		consultarDebitoActionForm
               			.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
			}
        }

        FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
        
        filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(FiltroClienteRelacaoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
        
		Collection<ClienteRelacaoTipo> collectionClienteRelacaoTipo = fachada.pesquisar(filtroClienteRelacaoTipo, ClienteRelacaoTipo.class.getName() );
		
		if (collectionClienteRelacaoTipo != null && !collectionClienteRelacaoTipo.isEmpty()) 
		{
			httpServletRequest.setAttribute("collectionClienteRelacaoTipo", collectionClienteRelacaoTipo);
		}
		else
		{
	        throw new ActionServletException(
	        		"atencao.collectionClienteRelacaoTipo_inexistente", null, "id");
		}
		return retorno;
    }
}
