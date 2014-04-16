package gcom.gui.cadastro;

import gcom.cadastro.AtributosBoletimChaveHelper;
import gcom.cadastro.AtributosBoletimHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.RelatorioBoletimCustoAtualizacaoCadastral;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para geração do boletim de 
 * custo atualizacao cadastral
 * 
 * @author Anderson Italo
 * @date 25/06/2009
 */

public class GerarBoletimCustoAtualizacaoCadastralAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

//		 Seta o caminho de retorno
		ActionForward retorno = null;
		Fachada fachada = Fachada.getInstancia();
		InformarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm informarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm = (InformarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm) actionForm;
		
		//pesquisa a Empresa
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, 
				new Integer(informarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm.getEmpresa())));
		Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		
		Empresa empresa = (Empresa)Util.retonarObjetoDeColecao(colecaoEmpresa);
		Date dataInicial = Util.converteStringParaDate(informarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm.getDataInicial());
		Date dataFinal = Util.converteStringParaDate(informarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm.getDataFinal());

		Object[] dadosBoletim = fachada.
		gerarBoletimCustoAtualizacaoCadastral(empresa, dataInicial, dataFinal);
		
		TreeMap<AtributosBoletimChaveHelper, AtributosBoletimHelper> mapAtributosBoletim = 
			(TreeMap<AtributosBoletimChaveHelper, AtributosBoletimHelper>)dadosBoletim[0];
		
		Integer numImoveisAtualizados = (Integer)dadosBoletim[1];
		
		//Parte que vai mandar o relatório para a tela
		// cria uma instância da classe do relatório
		RelatorioBoletimCustoAtualizacaoCadastral relatorioBoletimCustoAtualizacaoCadastral = new RelatorioBoletimCustoAtualizacaoCadastral((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorioBoletimCustoAtualizacaoCadastral.addParametro("mapAtributosBoletim", mapAtributosBoletim);
		relatorioBoletimCustoAtualizacaoCadastral.addParametro("empresa", empresa);
		relatorioBoletimCustoAtualizacaoCadastral.addParametro("dataInicial", informarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm.getDataInicial());
		relatorioBoletimCustoAtualizacaoCadastral.addParametro("dataFinal", informarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm.getDataFinal());
		relatorioBoletimCustoAtualizacaoCadastral.addParametro("numImoveisAtualizados", numImoveisAtualizados);
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		relatorioBoletimCustoAtualizacaoCadastral.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));

		try {
			retorno = processarExibicaoRelatorio(relatorioBoletimCustoAtualizacaoCadastral,
				tipoRelatorio, httpServletRequest, httpServletResponse,	actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
		
		return retorno;
	}

}
