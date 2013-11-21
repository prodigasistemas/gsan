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

public class AtualizarResolucaoDiretoriaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarResolucaoDiretoriaActionForm atualizarResolucaoDiretoriaActionForm = (AtualizarResolucaoDiretoriaActionForm) actionForm;

		ResolucaoDiretoria resolucaoDiretoria = (ResolucaoDiretoria) sessao
				.getAttribute("resolucaoDiretoriaAtualizar");

		resolucaoDiretoria.setNumeroResolucaoDiretoria(atualizarResolucaoDiretoriaActionForm.getNumero());
		resolucaoDiretoria.setDescricaoAssunto(atualizarResolucaoDiretoriaActionForm.getAssunto());
		resolucaoDiretoria.setDataVigenciaInicio(Util.converteStringParaDate(atualizarResolucaoDiretoriaActionForm.getDataInicio()));
		resolucaoDiretoria.setIndicadorParcelamentoUnico(new Short (atualizarResolucaoDiretoriaActionForm.getIndicadorParcelamentoUnico()));
		resolucaoDiretoria.setIndicadorUtilizacaoLivre(new Short (atualizarResolucaoDiretoriaActionForm.getIndicadorUtilizacaoLivre()));
		resolucaoDiretoria.setIndicadorDescontoSancoes(new Short (atualizarResolucaoDiretoriaActionForm.getIndicadorDescontoSancoes()));
		resolucaoDiretoria.setIndicadorParcelasEmAtraso(new Short(atualizarResolucaoDiretoriaActionForm.getIndicadorParcelasEmAtraso()));
		resolucaoDiretoria.setIndicadorParcelamentoEmAndamento(new Short(atualizarResolucaoDiretoriaActionForm.getIndicadorParcelamentoEmAndamento()));
		resolucaoDiretoria.setIndicadorNegociacaoSoAVista(new Short(atualizarResolucaoDiretoriaActionForm.getIndicadorNegociacaoSoAVista()));
		resolucaoDiretoria.setIndicadorDescontoSoEmContaAVista(new Short(atualizarResolucaoDiretoriaActionForm.getIndicadorDescontoSoEmContaAVista()));
		resolucaoDiretoria.setIndicadorParcelamentoLojaVirtual(new Short(atualizarResolucaoDiretoriaActionForm.getIndicadorParcelamentoLojaVirtual()));
		
		if (atualizarResolucaoDiretoriaActionForm.getIdParcelasEmAtraso()!= null &&
				!atualizarResolucaoDiretoriaActionForm.getIdParcelasEmAtraso().equals("")){
			
			FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
			FiltroResolucaoDiretoria.CODIGO, new Integer(atualizarResolucaoDiretoriaActionForm.getIdParcelasEmAtraso())));
			Collection<ResolucaoDiretoria> colecaoRD = fachada.pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());

			if(!Util.isVazioOrNulo(colecaoRD)){
				ResolucaoDiretoria rdParcelasEmAtraso = new ResolucaoDiretoria();
				rdParcelasEmAtraso.setId(new Integer(atualizarResolucaoDiretoriaActionForm.getIdParcelasEmAtraso()));
				resolucaoDiretoria.setRdParcelasEmAtraso(rdParcelasEmAtraso);
			}else{
				//RD Parcelas em Atraso inexistente.
				throw new ActionServletException(
				"atencao.pesquisa_inexistente", null, "RD Parcelas em Atraso");
			}
		}

		if (atualizarResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento()!= null &&
				!atualizarResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento().equals("")){
			
			FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
			FiltroResolucaoDiretoria.CODIGO, new Integer(atualizarResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento())));

			Collection<ResolucaoDiretoria> colecaoRD = fachada.pesquisar(filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());

			if(!Util.isVazioOrNulo(colecaoRD)){
				ResolucaoDiretoria rdParcelamentoEmAndamento = new ResolucaoDiretoria();
				rdParcelamentoEmAndamento.setId(new Integer(atualizarResolucaoDiretoriaActionForm.getIdParcelamentoEmAndamento()));
				resolucaoDiretoria.setRdParcelamentoEmAndamento(rdParcelamentoEmAndamento);
			}else{
				//RD Parcelamento em Andamento inexistente.
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "RD Parcelamento em Andamento");
			}
			
		}
		if (atualizarResolucaoDiretoriaActionForm.getDataFim() != null 
				&& !atualizarResolucaoDiretoriaActionForm.getDataFim().equals("")) {
			resolucaoDiretoria.setDataVigenciaFim(Util.converteStringParaDate(atualizarResolucaoDiretoriaActionForm.getDataFim()));
		}
		fachada.atualizarResolucaoDiretoria(resolucaoDiretoria, this.getUsuarioLogado(httpServletRequest));

		montarPaginaSucesso(httpServletRequest, "Resolução de Diretoria "
				+ resolucaoDiretoria.getNumeroResolucaoDiretoria()
				+ " atualizado com sucesso.",
				"Realizar outra Manutenção de Resolução de Diretoria",
				"exibirFiltrarResolucaoDiretoriaAction.do?menu=sim");

		return retorno;

	}
}
