package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.FiltroNegativadorRegistroTipo;
import gcom.cobranca.NegativadorRegistroTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter Negativador Registro Tipo
 * 
 * @author Yara Taciane 
 * @created 08/01/2008
 */
public class AtualizarNegativadorRegistroTipoAction extends GcomAction {
	/**
	 * @author Yara Taciane
	 * @date 08/01/2008
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
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarNegativadorRegistroTipoActionForm atualizarNegativadorRegistroTipoActionForm = (AtualizarNegativadorRegistroTipoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ATUALIZAR_NEGATIVADOR_REGISTRO_TIPO,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_ATUALIZAR_NEGATIVADOR_REGISTRO_TIPO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança

		NegativadorRegistroTipo negativadorRegistroTipo = (NegativadorRegistroTipo) sessao.getAttribute("negativadorRegistroTipo");

		// Pegando os dados do Formulário	
		String descricaoRegistroTipo = atualizarNegativadorRegistroTipoActionForm.getDescricaoRegistroTipo();
		String codigoRegistro = atualizarNegativadorRegistroTipoActionForm.getCodigoRegistro();
		Long time = Long.parseLong(atualizarNegativadorRegistroTipoActionForm.getTime()); 
		
		// Seta os campos para serem atualizados				
		
		if (descricaoRegistroTipo!= null	&& !descricaoRegistroTipo.equals("")) {

			negativadorRegistroTipo.setDescricaoRegistroTipo(descricaoRegistroTipo);

		} else {
			throw new ActionServletException("atencao.required", null,
					"Descrição do Tipo do Registro");
		}	

		// -------------------------------------------------------------------------------------

		if (codigoRegistro != null 	&& !codigoRegistro.equals("")) {
			
			if(!codigoRegistro.equalsIgnoreCase("H")&& !codigoRegistro.equalsIgnoreCase("D") && !codigoRegistro.equalsIgnoreCase("T") ){
				throw new ActionServletException("atencao.codigo_tipo_registro_invalido");
			}
				
			negativadorRegistroTipo.setCodigoRegistro(codigoRegistro);
			

		} else {
			throw new ActionServletException("atencao.required", null,"Código do Registro");
		}
		
		//------------------------------------------------------------------------------------		
		//Check para atualização realizada por outro usuário 
		FiltroNegativadorRegistroTipo filtroNegativadorRegistroTipo = new FiltroNegativadorRegistroTipo(); 
		filtroNegativadorRegistroTipo.adicionarParametro(new ParametroSimples(FiltroNegativadorRegistroTipo.ID, negativadorRegistroTipo.getId()));
		
		Collection collNegativadorRegistroTipo = Fachada.getInstancia().pesquisar(filtroNegativadorRegistroTipo, NegativadorRegistroTipo.class.getName());
		
		NegativadorRegistroTipo negativRegistroTipo = (NegativadorRegistroTipo)collNegativadorRegistroTipo.iterator().next();

		if (negativRegistroTipo.getUltimaAlteracao().getTime() != time){
			throw new ActionServletException("atencao.registro_remocao_nao_existente");
		}
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		negativadorRegistroTipo.setOperacaoEfetuada(operacaoEfetuada);
		negativadorRegistroTipo.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(negativadorRegistroTipo);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

			
		negativadorRegistroTipo.setUltimaAlteracao(new Date());
		
		// Atualiza o negativadorContrato 
		fachada.atualizar(negativadorRegistroTipo);
		
		montarPaginaSucesso(httpServletRequest, " Tipo do Registro do Negativador de código "
				+ negativadorRegistroTipo.getId() + " atualizado com sucesso.",
				"Realizar outra Manutenção do Tipo do Registro do Negativador",
				"exibirFiltrarNegativadorRegistroTipoAction.do?desfazer=S");
	
		return retorno;
	}
    
	
    
   
}
 
