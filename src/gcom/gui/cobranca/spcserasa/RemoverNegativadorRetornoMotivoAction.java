package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Remove Motivo de Retorno do Negativador 
 * 
 * @author Thiago Vieira
 * @created 24/12/2007
 */
public class RemoverNegativadorRetornoMotivoAction extends GcomAction {
	
	/**
	 * @author Thiago Vieira
	 * @date 24/12/2007
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

        HttpSession sessao = httpServletRequest.getSession(false);
        
	    Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");


		//Tenho que criar a operação para ContratoNegativador -> OPERACAO_CLIENTE_REMOVER
	    //------------ REGISTRAR TRANSAÇÃO ----------------
	    Operacao operacao = new Operacao();
	    operacao.setId(Operacao.OPERACAO_REMOVER_NEGATIVADOR_RETORNO_MOTIVO);
	    OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
	    operacaoEfetuada.setOperacao(operacao);
	    //------------ REGISTRAR TRANSAÇÃO ----------------
		

		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		// mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}
        
//		Verifica se algum dos registros já foi removido durante a operação
        int tamanhoIds = ids.length;
        for (int i = 0 ; i < tamanhoIds; i++){
        	Integer id = new Integer(ids[i]);
        	FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = new FiltroNegativadorRetornoMotivo(); 
    		filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.ID, id));
    		Collection collNegativadorRetornoMotivo = Fachada.getInstancia().pesquisar(filtroNegativadorRetornoMotivo, NegativadorRetornoMotivo.class.getName());
    		
    		if (collNegativadorRetornoMotivo == null || collNegativadorRetornoMotivo.isEmpty()){
    			throw new ActionServletException("atencao.registro_remocao_ja_removido");
    		}
        }
		
		//------------ REGISTRAR TRANSAÇÃO ----------------
    	UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(usuario,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
    	Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
    	colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
        //------------ REGISTRAR TRANSAÇÃO ----------------

        try {
        	fachada.remover(ids, NegativadorRetornoMotivo.class.getName(), operacaoEfetuada, colecaoUsuarios);
        } catch (Exception e) {
			e.getCause();
			if (e != null && e.getMessage() != null	&& 
					"atencao.dependencias.existentes" .equalsIgnoreCase(e.getMessage())) {
//				throw new ActionServletException("atencao.negativador.com.vinculo");
				throw new ActionServletException("atencao.dependencias.existentes");
			}

		}
	
		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest,
					 ids.length +
					" motivo(s) de retorno do registro do negativador(es) removido(s) com sucesso.",
					"Realizar outra manutenção de Negativador",
					"exibirFiltrarNegativadorRetornoMotivoAction.do?desfazer=S");
		}

		return retorno;
	}

}
