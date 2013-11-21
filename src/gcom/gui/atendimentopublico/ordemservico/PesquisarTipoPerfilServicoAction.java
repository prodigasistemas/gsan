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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0388] Pesquisar Tipo Perfil Serviço
 * 
 * @author Ana Maria
 * @date 01/08/2006
 * 
 */
public class PesquisarTipoPerfilServicoAction extends GcomAction {
	/**
	 * [UC0388] Esse caso de uso efetua pesquisa do perfil de serviço
	 * 
	 * @author Ana Maria
	 * @date 01/08/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return ActionForward
	 */
	   public ActionForward execute(ActionMapping actionMapping,
	            ActionForm actionForm, HttpServletRequest httpServletRequest,
	            HttpServletResponse httpServletResponse) {

	        ActionForward retorno = actionMapping.findForward("listaTipoPerfilServico");
	        
	        //Obtém a instância da Fachada
	        Fachada fachada = Fachada.getInstancia();
	        
			// Obtém o action form
	        PesquisarTipoPerfilServicoActionForm pesquisarTipoPerfilServicoActionForm = (PesquisarTipoPerfilServicoActionForm) actionForm;

			// Recupera os parâmetros do form
	        String idServicoPerfil = (String) pesquisarTipoPerfilServicoActionForm.getIdServicoPerfil();
	        String descricaoServicoPerfil = (String) pesquisarTipoPerfilServicoActionForm.getDescricaoServicoPerfil();
	        String abreviaturaServicoPerfil = (String) pesquisarTipoPerfilServicoActionForm.getAbreviaturaServicoPerfil();
	        String componenteEquipe = (String) pesquisarTipoPerfilServicoActionForm.getComponenteEquipe();
	        String equipeEspecial = (String) pesquisarTipoPerfilServicoActionForm.getEquipamentoEspecial();
	        String veiculoPropio = (String) pesquisarTipoPerfilServicoActionForm.getVeiculoProprio();
	        String tipoPesquisa = (String) pesquisarTipoPerfilServicoActionForm.getTipoPesquisa();
	        String tipoPesquisaAbreviada = (String) pesquisarTipoPerfilServicoActionForm.getTipoPesquisaAbreviada();

	        boolean peloMenosUmParametroInformado = false;

	        FiltroServicoPerfilTipo filtroServicoPerfilTipo = new FiltroServicoPerfilTipo(FiltroServicoPerfilTipo.DESCRICAO);
	        
			// Insere os parâmetros informados no filtro
	        if (idServicoPerfil != null && !idServicoPerfil.trim().equalsIgnoreCase("")) {
	        	filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
	        			FiltroServicoPerfilTipo.ID, new Integer(idServicoPerfil)));
	            peloMenosUmParametroInformado = true;
	        }
	        
	        if (descricaoServicoPerfil != null && !descricaoServicoPerfil.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true; 
    			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
    				filtroServicoPerfilTipo.adicionarParametro(new ComparacaoTextoCompleto(
    						FiltroServicoPerfilTipo.DESCRICAO, descricaoServicoPerfil));
    			} else {
    				filtroServicoPerfilTipo.adicionarParametro(new ComparacaoTexto(
    						FiltroServicoPerfilTipo.DESCRICAO, descricaoServicoPerfil));
    			}
	        }
	        
	        if (abreviaturaServicoPerfil != null && !abreviaturaServicoPerfil.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;	            
    			if (tipoPesquisaAbreviada != null && tipoPesquisaAbreviada.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
    				filtroServicoPerfilTipo.adicionarParametro(new ComparacaoTextoCompleto(
    						FiltroServicoPerfilTipo.DESCRICAO_ABREVIADA, abreviaturaServicoPerfil));
    			} else {
    				filtroServicoPerfilTipo.adicionarParametro(new ComparacaoTexto(
    						FiltroServicoPerfilTipo.DESCRICAO_ABREVIADA, abreviaturaServicoPerfil));
    			}
	        }
	        
	        if (componenteEquipe != null && !componenteEquipe.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;
	        	filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
	        			FiltroServicoPerfilTipo.COMPONENTES_EQUIPE, componenteEquipe));	        
	        }
	        
	        if (equipeEspecial != null && !equipeEspecial.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;
	        	filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
	        			FiltroServicoPerfilTipo.ID_EQUIPES_COMPONENTES, equipeEspecial));	        
	        }
	        
	        if (veiculoPropio != null && !veiculoPropio.trim().equalsIgnoreCase("") &&!veiculoPropio.equals("3")) {
	            peloMenosUmParametroInformado = true;
	        	filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
	        			FiltroServicoPerfilTipo.VEICULO_PROPIO, veiculoPropio));	        
	        }
	        
			// Erro caso o usuário mandou filtrar sem nenhum parâmetro
	        if (!peloMenosUmParametroInformado) {
	            throw new ActionServletException(
	                    "atencao.filtro.nenhum_parametro_informado");
	        }
	        
	        filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoPerfilTipo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

			// Faz a pesquisa baseada no filtro
	        Collection servicosPerfilTipo = fachada.pesquisar(filtroServicoPerfilTipo, ServicoPerfilTipo.class.getName());

			// Verificar se a pesquisa de servicoPerfilTipo não está vazia
	        if (servicosPerfilTipo != null && !servicosPerfilTipo.isEmpty()) {
                 //Aciona o controle de paginação para que sejam pesquisados apenas
				// os registros que aparecem na página
				Map resultado = controlarPaginacao(httpServletRequest, retorno,
						filtroServicoPerfilTipo, ServicoPerfilTipo.class.getName());
				servicosPerfilTipo = (Collection) resultado.get("colecaoRetorno");
				retorno = (ActionForward) resultado.get("destinoActionForward");
				
				// Manda a coleção dos servicosPerfilTipo pesquisados para o request
				httpServletRequest.getSession(false).setAttribute("servicosPerfilTipo",
						servicosPerfilTipo);
				
	        } else {
	            throw new ActionServletException(
	                    "atencao.pesquisa.nenhumresultado", null, "servicoTipoPerfil");
	        }
	        
	        return retorno;
	    }

	}
