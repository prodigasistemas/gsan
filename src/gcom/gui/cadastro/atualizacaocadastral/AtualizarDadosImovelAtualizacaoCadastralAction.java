package gcom.gui.cadastro.atualizacaocadastral;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.util.ConstantesSistema;

public class AtualizarDadosImovelAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm form = (ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm) actionForm;
		
		ImovelControleAtualizacaoCadastral controle = getFachada().pesquisarImovelControleAtualizacao(
				Integer.valueOf(form.getIdImovel()), Integer.valueOf(form.getIdTipoAlteracao()));
		
		CadastroOcorrencia ocorrencia = controle.getCadastroOcorrencia();

		if (ocorrencia != null) {
			if (permiteAprovarOuConcluirFiscalizacao(controle, ocorrencia.getIndicadorValidacao(), Integer.valueOf(form.getIdTipoAlteracao()))) {

				if (isImovelFiscalizado(controle)) {
					String registrosSelecionados = null;

					if (controle.isAguardandoAnalise()) {
						registrosSelecionados = form.getIdRegistrosFiscalizados();
					} else {
						registrosSelecionados = form.getIdRegistrosAutorizados();
					}

					if (StringUtils.isNotEmpty(registrosSelecionados)) {
						String[] listaIdRegistrosSim = registrosSelecionados.split(",");

						if (isDefinicaoSubcategoriaValida(controle, form.getIdImovel(), listaIdRegistrosSim, Integer.valueOf(form.getIdTipoAlteracao()))) {
							
							if (listaIdRegistrosSim != null && !listaIdRegistrosSim.equals("")) {
								getFachada().atualizarIndicadorAutorizacaoColunaAtualizacaoCadastral(Integer.valueOf(form.getIdImovel()),
										listaIdRegistrosSim, ConstantesSistema.SIM, getUsuarioLogado(request), obterCampoParaAtualizar(controle), Integer.valueOf(form.getIdTipoAlteracao()));
								
								this.atualizarSituacaoImovel(controle, Integer.valueOf(form.getIdTipoAlteracao()));
							}
						} else {
							throw new ActionServletException("atencao.imovel.sem.subcategorias", "");
						}
					} else if (controle.isAguardandoAnalise()) {
						this.atualizarSituacaoImovel(controle, Integer.valueOf(form.getIdTipoAlteracao()));   
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

	private void atualizarSituacaoImovel(ImovelControleAtualizacaoCadastral imovelControle, Integer tipoAlteracao) {
		if (imovelControle.isAguardandoAnalise()) {
			getFachada().atualizarSituacaoImovelControle(imovelControle.getImovel().getId(), SituacaoAtualizacaoCadastral.FISCALIZADO);
		} else if (imovelControle.isPreAprovado() || imovelControle.isFiscalizado())   {
			if (tipoAlteracao == AlteracaoTipo.INCLUSAO.intValue()) {
				getFachada().aprovarImovel(imovelControle.getId(), tipoAlteracao);
			} else {
				getFachada().aprovarImovel(imovelControle.getImovel().getId(), tipoAlteracao);
			}
			
			
		}
	}

	private boolean isImovelFiscalizado(ImovelControleAtualizacaoCadastral imovelControle) {
		boolean imovelFiscalizado = true;

		if (imovelControle.isAguardandoAnalise() && !getFachada().possuiInformacoesFiscalizacao(imovelControle)) {
			imovelFiscalizado = false;
		}

		return imovelFiscalizado;
	}

	private String obterCampoParaAtualizar(ImovelControleAtualizacaoCadastral imovelControle) {
		String campo = "";
		if (imovelControle.isPreAprovado())
			campo = "preaprovado";

		if (imovelControle.isAguardandoAnalise() || imovelControle.isFiscalizado())
			campo = "fiscalizado";
		return campo;
	}
	
	private boolean permiteAprovarOuConcluirFiscalizacao(ImovelControleAtualizacaoCadastral controle, short indicadorValidacao, Integer tipoAlteracao) {
		return imovelValido(controle, indicadorValidacao, tipoAlteracao) || 
			   imovelNovoValido(controle, indicadorValidacao, tipoAlteracao) ||
			   controle.isAguardandoAnalise() ||
			   controle.isFiscalizado();
	}

	private boolean imovelValido(ImovelControleAtualizacaoCadastral controle, short indicadorValidacao, Integer tipoAlteracao) {
		return controle.isPreAprovado() && retornoValidado(indicadorValidacao) && tipoAlteracao.intValue() == AlteracaoTipo.ALTERACAO.intValue();
	}
	
	private boolean imovelNovoValido(ImovelControleAtualizacaoCadastral controle, short indicadorValidacao, Integer tipoAlteracao) {
		return controle.isPreAprovado() && tipoAlteracao.intValue() == AlteracaoTipo.INCLUSAO.intValue();
	}

	private boolean retornoValidado(short indicadorValidacao) {
		return indicadorValidacao == ConstantesSistema.SIM.shortValue();
	}
	
	private boolean isDefinicaoSubcategoriaValida(ImovelControleAtualizacaoCadastral controle, String idImovel,String[] registrosSelecionados, Integer tipoAlteracao) {
		return getFachada().isDefinicaoSubcategoriaValida(idImovel, registrosSelecionados, tipoAlteracao);
	}
}
