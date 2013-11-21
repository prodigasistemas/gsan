/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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


package gcom.gui.relatorio.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.micromedicao.FiltroContratoEmpresaServico;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1054] - Gerar Relatório Boletim de Medição
 * 
 * @author Hugo Leonardo
 *
 * @date 04/08/2010
 */

public class ExibirGerarRelatorioBoletimMedicaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirGerarRelatorioBoletimMedicao");
		
		GerarRelatorioBoletimMedicaoForm form = 
			(GerarRelatorioBoletimMedicaoForm) actionForm;
		
		if (form.getFormaGeracao() == null
				|| form.getFormaGeracao().equals("")) {
			form.setFormaGeracao("3");
		}
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		
		// Pesquisar Localidade
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			(objetoConsulta.trim().equals("1") || objetoConsulta.trim().equals("3"))  ) {

			// Faz a consulta de Localidade
			this.pesquisarLocalidade(form, objetoConsulta);
		}
		
		this.pesquisarEmpresa(httpServletRequest);
		
		if(form.getEmpresa() != null && !form.getEmpresa().equals("")){
			this.pesquisarContrato(httpServletRequest, form);
		}
		
		this.pesquisarGerenciaRegional(httpServletRequest);
		
		// Seta os request´s encontrados
		this.setaRequest(httpServletRequest,form);

		return retorno;
	}

	private void setaRequest(HttpServletRequest httpServletRequest,
			GerarRelatorioBoletimMedicaoForm form){
		
		// Localidade Inicial
		if(form.getLocalidadeInicial() != null && 
			!form.getLocalidadeInicial().equals("") && 
			form.getNomeLocalidadeInicial() != null && 
			!form.getNomeLocalidadeInicial().equals("")){
					
			httpServletRequest.setAttribute("localidadeInicialEncontrada","true");
			httpServletRequest.setAttribute("localidadeFinalEncontrada","true");
		}else{

			if(form.getLocalidadeFinal() != null && 
				!form.getLocalidadeFinal().equals("") && 
				form.getNomeLocalidadeFinal() != null && 
				!form.getNomeLocalidadeFinal().equals("")){
								
				httpServletRequest.setAttribute("localidadeFinalEncontrada","true");
			}
		}
	}
	
	private void pesquisarEmpresa(HttpServletRequest httpServletRequest){
		
		/*
		FiltroContratoEmpresaServico filtroContratoEmpresaServico = new FiltroContratoEmpresaServico();
		
		filtroContratoEmpresaServico.setConsultaSemLimites(true);
		
		filtroContratoEmpresaServico.adicionarParametro(
				new ParametroNulo(FiltroContratoEmpresaServico.DATA_FIM_CONTRATO, ParametroSimples.CONECTOR_OR));
		
		filtroContratoEmpresaServico.adicionarParametro(
				new MaiorQue(FiltroContratoEmpresaServico.DATA_FIM_CONTRATO, new Date()));
		
		filtroContratoEmpresaServico.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoEmpresaServico.EMPRESA);
		filtroContratoEmpresaServico.setCampoOrderBy(FiltroContratoEmpresaServico.DESCRICAO);		

		Collection colecaoEmpresa = 
			this.getFachada().pesquisar(filtroContratoEmpresaServico, ContratoEmpresaServico.class.getName());

		*/
		
		Collection<Empresa> colecaoEmpresa = this.getFachada().pesquisarEmpresasContratoServico();
		
		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Empresa");
		} else {
			httpServletRequest.setAttribute("colecaoEmpresa", colecaoEmpresa);
		}
	}
	
	private void pesquisarContrato(HttpServletRequest httpServletRequest,  GerarRelatorioBoletimMedicaoForm form){
		
		FiltroContratoEmpresaServico filtroContratoEmpresaServico = new FiltroContratoEmpresaServico();
		
		filtroContratoEmpresaServico.setConsultaSemLimites(true);
		
		filtroContratoEmpresaServico.adicionarParametro(
				new ParametroSimples(FiltroContratoEmpresaServico.EMPRESA, form.getEmpresa()));

		filtroContratoEmpresaServico.setCampoOrderBy(FiltroContratoEmpresaServico.DESCRICAO);		

		Collection colecaoContratoEmpresaServico = 
			this.getFachada().pesquisar(filtroContratoEmpresaServico, ContratoEmpresaServico.class.getName());

		if (colecaoContratoEmpresaServico == null || colecaoContratoEmpresaServico.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Contrato");
		} else {
			httpServletRequest.setAttribute("colecaoContrato", colecaoContratoEmpresaServico);
		}
	}
	
	private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest){
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(
				new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());

		if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Gerência Regional");
		} else {
			httpServletRequest.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
		}
	}
	
	private void pesquisarLocalidade( GerarRelatorioBoletimMedicaoForm form, String objetoConsulta) {

		Object local = form.getLocalidadeInicial();
			
		if(!objetoConsulta.trim().equals("1")){
			local = form.getLocalidadeFinal();
		}
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(
			new ParametroSimples(FiltroLocalidade.ID, local));
		
		// Recupera Localidade
		Collection colecaoLocalidade = 
			this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			Localidade localidade = 
				(Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			
			if(objetoConsulta.trim().equals("1")){
				form.setLocalidadeInicial(localidade.getId().toString());
				form.setNomeLocalidadeInicial(localidade.getDescricao());
			}
			
			form.setLocalidadeFinal(localidade.getId().toString());
			form.setNomeLocalidadeFinal(localidade.getDescricao());

			
		} else {
			if(objetoConsulta.trim().equals("1")){
				form.setLocalidadeInicial(null);
				form.setNomeLocalidadeInicial("Localidade Inicial inexistente");
				
				form.setLocalidadeFinal(null);
				form.setNomeLocalidadeFinal(null);
			}else{
				form.setLocalidadeFinal(null);
				form.setNomeLocalidadeFinal("Localidade Final inexistente");
			}
		}
	}
}
