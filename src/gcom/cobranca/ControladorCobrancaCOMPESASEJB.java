package gcom.cobranca;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.bean.CalcularValorDataVencimentoAnteriorHelper;
import gcom.cobranca.bean.EmissaoDocumentoCobrancaHelper;
import gcom.cobranca.bean.EmitirDocumentoCobrancaHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.SessionBean;

import br.com.danhil.BarCode.Interleaved2of5;

/**
 * Controlador Cobranca COMPESA
 *
 * @author Raphael Rossiter
 * @date 26/06/2007
 */
public class ControladorCobrancaCOMPESASEJB extends ControladorCobranca implements SessionBean {

	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// MÉTODOS EXCLUSIVOS DA COMPESA
	//==============================================================================================================
	
	/**
	 * Este caso de uso permite a emissão de um ou mais documentos de cobrança
	 * 
	 * [UC0476] Emitir Documento de Cobrança - Ordem de Corte
	 * 
	 * @author Ivan Sergio
	 * @data 21/05/2009
	 * 
	 * @param
	 * @return void
	 */
	public void emitirDocumentoCobrancaOrdemCorte(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			Date dataAtualPesquisa, CobrancaAcao acaoCobranca,
			CobrancaGrupo grupoCobranca, CobrancaCriterio cobrancaCriterio)
			throws ControladorException {
		
		if (acaoCobranca.getId().equals(CobrancaAcao.CORTE_ADMINISTRATIVO) ||
            acaoCobranca.getId().equals(CobrancaAcao.CORTE_ADMINISTRATIVO_LIGADO_A_REVELIA) ||
            acaoCobranca.getId().equals(CobrancaAcao.SUPRESSAO_PARCIAL) || 
            acaoCobranca.getId().equals(CobrancaAcao.SUPRESSAO_TOTAL) ||
            acaoCobranca.getId().equals(CobrancaAcao.FISCALIZACAO_SITUACAO_ESGOTO)) {
			// [UC0349 Emitir Documento fr Cobrança - AvisoCorte]
			super.emitirDocumentoCobrancaOrdemCorte(
					cobrancaAcaoAtividadeCronograma,
					cobrancaAcaoAtividadeComando,
					dataAtualPesquisa,
					acaoCobranca,
					grupoCobranca,
					cobrancaCriterio);
		} else if (acaoCobranca.getId().equals(CobrancaAcao.CORTE_FISICO) ||
				   acaoCobranca.getId().equals(CobrancaAcao.CORTE_FISICO_LIGADO_A_REVELIA) ||
				   acaoCobranca.getId().equals(CobrancaAcao.CORTE_FISICO_PUBLICO) ||
				   acaoCobranca.getId().equals(CobrancaAcao.RECORTE)) {
			// [UC0640] Gerar TXT Corte Fisico COMPESA
			this.emitirDocumentoCobrancaCorteFisico(
					cobrancaAcaoAtividadeCronograma,
					cobrancaAcaoAtividadeComando,
					dataAtualPesquisa,
					acaoCobranca,
					grupoCobranca,
					cobrancaCriterio);
		}
	}
	
	/**
	 * [UC0640] Gerar TXT Corte Fisico COMPESA
	 * 
	 * @author Ivan Sergio, Vivianne Sousa
	 * @data 21/05/2009, 23/02/2010
	 * 
	 * @param
	 * @return void
	 */
	public void gerarDocumentoCobrancaImpressaoCorteFisico(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			Date dataAtualPesquisa, CobrancaAcao acaoCobranca,
			CobrancaGrupo grupoCobranca, CobrancaCriterio cobrancaCriterio)
			throws ControladorException {

		try {
		
			boolean flagFimPesquisa = false;
			final int quantidadeCobrancaDocumento = 1000;
			int quantidadeCobrancaDocumentoInicio = 0;
			int sequencialImpressao = 0;
	
			System.out.println("****************************************");
			System.out.println("ENTROU NO GERAR TXT CORTE FISICO COMPESA");
			System.out.println("****************************************");
	
			Integer idCronogramaAtividadeAcaoCobranca = null;
			Integer idComandoAtividadeAcaoCobranca = null;
			Integer idAcaoCobranca = null;
			
			if (cobrancaAcaoAtividadeCronograma != null && cobrancaAcaoAtividadeCronograma.getId() != null) {
				idCronogramaAtividadeAcaoCobranca = cobrancaAcaoAtividadeCronograma.getId();
			}
			if (cobrancaAcaoAtividadeComando != null && cobrancaAcaoAtividadeComando.getId() != null) {
				idComandoAtividadeAcaoCobranca = cobrancaAcaoAtividadeComando.getId();
			}
			if (acaoCobranca != null && acaoCobranca.getId() != null) {
				idAcaoCobranca = acaoCobranca.getId();
			}
			
			while (!flagFimPesquisa) {
				
				
				// map que armazena o sequencial e o numero da
				// conta para no final atualizar todos os sequencias
				Map<Integer, Integer> mapAtualizaSequencial = new HashMap();
	
				Collection colecaoEmitirDocumentoCobranca = repositorioCobranca.
				pesquisarCobrancaDocumentoOrdemCorte(idCronogramaAtividadeAcaoCobranca,
				idComandoAtividadeAcaoCobranca,	dataAtualPesquisa, idAcaoCobranca,	
				quantidadeCobrancaDocumentoInicio);
					
				if (colecaoEmitirDocumentoCobranca != null && !colecaoEmitirDocumentoCobranca.isEmpty()) {
	
					System.out.println("***************************************");
					System.out.println("QUANTIDADE DE COBRANÇA:" + colecaoEmitirDocumentoCobranca.size());
					System.out.println("***************************************");
	
					Collection colecaoCobrancaDocumentoItem = null;
	
					if (colecaoEmitirDocumentoCobranca.size() < quantidadeCobrancaDocumento) {
						flagFimPesquisa = true;
					} else {
						quantidadeCobrancaDocumentoInicio = quantidadeCobrancaDocumentoInicio + 1000;
					}
	
					Iterator iteratorColecaoCobrancaDocumento = colecaoEmitirDocumentoCobranca.iterator();
					EmitirDocumentoCobrancaHelper emitirDocumentoCobrancaHelper = null;
				
					while (iteratorColecaoCobrancaDocumento.hasNext()) {
						StringBuilder cobrancaDocumentoTxt = new StringBuilder();
						emitirDocumentoCobrancaHelper = null;
						String nomeCliente = null;
						emitirDocumentoCobrancaHelper = (EmitirDocumentoCobrancaHelper) iteratorColecaoCobrancaDocumento.next();
	
						/*
						 * Estes objetos auxiliarão na formatação da
						 * inscrição que será composta por informações do
						 * documento de cobrança e do imóvel a ele associado
						 */
						Imovel inscricao = null;
						SetorComercial setorComercialInscricao = null;
						Quadra quadraInscricao = null;
						Localidade localidade = null;
						sequencialImpressao++;
	
						if (emitirDocumentoCobrancaHelper != null) {
							
							nomeCliente = this.repositorioClienteImovel.pesquisarNomeClientePorImovel(
									emitirDocumentoCobrancaHelper.getIdImovel());
	
							CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
							cobrancaDocumento.setId(emitirDocumentoCobrancaHelper.getIdDocumentoCobranca());
	
							colecaoCobrancaDocumentoItem = this.repositorioCobranca
									.selecionarCobrancaDocumentoItemReferenteConta(cobrancaDocumento);
							
							
							if (colecaoCobrancaDocumentoItem != null && !colecaoCobrancaDocumentoItem.isEmpty()) {
								// Início do processo de geração do arquivo txt
								
								//******************************************************
								// Inscricao do Imovel
								quadraInscricao = new Quadra();
								setorComercialInscricao = new SetorComercial();
								localidade = new Localidade();
								inscricao = new Imovel();
	
								quadraInscricao.setNumeroQuadra(emitirDocumentoCobrancaHelper.getNumeroQuadra());
								setorComercialInscricao.setCodigo(emitirDocumentoCobrancaHelper.getCodigoSetorComercial());
								localidade.setId(emitirDocumentoCobrancaHelper.getIdLocalidade());
								inscricao.setLocalidade(localidade);
								inscricao.setSetorComercial(setorComercialInscricao);
								inscricao.setQuadra(quadraInscricao);
								inscricao.setLote(emitirDocumentoCobrancaHelper.getLote());
								inscricao.setSubLote(emitirDocumentoCobrancaHelper.getSubLote());
								
								cobrancaDocumentoTxt.append(Util.completaString(
										inscricao.getInscricaoFormatada(), 20));
								
								//******************************************************
								// Nome do Cliente
								cobrancaDocumentoTxt.append(Util.completaString(nomeCliente, 50));
								
								//******************************************************
								// Matrícula do imóvel
								String matriculaImovelFormatada = 
									Util.adicionarZerosEsquedaNumero(8, emitirDocumentoCobrancaHelper.getIdImovel().toString());
								matriculaImovelFormatada = matriculaImovelFormatada.substring(0, 7) + "." + matriculaImovelFormatada.substring(7, 8);
								cobrancaDocumentoTxt.append(matriculaImovelFormatada);
								
								//******************************************************
								// Endereco do Imovel
								String nomeBairro = "";
								String nomeMunicipio = "";
								String siglaUnidadeFederecao = "";
								String cepFormatado = "";
								
								String[] parmsEnderecoImovel = getControladorEndereco()
										.pesquisarEnderecoFormatadoDividido(emitirDocumentoCobrancaHelper.getIdImovel());
								
								if (parmsEnderecoImovel != null) {
									// Endereço sem Municipio e Unidade Federação
									cobrancaDocumentoTxt.append(Util.completaString(parmsEnderecoImovel[0], 100));
									
									// Nome do Municipio
									nomeMunicipio = "" + parmsEnderecoImovel[1];
									// Sigla da Unidade Federação
									siglaUnidadeFederecao = parmsEnderecoImovel[2];
									// Nome do Bairro
									nomeBairro = "" + parmsEnderecoImovel[3];
									// CEP Formatado
									cepFormatado = parmsEnderecoImovel[4];
								} else {
									cobrancaDocumentoTxt.append(Util.completaString("", 100));									
								}
									
								// Bairro
								cobrancaDocumentoTxt.append(Util.completaString(nomeBairro, 30));
								// Municipio
								cobrancaDocumentoTxt.append(Util.completaString(nomeMunicipio, 30));
								// UF
								cobrancaDocumentoTxt.append(Util.completaString(siglaUnidadeFederecao, 2));
								// CEP
								String cepStr = Util.completaString(cepFormatado, 8);
								cobrancaDocumentoTxt.append(cepStr.substring(0,5) + "-" + cepStr.substring(5,8));
								
								//******************************************************
								//CRC2574 alterado por Vivianne Sousa 19/08/2009 - Francisco
								// Numero da OS
								cobrancaDocumentoTxt.append(Util.completaString(
										"" +emitirDocumentoCobrancaHelper.getNumeroOS(), 9));
								//	"" +emitirDocumentoCobrancaHelper.getNumeroSequenciaDocumento(), 9));
								
								//******************************************************
								// Categorias
								// Quantidades de economias por categoria: 1º RESIDÊNCIAL 2º COMERCIAL 3º INDUSTRIAL 4º PÚBLICA
								Imovel imovel = new Imovel();
								imovel.setId(emitirDocumentoCobrancaHelper.getIdImovel());
								Collection colecaoCategorias = getControladorImovel().obterQuantidadeEconomiasCategoria(imovel);
								imovel = null;
								String qtdResidencial = "";
								String qtdComercial = "";
								String qtdIndustrial = "";
								String qtdPublico = "";
								Integer totalCategoria = 0;
			
								if (colecaoCategorias != null && !colecaoCategorias.isEmpty()) {
									Iterator iteratorColecaoCategorias = colecaoCategorias.iterator();
									Categoria categoria = null;
									
									while (iteratorColecaoCategorias.hasNext()) {
										categoria = (Categoria) iteratorColecaoCategorias.next();
			
										if (categoria.getId().equals(Categoria.RESIDENCIAL)) {
											qtdResidencial = "" + categoria.getQuantidadeEconomiasCategoria();
											totalCategoria += categoria.getQuantidadeEconomiasCategoria();
										} else if (categoria.getId().equals(Categoria.COMERCIAL)) {
											qtdComercial = "" + categoria.getQuantidadeEconomiasCategoria();
											totalCategoria += categoria.getQuantidadeEconomiasCategoria();
										} else if (categoria.getId().equals(Categoria.INDUSTRIAL)) {
											qtdIndustrial = "" + categoria.getQuantidadeEconomiasCategoria();
											totalCategoria += categoria.getQuantidadeEconomiasCategoria();
										} else if (categoria.getId().equals(Categoria.PUBLICO)) {
											qtdPublico = "" + categoria.getQuantidadeEconomiasCategoria();
											totalCategoria += categoria.getQuantidadeEconomiasCategoria();
										}
									}
								}
								colecaoCategorias = null;
								
								// Residencial
								if (!qtdResidencial.equals(""))
									cobrancaDocumentoTxt.append(Util.adicionarZerosEsquedaNumero(3, qtdResidencial));
								else
									cobrancaDocumentoTxt.append(Util.completaString("", 3));
								
								// Comercial
								if (!qtdComercial.equals(""))
									cobrancaDocumentoTxt.append(Util.adicionarZerosEsquedaNumero(3, qtdComercial));
								else
									cobrancaDocumentoTxt.append(Util.completaString("", 3));
			
								// Industrial
								if (!qtdIndustrial.equals(""))
									cobrancaDocumentoTxt.append(Util.adicionarZerosEsquedaNumero(3, qtdIndustrial));
								else
									cobrancaDocumentoTxt.append(Util.completaString("", 3));
			
								// Publico
								if (!qtdPublico.equals(""))
									cobrancaDocumentoTxt.append(Util.adicionarZerosEsquedaNumero(3, qtdPublico));
								else
									cobrancaDocumentoTxt.append(Util.completaString("", 3));
								
								//******************************************************
								// Codigo da Situacao da Ligacao de Agua
								cobrancaDocumentoTxt.append(Util.completaString(
										emitirDocumentoCobrancaHelper.getIdLigacaoAguaSituacao().toString(), 1));
								
								//******************************************************
								// Codigo da Situacao da Ligacao do Esgoto
								cobrancaDocumentoTxt.append(Util.completaString(
										emitirDocumentoCobrancaHelper.getIdLigacaoEsgotoSituacao().toString(), 1));
								
								//******************************************************
								// Data de Emissao Ordem de Corte
								if (emitirDocumentoCobrancaHelper.getEmissao() != null)
									cobrancaDocumentoTxt.append(
											Util.formatarData(emitirDocumentoCobrancaHelper.getEmissao()));
								else
									cobrancaDocumentoTxt.append(Util.completaString("", 10));
								
								//******************************************************
								// Numero do Hidrometro
								String numeroHidrometro = getControladorAtendimentoPublico()
									.pesquisarNumeroHidrometroLigacaoAgua(emitirDocumentoCobrancaHelper.getIdImovel());
								
								if (numeroHidrometro != null)
									cobrancaDocumentoTxt.append(Util.completaString(numeroHidrometro, 10));
								else
									cobrancaDocumentoTxt.append(Util.completaString("", 10));
								
								//******************************************************
								// Grupo de Cobranca
								cobrancaDocumentoTxt.append(Util.completaString(
										emitirDocumentoCobrancaHelper.getIdCobrancaGrupo().toString(), 2));
								
								//******************************************************
								// Data de Emissao Aviso de Corte
								if (emitirDocumentoCobrancaHelper.getDataEmissaoPredecessor() != null)
									cobrancaDocumentoTxt.append(
											Util.formatarData(emitirDocumentoCobrancaHelper.getDataEmissaoPredecessor()));
								else
									cobrancaDocumentoTxt.append(Util.completaString("", 10));
								
								//******************************************************
								// Sequencial de impressão
								cobrancaDocumentoTxt.append(Util.completaStringComEspacoAEsquerda(
										Util.retornaSequencialFormatado(sequencialImpressao), 7));
								
								//******************************************************
								// Ciclo
								String ciclo = "";
								if (idCronogramaAtividadeAcaoCobranca != null) {
									if (cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma() != null) {
										if (cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma()
												.getCobrancaGrupoCronogramaMes() != null) {
											ciclo = Util.formatarAnoMesParaMesAno(
													cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma()
													.getCobrancaGrupoCronogramaMes().getAnoMesReferencia());
										}
									}
								}
								cobrancaDocumentoTxt.append(Util.completaString(ciclo, 7));
								
								//******************************************************
								// Perfil do Imovel
								cobrancaDocumentoTxt.append(Util.completaString(
										emitirDocumentoCobrancaHelper.getDescricaoImovelPerfil(), 20));
								
								//******************************************************
								// CAS - Nome da Unidade de Negocio
								String nomeUnidadeNegocio = "";
								Integer idUnidadeNegocio = getControladorLocalidade().pesquisarIdUnidadeNegocioParaLocalidade(
										emitirDocumentoCobrancaHelper.getIdLocalidade());
								
								if (idUnidadeNegocio != null) {
									FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
									filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
											FiltroUnidadeNegocio.ID, idUnidadeNegocio));
									
									UnidadeNegocio unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(
											getControladorUtil().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName()));
									
									if (unidadeNegocio != null)
										nomeUnidadeNegocio = unidadeNegocio.getNome();
	
								}
								cobrancaDocumentoTxt.append(Util.completaString(nomeUnidadeNegocio, 50));
								
								//******************************************************
								// Quantidade de Contas em Debito
								cobrancaDocumentoTxt.append(Util.completaStringComEspacoAEsquerda(
										"" + colecaoCobrancaDocumentoItem.size(), 3));
								
								//******************************************************
								// Documentos - Dados da Conta
								/*
								 * Selecionar os itens do documento de cobrança
								 * correspondentes a conta e ordenar por ano/mês de
								 * referência da conta.
								 */
								Iterator iteratorColecaoCobrancaDocumentoItem = null;
								CobrancaDocumentoItem cobrancaDocumentoItem = null;
	
								int countImpressao = colecaoCobrancaDocumentoItem.size() - 11;
	
								/*
								 * Caso a quantidade de itens
								 * selecionados seja superior a 12
								 * [SB0001 - Calcular Valor e Data
								 * de Vencimento Anterior]
								 * 
								 * Caso contrário: Dados do primeiro
								 * e segundo itens selecionados
								 */
								if (colecaoCobrancaDocumentoItem.size() > 12) {
									CalcularValorDataVencimentoAnteriorHelper calcularValorDataVencimentoAnteriorHelper = this
											.calcularValorDataVencimentoAnterior(colecaoCobrancaDocumentoItem, 12);
	
									cobrancaDocumentoTxt.append("DEB.ATE");
	
									// Data de Vencimento da Conta
									cobrancaDocumentoTxt.append(Util.formatarData(
											calcularValorDataVencimentoAnteriorHelper.getDataVencimentoAnterior()));
									
									// Valor do Item da Conta
									cobrancaDocumentoTxt.append(Util.completaStringComEspacoAEsquerda(
											Util.formatarMoedaReal(calcularValorDataVencimentoAnteriorHelper.getValorAnterior()), 14));
								} else {
									iteratorColecaoCobrancaDocumentoItem = colecaoCobrancaDocumentoItem.iterator();
									cobrancaDocumentoItem = null;
									cobrancaDocumentoItem = (CobrancaDocumentoItem) iteratorColecaoCobrancaDocumentoItem.next();
	
									// Mes/Ano Referencia da conta
									cobrancaDocumentoTxt.append(Util.completaString(Util.formatarAnoMesParaMesAno(
											cobrancaDocumentoItem.getContaGeral().getConta().getReferencia()), 7));
	
									// Data de Vencimento da Conta
									cobrancaDocumentoTxt.append(Util.formatarData(
											cobrancaDocumentoItem.getContaGeral().getConta().getDataVencimentoConta()));
									
									// Valor do Item da Conta
									cobrancaDocumentoTxt.append(Util.completaStringComEspacoAEsquerda(
											Util.formatarMoedaReal(cobrancaDocumentoItem.getValorItemCobrado()), 14));
								}
								
								if (countImpressao <= 0) {
									iteratorColecaoCobrancaDocumentoItem = colecaoCobrancaDocumentoItem.iterator();
									cobrancaDocumentoItem = null;
									int countRegistros = 0;
									
									while (iteratorColecaoCobrancaDocumentoItem.hasNext()) {
										cobrancaDocumentoItem = (CobrancaDocumentoItem) iteratorColecaoCobrancaDocumentoItem.next();
										
										if (countRegistros > 0) {
											// Mes/Ano Referencia da conta
											cobrancaDocumentoTxt.append(Util.completaString(Util.formatarAnoMesParaMesAno(
													cobrancaDocumentoItem.getContaGeral().getConta().getReferencia()), 7));
	
											// Data de Vencimento da Conta
											cobrancaDocumentoTxt.append(Util.formatarData(
													cobrancaDocumentoItem.getContaGeral().getConta().getDataVencimentoConta()));
											
											// Valor do Item da Conta
											cobrancaDocumentoTxt.append(Util.completaStringComEspacoAEsquerda(
													Util.formatarMoedaReal(cobrancaDocumentoItem.getValorItemCobrado()), 14));
										}
										
										countRegistros++;
									}
									
									if (countRegistros < 12) {
										for (int x = 0; x < (12 - countRegistros); x++) {
											cobrancaDocumentoTxt.append(Util.completaString("", 31));
										}
									}
								} else {
									while (countImpressao < colecaoCobrancaDocumentoItem.size()) {
										cobrancaDocumentoItem = (CobrancaDocumentoItem) ((List) colecaoCobrancaDocumentoItem).get(countImpressao);
	
										//Mes/Ano Referencia da conta
										cobrancaDocumentoTxt.append(Util.completaString(Util.formatarAnoMesParaMesAno(
												cobrancaDocumentoItem.getContaGeral().getConta().getReferencia()), 7));
										
										// Data de Vencimento da Conta
										cobrancaDocumentoTxt.append(Util.formatarData(
												cobrancaDocumentoItem.getContaGeral().getConta().getDataVencimentoConta()));
										
										// Valor do Item da Conta
										cobrancaDocumentoTxt.append(Util.completaStringComEspacoAEsquerda(
												Util.formatarMoedaReal(cobrancaDocumentoItem.getValorItemCobrado()), 14));
										
										countImpressao++;
									}
								}
								
								//******************************************************
								// Valor total do Documento de Cobranca
								cobrancaDocumentoTxt.append(Util.completaStringComEspacoAEsquerda(
										Util.formatarMoedaReal(emitirDocumentoCobrancaHelper.getValorDocumento()), 14));
								
								//******************************************************
								// Representacao Numerica do Codigo de Barras
								String representacaoNumericaCodBarra = "";
								
								representacaoNumericaCodBarra = this.getControladorArrecadacao()
										.obterRepresentacaoNumericaCodigoBarra(
												5,
												emitirDocumentoCobrancaHelper.getValorDocumento(),
												localidade.getId(),
												emitirDocumentoCobrancaHelper.getIdImovel(),
												null, null, null, null,
												String.valueOf(emitirDocumentoCobrancaHelper.getNumeroSequenciaDocumento()),
												acaoCobranca.getDocumentoTipo().getId(),
												null, null, null);
								
								// Formata a representacao numerica do Codigo de Barras
								String representacaoNumericaCodBarraFormatada =
									representacaoNumericaCodBarra.substring(0, 11) + " " +
									representacaoNumericaCodBarra.substring(11, 12) + " " +
									representacaoNumericaCodBarra.substring(12, 23) + " " +
									representacaoNumericaCodBarra.substring(23, 24) + " " +
									representacaoNumericaCodBarra.substring(24, 35) + " " +
									representacaoNumericaCodBarra.substring(35, 36) + " " +
									representacaoNumericaCodBarra.substring(36, 47) + " " +
									representacaoNumericaCodBarra.substring(47, 48);
								
								cobrancaDocumentoTxt.append(representacaoNumericaCodBarraFormatada);
								
								//******************************************************
								// Codigo de Barras
								Interleaved2of5 codigoBarraIntercalado2de5 = new Interleaved2of5();
								
								// Recupera a representação númerica do código de barras
								// sem os dígitos verificadores
								String representacaoCodigoBarrasSemDigitoVerificador = 
									representacaoNumericaCodBarra.substring(0, 11) +
									representacaoNumericaCodBarra.substring(12, 23) +
									representacaoNumericaCodBarra.substring(24, 35) +
									representacaoNumericaCodBarra.substring(36, 47);
								
								cobrancaDocumentoTxt.append(codigoBarraIntercalado2de5.encodeValue(
												representacaoCodigoBarrasSemDigitoVerificador));
								
								
								//Vivianne Sousa 23/02/2010
								String txtString =  cobrancaDocumentoTxt.toString();
								
								txtString = txtString.replace('\'',' ');
								txtString = txtString.replace('`',' ');
									
								repositorioCobranca.inserirDocumentoCobrancaImpressao(
									cobrancaDocumento.getId(),
									txtString,
									idComandoAtividadeAcaoCobranca,
									idCronogramaAtividadeAcaoCobranca,
									sequencialImpressao);
									
							}
	
							// adiciona o id da conta e o sequencial no para serem atualizados
							mapAtualizaSequencial.put(
								emitirDocumentoCobrancaHelper.getIdDocumentoCobranca(),sequencialImpressao);
							
							colecaoCobrancaDocumentoItem = null;
						}
					}
				} else {
					flagFimPesquisa = true;
				}
				
				repositorioCobranca.atualizarSequencialCobrancaDocumentoImpressao(mapAtualizaSequencial);
				mapAtualizaSequencial.clear();
				mapAtualizaSequencial = null;
				colecaoEmitirDocumentoCobranca = null;
			}
	
			System.out.println("*************************************");
			System.out.println("FIM DO GERAR CORTE FISICO IMPRESSAO COMPESA");
			System.out.println("*************************************");

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}
	
	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 *
	 * @author Raphael Rossiter
	 * @date 20/07/2009
	 *
	 * @param helper
	 * @throws ControladorException
	 */
	public void gerarAtividadeAcaoCobrancaEmissaoDocumento(EmissaoDocumentoCobrancaHelper helper) 
		throws ControladorException{
		
		//7.1 para as ações aviso de corte
		if (helper.getAcaoCobranca().getId().equals(CobrancaAcao.AVISO_CORTE) ||
				helper.getAcaoCobranca().getId().equals(CobrancaAcao.AVISO_CORTE_A_REVELIA)) {
			
			// [UC0349 Emitir Documento fr Cobrança - AvisoCorte]
			emitirDocumentoCobranca(helper.getCobrancaAcaoAtividadeCronograma(),
			helper.getCobrancaAcaoAtividadeComando(), helper.getDataAtual(), helper.getAcaoCobranca(), 
			helper.getGrupoCobranca(), helper.getCriterioCobranca());
			
		}
		
		// 7.2 para as ações corte administrativo ou corte fisico
		if (helper.getAcaoCobranca().getId().equals(CobrancaAcao.CORTE_ADMINISTRATIVO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CORTE_FISICO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CORTE_ADMINISTRATIVO_LIGADO_A_REVELIA) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CORTE_FISICO_LIGADO_A_REVELIA) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CORTE_FISICO_PUBLICO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.RECORTE) ||
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.SUPRESSAO_PARCIAL) ||
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.SUPRESSAO_TOTAL) ||
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.FISCALIZACAO_SITUACAO_ESGOTO)) {
			
			// [UC0349 Emitir Documento fr Cobrança - AvisoCorte]
			emitirDocumentoCobrancaOrdemCorte(helper.getCobrancaAcaoAtividadeCronograma(),
			helper.getCobrancaAcaoAtividadeComando(), helper.getDataAtual(), helper.getAcaoCobranca(), 
			helper.getGrupoCobranca(), helper.getCriterioCobranca());

		}
		
		/*// 7.3 para as ações supressão parcial ou supressão total
		if (helper.getAcaoCobranca().getId().equals(CobrancaAcao.SUPRESSAO_PARCIAL) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.SUPRESSAO_TOTAL)) {
			
			// [UC0349 Emitir Documento fr Cobrança - AvisoCorte]
			emitirDocumentoCobrancaOrdemSupressao(helper.getCobrancaAcaoAtividadeCronograma(),
			helper.getCobrancaAcaoAtividadeComando(), helper.getDataAtual(), helper.getAcaoCobranca(), 
			helper.getGrupoCobranca(), helper.getCriterioCobranca());

		}*/

		// 7.4 para as ações fiscalização suprimido ou fiscalização
		// cortado
		if (helper.getAcaoCobranca().getId().equals(CobrancaAcao.FISCALIZACAO_SUPRIMIDO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.FISCALIZACAO_CORTADO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.FISCALIZACAO_FACTIVEL) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.FISCALIZACAO_POTENCIAL) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.FISCALIZACAO_LIGADO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.FISCALIZACAO_TOTAL) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.FISCALIZACAO_LIGADO_A_REVELIA)) {

			// [UC0349 Emitir Documento fr Cobrança - AvisoCorte]
			emitirDocumentoCobrancaOrdemFiscalizacao(helper.getCobrancaAcaoAtividadeCronograma(),
			helper.getCobrancaAcaoAtividadeComando(), helper.getDataAtual(), helper.getAcaoCobranca(), 
			helper.getGrupoCobranca(), helper.getCriterioCobranca());

		}
		
		//**************************************************************
		// Autor: Ivan Sergio
		// Data: 18/05/2009
		// CRC1902
		//**************************************************************
		if (helper.getAcaoCobranca().getId().equals(CobrancaAcao.INSPECAO_LIGACOES)) {
			
			// [UC0904] Gerar TXT Inspecao Ligacoes
			gerarTxtInspecaoLigacoes(helper.getCobrancaAcaoAtividadeCronograma(),
			helper.getCobrancaAcaoAtividadeComando(), helper.getDataAtual(), helper.getAcaoCobranca(), 
			helper.getGrupoCobranca(), helper.getCriterioCobranca());
		}
		//**************************************************************

		// 7.5 para as ações carta ligado ou carta cortado
		if (helper.getAcaoCobranca().getId().equals(CobrancaAcao.CARTA_COBRANCA_SUPRIMIDO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CARTA_COBRANCA_CORTADO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CARTA_TARIFA_SOCIAL_LIGADO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CARTA_TARIFA_SOCIAL_CORTADO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CARTA_COBRANCA_LIGADO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CARTA_COBRANCA_LIGADO_A_REVELIA) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CARTA_TARIFA_SOCIAL_LIGADO_A_REVELIA)) {

			// [UC0575] Emitir Aviso de Cobrança
			emitirAvisoCobranca(helper.getCobrancaAcaoAtividadeCronograma(),
			helper.getCobrancaAcaoAtividadeComando(), helper.getDataAtual(), helper.getAcaoCobranca(), 
			helper.getGrupoCobranca(), helper.getCriterioCobranca());

		}

		// 7.4 para as ações fiscalização suprimido ou fiscalização
		// cortado
		if (helper.getAcaoCobranca().getId().equals(
				CobrancaAcao.CARTA_COBRANCA_PARCELAMENTO)) {

			// [UC0576 Emitir Parcelamento em atraso]
			emitirParcelamentoEmAtraso(helper.getCobrancaAcaoAtividadeCronograma(),
			helper.getCobrancaAcaoAtividadeComando(), helper.getDataAtual(), helper.getAcaoCobranca(), 
			helper.getGrupoCobranca(), helper.getCriterioCobranca());

		}
		
		// 7.5	Para as ações de Carta de Campanha de Parcelamento
		if (helper.getAcaoCobranca().getId().equals(
				CobrancaAcao.CARTA_FINAL_DE_ANO)) {
			emitirCartasCampanha(helper.getCobrancaAcaoAtividadeCronograma(),
					helper.getCobrancaAcaoAtividadeComando(), helper.getDataAtual(), helper.getAcaoCobranca(), 
					helper.getGrupoCobranca(), helper.getCriterioCobranca());
		}
		
	}
	
	
	/**
	 * 
	 * Este caso de uso permite a emissão de um ou mais documentos de cobrança
	 * 
	 * [UC0349] Emitir Documento de Cobrança
	 * 
	 * @author Vivianne Sousa
	 * @data 14/12/2009
	 * 
	 * @param
	 * @return void
	 */
	public void emitirDocumentoCobranca(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			Date dataAtualPesquisa, CobrancaAcao acaoCobranca,
			CobrancaGrupo grupoCobranca, CobrancaCriterio cobrancaCriterio)
			throws ControladorException {
		
		try{
			
			Integer idCobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando != null ? cobrancaAcaoAtividadeComando.getId() : null;
			Integer idCobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma != null ? cobrancaAcaoAtividadeCronograma.getId() : null;
			
			//deleta os dados da tabela COBRANCA_DOCUMENTO_IMPRESSAO 
			//para não gerar dados duplicados quando reiniciar o batch
			repositorioCobranca.deletarDocumentoCobrancaImpressao(idCobrancaAcaoAtividadeComando,
					idCobrancaAcaoAtividadeCronograma);
			
			//gera os dados da tabela COBRANCA_DOCUMENTO_IMPRESSAO 
			gerarDocumentoCobrancaImpressaoAvisoCorte(cobrancaAcaoAtividadeCronograma,
					 cobrancaAcaoAtividadeComando,dataAtualPesquisa,  acaoCobranca,
					 grupoCobranca,  cobrancaCriterio);
			
			//gerar arquivo com os dados da tabela COBRANCA_DOCUMENTO_IMPRESSAO 
			emitirDocumentoCobranca(cobrancaAcaoAtividadeCronograma,
					cobrancaAcaoAtividadeComando, acaoCobranca, grupoCobranca);
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
	}
	
	
	/**
	 * Emitir Documento de Cobrança – Corte Fisico
	 * 
	 * @author Vivianne Sousa
	 * @date 23/02/2010
	 */

	public void gerarTxtCorteFisico(CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			CobrancaAcao acaoCobranca, CobrancaGrupo grupoCobranca)throws ControladorException{
	    
		BufferedWriter out = null;
//		ZipOutputStream zos = null;
		File leitura = null;

		try{
			
			Integer idCronogramaAtividadeAcaoCobranca = null;
			Integer idComandoAtividadeAcaoCobranca = null;
			Integer idAcaoCobranca = null;
			if (cobrancaAcaoAtividadeCronograma != null
					&& cobrancaAcaoAtividadeCronograma.getId() != null) {
				idCronogramaAtividadeAcaoCobranca = cobrancaAcaoAtividadeCronograma.getId();
			}
			if (cobrancaAcaoAtividadeComando != null
					&& cobrancaAcaoAtividadeComando.getId() != null) {
				idComandoAtividadeAcaoCobranca = cobrancaAcaoAtividadeComando.getId();
			}
			if (acaoCobranca != null && acaoCobranca.getId() != null) {
				idAcaoCobranca = acaoCobranca.getId();
			}
			
			// Definindo arquivo para escrita
			Date dataAtual = new Date();
			String nomeZip = "";
			// caso seja cronograma
			if (idCronogramaAtividadeAcaoCobranca != null) {
				if (idAcaoCobranca.equals(CobrancaAcao.CORTE_FISICO)) {
					nomeZip = "NOVA_ORDEM_CORTE_FISICO_GRUPO_"
							+ grupoCobranca.getId() + "_"
							+ Util.formatarData(dataAtual) + Util.formatarHoraSemDataSemDoisPontos(dataAtual);
				} else if (idAcaoCobranca.equals(CobrancaAcao.CORTE_FISICO_LIGADO_A_REVELIA)) {
					nomeZip = "NOVA_ORDEM_CORTE_FISICO_LIGADO_A_REVELIA_GRUPO_"
							+ grupoCobranca.getId() + "_"
							+ Util.formatarData(dataAtual) + Util.formatarHoraSemDataSemDoisPontos(dataAtual);
				} else if (idAcaoCobranca.equals(CobrancaAcao.CORTE_FISICO_PUBLICO)) {
					nomeZip = "NOVA_ORDEM_CORTE_FISICO_PUBLICO_GRUPO_"
							+ grupoCobranca.getId() + "_"
							+ Util.formatarData(dataAtual) + Util.formatarHoraSemDataSemDoisPontos(dataAtual);
				} else if (idAcaoCobranca.equals(CobrancaAcao.RECORTE)) {
					nomeZip = "NOVA_ORDEM_RECORTE_GRUPO_"
							+ grupoCobranca.getId() + "_"
							+ Util.formatarData(dataAtual) + Util.formatarHoraSemDataSemDoisPontos(dataAtual);
				} 
				// para o caso de eventual
			} else {
				String descricaoAbrevDocumentoTipo = "";
				if (acaoCobranca != null
						&& acaoCobranca.getDocumentoTipo() != null) {
					descricaoAbrevDocumentoTipo = acaoCobranca
							.getDocumentoTipo().getDescricaoAbreviado();
				}
				String tituloComandoEventual = cobrancaAcaoAtividadeComando
						.getDescricaoTitulo();
		
				nomeZip = descricaoAbrevDocumentoTipo + " "
						+ tituloComandoEventual + " "
						+ Util.formatarData(dataAtual) + Util.formatarHoraSemDataSemDoisPontos(dataAtual);
			}
			nomeZip = nomeZip.replace("/", "_");
			nomeZip = nomeZip.replace(" ", "_");
			
			// pegar o arquivo, zipar pasta e arquivo e escrever no stream
			System.out.println("***************************************");
			System.out.println("INICO DA CRIACAO DO ARQUIVO");
			System.out.println("***************************************");
	
			// criar o arquivo zip
//			File compactado = new File(nomeZip + ".zip"); // nomeZip
//			zos = new ZipOutputStream(new FileOutputStream(compactado));
	
			leitura = new File(nomeZip + ".txt");
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(leitura.getAbsolutePath())));
			
			System.out.println("***************************************");
			System.out.println("ENTROU NO CORTE FISICO");
			System.out.println("***************************************");
			Collection colecaoCobrancaDocumento = repositorioCobranca
					.pesquisarDocumentoCobrancaImpressao(
							idComandoAtividadeAcaoCobranca,idCronogramaAtividadeAcaoCobranca);
			System.out.println("***************************************");
			System.out.println("QTD DE COBRANCA DOCUMENTO:"	+ colecaoCobrancaDocumento.size());
			System.out.println("***************************************");
			
			if(colecaoCobrancaDocumento != null && !colecaoCobrancaDocumento.isEmpty()){
				
				Iterator iterCobrancaDocumentoImpressao = colecaoCobrancaDocumento.iterator();
				
				while (iterCobrancaDocumentoImpressao.hasNext()) {
					String  linhaTxt  = (String) iterCobrancaDocumentoImpressao.next();
					
					StringBuilder cobrancaDocumentoTxt = new StringBuilder();
					cobrancaDocumentoTxt.append(linhaTxt);
					cobrancaDocumentoTxt.append(System.getProperty("line.separator"));
				    out.write(cobrancaDocumentoTxt.toString());					
					
				}
				
			}
			
			out.flush();                           
	
			System.out.println("***************************************");
			System.out.println("FIM DA CRIACAO DO ARQUIVO");
			System.out.println("***************************************");
		
//			//deleta registros apos geração do txt
//			repositorioCobranca.deletarDocumentoCobrancaImpressao(idComandoAtividadeAcaoCobranca,
//					idCronogramaAtividadeAcaoCobranca);
			
			
		}catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		} finally {
			try{
				out.close();
//				ZipUtil.adicionarArquivo(zos, leitura);
		
				// close the stream
//				zos.close();
//				leitura.delete();
			} catch (IOException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}
	    }
		
	}
	
	/**
	 * Emitir Documento de Cobrança – Corte Fisico
	 * 
	 * @author Vivianne Sousa
	 * @date 23/02/2010
	 */
	public void emitirDocumentoCobrancaCorteFisico(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			Date dataAtualPesquisa, CobrancaAcao acaoCobranca,
			CobrancaGrupo grupoCobranca, CobrancaCriterio cobrancaCriterio)
			throws ControladorException {
		
		try{
			
			Integer idCobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando != null ? cobrancaAcaoAtividadeComando.getId() : null;
			Integer idCobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma != null ? cobrancaAcaoAtividadeCronograma.getId() : null;
			
			//deleta os dados da tabela COBRANCA_DOCUMENTO_IMPRESSAO 
			//para não gerar dados duplicados quando reiniciar o batch
			repositorioCobranca.deletarDocumentoCobrancaImpressao(idCobrancaAcaoAtividadeComando,
					idCobrancaAcaoAtividadeCronograma);
			
			//gera os dados da tabela COBRANCA_DOCUMENTO_IMPRESSAO 
			gerarDocumentoCobrancaImpressaoCorteFisico(cobrancaAcaoAtividadeCronograma,
					 cobrancaAcaoAtividadeComando,dataAtualPesquisa,  acaoCobranca,
					 grupoCobranca,  cobrancaCriterio);
			
			//gerar arquivo com os dados da tabela COBRANCA_DOCUMENTO_IMPRESSAO 
			gerarTxtCorteFisico(cobrancaAcaoAtividadeCronograma,
					cobrancaAcaoAtividadeComando, acaoCobranca, grupoCobranca);
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}	
	}
	
	
}
