package gcom.faturamento;

import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteConta;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocalHome;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.PocoTipo;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadraFace;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
import gcom.cobranca.IRepositorioCobranca;
import gcom.cobranca.bean.CalcularValorDataVencimentoAnteriorHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresa;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresaDivisao;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.FiltroArquivoTextoRoteiroEmpresa;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.Rota;
import gcom.micromedicao.ServicoTipoCelular;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.ConsumoAnormalidadeAcao;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidadeAcao;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraSituacao;
import gcom.micromedicao.medicao.FiltroMedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.Criptografia;
import gcom.util.ErroCriptografiaException;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

import javax.ejb.CreateException;
import javax.ejb.SessionContext;

/**
 * Esta classe tem como finalidade encapsular o caso de uso [UC0745] -
 * GerarArquivoTextoFaturamento, gerando maior facilidade na manuten��o do
 * mesmo.
 * 
 * @author Raphael Rossiter
 * @date 30/04/2008
 */
public class UC0745GerarArquivoTextoFaturamento {

	private static UC0745GerarArquivoTextoFaturamento instancia;

	private IRepositorioFaturamento repositorioFaturamento;
	private IRepositorioCobranca repositorioCobranca;
	private SessionContext sessionContext;

	private UC0745GerarArquivoTextoFaturamento(
			IRepositorioFaturamento repositorioFaturamento,
			SessionContext sessionContext,
			IRepositorioCobranca repositorioCobranca) {

		this.repositorioFaturamento = repositorioFaturamento;
		this.repositorioCobranca = repositorioCobranca;
		this.sessionContext = sessionContext;
	}

	public static UC0745GerarArquivoTextoFaturamento getInstancia(
			IRepositorioFaturamento repositorioFaturamento,
			SessionContext sessionContext,
			IRepositorioCobranca repositorioCobranca) {

		if (instancia == null) {
			instancia = new UC0745GerarArquivoTextoFaturamento(
					repositorioFaturamento, sessionContext, repositorioCobranca);
		}
		return instancia;
	}

	/**
	 * Controlador Util
	 * 
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 * 
	 * @return ControladorUtilLocal
	 */
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Controlador Endereco
	 * 
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 * 
	 * @return ControladorEnderecoLocal
	 */
	private ControladorEnderecoLocal getControladorEndereco() {
		ControladorEnderecoLocalHome localHome = null;
		ControladorEnderecoLocal local = null;
		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorEnderecoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ENDERECO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Controlador Imovel
	 * 
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 * 
	 * @return ControladorImovelLocal
	 */
	protected ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;
		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Controlador Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 * 
	 * @return ControladorFaturamentoLocal
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Controlador Micromedicao
	 * 
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 * 
	 * @return ControladorMicromedicaoLocal
	 */
	protected ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorMicromedicaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Controlador Cobranca
	 * 
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 * 
	 * @return ControladorCobrancaLocal
	 */
	protected ControladorCobrancaLocal getControladorCobranca() {
		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorCobrancaLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Controlador Arrecadacao
	 * 
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 * 
	 * @return ControladorArrecadacaoLocal
	 */
	private ControladorArrecadacaoLocal getControladorArrecadacao() {
		ControladorArrecadacaoLocalHome localHome = null;
		ControladorArrecadacaoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorArrecadacaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Controlador Batch
	 * 
	 * @author hugo Leonardo
	 * @date 16/06/2010
	 * 
	 * @return ControladorBatchLocal
	 */
	protected ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorBatchLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [FS0005] - Verificar exist�ncia do arquivo texto por rota
	 * 
	 * @author Raphael Rossiter, Hugo Leonardo
	 * @date 17/04/2008, 16/06/2010
	 * 
	 * @param idRota
	 * @param anoMesReferencia
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean verificarExistenciaArquivoTextoRota(Integer idRota,
			Integer anoMesReferencia) throws ControladorException {

		boolean retorno = true;
		Object[] dadosArquivoTextoRoteiroEmpresa = null;

		try {

			dadosArquivoTextoRoteiroEmpresa = repositorioFaturamento
					.pesquisarArquivoTextoRoteiroEmpresa(idRota,
							anoMesReferencia);

			/*
			 * Caso j� exista um arquivo texto para rota e m�s de referencia
			 * informados e com situa��o da transmiss�o de leitura igual a
			 * dispon�vel: Remover o registro encontrado.
			 */
			if (dadosArquivoTextoRoteiroEmpresa != null) {
				Integer idArquivoTexto = (Integer) dadosArquivoTextoRoteiroEmpresa[0];
				Integer idSituacaoTransmissaoLeitura = (Integer) dadosArquivoTextoRoteiroEmpresa[1];

				if (idSituacaoTransmissaoLeitura
						.equals(SituacaoTransmissaoLeitura.DISPONIVEL)) {
					// REMOVENDO REGISTRO
					this.repositorioFaturamento
							.deletaArquivoTextoRoteiroEmpresaDivisao(idArquivoTexto);
					repositorioFaturamento
							.deletarArquivoTextoRoteiroEmpresa(idArquivoTexto);

				} else {
					retorno = false;
				}

			}
		} catch (ErroRepositorioException e) {
			// sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [FS0002] - Verificar Situa��o Especial de Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 17/04/2008
	 * 
	 * @param idRota
	 * @param numeroPaginas
	 * @param quantidadeRegistros
	 * @throws ControladorException
	 */
	public Object[] pesquisarImovelGerarArquivoTextoFaturamento(Rota rota,
			int numeroIndice, int quantidadeRegistros,
			SistemaParametro sistemaParametro,
			FaturamentoGrupo faturamentoGrupo, Integer idImovelCondominio)
			throws ControladorException {

		Object[] retorno = new Object[2];
		Collection colecaoImoveis = null;
		int quantidadeImoveis = 0;
		Collection imoveis;

		Rota rotaAtual = rota;

		if (idImovelCondominio != null && !idImovelCondominio.equals("")) {
			rotaAtual = null;
		}

		/*
		 * Caso a rota n�o esteja com o indicador de rota alternativa ativo; a
		 * pesquisa dos im�veis ser� feita a partir de sua quadra.
		 */
		if (!rota.getIndicadorRotaAlternativa().equals(ConstantesSistema.SIM)) {

			try {
				imoveis = repositorioFaturamento
						.pesquisarImovelGerarArquivoTextoFaturamento(rotaAtual,
								numeroIndice, quantidadeRegistros,
								sistemaParametro, idImovelCondominio);

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}
		/*
		 * Caso contr�rio; a pesquisa dos im�veis ser� feita a partir da rota
		 * alternativa que estar� associada ao mesmo.
		 */
		else {

			try {

				imoveis = repositorioFaturamento
						.pesquisarImovelGerarArquivoTextoFaturamentoPorRotaAlternativa(
								rota, numeroIndice, quantidadeRegistros,
								sistemaParametro, idImovelCondominio);

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}

		// Carregando os dados dos im�veis selecionados
		if (imoveis != null && !imoveis.isEmpty()) {

			Iterator iteratorImoveis = imoveis.iterator();

			colecaoImoveis = new ArrayList();

			quantidadeImoveis = imoveis.size();

			Imovel imovel = null;

			while (iteratorImoveis.hasNext()) {

				Object[] arrayImovel = (Object[]) iteratorImoveis.next();

				imovel = new Imovel();

				// ID
				imovel.setId((Integer) arrayImovel[23]);

				// GERENCIA REGIONAL E LOCALIDADE
				GerenciaRegional gerenciaRegional = new GerenciaRegional();
				gerenciaRegional.setNome((String) arrayImovel[0]);
				gerenciaRegional.setId((Integer) arrayImovel[28]);

				Localidade localidade = new Localidade();
				localidade.setId((Integer) arrayImovel[1]);
				localidade.setDescricao((String) arrayImovel[2]);
				localidade.setGerenciaRegional(gerenciaRegional);

				imovel.setLocalidade(localidade);

				// SETOR_COMERCIAL
				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setCodigo((Integer) arrayImovel[5]);
				if (arrayImovel[26] != null) {
					setorComercial.setId((Integer) arrayImovel[26]);
				}
				imovel.setSetorComercial(setorComercial);

				// LEITURISTA
				if (arrayImovel[39] != null) {
					Leiturista leiturista = rota.getLeiturista();
					if (arrayImovel[59] != null) {
						Usuario usuario = new Usuario();
						usuario.setLogin((String) arrayImovel[59]);
						usuario.setSenha((String) arrayImovel[60]);
						leiturista.setUsuario(usuario);
					}
					rota.setLeiturista(leiturista);
				}

				// GRUPO
				rota.setFaturamentoGrupo(faturamentoGrupo);

				Quadra quadra = new Quadra();
				quadra.setNumeroQuadra((Integer) arrayImovel[6]);
				quadra.setRota(rota);

				imovel.setQuadra(quadra);

				// QUADRA FACE
				if (arrayImovel[41] != null) {
					QuadraFace quadraFace = new QuadraFace();
					quadraFace.setId((Integer) arrayImovel[41]);
					if (arrayImovel[42] != null) {
						quadraFace
								.setNumeroQuadraFace((Integer) arrayImovel[42]);
					}

					imovel.setQuadraFace(quadraFace);
				}
				// SEQUENCIAL DA ROTA
				if (arrayImovel[27] != null) {
					imovel.setNumeroSequencialRota((Integer) arrayImovel[27]);
				}

				// LOTE E SUBLOTE
				imovel.setLote((Short) arrayImovel[7]);
				imovel.setSubLote((Short) arrayImovel[8]);

				// IMOVEL_NOME E CLIENTES: USUARIO E RESPONSAVEL
				imovel.setNomeImovel((String) arrayImovel[3]);

				Set colecaoClienteImovel = new HashSet();
				if (arrayImovel[4] != null) {
					ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
					clienteRelacaoTipo.setId(ClienteRelacaoTipo.USUARIO
							.intValue());

					Cliente clienteUsuario = new Cliente();
					clienteUsuario.setNome((String) arrayImovel[10]);

					if (arrayImovel[32] != null) {
						clienteUsuario.setCpf((String) arrayImovel[32]);
					}

					if (arrayImovel[33] != null) {
						clienteUsuario.setCnpj((String) arrayImovel[33]);
					}

					clienteUsuario.setId((Integer) arrayImovel[74]);

					ClienteImovel clienteImovel = new ClienteImovel();
					clienteImovel.setCliente(clienteUsuario);
					clienteImovel.setClienteRelacaoTipo(clienteRelacaoTipo);

					clienteImovel
							.setIndicadorNomeConta((Short) arrayImovel[75]);
					colecaoClienteImovel.add(clienteImovel);
				}

				if (arrayImovel[9] != null) {
					ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
					clienteRelacaoTipo.setId(ClienteRelacaoTipo.RESPONSAVEL
							.intValue());

					Cliente clienteResponsavel = new Cliente();
					clienteResponsavel.setId((Integer) arrayImovel[9]);
					clienteResponsavel.setNome((String) arrayImovel[10]);

					clienteResponsavel.setId((Integer) arrayImovel[72]);

					ClienteImovel clienteImovel = new ClienteImovel();
					clienteImovel.setCliente(clienteResponsavel);
					clienteImovel.setClienteRelacaoTipo(clienteRelacaoTipo);

					clienteImovel
							.setIndicadorNomeConta((Short) arrayImovel[73]);

					colecaoClienteImovel.add(clienteImovel);
				}

				if (colecaoClienteImovel.size() > 0) {
					imovel.setClienteImoveis(colecaoClienteImovel);
				}

				// LIGACAO_AGUA_SITUACAO
				if (arrayImovel[11] != null) {
					LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
					ligacaoAguaSituacao.setId((Integer) arrayImovel[11]);
					ligacaoAguaSituacao
							.setIndicadorFaturamentoSituacao((Short) arrayImovel[29]);
					ligacaoAguaSituacao
							.setIndicadorAbastecimento((Short) arrayImovel[36]);
					ligacaoAguaSituacao.setDescricao((String) arrayImovel[38]);
					ligacaoAguaSituacao
							.setIndicadorConsumoReal((Short) arrayImovel[66]);
					ligacaoAguaSituacao
							.setNumeroDiasCorte((Integer) arrayImovel[67]);

					imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
				}

				// LIGACAO_ESGOTO_SITUACAO
				if (arrayImovel[12] != null) {
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId((Integer) arrayImovel[12]);
					ligacaoEsgotoSituacao
							.setIndicadorFaturamentoSituacao((Short) arrayImovel[30]);
					imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
				}

				// LIGACAO_ESGOTO - ConsumoMinimo
				LigacaoEsgoto ligacaoEsgoto = null;
				if (arrayImovel[14] != null) {
					ligacaoEsgoto = new LigacaoEsgoto();
					ligacaoEsgoto.setConsumoMinimo((Integer) arrayImovel[14]);

					// LIGACAO_ESGOTO - DataLigacao
					if (arrayImovel[71] != null) {
						ligacaoEsgoto.setDataLigacao((Date) arrayImovel[71]);
					}
				}

				// LIGACAO_ESGOTO - Percentual
				if (arrayImovel[15] != null) {

					if (ligacaoEsgoto != null) {
						ligacaoEsgoto
								.setPercentualAguaConsumidaColetada((BigDecimal) arrayImovel[15]);
					} else {
						ligacaoEsgoto = new LigacaoEsgoto();
						ligacaoEsgoto
								.setPercentualAguaConsumidaColetada((BigDecimal) arrayImovel[15]);
					}
				}

				// percentual alternativo
				if (arrayImovel[63] != null) {
					if (ligacaoEsgoto != null) {
						ligacaoEsgoto
								.setPercentualAlternativo((BigDecimal) arrayImovel[63]);
					} else {
						ligacaoEsgoto = new LigacaoEsgoto();
						ligacaoEsgoto
								.setPercentualAlternativo((BigDecimal) arrayImovel[63]);
					}

				}
				// consumo percentual alternativo
				if (arrayImovel[64] != null) {
					if (ligacaoEsgoto != null) {
						ligacaoEsgoto
								.setNumeroConsumoPercentualAlternativo((Integer) arrayImovel[64]);
					} else {
						ligacaoEsgoto = new LigacaoEsgoto();
						ligacaoEsgoto
								.setNumeroConsumoPercentualAlternativo((Integer) arrayImovel[64]);
					}

				}
				imovel.setLigacaoEsgoto(ligacaoEsgoto);

				// FATURAMENTO_SITUACAO_TIPO
				if (arrayImovel[61] != null) {
					FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
					faturamentoSituacaoTipo.setId((Integer) arrayImovel[61]);
					faturamentoSituacaoTipo
							.setIndicadorParalisacaoFaturamento((Short) arrayImovel[16]);
					faturamentoSituacaoTipo
							.setIndicadorValidoAgua((Short) arrayImovel[17]);
					faturamentoSituacaoTipo
							.setIndicadorValidoEsgoto((Short) arrayImovel[18]);
					faturamentoSituacaoTipo
							.setIndicadorParalisacaoLeitura((Short) arrayImovel[65]);

					imovel.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
				}

				// IMOVEL_CONDOMINIO
				if (arrayImovel[19] != null) {
					Imovel imovelCondominio = new Imovel();
					imovelCondominio.setId((Integer) arrayImovel[19]);

					imovel.setImovelCondominio(imovelCondominio);
				}

				// INDICADOR_IMOVEL_CONDOMINIO
				imovel.setIndicadorImovelCondominio((Short) arrayImovel[20]);

				// IMOVEL_PERFIL
				ImovelPerfil imovelPerfil = new ImovelPerfil();
				imovelPerfil.setId((Integer) arrayImovel[21]);

				imovel.setImovelPerfil(imovelPerfil);

				// CONSUMO_TARIFA
				ConsumoTarifa consumoTarifa = new ConsumoTarifa();
				consumoTarifa.setId((Integer) arrayImovel[22]);

				if (arrayImovel[25] != null) {
					TarifaTipoCalculo tarifaTipoCalculo = new TarifaTipoCalculo();
					tarifaTipoCalculo.setId((Integer) arrayImovel[25]);
					consumoTarifa.setTarifaTipoCalculo(tarifaTipoCalculo);
				}

				imovel.setConsumoTarifa(consumoTarifa);

				// POCO_TIPO
				if (arrayImovel[24] != null) {
					PocoTipo pocoTipo = new PocoTipo();
					pocoTipo.setId((Integer) arrayImovel[24]);

					imovel.setPocoTipo(pocoTipo);
				}

				// IMOVEL_CONTA_ENVIO
				if (arrayImovel[31] != null) {
					ImovelContaEnvio imovelContaEnvio = new ImovelContaEnvio();
					imovelContaEnvio.setId((Integer) arrayImovel[31]);

					imovel.setImovelContaEnvio(imovelContaEnvio);
				}

				boolean existeHidrometroAgua = false;
				if (arrayImovel[37] != null) {
					LigacaoAgua ligacaoAgua = new LigacaoAgua();
					ligacaoAgua.setId((Integer) arrayImovel[37]);
					if (arrayImovel[13] != null) {
						ligacaoAgua
								.setNumeroConsumoMinimoAgua((Integer) arrayImovel[13]);
					}
					if (arrayImovel[34] != null) {
						HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();
						hidrometroInstalacaoHistorico
								.setId((Integer) arrayImovel[34]);
						// MEDICAO TIPO AGUA
						if (arrayImovel[44] != null) {
							MedicaoTipo medicaoTipo = new MedicaoTipo();
							medicaoTipo.setId((Integer) arrayImovel[44]);
							hidrometroInstalacaoHistorico
									.setMedicaoTipo(medicaoTipo);
						}
						// LOCAL INSTALACAO
						if (arrayImovel[53] != null) {
							HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
							hidrometroLocalInstalacao
									.setId((Integer) arrayImovel[53]);
							hidrometroInstalacaoHistorico
									.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);
						}
						// DATA INSTALACAO
						if (arrayImovel[54] != null) {
							hidrometroInstalacaoHistorico
									.setDataInstalacao((Date) arrayImovel[54]);
						}
						// HIDROMETRO PROTECAO
						if (arrayImovel[55] != null) {
							HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
							hidrometroProtecao.setId((Integer) arrayImovel[55]);
							hidrometroInstalacaoHistorico
									.setHidrometroProtecao(hidrometroProtecao);
						}

						existeHidrometroAgua = true;
						ligacaoAgua
								.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);

						if (arrayImovel[70] != null) {
							ligacaoAgua.setDataLigacao((Date) arrayImovel[70]);
						}
					}
					// NUMERO DO LACRE
					if (arrayImovel[46] != null) {
						ligacaoAgua.setNumeroLacre((String) arrayImovel[46]);
					}

					ligacaoAgua.setDataCorte((Date) arrayImovel[68]);

					imovel.setLigacaoAgua(ligacaoAgua);
				}

				boolean existeHidrometroPoco = false;
				if (arrayImovel[35] != null) {
					HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();
					hidrometroInstalacaoHistorico
							.setId((Integer) arrayImovel[35]);
					// MEDICAO TIPO AGUA
					if (arrayImovel[45] != null) {
						MedicaoTipo medicaoTipo = new MedicaoTipo();
						medicaoTipo.setId((Integer) arrayImovel[45]);
						hidrometroInstalacaoHistorico
								.setMedicaoTipo(medicaoTipo);
					}
					// LOCAL INSTALACAO
					if (arrayImovel[56] != null) {
						HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
						hidrometroLocalInstalacao
								.setId((Integer) arrayImovel[56]);
						hidrometroInstalacaoHistorico
								.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);
					}
					// DATA INSTALACAO
					if (arrayImovel[57] != null) {
						hidrometroInstalacaoHistorico
								.setDataInstalacao((Date) arrayImovel[57]);
					}
					// HIDROMETRO PROTECAO
					if (arrayImovel[58] != null) {
						HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
						hidrometroProtecao.setId((Integer) arrayImovel[58]);
						hidrometroInstalacaoHistorico
								.setHidrometroProtecao(hidrometroProtecao);
					}

					imovel.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
					existeHidrometroAgua = true;
				}

				// NUMERO DE MORADORES
				if (arrayImovel[43] != null) {
					imovel.setNumeroMorador((Short) arrayImovel[43]);
				}

				// LOGRADOURO BAIRRO
				if (arrayImovel[47] != null) {
					LogradouroBairro logradouroBairro = new LogradouroBairro();
					logradouroBairro.setId((Integer) arrayImovel[47]);
					// LOGRADOURO
					if (arrayImovel[48] != null) {
						Logradouro logradouro = new Logradouro();
						logradouro.setId((Integer) arrayImovel[48]);
						logradouro.setNome((String) arrayImovel[49]);
						logradouroBairro.setLogradouro(logradouro);
					}
					// BAIRRO
					if (arrayImovel[50] != null) {
						Bairro bairro = new Bairro();
						bairro.setNome((String) arrayImovel[50]);
						logradouroBairro.setBairro(bairro);
					}

					imovel.setLogradouroBairro(logradouroBairro);
				}

				// NUMERO DO IMOVEL
				if (arrayImovel[51] != null) {
					imovel.setNumeroImovel((String) arrayImovel[51]);
				}

				// COMPLEMENTO ENDERECO
				if (arrayImovel[52] != null) {
					imovel.setComplementoEndereco((String) arrayImovel[52]);
				}

				// CODIGO D�BITO AUTOM�TICO
				if (arrayImovel[62] != null) {
					imovel.setCodigoDebitoAutomatico((Integer) arrayImovel[62]);
				}

				if (arrayImovel[69] != null) {
					imovel.setIndicadorImovelAreaComum((Short) arrayImovel[69]);
				}

				boolean emitir = true;
				
				if (!sistemaParametro.getCodigoEmpresaFebraban().equals(
						SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COSANPA)) {
					
					if (sistemaParametro.getCodigoEmpresaFebraban().equals(
							SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)) {
						if (imovel.getImovelContaEnvio() != null
								&& (imovel.getImovelContaEnvio().getId()
										.equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL_FINAL_GRUPO))) {
							emitir = false;
						}
					} else {
						// SE N�O FOR CAERN, ENT�O � COMPESA OU CAER
						if (imovel.getImovelContaEnvio() != null
								&& (imovel
										.getImovelContaEnvio()
										.getId()
										.equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL)
										|| imovel
										.getImovelContaEnvio()
										.getId()
										.equals(ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL)
										|| imovel
										.getImovelContaEnvio()
										.getId()
										.equals(ImovelContaEnvio.ENVIAR_CONTA_BRAILLE) || imovel
										.getImovelContaEnvio()
										.getId()
										.equals(ImovelContaEnvio.ENVIAR_CONTA_BRAILLE_RESPONSAVEL))) {
							emitir = false;
						}
					}
				}

				if ((imovel.getImovelCondominio() != null
						&& imovel.getImovelCondominio().getId() != null && !imovel
						.getImovelCondominio().getId().equals(""))
						|| imovel.getIndicadorImovelCondominio().equals(
								ConstantesSistema.SIM)) {
					emitir = true;
				}

				// caso seja para emtir ou seja medido de �gua ou po�o
				if (emitir || existeHidrometroAgua || existeHidrometroPoco) {
					colecaoImoveis.add(imovel);
				}
			}
		}

		retorno[0] = colecaoImoveis;
		retorno[1] = quantidadeImoveis;

		if (imoveis != null) {
			imoveis.clear();
			imoveis = null;
		}

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 23/04/2008
	 * 
	 * @param imovel
	 * @param anoMesReferencia
	 * @return Conta
	 * @throws ControladorException
	 */
	public Conta pesquisarContaGerarArquivoTextoFaturamento(Imovel imovel,
			Integer anoMesReferencia, Integer idFaturamentoGrupo)
			throws ControladorException {

		Conta conta = null;
		Object[] arrayConta = null;

		try {

			arrayConta = repositorioFaturamento
					.pesquisarContaGerarArquivoTextoFaturamento(imovel.getId(),
							anoMesReferencia, idFaturamentoGrupo);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		if (arrayConta != null) {

			conta = new Conta();

			// ID
			conta.setId((Integer) arrayConta[18]);

			// GERENCIA REGIONAL E LOCALIDADE
			GerenciaRegional gerenciaRegional = new GerenciaRegional();
			gerenciaRegional.setNome((String) arrayConta[0]);
			gerenciaRegional.setId((Integer) arrayConta[20]);

			Localidade localidade = new Localidade();
			localidade.setId((Integer) arrayConta[1]);
			localidade.setDescricao((String) arrayConta[2]);
			localidade.setGerenciaRegional(gerenciaRegional);

			conta.setLocalidade(localidade);

			// SETOR_COMERCIAL
			conta.setCodigoSetorComercial((Integer) arrayConta[4]);

			// QUADRA
			conta.setQuadra((Integer) arrayConta[5]);

			// LOTE E SUBLOTE
			conta.setLote((Short) arrayConta[6]);
			conta.setSubLote((Short) arrayConta[7]);

			// CLIENTES: USUARIO E RESPONSAVEL
			Set colecaoClienteConta = new HashSet();
			if (arrayConta[3] != null) {
				ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
				clienteRelacaoTipo.setId(ClienteRelacaoTipo.USUARIO.intValue());

				Cliente clienteUsuario = new Cliente();
				clienteUsuario.setNome((String) arrayConta[3]);

				if (arrayConta[24] != null) {
					clienteUsuario.setCpf((String) arrayConta[24]);
				}

				if (arrayConta[25] != null) {
					clienteUsuario.setCnpj((String) arrayConta[25]);
				}

				ClienteConta clienteConta = new ClienteConta();
				clienteConta.setCliente(clienteUsuario);
				clienteConta.setClienteRelacaoTipo(clienteRelacaoTipo);

				colecaoClienteConta.add(clienteConta);
			}

			if (arrayConta[8] != null) {
				ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
				clienteRelacaoTipo.setId(ClienteRelacaoTipo.RESPONSAVEL
						.intValue());

				Cliente clienteResponsavel = new Cliente();
				clienteResponsavel.setId((Integer) arrayConta[8]);
				clienteResponsavel.setNome((String) arrayConta[9]);

				ClienteConta clienteConta = new ClienteConta();
				clienteConta.setCliente(clienteResponsavel);
				clienteConta.setClienteRelacaoTipo(clienteRelacaoTipo);

				colecaoClienteConta.add(clienteConta);
			}

			if (colecaoClienteConta.size() > 0) {
				conta.setClienteContas(colecaoClienteConta);
			}

			// LIGACAO_AGUA_SITUACAO
			if (arrayConta[10] != null) {
				LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
				ligacaoAguaSituacao.setId((Integer) arrayConta[10]);
				ligacaoAguaSituacao
						.setIndicadorFaturamentoSituacao((Short) arrayConta[21]);

				conta.setLigacaoAguaSituacao(ligacaoAguaSituacao);
			}

			// LIGACAO_ESGOTO_SITUACAO
			if (arrayConta[11] != null) {
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
				ligacaoEsgotoSituacao.setId((Integer) arrayConta[11]);
				ligacaoEsgotoSituacao
						.setIndicadorFaturamentoSituacao((Short) arrayConta[22]);

				conta.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
			}

			// DATA_VENCIMENTO
			conta.setDataVencimentoConta((Date) arrayConta[12]);

			// DATA_VALIDADE
			conta.setDataValidadeConta((Date) arrayConta[13]);

			// DIGITO_VERIFICADOR
			conta.setDigitoVerificadorConta((Short) arrayConta[14]);

			// PERCENTUAL_ESGOTO
			conta.setPercentualEsgoto((BigDecimal) arrayConta[15]);

			// IMOVEL_PERFIL
			ImovelPerfil imovelPerfil = new ImovelPerfil();
			imovelPerfil.setId((Integer) arrayConta[16]);

			conta.setImovelPerfil(imovelPerfil);

			// CONSUMO_TARIFA
			ConsumoTarifa consumoTarifa = new ConsumoTarifa();
			consumoTarifa.setId((Integer) arrayConta[17]);

			conta.setConsumoTarifa(consumoTarifa);

			// REFERENCIA
			conta.setReferencia((Integer) arrayConta[19]);

			// IMOVEL
			conta.setImovel(imovel);
		}

		return conta;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0001] - Gerar Arquivo Texto
	 * 
	 * @author Raphael Rossiter
	 * @date 24/04/2008
	 * 
	 * @param imovel
	 * @param conta
	 * @param anoMesReferencia
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	public Object[] gerarArquivoTexto(Imovel imovel, Conta conta,
			Integer anoMesReferencia, Rota rota,
			FaturamentoGrupo faturamentoGrupo,
			SistemaParametro sistemaParametro, Date dataComando)
			throws ControladorException {

		StringBuilder arquivoTexto = new StringBuilder();

		int quantidadeLinhas = 0;

		Date dataEmissao = Util.subtrairNumeroDiasDeUmaData(dataComando, 10);

		CobrancaDocumento cobrancaDocumento = null;
		try {

			cobrancaDocumento = repositorioCobranca
					.pesquisarCobrancaDocumentoImpressaoSimultanea(dataEmissao,
							imovel.getId());

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		Object[] retorno = new Object[2];

		// REGISTRO_TIPO_01
		arquivoTexto.append(this.gerarArquivoTextoRegistroTipo01(imovel, conta,
				anoMesReferencia, rota, faturamentoGrupo, sistemaParametro,
				cobrancaDocumento));
		quantidadeLinhas = quantidadeLinhas + 1;

		// REGISTRO_TIPO_02
		Object[] tipo2 = this.gerarArquivoTextoRegistroTipo02(imovel, conta,
				sistemaParametro);
		arquivoTexto.append(tipo2[0]);
		int quantidadeTipo2 = (Integer) tipo2[1];
		quantidadeLinhas = quantidadeLinhas + quantidadeTipo2;

		// REGISTRO_TIPO_03
		Object[] tipo3 = this.gerarArquivoTextoRegistroTipo03(imovel,
				anoMesReferencia);
		arquivoTexto.append(tipo3[0]);
		int quantidadeTipo3 = (Integer) tipo3[1];
		quantidadeLinhas = quantidadeLinhas + quantidadeTipo3;

		// REGISTRO_TIPO_04
		Object[] tipo4 = this.gerarArquivoTextoRegistroTipo04(conta);
		arquivoTexto.append(tipo4[0]);
		int quantidadeTipo4 = (Integer) tipo4[1];
		quantidadeLinhas = quantidadeLinhas + quantidadeTipo4;

		// REGISTRO_TIPO_05
		Object[] tipo5 = this.gerarArquivoTextoRegistroTipo05(conta);
		arquivoTexto.append(tipo5[0]);
		int quantidadeTipo5 = (Integer) tipo5[1];
		quantidadeLinhas = quantidadeLinhas + quantidadeTipo5;

		// REGISTRO_TIPO_06
		Object[] tipo6 = this.gerarArquivoTextoRegistroTipo06(conta);
		arquivoTexto.append(tipo6[0]);
		int quantidadeTipo6 = (Integer) tipo6[1];
		quantidadeLinhas = quantidadeLinhas + quantidadeTipo6;

		// REGISTRO_TIPO_07
		Object[] tipo7 = this.gerarArquivoTextoRegistroTipo07(imovel,
				sistemaParametro, cobrancaDocumento);
		arquivoTexto.append(tipo7[0]);
		int quantidadeTipo7 = (Integer) tipo7[1];
		quantidadeLinhas = quantidadeLinhas + quantidadeTipo7;

		// REGISTRO_TIPO_08
		Object[] tipo8 = this.gerarArquivoTextoRegistroTipo08(imovel,
				anoMesReferencia, sistemaParametro);
		arquivoTexto.append(tipo8[0]);
		int quantidadeTipo8 = (Integer) tipo8[1];
		quantidadeLinhas = quantidadeLinhas + quantidadeTipo8;

		// Parte dos dados das tarifas e faixas
		// REGISTRO_TIPO_09 e TIPO_10
		Object[] registroTipo9e10 = gerarArquivoTextoRegistroDadosTarifa09(
				imovel, sistemaParametro, anoMesReferencia, faturamentoGrupo);

		arquivoTexto.append(registroTipo9e10[0]);
		int quantidadeTipo9 = (Integer) registroTipo9e10[1];
		quantidadeLinhas = quantidadeLinhas + quantidadeTipo9;

		retorno[0] = arquivoTexto;
		retorno[1] = quantidadeLinhas;

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0001] - Gerar Arquivo Texto - Registro Tipo 01
	 * 
	 * @author Raphael Rossiter, Mariana Victor, Magno Gouveia
	 * @date 23/04/2008, 11/03/2011, 24/08/2011
	 * 
	 * @param imovel
	 * @param conta
	 * @param anoMesReferencia
	 * @return StringBuilder
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	public StringBuilder gerarArquivoTextoRegistroTipo01(Imovel imovel,
			Conta conta, Integer anoMesReferencia, Rota rota,
			FaturamentoGrupo faturamentoGrupo,
			SistemaParametro sistemaParametro,
			CobrancaDocumento cobrancaDocumento) throws ControladorException {

		StringBuilder arquivoTextoRegistroTipo01 = new StringBuilder();

		Cliente clienteUsuario = null;
		Cliente clienteResponsavel = null;

		String nomeGerenciaRegional = null;
		Integer idGerenciaRegional = null;
		String descricaoLocalidade = null;
		Set colecaoClienteImovelOUConta = null;
		String inscricaoImovel = null;
		String idLigacaoAguaSituacao = null;
		String idLigacaoEsgotoSituacao = null;
		String idImovelPerfil = null;
		Integer idLocalidade = null;
		Integer idSetorComercial = null;
		Integer numeroSequencialRota = null;
		Integer idQuadraFace = null;
		Short indicadorFaturamentoSituacaoAgua = null;
		Short indicadorFaturamentoSituacaoEsgoto = null;
		short indicadorParalisacaoFaturamentoAgua = 2;
		short indicadorParalisacaoFaturamentoEsgoto = 2;

		if (conta != null) {

			nomeGerenciaRegional = conta.getLocalidade().getGerenciaRegional()
					.getNome();
			idGerenciaRegional = conta.getLocalidade().getGerenciaRegional()
					.getId();
			idLocalidade = conta.getLocalidade().getId();
			if (conta.getImovel() != null
					&& conta.getImovel().getSetorComercial() != null) {
				idSetorComercial = conta.getImovel().getSetorComercial()
						.getId();
			}
			if (conta.getImovel() != null
					&& conta.getImovel().getNumeroSequencialRota() != null) {
				numeroSequencialRota = conta.getImovel()
						.getNumeroSequencialRota();
			}
			descricaoLocalidade = conta.getLocalidade().getDescricao();
			colecaoClienteImovelOUConta = conta.getClienteContas();

			// MONTANDO INSCRI��O DO IMOVEL A PARTIR DA CONTA
			// -----------------------------------------------------------------
			Imovel imovelInscricao = new Imovel();
			imovelInscricao.setLocalidade(conta.getLocalidade());

			SetorComercial setorComercial = new SetorComercial();
			setorComercial.setCodigo(conta.getCodigoSetorComercial());
			imovelInscricao.setSetorComercial(setorComercial);

			Quadra quadra = new Quadra();
			quadra.setNumeroQuadra(conta.getQuadra());
			imovelInscricao.setQuadra(quadra);

			imovelInscricao.setLote(conta.getLote());
			imovelInscricao.setSubLote(conta.getSubLote());

			inscricaoImovel = imovelInscricao.getInscricaoFormatadaSemPonto();
			// -----------------------------------------------------------------

			idLigacaoAguaSituacao = conta.getLigacaoAguaSituacao().getId()
					.toString();
			indicadorFaturamentoSituacaoAgua = conta.getLigacaoAguaSituacao()
					.getIndicadorFaturamentoSituacao();
			idLigacaoEsgotoSituacao = conta.getLigacaoEsgotoSituacao().getId()
					.toString();
			indicadorFaturamentoSituacaoEsgoto = conta
					.getLigacaoEsgotoSituacao()
					.getIndicadorFaturamentoSituacao();
			idImovelPerfil = conta.getImovelPerfil().getId().toString();
		} else {

			nomeGerenciaRegional = imovel.getLocalidade().getGerenciaRegional()
					.getNome();
			idGerenciaRegional = imovel.getLocalidade().getGerenciaRegional()
					.getId();
			idLocalidade = imovel.getLocalidade().getId();
			if (imovel.getSetorComercial() != null) {
				idSetorComercial = imovel.getSetorComercial().getId();
			}
			if (imovel.getNumeroSequencialRota() != null) {
				numeroSequencialRota = imovel.getNumeroSequencialRota();
			}
			descricaoLocalidade = imovel.getLocalidade().getDescricao();
			colecaoClienteImovelOUConta = imovel.getClienteImoveis();
			inscricaoImovel = imovel.getInscricaoFormatadaSemPonto();
			idLigacaoAguaSituacao = imovel.getLigacaoAguaSituacao().getId()
					.toString();
			indicadorFaturamentoSituacaoAgua = imovel.getLigacaoAguaSituacao()
					.getIndicadorFaturamentoSituacao();
			idLigacaoEsgotoSituacao = imovel.getLigacaoEsgotoSituacao().getId()
					.toString();
			indicadorFaturamentoSituacaoEsgoto = imovel
					.getLigacaoEsgotoSituacao()
					.getIndicadorFaturamentoSituacao();
			idImovelPerfil = imovel.getImovelPerfil().getId().toString();
		}

		/**
		 * autor: Adriana Muniz Data: 04/05/2011 Preenche o id da
		 * quadraFace - Utilizado na pesquisa da qualidade da agua
		 * */
		if (imovel.getQuadraFace() != null) {
			idQuadraFace = imovel.getQuadraFace().getId();
		}
		// TIPO DO REGISTRO
		arquivoTextoRegistroTipo01.append("01");

		// MATR�CULA DO IM�VEL
		arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(9,
				imovel.getId().toString()));

		// NOME DA GER�NCIA REGIONAL
		arquivoTextoRegistroTipo01.append(Util.completaString(
				nomeGerenciaRegional, 25));

		// DESCRI��O DA LOCALIDADE
		arquivoTextoRegistroTipo01.append(Util.completaString(
				descricaoLocalidade, 25));

		Iterator iteratorClienteImovelOUConta = colecaoClienteImovelOUConta
				.iterator();

		Cliente clienteNomeConta = null;

		while (iteratorClienteImovelOUConta.hasNext()) {

			Object clienteImovelOUConta = iteratorClienteImovelOUConta.next();

			ClienteRelacaoTipo clienteRelacaoTipo = null;
			Cliente cliente = null;

			if (clienteImovelOUConta instanceof ClienteImovel) {

				/**
				 * 
				 * 
				 * Pamela Gatinho - 27/09/2011
				 * 
				 * Alteracao para enviar na conta o nome selecionado no cadastro
				 * do im�vel
				 */
				ClienteImovel clienteImovel = (ClienteImovel) clienteImovelOUConta;

				System.out.println("-- IM�VEL --");

				clienteRelacaoTipo = ((ClienteImovel) clienteImovelOUConta)
						.getClienteRelacaoTipo();
				/**
				 * 
				 * Clientes que sa�ram com o nome em branco 
				 * nas contas.
				 * 
				 * @author Wellington Rocha
				 * @date 19/09/2013*/
				FiltroCliente filtroCliente = new FiltroCliente();
				filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,new Integer(clienteImovel.getCliente().getId())));
				Collection clientes = Fachada.getInstancia().pesquisarCliente(filtroCliente);
				if(!clientes.isEmpty()){
					cliente = (Cliente)clientes.iterator().next();
				}
				System.out.println("Id: " + clienteImovel.getCliente().getId());
				System.out.println("IndicadorNomeConta: " + clienteImovel.getIndicadorNomeConta());
				System.out.println("Im�vel: " + imovel.getId());
				if (clienteImovel.getIndicadorNomeConta().equals(
						ConstantesSistema.SIM)) {
					clienteNomeConta = cliente;
				}
				System.out.println("------------");
			} else {

				clienteRelacaoTipo = ((ClienteConta) clienteImovelOUConta)
						.getClienteRelacaoTipo();
				cliente = ((ClienteConta) clienteImovelOUConta).getCliente();
			}

			if (clienteRelacaoTipo.getId().equals(
					ClienteRelacaoTipo.USUARIO.intValue())) {
				clienteUsuario = cliente;
			} else {
				clienteResponsavel = cliente;
			}
		}

		/**
		 * Im�veis com nomeImovel diferente de nulo
		 * por�m com valor vazio tiveram suas contas impressas com o nome do
		 * cliente em branco. Para corrigir o problema n�o � mais testado o nome
		 * do im�vel, passando a considerar sempre o nome do cliente usu�rio.
		 * 
		 * @author Wellington Rocha
		 * @date 12/12/2012
		 */
		// NOME DO IM�VEL OU NOME DO CLIENTE USU�RIO
		/*
		 * if (imovel.getNomeImovel() != null){
		 * arquivoTextoRegistroTipo01.append
		 * (Util.completaString(imovel.getNomeImovel(), 30)); } else{
		 */

		arquivoTextoRegistroTipo01.append(Util.completaString(
				clienteUsuario.getNome(), 30));
		// }

		if (conta != null) {

			// DATA DE VENCIMENTO
			arquivoTextoRegistroTipo01.append(Util.formatarDataAAAAMMDD(conta
					.getDataVencimentoConta()));

			// DATA DE VALIDADE
			arquivoTextoRegistroTipo01.append(Util.formatarDataAAAAMMDD(conta
					.getDataValidadeConta()));
		} else {

			// DATA DE VENCIMENTO E DATA DE VALIDADE
			arquivoTextoRegistroTipo01.append(Util.completaString("", 16));
		}

		// INSCRI��O DO IM�VEL
		arquivoTextoRegistroTipo01.append(Util.completaString(inscricaoImovel,
				17));

		// ENDERE�O DO IM�VEL
		// [UC0085] - Obter Endere�o
		String enderecoImovel = getControladorEndereco()
				.pesquisarEnderecoFormatado(imovel.getId());

		arquivoTextoRegistroTipo01.append(Util.completaString(
				enderecoImovel == null ? "" : enderecoImovel, 70));

		if (conta != null) {

			// M�S/ANO DE REFER�NCIA DA CONTA E DIGITO VERIFICADOR
			arquivoTextoRegistroTipo01.append("" + conta.getReferencia());
			arquivoTextoRegistroTipo01
					.append(conta.getDigitoVerificadorConta());

		} else {

			// M�S/ANO DE REFER�NCIA DA CONTA E DIGITO VERIFICADOR
			arquivoTextoRegistroTipo01.append(Util.completaString("", 7));
		}

		/**
		 *  Pamela Gatinho - 27/09/2011
		 * 
		 * Alteracao para enviar a conta para o endereco do im�vel ou do
		 * respons�vel, de acordo com a op��o selecionada no cadastro do im�vel
		 */
		// CLIENTE RESPONS�VEL - C�DIGO, NOME E ENDERE�O
		if (clienteResponsavel != null) {

			if (clienteNomeConta != null) {

				arquivoTextoRegistroTipo01.append(Util
						.adicionarZerosEsquedaNumero(9, clienteNomeConta
								.getId().toString()));
				arquivoTextoRegistroTipo01.append(Util.completaString(
						clienteNomeConta.getNome(), 25));

			} else {

				arquivoTextoRegistroTipo01.append(Util
						.adicionarZerosEsquedaNumero(9, clienteResponsavel
								.getId().toString()));
				arquivoTextoRegistroTipo01.append(Util.completaString(
						clienteResponsavel.getNome(), 25));

			}

			if (imovel.getImovelContaEnvio().getId()
					.equals(ImovelContaEnvio.ENVIAR_IMOVEL)) {

				// ENDERE�O DO IM�VEL
				arquivoTextoRegistroTipo01.append(Util.completaString(
						enderecoImovel == null ? "" : enderecoImovel, 75));
			} else {

				// [UC0085] - Obter Endere�o
				String enderecoCorrespondencia = getControladorEndereco()
						.pesquisarEnderecoClienteAbreviado(
								clienteResponsavel.getId());

				arquivoTextoRegistroTipo01.append(Util.completaString(
						enderecoCorrespondencia == null ? ""
								: enderecoCorrespondencia, 75));
			}
		} else {

			arquivoTextoRegistroTipo01.append(Util.completaString("", 109));
		}

		// LIGACAO_SITUACAO_AGUA
		arquivoTextoRegistroTipo01.append(idLigacaoAguaSituacao);

		// LIGACAO_SITUACAO_AGUA
		arquivoTextoRegistroTipo01.append(idLigacaoEsgotoSituacao);

		// BANCO E AG�NCIA
		Object[] parmsDebitoAutomatico = this.getControladorArrecadacao()
				.pesquisarParmsDebitoAutomatico(imovel.getId());

		if (parmsDebitoAutomatico != null) {

			// NOME DO BANCO
			if (parmsDebitoAutomatico[0] != null) {
				arquivoTextoRegistroTipo01.append(Util.completaString(
						(String) parmsDebitoAutomatico[0], 15));
			} else {
				arquivoTextoRegistroTipo01.append(Util.completaString("", 15));
			}

			// C�DIGO DA AG�NCIA
			arquivoTextoRegistroTipo01.append(Util.completaString(
					(String) parmsDebitoAutomatico[1], 5));

		} else {

			arquivoTextoRegistroTipo01.append(Util.completaString("", 20));
		}

		// MATR�CULA DO IM�VEL CONDOM�NIO
		if (imovel.getImovelCondominio() != null) {

			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
					9, imovel.getImovelCondominio().getId().toString()));
		} else {

			arquivoTextoRegistroTipo01.append(Util.completaString("", 9));
		}

		// INDICADOR IM�VEL CONDOM�NIO
		arquivoTextoRegistroTipo01.append(imovel.getIndicadorImovelCondominio()
				.toString());

		// IMOVEL_PERFIL
		/*
		 * Acr�scimo de uma caracter, para casos em que o id do
		 * perfil do imovel tem dois carateres. Com essa altera��o, a linha do
		 * tipo 1 foi acrescida de uma caracter
		 */
		arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(2,
				idImovelPerfil));

		/**
		 *  Alterando o c�lculo da m�dia
		 */
		MedicaoTipo medicao = new MedicaoTipo();
		medicao.setId(MedicaoTipo.LIGACAO_AGUA);
		
		boolean houveIntslacaoHidrometro = this
				.getControladorMicromedicao()
				.verificarInstalacaoSubstituicaoHidrometro(
						imovel.getId(), medicao);
		
		// CONSUMO M�DIO DO IM�VEL

		// [UC1113 - Obter Volume M�dio �gua ou Esgoto]
		int[] consumoMedioLigacaoAgua = this.getControladorMicromedicao()
				.obterVolumeMedioAguaEsgoto(imovel.getId(),
						faturamentoGrupo.getAnoMesReferencia(),
						LigacaoTipo.LIGACAO_AGUA, houveIntslacaoHidrometro);

		arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(6,
				String.valueOf(consumoMedioLigacaoAgua[0])));

		// INDICADOR_FATURAMENTO_ESGOTO
		arquivoTextoRegistroTipo01.append(indicadorFaturamentoSituacaoAgua
				.toString());

		// INDICADOR_FATURAMENTO_ESGOTO
		arquivoTextoRegistroTipo01.append(indicadorFaturamentoSituacaoEsgoto
				.toString());

		// INDICADOR_EMISSAO_CONTA
		Short indicadorEmissaoConta = new Short("1");

		/**
		 * Altera��o feita para corrigir o problema de emitir
		 * conta de im�veis que possuem faturas agrupadas
		 */
		boolean naoEmitir = false;
		if (sistemaParametro.getCodigoEmpresaFebraban().equals(
				SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)
				|| sistemaParametro.getCodigoEmpresaFebraban().equals(
						SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COSANPA)) {
			if (imovel.getImovelContaEnvio() != null
					&& (imovel.getImovelContaEnvio().getId()
							.equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL_FINAL_GRUPO))) {
				naoEmitir = true;
			}
		} else {
			if (imovel.getImovelContaEnvio() != null
					&& (imovel
							.getImovelContaEnvio()
							.getId()
							.equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL)
							|| imovel
									.getImovelContaEnvio()
									.getId()
									.equals(ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL)
							|| imovel
									.getImovelContaEnvio()
									.getId()
									.equals(ImovelContaEnvio.ENVIAR_CONTA_BRAILLE) || imovel
							.getImovelContaEnvio()
							.getId()
							.equals(ImovelContaEnvio.ENVIAR_CONTA_BRAILLE_RESPONSAVEL))) {
				naoEmitir = true;
			}
		}

		if (clienteResponsavel != null && naoEmitir) {
			indicadorEmissaoConta = new Short("2");
		}

		arquivoTextoRegistroTipo01.append(indicadorEmissaoConta.toString());

		// CONSUMO_MINIMO_AGUA
		if (imovel.getLigacaoAgua() != null
				&& imovel.getLigacaoAgua().getNumeroConsumoMinimoAgua() != null
				&& !imovel.getLigacaoAgua().getNumeroConsumoMinimoAgua()
						.equals("")) {
			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
					6, imovel.getLigacaoAgua().getNumeroConsumoMinimoAgua()
							.toString()));
		} else {
			arquivoTextoRegistroTipo01.append(Util.completaString("", 6));
		}

		// CONSUMO_MINIMO_ESGOTO
		if (imovel.getLigacaoEsgoto() != null
				&& imovel.getLigacaoEsgoto().getConsumoMinimo() != null) {
			arquivoTextoRegistroTipo01
					.append(Util.adicionarZerosEsquedaNumero(6, imovel
							.getLigacaoEsgoto().getConsumoMinimo().toString()));
		} else {
			arquivoTextoRegistroTipo01.append(Util.completaString("", 6));
		}

		// PERCENTUAL_ESGOTO_LIGACAO
		if (imovel.getLigacaoEsgoto() != null
				&& imovel.getLigacaoEsgoto()
						.getPercentualAguaConsumidaColetada() != null) {
			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
					6, Util.formatarBigDecimalComPonto(imovel
							.getLigacaoEsgoto()
							.getPercentualAguaConsumidaColetada())));
		} else {
			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
					6, "0"));
		}

		// PERCENTUAL_ESGOTO_COBRANCA
		/**
		 *  Pamela Gatinho - 19/07/2012 Altera��o para informar o
		 * percentual de esgoto para im�vel condom�nio
		 */
//		BigDecimal percentualEsgoto = this.getControladorFaturamento().obterPercentualColetaEsgotoImovel(imovel.getId());
		BigDecimal percentualEsgoto = this.getControladorFaturamento().verificarPercentualEsgotoAlternativo(imovel, null);

		// if (conta != null){
		if (percentualEsgoto != null) {
			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
					6,
					// Util.formatarBigDecimalComPonto(conta.getPercentualEsgoto())));
					Util.formatarBigDecimalComPonto(percentualEsgoto)));
		} else {
			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
					6, "0"));
		}

		// TIPO_PO�O
		if (imovel.getPocoTipo() != null) {
			arquivoTextoRegistroTipo01.append(imovel.getPocoTipo().getId()
					.toString());
		} else {
			arquivoTextoRegistroTipo01.append(Util.completaString("", 1));
		}

		// CONSUMO_TARIFA
		arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(2,
				imovel.getConsumoTarifa().getId().toString()));

		// CATEGORIA PRINCIPAL
		Collection colecaoCategoria = null;

		// Obt�m a quantidade de economias por categoria
		colecaoCategoria = getControladorImovel()
				.obterQuantidadeEconomiasCategoria(imovel);

		int consumoTotalReferenciaAltoConsumo = 0;
		int consumoTotalReferenciaEstouroConsumo = 0;
		int consumoTotalReferenciaBaixoConsumo = 0;
		int consumoMaximoCobrancaEstouroConsumo = 0;
		int maiorQuantidadeEconomia = 0;
		BigDecimal vezesMediaAltoConsumo = new BigDecimal(0);
		BigDecimal vezesMediaEstouroConsumo = new BigDecimal(0);
		BigDecimal percentualDeterminacaoBaixoConsumo = new BigDecimal(0);

		Iterator colecaoCategoriaIterator = colecaoCategoria.iterator();

		while (colecaoCategoriaIterator.hasNext()) {

			Categoria categoria = (Categoria) colecaoCategoriaIterator.next();

			// Multiplica o consumo por economia de referencia (consumo estouro)
			// pr n�mero de economias do im�vel
			consumoTotalReferenciaAltoConsumo = consumoTotalReferenciaAltoConsumo
					+ (categoria.getConsumoAlto().intValue() * categoria
							.getQuantidadeEconomiasCategoria().intValue());

			// Multiplica o consumo por economia de referencia (consumo estouro)
			// pr n�mero de economias do im�vel
			consumoTotalReferenciaEstouroConsumo = consumoTotalReferenciaEstouroConsumo
					+ (categoria.getConsumoEstouro().intValue() * categoria
							.getQuantidadeEconomiasCategoria().intValue());

			// Multiplica o consumo por economia de referencia (consumo estouro)
			// pr n�mero de economias do im�vel
			consumoMaximoCobrancaEstouroConsumo = consumoMaximoCobrancaEstouroConsumo
					+ (categoria.getNumeroConsumoMaximoEc().intValue() * categoria
							.getQuantidadeEconomiasCategoria().intValue());

			consumoTotalReferenciaBaixoConsumo = consumoTotalReferenciaBaixoConsumo
					+ (categoria.getMediaBaixoConsumo().intValue() * categoria
							.getQuantidadeEconomiasCategoria().intValue());

			// Obt�m a maior quantidade de economias e a vezes m�dia de estouro
			if (maiorQuantidadeEconomia < categoria
					.getQuantidadeEconomiasCategoria().intValue()) {

				maiorQuantidadeEconomia = categoria
						.getQuantidadeEconomiasCategoria().intValue();

				vezesMediaAltoConsumo = categoria.getVezesMediaAltoConsumo();
				vezesMediaEstouroConsumo = categoria.getVezesMediaEstouro();
				percentualDeterminacaoBaixoConsumo = categoria
						.getPorcentagemMediaBaixoConsumo();
			}
		}

		// CONSUMO_REFERENCIA_ESTOURO_CONSUMO
		if (consumoTotalReferenciaEstouroConsumo <= 999999) {
			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
					6, consumoTotalReferenciaEstouroConsumo + ""));
		} else {
			arquivoTextoRegistroTipo01.append("999999");
		}

		// CONSUMO_REFERENCIA_ALTO_CONSUMO
		if (consumoTotalReferenciaAltoConsumo <= 999999) {
			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
					6, consumoTotalReferenciaAltoConsumo + ""));
		} else {
			arquivoTextoRegistroTipo01.append("999999");
		}

		// CONSUMO_MEDIA_BAIXO_CONSUMO
		if (consumoTotalReferenciaBaixoConsumo <= 999999) {
			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
					6, consumoTotalReferenciaBaixoConsumo + ""));
		} else {
			arquivoTextoRegistroTipo01.append("999999");
		}

		// FATOR_MULTIPLICACAO_MEDIA_ESTOURO_CONSUMO
		if (vezesMediaEstouroConsumo != null) {
			arquivoTextoRegistroTipo01.append(Util.completaString(
					vezesMediaEstouroConsumo.toString(), 4));
		} else {
			arquivoTextoRegistroTipo01.append(Util.completaString("", 4));
		}

		// FATOR_MULTIPLICACAO_MEDIA_ALTO_CONSUMO
		if (vezesMediaAltoConsumo != null) {
			arquivoTextoRegistroTipo01.append(Util.completaString(
					vezesMediaAltoConsumo.toString(), 4));
		} else {
			arquivoTextoRegistroTipo01.append(Util.completaString("", 4));
		}

		// PERCENTUAL_DETERMINACAO_BAIXO_CONSUMO
		if (percentualDeterminacaoBaixoConsumo != null) {
			arquivoTextoRegistroTipo01
					.append(Util.adicionarZerosEsquedaNumero(
							6,
							Util.formatarBigDecimalComPonto(percentualDeterminacaoBaixoConsumo)));
		} else {
			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
					6, "0"));
		}

		// CONSUMO_MAXIMO_COBRANCA_ESTOURO_CONSUMO
		if (consumoMaximoCobrancaEstouroConsumo <= 999999) {
			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
					6, consumoMaximoCobrancaEstouroConsumo + ""));
		} else {
			arquivoTextoRegistroTipo01.append("999999");
		}

		// FATURAMENTO_GRUPO
		arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(3,
				faturamentoGrupo.getId().toString()));

		// CODIGO_ROTA
		arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(7,
				rota.getCodigo().toString()));

		// C�DIGO DA CONTA
		if (conta != null) {

			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
					9, conta.getId().toString()));
		} else {

			arquivoTextoRegistroTipo01.append(Util.completaString("", 9));
		}

		// N�MERO DA CONTA

		// Tipo da calculo da tarifa
		if (imovel.getConsumoTarifa() != null
				&& imovel.getConsumoTarifa().getTarifaTipoCalculo() != null) {
			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
					2, ""
							+ imovel.getConsumoTarifa().getTarifaTipoCalculo()
									.getId()));
		}

		// Endere�o Atendimento 1� Parte
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, idLocalidade));

		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");

		Collection cLocalidade = (Collection) getControladorUtil().pesquisar(
				filtroLocalidade, Localidade.class.getName());

		Localidade localidade = (Localidade) cLocalidade.iterator().next();

		String descricaoAtendimento = localidade
				.getEnderecoFormatadoTituloAbreviado();

		arquivoTextoRegistroTipo01.append(Util.completaString(
				descricaoAtendimento, 70));

		// Endere�o Atendimento 2� Parte
		String dddMunicipio = "";
		if (localidade.getLogradouroBairro() != null
				&& localidade.getLogradouroBairro().getBairro() != null
				&& localidade.getLogradouroBairro().getBairro().getMunicipio() != null
				&& localidade.getLogradouroBairro().getBairro().getMunicipio()
						.getDdd() != null) {
			dddMunicipio = ""
					+ localidade.getLogradouroBairro().getBairro()
							.getMunicipio().getDdd();
		}

		String fome = "";
		if (localidade.getFone() != null) {
			fome = localidade.getFone();
		}

		arquivoTextoRegistroTipo01.append(Util.completaString(dddMunicipio
				+ fome, 11));

		// Sequencial de rota
		if (numeroSequencialRota != null) {
			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
					9, "" + numeroSequencialRota));
		} else {
			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
					9, ""));
		}

		// Mensagem da conta em 3 partes
		EmitirContaHelper emitirContaHelper = new EmitirContaHelper();
		emitirContaHelper.setAmReferencia(faturamentoGrupo
				.getAnoMesReferencia());
		emitirContaHelper.setAnoMesFaturamentoGrupo(faturamentoGrupo
				.getAnoMesReferencia());
		emitirContaHelper.setIdFaturamentoGrupo(faturamentoGrupo.getId());
		emitirContaHelper.setIdGerenciaRegional(idGerenciaRegional);
		emitirContaHelper.setIdLocalidade(idLocalidade);
		emitirContaHelper.setIdSetorComercial(idSetorComercial);
		emitirContaHelper.setIdImovel(imovel.getId());
		// Caso a empresa seja a CAER
		if (sistemaParametro.getCodigoEmpresaFebraban().equals(
				SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER)) {
			String[] mensagemContaDividida = getControladorFaturamento()
					.obterMensagemConta(emitirContaHelper, sistemaParametro, 4,
							null);
			if (mensagemContaDividida != null) {
				// Parte 1
				arquivoTextoRegistroTipo01.append(Util.completaString(
						mensagemContaDividida[0], 60));
				// Parte 2
				arquivoTextoRegistroTipo01.append(Util.completaString(
						mensagemContaDividida[1], 60));
				// Parte 3
				arquivoTextoRegistroTipo01.append(Util.completaString(
						mensagemContaDividida[2], 60));
				// Parte 4
				arquivoTextoRegistroTipo01.append(Util.completaString(
						mensagemContaDividida[3], 60));
				// Parte 5
				arquivoTextoRegistroTipo01.append(Util.completaString(
						mensagemContaDividida[4], 60));

			} else {
				arquivoTextoRegistroTipo01.append(Util.completaString("", 300));
			}
		} else {
			String[] mensagemContaDividida = getControladorFaturamento()
					.obterMensagemConta3Partes(emitirContaHelper,
							sistemaParametro);
			if (mensagemContaDividida != null) {
				// Parte 1
				arquivoTextoRegistroTipo01.append(Util.completaString(
						mensagemContaDividida[0], 100));
				// Parte 2
				arquivoTextoRegistroTipo01.append(Util.completaString(
						mensagemContaDividida[1], 100));
				// Parte 3
				arquivoTextoRegistroTipo01.append(Util.completaString(
						mensagemContaDividida[2], 100));

			} else {
				arquivoTextoRegistroTipo01.append(Util.completaString("", 300));
			}
		}

		// Incluir mensagem de quita��o anual de d�bitos
		String msgQuitacaoDebitos = getControladorFaturamento()
				.obterMsgQuitacaoDebitos(imovel, anoMesReferencia);

		arquivoTextoRegistroTipo01.append(Util.completaString(
				msgQuitacaoDebitos, 120));

		// Qualidade �gua
		Integer anoMesReferenciaQualidadeAgua = null;

		if (sistemaParametro.getNomeEmpresa() != null
				&& sistemaParametro.getNomeEmpresa().equals(
						SistemaParametro.EMPRESA_COMPESA)) {
			anoMesReferenciaQualidadeAgua = Util
					.subtraiAteSeisMesesAnoMesReferencia(
							faturamentoGrupo.getAnoMesReferencia(), 1);
		} else {
			anoMesReferenciaQualidadeAgua = faturamentoGrupo
					.getAnoMesReferencia();
		}
		/**
		 * autor: Adriana Muniz Data: 04/05/2011 Como o crit�rio de
		 * preenchimento da qualidade da agua da cosanpa n�o � usa os campos
		 * usados como filtros na consulta da qualidade da agua, foi necess�rio
		 * acrescentar mais um parametro na assinatura no m�todo, idQuadraFace,
		 * que ser� usado na primeira op��o de busca pela qualidade da �gua
		 * 
		 * Foi necess�rio acrescentar um parametro a mais na assinatura do
		 * m�todo, o sistema de parametro para que a rotina de consulta da
		 * qualidade da agua seja diferente.
		 * */
		arquivoTextoRegistroTipo01 = arquivoTextoRegistroTipo01
				.append(gerarArquivoTextoQualidadeAgua(idLocalidade,
						idSetorComercial, anoMesReferenciaQualidadeAgua,
						idQuadraFace));

		Integer consumoMinimoImovel = null;
		/*
		 * Dentro desse m�todo j� verifica se o indicador tipo de consumo � por
		 * categoria ou por subcategoria
		 */
		consumoMinimoImovel = getControladorMicromedicao()
				.obterConsumoMinimoLigacao(imovel, null);

		// CONSUMO MINIMO IM�VEL
		if (consumoMinimoImovel != null) {
			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
					6, "" + consumoMinimoImovel));
		} else {
			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
					6, ""));

		}

		// CONSUMO MINIMO IM�VEL N�O MEDIDO
		int consumoMinimoNaoMedido = getControladorMicromedicao()
				.obterConsumoNaoMedido(imovel);

		arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(6,
				"" + consumoMinimoNaoMedido));

		if (cobrancaDocumento != null && !cobrancaDocumento.equals("")) {
			// Documento de Cobran�a
			arquivoTextoRegistroTipo01.append(Util.completaString(
					cobrancaDocumento.getId() + "", 9));

			String representacaoNumericaCodBarra = "";
			// Obt�m a representa��o num�rica do
			// c�digode
			// barra
			representacaoNumericaCodBarra = this.getControladorArrecadacao()
					.obterRepresentacaoNumericaCodigoBarra(
							5,
							cobrancaDocumento.getValorDocumento(),
							cobrancaDocumento.getLocalidade().getId(),
							cobrancaDocumento.getImovel().getId(),
							null,
							null,
							null,
							null,
							String.valueOf(cobrancaDocumento
									.getNumeroSequenciaDocumento()),
							cobrancaDocumento.getDocumentoTipo().getId(), null,
							null, null);

			// 57. C�digo de Barras do Documento do Cobran�a
			arquivoTextoRegistroTipo01.append(representacaoNumericaCodBarra);

		} else {
			arquivoTextoRegistroTipo01.append(Util.completaString("", 57));
		}

		// CPF ou CNPJ do CLIENTE
		String cpfCnpj = "";
		if (clienteUsuario != null && !clienteUsuario.equals("")) {
			if (clienteUsuario.getCpf() != null
					&& !clienteUsuario.getCpf().equals("")) {
				cpfCnpj = clienteUsuario.getCpf();
			} else {
				if (clienteUsuario.getCnpj() != null
						&& !clienteUsuario.getCnpj().equals("")) {
					cpfCnpj = clienteUsuario.getCnpj();
				}
			}
		}
		arquivoTextoRegistroTipo01.append(Util.completaString(cpfCnpj, 18));

		// GERA AS COLUNAS DA SITUA��O ESPECIAL DE FATURAMENTO
		arquivoTextoRegistroTipo01
				.append(gerarDadosSituacaoEspecialFaturamento(imovel,
						faturamentoGrupo));

		// DATA DE LEITURA ANTERIOR N�O MEDIDO

		// DATA LEITURA ANTERIOR E DATA LEITURA ATUAL
		Integer anoMesFaturamentoAnterior = Util.subtrairMesDoAnoMes(
				anoMesReferencia, 1);

		Date dataLeituraAnteriorFaturamento = null;

		try {

			dataLeituraAnteriorFaturamento = (Date) repositorioFaturamento
					.pesquisarFaturamentoAtividadeCronogramaDataPrevista(
							faturamentoGrupo.getId(),
							FaturamentoAtividade.EFETUAR_LEITURA,
							anoMesFaturamentoAnterior);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		if (dataLeituraAnteriorFaturamento == null
				|| dataLeituraAnteriorFaturamento.equals("")) {
			dataLeituraAnteriorFaturamento = Util.subtrairNumeroDiasDeUmaData(
					new Date(), 30);
		}

		arquivoTextoRegistroTipo01.append(Util
				.formatarDataAAAAMMDD(dataLeituraAnteriorFaturamento));

		// INDICADOR ABASTECIMENTO
		if (imovel.getLigacaoAguaSituacao() != null
				&& !imovel.getLigacaoAguaSituacao().equals("")) {
			arquivoTextoRegistroTipo01.append(Util.completaString(imovel
					.getLigacaoAguaSituacao().getIndicadorAbastecimento() + "",
					1));
		} else {
			arquivoTextoRegistroTipo01.append(Util.completaString("", 1));
		}

		// verificar se o im�vel � Sazonal
		boolean imovelSazonal = false;

		// [UC0108] - Obter Quantidade de Economias por Subcategoria
		Collection colecaoCategoriaOUSubcategoria = this.getControladorImovel()
				.obterQuantidadeEconomiasSubCategoria(imovel.getId());

		Iterator itSubcategoria = colecaoCategoriaOUSubcategoria.iterator();

		while (itSubcategoria.hasNext()) {

			Subcategoria subcategoria = (Subcategoria) itSubcategoria.next();

			if (subcategoria.getIndicadorSazonalidade().equals(
					ConstantesSistema.SIM)) {

				imovelSazonal = true;
				break;
			}
		}

		// IMOVEL SAZONAL
		if (imovelSazonal) {
			arquivoTextoRegistroTipo01.append("1");
		} else {
			arquivoTextoRegistroTipo01.append("2");
		}

		// Verificar se � para faturar pela situa��o especial de faturamento
		if (imovel.getFaturamentoSituacaoTipo() != null
				&& !imovel.getFaturamentoSituacaoTipo().equals("")) {
			FiltroFaturamentoSituacaoHistorico filtroFaturamentoSituacaoHistorico = new FiltroFaturamentoSituacaoHistorico();
			filtroFaturamentoSituacaoHistorico
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoSituacaoHistorico.ID_IMOVEL,
							imovel.getId()));
			filtroFaturamentoSituacaoHistorico
					.adicionarParametro(new ParametroNulo(
							FiltroFaturamentoSituacaoHistorico.ANO_MES_FATURAMENTO_RETIRADA));
			Collection<FaturamentoSituacaoHistorico> colFiltroFaturamentoSituacaoHistorico = this
					.getControladorUtil().pesquisar(
							filtroFaturamentoSituacaoHistorico,
							FaturamentoSituacaoHistorico.class.getName());

			FaturamentoSituacaoHistorico faturamentoSituacaoHistorico = (FaturamentoSituacaoHistorico) Util
					.retonarObjetoDeColecao(colFiltroFaturamentoSituacaoHistorico);

			if ((faturamentoSituacaoHistorico != null
					&& faturamentoGrupo.getAnoMesReferencia() >= faturamentoSituacaoHistorico
							.getAnoMesFaturamentoSituacaoInicio() && faturamentoGrupo
					.getAnoMesReferencia() <= faturamentoSituacaoHistorico
					.getAnoMesFaturamentoSituacaoFim())) {

				// INDICADOR_PARALIZA��O_�GUA
				if (imovel.getFaturamentoSituacaoTipo()
						.getIndicadorParalisacaoFaturamento() != null
						&& imovel.getFaturamentoSituacaoTipo()
								.getIndicadorParalisacaoFaturamento()
								.equals(ConstantesSistema.INDICADOR_USO_ATIVO)
						&& imovel.getFaturamentoSituacaoTipo()
								.getIndicadorValidoAgua()
								.equals(ConstantesSistema.INDICADOR_USO_ATIVO)) {

					indicadorParalisacaoFaturamentoAgua = 1;
				}

				// INDICADOR_PARALIZA��O_ESGOTO
				if (imovel.getFaturamentoSituacaoTipo()
						.getIndicadorParalisacaoFaturamento() != null
						&& imovel.getFaturamentoSituacaoTipo()
								.getIndicadorParalisacaoFaturamento()
								.equals(ConstantesSistema.INDICADOR_USO_ATIVO)
						&& imovel.getFaturamentoSituacaoTipo()
								.getIndicadorValidoEsgoto()
								.equals(ConstantesSistema.INDICADOR_USO_ATIVO)) {

					indicadorParalisacaoFaturamentoEsgoto = 1;
				}

			}
		}

		arquivoTextoRegistroTipo01.append(""
				+ indicadorParalisacaoFaturamentoAgua);

		arquivoTextoRegistroTipo01.append(""
				+ indicadorParalisacaoFaturamentoEsgoto);

		if (imovel.getCodigoDebitoAutomatico() != null
				&& !imovel.getCodigoDebitoAutomatico().equals("")) {
			arquivoTextoRegistroTipo01.append(Util.completaString(
					"" + imovel.getCodigoDebitoAutomatico(), 9));
		} else {
			arquivoTextoRegistroTipo01.append(Util.completaString("", 9));
		}

		// PERCENTUAL_ALTERNATIVO_ESGOTO_LIGACAO
		if (imovel.getLigacaoEsgoto() != null
				&& imovel.getLigacaoEsgoto().getPercentualAlternativo() != null) {
			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
					6, Util.formatarBigDecimalComPonto(imovel
							.getLigacaoEsgoto().getPercentualAlternativo())));
		} else {
			arquivoTextoRegistroTipo01.append(Util.completaString("", 6));
		}

		// CONSUMO_PERCENTUAL_ALTERNATIVO_ESGOTO
		if (imovel.getLigacaoEsgoto() != null
				&& imovel.getLigacaoEsgoto()
						.getNumeroConsumoPercentualAlternativo() != null) {
			arquivoTextoRegistroTipo01
					.append(Util
							.adicionarZerosEsquedaNumero(6, imovel
									.getLigacaoEsgoto()
									.getNumeroConsumoPercentualAlternativo()
									.toString()));
		} else {
			arquivoTextoRegistroTipo01.append(Util.completaString("", 6));
		}

		// DATA DA EMISS�O DO DOCUMENTO DE COBRANCA
		if (cobrancaDocumento != null) {
			arquivoTextoRegistroTipo01.append(Util
					.formatarDataAAAAMMDD(cobrancaDocumento.getEmissao()));
		} else {
			arquivoTextoRegistroTipo01.append(Util.completaString("", 8));
		}

		// [UC1113 - Obter Volume M�dio �gua ou Esgoto]
		int[] consumoMedioLigacaoEsgoto = this.getControladorMicromedicao()
				.obterVolumeMedioAguaEsgoto(imovel.getId(),
						faturamentoGrupo.getAnoMesReferencia(),
						LigacaoTipo.LIGACAO_ESGOTO, houveIntslacaoHidrometro);

//		arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(6,
//				String.valueOf(consumoMedioLigacaoEsgoto[0])));

		/*
		 * Magno Gouveia em 30/08/2011
		 */
//		if (imovel.getLigacaoAguaSituacao().getIndicadorConsumoReal() != null) {
//			arquivoTextoRegistroTipo01.append(String.valueOf(imovel
//					.getLigacaoAguaSituacao().getIndicadorConsumoReal()));
//		} else {
//			arquivoTextoRegistroTipo01.append(Util.completaString("", 1));
//		}

//		if (imovel.getLigacaoAguaSituacao().getNumeroDiasCorte() != null) {
//			arquivoTextoRegistroTipo01.append(Util.adicionarZerosEsquedaNumero(
//					2, String.valueOf(imovel.getLigacaoAguaSituacao()
//							.getNumeroDiasCorte())));
//		} else {
//			arquivoTextoRegistroTipo01.append(Util.completaString("", 2));
//		}
//
//		if (imovel.getLigacaoAgua() != null
//				&& !imovel.getLigacaoAgua().equals("")
//				&& imovel.getLigacaoAgua().getDataCorte() != null) {
//			arquivoTextoRegistroTipo01.append(Util.formatarDataAAAAMMDD(imovel
//					.getLigacaoAgua().getDataCorte()));
//		} else {
//			arquivoTextoRegistroTipo01.append(Util.completaString("", 8));
//		}

		arquivoTextoRegistroTipo01.append(System.getProperty("line.separator"));

		return arquivoTextoRegistroTipo01;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0001] - Gerar Arquivo Texto - Registro Tipo 02
	 * 
	 * @author Raphael Rossiter
	 * @date 24/04/2008
	 * 
	 * @param imovel
	 * @param conta
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	public Object[] gerarArquivoTextoRegistroTipo02(Imovel imovel, Conta conta,
			SistemaParametro sistemaParametro) throws ControladorException {

		StringBuilder arquivoTextoRegistroTipo02 = new StringBuilder();

		int quantidadeLinhas = 0;

		Object[] retorno = new Object[2];

		short icTarifaCategoria = sistemaParametro
				.getIndicadorTarifaCategoria();

		if (icTarifaCategoria == SistemaParametro.INDICADOR_TARIFA_CATEGORIA) {

			Collection colecaoCategoria = null;

			if (conta != null) {

				// PESQUISANDO A PARTIR DA TABELA CONTA_CATEGORIA
				colecaoCategoria = this.getControladorImovel()
						.obterQuantidadeEconomiasContaCategoria(conta);
			} else {

				// PESQUISANDO A PARTIR DA TABELA IMOVEL_SUBCATEGORIA
				colecaoCategoria = this.getControladorImovel()
						.obterQuantidadeEconomiasCategoria(imovel);
			}

			if (colecaoCategoria != null && !colecaoCategoria.isEmpty()) {

				Iterator iterator = colecaoCategoria.iterator();

				while (iterator.hasNext()) {

					Categoria categoria = (Categoria) iterator.next();

					quantidadeLinhas = quantidadeLinhas + 1;

					// TIPO DO REGISTRO
					arquivoTextoRegistroTipo02.append("02");

					// MATR�CULA DO IM�VEL
					arquivoTextoRegistroTipo02.append(Util
							.adicionarZerosEsquedaNumero(9, imovel.getId()
									.toString()));

					// CODIGO_CATEGORIA
					arquivoTextoRegistroTipo02.append(categoria.getId()
							.toString());

					// DESCRICAO_CATEGORIA
					arquivoTextoRegistroTipo02.append(Util.completaString(
							categoria.getDescricao(), 15));

					// CODIGO_SUBCATEGORIA
					arquivoTextoRegistroTipo02.append(Util
							.adicionarZerosEsquedaNumero(3, "0"));

					// DESCRICAO_SUBCATEGORIA
					arquivoTextoRegistroTipo02.append(Util.completaString("",
							50));

					// QUANTIDADE_ECONOMIAS_SUBCATEGORIA
					arquivoTextoRegistroTipo02
							.append(Util.adicionarZerosEsquedaNumero(
									4,
									""
											+ categoria
													.getQuantidadeEconomiasCategoria()));

					// DESCRICAO_ABREVIADA_CATEGORIA
					arquivoTextoRegistroTipo02.append(Util.completaString(
							categoria.getDescricaoAbreviada(), 3));

					// DESCRICAO_ABREVIADA_SUBCATEGORIA
					arquivoTextoRegistroTipo02.append(Util.completaString("",
							20));

					// FATOR ECONOMIAS
					String fatorEconomias = "";
					if (categoria.getFatorEconomias() != null) {
						fatorEconomias = categoria.getFatorEconomias()
								.toString();
					}
					arquivoTextoRegistroTipo02.append(Util.completaString(
							fatorEconomias, 2));

					arquivoTextoRegistroTipo02.append(System
							.getProperty("line.separator"));
				}
			}
		} else {

			Collection colecaoSubcategoria = null;

			if (conta != null) {

				// PESQUISANDO A PARTIR DA TABELA CONTA_CATEGORIA
				colecaoSubcategoria = this.getControladorImovel()
						.obterQuantidadeEconomiasContaCategoriaPorSubcategoria(
								conta.getId());
			} else {

				// PESQUISANDO A PARTIR DA TABELA IMOVEL_SUBCATEGORIA
				colecaoSubcategoria = this.getControladorImovel()
						.obterQuantidadeEconomiasSubCategoria(imovel.getId());
			}

			if (colecaoSubcategoria != null && !colecaoSubcategoria.isEmpty()) {

				Iterator iterator = colecaoSubcategoria.iterator();

				while (iterator.hasNext()) {

					Subcategoria subcategoria = (Subcategoria) iterator.next();

					quantidadeLinhas = quantidadeLinhas + 1;

					// TIPO DO REGISTRO
					arquivoTextoRegistroTipo02.append("02");

					// MATR�CULA DO IM�VEL
					arquivoTextoRegistroTipo02.append(Util
							.adicionarZerosEsquedaNumero(9, imovel.getId()
									.toString()));

					// CODIGO_CATEGORIA
					arquivoTextoRegistroTipo02.append(subcategoria
							.getCategoria().getId().toString());

					// DESCRICAO_CATEGORIA
					arquivoTextoRegistroTipo02.append(Util.completaString(
							subcategoria.getCategoria().getDescricao(), 15));

					// CODIGO_SUBCATEGORIA
					arquivoTextoRegistroTipo02.append(Util
							.adicionarZerosEsquedaNumero(3, subcategoria
									.getId().toString()));

					// DESCRICAO_SUBCATEGORIA
					arquivoTextoRegistroTipo02.append(Util.completaString(
							subcategoria.getDescricao(), 50));

					// QUANTIDADE_ECONOMIAS_SUBCATEGORIA
					arquivoTextoRegistroTipo02
							.append(Util.adicionarZerosEsquedaNumero(4, ""
									+ subcategoria.getQuantidadeEconomias()));

					// DESCRICAO_ABREVIADA_CATEGORIA
					arquivoTextoRegistroTipo02.append(Util
							.completaString(subcategoria.getCategoria()
									.getDescricaoAbreviada(), 3));

					// DESCRICAO_ABREVIADA_SUBCATEGORIA
					arquivoTextoRegistroTipo02.append(Util.completaString(
							subcategoria.getDescricaoAbreviada(), 20));

					// FATOR ECONOMIAS
					String fatorEconomias = "";
					if (subcategoria.getCategoria().getFatorEconomias() != null) {
						fatorEconomias = subcategoria.getCategoria()
								.getFatorEconomias().toString();
					}
					arquivoTextoRegistroTipo02.append(Util.completaString(
							fatorEconomias, 2));

					arquivoTextoRegistroTipo02.append(System
							.getProperty("line.separator"));
				}
			}
		}

		retorno[0] = arquivoTextoRegistroTipo02;
		retorno[1] = quantidadeLinhas;

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0001] - Gerar Arquivo Texto - Registro Tipo 03
	 * 
	 * @author Raphael Rossiter
	 * @date 24/04/2008
	 * 
	 * @param imovel
	 * @param anoMesReferencia
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	public Object[] gerarArquivoTextoRegistroTipo03(Imovel imovel,
			Integer anoMesReferencia) throws ControladorException {

		StringBuilder arquivoTextoRegistroTipo03 = new StringBuilder();

		int quantidadeLinhas = 0;

		Object[] retorno = new Object[2];

		// Para cada im�vel obter os 6 �ltimos consumos e medi��es anteriores.
		// Integer anoMesReferenciaFim =
		// Util.subtrairMesDoAnoMes(anoMesReferencia, 1);
		// Integer anoMesReferenciaInicio =
		// Util.subtrairMesDoAnoMes(anoMesReferencia, 6);

		Collection colecaoConsumoHistorico = this.getControladorMicromedicao()
				.obterUltimosConsumosImovel(imovel);

		if (colecaoConsumoHistorico != null
				&& !colecaoConsumoHistorico.isEmpty()) {

			Iterator iterator = colecaoConsumoHistorico.iterator();

			while (iterator.hasNext()) {

				ConsumoHistorico consumoHistorico = (ConsumoHistorico) iterator
						.next();

				quantidadeLinhas = quantidadeLinhas + 1;

				// TIPO DO REGISTRO
				arquivoTextoRegistroTipo03.append("03");

				// MATR�CULA DO IM�VEL
				arquivoTextoRegistroTipo03.append(Util
						.adicionarZerosEsquedaNumero(9, imovel.getId()
								.toString()));

				// LICAGAO_TIPO
				arquivoTextoRegistroTipo03.append(String
						.valueOf(consumoHistorico.getLigacaoTipo().getId()
								.toString()));

				// ANO_MES_FATURAMENTO
				arquivoTextoRegistroTipo03.append(String
						.valueOf(consumoHistorico.getReferenciaFaturamento()));

				// CONSUMO_FATURADO_MES
				if (consumoHistorico.getNumeroConsumoFaturadoMes() != null) {
					arquivoTextoRegistroTipo03.append(Util
							.adicionarZerosEsquedaNumero(6, consumoHistorico
									.getNumeroConsumoFaturadoMes().toString()));
				} else {
					arquivoTextoRegistroTipo03.append(Util
							.adicionarZerosEsquedaNumero(6, "0"));
				}

				// ANORMALIDADE_LEITURA
				Integer idLeituraAnormalidadeFaturamento = this
						.getControladorMicromedicao()
						.obterLeituraAnormalidadeFaturamentoMedicaoHistorico(
								consumoHistorico);

				if (idLeituraAnormalidadeFaturamento != null) {
					arquivoTextoRegistroTipo03
							.append(Util
									.adicionarZerosEsquedaNumero(2,
											idLeituraAnormalidadeFaturamento
													.toString()));
				} else {
					arquivoTextoRegistroTipo03.append(Util
							.adicionarZerosEsquedaNumero(2, "0"));
				}

				// ANORMALIDADE_CONSUMO
				if (consumoHistorico.getConsumoAnormalidade() != null) {
					arquivoTextoRegistroTipo03.append(Util
							.adicionarZerosEsquedaNumero(2, consumoHistorico
									.getConsumoAnormalidade().getId()
									.toString()));
				} else {
					arquivoTextoRegistroTipo03.append(Util
							.adicionarZerosEsquedaNumero(2, "0"));
				}

				arquivoTextoRegistroTipo03.append(System
						.getProperty("line.separator"));
			}
		}

		retorno[0] = arquivoTextoRegistroTipo03;
		retorno[1] = quantidadeLinhas;

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0001] - Gerar Arquivo Texto - Registro Tipo 04 [SB0002] - Obter dados
	 * dos servi�os e parcelamento
	 * 
	 * @author Raphael Rossiter
	 * @date 28/04/2008
	 * 
	 * @param conta
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	public Object[] gerarArquivoTextoRegistroTipo04(Conta conta)
			throws ControladorException {

		StringBuilder arquivoTextoRegistroTipo04 = new StringBuilder();

		int quantidadeLinhas = 0;

		Object[] retorno = new Object[2];

		if (conta != null) {

			// [SB0002] - Obter dados dos servi�os e parcelamento - PARTE I
			// -----------------------------------------------------------------------------------------
			// Object[] debitoCobradoDeParcelamento = null;
			// try {
			// CRC4428 - Vivianne Sousa - 14/06/2010

			/**
			 * Data: 13/05/2011
			 * 
			 * Altera��o para que no caso do im�vel ter um ou mais parcelamento
			 * ativos, todos sejam passados para o arquivo de rota
			 * */
			Collection colecaoDebitoCobradoDeParcelamento = this
					.getControladorFaturamento()
					.pesquisarDebitoCobradoDeParcelamentoIS(conta);

			// } catch (ErroRepositorioException e) {
			// sessionContext.setRollbackOnly();
			// throw new ControladorException("erro.sistema", e);
			// }
			if (colecaoDebitoCobradoDeParcelamento != null
					&& !colecaoDebitoCobradoDeParcelamento.isEmpty()) {

				Iterator iterator = colecaoDebitoCobradoDeParcelamento
						.iterator();

				while (iterator.hasNext()) {

					Object[] arrayDebitoCobrado = (Object[]) iterator.next();

					quantidadeLinhas = quantidadeLinhas + 1;

					// TIPO DO REGISTRO
					arquivoTextoRegistroTipo04.append("04");

					// MATR�CULA DO IM�VEL
					arquivoTextoRegistroTipo04.append(Util
							.adicionarZerosEsquedaNumero(9, conta.getImovel()
									.getId().toString()));

					// DESCRICAO_SERVICO_PARCELAMENTO
					/*
					 * Troca de 2 para 3 o tamanho da string, logo
					 * se o numero de parcelas for 12 e for a presta��o 4,
					 * ficar� 004/012. Com isso padroniza o tamanho da cadeia,
					 * atendendo os casos que o parcelamento possua 3 caracter
					 */
					arquivoTextoRegistroTipo04
							.append(Util
									.completaStringComEspacoADireitaCondicaoTamanhoMaximo(
											"PARCELAMENTO DE DEBITOS PARCELA "
													+ Util.adicionarZerosEsquedaNumero(
															3,
															String.valueOf(arrayDebitoCobrado[0]))
													+ "/"
													+ Util.adicionarZerosEsquedaNumero(
															3,
															String.valueOf(arrayDebitoCobrado[1])),
											90));

					// VALOR_SERVICO_PARCELAMENTO
					arquivoTextoRegistroTipo04
							.append(Util.adicionarZerosEsquedaNumero(
									14,
									Util.formatarBigDecimalComPonto((BigDecimal) arrayDebitoCobrado[2])));

					arquivoTextoRegistroTipo04
							.append(Util
									.completaString(
											(arrayDebitoCobrado[3] != null ? arrayDebitoCobrado[3]
													: "")
													+ "", 6));

					arquivoTextoRegistroTipo04.append(System
							.getProperty("line.separator"));
				}
			}

			// FIM - [SB0002] - Obter dados dos servi�os e parcelamento - PARTE
			// I
			// -----------------------------------------------------------------------------------------

			// [SB0002] - Obter dados dos servi�os e parcelamento - PARTE II
			// -----------------------------------------------------------------------------------------
			// Collection colecaoDebitoCobradoNaoParcelamento = null;
			// try {
			// CRC4428 - Vivianne Sousa - 14/06/2010
			Collection colecaoDebitoCobradoNaoParcelamento = this
					.getControladorFaturamento()
					.pesquisarDebitoCobradoNaoParcelamento(conta);

			// } catch (ErroRepositorioException e) {
			// sessionContext.setRollbackOnly();
			// throw new ControladorException("erro.sistema", e);
			// }

			if (colecaoDebitoCobradoNaoParcelamento != null
					&& !colecaoDebitoCobradoNaoParcelamento.isEmpty()) {

				Iterator iterator = colecaoDebitoCobradoNaoParcelamento
						.iterator();
				DebitoCobrado debitoCobradoAnterior = null;
				DebitoCobrado debitoCobradoAtual = null;
				BigDecimal valorPrestacaoAcumulado = BigDecimal.ZERO;
				String anoMesAcumulado = "";
				Integer qtdAnoMesDistintos = 0;

				while (iterator.hasNext()) {

					debitoCobradoAtual = (DebitoCobrado) iterator.next();

					if (debitoCobradoAnterior == null) {

						debitoCobradoAnterior = debitoCobradoAtual;

						if (debitoCobradoAnterior.getAnoMesReferenciaDebito() != null) {

							// ACUMULA O VALOR DA PRESTA��O PARA OS D�BITOS DE
							// MESMO TIPO
							valorPrestacaoAcumulado = debitoCobradoAnterior
									.getValorPrestacao();

							// ACUMULA A QUANTIDADE DE ANO/M�S DISTINTOS PARA O
							// MESMO TIPO DE D�BITO
							qtdAnoMesDistintos++;

							// CONCATENANDO OS ANO/M�S DOS DEBITOS DE MESMO TIPO
							anoMesAcumulado = Util
									.formatarAnoMesParaMesAno(debitoCobradoAnterior
											.getAnoMesReferenciaDebito());

						} else {
							quantidadeLinhas = quantidadeLinhas + 1;

							// GERANDO - [SB0002] - Obter dados dos servi�os e
							// parcelamento - PARTE II
							arquivoTextoRegistroTipo04.append(this
									.obterDadosServicosParcelamentosParteII(
											conta, debitoCobradoAnterior, 1,
											"", debitoCobradoAnterior
													.getValorPrestacao()));

						}

					} else if (debitoCobradoAnterior != null
							&& debitoCobradoAnterior
									.getDebitoTipo()
									.getId()
									.equals(debitoCobradoAtual.getDebitoTipo()
											.getId())) {

						debitoCobradoAnterior = debitoCobradoAtual;

						if (debitoCobradoAnterior.getAnoMesReferenciaDebito() != null) {

							// ACUMULA O VALOR DA PRESTA��O PARA OS D�BITOS DE
							// MESMO TIPO
							valorPrestacaoAcumulado = valorPrestacaoAcumulado
									.add(debitoCobradoAtual.getValorPrestacao());

							// ACUMULA A QUANTIDADE DE ANO/M�S DISTINTOS PARA O
							// MESMO TIPO DE D�BITO
							qtdAnoMesDistintos++;

							// CONCATENANDO OS ANO/M�S DOS DEBITOS DE MESMO TIPO
							anoMesAcumulado = anoMesAcumulado
									+ " "
									+ Util.formatarAnoMesParaMesAno(debitoCobradoAtual
											.getAnoMesReferenciaDebito());

						} else {
							quantidadeLinhas = quantidadeLinhas + 1;

							// GERANDO - [SB0002] - Obter dados dos servi�os e
							// parcelamento - PARTE II
							arquivoTextoRegistroTipo04.append(this
									.obterDadosServicosParcelamentosParteII(
											conta, debitoCobradoAnterior, 1,
											"", debitoCobradoAnterior
													.getValorPrestacao()));

						}

					} else {
						if (qtdAnoMesDistintos > 0) {
							quantidadeLinhas = quantidadeLinhas + 1;
							// GERANDO - [SB0002] - Obter dados dos servi�os e
							// parcelamento - PARTE II
							arquivoTextoRegistroTipo04.append(this
									.obterDadosServicosParcelamentosParteII(
											conta, debitoCobradoAnterior,
											qtdAnoMesDistintos,
											anoMesAcumulado,
											valorPrestacaoAcumulado));
						}

						debitoCobradoAnterior = debitoCobradoAtual;
						qtdAnoMesDistintos = 0;

						if (debitoCobradoAnterior.getAnoMesReferenciaDebito() != null) {

							// INICIALIZANDO O VALOR DA PRESTA��O PARA OS
							// D�BITOS DE MESMO TIPO
							valorPrestacaoAcumulado = debitoCobradoAnterior
									.getValorPrestacao();

							// ACUMULA A QUANTIDADE DE ANO/M�S DISTINTOS PARA O
							// MESMO TIPO DE D�BITO
							qtdAnoMesDistintos = 1;

							// CONCATENANDO OS ANO/M�S DOS DEBITOS DE MESMO TIPO
							anoMesAcumulado = Util
									.formatarAnoMesParaMesAno(debitoCobradoAnterior
											.getAnoMesReferenciaDebito());
						} else {
							quantidadeLinhas = quantidadeLinhas + 1;
							// GERANDO - [SB0002] - Obter dados dos servi�os e
							// parcelamento - PARTE II
							arquivoTextoRegistroTipo04.append(this
									.obterDadosServicosParcelamentosParteII(
											conta, debitoCobradoAnterior, 1,
											"", debitoCobradoAnterior
													.getValorPrestacao()));
						}
					}
				}

				if (qtdAnoMesDistintos > 0) {
					quantidadeLinhas = quantidadeLinhas + 1;
					// GERANDO - [SB0002] - Obter dados dos servi�os e
					// parcelamento - PARTE II
					arquivoTextoRegistroTipo04.append(this
							.obterDadosServicosParcelamentosParteII(conta,
									debitoCobradoAnterior, qtdAnoMesDistintos,
									anoMesAcumulado, valorPrestacaoAcumulado));
				}
			}

			// FIM - [SB0002] - Obter dados dos servi�os e parcelamento - PARTE
			// II
			// -----------------------------------------------------------------------------------------
		}

		retorno[0] = arquivoTextoRegistroTipo04;
		retorno[1] = quantidadeLinhas;

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0001] - Gerar Arquivo Texto - Registro Tipo 04 [SB0002] - Obter dados
	 * dos servi�os e parcelamento - PARTE II
	 * 
	 * @author Raphael Rossiter
	 * @date 28/04/2008
	 * 
	 * @param conta
	 * @param debitoCobrado
	 * @param qtdAnoMesDistintos
	 * @param anoMesAcumulado
	 * @param valorPrestacaoAcumulado
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	public StringBuilder obterDadosServicosParcelamentosParteII(Conta conta,
			DebitoCobrado debitoCobrado, Integer qtdAnoMesDistintos,
			String anoMesAcumulado, BigDecimal valorPrestacaoAcumulado)
			throws ControladorException {

		StringBuilder arquivoTextoRegistroTipo04 = new StringBuilder();

		// TIPO DO REGISTRO
		arquivoTextoRegistroTipo04.append("04");

		// MATR�CULA DO IM�VEL
		arquivoTextoRegistroTipo04.append(Util.adicionarZerosEsquedaNumero(9,
				conta.getImovel().getId().toString()));

		if (qtdAnoMesDistintos > 1) {

			/*
			 * Caso a lista dos meses de refer�ncia distintos do grupo de tipo
			 * de d�bitos esteja preenchida
			 */

			if (qtdAnoMesDistintos > 5) {

				// DESCRICAO_SERVICO_PARCELAMENTO
				arquivoTextoRegistroTipo04.append(Util.completaString(
						debitoCobrado.getDebitoTipo().getDescricao() + " "
								+ anoMesAcumulado + " E OUTRAS", 90));
			} else {

				// DESCRICAO_SERVICO_PARCELAMENTO
				arquivoTextoRegistroTipo04.append(Util.completaString(
						debitoCobrado.getDebitoTipo().getDescricao() + " "
								+ anoMesAcumulado, 90));
			}

			// VALOR_SERVICO_PARCELAMENTO
			arquivoTextoRegistroTipo04.append(Util.adicionarZerosEsquedaNumero(
					14,
					Util.formatarBigDecimalComPonto(valorPrestacaoAcumulado)));

			if (debitoCobrado.getDebitoTipo() != null
					&& debitoCobrado.getDebitoTipo().getCodigoConstante() != null) {
				arquivoTextoRegistroTipo04.append(Util
						.completaString(debitoCobrado.getDebitoTipo()
								.getCodigoConstante() + "", 6));
			} else {
				arquivoTextoRegistroTipo04.append(Util.completaString("", 6));
			}

			arquivoTextoRegistroTipo04.append(System
					.getProperty("line.separator"));
		} else {

			if (anoMesAcumulado == null || anoMesAcumulado.equals("")) {
				// Caso contr�rio formata o n�mero da parcela do d�bito.
				// DESCRICAO_SERVICO_PARCELAMENTO
				/**
				 * Data: 08/02/2011
				 * 
				 * Altera��o do numero de presta��es de 2 para 3 caracteres. E
				 * mudan�a da string de 90 para 60, para corre��o do
				 * deslocamento dos caracteres, que enviava o valor incorreto de
				 * parcelamento para o IS.
				 */
				arquivoTextoRegistroTipo04.append(Util.completaString(
						debitoCobrado.getDebitoTipo().getDescricao()
								+ " PARCELA "
								+ Util.adicionarZerosEsquedaNumero(3, String
										.valueOf(debitoCobrado
												.getNumeroPrestacaoDebito()))
								+ "/"
								+ Util.adicionarZerosEsquedaNumero(3, String
										.valueOf(debitoCobrado
												.getNumeroPrestacao())), 90));

			} else {
				// DESCRICAO_SERVICO_PARCELAMENTO
				arquivoTextoRegistroTipo04.append(Util.completaString(
						debitoCobrado.getDebitoTipo().getDescricao() + " "
								+ anoMesAcumulado, 90));
			}

			// VALOR_SERVICO_PARCELAMENTO
			arquivoTextoRegistroTipo04.append(Util.adicionarZerosEsquedaNumero(
					14, Util.formatarBigDecimalComPonto(debitoCobrado
							.getValorPrestacao())));

			if (debitoCobrado.getDebitoTipo() != null
					&& debitoCobrado.getDebitoTipo().getCodigoConstante() != null) {
				arquivoTextoRegistroTipo04.append(Util
						.completaString(debitoCobrado.getDebitoTipo()
								.getCodigoConstante() + "", 6));
			} else {
				arquivoTextoRegistroTipo04.append(Util.completaString("", 6));
			}

			arquivoTextoRegistroTipo04.append(System
					.getProperty("line.separator"));

		}

		return arquivoTextoRegistroTipo04;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0001] - Gerar Arquivo Texto - Registro Tipo 05 [SB0003] - Obter dados
	 * dos cr�ditos realizados
	 * 
	 * @author Raphael Rossiter
	 * @date 29/04/2008
	 * 
	 * @param conta
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	public Object[] gerarArquivoTextoRegistroTipo05(Conta conta)
			throws ControladorException {

		StringBuilder arquivoTextoRegistroTipo05 = new StringBuilder();

		int quantidadeLinhas = 0;

		Object[] retorno = new Object[2];

		if (conta != null) {

			// [SB0003] - Obter dados dos cr�ditos realizados
			Collection colecaoCreditoRealizado = null;
			try {

				colecaoCreditoRealizado = this.repositorioFaturamento
						.pesquisarCreditoRealizado(conta);

			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}

			if (colecaoCreditoRealizado != null
					&& !colecaoCreditoRealizado.isEmpty()) {

				Iterator iterator = colecaoCreditoRealizado.iterator();
				CreditoRealizado creditoRealizadoAnterior = null;
				CreditoRealizado creditoRealizadoAtual = null;
				BigDecimal valorCreditoAcumulado = BigDecimal.ZERO;
				String anoMesAcumulado = "";
				Integer qtdAnoMesDistintos = 0;

				while (iterator.hasNext()) {

					creditoRealizadoAtual = (CreditoRealizado) iterator.next();

					if (creditoRealizadoAnterior == null) {

						creditoRealizadoAnterior = creditoRealizadoAtual;

						if (creditoRealizadoAnterior
								.getAnoMesReferenciaCredito() != null) {

							// ACUMULA O VALOR DOS CR�DITOS DE MESMO TIPO
							valorCreditoAcumulado = creditoRealizadoAnterior
									.getValorCredito();

							// ACUMULA A QUANTIDADE DE ANO/M�S DISTINTOS PARA O
							// MESMO TIPO DE CR�DITO
							qtdAnoMesDistintos++;

							// CONCATENANDO OS ANO/M�S DOS CR�DITOS DE MESMO
							// TIPO
							anoMesAcumulado = Util
									.formatarAnoMesParaMesAno(creditoRealizadoAnterior
											.getAnoMesReferenciaCredito());
						} else {
							quantidadeLinhas = quantidadeLinhas + 1;
							// GERANDO - [SB0003] - Obter dados dos cr�ditos
							// realizados
							arquivoTextoRegistroTipo05.append(this
									.obterDadosCreditosRealizados(conta,
											creditoRealizadoAnterior, 1, "",
											creditoRealizadoAnterior
													.getValorCredito()));
						}

					} else if (creditoRealizadoAnterior != null
							&& creditoRealizadoAnterior
									.getCreditoTipo()
									.getId()
									.equals(creditoRealizadoAtual
											.getCreditoTipo().getId())) {

						creditoRealizadoAnterior = creditoRealizadoAtual;

						if (creditoRealizadoAnterior
								.getAnoMesReferenciaCredito() != null) {

							// ACUMULA O VALOR DOS CR�DITOS DE MESMO TIPO
							valorCreditoAcumulado = valorCreditoAcumulado
									.add(creditoRealizadoAtual
											.getValorCredito());

							// ACUMULA A QUANTIDADE DE ANO/M�S DISTINTOS PARA O
							// MESMO TIPO DE CR�DITO
							qtdAnoMesDistintos++;

							// CONCATENANDO OS ANO/M�S DOS CR�DITOS DE MESMO
							// TIPO
							anoMesAcumulado = anoMesAcumulado
									+ " "
									+ Util.formatarAnoMesParaMesAno(creditoRealizadoAtual
											.getAnoMesReferenciaCredito());
						} else {
							quantidadeLinhas = quantidadeLinhas + 1;
							// GERANDO - [SB0003] - Obter dados dos cr�ditos
							// realizados
							arquivoTextoRegistroTipo05.append(this
									.obterDadosCreditosRealizados(conta,
											creditoRealizadoAnterior, 1, "",
											creditoRealizadoAnterior
													.getValorCredito()));
						}

					} else {
						if (qtdAnoMesDistintos > 0) {
							quantidadeLinhas = quantidadeLinhas + 1;
							// GERANDO - [SB0003] - Obter dados dos cr�ditos
							// realizados
							arquivoTextoRegistroTipo05.append(this
									.obterDadosCreditosRealizados(conta,
											creditoRealizadoAnterior,
											qtdAnoMesDistintos,
											anoMesAcumulado,
											valorCreditoAcumulado));
						}

						creditoRealizadoAnterior = creditoRealizadoAtual;
						qtdAnoMesDistintos = 0;

						if (creditoRealizadoAnterior
								.getAnoMesReferenciaCredito() != null) {

							// INICIALIZANDO O VALOR DA PRESTA��O PARA OS
							// D�BITOS DE MESMO TIPO
							valorCreditoAcumulado = creditoRealizadoAnterior
									.getValorCredito();

							// INICIALIZANDO A QUANTIDADE DE ANO/M�S DISTINTOS
							// PARA O MESMO TIPO DE CR�DITO
							qtdAnoMesDistintos = 1;

							// CONCATENANDO OS ANO/M�S DOS CR�DITOS DE MESMO
							// TIPO
							anoMesAcumulado = Util
									.formatarAnoMesParaMesAno(creditoRealizadoAnterior
											.getAnoMesReferenciaCredito());
						} else {
							quantidadeLinhas = quantidadeLinhas + 1;
							// GERANDO - [SB0003] - Obter dados dos cr�ditos
							// realizados
							arquivoTextoRegistroTipo05.append(this
									.obterDadosCreditosRealizados(conta,
											creditoRealizadoAnterior, 1, "",
											creditoRealizadoAnterior
													.getValorCredito()));
						}

					}
				}

				if (qtdAnoMesDistintos > 0) {
					quantidadeLinhas = quantidadeLinhas + 1;
					// GERANDO - [SB0003] - Obter dados dos cr�ditos realizados
					arquivoTextoRegistroTipo05.append(this
							.obterDadosCreditosRealizados(conta,
									creditoRealizadoAnterior,
									qtdAnoMesDistintos, anoMesAcumulado,
									valorCreditoAcumulado));
				}
			}
		}

		retorno[0] = arquivoTextoRegistroTipo05;
		retorno[1] = quantidadeLinhas;

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0001] - Gerar Arquivo Texto - Registro Tipo 05 [SB0003] - Obter dados
	 * dos cr�ditos realizados
	 * 
	 * @author Raphael Rossiter
	 * @date 29/04/2008
	 * 
	 * @param conta
	 * @param creditoRealizado
	 * @param qtdAnoMesDistintos
	 * @param anoMesAcumulado
	 * @param valorCreditoAcumulado
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	public StringBuilder obterDadosCreditosRealizados(Conta conta,
			CreditoRealizado creditoRealizado, Integer qtdAnoMesDistintos,
			String anoMesAcumulado, BigDecimal valorCreditoAcumulado)
			throws ControladorException {

		StringBuilder arquivoTextoRegistroTipo05 = new StringBuilder();

		// TIPO DO REGISTRO
		arquivoTextoRegistroTipo05.append("05");

		// MATR�CULA DO IM�VEL
		arquivoTextoRegistroTipo05.append(Util.adicionarZerosEsquedaNumero(9,
				conta.getImovel().getId().toString()));

		if (qtdAnoMesDistintos > 1) {

			/*
			 * Caso a lista dos meses de refer�ncia distintos do grupo de tipo
			 * de cr�ditos esteja preenchida
			 */

			if (qtdAnoMesDistintos > 5) {

				// DESCRICAO_CREDITO
				arquivoTextoRegistroTipo05.append(Util.completaString(
						creditoRealizado.getCreditoTipo().getDescricao() + " "
								+ anoMesAcumulado + " E OUTRAS", 90));
			} else {

				// DESCRICAO_CREDITO
				arquivoTextoRegistroTipo05.append(Util.completaString(
						creditoRealizado.getCreditoTipo().getDescricao() + " "
								+ anoMesAcumulado, 90));
			}

			// VALOR_CREDITO
			arquivoTextoRegistroTipo05
					.append(Util.adicionarZerosEsquedaNumero(14, Util
							.formatarBigDecimalComPonto(valorCreditoAcumulado)));

			if (creditoRealizado.getCreditoTipo() != null
					&& creditoRealizado.getCreditoTipo().getCodigoConstante() != null) {
				arquivoTextoRegistroTipo05.append(Util.completaString(
						creditoRealizado.getCreditoTipo().getCodigoConstante()
								+ "", 6));
			}
			/*
			 * Adi��o de uma condi��o para que o id do cr�dito
			 * seja repassado para o arquivo txt
			 */
			else if (creditoRealizado.getCreditoTipo() != null) {
				arquivoTextoRegistroTipo05.append(Util.completaString(
						creditoRealizado.getCreditoTipo().getId() + "", 6));
			} else {
				arquivoTextoRegistroTipo05.append(Util.completaString("", 6));
			}

			arquivoTextoRegistroTipo05.append(System
					.getProperty("line.separator"));
		} else {

			// Caso contr�rio formata o n�mero da parcela do cr�dito.

			if (anoMesAcumulado == null || anoMesAcumulado.equals("")) {
				// DESCRICAO_CREDITO
				arquivoTextoRegistroTipo05.append(Util.completaString(
						creditoRealizado.getCreditoTipo().getDescricao()
								+ " PARCELA "
								+ Util.adicionarZerosEsquedaNumero(2, String
										.valueOf(creditoRealizado
												.getNumeroPrestacaoCredito()))
								+ "/"
								+ Util.adicionarZerosEsquedaNumero(2, String
										.valueOf(creditoRealizado
												.getNumeroPrestacao())), 90));

			} else {
				// DESCRICAO_SERVICO_PARCELAMENTO
				arquivoTextoRegistroTipo05.append(Util.completaString(
						creditoRealizado.getCreditoTipo().getDescricao() + " "
								+ anoMesAcumulado, 90));
			}

			// VALOR_CREDITO
			arquivoTextoRegistroTipo05.append(Util.adicionarZerosEsquedaNumero(
					14, Util.formatarBigDecimalComPonto(creditoRealizado
							.getValorCredito())));

			if (creditoRealizado.getCreditoTipo() != null
					&& creditoRealizado.getCreditoTipo().getCodigoConstante() != null) {
				arquivoTextoRegistroTipo05.append(Util.completaString(
						creditoRealizado.getCreditoTipo().getCodigoConstante()
								+ "", 6));
			}
			/*
			 * Adi��o de uma condi��o para que o id do cr�dito
			 * seja repassado para o arquivo txt
			 */
			else if (creditoRealizado.getCreditoTipo() != null) {
				arquivoTextoRegistroTipo05.append(Util.completaString(
						creditoRealizado.getCreditoTipo().getId() + "", 6));
			} else {
				arquivoTextoRegistroTipo05.append(Util.completaString("", 6));
			}

			arquivoTextoRegistroTipo05.append(System
					.getProperty("line.separator"));

		}

		return arquivoTextoRegistroTipo05;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0001] - Gerar Arquivo Texto - Registro Tipo 06 [SB0004] - Gerar linhas
	 * dos impostos retidos
	 * 
	 * @author Raphael Rossiter
	 * @date 29/04/2008
	 * 
	 * @param conta
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	public Object[] gerarArquivoTextoRegistroTipo06(Conta conta)
			throws ControladorException {

		StringBuilder arquivoTextoRegistroTipo06 = new StringBuilder();

		int quantidadeLinhas = 0;

		Object[] retorno = new Object[2];

		if (conta != null) {

			// [SB0004] - Gerar linhas dos impostos retidos
			Collection colecaoImpostosRetidos = null;
			try {

				colecaoImpostosRetidos = this.repositorioFaturamento
						.pesquisarParmsContaImpostosDeduzidos(conta.getId());

			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}

			if (colecaoImpostosRetidos != null
					&& !colecaoImpostosRetidos.isEmpty()) {

				Iterator iterator = colecaoImpostosRetidos.iterator();

				while (iterator.hasNext()) {

					Object[] arrayImpostos = (Object[]) iterator.next();

					quantidadeLinhas = quantidadeLinhas + 1;

					// TIPO DO REGISTRO
					arquivoTextoRegistroTipo06.append("06");

					// MATR�CULA DO IM�VEL
					arquivoTextoRegistroTipo06.append(Util
							.adicionarZerosEsquedaNumero(9, conta.getImovel()
									.getId().toString()));

					// ID_IMPOSTO_TIPO
					arquivoTextoRegistroTipo06.append(String
							.valueOf(arrayImpostos[3]));

					// DESCRICAO_IMPOSTO
					arquivoTextoRegistroTipo06.append(Util.completaString(
							String.valueOf(arrayImpostos[0]), 15));

					// PERCENTUAL_ALIQUOTA
					arquivoTextoRegistroTipo06
							.append(Util.adicionarZerosEsquedaNumero(
									6,
									Util.formatarBigDecimalComPonto((BigDecimal) arrayImpostos[1])));

					arquivoTextoRegistroTipo06.append(System
							.getProperty("line.separator"));
				}
			}
		}

		retorno[0] = arquivoTextoRegistroTipo06;
		retorno[1] = quantidadeLinhas;

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0001] - Gerar Arquivo Texto - Registro Tipo 07 [SB0005] - Obter dados
	 * da conta em aberto
	 * 
	 * @author Raphael Rossiter
	 * @date 29/04/2008
	 * 
	 * @param conta
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	public Object[] gerarArquivoTextoRegistroTipo07(Imovel imovel,
			SistemaParametro sistemaParametro,
			CobrancaDocumento cobrancaDocumento) throws ControladorException {

		StringBuilder arquivoTextoRegistroTipo07 = new StringBuilder();

		int quantidadeLinhas = 0;

		Object[] retorno = new Object[2];
		if (cobrancaDocumento != null && !cobrancaDocumento.equals("")) {
			retorno = formatarCobrancaDocumentoItemParaImpressaoSimultanea(
					imovel.getId(), cobrancaDocumento, 17);
		} else {
			retorno[0] = arquivoTextoRegistroTipo07;
			retorno[1] = quantidadeLinhas;
		}

		// if (conta != null){
		//
		// //[SB0005] - Obter dados da conta em aberto
		// String referenciaInicial = "190001";
		//
		// //PARM_AMREFERENCIAFATURAMENTO da tabela SISTEMA_PARAMETROS menos 1
		// m�s.
		// String referenciaFinal =
		// String.valueOf(Util.subtrairMesDoAnoMes(sistemaParametro
		// .getAnoMesFaturamento(), 1));
		//
		// Date dataVencimentoInicial = Util.criarData(1, 1, 1900);
		//
		// /*
		// * Data referente ao �ltimo dia do ano/m�s de refer�ncia da
		// arrecada��o (PARM_AMREFERENCIAARRECADACAO
		// * da tabela SISTEMA_PARAMETROS) menos 1 m�s.
		// */
		// Integer anoMesArrecadacaoMenos1Mes =
		// Util.subtrairMesDoAnoMes(sistemaParametro
		// .getAnoMesArrecadacao(), 1);
		//
		// Integer ano = Util.obterAno(anoMesArrecadacaoMenos1Mes);
		// Integer mes = Util.obterMes(anoMesArrecadacaoMenos1Mes);
		//
		// Date dataVencimentoFinal =
		// Util.criarData(Integer.parseInt(Util.obterUltimoDiaMes(mes, ano)),
		// mes, ano);
		//
		// //[UC0067] Obter D�bito do Im�vel ou Cliente
		// ObterDebitoImovelOuClienteHelper imovelDebitos =
		// this.getControladorCobranca()
		// .obterDebitoImovelOuCliente(1, conta.getImovel().getId().toString(),
		// null, null, referenciaInicial,
		// referenciaFinal, dataVencimentoInicial, dataVencimentoFinal, 1, 2, 2,
		// 2, 2, 1, 1, true);
		//
		// if (imovelDebitos.getColecaoContasValores() != null &&
		// !imovelDebitos.getColecaoContasValores().isEmpty()){
		//
		// Iterator iterator =
		// imovelDebitos.getColecaoContasValores().iterator();
		//
		// while(iterator.hasNext()){
		//
		// ContaValoresHelper contaValoresHelper = (ContaValoresHelper)
		// iterator.next();
		//
		// quantidadeLinhas = quantidadeLinhas + 1;
		// //TIPO DO REGISTRO
		// arquivoTextoRegistroTipo07.append("07");
		//
		// //MATR�CULA DO IM�VEL
		// arquivoTextoRegistroTipo07.append(Util.adicionarZerosEsquedaNumero(9,
		// conta
		// .getImovel().getId().toString()));
		//
		// //ANO/MES DE REFERENCIA DA CONTA
		// arquivoTextoRegistroTipo07.append(String.valueOf(contaValoresHelper.getConta().getReferencia()));
		//
		// //VALOR DA CONTA
		// arquivoTextoRegistroTipo07.append(Util.adicionarZerosEsquedaNumero(
		// 14,
		// Util.formatarBigDecimalComPonto(contaValoresHelper.getConta().getValorTotalContaBigDecimal())));
		//
		// //DATA DE VENCIMENTO
		// arquivoTextoRegistroTipo07.append(Util.formatarDataAAAAMMDD(contaValoresHelper.getConta()
		// .getDataVencimentoConta()));
		//
		// //VALOR ACR�SCIMOS IMPONTUALIDADE
		// arquivoTextoRegistroTipo07.append(Util.adicionarZerosEsquedaNumero(
		// 14,
		// Util.formatarBigDecimalComPonto(contaValoresHelper.getValorTotalContaValores())));
		//
		//
		// arquivoTextoRegistroTipo07.append(System.getProperty("line.separator"));
		// }
		// }
		// }

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0001] - Gerar Arquivo Texto - Registro Tipo 08 [SB0006] - Obter dados
	 * dos tipos de medi��o
	 * 
	 * @author Raphael Rossiter
	 * @date 02/05/2008
	 * 
	 * @param imovel
	 * @param anoMesReferencia
	 * @param sistemaParametro
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	public Object[] gerarArquivoTextoRegistroTipo08(Imovel imovel,
			Integer anoMesReferencia, SistemaParametro sistemaParametro)
			throws ControladorException {

		StringBuilder arquivoTextoRegistroTipo08 = new StringBuilder();
		int quantidadeLinhas = 0;

		Object[] retorno = new Object[2];

		Date dataLigacao = null;

		Date dataInstalacaoHidrometro = null;

		// [SB0006] - Obter dados dos tipos de medi��o
		Integer anoMesReferenciaAnterior = Util.subtrairMesDoAnoMes(
				anoMesReferencia, 1);

		Collection colecaoDadosMedicaoHistorico = this
				.getControladorMicromedicao().obterDadosTiposMedicao(imovel,
						anoMesReferenciaAnterior);

		if (colecaoDadosMedicaoHistorico != null
				&& !colecaoDadosMedicaoHistorico.isEmpty()) {

			Iterator iterator = colecaoDadosMedicaoHistorico.iterator();

			while (iterator.hasNext()) {

				Object[] arrayMedicaoHistorico = (Object[]) iterator.next();
				MedicaoHistorico medicaoHistorico = new MedicaoHistorico();
				Hidrometro hidrometro = null;

				quantidadeLinhas = quantidadeLinhas + 1;
				// TIPO DO REGISTRO
				arquivoTextoRegistroTipo08.append("08");

				// MATR�CULA DO IM�VEL
				arquivoTextoRegistroTipo08.append(Util
						.adicionarZerosEsquedaNumero(9, imovel.getId()
								.toString()));

				// TIPO DE MEDI��O
				MedicaoTipo medicaoTipo = new MedicaoTipo();

				// Leitura Inicial do Hidrometro
				Integer leituraInicialHidrometro = 0;
				if (!arrayMedicaoHistorico[3].equals(null)) {
					leituraInicialHidrometro = (Integer) arrayMedicaoHistorico[3];
				}

				if (arrayMedicaoHistorico[7] != null
						&& !((Integer) arrayMedicaoHistorico[7]).equals(0)) {

					medicaoTipo.setId(MedicaoTipo.LIGACAO_AGUA);
					arquivoTextoRegistroTipo08.append(MedicaoTipo.LIGACAO_AGUA
							.toString());

				} else {

					medicaoTipo.setId(MedicaoTipo.POCO);
					arquivoTextoRegistroTipo08.append(MedicaoTipo.POCO
							.toString());

				}

				// N�MERO DO HIDR�METRO
				if (arrayMedicaoHistorico[0] != null) {

					hidrometro = new Hidrometro();
					hidrometro.setNumero(String
							.valueOf(arrayMedicaoHistorico[0]));

					arquivoTextoRegistroTipo08.append(Util.completaString(
							hidrometro.getNumero(), 11));
				} else {
					arquivoTextoRegistroTipo08.append(Util.completaString("",
							11));
				}

				// DATA INSTALA��O HIDR�METRO
				if (arrayMedicaoHistorico[2] != null) {
					dataInstalacaoHidrometro = (Date) arrayMedicaoHistorico[2];
					arquivoTextoRegistroTipo08.append(Util
							.formatarDataAAAAMMDD(dataInstalacaoHidrometro));
				} else {
					arquivoTextoRegistroTipo08.append(Util
							.completaString("", 8));
				}

				// N�MERO DE D�GITOS DE LEITURA
				if (arrayMedicaoHistorico[1] != null) {

					hidrometro
							.setNumeroDigitosLeitura((Short) arrayMedicaoHistorico[1]);
					arquivoTextoRegistroTipo08.append(hidrometro
							.getNumeroDigitosLeitura().toString());
				} else {
					arquivoTextoRegistroTipo08.append(Util
							.completaString("", 1));
				}

				// LEITURA ANTERIOR FATURADA
				Integer leituraAnteriorFaturada = (Integer) arrayMedicaoHistorico[3];
				String filtroPoTipoMedicao = "";

				// LEITURA ANTERIOR INFORMADA
				Integer leituraAnteriorInformada = null;
				if (arrayMedicaoHistorico[12] != null) {
					leituraAnteriorInformada = (Integer) arrayMedicaoHistorico[12];

				}

				/**
				 *  Adicionando campos da data de ligacao
				 */
				if (medicaoTipo.getId().intValue() == MedicaoTipo.LIGACAO_AGUA
						.intValue()) {
					dataLigacao = imovel.getLigacaoAgua().getDataLigacao();
					filtroPoTipoMedicao = FiltroMedicaoHistorico.LIGACAO_AGUA_ID;
				} else {
					dataLigacao = imovel.getLigacaoEsgoto().getDataLigacao();
					filtroPoTipoMedicao = FiltroMedicaoHistorico.IMOVEL_ID;
				}

				Date dataLeituraAnteriorFaturamento = null;
				Date dataLeituraAnteriorInformada = null;

				// Verifica se existe medi��o historico para o m�s ano atual.
				FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();
				filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
						filtroPoTipoMedicao, imovel.getId()));
				filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
						FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO,
						anoMesReferencia));
				Collection<MedicaoHistorico> colMedicaoHistoricoMenos1Mes = getControladorUtil()
						.pesquisar(filtroMedicaoHistorico,
								MedicaoHistorico.class.getName());
				MedicaoHistorico medicaoHistoricoAtual = (MedicaoHistorico) Util
						.retonarObjetoDeColecao(colMedicaoHistoricoMenos1Mes);

				if (medicaoHistoricoAtual != null
						&& !medicaoHistoricoAtual.equals("")) {
					leituraAnteriorFaturada = medicaoHistoricoAtual
							.getLeituraAnteriorFaturamento();
					leituraAnteriorInformada = medicaoHistoricoAtual
							.getLeituraAnteriorInformada();
					dataLeituraAnteriorFaturamento = medicaoHistoricoAtual
							.getDataLeituraAnteriorFaturamento();
					dataLeituraAnteriorInformada = medicaoHistoricoAtual
							.getDataLeituraAtualInformada();
				}

				medicaoHistorico
						.setLeituraAnteriorFaturamento(leituraAnteriorFaturada);

				if (leituraAnteriorInformada != null) {
					medicaoHistorico
							.setLeituraAnteriorInformada(leituraAnteriorInformada);
				}

				arquivoTextoRegistroTipo08.append(Util
						.adicionarZerosEsquedaNumero(7,
								leituraAnteriorFaturada.toString()));

				// DATA DA LEITURA ANTERIOR FATURADA
				if (dataLeituraAnteriorFaturamento != null
						&& !dataLeituraAnteriorFaturamento.equals("")) {
					arquivoTextoRegistroTipo08
							.append(Util
									.formatarDataAAAAMMDD(dataLeituraAnteriorFaturamento));
				} else {
					if (arrayMedicaoHistorico[4] != null) {
						arquivoTextoRegistroTipo08
								.append(Util
										.formatarDataAAAAMMDD((Date) arrayMedicaoHistorico[4]));
					} else {
						arquivoTextoRegistroTipo08.append(Util.completaString(
								"", 8));
					}
				}

				// LEITURA SITUACAO ANTERIOR
				if (arrayMedicaoHistorico[5] != null) {

					LeituraSituacao leituraSituacao = new LeituraSituacao();
					leituraSituacao.setId((Integer) arrayMedicaoHistorico[5]);

					arquivoTextoRegistroTipo08.append(leituraSituacao.getId()
							.toString());

					medicaoHistorico.setLeituraSituacaoAtual(leituraSituacao);
				} else {
					arquivoTextoRegistroTipo08.append(Util
							.completaString("", 1));
				}

				// CONSUMO M�DIO HIDR�METRO

				// [UC0068] - Obter Consumo M�dio do Hidr�metro
				/*
				 * int[] consumoMedioHidrometro =
				 * this.getControladorMicromedicao()
				 * .obterConsumoMedioImovel(imovel, sistemaParametro);
				 */
				sistemaParametro.setAnoMesFaturamento(anoMesReferencia);

				/**
				 *  Alterando o c�lculo da m�dia
				 */
				boolean houveIntslacaoHidrometro = this
						.getControladorMicromedicao()
						.verificarInstalacaoSubstituicaoHidrometro(
								imovel.getId(), medicaoTipo);

				int[] consumoMedioHidrometro = this
						.getControladorMicromedicao()
						.obterVolumeMedioAguaEsgoto(imovel.getId(),
								anoMesReferencia, medicaoTipo.getId(), houveIntslacaoHidrometro);

				// [SB0007] - Obter dados do limite de leitura esperada
				Integer[] faixaLeitura = this.obterDadosLimiteLeituraEsperada(
						imovel, hidrometro, consumoMedioHidrometro[0],
						medicaoHistorico, sistemaParametro);

				// LIMITE INFERIOR FAIXA LEITURA ESPERADA
				arquivoTextoRegistroTipo08.append(Util
						.adicionarZerosEsquedaNumero(7,
								faixaLeitura[0].toString()));

				// LIMITE SUPERIOR FAIXA LEITURA ESPERADA
				arquivoTextoRegistroTipo08.append(Util
						.adicionarZerosEsquedaNumero(7,
								faixaLeitura[1].toString()));

				// CONSUMO M�DIO HIDR�METRO
				arquivoTextoRegistroTipo08.append(Util
						.adicionarZerosEsquedaNumero(6,
								String.valueOf(consumoMedioHidrometro[0])));

				// DESCRI��O LOCAL INSTALA��O DO HIDROMETRO
				if (arrayMedicaoHistorico[9] != null) {
					arquivoTextoRegistroTipo08.append(Util.completaString(""
							+ arrayMedicaoHistorico[9], 20));
				} else {
					arquivoTextoRegistroTipo08.append(Util.completaString("",
							20));
				}

				// LEITURA ANTERIOR INFORMADA
				if (leituraAnteriorInformada != null) {
					arquivoTextoRegistroTipo08.append(Util
							.adicionarZerosEsquedaNumero(7,
									leituraAnteriorInformada.toString()));
				} else {
					arquivoTextoRegistroTipo08.append(Util
							.completaString("", 7));
				}

				// DATA DA LEITURA ANTERIOR INFORMADA
				/**
				 *  Altera��o para enviar na rota a data da
				 * leitura anterior informada
				 * */
				if (dataLeituraAnteriorInformada != null
						&& !dataLeituraAnteriorInformada.equals("")) {
					arquivoTextoRegistroTipo08
							.append(Util
									.formatarDataAAAAMMDD(dataLeituraAnteriorInformada));
				} else {
					if (arrayMedicaoHistorico[10] != null) {
						arquivoTextoRegistroTipo08
								.append(Util
										.formatarDataAAAAMMDD((Date) arrayMedicaoHistorico[10]));
					} else {
						arquivoTextoRegistroTipo08.append(Util.completaString(
								"", 8));
					}
				}

				/**
				 * Adicionando a data da ligacao de agua do imovel
				 */
				arquivoTextoRegistroTipo08.append(Util.formatarDataAAAAMMDD(dataLigacao));

				// TIPO DE RATEIO
				if (arrayMedicaoHistorico[11] != null) {
					arquivoTextoRegistroTipo08
							.append(((Integer) arrayMedicaoHistorico[11])
									.toString());
				} else {
					arquivoTextoRegistroTipo08.append(" ");
				}

				// LEITURA INSTALA��O HIDROMETRO
				if (arrayMedicaoHistorico[3] != null) {
					arquivoTextoRegistroTipo08.append(Util
							.adicionarZerosEsquedaNumero(7,
									arrayMedicaoHistorico[3].toString()));
				} else {
					arquivoTextoRegistroTipo08.append(Util
							.completaString("", 7));
				}

				// INDICADOR PARALISAR LEITURA
				String indicadorParalisacaoLeituraHidrometroAgua = "2";
				if (imovel.getFaturamentoSituacaoTipo() != null) {

					FiltroFaturamentoSituacaoHistorico filtroFaturamentoSituacaoHistorico = new FiltroFaturamentoSituacaoHistorico();
					filtroFaturamentoSituacaoHistorico
							.adicionarParametro(new ParametroSimples(
									FiltroFaturamentoSituacaoHistorico.ID_IMOVEL,
									imovel.getId()));
					filtroFaturamentoSituacaoHistorico
							.adicionarParametro(new ParametroNulo(
									FiltroFaturamentoSituacaoHistorico.ANO_MES_FATURAMENTO_RETIRADA));
					Collection<FaturamentoSituacaoHistorico> colFiltroFaturamentoSituacaoHistorico = this
							.getControladorUtil().pesquisar(
									filtroFaturamentoSituacaoHistorico,
									FaturamentoSituacaoHistorico.class
											.getName());

					FaturamentoSituacaoHistorico faturamentoSituacaoHistorico = (FaturamentoSituacaoHistorico) Util
							.retonarObjetoDeColecao(colFiltroFaturamentoSituacaoHistorico);

					if ((faturamentoSituacaoHistorico != null
							&& anoMesReferencia >= faturamentoSituacaoHistorico
									.getAnoMesFaturamentoSituacaoInicio() && anoMesReferencia <= faturamentoSituacaoHistorico
							.getAnoMesFaturamentoSituacaoFim())) {

						// FTST_ICLEITURAPARALISACAO=1
						if (imovel.getFaturamentoSituacaoTipo()
								.getIndicadorParalisacaoLeitura()
								.equals(new Short("1"))) {

							// (tipoLiga��o=�GUA(lgti_id=1)_e_FTST_ICVALIDOAGUA=1)
							// ou
							// (tipoliga��o=PO�O(lgti_id=2)_e_FTST_ICVALIDOESGOTO=1)
							if ((medicaoTipo.getId().equals(
									MedicaoTipo.LIGACAO_AGUA) && imovel
									.getFaturamentoSituacaoTipo()
									.getIndicadorValidoAgua()
									.equals(ConstantesSistema.INDICADOR_USO_ATIVO))
									|| (medicaoTipo.getId().equals(
											MedicaoTipo.POCO) && imovel
											.getFaturamentoSituacaoTipo()
											.getIndicadorValidoEsgoto()
											.equals(ConstantesSistema.INDICADOR_USO_ATIVO))) {
								indicadorParalisacaoLeituraHidrometroAgua = "1";
							}
						}
					}
				}
//				arquivoTextoRegistroTipo08
//						.append(indicadorParalisacaoLeituraHidrometroAgua);

				// INDICADOR IMOVEL AREA COMUM
//				if (imovel.getIndicadorImovelAreaComum() != null
//						&& imovel.getIndicadorImovelAreaComum().equals(
//								ConstantesSistema.SIM)) {
//					arquivoTextoRegistroTipo08.append(imovel
//							.getIndicadorImovelAreaComum());
//				} else {
//					arquivoTextoRegistroTipo08.append(ConstantesSistema.NAO);
//				}

				arquivoTextoRegistroTipo08.append(System
						.getProperty("line.separator"));
			}
		}

		retorno[0] = arquivoTextoRegistroTipo08;
		retorno[1] = quantidadeLinhas;

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0001] - Gerar Arquivo Texto - Registro Tipo 08 [SB0007] - Obter dados
	 * do limite de leitura esperada
	 * 
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 * 
	 * @param imovel
	 * @param hidrometro
	 * @param consumoMedioHidrometro
	 * @param medicaoHistorico
	 * @param sistemaParametro
	 * @return Integer[]
	 * @throws ControladorException
	 */
	public Integer[] obterDadosLimiteLeituraEsperada(Imovel imovel,
			Hidrometro hidrometro, Integer consumoMedioHidrometro,
			MedicaoHistorico medicaoHistorico, SistemaParametro sistemaParametro)
			throws ControladorException {

		Integer[] retorno = new Integer[2];

		/*
		 * Caso o im�vel n�o possua hidr�metro instalado para o tipo de medi��o:
		 * Gravar a faixa de leitura esperada com zero.
		 */
		if (hidrometro == null) {
			retorno[0] = 0;
			retorno[1] = 0;
		} else {

			// [UC0086] - Calcular Faixa de Leitura Esperada
			int[] faixaLeituraEsperada = faixaLeituraEsperada = this
					.getControladorMicromedicao().calcularFaixaLeituraEsperada(
							consumoMedioHidrometro, null, hidrometro,
							medicaoHistorico.getLeituraAnteriorFaturamento());

			/*
			 * Caso esteja indicado nos par�metros do sistema que n�o seja
			 * gerado faixa falsa (PARM_ICGERACAOFAIXAFALSA=2 na tabela
			 * SISTEMA_PARAMETRO) ou que seja gerado de acordo com a rota
			 * (PARM_ICGERACAOFAIXAFALSA=3 na tabela SISTEMA_PARAMETRO) e esteja
			 * indicado na rota que n�o seja gerado faixa falsa
			 * (ROTA_ICGERACAOFAIXAFALSA=2): Gravar a faixa de leitura esperada.
			 */
			if ((sistemaParametro.getIndicadorFaixaFalsa() != null && sistemaParametro
					.getIndicadorFaixaFalsa().equals(
							ConstantesSistema.GERAR_FAIXA_FALSA_DESATIVO))
					|| (sistemaParametro.getIndicadorFaixaFalsa() != null
							&& sistemaParametro
									.getIndicadorFaixaFalsa()
									.equals(ConstantesSistema.GERAR_FAIXA_FALSA_ROTA)
							&& imovel.getQuadra().getRota()
									.getIndicadorGerarFalsaFaixa() != null && imovel
							.getQuadra()
							.getRota()
							.getIndicadorGerarFalsaFaixa()
							.equals(ConstantesSistema.GERAR_FAIXA_FALSA_DESATIVO))) {

				retorno[0] = faixaLeituraEsperada[0];
				retorno[1] = faixaLeituraEsperada[1];
			} else {

				// [UC0087] - Calcular Faixa de Leitura Falsa
				Object[] faixaLeituraFalsa = this.getControladorMicromedicao()
						.calcularFaixaLeituraFalsa(
								imovel,
								consumoMedioHidrometro.intValue(),
								medicaoHistorico
										.getLeituraAnteriorFaturamento(),
								medicaoHistorico, true, hidrometro);

				/*
				 * Caso o hidr�metro n�o tenha sido selecionado para faixa de
				 * leitura falsa: Gravar a faixa de leitura esperada. Caso
				 * contr�rio: gravar a faixa de leitura falsa
				 */
				boolean hidrometroSelecionado = Boolean
						.parseBoolean(faixaLeituraFalsa[2].toString());

				if (hidrometroSelecionado) {
					retorno[0] = Integer.parseInt(faixaLeituraFalsa[0]
							.toString());
					retorno[1] = Integer.parseInt(faixaLeituraFalsa[1]
							.toString());
				} else {
					retorno[0] = faixaLeituraEsperada[0];
					retorno[1] = faixaLeituraEsperada[1];
				}
			}
		}

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 02/05/2008
	 * 
	 * @param anoMesFaturamento
	 * @param faturamentoGrupo
	 * @param rota
	 * @param imovel
	 * @param qtdImoveis
	 * @param arquivoTexto
	 * @throws ControladorException
	 * @return idArquivoTextoRoteiroEmpresa
	 */
	public Integer inserirArquivoTextoRoteiroEmpresa(Integer anoMesFaturamento,
			FaturamentoGrupo faturamentoGrupo, Rota rota, Imovel imovel,
			Integer qtdImoveis, StringBuilder arquivoTexto,
			Boolean apenasNaoEnviados,
			boolean rotaSoComImoveisInformativos) throws ControladorException {

		ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa = null;

		if (!apenasNaoEnviados) {
			arquivoTextoRoteiroEmpresa = new ArquivoTextoRoteiroEmpresa();
		} else {
			FiltroArquivoTextoRoteiroEmpresa filtro = new FiltroArquivoTextoRoteiroEmpresa();
			filtro.adicionarParametro(new ParametroSimples(
					FiltroArquivoTextoRoteiroEmpresa.ROTA_ID, rota.getId()));
			filtro.adicionarParametro(new ParametroSimples(
					FiltroArquivoTextoRoteiroEmpresa.ANO_MES_REFERENCIA,
					anoMesFaturamento));

			Collection<ArquivoTextoRoteiroEmpresa> colArquivoTextoRoteiroEmpresa = Fachada
					.getInstancia().pesquisar(filtro,
							ArquivoTextoRoteiroEmpresa.class.getName());

			arquivoTextoRoteiroEmpresa = (ArquivoTextoRoteiroEmpresa) Util
					.retonarObjetoDeColecao(colArquivoTextoRoteiroEmpresa);
		}

		Rota rotaAlternativa = null;

		if (rota.getIndicadorRotaAlternativa().equals(ConstantesSistema.SIM)) {
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.ID_ROTA, rota.getId()));
			filtroRota
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroRota
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
			filtroRota.adicionarCaminhoParaCarregamentoEntidade("leiturista");
			Collection<Rota> colRotas = Fachada.getInstancia().pesquisar(
					filtroRota, Rota.class.getName());
			rotaAlternativa = (Rota) Util.retonarObjetoDeColecao(colRotas);
		}

		String nomeArquivoTexto = "";

		if (rotaAlternativa != null) {
			// NOME DO ARQUIVO
			// [FS0006] - Nome do arquivo texto
			nomeArquivoTexto = "G"
					+ Util.adicionarZerosEsquedaNumero(3,
							faturamentoGrupo.getId() + "")
					+ Util.adicionarZerosEsquedaNumero(3, rotaAlternativa
							.getSetorComercial().getLocalidade().getId()
							+ "")
					+ Util.adicionarZerosEsquedaNumero(3, rotaAlternativa
							.getSetorComercial().getCodigo() + "")
					+ Util.adicionarZerosEsquedaNumero(4,
							rotaAlternativa.getCodigo() + "")
					+ Util.adicionarZerosEsquedaNumero(6, anoMesFaturamento
							+ "");
		} else {
			// NOME DO ARQUIVO
			// [FS0006] - Nome do arquivo texto
			nomeArquivoTexto = "G"
					+ Util.adicionarZerosEsquedaNumero(3,
							faturamentoGrupo.getId() + "")
					+ Util.adicionarZerosEsquedaNumero(3, imovel
							.getLocalidade().getId() + "")
					+ Util.adicionarZerosEsquedaNumero(3, imovel
							.getSetorComercial().getCodigo() + "")
					+ Util.adicionarZerosEsquedaNumero(4, rota.getCodigo() + "")
					+ Util.adicionarZerosEsquedaNumero(6, anoMesFaturamento
							+ "");
		}

		// ARQUIVO TEMPOR�RIO GERADO PARA ROTA
		ByteArrayOutputStream baosArquivoZip = new ByteArrayOutputStream();

		GZIPOutputStream zos = null;
		BufferedWriter out = null;

		try {

			// arquivoTexto =
			// new StringBuilder( Util.reencodeString(
			// arquivoTexto.toString(), "UTF-8" ) );

			// Convertemos o StringBuilder em um vetor de array
			// arquivoTextoByte =
			// IoUtil.transformarObjetoParaBytes(arquivoTexto);

			File compactado = new File(nomeArquivoTexto + ".tar.gz");

			zos = new GZIPOutputStream(new FileOutputStream(compactado));
			File leitura = new File(nomeArquivoTexto + ".txt");

			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(leitura.getAbsolutePath())));
			out.write(arquivoTexto.toString());
			out.flush();
			ZipUtil.adicionarArquivo(zos, leitura);

			zos.close();

			FileInputStream inputStream = new FileInputStream(compactado);

			// Escrevemos aos poucos
			int INPUT_BUFFER_SIZE = 1024;
			byte[] temp = new byte[INPUT_BUFFER_SIZE];
			int numBytesRead = 0;

			while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
				baosArquivoZip.write(temp, 0, numBytesRead);
			}

			if (!apenasNaoEnviados) {
				arquivoTextoRoteiroEmpresa.setArquivoTexto(baosArquivoZip
						.toByteArray());
			} else {
				arquivoTextoRoteiroEmpresa
						.setArquivoTextoNaoRecebido(baosArquivoZip
								.toByteArray());

				this.getControladorUtil().atualizar(arquivoTextoRoteiroEmpresa);
			}

			// Fechamos o inputStream
			inputStream.close();
			baosArquivoZip.close();

			inputStream = null;
			compactado.delete();
			leitura.delete();
		} catch (IOException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (!apenasNaoEnviados) {

			Object[] intervalorNumeroQuadra = null;

			// ANO_MES_REFERENCIA
			arquivoTextoRoteiroEmpresa.setAnoMesReferencia(anoMesFaturamento);

			// FATURAMENTO_GRUPO
			arquivoTextoRoteiroEmpresa.setFaturamentoGrupo(faturamentoGrupo);

			if (rotaAlternativa != null) {
				// EMPRESA
				arquivoTextoRoteiroEmpresa.setEmpresa(rotaAlternativa
						.getEmpresa());

				// LOCALIDADE
				arquivoTextoRoteiroEmpresa.setLocalidade(rotaAlternativa
						.getSetorComercial().getLocalidade());

				// C�DIGO DO SETOR COMERCIAL
				arquivoTextoRoteiroEmpresa
						.setCodigoSetorComercial1(rotaAlternativa
								.getSetorComercial().getCodigo());

				try {
					intervalorNumeroQuadra = this.repositorioFaturamento
							.pesquisarIntervaloNumeroQuadraPorRota(rotaAlternativa
									.getId());
				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}

				// ROTA
				arquivoTextoRoteiroEmpresa.setRota(rotaAlternativa);
			} else {
				// EMPRESA
				arquivoTextoRoteiroEmpresa.setEmpresa(rota.getEmpresa());

				// LOCALIDADE
				arquivoTextoRoteiroEmpresa
						.setLocalidade(imovel.getLocalidade());

				// C�DIGO DO SETOR COMERCIAL
				arquivoTextoRoteiroEmpresa.setCodigoSetorComercial1(imovel
						.getSetorComercial().getCodigo());

				try {
					intervalorNumeroQuadra = this.repositorioFaturamento
							.pesquisarIntervaloNumeroQuadraPorRota(rota.getId());
				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}

				// ROTA
				arquivoTextoRoteiroEmpresa.setRota(rota);
			}

			// N�MERO SEQUENCIA DE LEITURA
			if (rota.getNumeroSequenciaLeitura() != null
					&& !rota.getNumeroSequenciaLeitura().equals("")) {
				if (rotaAlternativa != null) {
					arquivoTextoRoteiroEmpresa
							.setNumeroSequenciaLeitura(rotaAlternativa
									.getNumeroSequenciaLeitura());
				} else {
					arquivoTextoRoteiroEmpresa.setNumeroSequenciaLeitura(rota
							.getNumeroSequenciaLeitura());
				}
			}

			// MENOR E MAIOR N�MERO DA QUADRA PARA ROTA
			/**
		 	 * 
		 	 * Alteracao para inserir numero da quadra zero quando for 
		  	 * rota alternativa (pois rota alternativa n�o tem referencia 
		 	 * para quadra)
		 	 */
			if (intervalorNumeroQuadra != null && intervalorNumeroQuadra[0] != null) {
				arquivoTextoRoteiroEmpresa.setNumeroQuadraInicial1((Integer) intervalorNumeroQuadra[0]);
			} else {
				arquivoTextoRoteiroEmpresa.setNumeroQuadraInicial1(0);
			}
		
			if ( intervalorNumeroQuadra != null &&  intervalorNumeroQuadra[1] != null) {
				arquivoTextoRoteiroEmpresa.setNumeroQuadraFinal1((Integer) intervalorNumeroQuadra[1]);
			} else {
				arquivoTextoRoteiroEmpresa.setNumeroQuadraFinal1(0);
			}
			
			// QUANTIDADE DE IM�VEIS
			arquivoTextoRoteiroEmpresa.setQuantidadeImovel(qtdImoveis);

			arquivoTextoRoteiroEmpresa.setNomeArquivo(nomeArquivoTexto + ".gz");

			// INFORMA��ES LEITURISTA
			if (rotaAlternativa != null
					&& rotaAlternativa.getLeiturista() != null) {
				arquivoTextoRoteiroEmpresa.setLeiturista(rotaAlternativa
						.getLeiturista());
				arquivoTextoRoteiroEmpresa.setCodigoLeiturista(rotaAlternativa
						.getLeiturista().getCodigoDDD());
				arquivoTextoRoteiroEmpresa
						.setNumeroFoneLeiturista(rotaAlternativa
								.getLeiturista().getNumeroFone());

				if (rota.getNumeroLimiteImoveis() == null) {
					arquivoTextoRoteiroEmpresa.setNumeroImei(rotaAlternativa
							.getLeiturista().getNumeroImei());
				} else if (rota.getNumeroLimiteImoveis() != null
						&& qtdImoveis >= 250) {
					arquivoTextoRoteiroEmpresa.setNumeroImei(null);
				}
			} else if (rota.getLeiturista() != null) {
				arquivoTextoRoteiroEmpresa.setLeiturista(rota.getLeiturista());
				arquivoTextoRoteiroEmpresa.setCodigoLeiturista(rota
						.getLeiturista().getCodigoDDD());
				arquivoTextoRoteiroEmpresa.setNumeroFoneLeiturista(rota
						.getLeiturista().getNumeroFone());

				if (rota.getNumeroLimiteImoveis() == null) {
					arquivoTextoRoteiroEmpresa.setNumeroImei(rota
							.getLeiturista().getNumeroImei());
				} else if (rota.getNumeroLimiteImoveis() != null
						&& qtdImoveis >= 250) {
					arquivoTextoRoteiroEmpresa.setNumeroImei(null);
				}
			}

			/* 
		 * Caso rota contenha apenas im�veis informativos, insere com situa��o TRANSMITIDO
		 * */
		SituacaoTransmissaoLeitura situacaoTransmissaoLeitura = new SituacaoTransmissaoLeitura();
		if (rotaSoComImoveisInformativos) {
			situacaoTransmissaoLeitura
					.setId(SituacaoTransmissaoLeitura.TRANSMITIDO);
		}
		else{
		situacaoTransmissaoLeitura.setId(SituacaoTransmissaoLeitura.DISPONIVEL);
		}

			arquivoTextoRoteiroEmpresa
					.setSituacaoTransmissaoLeitura(situacaoTransmissaoLeitura);

			// ULTIMA ALTERACAO
			arquivoTextoRoteiroEmpresa.setUltimaAlteracao(new Date());

			// ULTIMA ALTERACAO
			arquivoTextoRoteiroEmpresa.setUltimaAlteracao(new Date());

			// TIPO DO SERVICO CELULAR
			ServicoTipoCelular servicoTipoCelular = new ServicoTipoCelular();
			servicoTipoCelular.setId(ServicoTipoCelular.IMPRESSAO_SIMULTANEA);
			arquivoTextoRoteiroEmpresa
					.setServicoTipoCelular(servicoTipoCelular);

			// INSERINDO NA BASE
			return (Integer) this.getControladorUtil().inserir(
					arquivoTextoRoteiroEmpresa);
		}

		return -1;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0001] - Gerar Arquivo Texto - Registro Tipo 09
	 * 
	 * @author S�vio Luiz
	 * @date 02/07/2009
	 * 
	 * @param imovel
	 * @param anoMesReferencia
	 * @param sistemaParametro
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	public Object[] gerarArquivoTextoRegistroDadosTarifa09(Imovel imovel,
			SistemaParametro sistemaParametro, Integer anoMesReferencia,
			FaturamentoGrupo faturamentoGrupo) throws ControladorException {

		StringBuilder arquivoTextoRegistroDadosTarifa09 = new StringBuilder();
		int quantidadeLinhas = 0;
		Object[] retorno = new Object[2];
		Collection idsConsumoTarifaCategoria = new ArrayList();

		// verifica qual o tipo de calculo da tarifa
		FiltroTarifaTipoCalculo filtroTarifaTipoCalculo = new FiltroTarifaTipoCalculo();
		filtroTarifaTipoCalculo.adicionarParametro(new ParametroSimples(
				FiltroTarifaTipoCalculo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection collTarifaTipoCalculo = getControladorUtil().pesquisar(
				filtroTarifaTipoCalculo, TarifaTipoCalculo.class.getName());

		TarifaTipoCalculo tarifaTipoCalculo = (TarifaTipoCalculo) Util
				.retonarObjetoDeColecao(collTarifaTipoCalculo);

		Short indicadorTarifaCategoria = sistemaParametro
				.getIndicadorTarifaCategoria();

		try {

			// PESQUISANDO A PARTIR DA TABELA IMOVEL_SUBCATEGORIA
			Collection dadosCategoria = this.getControladorImovel()
					.pesquisarCategoriaSubcategoriaImovel(imovel.getId());

			Collection colecaoCatSub = new ArrayList();
			// caso indique que seja para calcular pela categoria
			if (indicadorTarifaCategoria
					.equals(SistemaParametro.INDICADOR_TARIFA_CATEGORIA)) {
				Iterator ite = dadosCategoria.iterator();
				Integer idCategoriaAnterior = null;
				Object[] dadosCatSub = null;
				// recupera as categorias e coloca a subcategoria como 0.
				while (ite.hasNext()) {
					dadosCatSub = (Object[]) ite.next();

					Object[] dadosCatSubNovo = new Object[2];
					if (idCategoriaAnterior == null
							|| idCategoriaAnterior
									.equals((Integer) dadosCatSub[0])) {
						if (idCategoriaAnterior == null) {
							dadosCatSubNovo[0] = dadosCatSub[0];
							dadosCatSubNovo[1] = 0;
							colecaoCatSub.add(dadosCatSubNovo);
						}
						idCategoriaAnterior = (Integer) dadosCatSub[0];

					} else {
						dadosCatSubNovo[0] = dadosCatSub[0];
						dadosCatSubNovo[1] = 0;
						colecaoCatSub.add(dadosCatSubNovo);
						idCategoriaAnterior = (Integer) dadosCatSub[0];
					}
				}
			} else {
				colecaoCatSub.addAll(dadosCategoria);
			}

			Collection<Object[]> colecaoDadosTarifa = new ArrayList();

			if (tarifaTipoCalculo != null
					&& tarifaTipoCalculo.getId().equals(
							TarifaTipoCalculo.CALCULO_POR_REFERENCIA)) {
				Date dataFaturamento = Util.criarData(1,
						Util.obterMes(anoMesReferencia),
						Util.obterAno(anoMesReferencia));

				Collection<Object[]> colecaoDadosMaiorTarifa = repositorioFaturamento
						.pesquisarDadosConsumoMaiorTarifaVigenciaPorTarifa(
								dataFaturamento, imovel.getConsumoTarifa()
										.getId());

				if (colecaoDadosMaiorTarifa != null
						&& !colecaoDadosMaiorTarifa.equals("")) {
					for (Object[] dadosTarifaMaior : colecaoDadosMaiorTarifa) {
						if (dadosTarifaMaior[0] != null
								&& dadosTarifaMaior[1] != null) {
							Iterator ite = colecaoCatSub.iterator();
							Collection<Object[]> colecaoDadosTarifaPartes = null;
							while (ite.hasNext()) {
								Object[] dadosCatSub = (Object[]) ite.next();
								colecaoDadosTarifaPartes = repositorioFaturamento
										.pesquisarDadosConsumoTarifaVigencia(
												(Date) dadosTarifaMaior[1],
												(Integer) dadosTarifaMaior[0],
												(Integer) dadosCatSub[0],
												(Integer) dadosCatSub[1]);
								if (colecaoDadosTarifaPartes == null
										|| colecaoDadosTarifaPartes.isEmpty()) {
									colecaoDadosTarifaPartes = repositorioFaturamento
											.pesquisarDadosConsumoTarifaVigencia(
													(Date) dadosTarifaMaior[1],
													(Integer) dadosTarifaMaior[0],
													(Integer) dadosCatSub[0], 0);
								}
								boolean mesmoConsumoTarifaCategoria = false;
								if (colecaoDadosTarifa != null
										&& !colecaoDadosTarifa.isEmpty()) {
									for (Object[] dadosTarifaCategoria : colecaoDadosTarifa) {
										Integer idConsumoTarifaCategoria = (Integer) dadosTarifaCategoria[0];
										if (colecaoDadosTarifaPartes != null
												&& !colecaoDadosTarifaPartes
														.isEmpty()) {
											for (Object[] dadosTarifaCategoriaPartes : colecaoDadosTarifaPartes) {
												Integer idConsumoTarifaCategoriaPartes = (Integer) dadosTarifaCategoriaPartes[0];
												if (idConsumoTarifaCategoria
														.equals(idConsumoTarifaCategoriaPartes)) {
													mesmoConsumoTarifaCategoria = true;
													break;
												}
											}
										}
									}
								}

								if (!mesmoConsumoTarifaCategoria) {
									colecaoDadosTarifa
											.addAll(colecaoDadosTarifaPartes);
								}
							}

						}
					}
				}

			} else {

				Integer anoMesReferenciaAnterior = Util.subtrairMesDoAnoMes(
						anoMesReferencia, 1);

				Date dataLeituraAnterior = this.getControladorMicromedicao()
						.pesquisarMaiorDataLeituraImoveis(imovel.getId(),
								anoMesReferenciaAnterior);

				// caso n�o tenha a data de leitura anterior, pegar a data
				// prevista do cronograma anterior
				if (dataLeituraAnterior == null
						|| dataLeituraAnterior.equals("")) {
					FaturamentoAtividade faturamentoAtividade = new FaturamentoAtividade();
					faturamentoAtividade
							.setId(FaturamentoAtividade.GERAR_ARQUIVO_LEITURA);
					Object[] datasCronogramaAnterior = repositorioFaturamento
							.obterDataPrevistaRealizadaFaturamentoAtividadeCronograma(
									faturamentoGrupo, anoMesReferenciaAnterior,
									faturamentoAtividade);
					if (datasCronogramaAnterior != null) {
						dataLeituraAnterior = (Date) datasCronogramaAnterior[0];
					} else {
						Object[] datasCronogramaAtual = repositorioFaturamento
								.obterDataPrevistaRealizadaFaturamentoAtividadeCronograma(
										faturamentoGrupo, anoMesReferencia,
										faturamentoAtividade);
						
						dataLeituraAnterior = Util.subtrairNumeroDiasDeUmaData((Date) datasCronogramaAtual[0], 30);
					}
				}
				Iterator ite = colecaoCatSub.iterator();
				boolean dataVigenciaIgualAnterior = false;
				Collection colecaoDadosTarifaProporcional = null;
				while (ite.hasNext()) {
					Object[] dadosCatSub = (Object[]) ite.next();
					// consulta as vigencias entre a data de leitura anterior e
					// atual
					colecaoDadosTarifaProporcional = repositorioFaturamento
							.pesquisarDadosConsumoTarifaVigenciaProporcional(
									dataLeituraAnterior, imovel
											.getConsumoTarifa().getId(),
									(Integer) dadosCatSub[0],
									(Integer) dadosCatSub[1]);
					// caso exista e a data de leitura anterior n�o seja igual a
					// data de vigencia
					// n�o procura mais tarifa para esse im�vel
					if (colecaoDadosTarifaProporcional != null
							&& !colecaoDadosTarifaProporcional.isEmpty()) {

						Iterator iteProporcional = colecaoDadosTarifaProporcional
								.iterator();
						while (iteProporcional.hasNext()) {
							Object[] dadosProporcional = (Object[]) iteProporcional
									.next();

							if (dadosProporcional[2] != null) {
								Date dataVigencia = (Date) dadosProporcional[2];
								if (Util.compararData(dataLeituraAnterior,
										dataVigencia) == 0) {
									dataVigenciaIgualAnterior = true;
									break;
								}
							}
						}

						// Caso n�o tenha encontrado nenhuma tarifa proporcional
						// ou a data de vigencia da tarifa
						// seja igual a data anterior da leitura
						if (colecaoDadosTarifaProporcional == null
								|| colecaoDadosTarifaProporcional.isEmpty()
								|| !dataVigenciaIgualAnterior) {
							// pesquisa a tarifa com a maior data de vigencia
							// que seja menor que
							// a data de leitura anterior
							Collection<Object[]> colecaoDadosMaiorTarifa = repositorioFaturamento
									.pesquisarDadosConsumoMaiorTarifaVigenciaPorTarifa(
											dataLeituraAnterior, imovel
													.getConsumoTarifa().getId());

							if (colecaoDadosMaiorTarifa != null
									&& !colecaoDadosMaiorTarifa.equals("")) {
								for (Object[] dadosTarifaMaior : colecaoDadosMaiorTarifa) {
									if (dadosTarifaMaior[0] != null
											&& dadosTarifaMaior[1] != null) {
										Collection<Object[]> colecaoDadosTarifaPartes = null;

										colecaoDadosTarifaPartes = repositorioFaturamento
												.pesquisarDadosConsumoTarifaVigencia(
														(Date) dadosTarifaMaior[1],
														(Integer) dadosTarifaMaior[0],
														(Integer) dadosCatSub[0],
														(Integer) dadosCatSub[1]);

										if (colecaoDadosTarifaPartes == null
												|| colecaoDadosTarifaPartes
														.isEmpty()) {
											colecaoDadosTarifaPartes = repositorioFaturamento
													.pesquisarDadosConsumoTarifaVigencia(
															(Date) dadosTarifaMaior[1],
															(Integer) dadosTarifaMaior[0],
															(Integer) dadosCatSub[0],
															0);
										}
										boolean mesmoConsumoTarifaCategoria = false;
										if (colecaoDadosTarifa != null
												&& !colecaoDadosTarifa
														.isEmpty()) {
											for (Object[] dadosTarifaCategoria : colecaoDadosTarifa) {
												Integer idConsumoTarifaCategoria = (Integer) dadosTarifaCategoria[0];
												if (colecaoDadosTarifaPartes != null
														&& !colecaoDadosTarifaPartes
																.isEmpty()) {
													for (Object[] dadosTarifaCategoriaPartes : colecaoDadosTarifaPartes) {
														Integer idConsumoTarifaCategoriaPartes = (Integer) dadosTarifaCategoriaPartes[0];
														if (idConsumoTarifaCategoria
																.equals(idConsumoTarifaCategoriaPartes)) {
															mesmoConsumoTarifaCategoria = true;
															break;
														}
													}
												}
											}
										}

										if (!mesmoConsumoTarifaCategoria) {
											colecaoDadosTarifa
													.addAll(colecaoDadosTarifaPartes);
										}

									}
								}
							}

						}
						// coloca a cole��o de vigencia proporcial caso exista
						// s� � colocado nesse momento, para ficar ordenado pela
						// data
						// de vigencia
						if (colecaoDadosTarifaProporcional != null
								&& !colecaoDadosTarifaProporcional.isEmpty()) {
							colecaoDadosTarifa
									.addAll(colecaoDadosTarifaProporcional);
						}
					} else {
						// Caso n�o tenha encontrado nenhuma tarifa proporcional
						// ou a data de vigencia da tarifa
						// seja igual a data anterior da leitura

						// pesquisa a tarifa com a maior data de vigencia que
						// seja menor que
						// a data de leitura anterior
						Collection<Object[]> colecaoDadosMaiorTarifa = repositorioFaturamento
								.pesquisarDadosConsumoMaiorTarifaVigenciaPorTarifa(
										dataLeituraAnterior, imovel
												.getConsumoTarifa().getId());

						if (colecaoDadosMaiorTarifa != null
								&& !colecaoDadosMaiorTarifa.equals("")) {
							for (Object[] dadosTarifaMaior : colecaoDadosMaiorTarifa) {
								if (dadosTarifaMaior[0] != null
										&& dadosTarifaMaior[1] != null) {
									Collection<Object[]> colecaoDadosTarifaPartes = null;

									colecaoDadosTarifaPartes = repositorioFaturamento
											.pesquisarDadosConsumoTarifaVigencia(
													(Date) dadosTarifaMaior[1],
													(Integer) dadosTarifaMaior[0],
													(Integer) dadosCatSub[0],
													(Integer) dadosCatSub[1]);

									if (colecaoDadosTarifaPartes == null
											|| colecaoDadosTarifaPartes
													.isEmpty()) {
										colecaoDadosTarifaPartes = repositorioFaturamento
												.pesquisarDadosConsumoTarifaVigencia(
														(Date) dadosTarifaMaior[1],
														(Integer) dadosTarifaMaior[0],
														(Integer) dadosCatSub[0],
														0);
									}
									boolean mesmoConsumoTarifaCategoria = false;
									if (colecaoDadosTarifa != null
											&& !colecaoDadosTarifa.isEmpty()) {
										for (Object[] dadosTarifaCategoria : colecaoDadosTarifa) {
											Integer idConsumoTarifaCategoria = (Integer) dadosTarifaCategoria[0];
											if (colecaoDadosTarifaPartes != null
													&& !colecaoDadosTarifaPartes
															.isEmpty()) {
												for (Object[] dadosTarifaCategoriaPartes : colecaoDadosTarifaPartes) {
													Integer idConsumoTarifaCategoriaPartes = (Integer) dadosTarifaCategoriaPartes[0];
													if (idConsumoTarifaCategoria
															.equals(idConsumoTarifaCategoriaPartes)) {
														mesmoConsumoTarifaCategoria = true;
														break;
													}
												}
											}
										}
									}

									if (!mesmoConsumoTarifaCategoria) {
										colecaoDadosTarifa
												.addAll(colecaoDadosTarifaPartes);
									}

								}
							}
						}
					}
				}

			}

			if (colecaoDadosTarifa != null && !colecaoDadosTarifa.isEmpty()) {
				for (Object[] dadosTarifa : colecaoDadosTarifa) {
					quantidadeLinhas = quantidadeLinhas + 1;
					arquivoTextoRegistroDadosTarifa09.append("09");
					if (dadosTarifa[0] != null) {
						idsConsumoTarifaCategoria.add((Integer) dadosTarifa[0]);
					}
					// C�digo da Tarifa
					if (dadosTarifa[1] != null) {
						arquivoTextoRegistroDadosTarifa09.append(Util
								.adicionarZerosEsquedaNumero(2, ""
										+ dadosTarifa[1]));
					} else {
						arquivoTextoRegistroDadosTarifa09.append(Util
								.adicionarZerosEsquedaNumero(2, ""));
					}
					// Data de Vigencia
					if (dadosTarifa[2] != null) {
						Date dataVigencia = (Date) dadosTarifa[2];
						arquivoTextoRegistroDadosTarifa09.append(Util
								.formatarDataAAAAMMDD(dataVigencia));
					} else {
						arquivoTextoRegistroDadosTarifa09.append(Util
								.adicionarZerosEsquedaNumero(8, ""));
					}
					// C�digo da Categoria
					if (dadosTarifa[3] != null) {
						arquivoTextoRegistroDadosTarifa09.append(Util
								.adicionarZerosEsquedaNumero(1, ""
										+ dadosTarifa[3]));
					} else {
						arquivoTextoRegistroDadosTarifa09.append(Util
								.adicionarZerosEsquedaNumero(1, ""));
					}
					// C�digo da Subcategoria
					if (indicadorTarifaCategoria.equals(ConstantesSistema.NAO)
							&& dadosTarifa[4] != null) {
						arquivoTextoRegistroDadosTarifa09.append(Util
								.adicionarZerosEsquedaNumero(3, ""
										+ dadosTarifa[4]));
					} else {
						arquivoTextoRegistroDadosTarifa09.append(Util
								.completaString("", 3));
					}
					// Consumo m�nimo
					if (dadosTarifa[5] != null) {
						arquivoTextoRegistroDadosTarifa09.append(Util
								.adicionarZerosEsquedaNumero(6, ""
										+ dadosTarifa[5]));
					} else {
						arquivoTextoRegistroDadosTarifa09.append(Util
								.adicionarZerosEsquedaNumero(6, ""));
					}
					// Tarifa m�nima
					if (dadosTarifa[6] != null) {
						arquivoTextoRegistroDadosTarifa09
								.append(Util.adicionarZerosEsquedaNumero(
										14,
										Util.formatarBigDecimalComPonto((BigDecimal) dadosTarifa[6])));
					} else {
						arquivoTextoRegistroDadosTarifa09.append(Util
								.adicionarZerosEsquedaNumero(14, ""));
					}

					arquivoTextoRegistroDadosTarifa09.append(System
							.getProperty("line.separator"));

				}

				Object[] tarifaFaixa10 = gerarArquivoTextoRegistroDadosTarifaFaixa10(
						idsConsumoTarifaCategoria, indicadorTarifaCategoria);

				// Inseri as faixas das tarifas
				arquivoTextoRegistroDadosTarifa09.append(tarifaFaixa10[0]);
				int quantidadeLinhasRegistroTipo10 = (Integer) tarifaFaixa10[1];
				quantidadeLinhas = quantidadeLinhas
						+ quantidadeLinhasRegistroTipo10;
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		retorno[0] = arquivoTextoRegistroDadosTarifa09;
		retorno[1] = quantidadeLinhas;

		return retorno;

	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0001] - Gerar Arquivo Texto - Registro Tipo 10
	 * 
	 * @author S�vio Luiz
	 * @date 02/07/2009
	 * 
	 * @param imovel
	 * @param anoMesReferencia
	 * @param sistemaParametro
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	public Object[] gerarArquivoTextoRegistroDadosTarifaFaixa10(
			Collection idsConsumoTarifaCategoria, Short indicadorTarifaCategoria)
			throws ControladorException {

		StringBuilder arquivoTextoRegistroDadosTarifaFaixa10 = new StringBuilder();
		int quantidadeLinhas = 0;
		Object[] retorno = new Object[2];
		Collection<Object[]> colecaoDadosTarifaFaixa = null;
		try {

			colecaoDadosTarifaFaixa = repositorioFaturamento
					.pesquisarDadosConsumoTarifaFaixa(idsConsumoTarifaCategoria);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoDadosTarifaFaixa != null
				&& !colecaoDadosTarifaFaixa.isEmpty()) {
			int count = colecaoDadosTarifaFaixa.size();
			for (Object[] dadosTarifa : colecaoDadosTarifaFaixa) {
				quantidadeLinhas = quantidadeLinhas + 1;
				arquivoTextoRegistroDadosTarifaFaixa10.append("10");
				count--;
				// C�digo da Tarifa
				if (dadosTarifa[0] != null) {
					arquivoTextoRegistroDadosTarifaFaixa10
							.append(Util.adicionarZerosEsquedaNumero(2, ""
									+ dadosTarifa[0]));
				} else {
					arquivoTextoRegistroDadosTarifaFaixa10.append(Util
							.adicionarZerosEsquedaNumero(2, ""));
				}
				// Data de Vigencia
				if (dadosTarifa[1] != null) {
					Date dataVigencia = (Date) dadosTarifa[1];
					arquivoTextoRegistroDadosTarifaFaixa10.append(Util
							.formatarDataAAAAMMDD(dataVigencia));
				} else {
					arquivoTextoRegistroDadosTarifaFaixa10.append(Util
							.adicionarZerosEsquedaNumero(8, ""));
				}
				// C�digo da Categoria
				if (dadosTarifa[2] != null) {
					arquivoTextoRegistroDadosTarifaFaixa10
							.append(Util.adicionarZerosEsquedaNumero(1, ""
									+ dadosTarifa[2]));
				} else {
					arquivoTextoRegistroDadosTarifaFaixa10.append(Util
							.adicionarZerosEsquedaNumero(1, ""));
				}
				// C�digo da Subcategoria
				if (indicadorTarifaCategoria.equals(ConstantesSistema.NAO)
						&& dadosTarifa[3] != null) {
					arquivoTextoRegistroDadosTarifaFaixa10
							.append(Util.adicionarZerosEsquedaNumero(3, ""
									+ dadosTarifa[3]));
				} else {
					arquivoTextoRegistroDadosTarifaFaixa10.append(Util
							.completaString("", 3));
				}
				// Limite Inicial da Faixa
				if (dadosTarifa[4] != null) {
					arquivoTextoRegistroDadosTarifaFaixa10
							.append(Util.adicionarZerosEsquedaNumero(6, ""
									+ dadosTarifa[4]));
				} else {
					arquivoTextoRegistroDadosTarifaFaixa10.append(Util
							.adicionarZerosEsquedaNumero(6, ""));
				}
				// Limite Final da Faixa
				if (dadosTarifa[5] != null) {
					arquivoTextoRegistroDadosTarifaFaixa10
							.append(Util.adicionarZerosEsquedaNumero(6, ""
									+ dadosTarifa[5]));
				} else {
					arquivoTextoRegistroDadosTarifaFaixa10.append(Util
							.adicionarZerosEsquedaNumero(6, ""));
				}
				// Valor do m3 na Faixa
				if (dadosTarifa[6] != null) {
					arquivoTextoRegistroDadosTarifaFaixa10
							.append(Util.adicionarZerosEsquedaNumero(
									14,
									Util.formatarBigDecimalComPonto((BigDecimal) dadosTarifa[6])));
				} else {
					arquivoTextoRegistroDadosTarifaFaixa10.append(Util
							.adicionarZerosEsquedaNumero(14, ""));
				}

				if (count != 0) {
					arquivoTextoRegistroDadosTarifaFaixa10.append(System
							.getProperty("line.separator"));
				}

			}
		}

		retorno[0] = arquivoTextoRegistroDadosTarifaFaixa10;
		retorno[1] = quantidadeLinhas;

		return retorno;

	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * Formata a parte de padr�o da qualidade da �gua e a qualidade da �gua
	 * 
	 * @author S�vio Luiz
	 * @date 13/07/2009
	 * 
	 * @param imovel
	 * @param anoMesReferencia
	 * @param sistemaParametro
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	public StringBuilder gerarArquivoTextoQualidadeAgua(Integer idLocalidade,
			Integer idSetor, Integer anoMesReferencia, Integer idQuadraFace)
			throws ControladorException {

		StringBuilder arquivoTextoQualidadeAgua = new StringBuilder();

		// Pesquisa Dados Qualidade �gua Padr�o
		FiltroQualidadeAguaPadrao filtroQualidadeAguaPadrao = new FiltroQualidadeAguaPadrao();

		Collection colecaoQualidadeAguaPadrao = null;

		colecaoQualidadeAguaPadrao = getControladorUtil().pesquisar(
				filtroQualidadeAguaPadrao, QualidadeAguaPadrao.class.getName());

		if (colecaoQualidadeAguaPadrao != null
				&& !colecaoQualidadeAguaPadrao.isEmpty()) {
			QualidadeAguaPadrao qualidadeAguaPadrao = (QualidadeAguaPadrao) colecaoQualidadeAguaPadrao
					.iterator().next();
			if (qualidadeAguaPadrao != null) {
				// Turbidez
				if (qualidadeAguaPadrao.getDescricaoPadraoTurbidez() != null) {
					arquivoTextoQualidadeAgua.append(Util.completaString(
							qualidadeAguaPadrao.getDescricaoPadraoTurbidez(),
							20));
				} else {
					arquivoTextoQualidadeAgua.append(Util
							.completaString("", 20));
				}
				// PH
				if (qualidadeAguaPadrao.getDescricaoPadraoPh() != null) {
					arquivoTextoQualidadeAgua.append(Util.completaString(
							qualidadeAguaPadrao.getDescricaoPadraoPh(), 20));
				} else {
					arquivoTextoQualidadeAgua.append(Util
							.completaString("", 20));
				}
				// Cor
				if (qualidadeAguaPadrao.getDescricaoPadraoCor() != null) {
					arquivoTextoQualidadeAgua.append(Util.completaString(
							qualidadeAguaPadrao.getDescricaoPadraoCor(), 20));
				} else {
					arquivoTextoQualidadeAgua.append(Util
							.completaString("", 20));
				}
				// Cloro
				if (qualidadeAguaPadrao.getDescricaoPadraoCloro() != null) {
					arquivoTextoQualidadeAgua.append(Util.completaString(
							qualidadeAguaPadrao.getDescricaoPadraoCloro(), 20));
				} else {
					arquivoTextoQualidadeAgua.append(Util
							.completaString("", 20));
				}
				// Fluor
				if (qualidadeAguaPadrao.getDescricaoPadraoFluor() != null) {
					arquivoTextoQualidadeAgua.append(Util.completaString(
							qualidadeAguaPadrao.getDescricaoPadraoFluor(), 20));
				} else {
					arquivoTextoQualidadeAgua.append(Util
							.completaString("", 20));
				}
				// Ferro
				if (qualidadeAguaPadrao.getDescricaoPadraoFerro() != null) {
					arquivoTextoQualidadeAgua.append(Util.completaString(
							qualidadeAguaPadrao.getDescricaoPadraoFerro(), 20));
				} else {
					arquivoTextoQualidadeAgua.append(Util
							.completaString("", 20));
				}
				// Coliformes Totais
				if (qualidadeAguaPadrao.getDescricaoPadraoColiformesTotais() != null) {
					arquivoTextoQualidadeAgua.append(Util.completaString(
							qualidadeAguaPadrao
									.getDescricaoPadraoColiformesTotais(), 20));
				} else {
					arquivoTextoQualidadeAgua.append(Util
							.completaString("", 20));
				}
				// Coliformes Fecais
				if (qualidadeAguaPadrao.getDescricaoPadraoColiformesFecais() != null) {
					arquivoTextoQualidadeAgua.append(Util.completaString(
							qualidadeAguaPadrao
									.getDescricaoPadraoColiformesFecais(), 20));
				} else {
					arquivoTextoQualidadeAgua.append(Util
							.completaString("", 20));
				}
				// Nitrato
				if (qualidadeAguaPadrao.getDescricaoNitrato() != null) {
					arquivoTextoQualidadeAgua.append(Util.completaString(
							qualidadeAguaPadrao.getDescricaoNitrato(), 20));
				} else {
					arquivoTextoQualidadeAgua.append(Util
							.completaString("", 20));
				}
				// Coliformes Termo Tolerantes
				if (qualidadeAguaPadrao
						.getDescricaoPadraoColiformesTermotolerantes() != null) {
					arquivoTextoQualidadeAgua
							.append(Util.completaString(
									qualidadeAguaPadrao
											.getDescricaoPadraoColiformesTermotolerantes(),
									20));
				} else {
					arquivoTextoQualidadeAgua.append(Util
							.completaString("", 20));
				}
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString("", 200));
			}
		} else {
			arquivoTextoQualidadeAgua.append(Util.completaString("", 200));
		}

		// Pesquisa Dados Qualidade �gua
		FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();

		Collection colecaoQualidadeAgua = null;

		/**
		 * Adi��o da consulta quando a empresa utiliza o sistema
		 * de abastecimento para registro da qualidade da �gua
		 * */
		FiltroQuadraFace filtroQuadraFace = new FiltroQuadraFace();
		filtroQuadraFace.adicionarParametro(new ParametroSimples(
				FiltroQuadraFace.ID, idQuadraFace));

		filtroQuadraFace
				.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		filtroQuadraFace
				.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional.setorAbastecimento");
		filtroQuadraFace
				.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional.setorAbastecimento.sistemaAbastecimento");
		filtroQuadraFace
				.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional.setorAbastecimento.sistemaAbastecimento.fonteCaptacao");
		filtroQuadraFace
				.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional.setorAbastecimento.sistemaAbastecimento.fonteCaptacao.tipoCaptacao");

		Collection colecaoQudraFace = getControladorUtil().pesquisar(
				filtroQuadraFace, QuadraFace.class.getName());

		QuadraFace quadraFace = (QuadraFace) Util
				.retonarObjetoDeColecao(colecaoQudraFace);

		if (quadraFace.getDistritoOperacional() != null
				&& quadraFace.getDistritoOperacional().getSetorAbastecimento() != null
				&& quadraFace.getDistritoOperacional().getSetorAbastecimento()
						.getSistemaAbastecimento() != null) {

			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
					FiltroQualidadeAgua.SISTEMA_ABASTECIMENTO, quadraFace
							.getDistritoOperacional().getSetorAbastecimento()
							.getSistemaAbastecimento().getId()));

			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
					FiltroQualidadeAgua.ANO_MES_REFERENCIA, anoMesReferencia));

			filtroQualidadeAgua
					.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
			filtroQualidadeAgua
					.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao.tipoCaptacao");

			colecaoQualidadeAgua = getControladorUtil().pesquisar(
					filtroQualidadeAgua, QualidadeAgua.class.getName());

		}

		if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {

			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
					FiltroQualidadeAgua.LOCALIDADE_ID, idLocalidade));

			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
					FiltroQualidadeAgua.SETOR_COMERCIAL_ID, idSetor));

			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
					FiltroQualidadeAgua.ANO_MES_REFERENCIA, anoMesReferencia));

			filtroQualidadeAgua
					.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");

			colecaoQualidadeAgua = getControladorUtil().pesquisar(
					filtroQualidadeAgua, QualidadeAgua.class.getName());
		}

		if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {

			filtroQualidadeAgua.limparListaParametros();

			colecaoQualidadeAgua = null;
			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
					FiltroQualidadeAgua.LOCALIDADE_ID, idLocalidade));

			filtroQualidadeAgua.adicionarParametro(new ParametroNulo(
					FiltroQualidadeAgua.SETOR_COMERCIAL_ID));

			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
					FiltroQualidadeAgua.ANO_MES_REFERENCIA, anoMesReferencia));

			filtroQualidadeAgua
					.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");

			colecaoQualidadeAgua = getControladorUtil().pesquisar(
					filtroQualidadeAgua, QualidadeAgua.class.getName());
		}
		if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {

			filtroQualidadeAgua.limparListaParametros();

			colecaoQualidadeAgua = null;

			filtroQualidadeAgua.adicionarParametro(new ParametroNulo(
					FiltroQualidadeAgua.LOCALIDADE_ID));

			filtroQualidadeAgua.adicionarParametro(new ParametroNulo(
					FiltroQualidadeAgua.SETOR_COMERCIAL_ID));

			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
					FiltroQualidadeAgua.ANO_MES_REFERENCIA, anoMesReferencia));

			filtroQualidadeAgua
					.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");

			colecaoQualidadeAgua = getControladorUtil().pesquisar(
					filtroQualidadeAgua, QualidadeAgua.class.getName());
		}

		if (colecaoQualidadeAgua != null && !colecaoQualidadeAgua.isEmpty()) {

			QualidadeAgua qualidadeAgua = (QualidadeAgua) colecaoQualidadeAgua
					.iterator().next();

			// m�s e ano de refer�ncia
			if (qualidadeAgua.getAnoMesReferencia() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(Util
						.formatarAnoMesParaMesAnoSemBarra(qualidadeAgua
								.getAnoMesReferencia()), 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString("", 6));
			}
			// cloro
			if (qualidadeAgua.getNumeroCloroResidual() != null
					&& !qualidadeAgua.getNumeroCloroResidual().equals(0)) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getNumeroCloroResidual().toString(), 5));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString("", 5));
			}
			// turbidez
			if (qualidadeAgua.getNumeroIndiceTurbidez() != null
					&& !qualidadeAgua.getNumeroIndiceTurbidez().equals(0)) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getNumeroIndiceTurbidez().toString(), 5));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 5));
			}
			// ph
			if (qualidadeAgua.getNumeroIndicePh() != null
					&& !qualidadeAgua.getNumeroIndicePh().equals(0)) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getNumeroIndicePh().toString(), 5));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 5));
			}
			// Cor
			if (qualidadeAgua.getNumeroIndiceCor() != null
					&& !qualidadeAgua.getNumeroIndiceCor().equals(0)) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getNumeroIndiceCor().toString(), 5));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 5));
			}
			// fluor
			if (qualidadeAgua.getNumeroIndiceFluor() != null
					&& !qualidadeAgua.getNumeroIndiceFluor().equals(0)) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getNumeroIndiceFluor().toString(), 5));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 5));
			}
			// ferro
			if (qualidadeAgua.getNumeroIndiceFerro() != null
					&& !qualidadeAgua.getNumeroIndiceFerro().equals(0)) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getNumeroIndiceFerro().toString(), 5));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 5));
			}
			// Coliformes Totais
			if (qualidadeAgua.getNumeroIndiceColiformesTotais() != null
					&& !qualidadeAgua.getNumeroIndiceColiformesTotais().equals(
							0)) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getNumeroIndiceColiformesTotais()
								.toString(), 5));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 5));
			}
			// Coliformes Fecais
			if (qualidadeAgua.getNumeroIndiceColiformesFecais() != null
					&& !qualidadeAgua.getNumeroIndiceColiformesFecais().equals(
							0)) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getNumeroIndiceColiformesFecais()
								.toString(), 5));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 5));
			}
			// nitrato
			if (qualidadeAgua.getNumeroNitrato() != null
					&& !qualidadeAgua.getNumeroNitrato().equals(0)) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getNumeroNitrato().toString(), 5));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 5));
			}
			// Coliformes Termo Tolerantes
			if (qualidadeAgua.getNumeroIndiceColiformesTermotolerantes() != null
					&& !qualidadeAgua
							.getNumeroIndiceColiformesTermotolerantes().equals(
									0)) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua
								.getNumeroIndiceColiformesTermotolerantes()
								.toString(), 5));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 5));
			}
			// fonte
			if (qualidadeAgua.getFonteCaptacao() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getFonteCaptacao().getDescricao(), 30));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 30));
			}

			// Exigidas
			if (qualidadeAgua.getQuantidadeTurbidezExigidas() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getQuantidadeTurbidezExigidas() + "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			if (qualidadeAgua.getQuantidadeCorExigidas() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getQuantidadeCorExigidas() + "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			if (qualidadeAgua.getQuantidadeCloroExigidas() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getQuantidadeCloroExigidas() + "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			if (qualidadeAgua.getQuantidadeFluorExigidas() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getQuantidadeFluorExigidas() + "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			if (qualidadeAgua.getQuantidadeColiformesTotaisExigidas() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getQuantidadeColiformesTotaisExigidas()
								+ "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			if (qualidadeAgua.getQuantidadeColiformesFecaisExigidas() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getQuantidadeColiformesFecaisExigidas()
								+ "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			if (qualidadeAgua.getQuantidadeColiformesTermotolerantesExigidas() != null) {
				arquivoTextoQualidadeAgua
						.append(Util.completaString(
								qualidadeAgua
										.getQuantidadeColiformesTermotolerantesExigidas()
										+ "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			// Analisadas
			if (qualidadeAgua.getQuantidadeTurbidezAnalisadas() != null) {
				arquivoTextoQualidadeAgua
						.append(Util.completaString(
								qualidadeAgua.getQuantidadeTurbidezAnalisadas()
										+ "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			if (qualidadeAgua.getQuantidadeCorAnalisadas() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getQuantidadeCorAnalisadas() + "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			if (qualidadeAgua.getQuantidadeCloroAnalisadas() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getQuantidadeCloroAnalisadas() + "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			if (qualidadeAgua.getQuantidadeFluorAnalisadas() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getQuantidadeFluorAnalisadas() + "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			if (qualidadeAgua.getQuantidadeColiformesTotaisAnalisadas() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getQuantidadeColiformesTotaisAnalisadas()
								+ "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			if (qualidadeAgua.getQuantidadeColiformesFecaisAnalisadas() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getQuantidadeColiformesFecaisAnalisadas()
								+ "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			if (qualidadeAgua
					.getQuantidadeColiformesTermotolerantesAnalisadas() != null) {
				arquivoTextoQualidadeAgua
						.append(Util.completaString(
								qualidadeAgua
										.getQuantidadeColiformesTermotolerantesAnalisadas()
										+ "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}

			// Em Conformidade
			if (qualidadeAgua.getQuantidadeTurbidezConforme() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getQuantidadeTurbidezConforme() + "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			if (qualidadeAgua.getQuantidadeCorConforme() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getQuantidadeCorConforme() + "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			if (qualidadeAgua.getQuantidadeCloroConforme() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getQuantidadeCloroConforme() + "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			if (qualidadeAgua.getQuantidadeFluorConforme() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getQuantidadeFluorConforme() + "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			if (qualidadeAgua.getQuantidadeColiformesTotaisConforme() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getQuantidadeColiformesTotaisConforme()
								+ "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			if (qualidadeAgua.getQuantidadeColiformesFecaisConforme() != null) {
				arquivoTextoQualidadeAgua.append(Util.completaString(
						qualidadeAgua.getQuantidadeColiformesFecaisConforme()
								+ "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
			if (qualidadeAgua.getQuantidadeColiformesTermotolerantesConforme() != null) {
				arquivoTextoQualidadeAgua
						.append(Util.completaString(
								qualidadeAgua
										.getQuantidadeColiformesTermotolerantesConforme()
										+ "", 6));
			} else {
				arquivoTextoQualidadeAgua.append(Util.completaString(" ", 6));
			}
		} else {
			arquivoTextoQualidadeAgua.append(Util.completaString("", 212));
		}
		return arquivoTextoQualidadeAgua;
	}

	private Object[] formatarCobrancaDocumentoItemParaImpressaoSimultanea(
			Integer idImovel, CobrancaDocumento cobrancaDocumento,
			int quantidadeContas) throws ControladorException {

		Object[] retorno = new Object[2];
		StringBuilder arquivoTextoRegistroTipo07 = new StringBuilder();
		int quantidadeLinhas = 0;
		if (cobrancaDocumento != null && !cobrancaDocumento.equals("")) {
			Collection colecaoCobrancaDocumentoItemConta = null;
			try {
				colecaoCobrancaDocumentoItemConta = repositorioCobranca
						.selecionarCobrancaDocumentoItemReferenteConta(cobrancaDocumento);

			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}

			if (colecaoCobrancaDocumentoItemConta != null
					&& !colecaoCobrancaDocumentoItemConta.isEmpty()) {

				int countImpressao = colecaoCobrancaDocumentoItemConta.size()
						- (quantidadeContas - 1);

				if (colecaoCobrancaDocumentoItemConta.size() > quantidadeContas) {
					// indicadorEstouro = 1;

					CalcularValorDataVencimentoAnteriorHelper contaValoresHelper = getControladorCobranca()
							.calcularValorDataVencimentoAnterior(
									colecaoCobrancaDocumentoItemConta,
									quantidadeContas);

					quantidadeLinhas = quantidadeLinhas + 1;
					// TIPO DO REGISTRO
					arquivoTextoRegistroTipo07.append("07");

					// MATR�CULA DO IM�VEL
					arquivoTextoRegistroTipo07
							.append(Util.adicionarZerosEsquedaNumero(9,
									idImovel.toString()));

					// ANO/MES DE REFERENCIA DA CONTA
					arquivoTextoRegistroTipo07.append("DB.ATE");

					// VALOR DA CONTA
					arquivoTextoRegistroTipo07
							.append(Util.adicionarZerosEsquedaNumero(
									14,
									Util.formatarBigDecimalComPonto(contaValoresHelper
											.getValorAnterior())));

					// DATA DE VENCIMENTO
					arquivoTextoRegistroTipo07.append(Util
							.formatarDataAAAAMMDD(contaValoresHelper
									.getDataVencimentoAnterior()));

					// VALOR ACR�SCIMOS IMPONTUALIDADE
					arquivoTextoRegistroTipo07
							.append(Util.adicionarZerosEsquedaNumero(
									14,
									Util.formatarBigDecimalComPonto(contaValoresHelper
											.getValorAcrescimosAnterior())));

					arquivoTextoRegistroTipo07.append(System
							.getProperty("line.separator"));
				}

				if (countImpressao <= 1) {
					Iterator iteratorColecaoCobrancaDocumentoItem = colecaoCobrancaDocumentoItemConta
							.iterator();

					CobrancaDocumentoItem cobrancaDocumentoItem = null;

					while (iteratorColecaoCobrancaDocumentoItem.hasNext()) {
						cobrancaDocumentoItem = (CobrancaDocumentoItem) iteratorColecaoCobrancaDocumentoItem
								.next();

						quantidadeLinhas = quantidadeLinhas + 1;
						// TIPO DO REGISTRO
						arquivoTextoRegistroTipo07.append("07");

						// MATR�CULA DO IM�VEL
						arquivoTextoRegistroTipo07.append(Util
								.adicionarZerosEsquedaNumero(9,
										idImovel.toString()));

						// ANO/MES DE REFERENCIA DA CONTA
						arquivoTextoRegistroTipo07.append(cobrancaDocumentoItem
								.getContaGeral().getConta().getReferencia());

						// VALOR DA CONTA
						arquivoTextoRegistroTipo07
								.append(Util.adicionarZerosEsquedaNumero(
										14,
										Util.formatarBigDecimalComPonto(cobrancaDocumentoItem
												.getValorItemCobrado())));

						// DATA DE VENCIMENTO
						arquivoTextoRegistroTipo07.append(Util
								.formatarDataAAAAMMDD(cobrancaDocumentoItem
										.getContaGeral().getConta()
										.getDataVencimentoConta()));

						// VALOR ACR�SCIMOS IMPONTUALIDADE
						arquivoTextoRegistroTipo07
								.append(Util.adicionarZerosEsquedaNumero(
										14,
										Util.formatarBigDecimalComPonto(cobrancaDocumentoItem
												.getValorAcrescimos())));

						arquivoTextoRegistroTipo07.append(System
								.getProperty("line.separator"));

					}

				} else {
					CobrancaDocumentoItem cobrancaDocumentoItem = null;
					while (countImpressao < colecaoCobrancaDocumentoItemConta
							.size()) {
						cobrancaDocumentoItem = (CobrancaDocumentoItem) ((List) colecaoCobrancaDocumentoItemConta)
								.get(countImpressao);

						quantidadeLinhas = quantidadeLinhas + 1;
						// TIPO DO REGISTRO
						arquivoTextoRegistroTipo07.append("07");

						// MATR�CULA DO IM�VEL
						arquivoTextoRegistroTipo07.append(Util
								.adicionarZerosEsquedaNumero(9,
										idImovel.toString()));

						// ANO/MES DE REFERENCIA DA CONTA
						arquivoTextoRegistroTipo07.append(cobrancaDocumentoItem
								.getContaGeral().getConta().getReferencia());

						// VALOR DA CONTA
						arquivoTextoRegistroTipo07
								.append(Util.adicionarZerosEsquedaNumero(
										14,
										Util.formatarBigDecimalComPonto(cobrancaDocumentoItem
												.getValorItemCobrado())));

						// DATA DE VENCIMENTO
						arquivoTextoRegistroTipo07.append(Util
								.formatarDataAAAAMMDD(cobrancaDocumentoItem
										.getContaGeral().getConta()
										.getDataVencimentoConta()));

						// VALOR ACR�SCIMOS IMPONTUALIDADE
						arquivoTextoRegistroTipo07
								.append(Util.adicionarZerosEsquedaNumero(
										14,
										Util.formatarBigDecimalComPonto(cobrancaDocumentoItem
												.getValorAcrescimos())));

						arquivoTextoRegistroTipo07.append(System
								.getProperty("line.separator"));

						countImpressao++;

					}
				}
			}

		}

		retorno[0] = arquivoTextoRegistroTipo07;
		retorno[1] = quantidadeLinhas;

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0001] - Gerar Arquivo Texto - Registro Tipo 11
	 * 
	 * @author S�vio Luiz
	 * @date 25/11/2009
	 * 
	 * @param imovel
	 * @param conta
	 * @param anoMesReferencia
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	public StringBuilder gerarArquivoTextoRegistroTipo11(
			SistemaParametro sistemaParametro, Imovel imovel,
			Integer sequenciaRota, Integer anoMesFaturamento)
			throws ControladorException {

		StringBuilder arquivoTextoRegistroTipo11 = new StringBuilder();

		// TIPO DO REGISTRO
		arquivoTextoRegistroTipo11.append("11");

		// C�digo da empresa febraban
		arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(4,
				"" + sistemaParametro.getCodigoEmpresaFebraban()));

		// DATA DE REFER�NCIA DA ARRECADA��O
		Integer anoMesArrecadacao = sistemaParametro.getAnoMesArrecadacao();

		Date dataReferenciaArrecadacao = Util.criarData(
				new Integer(Util.obterUltimoDiaMes(
						Util.obterMes(anoMesArrecadacao),
						Util.obterAno(anoMesArrecadacao))),
				Util.obterMes(anoMesArrecadacao),
				Util.obterAno(anoMesArrecadacao));

		arquivoTextoRegistroTipo11.append(Util
				.formatarDataAAAAMMDD(dataReferenciaArrecadacao));

		/**
		 *  Adicionando informa��o da refer�ncia do faturamento
		 */
		arquivoTextoRegistroTipo11.append(anoMesFaturamento);

		// Telefone 0800
		String fome0800 = sistemaParametro.getNumero0800Empresa();
		arquivoTextoRegistroTipo11.append(Util.completaString(fome0800, 12));

		// CNPJ da Empresa
		if (sistemaParametro.getCnpjEmpresa() != null) {
			arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(
					14, sistemaParametro.getCnpjEmpresa()));
		} else {
			arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(
					14, ""));
		}

		// Inscri��o Estadual da Empresa
		if (sistemaParametro.getInscricaoEstadual() != null) {
			arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(
					20, sistemaParametro.getInscricaoEstadual()));
		} else {
			arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(
					20, ""));
		}

		// VALOR M�NIMO EMISS�O CONTA
		arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(14,
				Util.formatarBigDecimalComPonto(sistemaParametro
						.getValorMinimoEmissaoConta())));

		// PERCENTUAL TOLER�NCIA PARA RATEIO
		arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(4,
				Util.formatarBigDecimalComPonto(sistemaParametro
						.getPercentualToleranciaRateio())));

		// DECREMENTO M�XIMO DE CONSUMO POR ECONOMIA
		arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(6,
				"" + sistemaParametro.getDecrementoMaximoConsumoRateio()));

		// ICCREMENTO M�XIMO DE CONSUMO POR ECONOMIA
		arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(6,
				"" + sistemaParametro.getIncrementoMaximoConsumoRateio()));

		// INDICADOR TARIFA CATEGORIA
		arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(1,
				"" + sistemaParametro.getIndicadorTarifaCategoria()));

		// ENTRA NO PR�XIMA VERS�O
		// Verifica se o existe usu�rio para leiturista
		Rota rota = null;

		if (imovel != null && imovel.getRotaAlternativa() != null) {
			rota = imovel.getRotaAlternativa();
		} else if (imovel != null && imovel.getQuadra() != null) {
			rota = imovel.getQuadra().getRota();
		}
		if (rota != null && rota.getLeiturista() != null
				&& rota.getLeiturista().getUsuario() != null
				&& !rota.getLeiturista().getUsuario().equals("")) {
			Usuario usuario = rota.getLeiturista().getUsuario();
			if (usuario.getLogin() != null && !usuario.getLogin().equals("")) {
				// LOGIN LEITURISTA
				arquivoTextoRegistroTipo11.append(Util.completaString(
						usuario.getLogin(), 11));

				// SENHA LEITURISTA
				String senhaCriptografada = usuario.getSenha();
				if (senhaCriptografada != null
						&& !senhaCriptografada.equals("")) {
					arquivoTextoRegistroTipo11.append(Util.completaString(
							senhaCriptografada, 40));
				} else {
					arquivoTextoRegistroTipo11.append(Util.completaString("",
							40));
				}
			} else {
				// LOGIN LEITURISTA
				arquivoTextoRegistroTipo11.append(Util.completaString("gcom",
						11));

				// SENHA LEITURISTA
				String senhaGerada = "senha";
				String senhaCriptografada = null;
				try {
					senhaCriptografada = Criptografia
							.encriptarSenha(senhaGerada);
				} catch (ErroCriptografiaException e1) {
					throw new ControladorException("erro.criptografia.senha");
				}
				arquivoTextoRegistroTipo11.append(Util.completaString(
						senhaCriptografada, 40));
			}
		} else {
			// LOGIN LEITURISTA
			arquivoTextoRegistroTipo11.append(Util.completaString("gcom", 11));

			// SENHA LEITURISTA
			String senhaGerada = "senha";
			String senhaCriptografada = null;
			try {
				senhaCriptografada = Criptografia.encriptarSenha(senhaGerada);
			} catch (ErroCriptografiaException e1) {
				throw new ControladorException("erro.criptografia.senha");
			}
			arquivoTextoRegistroTipo11.append(Util.completaString(
					senhaCriptografada, 40));
		}

		if (rota != null) {
			// DATA AJUSTE DE LEITURA
			if (rota.getDataAjusteLeitura() != null
					&& !rota.getDataAjusteLeitura().equals("")) {
				arquivoTextoRegistroTipo11.append(Util
						.formatarDataAAAAMMDD(rota.getDataAjusteLeitura()));
			} else {
				arquivoTextoRegistroTipo11.append(Util.completaString("", 8));
			}
			// INDICADOR AJUSTE DE CONSUMO
			if (rota.getIndicadorAjusteConsumo() != null
					&& !rota.getIndicadorAjusteConsumo().equals("")) {
				arquivoTextoRegistroTipo11.append(rota
						.getIndicadorAjusteConsumo());
			} else {
				arquivoTextoRegistroTipo11.append(Util.completaString("", 1));
			}
			// INDICADOR TRANSMIS�O OFFLINE
			if (rota.getIndicadorTransmissaoOffline() != null
					&& !rota.getIndicadorTransmissaoOffline().equals("")) {
				arquivoTextoRegistroTipo11.append(rota
						.getIndicadorTransmissaoOffline());
			} else {
				arquivoTextoRegistroTipo11.append(Util.completaString("", 1));
			}
		} else {
			arquivoTextoRegistroTipo11.append(Util.completaString("", 10));
		}

		// VERS�O DO CELULAR
		if (sistemaParametro.getVersaoCelular() != null
				&& !sistemaParametro.getVersaoCelular().equals("")) {
			arquivoTextoRegistroTipo11.append(Util.completaString(
					sistemaParametro.getVersaoCelular(), 10));
		} else {
			arquivoTextoRegistroTipo11.append(Util.completaString("", 10));
		}

		// INDICADOR BLOQUEIO CONTA MOBILE
		if (sistemaParametro.getIndicadorBloqueioContaMobile() != null
				&& !sistemaParametro.getIndicadorBloqueioContaMobile().equals(
						"")) {
			arquivoTextoRegistroTipo11
					.append(Util.adicionarZerosEsquedaNumero(
							1,
							""
									+ sistemaParametro
											.getIndicadorBloqueioContaMobile()));
		} else {
			arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(
					1, ""));
		}

		// INDICADOR DE ROTA DE MARCA��O
		if (rota.getIndicadorSequencialLeitura() == null) {
			arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(
					1, "" + ConstantesSistema.NAO));
		} else {
			arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(
					1, "" + rota.getIndicadorSequencialLeitura()));
		}

		// QUANTIDADE DE DIAS DE CONSUMO
		long numeroDiasConsumoAjuste = getControladorFaturamento().obterDiferencaDiasCronogramas(
	        		sistemaParametro.getAnoMesFaturamento(), rota, FaturamentoAtividade.EFETUAR_LEITURA);
	        
	    arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(2,""+ numeroDiasConsumoAjuste ));

		// MODULO DIGITO VERIFICADOR
		Short moduloVerificador = ConstantesSistema.MODULO_VERIFICADOR_10;
		if (sistemaParametro.getNumeroModuloDigitoVerificador() != null
				&& sistemaParametro.getNumeroModuloDigitoVerificador()
						.compareTo(ConstantesSistema.MODULO_VERIFICADOR_11) == 0) {
			moduloVerificador = ConstantesSistema.MODULO_VERIFICADOR_11;
		}

		arquivoTextoRegistroTipo11.append(Util.completaString(""
				+ moduloVerificador, 2));

		// verifica se o campo n�mero de dias para bloqueio � diferente de nulo,
		// caso n�o seja a quantidade de dias ser� 30.
		int diasBloqueioCelular = 30;
		if (sistemaParametro.getNumeroDiasBloqueioCelular() != null) {
			diasBloqueioCelular = sistemaParametro
					.getNumeroDiasBloqueioCelular();
		}

		// DATA INICIO PARA BLOQUEIO
		Date dataInicioBloqueio = Util.subtrairNumeroDiasDeUmaData(new Date(),
				diasBloqueioCelular);
		arquivoTextoRegistroTipo11.append(Util
				.formatarDataAAAAMMDD(dataInicioBloqueio));

		// DATA FIM PARA BLOQUEIO
		Date dataFimBloqueio = Util.adicionarNumeroDiasDeUmaData(new Date(),
				diasBloqueioCelular);
		arquivoTextoRegistroTipo11.append(Util
				.formatarDataAAAAMMDD(dataFimBloqueio));

		/*
		 * Alteracao para enviar o codigo da rota, pois ao essa
		 * informacao � necessaria ao finalizar rotas alternativas, pois n�o �
		 * possivel localizar as rotas pelo setor original, somente pelo setor
		 * alternativo e essa informa��o n�o � gerada na rota.
		 */
		// ID DA ROTA
		arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(4,
				"" + rota.getId()));
		// CAMPO QUE INDICA SE A ROTA � DIVIDIDA
		/**
		 * Adicionando o numero sequencial da rota, caso seja uma
		 * rota dividida
		 */
		if (sequenciaRota != null) {
			arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(
					2, sequenciaRota.toString()));
		} else {
			arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(
					2, ""));
		}

		/**
		 * Pamela Gatinho - 24/01/2013 Adicionando o indicador
		 * para calcular a rota pela media
		 */
		// if (rota.getIndicadorCalculoMedia() != null) {
		// arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(2,rota.getIndicadorCalculoMedia().toString()));
		// } else {
		// arquivoTextoRegistroTipo11.append(Util.adicionarZerosEsquedaNumero(2,""));
		// }

		return arquivoTextoRegistroTipo11;
	}

	private StringBuilder gerarDadosSituacaoEspecialFaturamento(Imovel imovel,
			FaturamentoGrupo faturamentoGrupo) throws ControladorException {

		StringBuilder arquivoTextoRegistroTipo01 = new StringBuilder();

		if (imovel.getFaturamentoSituacaoTipo() != null
				&& imovel.getFaturamentoSituacaoTipo().getId() != null
				&& !imovel.getFaturamentoSituacaoTipo().getId().equals("")) {

			FiltroFaturamentoSituacaoHistorico filtroFaturamentoSituacaoHistorico = new FiltroFaturamentoSituacaoHistorico();
			filtroFaturamentoSituacaoHistorico
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoSituacaoHistorico.ID_IMOVEL,
							imovel.getId()));
			filtroFaturamentoSituacaoHistorico
					.adicionarParametro(new ParametroNulo(
							FiltroFaturamentoSituacaoHistorico.ANO_MES_FATURAMENTO_RETIRADA));
			Collection<FaturamentoSituacaoHistorico> colFiltroFaturamentoSituacaoHistorico = this
					.getControladorUtil().pesquisar(
							filtroFaturamentoSituacaoHistorico,
							FaturamentoSituacaoHistorico.class.getName());

			FaturamentoSituacaoHistorico faturamentoSituacaoHistorico = (FaturamentoSituacaoHistorico) Util
					.retonarObjetoDeColecao(colFiltroFaturamentoSituacaoHistorico);

			// Verificamos se anomesreferencia do grupo que esta sendo faturado
			// est� entre o os meses inicial e final do
			// FATURAMENTO_SITUACAO_HISTORICO
			if (faturamentoSituacaoHistorico != null
					&& faturamentoGrupo != null
					&& faturamentoGrupo.getAnoMesReferencia() >= faturamentoSituacaoHistorico
							.getAnoMesFaturamentoSituacaoInicio()
					&& faturamentoGrupo.getAnoMesReferencia() <= faturamentoSituacaoHistorico
							.getAnoMesFaturamentoSituacaoFim()) {

				Collection colecaoFaturamentoSituacaoTipo = null;

				// Pesquisa a anormalidade de leitura de faturamento
				colecaoFaturamentoSituacaoTipo = getControladorMicromedicao()
						.pesquisarFaturamentoSituacaoTipo(
								imovel.getFaturamentoSituacaoTipo());

				// Obt�m a leitura anormalidade
				FaturamentoSituacaoTipo faturamentoSituacaoTipo = getControladorMicromedicao()
						.obterFaturamentoSituacaoTipo(
								colecaoFaturamentoSituacaoTipo);

				// TIPO DA SITUA��O ESPECIAL DE FATURAMENTO
				arquivoTextoRegistroTipo01.append(Util
						.adicionarZerosEsquedaNumero(2, ""
								+ imovel.getFaturamentoSituacaoTipo().getId()));

				// ID DA LEITURA DE ANORMALIDADE DE CONSUMO SEM LEITURA
				if (faturamentoSituacaoTipo
						.getLeituraAnormalidadeConsumoSemLeitura() != null
						&& !faturamentoSituacaoTipo
								.getLeituraAnormalidadeConsumoSemLeitura()
								.equals("")) {
					arquivoTextoRegistroTipo01
							.append(Util
									.adicionarZerosEsquedaNumero(
											2,
											""
													+ faturamentoSituacaoTipo
															.getLeituraAnormalidadeConsumoSemLeitura()
															.getId()));
				} else {
					arquivoTextoRegistroTipo01.append(Util
							.completaString("", 2));

				}

				// ID DA LEITURA DE ANORMALIDADE DE CONSUMO COM LEITURA
				if (faturamentoSituacaoTipo
						.getLeituraAnormalidadeConsumoComLeitura() != null
						&& !faturamentoSituacaoTipo
								.getLeituraAnormalidadeConsumoComLeitura()
								.equals("")) {
					arquivoTextoRegistroTipo01
							.append(Util
									.adicionarZerosEsquedaNumero(
											2,
											""
													+ faturamentoSituacaoTipo
															.getLeituraAnormalidadeConsumoComLeitura()
															.getId()));
				} else {
					arquivoTextoRegistroTipo01.append(Util
							.completaString("", 2));

				}

				// ID DA LEITURA DE ANORMALIDADE DE LEITURA SEM LEITURA
				if (faturamentoSituacaoTipo
						.getLeituraAnormalidadeLeituraSemLeitura() != null
						&& !faturamentoSituacaoTipo
								.getLeituraAnormalidadeLeituraSemLeitura()
								.equals("")) {
					arquivoTextoRegistroTipo01
							.append(Util
									.adicionarZerosEsquedaNumero(
											2,
											""
													+ faturamentoSituacaoTipo
															.getLeituraAnormalidadeLeituraSemLeitura()
															.getId()));
				} else {
					arquivoTextoRegistroTipo01.append(Util
							.completaString("", 2));

				}

				// ID DA LEITURA DE ANORMALIDADE DE LEITURA COM LEITURA
				if (faturamentoSituacaoTipo
						.getLeituraAnormalidadeLeituraComLeitura() != null
						&& !faturamentoSituacaoTipo
								.getLeituraAnormalidadeLeituraComLeitura()
								.equals("")) {
					arquivoTextoRegistroTipo01
							.append(Util
									.adicionarZerosEsquedaNumero(
											2,
											""
													+ faturamentoSituacaoTipo
															.getLeituraAnormalidadeLeituraComLeitura()
															.getId()));
				} else {
					arquivoTextoRegistroTipo01.append(Util
							.completaString("", 2));

				}

				// CONSUMO �GUA MEDIDO DO HIST�RICO DE FATURAMENTO
				if (faturamentoSituacaoHistorico.getNumeroConsumoAguaMedido() != null
						&& !faturamentoSituacaoHistorico
								.getNumeroConsumoAguaMedido().equals("")) {
					arquivoTextoRegistroTipo01
							.append(Util.adicionarZerosEsquedaNumero(
									6,
									""
											+ faturamentoSituacaoHistorico
													.getNumeroConsumoAguaMedido()));
				} else {
					arquivoTextoRegistroTipo01.append(Util
							.completaString("", 6));

				}

				// CONSUMO �GUA N�O MEDIDO DO HIST�RICO DE FATURAMENTO
				if (faturamentoSituacaoHistorico
						.getNumeroConsumoAguaNaoMedido() != null
						&& !faturamentoSituacaoHistorico
								.getNumeroConsumoAguaNaoMedido().equals("")) {
					arquivoTextoRegistroTipo01
							.append(Util.adicionarZerosEsquedaNumero(
									6,
									""
											+ faturamentoSituacaoHistorico
													.getNumeroConsumoAguaNaoMedido()));
				} else {
					arquivoTextoRegistroTipo01.append(Util
							.completaString("", 6));

				}

				// VOLUME ESGOTO MEDIDO DO HIST�RICO DE FATURAMENTO
				if (faturamentoSituacaoHistorico.getNumeroVolumeEsgotoMedido() != null
						&& !faturamentoSituacaoHistorico
								.getNumeroVolumeEsgotoMedido().equals("")) {
					arquivoTextoRegistroTipo01
							.append(Util.adicionarZerosEsquedaNumero(
									6,
									""
											+ faturamentoSituacaoHistorico
													.getNumeroVolumeEsgotoMedido()));
				} else {
					arquivoTextoRegistroTipo01.append(Util
							.completaString("", 6));

				}

				// VOLUME ESGOTO N�O MEDIDO DO HIST�RICO DE FATURAMENTO
				if (faturamentoSituacaoHistorico
						.getNumeroVolumeEsgotoNaoMedido() != null
						&& !faturamentoSituacaoHistorico
								.getNumeroVolumeEsgotoNaoMedido().equals("")) {
					arquivoTextoRegistroTipo01
							.append(Util.adicionarZerosEsquedaNumero(
									6,
									""
											+ faturamentoSituacaoHistorico
													.getNumeroVolumeEsgotoNaoMedido()));
				} else {
					arquivoTextoRegistroTipo01.append(Util
							.completaString("", 6));

				}

				// INDICADOR VALIDA �GUA
				arquivoTextoRegistroTipo01.append(Util.completaString(""
						+ imovel.getFaturamentoSituacaoTipo()
								.getIndicadorValidoAgua(), 1));

				// INDICADOR VALIDA ESGOTO
				arquivoTextoRegistroTipo01.append(Util.completaString(""
						+ imovel.getFaturamentoSituacaoTipo()
								.getIndicadorValidoEsgoto(), 1));

			} else {
				// TIPO DA SITUA��O ESPECIAL DE FATURAMENTO
				arquivoTextoRegistroTipo01.append(Util.completaString("", 2));

				// ID DA LEITURA DE ANORMALIDADE DE CONSUMO SEM LEITURA
				arquivoTextoRegistroTipo01.append(Util.completaString("", 2));

				// ID DA LEITURA DE ANORMALIDADE DE CONSUMO COM LEITURA
				arquivoTextoRegistroTipo01.append(Util.completaString("", 2));

				// ID DA LEITURA DE ANORMALIDADE DE LEITURA SEM LEITURA
				arquivoTextoRegistroTipo01.append(Util.completaString("", 2));

				// ID DA LEITURA DE ANORMALIDADE DE LEITURA COM LEITURA
				arquivoTextoRegistroTipo01.append(Util.completaString("", 2));

				// CONSUMO �GUA MEDIDO DO HIST�RICO DE FATURAMENTO
				arquivoTextoRegistroTipo01.append(Util.completaString("", 6));

				// CONSUMO �GUA N�O MEDIDO DO HIST�RICO DE FATURAMENTO
				arquivoTextoRegistroTipo01.append(Util.completaString("", 6));

				// VOLUME ESGOTO MEDIDO DO HIST�RICO DE FATURAMENTO
				arquivoTextoRegistroTipo01.append(Util.completaString("", 6));

				// VOLUME ESGOTO N�O MEDIDO DO HIST�RICO DE FATURAMENTO
				arquivoTextoRegistroTipo01.append(Util.completaString("", 6));

				// INDICADOR VALIDA �GUA
				arquivoTextoRegistroTipo01.append(Util.completaString("", 1));

				// INDICADOR VALIDA ESGOTO
				arquivoTextoRegistroTipo01.append(Util.completaString("", 1));
			}
		} else {
			// TIPO DA SITUA��O ESPECIAL DE FATURAMENTO
			arquivoTextoRegistroTipo01.append(Util.completaString("", 2));

			// ID DA LEITURA DE ANORMALIDADE DE CONSUMO SEM LEITURA
			arquivoTextoRegistroTipo01.append(Util.completaString("", 2));

			// ID DA LEITURA DE ANORMALIDADE DE CONSUMO COM LEITURA
			arquivoTextoRegistroTipo01.append(Util.completaString("", 2));

			// ID DA LEITURA DE ANORMALIDADE DE LEITURA SEM LEITURA
			arquivoTextoRegistroTipo01.append(Util.completaString("", 2));

			// ID DA LEITURA DE ANORMALIDADE DE LEITURA COM LEITURA
			arquivoTextoRegistroTipo01.append(Util.completaString("", 2));

			// CONSUMO �GUA MEDIDO DO HIST�RICO DE FATURAMENTO
			arquivoTextoRegistroTipo01.append(Util.completaString("", 6));

			// CONSUMO �GUA N�O MEDIDO DO HIST�RICO DE FATURAMENTO
			arquivoTextoRegistroTipo01.append(Util.completaString("", 6));

			// VOLUME ESGOTO MEDIDO DO HIST�RICO DE FATURAMENTO
			arquivoTextoRegistroTipo01.append(Util.completaString("", 6));

			// VOLUME ESGOTO N�O MEDIDO DO HIST�RICO DE FATURAMENTO
			arquivoTextoRegistroTipo01.append(Util.completaString("", 6));

			// INDICADOR VALIDA �GUA
			arquivoTextoRegistroTipo01.append(Util.completaString("", 1));

			// INDICADOR VALIDA ESGOTO
			arquivoTextoRegistroTipo01.append(Util.completaString("", 1));
		}

		return arquivoTextoRegistroTipo01;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0001] - Gerar Arquivo Texto - Registro Tipo 12
	 * 
	 * @author S�vio Luiz
	 * @date 18/12/2009
	 * 
	 * @param imovel
	 * @param conta
	 * @param anoMesReferencia
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	public Object[] gerarArquivoTextoRegistroTipo12()
			throws ControladorException {

		StringBuilder arquivoTextoRegistroTipo12 = new StringBuilder();

		int quantidadeLinhas = 0;

		Object[] retorno = new Object[2];

		FiltroConsumoAnormalidadeAcao filtroConsumoAnormalidadeAcao = new FiltroConsumoAnormalidadeAcao();
		filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
				FiltroConsumoAnormalidadeAcao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoConsumoAnormalidadeAcao = getControladorUtil()
				.pesquisar(filtroConsumoAnormalidadeAcao,
						ConsumoAnormalidadeAcao.class.getName());

		if (colecaoConsumoAnormalidadeAcao != null
				&& !colecaoConsumoAnormalidadeAcao.isEmpty()) {

			int count = colecaoConsumoAnormalidadeAcao.size();

			Iterator itConsumoAnormalidadeAcao = colecaoConsumoAnormalidadeAcao
					.iterator();
			while (itConsumoAnormalidadeAcao.hasNext()) {

				quantidadeLinhas = quantidadeLinhas + 1;

				count--;

				ConsumoAnormalidadeAcao consumoAnormalidadeAcao = (ConsumoAnormalidadeAcao) itConsumoAnormalidadeAcao
						.next();

				// TIPO DO REGISTRO
				arquivoTextoRegistroTipo12.append("12");

				// Id do consumo Anormalidade
				if (consumoAnormalidadeAcao.getConsumoAnormalidade() != null
						&& consumoAnormalidadeAcao.getConsumoAnormalidade()
								.getId() != null) {
					arquivoTextoRegistroTipo12.append(Util
							.adicionarZerosEsquedaNumero(2, ""
									+ consumoAnormalidadeAcao
											.getConsumoAnormalidade().getId()));
				} else {
					arquivoTextoRegistroTipo12.append(Util
							.adicionarZerosEsquedaNumero(2, ""));
				}

				// Id da categoria
				if (consumoAnormalidadeAcao.getCategoria() != null
						&& consumoAnormalidadeAcao.getCategoria().getId() != null
						&& !consumoAnormalidadeAcao.getCategoria().getId()
								.equals("")) {
					arquivoTextoRegistroTipo12.append(Util
							.adicionarZerosEsquedaNumero(2, ""
									+ consumoAnormalidadeAcao.getCategoria()
											.getId()));
				} else {
					arquivoTextoRegistroTipo12.append(Util
							.completaString("", 2));
				}

				// Id do im�vel perfil
				if (consumoAnormalidadeAcao.getImovelPerfil() != null
						&& consumoAnormalidadeAcao.getImovelPerfil().getId() != null
						&& !consumoAnormalidadeAcao.getImovelPerfil().getId()
								.equals("")) {
					arquivoTextoRegistroTipo12.append(Util
							.adicionarZerosEsquedaNumero(2, ""
									+ consumoAnormalidadeAcao.getImovelPerfil()
											.getId()));
				} else {
					arquivoTextoRegistroTipo12.append(Util
							.completaString("", 2));
				}

				// Id da leitura anormalidade consumo do primeiro m�s
				if (consumoAnormalidadeAcao.getLeituraAnormalidadeConsumoMes1() != null
						&& consumoAnormalidadeAcao
								.getLeituraAnormalidadeConsumoMes1().getId() != null) {
					arquivoTextoRegistroTipo12
							.append(Util
									.adicionarZerosEsquedaNumero(
											2,
											""
													+ consumoAnormalidadeAcao
															.getLeituraAnormalidadeConsumoMes1()
															.getId()));
				} else {
					arquivoTextoRegistroTipo12.append(Util
							.adicionarZerosEsquedaNumero(2, ""));
				}

				// Id da leitura anormalidade consumo do segundo m�s
				if (consumoAnormalidadeAcao.getLeituraAnormalidadeConsumoMes2() != null
						&& consumoAnormalidadeAcao
								.getLeituraAnormalidadeConsumoMes2().getId() != null) {
					arquivoTextoRegistroTipo12
							.append(Util
									.adicionarZerosEsquedaNumero(
											2,
											""
													+ consumoAnormalidadeAcao
															.getLeituraAnormalidadeConsumoMes2()
															.getId()));
				} else {
					arquivoTextoRegistroTipo12.append(Util
							.adicionarZerosEsquedaNumero(2, ""));
				}

				// Id da leitura anormalidade consumo do terceiro m�s
				if (consumoAnormalidadeAcao.getLeituraAnormalidadeConsumoMes3() != null
						&& consumoAnormalidadeAcao
								.getLeituraAnormalidadeConsumoMes3().getId() != null) {
					arquivoTextoRegistroTipo12
							.append(Util
									.adicionarZerosEsquedaNumero(
											2,
											""
													+ consumoAnormalidadeAcao
															.getLeituraAnormalidadeConsumoMes3()
															.getId()));
				} else {
					arquivoTextoRegistroTipo12.append(Util
							.adicionarZerosEsquedaNumero(2, ""));
				}

				// Fator de consumo do primeiro m�s
				if (consumoAnormalidadeAcao.getNumerofatorConsumoMes1() != null
						&& !consumoAnormalidadeAcao.getNumerofatorConsumoMes1()
								.equals("")) {
					arquivoTextoRegistroTipo12
							.append(Util.adicionarZerosEsquedaNumero(
									4,
									""
											+ consumoAnormalidadeAcao
													.getNumerofatorConsumoMes1()));
				} else {
					arquivoTextoRegistroTipo12.append(Util
							.adicionarZerosEsquedaNumero(4, ""));
				}

				// Fator de consumo do segundo m�s
				if (consumoAnormalidadeAcao.getNumerofatorConsumoMes2() != null
						&& !consumoAnormalidadeAcao.getNumerofatorConsumoMes2()
								.equals("")) {
					arquivoTextoRegistroTipo12
							.append(Util.adicionarZerosEsquedaNumero(
									4,
									""
											+ consumoAnormalidadeAcao
													.getNumerofatorConsumoMes2()));
				} else {
					arquivoTextoRegistroTipo12.append(Util
							.adicionarZerosEsquedaNumero(4, ""));
				}

				// Fator de consumo do terceiro m�s
				if (consumoAnormalidadeAcao.getNumerofatorConsumoMes3() != null
						&& !consumoAnormalidadeAcao.getNumerofatorConsumoMes3()
								.equals("")) {
					arquivoTextoRegistroTipo12
							.append(Util.adicionarZerosEsquedaNumero(
									4,
									""
											+ consumoAnormalidadeAcao
													.getNumerofatorConsumoMes3()));
				} else {
					arquivoTextoRegistroTipo12.append(Util
							.adicionarZerosEsquedaNumero(4, ""));
				}

				// Mensagem da conta m�s 1
				if (consumoAnormalidadeAcao.getDescricaoContaMensagemMes1() != null
						&& !consumoAnormalidadeAcao
								.getDescricaoContaMensagemMes1().equals("")) {
					arquivoTextoRegistroTipo12.append(Util.completaString(
							consumoAnormalidadeAcao
									.getDescricaoContaMensagemMes1(), 120));
				} else {
					arquivoTextoRegistroTipo12.append(Util.completaString("",
							120));
				}

				// Mensagem da conta m�s 2
				if (consumoAnormalidadeAcao.getDescricaoContaMensagemMes2() != null
						&& !consumoAnormalidadeAcao
								.getDescricaoContaMensagemMes2().equals("")) {
					arquivoTextoRegistroTipo12.append(Util.completaString(
							consumoAnormalidadeAcao
									.getDescricaoContaMensagemMes2(), 120));
				} else {
					arquivoTextoRegistroTipo12.append(Util.completaString("",
							120));
				}

				// Mensagem da conta m�s 3
				if (consumoAnormalidadeAcao.getDescricaoContaMensagemMes3() != null
						&& !consumoAnormalidadeAcao
								.getDescricaoContaMensagemMes3().equals("")) {
					arquivoTextoRegistroTipo12.append(Util.completaString(
							consumoAnormalidadeAcao
									.getDescricaoContaMensagemMes3(), 120));
				} else {
					arquivoTextoRegistroTipo12.append(Util.completaString("",
							120));
				}

				if (count != 0) {
					arquivoTextoRegistroTipo12.append(System
							.getProperty("line.separator"));
				}

			}

		}

		retorno[0] = arquivoTextoRegistroTipo12;
		retorno[1] = quantidadeLinhas;

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0001] - Gerar Arquivo Texto - Registro Tipo 13
	 * 
	 * @author S�vio Luiz
	 * @date 18/12/2009
	 * 
	 * @param imovel
	 * @param conta
	 * @param anoMesReferencia
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	public Object[] gerarArquivoTextoRegistroTipo13()
			throws ControladorException {

		StringBuilder arquivoTextoRegistroTipo13 = new StringBuilder();

		int quantidadeLinhas = 0;

		Object[] retorno = new Object[2];

		FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade();
		filtroConsumoAnormalidade.adicionarParametro(new ParametroSimples(
				FiltroConsumoAnormalidade.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroConsumoAnormalidade.adicionarParametro(new ParametroNaoNulo(
				FiltroConsumoAnormalidade.MENSAGEM_CONTA));
		Collection colecaoConsumoAnormalidade = getControladorUtil().pesquisar(
				filtroConsumoAnormalidade, ConsumoAnormalidade.class.getName());

		if (colecaoConsumoAnormalidade != null
				&& !colecaoConsumoAnormalidade.isEmpty()) {

			int count = colecaoConsumoAnormalidade.size();

			Iterator itConsumoAnormalidade = colecaoConsumoAnormalidade
					.iterator();
			while (itConsumoAnormalidade.hasNext()) {

				quantidadeLinhas = quantidadeLinhas + 1;

				count--;

				ConsumoAnormalidade consumoAnormalidade = (ConsumoAnormalidade) itConsumoAnormalidade
						.next();

				// TIPO DO REGISTRO
				arquivoTextoRegistroTipo13.append("13");

				// Id do consumo Anormalidade
				if (consumoAnormalidade.getId() != null
						&& !consumoAnormalidade.getId().equals("")) {
					arquivoTextoRegistroTipo13.append(Util
							.adicionarZerosEsquedaNumero(2, ""
									+ consumoAnormalidade.getId()));
				} else {
					arquivoTextoRegistroTipo13.append(Util
							.adicionarZerosEsquedaNumero(2, ""));
				}

				// mensagem consumo Anormalidade
				if (consumoAnormalidade.getMensagemConta() != null
						&& !consumoAnormalidade.getMensagemConta().equals("")) {
					arquivoTextoRegistroTipo13.append(Util.completaString(""
							+ consumoAnormalidade.getMensagemConta(), 120));
				} else {
					arquivoTextoRegistroTipo13.append(Util.completaString("",
							120));
				}

				if (count != 0) {
					arquivoTextoRegistroTipo13.append(System
							.getProperty("line.separator"));
				}

			}

		}

		retorno[0] = arquivoTextoRegistroTipo13;
		retorno[1] = quantidadeLinhas;

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0001] - Gerar Arquivo Texto - Registro Tipo 14 (Leitura Anormalidade)
	 * 
	 * @author S�vio Luiz
	 * @date 18/12/2009
	 * 
	 * @param imovel
	 * @param conta
	 * @param anoMesReferencia
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	public Object[] gerarArquivoTextoRegistroTipo14()
			throws ControladorException {

		StringBuilder arquivoTextoRegistroTipo14 = new StringBuilder();

		int quantidadeLinhas = 0;

		Object[] retorno = new Object[2];

		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade(
				FiltroLeituraAnormalidade.ID);

		filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
				FiltroLeituraAnormalidade.INDICADOR_IMPRESSAO_SIMULTANEA,
				ConstantesSistema.SIM));

		Collection colecaoLeituraAnormalidade = getControladorUtil().pesquisar(
				filtroLeituraAnormalidade, LeituraAnormalidade.class.getName());

		if (colecaoLeituraAnormalidade != null
				&& !colecaoLeituraAnormalidade.isEmpty()) {

			int count = colecaoLeituraAnormalidade.size();

			Iterator itLeituraAnormalidade = colecaoLeituraAnormalidade
					.iterator();
			while (itLeituraAnormalidade.hasNext()) {

				quantidadeLinhas = quantidadeLinhas + 1;

				count--;

				LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade) itLeituraAnormalidade
						.next();

				// TIPO DO REGISTRO
				arquivoTextoRegistroTipo14.append("14");

				// Id da leitura Anormalidade
				if (leituraAnormalidade.getId() != null
						&& !leituraAnormalidade.getId().equals("")) {
					arquivoTextoRegistroTipo14.append(Util
							.adicionarZerosEsquedaNumero(3, ""
									+ leituraAnormalidade.getId()));
				} else {
					arquivoTextoRegistroTipo14.append(Util
							.adicionarZerosEsquedaNumero(3, ""));
				}

				// descri��o da leitura Anormalidade
				if (leituraAnormalidade.getDescricao() != null
						&& !leituraAnormalidade.getDescricao().equals("")) {
					arquivoTextoRegistroTipo14.append(Util.completaString(""
							+ leituraAnormalidade.getDescricao(), 25));
				} else {
					arquivoTextoRegistroTipo14.append(Util.completaString("",
							25));
				}

				// INDICADOR ACEITA LEITURA
				if (leituraAnormalidade.getIndicadorLeitura() != null
						&& !leituraAnormalidade.getIndicadorLeitura()
								.equals("")) {
					arquivoTextoRegistroTipo14.append(Util.completaString(""
							+ leituraAnormalidade.getIndicadorLeitura(), 1));
				} else {
					arquivoTextoRegistroTipo14.append(Util
							.completaString("", 1));
				}

				// ID Consumo a cobrar com leitura
				if (leituraAnormalidade
						.getLeituraAnormalidadeConsumoComleitura() != null
						&& !leituraAnormalidade
								.getLeituraAnormalidadeConsumoComleitura()
								.equals("")) {
					arquivoTextoRegistroTipo14.append(Util.completaString(""
							+ leituraAnormalidade
									.getLeituraAnormalidadeConsumoComleitura()
									.getId(), 2));
				} else {
					arquivoTextoRegistroTipo14.append(Util
							.completaString("", 2));
				}

				// ID Consumo a cobrar sem leitura
				if (leituraAnormalidade
						.getLeituraAnormalidadeConsumoSemleitura() != null
						&& !leituraAnormalidade
								.getLeituraAnormalidadeConsumoSemleitura()
								.equals("")) {
					arquivoTextoRegistroTipo14.append(Util.completaString(""
							+ leituraAnormalidade
									.getLeituraAnormalidadeConsumoSemleitura()
									.getId(), 2));
				} else {
					arquivoTextoRegistroTipo14.append(Util
							.completaString("", 2));
				}

				// ID Leitura anormalidade leitura com leitura
				if (leituraAnormalidade
						.getLeituraAnormalidadeLeituraComleitura() != null
						&& !leituraAnormalidade
								.getLeituraAnormalidadeLeituraComleitura()
								.equals("")) {
					arquivoTextoRegistroTipo14.append(Util.completaString(""
							+ leituraAnormalidade
									.getLeituraAnormalidadeLeituraComleitura()
									.getId(), 2));
				} else {
					arquivoTextoRegistroTipo14.append(Util
							.completaString("", 2));
				}

				// ID Leitura anormalidade leitura sem leitura
				if (leituraAnormalidade
						.getLeituraAnormalidadeLeituraSemleitura() != null
						&& !leituraAnormalidade
								.getLeituraAnormalidadeLeituraSemleitura()
								.equals("")) {
					arquivoTextoRegistroTipo14.append(Util.completaString(""
							+ leituraAnormalidade
									.getLeituraAnormalidadeLeituraSemleitura()
									.getId(), 2));
				} else {
					arquivoTextoRegistroTipo14.append(Util
							.completaString("", 2));
				}

				// Indicador de uso
				if (leituraAnormalidade.getIndicadorUso() != null
						&& !leituraAnormalidade.getIndicadorUso().equals("")) {
					arquivoTextoRegistroTipo14.append(Util.completaString(""
							+ leituraAnormalidade.getIndicadorUso(), 1));
				} else {
					arquivoTextoRegistroTipo14.append(Util
							.completaString("", 1));
				}

				// numero fator sem leitura
				if (leituraAnormalidade.getNumeroFatorSemLeitura() != null
						&& !leituraAnormalidade.getNumeroFatorSemLeitura()
								.equals("")) {
					arquivoTextoRegistroTipo14
							.append(Util.completaString(
									""
											+ leituraAnormalidade
													.getNumeroFatorSemLeitura(),
									4));
				} else {
					arquivoTextoRegistroTipo14.append(Util
							.completaString("", 4));
				}

				// numero fator com leitura
				if (leituraAnormalidade.getNumeroFatorComLeitura() != null
						&& !leituraAnormalidade.getNumeroFatorComLeitura()
								.equals("")) {
					arquivoTextoRegistroTipo14
							.append(Util.completaString(
									""
											+ leituraAnormalidade
													.getNumeroFatorComLeitura(),
									4));
				} else {
					arquivoTextoRegistroTipo14.append(Util
							.completaString("", 4));
				}

				if (count != 0) {
					arquivoTextoRegistroTipo14.append(System
							.getProperty("line.separator"));
				}

			}

		}

		retorno[0] = arquivoTextoRegistroTipo14;
		retorno[1] = quantidadeLinhas;

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto Dividido para Faturamento
	 * 
	 * @author Hugo Leonardo
	 * @date 01/06/2010
	 * 
	 * @param anoMesFaturamento
	 * @param faturamentoGrupo
	 * @param rota
	 * @param imovel
	 * @param qtdImoveis
	 * @param arquivoTexto
	 * @throws ControladorException
	 */
	public ArquivoTextoRoteiroEmpresaDivisao inserirArquivoTextoRoteiroEmpresaDivisao(
			Integer anoMesFaturamento, FaturamentoGrupo faturamentoGrupo,
			Rota rota, Imovel imovel, Integer qtdImoveis,
			StringBuilder arquivoTexto) throws ControladorException {

		ArquivoTextoRoteiroEmpresaDivisao arquivoTextoRoteiroEmpresaDivisao = new ArquivoTextoRoteiroEmpresaDivisao();

		// QUANTIDADE DE IM�VEIS
		arquivoTextoRoteiroEmpresaDivisao.setQuantidadeImovel(qtdImoveis);

		// NOME DO ARQUIVO

		// [FS0006] - Nome do arquivo texto
		String nomeArquivoTexto = "G"
				+ Util.adicionarZerosEsquedaNumero(3, faturamentoGrupo.getId()
						+ "")
				+ Util.adicionarZerosEsquedaNumero(3, imovel.getLocalidade()
						.getId() + "")
				+ Util.adicionarZerosEsquedaNumero(3, imovel
						.getSetorComercial().getCodigo() + "")
				+ Util.adicionarZerosEsquedaNumero(4, rota.getCodigo() + "")
				+ Util.adicionarZerosEsquedaNumero(6, anoMesFaturamento + "");

		arquivoTextoRoteiroEmpresaDivisao.setNomeArquivo(nomeArquivoTexto
				+ ".gz");

		// INFORMA��ES LEITURISTA
		if (rota.getLeiturista() != null) {
			arquivoTextoRoteiroEmpresaDivisao.setLeiturista(rota
					.getLeiturista());
			arquivoTextoRoteiroEmpresaDivisao.setNumeroImei(rota
					.getLeiturista().getNumeroImei());

		}

		// ARQUIVO TEMPOR�RIO GERADO PARA ROTA
		ByteArrayOutputStream baosArquivoZip = new ByteArrayOutputStream();

		GZIPOutputStream zos = null;
		BufferedWriter out = null;

		try {

			// arquivoTexto =
			// new StringBuilder( Util.reencodeString( arquivoTexto.toString(),
			// "UTF-8" ) );

			// Convertemos o StringBuilder em um vetor de array
			// arquivoTextoByte =
			// IoUtil.transformarObjetoParaBytes(arquivoTexto);

			File compactado = new File(nomeArquivoTexto + ".tar.gz");

			zos = new GZIPOutputStream(new FileOutputStream(compactado));
			File leitura = new File(nomeArquivoTexto + ".txt");

			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(leitura.getAbsolutePath())));
			out.write(arquivoTexto.toString());
			out.flush();
			ZipUtil.adicionarArquivo(zos, leitura);

			zos.close();

			FileInputStream inputStream = new FileInputStream(compactado);

			// Escrevemos aos poucos
			int INPUT_BUFFER_SIZE = 1024;
			byte[] temp = new byte[INPUT_BUFFER_SIZE];
			int numBytesRead = 0;

			while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
				baosArquivoZip.write(temp, 0, numBytesRead);
			}

			arquivoTextoRoteiroEmpresaDivisao.setArquivoTexto(baosArquivoZip
					.toByteArray());

			// Fechamos o inputStream
			inputStream.close();
			baosArquivoZip.close();

			inputStream = null;
			compactado.delete();
			leitura.delete();
		} catch (IOException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		// SITUACAO_TRANSMISSAO_LEITURA
		SituacaoTransmissaoLeitura situacaoTransmissaoLeitura = new SituacaoTransmissaoLeitura();
		situacaoTransmissaoLeitura.setId(SituacaoTransmissaoLeitura.DISPONIVEL);

		arquivoTextoRoteiroEmpresaDivisao
				.setSituacaoTransmissaoLeitura(situacaoTransmissaoLeitura);

		// ULTIMA ALTERACAO
		arquivoTextoRoteiroEmpresaDivisao.setUltimaAlteracao(new Date());

		// INSERINDO NA BASE
		// this.getControladorUtil().inserir(arquivoTextoRoteiroEmpresaDivisao);

		return arquivoTextoRoteiroEmpresaDivisao;

	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 30/08/2011
	 * 
	 * @param idImovelMacro
	 * @param anoMesReferencia
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMovimentoContaPrefaturadaArquivoTextoFaturamento(
			Integer idImovelMacro, Integer anoMesReferencia)
			throws ControladorException {

		Integer qtdMovimentoContaPrefaturada = null;

		try {

			qtdMovimentoContaPrefaturada = repositorioFaturamento
					.pesquisarMovimentoContaPrefaturadaArquivoTextoFaturamento(
							idImovelMacro, anoMesReferencia);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		return qtdMovimentoContaPrefaturada;
	}
}
