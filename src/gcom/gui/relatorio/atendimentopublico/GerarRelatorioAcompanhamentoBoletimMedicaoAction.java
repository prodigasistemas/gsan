package gcom.gui.relatorio.atendimentopublico;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.micromedicao.FiltroContratoEmpresaServico;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.RelatorioAcompanhamentoBoletimMedicao;
import gcom.relatorio.atendimentopublico.RelatorioAcompanhamentoBoletimMedicaoHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**[UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
 * 
 * Action responsável por gerar o Relatório de Acompanhamento
 * dos Boletins de Medição. Action será chamada através
 * da tela relatorio_acompanhamento_boletim_medicao_gerar.jsp
 * 
 * @author Diogo Peixoto
 * @since 17/06/2011
 */
public class GerarRelatorioAcompanhamentoBoletimMedicaoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		GerarRelatorioAcompanhamentoBoletimMedicaoActionForm form = (GerarRelatorioAcompanhamentoBoletimMedicaoActionForm) actionForm;
		
		String nomeRelatorio = ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_BOLETIM_MEDICAO;
		RelatorioAcompanhamentoBoletimMedicao relatorio = new RelatorioAcompanhamentoBoletimMedicao(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"), nomeRelatorio);

		FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper helper = new FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper();
		
		String idEmpresa = form.getIdEmpresa();
		if(Util.verificarNaoVazio(idEmpresa)){
			helper.setIdEmpresa(Integer.valueOf(idEmpresa));
		}
		
		String idContrato = form.getIdContrato();
		if(Util.verificarNaoVazio(idContrato)){
			helper.setIdContratoEmpresaServico(Integer.valueOf(idContrato));
		}
		
		String mesAno = form.getDataReferencia();
		if(Util.verificarNaoVazio(mesAno)){
			try{
				helper.setMesAnoReferencia(form.getDataReferencia());
			}catch (NumberFormatException e) {
				throw new ActionServletException("atencao.mes_ano_referencia_invalido");
			}	
		}

		RelatorioAcompanhamentoBoletimMedicaoHelper helperRelatorio = Fachada.getInstancia().filtrarRelatorioAcompanhamentoBoletimMedicao(helper);
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, form.getIdEmpresa()));
		Collection<Empresa> empresas = Fachada.getInstancia().pesquisar(filtroEmpresa, Empresa.class.getName());
		Empresa empresa = (Empresa)Util.retonarObjetoDeColecao(empresas);
		
		FiltroContratoEmpresaServico filtroContratoEmpresaServico = new FiltroContratoEmpresaServico();
		filtroContratoEmpresaServico.adicionarParametro(new ParametroSimples(FiltroContratoEmpresaServico.ID, form.getIdContrato()));
		Collection<ContratoEmpresaServico> contratosEmpresaServico = Fachada.getInstancia().pesquisar(
				filtroContratoEmpresaServico, ContratoEmpresaServico.class.getName());
		ContratoEmpresaServico contratoEmpresaServico = (ContratoEmpresaServico)Util.retonarObjetoDeColecao(contratosEmpresaServico);
		
		httpServletRequest.setAttribute( "telaSucessoRelatorio", "sim" );
		relatorio.addParametro("tipoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorio.addParametro("nomeEmpresa", empresa.getDescricao());
		relatorio.addParametro("numeroContrato", contratoEmpresaServico.getDescricaoContrato());
		relatorio.addParametro("dataReferencia", form.getDataReferencia());

		relatorio.addParametro("dadosRelatorio", helperRelatorio);
		
		try {
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");
			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
		return retorno;
	}
}
