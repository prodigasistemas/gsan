package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pesquisa de Comando de Ação de Cobrança 
 *
 * [UC0243] Inserir Comando de Ação de Conbrança
 * @author Rafael Santos
 * @since 08/03/2006
 */
public class ExibirPesquisarComandoAcaoCobrancaAction  extends GcomAction{
	
	/**
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

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirPesquisarComandoAcaoCobrancaAction");

		// Mudar isso quando implementar a parte de segurança
        HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
        
		//CARREGAR AS COBRANÇAS ATIVIDADE  - INICIO
		
		FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
		filtroCobrancaAtividade.setCampoOrderBy(FiltroCobrancaAtividade.DESCRICAO);
		if(sessao.getAttribute("colecaoCobrancaAtividade") == null){        		
			Collection colecaoCobrancaAtividade = (Collection) fachada.pesquisar(filtroCobrancaAtividade,CobrancaAtividade.class.getName());
			
	        if(colecaoCobrancaAtividade != null && !colecaoCobrancaAtividade.isEmpty()){
	            //carregar atividade de cobrança
	        	sessao.setAttribute("colecaoCobrancaAtividade",colecaoCobrancaAtividade);
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela",
					null, "Tabela Cobrança Atividade");
			}
		}
		
        
		//FIM COBRANÇA ATIVIDADE

		
		//CARREGAR AS COBRANÇAS ACAO  - INICIO
		
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);
		if(sessao.getAttribute("colecaoCobrancaAcao") == null){         		
			Collection colecaoCobrancaAcao = (Collection) fachada.pesquisar(filtroCobrancaAcao,CobrancaAcao.class.getName());
			
	        if(colecaoCobrancaAcao != null && !colecaoCobrancaAcao.isEmpty()){
	            //carregar ação de cobrança
	        	sessao.setAttribute("colecaoCobrancaAcao",colecaoCobrancaAcao);
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela",
					null, "Tabela Cobrança Ação");
			}
		}
	        
		//FIM COBRANÇA Ação
        
		PesquisarComandoAcaoCobrancaActionForm pesquisarComandoAcaoCobrancaActionForm = (PesquisarComandoAcaoCobrancaActionForm)actionForm;
		String idUsuario = pesquisarComandoAcaoCobrancaActionForm.getIdUsuario();  
		if(idUsuario != null && !idUsuario.equals("")){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID,idUsuario));
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario,Usuario.class.getName());
			if(colecaoUsuario != null && !colecaoUsuario.isEmpty()){
				Usuario usuario = (Usuario) colecaoUsuario.iterator().next();
				pesquisarComandoAcaoCobrancaActionForm.setUsuario(usuario.getNomeUsuario());
				httpServletRequest.setAttribute("usuarioNaoEncontrado",null);
			}else{
				httpServletRequest.setAttribute("usuarioNaoEncontrado","true");
				pesquisarComandoAcaoCobrancaActionForm.setUsuario("USUÁRIO INEXISTENTE");
			}
		}
        
        if (httpServletRequest.getParameter("tipoConsulta") != null
                && !httpServletRequest.getParameter("tipoConsulta").equals("")) {
            
        	pesquisarComandoAcaoCobrancaActionForm.setIdUsuario(
                        httpServletRequest.getParameter("idCampoEnviarDados"));
        	pesquisarComandoAcaoCobrancaActionForm.setUsuario(
        			    httpServletRequest.getParameter("descricaoCampoEnviarDados"));
        	sessao.removeAttribute("caminhoRetornoTelaPesquisaUsuario");
        	sessao.removeAttribute("caminhoRetornoTelaPesquisa");
        }
           
        pesquisarComandoAcaoCobrancaActionForm.setTipoPesquisa("1");
        return retorno;
    }

}
