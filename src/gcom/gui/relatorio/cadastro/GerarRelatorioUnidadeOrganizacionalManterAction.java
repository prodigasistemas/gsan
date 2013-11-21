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
package gcom.gui.relatorio.cadastro;

import gcom.atendimentopublico.registroatendimento.FiltroMeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.FiltroUnidadeTipo;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.cadastro.unidade.UnidadeOrganizacionalActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.RelatorioManterUnidadeOrganizacional;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class GerarRelatorioUnidadeOrganizacionalManterAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		// cria a variável de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		UnidadeOrganizacionalActionForm unidadeOrganizacionalActionForm = (UnidadeOrganizacionalActionForm) actionForm;

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = (FiltroUnidadeOrganizacional) sessao
				.getAttribute("filtroUnidadeOrganizacional");
		

		// Inicio da parte que vai mandar os parametros para o relatório

		UnidadeOrganizacional unidadeOrganizacionalParametros = new UnidadeOrganizacional();
		
		
		String id = null;
		
		if(unidadeOrganizacionalActionForm.getIdUnidade() != null 
			&& !unidadeOrganizacionalActionForm.getIdUnidade().trim().equals("")){
		
			id = unidadeOrganizacionalActionForm.getIdUnidade();
		
		}
		
		Short indicadorAberturaRa = 0;

		if (unidadeOrganizacionalActionForm.getUnidadeAbreRA() != null
				&& !unidadeOrganizacionalActionForm.getUnidadeAbreRA().equals("")) {

			indicadorAberturaRa = new Short(""
					+ unidadeOrganizacionalActionForm.getUnidadeAbreRA());
		}

		Short indicadorTramite = 0;
		
		if(unidadeOrganizacionalActionForm.getUnidadeTramitacao()!= null 
				&& !unidadeOrganizacionalActionForm.getUnidadeTramitacao().equals("")){
			
			indicadorTramite = new Short ("" + unidadeOrganizacionalActionForm.getUnidadeTramitacao());
		}
		
		Short indicadorEsgoto = 0;
		
		if(unidadeOrganizacionalActionForm.getUnidadeEsgoto() != null 
				&& !unidadeOrganizacionalActionForm.getUnidadeEsgoto().equals("")){
			
			indicadorEsgoto = new Short("" + unidadeOrganizacionalActionForm.getUnidadeEsgoto());
		}
		
		Short indicadorUso = 0;
		
		if(unidadeOrganizacionalActionForm.getIndicadorUso() != null 
				&& !unidadeOrganizacionalActionForm.getIndicadorUso().equals("")){
			
			indicadorUso = new Short("" + unidadeOrganizacionalActionForm.getIndicadorUso());
		}
		
		String sigla = null;
		
		if (unidadeOrganizacionalActionForm.getSigla() != null
				&& !unidadeOrganizacionalActionForm.getSigla().equals("")){
			
			sigla = unidadeOrganizacionalActionForm.getSigla();
		}
		
		
		// seta os parametros que serão mostrados no relatório
		unidadeOrganizacionalParametros.setId(id == null ? null : new Integer(id));
		
		UnidadeTipo unidadeTipo = new UnidadeTipo();
		
		//UnidadeTipo
		if (unidadeOrganizacionalActionForm.getIdTipoUnidade() != null && !unidadeOrganizacionalActionForm.getIdTipoUnidade().trim().equals("")) {
			FiltroUnidadeTipo filtroUnidadeTipo = new FiltroUnidadeTipo();
			filtroUnidadeTipo.adicionarParametro( new ParametroSimples( FiltroUnidadeTipo.ID, unidadeOrganizacionalActionForm.getIdTipoUnidade() ) );
			Collection colUniTip = Fachada.getInstancia().pesquisar( filtroUnidadeTipo, UnidadeTipo.class.getName() );
			
			UnidadeTipo unidadeTipoInformada = ( UnidadeTipo ) colUniTip.iterator().next();
			
			unidadeTipo = unidadeTipoInformada;
		}
		
		// Nível
		
		String pesquisouNivel = "nao";
		if (unidadeOrganizacionalActionForm.getNivelHierarquico() != null 
				&& !unidadeOrganizacionalActionForm.getNivelHierarquico().trim().equals("")) {
			
			pesquisouNivel = "sim";
			
			unidadeTipo.setNivel(new Short(unidadeOrganizacionalActionForm.getNivelHierarquico()));
		}
		
		//Unidade Centralizadora
		if(unidadeOrganizacionalActionForm.getIdUnidadeCentralizadora() != null && !unidadeOrganizacionalActionForm.getIdUnidadeCentralizadora().trim().equals("")){
			FiltroUnidadeOrganizacional filtroUnidadeCentralizadora = new FiltroUnidadeOrganizacional();
			filtroUnidadeCentralizadora.adicionarParametro( new ParametroSimples(FiltroUnidadeOrganizacional.ID, unidadeOrganizacionalActionForm.getIdUnidadeCentralizadora()));
			Collection colUniCen = Fachada.getInstancia().pesquisar( filtroUnidadeCentralizadora, UnidadeOrganizacional.class.getName() );
		
			UnidadeOrganizacional unidadeCentralizadora =  (UnidadeOrganizacional ) colUniCen.iterator().next();
			
			unidadeOrganizacionalParametros.setUnidadeCentralizadora( unidadeCentralizadora );
		}
		
		//Unidade Repavimentadora
		if(unidadeOrganizacionalActionForm.getIdUnidadeRepavimentadora() != null && !unidadeOrganizacionalActionForm.getIdUnidadeRepavimentadora().trim().equals("")){
			FiltroUnidadeOrganizacional filtroUnidadeRepavimentadora = new FiltroUnidadeOrganizacional();
			filtroUnidadeRepavimentadora.adicionarParametro( new ParametroSimples(FiltroUnidadeOrganizacional.ID, unidadeOrganizacionalActionForm.getIdUnidadeRepavimentadora()));
			Collection colUniRep = Fachada.getInstancia().pesquisar( filtroUnidadeRepavimentadora, UnidadeOrganizacional.class.getName() );
		
			UnidadeOrganizacional unidadeRepavimentadora =  ( UnidadeOrganizacional ) colUniRep.iterator().next();
			
			unidadeOrganizacionalParametros.setUnidadeRepavimentadora( unidadeRepavimentadora );
		}
		
	
		
		//Empresa
		if (unidadeOrganizacionalActionForm.getIdEmpresa() != null && !unidadeOrganizacionalActionForm.getIdEmpresa().trim().equals("")) {
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro( new ParametroSimples( FiltroEmpresa.ID, unidadeOrganizacionalActionForm.getIdEmpresa() ) );
			
			Collection colEmp = Fachada.getInstancia().pesquisar( filtroEmpresa, Empresa.class.getName() );
			
			Empresa empresa = ( Empresa ) colEmp.iterator().next();
			unidadeOrganizacionalParametros.setEmpresa( empresa );
		}
		
		//Meio da Solicitação padrão	
		if(unidadeOrganizacionalActionForm.getIdMeioSolicitacao() != null && !unidadeOrganizacionalActionForm.getIdMeioSolicitacao().trim().equals("")){
			FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao();
			filtroMeioSolicitacao.adicionarParametro( new ParametroSimples (FiltroMeioSolicitacao.ID, unidadeOrganizacionalActionForm.getIdMeioSolicitacao() ) );
		
			Collection colMeioSoli = Fachada.getInstancia().pesquisar( filtroMeioSolicitacao, MeioSolicitacao.class.getName());
			MeioSolicitacao meioSolicitacao = (MeioSolicitacao) colMeioSoli.iterator().next();
			unidadeOrganizacionalParametros.setMeioSolicitacao( meioSolicitacao );
		}
		
		//Localidade
		if(unidadeOrganizacionalActionForm.getIdLocalidade() != null && !unidadeOrganizacionalActionForm.getIdLocalidade().trim().equals("")){
			FiltroLocalidade filtroLocalidade= new FiltroLocalidade();
			filtroLocalidade.adicionarParametro( new ParametroSimples (FiltroLocalidade.ID, unidadeOrganizacionalActionForm.getIdLocalidade() ) );
		
			Collection colLoca = Fachada.getInstancia().pesquisar( filtroLocalidade, Localidade.class.getName());
			Localidade localidade = (Localidade) colLoca.iterator().next();
			unidadeOrganizacionalParametros.setLocalidade( localidade );
		}
			
		unidadeOrganizacionalParametros.setSigla(sigla);
		unidadeOrganizacionalParametros.setIndicadorEsgoto(indicadorEsgoto);
		unidadeOrganizacionalParametros.setIndicadorUso(indicadorUso);
		unidadeOrganizacionalParametros.setDescricao(unidadeOrganizacionalActionForm.getDescricao());
		unidadeOrganizacionalParametros.setIndicadorAberturaRa(indicadorAberturaRa);
		unidadeOrganizacionalParametros.setIndicadorTramite(indicadorTramite);
		unidadeOrganizacionalParametros.setUnidadeTipo(unidadeTipo);

		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterUnidadeOrganizacional relatorioManterUnidadeOrganizacional= new RelatorioManterUnidadeOrganizacional(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterUnidadeOrganizacional.addParametro("filtroUnidadeOrganizacional",
				filtroUnidadeOrganizacional);
		relatorioManterUnidadeOrganizacional.addParametro("unidadeOrganizacionalParametros",
				unidadeOrganizacionalParametros);
		
		relatorioManterUnidadeOrganizacional.addParametro("pesquisouNivel", pesquisouNivel);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterUnidadeOrganizacional.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterUnidadeOrganizacional,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}