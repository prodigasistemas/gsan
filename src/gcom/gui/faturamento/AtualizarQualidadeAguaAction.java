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
* Rômulo Aurélio de Melo Souza Filho
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
package gcom.gui.faturamento;
import gcom.fachada.Fachada;
import gcom.faturamento.QualidadeAgua;
import gcom.faturamento.QualidadeAguaPadrao;
import gcom.gui.GcomAction;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Atualizar Qualidade Agua
 *
 * @author Flávio Leonardo
 */


public class AtualizarQualidadeAguaAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		AtualizarQualidadeAguaActionForm form = (AtualizarQualidadeAguaActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);	
		
		Fachada fachada = Fachada.getInstancia();
		
		QualidadeAgua qualidadeAgua = (QualidadeAgua) sessao.getAttribute("qualidadeAgua");
		
		String[] idQualidadeAgua = new String[1];
		
		idQualidadeAgua[0] = qualidadeAgua.getId().toString();
		
		//Atualiza a entidade com os valores do formulário
		form.setDadosQualidadeAgua(qualidadeAgua);
		
		//quando o valor estiver em branco na tela setar no banco como vazio
		//Flávio Leonardo CRC-1419 data: 17/03/09
		
		QualidadeAguaPadrao qualidadeAguaPadrao = new QualidadeAguaPadrao();
		
		qualidadeAguaPadrao.setId((Integer)sessao.getAttribute("qualidadeAguaPadraoId"));
		
		if (form.getPadraoTurbidez()!= null && !form.getPadraoTurbidez().equals("")){
			qualidadeAguaPadrao.setDescricaoPadraoTurbidez(form.getPadraoTurbidez());
		}else{
			qualidadeAguaPadrao.setDescricaoPadraoTurbidez("");
		}
		if (form.getPadraoCloroResidual()!= null && !form.getPadraoCloroResidual().equals("")){
			qualidadeAguaPadrao.setDescricaoPadraoCloro(form.getPadraoCloroResidual());
		}else{
			qualidadeAguaPadrao.setDescricaoPadraoCloro("");
		}
		if (form.getPadraoPH()!= null && !form.getPadraoPH().equals("")){
			qualidadeAguaPadrao.setDescricaoPadraoPh(form.getPadraoPH());
		}else{
			qualidadeAguaPadrao.setDescricaoPadraoPh("");
		}
		if (form.getPadraoCor()!= null && !form.getPadraoCor().equals("")){
			qualidadeAguaPadrao.setDescricaoPadraoCor(form.getPadraoCor());
		}else{
			qualidadeAguaPadrao.setDescricaoPadraoCor("");
		}
		if (form.getPadraoFluor()!= null && !form.getPadraoFluor().equals("")){
			qualidadeAguaPadrao.setDescricaoPadraoFluor(form.getPadraoFluor());
		}else{
			qualidadeAguaPadrao.setDescricaoPadraoFluor("");
		}
		if (form.getPadraoFerro()!= null && !form.getPadraoFerro().equals("")){
			qualidadeAguaPadrao.setDescricaoPadraoFerro(form.getPadraoFerro());
		}else{
			qualidadeAguaPadrao.setDescricaoPadraoFerro("");
		}
		if (form.getPadraoColiformesTotais()!= null && !form.getPadraoColiformesTotais().equals("")){
			qualidadeAguaPadrao.setDescricaoPadraoColiformesTotais(form.getPadraoColiformesTotais());
		}else{
			qualidadeAguaPadrao.setDescricaoPadraoColiformesTotais("");
		}
		if (form.getPadraoColiformesFecais()!= null && !form.getPadraoColiformesFecais().equals("")){
			qualidadeAguaPadrao.setDescricaoPadraoColiformesFecais(form.getPadraoColiformesFecais());
		}else{
			qualidadeAguaPadrao.setDescricaoPadraoColiformesFecais("");
		}
		if (form.getPadraoNitrato()!= null && !form.getPadraoNitrato().equals("")){
			qualidadeAguaPadrao.setDescricaoNitrato(form.getPadraoNitrato());
		}else{
			qualidadeAguaPadrao.setDescricaoNitrato("");
		}
		
		if (form.getPadraoColiformesTermotolerantes()!= null && !form.getPadraoColiformesTermotolerantes().equals("")){
			qualidadeAguaPadrao.setDescricaoPadraoColiformesTermotolerantes(form.getPadraoColiformesTermotolerantes());
		}else{
			qualidadeAguaPadrao.setDescricaoPadraoColiformesTermotolerantes("");
		}
		
		if (form.getPadraoAlcalinidade() != null && !form.getPadraoAlcalinidade().equals("")){
			
			qualidadeAguaPadrao.setDescricaoPadraoAlcalinidade(form.getPadraoAlcalinidade());
			
		}else{
			
			qualidadeAguaPadrao.setDescricaoPadraoAlcalinidade("");
			
		}
		
		qualidadeAgua.setUltimaAlteracao(new Date());
		qualidadeAguaPadrao.setUltimaAlteracao(new Date());
		//atualiza na base de dados de Qualidade Agua
		fachada.atualizar(qualidadeAgua);
		fachada.atualizar(qualidadeAguaPadrao);
		
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Qualidade de Água de código "+ form.getIdQualidadeAgua()+" atualizado com sucesso.", "Realizar outra Manutenção de Qualidade de Água",
				"exibirFiltrarQualidadeAguaAction.do?menu=sim");
		
		return retorno;
	}
	
}	      
    



