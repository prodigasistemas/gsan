package gcom.relatorio.cobranca.spcserasa;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.batch.Relatorio;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroNegativadorExclusaoMotivo;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.cobranca.spcserasa.RelatorioNegativacoesExcluidasSomatorioDadosParcelamentoHelper;
import gcom.relatorio.ConstantesExecucaoRelatorios;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Yara Taciane
 * @created 18 de março de 2008
 * @version 1.0
 */

public class RelatorioNegativacoesExcluidas extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the RelatorioNegativacoesExcluidas object
	 */
	public RelatorioNegativacoesExcluidas(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_NEGATIVACOES_EXCLUIDAS);
	}

	@Deprecated
	public RelatorioNegativacoesExcluidas() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param Negativador
	 *            Parametros Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		// Recebe os parâmetros que serão utilizados no relatório
		DadosConsultaNegativacaoHelper parametrosHelper = (DadosConsultaNegativacaoHelper) getParametro("parametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório

		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioNegativacoesExcluidasBean relatorioBean = null;

		// Nova consulta para trazer objeto completo
		Collection colecaoNovos = fachada
				.pesquisarRelatorioNegativacoesExcluidas(parametrosHelper);

		if (colecaoNovos != null && !colecaoNovos.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator

			Iterator it = colecaoNovos.iterator();
			while (it.hasNext()) {

				NegativadorMovimentoReg negativadorMovimentoReg = (NegativadorMovimentoReg) it.next();

				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório

				String idNegativador = "";
				String negativador = "";
				if (negativadorMovimentoReg.getNegativadorMovimento() != null && negativadorMovimentoReg.getNegativadorMovimento().getNegativador() != null) {

					Negativador negativ = negativadorMovimentoReg.getNegativadorMovimento().getNegativador();

					idNegativador = negativ.getId().toString();

					if (negativ.getCliente() != null) {
						negativador = negativ.getCliente().getNome();
					}

				}

				// cliente nome
				String nomeCliente = "";
				if (negativadorMovimentoReg.getCliente() != null) {
					nomeCliente = negativadorMovimentoReg.getCliente().getNome();
				}

				// data de processamento
				String periodoEnvioNegativacao = "";
				if (negativadorMovimentoReg != null && negativadorMovimentoReg.getNegativadorMovimento() != null) {
					periodoEnvioNegativacao = Util.formatarData(negativadorMovimentoReg.getNegativadorMovimento().getDataProcessamentoEnvio());
				}

				// localidade
				String localidade = "";
				String idLocalidade = "";
				if (negativadorMovimentoReg.getLocalidade() != null) {
					idLocalidade = negativadorMovimentoReg.getLocalidade().getId().toString();
					localidade = negativadorMovimentoReg.getLocalidade().getDescricao();
				}

				// matricula do Imovel
				String matricula = "";
				if (negativadorMovimentoReg.getImovel() != null) {
					matricula = negativadorMovimentoReg.getImovel().getId().toString();
				}

				// cpf ou cnpj
				String cpfCnpj = "";
				if (negativadorMovimentoReg.getNumeroCnpj() != null) {
					cpfCnpj = negativadorMovimentoReg.getNumeroCnpj();
				} else if (negativadorMovimentoReg.getNumeroCpf() != null) {
					cpfCnpj = negativadorMovimentoReg.getNumeroCpf();
				}

				// valor negativado
				BigDecimal valorNegativado = new BigDecimal(0);
				if (negativadorMovimentoReg.getValorDebito() != null) {
					valorNegativado = negativadorMovimentoReg.getValorDebito();
				}

				// motivo da Exclusão
				String motivoExclusao = null;
				if (negativadorMovimentoReg.getNegativadorExclusaoMotivo() != null) {
					motivoExclusao = negativadorMovimentoReg.getNegativadorExclusaoMotivo().getDescricaoExclusaoMotivo();
				}

				// CRC996
				BigDecimal valorParcelado = new BigDecimal(0);
				BigDecimal valorParceladoEntrada = new BigDecimal(0);
				BigDecimal valorPago = new BigDecimal(0);
				BigDecimal valorParceladoPago = new BigDecimal(0);
				BigDecimal valorParceladoEntradaPago = new BigDecimal(0);
				
				if (negativadorMovimentoReg.getNegativadorExclusaoMotivo() != null 
						&& negativadorMovimentoReg.getNegativadorExclusaoMotivo().getCobrancaDebitoSituacao() != null) {
					
					Integer idCobrancaDebitoSituacao = negativadorMovimentoReg.getNegativadorExclusaoMotivo().getCobrancaDebitoSituacao().getId();

					if (idCobrancaDebitoSituacao.equals(CobrancaDebitoSituacao.PARCELADO)) {

//						valorParcelado = fachada.pesquisarSomatorioNegativadorMovimentoRegItens(negativadorMovimentoReg.getId(), CobrancaDebitoSituacao.PARCELADO);
//
//						if(valorParcelado == null){
//							valorParcelado = new BigDecimal(0);
//						}	
//						
//						valorParceladoEntrada = negativadorMovimentoReg.getValorParceladoEntrada();
//
//						if (valorParceladoEntrada != null) {
//							valorParcelado = valorParcelado.subtract(valorParceladoEntrada);
//						} else {
//							valorParceladoEntrada = new BigDecimal(0);
//
//						}
						
						//Vivianne Sousa - 28/04/2009 - CRC1599
						RelatorioNegativacoesExcluidasSomatorioDadosParcelamentoHelper helper = 
							fachada.pesquisarNegativadorMovimentoRegParcelamento(negativadorMovimentoReg.getId());
						
						valorParcelado = helper.getValorParcelado();
						valorParceladoPago = helper.getValorParceladoPago();
						valorParceladoEntrada = helper.getValorParceladoEntrada();
						valorParceladoEntradaPago = helper.getValorParceladoEntradaPago();

					}

					if (idCobrancaDebitoSituacao.equals(CobrancaDebitoSituacao.PAGO)) {

						valorPago = fachada.pesquisarSomatorioNegativadorMovimentoRegItens(negativadorMovimentoReg.getId(), CobrancaDebitoSituacao.PAGO);
						
						if(valorPago == null){
							valorPago = new BigDecimal(0);
						}

					}

				}

				// data Situação do Debito
				String dtSitDebito = null;
				if (negativadorMovimentoReg.getDataSituacaoDebito() != null) {
					dtSitDebito = Util.formatarData(negativadorMovimentoReg.getDataSituacaoDebito());
				}

				String dataExclusao = null;
				if (negativadorMovimentoReg.getImovel() != null) {

					Date dataExcl = fachada.pesquisarDataExclusaoNegativacao(negativadorMovimentoReg.getImovel().getId(),
							negativadorMovimentoReg.getNegativadorMovimento().getNegativacaoComando().getId());
					if (dataExcl != null) {
						dataExclusao = Util.formatarData(dataExcl);
					}

				}

				// Inicializa o construtor constituído dos campos
				// necessários para a impressão do relatorio
				relatorioBean = new RelatorioNegativacoesExcluidasBean(nomeCliente, matricula, cpfCnpj, valorNegativado, motivoExclusao, periodoEnvioNegativacao, 
						localidade,	idLocalidade, idNegativador, negativador, dtSitDebito, dataExclusao, valorParceladoEntrada, valorParcelado,	valorPago,
						 valorParceladoPago, valorParceladoEntradaPago);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}

		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		//*************************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 13/01/2011
		//*************************************************************
		if (parametrosHelper.getColecaoNegativador() != null && !parametrosHelper.getColecaoNegativador().isEmpty()) {
			FiltroNegativador filtroNegativador = new FiltroNegativador();
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroNegativador.adicionarParametro(new ParametroSimplesIn(FiltroNegativador.ID, parametrosHelper.getColecaoNegativador()));

			Collection collNegativador = fachada.pesquisar(filtroNegativador,Negativador.class.getName());
			Iterator itt = collNegativador.iterator();
			boolean primeiro = true;
			String negativadorSelecionado = "";
			while (itt.hasNext()) {
				Negativador negativador = (Negativador) itt.next();
				if (negativador.getCliente() != null) {
					//parametros.put("negativador", negativador.getCliente().getNome());
					negativadorSelecionado = negativadorSelecionado + negativador.getCliente().getNome();
					
					if (collNegativador.size() > 1 && primeiro) {
						negativadorSelecionado = negativadorSelecionado + " / ";
						primeiro = false;
					}
				}
			}
			parametros.put("negativador", negativadorSelecionado);
		} else {
			parametros.put("negativador", "");
		}

		if (parametrosHelper.getIdNegativadorExclusaoMotivo() != null) {
			FiltroNegativadorExclusaoMotivo filtroNegativadorExclusaoMotivo = new FiltroNegativadorExclusaoMotivo();
			filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.ID,	parametrosHelper.getIdNegativadorExclusaoMotivo()));

			Collection collNegativadorExclusaoMotivo = fachada.pesquisar(filtroNegativadorExclusaoMotivo,NegativadorExclusaoMotivo.class.getName());
			Iterator itt = collNegativadorExclusaoMotivo.iterator();
			while (itt.hasNext()) {
				NegativadorExclusaoMotivo negativadorExclusaoMotivo = (NegativadorExclusaoMotivo) itt.next();
				if (negativadorExclusaoMotivo != null) {
					parametros.put("negativadorExclusaoMotivo",	negativadorExclusaoMotivo.getDescricaoExclusaoMotivo());
				}
				break;
			}

		} else {
			parametros.put("negativadorExclusaoMotivo", "");
		}

		if (parametrosHelper.getPeriodoEnvioNegativacaoInicio() != null) {
			parametros.put("periodoEnvioNegativacao", Util.formatarData(parametrosHelper.getPeriodoEnvioNegativacaoInicio())
					+ " à "	+ Util.formatarData(parametrosHelper.getPeriodoEnvioNegativacaoFim()));
		} else {
			parametros.put("periodoEnvioNegativacao", "");
		}

		if (parametrosHelper.getPeriodoExclusaoNegativacaoInicio() != null) {
			parametros.put("periodoExclusaoNegativacao", Util.formatarData(parametrosHelper.getPeriodoExclusaoNegativacaoInicio())
					+ " à "	+ Util.formatarData(parametrosHelper.getPeriodoExclusaoNegativacaoFim()));
		} else {
			parametros.put("periodoExclusaoNegativacao", "");
		}

		if (parametrosHelper.getTituloComando() != null) {
			parametros.put("tituloComando", parametrosHelper.getTituloComando().toString());
		} else {
			parametros.put("tituloComando", "");
		}

		if (parametrosHelper.getIdEloPolo() != null) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_ELO, parametrosHelper.getIdEloPolo()));

			Collection collLocalidade = fachada.pesquisar(filtroLocalidade,	Localidade.class.getName());
			Iterator itt = collLocalidade.iterator();
			while (itt.hasNext()) {
				Localidade localidade = (Localidade) itt.next();
				if (localidade != null) {
					parametros.put("eloPolo", localidade.getDescricao());
				}
				break;
			}
		} else {
			parametros.put("eloPolo", "");
		}

		if (parametrosHelper.getIdLocalidade() != null) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, parametrosHelper.getIdLocalidade()));

			Collection collLocalidade = fachada.pesquisar(filtroLocalidade,	Localidade.class.getName());
			Iterator itt = collLocalidade.iterator();
			while (itt.hasNext()) {
				Localidade localidade = (Localidade) itt.next();
				if (localidade != null) {
					parametros.put("localidade", localidade.getDescricao());
				}
				break;
			}

		} else {
			parametros.put("localidade", "");
		}

		if (parametrosHelper.getIdSetorComercial() != null) {
			parametros.put("codigoSetorComercial", parametrosHelper.getIdSetorComercial().toString());
		} else {
			parametros.put("codigoSetorComercial", "");
		}

		if (parametrosHelper.getIdQuadra() != null) {
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, parametrosHelper.getIdQuadra()));

			Collection collQuadra = fachada.pesquisar(filtroQuadra,	Quadra.class.getName());
			Iterator itt = collQuadra.iterator();
			while (itt.hasNext()) {
				Quadra quadra = (Quadra) itt.next();
				if (quadra != null) {
					parametros.put("numeroQuadra", quadra.getNumeroQuadra());
				}
				break;
			}
		} else {
			parametros.put("numeroQuadra", "");
		}

		if (parametrosHelper.getColecaoCobrancaGrupo() != null) {
			String gruposCobranca = "";

			Iterator itt = parametrosHelper.getColecaoCobrancaGrupo().iterator();
			boolean primeiro = true;
			while (itt.hasNext()) {
				CobrancaGrupo cobrancaGrupo = (CobrancaGrupo) itt.next();
				if (cobrancaGrupo != null) {
					if (primeiro) {
						gruposCobranca = gruposCobranca	+ cobrancaGrupo.getDescricao();
						primeiro = false;
					} else {
						gruposCobranca = gruposCobranca + ", " + cobrancaGrupo.getDescricao();
					}
				}
			}

			parametros.put("grupoCobranca", gruposCobranca);

		} else {
			parametros.put("grupoCobranca", "");
		}

		if (parametrosHelper.getColecaoGerenciaRegional() != null) {
			String gerenciasRegionais = "";

			Iterator itt = parametrosHelper.getColecaoGerenciaRegional().iterator();
			boolean primeiro = true;
			while (itt.hasNext()) {
				GerenciaRegional gerenciaRegional = (GerenciaRegional) itt.next();
				if (gerenciaRegional != null) {
					if (primeiro) {
						gerenciasRegionais = gerenciasRegionais	+ gerenciaRegional.getNome();
						primeiro = false;
					} else {
						gerenciasRegionais = gerenciasRegionais + ", " + gerenciaRegional.getNome();
					}
				}
			}

			parametros.put("gerenciaRegional", gerenciasRegionais);

		} else {
			parametros.put("gerenciaRegional", "");
		}

		if (parametrosHelper.getColecaoUnidadeNegocio() != null) {
			String unidadesNegocio = "";

			Iterator itt = parametrosHelper.getColecaoUnidadeNegocio().iterator();
			boolean primeiro = true;
			while (itt.hasNext()) {
				UnidadeNegocio unidadeNegocio = (UnidadeNegocio) itt.next();
				if (unidadeNegocio != null) {
					if (primeiro) {
						unidadesNegocio = unidadesNegocio + unidadeNegocio.getNome();
						primeiro = false;
					} else {
						unidadesNegocio = unidadesNegocio + ", " + unidadeNegocio.getNome();
					}
				}
			}

			parametros.put("unidadeNegocio", unidadesNegocio);

		} else {
			parametros.put("unidadeNegocio", "");
		}

		if (parametrosHelper.getColecaoImovelPerfil() != null) {
			String imoveisPerfil = "";

			Iterator itt = parametrosHelper.getColecaoImovelPerfil().iterator();
			boolean primeiro = true;
			while (itt.hasNext()) {
				ImovelPerfil imovelPerfil = (ImovelPerfil) itt.next();
				if (imovelPerfil != null) {
					if (primeiro) {
						imoveisPerfil = imoveisPerfil + imovelPerfil.getDescricao();
						primeiro = false;
					} else {
						imoveisPerfil = imoveisPerfil + ", " + imovelPerfil.getDescricao();
					}
				}
			}

			parametros.put("imovelPerfil", imoveisPerfil);

		} else {
			parametros.put("imovelPerfil", "");
		}

		if (parametrosHelper.getColecaoCategoria() != null) {
			String categorias = "";

			Iterator itt = parametrosHelper.getColecaoCategoria().iterator();
			boolean primeiro = true;
			while (itt.hasNext()) {
				Categoria categoria = (Categoria) itt.next();
				if (categoria != null) {
					if (primeiro) {
						categorias = categorias + categoria.getDescricao();
						primeiro = false;
					} else {
						categorias = categorias + ", " + categoria.getDescricao();
					}
				}
			}

			parametros.put("categoria", categorias);

		} else {
			parametros.put("categoria", "");
		}

		if (parametrosHelper.getColecaoClienteTipo() != null) {
			String tiposCliente = "";

			Iterator itt = parametrosHelper.getColecaoClienteTipo().iterator();
			boolean primeiro = true;
			while (itt.hasNext()) {
				ClienteTipo clienteTipo = (ClienteTipo) itt.next();
				if (clienteTipo != null) {
					if (primeiro) {
						tiposCliente = tiposCliente	+ clienteTipo.getDescricao();
						primeiro = false;
					} else {
						tiposCliente = tiposCliente + ", " + clienteTipo.getDescricao();
					}
				}
			}

			parametros.put("tipoCliente", tiposCliente);

		} else {
			parametros.put("tipoCliente", "");
		}

		if (parametrosHelper.getColecaoEsferaPoder() != null) {
			String esferasPoder = "";

			Iterator itt = parametrosHelper.getColecaoEsferaPoder().iterator();
			boolean primeiro = true;
			while (itt.hasNext()) {
				EsferaPoder esferaPoder = (EsferaPoder) itt.next();
				if (esferaPoder != null) {
					if (primeiro) {
						esferasPoder = esferasPoder	+ esferaPoder.getDescricao();
						primeiro = false;
					} else {
						esferasPoder = esferasPoder + ", "	+ esferaPoder.getDescricao();
					}
				}
			}

			parametros.put("esferaPoder", esferasPoder);

		} else {
			parametros.put("esferaPoder", "");
		}
		
		//*************************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 13/01/2011
		//*************************************************************
		if (parametrosHelper.getColecaoLigacaoAguaSituacao() != null) {
			String ligacaoAguaSituacaoSel = "";

			Iterator itt = parametrosHelper.getColecaoLigacaoAguaSituacao().iterator();
			boolean primeiro = true;
			while (itt.hasNext()) {
				LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) itt.next();
				if (ligacaoAguaSituacao != null) {
					if (primeiro) {
						ligacaoAguaSituacaoSel = ligacaoAguaSituacaoSel + ligacaoAguaSituacao.getDescricao();
						primeiro = false;
					} else {
						ligacaoAguaSituacaoSel = ligacaoAguaSituacaoSel + ", "	+ ligacaoAguaSituacao.getDescricao();
					}
				}
			}

			parametros.put("ligacaoAguaSituacao", ligacaoAguaSituacaoSel);

		} else {
			parametros.put("ligacaoAguaSituacao", "");
		}
		
		if (parametrosHelper.getColecaoLigacaoEsgotoSituacao() != null) {
			String ligacaoEsgotoSituacaoSel = "";

			Iterator itt = parametrosHelper.getColecaoLigacaoEsgotoSituacao().iterator();
			boolean primeiro = true;
			while (itt.hasNext()) {
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) itt.next();
				if (ligacaoEsgotoSituacao != null) {
					if (primeiro) {
						ligacaoEsgotoSituacaoSel = ligacaoEsgotoSituacaoSel + ligacaoEsgotoSituacao.getDescricao();
						primeiro = false;
					} else {
						ligacaoEsgotoSituacaoSel = ligacaoEsgotoSituacaoSel + ", "	+ ligacaoEsgotoSituacao.getDescricao();
					}
				}
			}

			parametros.put("ligacaoEsgotoSituacao", ligacaoEsgotoSituacaoSel);

		} else {
			parametros.put("ligacaoEsgotoSituacao", "");
		}
		//****************************************************************
		
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_NEGATIVACOES_EXCLUIDAS, parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,Relatorio.GERAR_RELATORIO_NEGATIVACOES_EXCLUIDAS,idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = ConstantesExecucaoRelatorios.QUANTIDADE_NAO_INFORMADA;

		// retorno = Fachada.getInstancia().totalRegistrosPesquisa(
		// (FiltroNegativador) getParametro("filtroNegativador"),
		// Negativador.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioNegativacoesExcluidas", this);
	}

}
