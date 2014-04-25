package gcom.gui.cobranca.spcserasa;

import java.util.ArrayList;
import java.util.Collection;

import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter Negativador remove um ou mais objeto do tipo
 * NegativdorContrato
 * 
 * @author Thiago Vieira
 * @created 24/12/2007
 */
public class RemoverNegativadorAction extends GcomAction {
	
	/**
	 * @author Thiago Vieira
	 * @date 24/12/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

        HttpSession sessao = httpServletRequest.getSession(false);
        
	    Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");


		//Tenho que criar a operação para ContratoNegativador -> OPERACAO_CLIENTE_REMOVER
	    //------------ REGISTRAR TRANSAÇÃO ----------------
	    Operacao operacao = new Operacao();
	    operacao.setId(Operacao.OPERACAO_REMOVER_NEGATIVADOR);
	    OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
	    operacaoEfetuada.setOperacao(operacao);
	    //------------ REGISTRAR TRANSAÇÃO ----------------
		

		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		// mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}
        
		//------------ REGISTRAR TRANSAÇÃO ----------------
    	UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(usuario,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
    	Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
    	colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
        //------------ REGISTRAR TRANSAÇÃO ----------------

        try {
        	fachada.remover(ids, Negativador.class.getName(), operacaoEfetuada, colecaoUsuarios);
        } catch (Exception e) {
			e.getCause();
			if (e != null && e.getMessage() != null	&& 
					"atencao.dependencias.existentes" .equalsIgnoreCase(e.getMessage())) {
//				throw new ActionServletException("atencao.negativador.com.vinculo");
				throw new ActionServletException("atencao.dependencias.existentes");
			}

		}

		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest,
					 ids.length +
					" Negativador(es) removido(s) com sucesso.",
					"Realizar outra manutenção de Negativador",
					"exibirFiltrarNegativadorAction.do?desfazer=S");
		}

		return retorno;
	}

}
