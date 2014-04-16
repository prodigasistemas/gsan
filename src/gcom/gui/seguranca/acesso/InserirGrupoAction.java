package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.Usuario;
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
 * Action responsável por inserir um grupo e os acessos se informados 
 *
 * @author Pedro Alexandre
 * @date 28/06/2006
 */
public class InserirGrupoAction extends GcomAction {

   
    /**
     * <Breve descrição sobre o caso de uso>
     *
     * [UC0278] - Inserir Grupo
     *
     * @author Pedro Alexandre
     * @date 29/06/2006
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno para a tela de sucesso
    	ActionForward retorno = actionMapping.findForward("telaSucesso");

    	//Cria uma instância da sessão
    	HttpSession sessao = httpServletRequest.getSession(false);

    	//Recupera o grupo da sessão
    	Grupo grupo = (Grupo) sessao.getAttribute("grupo");    	
    	grupo.setUltimaAlteracao(new Date());
    	
        //[FS0002] - Verificar preenchimento dos campos
        if(grupo.getDescricao() == null){
        	throw new ActionServletException("atencao.naoinformado",null, "Descrição do Grupo");
        }

        //[FS0002] - Verificar preenchimento dos campos
        if(grupo.getDescricaoAbreviada() == null){
        	throw new ActionServletException("atencao.naoinformado",null, "Descrição Abreviada do Grupo");
        }
        
        //[FS002] - Verificar preenchimento dos campos
        if (grupo.getIndicadorSuperintendencia() == null){
        	throw new ActionServletException("atencao.naoinformado",null, "Indicador de Superintendência");
        }

        
        //[FS0001] - Verificar existência da descrição
        FiltroGrupo filtroGrupo = new FiltroGrupo();
        filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.DESCRICAO, grupo.getDescricao()));
        Collection colecaoGrupo = Fachada.getInstancia().pesquisar(filtroGrupo, Grupo.class.getSimpleName());
        if (colecaoGrupo != null && !colecaoGrupo.isEmpty()) {
			throw new ActionServletException("atencao.grupo.descricao_ja_existe",null,grupo.getDescricao());
        }
    	
    	//Recupera os acessos dos grupos definidos pelo usuário
    	Collection grupoFuncionalidades = (Collection) sessao.getAttribute("grupoFuncionalidades");
    	
    	//Passa o usuário logado para registrar operação.
    	Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
    	
    	//Cria uma instância da fachada
    	Fachada.getInstancia().inserirGrupo(grupo,grupoFuncionalidades, usuarioLogado);

    	//Monta página de sucesso
    	montarPaginaSucesso(httpServletRequest, "Grupo de descrição "
				+ grupo.getDescricao() + " inserido com sucesso!",
				"Inserir outro grupo", "exibirInserirGrupoAction.do?menu=sim",
				"exibirAtualizarGrupoAction.do?idRegistroAtualizacao="
						+ grupo.getId() , "Atualizar grupo inserido");

    	//Limpa a sessão depois de inserir os dados
        sessao.removeAttribute("grupo");
        sessao.removeAttribute("grupoFuncionalidades");

        //Retorna o mapeamento contido na variável "retorno" 
        return retorno;
    }
}
