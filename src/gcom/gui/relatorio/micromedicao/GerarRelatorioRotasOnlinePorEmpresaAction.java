package gcom.gui.relatorio.micromedicao;

import gcom.gui.ActionServletException;
import gcom.micromedicao.bean.PesquisarRelatorioRotasOnlinePorEmpresaHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.micromedicao.RelatorioRotasOnlinePorEmpresa;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0???] Gerar Relatorio Rotas Online por Empresa
 * 
 * @see gcom.gui.relatorio.micromedicao.ExibirGerarRelatorioRotasOnlinePorEmpresaAction
 * @see gcom.gui.relatorio.micromedicao.GerarRelatorioRotasOnlinePorEmpresaAction
 * @see gcom.relatorio.micromedicao.RelatorioRotasOnlinePorEmpresa
 * @see gcom.micromedicao.RepositorioMicromedicaoHBM#pesquisarRelatorioRotasOnlinePorEmpresa(PesquisarRelatorioRotasOnlinePorEmpresaHelper)
 * 
 * @author Victor Cisneiros
 * @date 28/10/2008
 */
public class GerarRelatorioRotasOnlinePorEmpresaAction extends ExibidorProcessamentoTarefaRelatorio {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogado");
		GerarRelatorioRotasOnlinePorEmpresaActionForm form = (GerarRelatorioRotasOnlinePorEmpresaActionForm) actionForm;
		
		String mesAno = null;
		if (form.getMesAnoReferencia() != null && !form.getMesAnoReferencia().trim().equals("")) {
			mesAno = form.getMesAnoReferencia();
		} else {
			throw new ActionServletException("atencao.required", null, "Mês/Ano da Arrecadação");
		}
		
		Integer idFaturamentoGrupo = null;
		if (form.getIdFaturamentoGrupo() != null && !form.getIdFaturamentoGrupo().equals("-1")) {
			idFaturamentoGrupo = Integer.parseInt(form.getIdFaturamentoGrupo());
		}
		
		Integer idEmpresa = null;
		if (form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1")) {
			idEmpresa = Integer.parseInt(form.getIdEmpresa());
		}
		
		Integer idLeiturista = null;
		if (form.getIdLeiturista() != null && !form.getIdLeiturista().equals("-1")) {
			idLeiturista = Integer.parseInt(form.getIdLeiturista());
		}
		
		Integer tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		try {
			tipoRelatorio = Integer.parseInt(request.getParameter("tipoRelatorio")); 
		} catch (NumberFormatException e) { }
		
		Integer anoMesReferencia = new Integer(mesAno.substring(3, 7) + mesAno.substring(0, 2));
		
		RelatorioRotasOnlinePorEmpresa relatorio = new RelatorioRotasOnlinePorEmpresa(usuario);
		relatorio.addParametro("anoMesReferencia", anoMesReferencia);
		relatorio.addParametro("idFaturamentoGrupo", idFaturamentoGrupo);
		relatorio.addParametro("idEmpresa", idEmpresa);
		relatorio.addParametro("idLeiturista", idLeiturista);
		relatorio.addParametro("tipoRelatorio", tipoRelatorio);
		
		ActionForward retorno = processarExibicaoRelatorio(
				relatorio, tipoRelatorio, request, response, mapping);
		
		return retorno;
	}

}
