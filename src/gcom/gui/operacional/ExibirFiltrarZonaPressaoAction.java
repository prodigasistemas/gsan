/**
 * 
 */
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

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * @author Vinícius Medeiros
 * @date 20/05/2008
 */

public class ExibirFiltrarZonaPressaoAction extends GcomAction {

	private String distritoOperacionalID;

	private Collection colecaoPesquisa;

	/*
	 * @param actionMapping @param actionForm @param httpServletRequest @param
	 * httpServletResponse @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho do retorno
		ActionForward retorno = actionMapping.findForward("filtrarZonaPressao");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando for implementado o esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarZonaPressaoActionForm filtrarZonaPressaoActionForm = (FiltrarZonaPressaoActionForm) actionForm;

		String primeiraVez = httpServletRequest.getParameter("menu");
		
		if (primeiraVez != null && !primeiraVez.equals("")) {
			
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarZonaPressaoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		
		}			
		
		if(filtrarZonaPressaoActionForm.getIndicadorAtualizar()==null){
		
			filtrarZonaPressaoActionForm.setIndicadorAtualizar("1");
		
		}
		
		if (sessao.getAttribute("consulta") != null) {
		
			sessao.removeAttribute("consulta");
		
		}
		
        String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
        
        if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {
        	
            //Recebe o valor do campo distritoOperacionalID do formulário.
            distritoOperacionalID = filtrarZonaPressaoActionForm.getDistritoOperacionalID();


            switch (Integer.parseInt(objetoConsulta)) {
            	// Distrito Operacional
            	case 1:
                    pesquisarDistritoOperacional(
                    		filtrarZonaPressaoActionForm,fachada, httpServletRequest);
                    
                    break;
                
            	default:
                    break;
            
            }
        }
	    

        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	filtrarZonaPressaoActionForm.setId("");
        	filtrarZonaPressaoActionForm.setDescricao("");
        	filtrarZonaPressaoActionForm.setDescricaoAbreviada("");
        	filtrarZonaPressaoActionForm.setDistritoOperacionalID("");
        	filtrarZonaPressaoActionForm.setIndicadorUso("");
        
        }
	
        return retorno;
	}
	
    private void pesquisarDistritoOperacional(
    		FiltrarZonaPressaoActionForm filtrarZonaPressaoActionForm, Fachada fachada, 
    		HttpServletRequest httpServletRequest) {
        
    	if (distritoOperacionalID == null || distritoOperacionalID.trim().equalsIgnoreCase("")) {
            //Limpa o campo distritoOperacionalID do formulario
        	filtrarZonaPressaoActionForm.setDistritoOperacionalDescricao("Informe Distrito Operacional");
            httpServletRequest.setAttribute("corDistritoOperacional", "exception");
        
    	} else {
        
    		FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
            filtroDistritoOperacional.adicionarParametro(
            		new ParametroSimples(FiltroDistritoOperacional.ID, distritoOperacionalID));

            //Retorna Distrito Operacional
            colecaoPesquisa = fachada.pesquisar(
            		filtroDistritoOperacional,DistritoOperacional.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //Distrito Operacional nao encontrada
                //Limpa o campo DistritoOperacionalID do formulário
            	filtrarZonaPressaoActionForm.setDistritoOperacionalID("");
            	filtrarZonaPressaoActionForm.setDistritoOperacionalDescricao("Distrito Operacional inexistente.");
                httpServletRequest.setAttribute("corDistritoOperacional", "exception");
                
                httpServletRequest.setAttribute("nomeCampo", "distritoOperacionalID");
            
            } else {
            
            	DistritoOperacional objetoDistritoOperacional = 
            		(DistritoOperacional) Util.retonarObjetoDeColecao(colecaoPesquisa);
                filtrarZonaPressaoActionForm.setDistritoOperacionalID(
                		String.valueOf(objetoDistritoOperacional.getId()));
                filtrarZonaPressaoActionForm.setDistritoOperacionalDescricao(
                		objetoDistritoOperacional.getDescricao());
                httpServletRequest.setAttribute("corDistritoOperacional", "valor");
            
            }
        }
    	
    }
}