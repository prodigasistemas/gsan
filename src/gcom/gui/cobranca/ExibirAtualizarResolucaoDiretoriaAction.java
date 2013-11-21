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
import gcom.cobranca.ResolucaoDiretoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarResolucaoDiretoriaAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarResolucaoDiretoria");

		AtualizarResolucaoDiretoriaActionForm atualizarResolucaoDiretoriaActionForm = (AtualizarResolucaoDiretoriaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		if (httpServletRequest.getParameter("inserir") != null
				&& !httpServletRequest.getParameter("inserir").equals("")) {
			String inserir = httpServletRequest.getParameter("inserir");
			httpServletRequest.setAttribute("inserir", inserir);
		}

		if (sessao.getAttribute("resolucaoDiretoria") != null) {

			ResolucaoDiretoria resolucaoDiretoria = (ResolucaoDiretoria) sessao
					.getAttribute("resolucaoDiretoria");

			atualizarResolucaoDiretoriaActionForm.setNumero(resolucaoDiretoria.getNumeroResolucaoDiretoria());
			atualizarResolucaoDiretoriaActionForm.setAssunto(resolucaoDiretoria.getDescricaoAssunto());
			atualizarResolucaoDiretoriaActionForm.setDataInicio(Util.formatarData(resolucaoDiretoria.getDataVigenciaInicio()));
			atualizarResolucaoDiretoriaActionForm.setDataFim(Util.formatarData(resolucaoDiretoria.getDataVigenciaFim()));
			atualizarResolucaoDiretoriaActionForm.setIndicadorParcelamentoUnico(resolucaoDiretoria.getIndicadorParcelamentoUnico().toString());
			atualizarResolucaoDiretoriaActionForm.setIndicadorUtilizacaoLivre(resolucaoDiretoria.getIndicadorUtilizacaoLivre().toString());
			atualizarResolucaoDiretoriaActionForm.setIndicadorDescontoSancoes(resolucaoDiretoria.getIndicadorDescontoSancoes().toString());
			atualizarResolucaoDiretoriaActionForm.setIndicadorParcelamentoLojaVirtual(resolucaoDiretoria.getIndicadorParcelamentoLojaVirtual().toString());
			atualizarResolucaoDiretoriaActionForm.setIndicadorParcelasEmAtraso(resolucaoDiretoria.getIndicadorParcelasEmAtraso().toString());
			
			if (resolucaoDiretoria.getRdParcelasEmAtraso()!= null &&
					!resolucaoDiretoria.getRdParcelasEmAtraso().equals("")){
				atualizarResolucaoDiretoriaActionForm.setIdParcelasEmAtraso(resolucaoDiretoria.getRdParcelasEmAtraso().getId().toString());
			}
			
			atualizarResolucaoDiretoriaActionForm.setIndicadorParcelamentoEmAndamento(resolucaoDiretoria.getIndicadorParcelamentoEmAndamento().toString());
			
			if (resolucaoDiretoria.getRdParcelamentoEmAndamento()!= null &&
					!resolucaoDiretoria.getRdParcelamentoEmAndamento().equals("")){
				atualizarResolucaoDiretoriaActionForm.setIdParcelamentoEmAndamento(resolucaoDiretoria.getRdParcelamentoEmAndamento().getId().toString());
			}
			
			atualizarResolucaoDiretoriaActionForm.setIndicadorNegociacaoSoAVista(resolucaoDiretoria.getIndicadorNegociacaoSoAVista().toString());
			
			atualizarResolucaoDiretoriaActionForm.setIndicadorDescontoSoEmContaAVista(resolucaoDiretoria.getIndicadorDescontoSoEmContaAVista().toString());

			sessao.setAttribute("resolucaoDiretoriaAtualizar",
					resolucaoDiretoria);
			sessao.removeAttribute("resolucaoDiretoria");

		} else {

			String idResolucaoDiretoria = httpServletRequest.getParameter("resolucaoDiretoriaID");

			FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.CODIGO, idResolucaoDiretoria));

			Collection<ResolucaoDiretoria> colecaoResolucaoDiretoria = fachada.pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());

			if (Util.isVazioOrNulo(colecaoResolucaoDiretoria)) {
				throw new ActionServletException("atencao.atualizacao.timestamp");
			}

			ResolucaoDiretoria resolucaoDiretoria = (ResolucaoDiretoria) colecaoResolucaoDiretoria.iterator().next();

			atualizarResolucaoDiretoriaActionForm.setNumero(resolucaoDiretoria.getNumeroResolucaoDiretoria());
			atualizarResolucaoDiretoriaActionForm.setAssunto(resolucaoDiretoria.getDescricaoAssunto());
			atualizarResolucaoDiretoriaActionForm.setDataInicio(Util.formatarData(resolucaoDiretoria.getDataVigenciaInicio()));
			atualizarResolucaoDiretoriaActionForm.setDataFim(Util.formatarData(resolucaoDiretoria.getDataVigenciaFim()));
			atualizarResolucaoDiretoriaActionForm.setIndicadorParcelamentoUnico(resolucaoDiretoria.getIndicadorParcelamentoUnico().toString());
			atualizarResolucaoDiretoriaActionForm.setIndicadorUtilizacaoLivre(resolucaoDiretoria.getIndicadorUtilizacaoLivre().toString());
			atualizarResolucaoDiretoriaActionForm.setIndicadorDescontoSancoes(resolucaoDiretoria.getIndicadorDescontoSancoes().toString());
			atualizarResolucaoDiretoriaActionForm.setIndicadorParcelasEmAtraso(resolucaoDiretoria.getIndicadorParcelasEmAtraso().toString());
			atualizarResolucaoDiretoriaActionForm.setIndicadorParcelamentoLojaVirtual(resolucaoDiretoria.getIndicadorParcelamentoLojaVirtual().toString());
			
			if (resolucaoDiretoria.getRdParcelasEmAtraso()!= null &&
					!resolucaoDiretoria.getRdParcelasEmAtraso().equals("")){
				atualizarResolucaoDiretoriaActionForm.setIdParcelasEmAtraso(resolucaoDiretoria.getRdParcelasEmAtraso().getId().toString());
			}
			
			atualizarResolucaoDiretoriaActionForm.setIndicadorParcelamentoEmAndamento(resolucaoDiretoria.getIndicadorParcelamentoEmAndamento().toString());
			
			if (resolucaoDiretoria.getRdParcelamentoEmAndamento()!= null &&
					!resolucaoDiretoria.getRdParcelamentoEmAndamento().equals("")){
				atualizarResolucaoDiretoriaActionForm.setIdParcelamentoEmAndamento(resolucaoDiretoria.getRdParcelamentoEmAndamento().getId().toString());
			}
			
			atualizarResolucaoDiretoriaActionForm.setIndicadorNegociacaoSoAVista(resolucaoDiretoria.getIndicadorNegociacaoSoAVista().toString());
			
			atualizarResolucaoDiretoriaActionForm.setIndicadorDescontoSoEmContaAVista(resolucaoDiretoria.getIndicadorDescontoSoEmContaAVista().toString());
			
			sessao.setAttribute("resolucaoDiretoriaAtualizar",
					resolucaoDiretoria);

		}

		return retorno;

	}

}