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

import gcom.atendimentopublico.ordemservico.FiltroMaterial;
import gcom.atendimentopublico.ordemservico.FiltroMaterialUnidade;
import gcom.atendimentopublico.ordemservico.Material;
import gcom.atendimentopublico.ordemservico.MaterialUnidade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
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
 * [SB0001]Atualizar Material
 *
 * @author Kássia Albuquerque
 * @date 20/11/2006
 */

public class ExibirAtualizarMaterialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarMaterial");
		AtualizarMaterialActionForm atualizarMaterialActionForm = (AtualizarMaterialActionForm)actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();

		FiltroMaterialUnidade filtroMaterialUnidade = new FiltroMaterialUnidade();
		Collection<MaterialUnidade> colecaoMaterialUnidade = fachada.pesquisar(filtroMaterialUnidade, MaterialUnidade.class.getName());
		httpServletRequest.setAttribute("colecaoMaterialUnidade", colecaoMaterialUnidade);

		Material material = null;
		String idMaterial = null;

		if (httpServletRequest.getParameter("idMaterial") != null) {
			//tela do manter
			idMaterial = (String) httpServletRequest.getParameter("idMaterial");
			sessao.setAttribute("idMaterial", idMaterial);
		} else if (sessao.getAttribute("idMaterial") != null) {
			//tela do filtrar
			idMaterial = (String) sessao.getAttribute("idMaterial");
		}else if(httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null){
			//link na tela de sucesso do inserir material
			idMaterial = (String)httpServletRequest.getParameter("idRegistroInseridoAtualizar");
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarMaterialAction.do?menu=sim");
		}

		if (idMaterial == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizar") == null){
				material = (Material) sessao.getAttribute("materialAtualizar");
			}else{
				idMaterial = (String) httpServletRequest.getAttribute("idRegistroAtualizar").toString();
			}
		}

		//------Inicio da parte que verifica se vem da página de
		// 		material_manter.jsp
		if (material == null){

			if (idMaterial != null && !idMaterial.equals("")) {
				FiltroMaterial filtroMaterial = new FiltroMaterial();
				filtroMaterial.adicionarCaminhoParaCarregamentoEntidade("materialUnidade");
				filtroMaterial.adicionarParametro(new ParametroSimples(FiltroMaterialUnidade.ID, idMaterial));
				Collection<Material> colecaoMaterial = fachada.pesquisar(filtroMaterial, Material.class.getName());

				if (colecaoMaterial != null && !colecaoMaterial.isEmpty()) {
					material = (Material) Util.retonarObjetoDeColecao(colecaoMaterial);
				}
			}
		}

		//  ------  O material foi encontrado
		atualizarMaterialActionForm.setId(String.valueOf(material.getId()));
		if(material.getCodigo() == null){
			atualizarMaterialActionForm.setCodigo("");
		}else{
			atualizarMaterialActionForm.setCodigo(String.valueOf(material.getCodigo()));
		}
		atualizarMaterialActionForm.setDescricaoMaterial(material.getDescricao());
		atualizarMaterialActionForm.setAbrevMaterial(material.getDescricaoAbreviada());
		atualizarMaterialActionForm.setUnidadeMaterial(material.getMaterialUnidade().getId().toString());
		atualizarMaterialActionForm.setIndicadorUso(String.valueOf(material.getIndicadorUso()));
		sessao.setAttribute("material", material);
		// ------ Fim da parte que verifica se vem da página de material_manter.jsp

		return retorno;
	}
}