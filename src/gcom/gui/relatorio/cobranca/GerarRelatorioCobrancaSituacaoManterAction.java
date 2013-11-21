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
package gcom.gui.relatorio.cobranca;

import java.util.Collection;

import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.FiltroContaMotivoRevisao;
import gcom.gui.cobranca.FiltrarCobrancaSituacaoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.RelatorioManterCobrancaSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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

public class GerarRelatorioCobrancaSituacaoManterAction extends
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

		FiltrarCobrancaSituacaoActionForm filtrarCobrancaSituacaoActionForm = (FiltrarCobrancaSituacaoActionForm) actionForm;

		FiltroCobrancaSituacao filtroCobrancaSituacao = (FiltroCobrancaSituacao) sessao
				.getAttribute("filtroCobrancaSituacao");

		// Inicio da parte que vai mandar os parametros para o relatório
		Fachada fachada = Fachada.getInstancia();
		
		CobrancaSituacao cobrancaSituacaoParametros = new CobrancaSituacao();

		String id = null;

		String idCobrancaSituacaoPesquisar = (String) filtrarCobrancaSituacaoActionForm.getId();

		if (idCobrancaSituacaoPesquisar != null && !idCobrancaSituacaoPesquisar.equals("")) {
			id = idCobrancaSituacaoPesquisar;
		}
		
		Short indicadorUso = null;
		
		if(filtrarCobrancaSituacaoActionForm.getIndicadorUso()!= null && !filtrarCobrancaSituacaoActionForm.getIndicadorUso().equals("")){
			
			indicadorUso = new Short ("" + filtrarCobrancaSituacaoActionForm.getIndicadorUso());
		}

		Short indicadorExigenciaAdvogado = null;
		
		if(filtrarCobrancaSituacaoActionForm.getIndicadorExigenciaAdvogado()!= null && !filtrarCobrancaSituacaoActionForm.getIndicadorExigenciaAdvogado().equals("")){
			
			indicadorExigenciaAdvogado = new Short ("" + filtrarCobrancaSituacaoActionForm.getIndicadorExigenciaAdvogado());
		}

		Short indicadorBloqueioParcelamento = null;
		
		if(filtrarCobrancaSituacaoActionForm.getIndicadorBloqueioParcelamento()!= null && !filtrarCobrancaSituacaoActionForm.getIndicadorBloqueioParcelamento().equals("")){
			
			indicadorBloqueioParcelamento = new Short ("" + filtrarCobrancaSituacaoActionForm.getIndicadorBloqueioParcelamento());
		}
		
		
		if (filtrarCobrancaSituacaoActionForm.getContaMotivoRevisao() != null && !filtrarCobrancaSituacaoActionForm.getContaMotivoRevisao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroContaMotivoRevisao filtroContaMotivoRevisao = new FiltroContaMotivoRevisao();
			filtroContaMotivoRevisao.adicionarParametro(new ParametroSimples(FiltroContaMotivoRevisao.ID, filtrarCobrancaSituacaoActionForm.getContaMotivoRevisao()));
			
			Collection colecaoCobrancaSituacao = fachada.pesquisar(filtroContaMotivoRevisao, ContaMotivoRevisao.class.getName());
			
			if (colecaoCobrancaSituacao != null && !colecaoCobrancaSituacao.isEmpty()) {
				ContaMotivoRevisao contaMotivoRevisao = (ContaMotivoRevisao) Util.retonarObjetoDeColecao(colecaoCobrancaSituacao);
				cobrancaSituacaoParametros.setContaMotivoRevisao(contaMotivoRevisao);
			}
			
		}
		
		
		// seta os parametros que serão mostrados no relatório

		cobrancaSituacaoParametros.setId(id == null ? null : new Integer(
				id));
		cobrancaSituacaoParametros.setDescricao(filtrarCobrancaSituacaoActionForm.getDescricao());
		cobrancaSituacaoParametros.setIndicadorExigenciaAdvogado(indicadorExigenciaAdvogado);
		cobrancaSituacaoParametros.setIndicadorBloqueioParcelamento(indicadorBloqueioParcelamento);
		cobrancaSituacaoParametros.setIndicadorUso(indicadorUso);
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
	
		RelatorioManterCobrancaSituacao relatorioManterCobrancaSituacao = new RelatorioManterCobrancaSituacao(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterCobrancaSituacao.addParametro("filtroCobrancaSituacao",
				filtroCobrancaSituacao);
		relatorioManterCobrancaSituacao.addParametro("cobrancaSituacaoParametros",
				cobrancaSituacaoParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterCobrancaSituacao.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterCobrancaSituacao,
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