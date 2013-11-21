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


package gcom.gui.relatorio.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
 * 
 * @author Bruno Barros
 *
 * @date 03/12/2007
 */


public class ExibirGerarRelatorioImoveisFaturasAtrasoAction extends GcomAction {
	

//	private static final String CRITERIO_FILTRO_LOCALIZACAO_GEOGRAFICA = "1";
//	private static final String RESPONSAVEL_ATUAL_IMOVEL = "1";
//	private static final String TIPO_AGRUPADA = "A";

	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		GerarRelatorioImoveisFaturasAtrasoActionForm form = 
			(GerarRelatorioImoveisFaturasAtrasoActionForm) actionForm;
		
		if(Util.verificarNaoVazio(httpServletRequest.getParameter("menu"))){
			form.setTipo("A");
			form.setCriterioFiltro("1");
			form.setResponsavel("1");
		}

		pesquisarLocalidadeOuSetorComercialOuCLiente(form, httpServletRequest);
		
		carregarComboboxEListbox(form);
		
		definirLocalidadeSetorClienteEncontrados(form,httpServletRequest);
		
		return actionMapping.findForward("exibirGerarRelatorioImoveisFaturasAtraso");
	}

	/**
	 * Esse método tem a lógica que verifica qual entidade consultar:
	 * Localidade,Setor Comercialou Cliente. Em seguida, chama o método responsável
	 * por realizar a consulta correta.
	 *
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private void pesquisarLocalidadeOuSetorComercialOuCLiente(
			GerarRelatorioImoveisFaturasAtrasoActionForm form,
			HttpServletRequest httpServletRequest) {

		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		if ( Util.verificarNaoVazio(objetoConsulta)){
			if(isConsultarLocalidade(objetoConsulta)){
				this.pesquisarLocalidade(form,objetoConsulta);				
			}
		
			if(isConsultarSetorComercial(objetoConsulta)){
				this.pesquisarSetorComercial(form,objetoConsulta);				
			}
			
			if(isConsultarCliente(objetoConsulta)){
				this.pesquisarCliente(form,objetoConsulta);				
			}
		}
	}

	/**
	 * 
	 * Esse método invoca os métodos responsáveis por carregar as coleções de
	 * todos os combo boxes e list boxes a serem exibidos na tela.
	 *
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private void carregarComboboxEListbox(GerarRelatorioImoveisFaturasAtrasoActionForm form) {
		carregarColecaoGerenciasRegionais(form);
		carregarColecaoUnidadesNegocios(form);
		carregarColecaoLigacoesAguaSituacao(form);
		carregarColecaoCategorias(form);
		carregarColecaoPerfisImovel(form);
		carregarColecaoEsferasPoder(form);
		carregarColecaoSituacoesCobranca(form);
		carregarColecaoTiposRelacoes(form);
	}

	/**
	 *Caso o parametro tenha valor 5 ou 6 retorna true.
	 *
	 *@since 26/08/2009
	 *@author Marlon Patrick
	 */
	private boolean isConsultarCliente(String objetoConsulta) {
		return objetoConsulta.trim().equals("5") || objetoConsulta.trim().equals("6");
	}

	/**
	 *Caso o parametro tenha valor 2 ou 4 retorna true.
	 *
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private boolean isConsultarSetorComercial(String objetoConsulta) {
		return objetoConsulta.trim().equals("2") || objetoConsulta.trim().equals("4");
	}

	
	/**
	 *Caso o parametro tenha valor 1 ou 3 retorna true.
	 *
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private boolean isConsultarLocalidade(String objetoConsulta) {
		return objetoConsulta.trim().equals("1")|| objetoConsulta.trim().equals("3");
	}
	
	/**
	 * Este método faz a consulta para verificar a existencia do Cliente informado pelo usuário,
	 * seja ele superior ou não.
	 * 
	 * @author Marlon Patrick
	 * @since 26/08/2009
	 */
	private void pesquisarCliente(GerarRelatorioImoveisFaturasAtrasoActionForm form,String objetoConsulta){

		boolean isConsultarClienteSuperior = objetoConsulta.trim().equals("5");
		
		String idCliente = form.getCodigoCliente();
		
		if(isConsultarClienteSuperior){
			idCliente = form.getCodigoClienteSuperior();
		}
		
    	FiltroCliente filtroCliente = new FiltroCliente();
		
		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.ID, idCliente));
		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<Cliente> colecaoCliente = getFachada().pesquisar(filtroCliente,
				Cliente.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoCliente)) {
			if(isConsultarClienteSuperior){
				form.setCodigoClienteSuperior(null);
				form.setNomeClienteSuperior(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);				
			}else{
				form.setCodigoCliente(null);
				form.setNomeCliente(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);
			}
			
			return;
		}
		
		Cliente cliente = colecaoCliente.iterator().next();
		
		if(isConsultarClienteSuperior){
			form.setCodigoClienteSuperior(cliente.getId().toString());
			form.setNomeClienteSuperior(cliente.getNome());
		}else{
			form.setCodigoCliente(cliente.getId().toString());
			form.setNomeCliente(cliente.getNome());
		}		
	}

	/**
	 * Este método faz a consulta para verificar a existencia da Localidade informada pelo usuário,
	 * seja ela inicial ou final.
	 * 
	 * @ultimaModificação Marlon Patrick
	 * @author Bruno Barros
	 * @since 28/11/2007
	 */
	private void pesquisarLocalidade(GerarRelatorioImoveisFaturasAtrasoActionForm form,String objetoConsulta){

		boolean isConsultarLocalidadeInicial = objetoConsulta.trim().equals("1");
		
		String idLocalidade = form.getLocalidadeFinal();
		
		if(isConsultarLocalidadeInicial){
			idLocalidade = form.getLocalidadeInicial();
		}
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,idLocalidade));
		
		Collection<Localidade> colecaoLocalidade = 
			this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoLocalidade)) {
			if(isConsultarLocalidadeInicial){
				form.setLocalidadeInicial(null);
				form.setNomeLocalidadeInicial("Localidade Inicial inexistente");
				
				form.setLocalidadeFinal(null);
				form.setNomeLocalidadeFinal(null);
			}else{
				form.setLocalidadeFinal(null);
				form.setNomeLocalidadeFinal("Localidade Final inexistente");
			}
			
			return;
		}
		
		Localidade localidade = colecaoLocalidade.iterator().next();
		
		if(isConsultarLocalidadeInicial){
			form.setLocalidadeInicial(localidade.getId().toString());
			form.setNomeLocalidadeInicial(localidade.getDescricao());
		}
		
		form.setLocalidadeFinal(localidade.getId().toString());
		form.setNomeLocalidadeFinal(localidade.getDescricao());		
	}
	
	
	/**
	 * Este método faz a consulta para verificar a existencia do Setor Comercial informado pelo usuário,
	 * seja ele inicial ou final.
	 * 
	 * @ultimaModificação Marlon Patrick
	 * @author Bruno Barros
	 * @since 28/11/2007
	 */
	private void pesquisarSetorComercial(GerarRelatorioImoveisFaturasAtrasoActionForm form,
		String objetoConsulta) {

		boolean isConsultarSetorComercialInicial = objetoConsulta.trim().equals("2");

		String idLocalidade = form.getLocalidadeFinal();
		String idSetorComercial = form.getSetorComercialFinal();
		
		if(isConsultarSetorComercialInicial){
			idLocalidade = form.getLocalidadeInicial();
			idSetorComercial = form.getSetorComercialInicial();
		}

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,idSetorComercial));
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.LOCALIDADE,idLocalidade));
		

		Collection<SetorComercial> colecaoSetorComercial = 
			this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoSetorComercial)) {

			if(isConsultarSetorComercialInicial){
				form.setSetorComercialInicial(null);
				form.setNomeSetorComercialInicial("Setor Comercial Inicial inexistente");
				
				form.setSetorComercialFinal(null);
				form.setNomeSetorComercialFinal(null);
			}else{
				form.setSetorComercialFinal(null);
				form.setNomeSetorComercialFinal("Setor Comercial Final inexistente");
			}
			
			return;
		}
		
		SetorComercial setorComercial = colecaoSetorComercial.iterator().next();
		
		if(isConsultarSetorComercialInicial){
			form.setSetorComercialInicial(""+setorComercial.getCodigo());
			form.setNomeSetorComercialInicial(setorComercial.getDescricao());
		}

		form.setSetorComercialFinal(""+setorComercial.getCodigo());
		form.setNomeSetorComercialFinal(setorComercial.getDescricao());

	}
	
	/**
	 * Método consulta as Gerencias Regionais ativas
	 * e seta essa coleção no FORM para que seja exibida na tela.
	 * 
	 * @ultimaModifricação Marlon Patrick
	 * @author Bruno Barros
	 * @date 28/11/2007
	 */
	private void carregarColecaoGerenciasRegionais(GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(
				new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<GerenciaRegional> colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());


		if ( Util.isVazioOrNulo(colecaoGerenciaRegional)) {
			throw new ActionServletException("atencao.naocadastrado", null,"Gerência Regional");
		}
		
		form.setColecaoGerenciasRegionais(colecaoGerenciaRegional);
	}
	
	
	/**
	 * Método consulta as Unidades de Negócio ativas
	 * e seta essa coleção no FORM para que seja exibida na tela.
	 * 
	 * @ultimaModifricação Marlon Patrick
	 * @author Bruno Barros
	 * @since 28/11/2007
	 */
	private void carregarColecaoUnidadesNegocios(GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		
		if(Util.verificarNaoVazio(form.getGerenciaRegional())
				&& !form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA, 
					form.getGerenciaRegional()));		
		}

		filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection<UnidadeNegocio> colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());


		if ( Util.isVazioOrNulo(colecaoUnidadeNegocio)) {
			throw new ActionServletException("atencao.naocadastrado", null,"Unidade de Negócio");
		}
		
		form.setColecaoUnidadesNegocios(colecaoUnidadeNegocio);
	}
	
	/**
	 * Método consulta as Situações de Ligação de Água ativas
	 * e seta essa coleção no FORM para que seja exibida na tela.
	 * 
	 * @ultimaModifricação Marlon Patrick
	 * @author Bruno Barros
	 * @since 28/11/2007
	 */
	private void carregarColecaoLigacoesAguaSituacao(GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
		
		filtroLigacaoAguaSituacao.setConsultaSemLimites(true);
		filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
		filtroLigacaoAguaSituacao.adicionarParametro(
				new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<LigacaoAguaSituacao> colecaoSituacaoLigacaoAgua = 
			this.getFachada().pesquisar(filtroLigacaoAguaSituacao,LigacaoAguaSituacao.class.getName());


		if ( Util.isVazioOrNulo(colecaoSituacaoLigacaoAgua)) {
			throw new ActionServletException("atencao.naocadastrado", null,"Ligaçao de Água");
		}
		
		form.setColecaoSituacoesLigacaoAgua(colecaoSituacaoLigacaoAgua);
	}	

	
	/**
	 * Método consulta as Situações de Cobrança ativas
	 * e seta essa coleção no FORM para que seja exibida na tela.
	 * 
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private void carregarColecaoSituacoesCobranca(GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		FiltroCobrancaSituacao filtroSituacaoCobranca = new FiltroCobrancaSituacao();
		
		filtroSituacaoCobranca.setConsultaSemLimites(true);
		filtroSituacaoCobranca.setCampoOrderBy(FiltroCobrancaSituacao.DESCRICAO);
		filtroSituacaoCobranca.adicionarParametro(
				new ParametroSimples(FiltroCobrancaSituacao.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<CobrancaSituacao> colecaoSituacoesCobranca = 
			this.getFachada().pesquisar(filtroSituacaoCobranca, CobrancaSituacao.class.getName());


		if ( Util.isVazioOrNulo(colecaoSituacoesCobranca)) {
			throw new ActionServletException("atencao.naocadastrado", null,"Situação de Cobrança");
		}
		
		form.setColecaoSituacoesCobranca(colecaoSituacoesCobranca);
	}

	/**
	 * Método consulta as Situações de Cobrança ativas
	 * e seta essa coleção no FORM para que seja exibida na tela.
	 * 
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private void carregarColecaoTiposRelacoes(GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
		
		filtroClienteRelacaoTipo.setConsultaSemLimites(true);
		filtroClienteRelacaoTipo.setCampoOrderBy(FiltroClienteRelacaoTipo.DESCRICAO);
		filtroClienteRelacaoTipo.adicionarParametro(
				new ParametroSimples(FiltroClienteRelacaoTipo.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<ClienteRelacaoTipo> colecaoTiposRelacoes = 
			this.getFachada().pesquisar(filtroClienteRelacaoTipo, ClienteRelacaoTipo.class.getName());


		if ( Util.isVazioOrNulo(colecaoTiposRelacoes)) {
			throw new ActionServletException("atencao.naocadastrado", null,"Situação de Cobrança");
		}
		
		form.setColecaoTiposRelacoes(colecaoTiposRelacoes);
	}

	/**
	 * Método consulta as categorias ativas
	 * e seta essa coleção no FORM para que seja exibida na tela.
	 * 
	 * @ultimaModifricação Marlon Patrick
	 * @author Bruno Barros
	 * @since 04/12/2007
	 */
	private void carregarColecaoCategorias(GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.setConsultaSemLimites(true);
		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
		filtroCategoria.adicionarParametro(
				new ParametroSimples(FiltroCategoria.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<Categoria> colecaoCategoria = 
			this.getFachada().pesquisar(filtroCategoria, Categoria.class.getName());


		if ( Util.isVazioOrNulo(colecaoCategoria)) {
			throw new ActionServletException("atencao.naocadastrado", null,"Categoria");
		}
		
		form.setColecaoCategorias(colecaoCategoria);
	}
	
	/**
	 * Método que consulta os perfis dos imóveis
	 * e seta essa coleção no FORM para que seja exibida na tela.
	 * 
	 * @author Diogo Peixoto
	 * @since 11/03/2011
	 */
	private void carregarColecaoPerfisImovel(GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		FiltroImovelPerfil filtroPerfisImovel = new FiltroImovelPerfil();
		
		filtroPerfisImovel.setConsultaSemLimites(true);
		filtroPerfisImovel.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
		filtroPerfisImovel.adicionarParametro(
				new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<ImovelPerfil> colecaoPerfisImovel = 
			this.getFachada().pesquisar(filtroPerfisImovel, ImovelPerfil.class.getName());

		if ( Util.isVazioOrNulo(colecaoPerfisImovel)) {
			throw new ActionServletException("atencao.naocadastrado", null,"Perfil Imóvel");
		}
		
		form.setColecaoPerfisImovel(colecaoPerfisImovel);
	}
	
	/**
	 * Método consulta as esferas do poder ativas
	 * e seta essa coleção no FORM para que seja exibida na tela.
	 * 
	 * @ultimaModifricação Marlon Patrick
	 * @author Flávio Leonardo
	 * @since 28/11/2007
	 */
	private void carregarColecaoEsferasPoder(GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
		filtroEsferaPoder.setConsultaSemLimites(true);
		filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);
		filtroEsferaPoder.adicionarParametro(
				new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<EsferaPoder> colecaoEsferaPoder = 
			this.getFachada().pesquisar(filtroEsferaPoder,EsferaPoder.class.getName());

		if ( Util.isVazioOrNulo(colecaoEsferaPoder)) {
			throw new ActionServletException("atencao.naocadastrado", null,"Esfera de Poder");
		}
		
		form.setColecaoEsferasPoder(colecaoEsferaPoder);
	}
	
	/**
	 * O método seta no request os atributos que a JSP usará para identificar
	 * se a Localidade, Setor e Cliente foram encontrados ou não.
	 *
	 * @ultimaModificação Marlon Patrick
	 * @author Bruno Barros
	 * @date 28/11/2007
	 */
	private void definirLocalidadeSetorClienteEncontrados(GerarRelatorioImoveisFaturasAtrasoActionForm form,
			HttpServletRequest httpServletRequest){
		
		if( Util.verificarNaoVazio(form.getLocalidadeInicial())
				&& Util.verificarNaoVazio(form.getNomeLocalidadeInicial())){
					
			httpServletRequest.setAttribute("localidadeInicialEncontrada","true");
		}
		
		if(Util.verificarNaoVazio(form.getLocalidadeFinal())
				&& Util.verificarNaoVazio(form.getNomeLocalidadeFinal())){								

			httpServletRequest.setAttribute("localidadeFinalEncontrada","true");
		}

		
		if(Util.verificarNaoVazio(form.getSetorComercialInicial())
				&& Util.verificarNaoVazio(form.getNomeSetorComercialInicial())){
					
			httpServletRequest.setAttribute("setorComercialInicialEncontrado","true");
		}
		
		if(Util.verificarNaoVazio(form.getSetorComercialFinal())
				&& Util.verificarNaoVazio(form.getNomeSetorComercialFinal())){
							
			httpServletRequest.setAttribute("setorComercialFinalEncontrado","true");
		}

		if(Util.verificarNaoVazio(form.getCodigoClienteSuperior())
				&& Util.verificarNaoVazio(form.getNomeClienteSuperior())){
							
			httpServletRequest.setAttribute("codigoClienteSuperiorEncontrado","true");
		}

		if(Util.verificarNaoVazio(form.getCodigoCliente())
				&& Util.verificarNaoVazio(form.getNomeCliente())){
							
			httpServletRequest.setAttribute("codigoClienteEncontrado","true");
		}

	}

}
