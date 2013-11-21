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
package gcom.gui.atendimentopublico.hidrometro;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroProtecao;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
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
 * @date 28/06/2006
 */
public class ExibirEfetuarRemanejamentoHidrometroAction extends GcomAction {

	/**
	 * [UC0365] Efetuar Remanejamento de Hidrômetro
	 * 
	 * Este caso de uso permite efetuar o remanejamento de hidrometro, sendo
	 * chamado pela funcionalidade que encerra a execução da ordem de serviço ou
	 * chamada diretamente do Menu.
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
		ActionForward retorno = actionMapping
				.findForward("efetuarRemanejamentoHidrometro");

		EfetuarRemanejamentoHidrometroActionForm efetuarRemanejamentoHidrometroActionForm = (EfetuarRemanejamentoHidrometroActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		Boolean veioEncerrarOS = null;
		if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			if (efetuarRemanejamentoHidrometroActionForm.getVeioEncerrarOS() != null
					&& !efetuarRemanejamentoHidrometroActionForm
							.getVeioEncerrarOS().equals("")) {
				if (efetuarRemanejamentoHidrometroActionForm.getVeioEncerrarOS()
						.toLowerCase().equals("true")) {
					veioEncerrarOS = veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = veioEncerrarOS = Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		String idOrdemServico = null;

		// Verifica se o id da Ordem de servico vem da sessao.
		if (efetuarRemanejamentoHidrometroActionForm.getIdOrdemServico() != null) {
			idOrdemServico = efetuarRemanejamentoHidrometroActionForm
					.getIdOrdemServico();
		} else {
			idOrdemServico = (String) httpServletRequest
					.getAttribute("veioEncerrarOS");
			efetuarRemanejamentoHidrometroActionForm
					.setDataInstalacao(
					(String) httpServletRequest.getAttribute("dataEncerramento"));
				
				sessao.setAttribute("caminhoRetornoIntegracaoComercial",
					httpServletRequest.getAttribute("caminhoRetornoIntegracaoComercial"));
		}

		if (httpServletRequest.getAttribute("semMenu") != null) {
			sessao.setAttribute("semMenu", "SIM");
		} else {
			sessao.removeAttribute("semMenu");
		}

		OrdemServico ordemServico = null;

		this.pesquisarSelectObrigatorio(httpServletRequest);

		// Ordem de Serviço
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

			ordemServico = fachada.recuperaOSPorId(new Integer(idOrdemServico));

			if (ordemServico != null) {

				fachada.validarExibirRemanejmentoHidrometroAgua(ordemServico,
						veioEncerrarOS);

				sessao.setAttribute("ordemServico", ordemServico);
				efetuarRemanejamentoHidrometroActionForm.setIdOrdemServico(idOrdemServico);
				efetuarRemanejamentoHidrometroActionForm.setVeioEncerrarOS(""
						+ veioEncerrarOS);
				efetuarRemanejamentoHidrometroActionForm
						.setNomeOrdemServico(ordemServico.getServicoTipo()
								.getDescricao());


				Imovel imovel = null;
				if (ordemServico.getRegistroAtendimento() != null && 
					ordemServico.getRegistroAtendimento().getImovel() != null) {
					
					imovel = ordemServico.getRegistroAtendimento().getImovel();
					
				} else if (ordemServico.getImovel() != null) {
					imovel = ordemServico.getImovel();
				}

				if (ordemServico.getServicoTipo() != null
						&& ordemServico.getServicoTipo().getDebitoTipo() != null) {
					efetuarRemanejamentoHidrometroActionForm
							.setIdTipoDebito(ordemServico.getServicoTipo()
									.getDebitoTipo().getId().toString());
					efetuarRemanejamentoHidrometroActionForm
							.setDescricaoTipoDebito(ordemServico
									.getServicoTipo().getDebitoTipo()
									.getDescricao());

					if (ordemServico.getServicoNaoCobrancaMotivo() != null) {
						efetuarRemanejamentoHidrometroActionForm
								.setMotivoNaoCobranca(ordemServico
										.getServicoNaoCobrancaMotivo().getId()
										.toString());
					}

					if (ordemServico.getPercentualCobranca() != null) {
						efetuarRemanejamentoHidrometroActionForm
								.setPercentualCobranca(ordemServico
										.getPercentualCobranca().toString());
					}
				}

				sessao.setAttribute("imovel", imovel);

				String matriculaImovel = imovel.getId().toString();
				efetuarRemanejamentoHidrometroActionForm.setMatriculaImovel(""
						+ matriculaImovel);

				/*-------------- Início dados do Imóvel---------------*/

//				sessao.setAttribute("imovel", ordemServico
//						.getRegistroAtendimento().getImovel());

				if (imovel != null) {

					// Matricula Imóvel
					efetuarRemanejamentoHidrometroActionForm
							.setMatriculaImovel(imovel.getId().toString());

					// Inscrição Imóvel
					String inscricaoImovel = fachada
							.pesquisarInscricaoImovel(imovel.getId());
					efetuarRemanejamentoHidrometroActionForm
							.setInscricaoImovel(inscricaoImovel);

					// Situação da Ligação de Agua
					String situacaoLigacaoAgua = imovel
							.getLigacaoAguaSituacao().getDescricao();
					efetuarRemanejamentoHidrometroActionForm
							.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

					// Situação da Ligação de Esgoto
					String situacaoLigacaoEsgoto = imovel
							.getLigacaoEsgotoSituacao().getDescricao();
					efetuarRemanejamentoHidrometroActionForm
							.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

					this.pesquisarCliente(
							efetuarRemanejamentoHidrometroActionForm,
							new Integer(matriculaImovel));
				}

				// Situação da Ligação de Agua
				String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao()
						.getDescricao();
				efetuarRemanejamentoHidrometroActionForm
						.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

				// Situação da Ligação de Esgoto
				String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();

				efetuarRemanejamentoHidrometroActionForm
						.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

				// LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();

				HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;
				FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
				// Tipo medição - Ligação Água
				if (ordemServico.getRegistroAtendimento() == null || ordemServico.getRegistroAtendimento()
						.getSolicitacaoTipoEspecificacao()
						.getIndicadorLigacaoAgua().equals(
								MedicaoTipo.LIGACAO_AGUA.shortValue())) {
					LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();

					if (ligacaoAgua.getHidrometroInstalacaoHistorico() == null) {
						throw new ActionServletException(
								"atencao.hidrometro_instalado_nao_existente",
								null, " na Ligação de Água ");
					}

					filtroHidrometroInstalacaoHistorico
							.adicionarParametro(new ParametroSimples(
									FiltroHidrometroInstalacaoHistorico.ID,
									ligacaoAgua
											.getHidrometroInstalacaoHistorico()
											.getId()));

					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("hidrometroProtecao");

					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("hidrometro");
					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("rateioTipo");
					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalInstalacao");
					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("medicaoTipo");

					Collection colecaoHidrometroInstalacaoHistorico = fachada
							.pesquisar(filtroHidrometroInstalacaoHistorico,
									HidrometroInstalacaoHistorico.class
											.getName());

					hidrometroInstalacaoHistorico = (HidrometroInstalacaoHistorico) colecaoHidrometroInstalacaoHistorico
							.iterator().next();

					efetuarRemanejamentoHidrometroActionForm
							.setTipoMedicao(MedicaoTipo.LIGACAO_AGUA.toString());

					sessao.setAttribute("hidrometroInstalacaoHistorico",
							hidrometroInstalacaoHistorico);

				}

				// Tipo medição- Poço
				else {

					
					if (imovel.getHidrometroInstalacaoHistorico() == null) {
						throw new ActionServletException(
								"atencao.hidrometro_instalado_nao_existente",
								null, " no Poço ");
					}
					
					filtroHidrometroInstalacaoHistorico
							.adicionarParametro(new ParametroSimples(
									FiltroHidrometroInstalacaoHistorico.ID,
									imovel.getHidrometroInstalacaoHistorico()
											.getId()));

					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("hidrometroProtecao");

					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("hidrometro");
					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("rateioTipo");
					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalInstalacao");
					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
					filtroHidrometroInstalacaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("medicaoTipo");

					Collection colecaoHidrometroInstalacaoHistorico = fachada
							.pesquisar(filtroHidrometroInstalacaoHistorico,
									HidrometroInstalacaoHistorico.class
											.getName());

					hidrometroInstalacaoHistorico = (HidrometroInstalacaoHistorico) colecaoHidrometroInstalacaoHistorico
							.iterator().next();

					efetuarRemanejamentoHidrometroActionForm
							.setTipoMedicao(MedicaoTipo.POCO.toString());

					sessao.setAttribute("hidrometroInstalacaoHistorico",
							hidrometroInstalacaoHistorico);

				}

				// Hidrometro

				efetuarRemanejamentoHidrometroActionForm
						.setHidrometro(hidrometroInstalacaoHistorico
								.getHidrometro().getNumero());

				// Data de Remanejamento
				Date dataInstalacao = ordemServico.getDataEncerramento();
				if(dataInstalacao != null && !dataInstalacao.equals("")){
					efetuarRemanejamentoHidrometroActionForm.setDataInstalacao(Util
						.formatarData(dataInstalacao));
				}

				// Tipo Medição
				if (hidrometroInstalacaoHistorico.getMedicaoTipo() != null) {

					String medicaoTipo = hidrometroInstalacaoHistorico
							.getMedicaoTipo().getId().toString();
					efetuarRemanejamentoHidrometroActionForm
							.setTipoMedicao(medicaoTipo);

				}

				// Local de Instalacao
				if (hidrometroInstalacaoHistorico
						.getHidrometroLocalInstalacao() != null) {

					String localInstalacao = hidrometroInstalacaoHistorico
							.getHidrometroLocalInstalacao().getId().toString();
					efetuarRemanejamentoHidrometroActionForm
							.setLocalInstalacao(localInstalacao);
				}

				// Protecao
				if (hidrometroInstalacaoHistorico.getHidrometroProtecao() != null) {

					String protecao = hidrometroInstalacaoHistorico
							.getHidrometroProtecao().getId().toString();
					efetuarRemanejamentoHidrometroActionForm
							.setProtecao(protecao);

				}

				// Cavalete
				if (hidrometroInstalacaoHistorico
						.getIndicadorExistenciaCavalete() != null) {

					String cavalete = hidrometroInstalacaoHistorico
							.getIndicadorExistenciaCavalete().toString();
					efetuarRemanejamentoHidrometroActionForm
							.setTipoCavalete(cavalete);

				}
				// medicao tipo
				if (ordemServico.getRegistroAtendimento() == null || ordemServico.getRegistroAtendimento()
						.getSolicitacaoTipoEspecificacao()
						.getIndicadorLigacaoAgua()
						.equals(ConstantesSistema.SIM)) {
					efetuarRemanejamentoHidrometroActionForm
							.setTipoMedicao("1");
				} else {
					efetuarRemanejamentoHidrometroActionForm
							.setTipoMedicao("2");
				}

				// Dados da Geração de Débito
				//this.pesquisarDadosGeracaoDebito(efetuarRemanejamentoHidrometroActionForm, ordemServico);
				FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
				
				filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");

				filtroServicoTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoTipo.ID, ordemServico.getServicoTipo().getId()));

				Collection colecaoServicoTipo = Fachada.getInstancia().pesquisar(
						filtroServicoTipo, ServicoTipo.class.getName());

				ServicoTipo servicoTipo = (ServicoTipo) colecaoServicoTipo.iterator()
						.next();

				// Filtro para carregar o Cliente
				if (servicoTipo.getDebitoTipo() != null){
				
					FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

					filtroDebitoTipo.adicionarParametro(new ParametroSimples(
							FiltroDebitoTipo.ID, servicoTipo.getDebitoTipo().getId()
									.toString()));

					Collection colecaoDebitoTipo = Fachada.getInstancia().pesquisar(
							filtroDebitoTipo, DebitoTipo.class.getName());

					DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo.iterator()
							.next();

					if (debitoTipo.getId() != null && !debitoTipo.getId().equals("")) {

						// Codigo/descricao
						efetuarRemanejamentoHidrometroActionForm.setIdTipoDebito(debitoTipo
								.getId().toString());
						efetuarRemanejamentoHidrometroActionForm
								.setDescricaoTipoDebito(debitoTipo.getDescricao());
					} else {
						// Codigo/descricao
						efetuarRemanejamentoHidrometroActionForm.setIdTipoDebito("");
						efetuarRemanejamentoHidrometroActionForm.setDescricaoTipoDebito("");

					}
				}
				
				
				//[FS0013] - Alteração de Valor
				this.permitirAlteracaoValor(ordemServico.getServicoTipo(), efetuarRemanejamentoHidrometroActionForm);
				
				// Colocado por Raphael Rossiter em 04/05/2007 (Analista: Rosana Carvalho)
				BigDecimal valorDebito = this.calcularValores(httpServletRequest, ordemServico, servicoTipo, 
				efetuarRemanejamentoHidrometroActionForm, imovel);
				
				
				// -----------------------------------------------------------
				// Verificar permissão especial
				boolean temPermissaoMotivoNaoCobranca = fachada.verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
				// -----------------------------------------------------------
				
				if (temPermissaoMotivoNaoCobranca) {
					httpServletRequest.setAttribute("permissaoMotivoNaoCobranca", temPermissaoMotivoNaoCobranca);
				}else{
					efetuarRemanejamentoHidrometroActionForm.setPercentualCobranca("100");
					efetuarRemanejamentoHidrometroActionForm.setQuantidadeParcelas("1");
					efetuarRemanejamentoHidrometroActionForm.setValorParcelas(Util.formataBigDecimal(valorDebito,2,true));
				}
			} else {
				httpServletRequest
						.setAttribute("OrdemServicoInexistente", true);
				efetuarRemanejamentoHidrometroActionForm.setIdOrdemServico("");
				efetuarRemanejamentoHidrometroActionForm
						.setNomeOrdemServico("ORDEM DE SERVIÇO INEXISTENTE");

			}

		}

		return retorno;
	}
	
	
	/*
	 * [FS0013 - Alteração de Valor]
	 * 
	 * autor: Raphael Rossiter
	 * data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo, EfetuarRemanejamentoHidrometroActionForm form){
		
		if (servicoTipo.getIndicadorPermiteAlterarValor() == 
			ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()){
			
			form.setAlteracaoValor("OK");
		}
		else{
			form.setAlteracaoValor("");
		}
		
	}
	
	/*
	 * Calcular valor da prestação com juros
	 * 
	 * return: Retorna o valor total do débito
	 * 
	 * autor: Raphael Rossiter
	 * data: 04/05/2007
	 */
	private BigDecimal calcularValores(HttpServletRequest httpServletRequest, OrdemServico ordemServico,
			ServicoTipo servicoTipo, EfetuarRemanejamentoHidrometroActionForm form, Imovel imovel){
		
		String calculaValores = httpServletRequest.getParameter("calculaValores");
		
		BigDecimal valorDebito = new BigDecimal(0);
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		Integer qtdeParcelas = null;
		
		if(calculaValores != null && calculaValores.equals("S")){
			
			//[UC0186] - Calcular Prestação
			BigDecimal  taxaJurosFinanciamento = null; 
			qtdeParcelas = new Integer(form.getQuantidadeParcelas());
			
			if(ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM.shortValue() && 
					qtdeParcelas.intValue() > 1){
				
				taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
			}else{
				taxaJurosFinanciamento = new BigDecimal(0);
				qtdeParcelas = 1;
			}
			
			BigDecimal valorPrestacao = null;
			if(taxaJurosFinanciamento != null){
				
				valorDebito = new BigDecimal(form.getValorDebito().replace(",","."));
				
				String percentualCobranca = form.getPercentualCobranca();
				
				if(percentualCobranca.equals("70")){
					valorDebito = valorDebito.multiply(new BigDecimal(0.7));
				}else if (percentualCobranca.equals("50")){
					valorDebito = valorDebito.multiply(new BigDecimal(0.5));
				}
				
				valorPrestacao =
					this.getFachada().calcularPrestacao(
						taxaJurosFinanciamento,
						qtdeParcelas, 
						valorDebito, 
						new BigDecimal("0.00"));
				
				valorPrestacao.setScale(2,BigDecimal.ROUND_HALF_UP);
			}
			
			if (valorPrestacao != null) {
				String valorPrestacaoComVirgula = Util.formataBigDecimal(valorPrestacao,2,true);
				form.setValorParcelas(valorPrestacaoComVirgula);
			} else {
				form.setValorParcelas("0,00");
			}						
			
		}else{
			
			short tipoMedicao = 1;
			valorDebito = Fachada.getInstancia().obterValorDebito(servicoTipo.getId(),
			imovel.getId(), tipoMedicao);

			form.setValorDebito(Util.formataBigDecimal(valorDebito, 2, true));
		}
		
		
		return valorDebito;
	}

	/**
	 * Pesquisa o local de instalação Pesquisa hidrometro proteção
	 */
	private void pesquisarSelectObrigatorio(
			HttpServletRequest httpServletRequest) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Local de Instalaçao
		FiltroHidrometroLocalInstalacao filtroHidrometroLocalInstalacao = new FiltroHidrometroLocalInstalacao();

		Collection<HidrometroLocalInstalacao> colecaoHidrometroLocalInstalacao = Fachada
				.getInstancia().pesquisar(filtroHidrometroLocalInstalacao,
						HidrometroLocalInstalacao.class.getName());

		if (colecaoHidrometroLocalInstalacao == null
				|| colecaoHidrometroLocalInstalacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela hidrômetro local de instalação ");
		}

		httpServletRequest.setAttribute("colecaoHidrometroLocalInstalacao",
				colecaoHidrometroLocalInstalacao);

		// Hidrometro Protecao
		FiltroHidrometroProtecao filtroHidrometroProtecao = new FiltroHidrometroProtecao();

		Collection<HidrometroProtecao> colecaoHidrometroProtecao = Fachada
				.getInstancia().pesquisar(filtroHidrometroProtecao,
						HidrometroProtecao.class.getName());

		if (colecaoHidrometroProtecao == null
				|| colecaoHidrometroProtecao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela hidrômetro proteção ");
		}

		httpServletRequest.setAttribute("colecaoHidrometroProtecao",
				colecaoHidrometroProtecao);

		// Filtro para o campo Tpo Debito
		Collection colecaoNaoCobranca = (Collection) sessao
				.getAttribute("colecaoNaoCobranca");
		if (colecaoNaoCobranca == null) {
			FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();

			filtroServicoNaoCobrancaMotivo
					.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

			colecaoNaoCobranca = Fachada.getInstancia().pesquisar(
					filtroServicoNaoCobrancaMotivo,
					ServicoNaoCobrancaMotivo.class.getName());

			if (colecaoNaoCobranca != null && !colecaoNaoCobranca.isEmpty()) {
				sessao.setAttribute("colecaoNaoCobranca", colecaoNaoCobranca);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Motivo da Não Cobrança");
			}
		}
	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Pinto
	 * @date 22/08/2006
	 */
	private void pesquisarCliente(
			EfetuarRemanejamentoHidrometroActionForm efetuarRemanejamentoHidrometroActionForm,
			Integer matriculaImovel) {

		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID, matriculaImovel));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
				ClienteRelacaoTipo.USUARIO));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.DATA_FIM_RELACAO));

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection colecaoClienteImovel = Fachada.getInstancia().pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());

		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

			ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel
					.iterator().next();

			Cliente cliente = clienteImovel.getCliente();

			String documento = "";

			if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				documento = cliente.getCpfFormatado();
			} else {
				documento = cliente.getCnpjFormatado();
			}
			// Cliente Nome/CPF-CNPJ
			efetuarRemanejamentoHidrometroActionForm.setClienteUsuario(cliente
					.getNome());
			efetuarRemanejamentoHidrometroActionForm
					.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}
	}

}
