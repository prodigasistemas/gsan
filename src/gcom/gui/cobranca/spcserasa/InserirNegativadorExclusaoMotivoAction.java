package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.FiltroNegativadorExclusaoMotivo;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorExclusaoMotivo;
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
 * Processamento para inserir o Motivo da Exclusão do Negativador
 * 
 * 
 * @author Yara Taciane de Souza
 * @date 18/12/2007
 */
public class InserirNegativadorExclusaoMotivoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirNegativadorExclusaoMotivoActionForm inserirNegativadorExclusaoMotivoActionForm = (InserirNegativadorExclusaoMotivoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_NEGATIVADOR_EXCLUSAO_MOTIVO,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_INSERIR_NEGATIVADOR_EXCLUSAO_MOTIVO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
	
		Fachada fachada = Fachada.getInstancia();
		Integer idNegativadorExclusaoMotivo = null;

		// cria o objeto negativador contrato para ser inserido
		NegativadorExclusaoMotivo negativadorExclusaoMotivo = new NegativadorExclusaoMotivo();
		
        //-------------------------------------------------------------------------
		if (inserirNegativadorExclusaoMotivoActionForm.getIdNegativador() != null
				&& !inserirNegativadorExclusaoMotivoActionForm
						.getIdNegativador().equals("")) {

			Negativador negativador = new Negativador();
			negativador.setId(new Integer(
					inserirNegativadorExclusaoMotivoActionForm
							.getIdNegativador()));
			negativadorExclusaoMotivo.setNegativador(negativador);

		} else {
			throw new ActionServletException("atencao.required", null,
					"Negativador");
		}

		// Verificar a existência de código do motivo
		// -------------------------------------------------------------------------------------

		if (inserirNegativadorExclusaoMotivoActionForm.getCodigoMotivo() != null
				&& !inserirNegativadorExclusaoMotivoActionForm
						.getCodigoMotivo().equals("")) {
			
			String codigoExclusaoMotivo = inserirNegativadorExclusaoMotivoActionForm.getCodigoMotivo();
			

			FiltroNegativadorExclusaoMotivo filtroNegativadorExclusaoMotivo = new FiltroNegativadorExclusaoMotivo();

			filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorExclusaoMotivo.CODIGO_EXCLUSAO_MOTIVO, codigoExclusaoMotivo));
			filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorExclusaoMotivo.NEGATIVADOR_ID, negativadorExclusaoMotivo.getNegativador().getId()));
		
			
			Collection collNegativadorExclusaoMotivo= fachada.pesquisar(filtroNegativadorExclusaoMotivo,
					NegativadorExclusaoMotivo.class.getName());
			
			
			if(collNegativadorExclusaoMotivo != null && !collNegativadorExclusaoMotivo.isEmpty()){
			
				throw new ActionServletException("atencao.codigo_motivo_ja_existe_cadastro");
				
			}
			
			negativadorExclusaoMotivo.setCodigoExclusaoMotivo(Short.parseShort(codigoExclusaoMotivo));
			

		} else {
			throw new ActionServletException("atencao.required", null,"Código do Motivo");
		}
		
		//------------------------------------------------------------------------------------
		if (inserirNegativadorExclusaoMotivoActionForm.getDescricaoExclusaoMotivo() != null
				&& !inserirNegativadorExclusaoMotivoActionForm
						.getDescricaoExclusaoMotivo().equals("")) {
			
			String descricaoExlusaoMotivo = inserirNegativadorExclusaoMotivoActionForm.getDescricaoExclusaoMotivo();
			negativadorExclusaoMotivo.setDescricaoExclusaoMotivo(descricaoExlusaoMotivo);			
		
		}else{
			throw new ActionServletException("atencao.required", null,"Descrição do Motivo Exclusão");
		}

		//----------------------------------------------------------------------------------------

		if (inserirNegativadorExclusaoMotivoActionForm.getIdCobrancaDebitoSituacao() != null
				&& !inserirNegativadorExclusaoMotivoActionForm
						.getIdCobrancaDebitoSituacao().equals("")) {

			CobrancaDebitoSituacao cobrancaDebitoSituacao = new CobrancaDebitoSituacao();
			cobrancaDebitoSituacao.setId(new Integer(
					inserirNegativadorExclusaoMotivoActionForm.getIdCobrancaDebitoSituacao()));
			negativadorExclusaoMotivo.setCobrancaDebitoSituacao(cobrancaDebitoSituacao);

		} else {
			throw new ActionServletException("atencao.required", null,
					"Cobrança Débito Situação");
		}

		
		short indicadorUso = 1;		
		negativadorExclusaoMotivo.setIndicadorUso(indicadorUso);
		
		
		if (negativadorExclusaoMotivo != null) {

			negativadorExclusaoMotivo.setUltimaAlteracao(new Date());

			 idNegativadorExclusaoMotivo = (Integer)
			 fachada.inserir(negativadorExclusaoMotivo);
		} else {
			throw new ActionServletException(
					"atencao.informar.linha.criterio.cobranca");
		}

		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		negativadorExclusaoMotivo.setOperacaoEfetuada(operacaoEfetuada);
		negativadorExclusaoMotivo.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(negativadorExclusaoMotivo);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		

		montarPaginaSucesso(httpServletRequest,
				"Motivo da Exclusão do Negativador"
						+ idNegativadorExclusaoMotivo
						+ " inserido com sucesso.",
				"Inserir outro Motivo da Exclusão do Negativador",
				"exibirInserirNegativadorExclusaoMotivoAction.do?menu=sim","exibirAtualizarNegativadorExclusaoMotivoAction.do?idRegistroAtualizacao="
				+ idNegativadorExclusaoMotivo, "Atualizar Motivo da Exclusão do Negativador Inserido");
		
		

		return retorno;
	}

}
