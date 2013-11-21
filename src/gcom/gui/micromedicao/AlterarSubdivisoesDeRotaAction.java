package gcom.gui.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.Rota;
import gcom.micromedicao.bean.InformarSubdivisoesDeRotaHelper;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @see gcom.gui.micromedicao.InformarSubdivisoesDeRotaAction
 * @see gcom.gui.micromedicao.InformarSubdivisoesDeRotaActionForm
 * @see gcom.gui.micromedicao.ExibirInformarSubdivisoesDeRotaAction
 * 
 * @author Ivan Sergio
 * @date 23/02/2010
 */
public class AlterarSubdivisoesDeRotaAction extends GcomAction {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("telaSucesso");
		HttpSession session = request.getSession();
		Fachada fachada = Fachada.getInstancia();
		
		List<InformarSubdivisoesDeRotaActionForm> subdivisoes = 
			(List<InformarSubdivisoesDeRotaActionForm>) session.getAttribute("subdivisoesDeRota");
		
		if (subdivisoes == null || subdivisoes.size() == 0) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Subdivisoes de Rota");
		}
		
		Integer idRotaOriginal = new Integer(subdivisoes.get(0).getIdRota());		
		List<InformarSubdivisoesDeRotaHelper> helpers = new ArrayList<InformarSubdivisoesDeRotaHelper>();
		session.setAttribute("quadraFinalAnterior", -1);
		for (InformarSubdivisoesDeRotaActionForm subdivisao : subdivisoes) {
			//*******************************************************************
			// Recupera os dados da Alteracao
			//*******************************************************************
			Integer quadraFinalAnterior = (Integer) session.getAttribute("quadraFinalAnterior");
			int quadraInicial = Integer.parseInt(request.getParameter("quadra_ini_" + subdivisao.getCodigoRota()));
			int quadraFinal = Integer.parseInt(request.getParameter("quadra_fim_" + subdivisao.getCodigoRota()));
			Integer idLeiturista = new Integer(request.getParameter("leiturista_" + subdivisao.getCodigoRota()));
			
			if (quadraFinal < quadraInicial) {
				throw new ActionServletException("atencao.quadraInicial.maior.que.quadraFinal");
			}
			
			if (quadraFinalAnterior != null && quadraInicial <= quadraFinalAnterior) {
				session.removeAttribute("quadraFinalAnterior");
				throw new ActionServletException("atencao.quadraInicial.menor.que.quadraFinal.anterior");
			}
			
			session.setAttribute("quadraFinalAnterior", quadraFinal);
			//*******************************************************************
			
			InformarSubdivisoesDeRotaHelper helper = new InformarSubdivisoesDeRotaHelper();
			helper.setIdRota(new Integer(subdivisao.getIdRota()));
			helper.setCodigoRota(new Short(subdivisao.getCodigoRota()));
			helper.setQuadraInicial(quadraInicial);
			helper.setQuadraFinal(quadraFinal);
			helper.setIdLeiturista(idLeiturista);
			helpers.add(helper);
		}
		
		for (int i = 0; i < helpers.size() -1; i++) {
			helpers.get(i).setQuadraFinal(helpers.get(i +1).getQuadraInicial() -1);
		}
		
		if (helpers.size() > 0) {
			helpers.get(helpers.size() -1).setQuadraFinal(9999);
		}
		
		//*************************************************************
		// Atualiza as Quadras com os intervalos informados
		//*************************************************************
		for (InformarSubdivisoesDeRotaHelper rotasHelper : helpers) {
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.SETOR_COMERCIAL);
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, rotasHelper.getIdRota()));
			Rota rota = (Rota) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroRota, Rota.class.getName()));
			
			if (rota == null) {
				// Recupera a Rota pai para a verificacao do Grupo
				FiltroRota filtroRotaPai = new FiltroRota();
				filtroRotaPai.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRotaOriginal));
				Collection pesquisa = fachada.pesquisar(filtroRotaPai, Rota.class.getName());
				if (pesquisa == null || pesquisa.isEmpty()) {
					throw new ControladorException("atencao.pesquisa_inexistente", null, "Rota");
				}
				Rota rotaPai = (Rota) Util.retonarObjetoDeColecao(pesquisa);
				
				// Verifica de o Grupo ja foi comandado ou faturado
				this.verificarComandoFaturamento(
						rotaPai.getFaturamentoGrupo().getId(), rotasHelper.getCodigoRota().toString());
				
				// Insere a Nova Rota
				Integer idRota = fachada.inserirNovaSubdivisoesDeRota(
						idRotaOriginal,
						rotasHelper.getCodigoRota(),
						rotasHelper.getIdLeiturista());
				
				rotasHelper.setIdRota(idRota);
				
				filtroRota = new FiltroRota();
				filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.SETOR_COMERCIAL);
				filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, rotasHelper.getIdRota()));
				rota = (Rota) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroRota, Rota.class.getName()));
			}else {
				// Verifica de o Grupo ja foi comandado ou faturado
				this.verificarComandoFaturamento(
						rota.getFaturamentoGrupo().getId(), rota.getCodigo().toString());
				
				FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
				filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
				filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.ID, rotasHelper.getIdLeiturista()));
				Collection pesquisaLeiturista = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
				if (pesquisaLeiturista == null || pesquisaLeiturista.isEmpty()) {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Leiturista");
				}
				Leiturista leiturista = (Leiturista) Util.retonarObjetoDeColecao(pesquisaLeiturista);
				
				rota.setLeiturista(leiturista);
				
				fachada.atualizar(rota);
			}
			
			// Atualiza o intervalo da Rota
			fachada.atualizarRotaDasQuadrasNoIntervaloNumero(
					rota.getId(),
					rota.getSetorComercial().getId(),
					rotasHelper.getQuadraInicial(),
					rotasHelper.getQuadraFinal());
			
		}
		//*************************************************************
		
		montarPaginaSucesso(request, "Alterações nas Subdivisões da Rota de código " + 
				idRotaOriginal + " realizadas com sucesso.", "Informar outra subdivisao",
				"exibirInformarSubdivisoesDeRotaAction.do?menu=sim");

		return retorno;
	}
	
	private void verificarComandoFaturamento(Integer idGrupoFaturamento, String codigoRota) {
		Fachada fachada = Fachada.getInstancia();
		
		// Permitir somente a manutencao caso o grupo não foi comandado
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Integer anoMes = sistemaParametro.getAnoMesFaturamento();
		Boolean grupoComandado = fachada.verificarGrupoFaturamentoComandado(anoMes, idGrupoFaturamento);
		if (grupoComandado) {
			throw new ActionServletException("atencao.rota.grupo.comandado", null, codigoRota);
		}
		
		// Verifica se o grupo já foi faturado
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoGrupo.ID, idGrupoFaturamento));
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) Util.retonarObjetoDeColecao(fachada.pesquisar(
				filtroFaturamentoGrupo, FaturamentoGrupo.class.getName()));
		
		// && !sistemaParametro.getNomeAbreviadoEmpresa().equals(SistemaParametro.EMPRESA_COMPESA)
		// adicionado por Rômulo Aurélio
		// solicitado por Leonardo Vieira
		// Data:17/10/2010
		if (faturamentoGrupo.getAnoMesReferencia() <= anoMes 
				&& !sistemaParametro.getNomeAbreviadoEmpresa().equals(SistemaParametro.EMPRESA_COMPESA)) {
			throw new ActionServletException("atencao.rota.grupo.ja.faturado", null, codigoRota);
		}
	}

}
