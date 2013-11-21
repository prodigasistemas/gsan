/**
 * 
 */
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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.SupressaoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.SupressaoMotivo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 14/07/2006
 */
public class EfetuarSupressaoLigacaoAguaAction extends GcomAction {

	/**
	 * [UC0360] Efetuar Supressao de Água
	 * 
	 * Este caso de uso permite efetuar supressão da ligação de água, sendo
	 * chamada pela funcionalidade que encerra a execução da ordem de serviço ou
	 * chamada diretamente do menu.
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		EfetuarSupressaoLigacaoAguaActionForm efetuarSupressaoLigacaoAguaActionForm = (EfetuarSupressaoLigacaoAguaActionForm) actionForm;

		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

		OrdemServico ordemServico = (OrdemServico) sessao
				.getAttribute("ordemServico");

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		integracaoComercialHelper.setUsuarioLogado(usuario);

		// Ordem de serviço

		String idOrdemServico = efetuarSupressaoLigacaoAguaActionForm
				.getIdOrdemServico();
		String motivoSupressao = efetuarSupressaoLigacaoAguaActionForm
				.getMotivoSupressao();
		String numeroLeituraSupressao = efetuarSupressaoLigacaoAguaActionForm
				.getNumeroLeituraSupressao();
		String tipoSupressao = efetuarSupressaoLigacaoAguaActionForm
				.getTipoSupressao();
		String numeroSeloSupressao = efetuarSupressaoLigacaoAguaActionForm
				.getNumeroSeloSupressao();
		String indicadorTipoSupressao = efetuarSupressaoLigacaoAguaActionForm
				.getIndicadorTipoSupressao();
		String dataEncerramento = efetuarSupressaoLigacaoAguaActionForm
				.getDataSupressao();
		String idServicoMotivoNaoCobranca = efetuarSupressaoLigacaoAguaActionForm
				.getMotivoNaoCobranca();
		String valorPercentual = efetuarSupressaoLigacaoAguaActionForm
				.getPercentualCobranca();

		// Comentado por Raphael Rossiter em 28/02/2007
		// Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
		Imovel imovel = ordemServico.getImovel();

		FiltroImovel filtroImovel = new FiltroImovel();

		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				imovel.getId()));

		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");

		Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class
				.getName());

		imovel = (Imovel) colecaoImovel.iterator().next();

		// [SB0001] - Atualizar Ligacao Agua

		// validar Data Corte
		Date dataSupressao = null;
		if (dataEncerramento != null && !dataEncerramento.equals("")) {
			dataSupressao = Util.converteStringParaDate(dataEncerramento);
		} else {
			throw new ActionServletException("atencao.required", null,
					" Data do Corte");
		}

		SupressaoTipo supressaoTipo = new SupressaoTipo();
		supressaoTipo.setId(new Integer(tipoSupressao));

		LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();

		if (indicadorTipoSupressao.equals("1")) {
			ligacaoAguaSituacao.setId(LigacaoAguaSituacao.SUPRIMIDO);

			supressaoTipo
					.setIndicadorTotal(ConstantesSistema.INDICADOR_USO_ATIVO);
		} else {
			ligacaoAguaSituacao.setId(LigacaoAguaSituacao.SUPR_PARC_PEDIDO);
			supressaoTipo
					.setIndicadorParcial(ConstantesSistema.INDICADOR_USO_ATIVO);
		}

		SupressaoMotivo supressaoMotivo = new SupressaoMotivo();
		supressaoMotivo.setId(new Integer(motivoSupressao));

		FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade(FiltroLigacaoAgua.HIDROMETRO_INSTALACAO_HISTORICO);
		filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAgua.ID, imovel.getLigacaoAgua().getId()));

		Collection colecaoLigacaoAgua = fachada.pesquisar(filtroLigacaoAgua,
				LigacaoAgua.class.getName());

		filtroLigacaoAgua
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico");

		LigacaoAgua ligacaoAgua = (LigacaoAgua) colecaoLigacaoAgua.iterator()
				.next();

		if (ligacaoAgua.getHidrometroInstalacaoHistorico() != null) {
			FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();

			filtroHidrometroInstalacaoHistorico
					.adicionarParametro(new ParametroSimples(
							FiltroHidrometroInstalacaoHistorico.ID, ligacaoAgua
									.getHidrometroInstalacaoHistorico().getId()));

			filtroHidrometroInstalacaoHistorico
					.adicionarCaminhoParaCarregamentoEntidade("imovel");

			filtroHidrometroInstalacaoHistorico
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroProtecao");
			filtroHidrometroInstalacaoHistorico
					.adicionarCaminhoParaCarregamentoEntidade("hidrometro");
			filtroHidrometroInstalacaoHistorico
					.adicionarCaminhoParaCarregamentoEntidade("rateioTipo");
			filtroHidrometroInstalacaoHistorico
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
			filtroHidrometroInstalacaoHistorico
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalInstalacao");

			filtroHidrometroInstalacaoHistorico
					.adicionarCaminhoParaCarregamentoEntidade("rateioTipo");

			Collection colecaoHidrometroInstalacaHistorico = fachada.pesquisar(
					filtroHidrometroInstalacaoHistorico,
					HidrometroInstalacaoHistorico.class.getName());

			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = (HidrometroInstalacaoHistorico) colecaoHidrometroInstalacaHistorico
					.iterator().next();

			if (hidrometroInstalacaoHistorico != null
					&& numeroLeituraSupressao != null) {

				if (numeroLeituraSupressao != null
						&& !numeroLeituraSupressao.equals("")) {
					hidrometroInstalacaoHistorico
							.setNumeroLeituraSupressao(new Integer(
									numeroLeituraSupressao));
				}

				hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());

				ligacaoAgua
						.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
			}
			
			integracaoComercialHelper
					.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);

		}
		ligacaoAgua.setDataSupressao(dataSupressao);
		if (numeroSeloSupressao != null && !numeroSeloSupressao.equals("")) {
			ligacaoAgua
					.setNumeroSeloSupressao(new Integer(numeroSeloSupressao));
		}

		// ligacaoAgua.setDataSupressao(ordemServico.getDataEncerramento());
		ligacaoAgua.setSupressaoTipo(supressaoTipo);
		ligacaoAgua.setSupressaoMotivo(supressaoMotivo);
		ligacaoAgua.setUltimaAlteracao(new Date());

		imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
		imovel.setLigacaoAgua(ligacaoAgua);
		imovel.setUltimaAlteracao(new Date());

		BigDecimal valorAtual = new BigDecimal(0);

		if (ordemServico != null
				&& efetuarSupressaoLigacaoAguaActionForm.getIdTipoDebito() != null) {

			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

			ordemServico.setIndicadorComercialAtualizado(ConstantesSistema.SIM);

			if (efetuarSupressaoLigacaoAguaActionForm.getValorDebito() != null) {
				String valorDebito = efetuarSupressaoLigacaoAguaActionForm
						.getValorDebito().toString().replace(".", "");

				valorDebito = valorDebito.replace(",", ".");

				valorAtual = new BigDecimal(valorDebito);

				ordemServico.setValorAtual(valorAtual);
			}

			if (idServicoMotivoNaoCobranca != null
					&& !idServicoMotivoNaoCobranca.equals("-1")) {
				servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
				servicoNaoCobrancaMotivo.setId(new Integer(
						idServicoMotivoNaoCobranca));
			}
			ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

			if (valorPercentual != null) {
				ordemServico.setPercentualCobranca(new BigDecimal(
						efetuarSupressaoLigacaoAguaActionForm
								.getPercentualCobranca()));
			}

			ordemServico.setUltimaAlteracao(new Date());
		}

		String qtdParcelas = efetuarSupressaoLigacaoAguaActionForm
				.getQuantidadeParcelas();

		integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);

		integracaoComercialHelper.setImovel(imovel);
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setQtdParcelas(qtdParcelas);

		if (efetuarSupressaoLigacaoAguaActionForm.getVeioEncerrarOS()
				.equalsIgnoreCase("FALSE")) {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);

			fachada.efetuarSupressaoLigacaoAgua(integracaoComercialHelper);
		} else {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);
			sessao.setAttribute("integracaoComercialHelper",
					integracaoComercialHelper);

			if (sessao.getAttribute("semMenu") == null) {
				retorno = actionMapping
						.findForward("encerrarOrdemServicoAction");
			} else {
				retorno = actionMapping
						.findForward("encerrarOrdemServicoPopupAction");
			}
			sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
		}
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest,
					"Supressão da Ligação de Água para o imóvel "
							+ imovel.getId() + " efetuada com sucesso.",
					"Efetuar outra Supressão da Ligação de Água",
					"exibirEfetuarSupressaoLigacaoAguaAction.do?menu=sim",
					"exibirEfetuarSupressaoLigacaoAguaAction.do?idOrdemServico="
							+ idOrdemServico + "",
					"Atualizar Supressão de Ligação de Água");
		}

		return retorno;

	}
}