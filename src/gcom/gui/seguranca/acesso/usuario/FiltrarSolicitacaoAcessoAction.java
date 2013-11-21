/**
 * 
 */
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
package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.SolicitacaoAcesso;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Hugo Leonardo	
 * @date 16/11/2010
 */
public class FiltrarSolicitacaoAcessoAction extends GcomAction {

	/**
	 * [UC1096] Filtrar Solicitação de Acesso.
	 * 
	 * Este caso de uso cria um filtro que será usado na pesquisa de Solicitacao de Acesso.
	 * 
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterSolicitacaoAcessoAction");

		FiltrarSolicitacaoAcessoActionForm form = (FiltrarSolicitacaoAcessoActionForm) actionForm;

		FiltroSolicitacaoAcesso filtroSolicitacaoAcesso = new FiltroSolicitacaoAcesso();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		String objeto = (String) sessao.getAttribute("objeto");

		boolean peloMenosUmParametroInformado = false;
		
		String idFuncionarioSolicitante = form.getIdFuncionarioSolicitante(); 
		String idFuncionarioResponsavel = form.getIdFuncionarioSuperior(); 
		String idFuncionario = form.getIdFuncionario(); 
		String idEmpresa = form.getIdEmpresa(); 
		String idLotacao = form.getIdLotacao();  
		String nomeUsuario = form.getNomeUsuario(); 
		String[] situacoes = form.getIdsSituacao(); 
	
		// verificar Situação
		if (situacoes != null && situacoes.length >= 0 
				&& !situacoes[0].equals("-1")){ 
			 
			List lista = Arrays.asList(form.getIdsSituacao());  
			
			filtroSolicitacaoAcesso.adicionarParametro(new ParametroSimplesIn(
					FiltroSolicitacaoAcesso.SOLICITACAO_ACESSO_SITUACAO_ID, lista));
			
			peloMenosUmParametroInformado = true;
		}else{
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Situação");
		}
		
		// Verifica se o campo Funcionario Solicitante foi informado
		if (Util.verificarNaoVazio(idFuncionarioSolicitante)) {
			
			filtroSolicitacaoAcesso.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoAcesso.FUNCIONARIO_SOLICITANTE_ID, idFuncionarioSolicitante));
			
			peloMenosUmParametroInformado = true;
		}
		
		// Verifica se o campo Funcionario Responsável foi informado
		if ((objeto.equals("autorizar") || objeto.equals("relatorio")) 
				&& situacoes[0].equals("1") && Util.verificarNaoVazio(idFuncionarioResponsavel)) {
			
			filtroSolicitacaoAcesso.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoAcesso.FUNCIONARIO_RESPONSAVEL_ID, idFuncionarioResponsavel));
			
			peloMenosUmParametroInformado = true;
		}
		
		// Verifica se o campo Funcionario foi informado
		if (Util.verificarNaoVazio(idFuncionario)) {
			
			filtroSolicitacaoAcesso.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoAcesso.FUNCIONARIO_ID, idFuncionario));
			
			peloMenosUmParametroInformado = true;
		}

		// Verifica se o campo Empresa foi informado
		if (idEmpresa != null && !idEmpresa.equals("-1")) {
			
			filtroSolicitacaoAcesso.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoAcesso.EMPRESA_ID, idEmpresa));
			
			peloMenosUmParametroInformado = true;
		} 
		
		// Verifica se o campo Lotação foi informado
		if (Util.verificarNaoVazio(idLotacao)) {
			
			filtroSolicitacaoAcesso.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoAcesso.UNIDADE_ORGANIZACIONAL_ID, idLotacao));
			
			peloMenosUmParametroInformado = true;
		}

		// Verifica se o campo Nome Usuário foi informado
		if (Util.verificarNaoVazio(nomeUsuario)) {
			
			filtroSolicitacaoAcesso.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoAcesso.USUARIO_NOME, nomeUsuario));
			
			peloMenosUmParametroInformado = true;
		}
		
		// Verifica se o campo Período de Solicitação foi informado
		if (form.getDataInicial() != null && !form.getDataInicial().equals("")
				&& form.getDataFinal() != null && !form.getDataFinal().equals("")){
			
			if (!Util.validarDiaMesAno(form.getDataInicial())) {
				if (!Util.validarDiaMesAno(form.getDataFinal())) {
					if(Util.compararData(Util.converteStringParaDate(form.getDataInicial()), Util.converteStringParaDate(form.getDataFinal())) == 1){
						throw new ActionServletException("atencao.atencao.data_vigencia_final_menor");
					}else{
						
						filtroSolicitacaoAcesso.adicionarParametro(new Intervalo(
							FiltroSolicitacaoAcesso.DATA_SOLICITACAO, 
								Util.converteStringParaDate(form.getDataInicial()), 
								Util.converteStringParaDate(form.getDataFinal())));
					}
				}else{
					throw new ActionServletException("atencao.atencao.data_vigencia_final_invalida");
				}			
			}else{
				throw new ActionServletException("atencao.data_vigencia_inicial_invalida");
			}
			
			peloMenosUmParametroInformado = true;
		}
		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.SOLICITACAO_ACESSO_SITUACAO);
		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.FUNCIONARIO_RESPONSAVEL);
		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.FUNCIONARIO_SOLICITANTE);
		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.UNIDADE_ORGANIZACIONAL);
		
		filtroSolicitacaoAcesso.adicionarCaminhoParaCarregamentoEntidade(
				FiltroSolicitacaoAcesso.EMPRESA);

		filtroSolicitacaoAcesso.setCampoOrderBy(FiltroSolicitacaoAcesso.DATA_SOLICITACAO);
		filtroSolicitacaoAcesso.setCampoOrderBy(FiltroSolicitacaoAcesso.USUARIO_NOME);

		Collection <SolicitacaoAcesso> colecaoSolicitacaoAcesso = 
			fachada.pesquisar(filtroSolicitacaoAcesso, SolicitacaoAcesso.class.getName());		
		
		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pelo um request uma variável para o
		// ExibirManterSolicitacaoAcessoAction para nele verificar se irá
		// para o atualizar ou para o manter
		if (form.getAtualizar() != null && form.getAtualizar().equalsIgnoreCase("1")) {
			
			httpServletRequest.setAttribute("atualizar", form.getAtualizar());
		}

		// Pesquisa sem registros
		if (colecaoSolicitacaoAcesso == null || colecaoSolicitacaoAcesso.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Solicitação de Acesso");
		} else {
			SolicitacaoAcesso solicitacaoAcesso = new SolicitacaoAcesso();
			solicitacaoAcesso = (SolicitacaoAcesso) Util.retonarObjetoDeColecao(colecaoSolicitacaoAcesso);
			String idRegistroAtualizar = solicitacaoAcesso.getId().toString();

			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
			httpServletRequest.setAttribute("colecaoSolicitacaoAcesso", colecaoSolicitacaoAcesso);
		
		}
		
		sessao.setAttribute("filtroSolicitacaoAcesso", filtroSolicitacaoAcesso);
		httpServletRequest.setAttribute("filtroSolicitacaoAcesso", filtroSolicitacaoAcesso);
		
		if (objeto != null && objeto.equals("relatorio")){
			sessao.setAttribute("filtroForm", form);
		}
		
		httpServletRequest.setAttribute("situacoes", situacoes[0]);
		
		return retorno;
		
	}
	
}
