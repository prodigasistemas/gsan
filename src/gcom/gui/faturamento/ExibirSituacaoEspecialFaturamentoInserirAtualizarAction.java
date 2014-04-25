package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoMotivo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirSituacaoEspecialFaturamentoInserirAtualizarAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("exibirSituacaoEspecialFaturamentoInserirAtualizar");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		SituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm = (SituacaoEspecialFaturamentoActionForm) actionForm;

		FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();
		Collection collectionFaturamentoSituacaoTipo = fachada.pesquisar(
				filtroFaturamentoSituacaoTipo, FaturamentoSituacaoTipo.class
						.getName());
		httpServletRequest.setAttribute("collectionFaturamentoSituacaoTipo",
				collectionFaturamentoSituacaoTipo);
		
		FiltroFaturamentoSituacaoMotivo filtroFaturamentoSituacaoMotivo = new FiltroFaturamentoSituacaoMotivo();
		Collection collectionFaturamentoSituacaoMotivo = fachada.pesquisar(
				filtroFaturamentoSituacaoMotivo,
				FaturamentoSituacaoMotivo.class.getName());
		httpServletRequest.setAttribute("collectionFaturamentoSituacaoMotivo",
				collectionFaturamentoSituacaoMotivo);
		
		String COM = situacaoEspecialFaturamentoActionForm
				.getQuantidadeImoveisCOMSituacaoEspecialFaturamento();
		String SEM = situacaoEspecialFaturamentoActionForm
				.getQuantidadeImoveisSEMSituacaoEspecialFaturamento();
		String quantidadeDeImoveisAtualizados = Integer.toString(Integer
				.parseInt(COM)
				+ Integer.parseInt(SEM));

		if (quantidadeDeImoveisAtualizados.equals("0"))
			throw new ActionServletException(
					"atencao.imovel.sem.situacao.especial.faturamento", null,
					"");

		situacaoEspecialFaturamentoActionForm
				.setQuantidadeDeImoveis(quantidadeDeImoveisAtualizados);

		return retorno;
	}

	
}
