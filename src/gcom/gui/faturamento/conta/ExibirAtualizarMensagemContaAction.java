package gcom.gui.faturamento.conta;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMensagem;
import gcom.faturamento.conta.FiltroContaMensagem;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>/
 * 
 * @author Administrador
 */
public class ExibirAtualizarMensagemContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarMensagemContaAction");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarMensagemContaActionForm atualizarMensagemContaActionForm = (AtualizarMensagemContaActionForm) actionForm;

		atualizarMensagemContaActionForm.setGerenciaRegional("");
		atualizarMensagemContaActionForm.setGrupoFaturamento("");
		atualizarMensagemContaActionForm.setLocalidade("");
		atualizarMensagemContaActionForm.setLocalidadeDescricao("");
		atualizarMensagemContaActionForm.setMensagemConta01("");
		atualizarMensagemContaActionForm.setMensagemConta02("");
		atualizarMensagemContaActionForm.setMensagemConta03("");
		atualizarMensagemContaActionForm.setReferenciaFaturamento("");
		atualizarMensagemContaActionForm.setSetorComercial("");
		atualizarMensagemContaActionForm.setSetorComercialDescricao("");
		atualizarMensagemContaActionForm.setQuadra("");

		String idMensagemConta = httpServletRequest
				.getParameter("idMensagemConta");

		if (idMensagemConta != null && !idMensagemConta.equalsIgnoreCase("")) {

	        FiltroContaMensagem filtroContaMensagem = new FiltroContaMensagem();
			filtroContaMensagem.adicionarParametro(new ParametroSimples(
					FiltroContaMensagem.ID, idMensagemConta));
			filtroContaMensagem
					.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroContaMensagem
					.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
			filtroContaMensagem
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroContaMensagem
					.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
			filtroContaMensagem
					.adicionarCaminhoParaCarregamentoEntidade("quadra");
			Collection colecaoMensagemConta = fachada.pesquisar(
					filtroContaMensagem, ContaMensagem.class.getName());
			sessao.setAttribute("colecaoContaMensagem", colecaoMensagemConta);
			if (colecaoMensagemConta != null && !colecaoMensagemConta.isEmpty()) {
				for (Iterator iter = colecaoMensagemConta.iterator(); iter
						.hasNext();) {
					ContaMensagem contaMensagem = (ContaMensagem) iter.next();

					FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
					
					Collection sistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());

					Integer refSistemaParametro = new Integer(((SistemaParametro) sistemaParametro.iterator().next()).getAnoMesFaturamento());
					Integer refContaMensagem = new Integer (contaMensagem.getAnoMesRreferenciaFaturamento());
					
					if (refSistemaParametro > refContaMensagem){
						FiltroSistemaParametro filtroSistemaParametro2 = new FiltroSistemaParametro();
						Collection colecaoFSP = fachada.pesquisar(filtroSistemaParametro2, SistemaParametro.class.getName());
						
						if (colecaoFSP != null && !colecaoFSP.isEmpty()){
							SistemaParametro sistemaParametro2 = (SistemaParametro) colecaoFSP.iterator().next();
						
							String mesAno = Util.formatarAnoMesParaMesAno(sistemaParametro2.getAnoMesFaturamento());
							
							throw new ActionServletException(
								 	"atencao.mensagem_nao_inserida", null, mesAno);
						}
					}
		
					atualizarMensagemContaActionForm
							.setReferenciaFaturamento(Util
									.formatarAnoMesParaMesAno(contaMensagem
											.getAnoMesRreferenciaFaturamento()
											.toString()));
					if (contaMensagem.getFaturamentoGrupo() != null) {
						atualizarMensagemContaActionForm
								.setGrupoFaturamento(contaMensagem
										.getFaturamentoGrupo().getDescricao());
					}
					if (contaMensagem.getGerenciaRegional() != null) {
						atualizarMensagemContaActionForm
								.setGerenciaRegional(contaMensagem
										.getGerenciaRegional().getNome());
					}
					if (contaMensagem.getLocalidade() != null) {
						atualizarMensagemContaActionForm
								.setLocalidade(contaMensagem.getLocalidade()
										.getId().toString());
						atualizarMensagemContaActionForm
								.setLocalidadeDescricao(contaMensagem
										.getLocalidade().getDescricao());
					}
					if (contaMensagem.getSetorComercial() != null) {
						atualizarMensagemContaActionForm
								.setSetorComercial(new Integer(contaMensagem
										.getSetorComercial().getCodigo())
										.toString());
						atualizarMensagemContaActionForm
								.setSetorComercialDescricao(contaMensagem
										.getSetorComercial().getDescricao());
					}
					if (contaMensagem.getSetorComercial() != null) {
						atualizarMensagemContaActionForm
								.setSetorComercial(new Integer(contaMensagem
										.getSetorComercial().getCodigo())
										.toString());
						atualizarMensagemContaActionForm
								.setSetorComercialDescricao(contaMensagem
										.getSetorComercial().getDescricao());
					}
					if (contaMensagem.getQuadra() != null) {
						atualizarMensagemContaActionForm
								.setQuadra(contaMensagem
										.getQuadra().getNumeroQuadra() +"");
					}
					if (contaMensagem.getDescricaoContaMensagem02() != null) {
						atualizarMensagemContaActionForm
								.setMensagemConta02(contaMensagem
										.getDescricaoContaMensagem02());
					}
					if (contaMensagem.getDescricaoContaMensagem03() != null) {
						atualizarMensagemContaActionForm
								.setMensagemConta03(contaMensagem
										.getDescricaoContaMensagem03() );
					}
					atualizarMensagemContaActionForm
							.setMensagemConta01(contaMensagem
									.getDescricaoContaMensagem01());
				}
			} else {
				throw new ActionServletException(
						"atencao.conta_mensagem_nao_existente");
			}

		} else {
			Collection colecaoMensagemConta = (Collection) sessao
					.getAttribute("colecaoContaMensagem");

			if (colecaoMensagemConta != null && !colecaoMensagemConta.isEmpty()) {
				

				for (Iterator iter = colecaoMensagemConta.iterator(); iter
						.hasNext();) {
					ContaMensagem contaMensagem = (ContaMensagem) iter.next();
					
					
					FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
					filtroSistemaParametro.adicionarParametro(new MaiorQue(FiltroSistemaParametro.ANO_MES_REFERECIA_ARRECADACAO, contaMensagem.getAnoMesRreferenciaFaturamento()));
					
					Collection sistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());

					if (sistemaParametro != null && !sistemaParametro.isEmpty()){
						Integer refSistemaParametro = new Integer(((SistemaParametro) sistemaParametro.iterator().next()).getAnoMesFaturamento());
						Integer refContaMensagem = new Integer (contaMensagem.getAnoMesRreferenciaFaturamento());
						
						if (refSistemaParametro > refContaMensagem){
							FiltroSistemaParametro filtroSistemaParametro2 = new FiltroSistemaParametro();
							Collection colecaoFSP = fachada.pesquisar(filtroSistemaParametro2, SistemaParametro.class.getName());
							
							if (colecaoFSP != null && !colecaoFSP.isEmpty()){
								SistemaParametro sistemaParametro2 = (SistemaParametro) colecaoFSP.iterator().next();
							
								String mesAno = Util.formatarAnoMesParaMesAno(sistemaParametro2.getAnoMesFaturamento());
								
								throw new ActionServletException(
									 	"atencao.mensagem_nao_inserida", null, mesAno);
							}
						}
					}
					
					atualizarMensagemContaActionForm
							.setReferenciaFaturamento(Util
									.formatarAnoMesParaMesAno(contaMensagem
											.getAnoMesRreferenciaFaturamento()
											.toString()));
					if (contaMensagem.getFaturamentoGrupo() != null) {
						atualizarMensagemContaActionForm
								.setGrupoFaturamento(contaMensagem
										.getFaturamentoGrupo().getDescricao());
					}
					if (contaMensagem.getGerenciaRegional() != null) {
						atualizarMensagemContaActionForm
								.setGerenciaRegional(contaMensagem
										.getGerenciaRegional().getNome());
					}
					if (contaMensagem.getLocalidade() != null) {
						atualizarMensagemContaActionForm
								.setLocalidade(contaMensagem.getLocalidade()
										.getId().toString());
						atualizarMensagemContaActionForm
								.setLocalidadeDescricao(contaMensagem
										.getLocalidade().getDescricao());
					}
					if (contaMensagem.getSetorComercial() != null) {
						atualizarMensagemContaActionForm
								.setSetorComercial(new Integer(contaMensagem
										.getSetorComercial().getId())
										.toString());
						atualizarMensagemContaActionForm
								.setSetorComercialDescricao(contaMensagem
										.getSetorComercial().getDescricao());
					}
					if (contaMensagem.getSetorComercial() != null) {
						atualizarMensagemContaActionForm
								.setSetorComercial(new Integer(contaMensagem
										.getSetorComercial().getCodigo())
										.toString());
						atualizarMensagemContaActionForm
								.setSetorComercialDescricao(contaMensagem
										.getSetorComercial().getDescricao());
					}
					if (contaMensagem.getQuadra() != null) {
						atualizarMensagemContaActionForm
								.setQuadra(contaMensagem
										.getQuadra().getNumeroQuadra() + "");
					}
					if (contaMensagem.getDescricaoContaMensagem02() != null && 
							!contaMensagem.getDescricaoContaMensagem02().equals("")) {
						atualizarMensagemContaActionForm
								.setMensagemConta02(contaMensagem
										.getDescricaoContaMensagem02());
					}
					if (contaMensagem.getDescricaoContaMensagem03() != null && 
							!contaMensagem.getDescricaoContaMensagem03().equals("")) {
						atualizarMensagemContaActionForm
								.setMensagemConta03(contaMensagem
										.getDescricaoContaMensagem03());
					}
					atualizarMensagemContaActionForm
							.setMensagemConta01(contaMensagem
									.getDescricaoContaMensagem01());
				}
			} else {
				throw new ActionServletException(
						"atencao.conta_mensagem_nao_existente");
			}
		}
		
		if (httpServletRequest.getParameter("url") != null && httpServletRequest.getParameter("url").equalsIgnoreCase("manter")){
			httpServletRequest.setAttribute("url","javascript:history.back();");
		}else{
			httpServletRequest.setAttribute("url","javascript:window.location.href='/gsan/exibirFiltrarMensagemContaAction.do';");
		}

		return retorno;

	}

}
