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
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroProtecao;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
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
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 */
public class ExibirEfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Fachada fachada = Fachada.getInstancia();
		
		// -----------------------------------------------------------
		// Verificar permissão especial
		boolean temPermissaoMotivoNaoCobranca = fachada.verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
		// -----------------------------------------------------------

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometro");

		EfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm = 
			(EfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm) actionForm;

		if (httpServletRequest.getParameter("desfazer") != null) {
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setSituacaoCavalete(null);
		}
		
		Boolean veioEncerrarOS = null;
		
		if (httpServletRequest.getAttribute("veioEncerrarOS") != null ||
			(efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.getVeioEncerrarOS() != null &&
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.getVeioEncerrarOS().equals("true"))) {
			
			veioEncerrarOS = Boolean.TRUE;
		} else {
			if (efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.getVeioEncerrarOS() != null
					&& !efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.getVeioEncerrarOS().equals("")) {
				if (efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.getVeioEncerrarOS().toLowerCase().equals("true")) {
					veioEncerrarOS = veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = veioEncerrarOS = Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}

		efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setVeioEncerrarOS("" + veioEncerrarOS);

		this.consultaSelectObrigatorio(sessao);

		// Variavel responsavél pelo preenchimento do imovel no formulário
		String idOrdemServico = null;

		if (efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.getIdOrdemServico() != null) {
			idOrdemServico = efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.getIdOrdemServico();
		} else {
			idOrdemServico = (String) httpServletRequest.getAttribute("veioEncerrarOS");
			
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setDataRestabelecimento((String) httpServletRequest.getAttribute("dataEncerramento"));
			
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setDataInstalacao((String) httpServletRequest.getAttribute("dataEncerramento"));
			
			sessao.setAttribute("caminhoRetornoIntegracaoComercial",httpServletRequest.getAttribute("caminhoRetornoIntegracaoComercial"));
		}

		if (httpServletRequest.getAttribute("semMenu") != null) {
			sessao.setAttribute("semMenu", "SIM");
		} else {
			sessao.removeAttribute("semMenu");
		}
		OrdemServico ordemServico = null;
		String matriculaImovel = null;
		
		BigDecimal valorDebito = new BigDecimal(0.00);
		
		// [FS0001] Validar Ordem de Serviço.
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

			ordemServico = fachada.recuperaOSPorId(new Integer(idOrdemServico));

			if (ordemServico != null) {

				fachada.validarRestabelecimentoLigacaoAguaComInstalacaoHidrometroExibir(ordemServico, veioEncerrarOS);

				sessao.setAttribute("ordemServico", ordemServico);

				efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setIdOrdemServico(idOrdemServico);
				efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());

				Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
				
				ServicoTipo servicoTipo = ordemServico.getServicoTipo();
				
				//BigDecimal valorDebito = new BigDecimal(0.00);
				
				if (servicoTipo != null && servicoTipo.getDebitoTipo() != null) {

					efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setIdTipoDebito(servicoTipo.getDebitoTipo().getId().toString());
					
					efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setDescricaoTipoDebito(servicoTipo.getDebitoTipo().getDescricao());

					
					//[FS0013] - Alteração de Valor
					this.permitirAlteracaoValor(ordemServico.getServicoTipo(), 
					efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm);
					
					//Colocado por Raphael Rossiter em 04/05/2007 (Analista: Rosana Carvalho)
					valorDebito = this.calcularValores(httpServletRequest, ordemServico, 
					efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm);
					
					/*
					 * Comentado por Raphael Rossiter em 14/08/2008
					 * 
					 * valorDebito = fachada.obterValorDebito(servicoTipo.getId(), imovel.getId(), new Short(LigacaoTipo.LIGACAO_AGUA + ""));
					 * efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setValorDebito(Util.formataBigDecimal(valorDebito,2, true)); 
					 */

					if (ordemServico.getServicoNaoCobrancaMotivo() != null) {
						efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setMotivoNaoCobranca(ordemServico.getServicoNaoCobrancaMotivo().getId().toString());
					}

					if (ordemServico.getPercentualCobranca() != null) {
						efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setPercentualCobranca(ordemServico.getPercentualCobranca().toString());
					}
				}

				if (ordemServico.getDataEncerramento() != null) {
					efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setDataRestabelecimento(Util.formatarData(ordemServico.getDataEncerramento()));
					efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setDataInstalacao(Util.formatarData(ordemServico.getDataEncerramento()));
				}

				sessao.setAttribute("imovel", imovel);

				matriculaImovel = imovel.getId().toString();
				efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setMatriculaImovel("" + matriculaImovel);

				/*-------------- Início dados do Imóvel---------------*/

				sessao.setAttribute("imovel", ordemServico.getRegistroAtendimento().getImovel());

				if (imovel != null) {

					// Matricula Imóvel
					efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setMatriculaImovel(imovel.getId().toString());

					// Inscrição Imóvel
					String inscricaoImovel = fachada.pesquisarInscricaoImovel(imovel.getId());
					efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setInscricaoImovel(inscricaoImovel);

					// Situação da Ligação de Agua
					String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
					efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

					// Situação da Ligação de Esgoto
					String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
					efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

					this.pesquisarCliente(efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm,new Integer(matriculaImovel));
				}
				if (temPermissaoMotivoNaoCobranca) {
					httpServletRequest.setAttribute("permissaoMotivoNaoCobranca", temPermissaoMotivoNaoCobranca);
				}else{
					efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setPercentualCobranca("100");
					efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setQuantidadeParcelas("1");
					efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setValorParcelas(Util.formataBigDecimal(valorDebito,2,true));
				}
				
			} else {
				efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setNomeOrdemServico("Ordem de Serviço inexistente");
				efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setIdOrdemServico("");
				httpServletRequest.setAttribute("OrdemServioInexistente", true);
			}
		} else {

			httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");

			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setIdOrdemServico("");
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setMatriculaImovel("");
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setInscricaoImovel("");
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setClienteUsuario("");
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setCpfCnpjCliente("");
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setSituacaoLigacaoAgua("");
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setSituacaoLigacaoEsgoto("");
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setDataRestabelecimento("");
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setIdTipoDebito("");
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setDescricaoTipoDebito("");
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setQuantidadeParcelas("");
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setValorParcelas("");
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setPercentualCobranca("-1");
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setMotivoNaoCobranca("-1");

		}

		String numeroHidrometro = efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.getNumeroHidrometro();

		// Verificar se o número do hidrômetro não está cadastrado
		if (numeroHidrometro != null && !numeroHidrometro.trim().equals("")) {

			// Pesquisa o hidrômetro
			Hidrometro hidrometro = fachada.pesquisarHidrometroPeloNumero(numeroHidrometro);

			if (hidrometro == null) {
				efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setNumeroHidrometro("");
				throw new ActionServletException("atencao.hidrometro_inexistente");
			} else {
				
				efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setNumeroHidrometro(hidrometro.getNumero());
				
				if(ordemServico != null){

					// Valor Débito
					/*BigDecimal valorDebito = fachada.obterValorDebito(ordemServico.getServicoTipo().getId(),new Integer(matriculaImovel),hidrometro.getHidrometroCapacidade());
					
					if (valorDebito != null) {
						efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setValorDebito(Util.formataBigDecimal(valorDebito,2,true));
					} else {
						efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setValorDebito("0");
					}*/
					
					if (temPermissaoMotivoNaoCobranca) {
						httpServletRequest.setAttribute("permissaoMotivoNaoCobranca", temPermissaoMotivoNaoCobranca);
					}else{
						efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setPercentualCobranca("100");
						efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setQuantidadeParcelas("1");
						efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm.setValorParcelas(Util.formataBigDecimal(valorDebito,2,true));
					}
				}
				
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
	private void permitirAlteracaoValor(ServicoTipo servicoTipo, 
	EfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm form){
		
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
			EfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm form){
		
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
//				qtdeParcelas = 1;
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
			
			valorDebito = Fachada.getInstancia().obterValorDebito(ordemServico.getServicoTipo().getId(), 
			ordemServico.getRegistroAtendimento().getImovel().getId(), new Short(LigacaoTipo.LIGACAO_AGUA + ""));

			form.setValorDebito(Util.formataBigDecimal(valorDebito,2, true));
		}
		
		
		return valorDebito;
	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Corrêa
	 * @date 27/11/2006
	 */
	private void pesquisarCliente(
			EfetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm,
			Integer matriculaImovel) {

		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, matriculaImovel));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO,ClienteRelacaoTipo.USUARIO));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection colecaoClienteImovel = Fachada.getInstancia().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());

		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

			ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel.iterator().next();

			Cliente cliente = clienteImovel.getCliente();

			String documento = "";

			if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				documento = cliente.getCpfFormatado();
			} else {
				documento = cliente.getCnpjFormatado();
			}
			// Cliente Nome/CPF-CNPJ
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
					.setClienteUsuario(cliente.getNome());
			efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometroActionForm
					.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}
	}

	/**
	 * Monta os select´s obrigatorios
	 * 
	 * @author Rafael Corrêa
	 * @date 27/11/2006
	 */
	private void consultaSelectObrigatorio(HttpSession sessao) {

		Fachada fachada = Fachada.getInstancia();
	
		// Filtro para o campo Tipo Debito
		Collection colecaoNaoCobranca = (Collection) sessao.getAttribute("colecaoNaoCobranca");
		if (colecaoNaoCobranca == null) {
			FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();

			filtroServicoNaoCobrancaMotivo.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

			colecaoNaoCobranca = fachada.pesquisar(filtroServicoNaoCobrancaMotivo,ServicoNaoCobrancaMotivo.class.getName());

			if (colecaoNaoCobranca != null && !colecaoNaoCobranca.isEmpty()) {
				sessao.setAttribute("colecaoNaoCobranca", colecaoNaoCobranca);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Motivo da Não Cobrança");
			}
		}

		// Pesquisando local de instalação
		FiltroHidrometroLocalInstalacao filtroHidrometroLocalInstalacao = new FiltroHidrometroLocalInstalacao();
		filtroHidrometroLocalInstalacao
				.setCampoOrderBy(FiltroHidrometroLocalInstalacao.DESCRICAO);
		filtroHidrometroLocalInstalacao
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroLocalInstalacao.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoHidrometroLocalInstalacao = Fachada.getInstancia()
				.pesquisar(filtroHidrometroLocalInstalacao,
						HidrometroLocalInstalacao.class.getName());

		if (colecaoHidrometroLocalInstalacao == null
				|| colecaoHidrometroLocalInstalacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Hidrômetro local de instalação");
		}

		sessao.setAttribute("colecaoHidrometroLocalInstalacao",
				colecaoHidrometroLocalInstalacao);

		// Pesquisando proteção
		FiltroHidrometroProtecao filtroHidrometroProtecao = new FiltroHidrometroProtecao();
		filtroHidrometroProtecao
				.setCampoOrderBy(FiltroHidrometroProtecao.DESCRICAO);
		filtroHidrometroProtecao.adicionarParametro(new ParametroSimples(
				FiltroHidrometroProtecao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoHidrometroProtecao = Fachada.getInstancia()
				.pesquisar(filtroHidrometroProtecao,
						HidrometroProtecao.class.getName());

		if (colecaoHidrometroProtecao == null
				|| colecaoHidrometroProtecao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Hidrômetro proteção");
		}

		sessao.setAttribute("colecaoHidrometroProtecao",
				colecaoHidrometroProtecao);

	}

}
