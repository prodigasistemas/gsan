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

import gcom.atendimentopublico.ordemservico.BoletimOsConcluida;
import gcom.atendimentopublico.ordemservico.FiltroBoletimOSConcluida;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0753] Manter Ordem de Servico Concluida
 * 
 * @author Ivan Sérgio
 * @created 26/03/2008
 */
public class ManterOrdemServicoConcluidaAction extends GcomAction {
	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(
			ActionMapping actionMapping,
			ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();
		//HttpSession sessao = httpServletRequest.getSession(false);
		
		ManterOrdemServicoConcluidaActionForm form = (ManterOrdemServicoConcluidaActionForm) actionForm;
		
		//********************************************************************************************
		// Data da Fiscalizacao deve ser anterior a data corrente.
		// Data da Fiscalizacao deve ser maior ou igual a Data de Encerramento da O.S.
		// Data de Fiscalizacao deve ser maior ou igual a ultima data da fiscalizacao.
		//********************************************************************************************
		Date dataAtual = new Date();
		String data = Util.getDiaMes(dataAtual) + "/" + Util.getMes(dataAtual) + "/" + Util.getAno(dataAtual);
		dataAtual = Util.converteStringParaDate(data);
					
		Date dataFicalizacao = new Date();
		Date dataFicalizacaoAnt = new Date();
		boolean possuiDataFiscalizacaoAnt = false;
		
		Date dataEncerramentoOS = new Date();
		dataEncerramentoOS = Util.converteStringParaDate(form.getDataEncerramento());
		
		int indicadorDataVerificacao = 0;
		
		if (!form.getDataFiscalizacao1().equals("")) {
			indicadorDataVerificacao = 1;
		}
		
		if (!form.getDataFiscalizacao2().equals("")) {
			indicadorDataVerificacao = 2;
		}
		
		if (!form.getDataFiscalizacao3().equals("")) {
			indicadorDataVerificacao = 3;
		}
		
		switch (indicadorDataVerificacao) {
		// Faz a verificacao na 1a data de Fiscalizacao
		case 1:
			dataFicalizacao = Util.converteStringParaDate(form.getDataFiscalizacao1());
			break;
		// Faz a verificacao na 2a data de Fiscalizacao
		case 2:
			dataFicalizacao = Util.converteStringParaDate(form.getDataFiscalizacao2());
			
			dataFicalizacaoAnt = Util.converteStringParaDate(form.getDataFiscalizacao1());
			possuiDataFiscalizacaoAnt = true;
			break;
		// Faz a verificacao na 3a data de Fiscalizacao
		case 3:
			dataFicalizacao = Util.converteStringParaDate(form.getDataFiscalizacao3());
			
			dataFicalizacaoAnt = Util.converteStringParaDate(form.getDataFiscalizacao2());
			possuiDataFiscalizacaoAnt = true;
			break;
		}
		
		if (indicadorDataVerificacao > 0) {
			if (dataFicalizacao.compareTo(dataAtual) >= 0) {
				throw new ActionServletException("atencao.data_fiscalizacao_anterior_corrente",
						null, indicadorDataVerificacao + "° ");
			}else if (dataFicalizacao.compareTo(dataEncerramentoOS) < 0) {
				throw new ActionServletException("atencao.data_fiscalizacao_anterior_data_encerramento_os",
						null, indicadorDataVerificacao + "° ");
			}else if (possuiDataFiscalizacaoAnt) {
				if (dataFicalizacao.compareTo(dataFicalizacaoAnt) < 0) {
					throw new ActionServletException("atencao.data_fiscalizacao_menor_data_fiscalizacao_anterior",
							null, indicadorDataVerificacao + "° ");
				}
			}
		}
		//********************************************************
		
		//********************************************************
		// Verifica a Matricula do Funcionario
		//********************************************************
		if (!Util.converterStringParaInteger(form.getCodigoFiscalizacao()).equals(0)) {
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.ID, form.getIdUsuario()));
			
			Collection dadosUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (dadosUsuario.isEmpty()) {
				throw new ActionServletException("pesquisa.funcionario.inexistente");
			}
		}
		
		//*************************************************************
		// [FS0005] - Atualizacao Realizada Por Outro Usuario
		//*************************************************************
		FiltroBoletimOSConcluida filtroBoletim = new FiltroBoletimOSConcluida();
		filtroBoletim.adicionarParametro(new ParametroSimples(
				FiltroBoletimOSConcluida.ID, form.getIdOrdemServico()));			
		//filtroBoletim.adicionarParametro(new ParametroSimples(
		//		FiltroBoletimOSConcluida.ULTIMA_ALTERACAO, form.getDataUltimaAlteracao()));
		
		Collection dadosBoletim = fachada.pesquisar(filtroBoletim, BoletimOsConcluida.class.getName());
		
		Iterator iDadosBoletim = dadosBoletim.iterator();
		BoletimOsConcluida b = (BoletimOsConcluida) iDadosBoletim.next();
		
		if (!b.getUltimaAlteracao().toString().equals(form.getDataUltimaAlteracao())) {
			throw new ActionServletException("atencao.registro_atualizado_por_outro_usuario");
		}
		
		//*********************************************************************
		// Realiza a Manutencao nos dados do Boletim de Datas de Fiscalizacao
		//*********************************************************************
		fachada.atualizarDadosFiscalizacao(
				Util.converterStringParaInteger(form.getIdOrdemServico()),
				new Short(form.getCodigoFiscalizacao()),
				form.getDataFiscalizacao1(),
				form.getDataFiscalizacao2(),
				form.getDataFiscalizacao3(),
				Util.converterStringParaInteger(form.getIdUsuario()),
				new Short(form.getCodigoFiscalizacaoAnterior()));
		
		montarPaginaSucesso(httpServletRequest, "OS de código " + 
				form.getIdOrdemServico() + " atualizada com sucesso.",
				"Atualizar dados da Fiscalização", 
				"exibirAtualizarOrdemServicoConcluidaAction.do?menu=sim");
		
		return retorno;
	}
}