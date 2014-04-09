package gcom.gui.micromedicao.leitura;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeLeitura;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirPesquisarAnormalidadeLeituraAction  extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirPesquisarAnormalidadeLeitura");

		PesquisarLeituraAnormalidadeActionForm pesquisarLeituraAnormalidadeActionForm = (PesquisarLeituraAnormalidadeActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		// limpa os parametros do form
		pesquisarLeituraAnormalidadeActionForm.setDescricao("");
		pesquisarLeituraAnormalidadeActionForm.setAnormalidadeRelativaHidrometro(ConstantesSistema.TODOS+"");
		pesquisarLeituraAnormalidadeActionForm.setAnormalidadeSemHidrometro(ConstantesSistema.TODOS+"");
		pesquisarLeituraAnormalidadeActionForm.setAnormalidadeRestritoSistema(ConstantesSistema.TODOS+"");
		pesquisarLeituraAnormalidadeActionForm.setAnormalidadePerdaTarifaSocial(ConstantesSistema.TODOS+"");
		pesquisarLeituraAnormalidadeActionForm.setAnormalidadeOrdemServicoAutomatica(ConstantesSistema.TODOS+"");

		// Parte que pega as coleções da sessão

		// Consumo a ser cobrado Leitura Anormalidade
		FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumo = new FiltroLeituraAnormalidadeConsumo();
		filtroLeituraAnormalidadeConsumo.adicionarParametro(new ParametroSimples(
				FiltroLeituraAnormalidadeConsumo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection leiturasAnormalidadesConsumo = fachada.pesquisar(filtroLeituraAnormalidadeConsumo,
				LeituraAnormalidadeConsumo.class.getName());
		if (leiturasAnormalidadesConsumo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Consumo a ser cobrado");
		} else {
			sessao.setAttribute("leiturasAnormalidadesConsumo", leiturasAnormalidadesConsumo);
		}

		// Leitura para Faturamento
		FiltroLeituraAnormalidadeLeitura filtroLeituraAnormalidadeLeitura = new FiltroLeituraAnormalidadeLeitura();
		filtroLeituraAnormalidadeLeitura.adicionarParametro(new ParametroSimples(
				FiltroLeituraAnormalidadeLeitura.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection leiturasAnormalidadesLeitura = fachada.pesquisar(filtroLeituraAnormalidadeLeitura,
				LeituraAnormalidadeLeitura.class.getName());
		if (leiturasAnormalidadesConsumo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Leitura para Faturamento");
		} else {
			sessao.setAttribute("leiturasAnormalidadesLeitura", leiturasAnormalidadesLeitura);
		}
		
		
//		 envia uma flag que será verificado no cliente_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaAnormalidadeLeitura") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaPesquisaAnormalidadeLeitura",
							httpServletRequest
									.getParameter("caminhoRetornoTelaPesquisaAnormalidadeLeitura"));
		}
/*
		// Ramo de Atividade
		FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade();
		filtroRamoAtividade.adicionarParametro(new ParametroSimples(
				FiltroRamoAtividade.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection ramosAtividades = fachada.pesquisar(filtroRamoAtividade,
				RamoAtividade.class.getName());
		if (ramosAtividades.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"ramo de atividade");
		} else {
			sessao.setAttribute("ramosAtividades", ramosAtividades);
		}
*/
		return retorno;
	}
}
