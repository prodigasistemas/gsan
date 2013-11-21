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
package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
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
 * Descrição da classe 
 *
 * @author Administrador
 * @date 08/07/2006
 */

public class ExibirFiltrarQuadraAction extends GcomAction {

    private String localidadeID;

    private String setorComercialCD;

    private Collection colecaoPesquisa;

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * <Breve descrição sobre o subfluxo>
     *
     * <Identificador e nome do subfluxo>	
     *
     * <Breve descrição sobre o fluxo secundário>
     *
     * <Identificador e nome do fluxo secundário> 
     *
     * @author Administrador
     * @date 08/07/2006
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        
        ActionForward retorno = actionMapping
                .findForward("filtrarQuadra");

      
        Fachada fachada = Fachada.getInstancia();

       
        HttpSession sessao = httpServletRequest.getSession(false);

        FiltrarQuadraActionForm filtrarQuadraActionForm = (FiltrarQuadraActionForm) actionForm;
        
		if (sessao.getAttribute("consulta") != null) {
			sessao.removeAttribute("consulta");
		}
		
		String idRota = filtrarQuadraActionForm.getIdRota();
		
        if (idRota != null && !idRota.trim().equals("")) {
        	FiltroRota filtroRota = new FiltroRota();
        	filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRota));
        	
        	Collection colecaoRotas = fachada.pesquisar(filtroRota, Rota.class.getName());
        	
        	if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
        		Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRotas);
        		
        		if(rota.getIndicadorRotaAlternativa().shortValue() == ConstantesSistema.SIM){
        			filtrarQuadraActionForm.setCodigoRota("");        			
        			throw new ActionServletException("atencao.rota_alternativa_nao_pode_associar_quadra");
        		}else{
        			filtrarQuadraActionForm.setCodigoRota(rota.getCodigo().toString());	
        		}
        		
        	}
        }
        
        String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");

        if (objetoConsulta != null
                && !objetoConsulta.trim().equalsIgnoreCase("")) {

            //Recebe o valor do campo localidadeID do formulário.
            localidadeID = filtrarQuadraActionForm.getLocalidadeID();

            //Recebe o valor do campo setorComercialID do formulário.
            setorComercialCD = filtrarQuadraActionForm
                    .getSetorComercialCD();

            switch (Integer.parseInt(objetoConsulta)) {
            //Localidade
            case 1:

                pesquisarLocalidade(filtrarQuadraActionForm,
                        fachada, httpServletRequest);

                break;
            //Setor Comercial
            case 2:

                pesquisarLocalidade(filtrarQuadraActionForm,
                        fachada, httpServletRequest);

                pesquisarSetorComercial(filtrarQuadraActionForm,
                        fachada, httpServletRequest);

                break;

            default:
                break;
            }
        }

        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	//-------------- bt LIMPAR ---------------
        	
        	String[] arrayQuadraIDVazio = null;
        	filtrarQuadraActionForm.setLocalidadeID("");
        	filtrarQuadraActionForm.setLocalidadeNome("");
        	filtrarQuadraActionForm.setSetorComercialCD("");
        	filtrarQuadraActionForm.setSetorComercialID("");
        	filtrarQuadraActionForm.setSetorComercialNome("");
        	filtrarQuadraActionForm.setQuadraID(arrayQuadraIDVazio);
            filtrarQuadraActionForm.setQuadraMensagem("");
            filtrarQuadraActionForm.setQuadraNM("");
        	//filtrarQuadraActionForm.setBairroNome("");
	        filtrarQuadraActionForm.setIndicadorUso("3");
	        //filtrarQuadraActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
	        sessao.setAttribute("indicadorAtualizar","1");
	        
        }
        
        // código para checar ou naum o checkbox Atualizar
        String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar","1");
			filtrarQuadraActionForm.setIndicadorUso("3");
			//filtrarQuadraActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		}
		
		//se voltou da tela de Atualizar Quadra
		//checkbox Atualizar = checked 
		if (sessao.getAttribute("voltar") !=null &&
				sessao.getAttribute("voltar").equals("filtrar")){
			sessao.setAttribute("indicadorAtualizar","1");
			if(sessao.getAttribute("indicadorUso") != null){
				filtrarQuadraActionForm.setIndicadorUso(sessao.getAttribute("indicadorUso").toString());
		     //   filtrarQuadraActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			}
		}
		sessao.removeAttribute("idRegistroAtualizacao");
		sessao.removeAttribute("indicadorUso");
        
        //devolve o mapeamento de retorno
        return retorno;
    }

    private void pesquisarLocalidade(
    		FiltrarQuadraActionForm filtrarQuadraActionForm,
            Fachada fachada, HttpServletRequest httpServletRequest) {

        if (localidadeID == null || localidadeID.trim().equalsIgnoreCase("")) {
            //Limpa os campos localidadeID e setorComercialID do formulario
        	filtrarQuadraActionForm
                    .setLocalidadeNome("Informe Localidade");
            httpServletRequest.setAttribute("corLocalidade", "exception");
        } else {
            FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.ID, localidadeID));

            //Retorna localidade
            colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
                    Localidade.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //Localidade nao encontrada
                //Limpa o campo localidadeID do formulário
            	filtrarQuadraActionForm.setLocalidadeID("");
            	filtrarQuadraActionForm
                        .setLocalidadeNome("Localidade inexistente.");
                httpServletRequest.setAttribute("corLocalidade", "exception");
                
                httpServletRequest.setAttribute("nomeCampo", "localidadeID");
            } else {
                Localidade objetoLocalidade = (Localidade) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
                filtrarQuadraActionForm.setLocalidadeID(String
                        .valueOf(objetoLocalidade.getId()));
                filtrarQuadraActionForm
                        .setLocalidadeNome(objetoLocalidade.getDescricao());
                httpServletRequest.setAttribute("corLocalidade", "valor");
                
                httpServletRequest.setAttribute("nomeCampo", "setorComercialCD");
            }
        }
    }

    private void pesquisarSetorComercial(
    		FiltrarQuadraActionForm filtrarQuadraActionForm,
            Fachada fachada, HttpServletRequest httpServletRequest) {

        if (localidadeID == null || localidadeID.trim().equalsIgnoreCase("")) {
            //Limpa os campos setorComercialCD e setorComercialID do formulario
        	filtrarQuadraActionForm.setSetorComercialCD("");
        	filtrarQuadraActionForm.setSetorComercialID("");
        	filtrarQuadraActionForm
                    .setSetorComercialNome("Informe Localidade.");
            httpServletRequest.setAttribute("corSetorComercial", "exception");
            
            httpServletRequest.setAttribute("nomeCampo", "localidadeID");
        } else if (setorComercialCD == null
                || setorComercialCD.trim().equalsIgnoreCase("")) {
        	filtrarQuadraActionForm.setSetorComercialCD("");
        	filtrarQuadraActionForm.setSetorComercialID("");
        	filtrarQuadraActionForm
                    .setSetorComercialNome("Informe o setor comercial.");
            httpServletRequest.setAttribute("corSetorComercial", "exception");
            
            httpServletRequest.setAttribute("nomeCampo", "setorComercialCD");
        } else {

            FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                    setorComercialCD));

            //Retorna setorComercial
            colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                    SetorComercial.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //setorComercial nao encontrado
                //Limpa os campos setorComercialCD e setorComercialID do
                // formulario
            	filtrarQuadraActionForm.setSetorComercialCD("");
                filtrarQuadraActionForm.setSetorComercialID("");
                filtrarQuadraActionForm
                        .setSetorComercialNome("Setor comercial inexistente.");
                httpServletRequest.setAttribute("corSetorComercial",
                        "exception");
                
                httpServletRequest.setAttribute("nomeCampo", "setorComercialCD");
            } else {
                SetorComercial objetoSetorComercial = (SetorComercial) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
                filtrarQuadraActionForm.setSetorComercialCD(String
                        .valueOf(objetoSetorComercial.getCodigo()));
                filtrarQuadraActionForm.setSetorComercialID(String
                        .valueOf(objetoSetorComercial.getId()));
                filtrarQuadraActionForm
                        .setSetorComercialNome(objetoSetorComercial
                                .getDescricao());
                httpServletRequest.setAttribute("corSetorComercial", "valor");
                
                httpServletRequest.setAttribute("nomeCampo", "quadraNM");
            }

        }
    }

}
