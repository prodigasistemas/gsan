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

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
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
 * [UC0766] Gerar Relatorio Boletim Ordens Servico Concluidas
 * 
 * @author Ivan Sérgio
 * @date 06/05/2008
 * 
 */
public class ExibirFiltrarRelatorioBoletimOrdensServicoConcluidasAction extends GcomAction {
	private String localidadeID = null;
	private String codigoSetorComercial = null;
	private Collection colecaoPesquisa = null;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirFiltrarRelatorioBoletimOrdensServicoConcluidasAction");
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Form		
		GerarRelatorioBoletimOrdensServicoConcluidasActionForm form = 
			(GerarRelatorioBoletimOrdensServicoConcluidasActionForm) actionForm;
		
		// Limpa os Campos
		if (httpServletRequest.getParameter("menu") != null) {
			form.setIdFirma("");
			form.setNomeFirma("");
			form.setIdLocalidade("");
			form.setNomeLocalidade("");
			form.setAnoMesReferenciaEncerramento("");
			form.setSituacao("1");
		}
		
		// Pesquisa Firma
		if(sessao.getAttribute("colecaoFirma") == null){
			pesquisarFirma(fachada, sessao, httpServletRequest);
		}
		
		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {
			switch (Integer.parseInt(objetoConsulta)) {
				// Localidade
				case 1:
					this.pesquisarLocalidade(form, fachada, httpServletRequest);
					break;
				// Setor Comercial
				case 2:
					pesquisarSetorComercial(form, fachada, httpServletRequest);
					break;
			}
		}
		
		return retorno;
	}
	
	/**
	 * Pesquisa as Firmas
	 * 
	 * @param gerarRelatorioAcompanhamento
	 * @param fachada
	 * @param sessao
	 * @param httpServletRequest
	 */
	private void pesquisarFirma(
			Fachada fachada,
			HttpSession sessao,
			HttpServletRequest httpServletRequest) {
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(
				FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		
		Collection<Empresa> colecaoFirma = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		
		// [FS0001 - Verificar Existencia de dados]
		if ( (colecaoFirma == null) || (colecaoFirma.size() == 0) ) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null, Empresa.class.getName());
		}else {
			sessao.setAttribute("colecaoFirma", colecaoFirma);
		}
	}
	
	/**
	 * Pesquisa a Localidade
	 * 
	 * @param gerarRelatorioAcompanhamento
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarLocalidade(
			GerarRelatorioBoletimOrdensServicoConcluidasActionForm form,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		// Recebe o valor do campo localidadeOrigemID do formulário.
		localidadeID = (String) form.getIdLocalidade();
		
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, localidadeID));
		
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		// Retorna localidade
		colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		
		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
			// Localidade nao encontrada
			// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
			// formulário
			form.setIdLocalidade("");
			form.setNomeLocalidade("Localidade inexistente");
			httpServletRequest.setAttribute("corLocalidadeOrigem", "exception");
			httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
			
		} else {
			Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
			form.setIdLocalidade(String.valueOf(objetoLocalidade.getId()));
			form.setNomeLocalidade(objetoLocalidade.getDescricao());
			httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercial");
		}
	}
	
	/**
	 * Pesquisa o Setor Comercial
	 * 
	 * @param gerarRelatorioAcompanhamento
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarSetorComercial(
			GerarRelatorioBoletimOrdensServicoConcluidasActionForm form,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial(); 

		// Recebe o valor do campo localidadeOrigemID do formulário.
		localidadeID = (String) form.getIdLocalidade();
		codigoSetorComercial = (String) form.getCodigoSetorComercial();
		
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.ID_LOCALIDADE, localidadeID));
		
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
		
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		// Retorna localidade
		colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
			// Setor nao encontrado
			// formulário
			form.setIdSetorComercial("");
			form.setCodigoSetorComercial("");
			form.setNomeSetorComercial("Setor Comercial inexistente");
			httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
			httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercial");
			
		} else {
			SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
			form.setIdSetorComercial(String.valueOf(objetoSetorComercial.getId()));
			form.setCodigoSetorComercial(String.valueOf(objetoSetorComercial.getCodigo()));
			form.setNomeSetorComercial(objetoSetorComercial.getDescricao());
			httpServletRequest.setAttribute("nomeCampo", "anoMesReferenciaEncerramento");
		}
	}
}