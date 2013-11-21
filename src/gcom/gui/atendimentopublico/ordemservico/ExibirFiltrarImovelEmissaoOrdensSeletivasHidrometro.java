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
* Ivan Sérgio Virginio da Silva Júnior
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

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarImovelEmissaoOrdensSeletivasHidrometro extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarImovelEmissaoOrdensSeletivasHidrometro");
		
		//obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		ImovelEmissaoOrdensSeletivasActionForm imovelEmissaoOrdensSeletivas = 
			(ImovelEmissaoOrdensSeletivasActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		 
		Collection<HidrometroCapacidade> collectionHidrometroCapacidade = null;
		Collection<HidrometroMarca> collectionHidrometroMarca = null;
		Collection<LeituraAnormalidade> collectionHidrometroAnormalidade = null;
		Collection<HidrometroLocalInstalacao> collectionHidrometroLocalInstalacao = null;
		
		if(imovelEmissaoOrdensSeletivas.getIdImovel() != null
				&& !imovelEmissaoOrdensSeletivas.getIdImovel().equals("")){
			
			httpServletRequest.setAttribute("desabilitarCampos", "ok");
		}else{
			if (httpServletRequest.getAttribute("desabilitarCampos") != null){
				httpServletRequest.removeAttribute("desabilitarCampos");
			}
		}
		
		if (imovelEmissaoOrdensSeletivas.getTipoOrdem().
				equalsIgnoreCase(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_INSTALACAO)) {
			
			FiltrarImovelEmissaoOrdensSeletivasWizardAction filtrar = new FiltrarImovelEmissaoOrdensSeletivasWizardAction();
			
			if (sessao.getAttribute("abaAtual") == "PARAMETROS") {
				retorno = actionMapping.findForward("filtrarImovelEmissaoOrdensSeletivasCaracteristicas");
				filtrar.exibirFiltrarImovelEmissaoOrdensSeletivasCaracteristicas(actionMapping, actionForm,
						httpServletRequest, httpServletResponse);
			}else if (sessao.getAttribute("abaAtual") == "CARACTERISTICAS") {
				retorno = actionMapping.findForward("filtrarImovelEmissaoOrdensSeletivasParametros");
				filtrar.exibirFiltrarImovelEmissaoOrdensSeletivasParametros(actionMapping, actionForm,
						httpServletRequest, httpServletResponse);
			}
			
		}else {
		
			// Lista Capacidade
			if (imovelEmissaoOrdensSeletivas.getCapacidade() == null) {
				FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
				filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
						FiltroHidrometroCapacidade.INDICADOR_USO, 1));
				filtroHidrometroCapacidade.setCampoOrderBy(FiltroHidrometroCapacidade.DESCRICAO);
				collectionHidrometroCapacidade = fachada.pesquisar(filtroHidrometroCapacidade,
						HidrometroCapacidade.class.getName());
				
				if(collectionHidrometroCapacidade == null || collectionHidrometroCapacidade.isEmpty()) {
					throw new ActionServletException("atencao.naocadastrado", null, "Capacidade Hidrômetro");			
				}
				
				sessao.setAttribute("collectionHidrometroCapacidade", collectionHidrometroCapacidade);
			}
			
			// Lista Marca
			if (imovelEmissaoOrdensSeletivas.getMarca() == null) {
				FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();
				filtroHidrometroMarca.adicionarParametro(new ParametroSimples(
						FiltroHidrometroMarca.INDICADOR_USO, 1));
				filtroHidrometroMarca.setCampoOrderBy(FiltroHidrometroMarca.DESCRICAO);
				
				collectionHidrometroMarca = fachada.pesquisar(filtroHidrometroMarca,
						HidrometroMarca.class.getName());
				
				if(collectionHidrometroMarca == null || collectionHidrometroMarca.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null, "Marca do Hidrômetro");			
				}
				
				sessao.setAttribute("collectionHidrometroMarca", collectionHidrometroMarca);
			}
			
			//Lista Local de Instalacao
			if(imovelEmissaoOrdensSeletivas.getLocalInstalacao() == null){
				
				FiltroHidrometroLocalInstalacao filtroHidrometroLocalInstalacao = 
					new FiltroHidrometroLocalInstalacao();
				// Retirado por Romulo Aurelio, Analista: Ana Cristina
				// CRC 
				// Data: 30/08/2010  
				/*filtroHidrometroLocalInstalacao.adicionarParametro( new ParametroSimples(
						FiltroHidrometroLocalInstalacao.INDICADOR_USO, 1));*/
				filtroHidrometroLocalInstalacao.setCampoOrderBy(FiltroHidrometroLocalInstalacao.DESCRICAO);
				
				collectionHidrometroLocalInstalacao = fachada.pesquisar(filtroHidrometroLocalInstalacao,
						HidrometroLocalInstalacao.class.getName());
				
				if(collectionHidrometroLocalInstalacao == null || collectionHidrometroLocalInstalacao.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null, "Local Instalação Hidrômetro");
				}
				
				sessao.setAttribute("collectionHidrometroLocalInstalacao", collectionHidrometroLocalInstalacao);
			}
			
			// Lista Hidrometro Anormalidade
			if (imovelEmissaoOrdensSeletivas.getAnormalidadeHidrometro() == null) {
				FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
				
				if(imovelEmissaoOrdensSeletivas.getTipoOrdem() != null && 
				(imovelEmissaoOrdensSeletivas.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_SUBSTITUICAO) ||						
				imovelEmissaoOrdensSeletivas.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_REMOCAO))){
					//Caso o tipo de ordem selecionado corresponda a ‘SUBSTITUIÇÃO HIDROMETRO’ 
					//ou ‘REMOÇÃO HIDROMETRO’, selecionar apenas  anormalidades com LTAN_ICRELATIVOHIDROMETRO=1 
					filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
							FiltroLeituraAnormalidade.INDICADOR_RELATIVO_HIDROMETRO, 1));
				}
				filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
						FiltroLeituraAnormalidade.INDICADOR_USO, 1));
				
				filtroLeituraAnormalidade.setCampoOrderBy(FiltroLeituraAnormalidade.DESCRICAO);
				
				collectionHidrometroAnormalidade = fachada.pesquisar(filtroLeituraAnormalidade,
						LeituraAnormalidade.class.getName());
				
				if(collectionHidrometroAnormalidade == null || collectionHidrometroAnormalidade.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null, "Anormalidade de Hidrômetro");			
				}
				
				sessao.setAttribute("collectionHidrometroAnormalidade", collectionHidrometroAnormalidade);
			}
		}
		
		// Usado para fazer o controle de navegacao por conta da Aba Local 
		//sessao.setAttribute("abaAtual", "HIDROMETRO");
		
		if (imovelEmissaoOrdensSeletivas.getTipoOrdem() != null) {
			httpServletRequest.setAttribute("tipoOrdem", imovelEmissaoOrdensSeletivas.getTipoOrdem());
		}
		
		return retorno;
	}
}