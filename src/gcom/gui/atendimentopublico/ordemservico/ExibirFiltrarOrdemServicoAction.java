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
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
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
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
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
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0450] Pesquisar Ordem Servico - Exibir
 * 
 * @author Leonardo Regis
 *
 * @date 04/09/2006
 */
public class ExibirFiltrarOrdemServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarOrdemServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		// Form
		FiltrarOrdemServicoActionForm filtrarOrdemServicoActionForm = 
			(FiltrarOrdemServicoActionForm) actionForm;
		
		//Colocado por Raphael Rossiter em 29/01/2007 
		String menu = httpServletRequest.getParameter("menu");
		  
		if (menu != null && !menu.equalsIgnoreCase("")){
			//Sugerindo um período para realização da filtragem de uma OS
			//SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			Date dataAtual = new Date();
			Calendar calendario = new GregorianCalendar();
			calendario.setTime(dataAtual);

			// ******************************************************************
			// Solicitado por Leonardo Vieira sem U.C. Executor: Marcio Roberto *
			// Pega quantidade de dias do mês atual,  antes tinha fixo 30 dias. * 
			Integer qtdDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH), calendario.get(Calendar.YEAR)));
			
			Date dataSugestao = Util.subtrairNumeroDiasDeUmaData(dataAtual, qtdDias-1);
			
			filtrarOrdemServicoActionForm.setPeriodoGeracaoInicial(Util.formatarData(dataSugestao));
			filtrarOrdemServicoActionForm.setPeriodoGeracaoFinal(Util.formatarData(dataAtual));
			
		}

		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		//[UC0443] - Pesquisar Registro Atendimento
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			objetoConsulta.trim().equals("1")) {

			// Faz a consulta de Registro Atendimento
			this.pesquisarRegistroAtendimento(filtrarOrdemServicoActionForm);
		}

		//[UC9999] - Pesquisar Documento de Cobrança
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("2")) {

			// Faz a consulta de Documento Cobrança
			this.pesquisarDocumentoCobranca(filtrarOrdemServicoActionForm);
		}
		
		//[UC0013] - Pesquisar Imovel
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("3")) {

			// Faz a consulta de Imovel
			this.pesquisarImovel(filtrarOrdemServicoActionForm);
		}

		//[UC0012] - Pesquisar Cliente
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("4")) {

			// Faz a consulta de Cliente
			this.pesquisarCliente(filtrarOrdemServicoActionForm);
		}

		//[UC0376 - Pesquisar Unidade
		if ( (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("5")) ||
			(objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("6")) ||
			(objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("7")) ) {

			// Faz a consulta de Cliente
			this.pesquisarUnidadeOrganizacional(filtrarOrdemServicoActionForm,objetoConsulta);
		}

		//[UC0075] - Pesquisar Municipio
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("8")) {

			// Faz a consulta de Municipio
			this.pesquisarMunicipio(filtrarOrdemServicoActionForm);
		}

		//[UC0141] - Pesquisar Bairro
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("9")) {

			// Faz a consulta de Bairro
			this.pesquisarBairro(filtrarOrdemServicoActionForm,httpServletRequest);
		}

		//[UC0004] - Pesquisar Logradouro
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("10")) {

			// Faz a consulta de logradouro
			this.pesquisarLogradouro(filtrarOrdemServicoActionForm);
		}
		
		if (httpServletRequest.getParameter("tipoConsulta") != null && 
			!httpServletRequest.getParameter("tipoConsulta").equals("")) {
			
			String id = httpServletRequest.getParameter("idCampoEnviarDados");
			String descricao = httpServletRequest.getParameter("descricaoCampoEnviarDados");
			
			if (httpServletRequest.getParameter("tipoConsulta").equals("registroAtendimento")) {

				filtrarOrdemServicoActionForm.setNumeroRA(id);
				filtrarOrdemServicoActionForm.setDescricaoRA(descricao);

			}else if (httpServletRequest.getParameter("tipoConsulta").equals("documentoCobranca")) {

				filtrarOrdemServicoActionForm.setDocumentoCobranca(id);
				filtrarOrdemServicoActionForm.setDescricaoDocumentoCobranca(descricao);

			}else if (httpServletRequest.getParameter("tipoConsulta").equals("imovel")) {

				filtrarOrdemServicoActionForm.setMatriculaImovel(id);
				filtrarOrdemServicoActionForm.setInscricaoImovel(descricao);

			}else if (httpServletRequest.getParameter("tipoConsulta").equals("cliente")) {

				filtrarOrdemServicoActionForm.setCodigoCliente(id);
				filtrarOrdemServicoActionForm.setNomeCliente(descricao);

			}else if (httpServletRequest.getParameter("tipoConsulta").equals("unidadeOrganizacional")) {

				if(sessao.getAttribute("tipoUnidade").equals("unidadeGeracao")){
					filtrarOrdemServicoActionForm.setUnidadeGeracao(id);
					filtrarOrdemServicoActionForm.setDescricaoUnidadeGeracao(descricao);
					
				}else if(sessao.getAttribute("tipoUnidade").equals("unidadeAtual")){
					filtrarOrdemServicoActionForm.setUnidadeAtual(id);
					filtrarOrdemServicoActionForm.setDescricaoUnidadeAtual(descricao);
					
				}else{
					filtrarOrdemServicoActionForm.setUnidadeSuperior(id);
					filtrarOrdemServicoActionForm.setDescricaoUnidadeSuperior(descricao);
				}

			}else if (httpServletRequest.getParameter("tipoConsulta").equals("municipio")) {

				filtrarOrdemServicoActionForm.setMunicipio(id);
				filtrarOrdemServicoActionForm.setDescricaoMunicipio(descricao);

			}else if (httpServletRequest.getParameter("tipoConsulta").equals("bairro")) {

				filtrarOrdemServicoActionForm.setCodigoBairro(id);
				filtrarOrdemServicoActionForm.setDescricaoBairro(descricao);

			}else if (httpServletRequest.getParameter("tipoConsulta").equals("logradouro")) {

				filtrarOrdemServicoActionForm.setLogradouro(id);
				filtrarOrdemServicoActionForm.setDescricaoLogradouro(descricao);

			}	
		}		
		
		// Atendimento Motivo Encerramento
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroAtendimentoMotivoEncerramento.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);
		Collection colecaoAtendimentoMotivoEncerramento = Fachada.getInstancia().pesquisar(
				filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());
		sessao.setAttribute("colecaoAtendimentoMotivoEncerramento", colecaoAtendimentoMotivoEncerramento);
		
		//Monta a colecao de tipos Servicos
		this.pesquisarTipoServico(httpServletRequest);
		
		// Perfil Imovel
		this.getPerfilImovelCollection(sessao, this.getFachada());
		
		//Seta os request´s encontrados
		this.setaRequest(httpServletRequest,filtrarOrdemServicoActionForm);
		
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaOrdemServico") != null) {
			
			sessao.setAttribute("caminhoRetornoTelaPesquisaOrdemServico",
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisaOrdemServico"));
			
		}
		
		if(filtrarOrdemServicoActionForm.getSituacaoProgramacao() == null || 
			filtrarOrdemServicoActionForm.getSituacaoProgramacao().equals("")){
			
			filtrarOrdemServicoActionForm.setSituacaoProgramacao(ConstantesSistema.SET_ZERO.toString());	
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
	private void pesquisarImovel(FiltrarOrdemServicoActionForm form) {

		// Filtra Imovel
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(
				new ParametroSimples(FiltroImovel.ID, 
				form.getMatriculaImovel()));
		
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
		
		// Recupera Imóvel
		Collection colecaoImovel = 
			Fachada.getInstancia().pesquisar(filtroImovel, Imovel.class.getName());
	
		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

			Imovel imovel = 
				(Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
			
			form.setMatriculaImovel(imovel.getId().toString());
			form.setInscricaoImovel(imovel.getInscricaoFormatada());
			
		} else {
			form.setMatriculaImovel("");
			form.setInscricaoImovel("Matrícula inexistente");
		}
	}

	/**
	 * Pesquisa Registro Atendimento 
	 *
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarRegistroAtendimento(FiltrarOrdemServicoActionForm form) {
		
		// Filtro para obter o localidade ativo de id informado
		FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();

		filtroRegistroAtendimento.adicionarParametro(
			new ParametroSimples(FiltroRegistroAtendimento.ID, 
			new Integer(form.getNumeroRA())));
		
		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacao");
		

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoRegistros = Fachada.getInstancia()
				.pesquisar(filtroRegistroAtendimento,RegistroAtendimento.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoRegistros != null && !colecaoRegistros.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			RegistroAtendimento registroAtendimento = 
				(RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRegistros);
			
			form.setNumeroRA(registroAtendimento.getId().toString());
			form.setDescricaoRA(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
			

		} else {

			form.setDescricaoRA("Registro Atendimento inexistente");
			form.setNumeroRA("");

		}
	}

	/**
	 * Pesquisa Documento Cobrança 
	 *
	 * @author Rafael Pinto
	 * @date 21/08/2006
	 */
	private void pesquisarDocumentoCobranca(FiltrarOrdemServicoActionForm form){

		FiltroDocumentoCobranca filtroDocumentoCobranca = new FiltroDocumentoCobranca();

		filtroDocumentoCobranca.adicionarParametro(
			new ParametroSimples(FiltroDocumentoCobranca.ID, 
			new Integer(form.getDocumentoCobranca())));
		
		filtroDocumentoCobranca.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
		
		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoDocumentoCobranca = Fachada.getInstancia()
				.pesquisar(filtroDocumentoCobranca,CobrancaDocumento.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoDocumentoCobranca != null && !colecaoDocumentoCobranca.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			CobrancaDocumento cobrancaDocumento = 
				(CobrancaDocumento) Util.retonarObjetoDeColecao(colecaoDocumentoCobranca);

			form.setDocumentoCobranca(cobrancaDocumento.getId().toString());
			form.setDescricaoDocumentoCobranca(cobrancaDocumento.getDocumentoTipo().getDescricaoDocumentoTipo());
			

		} else {
			form.setDocumentoCobranca("");
			form.setDescricaoDocumentoCobranca("Documento Cobrança inexistente");
		}
	}
	
	/**
	 * Pesquisa Cliente 
	 *
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarCliente(FiltrarOrdemServicoActionForm form) {
		
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(
			new ParametroSimples(FiltroCliente.ID, 
			new Integer(form.getCodigoCliente())));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoCliente = Fachada.getInstancia()
				.pesquisar(filtroCliente,Cliente.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Cliente cliente = 
				(Cliente) Util.retonarObjetoDeColecao(colecaoCliente);

			form.setCodigoCliente(cliente.getId().toString());
			form.setNomeCliente(cliente.getNome());
			

		} else {
			form.setCodigoCliente("");
			form.setNomeCliente("Cliente inexistente");
		}
	}
	
	/**
	 * Pesquisa Unidade Organizacional 
	 *
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarUnidadeOrganizacional(FiltrarOrdemServicoActionForm form, String objetoConsulta) {
		
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		
		Integer idUnidade = null;

		if(objetoConsulta.equals("5")){
			idUnidade = new Integer(form.getUnidadeGeracao());
		}else if(objetoConsulta.equals("6")){
			idUnidade = new Integer(form.getUnidadeAtual());
		}else{
			idUnidade = new Integer(form.getUnidadeSuperior());
			
		}

		filtroUnidadeOrganizacional.adicionarParametro(
				new ParametroSimples(FiltroUnidadeOrganizacional.ID,idUnidade));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoUnidade = Fachada.getInstancia()
				.pesquisar(filtroUnidadeOrganizacional,UnidadeOrganizacional.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			UnidadeOrganizacional unidadeOrganizacional = 
				(UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);

			if(objetoConsulta.equals("5")){
			
				form.setUnidadeGeracao(unidadeOrganizacional.getId().toString());
				form.setDescricaoUnidadeGeracao(unidadeOrganizacional.getDescricao());
				
			}else if(objetoConsulta.equals("6")){

				form.setUnidadeAtual(unidadeOrganizacional.getId().toString());
				form.setDescricaoUnidadeAtual(unidadeOrganizacional.getDescricao());
				
			}else{
				
				//[FS0009] - Verificar existência de unidades subordinadas
				filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

				filtroUnidadeOrganizacional.adicionarParametro(
						new ParametroSimples(FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR,idUnidade));
				
				colecaoUnidade = 
					Fachada.getInstancia().pesquisar(filtroUnidadeOrganizacional,UnidadeOrganizacional.class.getName());

				// Verifica se a pesquisa retornou algum objeto para a coleção
				if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
					form.setUnidadeSuperior(unidadeOrganizacional.getId().toString());
					form.setDescricaoUnidadeSuperior(unidadeOrganizacional.getDescricao());
				}else{
					throw new ActionServletException("atencao.filtrar_ra_sem_unidades_subordinadas");
				}
				
				
			}

		} else {
			if(objetoConsulta.equals("5")){
				
				form.setUnidadeGeracao("");
				form.setDescricaoUnidadeGeracao("Unidade de Geração inexistente");
				
			}else if(objetoConsulta.equals("6")){

				form.setUnidadeAtual("");
				form.setDescricaoUnidadeAtual("Unidade Atual inexistente");
				
			}else{
				
				form.setUnidadeSuperior("");
				form.setDescricaoUnidadeSuperior("Unidade Superior inexistente");
				
			}
		}
	}
	
	/**
	 * Pesquisa Municipio 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void pesquisarMunicipio(FiltrarOrdemServicoActionForm form) {
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.adicionarParametro(
			new ParametroSimples(FiltroMunicipio.ID, 
			new Integer(form.getMunicipio())));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoMunicipio = Fachada.getInstancia()
				.pesquisar(filtroMunicipio,Municipio.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Municipio municipio = 
				(Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);

			form.setMunicipio(municipio.getId().toString());
			form.setDescricaoMunicipio(municipio.getNome());
			

		} else {
			form.setMunicipio("");
			form.setDescricaoMunicipio("Município inexistente");
		}
	}
	
	/**
	 * Pesquisa Bairro 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void pesquisarBairro(FiltrarOrdemServicoActionForm form,HttpServletRequest httpServletRequest) {
		
		//[FS0013] - Verificar informação do munícipio
		String codigoMunicipio = form.getMunicipio();
		if(codigoMunicipio == null || codigoMunicipio.equals("")){
			throw new ActionServletException("atencao.filtrar_informar_municipio");	
		}
		
		FiltroBairro filtroBairro = new FiltroBairro();

		filtroBairro.adicionarParametro(
			new ParametroSimples(FiltroBairro.CODIGO, 
			new Integer(form.getCodigoBairro())));

		filtroBairro.adicionarParametro(
				new ParametroSimples(FiltroBairro.MUNICIPIO_ID,new Integer(codigoMunicipio)));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoBairro = Fachada.getInstancia()
				.pesquisar(filtroBairro,Bairro.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoBairro != null && !colecaoBairro.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Bairro bairro = 
				(Bairro) Util.retonarObjetoDeColecao(colecaoBairro);

			this.montarAreaBairroPorId(httpServletRequest,new Integer(bairro.getId()));			
			
			form.setCodigoBairro(""+bairro.getCodigo());
			form.setIdBairro(""+bairro.getId());
			form.setDescricaoBairro(bairro.getNome());

		} else {
			form.setCodigoBairro(null);
			form.setDescricaoBairro("Bairro inexistente");
		}
	}

	/**
	 * Pesquisa Logradouro 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void pesquisarLogradouro(FiltrarOrdemServicoActionForm form) {
		
		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

		filtroLogradouro.adicionarParametro(
			new ParametroSimples(FiltroLogradouro.ID, 
			new Integer(form.getLogradouro())));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoLogradouro = Fachada.getInstancia()
				.pesquisar(filtroLogradouro,Logradouro.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoLogradouro != null && !colecaoLogradouro.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Logradouro logradouro = 
				(Logradouro) Util.retonarObjetoDeColecao(colecaoLogradouro);

			form.setLogradouro(logradouro.getId().toString());
			form.setDescricaoLogradouro(logradouro.getNome());

		} else {
			form.setLogradouro("");
			form.setDescricaoLogradouro("Logradouro inexistente");
		}
	}

	/**
	 * Pesquisa Area do Bairro pelo Id 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void montarAreaBairroPorId(HttpServletRequest request,Integer id){

		
		// Parte que passa as coleções necessárias no jsp
		Collection colecaoAreaBairro = new ArrayList();
			
		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();
		filtroBairroArea.adicionarParametro(new ParametroSimples(FiltroBairroArea.ID_BAIRRO,id));

		colecaoAreaBairro = 
			Fachada.getInstancia().pesquisar(filtroBairroArea, BairroArea.class.getName());

		if (colecaoAreaBairro != null && !colecaoAreaBairro.isEmpty()) {
			request.setAttribute("colecaoAreaBairro", colecaoAreaBairro);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,"Área do Bairro");
		}
		
	}

	/**
	 * Pesquisa Tipo Servico 
	 *
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 */
	private void pesquisarTipoServico(HttpServletRequest httpServletRequest){
		
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
		
		filtroServicoTipo.setConsultaSemLimites(true);
		filtroServicoTipo.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);

		Collection colecaoTipoServico = 
			Fachada.getInstancia().pesquisar(filtroServicoTipo,ServicoTipo.class.getName());


		if (colecaoTipoServico == null || colecaoTipoServico.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Tipo de Serviço");
		} else {
			httpServletRequest.setAttribute("colecaoTipoServico",colecaoTipoServico);
		}
	}
	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			FiltrarOrdemServicoActionForm form){
		
		//Imovel
		if(form.getMatriculaImovel() != null && 
			!form.getMatriculaImovel().equals("") && 
			form.getInscricaoImovel() != null && 
			!form.getInscricaoImovel().equals("")){
			
			httpServletRequest.setAttribute("matriculaImovelEncontrada","true");			
		}

		//Registro Atendimento
		if(form.getNumeroRA() != null && 
			!form.getNumeroRA().equals("") && 
			form.getDescricaoRA() != null && 
			!form.getDescricaoRA().equals("")){
					
			httpServletRequest.setAttribute("numeroRAEncontrada","true");
		}
		
		//Documento Cobrança
		if(form.getDocumentoCobranca() != null && 
			!form.getDocumentoCobranca().equals("") && 
			form.getDescricaoDocumentoCobranca() != null && 
			!form.getDescricaoDocumentoCobranca().equals("")){
					
			httpServletRequest.setAttribute("documentoCobrancaEncontrada","true");
		}
		
		//Codigo Cliente
		if(form.getCodigoCliente() != null && 
			!form.getCodigoCliente().equals("") && 
			form.getNomeCliente() != null && 
			!form.getNomeCliente().equals("")){
							
			httpServletRequest.setAttribute("codigoClienteEncontrada","true");
		}

		//Unidade Geração
		if(form.getUnidadeGeracao() != null && 
			!form.getUnidadeGeracao().equals("") && 
			form.getDescricaoUnidadeGeracao() != null && 
			!form.getDescricaoUnidadeGeracao().equals("")){
								
			httpServletRequest.setAttribute("unidadeGeracaoEncontrada","true");
		}

		//Unidade Atual
		if(form.getUnidadeAtual() != null && 
			!form.getUnidadeAtual().equals("") && 
			form.getDescricaoUnidadeAtual() != null && 
			!form.getDescricaoUnidadeAtual().equals("")){
								
			httpServletRequest.setAttribute("unidadeAtualEncontrada","true");
		}
		
		//Unidade Superior
		if(form.getUnidadeSuperior() != null && 
			!form.getUnidadeSuperior().equals("") && 
			form.getDescricaoUnidadeSuperior() != null && 
			!form.getDescricaoUnidadeSuperior().equals("")){
								
			httpServletRequest.setAttribute("unidadeSuperiorEncontrada","true");
		}
		
		//Municipio
		if(form.getMunicipio() != null && 
			!form.getMunicipio().equals("") && 
			form.getDescricaoMunicipio() != null && 
			!form.getDescricaoMunicipio().equals("")){
								
			httpServletRequest.setAttribute("municipioEncontrada","true");
		}
		
		//Bairro
		if(form.getCodigoBairro() != null && 
			!form.getCodigoBairro().equals("") && 
			form.getDescricaoBairro() != null && 
			!form.getDescricaoBairro().equals("")){
								
			httpServletRequest.setAttribute("bairroEncontrada","true");
		}

		//Logradouro
		if(form.getLogradouro() != null && 
			!form.getLogradouro().equals("") && 
			form.getDescricaoLogradouro() != null && 
			!form.getDescricaoLogradouro().equals("")){
								
			httpServletRequest.setAttribute("logradouroEncontrado","true");
		}
		
		
		
	}
	
	
	/**
	 * Carrega coleção de Perfil do Imóvel
	 *
	 * @author Daniel Alves
	 * @date 09/07/2010
	 *
	 * @param sessao
	 * @param fachada
	 */
	private void getPerfilImovelCollection(HttpSession sessao, Fachada fachada) {
		// Filtra Solicitação Tipo
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);

		Collection<ImovelPerfil> colecaoPerfilImovel= fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());

		if (colecaoPerfilImovel != null && !colecaoPerfilImovel.isEmpty()) {
			sessao.setAttribute("colecaoPerfilImovel",	colecaoPerfilImovel);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Perfil do Imovel");
		}
	}

}