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
package gcom.gui.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para registrar leituras e anormalidades
 * 
 * @author Sávio Luiz
 */
public class ExibirRegistrarLeiturasAnormalidadesRelatorioAction extends GcomAction {
	private Collection colecaoPesquisa = null;

	private String localidadeID = null;

	private String setorComercialCD = null;
	
	//private String setorComercialID = null;

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("registrarLeiturasAnormalidadesRelatorio");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		//HttpSession sessao = httpServletRequest.getSession(false);

		// Criação das coleções
		Collection faturamentosGrupos = null;

		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo(FiltroFaturamentoGrupo.ID);

		faturamentosGrupos = fachada.pesquisar(filtroFaturamentoGrupo,
				FaturamentoGrupo.class.getName());

		if (faturamentosGrupos == null || faturamentosGrupos.isEmpty()) {
			// Nenhuma faturamento grupo cadastrada
			new ActionServletException("atencao.pesquisa.nenhumresultado",
					null, "faturamento grupo");
		}
		
		httpServletRequest.setAttribute("faturamentosGrupos",
				faturamentosGrupos);
		
		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade(FiltroLeituraAnormalidade.DESCRICAO);
		filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoAnormalidadesLeituras = this.getFachada().pesquisar(filtroLeituraAnormalidade, LeituraAnormalidade.class.getName());
		if(colecaoAnormalidadesLeituras == null || colecaoAnormalidadesLeituras.isEmpty()){
			new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Anormalidade de Leitura");
		} else {
			httpServletRequest.setAttribute("colecaoAnormalidadesLeituras", colecaoAnormalidadesLeituras);
		}
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil(FiltroImovelPerfil.DESCRICAO);
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoPerfisImovel = this.getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		if(colecaoPerfisImovel == null || colecaoPerfisImovel.isEmpty()){
			new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Perfil do Imóvel");
		} else {
			httpServletRequest.setAttribute("colecaoPerfisImovel", colecaoPerfisImovel);
		}

		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro( new ParametroSimples(
				FiltroEmpresa.INDICADOR_LEITURA,ConstantesSistema.SIM ) );	
    	filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
    	Collection colecaoEmpresas = Fachada.getInstancia().pesquisar(filtroEmpresa, Empresa.class.getName());
    	
    	httpServletRequest.setAttribute("colecaoEmpresas", colecaoEmpresas);
    	
		String objetoConsulta = (String) httpServletRequest
		.getParameter("objetoConsulta");

		String inscricaoTipo = (String) httpServletRequest
				.getParameter("inscricaoTipo");
		
		RegistrarLeiturasAnormalidadesRelatorioActionForm form = (RegistrarLeiturasAnormalidadesRelatorioActionForm) actionForm;
		
		this.pesquisarGerenciaRegional(httpServletRequest);
		this.pesquisarUnidadeNegocio(httpServletRequest,form);
		
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")
				|| ( inscricaoTipo != null && !inscricaoTipo.trim().equalsIgnoreCase("") ) ) {
		
			switch (Integer.parseInt(objetoConsulta)) {
			// Localidade
			case 1:
		
				pesquisarLocalidade(inscricaoTipo,
						form, fachada,
						httpServletRequest);
		
				break;
			// Setor Comercial
			case 2:
		
				pesquisarLocalidade(inscricaoTipo,
						form, fachada,
						httpServletRequest);
		
				pesquisarSetorComercial(inscricaoTipo,
						form, fachada,
						httpServletRequest);
		
				break;
				
			default:
				break;
			}
		}
		
		if ( form.getIdFirma() != null && !form.getIdFirma().equals( "" ) ){
			pesquisarLeituristasPorEmpresa( httpServletRequest, form );
		}

		return retorno;
	}
	
/*	private void pesquisarLeiturista(String leituristaId, 
			RegistrarLeiturasAnormalidadesRelatorioActionForm form, 
			Fachada fachada, HttpServletRequest httpServletRequest) {
		
		// Verifica se o código do Leiturista foi digitado
        if (leituristaId != null
            && !leituristaId.trim().equals("")
            && Integer.parseInt(leituristaId) > 0) {
            
            //Recupera o leiturista informado pelo usuário
            FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
            filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
            filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
            filtroLeiturista.adicionarParametro(new ParametroSimples(
                    FiltroLeiturista.ID, leituristaId));
            filtroLeiturista.adicionarParametro(new ParametroSimples(
                    FiltroLeiturista.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            Collection leituristaEncontrado = this.getFachada().pesquisar(filtroLeiturista,
                    Leiturista.class.getName());
            
            //Caso o leiturista informado pelo usuário esteja cadastrado no sistema
            //Seta os dados do leiturista no form
            //Caso contrário seta as informações de leiturista para vazio 
            //e indica ao usuário que o leiturista não existe 
            
            if (leituristaEncontrado != null && leituristaEncontrado.size() > 0) {
                //leiturista foi encontrado
                Leiturista leiturista = (Leiturista) ((List) leituristaEncontrado).get(0); 
                form.setIdLeiturista("" + 
                    leiturista.getId());
                if (leiturista.getFuncionario() != null){
                    form.setNomeLeiturista(leiturista.getFuncionario().getNome());                 
                } else if (leiturista.getCliente() != null){
                    form.setNomeLeiturista(leiturista.getCliente().getNome());
                }
                httpServletRequest.setAttribute("idLeituristaEncontrado","true");
                httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
                httpServletRequest.setAttribute( "idLeituristaEncontrado", "" );
            } else {
                //o leiturista não foi encontrado
                form.setIdLeiturista("");
                form.setNomeLeiturista("LEITURISTA INEXISTENTE");
                httpServletRequest.removeAttribute("idLeituristaEncontrado");
                httpServletRequest.setAttribute("nomeCampo","idLeiturista");
            }
        }
	}*/

	private void pesquisarLocalidade(String inscricaoTipo,
			RegistrarLeiturasAnormalidadesRelatorioActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			form.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) form
					.getLocalidadeOrigemID();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formulário
				form.setLocalidadeOrigemID("");
				form
				.setNomeLocalidadeOrigem("Localidade inexistente.");
				form.setLocalidadeDestinoID("");
				form.setNomeLocalidadeDestino("");
				httpServletRequest.setAttribute("corLocalidadeOrigem",
						"exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeOrigemID");
			} else {
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				form.setLocalidadeOrigemID(String
						.valueOf(objetoLocalidade.getId()));
				form
						.setNomeLocalidadeOrigem(objetoLocalidade
								.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo","setorComercialOrigemCD");
				//destino
				form.setLocalidadeDestinoID(String
						.valueOf(objetoLocalidade.getId()));
				form
						.setNomeLocalidadeDestino(objetoLocalidade
								.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) form
					.getLocalidadeDestinoID();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());
			
			form.setInscricaoTipo("destino");
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formulário
				form.setLocalidadeDestinoID("");
				form
						.setNomeLocalidadeDestino("Localidade inexistente.");
				httpServletRequest.setAttribute("corLocalidadeDestino",
						"exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeDestinoID");
			} else {
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				form.setLocalidadeDestinoID(String
						.valueOf(objetoLocalidade.getId()));
				form
						.setNomeLocalidadeDestino(objetoLocalidade
								.getDescricao());
				httpServletRequest
						.setAttribute("corLocalidadeDestino", "valor");
				httpServletRequest.setAttribute("nomeCampo","setorComercialDestinoCD");
			}
		}

	}

	private void pesquisarSetorComercial(String inscricaoTipo,
			RegistrarLeiturasAnormalidadesRelatorioActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			form.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) form
					.getLocalidadeOrigemID();

			// O campo localidadeOrigemID será obrigatório
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) form
						.getSetorComercialOrigemCD();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formulário
					form
							.setSetorComercialOrigemCD("");
					form
							.setSetorComercialOrigemID("");
					form
							.setNomeSetorComercialOrigem("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialOrigem",
							"exception");
					httpServletRequest.setAttribute("nomeCampo","setorComercialOrigemCD");
					//destino
					form
							.setSetorComercialDestinoCD("");
					form
							.setSetorComercialDestinoID("");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					//setorComercialOrigem
					form
							.setSetorComercialOrigemCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					form
							.setSetorComercialOrigemID(String
									.valueOf(objetoSetorComercial.getId()));
					form
							.setNomeSetorComercialOrigem(objetoSetorComercial
									.getDescricao());
					httpServletRequest.setAttribute("nomeCampo","quadraOrigemNM");
					//setorComercialOrigem
					
					//setorComercialDestino
					form
							.setSetorComercialDestinoCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					form
							.setSetorComercialDestinoID(String
									.valueOf(objetoSetorComercial.getId()));
					form
							.setNomeSetorComercialDestino(objetoSetorComercial
									.getDescricao());
					//setorComercialDestino					
					httpServletRequest.setAttribute("corSetorComercialDestino",
							"valor");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				form.setSetorComercialOrigemCD("");
				form
						.setNomeSetorComercialOrigem("Informe a localidade da inscrição de origem.");
				httpServletRequest.setAttribute("corSetorComercialOrigem",
						"exception");
			}
		} else {
			
			form.setInscricaoTipo("destino");
			
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) form
					.getLocalidadeDestinoID();

			// O campo localidadeOrigem será obrigatório
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) form
						.getSetorComercialDestinoCD();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formulário
					form
							.setSetorComercialDestinoCD("");
					form
							.setSetorComercialDestinoID("");
					form
							.setNomeSetorComercialDestino("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialDestino",
							"exception");
					httpServletRequest.setAttribute("nomeCampo","setorComercialDestinoCD");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					form
							.setSetorComercialDestinoCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					form
							.setSetorComercialDestinoID(String
									.valueOf(objetoSetorComercial.getId()));
					form
							.setNomeSetorComercialDestino(objetoSetorComercial
									.getDescricao());
					httpServletRequest.setAttribute("corSetorComercialDestino",
							"valor");
					httpServletRequest.setAttribute("nomeCampo","quadraDestinoNM");
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formulário
				form.setSetorComercialDestinoCD("");
				form
						.setNomeSetorComercialDestino("Informe a localidade da inscrição de destino.");
				httpServletRequest.setAttribute("corSetorComercialDestino",
						"exception");
			}
		}

	}
	
	private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest){
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(
				new ParametroSimples(FiltroQuadra.INDICADORUSO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());


		if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Gerência Regional");
		} else {
			httpServletRequest.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
		}
	}
	
	private void pesquisarUnidadeNegocio(HttpServletRequest httpServletRequest,
			RegistrarLeiturasAnormalidadesRelatorioActionForm form){
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		
		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		
		if(form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA, 
					form.getGerenciaRegional()));		
		}

		filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());


		if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Unidade de Negócio");
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeNegocio",colecaoUnidadeNegocio);
		}
	}
	
	private void pesquisarLeituristasPorEmpresa(HttpServletRequest httpServletRequest,
			RegistrarLeiturasAnormalidadesRelatorioActionForm form ){
		
		Collection colecaoLeiturista = new ArrayList();		

		FiltroLeiturista filtroLeiturista = 
			new FiltroLeiturista( FiltroLeiturista.ID );
		filtroLeiturista.adicionarParametro(
				new ParametroSimples( FiltroLeiturista.EMPRESA_ID, form.getIdFirma() ) );			
		filtroLeiturista.adicionarParametro(
				new ParametroSimples( FiltroLeiturista.INDICADOR_USO, ConstantesSistema.SIM ) );
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(
				FiltroLeiturista.CLIENTE );
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(
				FiltroLeiturista.FUNCIONARIO );
		
		filtroLeiturista.setCampoOrderBy( FiltroLeiturista.CLIENTE_NOME );

		Collection colecao = 
			Fachada.getInstancia().pesquisar(
					filtroLeiturista, Leiturista.class.getName() );

		if ( colecao != null && !colecao.isEmpty() ) {
			
			Iterator it = colecao.iterator();
			
			while ( it.hasNext() ) {
				
				Leiturista leitu = (Leiturista) it.next();
				
				DadosLeiturista dadosLeiu = null;
				
				if ( leitu.getFuncionario() != null ) {
					dadosLeiu = 
						new DadosLeiturista(
								leitu.getId(), leitu.getFuncionario().getNome() );
				} else {
					dadosLeiu = 
						new DadosLeiturista( leitu.getId(), leitu.getCliente().getNome() );
				}
				
				colecaoLeiturista.add(dadosLeiu);
			}
		}
		
		httpServletRequest.setAttribute("colecaoLeiturista", colecaoLeiturista);		
	}		
}
