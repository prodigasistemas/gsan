package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.FiltroNegativadorExclusaoMotivo;
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
 * Action form de manter  Negativador Exclusão Motivo
 * 
 * @author Yara Taciane 
 * @created 04/01/2008
 */
public class AtualizarNegativadorExclusaoMotivoAction extends GcomAction {
	/**
	 * @author Yara Taciane
	 * @date 04/01/2008
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

		AtualizarNegativadorExclusaoMotivoActionForm atualizarNegativadorExclusaoMotivoActionForm = (AtualizarNegativadorExclusaoMotivoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
			
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ATUALIZAR_NEGATIVADOR_EXCLUSAO_MOTIVO,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_ATUALIZAR_NEGATIVADOR_EXCLUSAO_MOTIVO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança

		NegativadorExclusaoMotivo negativadorExclusaoMotivo = (NegativadorExclusaoMotivo) sessao.getAttribute("negativadorExclusaoMotivo");

		// Pegando os dados do Formulário	
		String descricaoExclusaoMotivo = atualizarNegativadorExclusaoMotivoActionForm.getDescricaoExclusaoMotivo();
		String indicadorUso = atualizarNegativadorExclusaoMotivoActionForm.getIndicadorUso();
		String idCobrancaDebitoSituacao = atualizarNegativadorExclusaoMotivoActionForm.getIdCobrancaDebitoSituacao();
		
		Long time = Long.parseLong(atualizarNegativadorExclusaoMotivoActionForm.getTime()); 
		
		// Seta os campos para serem atualizados				
		
		if (descricaoExclusaoMotivo!= null	&& !descricaoExclusaoMotivo.equals("")) {

			negativadorExclusaoMotivo.setDescricaoExclusaoMotivo(descricaoExclusaoMotivo);

		} else {
			throw new ActionServletException("atencao.required", null,
					"Descrição Exclusão Motivo");
		}	
		
		if (idCobrancaDebitoSituacao!= null	&& !idCobrancaDebitoSituacao.equals("-1")) {

			CobrancaDebitoSituacao cobrancaDebitoSituacao = new CobrancaDebitoSituacao();
			cobrancaDebitoSituacao.setId(new Integer(idCobrancaDebitoSituacao));
			negativadorExclusaoMotivo.setCobrancaDebitoSituacao(cobrancaDebitoSituacao);

		} else {
			throw new ActionServletException("atencao.required", null,
					"Cobrança Débito Situação");
		}		
		
		if (indicadorUso!= null	&& !indicadorUso.equals("")) {		
		  negativadorExclusaoMotivo.setIndicadorUso(Short.parseShort(indicadorUso));		
		}else{
			throw new ActionServletException("atencao.required", null,
			"indicador de Uso");
		}
		
		//Check para atualização realizada por outro usuário 
		FiltroNegativadorExclusaoMotivo filtroNegativadorExclusaoMotivo = new FiltroNegativadorExclusaoMotivo(); 
		filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.ID, negativadorExclusaoMotivo.getId()));
		
		Collection collNegativadorExclusaoMotivo = Fachada.getInstancia().pesquisar(filtroNegativadorExclusaoMotivo, NegativadorExclusaoMotivo.class.getName());
		
		NegativadorExclusaoMotivo negativExclusaoMotivo = (NegativadorExclusaoMotivo)collNegativadorExclusaoMotivo.iterator().next();

		if (negativExclusaoMotivo.getUltimaAlteracao().getTime() != time){
			throw new ActionServletException("atencao.registro_remocao_nao_existente");
		}
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		negativadorExclusaoMotivo.setOperacaoEfetuada(operacaoEfetuada);
		negativadorExclusaoMotivo.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(negativadorExclusaoMotivo);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

			
		negativadorExclusaoMotivo.setUltimaAlteracao(new Date());
		
		// Atualiza o negativadorContrato 
		fachada.atualizar(negativadorExclusaoMotivo);
		
		montarPaginaSucesso(httpServletRequest, "negativador Exclusao Motivo de código "
				+ negativadorExclusaoMotivo.getId() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Negativador Exclusao Motivo",
				"exibirFiltrarNegativadorExclusaoMotivoAction.do?desfazer=S");
	
		return retorno;
	}
    
	
    
   
}
 
