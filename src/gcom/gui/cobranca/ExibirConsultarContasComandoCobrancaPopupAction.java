package gcom.gui.cobranca;

import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CmdEmpresaCobrancaContaLigacaoAguaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarContasComandoCobrancaPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("exibirConsultarContasComandoCobrancaPopupAction");

		GerarArquivoTextoContasCobrancaEmpresaActionForm form = (GerarArquivoTextoContasCobrancaEmpresaActionForm) actionForm;

		HttpSession sessao = request.getSession(false);

		String pesquisar = request.getParameter("pesquisa");

		boolean entrou = false;

		Integer idComando = Integer.valueOf(request.getParameter("idComandoEmpresaCobrancaConta"));

		Date dataIncial = (Date) sessao.getAttribute("dataInicial");
		Date dataFinal = (Date) sessao.getAttribute("dataFinal");

		if (pesquisar != null && pesquisar.equals("sim") && !entrou) {

			Object[] dados = getFachada().pesquisarDadosPopupExtensaoComando(new Integer(idComando), dataIncial, dataFinal);

			if (dados[0] != null) form.setNomeEmpresa(dados[0].toString());
			if (dados[1] != null) form.setDataExecucaoComando(Util.formatarData((Date) dados[1]));
			if (dados[2] != null) form.setPeriodoReferenciaContasInicial((Util.formatarAnoMesParaMesAno(new Integer(dados[2].toString()).intValue())));
			if (dados[3] != null) form.setPeriodoReferenciaContasFinal((Util.formatarAnoMesParaMesAno(new Integer(dados[3].toString()).intValue())));
			if (dados[4] != null) form.setPeriodoVencimentoContasInicial(Util.formatarData((Date) dados[4]));
			if (dados[5] != null) form.setPeriodoVencimentoContasFinal(Util.formatarData((Date) dados[5]));
			if (dados[6] != null) form.setIntervaloValorContasInicial(Util.formatarMoedaReal(new BigDecimal(dados[6].toString())));
			if (dados[7] != null) form.setIntervaloValorContasFinal(Util.formatarMoedaReal(new BigDecimal(dados[7].toString())));
			if (dados[8] != null) form.setIdImovel(dados[8].toString());
			if (dados[9] != null) form.setIdCliente(dados[9].toString());
			if (dados[10] != null) form.setIntervaloLocalizacaoInicial(dados[10].toString());
			if (dados[11] != null) form.setIntervaloLocalizacaoFinal(dados[11].toString());
			if (dados[12] != null) form.setIntervaloSetorComercialInicial(dados[12].toString());
			if (dados[13] != null) form.setIntervaloSetorComercialFinal(dados[13].toString());
			if (dados[14] != null && !dados[14].equals("") && ((Integer) dados[14]).compareTo(0) != 0) {
				form.setIntervaloQuadraInicial(dados[14].toString());
			}
			if (dados[15] != null && !dados[15].equals("") && ((Integer) dados[15]).compareTo(0) != 0) {
				form.setIntervaloQuadraFinal(dados[15].toString());
			}
			if (dados[16] != null) form.setQtdeTotalContasCobranca(dados[16].toString());
			if (dados[17] != null) form.setValorTotalContasCobranca(Util.formatarMoedaReal(new BigDecimal(dados[17].toString())));
			if (dados[18] != null) form.setQtdeContasCriterioComando(dados[18].toString());
			if (dados[19] != null) form.setValorContasCriterioComando(Util.formatarMoedaReal(new BigDecimal(dados[19].toString())));

			Object[] dadosQtdContas = getFachada().pesquisarDadosQtdContasEDiasVencidos(new Integer(idComando));
			if (dadosQtdContas[0] != null && dadosQtdContas[1] != null) {
				form.setQtdContasInicial(dadosQtdContas[0].toString());
				form.setQtdContasFinal(dadosQtdContas[1].toString());
			}

			if (dadosQtdContas[2] != null) {
				form.setQtdDeDiasVencimento(dadosQtdContas[2].toString());
			}

			pesquisarUnidades(sessao, idComando);
			pesquisarGerencias(sessao, idComando);
			pesquisarLigacoesAgua(sessao, idComando);
			pesquisarPerfis(sessao, idComando);

			entrou = true;
		}
		
		if (pesquisar != null && pesquisar.equals("nao")) {
			request.setAttribute("pesquisa", "nao");
			request.setAttribute("idComandoEmpresaCobrancaConta", idComando);
		}
		
		return retorno;
	}

	private void pesquisarLigacoesAgua(HttpSession sessao, Integer idComando) {
		Collection<CmdEmpresaCobrancaContaLigacaoAguaSituacao> colecaoLigacaoAguaSituacao = getFachada().pesquisarColecaoLigacaoAguaSituacaoPorComandoEmpresaCobrancaConta(new Integer(idComando));
		if (colecaoLigacaoAguaSituacao != null && !colecaoLigacaoAguaSituacao.isEmpty()) {
			sessao.setAttribute("colecaoLigacaoAguaSituacao", colecaoLigacaoAguaSituacao);
		} else {
			sessao.removeAttribute("colecaoLigacaoAguaSituacao");
		}
	}

	private void pesquisarPerfis(HttpSession sessao, Integer idComando) {
		Collection<ImovelPerfil> perfis = new ArrayList<ImovelPerfil>();

		Collection<Object[]> colecao = Fachada.getInstancia().pesquisarDadosPopupExtensaoComandoImovelPerfil(idComando);
		for (Iterator<Object[]> iterator = colecao.iterator(); iterator.hasNext();) {
			Object[] dados = (Object[]) iterator.next();
			
			ImovelPerfil perfil = new ImovelPerfil();
			if (dados[0] != null && dados[1] != null) {
				if (dados[0] != null) perfil.setId((Integer) dados[0]);
				if (dados[1] != null) perfil.setDescricao(dados[1].toString());
				
				perfis.add(perfil);
			}
		}

		if (perfis != null && !perfis.isEmpty()) {
			sessao.setAttribute("colecaoImovelPerfil", perfis);
		} else {
			sessao.removeAttribute("colecaoImovelPerfil");
		}
	}

	private void pesquisarGerencias(HttpSession sessao, Integer idComando) {
		Collection<GerenciaRegional> gerencias = new ArrayList<GerenciaRegional>();

		Collection<Object[]> colecao = Fachada.getInstancia().pesquisarDadosPopupExtensaoComandoGerenciaRegional(idComando);
		for (Iterator<Object[]> iterator = colecao.iterator(); iterator.hasNext();) {
			Object[] dados = (Object[]) iterator.next();
			
			GerenciaRegional gerencia = new GerenciaRegional();
			if (dados[0] != null && dados[1] != null) {
				if (dados[0] != null) gerencia.setId((Integer) dados[0]);
				if (dados[1] != null) gerencia.setNome(dados[1].toString());

				gerencias.add(gerencia);
			}
		}

		if (gerencias != null && !gerencias.isEmpty()) {
			sessao.setAttribute("colecaoGerenciaRegional", gerencias);
		} else {
			sessao.removeAttribute("colecaoGerenciaRegional");
		}
	}

	private void pesquisarUnidades(HttpSession sessao, Integer idComando) {
		Collection<UnidadeNegocio> unidades = new ArrayList<UnidadeNegocio>();

		Collection<Object[]> colecao = Fachada.getInstancia().pesquisarDadosPopupExtensaoComandoUnidadeNegocio(idComando);
		for (Iterator<Object[]> iterator = colecao.iterator(); iterator.hasNext();) {
			Object[] dados = (Object[]) iterator.next();
			
			UnidadeNegocio unidade = new UnidadeNegocio();
			if (dados[0] != null && dados[1] != null) {
				if (dados[0] != null) unidade.setId((Integer) dados[0]);
				if (dados[1] != null) unidade.setNome(dados[1].toString());
				
				unidades.add(unidade);
			}
		}

		if (unidades != null && !unidades.isEmpty()) {
			sessao.setAttribute("colecaoUnidadeNegocio", unidades);
		} else {
			sessao.removeAttribute("colecaoUnidadeNegocio");
		}
	}
}
