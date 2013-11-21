package gcom.gui.cadastro.entidadebeneficente;

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

import gcom.cadastro.imovel.EntidadeBeneficente;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Hugo Fernando.
 *
 */


public class AtualizarEntidadeBeneficenteAction extends GcomAction {
	
	/**
	 * Este caso de uso permite atualizar uma Entidade Beneficente.
	 * 
	 * [UC0916] Manter Entidade Beneficente
	 * 
	 * @author Hugo Fernando
	 * @date 22/01/2010
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarEntidadeBeneficenteActionForm form = (AtualizarEntidadeBeneficenteActionForm) actionForm;

		
		Fachada fachada = Fachada.getInstancia();
		

		HttpSession sessao = httpServletRequest.getSession(false);
		
		
		EntidadeBeneficente entidadeBeneficente = (EntidadeBeneficente)sessao.getAttribute("entidadeBeneficenteAtualizar");
		
		EntidadeBeneficente EntidadeBeneficenteAtualizar = this.
		getEntidadeBeneficente(entidadeBeneficente, form, fachada);
		
		

		
		fachada.atualizar(EntidadeBeneficenteAtualizar);
		
		sessao.removeAttribute("idEntidadeBeneficente");
		
		sessao.removeAttribute("entidadeBeneficenteAtualizar");
		
		montarPaginaSucesso(httpServletRequest, "Entidade beneficente de código "
				+ entidadeBeneficente.getId() + " atualizada com sucesso.",
				"Manter outra Entidade Beneficente",
				"exibirFiltrarEntidadeBeneficenteAction.do?menu=sim");
				
		
		return retorno;
	}


	/**
	 * [UC0916] Manter Entidade Beneficente
	 *
	 * Carregando os dados da Entidade Beneficente a partir do que foi informado no formulário
	 *
	 * @author Hugo Fernando
	 * @date 22/01/2010
	 *
	 * @param entidadeBeneficnete
	 * @param form
	 * @param fachada
	 * @return EntidadeBeneficente
	 */
	private EntidadeBeneficente getEntidadeBeneficente(EntidadeBeneficente entidadeBeneficnete
			, AtualizarEntidadeBeneficenteActionForm form, 
		Fachada fachada){
	
		
		// Cliente 
		if( form.getCliente() != null ){
		entidadeBeneficnete.setCliente(form.getCliente());
		}
		
		// Tipo de debito
		if( form.getDebitoTipo() != null ){
			entidadeBeneficnete.setDebitoTipo(form.getDebitoTipo());
		}
		
		// Empresa
		if( form.getEmpresa() != null ){
			entidadeBeneficnete.setEmpresa(form.getEmpresa());
		}
		
		// Inicio Adesao
		if( form.getInicioMesAnoAdesao() != null ){
			
			String  anoInicioAdesao = form.getInicioMesAnoAdesao().substring(3);
			String mesInicioAdesao = form.getInicioMesAnoAdesao().substring(0,2);
			String anoMesInicioAdesao = anoInicioAdesao+""+mesInicioAdesao;			
			
			entidadeBeneficnete.setAnoMesContratoInicial(new Integer(anoMesInicioAdesao));
		}
		
		
		// Fim Adesao
		if( form.getFimMesAnoAdesao() != null ){
			
			String  anoFimAdesao = form.getFimMesAnoAdesao().substring(3);
			String mesFimAdesao = form.getFimMesAnoAdesao().substring(0,2);
			String anoMesFimAdesao = anoFimAdesao+""+mesFimAdesao;	
			
			entidadeBeneficnete.setAnoMesContratoFinal(new Integer(anoMesFimAdesao));
		}
		
		
		// Ultima alteracao
		entidadeBeneficnete.setUltimaAlteracao(new Date());
		

		
		return entidadeBeneficnete;

	}
}