package gcom.gui.batch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.batch.FiltroProcesso;
import gcom.batch.Processo;
import gcom.batch.ProcessoIniciado;
import gcom.batch.ProcessoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

/**
 * Action que insere um ProcessoIniciado no sistema
 * 
 * @author Rodrigo Silveira
 * @created 24/07/2006
 */
public class InserirProcessoMensalEventualAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirProcessoMensalEventualActionForm inserirProcessoMensalEventualActionForm = (InserirProcessoMensalEventualActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		int idProcesso = Integer.parseInt(inserirProcessoMensalEventualActionForm.getIdProcesso());
		
		FiltroProcesso filtro = new FiltroProcesso();
		filtro.adicionarParametro(new ParametroSimples(FiltroProcesso.ID, Integer.valueOf(idProcesso)));
		
		Collection lista = fachada.pesquisar(filtro, Processo.class.getName());
		
		Processo processo = (Processo) lista.iterator().next();
		
		if (processo.isExcluido()){
			throw new ActionServletException("atencao.processo_excluido");
		}
		
		String dataAgendamento = inserirProcessoMensalEventualActionForm.getDataAgendamento();
		String horaAgendamento = inserirProcessoMensalEventualActionForm.getHoraAgendamento();
		String idProcessoIniciadoPrecedenteRequest = inserirProcessoMensalEventualActionForm.getIdProcessoIniciadoPrecedente();
		Integer idProcessoIniciadoPrecedente = null;
		
		if (idProcessoIniciadoPrecedenteRequest != null && !idProcessoIniciadoPrecedenteRequest.trim().equals("")) {
			idProcessoIniciadoPrecedente = Integer.parseInt(idProcessoIniciadoPrecedenteRequest);
		}

		ProcessoIniciado processoIniciado = new ProcessoIniciado();

		SimpleDateFormat formatoDataHora = new SimpleDateFormat("dd/MM/yyyy k:mm:ss");

		try {
			if (dataAgendamento != null && !dataAgendamento.equals("") && horaAgendamento != null && !horaAgendamento.equals("")) {
				processoIniciado.setDataHoraAgendamento(formatoDataHora.parse(dataAgendamento + " " + horaAgendamento));
			}
		} catch (ParseException e) {
			throw new ActionServletException("erro.sistema");
		}

		ProcessoSituacao processoSituacao = new ProcessoSituacao();

		processoIniciado.setProcesso(processo);
		processoIniciado.setProcessoSituacao(processoSituacao);

		if (idProcessoIniciadoPrecedente != null) {
			ProcessoIniciado processoIniciadoPrecedente = new ProcessoIniciado();
			processoIniciadoPrecedente.setId(idProcessoIniciadoPrecedente);

			processoIniciado.setProcessoIniciadoPrecedente(processoIniciadoPrecedente);
		}

		//Falta setar o usuário real
		processoIniciado.setUsuario(this.getUsuarioLogado(httpServletRequest));

		Integer codigoProcessoIniciadoGerado = (Integer) fachada.inserirProcessoIniciado(processoIniciado);

		montarPaginaSucesso(httpServletRequest, "Processo Iniciado de código "
				+ codigoProcessoIniciadoGerado + " inserido com sucesso.",
				"Inserir outro Processo", "exibirInserirProcessoAction.do");

		return retorno;
	}
}
