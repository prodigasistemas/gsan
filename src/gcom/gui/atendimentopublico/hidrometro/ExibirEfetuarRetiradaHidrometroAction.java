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
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.FiltroHidrometroSituacao;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de inserir bairro
 * 
 * @author Thiago Tenório
 * @created 28 de Junho de 2004
 */
public class ExibirEfetuarRetiradaHidrometroAction extends GcomAction {
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

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("efetuarRetiradaHidrometro");
		
		EfetuarRetiradaHidrometroActionForm retiradaActionForm = 
			(EfetuarRetiradaHidrometroActionForm) actionForm;
		
		
		Fachada fachada = Fachada.getInstancia();

		Boolean veioEncerrarOS = null;
		if(httpServletRequest.getAttribute("veioEncerrarOS") != null){
			veioEncerrarOS = Boolean.TRUE;
		}else{
			if (retiradaActionForm.getVeioEncerrarOS() != null
					&& !retiradaActionForm
							.getVeioEncerrarOS().equals("")) {
				if (retiradaActionForm.getVeioEncerrarOS()
						.toLowerCase().equals("true")) {
					veioEncerrarOS = veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = veioEncerrarOS = Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Variavel responsavél pelo preenchimento do imovel no formulário
		String idOrdemServico = null;
		if(retiradaActionForm.getIdOrdemServico() != null){
			idOrdemServico = retiradaActionForm.getIdOrdemServico();
		}else{
			idOrdemServico = (String)httpServletRequest.getAttribute("veioEncerrarOS");
			retiradaActionForm.setDataRetirada(
					(String) httpServletRequest.getAttribute("dataEncerramento"));
				
				sessao.setAttribute("caminhoRetornoIntegracaoComercial",
					httpServletRequest.getAttribute("caminhoRetornoIntegracaoComercial"));
		}
		
		if(httpServletRequest.getAttribute("semMenu") != null || sessao.getAttribute("semMenu")!=null){
			sessao.setAttribute("semMenu", "SIM");
		}else{
			sessao.removeAttribute("semMenu");
		}
		
		this.pesquisarSelectObrigatorio(httpServletRequest);
		
		OrdemServico ordemServico = null;
		
		sessao.setAttribute("veioEncerrarOS",veioEncerrarOS);
		
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

			ordemServico = 
				fachada.recuperaOSPorId(new Integer(idOrdemServico));

			if (ordemServico != null) {
				
				fachada.validarExibirRetiradaHidrometroAgua(ordemServico,veioEncerrarOS);
				retiradaActionForm.setIdOrdemServico(idOrdemServico);
				retiradaActionForm.setVeioEncerrarOS(""+veioEncerrarOS);
				retiradaActionForm.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
				
				if (ordemServico.getDataEncerramento() != null) {
					retiradaActionForm.setDataRetirada(Util.formatarData(ordemServico
									.getDataEncerramento()));
				}
				
				Imovel imovel = null;
				if (ordemServico.getRegistroAtendimento() != null && 
					ordemServico.getRegistroAtendimento().getImovel() != null) {
					
					imovel = ordemServico.getRegistroAtendimento().getImovel();
					
				} else if (ordemServico.getImovel() != null) {
					imovel = ordemServico.getImovel();
				}

				sessao.setAttribute("ordemServico", ordemServico);
				
				String matriculaImovel = imovel.getId().toString();
				retiradaActionForm.setMatriculaImovel(matriculaImovel);

				// Inscrição Imóvel
				String inscricaoImovel = fachada
						.pesquisarInscricaoImovel(imovel.getId());
				retiradaActionForm
						.setInscricaoImovel(inscricaoImovel);

				// Cliente Usuário
				this.pesquisarCliente(retiradaActionForm);
				
				// Situação da Ligação de Agua
				String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
				retiradaActionForm.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

				// Situação da Ligação de Esgoto
				String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
				retiradaActionForm.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);
				
				HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;
				FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
				// Hidrômetro
/*				HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;
				if (imovel.getHidrometroInstalacaoHistorico() != null) {
					hidrometroInstalacaoHistorico =imovel.getHidrometroInstalacaoHistorico();
				}else{
					hidrometroInstalacaoHistorico =ligacaoAgua.getHidrometroInstalacaoHistorico();
				}
			
				sessao.setAttribute("hidrometroInstalacaoHistorico", hidrometroInstalacaoHistorico);*/
				
				// Tipo medição - Ligação Água
				if (ordemServico.getRegistroAtendimento() == null ||
						ordemServico.getRegistroAtendimento()
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

					retiradaActionForm
							.setMedicaoTipo(MedicaoTipo.LIGACAO_AGUA.toString());

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

					retiradaActionForm
							.setMedicaoTipo(MedicaoTipo.POCO.toString());

					sessao.setAttribute("hidrometroInstalacaoHistorico",
							hidrometroInstalacaoHistorico);

				}
				
				// Hidrometro
				retiradaActionForm.setHidrometro(hidrometroInstalacaoHistorico.getHidrometro().getNumero());
				
/*				String hidrometro = hidrometroInstalacaoHistorico.getHidrometro().getNumero();
				retiradaActionForm.setHidrometro(hidrometro);
				
				if(hidrometroInstalacaoHistorico.getHidrometro() != null
						&& hidrometroInstalacaoHistorico.getHidrometro().getHidrometroLocalArmazenagem() != null){
					retiradaActionForm.setHidrometroLocalArmazenagem(
							""+hidrometroInstalacaoHistorico.getHidrometro().getHidrometroLocalArmazenagem().getId());
				}

			// medicao tipo
				if (ordemServico.getRegistroAtendimento()
						.getSolicitacaoTipoEspecificacao()
						.getIndicadorLigacaoAgua()
						.equals(ConstantesSistema.SIM)) {
					retiradaActionForm
							.setMedicaoTipo("1");
				} else {
					retiradaActionForm
							.setMedicaoTipo("2");
				}*/


/*				if (hidrometroInstalacaoHistorico.getDataRetirada() != null) {
					
					Date dataRetirada = hidrometroInstalacaoHistorico.getDataRetirada();
					retiradaActionForm.setDataRetirada(Util.formatarData(dataRetirada));
				} */

				// Número da Leitura
				if (hidrometroInstalacaoHistorico.getNumeroLeituraRetirada() != null) {
					retiradaActionForm.setNumeroLeitura(
							hidrometroInstalacaoHistorico.getNumeroLeituraRetirada().toString());
				}
				
				if(ordemServico.getRegistroAtendimento() == null ||
					ordemServico.getRegistroAtendimento()
							.getSolicitacaoTipoEspecificacao()
							.getIndicadorLigacaoAgua().equals(ConstantesSistema.SIM)){
						
						retiradaActionForm.setMedicaoTipo("LIGAÇÂO DE ÁGUA");
					}else{
						retiradaActionForm.setMedicaoTipo("POÇO");
					}	
				
				
				
				if (ordemServico.getServicoTipo().getDebitoTipo() != null) {
					retiradaActionForm
							.setIdTipoDebito(ordemServico.getServicoTipo()
									.getDebitoTipo().getId()
									+ "");
					retiradaActionForm
							.setDescricaoTipoDebito(ordemServico
									.getServicoTipo().getDebitoTipo()
									.getDescricao());
				}
				
				
				//[FS0013] - Alteração de Valor
				this.permitirAlteracaoValor(ordemServico.getServicoTipo(), retiradaActionForm);
				
				String calculaValores = httpServletRequest.getParameter("calculaValores");
				
				BigDecimal valorDebito = new BigDecimal(0);
				SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
				Integer qtdeParcelas = null;
				
				if(calculaValores != null && calculaValores.equals("S")){
					
					//[UC0186] - Calcular Prestação
					BigDecimal  taxaJurosFinanciamento = null; 
					qtdeParcelas = new Integer(retiradaActionForm.getQuantidadeParcelas());
					
					if(ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM.shortValue() && 
						qtdeParcelas.intValue() != 1){
						
						taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
					}else{
						taxaJurosFinanciamento = new BigDecimal(0);
					}
					
					BigDecimal valorPrestacao = null;
					if(taxaJurosFinanciamento != null){
						
						valorDebito = new BigDecimal(retiradaActionForm.getValorDebito().replace(",","."));
						
						String percentualCobranca = retiradaActionForm.getPercentualCobranca();
						
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
						retiradaActionForm.setValorParcelas(valorPrestacaoComVirgula);
					} else {
						retiradaActionForm.setValorParcelas("0,00");
					}						
					
				}else{				

					// Valor Débito
					valorDebito = fachada.obterValorDebito(ordemServico
							.getServicoTipo().getId(),
							new Integer(matriculaImovel), new Short("1"));
					if (valorDebito != null) {
						retiradaActionForm
								.setValorDebito(Util.formatarMoedaReal(valorDebito));
					} else {
						retiradaActionForm
								.setValorDebito("0");
					}
				}
				// Filtro para o campo Tpo Debito
				Collection colecaoNaoCobranca = (Collection) sessao
						.getAttribute("colecaoNaoCobranca");
				if (colecaoNaoCobranca == null) {
					FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();

					filtroServicoNaoCobrancaMotivo
							.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

					colecaoNaoCobranca = fachada.pesquisar(
							filtroServicoNaoCobrancaMotivo,
							ServicoNaoCobrancaMotivo.class.getName());

					if (colecaoNaoCobranca != null
							&& !colecaoNaoCobranca.isEmpty()) {
						sessao.setAttribute("colecaoNaoCobranca",
								colecaoNaoCobranca);
					} else {
						throw new ActionServletException(
								"atencao.naocadastrado", null,
								"Motivo da Não Cobrança");
					}
				}
				retiradaActionForm
						.setQtdeMaxParcelas(sistemaParametro
								.getNumeroMaximoParcelasFinanciamento()
								+ "");

				// -----------------------------------------------------------
				// Verificar permissão especial
				boolean temPermissaoMotivoNaoCobranca = fachada.verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
				// -----------------------------------------------------------
				
				if (temPermissaoMotivoNaoCobranca) {
					httpServletRequest.setAttribute("permissaoMotivoNaoCobranca", temPermissaoMotivoNaoCobranca);
				}else{
					retiradaActionForm.setPercentualCobranca("100");
					retiradaActionForm.setQuantidadeParcelas("1");
					retiradaActionForm.setValorParcelas(Util.formataBigDecimal(valorDebito,2,true));
				}	
			} else {
				httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
				retiradaActionForm.setIdOrdemServico("");
				retiradaActionForm.setNomeOrdemServico("ORDEM DE SERVIÇO INEXISTENTE");
			}

		}
		
		/*
//		 Parte em que é Verificado se o hidrometro esta extraviado.
		String idSituacaoHidrometro = (String) retiradaActionForm.getIdHidrometroSituacao();
		
		if(idSituacaoHidrometro!= null && !idSituacaoHidrometro.equals("")
				&& !idSituacaoHidrometro.equals("-1")){
			
		HidrometroSituacao hidrometroSituacao = new HidrometroSituacao(); 
		FiltroHidrometroSituacao filtroHidrometroSituacao = new FiltroHidrometroSituacao();
		
		filtroHidrometroSituacao.adicionarParametro(new ParametroSimples(FiltroHidrometroSituacao.ID,idSituacaoHidrometro));
		
		Collection colecaoHidrometroSituacao =  fachada.pesquisar(filtroHidrometroSituacao,
				HidrometroSituacao.class.getName());
		
		hidrometroSituacao = (HidrometroSituacao) colecaoHidrometroSituacao
		.iterator().next();
		
		Integer hidrometroExtraviado = null;
		if(hidrometroSituacao != null){
		hidrometroExtraviado = new Integer(hidrometroSituacao.getExtraviado().toString()); 
		}
		
		// hidrometroExtraviado==1, significa que o hidrometro esta extraviado,
		// como mostra o campo "hist_ichidrometroextraviado" da tabela "micromedicao.hidrometro_situacao".
		if(hidrometroExtraviado.intValue() == HidrometroSituacao.EXTRAVIADO.intValue()){
			httpServletRequest.setAttribute("hidrometroExtravido","sim");
			sessao.setAttribute("hidrometroExtravido","sim");
			retiradaActionForm.setHidrometroExtraviado("sim");
		}
		else{
			sessao.removeAttribute("hidrometroExtravido");
			retiradaActionForm.setHidrometroExtraviado("nao");
		}
		}
//		 Fim da Verificacao se o hidrometro esta extraviado.
		*/
 
		
		return retorno;
	}
	
	
	/*
	 * [FS0013 - Alteração de Valor]
	 * 
	 * autor: Raphael Rossiter
	 * data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo, EfetuarRetiradaHidrometroActionForm form){
		
		if (servicoTipo.getIndicadorPermiteAlterarValor() == 
			ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()){
			
			form.setAlteracaoValor("OK");
		}
		else{
			form.setAlteracaoValor("");
		}
		
	}
	

	/**
	 * Pesquisa o hidrometro local de armazenagem
	 * */
	private void pesquisarSelectObrigatorio(HttpServletRequest httpServletRequest){

//		 Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Pesquisando hidrometro local armazenagem
		FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();
		
		filtroHidrometroLocalArmazenagem.setCampoOrderBy(FiltroHidrometroLocalArmazenagem.DESCRICAO);
		filtroHidrometroLocalArmazenagem.adicionarParametro(
			new ParametroSimples(
					FiltroHidrometroLocalArmazenagem.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		
		
		Collection colecaoHidrometroLocalArmazenagem = 
			Fachada.getInstancia().pesquisar(filtroHidrometroLocalArmazenagem, 
					HidrometroLocalArmazenagem.class.getName());

		if (colecaoHidrometroLocalArmazenagem == null || colecaoHidrometroLocalArmazenagem.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Local de Armazenagem do Hidrômetro");
		}

		
		httpServletRequest.setAttribute("colecaoHidrometroLocalArmazenagem", colecaoHidrometroLocalArmazenagem);
		
		if(sessao.getAttribute("colecaoHidrometroSituacao") == null){
		
			FiltroHidrometroSituacao filtroHidrometroSituacao = new FiltroHidrometroSituacao();
			
			filtroHidrometroSituacao.setCampoOrderBy(FiltroHidrometroSituacao.DESCRICAO);
			
			filtroHidrometroSituacao.adicionarParametro(new ParametroSimples(FiltroHidrometroSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection colecaoHidrometroSituacao = Fachada.getInstancia().pesquisar(filtroHidrometroSituacao, HidrometroSituacao.class.getName());
			
			if (colecaoHidrometroLocalArmazenagem == null || colecaoHidrometroLocalArmazenagem.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Local de Armazenagem do Hidrômetro");
			}
			
			sessao.setAttribute("colecaoHidrometroSituacao",colecaoHidrometroSituacao);
		}
		
//		Filtro para o campo Tpo Debito
		Collection colecaoNaoCobranca = (Collection) 
			sessao.getAttribute("colecaoNaoCobranca");
		if(colecaoNaoCobranca == null){
			FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();
			
			filtroServicoNaoCobrancaMotivo.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);
			
			colecaoNaoCobranca = Fachada.getInstancia().pesquisar(filtroServicoNaoCobrancaMotivo, ServicoNaoCobrancaMotivo.class.getName());
			
			if (colecaoNaoCobranca != null && !colecaoNaoCobranca.isEmpty()) {
				sessao.setAttribute("colecaoNaoCobranca",colecaoNaoCobranca);
			} else {
				throw new ActionServletException("atencao.naocadastrado",null, "Motivo da Não Cobrança");
			}
		}
	//	SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
	//	retiradaActionForm.setQtdeMaxParcelas(sistemaParametro.getNumeroMaximoParcelasFinanciamento()+"");
	}
	
	/**
	 * Pesquisa Cliente 
	 *
	 * @author Rafael Pinto
	 * @date 01/09/2006
	 */
	private void pesquisarCliente(EfetuarRetiradaHidrometroActionForm efetuarRetiradaHidrometroActionForm) {
		
		//Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(
			new ParametroSimples(FiltroClienteImovel.IMOVEL_ID,
					efetuarRetiradaHidrometroActionForm.getMatriculaImovel()));

		filtroClienteImovel
			.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,ClienteRelacaoTipo.USUARIO));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection colecaoClienteImovel = 
			Fachada.getInstancia().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());

		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

			ClienteImovel clienteImovel = 
				(ClienteImovel) colecaoClienteImovel.iterator().next();
			
			Cliente cliente = clienteImovel.getCliente();

			String documento = "";

			if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				documento = cliente.getCpfFormatado();
			} else {
				documento = cliente.getCnpjFormatado();
			}
			//Cliente Nome/CPF-CNPJ
			efetuarRetiradaHidrometroActionForm.setClienteUsuario(cliente.getNome());
			efetuarRetiradaHidrometroActionForm.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Cliente");
		}
	}
	
}