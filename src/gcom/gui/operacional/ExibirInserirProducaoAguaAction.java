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

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroProducaoAgua;
import gcom.operacional.ProducaoAgua;
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

/**
 * @author Vinícius de Melo Medeiros
 * @created 09/06/2008
 */
public class ExibirInserirProducaoAguaAction extends GcomAction {

    private Collection colecaoPesquisa;
	
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
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando for implementado o esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Seta o caminho do retorno
		ActionForward retorno = actionMapping.findForward("inserirProducaoAgua");

		InserirProducaoAguaActionForm inserirProducaoAguaActionForm = (InserirProducaoAguaActionForm) actionForm;

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

        String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");

        if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {
        	
            switch (Integer.parseInt(objetoConsulta)) {
            
            // Localidade
            case 1:
                
                String localidadeID = inserirProducaoAguaActionForm.getLocalidadeID();

                FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

                filtroLocalidade.adicionarParametro(
                		new ParametroSimples(FiltroLocalidade.ID,
                                localidadeID));

                filtroLocalidade.adicionarParametro(
                		new ParametroSimples(FiltroLocalidade.INDICADORUSO,
                                ConstantesSistema.INDICADOR_USO_ATIVO));

                //Retorna Localidade
                colecaoPesquisa = fachada.pesquisar(
                		filtroLocalidade, Localidade.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    //Distrito Operacional nao encontrado
                    //Limpa o campo distritoOperacionalID do formulário
                	inserirProducaoAguaActionForm.setLocalidadeID("");
                	inserirProducaoAguaActionForm.setLocalidadeDescricao("Localidade inexistente.");
                    httpServletRequest.setAttribute("corLocalidade","exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "localidadeID");
                
                } else {
                
                	Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
                    
                	inserirProducaoAguaActionForm.setLocalidadeID(
                    		String.valueOf(objetoLocalidade.getId()));
                    inserirProducaoAguaActionForm.setLocalidadeDescricao(
                    		objetoLocalidade.getDescricao());
                    
                    httpServletRequest.setAttribute("corLocalidade","valor");
                }
                
                break;
        
            }
        }
        
		if ((httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirProducaoAguaActionForm.setAnoMesReferencia("");
			inserirProducaoAguaActionForm.setVolumeProduzido("");

			if (inserirProducaoAguaActionForm.getAnoMesReferencia() == null
					|| inserirProducaoAguaActionForm.getAnoMesReferencia().equals("")) {

				Collection colecaoPesquisa = null;
				FiltroProducaoAgua filtroProducaoAgua = new FiltroProducaoAgua();

				filtroProducaoAgua.setCampoOrderBy(FiltroProducaoAgua.MES_ANO_REFERENCIA);

				colecaoPesquisa = fachada.pesquisar(
						filtroProducaoAgua,ProducaoAgua.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,"Produção de Água");
				
				} else {
				
					sessao.setAttribute("colecaoProducaoAgua", colecaoPesquisa);
				
				}

				// Coleção de Producao de Agua
				FiltroProducaoAgua filtroProducaoAgua2 = new FiltroProducaoAgua();
				filtroProducaoAgua2.setCampoOrderBy(FiltroProducaoAgua.ID);

				Collection colecaoProducaoAgua2 = fachada.pesquisar(
						filtroProducaoAgua2, ProducaoAgua.class.getName());
				sessao.setAttribute("colecaoProducaoAgua2", colecaoProducaoAgua2);

			}

		}
		return retorno;
	}
}
