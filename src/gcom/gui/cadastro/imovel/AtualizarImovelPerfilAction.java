package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
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
 * [UC1032]	MANTER IMOVEL PERFIL
 * 
 * @author Wallace Thierre
 * @date 04/10/2010
 * 
 */

public class AtualizarImovelPerfilAction extends GcomAction{
	

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");	

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		

		AtualizarImovelPerfilActionForm form =
			(AtualizarImovelPerfilActionForm) actionForm;
		
		ImovelPerfil imovelPerfil = (ImovelPerfil)
			sessao.getAttribute("atualizarImovelPerfil");
	
			//Atualiza a entidade com os valores do formulário		
			imovelPerfil.setDescricao(form.getDescricao());imovelPerfil.setDescricao(form.getDescricao());
			imovelPerfil.setIndicadorUso(new Short(form.getIndicadorUso()));
			imovelPerfil.setIndicadorGeracaoAutomatica(new Short(form.getIndicadorGeracaoAutomatica()));
			imovelPerfil.setIndicadorInserirManterPerfil(new Short (form.getIndicadorInserirManterPerfil()));
			imovelPerfil.setIndicadorGerarDadosLeitura(new Short(form.getIndicadorGerarDadosLeitura()));
			imovelPerfil.setIndicadorBloquearRetificacao(new Short(form.getIndicadorBloquearRetificacao()));
			imovelPerfil.setIndicadorGrandeConsumidor(new Short(form.getIndicadorGrandeConsumidor()));
			imovelPerfil.setIndicadorBloqueaDadosSocial(new Short(form.getIndicadorBloquearDadosSocial()));
			imovelPerfil.setIndicadorGeraDebitoSegundaViaConta(new Short(form.getIndicadorGeraDebitoSegundaViaConta()));
			imovelPerfil.setUltimaAlteracao(new Date());
		
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_IMOVEL_PERFIL_ATUALIZAR, imovelPerfil.getId(),
					imovelPerfil.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			
			registradorOperacao.registrarOperacao(imovelPerfil);
			// ------------ REGISTRAR TRANSAÇÃO ----------------

			
			fachada.atualizar(imovelPerfil);
			
			montarPaginaSucesso(httpServletRequest, "Imovel Perfil "
					+form.getDescricao() + " atualizado com sucesso.",
					"Realizar outra Manutenção Imovel Perfil",
					"exibirFiltrarImovelPerfilAction.do?menu=sim");

		return retorno;
	}

}
