package gcom.gui.faturamento.autoinfracao;

import gcom.atendimentopublico.ordemservico.FiltroFiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.autoinfracao.AutoInfracaoSituacao;
import gcom.faturamento.autoinfracao.FiltroAutoInfracaoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarAutoInfracaoAction extends GcomAction {

	/**
	 * Filtrar Autos de Infração
	 * 
	 * @author Rômulo Aurélio - 22/04/2009
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirFiltrarAutoInfracaoAction");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		if(sessao.getAttribute("objetoAutosInfracao")!=null){
			sessao.removeAttribute("objetoAutosInfracao");
		}

		FiltrarAutoInfracaoActionForm form = (FiltrarAutoInfracaoActionForm) actionForm;
		
		this.carregarDados(form, httpServletRequest);

		this.pesquisarCamposEnter(form, httpServletRequest);

		return retorno;
	}

	private void carregarDados(FiltrarAutoInfracaoActionForm form,
			HttpServletRequest httpServletRequest) {
		
		form.setAtualizar("1");

		FiltroFiscalizacaoSituacao filtroFiscalizacaoSituacao = new FiltroFiscalizacaoSituacao();

		filtroFiscalizacaoSituacao.adicionarParametro(new ParametroSimples(
				FiltroFiscalizacaoSituacao.INDICADOR_ATUALIZACAO_AUTOS_INFRACAO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<FiscalizacaoSituacao> colecaoFiscalizacaoSituacao = this
				.getFachada().pesquisar(filtroFiscalizacaoSituacao,
						FiscalizacaoSituacao.class.getName());

		if (colecaoFiscalizacaoSituacao == null
				|| colecaoFiscalizacaoSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Fiscalizacao Situacao");
		}

		httpServletRequest.setAttribute("colecaoFiscalizacaoSituacao",
				colecaoFiscalizacaoSituacao);

		FiltroAutoInfracaoSituacao filtroAutoInfracaoSituacao = new FiltroAutoInfracaoSituacao();

		filtroAutoInfracaoSituacao.adicionarParametro(new ParametroSimples(
				FiltroAutoInfracaoSituacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<AutoInfracaoSituacao> colecaoAutoInfracaoSituacao = this
				.getFachada().pesquisar(filtroAutoInfracaoSituacao,
						AutoInfracaoSituacao.class.getName());

		if (colecaoAutoInfracaoSituacao == null
				|| colecaoAutoInfracaoSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Auto Infracao Situacao");
		}

		httpServletRequest.setAttribute("colecaoAutoInfracaoSituacao",
				colecaoAutoInfracaoSituacao);
	}

	private void pesquisarCamposEnter(FiltrarAutoInfracaoActionForm form,
			HttpServletRequest httpServletRequest) {

		String idImovel = (String) form.getIdImovel();

		if (idImovel != null && !idImovel.equals("")) {
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, idImovel));

			Collection imoveis = this.getFachada().pesquisar(filtroImovel,
					Imovel.class.getName());

			if (imoveis != null && !imoveis.isEmpty()) {
				Imovel imovel = (Imovel) ((List) imoveis).get(0);
				httpServletRequest.setAttribute("imovel", imovel);
				form.setDescricaoImovel(imovel.getInscricaoFormatada());
			} else {
				httpServletRequest.setAttribute("matriculaInexistente", "true");
				form.setIdImovel("");
				form.setDescricaoImovel("MATRÍCULA INEXISTENTE");
			}
		} else {
			form.setIdImovel("");
			form.setDescricaoImovel("");
		}

		String idFuncionario = (String) form.getIdFuncionario();

		if (idFuncionario != null && !idFuncionario.equals("")) {
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, idFuncionario));

			Collection colecaoFuncionario = this.getFachada().pesquisar(
					filtroFuncionario, Funcionario.class.getName());

			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

				Funcionario funcionario = (Funcionario) Util
						.retonarObjetoDeColecao(colecaoFuncionario);

				httpServletRequest.setAttribute("funcionario", funcionario
						.getId());
				form.setDescricaoFuncionario(funcionario.getNome());
			} else {
				httpServletRequest.setAttribute("funcionarioInexistente",
						"true");
				form.setIdFuncionario("");
				form.setDescricaoFuncionario("FUNCIONÁRIO INEXISTENTE");
			}
		} else {
			form.setIdFuncionario("");
			form.setDescricaoFuncionario("");
		}

	}

}
