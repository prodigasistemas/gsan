package gcom.gui.relatorio.financeiro;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.relatorio.financeiro.RelatorioVolumesConsumidosNaoFaturados;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0822] Gerar Relatório do Valor Referente a Volumes Consumidos e Não Faturados
 * 
 * @see gcom.gui.relatorio.financeiro.ExibirGerarRelatorioVolumesConsumidosNaoFaturadosAction
 * @see gcom.gui.relatorio.financeiro.GerarRelatorioVolumesConsumidosNaoFaturadosActionForm
 * 
 * @author Victor Cisneiros
 * @date 09/07/2008
 */
public class GerarRelatorioVolumesConsumidosNaoFaturadosAction extends GcomAction {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		Fachada fachada = Fachada.getInstancia();
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogado");
		GerarRelatorioVolumesConsumidosNaoFaturadosActionForm form = (GerarRelatorioVolumesConsumidosNaoFaturadosActionForm) actionForm;
		
		// parametros
		Integer mesAno = null;
		String opcaoTotalizacao = null;
		Integer idEntidade = null;
		
		// ------------------------------
		// -- Mes/Ano
		// ------------------------------
		if (form.getMesAno() != null && !form.getMesAno().trim().equals("")) {
			mesAno = Integer.parseInt(form.getMesAno().substring(3, 7) + form.getMesAno().substring(0, 2));
		} else {
			throw new ActionServletException("atencao.required", null, "Mês/Ano do Faturamento");
		}
		
		// ------------------------------
		// -- Opcao de Totalizacao
		// ------------------------------
		if (form.getOpcaoTotalizacao() != null && !form.getOpcaoTotalizacao().trim().equals("")) {
			opcaoTotalizacao = form.getOpcaoTotalizacao();
		} else {
			throw new ActionServletException("atencao.required", null, "Opção de Totalização");
		}
		
		// ------------------------------
		// -- Gerencia Regional
		// ------------------------------
		if (form.getOpcaoTotalizacao().equals("gerenciaRegional")) {
			String id = form.getGerenciaRegionalId();
			if (id == null || id.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null, "Gerência Regional");
			}
			idEntidade = new Integer(id);
		}
		
		// ------------------------------
		// -- Gerencia Regional por Unidade de Negocio
		// ------------------------------
		if (form.getOpcaoTotalizacao().equals("gerenciaRegionalPorUnidadeNegocio")) {
			String id = form.getGerenciaRegionalPorUnidadeId();
			if (id == null || id.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null, "Gerência Regional por Unidade de Negócio");
			}
			idEntidade = new Integer(id);
		}
		
		// ------------------------------
		// -- Gerencia Regional por Localidade
		// ------------------------------
		if (form.getOpcaoTotalizacao().equals("gerenciaRegionalPorLocalidade")) {
			String id = form.getGerenciaRegionalPorLocalidadeId();
			if (id == null || id.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null, "Gerência Regional por Localidade");
			}
			idEntidade = new Integer(id);
		}
		
		// ------------------------------
		// -- Unidade de Negocio
		// ------------------------------
		if (form.getOpcaoTotalizacao().equals("unidadeNegocio")) {
			String id = form.getUnidadeNegocioId();
			if (id == null || id.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null, "Unidade Negócio");
			}
			idEntidade = new Integer(id);
		}
		
		// ------------------------------
		// -- Unidade de Negocio por Localidade
		// ------------------------------
		if (form.getOpcaoTotalizacao().equals("unidadeNegocioPorLocalidade")) {
			String id = form.getUnidadeNegocioPorLocalidadeId();
			if (id == null || id.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null, "Unidade Negócio por Localidade");
			}
			idEntidade = new Integer(id);
		}
		
		// ------------------------------
		// -- Localidade
		// ------------------------------
		if (form.getOpcaoTotalizacao().equals("localidade")) {
			String id = form.getCodigoLocalidade();
			if (id == null || id.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null, "Localidade");
			}
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, id));
			
			Collection pesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			if (pesquisa == null || pesquisa.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Localidade");
			}
			
			idEntidade = new Integer(id);
		}
		
		// ------------------------------
		// -- Municipio
		// ------------------------------
		if (form.getOpcaoTotalizacao().equals("municipio")) {
			String id = form.getCodigoMunicipio();
			if (id == null || id.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null, "Município");
			}
			
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, id));
			
			Collection pesquisa = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
			if (pesquisa == null || pesquisa.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Município");
			}
			
			idEntidade = new Integer(id);
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
		RelatorioVolumesConsumidosNaoFaturados relatorio = 
			new RelatorioVolumesConsumidosNaoFaturados(usuario);
		
		relatorio.addParametro("tipoRelatorio", tipoRelatorio);
		relatorio.addParametro("mesAno", mesAno);
		relatorio.addParametro("opcaoTotalizacao", opcaoTotalizacao);
		relatorio.addParametro("idEntidade", idEntidade);
		
		byte[] bytes = (byte[]) relatorio.executar();

		if (tipoRelatorio == TarefaRelatorio.TIPO_PDF) {
			response.addHeader("Content-Disposition", 
					"attachment; filename=relatorio.pdf");
			response.setContentType("application/pdf");
		} else if (tipoRelatorio == TarefaRelatorio.TIPO_RTF) {
			response.addHeader("Content-Disposition", 
					"attachment; filename=relatorio.rtf");
			response.setContentType("application/rtf");
		} else if (tipoRelatorio == TarefaRelatorio.TIPO_XLS) {
			response.addHeader("Content-Disposition", 
					"attachment; filename=relatorio.xls");
			response.setContentType("application/vnd.ms-excel");
		} else if (tipoRelatorio == TarefaRelatorio.TIPO_HTML) {
			response.addHeader("Content-Disposition", 
					"attachment; filename=relatorio.zip");
			response.setContentType("application/zip");
		}
		
		OutputStream out = response.getOutputStream();
		out.write(bytes);
		out.flush();
		out.close();
		
		return null;
	}

}
