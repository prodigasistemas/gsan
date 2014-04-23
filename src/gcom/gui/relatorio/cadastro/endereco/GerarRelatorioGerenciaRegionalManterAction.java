package gcom.gui.relatorio.cadastro.endereco;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.gui.cadastro.localidade.FiltrarGerenciaRegionalActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.endereco.RelatorioManterGerenciaRegional;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @version 1.0
 */

public class GerarRelatorioGerenciaRegionalManterAction extends
ExibidorProcessamentoTarefaRelatorio {

/**
* < <Descrição do método>>
* 
* @param actionMapping
*            Descrição do parâmetro
* @param actionForm
*            Descrição do parâmetro
* @param httpServletRequest
*            Descrição do parâmetro
* @param httpServletResponse
*            Descrição do parâmetro
* @return Descrição do retorno
*/

public ActionForward execute(ActionMapping actionMapping,
	ActionForm actionForm, HttpServletRequest httpServletRequest,
	HttpServletResponse httpServletResponse) {

// cria a variável de retorno
ActionForward retorno = null;

// Mudar isso quando tiver esquema de segurança
HttpSession sessao = httpServletRequest.getSession(false);

FiltrarGerenciaRegionalActionForm form = (FiltrarGerenciaRegionalActionForm) actionForm;

FiltroGerenciaRegional filtroGerenciaRegional = (FiltroGerenciaRegional) sessao
		.getAttribute("filtroGerenciaRegional");

// Inicio da parte que vai mandar os parametros para o relatório

GerenciaRegional gerenciaRegionalParametros = new GerenciaRegional();

Short indicadordeUso = null;

if(form.getIndicadorUso()!= null && !form.getIndicadorUso().equals("")){
	
	indicadordeUso = new Short ("" + form.getIndicadorUso());
	gerenciaRegionalParametros.setIndicadorUso(indicadordeUso);
}


// seta os parametros que serão mostrados no relatório

if(form.getGerenciaRegionalNome() != null && !form.getGerenciaRegionalNome().equals("")){
	
	gerenciaRegionalParametros.setNome(form.getGerenciaRegionalNome());
	
}

if(form.getGerenciaRegionalNomeAbre() != null && !form.getGerenciaRegionalNomeAbre().equals("")){
	
	gerenciaRegionalParametros.setNomeAbreviado(form.getGerenciaRegionalNomeAbre());
}


Integer gerenciaRegionalID = null;

if(form.getGerenciaRegionalID()!= null && !form.getGerenciaRegionalID().equals("")){
	
	 gerenciaRegionalID = new Integer ("" + form.getGerenciaRegionalID());
	
	gerenciaRegionalParametros.setId( gerenciaRegionalID);
}

if(form.getCnpjGerenciaRegional() != null && !form.getCnpjGerenciaRegional().equals("")){
	gerenciaRegionalParametros.setCnpjGerenciaRegional(form.getCnpjGerenciaRegional());
}


// Fim da parte que vai mandar os parametros para o relatório

// cria uma instância da classe do relatório
RelatorioManterGerenciaRegional relatorioManterGerenciaRegional = new RelatorioManterGerenciaRegional(
		(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
relatorioManterGerenciaRegional.addParametro("gerenciaRegionalParametros",
		gerenciaRegionalParametros);
relatorioManterGerenciaRegional.addParametro("filtroGerenciaRegional",
		filtroGerenciaRegional);

// chama o metódo de gerar relatório passando o código da analise
// como parâmetro
String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
if (tipoRelatorio == null) {
	tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
}

relatorioManterGerenciaRegional.addParametro("tipoFormatoRelatorio", Integer
		.parseInt(tipoRelatorio));
try {
	retorno = processarExibicaoRelatorio(relatorioManterGerenciaRegional,
			tipoRelatorio, httpServletRequest, httpServletResponse,
			actionMapping);

} catch (SistemaException ex) {
	// manda o erro para a página no request atual
	reportarErros(httpServletRequest, "erro.sistema");

	// seta o mapeamento de retorno para a tela de erro de popup
	retorno = actionMapping.findForward("telaErroPopup");

} catch (RelatorioVazioException ex1) {
	// manda o erro para a página no request atual
	reportarErros(httpServletRequest, "erro.relatorio.vazio");

	// seta o mapeamento de retorno para a tela de atenção de popup
	retorno = actionMapping.findForward("telaAtencaoPopup");
}

// devolve o mapeamento contido na variável retorno
return retorno;
}

}
