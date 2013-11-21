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

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.FiltroAtividade;
import gcom.atendimentopublico.ordemservico.FiltroMaterial;
import gcom.atendimentopublico.ordemservico.FiltroPerfilServico;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoSubgrupo;
import gcom.atendimentopublico.ordemservico.Material;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.ServicoTipoSubgrupo;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirFiltrarTipoServicoAction extends GcomAction {

    /**
     * < <Descrição do método>>
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
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
    	FiltrarTipoServicoActionForm form = (FiltrarTipoServicoActionForm) actionForm;

        //Seta o mapeamento de retorno
        ActionForward retorno = null;
        String redirecionarPagina = httpServletRequest.getParameter("redirecionarPagina");
        if(redirecionarPagina != null && !redirecionarPagina.equals("")){
        	retorno = actionMapping
            .findForward(redirecionarPagina);
        	return retorno; 
        }else{
        	retorno = actionMapping
            .findForward("filtrarTipoServico");
        }     

        Fachada fachada = Fachada.getInstancia();

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);
        
        if (httpServletRequest.getParameter("limparCampos") != null) {
        	form.limparForm();
        	form.setArrayAtividade(new ArrayList());
        	form.setArrayMateriais(new ArrayList());        	
         }
        
        //verifica se é importancia tipo servico
        if(httpServletRequest.getParameter("imp")!=null && 
				httpServletRequest.getParameter("imp").equals("sim")){
        	form.setGrauImportancia("1");
			sessao.setAttribute("indicadorGrauImportancia",
			"true");
		}
        
        // Seta o Indicativo Tipo de Sevico por Economias como valor default "Não"
        //form.setIndicativoTipoSevicoEconomias("2");
        
        if(sessao.getAttribute("colecaoSubgrupo") == null){
        	FiltroServicoTipoSubgrupo filtroServicoTipoSubgrupo = new FiltroServicoTipoSubgrupo();
        	Collection colecaoSubgrupo = fachada.pesquisar(filtroServicoTipoSubgrupo, ServicoTipoSubgrupo.class.getName());
        	sessao.setAttribute("colecaoSubgrupo", colecaoSubgrupo);
        }
        
        if(sessao.getAttribute("colecaoCreditoTipo") == null){
        	FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
        	Collection colecaoCreditoTipo = fachada.pesquisar(filtroCreditoTipo, CreditoTipo.class.getName());
        	sessao.setAttribute("colecaoCreditoTipo", colecaoCreditoTipo);
        }
        
        if(sessao.getAttribute("colecaoPrioridade") == null){
        	FiltroServicoTipoPrioridade filtroServicoTipoPrioridade = new FiltroServicoTipoPrioridade();
        	Collection colecaoPrioridade = fachada.pesquisar(filtroServicoTipoPrioridade, ServicoTipoPrioridade.class.getName());
        	sessao.setAttribute("colecaoPrioridade", colecaoPrioridade);
        }
        
        if(form.getIdTipoDebito() != null && !form.getIdTipoDebito().trim().equals("")){
        	FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
        	filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, form.getIdTipoDebito()));
        	
        	Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
        	
        	if(colecaoDebitoTipo.isEmpty()){
        		form.setIdTipoDebito("");
				httpServletRequest.setAttribute("corTipoDebito","exception");
				form.setDescricaoTipoDebito("Tipo de Débito Inexistente");
        	}else{
        		DebitoTipo debitoTipo = (DebitoTipo)colecaoDebitoTipo.iterator().next();
        		form.setIdTipoDebito(debitoTipo.getId().toString());
        		form.setDescricaoTipoDebito(debitoTipo.getDescricao());
				httpServletRequest.setAttribute("corTipoDebito", "valor");        		
        	}
        }
        
        if(form.getPerfilServico() != null && !form.getPerfilServico().trim().equals("")){
        	FiltroPerfilServico filtroPerfilServico = new FiltroPerfilServico();
        	filtroPerfilServico.adicionarParametro(new ParametroSimples(FiltroPerfilServico.ID, form.getPerfilServico()));
        	
        	Collection colecaoPerfilServico = fachada.pesquisar(filtroPerfilServico, ServicoPerfilTipo.class.getName());
        	
        	if(colecaoPerfilServico.isEmpty()){
        		form.setPerfilServico("");
				httpServletRequest.setAttribute("corPerfilTipo","exception");
				form.setDescricaoPerfilServico("Perfil do Serviço Inexistente");
        	}else{
        		ServicoPerfilTipo servicoPerfilTipo = (ServicoPerfilTipo)colecaoPerfilServico.iterator().next();
        		form.setPerfilServico(servicoPerfilTipo.getId().toString());
        		form.setDescricaoPerfilServico(servicoPerfilTipo.getDescricao());
				httpServletRequest.setAttribute("corPerfilTipo", "valor");
        	}
        	
        }
        
        if(form.getIdTipoServicoReferencia() != null && !form.getIdTipoServicoReferencia().trim().equals("")){
        	FiltroServicoTipoReferencia filtroServicoTipoReferencia = new FiltroServicoTipoReferencia();
        	filtroServicoTipoReferencia.adicionarParametro(new ParametroSimples(FiltroServicoTipoReferencia.ID, form.getIdTipoServicoReferencia()));
        	
        	Collection colecaoServicoTipoReferencia = fachada.pesquisar(filtroServicoTipoReferencia, ServicoTipoReferencia.class.getName());
        	
        	if(colecaoServicoTipoReferencia.isEmpty()){
        		form.setIdTipoServicoReferencia("");
				httpServletRequest.setAttribute("corTipoReferencia","exception");
				form.setDescricaoTipoServicoReferencia("Tipo de Serviço de Referência Inexistente");
        	}else{
        		ServicoTipoReferencia servicoTipoReferencia = (ServicoTipoReferencia)colecaoServicoTipoReferencia.iterator().next();
        		form.setIdTipoServicoReferencia(servicoTipoReferencia.getId().toString());
        		form.setDescricaoTipoServicoReferencia(servicoTipoReferencia.getDescricao());
				httpServletRequest.setAttribute("corTipoReferencia", "valor");
        	}
        	
        }        

        //Resultado da Pesquisa pelo popup
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {

			// Atividade do tipo Servico
			if (httpServletRequest.getParameter("tipoConsulta").equals("atividade")) {
				form.setAtividadesTipoServico(httpServletRequest.getParameter("idCampoEnviarDados"));
				form.setDsAtividadesTipoServico(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}
			// Material do Tipo de Servico
			else if (httpServletRequest.getParameter("tipoConsulta").equals(
					"material")) {
				form.setTipoServicoMaterial(httpServletRequest.getParameter("idCampoEnviarDados"));
				form.setDsTipoServicoMaterial(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}
		}        
        
		// Atividades do tipo de Serviço
		getAtividadesTipoServico(form, fachada);
		// Tipo de Material
		getTipoServicoMaterial(form, fachada);
		
		// Remove componete na coleção Atividade
		if ("removeServicoTipoAtividade".equals(form.getMethod())) {
				Integer id = new Integer(form.getTipoServicoAtividadeId());
			if (id!= null){
				form.removeServicoTipoAtividade(id,form.getAtividades(),form.getArrayAtividade());
				form.setMethod("");
			}
		}
		// Remove componete na coleção Material
		if ("removeServicoTipoMaterial".equals(form.getMethod())) {
			Integer id = new Integer(form.getTipoServicoMaterialId());
			if (id!= null){
				form.removeServicoTipoMateriais(id,form.getMateriais(),form.getArrayMateriais());
				
				form.setMethod("");
			}
		}

        return retorno;

    }
    
	/**
	 * Validar atividade 
	 * 
	 * @param form
	 */

	private void getAtividadesTipoServico(
			FiltrarTipoServicoActionForm form, Fachada fachada) {

		String idAtividadesTipoServico = form.getAtividadesTipoServico();

		if (idAtividadesTipoServico != null
				&& !idAtividadesTipoServico.trim().equals("")) {

			// Filtro para obter o Equipamento Especial do id informado
			FiltroAtividade filtroAtividade = new FiltroAtividade();

			filtroAtividade.adicionarParametro(new ParametroSimples(
					FiltroAtividade.ID, idAtividadesTipoServico));
			
			// Pesquisa de acordo com os parâmetros informados no filtro
			Collection colecaoServicoTipoAtividade = Fachada.getInstancia()
					.pesquisar(filtroAtividade, Atividade.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (colecaoServicoTipoAtividade != null
					&& !colecaoServicoTipoAtividade.isEmpty()) {

				Atividade servicoTipoAtividade = (Atividade) Util
						.retonarObjetoDeColecao(colecaoServicoTipoAtividade);

				if ((servicoTipoAtividade != null && !servicoTipoAtividade
						.equals(""))) {
					form.setAtividadesTipoServico(servicoTipoAtividade
									.getId().toString());
					form.setDsAtividadesTipoServico(servicoTipoAtividade
									.getDescricao());

					// add aividades na coleção
					setColecaoAtividades(form, servicoTipoAtividade);
					
					form.setAtividadesTipoServico("");
					form.setDsAtividadesTipoServico("");
					form.setDsAtividadeCor("#000000");
				}
				
			} else {
				// [FS0006] Validar atividade
				form.setAtividadesTipoServico("");
				form.setDsAtividadesTipoServico("Atividada Inexistente!");
				form.setDsAtividadeCor("#FF0000");

			}
		}
	} 
	
	/**
	 * Seta novo Componente na Coleção
	 * 
	 * @param form
	 * @param atividades
	 */
	private void setColecaoAtividades(
			FiltrarTipoServicoActionForm form,Atividade atividade) {
		if(!form.getAtividades().contains(atividade)){
			form.getArrayAtividade().add(atividade);
			form.getAtividades().add(atividade.getId());
		}
	}	
	
	/**
	 * Recupera Materiais do Tipo de Servico [FS0007] Validar Material
	 * 
	 * @param form
	 */

	private void getTipoServicoMaterial(
			FiltrarTipoServicoActionForm form, Fachada fachada) {
		
		String idTipoServicoMaterial = form.getTipoServicoMaterial();

		if (idTipoServicoMaterial != null && !idTipoServicoMaterial.trim().equals("")) {

			// Filtro para obter o Equipamento Especial do id informado
			FiltroMaterial filtroMaterial = new FiltroMaterial();

			filtroMaterial.adicionarParametro(new ParametroSimples(
					FiltroMaterial.ID, idTipoServicoMaterial));
			
			// Pesquisa de acordo com os parâmetros informados no filtro

			Collection colecaoServicoTipoMaterial = Fachada.getInstancia()
					.pesquisar(filtroMaterial,
							Material.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (colecaoServicoTipoMaterial != null
					&& !colecaoServicoTipoMaterial.isEmpty()) {

				Material servicoTipoMaterial = (Material) Util
						.retonarObjetoDeColecao(colecaoServicoTipoMaterial);
				if ((servicoTipoMaterial != null && !servicoTipoMaterial
						.equals(""))) {
					form.setTipoServicoMaterial(servicoTipoMaterial.getId().toString());
					form.setDsTipoServicoMaterial(servicoTipoMaterial.getDescricao());
					
					// add componente material da coleção
					setColecaoMateriais(form,servicoTipoMaterial);
					//	sessao.setAttribute("materiais",servicoTipoMaterial );
					}
				form.setTipoServicoMaterial("");
				form.setDsTipoServicoMaterial("");
				form.setDsMaterialCor("#000000");
				}

			 else {
				//[FS0004] Validar tipo de Servico Material
				form.setTipoServicoMaterial("");
				form.setDsTipoServicoMaterial("Material do Tipo de Serviço Inexistente");
				form.setDsMaterialCor("#FF0000");
			}
		}
	}
	
	/**
	 * Seta novo Componente na Coleção
	 * 
	 * @param form
	 * @param materiais
	 */
	private void setColecaoMateriais(
			FiltrarTipoServicoActionForm form, Material servicoTipoMaterial) {
		if(!form.getMateriais().contains(servicoTipoMaterial)){
			form.getArrayMateriais().add(servicoTipoMaterial);
			form.getMateriais().add(servicoTipoMaterial.getId());
		}
	}	
}
