package gcom.gui.cobranca.cobrancaporresultado;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.FiltroEmpresaCobrancaFaixa;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1167] Consultar Comandos de Cobrança por Empresa
 * 
 * @author Mariana Victor
 * @since 05/05/2011
 */
public class ExibirConsultarComandosContasCobrancaEmpresaPopupAction extends GcomAction {

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
				.findForward("exibirConsultarComandosContasCobrancaEmpresaPopupAction");

		ConsultarComandosContasCobrancaEmpresaActionForm form = (ConsultarComandosContasCobrancaEmpresaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		String pesquisar = httpServletRequest.getParameter("pesquisa");
		
		boolean Entrou = false;
		
		String idComando = httpServletRequest.getParameter("idComandoEmpresaCobrancaConta");
		
		Date dataIncial = (Date) sessao.getAttribute("dataInicial");
		Date dataFinal =  (Date) sessao.getAttribute("dataFinal");
		
		if(pesquisar!=null && pesquisar.equals("sim") && !Entrou){
	
			
			Object[] dados = fachada.
				pesquisarDadosPopupExtensaoComandoCobranca(new Integer(idComando),dataIncial,dataFinal);
			
            //Nome empresa
			if (dados[0] != null) {
				form.setNomeEmpresa(dados[0].toString());
			} else {
				form.setNomeEmpresa("");
			}
			

			// Data Início Ciclo
			if (dados[1] != null) {
				form.setPeriodoCicloInicial(Util.formatarData((Date) dados[1]));
			} else {
				form.setPeriodoCicloInicial("");
			}

			// Data Fim Ciclo
			if (dados[2] != null) {
				form.setPeriodoCicloFinal(Util.formatarData((Date) dados[2]));
			} else {
				form.setPeriodoCicloFinal("");
			}

			// Data Execucao Comando
			if (dados[3] != null) {
				form.setDataExecucaoComando(Util.formatarData((Date) dados[3]));
			} else {
				form.setDataExecucaoComando("");
			}

			// Data Encerramento Comando
			if (dados[4] != null) {
				form.setDataEncerramento(Util.formatarData((Date) dados[4]));
			} else {
				form.setDataEncerramento("");
			}
			
			// Id Imóvel / Inscrição do Imóvel
			if (dados[5] != null) {
				form.setIdImovel(dados[5].toString());
				
				String inscricao = fachada.pesquisarInscricaoImovel((Integer) dados[5]);
				if (inscricao != null && !inscricao.equals("")) {
					form.setInscricaoImovel(inscricao);
				}
			} else {
				form.setIdImovel("");
				form.setInscricaoImovel("");
			}
			
			// Id Cliente
			if (dados[6] != null) {
				form.setIdCliente(dados[6].toString());
			} else {
				form.setIdCliente("");
			}
			
			// Nome do Cliente
			if (dados[7] != null) {
				form.setNomeCliente(dados[7].toString());
			} else {
				form.setNomeCliente("");
			}
			
			// Categoria
			Collection<String> colecaoCategoria = new ArrayList();
			if (dados[8] != null && ((Short)dados[8]).equals(ConstantesSistema.SIM)) {
				colecaoCategoria.add("Residencial");
			}
			if (dados[9] != null && ((Short)dados[9]).equals(ConstantesSistema.SIM)) {
				colecaoCategoria.add("Comercial");
			}
			if (dados[10] != null && ((Short)dados[10]).equals(ConstantesSistema.SIM)) {
				colecaoCategoria.add("Industrial");
			}
			if (dados[11] != null && ((Short)dados[11]).equals(ConstantesSistema.SIM)) {
				colecaoCategoria.add("Público");
			}
			if (colecaoCategoria != null && !colecaoCategoria.isEmpty()) {
				if (colecaoCategoria.size() > 1) {
					sessao.setAttribute("colecaoCategoria", colecaoCategoria);
				} else {
					form.setCategoria(colecaoCategoria.iterator().next());
					sessao.removeAttribute("colecaoCategoria");
				}
			} else {
				sessao.removeAttribute("colecaoCategoria");
			}
			
			// Perfil do Imóvel
			if (dados[12] != null) {
				form.setIdImovelPerfil(dados[12].toString());
				sessao.removeAttribute("colecaoImovelPerfil");
			} else {
				form.setIdImovelPerfil("");
				
				Collection<ImovelPerfil> colecaoImovelPerfil = this.pesquisarColecaoImovelPerfil(new Integer(idComando));
				
				if (colecaoImovelPerfil != null && !colecaoImovelPerfil.isEmpty()) {
					sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
				} else {
					sessao.removeAttribute("colecaoImovelPerfil");
				}
			}
			
			if (dados[13] != null) {
				form.setDsImovelPerfil(dados[13].toString());
			} else {
				form.setDsImovelPerfil("");
			}
			
			// Gerência Regional
			if (dados[14] != null) {
				form.setIdGerenciaRegional(dados[14].toString());
				sessao.removeAttribute("colecaoGerenciaRegional");
			} else {
				form.setIdGerenciaRegional("");
				
				Collection<GerenciaRegional> colecaoGerenciaRegional = this.pesquisarColecaoGerenciaRegional(new Integer(idComando));
				
				if (colecaoGerenciaRegional != null && !colecaoGerenciaRegional.isEmpty()) {
					sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
				} else {
					sessao.removeAttribute("colecaoGerenciaRegional");
				}
			}
			
			if (dados[15] != null) {
				form.setNomeGerenciaRegional(dados[15].toString());
			} else {
				form.setNomeGerenciaRegional("");
			}
			
			// Unidade de Negocio
			if (dados[16] != null) {
				form.setIdUnidadeNegocio(dados[16].toString());
				sessao.removeAttribute("colecaoUnidadeNegocio");
			} else {
				form.setIdUnidadeNegocio("");
				
				Collection<UnidadeNegocio> colecaoUnidadeNegocio = this.pesquisarColecaoUnidadeNegocio(new Integer(idComando));
				
				if (colecaoUnidadeNegocio != null && !colecaoUnidadeNegocio.isEmpty()) {
					sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
				} else {
					sessao.removeAttribute("colecaoUnidadeNegocio");
				}
			}
			if (dados[17] != null) {
				form.setNomeUnidadeNegocio(dados[17].toString());
			} else {
				form.setNomeUnidadeNegocio("");
			}

			// Intervalo de localizacao
			if (dados[18] != null) {
				form.setIntervaloLocalizacaoInicial(dados[18].toString());
			} else {
				form.setIntervaloLocalizacaoInicial("");
			}
			if (dados[19] != null) {
				form.setIntervaloLocalizacaoFinal(dados[19].toString());
			} else {
				form.setIntervaloLocalizacaoFinal("");
			}
			// Intervalo de Setor Comercial
			if (dados[20] != null) {
				form.setIntervaloSetorComercialInicial(dados[20].toString());
			} else {
				form.setIntervaloSetorComercialInicial("");
			}
			if (dados[21] != null) {
				form.setIntervaloSetorComercialFinal(dados[21].toString());
			} else {
				form.setIntervaloSetorComercialFinal("");
			}
			
			// Intervalo de Quadra
			if (dados[22] != null && !dados[22].equals("") && ((Integer)dados[22]).compareTo(0) != 0) {
				form.setIntervaloQuadraInicial(dados[22].toString());
			} else {
				form.setIntervaloQuadraInicial("");
			}
			if (dados[23] != null && !dados[23].equals("") && ((Integer)dados[23]).compareTo(0) != 0) {
				form.setIntervaloQuadraFinal(dados[23].toString());
			} else {
				form.setIntervaloQuadraFinal("");
			}
			
			// Periodo Referencia Contas Inicial
			if (dados[24] != null) {
				form.setPeriodoReferenciaContasInicial((Util
						.formatarAnoMesParaMesAno(new Integer(dados[24].toString()).intValue())));
			} else {
				form.setPeriodoReferenciaContasInicial("");
			}

			// Periodo Referencia Contas Final
			if (dados[25] != null) {
				form.setPeriodoReferenciaContasFinal((Util
						.formatarAnoMesParaMesAno(new Integer(dados[25].toString()).intValue())));
			} else {
				form.setPeriodoReferenciaContasFinal("");
			}
			// Periodo Vencimento Contas Inicial
			if (dados[26] != null) {
				form.setPeriodoVencimentoContasInicial(Util
						.formatarData((Date) dados[26]));
			} else {
				form.setPeriodoVencimentoContasInicial("");
			}

			// Periodo Vencimento Contas Final
			if (dados[27] != null) {
				form.setPeriodoVencimentoContasFinal(Util
						.formatarData((Date) dados[27]));
			} else {
				form.setPeriodoVencimentoContasFinal("");
			}
			// Intervalo Valor Contas Minimo

			if (dados[28] != null) {
				form.setIntervaloValorContasInicial(
						Util.formatarMoedaReal(new BigDecimal(dados[28].toString())));
			} else {
				form.setIntervaloValorContasInicial("");
			}

			// Intervalo Valor Contas Maximo

			if (dados[29] != null) {
				form.setIntervaloValorContasFinal(
						Util.formatarMoedaReal(new BigDecimal(dados[29].toString())));
			} else {
				form.setIntervaloValorContasFinal("");
			}

			// Arquivo txt gerado
			if (dados[32] == null || ((Integer)dados[32]).equals(new Integer(2))) {
				form.setArquivoTxtGerado("NÃO");
			} else {
				form.setArquivoTxtGerado("SIM");
			}
			
			//Quantidade de Contas Inicial
			if (dados[33] != null) {
				form.setQtdContasInicial(((Integer)dados[33]).toString());
			} else {
				form.setQtdContasInicial("");
			}
			
			//Quantidade de Contas Final
			if (dados[34] != null) {
				form.setQtdContasFinal(((Integer)dados[34]).toString());
			} else {
				form.setQtdContasFinal("");
			}
			
			//Quantidade de Dias de vencimento
			if (dados[35] != null) {
				form.setQtdDiasVencimento(((Integer)dados[35]).toString());
			} else {
				form.setQtdDiasVencimento("");
			}
			
			
			// Situação da ligação de água
				if (dados[36] != null) {
					form.setSituacaoAgua(dados[36].toString());
					sessao.removeAttribute("colecaoSituacaoAgua");
				} else {
					form.setSituacaoAgua("");
					
					Collection<LigacaoAguaSituacao> colecaoSituacaoAgua = this.pesquisarColecaoSituacaoAgua(new Integer(idComando));
					
					if (colecaoSituacaoAgua != null && !colecaoSituacaoAgua.isEmpty()) {
						sessao.setAttribute("colecaoSituacaoAgua", colecaoSituacaoAgua);
					} else {
						form.setSituacaoAgua("TODAS");
						sessao.removeAttribute("colecaoSituacaoAgua");
					}
				}
			
			
			
			if (dados[31] != null && !dados[31].equals("")) {
				sessao.setAttribute("percentualInformado", true);
				// Quantidade total de contas selecionadas para cobranca
				if (dados[37] != null) {
					form.setQtdeTotalContasCobranca(dados[37].toString());
				} else {
					form.setQtdeTotalContasCobranca("");
				}
				// Valor Total de contas selecionadas para cobranca
				if (dados[38] != null) {
					form.setValorTotalContasCobranca(Util
							.formatarMoedaReal(new BigDecimal(dados[38].toString())));
				} else {
					form.setValorTotalContasCobranca("");
				}
				
				
			} else {
				Collection<Object[]> colecaoDados = this.getFachada().pesquisarValorTotalCobrancaComandoEmpresaPorImovel(new Integer(idComando));
				if (colecaoDados != null && !colecaoDados.isEmpty()) {
					Collection<String> colecaoFaixa = new ArrayList();
					Collection<Integer> colecaoQtdeContas = new ArrayList();
					Collection<BigDecimal> colecaoValorTotalDivida= new ArrayList();

					FiltroEmpresaCobrancaFaixa filtroEmpresaCobrancaFaixa = new FiltroEmpresaCobrancaFaixa();
					filtroEmpresaCobrancaFaixa.adicionarParametro(new ParametroSimples(
							FiltroEmpresaCobrancaFaixa.EMPRESA_CONTRATO_COBRANCA_ID, dados[30]));
					filtroEmpresaCobrancaFaixa.setCampoOrderBy(FiltroEmpresaCobrancaFaixa.NUMERO_MAXIMO_CONTAS_FAIXA);
					
					List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa = (List<EmpresaCobrancaFaixa>)Fachada.getInstancia().pesquisar(
							filtroEmpresaCobrancaFaixa, EmpresaCobrancaFaixa.class.getName());
					
					if (colecaoEmpresaCobrancaFaixa != null && !colecaoEmpresaCobrancaFaixa.isEmpty()) {

						EmpresaCobrancaFaixa empresaCobrancaFaixa = (EmpresaCobrancaFaixa) colecaoEmpresaCobrancaFaixa.get(0);
						Integer numeroMinimoContas = null;
						Integer numeroMaximoContas = empresaCobrancaFaixa.getNumeroMinimoContasFaixa() - 1;
						
						Integer qtdeContas = 0;
						BigDecimal valorTotalDivida = new BigDecimal(0.0);
						Iterator iteratorColecaoDados = colecaoDados.iterator();
						
						for (int i = 0; i < colecaoEmpresaCobrancaFaixa.size(); i++) {
							
							empresaCobrancaFaixa = (EmpresaCobrancaFaixa) colecaoEmpresaCobrancaFaixa.get(i);

							numeroMinimoContas = empresaCobrancaFaixa.getNumeroMinimoContasFaixa();
							
							numeroMaximoContas = null;
							
							if (i < (colecaoEmpresaCobrancaFaixa.size() - 1)) {
								numeroMaximoContas = ((EmpresaCobrancaFaixa) colecaoEmpresaCobrancaFaixa.get(i + 1)).getNumeroMinimoContasFaixa() - 1;
							}
							
							qtdeContas = 0;
							
							valorTotalDivida = new BigDecimal(0.0);
							
							iteratorColecaoDados = colecaoDados.iterator();
							
							while (iteratorColecaoDados.hasNext()) {
								Object[] dadosRetornados = (Object[]) iteratorColecaoDados.next();
								
								if (dadosRetornados[0] != null){
									Integer qnt = (Integer) dadosRetornados[0];
									
									if (qnt >= numeroMinimoContas
										&& (numeroMaximoContas == null || qnt <= numeroMaximoContas)) {
									
										qtdeContas += qnt;
										
										if(dadosRetornados[1] !=null ){
											valorTotalDivida = valorTotalDivida.add((BigDecimal) dadosRetornados[1]);
										}
									}
								}
								
							}
							
							if (i < (colecaoEmpresaCobrancaFaixa.size() - 1)) {
								colecaoFaixa.add(numeroMinimoContas + " a " + numeroMaximoContas);
							} else {
								colecaoFaixa.add("Mais de " + numeroMinimoContas);
							}
							colecaoQtdeContas.add(qtdeContas);
							colecaoValorTotalDivida.add(valorTotalDivida);
							
						}

						
						if (!colecaoQtdeContas.isEmpty()
								&& !colecaoValorTotalDivida.isEmpty()) {
							
							sessao.setAttribute("colecaoQuantidadeContas", true);
							sessao.setAttribute("tamanho", colecaoFaixa.size());
							sessao.setAttribute("colecaoFaixa", colecaoFaixa);
							sessao.setAttribute("colecaoQtdeContas", colecaoQtdeContas);
							sessao.setAttribute("colecaoValorTotalDivida", colecaoValorTotalDivida);

							sessao.removeAttribute("percentualInformado");
						} else {
							
							form.setQtdeTotalContasCobranca("0");
							form.setValorTotalContasCobranca(Util.formatarMoedaReal(BigDecimal.ZERO));

							sessao.removeAttribute("colecaoQuantidadeContas");
							sessao.removeAttribute("colecaoFaixa");
							sessao.removeAttribute("colecaoQtdeContas");
							sessao.removeAttribute("colecaoValorTotalDivida");

							sessao.setAttribute("percentualInformado", true);
						}
						
					} else {
						form.setQtdeTotalContasCobranca("0");
						form.setValorTotalContasCobranca(Util.formatarMoedaReal(BigDecimal.ZERO));

						sessao.removeAttribute("colecaoQuantidadeContas");
						sessao.removeAttribute("colecaoFaixa");
						sessao.removeAttribute("colecaoQtdeContas");
						sessao.removeAttribute("colecaoValorTotalDivida");

						sessao.setAttribute("percentualInformado", true);
					}
				} else {
					form.setQtdeTotalContasCobranca("0");
					form.setValorTotalContasCobranca(Util.formatarMoedaReal(BigDecimal.ZERO));

					this.getSessao(httpServletRequest).removeAttribute("colecaoQuantidadeContas");
					this.getSessao(httpServletRequest).removeAttribute("colecaoFaixa");
					this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeContas");
					this.getSessao(httpServletRequest).removeAttribute("colecaoValorTotalDivida");

					sessao.setAttribute("percentualInformado", true);
				}
			}
			
			Entrou = true;
		}
		
		if(pesquisar!=null && pesquisar.equals("nao")){
			httpServletRequest.setAttribute("pesquisa" ,"nao");
			httpServletRequest.setAttribute("idComandoEmpresaCobrancaConta",idComando);
		}
		return retorno;

	}
	
	
	
	private Collection<LigacaoAguaSituacao> pesquisarColecaoSituacaoAgua(
			Integer idComando) {
		
		Collection<LigacaoAguaSituacao> retorno = new ArrayList();
		
		Collection<Object[]> colecaoDados = Fachada.getInstancia().pesquisarDadosPopupExtensaoComandoAguaSituacao(idComando);
		
		if (colecaoDados != null && !colecaoDados.isEmpty()) {
			Iterator iterator = colecaoDados.iterator();
			
			while(iterator.hasNext()) {
				Object[] dados = (Object[]) iterator.next();
				
				LigacaoAguaSituacao situacaoAgua = new LigacaoAguaSituacao();
				if (dados[0] != null && dados[1] != null) {
					if (dados[0] != null) {
						situacaoAgua.setId((Integer) dados[0]);
					}
					if (dados[1] != null) {
						situacaoAgua.setDescricao(dados[1].toString());
					}
					retorno.add(situacaoAgua);
				}
				
			}
			
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
