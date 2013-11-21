/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
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