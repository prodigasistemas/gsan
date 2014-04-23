package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirProcessoDoisInserirAvisoBancarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Declaracao de Variaveis principais
		ActionForward retorno = actionMapping
				.findForward("exibirInserirAvisoBancarioProcessoDois");
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		InserirAvisoBancarioActionForm form = (InserirAvisoBancarioActionForm) actionForm;
		// Declaracao de Variaveis principais

		form.setAvisoBancario(null);
		
		// Inicializacao de variaveis de pesquisa
		String idArrecadador = form.getCodigoArrecadador();
		String dataLancamento = form.getDataLancamento();
		// Inicializacao de variaveis de pesquisa

		Arrecadador arrecadadorVolta = (Arrecadador)sessao.getAttribute("arrecadador");
		String data = (String)sessao.getAttribute("data");
		if((sessao.getAttribute("arrecadador") != null 
				&&  !arrecadadorVolta.getId().toString().equalsIgnoreCase(idArrecadador)) 
				|| (data != null && !data.equalsIgnoreCase("") 
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
		
		// Filtro de Arrecadador caso usuário não tenho pesquisado no primeiro
		// processo
		Arrecadador arrecadador = null;
		Collection<Arrecadador> collectionArrecadador = null;
		if (idArrecadador != null && !idArrecadador.trim().equals("")) {

			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.CODIGO_AGENTE, idArrecadador));
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
			} else {
				throw new ActionServletException(
						"atencao.arrecadador.codigo.inexistente", null,
						idArrecadador);
			}
		}

		// Inicializacao de filtrode aviso bancario
		FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();

		if (idArrecadador != null && !idArrecadador.equals("")) {

			filtroAvisoBancario.adicionarParametro(new ParametroSimples(
					FiltroAvisoBancario.ARRECADADOR_CODIGO_AGENTE,
					idArrecadador));

		}

		Date dataAtual = new Date();

		Date dataLancamentoFormatada = null;

		if (dataLancamento != null && !dataLancamento.equals("")) {

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			try {
				dataLancamentoFormatada = format.parse(dataLancamento);
			} catch (ParseException ex) {
				throw new ActionServletException("erro.sistema");
			}

			if (dataLancamentoFormatada.after(dataAtual)) {

				String dataCorrente = format.format(dataAtual);

				throw new ActionServletException(
						"atencao.data.lancamento.posterior", null, dataCorrente);

			} else {

				filtroAvisoBancario.adicionarParametro(new ParametroSimples(
						FiltroAvisoBancario.DATA_LANCAMENTO,
						Util.formatarDataSemHora(dataLancamentoFormatada)));

			}

		}
		// Pesquisa de aviso bancario
		Collection<AvisoBancario> collectionAvisoBancario = fachada.pesquisar(
				filtroAvisoBancario, AvisoBancario.class.getName());

		String numeroPagina = httpServletRequest.getParameter("numeroPagina");

		if (collectionAvisoBancario == null
				|| collectionAvisoBancario.isEmpty()) {

			if (numeroPagina.equals("3")) {
				retorno = actionMapping
						.findForward("exibirInserirAvisoBancarioProcessoDoisParaUm");
				
			} else {

				retorno = actionMapping
						.findForward("exibirInserirAvisoBancarioProcessoDoisParaTres");
				Short numeroSequencial = fachada
						.pesquisarValorMaximoNumeroSequencial(
								dataLancamentoFormatada, ""
										+ arrecadador.getId());

				if (numeroSequencial != null) {
					numeroSequencial = new Short(""
							+ (numeroSequencial.intValue() + 1));
				} else {
					numeroSequencial = 1;
				}
				form.setNumeroSequencial("" + numeroSequencial);

			}

		} else {

			filtroAvisoBancario.adicionarParametro(new ParametroNulo(
					FiltroAvisoBancario.DATA_REALIZADA));
			// Inicializacao de filtrode aviso bancario

			// Pesquisa de aviso bancario
			filtroAvisoBancario.setCampoOrderBy(FiltroAvisoBancario.NUMERO_SEQUENCIAL);
			collectionAvisoBancario = fachada.pesquisar(filtroAvisoBancario,
					AvisoBancario.class.getName());
			// Pesquisa de aviso bancario

			// Validacao de collection nao nula e nao vazia
			if (collectionAvisoBancario != null
					&& !collectionAvisoBancario.isEmpty()) {
				httpServletRequest.setAttribute("collectionAvisoBancario",
						collectionAvisoBancario);

			} else {
				if (numeroPagina.equals("3")) {
					retorno = actionMapping
							.findForward("exibirInserirAvisoBancarioProcessoDoisParaUm");
				} else {
					retorno = actionMapping
							.findForward("exibirInserirAvisoBancarioProcessoDoisParaTres");
					form.setNumeroSequencial("1");
				}
			}

			// Validacao de collection nao nula e nao vazia

		}
		return retorno;
	}

}
