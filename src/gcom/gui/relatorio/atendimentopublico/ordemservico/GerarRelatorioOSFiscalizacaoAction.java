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
package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOSReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioOSFiscalizacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
 * [SB0005] – Gerar Formulário em formato pdf
 * @author Vivianne Sousa
 * @date 02/06/2011
 */
public class GerarRelatorioOSFiscalizacaoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoOSFiscalizacaoGeradas = null;
		if (sessao.getAttribute("colecaoOSFiscalizacao") != null 
				&& !sessao.getAttribute("colecaoOSFiscalizacao").equals("")){

			colecaoOSFiscalizacaoGeradas = (Collection)sessao.getAttribute("colecaoOSFiscalizacao");
		}
		
		if(colecaoOSFiscalizacaoGeradas == null || colecaoOSFiscalizacaoGeradas.isEmpty()){
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		Integer idGrupoCobranca = null;
		if(httpServletRequest.getParameter("idGrupoCobranca") != null
				&& !httpServletRequest.getParameter("idGrupoCobranca").equals("null")
				&& !httpServletRequest.getParameter("idGrupoCobranca").equals("")){
			idGrupoCobranca = new Integer((String)httpServletRequest.getParameter("idGrupoCobranca"));
		}
		
		FiltroOSReferidaRetornoTipo filtroOSReferidaRetornoTipo = 
			new FiltroOSReferidaRetornoTipo(FiltroOSReferidaRetornoTipo.DESCRICAO);
		filtroOSReferidaRetornoTipo.adicionarParametro(new ParametroSimples(
			FiltroOSReferidaRetornoTipo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroOSReferidaRetornoTipo.adicionarParametro(new ParametroSimples(
			FiltroOSReferidaRetornoTipo.ID_SERVICO_TIPO_REFERENCIA,new Integer(2)));

		Collection colecaoOSReferidaRetornoTipo = Fachada.getInstancia().pesquisar(
				filtroOSReferidaRetornoTipo, OsReferidaRetornoTipo.class.getName());
		
		RelatorioOSFiscalizacao relatorioOSFiscalizacao = new RelatorioOSFiscalizacao(
			(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
		// Parte que vai mandar o relatório para a tela
		// cria uma instância da classe do relatório
		
		relatorioOSFiscalizacao.addParametro("colecaoOSFiscalizacaoGeradas", colecaoOSFiscalizacaoGeradas);
		relatorioOSFiscalizacao.addParametro("qtdeOSFiscalizacaoGeradas", colecaoOSFiscalizacaoGeradas.size());
		relatorioOSFiscalizacao.addParametro("idGrupoCobranca", idGrupoCobranca);
		relatorioOSFiscalizacao.addParametro("colecaoOSReferidaRetornoTipo", colecaoOSReferidaRetornoTipo);
		
		String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		relatorioOSFiscalizacao.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

		
		try {
			retorno = processarExibicaoRelatorio(relatorioOSFiscalizacao,
				tipoRelatorio, httpServletRequest, httpServletResponse,	actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
	
		return retorno;
	}

}
