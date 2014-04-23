package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter Contrato Negativador remove um ou mais objeto do tipo
 * NegativdorContrato
 * 
 * @author Yara Taciane de Souza
 * @created 20/12/2007
 */
public class RemoverNegativadorExclusaoMotivoAction extends GcomAction {
	/**
	 * @author Yara Taciane de Souza
	 * @date 20/12/2007
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
		

		//Tenho que criar a operação para ContratoNegativador -> OPERACAO_CLIENTE_REMOVER 
		
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
	    Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

 
	    OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//	        //------------ REGISTRAR TRANSAÇÃO ----------------
//	        Operacao operacao = new Operacao();
//	        operacao.setId(Operacao.OPERACAO_REMOVER_CONTRATO_NEGATIVADOR);
//
//	        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//	        operacaoEfetuada.setOperacao(operacao);
//	        //------------ REGISTRAR TRANSAÇÃO ----------------
			
		
		

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
        	 fachada.remover(ids, NegativadorExclusaoMotivo.class.getName(), operacaoEfetuada, colecaoUsuarios);
        } catch (Exception e) {
			e.getCause();
			if (e != null && e.getMessage() != null	&& "atencao.dependencias.existentes" .equalsIgnoreCase(e.getMessage())) {
				throw new ActionServletException("atencao.dependencias.existentes");
			}

		}
        
       
		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest,
					 ids.length +
					"Motivo(os) da Exclusão do Negativador removido(s) com sucesso.",
					"Realizar outra manutenção de Motivo da Exclusão do Negativador",
					"exibirFiltrarNegativadorExclusaoMotivoAction.do?desfazer=S");
		}

		return retorno;
	}

}
