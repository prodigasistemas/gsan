package gcom.micromedicao;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.gui.micromedicao.DadosMovimentacao;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.ejb.SessionBean;

/**
 * Controlador Faturamento CAEMA
 * 
 * @author Sávio Luiz
 * @date 25/07/2006
 */
public class ControladorMicromedicaoCAEMASEJB extends ControladorMicromedicao
		implements SessionBean {
	private static final long serialVersionUID = 1L;
	
	// valida anoMes para caso de uso anlise excecoes leituras
	public boolean validaDataFaturamentoIncompativel(String anoMesReferencia,
			String anoMesAtual) {
		boolean retorno = true;

		String anoMesReferenciaMaisUmMes = ""
				+ Util.somarData(new Integer(anoMesReferencia));
		String anoMesReferenciaMenosUmMes = ""
				+ Util.subtrairMesDoAnoMes(new Integer(anoMesReferencia), 1);

		if (!((Util.compararAnoMesReferencia(new Integer(anoMesReferencia),
				new Integer(anoMesAtual), "="))
				|| (Util.compararAnoMesReferencia(new Integer(
						anoMesReferenciaMaisUmMes), new Integer(anoMesAtual),
						"=")) || (Util.compararAnoMesReferencia(new Integer(
				anoMesReferenciaMenosUmMes), new Integer(anoMesAtual), "=")))) {
			retorno = false;
		}

		return retorno;
	}

	// valida anoMes para caso de uso anlise excecoes leituras
	public boolean validaDataFaturamentoIncompativelInferior(
			String anoMesReferencia, String anoMesAnterior) {
		boolean retorno = true;

		String anoMesReferenciaMenosUmMes = ""
				+ Util.subtrairMesDoAnoMes(new Integer(anoMesReferencia), 2);

		// Comparando a data anterior faturada no form com o ano
		// mês
		// referência e com o ano mês anterior
		if (!((Util.compararAnoMesReferencia(new Integer(anoMesReferencia),
				new Integer(anoMesAnterior), "="))
				|| (Util.compararAnoMesReferencia(new Integer(
						anoMesReferenciaMenosUmMes),
						new Integer(anoMesAnterior), "=")) || (Util
				.compararAnoMesReferencia(new Integer(anoMesReferencia),
						new Integer(Util.somaMesAnoMesReferencia(new Integer(
								anoMesAnterior), 1)), "=")))) {

			retorno = false;
		}
		return retorno;
	}
	
	/**
	 * Gerar o vetor para registrar as leituras e anormalidades
	 * 
	 * @data 28/07/2008
	 * @return
	 * @throws ControladorException
	 */
	public Vector<DadosMovimentacao> gerarVetorDadosParaLeitura(BufferedReader buffer) throws ControladorException {
		Vector<DadosMovimentacao> dados = new Vector<DadosMovimentacao>();
		
        String linha = null;
        String primeiraLinha =  null;
        boolean bufferLido = false;
        
        
		try {

            primeiraLinha = buffer.readLine();

            while (buffer.ready()) {

                if (primeiraLinha.substring(173, 179) != null) {
                    if (bufferLido == false) {
                        dados.add(gerarDadosMovimentacao(primeiraLinha));
                        bufferLido = true;
                    } else {
                        linha = buffer.readLine();
                        dados.add(gerarDadosMovimentacao(linha));
                    }

                }
            }

        } catch (Exception e) {

            try {
                while (buffer.ready()) {
                    if (bufferLido == false) {
                        dados.add(gerarDadosMovimentacaoColetor(primeiraLinha));
                        bufferLido = true;
                    } else {
                        linha = buffer.readLine();
                        dados.add(gerarDadosMovimentacaoColetor(linha));
                    }

                }
            } catch (IOException e1) {
                e1.printStackTrace();
                throw new ControladorException("erro.sistema");
            }
        }

        return dados;
	}
	

    public DadosMovimentacao gerarDadosMovimentacaoColetor(String linha)
            throws ControladorException {

        DadosMovimentacao retorno = new DadosMovimentacao();

        retorno.setMatriculaOperador(new Integer(linha.substring(2, 8)));
        retorno.setDataLeituraCampo(Util.converteStringSemBarraParaDate(linha
                .substring(8, 16)));
        retorno.setMatriculaImovel(new Integer(linha.substring(16, 24)));
        // Alterado por Sávio Luiz para a Implantação
        // Data:29/10/2008
        // retorno.setLocalidade(new Integer(linha.substring(24, 27)));
        // retorno.setSetorComercial(new Integer(linha.substring(27, 30)));
        // retorno.setNumeroQuadra(new Integer(linha.substring(30, 33)));
        // retorno.setNumeroLote(new Integer(linha.substring (33, 37)));
        // retorno.setNumeroSubLote(new Integer(linha.substring(37, 40)));
        try {
            Object[] dadosImovel = repositorioImovel
                    .pesquisarInscricaoImovel(retorno.getMatriculaImovel());
            if (dadosImovel != null) {
                if (dadosImovel[0] != null) {
                    retorno.setLocalidade((Integer) dadosImovel[0]);
                }
                if (dadosImovel[1] != null) {
                    retorno.setSetorComercial((Integer) dadosImovel[1]);
                }
                if (dadosImovel[2] != null) {
                    retorno.setNumeroQuadra((Integer) dadosImovel[2]);
                }
                if (dadosImovel[3] != null) {
                    retorno.setNumeroLote(((Short) dadosImovel[3]).intValue());
                }
                if (dadosImovel[4] != null) {
                    retorno.setNumeroSubLote(((Short) dadosImovel[4])
                            .intValue());
                }
            }
        } catch (ErroRepositorioException ex) {
            ex.printStackTrace();
            throw new ControladorException("erro.sistema", ex);
        }

        retorno.setTipoMedicao(new Integer(linha.substring(40, 41)));
        retorno.setLeituraHidrometro(new Integer(linha.substring(41, 47)));
        retorno.setCodigoAnormalidade(new Integer(linha.substring(47, 49)));
        retorno
                .setIndicadorConfirmacaoLeitura(new Byte(linha
                        .substring(49, 50)));

        String rotaSequencialRota = this
                .obterRotaESequencialRotaDoImovel(retorno.getMatriculaImovel());

        StringTokenizer dadosRota = new StringTokenizer(rotaSequencialRota, ".");

        String codigoRota = dadosRota.nextToken();
        String sequencialRota = dadosRota.nextToken();

        // Alterado por Sávio Luiz para a Implantação
        // Data:29/10/2008
        // FiltroQuadra filtroQuadra = new FiltroQuadra();
        // filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");
        // filtroQuadra.adicionarParametro(new
        // ParametroSimples(FiltroQuadra.ID_LOCALIDADE,
        // retorno.getLocalidade()));
        // filtroQuadra.adicionarParametro(new
        // ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL,
        // retorno.getSetorComercial()));
        // filtroQuadra.adicionarParametro(new
        // ParametroSimples(FiltroQuadra.NUMERO_QUADRA,
        // retorno.getNumeroQuadra()));
        // filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID,
        // "0"));
        //		
        // Collection colecaoQuadra =
        // getControladorUtil().pesquisar(filtroQuadra, Quadra.class.getName());
        //		
        //		Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);

        FaturamentoGrupo faturamentoGrupo = getControladorImovel()
                .pesquisarGrupoImovel(retorno.getMatriculaImovel());
        if (faturamentoGrupo != null && !faturamentoGrupo.equals("")) {
            retorno.setGrupoFaturamento(faturamentoGrupo.getId());
        }

        retorno.setCodigoRota(new Integer(codigoRota));
        retorno.setNumeroSequencialRota(new Integer(sequencialRota));

        retorno.setHoraLeituraCampo("000000");

        return retorno;
    }

	// ==============================================================================================================
	// MÉTODOS EXCLUSIVOS DA CAEMA
	// ==============================================================================================================

	/**
	 * [UC0105] Obter Consumo Mínimo da Ligação por Subcategoria
	 * 
	 * @author Raphael Rossiter
	 * @date 11/04/2007
	 * 
	 * @return imovel, colecaoSubcategoria
	 * @throws ControladorException
	 *//*
	public int obterConsumoMinimoLigacao(Imovel imovel,
			Collection colecaoSubcategoria) throws ControladorException {

		int consumoMinimoLigacao = 0;

		Collection colecaoDataVigencia = null;
		Integer consumoMinimo = null;

		// Obtém o id do consumo tarifa vigência da maior data de vigência da
		// tarifa do imóvel
		try {
			colecaoDataVigencia = repositorioMicromedicao
					.pesquisarMaiorDataVigenciaConsumoTarifaImovel(new Date(),
							imovel);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", ex);
		}

		// Obtém o id do array e atribui na variável
		Object[] consumoTarifaVigenciaIdArray = (Object[]) Util
				.retonarObjetoDeColecaoArray(colecaoDataVigencia);
		Integer consumoTarifaVigenciaId = null;

		if (consumoTarifaVigenciaIdArray == null) {
			throw new ControladorException(
					"erro.nao_cadastrada_consumo_tarifa_vigencia", null, String
							.valueOf(imovel.getId()));
		}

		if (consumoTarifaVigenciaIdArray[0] != null) {
			consumoTarifaVigenciaId = (Integer) consumoTarifaVigenciaIdArray[0];
		}

		// Cria o objeto consumo tarifa vigência e seta o id
		ConsumoTarifaVigencia consumoTarifaVigencia = new ConsumoTarifaVigencia();
		consumoTarifaVigencia.setId(consumoTarifaVigenciaId);

		// Obter Quantidade de Economias por Subcategoria
		if (colecaoSubcategoria == null) {
			colecaoSubcategoria = getControladorImovel()
					.obterQuantidadeEconomiasSubCategoria(imovel.getId());
		}

		Iterator colecaoSubcategoriaIterator = colecaoSubcategoria.iterator();

		while (colecaoSubcategoriaIterator.hasNext()) {

			Subcategoria subcategoria = (Subcategoria) colecaoSubcategoriaIterator
					.next();

			try {
				// Obtém o consumo mínimo da tarifa da categoria do imóvel
				Object consumoMinimoObjeto = repositorioMicromedicao
						.pesquisarConsumoMinimoTarifaSubcategoriaVigencia(
								subcategoria, consumoTarifaVigencia);
				consumoMinimo = (Integer) consumoMinimoObjeto;
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				new ControladorException("erro.sistema", ex);
			}

			
			 * Caso não exista tarifa cadastrada para a subcategoria em questao,
			 * utilizar a tarifa da categoria
			 
			if (consumoMinimo == null) {

				Categoria categoria = subcategoria.getCategoria();
				categoria.setQuantidadeEconomiasCategoria(subcategoria
						.getQuantidadeEconomias());

				Collection colecaoCategoria = new ArrayList();
				colecaoCategoria.add(categoria);

				consumoMinimo = super.obterConsumoMinimoLigacao(imovel,
						colecaoCategoria);

				consumoMinimoLigacao = consumoMinimoLigacao + consumoMinimo;

			} else {

				// Multiplica a quantidade de economias da subcategoria pelo
				// consumo
				// mínimo e acumula
				consumoMinimoLigacao = consumoMinimoLigacao
						+ (subcategoria.getQuantidadeEconomias().intValue() * consumoMinimo
								.intValue());
			}

		}

		// Retorna o consumo mínimo da ligação
		return consumoMinimoLigacao;

	}
*/	//==============================================================================================================

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0005] Gerar Relação(ROL) em TXT - CAEMA
	 * 
	 * @author Rômulo Aurélio, Raphael Rossiter
	 * @date 04/07/2008, 27/08/2009
	 * 
	 * @param anoMesCorrente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection gerarDadosPorLeituraMicroColetor(Collection colecaoRota,
			Integer anoMesCorrente, Integer idGrupoFaturamentoRota,
			SistemaParametro sistemaParametro, int idFuncionalidadeIniciada)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		Iterator colecaoRotaIterator = colecaoRota.iterator();

		while (colecaoRotaIterator.hasNext()) {
			
			Rota rota = (Rota) colecaoRotaIterator.next();
			
			//-------------------------
			//
			// Registrar o início do processamento da Unidade de
			// Processamento
			// do Batch
			//
			// -------------------------
			idUnidadeIniciada = getControladorBatch()
			.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, 
			UnidadeProcessamento.ROTA, rota.getId());

			try {


				//CONTROLE DE PAGINAÇÃO DA PESQUISA
				int numeroIndice = 0;
				int quantidadeRegistrosPesquisa = 1000;
				boolean flagTerminou = false;
				Integer qtdImoveis = 0;

				//MOVIMENTO ROTEIRO EMPRESA
				Collection<Object[]> objetosMovimentoRoteiroEmpresa = new ArrayList<Object[]>();

				while (!flagTerminou) {

					try {

						//PESQUISANDO MOVIMENTO ROTEIRO EMPRESA
						objetosMovimentoRoteiroEmpresa = repositorioMicromedicao
						.pesquisarImoveisParaLeituraPorColecaoRotaCAEMA(rota, numeroIndice, anoMesCorrente);

					} catch (ErroRepositorioException e) {
						throw new ControladorException("erro.sistema", e);
					}

					if (objetosMovimentoRoteiroEmpresa != null && !objetosMovimentoRoteiroEmpresa.isEmpty()) {

						qtdImoveis = objetosMovimentoRoteiroEmpresa.size();

						//CONTROLE DE PAGINAÇÃO DA PESQUISA
						if (objetosMovimentoRoteiroEmpresa.size() < quantidadeRegistrosPesquisa) {
							flagTerminou = true;
						} 
						else {
							numeroIndice = numeroIndice + quantidadeRegistrosPesquisa;
						}

						// pega o id da empresa do objeto imovel.
						Integer idRotaOld = null;

						Integer idFaturamentoGrupoOld = null;

						/*
						 * Cria uma variável do tipo boolean para saber se é a mesma empresa
						 * ou outra empresa.
						 */
						boolean mesmaEmpresa = false;

						// É usado para criar o header do arquivo de leitura
						boolean headerArquivo = true;

						Integer quantidadeRegistros = new Integer(0);

						Integer quantidadeMovimentoRoteiroEmpresa = new Integer(0);

						Integer quantidadeRegistrosFiscalizacao = new Integer(0);

						StringBuilder arquivoTxt = new StringBuilder();

						StringBuilder arquivoHeaderFiscalizacao = new StringBuilder();

						StringBuilder arquivoTxtFiscalizacao = new StringBuilder();

						Calendar dataCalendar = new GregorianCalendar();

						String ano = null;
						String mes = null;
						String dia = null;

						MovimentoRoteiroEmpresa movimentoRoteiroEmpresa = null;


						for (Object[] dadosMovimentoRoteiroEmpresa : objetosMovimentoRoteiroEmpresa) {

							boolean ligacaoAgua = false;
							boolean ligacaoPoco = false;

							// cria uma string builder para adicionar no arquivo
							StringBuilder arquivoTxtLinha = new StringBuilder();

							movimentoRoteiroEmpresa = (MovimentoRoteiroEmpresa) dadosMovimentoRoteiroEmpresa[0];
							Integer idQuadra = (Integer) dadosMovimentoRoteiroEmpresa[1];

							Quadra quadra = new Quadra();
							quadra.setId(idQuadra);

							Imovel imovel = new Imovel();
							imovel.setId(movimentoRoteiroEmpresa.getImovel().getId());
							imovel.setQuadra(quadra);
							movimentoRoteiroEmpresa.setImovel(imovel);
							

							// incrementa a quantidade de registros
							quantidadeRegistros = quantidadeRegistros + 1;

							quantidadeMovimentoRoteiroEmpresa = objetosMovimentoRoteiroEmpresa.size();

							/*
							 * Verifica se a empresa da rota que está na coleção é igual
							 * a empresa anterior.
							 */
							if ((idRotaOld == null || movimentoRoteiroEmpresa
								.getRota().getId().equals(idRotaOld)) && 
								(idFaturamentoGrupoOld == null || movimentoRoteiroEmpresa
								.getFaturamentoGrupo().getAnoMesReferencia().equals(idFaturamentoGrupoOld))) {
								
								mesmaEmpresa = true;

							} 
							else {
								
								mesmaEmpresa = false;
							}

							if (mesmaEmpresa) {
								
								//GERANDO O ARQUIVO TXT
								adicionarLinhaTxt(arquivoTxt, arquivoTxtLinha,
										arquivoTxtFiscalizacao,
										arquivoHeaderFiscalizacao,
										quantidadeRegistros,
										quantidadeMovimentoRoteiroEmpresa,
										quantidadeRegistrosFiscalizacao,
										movimentoRoteiroEmpresa, ligacaoAgua,
										ligacaoPoco, idFaturamentoGrupoOld,
										headerArquivo, sistemaParametro,
										idRotaOld, dataCalendar, anoMesCorrente);
							} 
							else {

								/*
								 * Manda o header do arquivo para true, pois agora será outra
								 * empresa e precisa-se de um outro header.
								 */
								headerArquivo = true;

								ano = "" + dataCalendar.get(Calendar.YEAR);
								mes = "" + (dataCalendar.get(Calendar.MONTH) + 1);
								dia = "" + dataCalendar.get(Calendar.DAY_OF_MONTH);
								
								dia = Util.adicionarZerosEsquedaNumero(2, "" + dia);
								mes = Util.adicionarZerosEsquedaNumero(2, "" + mes);

								String nomeArquivo = "CG"
								+ movimentoRoteiroEmpresa.getFaturamentoGrupo().getId().toString()
								+ movimentoRoteiroEmpresa.getCodigoRota().toString()
								+ mes + ano + ".txt";

								if (arquivoTxt != null && arquivoTxt.length() != 0) {
									
									this.inserirArquivoTextoRoteiroEmpresa(
									anoMesCorrente, movimentoRoteiroEmpresa, qtdImoveis, arquivoTxt,
									nomeArquivo);
								}

								// ROTA ANTIGA.
								idRotaOld = movimentoRoteiroEmpresa.getRota().getId();

								/*
								 * Cria outra string para começar a criar o txt.
								 * Limpa os campos para serem usados na próxima empresa.
								 */
								arquivoTxt = new StringBuilder();
								quantidadeRegistros = 0;
								quantidadeRegistrosFiscalizacao = 0;
								arquivoTxtFiscalizacao = new StringBuilder();
								arquivoHeaderFiscalizacao = new StringBuilder();

								adicionarLinhaTxt(arquivoTxt, arquivoTxtLinha,
										arquivoTxtFiscalizacao,
										arquivoHeaderFiscalizacao,
										quantidadeRegistros,
										quantidadeMovimentoRoteiroEmpresa,
										quantidadeRegistrosFiscalizacao,
										movimentoRoteiroEmpresa, ligacaoAgua,
										ligacaoPoco, idFaturamentoGrupoOld,
										headerArquivo, sistemaParametro,
										idRotaOld, dataCalendar, anoMesCorrente);

							}

							headerArquivo = false;

						}

						// Registro Trailer tam.150(zeros)
						arquivoTxt.append("" + Util.adicionarZerosEsquedaNumeroTruncando(153, ""));

						arquivoTxt.append(System.getProperty("line.separator"));

						

						
						String mesAno = Util.formatarAnoMesParaMesAnoSemBarra(movimentoRoteiroEmpresa.getAnoMesMovimento());

						String nomeArquivo = "CG"
						+ movimentoRoteiroEmpresa.getFaturamentoGrupo().getId().toString()
						+ movimentoRoteiroEmpresa.getCodigoRota().toString() + mesAno + ".txt";


						if (arquivoTxt != null && arquivoTxt.length() != 0) {
							
							//GERANDO O ARQUIVO TXT
							this.inserirArquivoTextoRoteiroEmpresa(anoMesCorrente, movimentoRoteiroEmpresa,
							qtdImoveis, arquivoTxt, nomeArquivo);

						}
					}
					
					flagTerminou = true;

					/*
					 * Atualiza a data e a hora da realização da atividade com a data e
					 * a hora correntes.
					 */
					try {

						repositorioMicromedicao.atualizarFaturamentoAtividadeCronograma(
						idGrupoFaturamentoRota, anoMesCorrente);
					} 
					catch (ErroRepositorioException e) {
						throw new ControladorException("erro.sistema", e);
					}

					
				}
				
				//	Encerra a unidade de Faturamento
				getControladorBatch().encerrarUnidadeProcessamentoBatch(
				null, idUnidadeIniciada, false);
				

			} catch (Exception e) { 
				
				/*
				 * Este catch serve para interceptar qualquer exceção que o processo batch
				 * venha a lançar e garantir que a unidade de processamento do batch 
				 * será atualizada com o erro ocorrido.
				 */
				e.printStackTrace();

				getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
				idUnidadeIniciada, true);
			}
		}
		
		return null;
		// return colecaoRota;
	}

	/**
	 * [UC0083]- Gerar dados para Leitura Author: Rômulo Aurélio Date:
	 * 08/07/2008
	 * 
	 * @param arquivoTxt
	 * @param arquivoTxtLinha
	 * @param arquivoTxtFiscalizacao
	 * @param arquivoHeaderFiscalizacao
	 * @param quantidadeRegistros
	 * @param quantidadeMovimentoRoteiroEmpresa
	 * @param quantidadeRegistrosFiscalizacao
	 * @param movimentoRoteiroEmpresa
	 * @param ligacaoAgua
	 * @param ligacaoPoco
	 * @param idFaturamentoGrupoOld
	 * @param headerArquivo
	 * @param sistemaParametro
	 * @param idRotaOld
	 * @param dataCalendar
	 * @param anoMesCorrente
	 */

	private void adicionarLinhaTxt(StringBuilder arquivoTxt,
			StringBuilder arquivoTxtLinha,
			StringBuilder arquivoTxtFiscalizacao,
			StringBuilder arquivoHeaderFiscalizacao,
			Integer quantidadeRegistros,
			Integer quantidadeMovimentoRoteiroEmpresa,
			Integer quantidadeRegistrosFiscalizacao,
			MovimentoRoteiroEmpresa movimentoRoteiroEmpresa,
			boolean ligacaoAgua, boolean ligacaoPoco,
			Integer idFaturamentoGrupoOld, boolean headerArquivo,
			SistemaParametro sistemaParametro, Integer idRotaOld,
			Calendar dataCalendar, Integer anoMesCorrente) {

		String mes = null;
		String dia = null;

		// se for para criar o header do arquivo
		if (headerArquivo) {

			/*
			 * // pega o id da empresa do objeto imovel. idEmpresaOld =
			 * movimentoRoteiroEmpresa.getEmpresa().getId();
			 */

			// 1.1.1 tam.04
			String quantidadeLeitura = ""
					+ Util.adicionarZerosEsquedaNumeroTruncando(4, ""
							+ quantidadeMovimentoRoteiroEmpresa.toString());

			// 1.1.2 tam.03
			String tarefa = ""
					+ Util.adicionarZerosEsquedaNumeroTruncando(3,
							movimentoRoteiroEmpresa.getRota().getCodigo()
									.toString());
			// 1.1.3 tam.06
			String codigoLeiturista = ""
					+ Util.adicionarZerosEsquedaNumeroTruncando(6, "1");

			// 1.1.4
			mes = "" + (dataCalendar.get(Calendar.MONTH) + 1);
			dia = "" + dataCalendar.get(Calendar.DAY_OF_MONTH);
			mes = "" + Util.adicionarZerosEsquedaNumeroTruncando(2, mes);
			dia = "" + Util.adicionarZerosEsquedaNumeroTruncando(2, dia);

			// 1.1.5 tam.06
			String grupoFaturamento = ""
					+ Util.adicionarZerosEsquedaNumeroTruncando(6,
							movimentoRoteiroEmpresa.getFaturamentoGrupo()
									.getId().toString());

			// 1.1.6 fixo 150
			String percentualCritica = "150";

			// Registro HEADER
			arquivoTxt.append(quantidadeLeitura + tarefa + codigoLeiturista
					+ dia + mes + grupoFaturamento + percentualCritica);

			// manda o header do arquivo para falso
			headerArquivo = false;

			arquivoHeaderFiscalizacao.append(arquivoTxt);
			arquivoTxt.append(System.getProperty("line.separator"));

		}

		// 1.2.1-Matricula com digito Verificador tam.08
		arquivoTxtLinha
				.append(""
						+ Util.adicionarZerosEsquedaNumeroTruncando(8,
								movimentoRoteiroEmpresa.getImovel().getId()
										.toString()));

		// 1.2.2 - Inscricao do imovel tam.16
		if (movimentoRoteiroEmpresa.getInscricaoImovel() != null) {
			arquivoTxtLinha.append(movimentoRoteiroEmpresa.getInscricaoImovel()
					.replace(".", ""));
		} else {
			arquivoTxtLinha.append(""
					+ Util.adicionarZerosEsquedaNumeroTruncando(16, ""));
		}
		// 1.2.x - Codigo da Rota

		if (movimentoRoteiroEmpresa.getCodigoRota() != null){
			arquivoTxtLinha.append(""
					+ Util.adicionarZerosEsquedaNumeroTruncando(2, ""
							+ movimentoRoteiroEmpresa.getCodigoRota()));
		}else{
			arquivoTxtLinha.append(""
					+ Util.adicionarZerosEsquedaNumeroTruncando(2, ""));
		}
		// 1.2.3- Nome logradouro tam.30
		arquivoTxtLinha.append(completaString(movimentoRoteiroEmpresa
				.getNomeLogradouro(), 30));

		// 1.2.4-Complemento Endereco tam.04
		if(movimentoRoteiroEmpresa
				.getComplementoEndereco() != null){
			
			arquivoTxtLinha.append(completaString(movimentoRoteiroEmpresa
				.getComplementoEndereco(), 4));
			
		}else{
			arquivoTxtLinha.append(completaString("",4));
		}
		// 1.2.5- Numero imovel tam.05
		if (movimentoRoteiroEmpresa.getNumeroImovel() != null){

			arquivoTxtLinha.append(""
					+ Util.adicionarZerosEsquedaNumeroTruncando(5, ""
							+ movimentoRoteiroEmpresa.getNumeroImovel()));
		}else{
			arquivoTxtLinha.append(""
					+ Util.adicionarZerosEsquedaNumeroTruncando(5, ""));
		}
		// 1.2.6-Nome Bairro tam.20
		arquivoTxtLinha.append(completaString(movimentoRoteiroEmpresa
				.getNomeBairro(), 20));

		// 1.2.7- Hidrometro tam.10
		if (movimentoRoteiroEmpresa.getNumeroHidrometro() != null){

			arquivoTxtLinha.append(""
					+ Util.adicionarZerosEsquedaNumeroTruncando(10, ""
							+ movimentoRoteiroEmpresa.getNumeroHidrometro()));

		}else{
			arquivoTxtLinha.append(""
					+ Util.adicionarZerosEsquedaNumeroTruncando(10, ""));
		}
		// 1.2.8- Localizacao tam.01

		arquivoTxtLinha.append(""
				+ Util.adicionarZerosEsquedaNumeroTruncando(1, ""
						+ movimentoRoteiroEmpresa
								.getHidrometroLocalInstalacao().getId()));

		// 1.2.9 - Categoria tam.01

		if (movimentoRoteiroEmpresa.getCategoriaPrincipal() != null){

			arquivoTxtLinha.append(""
					+ Util.adicionarZerosEsquedaNumeroTruncando(1, ""
							+ movimentoRoteiroEmpresa.getCategoriaPrincipal().getId()));

		}else{
			arquivoTxtLinha.append(""
					+ Util.adicionarZerosEsquedaNumeroTruncando(1, ""));
		}
		// 1.2.10 - Subcategoria tam.01

		if (movimentoRoteiroEmpresa.getCodigoSubcategoria1() != null){

			arquivoTxtLinha
					.append(""
							+ Util.adicionarZerosEsquedaNumeroTruncando(1, ""
									+ movimentoRoteiroEmpresa
											.getCodigoSubcategoria1()));

		}else{
			arquivoTxtLinha.append(""
					+ Util.adicionarZerosEsquedaNumeroTruncando(1, ""));
		}
		// 1.2.11 - Numero do sequencial da rota tam.06

		if (movimentoRoteiroEmpresa.getNumeroSequencialRota() != null){

			arquivoTxtLinha.append(""
					+ Util
							.adicionarZerosEsquedaNumeroTruncando(6, ""
									+ movimentoRoteiroEmpresa
											.getNumeroSequencialRota()));

		}else{
			arquivoTxtLinha.append(""
					+ Util.adicionarZerosEsquedaNumeroTruncando(6, ""));
		}
		// 1.2.12 - Leitura Anterior tam.07
		if (movimentoRoteiroEmpresa.getNumeroLeituraAnterior() != null){
			arquivoTxtLinha.append(""
					+ Util.adicionarZerosEsquedaNumeroTruncando(7, ""
							+ movimentoRoteiroEmpresa
									.getNumeroLeituraAnterior()));

		}else{
			arquivoTxtLinha.append(""
					+ Util.adicionarZerosEsquedaNumeroTruncando(7, ""));
		}
		// 1.2.13 - Consumo Medio tam.06
		if (movimentoRoteiroEmpresa.getNumeroConsumoMedio() != null){
			arquivoTxtLinha.append(""
					+ Util.adicionarZerosEsquedaNumeroTruncando(6, ""
							+ movimentoRoteiroEmpresa.getNumeroConsumoMedio()));
		}else{
			arquivoTxtLinha.append(""
					+ Util.adicionarZerosEsquedaNumeroTruncando(6, ""));
		}
		// 1.2.14 - Nome do cliente tam.30

		arquivoTxtLinha.append(completaString(movimentoRoteiroEmpresa
				.getNomeCliente(), 30));

		// 1.2.15 - Zeros tam.21

		arquivoTxtLinha.append(""
				+ Util.adicionarZerosEsquedaNumeroTruncando(21, ""));

		// Registro Detalhe
		arquivoTxt.append(arquivoTxtLinha);

		arquivoTxt.append(System.getProperty("line.separator"));

	}

	/**
	 * [UC0083]-Gerar Dados para Leitura
	 * 
	 * Author: Rômulo Aurélio Data: 17/06/2008
	 * 
	 * 
	 * 
	 * @param colecaoRota
	 * @param anoMesCorrente
	 * @param idGrupoFaturamentoRota
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */

	/*public void gerarDadosPorLeituraParaInserir(
			Collection colecaoRota, Integer anoMesCorrente,
			Integer idGrupoFaturamentoRota, SistemaParametro sistemaParametro,
			Integer idFuncionalidadeIniciada) throws ControladorException {

		@SuppressWarnings("unused")
		int idUnidadeIniciada = 0;

		Iterator colecaoRotaIterator = colecaoRota.iterator();

		while (colecaoRotaIterator.hasNext()) {
			Rota rota = (Rota) colecaoRotaIterator.next();

			try {

				// -------------------------
				//
				// Registrar o início do processamento da Unidade de
				// Processamento
				// do Batch
				//
				// -------------------------

				idUnidadeIniciada = getControladorBatch()
						.iniciarUnidadeProcessamentoBatch(
								idFuncionalidadeIniciada,
								UnidadeProcessamento.FUNCIONALIDADE, 0);
				
//				 Apaga o anomesAtual e anterior
				repositorioMicromedicao.removerMovimentoRoteiroEmpresa(
						Util.subtraiAteSeisMesesAnoMesReferencia(1,anoMesCorrente), idGrupoFaturamentoRota, rota);

				repositorioMicromedicao.removerMovimentoRoteiroEmpresa(
						anoMesCorrente, idGrupoFaturamentoRota, rota);

				int numeroIndice = 0;
				int quantidadeRegistrosPesquisa = 1000;
				boolean flagTerminou = false;

				// inicializa uma coleção de imoveis
				Collection objetosImoveis = new ArrayList();

				while (!flagTerminou) {

					// cria uma coleção de imóvel por rota
					Collection imoveisPorRota = null;
					try {
						// recupera todos os imóveis da coleção de rotas do tipo
						// convencional

						imoveisPorRota = repositorioMicromedicao
								.pesquisarImoveisPorRotaCaema(rota,
										numeroIndice, sistemaParametro
												.getNomeAbreviadoEmpresa());

					} catch (ErroRepositorioException e) {
						throw new ControladorException("erro.sistema", e);
					}

					if (imoveisPorRota != null && !imoveisPorRota.isEmpty()) {

						if (imoveisPorRota.size() < quantidadeRegistrosPesquisa) {
							flagTerminou = true;
						} else {
							numeroIndice = numeroIndice
									+ quantidadeRegistrosPesquisa;
						}

						Iterator imovelporRotaIterator = imoveisPorRota
								.iterator();
						while (imovelporRotaIterator.hasNext()) {
							// cria um array de objetos para pegar os parametros
							// de
							// retorno da pesquisa
							Object[] arrayImoveisPorRota = (Object[]) imovelporRotaIterator
									.next();

							// instancia um imóvel
							Imovel imovel = new Imovel();
							if (arrayImoveisPorRota[0] != null) {
								// seta o id no imovel
								imovel.setId((Integer) arrayImoveisPorRota[0]);
							}

							if (arrayImoveisPorRota[1] != null) {
								// instancia uma localidade para ser setado no
								// imóvel
								Localidade localidade = new Localidade();
								localidade
										.setId((Integer) arrayImoveisPorRota[1]);
								imovel.setLocalidade(localidade);
							}

							if (arrayImoveisPorRota[2] != null) {
								// instancia um setor comercial para ser setado
								// no
								// imóvel
								SetorComercial setorComercial = new SetorComercial();
								setorComercial.setCodigo(Integer
										.parseInt(arrayImoveisPorRota[2]
												.toString()));
								imovel.setSetorComercial(setorComercial);
							}
							Quadra quadra = new Quadra();
							if (arrayImoveisPorRota[3] != null) {
								// instancia uma quadra para ser setado no
								// imóvel

								Integer numeroQuadra = (Integer) arrayImoveisPorRota[3];
								quadra.setNumeroQuadra(numeroQuadra);
								imovel.setQuadra(quadra);
							}

							if (arrayImoveisPorRota[4] != null) {
								// seta o lote no imóvel
								imovel.setLote(Short
										.parseShort(arrayImoveisPorRota[4]
												.toString()));
							}

							if (arrayImoveisPorRota[5] != null) {
								// seta o lote no imóvel
								imovel.setSubLote(Short
										.parseShort(arrayImoveisPorRota[5]
												.toString()));
							}
							if (arrayImoveisPorRota[6] != null) {
								// instancia uma imovel perfil para ser setado
								// no
								// imóvel
								ImovelPerfil imovelPerfil = new ImovelPerfil();
								imovelPerfil
										.setId((Integer) arrayImoveisPorRota[6]);
								imovel.setImovelPerfil(imovelPerfil);
							}

							LigacaoAgua ligacaoAgua = new LigacaoAgua();
							if (arrayImoveisPorRota[7] != null) {
								// instancia uma ligação agua para ser setado no
								// imóvel

								ligacaoAgua
										.setId((Integer) arrayImoveisPorRota[7]);
							}
							// instancia um hidrometro instalação historico para
							// ser
							// colocado na ligacao agua

							if (arrayImoveisPorRota[30] != null) {

								HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoLigacaoAgua = (HidrometroInstalacaoHistorico) arrayImoveisPorRota[30];
								MedicaoTipo medicaoTipo = new MedicaoTipo();
								medicaoTipo
										.setId((Integer) arrayImoveisPorRota[26]);
								hidrometroInstalacaoHistoricoLigacaoAgua
										.setMedicaoTipo(medicaoTipo);
								ligacaoAgua
										.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistoricoLigacaoAgua);

							}
							imovel.setLigacaoAgua(ligacaoAgua);

							// //instancia um hidrometro instalação historico
							// para
							// ser colocado no imovel

							if (arrayImoveisPorRota[31] != null) {

								HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoImovel = (HidrometroInstalacaoHistorico) arrayImoveisPorRota[31];
								MedicaoTipo medicaoTipo = new MedicaoTipo();
								medicaoTipo
										.setId((Integer) arrayImoveisPorRota[27]);
								hidrometroInstalacaoHistoricoImovel
										.setMedicaoTipo(medicaoTipo);
								imovel
										.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistoricoImovel);

							}
							// instancia a rota
							Rota rotaImovel = new Rota();

							if (arrayImoveisPorRota[10] != null) {
								// seta o id da rota
								rotaImovel
										.setId((Integer) arrayImoveisPorRota[10]);
							}
							if (arrayImoveisPorRota[11] != null) {
								// seta o indicador fiscalizador suprimido na
								// rota
								rotaImovel
										.setIndicadorFiscalizarSuprimido(Short
												.parseShort(arrayImoveisPorRota[11]
														.toString()));
							}
							if (arrayImoveisPorRota[12] != null) {
								// seta o indicador fiscalizador cortado na rota
								rotaImovel.setIndicadorFiscalizarCortado(Short
										.parseShort(arrayImoveisPorRota[12]
												.toString()));
							}
							if (arrayImoveisPorRota[13] != null) {
								// seta o indicador gerar fiscalizacao na rota
								rotaImovel.setIndicadorGerarFiscalizacao(Short
										.parseShort(arrayImoveisPorRota[13]
												.toString()));
							}
							if (arrayImoveisPorRota[14] != null) {
								// seta o indicador fgerar falsa faixa na rota
								rotaImovel.setIndicadorGerarFalsaFaixa(Short
										.parseShort(arrayImoveisPorRota[14]
												.toString()));
							}
							if (arrayImoveisPorRota[15] != null) {
								// seta o percentual geracao fiscalizacao na
								// rota
								rotaImovel
										.setPercentualGeracaoFiscalizacao((BigDecimal) (arrayImoveisPorRota[15]));
							}
							if (arrayImoveisPorRota[16] != null) {
								// seta o percentual geracao faixa falsa na rota
								rotaImovel
										.setPercentualGeracaoFaixaFalsa((BigDecimal) (arrayImoveisPorRota[16]));
							}
							// instancia a empresa
							Empresa empresa = new Empresa();
							if (arrayImoveisPorRota[17] != null) {

								// seta o id na empresa
								empresa
										.setId((Integer) arrayImoveisPorRota[17]);

							}
							if (arrayImoveisPorRota[18] != null) {

								// seta a descrição abreviada na empresa
								empresa
										.setDescricaoAbreviada(arrayImoveisPorRota[18]
												.toString());

							}
							if (arrayImoveisPorRota[19] != null) {

								// seta email da empresa
								empresa.setEmail(arrayImoveisPorRota[19]
										.toString());

							}
							if (arrayImoveisPorRota[28] != null) {

								// seta email da empresa
								empresa.setDescricao(arrayImoveisPorRota[28]
										.toString());

							}
							// seta a empresa na rota
							rotaImovel.setEmpresa(empresa);
							// instancia o faturamento
							FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
							if (arrayImoveisPorRota[20] != null) {
								// seta o id no faturamentGrupo
								faturamentoGrupo
										.setId((Integer) arrayImoveisPorRota[20]);

							}
							if (arrayImoveisPorRota[21] != null) {
								// seta o descrição no faturamentGrupo
								faturamentoGrupo
										.setDescricao((String) arrayImoveisPorRota[21]);
							}
							// seta o faturamento na rota
							rotaImovel.setFaturamentoGrupo(faturamentoGrupo);
							if (arrayImoveisPorRota[22] != null) {
								// instancia a ligação esgoto situação
								LeituraTipo leituraTipo = new LeituraTipo();
								// seta o id na ligação esgoto situação
								leituraTipo
										.setId((Integer) arrayImoveisPorRota[22]);
								// seta a ligação esgoto situação no imovel
								rotaImovel.setLeituraTipo(leituraTipo);
							}

							// seta a rota na quadra
							quadra.setRota(rotaImovel);

							// seta o roteiro empresa na quadra
							quadra
									.setRoteiroEmpresa((RoteiroEmpresa) arrayImoveisPorRota[29]);

							// seta a quadra no imovel
							imovel.setQuadra(quadra);
							if (arrayImoveisPorRota[23] != null) {
								// instancia a ligação agua situação
								LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
								// seta o id na ligação agua situação
								ligacaoAguaSituacao
										.setId((Integer) arrayImoveisPorRota[23]);
								// seta a ligação agua situação no imovel
								imovel
										.setLigacaoAguaSituacao(ligacaoAguaSituacao);
							}
							if (arrayImoveisPorRota[24] != null) {
								// instancia a ligação esgoto situação
								LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
								// seta o id na ligação esgoto situação
								ligacaoEsgotoSituacao
										.setId((Integer) arrayImoveisPorRota[24]);
								// seta a ligação esgoto situação no imovel
								imovel
										.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
							}

							if (arrayImoveisPorRota[25] != null) {
								// instancia o faturamento situacao tipo
								FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
								// seta o id no faturamento situacao tipo
								faturamentoSituacaoTipo
										.setIndicadorParalisacaoLeitura((Short) arrayImoveisPorRota[25]);
								// seta a ligação esgoto situação no imovel
								imovel
										.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
							}

							if (arrayImoveisPorRota[32] != null) {
								imovel
										.getQuadra()
										.getRota()
										.getEmpresa()
										.setId(
												(Integer) arrayImoveisPorRota[32]);
							}

							if (arrayImoveisPorRota[33] != null) {
								imovel
										.getLigacaoAguaSituacao()
										.setIndicadorFaturamentoSituacao(
												(Short) arrayImoveisPorRota[33]);
							}

							if (arrayImoveisPorRota[34] != null) {
								imovel
										.getLigacaoEsgotoSituacao()
										.setIndicadorFaturamentoSituacao(
												(Short) arrayImoveisPorRota[34]);
							}

							if (arrayImoveisPorRota[35] != null) {

								LogradouroBairro logradouroBairro = new LogradouroBairro();

								logradouroBairro
										.setId((Integer) arrayImoveisPorRota[35]);

								imovel.setLogradouroBairro(logradouroBairro);
							}

							if (arrayImoveisPorRota[36] != null) {

								Logradouro logradouro = new Logradouro();

								logradouro
										.setId((Integer) arrayImoveisPorRota[36]);

								imovel.getLogradouroBairro().setLogradouro(
										logradouro);
							}

							if (arrayImoveisPorRota[37] != null) {

								imovel
										.getLogradouroBairro()
										.getLogradouro()
										.setNome(
												(String) arrayImoveisPorRota[37]);
							}

							if (arrayImoveisPorRota[38] != null) {
								Bairro bairro = new Bairro();
								bairro
										.setNome((String) arrayImoveisPorRota[38]);

								imovel.getLogradouroBairro().setBairro(bairro);
							}
							if (arrayImoveisPorRota[39] != null) {
								imovel.setNumeroImovel(completaString(
										(String) arrayImoveisPorRota[39], 5));
							}

							if (arrayImoveisPorRota[40] != null) {
								imovel
										.setNumeroSequencialRota((Integer) arrayImoveisPorRota[40]);
							}
							if (arrayImoveisPorRota[41] != null) {
								ligacaoAgua.setNumeroLacre(completaString(
										(String) arrayImoveisPorRota[41], 6));
							}
							if (arrayImoveisPorRota[42] != null) {
								imovel.setComplementoEndereco(completaString(
										(String) arrayImoveisPorRota[42], 3));
							}
							if (arrayImoveisPorRota[43] != null) {
								GerenciaRegional gerenciaRegional = new GerenciaRegional();
								gerenciaRegional
										.setId((Integer) arrayImoveisPorRota[43]);
								imovel.getLocalidade().setGerenciaRegional(
										gerenciaRegional);
							}
							if (arrayImoveisPorRota[44] != null) {

								rotaImovel
										.setCodigo((Short) arrayImoveisPorRota[44]);
							}

							// adiciona na coleção de imoveis
							objetosImoveis.add(imovel);
							arrayImoveisPorRota = null;

						}

					} else {
						flagTerminou = true;
					}
				}

				Collection<Imovel> imoveisParaSerGerados = new ArrayList();
				/**
				 * Valida os imoveis para ser gerados
				 */
				/*imoveisParaSerGerados = this
						.verificarImoveisParaSerGerados(objetosImoveis);

				objetosImoveis = null;

				/**
				 * [SB0004]-Recuperar Dados para inclusao na Tabela
				 

				if (imoveisParaSerGerados != null
						&& !imoveisParaSerGerados.isEmpty()) {

					// pega o id da empresa do objeto imovel.
					Integer idEmpresaOld = null;

					// cria uma variavel do tipo boolean para saber se é a mesma
					// empresa
					// ou
					// outra empresa.
					boolean mesmaEmpresa = false;

					// é usado para na faixa falsa saber se o hidrometro foi
					// selecionado
					// ou
					// não
					boolean hidrometroSelecionado = false;

					Integer quantidadeRegistros = 0;

					Integer quantidadeImoveis = 0;

					ListIterator imovelParaSerGeradoIterator = ((List) imoveisParaSerGerados)
							.listIterator(0);

					Imovel imovelParaSerGerado = null;
					// ListIterator imovelParaSerGeradoIterator = (ListIterator)
					// imoveisParaSerGerados
					// .iterator();

					while (imovelParaSerGeradoIterator.hasNext()) {
						boolean ligacaoAgua = false;
						boolean ligacaoPoco = false;

						imovelParaSerGerado = (Imovel) imovelParaSerGeradoIterator
								.next();

						// pega o id da empresa do objeto imovel.
						idEmpresaOld = imovelParaSerGerado.getQuadra()
								.getRota().getEmpresa().getId();

						// Verifica se a empresa da rota que está na coleção é
						// igual
						// a
						// empresa anterior
						if (imovelParaSerGerado.getQuadra().getRota()
								.getEmpresa().getId().equals(idEmpresaOld)) {
							mesmaEmpresa = true;

						} else {
							mesmaEmpresa = false;

						}

						if (mesmaEmpresa) {
							// incrementa a quantidade de registros
							quantidadeRegistros = quantidadeRegistros + 1;

							quantidadeImoveis = quantidadeImoveis + 1;

							if (imovelParaSerGerado.getLigacaoAgua() != null
									&& imovelParaSerGerado.getLigacaoAgua()
											.getId() != null
									&& imovelParaSerGerado.getLigacaoAgua()
											.getHidrometroInstalacaoHistorico() != null
									&& imovelParaSerGerado.getLigacaoAgua()
											.getHidrometroInstalacaoHistorico()
											.getId() != null) {
								ligacaoAgua = true;
							}
							if (imovelParaSerGerado
									.getHidrometroInstalacaoHistorico() != null
									&& imovelParaSerGerado
											.getHidrometroInstalacaoHistorico()
											.getId() != null) {
								ligacaoPoco = true;
							}

							MovimentoRoteiroEmpresa movimentoRoteiroEmpresa = new MovimentoRoteiroEmpresa();

							movimentoRoteiroEmpresa
									.setAnoMesMovimento(anoMesCorrente);

							if (imovelParaSerGerado.getQuadra() != null) {

								if (sistemaParametro
										.getIndicadorRoteiroEmpresa() == 1) {
									if (imovelParaSerGerado.getQuadra()
											.getRoteiroEmpresa() == null) {

										continue;
									}
								}

								movimentoRoteiroEmpresa
										.setRoteiroEmpresa(imovelParaSerGerado
												.getQuadra()
												.getRoteiroEmpresa());
								movimentoRoteiroEmpresa
										.setNumeroQuadra(imovelParaSerGerado
												.getQuadra().getNumeroQuadra());

								if (imovelParaSerGerado.getQuadra().getRota() != null) {
									// id do grupo de faturamento
									movimentoRoteiroEmpresa
											.setFaturamentoGrupo(imovelParaSerGerado
													.getQuadra().getRota()
													.getFaturamentoGrupo());
								}

								if (imovelParaSerGerado.getQuadra()
										.getRoteiroEmpresa() != null) {
									movimentoRoteiroEmpresa
											.setEmpresa(imovelParaSerGerado
													.getQuadra()
													.getRoteiroEmpresa()
													.getEmpresa());
								} else {
									movimentoRoteiroEmpresa
											.setEmpresa(imovelParaSerGerado
													.getQuadra().getRota()
													.getEmpresa());
								}
							}

							movimentoRoteiroEmpresa
									.setLocalidade(imovelParaSerGerado
											.getLocalidade());
							movimentoRoteiroEmpresa
									.setCodigoSetorComercial(imovelParaSerGerado
											.getSetorComercial().getCodigo());

							movimentoRoteiroEmpresa.setNumeroLoteImovel(Util
									.adicionarZerosEsquedaNumero(4, ""
											+ imovelParaSerGerado.getLote()));
							movimentoRoteiroEmpresa
									.setNumeroSubloteImovel(Util
											.adicionarZerosEsquedaNumero(3, ""
													+ imovelParaSerGerado
															.getSubLote()));

							movimentoRoteiroEmpresa
									.setImovelPerfil(imovelParaSerGerado
											.getImovelPerfil());

							// caso seja tipo ligação agua e poço cria a string
							// primeiro
							// com
							// tipo
							// ligação agua
							if (ligacaoAgua && ligacaoPoco) {

								if (imovelParaSerGerado.getLigacaoAgua() != null
										&& imovelParaSerGerado
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico() != null
										&& imovelParaSerGerado
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico()
												.getId() != null
										&& !imovelParaSerGerado
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico()
												.getId().equals("")) {

									movimentoRoteiroEmpresa
											.setMedicaoTipo(imovelParaSerGerado
													.getLigacaoAgua()
													.getHidrometroInstalacaoHistorico()
													.getMedicaoTipo());
								}
								// caso não seja
							} else {
								// caso seja tipo ligação agua cria a string com
								// tipo
								// ligação agua
								if (ligacaoAgua) {
									if (imovelParaSerGerado.getLigacaoAgua() != null
											&& imovelParaSerGerado
													.getLigacaoAgua()
													.getHidrometroInstalacaoHistorico() != null
											&& imovelParaSerGerado
													.getLigacaoAgua()
													.getHidrometroInstalacaoHistorico()
													.getId() != null
											&& !imovelParaSerGerado
													.getLigacaoAgua()
													.getHidrometroInstalacaoHistorico()
													.getId().equals("")) {
										movimentoRoteiroEmpresa
												.setMedicaoTipo(imovelParaSerGerado
														.getLigacaoAgua()
														.getHidrometroInstalacaoHistorico()
														.getMedicaoTipo());
									}
								} else {
									// caso seja tipo ligação poço cria a string
									// com
									// tipo
									// ligação poço
									if (ligacaoPoco) {
										if (imovelParaSerGerado
												.getHidrometroInstalacaoHistorico() != null
												&& imovelParaSerGerado
														.getHidrometroInstalacaoHistorico()
														.getId() != null
												&& !imovelParaSerGerado
														.getHidrometroInstalacaoHistorico()
														.getId().equals("")) {
											movimentoRoteiroEmpresa
													.setMedicaoTipo(imovelParaSerGerado
															.getHidrometroInstalacaoHistorico()
															.getMedicaoTipo());
										}
									}
								}
							}

							// Matricula do imóvel
							movimentoRoteiroEmpresa
									.setImovel(imovelParaSerGerado);

							// Perfil do imovel
							movimentoRoteiroEmpresa
									.setImovelPerfil(imovelParaSerGerado
											.getImovelPerfil());

							String nomeClienteUsuario = null;
							try {
								// Pesquisa o nome do cliente que tem o tipo de
								// relação
								// usuário.
								nomeClienteUsuario = repositorioClienteImovel
										.pesquisarNomeClientePorImovel(imovelParaSerGerado
												.getId());
							} catch (ErroRepositorioException e) {
								throw new ControladorException("erro.sistema",
										e);
							}
							// nome do cliente usuário
							movimentoRoteiroEmpresa
									.setNomeCliente(completaString(
											nomeClienteUsuario, 25));

							// Pesquisa o endereço do imovel passando o id
							String enderecoImovel = getControladorEndereco()
									.pesquisarEnderecoFormatado(
											imovelParaSerGerado.getId());
							if (enderecoImovel != null
									&& !enderecoImovel.equals("")) {
								// endereço do imóvel
								movimentoRoteiroEmpresa
										.setEnderecoImovel(completaString(
												enderecoImovel, 50));
							} else {
								movimentoRoteiroEmpresa
										.setEnderecoImovel(completaString("",
												50));
							}

							// Dados do Hidrometro

							// caso seja tipo ligação agua e poço cria a string
							// primeiro
							// com
							// tipo
							// ligação agua
							Short numeroDigitosHidrometro = null;
							StringBuilder dadosHidrometro = null;
							Integer capacidadeHidrometro = null;
							Integer marcaHidrometro = null;

							if (ligacaoAgua && ligacaoPoco) {

								Object[] dadosHidrometroNumeroLeitura = pesquisarDadosHidrometroTipoLigacaoAgua(imovelParaSerGerado);

								dadosHidrometro = (StringBuilder) dadosHidrometroNumeroLeitura[0];
								numeroDigitosHidrometro = (Short) dadosHidrometroNumeroLeitura[1];
								capacidadeHidrometro = (Integer) dadosHidrometroNumeroLeitura[2];
								marcaHidrometro = (Integer) dadosHidrometroNumeroLeitura[3];

								HidrometroCapacidade capacidade = new HidrometroCapacidade();
								capacidade.setId(capacidadeHidrometro);
								movimentoRoteiroEmpresa
										.setHidrometroCapacidade(capacidade);

								HidrometroMarca hidrometroMarca = new HidrometroMarca();
								hidrometroMarca.setId(marcaHidrometro);
								movimentoRoteiroEmpresa
										.setHidrometroMarca(hidrometroMarca);

								movimentoRoteiroEmpresa
										.setNumeroHidrometro(completaString(
												(String) dadosHidrometroNumeroLeitura[4],
												10));
								// caso não seja
							} else {
								// caso seja tipo ligação agua cria a string com
								// tipo
								// ligação agua
								if (ligacaoAgua) {

									Object[] dadosHidrometroNumeroLeitura = pesquisarDadosHidrometroTipoLigacaoAgua(imovelParaSerGerado);

									dadosHidrometro = (StringBuilder) dadosHidrometroNumeroLeitura[0];
									numeroDigitosHidrometro = (Short) dadosHidrometroNumeroLeitura[1];
									capacidadeHidrometro = (Integer) dadosHidrometroNumeroLeitura[2];
									marcaHidrometro = (Integer) dadosHidrometroNumeroLeitura[3];

									HidrometroCapacidade capacidade = new HidrometroCapacidade();
									capacidade.setId(capacidadeHidrometro);
									movimentoRoteiroEmpresa
											.setHidrometroCapacidade(capacidade);

									HidrometroMarca hidrometroMarca = new HidrometroMarca();
									hidrometroMarca.setId(marcaHidrometro);
									movimentoRoteiroEmpresa
											.setHidrometroMarca(hidrometroMarca);

									movimentoRoteiroEmpresa
											.setNumeroHidrometro(completaString(
													(String) dadosHidrometroNumeroLeitura[4],
													10));

									// caso não seja
								} else {
									// caso seja tipo ligação poço cria a string
									// com
									// tipo
									// ligação poço
									if (ligacaoPoco) {

										Object[] dadosHidrometroNumeroLeitura = pesquisarDadosHidrometroTipoPoco(imovelParaSerGerado);

										dadosHidrometro = (StringBuilder) dadosHidrometroNumeroLeitura[0];
										numeroDigitosHidrometro = (Short) dadosHidrometroNumeroLeitura[1];
										capacidadeHidrometro = (Integer) dadosHidrometroNumeroLeitura[2];
										marcaHidrometro = (Integer) dadosHidrometroNumeroLeitura[3];

										HidrometroCapacidade capacidade = new HidrometroCapacidade();
										capacidade.setId(capacidadeHidrometro);
										movimentoRoteiroEmpresa
												.setHidrometroCapacidade(capacidade);

										HidrometroMarca hidrometroMarca = new HidrometroMarca();
										hidrometroMarca.setId(marcaHidrometro);
										movimentoRoteiroEmpresa
												.setHidrometroMarca(hidrometroMarca);

										movimentoRoteiroEmpresa
												.setNumeroHidrometro(completaString(
														(String) dadosHidrometroNumeroLeitura[4],
														10));

										// caso não seja nem um nem outro então
										// pode
										// chamar
										// qualquer um dos métodos
										// pois os dois fazem a verificação e
										// retorna
										// strings
										// vazia e
										// a data cpm zeros
									} else {
										Object[] dadosHidrometroNumeroLeitura = pesquisarDadosHidrometroTipoPoco(imovelParaSerGerado);

										dadosHidrometro = (StringBuilder) dadosHidrometroNumeroLeitura[0];
										numeroDigitosHidrometro = (Short) dadosHidrometroNumeroLeitura[1];
										capacidadeHidrometro = (Integer) dadosHidrometroNumeroLeitura[2];
										marcaHidrometro = (Integer) dadosHidrometroNumeroLeitura[3];

										HidrometroCapacidade capacidade = new HidrometroCapacidade();
										capacidade.setId(capacidadeHidrometro);
										movimentoRoteiroEmpresa
												.setHidrometroCapacidade(capacidade);

										HidrometroMarca hidrometroMarca = new HidrometroMarca();
										hidrometroMarca.setId(marcaHidrometro);
										movimentoRoteiroEmpresa
												.setHidrometroMarca(hidrometroMarca);

										movimentoRoteiroEmpresa
												.setNumeroHidrometro(completaString(
														(String) dadosHidrometroNumeroLeitura[4],
														10));
									}
								}
							}

							if (imovelParaSerGerado
									.getHidrometroInstalacaoHistorico() != null) {

								movimentoRoteiroEmpresa
										.setHidrometroLocalInstalacao(imovelParaSerGerado
												.getHidrometroInstalacaoHistorico()
												.getHidrometroLocalInstalacao());

								movimentoRoteiroEmpresa
										.setDataInstalacaoHidrometro(imovelParaSerGerado
												.getHidrometroInstalacaoHistorico()
												.getDataInstalacao());

								movimentoRoteiroEmpresa
										.setHidrometroProtecao(imovelParaSerGerado
												.getHidrometroInstalacaoHistorico()
												.getHidrometroProtecao());

							}

							if (imovelParaSerGerado.getLigacaoAgua()
									.getHidrometroInstalacaoHistorico() != null) {

								movimentoRoteiroEmpresa
										.setHidrometroLocalInstalacao(imovelParaSerGerado
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico()
												.getHidrometroLocalInstalacao());

								movimentoRoteiroEmpresa
										.setDataInstalacaoHidrometro(imovelParaSerGerado
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico()
												.getDataInstalacao());

								movimentoRoteiroEmpresa
										.setHidrometroProtecao(imovelParaSerGerado
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico()
												.getHidrometroProtecao());

							}

							// id da ligacao agua situação
							if (imovelParaSerGerado.getLigacaoAguaSituacao() != null
									&& imovelParaSerGerado
											.getLigacaoAguaSituacao().getId() != null) {
								// Situação da ligação de agua
								movimentoRoteiroEmpresa
										.setLigacaoAguaSituacao(imovelParaSerGerado
												.getLigacaoAguaSituacao());
							}

							// id da ligacao esgoto situação
							if (imovelParaSerGerado.getLigacaoEsgotoSituacao() != null
									&& imovelParaSerGerado
											.getLigacaoEsgotoSituacao().getId() != null) {
								// Situação de ligação esgoto
								movimentoRoteiroEmpresa
										.setLigacaoEsgotoSituacao(imovelParaSerGerado
												.getLigacaoEsgotoSituacao());
							}

							// pega as descrições das categorias do imovel

							Categoria categoria = getControladorImovel()
									.obterDescricoesCategoriaImovel(
											imovelParaSerGerado);

							// quantidade de economias
							movimentoRoteiroEmpresa
									.setDescricaoAbreviadaCategoriaImovel(categoria
											.getDescricaoAbreviada());

							// [UC0086 - Obter quantidade de economias]
							int quantidadeEconomias = getControladorImovel()
									.obterQuantidadeEconomias(
											imovelParaSerGerado);
							// quantidade de economias
							movimentoRoteiroEmpresa
									.setQuantidadeEconomias(new Integer(
											quantidadeEconomias).shortValue());

							// Leitura anterior
							Integer anoMesAnterior = Util
									.subtrairData(anoMesCorrente);
							String leituraAnterior = null;
							Integer idMedicaoTipo = null;
							MedicaoHistorico medicaoHistorico = null;
							Object[] retorno = pesquisaLeituraAnterior(
									ligacaoAgua, ligacaoPoco, anoMesAnterior,
									imovelParaSerGerado);
							// verifica se a leitura anterior é diferente de
							// nula
							if (retorno[0] != null) {
								leituraAnterior = retorno[0].toString();
							}
							// verifica se a leitura situação atual é diferente
							// de
							// nula
							if (retorno[1] != null) {
								medicaoHistorico = (MedicaoHistorico) retorno[1];
							}
							// verifica se o id da medição tipo é diferente de
							// nula
							if (retorno[2] != null) {
								idMedicaoTipo = (Integer) retorno[2];
							}

							// verifica se a leitura anterior é diferente de
							// nula
							// para
							// ser
							// jogado no arquivo
							// txt
							if (leituraAnterior != null) {
								movimentoRoteiroEmpresa
										.setNumeroLeituraAnterior(new Integer(
												leituraAnterior));

								/*
								 * movimentoRoteiroEmpresa
								 * .setNumeroLeituraAnterior(new Integer(
								 * Criptografia .encrypt(leituraAnterior)));
								 

								// caso contrario coloca a string com zeros
							}

							// Faixa de leitura esperada

							/*Object[] faixaInicialFinal = pesquisarFaixaEsperadaOuFalsaCelular(
									imovelParaSerGerado, dadosHidrometro,
									leituraAnterior, medicaoHistorico,
									idMedicaoTipo, sistemaParametro,
									hidrometroSelecionado,
									numeroDigitosHidrometro);

							hidrometroSelecionado = Boolean
									.parseBoolean(faixaInicialFinal[1]
											.toString());

							boolean faixaFalsaLeitura = Boolean
									.parseBoolean(faixaInicialFinal[2]
											.toString());

							int faixaInicialEsperada = 0;
							int faixaFinalEsperada = 0;

							if (faixaFalsaLeitura) {
								faixaInicialEsperada = Integer
										.parseInt(faixaInicialFinal[3]
												.toString());

								faixaFinalEsperada = Integer
										.parseInt(faixaInicialFinal[4]
												.toString());
							}

							movimentoRoteiroEmpresa
									.setNumeroFaixaLeituraEsperadaInicial(faixaInicialEsperada);
							movimentoRoteiroEmpresa
									.setNumeroFaixaLeituraEsperadaFinal(faixaFinalEsperada);

							movimentoRoteiroEmpresa
									.setUltimaAlteracao(new Date());

							movimentoRoteiroEmpresa.setRota(imovelParaSerGerado
									.getQuadra().getRota());
*/
							/**
							 * Obtem a colecao economias por categoria
							 * 
							 */

							/*Collection colecaoSubCategoria = this
									.getControladorImovel()
									.obterQuantidadeEconomiasSubCategoria(
											imovelParaSerGerado.getId());

							// 1.10.1 - SubCategoria 01
							// 1.10.2 - Quantidade 01
							// 1.10.3 - SubCategoria 02
							// 1.10.4 - Quantidade 02
							if (colecaoSubCategoria != null
									&& !colecaoSubCategoria.isEmpty()) {

								Iterator itera = colecaoSubCategoria.iterator();

								for (int i = 0; i < 2; i++) {

									while (itera.hasNext()) {

										Subcategoria subcategoria = (Subcategoria) itera
												.next();

										// tipoEconomia = categoria_id(1
										// posição) +
										// subcategoria_codigo(3 posições)

										if (i == 0) {
											// Código da subcategoria do imovel
											movimentoRoteiroEmpresa
													.setCodigoSubcategoria1(subcategoria
															.getCodigo());
											// qtdeEconomia
											movimentoRoteiroEmpresa
													.setQuantidadeEconomias(subcategoria
															.getQuantidadeEconomias()
															.shortValue());
										} else {

											// Código da 2 subcategoria do
											// imovel
											movimentoRoteiroEmpresa
													.setCodigoSubcategoria2(subcategoria
															.getCodigo());
											// qtdeEconomia
											movimentoRoteiroEmpresa
													.setQuantidadeEconomias2(subcategoria
															.getQuantidadeEconomias()
															.shortValue());
										}

									}
								}
							}
							// 1.11 - Consumo
							Integer numeroConsumoFaturadoMes = null;

							Integer anoMesFaturamento = sistemaParametro
									.getAnoMesFaturamento();

							try {

								// 1.11.1 - Consumo 01
								int anoMesSubtraido = Util.subtrairMesDoAnoMes(
										anoMesFaturamento, 6);

								numeroConsumoFaturadoMes = repositorioMicromedicao
										.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
												imovelParaSerGerado.getId(),
												anoMesSubtraido,
												LigacaoTipo.LIGACAO_AGUA);

								// caso o numero consumo faturado do mes for
								// diferente de nulo
								if (numeroConsumoFaturadoMes != null) {

									movimentoRoteiroEmpresa
											.setNumeroConsumoFaturadoMenos6Meses(numeroConsumoFaturadoMes);

								}

								// 1.11.2 - Consumo 02
								anoMesSubtraido = Util.subtrairMesDoAnoMes(
										anoMesFaturamento, 5);

								numeroConsumoFaturadoMes = repositorioMicromedicao
										.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
												imovelParaSerGerado.getId(),
												anoMesSubtraido,
												LigacaoTipo.LIGACAO_AGUA);

								// caso o numero consumo faturado do mes for
								// diferente de nulo
								if (numeroConsumoFaturadoMes != null) {

									movimentoRoteiroEmpresa
											.setNumeroConsumoFaturadoMenos5Meses(numeroConsumoFaturadoMes);

								}

								// 1.11.3 - Consumo 03
								anoMesSubtraido = Util.subtrairMesDoAnoMes(
										anoMesFaturamento, 4);

								numeroConsumoFaturadoMes = repositorioMicromedicao
										.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
												imovelParaSerGerado.getId(),
												anoMesSubtraido,
												LigacaoTipo.LIGACAO_AGUA);

								// caso o numero consumo faturado do mes for
								// diferente de nulo
								if (numeroConsumoFaturadoMes != null) {

									movimentoRoteiroEmpresa
											.setNumeroConsumoFaturadoMenos4Meses(numeroConsumoFaturadoMes);

								}

							} catch (ErroRepositorioException e) {

								throw new ControladorException("erro.sistema",
										e);

							}

							// 1.12 - Condição
							String condicao = null;

							try {
								condicao = this.repositorioMicromedicao
										.obterDescricaoConsumoTipo(
												imovelParaSerGerado.getId(),
												LigacaoTipo.LIGACAO_AGUA);

							} catch (ErroRepositorioException e) {

								throw new ControladorException("erro.sistema",
										e);

							}

							if (condicao != null) {
								movimentoRoteiroEmpresa
										.setDescricaoConsumoTipo(Util
												.completaStringComEspacoAEsquerda(
														"" + condicao, 15));
							}

							// 1.13 - Número do Lacre
							if (imovelParaSerGerado.getLigacaoAgua()
									.getNumeroLacre() != null) {
								movimentoRoteiroEmpresa
										.setNumeroLacreLigacaoAgua(Util
												.completaStringComEspacoAEsquerda(
														""
																+ imovelParaSerGerado
																		.getImovelPrincipal()
																		.getLigacaoAgua()
																		.getNumeroLacre(),
														6));
							}

							// 1.14 - Sequencial da Rota

							movimentoRoteiroEmpresa
									.setNumeroSequencialRota(imovelParaSerGerado
											.getNumeroSequencialRota());

							// 1.15 - Consumo 04

							int anoMesSubtraido = Util.subtrairMesDoAnoMes(
									anoMesFaturamento, 3);

							numeroConsumoFaturadoMes = repositorioMicromedicao
									.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
											imovelParaSerGerado.getId(),
											anoMesSubtraido,
											LigacaoTipo.LIGACAO_AGUA);

							// caso o numero consumo faturado do mes for
							// diferente
							// de nulo
							if (numeroConsumoFaturadoMes != null) {

								movimentoRoteiroEmpresa
										.setNumeroConsumoFaturadoMenos3Meses(numeroConsumoFaturadoMes);

							}

							// 1.16 - Consumo 05
							anoMesSubtraido = Util.subtrairMesDoAnoMes(
									anoMesFaturamento, 2);

							numeroConsumoFaturadoMes = repositorioMicromedicao
									.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
											imovelParaSerGerado.getId(),
											anoMesSubtraido,
											LigacaoTipo.LIGACAO_AGUA);

							// caso o numero consumo faturado do mes for
							// diferente
							// de nulo
							if (numeroConsumoFaturadoMes != null) {

								movimentoRoteiroEmpresa
										.setNumeroConsumoFaturadoMenos2Meses(numeroConsumoFaturadoMes);

							}

							// 1.17 - Consumo 06
							anoMesSubtraido = Util.subtrairMesDoAnoMes(
									anoMesFaturamento, 1);

							numeroConsumoFaturadoMes = repositorioMicromedicao
									.obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
											imovelParaSerGerado.getId(),
											anoMesSubtraido,
											LigacaoTipo.LIGACAO_AGUA);

							// caso o numero consumo faturado do mes for
							// diferente
							// de nulo
							if (numeroConsumoFaturadoMes != null) {

								movimentoRoteiroEmpresa
										.setNumeroConsumoFaturadoMenos1Mes(numeroConsumoFaturadoMes);

							}

							// 1.18 - Consumo Medio

							Integer numeroConsumoMedio = null;
							try {
								numeroConsumoMedio = this.repositorioMicromedicao
										.pesquisarNumeroConsumoMedioImovel(
												imovelParaSerGerado.getId(),
												anoMesCorrente,
												LigacaoTipo.LIGACAO_AGUA);
							} catch (ErroRepositorioException e) {
								throw new ControladorException("erro.sistema",
										e);
							}

							if (numeroConsumoMedio != null) {
								movimentoRoteiroEmpresa
										.setNumeroConsumoMedio(numeroConsumoMedio);
							}

							// 1.19 -Rota

							movimentoRoteiroEmpresa
									.setCodigoRota(imovelParaSerGerado
											.getQuadra().getRota().getCodigo());

							// 1.20 - Codigo Logradouro

							movimentoRoteiroEmpresa
									.setIdLogradouro(imovelParaSerGerado
											.getLogradouroBairro()
											.getLogradouro().getId());

							// 1.21 - Nome do Logradouro

							movimentoRoteiroEmpresa
									.setNomeLogradouro(imovelParaSerGerado
											.getLogradouroBairro()
											.getLogradouro().getNome());

							// 1.22 - Numero do Imovel

							movimentoRoteiroEmpresa
									.setNumeroImovel(imovelParaSerGerado
											.getNumeroImovel());

							// 1.23 - Complemento

							movimentoRoteiroEmpresa
									.setComplementoEndereco(imovelParaSerGerado
											.getComplementoEndereco());

							// 1.24 - Nome do Bairro

							movimentoRoteiroEmpresa
									.setNomeBairro(imovelParaSerGerado
											.getLogradouroBairro().getBairro()
											.getNome());

							// 1.25 - Id da Categoria

							Categoria categoria1 = (Categoria) getControladorImovel()
									.obterPrincipalCategoriaImovel(
											imovelParaSerGerado.getId());

							movimentoRoteiroEmpresa
									.setIdCategoriaImovel(categoria1.getId());
							// 1.1 Incricao do Imovel
							movimentoRoteiroEmpresa
									.setInscricaoImovel(imovelParaSerGerado
											.getInscricaoFormatada());

							// Id gerencia Regional
							movimentoRoteiroEmpresa
									.setIdGereciaRegional(imovelParaSerGerado
											.getLocalidade()
											.getGerenciaRegional().getId());

							movimentoRoteiroEmpresa.setLeituraTipo(rota
									.getLeituraTipo());
							
							

							getControladorUtil().inserir(
									movimentoRoteiroEmpresa);

							imovelParaSerGerado = null;

						}
					}
				}

				// Encerra a unidade de Faturamento

				getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
						idUnidadeIniciada, false);

			} catch (Exception e) { // Este catch serve para interceptar
				// qualquer exceção que o processo batch
				// venha a lançar e garantir que a unidade
				// de processamento do batch será atualizada
				// com o erro ocorrido
				e.printStackTrace();

				getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
						idUnidadeIniciada, true);

				throw new EJBException(e);
			}
		}
	}*/

	/**
	 * [UC0083] - Gerar Dados para Leitura Empresa 
	 * 
	 * Author: Rômulo Aurélio 
	 * 
	 * @param objetosImoveis
	 * @return
	 */

	public Collection verificarImoveisParaSerGerados(Collection objetosImoveis) {

		// Instancia uma coleção que será usada para gerar o arquivo
		// txt.
		Collection<Imovel> imoveisParaSerGerados = new ArrayList<Imovel>();

		Iterator imovelIterator = objetosImoveis.iterator();
		while (imovelIterator.hasNext()) {
			// Recupera o imovel da coleção
			Imovel imovel = (Imovel) imovelIterator.next();
			// variavel responsável para entrar em uma das 4 condicões
			// abaixo

			boolean achouImovel = false;

			/**
			 * [SF0002] - Verificar situação especial de faturamento Autor:
			 * Sávio Luiz Data: 21/12/2005
			 */
			if (!achouImovel) {
			
				// Se for ligado ou cortado então
				// Verifica se a ligação agua é diferente de
				// nulo
				// se for verifica se o id da ligação agua é
				// igual
				// ao id
				// do
				// imovel e
				// se o id do histórico da instalação do
				// hidrometro
				// é
				// diferente de
				// null
				if (imovel.getLigacaoAgua() != null
						&& imovel.getLigacaoAgua().getId() != null
						&& (imovel.getLigacaoAgua().getId().equals(
								imovel.getId())
								&& imovel.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico() != null && imovel
								.getLigacaoAgua()
								.getHidrometroInstalacaoHistorico().getId() != null)) {

					imoveisParaSerGerados.add(imovel);
					achouImovel = true;

				}
				
			}
			
			if (!achouImovel) {
				
				// Verifica se o id do hidrometro historico é
				// diferente de
				// nulo na tabela imovel
				if (imovel.getHidrometroInstalacaoHistorico() != null
						&& imovel.getHidrometroInstalacaoHistorico().getId() != null) {
					imoveisParaSerGerados.add(imovel);
					achouImovel = true;
				}
				
			}
			
		}

		return imoveisParaSerGerados;

	}
	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * @author Rômulo Aurélio
	 * @throws ControladorException 
	 * @date 07/07/2008
	 */
	
	public Collection pesquisarImoveisPorRota(Rota rota,
			SistemaParametro sistemaParametro) throws ControladorException {
		
		Collection imoveisPorRota = new ArrayList();

		try {

			imoveisPorRota = repositorioMicromedicao
					.pesquisarImoveisPorRotaCaema(rota, 
							sistemaParametro.getNomeAbreviadoEmpresa());

		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imoveisPorRota;
	}

	
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * @author Rômulo Aurélio
	 * @date 07/07/2008
	 */
	@SuppressWarnings("unchecked")
	public Collection retornaImoveisPorRota(Rota rota,
			SistemaParametro sistemaParametro) throws ControladorException {

		/**
		 * [SB0004]-Recuperar Dados para inclusao na Tabela
		 */

		//		 inicializa uma coleção de imoveis
		Collection objetosImoveis = new ArrayList();
		// cria uma coleção de imóvel por rota
		Collection imoveisPorRota = null;

		imoveisPorRota = this.pesquisarImoveisPorRota(rota, sistemaParametro);

		if (imoveisPorRota != null && !imoveisPorRota.isEmpty()) {

			Iterator imovelporRotaIterator = imoveisPorRota.iterator();

			while (imovelporRotaIterator.hasNext()) {
				// cria um array de objetos para pegar os parametros
				// de
				// retorno da pesquisa
				Object[] arrayImoveisPorRota = (Object[]) imovelporRotaIterator
						.next();

				// instancia um imóvel
				Imovel imovel = new Imovel();
				if (arrayImoveisPorRota[0] != null) {
					// seta o id no imovel
					imovel.setId((Integer) arrayImoveisPorRota[0]);
				}

				if (arrayImoveisPorRota[1] != null) {
					// instancia uma localidade para ser setado no
					// imóvel
					Localidade localidade = new Localidade();
					localidade.setId((Integer) arrayImoveisPorRota[1]);
					imovel.setLocalidade(localidade);
				}

				if (arrayImoveisPorRota[2] != null) {
					// instancia um setor comercial para ser setado
					// no
					// imóvel
					SetorComercial setorComercial = new SetorComercial();
					setorComercial.setCodigo(Integer
							.parseInt(arrayImoveisPorRota[2].toString()));
					imovel.setSetorComercial(setorComercial);
				}
				Quadra quadra = new Quadra();
				if (arrayImoveisPorRota[3] != null) {
					// instancia uma quadra para ser setado no
					// imóvel

					Integer numeroQuadra = (Integer) arrayImoveisPorRota[3];
					quadra.setNumeroQuadra(numeroQuadra);
					imovel.setQuadra(quadra);
				}

				if (arrayImoveisPorRota[4] != null) {
					// seta o lote no imóvel
					imovel.setLote(Short.parseShort(arrayImoveisPorRota[4]
							.toString()));
				}

				if (arrayImoveisPorRota[5] != null) {
					// seta o lote no imóvel
					imovel.setSubLote(Short.parseShort(arrayImoveisPorRota[5]
							.toString()));
				}
				if (arrayImoveisPorRota[6] != null) {
					// instancia uma imovel perfil para ser setado
					// no
					// imóvel
					ImovelPerfil imovelPerfil = new ImovelPerfil();
					imovelPerfil.setId((Integer) arrayImoveisPorRota[6]);
					imovel.setImovelPerfil(imovelPerfil);
				}

				LigacaoAgua ligacaoAgua = new LigacaoAgua();
				if (arrayImoveisPorRota[7] != null) {
					// instancia uma ligação agua para ser setado no
					// imóvel

					ligacaoAgua.setId((Integer) arrayImoveisPorRota[7]);
				}
				// instancia um hidrometro instalação historico para
				// ser
				// colocado na ligacao agua

				if (arrayImoveisPorRota[30] != null) {

					HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoLigacaoAgua = (HidrometroInstalacaoHistorico) arrayImoveisPorRota[30];
					MedicaoTipo medicaoTipo = new MedicaoTipo();
					medicaoTipo.setId((Integer) arrayImoveisPorRota[26]);
					hidrometroInstalacaoHistoricoLigacaoAgua
							.setMedicaoTipo(medicaoTipo);
					ligacaoAgua
							.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistoricoLigacaoAgua);

				}
				imovel.setLigacaoAgua(ligacaoAgua);

				// //instancia um hidrometro instalação historico
				// para
				// ser colocado no imovel

				if (arrayImoveisPorRota[31] != null) {

					HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoImovel = (HidrometroInstalacaoHistorico) arrayImoveisPorRota[31];
					MedicaoTipo medicaoTipo = new MedicaoTipo();
					medicaoTipo.setId((Integer) arrayImoveisPorRota[27]);
					hidrometroInstalacaoHistoricoImovel
							.setMedicaoTipo(medicaoTipo);
					imovel
							.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistoricoImovel);

				}
				// instancia a rota
				Rota rotaImovel = new Rota();

				if (arrayImoveisPorRota[10] != null) {
					// seta o id da rota
					rotaImovel.setId((Integer) arrayImoveisPorRota[10]);
				}
				if (arrayImoveisPorRota[11] != null) {
					// seta o indicador fiscalizador suprimido na
					// rota
					rotaImovel.setIndicadorFiscalizarSuprimido(Short
							.parseShort(arrayImoveisPorRota[11].toString()));
				}
				if (arrayImoveisPorRota[12] != null) {
					// seta o indicador fiscalizador cortado na rota
					rotaImovel.setIndicadorFiscalizarCortado(Short
							.parseShort(arrayImoveisPorRota[12].toString()));
				}
				if (arrayImoveisPorRota[13] != null) {
					// seta o indicador gerar fiscalizacao na rota
					rotaImovel.setIndicadorGerarFiscalizacao(Short
							.parseShort(arrayImoveisPorRota[13].toString()));
				}
				if (arrayImoveisPorRota[14] != null) {
					// seta o indicador fgerar falsa faixa na rota
					rotaImovel.setIndicadorGerarFalsaFaixa(Short
							.parseShort(arrayImoveisPorRota[14].toString()));
				}
				if (arrayImoveisPorRota[15] != null) {
					// seta o percentual geracao fiscalizacao na
					// rota
					rotaImovel
							.setPercentualGeracaoFiscalizacao((BigDecimal) (arrayImoveisPorRota[15]));
				}
				if (arrayImoveisPorRota[16] != null) {
					// seta o percentual geracao faixa falsa na rota
					rotaImovel
							.setPercentualGeracaoFaixaFalsa((BigDecimal) (arrayImoveisPorRota[16]));
				}
				
				if(rota.getLeiturista() != null ){
					rotaImovel.setLeiturista(rota.getLeiturista());
				}
				
				// instancia a empresa
				Empresa empresa = new Empresa();
				if (arrayImoveisPorRota[17] != null) {

					// seta o id na empresa
					empresa.setId((Integer) arrayImoveisPorRota[17]);

				}
				if (arrayImoveisPorRota[18] != null) {

					// seta a descrição abreviada na empresa
					empresa.setDescricaoAbreviada(arrayImoveisPorRota[18]
							.toString());

				}
				if (arrayImoveisPorRota[19] != null) {

					// seta email da empresa
					empresa.setEmail(arrayImoveisPorRota[19].toString());

				}
				if (arrayImoveisPorRota[28] != null) {

					// seta email da empresa
					empresa.setDescricao(arrayImoveisPorRota[28].toString());

				}
				// seta a empresa na rota
				rotaImovel.setEmpresa(empresa);
				// instancia o faturamento
				FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
				if (arrayImoveisPorRota[20] != null) {
					// seta o id no faturamentGrupo
					faturamentoGrupo.setId((Integer) arrayImoveisPorRota[20]);

				}
				if (arrayImoveisPorRota[21] != null) {
					// seta o descrição no faturamentGrupo
					faturamentoGrupo
							.setDescricao((String) arrayImoveisPorRota[21]);
				}
				// seta o faturamento na rota
				rotaImovel.setFaturamentoGrupo(faturamentoGrupo);
				if (arrayImoveisPorRota[22] != null) {
					// instancia a ligação esgoto situação
					LeituraTipo leituraTipo = new LeituraTipo();
					// seta o id na ligação esgoto situação
					leituraTipo.setId((Integer) arrayImoveisPorRota[22]);
					// seta a ligação esgoto situação no imovel
					rotaImovel.setLeituraTipo(leituraTipo);
				}

				// seta a rota na quadra
				quadra.setRota(rotaImovel);

				// seta o roteiro empresa na quadra
				quadra
						.setRoteiroEmpresa((RoteiroEmpresa) arrayImoveisPorRota[29]);

				// seta a quadra no imovel
				imovel.setQuadra(quadra);
				if (arrayImoveisPorRota[23] != null) {
					// instancia a ligação agua situação
					LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
					// seta o id na ligação agua situação
					ligacaoAguaSituacao
							.setId((Integer) arrayImoveisPorRota[23]);
					// seta a ligação agua situação no imovel
					imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
				}
				if (arrayImoveisPorRota[24] != null) {
					// instancia a ligação esgoto situação
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
					// seta o id na ligação esgoto situação
					ligacaoEsgotoSituacao
							.setId((Integer) arrayImoveisPorRota[24]);
					// seta a ligação esgoto situação no imovel
					imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
				}

				if (arrayImoveisPorRota[25] != null) {
					// instancia o faturamento situacao tipo
					FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
					// seta o id no faturamento situacao tipo
					faturamentoSituacaoTipo
							.setIndicadorParalisacaoLeitura((Short) arrayImoveisPorRota[25]);
					// seta a ligação esgoto situação no imovel
					imovel.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
				}

				if (arrayImoveisPorRota[32] != null) {
					imovel.getQuadra().getRota().getEmpresa().setId(
							(Integer) arrayImoveisPorRota[32]);
				}

				if (arrayImoveisPorRota[33] != null) {
					imovel.getLigacaoAguaSituacao()
							.setIndicadorFaturamentoSituacao(
									(Short) arrayImoveisPorRota[33]);
				}

				if (arrayImoveisPorRota[34] != null) {
					imovel.getLigacaoEsgotoSituacao()
							.setIndicadorFaturamentoSituacao(
									(Short) arrayImoveisPorRota[34]);
				}

				if (arrayImoveisPorRota[35] != null) {

					LogradouroBairro logradouroBairro = new LogradouroBairro();

					logradouroBairro.setId((Integer) arrayImoveisPorRota[35]);

					imovel.setLogradouroBairro(logradouroBairro);
				}

				if (arrayImoveisPorRota[36] != null) {

					Logradouro logradouro = new Logradouro();

					logradouro.setId((Integer) arrayImoveisPorRota[36]);

					imovel.getLogradouroBairro().setLogradouro(logradouro);
				}

				if (arrayImoveisPorRota[37] != null) {

					imovel.getLogradouroBairro().getLogradouro().setNome(
							(String) arrayImoveisPorRota[37]);
				}

				if (arrayImoveisPorRota[38] != null) {
					Bairro bairro = new Bairro();
					bairro.setNome((String) arrayImoveisPorRota[38]);

					imovel.getLogradouroBairro().setBairro(bairro);
				}
				if (arrayImoveisPorRota[39] != null) {
					imovel.setNumeroImovel(completaString(
							(String) arrayImoveisPorRota[39], 5));
				}

				if (arrayImoveisPorRota[40] != null) {
					imovel
							.setNumeroSequencialRota((Integer) arrayImoveisPorRota[40]);
				}
				if (arrayImoveisPorRota[41] != null) {
					ligacaoAgua.setNumeroLacre(completaString(
							(String) arrayImoveisPorRota[41], 6));
				}
				if (arrayImoveisPorRota[42] != null) {
					imovel.setComplementoEndereco(
							(String) arrayImoveisPorRota[42]);
				}
				if (arrayImoveisPorRota[43] != null) {
					GerenciaRegional gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId((Integer) arrayImoveisPorRota[43]);
					imovel.getLocalidade()
							.setGerenciaRegional(gerenciaRegional);
				}
				if (arrayImoveisPorRota[44] != null) {

					rotaImovel.setCodigo((Short) arrayImoveisPorRota[44]);
				}

				// adiciona na coleção de imoveis
				objetosImoveis.add(imovel);
				arrayImoveisPorRota = null;

			}

		}

		imoveisPorRota.clear();
		imoveisPorRota = null;
		return objetosImoveis;
	}
	
	

}
