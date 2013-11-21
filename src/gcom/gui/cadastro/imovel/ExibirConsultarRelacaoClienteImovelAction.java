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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovelFimRelacaoMotivo;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de exibir atulizar o aviso bancario
 *
 * @author thiago toscano
 * @date 10/03/2006
 */
public class ExibirConsultarRelacaoClienteImovelAction  extends GcomAction {

    /**
     * Método responsavel por responder a requisicao
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibir");

        ExibirConsultarRelacaoClienteImovelActionForm form = (ExibirConsultarRelacaoClienteImovelActionForm) actionForm;

        HttpSession sessao = request.getSession(false);
        
        if (request.getParameter("menu") != null && request.getParameter("menu").equalsIgnoreCase("sim")) {
        	form.setSituacaoRelacao("3");
        	request.setAttribute("nomeCampo","idImovel");  
        }
        
        if ("true".equals(sessao.getAttribute("manterDadosSesao"))) {

        	if (!"".equals(sessao.getAttribute("idCliente")) && sessao.getAttribute("idCliente") != null) 
        		form.setIdCliente(sessao.getAttribute("idCliente").toString());

        	if (!"".equals(sessao.getAttribute("nomeCliente")) && sessao.getAttribute("nomeCliente") != null) 
        		form.setNomeCliente(sessao.getAttribute("nomeCliente").toString());

        	if (!"".equals(sessao.getAttribute("idImovel")) && sessao.getAttribute("idImovel") != null) 
        		form.setIdImovel(sessao.getAttribute("idImovel").toString());

        	if (!"".equals(sessao.getAttribute("nomeImovel")) && sessao.getAttribute("nomeImovel") != null) 
        		form.setNomeImovel(sessao.getAttribute("nomeImovel").toString());

        	if (!"".equals(sessao.getAttribute("periodoInicialDataInicioRelacao")) && sessao.getAttribute("periodoInicialDataInicioRelacao") != null) 
        		form.setPeriodoInicialDataInicioRelacao(sessao.getAttribute("periodoInicialDataInicioRelacao").toString());

        	if (!"".equals(sessao.getAttribute("periodoFinalDataInicioRelacao")) && sessao.getAttribute("periodoFinalDataInicioRelacao") != null) 
        		form.setPeriodoFinalDataInicioRelacao(sessao.getAttribute("periodoFinalDataInicioRelacao").toString());
        	
        	if (!"".equals(sessao.getAttribute("periodoInicialDataFimRelacao")) && sessao.getAttribute("periodoInicialDataFimRelacao") != null) 
        		form.setPeriodoInicialDataFimRelacao(sessao.getAttribute("periodoInicialDataFimRelacao").toString());

        	if (!"".equals(sessao.getAttribute("periodoFinalDataFimRelacao")) && sessao.getAttribute("periodoFinalDataFimRelacao") != null) 
        		form.setPeriodoFinalDataFimRelacao(sessao.getAttribute("periodoFinalDataFimRelacao").toString());

        	if (!"".equals(sessao.getAttribute("idClienteImovelFimRelacaoMotivo")) && sessao.getAttribute("idClienteImovelFimRelacaoMotivo") != null) 
        		form.setIdClienteImovelFimRelacaoMotivo(sessao.getAttribute("idClienteImovelFimRelacaoMotivo").toString());

        	if (!"".equals(sessao.getAttribute("idClienteRelacaoTipo")) && sessao.getAttribute("idClienteRelacaoTipo") != null) 
        		form.setIdClienteRelacaoTipo(sessao.getAttribute("idClienteRelacaoTipo").toString());
        	
        	if (!"".equals(sessao.getAttribute("situacaoRelacao")) && sessao.getAttribute("situacaoRelacao") != null) 
        		form.setSituacaoRelacao(sessao.getAttribute("situacaoRelacao").toString());
        }

        // se tem id do Cliente entao pega o nome
        if (!"".equals(form.getIdCliente()) && form.getIdCliente() != null) {

        	sessao.setAttribute("manterDadosSesao", "false");

            FiltroCliente filtroCliente = new  FiltroCliente();
            
            //coloca parametro no filtro
            filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, new Integer(form.getIdCliente())));
            
            //pesquisa
            Collection coll = Fachada.getInstancia().pesquisar(filtroCliente, Cliente.class.getName());
            if (coll != null && !coll.isEmpty()) {
                //O cliente foi encontrado
                //inserirImovelFiltrarActionForm.set("idCliente", ((Cliente) ((List) clientes).get(0)).getId().toString());
            	form.setNomeCliente(((Cliente) ((List) coll).get(0)).getNome());
            	sessao.setAttribute("idCliente", form.getIdCliente());
            	sessao.setAttribute("nomeCliente", ((Cliente) ((List) coll).get(0)).getNome());
            	form.setClienteNaoEncontrado("false");
            } else {
            	form.setClienteNaoEncontrado("true");
            	form.setNomeCliente("");
            }
            
        } else {
        	form.setNomeCliente("");
        	sessao.removeAttribute("idCliente");
        	sessao.removeAttribute("nomeCliente");
        }
        
        // se tem id do Imovel entao pega o nome
        if (!"".equals(form.getIdImovel()) && form.getIdImovel() != null) {
        	
        	sessao.setAttribute("manterDadosSesao", "false");
        	
        	FiltroImovel filtroImovel = new  FiltroImovel();
            
            //coloca parametro no filtro
            filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, new Integer(form.getIdImovel())));
            
            filtroImovel.adicionarParametro(new ParametroNulo(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO,
			ConectorOr.CONECTOR_OR, 2));
            filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO, 
            ConstantesSistema.SIM));
            
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);

            //pesquisa
            Collection coll = Fachada.getInstancia().pesquisar(filtroImovel, Imovel.class.getName());
            if (coll != null && !coll.isEmpty()) {
                //O Imovel foi encontrado
                //inserirImovelFiltrarActionForm.set("idImovel", ((Imovel) ((List) Imovels).get(0)).getId().toString());
            	form.setNomeImovel(((Imovel) ((List) coll).get(0)).getInscricaoFormatada());
            	sessao.setAttribute("nomeImovel", ((Imovel) ((List) coll).get(0)).getInscricaoFormatada());
            	sessao.setAttribute("idImovel", form.getIdImovel());
            	
            	form.setImovelNaoEncontrado("false");
            } else {
            	form.setImovelNaoEncontrado("true");
            	form.setNomeImovel("");
            }
        } else {
        	form.setNomeImovel("");
        	sessao.removeAttribute("idImovel");
        	sessao.removeAttribute("nomeImovel");
        }

//      cliente relacao tipo
        FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();        
        Collection coll = Fachada.getInstancia().pesquisar(filtroClienteRelacaoTipo, ClienteRelacaoTipo.class.getSimpleName());
        form.setCollClienteRelacaoTipo(coll);

        if (coll == null || coll.isEmpty()) {
        	throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela",null," A Tabela Cliente Relacao Tipo está ");
        }

//      cliente imovel fim relacao motivo        
        FiltroClienteImovelFimRelacaoMotivo filtroClienteImovelFimRelacaoMotivo = new FiltroClienteImovelFimRelacaoMotivo();        
        coll = Fachada.getInstancia().pesquisar(filtroClienteImovelFimRelacaoMotivo, ClienteImovelFimRelacaoMotivo.class.getSimpleName());
        form.setCollClienteImovelFimRelacaoMotivo(coll);
        
        if (coll == null || coll.isEmpty()) {
        	throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela",null," A Motivo Fim da Relacao está ");
        }

        return retorno;
    }
}