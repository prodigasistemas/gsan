package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;


/**
 * Action responsável por validar os dados informados na página de inserir grupo
 * e por criar um grupo com a descrição e a descrição abreviada informadas.
 *
 * @author Pedro Alexandre, Mariana Victor
 * @date 15/06/2006, 10/01/2011
 */
public class InserirGrupoDadosGeraisAction extends GcomAction {
	
   
    /**
     * <Breve descrição sobre o caso de uso>
     *
     * [UC0278] Inserir Grupo
     *
     * @author Pedro Alexandre, Mariana Victor
     * @date 15/06/2006, 10/01/2011
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

    	//Seta o mapeamento de retorno para a página de definir acesso do grupo 
        ActionForward retorno = actionMapping.findForward("inserirGrupoAcessosGrupo");
        
        //Recupera o form 
        DynaValidatorForm grupoActionForm = (DynaValidatorForm) actionForm;

        //Cria uma instância da sessão 
        HttpSession sessao = httpServletRequest.getSession(false);

        /* Cria o grupo e seta os dados informados 
         * para depois usar esse grupo gerado nas 
         * outras páginas.
         */ 
        Grupo grupo = new Grupo();
        grupo.setDescricao((String)grupoActionForm.get("descricao"));
        grupo.setDescricaoAbreviada((String)grupoActionForm.get("descricaoAbreviada"));
        grupo.setIndicadorUso(ConstantesSistema.SIM);
        grupo.setIndicadorSuperintendencia((Short)grupoActionForm.get("indicadorSuperintendencia"));
        
        if(grupoActionForm.get("diasExpiracaoSenha")!=null
        		&& !grupoActionForm.get("diasExpiracaoSenha").toString().equals("")){
        	grupo.setNumDiasExpiracaoSenha(new Integer(grupoActionForm.get("diasExpiracaoSenha").toString()));
        }
        if(grupoActionForm.get("mensagem")!=null
        		&& !grupoActionForm.get("mensagem").equals("")){
        	grupo.setMensagem(grupoActionForm.get("mensagem").toString());
        }
        if(grupoActionForm.get("competenciaRetificacao")!=null
        		&& !grupoActionForm.get("competenciaRetificacao").toString().equals("")
        		&& !grupoActionForm.get("competenciaRetificacao").toString().equals("0")){
        	grupo.setCompetenciaRetificacao(Util.formatarMoedaRealparaBigDecimal(grupoActionForm.get("competenciaRetificacao").toString()));
        }

        
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
        

        //Coloca na sessãoo grupo gerado com as informações informadas
        sessao.setAttribute("grupo",grupo);

        //Retorna o mapemaneto na variável "retorno"
        return retorno;
    }
}
