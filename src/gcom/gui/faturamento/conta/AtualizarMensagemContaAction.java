package gcom.gui.faturamento.conta;

import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMensagem;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Administrador
 */
public class AtualizarMensagemContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		AtualizarMensagemContaActionForm atualizarMensagemContaActionForm = (AtualizarMensagemContaActionForm) actionForm;

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		// Variavel para testar se o campo naum obrigatorio esta vazio

		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
        /*
		 * [UC0107] Registrar Transação
		 * 
		 */
        
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_CONTA_MENSAGEM_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_CONTA_MENSAGEM_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		// [UC0107] -Fim- Registrar Transação
		
		
		Collection colecaoContaMensagem = (Collection) sessao.getAttribute("colecaoContaMensagem");
		
		if (colecaoContaMensagem != null && !colecaoContaMensagem.isEmpty()){
			for (Iterator iter = colecaoContaMensagem.iterator(); iter
					.hasNext();) {
				ContaMensagem contaMensagem = (ContaMensagem) iter.next();
				
				contaMensagem.setDescricaoContaMensagem01(atualizarMensagemContaActionForm.getMensagemConta01());
				contaMensagem.setDescricaoContaMensagem02(atualizarMensagemContaActionForm.getMensagemConta02());
				contaMensagem.setDescricaoContaMensagem03(atualizarMensagemContaActionForm.getMensagemConta03());
				//contaMensagem.setUltimaAlteracao(new java.util.Date());
				
				// regitrando operacao
				contaMensagem.setOperacaoEfetuada(operacaoEfetuada);
				contaMensagem.adicionarUsuario(usuarioLogado,	UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
				registradorOperacao.registrarOperacao(contaMensagem);
				
				fachada.atualizarMensagemConta(contaMensagem);
			}
			
		}
		
		String anoMes = atualizarMensagemContaActionForm.getReferenciaFaturamento();
		
		montarPaginaSucesso(httpServletRequest, "Mensagem da Conta com referência "+ anoMes + " atualizada com sucesso.",
				"Realizar outra Manutenção de Mensagem da Conta",
				"exibirFiltrarMensagemContaAction.do?menu=sim");
		
		return retorno;
	}
}
