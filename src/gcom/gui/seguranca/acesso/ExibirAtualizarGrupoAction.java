package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
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
 * Action responsável por montar todo o esquema do 
 * processo de inserir grupo de usuários.
 *
 * @author Pedro Alexandre
 * @date 26/06/2006
 */
public class ExibirAtualizarGrupoAction extends GcomAction {

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * [UC0279] - Manter Grupo
     *
     * @author Pedro Alexandre
     * @date 26/06/2006
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

        //Localiza o action no objeto 
        ActionForward retorno = actionMapping.findForward("atualizarGrupoDadosGerais");

        //Obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Cria a variável que vai armazenar o código do grupo
        String idGrupo = null;
        
        //Caso o botão de desfazer não tenha sido pressionado
        if(httpServletRequest.getParameter("desfazer") == null){
        	//Recupera o código do grupo
	       	if(httpServletRequest.getParameter("idRegistroAtualizacao") != null){
	        	idGrupo = (String) httpServletRequest.getParameter("idRegistroAtualizacao");
	        }else if(httpServletRequest.getAttribute("idRegistroAtualizacao") != null){
	        	idGrupo = (String) httpServletRequest.getAttribute("idRegistroAtualizacao");
	        }
	        
	       		       	
	       	//Monta o status wizard com os actions do processo
	       	StatusWizard statusWizard = new StatusWizard(
	                "atualizarGrupoWizardAction", "atualizarGrupoAction",
	                "cancelarAtualizarGrupoAction","exibirManterGrupoAction",
	                "exibirAtualizarGrupoAction.do", 
	                idGrupo);
	       	
	       	/*if(httpServletRequest.getParameter("sucesso") != null){
		        statusWizard = new StatusWizard(
		                "atualizarGrupoWizardAction", "atualizarGrupoAction",
		                "cancelarAtualizarGrupoAction","exibirFiltrarGrupoAction",
		                "exibirAtualizarGrupoAction.do", 
		                idGrupo);
	       	}else{
		        statusWizard = new StatusWizard(
		                "atualizarGrupoWizardAction", "atualizarGrupoAction",
		                "cancelarAtualizarGrupoAction","exibirManterGrupoAction",
		                "exibirAtualizarGrupoAction.do",
		                idGrupo);	       		
	       	}*/
	       		        	        
	        //Monta a primeira aba do processo de atualizar grupo
	        statusWizard
	                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
	                        1, "DadosGeraisPrimeiraAbaA.gif", "DadosGeraisPrimeiraAbaD.gif",
	                        "exibirAtualizarGrupoDadosGeraisAction",
	                        "atualizarGrupoDadosGeraisAction"));
	        
	        //Monta a segunda aba do processo de atualizar grupo
	        statusWizard
	                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
	                        2, "AcessosGrupoUltimaAbaA.gif", "AcessosGrupoUltimaAbaD.gif",
	                        "exibirAtualizarGrupoAcessosGrupoAction",
	                        "atualizarGrupoAcessosGrupoAction"));
	        

	        //Manda o statusWizard para a sessao
	        sessao.setAttribute("statusWizard", statusWizard);
	        
        }else{
        	//Recupera o status wizard da sessão para recuperar o código do grupo
        	StatusWizard statusWizardSessao = (StatusWizard)sessao.getAttribute("statusWizard");
        	idGrupo = statusWizardSessao.getId();
        }
        
        //Limpa a sessão
        sessao.removeAttribute("GrupoActionForm");
        sessao.removeAttribute("grupo");
        sessao.removeAttribute("grupoFuncionalidades");
        
        //Parte da atualização
        DynaValidatorForm atualizarGrupoActionForm = (DynaValidatorForm) actionForm;
        
        //Caso o usuário tenha informado o código do grupo que vai ser atualizado
        if (idGrupo != null ) {
        	FiltroGrupo filtroGrupo = new FiltroGrupo();
        	filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.ID,idGrupo));
        	Collection colecaoGrupo = Fachada.getInstancia().pesquisar(filtroGrupo, Grupo.class.getSimpleName());
        	Grupo grupo = (Grupo) colecaoGrupo.iterator().next();
        	sessao.setAttribute("grupo",grupo);
        }

        //Recupera o grupo da sessão
        Grupo grupo = (Grupo) sessao.getAttribute("grupo");

        //Caso exista um grupo na sessão seta todos os dados no grupo no form 
        if (grupo != null) {
        	atualizarGrupoActionForm.set("descricao",grupo.getDescricao());
        	atualizarGrupoActionForm.set("descricaoAbreviada",grupo.getDescricaoAbreviada());
	        if (grupo.getIndicadorUso() != null) {
	        	atualizarGrupoActionForm.set("indicadorUso",grupo.getIndicadorUso().toString());
	        } else {
	        	atualizarGrupoActionForm.set("indicadorUso",ConstantesSistema.SIM.toString());
	        }
        }

        //Atualiza o grupo da sessão
        grupo.setDescricao(""+atualizarGrupoActionForm.get("descricao"));
        grupo.setDescricaoAbreviada(""+atualizarGrupoActionForm.get("descricaoAbreviada"));
        
        if(grupo.getNumDiasExpiracaoSenha()!=null
        		&& !grupo.getNumDiasExpiracaoSenha().toString().equals("")){
        	atualizarGrupoActionForm.set("diasExpiracaoSenha",grupo.getNumDiasExpiracaoSenha());
        }
        if(grupo.getMensagem()!=null
        		&& !grupo.getMensagem().equals("")){
        	atualizarGrupoActionForm.set("mensagem",grupo.getMensagem());
        }
        
        if(grupo.getIndicadorSuperintendencia() != null){
        	atualizarGrupoActionForm.set("indicadorSuperintendencia",grupo.getIndicadorSuperintendencia());
        }

        if(grupo.getCompetenciaRetificacao()!=null
        		&& !grupo.getCompetenciaRetificacao().toString().equals("")){
        	atualizarGrupoActionForm.set("competenciaRetificacao",Util.formatarBigDecimalParaStringComVirgula(grupo.getCompetenciaRetificacao()));
        }
        
        sessao.setAttribute("grupo",grupo);
        
       
        
        //Retorna o mapeamento contido na variável retorno
        return retorno;
    }
}
