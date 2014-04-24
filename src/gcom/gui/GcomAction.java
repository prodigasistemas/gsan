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
package gcom.gui;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.Filtro;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * Action pai onde todas as 
 * action do sistema herda essa classe
 * 
 * @author Administrador
 */
public class GcomAction extends DispatchAction {
	
	private SistemaParametro sistemaParametro;
	private Fachada fachada;
	
	/**
	 * Reporta o erro de volta à página
	 * 
	 * @param request
	 *            O request usado atualmente
	 * @param chave
	 *            chave da descrição do erro no arquivo de propriedades do
	 *            struts
	 */
	protected void reportarErros(HttpServletRequest request, String chave) {
		ActionErrors errors = new ActionErrors();

		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(chave));

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
	}

	/**
	 * Este método retorna o id temporário baseado no timestamp do objeto para
	 * exibição na memória, enquanto o usuário estiver inserindo
	 * 
	 * @param objeto
	 * @return
	 */
	public static long obterTimestampIdObjeto(Object objeto) {
		long retorno = 0L;

		try {
			Integer idObjeto = (Integer) objeto.getClass().getMethod("getId",
					(Class[]) null).invoke(objeto, (Object[]) null);

			Object ultimaAlteracao = objeto.getClass().getMethod(
					"getUltimaAlteracao", (Class[]) null).invoke(objeto,
					(Object[]) null);
			if (ultimaAlteracao != null) {
				retorno = (Long) ultimaAlteracao.getClass().getMethod(
						"getTime", (Class[]) null).invoke(ultimaAlteracao,
						(Object[]) null);
			} else {
				throw new ActionServletException(
						"atencao.registro.sem.timestamp");
			}

			// Se o objeto já estiver na base, o seu id é adicionado ao seu
			// timestamp para deslocar um pouco
			// o timestamp. Isso acontece porque na base podem existir registros
			// com o
			// mesmo timestamp numa carga de dados.

			if (idObjeto != null) {
				retorno = retorno + idObjeto;
			}

		} catch (IllegalArgumentException e) {
			throw new ActionServletException("erro.sistema");
		} catch (SecurityException e) {
			throw new ActionServletException("erro.sistema");
		} catch (IllegalAccessException e) {
			throw new ActionServletException("erro.sistema");
		} catch (InvocationTargetException e) {
			throw new ActionServletException("erro.sistema");
		} catch (NoSuchMethodException e) {
			try {
				Object compId = (Object) objeto.getClass().getMethod(
						"getComp_id", (Class[]) null).invoke(objeto,
						(Object[]) null);

				Object ultimaAlteracao = objeto.getClass().getMethod(
						"getUltimaAlteracao", (Class[]) null).invoke(objeto,
						(Object[]) null);
				retorno = (Long) ultimaAlteracao.getClass().getMethod(
						"getTime", (Class[]) null).invoke(ultimaAlteracao,
						(Object[]) null);

				// Se o objeto já estiver na base, o seu id é adicionado ao seu
				// timestamp para deslocar um pouco
				// o timestamp. Isso acontece porque na base podem existir
				// registros
				// com o
				// mesmo timestamp numa carga de dados.

				if (compId != null) {
					retorno = retorno + compId.hashCode();
				}

			} catch (IllegalArgumentException ex) {
				throw new ActionServletException("erro.sistema");
			} catch (SecurityException ex) {
				throw new ActionServletException("erro.sistema");
			} catch (IllegalAccessException ex) {
				throw new ActionServletException("erro.sistema");
			} catch (InvocationTargetException ex) {
				throw new ActionServletException("erro.sistema");
			} catch (NoSuchMethodException ex) {
				throw new ActionServletException("erro.sistema");
			}

		}

		return retorno;

	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param request
	 *            Descrição do parâmetro
	 * @param chave
	 *            Descrição do parâmetro
	 * @param mensagem
	 *            Descrição do parâmetro
	 */
	protected void reportarErrosMensagem(HttpServletRequest request,
			String chave, String mensagem) {
		ActionErrors errors = new ActionErrors();

		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(chave, mensagem));

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param request
	 *            Descrição do parâmetro
	 * @param chaveMensagem
	 *            Descrição do parâmetro
	 * @param exception
	 *            Descrição do parâmetro
	 */
	protected void reportarErros(HttpServletRequest request,
			String chaveMensagem, Exception exception) {
		ActionErrors errors = new ActionErrors();

		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(chaveMensagem));

		// Recupera a sessao para colocar a exceção que será mostrada na tela de
		// erro
		HttpSession sessao = request.getSession(false);

		// Se a sessão não existir, então o problema deverá ser reportado sem
		// intervenção do usuário
		if (sessao == null) {
			// chamar a funcao que manda o email com o erro para o administrador
			// do sistema
			try {
				ServicosEmail.enviarMensagem("gcom@compesa.com.br",
						ServicosEmail.EMAIL_ADMINISTRADOR,
						 "Urgente: Erro no Sistema",
						ServicosEmail.processarExceptionParaEnvio(exception));
			} catch (ErroEmailException ex) {
				ex.printStackTrace();
			}
		} else {
			// Mandar a exceção na sessão para que o usuário possa reportá-la
			sessao.setAttribute("excecaoPaginaErro", exception);
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param request
	 *            Descrição do parâmetro
	 * @param chaveMensagem
	 *            Descrição do parâmetro
	 * @param mensagem
	 *            Descrição do parâmetro
	 * @param exception
	 *            Descrição do parâmetro
	 */
	protected void reportarErrosMensagem(HttpServletRequest request,
			String chaveMensagem, String mensagem, Exception exception) {
		ActionErrors errors = new ActionErrors();

		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(chaveMensagem,
				mensagem));

		// Recupera a sessao para colocar a exceção que será mostrada na tela de
		// erro
		HttpSession sessao = request.getSession(false);

		// Se a sessão não existir, então o problema deverá ser reportado sem
		// intervenção do usuário
		if (sessao == null) {
			// chamar a funcao que manda o email com o erro para o administrador
			// do sistema
			try {
				ServicosEmail.enviarMensagem("gcom@compesa.com.br",
						ServicosEmail.EMAIL_ADMINISTRADOR,
						"Urgente: Erro no Sistema",
						ServicosEmail.processarExceptionParaEnvio(exception));
			} catch (ErroEmailException ex) {
				ex.printStackTrace();
			}
		} else {
			// Mandar a exceção na sessão para que o usuário possa reportá-la
			sessao.setAttribute("excecaoPaginaErro", exception);
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
	}

	/**
	 * Faz a verificação se o usuário está logado no sistema
	 * 
	 * @param sessao
	 *            Descrição do parâmetro
	 * @param parametroSessao
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	protected boolean verificarUsuarioLogado(HttpSession sessao,
			String parametroSessao) {

		return (sessao != null && sessao.getAttribute(parametroSessao) != null);
	}

	/**
	 * Método que converte uma string para um inteiro, caso exista erro de
	 * conversão, a constante de número não informado é retornada
	 * 
	 * @param target
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	protected int converterStringToInt(String target) {
		try {
			return Integer.parseInt(target);
		} catch (NumberFormatException e) {
			return ConstantesSistema.NUMERO_NAO_INFORMADO;
		}
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param request
	 *            Descrição do parâmetro
	 * @param mensagemPaginaSucesso
	 *            Descrição do parâmetro
	 * @param labelPaginaSucesso
	 *            Descrição do parâmetro
	 * @param caminhoFuncionalidade
	 *            Descrição do parâmetro
	 */
	protected void montarPaginaSucesso(HttpServletRequest request,
			String mensagemPaginaSucesso, String labelPaginaSucesso,
			String caminhoFuncionalidade) {

		request.setAttribute("labelPaginaSucesso", labelPaginaSucesso);
		request.setAttribute("mensagemPaginaSucesso", mensagemPaginaSucesso);
		request.setAttribute("caminhoFuncionalidade", caminhoFuncionalidade);

	}

	protected void montarPaginaSucesso(HttpServletRequest request,
			String mensagemPaginaSucesso, String labelPaginaSucesso,
			String caminhoFuncionalidade, String caminhoAtualizarRegistro,
			String labelPaginaAtualizacao) {

		request.setAttribute("labelPaginaAtualizacao", labelPaginaAtualizacao);
		request.setAttribute("caminhoAtualizarRegistro",
				caminhoAtualizarRegistro);
		montarPaginaSucesso(request, mensagemPaginaSucesso, labelPaginaSucesso,
				caminhoFuncionalidade);

	}

	protected void montarPaginaSucesso(HttpServletRequest request,
			String mensagemPaginaSucesso, String labelPaginaSucesso,
			String caminhoFuncionalidade, String caminhoAtualizarRegistro,
			String labelPaginaAtualizacao, String labelGerarOrdemServico,
			String caminhoGerarOrdemServico) {

		request.setAttribute("labelGerarOrdemServico", labelGerarOrdemServico);
		request.setAttribute("caminhoGerarOrdemServico",
				caminhoGerarOrdemServico);
		montarPaginaSucesso(request, mensagemPaginaSucesso, labelPaginaSucesso,
				caminhoFuncionalidade, caminhoAtualizarRegistro,
				labelPaginaAtualizacao);

	}
	
	protected void montarPaginaSucessoComVoltarJavascript(HttpServletRequest request,
			String mensagemPaginaSucesso, String labelPaginaSucesso,
			String caminhoFuncionalidade, String caminhoAtualizarRegistro,
			String labelPaginaAtualizacao,String labelVoltar,
			String caminhoVoltar) {

		request.setAttribute("labelVoltarJavascript", labelVoltar);
		request.setAttribute("caminhoVoltarJavascript", caminhoVoltar);
		
		montarPaginaSucesso(request, mensagemPaginaSucesso, labelPaginaSucesso,
				caminhoFuncionalidade, caminhoAtualizarRegistro,
				labelPaginaAtualizacao );
	}	

	protected void montarPaginaSucesso(HttpServletRequest request,
			String mensagemPaginaSucesso, String labelPaginaSucesso,
			String caminhoFuncionalidade, String caminhoAtualizarRegistro,
			String labelPaginaAtualizacao, String labelGerarOrdemServico,
			String caminhoGerarOrdemServico, String labelVoltar,
			String caminhoVoltar) {

		request.setAttribute("labelVoltar", labelVoltar);
		request.setAttribute("caminhoVoltar", caminhoVoltar);
		montarPaginaSucesso(request, mensagemPaginaSucesso, labelPaginaSucesso,
				caminhoFuncionalidade, caminhoAtualizarRegistro,
				labelPaginaAtualizacao, labelGerarOrdemServico,
				caminhoGerarOrdemServico);

	}

	protected void montarPaginaSucessoComImpressora(HttpServletRequest request,
			String mensagemPaginaSucesso, String labelPaginaSucesso,
			String caminhoFuncionalidade, String caminhoAtualizarRegistro,
			String labelPaginaAtualizacao, String caminhoRelatorio) {

		request.setAttribute("labelPaginaAtualizacao", labelPaginaAtualizacao);
		request.setAttribute("caminhoAtualizarRegistro",
				caminhoAtualizarRegistro);
		request.setAttribute("caminhoRelatorio", caminhoRelatorio);
		montarPaginaSucesso(request, mensagemPaginaSucesso, labelPaginaSucesso,
				caminhoFuncionalidade);

	}

	protected void montarPaginaSucessoComImpressora(HttpServletRequest request,
			String mensagemPaginaSucesso, String labelPaginaSucesso,
			String caminhoFuncionalidade, String caminhoAtualizarRegistro,
			String labelPaginaAtualizacao, String labelGerarOrdemServico,
			String caminhoGerarOrdemServico, String caminhoRelatorio) {

		request.setAttribute("labelGerarOrdemServico", labelGerarOrdemServico);
		request.setAttribute("caminhoGerarOrdemServico",
				caminhoGerarOrdemServico);
		request.setAttribute("caminhoRelatorio", caminhoRelatorio);
		montarPaginaSucesso(request, mensagemPaginaSucesso, labelPaginaSucesso,
				caminhoFuncionalidade, caminhoAtualizarRegistro,
				labelPaginaAtualizacao);

	}
	
	protected void montarPaginaSucessoUmRelatorio(HttpServletRequest request,
			String mensagemPaginaSucesso, String labelPaginaSucesso,
			String caminhoFuncionalidade, String caminhoAtualizarRegistro,
			String labelPaginaAtualizacao, String labelGerarOrdemServico,
			String caminhoGerarOrdemServico, String mensagemRelatorioLink1,
			String caminhoRelatorioLink1) {

		request.setAttribute("labelGerarOrdemServico", labelGerarOrdemServico);
		request.setAttribute("caminhoGerarOrdemServico",
				caminhoGerarOrdemServico);
		request.setAttribute("mensagemRelatorioLink1", mensagemRelatorioLink1);
		request.setAttribute("caminhoRelatorioLink1", caminhoRelatorioLink1);
		
		montarPaginaSucesso(request, mensagemPaginaSucesso, labelPaginaSucesso,
				caminhoFuncionalidade, caminhoAtualizarRegistro,
				labelPaginaAtualizacao);

	}
	
	protected void montarPaginaSucessoUmRelatorio(HttpServletRequest request,
			String mensagemPaginaSucesso, String labelPaginaSucesso,
			String caminhoFuncionalidade, String caminhoAtualizarRegistro,
			String labelPaginaAtualizacao, String mensagemRelatorioLink1,
			String caminhoRelatorioLink1) {

		request.setAttribute("mensagemRelatorioLink1", mensagemRelatorioLink1);
		request.setAttribute("caminhoRelatorioLink1", caminhoRelatorioLink1);
		
		montarPaginaSucesso(request, mensagemPaginaSucesso, labelPaginaSucesso,
				caminhoFuncionalidade, caminhoAtualizarRegistro,
				labelPaginaAtualizacao);

	}
	
	protected void montarPaginaSucessoDoisRelatorios(HttpServletRequest request,
			String mensagemPaginaSucesso, String labelPaginaSucesso,
			String caminhoFuncionalidade, String caminhoAtualizarRegistro,
			String labelPaginaAtualizacao, String mensagemRelatorioLink1,
			String caminhoRelatorioLink1, String mensagemRelatorioLink2,
			String caminhoRelatorioLink2) {

		request.setAttribute("mensagemRelatorioLink1", mensagemRelatorioLink1);
		request.setAttribute("caminhoRelatorioLink1", caminhoRelatorioLink1);
		request.setAttribute("mensagemRelatorioLink2", mensagemRelatorioLink2);
		request.setAttribute("caminhoRelatorioLink2", caminhoRelatorioLink2);
		
		montarPaginaSucesso(request, mensagemPaginaSucesso, labelPaginaSucesso,
				caminhoFuncionalidade, caminhoAtualizarRegistro,
				labelPaginaAtualizacao);

	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param request
	 *            Descrição do parâmetro
	 * @param labelPaginaConfirmacao
	 *            Descrição do parâmetro
	 * @param mensagemSuperiorConfirmacao
	 *            Descrição do parâmetro
	 * @param mensagemInferiorConfirmacao
	 *            Descrição do parâmetro
	 * @param caminhoFuncionalidade
	 *            Descrição do parâmetro
	 */
	protected void montarPaginaConfirmacao(HttpServletRequest request,
			String labelPaginaConfirmacao, String mensagemSuperiorConfirmacao,
			String mensagemInferiorConfirmacao, String caminhoFuncionalidade) {
		request.setAttribute("labelPaginaConfirmacao", labelPaginaConfirmacao);
		request.setAttribute("mensagemSuperiorConfirmacao",
				mensagemSuperiorConfirmacao);
		request.setAttribute("mensagemInferiorConfirmacao",
				mensagemInferiorConfirmacao);
		request.setAttribute("caminhoFuncionalidade", caminhoFuncionalidade);

	}

	/**
	 * Retorna o valor de nomeClasse
	 * 
	 * @param objeto
	 *            Descrição do parâmetro
	 * @return O valor de nomeClasse
	 */
	protected String getNomeClasse(Object objeto) {
		String nomeClasse = null;

		String nomePacoteObjeto = objeto.getClass().getName();
		String nomeApenasPacote = (objeto.getClass().getPackage().toString())
				+ ".";

		int tamanhoNomePacoteObjeto = nomePacoteObjeto.length();
		int tamanhoNomePacote = nomeApenasPacote.length();

		nomeClasse = nomePacoteObjeto.substring((tamanhoNomePacote - 8),
				tamanhoNomePacoteObjeto);
		nomeClasse = (nomeClasse.substring(0, 1)).toLowerCase()
				+ (nomeClasse.substring(1, nomeClasse.length()));

		return nomeClasse;
	}

	/**
	 * Verifica se uma data informada pelo usuário é maior que a data atual do
	 * servidor
	 * 
	 * @param dataInformada
	 *            Data informada
	 * @return true - se a data for menor que a atual, false - se a data for
	 *         maior que a atual
	 */
	protected boolean verificarDataMenorQueDataCorrente(Date dataInformada) {
		return (new Date().after(dataInformada));
	}

	protected ActionForward montarPaginaConfirmacaoWizard(String chaveMensagem,
			HttpServletRequest request, ActionMapping actionMapping) {
		return montarPaginaConfirmacaoWizard(chaveMensagem, request,
				actionMapping, (String[]) null);
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param chaveMensagem
	 *            Descrição do parâmetro
	 * @param request
	 *            Descrição do parâmetro
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	protected ActionForward montarPaginaConfirmacaoWizard(String chaveMensagem,
			HttpServletRequest request, ActionMapping actionMapping,
			String... parametrosMensagem) {

		String destino = request.getParameter("destino");
		
	
		//Alterado por Sávio Luiz. Data:18/12/2007.
		//Alterado para mudar o destino passando por atributo por algum action.
		if(destino == null){
			if(request.getAttribute("destino") != null && !request.getAttribute("destino").equals("")){
			 destino = ""+request.getAttribute("destino");
			}
		}

		HttpSession sessao = getSessao(request);
		StatusWizard statusWizard = (StatusWizard) sessao
				.getAttribute("statusWizard");

		String caminhoActionInicial = null;

		if (destino == null || destino.trim().equalsIgnoreCase("")) {
			caminhoActionInicial = (statusWizard.getCaminhoActionConclusao());
		} else {
			caminhoActionInicial = (statusWizard.retornarItemWizard(Integer
					.parseInt(destino))).getCaminhoActionInicial();
		}
		request.setAttribute("confirmacao", "true");
		request.setAttribute("caminhoConfirmacao", caminhoActionInicial);
		request.setAttribute("chaveMensagem", chaveMensagem);
		request.setAttribute("parametrosMensagem", parametrosMensagem);

		return actionMapping.findForward("telaConfirmacao");
	}

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descrição sobre o fluxo secundário>
	 * 
	 * <Identificador e nome do fluxo secundário>
	 * 
	 * @author Administrador
	 * @date 25/03/2006
	 * 
	 * @param chaveMensagem
	 * @param request
	 * @param actionMapping
	 * @param parametrosMensagem
	 * @return
	 */
	protected ActionForward montarPaginaConfirmacao(String chaveMensagem,
			HttpServletRequest request, ActionMapping actionMapping,
			String... parametrosMensagem) {

		String caminhoActionConclusao = (String) request.getAttribute("caminhoActionConclusao");
		
		String tipoRelatorio = (String) request.getAttribute("tipoRelatorio");
		

		request.setAttribute("confirmacao", "true");
		request.setAttribute("confirmacaoNormal", "true");
		request.setAttribute("caminhoConfirmacao", caminhoActionConclusao);
		request.setAttribute("chaveMensagem", chaveMensagem);
		request.setAttribute("parametrosMensagem", parametrosMensagem);
		request.setAttribute("tipoRelatorio", tipoRelatorio);

		return actionMapping.findForward("telaConfirmacao");
	}

	/**
	 * Controla o mecanismo de paginação trabalhando com os parâmetros do
	 * paginador no jsp. A pesquisa é feita através deste método e só é
	 * retornado os dados da página sendo apresentada.
	 * 
	 * @author Rodrigo Silveira
	 * @date 30/03/2006
	 * 
	 * @param request
	 *            Request do Action
	 * @param actionForward
	 *            ActionForward original do Action
	 * @param filtro
	 *            Filtro da pesquisa
	 * @param nomePacoteObjeto
	 *            Nome do pacote e objeto pesquisado
	 * @return Um Map contendo dois atributos: a chave "colecaoRetorno" com a
	 *         coleção pesquisada e a chave "destinoActionForward" com o
	 *         actionForward que deve ser retornado
	 */
	protected Map controlarPaginacao(HttpServletRequest request,
			ActionForward actionForward, Filtro filtro, String nomePacoteObjeto) {

		String totalRegistros = ""
				+ (Integer) this.getSessao(request).getAttribute("totalRegistros");

		String pageOffsetRequest = request.getParameter("page.offset");

		if (pageOffsetRequest == null) {
			pageOffsetRequest = "1";
			totalRegistros = null;
		}

		Integer pageOffset = Integer.parseInt(pageOffsetRequest) - 1;

		Collection colecaoResultado = this.getFachada().pesquisar(filtro, pageOffset,
				nomePacoteObjeto);

		// O offset não existe na sessão
		if ((totalRegistros == null
				|| totalRegistros.trim().equalsIgnoreCase("")
				|| totalRegistros.trim().equalsIgnoreCase("0") || totalRegistros
				.trim().equalsIgnoreCase("null"))
				|| (pageOffsetRequest == null
						|| pageOffsetRequest.trim().equalsIgnoreCase("") || pageOffsetRequest
						.trim().equalsIgnoreCase("null"))) {

			int totalPesquisa = this.getFachada().totalRegistrosPesquisa(filtro,
					nomePacoteObjeto);
			totalRegistros = "" + totalPesquisa;
			
			this.getSessao(request).setAttribute("totalRegistros", totalPesquisa);

		}

		request.setAttribute("page.offset", pageOffset + 1);
		request.setAttribute("maximoPaginas", ((Double) Math.ceil(Double
				.parseDouble(totalRegistros) / 10)).intValue());

		actionForward = new ActionForward(actionForward.getName(),
				actionForward.getPath() + "?pager.offset="
						+ (((pageOffset + 1) * 10) - 10), false);

		HashMap retorno = new HashMap();
		retorno.put("colecaoRetorno", colecaoResultado);
		retorno.put("destinoActionForward", actionForward);

		return retorno;

	}

	/**
	 * Controla o mecanismo de paginação trabalhando com os parâmetros do
	 * paginador no jsp. Nesta versão, o usuário deve fazer a pesquisa com
	 * paginação na sua própria funcionalidade no action e deve informar o total
	 * de registros da pesquisa.
	 * 
	 * @author Rodrigo Silveira
	 * @date 05/05/2006
	 * 
	 * @param request
	 *            Request do Action
	 * @param actionForward
	 *            ActionForward original do Action
	 * @return O actionForward modificado com a configuração da paginação
	 */
	protected ActionForward controlarPaginacao(HttpServletRequest request,
			ActionForward actionForward, int totalRegistrosPesquisa) {

		HttpSession sessao = request.getSession(false);

		String totalRegistros = ""
				+ (Integer) sessao.getAttribute("totalRegistros");

		String pageOffsetRequest = request.getParameter("page.offset");

		if (pageOffsetRequest == null) {
			pageOffsetRequest = "1";
			totalRegistros = null;
		}

		
		// O offset não existe na sessão
		if ((totalRegistros == null
				|| totalRegistros.trim().equalsIgnoreCase("")
				|| totalRegistros.trim().equalsIgnoreCase("0") || totalRegistros
				.trim().equalsIgnoreCase("null"))
				|| (pageOffsetRequest == null
						|| pageOffsetRequest.trim().equalsIgnoreCase("") || pageOffsetRequest
						.trim().equalsIgnoreCase("null"))) {

			int totalPesquisa = totalRegistrosPesquisa;
			totalRegistros = "" + totalPesquisa;
			sessao.setAttribute("totalRegistros", totalPesquisa);

		}

		
		Integer pageOffset = Integer.parseInt(pageOffsetRequest) - 1;

		request.setAttribute("page.offset", pageOffset + 1);
		request.setAttribute("numeroPaginasPesquisa", pageOffset);
		request.setAttribute("maximoPaginas", ((Double) Math.ceil(Double
				.parseDouble(totalRegistros) / 10)).intValue());

		actionForward = new ActionForward(actionForward.getName(),
				actionForward.getPath() + "?pager.offset="
						+ (((pageOffset + 1) * 10) - 10), false);

		return actionForward;

	}

	/**
	 * Metodo que retorna a Fachada
	 * 
	 * @author Rafael Pinto
	 * @date 17/11/2006
	 * 
	 * @return Fachada
	 */
	protected Fachada getFachada() {
		
		if(fachada == null){
			fachada = Fachada.getInstancia(); 	
		}
		
		return fachada;
	}

	/**
	 * Metodo que retorna a Sessao
	 * 
	 * @author Rafael Pinto
	 * @date 17/11/2006
	 * 
	 * @return HttpSession
	 */
	protected HttpSession getSessao(HttpServletRequest request) {
		return request.getSession(false);
	}

	/**
	 * Metodo que retorna o Usuario que esta logado
	 * 
	 * @author Rafael Pinto
	 * @date 17/11/2006
	 * 
	 * @return Usuario
	 */
	protected Usuario getUsuarioLogado(HttpServletRequest request) {

		Usuario usuario = (Usuario) this.getSessao(request).getAttribute(
				"usuarioLogado");

		return usuario;
	}
	
	/**
	 * Metodo que retorna Sistema Parametro
	 * 
	 * @author Rafael Pinto
	 * @date 30/10/2008
	 * 
	 * @return Usuario
	 */
	protected SistemaParametro getSistemaParametro() {

		if(sistemaParametro == null){
			sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();	
		}
		
		return sistemaParametro;
	}
	
	
	/**
	 * Controla o mecanismo de paginação para uma tela que faça
	 * paginação e que a chama uma
	 * segunda tela q tambem faça paginação.
	 * 
	 * @author Kassia Albuquerque
	 * @date 27/11/2007
	 * 
	 * @param request
	 *            Request do Action
	 * @param actionForward
	 *            ActionForward original do Action
	 * @return O actionForward modificado com a configuração da paginação
	 */
	protected ActionForward controlarPaginacao(HttpServletRequest request,
			ActionForward actionForward, Integer totalRegistrosPesquisa,
			Boolean primeiraPaginacao) {

		if (primeiraPaginacao){
			
			String registrosPrimeiraPaginacao = "" + (Integer) this.getSessao(request).getAttribute("totalRegistrosPrimeiraPaginacao");
			String pageOffsetRequest = request.getParameter("page.offset");

			if (pageOffsetRequest == null) {
				pageOffsetRequest = "1";
				registrosPrimeiraPaginacao = null;
			}

			
			// O offset não existe na sessão
			if ((registrosPrimeiraPaginacao == null
					|| registrosPrimeiraPaginacao.trim().equalsIgnoreCase("")
					|| registrosPrimeiraPaginacao.trim().equalsIgnoreCase("0") || registrosPrimeiraPaginacao
					.trim().equalsIgnoreCase("null"))
					|| (pageOffsetRequest == null
							|| pageOffsetRequest.trim().equalsIgnoreCase("") || pageOffsetRequest
							.trim().equalsIgnoreCase("null"))) {

				Integer totalPesquisa = totalRegistrosPesquisa;
				registrosPrimeiraPaginacao = "" + totalPesquisa;
				this.getSessao(request).setAttribute("registrosPrimeiraPaginacao", totalPesquisa);

			}

			
			Integer pageOffset = Integer.parseInt(pageOffsetRequest) - 1;

			request.setAttribute("page.offset", pageOffset + 1);
			request.setAttribute("numeroPaginasPesquisaPrimeiraPaginacao", pageOffset);
			request.setAttribute("maximoPaginas", ((Double) Math.ceil(Double
					.parseDouble(registrosPrimeiraPaginacao) / 10)).intValue());
			actionForward = new ActionForward(actionForward.getName(),
					actionForward.getPath() + "?pager.offset="
							+ (((pageOffset + 1) * 10) - 10), false);
			
			
		}else{
			
			String registrosSegundaPaginacao = "" + (Integer) this.getSessao(request).getAttribute("totalRegistrosSegundaPaginacao");
			String pageOffsetRequest = request.getParameter("page.offset");

			if (pageOffsetRequest == null) {
				pageOffsetRequest = "1";
				registrosSegundaPaginacao = null;
			}

			
			// O offset não existe na sessão
			if ((registrosSegundaPaginacao == null
					|| registrosSegundaPaginacao.trim().equalsIgnoreCase("")
					|| registrosSegundaPaginacao.trim().equalsIgnoreCase("0") || registrosSegundaPaginacao
					.trim().equalsIgnoreCase("null"))
					|| (pageOffsetRequest == null
							|| pageOffsetRequest.trim().equalsIgnoreCase("") || pageOffsetRequest
							.trim().equalsIgnoreCase("null"))) {

				Integer totalPesquisa = totalRegistrosPesquisa;
				registrosSegundaPaginacao = "" + totalPesquisa;
				this.getSessao(request).setAttribute("registrosSegundaPaginacao", totalPesquisa);

			}

			
			Integer pageOffset = Integer.parseInt(pageOffsetRequest) - 1;

			request.setAttribute("page.offset", pageOffset + 1);
			request.setAttribute("numeroPaginasPesquisaSegundaPaginacao", pageOffset);
			request.setAttribute("maximoPaginas", ((Double) Math.ceil(Double
					.parseDouble(registrosSegundaPaginacao) / 10)).intValue());
			actionForward = new ActionForward(actionForward.getName(),
					actionForward.getPath() + "?pager.offset="
							+ (((pageOffset + 1) * 10) - 10), false);
		}
		

		return actionForward;

	}
	
	
}
