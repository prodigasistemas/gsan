package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que inicializa o pré-processamento da pagina de exibição do filtro 
 * de pesquisa de comandos de negativação com o tipo de comando "por matricula"
 * selecionado
 * 
 * @author: Thiago Vieira
 * @date: 10/01/2007
 */
public class ExibirFiltrarComandoNegativacaoTipoMatriculaAction extends GcomAction {

    /**
     * Método de execução principal do action
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

        ActionForward retorno = actionMapping.findForward("exibirFiltrarComandoNegativacaoTipoMatricula");
        FiltrarComandoNegativacaoTipoMatriculaActionForm form = (FiltrarComandoNegativacaoTipoMatriculaActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = getSessao(httpServletRequest);
        
        form.setUsuarioOk("false");
        
//      carregar coleção de negativadores para select do form de filtro
        Collection colecaoNegativador = (Collection) sessao.getAttribute("colecaoNegativador");

		if (colecaoNegativador == null) {
			FiltroNegativador filtroNegativador = new FiltroNegativador();			
			filtroNegativador.setCampoOrderBy(FiltroNegativador.CLIENTE);			
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroNegativador.adicionarParametro(
					new ParametroSimples(FiltroNegativador.INDICADOR_USO, 
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroNegativador.setConsultaSemLimites(true);

			colecaoNegativador = fachada.pesquisar(filtroNegativador, Negativador.class.getName());

			if (colecaoNegativador == null || colecaoNegativador.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"NEGATIVADOR");
			} else {
				sessao.setAttribute("colecaoNegativador", colecaoNegativador);
			}
		}
        
		
        if (form.getTipoPesquisaIdentificacaoCI().equals("") || form.getTipoPesquisaIdentificacaoCI() == null){
        	form.setTipoPesquisaIdentificacaoCI(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
        }
        
//      verifica se o codigo do usuário responsável foi digitado foi digitado
        String idDigitadoEnterUsuarioResponsavel = (String) form.getIdUsuarioResponsavel();
        if (idDigitadoEnterUsuarioResponsavel != null
                && !idDigitadoEnterUsuarioResponsavel.trim().equals("")
                && Integer.parseInt(idDigitadoEnterUsuarioResponsavel) > 0) {
            
        	FiltroUsuario filtroUsuario = new FiltroUsuario();
			
        	filtroUsuario.adicionarParametro(new ParametroSimples(
        			FiltroUsuario.ID, idDigitadoEnterUsuarioResponsavel));

			Collection usuarioEncontrado = fachada.pesquisar(filtroUsuario,
					Usuario.class.getName());

			if (usuarioEncontrado != null && !usuarioEncontrado.isEmpty()) {

				form.setIdUsuarioResponsavel(((Usuario) ((List) usuarioEncontrado)
								.get(0)).getId().toString());
				form.setNomeUsuarioResponsavel(((Usuario) ((List) usuarioEncontrado)
								.get(0)).getNomeUsuario());
				form.setUsuarioOk("true");
			} else {
				httpServletRequest.setAttribute("corUsuario","exception");
               	form.setNomeUsuarioResponsavel(ConstantesSistema.USUARIO_INEXISTENTE);
               	form.setIdUsuarioResponsavel("");
               	form.setUsuarioOk("false");
			}
        }
        
        return retorno;
    }
}
