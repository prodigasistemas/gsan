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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.projeto.FiltroProjeto;
import gcom.cadastro.projeto.Projeto;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.FiltroDocumentoCobranca;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0450] Pesquisar Ordem Servico - Exibir
 * 
 * @author Rafael Pinto
 * 
 * @date 14/08/2006
 */
public class ExibirPesquisarOrdemServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("ordemServicoPesquisar");

		HttpSession sessao = httpServletRequest.getSession(false);

		// Form
		PesquisarOrdemServicoActionForm pesquisarOrdemServicoActionForm = (PesquisarOrdemServicoActionForm) actionForm;

		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsultaOs = httpServletRequest
				.getParameter("objetoConsultaOs");

		// Gerando período de geração para melhorar performance - Raphael
		// Rossiter em 13/02/2007
		if ((objetoConsultaOs == null || objetoConsultaOs.trim().equals(""))
				&& (httpServletRequest.getParameter("tipoConsulta") == null || httpServletRequest
						.getParameter("tipoConsulta").equals(""))) {

			pesquisarOrdemServicoActionForm.setNumeroRA("");
			pesquisarOrdemServicoActionForm.setDescricaoRA("");
			pesquisarOrdemServicoActionForm.setDocumentoCobranca("");
			pesquisarOrdemServicoActionForm.setDescricaoDocumentoCobranca("");

			pesquisarOrdemServicoActionForm.setSituacaoOrdemServico("");
			pesquisarOrdemServicoActionForm.setSituacaoProgramacao("");

			pesquisarOrdemServicoActionForm.setMatriculaImovel("");
			pesquisarOrdemServicoActionForm.setInscricaoImovel("");
			pesquisarOrdemServicoActionForm.setCodigoCliente("");
			pesquisarOrdemServicoActionForm.setNomeCliente("");

			pesquisarOrdemServicoActionForm.setUnidadeGeracao("");
			pesquisarOrdemServicoActionForm.setDescricaoUnidadeGeracao("");

			pesquisarOrdemServicoActionForm.setUnidadeAtual("");
			pesquisarOrdemServicoActionForm.setDescricaoUnidadeAtual("");

			pesquisarOrdemServicoActionForm.setUnidadeSuperior("");
			pesquisarOrdemServicoActionForm.setDescricaoUnidadeSuperior("");

			pesquisarOrdemServicoActionForm.setPeriodoAtendimentoInicial("");
			pesquisarOrdemServicoActionForm.setPeriodoAtendimentoFinal("");
			pesquisarOrdemServicoActionForm.setPeriodoEncerramentoInicial("");
			pesquisarOrdemServicoActionForm.setPeriodoEncerramentoFinal("");
			pesquisarOrdemServicoActionForm.setPeriodoGeracaoInicial("");
			pesquisarOrdemServicoActionForm.setPeriodoGeracaoFinal("");
			pesquisarOrdemServicoActionForm.setPeriodoProgramacaoInicial("");
			pesquisarOrdemServicoActionForm.setPeriodoProgramacaoFinal("");

			pesquisarOrdemServicoActionForm.setMunicipio("");
			pesquisarOrdemServicoActionForm.setDescricaoMunicipio("");
			pesquisarOrdemServicoActionForm.setIdBairro("");
			pesquisarOrdemServicoActionForm.setBairro("");
			pesquisarOrdemServicoActionForm.setDescricaoBairro("");

			pesquisarOrdemServicoActionForm.setLogradouro("");
			pesquisarOrdemServicoActionForm.setDescricaoLogradouro("");

			// Sugerindo um período para realização da filtragem de uma OS
			// SistemaParametro sistemaParametro =
			// fachada.pesquisarParametrosDoSistema();
			Integer qtdDias = 30;

			Date dataAtual = new Date();
			Date dataSugestao = Util.subtrairNumeroDiasDeUmaData(dataAtual,
					qtdDias);

			pesquisarOrdemServicoActionForm.setPeriodoGeracaoInicial(Util
					.formatarData(dataSugestao));
			pesquisarOrdemServicoActionForm.setPeriodoGeracaoFinal(Util
					.formatarData(dataAtual));
			
			pesquisarOrdemServicoActionForm.setOrigemOrdemServico(OrdemServico.SOLICITADAS);
			
			pesquisarOrdemServicoActionForm.setProjeto(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
		}

		// [UC0443] - Pesquisar Registro Atendimento
		if (objetoConsultaOs != null && !objetoConsultaOs.trim().equals("")
				&& objetoConsultaOs.trim().equals("1")) {

			// Faz a consulta de Registro Atendimento
			this.pesquisarRegistroAtendimento(pesquisarOrdemServicoActionForm);
		}

		// [UC9999] - Pesquisar Documento de Cobrança
		if (objetoConsultaOs != null && !objetoConsultaOs.trim().equals("")
				&& objetoConsultaOs.trim().equals("2")) {

			// Faz a consulta de Documento Cobrança
			this.pesquisarDocumentoCobranca(pesquisarOrdemServicoActionForm);
		}

		// [UC0013] - Pesquisar Imovel
		if (objetoConsultaOs != null && !objetoConsultaOs.trim().equals("")
				&& objetoConsultaOs.trim().equals("3")) {

			// Faz a consulta de Imovel
			this.pesquisarImovel(pesquisarOrdemServicoActionForm);
		}

		// [UC0012] - Pesquisar Cliente
		if (objetoConsultaOs != null && !objetoConsultaOs.trim().equals("")
				&& objetoConsultaOs.trim().equals("4")) {

			// Faz a consulta de Cliente
			this.pesquisarCliente(pesquisarOrdemServicoActionForm);
		}

		// [UC0376 - Pesquisar Unidade
		if ((objetoConsultaOs != null && !objetoConsultaOs.trim().equals("") && objetoConsultaOs
				.trim().equals("5"))
				|| (objetoConsultaOs != null
						&& !objetoConsultaOs.trim().equals("") && objetoConsultaOs
						.trim().equals("6"))
				|| (objetoConsultaOs != null
						&& !objetoConsultaOs.trim().equals("") && objetoConsultaOs
						.trim().equals("7"))) {

			// Faz a consulta de Cliente
			this.pesquisarUnidadeOrganizacional(
					pesquisarOrdemServicoActionForm, objetoConsultaOs);
		}

		// [UC0075] - Pesquisar Municipio
		if (objetoConsultaOs != null && !objetoConsultaOs.trim().equals("")
				&& objetoConsultaOs.trim().equals("8")) {

			// Faz a consulta de Municipio
			this.pesquisarMunicipio(pesquisarOrdemServicoActionForm);
		}

		// [UC0141] - Pesquisar Bairro
		if (objetoConsultaOs != null && !objetoConsultaOs.trim().equals("")
				&& objetoConsultaOs.trim().equals("9")) {

			// Faz a consulta de Bairro
			this.pesquisarBairro(pesquisarOrdemServicoActionForm);
		}

		String idBairro = pesquisarOrdemServicoActionForm.getIdBairro();

		// Seleciona a partir do id do bairro informado
		if (idBairro != null && !idBairro.equals("")) {
			this.montarAreaBairroPorId(httpServletRequest,
					new Integer(idBairro));
		}

		// [UC0004] - Pesquisar Logradouro
		if (objetoConsultaOs != null && !objetoConsultaOs.trim().equals("")
				&& objetoConsultaOs.trim().equals("10")) {

			// Faz a consulta de logradouro
			this.pesquisarLogradouro(pesquisarOrdemServicoActionForm);
		}

		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {

			String id = httpServletRequest.getParameter("idCampoEnviarDados");
			String descricao = httpServletRequest
					.getParameter("descricaoCampoEnviarDados");

			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"registroAtendimento")) {

				pesquisarOrdemServicoActionForm.setNumeroRA(id);
				pesquisarOrdemServicoActionForm.setDescricaoRA(descricao);

			} else if (httpServletRequest.getParameter("tipoConsulta").equals(
					"documentoCobranca")) {

				pesquisarOrdemServicoActionForm.setDocumentoCobranca(id);
				pesquisarOrdemServicoActionForm
						.setDescricaoDocumentoCobranca(descricao);

			} else if (httpServletRequest.getParameter("tipoConsulta").equals(
					"imovel")) {

				pesquisarOrdemServicoActionForm.setMatriculaImovel(id);
				pesquisarOrdemServicoActionForm.setInscricaoImovel(descricao);

			} else if (httpServletRequest.getParameter("tipoConsulta").equals(
					"cliente")) {

				pesquisarOrdemServicoActionForm.setCodigoCliente(id);
				pesquisarOrdemServicoActionForm.setNomeCliente(descricao);

			} else if (httpServletRequest.getParameter("tipoConsulta").equals(
					"unidadeOrganizacional")) {

				if (sessao.getAttribute("tipoUnidade").equals("unidadeGeracao")) {
					pesquisarOrdemServicoActionForm.setUnidadeGeracao(id);
					pesquisarOrdemServicoActionForm
							.setDescricaoUnidadeGeracao(descricao);

				} else if (sessao.getAttribute("tipoUnidade").equals(
						"unidadeAtual")) {
					pesquisarOrdemServicoActionForm.setUnidadeAtual(id);
					pesquisarOrdemServicoActionForm
							.setDescricaoUnidadeAtual(descricao);

				} else {
					pesquisarOrdemServicoActionForm.setUnidadeSuperior(id);
					pesquisarOrdemServicoActionForm
							.setDescricaoUnidadeSuperior(descricao);
				}

			} else if (httpServletRequest.getParameter("tipoConsulta").equals(
					"municipio")) {

				pesquisarOrdemServicoActionForm.setMunicipio(id);
				pesquisarOrdemServicoActionForm
						.setDescricaoMunicipio(descricao);

			} else if (httpServletRequest.getParameter("tipoConsulta").equals(
					"bairro")) {
				
				pesquisarOrdemServicoActionForm.setBairro(id);
				pesquisarOrdemServicoActionForm.setDescricaoBairro(descricao);

			} else if (httpServletRequest.getParameter("tipoConsulta").equals(
					"logradouro")) {

				pesquisarOrdemServicoActionForm.setLogradouro(id);
				pesquisarOrdemServicoActionForm
						.setDescricaoLogradouro(descricao);

			}
		}

		// Monta a colecao de tipos Servicos
		this.pesquisarTipoServico(httpServletRequest);

		// Seta os request´s encontrados
		this.setaRequest(httpServletRequest, pesquisarOrdemServicoActionForm);

		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaOrdemServico") != null) {

			sessao
					.setAttribute(
							"caminhoRetornoTelaPesquisaOrdemServico",
							httpServletRequest
									.getParameter("caminhoRetornoTelaPesquisaOrdemServico"));

		}

		if (pesquisarOrdemServicoActionForm.getSituacaoProgramacao() == null
				|| pesquisarOrdemServicoActionForm.getSituacaoProgramacao()
						.equals("")) {

			pesquisarOrdemServicoActionForm
					.setSituacaoProgramacao(ConstantesSistema.SET_ZERO
							.toString());
		}
		
		/*
		 * Colocado por Raphael Rossiter em 15/10/2009
		 * 
		 * Permitir efetuar a pesquisa das ordens de serviço pelo projeto
		 */
		Fachada fachada = Fachada.getInstancia();
		
		if (sessao.getAttribute("colecaoProjeto") == null){
			
			FiltroProjeto filtroProjeto = new FiltroProjeto();
			
			Collection colecaoProjeto = fachada.pesquisar(filtroProjeto, Projeto.class.getName());
			
			if (colecaoProjeto != null && !colecaoProjeto.isEmpty()){
				
				sessao.setAttribute("colecaoProjeto", colecaoProjeto);
			}
		}

		return retorno;
	}

	/**
	 * Pesquisa Imóvel
	 * 
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarImovel(
			PesquisarOrdemServicoActionForm pesquisarOrdemServicoActionForm) {

		// Filtra Imovel
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				pesquisarOrdemServicoActionForm.getMatriculaImovel()));

		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");

		// Recupera Imóvel
		Collection colecaoImovel = Fachada.getInstancia().pesquisar(
				filtroImovel, Imovel.class.getName());

		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);

			pesquisarOrdemServicoActionForm.setMatriculaImovel(imovel.getId()
					.toString());
			pesquisarOrdemServicoActionForm.setInscricaoImovel(imovel
					.getInscricaoFormatada());

		} else {
			pesquisarOrdemServicoActionForm.setMatriculaImovel("");
			pesquisarOrdemServicoActionForm
					.setInscricaoImovel("Matrícula inexistente");
		}
	}

	/**
	 * Pesquisa Registro Atendimento
	 * 
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarRegistroAtendimento(
			PesquisarOrdemServicoActionForm pesquisarOrdemServicoActionForm) {

		// Filtro para obter o localidade ativo de id informado
		FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();

		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(
				FiltroRegistroAtendimento.ID, new Integer(
						pesquisarOrdemServicoActionForm.getNumeroRA())));

		filtroRegistroAtendimento
				.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacao");

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoRegistros = Fachada.getInstancia().pesquisar(
				filtroRegistroAtendimento, RegistroAtendimento.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoRegistros != null && !colecaoRegistros.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			RegistroAtendimento registroAtendimento = (RegistroAtendimento) Util
					.retonarObjetoDeColecao(colecaoRegistros);

			pesquisarOrdemServicoActionForm.setNumeroRA(registroAtendimento
					.getId().toString());
			pesquisarOrdemServicoActionForm.setDescricaoRA(registroAtendimento
					.getSolicitacaoTipoEspecificacao().getDescricao());

		} else {

			pesquisarOrdemServicoActionForm.setDescricaoRA("RA inexistente");
			pesquisarOrdemServicoActionForm.setNumeroRA("");

		}
	}

	/**
	 * Pesquisa Documento Cobrança
	 * 
	 * @author Rafael Pinto
	 * @date 21/08/2006
	 */
	private void pesquisarDocumentoCobranca(
			PesquisarOrdemServicoActionForm pesquisarOrdemServicoActionForm) {

		FiltroDocumentoCobranca filtroDocumentoCobranca = new FiltroDocumentoCobranca();

		filtroDocumentoCobranca
				.adicionarParametro(new ParametroSimples(
						FiltroDocumentoCobranca.ID, new Integer(
								pesquisarOrdemServicoActionForm
										.getDocumentoCobranca())));

		filtroDocumentoCobranca
				.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoDocumentoCobranca = Fachada.getInstancia().pesquisar(
				filtroDocumentoCobranca, CobrancaDocumento.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoDocumentoCobranca != null
				&& !colecaoDocumentoCobranca.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			CobrancaDocumento cobrancaDocumento = (CobrancaDocumento) Util
					.retonarObjetoDeColecao(colecaoDocumentoCobranca);

			pesquisarOrdemServicoActionForm
					.setDocumentoCobranca(cobrancaDocumento.getId().toString());
			pesquisarOrdemServicoActionForm
					.setDescricaoDocumentoCobranca(cobrancaDocumento
							.getDocumentoTipo().getDescricaoDocumentoTipo());

		} else {
			pesquisarOrdemServicoActionForm.setDocumentoCobranca("");
			pesquisarOrdemServicoActionForm
					.setDescricaoDocumentoCobranca("Documento Cobrança inexistente");
		}
	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarCliente(
			PesquisarOrdemServicoActionForm pesquisarOrdemServicoActionForm) {

		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente
				.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
						new Integer(pesquisarOrdemServicoActionForm
								.getCodigoCliente())));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoCliente = Fachada.getInstancia().pesquisar(
				filtroCliente, Cliente.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Cliente cliente = (Cliente) Util
					.retonarObjetoDeColecao(colecaoCliente);

			pesquisarOrdemServicoActionForm.setCodigoCliente(cliente.getId()
					.toString());
			pesquisarOrdemServicoActionForm.setNomeCliente(cliente.getNome());

		} else {
			pesquisarOrdemServicoActionForm.setCodigoCliente("");
			pesquisarOrdemServicoActionForm
					.setNomeCliente("Cliente inexistente");
		}
	}

	/**
	 * Pesquisa Unidade Organizacional
	 * 
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarUnidadeOrganizacional(
			PesquisarOrdemServicoActionForm pesquisarOrdemServicoActionForm,
			String objetoConsultaOs) {

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		Integer idUnidade = null;

		if (objetoConsultaOs.equals("5")) {
			idUnidade = new Integer(pesquisarOrdemServicoActionForm
					.getUnidadeGeracao());
		} else if (objetoConsultaOs.equals("6")) {
			idUnidade = new Integer(pesquisarOrdemServicoActionForm
					.getUnidadeAtual());
		} else {
			idUnidade = new Integer(pesquisarOrdemServicoActionForm
					.getUnidadeSuperior());

		}

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, idUnidade));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoUnidade = Fachada.getInstancia().pesquisar(
				filtroUnidadeOrganizacional,
				UnidadeOrganizacional.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util
					.retonarObjetoDeColecao(colecaoUnidade);

			if (objetoConsultaOs.equals("5")) {

				pesquisarOrdemServicoActionForm
						.setUnidadeGeracao(unidadeOrganizacional.getId()
								.toString());
				pesquisarOrdemServicoActionForm
						.setDescricaoUnidadeGeracao(unidadeOrganizacional
								.getDescricao());

			} else if (objetoConsultaOs.equals("6")) {

				pesquisarOrdemServicoActionForm
						.setUnidadeAtual(unidadeOrganizacional.getId()
								.toString());
				pesquisarOrdemServicoActionForm
						.setDescricaoUnidadeAtual(unidadeOrganizacional
								.getDescricao());

			} else {

				// [FS0009] - Verificar existência de unidades subordinadas
				filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

				filtroUnidadeOrganizacional
						.adicionarParametro(new ParametroSimples(
								FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR,
								idUnidade));

				colecaoUnidade = Fachada.getInstancia().pesquisar(
						filtroUnidadeOrganizacional,
						UnidadeOrganizacional.class.getName());

				// Verifica se a pesquisa retornou algum objeto para a coleção
				if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
					pesquisarOrdemServicoActionForm
							.setUnidadeSuperior(unidadeOrganizacional.getId()
									.toString());
					pesquisarOrdemServicoActionForm
							.setDescricaoUnidadeSuperior(unidadeOrganizacional
									.getDescricao());
				} else {
					throw new ActionServletException(
							"atencao.filtrar_ra_sem_unidades_subordinadas");
				}

			}

		} else {
			if (objetoConsultaOs.equals("5")) {

				pesquisarOrdemServicoActionForm.setUnidadeGeracao("");
				pesquisarOrdemServicoActionForm
						.setDescricaoUnidadeGeracao("Unidade de Geração inexistente");

			} else if (objetoConsultaOs.equals("6")) {

				pesquisarOrdemServicoActionForm.setUnidadeAtual("");
				pesquisarOrdemServicoActionForm
						.setDescricaoUnidadeAtual("Unidade Atual inexistente");

			} else {

				pesquisarOrdemServicoActionForm.setUnidadeSuperior("");
				pesquisarOrdemServicoActionForm
						.setDescricaoUnidadeSuperior("Unidade Superior inexistente");

			}
		}
	}

	/**
	 * Pesquisa Municipio
	 * 
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void pesquisarMunicipio(
			PesquisarOrdemServicoActionForm pesquisarOrdemServicoActionForm) {

		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroMunicipio.ID, new Integer(pesquisarOrdemServicoActionForm
						.getMunicipio())));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoMunicipio = Fachada.getInstancia().pesquisar(
				filtroMunicipio, Municipio.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Municipio municipio = (Municipio) Util
					.retonarObjetoDeColecao(colecaoMunicipio);

			pesquisarOrdemServicoActionForm.setMunicipio(municipio.getId()
					.toString());
			pesquisarOrdemServicoActionForm.setDescricaoMunicipio(municipio
					.getNome());

		} else {
			pesquisarOrdemServicoActionForm.setMunicipio("");
			pesquisarOrdemServicoActionForm
					.setDescricaoMunicipio("Município inexistente");
		}
	}

	/**
	 * Pesquisa Bairro
	 * 
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void pesquisarBairro(
			PesquisarOrdemServicoActionForm pesquisarOrdemServicoActionForm) {

		// [FS0013] - Verificar informação do munícipio
		String codigoMunicipio = pesquisarOrdemServicoActionForm.getMunicipio();
		if (codigoMunicipio == null || codigoMunicipio.equals("")) {
			throw new ActionServletException(
					"atencao.filtrar_informar_municipio");
		}

		FiltroBairro filtroBairro = new FiltroBairro();

		filtroBairro.adicionarParametro(new ParametroSimples(
				FiltroBairro.CODIGO, new Integer(
						pesquisarOrdemServicoActionForm.getBairro())));

		filtroBairro.adicionarParametro(new ParametroSimples(
				FiltroBairro.MUNICIPIO_ID, new Integer(codigoMunicipio)));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoBairro = Fachada.getInstancia().pesquisar(
				filtroBairro, Bairro.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoBairro != null && !colecaoBairro.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(colecaoBairro);

			pesquisarOrdemServicoActionForm.setBairro("" + bairro.getCodigo());
			pesquisarOrdemServicoActionForm.setIdBairro("" + bairro.getId());
			pesquisarOrdemServicoActionForm
					.setDescricaoBairro(bairro.getNome());

		} else {
			pesquisarOrdemServicoActionForm.setBairro("");
			pesquisarOrdemServicoActionForm
					.setDescricaoBairro("Bairro inexistente");
		}
	}

	/**
	 * Pesquisa Logradouro
	 * 
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void pesquisarLogradouro(
			PesquisarOrdemServicoActionForm pesquisarOrdemServicoActionForm) {

		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

		filtroLogradouro.adicionarParametro(new ParametroSimples(
				FiltroLogradouro.ID, new Integer(
						pesquisarOrdemServicoActionForm.getLogradouro())));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoLogradouro = Fachada.getInstancia().pesquisar(
				filtroLogradouro, Logradouro.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoLogradouro != null && !colecaoLogradouro.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Logradouro logradouro = (Logradouro) Util
					.retonarObjetoDeColecao(colecaoLogradouro);

			pesquisarOrdemServicoActionForm.setLogradouro(logradouro.getId()
					.toString());
			pesquisarOrdemServicoActionForm.setDescricaoLogradouro(logradouro
					.getNome());

		} else {
			pesquisarOrdemServicoActionForm.setLogradouro("");
			pesquisarOrdemServicoActionForm
					.setDescricaoLogradouro("Logradouro inexistente");
		}
	}

	/**
	 * Pesquisa Area do Bairro pelo Id
	 * 
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void montarAreaBairroPorId(HttpServletRequest request, Integer id) {

		// Parte que passa as coleções necessárias no jsp
		Collection colecaoAreaBairro = new ArrayList();

		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();
		filtroBairroArea.adicionarParametro(new ParametroSimples(
				FiltroBairroArea.ID_BAIRRO, id));

		colecaoAreaBairro = Fachada.getInstancia().pesquisar(filtroBairroArea,
				BairroArea.class.getName());

		if (colecaoAreaBairro != null && !colecaoAreaBairro.isEmpty()) {
			request.setAttribute("colecaoAreaBairro", colecaoAreaBairro);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Área do Bairro");
		}
	}

	/**
	 * Pesquisa Tipo Servico
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 */
	private void pesquisarTipoServico(HttpServletRequest httpServletRequest) {

		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

		filtroServicoTipo.setConsultaSemLimites(true);
		filtroServicoTipo.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);

		Collection colecaoTipoServico = Fachada.getInstancia().pesquisar(
				filtroServicoTipo, ServicoTipo.class.getName());

		if (colecaoTipoServico == null || colecaoTipoServico.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Tipo de Serviço");
		} else {
			httpServletRequest.setAttribute("colecaoTipoServico",
					colecaoTipoServico);
		}
	}

	/**
	 * Seta os request com os id encontrados
	 * 
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			PesquisarOrdemServicoActionForm pesquisarOrdemServicoActionForm) {

		// Imovel
		if (pesquisarOrdemServicoActionForm.getMatriculaImovel() != null
				&& !pesquisarOrdemServicoActionForm.getMatriculaImovel()
						.equals("")
				&& pesquisarOrdemServicoActionForm.getInscricaoImovel() != null
				&& !pesquisarOrdemServicoActionForm.getInscricaoImovel()
						.equals("")) {

			httpServletRequest
					.setAttribute("matriculaImovelEncontrada", "true");
		}

		// Documento Cobrança
		if (pesquisarOrdemServicoActionForm.getDocumentoCobranca() != null
				&& !pesquisarOrdemServicoActionForm.getDocumentoCobranca()
						.equals("")
				&& pesquisarOrdemServicoActionForm
						.getDescricaoDocumentoCobranca() != null
				&& !pesquisarOrdemServicoActionForm
						.getDescricaoDocumentoCobranca().equals("")) {

			httpServletRequest.setAttribute("documentoCobrancaEncontrada",
					"true");
		}

		// Registro Atendimento
		if (pesquisarOrdemServicoActionForm.getNumeroRA() != null
				&& !pesquisarOrdemServicoActionForm.getNumeroRA().equals("")
				&& pesquisarOrdemServicoActionForm.getDescricaoRA() != null
				&& !pesquisarOrdemServicoActionForm.getDescricaoRA().equals("")) {

			httpServletRequest.setAttribute("numeroRAEncontrada", "true");
		}

		// Codigo Cliente
		if (pesquisarOrdemServicoActionForm.getCodigoCliente() != null
				&& !pesquisarOrdemServicoActionForm.getCodigoCliente().equals(
						"")
				&& pesquisarOrdemServicoActionForm.getNomeCliente() != null
				&& !pesquisarOrdemServicoActionForm.getNomeCliente().equals("")) {

			httpServletRequest.setAttribute("codigoClienteEncontrada", "true");
		}

		// Unidade Geração
		if (pesquisarOrdemServicoActionForm.getUnidadeGeracao() != null
				&& !pesquisarOrdemServicoActionForm.getUnidadeGeracao().equals(
						"")
				&& pesquisarOrdemServicoActionForm.getDescricaoUnidadeGeracao() != null
				&& !pesquisarOrdemServicoActionForm
						.getDescricaoUnidadeGeracao().equals("")) {

			httpServletRequest.setAttribute("unidadeGeracaoEncontrada", "true");
		}

		// Unidade Atual
		if (pesquisarOrdemServicoActionForm.getUnidadeAtual() != null
				&& !pesquisarOrdemServicoActionForm.getUnidadeAtual()
						.equals("")
				&& pesquisarOrdemServicoActionForm.getDescricaoUnidadeAtual() != null
				&& !pesquisarOrdemServicoActionForm.getDescricaoUnidadeAtual()
						.equals("")) {

			httpServletRequest.setAttribute("unidadeAtualEncontrada", "true");
		}

		// Unidade Superior
		if (pesquisarOrdemServicoActionForm.getUnidadeSuperior() != null
				&& !pesquisarOrdemServicoActionForm.getUnidadeSuperior()
						.equals("")
				&& pesquisarOrdemServicoActionForm
						.getDescricaoUnidadeSuperior() != null
				&& !pesquisarOrdemServicoActionForm
						.getDescricaoUnidadeSuperior().equals("")) {

			httpServletRequest
					.setAttribute("unidadeSuperiorEncontrada", "true");
		}

		// Municipio
		if (pesquisarOrdemServicoActionForm.getMunicipio() != null
				&& !pesquisarOrdemServicoActionForm.getMunicipio().equals("")
				&& pesquisarOrdemServicoActionForm.getDescricaoMunicipio() != null
				&& !pesquisarOrdemServicoActionForm.getDescricaoMunicipio()
						.equals("")) {

			httpServletRequest.setAttribute("municipioEncontrada", "true");
		}

		// Bairro
		if (pesquisarOrdemServicoActionForm.getBairro() != null
				&& !pesquisarOrdemServicoActionForm.getBairro().equals("")
				&& pesquisarOrdemServicoActionForm.getDescricaoBairro() != null
				&& !pesquisarOrdemServicoActionForm.getDescricaoBairro()
						.equals("")) {

			httpServletRequest.setAttribute("bairroEncontrada", "true");
		}

		// Logradouro
		if (pesquisarOrdemServicoActionForm.getLogradouro() != null
				&& !pesquisarOrdemServicoActionForm.getLogradouro().equals("")
				&& pesquisarOrdemServicoActionForm.getDescricaoLogradouro() != null
				&& !pesquisarOrdemServicoActionForm.getDescricaoLogradouro()
						.equals("")) {

			httpServletRequest.setAttribute("logradouroEncontrado", "true");
		}

	}

}