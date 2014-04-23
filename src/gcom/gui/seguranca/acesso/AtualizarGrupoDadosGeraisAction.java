package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;


/**
 * Action responsável pela validação dos dados informados na primeira página 
 * do processo de atualizar grupo
 *
 * @author Pedro Alexandre, Mariana Victor
 * @date 03/07/2006, 10/01/2011
 */
public class AtualizarGrupoDadosGeraisAction extends GcomAction {
	
    
    /**
     * <Breve descrição sobre o caso de uso>
     *
     * [UC0279] - Manter Grupo
     *
     * @author Pedro Alexandre, Mariana Victor
     * @date 03/07/2006, 10/01/2011
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

    	//Recupera o form de atualizar grupo
    	DynaValidatorForm atualizarGrupoActionForm = (DynaValidatorForm) actionForm;

    	//Seta o mapeamento para a segunda página do processo de atualizar grupo 
        ActionForward retorno = actionMapping.findForward("atualizarGrupoAcessosGrupo");
        
        //Obtém uma instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Recupera o grupo da sessão e seta os dados da página no grupo
        Grupo grupo = (Grupo) sessao.getAttribute("grupo");
        grupo.setDescricao(""+atualizarGrupoActionForm.get("descricao"));
        grupo.setDescricaoAbreviada(""+atualizarGrupoActionForm.get("descricaoAbreviada"));
        grupo.setIndicadorSuperintendencia((Short)atualizarGrupoActionForm.get("indicadorSuperintendencia"));
        
        if(atualizarGrupoActionForm.get("diasExpiracaoSenha")!=null
        		&& !atualizarGrupoActionForm.get("diasExpiracaoSenha").toString().equals("")){
        	
        	Integer numeroDiasExpiracaoSenha = new Integer(atualizarGrupoActionForm.get("diasExpiracaoSenha").toString());
        		if(numeroDiasExpiracaoSenha.compareTo(0)!=0){
        			grupo.setNumDiasExpiracaoSenha(numeroDiasExpiracaoSenha);
        		}else{
        			grupo.setNumDiasExpiracaoSenha(null);
        		}
        }
        
        if (atualizarGrupoActionForm.get("competenciaRetificacao") != null
        		&& !atualizarGrupoActionForm.get("competenciaRetificacao").toString().equals("")) {

        	BigDecimal competenciaRetificacao = Util.formatarMoedaRealparaBigDecimal(atualizarGrupoActionForm.get("competenciaRetificacao").toString());
        		if(competenciaRetificacao.compareTo(BigDecimal.ZERO)!=0){
        			grupo.setCompetenciaRetificacao(competenciaRetificacao);
        		}else{
        			grupo.setCompetenciaRetificacao(null);
        		}
        }
        
        if(atualizarGrupoActionForm.get("mensagem")!=null
        		&& !atualizarGrupoActionForm.get("mensagem").equals("")){
        	grupo.setMensagem(atualizarGrupoActionForm.get("mensagem").toString());
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

        //[FS0001] - Verificar usuários vinculados do grupo
        if (grupo.getIndicadorUso().shortValue() == ConstantesSistema.SIM.shortValue() && (ConstantesSistema.NAO + "").equalsIgnoreCase(""+atualizarGrupoActionForm.get("indicadorUso"))) {
        	FiltroUsuarioGrupo filtroUsuarioGrupo = new FiltroUsuarioGrupo();
        	filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(FiltroUsuarioGrupo.GRUPO_ID, grupo.getId()));
        	Collection colecaoUsuarioGrupo = Fachada.getInstancia().pesquisar(filtroUsuarioGrupo, UsuarioGrupo.class.getSimpleName());
        	if (colecaoUsuarioGrupo != null && !colecaoUsuarioGrupo.isEmpty()) {
        		throw new ActionServletException("atencao.grupo.vinculo.usuario", null, grupo.getDescricao());
        	}
        	grupo.setIndicadorUso(ConstantesSistema.NAO);
        } else {
        	if (atualizarGrupoActionForm.get("indicadorUso").toString().equals(ConstantesSistema.SIM.toString())) {
        		grupo.setIndicadorUso(ConstantesSistema.SIM);	
        	} else {
        		grupo.setIndicadorUso(ConstantesSistema.NAO);
        	}
        }

        
        //[FS0006] - Verificar existência da descrição
        FiltroGrupo filtroGrupo = new FiltroGrupo();
        filtroGrupo.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroGrupo.ID,grupo.getId()));
        filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.DESCRICAO, grupo.getDescricao()));
        Collection colecaoGrupo = Fachada.getInstancia().pesquisar(filtroGrupo, Grupo.class.getSimpleName());
        if (colecaoGrupo != null && !colecaoGrupo.isEmpty()) {
			throw new ActionServletException("atencao.grupo.descricao_ja_existe",null,grupo.getDescricao());
        }

        //Coloca o grupo atualizado na sessão
        sessao.setAttribute("grupo",grupo);

        //Retorna o mapeamento contido na variável "retorno"  
        return retorno;
    }
}
