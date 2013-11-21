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
package gcom.gui.cobranca;

import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarResolucaoDiretoriaAction extends GcomAction {

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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("manterResolucaoDiretoria");

		FiltrarResolucaoDiretoriaActionForm filtrarResolucaoDiretoriaActionForm = (FiltrarResolucaoDiretoriaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// Limpa todo o formulário para evitar "sujeiras" na tela
		String numero = "";
		String assunto = "";
		String dataInicio = "";
		String dataFim = "";
	    String indicadorParcelamentoUnico = "";
	    String indicadorUtilizacaoLivre = "";
	    String indicadorDescontoSancoes = "";
	    
	    String indicadorParcelasEmAtraso = "";
	    String idParcelasEmAtraso = "";
	    String indicadorParcelamentoEmAndamento = "";
	    String idParcelamentoEmAndamento = "";
	    String indicadorNegociacaoSoAVista = "";
	    String indicadorDescontoSoEmContaAVista = "";
	    String indicadorParcelamentoLojaVirtual = "";

		// Verifica se voltou do ManterResolucaoDiretoria e para isso, verifica
		// se a paginação é nula
		if (httpServletRequest.getParameter("page.offset") == null) {

			// Variaveis
			// Recupera os parâmetros do form, setando-os na sessão para poder
			// recuperá-los e colocá-los novamente na página caso o usuário
			// deseje voltar para o filtro
			numero = filtrarResolucaoDiretoriaActionForm.getNumero();
			sessao.setAttribute("numero", numero);
			
			assunto = filtrarResolucaoDiretoriaActionForm.getAssunto();
			sessao.setAttribute("assunto", assunto);
			
			dataInicio = filtrarResolucaoDiretoriaActionForm.getDataInicio();
			sessao.setAttribute("dataInicio", dataInicio);
			
			dataFim = filtrarResolucaoDiretoriaActionForm.getDataFim();
			sessao.setAttribute("dataFim", dataFim);
			
			indicadorParcelamentoUnico = filtrarResolucaoDiretoriaActionForm.getIndicadorParcelamentoUnico();
			sessao.setAttribute("indicadorParcelamentoUnico", indicadorParcelamentoUnico);
			
			indicadorUtilizacaoLivre = filtrarResolucaoDiretoriaActionForm.getIndicadorUtilizacaoLivre();
			sessao.setAttribute("indicadorUtilizacaoLivre", indicadorUtilizacaoLivre);
			
			indicadorDescontoSancoes = filtrarResolucaoDiretoriaActionForm.getIndicadorDescontoSancoes();
			sessao.setAttribute("indicadorDescontoSancoes", indicadorDescontoSancoes);
			
			indicadorParcelasEmAtraso = filtrarResolucaoDiretoriaActionForm.getIndicadorParcelasEmAtraso();
			sessao.setAttribute("indicadorParcelasEmAtraso", indicadorParcelasEmAtraso);
			
			idParcelasEmAtraso = filtrarResolucaoDiretoriaActionForm.getIdParcelasEmAtraso();
			sessao.setAttribute("idParcelasEmAtraso", idParcelasEmAtraso);
			
			indicadorParcelamentoEmAndamento = filtrarResolucaoDiretoriaActionForm.getIndicadorParcelamentoEmAndamento();
			sessao.setAttribute("indicadorParcelamentoEmAndamento", indicadorParcelamentoEmAndamento);
			
			idParcelamentoEmAndamento = filtrarResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento();
			sessao.setAttribute("idParcelamentoEmAndamento", idParcelamentoEmAndamento);
		    
			indicadorNegociacaoSoAVista = filtrarResolucaoDiretoriaActionForm.getIndicadorNegociacaoSoAVista();
			sessao.setAttribute("indicadorNegociacaoSoAVista", indicadorNegociacaoSoAVista);
			
			indicadorDescontoSoEmContaAVista = filtrarResolucaoDiretoriaActionForm.getIndicadorDescontoSoEmContaAVista();
			sessao.setAttribute("indicadorDescontoSoEmContaAVista",indicadorDescontoSoEmContaAVista);

			indicadorParcelamentoLojaVirtual = filtrarResolucaoDiretoriaActionForm.getIndicadorParcelamentoLojaVirtual();
			sessao.setAttribute("indicadorParcelamentoLojaVirtual",indicadorParcelamentoLojaVirtual);
		} else {
			// Recupera as variáveis digitadas anteriormente para colocá-los
			// novamente no filtro, pois devido ao esquema de
			// paginação, cada vez que o usuário seleciona a visualização das
			// anteriores ou próximas resoluções de diretoria é efetuado um novo
			// filtro
			numero = (String) sessao.getAttribute("numero");
			assunto = (String) sessao.getAttribute("assunto");
			dataInicio = (String) sessao.getAttribute("dataInicio");
			dataFim = (String) sessao.getAttribute("dataFim");
			indicadorParcelamentoUnico = (String) sessao.getAttribute("indicadorParcelamentoUnico");
			indicadorUtilizacaoLivre = (String) sessao.getAttribute("indicadorUtilizacaoLivre");
			indicadorDescontoSancoes = (String) sessao.getAttribute("indicadorDescontoSancoes");
			
			indicadorParcelasEmAtraso = (String)sessao.getAttribute("indicadorParcelasEmAtraso");
			if (sessao.getAttribute("idParcelasEmAtraso") != null){
				idParcelasEmAtraso = (String)sessao.getAttribute("idParcelasEmAtraso");
			}
			indicadorParcelamentoEmAndamento = (String)sessao.getAttribute("indicadorParcelamentoEmAndamento");
			
			if(sessao.getAttribute("idParcelamentoEmAndamento")!= null){
				idParcelamentoEmAndamento = (String)sessao.getAttribute("idParcelamentoEmAndamento");
			}
			indicadorNegociacaoSoAVista = (String)sessao.getAttribute("indicadorNegociacaoSoAVista");
			indicadorDescontoSoEmContaAVista = (String)sessao.getAttribute("indicadorDescontoSoEmContaAVista");
			indicadorParcelamentoLojaVirtual = (String)sessao.getAttribute("indicadorParcelamentoLojaVirtual");
		}

		// Cria o filtro
		FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();

		// Ordena a pesquisa por um parâmetro pré-definido
		filtroResolucaoDiretoria.setCampoOrderBy("numeroResolucaoDiretoria");

		if (dataInicio != null && !dataInicio.trim().equals("")
				&& dataFim != null && !dataFim.trim().equals("")) {
			if ((Util.converteStringParaDate(dataInicio)).compareTo(Util
					.converteStringParaDate(dataFim)) >= 0) {
				throw new ActionServletException(
						"atencao.termino_vigencia.anterior.inicio_vigencia");
			}
		}

		boolean peloMenosUmParametroInformado = false;

		// Neste ponto o filtro é criado com os parâmetros informados na página
		// de filtrar resolução de diretoria para ser executada a pesquisa no
		// ExibirManterResolucaoDiretoriaAction

		if (numero != null && !numero.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.NUMERO, numero));
		}

		if (assunto != null && !assunto.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroResolucaoDiretoria.adicionarParametro(new ComparacaoTexto(
					FiltroResolucaoDiretoria.DESCRICAO, assunto));
		}

		if (dataInicio != null && !dataInicio.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			Date dataInicioFormatada = Util.converteStringParaDate(dataInicio);

			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.DATA_VIGENCIA_INICIO,
					dataInicioFormatada));
		}

		if (dataFim != null && !dataFim.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			Date dataFimFormatada = Util.converteStringParaDate(dataFim);

			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.DATA_VIGENCIA_FIM,
					dataFimFormatada));
		}
		
		// Indicador Parcelamento Unico
		if ( !filtrarResolucaoDiretoriaActionForm.getIndicadorParcelamentoUnico().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroResolucaoDiretoria.adicionarParametro( new ParametroSimples( FiltroResolucaoDiretoria.INDICADOR_PARCELAMENTO_UNICO, filtrarResolucaoDiretoriaActionForm.getIndicadorParcelamentoUnico() ) );
			peloMenosUmParametroInformado = true;			
		}
		
		// Indicador de Utilizacao Livre
		if ( !filtrarResolucaoDiretoriaActionForm.getIndicadorUtilizacaoLivre().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroResolucaoDiretoria.adicionarParametro( new ParametroSimples( FiltroResolucaoDiretoria.INDICADOR_UTILIZACAO_LIVRE, filtrarResolucaoDiretoriaActionForm.getIndicadorUtilizacaoLivre() ) );
			peloMenosUmParametroInformado = true;			
		}
		
		// Indicador de Descontos e Sancoes
		if ( !filtrarResolucaoDiretoriaActionForm.getIndicadorDescontoSancoes().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroResolucaoDiretoria.adicionarParametro( new ParametroSimples( FiltroResolucaoDiretoria.INDICADOR_DESCONTOS_SANCOES, filtrarResolucaoDiretoriaActionForm.getIndicadorDescontoSancoes() ) );
			peloMenosUmParametroInformado = true;			
		}
		
		//Indicador de Parcelas em Atraso
		if ( !filtrarResolucaoDiretoriaActionForm.getIndicadorParcelasEmAtraso().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroResolucaoDiretoria.adicionarParametro( new ParametroSimples( FiltroResolucaoDiretoria.INDICADOR_PARCELAS_EM_ATRASO, filtrarResolucaoDiretoriaActionForm.getIndicadorParcelasEmAtraso() ) );
			peloMenosUmParametroInformado = true;	
			
			//Id da RD Parcelas em Atraso 
			if (filtrarResolucaoDiretoriaActionForm.getIdParcelasEmAtraso().equals( ConstantesSistema.SIM.toString() ) ){
				filtroResolucaoDiretoria.adicionarParametro( new ParametroSimples( FiltroResolucaoDiretoria.ID_RD_PARCELAS_EM_ATRASO, filtrarResolucaoDiretoriaActionForm.getIdParcelasEmAtraso() ) );
			}
		}
		
		//Indicador de Parcelamento em Andamento
		if ( !filtrarResolucaoDiretoriaActionForm.getIndicadorParcelamentoEmAndamento().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroResolucaoDiretoria.adicionarParametro( new ParametroSimples( FiltroResolucaoDiretoria.INDICADOR_PARCELAMENTO_EM_ANDAMENTO, filtrarResolucaoDiretoriaActionForm.getIndicadorParcelamentoEmAndamento() ) );
			peloMenosUmParametroInformado = true;	
			
			//Id da RD Parcelamento em Andamento
			if (filtrarResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento().equals( ConstantesSistema.SIM.toString() ) ){
				filtroResolucaoDiretoria.adicionarParametro( new ParametroSimples( FiltroResolucaoDiretoria.ID_RD_PARCELAMENTO_EM_ANDAMENTO, filtrarResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento() ) );
			}
		}
		
		//Indicador de Negociação só a vista
		if ( !filtrarResolucaoDiretoriaActionForm.getIndicadorNegociacaoSoAVista().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroResolucaoDiretoria.adicionarParametro( new ParametroSimples( FiltroResolucaoDiretoria.INDICADOR_NEGOCIACAO_SO_A_VISTA, filtrarResolucaoDiretoriaActionForm.getIndicadorNegociacaoSoAVista() ) );
			peloMenosUmParametroInformado = true;			
		}
		
		//
		if (!filtrarResolucaoDiretoriaActionForm.getIndicadorDescontoSoEmContaAVista().equals(ConstantesSistema.TODOS.toString())){
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.INDICADOR_DESCONTO_SO_EM_CONTA_A_VISTA,filtrarResolucaoDiretoriaActionForm.getIndicadorDescontoSoEmContaAVista()));
			peloMenosUmParametroInformado = true;
		}
		
		//Indicador Parcelamento Loja Virtual
		if (!filtrarResolucaoDiretoriaActionForm.getIndicadorParcelamentoLojaVirtual().equals(ConstantesSistema.TODOS.toString())){
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.INDICADOR_PARCELAMENTO_LOJA_VIRTUAL,filtrarResolucaoDiretoriaActionForm.getIndicadorParcelamentoLojaVirtual()));
			peloMenosUmParametroInformado = true;
		}
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pelo um request uma variável para o
		// ExibirManterResolucaoDiretoriaAction e nele verificar se irá para o
		// atualizar ou para o manter
		if (filtrarResolucaoDiretoriaActionForm.getAtualizar() != null
				&& filtrarResolucaoDiretoriaActionForm.getAtualizar()
						.equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar",
					filtrarResolucaoDiretoriaActionForm.getAtualizar());
		}

		// Manda o filtro pela sessao para o
		// ExibirManterResolucaoDiretoriaAction
		sessao.setAttribute("filtroResolucaoDiretoria",
				filtroResolucaoDiretoria);

		return retorno;
	}
}