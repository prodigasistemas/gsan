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
* Thiago Silva Toscano de Brito
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
package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Thiago Silva Toscano de Brito, 
 *  	   Yara Taciane de Souza.
 * @date 22/12/2007
 */
public class ExcluirNegativacaoOnLineAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		ExcluirNegativacaoOnLineActionForm form = (ExcluirNegativacaoOnLineActionForm) actionForm; 
 
		NegativadorMovimentoReg negativadorMovimentoReg = (NegativadorMovimentoReg) getSessao(httpServletRequest).getAttribute("negativadorMovimentoReg");
		Imovel imovel = (Imovel) getSessao(httpServletRequest).getAttribute("imovel");
		Collection itensConta = (Collection) getSessao(httpServletRequest).getAttribute("itensConta");
		Collection itensGuiaPagamento = (Collection) getSessao(httpServletRequest).getAttribute("itensGuiaPagamento");

		NegativadorExclusaoMotivo negativadorExclusaoMotivo = new NegativadorExclusaoMotivo();
		negativadorExclusaoMotivo.setId(new Integer(form.getMotivoExclusao()));
		
		Date dataExclusao = Util.converteStringParaDate(form.getDataExclusao());

		Usuario usuarioPreenchido = new Usuario();
		usuarioPreenchido.setId(new Integer(form.getIdUsuario()));
		
			
		//..........................................................................................................

		if (!"".equals(form.getDataExclusao()) && form.getDataExclusao() != null) {
			
			String dtExclusao = form.getDataExclusao();
			if (Util.validarDiaMesAno(dtExclusao)) {
				throw new ActionServletException(
						"atencao.data_exclusao_invalida");
			}		
			Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
			
			if(Util.compararData(dataExclusao, dataAtualSemHora) ==  1){
				String dataAtual = Util.formatarData(new Date());
				throw new ActionServletException(
						"atencao.data_exclusao_superior_data_corrente", null,
						dataAtual);
			}			
			
		} else {
			throw new ActionServletException("atencao.required", null,
					"Data de Exclusão");
		}
		
		//..........................................................................................................
		if ((!"".equals(form.getDataExclusao()) && form.getDataExclusao() != null) && (!"".equals(form.getDataEnvio()) && form.getDataEnvio() != null)) {
			
			Date dtExclusao = Util.converteStringParaDate(form.getDataExclusao());
			Date dtEnvio =  negativadorMovimentoReg.getNegativadorMovimento().getDataProcessamentoEnvio();					
			String dataEnvio= Util.formatarData(dtEnvio);
			
			if(Util.compararData(dtExclusao, dtEnvio) == -1){				
				throw new ActionServletException(
						"atencao.data_exclusao_nao_pode_inferior_data_envio", null,dataEnvio);
			}			
			
		} 
		
		//..........................................................................................................
		
		Fachada.getInstancia().excluirNegativacaoOnLine(imovel, negativadorMovimentoReg, itensConta, itensGuiaPagamento, negativadorExclusaoMotivo, dataExclusao, usuarioPreenchido,usuarioLogado);

		montarPaginaSucesso(httpServletRequest, "Exclusão do imóvel " + form.getIdImovel() + " efetuada com sucesso.",
				"Excluir outra Negativação","exibirExcluirNegativacaoOnLineAction.do?menu=sim","", "");
		
		

		
		
		
		
		return retorno;
	}
}