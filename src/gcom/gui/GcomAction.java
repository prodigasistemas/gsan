/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
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
	 * Reporta o erro de volta � p�gina
	 * 
	 * @param request
	 *            O request usado atualmente
	 * @param chave
	 *            chave da descri��o do erro no arquivo de propriedades do
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
	 * Este m�todo retorna o id tempor�rio baseado no timestamp do objeto para
	 * exibi��o na mem�ria, enquanto o usu�rio estiver inserindo
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

			// Se o objeto j� estiver na base, o seu id � adicionado ao seu
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

				// Se o objeto j� estiver na base, o seu id � adicionado ao seu
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
	 * < <Descri��o do m�todo>>
	 * 
	 * @param request
	 *            Descri��o do par�metro
	 * @param chave
	 *            Descri��o do par�metro
	 * @param mensagem
	 *            Descri��o do par�metro
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
	 * < <Descri��o do m�todo>>
	 * 
	 * @param request
	 *            Descri��o do par�metro
	 * @param chaveMensagem
	 *            Descri��o do par�metro
	 * @param exception
	 *            Descri��o do par�metro
	 */
	protected void reportarErros(HttpServletRequest request,
			String chaveMensagem, Exception exception) {
		ActionErrors errors = new ActionErrors();

		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(chaveMensagem));

		// Recupera a sessao para colocar a exce��o que ser� mostrada na tela de
		// erro
		HttpSession sessao = request.getSession(false);

		// Se a sess�o n�o existir, ent�o o problema dever� ser reportado sem
		// interven��o do usu�rio
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
			// Mandar a exce��o na sess�o para que o usu�rio possa report�-la
			sessao.setAttribute("excecaoPaginaErro", exception);
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param request
	 *            Descri��o do par�metro
	 * @param chaveMensagem
	 *            Descri��o do par�metro
	 * @param mensagem
	 *            Descri��o do par�metro
	 * @param exception
	 *            Descri��o do par�metro
	 */
	protected void reportarErrosMensagem(HttpServletRequest request,
			String chaveMensagem, String mensagem, Exception exception) {
		ActionErrors errors = new ActionErrors();

		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(chaveMensagem,
				mensagem));

		// Recupera a sessao para colocar a exce��o que ser� mostrada na tela de
		// erro
		HttpSession sessao = request.getSession(false);

		// Se a sess�o n�o existir, ent�o o problema dever� ser reportado sem
		// interven��o do usu�rio
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
			// Mandar a exce��o na sess�o para que o usu�rio possa report�-la
			sessao.setAttribute("excecaoPaginaErro", exception);
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
	}

	/**
	 * Faz a verifica��o se o usu�rio est� logado no sistema
	 * 
	 * @param sessao
	 *            Descri��o do par�metro
	 * @param parametroSessao
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	protected boolean verificarUsuarioLogado(HttpSession sessao,
			String parametroSessao) {

		return (sessao != null && sessao.getAttribute(parametroSessao) != null);
	}

	/**
	 * M�todo que converte uma string para um inteiro, caso exista erro de
	 * convers�o, a constante de n�mero n�o informado � retornada
	 * 
	 * @param target
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	protected int converterStringToInt(String target) {
		try {
			return Integer.parseInt(target);
		} catch (NumberFormatException e) {
			return ConstantesSistema.NUMERO_NAO_INFORMADO;
		}
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param request
	 *            Descri��o do par�metro
	 * @param mensagemPaginaSucesso
	 *            Descri��o do par�metro
	 * @param labelPaginaSucesso
	 *            Descri��o do par�metro
	 * @param caminhoFuncionalidade
	 *            Descri��o do par�metro
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
	 * < <Descri��o do m�todo>>
	 * 
	 * @param request
	 *            Descri��o do par�metro
	 * @param labelPaginaConfirmacao
	 *            Descri��o do par�metro
	 * @param mensagemSuperiorConfirmacao
	 *            Descri��o do par�metro
	 * @param mensagemInferiorConfirmacao
	 *            Descri��o do par�metro
	 * @param caminhoFuncionalidade
	 *            Descri��o do par�metro
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
	 *            Descri��o do par�metro
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
	 * Verifica se uma data informada pelo usu�rio � maior que a data atual do
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
	 * < <Descri��o do m�todo>>
	 * 
	 * @param chaveMensagem
	 *            Descri��o do par�metro
	 * @param request
	 *            Descri��o do par�metro
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	protected ActionForward montarPaginaConfirmacaoWizard(String chaveMensagem,
			HttpServletRequest request, ActionMapping actionMapping,
			String... parametrosMensagem) {

		String destino = request.getParameter("destino");
		
	
		//Alterado por S�vio Luiz. Data:18/12/2007.
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
	 * <Breve descri��o sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descri��o sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descri��o sobre o fluxo secund�rio>
	 * 
	 * <Identificador e nome do fluxo secund�rio>
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
	 * Controla o mecanismo de pagina��o trabalhando com os par�metros do
	 * paginador no jsp. A pesquisa � feita atrav�s deste m�todo e s� �
	 * retornado os dados da p�gina sendo apresentada.
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
	 *         cole��o pesquisada e a chave "destinoActionForward" com o
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

		// O offset n�o existe na sess�o
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
	 * Controla o mecanismo de pagina��o trabalhando com os par�metros do
	 * paginador no jsp. Nesta vers�o, o usu�rio deve fazer a pesquisa com
	 * pagina��o na sua pr�pria funcionalidade no action e deve informar o total
	 * de registros da pesquisa.
	 * 
	 * @author Rodrigo Silveira
	 * @date 05/05/2006
	 * 
	 * @param request
	 *            Request do Action
	 * @param actionForward
	 *            ActionForward original do Action
	 * @return O actionForward modificado com a configura��o da pagina��o
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

		
		// O offset n�o existe na sess�o
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
	 * Controla o mecanismo de pagina��o para uma tela que fa�a
	 * pagina��o e que a chama uma
	 * segunda tela q tambem fa�a pagina��o.
	 * 
	 * @author Kassia Albuquerque
	 * @date 27/11/2007
	 * 
	 * @param request
	 *            Request do Action
	 * @param actionForward
	 *            ActionForward original do Action
	 * @return O actionForward modificado com a configura��o da pagina��o
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

			
			// O offset n�o existe na sess�o
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

			
			// O offset n�o existe na sess�o
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
	
	protected boolean verificaReferenciaIgualReferencialFaturamento(Integer referencia) {
		if(referencia.equals(getSistemaParametro().getAnoMesFaturamento()))
			return true;
		return false;
	}
	
	
}
