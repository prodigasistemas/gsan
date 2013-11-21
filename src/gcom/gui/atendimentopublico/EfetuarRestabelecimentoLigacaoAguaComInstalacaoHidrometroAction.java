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
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.medicao.MedicaoTipo;
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
 * Description of the Class
 * 
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 */
public class EfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroAction extends GcomAction {

	/**
	 * Description of the Method
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

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);

		EfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm = (EfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		String ordemServicoId = efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
				.getIdOrdemServico();

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();
		hidrometroInstalacaoHistorico = this
				.setDadosHidrometroInstalacaoHistorico(
						hidrometroInstalacaoHistorico,
						efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm);
		
		Imovel imovel = null;

		if (ordemServicoId != null && !ordemServicoId.equals("")) {

			OrdemServico ordemServico = (OrdemServico) sessao
					.getAttribute("ordemServico");

			if (ordemServico == null) {
				throw new ActionServletException(
						"atencao.ordem_servico_inexistente", null,
						"ORDEM DE SERVIÇO INEXISTENTE");
			}

			if (sessao.getAttribute("imovel") != null) {
				imovel = (Imovel) sessao.getAttribute("imovel");
				
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovel.getId()));
				
				Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
				
				Imovel imovelComLocalidade = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
				
				if (imovelComLocalidade != null && imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem() != null &&
					hidrometroInstalacaoHistorico.getHidrometro().getHidrometroLocalArmazenagem() != null && 
					!hidrometroInstalacaoHistorico.getHidrometro().getHidrometroLocalArmazenagem().getId().equals(imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem().getId())) {
						throw new ActionServletException("atencao.hidrometro_local_armazenagem_imovel_diferente_hidrometro_local_armazenagem_hidrometro");
				}
				
				imovel.setUltimaAlteracao(new Date());
			}
			
			FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua ();
			filtroLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroLigacaoAgua.ID, imovel.getId()));
			
			Collection colecaoLigacoesAgua = fachada.pesquisar(filtroLigacaoAgua, LigacaoAgua.class.getName());
			
			LigacaoAgua ligacaoAgua = (LigacaoAgua) Util.retonarObjetoDeColecao(colecaoLigacoesAgua);
			
			ligacaoAgua
					.setDataRestabelecimentoAgua(Util
							.converteStringParaDate(efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
									.getDataRestabelecimento()));

			hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);
			
			
			ordemServico = this.setDadosOrdemServico(ordemServico, efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm);
			
			String qtdParcelas = efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
					.getQuantidadeParcelas();

			IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

			integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
			integracaoComercialHelper.setImovel(imovel);
			integracaoComercialHelper.setOrdemServico(ordemServico);
			integracaoComercialHelper.setQtdParcelas(qtdParcelas);
			integracaoComercialHelper.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);

			if (efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
					.getVeioEncerrarOS().equalsIgnoreCase("FALSE")) {
				integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);

				fachada.efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometro(integracaoComercialHelper, usuario);
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
				// Monta a página de sucesso
				montarPaginaSucesso(httpServletRequest,
						"Restabelecimento da Ligação de Água com Instalação de Hidrômetro efetuada com Sucesso",
						"Efetuar outro Restabelecimento da Ligação de Água com Instalação de Hidrômetro",
						"exibirEfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroAction.do?menu=sim");
			}

			return retorno;
		} else {
			throw new ActionServletException("atencao.informe_campo", null,
					"Ordem de Serviço");
		}
	}

	// [SB0003] - Atualizar Ordem de Serviço
	//
	// Método responsável por setar os dados da ordem de serviço
	// de acordo com as exigências do caso de uso
	public OrdemServico setDadosOrdemServico(
			OrdemServico ordemServico,
			EfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm) {

		String idServicoMotivoNaoCobranca = efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
				.getMotivoNaoCobranca();
		String valorPercentual = efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
				.getPercentualCobranca();

		if (ordemServico != null
				&& efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
						.getIdTipoDebito() != null) {

			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

			ordemServico.setIndicadorComercialAtualizado(new Short("1"));

			if (idServicoMotivoNaoCobranca != null
					&& !idServicoMotivoNaoCobranca
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
				servicoNaoCobrancaMotivo.setId(new Integer(
						idServicoMotivoNaoCobranca));
			}

			ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

			if (valorPercentual != null) {
				ordemServico.setPercentualCobranca(new BigDecimal(
						efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
								.getPercentualCobranca()));
			}
			
			ordemServico
			.setDataEncerramento(Util
					.converteStringParaDate(efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
							.getDataRestabelecimento()));
			ordemServico.setUltimaAlteracao(new Date());

		}

		BigDecimal valorAtual = new BigDecimal(0);
		if (efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
				.getValorDebito() != null) {
			String valorDebito = efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
					.getValorDebito().toString().replace(".", "");

			valorDebito = valorDebito.replace(",", ".");

			valorAtual = new BigDecimal(valorDebito);

			ordemServico.setValorAtual(valorAtual);
		}

		return ordemServico;
	}

	// [SB0004] - Gerar Histórico de Instalação do Hidrômetro
	//
	// Método responsável por setar os dados do hidrômetro instalação histórico
	// de acordo com os dados selecionados pelo usuário e pelas exigências do
	// caso de uso
	public HidrometroInstalacaoHistorico setDadosHidrometroInstalacaoHistorico(
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico,
			EfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm) {

		Fachada fachada = Fachada.getInstancia();

		String numeroHidrometro = efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
				.getNumeroHidrometro();

		if (numeroHidrometro != null) {
			// Pesquisa o Hidrômetro
			Hidrometro hidrometro = fachada.pesquisarHidrometroPeloNumero(numeroHidrometro);
			
			if (hidrometro == null) {
				throw new ActionServletException(
						"atencao.hidrometro_inexistente");
			}
			
			hidrometroInstalacaoHistorico.setHidrometro(hidrometro);
		}

		// Data instalação
		if (efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
				.getDataInstalacao() != null
				&& !efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
						.getDataInstalacao().equals("")) {

			hidrometroInstalacaoHistorico
					.setDataInstalacao(Util
							.converteStringParaDate(efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
									.getDataInstalacao()));

		}
		
		// Medição tipo
		MedicaoTipo medicaoTipo = new MedicaoTipo();
		medicaoTipo.setId(MedicaoTipo.LIGACAO_AGUA);
		hidrometroInstalacaoHistorico.setMedicaoTipo(medicaoTipo);

		// hidrômetro local instalação
		HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
		hidrometroLocalInstalacao.setId(Integer
				.parseInt(efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
						.getLocalInstalacao()));
		hidrometroInstalacaoHistorico
				.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);

		// proteção
		HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
		hidrometroProtecao.setId(Integer
				.parseInt(efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
						.getProtecao()));
		hidrometroInstalacaoHistorico.setHidrometroProtecao(hidrometroProtecao);

		// leitura instalação
		if (efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
				.getLeituraInstalacao() != null
				&& !efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
						.getLeituraInstalacao().trim().equals("")) {
			hidrometroInstalacaoHistorico
					.setNumeroLeituraInstalacao(Integer
							.parseInt(efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
									.getLeituraInstalacao()));
		} else {
			hidrometroInstalacaoHistorico.setNumeroLeituraInstalacao(0);
		}

		// cavalete
		hidrometroInstalacaoHistorico.setIndicadorExistenciaCavalete(Short
				.parseShort(efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
						.getSituacaoCavalete()));

		/*
		 * Campos opcionais
		 */

		// data da retirada
		hidrometroInstalacaoHistorico.setDataRetirada(null);
		// leitura retirada
		hidrometroInstalacaoHistorico.setNumeroLeituraRetirada(null);
		// leitura corte
		hidrometroInstalacaoHistorico.setNumeroLeituraCorte(null);
		// leitura supressão
		hidrometroInstalacaoHistorico.setNumeroLeituraSupressao(null);
		// numero selo
		if (efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.getNumeroSelo() != null
				&& !efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
						.getNumeroSelo().equals("")) {
			hidrometroInstalacaoHistorico
					.setNumeroSelo(efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
							.getNumeroSelo());
		} else {
			hidrometroInstalacaoHistorico.setNumeroSelo(null);
		}
		// tipo de rateio
		hidrometroInstalacaoHistorico.setRateioTipo(null);
		hidrometroInstalacaoHistorico.setDataImplantacaoSistema(new Date());

		// indicador instalação substituição
		hidrometroInstalacaoHistorico
				.setIndicadorInstalcaoSubstituicao(new Short("1"));

		// data última alteração
		hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());

		return hidrometroInstalacaoHistorico;

	}
}
