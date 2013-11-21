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
 * [UC0535] Manter Feriado [SB0001]Atualizar Feriado
 *
 * @author Kássia Albuquerque
 * @date 24/01/2007
 */


public class AtualizarFeriadoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("telaSucesso");
			
			AtualizarFeriadoActionForm atualizarFeriadoActionForm = (AtualizarFeriadoActionForm) actionForm;
			
			Fachada fachada = Fachada.getInstancia();
			
			HttpSession sessao = httpServletRequest.getSession(false);		
			
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			
			
			/*if ((atualizarFeriadoActionForm.getIdMunicipio() != null && !atualizarFeriadoActionForm.getIdMunicipio().equals(""))) {

				FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();

				filtroTabelaAuxiliarAbreviada.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliarAbreviada.ID, atualizarFeriadoActionForm.getIdMunicipio()));

				Collection colecaoMunicipio = fachada.pesquisar(filtroTabelaAuxiliarAbreviada,Municipio.class.getName());

				if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
					Municipio municipio = (Municipio) colecaoMunicipio.iterator().next();
					atualizarFeriadoActionForm.setDescricaoMunicipio(municipio.getNome());
				} else {
					httpServletRequest.setAttribute("municipioEncontrado", "exception");
					atualizarFeriadoActionForm.setIdMunicipio("");
					atualizarFeriadoActionForm.setDescricaoMunicipio("MUNICIPIO INEXISTENTE");
				}

			}*/
			
			MunicipioFeriado municipioFeriado = null;
			NacionalFeriado nacionalFeriado = null;
			String tipoFeriado = null;
						
			if (atualizarFeriadoActionForm.getIdMunicipio() != null && 
					!atualizarFeriadoActionForm.getIdMunicipio().trim().equals("")) {
				
				FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();

				filtroTabelaAuxiliarAbreviada.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliarAbreviada.ID, atualizarFeriadoActionForm.getIdMunicipio()));

				Collection colecaoMunicipio = fachada.pesquisar(filtroTabelaAuxiliarAbreviada,Municipio.class.getName());

				if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
					Municipio municipio = (Municipio) colecaoMunicipio.iterator().next();
					atualizarFeriadoActionForm.setDescricaoMunicipio(municipio.getNome());
				} else {
					httpServletRequest.setAttribute("municipioEncontrado", "exception");
					atualizarFeriadoActionForm.setIdMunicipio("");
					atualizarFeriadoActionForm.setDescricaoMunicipio("MUNICIPIO INEXISTENTE");
				}

				municipioFeriado = (MunicipioFeriado) sessao.getAttribute("municipioFeriado");
				atualizarFeriadoActionForm.setFormValuesMunicipal( municipioFeriado);
				tipoFeriado= "Municipal";
				
			} else {
				
				nacionalFeriado = (NacionalFeriado) sessao.getAttribute("nacionalFeriado");
				atualizarFeriadoActionForm.setFormValuesNacional( nacionalFeriado);
				tipoFeriado= "Nacional";
				
			}
			
			//atualiza na base de dados feriado
			 fachada.atualizarFeriado(nacionalFeriado,municipioFeriado,usuarioLogado);
			
			//Monta a página de sucesso
			montarPaginaSucesso(httpServletRequest, "Feriado "+ tipoFeriado + " " +"de código "+ 
					atualizarFeriadoActionForm.getCodigoFeriado() +" atualizado com sucesso.", 
					"Realizar outra Manutenção de Feriado","exibirFiltrarFeriadoAction.do?menu=sim");
		
		
				
			return retorno;
	}
		
}
	
	      
    




