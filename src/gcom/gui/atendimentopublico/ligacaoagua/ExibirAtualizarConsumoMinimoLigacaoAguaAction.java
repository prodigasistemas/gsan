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

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de atualizar consumo mínimo de
 * ligação de água.
 * 
 * @author Leonardo Regis
 * @date 30/08/2006
 */
public class ExibirAtualizarConsumoMinimoLigacaoAguaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarConsumoMinimoLigacaoAgua");
		HttpSession sessao = httpServletRequest.getSession(false);
		AtualizarConsumoMinimoLigacaoAguaActionForm atualizarConsumoMinimoLigacaoAguaActionForm = (AtualizarConsumoMinimoLigacaoAguaActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		Boolean veioEncerrarOS = null;
		if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			if (atualizarConsumoMinimoLigacaoAguaActionForm.getVeioEncerrarOS() != null
					&& !atualizarConsumoMinimoLigacaoAguaActionForm
							.getVeioEncerrarOS().equals("")) {
				if (atualizarConsumoMinimoLigacaoAguaActionForm.getVeioEncerrarOS()
						.toLowerCase().equals("true")) {
					veioEncerrarOS = veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = veioEncerrarOS = Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}
		atualizarConsumoMinimoLigacaoAguaActionForm.setVeioEncerrarOS(""
				+ veioEncerrarOS);

		// Variavel responsavél pelo preenchimento do imovel no formulário
		String idOrdemServico = null;

		if (atualizarConsumoMinimoLigacaoAguaActionForm.getIdOrdemServico() != null) {
			idOrdemServico = atualizarConsumoMinimoLigacaoAguaActionForm
					.getIdOrdemServico();
		} else {
			idOrdemServico = (String) httpServletRequest
					.getAttribute("veioEncerrarOS");

			sessao.setAttribute("caminhoRetornoIntegracaoComercial",
					httpServletRequest
							.getAttribute("caminhoRetornoIntegracaoComercial"));
		}

		if (httpServletRequest.getAttribute("semMenu") != null) {
			sessao.setAttribute("semMenu", "SIM");
		} else {
			sessao.removeAttribute("semMenu");
		}

		if (idOrdemServico != null && !idOrdemServico.equals("")) {
			OrdemServico ordemServico = fachada.recuperaOSPorId(new Integer(
					idOrdemServico));
			if (ordemServico != null && !ordemServico.equals("")) {
				fachada.validarExibirAtualizarConsumoMinimoLigacaoAgua(
						ordemServico, veioEncerrarOS);
				atualizarConsumoMinimoLigacaoAguaActionForm.setIdOrdemServico(idOrdemServico);
				// Preenchar dados da ordem de servico
				atualizarConsumoMinimoLigacaoAguaActionForm
						.setNomeOrdemServico(ordemServico.getServicoTipo()
								.getDescricao());
				// Preencher dados do imovel
				this.preencherDadosImovel(
						atualizarConsumoMinimoLigacaoAguaActionForm,
						ordemServico);
				// Preencher dados do Cliente
				this.pesquisarCliente(
						atualizarConsumoMinimoLigacaoAguaActionForm,
						ordemServico);
				sessao.setAttribute("osEncontrada", "true");
			} else {
				sessao.removeAttribute("osEncontrada");
				atualizarConsumoMinimoLigacaoAguaActionForm
						.setNomeOrdemServico("Ordem de Serviço inexistente");
			}
		} else {
			httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
			atualizarConsumoMinimoLigacaoAguaActionForm
					.setDataConcorrencia(new Date());
		}
		return retorno;
	}
	
	
	/*
	 * [FS0013 - Alteração de Valor]
	 * 
	 * autor: Raphael Rossiter
	 * data: 19/04/2007
	 */
/*	private void permitirAlteracaoValor(ServicoTipo servicoTipo, EfetuarLigacaoAguaComInstalacaoHidrometroActionForm form){
		
		if (servicoTipo.getIndicadorPermiteAlterarValor() == 
			ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()){
			
			form.setAlteracaoValor("OK");
		}
		else{
			form.setAlteracaoValor("");
		}
		
	}*/
	

	/**
	 * Preencher dados do imóvel
	 * 
	 * @author Leonardo Regis
	 * @date 30/08/2006
	 */
	private void preencherDadosImovel(
			AtualizarConsumoMinimoLigacaoAguaActionForm atualizarVolumeMinimoLigacaoEsgotoActionForm,
			OrdemServico ordemServico) {
		Fachada fachada = Fachada.getInstancia();
		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
		// Matricula Imóvel
		atualizarVolumeMinimoLigacaoEsgotoActionForm.setMatriculaImovel(imovel
				.getId().toString());
		// Inscrição Imóvel
		String inscricaoImovel = fachada.pesquisarInscricaoImovel(imovel
				.getId());
		atualizarVolumeMinimoLigacaoEsgotoActionForm
				.setInscricaoImovel(inscricaoImovel);
		// Situação da Ligação de Agua
		String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao()
				.getDescricao();
		atualizarVolumeMinimoLigacaoEsgotoActionForm
				.setSituacaoLigacaoAgua(situacaoLigacaoAgua);
		// Situação da Ligação de Esgoto
		String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao()
				.getDescricao();
		atualizarVolumeMinimoLigacaoEsgotoActionForm
				.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);
		// Categoria do Imovel
		Categoria categoria = fachada.obterDescricoesCategoriaImovel(imovel);
		atualizarVolumeMinimoLigacaoEsgotoActionForm
				.setCategoriaImovel(categoria.getDescricao());
		// Quatidade de Economias
		int qtdeconomias = fachada.obterQuantidadeEconomias(imovel);
		atualizarVolumeMinimoLigacaoEsgotoActionForm
				.setQtdeEconomia(qtdeconomias + "");
		// Consumo Mínimo
		if (imovel.getLigacaoAgua().getNumeroConsumoMinimoAgua() != null
				&& !imovel.getLigacaoAgua().getNumeroConsumoMinimoAgua()
						.equals("")) {
			atualizarVolumeMinimoLigacaoEsgotoActionForm
					.setConsumoMinimoFixado(imovel.getLigacaoAgua()
							.getNumeroConsumoMinimoAgua()
							+ "");
		} else {
			atualizarVolumeMinimoLigacaoEsgotoActionForm
					.setConsumoMinimoFixado("");
		}
		// Valor Obtido
		String valorObtido = fachada.obterConsumoMinimoLigacao(imovel, null)
				+ "";
		atualizarVolumeMinimoLigacaoEsgotoActionForm
				.setValorObtido(valorObtido);
		// Consumo Tarifa
		atualizarVolumeMinimoLigacaoEsgotoActionForm.setConsumoTarifaId(imovel
				.getConsumoTarifa().getId()
				+ "");
	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Leonardo Regis
	 * @date 30/08/2006
	 */
	private void pesquisarCliente(
			AtualizarConsumoMinimoLigacaoAguaActionForm form,
			OrdemServico ordemServico) {

		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID, ordemServico
						.getRegistroAtendimento().getImovel().getId()));
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
			form.setClienteUsuario(cliente.getNome());
			form.setCpfCnpjCliente(documento);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}

	}
}