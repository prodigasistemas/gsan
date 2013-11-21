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
package gcom.gui.cadastro;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
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
 * [UC0884]Filtrar Cep
 * 
 * @author Vinícius Medeiros
 * @date 12/02/2009
 */

public class FiltrarCepAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho do retorno
		ActionForward retorno = actionMapping.findForward("exibirAtualizarCep");

		// Mudar isso quando for implementado o esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		FiltrarCepActionForm filtrarCepActionForm = (FiltrarCepActionForm) actionForm;

		FiltroCep filtroCep = new FiltroCep();

		boolean informouCodigo = false;
		
		String codigo = filtrarCepActionForm.getCodigo();
		String codigoCep = null;
		
		if(codigo.length() == 10){
			codigoCep = Util.retirarFormatacaoCEP(filtrarCepActionForm.getCodigo());
		} else if (codigo.length() == 8){
			codigoCep = codigo;
		}
		
		if (codigoCep != null && !codigoCep.trim().equals("")) {
			
			//boolean achou = fachada.verificarExistenciaAgente(new Integer(codigoCep));
			
			//if (achou) {
				informouCodigo = true;
				filtroCep.adicionarParametro(
						new ParametroSimples(FiltroCep.CODIGO, 
								codigoCep));
			
			//}
		}
		
		Collection<Cep> colecaoCep = fachada.pesquisar(
				filtroCep, Cep.class.getName());

		// Verificar a existencia de um CEP
		if (colecaoCep.isEmpty()) {
			
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"CEP");
		}

		// Filtragem sem parametros
		if (!informouCodigo == true) {
		
			throw new ActionServletException(
					"atencao.cep_deve_ser_informado");
		}
		
		// Pesquisa sem registros
		if (colecaoCep == null || colecaoCep.isEmpty()) {
			
			throw new ActionServletException("atencao.cep.inexistente");
		
		} else {
		
			httpServletRequest.setAttribute("colecaoCep",colecaoCep);
			Cep cep = new Cep();
			cep = (Cep) Util.retonarObjetoDeColecao(colecaoCep);
			String idRegistroAtualizacao = cep.getCepId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
			httpServletRequest.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroCep", filtroCep);

		httpServletRequest.setAttribute("filtroCep",filtroCep);

		return retorno;

	}
}
