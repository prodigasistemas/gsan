package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Description of the Class
 * 
 * @author Thiago Vieira
 */
public class AtualizarNegativadorRetornoMotivoAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

//    	 localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		// Pega o form do cliente
        AtualizarNegativadorRetornoMotivoActionForm form = (AtualizarNegativadorRetornoMotivoActionForm) actionForm; 

        String descricaoRetornoMotivo = "";
        short indicadorRegistroAceito = 0;
        short indicadorUso = 0;
        Long time = Long.parseLong(form.getTime());
        Integer idNegativadorRetornoMotivo = new Integer(form.getIdNegativadorRetornoMotivo());
        Integer idNegativador = new Integer(form.getIdNegativador());
        
		NegativadorRetornoMotivo negativadorRetornoMotivo = new NegativadorRetornoMotivo();
      //------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ATUALIZAR_NEGATIVADOR_RETORNO_MOTIVO,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_CLIENTE_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
	  // ------------ REGISTRAR TRANSAÇÃO ----------------
        
        if (form.getDescricaoRetornoMotivo() != null && !form.getDescricaoRetornoMotivo().equals("")){
        	descricaoRetornoMotivo = form.getDescricaoRetornoMotivo();
        	negativadorRetornoMotivo.setDescricaoRetornocodigo(descricaoRetornoMotivo);
        }
        if (form.getIndicadorRegistroAceito() != null && !form.getIndicadorRegistroAceito().equals("")){
        	indicadorRegistroAceito = Short.parseShort(form.getIndicadorRegistroAceito());
        	negativadorRetornoMotivo.setIndicadorRegistroAceito(indicadorRegistroAceito);
        }
        if (form.getIndicadorUso() != null && !form.getIndicadorUso().equals("")){
        	indicadorUso = Short.parseShort(form.getIndicadorUso());
        	negativadorRetornoMotivo.setIndicadorUso(indicadorUso);
        }
        
//      Check para atualização realizada por outro usuário 
		FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = new FiltroNegativadorRetornoMotivo(); 
		filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.ID, idNegativadorRetornoMotivo));
		filtroNegativadorRetornoMotivo.adicionarCaminhoParaCarregamentoEntidade("negativador");
		
		Collection collNegativadorRetornoMotivo = Fachada.getInstancia().pesquisar(filtroNegativadorRetornoMotivo, NegativadorRetornoMotivo.class.getName());
		NegativadorRetornoMotivo negativadorRetornoMotivoAtual = (NegativadorRetornoMotivo)collNegativadorRetornoMotivo.iterator().next();
		
		if (negativadorRetornoMotivoAtual.getUltimaAlteracao().getTime() != time){
			throw new ActionServletException("atencao.registro_remocao_nao_existente");
		}
		
		if (indicadorRegistroAceito == ConstantesSistema.INDICADOR_REGISTRO_ACEITO){
			filtroNegativadorRetornoMotivo.limparListaParametros();
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorRetornoMotivo.INDICADOR_REGISTRO_ACEITO, ConstantesSistema.INDICADOR_REGISTRO_ACEITO));
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR, idNegativador));
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimplesDiferenteDe(
					FiltroNegativadorRetornoMotivo.CODIGO_RETORNO_MOTIVO, negativadorRetornoMotivoAtual.getCodigoRetornoMotivo()));
			
			collNegativadorRetornoMotivo = Fachada.getInstancia().pesquisar(filtroNegativadorRetornoMotivo, NegativadorRetornoMotivo.class.getName());
			
			if (collNegativadorRetornoMotivo != null && !collNegativadorRetornoMotivo.isEmpty()){
				throw new ActionServletException("atencao.negativador_retorno_motivo_ja_existe_cadastro");
			}
		}
		
	    negativadorRetornoMotivo.setId(idNegativadorRetornoMotivo);
        Negativador negativador = new Negativador();
        negativador.setId(idNegativador);
        negativadorRetornoMotivo.setNegativador(negativador);
        negativadorRetornoMotivo.setCodigoRetornoMotivo(negativadorRetornoMotivoAtual.getCodigoRetornoMotivo());
        
		negativadorRetornoMotivo.setUltimaAlteracao(new Date());
		
		
	     //------------ REGISTRAR TRANSAÇÃO ----------------
		negativadorRetornoMotivo.setOperacaoEfetuada(operacaoEfetuada);
		negativadorRetornoMotivo.adicionarUsuario(usuario, 
        		UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
        registradorOperacao.registrarOperacao(negativadorRetornoMotivo);
        //------------ REGISTRAR TRANSAÇÃO ----------------
		
		
		
		 // atualiza o NegativadorRetornoMotivo
		Fachada.getInstancia().atualizar(negativadorRetornoMotivo);

        montarPaginaSucesso(httpServletRequest, "Motivo de retorno do registro do negativador "
				+ descricaoRetornoMotivo + " atualizado com sucesso.",
				"Realizar outra manutenção de motivo de retorno do registro do negativador",
				"exibirFiltrarNegativadorRetornoMotivoAction.do?menu=sim",
				"","");
        
		return retorno;
        
    }
}
