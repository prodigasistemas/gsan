package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.FiltroAtividade;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoAtividade;
import gcom.atendimentopublico.ordemservico.ServicoTipoBoletim;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirServicoTipoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		InserirServicoTipoActionForm form = (InserirServicoTipoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		String idOperacao = form.getIdOperacao();

		ServicoTipo servicoTipo = form.getServicoTipo();

		FiltroOperacao filtroOperacao = new FiltroOperacao();
		filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID, idOperacao));

		Collection colecaoOperacao = fachada.pesquisar(filtroOperacao, Operacao.class.getName());

		if (colecaoOperacao.isEmpty()) {
			servicoTipo.setOperacao(null);
		} else {
			Operacao operacao = new Operacao();
			operacao.setId(Integer.parseInt(idOperacao));
			operacao.setDescricao(form.getDescricaoOperacao());

			servicoTipo.setOperacao(operacao);
		}

		if (servicoTipo.getServicoTipoAtividades() == null || servicoTipo.getServicoTipoAtividades().isEmpty()) {

			if (form.getAtividadeUnica() != null && form.getAtividadeUnica().trim().equals("1")) {

				FiltroAtividade filtroAtividade = new FiltroAtividade();
				filtroAtividade.adicionarParametro(new ParametroSimples(FiltroAtividade.INDICADORATIVIDADEUNICA, Atividade.INDICADOR_ATIVIDADE_UNICA_SIM));

				Collection colecaoAtividade = fachada.pesquisar(filtroAtividade, Atividade.class.getName());

				if (colecaoAtividade.isEmpty()) {
					throw new ActionServletException("atencao.naocadastrado", null, "Atividade");
				} else {
					Atividade atividadeUnica = (Atividade) Util.retonarObjetoDeColecao(colecaoAtividade);

					ServicoTipoAtividade servicoTipoAtividade = new ServicoTipoAtividade();

					servicoTipoAtividade.setAtividade(atividadeUnica);

					Collection colecaoServicoTipoAtividade = new ArrayList();

					colecaoServicoTipoAtividade.add(servicoTipoAtividade);

					servicoTipo.setServicoTipoAtividades(colecaoServicoTipoAtividade);
				}
			} else {
				throw new ActionServletException("atencao.adionar.atividade.servico.tipo");
			}
		}

		if (sessao.getAttribute("servicoTipoReferencia") != null) {
			servicoTipo.setServicoTipoReferencia((ServicoTipoReferencia) sessao.getAttribute("servicoTipoReferencia"));
		}

		if (servicoTipo.getIndicadorFiscalizacaoInfracao() == 1) {
			if (servicoTipo.getServicoTipoReferencia() != null) {
				throw new ActionServletException("atencao.infracao.servico.tipo");
			}
		}
		
		ServicoTipoBoletim servicoTipoBoletim = null;
		if (form.getIndicadorInformacoesBoletimMedicao() != null) {
			servicoTipo.setIndicadorBoletim(new Short(form.getIndicadorInformacoesBoletimMedicao()));

			if (form.getIndicadorInformacoesBoletimMedicao().equals("1")) {

				servicoTipoBoletim = new ServicoTipoBoletim();
				servicoTipoBoletim.setIndicadorPavimento(new Short(form.getIndicativoPavimento()));
				servicoTipoBoletim.setIndicadorReposicaoAsfalto(new Short(form.getIndicativoReposicaoAsfalto()));
				servicoTipoBoletim.setIndicadorReposicaoCalcada(new Short(form.getIndicativoReposicaoCalcada()));
				servicoTipoBoletim.setIndicadorReposicaoParalelo(new Short(form.getIndicativoReposicaoParalelo()));

			}
		}

		if (form.getIndicadorEncAutomaticoRAQndEncOS() != null && !form.getIndicadorEncAutomaticoRAQndEncOS().equals("")) {
			servicoTipo.setIndicadorEncAutomaticoRAQndEncOS(new Short(form.getIndicadorEncAutomaticoRAQndEncOS()));
		}

		servicoTipo.setIndicadorInspecaoAnormalidade(Short.parseShort(form.getIndicadorInspecaoAnormalidade()));

		servicoTipo.setIndicadorServicoOrdemSeletiva(Short.parseShort(form.getIndicadorOrdemSeletiva()));

		servicoTipo.setIndicadorEnvioPesquisaSatisfacao(Short.parseShort(form.getIndicadorEnvioPesquisaSatisfacao()));

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		// Insere todos os ServicoTipoMotivoEncerramento selecionados no popup
		if (sessao.getAttribute("motivosEncerramentoEscolhidos") != null) {
			Collection colecaoAtendimentoMotivosEncerramento = (Collection) sessao.getAttribute("motivosEncerramentoEscolhidos");
			servicoTipo.setServicoTipoMotivoEncerramentos(colecaoAtendimentoMotivosEncerramento);

		}

		servicoTipo.setIndicadorPermiteAlteracaoImovelEmCampo(ConstantesSistema.SIM);

		if (servicoTipo.getDebitoTipo() != null && servicoTipo.getValor() != null && servicoTipo.getValor().doubleValue() > 0) {
			servicoTipo.setIndicadorIncluirDebito(ConstantesSistema.SIM);
		} else {
			servicoTipo.setIndicadorIncluirDebito(ConstantesSistema.NAO);
		}
		
		Integer idServicoTipo = fachada.inserirServicoTipo(servicoTipo, usuarioLogado, servicoTipoBoletim);

		montarPaginaSucesso(httpServletRequest, "Tipo de Serviço " + servicoTipo.getDescricao() + " inserido com sucesso.", 
				"Inserir Outro Tipo de Serviço",
				"exibirInserirServicoTipoAction.do?menu=sim", 
				"exibirAtualizarTipoServicoAction.do?pesquisa=S&idRegistroAtualizacao=" + idServicoTipo,
				"Atualizar Tipo de Serviço Inserido");

		return retorno;

	}

}
