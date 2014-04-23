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

/**
 * [UC0896] Gerar Arquivo Texto das Contas em Cobrança por Empresa
 * 
 * @author Rômulo Aurélio
 * @since 29/10/2008
 */
public class ExibirConsultarContasComandoCobrancaPopupAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarContasComandoCobrancaPopupAction");

		GerarArquivoTextoContasCobrancaEmpresaActionForm form = (GerarArquivoTextoContasCobrancaEmpresaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		String pesquisar = httpServletRequest.getParameter("pesquisa");
		
		boolean Entrou = false;
		
		String idComando = httpServletRequest.getParameter("idComandoEmpresaCobrancaConta");
		
		Date dataIncial = (Date) sessao.getAttribute("dataInicial");
		Date dataFinal =  (Date) sessao.getAttribute("dataFinal");
		
		if(pesquisar!=null && pesquisar.equals("sim") && !Entrou){
	
			
			Object[] dados = fachada.
				pesquisarDadosPopupExtensaoComando(new Integer(idComando),dataIncial,dataFinal);
			
            //Nome empresa
			if (dados[0] != null) {
				form.setNomeEmpresa(dados[0].toString());
			}

			// Data Execucao Comando
			if (dados[1] != null) {
				form.setDataExecucaoComando(Util.formatarData((Date) dados[1]));
			}

			// Periodo Referencia Contas Inicial
			if (dados[2] != null) {
				form.setPeriodoReferenciaContasInicial((Util
						.formatarAnoMesParaMesAno(new Integer(dados[2].toString()).intValue())));
			}

			// Periodo Referencia Contas Final
			if (dados[3] != null) {
				form.setPeriodoReferenciaContasFinal((Util
						.formatarAnoMesParaMesAno(new Integer(dados[3].toString()).intValue())));

			}
			// Periodo Vencimento Contas Inicial
			if (dados[4] != null) {

				form.setPeriodoVencimentoContasInicial(Util
						.formatarData((Date) dados[4]));
			}

			// Periodo Vencimento Contas Final
			if (dados[5] != null) {
				form.setPeriodoVencimentoContasFinal(Util
						.formatarData((Date) dados[5]));
			}
			// Intervalo Valor Contas Minimo

			if (dados[6] != null) {
				form.setIntervaloValorContasInicial(
						Util.formatarMoedaReal(new BigDecimal(dados[6].toString())));
			}

			// Intervalo Valor Contas Maximo

			if (dados[7] != null) {
				form.setIntervaloValorContasFinal(
						Util.formatarMoedaReal(new BigDecimal(dados[7].toString())));
			}
			
			
			Object[] dadosQtdContas = fachada.pesquisarDadosQtdContasEDiasVencidos(new Integer(idComando));
			//Quantidade de Contas
			if(dadosQtdContas[0] != null && dadosQtdContas[1] != null){
				form.setQtdContasInicial(dadosQtdContas[0].toString());
				form.setQtdContasFinal(dadosQtdContas[1].toString());
			}
			//Quantidade de Dias de Vencimento
			if (dadosQtdContas[2] != null) {
				form.setQtdDeDiasVencimento(dadosQtdContas[2].toString());
			}
			//LigacaoAguaSituacao
			if (dadosQtdContas[3] != null && dadosQtdContas[4] != null) {
				form.setIdLigacaoAguaSituacao(dadosQtdContas[3].toString());
				form.setNomeLigacaoAguaSituacao(dadosQtdContas[4].toString());
				sessao.removeAttribute("colecaoLigacaoAguaSituacao");
			} else {
				Collection<CmdEmpresaCobrancaContaLigacaoAguaSituacao> colecaoLigacaoAguaSituacao = fachada.pesquisarColecaoLigacaoAguaSituacaoPorComandoEmpresaCobrancaConta(new Integer(idComando));
				
				if (colecaoLigacaoAguaSituacao != null && !colecaoLigacaoAguaSituacao.isEmpty()) {
					sessao.setAttribute("colecaoLigacaoAguaSituacao", colecaoLigacaoAguaSituacao);
				} else {
					sessao.removeAttribute("colecaoLigacaoAguaSituacao");
				}
			}
			
			// Imóvel
			if (dados[8] != null) {
				form.setIdImovel(dados[8].toString());
			}
			// Dados Cliente
			if (dados[9] != null) {
				form.setIdCliente(dados[9].toString());
			}
			// UNidade de Negocio
			if (dados[10] != null) {
				form.setIdUnidadeNegocio(dados[10].toString());
				sessao.removeAttribute("colecaoUnidadeNegocio");
			} else {
				Collection<UnidadeNegocio> colecaoUnidadeNegocio = this.pesquisarColecaoUnidadeNegocio(new Integer(idComando));
				
				if (colecaoUnidadeNegocio != null && !colecaoUnidadeNegocio.isEmpty()) {
					sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
				} else {
					sessao.removeAttribute("colecaoUnidadeNegocio");
				}
			}
			if (dados[11] != null) {
				form.setNomeUnidadeNegocio(dados[11].toString());
			}

			// Intervalo de localizacao
			if (dados[12] != null) {
				form.setIntervaloLocalizacaoInicial(dados[12].toString());
			}
			if (dados[13] != null) {
				form.setIntervaloLocalizacaoFinal(dados[13].toString());
			}
			// Intervalo de Setor Comercial
			if (dados[14] != null) {
				form.setIntervaloSetorComercialInicial(dados[14].toString());
			}
			if (dados[15] != null) {
				form.setIntervaloSetorComercialFinal(dados[15].toString());
			}
			
			// Perfil do Imóvel
			if (dados[16] != null) {
				form.setIdImovelPerfil(dados[16].toString());
				sessao.removeAttribute("colecaoImovelPerfil");
			} else {
				Collection<ImovelPerfil> colecaoImovelPerfil = this.pesquisarColecaoImovelPerfil(new Integer(idComando));
				
				if (colecaoImovelPerfil != null && !colecaoImovelPerfil.isEmpty()) {
					sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
				} else {
					sessao.removeAttribute("colecaoImovelPerfil");
				}
			}
			if (dados[17] != null) {
				form.setDsImovelPerfil(dados[17].toString());
			}
			
			// Gerência Regional
			if (dados[18] != null) {
				form.setIdGerenciaRegional(dados[18].toString());
				sessao.removeAttribute("colecaoGerenciaRegional");
			} else {
				Collection<GerenciaRegional> colecaoGerenciaRegional = this.pesquisarColecaoGerenciaRegional(new Integer(idComando));
				
				if (colecaoGerenciaRegional != null && !colecaoGerenciaRegional.isEmpty()) {
					sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
				} else {
					sessao.removeAttribute("colecaoGerenciaRegional");
				}
			}
			if (dados[19] != null) {
				form.setNomeGerenciaRegional(dados[19].toString());
			}
			
			// Intervalo de Quadra
			if (dados[20] != null && !dados[20].equals("") && ((Integer)dados[20]).compareTo(0) != 0) {
				form.setIntervaloQuadraInicial(dados[20].toString());
			}
			if (dados[21] != null && !dados[21].equals("") && ((Integer)dados[21]).compareTo(0) != 0) {
				form.setIntervaloQuadraFinal(dados[21].toString());
			}
			
			// Quantidade total de contas selecionadas para cobranca

			if (dados[22] != null) {
				form.setQtdeTotalContasCobranca(dados[22].toString());
			}
			// Valor Total de contas selecionadas para cobranca
			if (dados[23] != null) {
				form.setValorTotalContasCobranca(Util
						.formatarMoedaReal(new BigDecimal(dados[23].toString())));
			}
			// Quantidade total de contas selecionadas para Criterios

			if (dados[24] != null) {
				form.setQtdeContasCriterioComando(dados[24].toString());
			}
			// Valor Total de contas selecionadas para Criterios COmando
			if (dados[25] != null) {
				form.setValorContasCriterioComando(Util.formatarMoedaReal(new BigDecimal(dados[25].toString())));
			}
		
		Entrou = true;
		}
		if(pesquisar!=null && pesquisar.equals("nao")){
			httpServletRequest.setAttribute("pesquisa" ,"nao");
			httpServletRequest.setAttribute("idComandoEmpresaCobrancaConta",idComando);
		}
		return retorno;

	}
	
	private Collection<ImovelPerfil> pesquisarColecaoImovelPerfil(Integer idComando) {
		
		Collection<ImovelPerfil> retorno = new ArrayList();
		
		Collection<Object[]> colecaoDados = Fachada.getInstancia().pesquisarDadosPopupExtensaoComandoImovelPerfil(idComando);
		
		if (colecaoDados != null && !colecaoDados.isEmpty()) {
			Iterator iterator = colecaoDados.iterator();
			
			while(iterator.hasNext()) {
				Object[] dados = (Object[]) iterator.next();
				
				ImovelPerfil imovelPerfil = new ImovelPerfil();
				if (dados[0] != null && dados[1] != null) {
					if (dados[0] != null) {
						imovelPerfil.setId((Integer) dados[0]);
					}
					if (dados[1] != null) {
						imovelPerfil.setDescricao(dados[1].toString());
					}
					retorno.add(imovelPerfil);
				}
				
			}
			
		}
		
		return retorno;
	}
	
	private Collection<GerenciaRegional> pesquisarColecaoGerenciaRegional(Integer idComando) {
		
		Collection<GerenciaRegional> retorno = new ArrayList();
		
		Collection<Object[]> colecaoDados = Fachada.getInstancia().pesquisarDadosPopupExtensaoComandoGerenciaRegional(idComando);
		
		if (colecaoDados != null && !colecaoDados.isEmpty()) {
			Iterator iterator = colecaoDados.iterator();
			
			while(iterator.hasNext()) {
				Object[] dados = (Object[]) iterator.next();
				
				GerenciaRegional gerenciaRegional = new GerenciaRegional();
				if (dados[0] != null && dados[1] != null) {
					if (dados[0] != null) {
						gerenciaRegional.setId((Integer) dados[0]);
					}
					if (dados[1] != null) {
						gerenciaRegional.setNome(dados[1].toString());
					}
					retorno.add(gerenciaRegional);
				}
				
			}
			
		}
		
		return retorno;
	}
	
	private Collection<UnidadeNegocio> pesquisarColecaoUnidadeNegocio(Integer idComando) {
		
		Collection<UnidadeNegocio> retorno = new ArrayList();
		
		Collection<Object[]> colecaoDados = Fachada.getInstancia().pesquisarDadosPopupExtensaoComandoUnidadeNegocio(idComando);
		
		if (colecaoDados != null && !colecaoDados.isEmpty()) {
			Iterator iterator = colecaoDados.iterator();
			
			while(iterator.hasNext()) {
				Object[] dados = (Object[]) iterator.next();
				
				UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
				if (dados[0] != null && dados[1] != null) {
					if (dados[0] != null) {
						unidadeNegocio.setId((Integer) dados[0]);
					}
					if (dados[1] != null) {
						unidadeNegocio.setNome(dados[1].toString());
					}
					retorno.add(unidadeNegocio);
				}
				
			}
			
		}
		
		return retorno;
	}
}
