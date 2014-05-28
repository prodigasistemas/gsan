package gcom.gui.atendimentopublico;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Quadra;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ConectorAnd;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
public class ExibirAlterarSituacaoLigacaoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
		ActionForward retorno = actionMapping.findForward("alterarSituacaoLigacao");
		AlterarSituacaoLigacaoActionForm form = (AlterarSituacaoLigacaoActionForm) actionForm;


		OrdemServico ordemServico = null;
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {
				form.setIdOrdemServico(idOrdemServico);
				Imovel imovel = ordemServico.getImovel();
				sessao.setAttribute("imovel", ordemServico.getImovel());
				if (imovel != null) {
					String inscricaoImovel = fachada.pesquisarInscricaoImovel(imovel.getId());
					String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
					String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();

					IntegracaoQuadraFaceHelper integracao = fachada.integracaoQuadraFace(imovel.getId());
					this.pesquisarCliente(form, new Integer(matriculaImovel));
	private void pesquisarCliente(AlterarSituacaoLigacaoActionForm form, Integer matriculaImovel) {
		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
			if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
			form.setClienteUsuario(cliente.getNome());
	private void pesquisarSelectObrigatorio(HttpServletRequest httpServletRequest, AlterarSituacaoLigacaoActionForm form, 
			String indicadorRedeAgua, String indicadorRedeEsgoto) {
		Fachada fachada = Fachada.getInstancia();
		httpServletRequest.setAttribute("colecaoLigacaoAguaSituacao", new ArrayList());
		if (form.getIndicadorTipoLigacao() != null) {
					Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = null;
					httpServletRequest.setAttribute("comboLigacaoAgua", "sim");
					
						filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
						filtroLigacaoAguaSituacao.adicionarParametro( new ParametroSimples(
								FiltroLigacaoAguaSituacao.INDICADOR_FATURAMENTO, ConstantesSistema.NAO ));						
					}
						filtroLigacaoAguaSituacao.adicionarParametro( new ParametroSimples(
								FiltroLigacaoAguaSituacao.INDICADOR_FATURAMENTO, ConstantesSistema.NAO ) );							
					
					
					httpServletRequest.setAttribute("colecaoLigacaoAguaSituacao", colecaoLigacaoAguaSituacao);

			if (form.getIndicadorTipoLigacao().equals("2") || form.getIndicadorTipoLigacao().equals("3")) {
					
					Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = null;
					httpServletRequest.setAttribute("comboLigacaoEsgoto", "sim");
					
						filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
					}
					if (indicadorRedeEsgoto.equalsIgnoreCase("" + Quadra.COM_REDE)) {
						filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples(
								FiltroLigacaoEsgotoSituacao.INDICADORFATURAMENTOSITUACAO, ConstantesSistema.NAO ) );							
					if (indicadorRedeEsgoto.equalsIgnoreCase("" + Quadra.REDE_PARCIAL)) {
								FiltroLigacaoEsgotoSituacao.ID, LigacaoEsgotoSituacao.POTENCIAL,
						filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples(
								FiltroLigacaoEsgotoSituacao.INDICADORFATURAMENTOSITUACAO,
								ConstantesSistema.NAO));
					}
					
					httpServletRequest.setAttribute("colecaoLigacaoEsgotoSituacao", colecaoLigacaoEsgotoSituacao);