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
* Anderson Italo Felinto de Lima
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeComando;
import gcom.fachada.Fachada;
import gcom.gui.cobranca.MotivoNaoGeracaoDocumentoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioMotivoNaoGeracaoDocumentoCobranca;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;


/**
 * @author Anderson Italo
 * @date 30/11/2009
 * Classe responsável pelo pré-precessamento 
 * da chamada do Relatorio Motivo de não geraçao de Documentos de Cobrança
 * UC9999 Consultar Motivo da não Geração de Documento de Cobrança
 */
public class GerarRelatorioMotivoNaoGeracaoDocumentoEventualAction extends ExibidorProcessamentoTarefaRelatorio{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		MotivoNaoGeracaoDocumentoActionForm form = (MotivoNaoGeracaoDocumentoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = null;
		
		/*5.	Caso Contrário
		 * 5.1.	O sistema exibe os dados para o Comando
		 * (Chamada do Relatorio Motivo de não geraçao de Documentos de Cobrança)*/
		if ((httpServletRequest.getParameter("filtroPorComandoSintetico") != null 
				&& httpServletRequest.getParameter("filtroPorComandoSintetico").equals("true"))
				||(httpServletRequest.getParameter("filtroPorComandoAnalitico") != null 
				&& httpServletRequest.getParameter("filtroPorComandoAnalitico").equals("true"))){
			
			FiltroCobrancaAcaoAtividadeComando filtroCobrancaAcaoAtividadeComando = new FiltroCobrancaAcaoAtividadeComando();
			filtroCobrancaAcaoAtividadeComando.adicionarParametro(
				new ParametroSimples(
					FiltroCobrancaAcaoAtividadeComando.ID, 
					new Integer(form.getIdCobrancaAcaoAtividadeComando())));
			
			filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.CLIENTE);
			filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.CLIENTE_RELACAO_TIPO);
			filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.CLIENTE_SUPERIOR);
			filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.COBRANCA_ACAO);
			filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.COBRANCA_ATIVIDADE);
			filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.USUARIO);
			
			
			Collection colecaoCobrancaAcaoAtividadeComando = fachada.pesquisar(filtroCobrancaAcaoAtividadeComando, CobrancaAcaoAtividadeComando.class.getName());
			cobrancaAcaoAtividadeComando = (CobrancaAcaoAtividadeComando)Util.retonarObjetoDeColecao(colecaoCobrancaAcaoAtividadeComando);
			
		}
		
		
		Usuario usuario = (Usuario) httpServletRequest.getSession(false).getAttribute("usuarioLogado");
		int tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		
		RelatorioMotivoNaoGeracaoDocumentoCobranca relatorio = new RelatorioMotivoNaoGeracaoDocumentoCobranca(usuario);
		relatorio.addParametro("cobrancaAcaoAtividadeComando", cobrancaAcaoAtividadeComando);
		relatorio.addParametro("tipoRelatorio", tipoRelatorio);
		relatorio.addParametro("indicadorCronograma", 2);
		relatorio.addParametro("form",form);
		
		if(httpServletRequest.getParameter("filtroPorComandoSintetico") != null 
				&& httpServletRequest.getParameter("filtroPorComandoSintetico").equals("true")){
			relatorio.addParametro("sintetico", 1);
		}else{
			relatorio.addParametro("sintetico", 2);
		}
		
		return processarExibicaoRelatorio(
				relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
		
	}

}
