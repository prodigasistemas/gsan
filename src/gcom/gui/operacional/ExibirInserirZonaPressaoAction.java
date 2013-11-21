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
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.operacional.FiltroZonaPressao;
import gcom.operacional.ZonaPressao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;


/**
 * @author Vinícius de Melo Medeiros
 * @created 20/05/2008
 */
public class ExibirInserirZonaPressaoAction extends GcomAction {
	/**
	 * Description of the Method
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
    private Collection colecaoPesquisa;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		ActionForward retorno = actionMapping.findForward("inserirZonaPressao");

		InserirZonaPressaoActionForm inserirZonaPressaoActionForm = (InserirZonaPressaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
        String objetoConsulta = (String) httpServletRequest
        .getParameter("objetoConsulta");

        if (objetoConsulta != null
        		&& !objetoConsulta.trim().equalsIgnoreCase("")) {
        	
            
            switch (Integer.parseInt(objetoConsulta)) {
            
            // Distrito Operacional

            case 1:
                
                String distritoOperacionalID = inserirZonaPressaoActionForm
                        .getDistritoOperacionalID();

                FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

                filtroDistritoOperacional
                        .adicionarParametro(new ParametroSimples(
                                FiltroDistritoOperacional.ID,
                                distritoOperacionalID));

                filtroDistritoOperacional
                        .adicionarParametro(new ParametroSimples(
                                FiltroDistritoOperacional.INDICADORUSO,
                                ConstantesSistema.INDICADOR_USO_ATIVO));

                //Retorna Distrito Operacional
                colecaoPesquisa = fachada.pesquisar(filtroDistritoOperacional,
                        DistritoOperacional.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    //Distrito Operacional nao encontrado
                    //Limpa o campo distritoOperacionalID do formulário
                	inserirZonaPressaoActionForm.setDistritoOperacionalID("");
                	inserirZonaPressaoActionForm
                            .setDistritoOperacionalDescricao("Distrito operacional inexistente.");
                    httpServletRequest.setAttribute("corDistritoOperacional",
                            "exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "distritoOperacionalID");
                } else {
                    DistritoOperacional objetoDistritoOperacional = (DistritoOperacional) Util
                            .retonarObjetoDeColecao(colecaoPesquisa);
                    inserirZonaPressaoActionForm.setDistritoOperacionalID(String
                            .valueOf(objetoDistritoOperacional.getId()));
                    inserirZonaPressaoActionForm
                            .setDistritoOperacionalDescricao(objetoDistritoOperacional
                                    .getDescricao());
                    httpServletRequest.setAttribute("corDistritoOperacional",
                            "valor");
                }
                break;
            }
        }

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirZonaPressaoActionForm.setDescricao("");
			inserirZonaPressaoActionForm.setDescricaoAbreviada("");
			inserirZonaPressaoActionForm.setDistritoOperacionalID("");
			inserirZonaPressaoActionForm.setDistritoOperacionalDescricao("");

			if (inserirZonaPressaoActionForm.getDescricao() == null
					|| inserirZonaPressaoActionForm.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;
				FiltroZonaPressao filtroZonaPressao = new FiltroZonaPressao();

				filtroZonaPressao.setCampoOrderBy(FiltroZonaPressao.DESCRICAO);
				
				colecaoPesquisa = fachada.pesquisar(filtroZonaPressao,
						ZonaPressao.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Zona de Pressão");
				} else {
					sessao.setAttribute("colecaoZonaPressao", colecaoPesquisa);
				}

				// Coleção de Grupo de Faturamento
				FiltroZonaPressao filtroZonaPressao2 = new FiltroZonaPressao();
				filtroZonaPressao2.setCampoOrderBy(FiltroZonaPressao.ID);

				Collection colecaoZonaPressao2 = fachada.pesquisar(filtroZonaPressao2,
						ZonaPressao.class.getName());
				sessao.setAttribute("colecaoZonaPressao2", colecaoZonaPressao2);

			}

		}
		return retorno;
	}
}
