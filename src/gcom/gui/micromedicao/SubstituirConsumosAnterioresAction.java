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
package gcom.gui.micromedicao;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.ControladorException;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class SubstituirConsumosAnterioresAction extends GcomAction {

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

		ActionForward retorno = actionMapping.findForward("exibirDadosAnalise");

		// LeituraConsumoActionForm leituraConsumoActionForm =
		// (LeituraConsumoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// String codigoImovel =
		// leituraConsumoActionForm.getIdImovelSubstituirConsumo();

		Collection colecaoImovelMicromedicao = (Collection) sessao
				.getAttribute("colecaoConsumoHistorico");

		Iterator iteratorImovelMicromedicao = colecaoImovelMicromedicao
				.iterator();
		String idImovel = "";

		while (iteratorImovelMicromedicao.hasNext()) {

			ImovelMicromedicao imovelMicromedicao = (ImovelMicromedicao) iteratorImovelMicromedicao
					.next();
			if (sessao.getAttribute("idImovelSelecionado") == null) {
				idImovel = imovelMicromedicao.getImovel().getId().toString();
			} else {
				idImovel = ((Integer) sessao
						.getAttribute("idImovelSelecionado")).toString();
			}
			if (imovelMicromedicao.getConsumoHistorico() != null) {
				ConsumoHistorico consumoHistorico = imovelMicromedicao
						.getConsumoHistorico();
				String consumoFaturadoMesAgua = (String) httpServletRequest
						.getParameter("agua"
								+ imovelMicromedicao.getConsumoHistorico()
										.getId().toString());
				if (!consumoFaturadoMesAgua.trim().equalsIgnoreCase(
						consumoHistorico.getNumeroConsumoCalculoMedia()
								.toString())) {
					consumoHistorico.setNumeroConsumoCalculoMedia(new Integer(
							consumoFaturadoMesAgua));
					fachada.atualizarConsumosAnteriores(consumoHistorico);
				}
			}
			if (imovelMicromedicao.getConsumoHistoricoEsgoto() != null) {
				ConsumoHistorico consumoHistoricoEsgoto = imovelMicromedicao
						.getConsumoHistoricoEsgoto();

				String consumoFaturadoMesEsgoto = (String) httpServletRequest
						.getParameter("esgoto"
								+ imovelMicromedicao
										.getConsumoHistoricoEsgoto().getId()
										.toString());
				if (!consumoFaturadoMesEsgoto.trim().equalsIgnoreCase(
						consumoHistoricoEsgoto.getNumeroConsumoCalculoMedia()
								.toString())) {
					consumoHistoricoEsgoto
							.setNumeroConsumoCalculoMedia(new Integer(
									consumoFaturadoMesEsgoto));
					fachada.atualizarConsumosAnteriores(consumoHistoricoEsgoto);
				}
			}

			Imovel imovel = new Imovel();
			imovel.setId(new Integer(idImovel));
			MedicaoTipo medicaoTipo = new MedicaoTipo();
			medicaoTipo.setId(MedicaoTipo.LIGACAO_AGUA);

			/**
			 * TODO : COSANPA Alterando o cálculo da média
			 */
			try {
				boolean houveIntslacaoHidrometro = fachada
						.verificarInstalacaoSubstituicaoHidrometro(
								imovel.getId(), medicaoTipo);

				int[] consumoMedioHidrometroAgua = fachada
						.obterVolumeMedioAguaEsgoto(imovel.getId(),
								imovelMicromedicao.getAnoMesGrupoFaturamento(),
								medicaoTipo.getId(), houveIntslacaoHidrometro);

				/**
				 * TODO : COSANPA Alterando o cálculo da média
				 */
				medicaoTipo.setId(MedicaoTipo.POCO);
				houveIntslacaoHidrometro = fachada
						.verificarInstalacaoSubstituicaoHidrometro(
								imovel.getId(), medicaoTipo);

				int[] consumoMedioHidrometroEsgoto = fachada
						.obterVolumeMedioAguaEsgoto(imovel.getId(),
								imovelMicromedicao.getAnoMesGrupoFaturamento(),
								medicaoTipo.getId(), houveIntslacaoHidrometro);

				fachada.atualizarConsumosMedio(new Integer(idImovel),
						imovelMicromedicao.getAnoMesGrupoFaturamento(),
						consumoMedioHidrometroAgua[0],
						consumoMedioHidrometroEsgoto[0]);

			} catch (ControladorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		httpServletRequest.setAttribute("sucesso",
				"Consumos substituídos com sucesso.");

		if (sessao.getAttribute("peloMenu") != null) {
			retorno = actionMapping.findForward("telaSucesso");

			montarPaginaSucesso(httpServletRequest,
					"Consumos anteriores substituídos do imóvel " + idImovel
							+ " com sucesso.",
					"Realizar outra Substituição de consumos anteriores",
					"exibirSubstituirConsumoAnteriorAction.do?menu=sim&peloMenu=true");
		}

		return retorno;
	}

}
