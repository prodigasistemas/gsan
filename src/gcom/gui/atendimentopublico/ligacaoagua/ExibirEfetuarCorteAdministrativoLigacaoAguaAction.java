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
package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.FiltroCorteTipo;
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
 * Action responsável pela pre-exibição da pagina de efetuar corte de ligação de
 * água
 * 
 * @author Thiago Tenório
 * @created 20 de Junho de 2006
 */
public class ExibirEfetuarCorteAdministrativoLigacaoAguaAction extends
		GcomAction {
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

		HttpSession sessao = getSessao(httpServletRequest);
		ActionForward retorno = actionMapping
				.findForward("efetuarCorteAdministrativoLigacaoAgua");

		EfetuarCorteAdministrativoLigacaoAguaActionForm corteAdministrativoLigacaoAguaActionForm = (EfetuarCorteAdministrativoLigacaoAguaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		Boolean veioEncerrarOS = null;
		if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			if (corteAdministrativoLigacaoAguaActionForm.getVeioEncerrarOS() != null
					&& !corteAdministrativoLigacaoAguaActionForm
							.getVeioEncerrarOS().equals("")) {
				if (corteAdministrativoLigacaoAguaActionForm.getVeioEncerrarOS()
						.toLowerCase().equals("true")) {
					veioEncerrarOS = veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = veioEncerrarOS = Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}
		
		if (corteAdministrativoLigacaoAguaActionForm.getReset().equals("true")) {
			corteAdministrativoLigacaoAguaActionForm.reset();
		}

		getTipoCorteCollection(sessao);

		String idOrdemServico = null;
		if(corteAdministrativoLigacaoAguaActionForm.getIdOrdemServico() != null){
			idOrdemServico = corteAdministrativoLigacaoAguaActionForm.getIdOrdemServico();
		}else{
			idOrdemServico = (String)httpServletRequest.getAttribute("veioEncerrarOS");
			corteAdministrativoLigacaoAguaActionForm.setDataCorte((String) httpServletRequest
					.getAttribute("dataEncerramento"));
			sessao.setAttribute("caminhoRetornoIntegracaoComercial",httpServletRequest
					.getAttribute("caminhoRetornoIntegracaoComercial"));
		}
		
		if(httpServletRequest.getAttribute("semMenu") != null){
			sessao.setAttribute("semMenu", "SIM");
		}else{
			sessao.removeAttribute("semMenu");
		}
		
		OrdemServico ordemServico = null;
		
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

			ordemServico = fachada.recuperaOSPorId(new Integer(idOrdemServico));

			if (ordemServico != null) {

				fachada.validarExibirCorteAdministrativoLigacaoAgua(
						ordemServico, veioEncerrarOS);

				sessao.setAttribute("ordemServico", ordemServico);

				corteAdministrativoLigacaoAguaActionForm.setVeioEncerrarOS(""
						+ veioEncerrarOS);

				corteAdministrativoLigacaoAguaActionForm.setIdOrdemServico(""
						+ ordemServico.getId());
				corteAdministrativoLigacaoAguaActionForm
						.setNomeOrdemServico(ordemServico.getServicoTipo()
								.getDescricao());

				
				//Comentado por Raphael Rossiter em 28/02/2007
				//Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
				Imovel imovel = ordemServico.getImovel();

				/*
				 * Validar campo Leitura do Corte Verefica se existe hidrometro
				 * na ligacao de água, pois se true o usuário poderar ou não
				 * informar o número de leitura do corte, mas se false a caixa
				 * de texto será desabilitada
				 */
				if (imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null) {
					corteAdministrativoLigacaoAguaActionForm
							.setHidrometro(imovel.getLigacaoAgua()
									.getHidrometroInstalacaoHistorico().getId()
									.toString());
				}

				// Matricula Imovel
				String matriculaImovel = imovel.getId().toString();
				corteAdministrativoLigacaoAguaActionForm
						.setMatriculaImovel(matriculaImovel);

				// Inscrição Imóvel
				String inscricaoImovel = fachada
						.pesquisarInscricaoImovel(imovel.getId());
				corteAdministrativoLigacaoAguaActionForm
						.setInscricaoImovel(inscricaoImovel);
				
				corteAdministrativoLigacaoAguaActionForm
				.setMatriculaImovel(matriculaImovel);

				// Cliente Usuário
				this.pesquisarCliente(corteAdministrativoLigacaoAguaActionForm);

				// Situação da Ligação de Agua
				String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao()
						.getDescricao();
				corteAdministrativoLigacaoAguaActionForm
						.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

				// Situação da Ligação de Esgoto
				String situacaoLigacaoEsgoto = imovel
						.getLigacaoEsgotoSituacao().getDescricao();
				corteAdministrativoLigacaoAguaActionForm
						.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

				Date dataCorte = ordemServico.getDataEncerramento();
				if(dataCorte != null && !dataCorte.equals("")){
				  corteAdministrativoLigacaoAguaActionForm.setDataCorte(""
						+ Util.formatarData(dataCorte));
				}

				if (imovel != null && imovel.getLigacaoAgua() != null
						&& imovel.getLigacaoAgua().getCorteTipo() != null) {
					corteAdministrativoLigacaoAguaActionForm.setTipoCorte(""
							+ imovel.getLigacaoAgua().getCorteTipo().getId());
				}

				if (ordemServico.getServicoTipo().getDebitoTipo() != null) {
					corteAdministrativoLigacaoAguaActionForm
							.setIdTipoDebito(ordemServico.getServicoTipo()
									.getDebitoTipo().getId()
									+ "");
					corteAdministrativoLigacaoAguaActionForm
							.setDescricaoTipoDebito(ordemServico
									.getServicoTipo().getDebitoTipo()
									.getDescricao());
				}else{
					corteAdministrativoLigacaoAguaActionForm.setIdTipoDebito("");
					corteAdministrativoLigacaoAguaActionForm.setDescricaoTipoDebito("");
				}
				
				
				//[FS0013] - Alteração de Valor
				this.permitirAlteracaoValor(ordemServico.getServicoTipo(), corteAdministrativoLigacaoAguaActionForm);
				
				String calculaValores = httpServletRequest.getParameter("calculaValores");
				
				BigDecimal valorDebito = new BigDecimal(0);
				SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
				Integer qtdeParcelas = null;
				
				if(calculaValores != null && calculaValores.equals("S")){
					
					//[UC0186] - Calcular Prestação
					BigDecimal  taxaJurosFinanciamento = null; 
					qtdeParcelas = new Integer(corteAdministrativoLigacaoAguaActionForm.getQuantidadeParcelas());
					
					if(ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM.shortValue() && 
						qtdeParcelas.intValue() != 1){
						
						taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
					}else{
						taxaJurosFinanciamento = new BigDecimal(0);
					}
					
					BigDecimal valorPrestacao = null;
					if(taxaJurosFinanciamento != null){
						
						valorDebito = new BigDecimal(corteAdministrativoLigacaoAguaActionForm.getValorDebito().replace(",","."));
						
						String percentualCobranca = corteAdministrativoLigacaoAguaActionForm.getPercentualCobranca();
						
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
						corteAdministrativoLigacaoAguaActionForm.setValorParcelas(valorPrestacaoComVirgula);
					} else {
						corteAdministrativoLigacaoAguaActionForm.setValorParcelas("0,00");
					}						
					
				}else{
					// Valor Débito
					valorDebito = fachada.obterValorDebito(ordemServico
							.getServicoTipo().getId(),
							new Integer(matriculaImovel), new Short("1"));
					
					if (valorDebito != null) {
						corteAdministrativoLigacaoAguaActionForm
								.setValorDebito(valorDebito + "");
					} else {
						corteAdministrativoLigacaoAguaActionForm
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

				corteAdministrativoLigacaoAguaActionForm
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
					corteAdministrativoLigacaoAguaActionForm.setPercentualCobranca("100");
					corteAdministrativoLigacaoAguaActionForm.setQuantidadeParcelas("1");
					corteAdministrativoLigacaoAguaActionForm.setValorParcelas(Util.formataBigDecimal(valorDebito,2,true));
				}
			} else {
				corteAdministrativoLigacaoAguaActionForm.setNomeOrdemServico("Ordem de Serviço inexistente");
				corteAdministrativoLigacaoAguaActionForm.setIdOrdemServico("");
			}
		} else {
			httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
			corteAdministrativoLigacaoAguaActionForm.reset();
		}
		return retorno;
	}
	
	
	/*
	 * [FS0013 - Alteração de Valor]
	 * 
	 * autor: Raphael Rossiter
	 * data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo, EfetuarCorteAdministrativoLigacaoAguaActionForm form){
		
		if (servicoTipo.getIndicadorPermiteAlterarValor() == 
			ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()){
			
			form.setAlteracaoValor("OK");
		}
		else{
			form.setAlteracaoValor("");
		}
		
	}
	

	private void getTipoCorteCollection(HttpSession sessao) {
		// Filtro para o campo Motivo do Corte
		FiltroCorteTipo filtroTipoCorteLigacaoAgua = new FiltroCorteTipo();
		filtroTipoCorteLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroCorteTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroTipoCorteLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroCorteTipo.INDICADOR_CORTE_ADMINISTRATIVO, ConstantesSistema.SIM));
		filtroTipoCorteLigacaoAgua.setCampoOrderBy(FiltroCorteTipo.DESCRICAO);

		Collection colecaoTipoCorteLigacaoAgua = Fachada.getInstancia().pesquisar(filtroTipoCorteLigacaoAgua, CorteTipo.class.getName());
		if (colecaoTipoCorteLigacaoAgua != null && !colecaoTipoCorteLigacaoAgua.isEmpty()) {
			sessao.setAttribute("colecaoTipoCorteLigacaoAgua",colecaoTipoCorteLigacaoAgua);
		} else {
			throw new ActionServletException("atencao.naocadastrado",null, "Tipo do Corte");
		}
	}	


	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Pinto
	 * @date 25/08/2006
	 */
	private void pesquisarCliente(
			EfetuarCorteAdministrativoLigacaoAguaActionForm corteAdministrativoLigacaoAguaActionForm) {

		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID,
				corteAdministrativoLigacaoAguaActionForm.getMatriculaImovel()));

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
			corteAdministrativoLigacaoAguaActionForm.setClienteUsuario(cliente
					.getNome());
			corteAdministrativoLigacaoAguaActionForm
					.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}
	}

}