package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.FiltroArrecadadorContrato;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirProcessoUmInserirAvisoBancarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirInserirAvisoBancarioProcessoUm");

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		InserirAvisoBancarioActionForm form = (InserirAvisoBancarioActionForm) actionForm;

		// Pesquisa de Arrecadador
		String idArrecadador = form.getCodigoArrecadador();
		String dataLancamento = form.getDataLancamento();
		Arrecadador arrecadador = null;
		Collection<Arrecadador> collectionArrecadador = null;
		
		Arrecadador arrecadadorVolta = (Arrecadador)sessao.getAttribute("arrecadador");
		String data = (String)sessao.getAttribute("data");
		if((sessao.getAttribute("arrecadador") != null 
				&&  !arrecadadorVolta.getId().toString().equalsIgnoreCase(idArrecadador)) 
				|| (sessao.getAttribute("data") != null  && !data.equalsIgnoreCase("")
						&&  !data.equalsIgnoreCase(dataLancamento)))
		{
			form.setTipoAviso("");
			form.setNumeroDocumento("");
			form.setDataRealizacao("");
			form.setValorArrecadacao("");
			form.setValorDevolucao("");
			form.setValorDeducoes("");
			form.setValorAviso("");
		}
		
		if (idArrecadador != null && !idArrecadador.trim().equals("")) {

			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.CODIGO_AGENTE, new Integer(idArrecadador)));
			filtroArrecadador
					.adicionarCaminhoParaCarregamentoEntidade("cliente");

			collectionArrecadador = fachada.pesquisar(filtroArrecadador,
					Arrecadador.class.getName());

			if (!collectionArrecadador.isEmpty()) {
				Iterator iterator = collectionArrecadador.iterator();
				if (iterator.hasNext())
				arrecadador = (Arrecadador) iterator.next();
				form.setNomeArrecadador(arrecadador.getCliente().getNome());
				sessao.setAttribute("arrecadador", arrecadador);
				
				FiltroArrecadadorContrato filtroArrecadadorContrato = new FiltroArrecadadorContrato();
				filtroArrecadadorContrato.adicionarParametro(new ParametroSimples(
						FiltroArrecadadorContrato.ARRECADADOR_CODIGO_AGENTE, idArrecadador));
				filtroArrecadadorContrato.adicionarParametro(new ParametroNulo(
						FiltroArrecadadorContrato.DATA_CONTRATO_ENCERRAMENTO));
				filtroArrecadadorContrato
						.adicionarCaminhoParaCarregamentoEntidade("contaBancariaDepositoArrecadacao.agencia.banco");

				Collection colecaoArrecadadorContrato = fachada.pesquisar(
						filtroArrecadadorContrato, ArrecadadorContrato.class
								.getName());

				if (colecaoArrecadadorContrato != null
						&& !colecaoArrecadadorContrato.isEmpty()) {
					
					httpServletRequest.setAttribute("colecaoArrecadadorContrato", colecaoArrecadadorContrato);
				}else{
					throw new ActionServletException("atencao.arrecadador.sem.contrato");
				}
			} else {
				httpServletRequest.setAttribute("arrecadadorInexistente", true);
				form.setCodigoArrecadador("");
				form.setNomeArrecadador("ARRECADADOR INEXISTENTE");
			}
		}
		
		sessao.setAttribute("data", dataLancamento);
		return retorno;
	}

}
