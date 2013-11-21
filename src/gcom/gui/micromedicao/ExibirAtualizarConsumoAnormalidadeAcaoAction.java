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
package gcom.gui.micromedicao;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.ConsumoAnormalidadeAcao;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidadeAcao;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
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


public class ExibirAtualizarConsumoAnormalidadeAcaoAction extends GcomAction {
	/**
	 * [UC1058] Manter Consumo Anormalidade e Ação
	 * 
	 * 
	 * @author Rodrigo Cabral
	 * @date 05/10/2010
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarConsumoAnormalidadeAcao");				
		
		AtualizarConsumoAnormalidadeAcaoActionForm form = (AtualizarConsumoAnormalidadeAcaoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		String codigo = null;

		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null || 
				sessao.getAttribute("idRegistroAtualizacao") != null ) {
			
			if ( httpServletRequest.getParameter("idRegistroAtualizacao") != null ) {
				sessao.setAttribute("idRegistroAtualizacao", httpServletRequest.getParameter("idRegistroAtualizacao"));
				codigo = httpServletRequest.getParameter("idRegistroAtualizacao");	
			} else {
				
				codigo = (String) sessao.getAttribute("idRegistroAtualizacao");
			}
			
		} else {
			codigo = ""+((ConsumoAnormalidadeAcao)sessao.getAttribute("consumoAnormalidadeAcao")).getId();
		}
		
		if (httpServletRequest.getParameter("menu") != null) {
			sessao.setAttribute("menu", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("menu");
		}
		
		if (codigo == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				codigo = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				codigo = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}
		
		//coleção anormalidade consumo
		FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade();
		filtroConsumoAnormalidade.setCampoOrderBy(FiltroConsumoAnormalidade.ID);
		
		Collection colecaoConsumoAnormalidade = fachada.pesquisar(
				filtroConsumoAnormalidade,
				ConsumoAnormalidade.class.getName());
		sessao.setAttribute("colecaoConsumoAnormalidade",
				colecaoConsumoAnormalidade);
		
		//Coleção Categoria
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		filtroCategoria
				.setCampoOrderBy(FiltroCategoria.DESCRICAO);
		
		Collection colecaoCategoria = fachada.pesquisar(
				filtroCategoria,
				Categoria.class.getName());
		sessao.setAttribute("colecaoCategoria",
				colecaoCategoria);
		
		//Coleção Perfil do Imovel
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil
				.setCampoOrderBy(FiltroImovelPerfil.ID);
		
		Collection colecaoImovelPerfil = fachada.pesquisar(
				filtroImovelPerfil,
				ImovelPerfil.class.getName());
		sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
		
		//coleção anormalidade leitura consumo
	   
		FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumo = new FiltroLeituraAnormalidadeConsumo();
		filtroLeituraAnormalidadeConsumo
				.setCampoOrderBy(FiltroLeituraAnormalidadeConsumo.ID);
		
		Collection colecaoLeituraAnormalidadeConsumo = fachada.pesquisar(
				filtroLeituraAnormalidadeConsumo,
				LeituraAnormalidadeConsumo.class.getName());
		sessao.setAttribute("colecaoLeituraAnormalidadeConsumo", colecaoLeituraAnormalidadeConsumo);

		
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		//
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			(objetoConsulta.trim().equals("1")|| objetoConsulta.trim().equals("2")|| objetoConsulta.trim().equals("3"))  ) {

			// Faz a consulta de Localidade
			this.pesquisarServicoTipo(form,objetoConsulta, httpServletRequest);	
			
		}
		
		//coleção tipo de solicitação
		   
		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
		
		filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.INDICADOR_USO, "1"));
		
		filtroSolicitacaoTipo
				.setCampoOrderBy(FiltroSolicitacaoTipo.DESCRICAO);
		
		filtroSolicitacaoTipo.adicionarParametro(
    			new ParametroSimples(FiltroSolicitacaoTipo.INDICADOR_USO_SISTEMA,
    					ConstantesSistema.INDICADOR_USO_DESATIVO));
		filtroSolicitacaoTipo.adicionarParametro(
    			new ParametroSimples(FiltroSolicitacaoTipo.INDICADOR_USO,
    					ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoSolicitacaoTipo = fachada.pesquisar(
				filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());

		
		sessao.setAttribute("colecaoSolicitacaoTipo", colecaoSolicitacaoTipo);
		

		
		
		ConsumoAnormalidadeAcao consumoAnormalidadeAcao = null;
		
		if (codigo != null && !codigo.trim().equals("") && Integer.parseInt(codigo) > 0) {
		
			form.setConsumoAnormalidadeAcaoId(codigo);
			FiltroConsumoAnormalidadeAcao filtroConsumoAnormalidadeAcao = new FiltroConsumoAnormalidadeAcao();
			
			filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(FiltroConsumoAnormalidadeAcao.ID, codigo));
			
			Collection colecaoConsumoAnormalidadeAcao = fachada.pesquisar
				(filtroConsumoAnormalidadeAcao,ConsumoAnormalidadeAcao.class.getName());
			
			if(colecaoConsumoAnormalidadeAcao != null && !colecaoConsumoAnormalidadeAcao.isEmpty()){
				
				consumoAnormalidadeAcao = (ConsumoAnormalidadeAcao)Util.retonarObjetoDeColecao(colecaoConsumoAnormalidadeAcao);
				
				form.setConsumoAnormalidade(consumoAnormalidadeAcao.getConsumoAnormalidade().getId().toString());
				
				if (consumoAnormalidadeAcao.getCategoria() != null && !consumoAnormalidadeAcao.getCategoria().getId().equals("")){
				form.setCategoria(consumoAnormalidadeAcao.getCategoria().getId().toString());
				}
				
				if (consumoAnormalidadeAcao.getImovelPerfil() != null && !consumoAnormalidadeAcao.getImovelPerfil().getId().equals("")){
				form.setImovelPerfil(consumoAnormalidadeAcao.getImovelPerfil().getId().toString());
				}
				
				form.setLeituraAnormalidadeConsumoMes1(consumoAnormalidadeAcao.getLeituraAnormalidadeConsumoMes1().getId().toString());
				form.setLeituraAnormalidadeConsumoMes2(consumoAnormalidadeAcao.getLeituraAnormalidadeConsumoMes2().getId().toString());
				form.setLeituraAnormalidadeConsumoMes3(consumoAnormalidadeAcao.getLeituraAnormalidadeConsumoMes3().getId().toString());
				form.setNumerofatorConsumoMes1(consumoAnormalidadeAcao.getNumerofatorConsumoMes1().toString());
				form.setNumerofatorConsumoMes2(consumoAnormalidadeAcao.getNumerofatorConsumoMes2().toString());
				form.setNumerofatorConsumoMes3(consumoAnormalidadeAcao.getNumerofatorConsumoMes3().toString());
				form.setIndicadorGeracaoCartaMes1(consumoAnormalidadeAcao.getIndicadorGeracaoCartaMes1().toString());
				form.setIndicadorGeracaoCartaMes2(consumoAnormalidadeAcao.getIndicadorGeracaoCartaMes2().toString());
				form.setIndicadorGeracaoCartaMes3(consumoAnormalidadeAcao.getIndicadorGeracaoCartaMes3().toString());

				if (consumoAnormalidadeAcao.getServicoTipoMes1() != null && !consumoAnormalidadeAcao.getServicoTipoMes1().getId().equals("")
						&& httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim") ) {
					
					form.setIdServicoTipoMes1(consumoAnormalidadeAcao.getServicoTipoMes1().getId().toString());
					FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
					filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, form.getIdServicoTipoMes1()));
					Collection colecaoServicoTipo = fachada.pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
					ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
					form.setDesServicoTipoMes1(servicoTipo.getDescricao());
				}
				
				if (consumoAnormalidadeAcao.getServicoTipoMes2() != null && !consumoAnormalidadeAcao.getServicoTipoMes2().getId().equals("")
						&& httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")){
					
					form.setIdServicoTipoMes2(consumoAnormalidadeAcao.getServicoTipoMes2().getId().toString());
					FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
					filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, form.getIdServicoTipoMes2()));
					Collection colecaoServicoTipo = fachada.pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
					ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
					form.setDesServicoTipoMes2(servicoTipo.getDescricao());
				
				}
				
				
				if (consumoAnormalidadeAcao.getServicoTipoMes3() != null && !consumoAnormalidadeAcao.getServicoTipoMes3().getId().equals("")
						&& httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")){
					
					form.setIdServicoTipoMes3(consumoAnormalidadeAcao.getServicoTipoMes3().getId().toString());
					FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
					filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, form.getIdServicoTipoMes3()));
					Collection colecaoServicoTipo = fachada.pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
					ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
					form.setDesServicoTipoMes3(servicoTipo.getDescricao());
				}
				
				
				if (consumoAnormalidadeAcao.getServicoTipoMes1() != null && !consumoAnormalidadeAcao.getServicoTipoMes1().getId().equals("")
						&& httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")){
				
					
					FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacaoMes1 = new FiltroSolicitacaoTipoEspecificacao();
					filtroSolicitacaoTipoEspecificacaoMes1.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoTipoEspecificacao.ID, consumoAnormalidadeAcao.getSolicitacaoTipoEspecificacaoMes1().getId()));
					filtroSolicitacaoTipoEspecificacaoMes1.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO);
					Collection colecaoSolicitacaoTipoEspecificacao1 = fachada.pesquisar(
							filtroSolicitacaoTipoEspecificacaoMes1, SolicitacaoTipoEspecificacao.class.getName());
					
					SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao1 = new SolicitacaoTipoEspecificacao();
					solicitacaoTipoEspecificacao1 = (SolicitacaoTipoEspecificacao) 
								Util.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao1);
					SolicitacaoTipo solicitacaoTipo1 = solicitacaoTipoEspecificacao1.getSolicitacaoTipo();
					
					form.setSolicitacaoTipoMes1(solicitacaoTipo1.getId().toString());

					
				}
				
				if (consumoAnormalidadeAcao.getServicoTipoMes2() != null && !consumoAnormalidadeAcao.getServicoTipoMes2().getId().equals("")
						&& httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")){
					
					
					FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacaoMes2 = new FiltroSolicitacaoTipoEspecificacao();
					filtroSolicitacaoTipoEspecificacaoMes2.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoTipoEspecificacao.ID, consumoAnormalidadeAcao.getSolicitacaoTipoEspecificacaoMes2().getId()));
					filtroSolicitacaoTipoEspecificacaoMes2.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO);
					Collection colecaoSolicitacaoTipoEspecificacao2 = fachada.pesquisar(
							filtroSolicitacaoTipoEspecificacaoMes2, SolicitacaoTipoEspecificacao.class.getName());
					
					SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao2 = new SolicitacaoTipoEspecificacao();
					solicitacaoTipoEspecificacao2 = (SolicitacaoTipoEspecificacao) 
								Util.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao2);
					SolicitacaoTipo solicitacaoTipo2 = solicitacaoTipoEspecificacao2.getSolicitacaoTipo();
					
					form.setSolicitacaoTipoMes2(solicitacaoTipo2.getId().toString());
		
				}
				
				if (consumoAnormalidadeAcao.getServicoTipoMes3() != null && !consumoAnormalidadeAcao.getServicoTipoMes3().getId().equals("")
						&& httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")){
					
					FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacaoMes3 = new FiltroSolicitacaoTipoEspecificacao();
					filtroSolicitacaoTipoEspecificacaoMes3.adicionarParametro(new ParametroSimples(
							FiltroSolicitacaoTipoEspecificacao.ID, consumoAnormalidadeAcao.getSolicitacaoTipoEspecificacaoMes3().getId()));
					filtroSolicitacaoTipoEspecificacaoMes3.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO);
					Collection colecaoSolicitacaoTipoEspecificacao3 = fachada.pesquisar(
							filtroSolicitacaoTipoEspecificacaoMes3, SolicitacaoTipoEspecificacao.class.getName());
					
					SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao3 = new SolicitacaoTipoEspecificacao();
					solicitacaoTipoEspecificacao3 = (SolicitacaoTipoEspecificacao) 
								Util.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao3);
					SolicitacaoTipo solicitacaoTipo3 = solicitacaoTipoEspecificacao3.getSolicitacaoTipo();
					
					form.setSolicitacaoTipoMes3(solicitacaoTipo3.getId().toString());

				}
				
				
				if (consumoAnormalidadeAcao.getServicoTipoMes1() != null && !consumoAnormalidadeAcao.getServicoTipoMes1().getId().equals("")
						&& httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")){
					
					form.setSolicitacaoTipoEspecificacaoMes1(consumoAnormalidadeAcao.getSolicitacaoTipoEspecificacaoMes1().getId().toString());
					
				}
				
				if (consumoAnormalidadeAcao.getServicoTipoMes2() != null && !consumoAnormalidadeAcao.getServicoTipoMes2().getId().equals("")
						&& httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")){
					
					form.setSolicitacaoTipoEspecificacaoMes2(consumoAnormalidadeAcao.getSolicitacaoTipoEspecificacaoMes2().getId().toString());
				
				}
				
				if (consumoAnormalidadeAcao.getServicoTipoMes3() != null && !consumoAnormalidadeAcao.getServicoTipoMes3().getId().equals("")
						&& httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")){
					
					form.setSolicitacaoTipoEspecificacaoMes3(consumoAnormalidadeAcao.getSolicitacaoTipoEspecificacaoMes3().getId().toString());
					
				}
				
				form.setDescricaoContaMensagemMes1(consumoAnormalidadeAcao.getDescricaoContaMensagemMes1());
				form.setDescricaoContaMensagemMes2(consumoAnormalidadeAcao.getDescricaoContaMensagemMes2());
				form.setDescricaoContaMensagemMes3(consumoAnormalidadeAcao.getDescricaoContaMensagemMes3());
				form.setIndicadorUso(consumoAnormalidadeAcao.getIndicadorUso().toString());
				
				
				//coleção tipo de solicitação especificação para o 1º mês
				   
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao1 = new FiltroSolicitacaoTipoEspecificacao();
				
				filtroSolicitacaoTipoEspecificacao1
				.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
				
				filtroSolicitacaoTipoEspecificacao1.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, "1"));

				if (form.getSolicitacaoTipoMes1() != null && !form.getSolicitacaoTipoMes1().equals("-1")){
					filtroSolicitacaoTipoEspecificacao1.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO, form.getSolicitacaoTipoMes1()));	
				}		

				Collection colecaoSolicitacaoTipoEspecificacaoMes1 = fachada.pesquisar(
						filtroSolicitacaoTipoEspecificacao1, 
						SolicitacaoTipoEspecificacao.class.getName());
				sessao.setAttribute("colecaoSolicitacaoTipoEspecificacaoMes1", colecaoSolicitacaoTipoEspecificacaoMes1);

				//coleção tipo de solicitação especificação para o 2º mês
				   
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao2 = new FiltroSolicitacaoTipoEspecificacao();
				
				filtroSolicitacaoTipoEspecificacao2
				.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
				
				filtroSolicitacaoTipoEspecificacao2.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, "1"));

				if (form.getSolicitacaoTipoMes2() != null && !form.getSolicitacaoTipoMes2().equals("-1")){
				filtroSolicitacaoTipoEspecificacao2.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO, form.getSolicitacaoTipoMes2()));	
				}		

				Collection colecaoSolicitacaoTipoEspecificacaoMes2 = fachada.pesquisar(
						filtroSolicitacaoTipoEspecificacao2, 
						SolicitacaoTipoEspecificacao.class.getName());
				sessao.setAttribute("colecaoSolicitacaoTipoEspecificacaoMes2", colecaoSolicitacaoTipoEspecificacaoMes2);

				
				//coleção tipo de solicitação especificação para o 3º mês
				   
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao3 = new FiltroSolicitacaoTipoEspecificacao();
				
				filtroSolicitacaoTipoEspecificacao3
				.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
				
				filtroSolicitacaoTipoEspecificacao3.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, "1"));

				if (form.getSolicitacaoTipoMes3() != null && !form.getSolicitacaoTipoMes3().equals("-1")){
				filtroSolicitacaoTipoEspecificacao3.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO, form.getSolicitacaoTipoMes3()));	
				}		

				Collection colecaoSolicitacaoTipoEspecificacaoMes3 = fachada.pesquisar(
						filtroSolicitacaoTipoEspecificacao3, 
						SolicitacaoTipoEspecificacao.class.getName());
				sessao.setAttribute("colecaoSolicitacaoTipoEspecificacaoMes3", colecaoSolicitacaoTipoEspecificacaoMes3);
			}
			
			
			sessao.setAttribute("atualizarConsumoAnormalidadeAcao", consumoAnormalidadeAcao);

			if (sessao.getAttribute("colecaoConsumoAnormdalidadeAcao") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarConsumoAnormalidadeAcaoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarConsumoAnormalidadeAcaoAction.do");
			}
			
		}

		return retorno;
	
	}

	//Pesquisar Tipo de Serviço para o Mes 1,2 e 3

	private void pesquisarServicoTipo(AtualizarConsumoAnormalidadeAcaoActionForm form,
			String objetoConsulta, HttpServletRequest httpServletRequest) {

			Object local = null;
			
			if(objetoConsulta.trim().equals("1")){
				local = form.getIdServicoTipoMes1();
				
			}else if(objetoConsulta.trim().equals("2")){
				local = form.getIdServicoTipoMes2();
				
			}else if(objetoConsulta.trim().equals("3")){
				local = form.getIdServicoTipoMes3();
				
			}
			
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(
				new ParametroSimples(FiltroServicoTipo.ID,local));
			
			// Recupera Tipo de Serviço
			Collection colecaoServicoTipo = 
				this.getFachada().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
		
			if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {

				ServicoTipo servicoTipo = 
					(ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
				
				if(objetoConsulta.trim().equals("1")){
					form.setIdServicoTipoMes1(servicoTipo.getId().toString());
					form.setDesServicoTipoMes1(servicoTipo.getDescricao());
				
				}
				
				if(objetoConsulta.trim().equals("2")){
					form.setIdServicoTipoMes2(servicoTipo.getId().toString());
					form.setDesServicoTipoMes2(servicoTipo.getDescricao());
				}
				
				if(objetoConsulta.trim().equals("3")){
					form.setIdServicoTipoMes3(servicoTipo.getId().toString());
					form.setDesServicoTipoMes3(servicoTipo.getDescricao());
				}
				

			} else {
				if(objetoConsulta.trim().equals("1")){
					form.setIdServicoTipoMes1(null);
					form.setDesServicoTipoMes1("Tipo de serviço inexistente");
					httpServletRequest.setAttribute("servicoTipoInexistente","true");
		
				}if(objetoConsulta.trim().equals("2")){
					form.setIdServicoTipoMes2(null);
					form.setDesServicoTipoMes2("Tipo de serviço inexistente");
					httpServletRequest.setAttribute("servicoTipoInexistente","true");
					
				}if(objetoConsulta.trim().equals("3")){
					form.setIdServicoTipoMes3(null);
					form.setDesServicoTipoMes3("Tipo de serviço inexistente");
					httpServletRequest.setAttribute("servicoTipoInexistente","true");
					
				}
				
			
			}
		}
}
