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
package gcom.gui.cobranca;

import java.util.Iterator;
import java.util.List;

import gcom.cobranca.CobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.micromedicao.InformarItensContratoServicoHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarCobrancaGrupoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarCobrancaGrupoActionForm form = (AtualizarCobrancaGrupoActionForm) actionForm;

		CobrancaGrupo cobrancaGrupo = (CobrancaGrupo) sessao.getAttribute("atualizarCobrancaGrupo");
		
		
		String idCobrancaGrupo = form.getId();	
		
		//Descrição
		String descricao = form.getDescricao();
		if (descricao == null || "".equals(descricao)) {
			throw new ActionServletException("atencao.required", null,"Descrição");
		}
		
		// Descrição Abreviada
		String descricaoAbreviada = form.getDescricaoAbreviada();
		if (descricaoAbreviada == null || 
			"".equals(descricaoAbreviada ) ) {
			throw new ActionServletException("atencao.required", null,"Descrição Abreviada");
		}
		
		//Ano Mes Referencia
		String anoMesReferencia = form.getAnoMesReferencia();
		if ( anoMesReferencia == null || anoMesReferencia.equals("") ) {
			throw new ActionServletException("atencao.required", null,"Mês/Ano de Referência");
		}
        
		//Indicador de Uso
        Short indicadorUso = form.getIndicadorUso();
        if ( indicadorUso != null && !indicadorUso.equals("") ) {
        	indicadorUso = form.getIndicadorUso() ;
		} 
        
		String emailResponsavel = form.getEmailResponsavel();

		//Ano Mes Referencia
		Short indicadorExecucaoAutomatica = form.getIndicadorExecucaoAutomatica();
		if ( indicadorExecucaoAutomatica == null || indicadorExecucaoAutomatica.equals("") ) {
			throw new ActionServletException("atencao.required", null, "Execução Automática" );
		}
		
		ContratoEmpresaServico contratoEmpresaServico = null;
		if (form.getIdNumeroContrato() != null && !form.getIdNumeroContrato().equals("")
				&& sessao.getAttribute("collectionContrato") != null
				&& !sessao.getAttribute("collectionContrato").equals("")) {
			List colecaoHelper = (List) sessao.getAttribute("collectionContrato");
			int posicaoComponente = new Integer(form.getIdNumeroContrato());
				
			Iterator iColecaoHelper = colecaoHelper.iterator();
			
			while (iColecaoHelper.hasNext()){
				
				InformarItensContratoServicoHelper helper = (InformarItensContratoServicoHelper)iColecaoHelper.next();
					
				if (helper.getContratoEmpresaServico() != null && 
						helper.getContratoEmpresaServico().getId() == posicaoComponente){
					
					contratoEmpresaServico = helper.getContratoEmpresaServico();
				}
				
			}
			
		}
		
        //Concatena ano mes para insercao
        String mes = anoMesReferencia.substring(0, 2);
        String ano = anoMesReferencia.substring(3, 7);

        anoMesReferencia = ano + "" + mes;
        
        cobrancaGrupo.setId(new Integer( idCobrancaGrupo ));
        cobrancaGrupo.setDescricao(descricao);
        cobrancaGrupo.setDescricaoAbreviada(descricaoAbreviada);
        cobrancaGrupo.setAnoMesReferencia(new Integer( anoMesReferencia ));
        cobrancaGrupo.setIndicadorUso(indicadorUso);
        cobrancaGrupo.setIndicadorExecucaoAutomatica(indicadorExecucaoAutomatica);
        cobrancaGrupo.setEmailResponsavel(emailResponsavel);
        cobrancaGrupo.setContratoEmpresaServico(contratoEmpresaServico);
		fachada.atualizar(cobrancaGrupo);

		
		montarPaginaSucesso(httpServletRequest, "Grupo de Cobrança "
				+ idCobrancaGrupo + " atualizado com sucesso.",
				"Realizar outra Manutenção de Grupo de Cobrança ",
				"exibirFiltrarCobrancaGrupoAction.do?menu=sim");        
        
		return retorno;
	}
}