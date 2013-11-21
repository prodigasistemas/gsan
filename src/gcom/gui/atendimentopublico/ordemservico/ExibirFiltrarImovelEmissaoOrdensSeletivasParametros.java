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
* Ivan Sérgio Virginio da Silva Júnior
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

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirFiltrarImovelEmissaoOrdensSeletivasParametros extends
		GcomAction {
	
	private Collection colecaoPesquisa = null;
	private String eloID = null;
	private String localidadeID = null;
	private String setorComercialCD = null;
	private String setorComercialID = null;
	private String quadraNM = null;
	private String logradouro = null;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarImovelEmissaoOrdensSeletivasParametros");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		ImovelEmissaoOrdensSeletivasActionForm imovelEmissaoOrdensSeletivas =
			(ImovelEmissaoOrdensSeletivasActionForm) actionForm;
		
		// Verifica o Tipo do Usuario
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		boolean usuarioPermissaoGerar = fachada.verificarPermissaoGerarOSSeletivasHidrometro(usuario);
		httpServletRequest.setAttribute("usuarioPermissaoGerar", usuarioPermissaoGerar);
		//usuario sem permissao
		if ( usuarioPermissaoGerar == false ) {
			imovelEmissaoOrdensSeletivas.setUsuarioSemPermissaoGerarOS("2");
		}
		
		
		if (imovelEmissaoOrdensSeletivas.getTipoOrdem() != null) {
			httpServletRequest.setAttribute("tipoOrdem", imovelEmissaoOrdensSeletivas.getTipoOrdem());
		}
		
		if (imovelEmissaoOrdensSeletivas.getSugestao() != null) {
			httpServletRequest.setAttribute("sugestao", imovelEmissaoOrdensSeletivas.getSugestao());
		}
		
		// Pesquisar Firma CASO Sugestao = NAO
		//if (imovelEmissaoOrdensSeletivas.getSugestao() == null || imovelEmissaoOrdensSeletivas.getSugestao().equals("2")) {
			if(sessao.getAttribute("colecaoFirma") == null){
				FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
				filtroEmpresa.adicionarParametro(new ParametroSimples(
						FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
				
				Collection<Empresa> colecaoFirma = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
				
				// [FS0001 - Verificar Existencia de dados]
				if ( (colecaoFirma == null) || (colecaoFirma.size() == 0) ) {
					throw new ActionServletException(
							"atencao.entidade_sem_dados_para_selecao", null, Empresa.class.getName());
				}else {
					sessao.setAttribute("colecaoFirma", colecaoFirma);
				}
			}
		//}
			
			//IMOVEL
			
			if (imovelEmissaoOrdensSeletivas.getInscricaoImovel() != null && 
					imovelEmissaoOrdensSeletivas.getInscricaoImovel().equals("IMÓVEL INEXISTENTE")){
				httpServletRequest.setAttribute("corImovel", "exception");
			}
			
			String pesquisarImovel = httpServletRequest.getParameter("pesquisarImovel");
			
			//entra aqui caso não venha da pesquisa ENTER
			if (imovelEmissaoOrdensSeletivas.getIdImovel() != null 
				&& !imovelEmissaoOrdensSeletivas.getIdImovel().equals("")
				&& pesquisarImovel != null && pesquisarImovel.equalsIgnoreCase("OK")){
				
				Integer idImovel = new Integer(imovelEmissaoOrdensSeletivas.getIdImovel());
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel ));
				
				Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
				
				if (colecaoImovel != null && !colecaoImovel.isEmpty()){
					Imovel imovel = (Imovel)Util.retonarObjetoDeColecao(colecaoImovel);
					imovelEmissaoOrdensSeletivas.setInscricaoImovel(imovel.getInscricaoFormatada());
					httpServletRequest.removeAttribute("corImovel");
					
				}else{
					imovelEmissaoOrdensSeletivas.setInscricaoImovel("IMÓVEL INEXISTENTE");
					httpServletRequest.setAttribute("corImovel", "exception");
				}
			}
			
			
			//SERVIÇO TIPO
//			if(sessao.getAttribute("colecaoServicoTipo") == null){
//			
//				FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
//				filtroServicoTipo.adicionarParametro(new ParametroSimples(
//						FiltroServicoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
//				
//				filtroServicoTipo.adicionarParametro(new ParametroSimples(
//						FiltroServicoTipo.INDICADOR_SERVICO_ORDEM_SELETIVA, ConstantesSistema.INDICADOR_USO_ATIVO));
//				
//				filtroServicoTipo.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);
//					
//				Collection<ServicoTipo> colecaoServicoTipo = 
//				fachada.pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
//					
//				// [FS0001 - Verificar Existencia de dados]
//				if (colecaoServicoTipo == null || colecaoServicoTipo.isEmpty() ) {
//						
//					throw new ActionServletException(
//					"atencao.entidade_sem_dados_para_selecao", null, ServicoTipo.class.getName());
//				}
//				else {
//					sessao.setAttribute("colecaoServicoTipo", colecaoServicoTipo);
//				}
//			}	
			
		//GERENCIA REGIONAL
		if(sessao.getAttribute("colecaoGerenciaRegional") == null){
		
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
			FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
				
			Collection<GerenciaRegional> colecaoGerenciaRegional = 
			fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
				
			// [FS0001 - Verificar Existencia de dados]
			if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty() ) {
					
				throw new ActionServletException(
				"atencao.entidade_sem_dados_para_selecao", null, GerenciaRegional.class.getName());
			}
			else {
				sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
			}
		}
		
		//UNIDADE NEGOCIO
		if(sessao.getAttribute("colecaoUnidadeNegocio") == null){
		
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
			FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
			filtroUnidadeNegocio.setCampoOrderBy(FiltroGerenciaRegional.NOME);
				
			Collection<UnidadeNegocio> colecaoUnidadeNegocio = 
			fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
				
			// [FS0001 - Verificar Existencia de dados]
			if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty() ) {
					
				throw new ActionServletException(
				"atencao.entidade_sem_dados_para_selecao", null, UnidadeNegocio.class.getName());
			}
			else {
				sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
			}
		}
		
		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		String inscricaoTipo = (String) httpServletRequest.getParameter("inscricaoTipo");
		
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("") &&
				inscricaoTipo != null && !inscricaoTipo.trim().equalsIgnoreCase("")) {
		
			switch (Integer.parseInt(objetoConsulta)) {
				// Localidade
				case 1:
					pesquisarLocalidade(inscricaoTipo, imovelEmissaoOrdensSeletivas, fachada, httpServletRequest);
					break;
	
				// Setor Comercial
				case 2:
					pesquisarLocalidade(inscricaoTipo, imovelEmissaoOrdensSeletivas, fachada, httpServletRequest);
					pesquisarSetorComercial(inscricaoTipo, imovelEmissaoOrdensSeletivas, fachada, httpServletRequest);
					break;
				
				// Quadra
				case 3:
					pesquisarLocalidade(inscricaoTipo, imovelEmissaoOrdensSeletivas, fachada, httpServletRequest);
					pesquisarSetorComercial(inscricaoTipo, imovelEmissaoOrdensSeletivas, fachada, httpServletRequest);
					pesquisarQuadra(inscricaoTipo, imovelEmissaoOrdensSeletivas, fachada, httpServletRequest);
					break;

				// ELO
				case 4:
					pesquisarElo(inscricaoTipo, imovelEmissaoOrdensSeletivas, fachada, httpServletRequest);
					break;
				
                //LOGRADOURO
				case 5:
					pesquisarLogradouro(imovelEmissaoOrdensSeletivas, fachada, httpServletRequest);
				default:
					break;
			}
		} else {
			sessao.removeAttribute("imovelEmissaoOrdensSeletivas");
		}
		
		// Usado para fazer o controle de navegacao por conta da Aba Local 
		sessao.setAttribute("abaAtual", "PARAMETROS");
		
		return retorno;
	}
	
	
	private void pesquisarElo(
			String inscricaoTipo,
			ImovelEmissaoOrdensSeletivasActionForm imovelEmissaoOrdensSeletivas,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		// Recebe o valor do campo elo do formulário.
		eloID = (String) imovelEmissaoOrdensSeletivas.getElo();
		
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID_ELO, eloID));
		
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		// Retorna localidade
		colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		
		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
			httpServletRequest.setAttribute("corEloOrigem", "exception");
			httpServletRequest.setAttribute("nomeCampo","elo");
			
			// Localidade nao encontrada
			imovelEmissaoOrdensSeletivas.setElo("");
			imovelEmissaoOrdensSeletivas.setNomeElo("Elo inexistente.");
			
		} else {
			Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
			
			objetoLocalidade = objetoLocalidade.getLocalidade();
			
			imovelEmissaoOrdensSeletivas.setElo(objetoLocalidade.getId().toString());
			imovelEmissaoOrdensSeletivas.setNomeElo(objetoLocalidade.getDescricao());
			
			/*********************************************************************
			 * Caso o Usuario informe o Elo deve-se limpar os campos da Inscricao
			 *********************************************************************/ 
			// Localidade
			imovelEmissaoOrdensSeletivas.setLocalidadeInicial("");
			imovelEmissaoOrdensSeletivas.setLocalidadeFinal("");
			imovelEmissaoOrdensSeletivas.setNomeLocalidadeInicial("");
			imovelEmissaoOrdensSeletivas.setNomeLocalidadeFinal("");
			// Setor Comercial
			imovelEmissaoOrdensSeletivas.setSetorComercialInicial("");
			imovelEmissaoOrdensSeletivas.setSetorComercialFinal("");
			imovelEmissaoOrdensSeletivas.setNomeSetorComercialInicial("");
			imovelEmissaoOrdensSeletivas.setNomeSetorComercialFinal("");
			imovelEmissaoOrdensSeletivas.setCodigoSetorComercialInicial("");
			imovelEmissaoOrdensSeletivas.setCodigoSetorComercialFinal("");
			// Quadra
			imovelEmissaoOrdensSeletivas.setQuadraInicial("");
			imovelEmissaoOrdensSeletivas.setQuadraFinal("");
			imovelEmissaoOrdensSeletivas.setIdQuadraInicial("");
			imovelEmissaoOrdensSeletivas.setIdQuadraFinal("");
			
			httpServletRequest.setAttribute("corElo", "valor");
		}
	}
	
	/***
	 * 
	 * @param inscricaoTipo
	 * @param imovelCurvaAbcDebitosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	
	private void pesquisarLocalidade(
			String inscricaoTipo,
			ImovelEmissaoOrdensSeletivasActionForm imovelEmissaoOrdensSeletivas,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			imovelEmissaoOrdensSeletivas.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) imovelEmissaoOrdensSeletivas.getLocalidadeInicial();
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formulário
				imovelEmissaoOrdensSeletivas.setLocalidadeInicial("");
				imovelEmissaoOrdensSeletivas.setNomeLocalidadeInicial("Localidade inexistente");
				imovelEmissaoOrdensSeletivas.setLocalidadeFinal("");
				imovelEmissaoOrdensSeletivas.setNomeLocalidadeFinal("");
				
				httpServletRequest.setAttribute("corLocalidadeOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeInicial");
				
			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				imovelEmissaoOrdensSeletivas.setLocalidadeInicial(String.valueOf(objetoLocalidade.getId()));
				imovelEmissaoOrdensSeletivas.setNomeLocalidadeInicial(objetoLocalidade.getDescricao());
				
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialInicial");
				
				//destino
				imovelEmissaoOrdensSeletivas.setLocalidadeFinal(String.valueOf(objetoLocalidade.getId()));
				imovelEmissaoOrdensSeletivas.setNomeLocalidadeFinal(objetoLocalidade.getDescricao());
				
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) imovelEmissaoOrdensSeletivas.getLocalidadeFinal();
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			imovelEmissaoOrdensSeletivas.setInscricaoTipo("destino");
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formulário
				imovelEmissaoOrdensSeletivas.setLocalidadeFinal("");
				imovelEmissaoOrdensSeletivas.setNomeLocalidadeFinal("Localidade inexistente.");
				
				httpServletRequest.setAttribute("corLocalidadeDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeFinal");
			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				imovelEmissaoOrdensSeletivas.setLocalidadeFinal(String.valueOf(objetoLocalidade.getId()));
				imovelEmissaoOrdensSeletivas.setNomeLocalidadeFinal(objetoLocalidade.getDescricao());
				
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
			}
		}

	}
	
	/***
	 * 
	 * @param inscricaoTipo
	 * @param imovelCurvaAbcDebitosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarSetorComercial(
			String inscricaoTipo,
			ImovelEmissaoOrdensSeletivasActionForm imovelEmissaoOrdensSeletivas,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		
		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			imovelEmissaoOrdensSeletivas.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) imovelEmissaoOrdensSeletivas.getLocalidadeInicial();

			// O campo localidadeOrigemID será obrigatório
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {
				setorComercialCD = (String) imovelEmissaoOrdensSeletivas.getCodigoSetorComercialInicial();
				
				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));
				
				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));
				
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formulário
					imovelEmissaoOrdensSeletivas.setCodigoSetorComercialInicial("");
					imovelEmissaoOrdensSeletivas.setSetorComercialInicial("");
					imovelEmissaoOrdensSeletivas.setNomeSetorComercialInicial("Setor comercial inexistente.");
					
					httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
					httpServletRequest.setAttribute("nomeCampo","setorComercialOrigemCD");

					//destino
					imovelEmissaoOrdensSeletivas.setCodigoSetorComercialFinal("");
					imovelEmissaoOrdensSeletivas.setSetorComercialFinal("");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					//setorComercialOrigem
					imovelEmissaoOrdensSeletivas.setCodigoSetorComercialInicial(
							String.valueOf(objetoSetorComercial.getCodigo()));
					imovelEmissaoOrdensSeletivas.setSetorComercialInicial(
							String.valueOf(objetoSetorComercial.getId()));
					imovelEmissaoOrdensSeletivas.setNomeSetorComercialInicial(
							objetoSetorComercial.getDescricao());
					
					//httpServletRequest.setAttribute("nomeCampo","quadraOrigemNM");
					//setorComercialOrigem
					
					//setorComercialDestino
					imovelEmissaoOrdensSeletivas.setCodigoSetorComercialFinal(
							String.valueOf(objetoSetorComercial.getCodigo()));
					imovelEmissaoOrdensSeletivas.setSetorComercialFinal(
							String.valueOf(objetoSetorComercial.getId()));
					imovelEmissaoOrdensSeletivas.setNomeSetorComercialFinal(
							objetoSetorComercial.getDescricao());
					
					//setorComercialDestino
					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				imovelEmissaoOrdensSeletivas.setCodigoSetorComercialInicial("");
				imovelEmissaoOrdensSeletivas.setNomeSetorComercialInicial(
						"Informe a localidade da inscrição de origem.");
				
				httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
			}
		} else {
			imovelEmissaoOrdensSeletivas.setInscricaoTipo("destino");
			
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) imovelEmissaoOrdensSeletivas.getLocalidadeFinal();

			// O campo localidadeOrigem será obrigatório
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {
				setorComercialCD = (String) imovelEmissaoOrdensSeletivas.getCodigoSetorComercialFinal();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formulário
					imovelEmissaoOrdensSeletivas.setCodigoSetorComercialFinal("");
					imovelEmissaoOrdensSeletivas.setSetorComercialFinal("");
					imovelEmissaoOrdensSeletivas.setNomeSetorComercialFinal("Setor comercial inexistente.");
					
					httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					
					imovelEmissaoOrdensSeletivas.setCodigoSetorComercialFinal(
							String.valueOf(objetoSetorComercial.getCodigo()));
					imovelEmissaoOrdensSeletivas.setSetorComercialFinal(
							String.valueOf(objetoSetorComercial.getId()));
					imovelEmissaoOrdensSeletivas.setNomeSetorComercialFinal(
							objetoSetorComercial.getDescricao());
					
					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
					//httpServletRequest.setAttribute("nomeCampo","quadraDestinoNM");
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formulário
				imovelEmissaoOrdensSeletivas.setCodigoSetorComercialFinal("");
				imovelEmissaoOrdensSeletivas.setNomeSetorComercialFinal("Informe a localidade da inscrição de destino.");
				
				httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
			}
		}
	}
	
	private void pesquisarQuadra(
			String inscricaoTipo,
			ImovelEmissaoOrdensSeletivasActionForm imovelEmissaoOrdensSeletivas,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {
		
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		// Objetos que serão retornados pelo hibernate.
		//filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");

		//QUADRA
		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			imovelEmissaoOrdensSeletivas.setInscricaoTipo("origem");
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formulário.
			setorComercialCD = (String) imovelEmissaoOrdensSeletivas.getCodigoSetorComercialInicial();
			setorComercialID = (String) imovelEmissaoOrdensSeletivas.getSetorComercialInicial();
			
			String idLocalidadeInicial = (String) imovelEmissaoOrdensSeletivas.getLocalidadeInicial();
			
			// Os campos setorComercialOrigemCD e setorComercialID serão
			// obrigatórios
			if (setorComercialCD != null && !setorComercialCD.trim().equalsIgnoreCase("") &&
					setorComercialID != null && !setorComercialID.trim().equalsIgnoreCase("")) {
				
				quadraNM = (String) imovelEmissaoOrdensSeletivas.getQuadraInicial();
				
				// coloca parametro no filtro
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidadeInicial)));
				
				// Adiciona o id do setor comercial que está no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o número da quadra que esta no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formulário
					imovelEmissaoOrdensSeletivas.setQuadraInicial("");
					imovelEmissaoOrdensSeletivas.setIdQuadraInicial("");
					// Mensagem de tela
					httpServletRequest.setAttribute("msgQuadraInicial", "QUADRA INEXISTENTE");
					//imovelOutrosCriteriosActionForm
						//	.setQuadraMensagemOrigem("Quadra inexistente.");
					httpServletRequest.setAttribute("corQuadraOrigem", "exception");
					httpServletRequest.setAttribute("nomeCampo","quadraInicial");
					
					//destino
					
					imovelEmissaoOrdensSeletivas.setQuadraFinal("");
					imovelEmissaoOrdensSeletivas.setIdQuadraFinal("");
				} else {
					Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
					imovelEmissaoOrdensSeletivas.setQuadraInicial(
							String.valueOf(objetoQuadra.getNumeroQuadra()));
					imovelEmissaoOrdensSeletivas.setIdQuadraInicial(
							String.valueOf(objetoQuadra.getId()));
					imovelEmissaoOrdensSeletivas.setQuadraFinal(
							String.valueOf(objetoQuadra.getNumeroQuadra()));
					imovelEmissaoOrdensSeletivas.setIdQuadraFinal(
							String.valueOf(objetoQuadra.getId()));
					
					httpServletRequest.setAttribute("corQuadraOrigem", null);
					httpServletRequest.setAttribute("nomeCampo","loteOrigem");
				}
			} else {
				// Limpa o campo quadraOrigemNM do formulário
				imovelEmissaoOrdensSeletivas.setQuadraInicial("");
				//imovelEmissaoOrdensSeletivas.setQuadraMensagemOrigem("Informe o setor comercial da inscrição de origem.");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
			}
		} else {//QUADRA FINAL
			imovelEmissaoOrdensSeletivas.setInscricaoTipo("destino");
			
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formulário.
			setorComercialCD = (String) imovelEmissaoOrdensSeletivas.getCodigoSetorComercialFinal();
			setorComercialID = (String) imovelEmissaoOrdensSeletivas.getSetorComercialFinal();

			String idLocalidadeFinal = (String) imovelEmissaoOrdensSeletivas.getLocalidadeFinal();			
			
			// Os campos setorComercialOrigemCD e setorComercialID serão
			// obrigatórios
			if (setorComercialCD != null && !setorComercialCD.trim().equalsIgnoreCase("") &&
					setorComercialID != null && !setorComercialID.trim().equalsIgnoreCase("")) {
				
				quadraNM = (String) imovelEmissaoOrdensSeletivas.getQuadraFinal();

				// coloca parametro no filtro
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidadeFinal)));
				
				// Adiciona o id do setor comercial que está no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o número da quadra que esta no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
				
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formulário
					imovelEmissaoOrdensSeletivas.setQuadraFinal("");
					imovelEmissaoOrdensSeletivas.setIdQuadraFinal("");
					// Mensagem de tela
					//imovelOutrosCriteriosActionForm
						//	.setQuadraMensagemDestino("Quadra inexistente.");
					httpServletRequest.setAttribute("msgQuadraFinal", "QUADRA INEXISTENTE");					
					httpServletRequest.setAttribute("corQuadraDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo","quadraDestinoNM");
				} else {
					Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
					imovelEmissaoOrdensSeletivas.setQuadraFinal(
							String.valueOf(objetoQuadra.getNumeroQuadra()));
					imovelEmissaoOrdensSeletivas.setIdQuadraFinal(
							String.valueOf(objetoQuadra.getId()));
					httpServletRequest.setAttribute("corQuadraDestino", null);
					//httpServletRequest.setAttribute("nomeCampo","loteDestino");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				imovelEmissaoOrdensSeletivas.setQuadraFinal("");
				// Mensagem de tela
				//imovelEmissaoOrdensSeletivas.setQuadraMensagemDestino("Informe o setor comercial da inscrição.");
				httpServletRequest.setAttribute("corQuadraDestino", "exception");
			}
		}

	}
	
	private void pesquisarLogradouro(ImovelEmissaoOrdensSeletivasActionForm imovelEmissaoOrdensSeletivas,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");

		logradouro = (String) imovelEmissaoOrdensSeletivas.getLogradouro();
		
		filtroLogradouro.adicionarParametro(new ParametroSimples(
		FiltroLogradouro.ID, logradouro));
		
		filtroLogradouro.adicionarParametro(new ParametroSimples(
		FiltroLogradouro.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		colecaoPesquisa = fachada.pesquisar(filtroLogradouro, Logradouro.class.getName());
		
		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
			// Logradouro nao encontrada
			imovelEmissaoOrdensSeletivas.setLogradouro("");
			imovelEmissaoOrdensSeletivas.setDescricaoLogradouro("Logradouro inexistente.");
			
			httpServletRequest.setAttribute("corLogradouro", "exception");
			httpServletRequest.setAttribute("nomeCampo","logradouro");
		} 
		else {
			Logradouro objetoLogradouro = (Logradouro) Util.retonarObjetoDeColecao(colecaoPesquisa);
			
			imovelEmissaoOrdensSeletivas.setLogradouro(objetoLogradouro.getId().toString());
			imovelEmissaoOrdensSeletivas.setDescricaoLogradouro(objetoLogradouro.getDescricaoFormatada());
			
			httpServletRequest.setAttribute("corLogradouro", "valor");
		}
	}
}
