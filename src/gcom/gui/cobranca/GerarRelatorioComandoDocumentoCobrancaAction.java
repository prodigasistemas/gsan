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
 * Rômulo Aurélio de Melo Souza Filho
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

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioComandoDocumentoCobranca;
import gcom.relatorio.cobranca.RelatorioNotificacaoDebito;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioComandoDocumentoCobrancaAction extends
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

		String idCobrancaAcaoAtividadeCronograma = httpServletRequest
				.getParameter("idCobrancaAcaoAtividadeCronograma");
		
		String tipoEndRelatorio = httpServletRequest
			.getParameter("tipoEndRelatorio");

		Integer idCobrancaAcaoCronograma = null;

		if (idCobrancaAcaoAtividadeCronograma != null
				&& !idCobrancaAcaoAtividadeCronograma.trim().equals("")) {
			idCobrancaAcaoCronograma = new Integer(
					idCobrancaAcaoAtividadeCronograma);
		}
		
		

		String idCobrancaAcaoAtividadeComando = httpServletRequest
				.getParameter("idCobrancaAcaoAtividadeComando");

		Integer idCobrancaAcaoComando = null;

		if (idCobrancaAcaoAtividadeComando != null
				&& !idCobrancaAcaoAtividadeComando.trim().equals("")) {
			idCobrancaAcaoComando = new Integer(idCobrancaAcaoAtividadeComando);
		}

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		TarefaRelatorio relatorio = null;
		
		Fachada fachada = Fachada.getInstancia();

		CobrancaAcao cobrancaAcao = fachada
			.pesquisarAcaoCobrancaParaRelatorio(idCobrancaAcaoComando,idCobrancaAcaoCronograma);

		DocumentoTipo documentoTipo = fachada
			.pesquisarTipoAcaoCobrancaParaRelatorio(idCobrancaAcaoComando,idCobrancaAcaoCronograma);

		if (documentoTipo != null && (documentoTipo.getId().intValue() == DocumentoTipo.AVISO_CORTE
				|| documentoTipo.getId().intValue() == DocumentoTipo.CORTE_ADMINISTRATIVO)) {

			relatorio = new RelatorioNotificacaoDebito(
					(Usuario) (httpServletRequest.getSession(false))
							.getAttribute("usuarioLogado"));

		} else {
			relatorio = new RelatorioComandoDocumentoCobranca(
					(Usuario) (httpServletRequest.getSession(false))
							.getAttribute("usuarioLogado"));
		}
		
		relatorio.addParametro("tipoEndRelatorio", tipoEndRelatorio);
		relatorio.addParametro("idCobrancaAcaoCronograma",
				idCobrancaAcaoCronograma);
		
		relatorio.addParametro("idCobrancaAcaoComando", idCobrancaAcaoComando);
		
		relatorio.addParametro("cobrancaAcao", cobrancaAcao);

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		
		/*
		 * TODO : COSANPA
		 * Alteração feita para gerar avisos de corte. Não estavam 
		 * sendo gerados pois faltava esse parâmetro, que indica a qtd de documentos
		 * que vão ser gerados por página.
		 */
		relatorio.addParametro("quantidadeRelatorios", "2");
		
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
