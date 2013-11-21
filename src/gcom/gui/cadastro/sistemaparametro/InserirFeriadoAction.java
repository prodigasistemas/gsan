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
package gcom.gui.cadastro.sistemaparametro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0534]	INSERIR FERIADO
 * 
 * @author Kássia Albuquerque
 * @date 12/01/2007
 */

public class InserirFeriadoAction extends GcomAction {

	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		InserirFeriadoActionForm inserirFeriadoActionForm = (InserirFeriadoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		String municipio = inserirFeriadoActionForm.getIdMunicipio();
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		
		if (municipio != null && !municipio.trim().equals("")) {

			FiltroTabelaAuxiliarAbreviada filtroMunicipio = new FiltroTabelaAuxiliarAbreviada();

			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliarAbreviada.ID, municipio));

			Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
	
			if (colecaoMunicipio == null || colecaoMunicipio.isEmpty()) {
				inserirFeriadoActionForm.setIdMunicipio("");
				throw new ActionServletException("atencao.municipio_inexistente");
			}
		}
		
		MunicipioFeriado municipioFeriado = null;
		NacionalFeriado nacionalFeriado = null;
		
		String nomeFeriado = null; 
		String tipoFeriado = null;
		
		if (municipio != null && !municipio.trim().equals("")) {
			municipioFeriado = new MunicipioFeriado();
			
			inserirFeriadoActionForm.setFormValuesMunicipal( municipioFeriado);
			nomeFeriado= "Municipal";
			tipoFeriado= "2";
		} else {
			nacionalFeriado = new NacionalFeriado();
			
			inserirFeriadoActionForm.setFormValuesNacional( nacionalFeriado);
			nomeFeriado = "Nacional";
			tipoFeriado= "1";
		}
		
		
		
		//Inserir na base de dados Feriado
		Integer idFeriado = fachada.inserirFeriado(nacionalFeriado, municipioFeriado,usuarioLogado);
		
		sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarFeriadoAction.do");
		
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Feriado "+ nomeFeriado + " de código " + idFeriado +" inserido com sucesso.",
				"Inserir outro Feriado","exibirInserirFeriadoAction.do?menu=sim",
				"exibirAtualizarFeriadoAction.do?tipoFeriado="+tipoFeriado+"&idRegistroInseridoAtualizar="+ idFeriado,"Atualizar Feriado Inserido");
		
		
		sessao.removeAttribute("InserirFeriadoActionForm");

		return retorno;
	}
}
