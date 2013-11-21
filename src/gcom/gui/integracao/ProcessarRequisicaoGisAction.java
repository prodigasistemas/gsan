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
 * Yara Taciane de Souza
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
package gcom.gui.integracao;

import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.DbVersaoBase;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.FiltroEnderecoReferencia;
import gcom.cadastro.endereco.FiltroLogradouroBairro;
import gcom.cadastro.endereco.FiltroLogradouroCep;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.SessaoHttpListener;
import gcom.gui.seguranca.acesso.MenuGCOM;
import gcom.integracao.GisRetornoMotivo;
import gcom.seguranca.acesso.FiltroGrupoFuncionalidadeOperacao;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.GrupoFuncionalidadeOperacao;
import gcom.seguranca.acesso.GrupoFuncionalidadeOperacaoPK;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupoRestricao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioFavorito;
import gcom.seguranca.acesso.usuario.UsuarioGrupoRestricao;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.util.ConstantesSistema;
import gcom.util.Internacionalizador;
import gcom.util.Util;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.validacao.AssinaturaDSA;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.PropertyMessageResources;

/**
 * Action utilizado
 * 
 * 
 * 
 * @author Yara T. Souza
 * @since 10/03/2009
 */
public class ProcessarRequisicaoGisAction extends GcomAction {
	
	public static HashMap<String, Collection> listaRAIntegracaoGIS = null;
	
	static {
		listaRAIntegracaoGIS = new HashMap<String, Collection> ();
	}
	
	
	//	Obtém a instância da fachada
	private Fachada fachada = Fachada.getInstancia();
	
	Integer retorno = GisRetornoMotivo.OPERACAO_SUCESSO;	
	
	
		
	/**
	 * Action que captura as requisições vindas do Gis para inserção de RA no GSAN 
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		
		
		
		
		HttpSession sessao = request.getSession(false);
		String hashValidacao = request.getParameter("sign");
		
		
		/**
		 * 
		 * Requisição vinda do ProGIS
		 * 
		 **/
		
		if(hashValidacao != null && !hashValidacao.equals("")){
		
			
			String loginUsuario = request.getParameter("usur_nmlogin");
			ActionForward retorno = actionMapping.findForward("exibirInserirRA");
			
			//Objeto GIS que será usado para preencher os dados passados pelo GIS no GSAN
			GisHelper gisHelper = new GisHelper();
			
			
			gisHelper.setHashValidacao(hashValidacao);
			gisHelper.setLogin(loginUsuario);
			
			
			//Subistitui os caracteres usados para passagem de parâmetro via GET pelos de BASE64 Correspondentes
			hashValidacao = hashValidacao.replace("-","/").replace("_","+")+"==";
			
			Usuario usuarioLogado = null;
			
			//Limpa a sessão para o primeiro acesso
			sessao.removeAttribute("gis");	
	        sessao.removeAttribute("origemGIS");
	        sessao.removeAttribute("temImovelGIS");
	        sessao.removeAttribute("idRegistroAtendimento");
	        
	        
			sessao.removeAttribute("colecaoEnderecos");
			sessao.removeAttribute("enderecoPertenceImovel");
			sessao.removeAttribute("habilitarAlteracaoEndereco");
			sessao.removeAttribute("colecaoBairroArea");
			sessao.removeAttribute("desabilitarMunicipioBairro");
			sessao.removeAttribute("desabilitarDivisaoEsgoto");
			sessao.removeAttribute("desabilitarPavimentoRua");
			sessao.removeAttribute("desabilitarPavimentoCalcada");
			sessao.removeAttribute("desabilitarLcalidadeSetorQuadra");
			sessao.removeAttribute("indicCoordenadaSemLogradouro");
			sessao.removeAttribute("colecaoPagamentosDuplicidade");
			sessao.removeAttribute("colecaoConta");
		
			/**
			 * RM5236 - Abertura de RA Através do PROGIS
			 * 
			 * Verifica se o usuário que está tentando realizar a requisição é oriundo de uma chamada GIS.
			 * Caso sim, cria um usuário para a sessão.
			 * 
			 **/
			
			//Validação da assinatura
	
			String path = getServlet().getServletContext().getRealPath("/WEB-INF/util/DSA_PROGIS_Publica.xml");
	        //String path = getServlet().getServletContext().getRealPath("/WEB-inf/util/DSA_PROGIS_Publica_TESTES.xml");
			AssinaturaDSA verificador = new AssinaturaDSA(path);
			if(!verificador.validarHash(loginUsuario.getBytes(),hashValidacao.getBytes())){
				throw new ActionServletException("atencao.assinatura.invalida", null, loginUsuario);
			}
		
		
	        
			
			
			/********************* Login do Usuário ***************************/
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario
					.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
			filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("localidadeElo");
			filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("localidade");
	
			filtroUsuario
					.adicionarCaminhoParaCarregamentoEntidade("usuarioSituacao");
			filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("usuarioTipo");
			filtroUsuario
					.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional.unidadeTipo");
			filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("funcionario");
			filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("empresa");
			filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("usuarioAbrangencia");
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN,loginUsuario));
			List<Usuario> list = new ArrayList(fachada.pesquisar(filtroUsuario,Usuario.class.getName()));
			
			
			//Valida o usuário
			if(list.size() != 0)
				usuarioLogado = list.get(0);
			else
				throw new ActionServletException("atencao.login.inexistente", loginUsuario);
			
			//Verifica a situação do usuário
			if (!this.verificarSituacaoUsuario(usuarioLogado)) {
				if (usuarioLogado.getUsuarioSituacao().getId().equals(UsuarioSituacao.INATIVO)) {
					throw new ActionServletException("atencao.usuario_invalido", null, usuarioLogado.getLogin());
				} else {
					throw new ActionServletException("atencao.usuario.cpf.inexistente", null,"");
				}
			}
			
			
			// Buscar as permissões do(s) grupo(s) do usuário
			FiltroGrupoFuncionalidadeOperacao filtroGrupoFuncionalidadeOperacao = new FiltroGrupoFuncionalidadeOperacao();
	        filtroGrupoFuncionalidadeOperacao.setCampoOrderBy(FiltroGrupoFuncionalidadeOperacao.FUNCIONALIDADE_NUMERO_ORDEM_MENU);
			filtroGrupoFuncionalidadeOperacao.setConsultaSemLimites(true);
			
			// Pesquisa os grupos do usuário
			Collection colecaoGruposUsuario = this.getFachada().pesquisarGruposUsuario(usuarioLogado.getId());
	
			// Seta na sessão os grupos aos que o usuário pertence
			sessao.setAttribute("colecaoGruposUsuario",colecaoGruposUsuario);
	
			// Cria o iterator dos grupos do usuário logado
			Iterator iterator = colecaoGruposUsuario.iterator();
	
			// Laço para adicionar todos os id's dos grupos no filtro
			// para pesquisar os acessos de todos os grupos do usuário
			// logado
			while (iterator.hasNext()) {
				Grupo grupoUsuario = (Grupo) iterator.next();
				filtroGrupoFuncionalidadeOperacao.adicionarParametro(new ParametroSimples(FiltroGrupoFuncionalidadeOperacao.GRUPO_ID,grupoUsuario.getId(),FiltroParametro.CONECTOR_OR));
				
				// Verifica se existe alguma mensagem para o
				// grupo que o usuario logado pertence.
				if(grupoUsuario.getMensagem()!=null 
						&& !grupoUsuario.getMensagem().equals("")){						
					// Coloca a mensagem na sessao
					sessao.setAttribute("mensagemGrupo",grupoUsuario.getMensagem());							
				}	
			}
	
			/*
			 * Pesquisa todas as permissões do usuário
			 * pesquisa ocorrência na tabela GrupoFuncionalidadeOperacao para os grupos
			 * aos quais o usuário logado pertence 
			 */
			Collection permissoes = this.getFachada().pesquisar(filtroGrupoFuncionalidadeOperacao,GrupoFuncionalidadeOperacao.class.getName());
	
			/*
			 * Pesquisa todas as restrições do usuário
			 * pesquisa se existe ocorrência na tabela UsuarioGrupoRestricao para o usuário 
			 * que está logado.
			 */
			Collection restricoes = this.pesquisarRestricaoUsuario(usuarioLogado);
			
			//Caso exista restrições para o usuário logado 
			//remove todas as funcionalidades que o usuário não tem acesso
			if(restricoes != null && !restricoes.isEmpty()){
				permissoes.removeAll(restricoes);
			}
			
			// Obtém a árvore de funcionalidades do sistema
			FuncionalidadeCategoria arvoreFuncionalidades = 
				this.getFachada().pesquisarArvoreFuncionalidades(permissoes);
			
			// Pega o ip do usuario logado.Esse ip sera usado no registrar transacao.
			// Para cada registrar transacao, sera gravado o ip na tabela usuario_acao.
			String ip = request.getRemoteAddr(); 
			usuarioLogado.setIpLogado(ip);
	
			// Coloca a árvore de funcionalidades/permissões na sessão
			// para
			// futuras consultas
			sessao.setAttribute("arvoreFuncionalidades",arvoreFuncionalidades);
			
			// Adiciona o usuário logado na sessão
			sessao.setAttribute("usuarioLogado", usuarioLogado);
			
			// Cria uma instância do menu para gerar a arvóre do menu
			MenuGCOM menu = new MenuGCOM();
			String menuGerado = menu.gerarMenu(arvoreFuncionalidades, usuarioLogado);
	
			// Coloca o menu gerado na sessão
			sessao.setAttribute("menuGCOM", menuGerado);
	
			// Seta o tempo máximo que o usuário tem para expirar sua
			// sessão
			// caso nenhuma requisição seja feita em 1000(mil) segundos
			sessao.setMaxInactiveInterval(900);
	
			/*
			 * Recupera a data do último acesso do usuário caso seja a
			 * primeira vez que o usuário acesse a aplicação cria uma
			 * nova data e seta essa data na sessão para a página de
			 * informaçãoes do usuário
			 */
			Date data = usuarioLogado.getUltimoAcesso();
			if (data == null) {
				data = new Date();
			}
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			String ultimaDataAcesso = formatter.format(data);
			sessao.setAttribute("dataUltimoAcesso", ultimaDataAcesso);
	
			// Cria a data atual e seta essa data na sessão
			data = new Date();
	        SimpleDateFormat formatterDataAtual = new SimpleDateFormat("dd/MM/yyyy");
			String dataAtual = formatterDataAtual.format(data);
			sessao.setAttribute("dataAtual", dataAtual);
	
			//Coloca na sessão a mensagem informando quantos dias falta para 
			//a senha do usuário expirar
			//sessao.setAttribute("mensagemExpiracao",mensagemExpiracao);
			
			/*
			 * Cria a lista de últimos acessos do usuário logado 
			 * e seta essa lista html na sessão para ser recuperada
			 * na página de informações do usuário 
			 */
			String ultimosAcessos = this.construirUltimosAcessos(usuarioLogado);
			sessao.setAttribute("ultimosAcessos",ultimosAcessos);
			
			// Registra o acesso do usuário no sistema
			this.getFachada().registrarAcessoUsuario(usuarioLogado);
			
			//Registra a Sessao para nao permitir acesso simultaneo no sistema
			SessaoHttpListener.registrarAcessoUsuario(sessao, this.getSistemaParametro());
			
			/*********************** FIM DO LOGIN ************************************/
			
			
			
			
			/*********************** Variáveis globais do sistema ************************************/
			
			// instacia a variavel de aplicacao tituloPagina com o valor cadastrado
			// em sistema parametro.
			if ( !Util.verificarNaoVazio((String)servlet.getServletContext().getAttribute("tituloPagina"))) {
	
				// Recupera o objeto que contém os parâmetros do sistema
				SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
				
				servlet.getServletContext().setAttribute("tituloPagina",sistemaParametro.getTituloPagina());
				servlet.getServletContext().setAttribute("logoMarca",sistemaParametro.getImagemLogomarca());
				servlet.getServletContext().setAttribute("nomeEmpresa",sistemaParametro.getNomeAbreviadoEmpresa());
	
				if( Util.verificarNaoVazio(sistemaParametro.getUrlhelp())){
					servlet.getServletContext().setAttribute("urlHelp",sistemaParametro.getUrlhelp());
				}
				if( Util.verificarNaoVazio(sistemaParametro.getIndicadorSenhaForte().toString())){
					servlet.getServletContext().setAttribute("indicadorSenhaForte",sistemaParametro.getIndicadorSenhaForte().toString());
				}
			}
	
			if ( !Util.verificarNaoVazio((String)servlet.getServletContext().getAttribute("rodapePagina"))) {
	
				// Recupera o objeto que contém as datas de Implementacao e
				// alteracao do Banco			
				DbVersaoBase dbVersaoBase = fachada.pesquisarDbVersaoBase();
	
				if ( dbVersaoBase != null ) {
	
					String versaoDataBase = Util.formatarData(dbVersaoBase.getVersaoDataBase());
	
					servlet.getServletContext().setAttribute("versaoDataBase",versaoDataBase);
				}
			}
	
			
			Internacionalizador.setLocale(
					(Locale)request.getSession(false).getAttribute(Globals.LOCALE_KEY));
			
			Internacionalizador.setProperties(
					(PropertyMessageResources)servlet.getServletContext().getAttribute(Globals.MESSAGES_KEY));
			
			/**************** Fim das variáveis globais ******************/
			
			
		
			
			/*************** Tratamento das variáveis GIS ***********************/
			
			//Identificador do logradouro x bairro
			String idLogradouroBairro = request.getParameter("lgbr_id");	
			
			//CEP do logradouro
			String idCepLogradouro = request.getParameter("lgcp_id");
	
			//Criticidade
			String nnCriticidade = request.getParameter("rgat_nncriticidade");
	
			//Identificador da matrícula do imóvel
			String matriculaImovel = request.getParameter("imov_id");
			
			//Identificador da localidade
			String idLocalidade = request.getParameter("loca_id");
			
			//Identificador do setor comercial
			String idSetorComercial = request.getParameter("stcm_id");
			
			//Ponto de referência
			String dsPontoReferencia = request.getParameter("rgat_dspontoreferencia");
			gisHelper.setDsPontoReferencia(dsPontoReferencia);
			
			//Complemento do endereço
			String dsComplementoEndereco = request.getParameter("rgat_dscomplementondereco");
					
			//Número do imóvel
			String numeroImovel = request.getParameter("rgat_nnimovel");
			
			
			//Coordenada norte da ocorrência
			String nnCoordenadaNorte = request.getParameter("rgat_nncoordenadanorte");	
			
			
			//Coordenada leste da ocorrência
			String nnCoordenadaLeste = request.getParameter("rgat_nncoordenadaleste");
			
			
			//Identificador da referência do endereço ? valor fixo=1
			String idEnderecoReferencia = request.getParameter("edrf_id");	
			
			//Diametro
			String diametro = request.getParameter("rgat_nndiametro");
			
			
			
			/***************************** Validações dos parâmetros ******************************/
			
			if(matriculaImovel.equals("") && idLogradouroBairro.equals("")){
				throw new ActionServletException("atencao.matricula.logradouro.invalidos",null,"");
			}
			
			if(nnCoordenadaNorte.equals("") || nnCoordenadaLeste.equals("")){
				throw new ActionServletException("atencao.coordenada.invalida",null,"");
			}
			
			
			
			Imovel imovel = new Imovel();
			LogradouroCep logradouroCep = new LogradouroCep();
			LogradouroBairro logradouroBairro = new LogradouroBairro();
	
			if(idLogradouroBairro != null && !idLogradouroBairro.equals("")){
				
				FiltroLogradouroBairro filtroBairro = new FiltroLogradouroBairro();
				
				filtroBairro
				.adicionarCaminhoParaCarregamentoEntidade("logradouro");
				filtroBairro
				.adicionarCaminhoParaCarregamentoEntidade("bairro");
				filtroBairro
				.adicionarCaminhoParaCarregamentoEntidade("bairro.municipio");
				filtroBairro
				.adicionarCaminhoParaCarregamentoEntidade("bairro.municipio.unidadeFederacao");
				
				filtroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID,idLogradouroBairro));
				filtroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.INDICADORUSO_BAIRRO,ConstantesSistema.INDICADOR_USO_ATIVO));
				//filtroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_BAIRRO,idLogradouroBairro));
				ArrayList<LogradouroBairro> listaBairro = new ArrayList<LogradouroBairro>(this.fachada.pesquisar(filtroBairro,LogradouroBairro.class.getName()));
				
				
				
				if(listaBairro.size() == 0)
					throw new ActionServletException("atencao.bairro.inexistente",null,"");
				
				logradouroBairro = listaBairro.get(0);
				
				
				FiltroLogradouroCep filtroCep = new FiltroLogradouroCep();
				
				filtroCep
				.adicionarCaminhoParaCarregamentoEntidade("logradouro");
				filtroCep
				.adicionarCaminhoParaCarregamentoEntidade("cep");
				filtroCep
				.adicionarCaminhoParaCarregamentoEntidade("logradouro.logradouroTipo");	
				filtroCep
				.adicionarCaminhoParaCarregamentoEntidade("logradouro.logradouroTitulo");
		
		
				filtroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_LOGRADOURO,logradouroBairro.getLogradouro().getId()));
				filtroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
				if(idCepLogradouro != null && !idCepLogradouro.equals(""))
					filtroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID,idCepLogradouro));
				ArrayList<LogradouroCep> listaCep = new ArrayList<LogradouroCep>(this.fachada.pesquisar(filtroCep,LogradouroCep.class.getName()));
				
				if(listaCep.size() == 0)
					throw new ActionServletException("atencao.cep.inexistente",null,"");
				if(listaCep.size()>1){
					gisHelper.setInformarCep("true");						
				}else if(listaCep.size()==1){
					logradouroCep = (LogradouroCep) Util
					.retonarObjetoDeColecao(listaCep);
				}
			}
			
			
			
			if(matriculaImovel != null && !matriculaImovel.equals(""))
				imovel.setId(new Integer(matriculaImovel).intValue());
			imovel.setLogradouroCep(logradouroCep);
			imovel.setLogradouroBairro(logradouroBairro);
			imovel.setComplementoEndereco(dsComplementoEndereco);
			imovel.setNumeroImovel(numeroImovel);
			
			
			if (idEnderecoReferencia != null && !idEnderecoReferencia.equals("")) {	
				Collection colecaoEnderecoReferencia = null;
				FiltroEnderecoReferencia filtroEnderecoReferencia = new FiltroEnderecoReferencia();
				filtroEnderecoReferencia
						.adicionarParametro(new ParametroSimples(
								FiltroEnderecoReferencia.ID,
								idEnderecoReferencia));
				filtroEnderecoReferencia
						.adicionarParametro(new ParametroSimples(
								FiltroEnderecoReferencia.INDICADORUSO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
				colecaoEnderecoReferencia = fachada.pesquisar(
						filtroEnderecoReferencia, EnderecoReferencia.class
								.getName());
	
				if (colecaoEnderecoReferencia == null
						|| colecaoEnderecoReferencia.isEmpty()) {
					
				} else {
					EnderecoReferencia enderecoReferencia = (EnderecoReferencia) Util
							.retonarObjetoDeColecao(colecaoEnderecoReferencia);
					imovel.setEnderecoReferencia(enderecoReferencia);
					}
			}
			
			
			if(diametro!=null && !diametro.equals("")){
				gisHelper.setNnDiametro(diametro);
			}
			
	
			gisHelper.setImovel(imovel);
			gisHelper.setDsPontoReferencia(dsPontoReferencia);
			gisHelper.setLocalidade(idLocalidade);
			gisHelper.setNnCriticidade(nnCriticidade);
			gisHelper.setSetorComercial(idSetorComercial);
			gisHelper.setNnCoordenadaNorte(nnCoordenadaNorte);
			gisHelper.setNnCoordenadaLeste(nnCoordenadaLeste);
	
			
			if(idLogradouroBairro.equals("")&& nnCoordenadaNorte != null && !(nnCoordenadaNorte.equals("")) 
					&& nnCoordenadaLeste != null && !(nnCoordenadaLeste.equals(""))) 
				gisHelper.setIndicadorCoordenadaSemLogradouro((Short)ConstantesSistema.SIM);
			else
				gisHelper.setIndicadorCoordenadaSemLogradouro((Short)ConstantesSistema.NAO);
			
					
			
			sessao.setAttribute("gisHelper",gisHelper);
			
			//indicador de que a origem da chamada é uma requisição GIS
			sessao.setAttribute("origemGIS", Boolean.TRUE);
			sessao.setAttribute("usuarioLogado",usuarioLogado);
			
			return retorno;
		}
		
		
		/**
		 * 
		 * Requisição vinda do AcquaGIS
		 * 
		 **/
		
		else{

			ActionForward actionForward = null;
			
			String loginUsuario = request.getParameter("usur_nmlogin");
			
			String rgat_idselecionados = request.getParameter("rgat_idselecionados");
			
			System.out.println("ACQUAGIS PARTE 1 - " + loginUsuario + " & " + rgat_idselecionados);
			
			if (loginUsuario != null 
					&& !loginUsuario.trim().equals("") 
					&& rgat_idselecionados != null
					&& !rgat_idselecionados.trim().equals("")) {
				//Usuario usuarioLogado = getUsuarioLogado(request);
				
				FiltroUsuario filtroUsuario = new FiltroUsuario();
				filtroUsuario.adicionarParametro(new ParametroSimples(
						FiltroUsuario.LOGIN, loginUsuario));
				Usuario usuarioLogado = (Usuario) Util.retonarObjetoDeColecao(
						fachada.pesquisar(filtroUsuario, Usuario.class.getName()));
				
				if (usuarioLogado != null) {
					Collection idsRASelecionados = Util.separarCamposString(";",
							rgat_idselecionados);
					
					// Faz a validacao das RAs no GSAN
					Iterator iRASelecionados = idsRASelecionados.iterator();
					while(iRASelecionados.hasNext()) {
						String idRa = (String) iRASelecionados.next();
						
						FiltroRegistroAtendimento filtroRA = new FiltroRegistroAtendimento();
						filtroRA.adicionarParametro(new ParametroSimples(
								FiltroRegistroAtendimento.ID, idRa));
						
						RegistroAtendimento ra = (RegistroAtendimento) Util.retonarObjetoDeColecao(
								fachada.pesquisar(filtroRA, RegistroAtendimento.class.getName()));
						
						if (ra == null) {
							retorno = GisRetornoMotivo.REGISTRO_ATENDIMENTO_INEXISTENTE;
							try {
								PrintWriter pw = response.getWriter();
								pw.println(retorno);
								pw.flush();
							} catch (IOException e) {
								e.printStackTrace();
							}
							return actionForward;
						}
					}
					
					listaRAIntegracaoGIS.put(loginUsuario, idsRASelecionados);
					try {
						PrintWriter pw = response.getWriter();
						pw.println(retorno);
						pw.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return actionForward;
				} else {
					retorno = GisRetornoMotivo.LOGIN_USUARIO_INEXISTENTE;
					try {
			
						PrintWriter pw = response.getWriter();
						pw.println(retorno);
						pw.flush();
			
					} catch (IOException e) {
						e.printStackTrace();
					}
					return actionForward;
			
				}
			
			} 
			
			
			
			// Flag usada para verificar se a requisição gis veio da tela correta.
			//inserção de um R.A (Aba nº 02 - Dados do local de ocorrência
			HttpSession sessaoUsuario = SessaoHttpListener.listaSessoesAtivasGis.get(loginUsuario);
			
			if(sessaoUsuario == null){
				retorno = GisRetornoMotivo.LOGIN_USUARIO_INEXISTENTE;		
			}else{
					     
			    GisHelper gisHelper = new GisHelper();
				
					//Login do usuário
						
					if(loginUsuario==null){
						retorno = GisRetornoMotivo.LOGIN_USUARIO_INEXISTENTE;
					}		
			
					//Identificador do logradouro x bairro
					String idLogradouroBairro = request.getParameter("lgbr_id");			
			
					
					//Ponto de referência
					String dsPontoReferencia = request.getParameter("rgat_dspontoreferencia");
					gisHelper.setDsPontoReferencia(dsPontoReferencia);
					
					//Complemento do endereço
					String dsComplementoEndereco = request.getParameter("rgat_dscomplementondereco");
							
					//Número do imóvel
					String numeroImovel = request.getParameter("rgat_nnimovel");
					
					
					//Coordenada norte da ocorrência
					String nnCoordenadaNorte = request.getParameter("rgat_nncoordenadanorte");	
					if("".equals(nnCoordenadaNorte) || nnCoordenadaNorte == null){
						retorno = GisRetornoMotivo.COORDENADA_NORTE_INVALIDA;
					}else{
						gisHelper.setNnCoordenadaNorte(nnCoordenadaNorte);
					}
					
					//Coordenada leste da ocorrência
					String nnCoordenadaLeste = request.getParameter("rgat_nncoordenadaleste");
					if("".equals(nnCoordenadaLeste) || nnCoordenadaLeste == null){
						retorno = GisRetornoMotivo.COORDENADA_LESTE_INVALIDA;
					}else{
						gisHelper.setNnCoordenadaLeste(nnCoordenadaLeste);
					}
					
					//Identificador da referência do endereço ? valor fixo=1
					String idEnderecoReferencia = request.getParameter("edrf_id");	
					if("".equals(idEnderecoReferencia)|| idEnderecoReferencia == null){
						retorno = GisRetornoMotivo.IDENTIFICADOR_REFERENCIA_ENDERECO_INVALIDO;
					}
					
					System.out.println("ACQUAGIS PARTE 2 - " + idLogradouroBairro + " & " + dsPontoReferencia + " & " + dsComplementoEndereco
					+ " & " + numeroImovel + " & " + nnCoordenadaNorte + " & " + nnCoordenadaLeste + " & " + idEnderecoReferencia);
					
							
					//Carregando os objetos finais com o LogradouroCep e
					// LogradouroBairro
					if(!(idLogradouroBairro.equals(""))) {
					
					Imovel imovel = new Imovel();	
					
					LogradouroBairro logradouroBairro = new LogradouroBairro();
					
					if (idLogradouroBairro != null) {
			
						Collection colecaoLogradouroBairro = null;
						FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
			
						// Objetos que serão retornados pelo hibernate.
						filtroLogradouroBairro
						.adicionarCaminhoParaCarregamentoEntidade("logradouro");
						filtroLogradouroBairro
						.adicionarCaminhoParaCarregamentoEntidade("bairro");
						filtroLogradouroBairro
						.adicionarCaminhoParaCarregamentoEntidade("bairro.municipio");
						filtroLogradouroBairro
						.adicionarCaminhoParaCarregamentoEntidade("bairro.municipio.unidadeFederacao");
			
			
						filtroLogradouroBairro.adicionarParametro(new ParametroSimples(
								FiltroLogradouroBairro.ID,idLogradouroBairro));
			
						filtroLogradouroBairro.adicionarParametro(new ParametroSimples(
								FiltroLogradouroBairro.INDICADORUSO_BAIRRO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
			
						colecaoLogradouroBairro = fachada.pesquisar(filtroLogradouroBairro,
								LogradouroBairro.class.getName());
			
						if (colecaoLogradouroBairro == null || colecaoLogradouroBairro.isEmpty()) {
							retorno = GisRetornoMotivo.IDENTIFICADOR_LOGRADOURO_BAIRRO_INEXISTENTE;			
						} else {
						  logradouroBairro = (LogradouroBairro) Util
									.retonarObjetoDeColecao(colecaoLogradouroBairro);
						}
					}
							
					
					LogradouroCep logradouroCep  = new LogradouroCep();
					if (logradouroBairro!= null) {
			
						Collection colecaoLogradouroCep = null;					
						FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
			
						// Objetos que serão retornados pelo hibernate.
						filtroLogradouroCep
								.adicionarCaminhoParaCarregamentoEntidade("logradouro");
						filtroLogradouroCep
						.adicionarCaminhoParaCarregamentoEntidade("cep");
						filtroLogradouroCep
						.adicionarCaminhoParaCarregamentoEntidade("logradouro.logradouroTipo");	
						filtroLogradouroCep
						.adicionarCaminhoParaCarregamentoEntidade("logradouro.logradouroTitulo");		
			
			
						filtroLogradouroCep.adicionarParametro(new ParametroSimples(
								FiltroLogradouroCep.ID_LOGRADOURO, logradouroBairro.getLogradouro().getId()));
			
						filtroLogradouroCep.adicionarParametro(new ParametroSimples(
								FiltroLogradouroCep.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
			
						colecaoLogradouroCep = fachada.pesquisar(filtroLogradouroCep,
								LogradouroCep.class.getName());
			
						if (colecaoLogradouroCep == null || colecaoLogradouroCep.isEmpty()) {
							retorno = GisRetornoMotivo.IDENTIFICADOR_LOGRADOURO_CEP_INEXISTENTE;				
						} else if(colecaoLogradouroCep.size()>1){
							gisHelper.setInformarCep("true");						
						}else if(colecaoLogradouroCep.size()==1){
							logradouroCep = (LogradouroCep) Util
							.retonarObjetoDeColecao(colecaoLogradouroCep);
						}
					}
				
					
					imovel.setLogradouroCep(logradouroCep);
					imovel.setLogradouroBairro(logradouroBairro);
					imovel.setComplementoEndereco(dsComplementoEndereco);
					imovel.setNumeroImovel(numeroImovel);
					
					
					if (dsPontoReferencia != null) {
			
						Collection colecaoEnderecoReferencia = null;
			
						FiltroEnderecoReferencia filtroEnderecoReferencia = new FiltroEnderecoReferencia();
			
						filtroEnderecoReferencia
								.adicionarParametro(new ParametroSimples(
										FiltroEnderecoReferencia.ID,
										idEnderecoReferencia));
			
						filtroEnderecoReferencia
								.adicionarParametro(new ParametroSimples(
										FiltroEnderecoReferencia.INDICADORUSO,
										ConstantesSistema.INDICADOR_USO_ATIVO));
			
						colecaoEnderecoReferencia = fachada.pesquisar(
								filtroEnderecoReferencia, EnderecoReferencia.class
										.getName());
			
						if (colecaoEnderecoReferencia == null
								|| colecaoEnderecoReferencia.isEmpty()) {
							retorno = GisRetornoMotivo.IDENTIFICADOR_REFERENCIA_ENDERECO_INEXISTENTE;					
						} else {
							EnderecoReferencia enderecoReferencia = (EnderecoReferencia) Util
									.retonarObjetoDeColecao(colecaoEnderecoReferencia);
				
							imovel.setEnderecoReferencia(enderecoReferencia);
							}
					}
					
					
					gisHelper.setImovel(imovel);			
					
					}
					if(idLogradouroBairro.equals("")&& nnCoordenadaNorte != null && !(nnCoordenadaNorte.equals("")) 
							&& nnCoordenadaLeste != null && !(nnCoordenadaLeste.equals(""))) 
						gisHelper.setIndicadorCoordenadaSemLogradouro((Short)ConstantesSistema.SIM);
					else
						gisHelper.setIndicadorCoordenadaSemLogradouro((Short)ConstantesSistema.NAO);
					
					//Colocando o helper na sessão.
					sessaoUsuario.setAttribute("gisHelper",gisHelper);	
			}
			
			
			System.out.println("ACQUAGIS PARTE 3 - " + retorno);	
			
			try{
				
			    PrintWriter pw = response.getWriter();	  
			    pw.println(retorno);	
			    pw.flush();
			    
			     } catch (IOException e) {
			           e.printStackTrace();
			     }
			
			
			return actionForward;
			
			
		}
	}
	
	
	private String construirUltimosAcessos(Usuario usuarioLogado){
		//Cria a variável que vai armazenar a string contendo o html do list com 
		//os últimos acessos do usuário
		String retorno = null;
		StringBuilder ultimosAcessos = new StringBuilder();

		Collection<UsuarioFavorito> colecaoUltimosAcessos = new ArrayList();
		
		colecaoUltimosAcessos = 
			this.getFachada().pesquisarUsuarioFavorito(usuarioLogado.getId());		
		
		/*
		 * Caso a coleção de últimos acessos não esteja vazia 
		 * cria a lista contendo os últimos acessos do usuário
		 * Caso contrário cria uma lista com nenhuma funcionalidade
		 * para último acessos
		 */
		if(colecaoUltimosAcessos != null && !colecaoUltimosAcessos.isEmpty()){
			
			/*
			 * Trecho para cria a parte inicial do list html
			 */
			ultimosAcessos.append("<select name=\"ultimoacesso\" id=\"ultimosacessos\" onchange=\"javascript:abrirUltimoAcesso();\" style=\"width:130px\">");
			ultimosAcessos.append(System.getProperty("line.separator"));
			ultimosAcessos.append("<option value=\"-1\">Ultimos Acessos</option>");
			ultimosAcessos.append(System.getProperty("line.separator"));
			ultimosAcessos.append("<option value=\"-1\">- - - - - - - - - - - - - - - -</option>");
			
            
			//Laço para adicionar as funcionalidades a lista de últimos acessos
			for(UsuarioFavorito usuarioFavorito : colecaoUltimosAcessos){
				Funcionalidade funcionalidade = usuarioFavorito.getFuncionalidade();
				ultimosAcessos.append("<option value=\""+funcionalidade.getCaminhoUrl()+"\">"+funcionalidade.getDescricao()+"</option>");
				ultimosAcessos.append(System.getProperty("line.separator"));
			}
			
			//Fecha o list html
			ultimosAcessos.append("</select>");
			retorno = ultimosAcessos.toString();
		}else{
			
			/*
			 * Cria o list html sem nenhuma funcionalidade dentro 
			 */
			ultimosAcessos.append("<select name=\"ultimoacesso\" id=\"ultimosacessos\" onchange=\"javascript:abrirUltimoAcesso();\" style=\"width:130px\">");
			ultimosAcessos.append(System.getProperty("line.separator"));
			ultimosAcessos.append("<option value=\"-1\"><font size=\"1\">Ultimos Acessos</font></option>");
			ultimosAcessos.append(System.getProperty("line.separator"));
			ultimosAcessos.append("<option value=\"-1\">- - - - - - - - - - - - - - - -</option>");
			ultimosAcessos.append("</select>");
			retorno = ultimosAcessos.toString();
		}

		//Retorna o html de uma lista contendo as funcionalidades últimas acessadas pelo usuário
		return retorno;
	}
	
	
private Collection<GrupoFuncionalidadeOperacao> pesquisarRestricaoUsuario(Usuario usuarioLogado){
		
		//Cria a coleção que vai armazenar as restrições do usuário
		Collection<GrupoFuncionalidadeOperacao> restricoes = new ArrayList();
		
		//Cria o filtro para pesquisar todas as restrições do usuário no sistema
		FiltroUsuarioGrupoRestricao filtroUsuarioGrupoRestricao = new FiltroUsuarioGrupoRestricao();
        filtroUsuarioGrupoRestricao.adicionarParametro(new ParametroSimples(FiltroUsuarioGrupoRestricao.USUARIO_ID, usuarioLogado.getId()));
        filtroUsuarioGrupoRestricao.setConsultaSemLimites(true);
        
        Collection colecaoUsuarioRestricoes = Fachada.getInstancia().pesquisar(filtroUsuarioGrupoRestricao, UsuarioGrupoRestricao.class.getName());

        //Caso exista restrição para o usuário
        //Existe ocorrência para o id do usuário logado na tabela UsuarioGrupoRestricao
        if(colecaoUsuarioRestricoes != null && !colecaoUsuarioRestricoes.isEmpty()){
        	
        	//Cria o iterator das restrições do usuário 
        	Iterator<UsuarioGrupoRestricao> iterator = colecaoUsuarioRestricoes.iterator();
        	
        	while(iterator.hasNext()){

        		//Recupera a restrição do iterator
        		UsuarioGrupoRestricao usuarioGrupoRestricao = iterator.next();
        		
        		//Recupera qual a funcionalidade e sua operação a qual o usuário tem restrição 
        		//para um determinado grupo
        		GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = new GrupoFuncionalidadeOperacao();
        		GrupoFuncionalidadeOperacaoPK grupoFuncionalidadeOperacaoPK = new GrupoFuncionalidadeOperacaoPK();
        		grupoFuncionalidadeOperacaoPK.setGrupoId(usuarioGrupoRestricao.getComp_id().getGrupoId());
        		grupoFuncionalidadeOperacaoPK.setFuncionalidadeId(usuarioGrupoRestricao.getComp_id().getFuncionalidadeId());
        		grupoFuncionalidadeOperacaoPK.setOperacaoId(usuarioGrupoRestricao.getComp_id().getOperacaoId());
        		grupoFuncionalidadeOperacao.setComp_id(grupoFuncionalidadeOperacaoPK);
        		
        		//Adiciona a operação a coleção de restrição
	        	restricoes.add(grupoFuncionalidadeOperacao);
        	}
        }
		
        //Retorna a coleção de restrições do sistema
		return restricoes;
	}

	private boolean verificarSituacaoUsuario(Usuario usuarioLogado) {
		boolean retorno = true;
		// Recupera a situação do usuário
		UsuarioSituacao usuarioSituacao = usuarioLogado.getUsuarioSituacao();
	
		/*
		 * Caso a situação do usuário não seja igual a ativo ou seja igual a
		 * pendente retorna uma flag indicando que o usuário não pode acessar o
		 * sistema
		 */
		if ((!usuarioSituacao.getId().equals(UsuarioSituacao.ATIVO))
				&& (!usuarioSituacao.getId().equals(
						UsuarioSituacao.PENDENTE_SENHA))) {
			retorno = false;
		}
	
		// Retorna uma flag indicando se a situção do usuário permite o acesso
		// ao sistema
		return retorno;
	}
}
	