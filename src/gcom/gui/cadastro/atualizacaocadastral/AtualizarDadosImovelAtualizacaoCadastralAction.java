package gcom.gui.cadastro.atualizacaocadastral;

import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarDadosImovelAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm form = (ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm) actionForm;

		ImovelControleAtualizacaoCadastral controle = getFachada().pesquisarImovelControleAtualizacao(new Integer(form.getIdImovel()));
		CadastroOcorrencia ocorrencia = controle.getCadastroOcorrencia();

		if (ocorrencia != null) {
			if (permiteAprovarOuConcluirFiscalizacao(controle, ocorrencia.getIndicadorValidacao())) {

				if (isImovelFiscalizado(controle)) {
					String registrosSelecionados = null;

					if (controle.isEmFiscalizacao()) {
						registrosSelecionados = form.getIdRegistrosFiscalizados();
					} else {
						registrosSelecionados = form.getIdRegistrosAutorizados();
					}

					if (!registrosSelecionados.equals("")) {
						String[] listaIdRegistrosSim = registrosSelecionados.split(",");

						if (listaIdRegistrosSim != null && !listaIdRegistrosSim.equals("")) {
							getFachada().atualizarIndicadorAutorizacaoColunaAtualizacaoCadastral(Integer.valueOf(form.getIdImovel()), listaIdRegistrosSim, ConstantesSistema.SIM,
									(Usuario) request.getSession(false).getAttribute("usuarioLogado"), obterCampoParaAtualizar(controle));

							this.atualizarSituacaoImovel(controle);
						}
					}

					request.setAttribute("reload", true);
				} else {
					throw new ActionServletException("atencao.imovel_nao_fiscalizado", "");
				}
			} else {
				throw new ActionServletException("atencao.cadastro_ocorrencia_invalido", ocorrencia.getDescricao());
			}
		} else {
			throw new ActionServletException("atencao.cadastro_ocorrencia_invalido", "NULO. Carregue o imóvel novamente");
		}

		return mapping.findForward("filtrarAlteracaoAtualizacaoCadastral");
	}

	private void atualizarSituacaoImovel(ImovelControleAtualizacaoCadastral imovelControle) {
		if (imovelControle.isEmFiscalizacao()) {
			getFachada().atualizarSituacaoImovelControle(imovelControle.getImovel().getId(), SituacaoAtualizacaoCadastral.FISCALIZADO);
		} else if (imovelControle.isPreAprovado() || imovelControle.isFiscalizado()) {
			getFachada().atualizarSituacaoImovelControle(imovelControle.getImovel().getId(), SituacaoAtualizacaoCadastral.APROVADO);
		}
	}

	private boolean isImovelFiscalizado(ImovelControleAtualizacaoCadastral imovelControle) {
		boolean imovelFiscalizado = true;

		if (imovelControle.isEmFiscalizacao() && !getFachada().possuiInformacoesFiscalizacao(imovelControle)) {
			imovelFiscalizado = false;
		}

		return imovelFiscalizado;
	}

	private String obterCampoParaAtualizar(ImovelControleAtualizacaoCadastral imovelControle) {
		String campo = "";
		if (imovelControle.isPreAprovado())
			campo = "preaprovado";

		if (imovelControle.isFiscalizado())
			campo = "fiscalizado";
		return campo;
	}
	
	private boolean permiteAprovarOuConcluirFiscalizacao(ImovelControleAtualizacaoCadastral controle, short indicadorValidacao) {
		return (controle.isPreAprovado() && retornoValidado(indicadorValidacao)) || controle.isEmFiscalizacao();
	}
	
	private boolean retornoValidado(short indicadorValidacao) {
		return indicadorValidacao == ConstantesSistema.SIM.shortValue();
	}
}
