package gcom.gui.portal;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1194] Consultar Estrutura Tarifária Loja Virtual
 * 
 * Classe responsável por gerar o relatório 
 * relatorioEstruturaTarifariaLojaVirtual.jasper
 * 
 * @author Diogo Peixoto
 * @since 15/07/2011
 *
 */

public class GerarRelatorioEstruturaTarifariaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward retorno = null;
		HttpSession sessao = request.getSession(false);
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, "INTERNET"));
		Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName()));
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		RelatorioEstruturaTarifaria relatorio = new RelatorioEstruturaTarifaria(usuario);
		String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		relatorio.addParametro("estruturaTarifariaBeans", sessao.getAttribute("estruturaTarifariaBeans"));
		relatorio.addParametro("sistemaParametro", sistemaParametro);
		relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

		try {
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, request, response, mapping);
		} catch (RelatorioVazioException ex) {
			reportarErros(request, "atencao.relatorio.vazio");
			retorno = mapping.findForward("telaAtencaoPopup");
		}
		return retorno;
	}
}