package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.hidrometro.RetornoControleHidrometro;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC1032]	MANTER RETORNO CONTROLE HIDROMETRO
 * 
 * @author Wallace Thierre
 * @date 03/08/2010
 * 
 */


public class AtualizarRetornoControleHidrometroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");	

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		

		AtualizarRetornoControleHidrometroActionForm form =
			(AtualizarRetornoControleHidrometroActionForm) actionForm;
		
		RetornoControleHidrometro retornoControleHidrometro = (RetornoControleHidrometro)
			sessao.getAttribute("atualizarRetornoControleHidrometro");
	
			//Atualiza a entidade com os valores do formulário		
			retornoControleHidrometro.setDescricao(form.getDescricao());
			retornoControleHidrometro.setDataCorrente(new Date());		
			retornoControleHidrometro.setIndicadorUso(new Short(form.getIndicadorUso()));
			retornoControleHidrometro.setIndicadorGeracao(new Short(form.getIndicadorGeracao()));
		
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_RETORNO_CONTROLE_HIDROMETRO_ATUALIZAR, retornoControleHidrometro.getId(),
					retornoControleHidrometro.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			
			registradorOperacao.registrarOperacao(retornoControleHidrometro);
			// ------------ REGISTRAR TRANSAÇÃO ----------------

			
			fachada.atualizar(retornoControleHidrometro);
			
			montarPaginaSucesso(httpServletRequest, "Retorno de Controle de Hidrometro "
					+form.getDescricao() + " atualizado com sucesso.",
					"Realizar outra Manutenção Retorno de Controle de Hidrometro ",
					"exibirFiltrarRetornoControleHidrometroAction.do?menu=sim");

		return retorno;
	}
}
