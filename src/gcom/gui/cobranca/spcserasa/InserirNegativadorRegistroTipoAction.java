package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.Negativador;
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

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processamento para inserir o negativador registro tipo
 * 
 * 
 * @author Yara Taciane de Souza
 * @date 07/01/2008
 */
public class InserirNegativadorRegistroTipoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirNegativadorRegistroTipoActionForm inserirNegativadorRegistroTipoActionForm = (InserirNegativadorRegistroTipoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_NEGATIVADOR_REGISTRO_TIPO,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_INSERIR_NEGATIVADOR_REGISTRO_TIPO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		Fachada fachada = Fachada.getInstancia();
		Integer idNegativadorRegistroTipo = null;

		// cria o objeto negativador contrato para ser inserido
		NegativadorRegistroTipo negativadorRegistroTipo = new NegativadorRegistroTipo();
		
        //-------------------------------------------------------------------------
		if (inserirNegativadorRegistroTipoActionForm.getIdNegativador() != null
				&& !inserirNegativadorRegistroTipoActionForm
						.getIdNegativador().equals("-1")) {

			Negativador negativador = new Negativador();
			negativador.setId(new Integer(
					inserirNegativadorRegistroTipoActionForm
							.getIdNegativador()));
			negativadorRegistroTipo.setNegativador(negativador);

		} else {
			throw new ActionServletException("atencao.required", null,
					"Negativador");
		}

		// Verificar a existência de código do motivo
		// -------------------------------------------------------------------------------------

		if (inserirNegativadorRegistroTipoActionForm.getCodigoRegistro() != null
				&& !inserirNegativadorRegistroTipoActionForm
						.getCodigoRegistro().equals("")) {
			
					
			String codigoRegistroTipo = inserirNegativadorRegistroTipoActionForm.getCodigoRegistro();
			
			if(!codigoRegistroTipo.equalsIgnoreCase("H")&& !codigoRegistroTipo.equalsIgnoreCase("D") && !codigoRegistroTipo.equalsIgnoreCase("T") ){
				throw new ActionServletException("atencao.codigo_tipo_registro_invalido");
			}
				
			negativadorRegistroTipo.setCodigoRegistro(codigoRegistroTipo.toUpperCase());
			

		} else {
			throw new ActionServletException("atencao.required", null,"Código do Registro");
		}
		
		//------------------------------------------------------------------------------------
		if (inserirNegativadorRegistroTipoActionForm.getDescricaoRegistroTipo() != null
				&& !inserirNegativadorRegistroTipoActionForm
						.getDescricaoRegistroTipo().equals("")) {
			
			String descricaoRegistroTipo = inserirNegativadorRegistroTipoActionForm.getDescricaoRegistroTipo();
			negativadorRegistroTipo.setDescricaoRegistroTipo(descricaoRegistroTipo.toUpperCase());			
		
		}else{
			throw new ActionServletException("atencao.required", null,"Descrição do Tipo do Registro ");
		}

		
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		negativadorRegistroTipo.setOperacaoEfetuada(operacaoEfetuada);
		negativadorRegistroTipo.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(negativadorRegistroTipo);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
	
		
		if (negativadorRegistroTipo != null) {

			negativadorRegistroTipo.setUltimaAlteracao(new Date());

			 idNegativadorRegistroTipo = (Integer)
			 fachada.inserir(negativadorRegistroTipo);
		} else {
			throw new ActionServletException(
					"atencao.informar.linha.criterio.cobranca");
		}

		// dúvida, os 2 ultimos parametros.
		montarPaginaSucesso(httpServletRequest,
				"Tipo do Registro do Negativador "
						+ idNegativadorRegistroTipo
						+ " inserido com sucesso.",
				"Inserir outro Tipo do Registro do Negativador",
				"exibirInserirNegativadorRegistroTipoAction.do?menu=sim","exibirAtualizarNegativadorRegistroTipoAction.do?idRegistroAtualizacao="
				+ idNegativadorRegistroTipo, "Atualizar Tipo de Registro do Negativador Inserido");

		return retorno;
	}

}
