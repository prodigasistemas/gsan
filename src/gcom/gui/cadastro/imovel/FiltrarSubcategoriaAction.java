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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Fernanda Karla
 * @created 30 de Dezembro de 2005
 */
public class FiltrarSubcategoriaAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarSubcategoriaActionForm filtrarSubcategoriaActionForm = (FiltrarSubcategoriaActionForm) actionForm;

		// Recupera os parâmetros do form
		Integer id = new Integer(filtrarSubcategoriaActionForm.getIdCategoria());
		String descricao = filtrarSubcategoriaActionForm.getDescricao();
		String indicadorUso = filtrarSubcategoriaActionForm.getIndicadorUso();
        String descricaoAbreviada = filtrarSubcategoriaActionForm.getDescricaoAbreviada();
        String codigoTarifaSocial = filtrarSubcategoriaActionForm.getCodigoTarifaSocial();
        String codigoGrupoSubcategoria = filtrarSubcategoriaActionForm.getCodigoGrupoSubcategoria() ;
        String  numeroFatorFiscalizacao =  filtrarSubcategoriaActionForm.getNumeroFatorFiscalizacao() ;
        String  indicadorTarifaConsumo =  filtrarSubcategoriaActionForm.getIndicadorTarifaConsumo() ;
        String indicadorSazonalidade = filtrarSubcategoriaActionForm.getIndicadorSazonalidade();
        
		String atualizar = httpServletRequest.getParameter("atualizar");

		FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria(
				FiltroSubCategoria.DESCRICAO);

		/*filtroSubcategoria
				.adicionarCaminhoParaCarregamentoEntidade(FiltroSubCategoria.CODIGO);*/
		
		filtroSubcategoria
				.adicionarCaminhoParaCarregamentoEntidade("categoria");

		boolean peloMenosUmParametroInformado = false;
		
				// Insere os parâmetros informados no filtro
		if (id != null
				&& id.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			filtroSubcategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.CATEGORIA_ID, id));
		}
		//Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroSubcategoria.adicionarParametro(new ComparacaoTexto(
					FiltroSubCategoria.DESCRICAO, descricao));
		}
		//Descricao Abreviada
		if (descricaoAbreviada != null && !descricaoAbreviada.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroSubcategoria.adicionarParametro(new ParametroSimples(
                    FiltroSubCategoria.DESCRICAO_ABREVIADA, descricaoAbreviada));
        }
		//Codigo Tarifa Social
		if (codigoTarifaSocial != null && !codigoTarifaSocial.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroSubcategoria.adicionarParametro(new ParametroSimples(
                    FiltroSubCategoria.CODIGO_TARIFA_SOCIAL, codigoTarifaSocial));
        }
		//Codigo Grupo SubCategoria
		if (codigoGrupoSubcategoria != null && !codigoGrupoSubcategoria.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroSubcategoria.adicionarParametro(new ParametroSimples(
                    FiltroSubCategoria.CODIGO_GRUPO_SUBCATEGORIA, codigoGrupoSubcategoria));
        }
		//Numero Fator Fiscalizacao
		if (numeroFatorFiscalizacao != null && !numeroFatorFiscalizacao.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroSubcategoria.adicionarParametro(new ParametroSimples(
                    FiltroSubCategoria.NUMERO_FATOR_FISCALIZACAO, numeroFatorFiscalizacao));
        }
		//Numero Fator Fiscalizacao
		if (indicadorTarifaConsumo != null && !indicadorTarifaConsumo.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroSubcategoria.adicionarParametro(new ParametroSimples(
                    FiltroSubCategoria.INDICADOR_TARIFA_CONSUMO, indicadorTarifaConsumo));
        }
		//Indicador Sazonalidade
		if (indicadorSazonalidade != null && !indicadorSazonalidade.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroSubcategoria.adicionarParametro(new ParametroSimples(
                    FiltroSubCategoria.INDICADOR_SAZONALIDADE, indicadorSazonalidade));
        }
		//Indicador de Uso
		if (indicadorUso != null && !indicadorUso.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroSubcategoria.adicionarParametro(new ParametroSimples(
                    FiltroBairro.INDICADOR_USO, indicadorUso));
        }
		
		Collection colecaoSubcategoria = (Collection) fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName());
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		} else {
			if (colecaoSubcategoria != null && !colecaoSubcategoria.isEmpty())
			{
		        if (atualizar != null && colecaoSubcategoria.size() == 1)
		        {
		        	Subcategoria subcategoria = (Subcategoria) colecaoSubcategoria.iterator().next();
		        	httpServletRequest.setAttribute("idRegistroAtualizacao",
							subcategoria.getId());
		        	
		        	retorno = actionMapping
		            	.findForward("exibirAtualizarSubcategoria");
		        	
		        } else {
		        	retorno = actionMapping
						.findForward("retornarFiltroSubcategoria");
		        }
	        } 
			else 
			{
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
			
			httpServletRequest.setAttribute("filtroSubcategoria",
					filtroSubcategoria);
		}
		sessao.setAttribute("filtrar_manter", "filtrar_manter");
		return retorno;
	}
}