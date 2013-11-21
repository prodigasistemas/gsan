package gcom.gui.relatorio.atendimentopublico;

import gcom.gui.ActionServletException;
import gcom.gui.atendimentopublico.ordemservico.GerarRelatorioGestaoServicosUPAActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioGestaoServicosUPA;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0776] Filtrar Relatório de Gestão de Serviços UPA
 * 
 * @see gcom.gui.atendimentopublico.ordemservico.ExibirGerarRelatorioGestaoServicosUPAAction
 * @see gcom.gui.atendimentopublico.ordemservico.GerarRelatorioGestaoServicosUPAActionForm
 * @see gcom.relatorio.atendimentopublico.RelatorioGestaoServicosUPA
 * 
 * @author Victor Cisneiros
 * @date 19/05/2008
 */
public class GerarRelatorioGestaoServicosUPAAction 
		extends ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogado");
		GerarRelatorioGestaoServicosUPAActionForm form = (GerarRelatorioGestaoServicosUPAActionForm) actionForm;
		
		// ------------------------------
		// -- Parametros
		// ------------------------------
		Short situacao = null;
		Collection<Integer> collectionServicoTipo = null;
		Date periodoGeracaoInicial = null;
		Date periodoGeracaoFinal = null;
		Integer idUnidadeOrganizacional = null;
		Integer idUnidadeSuperior = null;
		Integer idEmpresa = null;
		
		// ------------------------------
		// -- Situacao
		// ------------------------------
		if (form.getSituacaoOrdemServico() != null && !form.getSituacaoOrdemServico().trim().equals("")) {
			situacao = new Short(form.getSituacaoOrdemServico());
		}
		
		// ------------------------------
		// -- Tipos de Serviços
		// ------------------------------
		// Se o usuário selecionou Tipos de Serviços, popular a Collection
		if (form.getTipoServicoSelecionados() != null && !form.getTipoServicoSelecionados()[0].equals("-1")) {
			collectionServicoTipo = new ArrayList<Integer>();
			for (String idServico : form.getTipoServicoSelecionados()) {
				collectionServicoTipo.add(new Integer(idServico));
			}
		}
		
		// ------------------------------
		// -- Período de Geração Inicial
		// ------------------------------
		if (form.getPeriodoGeracaoInicial() != null && !form.getPeriodoGeracaoInicial().trim().equals("")) {
			periodoGeracaoInicial = Util.converteStringParaDate(form.getPeriodoGeracaoInicial());
		} else {
			throw new ActionServletException("atencao.required", null, "Período de Geração Inicial");
		}
		
		// ------------------------------
		// -- Período de Geração Final
		// ------------------------------
		if (form.getPeriodoGeracaoFinal() != null && !form.getPeriodoGeracaoFinal().trim().equals("")) {
			periodoGeracaoFinal = Util.converteStringParaDate(form.getPeriodoGeracaoFinal());
		} else {
			periodoGeracaoFinal = periodoGeracaoInicial;
		}
		
		if ((periodoGeracaoFinal.getTime() - periodoGeracaoInicial.getTime()) > 1000L * 60L * 60L * 24L * 31L) {
			throw new ActionServletException("atencao.filtrar_intervalo_limite", null, "Data de Atendimento");
		}
		
		// ------------------------------
		// -- UnidadeOrganizacional ou UnidadeSuperior ou Empresa
		// ------------------------------
		if (form.getIdUnidadeOrganizacional() != null && !form.getIdUnidadeOrganizacional().trim().equals("")) {
			idUnidadeOrganizacional = new Integer(form.getIdUnidadeOrganizacional());
		}
		else if (form.getIdUnidadeSuperior() != null && !form.getIdUnidadeSuperior().trim().equals("")) {
			idUnidadeSuperior = new Integer(form.getIdUnidadeSuperior());
		}
		else if (form.getIdEmpresa() != null && !form.getIdEmpresa().trim().equals("")) {
			idEmpresa = new Integer(form.getIdEmpresa());
		}
		else {
			throw new ActionServletException("atencao.required", null, "Unidade Organizacional OU Unidade Superior OU Empresa");
		}
		
		// ------------------------------
		// -- Tipo do Relatorio
		// ------------------------------
		int tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		try {
			tipoRelatorio = Integer.parseInt(request.getParameter("tipoRelatorio")); 
		} catch (NumberFormatException e) { }
		
		// ------------------------------
		// -- Geracao do Relatorio
		// ------------------------------
		RelatorioGestaoServicosUPA relatorio = new RelatorioGestaoServicosUPA(usuario);
		relatorio.addParametro("situacao", situacao);
		relatorio.addParametro("periodoGeracaoInicial", periodoGeracaoInicial);
		relatorio.addParametro("periodoGeracaoFinal", periodoGeracaoFinal);
		relatorio.addParametro("collectionServicoTipo", collectionServicoTipo);
		relatorio.addParametro("idUnidadeOrganizacional", idUnidadeOrganizacional);
		relatorio.addParametro("idUnidadeSuperior", idUnidadeSuperior);
		relatorio.addParametro("idEmpresa", idEmpresa);
		relatorio.addParametro("tipoRelatorio", tipoRelatorio);
		
		return processarExibicaoRelatorio(
				relatorio, tipoRelatorio, request, response, mapping);
		
//		byte[] bytes = (byte[]) relatorio.executar();
//
//		if (tipoRelatorio == TarefaRelatorio.TIPO_PDF) {
//			response.addHeader("Content-Disposition", 
//					"attachment; filename=relatorio.pdf");
//			response.setContentType("application/pdf");
//		} else if (tipoRelatorio == TarefaRelatorio.TIPO_RTF) {
//			response.addHeader("Content-Disposition", 
//					"attachment; filename=relatorio.rtf");
//			response.setContentType("application/rtf");
//		} else if (tipoRelatorio == TarefaRelatorio.TIPO_XLS) {
//			response.addHeader("Content-Disposition", 
//					"attachment; filename=relatorio.xls");
//			response.setContentType("application/vnd.ms-excel");
//		} else if (tipoRelatorio == TarefaRelatorio.TIPO_HTML) {
//			response.addHeader("Content-Disposition", 
//					"attachment; filename=relatorio.zip");
//			response.setContentType("application/zip");
//		}
//		
//		OutputStream out = response.getOutputStream();
//		out.write(bytes);
//		out.flush();
//		out.close();
//		
//		return null;
	}

}
