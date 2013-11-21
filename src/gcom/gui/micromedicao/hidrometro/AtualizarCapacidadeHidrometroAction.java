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
package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarCapacidadeHidrometroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarCapacidadeHidrometroActionForm atualizarCapacidadeHidrometroActionForm = (AtualizarCapacidadeHidrometroActionForm) actionForm;

		HidrometroCapacidade hidrometroCapacidade = (HidrometroCapacidade) sessao
				.getAttribute("hidrometroCapacidadeAtualizar");

		hidrometroCapacidade.setId(new Integer(
				atualizarCapacidadeHidrometroActionForm.getIdentificador()));
		hidrometroCapacidade
				.setDescricao(atualizarCapacidadeHidrometroActionForm
						.getDescricao());
		hidrometroCapacidade
				.setDescricaoAbreviada(atualizarCapacidadeHidrometroActionForm
						.getAbreviatura());
		hidrometroCapacidade.setLeituraMinimo(new Short(
				atualizarCapacidadeHidrometroActionForm.getNumMinimo()));
		hidrometroCapacidade.setLeituraMaximo((new Short(
				atualizarCapacidadeHidrometroActionForm.getNumMaximo())));
		hidrometroCapacidade
				.setIndicadorUso(atualizarCapacidadeHidrometroActionForm
						.getIndicadoruso());
		hidrometroCapacidade.setNumeroOrdem(new Short(
				atualizarCapacidadeHidrometroActionForm.getNumOrdem()));
		hidrometroCapacidade
				.setCodigoHidrometroCapacidade(atualizarCapacidadeHidrometroActionForm
						.getCodigo());

		String numMinimo = atualizarCapacidadeHidrometroActionForm
				.getNumMinimo();
		String numMaximo = atualizarCapacidadeHidrometroActionForm
				.getNumMaximo();
		String codigo = atualizarCapacidadeHidrometroActionForm.getCodigo();
		
		String numOrdem = atualizarCapacidadeHidrometroActionForm.getNumOrdem();

		Collection colecaoPesquisa = null;

		// O numero maximo de digitos de leitura do hidrômetro é obrigatório.
		if (numMaximo != null && !numMaximo.equalsIgnoreCase("")) {
			if (new Integer(numMaximo).intValue() < new Integer(numMinimo)
					.intValue()) {
				throw new ActionServletException(
						"atencao.numero_minimo_nao_pode_ser_maior_que_numero_maximo",
						null,
						"Numero maximo de digitos de leitura do hidrômetro");
			}
			hidrometroCapacidade.setLeituraMaximo(new Short(numMaximo));
		}

		// Verificar existência do código da Capacidade do Hidrometro que não seja ele mesmo
		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();

		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
				FiltroHidrometroCapacidade.CODIGO_HIDROMETRO_CAPACIDADE,
				hidrometroCapacidade.getCodigoHidrometroCapacidade()));

		// Verificar existência da Capacidade do Hidrometro
		colecaoPesquisa = fachada.pesquisar(filtroHidrometroCapacidade,
				HidrometroCapacidade.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {

			HidrometroCapacidade hidrometroCapacidadeBase = (HidrometroCapacidade) colecaoPesquisa
					.iterator().next();

			if ((hidrometroCapacidadeBase.getId().intValue() != hidrometroCapacidade
					.getId().intValue())
					&& (hidrometroCapacidadeBase
							.getCodigoHidrometroCapacidade()
							.equalsIgnoreCase(hidrometroCapacidade
									.getCodigoHidrometroCapacidade()))) {

				// Capacidade de hidrometro já existe
				throw new ActionServletException(
						"atencao.pesquisa_capacidade_do_hidrometro_ja_cadastrada",
						null, codigo);
			}

		}
		
		//Verifica a Existencia de um Numero de Ordem Já existente na base que não seja ele mesmo
		
		filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
		
		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
				FiltroHidrometroCapacidade.NUMERO_ORDEM,
				hidrometroCapacidade.getNumeroOrdem()));

		// Verificar existência do numero de ordem da Capacidade do Hidrometro
		colecaoPesquisa = fachada.pesquisar(filtroHidrometroCapacidade,
				HidrometroCapacidade.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {

			HidrometroCapacidade hidrometroCapacidadeBase = (HidrometroCapacidade) colecaoPesquisa
					.iterator().next();

			if ((hidrometroCapacidadeBase.getId().intValue() != hidrometroCapacidade
					.getId().intValue())
					&& (hidrometroCapacidadeBase
							.getNumeroOrdem().toString()
							.equalsIgnoreCase(hidrometroCapacidade
									.getNumeroOrdem().toString()))) {

				// Numero de Ordem já existe
				throw new ActionServletException(
						"atencao.pesquisa_numero_de_ordem_da_capacidade_do_hidrometro_ja_cadastrada",
						null, numOrdem);
			}

		}

		fachada.atualizarCapacidadeHidrometro(hidrometroCapacidade);

		montarPaginaSucesso(httpServletRequest,
				"Capacidade de Hidrômetro de código "
						+ hidrometroCapacidade.getId().toString()
						+ " atualizado com sucesso.",
				"Realizar outra Manutenção de Capacidade de Hidômetro",
				"exibirFiltrarCapacidadeHidrometroAction.do?menu=sim");
		return retorno;
	}
}
