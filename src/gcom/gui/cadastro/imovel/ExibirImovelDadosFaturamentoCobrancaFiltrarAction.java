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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.EloAnormalidade;
import gcom.cadastro.imovel.FiltroCadastroOcorrencia;
import gcom.cadastro.imovel.FiltroEloAnormalidade;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.CobrancaSituacaoTipo;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacaoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirImovelDadosFaturamentoCobrancaFiltrarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarImovelDadosFaturamento");

		Fachada fachada = Fachada.getInstancia();

		FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();
		filtroFaturamentoSituacaoTipo.setCampoOrderBy(FiltroFaturamentoSituacaoTipo.DESCRICAO);
		Collection<FaturamentoSituacaoTipo> collectionFaturamentoSituacaoTipo = fachada
				.pesquisar(filtroFaturamentoSituacaoTipo,
						FaturamentoSituacaoTipo.class.getName());
		 if(collectionFaturamentoSituacaoTipo == null || collectionFaturamentoSituacaoTipo.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null,
				"Tipo de Situação Especial de Faturamento");			
		 }	
		
		FiltroCobrancaSituacaoTipo filtroCobrancaSituacaoTipo = new FiltroCobrancaSituacaoTipo();
		filtroCobrancaSituacaoTipo.setCampoOrderBy(FiltroCobrancaSituacaoTipo.DESCRICAO);
		Collection<CobrancaSituacaoTipo> collectionCobrancaSituacaoTipo = fachada
				.pesquisar(filtroCobrancaSituacaoTipo,
						CobrancaSituacaoTipo.class.getName());
		 if(collectionCobrancaSituacaoTipo == null || collectionCobrancaSituacaoTipo.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null,
				" Tipo de Situação Especial de Cobrança");			
		 }	
		
		FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
		filtroCobrancaSituacao.setCampoOrderBy(FiltroCobrancaSituacao.DESCRICAO);
		Collection<CobrancaSituacao> collectionCobrancaSituacao = fachada
				.pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class
						.getName());
		 if(collectionCobrancaSituacao == null || collectionCobrancaSituacao.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null,
				" Situação de Cobrança");			
		 }	
		
		
		FiltroEloAnormalidade filtroEloAnormalidade = new FiltroEloAnormalidade();
		filtroEloAnormalidade.setCampoOrderBy(FiltroEloAnormalidade.DESCRICAO);
		Collection<EloAnormalidade> collectionEloAnormalidade = fachada
				.pesquisar(filtroEloAnormalidade, EloAnormalidade.class
						.getName());
		 if(collectionEloAnormalidade == null || collectionEloAnormalidade.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null,
				"Anormalidade de Elo");			
		 }	
		
		
		FiltroCadastroOcorrencia filtroCadastroOcorrencia = new FiltroCadastroOcorrencia();
		filtroCadastroOcorrencia.setCampoOrderBy(FiltroCadastroOcorrencia.DESCRICAO);
		Collection<CadastroOcorrencia> collectionCadastroOcorrencia = fachada
				.pesquisar(filtroCadastroOcorrencia, CadastroOcorrencia.class
						.getName());
		 if(collectionCadastroOcorrencia == null || collectionCadastroOcorrencia.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null,
				"Ocorrência de Cadastro");			
		 }	
		
		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
		filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
		Collection<ConsumoTarifa> collectionConsumoTarifa = fachada.pesquisar(
				filtroConsumoTarifa, ConsumoTarifa.class.getName());
		 if(collectionConsumoTarifa == null || collectionConsumoTarifa.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null,
				"Tarifa de Consumo");			
		 }	
		
		httpServletRequest.setAttribute("collectionFaturamentoSituacaoTipo",
				collectionFaturamentoSituacaoTipo);
		httpServletRequest.setAttribute("collectionCobrancaSituacaoTipo",
				collectionCobrancaSituacaoTipo);
		httpServletRequest.setAttribute("collectionCobrancaSituacao",
				collectionCobrancaSituacao);
		httpServletRequest.setAttribute("collectionEloAnormalidade",
				collectionEloAnormalidade);
		httpServletRequest.setAttribute("collectionCadastroOcorrencia",
				collectionCadastroOcorrencia);
		httpServletRequest.setAttribute("collectionConsumoTarifa",
				collectionConsumoTarifa);

		return retorno;
	}

}
