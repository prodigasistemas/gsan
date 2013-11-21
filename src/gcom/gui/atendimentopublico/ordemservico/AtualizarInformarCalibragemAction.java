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

import gcom.atendimentopublico.ordemservico.OSProgramacaoCalibragem;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarInformarCalibragemAction extends GcomAction {

	private String peso;
	private String fator;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		// Carrega as calibragens de acordo com os objetos da sessão
		Collection colecaoProgramaCalibragem = new Vector();
		if (sessao.getAttribute("colecaoProgramaCalibragem") != null) {
			colecaoProgramaCalibragem = (Collection) sessao
					.getAttribute("colecaoProgramaCalibragem");
		}
		
		Iterator colecaoProgramaCalibragemIt = colecaoProgramaCalibragem.iterator();
		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
		OSProgramacaoCalibragem osProgramaCalibragem;
		
		while (colecaoProgramaCalibragemIt.hasNext()) {
			osProgramaCalibragem = (OSProgramacaoCalibragem) colecaoProgramaCalibragemIt.next();
			
			if (requestMap.get("peso_" + osProgramaCalibragem.getId().intValue()) != null) {
				peso = (requestMap.get("peso_" + osProgramaCalibragem.getId().intValue()))[0];
			}
			
			if (peso == null
					|| peso.equalsIgnoreCase("")) {

				throw new ActionServletException(
						"atencao.campo_texto.obrigatorio", null,
						"Peso");
			}
			
			if (requestMap.get("fator_" + osProgramaCalibragem.getId().intValue()) != null) {
				fator = (requestMap.get("fator_" + osProgramaCalibragem.getId().intValue()))[0];
			}
			
			if (fator == null
					|| fator.equalsIgnoreCase("")) {

				throw new ActionServletException(
						"atencao.campo_texto.obrigatorio", null,
						"Fator");
			}
			
			osProgramaCalibragem.setFator(Integer.valueOf(fator));
			osProgramaCalibragem.setPeso(Integer.valueOf(peso));
			fachada.atualizarInformarCalibragem(osProgramaCalibragem, usuarioLogado);
		}
		
        httpServletRequest.setAttribute("caminhoFuncionalidade","exibirInformarCalibragemAction.do?menu=sim");
        httpServletRequest.setAttribute("labelPaginaSucesso","Realizar outra Manutenção na Programação Calibragem");
        httpServletRequest.setAttribute("mensagemPaginaSucesso","Dados da(s) calibragem(ens) atualizado(s) com sucesso.");		
		
		return retorno;
	}
}
