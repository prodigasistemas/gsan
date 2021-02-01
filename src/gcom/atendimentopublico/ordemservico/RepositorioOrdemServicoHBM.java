package gcom.atendimentopublico.ordemservico;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.atendimentopublico.ligacaoagua.SupressaoTipo;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.bean.DadosAtualizacaoOSParaInstalacaoHidrometroHelper;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSTipoPavimentoHelper;
import gcom.atendimentopublico.ordemservico.bean.OrdemRepavimentacaoProcessoAceiteHelper;
import gcom.atendimentopublico.ordemservico.bean.PesquisarOrdemServicoHelper;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.projeto.Projeto;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.cobranca.CobrancaDocumento;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.debito.DebitoTipo;
import gcom.micromedicao.Rota;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.operacional.DistritoOperacional;
import gcom.relatorio.atendimentopublico.bean.ImovelEmissaoOrdensSeletivasHelper;
import gcom.relatorio.atendimentopublico.ordemservico.FiltrarBoletimCustoPavimentoHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.CollectionUtil;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;

public class RepositorioOrdemServicoHBM implements IRepositorioOrdemServico {

	private static IRepositorioOrdemServico instancia;

	/**
	 * Construtor da classe RepositorioAcessoHBM
	 */

	private RepositorioOrdemServicoHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioOrdemServico getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioOrdemServicoHBM();
		}

		return instancia;
	}

	/**
	 * [UC0450] Pesquisar Ordem de Servico
	 * 
	 * [SB001] Selecionar Ordem de Servico por Situao [SB002] Selecionar Ordem
	 * de Servico por Situao da Programao [SB003] Selecionar Ordem de
	 * Servico por Matricula do Imovel [SB004] Selecionar Ordem de Servico por
	 * Codigo do Cliente [SB005] Selecionar Ordem de Servico por Unidade
	 * Superior [SB006] Selecionar Ordem de Servico por Municpio [SB007]
	 * Selecionar Ordem de Servico por Bairro [SB008] Selecionar Ordem de
	 * Servico por Bairro Logradouro [SB009] Selecionar Ordem de Servico por
	 * Origem
	 * 
	 * @author Rafael Pinto, Vivianne Sousa
	 * @date 18/08/2006, 09/12/2008
	 * 
	 * @param PesquisarOrdemServicoHelper
	 * 
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Collection<OrdemServico> pesquisarOrdemServico(
			PesquisarOrdemServicoHelper filtro) throws ErroRepositorioException {

		Collection<OrdemServico> retorno = null;
		Collection<Object[]> retornoConsulta = null;

		Integer numeroRA = filtro.getNumeroRA();
		Integer numeroOS = filtro.getNumeroOS();
		Integer documentoCobranca = filtro.getDocumentoCobranca();
		short situacaoOrdemServico = filtro.getSituacaoOrdemServico();
		short situacaoProgramacao = filtro.getSituacaoProgramacao();
		Integer[] tipoServicos = filtro.getTipoServicos();

		Integer matriculaImovel = filtro.getMatriculaImovel();

		Integer unidadeGeracao = filtro.getUnidadeGeracao();
		Integer unidadeAtual = filtro.getUnidadeAtual();

		Collection<Integer> idsUnidadesAtuais = filtro.getIdsUnidadesAtuais();

		Collection<Integer> idsPerfilImovel = filtro.getColecaoPerfilImovel();

		Set colecaoIdsOS = filtro.getColecaoIdsOS();

		Date dataAtendimentoInicial = filtro.getDataAtendimentoInicial();
		Date dataAtendimentoFinal = filtro.getDataAtendimentoFinal();

		Date dataGeracaoInicial = filtro.getDataGeracaoInicial();
		Date dataGeracaoFinal = filtro.getDataGeracaoFinal();

		Date dataProgramacaoInicial = filtro.getDataProgramacaoInicial();
		Date dataProgramacaoFinal = filtro.getDataProgramacaoFinal();

		Date dataEncerramentoInicial = filtro.getDataEncerramentoInicial();
		Date dataEncerramentoFinal = filtro.getDataEncerramentoFinal();

		Integer municipio = filtro.getMunicipio();
		Integer bairro = filtro.getBairro();
		Integer areaBairro = filtro.getAreaBairro();
		Integer logradouro = filtro.getLogradouro();

		Short indicadorTerceirizado = filtro.getIndicadorTerceirizado();
		Short indicadorPavimento = filtro.getIndicadorPavimento();
		Short indicadorVistoria = filtro.getIndicadorVistoria();
		String origemOrdemServico = filtro.getOrigemOrdemServico() != null ? filtro
				.getOrigemOrdemServico() : "";

		Integer idProjeto = filtro.getIdProjeto();

		int numeroPagina = filtro.getNumeroPaginasPesquisa();

		if (dataProgramacaoInicial != null || dataProgramacaoFinal != null
				&& situacaoProgramacao != ConstantesSistema.SIM.shortValue()) {

			situacaoProgramacao = ConstantesSistema.SIM.shortValue();
		}

		Session session = HibernateUtil.getSession();

		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		try {

			consulta = "SELECT DISTINCT os.orse_id as idOS, " // 0
					+ "servicotipo.svtp_id as idServicoTipo, "// 1
					+ "servicotipo.svtp_dsservicotipo as descServicoTipo, "// 2
					+ "imovel.imov_id as idImovel, " // 3
					+ "ra.rgat_id as idRA, " // 4
					+ "cobra.cbdo_id as idCobrancaDocumento, " // 5
					+ "os.orse_tmgeracao as dataGeracao, "// 6
					+ "os.orse_tmencerramento as dataEncerramento, "// 7
					+ "os.orse_tmemissao as dataEmissao, " // 8
					+ "os.orse_cdsituacao as codigoSituacao, " // 9
					+ "os.unid_idatual as unidadeAtual, " // 10
					+ "servicoTipoPrioridadeAtual.stpr_id as idServicoTipoPrioridade, " // 11
					+ "servicoTipoPrioridadeAtual.stpr_nnprazoexecucaofim as prazoExecucaoFim, " // 12
					+ "imovel_perfil.iper_id as idPerfilImovel, " // 13
					+ "imovel_perfil.iper_dsimovelperfil as perfilImovel " // 14

					+ " FROM atendimentopublico.ordem_servico_unidade osUnidade "
					+ " INNER JOIN atendimentopublico.atendimento_relacao_tipo art ON osUnidade.attp_id = art.attp_id "
					+ " INNER JOIN atendimentopublico.ordem_servico os ON osUnidade.orse_id = os.orse_id "
					+ " INNER JOIN atendimentopublico.servico_tipo servicotipo ON os.svtp_id = servicotipo.svtp_id "
					+ " INNER JOIN atendimentopublico.servico_tipo_prioridade servicoTipoPrioridadeAtual "
					+ " ON os.stpr_idatual = servicoTipoPrioridadeAtual.stpr_id "
					+ " LEFT JOIN atendimentopublico.registro_atendimento ra ON os.rgat_id = ra.rgat_id "
					+ " LEFT JOIN cobranca.cobranca_documento cobra ON os.cbdo_id = cobra.cbdo_id "
					+ " LEFT JOIN cadastro.imovel imovel ON os.imov_id = imovel.imov_id "
					+ " LEFT JOIN cadastro.imovel_perfil imovel_perfil on (imovel.iper_id = imovel_perfil.iper_id) and (imovel_perfil.iper_icuso = 1) ";

			if (unidadeGeracao != null) {

				consulta = consulta
						+ " INNER JOIN cadastro.unidade_organizacional unid ON osUnidade.unid_id = unid.unid_id ";

			}

			if (areaBairro != null || bairro != null || municipio != null
					|| logradouro != null) {
				consulta = consulta

						+ " LEFT JOIN cadastro.logradouro_bairro imovlogrbair ON imovel.lgbr_id = imovlogrbair.lgbr_id "
						+ " LEFT JOIN cadastro.logradouro imovlogr ON imovlogrbair.logr_id = imovlogr.logr_id "
						+ " LEFT JOIN cadastro.bairro imovbair ON imovlogrbair.bair_id = imovbair.bair_id "
						+ " LEFT JOIN cadastro.municipio imovmun ON imovbair.muni_id = imovmun.muni_id "

						+ " LEFT JOIN cadastro.logradouro_bairro logrbair ON ra.lgbr_id = logrbair.lgbr_id "
						+ " LEFT JOIN cadastro.logradouro logr ON logrbair.logr_id = logr.logr_id "
						+ " LEFT JOIN cadastro.bairro bair ON logrbair.bair_id = bair.bair_id "
						+ " LEFT JOIN cadastro.municipio mun ON bair.muni_id = mun.muni_id "

						+ " LEFT JOIN cadastro.bairro_area barea ON ra.brar_id = barea.brar_id ";

			}

			if (idProjeto != null) {

				consulta = consulta
						+ " INNER JOIN cadastro.projeto proj ON os.proj_id = proj.proj_id ";
			}

			consulta = consulta + " WHERE 1=1 ";

			if (numeroRA != null) {
				consulta += " AND ra.rgat_id = (:numeroRA) ";
				parameters.put("numeroRA", numeroRA);
			}

			if (idProjeto != null) {
				consulta += " AND proj.proj_id = (:idProjeto) ";
				parameters.put("idProjeto", idProjeto);
			}

			if (documentoCobranca != null) {
				consulta += " AND cobra.cbdo_id = (:documentoCobranca) ";
				parameters.put("documentoCobranca", documentoCobranca);
			}

			// [SB0001] - Selecionar Ordem de Servico por Situao
			if (situacaoOrdemServico != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				consulta += " AND os.orse_cdsituacao = (:situacaoOrdemServico) ";
				parameters.put("situacaoOrdemServico", situacaoOrdemServico);
			}

			// Tipo de Servios
			if (tipoServicos != null && tipoServicos.length > 0) {
				consulta += " AND servicotipo.svtp_id in (:idServicoTipo) ";
				parameters.put("idServicoTipo", tipoServicos);
			}

			// [SB0003] - Selecionar Ordem de Servico por Matricula do Imvel
			if (matriculaImovel != null) {
				consulta += " AND imovel.imov_id = (:matriculaImovel) ";
				parameters.put("matriculaImovel", matriculaImovel);
			}

			// Perfil do imovel
			if (idsPerfilImovel != null && !idsPerfilImovel.isEmpty()) {
				consulta += " AND imovel_perfil.iper_id in (:idPerfilImovel) ";
				parameters.put("idPerfilImovel", idsPerfilImovel);
			}

			// Unidade Gerao
			if (unidadeGeracao != null) {

				consulta += "AND unid.unid_id = :idUnidadeGeracao "
						+ "AND art.attp_id = :idAtendimentoTipo ";

				parameters.put("idUnidadeGeracao", unidadeGeracao);
				parameters.put("idAtendimentoTipo",
						AtendimentoRelacaoTipo.ABRIR_REGISTRAR);

			}

			// Unidade Atual
			if (unidadeAtual != null) {

				// incluso da coluna unidade atual na tabela
				// REGISTRO_ATENDIMENTO e ORDEM_SERVICO
				// Vivianne Sousa 11/06/2008 analista:Ftima Sampaio
				consulta += " AND os.unid_idatual = (:idUnidadeAtual) ";
				parameters.put("idUnidadeAtual", unidadeAtual);
			}

			if (idsUnidadesAtuais != null) {
				consulta += " AND os.unid_idatual IN (:idUnidadesAtuais) ";
				// parameters.put("idUnidadesAtuais", idsUnidadesAtuais);
			}

			if (filtro.getColecaoAtendimentoMotivoEncerramento() != null
					&& filtro.getColecaoAtendimentoMotivoEncerramento().size() > 0) {
				consulta += " AND os.amen_id IN (:colecaoAtendimentoMotivoEncerramento) ";
				parameters.put("colecaoAtendimentoMotivoEncerramento",
						filtro.getColecaoAtendimentoMotivoEncerramento());
			}

			if (numeroOS != null) {
				consulta += " AND os.orse_id = :numeroOS ";
				parameters.put("numeroOS", numeroOS);

				// Ids de Os das unidades filtradas (geracao, atual e superior)
				// Ids de Os dos clientes
			} else if (colecaoIdsOS != null && !colecaoIdsOS.isEmpty()) {
				consulta += " AND os.orse_id in (:listaIdOS) ";
				parameters.put("listaIdOS", colecaoIdsOS);
			}

			// Perodo de Atendimento
			if (dataAtendimentoInicial != null && dataAtendimentoFinal != null) {
				consulta += " AND ra.rgat_tmregistroatendimento BETWEEN (:dataAtendimentoInicial) AND (:dataAtendimentoFinal) ";
				parameters.put("dataAtendimentoInicial",
						Util.formatarDataInicial(dataAtendimentoInicial));
				parameters.put("dataAtendimentoFinal",
						Util.formatarDataFinal(dataAtendimentoFinal));
			}

			// Perodo de Data Geracao
			if (dataGeracaoInicial != null && dataGeracaoFinal != null) {

				consulta += " AND os.orse_tmgeracao BETWEEN (:dataGeracaoInicial) AND (:dataGeracaoFinal) ";
				parameters.put("dataGeracaoInicial",
						Util.formatarDataInicial(dataGeracaoInicial));
				parameters.put("dataGeracaoFinal",
						Util.formatarDataFinal(dataGeracaoFinal));
			}

			// Perodo de Data Encerramento
			if (dataEncerramentoInicial != null
					&& dataEncerramentoFinal != null) {

				consulta += " AND os.orse_tmencerramento BETWEEN (:dataEncerramentoInicial) AND (:dataEncerramentoFinal) ";
				parameters.put("dataEncerramentoInicial",
						Util.formatarDataInicial(dataEncerramentoInicial));
				parameters.put("dataEncerramentoFinal",
						Util.formatarDataFinal(dataEncerramentoFinal));
			}

			// [SB0006] - Selecionar Ordem de Servico por Municpio
			if (municipio != null) {

				if (origemOrdemServico.equals(OrdemServico.TODAS)) {
					// 1.1. Caso tenha selecionado a opo Todas

					// 1.1.1
					consulta += " AND ( mun.muni_id = :municipioId ";

					// 1.1.2
					consulta += " OR  (imovmun.muni_id=:municipioId AND ra.rgat_id is not null AND ra.lgbr_id is null) ";

					// 1.1.3
					consulta += "  OR  (imovmun.muni_id=:municipioId AND ra.rgat_id is null)) ";

				} else if (origemOrdemServico.equals(OrdemServico.SOLICITADAS)) {
					// 1.2. Caso tenha selecionado a opo Solicitadas

					// 1.2.1
					consulta += " AND (mun.muni_id = :municipioId ";

					// 1.2.2
					consulta += " OR (imovmun.muni_id = :municipioId AND ra.rgat_id is not null AND ra.lgbr_id is null)) ";

				} else if (origemOrdemServico
						.equals(OrdemServico.SELETIVAS_COBRANCA)) {
					// 1.3 Caso tenha selecionado a opo Seletivas de
					// Cobrana

					// 1.3.1
					consulta += " AND (imovmun.muni_id = :municipioId AND cobra.cbdo_id is not null) ";

				} else if (origemOrdemServico
						.equals(OrdemServico.SELETIVAS_HIDROMETRO)) {
					// 1.4 Caso tenha selecionado a opo Seletivas de
					// Hidrometro

					// 1.4.1
					consulta += " AND (imovmun.muni_id = :municipioId AND cobra.cbdo_id is null and ra.rgat_id is null) ";

				}
				parameters.put("municipioId", new Integer(municipio));
			}

			// [SB0007] - Selecionar Ordem de Servico por Bairro
			if (bairro != null && !bairro.equals("")) {

				if (origemOrdemServico.equals(OrdemServico.TODAS)) {
					// 1.1. Caso tenha selecionado a opo Todas

					// 1.1.1
					consulta += " AND (bair.bair_id = :bairroId ";

					// 1.1.2
					consulta += " OR (imovbair.bair_id = :bairroId AND ra.rgat_id is not null AND ra.lgbr_id is null) ";

					// 1.1.3
					consulta += " OR (imovbair.bair_id = :bairroId AND ra.rgat_id is null))  ";

				} else if (origemOrdemServico.equals(OrdemServico.SOLICITADAS)) {
					// 1.2. Caso tenha selecionado a opo Solicitadas

					// 1.2.1
					consulta += " AND (bair.bair_id = :bairroId ";

					// 1.2.2
					consulta += " OR (imovbair.bair_id = :bairroId AND ra.rgat_id is not null AND ra.lgbr_id is null)) ";

				} else if (origemOrdemServico
						.equals(OrdemServico.SELETIVAS_COBRANCA)) {
					// 1.3 Caso tenha selecionado a opo Seletivas de
					// Cobrana

					// 1.3.1
					consulta += " AND (imovbair.bair_id = :bairroId AND cobra.cbdo_id is not null) ";

				} else if (origemOrdemServico
						.equals(OrdemServico.SELETIVAS_HIDROMETRO)) {
					// 1.4 Caso tenha selecionado a opo Seletivas de
					// Hidrometro

					// 1.4.1
					consulta += " AND (imovbair.bair_id = :bairroId AND cobra.cbdo_id is null and ra.rgat_id is null) ";

				}

				parameters.put("bairroId", new Integer(bairro));

				if (areaBairro != null) {
					consulta += " AND barea.brar_id = :bairroAreaId  ";
					parameters.put("bairroAreaId", areaBairro);
				}
			}

			// [SB0008] - Selecionar Ordem de Servico por Logradouro
			if (logradouro != null && !logradouro.equals("")) {

				if (origemOrdemServico.equals(OrdemServico.TODAS)) {
					// 1.1. Caso tenha selecionado a opo Todas

					// 1.1.1
					consulta += "  AND (logr.logr_id = :logradouroId  ";

					// 1.1.2
					consulta += " OR  (imovlogr.logr_id = :logradouroId AND ra.rgat_id is not null AND ra.lgbr_id is null)  ";

					// 1.1.3
					consulta += " OR  (imovlogr.logr_id = :logradouroId AND ra.rgat_id is null))  ";

				} else if (origemOrdemServico.equals(OrdemServico.SOLICITADAS)) {
					// 1.2. Caso tenha selecionado a opo Solicitadas

					// 1.2.1
					consulta += " AND (logr.logr_id = :logradouroId ";

					// 1.2.2
					consulta += " OR  (imovlogr.logr_id = :logradouroId AND ra.rgat_id is not null AND ra.lgbr_id is null)) ";

				} else if (origemOrdemServico
						.equals(OrdemServico.SELETIVAS_COBRANCA)) {
					// 1.3 Caso tenha selecionado a opo Seletivas de
					// Cobrana

					// 1.3.1
					consulta += " AND (imovlogr.logr_id = :logradouroId AND cobra.cbdo_id is not null) ";

				} else if (origemOrdemServico
						.equals(OrdemServico.SELETIVAS_HIDROMETRO)) {
					// 1.4 Caso tenha selecionado a opo Seletivas de
					// Hidrometro

					// 1.4.1
					consulta += " AND (imovlogr.logr_id = :logradouroId AND cobra.cbdo_id is null and ra.rgat_id is null) ";

				}

				parameters.put("logradouroId", new Integer(logradouro));
			}

			// indicador de terceirizao
			if (indicadorTerceirizado != null) {
				consulta += " AND servicotipo.svtp_icterceirizado = (:icTerceirizado) ";
				parameters.put("icTerceirizado", indicadorTerceirizado);
			}

			// indicador de pavimento
			if (indicadorPavimento != null) {
				consulta += " AND (servicotipo.svtp_icpvtorua = (:icPavimentoRua) or servicotipo.svtp_icpvtocal = (:icPavimentoCalcada)) ";
				parameters.put("icPavimentoRua", indicadorPavimento);
				parameters.put("icPavimentoCalcada", indicadorPavimento);
			}

			// indicador de vistoria
			if (indicadorVistoria != null) {
				consulta += " AND servicotipo.svtp_icvistoria = (:icVistoria) ";
				parameters.put("icVistoria", indicadorVistoria);
			}

			if (areaBairro == null && bairro == null && municipio == null
					&& logradouro == null) {

				// Origem da OS
				// SB0009 - Selciona Ordem de servo por origem
				if (origemOrdemServico.equals(OrdemServico.SOLICITADAS)) {

					consulta += " AND ra.rgat_id is not null ";

				} else if (origemOrdemServico
						.equals(OrdemServico.SELETIVAS_COBRANCA)) {

					consulta += " AND cobra.cbdo_id is not null ";

				} else if (origemOrdemServico
						.equals(OrdemServico.SELETIVAS_HIDROMETRO)) {

					consulta += " AND ra.rgat_id is null AND cobra.cbdo_id is null ";

				}

			}

			query = session.createSQLQuery(consulta);

			query.addScalar("idOS", Hibernate.INTEGER)
					.addScalar("idServicoTipo", Hibernate.INTEGER)
					.addScalar("descServicoTipo", Hibernate.STRING)
					.addScalar("idImovel", Hibernate.INTEGER)
					.addScalar("idRA", Hibernate.INTEGER)
					.addScalar("idCobrancaDocumento", Hibernate.INTEGER)
					.addScalar("dataGeracao", Hibernate.TIMESTAMP)
					.addScalar("dataEncerramento", Hibernate.TIMESTAMP)
					.addScalar("dataEmissao", Hibernate.TIMESTAMP)
					.addScalar("codigoSituacao", Hibernate.SHORT)
					.addScalar("unidadeAtual", Hibernate.INTEGER)
					.addScalar("idServicoTipoPrioridade", Hibernate.INTEGER)
					.addScalar("prazoExecucaoFim", Hibernate.SHORT)
					.addScalar("idPerfilImovel", Hibernate.INTEGER)
					.addScalar("perfilImovel", Hibernate.STRING);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();

				if (parameters.get(key) instanceof Set) {
					Set setList = (Set) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (Collection) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Integer[]) {
					Integer[] collection = (Integer[]) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}
			if (numeroPagina == ConstantesSistema.NUMERO_NAO_INFORMADO) {

				if (idsUnidadesAtuais != null && idsUnidadesAtuais.size() > 999) {

					Collection<Object[]> retornoConsultaPaginada = new ArrayList<Object[]>();

					List<Integer> idsPaginacao = new ArrayList<Integer>();

					for (Integer id : idsUnidadesAtuais) {
						idsPaginacao.add(id);
					}

					List<List<Integer>> particoes = CollectionUtil.particao(
							idsPaginacao, 999);

					int qtdQuebras = 999;
					int indice = idsUnidadesAtuais.size() / qtdQuebras;
					if (idsUnidadesAtuais.size() % qtdQuebras != 0) {
						indice++;
					}

					for (int i = 0; i < indice; i++) {

						retornoConsultaPaginada = query.setParameterList(
								"idUnidadesAtuais", particoes.get(i)).list();

						if (retornoConsulta == null) {
							retornoConsulta = new ArrayList<Object[]>();
						}

						retornoConsulta.addAll(retornoConsultaPaginada);
					}

				} else {

					if (idsUnidadesAtuais != null) {
						query.setParameterList("idUnidadesAtuais",
								idsUnidadesAtuais);
					}

					retornoConsulta = query.list();
				}
			} else {

				if (idsUnidadesAtuais != null && idsUnidadesAtuais.size() > 999) {

					Collection<Object[]> retornoConsultaPaginada = new ArrayList<Object[]>();

					List<Integer> idsPaginacao = new ArrayList<Integer>();

					for (Integer id : idsUnidadesAtuais) {
						idsPaginacao.add(id);
					}

					List<List<Integer>> particoes = CollectionUtil.particao(
							idsPaginacao, 999);

					int qtdQuebras = 999;
					int indice = idsUnidadesAtuais.size() / qtdQuebras;
					if (idsUnidadesAtuais.size() % qtdQuebras != 0) {
						indice++;
					}

					for (int i = 0; i < indice; i++) {

						retornoConsultaPaginada = query.setParameterList(
								"idUnidadesAtuais", particoes.get(i)).list();

						if (retornoConsulta == null) {
							retornoConsulta = new ArrayList<Object[]>();
						}

						retornoConsulta.addAll(retornoConsultaPaginada);
					}

					List<List<Object[]>> retornoPagina = CollectionUtil
							.particao((List<Object[]>) retornoConsulta, 10);

					retornoConsulta = retornoPagina.get(numeroPagina);

				} else {

					if (idsUnidadesAtuais != null) {
						query.setParameterList("idUnidadesAtuais",
								idsUnidadesAtuais);
					}

					retornoConsulta = query.setFirstResult(10 * numeroPagina)
							.setMaxResults(10).list();
				}
			}

			if (retornoConsulta.size() > 0) {

				retorno = new ArrayList();

				OrdemServico os = null;
				ServicoTipo servicoTipo = null;
				Imovel imovel = null;
				RegistroAtendimento registro = null;
				CobrancaDocumento cobranca = null;
				UnidadeOrganizacional unidade = null;
				ServicoTipoPrioridade prioridade = null;

				for (Iterator iter = retornoConsulta.iterator(); iter.hasNext();) {

					Object[] element = (Object[]) iter.next();
					registro = null;
					cobranca = null;
					unidade = null;
					prioridade = null;

					os = new OrdemServico();
					os.setId((Integer) element[0]);

					servicoTipo = new ServicoTipo();
					servicoTipo.setId((Integer) element[1]);
					servicoTipo.setDescricao((String) element[2]);

					imovel = new Imovel();
					imovel.setId((Integer) element[3]);

					if (imovel != null) {
						// perfil do imovel
						ImovelPerfil imovelPerfil = new ImovelPerfil();
						imovelPerfil.setId((Integer) element[13]);
						imovelPerfil.setDescricao((String) element[14]);
						imovel.setImovelPerfil(imovelPerfil);
					}

					if (element[4] != null) {
						registro = new RegistroAtendimento();
						registro.setId((Integer) element[4]);
						registro.setImovel(imovel);
					}

					if (element[5] != null) {
						cobranca = new CobrancaDocumento();
						cobranca.setId((Integer) element[5]);
						cobranca.setImovel(imovel);
					}

					if (element[10] != null) {
						unidade = new UnidadeOrganizacional();
						unidade.setId((Integer) element[10]);
					}

					if (element[11] != null) {
						prioridade = new ServicoTipoPrioridade();
						prioridade.setId((Integer) element[11]);
						prioridade.setPrazoExecucaoFim((Short) element[12]);
					}

					os.setDataGeracao((Date) element[6]);
					os.setDataEncerramento((Date) element[7]);
					os.setDataEmissao((Date) element[8]);
					os.setSituacao((Short) element[9]);

					os.setCobrancaDocumento(cobranca);
					os.setRegistroAtendimento(registro);
					os.setServicoTipo(servicoTipo);
					os.setUnidadeAtual(unidade);
					os.setServicoTipoPrioridadeAtual(prioridade);

					os.setImovel(imovel);

					retorno.add(os);
				}

			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0450] Pesquisar Ordem Servio
	 * 
	 * Caso exista ordem de servico abertos para a unidade de geracao informada
	 * (existe ocorrncia na tabela ORDEM_SERVICO com ORDEM_SERVICO_UNIDADE=Id
	 * da Unidade de Atendimento e ATTP_ID=1 - ABRIR/REGISTRAR)
	 * 
	 * @author Rafael Pinto
	 * @date 19/08/2006
	 * 
	 * @param idUnidadeGeracao
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaOSPorUnidadeGeracao(
			Integer idUnidadeGeracao) throws ErroRepositorioException {

		Collection<Integer> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT os.id " + "FROM OrdemServicoUnidade osUnidade "
					+ "INNER JOIN osUnidade.atendimentoRelacaoTipo art  "
					+ "INNER JOIN osUnidade.ordemServico os  "
					+ "INNER JOIN osUnidade.unidadeOrganizacional unid  "
					+ "LEFT JOIN os.registroAtendimento ra  "
					+ "WHERE unid.id = :idUnidade "
					+ "AND art.id = :idAtendimentoTipo ";

			retornoConsulta = session
					.createQuery(consulta)
					.setInteger("idUnidade", idUnidadeGeracao)
					.setInteger("idAtendimentoTipo",
							AtendimentoRelacaoTipo.ABRIR_REGISTRAR).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0450] Pesquisar Ordem Servio
	 * 
	 * Caso exista ordem de servico abertos para a unidade de geracao informada
	 * (existe ocorrncia na tabela ORDEM_SERVICO com ORDEM_SERVICO_UNIDADE=Id
	 * da Unidade de Atendimento e ATTP_ID=1 - ABRIR/REGISTRAR)
	 * 
	 * @author Rafael Pinto
	 * @date 19/08/2006
	 * 
	 * @param idUnidadeGeracao
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaOSPorUnidadeAtual(
			Integer recuperaOSPorUnidadeAtual) throws ErroRepositorioException {

		Collection<Integer> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT os.id " + "FROM OrdemServicoUnidade osUnidade "
					+ "INNER JOIN osUnidade.atendimentoRelacaoTipo art  "
					+ "INNER JOIN osUnidade.ordemServico os  "
					+ "INNER JOIN osUnidade.unidadeOrganizacional unid  "
					+ "LEFT JOIN os.registroAtendimento ra  "
					+ "WHERE unid.id = :idUnidade " + "AND ra IS NULL "
					+ "AND art.id = :idAtendimentoTipo ";

			retornoConsulta = session
					.createQuery(consulta)
					.setInteger("idUnidade", recuperaOSPorUnidadeAtual)
					.setInteger("idAtendimentoTipo",
							AtendimentoRelacaoTipo.ABRIR_REGISTRAR).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0367] Atualizar Dados da Ligao Agua
	 * 
	 * Consulta a ordem de servico pelo id
	 * 
	 * @author Rafael Pinto
	 * @date 24/08/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public OrdemServico recuperaOSPorId(Integer idOS)
			throws ErroRepositorioException {

		OrdemServico ordemServico = null;

		Object[] retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT os.id, " // 0
					+ "ra.id," // 1
					+ "servicoTipo.id,"// 2
					+ "servicoTipo.descricao,"// 3
					+ "imovel.id,"// 4
					+ "imovel.lote,"// 5
					+ "imovel.subLote,"// 6
					+ "local.id,"// 7
					+ "setor.id,"// 8
					+ "quadra.id,"// 9
					+ "quadra.numeroQuadra,"// 10
					+ "ligAgua.id,"// 11
					+ "ligAguaSitu.id,"// 12
					+ "ligAguaSitu.descricao,"// 13
					+ "ligEsgoto.id,"// 14
					+ "ligEsgotoSitu.id,"// 15
					+ "ligEsgotoSitu.descricao,"// 16
					+ "consumoTarifa.id,"// 17
					+ "consumoTarifa.descricao,"// 18
					+ "hidroInstHist.id,"// 19
					+ "supressaoTipo.id,"// 20
					+ "supressaoTipo.descricao,"// 21
					+ "supressaoMotivo.id,"// 22
					+ "supressaoMotivo.descricao,"// 23
					+ "ligacaoAguaDiametro.id,"// 24
					+ "ligacaoAguaDiametro.descricao,"// 25
					+ "ligacaoAguaMaterial.id,"// 26
					+ "ligacaoAguaMaterial.descricao,"// 27
					+ "ligacaoAguaPerfil.id,"// 28
					+ "ligacaoAguaPerfil.descricao,"// 29
					+ "hidro.id,"// 30
					+ "hidro.numero,"// 31
					+ "medicaoTipo.id,"// 32
					+ "medicaoTipo.descricao, "// 33
					+ "debitoTipo.id, " // 34
					+ "debitoTipo.descricao, " // 35
					+ "ste.indicadorLigacaoAgua, " // 36
					+ "servicoTipo.tempoMedioExecucao, " // 37
					+ "imovel.indicadorExclusao, " // 38
					+ "ligEsgDiam.id, ligEsgDiam.descricao, " // 39,40
					+ "ligEsgMat.id, ligEsgMat.descricao, " // 41,42
					+ "ligEsgPerf.id, ligEsgPerf.descricao, " // 43,44
					+ "ligEsgoto.percentualAguaConsumidaColetada, " // 45
					+ "hidroLocalInst.id, "// 46
					+ "hidroProtecao.id, "// 47
					+ "hidroInstHist.indicadorExistenciaCavalete, hidroInstHist.numeroLeituraInstalacao, " // 48,49
					+ "quadra.indicadorRedeAgua, " // 50
					+ "os.percentualCobranca, "// 51
					+ "imovHidroInstHist.id, imovHidroInstHist.indicadorExistenciaCavalete, imovHidroInstHist.numeroLeituraInstalacao, " // 52,53,54
					+ "imovHidroLocalInst.id, imovHidroProtecao.id, imovHidro.id, imovHidro.numero, " // 55,56,57,58
					+ "imovMedicaoTipo.id, medicaoTipo.descricao, " // 59,60
					+ "os.situacao, "// 61
					+ "os.dataEncerramento, "// 62
					+ "quadra.indicadorRedeEsgoto, "// 63
					+ "ligEsgoto.consumoMinimo, ligAgua.numeroConsumoMinimoAgua, imovel.numeroImovel, "// 64,65,66
					+ "amen.id, "// 67
					+ "amen.indicadorExecucao, "// 68
					+ "imovHidroSit.id, imovHidroSit.descricao, "// 69, 70
					+ "hidroSit.id, hidroSit.descricao, "// 71,72
					+ "hidroInstHist.dataInstalacao, "// 73
					+ "hidroInstHist.dataImplantacaoSistema, "// 74
					+ "corteTipo.id, "// 75
					+ "corteTipo.descricao, "// 76
					+ "motivoCorte.id, "// 77
					+ "motivoCorte.descricao, "// 78
					+ "ligAgua.numeroSeloCorte, "// 79
					+ "ligAgua.numeroSeloSupressao, "// 80
					+ "hidroInstHist.dataRetirada, "// 81
					+ "hidroInstHist.numeroSelo, imovHidroInstHist.numeroSelo, " // 82,83
					+ "os.ultimaAlteracao, imovel.ultimaAlteracao, "// 84,85
					+ "os.indicadorComercialAtualizado, hidro.dataAquisicao, " // 86,87
					+ "imovHidro.dataAquisicao, imovHidro.anoFabricacao, " // 88,89
					+ "hidro.anoFabricacao, hidro.numeroLeituraAcumulada, " // 90,91
					+ "imovHidro.numeroLeituraAcumulada, " // 92
					+ "ligAgua.ultimaAlteracao, "// 93
					+ "hidroInstHist.ligacaoAgua.id, "// 94
					+ "imovHidroInstHist.ligacaoAgua.id, "// 95
					+ "os.dataGeracao, "// 96
					+ "servicoTipoPrioridadeOriginal.id, "// 97
					+ "servicoTipoPrioridadeAtual.id, "// 98
					+ "ramalLocalInstalacao.id, "// 99
					+ "ramalLocalInstalacao.descricao, "// 100
					+ "amen.descricao, "// 101
					+ "ste.descricao, "// 102
					+ "ligEsgoto.indicadorCaixaGordura, "// 103
					+ "imovelOS.id, "// 104
					+ "locaOS.id, "// 105
					+ "stcmOS.id, "// 106
					+ "stcmOS.codigo, "// 107
					+ "qdraOS.id, "// 108
					+ "qdraOS.numeroQuadra, "// 109
					+ "imovelOS.lote, "// 110
					+ "imovelOS.subLote, "// 111
					+ "setor.codigo, "// 112

					// Dados do Imvel da OS
					+ "ligAguaOS.id,"// 113
					+ "ligAguaSituOS.id,"// 114
					+ "ligAguaSituOS.descricao,"// 115
					+ "ligEsgotoOS.id,"// 116
					+ "ligEsgotoSituOS.id,"// 117
					+ "ligEsgotoSituOS.descricao,"// 118
					+ "consumoTarifaOS.id,"// 119
					+ "consumoTarifaOS.descricao,"// 120
					+ "hidroInstHistOS.id,"// 121
					+ "supressaoTipoOS.id,"// 122
					+ "supressaoTipoOS.descricao,"// 123
					+ "supressaoMotivoOS.id,"// 124
					+ "supressaoMotivoOS.descricao,"// 125
					+ "ligacaoAguaDiametroOS.id,"// 126
					+ "ligacaoAguaDiametroOS.descricao,"// 127
					+ "ligacaoAguaMaterialOS.id,"// 128
					+ "ligacaoAguaMaterialOS.descricao,"// 129
					+ "ligacaoAguaPerfilOS.id,"// 130
					+ "ligacaoAguaPerfilOS.descricao,"// 131
					+ "hidroOS.id,"// 132
					+ "hidroOS.numero,"// 133
					+ "medicaoTipoOS.id,"// 134
					+ "medicaoTipoOS.descricao, "// 135
					+ "imovelOS.indicadorExclusao, " // 136
					+ "ligEsgDiamOS.id, ligEsgDiamOS.descricao, " // 137, 138
					+ "ligEsgMatOS.id, ligEsgMatOS.descricao, " // 139, 140
					+ "ligEsgPerfOS.id, ligEsgPerfOS.descricao, " // 141, 142
					+ "ligEsgotoOS.percentualAguaConsumidaColetada, " // 143
					+ "hidroLocalInstOS.id, "// 144
					+ "hidroProtecaoOS.id, "// 145
					+ "hidroInstHistOS.indicadorExistenciaCavalete, hidroInstHistOS.numeroLeituraInstalacao, " // 146,
					// 147
					+ "qdraOS.indicadorRedeAgua, " // 148
					+ "imovHidroInstHistOS.id, imovHidroInstHistOS.indicadorExistenciaCavalete, " // 149,
					// 150
					+ "imovHidroInstHistOS.numeroLeituraInstalacao, " // 151
					+ "imovHidroLocalInstOS.id, imovHidroProtecaoOS.id, imovHidroOS.id, " // 152,
					// 153,
					// 154
					+ "imovHidroOS.numero, " // 155
					+ "imovMedicaoTipoOS.id, medicaoTipoOS.descricao, " // 156,
					// 157
					+ "qdraOS.indicadorRedeEsgoto, "// 158
					+ "ligEsgotoOS.consumoMinimo, ligAguaOS.numeroConsumoMinimoAgua, imovelOS.numeroImovel, "// 159,
					// 160,
					// 161
					+ "imovHidroSitOS.id, imovHidroSitOS.descricao, "// 162, 163
					+ "hidroSitOS.id, hidroSitOS.descricao, "// 164, 165
					+ "hidroInstHistOS.dataInstalacao, "// 166
					+ "hidroInstHistOS.dataImplantacaoSistema, "// 167
					+ "corteTipoOS.id, "// 168
					+ "corteTipoOS.descricao, "// 169
					+ "motivoCorteOS.id, "// 170
					+ "motivoCorteOS.descricao, "// 171
					+ "ligAguaOS.numeroSeloCorte, "// 172
					+ "ligAguaOS.numeroSeloSupressao, "// 173
					+ "hidroInstHistOS.dataRetirada, "// 174
					+ "hidroInstHistOS.numeroSelo, imovHidroInstHistOS.numeroSelo, " // 175,
					// 176
					+ "imovHidroOS.dataAquisicao, imovHidroOS.anoFabricacao, " // 177,
					// 178
					+ "hidroOS.anoFabricacao, hidroOS.numeroLeituraAcumulada, " // 179,
					// 180
					+ "imovHidroOS.numeroLeituraAcumulada, " // 181
					+ "ligAguaOS.ultimaAlteracao, "// 182
					+ "hidroInstHistOS.ligacaoAgua.id, "// 183
					+ "imovHidroInstHistOS.ligacaoAgua.id, "// 184
					+ "ramalLocalInstalacaoOS.id, "// 185
					+ "ramalLocalInstalacaoOS.descricao, "// 186
					+ "ligEsgotoOS.indicadorCaixaGordura, "// 187
					+ "imovelOS.ultimaAlteracao, hidroOS.dataAquisicao, " // 188,
					// 189

					+ "osReferencia.id, osReferencia.situacao, " // 190, 191

					+ "servicoTipo.indicadorPermiteAlterarValor, " // 192
					+ "servicoTipo.indicadorCobrarJuros, " // 193
					+ "ligAgua.numeroLacre, " // 194
					+ "ligAguaOS.numeroLacre, " // 195
					+ "ligAgua.dataLigacao, " // 196
					+ "ligEsgoto.dataLigacao, " // 197
					+ "ligAguaOS.dataLigacao, " // 198
					+ "ligEsgotoOS.dataLigacao, " // 199
					+ "usuInstalacao.id," // 200
					+ "usuInstalacao.nomeUsuario," // 201
					+ "hidroInstHistOS.numeroLacre, " // 202
					+ "func.id, " // 203
					+ "os.indicadorEncerramentoAutomatico, " // 204
					+ "ligOrigem.id, " // 205

					+ "imovHidroInstHist.dataImplantacaoSistema, "// 206
					+ "os.unidadeAtual.id, "// 207

					+ "quadraFace.id, "// 208
					+ "quadraFace.numeroQuadraFace, "// 209
					+ "quadraFace.indicadorRedeAgua, "// 210
					+ "quadraFace.indicadorRedeEsgoto, "// 211

					+ "qdraFaceOS.id, "// 212
					+ "qdraFaceOS.numeroQuadraFace, "// 213
					+ "qdraFaceOS.indicadorRedeAgua, "// 214
					+ "qdraFaceOS.indicadorRedeEsgoto, "// 215
					+ "os.indicadorBoletim, "// 216
					+ "os.indicadorAtualizaAgua, " // 217
					+ "os.indicadorAtualizaEsgoto, " // 218
					+ "amen.qtdeDiasAditivoPrazo, "// 219
					+ "ligEsgoto.indicadorLigacaoEsgoto, "// 220
					+ "pgrCali.id, "// 221
					+ "pgrCali.peso, "// 222
					+ "pgrCali.fator, "// 223
                    /**
					 * Felipe Santos
					 * 
					 * Incluso do atributo servicoTipoReferencia para 
					 * no retornar objeto com o campo nulo.
					 */
					+ "servicoTipoReferencia.id, "// 224
					+ "os.valorOriginal, " // 225
					+ "os.valorAtual " // 226
					
					// fim da alterao
					+ "FROM OrdemServico os "
					+ "LEFT JOIN os.registroAtendimento ra  "
					+ "LEFT JOIN os.atendimentoMotivoEncerramento amen "
					+ "LEFT JOIN ra.solicitacaoTipoEspecificacao ste "
					+ "INNER JOIN os.servicoTipo servicoTipo  "
					+ "LEFT JOIN servicoTipo.debitoTipo debitoTipo  "
					+ "LEFT JOIN servicoTipo.programaCalibragem pgrCali  "
					+ "INNER JOIN os.servicoTipoPrioridadeOriginal servicoTipoPrioridadeOriginal  "
					+ "INNER JOIN os.servicoTipoPrioridadeAtual servicoTipoPrioridadeAtual  "
					+ "LEFT JOIN os.osReferencia osReferencia "

					/**
					 * Felipe Santos
					 * 
					 * Incluso do atributo servicoTipoReferencia para 
					 * no retornar objeto com o campo nulo.
					 */
					 + "LEFT JOIN os.servicoTipoReferencia servicoTipoReferencia "
					 // fim da alterao
					// Dados do Imvel do RA da OS
					+ "LEFT JOIN ra.imovel imovel "
					+ "LEFT JOIN imovel.localidade local "
					+ "LEFT JOIN imovel.setorComercial setor  "
					+ "LEFT JOIN imovel.quadra quadra  "
					+ "LEFT JOIN imovel.quadraFace quadraFace "
					+ "LEFT JOIN imovel.ligacaoAgua ligAgua  "
					+ "LEFT JOIN imovel.ligacaoAguaSituacao ligAguaSitu  "
					+ "LEFT JOIN ligAgua.supressaoTipo supressaoTipo  "
					+ "LEFT JOIN ligAgua.supressaoMotivo supressaoMotivo  "
					+ "LEFT JOIN ligAgua.ligacaoAguaDiametro ligacaoAguaDiametro  "
					+ "LEFT JOIN ligAgua.ligacaoAguaMaterial ligacaoAguaMaterial  "
					+ "LEFT JOIN ligAgua.ligacaoAguaPerfil ligacaoAguaPerfil  "
					+ "LEFT JOIN ligAgua.corteTipo corteTipo "
					+ "LEFT JOIN ligAgua.motivoCorte motivoCorte "
					+ "LEFT JOIN ligAgua.ramalLocalInstalacao ramalLocalInstalacao "
					+ "LEFT JOIN ligAgua.hidrometroInstalacaoHistorico hidroInstHist  "
					+ "LEFT JOIN hidroInstHist.hidrometroLocalInstalacao hidroLocalInst  "
					+ "LEFT JOIN hidroInstHist.hidrometroProtecao hidroProtecao  "
					+ "LEFT JOIN hidroInstHist.medicaoTipo  medicaoTipo "
					+ "LEFT JOIN hidroInstHist.hidrometro  hidro "
					+ "LEFT JOIN hidro.hidrometroSituacao hidroSit "
					+ "LEFT JOIN imovel.ligacaoEsgoto ligEsgoto "
					+ "LEFT JOIN imovel.ligacaoEsgotoSituacao ligEsgotoSitu  "
					+ "LEFT JOIN ligEsgoto.ligacaoEsgotoDiametro ligEsgDiam "
					+ "LEFT JOIN ligEsgoto.ligacaoEsgotoMaterial ligEsgMat "
					+ "LEFT JOIN ligEsgoto.ligacaoEsgotoPerfil ligEsgPerf "
					+ "LEFT JOIN imovel.hidrometroInstalacaoHistorico imovHidroInstHist  "
					+ "LEFT JOIN imovHidroInstHist.hidrometroLocalInstalacao imovHidroLocalInst  "
					+ "LEFT JOIN imovHidroInstHist.hidrometroProtecao imovHidroProtecao  "
					+ "LEFT JOIN imovHidroInstHist.medicaoTipo  imovMedicaoTipo "
					+ "LEFT JOIN imovHidroInstHist.hidrometro  imovHidro "
					+ "LEFT JOIN imovHidro.hidrometroSituacao imovHidroSit "
					+ "LEFT JOIN imovel.consumoTarifa consumoTarifa  "
					+ "LEFT JOIN ligEsgoto.ligacaoOrigem ligOrigem "

					// Dados do Imvel da OS
					+ "LEFT JOIN os.imovel imovelOS "
					+ "LEFT JOIN imovelOS.localidade locaOS "
					+ "LEFT JOIN imovelOS.setorComercial stcmOS  "
					+ "LEFT JOIN imovelOS.quadra qdraOS  "
					+ "LEFT JOIN imovelOS.quadraFace qdraFaceOS  "
					+ "LEFT JOIN imovelOS.ligacaoAgua ligAguaOS  "
					+ "LEFT JOIN imovelOS.ligacaoAguaSituacao ligAguaSituOS  "
					+ "LEFT JOIN ligAguaOS.supressaoTipo supressaoTipoOS  "
					+ "LEFT JOIN ligAguaOS.supressaoMotivo supressaoMotivoOS  "
					+ "LEFT JOIN ligAguaOS.ligacaoAguaDiametro ligacaoAguaDiametroOS  "
					+ "LEFT JOIN ligAguaOS.ligacaoAguaMaterial ligacaoAguaMaterialOS  "
					+ "LEFT JOIN ligAguaOS.ligacaoAguaPerfil ligacaoAguaPerfilOS  "
					+ "LEFT JOIN ligAguaOS.corteTipo corteTipoOS "
					+ "LEFT JOIN ligAguaOS.motivoCorte motivoCorteOS "
					+ "LEFT JOIN ligAguaOS.ramalLocalInstalacao ramalLocalInstalacaoOS "
					+ "LEFT JOIN ligAguaOS.hidrometroInstalacaoHistorico hidroInstHistOS  "
					+ "LEFT JOIN hidroInstHistOS.hidrometroLocalInstalacao hidroLocalInstOS  "
					+ "LEFT JOIN hidroInstHistOS.hidrometroProtecao hidroProtecaoOS  "
					+ "LEFT JOIN hidroInstHistOS.medicaoTipo  medicaoTipoOS "
					+ "LEFT JOIN hidroInstHistOS.hidrometro  hidroOS "
					+ "LEFT JOIN hidroOS.hidrometroSituacao hidroSitOS "
					+ "LEFT JOIN imovelOS.ligacaoEsgoto ligEsgotoOS "
					+ "LEFT JOIN imovelOS.ligacaoEsgotoSituacao ligEsgotoSituOS  "
					+ "LEFT JOIN ligEsgotoOS.ligacaoEsgotoDiametro ligEsgDiamOS "
					+ "LEFT JOIN ligEsgotoOS.ligacaoEsgotoMaterial ligEsgMatOS "
					+ "LEFT JOIN ligEsgotoOS.ligacaoEsgotoPerfil ligEsgPerfOS "
					+ "LEFT JOIN imovelOS.hidrometroInstalacaoHistorico imovHidroInstHistOS  "
					+ "LEFT JOIN imovHidroInstHistOS.hidrometroLocalInstalacao imovHidroLocalInstOS  "
					+ "LEFT JOIN imovHidroInstHistOS.hidrometroProtecao imovHidroProtecaoOS  "
					+ "LEFT JOIN imovHidroInstHistOS.medicaoTipo  imovMedicaoTipoOS "
					+ "LEFT JOIN imovHidroInstHistOS.hidrometro  imovHidroOS "
					+ "LEFT JOIN imovHidroOS.hidrometroSituacao imovHidroSitOS "
					+ "LEFT JOIN imovelOS.consumoTarifa consumoTarifaOS  "
					+ "LEFT JOIN hidroInstHistOS.usuarioInstalacao usuInstalacao  "
					+ "LEFT JOIN usuInstalacao.funcionario func  "

					+ "WHERE os.id = :idOS ";

			retornoConsulta = (Object[]) session.createQuery(consulta)
					.setInteger("idOS", idOS).setMaxResults(1).uniqueResult();

			if (retornoConsulta != null) {

				ordemServico = new OrdemServico();
				ordemServico.setId((Integer) retornoConsulta[0]);

				if (retornoConsulta[51] != null) {
					ordemServico
					.setPercentualCobranca((BigDecimal) retornoConsulta[51]);
				}
				if (retornoConsulta[61] != null) {
					ordemServico.setSituacao((Short) retornoConsulta[61]);
				}
				if (retornoConsulta[62] != null) {
					ordemServico
					.setDataEncerramento((Date) retornoConsulta[62]);
				}
				if (retornoConsulta[204] != null) {
					ordemServico
					.setIndicadorEncerramentoAutomatico((Short) retornoConsulta[204]);
				}

				if (retornoConsulta[207] != null) {
					UnidadeOrganizacional unidadeAtual = new UnidadeOrganizacional();
					unidadeAtual.setId((Integer) retornoConsulta[207]);
					ordemServico.setUnidadeAtual(unidadeAtual);
				}

				if (retornoConsulta[96] != null) {
					ordemServico.setDataGeracao((Date) retornoConsulta[96]);
				}

				if (retornoConsulta[216] != null) {
					ordemServico
					.setIndicadorBoletim((Short) retornoConsulta[216]);
				}

				if (retornoConsulta[217] != null) {
					ordemServico
					.setIndicadorAtualizaAgua((Short) retornoConsulta[217]);
				}

				if (retornoConsulta[218] != null) {
					ordemServico
					.setIndicadorAtualizaEsgoto((Short) retornoConsulta[218]);
				}

				if (retornoConsulta[190] != null) {

					OrdemServico ordemServicoReferencia = new OrdemServico();

					ordemServicoReferencia
					.setId((Integer) retornoConsulta[190]);
					ordemServicoReferencia
					.setSituacao((Short) retornoConsulta[191]);

					ordemServico.setOsReferencia(ordemServicoReferencia);
				}

				ordemServico.setUltimaAlteracao((Date) retornoConsulta[84]);

				RegistroAtendimento registro = null;

				if (retornoConsulta[1] != null) {
					registro = new RegistroAtendimento();
					registro.setId((Integer) retornoConsulta[1]);

					SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
					solicitacaoTipoEspecificacao
					.setIndicadorLigacaoAgua((Short) retornoConsulta[36]);
					solicitacaoTipoEspecificacao
					.setDescricao((String) retornoConsulta[102]);

					registro.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);

					if (retornoConsulta[4] != null) {

						Imovel imovel = new Imovel();
						imovel.setId((Integer) retornoConsulta[4]);
						imovel.setLote((Short) retornoConsulta[5]);
						imovel.setSubLote((Short) retornoConsulta[6]);
						imovel.setIndicadorExclusao((Short) retornoConsulta[38]);
						imovel.setNumeroImovel((String) retornoConsulta[66]);
						imovel.setUltimaAlteracao((Date) retornoConsulta[85]);

						if (retornoConsulta[7] != null) {
							Localidade local = new Localidade();
							local.setId((Integer) retornoConsulta[7]);

							imovel.setLocalidade(local);
						}

						if (retornoConsulta[8] != null) {
							SetorComercial setor = new SetorComercial();
							setor.setId((Integer) retornoConsulta[8]);
							setor.setCodigo((Integer) retornoConsulta[112]);

							imovel.setSetorComercial(setor);
						}

						if (retornoConsulta[9] != null) {
							Quadra quadra = new Quadra();
							quadra.setId((Integer) retornoConsulta[9]);
							quadra.setNumeroQuadra((Integer) retornoConsulta[10]);
							quadra.setIndicadorRedeAgua((Short) retornoConsulta[50]);
							quadra.setIndicadorRedeEsgoto((Short) retornoConsulta[63]);

							imovel.setQuadra(quadra);
						}

						if (retornoConsulta[208] != null) {

							QuadraFace quadraFace = new QuadraFace();
							quadraFace.setId((Integer) retornoConsulta[208]);
							quadraFace
							.setNumeroQuadraFace((Integer) retornoConsulta[209]);
							quadraFace
							.setIndicadorRedeAgua((Short) retornoConsulta[210]);
							quadraFace
							.setIndicadorRedeEsgoto((Short) retornoConsulta[211]);

							imovel.setQuadraFace(quadraFace);
						}

						if (retornoConsulta[52] != null) {
							HidrometroInstalacaoHistorico imovHidrometroInstalacaoHist = new HidrometroInstalacaoHistorico();
							imovHidrometroInstalacaoHist
							.setId((Integer) retornoConsulta[52]);
							imovHidrometroInstalacaoHist
							.setIndicadorExistenciaCavalete((Short) retornoConsulta[53]);
							imovHidrometroInstalacaoHist
							.setNumeroLeituraInstalacao((Integer) retornoConsulta[54]);
							imovHidrometroInstalacaoHist
							.setNumeroSelo((String) retornoConsulta[83]);
							imovHidrometroInstalacaoHist
							.setDataImplantacaoSistema((Date) retornoConsulta[206]);

							LigacaoAgua ligacaoAgua = new LigacaoAgua();
							ligacaoAgua.setId((Integer) retornoConsulta[94]);

							imovHidrometroInstalacaoHist
							.setLigacaoAgua(ligacaoAgua);

							HidrometroLocalInstalacao imovHidrometroLocalInstalacao = new HidrometroLocalInstalacao();
							imovHidrometroLocalInstalacao
							.setId((Integer) retornoConsulta[55]);
							imovHidrometroInstalacaoHist
							.setHidrometroLocalInstalacao(imovHidrometroLocalInstalacao);

							HidrometroProtecao imovHidrometroProtecao = new HidrometroProtecao();
							imovHidrometroProtecao
							.setId((Integer) retornoConsulta[56]);
							imovHidrometroInstalacaoHist
							.setHidrometroProtecao(imovHidrometroProtecao);

							if (retornoConsulta[57] != null) {

								Hidrometro imovHidrometro = new Hidrometro();
								imovHidrometro
								.setId((Integer) retornoConsulta[57]);
								imovHidrometro
								.setNumero((String) retornoConsulta[58]);
								imovHidrometro
								.setDataAquisicao((Date) retornoConsulta[88]);
								imovHidrometro
								.setAnoFabricacao((Short) retornoConsulta[89]);
								imovHidrometro
								.setNumeroLeituraAcumulada((Integer) retornoConsulta[92]);

								imovHidrometroInstalacaoHist
								.setHidrometro(imovHidrometro);
								HidrometroSituacao hidrometroSituacao = new HidrometroSituacao();
								hidrometroSituacao
								.setId((Integer) retornoConsulta[69]);
								hidrometroSituacao
								.setDescricao((String) retornoConsulta[70]);
								imovHidrometro
								.setHidrometroSituacao(hidrometroSituacao);
							}

							if (retornoConsulta[59] != null) {
								MedicaoTipo imovMedicaoTipo = new MedicaoTipo();
								imovMedicaoTipo
								.setId((Integer) retornoConsulta[59]);
								imovMedicaoTipo
								.setDescricao((String) retornoConsulta[60]);

								imovHidrometroInstalacaoHist
								.setMedicaoTipo(imovMedicaoTipo);
							}

							imovel.setHidrometroInstalacaoHistorico(imovHidrometroInstalacaoHist);
						}

						if (retornoConsulta[11] != null) {

							LigacaoAgua ligacaoAgua = new LigacaoAgua();
							ligacaoAgua.setId((Integer) retornoConsulta[11]);
							ligacaoAgua
							.setNumeroConsumoMinimoAgua((Integer) retornoConsulta[65]);
							ligacaoAgua
							.setNumeroSeloCorte((Integer) retornoConsulta[79]);
							ligacaoAgua
							.setNumeroSeloSupressao((Integer) retornoConsulta[80]);
							ligacaoAgua
							.setUltimaAlteracao((Date) retornoConsulta[93]);
							ligacaoAgua
							.setNumeroLacre((String) retornoConsulta[194]);

							if (retornoConsulta[196] != null) {
								ligacaoAgua
								.setDataLigacao((Date) retornoConsulta[196]);
							}

							if (retornoConsulta[19] != null) {

								HidrometroInstalacaoHistorico hidroInstHist = new HidrometroInstalacaoHistorico();
								hidroInstHist
								.setId((Integer) retornoConsulta[19]);
								hidroInstHist
								.setIndicadorExistenciaCavalete((Short) retornoConsulta[48]);
								hidroInstHist
								.setNumeroLeituraInstalacao((Integer) retornoConsulta[49]);
								hidroInstHist
								.setDataInstalacao((Date) retornoConsulta[73]);
								hidroInstHist
								.setDataImplantacaoSistema((Date) retornoConsulta[74]);
								hidroInstHist
								.setDataRetirada((Date) retornoConsulta[81]);
								hidroInstHist
								.setNumeroSelo((String) retornoConsulta[82]);

								LigacaoAgua ligacaoAguaAgua = new LigacaoAgua();
								ligacaoAguaAgua
								.setId((Integer) retornoConsulta[94]);

								hidroInstHist.setLigacaoAgua(ligacaoAguaAgua);

								HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
								hidrometroLocalInstalacao
								.setId((Integer) retornoConsulta[46]);
								hidroInstHist
								.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);

								HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
								hidrometroProtecao
								.setId((Integer) retornoConsulta[47]);
								hidroInstHist
								.setHidrometroProtecao(hidrometroProtecao);

								if (retornoConsulta[30] != null) {
									Hidrometro hidrometro = new Hidrometro();
									hidrometro
									.setId((Integer) retornoConsulta[30]);
									hidrometro
									.setNumero((String) retornoConsulta[31]);
									hidrometro
									.setDataAquisicao((Date) retornoConsulta[87]);
									hidrometro
									.setAnoFabricacao((Short) retornoConsulta[90]);
									hidrometro
									.setNumeroLeituraAcumulada((Integer) retornoConsulta[91]);
									HidrometroSituacao hidrometroSituacao = new HidrometroSituacao();
									hidrometroSituacao
									.setId((Integer) retornoConsulta[71]);
									hidrometroSituacao
									.setDescricao((String) retornoConsulta[72]);
									hidrometro
									.setHidrometroSituacao(hidrometroSituacao);
									hidroInstHist.setHidrometro(hidrometro);
								}

								if (retornoConsulta[32] != null) {
									MedicaoTipo medicaoTipo = new MedicaoTipo();
									medicaoTipo
									.setId((Integer) retornoConsulta[32]);
									medicaoTipo
									.setDescricao((String) retornoConsulta[33]);

									hidroInstHist.setMedicaoTipo(medicaoTipo);
								}

								ligacaoAgua
								.setHidrometroInstalacaoHistorico(hidroInstHist);
							}

							if (retornoConsulta[20] != null) {
								SupressaoTipo supressaoTipo = new SupressaoTipo();
								supressaoTipo
								.setId((Integer) retornoConsulta[20]);
								supressaoTipo
								.setDescricao((String) retornoConsulta[21]);

								ligacaoAgua.setSupressaoTipo(supressaoTipo);
							}

							if (retornoConsulta[22] != null) {
								SupressaoMotivo supressaoMotivo = new SupressaoMotivo();
								supressaoMotivo
								.setId((Integer) retornoConsulta[22]);
								supressaoMotivo
								.setDescricao((String) retornoConsulta[23]);

								ligacaoAgua.setSupressaoMotivo(supressaoMotivo);
							}

							if (retornoConsulta[24] != null) {
								LigacaoAguaDiametro ligacaoAguaDiametro = new LigacaoAguaDiametro();
								ligacaoAguaDiametro
								.setId((Integer) retornoConsulta[24]);
								ligacaoAguaDiametro
								.setDescricao((String) retornoConsulta[25]);

								ligacaoAgua
								.setLigacaoAguaDiametro(ligacaoAguaDiametro);
							}

							if (retornoConsulta[26] != null) {
								LigacaoAguaMaterial ligacaoAguaMaterial = new LigacaoAguaMaterial();
								ligacaoAguaMaterial
								.setId((Integer) retornoConsulta[26]);
								ligacaoAguaMaterial
								.setDescricao((String) retornoConsulta[27]);

								ligacaoAgua
								.setLigacaoAguaMaterial(ligacaoAguaMaterial);
							}

							if (retornoConsulta[28] != null) {
								LigacaoAguaPerfil ligacaoAguaPerfil = new LigacaoAguaPerfil();
								ligacaoAguaPerfil
								.setId((Integer) retornoConsulta[28]);
								ligacaoAguaPerfil
								.setDescricao((String) retornoConsulta[29]);

								ligacaoAgua
								.setLigacaoAguaPerfil(ligacaoAguaPerfil);
							}

							if (retornoConsulta[75] != null) {
								CorteTipo corteTipo = new CorteTipo();
								corteTipo.setId((Integer) retornoConsulta[75]);
								corteTipo
								.setDescricao((String) retornoConsulta[76]);

								ligacaoAgua.setCorteTipo(corteTipo);
							}

							if (retornoConsulta[77] != null) {
								MotivoCorte motivoCorte = new MotivoCorte();
								motivoCorte
								.setId((Integer) retornoConsulta[77]);
								motivoCorte
								.setDescricao((String) retornoConsulta[78]);

								ligacaoAgua.setMotivoCorte(motivoCorte);
							}

							if (retornoConsulta[99] != null) {
								RamalLocalInstalacao ramalLocalInstalacao = new RamalLocalInstalacao();
								ramalLocalInstalacao
								.setId((Integer) retornoConsulta[99]);
								ramalLocalInstalacao
								.setDescricao((String) retornoConsulta[100]);

								ligacaoAgua
								.setRamalLocalInstalacao(ramalLocalInstalacao);
							}

							imovel.setLigacaoAgua(ligacaoAgua);
						}

						if (retornoConsulta[12] != null) {
							LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
							ligacaoAguaSituacao
							.setId((Integer) retornoConsulta[12]);
							ligacaoAguaSituacao
							.setDescricao((String) retornoConsulta[13]);

							imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
						}

						if (retornoConsulta[14] != null) {
							LigacaoEsgoto ligacaoEsgoto = new LigacaoEsgoto();
							ligacaoEsgoto.setId((Integer) retornoConsulta[14]);
							ligacaoEsgoto
							.setIndicadorCaixaGordura((Short) retornoConsulta[103]);
							ligacaoEsgoto
							.setConsumoMinimo((Integer) retornoConsulta[64]);

							if (retornoConsulta[197] != null) {
								ligacaoEsgoto
								.setDataLigacao((Date) retornoConsulta[197]);
							}

							LigacaoEsgotoDiametro ligacaoEsgotoDiametro = new LigacaoEsgotoDiametro();
							ligacaoEsgotoDiametro
							.setId((Integer) retornoConsulta[39]);
							ligacaoEsgotoDiametro
							.setDescricao((String) retornoConsulta[40]);
							ligacaoEsgoto
							.setLigacaoEsgotoDiametro(ligacaoEsgotoDiametro);

							LigacaoEsgotoMaterial ligacaoEsgotoMaterial = new LigacaoEsgotoMaterial();
							ligacaoEsgotoMaterial
							.setId((Integer) retornoConsulta[41]);
							ligacaoEsgotoMaterial
							.setDescricao((String) retornoConsulta[42]);
							ligacaoEsgoto
							.setLigacaoEsgotoMaterial(ligacaoEsgotoMaterial);

							LigacaoEsgotoPerfil ligacaoEsgotoPerfil = new LigacaoEsgotoPerfil();
							ligacaoEsgotoPerfil
							.setId((Integer) retornoConsulta[43]);
							ligacaoEsgotoPerfil
							.setDescricao((String) retornoConsulta[44]);
							ligacaoEsgoto
							.setLigacaoEsgotoPerfil(ligacaoEsgotoPerfil);
							ligacaoEsgoto
							.setIndicadorLigacaoEsgoto((Short) retornoConsulta[220]);

							ligacaoEsgoto
							.setPercentualAguaConsumidaColetada((BigDecimal) retornoConsulta[45]);
							if (retornoConsulta[205] != null) {

								LigacaoOrigem ligacaoOrigem = new LigacaoOrigem();

								ligacaoOrigem
								.setId((Integer) retornoConsulta[205]);

								ligacaoEsgoto.setLigacaoOrigem(ligacaoOrigem);
							}

							imovel.setLigacaoEsgoto(ligacaoEsgoto);
						}

						if (retornoConsulta[15] != null) {
							LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
							ligacaoEsgotoSituacao
							.setId((Integer) retornoConsulta[15]);
							ligacaoEsgotoSituacao
							.setDescricao((String) retornoConsulta[16]);

							imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
						}

						if (retornoConsulta[17] != null) {
							ConsumoTarifa consumoTarifa = new ConsumoTarifa();
							consumoTarifa.setId((Integer) retornoConsulta[17]);
							consumoTarifa
							.setDescricao((String) retornoConsulta[18]);

							imovel.setConsumoTarifa(consumoTarifa);
						}

						if (retornoConsulta[200] != null) {
							Usuario usuarioInstalacao = new Usuario();
							Funcionario funcionario = new Funcionario();
							usuarioInstalacao.setFuncionario(funcionario);
							usuarioInstalacao
							.setId((Integer) retornoConsulta[200]);
							usuarioInstalacao
							.setNomeUsuario((String) retornoConsulta[201]);
							usuarioInstalacao.getFuncionario().setId(
									(Integer) retornoConsulta[203]);

							imovel.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico()
							.setUsuarioInstalacao(usuarioInstalacao);
						}

						if (retornoConsulta[202] != null) {
							imovel.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico()
							.setNumeroLacre(
									(String) retornoConsulta[202]);
						}

						registro.setImovel(imovel);
					}
				}

				ServicoTipo servicoTipo = new ServicoTipo();
				servicoTipo.setId((Integer) retornoConsulta[2]);
				servicoTipo.setDescricao((String) retornoConsulta[3]);
				servicoTipo.setTempoMedioExecucao((Short) retornoConsulta[37]);
				servicoTipo
				.setIndicadorPermiteAlterarValor((Short) retornoConsulta[192]);
				servicoTipo
				.setIndicadorCobrarJuros((Short) retornoConsulta[193]);

				if (retornoConsulta[34] != null) {
					DebitoTipo debitoTipo = new DebitoTipo();

					debitoTipo.setId((Integer) retornoConsulta[34]);
					debitoTipo.setDescricao((String) retornoConsulta[35]);
					servicoTipo.setDebitoTipo(debitoTipo);
				}
				// Colocado por Sᶩo Luiz em 12/09/2011
				if (retornoConsulta[221] != null) {
					OSProgramacaoCalibragem oSProgramacaoCalibragem = new OSProgramacaoCalibragem();

					oSProgramacaoCalibragem
					.setId((Integer) retornoConsulta[221]);
					if (retornoConsulta[222] != null) {
						oSProgramacaoCalibragem
						.setPeso((Integer) retornoConsulta[222]);
					}
					if (retornoConsulta[223] != null) {
						oSProgramacaoCalibragem
						.setFator((Integer) retornoConsulta[223]);
					}
					servicoTipo.setProgramaCalibragem(oSProgramacaoCalibragem);
				}

				/**
				 * Felipe Santos
				 * 
				 * Incluso do atributo servicoTipoReferencia para 
				 * no retornar objeto com o campo nulo.
				 */
				ServicoTipo servicoTipoReferencia = new ServicoTipo();
				servicoTipoReferencia.setId((Integer) retornoConsulta[224]);
				ordemServico.setServicoTipoReferencia(servicoTipoReferencia);
				// fim da alterao

				ordemServico.setServicoTipo(servicoTipo);

				// Colocado por Raphael Rossiter em 23/09/2006
				if (retornoConsulta[67] != null) {
					AtendimentoMotivoEncerramento amen = new AtendimentoMotivoEncerramento();
					amen.setId((Integer) retornoConsulta[67]);
					amen.setIndicadorExecucao(((Short) retornoConsulta[68])
							.shortValue());
					amen.setDescricao((String) retornoConsulta[101]);
					if (retornoConsulta[219] != null) {
						amen.setQtdeDiasAditivoPrazo((Integer) retornoConsulta[219]);
					}
					ordemServico.setAtendimentoMotivoEncerramento(amen);
				}

				if (retornoConsulta[97] != null) {
					ServicoTipoPrioridade servicoTipoPrioridade = new ServicoTipoPrioridade();
					servicoTipoPrioridade.setId((Integer) retornoConsulta[97]);
					ordemServico
					.setServicoTipoPrioridadeOriginal(servicoTipoPrioridade);
				}

				if (retornoConsulta[98] != null) {
					ServicoTipoPrioridade servicoTipoPrioridade = new ServicoTipoPrioridade();
					servicoTipoPrioridade.setId((Integer) retornoConsulta[98]);
					ordemServico
					.setServicoTipoPrioridadeAtual(servicoTipoPrioridade);
				}

				ordemServico.setRegistroAtendimento(registro);
				ordemServico
				.setIndicadorComercialAtualizado((Short) retornoConsulta[86]);

				// Imvel da OS - Colocado por Raphael Rossiter em 16/01/2007
				if (retornoConsulta[104] != null) {

					Imovel imovelOS = new Imovel();
					imovelOS.setId((Integer) retornoConsulta[104]);

					Localidade localidadeOS = new Localidade();
					localidadeOS.setId((Integer) retornoConsulta[105]);

					SetorComercial setorComercialOS = new SetorComercial();
					setorComercialOS.setId((Integer) retornoConsulta[106]);
					setorComercialOS.setCodigo((Integer) retornoConsulta[107]);

					Quadra quadraOS = new Quadra();
					quadraOS.setId((Integer) retornoConsulta[108]);
					quadraOS.setNumeroQuadra((Integer) retornoConsulta[109]);
					quadraOS.setIndicadorRedeAgua((Short) retornoConsulta[148]);
					quadraOS.setIndicadorRedeEsgoto((Short) retornoConsulta[158]);

					if (retornoConsulta[212] != null) {

						QuadraFace quadraFaceOS = new QuadraFace();
						quadraFaceOS.setId((Integer) retornoConsulta[212]);
						quadraFaceOS
						.setNumeroQuadraFace((Integer) retornoConsulta[213]);
						quadraFaceOS
						.setIndicadorRedeAgua((Short) retornoConsulta[214]);
						quadraFaceOS
						.setIndicadorRedeEsgoto((Short) retornoConsulta[215]);

						imovelOS.setQuadraFace(quadraFaceOS);
					}

					imovelOS.setLocalidade(localidadeOS);
					imovelOS.setSetorComercial(setorComercialOS);
					imovelOS.setQuadra(quadraOS);

					imovelOS.setLote((Short) retornoConsulta[110]);
					imovelOS.setSubLote((Short) retornoConsulta[111]);

					// ==============================================================================================

					imovelOS.setIndicadorExclusao((Short) retornoConsulta[136]);
					imovelOS.setNumeroImovel((String) retornoConsulta[161]);
					imovelOS.setUltimaAlteracao((Date) retornoConsulta[188]);

					if (retornoConsulta[149] != null) {

						HidrometroInstalacaoHistorico imovHidrometroInstalacaoHist = new HidrometroInstalacaoHistorico();

						imovHidrometroInstalacaoHist
						.setId((Integer) retornoConsulta[149]);
						imovHidrometroInstalacaoHist
						.setIndicadorExistenciaCavalete((Short) retornoConsulta[150]);
						imovHidrometroInstalacaoHist
						.setNumeroLeituraInstalacao((Integer) retornoConsulta[151]);
						imovHidrometroInstalacaoHist
						.setNumeroSelo((String) retornoConsulta[176]);

						LigacaoAgua ligacaoAgua = new LigacaoAgua();
						ligacaoAgua.setId((Integer) retornoConsulta[183]);

						imovHidrometroInstalacaoHist
						.setLigacaoAgua(ligacaoAgua);

						HidrometroLocalInstalacao imovHidrometroLocalInstalacao = new HidrometroLocalInstalacao();

						imovHidrometroLocalInstalacao
						.setId((Integer) retornoConsulta[152]);
						imovHidrometroInstalacaoHist
						.setHidrometroLocalInstalacao(imovHidrometroLocalInstalacao);

						HidrometroProtecao imovHidrometroProtecao = new HidrometroProtecao();

						imovHidrometroProtecao
						.setId((Integer) retornoConsulta[153]);
						imovHidrometroInstalacaoHist
						.setHidrometroProtecao(imovHidrometroProtecao);

						if (retornoConsulta[154] != null) {

							Hidrometro imovHidrometro = new Hidrometro();

							imovHidrometro
							.setId((Integer) retornoConsulta[154]);
							imovHidrometro
							.setNumero((String) retornoConsulta[155]);
							imovHidrometro
							.setDataAquisicao((Date) retornoConsulta[177]);
							imovHidrometro
							.setAnoFabricacao((Short) retornoConsulta[178]);
							imovHidrometro
							.setNumeroLeituraAcumulada((Integer) retornoConsulta[181]);

							imovHidrometroInstalacaoHist
							.setHidrometro(imovHidrometro);

							HidrometroSituacao hidrometroSituacao = new HidrometroSituacao();

							hidrometroSituacao
							.setId((Integer) retornoConsulta[162]);
							hidrometroSituacao
							.setDescricao((String) retornoConsulta[163]);
							imovHidrometro
							.setHidrometroSituacao(hidrometroSituacao);
						}

						if (retornoConsulta[156] != null) {

							MedicaoTipo imovMedicaoTipo = new MedicaoTipo();

							imovMedicaoTipo
							.setId((Integer) retornoConsulta[156]);
							imovMedicaoTipo
							.setDescricao((String) retornoConsulta[157]);

							imovHidrometroInstalacaoHist
							.setMedicaoTipo(imovMedicaoTipo);
						}

						imovelOS.setHidrometroInstalacaoHistorico(imovHidrometroInstalacaoHist);

					}

					if (retornoConsulta[113] != null) {

						LigacaoAgua ligacaoAgua = new LigacaoAgua();

						ligacaoAgua.setId((Integer) retornoConsulta[113]);

						ligacaoAgua
						.setNumeroConsumoMinimoAgua((Integer) retornoConsulta[160]);
						ligacaoAgua
						.setNumeroSeloCorte((Integer) retornoConsulta[172]);
						ligacaoAgua
						.setNumeroSeloSupressao((Integer) retornoConsulta[173]);
						ligacaoAgua
						.setUltimaAlteracao((Date) retornoConsulta[182]);
						ligacaoAgua
						.setNumeroLacre((String) retornoConsulta[195]);

						if (retornoConsulta[198] != null) {
							ligacaoAgua
							.setDataLigacao((Date) retornoConsulta[198]);
						}

						if (retornoConsulta[121] != null) {

							HidrometroInstalacaoHistorico hidroInstHist = new HidrometroInstalacaoHistorico();
							hidroInstHist.setId((Integer) retornoConsulta[121]);
							hidroInstHist
							.setIndicadorExistenciaCavalete((Short) retornoConsulta[146]);
							hidroInstHist
							.setNumeroLeituraInstalacao((Integer) retornoConsulta[147]);
							hidroInstHist
							.setDataInstalacao((Date) retornoConsulta[166]);
							hidroInstHist
							.setDataImplantacaoSistema((Date) retornoConsulta[167]);
							hidroInstHist
							.setDataRetirada((Date) retornoConsulta[174]);
							hidroInstHist
							.setNumeroSelo((String) retornoConsulta[175]);

							LigacaoAgua ligacaoAguaAgua = new LigacaoAgua();

							ligacaoAguaAgua
							.setId((Integer) retornoConsulta[183]);

							hidroInstHist.setLigacaoAgua(ligacaoAguaAgua);

							HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();

							hidrometroLocalInstalacao
							.setId((Integer) retornoConsulta[144]);
							hidroInstHist
							.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);

							HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();

							hidrometroProtecao
							.setId((Integer) retornoConsulta[145]);
							hidroInstHist
							.setHidrometroProtecao(hidrometroProtecao);

							if (retornoConsulta[132] != null) {

								Hidrometro hidrometro = new Hidrometro();

								hidrometro
								.setId((Integer) retornoConsulta[132]);
								hidrometro
								.setNumero((String) retornoConsulta[133]);
								hidrometro
								.setDataAquisicao((Date) retornoConsulta[189]);
								hidrometro
								.setAnoFabricacao((Short) retornoConsulta[179]);
								hidrometro
								.setNumeroLeituraAcumulada((Integer) retornoConsulta[180]);

								HidrometroSituacao hidrometroSituacao = new HidrometroSituacao();

								hidrometroSituacao
								.setId((Integer) retornoConsulta[164]);
								hidrometroSituacao
								.setDescricao((String) retornoConsulta[165]);
								hidrometro
								.setHidrometroSituacao(hidrometroSituacao);

								hidroInstHist.setHidrometro(hidrometro);
							}

							if (retornoConsulta[134] != null) {

								MedicaoTipo medicaoTipo = new MedicaoTipo();

								medicaoTipo
								.setId((Integer) retornoConsulta[134]);
								medicaoTipo
								.setDescricao((String) retornoConsulta[135]);

								hidroInstHist.setMedicaoTipo(medicaoTipo);
							}

							ligacaoAgua
							.setHidrometroInstalacaoHistorico(hidroInstHist);
						}

						if (retornoConsulta[122] != null) {

							SupressaoTipo supressaoTipo = new SupressaoTipo();

							supressaoTipo.setId((Integer) retornoConsulta[122]);
							supressaoTipo
							.setDescricao((String) retornoConsulta[123]);

							ligacaoAgua.setSupressaoTipo(supressaoTipo);
						}

						if (retornoConsulta[124] != null) {

							SupressaoMotivo supressaoMotivo = new SupressaoMotivo();

							supressaoMotivo
							.setId((Integer) retornoConsulta[124]);
							supressaoMotivo
							.setDescricao((String) retornoConsulta[125]);

							ligacaoAgua.setSupressaoMotivo(supressaoMotivo);
						}

						if (retornoConsulta[126] != null) {

							LigacaoAguaDiametro ligacaoAguaDiametro = new LigacaoAguaDiametro();

							ligacaoAguaDiametro
							.setId((Integer) retornoConsulta[126]);
							ligacaoAguaDiametro
							.setDescricao((String) retornoConsulta[127]);

							ligacaoAgua
							.setLigacaoAguaDiametro(ligacaoAguaDiametro);
						}

						if (retornoConsulta[128] != null) {

							LigacaoAguaMaterial ligacaoAguaMaterial = new LigacaoAguaMaterial();

							ligacaoAguaMaterial
							.setId((Integer) retornoConsulta[128]);
							ligacaoAguaMaterial
							.setDescricao((String) retornoConsulta[129]);

							ligacaoAgua
							.setLigacaoAguaMaterial(ligacaoAguaMaterial);
						}

						if (retornoConsulta[130] != null) {

							LigacaoAguaPerfil ligacaoAguaPerfil = new LigacaoAguaPerfil();

							ligacaoAguaPerfil
							.setId((Integer) retornoConsulta[130]);
							ligacaoAguaPerfil
							.setDescricao((String) retornoConsulta[131]);

							ligacaoAgua.setLigacaoAguaPerfil(ligacaoAguaPerfil);
						}

						if (retornoConsulta[168] != null) {

							CorteTipo corteTipo = new CorteTipo();

							corteTipo.setId((Integer) retornoConsulta[168]);

							corteTipo
							.setDescricao((String) retornoConsulta[169]);

							ligacaoAgua.setCorteTipo(corteTipo);
						}

						if (retornoConsulta[170] != null) {

							MotivoCorte motivoCorte = new MotivoCorte();

							motivoCorte.setId((Integer) retornoConsulta[170]);
							motivoCorte
							.setDescricao((String) retornoConsulta[171]);

							ligacaoAgua.setMotivoCorte(motivoCorte);
						}

						if (retornoConsulta[185] != null) {

							RamalLocalInstalacao ramalLocalInstalacao = new RamalLocalInstalacao();

							ramalLocalInstalacao
							.setId((Integer) retornoConsulta[185]);
							ramalLocalInstalacao
							.setDescricao((String) retornoConsulta[186]);

							ligacaoAgua
							.setRamalLocalInstalacao(ramalLocalInstalacao);
						}

						imovelOS.setLigacaoAgua(ligacaoAgua);

					}

					if (retornoConsulta[114] != null) {

						LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();

						ligacaoAguaSituacao
						.setId((Integer) retornoConsulta[114]);
						ligacaoAguaSituacao
						.setDescricao((String) retornoConsulta[115]);

						imovelOS.setLigacaoAguaSituacao(ligacaoAguaSituacao);
					}

					if (retornoConsulta[116] != null) {

						LigacaoEsgoto ligacaoEsgoto = new LigacaoEsgoto();

						ligacaoEsgoto.setId((Integer) retornoConsulta[116]);

						ligacaoEsgoto
						.setIndicadorCaixaGordura((Short) retornoConsulta[187]);
						ligacaoEsgoto
						.setConsumoMinimo((Integer) retornoConsulta[159]);

						if (retornoConsulta[199] != null) {
							ligacaoEsgoto
							.setDataLigacao((Date) retornoConsulta[199]);
						}

						LigacaoEsgotoDiametro ligacaoEsgotoDiametro = new LigacaoEsgotoDiametro();

						ligacaoEsgotoDiametro
						.setId((Integer) retornoConsulta[137]);
						ligacaoEsgotoDiametro
						.setDescricao((String) retornoConsulta[138]);
						ligacaoEsgoto
						.setLigacaoEsgotoDiametro(ligacaoEsgotoDiametro);

						LigacaoEsgotoMaterial ligacaoEsgotoMaterial = new LigacaoEsgotoMaterial();

						ligacaoEsgotoMaterial
						.setId((Integer) retornoConsulta[139]);
						ligacaoEsgotoMaterial
						.setDescricao((String) retornoConsulta[140]);
						ligacaoEsgoto
						.setLigacaoEsgotoMaterial(ligacaoEsgotoMaterial);

						LigacaoEsgotoPerfil ligacaoEsgotoPerfil = new LigacaoEsgotoPerfil();
						ligacaoEsgotoPerfil
						.setId((Integer) retornoConsulta[141]);
						ligacaoEsgotoPerfil
						.setDescricao((String) retornoConsulta[142]);
						ligacaoEsgoto
						.setLigacaoEsgotoPerfil(ligacaoEsgotoPerfil);

						ligacaoEsgoto
						.setPercentualAguaConsumidaColetada((BigDecimal) retornoConsulta[143]);

						imovelOS.setLigacaoEsgoto(ligacaoEsgoto);
					}

					if (retornoConsulta[117] != null) {

						LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();

						ligacaoEsgotoSituacao
						.setId((Integer) retornoConsulta[117]);
						ligacaoEsgotoSituacao
						.setDescricao((String) retornoConsulta[118]);

						imovelOS.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
					}

					if (retornoConsulta[119] != null) {

						ConsumoTarifa consumoTarifa = new ConsumoTarifa();

						consumoTarifa.setId((Integer) retornoConsulta[119]);

						consumoTarifa
						.setDescricao((String) retornoConsulta[120]);

						imovelOS.setConsumoTarifa(consumoTarifa);
					}

					ordemServico.setImovel(imovelOS);
					
					ordemServico.setValorOriginal((BigDecimal) retornoConsulta[225]);
					ordemServico.setValorAtual((BigDecimal) retornoConsulta[226]);
					
				}

			}


		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return ordemServico;
	}

	/**
	 * [UC0450] Pesquisar Ordem Servio
	 * 
	 * Seleciona ordem de servio por codigo do cliente atraves do RASolicitante
	 * 
	 * @author Rafael Pinto
	 * @date 19/08/2006
	 * 
	 * @param codigoCliente
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaOSPorCodigoClienteRASolicitante(
			Integer codigoCliente) throws ErroRepositorioException {

		Collection<Integer> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select os.orse_id as idOS "
					+ "from atendimentopublico.ordem_servico os, "
					+ "atendimentopublico.ra_solicitante ras "
					+ "where os.rgat_id = ras.rgat_id "
					+ "and ras.clie_id = :codigoCliente ";

			retornoConsulta = session.createSQLQuery(consulta)
					.addScalar("idOS", Hibernate.INTEGER)
					.setInteger("codigoCliente", codigoCliente).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0450] Pesquisar Ordem Servio
	 * 
	 * Seleciona ordem de servio por codigo do cliente atraves do cliente
	 * imovel
	 * 
	 * @author Rafael Pinto
	 * @date 19/08/2006
	 * 
	 * @param codigoCliente
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaOSPorCodigoCliente(
			Integer codigoCliente, String origemOrdemServico)
					throws ErroRepositorioException {

		Collection<Integer> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "select os.orse_id as idOS "
					+ "from atendimentopublico.ordem_servico os, "
					+ "cadastro.cliente_imovel clienteImov  "
					+ "where os.imov_id = clienteImov.imov_id  "
					+ "and clienteImov.clie_id = :codigoCliente "
					+ "and clienteImov.clim_dtrelacaofim <> null  ";

			if (origemOrdemServico.equals(OrdemServico.SOLICITADAS)) {
				consulta = consulta + "and rgat_id <> null ";
			}

			if (origemOrdemServico.equals(OrdemServico.SELETIVAS_COBRANCA)) {
				consulta = consulta + "and cbdo_id <> null ";
			}

			if (origemOrdemServico.equals(OrdemServico.SELETIVAS_HIDROMETRO)) {
				consulta = consulta + "and rgat_id = null and cbdo_id = null ";
			}

			retornoConsulta = session.createSQLQuery(consulta)
					.addScalar("idOS", Hibernate.INTEGER)
					.setInteger("codigoCliente", codigoCliente).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0450] Pesquisar Ordem Servio
	 * 
	 * Seleciona ordem de servio por codigo do cliente atraves do documento
	 * cobrana
	 * 
	 * @author Rafael Pinto
	 * @date 19/08/2006
	 * 
	 * @param codigoCliente
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaOSPorCodigoClienteCobrancaDocumento(
			Integer codigoCliente) throws ErroRepositorioException {

		Collection<Integer> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "select os.orse_id as idOS "
					+ "from atendimentopublico.ordem_servico os, "
					+ "cobranca.cobranca_documento cob, "
					+ "cadastro.cliente_imovel clienteImov "
					+ "where os.cbdo_id = cob.cbdo_id "
					+ "and cob.imov_id = clienteImov.imov_id "
					+ "and clienteImov.clie_id = :codigoCliente "
					+ "and clienteImov.clim_dtrelacaofim <> null ";

			retornoConsulta = session.createSQLQuery(consulta)
					.addScalar("idOS", Hibernate.INTEGER)
					.setInteger("codigoCliente", codigoCliente).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0454] Obter Descrio da situao da OS
	 * 
	 * Verificar a situao(ORSE_CDSITUACAO) da ordem de servio
	 * 
	 * 
	 * @author Leonardo Regis
	 * @date 11/08/2006
	 * 
	 * 
	 * @param idOS
	 * @throws ControladorException
	 */
	public Short verificaSituacaoOS(Integer idOrdemServico)
			throws ErroRepositorioException {

		Short retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select orse.situacao " + "from OrdemServico orse "
					+ "where orse.id = :idOrdemServico ";

			retorno = (Short) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0441] Consultar Dados da Ordem de Servio
	 * 
	 * @author Leonardo Regis
	 * @date 15/08/2006
	 * 
	 * 
	 * @param idOrdemServico
	 * @exception ErroRepositorioExceptions
	 */
	public OrdemServico consultarDadosOS(Integer idOrdemServico)
			throws ErroRepositorioException {

		OrdemServico retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT orse.id, " // 00
					+ " orse.situacao, " // 01
					+ " rgat.id, " // 02
					+ " rgat.codigoSituacao, " // 03
					+ " doc.id, " // 04
					+ " orse.dataGeracao, " // 05
					+ " osRef.id, " // 06
					+ " servTipRef.id, " // 07
					+ " servTipRef.descricao, " // 08
					+ " servTip.id, " // 09
					+ " servTip.descricao, " // 10
					+ " retTipRef.id, " // 11
					+ " retTipRef.descricao, " // 12
					+ " orse.observacao, " // 13
					+ " orse.valorOriginal, " // 14
					+ " orse.valorAtual, " // 15
					+ " servTipPriOri.id, " // 16
					+ " servTipPriOri.descricao, " // 17
					+ " servTipPriAtu.id, " // 18
					+ " servTipPriAtu.descricao, " // 19
					+ " orse.dataEmissao, " // 20
					+ " orse.dataEncerramento, " // 21
					+ " orse.descricaoParecerEncerramento, " // 22
					+ " orse.areaPavimento, " // 23
					+ " orse.indicadorComercialAtualizado, " // 24
					+ " orse.percentualCobranca, " // 25
					+ " servNaoCobMot.id, " // 26
					+ " servNaoCobMot.descricao, " // 27
					+ " osRef.situacao, "// 28
					+ " servTipOsReferencia.descricao, "// 29
					+ " servTipReferencia.id,"// 30
					+ " servTip.indicadorPavimento, "// 31
					+ " servTipReferencia.descricao, "// 32
					+ " orse.ultimaAlteracao, "// 33
					+ " servTip.indicadorAtualizaComercial, "// 34
					+ " servTip.indicadorVistoria, "// 35
					+ " orse.indicadorProgramada, "// 36
					+ " servTip.indicadorFiscalizacaoInfracao, "// 37
					+ " fiscSit.id,"// 38
					+ " doc.imovel.id," // 39
					+ " imov.id," // 40
					+ " loca.id," // 41
					+ " stcm.codigo," // 42
					+ " qdra.numeroQuadra," // 43
					+ " imov.lote," // 44
					+ " imov.subLote," // 45
					+ " rota.codigo, " // 46
					+ " imov.numeroSequencialRota, " // 47
					+ " amen.descricao, " // 48
					+ " servTipReferencia.indicadorDiagnostico, " // 49
					+ " orse.indicadorEncerramentoAutomatico, " // 50
					+ " servTip.indicadorPavimentoRua, " // 51
					+ " servTip.indicadorPavimentoCalcada,  " // 52
					+ " servTipPrioridade.id,  " // 53
					+ " unidAtual.id, " // 54
					+ " debTipo.id," // 55
					+ " orse.projeto.id, " // 56
					+ " fiscSit.descricaoFiscalizacaoSituacao, " // 57
					+ " orse.dataFiscalizacaoSituacao, " // 58
					+ " rgat.logradouroCep.id, " // 59
					+ " orse.indicadorAtualizaAgua, " // 60
					+ " orse.indicadorAtualizaEsgoto, " // 61
					+ " orse.indicadorBoletim, " // 62
					+ " servTipReferencia.indicadorFiscalizacao, "// 63
					+ " servTip.indicadorProgramacaoAutomatica, "// 64
					+ " pgrCali.id, "// 65
					+ " pgrCali.peso, "// 66
					+ " pgrCali.fator "// 67
					+ " FROM OrdemServico orse "
					+ " LEFT JOIN orse.registroAtendimento rgat "
					+ " LEFT JOIN orse.cobrancaDocumento doc "
					+ " LEFT JOIN orse.servicoTipo servTip "
					+ " LEFT JOIN servTip.programaCalibragem pgrCali  "
					+ " LEFT JOIN orse.servicoTipoPrioridadeOriginal servTipPriOri "
					+ " LEFT JOIN orse.servicoTipoPrioridadeAtual servTipPriAtu "
					+ " LEFT JOIN orse.servicoNaoCobrancaMotivo servNaoCobMot "
					+ " LEFT JOIN orse.osReferidaRetornoTipo retTipRef "
					+ " LEFT JOIN orse.atendimentoMotivoEncerramento amen"
					+ " LEFT JOIN orse.osReferencia osRef "
					+ " LEFT JOIN osRef.servicoTipo servTipOsReferencia "
					+ " LEFT JOIN orse.servicoTipoReferencia servTipRef "
					+ " LEFT JOIN servTip.servicoTipoReferencia servTipReferencia "
					+ " LEFT JOIN servTip.servicoTipoPrioridade servTipPrioridade "
					+ " LEFT JOIN servTip.debitoTipo debTipo "
					+ " LEFT JOIN orse.fiscalizacaoSituacao fiscSit "
					+ " LEFT JOIN orse.imovel imov"
					+ " LEFT JOIN imov.localidade loca"
					+ " LEFT JOIN imov.setorComercial stcm"
					+ " LEFT JOIN imov.quadra qdra"
					+ " LEFT JOIN qdra.rota rota"
					+ " LEFT JOIN orse.unidadeAtual unidAtual "
					+ " WHERE orse.id = :idOrdemServico ";

			Object[] retornoConsulta = (Object[]) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setMaxResults(1).uniqueResult();

			if (retornoConsulta != null) {
				retorno = new OrdemServico();

				retorno.setId((Integer) retornoConsulta[0]);
				retorno.setSituacao((Short) retornoConsulta[1]);
				RegistroAtendimento registroAtendimento = null;
				if ((Integer) retornoConsulta[2] != null) {
					registroAtendimento = new RegistroAtendimento();
					registroAtendimento.setId((Integer) retornoConsulta[2]);
					registroAtendimento
					.setCodigoSituacao((Short) retornoConsulta[3]);

					if ((Integer) retornoConsulta[59] != null) {
						LogradouroCep logradouroCep = new LogradouroCep();
						logradouroCep.setId((Integer) retornoConsulta[59]);

						registroAtendimento.setLogradouroCep(logradouroCep);
					}

				}

				retorno.setRegistroAtendimento(registroAtendimento);
				CobrancaDocumento cobranca = null;
				if ((Integer) retornoConsulta[4] != null) {
					cobranca = new CobrancaDocumento();
					cobranca.setId((Integer) retornoConsulta[4]);
					Imovel imovel = new Imovel();
					imovel.setId((Integer) retornoConsulta[39]);
					cobranca.setImovel(imovel);
				}
				retorno.setCobrancaDocumento(cobranca);
				retorno.setDataGeracao((Date) retornoConsulta[5]);
				OrdemServico osReferencia = null;
				if ((Integer) retornoConsulta[6] != null) {
					osReferencia = new OrdemServico();
					osReferencia.setId((Integer) retornoConsulta[6]);
					osReferencia.setSituacao((Short) retornoConsulta[28]);
					if (retornoConsulta[29] != null) {
						ServicoTipo servicoTipo = new ServicoTipo();
						servicoTipo.setDescricao((String) retornoConsulta[29]);
						osReferencia.setServicoTipo(servicoTipo);
					}
				}
				retorno.setOsReferencia(osReferencia);
				ServicoTipo servicoTipoOsReferencia = null;
				if ((Integer) retornoConsulta[7] != null) {
					servicoTipoOsReferencia = new ServicoTipo();
					servicoTipoOsReferencia.setId((Integer) retornoConsulta[7]);
					servicoTipoOsReferencia
					.setDescricao((String) retornoConsulta[8]);
				}
				retorno.setServicoTipoReferencia(servicoTipoOsReferencia);
				ServicoTipo servicoTipo = null;
				if ((Integer) retornoConsulta[9] != null) {

					servicoTipo = new ServicoTipo();
					servicoTipo.setId((Integer) retornoConsulta[9]);
					servicoTipo.setDescricao((String) retornoConsulta[10]);
					servicoTipo
					.setIndicadorFiscalizacaoInfracao((Short) retornoConsulta[37]);
					servicoTipo
					.setIndicadorProgramacaoAutomatica((Short) retornoConsulta[64]);

					if (retornoConsulta[30] != null) {
						ServicoTipoReferencia servicoTipoReferencia = new ServicoTipoReferencia();
						servicoTipoReferencia
						.setId((Integer) retornoConsulta[30]);
						servicoTipoReferencia
						.setDescricao((String) retornoConsulta[32]);
						servicoTipoReferencia
						.setIndicadorDiagnostico((Short) retornoConsulta[49]);
						servicoTipoReferencia
						.setIndicadorFiscalizacao((Short) retornoConsulta[63]);
						servicoTipo
						.setServicoTipoReferencia(servicoTipoReferencia);
					}

					if (retornoConsulta[31] != null) {
						servicoTipo
						.setIndicadorPavimento((Short) retornoConsulta[31]);
					}

					if (retornoConsulta[34] != null) {
						servicoTipo
						.setIndicadorAtualizaComercial((Short) retornoConsulta[34]);
					}

					if (retornoConsulta[35] != null) {
						servicoTipo
						.setIndicadorVistoria((Short) retornoConsulta[35]);
					}

					if (retornoConsulta[51] != null) {
						servicoTipo
						.setIndicadorPavimentoRua((Short) retornoConsulta[51]);
					}

					if (retornoConsulta[52] != null) {
						servicoTipo
						.setIndicadorPavimentoCalcada((Short) retornoConsulta[52]);
					}

					if (retornoConsulta[53] != null) {

						ServicoTipoPrioridade servicoTipoPrioridade = new ServicoTipoPrioridade();

						servicoTipoPrioridade
						.setId((Integer) retornoConsulta[53]);
						servicoTipo
						.setServicoTipoPrioridade(servicoTipoPrioridade);
					}
					// Alterado por Sᶩo Luiz Data:12/09/2011
					if (retornoConsulta[65] != null) {

						OSProgramacaoCalibragem oSProgramacaoCalibragem = new OSProgramacaoCalibragem();

						oSProgramacaoCalibragem
						.setId((Integer) retornoConsulta[65]);
						if (retornoConsulta[66] != null) {
							oSProgramacaoCalibragem
							.setPeso((Integer) retornoConsulta[66]);
						}
						if (retornoConsulta[67] != null) {
							oSProgramacaoCalibragem
							.setFator((Integer) retornoConsulta[67]);
						}
						servicoTipo
						.setProgramaCalibragem(oSProgramacaoCalibragem);
					}

				}
				retorno.setServicoTipo(servicoTipo);
				OsReferidaRetornoTipo osReferidaRetornoTipo = null;
				if ((Integer) retornoConsulta[11] != null) {
					osReferidaRetornoTipo = new OsReferidaRetornoTipo();
					osReferidaRetornoTipo.setId((Integer) retornoConsulta[11]);
					osReferidaRetornoTipo
					.setDescricao((String) retornoConsulta[12]);
				}
				retorno.setOsReferidaRetornoTipo(osReferidaRetornoTipo);
				retorno.setObservacao((String) retornoConsulta[13]);
				retorno.setValorOriginal((BigDecimal) retornoConsulta[14]);
				retorno.setValorAtual((BigDecimal) retornoConsulta[15]);
				ServicoTipoPrioridade servicoTipoPrioridadeOriginal = null;
				if ((Integer) retornoConsulta[16] != null) {
					servicoTipoPrioridadeOriginal = new ServicoTipoPrioridade();
					servicoTipoPrioridadeOriginal
					.setId((Integer) retornoConsulta[16]);
					servicoTipoPrioridadeOriginal
					.setDescricao((String) retornoConsulta[17]);
				}
				if (retornoConsulta[50] != null) {
					retorno.setIndicadorEncerramentoAutomatico((Short) retornoConsulta[50]);
				}

				retorno.setServicoTipoPrioridadeOriginal(servicoTipoPrioridadeOriginal);
				ServicoTipoPrioridade servicoTipoPrioridadeAtual = null;
				if ((Integer) retornoConsulta[18] != null) {
					servicoTipoPrioridadeAtual = new ServicoTipoPrioridade();
					servicoTipoPrioridadeAtual
					.setId((Integer) retornoConsulta[18]);
					servicoTipoPrioridadeAtual
					.setDescricao((String) retornoConsulta[19]);
				}
				retorno.setServicoTipoPrioridadeAtual(servicoTipoPrioridadeAtual);
				retorno.setDataEmissao((Date) retornoConsulta[20]);
				retorno.setDataEncerramento((Date) retornoConsulta[21]);
				retorno.setDescricaoParecerEncerramento((String) retornoConsulta[22]);
				retorno.setAreaPavimento((BigDecimal) retornoConsulta[23]);
				retorno.setIndicadorComercialAtualizado((Short) retornoConsulta[24]);
				retorno.setPercentualCobranca((BigDecimal) retornoConsulta[25]);
				ServicoNaoCobrancaMotivo servicoNaoCobrancaoMotivo = null;
				if ((Integer) retornoConsulta[26] != null) {
					servicoNaoCobrancaoMotivo = new ServicoNaoCobrancaMotivo();
					servicoNaoCobrancaoMotivo
					.setId((Integer) retornoConsulta[26]);
					servicoNaoCobrancaoMotivo
					.setDescricao((String) retornoConsulta[27]);
				}
				retorno.setServicoNaoCobrancaMotivo(servicoNaoCobrancaoMotivo);
				if (retornoConsulta[33] != null) {
					retorno.setUltimaAlteracao((Date) retornoConsulta[33]);
				}

				if (retornoConsulta[36] != null) {
					retorno.setIndicadorProgramada((Short) retornoConsulta[36]);
				}

				if (retornoConsulta[38] != null) {
					FiscalizacaoSituacao fiscalizacaoSituacao = new FiscalizacaoSituacao();
					fiscalizacaoSituacao.setId((Integer) retornoConsulta[38]);

					if (retornoConsulta[57] != null) {
						fiscalizacaoSituacao
						.setDescricaoFiscalizacaoSituacao((String) retornoConsulta[57]);
					}

					retorno.setFiscalizacaoSituacao(fiscalizacaoSituacao);
				}

				if (retornoConsulta[48] != null) {
					AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
					atendimentoMotivoEncerramento
					.setDescricao((String) retornoConsulta[48]);
					retorno.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
				}

				// Dados do Imvel
				if (retornoConsulta[40] != null) {
					Imovel imovel = new Imovel();
					imovel.setId((Integer) retornoConsulta[40]);
					if (retornoConsulta[41] != null) {
						Localidade localidade = new Localidade();
						localidade.setId((Integer) retornoConsulta[41]);

						imovel.setLocalidade(localidade);
					}
					if (retornoConsulta[42] != null) {
						SetorComercial setorComercial = new SetorComercial();
						setorComercial.setCodigo((Integer) retornoConsulta[42]);
						imovel.setSetorComercial(setorComercial);
					}
					if (retornoConsulta[43] != null) {
						Quadra quadra = new Quadra();
						quadra.setNumeroQuadra((Integer) retornoConsulta[43]);
						Rota rota = null;
						if (retornoConsulta[46] != null) {
							rota = new Rota();
							rota.setCodigo((Short) retornoConsulta[46]);
						}
						quadra.setRota(rota);
						imovel.setQuadra(quadra);
					}

					if (retornoConsulta[47] != null) {
						imovel.setNumeroSequencialRota((Integer) retornoConsulta[47]);
					}

					imovel.setLote((Short) retornoConsulta[44]);
					imovel.setSubLote((Short) retornoConsulta[45]);
					retorno.setImovel(imovel);
				}

				if (retornoConsulta[54] != null) {
					UnidadeOrganizacional unidadeOrganizacionalAtual = new UnidadeOrganizacional();
					unidadeOrganizacionalAtual
					.setId((Integer) retornoConsulta[54]);
					retorno.setUnidadeAtual(unidadeOrganizacionalAtual);
				}

				if (retornoConsulta[55] != null) {
					DebitoTipo debitoTipo = new DebitoTipo();
					debitoTipo.setId((Integer) retornoConsulta[55]);
					servicoTipo.setDebitoTipo(debitoTipo);
					retorno.setServicoTipo(servicoTipo);
				}
				if (retornoConsulta[56] != null) {
					Projeto projeto = new Projeto();
					projeto.setId((Integer) retornoConsulta[56]);
					retorno.setProjeto(projeto);
				}
				if (retornoConsulta[58] != null) {
					retorno.setDataFiscalizacaoSituacao((Date) retornoConsulta[58]);
				}
				if (retornoConsulta[60] != null) {
					retorno.setIndicadorAtualizaAgua((Short) retornoConsulta[60]);
				}
				if (retornoConsulta[61] != null) {
					retorno.setIndicadorAtualizaEsgoto((Short) retornoConsulta[61]);
				}
				if (retornoConsulta[62] != null) {
					retorno.setIndicadorBoletim((Short) retornoConsulta[62]);
				}

			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0427] Tramitar Registro Atendimento
	 * 
	 * Verifica Existncia de Ordem de Servio Programada
	 * 
	 * @author Leonardo Regis
	 * @date 19/08/2006
	 * 
	 * @param idOS
	 * @return boolean
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaOSProgramada(Integer idOS)
			throws ErroRepositorioException {

		boolean retorno = false;
		Collection<Object[]> retornoConsulta = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = " SELECT osp.id, " // 0
					+ " os.id " // 1
					+ " FROM OrdemServicoProgramacao osp "
					+ " INNER JOIN osp.ordemServico os  "
					+ " WHERE os.id = :idOS "
					+ " and osp.indicadorAtivo = "
					+ OrdemServicoProgramacao.INDICADOR_ATIVO
					+ " ORDER BY os.id ";

			retornoConsulta = session.createQuery(consulta)
					.setInteger("idOS", idOS).list();

			if (retornoConsulta.size() > 0) {
				retorno = true;
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0450] Pesquisar Ordem Servio
	 * 
	 * Seleciona ordem de servio por codigo do cliente atraves do RASolicitante
	 * 
	 * @author Leandro Cavalcanti
	 * @date 19/08/2006
	 * 
	 * @param codigoCliente
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperarAtividadesServicoTipo(
			Collection<Integer> atividades) throws ErroRepositorioException {

		Collection<Integer> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			if (atividades != null && !atividades.isEmpty()) {
				consulta = "select at.ativ_id as idAT "
						+ "from atendimentopublico.atividade at, "
						+ "atendimentopublico.servico_tipo_atividade stat "
						+ "where at.ativ_id = stat.ativ_id "
						+ "and stat.svtp_id in (:atividades) ";

				retornoConsulta = session.createSQLQuery(consulta)
						.addScalar("idAT", Hibernate.INTEGER)
						.setParameter("atividades", atividades).list();
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * Pesquisar Materiais
	 * 
	 * Seleciona os materiais no array de servico tipo.
	 * 
	 * @author Rafael Pinto
	 * @date 19/08/2006
	 * 
	 * @param Collection
	 *            <Integer>
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperarMaterialServicoTipo(
			Collection<Integer> materiais) throws ErroRepositorioException {

		Collection<Integer> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			if (materiais != null && !materiais.isEmpty()) {
				consulta = "select at.ativ_id idMAT "
						+ "from atendimentopublico.material_unidade matuni, "
						+ "atendimentopublico.servico_tipo_material stmat "
						+ "where matuni.mate_id = stmat.mate_id "
						+ "and stmat.svtp_id in (:materiais) ";

				retornoConsulta = session.createSQLQuery(consulta)
						.addScalar("idMAT", Hibernate.INTEGER)
						.setParameter("materiais", materiais).list();
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0413] Pesquisar Tipo de Servio
	 * 
	 * select a.svtp_id from ATENDIMENTOPUBLICO.SERVICO_TIPO A,
	 * ATENDIMENTOPUBLICO.SERVICO_TIPO_ATIVIDADE B,
	 * ATENDIMENTOPUBLICO.SERVICO_TIPO_MATERIAL C WHERE A.SVTP_DSSERVICOTIPO
	 * LIKE '%DESC%' AND A.SVTP_DSABREVIADO LIKE '%DESC%' AND (A.SVTP_VLSERVICO
	 * >= 000000 AND A.SVTP_VLSERVICO <= 99999) AND A.SVTP_ICPAVIMENTO = 1 OU 2
	 * and A.SVTP_ICATUALIZACOMERCIAL = 1 OU 2 AND A.SVTP_ICTERCEIRIZADO = 1 OU
	 * 2 AND A.SVTP_CDSERVICOTIPO = ("O" OR "C") AND
	 * (A.SVTP_NNTEMPOMEDIOEXECUCAO >= 0000 AND A.SVTP_NNTEMPOMEDIOEXECUCAO <=
	 * 9999) AND DBTP_ID = ID INFORMADO AND AND CRTP_ID = ID INFORMADO AND
	 * STSG_ID = ID INFORMADO AND STRF_ID = ID INFORMADO AND STPR_ID = ID
	 * INFORMADO AND A.SVTP_ID = B.SVTP_ID AND B.ATIV_ID IN (ID's INFORMADOS)
	 * AND A.SVTP_ID = C.SVTP_ID AND C.MATE_ID IN (ID's INFORMADOS)
	 * 
	 * @author Leandro Cavalcanti
	 * @date 17/08/2006
	 * 
	 */
	public Collection<ServicoTipo> filtrarST(ServicoTipo servicoTipo,
			Collection colecaoAtividades, Collection colecaoMaterial,
			String valorServicoInicial, String valorServicoFinal,
			String tempoMedioExecucaoInicial, String tempoMedioExecucaoFinal,
			String tipoPesquisa, String tipoPesquisaAbreviada,
			Integer numeroPaginasPesquisa) throws ErroRepositorioException {

		Collection<ServicoTipo> retorno = null;
		Session session = HibernateUtil.getSession();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = "SELECT DISTINCT(svtp.id),svtp.descricao,"
					+ "svtp.servicoTipoPrioridade.descricao,"
					+ "svtp.indicadorAtualizaComercial,"
					+ "svtp.indicadorPavimentoRua,"
					+ "svtp.indicadorPavimentoCalcada,"
					+ "svtp.indicadorTerceirizado" + " FROM ServicoTipo svtp  "
					+ " LEFT JOIN svtp.servicoTipoSubgrupo subg "
					+ " LEFT JOIN svtp.debitoTipo tpdb"
					+ " LEFT JOIN svtp.creditoTipo tpcd "
					+ " LEFT JOIN svtp.servicoTipoPrioridade tppri "
					+ " LEFT JOIN svtp.servicoTipoReferencia tpref  "
					+ " LEFT JOIN svtp.servicoPerfilTipo perftp "
					+ " LEFT JOIN svtp.servicoTipoAtividades srvtpatv "
					+ " LEFT JOIN svtp.servicoTipoMateriais srvtpmat "
					+ " WHERE 1=1 and svtp.indicadorUso = "
					+ ConstantesSistema.INDICADOR_USO_ATIVO;

			if (servicoTipo.getDescricao() != null
					&& !servicoTipo.getDescricao().equals("")) {
				if (tipoPesquisa == null || tipoPesquisa.trim().equals("1")
						|| tipoPesquisa.equals("")) {
					consulta += " AND svtp.descricao LIKE '"
							+ servicoTipo.getDescricao().toUpperCase() + "%' ";
				} else {
					consulta += " AND svtp.descricao LIKE '%"
							+ servicoTipo.getDescricao().toUpperCase() + "%' ";
				}

			}
			if (servicoTipo.getDescricaoAbreviada() != null
					&& !servicoTipo.getDescricaoAbreviada().equals("")) {
				if (tipoPesquisaAbreviada == null
						|| tipoPesquisaAbreviada.trim().equals("1")
						|| tipoPesquisaAbreviada.equals("")) {
					consulta += " AND svtp.descricaoAbreviada LIKE '"
							+ servicoTipo.getDescricaoAbreviada().toUpperCase()
							+ "%' ";
				} else {
					consulta += " AND svtp.descricaoAbreviada LIKE '%"
							+ servicoTipo.getDescricaoAbreviada().toUpperCase()
							+ "%' ";
				}
			}

			if (servicoTipo.getServicoTipoSubgrupo() != null
					&& !servicoTipo.getServicoTipoSubgrupo().equals("")) {
				consulta += " AND subg.id = (:idSubg) ";
				parameters.put("idSubg", servicoTipo.getServicoTipoSubgrupo()
						.getId());
			}

			/*
			 * if (new Short(servicoTipo.getIndicadorPavimento()) != 0 && new
			 * Short(servicoTipo.getIndicadorPavimento()) != 3) { consulta +=
			 * " AND svtp.indicadorPavimento = (:idIndpv) ";
			 * parameters.put("idIndpv", servicoTipo.getIndicadorPavimento()); }
			 */

			// Indicador de pavimento de rua
			if (servicoTipo.getIndicadorPavimentoRua() != null
					&& new Short(servicoTipo.getIndicadorPavimentoRua()) != 0
					&& new Short(servicoTipo.getIndicadorPavimentoRua()) != 3) {
				consulta += " AND svtp.indicadorPavimentoRua = (:idIndpvRua) ";
				parameters.put("idIndpvRua",
						servicoTipo.getIndicadorPavimentoRua());
			}

			// Indicador de pavimento de calada
			if (servicoTipo.getIndicadorPavimentoCalcada() != null
					&& new Short(servicoTipo.getIndicadorPavimentoCalcada()) != 0
					&& new Short(servicoTipo.getIndicadorPavimentoCalcada()) != 3) {
				consulta += " AND svtp.indicadorPavimentoCalcada = (:idIndpvCalcada) ";
				parameters.put("idIndpvCalcada",
						servicoTipo.getIndicadorPavimentoCalcada());
			}

			if (!valorServicoInicial.equalsIgnoreCase("")
					&& !valorServicoFinal.equalsIgnoreCase("")) {
				consulta += " AND svtp.valor BETWEEN (:valorInicial) AND (:valorFinal) ";
				parameters.put("valorInicial", valorServicoInicial);
				parameters.put("valorFinal", valorServicoFinal);
			}

			if (new Short(servicoTipo.getIndicadorAtualizaComercial()) != 0) {

				if (servicoTipo.getIndicadorAtualizaComercial() == 1) {
					consulta += " AND svtp.indicadorAtualizaComercial in (1,3) ";

				} else if (servicoTipo.getIndicadorAtualizaComercial() == 2) {
					consulta += " AND svtp.indicadorAtualizaComercial = 1 ";

				} else if (servicoTipo.getIndicadorAtualizaComercial() == 3) {
					consulta += " AND svtp.indicadorAtualizaComercial = 3 ";

				} else if (servicoTipo.getIndicadorAtualizaComercial() == 4) {
					consulta += " AND svtp.indicadorAtualizaComercial in (1,2,3)  ";
				} else if (servicoTipo.getIndicadorAtualizaComercial() == 5) {
					consulta += " AND svtp.indicadorAtualizaComercial = 2 ";
				}
			}

			if (servicoTipo.getIndicadorTerceirizado() != 0) {
				consulta += " AND svtp.indicadorTerceirizado = "
						+ servicoTipo.getIndicadorTerceirizado();
			}

			if (servicoTipo.getCodigoServicoTipo() != null
					&& !servicoTipo.getCodigoServicoTipo().equals("")
					&& !servicoTipo.getCodigoServicoTipo().equals("3")) {
				consulta += " AND svtp.codigoServicoTipo = (:codSvtp) ";
				if (servicoTipo.getCodigoServicoTipo().toString().equals("1")) {
					parameters.put("codSvtp", "O");
				} else {
					parameters.put("codSvtp", "C");
				}
			}

			if (!tempoMedioExecucaoInicial.equalsIgnoreCase("")
					&& !tempoMedioExecucaoFinal.equalsIgnoreCase("")) {
				consulta += " AND svtp.tempoMedioExecucao BETWEEN (:tempoInicial) AND (:tempoFinal) ";
				parameters.put("tempoInicial", tempoMedioExecucaoInicial);
				parameters.put("tempoFinal", tempoMedioExecucaoFinal);

			} else if (!tempoMedioExecucaoInicial.equalsIgnoreCase("")
					&& tempoMedioExecucaoFinal.equalsIgnoreCase("")) {
				consulta += " AND svtp.tempoMedioExecucao BETWEEN (:tempoInicial) AND (:tempoFinal) ";
				parameters.put("tempoInicial", tempoMedioExecucaoInicial);
				parameters.put("tempoFinal", 9999);
			} else if (!tempoMedioExecucaoFinal.equalsIgnoreCase("")
					&& tempoMedioExecucaoInicial.equalsIgnoreCase("")) {
				consulta += " AND svtp.tempoMedioExecucao BETWEEN (:tempoInicial) AND (:tempoFinal) ";
				parameters.put("tempoInicial", 0000);
				parameters.put("tempoFinal", tempoMedioExecucaoFinal);
			}
			if (servicoTipo.getDebitoTipo() != null) {
				if (servicoTipo.getDebitoTipo().getId() != null
						&& !servicoTipo.getDebitoTipo().getId().equals("")) {
					consulta += " AND tpdb.id = (:idDeb) ";
					parameters
					.put("idDeb", servicoTipo.getDebitoTipo().getId());
				}
			}
			if (servicoTipo.getCreditoTipo() != null) {
				if (servicoTipo.getCreditoTipo().getId() != null
						&& !servicoTipo.getCreditoTipo().getId().equals("")) {
					consulta += " AND tpcd.id = (:idTpcd) ";
					parameters.put("idTpcd", servicoTipo.getCreditoTipo()
							.getId());
				}
			}
			if (servicoTipo.getServicoTipoPrioridade() != null) {
				if (servicoTipo.getServicoTipoPrioridade().getId() != null
						&& !servicoTipo.getServicoTipoPrioridade().getId()
						.equals("")) {
					consulta += " AND tppri.id = (:idTpcd) ";
					parameters.put("idTpcd", servicoTipo
							.getServicoTipoPrioridade().getId());
				}
			}
			if (servicoTipo.getServicoPerfilTipo() != null) {
				if (servicoTipo.getServicoPerfilTipo().getId() != null
						&& !servicoTipo.getServicoPerfilTipo().getId()
						.equals("")) {
					consulta += " AND perftp.id = (:idPerf) ";
					parameters.put("idPerf", servicoTipo.getServicoPerfilTipo()
							.getId());
				}
			}
			if (servicoTipo.getServicoTipoReferencia() != null) {
				if (servicoTipo.getServicoTipoReferencia().getId() != null
						&& !servicoTipo.getServicoTipoReferencia().getId()
						.equals("")) {
					consulta += " AND tpref.id = (:idRef) ";
					parameters.put("idRef", servicoTipo
							.getServicoTipoReferencia().getId());
				}
			}

			if (colecaoMaterial != null && !colecaoMaterial.isEmpty()) {

				consulta += " AND srvtpmat.comp_id.idMaterial in (:idMat) ";
				parameters.put("idMat", colecaoMaterial);
			}

			if (colecaoAtividades != null && !colecaoAtividades.isEmpty()) {

				consulta += " AND srvtpatv.comp_id.idAtividade in (:idAt) ";
				parameters.put("idAt", colecaoAtividades);
			}

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.setFirstResult(10 * numeroPaginasPesquisa)
					.setMaxResults(10).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * [UC0413] Pesquisar Tipo de Servio
	 * 
	 * Faz o count para saber a quantidade total de registros retornados
	 * 
	 * @author Flvio
	 * @date 17/08/2006
	 * 
	 */

	public Integer filtrarSTCount(ServicoTipo servicoTipo,
			Collection colecaoAtividades, Collection colecaoMaterial,
			String valorServicoInicial, String valorServicoFinal,
			String tempoMedioExecucaoInicial, String tempoMedioExecucaoFinal,
			String tipoPesquisa, String tipoPesquisaAbreviada)
					throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = "SELECT (count(distinct svtp.id)) "
					+ " FROM ServicoTipo svtp "
					+ " LEFT JOIN svtp.servicoTipoSubgrupo subg "
					+ " LEFT JOIN svtp.debitoTipo tpdb"
					+ " LEFT JOIN svtp.creditoTipo tpcd "
					+ " LEFT JOIN svtp.servicoTipoPrioridade tppri "
					+ " LEFT JOIN svtp.servicoTipoReferencia tpref  "
					+ " LEFT JOIN svtp.servicoPerfilTipo perftp "
					+ " LEFT JOIN svtp.servicoTipoAtividades srvtpatv "
					+ " LEFT JOIN svtp.servicoTipoMateriais srvtpmat "
					+ " WHERE 1=1 and svtp.indicadorUso = "
					+ ConstantesSistema.INDICADOR_USO_ATIVO;

			if (servicoTipo.getDescricao() != null
					&& !servicoTipo.getDescricao().equals("")) {
				if (tipoPesquisa == null || tipoPesquisa.trim().equals("1")
						|| tipoPesquisa.equals("")) {
					consulta += " AND svtp.descricao LIKE '"
							+ servicoTipo.getDescricao().toUpperCase() + "%' ";
				} else {
					consulta += " AND svtp.descricao LIKE '%"
							+ servicoTipo.getDescricao().toUpperCase() + "%' ";
				}

			}
			if (servicoTipo.getDescricaoAbreviada() != null
					&& !servicoTipo.getDescricaoAbreviada().equals("")) {
				if (tipoPesquisaAbreviada == null
						|| tipoPesquisaAbreviada.trim().equals("1")
						|| tipoPesquisaAbreviada.equals("")) {
					consulta += " AND svtp.descricaoAbreviada LIKE '"
							+ servicoTipo.getDescricaoAbreviada().toUpperCase()
							+ "%' ";
				} else {
					consulta += " AND svtp.descricaoAbreviada LIKE '%"
							+ servicoTipo.getDescricaoAbreviada().toUpperCase()
							+ "%' ";
				}
			}

			if (servicoTipo.getServicoTipoSubgrupo() != null
					&& !servicoTipo.getServicoTipoSubgrupo().equals("")) {
				consulta += " AND subg.id = (:idSubg) ";
				parameters.put("idSubg", servicoTipo.getServicoTipoSubgrupo()
						.getId());
			}

			// if (new Short(servicoTipo.getIndicadorPavimento()) != 0
			// && new Short(servicoTipo.getIndicadorPavimento()) != 3) {
			// consulta += " AND svtp.indicadorPavimento = (:idIndpv) ";
			// parameters.put("idIndpv", servicoTipo.getIndicadorPavimento());
			// }

			// Indicador de pavimento de rua
			if (servicoTipo.getIndicadorPavimentoRua() != null
					&& new Short(servicoTipo.getIndicadorPavimentoRua()) != 0
					&& new Short(servicoTipo.getIndicadorPavimentoRua()) != 3) {
				consulta += " AND svtp.indicadorPavimentoRua = (:idIndpvRua) ";
				parameters.put("idIndpvRua",
						servicoTipo.getIndicadorPavimentoRua());
			}

			// Indicador de pavimento de calada
			if (servicoTipo.getIndicadorPavimentoCalcada() != null
					&& new Short(servicoTipo.getIndicadorPavimentoCalcada()) != 0
					&& new Short(servicoTipo.getIndicadorPavimentoCalcada()) != 3) {
				consulta += " AND svtp.indicadorPavimentoCalcada = (:idIndpvCalcada) ";
				parameters.put("idIndpvCalcada",
						servicoTipo.getIndicadorPavimentoCalcada());
			}

			if (!valorServicoInicial.equalsIgnoreCase("")
					&& !valorServicoFinal.equalsIgnoreCase("")) {
				consulta += " AND svtp.valor BETWEEN (:valorInicial) AND (:valorFinal) ";
				parameters.put("valorInicial", valorServicoInicial);
				parameters.put("valorFinal", valorServicoFinal);
			}

			if (new Short(servicoTipo.getIndicadorAtualizaComercial()) != 0) {

				if (servicoTipo.getIndicadorAtualizaComercial() == 1) {
					consulta += " AND svtp.indicadorAtualizaComercial in (1,3) ";

				} else if (servicoTipo.getIndicadorAtualizaComercial() == 2) {
					consulta += " AND svtp.indicadorAtualizaComercial = 1 ";

				} else if (servicoTipo.getIndicadorAtualizaComercial() == 3) {
					consulta += " AND svtp.indicadorAtualizaComercial = 3 ";

				} else if (servicoTipo.getIndicadorAtualizaComercial() == 4) {
					consulta += " AND svtp.indicadorAtualizaComercial in (1,2,3)  ";
				} else if (servicoTipo.getIndicadorAtualizaComercial() == 5) {
					consulta += " AND svtp.indicadorAtualizaComercial = 2 ";
				}
			}

			if (servicoTipo.getIndicadorTerceirizado() != 0) {
				consulta += " AND svtp.indicadorTerceirizado = "
						+ servicoTipo.getIndicadorTerceirizado();
			}

			if (servicoTipo.getCodigoServicoTipo() != null
					&& !servicoTipo.getCodigoServicoTipo().equals("")
					&& !servicoTipo.getCodigoServicoTipo().equals("3")) {
				consulta += " AND svtp.codigoServicoTipo = (:codSvtp) ";
				if (servicoTipo.getCodigoServicoTipo().toString().equals("1")) {
					parameters.put("codSvtp", "O");
				} else {
					parameters.put("codSvtp", "C");
				}
			}

			if (!tempoMedioExecucaoInicial.equalsIgnoreCase("")
					&& !tempoMedioExecucaoFinal.equalsIgnoreCase("")) {
				consulta += " AND svtp.tempoMedioExecucao BETWEEN (:tempoInicial) AND (:tempoFinal) ";
				parameters.put("tempoInicial", tempoMedioExecucaoInicial);
				parameters.put("tempoFinal", tempoMedioExecucaoFinal);

			} else if (!tempoMedioExecucaoInicial.equalsIgnoreCase("")
					&& tempoMedioExecucaoFinal.equalsIgnoreCase("")) {
				consulta += " AND svtp.tempoMedioExecucao BETWEEN (:tempoInicial) AND (:tempoFinal) ";
				parameters.put("tempoInicial", tempoMedioExecucaoInicial);
				parameters.put("tempoFinal", 9999);
			} else if (!tempoMedioExecucaoFinal.equalsIgnoreCase("")
					&& tempoMedioExecucaoInicial.equalsIgnoreCase("")) {
				consulta += " AND svtp.tempoMedioExecucao BETWEEN (:tempoInicial) AND (:tempoFinal) ";
				parameters.put("tempoInicial", 0000);
				parameters.put("tempoFinal", tempoMedioExecucaoFinal);
			}
			if (servicoTipo.getDebitoTipo() != null) {
				if (servicoTipo.getDebitoTipo().getId() != null
						&& !servicoTipo.getDebitoTipo().getId().equals("")) {
					consulta += " AND tpdb.id = (:idDeb) ";
					parameters
					.put("idDeb", servicoTipo.getDebitoTipo().getId());
				}
			}
			if (servicoTipo.getCreditoTipo() != null) {
				if (servicoTipo.getCreditoTipo().getId() != null
						&& !servicoTipo.getCreditoTipo().getId().equals("")) {
					consulta += " AND tpcd.id = (:idTpcd) ";
					parameters.put("idTpcd", servicoTipo.getCreditoTipo()
							.getId());
				}
			}
			if (servicoTipo.getServicoTipoPrioridade() != null) {
				if (servicoTipo.getServicoTipoPrioridade().getId() != null
						&& !servicoTipo.getServicoTipoPrioridade().getId()
						.equals("")) {
					consulta += " AND tppri.id = (:idTpcd) ";
					parameters.put("idTpcd", servicoTipo
							.getServicoTipoPrioridade().getId());
				}
			}
			if (servicoTipo.getServicoPerfilTipo() != null) {
				if (servicoTipo.getServicoPerfilTipo().getId() != null
						&& !servicoTipo.getServicoPerfilTipo().getId()
						.equals("")) {
					consulta += " AND perftp.id = (:idPerf) ";
					parameters.put("idPerf", servicoTipo.getServicoPerfilTipo()
							.getId());
				}
			}
			if (servicoTipo.getServicoTipoReferencia() != null) {
				if (servicoTipo.getServicoTipoReferencia().getId() != null
						&& !servicoTipo.getServicoTipoReferencia().getId()
						.equals("")) {
					consulta += " AND tpref.id = (:idRef) ";
					parameters.put("idRef", servicoTipo
							.getServicoTipoReferencia().getId());
				}
			}

			if (colecaoMaterial != null && !colecaoMaterial.isEmpty()) {

				consulta += " AND srvtpmat.comp_id.idMaterial in (:idMat) ";
				parameters.put("idMat", colecaoMaterial);
			}

			if (colecaoAtividades != null && !colecaoAtividades.isEmpty()) {

				consulta += " AND srvtpatv.comp_id.idAtividade in (:idAt) ";
				parameters.put("idAt", colecaoAtividades);
			}

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * [UC413]- Pesquisar Tipo de Servio
	 * 
	 * Pesquisar o Objeto servicoTipo referente ao idTiposervico recebido na
	 * descrio da pesquisa, onde o mesmo sera detalhado.
	 * 
	 * @author Leandro Cavalcanti
	 * @date 23/08/2006
	 * 
	 * @param idImovel
	 * @param idSolicitacaoTipoEspecificacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarServicoTipo(Integer idTipoServico)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT svtp.descricao, " + "svtp.descricaoAbreviada, "
					+ " sts.descricao, " + "svtp.indicadorPavimento, "
					+ "svtp.valor, " + "svtp.indicadorAtualizaComercial, "
					+ "svtp.indicadorTerceirizado, "
					+ "svtp.codigoServicoTipo, " + "svtp.tempoMedioExecucao, "
					+ "dt.descricao, " + "ct.descricao, " + "stp.descricao, "
					+ "spt.descricao, " + "stri.descricao "
					+ "FROM ServicoTipo svtp "
					+ "LEFT JOIN svtp.servicoTipoReferencia stri "
					+ "LEFT JOIN svtp.creditoTipo ct "
					+ "LEFT JOIN svtp.servicoPerfilTipo spt "
					+ "LEFT JOIN svtp.servicoTipoSubgrupo sts "
					+ "LEFT JOIN svtp.servicoTipoPrioridade stp "
					+ "LEFT JOIN svtp.debitoTipo dt "
					+ "WHERE svtp.id = :idTipoServico";

			retorno = (Object[]) session.createQuery(consulta)
					.setInteger("idTipoServico", idTipoServico).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisar Materiais pelo idServicoTipo na tabela de ServicoTipoMaterial
	 * 
	 * Recupera os materiais do servico tipo material
	 * 
	 * @author Leandro Cavalcanti
	 * @date 24/08/2006
	 * 
	 * @param Integer
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Collection recuperarMaterialServicoTipoConsulta(
			Integer idServicoTipoMaterial) throws ErroRepositorioException {

		Collection retornoConsulta = null;
		Collection retorno = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			if (idServicoTipoMaterial != null) {
				consulta = "SELECT mat.descricao,"
						+ "svtpmat.quantidadePadrao "
						+ "FROM ServicoTipoMaterial svtpmat "
						+ "LEFT JOIN svtpmat.material mat "
						+ "WHERE svtpmat.comp_id.idServicoTipo = :idServicoTipoMaterial";

				retornoConsulta = (Collection) session
						.createQuery(consulta)
						.setInteger("idServicoTipoMaterial",
								idServicoTipoMaterial).list();

				if (retornoConsulta.size() > 0) {

					retorno = new ArrayList();

					ServicoTipoMaterial servicoTipoMaterial = null;
					Material material = null;

					for (Iterator iter = retornoConsulta.iterator(); iter
							.hasNext();) {

						Object[] element = (Object[]) iter.next();

						servicoTipoMaterial = new ServicoTipoMaterial();
						servicoTipoMaterial
						.setQuantidadePadrao((BigDecimal) element[1]);

						material = new Material();
						material.setDescricao((String) element[0]);

						servicoTipoMaterial.setMaterial(material);

						retorno.add(servicoTipoMaterial);
					}

				}

			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisar Atividades pelo idServicoTipo na tabela de ServicoTipoAtividade
	 * 
	 * Recupera os Atividades do servico tipo Atividade
	 * 
	 * @author Leandro Cavalcanti
	 * @date 24/08/2006
	 * 
	 * @param Integer
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Collection recuperarAtividadeServicoTipoConsulta(
			Integer idServicoTipoAtividade) throws ErroRepositorioException {

		Collection retornoConsulta = null;
		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			if (idServicoTipoAtividade != null) {
				consulta = "SELECT at.descricao,"
						+ "svtpat.numeroExecucao "
						+ "FROM ServicoTipoAtividade svtpat "
						+ "LEFT JOIN svtpat.atividade at "
						+ "WHERE svtpat.comp_id.idServicoTipo = :idServicoTipoAtividade";

				retornoConsulta = (Collection) session
						.createQuery(consulta)
						.setInteger("idServicoTipoAtividade",
								idServicoTipoAtividade).list();

				if (retornoConsulta.size() > 0) {

					retorno = new ArrayList();

					ServicoTipoAtividade servicoTipoAtividade = null;
					Atividade atividade = null;

					for (Iterator iter = retornoConsulta.iterator(); iter
							.hasNext();) {

						Object[] element = (Object[]) iter.next();

						servicoTipoAtividade = new ServicoTipoAtividade();
						servicoTipoAtividade
						.setNumeroExecucao((Short) element[1]);

						atividade = new Atividade();
						atividade.setDescricao((String) element[0]);

						servicoTipoAtividade.setAtividade(atividade);

						retorno.add(servicoTipoAtividade);
					}

				}
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Servio
	 * 
	 * @author lms
	 * @date 14/08/2006
	 */
	public boolean existeOrdemServicoDiferenteEncerrado(
			Integer idregistroAtendimento) throws ErroRepositorioException {
		boolean retorno = false;
		Session session = HibernateUtil.getSession();
		try {
			String consulta = "select os from OrdemServico os where os.registroAtendimento.id = "
					+ idregistroAtendimento
					+ " and os.situacao <> "
					+ OrdemServico.SITUACAO_ENCERRADO;
			Collection collection = session.createQuery(consulta).list();
			if (collection != null && !collection.isEmpty()) {
				retorno = true;
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Servio
	 * 
	 * @author lms
	 * @date 14/08/2006
	 */
	public OrdemServico pesquisarOrdemServicoDiferenteEncerrado(
			Integer idregistroAtendimento, Integer idServicoTipo)
					throws ErroRepositorioException {

		OrdemServico ordemServico = null;
		Object[] retornoConsulta = null;

		Session session = HibernateUtil.getSession();
		try {

			String consulta = "SELECT os.id,"// 0
					+ "servicoTipo.id,"// 1
					+ "servicoTipo.descricao, "// 2
					+ "ra.id "// 3
					+ "FROM OrdemServico os "
					+ "INNER JOIN os.registroAtendimento ra "
					+ "INNER JOIN os.servicoTipo servicoTipo "
					+ "WHERE servicoTipo.id = :idServicoTipo "
					+ "AND ra.id = :idregistroAtendimento "
					+ "AND os.situacao <> :situacao";

			retornoConsulta = (Object[]) session.createQuery(consulta)
					.setInteger("idServicoTipo", idServicoTipo)
					.setInteger("idregistroAtendimento", idregistroAtendimento)
					.setShort("situacao", OrdemServico.SITUACAO_ENCERRADO)
					.setMaxResults(1).uniqueResult();

			if (retornoConsulta != null) {

				ordemServico = new OrdemServico();
				ordemServico.setId((Integer) retornoConsulta[0]);

				ServicoTipo servico = new ServicoTipo();
				servico.setId((Integer) retornoConsulta[1]);
				servico.setDescricao((String) retornoConsulta[2]);

				RegistroAtendimento ra = new RegistroAtendimento();
				ra.setId((Integer) retornoConsulta[3]);

				ordemServico.setServicoTipo(servico);
				ordemServico.setRegistroAtendimento(ra);

			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return ordemServico;
	}

	/**
	 * [UC0430] Gerar Ordem de Servio
	 * 
	 * @author lms
	 * @date 17/08/2006
	 */
	public ServicoTipo pesquisarSevicoTipo(Integer id)
			throws ErroRepositorioException {
		ServicoTipo retorno = null;
		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT svtp "
					+ "FROM ServicoTipo svtp "
					+ " LEFT OUTER JOIN FETCH svtp.servicoTipoPrioridade svtpri "
					+ " LEFT OUTER JOIN FETCH svtp.servicoTipoReferencia svtr "
					+ " LEFT OUTER JOIN FETCH svtr.servicoTipo "
					+ " LEFT OUTER JOIN FETCH svtp.programaCalibragem "
					+ "WHERE svtp.id = " + id + " and svtp.indicadorUso = "
					+ ConstantesSistema.INDICADOR_USO_ATIVO;

			retorno = (ServicoTipo) session.createQuery(consulta)
					.uniqueResult();

			// if (retorno != null) {
			// Hibernate.initialize(retorno.getServicoTipoPrioridade());
			// Hibernate.initialize(retorno.getServicoTipoReferencia());
			// if (retorno.getServicoTipoReferencia() != null) {
			// Hibernate.initialize(retorno.getServicoTipoReferencia()
			// .getServicoTipo());
			// }
			// }

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto,Vivianne Sousa
	 * @date 04/09/2006,13/06/2008
	 */
	public Collection<ServicoTipo> pesquisarServicoTipoPorUnidade(
			Integer unidadeLotacao) throws ErroRepositorioException {

		Collection<ServicoTipo> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			// consulta = "SELECT DISTINCT svtp "
			// + "FROM OrdemServicoUnidade osUnidade "
			// + "INNER JOIN osUnidade.atendimentoRelacaoTipo art  "
			// + "INNER JOIN osUnidade.ordemServico os  "
			// + "INNER JOIN osUnidade.unidadeOrganizacional unid  "
			// + "INNER JOIN os.servicoTipo svtp  "
			// + "LEFT JOIN os.registroAtendimento ra  "
			// + "WHERE ra IS NULL " + "AND unid.id = :unidadeLotacao "
			// + "AND os.situacao in (1,3) "
			// + "AND art.id = :idAtendimentoTipo "
			// + "ORDER BY svtp.descricao";

			// incluso da coluna unidade atual nas tabelas
			// REGISTRO_ATENDIMENTO e ORDEM_SERVICO
			// Vivianne Sousa 13/06/2008 analista:Ftima Sampaio

			consulta = "SELECT DISTINCT svtp " + "FROM OrdemServico os  "
					+ "INNER JOIN os.unidadeAtual unid  "
					+ "INNER JOIN os.servicoTipo svtp  "
					+ "LEFT JOIN os.registroAtendimento ra  "
					+ "WHERE ra IS NULL " + "AND unid.id = :unidadeLotacao "
					+ "AND os.situacao in (1,3) " + "ORDER BY svtp.descricao";

			retornoConsulta = session.createQuery(consulta)
					.setInteger("unidadeLotacao", unidadeLotacao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<ServicoTipo> pesquisarServicoTipoPorRA(
			Collection<Integer> idsRa) throws ErroRepositorioException {

		Collection<ServicoTipo> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT DISTINCT svtp " + "FROM OrdemServico os "
					+ "INNER JOIN os.servicoTipo svtp  "
					+ "INNER JOIN os.registroAtendimento ragt  "
					+ "WHERE os.situacao in (1,3) "
					+ "AND ragt.id in (:idsRa) " + "ORDER BY svtp.descricao";

			if (idsRa.size() > 999) {

				System.out.println("## TAMANHO TOTAL = " + idsRa.size());

				List<List<Integer>> particoes = CollectionUtil.particao(
						(List<Integer>) idsRa, 999);

				int qtdQuebras = 999;
				int indice = idsRa.size() / qtdQuebras;
				if (idsRa.size() % qtdQuebras != 0) {
					indice++;
				}

				System.out.println("## QUANTIDADE PARTIES = " + indice);

				for (int i = 0; i < indice; i++) {

					System.out.println("## TAMANHO PARTIO DE INDICE  "
							+ indice + " = " + particoes.get(i).size());

					Collection<ServicoTipo> retornoConsultaParte = null;

					retornoConsultaParte = session.createQuery(consulta)
							.setParameterList("idsRa", particoes.get(i)).list();

					retornoConsulta.addAll(retornoConsultaParte);

				}
			} else {
				retornoConsulta = session.createQuery(consulta)
						.setParameterList("idsRa", idsRa).list();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<Localidade> pesquisarLocalidadePorRA(
			Collection<Integer> idsRa) throws ErroRepositorioException {

		Collection<Localidade> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT DISTINCT local " + "FROM OrdemServico os "
					+ "INNER JOIN os.registroAtendimento ragt  "
					+ "INNER JOIN ragt.localidade local  "
					+ "WHERE os.situacao in (1,3) "
					+ "AND ragt.id in (:idsRa) " + "ORDER BY local.descricao";

			if (idsRa.size() > 999) {

				System.out.println("## TAMANHO TOTAL = " + idsRa.size());

				List<List<Integer>> particoes = CollectionUtil.particao(
						(List<Integer>) idsRa, 999);

				int qtdQuebras = 999;
				int indice = idsRa.size() / qtdQuebras;
				if (idsRa.size() % qtdQuebras != 0) {
					indice++;
				}

				System.out.println("## QUANTIDADE PARTIES = " + indice);

				for (int i = 0; i < indice; i++) {

					System.out.println("## TAMANHO PARTO DE INDICE  "
							+ indice + " = " + particoes.get(i).size());

					Collection<Localidade> retornoConsultaParte = null;

					retornoConsultaParte = session.createQuery(consulta)
							.setParameterList("idsRa", particoes.get(i)).list();

					retornoConsulta.addAll(retornoConsultaParte);

				}
			} else {
				retornoConsulta = session.createQuery(consulta)
						.setParameterList("idsRa", idsRa).list();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<Localidade> pesquisarLocalidadePorUnidade(Integer unidade)
			throws ErroRepositorioException {

		Collection<Localidade> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			// consulta = "SELECT DISTINCT local "
			// + "FROM OrdemServicoUnidade osUnidade "
			// + "INNER JOIN osUnidade.atendimentoRelacaoTipo art  "
			// + "INNER JOIN osUnidade.ordemServico os  "
			// + "INNER JOIN osUnidade.unidadeOrganizacional unid  "
			// + "INNER JOIN os.cobrancaDocumento cobra  "
			// + "INNER JOIN cobra.localidade local  "
			// + "LEFT JOIN os.registroAtendimento ra  "
			// + "WHERE ra IS NULL " + "AND os.situacao in (1,3) "
			// + "AND unid.id = :unidade "
			// + "AND art.id = :idAtendimentoTipo "
			// + "ORDER BY local.descricao";

			// incluso da coluna unidade atual nas tabelas
			// REGISTRO_ATENDIMENTO e ORDEM_SERVICO
			// Vivianne Sousa 13/06/2008 analista:Ftima Sampaio

			consulta = "SELECT DISTINCT local " + "FROM OrdemServico os  "
					+ "INNER JOIN os.unidadeAtual unid  "
					+ "INNER JOIN os.cobrancaDocumento cobra  "
					+ "INNER JOIN cobra.localidade local  "
					+ "LEFT JOIN os.registroAtendimento ra  "
					+ "WHERE ra IS NULL " + "AND os.situacao in (1,3) "
					+ "AND unid.id = :unidade " + "ORDER BY local.descricao";

			retornoConsulta = session.createQuery(consulta)
					.setInteger("unidade", unidade).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto,Vivianne Sousa
	 * @date 04/09/2006,13/06/2008
	 */
	public Collection<SetorComercial> pesquisarSetorComercialPorUnidade(
			Integer unidade) throws ErroRepositorioException {

		Collection<SetorComercial> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			// consulta = "SELECT DISTINCT setorComercial "
			// + "FROM OrdemServicoUnidade osUnidade "
			// + "INNER JOIN osUnidade.atendimentoRelacaoTipo art  "
			// + "INNER JOIN osUnidade.ordemServico os  "
			// + "INNER JOIN osUnidade.unidadeOrganizacional unid  "
			// + "INNER JOIN os.cobrancaDocumento cobra  "
			// + "INNER JOIN cobra.quadra quadra  "
			// + "INNER JOIN quadra.setorComercial setorComercial  "
			// + "LEFT JOIN os.registroAtendimento ra  "
			// + "WHERE ra IS NULL " + "AND os.situacao in (1,3) "
			// + "AND unid.id = :unidade "
			// + "AND art.id = :idAtendimentoTipo "
			// + "ORDER BY setorComercial.descricao";

			// incluso da coluna unidade atual nas tabelas
			// REGISTRO_ATENDIMENTO e ORDEM_SERVICO
			// Vivianne Sousa 13/06/2008 analista:Ftima Sampaio

			consulta = "SELECT DISTINCT setorComercial "
					+ "FROM OrdemServico os  "
					+ "INNER JOIN os.unidadeAtual unid  "
					+ "INNER JOIN os.cobrancaDocumento cobra  "
					+ "INNER JOIN cobra.quadra quadra  "
					+ "INNER JOIN quadra.setorComercial setorComercial  "
					+ "LEFT JOIN os.registroAtendimento ra  "
					+ "WHERE ra IS NULL " + "AND os.situacao in (1,3) "
					+ "AND unid.id = :unidade "
					+ "ORDER BY setorComercial.descricao";

			retornoConsulta = session.createQuery(consulta)
					.setInteger("unidade", unidade).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<SetorComercial> pesquisarSetorComercialPorRA(
			Collection<Integer> idsRa) throws ErroRepositorioException {

		Collection<SetorComercial> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT DISTINCT setorComercial "
					+ "FROM OrdemServico os "
					+ "INNER JOIN os.registroAtendimento ragt  "
					+ "INNER JOIN ragt.quadra quadra  "
					+ "INNER JOIN quadra.setorComercial setorComercial  "
					+ "WHERE os.situacao in (1,3) "
					+ "AND ragt.id in (:idsRa) "
					+ "ORDER BY setorComercial.descricao";

			if (idsRa.size() > 999) {

				System.out.println("## TAMANHO TOTAL = " + idsRa.size());

				List<List<Integer>> particoes = CollectionUtil.particao(
						(List<Integer>) idsRa, 999);

				int qtdQuebras = 999;
				int indice = idsRa.size() / qtdQuebras;
				if (idsRa.size() % qtdQuebras != 0) {
					indice++;
				}

				System.out.println("## QUANTIDADE PARTIES = " + indice);

				for (int i = 0; i < indice; i++) {

					System.out.println("## TAMANHO PARTIO DE INDICE  "
							+ indice + " = " + particoes.get(i).size());

					Collection<SetorComercial> retornoConsultaParte = null;

					retornoConsultaParte = session.createQuery(consulta)
							.setParameterList("idsRa", particoes.get(i)).list();

					retornoConsulta.addAll(retornoConsultaParte);

				}
			} else {
				retornoConsulta = session.createQuery(consulta)
						.setParameterList("idsRa", idsRa).list();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto, Raphael Rossiter
	 * @date 04/09/2006, 22/05/2009
	 * 
	 * @param idsRa
	 * @return Collection<DistritoOperacional>
	 * @throws ErroRepositorioException
	 */
	public Collection<DistritoOperacional> pesquisarDistritoOperacionalPorRAPelaQuadra(
			Collection<Integer> idsRa) throws ErroRepositorioException {

		Collection<Object[]> retornoConsulta = new ArrayList();
		Collection<DistritoOperacional> retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT DISTINCT distritoOperacional.id," // 0
					+ "distritoOperacional.descricao " // 1
					+ "FROM OrdemServico os "
					+ "INNER JOIN os.registroAtendimento ragt  "
					+ "INNER JOIN ragt.quadra quadra  "
					+ "INNER JOIN quadra.distritoOperacional distritoOperacional  "
					+ "WHERE os.situacao in (1,3) "
					+ "AND ragt.id in (:idsRa) "
					+ "ORDER BY distritoOperacional.descricao";

			if (idsRa.size() > 999) {

				System.out.println("## TAMANHO TOTAL = " + idsRa.size());

				List<List<Integer>> particoes = CollectionUtil.particao(
						(List<Integer>) idsRa, 999);

				int qtdQuebras = 999;
				int indice = idsRa.size() / qtdQuebras;
				if (idsRa.size() % qtdQuebras != 0) {
					indice++;
				}

				System.out.println("## QUANTIDADE PARTIES = " + indice);

				for (int i = 0; i < indice; i++) {

					System.out.println("## TAMANHO PARTIO DE INDICE  "
							+ indice + " = " + particoes.get(i).size());

					Collection<Object[]> retornoConsultaParte = null;

					retornoConsultaParte = session.createQuery(consulta)
							.setParameterList("idsRa", particoes.get(i)).list();

					retornoConsulta.addAll(retornoConsultaParte);

				}
			} else {
				retornoConsulta = session.createQuery(consulta)
						.setParameterList("idsRa", idsRa).list();
			}

			if (retornoConsulta.size() > 0) {

				retorno = new ArrayList();

				DistritoOperacional distritoOperacional = null;

				for (Iterator iter = retornoConsulta.iterator(); iter.hasNext();) {

					Object[] element = (Object[]) iter.next();

					distritoOperacional = new DistritoOperacional();

					distritoOperacional.setId((Integer) element[0]);
					distritoOperacional.setDescricao((String) element[1]);

					retorno.add(distritoOperacional);
				}

			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto, Raphael Rossiter
	 * @date 04/09/2006, 22/05/2009
	 * 
	 * @param idsRa
	 * @return Collection<DistritoOperacional>
	 * @throws ErroRepositorioException
	 */
	public Collection<DistritoOperacional> pesquisarDistritoOperacionalPorRAPelaQuadraFace(
			Collection<Integer> idsRa) throws ErroRepositorioException {

		Collection<Object[]> retornoConsulta = new ArrayList();
		Collection<DistritoOperacional> retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT DISTINCT distritoOperacional.id," // 0
					+ "distritoOperacional.descricao " // 1
					+ "FROM OrdemServico os "
					+ "INNER JOIN os.registroAtendimento ragt  "
					+ "INNER JOIN ragt.quadraFace quadraFace  "
					+ "INNER JOIN quadraFace.distritoOperacional distritoOperacional  "
					+ "WHERE os.situacao in (1,3) "
					+ "AND ragt.id in (:idsRa) "
					+ "ORDER BY distritoOperacional.descricao";

			if (idsRa.size() > 999) {

				System.out.println("## TAMANHO TOTAL = " + idsRa.size());

				List<List<Integer>> particoes = CollectionUtil.particao(
						(List<Integer>) idsRa, 999);

				int qtdQuebras = 999;
				int indice = idsRa.size() / qtdQuebras;
				if (idsRa.size() % qtdQuebras != 0) {
					indice++;
				}

				System.out.println("## QUANTIDADE PARTIES = " + indice);

				for (int i = 0; i < indice; i++) {

					System.out.println("## TAMANHO PARTIO DE INDICE  "
							+ indice + " = " + particoes.get(i).size());

					Collection<Object[]> retornoConsultaParte = null;

					retornoConsultaParte = session.createQuery(consulta)
							.setParameterList("idsRa", particoes.get(i)).list();

					retornoConsulta.addAll(retornoConsultaParte);

				}
			} else {
				retornoConsulta = session.createQuery(consulta)
						.setParameterList("idsRa", idsRa).list();
			}

			if (retornoConsulta.size() > 0) {

				retorno = new ArrayList();

				DistritoOperacional distritoOperacional = null;

				for (Iterator iter = retornoConsulta.iterator(); iter.hasNext();) {

					Object[] element = (Object[]) iter.next();

					distritoOperacional = new DistritoOperacional();

					distritoOperacional.setId((Integer) element[0]);
					distritoOperacional.setDescricao((String) element[1]);

					retorno.add(distritoOperacional);
				}

			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto, Raphael Rossiter
	 * 
	 * @date 04/09/2006
	 */
	public Collection<DistritoOperacional> pesquisarDistritoOperacionalPorRA(
			Collection<Integer> idsRa) throws ErroRepositorioException {

		Collection<Object[]> retornoConsulta = new ArrayList();
		Collection<DistritoOperacional> retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT DISTINCT distritoOperacional.id," // 0
					+ "distritoOperacional.descricao " // 1
					+ "FROM OrdemServico os "
					+ "INNER JOIN os.registroAtendimento ragt  "
					+ "INNER JOIN ragt.quadra quadra  "
					+ "INNER JOIN quadra.distritoOperacional distritoOperacional  "
					+ "WHERE os.situacao in (1,3) "
					+ "AND ragt.id in (:idsRa) "
					+ "ORDER BY distritoOperacional.descricao";

			retornoConsulta = session.createQuery(consulta)
					.setParameterList("idsRa", idsRa).list();

			if (retornoConsulta.size() > 0) {

				retorno = new ArrayList();

				DistritoOperacional distritoOperacional = null;

				for (Iterator iter = retornoConsulta.iterator(); iter.hasNext();) {

					Object[] element = (Object[]) iter.next();

					distritoOperacional = new DistritoOperacional();

					distritoOperacional.setId((Integer) element[0]);
					distritoOperacional.setDescricao((String) element[1]);

					retorno.add(distritoOperacional);
				}

			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto, Vivianne Sousa, Raphael Rossiter
	 * @date 04/09/2006, 13/06/2008, 22/05/2009
	 * 
	 * @param unidade
	 * @return Collection<DistritoOperacional>
	 * @throws ErroRepositorioException
	 */
	public Collection<DistritoOperacional> pesquisarDistritoOperacionalPorUnidade(
			Integer unidade) throws ErroRepositorioException {

		Collection<Object[]> retornoConsulta = new ArrayList();
		Collection<DistritoOperacional> retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			// incluso da coluna unidade atual nas tabelas
			// REGISTRO_ATENDIMENTO e ORDEM_SERVICO
			// Vivianne Sousa 13/06/2008 analista:Ftima Sampaio

			consulta = "SELECT DISTINCT distritoOperacional.id," // 0
					+ "distritoOperacional.descricao " // 1
					+ "FROM OrdemServico os  "
					+ "INNER JOIN os.unidadeAtual unid  "
					+ "INNER JOIN os.cobrancaDocumento cobra  "
					+ "INNER JOIN cobra.quadra quadra  "
					+ "INNER JOIN quadra.distritoOperacional distritoOperacional  "
					+ "LEFT JOIN os.registroAtendimento ra  "
					+ "WHERE ra IS NULL "
					+ "AND os.situacao in (1,3) "
					+ "AND unid.id = :unidade "
					+ "ORDER BY distritoOperacional.descricao";

			retornoConsulta = session.createQuery(consulta)
					.setInteger("unidade", unidade).list();

			if (retornoConsulta.size() > 0) {

				retorno = new ArrayList();

				DistritoOperacional distritoOperacional = null;

				for (Iterator iter = retornoConsulta.iterator(); iter.hasNext();) {

					Object[] element = (Object[]) iter.next();

					distritoOperacional = new DistritoOperacional();

					distritoOperacional.setId((Integer) element[0]);
					distritoOperacional.setDescricao((String) element[1]);

					retorno.add(distritoOperacional);
				}

			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<ServicoPerfilTipo> pesquisarServicoPerfilTipoPorUnidade(
			Integer unidadeLotacao) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT DISTINCT sptp " + "FROM Equipe eqpe "
					+ "INNER JOIN eqpe.unidadeOrganizacional unid "
					+ "INNER JOIN eqpe.servicoPerfilTipo sptp "
					+ "WHERE unid.id = :unidadeLotacao "
					+ "ORDER BY sptp.descricao";

			retorno = session.createQuery(consulta)
					.setInteger("unidadeLotacao", unidadeLotacao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0471] Obter Dados da Equipe
	 * 
	 * @author Raphael Rossiter
	 * @date 01/09/2006
	 * 
	 * @return idQuipe
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEquipe(Integer idEquipe)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT eqpe.id, eqpe.nome, eqpe.placaVeiculo, eqpe.cargaTrabalho, "
					+ " unid.id, unid.descricao, sptp.id, sptp.descricao"
					+ " FROM Equipe eqpe "
					+ " INNER JOIN eqpe.unidadeOrganizacional unid "
					+ " LEFT JOIN eqpe.servicoPerfilTipo sptp "
					+ " WHERE eqpe.id = :idEquipe ";

			retorno = session.createQuery(consulta)
					.setInteger("idEquipe", idEquipe).setMaxResults(1).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0471] Obter Dados da Equipe
	 * 
	 * @author Raphael Rossiter
	 * @date 01/09/2006
	 * 
	 * @return idQuipe
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEquipeComponentes(Integer idEquipe)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT eqme.id, eqme.indicadorResponsavel, func.id, func.nome, eqme.componentes"
					+ " FROM EquipeComponentes eqme "
					+ " INNER JOIN eqme.equipe eqpe "
					+ " LEFT JOIN eqme.funcionario func "
					+ " WHERE eqpe.id = :idEquipe ";

			retorno = session.createQuery(consulta)
					.setInteger("idEquipe", idEquipe).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0465] Filtrar Ordem Servio
	 * 
	 * Recupera OS programadas
	 * 
	 * @author Leonardo Regis
	 * @date 05/09/2006
	 * 
	 * @param situacaoProgramacao
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> recuperaOSPorProgramacao(
			short situacaoProgramacao) throws ErroRepositorioException {

		Collection<Integer> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consultaOSNaoProgramada = "";
		String consultaOSProgramada = "";
		String consulta = "";
		try {
			consultaOSProgramada = "select os.orse_id as idOSP "
					+ " from atendimentopublico.ordem_servico os, "
					+ " atendimentopublico.os_programacao osp "
					+ " where os.orse_id = osp.orse_id and osp.ospg_icativo = 1  "
					+ " or (osp.ospg_icativo = 2 and osp.ospg_cdsituacaofechamento = 2)";

			consultaOSNaoProgramada = "select os.orse_id as idOSNP "
					+ " from atendimentopublico.ordem_servico os "
					+ " LEFT OUTER JOIN atendimentopublico.os_programacao osp ON osp.orse_id=os.orse_id "
					+ " where os.orse_id not in (" + consultaOSProgramada
					+ ") " + " or osp.ospg_icativo = 2 "
					+ " and osp.ospg_cdsituacaofechamento <> 2";

			String alias = "";
			if (situacaoProgramacao == ConstantesSistema.SIM.shortValue()) {
				alias = "idOSP";
				consulta = consultaOSProgramada;
			} else if (situacaoProgramacao == ConstantesSistema.NAO
					.shortValue()) {
				consulta = consultaOSNaoProgramada;
				alias = "idOSNP";
			}

			retornoConsulta = session.createSQLQuery(consulta)
					.addScalar(alias, Hibernate.INTEGER).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0450] Pesquisar Ordem de Servico
	 * 
	 * [SB0003] Seleciona Ordem de Servico por Criterio de Seleo [SB0004]
	 * Seleciona Ordem de Servico por Situacao de Diagnostico [SB0005] Seleciona
	 * Ordem de Servico por Situacao de Acompanhamento pela Agencia [SB0006]
	 * Seleciona Ordem de Servico por Critrio Geral
	 * 
	 * @author Rafael Pinto
	 * @date 07/09/2006
	 * 
	 * @param PesquisarOrdemServicoHelper
	 * 
	 * @return Collection<OrdemServico>
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServico> pesquisarOrdemServicoElaborarProgramacao(
			PesquisarOrdemServicoHelper filtro) throws ErroRepositorioException {

		Collection<OrdemServico> retorno = new ArrayList();
		Collection<Object[]> retornoConsulta = new ArrayList();

		Integer criterioSelecao = filtro.getCriterioSelecao();
		int origemServico = filtro.getOrigemServico();

		Integer[] tipoServicos = filtro.getTipoServicos();

		short servicoDiagnosticado = filtro.getServicoDiagnosticado();
		short servicoAcompanhamento = filtro.getServicoAcompanhamento();

		Date dataAtrasoInicial = filtro.getDataAtrasoInicial();
		Date dataAtrasoFinal = filtro.getDataAtrasoFinal();

		Date dataAtendimentoInicial = filtro.getDataAtendimentoInicial();
		Date dataAtendimentoFinal = filtro.getDataAtendimentoFinal();

		Date dataGeracaoInicial = filtro.getDataGeracaoInicial();
		Date dataGeracaoFinal = filtro.getDataGeracaoFinal();

		Date dataPrevisaoClienteInicial = filtro
				.getDataPrevisaoClienteInicial();
		Date dataPrevisaoClienteFinal = filtro.getDataPrevisaoClienteFinal();

		Date dataPrevisaoAgenciaInicial = filtro
				.getDataPrevisaoAgenciaInicial();
		Date dataPrevisaoAgenciaFinal = filtro.getDataPrevisaoAgenciaFinal();

		Integer[] unidadeLotacao = new Integer[1];
		unidadeLotacao[0] = filtro.getUnidadeLotacao();

		Session session = HibernateUtil.getSession();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();
		try {

			consulta = "SELECT distinct os.id," // 0
					+ "servicoTipo.id,"// 1
					+ "servicoTipo.descricao,"// 2
					+ "ra.id," // 3
					+ "cobra.id," // 4
					+ "os.dataGeracao,"// 5
					+ "os.dataEncerramento,"// 6
					+ "ra.dataPrevistaAtual,"// 7
					+ "os.situacao, "// 8
					+ "servicoTipoPrioridadeAtual.id "// 9
					+ "FROM OrdemServico os "

					// incluso da coluna unidade atual nas tabelas
					// REGISTRO_ATENDIMENTO e ORDEM_SERVICO
					// comentado por Vivianne Sousa 13/06/2008 analista:Ftima
					// Sampaio
					// + "INNER JOIN os.ordemServicoUnidades osUnidade "
					// + "INNER JOIN osUnidade.atendimentoRelacaoTipo art  "
					// + "INNER JOIN osUnidade.unidadeOrganizacional unid  "
					// + "INNER JOIN os.unidadeAtual unidAtual  "

					+ "INNER JOIN os.servicoTipo servicoTipo "
					+ "INNER JOIN os.servicoTipoPrioridadeAtual servicoTipoPrioridadeAtual "
					+ "INNER JOIN servicoTipo.servicoPerfilTipo servicoPerfilTipo ";

			// Solicitados
			if (origemServico == 1) {

				consulta += "INNER JOIN os.registroAtendimento ra "

						// incluso da coluna unidade atual nas tabelas
						// REGISTRO_ATENDIMENTO e ORDEM_SERVICO
						// comentado por Vivianne Sousa 13/06/2008
						// analista:Ftima Sampaio
						// + "INNER JOIN ra.tramites tramite "

						+ "INNER JOIN ra.localidade raLocal "
						+ "LEFT JOIN ra.setorComercial raSetor "
						+ "LEFT JOIN ra.quadra raQuadra "
						+ "LEFT JOIN raQuadra.distritoOperacional raDistrito  "
						+ "LEFT JOIN os.cobrancaDocumento cobra ";

				// Selecionados
			} else if (origemServico == 2) {

				consulta += "INNER JOIN os.cobrancaDocumento cobra "
						+ "INNER JOIN cobra.localidade cobraLocal "
						+ "INNER JOIN cobra.quadra cobraQuadra "
						+ "INNER JOIN cobraQuadra.setorComercial cobraSetor "
						+ "LEFT JOIN cobraQuadra.distritoOperacional cobraDistrito  "
						+ "LEFT JOIN os.registroAtendimento ra ";

				// Ambos
			} else {

				consulta += "LEFT JOIN os.registroAtendimento ra "

						// incluso da coluna unidade atual nas tabelas
						// REGISTRO_ATENDIMENTO e ORDEM_SERVICO
						// comentado por Vivianne Sousa 13/06/2008
						// analista:Ftima Sampaio
						// + "LEFT JOIN ra.tramites tramite "

						+ "LEFT JOIN ra.localidade raLocal "
						+ "LEFT JOIN ra.setorComercial raSetor "
						+ "LEFT JOIN ra.quadra raQuadra "
						+ "LEFT JOIN raQuadra.distritoOperacional raDistrito  ";

				consulta += "LEFT JOIN os.cobrancaDocumento cobra "
						+ "LEFT JOIN cobra.localidade cobraLocal "
						+ "LEFT JOIN cobra.quadra cobraQuadra "
						+ "LEFT JOIN cobraQuadra.distritoOperacional cobraDistrito  "
						+ "LEFT JOIN cobraQuadra.setorComercial cobraSetor ";
			}

			if (servicoAcompanhamento == ConstantesSistema.SIM.shortValue()
					|| dataPrevisaoAgenciaInicial != null
					&& dataPrevisaoAgenciaFinal != null) {

				consulta += "INNER JOIN ra.raDadosAgenciaReguladoras raDados ";
			}

			if (criterioSelecao.intValue() == 3) {
				// Solicitados
				if (origemServico == 1) {
					consulta += "INNER JOIN ra.registroAtendimentoUnidades raUnidade ";
					// Ambos
				} else if (origemServico == ConstantesSistema.NUMERO_NAO_INFORMADO) {
					consulta += "LEFT JOIN ra.registroAtendimentoUnidades raUnidade ";
				}
			}

			consulta += " WHERE os.situacao in (1,3) ";

			consulta += "AND os.indicadorProgramada = :naoProgramada ";
			parameters.put("naoProgramada", OrdemServico.NAO_PROGRAMADA);

			// [SB0003] - Seleciona Ordem de Servico por Criterio de Seleo
			if (tipoServicos != null) {
				switch (criterioSelecao.intValue()) {

				case 1:

					// Tipo de Servio
					consulta += " AND servicoTipo.id in (:idServicoTipo) ";
					break;

				case 2:

					// Perfil
					consulta += " AND servicoPerfilTipo.id in (:idServicoTipo) ";
					break;

				case 3:
					// Unidade Atendimento

					// Solicitados
					if (origemServico == 1) {

						consulta += " AND raUnidade.unidadeOrganizacional.id in (:idServicoTipo) "
								+ "AND raUnidade.atendimentoRelacaoTipo.id = :idAtendimentoTipo ";

						parameters.put("idAtendimentoTipo",
								AtendimentoRelacaoTipo.ABRIR_REGISTRAR);

						// Selecionados
					} else if (origemServico == 2) {

						// consulta +=
						// " AND ra is null AND osUnidade.unidadeOrganizacional in (:idServicoTipo) "
						// +
						// "AND osUnidade.atendimentoRelacaoTipo = :idAtendimentoTipo ";

						// incluso da coluna unidade atual nas tabelas
						// REGISTRO_ATENDIMENTO e ORDEM_SERVICO
						// comentado por Vivianne Sousa 16/06/2008
						// analista:Ftima Sampaio

						consulta += " AND os.registroAtendimento is null AND os.unidadeAtual.id in (:idServicoTipo) ";

						// Ambos
					} else {
						consulta += " AND ( (raUnidade.unidadeOrganizacional.id in (:idServicoTipo))  "
								+ "AND (raUnidade.atendimentoRelacaoTipo.id = :idAtendimentoTipo) ) ";

						// consulta +=
						// " OR ( ra is null AND osUnidade.unidadeOrganizacional in (:idServicoTipo) "
						// +
						// "AND osUnidade.atendimentoRelacaoTipo = :idAtendimentoTipo) ) ";

						// incluso da coluna unidade atual nas tabelas
						// REGISTRO_ATENDIMENTO e ORDEM_SERVICO
						// comentado por Vivianne Sousa 16/06/2008
						// analista:Fatima Sampaio
						// consulta +=
						// " OR (( (os.registroAtendimento is null) AND (os.unidadeAtual.id in (:idServicoTipo)) ))  ) ";
						parameters.put("idAtendimentoTipo",
								AtendimentoRelacaoTipo.ABRIR_REGISTRAR);

					}

					break;

				case 4:

					// Localidade

					// Solicitados
					if (origemServico == 1) {
						consulta += " AND raLocal.id in (:idServicoTipo) ";

						// Selecionados
					} else if (origemServico == 2) {
						consulta += " AND cobraLocal.id in (:idServicoTipo) ";

						// Ambos
					} else {
						consulta += " AND ( raLocal.id in (:idServicoTipo) OR ";
						consulta += " cobraLocal.id in (:idServicoTipo) ) ";
					}

					break;

				case 5:

					// Setor Comercial

					// Solicitados
					if (origemServico == 1) {
						consulta += " AND raSetor.id in (:idServicoTipo) ";

						// Selecionados
					} else if (origemServico == 2) {
						consulta += " AND cobraSetor.id in (:idServicoTipo) ";

						// Ambos
					} else {
						consulta += " AND ( raSetor.id in (:idServicoTipo) OR ";
						consulta += " cobraSetor.id in (:idServicoTipo) ) ";
					}

					break;

				case 6:

					// Distrito Operacional
					// Solicitados
					if (origemServico == 1) {
						consulta += " AND  raDistrito.id in (:idServicoTipo) ";

						// Selecionados
					} else if (origemServico == 2) {
						consulta += " AND cobraDistrito.id in (:idServicoTipo) ";

						// Ambos
					} else {
						consulta += " AND ( raDistrito.id in (:idServicoTipo) OR ";
						consulta += " cobraDistrito.id in (:idServicoTipo) ) ";
					}

					break;

				}

				parameters.put("idServicoTipo", tipoServicos);
			}

			// [SB0004] - Seleciona Ordem de Servico por Situacao de Diagnostico
			if (servicoDiagnosticado != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				consulta += " AND os.indicadorServicoDiagnosticado = :servicoDiagnosticado ";
				parameters.put("servicoDiagnosticado", servicoDiagnosticado);
			}

			// [SB0005] - Seleciona Ordem de Servico por Situacao de
			// Acompanhamento pela Agencia
			if (servicoAcompanhamento == ConstantesSistema.NAO.shortValue()) {

				consulta += " AND ra NOT IN "
						+ "(SELECT raDadosAgencia.registroAtendimento FROM RaDadosAgenciaReguladora raDadosAgencia) ";

			} else if (dataPrevisaoAgenciaInicial != null
					&& dataPrevisaoAgenciaFinal != null) {

				consulta += " AND raDados.dataPrevisaoAtual BETWEEN (:dataPrevisaoAgenciaInicial) AND (:dataPrevisaoAgenciaFinal) ";

				parameters.put("dataPrevisaoAgenciaInicial",
						dataPrevisaoAgenciaInicial);
				parameters.put("dataPrevisaoAgenciaFinal",
						Util.formatarDataFinal(dataPrevisaoAgenciaFinal));

			}

			// [SB0006] - Seleciona Ordem de Servico por Critrio Geral

			// Solicitados
			if (origemServico == 1) {

				// consulta
				// +=" AND tramite.unidadeOrganizacionalDestino.id = :unidadeLotacao "
				// +
				// " AND tramite.dataTramite = (SELECT max(tram.dataTramite) FROM Tramite tram "
				// + " WHERE tram.registroAtendimento.id = ra.id)  ";

				// incluso da coluna unidade atual nas tabelas
				// REGISTRO_ATENDIMENTO e ORDEM_SERVICO
				// comentado por Vivianne Sousa 16/06/2008 analista:Fatima
				// Sampaio

				consulta += " AND ra is not null AND os.unidadeAtual.id = :unidadeLotacao ";

				parameters.put("unidadeLotacao", unidadeLotacao);

				// Selecionados
			} else if (origemServico == 2) {

				consulta += " AND os.registroAtendimento is null AND os.unidadeAtual.id = :unidadeLotacao ";
				// incluso da coluna unidade atual nas tabelas
				// REGISTRO_ATENDIMENTO e ORDEM_SERVICO
				// comentado por Vivianne Sousa 16/06/2008 analista:Fatima
				// Sampaio
				// + "AND art.id = :idAtendimentoTipo ";

				parameters.put("unidadeLotacao", unidadeLotacao);
				// parameters.put("idAtendimentoTipo",
				// AtendimentoRelacaoTipo.ABRIR_REGISTRAR);

				// Ambos
			} else {

				// consulta
				// +=" AND ( (tramite.unidadeOrganizacionalDestino.id = :unidadeLotacao "
				// +
				// " AND tramite.dataTramite = (SELECT max(tram.dataTramite) FROM Tramite tram "
				// + " WHERE tram.registroAtendimento.id = ra.id) ) OR "
				// +
				// " (ra is null AND unid.id = :unidadeLotacao AND art.id = :idAtendimentoTipo) )";

				// incluso da coluna unidade atual nas tabelas
				// REGISTRO_ATENDIMENTO e ORDEM_SERVICO
				// comentado por Vivianne Sousa 16/06/2008 analista:Fatima
				// Sampaio

				// consulta
				// +=" AND ((ra is not null AND unidAtual.id = :unidadeLotacao ) OR "
				// + " (ra is null AND unidAtual.id = :unidadeLotacao ) )";

				consulta += " AND (os.unidadeAtual.id = :unidadeLotacao ) ";

				parameters.put("unidadeLotacao", unidadeLotacao);
				// parameters.put("idAtendimentoTipo",
				// AtendimentoRelacaoTipo.ABRIR_REGISTRAR);

			}

			// Perodo de Atraso
			if (dataAtrasoInicial != null && dataAtrasoFinal != null) {

				consulta += " AND ( ra.dataPrevistaAtual BETWEEN (:dataAtrasoInicial) AND CURRENT_DATE ";
				consulta += " OR ra.dataPrevistaAtual BETWEEN (:dataAtrasoFinal) AND CURRENT_DATE ) ";

				parameters.put("dataAtrasoInicial", dataAtrasoInicial);
				parameters.put("dataAtrasoFinal",
						Util.formatarDataFinal(dataAtrasoFinal));

			} else if (dataAtrasoInicial != null) {

				consulta += " AND ra.dataPrevistaAtual >= (:dataAtrasoInicial) ";
				parameters.put("dataAtrasoInicial",
						Util.formatarDataFinal(dataAtrasoInicial));
			}

			// Perodo de Atendimento
			if (dataAtendimentoInicial != null && dataAtendimentoFinal != null) {

				// Solicitados
				if (origemServico == 1) {

					consulta += " AND ra.registroAtendimento BETWEEN (:dataAtendimentoInicial) AND (:dataAtendimentoFinal) ";

					// Selecionados
				} else if (origemServico == 2) {

					consulta += " AND os.dataGeracao BETWEEN (:dataAtendimentoInicial) AND (:dataAtendimentoFinal)"
							+ " AND ra IS NULL ";

					// Ambos
				} else {

					consulta += " AND ( ra.registroAtendimento BETWEEN (:dataAtendimentoInicial) AND (:dataAtendimentoFinal) OR ";
					consulta += " (ra IS NULL AND os.dataGeracao BETWEEN (:dataAtendimentoInicial) AND (:dataAtendimentoFinal) ) ) ";

				}

				parameters
				.put("dataAtendimentoInicial", dataAtendimentoInicial);
				parameters.put("dataAtendimentoFinal",
						Util.formatarDataFinal(dataAtendimentoFinal));
			}

			// Perodo de Data Geracao
			if (dataGeracaoInicial != null && dataGeracaoFinal != null) {

				consulta += " AND os.dataGeracao BETWEEN (:dataGeracaoInicial) AND (:dataGeracaoFinal) ";

				parameters.put("dataGeracaoInicial", dataGeracaoInicial);
				parameters.put("dataGeracaoFinal",
						Util.formatarDataFinal(dataGeracaoFinal));
			}

			// Perodo de Previso para Cliente
			if (dataPrevisaoClienteInicial != null
					&& dataPrevisaoClienteFinal != null) {

				consulta += " AND ra.dataPrevistaAtual BETWEEN (:dataPrevisaoClienteInicial) AND (:dataPrevisaoClienteFinal) ";

				parameters.put("dataPrevisaoClienteInicial",
						dataPrevisaoClienteInicial);
				parameters.put("dataPrevisaoClienteFinal",
						Util.formatarDataFinal(dataPrevisaoClienteFinal));
			}

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Integer[]) {
					Integer[] collection = (Integer[]) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retornoConsulta = query.list();

			if (retornoConsulta.size() > 0) {

				retorno = new ArrayList();

				OrdemServico os = null;
				ServicoTipo servicoTipo = null;
				RegistroAtendimento registro = null;
				CobrancaDocumento cobranca = null;
				ServicoTipoPrioridade servicoTipoPrioridade = null;

				for (Iterator iter = retornoConsulta.iterator(); iter.hasNext();) {

					Object[] element = (Object[]) iter.next();

					os = new OrdemServico();
					os.setId((Integer) element[0]);

					servicoTipo = new ServicoTipo();
					servicoTipo.setId((Integer) element[1]);
					servicoTipo.setDescricao((String) element[2]);

					if (element[3] != null) {
						registro = new RegistroAtendimento();
						registro.setId((Integer) element[3]);
						registro.setDataPrevistaAtual((Date) element[7]);
					}

					if (element[4] != null) {
						cobranca = new CobrancaDocumento();
						cobranca.setId((Integer) element[4]);
					}

					if (element[9] != null) {
						servicoTipoPrioridade = new ServicoTipoPrioridade();
						servicoTipoPrioridade.setId((Integer) element[9]);
					}

					os.setDataGeracao((Date) element[5]);
					os.setDataEncerramento((Date) element[6]);
					os.setSituacao((Short) element[8]);

					os.setCobrancaDocumento(cobranca);
					os.setRegistroAtendimento(registro);
					os.setServicoTipo(servicoTipo);
					os.setServicoTipoPrioridadeAtual(servicoTipoPrioridade);

					retorno.add(os);
				}

			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * [UC0465] Filtrar Ordem Servio
	 * 
	 * Recupera OS por Data de Programao
	 * 
	 * @author Leonardo Regis
	 * @date 05/09/2006
	 * 
	 * @param dataProgramacaoInicial
	 * @param dataProgramacaoFinal
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> recuperaOSPorDataProgramacao(
			Date dataProgramacaoInicial, Date dataProgramacaoFinal)
					throws ErroRepositorioException {

		Collection<Integer> retornoConsulta = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "select os.orse_id as idOS "
					+ " from atendimentopublico.ordem_servico os, "
					+ " atendimentopublico.os_programacao osp, "
					+ " atendimentopublico.programacao_roteiro pr "
					+ " where osp.orse_id=os.orse_id "
					+ " and osp.pgrt_id=pr.pgrt_id "
					+ " and pr.pgrt_tmroteiro between (:dataProgramacaoInicial) and (:dataProgramacaoFinal) "
					+ " and (osp.ospg_icativo = 1 "
					+ " or (osp.ospg_icativo = 2 and osp.ospg_cdsituacaofechamento = 2)) ";

			retornoConsulta = session
					.createSQLQuery(consulta)
					.addScalar("idOS", Hibernate.INTEGER)
					.setDate("dataProgramacaoInicial",
							Util.formatarDataInicial(dataProgramacaoInicial))
							.setDate("dataProgramacaoFinal",
									Util.formatarDataFinal(dataProgramacaoFinal))
									.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retornoConsulta;
	}

	/**
	 * [UC0462] Obter Dados das Atividades da OS
	 * 
	 * obter OsAtividadeMaterialExecucao por OS
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param idOS
	 * @return Collection<OsAtividadeMaterialExecucao>
	 * @throws ErroRepositorioException
	 */
	public Collection<OsAtividadeMaterialExecucao> obterOsAtividadeMaterialExecucaoPorOS(
			Integer idOS) throws ErroRepositorioException {
		Collection<Object[]> retornoConsulta = null;
		Collection<OsAtividadeMaterialExecucao> colecaoOsAtividadeMaterialExecucao = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "SELECT osame.id, " // 0
					+ " osame.quantidadeMaterial, " // 1
					+ " a.id, " // 2
					+ " a.descricao, " // 3
					+ " m.id, " // 4
					+ " m.descricao, " // 5
					+ " mu.id, " // 6
					+ " mu.descricao " // 7
					+ "FROM OsAtividadeMaterialExecucao osame "
					+ "INNER JOIN osame.material m  "
					+ "INNER JOIN m.materialUnidade mu  "
					+ "INNER JOIN osame.ordemServicoAtividade osa  "
					+ "INNER JOIN osa.atividade a  "
					+ "INNER JOIN osa.ordemServico os  "
					+ "WHERE os.id = :idOS " + "ORDER BY a.descricao ";

			retornoConsulta = (Collection<Object[]>) session
					.createQuery(consulta).setInteger("idOS", idOS).list();

			if (retornoConsulta != null && !retornoConsulta.isEmpty()) {
				OsAtividadeMaterialExecucao osAtividadeMaterialExecucao = null;
				for (Object[] execucao : retornoConsulta) {
					osAtividadeMaterialExecucao = new OsAtividadeMaterialExecucao();
					osAtividadeMaterialExecucao.setId((Integer) execucao[0]);
					osAtividadeMaterialExecucao
					.setQuantidadeMaterial((BigDecimal) execucao[1]);
					OrdemServicoAtividade ordemServicoAtividade = new OrdemServicoAtividade();
					Atividade atividade = new Atividade();
					atividade.setId((Integer) execucao[2]);
					atividade.setDescricao((String) execucao[3]);
					ordemServicoAtividade.setAtividade(atividade);
					OrdemServico ordemServico = new OrdemServico();
					ordemServico.setId(idOS);
					ordemServicoAtividade.setOrdemServico(ordemServico);
					osAtividadeMaterialExecucao
					.setOrdemServicoAtividade(ordemServicoAtividade);
					Material material = new Material();
					material.setId((Integer) execucao[4]);
					material.setDescricao((String) execucao[5]);
					osAtividadeMaterialExecucao.setMaterial(material);
					MaterialUnidade materialUnidade = new MaterialUnidade();
					materialUnidade.setId((Integer) execucao[6]);
					materialUnidade.setDescricao((String) execucao[7]);
					material.setMaterialUnidade(materialUnidade);
					colecaoOsAtividadeMaterialExecucao
					.add(osAtividadeMaterialExecucao);
				}
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return colecaoOsAtividadeMaterialExecucao;
	}

	/**
	 * [UC0462] Obter Dados das Atividades da OS
	 * 
	 * obter OsAtividadePeriodoExecucao por OS
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param idOS
	 * @return OsExecucaoEquipe
	 * @throws ErroRepositorioException
	 */
	public Collection<OsExecucaoEquipe> obterOsExecucaoEquipePorOS(Integer idOS)
			throws ErroRepositorioException {
		Collection<Object[]> retornoConsulta = null;
		Collection<OsExecucaoEquipe> colecaoOsExecucaoEquipe = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "SELECT DISTINCT osape.id, " // 0
					+ " osape.dataInicio, " // 1
					+ " osape.dataFim, " // 2
					+ " a.id, " // 3
					+ " a.descricao, " // 4
					+ " e.id, " // 5
					+ " e.nome " // 6
					+ "FROM OsExecucaoEquipe osee "
					+ "INNER JOIN osee.equipe e "
					+ "INNER JOIN osee.osAtividadePeriodoExecucao osape  "
					+ "INNER JOIN osape.ordemServicoAtividade osa  "
					+ "INNER JOIN osa.atividade a  "
					+ "INNER JOIN osa.ordemServico os  "
					+ "WHERE os.id = :idOS " + "ORDER BY a.descricao ";

			retornoConsulta = (Collection<Object[]>) session
					.createQuery(consulta).setInteger("idOS", idOS).list();

			if (retornoConsulta != null && !retornoConsulta.isEmpty()) {
				OsExecucaoEquipe osExecucaoEquipe = null;
				for (Object[] periodo : retornoConsulta) {
					osExecucaoEquipe = new OsExecucaoEquipe();
					Equipe equipe = new Equipe();
					equipe.setId((Integer) periodo[5]);
					equipe.setNome((String) periodo[6]);
					osExecucaoEquipe.setEquipe(equipe);
					OsAtividadePeriodoExecucao osAtividadePeriodoExecucao = new OsAtividadePeriodoExecucao();
					osAtividadePeriodoExecucao.setId((Integer) periodo[0]);
					osAtividadePeriodoExecucao.setDataInicio((Date) periodo[1]);
					osAtividadePeriodoExecucao.setDataFim((Date) periodo[2]);
					OrdemServicoAtividade ordemServicoAtividade = new OrdemServicoAtividade();
					Atividade atividade = new Atividade();
					atividade.setId((Integer) periodo[3]);
					atividade.setDescricao((String) periodo[4]);
					ordemServicoAtividade.setAtividade(atividade);
					OrdemServico ordemServico = new OrdemServico();
					ordemServico.setId(idOS);
					ordemServicoAtividade.setOrdemServico(ordemServico);
					osAtividadePeriodoExecucao
					.setOrdemServicoAtividade(ordemServicoAtividade);
					osExecucaoEquipe
					.setOsAtividadePeriodoExecucao(osAtividadePeriodoExecucao);
					colecaoOsExecucaoEquipe.add(osExecucaoEquipe);
				}
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return colecaoOsExecucaoEquipe;
	}

	/**
	 * [UC0460] Obter Carga de Trabalho da Equipe
	 * 
	 * obter OsAtividadePeriodoExecucao por OS
	 * 
	 * @author Rafael Pinto
	 * @date 09/09/2006
	 * 
	 * @param idOS
	 * @return OsAtividadePeriodoExecucao
	 * @throws ErroRepositorioException
	 */
	public Collection<OsAtividadePeriodoExecucao> obterOsAtividadePeriodoExecucaoPorOS(
			Integer idOS, Date dataRoteiro) throws ErroRepositorioException {

		Collection<Object[]> retornoConsulta = null;
		Collection<OsAtividadePeriodoExecucao> colecaoOsPeriodo = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {

			consulta = "SELECT DISTINCT osAtividadePeriodoExecucao.id, " // 0
					+ "osAtividadePeriodoExecucao.dataInicio, " // 1
					+ "osAtividadePeriodoExecucao.dataFim " // 2
					+ "FROM OsAtividadePeriodoExecucao osAtividadePeriodoExecucao "
					+ "INNER JOIN osAtividadePeriodoExecucao.ordemServicoAtividade ordemServicoAtividade "
					+ "INNER JOIN ordemServicoAtividade.ordemServico ordemServico  "
					+ "WHERE ordemServico.id = :idOS "
					+ "AND  osAtividadePeriodoExecucao.dataFim <= :dataRoteiro "
					+ "ORDER BY osAtividadePeriodoExecucao.id ";

			retornoConsulta = (Collection<Object[]>) session
					.createQuery(consulta).setInteger("idOS", idOS)
					.setDate("dataRoteiro", dataRoteiro).list();

			if (retornoConsulta != null && !retornoConsulta.isEmpty()) {

				OsAtividadePeriodoExecucao osAtividadePeriodoExecucao = null;

				for (Object[] periodo : retornoConsulta) {

					osAtividadePeriodoExecucao = new OsAtividadePeriodoExecucao();

					osAtividadePeriodoExecucao.setId((Integer) periodo[0]);
					osAtividadePeriodoExecucao.setDataInicio((Date) periodo[1]);
					osAtividadePeriodoExecucao.setDataFim((Date) periodo[2]);

					colecaoOsPeriodo.add(osAtividadePeriodoExecucao);
				}
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return colecaoOsPeriodo;
	}

	/**
	 * [UC0460] Obter Carga de Trabalho da Equipe
	 * 
	 * obter OsAtividadePeriodoExecucao por OS
	 * 
	 * @author Rafael Pinto
	 * @date 09/09/2006
	 * 
	 * @param idEquipe
	 * @param dataRoteiro
	 * @return OsAtividadePeriodoExecucao
	 * @throws ErroRepositorioException
	 */
	public Collection<OsAtividadePeriodoExecucao> obterOsAtividadePeriodoExecucaoPorEquipe(
			Integer idEquipe, Date dataRoteiro) throws ErroRepositorioException {

		Collection<Object[]> retornoConsulta = null;
		Collection<OsAtividadePeriodoExecucao> colecaoOsPeriodo = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {

			consulta = "SELECT DISTINCT osAtividadePeriodoExecucao.id, " // 0
					+ "osAtividadePeriodoExecucao.dataInicio, " // 1
					+ "osAtividadePeriodoExecucao.dataFim " // 2
					+ "FROM OsExecucaoEquipe osExecucaoEquipe "
					+ "INNER JOIN osExecucaoEquipe.equipe equipe "
					+ "INNER JOIN osExecucaoEquipe.osAtividadePeriodoExecucao osAtividadePeriodoExecucao "
					+ "INNER JOIN osAtividadePeriodoExecucao.ordemServicoAtividade ordemServicoAtividade "
					+ "WHERE equipe.id = :idEquipe "
					+ "AND osAtividadePeriodoExecucao.dataFim = :dataRoteiro "
					+ "ORDER BY osAtividadePeriodoExecucao.id ";

			retornoConsulta = (Collection<Object[]>) session
					.createQuery(consulta).setInteger("idEquipe", idEquipe)
					.setDate("dataRoteiro", dataRoteiro).list();

			if (retornoConsulta != null && !retornoConsulta.isEmpty()) {

				OsAtividadePeriodoExecucao osAtividadePeriodoExecucao = null;

				for (Object[] periodo : retornoConsulta) {

					osAtividadePeriodoExecucao = new OsAtividadePeriodoExecucao();

					osAtividadePeriodoExecucao.setId((Integer) periodo[0]);
					osAtividadePeriodoExecucao.setDataInicio((Date) periodo[1]);
					osAtividadePeriodoExecucao.setDataFim((Date) periodo[2]);

					colecaoOsPeriodo.add(osAtividadePeriodoExecucao);
				}
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return colecaoOsPeriodo;
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto
	 * @date 11/09/2006
	 * 
	 * @param dataRoteiro
	 * @return collection
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> recuperaOSProgramacaoPorDataRoteiro(
			Date dataRoteiro) throws ErroRepositorioException {

		Collection<OrdemServicoProgramacao> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "SELECT osProgramacao "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "INNER JOIN FETCH osProgramacao.programacaoRoteiro programacaoRoteiro  "
					+ "INNER JOIN FETCH osProgramacao.equipe equipe  "
					+ "LEFT JOIN FETCH osProgramacao.ordemServico ordemServico "
					+ "LEFT JOIN FETCH ordemServico.servicoTipo servicoTipo "
					+ "LEFT JOIN FETCH servicoTipo.servicoPerfilTipo servicoPerfilTipo "
					+ "LEFT JOIN FETCH ordemServico.registroAtendimento registroAtendimento "
					+ "INNER JOIN FETCH equipe.servicoPerfilTipo servicoPerfilTipoEquipe  "
					+ "WHERE programacaoRoteiro.dataRoteiro BETWEEN :dataRoteiroInicial AND :dataRoteiroFinal "
					+ "AND (osProgramacao.indicadorAtivo = 1 "
					+ "OR (osProgramacao.indicadorAtivo = 2 AND osProgramacao.situacaoFechamento = 2)) ";

			retornoConsulta = session
					.createQuery(consulta)
					.setTimestamp("dataRoteiroInicial",
							Util.formatarDataInicial(dataRoteiro))
							.setTimestamp("dataRoteiroFinal",
									Util.formatarDataFinal(dataRoteiro)).list();

			/*
			 * if (retornoConsulta != null && !retornoConsulta.isEmpty()) {
			 * 
			 * Iterator itera = retornoConsulta.iterator(); while
			 * (itera.hasNext()) {
			 * 
			 * OrdemServicoProgramacao ordemServicoProgramacao =
			 * (OrdemServicoProgramacao) itera .next();
			 * 
			 * Hibernate.initialize(ordemServicoProgramacao .getOrdemServico());
			 * Hibernate.initialize(ordemServicoProgramacao
			 * .getProgramacaoRoteiro());
			 * Hibernate.initialize(ordemServicoProgramacao
			 * .getOrdemServico().getServicoTipo());
			 * Hibernate.initialize(ordemServicoProgramacao
			 * .getOrdemServico().getServicoTipo() .getServicoPerfilTipo());
			 * Hibernate.initialize(ordemServicoProgramacao
			 * .getOrdemServico().getRegistroAtendimento());
			 * Hibernate.initialize(ordemServicoProgramacao.getEquipe());
			 * Hibernate.initialize(ordemServicoProgramacao.getEquipe()
			 * .getServicoPerfilTipo()); }
			 * 
			 * }
			 */
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto
	 * @date 11/09/2006
	 * 
	 * @param dataRoteiro
	 *            ,idUnidadeOrganizacional
	 * @return collection
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> recuperaOSProgramacaoPorDataRoteiroUnidade(
			Date dataRoteiro, Integer idUnidadeOrganizacional)
					throws ErroRepositorioException {

		Collection<OrdemServicoProgramacao> retorno = new ArrayList();
		Collection<Object[]> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";

		Date dataRoteiroInicial = Util.formatarDataInicial(dataRoteiro);
		Date dataRoteiroFinal = Util.formatarDataFinal(dataRoteiro);

		try {

			consulta = "SELECT osProgramacao.id, " // 0
					+ "osProgramacao.nnSequencialProgramacao," // 1
					+ "osProgramacao.indicadorEquipePrincipal," // 2
					+ "osProgramacao.ultimaAlteracao," // 3
					+ "programacaoRoteiro.id," // 4
					+ "programacaoRoteiro.dataRoteiro," // 5
					+ "programacaoRoteiro.ultimaAlteracao," // 6
					+ "equipe.id," // 7
					+ "equipe.nome," // 8
					+ "equipe.placaVeiculo," // 9
					+ "equipe.cargaTrabalho," // 10
					+ "equipe.indicadorUso," // 11
					+ "equipe.ultimaAlteracao," // 12
					+ "unidade.id," // 13
					+ "unidade.descricao," // 14
					+ "servicoPerfilTipo.id," // 15
					+ "servicoPerfilTipo.descricao," // 16
					+ "ordemServico.id," // 17
					+ "servicoTipo.id," // 18
					+ "servicoTipo.descricao," // 19
					+ "servicoPerfilTipoOs.id," // 20
					+ "servicoPerfilTipoOs.descricao," // 21
					+ "ra.id," // 22
					+ "ra.ultimaAlteracao," // 23
					+ "ra.registroAtendimento, " // 24
					+ "ra.dataPrevistaAtual, " // 25
					+ "osProgramacao.indicadorAtivo " // 26
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "INNER JOIN osProgramacao.ordemServico ordemServico  "
					+ "INNER JOIN osProgramacao.programacaoRoteiro programacaoRoteiro  "
					+ "INNER JOIN osProgramacao.equipe equipe  "
					+ "INNER JOIN equipe.unidadeOrganizacional unidade  "
					+ "INNER JOIN equipe.servicoPerfilTipo servicoPerfilTipo  "
					+ "LEFT JOIN ordemServico.registroAtendimento ra "
					+ "INNER JOIN ordemServico.servicoTipo servicoTipo  "
					+ "INNER JOIN servicoTipo.servicoPerfilTipo servicoPerfilTipoOs "
					+ "WHERE programacaoRoteiro.dataRoteiro BETWEEN :dataRoteiroInicial AND :dataRoteiroFinal "
					+ "AND unidade.id = :idUnidadeOrganizacional "
					+ "AND ordemServico.indicadorProgramada = :programada "
					+ "AND (osProgramacao.indicadorAtivo = :indicadorAtivo "
					+ "OR (osProgramacao.indicadorAtivo = :indicadorAtivoNao AND osProgramacao.situacaoFechamento = :situacaoFechamento )) "
					+ "ORDER BY equipe.id,osProgramacao.nnSequencialProgramacao";

			retornoConsulta = session
					.createQuery(consulta)
					.setTimestamp("dataRoteiroInicial", dataRoteiroInicial)
					.setTimestamp("dataRoteiroFinal", dataRoteiroFinal)
					.setInteger("idUnidadeOrganizacional",idUnidadeOrganizacional)
							.setShort("programada", OrdemServico.PROGRAMADA)
							.setShort("indicadorAtivo",OrdemServicoProgramacao.INDICADOR_ATIVO)
							.setShort("indicadorAtivoNao",OrdemServicoProgramacao.INDICADOR_ATIVO_NAO)
							.setShort("situacaoFechamento",OrdemServicoProgramacao.SITUACAO_FECHAMENTO).list();

			if (retornoConsulta != null && !retornoConsulta.isEmpty()) {

				Iterator itera = retornoConsulta.iterator();
				while (itera.hasNext()) {

					Object[] dadosConsulta = (Object[]) itera.next();

					// OrdemServicoProgramacao
					OrdemServicoProgramacao ordemServicoProgramacao = new OrdemServicoProgramacao();

					ordemServicoProgramacao.setId((Integer) dadosConsulta[0]);

					ordemServicoProgramacao
					.setNnSequencialProgramacao((Short) dadosConsulta[1]);
					ordemServicoProgramacao
					.setIndicadorEquipePrincipal((Short) dadosConsulta[2]);
					ordemServicoProgramacao
					.setUltimaAlteracao((Date) dadosConsulta[3]);
					ordemServicoProgramacao
					.setIndicadorAtivo((Short) dadosConsulta[26]);

					// ProgramacaoRoteiro
					ProgramacaoRoteiro programacaoRoteiro = new ProgramacaoRoteiro();

					programacaoRoteiro.setId((Integer) dadosConsulta[4]);
					programacaoRoteiro.setDataRoteiro((Date) dadosConsulta[5]);
					programacaoRoteiro
					.setUltimaAlteracao((Date) dadosConsulta[6]);

					// Equipe
					Equipe equipe = new Equipe();

					equipe.setId((Integer) dadosConsulta[7]);
					equipe.setNome((String) dadosConsulta[8]);
					equipe.setPlacaVeiculo((String) dadosConsulta[9]);
					equipe.setCargaTrabalho((Integer) dadosConsulta[10]);
					equipe.setIndicadorUso((Short) dadosConsulta[11]);
					equipe.setUltimaAlteracao((Date) dadosConsulta[12]);

					// UnidadeOrganizacional
					UnidadeOrganizacional unidade = new UnidadeOrganizacional();

					unidade.setId((Integer) dadosConsulta[13]);
					unidade.setDescricao((String) dadosConsulta[14]);

					// ServicoPerfilTipo
					ServicoPerfilTipo servicoPerfilTipo = new ServicoPerfilTipo();

					servicoPerfilTipo.setId((Integer) dadosConsulta[15]);
					servicoPerfilTipo.setDescricao((String) dadosConsulta[16]);

					// OrdemServico
					OrdemServico os = new OrdemServico();
					os.setId((Integer) dadosConsulta[17]);

					// ServicoTipo
					ServicoTipo servicoTipo = new ServicoTipo();

					servicoTipo.setId((Integer) dadosConsulta[18]);
					servicoTipo.setDescricao((String) dadosConsulta[19]);

					// ServicoPerfilTipo da OS
					ServicoPerfilTipo servicoPerfilTipoOs = new ServicoPerfilTipo();

					servicoPerfilTipoOs.setId((Integer) dadosConsulta[20]);
					servicoPerfilTipoOs
					.setDescricao((String) dadosConsulta[21]);

					// RegistroAtendimento
					RegistroAtendimento ra = null;

					if (dadosConsulta[22] != null) {

						ra = new RegistroAtendimento();

						ra.setId((Integer) dadosConsulta[22]);
						ra.setUltimaAlteracao((Date) dadosConsulta[23]);
						ra.setRegistroAtendimento((Date) dadosConsulta[24]);
						ra.setDataPrevistaAtual((Date) dadosConsulta[25]);
						os.setRegistroAtendimento(ra);
					}

					equipe.setUnidadeOrganizacional(unidade);
					equipe.setServicoPerfilTipo(servicoPerfilTipo);
					servicoTipo.setServicoPerfilTipo(servicoPerfilTipoOs);
					os.setServicoTipo(servicoTipo);

					ordemServicoProgramacao
					.setProgramacaoRoteiro(programacaoRoteiro);
					ordemServicoProgramacao.setEquipe(equipe);
					ordemServicoProgramacao.setOrdemServico(os);

					retorno.add(ordemServicoProgramacao);
				}

			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0462] Obter Dados das Atividades da OS
	 * 
	 * obter OsAtividadeMaterialExecucao por OS
	 * 
	 * @author Leonardo Regis
	 * @date 14/09/2006
	 * 
	 * @param idOS
	 * @param idAtividade
	 * @return Collection<OsAtividadeMaterialExecucao>
	 * @throws ErroRepositorioException
	 */
	public Collection<OsAtividadeMaterialExecucao> obterOsAtividadeMaterialExecucaoPorOS(
			Integer idOS, Integer idAtividade) throws ErroRepositorioException {
		Collection<Object[]> retornoConsulta = null;
		Collection<OsAtividadeMaterialExecucao> colecaoOsAtividadeMaterialExecucao = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "SELECT osame.id, " // 0
					+ " osame.quantidadeMaterial, " // 1
					+ " a.id, " // 2
					+ " a.descricao, " // 3
					+ " m.id, " // 4
					+ " m.descricao, " // 5
					+ " mu.id, " // 6
					+ " mu.descricao " // 7
					+ "FROM OsAtividadeMaterialExecucao osame "
					+ "INNER JOIN osame.material m  "
					+ "INNER JOIN m.materialUnidade mu  "
					+ "INNER JOIN osame.ordemServicoAtividade osa  "
					+ "INNER JOIN osa.atividade a  "
					+ "INNER JOIN osa.ordemServico os  "
					+ "WHERE os.id = :idOS "
					+ " and a.id = :idAtividade "
					+ "ORDER BY a.descricao ";

			retornoConsulta = (Collection<Object[]>) session
					.createQuery(consulta).setInteger("idOS", idOS)
					.setInteger("idAtividade", idAtividade).list();

			if (retornoConsulta != null && !retornoConsulta.isEmpty()) {
				OsAtividadeMaterialExecucao osAtividadeMaterialExecucao = null;
				for (Object[] execucao : retornoConsulta) {
					osAtividadeMaterialExecucao = new OsAtividadeMaterialExecucao();
					osAtividadeMaterialExecucao.setId((Integer) execucao[0]);
					osAtividadeMaterialExecucao
					.setQuantidadeMaterial((BigDecimal) execucao[1]);
					OrdemServicoAtividade ordemServicoAtividade = new OrdemServicoAtividade();
					Atividade atividade = new Atividade();
					atividade.setId((Integer) execucao[2]);
					atividade.setDescricao((String) execucao[3]);
					ordemServicoAtividade.setAtividade(atividade);
					OrdemServico ordemServico = new OrdemServico();
					ordemServico.setId(idOS);
					ordemServicoAtividade.setOrdemServico(ordemServico);
					osAtividadeMaterialExecucao
					.setOrdemServicoAtividade(ordemServicoAtividade);
					Material material = new Material();
					material.setId((Integer) execucao[4]);
					material.setDescricao((String) execucao[5]);
					osAtividadeMaterialExecucao.setMaterial(material);
					MaterialUnidade materialUnidade = new MaterialUnidade();
					materialUnidade.setId((Integer) execucao[6]);
					materialUnidade.setDescricao((String) execucao[7]);
					material.setMaterialUnidade(materialUnidade);
					colecaoOsAtividadeMaterialExecucao
					.add(osAtividadeMaterialExecucao);
				}
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return colecaoOsAtividadeMaterialExecucao;
	}

	/**
	 * [UC0462] Obter Dados das Atividades da OS
	 * 
	 * obter OsAtividadePeriodoExecucao por OS
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param idOS
	 * @param idAtividade
	 * @return OsExecucaoEquipe
	 * @throws ErroRepositorioException
	 */
	public Collection<OsExecucaoEquipe> obterOsExecucaoEquipePorOS(
			Integer idOS, Integer idAtividade) throws ErroRepositorioException {
		Collection<Object[]> retornoConsulta = null;
		Collection<OsExecucaoEquipe> colecaoOsExecucaoEquipe = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "SELECT osape.id, " // 0
					+ " osape.dataInicio, " // 1
					+ " osape.dataFim, " // 2
					+ " a.id, " // 3
					+ " a.descricao, " // 4
					+ " e.id, " // 5
					+ " e.nome " // 6
					+ "FROM OsExecucaoEquipe osee "
					+ "INNER JOIN osee.equipe e "
					+ "INNER JOIN osee.osAtividadePeriodoExecucao osape  "
					+ "INNER JOIN osape.ordemServicoAtividade osa  "
					+ "INNER JOIN osa.atividade a  "
					+ "INNER JOIN osa.ordemServico os  "
					+ "WHERE os.id = :idOS "
					+ " and a.id = :idAtividade "
					+ "ORDER BY a.descricao ";

			retornoConsulta = (Collection<Object[]>) session
					.createQuery(consulta).setInteger("idOS", idOS)
					.setInteger("idAtividade", idAtividade).list();

			if (retornoConsulta != null && !retornoConsulta.isEmpty()) {
				OsExecucaoEquipe osExecucaoEquipe = null;
				for (Object[] periodo : retornoConsulta) {
					osExecucaoEquipe = new OsExecucaoEquipe();
					Equipe equipe = new Equipe();
					equipe.setId((Integer) periodo[5]);
					equipe.setNome((String) periodo[6]);
					osExecucaoEquipe.setEquipe(equipe);
					OsAtividadePeriodoExecucao osAtividadePeriodoExecucao = new OsAtividadePeriodoExecucao();
					osAtividadePeriodoExecucao.setId((Integer) periodo[0]);
					osAtividadePeriodoExecucao.setDataInicio((Date) periodo[1]);
					osAtividadePeriodoExecucao.setDataFim((Date) periodo[2]);
					OrdemServicoAtividade ordemServicoAtividade = new OrdemServicoAtividade();
					Atividade atividade = new Atividade();
					atividade.setId((Integer) periodo[3]);
					atividade.setDescricao((String) periodo[4]);
					ordemServicoAtividade.setAtividade(atividade);
					OrdemServico ordemServico = new OrdemServico();
					ordemServico.setId(idOS);
					ordemServicoAtividade.setOrdemServico(ordemServico);
					osAtividadePeriodoExecucao
					.setOrdemServicoAtividade(ordemServicoAtividade);
					osExecucaoEquipe
					.setOsAtividadePeriodoExecucao(osAtividadePeriodoExecucao);
					colecaoOsExecucaoEquipe.add(osExecucaoEquipe);
				}
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return colecaoOsExecucaoEquipe;
	}

	/**
	 * [UC0460] Obter Carga de Trabalho da Equipe
	 * 
	 * obter programaes ativas por os e situacao
	 * 
	 * @author Leonardo Regis
	 * @date 14/09/2006
	 * 
	 * @param idOS
	 * @return Collection<OrdemServicoProgramacao>
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> obterProgramacoesAtivasPorOs(
			Integer idOS) throws ErroRepositorioException {
		Collection<OrdemServicoProgramacao> retornoConsulta = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "SELECT osp "
					+ "FROM OrdemServicoProgramacao osp "
					+ "INNER JOIN osp.ordemServico os "
					+ "WHERE os.id = :idOS "
					+ " and (osp.indicadorAtivo = 1 or (osp.indicadorAtivo = 2 and osp.situacaoFechamento = 2))";

			retornoConsulta = (Collection<OrdemServicoProgramacao>) session
					.createQuery(consulta).setInteger("idOS", idOS).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retornoConsulta;
	}

	/**
	 * [UC0460] Obter Carga de Trabalho da Equipe
	 * 
	 * obter o somatrio de horas para a OS informada e para o dia do roteiro
	 * solicitado
	 * 
	 * @author Leonardo Regis
	 * @date 15/09/2006
	 * 
	 * @param idEquipe
	 * @param dataRoteiro
	 * @return OsExecucaoEquipe
	 * @throws ErroRepositorioException
	 */
	public Collection<OsExecucaoEquipe> obterOsExecucaoEquipePorEquipe(
			Integer idEquipe, Date dataRoteiro) throws ErroRepositorioException {
		Collection<Object[]> retornoConsulta = null;
		Collection<OsExecucaoEquipe> colecaoOsExecucaoEquipe = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "SELECT osape.id, " // 0
					+ " osape.dataInicio, " // 1
					+ " osape.dataFim, " // 2
					+ " a.id, " // 3
					+ " a.descricao, " // 4
					+ " e.id, " // 5
					+ " e.nome " // 6
					+ "FROM OsExecucaoEquipe osee "
					+ "INNER JOIN osee.equipe e "
					+ "INNER JOIN osee.osAtividadePeriodoExecucao osape  "
					+ "INNER JOIN osape.ordemServicoAtividade osa  "
					+ "INNER JOIN osa.atividade a  "
					+ "INNER JOIN osa.ordemServico os  "
					+ "WHERE e.id = :idEquipe "
					+ " and osape.dataFim = :dataRoteiro "
					+ "ORDER BY a.descricao ";

			retornoConsulta = (Collection<Object[]>) session
					.createQuery(consulta).setInteger("idEquipe", idEquipe)
					.setDate("dataRoteiro", dataRoteiro).list();

			if (retornoConsulta != null && !retornoConsulta.isEmpty()) {
				OsExecucaoEquipe osExecucaoEquipe = null;
				for (Object[] periodo : retornoConsulta) {
					osExecucaoEquipe = new OsExecucaoEquipe();
					Equipe equipe = new Equipe();
					equipe.setId((Integer) periodo[5]);
					equipe.setNome((String) periodo[6]);
					osExecucaoEquipe.setEquipe(equipe);
					OsAtividadePeriodoExecucao osAtividadePeriodoExecucao = new OsAtividadePeriodoExecucao();
					osAtividadePeriodoExecucao.setId((Integer) periodo[0]);
					osAtividadePeriodoExecucao.setDataInicio((Date) periodo[1]);
					osAtividadePeriodoExecucao.setDataFim((Date) periodo[2]);
					OrdemServicoAtividade ordemServicoAtividade = new OrdemServicoAtividade();
					Atividade atividade = new Atividade();
					atividade.setId((Integer) periodo[3]);
					atividade.setDescricao((String) periodo[4]);
					ordemServicoAtividade.setAtividade(atividade);
					osAtividadePeriodoExecucao
					.setOrdemServicoAtividade(ordemServicoAtividade);
					osExecucaoEquipe
					.setOsAtividadePeriodoExecucao(osAtividadePeriodoExecucao);
					colecaoOsExecucaoEquipe.add(osExecucaoEquipe);
				}
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return colecaoOsExecucaoEquipe;
	}

	/**
	 * [UC0457] Encerrar Ordem de Servio
	 * 
	 * @author Svio Luiz
	 * @date 18/09/2006
	 */
	public Integer pesquisarRAOrdemServico(Integer numeroOS)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT ra.id " + "FROM OrdemServico os "
					+ "LEFT JOIN os.registroAtendimento ra  "
					+ "WHERE os.id = :numeroOS ";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("numeroOS", numeroOS).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * pesquisa se a OS tenha uma prgramao ativa com a data menor ou igual a
	 * data corrente e no tenha recebido a data de encerramento
	 * 
	 * [UC0457] Encerra Ordem de Servio
	 * 
	 * [FS0006] - Verificar Origem do Encerramento da Ordem de Servio
	 * 
	 * @author Svio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarOSProgramacaoAtiva(Integer numeroOS)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT os.id "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "LEFT JOIN osProgramacao.ordemServico os  "
					+ "LEFT JOIN osProgramacao.programacaoRoteiro progRoteiro "
					+ "WHERE os.id = :numeroOS AND "
					+ "osProgramacao.indicadorAtivo = :indAtivo AND "
					+ "progRoteiro.dataRoteiro <= :dataAtual";

			retorno = (Integer) session
					.createQuery(consulta)
					.setInteger("numeroOS", numeroOS)
					.setShort("indAtivo",
							OrdemServicoProgramacao.INDICADOR_ATIVO)
							.setDate("dataAtual", new Date()).setMaxResults(1)
							.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * pesquisa se a OS tenha uma programao ativa com a data menor ou igual a
	 * data corrente e no tenha recebido a data de encerramento
	 * 
	 * [UC0457] Encerra Ordem de Servio
	 * 
	 * [SB0001] - Encerrar sem execuo
	 * 
	 * @author Svio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void atualizarParmsOS(Integer numeroOS,
			Integer idMotivoEncerramento, Date dataEncerramento,
			String parecerEncerramento, String codigoRetornoVistoriaOs)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			String atualizarOS = "";

			atualizarOS = "update gcom.atendimentopublico.ordemservico.OrdemServico "
					+ "set orse_tmultimaalteracao = :ultimaAlteracao, "
					+ "amen_id = :idMotivoEncerramento, "
					+ "orse_cdsituacao = :encerrado, "
					+ "orse_tmencerramento = :dataEncerramento ";
			if (codigoRetornoVistoriaOs != null
					&& !codigoRetornoVistoriaOs.equals("")) {
				atualizarOS = atualizarOS + ",orse_cdretornovistoria = "
						+ new Short(codigoRetornoVistoriaOs);
			} else {
				atualizarOS = atualizarOS + ",orse_cdretornovistoria = null";
			}
			if (parecerEncerramento != null && !parecerEncerramento.equals("")) {
				atualizarOS = atualizarOS + " ,orse_dsparecerencerramento = '"
						+ parecerEncerramento + "'";
			} else {
				atualizarOS = atualizarOS
						+ " ,orse_dsparecerencerramento = null ";
			}
			atualizarOS = atualizarOS + " where orse_id = :idOrdemServico";

			session.createQuery(atualizarOS)
			.setInteger("idOrdemServico", numeroOS)
			.setTimestamp("ultimaAlteracao", new Date())
			.setInteger("idMotivoEncerramento", idMotivoEncerramento)
			.setShort("encerrado", OrdemServico.SITUACAO_ENCERRADO)
			.setTimestamp("dataEncerramento", dataEncerramento)
			.executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0457] Encerrar Ordem de Servio
	 * 
	 * [SB0001] - Encerrar sem execuo
	 * 
	 * @author Svio Luiz
	 * @date 18/09/2006
	 */
	public Integer pesquisarOSReferencia(Integer numeroOS)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT osRef.id " + "FROM OrdemServico os "
					+ "LEFT JOIN os.osReferencia osRef  "
					+ "WHERE os.id = :numeroOS ";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("numeroOS", numeroOS).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0457] Encerra Ordem de Servio
	 * 
	 * [SB0001] - Encerrar sem execuo
	 * 
	 * @author Svio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void atualizarParmsOSReferencia(Integer numeroOSReferencia,
			Integer idMotivoEncerramento) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			String atualizarOS = "";

			atualizarOS = "update gcom.atendimentopublico.ordemservico.OrdemServico "
					+ "set orse_tmultimaalteracao = :ultimaAlteracao, "
					+ "orse_cdsituacao = :encerrado, "
					+ "amen_id = :idMotivoEncerramento "
					+ "where orse_id = :idOrdemServico";

			session.createQuery(atualizarOS)
			.setInteger("idOrdemServico", numeroOSReferencia)
			.setInteger("idMotivoEncerramento", idMotivoEncerramento)
			.setTimestamp("ultimaAlteracao", new Date())
			.setShort("encerrado", OrdemServico.SITUACAO_ENCERRADO)
			.executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1185] - Informar Calibragem
	 * 
	 * @author Thlio Arajo
	 * @param osProgramacaoCalibragem
	 * @throws ControladorException
	 * @date 28/06/2011
	 */
	public void atualizarInformarCalibragem(
			OSProgramacaoCalibragem osProgramacaoCalibragem)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Integer peso = osProgramacaoCalibragem.getPeso();
		Integer fator = osProgramacaoCalibragem.getFator();
		Integer id = osProgramacaoCalibragem.getId();

		try {

			String atualizarInformarCalibragem = "";

			atualizarInformarCalibragem = "update OSProgramacaoCalibragem "
					+ "set peso = :peso, " + "fator = :fator, "
					+ "ultimaAlteracao = :ultimaAlteracao " + "where id = :id";

			session.createQuery(atualizarInformarCalibragem)
			.setInteger("peso", peso).setInteger("fator", fator)
			.setTimestamp("ultimaAlteracao", new Date())
			.setInteger("id", id).executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * 
	 * [UC0457] Encerra Ordem de Servio
	 * 
	 * [FS0004] - Verificar/Excluir/Atualizar Programao da Ordem de Servio
	 * 
	 * @author Svio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarOSProgramacaoRoteiro(Integer numeroOS,
			Date dataEncerramento) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT osProgramacao.id "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "LEFT JOIN osProgramacao.ordemServico os  "
					+ "LEFT JOIN osProgramacao.programacaoRoteiro progRoteiro "
					+ "WHERE os.id = :numeroOS AND "
					+ "progRoteiro.dataRoteiro > :dataEncerramento";

			retorno = session.createQuery(consulta)
					.setInteger("numeroOS", numeroOS)
					.setTimestamp("dataEncerramento", dataEncerramento).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * [UC0457] Encerra Ordem de Servio
	 * 
	 * [FS0004] - Verificar/Excluir/Atualizar Programao da Ordem de Servio
	 * 
	 * @author Svio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarRoteiro(Integer numeroOS, Date dataEncerramento)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT osProgramacao.id,progRoteiro.id "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "LEFT JOIN osProgramacao.ordemServico os  "
					+ "LEFT JOIN osProgramacao.programacaoRoteiro progRoteiro "
					+ "WHERE os.id = :numeroOS AND "
					+ "progRoteiro.dataRoteiro > :dataEncerramento";

			retorno = session.createQuery(consulta)
					.setInteger("numeroOS", numeroOS)
					.setTimestamp("dataEncerramento", dataEncerramento).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * [UC0457] Encerra Ordem de Servio
	 * 
	 * [FS0004] - Verificar/Excluir/Atualizar Programao da Ordem de Servio
	 * 
	 * @author Svio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public Integer verificarExistenciaProgramacaoRoteiroParaOSProgramacao(
			Integer idOSProgramacao, Integer idRoteiro)
					throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT progRoteiro.id "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "LEFT JOIN osProgramacao.programacaoRoteiro progRoteiro "
					+ "WHERE osProgramacao.id <> :idOSProgramacao AND "
					+ "progRoteiro.id = :idRoteiro";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idOSProgramacao", idOSProgramacao)
					.setInteger("idRoteiro", idRoteiro).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0457] Encerra Ordem de Servio
	 * 
	 * [FS0004] - Verificar/Excluir/Atualizar Programao da Ordem de Servio
	 * 
	 * @author Sᶩo Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public OrdemServicoProgramacao pesquisarOSProgramacaoAtivaComDataEncerramento(
			Integer numeroOS, Date dataEncerramento)
					throws ErroRepositorioException {

		OrdemServicoProgramacao retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT osProgramacao "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "LEFT JOIN osProgramacao.ordemServico os  "
					+ "LEFT JOIN osProgramacao.programacaoRoteiro progRoteiro "
					+ "WHERE os.id = :numeroOS AND "
					+ "osProgramacao.indicadorAtivo = :indAtivo AND "
					+ "progRoteiro.dataRoteiro BETWEEN :dataProgramacaoRoteiroInicial AND :dataProgramacaoRoteiroFinal";

			retorno = (OrdemServicoProgramacao) session
					.createQuery(consulta)
					.setInteger("numeroOS", numeroOS)
					.setShort("indAtivo",
							OrdemServicoProgramacao.INDICADOR_ATIVO)
							.setTimestamp("dataProgramacaoRoteiroInicial",
									Util.formatarDataInicial(dataEncerramento))
									.setTimestamp("dataProgramacaoRoteiroFinal",
											Util.formatarDataFinal(dataEncerramento))
											.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servio
	 * 
	 * @author Raphael Rossiter
	 * @date 15/09/2006
	 * 
	 * @param numeroOS
	 * @return Collection<Atividade>
	 * @throws ErroRepositorioException
	 */
	public Collection<Atividade> obterAtividadesOrdemServico(Integer numeroOS)
			throws ErroRepositorioException {
		Collection<Atividade> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "SELECT ativ "
					+ "FROM ServicoTipoAtividade stat, Atividade ativ, OrdemServico orse "
					+ "INNER JOIN orse.servicoTipo svtp "
					+ "WHERE stat.servicoTipo.id = svtp.id AND stat.atividade.id = ativ.id "
					+ "AND orse.id = :numeroOS "
					+ "AND ativ.indicadorUso = 1 AND svtp.indicadorUso = 1 "
					+ "ORDER BY ativ.descricao ";

			retorno = (Collection<Atividade>) session.createQuery(consulta)
					.setInteger("numeroOS", numeroOS).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servio
	 * 
	 * @author Raphael Rossiter
	 * @date 15/09/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection obterEquipesProgramadas(Integer numeroOS)
			throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "SELECT eqpe.id, eqpe.nome "
					+ "FROM OrdemServicoProgramacao ospg "
					+ "INNER JOIN ospg.equipe eqpe "
					+ "INNER JOIN ospg.ordemServico orse "
					+ "WHERE eqpe.indicadorUso = 1 AND orse.id = :numeroOS "
					+ "ORDER BY eqpe.nome ";

			retorno = (Collection) session.createQuery(consulta)
					.setInteger("numeroOS", numeroOS).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servio
	 * 
	 * @author Raphael Rossiter
	 * @date 15/09/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection obterMateriaisProgramados(Integer numeroOS)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "SELECT mate.id, mate.descricao "
					+ "FROM OrdemServico orse, ServicoTipoMaterial stmt "
					+ "INNER JOIN stmt.material mate "
					+ "INNER JOIN stmt.servicoTipo svtp "
					+ "WHERE orse.servicoTipo.id = svtp.id AND orse.id = :numeroOS "
					+ "AND mate.indicadorUso = 1 AND svtp.indicadorUso = 1 "
					+ "ORDER BY mate.descricao ";

			retorno = (Collection<Atividade>) session.createQuery(consulta)
					.setInteger("numeroOS", numeroOS).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servio
	 * 
	 * @author Raphael Rossiter
	 * @date 15/09/2006
	 * 
	 * @param numeroOS
	 *            , idMaterial
	 * @return BigDecimal
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterQuantidadePadraoMaterial(Integer numeroOS,
			Integer idMaterial) throws ErroRepositorioException {

		BigDecimal retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "SELECT stmt.quantidadePadrao "
					+ "FROM OrdemServico orse, ServicoTipoMaterial stmt "
					+ "INNER JOIN stmt.material mate "
					+ "INNER JOIN stmt.servicoTipo svtp "
					+ "WHERE orse.servicoTipo.id = svtp.id AND orse.id = :numeroOS "
					+ "AND mate.id = :idMaterial AND mate.indicadorUso = 1 AND svtp.indicadorUso = 1 ";

			retorno = (BigDecimal) session.createQuery(consulta)
					.setInteger("numeroOS", numeroOS)
					.setInteger("idMaterial", idMaterial).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servio
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarOSAssociadaDocumentoCobranca(Integer numeroOS)
			throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "SELECT COUNT(*) " + "FROM OrdemServico orse "
					+ "INNER JOIN orse.cobrancaDocumento cbdo "
					+ "WHERE orse.id = :numeroOS ";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("numeroOS", numeroOS).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servio
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarOSAssociadaRA(Integer numeroOS)
			throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "SELECT COUNT(*) " + "FROM OrdemServico orse "
					+ "INNER JOIN orse.registroAtendimento rgat "
					+ "WHERE orse.id = :numeroOS ";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("numeroOS", numeroOS).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servio
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer obterUnidadeDestinoUltimoTramite(Integer numeroOS)
			throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "SELECT (SELECT tramite.unidadeOrganizacionalDestino.id "
					+ "FROM Tramite tramite WHERE tramite.id = (SELECT MAX(tram2.id) "
					+ "FROM Tramite tram2 WHERE tram2.id = tram.id)) "
					+ "FROM Tramite tram, OrdemServico orse "
					+ "INNER JOIN orse.registroAtendimento rgat "
					+ "WHERE orse.id = :numeroOS "
					+ "AND tram.registroAtendimento = rgat.id";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("numeroOS", numeroOS).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servio
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection obterEquipesPorUnidade(Integer idUnidade)
			throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "SELECT eqpe.id, eqpe.nome,eqpe.cargaTrabalho "
					+ "FROM Equipe eqpe "
					+ "INNER JOIN eqpe.unidadeOrganizacional unid "
					+ "WHERE eqpe.indicadorUso = 1 AND unid.id = :idUnidade and eqpe.indicadorProgramacaoAutomatica = :indProgAutomatica "
					+ "ORDER BY eqpe.nome ";

			retorno = (Collection<Atividade>) session.createQuery(consulta)
					.setInteger("idUnidade", idUnidade)
					.setShort("indProgAutomatica", ConstantesSistema.SIM)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0362] Efetuar Instala��o de Hidr�metro
	 * 
	 * @author Leonardo Regis
	 * @date 19/09/2006
	 * 
	 * @param dadosOS
	 * @throws ErroRepositorioException
	 */
	public void atualizarOSParaEfetivacaoInstalacaoHidrometro(
			DadosAtualizacaoOSParaInstalacaoHidrometroHelper dadosOS)
					throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String atualizarOS;
		Query query;
		try {

			atualizarOS = " UPDATE OrdemServico "
					+ " SET ultimaAlteracao = :dataUltimaAlteracao, "
					+ " indicadorComercialAtualizado = :indicadorComercialAtualizado, ";

			if (dadosOS.getServicoNaoCobrancaMotivo().getId() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				atualizarOS += " servicoNaoCobrancaMotivo.id = :servicoNaoCobrancaMotivo, ";
			}
			atualizarOS += " percentualCobranca = :percentualCobranca, "
					+ " valorAtual = :valorAtual " + " WHERE id = :osId";

			query = session
					.createQuery(atualizarOS)
					.setBigDecimal("valorAtual", dadosOS.getValorDebito())
					.setShort("indicadorComercialAtualizado",
							dadosOS.getIndicadorComercial())
							.setBigDecimal("percentualCobranca",
									dadosOS.getPercentualCobranca())
									.setTimestamp("dataUltimaAlteracao",
											dadosOS.getUltimaAlteracao())
											.setInteger("osId", dadosOS.getIdOs());
			if (dadosOS.getServicoNaoCobrancaMotivo().getId() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				query.setInteger("servicoNaoCobrancaMotivo", dadosOS
						.getServicoNaoCobrancaMotivo().getId());
			}
			query.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programao de Ordens de Servio
	 * 
	 * [FS0008] - Verificar possibilidade da incluso da ordem de Servio
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public OrdemServicoProgramacao pesquisarOSProgramacaoAtivaPorOS(Integer idOS)
			throws ErroRepositorioException {

		OrdemServicoProgramacao retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT osProgramacao "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "INNER JOIN osProgramacao.ordemServico os  "
					+ "INNER JOIN FETCH osProgramacao.equipe equipe  "
					+ "INNER JOIN FETCH osProgramacao.programacaoRoteiro "
					+ "WHERE os.id = :numeroOS "
					+ "AND osProgramacao.indicadorAtivo = :indAtivo";

			retorno = (OrdemServicoProgramacao) session
					.createQuery(consulta)
					.setInteger("numeroOS", idOS)
					.setShort("indAtivo",
							OrdemServicoProgramacao.INDICADOR_ATIVO)
							.setMaxResults(1).uniqueResult();
			/*
			 * if (retorno != null) { Hibernate.initialize(retorno.getEquipe());
			 * Hibernate.initialize(retorno.getProgramacaoRoteiro()); }
			 */

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public OrdemServicoProgramacao pesquisarOSProgramacaoPorId(
			Integer idOrdemServicoProgramacao) throws ErroRepositorioException {

		OrdemServicoProgramacao retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT osProgramacao "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "WHERE osProgramacao.id = :idOrdemServicoProgramacao ";

			retorno = (OrdemServicoProgramacao) session
					.createQuery(consulta)
					.setInteger("idOrdemServicoProgramacao",
							idOrdemServicoProgramacao).setMaxResults(1)
							.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public OrdemServicoProgramacao pesquisarOSProgramacaoAtivaComDataRoteiroIdEquipe(
			Integer numeroOS, Date dataRoteiro, Integer idEquipe)
					throws ErroRepositorioException {

		OrdemServicoProgramacao retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT osProgramacao "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "INNER JOIN osProgramacao.ordemServico os  "
					+ "INNER JOIN osProgramacao.equipe equipe  "
					+ "INNER JOIN osProgramacao.programacaoRoteiro progRoteiro "
					+ "WHERE os.id = :numeroOS  "
					+ "AND equipe.id = :idEquipe "
					+ "AND osProgramacao.indicadorAtivo = :indAtivo "
					+ "AND progRoteiro.dataRoteiro BETWEEN :dataProgramacaoRoteiroInicial AND :dataProgramacaoRoteiroFinal";

			retorno = (OrdemServicoProgramacao) session
					.createQuery(consulta)
					.setInteger("numeroOS", numeroOS)
					.setInteger("idEquipe", idEquipe)
					.setShort("indAtivo",
							OrdemServicoProgramacao.INDICADOR_ATIVO)
							.setTimestamp("dataProgramacaoRoteiroInicial",
									Util.formatarDataInicial(dataRoteiro))
									.setTimestamp("dataProgramacaoRoteiroFinal",
											Util.formatarDataFinal(dataRoteiro))
											.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> pesquisarOSProgramacaoComDataRoteiroIdEquipeDiferenteOS(
			Integer numeroOS, Date dataRoteiro, Integer idEquipe)
					throws ErroRepositorioException {

		Collection<OrdemServicoProgramacao> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT osProgramacao "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "INNER JOIN osProgramacao.ordemServico os  "
					+ "INNER JOIN osProgramacao.equipe equipe  "
					+ "INNER JOIN osProgramacao.programacaoRoteiro progRoteiro "
					+ "LEFT JOIN osProgramacao.osProgramNaoEncerMotivo osProgramNaoEncerMotivo "
					+ "WHERE os.id <> :numeroOS  "
					+ "AND equipe.id = :idEquipe "
					+ "AND (osProgramacao.indicadorAtivo = 1 OR (osProgramacao.indicadorAtivo = 2 "
					+ " AND osProgramNaoEncerMotivo IS NULL)) "
					+ "AND progRoteiro.dataRoteiro BETWEEN :dataProgramacaoRoteiroInicial AND :dataProgramacaoRoteiroFinal";

			retorno = (Collection<OrdemServicoProgramacao>) session
					.createQuery(consulta)
					.setInteger("numeroOS", numeroOS)
					.setInteger("idEquipe", idEquipe)
					.setTimestamp("dataProgramacaoRoteiroInicial",
							Util.formatarDataInicial(dataRoteiro))
							.setTimestamp("dataProgramacaoRoteiroFinal",
									Util.formatarDataFinal(dataRoteiro)).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> pesquisarOSProgramacaoComSequencialMaior(
			Integer numeroOS, Date dataRoteiro, Integer idEquipe,
			short sequencialReferencia) throws ErroRepositorioException {

		Collection<OrdemServicoProgramacao> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT osProgramacao "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "INNER JOIN osProgramacao.ordemServico os  "
					+ "INNER JOIN osProgramacao.equipe equipe  "
					+ "INNER JOIN osProgramacao.programacaoRoteiro progRoteiro "
					+ "WHERE equipe.id = :idEquipe "
					+ "AND os.id = :numeroOS "
					+ "AND (osProgramacao.indicadorAtivo = 1 "
					+ "OR (osProgramacao.indicadorAtivo = 2 AND osProgramacao.situacaoFechamento = 2)) "
					+ "AND osProgramacao.nnSequencialProgramacao > :sequencialReferencia "
					+ "AND progRoteiro.dataRoteiro BETWEEN :dataProgramacaoRoteiroInicial AND :dataProgramacaoRoteiroFinal";

			retorno = (Collection<OrdemServicoProgramacao>) session
					.createQuery(consulta)
					.setInteger("numeroOS", numeroOS)
					.setInteger("idEquipe", idEquipe)
					.setShort("sequencialReferencia", sequencialReferencia)
					.setTimestamp("dataProgramacaoRoteiroInicial",
							Util.formatarDataInicial(dataRoteiro))
							.setTimestamp("dataProgramacaoRoteiroFinal",
									Util.formatarDataFinal(dataRoteiro)).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> pesquisarOSProgramacaoComDataRoteiroIdEquipe(
			Date dataRoteiro, Integer idEquipe) throws ErroRepositorioException {

		Collection<OrdemServicoProgramacao> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT osProgramacao "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "INNER JOIN osProgramacao.equipe equipe  "
					+ "INNER JOIN FETCH osProgramacao.ordemServico os  "
					+ "INNER JOIN osProgramacao.programacaoRoteiro progRoteiro "
					+ "WHERE equipe.id = :idEquipe "
					+ "AND progRoteiro.dataRoteiro  BETWEEN :dataProgramacaoRoteiroInicial AND :dataProgramacaoRoteiroFinal "
					+ "AND osProgramacao.indicadorAcompanhamentoServico <> 3 "
					+ "AND (osProgramacao.indicadorAtivo = :indicadorAtivo "
					+ "OR (osProgramacao.indicadorAtivo = :indicadorAtivoNao AND osProgramacao.situacaoFechamento = :situacaoFechamento )) "
					+ "ORDER BY osProgramacao.nnSequencialProgramacao";

			retorno = (Collection<OrdemServicoProgramacao>) session
					.createQuery(consulta)
					.setInteger("idEquipe", idEquipe)
					.setTimestamp("dataProgramacaoRoteiroInicial",
							Util.formatarDataInicial(dataRoteiro))
							.setTimestamp("dataProgramacaoRoteiroFinal",
									Util.formatarDataFinal(dataRoteiro))
									.setShort("indicadorAtivo",
											OrdemServicoProgramacao.INDICADOR_ATIVO)
											.setShort("indicadorAtivoNao",
													OrdemServicoProgramacao.INDICADOR_ATIVO_NAO)
													.setShort("situacaoFechamento",
															OrdemServicoProgramacao.SITUACAO_FECHAMENTO).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> pesquisarOSProgramacaoComDataRoteiroIdEquipeOrdenada(
			Date dataRoteiro, Integer idEquipe) throws ErroRepositorioException {

		Collection<OrdemServicoProgramacao> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT osProgramacao "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "INNER JOIN osProgramacao.equipe equipe  "
					+ "INNER JOIN osProgramacao.programacaoRoteiro progRoteiro "
					+ "WHERE equipe.id = :idEquipe "
					+ "AND progRoteiro.dataRoteiro BETWEEN :dataProgramacaoRoteiroInicial AND :dataProgramacaoRoteiroFinal "
					+ "AND (osProgramacao.indicadorAtivo = :indicadorAtivo "
					+ "OR (osProgramacao.indicadorAtivo = :indicadorAtivoNao AND osProgramacao.situacaoFechamento = :situacaoFechamento )) "
					+ "ORDER BY osProgramacao.nnSequencialProgramacao ";

			retorno = (Collection<OrdemServicoProgramacao>) session
					.createQuery(consulta)
					.setInteger("idEquipe", idEquipe)
					.setTimestamp("dataProgramacaoRoteiroInicial",
							Util.formatarDataInicial(dataRoteiro))
							.setTimestamp("dataProgramacaoRoteiroFinal",
									Util.formatarDataFinal(dataRoteiro))
									.setShort("indicadorAtivo",
											OrdemServicoProgramacao.INDICADOR_ATIVO)
											.setShort("indicadorAtivoNao",
													OrdemServicoProgramacao.INDICADOR_ATIVO_NAO)
													.setShort("situacaoFechamento",
															OrdemServicoProgramacao.SITUACAO_FECHAMENTO).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0457] Encerrar Ordem de Servio
	 * 
	 * @author Svio Luiz
	 * @date 25/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Object[] recuperarParametrosServicoTipo(Integer numeroOS)
			throws ErroRepositorioException {
		Object[] retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "SELECT servTipo.indicadorAtualizaComercial,debTipo.id,imov.id,servTipo.id, "
					+ "servTipo.indicadorIncluirDebito, servTipo.valor "
					+ "FROM OrdemServico orse "
					+ "INNER JOIN orse.servicoTipo servTipo "
					+ "LEFT JOIN  servTipo.debitoTipo debTipo "
					+ "LEFT JOIN orse.imovel imov "
					+ "WHERE orse.id = :numeroOS ";

			retorno = (Object[]) session.createQuery(consulta)
					.setInteger("numeroOS", numeroOS).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto
	 * @date 25/09/2006
	 * 
	 * @param idOs
	 *            ,dataRoteiro
	 * @return Equipe
	 * @throws ErroRepositorioException
	 */
	public Collection<Equipe> recuperaEquipeDaOSProgramacaoPorDataRoteiro(
			Integer idOs, Date dataRoteiro) throws ErroRepositorioException {

		Collection<Equipe> retornoConsulta = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "SELECT equipe "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "INNER JOIN osProgramacao.programacaoRoteiro programacaoRoteiro  "
					+ "INNER JOIN osProgramacao.equipe equipe  "
					+ "INNER JOIN osProgramacao.ordemServico os  "
					+ "WHERE os.id = :idOs "
					+ "AND programacaoRoteiro.dataRoteiro between (:dataRoteiroInicial) and (:dataRoteiroFinal) "
					+ "AND osProgramacao.indicadorAtivo = 1 "
					+ "AND osProgramacao.indicadorAcompanhamentoServico <> 3";

			retornoConsulta = (Collection<Equipe>) session
					.createQuery(consulta)
					.setTimestamp("dataRoteiroInicial",
							Util.formatarDataInicial(dataRoteiro))
							.setTimestamp("dataRoteiroFinal",
									Util.formatarDataFinal(dataRoteiro))
									.setInteger("idOs", idOs).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * Atualiza��o Geral de OS
	 * 
	 * @author Leonardo Regis
	 * @date 25/09/2006
	 * 
	 * @param os
	 * @throws ErroRepositorioException
	 */
	public void atualizaOSGeral(OrdemServico os)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String update;
		Query query;
		try {
			update = "update OrdemServico set " + "valorAtual = :valorAtual, "
					+ "indicadorComercialAtualizado = :indicador, ";
			if (os.getServicoNaoCobrancaMotivo() != null
					&& os.getServicoNaoCobrancaMotivo().getId().intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				update += "servicoNaoCobrancaMotivo.id = :motivoNaoCobranca, ";
			} else {
				update += "percentualCobranca = :percentualCobranca, ";
			}
			update += "ultimaAlteracao = :dataCorrente " + "where id = :osId";
			query = session
					.createQuery(update)
					.setTimestamp("dataCorrente", os.getUltimaAlteracao())
					.setBigDecimal("valorAtual", os.getValorAtual())
					.setShort("indicador", os.getIndicadorComercialAtualizado());
			if (os.getServicoNaoCobrancaMotivo() != null
					&& os.getServicoNaoCobrancaMotivo().getId() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				query.setInteger("motivoNaoCobranca", os
						.getServicoNaoCobrancaMotivo().getId());
			} else {
				query.setBigDecimal("percentualCobranca",
						os.getPercentualCobranca());
			}
			query.setInteger("osId", os.getId()).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0457] Encerrar Ordem de Servio
	 * 
	 * @author Svio Luiz
	 * @date 25/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ErroRRepositorioException
	 */
	public Collection pesquisarSolicitacoesServicoTipoOS(Integer numeroOS)
			throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "SELECT solTipEspecificacao.especificacaoServicoTipos "
					+ "FROM OrdemServico orse "
					+ "LEFT JOIN orse.registroAtendimento regAtendimento "
					+ "LEFT JOIN regAtendimento.solicitacaoTipoEspecificacao solTipEspecificacao "
					+ "WHERE orse.id = :numeroOS";

			retorno = session.createQuery(consulta)
					.setInteger("numeroOS", numeroOS).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0457] Encerrar Ordem de Servio
	 * 
	 * @author Svio Luiz
	 * @date 25/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ErroRRepositorioException
	 */
	public Collection pesquisarServicoTipo(Collection idsServicoTipo)
			throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "SELECT servTipo "
					+ "FROM ServicoTipo servTipo "
					+ "LEFT JOIN servTipo.servicoTipoReferencia servTipoRef "
					+ "WHERE servTipoRef.id is null AND servTipo.id in (:idsServicoTipo) ";

			retorno = session.createQuery(consulta)
					.setParameterList("idsServicoTipo", idsServicoTipo).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto
	 * @date 25/09/2006
	 * 
	 * @param idOs
	 *            ,dataRoteiro
	 * @return Equipe
	 * @throws ErroRepositorioException
	 */
	public boolean verificaExitenciaProgramacaoAtivaParaDiasAnteriores(
			Integer idOs, Date dataRoteiro) throws ErroRepositorioException {

		boolean existeProgramacao = false;
		Collection retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "SELECT osProgramacao "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "INNER JOIN osProgramacao.programacaoRoteiro programacaoRoteiro  "
					+ "INNER JOIN osProgramacao.ordemServico os  "
					+ "WHERE os.id = :idOs "
					+ "AND programacaoRoteiro.dataRoteiro < :dataRoteiro "
					+ "AND osProgramacao.indicadorAtivo = 1 ";

			retornoConsulta = session.createQuery(consulta)
					.setDate("dataRoteiro", dataRoteiro)
					.setInteger("idOs", idOs).list();

			if (retornoConsulta != null && !retornoConsulta.isEmpty()) {
				existeProgramacao = true;
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return existeProgramacao;
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto
	 * @date 26/09/2006
	 * 
	 * @param idOs
	 *            ,dataRoteiro
	 * @return Equipe
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> recuperaOrdemServicoProgramacaoPorDataRoteiro(
			Integer idOs, Date dataRoteiro) throws ErroRepositorioException {

		Collection<OrdemServicoProgramacao> retornoConsulta = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "SELECT osProgramacao "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "INNER JOIN osProgramacao.programacaoRoteiro programacaoRoteiro  "
					+ "INNER JOIN osProgramacao.ordemServico os  "
					+ "WHERE os.id = :idOs "
					+ "AND programacaoRoteiro.dataRoteiro = :dataRoteiro "
					+ "AND osProgramacao.indicadorAtivo = 1 ";

			retornoConsulta = (Collection<OrdemServicoProgramacao>) session
					.createQuery(consulta).setDate("dataRoteiro", dataRoteiro)
					.setInteger("idOs", idOs).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0457] Encerrar Ordem de Servio
	 * 
	 * @author Svio Luiz
	 * @date 26/09/2006
	 * 
	 * @param idOsReferidaRetornoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdMotivoEncerramentoOSReferida(
			Integer idOsReferidaRetornoTipo) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "SELECT atenMotEncerramento.id "
					+ "FROM OsReferidaRetornoTipo osRefeRetTipo "
					+ "LEFT JOIN osRefeRetTipo.atendimentoMotivoEncerramento atenMotEncerramento  "
					+ "WHERE osRefeRetTipo.id = :idOsReferidaRetornoTipo ";

			retorno = (Integer) session
					.createQuery(consulta)
					.setInteger("idOsReferidaRetornoTipo",
							idOsReferidaRetornoTipo).setMaxResults(1)
							.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0457] Encerrar Ordem de Servio
	 * 
	 * @author Svio Luiz
	 * @date 26/09/2006
	 * 
	 * @param idOsReferidaRetornoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarIndDiagnosticoServicoTipoReferencia(
			Integer idOrdemServicoReferencia) throws ErroRepositorioException {

		Short retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "SELECT servTipoRef.indicadorDiagnostico "
					+ "FROM OrdemServico orse "
					+ "LEFT JOIN orse.servicoTipo servTipo "
					+ "LEFT JOIN servTipo.servicoTipoReferencia servTipoRef "
					+ "WHERE orse.id = :idOrdemServicoReferencia ";

			retorno = (Short) session
					.createQuery(consulta)
					.setInteger("idOrdemServicoReferencia",
							idOrdemServicoReferencia).setMaxResults(1)
							.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public UnidadeOrganizacional pesquisarUnidadeOrganizacionalPorUnidade(
			Integer idOs, Integer unidadeLotacao)
					throws ErroRepositorioException {

		UnidadeOrganizacional retornoConsulta = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT DISTINCT unid "
					+ "FROM OrdemServicoUnidade osUnidade "
					+ "INNER JOIN osUnidade.atendimentoRelacaoTipo art  "
					+ "INNER JOIN osUnidade.ordemServico os  "
					+ "INNER JOIN osUnidade.unidadeOrganizacional unid  "
					+ "LEFT JOIN os.registroAtendimento ra  "
					+ "WHERE ra IS NULL " + "AND os.id = :idOs "
					+ "AND unid.id = :unidadeLotacao "
					+ "AND art.id = :idAtendimentoTipo ";

			retornoConsulta = (UnidadeOrganizacional) session
					.createQuery(consulta)
					.setInteger("unidadeLotacao", unidadeLotacao)
					.setInteger("idOs", idOs)
					.setInteger("idAtendimentoTipo",
							AtendimentoRelacaoTipo.ABRIR_REGISTRAR)
							.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0457] Encerrar Ordem de Servio
	 * 
	 * @author Svio Luiz
	 * @date 29/09/2006
	 * 
	 * @param idOsReferidaRetornoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Object[] verificarExistenciaOSEncerrado(Integer idRA,
			Integer idServicoTipo) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "SELECT orse.id,servTipo.descricao "
					+ "FROM OrdemServico orse "
					+ "LEFT JOIN orse.registroAtendimento regAtendimento "
					+ "LEFT JOIN orse.servicoTipo servTipo "
					+ "WHERE regAtendimento.id = :idRA AND "
					+ "orse.situacao <> :encerrada AND "
					+ "servTipo.id = :idServicoTipo";

			retorno = (Object[]) session.createQuery(consulta)
					.setInteger("idRA", idRA)
					.setInteger("idServicoTipo", idServicoTipo)
					.setShort("encerrada", OrdemServico.SITUACAO_ENCERRADO)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0457] Encerrar Ordem de Servio
	 * 
	 * @author Svio Luiz
	 * @date 29/09/2006
	 * 
	 * @param idOsReferidaRetornoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaServicoTipoReferencia(
			Integer idServicoTipo) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "SELECT servTipoRef.id " + "FROM ServicoTipo servTipo "
					+ "LEFT JOIN servTipo.servicoTipoReferencia servTipoRef "
					+ "WHERE servTipo.id = :idServicoTipo";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idServicoTipo", idServicoTipo)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0457] Encerrar Ordem de Servio
	 * 
	 * @author Svio Luiz
	 * @date 02/10/2006
	 * 
	 * @param idOsReferidaRetornoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaUnidadeTerceirizada(Integer idUsuario)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "SELECT usuario.id "
					+ "FROM Usuario usuario "
					+ "LEFT JOIN usuario.unidadeOrganizacional unidOrganizacional "
					+ "LEFT JOIN unidOrganizacional.unidadeTipo unidTipo "
					+ "WHERE usuario.id = :idUsuario AND unidTipo.codigoTipo = :codigoTipo";

			retorno = (Integer) session
					.createQuery(consulta)
					.setString("codigoTipo",
							UnidadeTipo.UNIDADE_TIPO_TERCERIZADO)
							.setInteger("idUsuario", idUsuario).setMaxResults(1)
							.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0457] Encerrar Ordem de Servio
	 * 
	 * @author Svio Luiz
	 * @date 02/10/2006
	 * 
	 * @param idOsReferidaRetornoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection verificarOSparaRA(Integer idOrdemServico, Integer idRA)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "SELECT orse.id " + "FROM OrdemServico orse "
					+ "LEFT JOIN orse.registroAtendimento regAtendimento "
					+ "WHERE orse.id <> :idOrdemServico AND "
					+ "regAtendimento = :idRA "
					+ "and orse.situacao = :pendente ";

			retorno = session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setInteger("idRA", idRA)
					.setInteger("pendente", OrdemServico.SITUACAO_PENDENTE)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisa os campos da OS que ser�o impressos no relat�rio de Ordem de
	 * Servico
	 * 
	 * @author Rafael Corra
	 * @date 17/10/2006
	 * 
	 * @param idOrdemServico
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOSRelatorio(Collection idsOrdemServico)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "SELECT os.dataGeracao, loc.id, sc.codigo, " // 0,1,2
					+ "quadra.numeroQuadra, imov.lote, imov.subLote, " // 3,4,5
					+ "imov.id, last.descricaoAbreviado, " // 6,7
					+ "lest.descricaoAbreviado, lesg.consumoMinimo, " // 8,9
					+ "prua.descricaoAbreviada, pcal.descricaoAbreviada, " // 10,11
					+ "ms.descricao, usuario.nomeUsuario, " // 12,13
					+ "func.id, ra.pontoReferencia, " // 14,15
					+ "svtp.id, svtp.descricao, " // 16,17
					+ "lcoc.descricao, ra.dataPrevistaAtual, " // 18,19
					+ "ra.observacao, os.observacao, ra.id, " // 20,21,22
					+ "soltpesp.descricao, os.id, svtp.tempoMedioExecucao, " // 23,
					// 24,
					// 25
					+ "unidOrg.descricao, imov.numeroSequencialRota, rota.codigo, " // 26,
					// 27,
					// 28
					+ "servTpRef.id, servTpRef.descricao,svtp.valor, " // 29,
					// 30,
					// 31
					+ "os.dataEncerramento, os.descricaoParecerEncerramento, " // 32,
					// 33
					+ "os.cobrancaDocumento.id, os.dataEmissao, "// 34, 35
					+ "os.projeto.id, "// 36
					+ "rota.faturamentoGrupo.id "// 37
					+ "FROM OrdemServicoUnidade osu "
					+ "INNER JOIN osu.atendimentoRelacaoTipo atrt "
					+ "INNER JOIN osu.usuario usuario "
					+ "INNER JOIN osu.unidadeOrganizacional unidOrg "
					+ "LEFT JOIN usuario.funcionario func "
					+ "INNER JOIN osu.ordemServico os "
					+ "INNER JOIN os.servicoTipo svtp "
					+ "LEFT JOIN os.registroAtendimento ra "
					+ "LEFT JOIN ra.meioSolicitacao ms "
					+ "LEFT JOIN ra.solicitacaoTipoEspecificacao soltpesp "
					+ "LEFT JOIN ra.localOcorrencia lcoc "
					+ "LEFT JOIN os.imovel imov "
					+ "LEFT JOIN imov.ligacaoAguaSituacao last "
					+ "LEFT JOIN imov.ligacaoEsgotoSituacao lest "
					+ "LEFT JOIN imov.ligacaoEsgoto lesg "
					+ "LEFT JOIN imov.localidade loc "
					+ "LEFT JOIN imov.setorComercial sc "
					+ "LEFT JOIN imov.quadra quadra "
					+ "LEFT JOIN imov.pavimentoRua prua "
					+ "LEFT JOIN imov.pavimentoCalcada pcal "
					+ "LEFT JOIN quadra.rota rota "
					+ "LEFT JOIN os.servicoTipoReferencia servTpRef "
					+ "WHERE os.id in (:idsOrdemServico) and "
					+ "atrt.id = "
					+ AtendimentoRelacaoTipo.ABRIR_REGISTRAR;

			retorno = session.createQuery(consulta)
					.setParameterList("idsOrdemServico", idsOrdemServico)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servio
	 * 
	 * Obt�m as datas de encerramento e gera��o de uma ordem de Servio
	 * 
	 * @author Raphael Rossiter
	 * @date 18/10/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 */
	public Collection obterDatasGeracaoEncerramentoOS(Integer numeroOS)
			throws ErroRepositorioException {

		Collection retorno = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT dataGeracao, dataEncerramento "
					+ "FROM OrdemServico orse " + "WHERE orse.id = :numeroOS";

			retorno = session.createQuery(consulta)
					.setInteger("numeroOS", numeroOS).setMaxResults(1).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0457] Encerrar Ordem de Servio
	 * 
	 * Recupera o id do im�vel do registro atendimento
	 * 
	 * @author Svio Luiz
	 * @date 19/10/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 */
	public Integer recuperarIdImovelDoRA(Integer idRA)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT imov.id " + "FROM RegistroAtendimento ra "
					+ "LEFT JOIN ra.imovel imov " + "WHERE ra.id = :idRA";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idRA", idRA).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0457] Encerrar Ordem de Servio
	 * 
	 * Recupera o id do im�vel do registro atendimento
	 * 
	 * @author Svio Luiz
	 * @date 19/10/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 */
	public Short recuperarSituacaoOSReferida(Integer idOSReferida)
			throws ErroRepositorioException {

		Short retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT osReferida.situacaoOsReferencia "
					+ "FROM OsReferidaRetornoTipo osReferida "
					+ "WHERE osReferida.id = :idOSReferida";

			retorno = (Short) session.createQuery(consulta)
					.setInteger("idOSReferida", idOSReferida).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0482] - Obter Endere�o Abreviado da Ocorr�ncia do RA
	 * 
	 * Pesquisa os dados da OS usados para saber de onde ser� recebido o
	 * endere�o abreviado
	 * 
	 * @author Rafael Corra
	 * @date 19/10/2006
	 * 
	 * 
	 * @param idOrdemServico
	 * @throws ErroRepositorioException
	 */

	public Object[] obterDadosPesquisaEnderecoAbreviadoOS(Integer idOrdemServico)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT ra.id, imov.id " + "FROM OrdemServico os "
					+ "LEFT JOIN os.registroAtendimento ra "
					+ "LEFT JOIN os.cobrancaDocumento cd "
					+ "LEFT JOIN os.imovel imov " + "WHERE os.id =  :idOS";

			retorno = (Object[]) session.createQuery(consulta)
					.setInteger("idOS", idOrdemServico).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto
	 * @date 23/10/2006
	 */
	public Collection<Integer> recuperaRegistroAtendimentoPorMaiorTramiteUnidadeDestino(
			Integer unidadeDestino) throws ErroRepositorioException {

		Collection<Integer> retorno = new ArrayList();
		Collection<Object[]> retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT DISTINCT ra.id, MAX(tramite.dataTramite) "
					+ "FROM Tramite tramite "
					+ "INNER JOIN tramite.registroAtendimento ra "
					+ "INNER JOIN tramite.unidadeOrganizacionalDestino unidadeDestino "
					+ "WHERE unidadeDestino.id =  :idUnidade "
					+ "AND ra.codigoSituacao = :situacaoPendente "
					+ " GROUP BY  DISTINCT ra.id ";

			retornoConsulta = session
					.createQuery(consulta)
					.setInteger("idUnidade", unidadeDestino)
					.setInteger("situacaoPendente",
							RegistroAtendimento.SITUACAO_PENDENTE).list();

			if (retornoConsulta != null) {

				for (Iterator iter = retornoConsulta.iterator(); iter.hasNext();) {

					Object[] element = (Object[]) iter.next();
					retorno.add((Integer) element[0]);
				}
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Imprimir OS
	 * 
	 * Atualiza a data de emiss�o e a de �ltima altera��o de OS quando
	 * gerar o relat�rio
	 * 
	 * @author Rafael Corra
	 * @date 26/10/2006
	 * 
	 * 
	 * @param colecaoIdsOrdemServico
	 * @throws ErroRepositorioException
	 */
	public void atualizarOrdemServicoRelatorio(Collection colecaoIdsOrdemServico)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			String atualizarOrdemServico = "update gcom.atendimentopublico.ordemservico.OrdemServico "
					+ "set orse_tmemissao = :dataAtual, orse_tmultimaalteracao = :dataAtual "
					+ "where orse_id in (:colecaoIdsOrdemServico)";

			session.createQuery(atualizarOrdemServico)
			.setTimestamp("dataAtual", new Date())
			.setParameterList("colecaoIdsOrdemServico",
					colecaoIdsOrdemServico).executeUpdate();

		} catch (Exception e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");
			// } catch (HibernateException e) {
			// levanta a exceo para a prxima camada
			// throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sesso
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * Cria a query de acordo com os par�metros de pesquisa informados pelo
	 * usu�rio
	 * 
	 * [UC0492] - Gerar Relat�rio Acompanhamento de Execu��o de Ordem de
	 * Servio
	 * 
	 * @author Rafael Corra, Mariana Victor
	 * @date 01/11/06, 10/11/2010
	 * 
	 * @param idImovel
	 * @param idCliente
	 * @param idTipoRelacao
	 * @param localidadeInicial
	 * @param localidadeFinal
	 * @param idAvisoBancario
	 * @param idArrecadador
	 * @param periodoArrecadacaoInicial
	 * @param periodoArrecadacaoFinal
	 * @param periodoPagamentoInicio
	 * @param periodoPagamentoFim
	 * @param dataPagamentoInicial
	 * @param dataPagamentoFinal
	 * @param idsPagamentosSituacoes
	 * @param idsDebitosTipos
	 * @param idsArrecadacaoForma
	 * @param idsDocumentosTipos
	 * @return Collection
	 */
	public Collection pesquisarOSGerarRelatorioAcompanhamentoExecucao(
			String origemServico, String situacaoOS, String[] idsServicosTipos,
			String idUnidadeAtendimento, String idUnidadeAtual,
			String idUnidadeEncerramento, Date periodoAtendimentoInicial,
			Date periodoAtendimentoFinal, Date periodoEncerramentoInicial,
			Date periodoEncerramentoFinal, String idEquipeProgramacao,
			String idEquipeExecucao, String tipoOrdenacao)
					throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		/*
		 *
		 * Acrscimo dos alias para todas as colunas selecionadas na consulta
		 */		
		try {
			consulta = "SELECT DISTINCT os.orse_id as idOS, " // 0
					+ " os.orse_cdsituacao as situacaoOS, " // 1
					+ " servTp.svtp_id as idServTp, " // 2
					+ " servTp.svtp_dsservicotipo as descricaoServTp, " // 3
					+ " ra.rgat_id as idRA, " // 4
					+ " case when (ra.rgat_id is not null) then "
					+ "		ra.rgat_tmregistroatendimento "
					+ "	else "
					+ "		os.orse_tmgeracao "
					+ "	end as dtSolicitacao, " // 5
					+ " os.orse_tmencerramento as dtEncerramentoOS, " // 6
					+ " roteiroProgramacao.pgrt_tmroteiro as dtProgramacao, " // 7
					+ " case when (unidadeAtendimento.unid_id is not null) then "
					+ "		unidadeAtendimento.unid_id "
					+ "	else "
					+ "		unidadeAtendimentoOS.unid_id "
					+ "	end as idUnidadeAtendimento, " // 8
					+ " case when (unidadeAtendimento.unid_id is not null) then "
					+ "		unidadeAtendimento.unid_dsunidade "
					+ "	else "
					+ "		unidadeAtendimentoOS.unid_dsunidade "
					+ "	end as nomeUnidadeAtendimento, " // 9
					+ " case when (ra.rgat_id is not null) then "
					+ "		ra.rgat_tmencerramento "
					+ "	else "
					+ "		os.orse_tmencerramento "
					+ "	end as dtEncerramentoRA, " // 10
					+ " solTpEsp.step_nndiaprazo as diasPrazo, " // 11
					+ " osProgramacao.ospg_icativo as indicadorAtivo, " //12
					+ " osProgramacao.ospg_icequipeprincipal as indicadorEquipePrincipal" //13
					+ " FROM atendimentopublico.ordem_servico as os "
					+ " INNER JOIN atendimentopublico.servico_tipo servTp on os.svtp_id = servTp.svtp_id "
					+ " INNER JOIN atendimentopublico.ordem_servico_unidade osUnidade on osUnidade.orse_id = os.orse_id "
					+ " and osUnidade.attp_id = "
					+ AtendimentoRelacaoTipo.ABRIR_REGISTRAR
					+ " INNER JOIN cadastro.unidade_organizacional unidadeAtendimentoOS on unidadeAtendimentoOS.unid_id = osUnidade.unid_id ";

			if (origemServico
					.equals(ConstantesSistema.ORIGEM_SERVICO_SOLICITADO
							.toString())) {

				consulta = consulta
						+ " INNER JOIN atendimentopublico.registro_atendimento ra on os.rgat_id = ra.rgat_id "
						+ " INNER JOIN atendimentopublico.ra_unidade raUnidade on raUnidade.rgat_id = ra.rgat_id and "
						+ " raUnidade.attp_id = "
						+ AtendimentoRelacaoTipo.ABRIR_REGISTRAR
						+ " INNER JOIN cadastro.unidade_organizacional unidadeAtendimento on unidadeAtendimento.unid_id = raUnidade.unid_id "
						+ " INNER JOIN atendimentopublico.solicitacao_tipo_espec solTpEsp on solTpEsp.step_id = ra.step_id ";

				// incluso da coluna unidade atual na tabela
				// REGISTRO_ATENDIMENTO e ORDEM_SERVICO
				// Vivianne Sousa 11/06/2008 analista:Fatima Sampaio
				// + " INNER JOIN "
				// + " atendimentopublico.tramite tr "
				// + " on tr.rgat_id = ra.rgat_id ";

			} else {

				/*
				 * Alterado por Raphael Rossiter em 26/10/2010 OBJ: Refletir na
				 * aplicao o que esta sendo pedido no caso de uso.
				 * 
				 * 1.2. Seletivas � Ordens de Servio associadas a um
				 * Documento de Cobrana (CBDO_ID da tabela DOCUMENTO_COBRANCA
				 * com valor diferente de nulo);
				 */
				consulta = consulta
						+ " LEFT JOIN atendimentopublico.registro_atendimento ra on os.rgat_id = ra.rgat_id "
						+ " LEFT JOIN atendimentopublico.ra_unidade raUnidade on raUnidade.rgat_id = ra.rgat_id and "
						+ " raUnidade.attp_id = "
						+ AtendimentoRelacaoTipo.ABRIR_REGISTRAR
						+ " LEFT JOIN cadastro.unidade_organizacional unidadeAtendimento on unidadeAtendimento.unid_id = raUnidade.unid_id "
						+ " LEFT JOIN atendimentopublico.solicitacao_tipo_espec solTpEsp on solTpEsp.step_id = ra.step_id ";
			}

			if (idEquipeExecucao != null && !idEquipeExecucao.equals("")) {

				consulta = consulta
						+ " LEFT OUTER JOIN "
						+ " atendimentopublico.ordem_servico_atividade osAtividade "
						+ " on osAtividade.orse_id = os.orse_id "
						+ " LEFT OUTER JOIN "
						+ " atendimentopublico.os_ativ_periodo_execucao osAtividadePeriodoExecucao "
						+ " on osAtividadePeriodoExecucao.osat_id = osAtividade.osat_id "
						+ " LEFT OUTER JOIN "
						+ " atendimentopublico.os_execucao_equipe osExecucaoEquipe "
						+ " on osExecucaoEquipe.oape_id = osAtividadePeriodoExecucao.oape_id ";

			}

			consulta = consulta
					+ " LEFT OUTER JOIN atendimentopublico.os_programacao osProgramacao on osProgramacao.orse_id = os.orse_id "
					+ " LEFT OUTER JOIN atendimentopublico.programacao_roteiro roteiroProgramacao on osProgramacao.pgrt_id = roteiroProgramacao.pgrt_id ";

			consulta = consulta
					+ criarCondicionaisOSGerarRelatorioAcompanhamentoExecucao(
							origemServico, situacaoOS, idsServicosTipos,
							idUnidadeAtendimento, idUnidadeAtual,
							idUnidadeEncerramento, periodoAtendimentoInicial,
							periodoAtendimentoFinal,
							periodoEncerramentoInicial,
							periodoEncerramentoFinal, idEquipeProgramacao,
							idEquipeExecucao);

			consulta = consulta
					+ " and (roteiroProgramacao.pgrt_id is null or roteiroProgramacao.pgrt_tmroteiro in (select max(roteiroProgramacao.pgrt_tmroteiro) FROM atendimentopublico.os_programacao osProgramacao "
					+ " INNER JOIN atendimentopublico.programacao_roteiro roteiroProgramacao "
					+ " on osProgramacao.pgrt_id = roteiroProgramacao.pgrt_id "
					+ " WHERE osProgramacao.orse_id = os.orse_id))";

			if (tipoOrdenacao != null && tipoOrdenacao.equals("1")) {
				consulta = consulta + " order by servTp.svtp_id, ra.rgat_id ";
			}

			retorno = session.createSQLQuery(consulta)
					.addScalar("idOS", Hibernate.INTEGER)
					.addScalar("situacaoOS", Hibernate.SHORT)
					.addScalar("idServTp", Hibernate.INTEGER)
					.addScalar("descricaoServTp", Hibernate.STRING)
					.addScalar("idRA", Hibernate.INTEGER)
					.addScalar("dtSolicitacao", Hibernate.TIMESTAMP)
					.addScalar("dtEncerramentoOS", Hibernate.TIMESTAMP)
					.addScalar("dtProgramacao", Hibernate.TIMESTAMP)
					.addScalar("idUnidadeAtendimento", Hibernate.INTEGER)
					.addScalar("nomeUnidadeAtendimento", Hibernate.STRING)
					.addScalar("dtEncerramentoRA", Hibernate.TIMESTAMP)
					.addScalar("diasPrazo", Hibernate.INTEGER)
					.addScalar("indicadorAtivo", Hibernate.SHORT)
					.addScalar("indicadorEquipePrincipal", Hibernate.SHORT)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Cria as condicionais de acordo com os par�metros de pesquisa informados
	 * pelo usu�rio
	 * 
	 * [UC0492] - Gerar Relat�rio Acompanhamento de Execu��o de Ordem de
	 * Servio
	 * 
	 * @author Rafael Corra
	 * @date 01/11/06
	 * 
	 * @param idImovel
	 * @param idCliente
	 * @param idTipoRelacao
	 * @param localidadeInicial
	 * @param localidadeFinal
	 * @param idAvisoBancario
	 * @param idArrecadador
	 * @param periodoArrecadacaoInicial
	 * @param periodoArrecadacaoFinal
	 * @param periodoPagamentoInicio
	 * @param periodoPagamentoFim
	 * @param dataPagamentoInicial
	 * @param dataPagamentoFinal
	 * @param idsPagamentosSituacoes
	 * @param idsDebitosTipos
	 * @param idsArrecadacaoForma
	 * @param idsDocumentosTipos
	 * @return String
	 */
	public String criarCondicionaisOSGerarRelatorioAcompanhamentoExecucao(
			String origemServico, String situacaoOS, String[] idsServicosTipos,
			String idUnidadeAtendimento, String idUnidadeAtual,
			String idUnidadeEncerramento, Date periodoAtendimentoInicial,
			Date periodoAtendimentoFinal, Date periodoEncerramentoInicial,
			Date periodoEncerramentoFinal, String idEquipeProgramacao,
			String idEquipeExecucao) {

		String sql = " where ";

		if (origemServico != null && !origemServico.equals("")) {
			if (origemServico.equals(ConstantesSistema.ORIGEM_SERVICO_SELETIVO
					.toString())) {
				sql = sql + " os.rgat_id  is null " + " and ";
			}
		}

		if (situacaoOS != null && !situacaoOS.equals("")) {
			if (situacaoOS.equals(OrdemServico.SITUACAO_ENCERRADO.toString())) {
				sql = sql + " os.orse_cdsituacao = "
						+ OrdemServico.SITUACAO_ENCERRADO + " and ";
			} else if (situacaoOS.equals(OrdemServico.SITUACAO_PENDENTE
					.toString())) {
				sql = sql + " os.orse_cdsituacao = "
						+ OrdemServico.SITUACAO_PENDENTE + " and ";
			}
		}

		if (idsServicosTipos != null
				&& !idsServicosTipos.equals("")
				&& (idsServicosTipos.length != 1 || !idsServicosTipos[0]
						.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			String valoresIn = "";
			for (int i = 0; i < idsServicosTipos.length; i++) {
				if (!idsServicosTipos[i].equals("")) {
					valoresIn = valoresIn + idsServicosTipos[i] + ",";
				}
			}
			if (!valoresIn.equals("")) {
				sql = sql + " servTp.svtp_id in (" + valoresIn;
				sql = Util.removerUltimosCaracteres(sql, 1);
				sql = sql + ") and ";
			}
		}

		if (idUnidadeAtendimento != null && !idUnidadeAtendimento.equals("")) {

			if (origemServico
					.equals(ConstantesSistema.ORIGEM_SERVICO_SOLICITADO
							.toString())) {

				sql = sql + " unidadeAtendimento.unid_id = "
						+ idUnidadeAtendimento + " and ";
			} else {

				sql = sql + " unidadeAtendimentoOS.unid_id = "
						+ idUnidadeAtendimento + " and ";
			}
		}

		if (idUnidadeAtual != null && !idUnidadeAtual.equals("")) {

			// sql = sql
			// + " tr.unid_iddestino = "
			// + idUnidadeAtual
			// +
			// " and tr.tram_tmtramite in (select max(tr.tram_tmtramite) from atendimentopublico.tramite tr where "
			// + " ra.rgat_id = tr.rgat_id)"
			// + " and ";

			// incluso da coluna unidade atual na tabela REGISTRO_ATENDIMENTO
			// e ORDEM_SERVICO
			// Vivianne Sousa 11/06/2008 analista:Fatima Sampaio

			if (origemServico
					.equals(ConstantesSistema.ORIGEM_SERVICO_SOLICITADO
							.toString())) {

				sql = sql + " ra.rgat_id is not null "
						+ " and os.unid_idatual = " + idUnidadeAtual + " and ";
			} else {

				sql = sql + " os.unid_idatual = " + idUnidadeAtual + " and ";
			}

		}

		if (idUnidadeEncerramento != null && !idUnidadeEncerramento.equals("")) {

			sql = sql
					+ " os.orse_id in (select y.orse_id from atendimentopublico.ordem_servico_unidade y where y.orse_id = os.orse_id "
					+ " and y.unid_id = " + idUnidadeEncerramento
					+ " and y.attp_id = " + AtendimentoRelacaoTipo.ENCERRAR
					+ ") and ";
		}

		if (periodoAtendimentoInicial != null
				&& !periodoAtendimentoFinal.equals("")) {
			String data1 = Util
					.recuperaDataInvertida(periodoAtendimentoInicial);

			if (data1 != null && !data1.equals("")
					&& data1.trim().length() == 8) {

				data1 = data1.substring(0, 4) + "-" + data1.substring(4, 6)
						+ "-" + data1.substring(6, 8);
			}

			if (origemServico
					.equals(ConstantesSistema.ORIGEM_SERVICO_SOLICITADO
							.toString())) {

				sql = sql + " ra.rgat_tmregistroatendimento >= to_date('"
						+ data1 + "','YYYY-MM-DD HH24:MI:SS') and ";
			} else {

				sql = sql + " os.orse_tmgeracao >= to_date('" + data1
						+ "','YYYY-MM-DD HH24:MI:SS') and ";
			}

		}

		if (periodoAtendimentoFinal != null
				&& !periodoAtendimentoFinal.equals("")) {
			String data2 = Util.recuperaDataInvertida(Util
					.adicionarNumeroDiasDeUmaData(periodoAtendimentoFinal, 1));

			if (data2 != null && !data2.equals("")
					&& data2.trim().length() == 8) {

				data2 = data2.substring(0, 4) + "-" + data2.substring(4, 6)
						+ "-" + data2.substring(6, 8);
			}

			if (origemServico
					.equals(ConstantesSistema.ORIGEM_SERVICO_SOLICITADO
							.toString())) {

				sql = sql + " ra.rgat_tmregistroatendimento <= to_date('"
						+ data2 + "','YYYY-MM-DD HH24:MI:SS') and ";
			} else {

				sql = sql + " os.orse_tmgeracao <= to_date('" + data2
						+ "','YYYY-MM-DD HH24:MI:SS') and ";
			}
		}

		if (periodoEncerramentoInicial != null
				&& !periodoEncerramentoInicial.equals("")) {
			String data1 = Util
					.recuperaDataInvertida(periodoEncerramentoInicial);

			if (data1 != null && !data1.equals("")
					&& data1.trim().length() == 8) {

				data1 = data1.substring(0, 4) + "-" + data1.substring(4, 6)
						+ "-" + data1.substring(6, 8);
			}
			sql = sql + " os.orse_tmencerramento >= to_date('" + data1
					+ "','YYYY-MM-DD HH24:MI:SS') and ";
		}

		if (periodoEncerramentoFinal != null
				&& !periodoEncerramentoFinal.equals("")) {
			String data2 = Util.recuperaDataInvertida(Util
					.adicionarNumeroDiasDeUmaData(periodoEncerramentoFinal, 1));

			if (data2 != null && !data2.equals("")
					&& data2.trim().length() == 8) {

				data2 = data2.substring(0, 4) + "-" + data2.substring(4, 6)
						+ "-" + data2.substring(6, 8);
			}
			sql = sql + " os.orse_tmencerramento <= to_date('" + data2
					+ "','YYYY-MM-DD HH24:MI:SS') and ";
		}

		if (idEquipeProgramacao != null && !idEquipeProgramacao.equals("")) {
			sql = sql + " osProgramacao.eqpe_id = " + idEquipeProgramacao
					+ " and osProgramacao.ospg_icativo = "
					+ ConstantesSistema.INDICADOR_USO_ATIVO + " and ";
		}

		if (idEquipeExecucao != null && !idEquipeExecucao.equals("")) {
			sql = sql + " osExecucaoEquipe.eqpe_id = " + idEquipeExecucao
					+ " and ";
		}

		// retira o " and " q fica sobrando no final da query
		sql = Util.removerUltimosCaracteres(sql, 4);

		return sql;
	}

	/**
	 * [UC0461] - Manter Dados das Atividades da Ordem de Servio
	 * 
	 * Pesquisa os dados da OrdemServicoAtividade
	 * 
	 * @author Raphael Rossiter
	 * @date 01/11/2006
	 * 
	 * @param idOrdemServico
	 *            , idAtividade
	 * @throws ErroRepositorioException
	 */

	public Object[] pesquisarOrdemServicoAtividade(Integer numeroOS,
			Integer idAtividade) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT osat.id, osat.ultimaAlteracao "
					+ "FROM OrdemServicoAtividade osat "
					+ "WHERE osat.ordemServico.id = :numeroOS AND osat.atividade.id = :idAtividade ";

			retorno = (Object[]) session.createQuery(consulta)
					.setInteger("numeroOS", numeroOS)
					.setInteger("idAtividade", idAtividade).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * [UC0461] - Manter Dados das Atividades da Ordem de Servio
	 * 
	 * Pesquisa os dados da OsAtividadeMaterialExecucao associada �
	 * OrdemServicoAtividade para a data informada
	 * 
	 * @author Raphael Rossiter
	 * @date 01/11/2006
	 * 
	 * @param idOrdemServicoAtividade
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOsAtividadeMaterialExecucao(
			Integer idOrdemServicoAtividade) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT oame.id, oame.quantidadeMaterial , oame.material, oame.ultimaAlteracao "
					+ "FROM OsAtividadeMaterialExecucao oame "
					+ "INNER JOIN oame.material mate "
					+ "WHERE oame.ordemServicoAtividade.id = :idOrdemServicoAtividade "
					+ "ORDER BY mate.descricao ";

			retorno = (Collection) session
					.createQuery(consulta)
					.setInteger("idOrdemServicoAtividade",
							idOrdemServicoAtividade).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * Pesquisa a equipe principal de uma OS de programao atravs do id da OS
	 * 
	 * @author Rafael Corra
	 * @date 07/11/2006
	 * 
	 * @param idOS
	 * @throws ErroRepositorioException
	 */
	public Equipe pesquisarEquipePrincipalOSProgramacao(Integer idOS)
			throws ErroRepositorioException {
		Equipe equipe = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT equipe "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "INNER JOIN osProgramacao.ordemServico os "
					+ "INNER JOIN osProgramacao.equipe equipe "
					+ "WHERE os.id = :idOS AND osProgramacao.indicadorAtivo = "
					+ OrdemServicoProgramacao.INDICADOR_ATIVO
					+ " AND osProgramacao.indicadorEquipePrincipal = 1";

			equipe = (Equipe) session.createQuery(consulta)
					.setInteger("idOS", idOS).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return equipe;
	}

	/**
	 * Pesquisa a equipe principal de uma OS de execuo da equipe atravs do id
	 * da OS
	 * 
	 * @author Rafael Corra
	 * @date 07/11/2006
	 * 
	 * @param idOS
	 * @throws ErroRepositorioException
	 */
	public Equipe pesquisarEquipePrincipalOSExecucaoEquipe(Integer idOS)
			throws ErroRepositorioException {
		Equipe equipe = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT equipe "
					+ "FROM OsExecucaoEquipe osExecucaoEquipe "
					+ "INNER JOIN osExecucaoEquipe.equipe equipe "
					+ "INNER JOIN osExecucaoEquipe.osAtividadePeriodoExecucao osAtividadePeriodoExecucao "
					+ "INNER JOIN osAtividadePeriodoExecucao.ordemServicoAtividade ordemServicoAtividade "
					+ "INNER JOIN ordemServicoAtividade.ordemServico os "
					+ "WHERE os.id = :idOS "
					+ "ORDER BY osAtividadePeriodoExecucao.dataFim desc ";

			equipe = (Equipe) session.createQuery(consulta)
					.setInteger("idOS", idOS).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return equipe;
	}

	/**
	 * Cria o count de acordo com os par�metros de pesquisa informados pelo
	 * usu�rio
	 * 
	 * [UC0492] - Gerar Relat�rio Acompanhamento de Execu��o de Ordem de
	 * Servio
	 * 
	 * @author Rafael Corra
	 * @date 01/11/06
	 * 
	 * @param idImovel
	 * @param idCliente
	 * @param idTipoRelacao
	 * @param localidadeInicial
	 * @param localidadeFinal
	 * @param idAvisoBancario
	 * @param idArrecadador
	 * @param periodoArrecadacaoInicial
	 * @param periodoArrecadacaoFinal
	 * @param periodoPagamentoInicio
	 * @param periodoPagamentoFim
	 * @param dataPagamentoInicial
	 * @param dataPagamentoFinal
	 * @param idsPagamentosSituacoes
	 * @param idsDebitosTipos
	 * @param idsArrecadacaoForma
	 * @param idsDocumentosTipos
	 * @return int
	 */
	public int pesquisarOSGerarRelatorioAcompanhamentoExecucaoCount(
			String origemServico, String situacaoOS, String[] idsServicosTipos,
			String idUnidadeAtendimento, String idUnidadeAtual,
			String idUnidadeEncerramento, Date periodoAtendimentoInicial,
			Date periodoAtendimentoFinal, Date periodoEncerramentoInicial,
			Date periodoEncerramentoFinal, String idEquipeProgramacao,
			String idEquipeExecucao) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "SELECT COUNT(DISTINCT os.orse_id) as contador "
					+ " FROM " + " atendimentopublico.ordem_servico os "
					+ " INNER JOIN "
					+ " atendimentopublico.servico_tipo servTp "
					+ " on os.svtp_id = servTp.svtp_id ";

			if (origemServico
					.equals(ConstantesSistema.ORIGEM_SERVICO_SOLICITADO
							.toString())) {

				consulta = consulta
						+ " INNER JOIN "
						+ " atendimentopublico.registro_atendimento ra "
						+ " on os.rgat_id = ra.rgat_id "
						+ " INNER JOIN "
						+ " atendimentopublico.ra_unidade raUnidade "
						+ " on raUnidade.rgat_id = ra.rgat_id and "
						+ " raUnidade.attp_id = "
						+ AtendimentoRelacaoTipo.ABRIR_REGISTRAR
						+ " INNER JOIN "
						+ " cadastro.unidade_organizacional unidadeAtendimento "
						+ " on unidadeAtendimento.unid_id = raUnidade.unid_id ";
				// incluso da coluna unidade atual na tabela
				// REGISTRO_ATENDIMENTO e ORDEM_SERVICO
				// Vivianne Sousa 11/06/2008 analista:Fatima Sampaio
				// + " INNER JOIN "
				// + " atendimentopublico.tramite tr "
				// + " on tr.rgat_id = ra.rgat_id ";

			} else {

				/*
				 * Alterado por Raphael Rossiter em 26/10/2010 OBJ: Refletir na
				 * aplica��o o que esta sendo pedido no caso de uso.
				 * 
				 * 1.2. Seletivas � Ordens de Servio associadas a um
				 * Documento de Cobran�a (CBDO_ID da tabela DOCUMENTO_COBRANCA
				 * com valor diferente de nulo);
				 */
				consulta = consulta
						+ " LEFT JOIN atendimentopublico.registro_atendimento ra on os.rgat_id = ra.rgat_id ";

			}

			if (idUnidadeEncerramento != null
					&& !idUnidadeEncerramento.equals("")) {
				consulta = consulta
						+ " INNER JOIN "
						+ " atendimentopublico.ordem_servico_unidade osUnidade "
						+ " on osUnidade.orse_id = os.orse_id ";
			}

			if (idEquipeProgramacao != null && !idEquipeProgramacao.equals("")) {
				consulta = consulta + " LEFT OUTER JOIN "
						+ " atendimentopublico.os_programacao osProgramacao "
						+ " on osProgramacao.orse_id = os.orse_id ";
			}

			// consulta = consulta
			// + " LEFT OUTER JOIN "
			// + " atendimentopublico.programacao_roteiro roteiroProgramacao "
			// + " on osProgramacao.pgrt_id = roteiroProgramacao.pgrt_id ";

			if (idEquipeExecucao != null && !idEquipeExecucao.equals("")) {
				consulta = consulta
						+ " LEFT OUTER JOIN "
						+ " atendimentopublico.ordem_servico_atividade osAtividade "
						+ " on osAtividade.orse_id = os.orse_id "
						+ " LEFT OUTER JOIN "
						+ " atendimentopublico.os_ativ_periodo_execucao osAtividadePeriodoExecucao "
						+ " on osAtividadePeriodoExecucao.osat_id = osAtividade.osat_id "
						+ " LEFT OUTER JOIN "
						+ " atendimentopublico.os_execucao_equipe osExecucaoEquipe "
						+ " on osExecucaoEquipe.oape_id = osAtividadePeriodoExecucao.oape_id ";
			}

			consulta = consulta
					+ criarCondicionaisOSGerarRelatorioAcompanhamentoExecucao(
							origemServico, situacaoOS, idsServicosTipos,
							idUnidadeAtendimento, idUnidadeAtual,
							idUnidadeEncerramento, periodoAtendimentoInicial,
							periodoAtendimentoFinal,
							periodoEncerramentoInicial,
							periodoEncerramentoFinal, idEquipeProgramacao,
							idEquipeExecucao);

			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("contador", Hibernate.INTEGER).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * Pesquisa as equipes de acordo com os par�metros informado pelo
	 * usu�rio
	 * 
	 * [UC0370] - Filtrar Equipe
	 * 
	 * @author Rafael Corra
	 * @date 01/11/06
	 * 
	 * @param idEquipe
	 * @param nome
	 * @param placa
	 * @param cargaTrabalho
	 * @param idUnidade
	 * @param idFuncionario
	 * @param idPerfilServico
	 * @param indicadorUso
	 * @param numeroPagina
	 * @return Collection
	 */
	public Collection pesquisarEquipes(String idEquipe, String nome,
			String placa, String cargaTrabalho, String codigoDdd,
			String numeroTelefone, String numeroImei, String idUnidade,
			String idFuncionario, String idPerfilServico, String indicadorUso,
			String tipoPesquisa, Integer numeroPagina,
			String equipamentosEspeciasId, String cdUsuarioRespExecServico,
			String indicadorProgramacaoAutomatica)
					throws ErroRepositorioException {
		Collection retorno = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			if (equipamentosEspeciasId != null
					&& !equipamentosEspeciasId.equals(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				consulta = "SELECT DISTINCT equipe "
						+ "FROM EquipeEquipamentosEspeciais eqeqes "
						+ "INNER JOIN eqeqes.equipe equipe "
						+ "INNER JOIN FETCH equipe.unidadeOrganizacional unidade "
						+ "LEFT JOIN FETCH equipe.servicoPerfilTipo servicoPerfilTp "
						+ "LEFT JOIN equipe.usuarioRespExecServico ";
			} else {

				consulta = "SELECT DISTINCT equipe "
						+ "FROM EquipeComponentes eqpCom "
						+ "LEFT JOIN eqpCom.funcionario func "
						+ "INNER JOIN eqpCom.equipe equipe "
						+ "INNER JOIN FETCH equipe.unidadeOrganizacional unidade "
						+ "LEFT JOIN FETCH equipe.servicoPerfilTipo servicoPerfilTp "
						+ "LEFT JOIN equipe.usuarioRespExecServico ";
			}

			consulta = consulta
					+ criarCondicionaisPesquisarEquipes(idEquipe, nome, placa,
							cargaTrabalho, codigoDdd, numeroTelefone,
							numeroImei, idUnidade, idFuncionario,
							idPerfilServico, indicadorUso, tipoPesquisa,
							equipamentosEspeciasId, cdUsuarioRespExecServico,
							indicadorProgramacaoAutomatica);

			retorno = session.createQuery(consulta)
					.setFirstResult(10 * numeroPagina).setMaxResults(10).list();

			// while (iterator.hasNext()) {
			//
			// Equipe equipe = iterator.next();
			//
			// // carrega todos os objetos
			// Hibernate.initialize(equipe.getUnidadeOrganizacional());
			// Hibernate.initialize(equipe.getServicoPerfilTipo());
			//
			// retorno.add(equipe);
			//
			// }

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Cria as condicionais de pesquisa solicitadas pelo usu�rio
	 * 
	 * [UC0370] - Filtrar Equipe
	 * 
	 * @author Rafael Corra
	 * @date 01/11/06
	 * 
	 * @param idEquipe
	 * @param nome
	 * @param placa
	 * @param cargaTrabalho
	 * @param idUnidade
	 * @param idFuncionario
	 * @param idPerfilServico
	 * @param indicadorUso
	 * @return Collection
	 */
	public String criarCondicionaisPesquisarEquipes(String idEquipe,
			String nome, String placa, String cargaTrabalho, String codigoDdd,
			String numeroTelefone, String numeroImei, String idUnidade,
			String idFuncionario, String idPerfilServico, String indicadorUso,
			String tipoPesquisa, String equipamentosEspeciasId,
			String cdUsuarioRespExecServico,
			String indicadorProgramacaoAutomatica) {

		String hql = " WHERE ";

		if (idEquipe != null && !idEquipe.trim().equals("")) {
			hql = hql + " equipe.id = " + idEquipe + " AND ";
		}

		if (nome != null && !nome.trim().equals("")) {
			if (tipoPesquisa != null && tipoPesquisa.equals("1")) {
				hql = hql + " equipe.nome LIKE '" + nome + "%'" + " AND ";
			} else {
				hql = hql + " equipe.nome LIKE '" + "%" + nome + "%'" + " AND ";
			}
		}

		if (placa != null && !placa.trim().equals("")) {
			hql = hql + " equipe.placaVeiculo = '" + placa + "' AND ";
		}

		if (cargaTrabalho != null && !cargaTrabalho.trim().equals("")) {
			hql = hql + " equipe.cargaTrabalho = " + cargaTrabalho + " AND ";
		}

		if (codigoDdd != null && !codigoDdd.trim().equals("")) {
			hql = hql + "equipe.codigoDdd = '" + codigoDdd + "' AND ";
		}

		if (numeroTelefone != null && !numeroTelefone.trim().equals("")) {
			hql = hql + "equipe.numeroTelefone = '" + numeroTelefone + "' AND ";
		}

		if (numeroImei != null && !numeroImei.trim().equals("")) {
			hql = hql + "equipe.numeroImei = " + numeroImei + " AND ";
		}

		if (idUnidade != null && !idUnidade.trim().equals("")) {
			hql = hql + " unidade.id = " + idUnidade + " AND ";
		}

		if (idFuncionario != null && !idFuncionario.trim().equals("")) {
			hql = hql + " func.id = " + idFuncionario + " AND ";
		}

		if (idPerfilServico != null && !idPerfilServico.trim().equals("")) {
			hql = hql + " servicoPerfilTp.id = " + idPerfilServico + " AND ";
		}

		if (indicadorUso != null && !indicadorUso.equals("")) {
			hql = hql + " equipe.indicadorUso = " + indicadorUso + " AND ";
		}

		if (indicadorProgramacaoAutomatica != null
				&& !indicadorProgramacaoAutomatica.equals("")) {
			hql = hql + " equipe.indicadorProgramacaoAutomatica = "
					+ indicadorProgramacaoAutomatica + " AND ";
		}

		if (equipamentosEspeciasId != null
				&& !equipamentosEspeciasId.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			hql = hql + " eqeqes.equipamentosEspeciais.id = "
					+ equipamentosEspeciasId + " AND ";
		}

		if (cdUsuarioRespExecServico != null
				&& !cdUsuarioRespExecServico.equals("")) {
			hql = hql + " equipe.usuarioRespExecServico.id = "
					+ cdUsuarioRespExecServico + " AND ";
		}

		// retira o " and " q fica sobrando no final da query
		hql = Util.removerUltimosCaracteres(hql, 4);

		return hql;

	}

	/**
	 * Verifica a quantidade de registros retornados da pesquisa de equipe
	 * 
	 * [UC0370] - Filtrar Equipe
	 * 
	 * @author Rafael Corra
	 * @date 01/11/06
	 * 
	 * @param idEquipe
	 * @param nome
	 * @param placa
	 * @param cargaTrabalho
	 * @param idUnidade
	 * @param idFuncionario
	 * @param idPerfilServico
	 * @param indicadorUso
	 * @return int
	 */
	public int pesquisarEquipesCount(String idEquipe, String nome,
			String placa, String cargaTrabalho, String codigoDdd,
			String numeroTelefone, String numeroImei, String idUnidade,
			String idFuncionario, String idPerfilServico, String indicadorUso,
			String tipoPesquisa, String equipamentosEspeciasId,
			String cdUsuarioRespExecServico,
			String indicadorProgramacaoAutomatica)
					throws ErroRepositorioException {
		int retorno = 0;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			if (equipamentosEspeciasId != null
					&& !equipamentosEspeciasId.equals(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				consulta = "SELECT COUNT(DISTINCT equipe.id) "
						+ "FROM EquipeEquipamentosEspeciais eqeqes "
						+ "INNER JOIN eqeqes.equipe equipe "
						+ "INNER JOIN equipe.unidadeOrganizacional unidade "
						+ "LEFT JOIN equipe.servicoPerfilTipo servicoPerfilTp "
						+ "LEFT JOIN equipe.usuarioRespExecServico ";
			} else {

				consulta = "SELECT COUNT(DISTINCT equipe.id) "
						+ "FROM EquipeComponentes eqpCom "
						+ "LEFT JOIN eqpCom.funcionario func "
						+ "INNER JOIN eqpCom.equipe equipe "
						+ "INNER JOIN equipe.unidadeOrganizacional unidade "
						+ "LEFT JOIN equipe.servicoPerfilTipo servicoPerfilTp "
						+ "LEFT JOIN equipe.usuarioRespExecServico ";
			}

			consulta = consulta
					+ criarCondicionaisPesquisarEquipes(idEquipe, nome, placa,
							cargaTrabalho, codigoDdd, numeroTelefone,
							numeroImei, idUnidade, idFuncionario,
							idPerfilServico, indicadorUso, tipoPesquisa,
							equipamentosEspeciasId, cdUsuarioRespExecServico,
							indicadorProgramacaoAutomatica);

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Verifica se o Servio associado � ordem de Servio no corresponde a um
	 * Servio de fiscalizao de infra��o
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscalizao
	 * 
	 * @author Svio Luiz
	 * @date 01/11/06
	 * 
	 * @return Integer
	 */
	public Object[] pesquisarServicoTipoComFiscalizacaoInfracao(Integer idOS)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT servTipo.descricao,os.situacao,cobDocumento.id "
					+ "FROM OrdemServico os "
					+ "INNER JOIN os.servicoTipo servTipo "
					+ "LEFT JOIN os.cobrancaDocumento cobDocumento "
					+ "WHERE os.id = :idOS AND servTipo.indicadorFiscalizacaoInfracao <> :nao";

			retorno = (Object[]) session.createQuery(consulta)
					.setInteger("idOS", idOS)
					.setShort("nao", ConstantesSistema.NAO).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0367] Informar Retorno Ordem de Fiscalizao
	 * 
	 * Recupera os par�metros necess�rios da OS
	 * 
	 * @author Svio Luiz
	 * @date 24/08/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarParmsOS(Integer idOS)
			throws ErroRepositorioException {

		Object[] retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT servicoTipo.descricao," // 0
					+ "imovel.id, "// 1
					+ "ligAguaSitu.descricao,"// 2
					+ "ligEsgotoSitu.descricao,"// 3
					+ "leitAnormalidade.descricao, "// 4
					+ "ligAguaSitu.id,"// 5
					+ "ligEsgotoSitu.id, "// 6
					+ "servicoTipo.indicadorFiscalizacaoInfracao, " // 7
					+ "os.situacao, " // 8
					+ "cbdo.id, " // 9
					+ "os.codigoTipoRecebimento " // 10
					+ "FROM OrdemServico os "
					+ "INNER JOIN os.servicoTipo servicoTipo  "
					+ "INNER JOIN os.imovel imovel  "
					+ "LEFT JOIN os.cobrancaDocumento cbdo  "
					+ "LEFT JOIN imovel.ligacaoAguaSituacao ligAguaSitu  "
					+ "LEFT JOIN imovel.ligacaoEsgotoSituacao ligEsgotoSitu  "
					+ "LEFT JOIN imovel.leituraAnormalidade leitAnormalidade  "
					+ "WHERE os.id = :idOS ";

			retornoConsulta = (Object[]) session.createQuery(consulta)
					.setInteger("idOS", idOS).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0367] Informar Retorno Ordem de Fiscalizao
	 * 
	 * Recupera os par�metros necess�rios da OS
	 * 
	 * @author Svio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarIdFiscalizacaoSituacaoAgua(
			Integer idLigacaoAguaSituacao, Integer idSituacaoEncontrada)
					throws ErroRepositorioException {

		Object[] retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT fiscLigAgua.comp_id.idLigacaoAguaSituacao, fiscLigAgua.ligacaoAguaSituacaoByLastIdnova.id, "
					+ "step.id, sotp.id, svtp.id "
					+ "FROM FiscalizacaoSituacaoAgua fiscLigAgua "
					+ "LEFT JOIN fiscLigAgua.solicitacaoTipoEspecificacao step "
					+ "LEFT JOIN step.solicitacaoTipo sotp "
					+ "LEFT JOIN step.servicoTipo svtp "
					+ "WHERE fiscLigAgua.comp_id.idLigacaoAguaSituacao = :idLigacaoAguaSituacao "
					+ "AND fiscLigAgua.comp_id.idFiscalizacaoSituacao = :idSituacaoEncontrada ";

			retornoConsulta = (Object[]) session.createQuery(consulta)
					.setInteger("idLigacaoAguaSituacao", idLigacaoAguaSituacao)
					.setInteger("idSituacaoEncontrada", idSituacaoEncontrada)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0367] Informar Retorno Ordem de Fiscalizao
	 * 
	 * Obt�m o n�mero de meses da fatura a partir da tabela
	 * FISCALIZACAO_SITUACAO_AGUA
	 * 
	 * @author Raphael Rossiter
	 * @date 16/03/2007
	 * 
	 * @param idLigacaoAguaSituacao
	 *            , idSituacaoEncontrada
	 * @return numeroMesesFatura
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarNumeroMesesFaturaAgua(Integer idLigacaoAguaSituacao,
			Integer idSituacaoEncontrada) throws ErroRepositorioException {

		Short retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT fiscLigAgua.numeroMesesFatura "
					+ "FROM FiscalizacaoSituacaoAgua fiscLigAgua "
					+ "WHERE fiscLigAgua.comp_id.idLigacaoAguaSituacao = :idLigacaoAguaSituacao "
					+ "AND fiscLigAgua.comp_id.idFiscalizacaoSituacao = :idSituacaoEncontrada ";

			retornoConsulta = (Short) session.createQuery(consulta)
					.setInteger("idLigacaoAguaSituacao", idLigacaoAguaSituacao)
					.setInteger("idSituacaoEncontrada", idSituacaoEncontrada)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0367] Informar Retorno Ordem de Fiscalizao
	 * 
	 * Obt�m o n�mero de meses da fatura a partir da tabela
	 * FISCALIZACAO_SITUACAO_ESGOTO
	 * 
	 * @author Raphael Rossiter
	 * @date 16/03/2007
	 * 
	 * @param idLigacaoEsgotoSituacao
	 *            , idSituacaoEncontrada
	 * @return numeroMesesFatura
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarNumeroMesesFaturaEsgoto(
			Integer idLigacaoEsgotoSituacao, Integer idSituacaoEncontrada)
					throws ErroRepositorioException {

		Short retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT fiscLigEsgoto.numeroMesesFatura "
					+ "FROM FiscalizacaoSituacaoEsgoto fiscLigEsgoto "
					+ "WHERE fiscLigEsgoto.comp_id.idLigacaoEsgotoSituacao = :idLigacaoEsgotoSituacao "
					+ "AND fiscLigEsgoto.comp_id.idFiscalizacaoSituacao = :idSituacaoEncontrada ";

			retornoConsulta = (Short) session
					.createQuery(consulta)
					.setInteger("idLigacaoEsgotoSituacao",
							idLigacaoEsgotoSituacao)
							.setInteger("idSituacaoEncontrada", idSituacaoEncontrada)
							.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0367] Informar Retorno Ordem de Fiscalizao
	 * 
	 * Recupera os par�metros necess�rios da OS
	 * 
	 * @author Svio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarIdFiscalizacaoSituacaoEsgoto(
			Integer idLigacaoEsgotoSituacao, Integer idSituacaoEncontrada)
					throws ErroRepositorioException {

		Object[] retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT fiscLigEsgoto.comp_id.idLigacaoEsgotoSituacao, fiscLigEsgoto.ligacaoEsgotoSituacaoByLestIdnova, "
					+ "step.id, sotp.id, svtp.id "
					+ "FROM FiscalizacaoSituacaoEsgoto fiscLigEsgoto "
					+ "LEFT JOIN fiscLigEsgoto.solicitacaoTipoEspecificacao step "
					+ "LEFT JOIN step.solicitacaoTipo sotp "
					+ "LEFT JOIN step.servicoTipo svtp "
					+ "WHERE fiscLigEsgoto.comp_id.idLigacaoEsgotoSituacao = :idLigacaoEsgotoSituacao "
					+ "AND fiscLigEsgoto.comp_id.idFiscalizacaoSituacao = :idSituacaoEncontrada ";

			retornoConsulta = (Object[]) session
					.createQuery(consulta)
					.setInteger("idLigacaoEsgotoSituacao",
							idLigacaoEsgotoSituacao)
							.setInteger("idSituacaoEncontrada", idSituacaoEncontrada)
							.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0367] Informar Retorno Ordem de Fiscalizao
	 * 
	 * [SB0001] - Atualizar Ordem de Servio
	 * 
	 * 
	 * @author Svio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public void atualizarParmsOrdemFiscalizacao(Integer numeroOS,
			String indicadorDocumentoEntregue,
			Short indicadorAtualizacaoSituacaoLigacaoAgua,
			Short indicadorAtualizacaoSituacaoLigacaoEsgoto,
			Integer idSituacaoEncontrada) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			String atualizarOS = "";

			atualizarOS = "update gcom.atendimentopublico.ordemservico.OrdemServico "
					+ "set orse_tmultimaalteracao = :ultimaAlteracao, "
					+ "orse_icatualizaagua = :indicadorAtualizacaoSituacaoLigacaoAgua, "
					+ "orse_icatualizaesgoto = :indicadorAtualizacaoSituacaoLigacaoEsgoto, "
					+ "orse_dtfiscalizacaosituacao = :ultimaAlteracao ";

			if (idSituacaoEncontrada != null) {
				atualizarOS = atualizarOS + ",fzst_id = "
						+ idSituacaoEncontrada;
			}

			if (indicadorDocumentoEntregue != null
					&& !indicadorDocumentoEntregue.equals("")) {
				atualizarOS = atualizarOS + ",orse_cdtiporecebimento = "
						+ new Short(indicadorDocumentoEntregue);
			} else {
				atualizarOS = atualizarOS + ",orse_cdtiporecebimento = null";
			}
			atualizarOS = atualizarOS + " where orse_id = :idOrdemServico";

			session.createQuery(atualizarOS)
			.setInteger("idOrdemServico", numeroOS)
			.setTimestamp("ultimaAlteracao", new Date())
			.setShort("indicadorAtualizacaoSituacaoLigacaoAgua",
					indicadorAtualizacaoSituacaoLigacaoAgua)
					.setShort("indicadorAtualizacaoSituacaoLigacaoEsgoto",
							indicadorAtualizacaoSituacaoLigacaoEsgoto)
							.executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0367] Informar Retorno Ordem de Fiscalizao
	 * 
	 * Recupera os par�metros necess�rios da OS
	 * 
	 * @author Svio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarIdFiscalizacaoSituacaoAguaOS(
			Integer idFiscalizacaoSituacao, Integer idLigacaoAguaSituacao)
					throws ErroRepositorioException {

		Object[] retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT fsag.comp_id.idLigacaoAguaSituacao, fsag.indicadorConsumoFixado, "
					+ "fzst.indicadorLigacaoDataAtualiza, fsag.ligacaoAguaSituacaoByLastIdnova.id "
					+ "FROM FiscalizacaoSituacaoAgua fsag "
					+ "LEFT JOIN fsag.fiscalizacaoSituacao fzst "
					+ "WHERE fzst.id = :idFiscalizacaoSituacao "
					+ "AND fsag.comp_id.idLigacaoAguaSituacao = :idLigacaoAguaSituacao ";

			retornoConsulta = (Object[]) session
					.createQuery(consulta)
					.setInteger("idFiscalizacaoSituacao",
							idFiscalizacaoSituacao)
							.setInteger("idLigacaoAguaSituacao", idLigacaoAguaSituacao)
							.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0367] Informar Retorno Ordem de Fiscalizao
	 * 
	 * Recupera os par�metros necess�rios da OS
	 * 
	 * @author Svio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarIdFiscalizacaoSituacaoEsgotoOS(
			Integer idFiscalizacaoSituacao, Integer idLigacaoEsgotoSituacao)
					throws ErroRepositorioException {

		Object[] retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT fseg.comp_id.idLigacaoEsgotoSituacao, fseg.indicadorConsumoFixado, "
					+ "fseg.ligacaoEsgotoSituacaoByLestIdnova.id,fzst.indicadorLigacaoDataAtualiza "
					+ "FROM FiscalizacaoSituacaoEsgoto fseg "
					+ "LEFT JOIN fseg.fiscalizacaoSituacao fzst "
					+ "WHERE fzst.id = :idFiscalizacaoSituacao "
					+ "AND fseg.comp_id.idLigacaoEsgotoSituacao = :idLigacaoEsgotoSituacao ";

			retornoConsulta = (Object[]) session
					.createQuery(consulta)
					.setInteger("idFiscalizacaoSituacao",
							idFiscalizacaoSituacao)
							.setInteger("idLigacaoEsgotoSituacao",
									idLigacaoEsgotoSituacao).setMaxResults(1)
									.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalizao
	 * 
	 * 
	 * @author Svio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarHidormetroCapacidadeFiscalizacaoAgua(
			Integer idImovel) throws ErroRepositorioException {

		Integer retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT fiscSitHidCapacidade.comp_id.idHidrometroCapacidade "
					+ "FROM FiscalizacaoSituacaoHidrometroCapacidade fiscSitHidCapacidade "
					+ "LEFT JOIN fiscSitHidCapacidade.hidrometroCapacidade hidroCapacidade "
					+ "WHERE hidroCapacidade.id in "
					+ "(SELECT hidroCapac.id "
					+ "FROM HidrometroInstalacaoHistorico hidInstHistorico "
					+ "LEFT JOIN hidInstHistorico.hidrometro hidr "
					+ "LEFT JOIN hidr.hidrometroCapacidade hidroCapac "
					+ "LEFT JOIN hidInstHistorico.ligacaoAgua ligAgua "
					+ "WHERE ligAgua.id = :idImovel )";
			retornoConsulta = (Integer) session.createQuery(consulta)
					.setInteger("idImovel", idImovel).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalizao
	 * 
	 * 
	 * @author Svio Luiz
	 * @date 15/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarHidormetroCapacidadeFiscalizacaoPoco(
			Integer idImovel) throws ErroRepositorioException {

		Integer retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT fiscSitHidCapacidade.id "
					+ "FROM FiscalizacaoSituacaoHidrometroCapacidade fiscSitHidCapacidade "
					+ "LEFT JOIN fiscSitHidCapacidade.hidrometroCapacidade hidroCapacidade "
					+ "WHERE hidroCapacidade.id in " + "(SELECT hidroCapac.id "
					+ "FROM HidrometroInstalacaoHistorico hidInstHistorico "
					+ "LEFT JOIN hidInstHistorico.hidrometro hidr "
					+ "LEFT JOIN hidr.hidrometroCapacidade hidroCapac"
					+ "LEFT JOIN hidInstHistorico.imovel imov "
					+ "WHERE imov.id = :idImovel )";
			retornoConsulta = (Integer) session.createQuery(consulta)
					.setInteger("idImovel", idImovel).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalizao
	 * 
	 * 
	 * @author Svio Luiz
	 * @date 15/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Collection pesquisarFiscalizacaoSituacaoServicoACobrar(
			Integer idFiscalizacaoSituacao) throws ErroRepositorioException {

		Collection retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT fiscSitServicoACobrar.consumoCalculo, fiscSitServicoACobrar.comp_id.idDebitoTipo, "
					+ "fiscSitServicoACobrar.valorMultaAutoInfracao "
					+ "FROM FiscalizacaoSituacaoServicoACobrar fiscSitServicoACobrar "
					+ "LEFT JOIN fiscSitServicoACobrar.fiscalizacaoSituacao fiscalizacaoSit "
					+ "WHERE fiscalizacaoSit.id = :idFiscalizacaoSituacao ";

			retornoConsulta = session
					.createQuery(consulta)
					.setInteger("idFiscalizacaoSituacao",
							idFiscalizacaoSituacao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalizao
	 * 
	 * 
	 * @author Svio Luiz
	 * @date 20/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarParmsFiscalizacaoSituacaoServicoACobrar(
			Integer idFiscalizacaoSituacao, Integer idDebitoTipo)
					throws ErroRepositorioException {

		Object[] retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT dbtp.id, fscb.numeroVezesServicoCalculadoValor, fscb.numeroVezesServicoCalculadoValorReincidencia "
					+ "FROM FiscalizacaoSituacaoServicoACobrar fscb "
					+ "LEFT JOIN fscb.fiscalizacaoSituacao fzst "
					+ "LEFT JOIN fscb.debitoTipo dbtp "
					+ "WHERE fzst.id = :idFiscalizacaoSituacao AND dbtp.id = :idDebitoTipo ";

			retornoConsulta = (Object[]) session
					.createQuery(consulta)
					.setInteger("idFiscalizacaoSituacao",
							idFiscalizacaoSituacao)
							.setInteger("idDebitoTipo", idDebitoTipo).setMaxResults(1)
							.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC0450] Pesquisar Ordem de Servico verifica o tamanho da consulta
	 * 
	 * [SB001] Selecionar Ordem de Servico por Situa��o [SB002] Selecionar
	 * Ordem de Servico por Situa��o da Programao [SB003] Selecionar Ordem
	 * de Servico por Matricula do Imovel [SB004] Selecionar Ordem de Servico
	 * por Codigo do Cliente [SB005] Selecionar Ordem de Servico por Unidade
	 * Superior [SB006] Selecionar Ordem de Servico por Municpio [SB007]
	 * Selecionar Ordem de Servico por Bairro [SB008] Selecionar Ordem de
	 * Servico por Bairro Logradouro
	 * 
	 * @author Rafael Pinto
	 * @date 18/08/2006
	 * 
	 * @param PesquisarOrdemServicoHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarOrdemServicoTamanho(
			PesquisarOrdemServicoHelper filtro) throws ErroRepositorioException {

		Integer retorno = null;

		Integer numeroRA = filtro.getNumeroRA();
		Integer numeroOS = filtro.getNumeroOS();
		Integer documentoCobranca = filtro.getDocumentoCobranca();
		short situacaoOrdemServico = filtro.getSituacaoOrdemServico();
		short situacaoProgramacao = filtro.getSituacaoProgramacao();
		Integer[] tipoServicos = filtro.getTipoServicos();

		Integer unidadeGeracao = filtro.getUnidadeGeracao();
		Integer unidadeAtual = filtro.getUnidadeAtual();

		Integer matriculaImovel = filtro.getMatriculaImovel();

		Set colecaoIdsOS = filtro.getColecaoIdsOS();

		Collection<Integer> idsUnidadesAtuais = filtro.getIdsUnidadesAtuais();
		Collection<Integer> idsPerfilImovel = filtro.getColecaoPerfilImovel();

		Date dataAtendimentoInicial = filtro.getDataAtendimentoInicial();
		Date dataAtendimentoFinal = filtro.getDataAtendimentoFinal();

		Date dataGeracaoInicial = filtro.getDataGeracaoInicial();
		Date dataGeracaoFinal = filtro.getDataGeracaoFinal();

		Date dataProgramacaoInicial = filtro.getDataProgramacaoInicial();
		Date dataProgramacaoFinal = filtro.getDataProgramacaoFinal();

		Date dataEncerramentoInicial = filtro.getDataEncerramentoInicial();
		Date dataEncerramentoFinal = filtro.getDataEncerramentoFinal();

		Integer municipio = filtro.getMunicipio();
		Integer bairro = filtro.getBairro();
		Integer areaBairro = filtro.getAreaBairro();
		Integer logradouro = filtro.getLogradouro();

		Short indicadorTerceirizado = filtro.getIndicadorTerceirizado();
		Short indicadorPavimento = filtro.getIndicadorPavimento();
		Short indicadorVistoria = filtro.getIndicadorVistoria();
		String origemOrdemServico = filtro.getOrigemOrdemServico();

		Integer idProjeto = filtro.getIdProjeto();

		if (dataProgramacaoInicial != null || dataProgramacaoFinal != null
				&& situacaoProgramacao != ConstantesSistema.SIM.shortValue()) {

			situacaoProgramacao = ConstantesSistema.SIM.shortValue();
		}

		Session session = HibernateUtil.getSession();

		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		try {

			consulta = "SELECT count(distinct os.orse_id) as qtdeIdOS " // 0
					+ " FROM atendimentopublico.ordem_servico_unidade osUnidade  "
					+ " INNER JOIN atendimentopublico.atendimento_relacao_tipo art ON osUnidade.attp_id = art.attp_id  "
					+ " INNER JOIN atendimentopublico.ordem_servico os ON osUnidade.orse_id = os.orse_id ";

			if (unidadeGeracao != null) {
				// || unidadeAtual != null) {

				consulta = consulta
						+ " INNER JOIN cadastro.unidade_organizacional unid ON osUnidade.unid_id = unid.unid_id ";
			}

			/*
			 * Alterado por Raphael Rossiter em 04/08/2008
			 */
			/*
			 * if (tipoServicos != null) { consulta = consulta +
			 * " INNER JOIN os.servicoTipo servicotipo "; }
			 */
			if (tipoServicos != null || indicadorTerceirizado != null
					|| indicadorPavimento != null || indicadorVistoria != null) {
				consulta = consulta
						+ " INNER JOIN atendimentopublico.servico_tipo servicotipo ON os.svtp_id = servicotipo.svtp_id ";

			}

			// if (numeroRA != null || dataAtendimentoInicial != null ||
			// dataAtendimentoFinal != null
			// // || unidadeAtual != null
			// || areaBairro != null || bairro != null || municipio != null
			// || logradouro != null) {
			consulta = consulta
					+ " LEFT JOIN atendimentopublico.registro_atendimento ra ON os.rgat_id = ra.rgat_id  ";
			// }

			// incluso da coluna unidade atual na tabela REGISTRO_ATENDIMENTO
			// e ORDEM_SERVICO
			// Vivianne Sousa 11/06/2008 analista:Fatima Sampaio
			// if (unidadeAtual != null) {
			// // consulta = consulta + " LEFT JOIN ra.tramites tramite ";
			// consulta = consulta
			// + " LEFT JOIN os.unidadeAtual unidAtual  ";
			// }

			// if (documentoCobranca != null) {
			consulta = consulta
					+ " LEFT JOIN cobranca.cobranca_documento cobra ON os.cbdo_id = cobra.cbdo_id ";
			// }

			if (matriculaImovel != null || areaBairro != null || bairro != null
					|| municipio != null || logradouro != null) {
				consulta = consulta
						+ " LEFT JOIN cadastro.imovel imovel ON os.imov_id = imovel.imov_id ";

			}

			consulta = consulta
					+ " LEFT JOIN cadastro.imovel imov on (os.imov_id = imov.imov_id) "
					+ "LEFT JOIN cadastro.imovel_perfil imovel_perfil on (imov.iper_id = imovel_perfil.iper_id) and (imovel_perfil.iper_icuso = 1) ";

			if (areaBairro != null || bairro != null || municipio != null
					|| logradouro != null) {

				consulta = consulta
						+ " LEFT JOIN cadastro.logradouro_bairro imovlogrbair ON imovel.lgbr_id = imovlogrbair.lgbr_id "
						+ " LEFT JOIN cadastro.logradouro imovlogr ON imovlogrbair.logr_id = imovlogr.logr_id  "
						+ " LEFT JOIN cadastro.bairro imovbair ON imovlogrbair.bair_id = imovbair.bair_id "
						+ " LEFT JOIN cadastro.municipio imovmun ON imovbair.muni_id = imovmun.muni_id "
						+ " LEFT JOIN cadastro.logradouro_bairro logrbair ON ra.lgbr_id = logrbair.lgbr_id "
						+ " LEFT JOIN cadastro.bairro bair ON logrbair.bair_id = bair.bair_id "
						+ " LEFT JOIN cadastro.logradouro logr ON logrbair.logr_id = logr.logr_id "
						+ " LEFT JOIN cadastro.municipio mun ON bair.muni_id = mun.muni_id "
						+ " LEFT JOIN cadastro.bairro_area barea ON ra.brar_id = barea.brar_id ";

			}

			if (idProjeto != null) {

				consulta = consulta
						+ " INNER JOIN cadastro.projeto proj ON os.proj_id = proj.proj_id ";
			}

			consulta = consulta + " WHERE 1=1 ";

			if (numeroRA != null) {
				consulta += " AND ra.rgat_id = (:numeroRA) ";
				parameters.put("numeroRA", numeroRA);
			}

			if (idProjeto != null) {
				consulta += " AND proj.proj_id = (:idProjeto) ";
				parameters.put("idProjeto", idProjeto);
			}

			if (documentoCobranca != null) {
				consulta += " AND cobra.cbdo_id = (:documentoCobranca) ";
				parameters.put("documentoCobranca", documentoCobranca);
			}

			// [SB0001] - Selecionar Ordem de Servico por Situa��o
			if (situacaoOrdemServico != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				consulta += " AND os.orse_cdsituacao = (:situacaoOrdemServico) ";
				parameters.put("situacaoOrdemServico", situacaoOrdemServico);
			}

			// Tipo de Servios
			if (tipoServicos != null && tipoServicos.length > 0) {
				consulta += " AND servicotipo.svtp_id in (:idServicoTipo) ";
				parameters.put("idServicoTipo", tipoServicos);
			}

			// [SB0003] - Selecionar Ordem de Servico por Matricula do Im�vel
			if (matriculaImovel != null) {
				consulta += " AND imovel.imov_id = (:matriculaImovel) ";
				parameters.put("matriculaImovel", matriculaImovel);

			}

			// Perfil do imovel
			if (idsPerfilImovel != null && !idsPerfilImovel.isEmpty()) {
				consulta += " AND imovel_perfil.iper_id in (:idPerfilImovel) ";
				parameters.put("idPerfilImovel", idsPerfilImovel);
			}

			// Unidade Gera��o
			if (unidadeGeracao != null) {

				consulta += "AND unid.unid_id = :idUnidadeGeracao "
						+ "AND art.attp_id = :idAtendimentoTipo ";

				parameters.put("idUnidadeGeracao", unidadeGeracao);
				parameters.put("idAtendimentoTipo",
						AtendimentoRelacaoTipo.ABRIR_REGISTRAR);

			}

			// Unidade Atual
			if (unidadeAtual != null) {

				// incluso da coluna unidade atual na tabela
				// REGISTRO_ATENDIMENTO e ORDEM_SERVICO
				// Vivianne Sousa 11/06/2008 analista:Fatima Sampaio
				consulta += " AND os.unid_idatual = (:idUnidadeAtual) ";
				parameters.put("idUnidadeAtual", unidadeAtual);
			}

			if (idsUnidadesAtuais != null) {
				consulta += " AND os.unid_idatual IN (:idUnidadesAtuais) ";
				parameters.put("idUnidadesAtuais", idsUnidadesAtuais);
			}

			if (filtro.getColecaoAtendimentoMotivoEncerramento() != null
					&& filtro.getColecaoAtendimentoMotivoEncerramento().size() > 0) {
				consulta += " AND os.amen_id IN (:colecaoAtendimentoMotivoEncerramento) ";
				parameters.put("colecaoAtendimentoMotivoEncerramento",
						filtro.getColecaoAtendimentoMotivoEncerramento());
			}

			if (numeroOS != null) {
				consulta += " AND os.orse_id = :numeroOS ";
				parameters.put("numeroOS", numeroOS);

				// Ids de Os das unidades filtradas (geracao, atual e superior)
				// Ids de Os dos clientes
			} else if (colecaoIdsOS != null && !colecaoIdsOS.isEmpty()) {
				consulta += " AND os.orse_id in (:listaIdOS) ";
				parameters.put("listaIdOS", colecaoIdsOS);
			}

			// Perodo de Atendimento
			if (dataAtendimentoInicial != null && dataAtendimentoFinal != null) {
				consulta += " AND ra.rgat_tmregistroatendimento BETWEEN (:dataAtendimentoInicial) AND (:dataAtendimentoFinal) ";
				parameters.put("dataAtendimentoInicial",
						Util.formatarDataInicial(dataAtendimentoInicial));
				parameters.put("dataAtendimentoFinal",
						Util.formatarDataFinal(dataAtendimentoFinal));
			}

			// Perodo de Data Geracao
			if (dataGeracaoInicial != null && dataGeracaoFinal != null) {

				consulta += " AND os.orse_tmgeracao BETWEEN (:dataGeracaoInicial) AND (:dataGeracaoFinal) ";
				parameters.put("dataGeracaoInicial",
						Util.formatarDataInicial(dataGeracaoInicial));
				parameters.put("dataGeracaoFinal",
						Util.formatarDataFinal(dataGeracaoFinal));
			}

			// Perodo de Data Encerramento
			if (dataEncerramentoInicial != null
					&& dataEncerramentoFinal != null) {

				consulta += " AND os.orse_tmencerramento BETWEEN (:dataEncerramentoInicial) AND (:dataEncerramentoFinal) ";
				parameters.put("dataEncerramentoInicial",
						Util.formatarDataInicial(dataEncerramentoInicial));
				parameters.put("dataEncerramentoFinal",
						Util.formatarDataFinal(dataEncerramentoFinal));
			}

			// [SB0006] - Selecionar Ordem de Servico por Municpio
			if (municipio != null) {

				if (origemOrdemServico.equals(OrdemServico.TODAS)) {
					// 1.1. Caso tenha selecionado a opo Todas

					// 1.1.1
					consulta += " AND ( mun.muni_id = :municipioId ";

					// 1.1.2
					consulta += " OR  (imovmun.muni_id=:municipioId AND ra.rgat_id is not null AND ra.lgbr_id is null) ";

					// 1.1.3
					consulta += "  OR  (imovmun.muni_id=:municipioId AND ra.rgat_id is null)) ";

				} else if (origemOrdemServico.equals(OrdemServico.SOLICITADAS)) {
					// 1.2. Caso tenha selecionado a opo Solicitadas

					// 1.2.1
					consulta += " AND (mun.muni_id = :municipioId ";

					// 1.2.2
					consulta += " OR (imovmun.muni_id = :municipioId AND ra.rgat_id is not null AND ra.lgbr_id is null)) ";

				} else if (origemOrdemServico
						.equals(OrdemServico.SELETIVAS_COBRANCA)) {
					// 1.3 Caso tenha selecionado a opo Seletivas de
					// Cobran�a

					// 1.3.1
					consulta += " AND (imovmun.muni_id = :municipioId AND cobra.cbdo_id is not null) ";

				} else if (origemOrdemServico
						.equals(OrdemServico.SELETIVAS_HIDROMETRO)) {
					// 1.4 Caso tenha selecionado a opo Seletivas de
					// Hidrometro

					// 1.4.1
					consulta += " AND (imovmun.muni_id = :municipioId AND cobra.cbdo_id is null and ra.rgat_id is null) ";

				}
				parameters.put("municipioId", new Integer(municipio));
			}

			// [SB0007] - Selecionar Ordem de Servico por Bairro
			if (bairro != null && !bairro.equals("")) {

				if (origemOrdemServico.equals(OrdemServico.TODAS)) {
					// 1.1. Caso tenha selecionado a opo Todas

					// 1.1.1
					consulta += " AND (bair.bair_id = :bairroId ";

					// 1.1.2
					consulta += " OR (imovbair.bair_id = :bairroId AND ra.rgat_id is not null AND ra.lgbr_id is null) ";

					// 1.1.3
					consulta += " OR (imovbair.bair_id = :bairroId AND ra.rgat_id is null))  ";

				} else if (origemOrdemServico.equals(OrdemServico.SOLICITADAS)) {
					// 1.2. Caso tenha selecionado a opo Solicitadas

					// 1.2.1
					consulta += " AND (bair.bair_id = :bairroId ";

					// 1.2.2
					consulta += " OR (imovbair.bair_id = :bairroId AND ra.rgat_id is not null AND ra.lgbr_id is null)) ";

				} else if (origemOrdemServico
						.equals(OrdemServico.SELETIVAS_COBRANCA)) {
					// 1.3 Caso tenha selecionado a opo Seletivas de
					// Cobran�a

					// 1.3.1
					consulta += " AND (imovbair.bair_id = :bairroId AND cobra.cbdo_id is not null) ";

				} else if (origemOrdemServico
						.equals(OrdemServico.SELETIVAS_HIDROMETRO)) {
					// 1.4 Caso tenha selecionado a opo Seletivas de
					// Hidrometro

					// 1.4.1
					consulta += " AND (imovbair.bair_id = :bairroId AND cobra.cbdo_id is null and ra.rgat_id is null) ";

				}

				parameters.put("bairroId", new Integer(bairro));

				if (areaBairro != null) {
					consulta += " AND barea.brar_id = :bairroAreaId  ";
					parameters.put("bairroAreaId", areaBairro);
				}
			}

			// [SB0008] - Selecionar Ordem de Servico por Logradouro
			if (logradouro != null && !logradouro.equals("")) {

				if (origemOrdemServico.equals(OrdemServico.TODAS)) {
					// 1.1. Caso tenha selecionado a opo Todas

					// 1.1.1
					consulta += "  AND (logr.logr_id = :logradouroId  ";

					// 1.1.2
					consulta += " OR  (imovlogr.logr_id = :logradouroId AND ra.rgat_id is not null AND ra.lgbr_id is null)  ";

					// 1.1.3
					consulta += " OR  (imovlogr.logr_id = :logradouroId AND ra.rgat_id is null))  ";

				} else if (origemOrdemServico.equals(OrdemServico.SOLICITADAS)) {
					// 1.2. Caso tenha selecionado a opo Solicitadas

					// 1.2.1
					consulta += " AND (logr.logr_id = :logradouroId ";

					// 1.2.2
					consulta += " OR  (imovlogr.logr_id = :logradouroId AND ra.rgat_id is not null AND ra.lgbr_id is null)) ";

				} else if (origemOrdemServico
						.equals(OrdemServico.SELETIVAS_COBRANCA)) {
					// 1.3 Caso tenha selecionado a opo Seletivas de
					// Cobran�a

					// 1.3.1
					consulta += " AND (imovlogr.logr_id = :logradouroId AND cobra.cbdo_id is not null) ";

				} else if (origemOrdemServico
						.equals(OrdemServico.SELETIVAS_HIDROMETRO)) {
					// 1.4 Caso tenha selecionado a opo Seletivas de
					// Hidrometro

					// 1.4.1
					consulta += " AND (imovlogr.logr_id = :logradouroId AND cobra.cbdo_id is null and ra.rgat_id is null) ";

				}

				parameters.put("logradouroId", new Integer(logradouro));
			}

			// indicador de terceiriza��o
			if (indicadorTerceirizado != null) {
				consulta += " AND servicotipo.svtp_icterceirizado = (:icTerceirizado) ";
				parameters.put("icTerceirizado", indicadorTerceirizado);
			}

			// indicador de pavimento
			if (indicadorPavimento != null) {
				consulta += " AND (servicotipo.svtp_icpvtorua = (:icPavimentoRua) or servicotipo.svtp_icpvtocal = (:icPavimentoCalcada)) ";
				parameters.put("icPavimentoRua", indicadorPavimento);
				parameters.put("icPavimentoCalcada", indicadorPavimento);
			}

			// indicador de vistoria
			if (indicadorVistoria != null) {
				consulta += " AND servicotipo.svtp_icvistoria = (:icVistoria) ";
				parameters.put("icVistoria", indicadorVistoria);
			}

			if (areaBairro == null && bairro == null && municipio == null
					&& logradouro == null) {

				// Origem da OS
				// SB0009 - Selciona Ordem de serv�o por origem
				if (origemOrdemServico.equals(OrdemServico.SOLICITADAS)) {

					consulta += " AND ra.rgat_id is not null ";

				} else if (origemOrdemServico
						.equals(OrdemServico.SELETIVAS_COBRANCA)) {

					consulta += " AND cobra.cbdo_id is not null ";

				} else if (origemOrdemServico
						.equals(OrdemServico.SELETIVAS_HIDROMETRO)) {

					consulta += " AND ra.rgat_id is null AND cobra.cbdo_id is null ";

				}

			}

			query = session.createSQLQuery(consulta);
			query.addScalar("qtdeIdOS", Hibernate.INTEGER);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (Set) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (Collection) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Integer[]) {
					Integer[] collection = (Integer[]) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalizao
	 * 
	 * 
	 * 
	 * @author Svio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarIdServicoTipoDaOS(Integer idOS)
			throws ErroRepositorioException {

		Integer retornoConsulta = null;

		Session session = HibernateUtil.getSession();
		try {

			String consulta = "SELECT servicoTipo.id "
					+ "FROM OrdemServico os "
					+ "INNER JOIN os.servicoTipo servicoTipo "
					+ "WHERE os.id = :idOS ";

			retornoConsulta = (Integer) session.createQuery(consulta)
					.setInteger("idOS", idOS).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retornoConsulta;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalizao
	 * 
	 * @author Raphael Rossiter
	 * @date 25/01/2007
	 * 
	 * @param idOS
	 * @return fiscalizacaoSituacao
	 * @throws ControladorException
	 */
	public FiscalizacaoSituacao pesquisarFiscalizacaoSituacaoPorOS(Integer idOS)
			throws ErroRepositorioException {

		FiscalizacaoSituacao retornoConsulta = null;

		Session session = HibernateUtil.getSession();
		try {

			String consulta = "SELECT fzst " + "FROM OrdemServico os "
					+ "INNER JOIN os.fiscalizacaoSituacao fzst "
					+ "WHERE os.id = :idOS ";

			retornoConsulta = (FiscalizacaoSituacao) session
					.createQuery(consulta).setInteger("idOS", idOS)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retornoConsulta;
	}

	/**
	 * Consulta a ordem de servico pelo id
	 * 
	 * @author Svio Luiz
	 * @date 03/01/2007
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public OrdemServico pesquisarDadosServicoTipoPrioridade(
			Integer idServicoTipo) throws ErroRepositorioException {

		OrdemServico ordemServico = null;

		Integer retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT "
					+ "servicoTipoPrioridade.id "// 0
					+ "FROM ServicoTipo servTipo "
					+ "LEFT JOIN servTipo.servicoTipoPrioridade servicoTipoPrioridade "
					+ "WHERE servTipo.id = :idServicoTipo ";

			retornoConsulta = (Integer) session.createQuery(consulta)
					.setInteger("idServicoTipo", idServicoTipo)
					.setMaxResults(1).uniqueResult();

			if (retornoConsulta != null) {

				ordemServico = new OrdemServico();

				ServicoTipo servicoTipo = new ServicoTipo();
				ServicoTipoPrioridade servicoTipoPrioridade = new ServicoTipoPrioridade();
				servicoTipoPrioridade.setId(retornoConsulta);
				servicoTipo.setServicoTipoPrioridade(servicoTipoPrioridade);

				ordemServico.setServicoTipo(servicoTipo);
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return ordemServico;
	}

	/**
	 * Atualiza os imoveis da OS que refere a RA passada como parametro
	 * 
	 * @author Svio Luiz
	 * @date 03/01/2007
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public void atualizarOsDaRA(Integer idRA, Integer idImovel)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			String sql = "Update OrdemServico "
					+ " set imov_id = :idImovel, orse_tmultimaalteracao = :data"
					+ " where rgat_id = :idRA";

			session.createQuery(sql).setInteger("idRA", idRA)
			.setInteger("idImovel", idImovel)
			.setTimestamp("data", new Date()).executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0455] Exibir Calend�rio para Elabora��o ou Acompanhamento de
	 * Roteiro
	 * 
	 * @author Raphael Rossiter
	 * @date 14/02/2007
	 * 
	 * @param idProgramacaoRoteiro
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaOSProgramacao(Integer idProgramacaoRoteiro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		try {

			String consulta = "SELECT COUNT(*) "
					+ "FROM OrdemServicoProgramacao ospg "
					+ "INNER JOIN ospg.programacaoRoteiro pgrt "
					+ "WHERE pgrt.id = :idProgramacaoRoteiro AND ospg.indicadorAtivo = :ativo ";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idProgramacaoRoteiro", idProgramacaoRoteiro)
					.setShort("ativo", OrdemServicoProgramacao.INDICADOR_ATIVO)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Pesquisa as Os do Servio tipo especifico com RA
	 * 
	 * @author Svio Luiz
	 * @date 23/02/2007
	 * 
	 * @param idProgramacaoRoteiro
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOSComServicoTipo(Integer idServicoTipo)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		try {

			String consulta = "select orse_id as idOS "
					+ "from atendimentopublico.ordem_servico "
					+ "where orse_cdsituacao = :situacaoOS and  rgat_id is not null and svtp_id = :idServicoTipo";

			retorno = session.createSQLQuery(consulta)
					.addScalar("idOs", Hibernate.INTEGER)
					.setInteger("idServicoTipo", idServicoTipo)
					.setShort("situacaoOS", OrdemServico.SITUACAO_PENDENTE)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Pesquisa as Os do Servio tipo especifico com RA
	 * 
	 * @author Svio Luiz
	 * @date 23/02/2007
	 * 
	 * @param idProgramacaoRoteiro
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public void atualizarColecaoOS(Collection colecaoIdsOS)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {

			String consulta = "update  gcom.atendimentopublico.ordemservico.OrdemServico "
					+ "set  orse_cdsituacao = :situacaoOS,amen_id = :idAtendimentoMotivoEncerramento,"
					+ "orse_tmencerramento = :dataAtual,orse_tmultimaalteracao = :dataAtual "
					+ "where orse_id in (:idsOS)";

			session.createQuery(consulta)
			.setShort("situacaoOS", OrdemServico.SITUACAO_ENCERRADO)
			.setInteger("idAtendimentoMotivoEncerramento",
					AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO)
					.setParameterList("idsOS", colecaoIdsOS)
					.setTimestamp("dataAtual", new Date()).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * 
	 * Pesquisar data e equipe da programao da ordem Servio
	 * 
	 * @author Ana Maria
	 * 
	 * @date 09/03/2007
	 */
	public OrdemServicoProgramacao pesquisarDataEquipeOSProgramacao(Integer idOs)
			throws ErroRepositorioException {

		OrdemServicoProgramacao ordemServicoProgramacao = null;
		Object[] retornoConsulta = null;

		Session session = HibernateUtil.getSession();
		try {

			String consulta = "select pgrt.dataRoteiro, eqpe.nome "+
					"from OrdemServicoProgramacao ospg "+ 
					"inner join ospg.ordemServico orse "+
					"inner join ospg.programacaoRoteiro pgrt "+
					"inner join ospg.equipe eqpe "+
					"where orse.id = :idOs" +
					" and ospg.indicadorAtivo = :ativo" +
					" and ospg.situacaoFechamento = :situacao";

			retornoConsulta = (Object[]) session.createQuery(consulta)
					.setInteger("idOs", idOs)
					.setShort("ativo", OrdemServicoProgramacao.INDICADOR_ATIVO)
					.setShort("situacao", OrdemServicoProgramacao.SITUACAO_FECHAMENTO)
					.setMaxResults(1).uniqueResult();

			if (retornoConsulta != null) {

				ordemServicoProgramacao = new OrdemServicoProgramacao();

				ProgramacaoRoteiro programacaoRoteiro = new ProgramacaoRoteiro();
				programacaoRoteiro.setDataRoteiro((Date) retornoConsulta[0]);

				ordemServicoProgramacao
				.setProgramacaoRoteiro(programacaoRoteiro);

				Equipe equipe = new Equipe();
				equipe.setNome((String) retornoConsulta[1]);

				ordemServicoProgramacao.setEquipe(equipe);

			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return ordemServicoProgramacao;
	}

	public Collection pesquisaParaCriarDebitosNaoGerados()
			throws ErroRepositorioException {
		return null;
	}

	public Collection pesquisaParaCriarDebitosCategoriaNaoGerados()
			throws ErroRepositorioException {
		return null;
	}

	/**
	 * [UC0457] Encerra Ordem de Servio
	 * 
	 * @author Raphael Rossiter
	 * @date 25/04/2007
	 * 
	 * @param idServicoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarServicoTipoOperacao(Integer idServicoTipo)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		try {

			String consulta = "SELECT stop.comp_id.idOperacao "
					+ "FROM ServicoTipoOperacao stop "
					+ "WHERE stop.comp_id.idServicoTipo = :idServicoTipo ";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idServicoTipo", idServicoTipo)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0711] Filtro para Emissao de Ordens Seletivas
	 * 
	 * @author Ivan S�rgio, Ivan S�rgio, Raphael Rossiter, Anderson Italo
	 * @date 08/11/2007, 27/03/2008, 17/04/2009, 20/05/2009
	 * @alteracao: Media do Imovel e Consumo por Economia com os valores >= ao
	 *             informado 20/05/2009 - CRC1894
	 * 
	 * @param ImovelEmissaoOrdensSeletivasHelper
	 *            , dataInstalacaoHidrometro, anoMesFaturamento
	 * @return List
	 * @throws ErroRepositorioException
	 */
	public List filtrarImovelEmissaoOrdensSeletivas(
			ImovelEmissaoOrdensSeletivasHelper helper,
			String dataInstalacaoHidrometroInicial, String anoMesFaturamento,
			String dataInstalacaoHidrometroFinal, Short codigoEmpresaFebraban)
					throws ErroRepositorioException {

		List retorno = null;
		String hqlAux = "";
		boolean finaliza = false;

		Session session = HibernateUtil.getSession();

		try {

			String hql = "";
			// CRC4641 - adicionado por Vivianne Sousa - 22/06/2010
			// analista: Ana Cristina
			if (codigoEmpresaFebraban
					.equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)) {
				hql += "select distinct "
						+ // O distinct foi utilizado por conta das
						// sub-categorias
						"	imovel.id, " + "	localidade.id, "
						+ "	setorComercial.codigo, "
						+ "   quadra.rota.codigo, "
						+ "   imovel.numeroSequencialRota, "
						+ "   localidade.descricao, "
						+ "   setorComercial.descricao " + "from ";

			} else {
				hql += "select distinct "
						+ // O distinct foi utilizado por conta das
						// sub-categorias
						"	imovel.id, " + "	localidade.id, "
						+ "	setorComercial.codigo, " + "	quadra.numeroQuadra, "
						+ "	imovel.lote, " + "	imovel.subLote, "
						+ "   localidade.descricao, "
						+ "   setorComercial.descricao " + "from ";
			}

			// String hql =
			// "select distinct " + // O distinct foi utilizado por conta das
			// sub-categorias
			// "	imovel.id, " +
			// "	localidade.id, " +
			// "	setorComercial.codigo, " +
			// "	quadra.numeroQuadra, " +
			// "	imovel.lote, " +
			// "	imovel.subLote " +
			// "from ";

			// Se informou Categoria ou SubCategoria
			if (helper.getCategoria() != null
					|| helper.getSubCategoria() != null) {
				hql += "	gcom.cadastro.imovel.ImovelSubcategoria imovelSubcategoria "
						+ "	inner join imovelSubcategoria.comp_id.imovel imovel "
						+ "	left  join imovelSubcategoria.comp_id.subcategoria subcategoria "
						+ "	left  join subcategoria.categoria categoria ";
			} else {
				hql += "	gcom.cadastro.imovel.Imovel imovel ";
			}

			// se informou Logradouro
			if (helper.getLogradouro() != null
					&& !helper.getLogradouro().equals("")) {
				hql += "	left  join imovel.logradouroCep logradouroCep "
						+ "	left  join logradouroCep.logradouro logradouro ";
			}

			hql += "	inner join imovel.localidade localidade ";

			// Se unidade/gerencia ou elo
			if (helper.getGerenciaRegional() != null
					&& !helper.getGerenciaRegional().equals(
							ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				hql += "	inner join localidade.gerenciaRegional gerenciaRegional ";
			}

			if ((helper.getUnidadeNegocio() != null && !helper
					.getUnidadeNegocio().equals(
							ConstantesSistema.NUMERO_NAO_INFORMADO))
							|| (helper.getElo() != null & !helper.getElo().equals(""))) {
				hql += "	inner join localidade.unidadeNegocio unidadeNegocio ";
			}

			hql += "	inner join imovel.setorComercial setorComercial "
					+ "	inner join imovel.quadra quadra "
					+ "		with quadra.setorComercial.id = setorComercial.id "
					+ "  inner join quadra.rota rota ";

			// Se informou Perfil Imovel
			if (helper.getPerfilImovel() != null) {
				hql += "	left  join imovel.imovelPerfil imovelPerfil ";
			}

			hql += "	left  join imovel.ligacaoAgua ligacaoAgua "
					+ "	left  join ligacaoAgua.medicaoHistoricos medicaoHistorico ";

			// Tipo Medicao
			if (helper.getTipoMedicao() != null) {
				// hql += "and medicaoHistorico.medicaoTipo.id = " + tipoMedicao
				// + " ";
				hql += "with medicaoHistorico.medicaoTipo.id = "
						+ helper.getTipoMedicao() + " "
						+ "and medicaoHistorico.anoMesReferencia = "
						+ anoMesFaturamento + " ";
			}

			if ((helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO)) 
							|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_EFETUAR_REMOCAO_HIDROMETRO))
							|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_INSPECAO_ANORMALIDADE)) 
							|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_SUBSTITUICAO_HIDROMETRO_CONTROLE_DE_PERDAS))
							|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_SUBSTITUICAO_RAMAL_COM_SUBSTITUICAO_HDT_CONTROLE_DE_PERDAS))
							|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_SUBSTITUICAO_RAMAL_SEM_INST_HDT_CONTROLE_DE_PERDAS))
							|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_SUBSTITUICAO_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS))
							|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_SUBSTITUICAO_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS))
							|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_SUBSTITUICAO_RAMAL_SEM_INST_HDT_CONTROLE_DE_PERDAS))
							|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_INSTALACAO_HIDROMETRO_CONTROLE_DE_PERDAS)) 
							|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_SUBSTITUICAO_HIDROMETRO_CONTROLE_DE_PERDAS))
							|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_REFORMA_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS))
							|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_REFORMA_RAMAL_SEM_HINST_DT_CONTROLE_DE_PERDAS))
							|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_REFORMA_RAMAL_COM_SUBSTITUICAO_HDT_CONTROLE_DE_PERDAS))) {
				hql += " left  join ligacaoAgua.hidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoAgua "
						+ " left  join hidrometroInstalacaoHistoricoAgua.hidrometro hidrometroAgua "
						+ " left  join imovel.hidrometroInstalacaoHistorico hidrometroInstalacaoHistorico "
						+ " left  join hidrometroInstalacaoHistorico.hidrometro hidrometro ";
				/*
				 * // Local Instala��o Hidrometro
				 * if(helper.getLocalInstalacaoHidrometro() != null){ hql +=
				 * " left join hidrometroInstalacaoHistoricoAgua.hidrometroLocalInstalacao localInstalacaoAgua "
				 * +
				 * " left join hidrometroInstalacaoHistorico.hidrometroLocalInstalacao localInstalacaoEsg "
				 * ; }
				 */
			}

			hql += "	left  join imovel.consumosHistoricos consumosHistorico "
					+ "		with consumosHistorico.referenciaFaturamento <= "
					+ anoMesFaturamento + " ";

			if (helper.getTipoMedicao() != null) {
				if (helper.getTipoMedicao().equals(
						MedicaoTipo.LIGACAO_AGUA.toString())) {
					hql += "and consumosHistorico.ligacaoTipo.id = "
							+ LigacaoTipo.LIGACAO_AGUA + " ";
					hql += "	left  join imovel.ligacaoAguaSituacao ligacaoAguaSituacao ";
				}

				if (helper.getTipoMedicao().equals(MedicaoTipo.POCO.toString())) {
					hql += "and consumosHistorico.ligacaoTipo.id = "
							+ LigacaoTipo.LIGACAO_ESGOTO + " ";
					hql += "	left  join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao ";
				}
			}

			hql += "where " + "	imovel.indicadorExclusao = 2 and ";

			if (helper.getIdImovel() != null) {
				hql += "	imovel.id = " + helper.getIdImovel().toString()
						+ " and ";
			}

			if (helper.getTipoMedicao() != null) {
				// indicador de faturamento de �gua =1
				if (helper.getTipoMedicao().equals(
						MedicaoTipo.LIGACAO_AGUA.toString())) {
					hqlAux += "ligacaoAguaSituacao.indicadorFaturamentoSituacao = 1 and ";
					hqlAux += "ligacaoAguaSituacao.id = imovel.ligacaoAguaSituacao and ";
					// indicador de faturamento de esgoto =1
				} else if (helper.getTipoMedicao().equals(
						MedicaoTipo.POCO.toString())) {
					hqlAux += "ligacaoEsgotoSituacao.indicadorFaturamentoSituacao = 1 and ";
					hqlAux += "ligacaoEsgotoSituacao.id = imovel.ligacaoEsgotoSituacao and ";
				}
			}

			// Seleciona os Imoveis de acordo com o Tipo de Ordem
			if (helper.getTipoOrdem().equals("" + ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO) 
					|| (helper.getTipoOrdem().equals("" + ServicoTipo.TIPO_INSTALACAO_HIDROMETRO_CONTROLE_DE_PERDAS))
					|| (helper.getTipoOrdem().equals("" + ServicoTipo.TIPO_INSTALACAO_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS))
					|| (helper.getTipoOrdem().equals("" + ServicoTipo.TIPO_INSTALACAO_RAMAL_SEM_INST_HDT_CONTROLE_DE_PERDAS)))  {
				if (helper.getTipoMedicao() != null) {
					if (helper.getTipoMedicao().equals(MedicaoTipo.LIGACAO_AGUA.toString())) {
						// hqlAux += "(imovel.ligacaoAguaSituacao.id = " +
						// LigacaoAguaSituacao.LIGADO + " or ";
						// hqlAux += "imovel.ligacaoAguaSituacao.id = " +
						// LigacaoAguaSituacao.LIGADO_A_REVELIA + ") and ";
						hqlAux += "ligacaoAgua.hidrometroInstalacaoHistorico.id is null and ";
						finaliza = true;
					} else if (helper.getTipoMedicao().equals(MedicaoTipo.POCO.toString())) {
						// hqlAux += "imovel.ligacaoEsgotoSituacao.id = " +
						// LigacaoEsgotoSituacao.LIGADO + " and ";
						hqlAux += "imovel.hidrometroInstalacaoHistorico.id is null and ";
					}
				}
			} else if (helper.getTipoOrdem().equals("" + ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO) 
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_SUBSTITUICAO_HIDROMETRO_CONTROLE_DE_PERDAS))
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_SUBSTITUICAO_RAMAL_COM_SUBSTITUICAO_HDT_CONTROLE_DE_PERDAS))
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_SUBSTITUICAO_RAMAL_SEM_INST_HDT_CONTROLE_DE_PERDAS)) 
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_SUBSTITUICAO_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS)) 
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_SUBSTITUICAO_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS)) 
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_SUBSTITUICAO_RAMAL_SEM_INST_HDT_CONTROLE_DE_PERDAS)) 
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_INSTALACAO_HIDROMETRO_CONTROLE_DE_PERDAS)) 
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_SUBSTITUICAO_HIDROMETRO_CONTROLE_DE_PERDAS)) 
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_SUBSTITUICAO_RAMAL_COM_SUBSTITUICAO_HDT_CONTROLE_DE_PERDAS))
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_REFORMA_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS))
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_REFORMA_RAMAL_SEM_HINST_DT_CONTROLE_DE_PERDAS))
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_REFORMA_RAMAL_COM_SUBSTITUICAO_HDT_CONTROLE_DE_PERDAS))) {
				if (helper.getTipoMedicao() != null) {
					if (helper.getTipoMedicao().equals(MedicaoTipo.LIGACAO_AGUA.toString())) {
						// hqlAux += "(imovel.ligacaoAguaSituacao.id = " +
						// LigacaoAguaSituacao.LIGADO + " or ";
						// hqlAux += "imovel.ligacaoAguaSituacao.id = " +
						// LigacaoAguaSituacao.LIGADO_A_REVELIA + ") and ";
						hqlAux += "hidrometroInstalacaoHistoricoAgua.id is not null and "; 
						hqlAux += "hidrometroInstalacaoHistoricoAgua.dataRetirada is null and ";
						if (dataInstalacaoHidrometroFinal != null
								&& !dataInstalacaoHidrometroFinal.equals("")) {

							if (dataInstalacaoHidrometroInicial != null
									&& !dataInstalacaoHidrometroInicial
									.equals("")) {
								hqlAux += "hidrometroInstalacaoHistoricoAgua.dataInstalacao between '"
										+ dataInstalacaoHidrometroInicial
										+ "' and '"
										+ dataInstalacaoHidrometroFinal
										+ "' and ";
							} else {
								hqlAux += "hidrometroInstalacaoHistoricoAgua.dataInstalacao <= '"
										+ dataInstalacaoHidrometroFinal
										+ "' and ";
							}

						}

						finaliza = true;
					} else if (helper.getTipoMedicao().equals(
							MedicaoTipo.POCO.toString())) {
						// hqlAux += "imovel.ligacaoEsgotoSituacao.id = " +
						// LigacaoEsgotoSituacao.LIGADO + " and ";
						hqlAux += "hidrometroInstalacaoHistorico.id is not null and ";
						if (dataInstalacaoHidrometroFinal != null
								&& !dataInstalacaoHidrometroFinal.equals("")) {

							if (dataInstalacaoHidrometroInicial != null
									&& !dataInstalacaoHidrometroInicial
									.equals("")) {
								hqlAux += "hidrometroInstalacaoHistoricoAgua.dataInstalacao between '"
										+ dataInstalacaoHidrometroInicial
										+ "' and '"
										+ dataInstalacaoHidrometroFinal
										+ "' and ";
							} else {
								hqlAux += "hidrometroInstalacaoHistoricoAgua.dataInstalacao <= '"
										+ dataInstalacaoHidrometroFinal
										+ "' and ";
							}

						}

						finaliza = true;
					}

					// Local Instala��o Hidrometro
					if (helper.getLocalInstalacaoHidrometro() != null) {
						if (helper.getTipoMedicao().equals(
								MedicaoTipo.LIGACAO_AGUA.toString())) {
							hqlAux += "hidrometroInstalacaoHistoricoAgua.hidrometroLocalInstalacao.id = "
									+ helper.getLocalInstalacaoHidrometro()
									+ " and ";
							finaliza = true;
						} else if (helper.getTipoMedicao().equals(
								MedicaoTipo.POCO.toString())) {
							hqlAux += "hidrometroInstalacaoHistorico.hidrometroLocalInstalacao.id = "
									+ helper.getLocalInstalacaoHidrometro()
									+ " and ";
							finaliza = true;
						}
					}
				}

				// Capacidade Hidrometro
				/*
				 * if (helper.getCapacidadeHidrometro() != null) {kj if
				 * (helper.getTipoMedicao
				 * ().equals(MedicaoTipo.LIGACAO_AGUA.toString())) { hqlAux +=
				 * "hidrometroAgua.hidrometroCapacidade.id = " +
				 * helper.getCapacidadeHidrometro() + " and "; finaliza = true;
				 * }else if
				 * (helper.getTipoMedicao().equals(MedicaoTipo.POCO.toString()))
				 * { hqlAux += "hidrometro.hidrometroCapacidade.id = " +
				 * helper.getCapacidadeHidrometro() + " and "; finaliza = true;
				 * } }
				 */
				if (helper.getCapacidadeHidrometro() != null) {
					if (!helper.getCapacidadeHidrometro().equals("")) {
						String idCapacidades = "";
						for (int i = 0; i < helper.getCapacidadeHidrometro().length; i++) {
							idCapacidades += helper.getCapacidadeHidrometro()[i]
									+ ",";
						}
						idCapacidades = idCapacidades.substring(0,
								(idCapacidades.length() - 1));
						if (helper.getTipoMedicao().equals(
								MedicaoTipo.LIGACAO_AGUA.toString())) {
							hqlAux += "hidrometroAgua.hidrometroCapacidade.id in ("
									+ idCapacidades + ") and ";
							finaliza = true;
						} else if (helper.getTipoMedicao().equals(
								MedicaoTipo.POCO.toString())) {
							hqlAux += "hidrometro.hidrometroCapacidade.id in ("
									+ idCapacidades + ") and ";
							finaliza = true;
						}
					}
				}
				// Marca Hidrometro
				if (helper.getMarcaHidrometro() != null) {
					if (helper.getTipoMedicao().equals(
							MedicaoTipo.LIGACAO_AGUA.toString())) {
						hqlAux += "hidrometroAgua.hidrometroMarca.id = "
								+ helper.getMarcaHidrometro() + " and ";
						finaliza = true;
					} else if (helper.getTipoMedicao().equals(
							MedicaoTipo.POCO.toString())) {
						hqlAux += "hidrometro.hidrometroMarca.id = "
								+ helper.getMarcaHidrometro() + " and ";
						finaliza = true;
					}
				}

				// Anormalidade Hidrometro
				if (helper.getAnormalidadeHidrometro() != null) {
					if (!helper.getAnormalidadeHidrometro().equals("")) {
						String idAnormalidades = "";
						for (int i = 0; i < helper.getAnormalidadeHidrometro().length; i++) {
							idAnormalidades += helper
									.getAnormalidadeHidrometro()[i] + ",";
						}
						idAnormalidades = idAnormalidades.substring(0,
								(idAnormalidades.length() - 1));
						hqlAux += "medicaoHistorico.leituraAnormalidadeFaturamento in ("
								+ idAnormalidades + ") and ";
						finaliza = true;
					}
				}

				// MesAno Instalacao Hidrometro
				// if (helper.getMesAnoInstalacaoHidrometro() != null) {
				/*
				 * if (helper.getMesAnoInstalacaoInicialHidrometro() != null &&
				 * helper.getMesAnoInstalacaoFinalHidrometro() != null){ if
				 * (helper
				 * .getTipoMedicao().equals(MedicaoTipo.LIGACAO_AGUA.toString
				 * ())) { hqlAux +=
				 * "cast((substring(hidrometroInstalacaoHistoricoAgua.dataInstalacao, 1, 4) || "
				 * +
				 * "substring(hidrometroInstalacaoHistoricoAgua.dataInstalacao, 6, 2)) as integer) between "
				 * + helper.getMesAnoInstalacaoInicialHidrometro() + " and " +
				 * helper.getMesAnoInstalacaoFinalHidrometro() + " and ";
				 * finaliza = true; }else if
				 * (helper.getTipoMedicao().equals(MedicaoTipo.POCO.toString()))
				 * { hqlAux +=
				 * "cast((substring(hidrometroInstalacaoHistorico.dataInstalacao, 1, 4) || "
				 * +
				 * "substring(hidrometroInstalacaoHistorico.dataInstalacao, 6, 2)) as integer) between "
				 * + helper.getMesAnoInstalacaoInicialHidrometro() + " and " +
				 * helper.getMesAnoInstalacaoFinalHidrometro() + " and ";
				 * finaliza = true; } }
				 */

			} else if (helper.getTipoOrdem().equals(
					"" + ServicoTipo.TIPO_EFETUAR_REMOCAO_HIDROMETRO)
					|| helper.getTipoOrdem().equals(
							"" + ServicoTipo.TIPO_INSPECAO_ANORMALIDADE)) {
				if (helper.getTipoMedicao() != null) {
					if (helper.getTipoMedicao().equals(
							MedicaoTipo.LIGACAO_AGUA.toString())) {
						// hqlAux += "(imovel.ligacaoAguaSituacao.id = " +
						// LigacaoAguaSituacao.LIGADO + " or ";
						// hqlAux += "imovel.ligacaoAguaSituacao.id = " +
						// LigacaoAguaSituacao.LIGADO_A_REVELIA + ") and ";
						hqlAux += "hidrometroInstalacaoHistoricoAgua.id is not null and ";

						if (dataInstalacaoHidrometroFinal != null
								&& !dataInstalacaoHidrometroFinal.equals("")) {

							if (dataInstalacaoHidrometroInicial != null
									&& !dataInstalacaoHidrometroInicial
									.equals("")) {
								hqlAux += "hidrometroInstalacaoHistoricoAgua.dataInstalacao between '"
										+ dataInstalacaoHidrometroInicial
										+ "' and '"
										+ dataInstalacaoHidrometroFinal
										+ "' and ";
							} else {
								hqlAux += "hidrometroInstalacaoHistoricoAgua.dataInstalacao <= '"
										+ dataInstalacaoHidrometroFinal
										+ "' and ";
							}

						}

						finaliza = true;
					} else if (helper.getTipoMedicao().equals(
							MedicaoTipo.POCO.toString())) {
						// hqlAux += "imovel.ligacaoEsgotoSituacao.id = " +
						// LigacaoEsgotoSituacao.LIGADO + " and ";
						hqlAux += "hidrometroInstalacaoHistorico.id is not null and ";

						if (dataInstalacaoHidrometroFinal != null
								&& !dataInstalacaoHidrometroFinal.equals("")) {

							if (dataInstalacaoHidrometroInicial != null
									&& !dataInstalacaoHidrometroInicial
									.equals("")) {
								hqlAux += "hidrometroInstalacaoHistoricoAgua.dataInstalacao between '"
										+ dataInstalacaoHidrometroInicial
										+ "' and '"
										+ dataInstalacaoHidrometroFinal
										+ "' and ";
							} else {
								hqlAux += "hidrometroInstalacaoHistoricoAgua.dataInstalacao <= '"
										+ dataInstalacaoHidrometroFinal
										+ "' and ";
							}

						}

						finaliza = true;
					}

					// Local Instala��o Hidrometro
					if (helper.getLocalInstalacaoHidrometro() != null) {
						if (helper.getTipoMedicao().equals(
								MedicaoTipo.LIGACAO_AGUA.toString())) {
							hqlAux += "hidrometroInstalacaoHistoricoAgua.hidrometroLocalInstalacao.id = "
									+ helper.getLocalInstalacaoHidrometro()
									+ " and ";
							finaliza = true;
						} else if (helper.getTipoMedicao().equals(
								MedicaoTipo.POCO.toString())) {
							hqlAux += "hidrometroInstalacaoHistorico.hidrometroLocalInstalacao.id = "
									+ helper.getLocalInstalacaoHidrometro()
									+ " and ";
							finaliza = true;
						}
					}
				}

				// Capacidade Hidrometro
				/*
				 * if (helper.getCapacidadeHidrometro() != null) {jkhkj if
				 * (helper
				 * .getTipoMedicao().equals(MedicaoTipo.LIGACAO_AGUA.toString
				 * ())) { hqlAux += "hidrometroAgua.hidrometroCapacidade.id = "
				 * + helper.getCapacidadeHidrometro() + " and "; finaliza =
				 * true; }else if
				 * (helper.getTipoMedicao().equals(MedicaoTipo.POCO.toString()))
				 * { hqlAux += "hidrometro.hidrometroCapacidade.id = " +
				 * helper.getCapacidadeHidrometro() + " and "; finaliza = true;
				 * } }
				 */
				if (helper.getCapacidadeHidrometro() != null) {
					if (!helper.getCapacidadeHidrometro().equals("")) {
						String idCapacidades = "";
						for (int i = 0; i < helper.getCapacidadeHidrometro().length; i++) {
							idCapacidades += helper.getCapacidadeHidrometro()[i]
									+ ",";
						}
						idCapacidades = idCapacidades.substring(0,
								(idCapacidades.length() - 1));
						if (helper.getTipoMedicao().equals(
								MedicaoTipo.LIGACAO_AGUA.toString())) {
							hqlAux += "hidrometroAgua.hidrometroCapacidade.id in ("
									+ idCapacidades + ") and ";
							finaliza = true;
						} else if (helper.getTipoMedicao().equals(
								MedicaoTipo.POCO.toString())) {
							hqlAux += "hidrometro.hidrometroCapacidade.id in ("
									+ idCapacidades + ") and ";
							finaliza = true;
						}
					}
				}
				// Marca Hidrometro
				if (helper.getMarcaHidrometro() != null) {
					if (helper.getTipoMedicao().equals(
							MedicaoTipo.LIGACAO_AGUA.toString())) {
						hqlAux += "hidrometroAgua.hidrometroMarca.id = "
								+ helper.getMarcaHidrometro() + " and ";
						finaliza = true;
					} else if (helper.getTipoMedicao().equals(
							MedicaoTipo.POCO.toString())) {
						hqlAux += "hidrometro.hidrometroMarca.id = "
								+ helper.getMarcaHidrometro() + " and ";
						finaliza = true;
					}
				}

				// Anormalidade Hidrometro
				if (helper.getAnormalidadeHidrometro() != null) {
					if (!helper.getAnormalidadeHidrometro().equals("")) {
						String idAnormalidades = "";
						for (int i = 0; i < helper.getAnormalidadeHidrometro().length; i++) {
							idAnormalidades += helper
									.getAnormalidadeHidrometro()[i] + ",";
						}
						idAnormalidades = idAnormalidades.substring(0,
								(idAnormalidades.length() - 1));
						hqlAux += "medicaoHistorico.leituraAnormalidadeFaturamento in ("
								+ idAnormalidades + ") and ";
						finaliza = true;
					}
				}

			}

			// Cria as condicoes

			// GERENCIA REGIONAL
			if (helper.getGerenciaRegional() != null
					&& !helper.getGerenciaRegional().equals(
							ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				hqlAux += "gerenciaRegional.id = "
						+ helper.getGerenciaRegional() + " and ";
				finaliza = true;
			}

			// UNIDADE NEGOCIO
			if (helper.getUnidadeNegocio() != null
					&& !helper.getUnidadeNegocio().equals(
							ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				hqlAux += "unidadeNegocio.id = " + helper.getUnidadeNegocio()
						+ " and ";
				finaliza = true;
			}

			// Elo
			if (helper.getElo() != null & !helper.getElo().equals("")) {
				hqlAux += "localidade.localidade = " + helper.getElo()
						+ " and ";
				finaliza = true;
			}

			// Logradouro
			if (helper.getLogradouro() != null
					& !helper.getLogradouro().equals("")) {
				hqlAux += "logradouro.id = " + helper.getLogradouro() + " and ";
				finaliza = true;
			}

			// Localidade
			if (helper.getLocalidadeInicial() != null
					&& !helper.getLocalidadeInicial().equals("")
					&& helper.getLocalidadeFinal() != null
					&& !helper.getLocalidadeFinal().equals("")) {
				hqlAux += "(localidade.id between "
						+ helper.getLocalidadeInicial() + " and "
						+ helper.getLocalidadeFinal() + ") and ";
				finaliza = true;
			}
			// Setor Comercial
			if (helper.getSetorComercialInicial() != null
					&& !helper.getSetorComercialInicial().equals("")
					&& helper.getSetorComercialFinal() != null
					&& !helper.getSetorComercialFinal().equals("")) {
				hqlAux += "(setorComercial.id between "
						+ helper.getSetorComercialInicial() + " and "
						+ helper.getSetorComercialFinal() + ") and ";
				finaliza = true;
			}
			// Quadra
			if (helper.getQuadraInicial() != null
					&& !helper.getQuadraInicial().equals("")
					&& helper.getQuadraFinal() != null
					&& !helper.getQuadraFinal().equals("")) {
				hqlAux += "(quadra.numeroQuadra between " + helper.getQuadraInicial()
						+ " and " + helper.getQuadraFinal() + ") and ";
				finaliza = true;
			}
			// Rota
			if (helper.getRotaInicial() != null
					&& !helper.getRotaInicial().equals("")
					&& helper.getRotaFinal() != null
					&& !helper.getRotaFinal().equals("")) {
				hqlAux += "(rota.codigo between " + helper.getRotaInicial()
						+ " and " + helper.getRotaFinal() + ") and ";
				finaliza = true;
			}
			// Perfil Imovel
			if (helper.getPerfilImovel() != null) {
				hqlAux += "imovelPerfil.id = " + helper.getPerfilImovel()
						+ " and ";
				finaliza = true;
			}
			// Categoria
			if (helper.getCategoria() != null) {
				hqlAux += "categoria.id = " + helper.getCategoria() + " and ";
				finaliza = true;
			}
			// Subcategoria
			if (helper.getSubCategoria() != null) {
				hqlAux += "subcategoria.id = " + helper.getSubCategoria()
						+ " and ";
				finaliza = true;
			}
			// Quantidade de Economias
			if (helper.getQuantidadeEconomiasInicial() != null
					&& !helper.getQuantidadeEconomiasInicial().equals("")
					&& helper.getQuantidadeEconomiasFinal() != null
					&& !helper.getQuantidadeEconomiasFinal().equals("")) {
				hqlAux += "(imovel.quantidadeEconomias between "
						+ helper.getQuantidadeEconomiasInicial() + " and "
						+ helper.getQuantidadeEconomiasFinal() + ") and ";
				finaliza = true;
			}
			// Numero Moradores
			if (helper.getNumeroMoradoresInicial() != null
					&& !helper.getNumeroMoradoresInicial().equals("")
					&& helper.getNumeroMoradoresFinal() != null
					&& !helper.getNumeroMoradoresFinal().equals("")) {
				hqlAux += "(imovel.numeroMorador between "
						+ helper.getNumeroMoradoresInicial() + " and "
						+ helper.getNumeroMoradoresFinal() + ") and ";
				finaliza = true;
			}
			// Area Construida
			if (helper.getAreaConstruidaInicial() != null
					&& !helper.getAreaConstruidaInicial().equals("")
					&& helper.getAreaConstruidaFinal() != null
					&& !helper.getAreaConstruidaFinal().equals("")) {
				hqlAux += "(imovel.areaConstruida between "
						+ helper.getAreaConstruidaInicial() + " and "
						+ helper.getAreaConstruidaFinal() + ") and ";
				finaliza = true;
			}
			// Imovel Condominio
			if (helper.getImovelCondominio().equals("1")) {
				hqlAux += "imovel.indicadorImovelCondominio = "
						+ Imovel.IMOVEL_CONDOMINIO + " and ";
				finaliza = true;
			}
			// Media do Imovel
			if (helper.getMediaImovel() != null
					&& !helper.getMediaImovel().equals("")) {
				// hqlAux += "consumosHistorico.consumoMedio <= " + mediaImovel
				// + " and ";
				hqlAux += "consumosHistorico.consumoMedio >= "
						+ helper.getMediaImovel() + " and ";
				finaliza = true;
			}

			// Consumo por Economia
			if (helper.getConsumoPorEconomia() != null
					&& !helper.getConsumoPorEconomia().equals("")
					&& !helper.getConsumoPorEconomia().equals("--")
					&& helper.getConsumoPorEconomiaFinal() != null
					&& !helper.getConsumoPorEconomiaFinal().equals("")
					&& !helper.getConsumoPorEconomiaFinal().equals("--")) {

				hqlAux += "(coalesce(consumosHistorico.numeroConsumoFaturadoMes, 1) / "
						+ "coalesce(imovel.quantidadeEconomias, 1)) >= "
						+ helper.getConsumoPorEconomia() + " and ";

				hqlAux += "(coalesce(consumosHistorico.numeroConsumoFaturadoMes, 1) / "
						+ "coalesce(imovel.quantidadeEconomias, 1)) <= "
						+ helper.getConsumoPorEconomiaFinal() + " and ";

				finaliza = true;
			}

			// Situacao Ligacao Agua
			/*
			 * if (helper.getSituacaoLigacaoAgua() != null &&
			 * !helper.getSituacaoLigacaoAgua().equals("") ) {
			 * 
			 * Integer idSituacaoLigacao = new
			 * Integer(helper.getSituacaoLigacaoAgua());
			 * 
			 * if (idSituacaoLigacao.intValue() !=
			 * ConstantesSistema.NUMERO_NAO_INFORMADO){ hqlAux +=
			 * "imovel.ligacaoAguaSituacao = " + helper.getSituacaoLigacaoAgua()
			 * + " and "; finaliza = true; } }
			 */

			if (helper.getSituacaoLigacaoAgua() != null) {
				if (!helper.getSituacaoLigacaoAgua().equals("")) {
					String idsLigacaoAguaSituacao = "";
					for (int i = 0; i < helper.getSituacaoLigacaoAgua().length; i++) {
						idsLigacaoAguaSituacao += helper
								.getSituacaoLigacaoAgua()[i] + ",";
					}
					idsLigacaoAguaSituacao = idsLigacaoAguaSituacao.substring(
							0, (idsLigacaoAguaSituacao.length() - 1));
					hqlAux += "imovel.ligacaoAguaSituacao in("
							+ idsLigacaoAguaSituacao + ") and ";
					finaliza = true;
				}
			}

			// Finaliza o HQL
			if (finaliza) {
				hql += hqlAux;
				hql = hql.substring(0, hql.length() - 5);
			} else {
				hql = hql.substring(0, hql.length() - 7);
			}
			// CRC4641 - adicionado por Vivianne Sousa - 22/06/2010
			// analista: Ana Cristina
			if (codigoEmpresaFebraban
					.equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)) {
				hql += " order by " + "	localidade.id, "
						+ "	setorComercial.codigo, " + "    rota.codigo, "
						+ "    imovel.numeroSequencialRota ";
			} else {
				// Adiciona o Order By de acordo com a incricao do imovel
				hql += " order by " + "	localidade.id, "
						+ "	setorComercial.codigo, " + "	quadra.numeroQuadra, "
						+ "	imovel.lote, " + "	imovel.subLote";
			}

			retorno = session.createQuery(hql).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Verifica se j� existe Ordem de Servico de Instalacao/Substituicao de
	 * Hidrometro para o Imovel
	 * 
	 * @author Ivan S�rgio
	 * @date 19/11/2007
	 */
	public Integer verificarOrdemServicoInstalacaoSubstituicaoImovel(
			Integer idImovel, Date dataVencimento)
					throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String hql = "select " + "	 distinct os.imovel.id " + "from "
					+ "	OrdemServico os " + "where " + "	(os.servicoTipo.id = "
					+ ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO + " or "
					+ "    os.servicoTipo.id = "
					+ ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO + " or "
					+ "    os.servicoTipo.id = "
					+ ServicoTipo.TIPO_INSTALACAO_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS + " or "
					+ "    os.servicoTipo.id = "
					+ ServicoTipo.TIPO_INSPECAO_ANORMALIDADE
					+ " ) "
					+ "	and os.dataEncerramento is null "
					+ "	and os.registroAtendimento.id is null "
					+ "	and os.cobrancaDocumento.id is null "
					+
					// "	and replace(substring(os.dataEmissao, 1, 10), '-', '') >= :dataVencimento "
					// +
					"	and os.dataEmissao >= :dataVencimento "
					+ "	and os.imovel.id = " + idImovel;

			retorno = (Integer) session.createQuery(hql)
					.setDate("dataVencimento", dataVencimento).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0732] Gerar Relatorio Acompanhamento de Oredem de Servico de
	 * Hidrometro
	 * 
	 * @author Ivan S�rgio
	 * @date 13/12/2007, 27/03/2008
	 * @alteracao: Adicionado Motivo Encerramento; Setor Comercial;
	 * 
	 * @param tipoOrdem
	 * @param situacaoOrdem
	 * @param idLocalidade
	 * @param dataEncerramentoInicial
	 * @param dataEncerramentoFinal
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemServicoGerarRelatorioAcompanhamentoHidrometro(
			String idEmpresa, String tipoOrdem, Short situacaoOrdem,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String dataEncerramentoInicial, String dataEncerramentoFinal,
			String idMotivoEncerramento, String idSetorComercialInicial,
			String idSetorComercialFinal, String codigoQuadraInicial,
			String codigoQuadraFinal, String codigoRotaInicial,
			String codigoRotaFinal, String sequenciaRotaInicial,
			String sequenciaRotaFinal) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		Query query = null;

		try {
			String hql = "select "
					+ "	os.id, "
					+ "	imovel.id, "
					+ "	localidade.id, "
					+ "   localidade.descricao, "
					+ "	setorComercial.codigo, "
					+ "	quadra.numeroQuadra, "
					+ "	imovel.lote, "
					+ "	imovel.subLote, "
					+ "	os.dataGeracao, "
					+ "	os.dataEncerramento, "
					+ "	motivo.descricao, "
					+ "	setorComercial.id, "
					+ "	setorComercial.descricao, "
					+ "	motivo.id "
					+ "from "
					+ "	gcom.atendimentopublico.ordemservico.OrdemServico os "
					+ " 	inner join os.ordemServicoUnidades ordemServicoUnidade "
					+ " 	inner join ordemServicoUnidade.unidadeOrganizacional unidadeOrganizacional "
					+ " 	inner join os.imovel imovel "
					+ " 	inner join imovel.localidade localidade "
					+ " 	inner join imovel.setorComercial setorComercial "
					+ " 	inner join imovel.quadra quadra "
					+ " 	inner join quadra.rota rota "
					+ " 	left  join os.atendimentoMotivoEncerramento motivo "
					+ "where "
					+ "	os.registroAtendimento.id is null "
					+ "	and os.cobrancaDocumento.id is null "
					+ "	and unidadeOrganizacional.empresa.id = "
					+ idEmpresa
					+ " "
					+ "	and os.servicoTipo.constanteFuncionalidadeTipoServico = "
					+ tipoOrdem + " "
					+ " 	and ordemServicoUnidade.atendimentoRelacaoTipo.id = "
					+ AtendimentoRelacaoTipo.ABRIR_REGISTRAR;

			if (situacaoOrdem.equals(OrdemServico.SITUACAO_ENCERRADO)) {
				hql += "	and	os.situacao = " + OrdemServico.SITUACAO_ENCERRADO
						+ " ";

				if (idMotivoEncerramento != null
						&& !idMotivoEncerramento.equals("")
						&& !idMotivoEncerramento.equals(new Integer(
								ConstantesSistema.NUMERO_NAO_INFORMADO)
						.toString())) {
					hql += "	and	motivo.id = " + idMotivoEncerramento + " ";
				}

			} else if (situacaoOrdem.equals(OrdemServico.SITUACAO_PENDENTE)) {
				hql += "	and	(os.situacao = " + OrdemServico.SITUACAO_PENDENTE
						+ " or " + "	os.situacao = "
						+ OrdemServico.SITUACAO_EXECUCAO_EM_ANDAMENTO + ") ";
			}

			// Filtros de Inscri��o Inicial/Final
			// localidade
			if (idLocalidadeInicial != null && !idLocalidadeInicial.equals("")
					&& idLocalidadeFinal != null
					&& !idLocalidadeFinal.equals("")) {
				hql += "	and localidade.id between " + idLocalidadeInicial
						+ " and " + idLocalidadeFinal + " ";

				// setor comercial
				if (idSetorComercialInicial != null
						&& !idSetorComercialInicial.equals("")
						&& idSetorComercialFinal != null
						&& !idSetorComercialFinal.equals("")) {
					hql += "	and setorComercial.id between "
							+ idSetorComercialInicial + " and "
							+ idSetorComercialFinal + " ";

					// quadra
					if (codigoQuadraInicial != null
							&& !codigoQuadraInicial.equals("")
							&& codigoQuadraFinal != null
							&& !codigoQuadraFinal.equals("")) {
						hql += "	and quadra.numeroQuadra between "
								+ codigoQuadraInicial + " and "
								+ codigoQuadraFinal + " ";
					}

					// rota
					if (codigoRotaInicial != null
							&& !codigoRotaInicial.equals("")
							&& codigoRotaFinal != null
							&& !codigoRotaFinal.equals("")) {
						hql += "	and rota.codigo between " + codigoRotaInicial
								+ " and " + codigoRotaFinal + " ";

						// sequencia rota
						if (sequenciaRotaInicial != null
								&& !sequenciaRotaInicial.equals("")
								&& sequenciaRotaFinal != null
								&& !sequenciaRotaFinal.equals("")) {
							hql += "	and rota.numeroSequenciaLeitura between "
									+ sequenciaRotaInicial + " and "
									+ sequenciaRotaFinal + " ";
						}
					}
				}
			}

			if (dataEncerramentoInicial != null
					&& !dataEncerramentoInicial.equals("")
					&& dataEncerramentoFinal != null
					&& !dataEncerramentoFinal.equals("")) {
				hql += "	and os.dataEncerramento between :dataEncerramentoInicial and :dataEncerramentoFinal ";
			}

			hql += "group by" + "	localidade.id, "
					+ "   localidade.descricao, " + "	setorComercial.id, "
					+ "	setorComercial.descricao, " + "	motivo.id, "
					+ "	setorComercial.codigo, " + "	quadra.numeroQuadra, "
					+ "	os.id, " + "	imovel.id, " + "	imovel.lote, "
					+ "	imovel.subLote, " + "	os.dataGeracao, "
					+ " 	os.dataEncerramento, " + " 	motivo.descricao ";

			hql += "order by " + "	setorComercial.id, " + "	motivo.id, "
					+ "	os.id";

			query = session.createQuery(hql);

			if (dataEncerramentoInicial != null
					&& !dataEncerramentoInicial.equals("")
					&& dataEncerramentoFinal != null
					&& !dataEncerramentoFinal.equals("")) {
				query.setTimestamp("dataEncerramentoInicial", Util
						.converteStringParaDateHora(dataEncerramentoInicial));
				query.setTimestamp("dataEncerramentoFinal",
						Util.converteStringParaDateHora(dataEncerramentoFinal));
			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisa os dados necess�rios para exportar a ordem de Servio.
	 * 
	 * [UC0720] Exportar Ordem Servio Prestadoras
	 * 
	 * @author Pedro Alexandre
	 * @date 21/12/2007
	 * 
	 * @param idRegistroAtendimento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosParaExportarOrdemServicoPrestadora(
			Integer idRegistroAtendimento) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String hql = "select " + "	os.id, " + "   os.dataGeracao, "
					+ "   stpr.prazoExecucaoFim, " + "   svtp.id, "
					+ "   imov.id, " + "   bair.nome, " + "   loca.descricao, "
					+ "   elo.id, " + "   stcm.codigo, "
					+ "   qdra.numeroQuadra, " + "   os.observacao, "
					+ "   stpr_atual.id, " + "   svtp.indicadorPavimentoRua, "
					+ "   svtp.indicadorPavimentoCalcada, "
					+ "   ra.pontoReferencia, " + "   svtp.descricao,  "
					+ "   prua.id,  " + "   pcal.id,   "
					+ "   ra.nnCoordenadaNorte, " + "	ra.nnCoordenadaLeste "
					+ "    " + "from " + "	OrdemServico os "
					+ " 	left join os.imovel imov "
					+ "   left join imov.pavimentoRua prua "
					+ "   left join imov.pavimentoCalcada pcal "
					+ " 	inner join os.registroAtendimento ra "
					+ "   inner join os.servicoTipo svtp "
					+ "   inner join svtp.servicoTipoPrioridade stpr "
					+ "   left join ra.logradouroBairro lgbr "
					+ "   inner join lgbr.bairro bair "
					+ "   left join ra.localidade loca "
					+ "   inner join loca.localidade elo "
					+ "   left join ra.setorComercial stcm "
					+ "   left join ra.quadra qdra "
					+ "   inner join os.servicoTipoPrioridadeAtual stpr_atual "
					+ "where " + "	ra.id = :idRegistroAtendimento and "
					+ "   os.dataEncerramento is null and "
					+ "   svtp.indicadorTerceirizado = :indicadorTerceirizado";

			retorno = session.createQuery(hql)
					.setInteger("idRegistroAtendimento", idRegistroAtendimento)
					.setInteger("indicadorTerceirizado", ConstantesSistema.SIM)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0640] Gerar TXT A��o de Cobran�a
	 * 
	 * @author Raphael Rossiter
	 * @date 04/01/2008
	 * 
	 * @param idCobrancaDocumento
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarOrdemServicoPorCobrancaDocumento(
			Integer idCobrancaDocumento) throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "SELECT orse.id " + "FROM OrdemServico orse "
					+ "INNER JOIN orse.cobrancaDocumento cbdo "
					+ "WHERE cbdo.id = :idCobrancaDocumento ";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idCobrancaDocumento", idCobrancaDocumento)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0734] Encerrar Ordem de Servico Vencida
	 * 
	 * @author Ivan S�rgio
	 * @date 14/01/2008
	 * 
	 * @param idServicoTipo
	 * @param dataVencimento
	 * @return
	 * @throws ErroRepositorioException
	 */

	/**
	 * Incluso da Origem da OS na busca para encerrar as mesmas quando vencidas.
	 * @author Wellington Rocha
	 * @date 18/01/2012*/
	public List pesquisarOrdemServicoVencida(Integer idServicoTipo, String dataVencimento)
			throws ErroRepositorioException {

		List retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String hql = 
					"select " +
							"	os.id, " +
							"	un.unidadeOrganizacional.id " +
							"from " +
							"	gcom.atendimentopublico.ordemservico.OrdemServico os " +
							"	inner join os.ordemServicoUnidades un " +
							"where " +
							"	os.situacao = :situacaoOrdem " +
							"	and os.servicoTipo.id = :idServicoTipo " +
							"	and substring(os.dataGeracao, 1, 10) <= :dataVencimento ";

			/**
			 * Incluso da Origem da OS na busca para encerrar as mesmas quando vencidas.
			 * @author Wellington Rocha
			 * @date 18/01/2012*/
			/*Alteraes em testes.
				if (codigoOrigemOS.equals(OrdemServico.SELETIVAS_COBRANCA)){
					hql = hql + " and os.cobrancaDocumento.id is not null ";

				}else if (codigoOrigemOS.equals(OrdemServico.SELETIVAS_HIDROMETRO)){
					hql = hql + "	and os.cobrancaDocumento.id is null " +
					            "	and os.registroAtendimento.id is null ";

				}else if (codigoOrigemOS.equals(OrdemServico.SOLICITADAS)){
					hql = hql + "	and os.registroAtendimento.id is not null ";
				}*/

			hql = hql + " order by " +
					"	os.id";


			retorno = session.createQuery(hql)
					.setShort("situacaoOrdem", OrdemServico.SITUACAO_PENDENTE)
					.setInteger("idServicoTipo", idServicoTipo)
					.setString("dataVencimento", dataVencimento).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0734] Encerrar Ordem de Servico Vencida
	 * 
	 * @author Ivan S�rgio
	 * @date 16/01/2008
	 * 
	 * @param ordemServico
	 * @throws ErroRepositorioException
	 */
	public void alterarOrdemServicoVencida(OrdemServico ordemServico)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			String sql = "update "
					+ "	gcom.atendimentopublico.ordemservico.OrdemServico "
					+ "set " + "	amen_id = :idMotivoEncerramento, "
					+ "	orse_cdsituacao = :codigoSituacao, "
					+ "	orse_tmencerramento = :dataEncerramento, "
					+ "	orse_dsparecerencerramento = :parecerEncerramento, "
					+ "	orse_tmultimaalteracao = :dataUltimaAlteracao "
					+ "where " + "	orse_id = :idOrdemServico";

			session.createQuery(sql)
			.setInteger(
					"idMotivoEncerramento",
					ordemServico.getAtendimentoMotivoEncerramento()
					.getId())
					.setShort("codigoSituacao", ordemServico.getSituacao())
					.setTimestamp("dataEncerramento",
							ordemServico.getDataEncerramento())
							.setString("parecerEncerramento",
									ordemServico.getDescricaoParecerEncerramento())
									.setTimestamp("dataUltimaAlteracao",
											ordemServico.getUltimaAlteracao())
											.setInteger("idOrdemServico", ordemServico.getId())
											.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Pesquisa o movimento da ordem de Servio
	 * 
	 * [UC0720] Exportar Ordem de Servio Movimento
	 * 
	 * @author Pedro Alexandre
	 * @date 18/01/2008
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public OrdemServicoMovimento pesquisarOrdemServicoMovimento(
			Integer idOrdemServico) throws ErroRepositorioException {
		OrdemServicoMovimento retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select osmv " + "from OrdemServicoMovimento osmv "
					+ "where osmv.id = :idOrdemServico ";

			retorno = (OrdemServicoMovimento) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0753] Manter Ordem de Servico Concluida
	 * 
	 * @author Ivan S�rgio
	 * @date 26/03/2008
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemServicoConcluida(Integer idOrdemServico)
			throws ErroRepositorioException {
		List retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String hql = "select "
					+ "	boletim.id, "
					+ "	ordemServico.dataEmissao, "
					+ "	ordemServico.dataEncerramento, "
					+ "	ordemServico.imovel.id, "
					+ "	boletim.codigoFiscalizacao, "
					+ "	boletim.dataFiscalizacao, "
					+ "	dataFiscalizacao.dataFiscalizacaoOrdemServico2, "
					+ "	dataFiscalizacao.dataFiscalizacaoOrdemServico3, "
					+ "	boletim.usuario.id, "
					+ "	boletim.indicadorTrocaProtecaoHidrometro, "
					+ "	boletim.indicadorTrocaRegistroHidrometro, "
					+ "	localInstalacao.descricao, "
					+ "	boletim.dataEncerramentoBoletim, "
					+ "	boletim.ultimaAlteracao "
					+ "from "
					+ "	gcom.atendimentopublico.ordemservico.BoletimOsConcluida boletim "
					+ "	inner join boletim.hidrometroLocalInstalacao localInstalacao "
					+ "	left join boletim.ordemServico ordemServico "
					+ "	left join boletim.dataFiscalizacaoOsSeletivas dataFiscalizacao "
					+ "where " + "	boletim.id = :idOrdemServico";

			retorno = session.createQuery(hql)
					.setInteger("idOrdemServico", idOrdemServico).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0732] Gerar Relatorio Acompanhamento de Oredem de Servico de
	 * Hidrometro RELATORIO: Relacao das Ordens de Servico Concluidas
	 * 
	 * @author Ivan S�rgio
	 * @date 04/04/2008
	 * 
	 * @param tipoOrdem
	 * @param situacaoOrdem
	 * @param idLocalidade
	 * @param dataEncerramentoInicial
	 * @param dataEncerramentoFinal
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemServicoConcluidaGerarRelatorioAcompanhamentoHidrometro(
			String idEmpresa, String tipoOrdem, Short situacaoOrdem,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String dataEncerramentoInicial, String dataEncerramentoFinal,
			String idMotivoEncerramento, String idSetorComercialInicial,
			String idSetorComercialFinal, String codigoQuadraInicial,
			String codigoQuadraFinal, String codigoRotaInicial,
			String codigoRotaFinal, String sequenciaRotaInicial,
			String sequenciaRotaFinal) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		Query query = null;

		try {
			String hql = "select "
					+ "	os.id, "
					+ "	imovel.id, "
					+ "	localidade.id, "
					+ "	setorComercial.codigo, "
					+ "	quadra.numeroQuadra, "
					+ "	imovel.lote, "
					+ "	imovel.subLote, "
					+ "	os.dataGeracao, "
					+ "	os.dataEncerramento, "
					+ "	motivo.descricao, "
					+ "	setorComercial.id, "
					+ "	motivo.id, "
					+ "	localInstalacao.id, "
					+ "	localInstalacao.descricao, "
					+ "   localidade.descricao "
					+ "from "
					+ "	gcom.atendimentopublico.ordemservico.OrdemServico os "
					+ " 	inner join os.ordemServicoUnidades ordemServicoUnidade "
					+ " 	inner join ordemServicoUnidade.unidadeOrganizacional unidadeOrganizacional "
					+ " 	inner join os.imovel imovel "
					+ " 	inner join imovel.localidade localidade "
					+ " 	inner join imovel.setorComercial setorComercial "
					+ " 	inner join imovel.quadra quadra "
					+ "	left join imovel.ligacaoAgua ligacaoAgua "
					+ "	left join ligacaoAgua.hidrometroInstalacaoHistorico hidInstalacaoHistorico "
					+ "	left join hidInstalacaoHistorico.hidrometroLocalInstalacao localInstalacao "
					+ " 	left  join os.atendimentoMotivoEncerramento motivo "
					+ "	left join os.boletimOsConcluida bo "
					+ " 	left join bo.hidrometroLocalInstalacao localInstalacaoBoletim "
					+ "where " + "	os.registroAtendimento.id is null "
					+ "	and os.cobrancaDocumento.id is null "
					+ "	and unidadeOrganizacional.empresa.id = " + idEmpresa
					+ " " + "	and os.servicoTipo.id = " + tipoOrdem + " "
					+ " 	and ordemServicoUnidade.atendimentoRelacaoTipo.id = "
					+ AtendimentoRelacaoTipo.ABRIR_REGISTRAR;

			hql += "	and	os.situacao = " + OrdemServico.SITUACAO_ENCERRADO
					+ " " + "	and	motivo.id = " + idMotivoEncerramento + " ";

			// Filtros de Inscri��o Inicial/Final
			// localidade
			if (idLocalidadeInicial != null && !idLocalidadeInicial.equals("")
					&& idLocalidadeFinal != null
					&& !idLocalidadeFinal.equals("")) {
				hql += "	and localidade.id between " + idLocalidadeInicial
						+ " and " + idLocalidadeFinal + " ";

				// setor comercial
				if (idSetorComercialInicial != null
						&& !idSetorComercialInicial.equals("")
						&& idSetorComercialFinal != null
						&& !idSetorComercialFinal.equals("")) {
					hql += "	and setorComercial.id between "
							+ idSetorComercialInicial + " and "
							+ idSetorComercialFinal + " ";

					// quadra
					if (codigoQuadraInicial != null
							&& !codigoQuadraInicial.equals("")
							&& codigoQuadraFinal != null
							&& !codigoQuadraFinal.equals("")) {
						hql += "	and quadra.numeroQuadra between "
								+ codigoQuadraInicial + " and "
								+ codigoQuadraFinal + " ";
					}
					// rota
					if (codigoRotaInicial != null
							&& !codigoRotaInicial.equals("")
							&& codigoRotaFinal != null
							&& !codigoRotaFinal.equals("")) {
						hql += "	and rota.codigo between " + codigoRotaInicial
								+ " and " + codigoRotaFinal + " ";

						// sequencia rota
						if (sequenciaRotaInicial != null
								&& !sequenciaRotaInicial.equals("")
								&& sequenciaRotaFinal != null
								&& !sequenciaRotaFinal.equals("")) {
							hql += "	and rota.numeroSequenciaLeitura between "
									+ sequenciaRotaInicial + " and "
									+ sequenciaRotaFinal + " ";
						}
					}
				}
			}

			if (dataEncerramentoInicial != null
					&& !dataEncerramentoInicial.equals("")
					&& dataEncerramentoFinal != null
					&& !dataEncerramentoFinal.equals("")) {
				hql += "	and os.dataEncerramento between :dataEncerramentoInicial and :dataEncerramentoFinal ";
			}

			hql += "group by" + "	localInstalacao.id, "
					+ "	localInstalacaoBoletim.id, "
					+ "	localInstalacao.descricao, "
					+ "	localInstalacaoBoletim.descricao, "
					+ "	setorComercial.id, " + "	motivo.id, "
					+ "	setorComercial.codigo, " + "	quadra.numeroQuadra, "
					+ "	localidade.id, " + "   localidade.descricao, "
					+ "	os.id, " + "	imovel.id, " + "	imovel.lote, "
					+ "	imovel.subLote, " + "	os.dataGeracao, "
					+ " 	os.dataEncerramento, " + " 	motivo.descricao ";

			hql += "order by " + "	setorComercial.id, "
					+ "	localInstalacao.id, " + "	localInstalacaoBoletim.id, "
					+ "	motivo.id, " + "	os.id";

			query = session.createQuery(hql);

			if (dataEncerramentoInicial != null
					&& !dataEncerramentoInicial.equals("")
					&& dataEncerramentoFinal != null
					&& !dataEncerramentoFinal.equals("")) {
				query.setTimestamp("dataEncerramentoInicial", Util
						.converteStringParaDateHora(dataEncerramentoInicial));
				query.setTimestamp("dataEncerramentoFinal",
						Util.converteStringParaDateHora(dataEncerramentoFinal));
			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0720] Exportar Ordem de Servio Prestadoras
	 * 
	 * @author Vivianne Sousa
	 * @date 02/04/2008
	 * 
	 * @throws ControladorException
	 */
	public void atualizarIndicadorEncerramentoAutomaticoOS(
			Short indicadorEncerramentoAutomatico, Integer idOrdemServico)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			String atualizarOS = "";

			atualizarOS = "update gcom.atendimentopublico.ordemservico.OrdemServico "
					+ "set orse_tmultimaalteracao = :ultimaAlteracao, "
					+ "orse_icencerramentoautomatico = :indicadorEncerramentoAutomatico ";

			atualizarOS = atualizarOS + " where orse_id = :idOrdemServico";

			session.createQuery(atualizarOS)
			.setShort("indicadorEncerramentoAutomatico",
					indicadorEncerramentoAutomatico)
					.setTimestamp("ultimaAlteracao", new Date())
					.setInteger("idOrdemServico", idOrdemServico)
					.executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0753] Manter Ordem Servico Concluida
	 * 
	 * @author Ivan S�rgio
	 * @date 09/04/2008
	 * 
	 * @param idOrdemServico
	 * @param situacaoFiscalizacao
	 * @param dataFiscalizacao1
	 * @param idFuncionario
	 * @throws ErroRepositorioException
	 */
	public void atualizarBoletimOSConcluida(Integer idOrdemServico,
			Short situacaoFiscalizacao, Date dataFiscalizacao1,
			Integer idFuncionario) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			String sql = "UPDATE "
					+ "	gcom.atendimentopublico.ordemservico.BoletimOsConcluida "
					+ "SET " + "	bosc_cdfiscalizacao = " + situacaoFiscalizacao
					+ ", ";

			if (dataFiscalizacao1 != null) {
				sql += "	bosc_dtfiscalizacao = :dataFiscalizacao1 , ";
			} else {
				sql += "	bosc_dtfiscalizacao = null, ";
			}

			if (idFuncionario != null) {
				sql += "	usur_id = " + idFuncionario + ", ";
			} else {
				sql += "	usur_id = null, ";
			}

			sql += "	bosc_tmultimaalteracao = " + Util.obterSQLDataAtual()
					+ " WHERE " + "	orse_id = " + idOrdemServico;

			session.createQuery(sql)
			.setDate("dataFiscalizacao1", dataFiscalizacao1)
			.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0753] Manter Ordem Servico Concluida
	 * 
	 * @author Ivan S�rgio
	 * @date 09/04/2008
	 * 
	 * @param idOrdemServico
	 * @throws ErroRepositorioException
	 */
	public void excluirDatasFiscalizacaoBoletimOSConcluida(
			Integer idOrdemServico) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			String sql = "DELETE FROM gcom.atendimentopublico.ordemservico.DataFiscalizacaoOsSeletiva "
					+ "WHERE orse_id = :idOrdemServico";

			session.createQuery(sql)
			.setInteger("idOrdemServico", idOrdemServico)
			.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/***
	 * [UC0765] Gerar Boletim Ordens de Servico Concluidas
	 * 
	 * @author Ivan S�rgio
	 * @date 29/04/2008
	 * 
	 * @param idEmpresa
	 * @param idLocalidade
	 * @param anoMesReferenciaBoletim
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarResumoOrdensServicoConcluidas(Integer idEmpresa,
			Integer idLocalidade, String anoMesReferenciaBoletim)
					throws ErroRepositorioException {

		Collection colecaoDados = null;
		Session session = HibernateUtil.getSession();

		try {
			String hql = "select "
					+ "	bo.anoMesReferenciaBoletim, "
					+ "	os.id, "
					+ "	bo.codigoFiscalizacao, "
					+ " 	bo.indicadorTrocaProtecaoHidrometro, "
					+ "	bo.indicadorTrocaRegistroHidrometro, "
					+ "	li.id, "
					+ "	os.servicoTipo.id, "
					+ "	bo.dataFiscalizacao, "
					+ "	dt.dataFiscalizacaoOrdemServico2, "
					+ "	dt.dataFiscalizacaoOrdemServico3 "
					+ "from "
					+ "	gcom.atendimentopublico.ordemservico.BoletimOsConcluida bo "
					+ "	inner join bo.ordemServico os "
					+ "	left join bo.dataFiscalizacaoOsSeletivas dt "
					+ "	inner join os.imovel im "
					+ "	left join bo.hidrometroLocalInstalacao li "
					+ "	inner join os.ordemServicoUnidades os_uni "
					+ "	inner join os_uni.unidadeOrganizacional uni_org "
					+ "where "
					+ "	uni_org.empresa.id = :idEmpresa "
					+ "	and im.localidade.id = :idLocalidade "
					+ "	and bo.anoMesReferenciaBoletim <= :anoMesReferenciaBoletim "
					+ "	and bo.dataEncerramentoBoletim is null";

			colecaoDados = session
					.createQuery(hql)
					.setInteger("idEmpresa", idEmpresa)
					.setInteger("idLocalidade", idLocalidade)
					.setString("anoMesReferenciaBoletim",
							anoMesReferenciaBoletim).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return colecaoDados;
	}

	/**
	 * [UC0765] Gerar Boletim Ordens de Servico Concluidas
	 * 
	 * @author Ivan S�rgio
	 * @date 02/05/2008
	 * 
	 * @param idOrdemServico
	 * @throws ErroRepositorioException
	 */
	public void encerrarBoletimOrdemServicoConcluida(Integer idOrdemServico)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			String hql = "UPDATE "
					+ "	gcom.atendimentopublico.ordemservico.BoletimOsConcluida "
					+ "SET " + "	dataEncerramentoBoletim = :dataEncerramento, "
					+ "	ultimaAlteracao = :ultimaAlteracao " + "WHERE "
					+ "	id = :idOrdemServico "
					+ "	and codigoFiscalizacao IN (0, 1) ";

			session.createQuery(hql)
			.setTimestamp("dataEncerramento", new Date())
			.setTimestamp("ultimaAlteracao", new Date())
			.setInteger("idOrdemServico", idOrdemServico)
			.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Pesquisa Ordens em Processo de Repavimentao
	 * 
	 * [UC0639] Filtrar Ordens em Processo de Repavimeta��o.
	 * 
	 * @author Yara Taciane, Hugo Leonardo
	 * @date 02/06/2008, 13/12/2010
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemProcessoRepavimentacao(
			OSPavimentoHelper oSPavimentoHelper, Integer numeroPagina)
					throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();
		try {
			consulta = "select ospv "
					+ "from OrdemServicoPavimento ospv "
					+ "left join fetch ospv.pavimentoRua prua "
					+ "left join fetch ospv.pavimentoRuaRetorno pruaret "
					+ "left join fetch ospv.ordemServico os "
					+ "left join fetch os.registroAtendimento rgat "
					+ "left join fetch rgat.localidade loca "
					+ "left join fetch os.imovel imov  "
					+

					"left join rgat.logradouroBairro logradouroBairroRA "
					+ "left join logradouroBairroRA.logradouro logradouroRA "
					+ "left join logradouroBairroRA.bairro bairroRa "
					+

					"left join imov.logradouroBairro logradouroBairroImovel "
					+ "left join logradouroBairroImovel.logradouro logradouroImovel "
					+ "left join logradouroBairroImovel.bairro bairroImovel " +

					"left join fetch ospv.motivoRejeicao mrej "
					+ "left join fetch ospv.usuarioRejeicao usurej "
					+
					// "left join os.unidadeAtual unid  " +
					"where 1=1 " + "and ospv.unidadeRepavimentadora = "
					+ oSPavimentoHelper.getIdUnidadeResponsavel();

			// Pendente -1-
			if (oSPavimentoHelper.getSituacaoRetorno().equals(1)) {
				consulta = consulta
						+ "  and ospv.pavimentoRuaRetorno is null and ospv.areaPavimentoRuaRetorno is null"
						+ " and ospv.pavimentoCalcadaRetorno is null and ospv.areaPavimentoCalcadaRetorno is null "
						+ " and ospv.motivoRejeicao is null ";
			}

			// Conclu�da -2-
			if (oSPavimentoHelper.getSituacaoRetorno().equals(2)) {
				consulta = consulta
						+ " and ( (ospv.pavimentoRuaRetorno is not null and ospv.areaPavimentoRuaRetorno is not null) "
						+ " or (ospv.pavimentoCalcadaRetorno is not null and ospv.areaPavimentoCalcadaRetorno is not null) ) "
						+ " and ospv.motivoRejeicao is null ";
			}

			// Rejeitadas -4-
			if (oSPavimentoHelper.getSituacaoRetorno().equals(4)) {
				consulta = consulta
						+ " and ( ospv.motivoRejeicao is not null ) ";
			}

			// Numero da OS
			if (oSPavimentoHelper.getNumeroOS() != null) {
				consulta = consulta + " and ( os.id = :numeroOS ) ";

				parameters.put("numeroOS", oSPavimentoHelper.getNumeroOS());
			}

			// Com observacao de retorno
			if (oSPavimentoHelper.getIndicadorOsObservacaoRetorno() != null
					&& oSPavimentoHelper.getIndicadorOsObservacaoRetorno()
					.shortValue() == ConstantesSistema.SIM.shortValue()) {

				consulta = consulta
						+ " and ospv.observacao is not null and cast(ospv.observacao as string) is not null ";
			}

			// data de refer�ncia
			if (oSPavimentoHelper.getPeriodoEncerramentoOsInicial() != null
					&& oSPavimentoHelper.getPeriodoEncerramentoOsFinal() != null) {
				consulta = consulta
						+ " and ospv.ordemServico.dataEncerramento between :dataInicial and :dataFinal ";

				parameters.put("dataInicial", Util.formatarDataInicial(Util
						.converteStringParaDate(oSPavimentoHelper
								.getPeriodoEncerramentoOsInicial())));
				parameters.put("dataFinal", Util.formatarDataFinal(Util
						.converteStringParaDate(oSPavimentoHelper
								.getPeriodoEncerramentoOsFinal())));

			} else if (oSPavimentoHelper.getPeriodoRetornoServicoInicial() != null
					&& oSPavimentoHelper.getPeriodoRetornoServicoFinal() != null) {
				consulta = consulta
						+ " and ospv.dataExecucao between :dataInicialRetornoServico and :dataFinalRetornoServico ";

				parameters.put("dataInicialRetornoServico", Util
						.formatarDataInicial(Util
								.converteStringParaDate(oSPavimentoHelper
										.getPeriodoRetornoServicoInicial())));
				parameters.put("dataFinalRetornoServico", Util
						.formatarDataFinal(Util
								.converteStringParaDate(oSPavimentoHelper
										.getPeriodoRetornoServicoFinal())));

			} else if (oSPavimentoHelper.getPeriodoRejeicaoInicial() != null
					&& oSPavimentoHelper.getPeriodoRejeicaoFinal() != null) {
				consulta = consulta
						+ " and ospv.dataRejeicao between :dataRejeicaoInicial and :dataRejeicaoFinal ";

				parameters.put("dataRejeicaoInicial", Util
						.formatarDataInicial(Util
								.converteStringParaDate(oSPavimentoHelper
										.getPeriodoRejeicaoInicial())));
				parameters.put("dataRejeicaoFinal", Util.formatarDataFinal(Util
						.converteStringParaDate(oSPavimentoHelper
								.getPeriodoRejeicaoFinal())));
			}

			if (oSPavimentoHelper.getIdMunicipio() != null) {
				consulta = consulta
						+ " and (bairroRa.municipio.id = :idMunicipio or bairroImovel.municipio.id = :idMunicipio) ";
				parameters.put("idMunicipio",
						oSPavimentoHelper.getIdMunicipio());
			}

			if (oSPavimentoHelper.getIdBairro() != null) {
				consulta = consulta
						+ " and (bairroRa.id = :idBairro or bairroImovel.id = :idBairro) ";
				parameters.put("idBairro", oSPavimentoHelper.getIdBairro());
			}

			if (oSPavimentoHelper.getIdLogradouro() != null) {
				consulta = consulta
						+ " and (logradouroRA.id = :idLogradouro or logradouroImovel.id = :idLogradouro) ";
				parameters.put("idLogradouro",
						oSPavimentoHelper.getIdLogradouro());
			}

			consulta = consulta
					+ " and os.situacao = 2 "
					+ " and os.servicoTipo.indicadorPavimentoRua = 1 "
					+ " order by logradouroRA.nome,logradouroImovel.nome,mrej.id, os.id, pruaret.id";

			query = session.createQuery(consulta);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			if (numeroPagina != null) {
				retorno = query.setFirstResult(10 * numeroPagina)
						.setMaxResults(10).list();
			} else {
				// Caso relatorio.
				retorno = query.list();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisa Ordens em Processo de Repavimentao
	 * 
	 * [UC0639] Filtrar Ordens em Processo de Repavimeta��o Calcada.
	 * 
	 * @author Yara Taciane
	 * @date 02/06/2008
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemProcessoRepavimentacaoPorTipoPavimentoCalcada(
			OSPavimentoHelper oSPavimentoHelper)
					throws ErroRepositorioException {
		List retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "select "
					+ "ospv.pcal_id  as idPavimentoCalcada , "
					+ // 0 id do pavimento tipo
					"count(ospv.pcal_id) as quantidade , "
					+ // 1 - quantidade do pavimento calcada
					"pcal.pcal_dspavimentocalcada as descricao,  "
					+ // 2 - descri��o do pavimento rua
					"sum(ospv.ospv_nnareapvtocal) as somatorioArea "
					+ // 3 - somat�rio da metragem do pavimento de rua.
					" from atendimentopublico.ordem_servico_pavimento ospv "
					+ "left join  atendimentopublico.ordem_servico orse on  orse.orse_id = ospv.orse_id "
					+ "left join  atendimentopublico.servico_tipo svtp on  svtp.svtp_id = orse.svtp_id "
					+ "left join  cadastro.pavimento_calcada pcal on  pcal.pcal_id = ospv.ospv_id "
					+ "where 1=1 ";

			// Pendente -1-
			if (oSPavimentoHelper.getSituacaoRetorno().equals(1)) {
				consulta = consulta
						+ "  and ospv.pcal_idret is null and ospv.ospv_nnareapvtocalret is null ";
			}

			// Conclu�da -2-
			if (oSPavimentoHelper.getSituacaoRetorno().equals(2)) {
				consulta = consulta
						+ " and  ospv.pcal_idret is not null and ospv.ospv_nnareapvtocalret is not null ";
			}

			// data de refer�ncia
			if (oSPavimentoHelper.getPeriodoEncerramentoOsInicial() != null
					&& oSPavimentoHelper.getPeriodoEncerramentoOsFinal() != null) {
				consulta = consulta + " and orse.orse_tmencerramento between '"
						+ oSPavimentoHelper.getPeriodoEncerramentoOsInicial()
						+ "' and  " + "'"
						+ oSPavimentoHelper.getPeriodoEncerramentoOsFinal()
						+ " '";
			} else if (oSPavimentoHelper.getPeriodoRetornoServicoInicial() != null
					&& oSPavimentoHelper.getPeriodoRetornoServicoFinal() != null) {
				consulta = consulta + " and ospv.ospv_tmgeracao between '"
						+ oSPavimentoHelper.getPeriodoRetornoServicoInicial()
						+ "' and  " + "'"
						+ oSPavimentoHelper.getPeriodoRetornoServicoFinal()
						+ " '";
			}

			consulta = consulta
					+ " and orse.orse_cdsituacao = 2 "
					+ " and svtp.svtp_icpvtocal = 1 "
					+ " group by ospv.ospv_id,ospv.pcal_id, pcal.pcal_dspavimentocalcada  ";

			Collection coll = (Collection) session.createSQLQuery(consulta)
					.addScalar("idPavimentoCalcada", Hibernate.INTEGER)
					.addScalar("quantidade", Hibernate.INTEGER)
					.addScalar("descricao", Hibernate.STRING)
					.addScalar("somatorioArea", Hibernate.BIG_DECIMAL).list();
			if (coll != null) {
				Iterator it = coll.iterator();
				while (it.hasNext()) {
					Object[] obj = (Object[]) it.next();

					OSTipoPavimentoHelper osTipoPavimento = new OSTipoPavimentoHelper();
					osTipoPavimento.setId((Integer) obj[0]);
					osTipoPavimento.setQuantidade((Integer) obj[1]);
					osTipoPavimento.setDescricao((String) obj[2]);
					osTipoPavimento.setSomatorioArea((BigDecimal) obj[3]);

					retorno.add(osTipoPavimento);
				}
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisa Ordens em Processo de Repavimentao
	 * 
	 * [UC0639] Filtrar Ordens em Processo de Repavimeta��o Calcada.
	 * 
	 * @author Yara Taciane
	 * @date 02/06/2008
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemProcessoRepavimentacaoPorTipoPavimentoRua(
			OSPavimentoHelper oSPavimentoHelper)
					throws ErroRepositorioException {
		List retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select "
					+ "ospv.prua_id  as idPavimentoRua , "
					+ // 0 id do pavimento tipo
					// "count(ospv.prua_id) as quantidade , " + // 1 -
					// quantidade do pavimento calcada
					"prua.prua_dspavimentorua as descricao,  "
					+ // 2 - descri��o do pavimento rua
					"sum(ospv.ospv_nnareapvtorua) as somatorioArea "
					+ // 3 - somat�rio da metragem do pavimento de rua.
					" from atendimentopublico.ordem_servico_pavimento ospv "
					+ "left join  atendimentopublico.ordem_servico orse on  orse.orse_id = ospv.orse_id "
					+ "left join  atendimentopublico.servico_tipo svtp on  svtp.svtp_id = orse.svtp_id "
					+ "left join  cadastro.pavimento_rua prua on  prua.prua_id = ospv.prua_id "
					+

					"left join cadastro.imovel imov on imov.imov_id = orse.imov_id "
					+ "left join atendimentopublico.registro_atendimento rgat on rgat.rgat_id = orse.rgat_id "
					+

					"left join cadastro.logradouro_bairro logradouroBairroRA on logradouroBairroRA.lgbr_id = rgat.lgbr_id "
					+ "left join cadastro.logradouro logradouroRA on logradouroRA.logr_id = logradouroBairroRA.logr_id "
					+ "left join cadastro.bairro bairroRa on bairroRa.bair_id = logradouroBairroRA.bair_id "
					+

					"left join cadastro.logradouro_bairro logradouroBairroImovel on logradouroBairroImovel.lgbr_id = imov.lgbr_id "
					+ "left join cadastro.logradouro logradouroImovel on logradouroImovel.logr_id = logradouroBairroImovel.logr_id "
					+ "left join cadastro.bairro bairroImovel on bairroImovel.bair_id = logradouroBairroImovel.bair_id "
					+

					"left join  atendimentopublico.repavimento_mot_rejeicao mrej on mrej.rpmr_id = ospv.rpmr_id "
					+
					// "left join  cadastro.unidade_organizacional unid on unid.unid_id = orse.unid_idatual "
					// +
					"where 1=1 " + "and ospv.unid_idrepavimentadora = "
					+ oSPavimentoHelper.getIdUnidadeResponsavel();

			// Pendente -1-
			if (oSPavimentoHelper.getSituacaoRetorno().equals(1)) {
				consulta = consulta
						+ "  and ospv.prua_idret is null and ospv.ospv_nnareapvtoruaret is null "
						+ " and ospv.pcal_idret is null and ospv.ospv_nnareapvtocalret is null "
						+ " and ospv.rpmr_id is null";
			}

			// Conclu�da -2-
			if (oSPavimentoHelper.getSituacaoRetorno().equals(2)) {
				consulta = consulta
						+ " and ( ((ospv.prua_idret is not null and ospv.ospv_nnareapvtoruaret is not null) "
						+ " or (ospv.pcal_idret is not null and ospv.ospv_nnareapvtocalret is not null)) "
						+ " and ospv.rpmr_id is null )";
			}

			// Rejeitadas -4-
			if (oSPavimentoHelper.getSituacaoRetorno().equals(4)) {
				consulta = consulta + " and ( ospv.rpmr_id is not null ) ";
			}

			// Numero da OS
			if (oSPavimentoHelper.getNumeroOS() != null) {
				consulta = consulta + " and ( orse.orse_id = :numeroOS ) ";

				parameters.put("numeroOS", oSPavimentoHelper.getNumeroOS());
			}

			// Com observacao de retorno
			if (oSPavimentoHelper.getIndicadorOsObservacaoRetorno() != null
					&& oSPavimentoHelper.getIndicadorOsObservacaoRetorno()
					.shortValue() == ConstantesSistema.SIM.shortValue()) {

				consulta = consulta
						+ " and ospv.ospv_dsobservacao is not null and cast(ospv.ospv_dsobservacao as VARCHAR2(255 CHAR)) is not null ";
			}

			// data de refer�ncia
			if (oSPavimentoHelper.getPeriodoEncerramentoOsInicial() != null
					&& oSPavimentoHelper.getPeriodoEncerramentoOsFinal() != null) {
				consulta = consulta
						+ " and orse.orse_tmencerramento between :dataInicial and :dataFinal ";

				parameters.put("dataInicial", Util.formatarDataInicial(Util
						.converteStringParaDate(oSPavimentoHelper
								.getPeriodoEncerramentoOsInicial())));
				parameters.put("dataFinal", Util.formatarDataFinal(Util
						.converteStringParaDate(oSPavimentoHelper
								.getPeriodoEncerramentoOsFinal())));

			} else if (oSPavimentoHelper.getPeriodoRetornoServicoInicial() != null
					&& oSPavimentoHelper.getPeriodoRetornoServicoFinal() != null) {
				consulta = consulta
						+ " and ospv.ospv_tmrepavretorno between :dataInicialRetornoServico and :dataFinalRetornoServico ";

				parameters.put("dataInicialRetornoServico", Util
						.formatarDataInicial(Util
								.converteStringParaDate(oSPavimentoHelper
										.getPeriodoRetornoServicoInicial())));
				parameters.put("dataFinalRetornoServico", Util
						.formatarDataFinal(Util
								.converteStringParaDate(oSPavimentoHelper
										.getPeriodoRetornoServicoFinal())));
			}

			if (oSPavimentoHelper.getIdMunicipio() != null) {
				consulta = consulta
						+ " and (bairroRa.muni_id = :idMunicipio or bairroImovel.muni_id = :idMunicipio) ";
				parameters.put("idMunicipio",
						oSPavimentoHelper.getIdMunicipio());
			}

			if (oSPavimentoHelper.getIdBairro() != null) {
				consulta = consulta
						+ " and (bairroRa.bair_id = :idBairro or bairroImovel.bair_id = :idBairro) ";
				parameters.put("idBairro", oSPavimentoHelper.getIdBairro());
			}

			if (oSPavimentoHelper.getIdLogradouro() != null) {
				consulta = consulta
						+ " and (logradouroRA.logr_id = :idLogradouro or logradouroImovel.logr_id = :idLogradouro) ";
				parameters.put("idLogradouro",
						oSPavimentoHelper.getIdLogradouro());
			}

			consulta = consulta + " and orse.orse_cdsituacao = 2 "
					+ " and svtp.svtp_icpvtorua = 1 "
					+ " group by ospv.prua_id, prua.prua_dspavimentorua "
					+ " order by ospv.prua_id, prua.prua_dspavimentorua ";

			query = session.createSQLQuery(consulta)
					.addScalar("idPavimentoRua", Hibernate.INTEGER)
					// .addScalar("quantidade" , Hibernate.INTEGER)
					.addScalar("descricao", Hibernate.STRING)
					.addScalar("somatorioArea", Hibernate.BIG_DECIMAL);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			Collection coll = (Collection) query.list();

			if (coll != null) {
				Iterator it = coll.iterator();
				while (it.hasNext()) {
					Object[] obj = (Object[]) it.next();

					OSTipoPavimentoHelper osTipoPavimento = new OSTipoPavimentoHelper();
					osTipoPavimento.setId((Integer) obj[0]);
					// osTipoPavimento.setQuantidade((Integer)obj[1]);
					osTipoPavimento.setDescricao((String) obj[1]);
					osTipoPavimento.setSomatorioArea((BigDecimal) obj[2]);

					retorno.add(osTipoPavimento);
				}
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisa Ordens em Processo de Repavimentao
	 * 
	 * [UC0639] Filtrar Ordens em Processo de Repavimeta��o Calcada.
	 * 
	 * @author Yara Taciane
	 * @date 02/06/2008
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemProcessoRepavimentacaoPorTipoPavimentoRuaRet(
			OSPavimentoHelper oSPavimentoHelper)
					throws ErroRepositorioException {
		List retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";

		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select "
					+ "ospv.prua_idret  as idPavimentoRuaRet , "
					+ // 0 id do pavimento tipo
					"prua.prua_dspavimentorua as descricao,  "
					+ // 1 - descri��o do pavimento rua
					"sum(ospv.ospv_nnareapvtoruaret) as somatorioArea "
					+ // 2 - somat�rio da metragem do pavimento de rua.
					" from atendimentopublico.ordem_servico_pavimento ospv "
					+ "left join  atendimentopublico.ordem_servico orse on  orse.orse_id = ospv.orse_id "
					+ "left join  atendimentopublico.servico_tipo svtp on  svtp.svtp_id = orse.svtp_id "
					+ "left join  cadastro.pavimento_rua prua on  prua.prua_id = ospv.prua_idret "
					+

					"left join cadastro.imovel imov on imov.imov_id = orse.imov_id "
					+ "left join atendimentopublico.registro_atendimento rgat on rgat.rgat_id = orse.rgat_id "
					+

					"left join cadastro.logradouro_bairro logradouroBairroRA on logradouroBairroRA.lgbr_id = rgat.lgbr_id "
					+ "left join cadastro.logradouro logradouroRA on logradouroRA.logr_id = logradouroBairroRA.logr_id "
					+ "left join cadastro.bairro bairroRa on bairroRa.bair_id = logradouroBairroRA.bair_id "
					+

					"left join cadastro.logradouro_bairro logradouroBairroImovel on logradouroBairroImovel.lgbr_id = imov.lgbr_id "
					+ "left join cadastro.logradouro logradouroImovel on logradouroImovel.logr_id = logradouroBairroImovel.logr_id "
					+ "left join cadastro.bairro bairroImovel on bairroImovel.bair_id = logradouroBairroImovel.bair_id "
					+

					"left join  atendimentopublico.repavimento_mot_rejeicao mrej on mrej.rpmr_id = ospv.rpmr_id "
					+ "where ospv.unid_idrepavimentadora = :idUnidadeRepavimentadora ";

			parameters.put("idUnidadeRepavimentadora",
					oSPavimentoHelper.getIdUnidadeResponsavel());

			// Pendente -1-
			if (oSPavimentoHelper.getSituacaoRetorno().equals(1)) {
				consulta = consulta
						+ " and ospv.prua_idret is null and ospv.ospv_nnareapvtoruaret is null "
						+ " and ospv.pcal_idret is null and ospv.ospv_nnareapvtocalret is null "
						+ " and ospv.rpmr_id is null";
			}

			// Conclu�da -2-
			if (oSPavimentoHelper.getSituacaoRetorno().equals(2)) {
				consulta = consulta
						+ " and (( (ospv.prua_idret is not null and ospv.ospv_nnareapvtoruaret is not null) "
						+ " or (ospv.pcal_idret is not null and ospv.ospv_nnareapvtocalret is not null) ) "
						+ " and ospv.rpmr_id is null)";
			}

			// Rejeitadas -4-
			if (oSPavimentoHelper.getSituacaoRetorno().equals(4)) {
				consulta = consulta + " and ( ospv.rpmr_id is not null ) ";
			}

			// Numero da OS
			if (oSPavimentoHelper.getNumeroOS() != null) {
				consulta = consulta + " and ( orse.orse_id = :numeroOS ) ";

				parameters.put("numeroOS", oSPavimentoHelper.getNumeroOS());
			}

			// Com observacao de retorno
			if (oSPavimentoHelper.getIndicadorOsObservacaoRetorno() != null
					&& oSPavimentoHelper.getIndicadorOsObservacaoRetorno()
					.shortValue() == ConstantesSistema.SIM.shortValue()) {

				consulta = consulta
						+ " and ospv.ospv_dsobservacao is not null and cast(ospv.ospv_dsobservacao as VARCHAR2(255 CHAR)) is not null ";
			}

			// data de refer�ncia
			if (oSPavimentoHelper.getPeriodoEncerramentoOsInicial() != null
					&& oSPavimentoHelper.getPeriodoEncerramentoOsFinal() != null) {

				consulta = consulta
						+ " and orse.orse_tmencerramento between :dataInicial and :dataFinal ";

				parameters.put("dataInicial", Util.formatarDataInicial(Util
						.converteStringParaDate(oSPavimentoHelper
								.getPeriodoEncerramentoOsInicial())));
				parameters.put("dataFinal", Util.formatarDataFinal(Util
						.converteStringParaDate(oSPavimentoHelper
								.getPeriodoEncerramentoOsFinal())));

			} else if (oSPavimentoHelper.getPeriodoRetornoServicoInicial() != null
					&& oSPavimentoHelper.getPeriodoRetornoServicoFinal() != null) {
				consulta = consulta
						+ " and ospv.ospv_tmrepavretorno between :dataInicialRetornoServico and :dataFinalRetornoServico ";

				parameters.put("dataInicialRetornoServico", Util
						.formatarDataInicial(Util
								.converteStringParaDate(oSPavimentoHelper
										.getPeriodoRetornoServicoInicial())));
				parameters.put("dataFinalRetornoServico", Util
						.formatarDataFinal(Util
								.converteStringParaDate(oSPavimentoHelper
										.getPeriodoRetornoServicoFinal())));
			}

			if (oSPavimentoHelper.getIdMunicipio() != null) {
				consulta = consulta
						+ " and (bairroRa.muni_id = :idMunicipio or bairroImovel.muni_id = :idMunicipio) ";
				parameters.put("idMunicipio",
						oSPavimentoHelper.getIdMunicipio());
			}

			if (oSPavimentoHelper.getIdBairro() != null) {
				consulta = consulta
						+ " and (bairroRa.bair_id = :idBairro or bairroImovel.bair_id = :idBairro) ";
				parameters.put("idBairro", oSPavimentoHelper.getIdBairro());
			}

			if (oSPavimentoHelper.getIdLogradouro() != null) {
				consulta = consulta
						+ " and (logradouroRA.logr_id = :idLogradouro or logradouroImovel.logr_id = :idLogradouro) ";
				parameters.put("idLogradouro",
						oSPavimentoHelper.getIdLogradouro());
			}

			consulta = consulta + " and orse.orse_cdsituacao = 2 "
					+ " and svtp.svtp_icpvtorua = 1 "
					+ " group by ospv.prua_idret, prua.prua_dspavimentorua  "
					+ " order by ospv.prua_idret, prua.prua_dspavimentorua ";

			query = session.createSQLQuery(consulta)
					.addScalar("idPavimentoRuaRet", Hibernate.INTEGER)
					.addScalar("descricao", Hibernate.STRING)
					.addScalar("somatorioArea", Hibernate.BIG_DECIMAL);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			Collection coll = (Collection) query.list();

			if (coll != null) {
				Iterator it = coll.iterator();
				while (it.hasNext()) {
					Object[] obj = (Object[]) it.next();

					OSTipoPavimentoHelper osTipoPavimento = new OSTipoPavimentoHelper();
					osTipoPavimento.setId((Integer) obj[0]);
					osTipoPavimento.setDescricao((String) obj[1]);
					osTipoPavimento.setSomatorioArea((BigDecimal) obj[2]);

					retorno.add(osTipoPavimento);
				}
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0457]Encerrar Ordem de Servico
	 * 
	 * Obt�m as datas de gera��o de uma ordem de Servio
	 * 
	 * @author Yara Taciane
	 * @date 18/06/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 */
	public Date obterDataGeracaOS(Integer numeroOS)
			throws ErroRepositorioException {
		Date retorno = new Date();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT dataGeracao " + "FROM OrdemServico orse "
					+ "WHERE orse.id = :numeroOS";

			retorno = (Date) session.createQuery(consulta)
					.setInteger("numeroOS", numeroOS).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0457]Encerrar Ordem de Servico
	 * 
	 * Verifica se � uma OS seletiva.
	 * 
	 * @author Yara Taciane
	 * @date 18/06/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 */
	public Integer verificarOrdemServicoSeletiva(Integer numeroOS)
			throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT orse.id "
					+ "FROM OrdemServico orse "
					+ "WHERE orse.id = :numeroOS "
					+ "  and orse.registroAtendimento.id is null and orse.cobrancaDocumento.id is null";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("numeroOS", numeroOS).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0766] Gerar Relatorio Boletim de Ordens de Servico Concluidas
	 * 
	 * @author Ivan S�rgio
	 * @date 07/05/2008
	 * 
	 * @param idEmpresa
	 * @param idLocalidade
	 * @param idSetorComercial
	 * @param referenciaEncerramento
	 * @param situacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarBoletimOrdensServicoConcluidasGerarRelatorio(
			Integer idEmpresa, Integer idLocalidade, Integer idSetorComercial,
			String referenciaEncerramento, Short situacao)
					throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String hql = "select "
					+ "	bo.id, "
					+ "	bo.anoMesReferenciaBoletim, "
					+ "	bo.codigoFiscalizacao, "
					+ "	bo.indicadorTrocaProtecaoHidrometro, "
					+ "	bo.indicadorTrocaRegistroHidrometro, "
					+ "	os.servicoTipo.id, "
					+ "	loc.id, "
					+ "	loc.descricao, "
					+ "	li.id, "
					+ "	li.descricao, "
					+ "	uni_org.empresa.descricaoAbreviada, "
					+ "	setor.codigo, "
					+ "	qua.numeroQuadra, "
					+ "	im.lote, "
					+ "	im.subLote, "
					+ "	os.dataGeracao, "
					+ "	os.dataEncerramento, "
					+ "	bo.dataFiscalizacao, "
					+ "	fis.dataFiscalizacaoOrdemServico2, "
					+ "	fis.dataFiscalizacaoOrdemServico3, "
					+ "	bo.dataEncerramentoBoletim, "
					+ "	im.id, "
					+ "	setor.id "
					+ "from "
					+ "	gcom.atendimentopublico.ordemservico.BoletimOsConcluida bo "
					+ "	inner join bo.ordemServico os "
					+ "	inner join os.imovel im "
					+ "	inner join im.localidade loc "
					+ "	inner join im.setorComercial setor "
					+ "	inner join im.quadra qua "
					+ "	left join bo.hidrometroLocalInstalacao li "
					+ "	inner join os.ordemServicoUnidades os_uni "
					+ "	inner join os_uni.unidadeOrganizacional uni_org "
					+ "	inner join uni_org.empresa emp "
					+ "	left join bo.dataFiscalizacaoOsSeletivas fis "
					+ "where " + "	emp.id = " + idEmpresa + " ";

			// Localidade
			if (idLocalidade != null) {
				hql += "	and im.localidade.id = " + idLocalidade + " ";

				// Setor Comercial
				if (idSetorComercial != null) {
					hql += "	and setor.id = " + idSetorComercial + " ";
				}
			}

			if (situacao == 1) {
				hql += "	and bo.codigoFiscalizacao in (0, 1) "
						+ "	and bo.anoMesReferenciaBoletim = '"
						+ referenciaEncerramento + "' ";
				// Situacao - Reprovadas
			} else {
				hql += "	and bo.codigoFiscalizacao = 2 "
						+ "	and bo.anoMesReferenciaBoletim <= '"
						+ referenciaEncerramento + "' ";
			}

			hql += "group by " + "	li.id, " + "	li.descricao, " + "	setor.id, "
					+ "	setor.codigo, " + "	qua.numeroQuadra, " + "	loc.id, "
					+ "	loc.descricao, " + "	bo.id, " + "	im.id, "
					+ "	bo.anoMesReferenciaBoletim, "
					+ "	bo.codigoFiscalizacao, "
					+ "	bo.indicadorTrocaProtecaoHidrometro, "
					+ "	bo.indicadorTrocaRegistroHidrometro, "
					+ "	bo.hidrometroLocalInstalacao.id, "
					+ "	os.servicoTipo.id, "
					+ "	uni_org.empresa.descricaoAbreviada, " + "	im.lote, "
					+ "	im.subLote, " + "	os.dataGeracao, "
					+ "	os.dataEncerramento, " + "	bo.dataFiscalizacao, "
					+ "	fis.dataFiscalizacaoOrdemServico2, "
					+ "	fis.dataFiscalizacaoOrdemServico3, "
					+ "	bo.dataEncerramentoBoletim " + "order by "
					+ "	setor.id, " + "	li.id, " + "	bo.id";

			retorno = session.createQuery(hql).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0427] Tramitar Registro de Atendimento
	 * 
	 * @author Vivianne Sousa
	 * @date 10/06/2008
	 * 
	 * @throws ControladorException
	 */
	public void atualizarUnidadeOrganizacionalAtualOS(
			Integer idUnidadeOrganizacionalAtual, Integer idRA)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			String atualizarOS = "";

			atualizarOS = "update gcom.atendimentopublico.ordemservico.OrdemServico "
					+ "set orse_tmultimaalteracao = :ultimaAlteracao, "
					+ "unid_idatual = :idUnidadeOrganizacionalAtual ";

			atualizarOS = atualizarOS + " where rgat_id = :idRA";

			session.createQuery(atualizarOS)
			.setInteger("idUnidadeOrganizacionalAtual",
					idUnidadeOrganizacionalAtual)
					.setTimestamp("ultimaAlteracao", new Date())
					.setInteger("idRA", idRA).executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programao de Ordens de Servio [FS0008]
	 * - Verificar possibilidade da incluso da ordem de Servio
	 * 
	 * @author Vivianne Sousa
	 * @date 12/06/2008
	 * 
	 * @throws ControladorException
	 */
	public UnidadeOrganizacional obterUnidadeAtualOS(Integer idOS)
			throws ErroRepositorioException {

		UnidadeOrganizacional retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT unde " + "FROM OrdemServico os "
					+ "LEFT JOIN os.unidadeAtual unde "
					+ "WHERE os.id = :idOS ";

			retorno = (UnidadeOrganizacional) session.createQuery(consulta)
					.setInteger("idOS", idOS).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0456] Elaborar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Vivianne Sousa
	 * @date 13/06/2008
	 */
	public Collection<Integer> recuperaRegistroAtendimentoPendenteUnidadeAtual(
			Integer unidadeAtual) throws ErroRepositorioException {

		Collection<Integer> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT DISTINCT ra.id "
					+ "FROM RegistroAtendimento ra "
					+ "LEFT JOIN ra.unidadeAtual unidadeAtual "
					+ "WHERE unidadeAtual.id = :idUnidade "
					+ "AND ra.codigoSituacao = :situacaoPendente ";

			retorno = session
					.createQuery(consulta)
					.setInteger("idUnidade", unidadeAtual)
					.setInteger("situacaoPendente",
							RegistroAtendimento.SITUACAO_PENDENTE).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0427] - Tramitar Registro Atendimento [FS0011]Validar Tramite para
	 * terceira
	 * 
	 * @author Vivianne Sousa
	 * @date 29/10/2008
	 */
	public boolean existeMaisDeUmaOrdemServicoPendente(
			Integer idregistroAtendimento) throws ErroRepositorioException {
		boolean retorno = false;
		Session session = HibernateUtil.getSession();
		try {
			String consulta = "select os from OrdemServico os where os.registroAtendimento.id = "
					+ idregistroAtendimento
					+ " and os.situacao = "
					+ OrdemServico.SITUACAO_PENDENTE;

			Collection collection = session.createQuery(consulta).list();
			if (collection != null && !collection.isEmpty()
					&& collection.size() > 1) {
				retorno = true;
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0488] - Consultar Dados do Registro de Atendimento
	 * 
	 * Dados da Os Associadas
	 * 
	 * @author Arthur Carvalho
	 * @date 17/02/2009
	 */
	public Collection pesquisarOrdensServicosAssociados(
			Integer idregistroAtendimento) throws ErroRepositorioException {

		Collection retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		try {
			String consulta = "SELECT " + "os.id," + "os.dataGeracao,"
					+ "servicoTipo.id," + "servicoTipo.descricao,"
					+ "os.dataEncerramento,"
					+ "os.descricaoParecerEncerramento "
					+ "FROM OrdemServico os "
					+ "INNER JOIN os.servicoTipo servicoTipo  "
					+ "WHERE os.registroAtendimento.id = :idRA";

			retorno = session.createQuery(consulta)
					.setInteger("idRA", idregistroAtendimento).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Retorna a quantidade de registros para geracao do relatorio
	 * 
	 * [UC0711] Filtro para Emissao de Ordens Seletivas
	 * 
	 * @author Anderson Italo
	 * @date 21/08/2009
	 * 
	 * @param ImovelEmissaoOrdensSeletivasHelper
	 *            , dataInstalacaoHidrometro, anoMesFaturamento
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer filtrarImovelEmissaoOrdensSeletivasCount(
			ImovelEmissaoOrdensSeletivasHelper helper,
			String dataInstalacaoHidrometroInicial, String anoMesFaturamento,
			String dataInstalacaoHidrometroFinal)
					throws ErroRepositorioException {

		Integer retorno = null;
		String hqlAux = "";
		boolean finaliza = false;

		Session session = HibernateUtil.getSession();

		if (helper.getMesAnoInstalacaoInicialHidrometro() != null
				&& !helper.getMesAnoInstalacaoInicialHidrometro().equals("")) {
			dataInstalacaoHidrometroInicial = Util
					.formatarDataComTracoAAAAMMDD(Util
							.converteStringInvertidaSemBarraParaDate(helper
									.getMesAnoInstalacaoInicialHidrometro()
									+ "01"));

			dataInstalacaoHidrometroFinal = Util
					.formatarDataComTracoAAAAMMDD(Util.converteStringInvertidaSemBarraParaDate(Util
							.somaMesAnoMesReferencia(
									new Integer(
											helper.getMesAnoInstalacaoFinalHidrometro()),
											1).toString()
											+ "01"));
		} else {

			Date data = Util.subtrairNumeroDiasDeUmaData(new Date(), 30);
			dataInstalacaoHidrometroFinal = Util
					.formatarDataComTracoAAAAMMDD(data);
		}

		try {
			String hql = "select count(distinct imovel.id) " + // O distinct foi
					// utilizado por
					// conta das
					// sub-categorias
					"from ";

			// Se informou Categoria ou SubCategoria
			if (helper.getCategoria() != null
					|| helper.getSubCategoria() != null) {
				hql += "	gcom.cadastro.imovel.ImovelSubcategoria imovelSubcategoria "
						+ "	inner join imovelSubcategoria.comp_id.imovel imovel "
						+ "	left  join imovelSubcategoria.comp_id.subcategoria subcategoria "
						+ "	left  join subcategoria.categoria categoria ";
			} else {
				hql += "	gcom.cadastro.imovel.Imovel imovel ";
			}

			// se informou Logradouro
			if (helper.getLogradouro() != null
					&& !helper.getLogradouro().equals("")) {
				hql += "	left  join imovel.logradouroCep logradouroCep "
						+ "	left  join logradouroCep.logradouro logradouro ";
			}

			hql += "	inner join imovel.localidade localidade ";

			// Se unidade/gerencia ou elo
			if (helper.getGerenciaRegional() != null
					&& !helper.getGerenciaRegional().equals(
							ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				hql += "	inner join localidade.gerenciaRegional gerenciaRegional ";
			}

			if ((helper.getUnidadeNegocio() != null && !helper
					.getUnidadeNegocio().equals(
							ConstantesSistema.NUMERO_NAO_INFORMADO))
							|| (helper.getElo() != null & !helper.getElo().equals(""))) {
				hql += "	inner join localidade.unidadeNegocio unidadeNegocio ";
			}

			// Setor Comercial
			if (helper.getSetorComercialInicial() != null
					&& !helper.getSetorComercialInicial().equals("")
					&& helper.getSetorComercialFinal() != null
					&& !helper.getSetorComercialFinal().equals("")) {
				hql += "	inner join imovel.setorComercial setorComercial "
						+ "	inner join imovel.quadra quadra "
						+ "		with quadra.setorComercial.id = setorComercial.id ";
			}

			// Se informou Perfil Imovel
			if (helper.getPerfilImovel() != null) {
				hql += "	left  join imovel.imovelPerfil imovelPerfil ";
			}

			hql += "	left  join imovel.ligacaoAgua ligacaoAgua "
					+ "   left  join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "
					+ "	left  join ligacaoAgua.medicaoHistoricos medicaoHistorico ";

			// Tipo Medicao
			if (helper.getTipoMedicao() != null) {
				// hql += "and medicaoHistorico.medicaoTipo.id = " + tipoMedicao
				// + " ";
				hql += "with medicaoHistorico.medicaoTipo.id = "
						+ helper.getTipoMedicao() + " "
						+ "and medicaoHistorico.anoMesReferencia = "
						+ anoMesFaturamento + " ";
			}

			if (helper.getTipoOrdem().equals("" + ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO)
					|| helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_SUBSTITUICAO_HIDROMETRO_CONTROLE_DE_PERDAS)
					|| helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_SUBSTITUICAO_RAMAL_COM_SUBSTITUICAO_HDT_CONTROLE_DE_PERDAS)
					|| helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_SUBSTITUICAO_RAMAL_SEM_INST_HDT_CONTROLE_DE_PERDAS) 
					|| helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_SUBSTITUICAO_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS)
					|| helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_SUBSTITUICAO_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS)
					|| helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_SUBSTITUICAO_RAMAL_SEM_INST_HDT_CONTROLE_DE_PERDAS) 
					|| helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_INSTALACAO_HIDROMETRO_CONTROLE_DE_PERDAS) 
					|| helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_SUBSTITUICAO_HIDROMETRO_CONTROLE_DE_PERDAS) 
					|| helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_SUBSTITUICAO_RAMAL_COM_SUBSTITUICAO_HDT_CONTROLE_DE_PERDAS)
					|| helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_REFORMA_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS)
					|| helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_REFORMA_RAMAL_SEM_HINST_DT_CONTROLE_DE_PERDAS)
					|| helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_REFORMA_RAMAL_COM_SUBSTITUICAO_HDT_CONTROLE_DE_PERDAS)
					|| helper.getTipoOrdem().equals("" + ServicoTipo.TIPO_EFETUAR_REMOCAO_HIDROMETRO)
					|| helper.getTipoOrdem().equals("" + ServicoTipo.TIPO_INSPECAO_ANORMALIDADE)) {
				hql += " left  join ligacaoAgua.hidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoAgua "
						+ " left  join hidrometroInstalacaoHistoricoAgua.hidrometro hidrometroAgua "
						+ " left  join imovel.hidrometroInstalacaoHistorico hidrometroInstalacaoHistorico "
						+ " left  join hidrometroInstalacaoHistorico.hidrometro hidrometro ";
				/*
				 * // Local Instala��o Hidrometro
				 * if(helper.getLocalInstalacaoHidrometro() != null){ hql +=
				 * " left join hidrometroInstalacaoHistoricoAgua.hidrometroLocalInstalacao localInstalacaoAgua "
				 * +
				 * " left join hidrometroInstalacaoHistorico.hidrometroLocalInstalacao localInstalacaoEsg "
				 * ; }
				 */
			}

			hql += "	left  join imovel.consumosHistoricos consumosHistorico "
					+ "		with consumosHistorico.referenciaFaturamento <= "
					+ anoMesFaturamento + " ";

			if (helper.getTipoMedicao() != null) {
				if (helper.getTipoMedicao().equals(
						MedicaoTipo.LIGACAO_AGUA.toString())) {
					hql += "and consumosHistorico.ligacaoTipo.id = "
							+ LigacaoTipo.LIGACAO_AGUA + " ";
				}

				if (helper.getTipoMedicao().equals(MedicaoTipo.POCO.toString())) {
					hql += "and consumosHistorico.ligacaoTipo.id = "
							+ LigacaoTipo.LIGACAO_ESGOTO + " ";
				}
			}

			hql += "where " + "	imovel.indicadorExclusao = 2 and ";

			if (helper.getIdImovel() != null) {
				hql += "	imovel.id = " + helper.getIdImovel().toString()
						+ " and ";
			}

			if (helper.getTipoOrdem().equals("" + ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO) 
					|| (helper.getTipoOrdem().equals("" + ServicoTipo.TIPO_INSTALACAO_HIDROMETRO_CONTROLE_DE_PERDAS))
					|| (helper.getTipoOrdem().equals("" + ServicoTipo.TIPO_INSTALACAO_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS))
					|| (helper.getTipoOrdem().equals("" + ServicoTipo.TIPO_INSTALACAO_RAMAL_SEM_INST_HDT_CONTROLE_DE_PERDAS))) {
				if (helper.getTipoMedicao() != null) {
					if (helper.getTipoMedicao().equals(
							MedicaoTipo.LIGACAO_AGUA.toString())) {
						// hqlAux += "(imovel.ligacaoAguaSituacao.id = " +
						// LigacaoAguaSituacao.LIGADO + " or ";
						// hqlAux += "imovel.ligacaoAguaSituacao.id = " +
						// LigacaoAguaSituacao.LIGADO_A_REVELIA + ") and ";
						hqlAux += "ligacaoAgua.hidrometroInstalacaoHistorico.id is null and ";
						hqlAux += "ligacaoAguaSituacao.indicadorFaturamentoSituacao = 1 and ";
						hqlAux += "ligacaoAguaSituacao.id = imovel.ligacaoAguaSituacao and ";
						finaliza = true;
					} else if (helper.getTipoMedicao().equals(
							MedicaoTipo.POCO.toString())) {
						// hqlAux += "imovel.ligacaoEsgotoSituacao.id = " +
						// LigacaoEsgotoSituacao.LIGADO + " and ";
						hqlAux += "imovel.hidrometroInstalacaoHistorico.id is null and ";
						hqlAux += "ligacaoEsgotoSituacao.indicadorFaturamentoSituacao = 1 and ";
						hqlAux += "ligacaoEsgotoSituacao.id = imovel.ligacaoEsgotoSituacao and ";
					}
				}
			} else if (helper.getTipoOrdem().equals("" + ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO)
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_SUBSTITUICAO_HIDROMETRO_CONTROLE_DE_PERDAS))
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_SUBSTITUICAO_RAMAL_COM_SUBSTITUICAO_HDT_CONTROLE_DE_PERDAS))
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_SUBSTITUICAO_RAMAL_SEM_INST_HDT_CONTROLE_DE_PERDAS)) 
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_SUBSTITUICAO_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS)) 
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_SUBSTITUICAO_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS)) 
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_SUBSTITUICAO_RAMAL_SEM_INST_HDT_CONTROLE_DE_PERDAS)) 
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_INSTALACAO_HIDROMETRO_CONTROLE_DE_PERDAS)) 
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_SUBSTITUICAO_HIDROMETRO_CONTROLE_DE_PERDAS)) 
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_RELIGACAO_E_SUBSTITUICAO_RAMAL_COM_SUBSTITUICAO_HDT_CONTROLE_DE_PERDAS))
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_REFORMA_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS))
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_REFORMA_RAMAL_SEM_HINST_DT_CONTROLE_DE_PERDAS))
					|| (helper.getTipoOrdem().equals(""+ ServicoTipo.TIPO_REFORMA_RAMAL_COM_SUBSTITUICAO_HDT_CONTROLE_DE_PERDAS))) {
				
				if (helper.getTipoMedicao() != null) {
					if (helper.getTipoMedicao().equals(
							MedicaoTipo.LIGACAO_AGUA.toString())) {
						// hqlAux += "(imovel.ligacaoAguaSituacao.id = " +
						// LigacaoAguaSituacao.LIGADO + " or ";
						// hqlAux += "imovel.ligacaoAguaSituacao.id = " +
						// LigacaoAguaSituacao.LIGADO_A_REVELIA + ") and ";
						hqlAux += "hidrometroInstalacaoHistoricoAgua.id is not null and ";
						hqlAux += "ligacaoAguaSituacao.indicadorFaturamentoSituacao = 1 and ";
						hqlAux += "ligacaoAguaSituacao.id = imovel.ligacaoAguaSituacao and ";
						hqlAux += "hidrometroInstalacaoHistoricoAgua.dataRetirada is null and ";
						if (dataInstalacaoHidrometroFinal != null
								&& !dataInstalacaoHidrometroFinal.equals("")) {

							if (dataInstalacaoHidrometroInicial != null
									&& !dataInstalacaoHidrometroInicial
									.equals("")) {
								hqlAux += "hidrometroInstalacaoHistoricoAgua.dataInstalacao between to_date('"
										+ dataInstalacaoHidrometroInicial
										+ "','YYYY-MM-DD') and to_date('"
										+ dataInstalacaoHidrometroFinal
										+ "','YYYY-MM-DD') and ";
							} else {
								hqlAux += "hidrometroInstalacaoHistoricoAgua.dataInstalacao <= to_date('"
										+ dataInstalacaoHidrometroFinal
										+ "','YYYY-MM-DD') and ";
							}

						}

						finaliza = true;
					} else if (helper.getTipoMedicao().equals(
							MedicaoTipo.POCO.toString())) {
						// hqlAux += "imovel.ligacaoEsgotoSituacao.id = " +
						// LigacaoEsgotoSituacao.LIGADO + " and ";
						hqlAux += "hidrometroInstalacaoHistorico.id is not null and ";
						hqlAux += "ligacaoEsgotoSituacao.indicadorFaturamentoSituacao = 1 and ";
						hqlAux += "ligacaoEsgotoSituacao.id = imovel.ligacaoEsgotoSituacao and ";
						if (dataInstalacaoHidrometroFinal != null
								&& !dataInstalacaoHidrometroFinal.equals("")) {

							if (dataInstalacaoHidrometroInicial != null
									&& !dataInstalacaoHidrometroInicial
									.equals("")) {
								hqlAux += "hidrometroInstalacaoHistoricoAgua.dataInstalacao between to_date('"
										+ dataInstalacaoHidrometroInicial
										+ "','YYYY-MM-DD') and to_date('"
										+ dataInstalacaoHidrometroFinal
										+ "','YYYY-MM-DD') and ";
							} else {
								hqlAux += "hidrometroInstalacaoHistoricoAgua.dataInstalacao <= to_date('"
										+ dataInstalacaoHidrometroFinal
										+ "','YYYY-MM-DD') and ";
							}

						}

						finaliza = true;
					}

					// Local Instala��o Hidrometro
					if (helper.getLocalInstalacaoHidrometro() != null) {
						if (helper.getTipoMedicao().equals(
								MedicaoTipo.LIGACAO_AGUA.toString())) {
							hqlAux += "hidrometroInstalacaoHistoricoAgua.hidrometroLocalInstalacao.id = "
									+ helper.getLocalInstalacaoHidrometro()
									+ " and ";
							finaliza = true;
						} else if (helper.getTipoMedicao().equals(
								MedicaoTipo.POCO.toString())) {
							hqlAux += "hidrometroInstalacaoHistorico.hidrometroLocalInstalacao.id = "
									+ helper.getLocalInstalacaoHidrometro()
									+ " and ";
							finaliza = true;
						}
					}
				}

				// Capacidade Hidrometro
				if (helper.getCapacidadeHidrometro() != null) {
					if (!helper.getCapacidadeHidrometro().equals("")) {
						String idCapacidades = "";
						for (int i = 0; i < helper.getCapacidadeHidrometro().length; i++) {
							idCapacidades += helper.getCapacidadeHidrometro()[i]
									+ ",";
						}
						idCapacidades = idCapacidades.substring(0,
								(idCapacidades.length() - 1));
						if (helper.getTipoMedicao().equals(
								MedicaoTipo.LIGACAO_AGUA.toString())) {
							hqlAux += "hidrometroAgua.hidrometroCapacidade.id in ("
									+ idCapacidades + ") and ";
							finaliza = true;
						} else if (helper.getTipoMedicao().equals(
								MedicaoTipo.POCO.toString())) {
							hqlAux += "hidrometro.hidrometroCapacidade.id in ("
									+ idCapacidades + ") and ";
							finaliza = true;
						}
					}
				}
				// Marca Hidrometro
				if (helper.getMarcaHidrometro() != null) {
					if (helper.getTipoMedicao().equals(
							MedicaoTipo.LIGACAO_AGUA.toString())) {
						hqlAux += "hidrometroAgua.hidrometroMarca.id = "
								+ helper.getMarcaHidrometro() + " and ";
						finaliza = true;
					} else if (helper.getTipoMedicao().equals(
							MedicaoTipo.POCO.toString())) {
						hqlAux += "hidrometro.hidrometroMarca.id = "
								+ helper.getMarcaHidrometro() + " and ";
						finaliza = true;
					}
				}
				// Anormalidade Hidrometro
				if (helper.getAnormalidadeHidrometro() != null) {
					if (!helper.getAnormalidadeHidrometro().equals("")) {
						String idAnormalidades = "";
						for (int i = 0; i < helper.getAnormalidadeHidrometro().length; i++) {
							idAnormalidades += helper
									.getAnormalidadeHidrometro()[i] + ",";
						}
						idAnormalidades = idAnormalidades.substring(0,
								(idAnormalidades.length() - 1));
						hqlAux += "medicaoHistorico.leituraAnormalidadeFaturamento in ("
								+ idAnormalidades + ") and ";
						finaliza = true;
					}
				}

				// MesAno Instalacao Hidrometro
				// if (helper.getMesAnoInstalacaoHidrometro() != null) {
				/*
				 * if (helper.getMesAnoInstalacaoInicialHidrometro() != null &&
				 * helper.getMesAnoInstalacaoFinalHidrometro() != null){ if
				 * (helper
				 * .getTipoMedicao().equals(MedicaoTipo.LIGACAO_AGUA.toString
				 * ())) { hqlAux +=
				 * "cast((substring(hidrometroInstalacaoHistoricoAgua.dataInstalacao, 1, 4) || "
				 * +
				 * "substring(hidrometroInstalacaoHistoricoAgua.dataInstalacao, 6, 2)) as integer) between "
				 * + helper.getMesAnoInstalacaoInicialHidrometro() + " and " +
				 * helper.getMesAnoInstalacaoFinalHidrometro() + " and ";
				 * finaliza = true; }else if
				 * (helper.getTipoMedicao().equals(MedicaoTipo.POCO.toString()))
				 * { hqlAux +=
				 * "cast((substring(hidrometroInstalacaoHistorico.dataInstalacao, 1, 4) || "
				 * +
				 * "substring(hidrometroInstalacaoHistorico.dataInstalacao, 6, 2)) as integer) between "
				 * + helper.getMesAnoInstalacaoInicialHidrometro() + " and " +
				 * helper.getMesAnoInstalacaoFinalHidrometro() + " and ";
				 * finaliza = true; } }
				 */

			} else if (helper.getTipoOrdem().equals(
					"" + ServicoTipo.TIPO_EFETUAR_REMOCAO_HIDROMETRO)
					|| helper.getTipoOrdem().equals(
							"" + ServicoTipo.TIPO_INSPECAO_ANORMALIDADE)) {
				if (helper.getTipoMedicao() != null) {
					if (helper.getTipoMedicao().equals(
							MedicaoTipo.LIGACAO_AGUA.toString())) {
						// hqlAux += "(imovel.ligacaoAguaSituacao.id = " +
						// LigacaoAguaSituacao.LIGADO + " or ";
						// hqlAux += "imovel.ligacaoAguaSituacao.id = " +
						// LigacaoAguaSituacao.LIGADO_A_REVELIA + ") and ";
						hqlAux += "hidrometroInstalacaoHistoricoAgua.id is not null and ";
						if (dataInstalacaoHidrometroFinal != null
								&& !dataInstalacaoHidrometroFinal.equals("")) {

							if (dataInstalacaoHidrometroInicial != null
									&& !dataInstalacaoHidrometroInicial
									.equals("")) {
								hqlAux += "hidrometroInstalacaoHistoricoAgua.dataInstalacao between to_date('"
										+ dataInstalacaoHidrometroInicial
										+ "','YYYY-MM-DD') and to_date('"
										+ dataInstalacaoHidrometroFinal
										+ "','YYYY-MM-DD') and ";
							} else {
								hqlAux += "hidrometroInstalacaoHistoricoAgua.dataInstalacao <= to_date('"
										+ dataInstalacaoHidrometroFinal
										+ "','YYYY-MM-DD') and ";
							}

						}

						finaliza = true;
					} else if (helper.getTipoMedicao().equals(
							MedicaoTipo.POCO.toString())) {
						// hqlAux += "imovel.ligacaoEsgotoSituacao.id = " +
						// LigacaoEsgotoSituacao.LIGADO + " and ";
						hqlAux += "hidrometroInstalacaoHistorico.id is not null and ";
						if (dataInstalacaoHidrometroFinal != null
								&& !dataInstalacaoHidrometroFinal.equals("")) {

							if (dataInstalacaoHidrometroInicial != null
									&& !dataInstalacaoHidrometroInicial
									.equals("")) {
								hqlAux += "hidrometroInstalacaoHistoricoAgua.dataInstalacao between to_date('"
										+ dataInstalacaoHidrometroInicial
										+ "','YYYY-MM-DD') and to_date('"
										+ dataInstalacaoHidrometroFinal
										+ "','YYYY-MM-DD') and ";
							} else {
								hqlAux += "hidrometroInstalacaoHistoricoAgua.dataInstalacao <= to_date('"
										+ dataInstalacaoHidrometroFinal
										+ "','YYYY-MM-DD') and ";
							}

						}

						finaliza = true;
					}

					// Local Instala��o Hidrometro
					if (helper.getLocalInstalacaoHidrometro() != null) {
						if (helper.getTipoMedicao().equals(
								MedicaoTipo.LIGACAO_AGUA.toString())) {
							hqlAux += "hidrometroInstalacaoHistoricoAgua.hidrometroLocalInstalacao.id = "
									+ helper.getLocalInstalacaoHidrometro()
									+ " and ";
							finaliza = true;
						} else if (helper.getTipoMedicao().equals(
								MedicaoTipo.POCO.toString())) {
							hqlAux += "hidrometroInstalacaoHistorico.hidrometroLocalInstalacao.id = "
									+ helper.getLocalInstalacaoHidrometro()
									+ " and ";
							finaliza = true;
						}
					}

				}

				// Capacidade Hidrometro

				if (helper.getCapacidadeHidrometro() != null) {
					if (!helper.getCapacidadeHidrometro().equals("")) {
						String idCapacidades = "";
						for (int i = 0; i < helper.getCapacidadeHidrometro().length; i++) {
							idCapacidades += helper.getCapacidadeHidrometro()[i]
									+ ",";
						}
						idCapacidades = idCapacidades.substring(0,
								(idCapacidades.length() - 1));
						if (helper.getTipoMedicao().equals(
								MedicaoTipo.LIGACAO_AGUA.toString())) {
							hqlAux += "hidrometroAgua.hidrometroCapacidade.id in ("
									+ idCapacidades + ") and ";
							finaliza = true;
						} else if (helper.getTipoMedicao().equals(
								MedicaoTipo.POCO.toString())) {
							hqlAux += "hidrometro.hidrometroCapacidade.id in ("
									+ idCapacidades + ") and ";
							finaliza = true;
						}
					}
				}
				// Marca Hidrometro
				if (helper.getMarcaHidrometro() != null) {
					if (helper.getTipoMedicao().equals(
							MedicaoTipo.LIGACAO_AGUA.toString())) {
						hqlAux += "hidrometroAgua.hidrometroMarca.id = "
								+ helper.getMarcaHidrometro() + " and ";
						finaliza = true;
					} else if (helper.getTipoMedicao().equals(
							MedicaoTipo.POCO.toString())) {
						hqlAux += "hidrometro.hidrometroMarca.id = "
								+ helper.getMarcaHidrometro() + " and ";
						finaliza = true;
					}
				}

				// Anormalidade Hidrometro
				if (helper.getAnormalidadeHidrometro() != null) {
					if (!helper.getAnormalidadeHidrometro().equals("")) {
						String idAnormalidades = "";
						for (int i = 0; i < helper.getAnormalidadeHidrometro().length; i++) {
							idAnormalidades += helper
									.getAnormalidadeHidrometro()[i] + ",";
						}
						idAnormalidades = idAnormalidades.substring(0,
								(idAnormalidades.length() - 1));
						hqlAux += "medicaoHistorico.leituraAnormalidadeFaturamento in ("
								+ idAnormalidades + ") and ";
						finaliza = true;
					}
				}

			}

			// Cria as condicoes

			// GERENCIA REGIONAL
			if (helper.getGerenciaRegional() != null
					&& !helper.getGerenciaRegional().equals(
							ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				hqlAux += "gerenciaRegional.id = "
						+ helper.getGerenciaRegional() + " and ";
				finaliza = true;
			}

			// UNIDADE NEGOCIO
			if (helper.getUnidadeNegocio() != null
					&& !helper.getUnidadeNegocio().equals(
							ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				hqlAux += "unidadeNegocio.id = " + helper.getUnidadeNegocio()
						+ " and ";
				finaliza = true;
			}

			// Elo
			if (helper.getElo() != null & !helper.getElo().equals("")) {
				hqlAux += "localidade.localidade = " + helper.getElo()
						+ " and ";
				finaliza = true;
			}

			// Logradouro
			if (helper.getLogradouro() != null
					& !helper.getLogradouro().equals("")) {
				hqlAux += "logradouro.id = " + helper.getLogradouro() + " and ";
				finaliza = true;
			}

			// Localidade
			if (helper.getLocalidadeInicial() != null
					&& !helper.getLocalidadeInicial().equals("")
					&& helper.getLocalidadeFinal() != null
					&& !helper.getLocalidadeFinal().equals("")) {
				hqlAux += "(localidade.id between "
						+ helper.getLocalidadeInicial() + " and "
						+ helper.getLocalidadeFinal() + ") and ";
				finaliza = true;
			}
			// Setor Comercial
			if (helper.getSetorComercialInicial() != null
					&& !helper.getSetorComercialInicial().equals("")
					&& helper.getSetorComercialFinal() != null
					&& !helper.getSetorComercialFinal().equals("")) {
				hqlAux += "(setorComercial.id between "
						+ helper.getSetorComercialInicial() + " and "
						+ helper.getSetorComercialFinal() + ") and ";
				finaliza = true;
			}
			// Quadra
			if (helper.getQuadraInicial() != null
					&& !helper.getQuadraInicial().equals("")
					&& helper.getQuadraFinal() != null
					&& !helper.getQuadraFinal().equals("")) {
				hqlAux += "(quadra.numeroQuadra between " + helper.getQuadraInicial()
						+ " and " + helper.getQuadraFinal() + ") and ";
				finaliza = true;
			}
			// Rota
			if (helper.getRotaInicial() != null
					&& !helper.getRotaInicial().equals("")
					&& helper.getRotaFinal() != null
					&& !helper.getRotaFinal().equals("")) {
				hqlAux += "(quadra.rota.codigo between "
						+ helper.getRotaInicial() + " and "
						+ helper.getRotaFinal() + ") and ";
				finaliza = true;
			}
			// Perfil Imovel
			if (helper.getPerfilImovel() != null) {
				hqlAux += "imovelPerfil.id = " + helper.getPerfilImovel()
						+ " and ";
				finaliza = true;
			}
			// Categoria
			if (helper.getCategoria() != null) {
				hqlAux += "categoria.id = " + helper.getCategoria() + " and ";
				finaliza = true;
			}
			// Subcategoria
			if (helper.getSubCategoria() != null) {
				hqlAux += "subcategoria.id = " + helper.getSubCategoria()
						+ " and ";
				finaliza = true;
			}
			// Quantidade de Economias
			if (helper.getQuantidadeEconomiasInicial() != null
					&& !helper.getQuantidadeEconomiasInicial().equals("")
					&& helper.getQuantidadeEconomiasFinal() != null
					&& !helper.getQuantidadeEconomiasFinal().equals("")) {
				hqlAux += "(imovel.quantidadeEconomias between "
						+ helper.getQuantidadeEconomiasInicial() + " and "
						+ helper.getQuantidadeEconomiasFinal() + ") and ";
				finaliza = true;
			}

			// Numero Moradores
			if (helper.getNumeroMoradoresInicial() != null
					&& !helper.getNumeroMoradoresInicial().equals("")
					&& helper.getNumeroMoradoresFinal() != null
					&& !helper.getNumeroMoradoresFinal().equals("")) {
				hqlAux += "(imovel.numeroMorador between "
						+ helper.getNumeroMoradoresInicial() + " and "
						+ helper.getNumeroMoradoresFinal() + ") and ";
				finaliza = true;
			}
			// Area Construida
			if (helper.getAreaConstruidaInicial() != null
					&& !helper.getAreaConstruidaInicial().equals("")
					&& helper.getAreaConstruidaFinal() != null
					&& !helper.getAreaConstruidaFinal().equals("")) {
				hqlAux += "(imovel.areaConstruida between "
						+ helper.getAreaConstruidaInicial() + " and "
						+ helper.getAreaConstruidaFinal() + ") and ";
				finaliza = true;
			}
			// Imovel Condominio
			if (helper.getImovelCondominio().equals("1")) {
				hqlAux += "imovel.indicadorImovelCondominio = "
						+ Imovel.IMOVEL_CONDOMINIO + " and ";
				finaliza = true;
			}
			// Media do Imovel
			if (helper.getMediaImovel() != null
					&& !helper.getMediaImovel().equals("")) {
				// hqlAux += "consumosHistorico.consumoMedio <= " + mediaImovel
				// + " and ";
				hqlAux += "consumosHistorico.consumoMedio >= "
						+ helper.getMediaImovel() + " and ";
				finaliza = true;
			}

			// Consumo por Economia
			if (helper.getConsumoPorEconomia() != null
					&& !helper.getConsumoPorEconomia().equals("")
					&& !helper.getConsumoPorEconomia().equals("--")
					&& helper.getConsumoPorEconomiaFinal() != null
					&& !helper.getConsumoPorEconomiaFinal().equals("")
					&& !helper.getConsumoPorEconomiaFinal().equals("--")) {

				hqlAux += "(coalesce(consumosHistorico.numeroConsumoFaturadoMes, 1) / "
						+ "coalesce(imovel.quantidadeEconomias, 1)) >= "
						+ helper.getConsumoPorEconomia() + " and ";

				hqlAux += "(coalesce(consumosHistorico.numeroConsumoFaturadoMes, 1) / "
						+ "coalesce(imovel.quantidadeEconomias, 1)) <= "
						+ helper.getConsumoPorEconomiaFinal() + " and ";

				finaliza = true;
			}

			// Situacao Ligacao Agua
			/*
			 * if (helper.getSituacaoLigacaoAgua() != null &&
			 * !helper.getSituacaoLigacaoAgua().equals("") ) {
			 * 
			 * Integer idSituacaoLigacao = new
			 * Integer(helper.getSituacaoLigacaoAgua());
			 * 
			 * if (idSituacaoLigacao.intValue() !=
			 * ConstantesSistema.NUMERO_NAO_INFORMADO){ hqlAux +=
			 * "imovel.ligacaoAguaSituacao = " + helper.getSituacaoLigacaoAgua()
			 * + " and "; finaliza = true; } }
			 */

			if (helper.getSituacaoLigacaoAgua() != null) {
				if (!helper.getSituacaoLigacaoAgua().equals("")) {
					String idsLigacaoAguaSituacao = "";
					for (int i = 0; i < helper.getSituacaoLigacaoAgua().length; i++) {
						idsLigacaoAguaSituacao += helper
								.getSituacaoLigacaoAgua()[i] + ",";
					}
					idsLigacaoAguaSituacao = idsLigacaoAguaSituacao.substring(
							0, (idsLigacaoAguaSituacao.length() - 1));
					hqlAux += "imovel.ligacaoAguaSituacao in("
							+ idsLigacaoAguaSituacao + ") and ";
					finaliza = true;
				}
			}

			// Finaliza o HQL
			if (finaliza) {
				hql += hqlAux;
				hql = hql.substring(0, hql.length() - 5);
			} else {
				hql = hql.substring(0, hql.length() - 7);
			}

			retorno = (Integer) session.createQuery(hql).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisar Servio Tipo Seletivo
	 * 
	 * Seleciona Servio Tipo Seletivo por codigo da constante
	 * 
	 * @author Hugo Amorim
	 * @date 09/10/2009
	 * 
	 */
	public Integer recuperaServicoTipoSeletivoPorConstante(
			Integer codigoConstante) throws ErroRepositorioException {

		Integer retorno;

		Collection retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select  svse.svtp_id as svtp"
					+ " from atendimentopublico.servico_tipo_seletiva svse"
					+ " where svse.svse_cdconstante = :codigoConstante ";

			retornoConsulta = session.createSQLQuery(consulta)
					.addScalar("svtp", Hibernate.INTEGER)
					.setInteger("codigoConstante", codigoConstante).list();

			retorno = (Integer) retornoConsulta.iterator().next();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	public Integer recuperaServicoTipoSeletivoPorId(
			Integer id) throws ErroRepositorioException {

		Integer retorno;

		Collection retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select  svse.svse_cdconstante as svtp"
					+ " from atendimentopublico.servico_tipo_seletiva svse"
					+ " where svse.svtp_id = :id ";

			retornoConsulta = session.createSQLQuery(consulta)
					.addScalar("svtp", Hibernate.INTEGER)
					.setInteger("id", id).list();
					

			retorno = (Integer) retornoConsulta.iterator().next();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0470] Acompanhar Roteiro de Programao de Ordens de Servio
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public UnidadeOrganizacional pesquisarUnidadeOrganizacionalSuperior(
			Integer idUnidadeOrganizacional) throws ErroRepositorioException {

		UnidadeOrganizacional retornoConsulta = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT unidOrg  "
					+ "FROM UnidadeOrganizacional unidOrg "
					// +
					// "left join fetch unidOrg.unidadeSuperior  unidadeSuperior "
					+ "WHERE unidOrg.id = " + idUnidadeOrganizacional + " ";

			retornoConsulta = (UnidadeOrganizacional) session
					.createQuery(consulta).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * Pesquisar Servio Tipo Seletivo
	 * 
	 * Seleciona Servio Tipo Seletivo por codigo da constante
	 * 
	 * @author Hugo Amorim
	 * @date 12/02/2009
	 * 
	 */
	public Integer recuperaServicoTipoPorConstante(Integer codigoConstante)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select st.svtp_id as svtp"
					+ " from atendimentopublico.servico_tipo st "
					+ " where st.svtp_nncodigoconstante = :codigoConstante ";

			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("svtp", Hibernate.INTEGER)
					.setInteger("codigoConstante", codigoConstante)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisa a quantidade Ordens em Processo de Repavimentao
	 * 
	 * [UC0639] Filtrar Ordens em Processo de Repavimeta��o.
	 * 
	 * @author Arthur Carvalho
	 * @date 22/02/2010
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarOrdemProcessoRepavimentacaoCount(
			OSPavimentoHelper oSPavimentoHelper)
					throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();
		try {
			consulta = "select count (ospv.id) "
					+ "from OrdemServicoPavimento ospv "
					+ "left join ospv.pavimentoRua prua "
					+ "left join ospv.pavimentoRuaRetorno pruaret "
					+ "left join ospv.ordemServico os "
					+ "left join os.registroAtendimento rgat "
					+ "left join rgat.localidade loca "
					+ "left join os.imovel imov  "
					+

					"left join rgat.logradouroBairro logradouroBairroRA "
					+ "left join logradouroBairroRA.logradouro logradouroRA "
					+ "left join logradouroBairroRA.bairro bairroRa "
					+

					"left join imov.logradouroBairro logradouroBairroImovel "
					+ "left join logradouroBairroImovel.logradouro logradouroImovel "
					+ "left join logradouroBairroImovel.bairro bairroImovel " +

					"left join ospv.motivoRejeicao mrej "
					+ "left join ospv.usuarioRejeicao usurej " + "where 1=1 "
					+ "and ospv.unidadeRepavimentadora = "
					+ oSPavimentoHelper.getIdUnidadeResponsavel();

			// Pendente -1-
			if (oSPavimentoHelper.getSituacaoRetorno().equals(1)) {
				consulta = consulta
						+ " and ospv.pavimentoRuaRetorno is null and ospv.areaPavimentoRuaRetorno is null"
						+ " and ospv.pavimentoCalcadaRetorno is null and ospv.areaPavimentoCalcadaRetorno is null "
						+ " and ospv.motivoRejeicao is null ";
			}

			// Conclu�da -2-
			if (oSPavimentoHelper.getSituacaoRetorno().equals(2)) {
				consulta = consulta
						+ " and ( (ospv.pavimentoRuaRetorno is not null and ospv.areaPavimentoRuaRetorno is not null) "
						+ " or ( ospv.pavimentoCalcadaRetorno is not null and ospv.areaPavimentoCalcadaRetorno is not null) ) "
						+ " and ospv.motivoRejeicao is null ";
			}

			// Rejeitadas -4-
			if (oSPavimentoHelper.getSituacaoRetorno().equals(4)) {
				consulta = consulta
						+ " and ( ospv.motivoRejeicao is not null ) ";
			}

			// Numero da OS
			if (oSPavimentoHelper.getNumeroOS() != null) {
				consulta = consulta + " and ( os.id = :numeroOS ) ";

				parameters.put("numeroOS", oSPavimentoHelper.getNumeroOS());
			}

			// Com observacao de retorno
			if (oSPavimentoHelper.getIndicadorOsObservacaoRetorno() != null
					&& oSPavimentoHelper.getIndicadorOsObservacaoRetorno()
					.shortValue() == ConstantesSistema.SIM.shortValue()) {

				consulta = consulta
						+ " and ospv.observacao is not null and cast(ospv.observacao as string) is not null ";
			}

			// data de refer�ncia
			if (oSPavimentoHelper.getPeriodoEncerramentoOsInicial() != null
					&& oSPavimentoHelper.getPeriodoEncerramentoOsFinal() != null) {

				consulta = consulta
						+ " and ospv.ordemServico.dataEncerramento between :dataInicial and :dataFinal ";

				parameters.put("dataInicial", Util.formatarDataInicial(Util
						.converteStringParaDate(oSPavimentoHelper
								.getPeriodoEncerramentoOsInicial())));
				parameters.put("dataFinal", Util.formatarDataFinal(Util
						.converteStringParaDate(oSPavimentoHelper
								.getPeriodoEncerramentoOsFinal())));

			} else if (oSPavimentoHelper.getPeriodoRetornoServicoInicial() != null
					&& oSPavimentoHelper.getPeriodoRetornoServicoFinal() != null) {

				consulta = consulta
						+ " and ospv.dataExecucao between :dataRetornoInicial and :dataRetornoFinal ";

				parameters.put("dataRetornoInicial", Util
						.formatarDataInicial(Util
								.converteStringParaDate(oSPavimentoHelper
										.getPeriodoRetornoServicoInicial())));
				parameters.put("dataRetornoFinal", Util.formatarDataFinal(Util
						.converteStringParaDate(oSPavimentoHelper
								.getPeriodoRetornoServicoFinal())));

			} else if (oSPavimentoHelper.getPeriodoRejeicaoInicial() != null
					&& oSPavimentoHelper.getPeriodoRejeicaoFinal() != null) {
				consulta = consulta
						+ " and ospv.dataRejeicao between :dataRejeicaoInicial and :dataRejeicaoFinal ";

				parameters.put("dataRejeicaoInicial", Util
						.formatarDataInicial(Util
								.converteStringParaDate(oSPavimentoHelper
										.getPeriodoRejeicaoInicial())));
				parameters.put("dataRejeicaoFinal", Util.formatarDataFinal(Util
						.converteStringParaDate(oSPavimentoHelper
								.getPeriodoRejeicaoFinal())));
			}

			if (oSPavimentoHelper.getIdMunicipio() != null) {
				consulta = consulta
						+ " and (bairroRa.municipio.id = :idMunicipio or bairroImovel.municipio.id = :idMunicipio) ";
				parameters.put("idMunicipio",
						oSPavimentoHelper.getIdMunicipio());
			}

			if (oSPavimentoHelper.getIdBairro() != null) {
				consulta = consulta
						+ " and (bairroRa.id = :idBairro or bairroImovel.id = :idBairro) ";
				parameters.put("idBairro", oSPavimentoHelper.getIdBairro());
			}

			if (oSPavimentoHelper.getIdLogradouro() != null) {
				consulta = consulta
						+ " and (logradouroRA.id = :idLogradouro or logradouroImovel.id = :idLogradouro) ";
				parameters.put("idLogradouro",
						oSPavimentoHelper.getIdLogradouro());
			}

			consulta = consulta + " and os.situacao = 2 "
					+ " and os.servicoTipo.indicadorPavimentoRua = 1 ";

			query = session.createQuery(consulta);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisa a quantidade de Ocorr�ncias Consecultivas da anormalidade
	 * 
	 * Parte do [UC0711] Filtro Para Gera��o de Ordens de Servio
	 * Hidr�metro.
	 * 
	 * @author Daniel Alves
	 * @date 22/03/2010
	 * 
	 * @param String
	 *            idAnormalidade, int qtdAnormalidades, int referencia
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection<Integer> pesquisarNumeroDeOcorrenciasConsecultivasAnormalidades(
			String idAnormalidades, String qtdAnormalidades,
			Integer referenciaFaturamento,
			ImovelEmissaoOrdensSeletivasHelper helper)
					throws ErroRepositorioException {

		Collection retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		try {
			String consulta = "SELECT idImovel FROM ("
					+ "select imovel.imov_id  as idImovel, mh.ltan_idleitanorminformada "
					+ "from micromedicao.medicao_historico mh "
					+ "inner join cadastro.imovel imovel on ((CASE WHEN mh.imov_id IS NOT NULL THEN mh.imov_id ELSE mh.lagu_id END) = imovel.imov_id) ";

			// se informou Logradouro
			if (helper.getLogradouro() != null
					&& !helper.getLogradouro().equals("")) {
				consulta += "	left  join cadastro.logradouro_cep logradouroCep on (imovel.lgcp_id = logradouroCep.lgcp_id) "
						+ "	left  join cadastro.logradouro logradouro on (logradouro.logr_id = logradouroCep.logr_id) ";
			}

			// localidade
			if (helper.getLocalidadeInicial() != null
					&& !helper.getLocalidadeInicial().equalsIgnoreCase("")
					&& helper.getLocalidadeFinal() != null
					&& !helper.getLocalidadeFinal().equalsIgnoreCase("")) {
				consulta = consulta
						+ "inner join cadastro.localidade localidade on (imovel.loca_id = localidade.loca_id) and (imovel.loca_id between "
						+ helper.getLocalidadeInicial() + " and "
						+ helper.getLocalidadeFinal() + ") ";
				// setor
				if (helper.getSetorComercialInicial() != null
						&& !helper.getSetorComercialInicial().equalsIgnoreCase(
								"")
								&& helper.getSetorComercialFinal() != null
								&& !helper.getSetorComercialFinal()
								.equalsIgnoreCase("")) {
					consulta = consulta
							+ "inner join cadastro.setor_comercial sc on (imovel.stcm_id = sc.stcm_id) and (sc.stcm_cdsetorcomercial between "
							+ helper.getCodigoSetorComercialInicial() + " and "
							+ helper.getCodigoSetorComercialFinal() + ") ";
					// Quadra
					if (helper.getQuadraInicial() != null
							&& !helper.getQuadraInicial().equalsIgnoreCase("")
							&& helper.getQuadraFinal() != null
							&& !helper.getQuadraFinal().equalsIgnoreCase("")) {
						consulta = consulta
								+ "inner join cadastro.quadra quadra on (imovel.qdra_id = quadra.qdra_id) and (quadra.qdra_nnquadra beetween "
								+ helper.getQuadraInicial() + " and "
								+ helper.getQuadraFinal() + ") ";
						// Rota
						if (helper.getRotaInicial() != null
								&& !helper.getRotaInicial()
								.equalsIgnoreCase("")
								&& helper.getRotaFinal() != null
								&& !helper.getRotaFinal().equalsIgnoreCase("")) {
							consulta = consulta
									+ "inner join micromedicao.rota rota on (imovel.stcm_id = rota.stcm_id) and (rota.rota_cdrota between "
									+ helper.getRotaInicial() + " and "
									+ helper.getRotaFinal() + ") ";
							// sequencia Rota
							if (helper.getRotaSequenciaInicial() != null
									&& !helper.getRotaSequenciaInicial()
									.equalsIgnoreCase("")
									&& helper.getRotaSequenciaFinal() != null
									&& !helper.getRotaSequenciaFinal()
									.equalsIgnoreCase("")) {
								consulta = consulta
										+ "and (rota.rota_nnsequencialeiturista between "
										+ helper.getRotaSequenciaInicial()
										+ " and "
										+ helper.getRotaSequenciaFinal() + ") ";
							}
						}
					}
				}
			} else {
				consulta += "	inner join cadastro.localidade on (localidade.loca_id = imovel.loca_id) ";
			}

			// Se unidade/gerencia ou elo
			if (helper.getGerenciaRegional() != null
					&& !helper.getGerenciaRegional().equals(
							ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				consulta += "	inner join cadastro.gerencia_regional gerenciaRegional on (gerenciaRegional.loca_id = localidade.loca_id) ";
			}

			if ((helper.getUnidadeNegocio() != null && !helper
					.getUnidadeNegocio().equals(
							ConstantesSistema.NUMERO_NAO_INFORMADO))
							|| (helper.getElo() != null & !helper.getElo().equals(""))) {
				consulta += "	inner join cadastro.unidade_negocio unidadeNegocio on (localidade.loca_id = unidadeNegocio.loca_id) ";
			}

			int numOcorrencias = (Integer.parseInt(qtdAnormalidades)) + 1;
			List<String> anoMesOcorrencias = new ArrayList<String>();
			for (int i = 1; i <= numOcorrencias; i++) {
				anoMesOcorrencias.add(Integer.toString(Util
						.subtrairMesDoAnoMes(referenciaFaturamento, i)));
			}

			consulta = consulta +
					// "where mh.mdhi_amleitura between "+referenciaAnterior+" and "+referencia+" ";
					"where mh.mdhi_amleitura in (:anoMesOcorrencias) ";

			// caso nao venha nenhuma anormalidade informada, seleciona todas as
			// anormalidades
			if (idAnormalidades != null
					&& !idAnormalidades.equalsIgnoreCase("")) {
				consulta = consulta + "and mh.ltan_idleitanormfatmt in ("
						+ idAnormalidades + ") ";
			}

			// Se informou o imovel
			if (helper.getIdImovel() != null
					&& !helper.getIdImovel().equals("")) {
				consulta = consulta + "and imovel.imov_id = "
						+ helper.getIdImovel() + " ";
			}

			// GERENCIA REGIONAL
			if (helper.getGerenciaRegional() != null
					&& !helper.getGerenciaRegional().equals(
							ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				consulta += "and gerenciaRegional.greg_id = "
						+ helper.getGerenciaRegional() + " ";
			}

			// UNIDADE NEGOCIO
			if (helper.getUnidadeNegocio() != null
					&& !helper.getUnidadeNegocio().equals(
							ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				consulta += "and unidadeNegocio.uneg_id = "
						+ helper.getUnidadeNegocio() + " ";
			}

			// Elo
			if (helper.getElo() != null & !helper.getElo().equals("")) {
				consulta += "and localidade.loca_cdelo = " + helper.getElo()
						+ " ";
			}

			// Logradouro
			if (helper.getLogradouro() != null
					& !helper.getLogradouro().equals("")) {
				consulta += "and logradouro.logr_id = "
						+ helper.getLogradouro() + " ";
			}

			consulta = consulta
					+ "group by imovel.imov_id, mh.ltan_idleitanorminformada "
					+
					// "having Count(imovel.imov_id) between "+qtdAnormalidades
					// +" and 10";
					"having Count(imovel.imov_id) > " + qtdAnormalidades
					+ " ) temp ";

			retorno = session.createSQLQuery(consulta)
					.addScalar("idImovel", Hibernate.INTEGER)
					.setParameterList("anoMesOcorrencias", anoMesOcorrencias)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [SB0006] - Obter Unidade Repavimentadora do Municpio
	 * 
	 * 1.1.1.1. Obter a unidade repavimentadora a partir do municpio do
	 * endere�o do CEP ou do endereco do bairro
	 * 
	 * @author Arthur Carvalho
	 * @date 12/02/2009
	 * 
	 */
	public UnidadeOrganizacional obterUnidadeRepavimentadorAPartirMunicipio(
			OrdemServico os, String tipoPesquisa)
					throws ErroRepositorioException {

		UnidadeOrganizacional retorno = new UnidadeOrganizacional();
		Object[] object = null;
		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select unidOrg.unid_id as id, "
					+ "unidOrg.unid_dsunidade as descricao "
					+ "from cadastro.unidade_organizacional unidOrg "
					+ "inner join cadastro.unidade_org_municipio undOrgMun on undOrgMun.unid_idrepavimentadora = unidOrg.unid_id "
					+ "inner join cadastro.logradouro log on log.muni_id = undOrgMun.muni_id ";

			if (tipoPesquisa.equals(ConstantesSistema.PESQUISA_PELO_CEP)) {
				consulta += "inner join cadastro.logradouro_cep logCep on logCep.logr_id = log.logr_id "
						+ "inner join atendimentopublico.registro_atendimento ra on ra.lgcp_id = logCep.lgcp_id "
						+ "inner join atendimentopublico.ordem_servico os on os.rgat_id = ra.rgat_id and os.rgat_id = "
						+ os.getRegistroAtendimento().getId();

			} else if (tipoPesquisa
					.equals(ConstantesSistema.PESQUISA_PELO_BAIRRO)) {
				consulta += "inner join cadastro.logradouro_bairro logBairro on logbairro.logr_id = log.logr_id "
						+ "inner join atendimentopublico.registro_atendimento ra on ra.lgbr_id = logBairro.lgbr_id "
						+ "inner join atendimentopublico.ordem_servico os on os.rgat_id = ra.rgat_id and os.rgat_id = "
						+ os.getRegistroAtendimento().getId();

			} else if (tipoPesquisa
					.equals(ConstantesSistema.PESQUISA_PELO_IMOVEL)) {
				consulta += "inner join cadastro.logradouro_bairro logBairro on logbairro.logr_id = log.logr_id "
						+ "inner join cadastro.imovel imov on imov.lgbr_id = logBairro.lgbr_id "
						+ "inner join atendimentopublico.ordem_servico os on os.imov_id = imov.imov_id and os.imov_id = "
						+ os.getImovel().getId();
			}

			consulta += " where unidOrg.unid_icuso = 1 "
					+ "	and undOrgMun.unmn_dtdesvinculacao is null ";

			object = (Object[]) session.createSQLQuery(consulta)
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("descricao", Hibernate.STRING).setMaxResults(1)
					.uniqueResult();

			if (object != null) {
				retorno.setId((Integer) object[0]);
				retorno.setDescricao((String) object[1]);
			} else {
				retorno = null;
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisa Ordens de Repavimentao em Processo de Aceite.
	 * 
	 * [UC1019] Filtrar Ordens de Repavimeta��o em Processo de Aceite.
	 * 
	 * @author Hugo Leonardo
	 * @date 17/05/2010
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemRepavimentacaoProcessoAceite(
			OrdemRepavimentacaoProcessoAceiteHelper oSPavimentoAceiteHelper,
			Integer numeroPagina) throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();
		try {
			consulta = "select ospv " + "from OrdemServicoPavimento ospv "
					+ "left join fetch ospv.pavimentoRua prua "
					+ "left join fetch ospv.pavimentoRuaRetorno pruaret "
					+ "left join fetch ospv.pavimentoCalcada pcal "
					+ "left join fetch ospv.pavimentoCalcadaRetorno pcalret "
					+ "left join fetch ospv.ordemServico os "
					+ "left join fetch os.registroAtendimento rgat "
					+ "left join fetch rgat.localidade loca "
					+ "left join fetch os.imovel imov  "
					+ "left join fetch ospv.motivoRejeicao mrej "
					+ "left join os.ordemServicoUnidades ordemUnidade "
					+ "where 1=1 "
					+ " and ordemUnidade.atendimentoRelacaoTipo = 3 "
					+ "and ospv.unidadeRepavimentadora = "
					+ oSPavimentoAceiteHelper.getIdUnidadeResponsavel();

			// Unidade Organizacional
			if (oSPavimentoAceiteHelper.getIdUnidadeOrganizacional() != null) {
				// consulta += " and ordemUnidade.atendimentoRelacaoTipo = 3 " +
				// " and ordemUnidade.unidadeOrganizacional = " +
				consulta += " and ordemUnidade.unidadeOrganizacional = "
						+ oSPavimentoAceiteHelper.getIdUnidadeOrganizacional()
						.toString();
			}
			// SEM ACEITE -1-
			if (oSPavimentoAceiteHelper.getSituacaoAceite().equals(1)) {
				consulta += " and (ospv.indicadorAceite is null ) ";
				// + " and ospv.motivoRejeicao is null ";
			}

			// ACEITAS -2-
			if (oSPavimentoAceiteHelper.getSituacaoAceite().equals(2)) {
				consulta += " and (ospv.indicadorAceite = 1 ) ";
				// + " and ospv.motivoRejeicao is null ";
			}

			// N�O ACEITAS -3-
			if (oSPavimentoAceiteHelper.getSituacaoAceite().equals(3)) {
				consulta += " and (ospv.indicadorAceite = 2 ) ";
				// + " and ospv.motivoRejeicao is null ";
			}

			// TODAS -4-
			if (oSPavimentoAceiteHelper.getSituacaoAceite().equals(4)) {

			}

			// Retorno do Servico
			if (oSPavimentoAceiteHelper.getPeriodoRetornoServicoInicial() != null
					&& oSPavimentoAceiteHelper.getPeriodoRetornoServicoFinal() != null) {

				consulta = consulta
						+ " and ospv.dataExecucao between :dataInicial and :dataFinal ";

				parameters.put("dataInicial", Util.formatarDataInicial(Util
						.converteStringParaDate(oSPavimentoAceiteHelper
								.getPeriodoRetornoServicoInicial())));
				parameters.put("dataFinal", Util.formatarDataFinal(Util
						.converteStringParaDate(oSPavimentoAceiteHelper
								.getPeriodoRetornoServicoFinal())));
			}

			// Aceite do Servico
			if (oSPavimentoAceiteHelper.getPeriodoAceiteServicoInicial() != null
					&& oSPavimentoAceiteHelper.getPeriodoAceiteServicoFinal() != null) {

				consulta = consulta
						+ " and ospv.dataAceite between :dataInicial2 and :dataFinal2 ";

				parameters.put("dataInicial2", Util.formatarDataInicial(Util
						.converteStringParaDate(oSPavimentoAceiteHelper
								.getPeriodoAceiteServicoInicial())));
				parameters.put("dataFinal2", Util.formatarDataFinal(Util
						.converteStringParaDate(oSPavimentoAceiteHelper
								.getPeriodoAceiteServicoFinal())));
			}

			consulta = consulta + " and os.situacao = 2 "
					+ " and os.servicoTipo.indicadorPavimentoRua = 1 "
					+ " and ospv.pavimentoRuaRetorno is not null "
					+ " order by ospv.dataExecucao ";

			query = session.createQuery(consulta);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			if (numeroPagina != null) {
				retorno = query.setFirstResult(10 * numeroPagina)
						.setMaxResults(10).list();
			} else {
				// Caso relatorio.
				retorno = query.list();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisa quantidade Ordens de Repavimentao em Processo de Aceite.
	 * 
	 * [UC1019] Filtrar Ordens de Repavimeta��o em Processo de Aceite.
	 * 
	 * @author Hugo Leonardo.
	 * @date 17/05/2010
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarOrdemProcessoRepavimentacaoAceiteCount(
			OrdemRepavimentacaoProcessoAceiteHelper oSPavimentoAceiteHelper)
					throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();
		try {
			consulta = "select count (ospv) "
					+ "from OrdemServicoPavimento ospv "
					+ "left join ospv.pavimentoRua prua "
					+ "left join ospv.pavimentoRuaRetorno pruaret "
					+ "left join ospv.ordemServico os "
					+ "left join os.registroAtendimento rgat "
					+ "left join rgat.localidade loca "
					+ "left join os.imovel imov  "
					+ "left join os.ordemServicoUnidades ordemUnidade "
					+ "left join ospv.motivoRejeicao mrej " + "where 1=1 "
					+ "and ordemUnidade.atendimentoRelacaoTipo = 3 "
					+ "and ospv.unidadeRepavimentadora = "
					+ oSPavimentoAceiteHelper.getIdUnidadeResponsavel();

			// Unidade Organizacional
			if (oSPavimentoAceiteHelper.getIdUnidadeOrganizacional() != null) {
				/*
				 * consulta += " and ordemUnidade.atendimentoRelacaoTipo = 3 " +
				 * " and ordemUnidade.unidadeOrganizacional = " +
				 * oSPavimentoAceiteHelper
				 * .getIdUnidadeOrganizacional().toString();
				 */

				consulta += " and ordemUnidade.unidadeOrganizacional = "
						+ oSPavimentoAceiteHelper.getIdUnidadeOrganizacional()
						.toString();
			}

			// SEM ACEITE -1-
			if (oSPavimentoAceiteHelper.getSituacaoAceite().equals(1)) {
				consulta = consulta + " and (ospv.indicadorAceite is null ) ";
			}

			// ACEITAS -2-
			if (oSPavimentoAceiteHelper.getSituacaoAceite().equals(2)) {
				consulta = consulta + " and (ospv.indicadorAceite = 1 ) ";
			}

			// N�O ACEITAS -3-
			if (oSPavimentoAceiteHelper.getSituacaoAceite().equals(3)) {
				consulta = consulta + " and (ospv.indicadorAceite = 2 ) ";
			}

			// TODAS -4-
			if (oSPavimentoAceiteHelper.getSituacaoAceite().equals(4)) {

			}

			// Retorno do Servico
			if (oSPavimentoAceiteHelper.getPeriodoRetornoServicoInicial() != null
					&& oSPavimentoAceiteHelper.getPeriodoRetornoServicoFinal() != null) {

				consulta = consulta
						+ " and ospv.dataExecucao between :dataInicial and :dataFinal ";

				parameters.put("dataInicial", Util.formatarDataInicial(Util
						.converteStringParaDate(oSPavimentoAceiteHelper
								.getPeriodoRetornoServicoInicial())));
				parameters.put("dataFinal", Util.formatarDataFinal(Util
						.converteStringParaDate(oSPavimentoAceiteHelper
								.getPeriodoRetornoServicoFinal())));
			}

			// Aceite do Servico
			if (oSPavimentoAceiteHelper.getPeriodoAceiteServicoInicial() != null
					&& oSPavimentoAceiteHelper.getPeriodoAceiteServicoFinal() != null) {

				consulta = consulta
						+ " and ospv.dataAceite between :dataInicial2 and :dataFinal2 ";

				parameters.put("dataInicial2", Util.formatarDataInicial(Util
						.converteStringParaDate(oSPavimentoAceiteHelper
								.getPeriodoAceiteServicoInicial())));
				parameters.put("dataFinal2", Util.formatarDataFinal(Util
						.converteStringParaDate(oSPavimentoAceiteHelper
								.getPeriodoAceiteServicoFinal())));
			}

			consulta = consulta + " and os.situacao = 2 "
					+ " and os.servicoTipo.indicadorPavimentoRua = 1 "
					+ " and ospv.pavimentoRuaRetorno is not null ";

			query = session.createQuery(consulta);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = (Integer) query.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalizao
	 * 
	 * Seleciona OSFS_DTFISCALIZACAOSITUACAO da tabela ORDEM_SERVICO_FISC_SIT
	 * para ORSE_ID=ORSE_ID da tabela ORDEM_SERVICO
	 * 
	 * @author Vivianne Sousa
	 * @date 28/07/2010
	 * 
	 */
	public Date recuperaDataFiscalizacaoSituacao(Integer idOrdemServico,
			Integer idFiscalizacaoSituacao) throws ErroRepositorioException {

		Date retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select osfs.dataFiscalizacaoSituacao "
					+ " from OrdemServicoFiscSit osfs "
					+ " where osfs.ordemServico.id = :idOrdemServico "
					+ " and osfs.fiscalizacaoSituacao.id = :idFiscalizacaoSituacao ";

			retorno = (Date) session
					.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setInteger("idFiscalizacaoSituacao",
							idFiscalizacaoSituacao).setMaxResults(1)
							.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalizao
	 * 
	 * @author Vivianne Sousa
	 * @date 29/07/2010
	 */
	public OrdemServicoFiscSit recuperaOrdemServicoFiscSit(
			Integer idOrdemServico, Integer idFiscalizacaoSituacao)
					throws ErroRepositorioException {

		OrdemServicoFiscSit retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select osfs "
					+ " from OrdemServicoFiscSit osfs "
					+ " where osfs.ordemServico.id = :idOrdemServico "
					+ " and osfs.fiscalizacaoSituacao.id = :idFiscalizacaoSituacao ";

			retorno = (OrdemServicoFiscSit) session
					.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setInteger("idFiscalizacaoSituacao",
							idFiscalizacaoSituacao).setMaxResults(1)
							.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalizao
	 * 
	 * @author Vivianne Sousa
	 * @date 30/07/2010
	 */
	public OrdemServico recuperaOrdemServico(Integer idOrdemServico)
			throws ErroRepositorioException {

		OrdemServico retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select orse " + " from OrdemServico orse "
					+ " where orse.id = :idOrdemServico ";

			retorno = (OrdemServico) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalizao
	 * 
	 * @author Vivianne Sousa
	 * @date 02/08/2010
	 */
	public Collection recuperaFiscalizacaoSituacao(Integer idOrdemServico)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select fzst " + " from FiscalizacaoSituacao fzst "
					+ " where fzst.id not in( "
					+ " 	select osfs.fiscalizacaoSituacao.id "
					+ " 	from OrdemServicoFiscSit osfs "
					+ " 	where osfs.ordemServico.id = :idOrdemServico )"
					+ " order by fzst.descricaoFiscalizacaoSituacao ";

			retorno = (Collection) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalizao
	 * 
	 * @author Vivianne Sousa
	 * @date 03/08/2010
	 */
	public Collection recuperaFiscalizacaoSituacaoSelecionada(
			Integer idOrdemServico) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select fzst " + " from FiscalizacaoSituacao fzst "
					+ " where fzst.id in( "
					+ " 	select osfs.fiscalizacaoSituacao.id "
					+ " 	from OrdemServicoFiscSit osfs "
					+ " 	where osfs.ordemServico.id = :idOrdemServico )"
					+ " order by fzst.descricaoFiscalizacaoSituacao ";

			retorno = (Collection) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalizao
	 * 
	 * @author Vivianne Sousa
	 * @date 09/08/2010
	 */
	public Collection recuperaOrdemServicoFiscSit(Integer idOrdemServico)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select osfs " + " from OrdemServicoFiscSit osfs "
					+ " where osfs.ordemServico.id = :idOrdemServico ";

			retorno = (Collection) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalizao [SB0003] �
	 * Calcular/Inserir Valor
	 * 
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorDebitoOS(Integer indicadorDebito,
			Integer idFiscalizacaoSituacao, Integer idOS)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			String atualizarOS = "";

			atualizarOS = "update gcom.atendimentopublico.ordemservico.OrdemServicoFiscSit "
					+ "set osfs_tmultimaalteracao = :ultimaAlteracao, "
					+ "osfs_icdebito = :indicadorDebito "
					+ "where orse_id = :idOS "
					+ "and fzst_id = :idFiscalizacaoSituacao ";

			session.createQuery(atualizarOS)
			.setInteger("indicadorDebito", indicadorDebito)
			.setInteger("idFiscalizacaoSituacao",
					idFiscalizacaoSituacao).setInteger("idOS", idOS)
					.setTimestamp("ultimaAlteracao", new Date())
					.executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalizao [SB0001] � Atualizar
	 * Ordem de Servio.
	 * 
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public void excluirSituacaoFiscalizacaoPorOS(Integer idOrdemServico)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			String sql = "DELETE FROM gcom.atendimentopublico.ordemservico.OrdemServicoFiscSit "
					+ "WHERE orse_id = :idOrdemServico";

			session.createQuery(sql)
			.setInteger("idOrdemServico", idOrdemServico)
			.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalizao [SB0001] � Atualizar
	 * Ordem de Servio.
	 * 
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public void excluirCobrancaDocumentoFiscPorOS(Integer idOrdemServico)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			String sql = "DELETE FROM gcom.cobranca.CobrancaDocumentoFisc "
					+ "WHERE orse_id = :idOrdemServico";

			session.createQuery(sql)
			.setInteger("idOrdemServico", idOrdemServico)
			.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalizao
	 * 
	 * @author Vivianne Sousa
	 * @date 19/08/2010
	 */
	public Short recuperaIndicadorDebitoDaOrdemServicoFiscSit(
			Integer idOrdemServico, Integer idFiscalizacaoSituacao)
					throws ErroRepositorioException {

		Short retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select osfs.indicadorDebito "
					+ " from OrdemServicoFiscSit osfs "
					+ " where osfs.ordemServico.id = :idOrdemServico "
					+ " and osfs.fiscalizacaoSituacao.id = :idFiscalizacaoSituacao ";

			retorno = (Short) session
					.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setInteger("idFiscalizacaoSituacao",
							idFiscalizacaoSituacao).setMaxResults(1)
							.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * @author Vivianne Sousa
	 * @date 30/08/2010
	 * 
	 */
	public OrdemServicoFiscSit recuperaOrdemServicoFiscSitComMenorDataFiscalizacao(
			Integer idOrdemServico) throws ErroRepositorioException {

		OrdemServicoFiscSit retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select osfs " + " from OrdemServicoFiscSit osfs "
					+ " where osfs.ordemServico.id = :idOrdemServico "
					+ " order by osfs.dataFiscalizacaoSituacao";

			retorno = (OrdemServicoFiscSit) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0441] Consultar Dados da Ordem de Servio
	 * 
	 * @author Vivianne Sousa
	 * @date 01/09/2010
	 */
	public Collection pesquisaOrdemServicoFiscSit(Integer idOrdemServico)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select osfs " + " from OrdemServicoFiscSit osfs "
					+ " inner join fetch osfs.fiscalizacaoSituacao fzst "
					+ " where osfs.ordemServico.id = :idOrdemServico "
					+ " order by osfs.dataFiscalizacaoSituacao desc ";

			retorno = (Collection) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0639] Filtrar Ordens em Processo de Repavimeta��o.
	 * 
	 * @author Hugo Leonardo
	 * @date 03/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioBoletimCustoPavimento(
			FiltrarBoletimCustoPavimentoHelper relatorioHelper)
					throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select ospv "
					+ " from OrdemServicoPavimento ospv "
					+ " left join fetch ospv.motivoRejeicao mrej "
					+ " left join fetch ospv.pavimentoRua prua "
					+ " left join fetch ospv.pavimentoRuaRetorno pruaret "
					+ " left join fetch ospv.ordemServico os "
					+ " where 1=1 "
					+ " and ospv.unidadeRepavimentadora = "
					+ relatorioHelper.getIdUnidadeRepavimentadora()
					+ " and ((os.dataEncerramento between :dataInicial and :dataFinal) "
					+ " or ( "
					+ " 	os.dataEncerramento >= :dtmenos3Meses  "
					+ " 	and os.dataEncerramento < :dataInicial "
					+ " 	and ospv.motivoRejeicao.id is null  "
					+ " 	and ( "
					+ " 		ospv.pavimentoRuaRetorno.id is null "
					+ " 	    or ( "
					+ " 		   ospv.pavimentoRuaRetorno.id is not null and "
					+ " 		   ospv.indicadorAceite is null "
					+ " 		   ) "
					+ " 		) "
					+ " 	)  "
					+ " or ( "
					+ " 	ospv.indicadorAceite = 1 "
					+ " 	and ospv.dataAceite between :dataInicial and :dataFinal "
					+ " 	) " + "  ) ";

			Date dtmenos3Meses = Util.adcionarOuSubtrairMesesAData(Util
					.gerarDataInicialApartirAnoMesRefencia(new Integer(
							relatorioHelper.getMesAnoReferenciaGeracao())), -3,
							new Integer(relatorioHelper.getMesAnoReferenciaGeracao())
			.intValue());

			Date dtInicio = Util
					.gerarDataInicialApartirAnoMesRefencia(new Integer(
							relatorioHelper.getMesAnoReferenciaGeracao()));

			Date dtFim = Util.gerarDataApartirAnoMesRefencia(new Integer(
					relatorioHelper.getMesAnoReferenciaGeracao()));

			parameters.put("dataInicial", dtInicio);

			parameters.put("dtmenos3Meses", dtmenos3Meses);

			parameters.put("dataFinal", dtFim);

			consulta += " order by os.id, prua.id, os.dataEncerramento ";

			query = session.createQuery(consulta);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1109] Filtrar Dados para Gera��o Boletim de Custo de
	 * Repavimentao
	 * 
	 * @author Hugo Leonardo
	 * @date 03/01/2011
	 * 
	 * @return boolean
	 */
	public boolean verificaDadosGeracaoBoletimCustoRepavimentacao(
			FiltrarBoletimCustoPavimentoHelper relatorioHelper)
					throws ErroRepositorioException {

		// Cria uma sesso com o hibernate
		Session session = HibernateUtil.getSession();

		// Retorno Consulta
		boolean retorno = false;

		// Cria a vari�vel que vai conter o hql
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " select ospv.id "
					+ " from OrdemServicoPavimento ospv "
					+ " left join fetch ospv.motivoRejeicao mrej "
					+ " left join fetch ospv.pavimentoRua prua "
					+ " left join fetch ospv.pavimentoRuaRetorno pruaret "
					+ " left join fetch ospv.ordemServico os "
					+ " where 1=1 "
					+ " and ospv.unidadeRepavimentadora = "
					+ relatorioHelper.getIdUnidadeRepavimentadora()
					+ " and ((os.dataEncerramento between :dataInicial and :dataFinal) "
					+ " or (( "
					+ " 	   os.dataEncerramento >= :dtmenos3Meses  "
					+ " 	   and os.dataEncerramento < :dataInicial "
					+ " 	   and ospv.motivoRejeicao.id is null  "
					+ " 	   and ( "
					+ " 			ospv.pavimentoRuaRetorno.id is null "
					+ " 	        or ( "
					+ " 				ospv.pavimentoRuaRetorno.id is not null and "
					+ " 				ospv.indicadorAceite is null "
					+ " 				) "
					+ " 		   ) "
					+ " 	  )  "
					+ " 	  or ( "
					+ " 		  ospv.indicadorAceite = 1 "
					+ " 		  and ospv.dataAceite between :dataInicial and :dataFinal "
					+ " 		 ) " + "  )) ";

			Date dtmenos3Meses = Util.adcionarOuSubtrairMesesAData(Util
					.gerarDataInicialApartirAnoMesRefencia(new Integer(
							relatorioHelper.getMesAnoReferenciaGeracao())), -3,
							new Integer(relatorioHelper.getMesAnoReferenciaGeracao())
			.intValue());

			Date dtInicio = Util
					.gerarDataInicialApartirAnoMesRefencia(new Integer(
							relatorioHelper.getMesAnoReferenciaGeracao()));

			Date dtFim = Util.gerarDataApartirAnoMesRefencia(new Integer(
					relatorioHelper.getMesAnoReferenciaGeracao()));

			parameters.put("dataInicial", dtInicio);

			parameters.put("dtmenos3Meses", dtmenos3Meses);

			parameters.put("dataFinal", dtFim);

			query = session.createQuery(consulta);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			Collection collRetorno = (Collection) query.list();

			if (collRetorno == null || collRetorno.isEmpty()) {
				retorno = true;
			}

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceo para a prxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sesso com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * [UC1110] Gerar Boletim de Custo de Repavimentao por Tipo de Pavimento
	 * 
	 * @author Hugo Leonardo
	 * @date 04/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarBoletimCustoPavimentoPorTipoPavimentoRua(
			FiltrarBoletimCustoPavimentoHelper relatorioHelper)
					throws ErroRepositorioException {

		List retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " select prua.id, prua.descricao "
					+ " from OrdemServicoPavimento ospv "
					+ " left join ospv.pavimentoRua prua "
					+ " left join ospv.ordemServico os "
					+ " where 1=1 "
					+ " and ospv.unidadeRepavimentadora = "
					+ relatorioHelper.getIdUnidadeRepavimentadora()
					+ " and ((os.dataEncerramento between :dataInicial and :dataFinal) "
					+ " or (( "
					+ " 	   os.dataEncerramento >= :dtmenos3Meses  "
					+ " 	   and os.dataEncerramento < :dataInicial "
					+ " 	   and ospv.motivoRejeicao.id is null  "
					+ " 	   and ( "
					+ " 			ospv.pavimentoRuaRetorno.id is null "
					+ " 	        or ( "
					+ " 				ospv.pavimentoRuaRetorno.id is not null and "
					+ " 				ospv.indicadorAceite is null "
					+ " 				) "
					+ " 		   ) "
					+ " 	  )  "
					+ " 	  or ( "
					+ " 		  ospv.indicadorAceite = 1 "
					+ " 		  and ospv.dataAceite between :dataInicial and :dataFinal "
					+ " 		 ) " + "  )) ";

			Date dtmenos3Meses = Util.adcionarOuSubtrairMesesAData(Util
					.gerarDataInicialApartirAnoMesRefencia(new Integer(
							relatorioHelper.getMesAnoReferenciaGeracao())), -3,
							new Integer(relatorioHelper.getMesAnoReferenciaGeracao())
			.intValue());

			Date dtInicio = Util
					.gerarDataInicialApartirAnoMesRefencia(new Integer(
							relatorioHelper.getMesAnoReferenciaGeracao()));

			Date dtFim = Util.gerarDataApartirAnoMesRefencia(new Integer(
					relatorioHelper.getMesAnoReferenciaGeracao()));

			parameters.put("dataInicial", dtInicio);

			parameters.put("dtmenos3Meses", dtmenos3Meses);

			parameters.put("dataFinal", dtFim);

			consulta += " group by prua.id, prua.descricao "
					+ " order by prua.id ";

			query = session.createQuery(consulta);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			Collection coll = (Collection) query.list();

			if (coll != null) {
				Iterator it = coll.iterator();
				while (it.hasNext()) {
					Object[] obj = (Object[]) it.next();

					OSTipoPavimentoHelper osTipoPavimento = new OSTipoPavimentoHelper();
					osTipoPavimento.setId((Integer) obj[0]);
					osTipoPavimento.setDescricao((String) obj[1]);

					retorno.add(osTipoPavimento);
				}
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * [UC1110] Gerar Boletim de Custo de Repavimentao por Tipo de Pavimento
	 * 
	 * @author Hugo Leonardo
	 * @date 04/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarBoletimCustoPavimentoPorTipoPavimentoRuaRet(
			FiltrarBoletimCustoPavimentoHelper relatorioHelper)
					throws ErroRepositorioException {

		List retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " select pruaret.descricao, "
					+ " sum(ospv.areaPavimentoRuaRetorno) as somatorioArea "
					+ " from OrdemServicoPavimento ospv "
					+ " left join ospv.pavimentoRuaRetorno pruaret "
					+ " left join ospv.ordemServico os "
					+ " where 1=1 "
					+ " and ospv.unidadeRepavimentadora = "
					+ relatorioHelper.getIdUnidadeRepavimentadora()
					+ " and ((os.dataEncerramento between :dataInicial and :dataFinal) "
					+ " or (( "
					+ " 	   os.dataEncerramento >= :dtmenos3Meses  "
					+ " 	   and os.dataEncerramento < :dataInicial "
					+ " 	   and ospv.motivoRejeicao.id is null  "
					+ " 	   and ( "
					+ " 			ospv.pavimentoRuaRetorno.id is null "
					+ " 	        or ( "
					+ " 				ospv.pavimentoRuaRetorno.id is not null and "
					+ " 				ospv.indicadorAceite is null "
					+ " 				) "
					+ " 		   ) "
					+ " 	  )  "
					+ " 	  or ( "
					+ " 		  ospv.indicadorAceite = 1 "
					+ " 		  and ospv.dataAceite between :dataInicial and :dataFinal "
					+ " 		 ) " + "  )) ";

			Date dtmenos3Meses = Util.adcionarOuSubtrairMesesAData(Util
					.gerarDataInicialApartirAnoMesRefencia(new Integer(
							relatorioHelper.getMesAnoReferenciaGeracao())), -3,
							new Integer(relatorioHelper.getMesAnoReferenciaGeracao())
			.intValue());

			Date dtInicio = Util
					.gerarDataInicialApartirAnoMesRefencia(new Integer(
							relatorioHelper.getMesAnoReferenciaGeracao()));

			Date dtFim = Util.gerarDataApartirAnoMesRefencia(new Integer(
					relatorioHelper.getMesAnoReferenciaGeracao()));

			parameters.put("dataInicial", dtInicio);

			parameters.put("dtmenos3Meses", dtmenos3Meses);

			parameters.put("dataFinal", dtFim);

			consulta += " group by pruaret.descricao "
					+ " order by pruaret.descricao ";

			query = session.createQuery(consulta);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			Collection coll = (Collection) query.list();

			if (coll != null) {
				Iterator it = coll.iterator();
				while (it.hasNext()) {
					Object[] obj = (Object[]) it.next();

					if (obj[0] != null && obj[1] != null) {

						OSTipoPavimentoHelper osTipoPavimento = new OSTipoPavimentoHelper();

						osTipoPavimento.setDescricao((String) obj[0]);
						osTipoPavimento.setSomatorioArea((BigDecimal) obj[1]);

						retorno.add(osTipoPavimento);
					}

				}
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * [UC1110] Gerar Boletim de Custo de Repavimentao por Tipo de Pavimento
	 * 
	 * @author Hugo Leonardo
	 * @date 10/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTotaisPorTipoPavimentoRuaDemandadas(
			FiltrarBoletimCustoPavimentoHelper relatorioHelper)
					throws ErroRepositorioException {

		List retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " SELECT case when (osPavimento.pavimentoRua.id is not null) then 1 else 0 end, "
					+ " osPavimento.pavimentoRua.id, "
					+ " 	sum(osPavimento.areaPavimentoRua), "
					+ " 	sum(osPavimento.areaPavimentoRua*custo.valorPavimento) "
					+ " FROM OrdemServicoPavimento osPavimento, "
					+ " 	OrdemServico os, "
					+ " 	UnidadeRepavimentadoraCustoPavimentoRua custo "
					+ " WHERE os.id 								= osPavimento.ordemServico.id "
					+ " AND custo.unidadeRepavimentadora.id    	= osPavimento.unidadeRepavimentadora.id "
					+ " AND custo.pavimentoRua.id              	= osPavimento.pavimentoRua.id "
					+ " AND custo.dataVigenciaInicial    			<= os.dataEncerramento "
					+ " AND coalesce(custo.dataVigenciaFinal, to_date('9999-12-31','YYYY/MM/DD')) >= os.dataEncerramento "
					+ " AND osPavimento.unidadeRepavimentadora.id  = :unidade "
					+ " AND os.dataEncerramento between :dataInicial and :dataFinal "
					+ " GROUP BY osPavimento.pavimentoRua.id ";

			Date dtInicio = Util
					.gerarDataInicialApartirAnoMesRefencia(new Integer(
							relatorioHelper.getMesAnoReferenciaGeracao()));

			Date dtFim = Util.gerarDataApartirAnoMesRefencia(new Integer(
					relatorioHelper.getMesAnoReferenciaGeracao()));

			parameters.put("dataInicial", dtInicio);

			parameters.put("dataFinal", dtFim);

			parameters.put("unidade",
					relatorioHelper.getIdUnidadeRepavimentadora());

			query = session.createQuery(consulta);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * [UC1110] Gerar Boletim de Custo de Repavimentao por Tipo de Pavimento
	 * 
	 * @author Hugo Leonardo
	 * @date 10/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTotaisPorTipoPavimentoRuaDemandadas3Meses(
			FiltrarBoletimCustoPavimentoHelper relatorioHelper)
					throws ErroRepositorioException {

		List retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " SELECT case when (osPavimento.pavimentoRua.id is not null) then 2 else 0 end, "
					+ " osPavimento.pavimentoRua.id, "
					+ " 	sum(osPavimento.areaPavimentoRua), "
					+ " 	sum(osPavimento.areaPavimentoRua*custo.valorPavimento) "
					+ " FROM OrdemServicoPavimento osPavimento, "
					+ " 	OrdemServico os, "
					+ " 	UnidadeRepavimentadoraCustoPavimentoRua custo "
					+ " WHERE os.id 								= osPavimento.ordemServico.id "
					+ " AND custo.unidadeRepavimentadora.id    	= osPavimento.unidadeRepavimentadora.id "
					+ " AND custo.pavimentoRua.id              	= osPavimento.pavimentoRua.id "
					+ " AND custo.dataVigenciaInicial    			<= os.dataEncerramento "
					+ " AND coalesce(custo.dataVigenciaFinal, to_date('9999-12-31','YYYY/MM/DD')) >= os.dataEncerramento "
					+ " AND osPavimento.unidadeRepavimentadora.id  = :unidade "
					+ " AND os.dataEncerramento >= :dtmenos3Meses "
					+ " AND os.dataEncerramento < :dataInicial "
					+ " AND osPavimento.motivoRejeicao.id is null "
					+ " AND ( osPavimento.pavimentoRuaRetorno is null "
					+ " 	OR ( osPavimento.pavimentoRuaRetorno is not null "
					+ " 		AND osPavimento.indicadorAceite is null ))"
					+ " GROUP BY osPavimento.pavimentoRua.id ";

			Date dtInicio = Util
					.gerarDataInicialApartirAnoMesRefencia(new Integer(
							relatorioHelper.getMesAnoReferenciaGeracao()));

			Date dtmenos3Meses = Util.adcionarOuSubtrairMesesAData(Util
					.gerarDataInicialApartirAnoMesRefencia(new Integer(
							relatorioHelper.getMesAnoReferenciaGeracao())), -3,
							new Integer(relatorioHelper.getMesAnoReferenciaGeracao())
			.intValue());

			parameters.put("dataInicial", dtInicio);

			parameters.put("dtmenos3Meses", dtmenos3Meses);

			parameters.put("unidade",
					relatorioHelper.getIdUnidadeRepavimentadora());

			query = session.createQuery(consulta);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * [UC1110] Gerar Boletim de Custo de Repavimentao por Tipo de Pavimento
	 * 
	 * @author Hugo Leonardo
	 * @date 10/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTotaisPorTipoPavimentoRuaAceitas(
			FiltrarBoletimCustoPavimentoHelper relatorioHelper)
					throws ErroRepositorioException {

		List retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " SELECT case when (osPavimento.pavimentoRuaRetorno.id is not null) then 3 else 0 end, "
					+ " osPavimento.pavimentoRuaRetorno.id, "
					+ " 	sum(osPavimento.areaPavimentoRuaRetorno), "
					+ " 	sum(osPavimento.areaPavimentoRuaRetorno*custo.valorPavimento) "
					+ " FROM OrdemServicoPavimento osPavimento, "
					+ " 	OrdemServico os, "
					+ " 	UnidadeRepavimentadoraCustoPavimentoRua custo "
					+ " WHERE os.id 								= osPavimento.ordemServico.id "
					+ " AND custo.unidadeRepavimentadora.id    	= osPavimento.unidadeRepavimentadora.id "
					+ " AND custo.pavimentoRua.id              	= osPavimento.pavimentoRuaRetorno.id "
					+ " AND custo.dataVigenciaInicial    			<= os.dataEncerramento "
					+ " AND coalesce(custo.dataVigenciaFinal, to_date('9999-12-31','YYYY/MM/DD')) >= os.dataEncerramento "
					+ " AND osPavimento.unidadeRepavimentadora.id  = :unidade "
					+ " AND osPavimento.dataAceite between :dataInicial and :dataFinal "
					+ " AND osPavimento.indicadorAceite = 1 "
					+ " GROUP BY osPavimento.pavimentoRuaRetorno.id ";

			Date dtInicio = Util
					.gerarDataInicialApartirAnoMesRefencia(new Integer(
							relatorioHelper.getMesAnoReferenciaGeracao()));

			Date dtFim = Util.gerarDataApartirAnoMesRefencia(new Integer(
					relatorioHelper.getMesAnoReferenciaGeracao()));

			parameters.put("dataInicial", dtInicio);

			parameters.put("dataFinal", dtFim);

			parameters.put("unidade",
					relatorioHelper.getIdUnidadeRepavimentadora());

			query = session.createQuery(consulta);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0457] Encerrar Ordem de Servio
	 * 
	 * @author Vivianne Sousa
	 * @date 18/01/2011
	 */
	public ServicoTipo recuperaServicoTipoDaOrdemServico(Integer idOrdemServico)
			throws ErroRepositorioException {

		ServicoTipo retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select svtp " + " from OrdemServico orse "
					+ " inner join orse.servicoTipo svtp "
					+ " where orse.id = :idOrdemServico ";

			retorno = (ServicoTipo) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0457] Encerrar Ordem de Servio
	 * 
	 * @author Vivianne Sousa
	 * @date 21/01/2011
	 */
	public ServicoTipoBoletim recuperaServicoTipoBoletimDoServicoTipo(
			Integer idServicoTipo) throws ErroRepositorioException {

		ServicoTipoBoletim retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select svbo " + " from ServicoTipoBoletim svbo "
					+ " inner join svbo.servicoTipo svtp "
					+ " where svtp.id = :idServicoTipo ";

			retorno = (ServicoTipoBoletim) session.createQuery(consulta)
					.setInteger("idServicoTipo", idServicoTipo)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1116] Atualizar Informaes da OS para Boletim de Medio
	 * 
	 * @author Vivianne Sousa
	 * @date 02/02/2011
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public OrdemServico recuperaOSEDadosImovel(Integer idOS)
			throws ErroRepositorioException {

		OrdemServico ordemServico = null;

		Object[] retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT os.id, " // 0
					+ "imovel.id,"// 1
					+ "imovel.lote,"// 2
					+ "imovel.subLote,"// 3
					+ "local.id,"// 4
					+ "setor.codigo,"// 5
					+ "quadra.numeroQuadra,"// 6
					+ "ligAguaSitu.descricao,"// 7
					+ "ligEsgotoSitu.descricao,"// 8
					+ "os.servicoTipo.descricao, " // 9
					+ "os.servicoTipo.indicadorBoletim, " // 10
					+ "os.servicoTipo.id, " // 11
					+ "os.situacao "// 12

					+ "FROM OrdemServico os "
					// Dados do Im�vel da OS
					+ "LEFT JOIN os.imovel imovel "
					+ "LEFT JOIN imovel.localidade local "
					+ "LEFT JOIN imovel.setorComercial setor  "
					+ "LEFT JOIN imovel.quadra quadra  "
					+ "LEFT JOIN imovel.ligacaoAguaSituacao ligAguaSitu  "
					+ "LEFT JOIN imovel.ligacaoEsgotoSituacao ligEsgotoSitu  "

					+ "WHERE os.id = :idOS ";

			retornoConsulta = (Object[]) session.createQuery(consulta)
					.setInteger("idOS", idOS).setMaxResults(1).uniqueResult();

			if (retornoConsulta != null) {

				ordemServico = new OrdemServico();
				ordemServico.setId((Integer) retornoConsulta[0]);

				if (retornoConsulta[1] != null) {

					Imovel imovel = new Imovel();
					imovel.setId((Integer) retornoConsulta[1]);
					imovel.setLote((Short) retornoConsulta[2]);
					imovel.setSubLote((Short) retornoConsulta[3]);

					Localidade localidade = new Localidade();
					localidade.setId((Integer) retornoConsulta[4]);
					imovel.setLocalidade(localidade);

					SetorComercial setor = new SetorComercial();
					setor.setCodigo((Integer) retornoConsulta[5]);
					imovel.setSetorComercial(setor);

					Quadra quadra = new Quadra();
					quadra.setNumeroQuadra((Integer) retornoConsulta[6]);
					imovel.setQuadra(quadra);

					LigacaoAguaSituacao last = new LigacaoAguaSituacao();
					last.setDescricao((String) retornoConsulta[7]);
					imovel.setLigacaoAguaSituacao(last);

					LigacaoEsgotoSituacao lest = new LigacaoEsgotoSituacao();
					lest.setDescricao((String) retornoConsulta[8]);
					imovel.setLigacaoEsgotoSituacao(lest);
					ordemServico.setImovel(imovel);

					ServicoTipo servicoTipo = new ServicoTipo();
					servicoTipo.setDescricao((String) retornoConsulta[9]);
					servicoTipo
					.setIndicadorBoletim((Short) retornoConsulta[10]);
					servicoTipo.setId((Integer) retornoConsulta[11]);
					ordemServico.setServicoTipo(servicoTipo);

					ordemServico.setSituacao((Short) retornoConsulta[12]);
				}

			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return ordemServico;
	}

	/**
	 * [UC1116] Atualizar Informaes da OS para Boletim de Medio
	 * 
	 * @author Vivianne Sousa
	 * @date 02/02/2011
	 */
	public OrdemServicoBoletim recuperaOrdemServicoBoletimDaOS(
			Integer idOrdemServico) throws ErroRepositorioException {

		OrdemServicoBoletim retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select orbo " + " from OrdemServicoBoletim orbo "
					+ " where orbo.ordemServico.id = :idOrdemServico ";

			retorno = (OrdemServicoBoletim) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1163] Gerar Relat�rio de OS executadas por Prestadora de Servio
	 * 
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	public Collection recuperaOSExecutadas(Date dataInicial, Date dataFinal,
			Integer idGerencia, Integer idUnidade, Integer idLocalidade,
			String[] idsRegiao, String[] idsMicroregiao, String[] idsMunicipio)
					throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String consultaParte1 = "SELECT osdg.osdg_cdordemservico as numeroOS, "
					+ // 0
					"svtp.svps_cdservico as codigoServico, "
					+ // 1
					"svtp.svps_dsservico as descServico, "
					+ // 2
					"osdg.osdg_dstipopavimento as descTipoPavimento, "
					+ // 3
					"osdg.osdg_dsmaterialrede as materialrede, "
					+ // 4
					"osdg.osdg_diametrorede as diametroRede, "
					+ // 5
					"orse.orse_tmencerramento as dataConclusao, "
					+ // 6
					"osdg.osdg_cdservicoexcedente as codigoExcedente, "
					+ // 7
					// "mtps.mtps_cdmaterial as codigoMaterial, " +
					// "mtps.mtps_dsmaterial as descMaterial, " +
					"svtpexc.svps_dsservico as descExcedente, "
					+ // 8
					// "osdg.osdg_qtmaterialexcedente as qtdeExcedente, " +
					"osdg.osdg_profundidaderede as profundRede, "
					+ // 9
					"osdg.osdg_dimensaoburaco as dimenBuraco,   "
					+ // 10
					"loca.loca_id as idLocalidade, "
					+ // 11
					"loca.loca_nmlocalidade as nomeLocalidade,"
					+ // 12
					"rgat.rgat_id as idRA, "
					+ // 13
					"uneg.uneg_nmunidadenegocio as nomeUnidade, "
					+ // 14
					"greg.greg_nmregional as nomeGerencia, "
					+ // 15
					"osdg.osdg_qtmaterialexcedente as qtdeExcedente "
					+ // 16
					"FROM INTEGRACAO.os_dados_gerais osdg "
					+ "INNER JOIN ATENDIMENTOPUBLICO.ordem_servico orse on osdg.osdg_cdordemservico = orse.orse_id "
					+
					// "LEFT JOIN INTEGRACAO.material_prestador_serv mtps on osdg.mtps_cdmaterialutilizado = mtps.mtps_cdmaterial "
					// +
					"LEFT JOIN INTEGRACAO.servico_prestador_serv svtp on osdg.svps_cdservico = svtp.svps_cdservico "
					+ "LEFT JOIN INTEGRACAO.servico_prestador_serv svtpExc on osdg.osdg_cdservicoexcedente = svtpExc.svps_cdservico "
					+ "INNER JOIN ATENDIMENTOPUBLICO.registro_atendimento rgat on orse.rgat_id = rgat.rgat_id "
					+ "INNER JOIN CADASTRO.localidade loca on rgat.loca_id = loca.loca_id "
					+ "INNER JOIN cadastro.gerencia_regional greg on greg.greg_id = loca.greg_id "
					+ "INNER JOIN cadastro.unidade_negocio uneg on uneg.uneg_id = loca.uneg_id ";

			String data1 = Util.recuperaDataInvertida(dataInicial);
			if (data1 != null && !data1.equals("")
					&& data1.trim().length() == 8) {
				data1 = data1.substring(0, 4) + "-" + data1.substring(4, 6)
						+ "-" + data1.substring(6, 8);
			}

			String data2 = Util.recuperaDataInvertida(dataFinal);
			if (data2 != null && !data2.equals("")
					&& data2.trim().length() == 8) {
				data2 = data2.substring(0, 4) + "-" + data2.substring(4, 6)
						+ "-" + data2.substring(6, 8);
			}
			String consultaParte2 = "WHERE "
					+ " orse.orse_tmencerramento >= to_date('" + data1
					+ " 00:00:00','YYYY-MM-DD HH24:MI:SS') and  "
					+ "orse.orse_tmencerramento <= to_date('" + data2
					+ " 23:59:59','YYYY-MM-DD HH24:MI:SS') "
					+ "AND orse.amen_id = 2 ";
			//
			// String consultaParte2 =
			// "WHERE orse.orse_tmencerramento >= to_date('"
			// + Util.formatarDataComTracoAAAAMMDD(dataInicial) +
			// "','YYYY-MM-DD') "
			// + "AND orse.orse_tmencerramento <= to_date('"
			// + Util.formatarDataComTracoAAAAMMDD(dataFinal) +
			// "','YYYY-MM-DD') "
			// + "AND orse.amen_id = :idAtendimentoMotivoEncerramento ";

			if (idLocalidade != null) {
				consultaParte2 = consultaParte2 + " AND loca.loca_id = "
						+ idLocalidade;
			}

			if (idGerencia != null) {
				consultaParte2 = consultaParte2 + " AND greg.greg_id = "
						+ idGerencia;
			}

			if (idUnidade != null) {
				consultaParte2 = consultaParte2 + " AND uneg.uneg_id = "
						+ idUnidade;
			}

			if ((idsRegiao != null && idsRegiao.length > 0 && !((String) idsRegiao[0])
					.equals("-1"))
					|| (idsMicroregiao != null && idsMicroregiao.length > 0 && !((String) idsMicroregiao[0])
					.equals("-1"))
					|| (idsMunicipio != null && idsMunicipio.length > 0 && !((String) idsMunicipio[0])
					.equals("-1"))) {

				consultaParte1 = consultaParte1
						+ " INNER JOIN CADASTRO.municipio muni on loca.muni_idprincipal = muni.muni_id ";

				if (idsMunicipio != null && idsMunicipio.length > 0
						&& !((String) idsMunicipio[0]).equals("-1")) {

					consultaParte2 = consultaParte2 + " AND muni.muni_id in (";
					String id = null;
					for (int i = 0; i < idsMunicipio.length; i++) {
						id = idsMunicipio[i];
						consultaParte2 = consultaParte2 + id + ",";
					}
					consultaParte2 = Util.removerUltimosCaracteres(
							consultaParte2, 1);
					consultaParte2 = consultaParte2 + ") ";

				}
				if (idsMicroregiao != null && idsMicroregiao.length > 0
						&& !((String) idsMicroregiao[0]).equals("-1")) {

					consultaParte1 = consultaParte1
							+ " INNER JOIN CADASTRO.microrregiao mreg on muni.mreg_id = mreg.mreg_id ";

					consultaParte2 = consultaParte2 + " AND mreg.mreg_id in (";
					String id = null;
					for (int i = 0; i < idsMicroregiao.length; i++) {
						id = idsMicroregiao[i];
						consultaParte2 = consultaParte2 + id + ",";
					}
					consultaParte2 = Util.removerUltimosCaracteres(
							consultaParte2, 1);
					consultaParte2 = consultaParte2 + ") ";

				}
				if (idsRegiao != null && idsRegiao.length > 0
						&& !((String) idsRegiao[0]).equals("-1")) {

					if (idsMicroregiao != null && idsMicroregiao.length > 0
							&& !((String) idsMicroregiao[0]).equals("-1")) {
						consultaParte1 = consultaParte1
								+ " INNER JOIN CADASTRO.regiao regi on mreg.regi_id = regi.regi_id ";

					} else {
						consultaParte1 = consultaParte1
								+ " INNER JOIN CADASTRO.microrregiao mreg on muni.mreg_id = mreg.mreg_id "
								+ " INNER JOIN CADASTRO.regiao regi on mreg.regi_id = regi.regi_id ";
					}

					consultaParte2 = consultaParte2 + " AND regi.regi_id in (";
					String id = null;
					for (int i = 0; i < idsRegiao.length; i++) {
						id = idsRegiao[i];
						consultaParte2 = consultaParte2 + id + ",";
					}
					consultaParte2 = Util.removerUltimosCaracteres(
							consultaParte2, 1);
					consultaParte2 = consultaParte2 + ") ";

				}
			}

			String consulta = consultaParte1
					+ consultaParte2
					+ " order by greg.greg_nmregional,uneg.uneg_nmunidadenegocio,loca.loca_nmlocalidade ";

			retorno = (Collection) session
					.createSQLQuery(consulta)
					.addScalar("numeroOS", Hibernate.STRING)
					.addScalar("codigoServico", Hibernate.STRING)
					.addScalar("descServico", Hibernate.STRING)
					.addScalar("descTipoPavimento", Hibernate.STRING)
					.addScalar("materialrede", Hibernate.STRING)
					.addScalar("diametroRede", Hibernate.STRING)
					.addScalar("dataConclusao", Hibernate.TIMESTAMP)
					.addScalar("codigoExcedente", Hibernate.STRING)
					// .addScalar("codigoMaterial", Hibernate.STRING)
					.addScalar("descExcedente", Hibernate.STRING)
					.addScalar("profundRede", Hibernate.STRING)
					.addScalar("dimenBuraco", Hibernate.STRING)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("nomeLocalidade", Hibernate.STRING)
					.addScalar("idRA", Hibernate.INTEGER)
					.addScalar("nomeUnidade", Hibernate.STRING)
					.addScalar("nomeGerencia", Hibernate.STRING)
					.addScalar("qtdeExcedente", Hibernate.INTEGER)
					// .setInteger("idAtendimentoMotivoEncerramento",
					// AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1163] Gerar Relat�rio de OS executadas por Prestadora de Servio
	 * 
	 * @author Vivianne Sousa
	 * @date 18/04/2011
	 */
	public Collection recuperaTotalServicoOSExecutadas(Date dataInicial,
			Date dataFinal, Integer idLocalidade)
					throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String consulta = "SELECT count(osdg.osdg_cdordemservico) as qtdeOS, "
					+ "svtp.svps_cdservico as codigoServico, "
					+ "svtp.svps_dsservico as descServico "
					+ "FROM INTEGRACAO.os_dados_gerais osdg "
					+ "INNER JOIN ATENDIMENTOPUBLICO.ordem_servico orse on osdg.osdg_cdordemservico = orse.orse_id "
					+ "LEFT JOIN INTEGRACAO.servico_prestador_serv svtp on osdg.svps_cdservico = svtp.svps_cdservico "
					+ "INNER JOIN ATENDIMENTOPUBLICO.registro_atendimento rgat on orse.rgat_id = rgat.rgat_id ";

			String data1 = Util.recuperaDataInvertida(dataInicial);
			if (data1 != null && !data1.equals("")
					&& data1.trim().length() == 8) {
				data1 = data1.substring(0, 4) + "-" + data1.substring(4, 6)
						+ "-" + data1.substring(6, 8);
			}

			String data2 = Util.recuperaDataInvertida(dataFinal);
			if (data2 != null && !data2.equals("")
					&& data2.trim().length() == 8) {
				data2 = data2.substring(0, 4) + "-" + data2.substring(4, 6)
						+ "-" + data2.substring(6, 8);
			}
			consulta = consulta
					+ "WHERE "
					+ " orse.orse_tmencerramento >= to_date('"
					+ data1
					+ " 00:00:00','YYYY-MM-DD HH24:MI:SS') and  "
					+ " orse.orse_tmencerramento <= to_date('"
					+ data2
					+ " 23:59:59','YYYY-MM-DD HH24:MI:SS') "
					+ "AND orse.amen_id = 2 "
					// + "WHERE orse.orse_tmencerramento >= to_date('"
					// + Util.formatarDataComTracoAAAAMMDD(dataInicial) +
					// "','YYYY-MM-DD') "
					// + "AND orse.orse_tmencerramento <= to_date('"
					// + Util.formatarDataComTracoAAAAMMDD(dataFinal) +
					// "','YYYY-MM-DD') "
					// + "AND orse.amen_id = :idAtendimentoMotivoEncerramento "
					+ " AND rgat.loca_id = " + idLocalidade
					+ " group by svtp.svps_cdservico,svtp.svps_dsservico "
					+ " order by svtp.svps_dsservico";

			retorno = (Collection) session.createSQLQuery(consulta)
					.addScalar("qtdeOS", Hibernate.INTEGER)
					.addScalar("codigoServico", Hibernate.STRING)
					.addScalar("descServico", Hibernate.STRING)
					// .setInteger("idAtendimentoMotivoEncerramento",
					// AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1163] Gerar Relat�rio de OS executadas por Prestadora de Servio
	 * 
	 * @author Vivianne Sousa
	 * @date 18/04/2011
	 */
	public Collection recuperaTotalServicoOSExecutadasPorLocalidade(
			Date dataInicial, Date dataFinal, Integer idGerencia,
			Integer idUnidade, Integer idLocalidade, String[] idsRegiao,
			String[] idsMicroregiao, String[] idsMunicipio)
					throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String consultaParte1 = "SELECT count(osdg.osdg_cdordemservico) as qtdeOS, "
					+ "svtp.svps_cdservico as codigoServico, "
					+ "svtp.svps_dsservico as descServico, "
					+ "loca.loca_id as idLocalidade,"
					+ "loca.loca_nmlocalidade as nomeLocalidade "
					+ "FROM INTEGRACAO.os_dados_gerais osdg "
					+ "INNER JOIN ATENDIMENTOPUBLICO.ordem_servico orse on osdg.osdg_cdordemservico = orse.orse_id "
					+ "LEFT JOIN INTEGRACAO.material_prestador_serv mtps on osdg.mtps_cdmaterialutilizado = mtps.mtps_cdmaterial "
					+ "LEFT JOIN INTEGRACAO.servico_prestador_serv svtp on osdg.svps_cdservico = svtp.svps_cdservico "
					+ "INNER JOIN ATENDIMENTOPUBLICO.registro_atendimento rgat on orse.rgat_id = rgat.rgat_id "
					+ "INNER JOIN CADASTRO.localidade loca on rgat.loca_id = loca.loca_id ";

			String data1 = Util.recuperaDataInvertida(dataInicial);
			if (data1 != null && !data1.equals("")
					&& data1.trim().length() == 8) {
				data1 = data1.substring(0, 4) + "-" + data1.substring(4, 6)
						+ "-" + data1.substring(6, 8);
			}

			String data2 = Util.recuperaDataInvertida(dataFinal);
			if (data2 != null && !data2.equals("")
					&& data2.trim().length() == 8) {
				data2 = data2.substring(0, 4) + "-" + data2.substring(4, 6)
						+ "-" + data2.substring(6, 8);
			}
			String consultaParte2 = "WHERE "
					+ " orse.orse_tmencerramento >= to_date('" + data1
					+ " 00:00:00','YYYY-MM-DD HH24:MI:SS') and  "
					+ "orse.orse_tmencerramento <= to_date('" + data2
					+ " 23:59:59','YYYY-MM-DD HH24:MI:SS') "
					+ "AND orse.amen_id = 2 ";

			if (idLocalidade != null) {
				consultaParte2 = consultaParte2 + " AND loca.loca_id = "
						+ idLocalidade;
			}

			if (idGerencia != null) {
				consultaParte2 = consultaParte2 + " AND loca.greg_id = "
						+ idGerencia;
			}

			if (idUnidade != null) {
				consultaParte2 = consultaParte2 + " AND loca.uneg_id = "
						+ idUnidade;
			}

			if ((idsRegiao != null && idsRegiao.length > 0 && !((String) idsRegiao[0])
					.equals("-1"))
					|| (idsMicroregiao != null && idsMicroregiao.length > 0 && !((String) idsMicroregiao[0])
					.equals("-1"))
					|| (idsMunicipio != null && idsMunicipio.length > 0 && !((String) idsMunicipio[0])
					.equals("-1"))) {

				consultaParte1 = consultaParte1
						+ " INNER JOIN CADASTRO.municipio muni on loca.muni_idprincipal = muni.muni_id ";

				if (idsMunicipio != null && idsMunicipio.length > 0
						&& !((String) idsMunicipio[0]).equals("-1")) {

					consultaParte2 = consultaParte2 + " AND muni.muni_id in (";
					String id = null;
					for (int i = 0; i < idsMunicipio.length; i++) {
						id = idsMunicipio[i];
						consultaParte2 = consultaParte2 + id + ",";
					}
					consultaParte2 = Util.removerUltimosCaracteres(
							consultaParte2, 1);
					consultaParte2 = consultaParte2 + ") ";

				}
				if (idsMicroregiao != null && idsMicroregiao.length > 0
						&& !((String) idsMicroregiao[0]).equals("-1")) {

					consultaParte1 = consultaParte1
							+ " INNER JOIN CADASTRO.microrregiao mreg on muni.mreg_id = mreg.mreg_id ";

					consultaParte2 = consultaParte2 + " AND mreg.mreg_id in (";
					String id = null;
					for (int i = 0; i < idsMicroregiao.length; i++) {
						id = idsMicroregiao[i];
						consultaParte2 = consultaParte2 + id + ",";
					}
					consultaParte2 = Util.removerUltimosCaracteres(
							consultaParte2, 1);
					consultaParte2 = consultaParte2 + ") ";

				}
				if (idsRegiao != null && idsRegiao.length > 0
						&& !((String) idsRegiao[0]).equals("-1")) {

					if (idsMicroregiao != null && idsMicroregiao.length > 0
							&& !((String) idsMicroregiao[0]).equals("-1")) {
						consultaParte1 = consultaParte1
								+ " INNER JOIN CADASTRO.regiao regi on mreg.regi_id = regi.regi_id ";

					} else {
						consultaParte1 = consultaParte1
								+ " INNER JOIN CADASTRO.microrregiao mreg on muni.mreg_id = mreg.mreg_id "
								+ " INNER JOIN CADASTRO.regiao regi on mreg.regi_id = regi.regi_id ";
					}

					consultaParte2 = consultaParte2 + " AND regi.regi_id in (";
					String id = null;
					for (int i = 0; i < idsRegiao.length; i++) {
						id = idsRegiao[i];
						consultaParte2 = consultaParte2 + id + ",";
					}
					consultaParte2 = Util.removerUltimosCaracteres(
							consultaParte2, 1);
					consultaParte2 = consultaParte2 + ") ";

				}
			}

			String consulta = consultaParte1
					+ consultaParte2
					+ " group by svtp.svps_cdservico,svtp.svps_dsservico,loca.loca_id,loca.loca_nmlocalidade"
					+ " order by loca.loca_nmlocalidade,svtp.svps_dsservico";

			retorno = (Collection) session.createSQLQuery(consulta)
					.addScalar("qtdeOS", Hibernate.INTEGER)
					.addScalar("codigoServico", Hibernate.STRING)
					.addScalar("descServico", Hibernate.STRING)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("nomeLocalidade", Hibernate.STRING).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * Recupera os par�metros necess�rios da OS
	 * 
	 * @author Svio Luiz
	 * @date 04/04/2011
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Short pesquisarIndAtividadeServicoTipoOS(Integer idOS)
			throws ErroRepositorioException {

		Short retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT servicoTipo.indicadorAtividade " // 0
					+ "FROM OrdemServico os "
					+ "INNER JOIN os.servicoTipo servicoTipo  "
					+ "WHERE os.id = :idOS ";

			retornoConsulta = (Short) session.createQuery(consulta)
					.setInteger("idOS", idOS).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC1163] Gerar Relat�rio de OS executadas por Prestadora de Servio
	 * 
	 * @author Vivianne Sousa
	 * @date 04/05/2011
	 */
	public Integer recuperaTotalOSExecutadasPorLocalidade(Date dataInicial,
			Date dataFinal, Integer idGerencia, Integer idUnidade,
			Integer idLocalidade, String[] idsRegiao, String[] idsMicroregiao,
			String[] idsMunicipio) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String consultaParte1 = "SELECT  count(distinct osdg.osdg_cdordemservico) as qtdeOS "
					+ "FROM INTEGRACAO.os_dados_gerais osdg "
					+ "INNER JOIN ATENDIMENTOPUBLICO.ordem_servico orse on osdg.osdg_cdordemservico = orse.orse_id "
					+ "INNER JOIN ATENDIMENTOPUBLICO.registro_atendimento rgat on orse.rgat_id = rgat.rgat_id "
					+ "INNER JOIN CADASTRO.localidade loca on rgat.loca_id = loca.loca_id ";

			String data1 = Util.recuperaDataInvertida(dataInicial);
			if (data1 != null && !data1.equals("")
					&& data1.trim().length() == 8) {
				data1 = data1.substring(0, 4) + "-" + data1.substring(4, 6)
						+ "-" + data1.substring(6, 8);
			}

			String data2 = Util.recuperaDataInvertida(dataFinal);
			if (data2 != null && !data2.equals("")
					&& data2.trim().length() == 8) {
				data2 = data2.substring(0, 4) + "-" + data2.substring(4, 6)
						+ "-" + data2.substring(6, 8);
			}
			String consultaParte2 = "WHERE "
					+ " orse.orse_tmencerramento >= to_date('" + data1
					+ " 00:00:00','YYYY-MM-DD HH24:MI:SS') and  "
					+ "orse.orse_tmencerramento <= to_date('" + data2
					+ " 23:59:59','YYYY-MM-DD HH24:MI:SS') "
					+ "AND orse.amen_id = 2 ";

			if (idLocalidade != null) {
				consultaParte2 = consultaParte2 + " AND loca.loca_id = "
						+ idLocalidade;
			}

			if (idGerencia != null) {
				consultaParte2 = consultaParte2 + " AND loca.greg_id = "
						+ idGerencia;
			}

			if (idUnidade != null) {
				consultaParte2 = consultaParte2 + " AND loca.uneg_id = "
						+ idUnidade;
			}

			if ((idsRegiao != null && idsRegiao.length > 0 && !((String) idsRegiao[0])
					.equals("-1"))
					|| (idsMicroregiao != null && idsMicroregiao.length > 0 && !((String) idsMicroregiao[0])
					.equals("-1"))
					|| (idsMunicipio != null && idsMunicipio.length > 0 && !((String) idsMunicipio[0])
					.equals("-1"))) {

				consultaParte1 = consultaParte1
						+ " INNER JOIN CADASTRO.municipio muni on loca.muni_idprincipal = muni.muni_id ";

				if (idsMunicipio != null && idsMunicipio.length > 0
						&& !((String) idsMunicipio[0]).equals("-1")) {

					consultaParte2 = consultaParte2 + " AND muni.muni_id in (";
					String id = null;
					for (int i = 0; i < idsMunicipio.length; i++) {
						id = idsMunicipio[i];
						consultaParte2 = consultaParte2 + id + ",";
					}
					consultaParte2 = Util.removerUltimosCaracteres(
							consultaParte2, 1);
					consultaParte2 = consultaParte2 + ") ";

				}
				if (idsMicroregiao != null && idsMicroregiao.length > 0
						&& !((String) idsMicroregiao[0]).equals("-1")) {

					consultaParte1 = consultaParte1
							+ " INNER JOIN CADASTRO.microrregiao mreg on muni.mreg_id = mreg.mreg_id ";

					consultaParte2 = consultaParte2 + " AND mreg.mreg_id in (";
					String id = null;
					for (int i = 0; i < idsMicroregiao.length; i++) {
						id = idsMicroregiao[i];
						consultaParte2 = consultaParte2 + id + ",";
					}
					consultaParte2 = Util.removerUltimosCaracteres(
							consultaParte2, 1);
					consultaParte2 = consultaParte2 + ") ";

				}
				if (idsRegiao != null && idsRegiao.length > 0
						&& !((String) idsRegiao[0]).equals("-1")) {

					if (idsMicroregiao != null && idsMicroregiao.length > 0
							&& !((String) idsMicroregiao[0]).equals("-1")) {
						consultaParte1 = consultaParte1
								+ " INNER JOIN CADASTRO.regiao regi on mreg.regi_id = regi.regi_id ";

					} else {
						consultaParte1 = consultaParte1
								+ " INNER JOIN CADASTRO.microrregiao mreg on muni.mreg_id = mreg.mreg_id "
								+ " INNER JOIN CADASTRO.regiao regi on mreg.regi_id = regi.regi_id ";
					}

					consultaParte2 = consultaParte2 + " AND regi.regi_id in (";
					String id = null;
					for (int i = 0; i < idsRegiao.length; i++) {
						id = idsRegiao[i];
						consultaParte2 = consultaParte2 + id + ",";
					}
					consultaParte2 = Util.removerUltimosCaracteres(
							consultaParte2, 1);
					consultaParte2 = consultaParte2 + ") ";

				}
			}

			String consulta = consultaParte1 + consultaParte2;

			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("qtdeOS", Hibernate.INTEGER).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Atualiza o documento de cobran�a da ordem de Servio que foi gerado
	 * pelo "[UC0444 Gerar e Emitir Extrato de D�bito]"
	 * 
	 * [UC1169] Movimentar Ordens de Servio de Cobran�a por Resultado
	 * 
	 * @author Mariana Victor
	 * @date 19/05/2011
	 */
	public void atualizarDocumentoCobrancaOS(Integer idOrdemServico,
			Integer idCobrancaDocumento) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			String atualizarOS = "";

			atualizarOS = "update gcom.atendimentopublico.ordemservico.OrdemServico "
					+ " set orse_tmultimaalteracao = :ultimaAlteracao, "
					+ " cbdo_id = :idCobrancaDocumento "
					+ " where orse_id = :idOrdemServico";

			session.createQuery(atualizarOS)
			.setInteger("idOrdemServico", idOrdemServico)
			.setTimestamp("ultimaAlteracao", new Date())
			.setInteger("idCobrancaDocumento", idCobrancaDocumento)
			.executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1176] Gerar Ordem de Fiscalizao para Ordem de Servio Encerrada
	 * 
	 * [FS0001] � Validar Ordem de Servio.
	 * 
	 * @author Vivianne Sousa
	 * @date 24/05/2011
	 */
	public Integer pesquisarOSFiscalizacaoPendente(Integer numeroOS)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT os.id " + "FROM OrdemServico os "
					+ "LEFT JOIN os.osReferencia osRef  "
					+ "WHERE osRef.id = :numeroOS "
					+ "AND os.situacao = :pendente "
					+ "AND os.servicoTipo.id = :fiscalizacao ";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("numeroOS", numeroOS)
					.setInteger("fiscalizacao", ServicoTipo.TIPO_FISCALIZACAO)
					.setInteger("pendente", OrdemServico.SITUACAO_PENDENTE)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1176] Gerar Ordem de Fiscalizao para Ordem de Servio Encerrada
	 * [SB0001] - Selecionar Ordens de Servio
	 * 
	 * @author Vivianne Sousa
	 * @date 24/05/2011
	 */
	public Integer recuperaQtdeOSEncerradaConclusaoServico(
			Integer idGrupoCobranca, Integer idGerencia, Integer idUnidade,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idTipoServico, Integer qtdeDiasEncerramentoOS,
			Integer idEmpresaContratoServico) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT count(orse.orse_id) as qtdeOS "
					+ " FROM ATENDIMENTOPUBLICO.ordem_servico orse"
					+ " INNER JOIN cadastro.imovel imov on orse.imov_id= imov.imov_id"
					+ " INNER JOIN cadastro.quadra qdra on imov.qdra_id = qdra.qdra_id"
					+ " INNER JOIN micromedicao.rota rota on qdra.rota_id = rota.rota_id "
					+ " INNER JOIN ATENDIMENTOPUBLICO.ordem_servico_unidade osun on orse.orse_id = osun.orse_id"
					+ " INNER JOIN cadastro.unidade_organizacional unid on osun.unid_id = unid.unid_id";

			if ((idGerencia != null)
					|| (idUnidade != null)
					|| (idLocalidadeInicial != null && idLocalidadeFinal != null)) {
				consulta += " INNER JOIN CADASTRO.localidade loca on imov.loca_id = loca.loca_id  ";
			}

			String consultaWhere = " WHERE rota.cbgr_id = "
					+ idGrupoCobranca
					+ " AND imov.imov_icexclusao = 2 "
					+ " AND orse.orse_cdsituacao = 2 "
					+ " AND orse.amen_id = 2 "
					+ // CONCLUS�O DO Servio
					" AND not exists (select os.orse_id  "
					+ " from ATENDIMENTOPUBLICO.ordem_servico os "
					+ " where os.orse_idreferencia = orse.orse_id  "
					+ " and os.orse_cdsituacao = 1 and os.svtp_id = 705 )"
					+ " AND osun.attp_id = 3 " + " AND unid.empr_id =  "
					+ idEmpresaContratoServico + // empresa da tabela
					// contrato_empresa_servico
					// ;
					" AND orse.svtp_id <> " + ServicoTipo.TIPO_FISCALIZACAO;

			if (qtdeDiasEncerramentoOS != null) {

				Date dataAtualMaisQtdeDias = Util.adicionarNumeroDiasDeUmaData(
						new Date(), -qtdeDiasEncerramentoOS);
				String data1 = Util
						.recuperaDataInvertida(dataAtualMaisQtdeDias);
				if (data1 != null && !data1.equals("")
						&& data1.trim().length() == 8) {
					data1 = data1.substring(0, 4) + "-" + data1.substring(4, 6)
							+ "-" + data1.substring(6, 8);
				}

				consultaWhere += " AND date(substr(orse_tmencerramento,1,10)) >= '"
						+ data1 + "'";

			}

			if (idGerencia != null) {
				consultaWhere += " AND loca.greg_id = " + idGerencia + " ";
			}
			if (idUnidade != null) {
				consultaWhere += " AND loca.uneg_id = " + idUnidade + " ";
			}
			if (idLocalidadeInicial != null && idLocalidadeFinal != null) {
				consultaWhere += " AND loca.loca_id between "
						+ idLocalidadeInicial + " and " + idLocalidadeFinal
						+ " ";
			}
			if (idTipoServico != null) {
				consultaWhere += " AND orse.svtp_id = " + idTipoServico + " ";
			}

			consulta = consulta + consultaWhere;

			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("qtdeOS", Hibernate.INTEGER).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1176] Gerar Ordem de Fiscalizao para Ordem de Servio Encerrada
	 * [SB0001] - Selecionar Ordens de Servio
	 * 
	 * @author Vivianne Sousa
	 * @date 24/05/2011
	 */
	public Integer recuperaQtdeOSEncerradaNaoConclusaoServico(
			Integer idGrupoCobranca, Integer idGerencia, Integer idUnidade,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idTipoServico, Integer qtdeDiasEncerramentoOS,
			Integer idEmpresaContratoServico) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT count(orse.orse_id) as qtdeOS "
					+ " FROM ATENDIMENTOPUBLICO.ordem_servico orse"
					+ " INNER JOIN cadastro.imovel imov on orse.imov_id= imov.imov_id"
					+ " INNER JOIN cadastro.quadra qdra on imov.qdra_id = qdra.qdra_id"
					+ " INNER JOIN micromedicao.rota rota on qdra.rota_id = rota.rota_id "
					+ " INNER JOIN ATENDIMENTOPUBLICO.ordem_servico_unidade osun on orse.orse_id = osun.orse_id"
					+ " INNER JOIN cadastro.unidade_organizacional unid on osun.unid_id = unid.unid_id";

			if ((idGerencia != null)
					|| (idUnidade != null)
					|| (idLocalidadeInicial != null && idLocalidadeFinal != null)) {
				consulta += " INNER JOIN CADASTRO.localidade loca on imov.loca_id = loca.loca_id  ";
			}

			String consultaWhere = " WHERE rota.cbgr_id = "
					+ idGrupoCobranca
					+ " AND imov.imov_icexclusao = 2 "
					+ " AND orse.orse_cdsituacao = 2 "
					+ " AND orse.amen_id not in (32,2)"
					+ // CANCELADO POR DECURSO DE PRAZO,CONCLUS�O DO Servio
					" AND not exists (select os.orse_id  "
					+ " from ATENDIMENTOPUBLICO.ordem_servico os "
					+ " where os.orse_idreferencia = orse.orse_id  "
					+ " and os.orse_cdsituacao = 1 and os.svtp_id = 705 )"
					+ " AND osun.attp_id = 3 " + " AND unid.empr_id = "
					+ idEmpresaContratoServico + " AND orse.svtp_id <> "
					+ ServicoTipo.TIPO_FISCALIZACAO;

			if (qtdeDiasEncerramentoOS != null) {

				Date dataAtualMaisQtdeDias = Util.adicionarNumeroDiasDeUmaData(
						new Date(), -qtdeDiasEncerramentoOS);
				String data1 = Util
						.recuperaDataInvertida(dataAtualMaisQtdeDias);
				if (data1 != null && !data1.equals("")
						&& data1.trim().length() == 8) {
					data1 = data1.substring(0, 4) + "-" + data1.substring(4, 6)
							+ "-" + data1.substring(6, 8);
				}

				consultaWhere += " AND date(substr(orse_tmencerramento,1,10)) >= '"
						+ data1 + "'";

			}

			if (idGerencia != null) {
				consultaWhere += " AND loca.greg_id = " + idGerencia + " ";
			}
			if (idUnidade != null) {
				consultaWhere += " AND loca.uneg_id = " + idUnidade + " ";
			}
			if (idLocalidadeInicial != null && idLocalidadeFinal != null) {
				consultaWhere += " AND loca.loca_id between "
						+ idLocalidadeInicial + " and " + idLocalidadeFinal
						+ " ";
			}
			if (idTipoServico != null) {
				consultaWhere += " AND orse.svtp_id = " + idTipoServico + " ";
			}

			consulta = consulta + consultaWhere;

			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("qtdeOS", Hibernate.INTEGER).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1176] Gerar Ordem de Fiscalizao para Ordem de Servio Encerrada
	 * 
	 * [FS0001] � Validar Ordem de Servio.
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Integer pesquisarEmpresaContratoEmpresaServico(
			Integer idGrupoCobranca) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT cese.empresa.id " + "FROM CobrancaGrupo cbgr "
					+ "INNER JOIN cbgr.contratoEmpresaServico cese   "
					+ "WHERE cbgr.id = :idGrupoCobranca ";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idGrupoCobranca", idGrupoCobranca)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1176] Gerar Ordem de Fiscalizao para Ordem de Servio Encerrada
	 * 
	 * [SB0001] - Selecionar Ordens de Servio
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Integer pesquisarRotaGrupoCobranca(Integer idGrupoCobranca)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT rota.id " + "FROM Rota rota "
					+ "WHERE rota.cobrancaGrupo.id = :idGrupoCobranca ";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idGrupoCobranca", idGrupoCobranca)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1176] Gerar Ordem de Fiscalizao para Ordem de Servio Encerrada
	 * [SB0002] � Verificar Ordem Servio
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Integer pesquisarIdMotivoEncerramentoOS(Integer idOrdemServico)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "SELECT amen.id " + "FROM OrdemServico orse "
					+ "LEFT JOIN orse.atendimentoMotivoEncerramento amen  "
					+ "WHERE orse.id = :idOrdemServico ";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1176] Gerar Ordem de Fiscalizao para Ordem de Servio Encerrada
	 * [SB0003] - Gerar V�rias Ordens de Fiscalizao
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Collection recuperaIdOSEncerradaConclusaoServico(
			Integer idGrupoCobranca, Integer idGerencia, Integer idUnidade,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idTipoServico, Integer qtdeDiasEncerramentoOS,
			Integer idEmpresaContratoServico) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT orse.orse_id as idOS "
					+ " FROM ATENDIMENTOPUBLICO.ordem_servico orse"
					+ " INNER JOIN cadastro.imovel imov on orse.imov_id= imov.imov_id"
					+ " INNER JOIN cadastro.quadra qdra on imov.qdra_id = qdra.qdra_id"
					+ " INNER JOIN micromedicao.rota rota on qdra.rota_id = rota.rota_id "
					+ " INNER JOIN ATENDIMENTOPUBLICO.ordem_servico_unidade osun on orse.orse_id = osun.orse_id"
					+ " INNER JOIN cadastro.unidade_organizacional unid on osun.unid_id = unid.unid_id";

			if ((idGerencia != null)
					|| (idUnidade != null)
					|| (idLocalidadeInicial != null && idLocalidadeFinal != null)) {
				consulta += " INNER JOIN CADASTRO.localidade loca on imov.loca_id = loca.loca_id  ";
			}

			String consultaWhere = " WHERE rota.cbgr_id = "
					+ idGrupoCobranca
					+ " AND imov.imov_icexclusao = 2 "
					+ " AND orse.orse_cdsituacao = 2 "
					+ " AND orse.amen_id = 2 "
					+ // CONCLUS�O DO Servio
					" AND not exists (select os.orse_id  "
					+ " from ATENDIMENTOPUBLICO.ordem_servico os "
					+ " where os.orse_idreferencia = orse.orse_id  "
					+ " and os.orse_cdsituacao = 1 and os.svtp_id = 705 )"
					+ " AND osun.attp_id = 3 " + " AND unid.empr_id =  "
					+ idEmpresaContratoServico + " AND orse.svtp_id <> "
					+ ServicoTipo.TIPO_FISCALIZACAO;
			if (qtdeDiasEncerramentoOS != null) {

				Date dataAtualMaisQtdeDias = Util.adicionarNumeroDiasDeUmaData(
						new Date(), -qtdeDiasEncerramentoOS);
				String data1 = Util
						.recuperaDataInvertida(dataAtualMaisQtdeDias);
				if (data1 != null && !data1.equals("")
						&& data1.trim().length() == 8) {
					data1 = data1.substring(0, 4) + "-" + data1.substring(4, 6)
							+ "-" + data1.substring(6, 8);
				}

				consultaWhere += " AND date(substr(orse_tmencerramento,1,10)) >= '"
						+ data1 + "'";

			}

			if (idGerencia != null) {
				consultaWhere += " AND loca.greg_id = " + idGerencia + " ";
			}
			if (idUnidade != null) {
				consultaWhere += " AND loca.uneg_id = " + idUnidade + " ";
			}
			if (idLocalidadeInicial != null && idLocalidadeFinal != null) {
				consultaWhere += " AND loca.loca_id between "
						+ idLocalidadeInicial + " and " + idLocalidadeFinal
						+ " ";
			}
			if (idTipoServico != null) {
				consultaWhere += " AND orse.svtp_id = " + idTipoServico + " ";
			}

			consulta = consulta + consultaWhere;

			retorno = (Collection) session.createSQLQuery(consulta)
					.addScalar("idOS", Hibernate.INTEGER).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1176] Gerar Ordem de Fiscalizao para Ordem de Servio Encerrada
	 * [SB0003] - Gerar V�rias Ordens de Fiscalizao
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Collection recuperaIdOSEncerradaNaoConclusaoServico(
			Integer idGrupoCobranca, Integer idGerencia, Integer idUnidade,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idTipoServico, Integer qtdeDiasEncerramentoOS,
			Integer idEmpresaContratoServico) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT orse.orse_id as idOS "
					+ " FROM ATENDIMENTOPUBLICO.ordem_servico orse"
					+ " INNER JOIN cadastro.imovel imov on orse.imov_id= imov.imov_id"
					+ " INNER JOIN cadastro.quadra qdra on imov.qdra_id = qdra.qdra_id"
					+ " INNER JOIN micromedicao.rota rota on qdra.rota_id = rota.rota_id "
					+ " INNER JOIN ATENDIMENTOPUBLICO.ordem_servico_unidade osun on orse.orse_id = osun.orse_id"
					+ " INNER JOIN cadastro.unidade_organizacional unid on osun.unid_id = unid.unid_id";

			if ((idGerencia != null)
					|| (idUnidade != null)
					|| (idLocalidadeInicial != null && idLocalidadeFinal != null)) {
				consulta += " INNER JOIN CADASTRO.localidade loca on imov.loca_id = loca.loca_id  ";
			}

			String consultaWhere = " WHERE rota.cbgr_id = "
					+ idGrupoCobranca
					+ " AND imov.imov_icexclusao = 2 "
					+ " AND orse.orse_cdsituacao = 2 "
					+ " AND orse.amen_id not in (32,2)"
					+ // CANCELADO POR DECURSO DE PRAZO,CONCLUS�O DO Servio
					" AND not exists (select os.orse_id  "
					+ " from ATENDIMENTOPUBLICO.ordem_servico os "
					+ " where os.orse_idreferencia = orse.orse_id  "
					+ " and os.orse_cdsituacao = 1 and os.svtp_id = 705 )"
					+ " AND osun.attp_id = 3 " + " AND unid.empr_id = "
					+ idEmpresaContratoServico + " AND orse.svtp_id <> "
					+ ServicoTipo.TIPO_FISCALIZACAO;

			if (qtdeDiasEncerramentoOS != null) {

				Date dataAtualMaisQtdeDias = Util.adicionarNumeroDiasDeUmaData(
						new Date(), -qtdeDiasEncerramentoOS);
				String data1 = Util
						.recuperaDataInvertida(dataAtualMaisQtdeDias);
				if (data1 != null && !data1.equals("")
						&& data1.trim().length() == 8) {
					data1 = data1.substring(0, 4) + "-" + data1.substring(4, 6)
							+ "-" + data1.substring(6, 8);
				}

				consultaWhere += " AND date(substr(orse_tmencerramento,1,10)) >= '"
						+ data1 + "'";

			}

			if (idGerencia != null) {
				consultaWhere += " AND loca.greg_id = " + idGerencia + " ";
			}
			if (idUnidade != null) {
				consultaWhere += " AND loca.uneg_id = " + idUnidade + " ";
			}
			if (idLocalidadeInicial != null && idLocalidadeFinal != null) {
				consultaWhere += " AND loca.loca_id between "
						+ idLocalidadeInicial + " and " + idLocalidadeFinal
						+ " ";
			}
			if (idTipoServico != null) {
				consultaWhere += " AND orse.svtp_id = " + idTipoServico + " ";
			}

			consulta = consulta + consultaWhere;

			retorno = (Collection) session.createSQLQuery(consulta)
					.addScalar("idOS", Hibernate.INTEGER).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1176] Gerar Ordem de Fiscalizao para Ordem de Servio Encerrada
	 * [SB0004] � Gerar Ordem de Servio.
	 * 
	 * @author Vivianne Sousa
	 * @date 27/05/2011
	 */
	public Object[] pesquisarImovelEUnidadeOrganizacional(Integer idOrdemServico)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "SELECT imov.imov_id as idImovel, unid.unid_id as idUnidade"
					+ " FROM ATENDIMENTOPUBLICO.ordem_servico orse "
					+ " INNER JOIN cadastro.imovel imov on orse.imov_id= imov.imov_id "
					+ " INNER JOIN cadastro.localidade loca on imov.loca_id = loca.loca_id"
					+ " INNER JOIN cadastro.unidade_organizacional unid on loca.loca_id = unid.loca_id "
					+ " WHERE orse.orse_id = :idOrdemServico ";

			retorno = (Object[]) session.createSQLQuery(consulta)
					.addScalar("idImovel", Hibernate.INTEGER)
					.addScalar("idUnidade", Hibernate.INTEGER)
					.setInteger("idOrdemServico", idOrdemServico)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1176] Gerar Ordem de Fiscalizao para Ordem de Servio Encerrada
	 * 
	 * @author Vivianne sousa
	 * @date 30/05/2011
	 * 
	 * @param idOS
	 * @throws ControladorException
	 */
	public Object[] recuperaDadosOSPorId(Integer idOS)
			throws ErroRepositorioException {

		Object[] retornoConsulta = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT  "
					+ "servicoTipo.descricao, "// 0
					+ "amen.descricao, "// 1
					+ "os.dataEncerramento, "// 2
					+ "imovelOS.id, " // 3
					+ "imovelOS.numeroSequencialRota, "// 4
					+ "ligAguaSitu.descricao, "// 5
					+ "ligEsgotoSitu.descricao, " // 6
					+ "rota.codigo, "// 7
					+ "rota.cobrancaGrupo.id "// 8
					+ "FROM OrdemServico os "
					+ "LEFT JOIN os.atendimentoMotivoEncerramento amen "
					+ "INNER JOIN os.servicoTipo servicoTipo  "
					+ "LEFT JOIN os.imovel imovelOS "
					+ "LEFT JOIN imovelOS.ligacaoAguaSituacao ligAguaSitu "
					+ "LEFT JOIN imovelOS.ligacaoEsgotoSituacao ligEsgotoSitu "
					+ "LEFT JOIN imovelOS.quadra qdraOS  "
					+ "INNER JOIN qdraOS.rota rota " + "WHERE os.id = :idOS ";

			retornoConsulta = (Object[]) session.createQuery(consulta)
					.setInteger("idOS", idOS).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}

	/**
	 * [UC1116] Atualizar Informaes da OS para Boletim de Medio
	 * 
	 * @author Vivianne Sousa
	 * @date 01/06/2011
	 */
	public OrdemServicoBoletim recuperaOrdemServicoBoletim(
			Integer idOrdemServico) throws ErroRepositorioException {

		OrdemServicoBoletim retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select orbo " + " from OrdemServicoBoletim orbo "
					+ " inner join orbo.ordemServico orse "
					+ " where orse.id = :idOrdemServico "
					+ " and orse.indicadorBoletim = :indicadorBoletim "
					+ " and orbo.indicadorPavimento = :indicadorPavimento ";

			retorno = (OrdemServicoBoletim) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setShort("indicadorBoletim", ConstantesSistema.SIM)
					.setShort("indicadorPavimento", ConstantesSistema.SIM)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1116] Atualizar Informaes da OS para Boletim de Medio
	 * 
	 * @author Vivianne Sousa
	 * @date 01/06/2011
	 */
	public String pesquisarNumeroHidrometro(Integer idImovel)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String retorno = null;
		String consulta = null;

		try {

			consulta = "select hid.numero" + " from LigacaoAgua la"
					+ " inner join la.hidrometroInstalacaoHistorico hih"
					+ " inner join hih.hidrometro hid"
					+ " where la.id = :idImovel "
					+ " and hih.dataRetirada  is null ";

			retorno = (String) session.createQuery(consulta)
					.setInteger("idImovel", idImovel).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceo para a prxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sesso
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection pesquisarEquipeEquipamentosEspeciais(
			Integer indicadorEquipamentosEspeciais)
					throws ErroRepositorioException {
		
		return null;
	}

	/**
	 * [UC0713] Emitir Ordem de Servio Seletiva [SB0002] Gerar TXT
	 * 
	 * @author Vivianne Sousa
	 * @date 28/06/2011
	 */
	public Collection pesquisarDadosEmitirTxtOsInspecaoAnormalidade(
			Integer quantidadeInicio, Integer quantidadeMaxima,
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta;

		try {
			consulta = "SELECT orse.id, "
					+ " imov.id, "
					+ " imov.imovelPerfil.descricao, "
					+ " imov.ultimaAlteracao, "
					+ " imov.ligacaoAguaSituacao.descricao, "
					+ " imov.ligacaoEsgotoSituacao.descricao, "
					+ " ltan.descricao "
					+ " FROM OrdemServico orse "
					+ " INNER JOIN orse.imovel imov "
					+ " LEFT JOIN imov.leituraAnormalidade ltan "
					+ " WHERE orse.comandoOrdemSeletiva.id = :idComandoOrdemSeletiva "
					+ " and orse.situacao = :pendente";
			// +
			// " ORDER BY idLocalidade,codigoSetorComercial,numeroQuadra,numeroLote,numeroSubLote ";

			retorno = session
					.createQuery(consulta)
					.setInteger("idComandoOrdemSeletiva",
							idComandoOrdemSeletiva)
							.setShort("pendente", OrdemServico.SITUACAO_PENDENTE)
							.setFirstResult(quantidadeInicio)
							.setMaxResults(quantidadeMaxima).list();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0713] Emitir Ordem de Servio Seletiva [SB0002] Gerar TXT
	 * 
	 * @author Vivianne Sousa
	 * @date 29/06/2011
	 */
	public Collection pesquisarAtendimentoMotivoEncerramento()
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta;

		try {
			consulta = "SELECT amen "
					+ " FROM AtendimentoMotivoEncerramento amen "
					+ " WHERE amen.indicadorExibirFormOrdemSeletiva = :indicadorExibirFormOrdemSeletiva";

			retorno = session
					.createQuery(consulta)
					.setInteger("indicadorExibirFormOrdemSeletiva",
							ConstantesSistema.SIM).list();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0713] Emitir Ordem de Servio Seletiva [SB0002] Gerar TXT
	 * 
	 * @author Vivianne Sousa
	 * @date 05/07/2011
	 */
	public String pesquisarAnormalidadeImovelPorNumeroDeOcorrenciasConsecultivas(
			Collection idAnormalidades, Integer qtdAnormalidades,
			List<String> anoMesOcorrencias, Integer idImovel)
					throws ErroRepositorioException {

		String retorno = "";
		Session session = HibernateUtil.getSession();
		try {

			String consulta = " select ltan.ltan_dsleituraanormalidade  as idAnormalidade "
					+ " from micromedicao.medicao_historico mh "
					+ " inner join cadastro.imovel imovel on ((CASE WHEN mh.imov_id IS NOT NULL THEN mh.imov_id ELSE mh.lagu_id END) = imovel.imov_id) "
					+ " inner join micromedicao.leitura_anormalidade ltan on ltan.ltan_id = mh.ltan_idleitanormfatmt "
					+ " where mh.mdhi_amleitura in (:anoMesOcorrencias) "
					+ " and imovel.imov_id = :idImovel "
					+ " and mh.ltan_idleitanormfatmt in (:idAnormalidades) "
					+ " group by ltan.ltan_dsleituraanormalidade "
					+ " having Count(ltan.ltan_dsleituraanormalidade) > :qtdAnormalidades ";

			retorno = (String) session.createSQLQuery(consulta)
					.addScalar("idAnormalidade", Hibernate.STRING)
					.setParameterList("anoMesOcorrencias", anoMesOcorrencias)
					.setParameterList("idAnormalidades", idAnormalidades)
					.setInteger("qtdAnormalidades", qtdAnormalidades)
					.setInteger("idImovel", idImovel).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0713] Emitir Ordem de Servio Seletiva [SB0002] Gerar TXT
	 * 
	 * @author Vivianne Sousa
	 * @date 05/07/2011
	 */
	public Collection pesquisarAnormalidadeComandoOSS(Integer idComando)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta;

		try {
			consulta = "SELECT acos.comp_id.leituraAnormalidadeId "
					+ " FROM AnormalidadeComandoOSS acos "
					+ " WHERE acos.comp_id.comandoOrdemSeletivaId = :idComando ";

			retorno = session.createQuery(consulta)
					.setInteger("idComando", idComando).list();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0412] Manter Tipo de Servio [SB0003] Atualizar Grau de Importncia
	 * 
	 * @author Thlio Arajo
	 * @date 30/06/2011
	 */
	public void atualizarGrauImportanciaServicoTipo(ServicoTipo servicoTipo)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {
			String update = "update ServicoTipo "
					+ "set ospc_id = :grauImportancia, svtp_tmultimaalteracao = :dataAtual "
					+ "where svtp_id = :idServicoTipo";

			session.createQuery(update)
			.setInteger("grauImportancia",
					servicoTipo.getProgramaCalibragem().getId())
					.setInteger("idServicoTipo", servicoTipo.getId())
					.setTimestamp("dataAtual", new Date()).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceo para a prxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sesso
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de
	 * Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 11/07/2011
	 */
	public Integer pesquisarDadosComandoOSSeletivaCount(Integer idEmpresa,
			Date comandoInicial, Date comandoFinal)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Integer retorno = null;
		String consulta = null;

		try {
			consulta = "SELECT COUNT(DISTINCT coss.id) "
					+ "from ComandoOrdemSeletiva coss "
					+ "where coss.empresa.id = :idEmpresa";

			if (comandoInicial != null && comandoFinal != null) {

				consulta = consulta + " and "
						+ " coss.dataGeracao >= :comandoInicial "
						+ " and coss.dataGeracao <= :comandoFinal  ";

				retorno = (Integer) session
						.createQuery(consulta)
						.setInteger("idEmpresa", idEmpresa)
						.setTimestamp("comandoInicial",
								Util.formatarDataInicial(comandoInicial))
								.setTimestamp("comandoFinal",
										Util.formatarDataFinal(comandoFinal))
										.setMaxResults(1).uniqueResult();

			} else {
				retorno = (Integer) session.createQuery(consulta)
						.setInteger("idEmpresa", idEmpresa).setMaxResults(1)
						.uniqueResult();
			}

		} catch (HibernateException e) {
			// levanta a exceo para a prxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sesso
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de
	 * Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 11/07/2011
	 */
	public Collection pesquisarDadosComandoOSSeletivaResumido(
			Integer idEmpresa, Date dataInicial, Date dataFinal,
			int numeroIndice, int quantidadeRegistros)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno = null;
		String consulta = null;

		try {
			consulta = "select "
					+ " coss.id," // 0
					+ " coss.descricaoComando," // 1
					+ " coss.dataGeracao," // 2
					+ " coss.dataEncerramento," // 3
					+ " coss.situacaoComando " // 4
					+ "from ComandoOrdemSeletiva coss "
					+ "where coss.empresa.id = :idEmpresa "
					+ " and coss.servicoTipo.id = :idServicoTipo  ";

			if (dataInicial != null && dataFinal != null) {

				consulta = consulta + " and "
						+ " coss.dataGeracao >= :dataInicial "
						+ " and coss.dataGeracao <= :dataFinal  "
						+ " ORDER BY coss.id ";

				retorno = session
						.createQuery(consulta)
						.setInteger("idEmpresa", idEmpresa)
						.setInteger(
								"idServicoTipo",
								new Integer(
										ServicoTipo.TIPO_INSPECAO_ANORMALIDADE))
										.setTimestamp("dataInicial",
												Util.formatarDataInicial(dataInicial))
												.setTimestamp("dataFinal",
														Util.formatarDataFinal(dataFinal))
														.setMaxResults(quantidadeRegistros)
														.setFirstResult(numeroIndice * quantidadeRegistros)
														.list();

			} else {
				consulta = consulta + "ORDER BY coss.id ";

				retorno = session
						.createQuery(consulta)
						.setInteger(
								"idServicoTipo",
								new Integer(
										ServicoTipo.TIPO_INSPECAO_ANORMALIDADE))
										.setInteger("idEmpresa", idEmpresa)
										.setMaxResults(quantidadeRegistros)
										.setFirstResult(numeroIndice * quantidadeRegistros)
										.list();
			}

		} catch (HibernateException e) {
			// levanta a exceo para a prxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sesso
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de
	 * Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 12/07/2011
	 */
	public ComandoOrdemSeletiva pesquisarDadosComandoOSSeletiva(
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		ComandoOrdemSeletiva retorno = null;
		String consulta = null;

		try {
			consulta = "SELECT coss " + "FROM ComandoOrdemSeletiva coss "
					+ "LEFT JOIN FETCH coss.empresa "
					+ "LEFT JOIN FETCH coss.gerenciaRegional "
					+ "LEFT JOIN FETCH coss.unidadeNegocio "
					+ "LEFT JOIN FETCH coss.localidadePolo "
					+ "LEFT JOIN FETCH coss.logradouro "
					+ "LEFT JOIN FETCH coss.localidadeInicial "
					+ "LEFT JOIN FETCH coss.localidadeFinal "
					+ "LEFT JOIN FETCH coss.setorComercialInicial "
					+ "LEFT JOIN FETCH coss.setorComercialFinal "
					+ "LEFT JOIN FETCH coss.quadraInicial "
					+ "LEFT JOIN FETCH coss.quadraFinal "
					+ "LEFT JOIN FETCH coss.hidrometroMarca "
					+ "LEFT JOIN FETCH coss.hidrometroLocalInstalacao "
					+ "LEFT JOIN FETCH coss.imovelPerfil "
					+ "LEFT JOIN FETCH coss.categoria "
					+ "LEFT JOIN FETCH coss.subcategoria "
					+ "WHERE coss.id = :idComandoOrdemSeletiva ";

			retorno = (ComandoOrdemSeletiva) session
					.createQuery(consulta)
					.setInteger("idComandoOrdemSeletiva",
							idComandoOrdemSeletiva).setMaxResults(1)
							.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceo para a prxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sesso
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de
	 * Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 13/07/2011
	 */
	public Collection pesquisarDadosAnormalidadeComandoOSS(
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno = null;
		String consulta = null;

		try {
			consulta = "SELECT acos.leituraAnormalidade "
					+ "FROM AnormalidadeComandoOSS acos "
					+ "WHERE acos.comandoOrdemSeletiva.id = :idComandoOrdemSeletiva "
					+ "ORDER BY acos.leituraAnormalidade.descricao ";

			retorno = (Collection) session
					.createQuery(consulta)
					.setInteger("idComandoOrdemSeletiva",
							idComandoOrdemSeletiva).list();

		} catch (HibernateException e) {
			// levanta a exceo para a prxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sesso
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de
	 * Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 13/07/2011
	 */
	public Collection pesquisarDadosCapacidHidrComandoOSS(
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno = null;
		String consulta = null;

		try {
			consulta = "SELECT chco.hidrometroCapacidade "
					+ "FROM CapacidHidrComandoOSS chco "
					+ "WHERE chco.comandoOrdemSeletiva.id = :idComandoOrdemSeletiva "
					+ "ORDER BY chco.hidrometroCapacidade.descricao ";

			retorno = (Collection) session
					.createQuery(consulta)
					.setInteger("idComandoOrdemSeletiva",
							idComandoOrdemSeletiva).list();

		} catch (HibernateException e) {
			// levanta a exceo para a prxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sesso
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de
	 * Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 13/07/2011
	 */
	public Collection pesquisarDadosLigacaoSitComandoOSS(
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno = null;
		String consulta = null;

		try {
			consulta = "SELECT lsco.ligacaoAguaSituacao "
					+ "FROM LigacaoSitComandoOSS lsco "
					+ "WHERE lsco.comandoOrdemSeletiva.id = :idComandoOrdemSeletiva "
					+ "ORDER BY lsco.ligacaoAguaSituacao.descricao ";

			retorno = (Collection) session
					.createQuery(consulta)
					.setInteger("idComandoOrdemSeletiva",
							idComandoOrdemSeletiva).list();

		} catch (HibernateException e) {
			// levanta a exceo para a prxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sesso
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de
	 * Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 15/07/2011
	 */
	public ComandoOrdemSeletiva pesquisarComandoOSSeletiva(
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		ComandoOrdemSeletiva retorno = null;
		String consulta = null;

		try {
			consulta = "SELECT coss " + "FROM ComandoOrdemSeletiva coss "
					+ "WHERE coss.id = :idComandoOrdemSeletiva ";

			retorno = (ComandoOrdemSeletiva) session
					.createQuery(consulta)
					.setInteger("idComandoOrdemSeletiva",
							idComandoOrdemSeletiva).setMaxResults(1)
							.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceo para a prxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sesso
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1192] Movimentar OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @date 18/07/2011
	 */
	public Collection<Object[]> pesquisarDadosOSEmitir(Integer idComando,
			Integer numeroOSInicial, Integer numeroOSFinal)
					throws ErroRepositorioException {

		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = " select distinct(orse.orse_id) as idOS, "
					+ "svtp.svtp_dsservicotipo as servicoTipo, "
					+ "orse.imov_id as idImovel, "
					+ "clie.clie_nmcliente as nomeCliente "
					+ " from atendimentopublico.ordem_servico orse "
					+ "   inner join atendimentopublico.servico_tipo svtp on svtp.svtp_id = orse.svtp_id "
					+ "   inner join cadastro.cliente_imovel clim on clim.imov_id = orse.imov_id "
					+ "   inner join cadastro.cliente clie on clie.clie_id = clim.clie_id "
					+ " where orse.coss_id = :idComando "
					+ "   and clim.crtp_id = 2 "
					+ "   and clim.clim_dtrelacaofim is null "
					+ "   and orse.orse_cdsituacao = 1 ";

			if (numeroOSInicial != null && numeroOSFinal != null) {
				consulta += "  and orse.orse_id BETWEEN (:numeroOSInicial) AND (:numeroOSFinal)";
			}

			consulta = consulta
					+ " group by orse.orse_id, svtp.svtp_dsservicotipo, orse.imov_id, clie.clie_nmcliente "
					+ " order by orse.orse_id, svtp.svtp_dsservicotipo, orse.imov_id, clie.clie_nmcliente ";

			if (numeroOSInicial != null && numeroOSFinal != null) {
				retorno = session.createSQLQuery(consulta)
						.addScalar("idOS", Hibernate.INTEGER)
						.addScalar("servicoTipo", Hibernate.STRING)
						.addScalar("idImovel", Hibernate.INTEGER)
						.addScalar("nomeCliente", Hibernate.STRING)
						.setInteger("idComando", idComando)
						.setInteger("numeroOSInicial", numeroOSInicial)
						.setInteger("numeroOSFinal", numeroOSFinal).list();
			} else {
				retorno = session.createSQLQuery(consulta)
						.addScalar("idOS", Hibernate.INTEGER)
						.addScalar("servicoTipo", Hibernate.STRING)
						.addScalar("idImovel", Hibernate.INTEGER)
						.addScalar("nomeCliente", Hibernate.STRING)
						.setInteger("idComando", idComando).list();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;

	}

	/**
	 * [UC1192] Movimentar OS Seletiva de Inspe��o de Anormalidade [FS0001]
	 * - Verificar se ordem de Servio faz parte do comando
	 * 
	 * @author Vivianne Sousa
	 * @date 19/07/2011
	 */
	public Collection verificaSeOSFazParteComando(
			Integer idComandoOrdemSeletiva, List<Integer> numerosOSPesquisar)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta;

		try {
			consulta = "SELECT distinct(orse.id) "
					+ " FROM OrdemServico orse "
					+ " WHERE orse.comandoOrdemSeletiva.id = :idComandoOrdemSeletiva "
					+ " AND orse.id in (:numerosOSPesquisar) ";

			retorno = session
					.createQuery(consulta)
					.setInteger("idComandoOrdemSeletiva",
							idComandoOrdemSeletiva)
							.setParameterList("numerosOSPesquisar", numerosOSPesquisar)
							.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;

	}

	/**
	 * [UC1192] Movimentar OS Seletiva de Inspe��o de Anormalidade [FS0003]
	 * � Verificar se im�vel faz parte do comando
	 * 
	 * @author Vivianne Sousa
	 * @date 19/07/2011
	 */
	public Collection verificaSeImovelFazParteComando(
			Integer idComandoOrdemSeletiva,
			List<Integer> numerosImoveisPesquisar)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta;

		try {
			consulta = "SELECT distinct(orse.imovel.id) "
					+ " FROM OrdemServico orse "
					+ " WHERE orse.comandoOrdemSeletiva.id = :idComandoOrdemSeletiva "
					+ " AND orse.imovel.id in (:numerosImoveisPesquisar) ";

			retorno = session
					.createQuery(consulta)
					.setInteger("idComandoOrdemSeletiva",
							idComandoOrdemSeletiva)
							.setParameterList("numerosImoveisPesquisar",
									numerosImoveisPesquisar).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;

	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servio do Roteiro Programado
	 * 
	 * Pesquisa os id's das equipes que ainda possuem OS, para a data informada,
	 * que ainda no foram encaminhadas para o campo.
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param dataRoteiro
	 *            - Data para a pesquisa das OS
	 * 
	 * @return Collection<Integer> - Coleo com todos os ID's das equipes.
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarEquipesOSNaoEnviadas(
			Collection<Integer> colsEquipes, Date dataRoteiro)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta;

		try {
			consulta = "select \n "
					+ "	distinct osProgramacao.equipe.id \n "
					+ "from \n "
					+ "   OrdemServicoProgramacao osProgramacao \n "
					+ "   inner join osProgramacao.programacaoRoteiro programacaoRoteiro \n "
					+ "where \n "
					+ "   osProgramacao.indicadorAcompanhamentoServico = 2 and \n "
					+ "   programacaoRoteiro.dataRoteiro >= :dataRoteiro and \n"
					+ "   osProgramacao.equipe.id in ( :ids )";

			retorno = session.createQuery(consulta)
					.setDate("dataRoteiro", dataRoteiro)
					.setParameterList("ids", colsEquipes).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servio do Roteiro Programado
	 * 
	 * Pesquisa se ja foi incluido um arquivo de acompanhamento de Servio para
	 * a equipe / data informadas
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe
	 *            - Identificador da equipe
	 * @param dataRoteiro
	 *            - Data para a pesquisa do arquivo
	 * 
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarArquivoTextoAcompanhamentoServico(
			Integer idUnidadeOrganizacional, Date dataRoteiro)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno;
		String consulta;

		try {
			consulta = "select \n "
					+ "	atas.id \n "
					+ "from \n "
					+ "	ArquivoTextoAcompanhamentoServico atas \n "
					+ "inner join atas.situacaoTransmissaoLeitura sitTrsLeit \n "
					+ "inner join atas.equipe equipe \n "
					+ "where \n "
					+ "	equipe.unidadeOrganizacional.id = :idUnidadeOrganizacional and \n "
					+ "	atas.dataProgramacao = :dataRoteiro and \n"
					+ "   sitTrsLeit.id = :idSitTransLeit ";

			retorno = session
					.createQuery(consulta)
					.setInteger("idUnidadeOrganizacional",
							idUnidadeOrganizacional)
							.setDate("dataRoteiro", dataRoteiro)
							.setInteger("idSitTransLeit",
									SituacaoTransmissaoLeitura.LIBERADO).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servio do Roteiro Programado
	 * 
	 * Pesquisa o imei de uma equipe
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe
	 *            - Identificador da equipe
	 * 
	 * @return Integer - Numero do imei da equipe informada
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarIMEIEquipe(Integer idEquipe)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		BigDecimal retorno;
		String consulta;

		try {
			consulta = "select \n " + "	equipe.numeroImei \n" + "from \n "
					+ "	Equipe equipe \n " + "where \n "
					+ "	equipe.id = :idEquipe ";

			retorno = (BigDecimal) session.createQuery(consulta)
					.setInteger("idEquipe", idEquipe).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servio do Roteiro Programado
	 * 
	 * Pesquisa as OS que ainda no foram enviadas para uma equipe em uma
	 * determinada data
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe
	 *            - id da equipe que ter� as os incluidas
	 * 
	 * @return Collection<OSProgramacao> - Coleo com todos as ordens de
	 *         servico a serem incluidas na programao
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> pesquisarOSNaoEnviadas(
			Integer idEquipe, Date dataProgramacao)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection<OrdemServicoProgramacao> retorno = null;
		String consulta;

		Date dataRoteiroInicial = Util.formatarDataInicial(dataProgramacao);
		Date dataRoteiroFinal = Util.formatarDataFinal(dataProgramacao);

		try {
			consulta = "select \n "
					+ "	osProgramacao \n "
					+ "from \n "
					+ "   OrdemServicoProgramacao osProgramacao \n "
					+ "   inner join fetch osProgramacao.ordemServico ordem \n"
					+ "   left join fetch ordem.imovel imovel \n"
					+ "   left join fetch imovel.ligacaoAgua ligacaoAgua \n"
					+ "   left join fetch imovel.ligacaoAguaSituacao ligacaoAguaSituacao \n"
					+ "   left join fetch imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao \n"
					+ "   left join fetch ligacaoAgua.hidrometroInstalacaoHistorico hih \n"
					+ "   left join fetch hih.hidrometro hid \n"
					+ "   left join fetch hid.hidrometroCapacidade \n"
					+ "   inner join fetch ordem.registroAtendimento registro \n"
					+ "   inner join fetch osProgramacao.programacaoRoteiro prgRot \n"
					+ "where \n "
					+ "   osProgramacao.indicadorAcompanhamentoServico in (2) and \n "
					+ "   osProgramacao.equipe.id = :idEquipe and"
					+ "   prgRot.dataRoteiro BETWEEN :dataRoteiroInicial AND :dataRoteiroFinal ";

			retorno = session.createQuery(consulta)
					.setInteger("idEquipe", idEquipe)
					.setTimestamp("dataRoteiroInicial", dataRoteiroInicial)
					.setTimestamp("dataRoteiroFinal", dataRoteiroFinal).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servio do Roteiro Programado
	 * 
	 * Pesquisa as OS que foram enviadas para uma equipe em uma determinada data
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe
	 *            - id da equipe que ter� as os incluidas
	 * 
	 * @return Collection<OSProgramacao> - Coleo com todos as ordens de
	 *         servico a serem incluidas na programao
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> pesquisarOSEnviadas(
			Integer idUnidadeOrganizacional, Date dataProgramacao)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection<OrdemServicoProgramacao> retorno = null;
		String consulta;

		Date dataRoteiroInicial = Util.formatarDataInicial(dataProgramacao);
		Date dataRoteiroFinal = Util.formatarDataFinal(dataProgramacao);

		try {
			consulta = "select \n "
					+ "	osProgramacao \n "
					+ "from \n "
					+ "   OrdemServicoProgramacao osProgramacao \n "
					+ "   inner join fetch osProgramacao.programacaoRoteiro prgRot \n"
					+ "where \n "
					+ "   osProgramacao.indicadorAcompanhamentoServico = 1 and \n "
					+ "   prgRot.unidadeOrganizacional.id = :idUnidadeOrganizacional and"
					+ "   prgRot.dataRoteiro BETWEEN :dataRoteiroInicial AND :dataRoteiroFinal ";

			retorno = session
					.createQuery(consulta)
					.setInteger("idUnidadeOrganizacional",
							idUnidadeOrganizacional)
							.setTimestamp("dataRoteiroInicial", dataRoteiroInicial)
							.setTimestamp("dataRoteiroFinal", dataRoteiroFinal).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servio do Roteiro Programado
	 * 
	 * Pesquisa o id do Arquivo Texto do Acompanhamento de Servio
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe
	 *            - Identificador da equipe
	 * @param dataRoteiro
	 *            - Data do roteiro a ser pesquisado
	 * 
	 * @return Integer - Numero do imei da equipe informada
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdArquivoTextoAcompanhamentoServico(
			Integer idEquipe, Date dataRoteiro) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Integer retorno;
		String consulta;

		try {
			consulta = "select \n " + "	atas.id " + "from \n "
					+ "	ArquivoTextoAcompanhamentoServico atas \n "
					+ "where \n " + "	atas.equipe.id = :idEquipe and \n "
					+ "	atas.dataProgramacao = :dataRoteiro";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idEquipe", idEquipe)
					.setDate("dataRoteiro", dataRoteiro).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1199] � Acompanhar Arquivos de Roteiro
	 * 
	 * Pesquisa o Arquivo Texto do Acompanhamento de Servio
	 * 
	 * @author Thlio Arajo
	 * @date 19/07/2011
	 * 
	 * @param periodoProgramacaoInicial
	 * @param periodoProgramacaoFinal
	 * @param idEmpresa
	 * @param idSituacao
	 * @param idUnidOrganizacional
	 * 
	 * @returnCollection <ArquivoTextoAcompanhamentoServico>
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAcompanhamentoArquivosRoteiro(
			String dataProgramacao, String idEmpresa, String idSituacao,
			Integer idUnidOrganizacional) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Collection retorno;
		String consulta;

		try {
			consulta = "select "
					+ "	arq.txac_id as idArq,"
					+ "   eqp.eqpe_nmequipe as nmEquipe,"
					+ "	eqp.eqpe_id as idEquipe,"
					+ "   count(distinct(ospr.osas_id)) as qtdProgramadas,"
					+ "   count(distinct(osprAtu.osas_id)) as qtdTransmitidas,"
					+ "   stl.sitl_dssituacao as dsSituacao "
					+ "from "
					+ "   atendimentopublico.os_prg_acomp_servico ospr "
					+ "inner join"
					+ "   atendimentopublico.arq_txt_acomp_servico arq "
					+ "on ospr.txac_id = arq.txac_id "
					+ "inner join"
					+ "	atendimentopublico.equipe eqp "
					+ "on arq.eqpe_id = eqp.eqpe_id "
					+ "inner join"
					+ "   micromedicao.situacao_transm_leitura stl "
					+ "on arq.sitl_id = stl.sitl_id "
					+ "left join "
					+ "   (select osas_id, txac_id "
					+ "      from atendimentopublico.os_prg_acomp_servico "
					+ "      where osas_icatualizaos = 1 and osas_icexcluido <> 1) osprAtu "
					+ "on osprAtu.txac_id = arq.txac_id "
					+ "where arq.txac_dtprogramacao = :dataProgramacao and "
					+ "	arq.eqpe_id in ( "
					+ "      select "
					+ "        eqpe_id from atendimentopublico.equipe "
					+ "      where eqpe_icuso = 1 and unid_id in ("
					+ "         select "
					+ "           unid_id from cadastro.unidade_organizacional where empr_id = :idEmpresa and unid_id = :idUnidOrganizacional) "
					+ "      ) and" + "	ospr.osas_icexcluido <> 1";
			if (!idSituacao.equals("-1")) {
				consulta = consulta + " and stl.sitl_id = "
						+ Util.converterStringParaInteger(idSituacao);
			}

			consulta = consulta
					+ "   group by arq.txac_id, eqp.eqpe_nmequipe, eqp.eqpe_id, stl.sitl_dssituacao order by arq.txac_id";
			retorno = session
					.createSQLQuery(consulta)
					.addScalar("idArq", Hibernate.INTEGER)
					.addScalar("nmEquipe", Hibernate.STRING)
					.addScalar("idEquipe", Hibernate.INTEGER)
					.addScalar("qtdProgramadas", Hibernate.INTEGER)
					.addScalar("qtdTransmitidas", Hibernate.INTEGER)
					.addScalar("dsSituacao", Hibernate.STRING)
					.setDate("dataProgramacao",
							Util.converteStringParaDate(dataProgramacao))
							.setInteger("idEmpresa",
									Util.converterStringParaInteger(idEmpresa))
									.setInteger("idUnidOrganizacional", idUnidOrganizacional)
									.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1199] � Acompanhar Arquivos de Roteiro
	 * 
	 * Pesquisa OS Programacao Acompanhamento Servico
	 * 
	 * @author Thlio Arajo
	 * @date 21/07/2011
	 * 
	 * @param idArqTextoAcompServico
	 * @return Date - data da OS Programacao Acompanhamento Servico
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOSProgramacaoAcompServico(
			String idArqTextoAcompServico) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Collection retorno;
		String consulta;

		try {
			consulta = "select "
					+ "	ospr.id,"
					+ "   ospr.ordemServico.id,"
					+ "   ospr.descricaoEndereco,"
					+ "   osst.descricaoSituacao,"
					+ "	ospr.sequencialProgramacao,"
					+ "   svtp.descricao "
					+ "from "
					+ "   OSProgramacaoAcompanhamentoServico ospr "
					+ "inner join"
					+ "   ospr.ordemServicoSituacao osst "
					+ "inner join"
					+ "	ospr.servicoTipo svtp "
					+ "inner join"
					+ "	ospr.equipe eqpe "
					+ "where ospr.arquivoTextoAcompanhamentoServico.id = :idArqTextoAcompServico and ospr.indicadorExcluido <> 1 order by eqpe.nome, ospr.sequencialProgramacao";

			retorno = session
					.createQuery(consulta)
					.setInteger(
							"idArqTextoAcompServico",
							Util.converterStringParaInteger(idArqTextoAcompServico))
							.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1197] Encerrar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @date 21/07/2011
	 */
	public Collection pesquisaOrdemServicoFazParteComando(int quantidadeInicio,
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta;

		try {
			consulta = "SELECT distinct(orse.id) "
					+ " FROM OrdemServico orse "
					+ " WHERE orse.comandoOrdemSeletiva.id = :idComandoOrdemSeletiva "
					+ " and orse.situacao = :pendente ";

			retorno = session
					.createQuery(consulta)
					.setInteger("idComandoOrdemSeletiva",
							idComandoOrdemSeletiva)
							.setShort("pendente", OrdemServico.SITUACAO_PENDENTE)
							.setFirstResult(quantidadeInicio).setMaxResults(1000)
							.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;

	}

	/**
	 * [UC1197] Encerrar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @date 21/07/2011
	 */
	public void atualizarDataEncerramentoComando(Integer idComando)
			throws ErroRepositorioException {

		String consulta;
		Session session = HibernateUtil.getSession();

		try {
			consulta = "update gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva "
					+ " set coss_dtencerramento = :dataEncerramento, "
					+ " coss_cdsituacao = :encerrada,"
					+ " coss_tmultimaalteracao = :ultimaAlteracao "
					+ " where coss_id = :idComando ";

			session.createQuery(consulta)
			.setTimestamp("dataEncerramento", new Date())
			.setTimestamp("ultimaAlteracao", new Date())
			.setInteger("idComando", idComando)
			.setShort("encerrada", ConstantesSistema.NAO)
			.executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * [UC0713] Emitir Ordem de Servio Seletiva [SB0002] Gerar TXT
	 * 
	 * @author Vivianne Sousa
	 * @date 21/07/2011
	 */
	public void atualizarIndicadorGeracaoTxtComando(Integer idComando)
			throws ErroRepositorioException {

		String consulta;
		Session session = HibernateUtil.getSession();

		try {
			consulta = "update gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva "
					+ " set coss_icgeracaotxt = :indicadorGeracaoTxt, coss_tmultimaalteracao = :ultimaAlteracao "
					+ " where coss_id = :idComando ";

			session.createQuery(consulta)
			.setTimestamp("ultimaAlteracao", new Date())
			.setShort("indicadorGeracaoTxt", ConstantesSistema.SIM)
			.setInteger("idComando", idComando).executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * [UC1190] Programao Automtica do Roteiro para Acompanhamento das OS
	 * 
	 * @author Svio Luiz
	 * @date 19/07/2011
	 */
	public Date pesquisarDataAnteriorProgramacaoRoteiro(
			Integer idUnidadeOrganizacional) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Date retorno = null;
		String consulta;

		try {
			consulta = "SELECT max(arTxtAcServ.dataProgramacao) "
					+ " FROM ArquivoTextoAcompanhamentoServico arTxtAcServ "
					+ " INNER JOIN arTxtAcServ.equipe eqpe "
					+ " INNER JOIN eqpe.unidadeOrganizacional unidOrg "
					+ " WHERE unidOrg.id = :idUnidadeOrganizacional";

			retorno = (Date) session
					.createQuery(consulta)
					.setInteger("idUnidadeOrganizacional",
							idUnidadeOrganizacional).setMaxResults(1)
							.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * -- [UC1190] Programao Automtica do Roteiro para Acompanhamento das OS
	 * 
	 * @author Svio Luiz
	 * @date 19/07/2011
	 */
	public Collection pesquisarOSAcompServicoTransmitidasNaoAtualizadas(
			Integer idUnidadeOrganizacional, Date dataProgramacao)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta;

		try {
			consulta = "SELECT osProAcomServ "
					+ " FROM OSProgramacaoAcompanhamentoServico osProAcomServ "
					+ " INNER JOIN osProAcomServ.equipe eqpe "
					+ " INNER JOIN eqpe.unidadeOrganizacional unidOrg "
					+ " WHERE osProAcomServ.dataProgramacao = :dataProgramacao and "
					+ " osProAcomServ.indicadorTrasmissaoOS = :indicadorTrasmissaoOS and "
					+ " osProAcomServ.indicadorAtualizacaoOS = :indicadorAtualizacaoOS and "
					+ " unidOrg.id = :idUnidadeOrganizacional";

			retorno = (Collection) session
					.createQuery(consulta)
					.setInteger("idUnidadeOrganizacional",
							idUnidadeOrganizacional)
							.setDate("dataProgramacao", dataProgramacao)
							.setShort(
									"indicadorTrasmissaoOS",
									OSProgramacaoAcompanhamentoServico.INDICADOR_TRANSMISSAO_SIM)
									.setShort(
											"indicadorAtualizacaoOS",
											OSProgramacaoAcompanhamentoServico.INDICADOR_ATUALIZA_OS_NAO)
											.list();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1190] Programao Automtica do Roteiro para Acompanhamento das OS
	 * 
	 * @author Svio Luiz
	 * @date 19/07/2011
	 */
	public Collection pesquisarOSAcompServicoNaoENcerradas(
			Integer idUnidadeOrganizacional, Date dataProgramacao)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta;

		try {
			consulta = "SELECT osProAcomServ "
					+ " FROM OSProgramacaoAcompanhamentoServico osProAcomServ "
					+ " INNER JOIN osProAcomServ.ordemServicoSituacao osSituacao "
					+ " INNER JOIN osProAcomServ.ordemServico os "
					+ " INNER JOIN osProAcomServ.equipe eqpe "
					+ " INNER JOIN eqpe.unidadeOrganizacional unidOrg "
					+ " LEFT JOIN osProAcomServ.osProgramacaoNaoEncerramentoMotivo osProgramaNaoEnceMotivo "
					+ " WHERE osProAcomServ.dataProgramacao = :dataProgramacao and "
					+ " (osSituacao.id <> :situacaoEncerrada or (osSituacao.id = :situacaoEncerrada and osProgramaNaoEnceMotivo.id is not null))  and "
					+ " os.situacao <> :situacaoOSEncerrada and "
					+ " unidOrg.id = :idUnidadeOrganizacional and "
					+ " osProAcomServ.indicadorExcluido <> :indicadorSim";

			retorno = (Collection) session
					.createQuery(consulta)
					.setInteger("idUnidadeOrganizacional",
							idUnidadeOrganizacional)
							.setDate("dataProgramacao", dataProgramacao)
							.setInteger("situacaoEncerrada",
									OrdemServicoSituacao.ENCERRADO)
									.setShort("situacaoOSEncerrada",
											OrdemServico.SITUACAO_ENCERRADO)
											.setShort("indicadorSim", ConstantesSistema.SIM).list();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1190] Programao Automtica do Roteiro para Acompanhamento das OS
	 * 
	 * @author Svio Luiz
	 * @date 19/07/2011
	 */
	public Equipe pesquisarEquipeComEquipamentoEspecial(
			Integer idUnidadeOrganizacional, Integer idEquipamentoEspecial)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Equipe retorno = null;
		String consulta;

		try {
			consulta = "SELECT DISTINCT equipe "
					+ "FROM EquipeEquipamentosEspeciais eqeqes "
					+ "INNER JOIN eqeqes.equipamentosEspeciais equipEspe "
					+ "INNER JOIN eqeqes.equipe equipe "
					+ "INNER JOIN equipe.unidadeOrganizacional unidade "
					+ "WHERE unidade.id = :idUnidadeOrganizacional and "
					+ "equipEspe.id = :idEquipamentoEspecial ";

			retorno = (Equipe) session
					.createQuery(consulta)
					.setInteger("idUnidadeOrganizacional",
							idUnidadeOrganizacional)
							.setInteger("idEquipamentoEspecial", idEquipamentoEspecial)
							.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1190] Programao Automtica do Roteiro para Acompanhamento das OS
	 * 
	 * [SB0002] Inserir Ordem de Servio na Programao
	 * 
	 * @author Svio Luiz
	 * @date 19/07/2011
	 */
	public ProgramacaoRoteiro pesquisarProgramacaoRoteiro(
			Integer idUnidadeOrganizacional, Date dataProgramacao)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		ProgramacaoRoteiro retorno = null;
		String consulta;

		try {
			consulta = "SELECT proRot "
					+ "FROM ProgramacaoRoteiro proRot "
					+ "INNER JOIN proRot.unidadeOrganizacional unidade "
					+ "WHERE unidade.id = :idUnidadeOrganizacional and "
					+ "   proRot.dataRoteiro BETWEEN :dataRoteiroInicial AND :dataRoteiroFinal ";

			retorno = (ProgramacaoRoteiro) session
					.createQuery(consulta)
					.setInteger("idUnidadeOrganizacional",
							idUnidadeOrganizacional)
							.setTimestamp("dataRoteiroInicial",
									Util.formatarDataInicial(dataProgramacao))
									.setTimestamp("dataRoteiroFinal",
											Util.formatarDataFinal(dataProgramacao))
											.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1190] Programao Automtica do Roteiro para Acompanhamento das OS
	 * 
	 * [SB0002] Inserir Ordem de Servio na Programao
	 * 
	 * @author Svio Luiz
	 * @date 19/07/2011
	 */
	public short pesquisarMaiorSequencialOSProgramacao(
			Integer idProgramacaoRoteiro, Integer idEquipe)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		short retorno = 0;
		String consulta;

		try {
			consulta = "SELECT max(osProgramacao.nnSequencialProgramacao) "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "INNER JOIN osProgramacao.programacaoRoteiro prgRot "
					+ "INNER JOIN osProgramacao.equipe equipe "
					+ "WHERE equipe.id = :idEquipe and "
					+ "prgRot.id = :idProgramacaoRoteiro ";

			Short numeroSequencial = (Short) session.createQuery(consulta)
					.setInteger("idProgramacaoRoteiro", idProgramacaoRoteiro)
					.setInteger("idEquipe", idEquipe).setMaxResults(1)
					.uniqueResult();

			if (numeroSequencial != null && !numeroSequencial.equals("")) {
				retorno = numeroSequencial;
			}

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1190] Programao Automtica do Roteiro para Acompanhamento das OS
	 * 
	 * [SB0002] Inserir Ordem de Servio na Programao
	 * 
	 * @author Svio Luiz -
	 * @date 19/07/2011
	 */
	public void atualizarIndicadorOSProgramada(Integer idOrdemServico)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {
			String update = "update OrdemServico "
					+ "set orse_icprogramada = :indicadorProgramada, orse_tmultimaalteracao = :dataAtual "
					+ "where orse_id = :idOrdemServico";

			session.createQuery(update)
			.setInteger("idOrdemServico", idOrdemServico)
			.setShort("indicadorProgramada", ConstantesSistema.SIM)
			.setTimestamp("dataAtual", new Date()).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceo para a prxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sesso
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1190] Programao Automtica do Roteiro para Acompanhamento das OS
	 * 
	 * [SB0003] Programao Automtica das Ordens de Servio por Prioridade
	 * 
	 * @author Svio Luiz
	 * @date 19/07/2011
	 */
	public Collection<Object[]> pesquisarOSProgramacaoAutomatica(
			Integer idUnidadeOrganizacional) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection<Object[]> retorno = null;
		String consulta;

		try {
			consulta = "SELECT ordemServico.id," // 0
					+ "pgrCalLB.id, "// 1
					+ "pgrCalLC.id,  "// 2
					+ "rgat.nnDiametro, "// 3
					+ "rgat.quantidadeReiteracao, "// 4
					+ "rgat.id, "// 5
					+ "pgrCalST.id, "// 6
					+ "ordemServico.dataGeracao "// 7
					+ "FROM OrdemServico ordemServico "
					+ "INNER JOIN ordemServico.registroAtendimento rgat "
					+ "INNER JOIN ordemServico.unidadeAtual unidadeAtual "
					+ "INNER JOIN ordemServico.servicoTipo servicoTipo "
					+ "LEFT JOIN servicoTipo.programaCalibragem pgrCalST "
					+ "LEFT JOIN rgat.logradouroBairro logr_bairro "
					+ "LEFT JOIN logr_bairro.logradouro logradouroB "
					+ "LEFT JOIN logradouroB.programaCalibragem pgrCalLB "
					+ "LEFT JOIN rgat.logradouroCep logr_cep "
					+ "LEFT JOIN logr_cep.logradouro logradouroC "
					+ "LEFT JOIN logradouroC.programaCalibragem pgrCalLC "
					+ "WHERE ordemServico.situacao = :cdSituacao and "
					+ "unidadeAtual.id = :idUnidadeOrganizacional and "
					+ "ordemServico.indicadorProgramada = :indProgramada "
					+ "and servicoTipo.indicadorProgramacaoAutomatica = :indProgAutomatica ";

			retorno = session
					.createQuery(consulta)
					.setInteger("idUnidadeOrganizacional",
							idUnidadeOrganizacional)
							.setShort("indProgAutomatica", ConstantesSistema.SIM)
							.setShort("indProgramada", ConstantesSistema.NAO)
							.setShort("cdSituacao", OrdemServico.SITUACAO_PENDENTE)
							.list();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1190] Programao Automtica do Roteiro para Acompanhamento das OS
	 * 
	 * [SB0003] Programao Automtica das Ordens de Servio por Prioridade
	 * 
	 * @author Svio Luiz
	 * @date 19/07/2011
	 */
	public Object[] pesquisarDadosOSCalibragem(Integer idPriorizacaoTipo,
			Integer idOSProgramaCalibragem, Integer faixaPriorizacao)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Object[] retorno = null;
		String consulta;

		try {
			consulta = "SELECT oSProgramacaoCalibragem.peso," // 0
					+ "oSProgramacaoCalibragem.fator "// 1
					+ "FROM OSProgramacaoCalibragem oSProgramacaoCalibragem "
					+ "INNER JOIN oSProgramacaoCalibragem.priorizacaoTipo priorizacaoTipo "
					+ "WHERE priorizacaoTipo.id = :idPriorizacaoTipo ";

			if (idOSProgramaCalibragem != null
					&& !idOSProgramaCalibragem.equals("")) {
				consulta = consulta + " and oSProgramacaoCalibragem.id = "
						+ idOSProgramaCalibragem;
			}

			if (faixaPriorizacao != null && !faixaPriorizacao.equals("")) {
				consulta = consulta
						+ " and oSProgramacaoCalibragem.faixaInicial <= "
						+ faixaPriorizacao;
				consulta = consulta
						+ " and oSProgramacaoCalibragem.faixaFinal >= "
						+ faixaPriorizacao;
			}

			retorno = (Object[]) session.createQuery(consulta)
					.setInteger("idPriorizacaoTipo", idPriorizacaoTipo)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servio do Roteiro Programado
	 * 
	 * Pesquisa a tabela atendimentopublico.os_prg_acomp_servico retornando
	 * apenas as os que ainda no foram enviadas.
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param idEquipe
	 *            - id da equipe que ter� as os incluidas
	 * 
	 * @return Collection<OSProgramacaoAcompanhamentoServico> - Coleo com
	 *         todos as ordens de servico a serem incluidas na programao
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSProgramacaoAcompanhamentoServico> pesquisarOSAcompanhamentoServico(
			Integer idEquipe, Date dataRoteiro, boolean arquivoOnLine)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection<OSProgramacaoAcompanhamentoServico> retorno = null;
		String consulta;

		try {
			consulta = "select \n "
					+ "	osProgramacaoAcompanhamentoServico \n"
					+ "from \n "
					+ "   OSProgramacaoAcompanhamentoServico osProgramacaoAcompanhamentoServico \n "
					+ "   inner join fetch osProgramacaoAcompanhamentoServico.ordemServico ordemServico \n "
					+ "   left join fetch ordemServico.imovel imovel \n "
					+ "   left join fetch imovel.setorComercial setorComercial \n "
					+ "   left join fetch imovel.quadra quadra \n "
					+ "where \n "
					+ "   osProgramacaoAcompanhamentoServico.equipe.id = :idEquipe and \n "
					+ "   osProgramacaoAcompanhamentoServico.dataProgramacao = :dataRoteiro";

			if (arquivoOnLine) {
				consulta = consulta
						+ " and osProgramacaoAcompanhamentoServico.indicadorTrasmissaoOS = 2 ";
			}

			retorno = session.createQuery(consulta)
					.setInteger("idEquipe", idEquipe)
					.setDate("dataRoteiro", dataRoteiro).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servio do Roteiro Programado
	 * 
	 * Pesquisa a tabela atendimentopublico.os_at_prg_acomp_servico apenas as os
	 * que ainda no foram enviadas.
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param idOSProgramacaoAcompanhamentoServico
	 *            - id da ativdade programacao
	 * 
	 * @return Collection<OSAtividadeProgramacaoAcompanhamentoServico> - Coleo
	 *         com todas as atividades a serem incluidas na programao
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSAtividadeProgramacaoAcompanhamentoServico> pesquisarAtividadeOSAcompanhamentoServico(
			Integer idOSProgramacaoAcompanhamentoServico)
					throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Collection<OSAtividadeProgramacaoAcompanhamentoServico> retorno = null;
		String consulta;

		try {
			consulta = "select \n "
					+ "	osAtividadeProgramacaoAcompanhamentoServico \n"
					+ "from \n "
					+ "   OSAtividadeProgramacaoAcompanhamentoServico osAtividadeProgramacaoAcompanhamentoServico \n "
					+ "where \n "
					+ "   osAtividadeProgramacaoAcompanhamentoServico.indicadorTransmissaoOS = 2 and \n "
					+ "   osAtividadeProgramacaoAcompanhamentoServico.osProgramacaoAcompanhamentoServico.id = :idOSProgramacaoAcompanhamentoServico";

			retorno = session
					.createQuery(consulta)
					.setInteger("idOSProgramacaoAcompanhamentoServico",
							idOSProgramacaoAcompanhamentoServico).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1190] Programao Automtica do Roteiro para Acompanhamento das OS
	 * 
	 * [SB0004] Recuperar Equipe Pela Ordem de Servio
	 * 
	 * @author Svio Luiz
	 * @date 19/07/2011
	 */
	public Collection<Equipe> pequisarEquipesPorUnidade(Integer idUnidade)
			throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "SELECT eqpe " + "FROM Equipe eqpe "
					+ "INNER JOIN eqpe.unidadeOrganizacional unid "
					+ "WHERE eqpe.indicadorUso = 1 AND unid.id = :idUnidade "
					+ "ORDER BY eqpe.nome ";

			retorno = (Collection<Atividade>) session.createQuery(consulta)
					.setInteger("idUnidade", idUnidade).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1190] Programao Automtica do Roteiro para Acompanhamento das OS
	 * 
	 * [SB0004] Recuperar Equipe Pela Ordem de Servio
	 * 
	 * @author Svio Luiz
	 * @date 19/07/2011
	 */
	public Object[] pesquisarTempoMedioOSProgramacaoComDataRoteiroUnidade(
			Integer idProgramacaoRoteiro, Integer idEquipe)
					throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT equipe.cargaTrabalho,sum(servicoTp.tempoMedioExecucao) "
					+ "FROM OrdemServicoProgramacao osProgramacao "
					+ "INNER JOIN osProgramacao.equipe equipe  "
					+ "INNER JOIN osProgramacao.programacaoRoteiro progRoteiro "
					+ "INNER JOIN osProgramacao.ordemServico ordemServico "
					+ "INNER JOIN ordemServico.servicoTipo servicoTp "
					+ "WHERE progRoteiro.id = :idProgramacaoRoteiro AND "
					+ "equipe.id = :idEquipe AND "
					+ "osProgramacao.indicadorAcompanhamentoServico <> :indicadorAcompanhamentoServico "
					+ "GROUP BY equipe.cargaTrabalho ";

			retorno = (Object[]) session
					.createQuery(consulta)
					.setInteger("idProgramacaoRoteiro", idProgramacaoRoteiro)
					.setInteger("idEquipe", idEquipe)
					.setInteger(
							"indicadorAcompanhamentoServico",
							OrdemServicoProgramacao.INDICADOR_ACOMP_SERV_REALOCADA)
							.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1190] Programao Automtica do Roteiro para Acompanhamento das OS D
	 * 
	 * @author Svio Luiz
	 * @date 19/07/2011
	 */
	public Collection<Integer> pesquisarOSFatorPrioridadeDecrescente(
			Integer idUnidadeOrganizacional) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection<Integer> retorno = null;
		String consulta;

		try {
			consulta = "SELECT ordemServico.id " // 0
					+ "FROM OrdemServico ordemServico "
					+ "INNER JOIN ordemServico.unidadeAtual unidadeAtual "
					+ "INNER JOIN ordemServico.servicoTipo servicoTipo "
					+ "WHERE ordemServico.situacao = :cdSituacao and "
					+ "unidadeAtual.id = :idUnidadeOrganizacional and "
					+ "ordemServico.indicadorProgramada = :indProgramada and "
					+ "servicoTipo.indicadorProgramacaoAutomatica = :indProgAutomatica "
					+ "ORDER BY ordemServico.numeroFatorPrioridade desc,ordemServico.dataGeracao ";

			retorno = session
					.createQuery(consulta)
					.setInteger("idUnidadeOrganizacional",
							idUnidadeOrganizacional)
							.setShort("indProgAutomatica", ConstantesSistema.SIM)
							.setShort("indProgramada", ConstantesSistema.NAO)
							.setShort("cdSituacao", OrdemServico.SITUACAO_PENDENTE)
							.list();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspeo de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @date 26/07/2011
	 */
	public Integer pesquisaOrdemServicoNaoPendenteFazParteComando(
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Integer retorno = null;
		String consulta;

		try {
			consulta = "SELECT count(distinct orse.id) "
					+ " FROM OrdemServico orse "
					+ " WHERE orse.comandoOrdemSeletiva.id = :idComandoOrdemSeletiva "
					+ " and orse.situacao <> :pendente ";

			retorno = (Integer) session
					.createQuery(consulta)
					.setInteger("idComandoOrdemSeletiva",
							idComandoOrdemSeletiva)
							.setShort("pendente", OrdemServico.SITUACAO_PENDENTE)
							.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;

	}

	/**
	 * [UC1190] Programao Automtica do Roteiro para Acompanhamento das OS
	 * 
	 * @author Svio Luiz
	 * @date 19/07/2011
	 */
	public void excluirOSProgramadas(Integer idUnidadeOrganizacional,
			Date dataProgramacao) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			String consulta = "";
			Collection colecaoIdsOrdemServico = null;

			Date dataInicio = Util.formatarDataInicial(dataProgramacao);
			Date dataFim = Util.formatarDataFinal(dataProgramacao);

			consulta = "select orse.id "
					+ "from OrdemServicoProgramacao osPrograma "
					+ "inner join osPrograma.programacaoRoteiro programacaRot "
					+ "inner join programacaRot.unidadeOrganizacional unid "
					+ "inner join osPrograma.ordemServico orse "
					+ "WHERE unid.id= :idUnidadeOrganizacional and "
					+ "programacaRot.dataRoteiro BETWEEN :dataRoteiroInicial AND :dataRoteiroFinal and "
					+ "osPrograma.indicadorAcompanhamentoServico = :icAcompanhamentoServico";

			colecaoIdsOrdemServico = session
					.createQuery(consulta)
					.setInteger("idUnidadeOrganizacional",
							idUnidadeOrganizacional)
							.setTimestamp("dataRoteiroInicial", dataInicio)
							.setTimestamp("dataRoteiroFinal", dataFim)
							.setShort("icAcompanhamentoServico", ConstantesSistema.NAO)
							.list();

			if (colecaoIdsOrdemServico == null
					|| colecaoIdsOrdemServico.isEmpty()) {
				colecaoIdsOrdemServico = new ArrayList();
			}

			// pesquisa as ordens de servi篠dos arquivos que est㯠com a
			// situa磯 de "LIBERADO"
			consulta = "select osProgramaAcompServico.ordemServico.id "
					+ "from OSProgramacaoAcompanhamentoServico osProgramaAcompServico "
					+ "inner join osProgramaAcompServico.arquivoTextoAcompanhamentoServico arqTxtAcompServ "
					+ "inner join arqTxtAcompServ.equipe eqpe "
					+ "inner join eqpe.unidadeOrganizacional unid "
					+ "WHERE unid.id= :idUnidadeOrganizacional and "
					+ "osProgramaAcompServico.dataProgramacao = :dataProgramacao and "
					+ "arqTxtAcompServ.situacaoTransmissaoLeitura.id = :idSituacaoLeitura";

			Collection colecaoIdsOrdemServicoArquivo = session
					.createQuery(consulta)
					.setInteger("idUnidadeOrganizacional",
							idUnidadeOrganizacional)
							.setDate("dataProgramacao", dataProgramacao)
							.setInteger("idSituacaoLeitura",
									SituacaoTransmissaoLeitura.LIBERADO).list();

			if (colecaoIdsOrdemServicoArquivo != null
					&& !colecaoIdsOrdemServicoArquivo.isEmpty()) {
				colecaoIdsOrdemServico.addAll(colecaoIdsOrdemServicoArquivo);
			}

			if (colecaoIdsOrdemServico != null
					&& !colecaoIdsOrdemServico.isEmpty()) {

				String atualizarOS = "";

				atualizarOS = "update gcom.atendimentopublico.ordemservico.OrdemServico "
						+ "set orse_icprogramada = :icprogramadaNao,orse_tmultimaalteracao = :ultimaAlteracao ";

				atualizarOS = atualizarOS
						+ " where orse_id in (:colecaoIdsOrdemServico) ";

				session.createQuery(atualizarOS)
				.setShort("icprogramadaNao", ConstantesSistema.NAO)
				.setTimestamp("ultimaAlteracao", new Date())
				.setParameterList("colecaoIdsOrdemServico",
						colecaoIdsOrdemServico).executeUpdate();

				// pesquisa o id da programacao
				consulta = "SELECT proRot.id "
						+ "FROM ProgramacaoRoteiro proRot "
						+ "INNER JOIN proRot.unidadeOrganizacional unidade "
						+ "WHERE unidade.id = :idUnidadeOrganizacional and "
						+ "   proRot.dataRoteiro BETWEEN :dataRoteiroInicial AND :dataRoteiroFinal ";

				Collection idsProgramacao = session
						.createQuery(consulta)
						.setInteger("idUnidadeOrganizacional",
								idUnidadeOrganizacional)
								.setTimestamp("dataRoteiroInicial", dataInicio)
								.setTimestamp("dataRoteiroFinal", dataFim).list();

				if (idsProgramacao != null && !idsProgramacao.isEmpty()) {
					String sql = "DELETE FROM gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao "
							+ "WHERE orse_id in (:colecaoIdsOrdemServico) and pgrt_id in (:idsProgramacao) ";

					session.createQuery(sql)
					.setParameterList("colecaoIdsOrdemServico",
							colecaoIdsOrdemServico)
							.setParameterList("idsProgramacao", idsProgramacao)
							.executeUpdate();
				}

				if (idsProgramacao != null && !idsProgramacao.isEmpty()) {
					String sql = "DELETE FROM gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro "
							+ "WHERE pgrt_id in (:idsProgramacao) ";

					session.createQuery(sql)
					.setParameterList("idsProgramacao", idsProgramacao)
					.executeUpdate();
				}

			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0412] Manter Tipo de Servio
	 * 
	 * Metodo responsvel por deletar motivos de encerramento a partir de um
	 * tipo de Servio
	 * 
	 * @author Raimundo Martins
	 * @date 26/07/2011
	 * 
	 * @param idServicoTipo
	 * 
	 */
	public void removerServicoTipoMotivoEncerramento(Integer idServicoTipo)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {

			String delete = "delete ServicoTipoMotivoEncerramento "
					+ " where SVTP_ID = :idServicoTipo ";

			session.createQuery(delete)
			.setInteger("idServicoTipo", idServicoTipo).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceo para a prxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sesso
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1190] Programao Automtica do Roteiro para Acompanhamento das OS
	 * 
	 * [SB0003] Programao Automtica das Ordens de Servio por Prioridade
	 * 
	 * @author Svio Luiz
	 * @date 19/07/2011
	 */
	public void atualizarFatorPrioridadeOS(Integer idOrdemServico,
			Integer fatorPrioridade) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {

			String atualizarOS = "";

			atualizarOS = "update gcom.atendimentopublico.ordemservico.OrdemServico "
					+ "set orse_nnfatorprioridade = :fatorPrioridade,orse_tmultimaalteracao = :ultimaAlteracao ";

			atualizarOS = atualizarOS + " where orse_id = :idOrdemServico ";

			session.createQuery(atualizarOS)
			.setTimestamp("ultimaAlteracao", new Date())
			.setInteger("idOrdemServico", idOrdemServico)
			.setInteger("fatorPrioridade", fatorPrioridade)
			.executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceo para a prxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sesso
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1190] Programao Automtica do Roteiro para Acompanhamento das OS
	 * 
	 * [SB0003] Programao Automtica das Ordens de Servio por Prioridade
	 * 
	 * @author Svio Luiz
	 * @date 19/07/2011
	 */
	public Integer pesquisarQuantidadeRAReativacao(Integer idRA)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		Integer qdtRAReativacao = null;
		try {

			String consulta = "SELECT count(regAtend.id) " // 0
					+ "FROM RegistroAtendimento regAtend "
					+ "WHERE regAtend.registroAtendimentoReativacao.id = :idRA ";

			qdtRAReativacao = (Integer) session.createQuery(consulta)
					.setInteger("idRA", idRA).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceo para a prxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sesso
			HibernateUtil.closeSession(session);
		}
		return qdtRAReativacao;
	}

	/**
	 * [UC1190] Programao Automtica do Roteiro para Acompanhamento das OS
	 * 
	 * 
	 * @author Svio Luiz
	 * @date 30/07/2011
	 */
	public Collection pequisarUnidadesOrganizacionaisdasEquipes()
			throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "SELECT distinct(unid.id) " + "FROM Equipe eqpe "
					+ "INNER JOIN eqpe.unidadeOrganizacional unid "
					+ "WHERE eqpe.indicadorUso = 1 ";

			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1199] Acompanhamento de Arquivos de Roteiro
	 * 
	 * @author Thlio Arajo
	 * @date 28/07/2011
	 * 
	 * @param ids
	 * @param situacaoNova
	 */
	public void atualizarArquivoTextoAcompArqRoteiro(Integer id,
			Integer situacaoNova) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {
			String sql = "update ArquivoTextoAcompanhamentoServico arq "
					+ " set situacaoTransmissaoLeitura = :idSituacaoTransmissaoLeituraNova, "
					+ " dataUltimaAlteracao = :data" + " where arq.id = :id";

			session.createQuery(sql)
			.setInteger("id", id)
			.setInteger("idSituacaoTransmissaoLeituraNova",
					situacaoNova).setTimestamp("data", new Date())
					.executeUpdate();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * [UC1192] Movimentar OS Seletiva de Inspeo de Anormalidade Verificar se
	 * ordem de servio que faz parte do comando ja esta encerrada
	 * 
	 * @author Vivianne Sousa
	 * @date 02/08/2011
	 */
	public Collection verificaSeOSJaEncerrada(List<Integer> numerosOSPesquisar)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta;

		try {
			consulta = "SELECT distinct(orse.id) " + " FROM OrdemServico orse "
					+ " WHERE orse.situacao = :encerrada "
					+ " AND orse.id in (:numerosOSPesquisar) ";

			retorno = session.createQuery(consulta)
					.setShort("encerrada", OrdemServico.SITUACAO_ENCERRADO)
					.setParameterList("numerosOSPesquisar", numerosOSPesquisar)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;

	}

	public Collection<Integer> pesquisarIdsEquipesPorUnidadeOrganizacional(
			Integer idUnidadeOrganizacional) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = "SELECT eqpe.id "
					+ "  FROM Equipe eqpe "
					+ "INNER JOIN eqpe.unidadeOrganizacional unid "
					+ "WHERE eqpe.indicadorUso = 1 AND unid.id = :idUnidade AND eqpe.indicadorProgramacaoAutomatica = :indProgrAutomatica "
					+ "ORDER BY eqpe.nome ";

			retorno = (Collection<Integer>) session.createQuery(consulta)
					.setInteger("idUnidade", idUnidadeOrganizacional)
					.setShort("indProgrAutomatica", ConstantesSistema.SIM)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servio do Roteiro Programado
	 * 
	 * Exclue o arquivo de uma equipe para uma determinada data
	 * 
	 * 
	 * @date 03/08/2011
	 * @author Bruno Barros
	 * 
	 * @param idEquipe
	 * @param dataProgramacao
	 * @throws ErroRepositorioException
	 */
	public void excluirDadosArquivoAcompanharServicoRoteiroProgramado(
			Integer idArquivoTexto) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {

			String sql = "delete \n" + "from \n "
					+ "	ArquivoTextoAcompanhamentoServico atas \n "
					+ "where \n " + "	atas.id = :idArquivoTexto";

			session.createQuery(sql)
			.setInteger("idArquivoTexto", idArquivoTexto)
			.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servio do Roteiro Programado
	 * 
	 * Exclue o as os programadas
	 * 
	 * 
	 * @date 03/08/2011
	 * @author Bruno Barros
	 * 
	 * @param idEquipe
	 * @param dataProgramacao
	 * @throws ErroRepositorioException
	 */
	public void excluirOSProgramadasAcompanharServicoRoteiroProgramado(
			Integer id) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {

			String sql = "delete \n" + "from \n "
					+ "   OSProgramacaoAcompanhamentoServico osProgramacao \n "
					+ "where " + "   osProgramacao.id = :id \n ";

			session.createQuery(sql).setInteger("id", id).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servio do Roteiro Programado
	 * 
	 * Exclue o as os programadas
	 * 
	 * 
	 * @date 03/08/2011
	 * @author Bruno Barros
	 * 
	 * @param idEquipe
	 * @param dataProgramacao
	 * @throws ErroRepositorioException
	 */
	public void excluirOrdemServicoAtividadeAcompanharServicoRoteiroProgramado(
			Integer id) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {

			String sql = "delete \n"
					+ "from \n "
					+ "   OSAtividadeProgramacaoAcompanhamentoServico ordem \n "
					+ "where "
					+ "   ordem.osProgramacaoAcompanhamentoServico.id = :id \n ";

			session.createQuery(sql).setInteger("id", id).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 06/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdensServicoFiscalizacao(int tipoRelatorio,
			String periodoInicial, String periodoFinal,
			String idGerenciaRegional, String idUnidadeNegocios,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String situacaoOS, String idOSReferidaRetornoTipo,
			String aceitacaoDaOS) throws ErroRepositorioException {

		List<OrdemServico> retorno = null;

		Session session = HibernateUtil.getSession();
		String consultaImovel = "";
		String consultaRA = "";
		String clausulasFiltro = "";
		try {
			consultaImovel = "select \n "
					+ "	ordemServico \n "
					+ "from \n "
					+ "   OrdemServico ordemServico \n "
					+ "   inner join fetch ordemServico.osReferencia	osReferencia \n "
					+ "   inner join fetch ordemServico.servicoTipo servicoTipo \n "
					+ "   inner join fetch servicoTipo.servicoTipoReferencia servicoTipoReferencia \n "
					+ "   inner join fetch osReferencia.imovel imovel \n "
					+ "   inner join fetch imovel.localidade localidade \n "
					+ "   inner join fetch localidade.gerenciaRegional gerenciaRegional \n "
					+ "   inner join fetch localidade.unidadeNegocio unidadeNegocio \n "
					+ "   left join fetch ordemServico.atendimentoMotivoEncerramento atendimentoMotivoEncerramento \n ";

			consultaRA = "select \n "
					+ "	ordemServico \n "
					+ "from \n "
					+ "   OrdemServico ordemServico \n "
					+ "   inner join fetch ordemServico.osReferencia	osReferencia \n "
					+ "   inner join fetch ordemServico.servicoTipo servicoTipo \n "
					+ "   inner join fetch servicoTipo.servicoTipoReferencia servicoTipoReferencia \n "
					+ "   inner join fetch osReferencia.registroAtendimento registroAtendimento \n "
					+ "   inner join fetch registroAtendimento.localidade localidade \n "
					+ "   inner join fetch localidade.gerenciaRegional gerenciaRegional \n "
					+ "   inner join fetch localidade.unidadeNegocio unidadeNegocio \n "
					+ "   left join fetch ordemServico.atendimentoMotivoEncerramento atendimentoMotivoEncerramento \n ";

			if (idGerenciaRegional != null && !idGerenciaRegional.equals("")) {
				clausulasFiltro = clausulasFiltro
						+ " and gerenciaRegional.id = " + idGerenciaRegional
						+ " \n ";
			}

			if (idUnidadeNegocios != null && !idUnidadeNegocios.equals("")) {
				clausulasFiltro = clausulasFiltro + " and unidadeNegocio.id = "
						+ idUnidadeNegocios + " \n ";
			}

			if (idLocalidadeInicial != null && !idLocalidadeInicial.equals("")
					&& idLocalidadeFinal != null
					&& !idLocalidadeFinal.equals("")) {
				clausulasFiltro = clausulasFiltro
						+ " and localidade.id between " + idLocalidadeInicial
						+ " and " + idLocalidadeFinal + " \n ";
			}

			if (situacaoOS != null && !situacaoOS.equals("")
					&& !situacaoOS.equals("-1")) {
				clausulasFiltro = clausulasFiltro
						+ " and ordemServico.situacao = " + situacaoOS + " \n ";
			}

			if (idOSReferidaRetornoTipo != null
					&& !idOSReferidaRetornoTipo.equals("")) {
				consultaImovel = consultaImovel
						+ "   inner join fetch ordemServico.osReferidaRetornoTipo osReferidaRetornoTipo \n ";
				consultaRA = consultaRA
						+ "   inner join fetch ordemServico.osReferidaRetornoTipo osReferidaRetornoTipo \n ";

				clausulasFiltro = clausulasFiltro
						+ " and osReferidaRetornoTipo.id = "
						+ idOSReferidaRetornoTipo + " \n ";
			}

			if (aceitacaoDaOS != null && !aceitacaoDaOS.equals("")
					&& !aceitacaoDaOS.equals("3")) {

				if (consultaImovel.contains("osReferidaRetornoTipo") == false) {
					consultaImovel = consultaImovel
							+ "   inner join fetch ordemServico.osReferidaRetornoTipo osReferidaRetornoTipo \n ";
					consultaRA = consultaRA
							+ "   inner join fetch ordemServico.osReferidaRetornoTipo osReferidaRetornoTipo \n ";
				}

				clausulasFiltro = clausulasFiltro
						+ " and osReferidaRetornoTipo.indicadorDeferimento = "
						+ aceitacaoDaOS + " \n ";
			} else {
				consultaImovel = consultaImovel
						+ "   left join fetch ordemServico.osReferidaRetornoTipo osReferidaRetornoTipo \n ";
				consultaRA = consultaRA
						+ "   left join fetch ordemServico.osReferidaRetornoTipo osReferidaRetornoTipo \n ";
			}

			consultaImovel += " \n where ordemServico.osReferencia is not null and servicoTipoReferencia.indicadorFiscalizacao = 1 and ordemServico.dataGeracao between :periodoInicial and :periodoFinal"
					+ clausulasFiltro;
			consultaRA += " \n where ordemServico.imovel is null and ordemServico.osReferencia is not null and servicoTipoReferencia.indicadorFiscalizacao = 1 and ordemServico.dataGeracao between :periodoInicial and :periodoFinal"
					+ clausulasFiltro;

			consultaImovel += "  \n order by ordemServico.dataGeracao, gerenciaRegional.id, unidadeNegocio.id, localidade.id, ordemServico.osReferidaRetornoTipo.id, ordemServico.atendimentoMotivoEncerramento.id ";

			String[] mesAnoArray = periodoFinal.split("/");
			String ultimoDiaMEs = Util.obterUltimoDiaMes(
					Integer.parseInt(mesAnoArray[0]),
					Integer.parseInt(mesAnoArray[1]));
			retorno = (List<OrdemServico>) session
					.createQuery(consultaImovel)
					.setDate(
							"periodoInicial",
							Util.formatarMesAnoParaData(periodoInicial, "01",
									"00:00:00"))
									.setDate(
											"periodoFinal",
											Util.formatarMesAnoParaData(periodoFinal,
													ultimoDiaMEs, "23:59:59")).list();
			if (retorno == null || retorno.isEmpty()) {
				retorno = new ArrayList<OrdemServico>();
			} else {
				String idsOS = "";
				for (OrdemServico ordem : retorno) {
					idsOS += ordem.getId().intValue() + ",";
				}
				idsOS = idsOS.substring(0, idsOS.length() - 1);
				consultaRA += " and ordemServico.id not in (" + idsOS + ") ";
			}

			consultaRA += "  \n order by ordemServico.dataGeracao, gerenciaRegional.id, unidadeNegocio.id, localidade.id, ordemServico.osReferidaRetornoTipo.id, ordemServico.atendimentoMotivoEncerramento.id ";
			Collection<OrdemServico> colecaoRA = (Collection<OrdemServico>) session
					.createQuery(consultaRA)
					.setDate(
							"periodoInicial",
							Util.formatarMesAnoParaData(periodoInicial, "01",
									"00:00:00"))
									.setDate(
											"periodoFinal",
											Util.formatarMesAnoParaData(periodoFinal,
													ultimoDiaMEs, "23:59:59")).list();
			retorno.addAll(colecaoRA);
			Collections.sort(retorno, new ComparatorOrdemServicoFiscalizacao());

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1199] - Acompanhar Arquivos de Roteiro
	 * 
	 * Pesquisa a(s) equipe(s) da OS Programacao Acompanhamento Servico
	 * filtrando pelo identificador da Ordem de Servio
	 * 
	 * @author Raimundo Martins
	 * @date 09/08/2011
	 * 
	 * @param idOrdemServio
	 * 
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */

	public Collection<Integer> pesquisarEquipeOSProgramacaoAcompServicoPorIdOrdemServico(
			Integer idOrdemServico) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Collection retorno;
		String consulta;

		try {
			consulta = "select " + "	ospr.equipe.id " + "from "
					+ "   OSProgramacaoAcompanhamentoServico ospr "
					+ "where ospr.ordemServico.id = :idOrdemServico";

			retorno = (Collection<Integer>) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico).list();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1205] - Remanejar Ordem de Servico
	 * 
	 * Pesquisa OS Programacao Acompanhamento Servico por Equipe
	 * 
	 * @author Thlio Arajo
	 * @date 22/08/2011
	 * 
	 * @param idArqTextoAcompServico
	 * @return Date - data da OS Programacao Acompanhamento Servico
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOSProgramacaoAcompServicoPorEquipeOS(
			Integer idOrdemServico, Date dataProgramacao, Integer idEquipe)
					throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Collection retorno;
		String consulta;

		try {
			consulta = "select"
					+ "	ospr "
					+ "from "
					+ "   OSProgramacaoAcompanhamentoServico ospr "
					+ "where ospr.ordemServico = :idOrdemServico and ospr.equipe = :idEquipe and ospr.dataProgramacao between :dataProgramacaoInicial and :dataProgramacaoFinal";

			retorno = session
					.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setTimestamp("dataProgramacaoInicial",
							Util.formatarDataInicial(dataProgramacao))
							.setTimestamp("dataProgramacaoFinal",
									Util.formatarDataFinal(dataProgramacao))
									.setInteger("idEquipe", idEquipe).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * -- [UC1190] Programa磯 Automᴩca do Roteiro para Acompanhamento das OS
	 * 
	 * @author Sᶩo Luiz
	 * @date 19/07/2011
	 */
	public Integer pesquisarOSAcompServicoAtual(Integer idOrdemServico,
			Date dataProgramacao) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Integer retorno = null;
		String consulta;

		try {
			consulta = "SELECT osProAcomServ.id "
					+ " FROM OSProgramacaoAcompanhamentoServico osProAcomServ "
					+ " WHERE osProAcomServ.dataProgramacao = :dataProgramacao and "
					+ " osProAcomServ.ordemServico.id = :idOrdemServico "
					+ " AND osProAcomServ.indicadorExcluido <> :indicadorExcluido";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setDate("dataProgramacao", dataProgramacao)
					.setShort("indicadorExcluido", ConstantesSistema.SIM)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * -- [UC1190] Programa磯 Automᴩca do Roteiro para Acompanhamento das OS
	 * 
	 * @author Sᶩo Luiz
	 * @date 19/07/2011
	 */
	public Integer pesquisarOSProgramacaoAtual(Integer idOrdemServico,
			Date dataProgramacao) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Integer retorno = null;
		String consulta;

		Date dataRoteiroInicial = Util.formatarDataInicial(dataProgramacao);
		Date dataRoteiroFinal = Util.formatarDataFinal(dataProgramacao);

		try {
			consulta = "select \n "
					+ "	osProgramacao.id \n "
					+ "from \n "
					+ "   OrdemServicoProgramacao osProgramacao \n "
					+ "   inner join osProgramacao.programacaoRoteiro prgRot \n"
					+ "where \n "
					+ "   osProgramacao.ordemServico.id = :idOrdemServico and"
					+ "   prgRot.dataRoteiro BETWEEN :dataRoteiroInicial AND :dataRoteiroFinal ";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setTimestamp("dataRoteiroInicial", dataRoteiroInicial)
					.setTimestamp("dataRoteiroFinal", dataRoteiroFinal)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 06/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public OrdemServico pesquisarOrdemServicoFiscalizada(Integer idOrdemServico)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		OrdemServico retorno = null;
		String consulta;

		try {
			consulta = "select \n "
					+ "	ordemServico \n "
					+ "from \n "
					+ "   OrdemServico ordemServico \n "
					+ "   left join fetch ordemServico.imovel imovel \n "
					+ "   left join fetch imovel.localidade localidade \n "
					+ "   left join fetch localidade.gerenciaRegional gerenciaRegional \n "
					+ "   left join fetch localidade.unidadeNegocio unidadeNegocio \n "
					+

					"   left join fetch ordemServico.registroAtendimento registroAtendimento \n "
					+ "   left join fetch registroAtendimento.localidade localidadeRA \n "
					+ "   left join fetch localidadeRA.gerenciaRegional gerenciaRegionalRA \n "
					+ "   left join fetch localidadeRA.unidadeNegocio unidadeNegocioRA \n "
					+

					"   left join fetch ordemServico.atendimentoMotivoEncerramento atendimentoMotivoEncerramento \n "
					+ "   left join fetch ordemServico.osReferidaRetornoTipo osReferidaRetornoTipo \n "
					+ "   left join fetch ordemServico.servicoTipo servicoTipo \n "
					+ "where ordemServico.id = :idOrdemServico";

			Collection collRetorno = (Collection<Integer>) session
					.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico).list();

			if (collRetorno != null && !collRetorno.isEmpty()) {
				retorno = (OrdemServico) collRetorno.iterator().next();
			}

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;

	}

	/**
	 * [UC1199] Acompanhamento de Arquivos de Roteiro
	 * 
	 * @author Thlio Arajo
	 * @date 27/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSProgramacaoAcompanhamentoServico> pesquisarOSProgramacaoAcompArquivoComDataRoteiroIdEquipe(
			Date dataRoteiro, Integer idArquivo)
					throws ErroRepositorioException {

		Collection<OSProgramacaoAcompanhamentoServico> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT osProgramacao "
					+ "FROM OSProgramacaoAcompanhamentoServico osProgramacao "
					+ "WHERE osProgramacao.arquivoTextoAcompanhamentoServico = :idArquivo "
					+ "AND osProgramacao.dataProgramacao  BETWEEN :dataProgramacaoInicial AND :dataProgramacaoFinal "
					+ "AND osProgramacao.indicadorExcluido = :indicadorExcluido";

			retorno = (Collection<OSProgramacaoAcompanhamentoServico>) session
					.createQuery(consulta)
					.setInteger("idArquivo", idArquivo)
					.setTimestamp("dataProgramacaoInicial",
							Util.formatarDataInicial(dataRoteiro))
							.setTimestamp("dataProgramacaoFinal",
									Util.formatarDataFinal(dataRoteiro))
									.setShort("indicadorExcluido", ConstantesSistema.NAO)
									.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1199] Acompanhamento de Arquivos de Roteiro
	 * 
	 * @author Thlio Arajo
	 * @date 27/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSProgramacaoAcompanhamentoServico> pesquisarOSProgramacaoAcompServicoComSequencialMaior(
			Integer numeroOS, Date dataRoteiro, Integer idArquivo,
			short sequencialReferencia) throws ErroRepositorioException {

		Collection<OSProgramacaoAcompanhamentoServico> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT osProgramacao "
					+ "FROM OSProgramacaoAcompanhamentoServico osProgramacao "
					+ "INNER JOIN osProgramacao.ordemServico os  "
					+ "WHERE osProgramacao.arquivoTextoAcompanhamentoServico = :idArquivo "
					+ "AND os.id = :numeroOS "
					+ "AND osProgramacao.sequencialProgramacao > :sequencialReferencia "
					+ "AND osProgramacao.dataProgramacao BETWEEN :dataProgramacaoRoteiroInicial AND :dataProgramacaoRoteiroFinal "
					+ "AND osProgramacao.indicadorExcluido = :indicadorExcluido";

			retorno = (Collection<OSProgramacaoAcompanhamentoServico>) session
					.createQuery(consulta)
					.setInteger("numeroOS", numeroOS)
					.setInteger("idArquivo", idArquivo)
					.setShort("sequencialReferencia", sequencialReferencia)
					.setTimestamp("dataProgramacaoRoteiroInicial",
							Util.formatarDataInicial(dataRoteiro))
							.setTimestamp("dataProgramacaoRoteiroFinal",
									Util.formatarDataFinal(dataRoteiro))
									.setShort("indicadorExcluido", ConstantesSistema.SIM)
									.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1205] - Remanejar ordem de servico
	 * 
	 * Pesquisa as OS que ainda n㯠foram enviadas para uma equipe em uma
	 * determinada data
	 * 
	 * @author Thlio Arajo
	 * @date 06/07/2011
	 * 
	 * @param idEquipe
	 *            - id da equipe que terᠡs os incluidas
	 * 
	 * @return Collection<OSProgramacao> - Cole磯 com todos as ordens de
	 *         servico a serem incluidas na programa磯
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> pesquisarOSNaoEnviadasRemanejadas(
			Integer idEquipe, Date dataProgramacao, Integer idOrdemServico)
					throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Collection<OrdemServicoProgramacao> retorno = null;
		String consulta;

		Date dataRoteiroInicial = Util.formatarDataInicial(dataProgramacao);
		Date dataRoteiroFinal = Util.formatarDataFinal(dataProgramacao);

		try {
			consulta = "select \n "
					+ "	osProgramacao \n "
					+ "from \n "
					+ "   OrdemServicoProgramacao osProgramacao \n "
					+ "   inner join fetch osProgramacao.ordemServico ordem \n"
					+ "   left join fetch ordem.imovel imovel \n"
					+ "   left join fetch imovel.ligacaoAgua ligacaoAgua \n"
					+ "   left join fetch imovel.ligacaoAguaSituacao ligacaoAguaSituacao \n"
					+ "   left join fetch imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao \n"
					+ "   left join fetch ligacaoAgua.hidrometroInstalacaoHistorico hih \n"
					+ "   left join fetch hih.hidrometro hid \n"
					+ "   left join fetch hid.hidrometroCapacidade \n"
					+ "   inner join fetch ordem.registroAtendimento registro \n"
					+ "   inner join fetch osProgramacao.programacaoRoteiro prgRot \n"
					+

					"where \n "
					+ "   osProgramacao.indicadorAcompanhamentoServico in (2,3) and \n "
					+ "   osProgramacao.equipe.id = :idEquipe and"
					+ "   osProgramacao.ordemServico = :idOrdemServico and"
					+ "   prgRot.dataRoteiro BETWEEN :dataRoteiroInicial AND :dataRoteiroFinal ";

			retorno = session.createQuery(consulta)
					.setInteger("idEquipe", idEquipe)
					.setTimestamp("dataRoteiroInicial", dataRoteiroInicial)
					.setTimestamp("dataRoteiroFinal", dataRoteiroFinal)
					.setInteger("idOrdemServico", idOrdemServico).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1199] - Acompanhamento de Arquivos de Roteiro
	 * 
	 * Pesquisa os id's das equipes que ainda possuem OS, para a data informada,
	 * que ainda n㯠foram encaminhadas para o campo.
	 * 
	 * @author Thlio Arajo
	 * @date 06/07/2011
	 * 
	 * @param dataRoteiro
	 *            - Data para a pesquisa das OS
	 * 
	 * @return Collection<Integer> - Cole磯 com todos os ID's das equipes.
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Equipe> pesquisarEquipesOSNaoEnviadasProgramadas(
			Integer idUnidadeLotacao, Date dataRoteiro, Integer idEquipe)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection<Equipe> retorno = null;
		String consulta;

		try {
			consulta = "select \n "
					+ "	distinct osProgramacao.equipe \n "
					+ "from \n "
					+ "   OrdemServicoProgramacao osProgramacao \n "
					+ "   inner join osProgramacao.programacaoRoteiro programacaoRoteiro \n "
					+ "where \n "
					+ "   programacaoRoteiro.dataRoteiro between :dataRoteiroInicial and :dataRoteiroFinal and \n"
					+ "   programacaoRoteiro.unidadeOrganizacional = :idUnidadeLotacao and \n"
					+ "   osProgramacao.equipe.id <> :idEquipe \n"
					+ "   order by osProgramacao.equipe.nome";

			retorno = session
					.createQuery(consulta)
					.setTimestamp("dataRoteiroInicial",
							Util.formatarDataInicial(dataRoteiro))
							.setTimestamp("dataRoteiroFinal",
									Util.formatarDataFinal(dataRoteiro))
									.setInteger("idUnidadeLotacao", idUnidadeLotacao)
									.setInteger("idEquipe", idEquipe).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1199] - Acompanhamento de Arquivos de Roteiro
	 * 
	 * Pesquisa o Arquivo Texto do Acompanhamento de Servi篠por Equipe e Data
	 * Roteiro
	 * 
	 * @author Thlio Arajo
	 * @date 06/07/2011
	 * 
	 * @param idEquipe
	 *            - Identificador da equipe
	 * @param dataRoteiro
	 *            - Data do roteiro a ser pesquisado
	 * 
	 * @return Integer - Numero do imei da equipe informada
	 * 
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoAcompanhamentoServico pesquisarArquivoTextoAcompanhamentoServicoEquipe(
			Integer idEquipe, Date dataRoteiro) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		ArquivoTextoAcompanhamentoServico retorno;
		String consulta;

		try {
			consulta = "select \n " + "	atas " + "from \n "
					+ "	ArquivoTextoAcompanhamentoServico atas \n "
					+ "inner join fetch \n" + "   atas.equipe \n"
					+ "inner join fetch \n"
					+ "   atas.situacaoTransmissaoLeitura \n" + "where \n "
					+ "	atas.equipe.id = :idEquipe and \n "
					+ "	atas.dataProgramacao = :dataRoteiro";

			retorno = (ArquivoTextoAcompanhamentoServico) session
					.createQuery(consulta).setInteger("idEquipe", idEquipe)
					.setDate("dataRoteiro", dataRoteiro).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UCXXX] - Retornar Mensagem Cadastrada para Equipe
	 * 
	 * @author Thlio Arajo
	 * @date 08/09/2011
	 * 
	 * @param idArquivo
	 *            - id do Arquivo a ser pesquisada a mensagem
	 * 
	 * @return MensagemAcompanhamentoServico - Objeto de Mensagem
	 * 
	 * @throws ErroRepositorioException
	 */
	public MensagemAcompanhamentoServico retornaMensagemAcompanhamentoArquivosRoteiroImei(
			Integer idArquivo) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		MensagemAcompanhamentoServico retorno;
		String consulta;

		try {
			consulta = "select \n "
					+ "	msgem "
					+ "from \n "
					+ "	MensagemAcompanhamentoServico msgem \n "
					+ "where \n "
					+ "	msgem.arquivoTextoAcompanhamentoServico.id = :idArquivo and \n "
					+ "	msgem.indicadorSituacao = :indicadorSituacao";

			retorno = (MensagemAcompanhamentoServico) session
					.createQuery(consulta)
					.setInteger("idArquivo", idArquivo)
					.setInteger("indicadorSituacao",
							ConstantesSistema.NAO.intValue()).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UCXXX] - Retornar Mensagem Cadastrada para Equipe
	 * 
	 * Pesquisar o id do Arquivo Texto do Acompanhamento de Servi篿
	 * 
	 * @author Thlio Arajo
	 * @date 15/09/2011
	 * 
	 * @param imei
	 * @param dataRoteiro
	 *            - Data do roteiro a ser pesquisado
	 * 
	 * @return Integer - idArquivo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdArquivoTextoAcompanhamentoServicoImei(long imei)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Integer retorno;
		String consulta;

		try {
			consulta = "select \n " + "	atas.id " + "from \n "
					+ "	ArquivoTextoAcompanhamentoServico atas \n "
					+ "where \n " + "	atas.imei = :imei and \n "
					+ "	atas.dataProgramacao = :dataRoteiro";

			retorno = (Integer) session.createQuery(consulta)
					.setLong("imei", imei).setDate("dataRoteiro", new Date())
					.setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public void atualizarSituacaoArquivoTextoAcompanhamentoServico(
			Integer equipe, short situacao) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String atualizarOS;
		Query query;

		try {

			atualizarOS = "update "
					+ "	gcom.atendimentopublico.ordemservico.ArquivoTextoAcompanhamentoServico "
					+ "set " + "   sitl_id = :situacao " + "where "
					+ "   eqpe_id = :equipe and "
					+ "   txac_dtprogramacao = :dtProgramacao";

			session.createQuery(atualizarOS).setShort("situacao", situacao)
			.setInteger("equipe", equipe)
			.setDate("dtProgramacao", new Date()).executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public void atualizarSituacaoProgramacaoOrdemServico(int numeroOS,
			short situacao) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String atualizarOS;
		Query query;

		try {

			atualizarOS = "update "
					+ "	gcom.atendimentopublico.ordemservico.OSProgramacaoAcompanhamentoServico "
					+ "set " + "   osst_id = :situacao " + "where "
					+ "   orse_id = :numeroOS and "
					+ "   osas_dtprogramacao = :dtProgramacao";

			session.createQuery(atualizarOS).setShort("situacao", situacao)
			.setInteger("numeroOS", numeroOS)
			.setDate("dtProgramacao", new Date()).executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1227] Atualizar Ordens Servi篠de Acompanhamento de Celular
	 * 
	 * @autor Sᶩo Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSProgramacaoAcompanhamentoServico> pesquisarOSProgramacaoAcompServicoPelaDataProgramacao(
			Date dataRoteiro, Integer idEquipe, Integer idUnidadeOrganizacional)
					throws ErroRepositorioException {

		Collection<OSProgramacaoAcompanhamentoServico> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT osProgramacao "
					+ "FROM OSProgramacaoAcompanhamentoServico osProgramacao "
					+ "INNER JOIN FETCH osProgramacao.ordemServico os "
					+ "LEFT JOIN FETCH osProgramacao.equipamentosEspeciaisFaltante equipFaltante "
					+ "WHERE osProgramacao.dataProgramacao = :dataProgramacaoRoteiro "
					+ "AND osProgramacao.indicadorAtualizacaoOS = :indicadorAtualizacaoOS";
			if (idEquipe != null && !idEquipe.equals("")) {
				consulta = consulta + " AND osProgramacao.equipe.id = "
						+ idEquipe;
			}

			retorno = (Collection<OSProgramacaoAcompanhamentoServico>) session
					.createQuery(consulta)
					.setDate("dataProgramacaoRoteiro", dataRoteiro)
					.setShort("indicadorAtualizacaoOS", ConstantesSistema.NAO)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1227] Atualizar Ordens Servi篠de Acompanhamento de Celular
	 * 
	 * @autor Sᶩo Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSAtividadeProgramacaoAcompanhamentoServico> pesquisarOSAtividadeProgramacaoAcompServico(
			Integer idOSProgramacaoAcompServico)
					throws ErroRepositorioException {

		Collection<OSAtividadeProgramacaoAcompanhamentoServico> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT osAtivAcompServico "
					+ "FROM OSAtividadeProgramacaoAcompanhamentoServico osAtivAcompServico "
					+ "INNER JOIN osAtivAcompServico.osProgramacaoAcompanhamentoServico osProgramacaoAcompServ "
					+ "WHERE osProgramacaoAcompServ.id = :idOSProgramacaoAcompServico "
					+ "AND osAtivAcompServico.indicadorAtualizacaoOS = :indicadorAtualizacaoOS";

			retorno = (Collection<OSAtividadeProgramacaoAcompanhamentoServico>) session
					.createQuery(consulta)
					.setInteger("idOSProgramacaoAcompServico",
							idOSProgramacaoAcompServico)
							.setShort("indicadorAtualizacaoOS", ConstantesSistema.NAO)
							.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1227] Atualizar Ordens Servi篠de Acompanhamento de Celular
	 * 
	 * @autor Sᶩo Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSAtividadeMaterialProgramacaoAcompanhamentoServico> pesquisarOSAtividadeMaterialProgramacaoAcompanhamentoServico(
			Integer idOSAtividadeProgramacaoAcompanhamentoServico)
					throws ErroRepositorioException {

		Collection<OSAtividadeMaterialProgramacaoAcompanhamentoServico> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT osAtividadeMaterialProgramacaoAcompanhamentoServico "
					+ "FROM OSAtividadeMaterialProgramacaoAcompanhamentoServico osAtividadeMaterialProgramacaoAcompanhamentoServico "
					+ "INNER JOIN osAtividadeMaterialProgramacaoAcompanhamentoServico.osAtividadeProgramacaoAcompanhamentoServico osAtivProgrAcompaServico "
					+ "WHERE osAtivProgrAcompaServico.id = :idOSAtividadeProgramacaoAcompanhamentoServico ";

			retorno = (Collection<OSAtividadeMaterialProgramacaoAcompanhamentoServico>) session
					.createQuery(consulta)
					.setInteger(
							"idOSAtividadeProgramacaoAcompanhamentoServico",
							idOSAtividadeProgramacaoAcompanhamentoServico)
							.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1227] Atualizar Ordens Servi篠de Acompanhamento de Celular
	 * 
	 * @autor Sᶩo Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOsAtivMaterialExecucao(
			Integer idOrdemServicoAtividade) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT oame "
					+ "FROM OsAtividadeMaterialExecucao oame "
					+ "WHERE oame.ordemServicoAtividade.id = :idOrdemServicoAtividade ";

			retorno = (Collection) session
					.createQuery(consulta)
					.setInteger("idOrdemServicoAtividade",
							idOrdemServicoAtividade).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * [UC1227] Atualizar Ordens Servi篠de Acompanhamento de Celular
	 * 
	 * @autor Sᶩo Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSAtividadeExecucaoAcompanhamentoServico> pesquisarOSAtividadeExecucaolProgramacaoAcompanhamentoServico(
			Integer idOSAtividadeProgramacaoAcompanhamentoServico)
					throws ErroRepositorioException {

		Collection<OSAtividadeExecucaoAcompanhamentoServico> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT osAtividadeExecucaoAcompanhamentoServico "
					+ "FROM OSAtividadeExecucaoAcompanhamentoServico osAtividadeExecucaoAcompanhamentoServico "
					+ "INNER JOIN osAtividadeExecucaoAcompanhamentoServico.osAtividadeProgramacaoAcompanhamentoServico osAtivProgrAcompaServico "
					+ "WHERE osAtivProgrAcompaServico.id = :idOSAtividadeProgramacaoAcompanhamentoServico ";

			retorno = (Collection<OSAtividadeExecucaoAcompanhamentoServico>) session
					.createQuery(consulta)
					.setInteger(
							"idOSAtividadeProgramacaoAcompanhamentoServico",
							idOSAtividadeProgramacaoAcompanhamentoServico)
							.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1227] Atualizar Ordens Servi篠de Acompanhamento de Celular
	 * 
	 * @autor Sᶩo Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOsAtivPeriodoExecucao(
			Integer idOrdemServicoAtividade) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "SELECT oape "
					+ "FROM OsAtividadePeriodoExecucao oape "
					+ "WHERE oape.ordemServicoAtividade.id = :idOrdemServicoAtividade ";

			retorno = (Collection) session
					.createQuery(consulta)
					.setInteger("idOrdemServicoAtividade",
							idOrdemServicoAtividade).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1225] Incluir Dados Acompanhamento de Servio
	 * 
	 * [FS0001] - Verificar existncia do nmero da ordem de servio
	 * 
	 * Caso o numero da ordem de servionoexista na tabela ORDEM_SERVICO,
	 * exibir a mensagem Ordem de Servioinexistente: <<nmero da Ordem de
	 * Servio>
	 * 
	 * e retornar para pro tipo 1 do arquivo de retorno.
	 * 
	 * @author Thlio Arajo
	 * @date 23/09/2011
	 * 
	 * @param helperLaco
	 * @throws ControladorException
	 */
	public boolean verificarExistenciaOrdemServico(Integer idOrdemServico)
			throws ErroRepositorioException {
		boolean retorno = false;

		Integer idOs;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT os.id " + "FROM OrdemServico os "
					+ "WHERE os.id = :idOrdemServico ";

			idOs = (Integer) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		if (idOs != null && !idOs.equals(null)) {
			retorno = true;
		}

		return retorno;
	}

	/**
	 * [UC1227] Atualizar Ordens Servi篠de Acompanhamento de Celular
	 * 
	 * @autor Sᶩo Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarCodigoSituacaoOS(Integer numeroOS, short situacao)
			throws ErroRepositorioException {

		String consulta;
		Session session = HibernateUtil.getSession();

		try {
			consulta = "update gcom.atendimentopublico.ordemservico.OrdemServico "
					+ " set orse_cdsituacao = :codigoSituacao, "
					+ " orse_tmultimaalteracao = :ultimaAlteracao "
					+ " where orse_id = :numeroOS ";

			session.createQuery(consulta).setShort("codigoSituacao", situacao)
			.setTimestamp("ultimaAlteracao", new Date())
			.setInteger("numeroOS", numeroOS).executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * [UC1190] Programa磯 Automᴩca do Roteiro para Acompanhamento das OS
	 * 
	 * @author Sᶩo Luiz
	 * @date 19/07/2011
	 */
	public Collection<OSProgramacaoAcompanhamentoServico> pesquisarOSAcompServicoNaoENcerradasMotivo(
			Integer idUnidadeOrganizacional) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta;

		try {
			consulta = "SELECT osProAcomServ "
					+ " FROM OSProgramacaoAcompanhamentoServico osProAcomServ "
					+ " INNER JOIN osProAcomServ.ordemServico ordemServico "
					+ " INNER JOIN osProAcomServ.equipe eqpe "
					+ " INNER JOIN eqpe.unidadeOrganizacional unidOrg "
					+ " LEFT JOIN osProAcomServ.osProgramacaoNaoEncerramentoMotivo osProgramaNaoEnceMotivo "
					+ " WHERE ordemServico.situacao = :situacaoPendente and osProgramaNaoEnceMotivo.id in (:faltaEquip,:EquipQuebrado)  and "
					+ " unidOrg.id = :idUnidadeOrganizacional ";

			retorno = (Collection<OSProgramacaoAcompanhamentoServico>) session
					.createQuery(consulta)
					.setInteger("idUnidadeOrganizacional",
							idUnidadeOrganizacional)
							.setInteger("situacaoPendente",
									OrdemServico.SITUACAO_PENDENTE)
									.setInteger("faltaEquip",
											OsProgramNaoEncerMotivo.EQUIPAMENTO_QUEBRADO)
											.setInteger("EquipQuebrado",
													OsProgramNaoEncerMotivo.FALTA_EQUIPAMENTO).list();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC1225] Incluir Dados Acompanhamento de Servio
	 * 
	 * Pesquisa OS Programacao Acompanhamento Servico por idOs e dataProgramacao
	 * 
	 * @author Thlio Arajo
	 * @date 23/09/2011
	 * 
	 * @param idOs
	 *            , dataProgramacao
	 * @return boolean - data da OS Programacao Acompanhamento Servico
	 * @throws ControladorException
	 */
	public OSProgramacaoAcompanhamentoServico pesquisarOSProgramacaoAcompServicoPorIdOs(
			Integer idOrdemServico, Date dataProgramacao)
					throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		OSProgramacaoAcompanhamentoServico retorno = null;

		String consulta;

		try {
			consulta = "select" + "	ospr " + "from "
					+ "   OSProgramacaoAcompanhamentoServico ospr "
					+ "where ospr.ordemServico.id = :idOrdemServico and "
					+ "ospr.dataProgramacao = :dataProgramacao";

			retorno = (OSProgramacaoAcompanhamentoServico) session
					.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setDate("dataProgramacao", dataProgramacao)
					.setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1225] Incluir Dados Acompanhamento de Servio
	 * 
	 * Pesquisa OS Atividade Programacao Acompanhamento Servico por idOs,
	 * dataProgramacao, idAtividade
	 * 
	 * @author Thlio Arajo
	 * @date 23/09/2011
	 * 
	 * @param idOrdemServico
	 *            , dataProgramacao, idAtividade
	 * @return Collection<OSAtividadeProgramacaoAcompanhamentoServico>
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarOSAtividadeProgramacaoAcompServicoPorIdOs(
			Integer idOrdemServico, Date dataProgramacao, Integer idAtividade)
					throws ErroRepositorioException {

		Collection<Integer> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT osAtivAcompServico.id "
					+ "FROM OSAtividadeProgramacaoAcompanhamentoServico osAtivAcompServico "
					+ "INNER JOIN osAtivAcompServico.osProgramacaoAcompanhamentoServico osProgramacao "
					+ "WHERE osProgramacao.ordemServico.id = :idOrdemServico "
					+ "AND osProgramacao.dataProgramacao = :dataProgramacao "
					+ "AND osAtivAcompServico.atividade.id = :idAtividade ";

			retorno = (Collection<Integer>) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setInteger("idAtividade", idAtividade)
					.setDate("dataProgramacao", dataProgramacao).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1225] Incluir Dados Acompanhamento de Servio
	 * 
	 * Excluir os dados da tabela OSAtividadeExecucaoAcompanhamentoServico para
	 * cada id da tabela OsAtividadeProgramcaoAcompanhamentoServico
	 * 
	 * @author Thlio Arajo
	 * @date 26/09/2011
	 * 
	 * @param Collection
	 *            <Integer> isOsAtividadeProgramacaoAcompanhamentoServico
	 * @throws ErroRepositorioException
	 */
	public void excluirOsAtividadeExecucaoAcompahamentoServico(
			Collection<Integer> isOsAtividadeProgramacaoAcompanhamentoServico)
					throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String sql = "";

		try {

			sql = "DELETE FROM OSAtividadeExecucaoAcompanhamentoServico "
					+ "WHERE oats_id = :idOsAtividadeProgramacaoAcompanhamentoServico ";

			session.createQuery(sql)
			.setParameterList(
					"idOsAtividadeProgramacaoAcompanhamentoServico",
					isOsAtividadeProgramacaoAcompanhamentoServico)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1225] Incluir Dados Acompanhamento de Servio
	 * 
	 * Excluir os dados da tabela
	 * OSAtividadeMaterialProgramacaoAcompanhamentoServico para cada id da
	 * tabela OsAtividadeProgramcaoAcompanhamentoServico
	 * 
	 * @author Thlio Arajo
	 * @date 26/09/2011
	 * 
	 * @param Collection
	 *            <Integer> isOsAtividadeProgramacaoAcompanhamentoServico
	 * @throws ErroRepositorioException
	 */
	public void excluirOsAtividadeMaterialProgramacaoAcompahamentoServico(
			Collection<Integer> isOsAtividadeProgramacaoAcompanhamentoServico)
					throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String sql = "";

		try {

			sql = "DELETE FROM OSAtividadeMaterialProgramacaoAcompanhamentoServico "
					+ "WHERE oats_id = :idOsAtividadeProgramacaoAcompanhamentoServico ";

			session.createQuery(sql)
			.setParameterList(
					"idOsAtividadeProgramacaoAcompanhamentoServico",
					isOsAtividadeProgramacaoAcompanhamentoServico)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1225] Incluir Dados Acompanhamento de Servio
	 * 
	 * Excluir os dados da tabela OSAtividadeProgramacaoAcompanhamentoServico
	 * para cada id da tabela OsAtividadeProgramcaoAcompanhamentoServico
	 * 
	 * @author Thlio Arajo
	 * @date 26/09/2011
	 * 
	 * @param Collection
	 *            <Integer> isOsAtividadeProgramacaoAcompanhamentoServico
	 * @throws ErroRepositorioException
	 */
	public void excluirOsAtividadeProgramacaoAcompahamentoServico(
			Collection<Integer> isOsAtividadeProgramacaoAcompanhamentoServico)
					throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String sql = "";

		try {

			sql = "DELETE FROM OSAtividadeProgramacaoAcompanhamentoServico "
					+ "WHERE oats_id = :idOsAtividadeProgramacaoAcompanhamentoServico ";

			session.createQuery(sql)
			.setParameterList(
					"idOsAtividadeProgramacaoAcompanhamentoServico",
					isOsAtividadeProgramacaoAcompanhamentoServico)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public Collection<Object[]> pesquisarOrdensServicoProgramadas(Integer unidadeOrganizacionalId) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			StringBuilder sql = new StringBuilder();

			sql.append("SELECT orse.orse_id as idOrdemServico, ")
			   .append("       orse.orse_cdsituacao as situacao, ")
			   .append("       orse.orse_tmgeracao as dataGeracao, ")
			   .append("       svtp.svtp_dsservicotipo as servicoTipoDescricao, ")
			   .append("       svtp.svtp_vlservico servicoTipoValor, ")
			   .append("       orse.orse_dsobservacao as observacao, ")
			   .append("       pgrt.pgrt_tmroteiro as dataProgramacao, ")
			   .append("       eqpe.eqpe_nmequipe as equipeProgramacao, ")
			   .append("       stop.oper_id as idOperacao, ")
			   .append("       orse.imov_id as idImovel ")
			   .append("FROM atendimentopublico.os_programacao ospg ")
			   .append("INNER JOIN atendimentopublico.programacao_roteiro pgrt ON pgrt.pgrt_id = ospg.pgrt_id ")
			   .append("INNER JOIN atendimentopublico.ordem_servico orse ON orse.orse_id = ospg.orse_id ")
			   .append("INNER JOIN atendimentopublico.servico_tipo svtp ON svtp.svtp_id = orse.svtp_id ")
			   .append("LEFT JOIN atendimentopublico.servico_tipo_operacao stop ON stop.svtp_id = svtp.svtp_id ")
			   .append("INNER JOIN atendimentopublico.equipe eqpe ON eqpe.eqpe_id = ospg.eqpe_id ")
			   .append("INNER JOIN cadastro.unidade_organizacional unid ON unid.unid_id = pgrt.unid_id ")
			   .append("INNER JOIN cadastro.imovel imov ON imov.imov_id = orse.imov_id ")
			   .append("WHERE 1=1 ")
			   .append("AND unid.unid_id = :unidadeOrganizacionalId ")
			   .append("AND orse.orse_cdsituacao = :situacao ")
			   .append("GROUP BY idOrdemServico, situacao, dataGeracao, servicoTipoDescricao, servicoTipoValor, observacao, dataProgramacao, equipeProgramacao, idOperacao, idImovel ")
			   .append("ORDER BY idOrdemServico");

			return session.createSQLQuery(sql.toString())
						  .addScalar("idOrdemServico", Hibernate.INTEGER)
						  .addScalar("situacao", Hibernate.INTEGER)
						  .addScalar("dataGeracao", Hibernate.TIMESTAMP)
						  .addScalar("servicoTipoDescricao", Hibernate.STRING)
						  .addScalar("servicoTipoValor", Hibernate.BIG_DECIMAL)
						  .addScalar("observacao", Hibernate.STRING)
						  .addScalar("dataProgramacao", Hibernate.TIMESTAMP)
						  .addScalar("equipeProgramacao", Hibernate.STRING)
						  .addScalar("idOperacao", Hibernate.INTEGER)
						  .addScalar("idImovel", Hibernate.INTEGER)
						  .setInteger("unidadeOrganizacionalId", unidadeOrganizacionalId)
						  .setInteger("situacao", OrdemServicoSituacao.PENDENTE)
						  .list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * 09/12/2020
	 * Para atender as OS programadas sem RA
	 */
	public Collection<ServicoTipo> pesquisarServicoTipoPorUnidadeSemRA(Integer unidadeLotacao) throws ErroRepositorioException {

		Collection<ServicoTipo> retornoConsulta = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = "SELECT DISTINCT svtp " + "FROM OrdemServico os  "
					+ "INNER JOIN os.unidadeAtual unid  "
					+ "LEFT JOIN os.servicoTipo svtp  "
					+ "LEFT JOIN os.registroAtendimento ra  "
					+ "WHERE unid.id = :unidadeLotacao "
					+ "AND os.situacao in (1,3) " + "ORDER BY svtp.descricao";

			retornoConsulta = session.createQuery(consulta)
					.setInteger("unidadeLotacao", unidadeLotacao).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoConsulta;
	}
}
