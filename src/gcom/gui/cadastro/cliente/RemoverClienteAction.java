package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverClienteAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

        String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        Fachada fachada = Fachada.getInstancia();

        // mensagem de erro quando o usuário tenta excluir sem ter selecionado nenhum registro
        if (ids == null || ids.length == 0) {
            throw new ActionServletException("atencao.registros.nao_selecionados");
        }
		
        FiltroCliente filtroCliente = new FiltroCliente();
        
        for(int i = 0; i < ids.length; i++){
			filtroCliente.limparListaParametros();
			
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, ids[i]));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());
			
			Cliente clienteNaBase = (Cliente) ((List)colecaoCliente).get(0);
        	
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_CLIENTE_REMOVER, new Integer(ids[i]), new Integer(ids[i]), 
					new UsuarioAcaoUsuarioHelper(usuario,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
	    	
			registradorOperacao.registrarOperacao(clienteNaBase);
			fachada.remover(clienteNaBase);
        }

        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest,
            		ids.length + " Cliente(s) removido(s) com sucesso",
                    "Realizar outra Manutenção de Cliente",
                    "exibirManterClienteAction.do?menu=sim");
        }

        return retorno;
    }
}