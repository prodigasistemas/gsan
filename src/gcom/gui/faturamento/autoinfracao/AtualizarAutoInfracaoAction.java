package gcom.gui.faturamento.autoinfracao;

import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.fachada.Fachada;
import gcom.faturamento.autoinfracao.AutoInfracaoSituacao;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.faturamento.autoinfracao.FiltroAutoInfracaoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarAutoInfracaoAction extends GcomAction {

	/**
	 * 
	 * [UC0896] Manter Autos de Infração
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 05/05/2009
	 * 
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarAutoInfracaoActionForm form = (AtualizarAutoInfracaoActionForm) actionForm;

		// Usuario logado no sistema
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		AutosInfracao autosInfracao = (AutosInfracao) sessao
				.getAttribute("objetoAutosInfracao");

		// Funcionario

		if (form.getIdFuncionario() != null
				&& !form.getIdFuncionario().equals("")) {


			String idFuncionario = form.getIdFuncionario();

			Funcionario funcionario = this.validarFuncionario(idFuncionario); 
			
			autosInfracao
					.setFuncionario(funcionario);
		} else {
			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", null,
					"Funcionário Responsável");
		}

		// Situacao do Auto
		AutoInfracaoSituacao autoInfracaoSituacao = null;

		if (form.getIdAutoInfracaoSituacao() != null
				&& !form.getIdAutoInfracaoSituacao().equals("-1")) {

			FiltroAutoInfracaoSituacao filtroAutoInfracaoSituacao = new FiltroAutoInfracaoSituacao();

			filtroAutoInfracaoSituacao.adicionarParametro(new ParametroSimples(
					FiltroAutoInfracaoSituacao.ID, form
							.getIdAutoInfracaoSituacao()));

			Collection<AutoInfracaoSituacao> colecaoAutoInfracaoSituacao = this
					.getFachada().pesquisar(filtroAutoInfracaoSituacao,
							AutoInfracaoSituacao.class.getName());

			autoInfracaoSituacao = (AutoInfracaoSituacao) Util
					.retonarObjetoDeColecao(colecaoAutoInfracaoSituacao);

			autoInfracaoSituacao.setId(new Integer(form
					.getIdAutoInfracaoSituacao()));

			autosInfracao.setAutoInfracaoSituacao(autoInfracaoSituacao);

		} else {

			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", null,
					"Situação do Auto");

		}

		// Irregularidade Constatada

		if (form.getIdFiscalizacaoSituacao() != null
				&& !form.getIdFiscalizacaoSituacao().equals("-1")) {
			FiscalizacaoSituacao fiscalizacaoSituacao = new FiscalizacaoSituacao();

			fiscalizacaoSituacao.setId(new Integer(form
					.getIdFiscalizacaoSituacao()));

			autosInfracao.setFiscalizacaoSituacao(fiscalizacaoSituacao);

		} else {

			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", null,
					"Irregularidade Constatada");

		}

		// Data emissao
		if (form.getDataEmissao() != null && !form.getDataEmissao().equals("")) {

			Date dataEmissao = Util.converteStringParaDate(form
					.getDataEmissao());

			autosInfracao.setDataEmissao(dataEmissao);

		} else {
			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", null,
					"Data de Emissão");
		}

		// Data Inicio Recurso

		if (autoInfracaoSituacao.getIndicadorDataInicioRecurso().toString().equals(
				AutoInfracaoSituacao.OBRIGATORIO.toString())) {

			if (form.getDataInicioRecurso() != null
					&& !form.getDataInicioRecurso().equals("")) {

				Date dataInicioRecurso = Util.converteStringParaDate(form
						.getDataInicioRecurso());

				autosInfracao.setDataInicioRecurso(dataInicioRecurso);

			} else {
				throw new ActionServletException(
						"atencao.campo_selecionado.obrigatorio", null,
						"Data de Início do Recurso");
			}

		} else if (autoInfracaoSituacao.getIndicadorDataInicioRecurso().toString().equals(
				AutoInfracaoSituacao.OPCIONAL.toString())) {
			if (form.getDataInicioRecurso() != null
					&& !form.getDataInicioRecurso().equals("")) {

				Date dataInicioRecurso = Util.converteStringParaDate(form
						.getDataInicioRecurso());

				autosInfracao.setDataInicioRecurso(dataInicioRecurso);

			}

		}

		// Data Termino Recurso
		if (autoInfracaoSituacao.getIndicadorDataTerminoRecurso().toString().equals(
				AutoInfracaoSituacao.OBRIGATORIO.toString())) {

			if (form.getDataTerminoRecurso() != null
					&& !form.getDataTerminoRecurso().equals("")) {

				Date dataTerminoRecurso = Util.converteStringParaDate(form
						.getDataTerminoRecurso());

				autosInfracao.setDataTerminoRecurso(dataTerminoRecurso);

			} else {
				throw new ActionServletException(
						"atencao.campo_selecionado.obrigatorio", null,
						"Data de Término do Recurso");
			}

		} else if (autoInfracaoSituacao.getIndicadorDataTerminoRecurso().toString()
				.equals(AutoInfracaoSituacao.OPCIONAL.toString())) {

			if (form.getDataTerminoRecurso() != null
					&& !form.getDataTerminoRecurso().equals("")) {

				Date dataTerminoRecurso = Util.converteStringParaDate(form
						.getDataTerminoRecurso());

				autosInfracao.setDataTerminoRecurso(dataTerminoRecurso);

			}

		}

		// Observacao
		if (form.getObservacao() != null && !form.getObservacao().equals("")) {

			autosInfracao.setObservacao(form.getObservacao());

		}
		
		autosInfracao.setNumeroParcelasDebito(new Integer(form.getQuantidadeParcelas()));
		
		autosInfracao.setDebitoTipo(null);
		
		if(fachada.validarExistenciaDebitoAtivosAutoInfracao(autosInfracao.getId())){
			throw new ActionServletException("atencao.auto_possui_debitos_ativos");
		}

		fachada.atualizarAutoInfracao(autosInfracao, usuarioLogado);

		montarPaginaSucesso(httpServletRequest, "Auto de Infração de código "
				+ autosInfracao.getId() + " atualizado com sucesso.",
				"Realizar outra Manutenção Auto de Infração",
				"exibirFiltrarAutoInfracaoAction.do?menu=sim");

		return retorno;

	}

	private Funcionario validarFuncionario(String idFuncionario) {

		Funcionario funcionario = null;

		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

		filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.ID, idFuncionario));

		Collection<Funcionario> colecaoFuncionario = this.getFachada()
				.pesquisar(filtroFuncionario, Funcionario.class.getName());

		if (colecaoFuncionario == null || colecaoFuncionario.isEmpty()) {

			throw new ActionServletException("pesquisa.funcionario.inexistente");

		}
		funcionario = (Funcionario) Util
				.retonarObjetoDeColecao(colecaoFuncionario);
		return funcionario;
	}


}
