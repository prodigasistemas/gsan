package gcom.gui.cadastro.atualizacaocadastral;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atualizacaocadastral.ImagemRetorno;
import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.SituacaoAguaHelper;
import gcom.cadastro.atualizacaocadastral.SituacaoEsgotoHelper;
import gcom.cadastro.atualizacaocadastral.SituacaoSubcategoriaHelper;
import gcom.cadastro.atualizacaocadastral.bean.DadosTabelaAtualizacaoCadastralHelper;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.util.AtualizacaoCadastralUtil;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

@SuppressWarnings("unchecked")
public class ExibirAtualizarDadosImovelAtualizacaoCadastralPopupAction extends GcomAction {
	
	private Fachada fachada = Fachada.getInstancia();

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		ActionForward retorno = actionMapping.findForward("exibirAtualizarDadosImovelAtualizacaoCadastralPopup");

		HttpSession sessao = request.getSession(false);

		ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm form = (ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm) actionForm;

		String idImovel = (String) request.getParameter("idImovel");
		String idTipoAlteracao = (String) request.getParameter("idTipoAlteracao");

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		form.setTemPermissaoAprovarImovel(temPermissaoAprovarImovel(usuario.getId(), idImovel));
		
		try {
			Collection<DadosTabelaAtualizacaoCadastralHelper> resumoImovel = new LinkedList<DadosTabelaAtualizacaoCadastralHelper>();

			if ((idImovel != null && !idImovel.equals(""))) {
				form.setIdImovel(idImovel);

				Imovel imovel = null;
				if (idTipoAlteracao.equals(AlteracaoTipo.ALTERACAO.toString()) || idTipoAlteracao.equals(AlteracaoTipo.EXCLUSAO.toString())) {
					imovel = pesquisarImovel(fachada, idImovel);
				}

				if (imovel != null) {
					form.setDescricaoImovel(imovel.getId().toString());
					
					Localidade localidade = imovel.getLocalidade();
					form.setIdLocalidade(localidade.getId().toString());
					form.setDescricaoLocalidade(localidade.getDescricao());
					
					SetorComercial setorComercial = imovel.getSetorComercial();
					form.setIdSetorComercial(setorComercial.getId().toString());
					form.setCodigoSetorComercial(Integer.toString(setorComercial.getCodigo()));
					form.setDescricaoSetorComercial(setorComercial.getDescricao());
					
					Quadra quadra = (Quadra) imovel.getQuadra();
					form.setIdQuadra(quadra.getId().toString());
					form.setNumeroQuadra(Integer.toString(quadra.getNumeroQuadra()));
					
					resumoImovel.add(new SituacaoAguaHelper(imovel.getLigacaoAguaSituacao().getDescricao()));
					resumoImovel.add(new SituacaoEsgotoHelper(imovel.getLigacaoEsgotoSituacao().getDescricao()));

					Collection<ImovelSubcategoriaAtualizacaoCadastral> subcategorias = fachada.pesquisarSubCategoriasAtualizacaoCadastral(imovel.getId());
					for (ImovelSubcategoriaAtualizacaoCadastral economia : subcategorias) {
						String subcategoria = economia.getDescricaoCategoria() + " - " + economia.getDescricaoSubcategoria();
						resumoImovel.add(new SituacaoSubcategoriaHelper(String.valueOf(economia.getQuantidadeEconomias()), subcategoria));
					}
				} else {
					form.setDescricaoImovel("NOVO");
					form.limparCampos();
				}
			}

			Collection<ImagemRetorno> imagens = fachada.pesquisarImagensRetornoPorIdImovel(Integer.parseInt(idImovel));
			if (imagens != null && !imagens.isEmpty()) {
				sessao.setAttribute("colecaoImagens", imagens);
			} else {
				sessao.setAttribute("colecaoImagens", null);
			}

			Map<String, List<DadosTabelaAtualizacaoCadastralHelper>> map = fachada.consultarDadosTabelaColunaAtualizacaoCadastral(
					null, null, Integer.valueOf(idImovel), null, null);

			Collection<DadosTabelaAtualizacaoCadastralHelper> atualizacoes = new AtualizacaoCadastralUtil().linhasAtualizacaoCadastral(
					resumoImovel, map);

			if (atualizacoes != null && !atualizacoes.isEmpty()) {
				sessao.setAttribute("colecaoDadosTabelaAtualizacaoCadastral", atualizacoes);
			}

			ImovelControleAtualizacaoCadastral controle = fachada.pesquisarImovelControleAtualizacao(Integer.valueOf(idImovel), Integer.valueOf(idTipoAlteracao));

			if (controle != null) {
				form.setSituacao(controle.getSituacaoAtualizacaoCadastral().getDescricao());
				
				sessao.setAttribute("aguardandoAnalise", controle.isAguardandoAnalise());
				sessao.setAttribute("exibirColunaFiscalizado", controle.isAguardandoAnalise() || controle.isFiscalizado() || controle.isAprovado());
				sessao.setAttribute("exibirLinkVisualizar", imovelAlterado(controle));
				sessao.setAttribute("exibirBotaoConcluirFiscalizacao", exibirBotaoConcluirFiscalizacao(controle));
				sessao.setAttribute("exibirBotaoAprovado", exibirBotaoAprovado(controle));
				sessao.setAttribute("exibirBotaoFiscalizar", exibirBotaoFiscalizar(controle));
			}
		} catch (Exception e) {
			throw new ActionServletException("erro.exibir.dados.atualizacao", e, "Dados do Imovel e Cliente");
		}

		return retorno;
	}

	private Imovel pesquisarImovel(Fachada fachada, String idImovel) {
		FiltroImovel filtro = new FiltroImovel();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);
		return (Imovel) Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, Imovel.class.getName()));
	}
	
	public boolean temPermissaoAprovarImovel(Integer idUsuario, String idImovel) {
		return fachada.verificarPermissaoAprovarImovel(idUsuario, new Integer(idImovel));
	}
	
	private boolean exibirBotaoFiscalizar(ImovelControleAtualizacaoCadastral controle) {
		return imovelAlterado(controle) && controle.isPreAprovado();
	}
	
	private boolean exibirBotaoConcluirFiscalizacao(ImovelControleAtualizacaoCadastral controle) {
		return controle != null && controle.isAguardandoAnalise();
	}
	
	private boolean exibirBotaoAprovado(ImovelControleAtualizacaoCadastral controle) {
		return controle != null && (controle.isPreAprovado() || controle.isFiscalizado());
	}
	
	private boolean imovelAlterado(ImovelControleAtualizacaoCadastral controle) {
		return controle != null && controle.getImovel() != null;
	}
}
