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

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroQuadraFace;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;


/**
 * Classe responável pelo recebimento dos dados que formarão a inscrição do imóvel
 *
 * @author Raphael Rossiter
 * @date 07/05/2009
 */
public class ExibirInserirImovelLocalidadeAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("inserirImovelLocalidade");

		// obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm inserirImovelLocalidadeActionForm = (DynaValidatorForm) sessao
		.getAttribute("InserirImovelActionForm");
		
		// cria as variaveis
		String idLocalidade = null;
		String codigoSetorComercial = null;
		String numeroQuadra = null;

		// atribui os valores q vem pelo request as variaveis
		idLocalidade = (String) inserirImovelLocalidadeActionForm.get("idLocalidade");
		codigoSetorComercial = (String) inserirImovelLocalidadeActionForm.get("idSetorComercial");
		numeroQuadra = (String) inserirImovelLocalidadeActionForm.get("idQuadra");

		// instancia o filtro imovel
		FiltroQuadra filtroQuadra = new FiltroQuadra();
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		FiltroQuadraFace filtroQuadraFace = new FiltroQuadraFace();

		// cria a colecao para receber a pesquisa
		Collection localidades = new HashSet();
		Collection setorComerciais = new HashSet();
		Collection quadras = new HashSet();
		Collection facesQuadra = new HashSet();

		Fachada fachada = Fachada.getInstancia(); 

		sessao.removeAttribute("gis");
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		((DynaValidatorForm) actionForm).set("usaRota", sistemaParametro.getIndicadorUsaRota().toString()); 

		//PESQUISANDO LOCALIDADE...
		if (idLocalidade != null && !idLocalidade.toString().trim().equalsIgnoreCase("")) {
			
			filtroLocalidade.limparListaParametros();
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
			FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
			FiltroLocalidade.ID, new Integer(idLocalidade)));
			
			localidades = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (localidades != null && !localidades.isEmpty()) {
				
				//LOCALIDADE ENCONTRADA
				inserirImovelLocalidadeActionForm.set("idLocalidade", 
				Util.adicionarZerosEsquedaNumero(3, new Integer(((Localidade) ((List) localidades).get(0))
				.getId().toString()).toString()));
				
				inserirImovelLocalidadeActionForm.set("localidadeDescricao",
				((Localidade) ((List) localidades).get(0)).getDescricao());
				
				httpServletRequest.setAttribute("nomeCampo", "idSetorComercial");
			} 
			else {
				
				httpServletRequest.setAttribute("codigoLocalidadeNaoEncontrada", "true");
				
				inserirImovelLocalidadeActionForm.set("idLocalidade", "");
				inserirImovelLocalidadeActionForm.set("idSetorComercial", "");
				inserirImovelLocalidadeActionForm.set("setorComercialDescricao", "");
				inserirImovelLocalidadeActionForm.set("idQuadra", "");
				inserirImovelLocalidadeActionForm.set("idQuadraFace", "");
				
				httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
			}
		}

		
		//PESQUISANDO SETOR COMERCIAL...
		if (codigoSetorComercial != null && !codigoSetorComercial.toString().trim().equalsIgnoreCase("")) {
			
			if (idLocalidade != null && !idLocalidade.toString().trim().equalsIgnoreCase("")) {
				
				filtroSetorComercial.limparListaParametros();
				
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.ID_LOCALIDADE, new Integer(idLocalidade)));
				
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,new Integer(codigoSetorComercial)));
				
				setorComerciais = fachada.pesquisar(filtroSetorComercial,
				SetorComercial.class.getName());
				
				if (setorComerciais != null && !setorComerciais.isEmpty()) {
					
					// SETOR COMERCIAL ENCONTRADO
					inserirImovelLocalidadeActionForm.set("idSetorComercial",
					Util.adicionarZerosEsquedaNumero(3, new Integer(
					((SetorComercial) ((List) setorComerciais).get(0)).getCodigo()).toString()));

					inserirImovelLocalidadeActionForm.set("setorComercialDescricao",
					((SetorComercial) ((List) setorComerciais).get(0)).getDescricao());

					httpServletRequest.setAttribute("nomeCampo", "idQuadra");
				} 
				else {
					
					inserirImovelLocalidadeActionForm.set("idSetorComercial","");
					inserirImovelLocalidadeActionForm.set("idQuadra", "");
					
					httpServletRequest.setAttribute("codigoSetorComercialNaoEncontrada", "true");
					httpServletRequest.setAttribute("nomeCampo","idSetorComercial");
				}
			}

		} 
		else {
			
			inserirImovelLocalidadeActionForm.set("idSetorComercial", "");
			inserirImovelLocalidadeActionForm.set("setorComercialDescricao", "");
		}

		//PESQUISANDO QUADRA...
		if (numeroQuadra != null && !numeroQuadra.toString().trim().equalsIgnoreCase("")) {
			
			if (codigoSetorComercial != null && !codigoSetorComercial.toString().trim()
				.equalsIgnoreCase("")) {
				
				filtroQuadra.limparListaParametros();

				filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");

				filtroQuadra.adicionarParametro(new ParametroSimples(
				FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidade)));

				filtroQuadra.adicionarParametro(new ParametroSimples(
				FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				filtroQuadra.adicionarParametro(new ParametroSimples(
				FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(codigoSetorComercial)));
				
				filtroQuadra.adicionarParametro(new ParametroSimples(
				FiltroQuadra.NUMERO_QUADRA, new Integer(numeroQuadra)));
				
				quadras = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
				
				if (quadras != null && !quadras.isEmpty()) {
					
					// QUADRA ENCONTRADA
					Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(quadras);
					
					inserirImovelLocalidadeActionForm.set("idQuadra", ""
					+ Util.adicionarZerosEsquedaNumero(3, "" + quadra.getNumeroQuadra()));
					
					//ROTA QUE ESTÁ ASSOCIADA COM A QUADRA
					String msg = "Rota:" + quadra.getRota().getCodigo().toString();
					
					httpServletRequest.setAttribute("msgQuadra", msg);
					
					//CARREGANDO AS FACES ASSOCIADAS A QUADRA SELECIONADA
					if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.SIM)){
						
						filtroQuadraFace.limparListaParametros();

						filtroQuadraFace.adicionarParametro(new ParametroSimples(
						FiltroQuadraFace.ID_QUADRA, quadra.getId()));
						
						facesQuadra = fachada.pesquisar(filtroQuadraFace, QuadraFace.class.getName());
						
						if (facesQuadra != null && !facesQuadra.isEmpty()) {
							
							//FACE(S) DA QUADRA ENCONTRADA(S)
							httpServletRequest.setAttribute("facesQuadra", facesQuadra);
						}
						else{
							
							//FACE(S) DA QUADRA NÃO ENCONTRADA(S)
							httpServletRequest.setAttribute("msgQuadraFace","Face(s) da Quadra inexistente(s)");
						}
						
						httpServletRequest.setAttribute("nomeCampo", "idQuadraFace");
					}
					else{
						
						httpServletRequest.setAttribute("nomeCampo", "lote");
					}
				} 
				else {
					
					httpServletRequest.setAttribute("codigoQuadraNaoEncontrada", "true");
					httpServletRequest.setAttribute("msgQuadra","QUADRA INEXISTENTE");

					inserirImovelLocalidadeActionForm.set("idQuadra", "");
					inserirImovelLocalidadeActionForm.set("idQuadraFace", "");

					httpServletRequest.setAttribute("nomeCampo", "idQuadra");
				}
			}
		} 
		else {
			inserirImovelLocalidadeActionForm.set("idQuadra", "");
			inserirImovelLocalidadeActionForm.set("idQuadraFace", "");
		}
		
		//VERIFICAÇÃO PARA DEFINIR SE O CAMPO DE FACE DA QUADRA FICARÁ DISPONÍVEL
		if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.SIM)){
			httpServletRequest.setAttribute("exibirQuadraFace", "SIM");
		}

		return retorno;
	}

}
