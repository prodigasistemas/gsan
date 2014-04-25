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
public class InserirNegativadorRetornoMotivoAction extends GcomAction {

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
        InserirNegativadorRetornoMotivoActionForm form = (InserirNegativadorRetornoMotivoActionForm) actionForm; 

        Integer idNegativador = 0;
        short codigoRetornoMotivo = 0;
        String descricaoRetornoMotivo = "";
        short indicadorRegistroAceito = 0;
        
        NegativadorRetornoMotivo negativadorRetornoMotivo = new NegativadorRetornoMotivo();
        Negativador negativador = new Negativador();

      //------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_NEGATIVADOR_RETORNO_MOTIVO,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_INSERIR_NEGATIVADOR_RETORNO_MOTIVO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------
        
        if (form.getIdNegativador() != null && !form.getIdNegativador().equals("")){
        	idNegativador = new Integer(form.getIdNegativador());
             negativador.setId(idNegativador);
             negativadorRetornoMotivo.setNegativador(negativador);
        }
        if (form.getCodigoMotivo() != null && !form.getCodigoMotivo().equals("")){
        	codigoRetornoMotivo = Short.parseShort(form.getCodigoMotivo());
        	negativadorRetornoMotivo.setCodigoRetornoMotivo(codigoRetornoMotivo);
        } 
        if (form.getDescricaoRetornoMotivo() != null && !form.getDescricaoRetornoMotivo().equals("")){
        	descricaoRetornoMotivo = form.getDescricaoRetornoMotivo();
        	negativadorRetornoMotivo.setDescricaoRetornocodigo(descricaoRetornoMotivo);
        }
        if (form.getIndicadorRegistroAceito() != null && !form.getIndicadorRegistroAceito().equals("")){
        	indicadorRegistroAceito = Short.parseShort(form.getIndicadorRegistroAceito());
        	negativadorRetornoMotivo.setIndicadorRegistroAceito(indicadorRegistroAceito);
        }
        
        
        
        
        
        
        FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = new FiltroNegativadorRetornoMotivo();
        
        //check do código do motivo
        //caso já exista no cadastro levanta exception
        if (codigoRetornoMotivo != 0){
			filtroNegativadorRetornoMotivo.limparListaParametros();
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.CODIGO_RETORNO_MOTIVO, codigoRetornoMotivo));
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR, negativadorRetornoMotivo.getNegativador().getId()));
        	
        	Collection codigoMotivoEncontrado = Fachada.getInstancia().pesquisar(filtroNegativadorRetornoMotivo, NegativadorRetornoMotivo.class.getName());
        	        	
        	if (codigoMotivoEncontrado != null && !codigoMotivoEncontrado.isEmpty()) {
        		throw new ActionServletException("atencao.codigo_motivo_negativador_retorno_motivo_ja_existe_cadastro");
        	}
		}
        
            
        if (indicadorRegistroAceito == ConstantesSistema.INDICADOR_REGISTRO_ACEITO){
        	
			filtroNegativadorRetornoMotivo.limparListaParametros();			
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.INDICADOR_REGISTRO_ACEITO, ConstantesSistema.INDICADOR_REGISTRO_ACEITO));
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR,negativadorRetornoMotivo.getNegativador().getId()));
			filtroNegativadorRetornoMotivo.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
				
			Collection collNegativadorRetornoMotivo = Fachada.getInstancia().pesquisar(filtroNegativadorRetornoMotivo, NegativadorRetornoMotivo.class.getName());
			
			if (collNegativadorRetornoMotivo != null && !collNegativadorRetornoMotivo.isEmpty()){
				throw new ActionServletException("atencao.codigo_motivo_correspondente_registro_aceito_ja_existe_cadastro");
			}
		}
        
       
        negativadorRetornoMotivo.setIndicadorUso(new Short("1").shortValue());
        negativadorRetornoMotivo.setUltimaAlteracao(new Date());
        
        
//      ------------ REGISTRAR TRANSAÇÃO ----------------
        negativadorRetornoMotivo.setOperacaoEfetuada(operacaoEfetuada);
        negativadorRetornoMotivo.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(negativadorRetornoMotivo);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

             
        // Insere o NegativadorRetornoMotivo
		@SuppressWarnings("unused")
		Integer codigoNegativadorRetornoMotivo = (Integer) Fachada.getInstancia().inserir(negativadorRetornoMotivo);

        montarPaginaSucesso(httpServletRequest, "Motivo de retorno do negativador "
				+ descricaoRetornoMotivo + " inserido com sucesso.",
				"Inserir outro Motivo de Retorno do Registro do Negativador",
				"exibirInserirNegativadorRetornoMotivoAction.do?menu=sim",
				"","");
        
		return retorno;
        
    }
}
