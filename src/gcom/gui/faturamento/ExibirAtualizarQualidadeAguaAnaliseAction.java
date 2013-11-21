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

import gcom.faturamento.QualidadeAgua;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarQualidadeAguaAnaliseAction extends GcomAction {

	/**
	 * Description of the Method
	 */
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

		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarQualidadeAguaAnaliseAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Integer entrada = (Integer)sessao.getAttribute("PrimeiraVez");
		
		if(entrada==1){
		
		QualidadeAgua qualidadeAgua = (QualidadeAgua) sessao.getAttribute("qualidadeAgua"); 
		
		AtualizarQualidadeAguaActionForm form = (AtualizarQualidadeAguaActionForm) actionForm;
		
		form.setQuantidadeTurbidezExigidas(qualidadeAgua
				.getQuantidadeTurbidezExigidas() != null ? qualidadeAgua
						.getQuantidadeTurbidezExigidas().toString() : "");
		
		form.setQuantidadeTurbidezAnalisadas(qualidadeAgua
				.getQuantidadeTurbidezAnalisadas() != null ? qualidadeAgua
				.getQuantidadeTurbidezAnalisadas().toString() : "");
		
		form.setQuantidadeTurbidezConforme(qualidadeAgua
				.getQuantidadeTurbidezConforme() != null ? qualidadeAgua
				.getQuantidadeTurbidezConforme().toString() : "");
		
		form.setQuantidadeCorExigidas(qualidadeAgua
				.getQuantidadeCorExigidas() != null ? qualidadeAgua
				.getQuantidadeCorExigidas().toString() : "");
		
		form.setQuantidadeCorAnalisadas(qualidadeAgua
				.getQuantidadeCorAnalisadas() != null ? qualidadeAgua
				.getQuantidadeCorAnalisadas().toString() : "");
		
		form.setQuantidadeCorConforme(qualidadeAgua
				.getQuantidadeCorConforme() != null ? qualidadeAgua
				.getQuantidadeCorConforme().toString() : "");
		
		form.setQuantidadeCloroExigidas(qualidadeAgua
				.getQuantidadeCloroExigidas() != null ? qualidadeAgua
				.getQuantidadeCloroExigidas().toString() : "");
		
		form.setQuantidadeCloroAnalisadas(qualidadeAgua
				.getQuantidadeCloroAnalisadas() != null ? qualidadeAgua
				.getQuantidadeCloroAnalisadas().toString() : "");
		
		form.setQuantidadeCloroConforme(qualidadeAgua
				.getQuantidadeCloroConforme() != null ? qualidadeAgua
				.getQuantidadeCloroConforme().toString() : "");
		
		form.setQuantidadeFluorExigidas(qualidadeAgua
				.getQuantidadeFluorExigidas() != null ? qualidadeAgua
				.getQuantidadeFluorExigidas().toString() : "");
		
		form.setQuantidadeFluorAnalisadas(qualidadeAgua
				.getQuantidadeFluorAnalisadas() != null ? qualidadeAgua
				.getQuantidadeFluorAnalisadas().toString() : "");
		
		form.setQuantidadeFluorConforme(qualidadeAgua
				.getQuantidadeFluorConforme() != null ? qualidadeAgua
				.getQuantidadeFluorConforme().toString() : "");
		
		form.setQuantidadeColiformesTotaisExigidas(qualidadeAgua
				.getQuantidadeColiformesTotaisExigidas() != null ? qualidadeAgua
				.getQuantidadeColiformesTotaisExigidas().toString() : "");
		
		form.setQuantidadeColiformesTotaisAnalisadas(qualidadeAgua
				.getQuantidadeColiformesTotaisAnalisadas() != null ? qualidadeAgua
				.getQuantidadeColiformesTotaisAnalisadas().toString() : "");
		
		form.setQuantidadeColiformesTotaisConforme(qualidadeAgua
				.getQuantidadeColiformesTotaisConforme() != null ? qualidadeAgua
				.getQuantidadeColiformesTotaisConforme().toString() : "");
		
		form.setQuantidadeColiformesFecaisExigidas(qualidadeAgua
				.getQuantidadeColiformesFecaisExigidas() != null ? qualidadeAgua
				.getQuantidadeColiformesFecaisExigidas().toString() : "");
		
		form.setQuantidadeColiformesFecaisAnalisadas(qualidadeAgua
				.getQuantidadeColiformesFecaisAnalisadas() != null ? qualidadeAgua
				.getQuantidadeColiformesFecaisAnalisadas().toString() : "");
		
		form.setQuantidadeColiformesFecaisConforme(qualidadeAgua
				.getQuantidadeColiformesFecaisConforme() != null ? qualidadeAgua
				.getQuantidadeColiformesFecaisConforme().toString() : "");
		
		form.setQuantidadeColiformesTermotolerantesExigidas(qualidadeAgua
				.getQuantidadeColiformesTermotolerantesExigidas() != null ? qualidadeAgua
				.getQuantidadeColiformesTermotolerantesExigidas().toString() : "");
		
		form.setQuantidadeColiformesTermotolerantesAnalisadas(qualidadeAgua
				.getQuantidadeColiformesTermotolerantesAnalisadas() != null ? qualidadeAgua
				.getQuantidadeColiformesTermotolerantesAnalisadas().toString() : "");
		
		form.setQuantidadeColiformesTermotolerantesConforme(qualidadeAgua
				.getQuantidadeColiformesTermotolerantesConforme() != null ? qualidadeAgua
				.getQuantidadeColiformesTermotolerantesConforme().toString() : "");
		
		form.setQuantidadeAlcalinidadeExigidas(qualidadeAgua
				.getQuantidadeAlcalinidadeExigidas() != null ? qualidadeAgua
				.getQuantidadeAlcalinidadeExigidas().toString() : "");
		
		form.setQuantidadeAlcalinidadeAnalisadas(qualidadeAgua
				.getQuantidadeAlcalinidadeAnalisadas() != null ? qualidadeAgua
				.getQuantidadeAlcalinidadeAnalisadas().toString() : "");
		
		form.setQuantidadeAlcalinidadeConforme(qualidadeAgua
				.getQuantidadeAlcalinidadeConforme() != null ? qualidadeAgua
				.getQuantidadeAlcalinidadeConforme().toString() : "");
		}
		return retorno;
		
		
	}

}
