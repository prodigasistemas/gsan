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
package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.operacional.ZonaPressao;
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

public class AtualizarZonaPressaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando for implementado o esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarZonaPressaoActionForm atualizarZonaPressaoActionForm = (AtualizarZonaPressaoActionForm) actionForm;

		ZonaPressao zonaPressao = (ZonaPressao) sessao.getAttribute("atualizarZonaPressao");
		
		Collection colecaoPesquisa = null;
		
        String descricaoZonaPressao = atualizarZonaPressaoActionForm.getDescricao();
        String descricaoAbreviadaZonaPressao = atualizarZonaPressaoActionForm.getDescricaoAbreviada();    
        String indicadordeUso = atualizarZonaPressaoActionForm.getIndicadorUso();
        String distritoOperacionalID = atualizarZonaPressaoActionForm.getDistritoOperacionalID();
        
        //Distrito Operacional é obrigatório.

        if (distritoOperacionalID == null || distritoOperacionalID.equalsIgnoreCase("")) {
            throw new ActionServletException("atencao.required", null, "Distrito Operacional");
        }
		FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

		filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
				FiltroDistritoOperacional.ID, distritoOperacionalID));

		filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
				FiltroDistritoOperacional.INDICADORUSO,
		        ConstantesSistema.INDICADOR_USO_ATIVO));

		//Retorna distrito Operacional
		colecaoPesquisa = fachada.pesquisar(filtroDistritoOperacional,
				DistritoOperacional.class.getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		    throw new ActionServletException(
		            "atencao.pesquisa.distrito_operacional_inexistente");
		}

        
    zonaPressao.setDescricaoZonaPressao(atualizarZonaPressaoActionForm.getDescricao());
	zonaPressao.setDescricaoAbreviada(atualizarZonaPressaoActionForm.getDescricaoAbreviada());
	zonaPressao.setIndicadorUso(new Short (atualizarZonaPressaoActionForm.getIndicadorUso()));
	
    DistritoOperacional distritoOperacional = new DistritoOperacional();
    	distritoOperacional.setId(new Integer(atualizarZonaPressaoActionForm.getDistritoOperacionalID()));
    zonaPressao.setDistritoOperacional(distritoOperacional);
    
    zonaPressao.setDescricaoZonaPressao(descricaoZonaPressao);
        
	// Caso não tenha sido preenchida a Descrição Abreviada, no banco ficará null
    if(atualizarZonaPressaoActionForm.getDescricaoAbreviada() != null 
			&& !atualizarZonaPressaoActionForm.getDescricaoAbreviada().equals("")){
        
		zonaPressao.setDescricaoAbreviada(descricaoAbreviadaZonaPressao);
	
	} else {
	
		zonaPressao.setDescricaoAbreviada(null);
	
	}
        
   	// Seta a data da alteração
    zonaPressao.setUltimaAlteracao( new Date() );	
    
    // Seta o Indicador de Uso
    zonaPressao.setIndicadorUso( new Short(indicadordeUso));
	
	fachada.atualizar(zonaPressao);

	montarPaginaSucesso(httpServletRequest, "Zona de Pressão "
			+ atualizarZonaPressaoActionForm.getId().toString() + " atualizado com sucesso.",
			"Realizar outra Manutenção de Zona de Pressão ",
			"exibirFiltrarZonaPressaoAction.do?menu=sim");        
    
	return retorno;
	}
}
