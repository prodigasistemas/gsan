package gcom.micromedicao;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.batch.Relatorio;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.RotaAcaoCriterio;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FaturamentoSituacaoHistorico;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.MotivoInterferenciaTipo;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.faturamento.ImovelFaturamentoSeletivoHelper;
import gcom.gui.micromedicao.ColetaMedidorEnergiaHelper;
import gcom.gui.micromedicao.DadosMovimentacao;
import gcom.gui.relatorio.micromedicao.FiltroRelatorioLeituraConsultarArquivosTextoHelper;
import gcom.gui.relatorio.micromedicao.RelatorioNotificacaoDebitosImpressaoSimultaneaHelper;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.bean.FiltrarLeiturasTelemetriaHelper;
import gcom.micromedicao.bean.ImovelPorRotaHelper;
import gcom.micromedicao.bean.LigacaoMedicaoIndividualizadaHelper;
import gcom.micromedicao.bean.PesquisarRelatorioRotasOnlinePorEmpresaHelper;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.ConsumoAnormalidadeAcao;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroMovimentado;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraFaixaFalsa;
import gcom.micromedicao.leitura.LeituraSituacao;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.relatorio.cadastro.micromedicao.FiltrarRelatorioImoveisComLeiturasHelper;
import gcom.relatorio.cadastro.micromedicao.RelatorioResumoLigacoesCapacidadeHidrometroHelper;
import gcom.relatorio.micromedicao.FiltrarAnaliseExcecoesLeiturasHelper;
import gcom.relatorio.micromedicao.FiltrarRelatorioAnormalidadeLeituraPeriodoHelper;
import gcom.relatorio.micromedicao.FiltrarRelatorioBoletimMedicaoHelper;
import gcom.relatorio.micromedicao.GerarDadosLeituraHelper;
import gcom.relatorio.micromedicao.RelatorioAcompanhamentoLeituristaHelper;
import gcom.relatorio.micromedicao.RelatorioRotasOnlinePorEmpresaBean;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.IoUtil;
import gcom.util.RemocaoInvalidaException;
import gcom.util.RemocaoRegistroNaoExistenteException;
import gcom.util.RepositorioUtilHBM;
import gcom.util.Util;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.CallbackException;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class RepositorioMicromedicaoHBM implements IRepositorioMicromedicao {

	protected static IRepositorioMicromedicao instancia;

	/**
	 * Construtor da classe RepositorioMicromedicaoHBM
	 */
	protected RepositorioMicromedicaoHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioMicromedicao getInstancia() {

		String dialect = HibernateUtil.getDialect();
		
		if (dialect.toUpperCase().contains("ORACLE")){
			if (instancia == null) {
				instancia = new RepositorioMicromedicaoHBM();
			}
		} else {
			if (instancia == null) {
				instancia = new RepositorioMicromedicaoPostgresHBM();
			}
		}
		
		return instancia;
	}

	/**
	 * pesquisa uma coleção de cep(s) de acordo com o código
	 * 
	 * @param faixaInicial
	 *            Descrição do parâmetro
	 * @param faixaFinal
	 *            Descrição do parâmetro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarNumeroHidrometroFaixaPaginacao(
			String faixaInicial, String faixaFinal, Integer numeroPagina)
			throws ErroRepositorioException {

		// Collection<Hidrometro> retorno = new ArrayList();
		Collection<Hidrometro> hidrometros = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select hidrometro "
					+ "from gcom.micromedicao.hidrometro.Hidrometro hidrometro "
					+ "left join fetch hidrometro.hidrometroMarca "
					+ "left join fetch hidrometro.hidrometroCapacidade "
					+ "left join fetch hidrometro.hidrometroSituacao "
					+ "left join fetch hidrometro.hidrometroLocalArmazenagem "
					+ "left join fetch hidrometro.hidrometroRelojoaria "
					+ "where hidrometro.numero between :fi and :ff "
					+ "order by hidrometro.numero";

			hidrometros = session.createQuery(consulta).setString("fi",
					faixaInicial).setString("ff", faixaFinal).setFirstResult(
					10 * numeroPagina).setMaxResults(10).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		// return retorno;
		return hidrometros;
	}

	/**
	 * pesquisa uma coleção de cep(s) de acordo com o código
	 * 
	 * @param faixaInicial
	 *            Descrição do parâmetro
	 * @param faixaFinal
	 *            Descrição do parâmetro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarNumeroHidrometroFaixa(String faixaInicial,
			String faixaFinal) throws ErroRepositorioException {

		// Collection<Hidrometro> retorno = new ArrayList();
		Collection<Hidrometro> hidrometros = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select hidrometro "
					+ "from gcom.micromedicao.hidrometro.Hidrometro hidrometro "
					+ "left join fetch hidrometro.hidrometroMarca "
					+ "left join fetch hidrometro.hidrometroCapacidade "
					+ "left join fetch hidrometro.hidrometroSituacao "
					+ "left join fetch hidrometro.hidrometroLocalArmazenagem "
					+ "left join fetch hidrometro.hidrometroRelojoaria "
					+ "where hidrometro.numero between :fi and :ff "
					+ "order by hidrometro.numero";

			hidrometros = session.createQuery(consulta).setString("fi",
					faixaInicial).setString("ff", faixaFinal).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return hidrometros;
	}

	/**
	 * pesquisa uma coleção de cep(s) de acordo com o código
	 * 
	 * @param faixaInicial
	 *            Descrição do parâmetro
	 * @param faixaFinal
	 *            Descrição do parâmetro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarNumeroHidrometroFaixaBatch(String faixaInicial,
			String faixaFinal, Integer qtd) throws ErroRepositorioException {

		// Collection<Hidrometro> retorno = new ArrayList();
		Collection<Hidrometro> hidrometros = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select hidrometro "
					+ "from gcom.micromedicao.hidrometro.Hidrometro hidrometro "
					+ "left join fetch hidrometro.hidrometroMarca "
					+ "left join fetch hidrometro.hidrometroCapacidade "
					+ "left join fetch hidrometro.hidrometroSituacao "
					+ "left join fetch hidrometro.hidrometroLocalArmazenagem "
					+ "left join fetch hidrometro.hidrometroRelojoaria "
					+ "where hidrometro.numero between :fi and :ff "
					+ "order by hidrometro.numero";

			hidrometros = session.createQuery(consulta).setString("fi",
					faixaInicial).setString("ff", faixaFinal).setFirstResult(
					500 * qtd).setMaxResults(500).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return hidrometros;
	}

	/**
	 * Pesquisa uma coleção de hidrômetros de acordo com fixo, faixa inicial e
	 * faixa final
	 * 
	 * @param faixaInicial
	 *            Descrição do parâmetro
	 * @param faixaFinal
	 *            Descrição do parâmetro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarNumeroHidrometroFaixaRelatorio(
			String faixaInicial, String faixaFinal)
			throws ErroRepositorioException {

		// Collection<Hidrometro> retorno = new ArrayList();
		Collection<Hidrometro> hidrometros = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select hidrometro "
					+ "from gcom.micromedicao.hidrometro.Hidrometro hidrometro "
					+ "left join fetch hidrometro.hidrometroClasseMetrologica "
					+ "left join fetch hidrometro.hidrometroMarca "
					+ "left join fetch hidrometro.hidrometroDiametro "
					+ "left join fetch hidrometro.hidrometroCapacidade "
					+ "left join fetch hidrometro.hidrometroTipo "
					+ "left join fetch hidrometro.hidrometroSituacao "
					+ "where hidrometro.numero between :fi and :ff "
					+ "order by hidrometro.numero";

			hidrometros = session.createQuery(consulta).setString("fi",
					faixaInicial).setString("ff", faixaFinal).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return hidrometros;
	}

	/**
	 * pesquisa uma coleção de cep(s) de acordo com o código
	 * 
	 * @param faixaInicial
	 *            Descrição do parâmetro
	 * @param faixaFinal
	 *            Descrição do parâmetro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarNumeroHidrometroFaixaComLimite(
			String faixaInicial, String faixaFinal)
			throws ErroRepositorioException {

		Collection<Hidrometro> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select hidrometro "
					+ "from gcom.micromedicao.hidrometro.Hidrometro hidrometro "
					+ "left join fetch hidrometro.hidrometroMarca "
					+ "left join fetch hidrometro.hidrometroCapacidade "
					+ "left join fetch hidrometro.hidrometroSituacao "
					+ "where hidrometro.numero between :fi and :ff "
					+ "order by hidrometro.numero ";

			retorno = session.createQuery(consulta).setString("fi",
					faixaInicial).setString("ff", faixaFinal).setMaxResults(50)
					.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;
	}

	/**
	 * 
	 * @param faixaInicial
	 *            Descrição do parâmetro
	 * @param faixaFinal
	 *            Descrição do parâmetro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Integer pesquisarNumeroHidrometroFaixaCount(String Fixo,
			String faixaInicial, String faixaFinal)
			throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select count (hidrometro.id) from gcom.micromedicao.hidrometro.Hidrometro hidrometro where hidrometro.numero between :fi and :ff ";

			Object resultado = session.createQuery(consulta).setString("fi",
					faixaInicial).setString("ff", faixaFinal).uniqueResult();

			if (resultado != null) {
				retorno = (Integer) resultado;
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoGrupo
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarImovelFaturamentoGrupoObterIds(
			FaturamentoGrupo faturamentoGrupo) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select im.id from Imovel im "
					+ "inner join im.quadra " + "inner join im.quadra.rota "
					+ "inner join im.quadra.rota.faturamentoGrupo "
					+ "where im.quadra.rota.faturamentoGrupo.id = :id ";

			retorno = session.createQuery(consulta).setInteger("id",
					faturamentoGrupo.getId().intValue()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoGrupo
	 *            Descrição do parâmetro
	 * @param imovel
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarImovelTesteMedicaoConsumoLigacaoAgua(
			FaturamentoGrupo faturamentoGrupo, Imovel imovel)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select new gcom.micromedicao.ImovelTestesMedicaoConsumo(mh.ligacaoAgua.imovel.id, "
					+ "mh.ligacaoAgua.imovel.ligacaoAguaSituacao.id, "
					+ "mh.ligacaoAgua.hidrometroInstalacaoHistorico.id, "
					+ "mh.leituraAnteriorFaturamento, "
					+ "mh.leituraAnteriorInformada, "
					+ "mh.leituraSituacaoAnterior.id, "
					+ "mh.leituraAtualInformada, "
					+ "mh.leituraSituacaoAtual.id, "
					+ "mh.leituraAnormalidadeInformada.id, "
					+ "mh.ligacaoAgua.imovel.faturamentoSituacaoTipo.id, "
					+ "mh.ligacaoAgua.numeroConsumoMinimoAgua, "
					+ "mh.ligacaoAgua.hidrometroInstalacaoHistorico.hidrometro.numeroDigitosLeitura, "
					+ "mh.ligacaoAgua.hidrometroInstalacaoHistorico.hidrometro.hidrometroCapacidade.id) "
					+ "from MedicaoHistorico mh "
					+ "right join mh.ligacaoAgua "
					+ "right join mh.ligacaoAgua.imovel "
					+ "where mh.ligacaoAgua.imovel.id = :id and mh.anoMesReferencia = :anoMes ";

			retorno = session.createQuery(consulta).setInteger("id",
					imovel.getId().intValue()).setInteger("anoMes",
					faturamentoGrupo.getAnoMesReferencia().intValue()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoGrupo
	 *            Descrição do parâmetro
	 * @param imovel
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarImovelTesteMedicaoConsumoPoco(
			FaturamentoGrupo faturamentoGrupo, Imovel imovel)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select new gcom.micromedicao.ImovelTestesMedicaoConsumo(mh.imovel.id, "
					+ "mh.imovel.hidrometroInstalacaoHistorico.id, "
					+ "mh.leituraAnteriorFaturamento, "
					+ "mh.leituraAnteriorInformada, "
					+ "mh.leituraSituacaoAnterior.id, "
					+ "mh.leituraAtualInformada, "
					+ "mh.leituraSituacaoAtual.id, "
					+ "mh.leituraAnormalidadeInformada.id, "
					+ "mh.imovel.faturamentoSituacaoTipo.id, "
					+ "mh.imovel.pocoTipo.id, "
					+ "mh.imovel.hidrometroInstalacaoHistorico.hidrometro.numeroDigitosLeitura, "
					+ "mh.imovel.hidrometroInstalacaoHistorico.hidrometro.hidrometroCapacidade.id) "
					+ "from MedicaoHistorico mh "
					+ "right join mh.imovel "
					+ "where mh.imovel.id = :id and mh.anoMesReferencia = :anoMes ";

			retorno = session.createQuery(consulta).setInteger("id",
					imovel.getId().intValue()).setInteger("anoMes",
					faturamentoGrupo.getAnoMesReferencia().intValue()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param faturamentoGrupo
	 *            Descrição do parâmetro
	 * @param imovel
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarImovelTesteMedicaoConsumoLigacaoEsgoto(
			FaturamentoGrupo faturamentoGrupo, Imovel imovel)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = " select new gcom.micromedicao.ImovelTestesMedicaoConsumo(im.id, "
					+ " im.ligacaoEsgotoSituacao.id, "
					+ " im.ligacaoEsgoto.consumoMinimo, "
					+ " im.ligacaoEsgoto.percentualAguaConsumidaColetada) "
					+ " from Imovel im "
					+ " inner join im.ligacaoEsgoto "
					+ " where im.id = :id ";

			retorno = session.createQuery(consulta).setInteger("id",
					imovel.getId().intValue()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param anoMesReferencia
	 *            Descrição do parâmetro
	 * @param periodoInformado
	 *            Descrição do parâmetro
	 * @param ligacaoTipo
	 *            Descrição do parâmetro
	 * 
	 * @return Descrição do retorno
	 * 
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarConsumoMedidoHidrometroPeriodoInformadoLigacaoAgua(
			Imovel imovel, int anoMesReferencia,
			LigacaoTipo ligacaoTipo) throws ErroRepositorioException {



		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT mh.anoMesReferencia,"
					+ " la.hidrometroInstalacaoHistorico.id,"
					+ " mh.hidrometroInstalacaoHistorico.id,"
					+ " mh.numeroConsumoMes,"
					+ " mh.medicaoTipo.id,"
					+ " ch.consumoAnormalidade.id,"
					+ " ch.numeroConsumoCalculoMedia, "
					+ " ch.consumoTipo.indicadorCalculoMedia, "
					/** TODO : COSANPA Adicionando informacao da data de instalacao do hidrometro */
					+ " la.hidrometroInstalacaoHistorico.dataInstalacao, "
					+ " mh.dataLeituraAtualInformada "
					+ " FROM Imovel im, LigacaoAgua la, MedicaoHistorico  mh, ConsumoHistorico ch"
					+ " WHERE im.id = :imovelId"
					+ " AND ch.ligacaoTipo = :ligacaoTipoId"
					+ " AND im.id = la.id" + " AND la.id = mh.ligacaoAgua.id"
					+ " AND im.id = ch.imovel.id"
					+ " AND mh.anoMesReferencia = ch.referenciaFaturamento" 
					+ "	AND mh.anoMesReferencia = :anoMesReferencia	";

			retorno = session.createQuery(consulta).setInteger("imovelId",
					imovel.getId().intValue()).setInteger("ligacaoTipoId",
					ligacaoTipo.getId().intValue()).
					setInteger("anoMesReferencia",anoMesReferencia).
					list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param anoMesReferencia
	 *            Descrição do parâmetro
	 * @param periodoInformado
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarConsumoMedidoHidrometroPeriodoInformadoPoco(
			Imovel imovel, int anoMesReferencia)
			throws ErroRepositorioException {


		Collection retorno = null;


		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "SELECT mh.anoMesReferencia,"
					+ " im.hidrometroInstalacaoHistorico.id,"
					+ " mh.hidrometroInstalacaoHistorico.id,"
					+ " mh.numeroConsumoMes,"
					+ " mh.medicaoTipo.id,"
					+ " ch.consumoAnormalidade.id,"
					+ " ch.numeroConsumoCalculoMedia, "
					+ " ch.consumoTipo.indicadorCalculoMedia, "
					+ " ch.percentualColeta, "
					+ " ch.consumoTipo.indicadorCalculoMedia, "
					/** TODO : COSANPA 
					 * @date 04/10/2012
					 * Adriana Muniz / Pamela Gatinho
					 * Adicionando informacao da data de instalacao do hidrometro */
					+ " im.hidrometroInstalacaoHistorico.dataInstalacao, "
					+ " mh.dataLeituraAtualInformada "
					+ " FROM Imovel im,MedicaoHistorico  mh, ConsumoHistorico ch"
					+ " WHERE im.id = :imovelId"
					+ " AND ch.ligacaoTipo = :ligacaoTipoId"
					+ " AND im.id = mh.imovel.id" + " AND im.id = ch.imovel.id"
					+ " AND mh.anoMesReferencia = ch.referenciaFaturamento"
					+ "	AND mh.anoMesReferencia = :anoMesReferencia	";

			retorno = session.createQuery(consulta).setInteger("imovelId",
					imovel.getId().intValue())
					.setInteger("anoMesReferencia",anoMesReferencia)
					.setInteger("ligacaoTipoId",
					MedicaoTipo.POCO).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param anoMesReferencia
	 *            Descrição do parâmetro
	 * @param periodoInformado
	 *            Descrição do parâmetro
	 * @param ligacaoTipo
	 *            Descrição do parâmetro
	 * 
	 * @return Descrição do retorno
	 * 
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Integer pesquisarConsumoFaturadoMesPorConsumoHistorico(
			Integer idImovel, int anoMesReferencia, Integer idLigacaoTipo)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT ch.numeroConsumoCalculoMedia"
					+ " FROM Imovel im, LigacaoAgua la, MedicaoHistorico  mh, ConsumoHistorico ch"
					+ " WHERE im.id = :imovelId"
					+ " AND ch.ligacaoTipo = :ligacaoTipoId"
					+ " AND im.id = la.id" + " AND la.id = mh.ligacaoAgua.id"
					+ " AND im.id = ch.imovel.id"
					+ " AND ch.referenciaFaturamento = :anoMesReferencia"
					+ " AND mh.anoMesReferencia = ch.referenciaFaturamento";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"imovelId", idImovel).setInteger("ligacaoTipoId",
					idLigacaoTipo).setInteger("anoMesReferencia",
					anoMesReferencia).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param anoMesReferencia
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarObterConsumoMedioImovel(Imovel imovel,
			int anoMesReferencia) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select ch.id, "
					+ "ch.ligacaoTipo.id, "
					+ "ch.consumoTipo.id, "
					+ "ch.consumoAnormalidade.id, "
					+ "ch.numeroConsumoCalculoMedia, "
					+ "ch.percentualColeta, "
					+ "ch.consumoTipo.indicadorCalculoMedia, "
					// TIRAR ESSA PARTE DEPOIS
					+ "imov.id "
					+ "from ConsumoHistorico ch "
					+ "inner join ch.imovel imov "
					+ "where imov.id = :id and ch.referenciaFaturamento = :anoMes";

			retorno = session.createQuery(consulta).setInteger("id",
					imovel.getId().intValue()).setInteger("anoMes",
					anoMesReferencia).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarImovelLigacaoSituacao(Imovel imovel)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select im.id, " + "im.ligacaoAguaSituacao.id, "
					+ "im.ligacaoEsgotoSituacao.id " + "from Imovel im "
					+ "inner join im.ligacaoAguaSituacao "
					+ "inner join im.ligacaoEsgotoSituacao "
					+ "where im.id = :id ";

			retorno = session.createQuery(consulta).setInteger("id",
					imovel.getId().intValue()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	@SuppressWarnings("unchecked")
	public Collection<MedicaoHistorico> pesquisarObterDadosHistoricoMedicao(Imovel imovel,MedicaoTipo medicaoTipo, FaturamentoGrupo faturamentoGrupo) throws ErroRepositorioException {

		Collection<MedicaoHistorico> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		String composicao = null;

		if (medicaoTipo.getId().intValue() == MedicaoTipo.LIGACAO_AGUA.intValue()) {
			composicao = "where mh.medicaoTipo.id = :medicaoTipoId"
					+ " and mh.ligacaoAgua.id = :imovelId"
					+ " and mh.anoMesReferencia = :amReferencia";
		
		} else if (medicaoTipo.getId().intValue() == MedicaoTipo.POCO.intValue()) {
			composicao = "where mh.medicaoTipo.id = :medicaoTipoId"
					+ " and mh.imovel.id = :imovelId "
					+ " and mh.anoMesReferencia = :amReferencia";

		}

		try {
			consulta = " SELECT mh FROM MedicaoHistorico mh "
					+ " LEFT JOIN FETCH mh.leituraAnormalidadeFaturamento laf "
					+ " LEFT JOIN FETCH mh.leituraAnormalidadeInformada lai "
					+ " LEFT JOIN FETCH mh.leituraSituacaoAnterior lsant "
					+ " LEFT JOIN FETCH mh.leituraSituacaoAtual lsatual "
					+ " LEFT JOIN FETCH mh.hidrometroInstalacaoHistorico hih "
					+ composicao;

			retorno = (Collection<MedicaoHistorico>)session.createQuery(consulta)
					.setInteger("medicaoTipoId",medicaoTipo.getId())
					.setInteger("imovelId", imovel.getId())
					.setInteger("amReferencia",faturamentoGrupo.getAnoMesReferencia()).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/*
	 * Método utilizado para pesquisar dados do maior histórico de medição
	 * existente para um imóvel
	 * 
	 * [UC0101] Consistir Leituras e Calcular Consumos Autor: Leonardo Vieira
	 * Data: 20/02/2006
	 */

	public Object pesquisarObterDadosMaiorHistoricoMedicao(Imovel imovel,
			MedicaoTipo medicaoTipo, SistemaParametro sistemaParametro)
			throws ErroRepositorioException {

		Object retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		String composicao = null;

		// Caso seja ligação de água
		if (medicaoTipo.getId().intValue() == MedicaoTipo.LIGACAO_AGUA
				.intValue()) {
			composicao = "where mt.id = :medicaoTipoId"
					+ " and la.id = :imovelId";
		} else if (medicaoTipo.getId().intValue() == MedicaoTipo.POCO
				.intValue()) {
			composicao = "where mt.id = :medicaoTipoId"
					+ " and im.id = :imovelId";
		}

		try {
			consulta = " select mh.dataLeituraAtualFaturamento, "
					+ "mh.leituraAtualFaturamento, "
					+ "mh.leituraAtualInformada "
					+ "from MedicaoHistorico mh "
					+ "inner join mh.medicaoTipo mt "
					+ "left join mh.ligacaoAgua la "
					+ "left join mh.imovel im "
					+ composicao
					+ " and mh.anoMesReferencia in (select max(mh.anoMesReferencia) from MedicaoHistorico mh "
					+ composicao + ")";

			retorno = session.createQuery(consulta).setInteger("medicaoTipoId",
					medicaoTipo.getId()).setInteger("imovelId", imovel.getId())
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param objeto
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Object inserirBat(Object objeto) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Object retorno = null;

		try {

			retorno = session.save(objeto);
			session.flush();
			session.clear();
			return retorno;
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			session.clear();
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param rotas
	 *            Descrição do parâmetro
	 * @param anoMesReferencia
	 *            Descrição do parâmetro
	 * @param ligacaoAguaSituacaoLigado
	 *            Descrição do parâmetro
	 * @param ligacaoAguaSituacaoCortado
	 *            Descrição do parâmetro
	 * @param ligacaoEsgotoLigado
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarImoveisLigadosCortadosAguaLigadosEsgoto(
			Rota rota, int anoMesReferencia, Integer ligacaoAguaSituacaoLigado,
			Integer ligacaoAguaSituacaoCortado, Integer ligacaoEsgotoLigado)
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select im.id," // 0
					+ "im.ligacaoAguaSituacao.id, " // 1
					+ "im.ligacaoEsgotoSituacao.id, " // 2
					+ "li.id, " // 3
					+ "hia.id, " // 4
					+ "hdra.id, " // 5
					+ "hdra.numeroDigitosLeitura, " // 6
					+ "fst.id," // 7
					+ "im.pocoTipo.id, " // 8
					+ "hie.id, " // 9
					+ "hdre.id, " // 10
					+ "im.quadra.rota.indicadorAjusteConsumo, " // 11
					+ "im.ligacaoAgua.numeroConsumoMinimoAgua, " // 12
					+ "im.indicadorImovelCondominio, " // 13
					+ "fst.indicadorParalisacaoFaturamento, " // 14
					+ "im.indicadorDebitoConta, " // 15
					+ "le.id, " // Ligacao Esgoto //16
					+ "le.consumoMinimo, " // 17
					+ "hia.dataInstalacao, " // 18
					+ "ct.id, " // 19
					+ "le.percentualAguaConsumidaColetada, " // 20
					+ "im.quantidadeEconomias, " // 21
					+ "hdre.numeroDigitosLeitura, " // 22
					+ "hie.dataInstalacao "// 23
					+ "from Imovel im "
					+ "inner join im.quadra.rota.faturamentoGrupo "
					+ "inner join im.consumoTarifa ct "
					+ "left join im.ligacaoAgua li "
					+ "left join im.ligacaoAgua.hidrometroInstalacaoHistorico hia "
					+ "left join im.ligacaoAgua.hidrometroInstalacaoHistorico.hidrometro hdra "
					+ "left join im.hidrometroInstalacaoHistorico hie "
					+ "left join im.hidrometroInstalacaoHistorico.hidrometro hdre "
					+ "left join im.faturamentoSituacaoTipo fst "
					+ "left join im.ligacaoEsgoto le "
					+ "where (im.quadra.rota.faturamentoGrupo.anoMesReferencia = :anoMes) "
					+ "and im.quadra.rota.id = :rota "
					+ "and (im.ligacaoAguaSituacao.id = :ligacaoAguaSituacaoId1 or im.ligacaoAguaSituacao = :ligacaoAguaSituacaoId2 or im.ligacaoEsgotoSituacao.id = :ligacaoAguaSituacaoId3) ";

			retorno = session.createQuery(consulta).setInteger("anoMes",
					anoMesReferencia).setInteger("ligacaoAguaSituacaoId1",
					ligacaoAguaSituacaoLigado.intValue()).setInteger(
					"ligacaoAguaSituacaoId2",
					ligacaoAguaSituacaoCortado.intValue()).setInteger(
					"ligacaoAguaSituacaoId3", ligacaoEsgotoLigado.intValue())
					.setInteger("rota", rota.getId()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 * 
	 * Método Especifico Para CAERN
	 * 
	 * @author Raphael Rossiter, Flávio Cordeiro
	 * @date 20/03/2007
	 * 
	 * @param rota
	 * @param anoMesReferencia
	 * @param ligacaoAguaSituacaoLigado
	 * @param ligacaoEsgotoLigado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisLigadosAguaLigadosEsgoto(Rota rota,
			int anoMesReferencia, Integer ligacaoAguaSituacaoLigado,
			Integer ligacaoEsgotoLigado) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select im.id," // 0
					+ "im.ligacaoAguaSituacao.id, " // 1
					+ "im.ligacaoEsgotoSituacao.id, " // 2
					+ "li.id, " // 3
					+ "hia.id, " // 4
					+ "hdra.id, " // 5
					+ "hdra.numeroDigitosLeitura, " // 6
					+ "fst.id," // 7
					+ "im.pocoTipo.id, " // 8
					+ "hie.id, " // 9
					+ "hdre.id, " // 10
					+ "im.quadra.rota.indicadorAjusteConsumo, " // 11
					+ "im.ligacaoAgua.numeroConsumoMinimoAgua, " // 12
					+ "im.indicadorImovelCondominio, " // 13
					+ "fst.indicadorParalisacaoFaturamento, " // 14
					+ "im.indicadorDebitoConta, " // 15
					+ "le.id, " // Ligacao Esgoto //16
					+ "le.consumoMinimo, " // 17
					+ "hia.dataInstalacao, " // 18
					+ "ct.id, " // 19
					+ "le.percentualAguaConsumidaColetada, " // 20
					+ "im.quantidadeEconomias, " // 21
					+ "hdre.numeroDigitosLeitura, " // 22
					+ "hie.dataInstalacao "// 23
					+ "from Imovel im "
					+ "inner join im.quadra.rota.faturamentoGrupo "
					+ "inner join im.consumoTarifa ct "
					+ "left join im.ligacaoAgua li "
					+ "left join im.ligacaoAgua.hidrometroInstalacaoHistorico hia "
					+ "left join im.ligacaoAgua.hidrometroInstalacaoHistorico.hidrometro hdra "
					+ "left join im.hidrometroInstalacaoHistorico hie "
					+ "left join im.hidrometroInstalacaoHistorico.hidrometro hdre "
					+ "left join im.faturamentoSituacaoTipo fst "
					+ "left join im.ligacaoEsgoto le "
					+ "where (im.quadra.rota.faturamentoGrupo.anoMesReferencia = :anoMes) "
					+ "and im.quadra.rota.id = :rota "
					+ "and (im.ligacaoAguaSituacao.id = :ligacaoAguaSituacaoId1 or im.ligacaoEsgotoSituacao.id = :ligacaoAguaSituacaoId3) ";

			retorno = session.createQuery(consulta).setInteger("anoMes",
					anoMesReferencia).setInteger("ligacaoAguaSituacaoId1",
					ligacaoAguaSituacaoLigado.intValue()).setInteger(
					"ligacaoAguaSituacaoId3", ligacaoEsgotoLigado.intValue())
					.setInteger("rota", rota.getId()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param ligacaoTipo
	 *            Descrição do parâmetro
	 * @param anoMesReferencia
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarConsumoHistoricoConsumoAnormalidade(
			Imovel imovel, LigacaoTipo ligacaoTipo, int anoMesReferencia)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select ch.id, ca.id, lt.id,  ch.referenciaFaturamento  from ConsumoHistorico ch "
					+ "inner join ch.consumoAnormalidade ca "
					+ "inner join ch.ligacaoTipo lt "
					+ "inner join ch.imovel im "
					+ "where im.id = :id and lt.id = :ligacaoTipoId and ch.referenciaFaturamento = :anoMes ";

			retorno = session.createQuery(consulta).setInteger("id",
					imovel.getId().intValue()).setInteger("ligacaoTipoId",
					ligacaoTipo.getId().intValue()).setInteger("anoMes",
					anoMesReferencia).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Consultar Histórico de Medição Individualizada
	 * 
	 * [UC0179] Consultar Histórico de Medição Individualizada
	 * 
	 * @author Pedro Alexandre
	 * @date 29/01/2008
	 * 
	 * @param imovel
	 * @param ligacaoTipo
	 * @param anoMesReferencia
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarConsumoHistorico(Imovel imovel,
			LigacaoTipo ligacaoTipo, int anoMesReferencia)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select ch.id, " + "ch.imovel.id, "
					+ "ch.ligacaoTipo.id, " + "ch.referenciaFaturamento, "
					+ "ch.indicadorAlteracaoUltimosConsumos, "
					+ "ch.indicadorAjuste, " + "ch.numeroConsumoFaturadoMes, "
					+ "ch.consumoRateio, "
					+ "ch.consumoHistoricoCondominio.id, "
					+ "ch.indicadorImovelCondominio, " + "ch.consumoMedio,"
					+ "ch.consumoMinimo, " + "ch.percentualColeta, "
					+ "ch.ultimaAlteracao, " + "ch.rateioTipo.id, "
					+ "ch.consumoTipo.id, " + "ch.consumoAnormalidade.id, "
					+ "ch.pocoTipo.id, " + "ch.faturamentoSituacaoTipo.id, "
					+ "ch.indicadorFaturamento, "
					+ "ch.consumoImovelVinculadosCondominio, "
					+ "ch.numeroConsumoFaturadoMes "
					+ "from ConsumoHistorico ch "
					+ "where ch.imovel.id = :id and "
					+ "ch.referenciaFaturamento = :anoMes and "
					+ "ch.ligacaoTipo.id = :ligacaoTipoId ";

			retorno = session.createQuery(consulta).setInteger("id",
					imovel.getId().intValue()).setInteger("ligacaoTipoId",
					ligacaoTipo.getId().intValue()).setInteger("anoMes",
					anoMesReferencia).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection pesquisarAnormalidadeLeitura(
			LeituraAnormalidade leituraAnormalidadeFaturamento)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select la.id, "
					+ "la.leituraAnormalidadeConsumoSemleitura.id, "
					+ "la.leituraAnormalidadeConsumoComleitura.id, "
					+ "la.leituraAnormalidadeLeituraSemleitura.id, "
					+ "la.leituraAnormalidadeLeituraComleitura.id, "
					+ "la.numeroFatorSemLeitura, "
					+ "la.numeroFatorComLeitura "
					+ "from LeituraAnormalidade la "
					+ "left join la.leituraAnormalidadeConsumoSemleitura "
					+ "left join la.leituraAnormalidadeConsumoComleitura "
					+ "left join la.leituraAnormalidadeLeituraSemleitura "
					+ "left join la.leituraAnormalidadeLeituraComleitura "
					+ "where la.id = :idLeituraAnormalidadeFaturamento";

			retorno = session.createQuery(consulta).setInteger(
					"idLeituraAnormalidadeFaturamento",
					leituraAnormalidadeFaturamento.getId().intValue()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 * 
	 * [SB0021] - Dados para Faturamento Especial Medido
	 * 
	 * @author Raphael Rossiter
	 * @date 12/08/2008
	 * 
	 * @param faturamentoSituacaoTipo
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarFaturamentoSituacaoTipo(
			FaturamentoSituacaoTipo faturamentoSituacaoTipo)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select fst.id, "
					+ "fst.leituraAnormalidadeConsumoSemLeitura.id, "
					+ "fst.leituraAnormalidadeConsumoComLeitura.id, "
					+ "fst.leituraAnormalidadeLeituraSemLeitura.id, "
					+ "fst.leituraAnormalidadeLeituraComLeitura.id "
					+ "from FaturamentoSituacaoTipo fst "
					+ "left join fst.leituraAnormalidadeConsumoSemLeitura "
					+ "left join fst.leituraAnormalidadeConsumoComLeitura "
					+ "left join fst.leituraAnormalidadeLeituraSemLeitura "
					+ "left join fst.leituraAnormalidadeLeituraComLeitura "
					+ "where fst.id = :idFaturamentoSituacaoTipo";

			retorno = session.createQuery(consulta).setInteger(
					"idFaturamentoSituacaoTipo",
					faturamentoSituacaoTipo.getId().intValue()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection pesquisarMaiorDataVigenciaConsumoTarifaImovel(
			Date dataCorrente, Imovel imovel) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String consulta = "select ctv.id, ctv.dataVigencia "
					+ "from ConsumoTarifaVigencia ctv  "
					+ "inner join ctv.consumoTarifa ct "
					+ "where ct.id = :consumoTarifaId "
					+ "and ctv.dataVigencia in "
					+ "(select max(ctv2.dataVigencia) from ConsumoTarifaVigencia ctv2 "
					+ "inner join ctv2.consumoTarifa ct2 "
					+ "where ct2.id = :consumoTarifaId and ctv2.dataVigencia  <= :dataCorrente)";

			retorno = session.createQuery(consulta).setDate("dataCorrente",
					new Date()).setInteger("consumoTarifaId",
					imovel.getConsumoTarifa().getId().intValue()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Object pesquisarConsumoMinimoTarifaCategoriaVigencia(
			Categoria categoria, ConsumoTarifaVigencia consumoTarifaVigencia)
			throws ErroRepositorioException {

		Object retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select ct.numeroConsumoMinimo "
					+ "from ConsumoTarifaCategoria ct "
					+ "inner join ct.consumoTarifaVigencia.consumoTarifa "
					+ "inner join ct.categoria "
					+ "where ct.consumoTarifaVigencia.id = :consumoTarifaVigenciaId "
					+ "and ct.categoria.id = :categoriaId "
					+ "and ct.subCategoria.id = :subCategoriaId ";

			retorno = session.createQuery(consulta).setInteger(
					"consumoTarifaVigenciaId", consumoTarifaVigencia.getId())
					.setInteger("categoriaId", categoria.getId()).setInteger(
							"subCategoriaId",
							Subcategoria.SUBCATEGORIA_ZERO.getId())
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public void inseriOuAtualizaMedicaoHistorico(
			Collection colecaoMedicaoHistorico) throws ErroRepositorioException {

		StatelessSession session = HibernateUtil.getStatelessSession();

		Iterator iteratorColecaoMedicaoHistorico = colecaoMedicaoHistorico
				.iterator();

		try {

			while (iteratorColecaoMedicaoHistorico.hasNext()) {

				MedicaoHistorico medicaoHistorico = (MedicaoHistorico) iteratorColecaoMedicaoHistorico
						.next();

				if (medicaoHistorico.getId() == null) {
					session.insert(medicaoHistorico);
				} else {
					session.update(medicaoHistorico);
				}

			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	public void inseriOuAtualizaConsumoHistorico(
			Collection colecaoConsumoHistorico) throws ErroRepositorioException {

		StatelessSession session = HibernateUtil.getStatelessSession();

		Iterator iteratorColecaoConsumoHistorico = colecaoConsumoHistorico
				.iterator();

		try {

			while (iteratorColecaoConsumoHistorico.hasNext()) {

				ConsumoHistorico consumoHistorico = (ConsumoHistorico) iteratorColecaoConsumoHistorico
						.next();

				if (consumoHistorico.getId() == null) {
					session.insert(consumoHistorico);
				} else {
					session.update(consumoHistorico);
				}

			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	public Collection pesquisarImoveisPorRota(Collection colecaoRota,
			Integer idLeituraTipo, int inicioPesquisa)
			throws ErroRepositorioException {
		Collection retorno = null;
		Collection idsRotas = new ArrayList();
		Iterator iteColecaoRotas = colecaoRota.iterator();
		while (iteColecaoRotas.hasNext()) {
			Rota rota = (Rota) iteColecaoRotas.next();
			idsRotas.add(rota.getId());
		}

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select imovel.id,"
					+ // 0
					"localidade.id,"
					+ // 1
					"setorComercial.codigo,"
					+ // 2
					"quadra.numeroQuadra,"
					+ // 3
					"imovel.lote,"
					+ // 4
					"imovel.subLote,"
					+ // 5
					"imovelPerfil.id,"
					+ // 6
					"ligacaoAgua.id,"
					+ // 7
					"hidInstHistoricoAgua.id,"
					+ // 8
					"hidInstHistoricoPoco.id,"
					+ // 9
					"rota.id,"
					+ // 10
					"rota.indicadorFiscalizarSuprimido,"
					+ // 11
					"rota.indicadorFiscalizarCortado,"
					+ // 12
					"rota.indicadorGerarFiscalizacao,"
					+ // 13
					"rota.indicadorGerarFalsaFaixa,"
					+ // 14
					"rota.percentualGeracaoFiscalizacao,"
					+ // 15
					"rota.percentualGeracaoFaixaFalsa,"
					+ // 16
					"empresa.id,"
					+ // 17
					"empresa.descricaoAbreviada,"
					+ // 18
					"empresa.email,"
					+ // 19
					"faturamentoGrupo.id,"
					+ // 20
					"faturamentoGrupo.descricao,"
					+ // 21
					"leituraTipo.id,"
					+ // 22
					"ligacaoAguaSituacao.id,"
					+ // 23
					"ligacaoEsgotoSituacao.id, "
					+ // 24
					"faturamentoSituacaoTipo.indicadorParalisacaoLeitura, "// 25
					+ "medTipoAgua.id, "// 26
					+ "medTipoPoco.id "// 27

					+ "from Imovel imovel "
					+ "left join imovel.localidade localidade "
					+ "left join imovel.setorComercial setorComercial "
					+ "left join imovel.quadra quadra "
					+ "left join imovel.imovelPerfil imovelPerfil "
					+ "left join imovel.ligacaoAgua ligacaoAgua "
					+ "left join imovel.hidrometroInstalacaoHistorico hidInstHistoricoPoco "
					+ "left join ligacaoAgua.hidrometroInstalacaoHistorico hidInstHistoricoAgua "
					+ "left join hidInstHistoricoAgua.medicaoTipo medTipoAgua "
					+ "left join hidInstHistoricoPoco.medicaoTipo medTipoPoco "
					+ "left join quadra.rota rota "
					+ "left join rota.empresa empresa "
					+ "left join rota.faturamentoGrupo faturamentoGrupo "
					+ "left join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "
					+ "left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "
					+ "left join imovel.faturamentoSituacaoTipo faturamentoSituacaoTipo "
					+ "left join rota.leituraTipo leituraTipo "

					+ "where rota.id in (:idsRotas) and "
					+ "leituraTipo.id = :idLeituraTipo "
					+ "order by empresa.id";

			retorno = session.createQuery(consulta).setInteger("idLeituraTipo",
					idLeituraTipo).setParameterList("idsRotas", idsRotas)
					.setFirstResult(inicioPesquisa).setMaxResults(1000).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection pesquisarDadosHidrometroTipoLigacaoAgua(Integer idImovel)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select hm.codigoHidrometroMarca," + // 0
					" hid.numero," + // 1
					" hc.codigoHidrometroCapacidade," + // 2
					" hli.id," + // 3
					" hih.dataInstalacao," + // 4
					" hp.id," + // 5
					" mt.id, " + // 6
					" hid.numeroDigitosLeitura, " + // 7
					" hc.id, " + // 8 Adicionado por Rodrigo 06/08/07
					" hm.id, " + // 9 Adicionado por Rodrigo 06/08/07
					" hm.descricao " // 10
					+ "from Imovel imovel "
					+ "left join imovel.ligacaoAgua la "
					+ "left join la.hidrometroInstalacaoHistorico hih "
					+ "left join hih.hidrometroLocalInstalacao hli "
					+ "left join hih.hidrometro hid "
					+ "left join hid.hidrometroMarca hm "
					+ "left join hid.hidrometroCapacidade hc "
					+ "left join hih.hidrometroProtecao hp "
					+ "left join hih.hidrometroLocalInstalacao hli "
					+ "left join hih.medicaoTipo mt "
					+ "where imovel.id = :idImovel and "
					+ "mt = :idMedicaoTipo";
			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel.intValue()).setInteger("idMedicaoTipo",
					MedicaoTipo.LIGACAO_AGUA.intValue()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	public Collection pesquisarDadosHidrometroTipoPoco(Integer idImovel)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select hm.codigoHidrometroMarca," + // 0
					" hid.numero," + // 1
					" hc.codigoHidrometroCapacidade," + // 2
					" hli.id," + // 3
					" hih.dataInstalacao," + // 4
					" hp.id," + // 5
					" mt.id, " + // 6
					" hid.numeroDigitosLeitura, " + // 7
					" hc.id, " + // 8 Adicionado por Rodrigo 06/08/07
					" hm.id, " + // 9 Adicionado por Rodrigo 06/08/07
					" hm.descricao " // 10
					+ "from Imovel imovel "
					+ "left join imovel.hidrometroInstalacaoHistorico hih "
					+ "left join hih.hidrometroLocalInstalacao hli "
					+ "left join hih.hidrometro hid "
					+ "left join hid.hidrometroMarca hm "
					+ "left join hid.hidrometroCapacidade hc "
					+ "left join hih.hidrometroProtecao hp "
					+ "left join hih.hidrometroLocalInstalacao hli "
					+ "left join hih.medicaoTipo mt "
					+ "where imovel.id = :idImovel and "
					+ "mt = :idMedicaoTipo";
			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel.intValue()).setInteger("idMedicaoTipo",
					MedicaoTipo.POCO.intValue()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;

	}

	public Collection pesquisarLeituraAnteriorTipoLigacaoAgua(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select md.leituraAtualFaturamento, "
					+ // 0
					"leituraSituacaoAtual.id, "
					+ // 1
					"md.id, "
					+ // 2
					"md.leituraAnteriorInformada,"
					+ // 3
					"md.leituraAtualInformada, "// 4
					+ "leituraAnormalidadeInformada.id, "// 5
					+ "leituraAnormalidadeInformada.descricao, "// 6
					+ "md.dataLeituraAtualFaturamento "// 7
					+ "from MedicaoHistorico md "
					+ "left join md.leituraSituacaoAtual leituraSituacaoAtual "
					+ "left join md.leituraAnormalidadeInformada leituraAnormalidadeInformada "
					+ "where md.ligacaoAgua.id = :idImovel and "
					+ "md.medicaoTipo.id = :idMedicaoTipo and "
					+ "md.anoMesReferencia = :anoMes ";

			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel.intValue()).setInteger("idMedicaoTipo",
					MedicaoTipo.LIGACAO_AGUA.intValue()).setInteger("anoMes",
					anoMes.intValue()).setMaxResults(1).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public Collection pesquisarLeituraAnteriorTipoPoco(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select md.leituraAtualFaturamento, "
					+ // 0
					"leituraSituacaoAtual.id, "
					+ // 1
					"md.id, "
					+ // 2
					"md.leituraAnteriorInformada,"
					+ // 3
					"md.leituraAtualInformada, "// 4
					+ "leituraAnormalidadeInformada.id, "// 5
					+ "leituraAnormalidadeInformada.descricao, "// 6
					+ "md.dataLeituraAtualFaturamento "// 7
					+ "from MedicaoHistorico md "
					+ "left join md.leituraSituacaoAtual leituraSituacaoAtual "
					+ "left join md.leituraAnormalidadeInformada leituraAnormalidadeInformada "
					+ "where md.imovel.id = :idImovel and "
					+ "md.medicaoTipo.id = :idMedicaoTipo and "
					+ "md.anoMesReferencia = :anoMes ";

			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel.intValue()).setInteger("idMedicaoTipo",
					MedicaoTipo.POCO.intValue()).setMaxResults(1).setInteger(
					"anoMes", anoMes.intValue()).setMaxResults(1).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public Collection pesquisarIdConsumoTarifaCategoria(Integer idImovel)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select consumoTarifa.id,"
					+ // 0
					"categoria.id " // 1
					+ "from ImovelSubcategoria imovelSubcategoria "
					+ "inner join imovelSubcategoria.comp_id.imovel imovel "
					+ "inner join imovel.consumoTarifa consumoTarifa "
					+ "inner join imovelSubcategoria.comp_id.subcategoria.categoria categoria "
					+ "where imovel.id = :idImovel "
					+ "order by imovelSubcategoria.quantidadeEconomias desc ";

			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel.intValue()).setMaxResults(1).list();
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public Integer pesquisarMaiorDataVigencia(Integer idConsumoTarifa)
			throws ErroRepositorioException {
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select consumoTarifaVigencia.id "
					+ "from ConsumoTarifaVigencia consumoTarifaVigencia "
					+ "inner join consumoTarifaVigencia.consumoTarifa consumoTarifa "
					+ "where consumoTarifa.id = :idConsumoTarifa "
					+ "order by consumoTarifaVigencia.dataVigencia desc ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idConsumoTarifa", idConsumoTarifa.intValue())
					.setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public Integer pesquisarConsumoMinimoTarifaCategoria(
			Integer idConsumoTarifaVigencia, Integer idCategoria)
			throws ErroRepositorioException {
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select consumoTarifaCategoria.numeroConsumoMinimo "
					+ "from ConsumoTarifaCategoria consumoTarifaCategoria "
					+ "inner join consumoTarifaCategoria.consumoTarifaVigencia consumoTarifaVigencia "
					+ "inner join consumoTarifaVigencia.consumoTarifa consumoTarifa "
					+ "inner join consumoTarifaCategoria.categoria categoria "
					+ "where consumoTarifaVigencia.id = :idConsumoTarifaVigencia and categoria.id = :idCategoria ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idConsumoTarifaVigencia",
					idConsumoTarifaVigencia.intValue()).setInteger(
					"idCategoria", idCategoria.intValue()).setMaxResults(1)
					.uniqueResult();
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public Integer verificarExistenciaHidrometroInstalacaoHistoricoTipoAgua(
			Integer idImovel) throws ErroRepositorioException {
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select hih.id "
					+ "from HidrometroInstalacaoHistorico hih "
					+ "where hih.ligacaoAgua.id = :idImovel and "
					+ "hih.medicaoTipo.id = :idMedicaoTipo and "
					+ "hih.dataRetirada is null ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel.intValue()).setInteger(
					"idMedicaoTipo", MedicaoTipo.LIGACAO_AGUA.intValue())
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public Integer verificarExistenciaHidrometroInstalacaoHistoricoTipoPoco(
			Integer idImovel) throws ErroRepositorioException {
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select hih.id "
					+ "from HidrometroInstalacaoHistorico hih "
					+ "where hih.imovel.id = :idImovel and "
					+ "hih.medicaoTipo.id = :idMedicaoTipo ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel.intValue()).setInteger(
					"idMedicaoTipo", MedicaoTipo.POCO.intValue())
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Pesquisa a medição histórico do imóvel.
	 * 
	 * [UC0179] Consultar Histórico de Medição Individualizada
	 * 
	 * @author Pedro Alexandre
	 * @date 28/01/2008
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public MedicaoHistorico pesquisarMedicaoHistoricoTipoAgua(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException {

		MedicaoHistorico retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "select mh " + "from MedicaoHistorico mh "
					+ "left join mh.ligacaoAgua la "
					+ "left join la.hidrometroInstalacaoHistorico hih "
					+ "where la.id = :idImovel and "
					+ "mh.medicaoTipo.id = :idMedicaoTipo and "
					+ "mh.anoMesReferencia = :anoMesReferencia";

			retorno = (MedicaoHistorico) session.createQuery(consulta)
					.setInteger("idImovel", idImovel).setInteger(
							"idMedicaoTipo", MedicaoTipo.LIGACAO_AGUA)
					.setInteger("anoMesReferencia", anoMes).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public MedicaoHistorico pesquisarMedicaoHistoricoTipoPoco(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException {
		MedicaoHistorico retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select mh " + "from MedicaoHistorico mh "
					+ "left join mh.imovel imovel "
					+ "left join imovel.hidrometroInstalacaoHistorico hih "
					+ "where imovel.id = :idImovel and "
					+ "mh.medicaoTipo.id = :idMedicaoTipo and "
					+ "mh.anoMesReferencia = :anoMesReferencia";

			retorno = (MedicaoHistorico) session.createQuery(consulta)
					.setInteger("idImovel", idImovel.intValue()).setInteger(
							"idMedicaoTipo", MedicaoTipo.POCO.intValue())
					.setInteger("anoMesReferencia", anoMes.intValue())
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	

	
	public MedicaoHistorico pesquisarMedicaoHistoricoTipoAguaLeituraAnormalidade(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException {

		MedicaoHistorico retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "select mh " + "from MedicaoHistorico mh "
					+ "left join mh.ligacaoAgua la "
					+ "left join fetch mh.leituraAnormalidadeFaturamento laf "
					+ "left join fetch mh.leituraAnormalidadeInformada lai "
					+ "left join la.hidrometroInstalacaoHistorico hih "
					+ "where la.id = :idImovel and "
					+ "mh.medicaoTipo.id = :idMedicaoTipo and "
					+ "mh.anoMesReferencia = :anoMesReferencia";

			retorno = (MedicaoHistorico) session.createQuery(consulta)
					.setInteger("idImovel", idImovel).setInteger(
							"idMedicaoTipo", MedicaoTipo.LIGACAO_AGUA)
					.setInteger("anoMesReferencia", anoMes).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public MedicaoHistorico pesquisarMedicaoHistoricoTipoPocoLeituraAnormalidade(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException {
		MedicaoHistorico retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select mh " + "from MedicaoHistorico mh "
					+ "left join mh.imovel imovel "
					+ "left join fetch mh.leituraAnormalidadeFaturamento laf "
					+ "left join fetch mh.leituraAnormalidadeInformada lai "
					+ "left join imovel.hidrometroInstalacaoHistorico hih "
					+ "where imovel.id = :idImovel and "
					+ "mh.medicaoTipo.id = :idMedicaoTipo and "
					+ "mh.anoMesReferencia = :anoMesReferencia";

			retorno = (MedicaoHistorico) session.createQuery(consulta)
					.setInteger("idImovel", idImovel.intValue()).setInteger(
							"idMedicaoTipo", MedicaoTipo.POCO.intValue())
					.setInteger("anoMesReferencia", anoMes.intValue())
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC0082] - Registrar Leituras e Anormalidades Autor: Sávio Luiz Data:
	 * 04/01/2006
	 */

	public Integer verificarExistenciaMedicaoTipo(Integer idMedicaoTipo)
			throws ErroRepositorioException {
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select mt.id " + "from MedicaoTipo mt "
					+ "where mt.id = :idMedicaoTipo";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idMedicaoTipo", idMedicaoTipo.intValue()).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0082] - Registrar Leituras e Anormalidades Autor: Sávio Luiz Data:
	 * 04/01/2006
	 */

	public Object[] pesquisarLeituraAnormalidade(Integer idLeituraAnormalidade)
			throws ErroRepositorioException {

		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select la.id,la.indicadorImovelSemHidrometro "
					+ "from LeituraAnormalidade la "
					+ "where la.id = :idLeituraAnormalidade";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idLeituraAnormalidade", idLeituraAnormalidade.intValue())
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;

	}

	/**
	 * [UC0082] - Registrar Leituras e Anormalidades Autor: Sávio Luiz Data:
	 * 04/01/2006
	 */

	public Object[] pesquisarMedicaoHistoricoAnterior(Integer idImovel,
			Integer anoMes, Integer idMedicaoTipo)
			throws ErroRepositorioException {
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select md.dataLeituraAtualFaturamento, "
					+ // 0
					"md.leituraAtualFaturamento, "
					+ // 1
					"md.leituraAtualInformada,"
					+ // 2
					"leituraSituacaoAtual.id, "// 3
					/**
					 * TODO : COSANPA
					 * Alteracao para retornar a anormalidade
					 */
					+ "leituraAnormalidadeInformada.id, "// 4
					+ " md.id, " // 5
					+ " md.dataLeituraAtualInformada " // 6
					+ "from MedicaoHistorico md "
					+ "left join md.leituraSituacaoAtual leituraSituacaoAtual "
					+ "left join md.hidrometroInstalacaoHistorico hidrometroInstalacaoHistorico "
					+ "left join md.leituraAnormalidadeInformada leituraAnormalidadeInformada "
					+ "where ";
			if (idMedicaoTipo.equals(MedicaoTipo.LIGACAO_AGUA)) {
				consulta = consulta + "md.ligacaoAgua.id = :idImovel and ";
			} else {
				consulta = consulta + "md.imovel.id = :idImovel and ";
			}

			consulta = consulta + "md.medicaoTipo.id = :idMedicaoTipo and "
					+ "md.anoMesReferencia = :anoMes ";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idImovel", idImovel.intValue()).setInteger(
					"idMedicaoTipo", idMedicaoTipo.intValue()).setInteger(
					"anoMes", anoMes.intValue()).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0082] - Registrar Leituras e Anormalidades Autor: Sávio Luiz Data:
	 * 04/01/2006
	 */

	public Object[] pesquisarMedicaoHistoricoAnteriorTipoPoco(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException {
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select md.dataLeituraAtualFaturamento, " + // 0
					"md.leituraAtualFaturamento, " + // 1
					"md.leituraAtualInformada," + // 2
					"leituraSituacaoAtual.id "// 3
					+ "from MedicaoHistorico md	"
					+ "left join md.leituraSituacaoAtual leituraSituacaoAtual "
					+ "where md.imovel.id = :idImovel and "
					+ "md.medicaoTipo.id = :idMedicaoTipo and "
					+ "md.anoMesReferencia = :anoMes ";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idImovel", idImovel.intValue()).setInteger(
					"idMedicaoTipo", MedicaoTipo.POCO.intValue()).setInteger(
					"anoMes", anoMes.intValue()).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0082] - Registrar Leituras e Anormalidades Autor: Sávio Luiz Data:
	 * 04/01/2006
	 */

	public Date pesquisarDataInstalacaoHidrometro(
			Integer idHidrometroInstalacaoHistorico)
			throws ErroRepositorioException {
		Date retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select hih.dataInstalacao "
					+ "from HidrometroInstalacaoHistorico hih "
					+ "where hih.id = :idHidrometroInstalacaoHistorico ";

			retorno = (Date) session.createQuery(consulta).setInteger(
					"idHidrometroInstalacaoHistorico",
					idHidrometroInstalacaoHistorico.intValue())
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0082] - Registrar Leituras e Anormalidades Autor: Sávio Luiz Data:
	 * 04/01/2006
	 */

	public void inserirAtualizarMedicaoHistorico(
			Collection medicoesHistoricosParaRegistrar)
			throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();

		Iterator iteratorMedicaoHistoricoParaImportar = medicoesHistoricosParaRegistrar
				.iterator();

		try {

			while (iteratorMedicaoHistoricoParaImportar.hasNext()) {
				MedicaoHistorico medicaoHistorico = (MedicaoHistorico) iteratorMedicaoHistoricoParaImportar
						.next();

				if (medicaoHistorico.getId() == null) {
					session.insert(medicaoHistorico);
				} else {
					session.update(medicaoHistorico);

				}

			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * Consultar Dados do Cliente Imovel Vinculado Auhtor: Rafael Santos Data:
	 * 23/01/2006
	 * 
	 * @param inscricaoImovel
	 *            Inscrição do Imovel
	 * @return Dados do Imovel Vinculado
	 * @throws ControladorException
	 */
	public Object[] consultarDadosClienteImovelUsuario(Imovel imovel)
			throws ErroRepositorioException {

		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select cliente.id,cliente.nome "
					+ "from ClienteImovel clienteImovel "
					+ "inner join clienteImovel.cliente cliente "
					+ "inner join clienteImovel.imovel imovel "
					+ "inner join clienteImovel.clienteRelacaoTipo clienteRelacaoTipo "
					+ "where imovel.id = :id and clienteRelacaoTipo.id = :usuario "
					+ "and clienteImovel.clienteImovelFimRelacaoMotivo is null";

			retorno = (Object[]) session.createQuery(consulta).setInteger("id",
					imovel.getId().intValue()).setInteger("usuario",
					ClienteRelacaoTipo.USUARIO).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;

	}

	/**
	 * Consultar Matriculas dos Imoveis Vinculados do Imovel condominio Auhtor:
	 * Rafael Santos Data: 23/01/2006 [UC0179] Consultar Historico Medição
	 * Indiviualizada
	 * 
	 * @param consumoHistorico
	 *            Consumo Historico
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarConsumoHistoricoImoveisVinculados(
			ConsumoHistorico consumoHistorico) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT imovel.id " 
					 + "FROM ConsumoHistorico consumo "
					 + 		"INNER JOIN consumo.imovel imovel "
					 + "WHERE consumo.consumoImovelCondominio = :id " 
					 + "ORDER BY imovel.indicadorImovelAreaComum";

			retorno = session.createQuery(consulta)
							 .setInteger("id", consumoHistorico.getId())
							 .list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * Consultar Consumo Tipo do Consumo Historico Auhtor: Rafael Santos Data:
	 * 23/01/2006
	 * 
	 * @param consumoHistorico
	 *            Consumo Historico
	 * @return Dados do Consumo Tipo
	 * @throws ControladorException
	 */
	public Object[] consultarDadosConsumoTipoConsumoHistorico(
			ConsumoHistorico consumoHistorico) throws ErroRepositorioException {
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select consumo.descricaoAbreviada,consumo.id, consumo.descricao  "
					+ "from ConsumoTipo consumo " + "where consumo.id = :id";

			retorno = (Object[]) session.createQuery(consulta).setInteger("id",
					consumoHistorico.getConsumoTipo().getId().intValue())
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Consultar Consumo Historico da Medicao Individualizada
	 * 
	 * [UC0179] Consultar Historico Medição Indiviualizada
	 * 
	 * @author Rafael Santos, Pedro Alexandre
	 * @date 23/01/2006, 24/01/2008
	 * 
	 * @param imovel
	 * @param ligacaoTipo
	 * @param anoMesFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterConsumoHistoricoMedicaoIndividualizada(Imovel imovel,
			LigacaoTipo ligacaoTipo, int anoMesFaturamento)
			throws ErroRepositorioException {

		Object[] retornoDados = null;
		Object retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "SELECT ch.id," 
					 + 		  "ch.consumoRateio, "
					 + 		  "ch.numeroConsumoFaturadoMes, " 
					 + 		  "consumoTipo.id, "
					 + 		  "ch.indicadorFaturamento, "
					 + 		  "consumoAnormalidade.id, "
					 + 		  "ch.consumoImovelVinculadosCondominio, "
					 + 		  "imovel.indicadorImovelAreaComum "
					 + "FROM ConsumoHistorico ch "
					 + 		"LEFT JOIN ch.consumoAnormalidade consumoAnormalidade "
					 + 		"LEFT JOIN ch.consumoTipo consumoTipo "
					 + 		"LEFT JOIN ch.imovel imovel "
					 + "WHERE ch.imovel.id = :id "
					 + 		"AND ch.referenciaFaturamento = :anoMes "
					 + 		"AND ch.ligacaoTipo.id = :ligacao ";

			retorno = session.createQuery(consulta)
							 .setInteger("id", imovel.getId())
							 .setInteger("anoMes", anoMesFaturamento)
							 .setInteger("ligacao", ligacaoTipo.getId())
							 .setMaxResults(1)
							 .uniqueResult();

			if (retorno != null) {
				retornoDados = (Object[]) retorno;
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retornoDados;

	}

	/**
	 * Pesquisa o imóvel condomínio com a situação de ligação de água (ligado ou
	 * cortado) e a situação da ligação de esgoto (ligado) [UC0103] Efetuar
	 * Rateio de Consumo Autor: Leonardo Vieira Data: 17/02/2006
	 */

	public Collection pesquisarImovelCondominio(
			Integer indicadorImovelCondominio,
			Integer ligacaoAguaSituacaoLigado,
			Integer ligacaoAguaSituacaoCortado,
			Integer ligacaoEsgotoSituacaoCortado)
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = " select im from Imovel im "
					+ "inner join im.ligacaoAguaSituacao las "
					+ "inner join im.ligacaoEsgotoSituacao les "
					+ " where im.indicadorImovelCondominio = indicadorImovelCondominio "
					+ " and (las.id = ligado or las.id = cortado)  "
					+ " or (les.id = 3 and im.hidrometroInstalacaoHistorico != null) ";

			retorno = session.createQuery(consulta).setInteger(
					"indicadorImovelCondominio",
					indicadorImovelCondominio.intValue()).setInteger(
					"ligacaoAguaSituacaoLigado",
					ligacaoAguaSituacaoLigado.intValue()).setInteger(
					"ligacaoAguaSituacaoCortado",
					ligacaoAguaSituacaoCortado.intValue()).setInteger(
					"ligacaoEsgotoSituacaoCortado",
					ligacaoEsgotoSituacaoCortado.intValue()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * Consultar Consumo Historico da Medicao Individualizada [UC0113] Faturar
	 * Grupo Faturamento Auhtor: Rafael Santos Data: 20/02/2006
	 * 
	 * @param idImovel
	 * @param idAnormalidade
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterConsumoHistoricoAnormalidade(Integer idImovel,
			Integer idAnormalidade, int anoMes) throws ErroRepositorioException {

		Object[] retornoDados = null;
		Object retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select ch.id " + "from ConsumoHistorico ch "
					+ "inner join ch.consumoAnormalidade where "
					+ "ch.imovel.id = :id  "
					+ "and ch.referenciaFaturamento = :anoMes "
					+ " and consumoAnormalidade.id = :idAnormalidade "
					+ "and ch.ligacaoTipo.id = :ligacao";

			retorno = session.createQuery(consulta).setInteger("id",
					idImovel.intValue()).setInteger("anoMes", anoMes)
					.setInteger("idAnormalidade", idAnormalidade.intValue())
					.setMaxResults(1).uniqueResult();

			if (retorno != null) {
				retornoDados = (Object[]) retorno;
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retornoDados;

	}

	/**
	 * Consultar Imoveis com Medição Indiviualizada Auhtor: Sávio Luiz Data:
	 * 06/02/2006 [UC0180] Consultar Imoveis com Medição Indiviualizada
	 * 
	 */

	public String pesquisarDescricaoRateioTipoLigacaoAgua(Integer idImovel)
			throws ErroRepositorioException {
		String retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select rt.descricao "
					+ "from HidrometroInstalacaoHistorico hih "
					+ "inner join hih.rateioTipo rt "
					+ "inner join hih.ligacaoAgua la "
					+ "where la.id = :idImovel";

			retorno = (String) session.createQuery(consulta).setInteger(
					"idImovel", idImovel.intValue()).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;

	}

	/**
	 * Consultar Imoveis com Medição Indiviualizada Auhtor: Sávio Luiz Data:
	 * 06/02/2006 [UC0180] Consultar Imoveis com Medição Indiviualizada
	 * 
	 */

	public String pesquisarDescricaoRateioTipoLigacaoEsgoto(Integer idImovel)
			throws ErroRepositorioException {
		String retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select rt.descricao "
					+ "from HidrometroInstalacaoHistorico hih "
					+ "inner join hih.rateioTipo rt "
					+ "inner join hih.imovel imov "
					+ "where imov.id = :idImovel";

			retorno = (String) session.createQuery(consulta).setInteger(
					"idImovel", idImovel.intValue()).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0121] - Filtrar Exceções de Leituras e Consumos
	 * 
	 * @author Flávio Leonardo, Raphael Rossiter
	 * @date 00/00/0000, 06/10/2009
	 * 
	 * @param filtroMedicaoHistoricoSql
	 * @param faturamentoGrupo
	 * @param numeroPagina
	 * @param todosRegistros
	 * @param anoMes
	 * @param pesquisarPorRotaAlternativa
	 * @return Integer
	 * @throws ControladorException
	 */
	public Collection pesquisarImovelExcecoesLeituras(
			FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql,
			FaturamentoGrupo faturamentoGrupo, Integer numeroPagina,
			boolean todosRegistros, String anoMes, String valorAguaEsgotoInicial,
			String valorAguaEsgotoFinal, boolean pesquisarPorRotaAlternativa)
			throws ErroRepositorioException {

		Collection retorno = null;

		Collection medicaohistoricoParametros = filtroMedicaoHistoricoSql
				.getParametros();

		Session session = HibernateUtil.getSession();

		String sqlRota = "";

		if (!medicaohistoricoParametros.isEmpty()
				&& medicaohistoricoParametros.size() >= 1) {

			sqlRota = "select  distinct(imovel.imov_id) as idImovel,"// 0
					+ " imovel.loca_id as idLocalidade, " // 01
					+ " setorComercial.stcm_cdsetorcomercial as codigoSetorComercial, "// 02
					+ " quadra.qdra_nnquadra as numeroQuadra, " // 03
					+ " imovel.imov_nnlote as lote, " // 04
					+ " imovel.imov_nnsublote as subLote,"// 05
					+ " imovelPerfil.iper_dsimovelperfil as dsImovelPerfil, " // 06
					+ " medicaoTipo.medt_dsmedicaotipo as dsMedicaoTipo," // 07
					+ " consumoHistorico.cshi_nnconsumofaturadomes as numeroConsumoFaturadoMes,"// 08
					+ " leituraAnormalidadeFaturamento.ltan_dsleituraanormalidade as dsleituraanormalidadefaturamen,"// 09
					+ " consumoAnormalidade.csan_dsconsumoanormalidade as dsConsumoAnormalidade," // 10
					+ " medicaoTipo.medt_id as idMedicaoTipo, "// 11
					+ " medicaoHistorico.mdhi_id as idMedicaoHistorico, " // 12
					+ " medicaoHistorico.mdhi_icanalisado as indicadorAnalisado, " // 13
					+ " medicaoHistorico.mdhi_nnconsumomediohidrometro as consumoMedioHidrometro, " // 14
					+ " medicaoHistorico.mdhi_nnleituracampo as leituraCampo, " // 15
					+ " medicaoHistorico.mdhi_dtleituracampo as dataLeituraCampo, " // 16
					+ " cliente.clie_nmcliente as nomeCliente, " // 17
					+ " funcionario.func_nmfuncionario as nomeFuncionario, " // 18
					+ " localidade.loca_nmlocalidade as nomeLocalidade, " // 19
					+ " imovel.poco_id as idPocoTipo, imovel.prua_id as pavimentoRua, imovel.pcal_id as pavimentoCalcada, " // 20,
					// 21,
					// 22
					+ " setorComercial.stcm_id as idSetorComercial, quadra.qdra_id as idQuadra, " // 23,
					// 24
					+ " usuario.usur_nmlogin as loginUsuario, usuario.usur_nmusuario as nomeUsuario, " // 25,
					// 26
					+ " rota.rota_id as idRota, " // 27
					+ " tipoLeitura.lttp_id as idTipoLeitura " // 28
					+ " from cadastro.imovel imovel"
					+ " inner join micromedicao.consumo_historico consumoHistorico on imovel.imov_id=consumoHistorico.imov_id and consumoHistorico.cshi_amfaturamento = "
					+ anoMes
					+ "  and consumoHistorico.lgti_id = "
					+ LigacaoTipo.LIGACAO_AGUA
					+ " inner join micromedicao.medicao_historico medicaoHistorico on  consumoHistorico.imov_id=medicaoHistorico.lagu_id and medicaoHistorico.mdhi_amleitura =  "
					+ anoMes
					+ " and medicaoHistorico.medt_id = "
					+ LigacaoTipo.LIGACAO_AGUA

					+ " inner join cadastro.imovel_subcategoria imovelSubcategoria on imovel.imov_id=imovelSubcategoria.imov_id  "
					+ " inner join cadastro.setor_comercial setorComercial on imovel.stcm_id=setorComercial.stcm_id  "
					+ " inner join cadastro.quadra quadra on imovel.qdra_id=quadra.qdra_id"
					+ " inner join micromedicao.rota rota on quadra.rota_id=rota.rota_id"
					+ " inner join micromedicao.leitura_tipo tipoLeitura on rota.lttp_id=tipoLeitura.lttp_id"

					+ " left outer join faturamento.faturamento_grupo faturamentoGrupo on rota.ftgr_id=faturamentoGrupo.ftgr_id  left outer join cadastro.empresa empresa on rota.empr_id=empresa.empr_id"
					+ " left outer join cadastro.imovel_perfil imovelPerfil on imovel.iper_id=imovelPerfil.iper_id"
					+ " left outer join cadastro.subcategoria subcategoria on imovelSubcategoria.scat_id=subcategoria.scat_id"
					+ " left outer join micromedicao.consumo_anormalidade consumoAnormalidade on consumoHistorico.csan_id=consumoAnormalidade.csan_id"
					+ " left outer join cadastro.categoria categoria on subcategoria.catg_id=categoria.catg_id"
					+ " left outer join micromedicao.medicao_tipo medicaoTipo on medicaoHistorico.medt_id=medicaoTipo.medt_id"
					+ " left outer join micromedicao.leitura_anormalidade leituraAnormalidadeFaturamento on medicaohistorico.ltan_idleitanormfatmt=leituraAnormalidadeFaturamento.ltan_id"
					+ " left outer join micromedicao.leiturista leiturista on medicaoHistorico.leit_id=leiturista.leit_id"
					+ " left outer join cadastro.cliente cliente on leiturista.clie_id=cliente.clie_id"
					+ " left outer join cadastro.funcionario funcionario on leiturista.func_id=funcionario.func_id"
					+ " left outer join cadastro.localidade localidade on localidade.loca_id=imovel.loca_id"
					+ " left outer join seguranca.usuario usuario on medicaoHistorico.usur_idalteracao=usuario.usur_id"
					
/////////////////////////////////////////////////////
					+ " left outer join faturamento.conta cnta on cnta.imov_id = imovel.imov_id and cnta.cnta_amreferenciaconta = " + anoMes
					+ " and cnta.dcst_idatual in ( " + DebitoCreditoSituacao.NORMAL +"," + DebitoCreditoSituacao.PRE_FATURADA + ")"
					+ " left outer join faturamento.conta_historico cnhi on cnhi.imov_id = imovel.imov_id and cnhi.cnhi_amreferenciaconta = " + anoMes
					+ " and cnhi.dcst_idatual in ( " + DebitoCreditoSituacao.NORMAL +"," + DebitoCreditoSituacao.PRE_FATURADA + ")"
////////////////////////////////////////////////////					/

					
					+ " where ";

			String sqlRotaSegundaParte = " select  distinct(imovel.imov_id) as idImovel,imovel.loca_id as idLocalidade, setorComercial.stcm_cdsetorcomercial as codigoSetorComercial,"
					+ " quadra.qdra_nnquadra as numeroQuadra, imovel.imov_nnlote as lote, imovel.imov_nnsublote as subLote,"
					+ " imovelPerfil.iper_dsimovelperfil as dsImovelPerfil, medicaoTipo.medt_dsmedicaotipo as dsMedicaoTipo,"
					+ " consumoHistorico.cshi_nnconsumofaturadomes as numeroConsumoFaturadoMes,"
					+ " leituraAnormalidadeFaturamento.ltan_dsleituraanormalidade as dsleituraanormalidadefaturamen,"
					+ " consumoAnormalidade.csan_dsconsumoanormalidade as dsConsumoAnormalidade,"
					+ " medicaoTipo.medt_id as idMedicaoTipo, "
					+ " medicaoHistorico.mdhi_id as idMedicaoHistorico, "
					+ " medicaoHistorico.mdhi_icanalisado as indicadorAnalisado, "
					+ " medicaoHistorico.mdhi_nnconsumomediohidrometro as consumoMedioHidrometro, "
					+ " medicaoHistorico.mdhi_nnleituracampo as leituraCampo, "
					+ " medicaoHistorico.mdhi_dtleituracampo as dataLeituraCampo, "
					+ " cliente.clie_nmcliente as nomeCliente, "
					+ " funcionario.func_nmfuncionario as nomeFuncionario, "
					+ " localidade.loca_nmlocalidade as nomeLocalidade, "
					+ " imovel.poco_id as idPocoTipo, imovel.prua_id as pavimentoRua, imovel.pcal_id as pavimentoCalcada, "
					+ " setorComercial.stcm_id as idSetorComercial, quadra.qdra_id as idQuadra, "
					+ " usuario.usur_nmlogin as loginUsuario, usuario.usur_nmusuario as nomeUsuario, "
					+ " rota.rota_id as idRota, " // 27
					+ " tipoLeitura.lttp_id as idTipoLeitura " // 28
					+ " from cadastro.imovel imovel"
					+ " inner join micromedicao.consumo_historico consumoHistorico on imovel.imov_id=consumoHistorico.imov_id and consumoHistorico.cshi_amfaturamento =  "
					+ anoMes
					+ "  and consumoHistorico.lgti_id = "
					+ LigacaoTipo.LIGACAO_ESGOTO
					+ " inner join micromedicao.medicao_historico medicaoHistorico on  consumoHistorico.imov_id=medicaoHistorico.imov_id and medicaoHistorico.mdhi_amleitura =  "
					+ anoMes
					+ " and medicaoHistorico.medt_id = "
					+ LigacaoTipo.LIGACAO_ESGOTO

					+ " inner join cadastro.imovel_subcategoria imovelSubcategoria on imovel.imov_id=imovelSubcategoria.imov_id  "
					+ " inner join cadastro.setor_comercial setorComercial on imovel.stcm_id=setorComercial.stcm_id  "
					+ " inner join cadastro.quadra quadra on imovel.qdra_id=quadra.qdra_id "
					+ " inner join micromedicao.rota rota on quadra.rota_id=rota.rota_id"
					+ " inner join micromedicao.leitura_tipo tipoLeitura on rota.lttp_id=tipoLeitura.lttp_id"

					+ " left outer join faturamento.faturamento_grupo faturamentoGrupo on rota.ftgr_id=faturamentoGrupo.ftgr_id  left outer join cadastro.empresa empresa on rota.empr_id=empresa.empr_id"
					+ " left outer join cadastro.imovel_perfil imovelPerfil on imovel.iper_id=imovelPerfil.iper_id"
					+ " left outer join cadastro.subcategoria subcategoria on imovelSubcategoria.scat_id=subcategoria.scat_id"
					+ " left outer join micromedicao.consumo_anormalidade consumoAnormalidade on consumoHistorico.csan_id=consumoAnormalidade.csan_id"
					+ " left outer join cadastro.categoria categoria on subcategoria.catg_id=categoria.catg_id"
					+ " left outer join micromedicao.medicao_tipo medicaoTipo on medicaoHistorico.medt_id=medicaoTipo.medt_id"
					+ " left outer join micromedicao.leitura_anormalidade leituraAnormalidadeFaturamento on medicaohistorico.ltan_idleitanormfatmt=leituraAnormalidadeFaturamento.ltan_id"
					+ " left outer join micromedicao.leiturista leiturista on medicaoHistorico.leit_id=leiturista.leit_id"
					+ " left outer join cadastro.cliente cliente on leiturista.clie_id=cliente.clie_id"
					+ " left outer join cadastro.funcionario funcionario on leiturista.func_id=funcionario.func_id"
					+ " left outer join cadastro.localidade localidade on localidade.loca_id=imovel.loca_id"
					+ " left outer join seguranca.usuario usuario on medicaoHistorico.usur_idalteracao=usuario.usur_id"
					
/////////////////////////////////////////////////////
					+ " left outer join faturamento.conta cnta on cnta.imov_id = imovel.imov_id and cnta.cnta_amreferenciaconta = " + anoMes
					+ " and cnta.dcst_idatual in ( " + DebitoCreditoSituacao.NORMAL +"," + DebitoCreditoSituacao.PRE_FATURADA + ")"
					+ " left outer join faturamento.conta_historico cnhi on cnhi.imov_id = imovel.imov_id and cnhi.cnhi_amreferenciaconta = " + anoMes
					+ " and cnhi.dcst_idatual in ( " + DebitoCreditoSituacao.NORMAL +"," + DebitoCreditoSituacao.PRE_FATURADA + ")"
////////////////////////////////////////////////////
					
					+ " where ";

			sqlRota = criarCondicionalPesquisaAnaliseExcecoesLeituras(
					medicaohistoricoParametros, sqlRota, sqlRotaSegundaParte,
					pesquisarPorRotaAlternativa, false, valorAguaEsgotoInicial, valorAguaEsgotoFinal);

			if (pesquisarPorRotaAlternativa) {

				String sqlRotaAlternativa = "select  distinct(imovel.imov_id) as idImovel,"// 0
						+ " imovel.loca_id as idLocalidade, " // 01
						+ " setorComercial.stcm_cdsetorcomercial as codigoSetorComercial, "// 02
						+ " quadra.qdra_nnquadra as numeroQuadra, " // 03
						+ " imovel.imov_nnlote as lote, " // 04
						+ " imovel.imov_nnsublote as subLote,"// 05
						+ " imovelPerfil.iper_dsimovelperfil as dsImovelPerfil, " // 06
						+ " medicaoTipo.medt_dsmedicaotipo as dsMedicaoTipo," // 07
						+ " consumoHistorico.cshi_nnconsumofaturadomes as numeroConsumoFaturadoMes,"// 08
						+ " leituraAnormalidadeFaturamento.ltan_dsleituraanormalidade as dsleituraanormalidadefaturamen,"// 09
						+ " consumoAnormalidade.csan_dsconsumoanormalidade as dsConsumoAnormalidade," // 10
						+ " medicaoTipo.medt_id as idMedicaoTipo, "// 11
						+ " medicaoHistorico.mdhi_id as idMedicaoHistorico, " // 12
						+ " medicaoHistorico.mdhi_icanalisado as indicadorAnalisado, " // 13
						+ " medicaoHistorico.mdhi_nnconsumomediohidrometro as consumoMedioHidrometro, " // 14
						+ " medicaoHistorico.mdhi_nnleituracampo as leituraCampo, " // 15
						+ " medicaoHistorico.mdhi_dtleituracampo as dataLeituraCampo, " // 16
						+ " cliente.clie_nmcliente as nomeCliente, " // 17
						+ " funcionario.func_nmfuncionario as nomeFuncionario, " // 18
						+ " localidade.loca_nmlocalidade as nomeLocalidade, " // 19
						+ " imovel.poco_id as idPocoTipo, imovel.prua_id as pavimentoRua, imovel.pcal_id as pavimentoCalcada, " // 20,
						// 21,
						// 22
						+ " setorComercial.stcm_id as idSetorComercial, quadra.qdra_id as idQuadra, " // 23,
						// 24
						+ " usuario.usur_nmlogin as loginUsuario, usuario.usur_nmusuario as nomeUsuario, " // 25,
						// 26
						+ " rota.rota_id as idRota, " // 27
						+ " tipoLeitura.lttp_id as idTipoLeitura " // 28
						+ " from cadastro.imovel imovel"
						+ " inner join micromedicao.consumo_historico consumoHistorico on imovel.imov_id=consumoHistorico.imov_id and consumoHistorico.cshi_amfaturamento = "
						+ anoMes
						+ " and consumoHistorico.lgti_id = "
						+ LigacaoTipo.LIGACAO_AGUA
						+ " inner join micromedicao.medicao_historico medicaoHistorico on  consumoHistorico.imov_id=medicaoHistorico.lagu_id and medicaoHistorico.mdhi_amleitura = "
						+ anoMes
						+ " and medicaoHistorico.medt_id = "
						+ LigacaoTipo.LIGACAO_AGUA

						+ " inner join cadastro.imovel_subcategoria imovelSubcategoria on imovel.imov_id=imovelSubcategoria.imov_id  "
						+ " inner join cadastro.setor_comercial setorComercial on imovel.stcm_id=setorComercial.stcm_id  "
						+ " inner join cadastro.quadra quadra on imovel.qdra_id=quadra.qdra_id"
						+ " inner join micromedicao.rota rota on imovel.rota_idalternativa=rota.rota_id"
						+ " inner join micromedicao.leitura_tipo tipoLeitura on rota.lttp_id=tipoLeitura.lttp_id"

						+ " left outer join faturamento.faturamento_grupo faturamentoGrupo on rota.ftgr_id=faturamentoGrupo.ftgr_id  left outer join cadastro.empresa empresa on rota.empr_id=empresa.empr_id"
						+ " left outer join cadastro.imovel_perfil imovelPerfil on imovel.iper_id=imovelPerfil.iper_id"
						+ " left outer join cadastro.subcategoria subcategoria on imovelSubcategoria.scat_id=subcategoria.scat_id"
						+ " left outer join micromedicao.consumo_anormalidade consumoAnormalidade on consumoHistorico.csan_id=consumoAnormalidade.csan_id"
						+ " left outer join cadastro.categoria categoria on subcategoria.catg_id=categoria.catg_id"
						+ " left outer join micromedicao.medicao_tipo medicaoTipo on medicaoHistorico.medt_id=medicaoTipo.medt_id"
						+ " left outer join micromedicao.leitura_anormalidade leituraAnormalidadeFaturamento on medicaohistorico.ltan_idleitanormfatmt=leituraAnormalidadeFaturamento.ltan_id"
						+ " left outer join micromedicao.leiturista leiturista on medicaoHistorico.leit_id=leiturista.leit_id"
						+ " left outer join cadastro.cliente cliente on leiturista.clie_id=cliente.clie_id"
						+ " left outer join cadastro.funcionario funcionario on leiturista.func_id=funcionario.func_id"
						+ " left outer join cadastro.localidade localidade on localidade.loca_id=imovel.loca_id"
						+ " left outer join seguranca.usuario usuario on medicaoHistorico.usur_idalteracao=usuario.usur_id"
						
/////////////////////////////////////////////////////
						+ " left outer join faturamento.conta cnta on cnta.imov_id = imovel.imov_id and cnta.cnta_amreferenciaconta = " + anoMes
						+ " and cnta.dcst_idatual in ( " + DebitoCreditoSituacao.NORMAL +"," + DebitoCreditoSituacao.PRE_FATURADA + ")"
						+ " left outer join faturamento.conta_historico cnhi on cnhi.imov_id = imovel.imov_id and cnhi.cnhi_amreferenciaconta = " + anoMes
						+ " and cnhi.dcst_idatual in ( " + DebitoCreditoSituacao.NORMAL +"," + DebitoCreditoSituacao.PRE_FATURADA + ")"
//////////////////////////////////////////////////	//
						
						+ " where ";

				String sqlRotaAlternativaSegundaParte = " select  distinct(imovel.imov_id) as idImovel,imovel.loca_id as idLocalidade, setorComercial.stcm_cdsetorcomercial as codigoSetorComercial,"
						+ " quadra.qdra_nnquadra as numeroQuadra, imovel.imov_nnlote as lote, imovel.imov_nnsublote as subLote,"
						+ " imovelPerfil.iper_dsimovelperfil as dsImovelPerfil, medicaoTipo.medt_dsmedicaotipo as dsMedicaoTipo,"
						+ " consumoHistorico.cshi_nnconsumofaturadomes as numeroConsumoFaturadoMes,"
						+ " leituraAnormalidadeFaturamento.ltan_dsleituraanormalidade as dsleituraanormalidadefaturamen,"
						+ " consumoAnormalidade.csan_dsconsumoanormalidade as dsConsumoAnormalidade,"
						+ " medicaoTipo.medt_id as idMedicaoTipo, "
						+ " medicaoHistorico.mdhi_id as idMedicaoHistorico, "
						+ " medicaoHistorico.mdhi_icanalisado as indicadorAnalisado, "
						+ " medicaoHistorico.mdhi_nnconsumomediohidrometro as consumoMedioHidrometro, "
						+ " medicaoHistorico.mdhi_nnleituracampo as leituraCampo, "
						+ " medicaoHistorico.mdhi_dtleituracampo as dataLeituraCampo, "
						+ " cliente.clie_nmcliente as nomeCliente, "
						+ " funcionario.func_nmfuncionario as nomeFuncionario, "
						+ " localidade.loca_nmlocalidade as nomeLocalidade, "
						+ " imovel.poco_id as idPocoTipo, imovel.prua_id as pavimentoRua, imovel.pcal_id as pavimentoCalcada, "
						+ " setorComercial.stcm_id as idSetorComercial, quadra.qdra_id as idQuadra, "
						+ " usuario.usur_nmlogin as loginUsuario, usuario.usur_nmusuario as nomeUsuario, "
						+ " rota.rota_id as idRota, " // 27
						+ " tipoLeitura.lttp_id as idTipoLeitura " // 28
						+ " from cadastro.imovel imovel"
						+ " inner join micromedicao.consumo_historico consumoHistorico on imovel.imov_id=consumoHistorico.imov_id and consumoHistorico.cshi_amfaturamento =  "
						+ anoMes
						+ "  and consumoHistorico.lgti_id = "
						+ LigacaoTipo.LIGACAO_ESGOTO
						+ " inner join micromedicao.medicao_historico medicaoHistorico on  consumoHistorico.imov_id=medicaoHistorico.imov_id and medicaoHistorico.mdhi_amleitura =  "
						+ anoMes
						+ " and medicaoHistorico.medt_id = "
						+ LigacaoTipo.LIGACAO_ESGOTO

						+ " inner join cadastro.imovel_subcategoria imovelSubcategoria on imovel.imov_id=imovelSubcategoria.imov_id  "
						+ " inner join cadastro.setor_comercial setorComercial on imovel.stcm_id=setorComercial.stcm_id  "
						+ " inner join cadastro.quadra quadra on imovel.qdra_id=quadra.qdra_id "
						+ " inner join micromedicao.rota rota on imovel.rota_idalternativa=rota.rota_id"
						+ " inner join micromedicao.leitura_tipo tipoLeitura on rota.lttp_id=tipoLeitura.lttp_id"

						+ " left outer join faturamento.faturamento_grupo faturamentoGrupo on rota.ftgr_id=faturamentoGrupo.ftgr_id  left outer join cadastro.empresa empresa on rota.empr_id=empresa.empr_id"
						+ " left outer join cadastro.imovel_perfil imovelPerfil on imovel.iper_id=imovelPerfil.iper_id"
						+ " left outer join cadastro.subcategoria subcategoria on imovelSubcategoria.scat_id=subcategoria.scat_id"
						+ " left outer join micromedicao.consumo_anormalidade consumoAnormalidade on consumoHistorico.csan_id=consumoAnormalidade.csan_id"
						+ " left outer join cadastro.categoria categoria on subcategoria.catg_id=categoria.catg_id"
						+ " left outer join micromedicao.medicao_tipo medicaoTipo on medicaoHistorico.medt_id=medicaoTipo.medt_id"
						+ " left outer join micromedicao.leitura_anormalidade leituraAnormalidadeFaturamento on medicaohistorico.ltan_idleitanormfatmt=leituraAnormalidadeFaturamento.ltan_id"
						+ " left outer join micromedicao.leiturista leiturista on medicaoHistorico.leit_id=leiturista.leit_id"
						+ " left outer join cadastro.cliente cliente on leiturista.clie_id=cliente.clie_id"
						+ " left outer join cadastro.funcionario funcionario on leiturista.func_id=funcionario.func_id"
						+ " left outer join cadastro.localidade localidade on localidade.loca_id=imovel.loca_id"
						+ " left outer join seguranca.usuario usuario on medicaoHistorico.usur_idalteracao=usuario.usur_id"
						
/////////////////////////////////////////////////////
						+ " left outer join faturamento.conta cnta on cnta.imov_id = imovel.imov_id and cnta.cnta_amreferenciaconta = " + anoMes
						+ " and cnta.dcst_idatual in ( " + DebitoCreditoSituacao.NORMAL +"," + DebitoCreditoSituacao.PRE_FATURADA + ")"
						+ " left outer join faturamento.conta_historico cnhi on cnhi.imov_id = imovel.imov_id and cnhi.cnhi_amreferenciaconta = " + anoMes
						+ " and cnhi.dcst_idatual in ( " + DebitoCreditoSituacao.NORMAL +"," + DebitoCreditoSituacao.PRE_FATURADA + ")"
////////////////////////////////////////////////////
						
						+ " where ";

				sqlRotaAlternativa = criarCondicionalPesquisaAnaliseExcecoesLeituras(
						medicaohistoricoParametros, sqlRotaAlternativa,
						sqlRotaAlternativaSegundaParte, false, false, valorAguaEsgotoInicial, valorAguaEsgotoFinal);

				sqlRota = sqlRota + " UNION " + sqlRotaAlternativa;
			}

		}
		try {
			if (todosRegistros) {
				retorno = session.createSQLQuery(sqlRota).addScalar("idImovel",
						Hibernate.INTEGER).addScalar("idLocalidade",
						Hibernate.INTEGER).addScalar("codigoSetorComercial",
						Hibernate.INTEGER).addScalar("numeroQuadra",
						Hibernate.INTEGER).addScalar("lote", Hibernate.SHORT)
						.addScalar("subLote", Hibernate.SHORT).addScalar(
								"dsImovelPerfil", Hibernate.STRING).addScalar(
								"dsMedicaoTipo", Hibernate.STRING).addScalar(
								"numeroConsumoFaturadoMes", Hibernate.INTEGER)
						.addScalar("dsleituraanormalidadefaturamen",
								Hibernate.STRING).addScalar(
								"dsConsumoAnormalidade", Hibernate.STRING)
						.addScalar("idMedicaoTipo", Hibernate.INTEGER)
						.addScalar("idMedicaoHistorico", Hibernate.INTEGER)
						.addScalar("indicadorAnalisado", Hibernate.SHORT)
						.addScalar("consumoMedioHidrometro", Hibernate.INTEGER)
						.addScalar("leituraCampo", Hibernate.INTEGER)
						.addScalar("dataLeituraCampo", Hibernate.DATE)
						.addScalar("nomeCliente", Hibernate.STRING).addScalar(
								"nomeFuncionario", Hibernate.STRING).addScalar(
								"nomeLocalidade", Hibernate.STRING).addScalar(
								"idPocoTipo", Hibernate.INTEGER).addScalar(
								"pavimentoRua", Hibernate.INTEGER).addScalar(
								"pavimentoCalcada", Hibernate.INTEGER)
						.addScalar("idSetorComercial", Hibernate.INTEGER)
						.addScalar("idQuadra", Hibernate.INTEGER).addScalar(
								"loginUsuario", Hibernate.STRING).addScalar(
								"nomeUsuario", Hibernate.STRING).addScalar(
								"idRota", Hibernate.INTEGER).addScalar(
								"idTipoLeitura", Hibernate.INTEGER).list();
			} else {
				retorno = session.createSQLQuery(sqlRota).addScalar("idImovel",
						Hibernate.INTEGER).addScalar("idLocalidade",
						Hibernate.INTEGER).addScalar("codigoSetorComercial",
						Hibernate.INTEGER).addScalar("numeroQuadra",
						Hibernate.INTEGER).addScalar("lote", Hibernate.SHORT)
						.addScalar("subLote", Hibernate.SHORT).addScalar(
								"dsImovelPerfil", Hibernate.STRING).addScalar(
								"dsMedicaoTipo", Hibernate.STRING).addScalar(
								"numeroConsumoFaturadoMes", Hibernate.INTEGER)
						.addScalar("dsleituraanormalidadefaturamen",
								Hibernate.STRING).addScalar(
								"dsConsumoAnormalidade", Hibernate.STRING)
						.addScalar("idMedicaoTipo", Hibernate.INTEGER)
						.addScalar("idMedicaoHistorico", Hibernate.INTEGER)
						.addScalar("indicadorAnalisado", Hibernate.SHORT)
						.addScalar("consumoMedioHidrometro", Hibernate.INTEGER)
						.addScalar("leituraCampo", Hibernate.INTEGER)
						.addScalar("dataLeituraCampo", Hibernate.DATE)
						.addScalar("nomeCliente", Hibernate.STRING).addScalar(
								"nomeFuncionario", Hibernate.STRING).addScalar(
								"nomeLocalidade", Hibernate.STRING).addScalar(
								"idPocoTipo", Hibernate.INTEGER).addScalar(
								"pavimentoRua", Hibernate.INTEGER).addScalar(
								"pavimentoCalcada", Hibernate.INTEGER)
						.addScalar("idSetorComercial", Hibernate.INTEGER)
						.addScalar("idQuadra", Hibernate.INTEGER).addScalar(
								"loginUsuario", Hibernate.STRING).addScalar(
								"nomeUsuario", Hibernate.STRING).addScalar(
								"idRota", Hibernate.INTEGER).addScalar(
								"idTipoLeitura", Hibernate.INTEGER)
						.setFirstResult(10 * numeroPagina).setMaxResults(10)
						.list();
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0121] - Filtrar Exceções de Leituras e Consumos
	 * 
	 * @author Raphael Rossiter
	 * @date 06/10/2009
	 * 
	 * @param medicaohistoricoParametros
	 * @param sql
	 * @param sqlSegundaParte
	 * @return
	 */
	private String criarCondicionalPesquisaAnaliseExcecoesLeituras(
			Collection medicaohistoricoParametros, String sql,
			String sqlSegundaParte, boolean pesquisarPorRotaAlternativa,
			boolean count, String valorAguaEsgotoInicial, 
			String valorAguaEsgotoFinal) {

		Iterator iteratorImovelSub = medicaohistoricoParametros.iterator();

		while (iteratorImovelSub.hasNext()) {

			FiltroParametro filtroParametro = (FiltroParametro) iteratorImovelSub
					.next();

			if (filtroParametro instanceof Intervalo) {

				Intervalo intervalo = ((Intervalo) filtroParametro);

				sql = sql + " (quadra.qdra_nnquadra between "
						+ intervalo.getIntervaloInicial() + " and "
						+ intervalo.getIntervaloFinal() + ") and ";

				if (sqlSegundaParte != null) {

					sqlSegundaParte = sqlSegundaParte
							+ " (quadra.qdra_nnquadra between "
							+ intervalo.getIntervaloInicial() + " and "
							+ intervalo.getIntervaloFinal() + ") and ";
				}
			}

			if (filtroParametro instanceof ParametroSimples) {
				ParametroSimples parametroSimples = ((ParametroSimples) filtroParametro);

				if (parametroSimples.getNomeAtributo().trim().equalsIgnoreCase(
						FiltroMedicaoHistoricoSql.IMOVEL_CONDOMINIO_ID)) {

					sql = sql + " (" + parametroSimples.getNomeAtributo()
							+ " = " + parametroSimples.getValor() + " and ";

					sql = Util.removerUltimosCaracteres(sql, 4);
					sql = sql + " or imovel.imov_id " + " = "
							+ parametroSimples.getValor() + ") and ";

					if (sqlSegundaParte != null) {

						sqlSegundaParte = sqlSegundaParte + " ("
								+ parametroSimples.getNomeAtributo() + " = "
								+ parametroSimples.getValor() + " and ";

						sqlSegundaParte = Util.removerUltimosCaracteres(
								sqlSegundaParte, 4);
						sqlSegundaParte = sqlSegundaParte
								+ " or imovel.imov_id " + " = "
								+ parametroSimples.getValor() + ") and ";
					}

				} else if (parametroSimples
						.getNomeAtributo()
						.trim()
						.equalsIgnoreCase(
								FiltroMedicaoHistoricoSql.MH_INDICADOR_ANALISADO)) {

					if (parametroSimples.getValor().equals(
							"" + ConstantesSistema.INDICADOR_USO_ATIVO)) {
						sql = sql
								+ " ("
								+ parametroSimples.getNomeAtributo()
								+ " = "
								+ MedicaoHistorico.INDICADOR_ANALISADO_SIM
								+ " or "
								+ parametroSimples.getNomeAtributo()
								+ " = "
								+ MedicaoHistorico.INDICADOR_ANALISADO_ATUALIZADO
								+ ")" + " and ";

						if (sqlSegundaParte != null) {

							sqlSegundaParte = sqlSegundaParte
									+ " ("
									+ parametroSimples.getNomeAtributo()
									+ " = "
									+ MedicaoHistorico.INDICADOR_ANALISADO_SIM
									+ " or "
									+ parametroSimples.getNomeAtributo()
									+ " = "
									+ MedicaoHistorico.INDICADOR_ANALISADO_ATUALIZADO
									+ ") " + " and ";
						}

					} else {
						sql = sql + parametroSimples.getNomeAtributo() + " = "
								+ MedicaoHistorico.INDICADOR_ANALISADO_NAO
								+ " and ";

						if (sqlSegundaParte != null) {

							sqlSegundaParte = sqlSegundaParte
									+ parametroSimples.getNomeAtributo()
									+ " = "
									+ MedicaoHistorico.INDICADOR_ANALISADO_NAO
									+ " and ";
						}

					}

				} else if ((parametroSimples.getNomeAtributo().trim()
						.equalsIgnoreCase(FiltroMedicaoHistoricoSql.PERFIL_IMOVEL))
						|| (parametroSimples.getNomeAtributo().trim()
								.equalsIgnoreCase(FiltroMedicaoHistoricoSql.MH_ANORMALIDADE_FATURADA))
						|| (parametroSimples.getNomeAtributo().trim()
								.equalsIgnoreCase(FiltroMedicaoHistoricoSql.MH_ANORMALIDADE_INFORMADA))
						|| (parametroSimples.getNomeAtributo().trim()
								.equalsIgnoreCase(FiltroMedicaoHistoricoSql.CONSUMO_HISTORICO_ANORMALIDADE_CONSUMO))) {
					sql = sql + parametroSimples.getNomeAtributo() + " in( "
							+ parametroSimples.getValor() + " ) and ";

					if (sqlSegundaParte != null) {

						sqlSegundaParte = sqlSegundaParte
								+ parametroSimples.getNomeAtributo() + " in( "
								+ parametroSimples.getValor() + " ) and ";
					}
				} else {

					sql = sql + parametroSimples.getNomeAtributo() + " = "
							+ parametroSimples.getValor() + " and ";

					if (sqlSegundaParte != null) {

						sqlSegundaParte = sqlSegundaParte
								+ parametroSimples.getNomeAtributo() + " = "
								+ parametroSimples.getValor() + " and ";
					}

				}
			}

			if (filtroParametro instanceof ParametroNaoNulo) {
				ParametroNaoNulo parametroSimples = ((ParametroNaoNulo) filtroParametro);

				sql = sql + " (" + parametroSimples.getNomeAtributo()
						+ " is not null) and ";

				if (sqlSegundaParte != null) {

					sqlSegundaParte = sqlSegundaParte + " ("
							+ parametroSimples.getNomeAtributo()
							+ " is not null) and ";
				}
			}

			if (filtroParametro instanceof MaiorQue) {
				MaiorQue parametroSimples = ((MaiorQue) filtroParametro);

				sql = sql + " (" + parametroSimples.getNomeAtributo() + " >= "
						+ parametroSimples.getNumero() + ") and ";

				if (sqlSegundaParte != null) {

					sqlSegundaParte = sqlSegundaParte + " ("
							+ parametroSimples.getNomeAtributo() + " >= "
							+ parametroSimples.getNumero() + ") and ";
				}

			}

			if (filtroParametro instanceof MenorQue) {
				MenorQue parametroSimples = ((MenorQue) filtroParametro);

				sql = sql + " (" + parametroSimples.getNomeAtributo() + " <= "
						+ parametroSimples.getNumero() + ") and ";

				if (sqlSegundaParte != null) {

					sqlSegundaParte = sqlSegundaParte + " ("
							+ parametroSimples.getNomeAtributo() + " <= "
							+ parametroSimples.getNumero() + ") and ";
				}

			}

			// testa para colocar a anomes no consumo historico
			
			if ((valorAguaEsgotoInicial != null && !valorAguaEsgotoFinal.equals("")) &&(
					valorAguaEsgotoFinal != null && !valorAguaEsgotoFinal.equals(""))) {

				sql = sql + " (((cnta.cnta_vlagua + cnta.cnta_vlesgoto) > "+ valorAguaEsgotoInicial + " and (cnta.cnta_vlagua + cnta.cnta_vlesgoto) < "+ valorAguaEsgotoFinal + ") "
				+ " or ((cnhi.cnhi_vlagua + cnhi.cnhi_vlesgoto) > "+ valorAguaEsgotoInicial + " and (cnhi.cnhi_vlagua + cnhi.cnhi_vlesgoto) < "+ valorAguaEsgotoFinal + ")) and ";

				if (sqlSegundaParte != null) {

				sqlSegundaParte = sqlSegundaParte + " (((cnta.cnta_vlagua + cnta.cnta_vlesgoto) > "+ valorAguaEsgotoInicial + " and (cnta.cnta_vlagua + cnta.cnta_vlesgoto) < "+ valorAguaEsgotoFinal + ") "
				+ " or ((cnhi.cnhi_vlagua + cnhi.cnhi_vlesgoto) > "+ valorAguaEsgotoInicial + " and (cnhi.cnhi_vlagua + cnhi.cnhi_vlesgoto) < "+ valorAguaEsgotoFinal + ")) and ";

				}
				}


		}

		sql = Util.removerUltimosCaracteres(sql, 4);

		if (pesquisarPorRotaAlternativa) {

			sql = sql + " and imovel.rota_idalternativa IS NULL ";
		}

		if (sqlSegundaParte != null) {

			sqlSegundaParte = Util.removerUltimosCaracteres(sqlSegundaParte, 4);

			if (pesquisarPorRotaAlternativa) {

				sqlSegundaParte = sqlSegundaParte
						+ " and imovel.rota_idalternativa IS NULL ";
			}

			if (count) {
				sql = sql + " UNION ALL " + sqlSegundaParte;
			} else {
				sql = sql + " UNION  " + sqlSegundaParte;
			}

		}

		return sql;
	}

	/**
	 * [UC0121] - Filtrar Exceções de Leituras e Consumos
	 * 
	 * @author Flávio Leonardo, Raphael Rossiter
	 * @date 00/00/0000, 06/10/2009
	 * 
	 * @param filtroMedicaoHistoricoSql
	 * @param faturamentoGrupo
	 * @param mesAnoPesquisa
	 * @param pesquisarPorRotaAlternativa
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarImovelExcecoesLeiturasCount(
			FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql,
			FaturamentoGrupo faturamentoGrupo, String anoMes , String valorAguaEsgotoInicial,
			String valorAguaEsgotoFinal, boolean pesquisarPorRotaAlternativa )
			throws ErroRepositorioException {

		Integer retorno = null;

		Collection medicaohistoricoParametros = filtroMedicaoHistoricoSql
				.getParametros();

		Session session = HibernateUtil.getSession();
		Collection resultado = null;

		int valor = 0;

		try {

			if (!medicaohistoricoParametros.isEmpty()
					&& medicaohistoricoParametros.size() >= 1) {

				String sqlRota = "select  count(distinct(imovel.imov_id)) as contador"
						+ " from cadastro.imovel imovel"
						+ " inner join micromedicao.consumo_historico consumoHistorico on imovel.imov_id=consumoHistorico.imov_id and consumoHistorico.cshi_amfaturamento = "
						+ anoMes
						+ " and consumoHistorico.lgti_id = "
						+ LigacaoTipo.LIGACAO_AGUA
						+ " inner join micromedicao.medicao_historico medicaoHistorico on  consumoHistorico.imov_id=medicaoHistorico.lagu_id and medicaoHistorico.mdhi_amleitura = "
						+ anoMes
						+ " and medicaoHistorico.medt_id = "
						+ LigacaoTipo.LIGACAO_AGUA
						+ " inner join cadastro.imovel_subcategoria imovelSubcategoria on imovel.imov_id=imovelSubcategoria.imov_id  "
						+ " inner join cadastro.setor_comercial setorComercial on imovel.stcm_id=setorComercial.stcm_id  "
						+ " inner join cadastro.quadra quadra on imovel.qdra_id=quadra.qdra_id"
						+ " inner join micromedicao.rota rota on quadra.rota_id=rota.rota_id"
						+ " left outer join faturamento.faturamento_grupo faturamentoGrupo on rota.ftgr_id=faturamentoGrupo.ftgr_id  left outer join cadastro.empresa empresa on rota.empr_id=empresa.empr_id"
						+ " left outer join cadastro.imovel_perfil imovelPerfil on imovel.iper_id=imovelPerfil.iper_id"
						+ " left outer join cadastro.subcategoria subcategoria on imovelSubcategoria.scat_id=subcategoria.scat_id"
						+ " left outer join micromedicao.consumo_anormalidade consumoAnormalidade on consumoHistorico.csan_id=consumoAnormalidade.csan_id"
						+ " left outer join cadastro.categoria categoria on subcategoria.catg_id=categoria.catg_id"
						+ " left outer join micromedicao.medicao_tipo medicaoTipo on medicaoHistorico.medt_id=medicaoTipo.medt_id"
						+ " left outer join micromedicao.leitura_anormalidade leituraAnormalidadeFaturamento on medicaohistorico.ltan_idleitanormfatmt=leituraAnormalidadeFaturamento.ltan_id"
						
/////////////////////////////////////////////////////
						+ " left outer join faturamento.conta cnta on cnta.imov_id = imovel.imov_id and cnta.cnta_amreferenciaconta = " + anoMes
						+ " and cnta.dcst_idatual in ( " + DebitoCreditoSituacao.NORMAL +"," + DebitoCreditoSituacao.PRE_FATURADA + ")"
						+ " left outer join faturamento.conta_historico cnhi on cnhi.imov_id = imovel.imov_id and cnhi.cnhi_amreferenciaconta = " + anoMes
						+ " and cnhi.dcst_idatual in ( " + DebitoCreditoSituacao.NORMAL +"," + DebitoCreditoSituacao.PRE_FATURADA + ")"
////////////////////////////////////////////////////						
						
						+ " where ";

				String sqlRotaSegundaParte = "select  count(distinct(imovel.imov_id)) as contador "
						+ " from cadastro.imovel imovel"
						+ " inner join micromedicao.consumo_historico consumoHistorico on imovel.imov_id=consumoHistorico.imov_id and consumoHistorico.cshi_amfaturamento = "
						+ anoMes
						+ " and consumoHistorico.lgti_id = "
						+ LigacaoTipo.LIGACAO_ESGOTO
						+ " inner join micromedicao.medicao_historico medicaoHistorico on  consumoHistorico.imov_id=medicaoHistorico.imov_id and medicaoHistorico.mdhi_amleitura = "
						+ anoMes
						+ " and medicaoHistorico.medt_id = "
						+ LigacaoTipo.LIGACAO_ESGOTO
						+ " inner join cadastro.imovel_subcategoria imovelSubcategoria on imovel.imov_id=imovelSubcategoria.imov_id  "
						+ " inner join cadastro.setor_comercial setorComercial on imovel.stcm_id=setorComercial.stcm_id  "
						+ " inner join cadastro.quadra quadra on imovel.qdra_id=quadra.qdra_id"
						+ " inner join micromedicao.rota rota on quadra.rota_id=rota.rota_id"
						+ " left outer join faturamento.faturamento_grupo faturamentoGrupo on rota.ftgr_id=faturamentoGrupo.ftgr_id  left outer join cadastro.empresa empresa on rota.empr_id=empresa.empr_id"
						+ " left outer join cadastro.imovel_perfil imovelPerfil on imovel.iper_id=imovelPerfil.iper_id"
						+ " left outer join cadastro.subcategoria subcategoria on imovelSubcategoria.scat_id=subcategoria.scat_id"
						+ " left outer join micromedicao.consumo_anormalidade consumoAnormalidade on consumoHistorico.csan_id=consumoAnormalidade.csan_id"
						+ " left outer join cadastro.categoria categoria on subcategoria.catg_id=categoria.catg_id"
						+ " left outer join micromedicao.medicao_tipo medicaoTipo on medicaoHistorico.medt_id=medicaoTipo.medt_id"
						+ " left outer join micromedicao.leitura_anormalidade leituraAnormalidadeFaturamento on medicaohistorico.ltan_idleitanormfatmt=leituraAnormalidadeFaturamento.ltan_id"
						
/////////////////////////////////////////////////////
						+ " left outer join faturamento.conta cnta on cnta.imov_id = imovel.imov_id and cnta.cnta_amreferenciaconta = " + anoMes
						+ " and cnta.dcst_idatual in ( " + DebitoCreditoSituacao.NORMAL +"," + DebitoCreditoSituacao.PRE_FATURADA + ")"
						+ " left outer join faturamento.conta_historico cnhi on cnhi.imov_id = imovel.imov_id and cnhi.cnhi_amreferenciaconta = " + anoMes
						+ " and cnhi.dcst_idatual in ( " + DebitoCreditoSituacao.NORMAL +"," + DebitoCreditoSituacao.PRE_FATURADA + ")"
//////////////////////////////////////////////////	//
						
						+ " where ";

				sqlRota = criarCondicionalPesquisaAnaliseExcecoesLeituras(
						medicaohistoricoParametros, sqlRota,
						sqlRotaSegundaParte, pesquisarPorRotaAlternativa, true, valorAguaEsgotoInicial, valorAguaEsgotoFinal);

				resultado = session.createSQLQuery(sqlRota).addScalar("contador",
						Hibernate.INTEGER).list();

				if (!resultado.isEmpty()) {

					Iterator iterator = resultado.iterator();

					while (iterator.hasNext()) {
						Integer valorUm = (Integer) iterator.next();
						valor = valor + valorUm.intValue();
					}
					retorno = new Integer(valor);

				} else {
					retorno = new Integer(0);
				}

				if (pesquisarPorRotaAlternativa) {

					String sqlRotaAlternativa = "select  count(distinct(imovel.imov_id)) as contador "
							+ " from cadastro.imovel imovel"
							+ " inner join micromedicao.consumo_historico consumoHistorico on imovel.imov_id=consumoHistorico.imov_id and consumoHistorico.cshi_amfaturamento = "
							+ anoMes
							+ " and consumoHistorico.lgti_id = "
							+ LigacaoTipo.LIGACAO_AGUA
							+ " inner join micromedicao.medicao_historico medicaoHistorico on  consumoHistorico.imov_id=medicaoHistorico.lagu_id and medicaoHistorico.mdhi_amleitura = "
							+ anoMes
							+ " and medicaoHistorico.medt_id = "
							+ LigacaoTipo.LIGACAO_AGUA
							+ " inner join cadastro.imovel_subcategoria imovelSubcategoria on imovel.imov_id=imovelSubcategoria.imov_id  "
							+ " inner join cadastro.setor_comercial setorComercial on imovel.stcm_id=setorComercial.stcm_id  "
							+ " inner join cadastro.quadra quadra on imovel.qdra_id=quadra.qdra_id"
							+ " inner join micromedicao.rota rota on imovel.rota_idalternativa=rota.rota_id"
							+ " left outer join faturamento.faturamento_grupo faturamentoGrupo on rota.ftgr_id=faturamentoGrupo.ftgr_id  left outer join cadastro.empresa empresa on rota.empr_id=empresa.empr_id"
							+ " left outer join cadastro.imovel_perfil imovelPerfil on imovel.iper_id=imovelPerfil.iper_id"
							+ " left outer join cadastro.subcategoria subcategoria on imovelSubcategoria.scat_id=subcategoria.scat_id"
							+ " left outer join micromedicao.consumo_anormalidade consumoAnormalidade on consumoHistorico.csan_id=consumoAnormalidade.csan_id"
							+ " left outer join cadastro.categoria categoria on subcategoria.catg_id=categoria.catg_id"
							+ " left outer join micromedicao.medicao_tipo medicaoTipo on medicaoHistorico.medt_id=medicaoTipo.medt_id"
							+ " left outer join micromedicao.leitura_anormalidade leituraAnormalidadeFaturamento on medicaohistorico.ltan_idleitanormfatmt=leituraAnormalidadeFaturamento.ltan_id"
							
/////////////////////////////////////////////////////
							+ " left outer join faturamento.conta cnta on cnta.imov_id = imovel.imov_id and cnta.cnta_amreferenciaconta = " + anoMes
							+ " and cnta.dcst_idatual in ( " + DebitoCreditoSituacao.NORMAL +"," + DebitoCreditoSituacao.PRE_FATURADA + ")"
							+ " left outer join faturamento.conta_historico cnhi on cnhi.imov_id = imovel.imov_id and cnhi.cnhi_amreferenciaconta = " + anoMes
							+ " and cnhi.dcst_idatual in ( " + DebitoCreditoSituacao.NORMAL +"," + DebitoCreditoSituacao.PRE_FATURADA + ")"
//////////////////////////////////////////////////		//
							
							+ " where ";

					String sqlRotaAlternativaSegundaParte = "select  count(distinct(imovel.imov_id)) as contador "
							+ " from cadastro.imovel imovel"
							+ " inner join micromedicao.consumo_historico consumoHistorico on imovel.imov_id=consumoHistorico.imov_id and consumoHistorico.cshi_amfaturamento = "
							+ anoMes
							+ " and consumoHistorico.lgti_id = "
							+ LigacaoTipo.LIGACAO_ESGOTO
							+ " inner join micromedicao.medicao_historico medicaoHistorico on  consumoHistorico.imov_id=medicaoHistorico.imov_id and medicaoHistorico.mdhi_amleitura = "
							+ anoMes
							+ " and medicaoHistorico.medt_id = "
							+ LigacaoTipo.LIGACAO_ESGOTO
							+ " inner join cadastro.imovel_subcategoria imovelSubcategoria on imovel.imov_id=imovelSubcategoria.imov_id  "
							+ " inner join cadastro.setor_comercial setorComercial on imovel.stcm_id=setorComercial.stcm_id  "
							+ " inner join cadastro.quadra quadra on imovel.qdra_id=quadra.qdra_id"
							+ " inner join micromedicao.rota rota on imovel.rota_idalternativa=rota.rota_id"
							+ " left outer join faturamento.faturamento_grupo faturamentoGrupo on rota.ftgr_id=faturamentoGrupo.ftgr_id  left outer join cadastro.empresa empresa on rota.empr_id=empresa.empr_id"
							+ " left outer join cadastro.imovel_perfil imovelPerfil on imovel.iper_id=imovelPerfil.iper_id"
							+ " left outer join cadastro.subcategoria subcategoria on imovelSubcategoria.scat_id=subcategoria.scat_id"
							+ " left outer join micromedicao.consumo_anormalidade consumoAnormalidade on consumoHistorico.csan_id=consumoAnormalidade.csan_id"
							+ " left outer join cadastro.categoria categoria on subcategoria.catg_id=categoria.catg_id"
							+ " left outer join micromedicao.medicao_tipo medicaoTipo on medicaoHistorico.medt_id=medicaoTipo.medt_id"
							+ " left outer join micromedicao.leitura_anormalidade leituraAnormalidadeFaturamento on medicaohistorico.ltan_idleitanormfatmt=leituraAnormalidadeFaturamento.ltan_id"
							
/////////////////////////////////////////////////////
							+ " left outer join faturamento.conta cnta on cnta.imov_id = imovel.imov_id and cnta.cnta_amreferenciaconta = " + anoMes
							+ " and cnta.dcst_idatual in ( " + DebitoCreditoSituacao.NORMAL +"," + DebitoCreditoSituacao.PRE_FATURADA + ")"
							+ " left outer join faturamento.conta_historico cnhi on cnhi.imov_id = imovel.imov_id and cnhi.cnhi_amreferenciaconta = " + anoMes
							+ " and cnhi.dcst_idatual in ( " + DebitoCreditoSituacao.NORMAL +"," + DebitoCreditoSituacao.PRE_FATURADA + ")"
//////////////////////////////////////////////////		//
							
							+ " where ";

					sqlRotaAlternativa = criarCondicionalPesquisaAnaliseExcecoesLeituras(
							medicaohistoricoParametros, sqlRotaAlternativa,
							sqlRotaAlternativaSegundaParte, false, true, valorAguaEsgotoInicial, valorAguaEsgotoFinal);

					resultado = session.createSQLQuery(sqlRotaAlternativa)
							.addScalar("contador", Hibernate.INTEGER).list();

					valor = 0;

					if (!resultado.isEmpty()) {

						Iterator iterator = resultado.iterator();

						while (iterator.hasNext()) {
							Integer valorUm = (Integer) iterator.next();
							valor = valor + valorUm.intValue();
						}

						retorno = retorno + valor;
					}
				}
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * Método que apresenta os dados do imovel
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 * 
	 * @author Sávio Luiz
	 * @date 04/08/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 */
	public Collection pesquiarImovelExcecoesApresentaDados(Integer idImovel,
			boolean ligacaoAgua) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno = null;

		String ligacaoHidrometro = "";
		if (ligacaoAgua) {
			ligacaoHidrometro = " ligacaoAgua.hidi_id = hidrometroIstalacaoHistorico.hidi_id";
		} else {
			ligacaoHidrometro = " imovel.hidi_id = hidrometroIstalacaoHistorico.hidi_id";
		}

		String sql = "select faturamentoGrupo.ftgr_id as idFaturamentoGrupo,"// 0
				+ " faturamentoGrupo.ftgr_amreferencia as amFaturamentoGrupo,"// 1
				+ " empresa.empr_nmempresa as nomeEmpresa,"// 2
				+ " imovel.imov_icimovelcondominio as icCondominio,"// 3
				+ " ligacaoAguaSituacao.last_dsligacaoaguasituacao as descricaoLigAguaSit,"// 4
				+ " ligacaoEsgotoSituacao.lest_dsligacaoesgotosituacao as descricaoLigEsgSit,"// 5
				+ " cliente.clie_nmcliente as nomeCliente,"// 6
				+ " cliente.clie_nncpf as cpf,"// 7
				+ " cliente.clie_nncnpj as cnpj,"// 8
				+ " hidrometro.hidr_nnhidrometro as numerohidrometro,"// 9
				+ " hidrometroIstalacaoHistorico.hidi_dtinstalacaohidrometro as dtInstHidrometro,"// 10
				+ " hidrometroCapacidade.hicp_dshidrometrocapacidade as descricaoHidCapacidade,"// 11
				+ " hidrometroTipo.hitp_dshidrometrotipo as descricaoHidTipo,"// 12
				+ " hidrometroMarca.himc_dshidrometromarca as descricaoHidMarca,"// 13
				+ " hidrometroLocalInstalacao.hili_dshidmtlocalinstalacao as descricaoHidLocInst,"// 14
				+ " hidrometroDiametro.hidm_dshidrometrodiametro as descricaoHidDiamentro,"// 15
				+ " hidrometroProtecao.hipr_dshidrometroprotecao as descricaoHidProtecao,"// 16
				+ " hidrometroIstalacaoHistorico.hidi_iccavalete as descricaoIcCavalete,"// 17
				+ " hidrometro.hidr_nnanofabricacao as anoFabricacao,"// 18
				+ " imovelPerfil.iper_dsimovelperfil as descricaoImovelPerfil,"// 19
				+ " ligacaoAgua.lagu_dtligacaoagua as dataLigacaoAgua,"// 20
				+ " ligacaoAgua.lagu_dtcorte as dataCorte,"// 21
				+ " ligacaoAgua.lagu_dtreligacaoagua as dataReligAgua,"// 22
				+ " ligacaoAgua.lagu_dtsupressaoagua as dataSupAgua,"// 23
				+ " ligacaoAguaDiametro.lagd_dsligacaoaguadiametro as descricaoAguaDia,"// 24
				+ " ligacaoAguaMaterial.lagm_dsligacaoaguamaterial as descricaoLigAguaMat,"// 25
				+ " ligacaoAgua.lagu_nnconsumominimoagua as numeroConsumoMinAgua,"// 26
				+ " ligacaoEsgoto.lesg_dtligacao as dataLigacao,"// 27
				+ " ligacaoEsgotoDiametro.legd_dsligacaoesgotodiametro as descricaoLigEsgDia,"// 28
				+ " ligacaoEsgotoMaterial.legm_dsligacaoesgotomaterial as descricaoLigEsgMat,"// 29
				+ " ligacaoEsgotoPerfil.lepf_dsligacaoesgotoperfil as descricaoLigEsgPerfil,"// 30
				+ " ligacaoEsgoto.lesg_nnconsumominimoesgoto as numeroConsumoMinEsgo,"// 31
				+ " ligacaoEsgoto.lesg_pcesgoto as percentualEsgoto,"// 32
				+ " ligacaoEsgoto.lesg_pccoleta as percentualColeta,"// 33
				+ " pocoTipo.poco_dspocotipo as descricaoPocoTipo, "// 34
				+ " ligacaoAgua.lagu_dtrestabelecimentoagua as dataRestabelecimentoAgua,"// 35
				+ " ligacaoAguaPerfil.lapf_dsligacaoaguaperfil as descricaoLigacaoAguaPerfil,"// 36
				+ " ligacaoEsgoto.lesg_id as idLigacaoEsgoto, "// 37
				+ " ligacaoAgua.lagu_id as idLigacaoAgua,"// 38
				+ " imovel.last_id as idLigacaoAguaSituacao,"// 39
				+ " imovel.imov_idimovelcondominio as idImovelCondominio, "// 40
				+ " faturamentoGrupo.ftgr_nndiavencimento as diaVencimento, " // 41
				+ " rota.rota_cdrota as codigoRota, " // 42
				+ " imovel.imov_nnsequencialrota as sequencialRota, "// 43

				/*
				 * Início Campos Adicionados por Rafael Corrêa 22/07/2008
				 */
				+ " hidrometroRelojoaria.hire_dsrelojoaria as tipoRelojoaria, " // 44
				+ " usuarioResponsavelInstalacao.usur_nmusuario as nomeusuarioresponsavelinstalac, " // 45
				+ " hidrometroIstalacaoHistorico.hidi_nnlacre as numeroLacre, " // 46
				+ " ligacaoEsgotoEsgotamento.lees_dsesgotamento as descricaoligacaoesgotoesgotame, " // 47
				+ " ligacaoEsgotoCaixaInspecao.leci_dscaixainspecao as descricaoligacaoesgotocaixains, " // 48
				+ " ligacaoEsgotoDestinoDejetos.ledd_dsdestinodejetos as descricaoligacaoesgotodestinod, " // 49
				+ " ligacaoesgotodestinoaguaspluv1.leda_dsaguaspluviais as descricaoligacaoesgotodestinoa, " // 50
				/*
				 * Fim Campos Adicionados por Rafael Corrêa
				 */

				/*
				 * Início Campos Adicionados por Raphael Rossiter 21/10/2009
				 */
				+ " rotaAlternativa.rota_cdrota as codigoRotaAlternativa, " // 51
				+ " rotaAlternativa.ftgr_id as faturamentoGrupoAlternativa, " // 52
				+ " faturamentoGrupoAlternativa.ftgr_amreferencia as amFaturamentoGrupoAlternativo, "// 53
				+ " empresaAlternativa.empr_nmempresa as nomeEmpresaAlternativa, "// 54
				+ " faturamentoGrupoAlternativa.ftgr_nndiavencimento as vencimentofaturamentogrupoalte " // 55
				
				/*
				 * Fim Campos Adicionados por Raphael Rossiter
				 */

				+ " from cadastro.cliente_imovel clienteImovel "
				+ " inner join cadastro.imovel imovel on clienteImovel.imov_id=imovel.imov_id and imovel.imov_id = "
				+ idImovel
				+ "	left outer join micromedicao.rota rotaAlternativa on imovel.rota_idalternativa=rotaAlternativa.rota_id "
				+ "	left outer join faturamento.faturamento_grupo faturamentoGrupoAlternativa on rotaAlternativa.ftgr_id=faturamentoGrupoAlternativa.ftgr_id "
				+ "	left outer join cadastro.empresa empresaAlternativa on rotaAlternativa.empr_id=empresaAlternativa.empr_id  "
				+ "	left outer join atendimentopublico.ligacao_agua ligacaoAgua on imovel.imov_id=ligacaoAgua.lagu_id "
				+ " left outer join micromedicao.hidrometro_inst_hist hidrometroIstalacaoHistorico on "
				+ ligacaoHidrometro
				+ "	left outer join micromedicao.hidrometro_local_inst hidrometroLocalInstalacao on hidrometroIstalacaoHistorico.hili_id=hidrometroLocalInstalacao.hili_id "
				+ "	left outer join micromedicao.hidrometro hidrometro on hidrometroIstalacaoHistorico.hidr_id=hidrometro.hidr_id "
				+ "	left outer join micromedicao.hidrometro_capacidade hidrometroCapacidade on hidrometro.hicp_id=hidrometroCapacidade.hicp_id "
				+ "	left outer join micromedicao.hidrometro_tipo hidrometroTipo on hidrometro.hitp_id=hidrometroTipo.hitp_id "
				+ "	left outer join micromedicao.hidrometro_marca hidrometroMarca on hidrometro.himc_id=hidrometroMarca.himc_id "
				+ "	left outer join micromedicao.hidrometro_diametro hidrometroDiametro on hidrometro.hidm_id=hidrometroDiametro.hidm_id "
				+ "	left outer join micromedicao.hidrometro_protecao hidrometroProtecao on  hidrometroIstalacaoHistorico.hipr_id=hidrometroProtecao.hipr_id "
				+ "	inner join cadastro.cliente cliente on clienteImovel.clie_id=cliente.clie_id "
				+ "	left outer join cadastro.quadra quadra on imovel.qdra_id=quadra.qdra_id "
				+ "	left outer join micromedicao.rota rota on quadra.rota_id=rota.rota_id  "
				+ "	left outer join faturamento.faturamento_grupo faturamentoGrupo on rota.ftgr_id=faturamentoGrupo.ftgr_id "
				+ "	left outer join cadastro.empresa empresa on rota.empr_id=empresa.empr_id  "
				+ "	left outer join atendimentopublico.ligacao_agua_situacao ligacaoAguaSituacao on imovel.last_id=ligacaoAguaSituacao.last_id "
				+ "	left outer join atendimentopublico.ligacao_esgoto_situacao ligacaoEsgotoSituacao on  imovel.lest_id=ligacaoEsgotoSituacao.lest_id "
				+ "	left outer join cadastro.imovel_perfil imovelPerfil on imovel.iper_id=imovelPerfil.iper_id "
				+ "	left outer join atendimentopublico.ligacao_agua_diametro ligacaoAguaDiametro on  ligacaoAgua.lagd_id=ligacaoAguaDiametro.lagd_id "
				+ "	left outer join atendimentopublico.ligacao_agua_material ligacaoAguaMaterial on ligacaoAgua.lagm_id=ligacaoAguaMaterial.lagm_id "
				+ "	left outer join atendimentopublico.ligacao_agua_perfil ligacaoAguaPerfil on ligacaoAgua.lapf_id=ligacaoAguaPerfil.lapf_id "
				+ "	left outer join atendimentopublico.ligacao_esgoto ligacaoEsgoto on imovel.imov_id=ligacaoEsgoto.lesg_id "
				+ "	left outer join atendimentopublico.ligacao_esgoto_diametro ligacaoEsgotoDiametro on  ligacaoEsgoto.legd_id=ligacaoEsgotoDiametro.legd_id "
				+ "	left outer join atendimentopublico.ligacao_esgoto_material ligacaoEsgotoMaterial on ligacaoEsgoto.legm_id=ligacaoEsgotoMaterial.legm_id "
				+ "	left outer join atendimentopublico.ligacao_esgoto_perfil ligacaoEsgotoPerfil on ligacaoEsgoto.lepf_id=ligacaoEsgotoPerfil.lepf_id "
				+ "	left outer join cadastro.poco_tipo pocoTipo on imovel.poco_id=pocoTipo.poco_id  "
				+ "	left outer join atendimentopublico.lig_esgoto_esgotamento ligacaoEsgotoEsgotamento on ligacaoEsgoto.lees_id = ligacaoEsgotoEsgotamento.lees_id "
				+ "	left outer join atendimentopublico.lig_esgoto_caixa_inspec ligacaoEsgotoCaixaInspecao on ligacaoEsgoto.leci_id = ligacaoEsgotoCaixaInspecao.leci_id "
				+ "	left outer join atendimentopublico.lig_esgoto_dest_dejetos ligacaoEsgotoDestinoDejetos on ligacaoEsgoto.ledd_id = ligacaoEsgotoDestinoDejetos.ledd_id "
				+ "	left outer join atendimentopublico.lig_esgoto_dest_agplv ligacaoesgotodestinoaguaspluv1 on ligacaoEsgoto.leda_id = ligacaoesgotodestinoaguaspluv1.leda_id "

				+ "	left outer join micromedicao.hidrometro_relojoaria hidrometroRelojoaria on hidrometro.hire_id = hidrometroRelojoaria.hire_id "
				+ "	left outer join seguranca.usuario usuarioResponsavelInstalacao on hidrometroIstalacaoHistorico.usur_idinstalacao = usuarioResponsavelInstalacao.usur_id "
				+ "	where clienteImovel.cifr_id is null AND clienteImovel.crtp_id = "
				+ ConstantesSistema.CLIENTE_IMOVEL_TIPO_USUARIO;

		try {
			retorno = session
					.createSQLQuery(sql)
					.addScalar("idFaturamentoGrupo", Hibernate.INTEGER)
					.addScalar("amFaturamentoGrupo", Hibernate.INTEGER)
					.addScalar("nomeEmpresa", Hibernate.STRING)
					.addScalar("icCondominio", Hibernate.SHORT)
					.addScalar("descricaoLigAguaSit", Hibernate.STRING)
					.addScalar("descricaoLigEsgSit", Hibernate.STRING)
					.addScalar("nomeCliente", Hibernate.STRING)
					.addScalar("cpf", Hibernate.STRING)
					.addScalar("cnpj", Hibernate.STRING)
					.addScalar("numerohidrometro", Hibernate.STRING)
					.addScalar("dtInstHidrometro", Hibernate.DATE)
					.addScalar("descricaoHidCapacidade", Hibernate.STRING)
					.addScalar("descricaoHidTipo", Hibernate.STRING)
					.addScalar("descricaoHidMarca", Hibernate.STRING)
					.addScalar("descricaoHidLocInst", Hibernate.STRING)
					.addScalar("descricaoHidDiamentro", Hibernate.STRING)
					.addScalar("descricaoHidProtecao", Hibernate.STRING)
					.addScalar("descricaoIcCavalete", Hibernate.SHORT)
					.addScalar("anoFabricacao", Hibernate.SHORT)
					.addScalar("descricaoImovelPerfil", Hibernate.STRING)
					.addScalar("dataLigacaoAgua", Hibernate.DATE)
					.addScalar("dataCorte", Hibernate.DATE)
					.addScalar("dataReligAgua", Hibernate.DATE)
					.addScalar("dataSupAgua", Hibernate.DATE)
					.addScalar("descricaoAguaDia", Hibernate.STRING)
					.addScalar("descricaoLigAguaMat", Hibernate.STRING)
					.addScalar("numeroConsumoMinAgua", Hibernate.INTEGER)
					.addScalar("dataLigacao", Hibernate.DATE)
					.addScalar("descricaoLigEsgDia", Hibernate.STRING)
					.addScalar("descricaoLigEsgMat", Hibernate.STRING)
					.addScalar("descricaoLigEsgPerfil", Hibernate.STRING)
					.addScalar("numeroConsumoMinEsgo", Hibernate.INTEGER)
					.addScalar("percentualEsgoto", Hibernate.BIG_DECIMAL)
					.addScalar("percentualColeta", Hibernate.BIG_DECIMAL)
					.addScalar("descricaoPocoTipo", Hibernate.STRING)
					.addScalar("dataRestabelecimentoAgua", Hibernate.DATE)
					.addScalar("descricaoLigacaoAguaPerfil", Hibernate.STRING)
					.addScalar("idLigacaoEsgoto", Hibernate.INTEGER)
					.addScalar("idLigacaoAgua", Hibernate.INTEGER)
					.addScalar("idLigacaoAguaSituacao", Hibernate.INTEGER)
					.addScalar("idImovelCondominio", Hibernate.INTEGER)
					.addScalar("diaVencimento", Hibernate.SHORT)
					.addScalar("codigoRota", Hibernate.SHORT)
					.addScalar("sequencialRota", Hibernate.INTEGER)
					.addScalar("tipoRelojoaria", Hibernate.STRING)
					.addScalar("nomeusuarioresponsavelinstalac",
							Hibernate.STRING)
					.addScalar("numeroLacre", Hibernate.STRING)
					.addScalar("descricaoligacaoesgotoesgotame",
							Hibernate.STRING)
					.addScalar("descricaoligacaoesgotocaixains",
							Hibernate.STRING)
					.addScalar("descricaoligacaoesgotodestinod",
							Hibernate.STRING)
					.addScalar("descricaoligacaoesgotodestinoa",
							Hibernate.STRING)

					.addScalar("codigoRotaAlternativa", Hibernate.SHORT)
					.addScalar("faturamentoGrupoAlternativa", Hibernate.INTEGER)
					.addScalar("amFaturamentoGrupoAlternativo",
							Hibernate.INTEGER).addScalar(
							"nomeEmpresaAlternativa", Hibernate.STRING)
							.addScalar("vencimentofaturamentogrupoalte",
									Hibernate.SHORT).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * Método que apresenta os dados do imovel
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 * 
	 * @author Flávio Cordeiro, Ivan Sérgio
	 * @date 04/08/2006, 06/03/2008
	 * @alteracao: Trocar imovel.hidi_id por imovel.poco_id, pois alguns imoveis
	 *             nao possuem registro na tabela de
	 *             HidrometroInstalacaoHistorico mas possuem poco.
	 * 
	 * @param idImovel
	 * @return Collection
	 */
	public Collection pesquiarImovelExcecoesApresentaDadosResumido(
			Integer idImovel, boolean ligacaoAgua)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno = null;

		String ligacaoHidrometro = "";
		if (ligacaoAgua) {
			ligacaoHidrometro = " ligacaoAgua.hidi_id = hidrometroIstalacaoHistorico.hidi_id";
		} else {
			ligacaoHidrometro = " imovel.hidi_id = hidrometroIstalacaoHistorico.hidi_id";
		}

		String sql = "select  faturamentoGrupo.ftgr_id as idFaturamentoGrupo,"// 0
				+ " faturamentoGrupo.ftgr_amreferencia as amFaturamentoGrupo,"// 1
				+ " imovel.imov_icimovelcondominio as icCondominio,"// 2
				+ " ligacaoAguaSituacao.last_dsligacaoaguasituacao as descricaoLigAguaSit,"// 3
				+ " ligacaoEsgotoSituacao.lest_dsligacaoesgotosituacao as descricaoLigEsgSit,"// 4
				+ " hidrometro.hidr_nnhidrometro as numerohidrometro,"// 5
				+ " hidrometroIstalacaoHistorico.hidi_dtinstalacaohidrometro as dtInstHidrometro,"// 6
				+ " hidrometroCapacidade.hicp_dshidrometrocapacidade as descricaoHidCapacidade,"// 7
				+ " imovelPerfil.iper_dsimovelperfil as descricaoImovelPerfil,"// 8
				+ " imovel.imov_idimovelcondominio as idImovelCondominio,"// 9
				+ " imovel.imov_qteconomia as qtdEconomias,"// 10
				// + " imovel.hidi_id as idHidrometroHistoricoImovel,"// 11
				+ " imovel.poco_id as idPoco,"// 11
				+ " hidrometro.hidr_nndigitosleitura as numeroDigitosLeitura, "// 12
				+ " imovel.imov_nnareaconstruida as areaConstruida, " // 13
				+ " ligacaoAguaSituacao.last_id as idLigAguaSit, " // 14
				+ " ligacaoEsgotoSituacao.lest_id as idLigEsgotoSit, " // 15
				+ " imovelPerfil.iper_id as idImovelPerfil, " // 16
				+ " ligacaoAguaSituacao.last_icfaturamento as indFatLigAguaSit,"// 17
				+ " ligacaoEsgotoSituacao.lest_icfaturamento as indFatLigEsgSit "// 18
				+ " from cadastro.cliente_imovel clienteImovel "
				+ " inner join cadastro.imovel imovel on clienteImovel.imov_id=imovel.imov_id and imovel.imov_id = "
				+ idImovel
				+ "	left outer join atendimentopublico.ligacao_agua ligacaoAgua on imovel.imov_id=ligacaoAgua.lagu_id "
				+ " left outer join micromedicao.hidrometro_inst_hist hidrometroIstalacaoHistorico on "
				+ ligacaoHidrometro
				+ "	left outer join micromedicao.hidrometro hidrometro on hidrometroIstalacaoHistorico.hidr_id=hidrometro.hidr_id "
				+ "	left outer join micromedicao.hidrometro_capacidade hidrometroCapacidade on hidrometro.hicp_id=hidrometroCapacidade.hicp_id "
				+ "	left outer join cadastro.quadra quadra on imovel.qdra_id=quadra.qdra_id "
				+ "	left outer join micromedicao.rota rota on quadra.rota_id=rota.rota_id  "
				+ "	left outer join faturamento.faturamento_grupo faturamentoGrupo on rota.ftgr_id=faturamentoGrupo.ftgr_id "
				+ "	left outer join atendimentopublico.ligacao_agua_situacao ligacaoAguaSituacao on imovel.last_id=ligacaoAguaSituacao.last_id "
				+ "	left outer join atendimentopublico.ligacao_esgoto_situacao ligacaoEsgotoSituacao on  imovel.lest_id=ligacaoEsgotoSituacao.lest_id "
				+ "	left outer join cadastro.imovel_perfil imovelPerfil on imovel.iper_id=imovelPerfil.iper_id "
				+ "	left outer join atendimentopublico.ligacao_esgoto ligacaoEsgoto on imovel.imov_id=ligacaoEsgoto.lesg_id "
				+ "	left outer join cadastro.poco_tipo pocoTipo on imovel.poco_id=pocoTipo.poco_id  "
				+ "	where clienteImovel.cifr_id is null AND clienteImovel.crtp_id = "
				+ ConstantesSistema.CLIENTE_IMOVEL_TIPO_USUARIO;

		try {
			retorno = session.createSQLQuery(sql).addScalar(
					"idFaturamentoGrupo", Hibernate.INTEGER).addScalar(
					"amFaturamentoGrupo", Hibernate.INTEGER).addScalar(
					"icCondominio", Hibernate.SHORT).addScalar(
					"descricaoLigAguaSit", Hibernate.STRING).addScalar(
					"descricaoLigEsgSit", Hibernate.STRING).addScalar(
					"numerohidrometro", Hibernate.STRING).addScalar(
					"dtInstHidrometro", Hibernate.DATE).addScalar(
					"descricaoHidCapacidade", Hibernate.STRING).addScalar(
					"descricaoImovelPerfil", Hibernate.STRING).addScalar(
					"idImovelCondominio", Hibernate.INTEGER).addScalar(
					"qtdEconomias", Hibernate.SHORT).addScalar("idPoco",
					Hibernate.INTEGER).addScalar("numeroDigitosLeitura",
					Hibernate.SHORT).addScalar("areaConstruida",
					Hibernate.BIG_DECIMAL).addScalar("idLigAguaSit",
					Hibernate.INTEGER).addScalar("idLigEsgotoSit",
					Hibernate.INTEGER).addScalar("idImovelPerfil",
					Hibernate.INTEGER).addScalar("indFatLigAguaSit",
							Hibernate.SHORT).addScalar("indFatLigEsgSit",
							Hibernate.SHORT).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * Método que apresenta os dados do imovel
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 * 
	 * @author Sávio Luiz
	 * @date 04/08/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 */
	public Collection pesquiarMedicaoConsumoHistoricoExcecoesApresentaDados(
			FaturamentoGrupo faturamentoGrupo, Integer idImovel,
			boolean ligacaoAgua) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno = null;

		String ligadoPorAguaOuHidrometro = "";

		if (ligacaoAgua) {
			ligadoPorAguaOuHidrometro = " medicaoHistorico.lagu_id = imovel.imov_id ";
		} else {
			ligadoPorAguaOuHidrometro = " medicaoHistorico.imov_id = imovel.imov_id ";
		}

		String sql = "select   distinct medicaoTipo.medt_dsmedicaotipo as descricaoMedTipo,"// 0
				+ "medicaoTipo.medt_id as idMedTipo,"// 1
				+ "mdhi_dtleitantfatmt as dataLeituraAnteriorFaturamento,"// 2
				+ "mdhi_dtleituraatualfaturamento as dataLeituraAtualFaturamento,"// 3
				+ "mdhi_nnleituraatualfaturamento as numeroLeituraAtualFaturamento,"// 4
				+ "mdhi_nnleitantfatmt as numeroleituraanteriorfaturamen,"// 5
				+ "mdhi_dtleituraatualinformada as dataLeituraAtualInformada,"// 6
				+ "mdhi_nnleituraatualinformada as numeroLeituraInformada,"// 7
				+ "leituraSituacaoAtual.ltst_dsleiturasituacao as descricaoLeituraSituacao,"// 8
				+ "mdhi_dtleituraatualfaturamento as dataLeituraFaturamento,"// 9
				+ "mdhi_nnleituraatualfaturamento as numeroLeituraFaturamento,"// 10
				+ "medicaoHistorico.func_id as idFuncionario,"// 11
				+ "leituraAnormalidadeInformada.ltan_dsleituraanormalidade as descricaoleituraanormalidadein,"// 12
				+ "leituraAnormalidadeFaturamento.ltan_dsleituraanormalidade as descricaoleituraanormalidadefa,"// 13
				+ "mdhi_nnconsumomedidomes as numeroConsumoMes,"// 14
				+ "consumoHistorico.cshi_nnconsumofaturadomes as numeroConsumoFaturadoMes,"// 15
				+ "consumoHistorico.cshi_nnconsumorateio as numeroConsumoRateio,"// 16
				+ "consumoAnormalidade.csan_dsabrvconsanormalidade as descricaoabreviadaconsumoanorm,"// 17
				+ "consumoTipo.cstp_dsabreviadaconsumotipo as descricaoAbreviadaConsumoTipo, " // 18
				+ "medicaoHistorico.mdhi_nnconsumomediohidrometro as consumoMedioHidrometro, " // 19
				+ "consumoHistorico.cshi_nnconsumomedio as consumoMedioImovel, " // 20
				+ "consumoHistoricoEsgoto.cshi_nnconsumofaturadomes as consumoFaturadoEsgoto "// 21
				+ "from cadastro.imovel imovel "
				+ " left outer join micromedicao.consumo_historico consumoHistorico on imovel.imov_id=consumoHistorico.imov_id and consumoHistorico.cshi_amfaturamento = "
				+ faturamentoGrupo.getAnoMesReferencia()
				+ " left outer join micromedicao.medicao_historico medicaoHistorico on medicaoHistorico.mdhi_amleitura = "
				+ faturamentoGrupo.getAnoMesReferencia()
				+ "  AND "
				+ ligadoPorAguaOuHidrometro
				+ " left outer join micromedicao.consumo_anormalidade consumoAnormalidade on consumoHistorico.csan_id=consumoAnormalidade.csan_id "
				+ " left outer join micromedicao.consumo_tipo consumoTipo on consumoHistorico.cstp_id=consumoTipo.cstp_id "
				+ " left outer join cadastro.quadra quadra on imovel.qdra_id=quadra.qdra_id "
				+ " left outer join micromedicao.rota rota on quadra.rota_id=rota.rota_id "
				+ " left outer join faturamento.faturamento_grupo faturamentoGrupo on rota.ftgr_id=faturamentoGrupo.ftgr_id "
				+ " left outer join cadastro.empresa empresa on rota.empr_id=empresa.empr_id "
				+ " left outer join cadastro.imovel_perfil imovelPerfil on imovel.iper_id=imovelPerfil.iper_id "
				+ " left outer join micromedicao.medicao_tipo medicaoTipo on medicaoHistorico.medt_id=medicaoTipo.medt_id "
				+ " left outer join micromedicao.leitura_situacao leituraSituacaoAtual on medicaoHistorico.ltst_idleiturasituacaoatual=leituraSituacaoAtual.ltst_id "
				+ " left outer join micromedicao.leitura_anormalidade leituraAnormalidadeInformada on medicaohistorico.ltan_idleitanorminformada=leituraAnormalidadeInformada.ltan_id "
				+ " left outer join micromedicao.leitura_anormalidade leituraAnormalidadeFaturamento on medicaohistorico.ltan_idleitanormfatmt=leituraAnormalidadeFaturamento.ltan_id "
				+ "	left outer join micromedicao.consumo_historico consumoHistoricoEsgoto on consumoHistoricoEsgoto.imov_id= "
				+ idImovel
				+ " and  consumoHistorico.cshi_amfaturamento = "
				+ faturamentoGrupo.getAnoMesReferencia()
				+ " and consumoHistorico.lgti_id = "
				+ LigacaoTipo.LIGACAO_ESGOTO
				+ " where imovel.imov_id = "
				+ idImovel;

		try {
			retorno = session
					.createSQLQuery(sql)
					.addScalar("descricaoMedTipo", Hibernate.STRING)
					.addScalar("idMedTipo", Hibernate.INTEGER)
					.addScalar("dataLeituraAnteriorFaturamento", Hibernate.DATE)
					.addScalar("dataLeituraAtualFaturamento", Hibernate.DATE)
					.addScalar("numeroleituraanteriorfaturamen",
							Hibernate.INTEGER).addScalar(
							"numeroleituraanteriorfaturamen",
							Hibernate.INTEGER).addScalar(
							"dataLeituraAtualInformada", Hibernate.DATE)
					.addScalar("numeroLeituraInformada", Hibernate.INTEGER)
					.addScalar("descricaoLeituraSituacao", Hibernate.STRING)
					.addScalar("dataLeituraFaturamento", Hibernate.DATE)
					.addScalar("numeroLeituraFaturamento", Hibernate.INTEGER)
					.addScalar("idFuncionario", Hibernate.INTEGER).addScalar(
							"descricaoleituraanormalidadein",
							Hibernate.STRING).addScalar(
							"descricaoleituraanormalidadefa",
							Hibernate.STRING).addScalar("numeroConsumoMes",
							Hibernate.INTEGER).addScalar(
							"numeroConsumoFaturadoMes", Hibernate.INTEGER)
					.addScalar("numeroConsumoRateio", Hibernate.INTEGER)
					.addScalar("descricaoabreviadaconsumoanorm",
							Hibernate.STRING).addScalar(
							"descricaoAbreviadaConsumoTipo", Hibernate.STRING)
					.addScalar("consumoMedioHidrometro", Hibernate.INTEGER)
					.addScalar("consumoMedioImovel", Hibernate.INTEGER)
					.addScalar("consumoFaturadoEsgoto", Hibernate.INTEGER)
					.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * Método que apresenta os dados do imovel
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 * 
	 * @author Sávio Luiz
	 * @date 04/08/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 */
	public Collection pesquisarMedicaoConsumoHistoricoExcecoesApresentaDadosConsultarImovel(
			Integer anoMesReferencia, Integer idImovel, boolean ligacaoAgua)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno = null;

		String ligadoPorAguaOuHidrometro = "";

		if (ligacaoAgua) {
			ligadoPorAguaOuHidrometro = " medicaoHistorico.lagu_id = imovel.imov_id ";
		} else {
			ligadoPorAguaOuHidrometro = " medicaoHistorico.imov_id = imovel.imov_id ";
		}

		/**TODO:COSANPA
		 * Autor:Adriana Muniz
		 * Data: 14/09/2011
		 * 
		 * Ajuste no select, pois estava trazendo informação do esgoto e não da agua
		 * */
		String sql = "select distinct medicaoTipo.medt_dsmedicaotipo as descricaoMedTipo,"// 0
				+ "medicaoTipo.medt_id as idMedTipo,"// 1
				+ "mdhi_dtleitantfatmt as dataLeituraAnteriorFaturamento,"// 2
				+ "mdhi_dtleituraatualfaturamento as dataLeituraAtualFaturamento,"// 3
				+ "mdhi_nnleituraatualfaturamento as numeroLeituraAtualFaturamento,"// 4
				+ "mdhi_nnleitantfatmt as numeroleituraanteriorfaturamen,"// 5
				+ "mdhi_dtleituraatualinformada as dataLeituraAtualInformada,"// 6
				+ "mdhi_nnleituraatualinformada as numeroLeituraInformada,"// 7
				+ "leituraSituacaoAtual.ltst_dsleiturasituacao as descricaoLeituraSituacao,"// 8
				+ "mdhi_dtleituraatualfaturamento as dataLeituraFaturamento,"// 9
				+ "mdhi_nnleituraatualfaturamento as numeroLeituraFaturamento,"// 10
				+ "medicaoHistorico.func_id as idFuncionario,"// 11
				+ "leituraAnormalidadeInformada.ltan_dsleituraanormalidade as descricaoleituraanormalidadein,"// 12
				+ "leituraAnormalidadeFaturamento.ltan_dsleituraanormalidade as descricaoleituraanormalidadefa,"// 13
				+ "mdhi_nnconsumomedidomes as numeroConsumoMes,"// 14
				+ "consumoHistorico.cshi_nnconsumofaturadomes as numeroConsumoFaturadoMes,"// 15
				+ "consumoHistorico.cshi_nnconsumorateio as numeroConsumoRateio,"// 16
				+ "consumoAnormalidade.csan_dsconsumoanormalidade as descricaoConsumoAnormalidade,"// 17
				+ "consumoTipo.cstp_dsconsumotipo as descricaoConsumoTipo, " // 18
				+ "medicaoHistorico.mdhi_nnconsumomediohidrometro as consumoMedioHidrometro, " // 19
				+ "consumoHistorico.cshi_nnconsumomedio as consumoMedioImovel, " // 20
				+ "consumoHistoricoEsgoto.cshi_nnconsumofaturadomes as consumoFaturadoEsgoto "// 21
				+ "from cadastro.imovel imovel "
				+ " inner join micromedicao.consumo_historico consumoHistorico on imovel.imov_id=consumoHistorico.imov_id and consumoHistorico.cshi_amfaturamento = "
				+ anoMesReferencia + " and  consumoHistorico.lgti_id = " + LigacaoTipo.LIGACAO_AGUA
				+ " left outer join micromedicao.medicao_historico medicaoHistorico on medicaoHistorico.mdhi_amleitura = "
				+ anoMesReferencia
				+ "  AND "
				+ ligadoPorAguaOuHidrometro
				+ " left outer join micromedicao.consumo_anormalidade consumoAnormalidade on consumoHistorico.csan_id=consumoAnormalidade.csan_id "
				+ " left outer join micromedicao.consumo_tipo consumoTipo on consumoHistorico.cstp_id=consumoTipo.cstp_id "
				+ " left outer join cadastro.quadra quadra on imovel.qdra_id=quadra.qdra_id "
				+ " left outer join micromedicao.rota rota on quadra.rota_id=rota.rota_id "
				+ " left outer join faturamento.faturamento_grupo faturamentoGrupo on rota.ftgr_id=faturamentoGrupo.ftgr_id "
				+ " left outer join cadastro.empresa empresa on rota.empr_id=empresa.empr_id "
				+ " left outer join cadastro.imovel_perfil imovelPerfil on imovel.iper_id=imovelPerfil.iper_id "
				+ " left outer join micromedicao.medicao_tipo medicaoTipo on medicaoHistorico.medt_id=medicaoTipo.medt_id "
				+ " left outer join micromedicao.leitura_situacao leituraSituacaoAtual on medicaoHistorico.ltst_idleiturasituacaoatual=leituraSituacaoAtual.ltst_id "
				+ " left outer join micromedicao.leitura_anormalidade leituraAnormalidadeInformada on medicaohistorico.ltan_idleitanorminformada=leituraAnormalidadeInformada.ltan_id "
				+ " left outer join micromedicao.leitura_anormalidade leituraAnormalidadeFaturamento on medicaohistorico.ltan_idleitanormfatmt=leituraAnormalidadeFaturamento.ltan_id "
				+ "	left outer join micromedicao.consumo_historico consumoHistoricoEsgoto on consumoHistoricoEsgoto.imov_id= "
				+ idImovel
				+ " and  consumoHistoricoEsgoto.cshi_amfaturamento = "
				+ anoMesReferencia
				+ " and consumoHistoricoEsgoto.lgti_id = "
				+ LigacaoTipo.LIGACAO_ESGOTO
				+ " where imovel.imov_id = "
				+ idImovel;

		try {
			retorno = session
					.createSQLQuery(sql)
					.addScalar("descricaoMedTipo", Hibernate.STRING)
					.addScalar("idMedTipo", Hibernate.INTEGER)
					.addScalar("dataLeituraAnteriorFaturamento", Hibernate.DATE)
					.addScalar("dataLeituraAtualFaturamento", Hibernate.DATE)
					.addScalar("numeroleituraanteriorfaturamen",
							Hibernate.INTEGER)
					.addScalar("numeroleituraanteriorfaturamen",
							Hibernate.INTEGER)
					.addScalar("dataLeituraAtualInformada", Hibernate.DATE)
					.addScalar("numeroLeituraInformada", Hibernate.INTEGER)
					.addScalar("descricaoLeituraSituacao", Hibernate.STRING)
					.addScalar("dataLeituraFaturamento", Hibernate.DATE)
					.addScalar("numeroLeituraFaturamento", Hibernate.INTEGER)
					.addScalar("idFuncionario", Hibernate.INTEGER)
					.addScalar("descricaoleituraanormalidadein",
							Hibernate.STRING)
					.addScalar("descricaoleituraanormalidadefa",
							Hibernate.STRING)
					.addScalar("numeroConsumoMes", Hibernate.INTEGER)
					.addScalar("numeroConsumoFaturadoMes", Hibernate.INTEGER)
					.addScalar("numeroConsumoRateio", Hibernate.INTEGER)
					.addScalar("descricaoConsumoAnormalidade", Hibernate.STRING)
					.addScalar("descricaoConsumoTipo", Hibernate.STRING)
					.addScalar("consumoMedioHidrometro", Hibernate.INTEGER)
					.addScalar("consumoMedioImovel", Hibernate.INTEGER)
					.addScalar("consumoFaturadoEsgoto", Hibernate.INTEGER)
					.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * Retorna uma coleção com os dados das medicoes para apresentação
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 */
	public Collection carregarDadosMedicao(Integer idImovel, boolean ligacaoAgua)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;
		try {
			consulta = "select medicaoHistorico.mdhi_amleitura as mesAno,"
					+ " medicaoHistorico.mdhi_dtleituraatualinformada as dataLeituraInformada,"
					+ " medicaoHistorico.mdhi_nnleituraatualinformada as numeroLeituraInformada,"
					+ " medicaoHistorico.mdhi_dtleituraatualfaturamento as dataLeituraFaturada,"
					+ " medicaoHistorico.mdhi_nnleituraatualfaturamento as numeroLeituraFaturada,"
					+ " leituraAnormalidadeInformada.ltan_dsleituraanormalidade as descricaoanormalidadeleiturain,"
					+ " leituraAnormalidadeFaturamento.ltan_dsleituraanormalidade as descricaoanormalidadeleiturafa,"
					+ " leituraSituacao.ltst_dsleiturasituacao as leituraSituacao,"
					+ " medicaoHistorico.mdhi_nnconsumomediohidrometro as consumoMedioHidrometro,"
					+ " leituraAnormalidadeInformada.ltan_id as idAnormalidadeLeituraInformada,"
					+ " leituraAnormalidadeFaturamento.ltan_id as idAnormalidadeLeituraFaturada,"
					+ " leituraAnormalidadeFaturamento.ltan_dsleituraanormalidade as descricaoanormalidadeleiturafa"
					+ " from micromedicao.medicao_historico medicaoHistorico "
					+ " left outer join micromedicao.leitura_situacao leituraSituacao on medicaoHistorico.ltst_idleiturasituacaoatual = leituraSituacao.ltst_id "
					+ "	left outer join micromedicao.leitura_anormalidade leituraAnormalidadeInformada on medicaohistorico.ltan_idleitanorminformada=leituraAnormalidadeInformada.ltan_id "
					+ "	left outer join micromedicao.leitura_anormalidade leituraAnormalidadeFaturamento on medicaohistorico.ltan_idleitanormfatmt=leituraAnormalidadeFaturamento.ltan_id ";
			if (ligacaoAgua) {
				consulta = consulta + " where medicaoHistorico.lagu_id = "
						+ idImovel
						+ " order by medicaoHistorico.mdhi_amleitura";
			} else {
				consulta = consulta + " where medicaoHistorico.imov_id = "
						+ idImovel
						+ " order by medicaoHistorico.mdhi_amleitura";
			}

			retorno = session.createSQLQuery(consulta).addScalar("mesAno",
					Hibernate.INTEGER).addScalar("dataLeituraInformada",
					Hibernate.DATE).addScalar("numeroLeituraInformada",
					Hibernate.INTEGER).addScalar("dataLeituraFaturada",
					Hibernate.DATE).addScalar("numeroLeituraFaturada",
					Hibernate.INTEGER).addScalar(
					"descricaoanormalidadeleiturain", Hibernate.STRING)
					.addScalar("descricaoanormalidadeleiturafa",
							Hibernate.STRING).addScalar("leituraSituacao",
							Hibernate.STRING).addScalar(
							"consumoMedioHidrometro", Hibernate.INTEGER)
					.addScalar("idAnormalidadeLeituraInformada",
							Hibernate.INTEGER).addScalar(
							"idAnormalidadeLeituraFaturada", Hibernate.INTEGER)
					.addScalar("descricaoanormalidadeleiturafa",
							Hibernate.STRING).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * Retorna uma coleção com os dados das medicoes para apresentação
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 */
	public Collection carregarDadosMedicaoResumo(Integer idImovel,
			boolean ligacaoAgua) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;
		try {
			consulta = "select medicaoHistorico.mdhi_amleitura as mesAno,"
					+ " medicaoHistorico.mdhi_dtleituraatualinformada as dataLeituraInformada,"
					+ " medicaoHistorico.mdhi_nnleituraatualinformada as numeroLeituraInformada,"
					+ " medicaoHistorico.mdhi_dtleituraatualfaturamento as dataLeituraFaturada,"
					+ " medicaoHistorico.mdhi_nnleituraatualfaturamento as numeroLeituraFaturada,"
					+ " leituraAnormalidadeInformada.ltan_id as idAnormalidadeLeituraInformada,"
					+ " leituraAnormalidadeFaturamento.ltan_id as idAnormalidadeLeituraFaturada,"
					+ " leituraAnormalidadeFaturamento.ltan_dsleituraanormalidade as descricaoanormalidadeleiturafa,"
					+ " leituraSituacao.ltst_dsleiturasituacao as leituraSituacao"
					+ " from micromedicao.medicao_historico medicaoHistorico "
					+ " left outer join micromedicao.leitura_situacao leituraSituacao on medicaoHistorico.ltst_idleiturasituacaoatual = leituraSituacao.ltst_id "
					+ "	left outer join micromedicao.leitura_anormalidade leituraAnormalidadeInformada on medicaohistorico.ltan_idleitanorminformada=leituraAnormalidadeInformada.ltan_id "
					+ "	left outer join micromedicao.leitura_anormalidade leituraAnormalidadeFaturamento on medicaohistorico.ltan_idleitanormfatmt=leituraAnormalidadeFaturamento.ltan_id ";
			if (ligacaoAgua) {
				consulta = consulta + " where medicaoHistorico.lagu_id = "
						+ idImovel
						+ " order by medicaoHistorico.mdhi_amleitura";
			} else {
				consulta = consulta + " where medicaoHistorico.imov_id = "
						+ idImovel
						+ " order by medicaoHistorico.mdhi_amleitura";
			}

			retorno = session.createSQLQuery(consulta).addScalar("mesAno",
					Hibernate.INTEGER).addScalar("dataLeituraInformada",
					Hibernate.DATE).addScalar("numeroLeituraInformada",
					Hibernate.INTEGER).addScalar("dataLeituraFaturada",
					Hibernate.DATE).addScalar("numeroLeituraFaturada",
					Hibernate.INTEGER).addScalar(
					"idAnormalidadeLeituraInformada", Hibernate.INTEGER)
					.addScalar("idAnormalidadeLeituraFaturada",
							Hibernate.INTEGER).addScalar(
							"descricaoanormalidadeleiturafa",
							Hibernate.STRING).addScalar("leituraSituacao",
							Hibernate.STRING).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * Retorna um objeto com os dados das medicoes para apresentação
	 * 
	 * Flávio
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 */
	public Object[] carregarDadosMedicaoResumido(Integer idImovel,
			boolean ligacaoAgua, String anoMes) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;
		try {
			consulta = "select medicaoHistorico.mdhi_amleitura as mesAno,"
					+ " medicaoHistorico.mdhi_dtleituraatualinformada as dataLeituraInformada,"
					+ " medicaoHistorico.mdhi_nnleituraatualinformada as numeroLeituraInformada,"
					+ " medicaoHistorico.mdhi_dtleitantfatmt as dataLeituraAnteriorFaturada,"
					+ " medicaoHistorico.mdhi_nnleitantfatmt as numeroLeituraAnteriorFaturada,"
					+ " leituraAnormalidadeInformada.ltan_dsabrevleituraanormalidad as descricaoanormalidadeleiturain,"
					+ " leituraAnormalidadeFaturamento.ltan_dsabrevleituraanormalidad as descricaoanormalidadeleiturafa,"
					+ " medicaoHistorico.mdhi_nnxconsecanormalidade as numerovezesconsecutivosanormal,"
					+ " medicaoHistorico.func_id as idFuncionario,"
					+ " leituraSituacao.ltst_dsleiturasituacao as leituraSituacao, "
					+ " medicaoHistorico.mdhi_dtleituraatualfaturamento as dataLeituraAtualFaturada,"
					+ " medicaoHistorico.mdhi_nnleituraatualfaturamento as numeroLeituraAtualFaturada,"
					+ " medicaoHistorico.ltan_idleitanormfatmt as idAnormalidade, "
					+ " medicaoHistorico.mdhi_nnconsumoinformado as consumoInformado,"
					+ " medicaoHistorico.mdhi_nnconsumomediohidrometro as consumoMedioHidrometro,"
					+ " medicaoHistorico.motp_id as idMotivoInterferencia"
					+ " from micromedicao.medicao_historico medicaoHistorico "
					+ " left outer join micromedicao.leitura_situacao leituraSituacao on medicaoHistorico.ltst_idleiturasituacaoatual = leituraSituacao.ltst_id "
					+ "	left outer join micromedicao.leitura_anormalidade leituraAnormalidadeInformada on medicaohistorico.ltan_idleitanorminformada=leituraAnormalidadeInformada.ltan_id "
					+ "	left outer join micromedicao.leitura_anormalidade leituraAnormalidadeFaturamento on medicaohistorico.ltan_idleitanormfatmt=leituraAnormalidadeFaturamento.ltan_id ";
			if (ligacaoAgua) {
				consulta = consulta + " where medicaoHistorico.lagu_id = "
						+ idImovel + " and medicaoHistorico.mdhi_amleitura = "
						+ anoMes + " order by medicaoHistorico.mdhi_amleitura";
			} else {
				consulta = consulta + " where medicaoHistorico.imov_id = "
						+ idImovel + " and medicaoHistorico.mdhi_amleitura = "
						+ anoMes + " order by medicaoHistorico.mdhi_amleitura";
			}

			retorno = (Object[]) session.createSQLQuery(consulta).addScalar(
					"mesAno", Hibernate.INTEGER)// 0
					.addScalar("dataLeituraInformada", Hibernate.DATE)// 1
					.addScalar("numeroLeituraInformada", Hibernate.INTEGER)// 2
					.addScalar("dataLeituraAnteriorFaturada", Hibernate.DATE)// 3
					.addScalar("numeroLeituraAnteriorFaturada",
							Hibernate.INTEGER)// 4
					.addScalar("descricaoanormalidadeleiturain",
							Hibernate.STRING)// 5
					.addScalar("descricaoanormalidadeleiturafa",
							Hibernate.STRING)// 6
					.addScalar("numerovezesconsecutivosanormal",
							Hibernate.SHORT)// 7
					.addScalar("idFuncionario", Hibernate.INTEGER)// 8
					.addScalar("leituraSituacao", Hibernate.STRING)// 9
					.addScalar("dataLeituraAtualFaturada", Hibernate.DATE)// 10
					.addScalar("numeroLeituraAtualFaturada", Hibernate.INTEGER)// 11
					.addScalar("idAnormalidade", Hibernate.INTEGER)// 12
					.addScalar("consumoInformado", Hibernate.INTEGER)// 13
					.addScalar("consumoMedioHidrometro", Hibernate.INTEGER)// 14
					.addScalar("idMotivoInterferencia", Hibernate.INTEGER)// 15
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * Retorna uma coleção com os dados dos Consumos para apresentação
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 */
	public Collection carregarDadosConsumo(Integer idImovel, int anoMes,
			boolean ligacaoAgua) throws ErroRepositorioException {

		/**
		 * TODO : COSANPA
		 * Alteracao para enviar o ID da anormalidade de consumo
		 */
		
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;
		try {
			if (ligacaoAgua) {
				consulta = "select consumoHistorico.cshi_amfaturamento as mesAno,"// 0
						+ " medicaohistorico.mdhi_nnconsumomedidomes as consumoMedido,"// 1
						+ " consumoHistorico.cshi_nnconsumofaturadomes as consumoFaturado,"// 2
						+ " consumoHistorico.cshi_nnconsumorateio as consumoRateio,"// 3
						+ " consumoAnormalidade.csan_dsconsumoanormalidade as consumoAnormalidade,"// 4
						+ " consumoTipo.cstp_dsconsumotipo as consumoTipo, " // 5
						+ " consumoTipo.cstp_dsabreviadaconsumotipo as consumoTipoAbreviado,"// 6
						+ " medicaoHistorico.mdhi_dtleitantfatmt as dataLeituraAnterior,"// 7
						+ " medicaoHistorico.mdhi_dtleituraatualfaturamento as dataLeituraAtual,"// 8
						+ " consumoHistoricoEsgoto.cshi_nnconsumofaturadomes as consumoFaturadoEsgoto,"// 9
						+ " consumoHistorico.cshi_nnconsumomedio as consumoMedio, "// 10
						+ " medicaoHistorico.ltan_idleitanormfatmt as idAnormalidade,"// 11
						+ " medicaoHistorico.mdhi_nnconsumoInformado as consumoInformado, "// 12
						+ " consumoAnormalidade.csan_dsabrvconsanormalidade as consumoAnormalidadeAbreviada, "// 13
						+ " consumoHistorico.cshi_nnconsumorateio as rateio, "// 14
						+ " consumoHistorico.cshi_nnconsumocalculomedia as consumoCalculoMedia, "// 15
						+ " consumoAnormalidade.csan_id as idAnormalidadeConsumo " // 16
						+ " from micromedicao.consumo_historico consumoHistorico"
						+ " inner join micromedicao.medicao_historico medicaoHistorico on consumoHistorico.imov_id = medicaoHistorico.lagu_id and consumoHistorico.cshi_amfaturamento = medicaoHistorico.mdhi_amleitura"
						+ " left outer join micromedicao.consumo_anormalidade consumoAnormalidade on consumoHistorico.csan_id = consumoAnormalidade.csan_id"
						+ " left outer join micromedicao.consumo_tipo consumoTipo on consumoHistorico.cstp_id=consumoTipo.cstp_id"
						+ " left outer join micromedicao.consumo_historico consumoHistoricoEsgoto on consumoHistorico.imov_id = consumoHistoricoEsgoto.imov_id and consumoHistorico.cshi_amfaturamento = consumoHistoricoEsgoto.cshi_amfaturamento and consumoHistoricoEsgoto.lgti_id = "
						+ LigacaoTipo.LIGACAO_ESGOTO
						+ " where consumoHistorico.imov_id ="
						+ idImovel
						+ " and consumoHistorico.lgti_id = "
						+ LigacaoTipo.LIGACAO_AGUA
						+ " and consumoHistorico.cshi_amfaturamento = "
						+ anoMes
						+ " order by consumoHistorico.cshi_amfaturamento";

				retorno = session.createSQLQuery(consulta).addScalar("mesAno",
						Hibernate.INTEGER).addScalar("consumoMedido",
						Hibernate.INTEGER).addScalar("consumoFaturado",
						Hibernate.INTEGER).addScalar("consumoRateio",
						Hibernate.INTEGER).addScalar("consumoAnormalidade",
						Hibernate.STRING).addScalar("consumoTipo",
						Hibernate.STRING).addScalar("consumoTipoAbreviado",
						Hibernate.STRING).addScalar("dataLeituraAnterior",
						Hibernate.DATE).addScalar("dataLeituraAtual",
						Hibernate.DATE).addScalar("consumoFaturadoEsgoto",
						Hibernate.INTEGER).addScalar("consumoMedio",
						Hibernate.INTEGER).addScalar("idAnormalidade",
						Hibernate.INTEGER).addScalar("consumoInformado",
						Hibernate.INTEGER).addScalar(
						"consumoAnormalidadeAbreviada", Hibernate.STRING)
						.addScalar("rateio", Hibernate.INTEGER).addScalar(
								"consumoCalculoMedia", Hibernate.INTEGER)
						.addScalar(
								"idAnormalidadeConsumo", Hibernate.INTEGER)
						.list();
			} else {
				consulta = "select consumoHistorico.cshi_amfaturamento as mesAno,"
						+ " medicaohistorico.mdhi_nnconsumomedidomes as consumoMedido,"
						+ " consumoHistorico.cshi_nnconsumofaturadomes as consumoFaturado,"
						+ " consumoHistorico.cshi_nnconsumorateio as consumoRateio,"
						+ " consumoAnormalidade.csan_dsconsumoanormalidade as consumoAnormalidade,"
						+ " consumoTipo.cstp_dsconsumotipo as consumoTipo, "
						+ " consumoTipo.cstp_dsabreviadaconsumotipo as consumoTipoAbreviado,"
						+ " medicaoHistorico.mdhi_dtleitantfatmt as dataLeituraAnterior,"
						+ " medicaoHistorico.mdhi_dtleituraatualfaturamento as dataLeituraAtual,"
						+ " consumoHistorico.cshi_nnconsumomedio as consumoMedio,"
						+ " medicaoHistorico.ltan_idleitanormfatmt as idAnormalidade,"
						+ " medicaoHistorico.mdhi_nnconsumoInformado as consumoInformado, "
						+ " consumoAnormalidade.csan_dsabrvconsanormalidade as consumoAnormalidadeAbreviada, "
						+ " consumoHistorico.cshi_nnconsumorateio as rateio,"// 13
						+ " consumoHistorico.cshi_nnconsumorateio as rateio, "// 14
						+ " consumoHistorico.cshi_nnconsumocalculomedia as consumoCalculoMedia, "// 15
						+ " consumoAnormalidade.csan_id as idAnormalidadeConsumo " // 16
						+ " from micromedicao.consumo_historico consumoHistorico "
						+ " inner join micromedicao.medicao_historico medicaoHistorico on consumoHistorico.imov_id = medicaoHistorico.imov_id and consumoHistorico.cshi_amfaturamento = medicaoHistorico.mdhi_amleitura"
						+ " left outer join micromedicao.consumo_anormalidade consumoAnormalidade on consumoHistorico.csan_id = consumoAnormalidade.csan_id"
						+ " left outer join micromedicao.consumo_tipo consumoTipo on consumoHistorico.cstp_id=consumoTipo.cstp_id"
						+ " where consumoHistorico.imov_id = "
						+ idImovel
						+ " and consumoHistorico.lgti_id = "
						+ LigacaoTipo.LIGACAO_ESGOTO
						+ " and consumoHistorico.cshi_amfaturamento = "
						+ anoMes
						+ " order by consumoHistorico.cshi_amfaturamento";

				retorno = session.createSQLQuery(consulta).addScalar("mesAno",
						Hibernate.INTEGER).addScalar("consumoMedido",
						Hibernate.INTEGER).addScalar("consumoFaturado",
						Hibernate.INTEGER).addScalar("consumoRateio",
						Hibernate.INTEGER).addScalar("consumoAnormalidade",
						Hibernate.STRING).addScalar("consumoTipo",
						Hibernate.STRING).addScalar("consumoTipoAbreviado",
						Hibernate.STRING).addScalar("dataLeituraAnterior",
						Hibernate.DATE).addScalar("dataLeituraAtual",
						Hibernate.DATE).addScalar("consumoMedio",
						Hibernate.INTEGER).addScalar("idAnormalidade",
						Hibernate.INTEGER).addScalar("consumoInformado",
						Hibernate.INTEGER).addScalar(
						"consumoAnormalidadeAbreviada", Hibernate.STRING)
						.addScalar("rateio", Hibernate.INTEGER).addScalar(
								"consumoCalculoMedia", Hibernate.INTEGER)
						.list();
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * Retorna uma coleção com os dados dos Consumos para apresentação sem
	 * informar o ano/mes para o caso em que o Imovel nao possui Hidrometro (Sem
	 * Medicao).
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 */
	public Collection carregarDadosConsumo(Integer idImovel, boolean ligacaoAgua)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;
		try {
			if (ligacaoAgua) {
				consulta = "select consumoHistorico.cshi_amfaturamento as mesAno,"// 0
						+ " medicaohistorico.mdhi_nnconsumomedidomes as consumoMedido,"// 1
						+ " consumoHistorico.cshi_nnconsumofaturadomes as consumoFaturado,"// 2
						+ " consumoHistorico.cshi_nnconsumorateio as consumoRateio,"// 3
						+ " consumoAnormalidade.csan_dsconsumoanormalidade as consumoAnormalidade,"// 4
						+ " consumoTipo.cstp_dsconsumotipo as consumoTipo, " // 5
						+ " consumoTipo.cstp_dsabreviadaconsumotipo as consumoTipoAbreviado, " // 6
						+ " medicaoHistorico.mdhi_dtleitantfatmt as dataLeituraAnterior,"// 7
						+ " medicaoHistorico.mdhi_dtleituraatualfaturamento as dataLeituraAtual,"// 8
						+ " consumoHistoricoEsgoto.cshi_nnconsumofaturadomes as consumoFaturadoEsgoto,"// 9
						+ " consumoHistorico.cshi_nnconsumomedio as consumoMedio, "// 10
						+ " medicaoHistorico.ltan_idleitanormfatmt as idAnormalidade,"// 11
						+ " medicaoHistorico.mdhi_nnconsumoInformado as consumoInformado, "// 12
						+ " consumoAnormalidade.csan_dsabrvconsanormalidade as consumoAnormalidadeAbreviada, "// 13
						+ " consumoHistorico.cshi_nnconsumorateio as rateio, "// 14
						+ " consumoHistorico.cshi_nnconsumocalculomedia as consumoCalculoMedia, "// 15
						+ " medicaoHistorico.mdhi_dtleituraatualinformada as dataLeituraInformada," // 16
						+ " medicaoHistorico.mdhi_nnleituraatualinformada as numeroLeituraInformada," // 17
						+ " medicaoHistorico.mdhi_nnleituraatualfaturamento as numeroLeituraFaturada, " // 18
						+ " leituraAnormalidadeInformada.ltan_id as idAnormalidadeLeituraInformada," // 19
						+ " leituraAnormalidadeFaturamento.ltan_dsleituraanormalidade as descricaoanormalidadeleiturafa," // 20
						+ " leituraSituacao.ltst_dsleiturasituacao as leituraSituacao, " // 21
						+ " consumoAnormalidade.csan_id as idAnormalidadeConsumo " // 22
						+ " from micromedicao.consumo_historico consumoHistorico"
						+ " left join micromedicao.medicao_historico medicaoHistorico on consumoHistorico.imov_id = medicaoHistorico.lagu_id and consumoHistorico.cshi_amfaturamento = medicaoHistorico.mdhi_amleitura"
						+ " left outer join micromedicao.consumo_anormalidade consumoAnormalidade on consumoHistorico.csan_id = consumoAnormalidade.csan_id"
						+ " left outer join micromedicao.consumo_tipo consumoTipo on consumoHistorico.cstp_id=consumoTipo.cstp_id"
						+ " left outer join micromedicao.consumo_historico consumoHistoricoEsgoto on consumoHistorico.imov_id = consumoHistoricoEsgoto.imov_id and consumoHistorico.cshi_amfaturamento = consumoHistoricoEsgoto.cshi_amfaturamento and consumoHistoricoEsgoto.lgti_id = "
						+ LigacaoTipo.LIGACAO_ESGOTO
						+ " left outer join micromedicao.leitura_situacao leituraSituacao on medicaoHistorico.ltst_idleiturasituacaoatual = leituraSituacao.ltst_id "
						+ " left outer join micromedicao.leitura_anormalidade leituraAnormalidadeInformada on medicaohistorico.ltan_idleitanorminformada=leituraAnormalidadeInformada.ltan_id "
						+ " left outer join micromedicao.leitura_anormalidade leituraAnormalidadeFaturamento on medicaohistorico.ltan_idleitanormfatmt=leituraAnormalidadeFaturamento.ltan_id "
						+ " where consumoHistorico.imov_id ="
						+ idImovel
						+ " and consumoHistorico.lgti_id = "
						+ LigacaoTipo.LIGACAO_AGUA
						+ " order by consumoHistorico.cshi_amfaturamento desc ";

				retorno = session.createSQLQuery(consulta)
						.addScalar("mesAno",Hibernate.INTEGER)
						.addScalar("consumoMedido",Hibernate.INTEGER)
						.addScalar("consumoFaturado",Hibernate.INTEGER)
						.addScalar("consumoRateio",Hibernate.INTEGER)
						.addScalar("consumoAnormalidade",Hibernate.STRING)
						.addScalar("consumoTipo",Hibernate.STRING)
						.addScalar("consumoTipoAbreviado",Hibernate.STRING)
						.addScalar("dataLeituraAnterior",Hibernate.DATE)
						.addScalar("dataLeituraAtual",Hibernate.DATE)
						.addScalar("consumoFaturadoEsgoto",Hibernate.INTEGER)
						.addScalar("consumoMedio",Hibernate.INTEGER)
						.addScalar("idAnormalidade",Hibernate.INTEGER)
						.addScalar("consumoInformado",Hibernate.INTEGER)
						.addScalar("consumoAnormalidadeAbreviada", Hibernate.STRING)
						.addScalar("rateio", Hibernate.INTEGER).addScalar("consumoCalculoMedia", Hibernate.INTEGER)
						.addScalar("dataLeituraInformada", Hibernate.DATE)
						.addScalar("numeroLeituraInformada", Hibernate.INTEGER)
						.addScalar("numeroLeituraFaturada", Hibernate.INTEGER)
						.addScalar("idAnormalidadeLeituraInformada",Hibernate.INTEGER)
						.addScalar("descricaoanormalidadeleiturafa",Hibernate.STRING)
						.addScalar("leituraSituacao",Hibernate.STRING)
						.addScalar("idAnormalidadeConsumo",Hibernate.INTEGER).list();
			} else {
				consulta = "select consumoHistorico.cshi_amfaturamento as mesAno," // 0
						+ " null as consumoMedido, " // 1
						+ " consumoHistorico.cshi_nnconsumofaturadomes as consumoFaturado," // 2
						+ " consumoHistorico.cshi_nnconsumorateio as consumoRateio," // 3
						+ " consumoAnormalidade.csan_dsconsumoanormalidade as consumoAnormalidade," // 4
						+ " consumoTipo.cstp_dsabreviadaconsumotipo as consumoTipo," // 5
						+ " consumoTipo.cstp_dsabreviadaconsumotipo as consumoTipoAbreviado, " // 6
						+ " null as dataLeituraAnterior, " // 7
						+ " medicaoHistorico.mdhi_dtleituraatualfaturamento as dataLeituraAtual, " // 8
						+ " consumoHistorico.cshi_nnconsumomedio as consumoMedio," // 9
						+ " medicaoHistorico.ltan_idleitanormfatmt as idAnormalidade, " // 10
						+ " null as consumoInformado, " // 11
						+ " consumoAnormalidade.csan_dsabrvconsanormalidade as consumoAnormalidadeAbreviada, " // 12
						+ " consumoHistorico.cshi_nnconsumorateio as rateio," // 13
						+ " consumoHistorico.cshi_nnconsumocalculomedia as consumoCalculoMedia," // 14
						+ " medicaoHistorico.mdhi_dtleituraatualinformada as dataLeituraInformada," // 15
						+ " medicaoHistorico.mdhi_nnleituraatualinformada as numeroLeituraInformada," // 16
						+ " medicaoHistorico.mdhi_nnleituraatualfaturamento as numeroLeituraFaturada " // 17
						+ " from micromedicao.consumo_historico consumoHistorico"
						+ " left join micromedicao.medicao_historico medicaoHistorico on consumoHistorico.imov_id = medicaoHistorico.imov_id and consumoHistorico.cshi_amfaturamento = medicaoHistorico.mdhi_amleitura"
						+ " left outer join micromedicao.consumo_anormalidade consumoAnormalidade on consumoHistorico.csan_id = consumoAnormalidade.csan_id"
						+ " left outer join micromedicao.consumo_tipo consumoTipo on consumoHistorico.cstp_id=consumoTipo.cstp_id"
						+ " where consumoHistorico.imov_id = "
						+ idImovel
						+ " and consumoHistorico.lgti_id = "
						+ LigacaoTipo.LIGACAO_ESGOTO
						+ " order by consumoHistorico.cshi_amfaturamento";

				retorno = session.createSQLQuery(consulta).addScalar("mesAno",
						Hibernate.INTEGER).addScalar("consumoMedido",
						Hibernate.INTEGER).addScalar("consumoFaturado",
						Hibernate.INTEGER).addScalar("consumoRateio",
						Hibernate.INTEGER).addScalar("consumoAnormalidade",
						Hibernate.STRING).addScalar("consumoTipo",
						Hibernate.STRING).addScalar("consumoTipoAbreviado",
						Hibernate.STRING).addScalar("dataLeituraAnterior",
						Hibernate.DATE).addScalar("dataLeituraAtual",
						Hibernate.DATE).addScalar("consumoMedio",
						Hibernate.INTEGER).addScalar("idAnormalidade",
						Hibernate.INTEGER).addScalar("consumoInformado",
						Hibernate.INTEGER).addScalar(
						"consumoAnormalidadeAbreviada", Hibernate.STRING)
						.addScalar("rateio", Hibernate.INTEGER).addScalar(
								"consumoCalculoMedia", Hibernate.INTEGER)
						.addScalar("dataLeituraInformada", Hibernate.DATE)
						.addScalar("numeroLeituraInformada", Hibernate.INTEGER)
						.addScalar("numeroLeituraFaturada", Hibernate.INTEGER)
						.list();
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * Retorna um Objeto com os dados dos Consumos para apresentação
	 * 
	 * Flávio
	 * 
	 * [UC0153] Apresentar dados para Analise da medição e Consumo
	 */
	public Object[] carregarDadosConsumoResumido(Integer idImovel, int anoMes,
			boolean ligacaoAgua) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;
		try {
			if (ligacaoAgua) {
				consulta = "select consumoHistorico.cshi_amfaturamento as mesAno,"
						+ " consumoHistorico.cshi_nnconsumofaturadomes as consumoFaturado,"
						+ " consumoHistorico.cshi_nnconsumomedio as consumoMedio,"
						+ " consumoTipo.cstp_dsconsumotipo as consumoTipo,"
						+ " consumoHistoricoEsgoto.cshi_nnconsumofaturadomes as consumoFaturadoEsgoto"
						+ " from micromedicao.consumo_historico consumoHistorico"
						+ " left outer join micromedicao.consumo_tipo consumoTipo on consumoHistorico.cstp_id=consumoTipo.cstp_id"
						+ " left outer join micromedicao.consumo_historico consumoHistoricoEsgoto on consumoHistorico.imov_id = consumoHistoricoEsgoto.imov_id and consumoHistorico.cshi_amfaturamento = consumoHistoricoEsgoto.cshi_amfaturamento and consumoHistoricoEsgoto.lgti_id = "
						+ LigacaoTipo.LIGACAO_ESGOTO
						+ " where consumoHistorico.imov_id ="
						+ idImovel
						+ " and consumoHistorico.lgti_id = "
						+ LigacaoTipo.LIGACAO_AGUA
						+ " and consumoHistorico.cshi_amfaturamento = "
						+ anoMes
						+ " order by consumoHistorico.cshi_amfaturamento";

				retorno = (Object[]) session.createSQLQuery(consulta)
						.addScalar("mesAno", Hibernate.INTEGER).addScalar(
								"consumoMedio", Hibernate.INTEGER).addScalar(
								"consumoFaturado", Hibernate.INTEGER)
						.addScalar("consumoTipo", Hibernate.STRING)
						.uniqueResult();
			} else {
				consulta = "select consumoHistorico.cshi_amfaturamento as mesAno,"
						+ " consumoHistorico.cshi_nnconsumofaturadomes as consumoFaturado,"
						+ " consumoHistorico.cshi_nnconsumomedio as consumoMedio,"
						+ " consumoTipo.cstp_dsconsumotipo as consumoTipo,"
						+ " consumoHistoricoEsgoto.cshi_nnconsumofaturadomes as consumoFaturadoEsgoto"
						+ " from micromedicao.consumo_historico consumoHistorico"
						+ " left outer join micromedicao.consumo_tipo consumoTipo on consumoHistorico.cstp_id=consumoTipo.cstp_id"
						+ " left outer join micromedicao.consumo_historico consumoHistoricoEsgoto on consumoHistorico.imov_id = consumoHistoricoEsgoto.imov_id and consumoHistorico.cshi_amfaturamento = consumoHistoricoEsgoto.cshi_amfaturamento and consumoHistoricoEsgoto.lgti_id = "
						+ " where consumoHistorico.imov_id = "
						+ idImovel
						+ " and consumoHistorico.lgti_id = "
						+ LigacaoTipo.LIGACAO_ESGOTO
						+ " and consumoHistorico.cshi_amfaturamento = "
						+ anoMes
						+ " order by consumoHistorico.cshi_amfaturamento";

				retorno = (Object[]) session.createSQLQuery(consulta)
						.addScalar("mesAno", Hibernate.INTEGER).addScalar(
								"consumoMedio", Hibernate.INTEGER).addScalar(
								"consumoFaturado", Hibernate.INTEGER)
						.addScalar("consumoTipo", Hibernate.STRING)
						.uniqueResult();

			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Método que retorna os imoveis condominiais e esteja com ligados ou
	 * cortados a agua e ou ligados com esgoto que possuam hidrometro no poço
	 * das rotas passadas
	 * 
	 * [UC0103] Efetuar Rateio de Consumo
	 * 
	 * @author Thiago Toscano, Pedro Alexandre, Raphael Rossiter
	 * @date 07/04/2006, 04/09/2006, 26/08/2009
	 * 
	 * @param idRota
	 * @return Imoveis
	 */
	public Collection pesquisarImovelCondominioParaCalculoDoRateioPorRota(
			Integer idRota) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select imovel.id, imovel.ligacaoAguaSituacao.id, " // 0,1
					+ "imovel.ligacaoEsgotoSituacao.id, " // 2
					+ "imovel.ligacaoAguaSituacao.indicadorFaturamentoSituacao, "// 3
					+ "imovel.ligacaoEsgotoSituacao.indicadorFaturamentoSituacao "// 4

					+ "from gcom.cadastro.imovel.Imovel as imovel "
					+ "inner join imovel.quadra as quadra "
					+ "inner join quadra.rota as rota "
					+ "inner join rota.faturamentoGrupo as ftgr "
					+ "left join imovel.ligacaoAgua ligacaoAgua "

					// VERIFICANDO SE É UM IMÓVEL CONDOMÍNIO
					+ "WHERE imovel.indicadorImovelCondominio = "
					+ ConstantesSistema.SIM

					// VERIFICANDO SE O IMÓVEL ESTÁ DISPONÍVEL PARA EFETUAR
					// RATEIO
					+ " AND (imovel.ligacaoAguaSituacao.indicadorFaturamentoSituacao = :faturamentoAgua OR "
					+ "imovel.ligacaoEsgotoSituacao.indicadorFaturamentoSituacao = :faturamentoEsgoto)"

					+ " AND (imovel.hidrometroInstalacaoHistorico IS NOT NULL "
					+ "      OR ligacaoAgua.hidrometroInstalacaoHistorico IS NOT NULL) "

					// VERIFICANDO SE O IMÓVEL PERTENCE A ROTA
					+ " AND rota.id = :idRota AND imovel.rotaAlternativa IS NULL"
					+ " AND imovel.indicadorExclusao <> 1 "
					+ " AND NOT EXISTS "
					+ "(select im.id "
					+ " from ConsumoHistorico consumoHistorico "
					+ " inner join consumoHistorico.imovel im "
					+ " where im.id = imovel.id and consumoHistorico.consumoRateio is not null "
					+ " and consumoHistorico.referenciaFaturamento = ftgr.anoMesReferencia)";

			retorno = session.createQuery(consulta).setShort("faturamentoAgua",
					ConstantesSistema.SIM.shortValue()).setShort(
					"faturamentoEsgoto", ConstantesSistema.SIM.shortValue())
					.setInteger("idRota", idRota).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Método que retorna todos os imoveis veinculados a um imovel condominio
	 * 
	 * [UC0103] Efetuar Rateio de Consumo
	 * 
	 * @author Thiago Toscano, Pedro Alexandre
	 * @date 07/04/2006, 04/09/2006
	 * 
	 * @param rotas
	 * @return Imoveis
	 */
	public Collection getImovelVinculadosImovelCondominio(
			Integer idImovelCondominio) throws ErroRepositorioException {

		Collection retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			// gerando o hql das rotas
			consulta = 
				"select " + 
				"  imov.id," +//0
				"  imov.quantidadeEconomias," +//1
				"  last.id, lest.id, " +//2,3
				"  imov.hidrometroInstalacaoHistorico.id," +//4
				"  imov.consumoTarifa.id, " +//5
				"  lagu.id, " +//6
				"  hidi.id, " + //7
				"  last.indicadorFaturamentoSituacao, " +//8
				"  lest.indicadorFaturamentoSituacao, " +//9
				"  imov.indicadorImovelAreaComum " +//10
				"from " + 
				"  Imovel imov " + // imovel
				"  left join imov.ligacaoAguaSituacao as last " + // ligação de água situação
				"  left join imov.ligacaoEsgotoSituacao as lest " + // ligação de esgoto situação
				"  left join imov.imovelCondominio as imovelCondominio " + // imovel condominio
				"  left join imov.ligacaoAgua lagu " + // ligação de água
			    "  left join lagu.hidrometroInstalacaoHistorico hidi " + // hidrometro
			    "  left join hidi.rateioTipo rttp " + // rateio tipo
			    "where " +
			    "  imovelCondominio.id = :idImovelCondominio" ;
			
			retorno = session.createQuery(consulta).setInteger(
					"idImovelCondominio", idImovelCondominio).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * Método que retorna o consumo de ligacao da agua ou esgoto (tipo passado)
	 * de um imovel em um determinado anoMes do faturamento. Método utilizado
	 * pra saber a ligacao de
	 * 
	 * 2.2.2.2 e 2.2.3.2 do [UC0103] Efetuar Rateio de Consumo
	 * 
	 * @author Administrador
	 * @date 07/04/2006
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @param idLigacaoTipo
	 *            podendo ser agua ou esgoto
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object obterConsumoLigacaoAguaOuEsgotoDoImovel(Integer idImovel,
			Integer anoMes, Integer idLigacaoTipo)
			throws ErroRepositorioException {

		Object retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "		consumoHistorico.id, consumoHistorico.numeroConsumoFaturadoMes, pocoTipo.id "
					+ "from "
					+ "		gcom.micromedicao.consumo.ConsumoHistorico consumoHistorico "
					+ "		inner join consumoHistorico.ligacaoTipo ligacaoTipo "
					+ "		inner join consumoHistorico.imovel imovel"
					+ "     left join imovel.pocoTipo pocoTipo " + "where "
					+ " 	imovel.id = " + idImovel + " 	and ligacaoTipo.id = "
					+ idLigacaoTipo
					+ " 	and consumoHistorico.referenciaFaturamento = "
					+ anoMes + " ";

			retorno = session.createQuery(consulta).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * Método que retorna o consumo de ligacao da agua ou esgoto (tipo passado)
	 * de um imovel em um determinado anoMes do faturamento. Método utilizado
	 * pra saber a ligacao de
	 * 
	 * 2.2.2.2 e 2.2.3.2 do [UC0103] Efetuar Rateio de Consumo
	 * 
	 * @author Sávio Luiz
	 * @date 07/04/2006
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @param idLigacaoTipo
	 *            podendo ser agua ou esgoto
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer obterConsumoLigacaoAguaOuEsgotoDoImovelPeloTipoLogacao(
			Integer idImovel, Integer anoMes, Integer idLigacaoTipo)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "		consumoHistorico.numeroConsumoFaturadoMes "
					+ "from "
					+ "		gcom.micromedicao.consumo.ConsumoHistorico consumoHistorico "
					+ "		inner join consumoHistorico.ligacaoTipo ligacaoTipo "
					+ "		inner join consumoHistorico.imovel imovel" + " "
					+ "where " + " 	imovel.id = " + idImovel
					+ " 	and ligacaoTipo.id = " + idLigacaoTipo
					+ " 	and consumoHistorico.referenciaFaturamento = "
					+ anoMes + " ";

			Object pesquisa = session.createQuery(consulta).setMaxResults(1)
					.uniqueResult();

			if (pesquisa != null) {
				retorno = (Integer) pesquisa;
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * 
	 * Método que retorna o consumo de ligacao da agua ou esgoto (tipo passado)
	 * de um imovel em um determinado anoMes do faturamento. Método utilizado
	 * pra saber a ligacao de
	 * 
	 * 2.2.2.2 e 2.2.3.2 do [UC0103] Efetuar Rateio de Consumo
	 * 
	 * @author Sávio Luiz
	 * @date 07/04/2006
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @param idLigacaoTipo
	 *            podendo ser agua ou esgoto
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterConsumoAnteriorAnormalidadeDoImovel(Integer idImovel,
			Integer anoMes, Integer idLigacaoTipo)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "		consumoHistorico.numeroConsumoFaturadoMes,consAnormalidade.descricaoAbreviada,consAnormalidade.id "
					+ "from "
					+ "		gcom.micromedicao.consumo.ConsumoHistorico consumoHistorico "
					+ "     left join consumoHistorico.consumoAnormalidade consAnormalidade "
					+ "		left join consumoHistorico.ligacaoTipo ligacaoTipo "
					+ "		left join consumoHistorico.imovel imovel" + " "
					+ "where " + " 	imovel.id = " + idImovel
					+ " 	and ligacaoTipo.id = " + idLigacaoTipo
					+ " 	and consumoHistorico.referenciaFaturamento = "
					+ anoMes + " ";

			retorno = (Object[]) session.createQuery(consulta).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * Método que retorna um consumoHistorico do imovel com o anoMes passado
	 * 
	 * @author thiago toscano
	 * @date 18/04/2006
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ConsumoHistorico obterConsumoHistoricoImovel(Integer idImovel,
			Integer anoMes, Integer idLigacaoTipo)
			throws ErroRepositorioException {

		ConsumoHistorico retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select consumoHistorico from "
					+ "ConsumoHistorico consumoHistorico "
					+ "inner join consumoHistorico.imovel imovel "
					+ "inner join consumoHistorico.ligacaoTipo ligacaoTipo "
					+ "where ligacaoTipo.id = :idLigacaoTipo "
					+ "and imovel.id = :idImovel "
					+ "and consumoHistorico.referenciaFaturamento = :anoMes ";

			retorno = (ConsumoHistorico) session.createQuery(consulta)
					.setInteger("idImovel", idImovel).setInteger("anoMes",
							anoMes).setInteger("idLigacaoTipo", idLigacaoTipo)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Método que retorna o id da leitura anormalidade do faturamento no caso do
	 * tipo de ligação ser agua
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0003] Obter Dados do Consumo e Medicao Anterior
	 * 
	 * @author Sávio Luiz
	 * @date 17/05/2006
	 * 
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLeituraAnormalidadeTipoAgua(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException {
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select laf.id " + "from MedicaoHistorico mh "
					+ "left join mh.leituraAnormalidadeFaturamento laf "
					+ "left join mh.ligacaoAgua la "
					+ "where la.id = :idImovel and "
					+ "mh.medicaoTipo.id = :idMedicaoTipo and "
					+ "mh.anoMesReferencia = :anoMesReferencia";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel.intValue()).setInteger(
					"idMedicaoTipo", MedicaoTipo.LIGACAO_AGUA.intValue())
					.setInteger("anoMesReferencia", anoMes.intValue())
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Método que retorna o id da leitura anormalidade do faturamento no caso do
	 * tipo de ligação ser esgoto
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0003] Obter Dados do Consumo e Medicao Anterior
	 * 
	 * @author Sávio Luiz
	 * @date 17/05/2006
	 * 
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLeituraAnormalidadeTipoEsgoto(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException {
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select laf.id " + "from MedicaoHistorico mh "
					+ "left join mh.leituraAnormalidadeFaturamento laf "
					+ "left join mh.imovel imov "
					+ "where imov.id = :idImovel and "
					+ "mh.medicaoTipo.id = :idMedicaoTipo and "
					+ "mh.anoMesReferencia = :anoMesReferencia";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel.intValue()).setInteger(
					"idMedicaoTipo", MedicaoTipo.LIGACAO_AGUA.intValue())
					.setInteger("anoMesReferencia", anoMes.intValue())
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Método que retorna um arrey de Object com informações do histórico de
	 * medição com tipo de medição agua
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0004] Obter Dados de medição da conta
	 * 
	 * @author Sávio Luiz
	 * @date 17/05/2006
	 * 
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	/**TODO Cosanpa:
	 * Inclusao de uma coluna no retorno, para atender melhoria da segunda via da conta*/
	public Object[] obterDadosMedicaoContaTipoAgua(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException {
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select md.leituraAnteriorFaturamento,"// 0
					+ 
					"md.leituraAtualFaturamento, "// 1
					+ 
					"md.dataLeituraAtualFaturamento, "// 2
					+ 
					"md.dataLeituraAnteriorFaturamento, "// 3
					+ 
					"leituraSituacaoAtual.id, "// 4
					+ 
					"leituraAnormalidadeFaturamento.id, "// 5
					+ 
					"md.numeroConsumoMes, "// 6
					+
					"leituraAnormalidadeFaturamento.descricaoAbreviada, "// 7
					+
					"leiturista.id "//8
					
					+ "from MedicaoHistorico md	"
					+ "left join md.leituraSituacaoAtual leituraSituacaoAtual "
					+ "left join md.leituraAnormalidadeFaturamento leituraAnormalidadeFaturamento "
					+ "left join md.leiturista leiturista "
					+ "where md.ligacaoAgua.id = :idImovel and "
					+ "md.medicaoTipo.id = :idMedicaoTipo and "
					+ "md.anoMesReferencia = :anoMes ";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idImovel", idImovel.intValue()).setInteger(
					"idMedicaoTipo", MedicaoTipo.LIGACAO_AGUA.intValue())
					.setInteger("anoMes", anoMes.intValue()).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Método que retorna um arrey de Object com informações do histórico de
	 * medição com tipo de medição poco
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0004] Obter Dados de medição da conta
	 * 
	 * @author Sávio Luiz
	 * @date 17/05/2006
	 * 
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */

	/**TODO Cosanpa:
	 * Inclusao de uma coluna no retorno, para atender melhoria da segunda via da conta*/
	public Object[] obterDadosMedicaoContaTipoPoco(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException {
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select md.leituraAnteriorFaturamento,"// 0
					+ 
					"md.leituraAtualFaturamento, "// 1
					+ 
					"md.dataLeituraAtualFaturamento, "// 2
					+ 
					"md.dataLeituraAnteriorFaturamento, "// 3
					+ 
					"leituraSituacaoAtual.id, "// 4
					+ 
					"leituraAnormalidadeFaturamento.id, "// 5
					+
					"md.numeroConsumoMes, " // 6
					+
					"leituraAnormalidadeFaturamento.descricaoAbreviada, "// 7
					+
					"leiturista.id "// 8				
					+ "from MedicaoHistorico md	"
					+ "left join md.leituraSituacaoAtual leituraSituacaoAtual "
					+ "left join md.leituraAnormalidadeFaturamento leituraAnormalidadeFaturamento "
					+ "left join md.leiturista leiturista "
					+ "where md.imovel.id = :idImovel and "
					+ "md.medicaoTipo.id = :idMedicaoTipo and "
					+ "md.anoMesReferencia = :anoMes ";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idImovel", idImovel.intValue()).setInteger(
					"idMedicaoTipo", MedicaoTipo.POCO.intValue()).setInteger(
					"anoMes", anoMes.intValue()).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Método que retorna um arrey de Object com informações do histórico de
	 * consumo com tipo de medição poco
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0006] Obter Dados de consumo da conta
	 * 
	 * @author Sávio Luiz
	 * @date 19/05/2006
	 * 
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @param idTipoLigacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterDadosConsumoConta(Integer idImovel,
			int anoMesReferencia, Integer idTipoLigacao)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select conTipo.descricaoAbreviada, "// 0
					+ "conTipo.descricao, "// 1
					+ "ch.consumoMedio, "// 2
					+ "consAnormalidade.descricaoAbreviada, "// 3
					+ "consAnormalidade.descricao, "// 4
					+ "ch.consumoRateio, " // 5
					+ "consAnormalidade.mensagemConta "// 6
					+ "from ConsumoHistorico ch "
					+ "inner join ch.imovel imovel "
					+ "inner join ch.ligacaoTipo lt "
					+ "left join ch.consumoTipo conTipo "
					+ "left join ch.consumoAnormalidade consAnormalidade "
					+ "where imovel.id = :idImovel and ch.referenciaFaturamento = :anoMes  and "
					+ "lt.id = :idTipoLigacao";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idImovel", idImovel)
					.setInteger("anoMes", anoMesReferencia).setInteger(
							"idTipoLigacao", idTipoLigacao.intValue())
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Método que retorna o maior código de Rota de um Setor Comercial
	 * 
	 * @author Vivianne Sousa
	 * @date 12/07/2006
	 * 
	 * @param idSetorComercial
	 * @return
	 * @throws ControladorException
	 */

	public Short pesquisarMaximoCodigoRota(Integer idSetorComercial)
			throws ErroRepositorioException {
		Short retorno = 0;
		Object maxCodigoRota = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT max(r.codigo) " + "FROM Rota r "
					+ "INNER JOIN r.setorComercial setorC "
					+ "WHERE setorC.id = :idSetorComercial ";

			maxCodigoRota = session.createQuery(consulta).setInteger(
					"idSetorComercial", idSetorComercial).setMaxResults(1)
					.uniqueResult();

			if (maxCodigoRota != null) {
				retorno = (Short) maxCodigoRota;
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Método que remove RotaAcaoCriterio
	 * 
	 * @author Vivianne Sousa
	 * @date 12/07/2006
	 * 
	 * @param id
	 * @param operacaoEfetuada
	 * @param acaoUsuarioHelper
	 * @return
	 * @throws ControladorException
	 */
	public void removerRotaAcaoCriterio(int id,
			OperacaoEfetuada operacaoEfetuada,
			Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ErroRepositorioException {

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {

			// Remove RotaAcaoCriterio
			Iterator iteratorRotaAcaoCriterio = session.createQuery(
					"from " + RotaAcaoCriterio.class.getName()
							+ " where rota_id = :id").setInteger("id", id)
					.iterate();

			if (!iteratorRotaAcaoCriterio.hasNext()) {
				throw new RemocaoRegistroNaoExistenteException();

			}

			while (iteratorRotaAcaoCriterio.hasNext()) {
				Object obj = iteratorRotaAcaoCriterio.next();
				if (obj instanceof ObjetoTransacao && operacaoEfetuada != null) {
					ObjetoTransacao objetoTransacao = (ObjetoTransacao) obj;
					objetoTransacao.setOperacaoEfetuada(operacaoEfetuada);
					Iterator it = acaoUsuarioHelper.iterator();
					while (it.hasNext()) {
						UsuarioAcaoUsuarioHelper helper = (UsuarioAcaoUsuarioHelper) it
								.next();
						objetoTransacao.adicionarUsuario(helper.getUsuario(),
								helper.getUsuarioAcao());
					}
				}
				iteratorRotaAcaoCriterio.remove();
			}

			session.flush();
			// restrições no sistema
		} catch (JDBCException e) {
			// e.printStackTrace();
			// levanta a exceção para a próxima camada
			throw new RemocaoInvalidaException(e);
			// erro no hibernate
		} catch (CallbackException e) {
			throw new ErroRepositorioException(e, e.getMessage());

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * 
	 * Método utilizado para pesquisar os consumo historicos a serem
	 * substituidos pelo caso de uso [UC0106] Substituir Consumos Anteriores
	 * 
	 */
	public Collection pesquisaConsumoHistoricoSubstituirConsumo(
			Integer idImovel, SistemaParametro sistemaParametro,
			Integer anoMesFaturamento) throws ErroRepositorioException {

		Collection retorno = new ArrayList();

		Session session = HibernateUtil.getSession();

		try {

			// anoMesFaturamento Final que será o anoMesFaturamento - 1
			int anoMesFinal = Util.subtraiAteSeisMesesAnoMesReferencia(
					anoMesFaturamento, new Integer(1));
			// anoMesFaturamento Inicial que será o anoMesFaturamento -
			// sistemaParametro.PARM_NNMESESCALCULOMEDIACONSUMO
			int anoMesInicial = Util.subtraiAteSeisMesesAnoMesReferencia(
					anoMesFaturamento, new Integer(sistemaParametro
							.getMesesMediaConsumo()));

			for (int i = anoMesInicial; i < (anoMesFinal + 1); i++) {
				String sql = "select "
						+ " cha.lgti_id as ligacaoTipoAgua,"
						+ " che.lgti_id as ligacaoTipoEsgoto,"
						+ " cha.cshi_nnconsumocalculomedia as consumoMesAgua,"
						+ " che.cshi_nnconsumocalculomedia as consumoMesEsgoto,"
						+ " cta.cstp_dsabreviadaconsumotipo as descricaoAbreviadaTipoAgua,"
						+ " cte.cstp_dsabreviadaconsumotipo as descricaoAbreviadaTipoEsgoto,"
						+ " caa.csan_dsabrvconsanormalidade as descicaoabreviadaanormalidadea,"
						+ " cae.csan_dsabrvconsanormalidade as descicaoabreviadaanormalidadee,"
						+ " cha.cshi_id as idConsumoAgua,"
						+ " che.cshi_id as idConsumoEsgoto,"
						+ " cha.cshi_amfaturamento as anoMesReferenciaAgua,"
						+ " che.cshi_amfaturamento as anoMesReferenciaEsgoto,"
						+ " fatGrupo.ftgr_amreferencia as anoMesGrupoFaturamento"
						+ " from cadastro.imovel imovel"
						+ " inner join micromedicao.consumo_historico cha on cha.imov_id = imovel.imov_id and (cha.cshi_amfaturamento ="
						+ i
						+ " and cha.lgti_id = "
						+ LigacaoTipo.LIGACAO_AGUA
						+ ")"
						+ " left outer join micromedicao.consumo_historico che on che.imov_id = imovel.imov_id and (che.cshi_amfaturamento = "
						+ i
						+ " and che.lgti_id = "
						+ LigacaoTipo.LIGACAO_ESGOTO
						+ ")"
						+ " left outer join micromedicao.consumo_tipo cta on cha.cstp_id = cta.cstp_id"
						+ " left outer join micromedicao.consumo_tipo cte on che.cstp_id = cte.cstp_id"
						+ " left outer join micromedicao.consumo_anormalidade caa on cha.csan_id = caa.csan_id"
						+ " left outer join micromedicao.consumo_anormalidade cae on che.csan_id = cae.csan_id"
						+ " inner join cadastro.quadra qd on qd.qdra_id = imovel.qdra_id"
						+ " inner join micromedicao.rota rot on rot.rota_id = qd.rota_id"
						+ " inner join faturamento.faturamento_grupo fatGrupo on fatGrupo.ftgr_id = rot.ftgr_id"
						+ " where imovel.imov_id = :idImovel";

				// Realiza pesquisa em consumo historico

				Object[] objetoRetorno = (Object[]) session.createSQLQuery(sql)
						.addScalar("ligacaoTipoAgua", Hibernate.INTEGER)
						.addScalar("ligacaoTipoEsgoto", Hibernate.INTEGER)
						.addScalar("consumoMesAgua", Hibernate.INTEGER)
						.addScalar("consumoMesEsgoto", Hibernate.INTEGER)
						.addScalar("descricaoAbreviadaTipoAgua",
								Hibernate.STRING).addScalar(
								"descricaoAbreviadaTipoEsgoto",
								Hibernate.STRING).addScalar(
								"descicaoabreviadaanormalidadea",
								Hibernate.STRING).addScalar(
								"descicaoabreviadaanormalidadee",
								Hibernate.STRING).addScalar("idConsumoAgua",
								Hibernate.INTEGER).addScalar("idConsumoEsgoto",
								Hibernate.INTEGER).addScalar(
								"anoMesReferenciaAgua", Hibernate.INTEGER)
						.addScalar("anoMesReferenciaEsgoto", Hibernate.INTEGER)
						.addScalar("anoMesGrupoFaturamento", Hibernate.INTEGER)
						.setInteger("idImovel", idImovel.intValue())
						.uniqueResult();
				if (objetoRetorno != null) {
					retorno.add(objetoRetorno);
				}
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Atualiza o valor de cshi_nnconsumoFaturadomes consumo historico [UC0106] -
	 * Substituir Consumos Anteriores
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarConsumosAnteriores(ConsumoHistorico consumoHistorico)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {
			String sql = "update gcom.micromedicao.consumo.ConsumoHistorico "
					+ " set numeroConsumoCalculoMedia = "
					+ consumoHistorico.getNumeroConsumoCalculoMedia()
					+ ", ultimaAlteracao = :data ,"
					+ " indicadorAlteracaoUltimosConsumos = 1" + " where id = "
					+ consumoHistorico.getId();

			session.createQuery(sql).setTimestamp("data", new Date())
					.executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Atualiza o valor de cshi_nnconsumomedio, cshi_nnconsumomediohidrometro
	 * consumo historico [UC0106] - Substituir Consumos Anteriores
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarConsumosMedio(Integer idImovel , Integer anoMesConsumoHistorico, 
			int consumoMedioHidrometroAgua, int consumoMedioHidrometroEsgoto)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {
			
			String sql2 = "";
			if (consumoMedioHidrometroAgua != 0) {
				sql2 = "update gcom.micromedicao.medicao.MedicaoHistorico "
						+ " set consumoMedioHidrometro = "
						+ consumoMedioHidrometroAgua
						+ ", ultimaAlteracao = :data" + " where ligacaoAgua = "
						+ idImovel + " and anoMesReferencia = "
						+ anoMesConsumoHistorico;
				
				session.createQuery(sql2).setTimestamp("data", new Date())
				.executeUpdate();
				
				String sql = "update gcom.micromedicao.consumo.ConsumoHistorico "
					+ " set consumoMedio = " + consumoMedioHidrometroAgua
					+ ", ultimaAlteracao = :data" + " where  imovel = "
					+ idImovel + " and referenciaFaturamento = "
					+ anoMesConsumoHistorico +" and ligacaoTipo = "+ LigacaoTipo.LIGACAO_AGUA;

				session.createQuery(sql).setTimestamp("data", new Date())
					.executeUpdate();
			} 
			
			if(consumoMedioHidrometroEsgoto != 0) {
				sql2 = "update gcom.micromedicao.medicao.MedicaoHistorico "
						+ " set consumoMedioHidrometro = "
						+ consumoMedioHidrometroEsgoto
						+ ", ultimaAlteracao = :data" + " where imovel = "
						+ idImovel + " and anoMesReferencia = "
						+ anoMesConsumoHistorico;
				session.createQuery(sql2).setTimestamp("data", new Date())
				.executeUpdate();
				
				String sql = "update gcom.micromedicao.consumo.ConsumoHistorico "
					+ " set consumoMedio = " + consumoMedioHidrometroAgua
					+ ", ultimaAlteracao = :data" + " where  imovel = "
					+ idImovel + " and referenciaFaturamento = "
					+ anoMesConsumoHistorico +" and ligacaoTipo = "+ LigacaoTipo.LIGACAO_ESGOTO;

				session.createQuery(sql).setTimestamp("data", new Date())
					.executeUpdate();
			}

			
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Atualizar Analise excecoes consumo resumido
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarLeituraConsumoResumido(Integer idImovel,
			String mesAno, String dataLeituraAnteriorFaturamento,
			String leituraAnteriorFaturamento,
			String dataLeituraAtualInformada, String leituraAtualInformada,
			String consumo, boolean ligacaoAgua, Integer idAnormalidade,
			Integer idleituraSituacaoAtual, Usuario usuarioLogado,
			boolean alterouAnormalidade,
			MotivoInterferenciaTipo motivoInterferenciaTipo)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			String sql = "update gcom.micromedicao.consumo.ConsumoHistorico as consumoHistorico"
					+ " set ultimaAlteracao = :data"
					+ " where consumoHistorico.imovel.id = "
					+ idImovel
					+ " and consumoHistorico.referenciaFaturamento ="
					+ Util.formatarMesAnoComBarraParaAnoMes(mesAno);

			if (leituraAtualInformada.equals("")) {
				leituraAtualInformada = null;
			}

			if (consumo.equals("")) {
				consumo = null;
			}

			String sqlMedicao = "update gcom.micromedicao.medicao.MedicaoHistorico as medicaoHistorico"
					+ " set medicaoHistorico.dataLeituraAnteriorFaturamento = :dataLeituraAnteriorFaturamento"
					+ ", medicaoHistorico.leituraAnteriorFaturamento = "
					+ leituraAnteriorFaturamento
					+ ", medicaoHistorico.dataLeituraAtualInformada = :dataLeituraAtualInformada"
					+ ", medicaoHistorico.leituraAtualInformada = "
					+ leituraAtualInformada
					+ ", medicaoHistorico.leituraAnormalidadeFaturamento = "
					+ idAnormalidade
					+ ", medicaoHistorico.leituraAnormalidadeInformada = "
					+ idAnormalidade
					+ ", medicaoHistorico.ultimaAlteracao = :data "
					+ ", numeroConsumoInformado = "
					+ consumo
					+ ", indicadorAnalisado = "
					+ MedicaoHistorico.INDICADOR_ANALISADO_ATUALIZADO
					+ ", usuarioAlteracao.id = " + usuarioLogado.getId();

			if (idleituraSituacaoAtual != null
					&& idleituraSituacaoAtual.intValue() > 0) {
				sqlMedicao = sqlMedicao
						+ ", medicaoHistorico.leituraSituacaoAtual.id ="
						+ idleituraSituacaoAtual;
			}

			if (motivoInterferenciaTipo != null) {
				sqlMedicao = sqlMedicao
						+ ", medicaoHistorico.motivoInterferenciaTipo.id ="
						+ motivoInterferenciaTipo.getId();
			}
			
			if(leituraAtualInformada != null){
				sqlMedicao = sqlMedicao 
					+ ", medicaoHistorico.leituraAtualFaturamento = "
					+ leituraAtualInformada;
			}else{
				sqlMedicao = sqlMedicao 
				+ ", medicaoHistorico.leituraAtualFaturamento = 0 ";
			}


			sqlMedicao = sqlMedicao
					+ " where medicaoHistorico.anoMesReferencia ="
					+ Util.formatarMesAnoComBarraParaAnoMes(mesAno);

			if (ligacaoAgua) {
				sqlMedicao = sqlMedicao
						+ " and medicaoHistorico.ligacaoAgua.id =" + idImovel;
			} else {
				sqlMedicao = sqlMedicao + " and medicaoHistorico.imovel.id ="
						+ idImovel;
			}

			session.createQuery(sql).setTimestamp("data", new Date())
					.executeUpdate();

			session
					.createQuery(sqlMedicao)
					.setTimestamp(
							"dataLeituraAnteriorFaturamento",
							Util
									.converteStringParaDate(dataLeituraAnteriorFaturamento))
					.setTimestamp(
							"dataLeituraAtualInformada",
							Util
									.converteStringParaDate(dataLeituraAtualInformada))
					.setTimestamp("data", new Date()).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * Atualizar Analise excecoes consumo resumido
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorAnalisadoMedicaoHistorico(
			Integer idMedicaoHistorico, Usuario usuarioLogado)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {
			String sql = "update gcom.micromedicao.medicao.MedicaoHistorico as medicaoHistorico"
					+ " set indicadorAnalisado = "
					+ MedicaoHistorico.INDICADOR_ANALISADO_SIM
					+ ", usuarioAlteracao.id = "
					+ usuarioLogado.getId()
					+ " where medicaoHistorico.id = " + idMedicaoHistorico;

			session.createQuery(sql).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * Registrar leituras e anormalidades Autor:Sávio Luiz
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarMedicaoHistorico(Collection idsImovel,
			Integer anoMes, Integer idMedicaoTipo)
			throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "";
			if (idMedicaoTipo.equals(MedicaoTipo.POCO)) {
				consulta = "Select "
						+ " medicaohis0_.mdhi_id as idMedicaoHistorico, "
						+ " imovel2_.imov_id as idImovel "
						+ " from  micromedicao.medicao_historico medicaohis0_ "
						+ " inner join  cadastro.imovel imovel2_ on medicaohis0_.imov_id=imovel2_.imov_id "
						+ " where "
						+ " medicaohis0_.mdhi_amleitura = :anoMesReferencia  and "
						+ " medicaohis0_.medt_id = :idMedicaoTipo and "
						+ " imovel2_.imov_id in (:idsImovel)";

			}
			if (idMedicaoTipo.equals(MedicaoTipo.LIGACAO_AGUA)) {
				consulta = " Select "
						+ " medicaohis0_.mdhi_id as idMedicaoHistorico, "
						+ " ligacaoagu1_.lagu_id as idImovel "
						+ " from  micromedicao.medicao_historico medicaohis0_ "
						+ " inner join  atendimentopublico.ligacao_agua ligacaoagu1_   on medicaohis0_.lagu_id=ligacaoagu1_.lagu_id "
						+ " where "
						+ " medicaohis0_.mdhi_amleitura = :anoMesReferencia  and "
						+ " medicaohis0_.medt_id= :idMedicaoTipo and "
						+ " ligacaoagu1_.lagu_id in (:idsImovel)";

			}
			retorno = session.createSQLQuery(consulta).addScalar(
					"idMedicaoHistorico", Hibernate.INTEGER).addScalar(
					"idImovel", Hibernate.INTEGER).setParameterList(
					"idsImovel", idsImovel).setInteger("idMedicaoTipo",
					idMedicaoTipo).setInteger("anoMesReferencia",
					anoMes.intValue()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0082] - Registrar Leituras e Anormalidades Autor: Sávio Luiz Data:
	 * 04/01/2006
	 */

	public Collection pesquisarMedicaoHistoricoAnterior(
			Collection colecaoIdsImoveis, Integer anoMes, Integer idMedicaoTipo)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "";
			if (idMedicaoTipo.equals(MedicaoTipo.POCO)) {
				consulta = "Select "
						+ " medicaohis0_.mdhi_dtleituraatualfaturamento as dataLeituraAtualFat, "
						+ // 0
						" medicaohis0_.mdhi_nnleituraatualfaturamento as leituraAtualFat, "
						+ // 1
						" medicaohis0_.mdhi_nnleituraatualinformada as leituraAtualInf, "
						+ // 2
						" medicaohis0_.ltst_idleiturasituacaoatual as idLeituraSitAtual, "
						+ // 3
						" hidrometro3_.hidi_id as idHidrometroInstHist, "
						+ // 4
						" hidrometro3_.hidi_dtinstalacaohidrometro as dataInstalacao, "
						+ // 5
						" medicaohis0_.imov_id as idImovel "// 6
						+ " from  micromedicao.medicao_historico medicaohis0_ "
						+ " inner join  cadastro.imovel imovel2_ on medicaohis0_.imov_id=imovel2_.imov_id "
						+ " left outer join  micromedicao.hidrometro_inst_hist hidrometro3_  on imovel2_.hidi_id=hidrometro3_.hidi_id "
						+ " where "
						+ " medicaohis0_.mdhi_amleitura = :anoMesReferencia  and "
						+ " medicaohis0_.medt_id = :idMedicaoTipo and "
						+ " imovel2_.imov_id in (:idsImovel)";

			}
			if (idMedicaoTipo.equals(MedicaoTipo.LIGACAO_AGUA)) {
				consulta = " Select "
						+ " medicaohis0_.mdhi_dtleituraatualfaturamento as dataLeituraAtualFat, "
						+ // 0
						" medicaohis0_.mdhi_nnleituraatualfaturamento as leituraAtualFat, "
						+ // 1
						" medicaohis0_.mdhi_nnleituraatualinformada as leituraAtualInf, "
						+ // 2
						" medicaohis0_.ltst_idleiturasituacaoatual as idLeituraSitAtual, "
						+ // 3
						" hidrometro3_.hidi_id as idHidrometroInstHist, "
						+ // 4
						" hidrometro3_.hidi_dtinstalacaohidrometro as dataInstalacao, "
						+ // 5
						" medicaohis0_.lagu_id as idImovel "// 6
						+ " from  micromedicao.medicao_historico medicaohis0_ "
						+ " inner join  atendimentopublico.ligacao_agua ligacaoagu1_   on medicaohis0_.lagu_id=ligacaoagu1_.lagu_id "
						+ " left outer join  micromedicao.hidrometro_inst_hist hidrometro3_  on ligacaoagu1_.hidi_id=hidrometro3_.hidi_id "
						+ " where "
						+ " medicaohis0_.mdhi_amleitura = :anoMesReferencia  and "
						+ " medicaohis0_.medt_id= :idMedicaoTipo and "
						+ " ligacaoagu1_.lagu_id in (:idsImovel)";

			}

			retorno = session.createSQLQuery(consulta).addScalar(
					"dataLeituraAtualFat", Hibernate.DATE).addScalar(
					"leituraAtualFat", Hibernate.INTEGER).addScalar(
					"leituraAtualInf", Hibernate.INTEGER).addScalar(
					"idLeituraSitAtual", Hibernate.INTEGER).addScalar(
					"idHidrometroInstHist", Hibernate.INTEGER).addScalar(
					"dataInstalacao", Hibernate.DATE).addScalar("idImovel",
					Hibernate.INTEGER).setParameterList("idsImovel",
					colecaoIdsImoveis).setInteger("idMedicaoTipo",
					idMedicaoTipo).setInteger("anoMesReferencia",
					anoMes.intValue()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public void inseriMedicaoHistorico(Collection colecaoMedicaoHistorico)
			throws ErroRepositorioException {

		StatelessSession session = HibernateUtil.getStatelessSession();

		Iterator iteratorColecaoMedicaoHistorico = colecaoMedicaoHistorico
				.iterator();

		try {
			while (iteratorColecaoMedicaoHistorico.hasNext()) {

				MedicaoHistorico medicaoHistorico = (MedicaoHistorico) iteratorColecaoMedicaoHistorico
						.next();

				session.insert(medicaoHistorico);

			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	// ----------Savio
	public void atualizarMedicaoHistorico(Collection medicaoHistoricoAtualizar)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			Iterator iteMedicaoHistorico = medicaoHistoricoAtualizar.iterator();

			while (iteMedicaoHistorico.hasNext()) {
				MedicaoHistorico medicaoHistorico = (MedicaoHistorico) iteMedicaoHistorico
						.next();

				String atualizarMedicaoHistorico;

				atualizarMedicaoHistorico = "update gcom.micromedicao.medicao.MedicaoHistorico "
						+ "set mdhi_tmultimaalteracao = :ultimaAlteracao ";

				if (medicaoHistorico.getFuncionario() != null
						&& medicaoHistorico.getFuncionario().getId() != null) {

					atualizarMedicaoHistorico = atualizarMedicaoHistorico
							+ ",func_id = "
							+ medicaoHistorico.getFuncionario().getId();
				}

				if (medicaoHistorico.getLeituraAnormalidadeInformada() != null
						&& medicaoHistorico.getLeituraAnormalidadeInformada()
								.getId() != null) {

					atualizarMedicaoHistorico = atualizarMedicaoHistorico
							+ ",ltan_idleitanorminformada = "
							+ medicaoHistorico
									.getLeituraAnormalidadeInformada().getId();
				}

				if (medicaoHistorico.getDataLeituraAtualInformada() != null
						&& !medicaoHistorico.getDataLeituraAtualInformada()
								.equals("")) {

					atualizarMedicaoHistorico = atualizarMedicaoHistorico
							+ ",mdhi_dtleituraatualinformada = :dataLeituraAtualInformada ";
				}

				if (medicaoHistorico.getLeituraAtualInformada() != null
						&& !medicaoHistorico.getLeituraAtualInformada().equals(
								"")) {

					atualizarMedicaoHistorico = atualizarMedicaoHistorico
							+ ",mdhi_nnleituraatualinformada = "
							+ medicaoHistorico.getLeituraAtualInformada();
				}

				if (medicaoHistorico.getLeituraSituacaoAtual() != null
						&& medicaoHistorico.getLeituraSituacaoAtual().getId() != null) {

					atualizarMedicaoHistorico = atualizarMedicaoHistorico
							+ ",ltst_idleiturasituacaoatual = "
							+ medicaoHistorico.getLeituraSituacaoAtual()
									.getId();
				}

				if (medicaoHistorico.getLeituraSituacaoAnterior() != null
						&& medicaoHistorico.getLeituraSituacaoAnterior()
								.getId() != null) {

					atualizarMedicaoHistorico = atualizarMedicaoHistorico
							+ ",ltst_idleiturasituacaoanterior = "
							+ medicaoHistorico.getLeituraSituacaoAnterior()
									.getId();
				}

				atualizarMedicaoHistorico = atualizarMedicaoHistorico
						+ " where mdhi_id = :idMedicaoHistorico";

				if (medicaoHistorico.getDataLeituraAtualInformada() != null
						&& !medicaoHistorico.getDataLeituraAtualInformada()
								.equals("")) {

					session.createQuery(atualizarMedicaoHistorico).setInteger(
							"idMedicaoHistorico", medicaoHistorico.getId())
							.setTimestamp("ultimaAlteracao",
									medicaoHistorico.getUltimaAlteracao())
							.setTimestamp(
									"dataLeituraAtualInformada",
									medicaoHistorico
											.getDataLeituraAtualInformada())
							.executeUpdate();
				} else {
					session.createQuery(atualizarMedicaoHistorico).setInteger(
							"idMedicaoHistorico", medicaoHistorico.getId())
							.setTimestamp("ultimaAlteracao",
									medicaoHistorico.getUltimaAlteracao())
							.executeUpdate();
				}
			}

		} catch (Exception e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");
			// } catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			// throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * Registrar leituras e anormalidades Autor:Sávio Luiz
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarHidrometroInstalacaoHistorico(
			Collection idsImovel) throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "Select (CASE WHEN hidInsHist.lagu_id is not null THEN hidInsHist.lagu_id ELSE hidInsHist.imov_id END) as idImovel "
					+ " from micromedicao.hidrometro_inst_hist  hidInsHist "
					+ " where hidInsHist.hidi_dtretiradahidrometro is null AND "
					+ " hidInsHist.lagu_id in (:idsImovel) or hidInsHist.imov_id in (:idsImovel)";

			retorno = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).setParameterList("idsImovel", idsImovel)
					.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Método que retorna o número da leitura de retirada do hidrômetro
	 * 
	 * @author Ana Maria
	 * @date 25/09/2006
	 * 
	 * @param idLigacaoAgua
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarNumeroLeituraRetiradaLigacaoAgua(
			Integer idLigacaoAgua) throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select mdhi.leituraAtualFaturamento "
					+ "from MedicaoHistorico mdhi "
					+ "left join mdhi.ligacaoAgua lagu "
					+ "where lagu.id = :idLigacaoAgua "
					+ "order by mdhi.anoMesReferencia desc";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idLigacaoAgua", idLigacaoAgua).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Método que retorna o número da leitura de retirada do hidrômetro
	 * 
	 * @author Ana Maria
	 * @date 25/09/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarNumeroLeituraRetiradaImovel(Integer idImovel)
			throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select mdhi.leituraAtualFaturamento "
					+ "from MedicaoHistorico mdhi "
					+ "left join mdhi.imovel imov "
					+ "where imov.id = :idImovel "
					+ "order by mdhi.anoMesReferencia desc";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	// ----------Savio
	public void atualizarFaturamentoAtividadeCronograma(
			Integer idGrupoFaturamento, int amReferencia)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			String atualizar;

			atualizar = "update FaturamentoAtividadeCronograma fatAtiv set "
					+ "ftac_tmrealizacao = :dataCorrente "
					+ ",ftac_tmultimaalteracao = :dataCorrente "
					+ "where ftat_id = :idFaturamentoAtividade AND "
					+ "ftcm_id IN (select fgcm.id from FaturamentoGrupoCronogramaMensal fgcm where "
					+ "fgcm.anoMesReferencia = :amReferencia AND "
					+ "fgcm.faturamentoGrupo.id = :idGrupoFaturamento)";

			session.createQuery(atualizar).setTimestamp("dataCorrente",
					new Date()).setInteger("idGrupoFaturamento",
					idGrupoFaturamento).setInteger("idFaturamentoAtividade",
					FaturamentoAtividade.GERAR_ARQUIVO_LEITURA).setInteger(
					"amReferencia", amReferencia).executeUpdate();

		} catch (Exception e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * Verifica se existe consumo histórico para o imóvel de acordo com o tipo
	 * de ligação
	 * 
	 * @author Ana Maria
	 * @date 17/10/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarConsumoMedioImovel(Integer idImovel)
			throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select cshi.consumoMedio "
					+ "from ConsumoHistorico cshi "
					+ "where cshi.imovel.id = :idImovel and cshi.ligacaoTipo.id = :tipoLigacao "
					+ "order by cshi.referenciaFaturamento desc";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setInteger("tipoLigacao",
					LigacaoTipo.LIGACAO_AGUA).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Método que deleta o consumo histórico do imóvel
	 * 
	 * @author Leonardo Vieira
	 * @date 02/11/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */

	public void deletarConsumoHistorico(Integer idRota, int amFaturamento)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {
			String delete = "delete ConsumoHistorico "
					+ "where rota = :idRota and referenciaFaturamento = :amFaturamento";

			session.createQuery(delete).setInteger("idRota", idRota)
					.setInteger("amFaturamento", amFaturamento).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}

	public void deletarConsumoHistoricoCondominio(Integer idRota,
			int amFaturamento) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {
			String delete = "delete ConsumoHistorico "
					+ "where rota = :idRota and referenciaFaturamento = :amFaturamento and consumoImovelCondominio is not null";

			session.createQuery(delete).setInteger("idRota", idRota)
					.setInteger("amFaturamento", amFaturamento).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}

	public void deletarConsumoHistoricoRETIRAR(Integer idImovel,
			int amFaturamento) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {
			String delete = "delete ConsumoHistorico "
					+ " where imovel = :idImovel and referenciaFaturamento = :amFaturamento"
					+ " and not exists(select conta.id from Conta conta where conta.referencia = :referenciaConta and conta.imovel.id = :idImovelConta and conta.debitoCreditoSituacaoAtual.id <> :debito) ";

			session.createQuery(delete).setInteger("idImovel", idImovel)
					.setInteger("amFaturamento", amFaturamento).setInteger(
							"idImovelConta", idImovel).setInteger(
							"referenciaConta", amFaturamento).setInteger(
							"debito", DebitoCreditoSituacao.PRE_FATURADA)
					.executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}

	public void deletarConsumoHistoricoCondominioRETIRAR(Integer idImovel,
			int amFaturamento) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {
			String delete = "delete ConsumoHistorico "
					+ " where imovel = :idImovel and referenciaFaturamento = :amFaturamento and consumoImovelCondominio is not null "
					+ " and not exists(select conta.id from Conta conta where conta.referencia = :referenciaConta and conta.imovel.id = :idImovelConta and conta.debitoCreditoSituacaoAtual.id = :debito) ";

			session.createQuery(delete).setInteger("idImovel", idImovel)
					.setInteger("amFaturamento", amFaturamento).setInteger(
							"idImovelConta", idImovel).setInteger(
							"referenciaConta", amFaturamento).setInteger(
							"debito", DebitoCreditoSituacao.PRE_FATURADA)
					.executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 15/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Integer pesquisarConsumoMedio(Integer idImovel, Integer tipoMedicao)
			throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select cshi.consumoMedio "
					+ "from ConsumoHistorico cshi "
					+ "where cshi.imovel.id = :idImovel and cshi.ligacaoTipo.id = :tipoLigacao "
					+ "order by cshi.referenciaFaturamento desc";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel)
					.setInteger("tipoLigacao", tipoMedicao).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 15/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Collection pesquisarConsumoFaturadoQuantidadeMeses(Integer idImovel,
			Integer tipoMedicao, short qtdMeses)
			throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select cshi.numeroConsumoFaturadoMes, cshi.referenciaFaturamento "
					+ "from ConsumoHistorico cshi "
					+ "where cshi.imovel.id = :idImovel and cshi.ligacaoTipo.id = :tipoLigacao "
					+ "order by cshi.referenciaFaturamento desc ";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idImovel", idImovel)
					.setInteger("tipoLigacao", tipoMedicao).setMaxResults(
							qtdMeses).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Raphael Rossiter
	 * @date 19/01/2007
	 * 
	 * @param Integer
	 *            idImovel, Integer tipoMedicao
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarUltimoAnoMesConsumoFaturado(Integer idImovel,
			Integer tipoMedicao) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select cshi.referenciaFaturamento "
					+ "from ConsumoHistorico cshi "
					+ "where cshi.imovel.id = :idImovel and cshi.ligacaoTipo.id = :tipoLigacao "
					+ "order by cshi.referenciaFaturamento desc ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel)
					.setInteger("tipoLigacao", tipoMedicao).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Atualizar Hidrômetro
	 * 
	 * Pesquisa o imóvel no qual o hidrômetro está instalado
	 * 
	 * @author Rafael Corrêa
	 * @date 23/11/2006
	 * 
	 * @param idHidrometro
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarImovelHidrometroInstalado(Integer idHidrometro)
			throws ErroRepositorioException {
		Object[] retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT imovel.id, lagu.id "
					+ "FROM HidrometroInstalacaoHistorico hidInsHis "
					+ "INNER JOIN hidInsHis.hidrometro hidrometro "
					+ "LEFT JOIN hidInsHis.imovel imovel "
					+ "LEFT JOIN hidInsHis.ligacaoAgua lagu "
					+ "WHERE hidrometro.id =:idHidrometro and "
					+ "hidInsHis.dataRetirada is null";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idHidrometro", idHidrometro).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0498] - Efetuar Ligação de Água com Instalaação de Hidrômetro
	 * 
	 * Pesquisa o id do hidrômetro e a sua situação pelo número
	 * 
	 * @author Rafael Corrêa
	 * @date 29/11/2006
	 * 
	 * @param numeroHidrometro
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarHidrometroPeloNumero(String numeroHidrometro)
			throws ErroRepositorioException {
		Object[] retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT hidr.id, " // 0
					+ "hidr.numero, " // 1
					+ "hidrSit.id, " // 2
					+ "hidrSit.descricao,"// 3
					+ "hidrCap.id, " // 4
					+ "hidrCap.descricao, "// 5
					+ "hidrLocalArmazenagem.id " // 6
					+ "FROM Hidrometro hidr "
					+ "INNER JOIN hidr.hidrometroSituacao hidrSit "
					+ "INNER JOIN hidr.hidrometroCapacidade hidrCap "
					+ "INNER JOIN hidr.hidrometroLocalArmazenagem hidrLocalArmazenagem "
					+ "WHERE hidr.numero =:numeroHidrometro";

			retorno = (Object[]) session.createQuery(consulta).setString(
					"numeroHidrometro", numeroHidrometro).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 06/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Date pesquisarDataInstalacaoHidrometroAgua(Integer idImovel)
			throws ErroRepositorioException {
		Date retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select hih.dataInstalacao "
					+ "FROM LigacaoAgua ligAgua "
					+ "LEFT JOIN ligAgua.hidrometroInstalacaoHistorico hih "
					+ "where ligAgua.id = :idImovel ";

			retorno = (Date) session.createQuery(consulta).setInteger(
					"idImovel", idImovel.intValue()).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 06/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Date pesquisarDataInstalacaoHidrometroPoco(Integer idImovel)
			throws ErroRepositorioException {
		Date retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select hih.dataInstalacao " + "FROM Imovel imov "
					+ "LEFT JOIN imov.hidrometroInstalacaoHistorico hih "
					+ "where imov.id = :idImovel ";

			retorno = (Date) session.createQuery(consulta).setInteger(
					"idImovel", idImovel.intValue()).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarConsumoFaturaMes(int anoMesReferencia,
			Integer idMedicaoTipo, Integer idImovel)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select ch.numeroConsumoFaturadoMes "
					+ "from ConsumoHistorico ch "
					+ "left join ch.ligacaoTipo ligTipo "
					+ "left join ch.imovel imov "
					+ "where ligTipo.id = :idLigacaoTipo and ch.referenciaFaturamento = :anoMes AND "
					+ "imov.id = :idImovel";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idLigacaoTipo", idMedicaoTipo).setInteger("anoMes",
					anoMesReferencia).setInteger("idImovel", idImovel)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection pesqusiarLigacoesMedicaoIndividualizada(Integer idImovel,
			String anoMes) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select imovel.imov_id as idImovel, " // 0
					+ " imovel.imov_qteconomia as qtdEconomias, "// 1
					+ " medicaoHistorico.mdhi_id as idMedicaoHistorico,"// 2
					+ " medicaoHistorico.mdhi_dtleitantfatmt as dataLeituraAnterior,"// 3
					+ " medicaoHistorico.mdhi_nnleitantfatmt as leituraAnterior,"// 4
					+ " medicaoHistorico.mdhi_dtleituraatualfaturamento as dataLeituraAtualFaturamento,"// 5
					+ " medicaoHistorico.mdhi_nnleituraatualfaturamento as leituraAtualFaturamento,"// 6
					+ " medicaoHistorico.mdhi_nnconsumomedidomes as consumoMedido,"// 7
					+ " medicaoHistorico.ltan_idleitanormfatmt as idAnormalidadeLeitura,"// 8
					+ " consumoHistorico.cshi_id as idConsumoHistorico,"// 9
					+ " consumoHistorico.cshi_nnconsumomedio as consumoMedio,"// 10
					+ " consumoHistorico.cshi_nnconsumofaturadomes as consumoFaturado,"// 11
					+ " consumoHistorico.cshi_nnconsumorateio as rateio,"// 12
					+ " consumoAnormalidade.csan_dsabrvconsanormalidade as dsAbreviadaAnormalidadeConsumo,"// 13
					+ " consumoTipo.cstp_dsabreviadaconsumotipo as dsAbreviadaTipoConsumo,"// 14
					+ " medicaoTipo.medt_id as tipoMedicao,"// 15

					// 16 Consumo Esgoto
					+ " (select consumo.cshi_nnconsumofaturadomes"
					+ " from micromedicao.consumo_historico consumo"
					+ " where consumo.imov_id = imovel.imov_id"
					+ " and consumo.cshi_amfaturamento = "
					+ anoMes
					+ " and consumo.lgti_id = "
					+ LigacaoTipo.LIGACAO_ESGOTO
					+ ") as consumoEsgoto,"

					+ " medicaoHistorico.mdhi_nnconsumoinformado as consumoInformado,"// 17
					+ " consumoHistorico.cshi_nnconsimoveisvinculados as consumosVinculados"// 18

					+ " from cadastro.imovel imovel"
					+ " inner join micromedicao.consumo_historico consumoHistorico on imovel.imov_id=consumoHistorico.imov_id and consumoHistorico.cshi_amfaturamento = "
					+ anoMes
					+ " inner join micromedicao.medicao_historico medicaoHistorico on  consumoHistorico.imov_id=medicaoHistorico.lagu_id and medicaoHistorico.mdhi_amleitura = "
					+ anoMes
					+ " left outer join micromedicao.consumo_anormalidade consumoAnormalidade on consumoHistorico.csan_id=consumoAnormalidade.csan_id"
					+ " left outer join micromedicao.medicao_tipo medicaoTipo on medicaoHistorico.medt_id=medicaoTipo.medt_id"
					+ " left outer join micromedicao.consumo_tipo consumoTipo on consumoHistorico.cstp_id=consumoTipo.cstp_id"
					+ " where (medicaoHistorico.medt_id = "
					+ LigacaoTipo.LIGACAO_AGUA
					+ " and consumoHistorico.lgti_id = "
					+ LigacaoTipo.LIGACAO_AGUA + ")"
					+ " and (imovel.imov_idimovelcondominio = " + idImovel
					+ " or imovel.imov_id = " + idImovel
					+ ") order by imovel.imov_idimovelcondominio";

			retorno = (Collection) session.createSQLQuery(consulta).addScalar(
					"idImovel", Hibernate.INTEGER).addScalar("qtdEconomias",
					Hibernate.INTEGER).addScalar("idMedicaoHistorico",
					Hibernate.INTEGER).addScalar("dataLeituraAnterior",
					Hibernate.DATE).addScalar("leituraAnterior",
					Hibernate.INTEGER).addScalar("dataLeituraAtualFaturamento",
					Hibernate.DATE).addScalar("leituraAtualFaturamento",
					Hibernate.INTEGER).addScalar("consumoMedido",
					Hibernate.INTEGER).addScalar("idAnormalidadeLeitura",
					Hibernate.INTEGER).addScalar("idConsumoHistorico",
					Hibernate.INTEGER).addScalar("consumoMedio",
					Hibernate.INTEGER).addScalar("consumoFaturado",
					Hibernate.INTEGER).addScalar("rateio", Hibernate.INTEGER)
					.addScalar("dsAbreviadaAnormalidadeConsumo",
							Hibernate.STRING).addScalar(
							"dsAbreviadaTipoConsumo", Hibernate.STRING)
					.addScalar("tipoMedicao", Hibernate.INTEGER).addScalar(
							"consumoEsgoto", Hibernate.INTEGER).addScalar(
							"consumoInformado", Hibernate.INTEGER).addScalar(
							"consumosVinculados", Hibernate.INTEGER).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * Obtém os ids de todas as rotas cadastradas
	 * 
	 * [UC0251] - Gerar Atividade de Ação de Cobrança
	 * 
	 * [SB0002] - Gerar Atividade de Ação de Cobrança para os Imóveis da Lista
	 * de Rotas
	 * 
	 * @author Leonardo Vieira
	 * @date 13/12/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarListaRotas() throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select rota from Rota rota ";

			retorno = (Collection) session.createQuery(consulta).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Obtém os ids de todas as rotas cadastradas menos as rotas que tiverem o
	 * emp_cobranca = 1
	 * 
	 * [UC0251] - Gerar Atividade de Ação de Cobrança
	 * 
	 * [SB0002] - Gerar Atividade de Ação de Cobrança para os Imóveis da Lista
	 * de Rotas
	 * 
	 * @author Sávio Luiz
	 * @date 05/03/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarListaRotasEspecificas()
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select rota from Rota rota "
					+ "left join rota.empresaCobranca empCobranca "
					+ "where empCobranca.id = :indUm";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"indUm", 1).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [] Ligacoes Medicao Individualizada
	 * 
	 * 
	 * @author Flávio Cordeiro
	 * @date 17/12/2006
	 * 
	 * @param colecaoLigacoesMedicao
	 * @throws ControladorException
	 */
	public void atualizarLigacoesMedicaoIndividualizada(
			LigacaoMedicaoIndividualizadaHelper ligacaoMedicaoIndividualizadaHelper,
			Integer anoMes) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String update;

		try {

			update = "update MedicaoHistorico as medicao set"
					+ " leituraAnteriorFaturamento = :leituraAnteriorFaturamento,"
					+ " dataLeituraAnteriorFaturamento = :dataLeituraAnteriorFaturamento,"
					+ " leituraAtualFaturamento = :leituraAtualFaturamento,"
					+ " dataLeituraAtualFaturamento = :dataLeituraAtualFaturamento,"
					+ " leituraAnormalidadeFaturamento = "
					+ ligacaoMedicaoIndividualizadaHelper
							.getIdLeituraAnormalidade()
					+ ", numeroConsumoInformado = "
					+ ligacaoMedicaoIndividualizadaHelper.getConsumoInformado()
					+ " where medicao.id = "
					+ ligacaoMedicaoIndividualizadaHelper
							.getIdMedicaoHistorico()
					+ " and medicao.anoMesReferencia = " + anoMes;

			session
					.createQuery(update)
					.setInteger(
							"leituraAnteriorFaturamento",
							ligacaoMedicaoIndividualizadaHelper
									.getLeituraAnterior())
					.setDate(
							"dataLeituraAnteriorFaturamento",
							Util
									.converteStringParaDate(ligacaoMedicaoIndividualizadaHelper
											.getDataLeituraAnterior()))
					.setInteger(
							"leituraAtualFaturamento",
							ligacaoMedicaoIndividualizadaHelper
									.getLeituraAtual())
					.setDate(
							"dataLeituraAtualFaturamento",
							Util
									.converteStringParaDate(ligacaoMedicaoIndividualizadaHelper
											.getDataLeituraAtual()))
					.executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 * 
	 * Obter empresa da rota.
	 * 
	 * [SB0006 - ]
	 * 
	 * @author Raphael Rossiter
	 * @date 09/01/2007
	 * 
	 * @param rota
	 * @throws ErroRepositorioException
	 */
	public Integer obterIdEmpresaPorRota(Rota rota)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT rota.empresa.id " + "FROM Rota rota "
					+ "WHERE rota.id = :idRota";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idRota", rota.getId()).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0082] - Registrar Leituras e Anormalidades
	 * 
	 * [SB0001] - Gerar Relatório Resumo das Leituras e Anormalidades
	 * Registradas
	 * 
	 * Obter empresa do imóvel.
	 * 
	 * @author Rafael Corrêa
	 * @date 13/04/2007
	 * 
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public Integer obterIdEmpresaPorImovel(Integer idImovel)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT imovel.quadra.rota.empresa.id "
					+ "FROM Imovel imovel " + "WHERE imovel.id = :idImovel";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0103] Efetuar Rateio de Consumo
	 * 
	 * atualiza o consumo de água/esgoto a ser rateado e o consumo de
	 * água/esgoto dos imóveis vínculados do imóvel condomínio.
	 * 
	 * @author Pedro Alexandre
	 * @date 17/01/2007
	 * 
	 * @param idConsumoHistoricoImovelCondominio
	 * @param consumoRateio
	 * @param consumoImovelVinculadosCondominio
	 * @throws ErroRepositorioException
	 */
	public void atualizarConsumoHistoricoImovelCondominio(
			Integer idConsumoHistoricoImovelCondominio, Integer consumoRateio,
			Integer consumoImovelVinculadosCondominio)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {
			String sql = "update ConsumoHistorico "
					+ "set consumoRateio = :consumoRateio "
					+ ",consumoImovelVinculadosCondominio = :consumoImovelVinculadosCondominio "
					+ ",ultimaAlteracao = :data "
					+ "where id = :idConsumoHistoricoImovelCondominio";

			session.createQuery(sql).setInteger("consumoRateio", consumoRateio)
					.setInteger("consumoImovelVinculadosCondominio",
							consumoImovelVinculadosCondominio).setTimestamp(
							"data", new Date()).setInteger(
							"idConsumoHistoricoImovelCondominio",
							idConsumoHistoricoImovelCondominio).executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0002] Gerar Relação(ROL) em TXT
	 * 
	 * @author Rafael Francisco Pinto
	 * @date 19/01/2007
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisParaLeituraPorColecaoRota(
			Collection colecaoRota, Integer idLeituraTipo, int numeroPagina)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select imovel.id,"// 0
					+ "localidade.id,"// 1
					+ "setorComercial.codigo,"// 2
					+ "quadra.numeroQuadra,"// 3
					+ "imovel.lote,"// 4
					+ "imovel.subLote,"// 5
					+ "imovelPerfil.id,"// 6
					+ "ligacaoAgua.id,"// 7
					+ "hidInstHistoricoAgua.id,"// 8
					+ "hidInstHistoricoPoco.id,"// 9
					+ "rota.id,"// 10
					+ "rota.indicadorFiscalizarSuprimido,"// 11
					+ "rota.indicadorFiscalizarCortado,"// 12
					+ "rota.indicadorGerarFiscalizacao,"// 13
					+ "rota.indicadorGerarFalsaFaixa,"// 14
					+ "rota.percentualGeracaoFiscalizacao,"// 15
					+ "rota.percentualGeracaoFaixaFalsa,"// 16
					+ "empresa.id,"// 17
					+ "empresa.descricaoAbreviada,"// 18
					+ "empresa.email,"// 19
					+ "faturamentoGrupo.id,"// 20
					+ "faturamentoGrupo.descricao,"// 21
					+ "leituraTipo.id,"// 22
					+ "ligacaoAguaSituacao.id,"// 23
					+ "ligacaoEsgotoSituacao.id, "// 24
					+ "faturamentoSituacaoTipo.indicadorParalisacaoLeitura, "// 25
					+ "medTipoAgua.id, "// 26
					+ "medTipoPoco.id, "// 27
					+ "gerenciaRegional.id, "// 28
					+ "gerenciaRegional.nome, "// 29
					+ "localidade.descricao, "// 30
					+ "ligacaoAguaSituacao.descricao, "// 31
					+ "ligacaoAgua.numeroLacre, "// 32
					+ "hidroAgua.numero, "// 33
					+ "imovel.numeroSequencialRota, "// 34
					+ "rota.codigo, "// 35
					+ "hidroPoco.numero, "// 36
					+ "ligacaoAguaSituacao.indicadorFaturamentoSituacao, "// 37
					+ "ligacaoEsgotoSituacao.indicadorFaturamentoSituacao "// 38
					+ "from Imovel imovel "
					+ "left join imovel.localidade localidade "
					+ "left join imovel.setorComercial setorComercial "
					+ "left join imovel.quadra quadra "
					+ "left join imovel.imovelPerfil imovelPerfil "
					+ "left join imovel.ligacaoAgua ligacaoAgua "
					+ "left join imovel.hidrometroInstalacaoHistorico hidInstHistoricoPoco "
					+ "left join ligacaoAgua.hidrometroInstalacaoHistorico hidInstHistoricoAgua "
					+ "left join hidInstHistoricoAgua.hidrometro hidroAgua "
					+ "left join hidInstHistoricoAgua.medicaoTipo medTipoAgua "
					+ "left join hidInstHistoricoPoco.medicaoTipo medTipoPoco "
					+ "left join hidInstHistoricoPoco.hidrometro hidroPoco "
					+ "left join quadra.rota rota "
					+ "left join rota.empresa empresa "
					+ "left join rota.faturamentoGrupo faturamentoGrupo "
					+ "left join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "
					+ "left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "
					+ "left join imovel.faturamentoSituacaoTipo faturamentoSituacaoTipo "
					+ "left join rota.leituraTipo leituraTipo "
					+ "left join localidade.gerenciaRegional gerenciaRegional "
					+ "where rota.id in (:idsRotas) and "
					+ "leituraTipo.id = :idLeituraTipo "
					+ "order by gerenciaRegional.id,localidade.id,setorComercial.codigo,"
					+ "rota.codigo,imovel.numeroSequencialRota ";

			retorno = session.createQuery(consulta).setInteger("idLeituraTipo",
					idLeituraTipo).setParameterList("idsRotas", colecaoRota)
					.setFirstResult(numeroPagina).setMaxResults(500).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0002] Gerar Relação(ROL) em TXT - Registro 2 (DOIS)
	 * 
	 * @author Rafael Francisco Pinto
	 * @date 22/01/2007
	 * 
	 * @param idImovel
	 * @param anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarNumeroConsumoMedioImovel(Integer idImovel,
			int anoMesReferencia, Integer idLigacaoTipo)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT ch.consumoMedio "
					+ "FROM ConsumoHistorico as ch "
					+ "INNER JOIN ch.imovel imov "
					+ "INNER JOIN ch.ligacaoTipo ligacaoTipo "
					+ "WHERE imov.id = :id "
					+ "AND ch.referenciaFaturamento = :anoMes "
					+ "AND ligacaoTipo.id = :idLigacaoTipo ";

			retorno = (Integer) session.createQuery(consulta).setInteger("id",
					idImovel).setInteger("anoMes", anoMesReferencia)
					.setInteger("idLigacaoTipo", idLigacaoTipo)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0002] Gerar Relação(ROL) em TXT - Registro 1 (HUM)
	 * 
	 * @author Rafael Francisco Pinto
	 * @date 23/01/2007
	 * 
	 * @param idImovel
	 * @param anoMesReferencia
	 * @param idTipoLigacao
	 * 
	 * @throws ErroRepositorioException
	 */
	public String obterDescricaoConsumoTipo(Integer idImovel,
			Integer idTipoLigacao) throws ErroRepositorioException {

		String retorno = null;
		Object[] retornoConsulta = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT consumoTipo.id,consumoTipo.descricao,max(consumoHistorico.referenciaFaturamento) "
					+ "FROM ConsumoHistorico consumoHistorico "
					+ "INNER JOIN consumoHistorico.imovel imovel "
					+ "INNER JOIN consumoHistorico.ligacaoTipo ligacaoTipo "
					+ "INNER JOIN consumoHistorico.consumoTipo consumoTipo "
					+ "WHERE imovel.id = :idImovel "
					+ "AND ligacaoTipo.id = :idTipoLigacao "
					+ "GROUP BY consumoTipo.id,consumoTipo.descricao";

			retornoConsulta = (Object[]) session.createQuery(consulta)
					.setInteger("idImovel", idImovel).setInteger(
							"idTipoLigacao", idTipoLigacao).setMaxResults(1)
					.uniqueResult();

			if (retornoConsulta != null && retornoConsulta.length > 0) {
				retorno = (String) retornoConsulta[1];
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * @author Sávio Luiz
	 * @date 13/03/2007
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisPorRotaOrdenadoPorInscricao(
			Collection colecaoRota, Integer idLeituraTipo, int inicioPesquisa,
			String empresa) throws ErroRepositorioException {
		Collection retorno = null;
		Collection idsRotas = new ArrayList();
		Iterator iteColecaoRotas = colecaoRota.iterator();
		while (iteColecaoRotas.hasNext()) {
			Rota rota = (Rota) iteColecaoRotas.next();
			idsRotas.add(rota.getId());
		}

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select imovel.id,"
					+ // 0
					"localidade.id,"
					+ // 1
					"setorComercial.codigo,"
					+ // 2
					"quadra.numeroQuadra,"
					+ // 3
					"imovel.lote,"
					+ // 4
					"imovel.subLote,"
					+ // 5
					"imovelPerfil.id,"
					+ // 6
					"ligacaoAgua.id,"
					+ // 7
					"hidInstHistoricoAgua.id,"
					+ // 8
					"hidInstHistoricoPoco.id,"
					+ // 9
					"rota.id,"
					+ // 10
					"rota.indicadorFiscalizarSuprimido,"
					+ // 11
					"rota.indicadorFiscalizarCortado,"
					+ // 12
					"rota.indicadorGerarFiscalizacao,"
					+ // 13
					"rota.indicadorGerarFalsaFaixa,"
					+ // 14
					"rota.percentualGeracaoFiscalizacao,"
					+ // 15
					"rota.percentualGeracaoFaixaFalsa,"
					+ // 16
					"empresa.id,"
					+ // 17
					"empresa.descricaoAbreviada,"
					+ // 18
					"empresa.email,"
					+ // 19
					"faturamentoGrupo.id,"
					+ // 20
					"faturamentoGrupo.descricao,"
					+ // 21
					"leituraTipo.id,"
					+ // 22
					"ligacaoAguaSituacao.id,"
					+ // 23
					"ligacaoEsgotoSituacao.id, "
					+ // 24
					"faturamentoSituacaoTipo.indicadorParalisacaoLeitura, "// 25
					+ "medTipoAgua.id, "// 26
					+ "medTipoPoco.id, "// 27
					+ "empresa.descricao, " // 28
					+ "roteiroEmpresa, "// 29
					+ "hidInstHistoricoAgua,"// 30
					+ "hidInstHistoricoPoco, "// 31
					+ "empresaRota.id, " // 32
					+ "ligacaoAguaSituacao.indicadorFaturamentoSituacao, " // 33
					+ "ligacaoEsgotoSituacao.indicadorFaturamentoSituacao " // 34
					+ "from Imovel imovel "
					+ "left join imovel.localidade localidade "
					+ "left join imovel.setorComercial setorComercial "
					+ "left join imovel.quadra quadra "
					+ "left join imovel.imovelPerfil imovelPerfil "
					+ "left join imovel.ligacaoAgua ligacaoAgua "
					+ "left join imovel.hidrometroInstalacaoHistorico hidInstHistoricoPoco "
					+ "left join ligacaoAgua.hidrometroInstalacaoHistorico hidInstHistoricoAgua "
					+ "left join fetch hidInstHistoricoAgua.hidrometroProtecao "
					+ "left join fetch hidInstHistoricoAgua.hidrometroLocalInstalacao "
					+ "left join fetch hidInstHistoricoPoco.hidrometroProtecao "
					+ "left join fetch hidInstHistoricoPoco.hidrometroLocalInstalacao "
					+ "left join hidInstHistoricoAgua.medicaoTipo medTipoAgua "
					+ "left join hidInstHistoricoPoco.medicaoTipo medTipoPoco "
					+ "left join quadra.rota rota "
					+ "left join rota.empresa empresaRota "
					+ "left join quadra.roteiroEmpresa roteiroEmpresa "
					+ "left join rota.empresa empresa "
					+ "left join rota.faturamentoGrupo faturamentoGrupo "
					+ "left join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "
					+ "left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "
					+ "left join imovel.faturamentoSituacaoTipo faturamentoSituacaoTipo "
					+ "left join rota.leituraTipo leituraTipo "
					+ "left join localidade.gerenciaRegional gerenciaRegional "

					+ "where rota.id in (:idsRotas) and "
					+ "leituraTipo.id = :idLeituraTipo and imovelPerfil.indicadorGerarDadosLeitura = 1 ";

			/*
			 * Alteração para ordenar igual ao rol da CAERN Thiago Nascimento
			 * 16/04/2008
			 */
			if (empresa.toUpperCase().equals("COMPESA")) {
				consulta = consulta
						+ "order by empresa.id,localidade.id,setorComercial.codigo,quadra.numeroQuadra,imovel.lote,imovel.subLote";
			} else if (empresa.toUpperCase().equals("CAERN")) {
				consulta = consulta
						+ "order by gerenciaRegional.id, localidade.id,setorComercial.codigo,rota.codigo, imovel.numeroSequencialRota, "
						+ "imovel.lote, imovel.subLote ";
			}

			retorno = session.createQuery(consulta).setInteger("idLeituraTipo",
					idLeituraTipo).setParameterList("idsRotas", idsRotas)
					.setFirstResult(inicioPesquisa).setMaxResults(1000).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisa todas as rotas do sistema. Metódo usado no [UC0302] Gerar Débito
	 * a Cobrar de Acréscimos por Impontualidade
	 * 
	 * @author Pedro Alexandre
	 * @date 20/03/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarListaRotasCarregadas()
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select rota from Rota rota ";

			retorno = (Collection) session.createQuery(consulta).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0105] Obter Consumo Mínimo da Ligação por Subcategoria
	 * 
	 * (CSTC_NNCONSUMOMINIMO da tarifa associada ao imóvel na tabela
	 * CONSUMO_TARIFA_CATEGORIA com SCAT_ID=Id da subcategoria e CSTV_ID =
	 * CSTV_ID da tabela CONSUMO_TARIFA_VIGENCIA com CSTF_ID=CSTF_ID da tabela
	 * IMOVEL e maior CSTV_DTVIGENCIA, que seja menor ou igual a data corrente)
	 * 
	 * @author Raphael Rossiter
	 * @date 11/04/2007
	 * 
	 * @return subcategoria, consumoTarifaVigencia
	 * @throws ControladorException
	 */
	public Object pesquisarConsumoMinimoTarifaSubcategoriaVigencia(
			Subcategoria subcategoria,
			ConsumoTarifaVigencia consumoTarifaVigencia)
			throws ErroRepositorioException {

		Object retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select ct.numeroConsumoMinimo "
					+ "from ConsumoTarifaCategoria ct "
					+ "inner join ct.consumoTarifaVigencia.consumoTarifa "
					+ "inner join ct.subCategoria scat "
					+ "where ct.consumoTarifaVigencia.id = :consumoTarifaVigenciaId "
					+ "and ct.subCategoria.id = :subCategoriaId ";

			retorno = session.createQuery(consulta).setInteger(
					"consumoTarifaVigenciaId", consumoTarifaVigencia.getId())
					.setInteger("subCategoriaId", subcategoria.getId())
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0082] - Registrar Leituras e Anormalidades
	 * 
	 * [SB0001] - Gerar Relatório Resumo das Leituras e Anormalidades
	 * 
	 * Pesquisa os dados do relatório do comparativo de leituras e anormalidades
	 * 
	 * @author Rafael Corrêa - Hugo Leonardo      - Magno Gouveia
	 * @date 13/04/2007 - 18/03/2010      		  - 21/06/2011
	 * 
	 * @author Magno Gouveia
	 * @param idPerfilImovel
	 * @param numOcorrenciasConsecutivas
	 * @param colecaoAnormalidadesLeituras
	 * @param sistemaParametro
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosRelatorioComparativoLeiturasEAnormalidades(
			Integer idGrupoFaturamento, Integer idEmpresa, Integer anoMes,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorInicial, Integer idSetorFinal, Integer idGerencia,
			Integer idUnidadeNegocio, Integer idLeiturista,
			Integer idRotaInicial, Integer idRotaFinal,
			Integer idPerfilImovel, Integer numOcorrenciasConsecutivas, Collection colecaoAnormalidadesLeituras,
			SistemaParametro sistemaParametro)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			
			String consulta = "select loc.loca_id as idLocalidade, "  //0
				+ "setor.stcm_id as idSetor, " //1
				+ "setor.stcm_cdsetorcomercial as codigoSetor, " //2
				//Com Leitura 
				+ "sum(case when (mrem.mrem_nnleiturahidrometro is not null " 
				+ "and (mrem.ltan_id is null or mrem.ltan_id = 0))  then 1  else 0  end) as comLeitura, "//3
				//Com Anormalidade
				+ "sum(case when (mrem.mrem_nnleiturahidrometro is null " 
				+ "and (mrem.ltan_id is not null and mrem.ltan_id <> 0))  then 1  else 0  end) as comAnormalidade, " //4
				//Com Leitura Anormalidade
				+ "sum(case when (mrem.mrem_nnleiturahidrometro is not null and (mrem.ltan_id is not null " 
				+ "and mrem.ltan_id <> 0))  then 1  else 0  end) as comLeituraAnormalidade, " //5
				//Registros Recebidos
				+ "sum(case when (mrem.mrem_nnleiturahidrometro is not null " 
				+ "or (mrem.ltan_id is not null and mrem.ltan_id <> 0))  then 1  else 0  end) as recebidos, " //6
				//Registros enviados
				+ "sum(case when (mrem.mrem_id is not null and mrem.mrem_dtinstalacaohidrometro is not null)  then 1  else 0  end) as enviados, " //7
				+ "mrem.mrem_cdrota as codigoRota, " //8
				//Registros Recebidos Convencional
				+ " sum(case when (mrem.mrem_id is not null) " 
				+ " and (mcpf.mcpf_id is null) and (mrem.mrem_tmleitura is not null) and (mrem.mrem_dtinstalacaohidrometro is not null) then 1  else 0  end) as convencional, " //9
				//Registros Recebidos Simultaneo
				+ " sum(case when (mrem.mrem_id is not null " 
				+ " and (mcpf.mcpf_id is not null) and ((mcpf.medt_id=1) or (mcpf.medt_id=2)) "
				+ " and (mcpf.mcpf_nnleiturahidrometro is not null or mcpf.ltan_id  is not null)) "  
				+ " then 1 else 0  end) as simultaneo " //10 	
				
				+ "from micromedicao.movimento_roteiro_empr mrem "
				+ "INNER JOIN cadastro.localidade loc on loc.loca_id = mrem.loca_id "
				+ "INNER JOIN cadastro.setor_comercial setor on setor.loca_id = mrem.loca_id "
				+ "and setor.stcm_cdsetorcomercial = mrem.mrem_cdsetorcomercial "
			    //+ "INNER JOIN cadastro.empresa empr on empr.empr_id = mrem.empr_id "	
				/*+ " LEFT JOIN faturamento.mov_conta_prefaturada mcpf on (mcpf.imov_id = mrem.imov_id and mcpf.mcpf_ammovimento = mrem.mrem_ammovimento " 
				+ " and (mcpf.mcpf_nnleiturahidrometro is not null or mcpf.ltan_id  is not null))"*/
				+ " LEFT JOIN faturamento.mov_conta_prefaturada mcpf on (mcpf.imov_id = mrem.imov_id and mcpf.mcpf_ammovimento = mrem.mrem_ammovimento "
				+ " and (mcpf.mcpf_nnleiturahidrometro is not null or mcpf.ltan_id is not null)and mcpf.medt_id=mrem.medt_id) "
				+ "INNER JOIN cadastro.imovel i on i.imov_id = mrem.imov_id "
				+ " where mrem.mrem_ammovimento = :anoMes and mrem.mrem_dtinstalacaohidrometro is not null "
				+ " and i.imov_icexclusao <> 1";
		
				//Grupo de Faturamento
				if (idGrupoFaturamento != null) {
					consulta = consulta + "	and mrem.ftgr_id = " + idGrupoFaturamento;
				}
				
				//Firma 
				if (idEmpresa != null) {
					consulta = consulta + " and mrem.empr_id = " + idEmpresa;
				}
				//Leiturista
				if (idLeiturista != null && !idLeiturista.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					consulta = consulta + " and mrem.leit_id = " + idLeiturista;
				}
				
				//Gerencia Regional
				if (idGerencia != null) {
					consulta = consulta + "	and mrem.greg_id = " + idGerencia;
				}
				//Unidade de Negocio
				if (idUnidadeNegocio != null) {
					consulta = consulta + "	and loc.uneg_id = " + idUnidadeNegocio;
				}
				//Localidade Inicial
				if (idLocalidadeInicial != null) {
					if (idLocalidadeInicial.equals(idLocalidadeFinal)) {
						consulta = consulta + "	and loc.loca_id = " + idLocalidadeInicial;
						//Setor Comercial
						if (idSetorInicial != null) {
							if (idSetorInicial.equals(idSetorFinal)) {
								consulta = consulta + "	and setor.stcm_id = "+ idSetorInicial;
								//Rota
				    			if(idRotaInicial != null){
				    				if(idRotaInicial.equals(idRotaFinal)){
				    					consulta += " and mrem.mrem_cdrota = "+idRotaInicial;
				    				} else{
				    					consulta += " and mrem.mrem_cdrota between " +idRotaInicial+" and "+idRotaFinal;
				    				}
				    			} 
								
							} else {
								consulta = consulta + "	and setor.stcm_id between "
										+ idSetorInicial + " and " + idSetorFinal;
							}
						}
					} else {
						consulta = consulta + "	and loc.loca_id between "
								+ idLocalidadeInicial + " and " + idLocalidadeFinal;
					}
				}
				
				if(idPerfilImovel != null){
					consulta += " AND i.iper_id = :idPerfilImovel ";
				}
				
				List<Integer> anoMesOcorrencias = new ArrayList<Integer>();
				if(colecaoAnormalidadesLeituras != null && !colecaoAnormalidadesLeituras.isEmpty()){
					// caso a opção -1 tenha sido selecionada, remove
					if(colecaoAnormalidadesLeituras.contains(ConstantesSistema.NUMERO_NAO_INFORMADO)){
						colecaoAnormalidadesLeituras.remove(ConstantesSistema.NUMERO_NAO_INFORMADO);
					}
					
					// caso não tenha sido setado o número de ocorrências consecutivas, ou esta seja menor ou igual a 0
					if(numOcorrenciasConsecutivas == null || numOcorrenciasConsecutivas <= 0){
						numOcorrenciasConsecutivas = 1;
					}
					
					for (int i = 1; i <= (numOcorrenciasConsecutivas + 1); i++) {
						anoMesOcorrencias.add(Util.subtrairMesDoAnoMes((Integer) sistemaParametro.getAnoMesFaturamento(), i));
					}
					
					consulta += " AND mrem.ltan_id IN (:anormalidadesLeituras) " 
						      + " AND i.imov_id IN (SELECT imovel "		  
						      +					   "FROM "
							  +						"(SELECT mh.lagu_id AS imovel, mh.ltan_idleitanorminformada "
							  +						"FROM micromedicao.medicao_historico mh "
							  +						"WHERE mdhi_amleitura IN (:anoMesOcorrencias) "
							  +				  		  "AND mh.ltan_idleitanorminformada IN (:anormalidadesLeituras) "
							  +						"GROUP BY mh.lagu_id, mh.ltan_idleitanorminformada HAVING COUNT(*) >= :numOcorrenciasConsecutivas "
							  +						"ORDER BY 1) temp) ";
					
				}
				
				consulta = consulta
						+ " GROUP BY loc.loca_id, " +
								"setor.stcm_id, " +
								"setor.stcm_cdsetorcomercial, " +
								"mrem.mrem_cdrota "
								
						+ " ORDER BY loc.loca_id, setor.stcm_cdsetorcomercial, mrem.mrem_cdrota ";
				
				Query query = session.createSQLQuery(consulta)
											.addScalar("idLocalidade", Hibernate.INTEGER)
											.addScalar("idSetor",Hibernate.INTEGER)
											.addScalar("codigoSetor",Hibernate.INTEGER)
											.addScalar("comLeitura",Hibernate.INTEGER)
											.addScalar("comAnormalidade",Hibernate.INTEGER)
											.addScalar("comLeituraAnormalidade",Hibernate.INTEGER)
											.addScalar("recebidos", Hibernate.INTEGER)
											.addScalar("enviados", Hibernate.INTEGER)
											.addScalar("codigoRota", Hibernate.SHORT)
											.addScalar("convencional", Hibernate.INTEGER)
											.addScalar("simultaneo", Hibernate.INTEGER)
											.setInteger("anoMes", anoMes);
				
				if (idPerfilImovel != null) {
					query.setInteger("idPerfilImovel", idPerfilImovel);
				}
				
				if (colecaoAnormalidadesLeituras != null && colecaoAnormalidadesLeituras.size() > 0) {
					query.setParameterList("anormalidadesLeituras", colecaoAnormalidadesLeituras);
					query.setParameterList("anoMesOcorrencias", anoMesOcorrencias);
					query.setInteger("numOcorrenciasConsecutivas", numOcorrenciasConsecutivas);
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
	 * [UC0082] - Registrar Leituras e Anormalidades
	 * 
	 * [SB0001] - Gerar Relatório Resumo das Leituras e Anormalidades
	 * 
	 * Pesquisa as Anormalidades de Leitura e suas quantidades
	 * 
	 * @author Rafael Corrêa - Hugo Leonardo - Magno Gouveia 
	 * @date 13/04/2007 - 18/03/2010		 - 29/06/2011
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAnormalidadesRelatorioComparativoLeiturasEAnormalidades(
			Integer idGrupoFaturamento, Integer idEmpresa, Integer anoMes,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorInicial, Integer idSetorFinal, Integer idGerencia,
			Integer idUnidadeNegocio, Integer idLeiturista,
			Integer idRotaInicial, Integer idRotaFinal,
			Integer idPerfilImovel, Integer numOcorrenciasConsecutivas, Collection colecaoAnormalidadesLeituras,
			SistemaParametro sistemaParametro)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "SELECT "
					+ " anormalidade.ltan_id AS id, "
					+ " anormalidade.ltan_dsleituraanormalidade AS descricao, "
					+ " COUNT(mrem.ltan_id) AS qtd";
			
			if (idEmpresa != null) {
				consulta += " ,empr.empr_id AS idEmpresa, "
						  + " empr.empr_nmempresa AS nomeEmpresa ";
			}
			
			if(idGrupoFaturamento != null && !idGrupoFaturamento.equals(ConstantesSistema.INVALIDO_ID)){
				consulta += ", mrem.ftgr_id as grupoFaturamento ";
			}
					
			consulta += " FROM micromedicao.movimento_roteiro_empr mrem" 
					  + " INNER JOIN micromedicao.leitura_anormalidade anormalidade on (mrem.ltan_id = anormalidade.ltan_id and mrem.ltan_id <> 0)"
					  + " INNER JOIN cadastro.localidade loc on loc.loca_id = mrem.loca_id"
					  + " INNER JOIN cadastro.setor_comercial setor on setor.loca_id = mrem.loca_id and setor.stcm_cdsetorcomercial = mrem.mrem_cdsetorcomercial"	
					  + " INNER JOIN cadastro.empresa empr on empr.empr_id = mrem.empr_id"
					  + " INNER JOIN cadastro.imovel i on i.imov_id = mrem.imov_id"
					  + " WHERE mrem.mrem_ammovimento = :anoMes AND mrem.mrem_dtinstalacaohidrometro IS NOT NULL "
					  + " AND i.imov_icexclusao <> 1 ";
					
			if(idEmpresa != null){
				consulta += " AND mrem.empr_id = :idEmpresa";
				
				// Filtro por Leiturista
				if(idLeiturista != null && !idLeiturista.equals(ConstantesSistema.INVALIDO_ID)){
					consulta += " AND mrem.leit_id = :idLeiturista";
				}
			}
					
			if(idGrupoFaturamento != null && !idGrupoFaturamento.equals(ConstantesSistema.INVALIDO_ID)){
				consulta += " AND mrem.ftgr_id = :idGrupoFaturamento";
			}
					
			if(idGerencia != null && !idGerencia.equals(ConstantesSistema.INVALIDO_ID)){
				consulta += " AND mrem.greg_id = :idGerencia";
			}
					
			if(idUnidadeNegocio != null && !idUnidadeNegocio.equals(ConstantesSistema.INVALIDO_ID)){
				consulta += " AND loc.uneg_id = :idUnidadeNegocio";
			}
					
			// Localidade
			if(idLocalidadeInicial != null) {
				if(idLocalidadeInicial.equals(idLocalidadeFinal)){
					consulta += " AND loc.loca_id = :idLocalidadeInicial";	

					// Setor Comercial
					if(idSetorInicial != null){
			    		if(idSetorInicial.equals(idSetorFinal)){
			    			consulta += " AND setor.stcm_id = :idSetorInicial";
			    			
			    			// Rota
			    			if (idRotaInicial != null){
			    				if(idRotaInicial.equals(idRotaFinal)){
			    					consulta += " AND mrem.mrem_cdrota = :idRotaInicial";
			    				}else{
			    					consulta += " AND mrem.mrem_cdrota BETWEEN :idRotaInicial AND :idRotaFinal";
				    			}
			    			}
			    		}else{
			    			consulta += " AND setor.stcm_id BETWEEN :idSetorInicial AND :idSetorFinal";	
			    		}
			    	}
			    }else{
			    	consulta += " AND loc.loca_id BETWEEN :idLocalidadeInicial AND :idLocalidadeFinal";	
			    }
			}
					
			if(idPerfilImovel != null){
				consulta += " AND i.iper_id = :idPerfilImovel ";
			}
					
			List<Integer> anoMesOcorrencias = new ArrayList<Integer>();
			if(colecaoAnormalidadesLeituras != null && !colecaoAnormalidadesLeituras.isEmpty()){
				// caso a opção -1 tenha sido selecionada, remove
				if(colecaoAnormalidadesLeituras.contains(ConstantesSistema.NUMERO_NAO_INFORMADO)){
					colecaoAnormalidadesLeituras.remove(ConstantesSistema.NUMERO_NAO_INFORMADO);
				}
				
				// caso não tenha sido setado o número de ocorrências consecutivas, ou esta seja menor ou igual a 0
				if(numOcorrenciasConsecutivas == null || numOcorrenciasConsecutivas <= 0){
					numOcorrenciasConsecutivas = 1;
				}
				
				for (int i = 1; i <= (numOcorrenciasConsecutivas + 1); i++) {
					anoMesOcorrencias.add(Util.subtrairMesDoAnoMes((Integer) sistemaParametro.getAnoMesFaturamento(), i));
				}
				
				consulta += " AND mrem.ltan_id IN (:anormalidadesLeituras) " 
						  + " AND i.imov_id IN (SELECT imovel "
						  +					   "FROM "
						  +						"(SELECT mh.lagu_id AS imovel, mh.ltan_idleitanorminformada "
						  +						"FROM micromedicao.medicao_historico mh "
						  +						"WHERE mdhi_amleitura IN (:anoMesOcorrencias) "
						  +				  		  "AND mh.ltan_idleitanorminformada IN (:anormalidadesLeituras) "
						  +						"GROUP BY mh.lagu_id, mh.ltan_idleitanorminformada HAVING COUNT(*) >= :numOcorrenciasConsecutivas "
						  +						"ORDER BY 1) temp) ";
				
			}
					
			consulta = consulta + " group by";
			
			if(idGrupoFaturamento != null && !idGrupoFaturamento.equals(ConstantesSistema.INVALIDO_ID)){
				consulta += " mrem.ftgr_id, ";
			}
			
			if(idEmpresa != null){
				consulta += " empr.empr_id, empr.empr_nmempresa, ";
			}

			consulta += " anormalidade.ltan_id, anormalidade.ltan_dsleituraanormalidade";
			consulta += " order by anormalidade.ltan_id ";

			Query query = null;
		    if (idGrupoFaturamento == null && idEmpresa == null) {
		    	query = session.createSQLQuery(consulta)
	    								.addScalar("id", Hibernate.INTEGER)
	    								.addScalar("descricao", Hibernate.STRING)						
	    								.addScalar("qtd", Hibernate.INTEGER)
								    	.setInteger("anoMes", anoMes);
		    	
		    	if(idEmpresa != null){
		    		query.setInteger("idEmpresa", idEmpresa);
		    		if(idLeiturista != null && !idLeiturista.equals(ConstantesSistema.INVALIDO_ID)){
		    			query.setInteger("idLeiturista", idLeiturista);
		    		}
		    	}
		    	if(idGrupoFaturamento != null && !idGrupoFaturamento.equals(ConstantesSistema.INVALIDO_ID)){
		    		query.setInteger("idGrupoFaturamento", idGrupoFaturamento);
		    	}
		    	if(idGerencia != null && !idGerencia.equals(ConstantesSistema.INVALIDO_ID)){
		    		query.setInteger("idGerencia", idGerencia);
		    	}
		    	if(idUnidadeNegocio != null && !idUnidadeNegocio.equals(ConstantesSistema.INVALIDO_ID)){
		    		query.setInteger("idUnidadeNegocio", idUnidadeNegocio);
		    	}

		    	if(idLocalidadeInicial != null) {
		    		query.setInteger("idLocalidadeInicial", idLocalidadeInicial);
		    		
		    		if(idLocalidadeInicial.equals(idLocalidadeFinal)){
			    		
		    			if(idSetorInicial != null){
			    			query.setInteger("idSetorInicial", idSetorInicial);
			    			
			    			if(idSetorInicial.equals(idSetorFinal)){
				    			if (idRotaInicial != null){
				    				query.setInteger("idRotaInicial", idRotaInicial);
				    				
				    				if(!idRotaInicial.equals(idRotaFinal)){
					    				if (idRotaFinal != null){
					    					query.setInteger("idRotaFinal", idRotaFinal);
					    				}
				    				}
				    			}
			    			}else{
				    			if(idSetorFinal != null){
				    				query.setInteger("idSetorFinal", idSetorFinal);
				    			}
			    			}
			    		}		    			
		    		}else{
			    		if(idLocalidadeFinal != null){
			    			query.setInteger("idLocalidadeFinal", idLocalidadeFinal);
			    		}
		    		}
		    	}
		    	
		    	if (idPerfilImovel != null) {
		    		query.setInteger("idPerfilImovel", idPerfilImovel);
		    	}
		    	if (colecaoAnormalidadesLeituras != null && colecaoAnormalidadesLeituras.size() > 0) {
			    	query.setParameterList("anormalidadesLeituras", colecaoAnormalidadesLeituras);
			    	query.setParameterList("anoMesOcorrencias", anoMesOcorrencias);
			    	query.setInteger("numOcorrenciasConsecutivas", numOcorrenciasConsecutivas);
		    	}
		    	
		    	retorno = query.list();
						
		    } else if (idGrupoFaturamento == null) {
		    	query = session.createSQLQuery(consulta)
		    							.addScalar("id", Hibernate.INTEGER)
		    							.addScalar("descricao", Hibernate.STRING)
										.addScalar("qtd",Hibernate.INTEGER)	
										.addScalar("idEmpresa", Hibernate.INTEGER)
										.addScalar("nomeEmpresa", Hibernate.STRING)					
										.setInteger("anoMes", anoMes);
		    	
		    	if(idEmpresa != null){
		    		query.setInteger("idEmpresa", idEmpresa);
		    		if(idLeiturista != null && !idLeiturista.equals(ConstantesSistema.INVALIDO_ID)){
		    			query.setInteger("idLeiturista", idLeiturista);
		    		}
		    	}
		    	if(idGrupoFaturamento != null && !idGrupoFaturamento.equals(ConstantesSistema.INVALIDO_ID)){
		    		query.setInteger("idGrupoFaturamento", idGrupoFaturamento);
		    	}
		    	if(idGerencia != null && !idGerencia.equals(ConstantesSistema.INVALIDO_ID)){
		    		query.setInteger("idGerencia", idGerencia);
		    	}
		    	if(idUnidadeNegocio != null && !idUnidadeNegocio.equals(ConstantesSistema.INVALIDO_ID)){
		    		query.setInteger("idUnidadeNegocio", idUnidadeNegocio);
		    	}
		    	
		    	if(idLocalidadeInicial != null) {
		    		query.setInteger("idLocalidadeInicial", idLocalidadeInicial);
		    		
		    		if(idLocalidadeInicial.equals(idLocalidadeFinal)){
			    		
		    			if(idSetorInicial != null){
			    			query.setInteger("idSetorInicial", idSetorInicial);
			    			
			    			if(idSetorInicial.equals(idSetorFinal)){
				    			if (idRotaInicial != null){
				    				query.setInteger("idRotaInicial", idRotaInicial);
				    				
				    				if(!idRotaInicial.equals(idRotaFinal)){
					    				if (idRotaFinal != null){
					    					query.setInteger("idRotaFinal", idRotaFinal);
					    				}
				    				}
				    			}
			    			}else{
				    			if(idSetorFinal != null){
				    				query.setInteger("idSetorFinal", idSetorFinal);
				    			}
			    			}
			    		}		    			
		    		}else{
			    		if(idLocalidadeFinal != null){
			    			query.setInteger("idLocalidadeFinal", idLocalidadeFinal);
			    		}
		    		}
		    	}
		    	
		    	if (idPerfilImovel != null) {
		    		query.setInteger("idPerfilImovel", idPerfilImovel);
		    	}
		    	if (colecaoAnormalidadesLeituras != null && colecaoAnormalidadesLeituras.size() > 0) {
			    	query.setParameterList("anormalidadesLeituras", colecaoAnormalidadesLeituras);
			    	query.setParameterList("anoMesOcorrencias", anoMesOcorrencias);
			    	query.setInteger("numOcorrenciasConsecutivas", numOcorrenciasConsecutivas);
		    	}
		    	
				retorno = query.list();
		    } else if (idEmpresa == null) {
				query = session.createSQLQuery(consulta)
										.addScalar("id", Hibernate.INTEGER)
										.addScalar("descricao", Hibernate.STRING)
										.addScalar("qtd",Hibernate.INTEGER)
										.addScalar("grupoFaturamento", Hibernate.INTEGER)
										.setInteger("anoMes", anoMes);
				
				if(idEmpresa != null){
		    		query.setInteger("idEmpresa", idEmpresa);
		    		if(idLeiturista != null && !idLeiturista.equals(ConstantesSistema.INVALIDO_ID)){
		    			query.setInteger("idLeiturista", idLeiturista);
		    		}
		    	}
		    	if(idGrupoFaturamento != null && !idGrupoFaturamento.equals(ConstantesSistema.INVALIDO_ID)){
		    		query.setInteger("idGrupoFaturamento", idGrupoFaturamento);
		    	}
		    	if(idGerencia != null && !idGerencia.equals(ConstantesSistema.INVALIDO_ID)){
		    		query.setInteger("idGerencia", idGerencia);
		    	}
		    	if(idUnidadeNegocio != null && !idUnidadeNegocio.equals(ConstantesSistema.INVALIDO_ID)){
		    		query.setInteger("idUnidadeNegocio", idUnidadeNegocio);
		    	}
		    	
		    	if(idLocalidadeInicial != null) {
		    		query.setInteger("idLocalidadeInicial", idLocalidadeInicial);
		    		
		    		if(idLocalidadeInicial.equals(idLocalidadeFinal)){
			    		
		    			if(idSetorInicial != null){
			    			query.setInteger("idSetorInicial", idSetorInicial);
			    			
			    			if(idSetorInicial.equals(idSetorFinal)){
				    			if (idRotaInicial != null){
				    				query.setInteger("idRotaInicial", idRotaInicial);
				    				
				    				if(!idRotaInicial.equals(idRotaFinal)){
					    				if (idRotaFinal != null){
					    					query.setInteger("idRotaFinal", idRotaFinal);
					    				}
				    				}
				    			}
			    			}else{
				    			if(idSetorFinal != null){
				    				query.setInteger("idSetorFinal", idSetorFinal);
				    			}
			    			}
			    		}		    			
		    		}else{
			    		if(idLocalidadeFinal != null){
			    			query.setInteger("idLocalidadeFinal", idLocalidadeFinal);
			    		}
		    		}
		    	}
		    	
		    	if (idPerfilImovel != null) {
		    		query.setInteger("idPerfilImovel", idPerfilImovel);
		    	}
		    	if (colecaoAnormalidadesLeituras != null && colecaoAnormalidadesLeituras.size() > 0) {
			    	query.setParameterList("anormalidadesLeituras", colecaoAnormalidadesLeituras);
			    	query.setParameterList("anoMesOcorrencias", anoMesOcorrencias);
			    	query.setInteger("numOcorrenciasConsecutivas", numOcorrenciasConsecutivas);
		    	}
		    	
				retorno = query.list();
		    }else{
				query = session.createSQLQuery(consulta)
										.addScalar("id", Hibernate.INTEGER)
										.addScalar("descricao", Hibernate.STRING)
										.addScalar("qtd",Hibernate.INTEGER)	
										.addScalar("idEmpresa", Hibernate.INTEGER)
										.addScalar("nomeEmpresa", Hibernate.STRING)
										.addScalar("grupoFaturamento", Hibernate.INTEGER)
										.setInteger("anoMes", anoMes);
				
				if(idEmpresa != null){
		    		query.setInteger("idEmpresa", idEmpresa);
		    		if(idLeiturista != null && !idLeiturista.equals(ConstantesSistema.INVALIDO_ID)){
		    			query.setInteger("idLeiturista", idLeiturista);
		    		}
		    	}
		    	if(idGrupoFaturamento != null && !idGrupoFaturamento.equals(ConstantesSistema.INVALIDO_ID)){
		    		query.setInteger("idGrupoFaturamento", idGrupoFaturamento);
		    	}
		    	if(idGerencia != null && !idGerencia.equals(ConstantesSistema.INVALIDO_ID)){
		    		query.setInteger("idGerencia", idGerencia);
		    	}
		    	if(idUnidadeNegocio != null && !idUnidadeNegocio.equals(ConstantesSistema.INVALIDO_ID)){
		    		query.setInteger("idUnidadeNegocio", idUnidadeNegocio);
		    	}

		    	if(idLocalidadeInicial != null) {
		    		query.setInteger("idLocalidadeInicial", idLocalidadeInicial);
		    		
		    	if(idLocalidadeInicial.equals(idLocalidadeFinal)){
			    		
		    			if(idSetorInicial != null){
			    			query.setInteger("idSetorInicial", idSetorInicial);
			    			
			    			if(idSetorInicial.equals(idSetorFinal)){
				    			if (idRotaInicial != null){
				    				query.setInteger("idRotaInicial", idRotaInicial);
				    				
				    				if(!idRotaInicial.equals(idRotaFinal)){
					    				if (idRotaFinal != null){
					    					query.setInteger("idRotaFinal", idRotaFinal);
					    				}
				    				}
				    			}
			    			}else{
				    			if(idSetorFinal != null){
				    				query.setInteger("idSetorFinal", idSetorFinal);
				    			}
			    			}
			    		}		    			
		    		}else{
			    		if(idLocalidadeFinal != null){
			    			query.setInteger("idLocalidadeFinal", idLocalidadeFinal);
			    		}
		    		}
		    	}		    	
		    	
		    	if (idPerfilImovel != null) {
		    		query.setInteger("idPerfilImovel", idPerfilImovel);
		    	}
		    	if (colecaoAnormalidadesLeituras != null && colecaoAnormalidadesLeituras.size() > 0) {
			    	query.setParameterList("anormalidadesLeituras", colecaoAnormalidadesLeituras);
			    	query.setParameterList("anoMesOcorrencias", anoMesOcorrencias);
			    	query.setInteger("numOcorrenciasConsecutivas", numOcorrenciasConsecutivas);
		    	}
		    	
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
	 * 
	 * @author Vivianne Sousa
	 * @date 06/06/2007
	 * 
	 * @param idImovel
	 * @param anoMes
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterDadosConsumoHistoricoDoImovel(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "consumoHistorico.numeroConsumoFaturadoMes, "
					+ "consAnormalidade.descricaoAbreviada "
					+ "from "
					+ "gcom.micromedicao.consumo.ConsumoHistorico consumoHistorico "
					+ "left join consumoHistorico.consumoAnormalidade consAnormalidade "
					+ "left join consumoHistorico.ligacaoTipo ligacaoTipo "
					+ "left join consumoHistorico.imovel imovel "
					+ "where imovel.id = :idImovel "
					+ "and ligacaoTipo.id = :idLigacaoTipo "
					+ "and consumoHistorico.referenciaFaturamento = :anoMes ";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setInteger("idLigacaoTipo",
					LigacaoTipo.LIGACAO_AGUA).setInteger("anoMes", anoMes)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 07/06/2007
	 * 
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterDadosMedicaoHistoricoDoImovel(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException {
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select mh.dataLeituraAtualInformada, "
					+ "mh.leituraAtualInformada, "
					+ "leituraAnormalidade.descricaoAbreviada "
					+ "from MedicaoHistorico mh "
					+ "left join mh.ligacaoAgua ligacaoAgua "
					+ "left join mh.leituraAnormalidadeInformada leituraAnormalidade "
					+ "where ligacaoAgua.id = :idImovel and "
					+ "mh.medicaoTipo.id = :idMedicaoTipo and "
					+ "mh.anoMesReferencia = :anoMesReferencia";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idImovel", idImovel.intValue()).setInteger(
					"idMedicaoTipo", MedicaoTipo.LIGACAO_AGUA.intValue())
					.setInteger("anoMesReferencia", anoMes.intValue())
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0613] - Registrar Leituras e Anormalidades
	 * 
	 * Pesquisa os imóveis com faixa falsa
	 * 
	 * @author Rafael Corrêa
	 * @date 18/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelFaixaFalsa(Integer anoMesReferencia)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "SELECT ftgr.ftgr_id as idGrupo, ftgr.ftgr_dsfaturamentogrupo as descricaoGrupo, " // 0,1
					+ " empresa.empr_id as idEmpresa, empresa.empr_nmempresa as nomeEmpresa, " // 2,3
					+ " loc.loca_id as idLocalidade, loc.loca_nmlocalidade as nomeLocalidade, " // 4,5
					+ " stcm.stcm_id as idSetor, stcm.stcm_cdsetorcomercial as codSetor, " // 6,7
					+ " stcm.stcm_nmsetorcomercial as nomeSetor, " // 8
					+ " leitFaixaFalsa.fxer_nnleiturafalsainferior as leituraFalsaInferior, " // 9
					+ " mdhi.mdhi_nnleituraatualinformada as leituraInformada, " // 10
					+ " leitFaixaFalsa.fxer_nnleiturafalsasuperior as leituraFalsaSuperior, " // 11
					+ " quadra.qdra_nnquadra as numeroQuadra, imov.imov_nnlote as lote, " // 12,13
					+ " imov.imov_nnsublote as sublote, imov.imov_id as idImovel, " // 14,15
					+ " func.func_id as idLeiturista, " // 16
					+ " leitFaixaFalsa.fxer_nnleituracorretainferior as leituraCorretaInferior, " // 17
					+ " leitFaixaFalsa.fxer_nnleituracorretasuperior as leituraCorretaSuperior, " // 18
					+ " mdhi.mdhi_dtleituraatualinformada as dataLeitura, " // 19
					+ " leitAnorm.ltan_dsleituraanormalidade as leituraAnormalidade, " // 20
					+ " leitSit.ltst_dsleiturasituacao as situacaoLeitura " // 21
					+ " FROM micromedicao.leitura_faixa_falsa leitFaixaFalsa "
					+ " INNER JOIN micromedicao.medicao_historico mdhi "
					+ " on mdhi.mdhi_id = leitFaixaFalsa.mdhi_id "
					+ " INNER JOIN cadastro.imovel imov "
					+ " on imov.imov_id = mdhi.imov_id or imov.imov_id = mdhi.lagu_id "
					+ " INNER JOIN cadastro.setor_comercial stcm "
					+ " on stcm.stcm_id = imov.stcm_id "
					+ " INNER JOIN cadastro.quadra quadra "
					+ " on quadra.qdra_id = imov.qdra_id "
					+ " INNER JOIN micromedicao.rota rota "
					+ " on rota.rota_id = quadra.rota_id "
					+ " INNER JOIN faturamento.faturamento_grupo ftgr "
					+ " on ftgr.ftgr_id = rota.ftgr_id "
					+ " LEFT OUTER JOIN cadastro.funcionario func "
					+ " on func.func_id = mdhi.func_id "
					+ " LEFT OUTER JOIN cadastro.empresa empresa "
					+ " on empresa.empr_id = func.empr_id "
					+ " INNER JOIN cadastro.localidade loc "
					+ " on loc.loca_id = imov.loca_id "
					+ " LEFT OUTER JOIN micromedicao.leitura_anormalidade leitAnorm "
					+ " on leitAnorm.ltan_id = mdhi.ltan_idleitanorminformada "
					+ " LEFT OUTER JOIN micromedicao.leitura_situacao leitSit "
					+ " on leitSit.ltst_id = mdhi.ltst_idleiturasituacaoatual "
					+ " WHERE mdhi.mdhi_amleitura = :anoMesReferencia "
					+ " ORDER BY idGrupo, idEmpresa, idLocalidade, codSetor, numeroQuadra, lote, sublote ";

			retorno = session.createSQLQuery(consulta).addScalar("idGrupo",
					Hibernate.INTEGER).addScalar("descricaoGrupo",
					Hibernate.STRING).addScalar("idEmpresa", Hibernate.INTEGER)
					.addScalar("nomeEmpresa", Hibernate.STRING).addScalar(
							"idLocalidade", Hibernate.INTEGER).addScalar(
							"nomeLocalidade", Hibernate.STRING).addScalar(
							"idSetor", Hibernate.INTEGER).addScalar("codSetor",
							Hibernate.INTEGER).addScalar("nomeSetor",
							Hibernate.STRING).addScalar("leituraFalsaInferior",
							Hibernate.INTEGER).addScalar("leituraInformada",
							Hibernate.INTEGER).addScalar(
							"leituraFalsaSuperior", Hibernate.INTEGER)
					.addScalar("numeroQuadra", Hibernate.INTEGER).addScalar(
							"lote", Hibernate.SHORT).addScalar("sublote",
							Hibernate.SHORT).addScalar("idImovel",
							Hibernate.INTEGER).addScalar("idLeiturista",
							Hibernate.INTEGER).addScalar(
							"leituraCorretaInferior", Hibernate.INTEGER)
					.addScalar("leituraCorretaSuperior", Hibernate.INTEGER)
					.addScalar("dataLeitura", Hibernate.DATE).addScalar(
							"leituraAnormalidade", Hibernate.STRING).addScalar(
							"situacaoLeitura", Hibernate.STRING).setInteger(
							"anoMesReferencia", anoMesReferencia).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0613] - Registrar Leituras e Anormalidades
	 * 
	 * Retorna a quantidade de imóveis com faixa falsa
	 * 
	 * @author Rafael Corrêa
	 * @date 18/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelFaixaFalsaCount(Integer anoMesReferencia)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "SELECT COUNT(leitFaixaFalsa.mdhi_id) as count "
					+ " FROM micromedicao.leitura_faixa_falsa leitFaixaFalsa "
					+ " INNER JOIN micromedicao.medicao_historico mdhi "
					+ " on mdhi.mdhi_id = leitFaixaFalsa.mdhi_id "
					+ " WHERE mdhi.mdhi_amleitura = :anoMesReferencia ";

			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"count", Hibernate.INTEGER).setInteger("anoMesReferencia",
					anoMesReferencia).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 06/06/2007
	 * 
	 * @param idImovel
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer obterMaxAMFaturamentoConsumoHistoricoDoImovel(
			Integer idImovel) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "max(consumoHistorico.referenciaFaturamento) "
					+ "from "
					+ "gcom.micromedicao.consumo.ConsumoHistorico consumoHistorico "
					+ "left join consumoHistorico.ligacaoTipo ligacaoTipo "
					+ "left join consumoHistorico.imovel imovel "
					+ "where imovel.id = :idImovel "
					+ "and ligacaoTipo.id = :idLigacaoTipo ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setInteger("idLigacaoTipo",
					LigacaoTipo.LIGACAO_AGUA).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 08/06/2007
	 * 
	 * @param idImovel
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterDadosHidrometro(Integer idImovel)
			throws ErroRepositorioException {

		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select hidrometro.numero, hidrometroMarca.descricaoAbreviada, "
					+ "hidrometroCapacidade.descricaoAbreviada,hidrometroDiametro.descricaoAbreviada, "
					+ "hidrometroLocalInstalacao.descricaoAbreviada, medicaoHistorico.dataLeituraAtualInformada, "
					+ "hidrometro.numeroDigitosLeitura "
					+ "from "
					+ "gcom.micromedicao.medicao.MedicaoHistorico medicaoHistorico "
					+ "left join medicaoHistorico.hidrometroInstalacaoHistorico as hidrometroInstalacaoHistorico "
					+ "left join medicaoHistorico.ligacaoAgua as ligacaoagua "
					+ "left join hidrometroInstalacaoHistorico.hidrometro as hidrometro "
					+ "left join hidrometro.hidrometroMarca as hidrometroMarca "
					+ "left join hidrometro.hidrometroCapacidade as hidrometroCapacidade "
					+ "left join hidrometro.hidrometroDiametro as hidrometroDiametro "
					+ "left join hidrometroInstalacaoHistorico.hidrometroLocalInstalacao as hidrometroLocalInstalacao "
					+ "where ligacaoagua.id = :idImovel and hidrometroInstalacaoHistorico.dataRetirada is null";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;
	}

	/**
	 * @author Vivianne Sousa
	 * @date 13/06/2007
	 * 
	 * @param idImovel
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterRotaESequencialRotaDoImovel(Integer idImovel)
			throws ErroRepositorioException {

		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select rota.codigo, " + "imovel.numeroSequencialRota "
					+ "from Imovel imovel " + "left join imovel.quadra quadra "
					+ "left join quadra.rota rota "
					+ "where imovel.id = :idImovel";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;
	}

	/**
	 * [UC0623] Gerar Resumo de Metas CAERN
	 * 
	 * @author Sávio Luiz
	 * @date 15/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Integer pesquisarConsumoFaturado(Integer idImovel,
			Integer tipoLigacao, Integer idConsumoTipoMediaImovel,
			Integer idConsumoTipoMediaHidrometro, Integer amArrecadacao)
			throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select cshi.numeroConsumoFaturadoMes "
					+ "from ConsumoHistorico cshi "
					+ "where cshi.imovel.id = :idImovel and cshi.ligacaoTipo.id = :tipoLigacao AND "
					+ " cshi.referenciaFaturamento = :amArrecadacao ";
			if (idConsumoTipoMediaImovel != null
					&& !idConsumoTipoMediaImovel.equals("")) {
				if (idConsumoTipoMediaHidrometro != null
						&& !idConsumoTipoMediaHidrometro.equals("")) {
					consulta += "and (cshi.consumoTipo.id = "
							+ idConsumoTipoMediaImovel;
					consulta += " or cshi.consumoTipo.id = "
							+ idConsumoTipoMediaHidrometro + ")";
				} else {
					consulta += "and cshi.consumoTipo.id = "
							+ idConsumoTipoMediaImovel;
				}

			}

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel)
					.setInteger("tipoLigacao", tipoLigacao).setInteger(
							"amArrecadacao", amArrecadacao).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * selecionar os movimentos roteiros empresas.
	 * 
	 * [UC0627] Gerar Arquivo Texto para Leituristas
	 * 
	 * @author Pedro Alexandre
	 * @date 03/08/2007
	 * 
	 * @param idRoteiroEmpresa
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarMovimentoRoteiroEmpresa(
			Integer idRoteiroEmpresa, Integer anoMesFaturamento,
			Integer idFaturamentoGrupo) throws ErroRepositorioException {

		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select loca.id, " + "mrem.codigoSetorComercial, "
					+ "mrem.numeroQuadra, " + "mrem.numeroLoteImovel, "
					+ "mrem.numeroSubloteImovel, " + "medt.id, " + "imov.id, "
					+ "iper.id, " + "mrem.nomeCliente, "
					+ "mrem.enderecoImovel, " + "himc.id, "
					+ "mrem.numeroHidrometro, " + "hicp.id, " + "hili.id, "
					+ "mrem.dataInstalacaoHidrometro, " + "hipr.id, "
					+ "last.id, " + "lest.id, "
					+ "mrem.descricaoAbreviadaCategoriaImovel, "
					+ "mrem.quantidadeEconomias, "
					+ "mrem.numeroLeituraAnterior, "
					+ "mrem.numeroFaixaLeituraEsperadaInicial, "
					+ "mrem.numeroFaixaLeituraEsperadaFinal, " + "fun.id "
					+ "from MovimentoRoteiroEmpresa mrem "
					+ "left join mrem.roteiroEmpresa roem "
					+ "left join roem.leiturista leitu "
					+ "left join leitu.funcionario fun"
					+ "left join mrem.localidade loca "
					+ "left join mrem.medicaoTipo medt "
					+ "left join mrem.imovel imov "
					+ "left join imov.imovelPerfil iper "
					+ "left join mrem.hidrometroMarca himc "
					+ "left join mrem.hidrometroCapacidade hicp "
					+ "left join mrem.hidrometroLocalInstalacao hili "
					+ "left join mrem.hidrometroProtecao hipr "
					+ "left join mrem.ligacaoAguaSituacao last "
					+ "left join mrem.ligacaoEsgotoSituacao lest "
					+ "left join mrem.faturamentoGrupo ftgr "
					+ "where roem.id = :idRoteiroEmpresa and "
					+ "mrem.anoMesMovimento = :anoMesFaturamento and "
					+ "ftgr.id = :idFaturamentoGrupo " + "";

			retorno = session.createQuery(consulta).setInteger(
					"idRoteiroEmpresa", idRoteiroEmpresa).setInteger(
					"anoMesFaturamento", anoMesFaturamento).setInteger(
					"idFaturamentoGrupo", idFaturamentoGrupo).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0627] Gerar Arquivo Texto para Leituristas
	 * 
	 * Pesquisa a quantidade de setores comercias por roteiro empresa.
	 * 
	 * [FS0004] Verificar Quantidade de setores comercias.
	 * 
	 * @author Pedro Alexandre
	 * @date 06/08/2007
	 * 
	 * @param idRoteiroEmpresa
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeSetorComercialPorRoteiroEmpresa(
			Integer idRoteiroEmpresa, Integer anoMesFaturamento,
			Integer idFaturamentoGrupo, Integer idLeituraTipo)
			throws ErroRepositorioException {

		Collection retorno = null;
		Integer qtdSetoresComercias = 0;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select distinct(mrem.codigoSetorComercial) "
					+ "from MovimentoRoteiroEmpresa mrem "
					+ "left join mrem.roteiroEmpresa roem "
					+ "left join mrem.faturamentoGrupo ftgr "
					+ "where roem.id = :idRoteiroEmpresa and "
					+ "mrem.anoMesMovimento = :anoMesFaturamento and "
					+ "ftgr.id = :idFaturamentoGrupo and mrem.leituraTipo.id = :idLeituraTipo";

			retorno = session.createQuery(consulta).setInteger(
					"idRoteiroEmpresa", idRoteiroEmpresa).setInteger(
					"anoMesFaturamento", anoMesFaturamento).setInteger(
					"idFaturamentoGrupo", idFaturamentoGrupo).setInteger(
					"idLeituraTipo", idLeituraTipo).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		if (retorno != null && !retorno.isEmpty()) {
			qtdSetoresComercias = retorno.size();
		}
		return qtdSetoresComercias;
	}

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0005] Gerar Relação(ROL) em TXT - CAER
	 * 
	 * @author Sávio Luiz
	 * @date 13/08/2007
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisParaLeituraPorColecaoRotaCAER(Rota rota,
			Integer idLeituraTipo) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String consulta = "select imovel.id,"// 0
					+ "localidade.id,"// 1
					+ "setorComercial.codigo,"// 2
					+ "quadra.numeroQuadra,"// 3
					+ "imovel.lote,"// 4
					+ "imovel.subLote,"// 5
					+ "imovelPerfil.id,"// 6
					+ "ligacaoAgua.id,"// 7
					+ "hidInstHistoricoAgua.id,"// 8
					+ "hidInstHistoricoPoco.id,"// 9
					+ "rota.id,"// 10
					+ "rota.indicadorFiscalizarSuprimido,"// 11
					+ "rota.indicadorFiscalizarCortado,"// 12
					+ "rota.indicadorGerarFiscalizacao,"// 13
					+ "rota.indicadorGerarFalsaFaixa,"// 14
					+ "rota.percentualGeracaoFiscalizacao,"// 15
					+ "rota.percentualGeracaoFaixaFalsa,"// 16
					+ "empresa.id,"// 17
					+ "empresa.descricaoAbreviada,"// 18
					+ "empresa.email,"// 19
					+ "faturamentoGrupo.id,"// 20
					+ "faturamentoGrupo.descricao,"// 21
					+ "leituraTipo.id,"// 22
					+ "ligacaoAguaSituacao.id,"// 23
					+ "ligacaoEsgotoSituacao.id, "// 24
					+ "faturamentoSituacaoTipo.indicadorParalisacaoLeitura, "// 25
					+ "medTipoAgua.id, "// 26
					+ "medTipoPoco.id, "// 27
					+ "gerenciaRegional.id, "// 28
					+ "gerenciaRegional.nome, "// 29
					+ "localidade.descricao, "// 30
					+ "ligacaoAguaSituacao.descricao, "// 31
					+ "ligacaoAgua.numeroLacre, "// 32
					+ "hidroAgua.numero, "// 33
					+ "imovel.numeroSequencialRota, "// 34
					+ "rota.codigo, "// 35
					+ "hidroPoco.numero, "// 36
					+ "ligacaoAguaSituacao.descricaoAbreviado,"// 37
					+ "logradouro.id,"// 38
					+ "logradouro.nome,"// 39
					+ "imovel.numeroImovel,"// 40
					+ "imovel.complementoEndereco,"// 41
					+ "hidroAgua.numeroDigitosLeitura,"// 42
					+ "hidroPoco.numeroDigitosLeitura,"// 43
					+ "logTit.descricaoAbreviada, "// 44
					+ "logTip.descricaoAbreviada, "// 45
					+ "ligacaoAguaSituacao.indicadorFaturamentoSituacao, "// 46
					+ "ligacaoEsgotoSituacao.indicadorFaturamentoSituacao, "// 47
					+ "bairro.id, "// 48
					+ "bairro.nome "// 49
					+ "from Imovel imovel "
					+ "left join imovel.localidade localidade "
					+ "left join imovel.setorComercial setorComercial "
					+ "left join imovel.quadra quadra "
					+ "left join imovel.imovelPerfil imovelPerfil "
					+ "left join imovel.ligacaoAgua ligacaoAgua "
					+ "left join imovel.hidrometroInstalacaoHistorico hidInstHistoricoPoco "
					+ "left join ligacaoAgua.hidrometroInstalacaoHistorico hidInstHistoricoAgua "
					+ "left join hidInstHistoricoAgua.hidrometro hidroAgua "
					+ "left join hidInstHistoricoAgua.medicaoTipo medTipoAgua "
					+ "left join hidInstHistoricoPoco.medicaoTipo medTipoPoco "
					+ "left join hidInstHistoricoPoco.hidrometro hidroPoco "
					+ "left join quadra.rota rota "
					+ "left join rota.empresa empresa "
					+ "left join rota.faturamentoGrupo faturamentoGrupo "
					+ "left join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "
					+ "left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "
					+ "left join imovel.faturamentoSituacaoTipo faturamentoSituacaoTipo "
					+ "left join rota.leituraTipo leituraTipo "
					+ "left join localidade.gerenciaRegional gerenciaRegional "
					+ "left join imovel.logradouroBairro logBairro "
					+ "left join logBairro.logradouro logradouro "
					+ "left join logradouro.logradouroTitulo logTit "
					+ "left join logradouro.logradouroTipo logTip "
					+ "left join logBairro.bairro bairro "
					+ "where rota.id = :idRota "
					+ "AND imovel.rotaAlternativa IS NULL "
					+ "AND imovelPerfil.indicadorGerarDadosLeitura = 1 "
					+ "AND leituraTipo.id = :idLeituraTipo "
					+ "order by rota.id,imovel.numeroSequencialRota ";

			retorno = session.createQuery(consulta).setInteger("idRota",
					rota.getId()).setInteger("idLeituraTipo", idLeituraTipo)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0005] Gerar Relação(ROL) em TXT - CAER
	 * 
	 * @author Sávio Luiz
	 * @date 24/08/2007
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosLocalProtecaoTipoLigacaoAgua(Integer idImovel)
			throws ErroRepositorioException {
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select hli.descricao," + // 0
					" hp.descricao " // 1
					+ "from Imovel imovel "
					+ "left join imovel.ligacaoAgua la "
					+ "left join la.hidrometroInstalacaoHistorico hih "
					+ "left join hih.hidrometroLocalInstalacao hli "
					+ "left join hih.hidrometroProtecao hp "
					+ "left join hih.medicaoTipo mt "
					+ "where imovel.id = :idImovel and "
					+ "mt.id = :idMedicaoTipo";
			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idImovel", idImovel.intValue()).setInteger(
					"idMedicaoTipo", MedicaoTipo.LIGACAO_AGUA.intValue())
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0005] Gerar Relação(ROL) em TXT - CAER
	 * 
	 * @author Sávio Luiz
	 * @date 24/08/2007
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosLocalProtecaoTipoTipoPoco(Integer idImovel)
			throws ErroRepositorioException {
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select hli.descricao," + // 0
					" hp.descricao " // 1
					+ "from Imovel imovel "
					+ "left join imovel.hidrometroInstalacaoHistorico hih "
					+ "left join hih.hidrometroLocalInstalacao hli "
					+ "left join hih.hidrometroProtecao hp "
					+ "left join hih.medicaoTipo mt "
					+ "where imovel.id = :idImovel and "
					+ "mt.id = :idMedicaoTipo";
			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idImovel", idImovel.intValue()).setInteger(
					"idMedicaoTipo", MedicaoTipo.POCO.intValue())
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;

	}

	/**
	 * [UC00082] Registrar Leituras e Anormalidades
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 29/08/2007
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<MovimentoRoteiroEmpresa> pesquisarColecaoMovimentoRoteiroEmpresa(
			Integer idGrupoFaturamento) throws ErroRepositorioException {
		Collection<MovimentoRoteiroEmpresa> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select new MovimentoRoteiroEmpresa (movimentoRoteiroEmpresa.imovel,"
					+ "movimentoRoteiroEmpresa.localidade, "
					+ "movimentoRoteiroEmpresa.codigoSetorComercial, "
					+ "movimentoRoteiroEmpresa.numeroQuadra, "
					+ "movimentoRoteiroEmpresa.numeroLoteImovel, "
					+ "movimentoRoteiroEmpresa.numeroSubloteImovel, "
					+ "movimentoRoteiroEmpresa.medicaoTipo, "
					+ "movimentoRoteiroEmpresa.numeroLeituraHidrometro, "
					+ "movimentoRoteiroEmpresa.leituraAnormalidade, "
					+ "movimentoRoteiroEmpresa.tempoLeitura, "
					+ "movimentoRoteiroEmpresa.indicadorConfirmacaoLeitura) "

					+ "from MovimentoRoteiroEmpresa movimentoRoteiroEmpresa "
					+ "inner join movimentoRoteiroEmpresa.faturamentoGrupo fatGrupo "
					+ "where fatGrupo.id = :idGrupoFaturamento and "
					+ "movimentoRoteiroEmpresa.dataHoraProcessamento is null";

			retorno = session.createQuery(consulta).setInteger(
					"idGrupoFaturamento", idGrupoFaturamento).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;

	}

	/**
	 * pesquisa uma coleção de roteiroempresa(s) de acordo com o código
	 * 
	 * @param idLocalidade
	 *            Descrição do parâmetro
	 * @param codigoSetorComercial
	 *            Descrição do parâmetro
	 * @param idLeiturista
	 *            Descrição do parâmetro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarRoteiroEmpresa(String idEmpresa,
			String idLocalidade, String codigoSetorComercial,
			String idLeiturista, String indicadorUso, Integer numeroPagina)
			throws ErroRepositorioException {

		// Collection<Hidrometro> retorno = new ArrayList();
		Collection roteirosEmpresa = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select distinct(re.roem_id) as id, "
					+ // [0] id roteiro empresa
					"re.empr_id as idEmpresa, "
					+ // [1] id da empresa
					"emp.empr_nmempresa as nomeEmpresa, "
					+ // [2] nome da empresa
					"loc.loca_id as idLocalidade, "
					+ // [3] id da localidade
					"loc.loca_nmlocalidade as nomeLocalidade, "
					+ // [4] nome da localidade
					"re.leit_id as idLeiturista, "
					+ // [5] id leiturista
					"cli.clie_nmcliente as nomeCliente, "
					+ // [6] nome do cliente
					"func.func_nmfuncionario as nomeFuncionario, "
					+ // [7] nome do funcionario
					"sc.stcm_cdsetorcomercial as codigoSetor "
					+ "from micromedicao.roteiro_empresa re "
					+ "LEFT OUTER JOIN cadastro.quadra q  on re.roem_id = q.roem_id "
					+ "LEFT OUTER JOIN cadastro.setor_comercial sc on q.stcm_id = sc.stcm_id "
					+ "LEFT OUTER JOIN cadastro.empresa emp  on re.empr_id = emp.empr_id "
					+ "LEFT OUTER JOIN cadastro.localidade loc  on sc.loca_id = loc.loca_id "
					+ "LEFT OUTER JOIN micromedicao.leiturista lei on re.leit_id = lei.leit_id "
					+ "LEFT OUTER JOIN cadastro.cliente cli on cli.clie_id = lei.clie_id "
					+ "LEFT OUTER JOIN cadastro.funcionario func on func.func_id = lei.func_id "
					+ "WHERE (1 = 1) "
					+ ((idLocalidade != null && !idLocalidade.equals("")) ? " and sc.loca_id = "
							+ idLocalidade
							: "")
					+ ((codigoSetorComercial != null
							&& !codigoSetorComercial.equals("") && !codigoSetorComercial
							.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) ? " and sc.stcm_id = "
							+ codigoSetorComercial
							: "")
					+ ((idLeiturista != null && !idLeiturista.equals("") && !idLeiturista
							.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) ? " and re.leit_id = "
							+ idLeiturista
							: "")
					+ ((idEmpresa != null && !idEmpresa.equals("") && !idEmpresa
							.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) ? " and re.empr_id = "
							+ idEmpresa
							: "");

			roteirosEmpresa = session.createSQLQuery(consulta).addScalar("id",
					Hibernate.INTEGER)
					.addScalar("idEmpresa", Hibernate.INTEGER).addScalar(
							"nomeEmpresa", Hibernate.STRING).addScalar(
							"idLocalidade", Hibernate.INTEGER).addScalar(
							"nomeLocalidade", Hibernate.STRING).addScalar(
							"idLeiturista", Hibernate.INTEGER).addScalar(
							"nomeCliente", Hibernate.STRING).addScalar(
							"nomeFuncionario", Hibernate.STRING).addScalar(
							"codigoSetor", Hibernate.INTEGER).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return roteirosEmpresa;
	}

	/**
	 * Verifica a quantidade de registros retornados da pesquisa de roteiro
	 * empresa
	 * 
	 * [UC0370] - Filtrar Roteiro Empresa
	 * 
	 * @author Thiago Tenório
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
	public int pesquisarRoteiroEmpresaCount(String idEmpresa,
			String idLocalidade, String codigoSetorComercial,
			String idLeiturista, String indicadorUso)
			throws ErroRepositorioException {
		int retorno = 0;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "select COUNT(distinct(re.roem_id)) as count from micromedicao.roteiro_empresa re "
					+ "LEFT OUTER JOIN cadastro.quadra q  on re.roem_id = q.roem_id "
					+ "LEFT OUTER JOIN cadastro.setor_comercial sc on q.stcm_id = sc.stcm_id "
					+ "WHERE (1 = 1) "
					+ ((idLocalidade != null && !idLocalidade.equals("")) ? " and sc.loca_id = "
							+ idLocalidade
							: "")
					+ ((codigoSetorComercial != null
							&& !codigoSetorComercial.equals("") && !codigoSetorComercial
							.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) ? " and sc.stcm_id = "
							+ codigoSetorComercial
							: "")
					+ ((idLeiturista != null && !idLeiturista.equals("") && !idLeiturista
							.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) ? " and re.leit_id = "
							+ idLeiturista
							: "")
					+ ((idEmpresa != null && !idEmpresa.equals("") && !idEmpresa
							.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) ? " and re.empr_id = "
							+ idEmpresa
							: "");

			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"count", Hibernate.INTEGER).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0631] Processar Requisições do Dispositivo Móvel.
	 * 
	 * [SB0001] Baixar Arquivo Texto para o Leiturista.
	 * 
	 * 
	 * @author Thiago Nascimento
	 * @date 14/08/2007
	 * 
	 * @param imei
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] baixarArquivoTextoParaLeitura(long imei,
			Integer idServicoTipoCelular) throws ErroRepositorioException {
		Object[] retorno = new Object[2];
		byte[] arquivo = null;
		ArquivoTextoRoteiroEmpresa arquivoTextoRoteiro = null;
		Session session = HibernateUtil.getSession();

		String hdl = "FROM ArquivoTextoRoteiroEmpresa a where a.situacaoTransmissaoLeitura.id = 2 and"
				+ " a.numeroImei = "
				+ imei
				+ " and a.servicoTipoCelular.id = "
				+ idServicoTipoCelular;
		try {
			arquivoTextoRoteiro = (ArquivoTextoRoteiroEmpresa) session
					.createQuery(hdl).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		if (arquivoTextoRoteiro != null) {
			arquivo = arquivoTextoRoteiro.getArquivoTexto();
			
			StringBuilder sb = new StringBuilder();
			
			boolean ehtxt = arquivoTextoRoteiro.getNomeArquivo().toUpperCase().endsWith(".TXT");

			if(ehtxt){
			
				try {
					sb = (StringBuilder) IoUtil.transformarBytesParaObjeto(arquivo);
				} catch (IOException e) {
					throw new ErroRepositorioException(
							"Erro em Transformar Array de Byte em Object");
				} catch (ClassNotFoundException e) {
					throw new ErroRepositorioException(
							"Erro em Transformar Array de Byte em Object");
				}
				
				arquivo = sb.toString().getBytes();
			}
		}

		retorno[0] = arquivo;
		retorno[1] = arquivoTextoRoteiro;

		return retorno;
	}

	/**
	 * [UC0631] Processar Requisições do Dispositivo Móvel.
	 * 
	 * Atualizar Situação do Arquivo Texto.
	 * 
	 * 
	 * @author Thiago Nascimento
	 * @date 14/08/2007
	 * 
	 * Alterado por Sávio Luiz
	 * @date 05/04/2010
	 * 
	 * @param imei
	 * @param situacaoAnterior
	 * @param situacaoNova
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoTextoEnviado(long imei, int situacaoAnterior,
			int situacaoNova) throws ErroRepositorioException {

		
		Session session = HibernateUtil.getSession();
		
		Collection<Integer> idsArquivos = null;
		String consulta = null;

		try {
			 // verifica a situação anterior da tabela arquivo texto roteiro empresa dividido
			 consulta = "select id " 
			 		    + " from ArquivoTextoRoteiroEmpresaDivisao a "
						+ " where a.numeroImei = :imei and "
						+ " a.situacaoTransmissaoLeitura.id = :idSituacaoTransmissaoLeituraAnterior";
				
			 idsArquivos = session.createQuery(consulta)
			 				.setLong("imei",imei)
			 				.setInteger("idSituacaoTransmissaoLeituraAnterior",situacaoAnterior)			
			 				.list();
			 if(idsArquivos != null && !idsArquivos.isEmpty()){
				 String sql = "update gcom.micromedicao.ArquivoTextoRoteiroEmpresaDivisao "
						+ " set sitl_id = :idSituacaoTransmissaoLeituraNova "
						+ ", tred_tmultimaalteracao = :data "
						+ " where tred_id in (:ids)";

				session.createQuery(sql)
				  .setParameterList("ids", idsArquivos)
				  .setInteger("idSituacaoTransmissaoLeituraNova",situacaoNova)
				  .setTimestamp("data", new Date())
						.executeUpdate();
			 }
			 
			 //verifica a situação anterior da tabela arquivo texto roteiro empresa
			 consulta = "select id " 
			 		    + " from ArquivoTextoRoteiroEmpresa a "
						+ " where a.numeroImei = :imei and "
						+ " a.situacaoTransmissaoLeitura.id = :idSituacaoTransmissaoLeituraAnterior";
				
			 idsArquivos = session.createQuery(consulta)
			 				.setLong("imei",imei)
			 				.setInteger("idSituacaoTransmissaoLeituraAnterior",situacaoAnterior)			
			 				.list();
			 if(idsArquivos != null && !idsArquivos.isEmpty()){
				 String sql = "update gcom.micromedicao.ArquivoTextoRoteiroEmpresa "
						+ " set sitl_id = :idSituacaoTransmissaoLeituraNova "
						+ ", ultimaAlteracao = :data "
						+ " where txre_id in (:ids)";

				session.createQuery(sql)
				  .setParameterList("ids", idsArquivos)
				  .setInteger("idSituacaoTransmissaoLeituraNova",situacaoNova)
				  .setTimestamp("data", new Date())
						.executeUpdate();
			 }
			
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
//		FiltroArquivoTextoRoteiroEmpresa filtro = new FiltroArquivoTextoRoteiroEmpresa();
//		filtro.adicionarParametro(new ParametroSimples(
//				FiltroArquivoTextoRoteiroEmpresa.SITUACAO_TRANS_LEITURA_ID,
//				situacaoAnterior));
//		filtro.adicionarParametro(new ParametroSimples(
//				FiltroArquivoTextoRoteiroEmpresa.IMEI, imei));
//
//		Collection<ArquivoTextoRoteiroEmpresa> colArquivos = RepositorioUtilHBM
//				.getInstancia().pesquisar(filtro,
//						ArquivoTextoRoteiroEmpresa.class.getName());
//		ArquivoTextoRoteiroEmpresa arquivo = (ArquivoTextoRoteiroEmpresa) Util
//				.retonarObjetoDeColecao(colArquivos);
//
//		if (arquivo != null) {
//			SituacaoTransmissaoLeitura situacao = new SituacaoTransmissaoLeitura();
//			situacao.setId(situacaoNova);
//
//			arquivo.setSituacaoTransmissaoLeitura(situacao);
//			arquivo.setUltimaAlteracao(new Date());
//
//			RepositorioUtilHBM.getInstancia().atualizar(arquivo);
//		}

	}
	
	/**
	 * [UC0631] Processar Requisições do Dispositivo Móvel.
	 * 
	 * Atualizar Situação do Arquivo Texto.
	 * 
	 * 
	 * @author Thiago Nascimento
	 * @date 14/08/2007
	 * 
	 * Alterado por Sávio Luiz
	 * @date 05/04/2010
	 * 
	 * @param imei
	 * @param situacaoAnterior
	 * @param situacaoNova
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoTextoEnviadoPorRota(Integer idRota, int situacaoAnterior,
			int situacaoNova) throws ErroRepositorioException {

		
		Session session = HibernateUtil.getSession();

		try {
			String sql = "update gcom.micromedicao.ArquivoTextoRoteiroEmpresa "
					+ " set sitl_id = :idSituacaoTransmissaoLeituraNova "
					+ ", txre_tmultimaalteracao = :data "
					+ " where rota_id = :idRota and "
					+ "sitl_id = :idSituacaoTransmissaoLeituraAnterior";

			session.createQuery(sql).
			  setInteger("idRota",idRota).
			  setInteger("idSituacaoTransmissaoLeituraAnterior",situacaoAnterior).
			  setInteger("idSituacaoTransmissaoLeituraNova",situacaoNova).
			  setTimestamp("data", new Date())
					.executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC0631] Processar Requisições do Dispositivo Móvel.
	 * 
	 * Atualizar Situação do Arquivo Texto.
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 05/04/2010
	 * 
	 * @param imei
	 * @param situacaoAnterior
	 * @param situacaoNova
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoTextoMenorSequencialLeitura(long imei, int situacaoAnterior,
			int situacaoNova,int idServicoTipoCelular) throws ErroRepositorioException {

		
		Session session = HibernateUtil.getSession();
		Collection<Integer> idsArquivos = null;
		String consulta = null;

		try {
			
			// verifica a situação anterior da tabela arquivo texto roteiro empresa dividido
			 consulta = "select a.id " 
			 		    + " from ArquivoTextoRoteiroEmpresaDivisao a "
			 		    + " inner join a.arquivoTextoRoteiroEmpresa atre "
						+ " where atre.situacaoTransmissaoLeitura.id = 3 and a.numeroImei = :imei and "
						+ " a.situacaoTransmissaoLeitura.id = :idSituacaoTransmissaoLeituraAnterior and "
						+ " a.numeroSequenciaArquivo = "
						+ " (select min(b.numeroSequenciaArquivo) "
						+ "  from ArquivoTextoRoteiroEmpresaDivisao b "
						+ "  inner join b.arquivoTextoRoteiroEmpresa atreb "
						+ "  where b.numeroImei = :imei and "
						+ "  b.situacaoTransmissaoLeitura.id = :idSituacaoTransmissaoLeituraAnterior and "
						+ "  atre.id = atreb.id) ";
				
			 idsArquivos = session.createQuery(consulta)
			 				.setLong("imei",imei)
			 				.setInteger("idSituacaoTransmissaoLeituraAnterior",situacaoAnterior)			
			 				.list();
			 if(idsArquivos != null && !idsArquivos.isEmpty()){
				 String sql = "update gcom.micromedicao.ArquivoTextoRoteiroEmpresaDivisao "
						+ " set sitl_id = :idSituacaoTransmissaoLeituraNova "
						+ ", tred_tmultimaalteracao = :data "
						+ " where tred_id in (:ids) ";

				session.createQuery(sql)
				  .setParameterList("ids", idsArquivos)
				  .setInteger("idSituacaoTransmissaoLeituraNova",situacaoNova)
				  .setTimestamp("data", new Date())
						.executeUpdate();
			 }else{
				 //Caso não tenha dados para arquivo divisão 
				 // então verifica a situação anterior da tabela arquivo texto roteiro empresa
				 
				 String sql = "update gcom.micromedicao.ArquivoTextoRoteiroEmpresa "
						+ " set sitl_id = :idSituacaoTransmissaoLeituraNova "
						+ ", txre_tmultimaalteracao = :data "
						+ " where txre_nnimei = :imei and "
						+ " stce_id = :idServicoTipoCelular and "
						+ "sitl_id = :idSituacaoTransmissaoLeituraAnterior and "
						+ "txre_nnsequencialeiturista = "
						+ "(select min(atre.numeroSequenciaLeitura) "
						+ " from gcom.micromedicao.ArquivoTextoRoteiroEmpresa atre "
						+ " where atre.numeroImei = :imei and "
						+ " atre.situacaoTransmissaoLeitura.id = :idSituacaoTransmissaoLeituraAnterior and "
						+ " stce_id = :idServicoTipoCelular "
						+ ")";

				session.createQuery(sql).
				  setLong("imei",imei).
				  setInteger("idSituacaoTransmissaoLeituraAnterior",situacaoAnterior).
				  setInteger("idSituacaoTransmissaoLeituraNova",situacaoNova).
				  setInteger("idServicoTipoCelular",idServicoTipoCelular).
				  setTimestamp("data", new Date())
						.executeUpdate();
			 }
			
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
	}

	/**
	* [UC0631] Processar Requisições do Dispositivo Móvel.
	*
	* [SB0002] Atualizar o movimento roteiro empresa.
	*
	*
	* @author Thiago Nascimento
	* @date 14/08/2007
	*
	* @param dados
	*
	* @throws ErroRepositorioException
	*/
	public void atualizarRoteiro(Collection<DadosMovimentacao> dados, Integer anoMesReferencia, boolean isCelular) 
		throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		Iterator<DadosMovimentacao> it = dados.iterator();
		
		try {
	
			while (it.hasNext()) {
		
				DadosMovimentacao dado = it.next();
				
				this.atualizarMovimentoRoteiroEmpresa(dado, anoMesReferencia, isCelular);
			}
		} 
		catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} 
		finally {
			HibernateUtil.closeSession(session);
		}
	}


	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 06/09/2007
	 * 
	 * @param idImovel
	 * @param anoMes
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer obterConsumoMedioEmConsumoHistorico(Integer idImovel,
			Integer idLigacaoTipo) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "consumoHistorico.consumoMedio "
					+ "from "
					+ "gcom.micromedicao.consumo.ConsumoHistorico consumoHistorico "
					+ "left join consumoHistorico.ligacaoTipo ligacaoTipo "
					+ "left join consumoHistorico.imovel imovel "
					+ "where imovel.id = :idImovel "
					+ "and ligacaoTipo.id = :idLigacaoTipo "
					+ "and consumoHistorico.referenciaFaturamento = "
					+ "(select max(consHist.referenciaFaturamento) from gcom.micromedicao.consumo.ConsumoHistorico consHist) ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setInteger("idLigacaoTipo",
					idLigacaoTipo).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	public void removerMovimentoRoteiroEmpresa(Integer anoMesCorrente,
			Integer idGrupoFaturamentoRota) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "delete "
					+ "MovimentoRoteiroEmpresa mrem "
					+ "where mrem.anoMesMovimento = :anoMes and mrem.faturamentoGrupo.id = :idFaturamentoGrupo";

			session.createQuery(consulta).setInteger("anoMes", anoMesCorrente)
					.setInteger("idFaturamentoGrupo", idGrupoFaturamentoRota)
					.executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * Pesquisa os roteiros empresas pelo grupo de faturamento
	 * 
	 * [UC0627] Gerar Arquivo Texto para Leitura
	 * 
	 * @author Pedro Alexandre
	 * @date 13/09/2007
	 * 
	 * @param idGrupoFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRoteiroEmpresaPorGrupoFaturamento(
			Integer idGrupoFaturamento) throws ErroRepositorioException {

		Collection<RoteiroEmpresa> roteirosEmpresa = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select roem " + "from RoteiroEmpresa roem "
					+ "inner join fetch roem.empresa empr "
					+ "inner join fetch roem.leiturista leit "
					+ "where roem.id in (select qdra.roteiroEmpresa.id "
					+ "from Quadra qdra " + "inner join qdra.rota rota "
					+ "inner join rota.faturamentoGrupo ftgr "
					+ "where ftgr.id = :idFaturamentoGrupo)"

			;

			roteirosEmpresa = session.createQuery(consulta).setInteger(
					"idFaturamentoGrupo", idGrupoFaturamento).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return roteirosEmpresa;
	}

	/**
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 * 
	 * @author Raphael Rossiter, Raphael Rossiter
	 * @date 17/09/2007, 25/08/2009
	 * 
	 * @return Collection
	 * @exception ErroRepositorioException
	 */
	public Collection pesquisarImovelConsistirLeituraPorRota(Rota rota)
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select im.id," // 0
					+ "im.ligacaoAguaSituacao.id, " // 1
					+ "im.ligacaoEsgotoSituacao.id, " // 2
					+ "li.id, " // 3
					+ "hia.id, " // 4
					+ "hdra.id, " // 5
					+ "hdra.numeroDigitosLeitura, " // 6
					+ "fst.id," // 7
					+ "im.pocoTipo.id, " // 8
					+ "hie.id, " // 9
					+ "hdre.id, " // 10
					+ "im.quadra.rota.indicadorAjusteConsumo, " // 11
					+ "im.ligacaoAgua.numeroConsumoMinimoAgua, " // 12
					+ "im.indicadorImovelCondominio, " // 13
					+ "fst.indicadorParalisacaoFaturamento, " // 14
					+ "im.indicadorDebitoConta, " // 15
					+ "le.id, " // Ligacao Esgoto //16
					+ "le.consumoMinimo, " // 17
					+ "hia.dataInstalacao, " // 18
					+ "ct.id, " // 19
					+ "le.percentualAguaConsumidaColetada, " // 20
					+ "im.quantidadeEconomias, " // 21
					+ "hdre.numeroDigitosLeitura, " // 22
					+ "hie.dataInstalacao, "// 23
					+ "fst.indicadorValidoAgua, " // 24
					+ "fst.indicadorValidoEsgoto, " // 25
					+ "esferaPoderClieResp.id, " // 26
					+ "im.ligacaoAguaSituacao.indicadorFaturamentoSituacao, " // 27
					+ "im.ligacaoEsgotoSituacao.indicadorFaturamentoSituacao, "// 28
					+ "im.ligacaoAguaSituacao.consumoMinimoFaturamento, " // 29
					+ "im.ligacaoEsgotoSituacao.volumeMinimoFaturamento, "// 30
					+ "im.ligacaoAguaSituacao.indicadorAbastecimento, " // 31
					+ "hia.numeroLeituraInstalacao," // 32
					+ "hie.numeroLeituraInstalacao, " // 33
					/**
					 * TODO : COSANPA Adicionando parametros da ligacao de agua
					 */
					+ "li.dataLigacao, " // 34
					+ "le.dataLigacao, " // 35
					+ "li.numeroConsumoMinimoAgua " //36
					+ "from Imovel im "
					+ "inner join im.quadra.rota.faturamentoGrupo "
					+ "inner join im.consumoTarifa ct "
					+ "left join im.ligacaoAguaSituacao las  "
					+ "left join im.ligacaoEsgotoSituacao les  "
					+ "left join im.ligacaoAgua li "
					+ "left join im.ligacaoAgua.hidrometroInstalacaoHistorico hia "
					+ "left join im.ligacaoAgua.hidrometroInstalacaoHistorico.hidrometro hdra "
					+ "left join im.hidrometroInstalacaoHistorico hie "
					+ "left join im.hidrometroInstalacaoHistorico.hidrometro hdre "
					+ "left join im.faturamentoSituacaoTipo fst "
					+ "left join im.ligacaoEsgoto le "
					+ "left join im.clienteImoveis clieImovResp with (clieImovResp.clienteRelacaoTipo.id = :clienteResponsavel and clieImovResp.dataFimRelacao is null) "
					+ "left join clieImovResp.cliente clieResp "
					+ "left join clieResp.clienteTipo tipoClieResp "
					+ "left join tipoClieResp.esferaPoder esferaPoderClieResp "

					// SELEÇÃO POR ROTA
					+ "WHERE im.quadra.rota.id = :rota " //"and im.id = :idImovel "
					+ "AND im.rotaAlternativa IS NULL AND im.indicadorExclusao <> 1 "

					// VERIFICANDO SE O IMÓVEL ESTÁ DISPONÍVEL PARA GERAÇÃO DE
					// CONSUMO
					+ "AND ((las.indicadorFaturamentoSituacao = :faturamentoAgua OR "
					+ "les.indicadorFaturamentoSituacao = :faturamentoEsgoto) "

					// VERIFICA SE O IMÓVEL POSSUI HIDROMETRO NA LIGAÇÃO DE AGUA
					// OU DE POÇO
					+ "OR  (li.hidrometroInstalacaoHistorico <> null OR "
					+ "im.hidrometroInstalacaoHistorico <> null)) ";

			retorno = session.createQuery(consulta).setInteger(
					"clienteResponsavel",
					ClienteRelacaoTipo.RESPONSAVEL.intValue()).setInteger(
					"rota", rota.getId()).setShort("faturamentoAgua",
					LigacaoAguaSituacao.FATURAMENTO_ATIVO).setShort(
					"faturamentoEsgoto",
					LigacaoEsgotoSituacao.FATURAMENTO_ATIVO).
					//setInteger( "idImovel", 333093 ).					
					list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [FS005] - Verificar existência do arquivo texto roteiro empresa.
	 * 
	 * Caso já exista um arquivo texto para o mês de referência informado, mesmo
	 * roteiro empresa, mesmo grupo de faturamento e sua situação de leitura
	 * transmissão esteja liberado, exclui o arquivo correspondente e retorna
	 * pra o caso se uso que chamou esta funcionalidade.
	 * 
	 * [UC0627] Gerar Arquivo Texto para Leiturista
	 * 
	 * @author Pedro Alexandre
	 * @date 17/09/2007
	 * 
	 * @param anoMesReferencia
	 * @param idRoteiroEmpresa
	 * @param idGrupoFaturamento
	 * @throws ErroRepositorioException
	 */
	public boolean excluirArquivoTextoParaLeiturista(Integer anoMesReferencia,
			Integer idRoteiroEmpresa, Integer idGrupoFaturamento)
			throws ErroRepositorioException {

		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// Retorno Consulta
		int qtdRegistrosDeletados = 0;

		// Cria a variável que vai conter o hql
		String consulta;

		try {

			consulta = "delete ArquivoTextoRoteiroEmpresa where "
					+ "anoMesReferencia = :anoMesReferencia "
					+ "and roteiroEmpresa = :idRoteiroEmpresa "
					+ "and faturamentoGrupo = :idFaturamentoGrupo "
					+ "and situacaoTransmissaoLeitura = :situacao";

			// Executa o hql
			qtdRegistrosDeletados = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).setInteger(
					"idRoteiroEmpresa", idRoteiroEmpresa).setInteger(
					"idFaturamentoGrupo", idGrupoFaturamento).setInteger(
					"situacao", SituacaoTransmissaoLeitura.DISPONIVEL)
					.executeUpdate();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return qtdRegistrosDeletados > 0;
	}

	// caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public String[] pesquisarQuantidadeRotas(String codigoSetorComercial,
			String rotaInicial, String rotaFinal, String idLocalidade,
			String idCobrancaAcao, Integer idCriterio)
			throws ErroRepositorioException {

		String[] retorno = new String[3];
		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		// Cria a variável que vai conter o hql
		String consulta;
		try {

			consulta = "select count(rota.id) from Rota rota "
					+ " inner join rota.setorComercial setor "
					+ " inner join setor.localidade localidade"
					+ " where rota.codigo between :rotaInicial and :rotaFinal "
					+ " and setor.codigo = :setorComercial "
					+ " and localidade.id = :idLocalidade";

			// Executa o hql
			Integer qtdRotas = (Integer) session.createQuery(consulta)
					.setInteger("rotaInicial", new Integer(rotaInicial))
					.setInteger("rotaFinal", new Integer(rotaFinal))
					.setInteger("setorComercial",
							new Integer(codigoSetorComercial)).setInteger(
							"idLocalidade", new Integer(idLocalidade))
					.setMaxResults(1).uniqueResult();

			String consultaComCriterio = "";
			Integer qtdComCriterio = 0;
			if (idCriterio != null) {
				consultaComCriterio = "select count(rota.id) from RotaAcaoCriterio rotaCriterio "
						+ " inner join rotaCriterio.rota rota"
						+ " inner join rota.setorComercial setor "
						+ " inner join setor.localidade localidade"
						+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao "
						+ " inner join rotaCriterio.cobrancaCriterio cobCrit "
						+ " where cobrancaAcao.id = :idCobrancaAcao"
						+ " and rota.codigo between :rotaInicial and :rotaFinal "
						+ " and setor.codigo = :setorComercial "
						+ " and localidade.id = :idLocalidade "
						+ " and cobCrit.id = :idCriterio ";

				qtdComCriterio = (Integer) session.createQuery(
						consultaComCriterio).setInteger("idLocalidade",
						new Integer(idLocalidade)).setInteger("setorComercial",
						new Integer(codigoSetorComercial)).setInteger(
						"rotaInicial", new Integer(rotaInicial)).setInteger(
						"rotaFinal", new Integer(rotaFinal)).setInteger(
						"idCobrancaAcao", new Integer(idCobrancaAcao))
						.setInteger("idCriterio", new Integer(idCobrancaAcao))
						.setMaxResults(1).uniqueResult();
			} else {
				consultaComCriterio = "select count(rota.id) from RotaAcaoCriterio rotaCriterio "
						+ " inner join rotaCriterio.rota rota"
						+ " inner join rota.setorComercial setor "
						+ " inner join setor.localidade localidade"
						+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao "
						+ " where cobrancaAcao.id = :idCobrancaAcao"
						+ " and rota.codigo between :rotaInicial and :rotaFinal "
						+ " and setor.codigo = :setorComercial "
						+ " and localidade.id = :idLocalidade ";

				qtdComCriterio = (Integer) session.createQuery(
						consultaComCriterio).setInteger("idLocalidade",
						new Integer(idLocalidade)).setInteger("setorComercial",
						new Integer(codigoSetorComercial)).setInteger(
						"rotaInicial", new Integer(rotaInicial)).setInteger(
						"rotaFinal", new Integer(rotaFinal)).setInteger(
						"idCobrancaAcao", new Integer(idCobrancaAcao))
						.setMaxResults(1).uniqueResult();
			}

			retorno[0] = "" + qtdRotas;
			retorno[1] = "" + qtdComCriterio;
			retorno[2] = "" + (qtdRotas - qtdComCriterio);

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	// caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public String[] pesquisarQuantidadeRotasIntervaloSetor(
			String codigoSetorComercialInicial,
			String codigoSetorComercialFinal, String idLocalidade,
			String idCobrancaAcao, Integer idCriterio)
			throws ErroRepositorioException {

		String[] retorno = new String[3];
		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		// Cria a variável que vai conter o hql
		String consulta;
		try {

			consulta = "select count(rota.id) from Rota rota "
					+ " inner join rota.setorComercial setor "
					+ " inner join setor.localidade localidade"
					+ " where setor.codigo between :codigoSetorComercialInicial and :codigoSetorComercialFinal "
					+ " and localidade.id = :idLocalidade";

			// Executa o hql
			Integer qtdRotas = (Integer) session.createQuery(consulta)
					.setInteger("idLocalidade", new Integer(idLocalidade))
					.setInteger("codigoSetorComercialInicial",
							new Integer(codigoSetorComercialInicial))
					.setInteger("codigoSetorComercialFinal",
							new Integer(codigoSetorComercialFinal))
					.setMaxResults(1).uniqueResult();

			String consultaComCriterio = "";
			Integer qtdComCriterio = 0;
			if (idCriterio != null) {
				consultaComCriterio = "select count(rota.id) from RotaAcaoCriterio rotaCriterio "
						+ " inner join rotaCriterio.rota rota"
						+ " inner join rota.setorComercial setor "
						+ " inner join setor.localidade localidade"
						+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao "
						+ " inner join rotaCriterio.cobrancaCriterio cobCrit "
						+ " where cobrancaAcao.id = :idCobrancaAcao"
						+ " and setor.codigo between :codigoSetorComercialInicial and :codigoSetorComercialFinal "
						+ " and localidade.id = :idLocalidade and cobCrit.id = :idCriterio";

				qtdComCriterio = (Integer) session.createQuery(
						consultaComCriterio).setInteger("idLocalidade",
						new Integer(idLocalidade)).setInteger(
						"codigoSetorComercialInicial",
						new Integer(codigoSetorComercialInicial)).setInteger(
						"codigoSetorComercialFinal",
						new Integer(codigoSetorComercialFinal)).setInteger(
						"idCobrancaAcao", new Integer(idCobrancaAcao))
						.setInteger("idCriterio", new Integer(idCriterio))
						.setMaxResults(1).uniqueResult();
			} else {
				consultaComCriterio = "select count(rota.id) from RotaAcaoCriterio rotaCriterio "
						+ " inner join rotaCriterio.rota rota"
						+ " inner join rota.setorComercial setor "
						+ " inner join setor.localidade localidade"
						+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao "
						+ " where cobrancaAcao.id = :idCobrancaAcao"
						+ " and setor.codigo between :codigoSetorComercialInicial and :codigoSetorComercialFinal "
						+ " and localidade.id = :idLocalidade";

				qtdComCriterio = (Integer) session.createQuery(
						consultaComCriterio).setInteger("idLocalidade",
						new Integer(idLocalidade)).setInteger(
						"codigoSetorComercialInicial",
						new Integer(codigoSetorComercialInicial)).setInteger(
						"codigoSetorComercialFinal",
						new Integer(codigoSetorComercialFinal)).setInteger(
						"idCobrancaAcao", new Integer(idCobrancaAcao))
						.setMaxResults(1).uniqueResult();
			}
			consulta = "select distinct(rota.id) from Rota rota "
					+ " inner join rota.setorComercial setor "
					+ " inner join setor.localidade localidade"
					+ " where setor.codigo between :codigoSetorComercialInicial and :codigoSetorComercialFinal "
					+ " and localidade.id = :idLocalidade";

			retorno[0] = "" + qtdRotas;
			retorno[1] = "" + qtdComCriterio;
			retorno[2] = "" + (qtdRotas - qtdComCriterio);

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	// caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public String[] pesquisarQuantidadeRotasIntervaloLocalidade(
			String idLocalidadeInicial, String idLocalidadeFinal,
			String idCobrancaAcao, Integer idCriterio)
			throws ErroRepositorioException {

		String[] retorno = new String[3];
		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		// Cria a variável que vai conter o hql
		String consulta;
		try {

			consulta = "select count(rota.id) from Rota rota "
					+ " inner join rota.setorComercial setor "
					+ " inner join setor.localidade localidade"
					+ " where localidade.id between :idLocalidadeInicial and :idLocalidadeFinal ";

			// Executa o hql
			Integer qtdRotas = (Integer) session.createQuery(consulta)
					.setInteger("idLocalidadeInicial",
							new Integer(idLocalidadeInicial))
					.setInteger("idLocalidadeFinal",
							new Integer(idLocalidadeFinal)).setMaxResults(1)
					.uniqueResult();

			String consultaComCriterio = "";
			Integer qtdComCriterio = 0;
			if (idCriterio != null) {
				consultaComCriterio = "select count(rota.id) from RotaAcaoCriterio rotaCriterio "
						+ " inner join rotaCriterio.rota rota"
						+ " inner join rota.setorComercial setor "
						+ " inner join setor.localidade localidade"
						+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao "
						+ " inner join rotaCriterio.cobrancaCriterio cobCrit "
						+ " where cobrancaAcao.id = :idCobrancaAcao"
						+ " and localidade.id between :idLocalidadeInicial and :idLocalidadeFinal"
						+ " and cobCrit.id = :idCriterio";

				qtdComCriterio = (Integer) session.createQuery(
						consultaComCriterio).setInteger("idLocalidadeInicial",
						new Integer(idLocalidadeInicial)).setInteger(
						"idLocalidadeFinal", new Integer(idLocalidadeFinal))
						.setInteger("idCobrancaAcao",
								new Integer(idCobrancaAcao)).setInteger(
								"idCriterio", new Integer(idCriterio))
						.setMaxResults(1).uniqueResult();
			} else {
				consultaComCriterio = "select count(rota.id) from RotaAcaoCriterio rotaCriterio "
						+ " inner join rotaCriterio.rota rota"
						+ " inner join rota.setorComercial setor "
						+ " inner join setor.localidade localidade"
						+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao "
						+ " where cobrancaAcao.id = :idCobrancaAcao"
						+ " and localidade.id between :idLocalidadeInicial and :idLocalidadeFinal";

				qtdComCriterio = (Integer) session.createQuery(
						consultaComCriterio).setInteger("idLocalidadeInicial",
						new Integer(idLocalidadeInicial)).setInteger(
						"idLocalidadeFinal", new Integer(idLocalidadeFinal))
						.setInteger("idCobrancaAcao",
								new Integer(idCobrancaAcao)).setMaxResults(1)
						.uniqueResult();
			}
			consulta = "select distinct(rota.id) from Rota rota "
					+ " inner join rota.setorComercial setor "
					+ " inner join setor.localidade localidade"
					+ " where localidade.id between :idLocalidadeInicial and :idLocalidadeFinal ";

			retorno[0] = "" + qtdRotas;
			retorno[1] = "" + qtdComCriterio;
			retorno[2] = "" + (qtdRotas - qtdComCriterio);

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	// caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public String[] pesquisarQuantidadeRotasIntervaloGerencia(
			String idGerenciaRegional, String idCobrancaAcao, Integer idCriterio)
			throws ErroRepositorioException {

		String[] retorno = new String[3];
		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		// Cria a variável que vai conter o hql
		String consulta;
		try {

			consulta = "select count(rota.id) from Rota rota "
					+ " inner join rota.setorComercial setor "
					+ " inner join setor.localidade localidade"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join unidadeNegocio.gerenciaRegional gerenciaRegional"
					+ " where gerenciaRegional.id = :idGerenciaRegional ";

			// Executa o hql
			Integer qtdRotas = (Integer) session.createQuery(consulta)
					.setInteger("idGerenciaRegional",
							new Integer(idGerenciaRegional)).setMaxResults(1)
					.uniqueResult();

			String consultaComCriterio = "";
			Integer qtdComCriterio = 0;
			if (idCriterio != null) {
				consultaComCriterio = "select count(rota.id) from RotaAcaoCriterio rotaCriterio "
						+ " inner join rotaCriterio.rota rota"
						+ " inner join rota.setorComercial setor "
						+ " inner join setor.localidade localidade"
						+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao "
						+ " inner join localidade.unidadeNegocio unidadeNegocio"
						+ " inner join unidadeNegocio.gerenciaRegional gerenciaRegional"
						+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao "
						+ " inner join rotaCriterio.cobrancaCriterio cobCrit "
						+ " where cobrancaAcao.id = :idCobrancaAcao"
						+ " and gerenciaRegional.id = :idGerenciaRegional "
						+ " and cobCrit.id = :idCriterio ";

				qtdComCriterio = (Integer) session.createQuery(
						consultaComCriterio).setInteger("idGerenciaRegional",
						new Integer(idGerenciaRegional)).setInteger(
						"idCobrancaAcao", new Integer(idCobrancaAcao))
						.setInteger("idCriterio", new Integer(idCriterio))
						.setMaxResults(1).uniqueResult();
			} else {
				consultaComCriterio = "select count(rota.id) from RotaAcaoCriterio rotaCriterio "
						+ " inner join rotaCriterio.rota rota"
						+ " inner join rota.setorComercial setor "
						+ " inner join setor.localidade localidade"
						+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao "
						+ " inner join localidade.unidadeNegocio unidadeNegocio"
						+ " inner join unidadeNegocio.gerenciaRegional gerenciaRegional"
						+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao "
						+ " where cobrancaAcao.id = :idCobrancaAcao"
						+ " and gerenciaRegional.id = :idGerenciaRegional";

				qtdComCriterio = (Integer) session.createQuery(
						consultaComCriterio).setInteger("idGerenciaRegional",
						new Integer(idGerenciaRegional)).setInteger(
						"idCobrancaAcao", new Integer(idCobrancaAcao))
						.setMaxResults(1).uniqueResult();
			}
			retorno[0] = "" + qtdRotas;
			retorno[1] = "" + qtdComCriterio;
			retorno[2] = "" + (qtdRotas - qtdComCriterio);
			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	// caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public String[] pesquisarQuantidadeRotasIntervaloGrupo(
			String idGrupoCobranca, String idCobrancaAcao, Integer idCriterio)
			throws ErroRepositorioException {

		String[] retorno = new String[3];
		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		// Cria a variável que vai conter o hql
		String consulta;
		try {

			consulta = "select count(rota.id) from Rota rota "
					+ " inner join rota.setorComercial setor "
					+ " inner join rota.cobrancaGrupo cobrancaGrupo"
					+ " where cobrancaGrupo.id = :idGrupoCobranca";

			// Executa o hql
			Integer qtdRotas = (Integer) session
					.createQuery(consulta)
					.setInteger("idGrupoCobranca", new Integer(idGrupoCobranca))
					.setMaxResults(1).uniqueResult();

			String consultaComCriterio = null;
			Integer qtdComCriterio = 0;
			if (idCriterio != null) {
				consultaComCriterio = "select count(rota.id) from RotaAcaoCriterio rotaCriterio "
						+ " inner join rotaCriterio.rota rota"
						+ " inner join rota.cobrancaGrupo cobrancaGrupo"
						+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao "
						+ " inner join rotaCriterio.cobrancaCriterio cobCrit "
						+ " where cobrancaAcao.id = :idCobrancaAcao"
						+ " and cobrancaGrupo.id = :idGrupoCobranca"
						+ " and cobCrit.id = :idCriterio";
				qtdComCriterio = (Integer) session.createQuery(
						consultaComCriterio).setInteger("idGrupoCobranca",
						new Integer(idGrupoCobranca)).setInteger(
						"idCobrancaAcao", new Integer(idCobrancaAcao))
						.setInteger("idCriterio", new Integer(idCriterio))
						.setMaxResults(1).uniqueResult();
			} else {
				consultaComCriterio = "select count(rota.id) from RotaAcaoCriterio rotaCriterio "
						+ " inner join rotaCriterio.rota rota"
						+ " inner join rota.cobrancaGrupo cobrancaGrupo"
						+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao "
						+ " where cobrancaAcao.id = :idCobrancaAcao"
						+ " and cobrancaGrupo.id = :idGrupoCobranca";
				qtdComCriterio = (Integer) session.createQuery(
						consultaComCriterio).setInteger("idGrupoCobranca",
						new Integer(idGrupoCobranca)).setInteger(
						"idCobrancaAcao", new Integer(idCobrancaAcao))
						.setMaxResults(1).uniqueResult();
			}

			retorno[0] = "" + qtdRotas;
			retorno[1] = "" + qtdComCriterio;
			retorno[2] = "" + (qtdRotas - qtdComCriterio);

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	// caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public String[] pesquisarQuantidadeRotasIntervaloUnidadeNegocio(
			String idUnidadeNegocio, String idCobrancaAcao, Integer idCriterio)
			throws ErroRepositorioException {

		String[] retorno = new String[3];
		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		// Cria a variável que vai conter o hql
		String consulta;
		try {

			consulta = "select count(rota.id) from Rota rota "
					+ " inner join rota.setorComercial setor "
					+ " inner join setor.localidade localidade"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " where unidadeNegocio.id = :idUnidadeNegocio";

			// Executa o hql
			Integer qtdRotas = (Integer) session.createQuery(consulta)
					.setInteger("idUnidadeNegocio",
							new Integer(idUnidadeNegocio)).setMaxResults(1)
					.uniqueResult();

			String consultaComCriterio = "";
			Integer qtdComCriterio = 0;
			if (idCriterio != null) {
				consultaComCriterio = "select count(rota.id) from RotaAcaoCriterio rotaCriterio "
						+ " inner join rotaCriterio.rota rota"
						+ " inner join rota.setorComercial setor "
						+ " inner join setor.localidade localidade"
						+ " inner join localidade.unidadeNegocio unidadeNegocio "
						+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao "
						+ " inner join rotaCriterio.cobrancaCriterio cobCrit "
						+ " where cobrancaAcao.id = :idCobrancaAcao"
						+ " and unidadeNegocio.id = :idUnidadeNegocio"
						+ " and cobCrit.id = :idCriterio ";

				qtdComCriterio = (Integer) session.createQuery(
						consultaComCriterio).setInteger("idUnidadeNegocio",
						new Integer(idUnidadeNegocio)).setInteger(
						"idCobrancaAcao", new Integer(idCobrancaAcao))
						.setInteger("idCriterio", new Integer(idCriterio))
						.setMaxResults(1).uniqueResult();
			} else {
				consultaComCriterio = "select count(rota.id) from RotaAcaoCriterio rotaCriterio "
						+ " inner join rotaCriterio.rota rota"
						+ " inner join rota.setorComercial setor "
						+ " inner join setor.localidade localidade"
						+ " inner join localidade.unidadeNegocio unidadeNegocio "
						+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao "
						+ " where cobrancaAcao.id = :idCobrancaAcao"
						+ " and unidadeNegocio.id = :idUnidadeNegocio";

				qtdComCriterio = (Integer) session.createQuery(
						consultaComCriterio).setInteger("idUnidadeNegocio",
						new Integer(idUnidadeNegocio)).setInteger(
						"idCobrancaAcao", new Integer(idCobrancaAcao))
						.setMaxResults(1).uniqueResult();
			}

			retorno[0] = "" + qtdRotas;
			retorno[1] = "" + qtdComCriterio;
			retorno[2] = "" + (qtdRotas - qtdComCriterio);

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	 * 
	 * @author Francisco do Nascimento
	 * @date 27/02/08
	 */
	public String[] pesquisarQuantidadeRotasPorCobrancaAcao(
			String idCobrancaAcao, Integer idCriterio)
			throws ErroRepositorioException {

		String[] retorno = new String[3];
		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		// Cria a variável que vai conter o hql
		String consulta;
		try {

			consulta = "select count(rota.id) from Rota rota ";

			// Executa o hql
			Integer qtdRotas = (Integer) session.createQuery(consulta)
					.setMaxResults(1).uniqueResult();
			String consultaComCriterio = "";
			Integer qtdComCriterio = 0;
			if (idCriterio != null) {
				consultaComCriterio = "select count(rota.id) from RotaAcaoCriterio rotaCriterio "
						+ " inner join rotaCriterio.rota rota"
						+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao "
						+ " inner join rotaCriterio.cobrancaCriterio cobCrit "
						+ " where cobrancaAcao.id = :idCobrancaAcao"
						+ " and cobCrit.id = :idCriterio";

				qtdComCriterio = (Integer) session.createQuery(
						consultaComCriterio).setInteger("idCobrancaAcao",
						new Integer(idCobrancaAcao)).setInteger("idCriterio",
						new Integer(idCriterio)).setMaxResults(1)
						.uniqueResult();

			} else {
				consultaComCriterio = "select count(rota.id) from RotaAcaoCriterio rotaCriterio "
						+ " inner join rotaCriterio.rota rota"
						+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao "
						+ " where cobrancaAcao.id = :idCobrancaAcao";

				qtdComCriterio = (Integer) session.createQuery(
						consultaComCriterio).setInteger("idCobrancaAcao",
						new Integer(idCobrancaAcao)).setMaxResults(1)
						.uniqueResult();

			}
			retorno[0] = "" + qtdRotas;
			retorno[1] = "" + qtdComCriterio;
			retorno[2] = "" + (qtdRotas - qtdComCriterio);

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisa as rotas pelo grupo de faturamento
	 * 
	 * [UC0627] Gerar Arquivo Texto para Leitura
	 * 
	 * @author Pedro Alexandre
	 * @date 15/10/2007
	 * 
	 * @param idGrupoFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRotasPorGrupoFaturamento(
			Integer idGrupoFaturamento) throws ErroRepositorioException {

		Collection<Rota> rotas = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			/*
			 * Alteração feita para não pegar rotas repetidas Thiago Nascimento
			 * 16/04/2008
			 */
			consulta = "select distinct(rota) " + "from Quadra qdra "
					+ "inner join qdra.rota rota "
					+ "inner join rota.faturamentoGrupo ftgr "
					+ "inner join fetch rota.empresa empr "
					+ "inner join fetch rota.leiturista leit "
					+ "where ftgr.id = :idFaturamentoGrupo"

			;

			rotas = session.createQuery(consulta).setInteger(
					"idFaturamentoGrupo", idGrupoFaturamento).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return rotas;
	}

	/**
	 * [FS005] - Verificar existência do arquivo texto roteiro empresa por rota.
	 * 
	 * Caso já exista um arquivo texto para o mês de referência informado, mesma
	 * rota, mesmo grupo de faturamento e sua situação de leitura transmissão
	 * esteja liberado, exclui o arquivo correspondente e retorna pra o caso se
	 * uso que chamou esta funcionalidade.
	 * 
	 * [UC0627] Gerar Arquivo Texto para Leiturista s
	 * 
	 * @author Pedro Alexandre
	 * @date 15/10/2007
	 * 
	 * @param anoMesReferencia
	 * @param idRota
	 * @param idGrupoFaturamento
	 * @throws ErroRepositorioException
	 */
	public boolean excluirArquivoTextoParaLeituristaPorRota(
			Integer anoMesReferencia, Integer idRota, Integer idGrupoFaturamento)
			throws ErroRepositorioException {

		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// Retorno Consulta
		int qtdRegistrosDeletados = 0;

		// Cria a variável que vai conter o hql
		String consulta;

		try {

			consulta = "delete ArquivoTextoRoteiroEmpresa where "
					+ "anoMesReferencia = :anoMesReferencia "
					+ "and rota = :idRota "
					+ "and faturamentoGrupo = :idFaturamentoGrupo "
					+ "and situacaoTransmissaoLeitura = :situacao";

			// Executa o hql
			/*
			 * Alteração feita para excluir os arquivos que já forão lidos.
			 * Thiago Nascimento 16/04/2008
			 */
			qtdRegistrosDeletados = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).setInteger("idRota",
					idRota)
					.setInteger("idFaturamentoGrupo", idGrupoFaturamento)
					.setInteger("situacao",
							SituacaoTransmissaoLeitura.DISPONIVEL)
					.executeUpdate();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return qtdRegistrosDeletados > 0;
	}

	/**
	 * [UC0627] Gerar Arquivo Texto para Leituristas
	 * 
	 * Pesquisa a quantidade de setores comercias por roteiro empresa.
	 * 
	 * [FS0004] Verificar Quantidade de setores comercias.
	 * 
	 * @author Pedro Alexandre
	 * @date 15/10/2007
	 * 
	 * @param idRota
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeSetorComercialPorRota(Integer idRota,
			Integer anoMesFaturamento, Integer idFaturamentoGrupo)
			throws ErroRepositorioException {

		Collection retorno = null;
		Integer qtdSetoresComercias = 0;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select distinct(mrem.codigoSetorComercial) "
					+ "from MovimentoRoteiroEmpresa mrem "
					+ "left join mrem.rota rota "
					+ "left join mrem.faturamentoGrupo ftgr "
					+ "where rota.id = :idRota and "
					+ "mrem.anoMesMovimento = :anoMesFaturamento and "
					+ "ftgr.id = :idFaturamentoGrupo";

			retorno = session.createQuery(consulta)
					.setInteger("idRota", idRota).setInteger(
							"anoMesFaturamento", anoMesFaturamento).setInteger(
							"idFaturamentoGrupo", idFaturamentoGrupo).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		if (retorno != null && !retorno.isEmpty()) {
			qtdSetoresComercias = retorno.size();
		}
		return qtdSetoresComercias;
	}

	/**
	 * selecionar os movimentos roteiros empresas por rota.
	 * 
	 * [UC0627] Gerar Arquivo Texto para Leituristas
	 * 
	 * @author Pedro Alexandre
	 * @date 15/10/2007
	 * 
	 * @param idRota
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarMovimentoRoteiroEmpresaPorRota(
			Integer idRota, Integer anoMesFaturamento,
			Integer idFaturamentoGrupo, String empresa, Integer idLeituraTipo)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			/*
			 * Alteração feita para incluir o código da rota, sequencial de rota
			 * e a matrícula do funcionario no arquivo gerado para a leitura.
			 * Thiago Nascimento 01/04/2008
			 */
			consulta = "select loca.id, "
					+ "mrem.codigoSetorComercial, "
					+ "mrem.numeroQuadra, "
					+ "mrem.numeroLoteImovel, "
					+ "mrem.numeroSubloteImovel, "
					+ "medt.id, "
					+ "imov.id, "
					+ "iper.id, "
					+ "mrem.nomeCliente, "
					+ "mrem.enderecoImovel, "
					+ "himc.id, "
					+ "mrem.numeroHidrometro, "
					+ "hicp.id, "
					+ "hili.id, "
					+ "mrem.dataInstalacaoHidrometro, "
					+ "hipr.id, "
					+ "last.id, "
					+ "lest.id, "
					+ "mrem.descricaoAbreviadaCategoriaImovel, "
					+ "mrem.quantidadeEconomias, "
					+ "mrem.numeroLeituraAnterior, "
					+ "mrem.numeroFaixaLeituraEsperadaInicial, "
					+ "mrem.numeroFaixaLeituraEsperadaFinal, "
					+ "rota.codigo, "
					+ "imov.numeroSequencialRota, "
					+ "fun.id "
					+ "from MovimentoRoteiroEmpresa mrem "
					+ "left join mrem.rota rota "
					+ "left join rota.leiturista leitu "
					+ "left join leitu.funcionario fun "
					+ "left join mrem.localidade loca "
					+ "left join mrem.medicaoTipo medt "
					+ "left join mrem.imovel imov "
					+ "left join imov.imovelPerfil iper "
					+ "left join mrem.hidrometroMarca himc "
					+ "left join mrem.hidrometroCapacidade hicp "
					+ "left join mrem.hidrometroLocalInstalacao hili "
					+ "left join mrem.hidrometroProtecao hipr "
					+ "left join mrem.ligacaoAguaSituacao last "
					+ "left join mrem.ligacaoEsgotoSituacao lest "
					+ "left join mrem.faturamentoGrupo ftgr "
					+ "where rota.id = :idRota and "
					+ "mrem.anoMesMovimento = :anoMesFaturamento and "
					+ "ftgr.id = :idFaturamentoGrupo and mrem.leituraTipo.id = :idLeituraTipo ";
			if (empresa.toUpperCase().equals("COMPESA")) {
				consulta = consulta
						+ "order by mrem.codigoSetorComercial, mrem.numeroQuadra, mrem.numeroLoteImovel, mrem.numeroSubloteImovel";
			} else {
				consulta = consulta
				+ "order by mrem.gerenciaRegional.id, mrem.localidade.id, mrem.codigoSetorComercial,mrem.numeroQuadra,mrem.numeroSequencialRota, mrem.numeroLoteImovel, mrem.numeroSubloteImovel ";
			}

			retorno = session.createQuery(consulta)
					.setInteger("idRota", idRota).setInteger(
							"anoMesFaturamento", anoMesFaturamento).setInteger(
							"idFaturamentoGrupo", idFaturamentoGrupo)
					.setInteger("idLeituraTipo", idLeituraTipo).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	// caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public void desassociarRotasIntervaloRotas(String codigoSetorComercial,
			String rotaInicial, String rotaFinal, String idLocalidade,
			String idCobrancaAcao) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select distinct(rotaCriterio.comp_id.rotaId) from RotaAcaoCriterio rotaCriterio"
					+ " inner join rotaCriterio.rota rota"
					+ " inner join rota.setorComercial setor"
					+ " inner join setor.localidade localidade"
					+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao"
					+ " where cobrancaAcao.id = :idCobrancaAcao"
					+ " and rota.codigo between :rotaInicial and :rotaFinal"
					+ " and setor.codigo = :setorComercial"
					+ " and localidade.id = :idLocalidade";

			Collection pesquisa = session.createQuery(consulta).setInteger(
					"idLocalidade", new Integer(idLocalidade)).setInteger(
					"setorComercial", new Integer(codigoSetorComercial))
					.setInteger("rotaInicial", new Integer(rotaInicial))
					.setInteger("rotaFinal", new Integer(rotaFinal))
					.setInteger("idCobrancaAcao", new Integer(idCobrancaAcao))
					.list();

			if (idCobrancaAcao == null) {
				String delete = "delete from RotaAcaoCriterio rotaCriterio"
						+ " where rotaCriterio.rota.id in (:pesquisa) ";

				session.createQuery(delete).setParameterList("pesquisa",
						pesquisa).executeUpdate();
			} else {
				String delete = "delete from RotaAcaoCriterio rotaCriterio"
						+ " where rotaCriterio.rota.id in (:pesquisa) and rotaCriterio.cobrancaAcao.id = :idCobrancaAcao ";

				session.createQuery(delete).setParameterList("pesquisa",
						pesquisa).setInteger("idCobrancaAcao",
						new Integer(idCobrancaAcao)).executeUpdate();
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	// caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public void desassociarRotasIntervaloSetor(
			String codigoSetorComercialInicial,
			String codigoSetorComercialFinal, String idLocalidade,
			String idCobrancaAcao) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {

			String consulta = "select distinct(rotaCriterio.comp_id.rotaId) from RotaAcaoCriterio rotaCriterio"
					+ " inner join rotaCriterio.rota rota"
					+ " inner join rota.setorComercial setor"
					+ " inner join setor.localidade localidade"
					+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao"
					+ " where cobrancaAcao.id = :idCobrancaAcao"
					+ " and setor.codigo between :codigoSetorComercialInicial and :codigoSetorComercialFinal"
					+ " and localidade.id = :idLocalidade";

			Collection pesquisa = session.createQuery(consulta).setInteger(
					"idLocalidade", new Integer(idLocalidade)).setInteger(
					"codigoSetorComercialInicial",
					new Integer(codigoSetorComercialInicial)).setInteger(
					"codigoSetorComercialFinal",
					new Integer(codigoSetorComercialFinal)).setInteger(
					"idCobrancaAcao", new Integer(idCobrancaAcao)).list();

			if (idCobrancaAcao == null) {
				String delete = "delete from RotaAcaoCriterio rotaCriterio"
						+ " where rotaCriterio.rota.id in (:pesquisa)";

				session.createQuery(delete).setParameterList("pesquisa",
						pesquisa).executeUpdate();
			} else {
				String delete = "delete from RotaAcaoCriterio rotaCriterio"
						+ " where rotaCriterio.rota.id in (:pesquisa) and rotaCriterio.cobrancaAcao.id = :idCobrancaAcao ";

				session.createQuery(delete).setParameterList("pesquisa",
						pesquisa).setInteger("idCobrancaAcao",
						new Integer(idCobrancaAcao)).executeUpdate();
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	// caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public void desassociarRotasIntervaloLocalidade(String idLocalidadeInicial,
			String idLocalidadeFinal, String idCobrancaAcao)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {
			String consulta = "select distinct(rotaCriterio.comp_id.rotaId) from RotaAcaoCriterio rotaCriterio"
					+ " inner join rotaCriterio.rota rota"
					+ " inner join rota.setorComercial setor "
					+ " inner join setor.localidade localidade"
					+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao"
					+ " where cobrancaAcao.id = :idCobrancaAcao"
					+ " and localidade.id between :idLocalidadeInicial and :idLocalidadeFinal";

			Collection pesquisa = session.createQuery(consulta).setInteger(
					"idLocalidadeInicial", new Integer(idLocalidadeInicial))
					.setInteger("idLocalidadeFinal",
							new Integer(idLocalidadeFinal)).setInteger(
							"idCobrancaAcao", new Integer(idCobrancaAcao))
					.list();

			String delete = "delete from RotaAcaoCriterio rotaCriterio"
					+ " where rotaCriterio.rota.id in (:pesquisa)  and rotaCriterio.cobrancaAcao.id = :idCobrancaAcao ";

			session.createQuery(delete).setParameterList("pesquisa", pesquisa)
					.setInteger("idCobrancaAcao", new Integer(idCobrancaAcao))
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	// caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public void desassociarRotasIntervaloGerencia(String idGerenciaRegional,
			String idCobrancaAcao) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {

			String consulta = "select distinct(rotaCriterio.comp_id.rotaId) from RotaAcaoCriterio rotaCriterio"
					+ " inner join rotaCriterio.rota rota"
					+ " inner join rota.setorComercial setor "
					+ " inner join setor.localidade localidade"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join unidadeNegocio.gerenciaRegional gerenciaRegional"
					+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao "
					+ " where cobrancaAcao.id = :idCobrancaAcao"
					+ " and gerenciaRegional.id = :idGerenciaRegional";

			Collection pesquisa = session.createQuery(consulta).setInteger(
					"idGerenciaRegional", new Integer(idGerenciaRegional))
					.setInteger("idCobrancaAcao", new Integer(idCobrancaAcao))
					.list();

			if (idCobrancaAcao == null) {

				String delete = "delete from RotaAcaoCriterio rotaCriterio"
						+ " where rotaCriterio.rota.id in (:pesquisa) ";

				session.createQuery(delete).setParameterList("pesquisa",
						pesquisa).executeUpdate();

			} else {
				String delete = "delete from RotaAcaoCriterio rotaCriterio"
						+ " where rotaCriterio.rota.id in (:pesquisa)  and rotaCriterio.cobrancaAcao.id = :idCobrancaAcao ";

				session.createQuery(delete).setParameterList("pesquisa",
						pesquisa).setInteger("idCobrancaAcao",
						new Integer(idCobrancaAcao)).executeUpdate();
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	// caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public void desassociarRotasIntervaloGrupo(String idGrupoCobranca,
			String idCobrancaAcao) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {

			String consulta = "select distinct(rotaPesquisa.id) from RotaAcaoCriterio racc"
					+ " inner join racc.rota rotaPesquisa"
					+ " inner join rotaPesquisa.cobrancaGrupo cobrancaGrupo"
					+ " inner join racc.cobrancaAcao cobrancaAcao "
					+ " where cobrancaAcao.id = :idCobrancaAcao"
					+ " and cobrancaGrupo.id = :idGrupoCobranca";

			Collection pesquisa = session.createQuery(consulta).setInteger(
					"idGrupoCobranca", new Integer(idGrupoCobranca))
					.setInteger("idCobrancaAcao", new Integer(idCobrancaAcao))
					.list();

			if (idCobrancaAcao == null) {
				String delete = "delete from RotaAcaoCriterio rotaCriterio"
						+ " where rotaCriterio.rota.id in (:pesquisa)";

				session.createQuery(delete).setParameterList("pesquisa",
						pesquisa).executeUpdate();
			} else {
				String delete = "delete from RotaAcaoCriterio rotaCriterio"
						+ " where rotaCriterio.rota.id in (:pesquisa)  and rotaCriterio.cobrancaAcao.id = :idCobrancaAcao ";

				session.createQuery(delete).setParameterList("pesquisa",
						pesquisa).setInteger("idCobrancaAcao",
						new Integer(idCobrancaAcao)).executeUpdate();

			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	// caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public void desassociarRotasIntervaloUnidadeNegocio(
			String idUnidadeNegocio, String idCobrancaAcao)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		// Cria a variável que vai conter o hql
		try {

			String consulta = "select distinct(rotaCriterio.comp_id.rotaId) from RotaAcaoCriterio rotaCriterio"
					+ " inner join rotaCriterio.rota rota"
					+ " inner join rota.setorComercial setor"
					+ " inner join setor.localidade localidade"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join rotaCriterio.cobrancaAcao cobrancaAcao"
					+ " where cobrancaAcao.id = :idCobrancaAcao"
					+ " and unidadeNegocio.id = :idUnidadeNegocio";

			Collection pesquisa = session.createQuery(consulta).setInteger(
					"idUnidadeNegocio", new Integer(idUnidadeNegocio))
					.setInteger("idCobrancaAcao", new Integer(idCobrancaAcao))
					.list();

			if (idCobrancaAcao == null) {
				String delete = "delete from RotaAcaoCriterio rotaCriterio"
						+ " where rotaCriterio.rota.id in (:pesquisa)";

				session.createQuery(delete).setParameterList("pesquisa",
						pesquisa).executeUpdate();
			} else {
				String delete = "delete from RotaAcaoCriterio rotaCriterio"
						+ " where rotaCriterio.rota.id in (:pesquisa)  and rotaCriterio.cobrancaAcao.id = :idCobrancaAcao ";

				session.createQuery(delete).setParameterList("pesquisa",
						pesquisa).setInteger("idCobrancaAcao",
						new Integer(idCobrancaAcao)).executeUpdate();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	 * 
	 * @param idCobrancaAcao
	 * @author Francisco Nascimento
	 * @date 27/02/08
	 * @throws ErroRepositorioException
	 */
	public void desassociarRotasPorCobrancaAcao(String idCobrancaAcao)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {

			String delete = "delete from RotaAcaoCriterio rotaCriterio"
					+ " where rotaCriterio.cobrancaAcao.id in (:idCobrancaAcao)";

			session.createQuery(delete).setInteger("idCobrancaAcao",
					new Integer(idCobrancaAcao)).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * pesquisa o consumo historico passando o imovel e o anomes referencia e o
	 * consumo anormalidade correspondente ao faturameto antecipado.
	 * 
	 * [UC0113] Faturar Grupo de Faturamento
	 * 
	 * @author Sávio LuIz
	 * @date 08/11/2007
	 * 
	 * @param idRota
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarConsumoHistoricoAntecipado(Integer idImovel,
			Integer anoMes) throws ErroRepositorioException {
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select ch.id " + "from ConsumoHistorico ch "
					+ "inner join ch.imovel im "
					+ "left join ch.consumoAnormalidade ca "
					+ "where im.id = :idImovel and "
					+ "ca.id = :idConsumoAnormalidade and "
					+ "ch.referenciaFaturamento = :anoMesReferencia";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel.intValue()).setInteger(
					"idConsumoAnormalidade",
					ConsumoAnormalidade.FATURAMENTO_ANTECIPADO).setInteger(
					"anoMesReferencia", anoMes.intValue()).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * Relatório Analise de Consumo
	 * 
	 * @author Flávio Leonardo, Ivan Sergio
	 * @date 26/12/2007
	 * @alteracao: 24/07/2008 - Adicionado a descricao da Leitura Anormalidade
	 *             Informada
	 * 
	 * @param idImovel
	 * @param anomes
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarLeiturasImovel(String idImovel, String anoMes)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select mh.leituraAtualInformada, "
					+ " mh.leituraAnteriorFaturamento," + " hi.numero, "
					+ " la.descricao " + " from MedicaoHistorico mh"
					+ " inner join mh.hidrometroInstalacaoHistorico hid "
					+ " inner join hid.hidrometro hi"
					+ " left join mh.leituraAnormalidadeInformada la "
					+ " where mh.ligacaoAgua.id = :id"
					+ " and mh.anoMesReferencia = :anoMes";

			retorno = session.createQuery(consulta).setInteger("id",
					new Integer(idImovel)).setInteger("anoMes",
					new Integer(anoMes)).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Relatório Manter Hidrometro
	 * 
	 * Flávio Leonardo
	 * 
	 * pesquisa o id do imovel do hidrometro instalado
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelPeloHidrometro(Integer hidrometroId)
			throws ErroRepositorioException {
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select CASE WHEN (hih.imovel.id = null) THEN "
					+ " hih.ligacaoAgua.id " + " ELSE " + " hih.imovel.id END "
					+ " from HidrometroInstalacaoHistorico hih"
					+ " where hih.hidrometro.id = :id "
					+ " and hih.dataRetirada is null";

			retorno = (Integer) session.createQuery(consulta).setInteger("id",
					new Integer(hidrometroId)).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * Busca o Ano Mês de Referencia o grupo de Faturamento fornecido.
	 * 
	 * [UC0712] Atualizar Leituras e Anormalidades do Celular
	 * 
	 * @author Thiago Nascimento
	 * @date 11/12/2007
	 * 
	 * @param grupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public FaturamentoGrupo buscarAnoMesReferenciaCasoOperador(Integer grupo)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		FaturamentoGrupo faturamento = null;
		try {
			// Selecionando o Grupo de Faturamento
			StringBuffer hql = new StringBuffer(
					"FROM FaturamentoGrupo fg where fg.id = ");
			hql.append(grupo.toString());
			faturamento = (FaturamentoGrupo) session
					.createQuery(hql.toString()).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return faturamento;
	}

	/**
	 * 
	 * Busca o Ano Mês de Referencia para a matrícula do Imóvel informada.
	 * 
	 * [UC0712] Atualizar Leituras e Anormalidades do Celular
	 * 
	 * @author Thiago Nascimento, Rômulo Aurélio
	 * @date 11/12/2007, 09/11/2010
	 * 
	 * Alteração realizada para pegar o grupo correto quando a rota do imovel for alternativa
	 * 
	 * @param matricula
	 * @return
	 * @throws ErroRepositorioException
	 */
	public FaturamentoGrupo buscarAnoMesReferenciaCasoSistema(Integer matricula)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		FaturamentoGrupo faturamento = null;
		
		Object retorno[] = null;
		
		try {
			// Selecionando o Grupo de Faturamento
			
			
			String sql =  "select case when imov.rota_idalternativa is not null then grupoAlternativo.ftgr_id " 
							+ " else grupoQuadra.ftgr_id " 
							+ " end as idGrupo, "
							+ " case when imov.rota_idalternativa is not null then grupoAlternativo.ftgr_amreferencia " 
							+ " else grupoQuadra.ftgr_amreferencia " 
							+ " end as anoMesReferencia, "
							+ " case when imov.rota_idalternativa is not null then grupoAlternativo.ftgr_dsabreviado " 
							+ " else grupoQuadra.ftgr_dsabreviado " 
							+ " end as descricaoAbreviada, "
							+ " case when imov.rota_idalternativa is not null then grupoAlternativo.ftgr_icuso " 
							+ " else grupoQuadra.ftgr_icuso " 
							+ " end as indicadorUso, "
							+ " case when imov.rota_idalternativa is not null then grupoAlternativo.ftgr_tmultimaalteracao " 
							+ " else grupoQuadra.ftgr_tmultimaalteracao " 
							+ " end as ultimaAlteracao, "
							+ " case when imov.rota_idalternativa is not null then grupoAlternativo.ftgr_nndiavencimento " 
							+ " else grupoQuadra.ftgr_nndiavencimento " 
							+ " end as nnDiasVencimento, "
							+ " case when imov.rota_idalternativa is not null then grupoAlternativo.ftgr_icvencimentomesfatura " 
							+ " else grupoQuadra.ftgr_icvencimentomesfatura " 
							+ " end as icVencimentoMesFatura"
							+ " from cadastro.imovel imov " 
							+ " inner join cadastro.quadra quadra on imov.qdra_id = quadra.qdra_id " 
							+ " inner join micromedicao.rota rotaQuadra on quadra.rota_id = rotaQuadra.rota_id " 
							+ " inner join faturamento.faturamento_grupo grupoQuadra on rotaQuadra.ftgr_id = grupoQuadra.ftgr_id " 
							+ " left outer join micromedicao.rota rotaAlternativa on imov.rota_idalternativa = rotaAlternativa.rota_id " 
							+ " left outer join faturamento.faturamento_grupo grupoAlternativo on rotaAlternativa.ftgr_id = grupoAlternativo.ftgr_id " 
							+ " where  imov.imov_id = :idImovel  ";	 
			
				
			retorno =  (Object[]) session
				.createSQLQuery(sql)
				.addScalar("idGrupo", Hibernate.INTEGER)
				.addScalar("anoMesReferencia", Hibernate.INTEGER)
				.addScalar("descricaoAbreviada", Hibernate.STRING)
				.addScalar("indicadorUso", Hibernate.SHORT)
				.addScalar("ultimaAlteracao", Hibernate.TIMESTAMP)
				.addScalar("nnDiasVencimento", Hibernate.INTEGER)
				.addScalar("icVencimentoMesFatura", Hibernate.SHORT)
				.setInteger( "idImovel", matricula ).uniqueResult();
			
			if (retorno !=null){
			
				faturamento = new FaturamentoGrupo();
				faturamento.setId((Integer)retorno[0]); 					
				faturamento.setAnoMesReferencia((Integer)retorno[1]);
				faturamento.setDescricaoAbreviada((String)retorno[2]);
				faturamento.setIndicadorUso((Short)retorno[3]);
				faturamento.setUltimaAlteracao((Date)retorno[4]);
				faturamento.setDiaVencimento(((Integer)retorno[5]).shortValue());
				faturamento.setIndicadorVencimentoMesFatura((Short)retorno[6]);
				
					
			}
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return faturamento;
	}

	/**
	 * 
	 * Método para a atualização das leituras e Anormalidades do Celular, em que
	 * atualiza o Movimento do Roteiro de uma Empresa e inserir no Histórico de
	 * medições.
	 * 
	 * [UC0712] Atualizar Leituras e Anormalidades do Celular
	 * 
	 * @author Thiago Nascimento
	 * @date 05/12/2007
	 * 
	 * @param dados
	 * @param anoMesFaturamento
	 * @throws ErroRepositorioException
	 */
	public void atualizarLeituraAnormailidadeCelular(DadosMovimentacao dado,
			Integer anoMesFaturamento, Leiturista leit)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {
			StringBuffer hql;

			// Selecionando a Ligacao de Agua do Imovel
			LigacaoAgua ligacaoAgua = null;
			Integer tipoMedicao = dado.getTipoMedicao();
			if (dado.getTipoMedicao().equals(MedicaoTipo.LIGACAO_AGUA)
					|| tipoMedicao.equals(new Integer(0))) {
				hql = new StringBuffer("FROM LigacaoAgua l where l.id = ");
				hql.append(dado.getMatriculaImovel());
				ligacaoAgua = (LigacaoAgua) session.createQuery(hql.toString())
						.uniqueResult();
			}

			// Selecionar o Imovel
			Imovel imovel = null;
			if (dado.getTipoMedicao().equals(MedicaoTipo.POCO)
					|| tipoMedicao.equals(new Integer(0))) {
				hql = new StringBuffer("FROM Imovel i where i.id = ");
				hql.append(dado.getMatriculaImovel());
				imovel = (Imovel) session.createQuery(hql.toString())
						.uniqueResult();
			}

			// Selecionar a Leitura Anormalidade
			hql = new StringBuffer("FROM LeituraAnormalidade l where l.id = ");
			hql.append(dado.getCodigoAnormalidade().toString());
			LeituraAnormalidade leitura = (LeituraAnormalidade) session
					.createQuery(hql.toString()).uniqueResult();
			if (dado.getTipoMedicao().intValue() == 0 && ligacaoAgua != null
					&& ligacaoAgua.getHidrometroInstalacaoHistorico() == null
					&& imovel != null
					&& imovel.getHidrometroInstalacaoHistorico() == null) {
				imovel.setLeituraAnormalidade(leitura);
				session.update(imovel);
			} else {
				// Histórico de Medição
				// Verifica se já existe um Historio de Medicao.
				hql = new StringBuffer(
						"FROM MedicaoHistorico m where (m.imovel.id = ");
				hql.append(dado.getMatriculaImovel());
				hql.append(" or m.ligacaoAgua.id = ");
				hql.append(dado.getMatriculaImovel());
				hql.append(") and m.anoMesReferencia = ");
				hql.append(anoMesFaturamento);
				hql.append(" and m.medicaoTipo.id  = ");
				hql.append(dado.getTipoMedicao());
				MedicaoHistorico medicaoHistorico = (MedicaoHistorico) session
						.createQuery(hql.toString()).uniqueResult();

				// Seleciona o Funcionario.
				hql = new StringBuffer("FROM Funcionario f where f.id=");
				hql.append(dado.getMatriculaOperador());
				Funcionario funcionario = (Funcionario) session.createQuery(
						hql.toString()).uniqueResult();
				boolean inserir = false;

				if (medicaoHistorico == null) {
					medicaoHistorico = new MedicaoHistorico();
					inserir = true;
				}

				// Set Funcionario
				medicaoHistorico.setFuncionario(funcionario);
				if (dado.getCodigoAnormalidade() != null
						&& dado.getCodigoAnormalidade().intValue() != 0) {
					medicaoHistorico.setLeituraAnormalidadeInformada(leitura);
				} else {
					medicaoHistorico.setLeituraAnormalidadeInformada(null);
				}

				// Compara se data de leitura está dentro do intervalo
				// anoMesFaturamentoAnterior e anoMesFaturamentoPosterior
				// Caso contrário, dataLeitura recebe a data corrente.
				// --------------------------------------------------------------
				// CRC 826 , CRC892
				// Alterado por: Yara T. Souza
				// Date = 17/02/2008
				// --------------------------------------------------------------
				Integer anoMesDataLeitura = Util.formataAnoMes(dado
						.getDataLeituraCampo());
				Integer anoMesFaturamentoAnterior = Util.subtrairMesDoAnoMes(
						anoMesFaturamento, 1);
				Integer anoMesFaturamentoPosterior = Util
						.somaUmMesAnoMesReferencia(anoMesFaturamento);

				if (anoMesDataLeitura != null
						&& anoMesFaturamentoAnterior != null
						&& anoMesFaturamentoPosterior != null) {
					if (anoMesFaturamento.equals(anoMesDataLeitura)
							|| Util.compararAnoMesReferencia(anoMesDataLeitura,
									anoMesFaturamentoAnterior, "=")
							|| Util.compararAnoMesReferencia(anoMesDataLeitura,
									anoMesFaturamentoPosterior, "=")) {
						medicaoHistorico.setDataLeituraAtualInformada(dado
								.getDataLeituraCampo());
						medicaoHistorico.setDataLeituraCampo(dado
								.getDataLeituraCampo());
					} else {
						medicaoHistorico
								.setDataLeituraAtualInformada(new Date());
						medicaoHistorico.setDataLeituraCampo(new Date());
					}
				}

				// Set Leiturista
				medicaoHistorico.setLeiturista(leit);

				// Set Leituira Situação
				Integer leituraSitucao = LeituraSituacao.NAO_REALIZADA;
				if (dado.getLeituraHidrometro() != null
						&& dado.getLeituraHidrometro().intValue() != -1) {
					medicaoHistorico.setLeituraAtualInformada(dado
							.getLeituraHidrometro());
					medicaoHistorico.setLeituraCampo(dado
							.getLeituraHidrometro());
					if (dado.getIndicadorConfirmacaoLeitura().intValue() == 0) {
						leituraSitucao = LeituraSituacao.REALIZADA;
					} else if (dado.getIndicadorConfirmacaoLeitura().intValue() == 1) {
						leituraSitucao = LeituraSituacao.CONFIRMADA;
					}
				} else {
					medicaoHistorico.setLeituraAtualInformada(null);
					medicaoHistorico.setLeituraCampo(null);
					leituraSitucao = LeituraSituacao.NAO_REALIZADA;
				}

				// Seleciona a Situação da Leitura
				hql = new StringBuffer("FROM LeituraSituacao l where l.id =");
				hql.append(leituraSitucao.toString());
				medicaoHistorico
						.setLeituraSituacaoAtual((LeituraSituacao) session
								.createQuery(hql.toString()).uniqueResult());

				if (!inserir) {
					// Atualizar o Histórico Medição
					session.update(medicaoHistorico);
				} else {
					// Inserir em Histórico Medição
					// Selecionar Tipo de Medição
					if (dado.getTipoMedicao() == 0) {
						String query = "update Imovel set leituraAnormalidade = :ltan";

						session.createQuery(query).setInteger("ltan",
								dado.getCodigoAnormalidade());

					} else {

						hql = new StringBuffer(
								"FROM MedicaoTipo m where m.id = ");
						hql.append(dado.getTipoMedicao());
						medicaoHistorico.setMedicaoTipo((MedicaoTipo) session
								.createQuery(hql.toString()).uniqueResult());

						// Set Ligacao de Agua ou Imovel
						if (medicaoHistorico.getMedicaoTipo().getId().equals(
								MedicaoTipo.LIGACAO_AGUA)) {
							medicaoHistorico.setImovel(null);
							medicaoHistorico.setLigacaoAgua(ligacaoAgua);
						} else if (medicaoHistorico.getMedicaoTipo().getId()
								.equals(MedicaoTipo.POCO)) {
							medicaoHistorico.setImovel(imovel);
							medicaoHistorico.setLigacaoAgua(null);
						}

						// Set Ano Mes de Referencia
						medicaoHistorico.setAnoMesReferencia(anoMesFaturamento
								.intValue());

						// Set Numero Vezes Consecutivas Ocorrencia Anormalidade
						medicaoHistorico
								.setNumeroVezesConsecutivasOcorrenciaAnormalidade(null);

						// Selecionar a data anterior
						// Set Valores Anteriores de Data e Leitura
						int anoMesAnterior = Util.subtrairMesDoAnoMes(
								anoMesFaturamento.intValue(), 1);
						int ano2MesAnterior = Util.subtrairMesDoAnoMes(
								anoMesFaturamento.intValue(), 2);
						hql = new StringBuffer(
								"FROM MedicaoHistorico m where (m.anoMesReferencia = ");
						hql.append(anoMesAnterior);
						hql.append(" or m.anoMesReferencia = ");
						hql.append(ano2MesAnterior);
						hql.append(") and (m.imovel.id = ");
						hql.append(dado.getMatriculaImovel().toString());
						hql.append(" or m.ligacaoAgua.id = ");
						hql.append(dado.getMatriculaImovel().toString());
						hql.append(") and m.medicaoTipo.id  = ");
						hql.append(dado.getTipoMedicao());
						hql.append(" order by m.anoMesReferencia DESC");
						List l = session.createQuery(hql.toString()).list();

						// Se tem algum dado de Medicao Historico dos 2 meses
						// anteriores
						if (l.size() > 0) {
							MedicaoHistorico medicao = (MedicaoHistorico) l
									.get(0);
							medicaoHistorico
									.setDataLeituraAnteriorFaturamento(medicao
											.getDataLeituraAtualFaturamento());
							medicaoHistorico
									.setLeituraAnteriorFaturamento(medicao
											.getLeituraAtualFaturamento());
							medicaoHistorico
									.setLeituraAnteriorInformada(medicao
											.getLeituraAtualInformada());
							medicaoHistorico.setLeituraSituacaoAnterior(medicao
									.getLeituraSituacaoAtual());

						} else {
							// if(dado.getDataInstalacao()!=null){
							// medicaoHistorico.setDataLeituraAnteriorFaturamento(dado
							// .getDataInstalacao());
							// }else{

							System.out.println(" %% Dado.Data Instalacao = "+ dado.getDataInstalacao());

							hql = new StringBuffer("select h.dataInstalacao,h.numeroLeituraInstalacao from HidrometroInstalacaoHistorico h where h.id=");
							
							if (imovel != null) {
								hql.append(imovel.getHidrometroInstalacaoHistorico().getId());
							} else {
								hql.append(ligacaoAgua.getHidrometroInstalacaoHistorico().getId());
							}

							List list = session.createQuery(hql.toString()).list();
							if (list != null && !list.isEmpty()) {
								
								Object[] resultado = (Object[]) Util.retonarObjetoDeColecaoArray(list);
								
								medicaoHistorico.setDataLeituraAnteriorFaturamento((Date) resultado[0]);
								medicaoHistorico.setLeituraAnteriorFaturamento((Integer) resultado[1]);
							} else {
								medicaoHistorico.setDataLeituraAnteriorFaturamento(new Date());
								medicaoHistorico.setLeituraAnteriorFaturamento(0);								
							}
							// }
							
							
							
							medicaoHistorico.setLeituraAnteriorInformada(null);
							// Buscar por Leitura Não Realizada
							hql = new StringBuffer(
									"FROM LeituraSituacao l where l.id = ");
							hql.append(LeituraSituacao.NAO_REALIZADA);
							medicaoHistorico
									.setLeituraSituacaoAnterior((LeituraSituacao) session
											.createQuery(hql.toString())
											.uniqueResult());
						}

						// Compara se data de leitura está dentro do intervalo
						// anoMesFaturamentoAnterior e
						// anoMesFaturamentoPosterior
						// Caso contrário, dataLeitura recebe a data corrente.
						// --------------------------------------------------------------
						// CRC 826 , CRC892
						// Alterado por: Yara T. Souza
						// Date = 17/02/2008
						// --------------------------------------------------------------

						if (anoMesDataLeitura != null
								&& anoMesFaturamentoAnterior != null
								&& anoMesFaturamentoPosterior != null) {
							if (anoMesFaturamento.equals(anoMesDataLeitura)
									|| Util.compararAnoMesReferencia(
											anoMesDataLeitura,
											anoMesFaturamentoAnterior, "=")
									|| Util.compararAnoMesReferencia(
											anoMesDataLeitura,
											anoMesFaturamentoPosterior, "=")) {
								medicaoHistorico
										.setDataLeituraAtualInformada(dado
												.getDataLeituraCampo());
							} else {
								medicaoHistorico
										.setDataLeituraAtualInformada(new Date());
							}
						}

						if (dado.getLeituraHidrometro() != null
								&& dado.getLeituraHidrometro().intValue() > 0) {
							medicaoHistorico.setLeituraAtualInformada(dado
									.getLeituraHidrometro());
						} else {
							medicaoHistorico.setLeituraAtualInformada(null);
						}

						// Compara se data de leitura está dentro do intervalo
						// anoMesFaturamentoAnterior e
						// anoMesFaturamentoPosterior
						// Caso contrário, dataLeitura recebe a data corrente.
						// --------------------------------------------------------------
						// CRC 826 , CRC892
						// Alterado por: Yara T. Souza
						// Date = 17/02/2008
						// --------------------------------------------------------------
						if (anoMesDataLeitura != null
								&& anoMesFaturamentoAnterior != null
								&& anoMesFaturamentoPosterior != null) {
							if (anoMesFaturamento.equals(anoMesDataLeitura)
									|| Util.compararAnoMesReferencia(
											anoMesDataLeitura,
											anoMesFaturamentoAnterior, "=")
									|| Util.compararAnoMesReferencia(
											anoMesDataLeitura,
											anoMesFaturamentoPosterior, "=")) {
								
								medicaoHistorico.setDataLeituraAtualFaturamento(dado
								.getDataLeituraCampo());
								
							} else {
								medicaoHistorico
										.setDataLeituraAtualFaturamento(new Date());
							}
						}

						if (dado.getLeituraHidrometro() != null
								&& dado.getLeituraHidrometro().intValue() > 0) {
							medicaoHistorico.setLeituraAtualFaturamento(dado
									.getLeituraHidrometro());
						}

						// Set o Numero Cosumo Mês e Informado
						medicaoHistorico.setNumeroConsumoMes(null);
						medicaoHistorico.setNumeroConsumoInformado(null);

						// Set Processamento Movimento
						medicaoHistorico
								.setLeituraProcessamentoMovimento(new Date());

						// Set Anormalidade Faturamento
						medicaoHistorico
								.setLeituraAnormalidadeFaturamento(medicaoHistorico
										.getLeituraAnormalidadeInformada());

						// Set Hidrometro Instalação histórico
						if (medicaoHistorico.getMedicaoTipo().getId().equals(
								MedicaoTipo.LIGACAO_AGUA)) {
							medicaoHistorico
									.setHidrometroInstalacaoHistorico(ligacaoAgua
											.getHidrometroInstalacaoHistorico());
						} else if (medicaoHistorico.getMedicaoTipo().getId()
								.equals(MedicaoTipo.POCO)) {
							medicaoHistorico
									.setHidrometroInstalacaoHistorico(imovel
											.getHidrometroInstalacaoHistorico());
						}

						// Set Indicador Analisado
						medicaoHistorico.setIndicadorAnalisado(new Short("2"));

						// Set Cosumo Médio
						medicaoHistorico.setConsumoMedioHidrometro(null);

						// Set Ultima Alteração
						medicaoHistorico.setUltimaAlteracao(new Date());

						// Inserir
						session.save(medicaoHistorico);
					}
				}
			}
			session.flush();
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * 
	 * Verifica se houve todos os processamentos do um determinado grupo e
	 * ano-Mês de Referencia e inserir no cronograma de atividades.
	 * 
	 * [UC0712] Atualizar Leituras e Anormalidades do Celular
	 * 
	 * @author Thiago Nascimento
	 * @date 05/12/2007
	 * 
	 * @param grupo
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public void verificarProcessamentoUltimoArquivoTexto(Integer grupo,
			Integer anoMesReferencia,Date dataRealizacao) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		try {
			StringBuffer hql = 
				new StringBuffer("select count(m.id) FROM MovimentoRoteiroEmpresa m where m.faturamentoGrupo.id =");
			
			hql.append(grupo);
			hql.append(" and m.dataHoraProcessamento is null and m.anoMesMovimento = ");
			hql.append(anoMesReferencia);
			
			Integer qtdDados = 
				(Integer) session.createQuery(hql.toString()).iterate().next();

			if (qtdDados == 0) {
				
				// Para Atividade de Registrar Leitura
				hql = new StringBuffer("FROM FaturamentoAtividadeCronograma f where f.faturamentoAtividade.id = ");
				hql.append(FaturamentoAtividade.REGISTRAR_LEITURA_ANORMALIDADE);
				hql.append(" and f.faturamentoGrupoCronogramaMensal.anoMesReferencia =");
				hql.append(anoMesReferencia);
				hql.append(" and f.faturamentoGrupoCronogramaMensal.faturamentoGrupo.id =");
				hql.append(grupo);
				
				
				FaturamentoAtividadeCronograma faturamentoAtivCronograma = 
					(FaturamentoAtividadeCronograma) session.createQuery(hql.toString()).uniqueResult();
				
				if (faturamentoAtivCronograma != null) {
					
					faturamentoAtivCronograma.setDataRealizacao(new Date());
					faturamentoAtivCronograma.setUltimaAlteracao(new Date());
					
					session.update(faturamentoAtivCronograma);
				}

				// Para atividade de Efetuar Leitura
				hql = new StringBuffer("FROM FaturamentoAtividadeCronograma f where f.faturamentoAtividade.id = ");
				hql.append(FaturamentoAtividade.EFETUAR_LEITURA);
				hql.append(" and f.faturamentoGrupoCronogramaMensal.anoMesReferencia =");
				hql.append(anoMesReferencia);
				hql.append(" and f.faturamentoGrupoCronogramaMensal.faturamentoGrupo.id =");
				hql.append(grupo);
				
				faturamentoAtivCronograma = 
					(FaturamentoAtividadeCronograma) session.createQuery(hql.toString()).uniqueResult();
				
				if (faturamentoAtivCronograma != null) {

//					hql = new StringBuffer(
//							"select max(m.dataHoraProcessamento) FROM MovimentoRoteiroEmpresa m where m.faturamentoGrupo.id = ");
//					hql.append(grupo);
//					Object o = session.createQuery(hql.toString())
//							.uniqueResult();
					
					faturamentoAtivCronograma.setDataRealizacao(dataRealizacao);
					faturamentoAtivCronograma.setUltimaAlteracao(new Date());
					session.update(faturamentoAtivCronograma);
				}

				// Para atividade de Consitir Leitura
				hql = new StringBuffer("FROM FaturamentoAtividadeCronograma f where f.faturamentoAtividade.id = ");
				hql.append(FaturamentoAtividade.CONSISTIR_LEITURAS_E_CALCULAR_CONSUMOS);
				hql.append(" and f.faturamentoGrupoCronogramaMensal.anoMesReferencia =");
				hql.append(anoMesReferencia);
				hql.append(" and f.faturamentoGrupoCronogramaMensal.faturamentoGrupo.id =");
				hql.append(grupo);
				
				
				faturamentoAtivCronograma = 
					(FaturamentoAtividadeCronograma) session.createQuery(hql.toString()).uniqueResult();
				
				if (faturamentoAtivCronograma != null) {
					
					faturamentoAtivCronograma.setDataRealizacao(new Date());
					faturamentoAtivCronograma.setUltimaAlteracao(new Date());
					session.update(faturamentoAtivCronograma);
				}
			}
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * 
	 * Método que busca a descarição da LeituraAnormalidade que tem o código
	 * passado como parâmetro.
	 * 
	 * [UC0712] Atualizar Leituras e Anormalidades do Celular
	 * 
	 * @author Thiago Nascimento
	 * @date 12/12/2007
	 * 
	 * @param id
	 * @return String com a descricao da LeituraAnormalidade
	 * @throws ErroRepositorioException
	 */
	public String buscarDescricaoLeituraAnormalidade(Integer id)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String descricao = "";
		try {
			StringBuffer hql = new StringBuffer(
					"select l.descricao FROM LeituraAnormalidade l where l.id =");
			hql.append(id);
			descricao = (String) session.createQuery(hql.toString())
					.uniqueResult();
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return descricao;
	}

	/**
	 * 
	 * Pesquisar o imovel pela Matrícula.
	 * 
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 * 
	 * @author Raphael Rossiter
	 * @date 17/09/2007
	 * 
	 * @return Collection
	 * @exception ErroRepositorioException
	 */
	public Object[] pesquisarImovelPelaMatricula(Integer matricula)
			throws ErroRepositorioException {

		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select im.id," // 0
					+ "im.ligacaoAguaSituacao.id, " // 1
					+ "im.ligacaoEsgotoSituacao.id, " // 2
					+ "li.id, " // 3
					+ "hia.id, " // 4
					+ "hdra.id, " // 5
					+ "hdra.numeroDigitosLeitura, " // 6
					+ "fst.id," // 7
					+ "im.pocoTipo.id, " // 8
					+ "hie.id, " // 9
					+ "hdre.id, " // 10
					+ "im.quadra.rota.indicadorAjusteConsumo, " // 11
					+ "im.ligacaoAgua.numeroConsumoMinimoAgua, " // 12
					+ "im.indicadorImovelCondominio, " // 13
					+ "fst.indicadorParalisacaoFaturamento, " // 14
					+ "im.indicadorDebitoConta, " // 15
					+ "le.id, " // Ligacao Esgoto //16
					+ "le.consumoMinimo, " // 17
					+ "hia.dataInstalacao, " // 18
					+ "ct.id, " // 19
					+ "le.percentualAguaConsumidaColetada, " // 20
					+ "im.quantidadeEconomias, " // 21
					+ "hdre.numeroDigitosLeitura, " // 22
					+ "hie.dataInstalacao, "// 23
					+ "fst.indicadorValidoAgua, " // 24
					+ "fst.indicadorValidoEsgoto, " // 25
					+ "esferaPoderClieResp.id, " // 26
					+ "im.ligacaoAguaSituacao.indicadorFaturamentoSituacao, " // 27
					+ "im.ligacaoEsgotoSituacao.indicadorFaturamentoSituacao, "// 28
					+ "im.ligacaoAguaSituacao.consumoMinimoFaturamento, " // 29
					+ "im.ligacaoEsgotoSituacao.volumeMinimoFaturamento, "// 30
					+ "im.ligacaoAguaSituacao.indicadorAbastecimento, " // 31
					+ "hia.numeroLeituraInstalacao," // 32
					+ "hie.numeroLeituraInstalacao, " // 33
					/**
					 * TODO : COSANPA Adicionando parametros da ligacao de agua
					 */
					+ "li.dataLigacao, " // 34
					+ "le.dataLigacao " // 35
					+ "from Imovel im "
					+ "inner join im.quadra.rota.faturamentoGrupo "
					+ "inner join im.consumoTarifa ct "
					+ "left join im.ligacaoAgua li "
					+ "left join im.ligacaoAgua.hidrometroInstalacaoHistorico hia "
					+ "left join im.ligacaoAgua.hidrometroInstalacaoHistorico.hidrometro hdra "
					+ "left join im.hidrometroInstalacaoHistorico hie "
					+ "left join im.hidrometroInstalacaoHistorico.hidrometro hdre "
					+ "left join im.faturamentoSituacaoTipo fst "
					+ "left join im.ligacaoEsgoto le "
					+ "left join im.clienteImoveis clieImovResp with (clieImovResp.clienteRelacaoTipo.id = :clienteResponsavel and clieImovResp.dataFimRelacao is null) "
					+ "left join clieImovResp.cliente clieResp "
					+ "left join clieResp.clienteTipo tipoClieResp "
					+ "left join tipoClieResp.esferaPoder esferaPoderClieResp "
					+ "where im.id = :matricula ";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"matricula", matricula).setInteger("clienteResponsavel",
					ClienteRelacaoTipo.RESPONSAVEL.intValue()).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Atualiza Movimento Roteiro Empresa 
	 *
	 * @author Raphael Rossiter
	 * @date 31/03/2010
	 *
	 * @param dado
	 * @param anoMesReferencia
	 * @param isCelular
	 * @return MovimentoRoteiroEmpresa
	 * @throws ErroRepositorioException
	 */
	public MovimentoRoteiroEmpresa atualizarMovimentoRoteiroEmpresa(DadosMovimentacao dado, Integer anoMesReferencia, 
			boolean isCelular) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		MovimentoRoteiroEmpresa movimento = null;

		try {
			
			String hql1 = "FROM SistemaParametro";
			SistemaParametro sistemaParametro = (SistemaParametro) session.createQuery(hql1).uniqueResult();
			
			StringBuffer hql = new StringBuffer("FROM MovimentoRoteiroEmpresa m where m.anoMesMovimento =");
			hql.append(anoMesReferencia);
			hql.append(" and m.imovel.id =");
			hql.append(dado.getMatriculaImovel());
	
			if (dado.getTipoMedicao() != null && dado.getTipoMedicao() != 0) {
				hql.append(" and m.medicaoTipo.id = ");
				hql.append(dado.getTipoMedicao());
			}else{
				hql.append(" and m.medicaoTipo.id is null ");
			}
			
			movimento = (MovimentoRoteiroEmpresa) session.createQuery(hql.toString()).uniqueResult();

			if (movimento != null) {
				
				if (dado.getLeituraHidrometro() != null && dado.getLeituraHidrometro().intValue() != -1) {
					movimento.setNumeroLeituraHidrometro(dado.getLeituraHidrometro());
				} 
				else {
					movimento.setNumeroLeituraHidrometro(null);
				}
	
				hql1 = "FROM LeituraAnormalidade l where l.id =" + dado.getCodigoAnormalidade();
	
				movimento.setLeituraAnormalidade((LeituraAnormalidade) session.createQuery(hql1).uniqueResult());
				movimento.setIndicadorConfirmacaoLeitura(new Short((short) dado.getIndicadorConfirmacaoLeitura()));

				//	 Compara se data de leitura está dentro do intervalo
				//	 anoMesFaturamentoAnterior e anoMesFaturamentoPosterior
				//	 Caso contrário, dataLeitura recebe a data corrente.
				//	 --------------------------------------------------------------
				//	 CRC 826 , CRC892
				//	 Alterado por: Yara T. Souza
				//	 Date = 17/02/2008
				//	 --------------------------------------------------------------
				Integer anoMesFaturamento = sistemaParametro.getAnoMesFaturamento();
				
				
				//TEMPO DE LEITURA
				if (anoMesFaturamento != null) {
				
					Integer anoMesDataLeitura = Util.formataAnoMes(dado.getDataLeituraCampo());
					Integer anoMesFaturamentoAnterior = Util.subtrairMesDoAnoMes(anoMesFaturamento, 1);
					Integer anoMesFaturamentoPosterior = Util.somaUmMesAnoMesReferencia(anoMesFaturamento);

					if (anoMesDataLeitura != null && anoMesFaturamentoAnterior != null && anoMesFaturamentoPosterior != null) {
					
						if (anoMesFaturamento.equals(anoMesDataLeitura) || 
							Util.compararAnoMesReferencia(anoMesDataLeitura, anoMesFaturamentoAnterior, "=") || 
							Util.compararAnoMesReferencia(anoMesDataLeitura, anoMesFaturamentoPosterior, "=")) {
					
							movimento.setTempoLeitura(dado.getDataLeituraCampo());
						} 
						else {
							movimento.setTempoLeitura(new Date());
						}	
					}
				}

				movimento.setUltimaAlteracao(new Date());
				movimento.setDataHoraProcessamento(new Date());
				movimento.setIndicadorAtualizacaoLeitura(new Integer(1));
				
				StringBuilder update = new StringBuilder("update gcom.micromedicao.MovimentoRoteiroEmpresa set \n");
			
				if ( movimento.getNumeroLeituraHidrometro() != null ){
					update.append(" numeroLeituraHidrometro = :numeroLeituraHidrometro, \n");
				}else{
					update.append(" numeroLeituraHidrometro = null, \n");
				}
			
				if ( movimento.getLeituraAnormalidade() != null ){
					update.append(" leituraAnormalidade = :leituraAnormalidade, \n");
				} else {
					update.append(" leituraAnormalidade = null, \n");
				}
			
				if ( movimento.getIndicadorConfirmacaoLeitura() != null ){
					update.append(" indicadorConfirmacaoLeitura = :indicadorConfirmacaoLeitura, \n");
				}
			
				if ( movimento.getTempoLeitura() != null ){
					update.append(" tempoLeitura = :tempoLeitura, \n");
				}
				
				if (isCelular){
					update.append(" indicadorAtualizacaoLeitura = :indicadorAtualizacaoLeitura, \n");
					update.append(" ultimaAlteracao = :ultimaAlteracao, \n");
					update.append(" dataHoraProcessamento = :dataHoraProcessamento \n ");
					update.append("where id = :id ");
				}
				else{
					update.append(" indicadorAtualizacaoLeitura = :indicadorAtualizacaoLeitura, \n");
					update.append(" ultimaAlteracao = :ultimaAlteracao \n");
					update.append("where id = :id ");
				}
				
				session.clear();
				Query query = session.createQuery(update.toString());
			
				if ( movimento.getNumeroLeituraHidrometro() != null ){
					query.setInteger("numeroLeituraHidrometro", movimento.getNumeroLeituraHidrometro() );
				}
			
				if ( movimento.getLeituraAnormalidade() != null ){
					query.setInteger("leituraAnormalidade", movimento.getLeituraAnormalidade().getId() );
				}
			
				if ( movimento.getIndicadorConfirmacaoLeitura() != null ){
					query.setShort("indicadorConfirmacaoLeitura", movimento.getIndicadorConfirmacaoLeitura() );
				}
			
				if ( movimento.getTempoLeitura() != null ){
					query.setTimestamp("tempoLeitura", movimento.getTempoLeitura() );
				}

				if (isCelular){
					query.setInteger("indicadorAtualizacaoLeitura", movimento.getIndicadorAtualizacaoLeitura() )
						.setTimestamp("ultimaAlteracao", movimento.getUltimaAlteracao() )
						.setTimestamp("dataHoraProcessamento", movimento.getDataHoraProcessamento() )
						.setInteger("id", movimento.getId() )
						.executeUpdate();
				}
				else{
					query
					.setInteger("indicadorAtualizacaoLeitura", movimento.getIndicadorAtualizacaoLeitura() )
					.setTimestamp("ultimaAlteracao", movimento.getUltimaAlteracao() )
					.setInteger("id", movimento.getId() )
					.executeUpdate();
				}
				
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return movimento;
	}

	/**
	 * Buscar Rota a qual o imóvel pertence.
	 * 
	 * @param matricula
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Rota buscarRotaDoImovel(Integer matricula) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Rota rota = null;

		try {
			
			StringBuffer hql = new StringBuffer("FROM Rota r ");
			hql.append("inner join fetch r.faturamentoGrupo grupo ");
			hql.append("inner join fetch r.leituraTipo leituraTipo ");
			hql.append("where r.id = ");
				hql.append("(select i.rotaAlternativa.id from Imovel i where i.id =");
				hql.append(matricula);
				hql.append(")");
			rota = (Rota) session.createQuery(hql.toString()).uniqueResult();
			
			if (rota == null || rota.getId() == null) {
				hql = new StringBuffer("FROM Rota r ");
				hql.append("inner join fetch r.faturamentoGrupo grupo ");
				hql.append("inner join fetch r.leituraTipo leituraTipo ");
				hql.append("where r.id = ");
					hql.append("(select q.rota.id from Quadra q where q.id = (select i.quadra.id from Imovel i where i.id =");
					hql.append(matricula);
					hql.append("))");
				rota = (Rota) session.createQuery(hql.toString()).uniqueResult();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return rota;
	}

	/**
	 * [UC0629] Consultar Arquivo Texto Leitura.
	 * 
	 * Atualizar Situação do Arquivo Texto.
	 * 
	 * 
	 * @author Thiago Nascimento
	 * @date 29/01/2008
	 * 
	 * @param id
	 * @param situacaoNova
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoTexto(Integer id, Integer situacaoNova, String motivoFinalizacao)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			String sql = "update gcom.micromedicao.ArquivoTextoRoteiroEmpresa atre "
					+ " set situacaoTransmissaoLeitura = :idSituacaoTransmissaoLeituraNova, "
					+ " ultimaAlteracao = :data, motivoFinalizacao = :motivoFinalizacao "
					+ " where atre.id = :id";

			session.createQuery(sql)
			  .setInteger("id",id)
			  .setInteger("idSituacaoTransmissaoLeituraNova",situacaoNova)
			  .setString("motivoFinalizacao",motivoFinalizacao)
			  .setTimestamp("data", new Date())
			  .executeUpdate();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * 
	 * Atualizar a situação do Arquivo Roteiro Empresa para 4, para o arquivo
	 * com grupo, anoMesReferencia, localidade e setor e quadra passados como
	 * parametros.
	 * 
	 * @param grupo
	 * @param dado
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoRoteiroEmpresaParaFinalizado(
			FaturamentoGrupo grupo, DadosMovimentacao dado, Integer idRota)
			throws ErroRepositorioException {
		ArquivoTextoRoteiroEmpresa arquivoTextoRoteiro = null;
		Session session = HibernateUtil.getSession();
		try {

			StringBuffer hql = new StringBuffer(
					"FROM ArquivoTextoRoteiroEmpresa a where a.situacaoTransmissaoLeitura.id in (3,5) and a.anoMesReferencia =");
			hql.append(grupo.getAnoMesReferencia());
			hql.append(" and a.faturamentoGrupo.id = ");
			hql.append(grupo.getId());
			hql.append(" and a.rota.id = ");
			hql.append(idRota);
			// hql.append(" and a.localidade.id =");
			// hql.append(dado.getLocalidade());
			// hql.append(" and ((a.codigoSetorComercial1 = ");
			// hql.append(dado.getSetorComercial());
			// hql.append(" and ((a.codigoSetorComercial1 = ");
			// hql.append(dado.getSetorComercial());
			// hql.append(" and a.numeroQuadraInicial1 <= ");
			// hql.append(dado.getNumeroQuadra());
			// hql.append(" and a.numeroQuadraFinal1 >=");
			// hql.append(dado.getNumeroQuadra());
			// hql.append(") or (a.codigoSetorComercial2 = ");
			// hql.append(dado.getSetorComercial());
			// hql.append(" and a.numeroQuadraInicial2 <= ");
			// hql.append(dado.getNumeroQuadra());
			// hql.append(" and a.numeroQuadraFinal2 >=");
			// hql.append(dado.getNumeroQuadra());
			// hql.append(") or (a.codigoSetorComercial3 = ");
			// hql.append(dado.getSetorComercial());
			// hql.append(" and a.numeroQuadraInicial3 <= ");
			// hql.append(dado.getNumeroQuadra());
			// hql.append(" and a.numeroQuadraFinal3 >=");
			// hql.append(dado.getNumeroQuadra());
			hql.append("))");

			arquivoTextoRoteiro = (ArquivoTextoRoteiroEmpresa) session
					.createQuery(hql.toString()).uniqueResult();
			if (arquivoTextoRoteiro != null) {
				hql = new StringBuffer(
						"FROM SituacaoTransmissaoLeitura s where s.id =");
				hql.append(SituacaoTransmissaoLeitura.TRANSMITIDO);
				arquivoTextoRoteiro
						.setSituacaoTransmissaoLeitura((SituacaoTransmissaoLeitura) session
								.createQuery(hql.toString()).uniqueResult());
				arquivoTextoRoteiro.setUltimaAlteracao(new Date());
				session.update(arquivoTextoRoteiro);
				session.flush();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

    public Collection buscarImoveisPorRota(Integer idRota, String empresa, Integer anoMesFaturamento)
                                                                                                     throws ErroRepositorioException {
        Collection retorno = null;
        Session session = HibernateUtil.getSession();

        try {
            StringBuilder consulta = new StringBuilder();
            consulta.append("SELECT imovel.id, "); // 0
            consulta.append("   localidade.id, "); // 1
            consulta.append("   movimento.codigoSetorComercial, "); // 2
            consulta.append("   movimento.numeroQuadra, "); // 3
            consulta.append("   imovel.lote, "); // 4
            consulta.append("   imovel.subLote, "); // 5
            consulta.append("   ligacaoAgua.id, "); // 6
            consulta.append("   hidInstHistoricoAgua.id, "); // 7
            consulta.append("   hidInstHistoricoPoco.id, "); // 8
            consulta.append("   rota.indicadorFiscalizarSuprimido, "); // 9
            consulta.append("   rota.indicadorFiscalizarCortado, "); // 10
            consulta.append("   ligacaoAguaSituacao.id, "); // 11
            consulta.append("   ligacaoEsgotoSituacao.id, "); // 12
            consulta.append("   faturamentoSituacaoTipo.indicadorParalisacaoLeitura, "); // 13
            consulta.append("   medTipoAgua.id, "); // 14
            consulta.append("   medTipoPoco.id, "); // 15
            consulta.append("   hidInstHistoricoAgua, "); // 16
            consulta.append("   hidInstHistoricoPoco, "); // 17
            consulta.append("   ligacaoAguaSituacao.indicadorFaturamentoSituacao, "); // 18
            consulta.append("   ligacaoEsgotoSituacao.indicadorFaturamentoSituacao, "); // 19
            consulta.append("   imovel.numeroImovel, "); // 20
            consulta.append("   imovel.numeroSequencialRota, "); // 21
            consulta.append("   ligacaoAgua.dataSupressao, "); // 22
            consulta.append("   movimento.numeroLeituraHidrometro, "); // 23
            consulta.append("   movimento.tempoLeitura, "); // 24
            consulta.append("   leituraAnormalidade, "); // 25
            consulta.append("   movimento.dataHoraProcessamento, "); // 26
            consulta.append("   movimento.localidade, "); // 27
            consulta.append("   movimento.faturamentoGrupo, "); // 28
            consulta.append("   movimento.rota, "); // 29
            consulta.append("   movimento.anoMesMovimento, "); // 30
            consulta.append("   faturamentoSituacaoTipo.indicadorValidoAgua, "); // 31
            consulta.append("   faturamentoSituacaoTipo.indicadorValidoEsgoto, "); // 32
            consulta.append("   faturamentoSituacaoTipo.id "); // 33
            consulta.append("FROM MovimentoRoteiroEmpresa movimento ");
            consulta.append("   LEFT JOIN movimento.localidade localidade ");
            consulta.append("   LEFT JOIN movimento.imovel imovel ");
            consulta.append("   LEFT JOIN imovel.imovelPerfil imovelPerfil ");
            consulta.append("   LEFT JOIN imovel.ligacaoAgua ligacaoAgua ");
            consulta.append("   LEFT JOIN imovel.hidrometroInstalacaoHistorico hidInstHistoricoPoco ");
            consulta.append("   LEFT JOIN ligacaoAgua.hidrometroInstalacaoHistorico hidInstHistoricoAgua ");
            consulta.append("   LEFT JOIN fetch hidInstHistoricoAgua.hidrometroProtecao ");
            consulta.append("   LEFT JOIN fetch hidInstHistoricoAgua.hidrometroLocalInstalacao ");
            consulta.append("   LEFT JOIN fetch hidInstHistoricoPoco.hidrometroProtecao ");
            consulta.append("   LEFT JOIN fetch hidInstHistoricoPoco.hidrometroLocalInstalacao ");
            consulta.append("   LEFT JOIN hidInstHistoricoAgua.medicaoTipo medTipoAgua ");
            consulta.append("   LEFT JOIN hidInstHistoricoPoco.medicaoTipo medTipoPoco ");
            consulta.append("   LEFT JOIN movimento.rota rota ");
            consulta.append("   LEFT JOIN rota.empresa empresaRota ");
            consulta.append("   LEFT JOIN movimento.roteiroEmpresa roteiroEmpresa ");
            consulta.append("   LEFT JOIN rota.empresa empresa ");
            consulta.append("   LEFT JOIN rota.faturamentoGrupo faturamentoGrupo ");
            consulta.append("   LEFT JOIN imovel.ligacaoAguaSituacao ligacaoAguaSituacao ");
            consulta.append("   LEFT JOIN imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao ");
            consulta.append("   LEFT JOIN imovel.faturamentoSituacaoTipo faturamentoSituacaoTipo ");
            consulta.append("   LEFT JOIN rota.leituraTipo leituraTipo ");
            consulta.append("   LEFT JOIN localidade.gerenciaRegional gerenciaRegional ");
            consulta.append("   LEFT JOIN movimento.leituraAnormalidade leituraAnormalidade ");
            consulta.append("WHERE rota.id = :idRota AND movimento.anoMesMovimento = :anoMesFaturamento ");
            consulta.append(" and (movimento.numeroHidrometro is not null and movimento.numeroHidrometro <> '') ");

            if (empresa.toUpperCase().equals("COMPESA")) {
                consulta.append(" ORDER BY movimento.numeroSequencialRota,empresa.id,localidade.id,movimento,movimento.numeroQuadra,movimento.numeroLoteImovel,movimento.numeroSubloteImovel");
            } else {
                if (empresa.toUpperCase().equals("COSAMA")) {
                    consulta.append(" ORDER BY localidade.id, movimento.codigoSetorComercial, imovel.quadra.numeroQuadra, imovel.lote, imovel.subLote");
                } else { 
                	consulta.append(" order by imovel.localidade.id, imovel.setorComercial.codigo, imovel.quadra.numeroQuadra, imovel.lote, imovel.subLote ");
                }
            }

            retorno = session.createQuery(consulta.toString())
                             .setInteger("idRota", idRota)
                             .setInteger("anoMesFaturamento", anoMesFaturamento)
                             .list();

        } catch (HibernateException e) {
            throw new ErroRepositorioException(e,
                                               "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 24/04/2008
	 * 
	 * @param idImovel
	 * @param anoMesReferenciaInicio
	 * @param anoMesReferenciaFim
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection obterUltimosConsumosImovel(Integer idImovel,
			Integer tipoLigacao) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT cshi.id, cshi.referenciaFaturamento, cshi.numeroConsumoFaturadoMes, "// 0,1,2
					+ "consumoAnormalidade.id, ligacaoTipo.id "// 3,4
					+ "FROM ConsumoHistorico cshi "
					+ "INNER JOIN cshi.imovel imovel "
					+ "INNER JOIN cshi.ligacaoTipo ligacaoTipo "
					+ "LEFT JOIN cshi.consumoAnormalidade consumoAnormalidade "
					+ "WHERE imovel.id = :idImovel "
					+ "AND ligacaoTipo.id = :tipoLigacao "
					+ "ORDER BY cshi.referenciaFaturamento desc";

			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel).setInteger("tipoLigacao", tipoLigacao)
					.setMaxResults(6).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 24/04/2008
	 * 
	 * @param consumoHistorico
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer obterLeituraAnormalidadeFaturamentoMedicaoHistorico(
			ConsumoHistorico consumoHistorico) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT ltan.id "// 0
					+ "FROM MedicaoHistorico mdhi "
					+ "INNER JOIN mdhi.leituraAnormalidadeFaturamento ltan ";

			if (consumoHistorico.getLigacaoTipo().getId().equals(
					LigacaoTipo.LIGACAO_AGUA)) {

				consulta = consulta
						+ "INNER JOIN mdhi.ligacaoAgua lagu "
						+ "WHERE lagu.id = :idImovel AND mdhi.anoMesReferencia = :anoMesReferencia ";
			} else {

				consulta = consulta
						+ "INNER JOIN mdhi.imovel imovel "
						+ "WHERE imovel.id = :idImovel AND mdhi.anoMesReferencia = :anoMesReferencia ";
			}

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", consumoHistorico.getImovel().getId())
					.setInteger("anoMesReferencia",
							consumoHistorico.getReferenciaFaturamento())
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0006] - Obter dados dos tipos de medição
	 * 
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 * 
	 * @param imovel
	 * @param anoMesReferencia
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Object[] obterDadosTiposMedicao(Integer idImovel,
			Integer idLigacaoAgua, Integer anoMesReferencia)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;
		/*TODO COSANPA - Adicionada a coluna de data da Leitura Atual Informada no select para que possa ser
		 * incluída a informação no registro do Tipo 08 para o IS*/
		try {
			consulta = "SELECT mdhi.leituraAtualFaturamento, mdhi.dataLeituraAtualFaturamento, "// 0,1
					+ "ltst.id, mdhi.consumoMedioHidrometro, "// 2,3
					+ "mdhi.leituraAtualInformada, mdhi.dataLeituraAtualInformada "// 4,5
					+ "FROM MedicaoHistorico mdhi "
					+ "LEFT JOIN mdhi.ligacaoAgua lagu "
					+ "LEFT JOIN mdhi.imovel imovel "
					+ "LEFT JOIN mdhi.leituraSituacaoAtual ltst "
					+ "WHERE mdhi.anoMesReferencia = :anoMesReferencia ";
			if (idImovel != null) {
				consulta += " AND imovel.id = " + idImovel;
			}
			if (idLigacaoAgua != null) {
				consulta += " AND lagu.id = " + idLigacaoAgua;
			}

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Remove a Relação de Leiturista com a Rota
	 * 
	 * @autor Thiago Nascimento
	 * 
	 * @param rotas
	 * @throws ErroRepositorioException
	 */
	public void removerRelacaoRotaLeiturista(Collection rotas)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {

			Iterator it = rotas.iterator();

			while (it.hasNext()) {
				Rota rota = (Rota) it.next();
				rota.setLeiturista(null);
				rota.setNumeroSequenciaLeitura(null);
				rota.setUltimaAlteracao(new Date());

				session.update(rota);

			}
			session.flush();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * Avalia se existe algum arquivo liberado para o leiturista do arquivo
	 * passado no parametro.
	 * 
	 * @autor Thiago Nascimento
	 * 
	 * @param idArquivo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean temArquivoLiberado(Integer idArquivo)
			throws ErroRepositorioException {
		boolean retorno = false;
		Session session = HibernateUtil.getSession();
		try {
			StringBuffer hql = new StringBuffer(
					"SELECT a.id FROM ArquivoTextoRoteiroEmpresa a where a.situacaoTransmissaoLeitura.id = 2");
			hql
					.append(" and a.leiturista.id = (select a2.leiturista.id from ArquivoTextoRoteiroEmpresa a2 where a2.id = ");
			hql.append(idArquivo);
			hql.append(") and a.id != ");
			hql.append(idArquivo);

			Collection arqs = session.createQuery(hql.toString()).list();

			if (arqs != null && !arqs.isEmpty()) {
				retorno = true;
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * 
	 * Retorno o valor maximo da Sequencia de Rota do Leiturista
	 * 
	 * @author Thiago Nascimento
	 * 
	 * @param leiturista
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer numeroSequenciaUltimaRota(Integer leiturista)
			throws ErroRepositorioException {
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			StringBuffer hql = new StringBuffer(
					"select max(rota.numeroSequenciaLeitura) FROM Rota rota where rota.leiturista.id = ");
			hql.append(leiturista);

			retorno = (Integer) session.createQuery(hql.toString())
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * Retorno o valor máximo do Id do Leiturista.
	 * 
	 * @author Thiago Nascimento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer maximoIdLeiturista() throws ErroRepositorioException {
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			StringBuffer hql = new StringBuffer(
					"select max(l.id) FROM Leiturista l");

			retorno = (Integer) session.createQuery(hql.toString())
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * Retorna a quantidade de Leitura feitas na Rota para o AnoMes de
	 * referencia.
	 * 
	 * @author Thiago Nascimento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer quantidadeLeiturasRealizada(Integer rota, Integer anoMes,
			Integer idServicoTipoCelular) throws ErroRepositorioException {
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			if (idServicoTipoCelular != null
					&& idServicoTipoCelular.equals(ServicoTipoCelular.LEITURA)) {
				StringBuffer hql = new StringBuffer(
						"select count(*) as contador FROM MovimentoRoteiroEmpresa m where m.tempoLeitura is not null");
				hql.append(" and m.rota.id = ");
				hql.append(rota);
				hql.append(" and m.anoMesMovimento = ");
				hql.append(anoMes);
				/**
                 * TODO : COSANPA
                 * Pamela Gatinho - 12/08/2011
                 * Adiconando essa validacao para contabilizar somente
                 * imoveis que possuirem conta
                 */
				hql.append(" and m.conta is not null ");
				retorno = (Integer) session.createQuery(hql.toString())
						.uniqueResult();
			}

			if (idServicoTipoCelular != null
					&& idServicoTipoCelular
							.equals(ServicoTipoCelular.IMPRESSAO_SIMULTANEA)) {
				StringBuffer hql = new StringBuffer(
						"select count(distinct m.imovel.id) "
								+ "FROM MovimentoContaPrefaturada m where ");
				hql.append(" m.rota.id = ");
				hql.append(rota);
				hql.append(" and m.anoMesReferenciaPreFaturamento = ");
				hql.append(anoMes);
				retorno = (Integer) session.createQuery(hql.toString())
						.uniqueResult();
				
				/**
                 * TODO : COSANPA
                 * Pamela Gatinho - 12/08/2011
                 * Adiconando essa validacao para contabilizar somente
                 * imoveis que possuirem conta
                 */
				hql.append(" and m.conta is not null ");
				retorno = (Integer) session.createQuery(hql.toString())
						.uniqueResult();
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * [UC0781] - Informar Consumo por Área
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descrição sobre o fluxo secundário>
	 * 
	 * <Identificador e nome do fluxo secundário>
	 * 
	 * @author Rômulo Aurélio
	 * @date 21/05/2008
	 * 
	 * @param anoMesReferenciaInformado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarAnoMesReferenciaMenorAnoMesReferenciaFaturamentoGrupo(
			int anoMesReferenciaInformado) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select count(*) count from FaturamentoGrupo fg "
					+ "where fg.anoMesReferencia <= :anoMesReferenciaInformado ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"anoMesReferenciaInformado", anoMesReferenciaInformado)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0805] - Gerar Aviso de Anormalidade
	 * 
	 * Pesquisa os dados necessérios para a geração do relatório
	 * 
	 * @author Rafael Corrêa
	 * @date 03/06/2008
	 * 
	 * @param colecaoImoveis
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAvisoAnormalidadeRelatorio(
			Collection colecaoImoveis, Integer anoMes)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT DISTINCT clieUsuario.clie_nmcliente as nomeCliente, imov.imov_id as idImovel, " // 0, 1
					+ " rota.rota_cdrota as codigoRota, imov.imov_nnsequencialrota as sequencialRota, " // 2, 3
					+ " municipio.muni_nmmunicipio as nomeMunicipio, consAnormAgua.csan_dsconsumoanormalidade as anormalidadeConsumoAgua, " // 4, 5
					+ " consAnormEsgoto.csan_dsconsumoanormalidade as anormalidadeConsumoEsgoto,  " // 6
					+ " consHistAgua.cshi_nnconsumofaturadomes as consumoFaturadoAgua, consHistAgua.cshi_nnconsumomedio as consumoMedioAgua, medHistAgua.mdhi_nnconsumomedidomes as consumoMedidoAgua, " // 7,
					// 8, 9
					+ " consHistEsgoto.cshi_nnconsumofaturadomes as consumoFaturadoEsgoto, consHistEsgoto.cshi_nnconsumomedio as consumoMedioEsgoto, medHistEsgoto.mdhi_nnconsumomedidomes as consumoMedidoEsgoto " // 10,
					// 11,
					// 12
					+ " FROM cadastro.imovel imov "
					+ " INNER JOIN cadastro.cliente_imovel clieImovUsuario "
					+ " on clieImovUsuario.imov_id = imov.imov_id and clieImovUsuario.clim_dtrelacaofim is null and clieImovUsuario.crtp_id = "
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " INNER JOIN cadastro.cliente clieUsuario "
					+ " on clieUsuario.clie_id = clieImovUsuario.clie_id "
					+ " INNER JOIN cadastro.quadra quadra "
					+ " on quadra.qdra_id = imov.qdra_id "
					+ " INNER JOIN micromedicao.rota rota "
					+ " on rota.rota_id = quadra.rota_id "
					+ " LEFT OUTER JOIN micromedicao.consumo_historico consHistAgua "
					+ " on consHistAgua.imov_id = imov.imov_id and consHistAgua.cshi_amfaturamento = :anoMes and consHistAgua.lgti_id = "
					+ LigacaoTipo.LIGACAO_AGUA
					+ " LEFT OUTER JOIN micromedicao.consumo_historico consHistEsgoto "
					+ " on consHistEsgoto.imov_id = imov.imov_id and consHistEsgoto.cshi_amfaturamento = :anoMes and consHistEsgoto.lgti_id = "
					+ LigacaoTipo.LIGACAO_ESGOTO
					+ " LEFT OUTER JOIN micromedicao.consumo_anormalidade consAnormAgua "
					+ " on consAnormAgua.csan_id = consHistAgua.csan_id "
					+ " LEFT OUTER JOIN micromedicao.consumo_anormalidade consAnormEsgoto "
					+ " on consAnormEsgoto.csan_id = consHistEsgoto.csan_id "
					+ " LEFT OUTER JOIN micromedicao.medicao_historico medHistAgua "
					+ " on imov.imov_id = medHistAgua.lagu_id and medHistAgua.mdhi_amleitura = "
					+ anoMes
					+ " and medHistAgua.medt_id = "
					+ MedicaoTipo.LIGACAO_AGUA
					+ " LEFT OUTER JOIN micromedicao.medicao_historico medHistEsgoto "
					+ " on imov.imov_id = medHistEsgoto.imov_id and medHistEsgoto.mdhi_amleitura = "
					+ anoMes
					+ " and medHistEsgoto.medt_id = "
					+ MedicaoTipo.POCO
					+ " INNER JOIN cadastro.logradouro_bairro logrBairro "
					+ " on logrBairro.lgbr_id = imov.lgbr_id "
					+ " INNER JOIN cadastro.bairro bairro "
					+ " on bairro.bair_id = logrBairro.bair_id "
					+ " INNER JOIN cadastro.municipio municipio "
					+ " on municipio.muni_id = bairro.muni_id "
					+ " WHERE imov.imov_id in (:colecaoImoveis) ";

			retorno = (Collection) session.createSQLQuery(consulta).addScalar(
					"nomeCliente", Hibernate.STRING).addScalar("idImovel",
					Hibernate.INTEGER).addScalar("codigoRota", Hibernate.SHORT)
					.addScalar("sequencialRota", Hibernate.INTEGER).addScalar(
							"nomeMunicipio", Hibernate.STRING).addScalar(
							"anormalidadeConsumoAgua", Hibernate.STRING)
					.addScalar("anormalidadeConsumoEsgoto", Hibernate.STRING)
					.addScalar("consumoFaturadoAgua", Hibernate.INTEGER)
					.addScalar("consumoMedioAgua", Hibernate.INTEGER)
					.addScalar("consumoMedidoAgua", Hibernate.INTEGER)
					.addScalar("consumoFaturadoEsgoto", Hibernate.INTEGER)
					.addScalar("consumoMedioEsgoto", Hibernate.INTEGER)
					.addScalar("consumoMedidoEsgoto", Hibernate.INTEGER)
					.setInteger("anoMes", anoMes).setParameterList(
							"colecaoImoveis", colecaoImoveis).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0805] - Gerar Aviso de Anormalidade
	 * 
	 * Pesquisa os dados necessérios para a geração do relatório
	 * 
	 * @author Rafael Corrêa
	 * @date 28/06/2008
	 * 
	 * @param gerarDadosLeituraHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosParaLeituraRelatorio(
			GerarDadosLeituraHelper gerarDadosLeituraHelper, String empresa)
			throws ErroRepositorioException {
		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta;

		try {
			consulta = "SELECT movRotEmp.mrem_ammovimento as anoMes, fatGrupo.ftgr_dsfaturamentogrupo as grupoFaturamento, " // 0, 1
					+ " loca.loca_id as idLocalidade, loca.loca_nmlocalidade as nomeLocalidade, fatAtivCron.ftac_dtprevista as dataPrevista, " // 2,
					// 3, 4
					+ " movRotEmp.mrem_cdrota as codigoRota, movRotEmp.mrem_nmleiturista as nomeLeiturista, " // 5, 6
					+ " movRotEmp.mrem_enderecoimovel as endereco, movRotEmp.mrem_inscricaoimovel as inscricao, movRotEmp.mrem_nmcliente as nomeCliente, " // 7,
					// 8, 9
					+ " movRotEmp.mrem_dsabrevcatgimovel as categoria1, movRotEmp.mrem_qteconomias as qtdeEconomiasCat1, " // 10,
					// 11
					+ " cat2.catg_dsabreviado as categoria2, movRotEmp.mrem_qteconomias2 as qtdeEconomiasCat2, " // 12,
					// 13
					+ " ligAguaSit.last_dsligacaoaguasituacao as ligacaoAgua, ligEsgSit.lest_dsligacaoesgotosituacao as ligacaoEsgoto, " // 14,
					// 15
					+ " movRotEmp.iper_id as perfilImovel, movRotEmp.mrem_nnhidrometro as numeroHidrometro, movRotEmp.mrem_nnconsumomedio as consumoMedio, " // 16,
					// 17,
					// 18
					+ " movRotEmp.mrem_nnsequencialrota as sequencialRota, movRotEmp.mrem_nnsubloteimovel as complementoSeqLeitura, imov.imov_id as idImovel " // 19,
					// 20,
					// 21
					+ " FROM micromedicao.movimento_roteiro_empr movRotEmp "
					+ " INNER JOIN faturamento.faturamento_grupo fatGrupo "
					+ " on fatGrupo.ftgr_id = movRotEmp.ftgr_id "
					+ " INNER JOIN cadastro.localidade loca "
					+ " on loca.loca_id = movRotEmp.loca_id "
					+ " INNER JOIN faturamento.fatur_grupo_crg_mensal fatGrupoCronMensal "
					+ " on fatGrupoCronMensal.ftgr_id = fatGrupo.ftgr_id and fatGrupoCronMensal.ftcm_amreferencia = fatGrupo.ftgr_amreferencia "
					+ " INNER JOIN faturamento.fatur_ativ_cronograma fatAtivCron "
					+ " on fatAtivCron.ftcm_id = fatGrupoCronMensal.ftcm_id and ftat_id = "
					+ FaturamentoAtividade.GERAR_ARQUIVO_LEITURA
					+ " INNER JOIN cadastro.imovel imov "
					+ " on imov.imov_id = movRotEmp.imov_id " 
					+ " INNER JOIN cadastro.quadra quadra on imov.qdra_id = quadra.qdra_id "
					+ " INNER JOIN atendimentopublico.ligacao_agua_situacao ligAguaSit "
					+ " on ligAguaSit.last_id = imov.last_id "
					+ " INNER JOIN atendimentopublico.ligacao_esgoto_situacao ligEsgSit "
					+ " on ligEsgSit.lest_id = imov.lest_id "
					+ " LEFT OUTER JOIN micromedicao.roteiro_empresa rotEmp "
					+ " on rotEmp.roem_id = movRotEmp.roem_id "
					+ " LEFT OUTER JOIN cadastro.categoria cat2 "
					+ " on cat2.catg_id = movRotEmp.catg_id  "
					+ " WHERE movRotEmp.mrem_ammovimento = fatGrupo.ftgr_amreferencia "
					+ " AND movRotEmp.mrem_nnhidrometro <> '' ";

			if (gerarDadosLeituraHelper.getIdGrupoFaturamento() != null) {
				consulta = consulta + " and movRotEmp.ftgr_id = "
						+ gerarDadosLeituraHelper.getIdGrupoFaturamento();
			}

			if (gerarDadosLeituraHelper.getIdRota() != null) {
				if (gerarDadosLeituraHelper.getIdRota() != null) {
					consulta = consulta + " and movRotEmp.rota_id = "
							+ gerarDadosLeituraHelper.getIdRota();
				}
			}

			if (gerarDadosLeituraHelper.getIdLocalidadeInicial() != null) {
				if (gerarDadosLeituraHelper.getIdLocalidadeFinal() != null) {
					consulta = consulta + " and (movRotEmp.loca_id between "
							+ gerarDadosLeituraHelper.getIdLocalidadeInicial()
							+ " and "
							+ gerarDadosLeituraHelper.getIdLocalidadeFinal()
							+ ")";
				} else {
					consulta = consulta + " and movRotEmp.loca_id = "
							+ gerarDadosLeituraHelper.getIdLocalidadeInicial();
				}

			}

			if (empresa.toUpperCase().equals("COMPESA")) {
				consulta = consulta
						+ " order by movRotEmp.mrem_nnsequencialrota,movRotEmp.empr_id,loca.loca_id,movRotEmp.mrem_cdsetorcomercial,movRotEmp.mrem_nnquadra,movRotEmp.mrem_nnloteimovel,movRotEmp.mrem_nnsubloteimovel";
			} else if (empresa.toUpperCase().equals("COSANPA")) {
				consulta = consulta
						+ " order by loca.loca_id, movRotEmp.mrem_cdsetorcomercial, movRotEmp.mrem_cdrota, movRotEmp.mrem_nnquadra, movRotEmp.mrem_nnloteimovel";
			} else if(empresa.toUpperCase().equals("COSAMA")){
				consulta = consulta
				        + " ORDER BY loca.loca_id, movRotEmp.mrem_cdsetorcomercial, quadra.qdra_nnquadra, imov.imov_nnlote, imov.imov_nnsublote";
			} else{
				consulta = consulta
						+ " ORDER BY loca.loca_id, movRotEmp.mrem_cdrota, imov.imov_nnsequencialrota,movRotEmp.mrem_nnsubloteimovel";
			}			

			retorno = (Collection<Object[]>) session.createSQLQuery(consulta)
					.addScalar("anoMes", Hibernate.INTEGER).addScalar(
							"grupoFaturamento", Hibernate.STRING).addScalar(
							"idLocalidade", Hibernate.INTEGER).addScalar(
							"nomeLocalidade", Hibernate.STRING).addScalar(
							"dataPrevista", Hibernate.DATE).addScalar(
							"codigoRota", Hibernate.SHORT).addScalar(
							"nomeLeiturista", Hibernate.STRING).addScalar(
							"endereco", Hibernate.STRING).addScalar(
							"inscricao", Hibernate.STRING).addScalar(
							"nomeCliente", Hibernate.STRING).addScalar(
							"categoria1", Hibernate.STRING).addScalar(
							"qtdeEconomiasCat1", Hibernate.SHORT).addScalar(
							"categoria2", Hibernate.STRING).addScalar(
							"qtdeEconomiasCat2", Hibernate.SHORT).addScalar(
							"ligacaoAgua", Hibernate.STRING).addScalar(
							"ligacaoEsgoto", Hibernate.STRING).addScalar(
							"perfilImovel", Hibernate.INTEGER).addScalar(
							"numeroHidrometro", Hibernate.STRING).addScalar(
							"consumoMedio", Hibernate.INTEGER).addScalar(
							"sequencialRota", Hibernate.INTEGER).addScalar(
							"complementoSeqLeitura", Hibernate.STRING)
					.addScalar("idImovel", Hibernate.STRING).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0805] - Gerar Aviso de Anormalidade
	 * 
	 * Pesquisa a quantidade de registros do relatório
	 * 
	 * @author Rafael Corrêa
	 * @date 28/06/2008
	 * 
	 * @param gerarDadosLeituraHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarDadosParaLeituraRelatorioCount(
			GerarDadosLeituraHelper gerarDadosLeituraHelper)
			throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta;

		try {
			consulta = "SELECT COUNT(movRotEmp.id) "
					+ " FROM MovimentoRoteiroEmpresa movRotEmp "
					+ " INNER JOIN movRotEmp.faturamentoGrupo fatGrupo "
					+ " WHERE movRotEmp.anoMesMovimento = fatGrupo.anoMesReferencia "
					+ " AND movRotEmp.numeroHidrometro <> '' ";

			if (gerarDadosLeituraHelper.getIdGrupoFaturamento() != null) {
				consulta = consulta + " and movRotEmp.faturamentoGrupo.id = "
						+ gerarDadosLeituraHelper.getIdGrupoFaturamento();
			}

			if (gerarDadosLeituraHelper.getIdRota() != null) {
				if (gerarDadosLeituraHelper.getIdRota() != null) {
					consulta = consulta
							+ " and (movRotEmp.rota.codigo = "
							+ gerarDadosLeituraHelper.getIdRota() + ")";
				}
			}

			if (gerarDadosLeituraHelper.getIdLocalidadeInicial() != null) {
				if (gerarDadosLeituraHelper.getIdLocalidadeFinal() != null) {
					consulta = consulta
							+ " and (movRotEmp.localidade.id between "
							+ gerarDadosLeituraHelper.getIdLocalidadeInicial()
							+ " and "
							+ gerarDadosLeituraHelper.getIdLocalidadeFinal()
							+ ")";
				} else {
					consulta = consulta + " and movRotEmp.localidade.id = "
							+ gerarDadosLeituraHelper.getIdLocalidadeInicial();
				}

			}

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0805] - Gerar Aviso de Anormalidade
	 * 
	 * Pesquisa os dados necessérios para a geração do relatório
	 * 
	 * @author Rafael Corrêa
	 * @date 03/06/2008
	 * 
	 * @param colecaoImoveis
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAvisoAnormalidadeRelatorio(
			FiltrarAnaliseExcecoesLeiturasHelper filtrarAnaliseExcecoesLeiturasHelper,
			Integer anoMes) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String sql;

		try {
			sql = "SELECT DISTINCT clieUsuario.clie_nmcliente as nomeCliente, imovel.imov_id as idImovel, " // 0, 1
					+ " rota.rota_cdrota as codigoRota, imovel.imov_nnsequencialrota as sequencialRota, " // 2, 3
					+ "  municipio.muni_nmmunicipio as nomeMunicipio, "; // 4

			if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo() != null
					&& !filtrarAnaliseExcecoesLeiturasHelper
							.getIdLigacaoTipo()
							.trim()
							.equalsIgnoreCase(
									"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo()
						.trim().equals("" + LigacaoTipo.LIGACAO_AGUA)) {
					sql = sql
							+ " consAnormAgua.csan_dsconsumoanormalidade as anormalidadeConsumoAgua, null as anormalidadeConsumoEsgoto, " // 5, 6
							+ " consHistAgua.cshi_nnconsumofaturadomes as consumoFaturadoAgua, consHistAgua.cshi_nnconsumomedio as consumoMedioAgua, medHistAgua.mdhi_nnconsumomedidomes as consumoMedidoAgua, " // 7,
							// 8, 9
							+ " null as consumoFaturadoEsgoto, null as consumoMedioEsgoto, null as consumoMedidoEsgoto, "; // 10,
					// 11,
					// 12
				} else {
					sql = sql
							+ " null as anormalidadeConsumoAgua, consAnormEsgoto.csan_dsconsumoanormalidade as anormalidadeConsumoEsgoto, " // 5, 6
							+ " null as consumoFaturadoAgua, consHistAgua.cshi_nnconsumomedio as consumoMedioAgua, null as consumoMedidoAgua, " // 7,
							// 8, 9
							+ " consHistEsgoto.cshi_nnconsumofaturadomes as consumoFaturadoEsgoto, consHistEsgoto.cshi_nnconsumomedio as consumoMedioEsgoto, medHistEsgoto.mdhi_nnconsumomedidomes as consumoMedidoEsgoto "; // 10,
					// 11,
					// 12
				}
			} else {
				sql = sql
						+ " consAnormAgua.csan_dsconsumoanormalidade as anormalidadeConsumoAgua, consAnormEsgoto.csan_dsconsumoanormalidade as anormalidadeConsumoEsgoto, " // 5, 6
						+ " consHistAgua.cshi_nnconsumofaturadomes as consumoFaturadoAgua, consHistAgua.cshi_nnconsumomedio as consumoMedioAgua, medHistAgua.mdhi_nnconsumomedidomes as consumoMedidoAgua, " // 7,
						// 8, 9
						+ " consHistEsgoto.cshi_nnconsumofaturadomes as consumoFaturadoEsgoto, consHistEsgoto.cshi_nnconsumomedio as consumoMedioEsgoto, medHistEsgoto.mdhi_nnconsumomedidomes as consumoMedidoEsgoto "; // 10,
				// 11,
				// 12
			}

			sql = sql
					+ " FROM cadastro.imovel imovel "
					+ " INNER JOIN cadastro.cliente_imovel clieImovUsuario "
					+ " on clieImovUsuario.imov_id = imovel.imov_id and clieImovUsuario.clim_dtrelacaofim is null and clieImovUsuario.crtp_id = "
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ " INNER JOIN cadastro.cliente clieUsuario "
					+ " on clieUsuario.clie_id = clieImovUsuario.clie_id "
					+ " INNER JOIN cadastro.setor_comercial setorComercial "
					+ " on imovel.stcm_id = setorComercial.stcm_id "
					+ " INNER JOIN cadastro.quadra quadra "
					+ " on quadra.qdra_id = imovel.qdra_id "
					+ " INNER JOIN micromedicao.rota rota "
					+ " on rota.rota_id = quadra.rota_id "
					+ " INNER JOIN cadastro.imovel_subcategoria imovelSubcategoria "
					+ " on imovel.imov_id = imovelSubcategoria.imov_id "
					+ " INNER JOIN cadastro.subcategoria subcategoria "
					+ " on imovelSubcategoria.scat_id = subcategoria.scat_id ";
			if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null
					&& !filtrarAnaliseExcecoesLeiturasHelper
							.getIdMedicaoTipo()
							.trim()
							.equalsIgnoreCase(
									"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo()
						.trim().equals("" + MedicaoTipo.LIGACAO_AGUA)) {
					sql = sql
							+ " INNER JOIN micromedicao.medicao_historico medHistAgua "
							+ " on imovel.imov_id = medHistAgua.lagu_id and medHistAgua.mdhi_amleitura = "
							+ anoMes
							+ " and medHistAgua.medt_id = "
							+ MedicaoTipo.LIGACAO_AGUA
							+ " LEFT OUTER JOIN micromedicao.leitura_anormalidade leitAnormFatAgua "
							+ " on leitAnormAgua.ltan_id = medHistAgua.ltan_idleitanormfatmt ";
				} else {
					sql = sql
							+ " INNER JOIN micromedicao.medicao_historico medHistEsgoto "
							+ " on imovel.imov_id = medHistEsgoto.imov_id and medHistEsgoto.mdhi_amleitura = "
							+ anoMes + " and medHistEsgoto.medt_id = "
							+ MedicaoTipo.POCO;
				}
			} else {
				sql = sql
						+ " LEFT OUTER JOIN micromedicao.medicao_historico medHistAgua "
						+ " on imovel.imov_id = medHistAgua.lagu_id and medHistAgua.mdhi_amleitura = "
						+ anoMes
						+ " and medHistAgua.medt_id = "
						+ MedicaoTipo.LIGACAO_AGUA
						+ " LEFT OUTER JOIN micromedicao.medicao_historico medHistEsgoto "
						+ " on imovel.imov_id = medHistEsgoto.imov_id and medHistEsgoto.mdhi_amleitura = "
						+ anoMes + " and medHistEsgoto.medt_id = "
						+ MedicaoTipo.POCO;
			}

			if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo() != null
					&& !filtrarAnaliseExcecoesLeiturasHelper
							.getIdLigacaoTipo()
							.trim()
							.equalsIgnoreCase(
									"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo()
						.trim().equals("" + LigacaoTipo.LIGACAO_AGUA)) {
					sql = sql
							+ " INNER JOIN micromedicao.consumo_historico consHistAgua "
							+ " on consHistAgua.imov_id = imovel.imov_id and consHistAgua.cshi_amfaturamento = :anoMes and consHistAgua.lgti_id = "
							+ LigacaoTipo.LIGACAO_AGUA
							+ " LEFT OUTER JOIN micromedicao.consumo_anormalidade consAnormAgua "
							+ " on consAnormAgua.csan_id = consHistAgua.csan_id ";
				} else {
					sql = sql
							+ " INNER JOIN micromedicao.consumo_historico consHistEsgoto "
							+ " on consHistEsgoto.imov_id = imovel.imov_id and consHistEsgoto.cshi_amfaturamento = :anoMes and consHistEsgoto.lgti_id = "
							+ LigacaoTipo.LIGACAO_ESGOTO
							+ " LEFT OUTER JOIN micromedicao.consumo_anormalidade consAnormEsgoto "
							+ " on consAnormEsgoto.csan_id = consHistEsgoto.csan_id ";
				}

			} else {
				sql = sql
						+ " LEFT OUTER JOIN micromedicao.consumo_historico consHistAgua "
						+ " on consHistAgua.imov_id = imovel.imov_id and consHistAgua.cshi_amfaturamento = :anoMes and consHistAgua.lgti_id = "
						+ LigacaoTipo.LIGACAO_AGUA
						+ " LEFT OUTER JOIN micromedicao.consumo_historico consHistEsgoto "
						+ " on consHistEsgoto.imov_id = imovel.imov_id and consHistEsgoto.cshi_amfaturamento = :anoMes and consHistEsgoto.lgti_id = "
						+ LigacaoTipo.LIGACAO_ESGOTO
						+ " LEFT OUTER JOIN micromedicao.consumo_anormalidade consAnormAgua "
						+ " on consAnormAgua.csan_id = consHistAgua.csan_id "
						+ " LEFT OUTER JOIN micromedicao.consumo_anormalidade consAnormEsgoto "
						+ " on consAnormEsgoto.csan_id = consHistEsgoto.csan_id ";
			}
			sql = sql + " INNER JOIN cadastro.logradouro_bairro logrBairro "
					+ " on logrBairro.lgbr_id = imovel.lgbr_id "
					+ " INNER JOIN cadastro.bairro bairro "
					+ " on bairro.bair_id = logrBairro.bair_id "
					+ " INNER JOIN cadastro.municipio municipio "
					+ " on municipio.muni_id = bairro.muni_id " + " WHERE ";

			sql = sql
					+ criarCondicionalPesquisarAvisoAnormalidadeRelatorio(filtrarAnaliseExcecoesLeiturasHelper);

			retorno = (Collection) session.createSQLQuery(sql).addScalar(
					"nomeCliente", Hibernate.STRING).addScalar("idImovel",
					Hibernate.INTEGER).addScalar("codigoRota", Hibernate.SHORT)
					.addScalar("sequencialRota", Hibernate.INTEGER).addScalar(
							"nomeMunicipio", Hibernate.STRING).addScalar(
							"anormalidadeConsumoAgua", Hibernate.STRING)
					.addScalar("anormalidadeConsumoEsgoto", Hibernate.STRING)
					.addScalar("consumoFaturadoAgua", Hibernate.INTEGER)
					.addScalar("consumoMedioAgua", Hibernate.INTEGER)
					.addScalar("consumoMedidoAgua", Hibernate.INTEGER)
					.addScalar("consumoFaturadoEsgoto", Hibernate.INTEGER)
					.addScalar("consumoMedioEsgoto", Hibernate.INTEGER)
					.addScalar("consumoMedidoEsgoto", Hibernate.INTEGER)
					.setInteger("anoMes", anoMes).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	private String criarCondicionalPesquisarAvisoAnormalidadeRelatorio(
			FiltrarAnaliseExcecoesLeiturasHelper filtrarAnaliseExcecoesLeiturasHelper) {
		String retorno = " (medHistAgua.mdhi_id is not null or medHistEsgoto.mdhi_id is not null) and "
				+ " (consHistAgua.cshi_id is not null or consHistEsgoto.cshi_id is not null) and "
				+ " imovel.imov_icexclusao = "
				+ ConstantesSistema.INDICADOR_IMOVEL_ATIVO;

		if (filtrarAnaliseExcecoesLeiturasHelper.getIdImovel() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper.getIdImovel().trim()
						.equals("")) {
			retorno = retorno + " and imovel.imov_id = "
					+ filtrarAnaliseExcecoesLeiturasHelper.getIdImovel();
		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getIdImovelCondominio() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper
						.getIdImovelCondominio().trim().equals("")) {
			retorno = retorno
					+ " and imovel.imov_idimovelcondominio = "
					+ filtrarAnaliseExcecoesLeiturasHelper
							.getIdImovelCondominio()
					+ " or imovel.imov_id = "
					+ filtrarAnaliseExcecoesLeiturasHelper
							.getIdImovelCondominio();
		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getIdFaturamentoGrupo() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper
						.getIdFaturamentoGrupo().trim().equals("")) {
			retorno = retorno
					+ " and rota.ftgr_id = "
					+ filtrarAnaliseExcecoesLeiturasHelper
							.getIdFaturamentoGrupo();
		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getIdEmpresa() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper.getIdEmpresa().trim()
						.equals("")) {
			retorno = retorno + " and rota.empr_id = "
					+ filtrarAnaliseExcecoesLeiturasHelper.getIdEmpresa();
		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getIdLocalidade() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper.getIdLocalidade()
						.trim().equals("")) {
			retorno = retorno + " and imovel.loca_id = "
					+ filtrarAnaliseExcecoesLeiturasHelper.getIdLocalidade();
		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getCodigoSetorComercial() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper
						.getCodigoSetorComercial().trim().equals("")) {
			retorno = retorno
					+ " and setorComercial.stcm_cdsetorcomercial = "
					+ filtrarAnaliseExcecoesLeiturasHelper
							.getCodigoSetorComercial();
		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getNumeroQuadraInicial() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper
						.getNumeroQuadraInicial().trim().equals("")) {
			retorno = retorno
					+ " and quadra.qdra_nnquadra between "
					+ filtrarAnaliseExcecoesLeiturasHelper
							.getNumeroQuadraInicial()
					+ " and "
					+ filtrarAnaliseExcecoesLeiturasHelper
							.getNumeroQuadraFinal();
		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getCodigoRota() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper.getCodigoRota().trim()
						.equals("")) {
			retorno = retorno + " and rota.rota_cdrota = "
					+ filtrarAnaliseExcecoesLeiturasHelper.getCodigoRota();
		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getIdUsuarioAlteracao() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper
						.getIdUsuarioAlteracao().trim().equals("")) {

			String idUsuario = filtrarAnaliseExcecoesLeiturasHelper
					.getIdUsuarioAlteracao();

			if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null
					&& !filtrarAnaliseExcecoesLeiturasHelper
							.getIdMedicaoTipo()
							.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo()
						.trim().equals("" + MedicaoTipo.LIGACAO_AGUA)) {
					retorno = retorno + " and medHistAgua.usur_idalteracao = "
							+ idUsuario;
				} else {
					retorno = retorno
							+ " and medHistEsgoto.usur_idalteracao = "
							+ idUsuario;
				}
			} else {
				retorno = retorno + " and (medHistAgua.usur_idalteracao = "
						+ idUsuario + " or medHistEsgoto.usur_idalteracao = "
						+ idUsuario + ") ";
			}

		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getIndicadorImovelCondominio() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper
						.getIndicadorImovelCondominio().trim().equals("")) {
			retorno = retorno
					+ " and imovel.imov_icimovelcondominio = "
					+ filtrarAnaliseExcecoesLeiturasHelper
							.getIndicadorImovelCondominio();
		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getIndicadorDebitoAutomatico() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper
						.getIndicadorDebitoAutomatico().trim().equals("")) {
			retorno = retorno
					+ " and imovel.imov_icdebitoconta = "
					+ filtrarAnaliseExcecoesLeiturasHelper
							.getIndicadorDebitoAutomatico();
		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getIndicadorAnalisado() != null) {
			String[] indicadorAnalisado = filtrarAnaliseExcecoesLeiturasHelper
					.getIndicadorAnalisado();
			String valores = "";

			for (int i = 0; i < indicadorAnalisado.length; i++) {
				if (indicadorAnalisado[i] != null
						&& !indicadorAnalisado[i].trim().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					valores = valores + indicadorAnalisado[i] + ", ";
				}
			}

			valores = Util.removerUltimosCaracteres(valores, 2);

			if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null
					&& !filtrarAnaliseExcecoesLeiturasHelper
							.getIdMedicaoTipo()
							.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo()
						.trim().equals("" + MedicaoTipo.LIGACAO_AGUA)) {
					retorno = retorno
							+ " and medHistAgua.mdhi_icanalisado in ( "
							+ valores + ") ";
				} else {
					retorno = retorno
							+ " and medHistEsgoto.mdhi_icanalisado in ( "
							+ valores + ") ";
				}

			} else {
				retorno = retorno + " and ( medHistAgua.mdhi_icanalisado in ( "
						+ valores + ") or medHistEsgoto.mdhi_icanalisado in ( "
						+ valores + " ) ) ";
			}

		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getIdsImovelPerfil() != null) {
			String[] idsImovelPerfil = filtrarAnaliseExcecoesLeiturasHelper
					.getIdsImovelPerfil();
			String valores = "";

			for (int i = 0; i < idsImovelPerfil.length; i++) {
				if (idsImovelPerfil[i] != null
						&& !idsImovelPerfil[i].trim().equals(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					valores = valores + idsImovelPerfil[i] + ", ";
				}
			}

			valores = Util.removerUltimosCaracteres(valores, 2);

			retorno = retorno + " and imovel.iper_id in ( " + valores + ") ";

		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getIdCategoria() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper.getIdCategoria()
						.trim().equals("")) {
			retorno = retorno + " and subcategoria.catg_id = "
					+ filtrarAnaliseExcecoesLeiturasHelper.getIdCategoria();
		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getQuantidadeEconomias() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper
						.getQuantidadeEconomias().trim().equals("")) {
			retorno = retorno
					+ " and imovel.imov_qteconomia = "
					+ filtrarAnaliseExcecoesLeiturasHelper
							.getQuantidadeEconomias();
		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getIdTipoAnormalidade() != null) {

			Integer idTipoAnormalidade = filtrarAnaliseExcecoesLeiturasHelper
					.getIdTipoAnormalidade();

			if (idTipoAnormalidade
					.equals(FiltrarAnaliseExcecoesLeiturasHelper.ANORMALIDADE_LEITURA_INFORMADA)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null
						&& !filtrarAnaliseExcecoesLeiturasHelper
								.getIdMedicaoTipo()
								.equals(
										""
												+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo()
							.trim().equals("" + MedicaoTipo.LIGACAO_AGUA)) {
						retorno = retorno
								+ " and medHistAgua.ltan_idleitanorminformada is not null ";
					} else {
						retorno = retorno
								+ " and medHistEsgoto.ltan_idleitanorminformada is not null ";
					}
				} else {
					retorno = retorno
							+ " and (medHistAgua.ltan_idleitanorminformada is not null or medHistEsgoto.ltan_idleitanorminformada is not null) ";
				}
			} else if (idTipoAnormalidade
					.equals(FiltrarAnaliseExcecoesLeiturasHelper.ANORMALIDADE_LEITURA_FATURADA)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null
						&& !filtrarAnaliseExcecoesLeiturasHelper
								.getIdMedicaoTipo()
								.equals(
										""
												+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo()
							.trim().equals("" + MedicaoTipo.LIGACAO_AGUA)) {
						retorno = retorno
								+ " and medHistAgua.ltan_idleitanormfatmt is not null ";
					} else {
						retorno = retorno
								+ " and medHistEsgoto.ltan_idleitanormfatmt is not null ";
					}
				} else {
					retorno = retorno
							+ " and (medHistAgua.ltan_idleitanormfatmt is not null or medHistEsgoto.ltan_idleitanormfatmt is not null) ";
				}
			} else {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo() != null
						&& !filtrarAnaliseExcecoesLeiturasHelper
								.getIdLigacaoTipo()
								.equals(
										""
												+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo()
							.trim().equals("" + LigacaoTipo.LIGACAO_AGUA)) {
						retorno = retorno
								+ " and consHistAgua.csan_id is not null ";
					} else {
						retorno = retorno
								+ " and consHistEsgoto.csan_id is not null ";
					}
				} else {
					retorno = retorno
							+ " and (consHistAgua.csan_id is not null or consHistEsgoto.csan_id is not null) ";
				}
			}

		}

		if (filtrarAnaliseExcecoesLeiturasHelper
				.getIdsAnormalidadeLeituraInformada() != null) {

			String[] idsAnormalidade = filtrarAnaliseExcecoesLeiturasHelper
					.getIdsAnormalidadeLeituraInformada();
			String valor = "";

			for (int i = 0; i < idsAnormalidade.length; i++) {
				String idAnormalidade = idsAnormalidade[i];

				if (idAnormalidade != null
						&& !idAnormalidade.equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					valor = valor + idAnormalidade + ", ";
				}
			}

			if (valor != null && !valor.equals("")) {

				valor = Util.removerUltimosCaracteres(valor, 2);

				if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null
						&& !filtrarAnaliseExcecoesLeiturasHelper
								.getIdMedicaoTipo()
								.equals(
										""
												+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo()
							.trim().equals("" + MedicaoTipo.LIGACAO_AGUA)) {
						retorno = retorno
								+ " and medHistAgua.ltan_idleitanorminformada in ("
								+ valor + ")";
					} else {
						retorno = retorno
								+ " and medHistEsgoto.ltan_idleitanorminformada in ("
								+ valor + ")";
					}
				} else {
					retorno = retorno
							+ " and (medHistAgua.ltan_idleitanorminformada in ("
							+ valor
							+ ")"
							+ " or medHistEsgoto.ltan_idleitanorminformada in ("
							+ valor + ") )";
				}
			}

		}

		if (filtrarAnaliseExcecoesLeiturasHelper
				.getIdsAnormalidadeLeituraFaturada() != null) {

			String[] idsAnormalidade = filtrarAnaliseExcecoesLeiturasHelper
					.getIdsAnormalidadeLeituraFaturada();
			String valor = "";

			for (int i = 0; i < idsAnormalidade.length; i++) {
				String idAnormalidade = idsAnormalidade[i];

				if (idAnormalidade != null
						&& !idAnormalidade.equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					valor = valor + idAnormalidade + ", ";
				}
			}

			if (valor != null && !valor.equals("")) {

				valor = Util.removerUltimosCaracteres(valor, 2);

				if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null
						&& !filtrarAnaliseExcecoesLeiturasHelper
								.getIdMedicaoTipo()
								.equals(
										""
												+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo()
							.trim().equals("" + MedicaoTipo.LIGACAO_AGUA)) {
						retorno = retorno
								+ " and medHistAgua.ltan_idleitanormfatmt in ("
								+ valor + ")";
					} else {
						retorno = retorno
								+ " and medHistEsgoto.ltan_idleitanormfatmt in ("
								+ valor + ")";
					}
				} else {
					retorno = retorno
							+ " and (medHistAgua.ltan_idleitanormfatmt in ("
							+ valor
							+ ")"
							+ " or medHistEsgoto.ltan_idleitanormfatmt in ("
							+ valor + ") )";
				}
			}

		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getIdsAnormalidadeConsumo() != null) {

			String[] idsAnormalidade = filtrarAnaliseExcecoesLeiturasHelper
					.getIdsAnormalidadeConsumo();
			String valor = "";

			for (int i = 0; i < idsAnormalidade.length; i++) {
				String idAnormalidade = idsAnormalidade[i];

				if (idAnormalidade != null
						&& !idAnormalidade.equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					valor = valor + idAnormalidade + ", ";
				}
			}

			if (valor != null && !valor.equals("")) {

				valor = Util.removerUltimosCaracteres(valor, 2);

				if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null
						&& !filtrarAnaliseExcecoesLeiturasHelper
								.getIdMedicaoTipo()
								.equals(
										""
												+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo()
							.trim().equals("" + MedicaoTipo.LIGACAO_AGUA)) {
						retorno = retorno + " and consHistAgua.csan_id in ("
								+ valor + ")";
					} else {
						retorno = retorno + " and consHistEsgoto.csan_id in ("
								+ valor + ")";
					}
				} else {
					retorno = retorno + " and (consHistAgua.csan_id in ("
							+ valor + ")" + " or consHistEsgoto.csan_id in ("
							+ valor + ") ) ";
				}
			}

		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getConsumoFaturadoInicial() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper
						.getConsumoFaturadoInicial().trim().equals("")) {

			String consumoInicial = filtrarAnaliseExcecoesLeiturasHelper
					.getConsumoFaturadoInicial();
			String consumoFinal = filtrarAnaliseExcecoesLeiturasHelper
					.getConsumoFaturadoFinal();

			if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo() != null
					&& !filtrarAnaliseExcecoesLeiturasHelper
							.getIdLigacaoTipo()
							.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo()
						.trim().equals("" + LigacaoTipo.LIGACAO_AGUA)) {
					retorno = retorno
							+ " and consHistAgua.cshi_nnconsumofaturadomes >= "
							+ consumoInicial
							+ " and consHistAgua.cshi_nnconsumofaturadomes <= "
							+ consumoFinal;
				} else {
					retorno = retorno
							+ " and consHistEsgoto.cshi_nnconsumofaturadomes >= "
							+ consumoInicial
							+ " and consHistEsgoto.cshi_nnconsumofaturadomes <= "
							+ consumoFinal;
				}
			} else {
				retorno = retorno
						+ " and ( ( consHistAgua.cshi_nnconsumofaturadomes >= "
						+ consumoInicial
						+ " and consHistAgua.cshi_nnconsumofaturadomes <= "
						+ consumoFinal + " ) "
						+ " or ( consHistEsgoto.cshi_nnconsumofaturadomes >= "
						+ consumoInicial
						+ " and consHistEsgoto.cshi_nnconsumofaturadomes <= "
						+ consumoFinal + " ) ) ";
			}

		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getConsumoMedidoInicial() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper
						.getConsumoMedidoInicial().trim().equals("")) {

			String consumoInicial = filtrarAnaliseExcecoesLeiturasHelper
					.getConsumoMedidoInicial();
			String consumoFinal = filtrarAnaliseExcecoesLeiturasHelper
					.getConsumoMedidoFinal();

			if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null
					&& !filtrarAnaliseExcecoesLeiturasHelper
							.getIdMedicaoTipo()
							.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo()
						.trim().equals("" + MedicaoTipo.LIGACAO_AGUA)) {
					retorno = retorno
							+ " and medHistAgua.mdhi_nnconsumomedidomes >= "
							+ consumoInicial
							+ " and medHistAgua.mdhi_nnconsumomedidomes <= "
							+ consumoFinal;
				} else {
					retorno = retorno
							+ " and medHistEsgoto.mdhi_nnconsumomedidomes >= "
							+ consumoInicial
							+ " and medHistEsgoto.mdhi_nnconsumomedidomes <= "
							+ consumoFinal;
				}
			} else {
				retorno = retorno
						+ " and ( ( medHistAgua.mdhi_nnconsumomedidomes >= "
						+ consumoInicial
						+ " and medHistAgua.mdhi_nnconsumomedidomes <= "
						+ consumoFinal + " ) "
						+ " or ( medHistEsgoto.mdhi_nnconsumomedidomes >= "
						+ consumoInicial
						+ " and medHistEsgoto.mdhi_nnconsumomedidomes <= "
						+ consumoFinal + " ) ) ";
			}

		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getConsumoMedioInicial() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper
						.getConsumoMedioInicial().trim().equals("")) {

			String consumoInicial = filtrarAnaliseExcecoesLeiturasHelper
					.getConsumoMedioInicial();
			String consumoFinal = filtrarAnaliseExcecoesLeiturasHelper
					.getConsumoMedioFinal();

			if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo() != null
					&& !filtrarAnaliseExcecoesLeiturasHelper
							.getIdLigacaoTipo()
							.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo()
						.trim().equals("" + LigacaoTipo.LIGACAO_AGUA)) {
					retorno = retorno
							+ " and consHistAgua.cshi_nnconsumomedio >= "
							+ consumoInicial
							+ " and consHistAgua.cshi_nnconsumomedio <= "
							+ consumoFinal;
				} else {
					retorno = retorno
							+ " and consHistEsgoto.cshi_nnconsumomedio >= "
							+ consumoInicial
							+ " and consHistEsgoto.cshi_nnconsumomedio <= "
							+ consumoFinal;
				}
			} else {
				retorno = retorno
						+ " and ( ( consHistAgua.cshi_nnconsumomedio >= "
						+ consumoInicial
						+ " and consHistAgua.cshi_nnconsumomedio <= "
						+ consumoFinal + " ) "
						+ " or ( consHistEsgoto.cshi_nnconsumomedio >= "
						+ consumoInicial
						+ " and consHistEsgoto.cshi_nnconsumomedio <= "
						+ consumoFinal + " ) ) ";
			}

		}

		if (filtrarAnaliseExcecoesLeiturasHelper.getIdLeituraSituacaoAtual() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper
						.getIdLeituraSituacaoAtual().trim().equals("")) {

			String idLeituraSituacao = filtrarAnaliseExcecoesLeiturasHelper
					.getIdLeituraSituacaoAtual();

			if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null
					&& !filtrarAnaliseExcecoesLeiturasHelper
							.getIdMedicaoTipo()
							.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo()
						.trim().equals("" + MedicaoTipo.LIGACAO_AGUA)) {
					retorno = retorno
							+ " and medHistAgua.ltst_idleiturasituacaoatual = "
							+ idLeituraSituacao;
				} else {
					retorno = retorno
							+ " and medHistEsgoto.ltst_idleiturasituacaoatual = "
							+ idLeituraSituacao;
				}
			} else {
				retorno = retorno
						+ " and (medHistAgua.ltst_idleiturasituacaoatual = "
						+ idLeituraSituacao
						+ " or medHistEsgoto.ltst_idleiturasituacaoatual >= "
						+ idLeituraSituacao + ") ";
			}

		}

		return retorno;
	}

	/**
	 * [UC0082] - Registrar Leituras e Anormalidades
	 * 
	 * Obter o leiturista do imóvel
	 * 
	 * @author Rafael Corrêa
	 * @date 01/07/2008
	 * 
	 * @param idImovel
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarLeituristaImovel(Integer idImovel)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT leiturista.id "// 0
					+ "FROM Imovel imov " + "INNER JOIN imov.quadra quadra "
					+ "INNER JOIN quadra.rota rota "
					+ "INNER JOIN rota.leiturista leiturista "
					+ "WHERE imov.id = :idImovel ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0800] - Obter Consumo Não Medido
	 * 
	 * Obter o consumo mínimo associado à faixa de área do imóvel e a categoria
	 * ou subcategoria informada
	 * 
	 * @author Raphael Rossiter
	 * @date 22/05/2008
	 * 
	 * @param areaConstruida
	 * @param anoMesReferencia
	 * @param idSubcategoria
	 * @param idCategoria
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarConsumoMinimoArea(BigDecimal areaConstruida,
			Integer anoMesReferencia, Integer idSubcategoria,
			Integer idCategoria) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT cmar.numeroConsumo "// 0
					+ "FROM ConsumoMinimoArea cmar "
					+ "WHERE cmar.numeroAreaFinal >= :areaConstruida AND cmar.anoMesReferencia <= :anoMesReferencia ";

			if (idSubcategoria != null) {
				consulta = consulta
						+ "AND cmar.subCategoria.id = :idCategoriaOUSubcategoria ";
			} else {
				consulta = consulta
						+ "AND cmar.categoria.id = :idCategoriaOUSubcategoria ";
			}

			consulta = consulta
					+ "ORDER BY cmar.anoMesReferencia DESC, cmar.numeroAreaFinal ";

			retorno = (Integer) session.createQuery(consulta).setBigDecimal(
					"areaConstruida", areaConstruida).setInteger(
					"anoMesReferencia", anoMesReferencia).setInteger(
					"idCategoriaOUSubcategoria",
					idSubcategoria != null ? idSubcategoria : idCategoria)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * Atualiza o FaturamentoAtividadeCronograma do grupo no anoMes especificado
	 * para o Registrar, Consistir e Efetuar Leitura.
	 * 
	 * [UC0712] Atualizar Leituras e Anormalidades do Celular
	 * 
	 * @author Thiago Nascimento
	 * @date 23/05/2008
	 * 
	 * @param grupo
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public void atualizarFaturamentoAtividadeCronogramaRegistrarConsistirEfetuarLeitura(
			Integer grupo, Integer anoMesReferencia,Date dataRealizacao)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			
			StringBuffer hql = 
				new StringBuffer("select count(m.id) FROM MovimentoRoteiroEmpresa m where m.faturamentoGrupo.id =");
			
			hql.append(grupo);
			hql.append(" and m.dataHoraProcessamento is null and m.anoMesMovimento = ");
			hql.append(anoMesReferencia);
			
			Integer qtdDados = 
				(Integer) session.createQuery(hql.toString()).iterate().next();

			if (qtdDados == 0) {

				// Para Atividade de Registrar Leitura
				hql = new StringBuffer(
						"FROM FaturamentoAtividadeCronograma f where f.faturamentoAtividade.id = ");
				hql.append(FaturamentoAtividade.REGISTRAR_LEITURA_ANORMALIDADE);
				hql
						.append(" and f.faturamentoGrupoCronogramaMensal.anoMesReferencia =");
				hql.append(anoMesReferencia);
				hql
						.append(" and f.faturamentoGrupoCronogramaMensal.faturamentoGrupo.id =");
				hql.append(grupo);
				
				FaturamentoAtividadeCronograma faturamentoAtivCronograma = (FaturamentoAtividadeCronograma) session
						.createQuery(hql.toString()).uniqueResult();
				if (faturamentoAtivCronograma != null) {
					faturamentoAtivCronograma.setDataRealizacao(dataRealizacao);
					faturamentoAtivCronograma.setUltimaAlteracao(new Date());
					session.update(faturamentoAtivCronograma);
				}
	
				// Para atividade de Efetuar Leitura
				hql = new StringBuffer(
						"FROM FaturamentoAtividadeCronograma f where f.faturamentoAtividade.id = ");
				hql.append(FaturamentoAtividade.EFETUAR_LEITURA);
				hql
						.append(" and f.faturamentoGrupoCronogramaMensal.anoMesReferencia =");
				hql.append(anoMesReferencia);
				hql
						.append(" and f.faturamentoGrupoCronogramaMensal.faturamentoGrupo.id =");
				hql.append(grupo);
				
				faturamentoAtivCronograma = (FaturamentoAtividadeCronograma) session
						.createQuery(hql.toString()).uniqueResult();
				if (faturamentoAtivCronograma != null) {
					// Fazer alteração
	
	
					faturamentoAtivCronograma.setDataRealizacao(dataRealizacao);
					faturamentoAtivCronograma.setUltimaAlteracao(new Date());
					session.update(faturamentoAtivCronograma);
				}
	
				// Para atividade de Consitir Leitura
				hql = new StringBuffer(
						"FROM FaturamentoAtividadeCronograma f where f.faturamentoAtividade.id = ");
				hql
						.append(FaturamentoAtividade.CONSISTIR_LEITURAS_E_CALCULAR_CONSUMOS);
				hql
						.append(" and f.faturamentoGrupoCronogramaMensal.anoMesReferencia =");
				hql.append(anoMesReferencia);
				hql
						.append(" and f.faturamentoGrupoCronogramaMensal.faturamentoGrupo.id =");
				hql.append(grupo);
				
				faturamentoAtivCronograma = (FaturamentoAtividadeCronograma) session
						.createQuery(hql.toString()).uniqueResult();
				if (faturamentoAtivCronograma != null) {
					faturamentoAtivCronograma.setDataRealizacao(dataRealizacao);
					faturamentoAtivCronograma.setUltimaAlteracao(new Date());
					session.update(faturamentoAtivCronograma);
				}
				
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * Retorno a quantidade leituras que ainda não foram registradas
	 * 
	 * @data 03/06/2008
	 * @param anoMes
	 * @param grupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer consultarQuantidadeLeiturasNaoResgistradas(Integer anoMes,
			Integer grupo) throws ErroRepositorioException {
		Integer retorno = 0;
		Session session = HibernateUtil.getSession();
		try {
			StringBuffer hql = new StringBuffer(
					"select count(*) as total from MovimentoRoteiroEmpresa m where ");
			hql.append("m.anoMesMovimento = ");
			hql.append(anoMes);
			hql.append(" and m.faturamentoGrupo.id = ");
			hql.append(grupo);
			hql
					.append(" and (m.indicadorAtualizacaoLeitura is null or m.indicadorAtualizacaoLeitura = 2)");
			hql.append(" and m.tempoLeitura is not null");

			retorno = (Integer) session.createQuery(hql.toString())
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Retorna as leituras que ainda não foram registradas
	 * 
	 * @data 03/06/2008
	 * @param anoMes
	 * @param grupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List consultarLeiturasNaoResgistradas(Integer anoMes, Integer grupo)
			throws ErroRepositorioException {
		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			StringBuffer hql = new StringBuffer(
					"FROM MovimentoRoteiroEmpresa m  where ");
			hql.append("m.anoMesMovimento = ");
			hql.append(anoMes);
			hql.append(" and m.faturamentoGrupo.id = ");
			hql.append(grupo);
			hql
					.append(" and (m.indicadorAtualizacaoLeitura is null or m.indicadorAtualizacaoLeitura = 2)");
			hql.append(" and m.tempoLeitura is not null");
			retorno = session.createQuery(hql.toString()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * @author Rômulo Aurélio, Raphael Rossiter
	 * @date 17/06/2008, 27/08/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisPorRotaCompesa(Rota rota, String empresa)
			throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select imovel.id,"
					+ // 0
					"localidade.id,"
					+ // 1
					"setorComercial.codigo,"
					+ // 2
					"quadra.numeroQuadra,"
					+ // 3
					"imovel.lote,"
					+ // 4
					"imovel.subLote,"
					+ // 5
					"imovelPerfil.id,"
					+ // 6
					"ligacaoAgua.id,"
					+ // 7
					"hidInstHistoricoAgua.id,"
					+ // 8
					"hidInstHistoricoPoco.id,"
					+ // 9
					"rota.id,"
					+ // 10
					"rota.indicadorFiscalizarSuprimido,"
					+ // 11
					"rota.indicadorFiscalizarCortado,"
					+ // 12
					"rota.indicadorGerarFiscalizacao,"
					+ // 13
					"rota.indicadorGerarFalsaFaixa,"
					+ // 14
					"rota.percentualGeracaoFiscalizacao,"
					+ // 15
					"rota.percentualGeracaoFaixaFalsa,"
					+ // 16
					"empresa.id,"
					+ // 17
					"empresa.descricaoAbreviada,"
					+ // 18
					"empresa.email,"
					+ // 19
					"faturamentoGrupo.id,"
					+ // 20
					"faturamentoGrupo.descricao,"
					+ // 21
					"leituraTipo.id,"
					+ // 22
					"ligacaoAguaSituacao.id,"
					+ // 23
					"ligacaoEsgotoSituacao.id, "
					+ // 24
					"faturamentoSituacaoTipo.indicadorParalisacaoLeitura, "// 25
					+ "medTipoAgua.id, "// 26
					+ "medTipoPoco.id, "// 27
					+ "empresa.descricao, " // 28
					+ "roteiroEmpresa, "// 29
					+ "hidInstHistoricoAgua,"// 30
					+ "hidInstHistoricoPoco, "// 31
					+ "empresaRota.id, " // 32
					+ "ligacaoAguaSituacao.indicadorFaturamentoSituacao, " // 33
					+ "ligacaoEsgotoSituacao.indicadorFaturamentoSituacao, " // 34
					+ "logradouroBairro.id, " // 35
					+ "logradouro.id, " // 36
					+ "logradouro.nome, " // 37
					+ "bairro.nome, " // 38
					+ "imovel.numeroImovel, " // 39
					+ "imovel.numeroSequencialRota, "// 40
					+ "ligacaoAgua.numeroLacre, "// 41
					+ "imovel.complementoEndereco, "// 42
					+ "gerenciaRegional.id, "// 43
					+ "rota.codigo, "// 44
					+ "quadraFace.id, "// 45
					+ "quadraFace.numeroQuadraFace, "// 46
					+ "ligacaoAguaSituacao.descricao, "// 47
					+ "imovel.numeroMorador, "// 48
					+ "localidade.descricao, "// 49
					+ "leiturista.id "// 50
					+ "from Imovel imovel "
					+ "left join imovel.quadraFace quadraFace "
					+ "left join imovel.localidade localidade "
					+ "left join imovel.setorComercial setorComercial "
					+ "left join imovel.quadra quadra "
					+ "left join imovel.imovelPerfil imovelPerfil "
					+ "left join imovel.ligacaoAgua ligacaoAgua "
					+ "left join imovel.hidrometroInstalacaoHistorico hidInstHistoricoPoco "
					+ "left join imovel.logradouroBairro logradouroBairro "
					+ "left join ligacaoAgua.hidrometroInstalacaoHistorico hidInstHistoricoAgua "
					+ "left join fetch hidInstHistoricoAgua.hidrometroProtecao "
					+ "left join fetch hidInstHistoricoAgua.hidrometroLocalInstalacao "
					+ "left join fetch hidInstHistoricoPoco.hidrometroProtecao "
					+ "left join fetch hidInstHistoricoPoco.hidrometroLocalInstalacao "
					+ "left join hidInstHistoricoAgua.medicaoTipo medTipoAgua "
					+ "left join hidInstHistoricoPoco.medicaoTipo medTipoPoco "
					+ "left join quadra.rota rota "
					+ "left join rota.leiturista leiturista "
					+ "left join rota.empresa empresaRota "
					+ "left join quadra.roteiroEmpresa roteiroEmpresa "
					+ "left join rota.empresa empresa "
					+ "left join rota.faturamentoGrupo faturamentoGrupo "
					+ "left join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "
					+ "left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "
					+ "left join imovel.faturamentoSituacaoTipo faturamentoSituacaoTipo "
					+ "left join rota.leituraTipo leituraTipo "
					+ "left join localidade.gerenciaRegional gerenciaRegional "
					+ "left join logradouroBairro.logradouro logradouro "
					+ "left join logradouroBairro.bairro bairro "
					+ "left join localidade.gerenciaRegional gerenciaRegional "
					+ "where rota.id = :idRota AND imovel.rotaAlternativa IS NULL "
					+ "AND imovelPerfil.indicadorGerarDadosLeitura = 1 ";

			/*
			 * Alteração para ordenar igual ao rol da CAERN Thiago Nascimento
			 * 16/04/2008
			 */
			if (empresa.toUpperCase().equals("COMPESA")) {
				consulta = consulta
						+ "order by empresa.id,localidade.id,setorComercial.codigo,quadra.numeroQuadra,imovel.lote,imovel.subLote";
			} else { // if (empresa.toUpperCase().equals("CAERN")) {
				consulta = consulta
						+ "order by gerenciaRegional.id, localidade.id,setorComercial.codigo,rota.codigo, imovel.numeroSequencialRota";
			}

			retorno = session.createQuery(consulta).setInteger("idRota",
					rota.getId()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Selecionar os movimentos roteiros empresas.
	 * 
	 * [UC0083] Gerar Dados para Leitura
	 * 
	 * @author Rômulo Aurelio
	 * @date 28/06/2008
	 * 
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarMovimentoRoteiroEmpresaConvencional(
			Integer anoMesFaturamento, Integer idFaturamentoGrupo)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select mrem " + "from MovimentoRoteiroEmpresa mrem "
					+ "left join fetch mrem.roteiroEmpresa roem "
					+ "inner join fetch mrem.empresa empr "
					+ "left join roem.leiturista leitu "
					+ "left join leitu.funcionario fun"
					+ "left join fetch mrem.localidade loca "
					+ "left join fetch mrem.medicaoTipo medt "
					+ "left join fetch mrem.imovel imov "
					+ "left join fetch imov.imovelPerfil iper "
					+ "inner join fetch mrem.hidrometroMarca himc "
					+ "inner join fetch mrem.hidrometroCapacidade hicp "
					+ "inner join fetch mrem.hidrometroLocalInstalacao hili "
					+ "inner join fetch mrem.hidrometroProtecao hipr "
					+ "left join fetch mrem.ligacaoAguaSituacao last "
					+ "left join fetch mrem.ligacaoEsgotoSituacao lest "
					+ "left join fetch mrem.faturamentoGrupo ftgr "
					+ "left join fetch mrem.leituraAnormalidade lean "
					+ "inner join fetch mrem.rota rota "
					+ "where mrem.anoMesMovimento = :anoMesFaturamento and "
					+ "ftgr.id = :idFaturamentoGrupo " + "";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesFaturamento", anoMesFaturamento).setInteger(
					"idFaturamentoGrupo", idFaturamentoGrupo).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Selecionar os movimentos roteiros empresas.
	 * 
	 * [UC0083] Gerar Dados para Leitura
	 * 
	 * @author Rômulo Aurelio
	 * @date 28/06/2008
	 * 
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarMovimentoRoteiroEmpresaRolCompesa(
			Integer anoMesFaturamento, Integer idFaturamentoGrupo,
			int inicioPesquisa) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select mrem, "
					+ "mrem.empresa.descricaoAbreviada, "
					+ "mrem.rota.indicadorGerarFiscalizacao, "
					+ "mrem.faturamentoGrupo.descricao, "
					+ "mrem.hidrometroMarca.codigoHidrometroMarca "
					+ "from MovimentoRoteiroEmpresa mrem "
					+ "where mrem.anoMesMovimento = :anoMesFaturamento and "
					+ "mrem.faturamentoGrupo.id = :idFaturamentoGrupo "
					+ "order by mrem.empresa.id, mrem.localidade.id, mrem.codigoSetorComercial, mrem.numeroQuadra, "
					+ "mrem.numeroLoteImovel, mrem.numeroSubloteImovel";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesFaturamento", anoMesFaturamento).setInteger(
					"idFaturamentoGrupo", idFaturamentoGrupo).setFirstResult(
					inicioPesquisa).setMaxResults(1000).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0006] Gerar Relação(ROL) em TXT - CAEMA, JUAZEIRO
	 * 
	 * @author Rômulo Aurelio
	 * @date 02/07/2008
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisParaLeituraPorColecaoRotaCAEMA(Rota rota,
			int inicioPesquisa, Integer anoMes) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select mrem, " // 0
					+ "quadra.id " // 1
					+ "from MovimentoRoteiroEmpresa mrem "
					+ "inner join fetch mrem.rota rota "
					+ "inner join fetch rota.faturamentoGrupo ftgr "
					+ "inner join fetch mrem.localidade loca "
					+ "inner join fetch mrem.logradouro logr "
					+ "inner join mrem.imovel imov "
					+ "inner join imov.quadra quadra "
					+ "left join fetch rota.leiturista leiturista "
					+ "left join fetch mrem.hidrometroMarca hidrometroMarca "
					+ "left join fetch logr.logradouroTipo logradouroTipo "
					+ "left join fetch logr.logradouroTitulo logradouroTitulo "
					+ "where mrem.rota.id = :idRota and mrem.anoMesMovimento = :anoMes "
					+ "order by mrem.localidade.id, mrem.codigoSetorComercial, mrem.rota.codigo, mrem.numeroSequencialRota, "
					+ "mrem.numeroSubloteImovel";

			retorno = session.createQuery(consulta).setInteger("idRota",
					rota.getId()).setInteger("anoMes", anoMes).setFirstResult(
					inicioPesquisa).setMaxResults(1000).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0083]- Gerar Dados para Leitura Pesquisar Faixa de leitura Falsa
	 * Author: Rômulo Aurélio Date: 08/07/2008
	 */

	public LeituraFaixaFalsa pesquisarDadosLeituraFaixaFalsa(
			Integer idMedicaoTipo, Imovel imovel, Integer anoMes)
			throws ErroRepositorioException {
		LeituraFaixaFalsa retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select lff " + "from LeituraFaixaFalsa lff "
					+ "inner join MedicaoHistorico medicaoHistorico ";
			if (idMedicaoTipo != null
					&& idMedicaoTipo.equals(MedicaoTipo.LIGACAO_AGUA)) {
				consulta = consulta
						+ "where medicaoHistorico.ligacaoAgua.id = :idImovel ";
			} else {
				consulta = consulta
						+ "where medicaoHistorico.imovel.id = :idImovel ";
			}
			consulta = consulta
					+ "AND medicaoHistorico.medicaoTipo.id = :idMedicaoTipo "
					+ "AND medicaoHistorico.anoMesReferencia = :amReferencia";
			retorno = (LeituraFaixaFalsa) session.createQuery(consulta)
					.setInteger("idMedicaoTipo", idMedicaoTipo.intValue())
					.setInteger("idImovel", imovel.getId()).setInteger(
							"amReferencia", anoMes).setMaxResults(1)
					.uniqueResult();
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * Obter dados Leitura Anteriores
	 * 
	 * @author Tiago Moreno
	 * @data 03/06/2008
	 * 
	 * @param idImovel
	 * @param anomes
	 * @return
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarLeiturasImovelAnoMes(String idImovel, String anoMes)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select mh.leituraAtualInformada, "
					+ " from MedicaoHistorico mh"
					+ " inner join mh.hidrometroInstalacaoHistorico hid "
					+ " inner join hid.hidrometro hi"
					+ " where mh.ligacaoAgua.id = :id"
					+ " and mh.anoMesReferencia = :anoMes";

			retorno = (Integer) session.createQuery(consulta).setInteger("id",
					new Integer(idImovel)).setInteger("anoMes",
					new Integer(anoMes)).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0002] Gerar Relação(ROL) em TXT
	 * 
	 * @author Rômulo Aurélio
	 * @date 08/07/2008
	 * 
	 */

	public Collection pesquisarMovimentoRoteiroEmpresaParaLeituraPorColecaoRotaCAERN(
			Collection colecaoRotas, Integer idLeituraTipo, int numeroIndice,
			Integer anoMesCorrente) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select mrem, " // 0
					+ "quadra.id, "// 1
					+ "faturamentoGrupo.descricao, "// 2
					+ "empresa.descricaoAbreviada, "// 3
					+ "empresa.email, "// 4
					+ "localidade.descricao, "// 5
					+ "gerenciaRegional.id, "// 6
					+ "gerenciaRegional.nome, "// 7
					+ "hidrometroMarca.codigoHidrometroMarca "// 8
					+ "from MovimentoRoteiroEmpresa mrem "
					+ "left join mrem.faturamentoGrupo faturamentoGrupo "
					+ "left join mrem.localidade localidade "
					+ "left join mrem.imovel imov "
					+ "left join imov.quadra quadra "
					+ "left join mrem.empresa empresa "
					+ "left join fetch mrem.rota rota "
					+ "left join fetch rota.leiturista "
					+ "left join rota.leituraTipo leituraTipo "
					+ "left join localidade.gerenciaRegional gerenciaRegional "
					+ "left join mrem.hidrometroMarca hidrometroMarca "
					+ "where mrem.rota.id in (:idsRotas) and "
					+ "leituraTipo.id = :idLeituraTipo and mrem.anoMesMovimento = :anoMes "
					+ "order by gerenciaRegional.id, mrem.localidade.id, mrem.codigoSetorComercial,"
					+ "mrem.codigoRota,mrem.numeroSequencialRota ";

			retorno = session.createQuery(consulta).setParameterList(
					"idsRotas", colecaoRotas).setInteger("idLeituraTipo",
					idLeituraTipo).setInteger("anoMes", anoMesCorrente)
					.setFirstResult(numeroIndice).setMaxResults(500).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0005] Gerar Relação(ROL) em TXT - CAER
	 * 
	 * @author Sávio Luiz
	 * @date 13/08/2007
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarMovimentoRoteiroEmpresaParaLeituraPorColecaoRotaCAER(
			Rota rota, Integer idLeituraTipo, Integer anoMesCorrente)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String consulta = "select mrem, " // 0
					+ "quadra.id, " // 1
					+ "faturamentoGrupo.descricao, "// 2
					+ "empresa.descricaoAbreviada, "// 3
					+ "empresa.email, "// 4
					+ "localidade.descricao, "// 5
					+ "ligacaoAguaSituacao.descricaoAbreviado, "// 6
					+ "ligacaoEsgotoSituacao.descricaoAbreviado "// 7
					+ "from MovimentoRoteiroEmpresa mrem "
					+ "left join mrem.localidade localidade "
					+ "left join mrem.rota rota "
					+ "left join mrem.imovel imov "
					+ "left join imov.quadra quadra "
					+ "left join localidade.gerenciaRegional gerenciaRegional "
					+ "left join mrem.faturamentoGrupo faturamentoGrupo "
					+ "left join mrem.empresa empresa "
					+ "left join fetch rota.leiturista "
					+ "left join mrem.ligacaoAguaSituacao ligacaoAguaSituacao "
					+ "left join mrem.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "
					// + "left join mrem.rota rota "
					+ "where mrem.rota.id = :idRota and mrem.anoMesMovimento = :anoMes "
					+ "order by mrem.numeroSequencialRota ";

			retorno = session.createQuery(consulta).setInteger("idRota",
					rota.getId()).setInteger("anoMes", anoMesCorrente).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * @author Rômulo Aurélio
	 * @date 17/06/2008
	 * 
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarImoveisPorRotaCaema(Rota rota, String empresa)
			throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select imovel.id,"
					+ // 0
					"localidade.id,"
					+ // 1
					"setorComercial.codigo,"
					+ // 2
					"quadra.numeroQuadra,"
					+ // 3
					"imovel.lote,"
					+ // 4
					"imovel.subLote,"
					+ // 5
					"imovelPerfil.id,"
					+ // 6
					"ligacaoAgua.id,"
					+ // 7
					"hidInstHistoricoAgua.id,"
					+ // 8
					"hidInstHistoricoPoco.id,"
					+ // 9
					"rota.id,"
					+ // 10
					"rota.indicadorFiscalizarSuprimido,"
					+ // 11
					"rota.indicadorFiscalizarCortado,"
					+ // 12
					"rota.indicadorGerarFiscalizacao,"
					+ // 13
					"rota.indicadorGerarFalsaFaixa,"
					+ // 14
					"rota.percentualGeracaoFiscalizacao,"
					+ // 15
					"rota.percentualGeracaoFaixaFalsa,"
					+ // 16
					"empresa.id,"
					+ // 17
					"empresa.descricaoAbreviada,"
					+ // 18
					"empresa.email,"
					+ // 19
					"faturamentoGrupo.id,"
					+ // 20
					"faturamentoGrupo.descricao,"
					+ // 21
					"leituraTipo.id,"
					+ // 22
					"ligacaoAguaSituacao.id,"
					+ // 23
					"ligacaoEsgotoSituacao.id, "
					+ // 24
					"faturamentoSituacaoTipo.indicadorParalisacaoLeitura, "// 25
					+ "medTipoAgua.id, "// 26
					+ "medTipoPoco.id, "// 27
					+ "empresa.descricao, " // 28
					+ "roteiroEmpresa, "// 29
					+ "hidInstHistoricoAgua,"// 30
					+ "hidInstHistoricoPoco, "// 31
					+ "empresaRota.id, " // 32
					+ "ligacaoAguaSituacao.indicadorFaturamentoSituacao, " // 33
					+ "ligacaoEsgotoSituacao.indicadorFaturamentoSituacao, " // 34
					+ "logradouroBairro.id, " // 35
					+ "logradouro.id, " // 36
					+ "logradouro.nome, " // 37
					+ "bairro.nome, " // 38
					+ "imovel.numeroImovel, " // 39
					+ "imovel.numeroSequencialRota, "// 40
					+ "ligacaoAgua.numeroLacre, "// 41
					+ "imovel.complementoEndereco, "// 42
					+ "gerenciaRegional.id, "// 43
					+ "rota.codigo "// 44
					+ "from Imovel imovel "
					+ "left join imovel.localidade localidade "
					+ "left join imovel.setorComercial setorComercial "
					+ "left join imovel.quadra quadra "
					+ "left join imovel.imovelPerfil imovelPerfil "
					+ "left join imovel.ligacaoAgua ligacaoAgua "
					+ "left join imovel.hidrometroInstalacaoHistorico hidInstHistoricoPoco "
					+ "left join imovel.logradouroBairro logradouroBairro "
					+ "left join ligacaoAgua.hidrometroInstalacaoHistorico hidInstHistoricoAgua "
					+ "left join fetch hidInstHistoricoAgua.hidrometroProtecao "
					+ "left join fetch hidInstHistoricoAgua.hidrometroLocalInstalacao "
					+ "left join fetch hidInstHistoricoPoco.hidrometroProtecao "
					+ "left join fetch hidInstHistoricoPoco.hidrometroLocalInstalacao "
					+ "left join hidInstHistoricoAgua.medicaoTipo medTipoAgua "
					+ "left join hidInstHistoricoPoco.medicaoTipo medTipoPoco "
					+ "left join quadra.rota rota "
					+ "left join rota.empresa empresaRota "
					+ "left join quadra.roteiroEmpresa roteiroEmpresa "
					+ "left join rota.empresa empresa "
					+ "left join rota.faturamentoGrupo faturamentoGrupo "
					+ "left join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "
					+ "left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "
					+ "left join imovel.faturamentoSituacaoTipo faturamentoSituacaoTipo "
					+ "left join rota.leituraTipo leituraTipo "
					+ "left join localidade.gerenciaRegional gerenciaRegional "
					+ "left join logradouroBairro.logradouro logradouro "
					+ "left join logradouroBairro.bairro bairro "
					+ "left join localidade.gerenciaRegional gerenciaRegional "
					+ "where rota.id = :idRota ";

			retorno = session.createQuery(consulta).setInteger("idRota",
					rota.getId()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0002] Gerar Relação(ROL) em TXT
	 * 
	 * @author Rômulo Aurélio
	 * @date 08/07/2008
	 * 
	 */

	public void removerMovimentoRoteiroEmpresa(Integer anoMesCorrente,
			Integer idGrupoFaturamentoRota, Rota rota)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();

			String delete = " delete from micromedicao.movimento_roteiro_empr "
					+ " where mrem_ammovimento = "
					+ anoMesCorrente
					+ " and ftgr_id = "
					+ idGrupoFaturamentoRota
					+ " and rota_id = " + rota.getId();

			stmt.executeUpdate(delete);

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0002] Gerar Relação(ROL) em TXT
	 * 
	 * @author Rômulo Aurélio
	 * @date 08/07/2008
	 * 
	 */

	public boolean removerArquivoTextoRoteiroEmpresa(Integer anoMesCorrente,
			Integer idGrupoFaturamentoRota, Integer idRota)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Connection con = null;
		Statement stmt = null;
		int qtdRegistrosDeletados = 0;

		try {

			con = session.connection();
			stmt = con.createStatement();

			String delete = " delete from micromedicao.arquivo_texto_rot_empr "
					+ " where txre_amreferencia = "
					+ anoMesCorrente
					+ " and ftgr_id = "
					+ idGrupoFaturamentoRota
					+ " and rota_id = "
					+ idRota
					+ " and sitl_id = "
					+ SituacaoTransmissaoLeitura.LIBERADO;

			qtdRegistrosDeletados = stmt.executeUpdate(delete);

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return qtdRegistrosDeletados > 0;

	}

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0002] Gerar Relação(ROL) em TXT
	 * 
	 * @author Rômulo Aurélio
	 * @date 08/07/2008
	 * 
	 */

	public Collection pesquisarImoveisPorRotaCAERN(Rota rota, String empresa)
			throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select imovel.id,"
					+ // 0
					"localidade.id,"
					+ // 1
					"setorComercial.codigo,"
					+ // 2
					"quadra.numeroQuadra,"
					+ // 3
					"imovel.lote,"
					+ // 4
					"imovel.subLote,"
					+ // 5
					"imovelPerfil.id,"
					+ // 6
					"ligacaoAgua.id,"
					+ // 7
					"hidInstHistoricoAgua.id,"
					+ // 8
					"hidInstHistoricoPoco.id,"
					+ // 9
					"rota.id,"
					+ // 10
					"rota.indicadorFiscalizarSuprimido,"
					+ // 11
					"rota.indicadorFiscalizarCortado,"
					+ // 12
					"rota.indicadorGerarFiscalizacao,"
					+ // 13
					"rota.indicadorGerarFalsaFaixa,"
					+ // 14
					"rota.percentualGeracaoFiscalizacao,"
					+ // 15
					"rota.percentualGeracaoFaixaFalsa,"
					+ // 16
					"empresa.id,"
					+ // 17
					"empresa.descricaoAbreviada,"
					+ // 18
					"empresa.email,"
					+ // 19
					"faturamentoGrupo.id,"
					+ // 20
					"faturamentoGrupo.descricao,"
					+ // 21
					"leituraTipo.id,"
					+ // 22
					"ligacaoAguaSituacao.id,"
					+ // 23
					"ligacaoEsgotoSituacao.id, "
					+ // 24
					"faturamentoSituacaoTipo.indicadorParalisacaoLeitura, "// 25
					+ "medTipoAgua.id, "// 26
					+ "medTipoPoco.id, "// 27
					+ "empresa.descricao, " // 28
					+ "roteiroEmpresa, "// 29
					+ "hidInstHistoricoAgua,"// 30
					+ "hidInstHistoricoPoco, "// 31
					+ "empresaRota.id, " // 32
					+ "ligacaoAguaSituacao.indicadorFaturamentoSituacao, " // 33
					+ "ligacaoEsgotoSituacao.indicadorFaturamentoSituacao, " // 34
					+ "logradouroBairro.id, " // 35
					+ "logradouro.id, " // 36
					+ "logradouro.nome, " // 37
					+ "bairro.nome, " // 38
					+ "imovel.numeroImovel, " // 39
					+ "imovel.numeroSequencialRota, "// 40
					+ "ligacaoAgua.numeroLacre, "// 41
					+ "imovel.complementoEndereco, "// 42
					+ "gerenciaRegional.id, "// 43
					+ "rota.codigo "// 44
					+ "from Imovel imovel "
					+ "left join imovel.localidade localidade "
					+ "left join imovel.setorComercial setorComercial "
					+ "left join imovel.quadra quadra "
					+ "left join imovel.imovelPerfil imovelPerfil "
					+ "left join imovel.ligacaoAgua ligacaoAgua "
					+ "left join imovel.hidrometroInstalacaoHistorico hidInstHistoricoPoco "
					+ "left join imovel.logradouroBairro logradouroBairro "
					+ "left join ligacaoAgua.hidrometroInstalacaoHistorico hidInstHistoricoAgua "
					+ "left join fetch hidInstHistoricoAgua.hidrometroProtecao "
					+ "left join fetch hidInstHistoricoAgua.hidrometroLocalInstalacao "
					+ "left join fetch hidInstHistoricoPoco.hidrometroProtecao "
					+ "left join fetch hidInstHistoricoPoco.hidrometroLocalInstalacao "
					+ "left join hidInstHistoricoAgua.medicaoTipo medTipoAgua "
					+ "left join hidInstHistoricoPoco.medicaoTipo medTipoPoco "
					+ "left join quadra.rota rota "
					+ "left join rota.empresa empresaRota "
					+ "left join quadra.roteiroEmpresa roteiroEmpresa "
					+ "left join rota.empresa empresa "
					+ "left join rota.faturamentoGrupo faturamentoGrupo "
					+ "left join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "
					+ "left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "
					+ "left join imovel.faturamentoSituacaoTipo faturamentoSituacaoTipo "
					+ "left join rota.leituraTipo leituraTipo "
					+ "left join localidade.gerenciaRegional gerenciaRegional "
					+ "left join logradouroBairro.logradouro logradouro "
					+ "left join logradouroBairro.bairro bairro "
					+ "left join localidade.gerenciaRegional gerenciaRegional "
					+ "where rota.id = :idRota and hidInstHistoricoAgua.dataRetirada is null and hidInstHistoricoPoco.dataRetirada is null "
					+ "order by gerenciaRegional.id, localidade.id,setorComercial.codigo,rota.codigo, imovel.numeroSequencialRota";

			retorno = session.createQuery(consulta).setInteger("idRota",
					rota.getId()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0805] - Gerar Aviso de Anormalidade
	 * 
	 * Pesquisa a quantidade de registros do relatório
	 * 
	 * @author Yara T.Souza - Hugo Leonardo
	 * @date 12/08/2008 - 18/03/2010
	 * 
	 */
	public Integer pesquisarLeiturasEnviadasRelatorioCount(
			Integer anoMesReferencia, Integer idEmpresa, Integer idLocalidade,
			Integer codigoSetorComercial, Integer idGrupoFaturamento,
			Integer idGerencia, Integer idUnidadeNegocio, Integer idLeiturista)
			throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta;

		try {
			consulta = "SELECT COUNT(movRotEmp.id) "
					+ " FROM MovimentoRoteiroEmpresa movRotEmp "
					+ " INNER JOIN movRotEmp.imovel imov"
					+ " INNER JOIN imov.localidade loca"
					+ " INNER JOIN imov.setorComercial stcm"
					+ " INNER JOIN imov.quadra qd" + " INNER JOIN qd.rota rt"
					+ " WHERE movRotEmp.anoMesMovimento = :anoMesReferencia ";

			if (idEmpresa != null) {
				consulta = consulta + " and rt.empresa.id = " + idEmpresa;

				if (idLeiturista != null) {
					// filtra por Leiturista
					consulta = consulta
							+ " and rt.leiturista.id = :idLeiturista";
				}
			}

			if (idGrupoFaturamento != null) {
				consulta = consulta + " and rt.faturamentoGrupo.id = "
						+ idGrupoFaturamento;
			}

			if (idGerencia != null) {
				consulta = consulta + "	and loca.gerenciaRegional.id = "
						+ idGerencia;
			}

			if (idUnidadeNegocio != null) {
				consulta = consulta + "	and loca.unidadeNegocio = "
						+ idUnidadeNegocio;
			}

			consulta = consulta + " and imov.localidade.id = :idLocalidade "
					+ " and stcm.codigo = :codigoSetorComercial ";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).setInteger(
					"codigoSetorComercial", codigoSetorComercial).setInteger(
					"idLocalidade", idLocalidade).setInteger("idLeiturista",
					idLeiturista).setMaxResults(1).uniqueResult();

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).setInteger(
					"codigoSetorComercial", codigoSetorComercial).setInteger(
					"idLocalidade", idLocalidade).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 * 
	 * [SB0021] - Dados para Faturamento Especial Medido
	 * 
	 * @author Raphael Rossiter
	 * @date 12/08/2008
	 * 
	 * @param imovel
	 * @param faturamentoGrupo
	 * @return FaturamentoSituacaoHistorico
	 * @throws ErroRepositorioException
	 */
	public FaturamentoSituacaoHistorico pesquisarFaturamentoSituacaoHistoricoConsumoVolumeFixo(
			Imovel imovel, FaturamentoGrupo faturamentoGrupo) throws ErroRepositorioException {

		FaturamentoSituacaoHistorico retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select new FaturamentoSituacaoHistorico(ftsh.id, ftsh.numeroConsumoAguaMedido, "
					+ "ftsh.numeroConsumoAguaNaoMedido, ftsh.numeroVolumeEsgotoMedido, "
					+ "ftsh.numeroVolumeEsgotoNaoMedido) "
					+ "from FaturamentoSituacaoHistorico ftsh "
					+ "inner join ftsh.imovel imov "
					+ "where imov.id = :idImovel and ftsh.anoMesFaturamentoRetirada IS NULL and "
					+ "(ftsh.anoMesFaturamentoSituacaoInicio <= :anosMesReferenciaFaturamentoGrupo and "
					+ " ftsh.anoMesFaturamentoSituacaoFim >= :anosMesReferenciaFaturamentoGrupo)";

			retorno = (FaturamentoSituacaoHistorico) session.createQuery(consulta)
					.setInteger("idImovel", imovel.getId().intValue())
					.setInteger("anosMesReferenciaFaturamentoGrupo", faturamentoGrupo.getAnoMesReferencia().intValue())
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular
	 * 
	 * @author Vinicius Medeiros
	 * @date 20/09/2008
	 * 
	 * @return ImovelSubcategoria
	 * @throws ErroRepositorioException
	 */

	public Object[] obterDadosHidrometroAtualizacaoCadastral(Integer idImovel)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Object[] retornoConsulta = null;

		try {

			consulta = " select hidi_nnleitinstalacaohidmt as leitInstHidrometro, "
					+ // 0
					" hicp_id as capHidrometro, "
					+ // 1
					" himc_id as marcaHidrometro, "
					+ // 2
					" hili_id as locInstHidrometro, "
					+ // 3
					" hipr_id as protHidrometro, "
					+ // 4
					" hidi_iccavalete as indCavalete, "
					+ // 5
					" hidr_nnhidrometro as numeroHidrometro"
					+ // 6

					" from micromedicao.hidrometro_inst_hist hidrInstHistorico "
					+

					" inner join micromedicao.hidrometro hidrometro on hidrometro.hidr_id = hidrInstHistorico.hidr_id "
					+ " inner join atendimentopublico.ligacao_agua lagu on lagu.hidi_id = hidrInstHistorico.hidi_id "
					+

					" where hidrInstHistorico.lagu_id = :idImovel "
					+ " and hidrInstHistorico.hidi_dtretiradahidrometro is null ";

			retornoConsulta = (Object[]) session.createSQLQuery(consulta)
					.addScalar("leitInstHidrometro", Hibernate.INTEGER)
					.addScalar("capHidrometro", Hibernate.INTEGER).addScalar(
							"marcaHidrometro", Hibernate.INTEGER).addScalar(
							"locInstHidrometro", Hibernate.INTEGER).addScalar(
							"protHidrometro", Hibernate.INTEGER).addScalar(
							"indCavalete", Hibernate.SHORT).addScalar(
							"numeroHidrometro", Hibernate.STRING).setInteger(
							"idImovel", idImovel).uniqueResult();

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retornoConsulta;

	}

	/**
	 * [UC0629] Retornar Arquivo Txt Leitura
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 06/10/2008
	 * 
	 */

	public Collection pesquisarArquivosTextoRoteiroEmpresaParaArquivoZip(
			String[] ids) throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select atre "
					+ "from ArquivoTextoRoteiroEmpresa atre "
					+ "where atre.id in ( :ids) ";

			retorno = session.createQuery(consulta)
					.setParameterList("ids", ids).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00083] Gerar Dados para Leitura [FS0006]-Verificar imóveis processados
	 * na competência
	 * 
	 * @date 13/10/2008
	 * @author Rômulo Aurélio
	 * 
	 */

	public Collection pesquisarImoveisMovimentoRoteiroEmpresaParaExistenteGeradoParaOutroGrupo(
			Collection colecaoImoveisParaGerar, Integer idFaturamentoGrupo,
			Integer anoMes) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Iterator colecaoImoveisParaGerarIterator = colecaoImoveisParaGerar
				.iterator();

		Collection ids = new ArrayList();

		while (colecaoImoveisParaGerarIterator.hasNext()) {

			Imovel imovel = (Imovel) colecaoImoveisParaGerarIterator.next();

			ids.add(imovel.getId());

		}

		try {
			String consulta = "select distinct mrem.imovel "
					+ "from MovimentoRoteiroEmpresa mrem "
					+ "where mrem.imovel.id in (:ids) and mrem.faturamentoGrupo.id <> :idFaturamentoGrupo and "
					+ "mrem.anoMesMovimento = :anoMes ";

			retorno = session.createQuery(consulta)
					.setParameterList("ids", ids).setInteger(
							"idFaturamentoGrupo", idFaturamentoGrupo)
					.setInteger("anoMes", anoMes).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0???] Gerar Relatorio Rotas Online por Empresa
	 * 
	 * @author Victor Cisneiros
	 * @date 28/10/2008
	 */
	public Collection<RelatorioRotasOnlinePorEmpresaBean> pesquisarRelatorioRotasOnlinePorEmpresa(
			PesquisarRelatorioRotasOnlinePorEmpresaHelper helper)
			throws ErroRepositorioException {

		Collection<RelatorioRotasOnlinePorEmpresaBean> retorno = new ArrayList<RelatorioRotasOnlinePorEmpresaBean>();

		Session session = HibernateUtil.getSession();

		String where = "";
		if (helper.getIdFaturamentoGrupo() != null) {
			where += "AND r.ftgr_id = :idFaturamentoGrupo ";
		}
		if (helper.getIdEmpresa() != null) {
			where += "AND r.empr_id = :idEmpresa ";
		}
		if (helper.getIdLeiturista() != null) {
			where += "AND r.leit_id = :idLeiturista ";
		}

		String where2 = "";
		if (helper.getAnoMesReferencia() != null) {
			where2 += "AND m.mrem_ammovimento = :anoMesReferencia ";
		}

		try {
			String consulta = "SELECT "
					+ "l.loca_id as idLocalidade, "
					+ "l.loca_nmlocalidade as nomeLocalidade, "
					+ "s.stcm_cdsetorcomercial as codigoSetorComercial, "
					+ "r.rota_id as idRota, "
					+ "r.rota_cdrota as codigoRota, "
					+ "r.ftgr_id as idFaturamentoGrupo, "
					+ "leit.leit_id as idLeiturista, "
					+ "c.clie_nmcliente as nomeLeiturista, "
					+ "( SELECT min(q.qdra_nnquadra) FROM cadastro.quadra q WHERE q.rota_id = r.rota_id ) quadraMinima, "
					+ "( SELECT max(q.qdra_nnquadra) FROM cadastro.quadra q WHERE q.rota_id = r.rota_id ) quadraMaxima, "
					+ "( SELECT count(*) count FROM cadastro.quadra q WHERE q.rota_id = r.rota_id ) quantidadeQuadras, "
					+ "( SELECT count(*) count FROM micromedicao.movimento_roteiro_empr m WHERE m.rota_id = r.rota_id  "
					+ where2
					+ " ) as quantidadeLeituras "
					+ "FROM micromedicao.rota r "
					+ "INNER JOIN cadastro.setor_comercial s ON (s.stcm_id = r.stcm_id) "
					+ "INNER JOIN cadastro.localidade l ON (s.loca_id = l.loca_id) "
					+ "INNER JOIN micromedicao.leiturista leit ON (r.leit_id = leit.leit_id) "
					+ "INNER JOIN cadastro.cliente c ON (leit.clie_id = c.clie_id) "
//					+ "WHERE " + "r.lttp_id = 4 " + where + "ORDER BY "
					+ "WHERE " + "r.lttp_id in ( 4, 3 ) " + where + "ORDER BY "
					+ "r.stcm_id, r.rota_id ASC";

			SQLQuery q = session.createSQLQuery(consulta);

			q.addScalar("idLocalidade", Hibernate.INTEGER);
			q.addScalar("nomeLocalidade", Hibernate.STRING);
			q.addScalar("codigoSetorComercial", Hibernate.INTEGER);
			q.addScalar("idRota", Hibernate.INTEGER);
			q.addScalar("codigoRota", Hibernate.INTEGER);
			q.addScalar("idFaturamentoGrupo", Hibernate.INTEGER);
			q.addScalar("idLeiturista", Hibernate.INTEGER);
			q.addScalar("nomeLeiturista", Hibernate.STRING);
			q.addScalar("quadraMinima", Hibernate.INTEGER);
			q.addScalar("quadraMaxima", Hibernate.INTEGER);
			q.addScalar("quantidadeQuadras", Hibernate.INTEGER);
			q.addScalar("quantidadeLeituras", Hibernate.INTEGER);

			if (helper.getIdFaturamentoGrupo() != null) {
				q.setInteger("idFaturamentoGrupo", helper
						.getIdFaturamentoGrupo());
			}
			if (helper.getIdEmpresa() != null) {
				q.setInteger("idEmpresa", helper.getIdEmpresa());
			}
			if (helper.getIdLeiturista() != null) {
				q.setInteger("idLeiturista", helper.getIdLeiturista());
			}
			if (helper.getAnoMesReferencia() != null) {
				q.setInteger("anoMesReferencia", helper.getAnoMesReferencia());
			}

			for (Object[] linha : (List<Object[]>) q.list()) {
				int i = 0;

				RelatorioRotasOnlinePorEmpresaBean bean = new RelatorioRotasOnlinePorEmpresaBean();
				bean.setIdLocalidade((Integer) linha[i++]);
				bean.setNomeLocalidade((String) linha[i++]);
				bean.setCodigoSetorComercial((Integer) linha[i++]);
				bean.setIdRota((Integer) linha[i++]);
				bean.setCodigoRota((Integer) linha[i++]);
				bean.setIdFaturamentoGrupo((Integer) linha[i++]);
				bean.setIdLeiturista((Integer) linha[i++]);
				bean.setNomeLeiturista((String) linha[i++]);
				bean.setNumeroQuadraMinimo((Integer) linha[i++]);
				bean.setNumeroQuadraMaximo((Integer) linha[i++]);
				bean.setQuantidadeQuadras((Integer) linha[i++]);
				bean.setQuantidadeLeituras((Integer) linha[i++]);

				retorno.add(bean);
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0???] Gerar Relatorio Rotas Online por Empresa
	 * 
	 * @author Victor Cisneiros
	 * @date 28/10/2008
	 */
	public Integer pesquisarRelatorioRotasOnlinePorEmpresaCount(
			PesquisarRelatorioRotasOnlinePorEmpresaHelper helper)
			throws ErroRepositorioException {

		Integer retorno = 0;
		Session session = HibernateUtil.getSession();

		String where = "";
		if (helper.getIdFaturamentoGrupo() != null) {
			where += "AND r.ftgr_id = :idFaturamentoGrupo ";
		}
		if (helper.getIdEmpresa() != null) {
			where += "AND r.empr_id = :idEmpresa ";
		}
		if (helper.getIdLeiturista() != null) {
			where += "AND r.leit_id = :idLeiturista ";
		}

		try {
			String consulta = "SELECT count(*) as count FROM micromedicao.rota r WHERE r.lttp_id = 4 "
					+ where;

			SQLQuery q = session.createSQLQuery(consulta);

			q.addScalar("count", Hibernate.INTEGER);

			if (helper.getIdFaturamentoGrupo() != null) {
				q.setInteger("idFaturamentoGrupo", helper
						.getIdFaturamentoGrupo());
			}
			if (helper.getIdEmpresa() != null) {
				q.setInteger("idEmpresa", helper.getIdEmpresa());
			}
			if (helper.getIdLeiturista() != null) {
				q.setInteger("idLeiturista", helper.getIdLeiturista());
			}

			retorno = (Integer) q.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisar quantidade de imóveis por arquivo texto leitura.
	 * 
	 * @author Yara T. Souza
	 * @date 18/12/2008
	 * 
	 */
	public Object[] pesquisarQuantidadeImoveisPorArquivo(Integer id)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Object[] retorno = null;

		try {

			String sql = "SELECT rota_id as idRota, "
					+ "txre_qtimovel as qtdImoveis, "
					+ "fg.ftgr_amreferencia as amReferencia, "
					+ "sitl_id as situacao "
					+ "FROM micromedicao.arquivo_texto_rot_empr txre "
					+ "INNER JOIN faturamento.faturamento_grupo fg on fg.ftgr_id = txre.ftgr_id "
					+ "WHERE txre_id =" + id;

			Collection colecaoConsulta = session.createSQLQuery(sql)
					.addScalar("idRota", Hibernate.INTEGER)
					.addScalar("qtdImoveis",Hibernate.INTEGER)
					.addScalar("amReferencia",Hibernate.INTEGER)
					.addScalar("situacao",Hibernate.INTEGER)
					.list();

			retorno = (Object[]) Util.retonarObjetoDeColecao(colecaoConsulta);

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	public Collection pesquisarDadosRelatorioAnaliseImovelCorporativoGrande(
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorComercialInicial, Integer idSetorComercialFinal,
			Integer referencia, Integer idImovelPerfil, Integer selecionar)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno = null;
		String consulta = null;

		try {

			consulta = " SELECT greg.greg_id as idGerencia,"
					+ " unidNeg.uneg_id as idUnidadeNegocio,"
					+ " loc.loca_id as idLocalidade,"
					+ " setor.stcm_cdsetorcomercial as codigoSetorComercial,"
					+ " imov.imov_id as idImovel,"
					+ " hicp_dshidrometrocapacidade as capacidadeHidrometro,"
					+ " cshi_nnconsumomedio as consumoMedio, cshi_nnconsumofaturadomes as consumoFaturado,"
					+ " lgti_id as idTipoLigacao"
					+ " FROM micromedicao.consumo_historico ch"
					+ " LEFT JOIN cadastro.imovel imov on(ch.imov_id = imov.imov_id)"
					+ " INNER JOIN cadastro.setor_comercial setor on (setor.stcm_id = imov.stcm_id)"
					+ " INNER JOIN cadastro.localidade loc on (loc.loca_id = imov.loca_id)"
					+ " INNER JOIN cadastro.unidade_negocio unidNeg on (unidNeg.uneg_id = loc.uneg_id)"
					+ " INNER JOIN cadastro.gerencia_regional greg on (greg.greg_id = unidNeg.greg_id)"
					+ " LEFT JOIN atendimentopublico.ligacao_agua la on (ch.imov_id = la.lagu_id)"
					+ " INNER JOIN micromedicao.hidrometro_inst_hist hih on (la.hidi_id = hih.hidi_id and hih.hidi_dtretiradahidrometro is null and hih.medt_id = ch.lgti_id "
					+ " or imov.hidi_id = hih.hidi_id and hih.hidi_dtretiradahidrometro is null and hih.medt_id = ch.lgti_id)"
					+ " INNER JOIN micromedicao.hidrometro h on (hih.hidr_id = h.hidr_id)"
					+ " INNER JOIN micromedicao.hidrometro_capacidade hicp on(h.hicp_id = hicp.hicp_id)";

			consulta = consulta
					+ criarCondicionaisRelatorioAnaliseImovelCorporativoGrande(
							idGerenciaRegional, idUnidadeNegocio,
							idLocalidadeInicial, idLocalidadeFinal,
							idSetorComercialInicial, idSetorComercialFinal,
							referencia, idImovelPerfil, selecionar);

			consulta = consulta
					+ " GROUP BY greg.greg_id, unidNeg.uneg_id, loc.loca_id, setor.stcm_cdsetorcomercial,"
					+ " imov.imov_id, hicp.hicp_id, hicp_dshidrometrocapacidade, cshi_nnconsumomedio, "
					+ " cshi_nnconsumofaturadomes, lgti_id"
					+ " ORDER BY greg.greg_id, unidNeg.uneg_id, loc.loca_id,"
					+ " setor.stcm_cdsetorcomercial, imov.imov_id, ch.lgti_id ";

			SQLQuery query = session.createSQLQuery(consulta);

			retorno = query.addScalar("idGerencia", Hibernate.INTEGER)

			.addScalar("idUnidadeNegocio", Hibernate.INTEGER)

			.addScalar("idLocalidade", Hibernate.INTEGER)

			.addScalar("codigoSetorComercial", Hibernate.INTEGER).addScalar(
					"idImovel", Hibernate.INTEGER).addScalar(
					"capacidadeHidrometro", Hibernate.STRING).addScalar(
					"consumoMedio", Hibernate.INTEGER).addScalar(
					"consumoFaturado", Hibernate.INTEGER).addScalar(
					"idTipoLigacao", Hibernate.INTEGER).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public String criarCondicionaisRelatorioAnaliseImovelCorporativoGrande(
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorComercialInicial, Integer idSetorComercialFinal,
			Integer referencia, Integer idImovelPerfil, Integer selecionar) {

		String sql = " WHERE ch.cshi_amfaturamento = " + referencia + " and ";

		if (selecionar == null) {
			sql = sql + " imov.iper_id = " + idImovelPerfil.toString()
					+ " and ";
		} else if (selecionar.equals(1)) {
			if (idImovelPerfil.equals(1)) {
				sql = sql
						+ " iper_id not in (1,7) "
						+ " and (cshi_nnconsumomedio >= 200 or h.hicp_id not in (1,2,3,8,9))"
						+ " and lgti_id in(1,2) and cshi_icimovelcondominio = 2 and not exists (select ims.imov_id from cadastro.imovel_subcategoria ims"
						+ " 																	inner join cadastro.subcategoria su on (ims.scat_id = su.scat_id)"
						+ " 																	inner join micromedicao.consumo_historico ch on (imov.imov_id = ch.imov_id)"
						+ " 																	where ch.imov_id = ims.imov_id and catg_id in (1,2,3) "
						+ "																    and ch.cshi_amfaturamento = "
						+ referencia + " and cshi_nnconsumomedio >= 1000) and ";

			} else {
				sql = sql
						+ " (cshi_nnconsumomedio >= 1000 and h.hicp_id not in (1,2,3,8,9))"
						+ " and lgti_id in(1,2) and iper_id NOT IN (3,8) and cshi_icimovelcondominio = 2"
						+ " and exists (select ims.imov_id from cadastro.imovel_subcategoria ims"
						+ " inner join cadastro.subcategoria su on (ims.scat_id = su.scat_id)"
						+ " where ch.imov_id = ims.imov_id and catg_id <> 4) and ";

			}
		} else {
			sql = sql + " imov.iper_id = " + idImovelPerfil.toString()
					+ " and  ";
			if (idImovelPerfil.equals(1)) {
				sql = sql
						+ " (((cshi_nnconsumomedio < 200) or h.hicp_id in (1,2,3,8,9))"
						+ " or cshi_icimovelcondominio <> 2) and ";

			} else {
				sql = sql
						+ " ((cshi_nnconsumomedio < 1000 or h.hicp_id in (1,2,3,8,9))"
						+ " or  cshi_icimovelcondominio <> 2"
						+ " or exists (select ims.imov_id from cadastro.imovel_subcategoria ims"
						+ " inner join cadastro.subcategoria su on (ims.scat_id = su.scat_id)"
						+ " where ch.imov_id = ims.imov_id and catg_id = 4)) and ";
			}
		}

		if (idGerenciaRegional != null) {
			sql = sql + " greg.greg_id = " + idGerenciaRegional.toString()
					+ " and ";
		}

		if (idLocalidadeInicial != null) {
			sql = sql + " loc.loca_id between "
					+ idLocalidadeInicial.toString() + " and "
					+ idLocalidadeFinal.toString() + " and ";
		}

		if (idSetorComercialInicial != null) {
			sql = sql + " setor.stcm_cdsetorcomercial between "
					+ idSetorComercialInicial.toString() + " and "
					+ idSetorComercialFinal.toString() + " and ";
		}

		if (idUnidadeNegocio != null) {
			sql = sql + " unidNeg.uneg_id = " + idUnidadeNegocio.toString()
					+ " and ";
		}

		sql = Util.removerUltimosCaracteres(sql, 4);

		return sql;

	}

	/**
	 * 
	 * [UC0889] - Alterar datas das leituras
	 * 
	 * Pesquisamos todos os dados necessários para a alteração das datas
	 * 
	 * @author bruno, Mariana Victor
	 * @date 26/02/2009, 24/02/2011
	 * 
	 * @param idGrupoFaturamento
	 * @return
	 */
	public Collection<Object[]> pesquisarDadosAlterarGruposFaturamento(
			Integer idGrupoFaturamento) throws ErroRepositorioException {

		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select \n"
					+ "      mh.dataLeituraAnteriorFaturamento,\n"
					+ "      mh.dataLeituraAtualFaturamento,\n"
					+ "      count(*) as count \n"
					+ "from\n"
					+ "  MedicaoHistorico mh\n"
					+ "  inner join mh.ligacaoAgua la\n"
					+ "  inner join la.imovel imo\n"
					+ "  inner join imo.quadra qua\n"
					+ "  inner join qua.rota rot\n"
					+ "  inner join rot.faturamentoGrupo fatGru\n"
					+ "where\n"
					+ "  mh.anoMesReferencia = fatGru.anoMesReferencia and fatGru.id = :idGrupo\n"
					+ " GROUP BY  mh.dataLeituraAnteriorFaturamento,  mh.dataLeituraAtualFaturamento \n" + "      \n" + "order by\n" + "  1, 2\n";

			retorno = session.createQuery(consulta).setInteger("idGrupo",
					idGrupoFaturamento).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * 
	 * [UC0889] - Alterar datas das leituras
	 * 
	 * Alteramos todos as datas informadas
	 * 
	 * @author bruno, Mariana Victor
	 * @date 26/02/2009, 04/03/2011
	 * 
	 * @param idGrupoFaturamento
	 * @return
	 */
	public void alterarDatasLeituras(AlterarDatasLeiturasHelper helper,
			Integer idGrupo) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String update;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();

			update = "update "
					+ "  micromedicao.medicao_historico as mh "
					+ "set "
					+ "  mdhi_dtleitantfatmt=to_date('"
					+ Util.formatarDataComTracoAAAAMMDD(Util
							.converteStringParaDate(helper
									.getDtLeituraAnteriorNova()))
					+ "','YYYY-MM-DD'), "
					+ "  mdhi_dtleituraatualfaturamento=to_date('"
					+ Util.formatarDataComTracoAAAAMMDD(Util
							.converteStringParaDate(helper
									.getDtLeituraAtualNova()))
					+ "','YYYY-MM-DD'), "
					+ "  mdhi_dtleituraatualinformada=to_date('"
					+ Util.formatarDataComTracoAAAAMMDD(Util
							.converteStringParaDate(helper
									.getDtLeituraAtualNova()))
					+ "','YYYY-MM-DD'), "
					+ "  mdhi_tmultimaalteracao=  " + Util.obterSQLDataAtual()
					+ "where "
					+ "  mh.mdhi_id in ( "
					+ "    select "
					+ "      mh2.mdhi_id "
					+ "    from "
					+ "      micromedicao.medicao_historico mh2 "
					+ "      inner join cadastro.imovel imo on ( mh2.lagu_id = imo.imov_id ) "
					+ "      inner join cadastro.quadra qua on ( imo.qdra_id = qua.qdra_id ) "
					+ "      inner join micromedicao.rota rot on ( qua.rota_id = rot.rota_id ) "
					+ "      inner join faturamento.faturamento_grupo fatGru on (rot.ftgr_id = fatGru.ftgr_id)"
					+ "    where "
					+ "      mh2.mdhi_amleitura = fatGru.ftgr_amreferencia and "
					+ "      fatGru.ftgr_id = "
					+ idGrupo
					+ " ) and "
					+ "  mh.mdhi_dtleitantfatmt=to_date('"
					+ Util.formatarDataComTracoAAAAMMDD(Util
							.converteStringParaDate(helper
									.getDtLeituraAnterior()))
					+ "','YYYY-MM-DD') and "
					+ "  mh.mdhi_dtleituraatualfaturamento=to_date('"
					+ Util
							.formatarDataComTracoAAAAMMDD(Util
									.converteStringParaDate(helper
											.getDtLeituraAtual())) + "','YYYY-MM-DD')";

			stmt.executeUpdate(update);
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e,
						"Erro ao fechar conexï¿½es");
			}
		}
	}

	/**
	 * 
	 * 
	 * @author Rômulo Aurélio Data: 28/04/2009
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Integer pesquisarNumeroHidrometroSituacaoInstaladoPaginacaoCount(
			FiltrarHidrometroHelper helper) throws ErroRepositorioException {

		// Collection<Hidrometro> retorno = new ArrayList();
		// Collection<Hidrometro> hidrometros = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta;

		Integer retorno = null;

		try {
			consulta = "select count(distinct hidrometro.id) "
					+ "from gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico "
					+ "inner join hidrometroInstalacaoHistorico.ligacaoAgua ligacaoAgua "
					+ "inner join ligacaoAgua.imovel imovel "
					+ "inner join hidrometroInstalacaoHistorico.hidrometro hidrometro "
					+ "left join  hidrometro.hidrometroMarca "
					+ "left join  hidrometro.hidrometroCapacidade "
					+ "left join  hidrometro.hidrometroSituacao "
					+ "left join  hidrometro.hidrometroLocalArmazenagem "
					+ "left join  hidrometro.hidrometroRelojoaria " + "where  ";

			consulta = criarCondicionaisPesquisarNumeroHidrometroSituacaoInstaladoPaginacao(
					consulta, helper);

			// consulta = consulta + " order by 1 ";
			// consulta = consulta + " group by 1 ";

			Object resultado = session.createQuery(consulta).uniqueResult();

			if (resultado != null) {
				retorno = (Integer) resultado;
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		// return retorno;
		return retorno;
	}

	/**
	 * 
	 * @author Rômulo Aurélio Data: 28/04/2009
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarNumeroHidrometroSituacaoInstaladoPaginacao(
			FiltrarHidrometroHelper helper, Integer numeroPagina)
			throws ErroRepositorioException {

		// Collection<Hidrometro> retorno = new ArrayList();
		Collection<Hidrometro> hidrometros = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select hidrometro "
					+ "from gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico "
					+ "inner join hidrometroInstalacaoHistorico.ligacaoAgua ligacaoAgua "
					+ "inner join ligacaoAgua.imovel imovel "
					+ "inner join hidrometroInstalacaoHistorico.hidrometro hidrometro "
					+ "left join fetch hidrometro.hidrometroMarca "
					+ "left join fetch hidrometro.hidrometroCapacidade "
					+ "left join fetch hidrometro.hidrometroSituacao "
					+ "left join fetch hidrometro.hidrometroLocalArmazenagem "
					+ "left join fetch hidrometro.hidrometroRelojoaria "
					+ "where  ";

			consulta = criarCondicionaisPesquisarNumeroHidrometroSituacaoInstaladoPaginacao(
					consulta, helper);

			consulta = consulta + " order by hidrometro.numero";

			hidrometros = session.createQuery(consulta).setFirstResult(
					10 * numeroPagina).setMaxResults(10).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		// return retorno;
		return hidrometros;
	}

	/**
	 * 
	 * @author Rômulo Aurélio Data: 28/04/2009
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarNumeroHidrometroSituacaoInstaladoRelatorio(
			FiltrarHidrometroHelper helper) throws ErroRepositorioException {

		// Collection<Hidrometro> retorno = new ArrayList();
		Collection<Hidrometro> hidrometros = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select hidrometro "
					+ "from gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico "
					+ "inner join hidrometroInstalacaoHistorico.ligacaoAgua ligacaoAgua "
					+ "inner join ligacaoAgua.imovel imovel "
					+ "inner join hidrometroInstalacaoHistorico.hidrometro hidrometro "
					+ "inner join fetch hidrometro.hidrometroMarca "
					+ "inner join fetch hidrometro.hidrometroCapacidade "
					+ "inner join fetch hidrometro.hidrometroSituacao "
					+ "inner join fetch hidrometro.hidrometroLocalArmazenagem "
					+ "left join fetch hidrometro.hidrometroRelojoaria "
					+ "inner join fetch hidrometro.hidrometroClasseMetrologica "
					+ "inner join fetch hidrometro.hidrometroDiametro "
					+ "inner join fetch hidrometro.hidrometroTipo " + "where  ";

			consulta = criarCondicionaisPesquisarNumeroHidrometroSituacaoInstaladoPaginacao(
					consulta, helper);

			consulta = consulta + " order by hidrometro.numero";

			hidrometros = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		// return retorno;
		return hidrometros;
	}

	public String criarCondicionaisPesquisarNumeroHidrometroSituacaoInstaladoPaginacao(
			String consulta, FiltrarHidrometroHelper helper) {

		boolean peloMenosUmParametroInformado = false;

		if (helper.getNumeroHidrometro() != null
				&& !helper.getNumeroHidrometro().equals("")) {

			peloMenosUmParametroInformado = true;

			consulta = consulta + " hidrometro.numero = "
					+ helper.getNumeroHidrometro();

		}
		
		if (helper.getAnoFabricacao() != null
				&& !helper.getAnoFabricacao().equals("")) {

			peloMenosUmParametroInformado = true;

			consulta = consulta + " hidrometro.anoFabricacao = "
					+ helper.getAnoFabricacao();

		}

		if (helper.getDataAquisicao() != null
				&& !helper.getDataAquisicao().equals("")
				&& !peloMenosUmParametroInformado) {

			consulta = consulta
					+ " hidrometro.dataAquisicao = to_date('"
					+ Util.formatarDataComTracoAAAAMMDD(helper
							.getDataAquisicao()) + "','YYYY-MM-DD')";

			peloMenosUmParametroInformado = true;

		} else if (helper.getDataAquisicao() != null
				&& !helper.getDataAquisicao().equals("")
				&& peloMenosUmParametroInformado) {

			consulta = consulta
					+ " and hidrometro.dataAquisicao = to_date('"
					+ Util.formatarDataComTracoAAAAMMDD(helper
							.getDataAquisicao()) + "','YYYY-MM-DD')";
		}

		if (helper.getFinalidade() != null
				&& !helper.getFinalidade().equals("")
				&& !peloMenosUmParametroInformado) {

			consulta = consulta + " hidrometro.indicadorMacromedidor = "
					+ helper.getFinalidade();

			peloMenosUmParametroInformado = true;

		} else if (helper.getFinalidade() != null
				&& !helper.getFinalidade().equals("")
				&& peloMenosUmParametroInformado) {

			consulta = consulta + " and hidrometro.indicadorMacromedidor = "
					+ helper.getFinalidade();
		}

		if (helper.getIdHidrometroClasseMetrologica() != null
				&& !helper.getIdHidrometroClasseMetrologica().equals("-1")
				&& !peloMenosUmParametroInformado) {

			consulta = consulta
					+ " hidrometro.hidrometroClasseMetrologica.id = "
					+ helper.getIdHidrometroClasseMetrologica();

			peloMenosUmParametroInformado = true;
		} else if (helper.getIdHidrometroClasseMetrologica() != null
				&& !helper.getIdHidrometroClasseMetrologica().equals("-1")
				&& peloMenosUmParametroInformado) {

			consulta = consulta
					+ " and hidrometro.hidrometroClasseMetrologica.id = "
					+ helper.getIdHidrometroClasseMetrologica();
		}

		if (helper.getIdHidrometroDiametro() != null
				&& !helper.getIdHidrometroDiametro().equals("-1")
				&& !peloMenosUmParametroInformado) {

			consulta = consulta + " hidrometro.hidrometroDiametro.id = "
					+ helper.getIdHidrometroDiametro();

			peloMenosUmParametroInformado = true;
		} else if (helper.getIdHidrometroDiametro() != null
				&& !helper.getIdHidrometroDiametro().equals("-1")
				&& peloMenosUmParametroInformado) {

			consulta = consulta + " and hidrometro.hidrometroDiametro.id = "
					+ helper.getIdHidrometroDiametro();
		}

		if (helper.getIdHidrometroTipo() != null
				&& !helper.getIdHidrometroTipo().equals("-1")
				&& !peloMenosUmParametroInformado) {

			consulta = consulta + " hidrometro.hidrometroTipo.id = "
					+ helper.getIdHidrometroTipo();

			peloMenosUmParametroInformado = true;
		} else if (helper.getIdHidrometroTipo() != null
				&& !helper.getIdHidrometroTipo().equals("-1")
				&& peloMenosUmParametroInformado) {

			consulta = consulta + " and hidrometro.hidrometroTipo.id = "
					+ helper.getIdHidrometroTipo();
		}

		if (helper.getIdHidrometroRelojoaria() != null
				&& !helper.getIdHidrometroRelojoaria().equals("-1")
				&& !peloMenosUmParametroInformado) {

			consulta = consulta + " hidrometro.hidrometroRelojoaria.id = "
					+ helper.getIdHidrometroRelojoaria();

			peloMenosUmParametroInformado = true;
		} else if (helper.getIdHidrometroRelojoaria() != null
				&& !helper.getIdHidrometroRelojoaria().equals("-1")
				&& peloMenosUmParametroInformado) {

			consulta = consulta + " and hidrometro.hidrometroRelojoaria.id = "
					+ helper.getIdHidrometroRelojoaria();
		}

		if (helper.getIdLocalArmazenagem() != null
				&& !helper.getIdLocalArmazenagem().equals("")
				&& !peloMenosUmParametroInformado) {

			consulta = consulta
					+ " hidrometro.hidrometroLocalArmazenagem.id = "
					+ helper.getIdLocalArmazenagem();

			peloMenosUmParametroInformado = true;
		} else if (helper.getIdLocalArmazenagem() != null
				&& !helper.getIdLocalArmazenagem().equals("")
				&& peloMenosUmParametroInformado) {

			consulta = consulta
					+ " and hidrometro.hidrometroLocalArmazenagem.id = "
					+ helper.getIdLocalArmazenagem();
		}

		if (helper.getIdHidrometroSituacao() != null
				&& !helper.getIdHidrometroSituacao().equals("-1")
				&& !peloMenosUmParametroInformado) {

			consulta = consulta + " hidrometro.hidrometroSituacao.id = "
					+ helper.getIdHidrometroSituacao();

			peloMenosUmParametroInformado = true;
		} else if (helper.getIdHidrometroSituacao() != null
				&& !helper.getIdHidrometroSituacao().equals("-1")
				&& peloMenosUmParametroInformado) {

			consulta = consulta + " and hidrometro.hidrometroSituacao.id = "
					+ helper.getIdHidrometroSituacao();
		}

		if (helper.getIdLocalidade() != null
				&& !helper.getIdLocalidade().equals("")
				&& !peloMenosUmParametroInformado) {

			consulta = consulta + " imovel.localidade.id = "
					+ helper.getIdHidrometroSituacao();

			peloMenosUmParametroInformado = true;
		} else if (helper.getIdLocalidade() != null
				&& !helper.getIdLocalidade().equals("")
				&& peloMenosUmParametroInformado) {

			consulta = consulta + " and imovel.localidade.id = "
					+ helper.getIdLocalidade();
		}

		if (helper.getIdSetorComercial() != null
				&& !helper.getIdSetorComercial().equals("")
				&& !peloMenosUmParametroInformado) {

			consulta = consulta + " imovel.setorComercial.id = "
					+ helper.getIdSetorComercial();

			peloMenosUmParametroInformado = true;
		} else if (helper.getIdSetorComercial() != null
				&& !helper.getIdSetorComercial().equals("")
				&& peloMenosUmParametroInformado) {

			consulta = consulta + " and imovel.setorComercial.id = "
					+ helper.getIdSetorComercial();
		}

		if (helper.getIdQuadra() != null && !helper.getIdQuadra().equals("")
				&& !peloMenosUmParametroInformado) {

			consulta = consulta + " imovel.quadra.id = " + helper.getIdQuadra();

			peloMenosUmParametroInformado = true;
		} else if (helper.getIdQuadra() != null
				&& !helper.getIdQuadra().equals("")
				&& peloMenosUmParametroInformado) {

			consulta = consulta + " and imovel.quadra.id = "
					+ helper.getIdQuadra();
		}
		
		if (helper.getIdHidrometroCapacidade() != null && !helper.getIdHidrometroCapacidade().equals("")
				&& !peloMenosUmParametroInformado) {

			consulta = consulta + " hidrometro.hidrometroCapacidade.id = " + helper.getIdHidrometroCapacidade();
			
			peloMenosUmParametroInformado = true;
		} else if (helper.getIdHidrometroCapacidade() != null
				&& !helper.getIdHidrometroCapacidade().equals("")
				&& peloMenosUmParametroInformado) {

			consulta = consulta + " and hidrometro.hidrometroCapacidade.id = "
					+ helper.getIdHidrometroCapacidade();
		}
		if (helper.getIdHidrometroMarca() != null && !helper.getIdHidrometroMarca().equals("")
				&& !peloMenosUmParametroInformado){
            
			consulta = consulta + " hidrometro.hidrometroMarca.id = " + helper.getIdHidrometroMarca();
			
			peloMenosUmParametroInformado = true;
		}
		else if(helper.getIdHidrometroMarca() != null && !helper.getIdHidrometroMarca().equals("")
				&& peloMenosUmParametroInformado ){
			
			consulta = consulta + " and hidrometro.hidrometroMarca.id = " + helper.getIdHidrometroMarca();
		}
		
		return consulta;
	}

	/**
	 * 
	 * Método que retorna os consumos de ligacao da agua ou esgoto (tipo
	 * passado) de um imovel em um determinado período do faturamento.
	 * 
	 * @author Rafael Corrêa
	 * @date 27/07/2009
	 * 
	 * @param idImovel
	 * @param anoMesInicial
	 * @param anoMesFinal
	 * @param idLigacaoTipo
	 *            podendo ser agua ou esgoto
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterConsumosAnterioresAnormalidadeDoImovel(
			Integer idImovel, Integer anoMesInicial, Integer anoMesFinal,
			Integer idLigacaoTipo) throws ErroRepositorioException {

		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "		consumoHistorico.numeroConsumoFaturadoMes,consAnormalidade.descricaoAbreviada, consumoHistorico.referenciaFaturamento "
					+ "from "
					+ "		gcom.micromedicao.consumo.ConsumoHistorico consumoHistorico "
					+ "     left join consumoHistorico.consumoAnormalidade consAnormalidade "
					+ "		left join consumoHistorico.ligacaoTipo ligacaoTipo "
					+ "		left join consumoHistorico.imovel imovel"
					+ " "
					+ "where "
					+ " 	imovel.id = "
					+ idImovel
					+ " 	and ligacaoTipo.id = "
					+ idLigacaoTipo
					+ " 	and consumoHistorico.referenciaFaturamento between :anoMesInicial and :anoMesFinal ";

			retorno = (Collection<Object[]>) session.createQuery(consulta)
					.setInteger("anoMesInicial", anoMesInicial).setInteger(
							"anoMesFinal", anoMesFinal).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * 
	 * Método que retorna os tipos de consumos de um determinado consumo
	 * historico
	 * 
	 * @author Tiago Moreno
	 * @date 25/02/2010
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @param idLigacaoTipo
	 *            podendo ser agua ou esgoto
	 * @return consumoTipo - descricaoAbreviada
	 * @throws ErroRepositorioException
	 */
	public String obterConsumoTipoImovel(Integer idImovel, Integer anoMes,
			Integer idLigacaoTipo) throws ErroRepositorioException {

		String retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "		tipo.descricaoAbreviada "
					+ "from "
					+ "		gcom.micromedicao.consumo.ConsumoHistorico consumoHistorico "
					+ "     left join consumoHistorico.consumoAnormalidade consAnormalidade "
					+ "		left join consumoHistorico.ligacaoTipo ligacaoTipo "
					+ "		left join consumoHistorico.imovel imovel "
					+ "     left join consumoHistorico.consumoTipo tipo " + " "
					+ "where " + " 	imovel.id = " + idImovel
					+ " 	and ligacaoTipo.id = " + idLigacaoTipo
					+ " 	and consumoHistorico.referenciaFaturamento = :anoMes";

			retorno = (String) session.createQuery(consulta).setInteger(
					"anoMes", anoMes).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * [UC0898] Atualizar Autos de Infração com prazo de Recurso Vencido
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 11/05/2009
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */

	public Collection pesquisarConsumoFaturadoQuantidadeMesesPorReferencia(
			Integer idImovel, Integer tipoMedicao, short qtdMeses)
			throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select cshi.numeroConsumoFaturadoMes,cshi.referenciaFaturamento "
					+ "from ConsumoHistorico cshi "
					+ "where cshi.imovel.id = :idImovel and cshi.ligacaoTipo.id = :tipoLigacao "
					+ "order by cshi.referenciaFaturamento desc ";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idImovel", idImovel)
					.setInteger("tipoLigacao", tipoMedicao).setMaxResults(
							qtdMeses).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * @author Sávio Luiz
	 * @date 01/07/2009
	 * 
	 * @param imovel
	 * @param anoMesReferencia
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMaiorDataLeituraImoveis(Integer idImovel,
			Integer anoMesReferencia) throws ErroRepositorioException {

		Date retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT mdhi.dataLeituraAtualFaturamento "
					+ "FROM MedicaoHistorico mdhi "
					+ "LEFT JOIN mdhi.ligacaoAgua lagu "
					+ "WHERE mdhi.anoMesReferencia = :anoMesReferencia "
					+ "AND lagu.id = :idImovel ";

			retorno = (Date) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setInteger("anoMesReferencia",
					anoMesReferencia).setMaxResults(1).uniqueResult();

			if (retorno == null || retorno.equals("")) {
				consulta = "SELECT mdhi.dataLeituraAtualFaturamento "
						+ "FROM MedicaoHistorico mdhi "
						+ "LEFT JOIN mdhi.imovel imovel "
						+ "WHERE mdhi.anoMesReferencia = :anoMesReferencia "
						+ "AND imovel.id = :idImovel ";

				retorno = (Date) session.createQuery(consulta).setInteger(
						"idImovel", idImovel).setInteger("anoMesReferencia",
						anoMesReferencia).setMaxResults(1).uniqueResult();
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 * 
	 * @author Raphael Rossiter
	 * @date 25/08/2009
	 * 
	 * @return Collection
	 * @exception ErroRepositorioException
	 */
	public Collection pesquisarImovelConsistirLeituraPorRotaAlternativa(
			Rota rota) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select im.id," // 0
					+ "im.ligacaoAguaSituacao.id, " // 1
					+ "im.ligacaoEsgotoSituacao.id, " // 2
					+ "li.id, " // 3
					+ "hia.id, " // 4
					+ "hdra.id, " // 5
					+ "hdra.numeroDigitosLeitura, " // 6
					+ "fst.id," // 7
					+ "im.pocoTipo.id, " // 8
					+ "hie.id, " // 9
					+ "hdre.id, " // 10
					+ "im.quadra.rota.indicadorAjusteConsumo, " // 11
					+ "im.ligacaoAgua.numeroConsumoMinimoAgua, " // 12
					+ "im.indicadorImovelCondominio, " // 13
					+ "fst.indicadorParalisacaoFaturamento, " // 14
					+ "im.indicadorDebitoConta, " // 15
					+ "le.id, " // Ligacao Esgoto //16
					+ "le.consumoMinimo, " // 17
					+ "hia.dataInstalacao, " // 18
					+ "ct.id, " // 19
					+ "le.percentualAguaConsumidaColetada, " // 20
					+ "im.quantidadeEconomias, " // 21
					+ "hdre.numeroDigitosLeitura, " // 22
					+ "hie.dataInstalacao, "// 23
					+ "fst.indicadorValidoAgua, " // 24
					+ "fst.indicadorValidoEsgoto, " // 25
					+ "esferaPoderClieResp.id, " // 26
					+ "im.ligacaoAguaSituacao.indicadorFaturamentoSituacao, " // 27
					+ "im.ligacaoEsgotoSituacao.indicadorFaturamentoSituacao, "// 28
					+ "im.ligacaoAguaSituacao.consumoMinimoFaturamento, " // 29
					+ "im.ligacaoEsgotoSituacao.volumeMinimoFaturamento, "// 30
					+ "im.ligacaoAguaSituacao.indicadorAbastecimento, " // 31
					+ "hia.numeroLeituraInstalacao, " // 32
					+ "hia.numeroLeituraInstalacao, " // 33
					/**
					 * TODO : COSANPA Adicionando parametros da ligacao de agua
					 */
					+ "li.dataLigacao, " // 34
					+ "le.dataLigacao, " // 35
					+ "li.numeroConsumoMinimoAgua " //36
					+ "from Imovel im "
					+ "inner join im.rotaAlternativa rotaAlternativa "
					+ "inner join im.quadra.rota.faturamentoGrupo "
					+ "inner join im.consumoTarifa ct "
					+ "left join im.ligacaoAgua li "
					+ "left join im.ligacaoAgua.hidrometroInstalacaoHistorico hia "
					+ "left join im.ligacaoAgua.hidrometroInstalacaoHistorico.hidrometro hdra "
					+ "left join im.hidrometroInstalacaoHistorico hie "
					+ "left join im.hidrometroInstalacaoHistorico.hidrometro hdre "
					+ "left join im.faturamentoSituacaoTipo fst "
					+ "left join im.ligacaoEsgoto le "
					+ "left join im.clienteImoveis clieImovResp with (clieImovResp.clienteRelacaoTipo.id = :clienteResponsavel and clieImovResp.dataFimRelacao is null) "
					+ "left join clieImovResp.cliente clieResp "
					+ "left join clieResp.clienteTipo tipoClieResp "
					+ "left join tipoClieResp.esferaPoder esferaPoderClieResp "

					// SELEÇÃO POR ROTA ALTERNATIVA
					+ "WHERE rotaAlternativa.id = :rota AND im.indicadorExclusao <> 1 "

					// VERIFICANDO SE O IMÓVEL ESTÁ DISPONÍVEL PARA GERAÇÃO DE
					// CONSUMO
					+ "AND ((im.ligacaoAguaSituacao.indicadorFaturamentoSituacao = :faturamentoAgua OR "
					+ "im.ligacaoEsgotoSituacao.indicadorFaturamentoSituacao = :faturamentoEsgoto) "

					// VERIFICA SE O IMÓVEL POSSUI HIDROMETRO NA LIGAÇÃO DE AGUA
					// OU DE POÇO
					+ "OR  (li.hidrometroInstalacaoHistorico <> null OR "
					+ "im.hidrometroInstalacaoHistorico <> null)) ";

			retorno = session.createQuery(consulta).setInteger(
					"clienteResponsavel",
					ClienteRelacaoTipo.RESPONSAVEL.intValue()).setInteger(
					"rota", rota.getId()).setShort("faturamentoAgua",
					LigacaoAguaSituacao.FATURAMENTO_ATIVO).setShort(
					"faturamentoEsgoto",
					LigacaoEsgotoSituacao.FATURAMENTO_ATIVO).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Método que retorna os imoveis condominiais e esteja com ligados ou
	 * cortados a agua e ou ligados com esgoto que possuam hidrometro no poço
	 * das rotas passadas
	 * 
	 * [UC0103] Efetuar Rateio de Consumo
	 * 
	 * @author Raphael Rossiter
	 * @date 26/08/2009
	 * 
	 * @param idRota
	 * @return Imoveis
	 */
	public Collection pesquisarImovelCondominioParaCalculoDoRateioPorRotaAlternativa(
			Integer idRota) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select imovel.id, imovel.ligacaoAguaSituacao.id, " // 0,1
					+ "imovel.ligacaoEsgotoSituacao.id, " // 2
					+ "imovel.ligacaoAguaSituacao.indicadorFaturamentoSituacao, "// 3
					+ "imovel.ligacaoEsgotoSituacao.indicadorFaturamentoSituacao "// 4

					+ "from gcom.cadastro.imovel.Imovel imovel "
					+ "inner join imovel.rotaAlternativa as rotaAlternativa "
					+ "inner join rotaAlternativa.faturamentoGrupo as ftgr "

					// VERIFICANDO SE É UM IMÓVEL CONDOMÍNIO
					+ "WHERE imovel.indicadorImovelCondominio = "
					+ ConstantesSistema.SIM

					// VERIFICANDO SE O IMÓVEL ESTÁ DISPONÍVEL PARA EFETUAR
					// RATEIO
					+ " AND (imovel.ligacaoAguaSituacao.indicadorFaturamentoSituacao = :faturamentoAgua OR "
					+ "imovel.ligacaoEsgotoSituacao.indicadorFaturamentoSituacao = :faturamentoEsgoto)"

					+ " AND imovel.hidrometroInstalacaoHistorico IS NOT NULL"

					// VERIFICANDO SE O IMÓVEL PERTENCE A ROTA
					+ " AND rotaAlternativa.id = :idRota"
					+ " AND imovel.indicadorExclusao <> 1 "
					+ " AND NOT EXISTS "
					+ "(select im.id "
					+ " from ConsumoHistorico consumoHistorico "
					+ " inner join consumoHistorico.imovel im "
					+ " where im.id = imovel.id and consumoHistorico.consumoRateio is not null "
					+ " and consumoHistorico.referenciaFaturamento = ftgr.anoMesReferencia)";

			retorno = session.createQuery(consulta).setShort("faturamentoAgua",
					ConstantesSistema.SIM.shortValue()).setShort(
					"faturamentoEsgoto", ConstantesSistema.SIM.shortValue())
					.setInteger("idRota", idRota).list();
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * @author Raphael Rossiter
	 * @date 27/08/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisPorRotaAlternativa(Rota rota,
			String empresa) throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select imovel.id,"
					+ // 0
					"localidade.id,"
					+ // 1
					"setorComercial.codigo,"
					+ // 2
					"quadra.numeroQuadra,"
					+ // 3
					"imovel.lote,"
					+ // 4
					"imovel.subLote,"
					+ // 5
					"imovelPerfil.id,"
					+ // 6
					"ligacaoAgua.id,"
					+ // 7
					"hidInstHistoricoAgua.id,"
					+ // 8
					"hidInstHistoricoPoco.id,"
					+ // 9
					"rotaAlternativa.id,"
					+ // 10
					"rotaAlternativa.indicadorFiscalizarSuprimido,"
					+ // 11
					"rotaAlternativa.indicadorFiscalizarCortado,"
					+ // 12
					"rotaAlternativa.indicadorGerarFiscalizacao,"
					+ // 13
					"rotaAlternativa.indicadorGerarFalsaFaixa,"
					+ // 14
					"rotaAlternativa.percentualGeracaoFiscalizacao,"
					+ // 15
					"rotaAlternativa.percentualGeracaoFaixaFalsa,"
					+ // 16
					"empresa.id,"
					+ // 17
					"empresa.descricaoAbreviada,"
					+ // 18
					"empresa.email,"
					+ // 19
					"faturamentoGrupo.id,"
					+ // 20
					"faturamentoGrupo.descricao,"
					+ // 21
					"leituraTipo.id,"
					+ // 22
					"ligacaoAguaSituacao.id,"
					+ // 23
					"ligacaoEsgotoSituacao.id, "
					+ // 24
					"faturamentoSituacaoTipo.indicadorParalisacaoLeitura, "// 25
					+ "medTipoAgua.id, "// 26
					+ "medTipoPoco.id, "// 27
					+ "empresa.descricao, " // 28
					+ "roteiroEmpresa, "// 29
					+ "hidInstHistoricoAgua,"// 30
					+ "hidInstHistoricoPoco, "// 31
					+ "empresa.id, " // 32
					+ "ligacaoAguaSituacao.indicadorFaturamentoSituacao, " // 33
					+ "ligacaoEsgotoSituacao.indicadorFaturamentoSituacao, " // 34
					+ "logradouroBairro.id, " // 35
					+ "logradouro.id, " // 36
					+ "logradouro.nome, " // 37
					+ "bairro.nome, " // 38
					+ "imovel.numeroImovel, " // 39
					+ "imovel.numeroSequencialRota, "// 40
					+ "ligacaoAgua.numeroLacre, "// 41
					+ "imovel.complementoEndereco, "// 42
					+ "gerenciaRegional.id, "// 43
					+ "rotaAlternativa.codigo, "// 44
					+ "quadraFace.id, "// 45
					+ "quadraFace.numeroQuadraFace, "// 46
					+ "ligacaoAguaSituacao.descricao, "// 47
					+ "imovel.numeroMorador, "// 48
					+ "localidade.descricao, "// 49
					+ "leiturista.id "// 50
					+ "from Imovel imovel "
					+ "inner join imovel.rotaAlternativa rotaAlternativa "
					+ "left join imovel.quadraFace quadraFace "
					+ "left join imovel.localidade localidade "
					+ "left join imovel.setorComercial setorComercial "
					+ "left join imovel.quadra quadra "
					+ "left join imovel.imovelPerfil imovelPerfil "
					+ "left join imovel.ligacaoAgua ligacaoAgua "
					+ "left join imovel.hidrometroInstalacaoHistorico hidInstHistoricoPoco "
					+ "left join imovel.logradouroBairro logradouroBairro "
					+ "left join ligacaoAgua.hidrometroInstalacaoHistorico hidInstHistoricoAgua "
					+ "left join fetch hidInstHistoricoAgua.hidrometroProtecao "
					+ "left join fetch hidInstHistoricoAgua.hidrometroLocalInstalacao "
					+ "left join fetch hidInstHistoricoPoco.hidrometroProtecao "
					+ "left join fetch hidInstHistoricoPoco.hidrometroLocalInstalacao "
					+ "left join hidInstHistoricoAgua.medicaoTipo medTipoAgua "
					+ "left join hidInstHistoricoPoco.medicaoTipo medTipoPoco "
					+ "left join quadra.roteiroEmpresa roteiroEmpresa "
					+ "left join rotaAlternativa.leiturista leiturista "
					+ "left join rotaAlternativa.empresa empresa "
					+ "left join rotaAlternativa.faturamentoGrupo faturamentoGrupo "
					+ "left join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "
					+ "left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "
					+ "left join imovel.faturamentoSituacaoTipo faturamentoSituacaoTipo "
					+ "left join rotaAlternativa.leituraTipo leituraTipo "
					+ "left join localidade.gerenciaRegional gerenciaRegional "
					+ "left join logradouroBairro.logradouro logradouro "
					+ "left join logradouroBairro.bairro bairro "
					+ "left join localidade.gerenciaRegional gerenciaRegional "
					+ "where rotaAlternativa.id = :idRota "
					+ "AND imovelPerfil.indicadorGerarDadosLeitura = 1 ";

			/*
			 * Alteração para ordenar igual ao rol da CAERN Thiago Nascimento
			 * 16/04/2008
			 */
			if (empresa.toUpperCase().equals("COMPESA")) {
				consulta = consulta
						+ "order by empresa.id,localidade.id,setorComercial.codigo,quadra.numeroQuadra,imovel.lote,imovel.subLote";
			} else { // if (empresa.toUpperCase().equals("CAERN")) {
				consulta = consulta
						+ "order by gerenciaRegional.id, localidade.id,setorComercial.codigo,rotaAlternativa.codigo, imovel.numeroSequencialRota";
			}

			retorno = session.createQuery(consulta).setInteger("idRota",
					rota.getId()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0006] - Obter dados dos tipos de medição
	 * 
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 * 
	 * @param imovel
	 * @param anoMesReferencia
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection obterDadosTiposMedicaoHidrometro(Imovel imovel)
			throws ErroRepositorioException {

		Collection retorno = new ArrayList();
		Collection retornoAgua = null;
		Collection retornoPoco = null;

		Session session = HibernateUtil.getSession();
		String consulta;
		String consultaPoco;

		try {
			consulta = "SELECT hidr.numero, hidr.numeroDigitosLeitura, hidi.dataInstalacao, "// 0,1,2
					+ "hidi.numeroLeituraInstalacao, hidi.dataInstalacao, "// 3,4
					+ "lagu.id, 0, "// 5,6
					+ "hli.descricao, "// 7
					+ "rttp.id "// 8
					+ "From HidrometroInstalacaoHistorico hidi "
					+ "LEFT JOIN hidi.hidrometro hidr "
					+ "LEFT JOIN hidi.ligacaoAgua lagu "
					+ "LEFT JOIN hidi.hidrometroLocalInstalacao hli "
					+ "LEFT JOIN hidi.rateioTipo rttp "
					+ "WHERE lagu.id = :idImovel "
					+ "AND hidi.dataRetirada is null "
					/**
					 * TODO : COSANPA
					 * Adicionando relacionamento da tabela ligacao de agua com 
					 * hidrometro instalacao historico, para não retornar mais
					 * de um hidrometro sem data de retirada
					 */
					+ "AND hidi.id = lagu.hidrometroInstalacaoHistorico.id ";

			retornoAgua = session.createQuery(consulta).setInteger("idImovel",
					imovel.getId()).list();

			consultaPoco = "SELECT hidr.numero, hidr.numeroDigitosLeitura, hidi.dataInstalacao, "// 0,1,2
					+ "hidi.numeroLeituraInstalacao, hidi.dataInstalacao, "// 3,4
					+ "0, imovel.id, "// 5,6
					+ "hli.descricao, "// 7
					+ "rttp.id "// 8
					+ "From HidrometroInstalacaoHistorico hidi "
					+ "LEFT JOIN hidi.hidrometro hidr "
					+ "LEFT JOIN hidi.imovel imovel "
					+ "LEFT JOIN hidi.hidrometroLocalInstalacao hli "
					+ "LEFT JOIN hidi.rateioTipo rttp "
					+ "WHERE imovel.id = :idImovel "
					+ "AND hidi.dataRetirada is null ";

			retornoPoco = session.createQuery(consultaPoco).setInteger(
					"idImovel", imovel.getId()).list();

			if (retornoAgua != null && !retornoAgua.isEmpty()) {
				retorno.addAll(retornoAgua);
			}

			if (retornoPoco != null && !retornoPoco.isEmpty()) {
				retorno.addAll(retornoPoco);
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0038] Inserir Rota [UC0039]Manter Rota
	 * 
	 * @author Rafael Pinto
	 * @date 31/08/2009
	 * 
	 * @param idRota
	 * @param idFaturamentoGrupo
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarComandoNaoRealizadoParaRota(Integer idRota,
			Integer idFaturamentoGrupo) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "SELECT distinct ftat.descricao "
					+ "FROM FaturamentoAtivCronRota facr "
					+ "INNER JOIN facr.rota rota "
					+ "INNER JOIN facr.faturamentoAtividadeCronograma ftac "
					+ "INNER JOIN ftac.faturamentoAtividade ftat "
					+ "INNER JOIN ftac.faturamentoGrupoCronogramaMensal ftcm "
					+ "INNER JOIN ftcm.faturamentoGrupo ftgr "
					+ "WHERE rota.id = :idRota "
					+ "AND ftac.dataRealizacao is null "
					+ "AND ftgr.id = :idFaturamentoGrupo "
					+ "AND ftat.id = :idAtividade "
					+ "AND ftcm.anoMesReferencia = ftgr.anoMesReferencia  ";

			retorno = session
					.createQuery(consulta)
					.setInteger("idRota", idRota)
					.setInteger("idFaturamentoGrupo", idFaturamentoGrupo)
					.setInteger(
							"idAtividade",
							FaturamentoAtividade.CONSISTIR_LEITURAS_E_CALCULAR_CONSUMOS)
					.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * [UC0811] Requisições do Dispositivo Móvel da Conta Pré-faturada.
	 * 
	 * SB0001 - Baixar Arquivo Texto para o Leiturista
	 * 
	 * @author Bruno Barros
	 * @date 24/09/2009
	 * 
	 * @param imei
	 * @return
	 * @throws ControladorException
	 */
	public Object[] baixarArquivoTextoParaLeituristaImpressaoSimultanea(long imei,Integer idServicoTipoCelular)
			throws ErroRepositorioException {
		Object[] retorno = new Object[2];
		ArquivoTextoRoteiroEmpresa arquivoTextoRoteiro = null;
		Session session = HibernateUtil.getSession();
		byte[] arquivo = null;
		String nomeArquivo = null;

		String hdl = "FROM ArquivoTextoRoteiroEmpresa a where a.situacaoTransmissaoLeitura.id = 2 and a.servicoTipoCelular.id = :idServicoTipoCelular and "
				+ " a.numeroImei = "
				+ imei
				+ " order by a.numeroSequenciaLeitura";

        try {
            arquivoTextoRoteiro = (ArquivoTextoRoteiroEmpresa) session
                .createQuery(hdl)
                .setInteger("idServicoTipoCelular",idServicoTipoCelular)
                .setMaxResults( 1 ). uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new ErroRepositorioException("Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        if (arquivoTextoRoteiro != null) {
        	if ( arquivoTextoRoteiro.getArquivoTextoNaoRecebido() != null ){
        		arquivo = arquivoTextoRoteiro.getArquivoTextoNaoRecebido();
        	} else {
        		arquivo = arquivoTextoRoteiro.getArquivoTexto();	
        	}

            nomeArquivo = arquivoTextoRoteiro.getNomeArquivo();
            
            if ( arquivoTextoRoteiro.getNomeArquivo().endsWith( ".txt" ) ){         
                StringBuilder sb = new StringBuilder();
                try {
                    sb = (StringBuilder) IoUtil.transformarBytesParaObjeto(arquivo);
                } catch (IOException e) {
                    throw new ErroRepositorioException(
                        "Erro em Transformar Array de Byte em Object");
                } catch (ClassNotFoundException e) {
                    throw new ErroRepositorioException(
                        "Erro em Transformar Array de Byte em Object");
                }
                arquivo = sb.toString().getBytes();
            }
        }

        retorno[0] = arquivo;
        retorno[1] = nomeArquivo;

        return retorno;
    }
	
	/*
	 * TODO : COSANPA
	 * Alteração do objeto de consulta de 
	 * ArquivoTextoRoteiroEmpresaDivisao para ArquivoTextoRoteiroEmpresa 
	 */
	/**
	 * [UC0811] Requisições do Dispositivo Móvel da Conta Pré-faturada.
	 * 
	 * SB0001 - Baixar Arquivo Texto para o Leiturista
	 * 
	 * @author Sávio Luiz
	 * @date 29/09/2010
	 * 
	 * @param imei
	 * @return
	 * @throws ControladorException
	 */
	public Object[] baixarArquivoTextoDivididoParaLeituristaImpressaoSimultanea(long imei)
			throws ErroRepositorioException {
		
		Object[] retorno = new Object[2];
		ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa = null;
		Session session = HibernateUtil.getSession();
		byte[] arquivo = null;
		String nomeArquivo = null;

		String hdl = "FROM ArquivoTextoRoteiroEmpresa a where a.situacaoTransmissaoLeitura.id = 2 and "
				+ " a.numeroImei = "
				+ imei
				+ " order by a.numeroSequenciaLeitura";

        try {
        	arquivoTextoRoteiroEmpresa = (ArquivoTextoRoteiroEmpresa) session
                .createQuery(hdl)
                .setMaxResults( 1 ). uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new ErroRepositorioException("Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        if (arquivoTextoRoteiroEmpresa != null) {
            arquivo = arquivoTextoRoteiroEmpresa.getArquivoTexto();
            nomeArquivo = arquivoTextoRoteiroEmpresa.getNomeArquivo();
            
            if ( arquivoTextoRoteiroEmpresa.getNomeArquivo().endsWith( ".txt" ) ){         
                StringBuilder sb = new StringBuilder();
                try {
                    sb = (StringBuilder) IoUtil.transformarBytesParaObjeto(arquivo);
                } catch (IOException e) {
                    throw new ErroRepositorioException(
                        "Erro em Transformar Array de Byte em Object");
                } catch (ClassNotFoundException e) {
                    throw new ErroRepositorioException(
                        "Erro em Transformar Array de Byte em Object");
                }
                arquivo = sb.toString().getBytes();
            }
        }

        retorno[0] = arquivo;
        retorno[1] = nomeArquivo;

        return retorno;
    }
	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0002] Gerar Relação(ROL) em TXT
	 * 
	 * @author Raphael Rossiter
	 * @date 17/09/2009
	 */
	public boolean removerArquivoTextoRoteiroEmpresa(Integer anoMesCorrente,
			Integer idGrupoFaturamentoRota) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Connection con = null;
		Statement stmt = null;
		int qtdRegistrosDeletados = 0;

		try {

			con = session.connection();
			stmt = con.createStatement();

			String delete = " delete from micromedicao.arquivo_texto_rot_empr "
					+ " where txre_amreferencia = "
					+ anoMesCorrente
					+ " and ftgr_id = "
					+ idGrupoFaturamentoRota
					+ " and sitl_id = " + SituacaoTransmissaoLeitura.LIBERADO;

			qtdRegistrosDeletados = stmt.executeUpdate(delete);

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return qtdRegistrosDeletados > 0;
	}

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0006] Gerar Relação(ROL) em TXT - COSANPA
	 * 
	 * @author Rômulo Aurelio
	 * @date 02/07/2008
	 * 
	 * @param idRota
	 * @param idLeituraTipo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisParaLeituraPorColecaoRotaCOSANPA(
			Rota rota, int inicioPesquisa, Integer anoMes)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select mrem, " // 0
					+ "quadra.id " // 1
					+ "from MovimentoRoteiroEmpresa mrem "
					+ "inner join fetch mrem.rota rota "
					+ "inner join fetch rota.faturamentoGrupo ftgr "
					+ "inner join fetch mrem.localidade loca "
					+ "inner join fetch mrem.logradouro logr "
					+ "inner join mrem.imovel imov "
					+ "inner join imov.quadra quadra "
					+ "left join fetch rota.leiturista leiturista "
					+ "left join fetch mrem.hidrometroMarca hidrometroMarca "
					+ "left join fetch logr.logradouroTipo logradouroTipo "
					+ "left join fetch logr.logradouroTitulo logradouroTitulo "
					+ "where mrem.rota.id = :idRota and mrem.anoMesMovimento = :anoMes "
					+ "order by mrem.numeroQuadra, mrem.numeroLoteImovel ";

			retorno = session.createQuery(consulta).setInteger("idRota",
					rota.getId()).setInteger("anoMes", anoMes).setFirstResult(
					inicioPesquisa).setMaxResults(1000).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisa as rotas que possuem validos para geração do relatorio de
	 * acompanhamento de leiturista.
	 * 
	 * @author Hugo Amorim
	 * @data 16/10/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection selecionarRotasRelatorioAcompanhamentoLeiturista(
			Integer anoMesReferencia, Integer rotas[], String idEmpresa,
			String idLeiturista) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select mrem.rota.id " // 0
					+ "from MovimentoRoteiroEmpresa mrem "
					+ "inner join mrem.leiturista leiturista "
					+ "where mrem.anoMesMovimento = :anoMes and ";

			if (rotas != null && rotas.length != 0 && rotas[0] != null) {
				consulta = consulta + " mrem.rota.id in (";

				for (int i = 0; i < rotas.length; i++) {
					consulta = consulta + " " + rotas[i] + ",";
				}

				consulta = Util.removerUltimosCaracteres(consulta, 1);

				consulta = consulta + " ) and ";
			}
			if (idLeiturista != null && !idLeiturista.equals("")
					&& !idLeiturista.equals("-1")) {
				consulta = consulta + " mrem.leiturista.id = " + idLeiturista
						+ " and ";
			}

			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta = consulta + "  GROUP BY  mrem.rota.id order by 1 ";

			retorno = session.createQuery(consulta).setInteger("anoMes",
					anoMesReferencia).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * 
	 * @author Hugo Amorim
	 * @data 19/10/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioAcompanhamentoLeiturista(
			RelatorioAcompanhamentoLeituristaHelper helper, Integer idRota,
			Integer idLeiturista) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = " select "
					+ " mre.mrem_ammovimento as refFaturamento,"// 0
					+ " loc.loca_id as idLocalidade,"// 1
					+ " loc.loca_nmlocalidade as nomeLocalidade,"// 2
					+ " mre.ftgr_id as gripoFaturamento,"// 3
					+ " rota.rota_cdrota as codigoRota,"// 4
					+ " leitTipo.lttp_dsleituratipo as tipoLeitura,"// 5
					+ " case"
					+ "       when leit.func_id  is not null then"
					+ " 	func.func_nmfuncionario"
					+ " 	      when leit.clie_id is not null then"
					+ " 	cli.clie_nmcliente"
					+ " end as leiturista,"// 6
					+ " mre.medt_id as medicaoTipo,"// 7
					+ " la.ltan_dsleituraanormalidade as anormalidade,"// 8
					+ " (select mre.mrem_tmleitura where mre.lttp_id in ("
					+ LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA
					+ " , "
					+ LeituraTipo.CELULAR_MOBILE
					+ ")) as dataHoraLeitRota"// 9
					+ " from micromedicao.movimento_roteiro_empr mre"
					+ " inner join cadastro.localidade loc on loc.loca_id = mre.loca_id"
					+ " inner join micromedicao.rota rota on rota.rota_id = mre.rota_id"
					+ " inner join micromedicao.leitura_tipo leitTipo on leitTipo.lttp_id = mre.lttp_id"
					+ " inner join micromedicao.leiturista leit on leit.leit_id = mre.leit_id"
					+ " left join cadastro.cliente cli on cli.clie_id = leit.clie_id"
					+ " left join cadastro.funcionario func on func.func_id = leit.func_id"
					+ " left join micromedicao.leitura_anormalidade la on la.ltan_id = mre.ltan_id"
					+ " where mre.mrem_ammovimento = :referencia and"
					+ " mre.rota_id = :idRota and mre.leit_id = :idLeiturista "
					+ " order by dataHoraLeitRota ";

			retorno = session.createSQLQuery(consulta).addScalar(
					"refFaturamento", Hibernate.INTEGER).addScalar(
					"idLocalidade", Hibernate.INTEGER).addScalar(
					"nomeLocalidade", Hibernate.STRING).addScalar(
					"gripoFaturamento", Hibernate.INTEGER).addScalar(
					"codigoRota", Hibernate.SHORT).addScalar("tipoLeitura",
					Hibernate.STRING).addScalar("leiturista", Hibernate.STRING)
					.addScalar("medicaoTipo", Hibernate.STRING).addScalar(
							"anormalidade", Hibernate.STRING).addScalar(
							"dataHoraLeitRota", Hibernate.TIMESTAMP)
					.setInteger("referencia", helper.getMesAno()).setInteger(
							"idRota", idRota).setInteger("idLeiturista",
							idLeiturista).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * @author Hugo Amorim
	 * @data 19/10/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarLeituristasDasRotas(
			RelatorioAcompanhamentoLeituristaHelper helper)
			throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select mrem.rota.id," // 0
					+ " mrem.leiturista.id,  " // 1
					+ " mrem.faturamentoGrupo.id "// 2
					+ "from MovimentoRoteiroEmpresa mrem "
					+ "inner join mrem.leiturista leiturista "
					+ "where mrem.anoMesMovimento = :anoMes and ";

			if (helper.getRotas() != null) {
				consulta = consulta + " mrem.rota.id in (";

				Iterator itera = helper.getRotas().iterator();

				while (itera.hasNext()) {
					consulta = consulta + " " + itera.next().toString() + ",";
				}

				consulta = Util.removerUltimosCaracteres(consulta, 1);

				consulta = consulta + " ) and ";
			}
			if (helper.getIdLeiturista() != null
					&& !helper.getIdLeiturista().equals("")
					&& !helper.getIdLeiturista().equals("-1")) {
				consulta = consulta + " mrem.leiturista.id = "
						+ helper.getIdLeiturista() + " and ";
			}

			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta = consulta + "  GROUP BY  mrem.rota.id,  mrem.leiturista.id,  mrem.faturamentoGrupo.id order by 1,2 ";

			retorno = session.createQuery(consulta).setInteger("anoMes",
					helper.getMesAno()).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * @author Hugo Amorim
	 * @data 19/10/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosResumoLeituraAgua(
			RelatorioAcompanhamentoLeituristaHelper helper)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select count(*) as count"
					+ " from micromedicao.movimento_roteiro_empr mre"
					+ " inner join micromedicao.medicao_historico mh on mh.mdhi_amleitura = mre.mrem_ammovimento "
					+ " where mre.medt_id =  "
					+ MedicaoTipo.LIGACAO_AGUA
					+ " and mre.imov_id = mh.lagu_id and mh.ltst_idleiturasituacaoatual <> "
					+ LeituraSituacao.NAO_REALIZADA + " and mre.mrem_ammovimento = " + helper.getMesAno() + " and ";

			if (helper.getIdRotaFluxo() != null
					&& !helper.getIdRotaFluxo().equals("")) {
				consulta = consulta + " mre.rota_id = "
						+ helper.getIdRotaFluxo() + " and ";
			}

			if (helper.getIdLeiturista() != null
					&& !helper.getIdLeiturista().equals("")) {
				consulta = consulta + " mre.leit_id = "
						+ helper.getIdLeiturista() + " and ";
			}

			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta = consulta
					+ " UNION ALL"
					+ " select "
					+ " count(*) as count"
					+ " from micromedicao.movimento_roteiro_empr mre"
					+ " where mre.mrem_ammovimento = :referencia and  mre.medt_id = "
					+ MedicaoTipo.LIGACAO_AGUA + " and ";

			if (helper.getIdRotaFluxo() != null
					&& !helper.getIdRotaFluxo().equals("")) {
				consulta = consulta + " mre.rota_id = "
						+ helper.getIdRotaFluxo() + " and ";
			}
			
			if(helper.getIdLeiturista()!=null && !helper.getIdLeiturista().equals("")){ 
				consulta = consulta + "mre.leit_id = " + helper.getIdLeiturista()+ " and ";
			}			 

			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta = consulta + " order by 1";

			retorno = session.createSQLQuery(consulta).addScalar("count",
					Hibernate.INTEGER).setInteger("referencia",
					helper.getMesAno()).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * @author Hugo Amorim
	 * @data 19/10/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosResumoLeituraPoco(
			RelatorioAcompanhamentoLeituristaHelper helper)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select count(*) as count "
					+ " from micromedicao.movimento_roteiro_empr mre"
					+ " inner join micromedicao.medicao_historico mh on mh.mdhi_amleitura = mre.mrem_ammovimento "
					+ " where mre.medt_id = "
					+ MedicaoTipo.POCO
					+ " and mre.imov_id = mh.lagu_id and mh.ltst_idleiturasituacaoatual <> "
					+ LeituraSituacao.NAO_REALIZADA + " and ";

			if (helper.getIdRotaFluxo() != null
					&& !helper.getIdRotaFluxo().equals("")) {
				consulta = consulta + " mre.rota_id = "
						+ helper.getIdRotaFluxo() + " and ";
			}

			if (helper.getIdLeiturista() != null
					&& !helper.getIdLeiturista().equals("")) {
				consulta = consulta + " mre.leit_id = "
						+ helper.getIdLeiturista() + " and ";
			}

			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta = consulta
					+ " UNION ALL "
					+ " select "
					+ " count(*) as count"
					+ " from micromedicao.movimento_roteiro_empr mre"
					+ " inner join cadastro.localidade loc on loc.loca_id = mre.loca_id"
					+ " inner join micromedicao.rota rota on rota.rota_id = mre.rota_id"
					+ " inner join micromedicao.leitura_tipo leitTipo on leitTipo.lttp_id = mre.lttp_id"
					+ " inner join micromedicao.leiturista leit on leit.leit_id = mre.leit_id"
					+ " where mre.mrem_ammovimento = :referencia and"
					+ " mre.medt_id = " + MedicaoTipo.POCO + " and ";

			if (helper.getIdRotaFluxo() != null
					&& !helper.getIdRotaFluxo().equals("")) {
				consulta = consulta + " mre.rota_id = "
						+ helper.getIdRotaFluxo() + " and ";
			}
			/*
			 * if(helper.getIdLeiturista()!=null &&
			 * !helper.getIdLeiturista().equals("")){ consulta = consulta + "
			 * mre.leit_id = " + helper.getIdLeiturista()+ " and "; }
			 */

			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta = consulta + " order by 1";

			retorno = session.createSQLQuery(consulta).addScalar("count",
					Hibernate.INTEGER).setInteger("referencia",
					helper.getMesAno()).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * @author Hugo Amorim
	 * @data 20/10/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosFaturadosPelaMedia(
			RelatorioAcompanhamentoLeituristaHelper helper)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select "
					+ " count(*) as total "
					+ " from micromedicao.consumo_historico ch"
					+ " inner join micromedicao.movimento_roteiro_empr mre "
					+ " on mre.mrem_ammovimento = ch.cshi_amfaturamento and mre.imov_id = ch.imov_id"
					+ " where mre.medt_id = " + MedicaoTipo.LIGACAO_AGUA
					+ " and ch.cstp_id in ( " + ConsumoTipo.MEDIA_HIDROMETRO
					+ "," + ConsumoTipo.MEDIA_IMOVEL + " )"
					+ " and mre.mrem_ammovimento = :referencia" + " and ";

			if (helper.getIdRotaFluxo() != null
					&& !helper.getIdRotaFluxo().equals("")) {
				consulta = consulta + " mre.rota_id = "
						+ helper.getIdRotaFluxo() + " and ";
			}
			/*
			 * if(helper.getIdLeiturista()!=null &&
			 * !helper.getIdLeiturista().equals("")){ consulta = consulta + "
			 * mre.leit_id = " + helper.getIdLeiturista()+ " and "; }
			 */

			consulta = Util.removerUltimosCaracteres(consulta, 4);

			retorno = session.createSQLQuery(consulta).addScalar("total",
					Hibernate.BIG_DECIMAL).setInteger("referencia",
					helper.getMesAno()).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * @author Hugo Amorim
	 * @data 20/10/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosFaturadosPeloMinimo(
			RelatorioAcompanhamentoLeituristaHelper helper)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select count(*) as total "
					+ " from micromedicao.consumo_historico ch"
					+ " inner join micromedicao.movimento_roteiro_empr mre "
					+ " on mre.mrem_ammovimento = ch.cshi_amfaturamento and mre.imov_id = ch.imov_id"
					+ " where mre.medt_id = "
					+ MedicaoTipo.LIGACAO_AGUA
					+ " and ch.cshi_nnconsumofaturadomes <= cshi_nnconsumominimo"
					+ " and mre.mrem_ammovimento = :referencia" + " and ";

			if (helper.getIdRotaFluxo() != null
					&& !helper.getIdRotaFluxo().equals("")) {
				consulta = consulta + " mre.rota_id = "
						+ helper.getIdRotaFluxo() + " and ";
			}
			/*
			 * if(helper.getIdLeiturista()!=null &&
			 * !helper.getIdLeiturista().equals("")){ consulta = consulta + "
			 * mre.leit_id = " + helper.getIdLeiturista()+ " and "; }
			 */

			consulta = Util.removerUltimosCaracteres(consulta, 4);

			retorno = session.createSQLQuery(consulta).addScalar("total",
					Hibernate.BIG_DECIMAL).setInteger("referencia",
					helper.getMesAno()).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * @author Hugo Amorim
	 * @data 20/10/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosConsumoTotalMedido(
			RelatorioAcompanhamentoLeituristaHelper helper)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select sum(mdhi_nnconsumomedidomes) as sum"
					+ " from micromedicao.medicao_historico mh"
					+ " inner join micromedicao.movimento_roteiro_empr mre "
					+ " on mre.mrem_ammovimento = mh.mdhi_amleitura and mre.imov_id = mh.lagu_id"
					+ " where mre.medt_id = " + MedicaoTipo.LIGACAO_AGUA
					+ " and mre.mrem_ammovimento = :referencia "
					+ " and mh.mdhi_nnconsumomedidomes is not null " + " and ";

			if (helper.getIdRotaFluxo() != null
					&& !helper.getIdRotaFluxo().equals("")) {
				consulta = consulta + " mre.rota_id = "
						+ helper.getIdRotaFluxo() + " and ";
			}
			/*
			 * if(helper.getIdLeiturista()!=null &&
			 * !helper.getIdLeiturista().equals("")){ consulta = consulta + "
			 * mre.leit_id = " + helper.getIdLeiturista()+ " and "; }
			 */

			consulta = Util.removerUltimosCaracteres(consulta, 4);

			retorno = session.createSQLQuery(consulta).addScalar("sum",
					Hibernate.BIG_DECIMAL).setInteger("referencia",
					helper.getMesAno()).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * @author Hugo Amorim
	 * @data 20/10/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosConsumoTotalFaturado(
			RelatorioAcompanhamentoLeituristaHelper helper)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select sum(cshi_nnconsumofaturadomes) as sum"
					+ " from micromedicao.consumo_historico ch"
					+ " inner join micromedicao.movimento_roteiro_empr mre "
					+ " on mre.mrem_ammovimento = ch.cshi_amfaturamento and mre.imov_id = ch.imov_id"
					+ " where mre.medt_id = " + MedicaoTipo.LIGACAO_AGUA
					// + " and ch.cstp_id = " + ConsumoTipo.REAL
					+ " and mre.mrem_ammovimento = :referencia" + " and ";

			if (helper.getIdRotaFluxo() != null
					&& !helper.getIdRotaFluxo().equals("")) {
				consulta = consulta + " mre.rota_id = "
						+ helper.getIdRotaFluxo() + " and ";
			}
			/*
			 * if(helper.getIdLeiturista()!=null &&
			 * !helper.getIdLeiturista().equals("")){ consulta = consulta + "
			 * mre.leit_id = " + helper.getIdLeiturista()+ " and "; }
			 */

			consulta = Util.removerUltimosCaracteres(consulta, 4);

			retorno = session.createSQLQuery(consulta).addScalar("sum",
					Hibernate.BIG_DECIMAL).setInteger("referencia",
					helper.getMesAno()).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> pesquisarRelatorioAnormalidadeLeituraPeriodo(
			FiltrarRelatorioAnormalidadeLeituraPeriodoHelper filtro)
			throws ErroRepositorioException {

		StringBuilder sql = new StringBuilder();

		Map<String, Object> parameters = new HashMap<String, Object>();

		sql.append("SELECT  \n");
		sql.append("	UN.UNEG_ID, \n");// 0
		sql.append("	UN.UNEG_NMUNIDADENEGOCIO, \n");// 1
		sql.append("	LOC.LOCA_ID,  \n");// 2
		sql.append("	LOC.LOCA_NMLOCALIDADE, \n");// 3
		sql.append("	SC.STCM_CDSETORCOMERCIAL, \n");// 4
		sql.append("	QUADRA.QDRA_NNQUADRA, \n");// 5
		sql.append("	IMOVEL.IMOV_NNLOTE,  \n");// 6
		sql.append("	IMOVEL.IMOV_NNSUBLOTE, \n");// 7
		sql.append("	IMOVEL.IMOV_ID, \n");// 8
		sql.append("	CLIENTE.CLIE_NMCLIENTE, \n");// 9
		sql.append("	ROTA.FTGR_ID, \n");// 10
		sql.append("	ROTA.ROTA_CDROTA, \n");// 11
		sql.append("	IMOVEL.IMOV_NNSEQUENCIALROTA \n");// 12

		sql.append("FROM MICROMEDICAO.MEDICAO_HISTORICO MH \n");
		sql
				.append("	INNER JOIN CADASTRO.IMOVEL ON ( MH.LAGU_ID = IMOVEL.IMOV_ID) \n");
		sql
				.append("	INNER JOIN CADASTRO.LOCALIDADE LOC ON (IMOVEL.LOCA_ID = LOC.LOCA_ID ) \n");
		sql
				.append("	INNER JOIN CADASTRO.UNIDADE_NEGOCIO UN ON (LOC.UNEG_ID = UN.UNEG_ID ) \n");
		sql
				.append("	INNER JOIN CADASTRO.SETOR_COMERCIAL SC ON ( IMOVEL.STCM_ID = SC.STCM_ID) \n");
		sql
				.append("	INNER JOIN CADASTRO.QUADRA ON ( IMOVEL.QDRA_ID = QUADRA.QDRA_ID) \n");
		sql
				.append("	INNER JOIN CADASTRO.CLIENTE_IMOVEL CI ON ( IMOVEL.IMOV_ID = CI.IMOV_ID) \n");
		sql
				.append("	INNER JOIN CADASTRO.CLIENTE ON ( CI.CLIE_ID = CLIENTE.CLIE_ID) \n");
		sql
				.append("	INNER JOIN MICROMEDICAO.ROTA ON ( QUADRA.ROTA_ID = ROTA.ROTA_ID AND IMOVEL.STCM_ID = ROTA.STCM_ID) \n");
		sql
				.append("	INNER JOIN MICROMEDICAO.LEITURA_ANORMALIDADE LA ON ( MH.ltan_idleitanorminformada = LA.LTAN_ID)\n");

		sql.append("	INNER JOIN ( \n");
		sql.append("		SELECT \n");
		sql.append("			MH_INTERNO.LAGU_ID \n");
		sql.append("		FROM MICROMEDICAO.MEDICAO_HISTORICO MH_INTERNO \n");
		sql
				.append("		WHERE MH_INTERNO.ltan_idleitanorminformada IS NOT NULL \n");
		sql
				.append("				AND MH_INTERNO.MDHI_AMLEITURA BETWEEN :anoMesInicial AND :anoMesFinal \n");
		sql
				.append("				AND MH_INTERNO.ltan_idleitanorminformada = :anormalidade \n");
		sql
				.append("		GROUP BY MH_INTERNO.LAGU_ID HAVING COUNT(MH_INTERNO.LAGU_ID) = :quantidadeMeses \n");
		sql.append("	)IMOVEIS_ANORMALIDADE \n");
		sql.append("	ON ( IMOVEIS_ANORMALIDADE.LAGU_ID = MH.LAGU_ID) \n");

		sql
				.append("WHERE MH.ltan_idleitanorminformada IS NOT NULL \n");
		sql.append("	AND CI.CRTP_ID = " + ClienteRelacaoTipo.USUARIO
				+ " AND CI.CLIM_DTRELACAOFIM IS NULL \n");
		sql
				.append("	AND MH.MDHI_AMLEITURA BETWEEN :anoMesInicial AND :anoMesFinal \n");

		sql.append("	AND LA.LTAN_ID = :anormalidade \n");

		parameters.put("anoMesInicial", filtro.getAnoMesReferenciaInicial());
		parameters.put("anoMesFinal", filtro.getAnoMesReferenciaFinal());
		parameters.put("quantidadeMeses", filtro.getQuantidadeMeses());
		parameters.put("anormalidade", filtro.getAnormalidadeLeitura());

		if (filtro.getGrupoFaturamento() != null) {
			sql.append("	AND ROTA.FTGR_ID = :grupoFaturamento \n");
			parameters.put("grupoFaturamento", filtro.getGrupoFaturamento());

		}

		if (filtro.getUnidadeNegocio() != null) {
			sql.append("	AND UN.UNEG_ID = :unidadeNegocio  \n");
			parameters.put("unidadeNegocio", filtro.getUnidadeNegocio());
		}

		if (filtro.getLocalidadeInicial() != null) {
			sql
					.append("	AND LOC.LOCA_ID BETWEEN :localidadeInicial AND :localidadeFinal  \n");
			parameters.put("localidadeInicial", filtro.getLocalidadeInicial());
			parameters.put("localidadeFinal", filtro.getLocalidadeFinal());
		}

		if (filtro.getSetorComercialInicial() != null) {
			sql
					.append("	AND SC.STCM_CDSETORCOMERCIAL BETWEEN :setorInicial AND :setorFinal \n");
			parameters.put("setorInicial", filtro.getSetorComercialInicial());
			parameters.put("setorFinal", filtro.getSetorComercialFinal());
		}

		if (filtro.getRotaInicial() != null) {
			sql
					.append("	AND ROTA.ROTA_CDROTA BETWEEN :rotaInicial AND :rotaFinal  \n");
			parameters.put("rotaInicial", filtro.getRotaInicial());
			parameters.put("rotaFinal", filtro.getRotaFinal());
		}

		if (filtro.getSequencialRotaInicial() != null) {
			sql
					.append("	AND IMOVEL.IMOV_NNSEQUENCIALROTA BETWEEN :sequencialInicial AND :sequencialFinal  \n");
			parameters.put("sequencialInicial", filtro
					.getSequencialRotaInicial());
			parameters.put("sequencialFinal", filtro.getSequencialRotaFinal());
		}

		sql.append("GROUP BY \n");
		sql.append("		UN.UNEG_ID, \n");
		sql.append("		UN.UNEG_NMUNIDADENEGOCIO, \n");
		sql.append("		LOC.LOCA_ID,  \n");
		sql.append("		LOC.LOCA_NMLOCALIDADE, \n");
		sql.append("		SC.STCM_CDSETORCOMERCIAL, \n");
		sql.append("		QUADRA.QDRA_NNQUADRA, \n");
		sql.append("		IMOVEL.IMOV_NNLOTE,  \n");
		sql.append("		IMOVEL.IMOV_NNSUBLOTE, \n");
		sql.append("		IMOVEL.IMOV_ID, \n");
		sql.append("		CLIENTE.CLIE_NMCLIENTE, \n");
		sql.append("		ROTA.FTGR_ID, \n");
		sql.append("		ROTA.ROTA_CDROTA, \n");
		sql.append("		IMOVEL.IMOV_NNSEQUENCIALROTA \n");

		sql.append("ORDER BY \n");
		sql.append("		UN.UNEG_ID, \n");
		sql.append("		LOC.LOCA_ID,  \n");
		sql.append("		SC.STCM_CDSETORCOMERCIAL, \n");
		sql.append("		QUADRA.QDRA_NNQUADRA, \n");
		sql.append("		IMOVEL.IMOV_NNLOTE,  \n");
		sql.append("		IMOVEL.IMOV_NNSUBLOTE, \n");
		sql.append("		ROTA.FTGR_ID, \n");
		sql.append("		ROTA.ROTA_CDROTA, \n");
		sql.append("		IMOVEL.IMOV_NNSEQUENCIALROTA \n");

		Session session = HibernateUtil.getSession();

		try {
			SQLQuery query = criarSQLQueryComParametros(sql.toString(),
					parameters, session);

			return query.addScalar("UNEG_ID", Hibernate.INTEGER).addScalar(
					"UNEG_NMUNIDADENEGOCIO", Hibernate.STRING).addScalar(
					"LOCA_ID", Hibernate.INTEGER).addScalar(
					"LOCA_NMLOCALIDADE", Hibernate.STRING).addScalar(
					"STCM_CDSETORCOMERCIAL", Hibernate.INTEGER).addScalar(
					"QDRA_NNQUADRA", Hibernate.INTEGER).addScalar(
					"IMOV_NNLOTE", Hibernate.SHORT).addScalar("IMOV_NNSUBLOTE",
					Hibernate.SHORT).addScalar("IMOV_ID", Hibernate.INTEGER)
					.addScalar("CLIE_NMCLIENTE", Hibernate.STRING).addScalar(
							"FTGR_ID", Hibernate.INTEGER).addScalar(
							"ROTA_CDROTA", Hibernate.INTEGER).addScalar(
							"IMOV_NNSEQUENCIALROTA", Hibernate.INTEGER).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public Collection<Object[]> pesquisarTotalRegistrosRelatorioAnormalidadeLeituraPeriodo(
			FiltrarRelatorioAnormalidadeLeituraPeriodoHelper filtro)
			throws ErroRepositorioException {

		StringBuilder sql = new StringBuilder();

		Map<String, Object> parameters = new HashMap<String, Object>();

		sql.append("SELECT  \n");
		sql.append("	IMOVEL.IMOV_ID, \n");
		sql.append("	COUNT(*) AS QTDREGISTROS \n");

		sql.append("FROM MICROMEDICAO.MEDICAO_HISTORICO MH \n");
		sql
				.append("	INNER JOIN CADASTRO.IMOVEL ON ( MH.LAGU_ID = IMOVEL.IMOV_ID) \n");
		sql
				.append("	INNER JOIN CADASTRO.LOCALIDADE LOC ON (IMOVEL.LOCA_ID = LOC.LOCA_ID ) \n");
		sql
				.append("	INNER JOIN CADASTRO.CLIENTE_IMOVEL CI ON ( IMOVEL.IMOV_ID = CI.IMOV_ID) \n");
		sql
				.append("	INNER JOIN MICROMEDICAO.ROTA ON ( IMOVEL.STCM_ID = ROTA.STCM_ID) \n");

		sql.append("	INNER JOIN ( \n");
		sql.append("		SELECT \n");
		sql.append("			MH_INTERNO.LAGU_ID \n");
		sql.append("		FROM MICROMEDICAO.MEDICAO_HISTORICO MH_INTERNO \n");
		sql
				.append("		WHERE MH_INTERNO.ltan_idleitanorminformada IS NOT NULL \n");
		sql
				.append("				AND MH_INTERNO.MDHI_AMLEITURA BETWEEN :anoMesInicial AND :anoMesFinal \n");
		sql
				.append("				AND MH_INTERNO.ltan_idleitanorminformada = :anormalidade \n");
		sql
				.append("		GROUP BY MH_INTERNO.LAGU_ID HAVING COUNT(MH_INTERNO.LAGU_ID) = :quantidadeMeses \n");
		sql.append("	)IMOVEIS_ANORMALIDADE \n");
		sql.append("	ON ( IMOVEIS_ANORMALIDADE.LAGU_ID = MH.LAGU_ID) \n");

		sql
				.append("WHERE MH.ltan_idleitanorminformada IS NOT NULL \n");
		sql.append("	AND CI.CRTP_ID = " + ClienteRelacaoTipo.USUARIO
				+ " AND CI.CLIM_DTRELACAOFIM IS NULL \n");
		sql
				.append("	AND MH.MDHI_AMLEITURA BETWEEN :anoMesInicial AND :anoMesFinal \n");

		sql
				.append("	AND MH.ltan_idleitanorminformada = :anormalidade \n");

		parameters.put("anoMesInicial", filtro.getAnoMesReferenciaInicial());
		parameters.put("anoMesFinal", filtro.getAnoMesReferenciaFinal());
		parameters.put("quantidadeMeses", filtro.getQuantidadeMeses());
		parameters.put("anormalidade", filtro.getAnormalidadeLeitura());

		if (filtro.getGrupoFaturamento() != null) {
			sql.append("	AND ROTA.FTGR_ID = :grupoFaturamento \n");
			parameters.put("grupoFaturamento", filtro.getGrupoFaturamento());

		}

		if (filtro.getUnidadeNegocio() != null) {
			sql.append("	AND LOC.UNEG_ID = :unidadeNegocio  \n");
			parameters.put("unidadeNegocio", filtro.getUnidadeNegocio());
		}

		if (filtro.getLocalidadeInicial() != null) {
			sql
					.append("	AND IMOVEL.LOCA_ID BETWEEN :localidadeInicial AND :localidadeFinal  \n");
			parameters.put("localidadeInicial", filtro.getLocalidadeInicial());
			parameters.put("localidadeFinal", filtro.getLocalidadeFinal());
		}

		if (filtro.getSetorComercialInicial() != null) {
			sql
					.append("	AND IMOVEL.STCM_ID BETWEEN :setorInicial AND :setorFinal \n");
			parameters.put("setorInicial", filtro.getSetorComercialInicial());
			parameters.put("setorFinal", filtro.getSetorComercialFinal());
		}

		if (filtro.getRotaInicial() != null) {
			sql
					.append("	AND ROTA.ROTA_CDROTA BETWEEN :rotaInicial AND :rotaFinal  \n");
			parameters.put("rotaInicial", filtro.getRotaInicial());
			parameters.put("rotaFinal", filtro.getRotaFinal());
		}

		if (filtro.getSequencialRotaInicial() != null) {
			sql
					.append("	AND IMOVEL.IMOV_NNSEQUENCIALROTA BETWEEN :sequencialInicial AND :sequencialFinal  \n");
			parameters.put("sequencialInicial", filtro
					.getSequencialRotaInicial());
			parameters.put("sequencialFinal", filtro.getSequencialRotaFinal());
		}

		sql.append("GROUP BY \n");
		sql.append("		IMOVEL.IMOV_ID \n");

		Session session = HibernateUtil.getSession();

		try {
			SQLQuery query = criarSQLQueryComParametros(sql.toString(),
					parameters, session);

			return query.addScalar("IMOV_ID", Hibernate.INTEGER).addScalar(
					"QTDREGISTROS", Hibernate.INTEGER).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Metodo seta os parametros numa determinada consulta e retorna um objeto
	 * SQLQuery com o sql já com parametros.
	 * 
	 * @since 16/09/2009
	 * @author Marlon Patrick
	 * @throws ErroRepositorioException 
	 */
	private SQLQuery criarSQLQueryComParametros(String consulta,
			Map<String, Object> parameters, Session session) throws ErroRepositorioException {
		
		SQLQuery query = null;
		
		try{
			query = session.createSQLQuery(consulta);
	
			Set<String> set = parameters.keySet();
			Iterator<String> iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection<? extends Object> collection = (ArrayList<? extends Object>) parameters
							.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}
			
		}catch(Exception e)
		{
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}

		return query;
	}

	/**
	 * [UC0082] - Registrar Leituras e Anormalidades
	 * 
	 * [SB0001] - Gerar Relatório Resumo das Leituras e Anormalidades
	 * 
	 * Pesquisa os dados do relatório do comparativo de leituras e anormalidades
	 * 
	 * @author Arthur Carvalho - Hugo Leonardo      - Magno Gouveia
	 * @date 13/11/2009 - 18/03/2010				- 21/06/2011
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarDadosRelatorioComparativoLeiturasEAnormalidadesCount(
			Integer idGrupoFaturamento, Integer idEmpresa, Integer anoMes,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorInicial, Integer idSetorFinal, Integer idGerencia,
			Integer idUnidadeNegocio, Integer idLeiturista,
			Integer idRotaInicial, Integer idRotaFinal, Integer idPerfilImovel,
			Integer numOcorrenciasConsecutivas, Collection colecaoAnormalidadesLeituras,
			SistemaParametro sistemaParametro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select count(distinct mrem.imov_id ) as qtde "
					+ " from micromedicao.movimento_roteiro_empr mrem"
					+ " INNER JOIN cadastro.localidade loc on loc.loca_id = mrem.loca_id"
					+ " INNER JOIN cadastro.setor_comercial setor on setor.loca_id = mrem.loca_id and setor.stcm_cdsetorcomercial = mrem.mrem_cdsetorcomercial"
					+ " INNER JOIN cadastro.imovel i on i.imov_id = mrem.imov_id "
					+ " where mrem.mrem_ammovimento =:anoMes and mrem.mrem_dtinstalacaohidrometro is not null "
					+ " and i.imov_icexclusao <> 1 ";

			if (idEmpresa != null) {
				consulta = consulta + " and mrem.empr_id = " + idEmpresa;

				// Filtra por Leiturista
				if (idLeiturista != null
						&& !idLeiturista
								.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					consulta = consulta + " and mrem.leit_id = " + idLeiturista;
				}
			}
			if (idGrupoFaturamento != null) {
				consulta = consulta + "	and mrem.ftgr_id = "
						+ idGrupoFaturamento;
			}
			if (idGerencia != null) {
				consulta = consulta + "	and mrem.greg_id = " + idGerencia;
			}
			if (idUnidadeNegocio != null) {
				consulta = consulta + "	and loc.uneg_id = " + idUnidadeNegocio;
			}

			// Localidade
			if (idLocalidadeInicial != null) {
				if (idLocalidadeInicial.equals(idLocalidadeFinal)) {
					consulta = consulta + "	and loc.loca_id = "
							+ idLocalidadeInicial;

					// Setor Comercial
					if (idSetorInicial != null) {
						if (idSetorInicial.equals(idSetorFinal)) {
							consulta = consulta + "	and setor.stcm_id = "
									+ idSetorInicial;

							// Rota
							if (idRotaInicial != null) {
								if (idRotaInicial.equals(idRotaFinal)) {
									consulta += " and mrem.mrem_cdrota = "
											+ idRotaInicial;
								} else {
									consulta += " and mrem.mrem_cdrota between "
											+ idRotaInicial
											+ " and "
											+ idRotaFinal;
								}
							}
						} else {
							consulta = consulta + "	and setor.stcm_id between "
									+ idSetorInicial + " and " + idSetorFinal;
						}
					}
				} else {
					consulta = consulta + "	and loc.loca_id between "
							+ idLocalidadeInicial + " and " + idLocalidadeFinal;
				}
			}

			if(idPerfilImovel != null){
				consulta += " AND i.iper_id = :idPerfilImovel ";
			}
			
			List<Integer> anoMesOcorrencias = new ArrayList<Integer>();
			if(colecaoAnormalidadesLeituras != null){
				// caso a opção -1 tenha sido selecionada, remove
				if(colecaoAnormalidadesLeituras.contains(ConstantesSistema.NUMERO_NAO_INFORMADO)){
					colecaoAnormalidadesLeituras.remove(ConstantesSistema.NUMERO_NAO_INFORMADO);
				}
				
				// caso não tenha sido setado o número de ocorrências consecutivas, ou esta seja menor ou igual a 0
				if(numOcorrenciasConsecutivas == null || numOcorrenciasConsecutivas <= 0){
					numOcorrenciasConsecutivas = 1;
				}
				
				for (int i = 1; i <= (numOcorrenciasConsecutivas + 1); i++) {
					anoMesOcorrencias.add(Util.subtrairMesDoAnoMes((Integer) sistemaParametro.getAnoMesFaturamento(), i));
				}
				
				consulta += " AND mrem.ltan_id IN (:anormalidadesLeituras) " 
					      + " AND i.imov_id IN (SELECT imovel "
						  +					   "FROM "
						  +						"(SELECT mh.lagu_id AS imovel, mh.ltan_idleitanorminformada "
						  +						"FROM micromedicao.medicao_historico mh "
						  +						"WHERE mdhi_amleitura IN (:anoMesOcorrencias) "
						  +				  		  "AND mh.ltan_idleitanorminformada IN (:anormalidadesLeituras) "
						  +						"GROUP BY mh.lagu_id, mh.ltan_idleitanorminformada HAVING COUNT(*) >= :numOcorrenciasConsecutivas "
						  +						"ORDER BY 1) temp) ";
				
			}
				
			Query query = session.createSQLQuery(consulta)
										.addScalar("qtde", Hibernate.INTEGER)
						   				.setInteger("anoMes", anoMes);
			
			if (idPerfilImovel != null) {
				query.setInteger("idPerfilImovel", idPerfilImovel);
			}
			
			if (colecaoAnormalidadesLeituras != null && colecaoAnormalidadesLeituras.size() > 0) {
				query.setParameterList("anormalidadesLeituras", colecaoAnormalidadesLeituras);
				query.setParameterList("anoMesOcorrencias", anoMesOcorrencias);
				query.setInteger("numOcorrenciasConsecutivas", numOcorrenciasConsecutivas);
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
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * @author Hugo Amorim
	 * @date 18/11/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisPorRotaAlternativaCAER(Rota rota,
			String empresa) throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta =

			"select imovel.id,"// 0
					+ "localidade.id,"// 1
					+ "setorComercial.codigo,"// 2
					+ "quadra.numeroQuadra,"// 3
					+ "imovel.lote,"// 4
					+ "imovel.subLote,"// 5
					+ "imovelPerfil.id,"// 6
					+ "ligacaoAgua.id,"// 7
					+ "hidInstHistoricoAgua.id,"// 8
					+ "hidInstHistoricoPoco.id,"// 9
					+ "rotaAlternativa.id,"// 10
					+ "rotaAlternativa.indicadorFiscalizarSuprimido,"// 11
					+ "rotaAlternativa.indicadorFiscalizarCortado,"// 12
					+ "rotaAlternativa.indicadorGerarFiscalizacao,"// 13
					+ "rotaAlternativa.indicadorGerarFalsaFaixa,"// 14
					+ "rotaAlternativa.percentualGeracaoFiscalizacao,"// 15
					+ "rotaAlternativa.percentualGeracaoFaixaFalsa,"// 16
					+ "empresa.id,"// 17
					+ "empresa.descricaoAbreviada,"// 18
					+ "empresa.email,"// 19
					+ "faturamentoGrupo.id,"// 20
					+ "faturamentoGrupo.descricao,"// 21
					+ "leituraTipo.id,"// 22
					+ "ligacaoAguaSituacao.id,"// 23
					+ "ligacaoEsgotoSituacao.id, "// 24
					+ "faturamentoSituacaoTipo.indicadorParalisacaoLeitura, "// 25
					+ "medTipoAgua.id, "// 26
					+ "medTipoPoco.id, "// 27
					+ "gerenciaRegional.id, "// 28
					+ "gerenciaRegional.nome, "// 29
					+ "localidade.descricao, "// 30
					+ "ligacaoAguaSituacao.descricao, "// 31
					+ "ligacaoAgua.numeroLacre, "// 32
					+ "hidroAgua.numero, "// 33
					+ "imovel.numeroSequencialRota, "// 34
					+ "rotaAlternativa.codigo, "// 35
					+ "hidroPoco.numero, "// 36
					+ "ligacaoAguaSituacao.descricaoAbreviado,"// 37
					+ "logradouro.id,"// 38
					+ "logradouro.nome,"// 39
					+ "imovel.numeroImovel,"// 40
					+ "imovel.complementoEndereco,"// 41
					+ "hidroAgua.numeroDigitosLeitura,"// 42
					+ "hidroPoco.numeroDigitosLeitura,"// 43
					+ "logTit.descricaoAbreviada, "// 44
					+ "logTip.descricaoAbreviada, "// 45
					+ "ligacaoAguaSituacao.indicadorFaturamentoSituacao, "// 46
					+ "ligacaoEsgotoSituacao.indicadorFaturamentoSituacao, "// 47
					+ "bairro.id, "// 48
					+ "bairro.nome "// 49
					+ "from Imovel imovel "
					+ "inner join imovel.rotaAlternativa rotaAlternativa "
					+ "left join imovel.quadraFace quadraFace "
					+ "left join imovel.localidade localidade "
					+ "left join imovel.setorComercial setorComercial "
					+ "left join imovel.quadra quadra "
					+ "left join imovel.imovelPerfil imovelPerfil "
					+ "left join imovel.ligacaoAgua ligacaoAgua "
					+ "left join imovel.hidrometroInstalacaoHistorico hidInstHistoricoPoco "
					+ "left join imovel.logradouroBairro logradouroBairro "
					+ "left join ligacaoAgua.hidrometroInstalacaoHistorico hidInstHistoricoAgua "
					+ "left join hidInstHistoricoAgua.hidrometro hidroAgua "
					+ "left join hidInstHistoricoPoco.hidrometro hidroPoco "
					+ "left join fetch hidInstHistoricoAgua.hidrometroProtecao "
					+ "left join fetch hidInstHistoricoAgua.hidrometroLocalInstalacao "
					+ "left join fetch hidInstHistoricoPoco.hidrometroProtecao "
					+ "left join fetch hidInstHistoricoPoco.hidrometroLocalInstalacao "
					+ "left join hidInstHistoricoAgua.medicaoTipo medTipoAgua "
					+ "left join hidInstHistoricoPoco.medicaoTipo medTipoPoco "
					+ "left join quadra.roteiroEmpresa roteiroEmpresa "
					+ "left join rotaAlternativa.leiturista leiturista "
					+ "left join rotaAlternativa.empresa empresa "
					+ "left join rotaAlternativa.faturamentoGrupo faturamentoGrupo "
					+ "left join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "
					+ "left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "
					+ "left join imovel.faturamentoSituacaoTipo faturamentoSituacaoTipo "
					+ "left join rotaAlternativa.leituraTipo leituraTipo "
					+ "left join localidade.gerenciaRegional gerenciaRegional "
					+ "left join logradouroBairro.logradouro logradouro "
					+ "left join logradouroBairro.bairro bairro "
					+ "left join logradouro.logradouroTitulo logTit "
					+ "left join logradouro.logradouroTipo logTip "
					+ "left join localidade.gerenciaRegional gerenciaRegional "
					+ "where rotaAlternativa.id = :idRota "
					+ "AND imovelPerfil.indicadorGerarDadosLeitura = 1 ";

			consulta = consulta
					+ "order by gerenciaRegional.id, localidade.id,setorComercial.codigo,rotaAlternativa.codigo, imovel.numeroSequencialRota";

			retorno = session.createQuery(consulta).setInteger("idRota",
					rota.getId()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * Verifica se houve todos os processamentos do um determinado grupo e
	 * ano-Mês de Referencia e inserir no cronograma de atividades.
	 * 
	 * [UC0840] - Atualizar Faturamento do Movimento Celular
	 * 
	 * @author Sávio Luiz
	 * @date 01/12/2009
	 * 
	 * @param grupo
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 */
	public void atualizarDataRealizacaoGronogramaPreFaturamento(Integer grupo,
			Integer anoMesReferencia, Date dataRealizacao)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			// Verifica ja foi atualizado a data de realização do gronograma
			// para a atividade de Registrar
			// Leituras e Anormalidades
			StringBuffer hql = new StringBuffer(
					"FROM FaturamentoAtividadeCronograma f where f.faturamentoAtividade.id = ");
			hql.append(FaturamentoAtividade.REGISTRAR_LEITURA_ANORMALIDADE);
			hql
					.append(" and f.faturamentoGrupoCronogramaMensal.anoMesReferencia =");
			hql.append(anoMesReferencia);
			hql
					.append(" and f.faturamentoGrupoCronogramaMensal.faturamentoGrupo.id =");
			hql.append(grupo);
			hql.append(" and f.dataRealizacao is null");
			FaturamentoAtividadeCronograma faturamentoAtivCronogramaRegistrar = (FaturamentoAtividadeCronograma) session
					.createQuery(hql.toString()).uniqueResult();

			if (faturamentoAtivCronogramaRegistrar != null
					&& !faturamentoAtivCronogramaRegistrar.equals("")) {
				faturamentoAtivCronogramaRegistrar
						.setDataRealizacao(dataRealizacao);
				faturamentoAtivCronogramaRegistrar
						.setUltimaAlteracao(dataRealizacao);
				session.update(faturamentoAtivCronogramaRegistrar);

				// Para atividade de Efetuar Leitura
				hql = new StringBuffer(
						"FROM FaturamentoAtividadeCronograma f where f.faturamentoAtividade.id = ");
				hql.append(FaturamentoAtividade.EFETUAR_LEITURA);
				hql
						.append(" and f.faturamentoGrupoCronogramaMensal.anoMesReferencia =");
				hql.append(anoMesReferencia);
				hql
						.append(" and f.faturamentoGrupoCronogramaMensal.faturamentoGrupo.id =");
				hql.append(grupo);
				FaturamentoAtividadeCronograma faturamentoAtivCronogramaEfetuar = (FaturamentoAtividadeCronograma) session
						.createQuery(hql.toString()).uniqueResult();
				if (faturamentoAtivCronogramaEfetuar != null) {
					faturamentoAtivCronogramaEfetuar
							.setDataRealizacao(dataRealizacao);
					faturamentoAtivCronogramaEfetuar
							.setUltimaAlteracao(dataRealizacao);
					session.update(faturamentoAtivCronogramaEfetuar);
				}

				// Para atividade de Consitir Leitura
				hql = new StringBuffer(
						"FROM FaturamentoAtividadeCronograma f where f.faturamentoAtividade.id = ");
				hql
						.append(FaturamentoAtividade.CONSISTIR_LEITURAS_E_CALCULAR_CONSUMOS);
				hql
						.append(" and f.faturamentoGrupoCronogramaMensal.anoMesReferencia =");
				hql.append(anoMesReferencia);
				hql
						.append(" and f.faturamentoGrupoCronogramaMensal.faturamentoGrupo.id =");
				hql.append(grupo);
				FaturamentoAtividadeCronograma faturamentoAtivCronogramaConsistir = (FaturamentoAtividadeCronograma) session
						.createQuery(hql.toString()).uniqueResult();
				if (faturamentoAtivCronogramaConsistir != null) {
					faturamentoAtivCronogramaConsistir
							.setDataRealizacao(new Date());
					faturamentoAtivCronogramaConsistir
							.setUltimaAlteracao(new Date());
					session.update(faturamentoAtivCronogramaConsistir);
				}
			}
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * [UC0101] Consistir Leituras e Calcular Consumos
	 * 
	 * [SB0014] - Verificar Estouro de Consumo
	 * 
	 * @author Vivianne Sousa
	 * @date 17/12/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public ConsumoAnormalidadeAcao pesquisarConsumoAnormalidadeAcao(
			Integer idConsumoAnormalidade, Integer idCategoria,
			Integer idPerfilImovel) throws ErroRepositorioException {

		ConsumoAnormalidadeAcao retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String consulta = "select csaa "
					+ " from ConsumoAnormalidadeAcao csaa "
					+ " where csaa.consumoAnormalidade.id = "
					+ idConsumoAnormalidade;

			if (idCategoria != null) {
				consulta = consulta + " and csaa.categoria.id = " + idCategoria;
			}
			if (idPerfilImovel != null) {
				consulta = consulta + "	and csaa.imovelPerfil = "
						+ idPerfilImovel;
			}

			retorno = (ConsumoAnormalidadeAcao) session.createQuery(consulta)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0101] Consistir Leituras e Calcular Consumos
	 * 
	 * [SB0014] - Verificar Estouro de Consumo
	 * 
	 * @author Vivianne Sousa
	 * @date 17/12/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarConsumoHistoricoConsumoAnormalidade(
			Imovel imovel, LigacaoTipo ligacaoTipo, int anoMesReferencia,
			Integer idConsumoAnormalidade) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select ch.id, ca.id, lt.id,  ch.referenciaFaturamento  "
					+ "from ConsumoHistorico ch "
					+ "inner join ch.consumoAnormalidade ca "
					+ "inner join ch.ligacaoTipo lt "
					+ "inner join ch.imovel im "
					+ "where im.id = :id and lt.id = :ligacaoTipoId "
					+ "and ch.referenciaFaturamento = :anoMes "
					+ "and ca.id = :idConsumoAnormalidade";

			retorno = session.createQuery(consulta).setInteger("id",
					imovel.getId().intValue()).setInteger("ligacaoTipoId",
					ligacaoTipo.getId().intValue()).setInteger("anoMes",
					anoMesReferencia).setInteger("idConsumoAnormalidade",
					idConsumoAnormalidade).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Método que retorna os dados necessários para efetuar o rateio de um
	 * imovel condominio e seus imoveis vinculados
	 * 
	 * [UC0103] Efetuar Rateio de Consumo
	 * 
	 * @author Bruno Barros
	 * @date 29/12/2009
	 * 
	 * @param idImovel
	 *            id do imovel condominio
	 * @return Imovel Encapsulado dentro da colação os dados retornados
	 */
	public Collection pesquisarImovelCondominio(Integer idImovel)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select imovel.id, imovel.ligacaoAguaSituacao.id, " // 0,1
					+ "imovel.ligacaoEsgotoSituacao.id, " // 2
					+ "imovel.ligacaoAguaSituacao.indicadorFaturamentoSituacao, "// 3
					+ "imovel.ligacaoEsgotoSituacao.indicadorFaturamentoSituacao "// 4
					+ "from gcom.cadastro.imovel.Imovel imovel "
					// VERIFICANDO SE É UM IMÓVEL CONDOMÍNIO
					+ "WHERE imovel.id = :id";

			retorno = session.createQuery(consulta).setInteger("id", idImovel)
					.list();
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * Buscar Rota a partir da Matrícula de um Imóvel e anoMesFaturamentoGrupo.
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 03/02/2010
	 * 
	 * @param dados
	 * 
	 * @throws ControladorException
	 */
	public Rota buscarRotaPorMatriculaImovel(Integer imovel,
			Integer anoMesFaturamentoGrupo) throws ErroRepositorioException {
		Rota rota = null;
		Collection colecaoRota = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {

			consulta = " select rota "
					+ " from gcom.micromedicao.MovimentoRoteiroEmpresa mrem, Rota rota  "
					+ " where mrem.imovel.id = :idImovel "
					+ " and mrem.anoMesMovimento = :anoMesFaturamentoGrupo "
					+ " and rota.id = mrem.rota.id ";

			colecaoRota = session.createQuery(consulta).setInteger("idImovel",
					imovel).setInteger("anoMesFaturamentoGrupo",
					anoMesFaturamentoGrupo).list();

			if (colecaoRota != null && !colecaoRota.isEmpty()) {
				rota = (Rota) Util.retonarObjetoDeColecao(colecaoRota);
			}

			// erro no hibernate

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return rota;
	}

	/**
	 * Exclui Arquivos que estão na situação passada como parametro para serem
	 * gerando novamente.
	 * 
	 * @author Hugo Amorim
	 * @date 08/02/2010
	 * 
	 * @param anoMesReferencia
	 * @param idRota
	 * @param idGrupoFaturamento
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoRoteiroEmpresa pesquisaArquivoTextoParaLeituristaPorRota(
			Integer anoMesReferencia, Integer idRota, Integer idGrupoFaturamento)
			throws ErroRepositorioException {

		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// Retorno Consulta
		ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa = null;

		// Cria a variável que vai conter o hql
		String consulta;

		try {

			consulta = "from ArquivoTextoRoteiroEmpresa where "
					+ "anoMesReferencia = :anoMesReferencia "
					+ "and rota = :idRota "
					+ "and faturamentoGrupo = :idFaturamentoGrupo ";

			arquivoTextoRoteiroEmpresa = (ArquivoTextoRoteiroEmpresa) session
					.createQuery(consulta).setInteger("anoMesReferencia",
							anoMesReferencia).setInteger("idRota", idRota)
					.setInteger("idFaturamentoGrupo", idGrupoFaturamento)
					.setMaxResults(1).uniqueResult();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return arquivoTextoRoteiroEmpresa;
	}

	/**
	 * Exclui Arquivos que estão na situação passada como parametro para serem
	 * gerando novamente.
	 * 
	 * @author Hugo Amorim
	 * @date 08/02/2010
	 * 
	 * @param anoMesReferencia
	 * @param idRota
	 * @param idGrupoFaturamento
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoRoteiroEmpresa pesquisaArquivoTextoParaLeituristaPorRoteiroEmpresa(
			Integer anoMesReferencia, Integer idRoteiroEmpresa,
			Integer idGrupoFaturamento) throws ErroRepositorioException {

		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// Retorno Consulta
		ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa = null;

		// Cria a variável que vai conter o hql
		String consulta;

		try {

			consulta = "from ArquivoTextoRoteiroEmpresa where "
					+ "anoMesReferencia = :anoMesReferencia "
					+ "and rota = :idRota "
					+ "and faturamentoGrupo = :idFaturamentoGrupo ";

			arquivoTextoRoteiroEmpresa = (ArquivoTextoRoteiroEmpresa) session
					.createQuery(consulta).setInteger("anoMesReferencia",
							anoMesReferencia).setInteger("roteiroEmpresa",
							idRoteiroEmpresa).setInteger("idFaturamentoGrupo",
							idGrupoFaturamento).setMaxResults(1).uniqueResult();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return arquivoTextoRoteiroEmpresa;
	}

	/**
	 * Exclui Arquivos que estão na situação passada como parametro para serem
	 * gerando novamente.
	 * 
	 * @author Hugo Amorim
	 * @date 08/02/2010
	 * 
	 * @param anoMesReferencia
	 * @param idRota
	 * @param idGrupoFaturamento
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoRoteiroEmpresa pesquisaArquivoTextoParaLeiturista(
			Integer anoMesReferencia, Integer idGrupoFaturamento)
			throws ErroRepositorioException {

		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// Retorno Consulta
		ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa = null;

		// Cria a variável que vai conter o hql
		String consulta;

		try {

			consulta = "from ArquivoTextoRoteiroEmpresa where "
					+ "anoMesReferencia = :anoMesReferencia "
					+ "and faturamentoGrupo = :idFaturamentoGrupo ";

			arquivoTextoRoteiroEmpresa = (ArquivoTextoRoteiroEmpresa) session
					.createQuery(consulta).setInteger("anoMesReferencia",
							anoMesReferencia).setInteger("idFaturamentoGrupo",
							idGrupoFaturamento).setMaxResults(1).uniqueResult();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return arquivoTextoRoteiroEmpresa;
	}

	/**
	 * Pesquisar ultima anormalidade de leitura do imovel
	 * 
	 * @author Hugo Amorim
	 * @date 19/02/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public String pesquisarAnormalidadesImovel(Integer idImovel,
			String indicadorAguaEsgoto) throws ErroRepositorioException {

		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// Retorno Consulta
		String retorno = null;

		// Cria a variável que vai conter o hql
		String consulta;

		try {

			consulta = "SELECT ltan_dsleituraanormalidade as dsAnormalidade"
					+ " FROM micromedicao.medicao_historico mh"
					+ " INNER JOIN micromedicao.leitura_anormalidade la on la.ltan_id = mh.ltan_idleitanorminformada"
					+ " WHERE ltan_idleitanorminformada is not null";

			if (indicadorAguaEsgoto.equals(ConstantesSistema.CALCULAR_AGUA)) {
				consulta += " and lagu_id = :idImovel ";
			} else if (indicadorAguaEsgoto
					.equals(ConstantesSistema.CALCULAR_ESGOTO)) {
				consulta += " and imov_id = :idImovel ";
			}

			consulta = consulta + " order by mdhi_amleitura desc";

			retorno = (String) session.createSQLQuery(consulta).addScalar(
					"dsAnormalidade", Hibernate.STRING).setInteger("idImovel",
					idImovel).setMaxResults(1).uniqueResult();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Método que retorna as datas de leituas anteriores e atuais.
	 * 
	 * @author Tiago Moreno
	 * @date 25/02/2010
	 * 
	 * 
	 * @param idImovel
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterLeituraAnteriorEAtual(Integer idImovel, Integer anoMes)
			throws ErroRepositorioException {
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select md.dataLeituraAnteriorFaturamento, "// 0
					+ "md.dataLeituraAtualFaturamento " // 1
					+ "from MedicaoHistorico md	"
					+ "where md.ligacaoAgua.id = :idImovel and "
					+ "md.medicaoTipo.id = :idMedicaoTipo and "
					+ "md.anoMesReferencia = :anoMes ";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idImovel", idImovel.intValue()).setInteger(
					"idMedicaoTipo", MedicaoTipo.LIGACAO_AGUA.intValue())
					.setInteger("anoMes", anoMes.intValue()).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Verificar quais rotas ainda faltam ser transmitidas pela impressao
	 * simultanea
	 * 
	 * @author Rafael Pinto
	 * @date 01/03/2010
	 * 
	 * @param anoMesReferencia
	 * @param idGrupoFaturamento
	 * @throws ErroRepositorioException
	 */
	public Collection<Rota> pesquisaRotasNaoTransmitidas(
			Integer anoMesReferencia, Integer idGrupoFaturamento)
			throws ErroRepositorioException {

		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// Retorno Consulta
		Collection<Rota> ColecaoRota = null;

		// Cria a variável que vai conter o hql
		String consulta;

		try {

			consulta = "SELECT distinct rota "
					+ "FROM ArquivoTextoRoteiroEmpresa arquivo "
					+ "INNER JOIN arquivo.rota rota "
					+ "WHERE arquivo.anoMesReferencia = :anoMesReferencia "
					+ "AND arquivo.faturamentoGrupo = :idFaturamentoGrupo "
					+ "AND arquivo.situacaoTransmissaoLeitura.id in (:situacaoDisponivel,:situacaoLiberado,:situacaoEmCampo) "
					+ "AND rota.leituraTipo.id = :leituraTipo";

			ColecaoRota = (Collection<Rota>) session
					.createQuery(consulta)
					.setInteger("anoMesReferencia", anoMesReferencia)
					.setInteger("idFaturamentoGrupo", idGrupoFaturamento)
					.setInteger("situacaoDisponivel",
							SituacaoTransmissaoLeitura.DISPONIVEL)
					.setInteger("situacaoLiberado",
							SituacaoTransmissaoLeitura.LIBERADO)
					.setInteger(
							"situacaoEmCampo",
							SituacaoTransmissaoLeitura.EM_CAMPO)
					.setInteger("leituraTipo",
							LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA).list();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return ColecaoRota;
	}

	/**
	 * 
	 * Pesquisa numero de hidrometro da ligacao de agua do imovel
	 * 
	 * @author Hugo Amorim
	 * @date 04/03/2010
	 * @param Id
	 *            Imovel
	 * @return Numero do Hidrometro
	 * @throws ErroRepositorioException
	 */
	public String pesquisarNumeroHidrometro(Integer idImovel)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String retorno = null;

		String consulta = null;

		try {
			consulta = "SELECT hidr.numero "
					+ "From HidrometroInstalacaoHistorico hidi "
					+ "LEFT JOIN hidi.hidrometro hidr "
					+ "LEFT JOIN hidi.ligacaoAgua lagu "
					+ "LEFT JOIN hidi.hidrometroLocalInstalacao hli "
					+ "WHERE lagu.id = :idImovel "
					+ "AND hidi.dataRetirada is null ";

			retorno = (String) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0820] Atualizar Faturamento do Movimento Celular
     * [SB002] Incluir Medicao
     * 
     * Método criado para atualizar apenas os campos necessários para
     * medição histórico.
     * 	 
     * @author Bruno Barros
     * @date 31/03/2010
     * @param medicaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarMedicaoHistoricoProcessoMOBILE( MedicaoHistorico medicaoHistorico ) 
		throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;

		try {
			
			Connection jdbcCon = session.connection();
			
			String update = 		
				"update " +
				"  micromedicao.medicao_historico as mh " +
				"set " +
				// 1
				"  mh.imov_id = :imov_id, " +
				// 2
				"  mh.lagu_id = :ligacaoAgua, " +
				// 3
				"  mh.medt_id = :medicaoTipo, " +
				// 4
				"  mh.mdhi_amleitura = :anoMesReferencia, " +
				// 5
				"  mh.mdhi_nnxconsecanormalidade = :numeroVezesConsecutivasOcorrenciaAnormalidade, " +
				// 6
				"  mh.mdhi_dtleituraatualinformada = :dataLeituraAtualInformada, " +
				// 7
				"  mh.mdhi_dtleituracampo = :dataLeituraCampo, " +
				// 8
				"  mh.mdhi_dtleituraatualfaturamento = :dataLeituraAtualFaturamento, " +
				// 9
				"  mh.mdhi_dtleituraatualinformada = :dataLeituraAtualInformada, " +
				// 10
				"  mh.mdhi_nnleituracampo = :leituraCampo, " +
				// 11
				"  mh.mdhi_nnconsumomedidomes = :numeroConsumoMes, " +
				// 12
				"  mh.mdhi_dtleitprocessamentomov = :leituraProcessamentoMovimento, " +
				// 13
				"  mh.ltan_idleitanorminformada  = :leituraAnormalidadeInformada, " +
				// 14
				"  mh.ltan_idleitanormfatmt = :leituraAnormalidadeFaturamento, " +
				// 15
				"  mh.ltst_idleiturasituacaoatual = :leituraSituacaoAtual, " +
				// 16
				"  mh.hidi_id = :hidrometroInstalacaoHistorico, " +
				// 17
				"  mh.mdhi_nnconsumomediohidrometro = :consumoMedioHidrometro, " +
				// 18
				"  mh.mdhi_tmultimaalteracao = :ultimaAlteracao, " +
				// 19
				"  mh.mdhi_icanalisado = :indicadorAnalisado " +
				"where " +
				// 20
				"  mh.medh_id = :id ";

			
			st = 
				jdbcCon.prepareStatement(update);
			
			st.setInt( 1, medicaoHistorico.getImovel().getId() );
			st.setInt( 2, medicaoHistorico.getLigacaoAgua().getId() );
			st.setInt( 3, medicaoHistorico.getMedicaoTipo().getId() );
			st.setInt( 4, medicaoHistorico.getAnoMesReferencia() );
			st.setShort( 5, medicaoHistorico.getNumeroVezesConsecutivasOcorrenciaAnormalidade() );
			st.setDate( 6, new java.sql.Date( medicaoHistorico.getDataLeituraAtualInformada().getTime() ) );
			st.setDate( 7, new java.sql.Date( medicaoHistorico.getDataLeituraCampo().getTime() ) );
			st.setDate( 8, new java.sql.Date( medicaoHistorico.getDataLeituraAtualFaturamento().getTime() ) );
			st.setDate( 9, new java.sql.Date( medicaoHistorico.getDataLeituraAtualInformada().getTime() ) );
			st.setInt(10, medicaoHistorico.getLeituraCampo() );
			st.setInt(11, medicaoHistorico.getNumeroConsumoMes() );
			st.setDate( 12, new java.sql.Date( new Date().getTime() ) );
			st.setInt(13, medicaoHistorico.getLeituraAnormalidadeInformada().getId() );
			st.setInt(14, medicaoHistorico.getLeituraAnormalidadeFaturamento().getId() );
			st.setInt(15, medicaoHistorico.getLeituraSituacaoAtual().getId() );
			st.setInt(16, medicaoHistorico.getHidrometroInstalacaoHistorico().getId() );
			st.setInt(17, medicaoHistorico.getConsumoMedioHidrometro() );
			st.setDate(18, new java.sql.Date( new Date().getTime() ) );
			st.setShort(19, medicaoHistorico.getIndicadorAnalisado() );
			st.setInt(20, medicaoHistorico.getId() );

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1000] Informar Medidor de Energia por Rota.
	 * 
	 * Obtém a quantidade de imoveis de acordo com o filtro.
	 * 
	 * @author Hugo Leonardo
	 * @date 09/03/2010
	 * 
	 * @param FiltrarRelatorioColetaMedidorEnergiaHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroColetaMedidorEnergia(
			String idLocalidade, String idSetorComercial, String rota,
			String tipo) throws ErroRepositorioException {

		int retorno = 0;

		Session session = HibernateUtil.getSession();

		Query query = null;

		Map parameters = new HashMap();

		String consulta = "";

		try {

			consulta += " select count (roteiro.imovel.id) as cont "
					+ " from MovimentoRoteiroEmpresa roteiro "
					+ " inner join roteiro.imovel imovel "
					+ " inner join imovel.quadra quadra "
					+ " inner join imovel.setorComercial setor "
					+ " inner join quadra.rota rota "
					+ " inner join rota.faturamentoGrupo faturamentoGrupo ";

			if (idLocalidade != null && !idLocalidade.equals("")) {

				consulta += " where imovel.localidade = :idLocalidade "
						+ " and roteiro.anoMesMovimento = faturamentoGrupo.anoMesReferencia "
						+ " and imovel.ligacaoAguaSituacao in (3,4,5,6) "
						+ " and imovel.ligacaoEsgotoSituacao in (3,5,6) ";

				parameters.put("idLocalidade", new Integer(idLocalidade));
			}

			if (idSetorComercial != null && !idSetorComercial.equals("")) {

				consulta += " and setor.codigo = :idSetorComercial ";

				parameters.put("idSetorComercial",
						new Integer(idSetorComercial));
			}

			if (rota != null && !rota.equals("")) {

				consulta += " and rota.codigo = :rota ";

				parameters.put("rota", new Integer(rota));
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

			retorno = (Integer) query.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<ColetaMedidorEnergiaHelper> pesquisarColetaMedidorEnergia(
			String idLocalidade, String idSetorComercial, String rota,
			String tipo) throws ErroRepositorioException {

		Collection retorno = null;
		String consulta = "";
		Session session = HibernateUtil.getSession();

		Query query = null;

		Map parameters = new HashMap();

		try {

			consulta += " select distinct empresa.descricao, " // 0
					+ " faturamentoGrupo.id, " // 1
					+ " imovel.localidade.id, " // 2
					+ " setor.codigo, " // 3
					+ " rota.codigo, " // 4
					+ " roteiro.numeroSequencialRota, " // 5
					+ " imovel.numeroMedidorEnergia, " // 6
					+ " roteiro.imovel.id, " // 7
					
					+ " localidade.gerenciaRegional, " //8
					+ " imovel.localidade, " //9
					+ " setor.codigo, " //10
					+ " quadra.numeroQuadra, " //11
					+ " roteiro.numeroLoteImovel, " //12
					+ " roteiro.numeroSubloteImovel " //13
					
					+ " from MovimentoRoteiroEmpresa roteiro "
					+ " inner join roteiro.imovel imovel "
					+ " inner join imovel.quadra quadra "
					+ " inner join imovel.setorComercial setor "
					+ " inner join imovel.localidade localidade "
					+ " inner join quadra.rota rota "
					+ " inner join rota.faturamentoGrupo faturamentoGrupo "
					+ " inner join rota.empresa empresa ";

			if (idLocalidade != null && !idLocalidade.equals("")) {

				consulta += " where imovel.localidade = :idLocalidade "
						+ " and roteiro.anoMesMovimento = faturamentoGrupo.anoMesReferencia "
						+ " and imovel.ligacaoAguaSituacao in (:last) "
						+ " and imovel.ligacaoEsgotoSituacao in (:lest) ";

				parameters.put("idLocalidade", new Integer(idLocalidade));
			}

			if (idSetorComercial != null && !idSetorComercial.equals("")) {

				consulta += " and setor.codigo = :idSetorComercial ";

				parameters.put("idSetorComercial",
						new Integer(idSetorComercial));
			}

			if (rota != null && !rota.equals("")) {

				consulta += " and rota.codigo = :rota ";

				parameters.put("rota", new Integer(rota));
			}

			consulta += " order by localidade.gerenciaRegional, imovel.localidade, setor.codigo, quadra.numeroQuadra, " 
				+ " roteiro.numeroLoteImovel, roteiro.numeroSubloteImovel, roteiro.imovel.id,"
				+ " empresa.descricao, faturamentoGrupo.id, imovel.localidade.id, " 
				+ " setor.codigo, rota.codigo, roteiro.numeroSequencialRota, imovel.numeroMedidorEnergia ";
			
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
			
			Collection colecaoLast = new ArrayList();
			colecaoLast.add(LigacaoAguaSituacao.LIGADO);
			colecaoLast.add(LigacaoAguaSituacao.LIGADO_EM_ANALISE);
			colecaoLast.add(LigacaoAguaSituacao.CORTADO);
			colecaoLast.add(LigacaoAguaSituacao.SUPRIMIDO);
			
			Collection colecaoLest = new ArrayList();
			colecaoLest.add(LigacaoEsgotoSituacao.POTENCIAL);
			colecaoLest.add(LigacaoEsgotoSituacao.LIGADO);
			colecaoLest.add(LigacaoEsgotoSituacao.LIG_FORA_DE_USO);
			colecaoLest.add(LigacaoEsgotoSituacao.TAMPONADO);
			
			retorno = query.setParameterList("last", colecaoLast)
					.setParameterList("lest", colecaoLest)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
    /**
	 * [UC0997] Gerar Resumo de Ligações por Capacidade de Hidrômetro.
	 * 
	 * @author Hugo Leonardo
	 * @date 30/03/2010
	 * 
	 * @param RelatorioResumoLigacoesCapacidadeHidrometroHelper, tipoTotalizacao
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioResumoLigacoesCapacidadeHidrometro(
			RelatorioResumoLigacoesCapacidadeHidrometroHelper helper, String tipoTotalizacao) throws ErroRepositorioException {

		Collection retorno = null;
		String consulta = "";
		Session session = HibernateUtil.getSession();
		
		try {
			
			consulta =  " SELECT  ";
				
			if(tipoTotalizacao.equals("LOCALIDADE")){
				 consulta += " A.idGerRegional as idGerRegional1, " //0
						  +	 " A.nomeGerRegional as nomeGerRegional1, " //1
						  +	 " A.idUnidadeNeg as idUnidadeNeg1, " //2
						  +	 " A.nomeUnidadeNeg as nomeUnidadeNeg1, " //3
						  +	 " A.idLocalidade as idLocalidade1, " //4
						  +	 " A.nomeLocalidade as nomeLocalidade1, "; //5
			}else if(tipoTotalizacao.equals("UNIDADE")){
				consulta += " A.idGerRegional as idGerRegional1, " //0
						 +  " A.nomeGerRegional as nomeGerRegional1, " //1
						 +  " A.idUnidadeNeg as idUnidadeNeg1, " //2
						 +	" A.nomeUnidadeNeg as nomeUnidadeNeg1, "; //3
				
			}else if(tipoTotalizacao.equals("GERENCIA")){
				consulta += " A.idGerRegional as idGerRegional1, " //0
					 	 +  " A.nomeGerRegional as nomeGerRegional1, "; //1	  
			}
			
			consulta +=	" A.capacidadeHidr as capacidadeHidr1, " 
				 +	" A.diametroHidr as diametroHidr1, " 
				 +	" SUM(A.qtdLigacoes) as qtdLigacoesHidr, " 
				 +	" SUM(A.qtdEconomias) as qtdEconomiasHidr, " 
				 +	" SUM(A.volAguaMedido) as qtdVolAguaMedidoHidr, " 
				 +	" SUM(A.valorFaturadoAE) as valorFaturadoAEMedidoHidr, "
				 +  " A.ordem "
				 +	" FROM "
				 +  "  "
				 +	" (SELECT ";
					 
			if(tipoTotalizacao.equals("LOCALIDADE")){
				 consulta += " ger.greg_id as idGerRegional, "
						  +  " ger.greg_nmregional as nomeGerRegional, "
						  +	 " uneg.uneg_id as idUnidadeNeg, "
						  +	 " uneg.uneg_nmunidadenegocio as nomeUnidadeNeg, "
						  +	 " loca.loca_id as idLocalidade, "
						  +	 " loca.loca_nmlocalidade as nomeLocalidade, ";
			}else if(tipoTotalizacao.equals("UNIDADE")){
				 consulta += " ger.greg_id as idGerRegional, "
						  +	 " ger.greg_nmregional as nomeGerRegional, "
						  +	 " uneg.uneg_id as idUnidadeNeg, "
						  +	 " uneg.uneg_nmunidadenegocio as nomeUnidadeNeg, ";
			}else if(tipoTotalizacao.equals("GERENCIA")){
				 consulta += " ger.greg_id as idGerRegional, "
					 	  +	 " ger.greg_nmregional as nomeGerRegional, ";
			}
			consulta+=  " hidcap.hicp_dshidrometrocapacidade as capacidadeHidr, "
					+	" hidi.hidm_dshidrometrodiametro as diametroHidr, "
					+	" 1 as qtdLigacoes, "
					+	" imo.imov_qteconomia as qtdEconomias, "
					+	" conta.cnta_nnconsumoagua as volAguaMedido, "
					+	" (coalesce(conta.cnta_vlagua,0) + coalesce(conta.cnta_vlesgoto,0)) as valorFaturadoAE, "
					+   " hidcap.hicp_nnordem as ordem "
					+	" from    micromedicao.hidrometro_inst_hist hihi "
					+	" inner join cadastro.imovel imo on (imo.imov_id = hihi.lagu_id ) "
					+	" inner join cadastro.localidade loca on (loca.loca_id = imo.loca_id ) "
					+	" inner join cadastro.unidade_negocio uneg on (uneg.uneg_id = loca.uneg_id) "
					+	" inner join cadastro.gerencia_regional ger on (ger.greg_id = loca.greg_id) "
					+	" inner join micromedicao.hidrometro hidr on( hidr.hidr_id = hihi.hidr_id) "
					+	" inner join faturamento.conta conta on (hihi.lagu_id = conta.imov_id and conta.cnta_amreferenciaconta = :anoMesFaturamento ) "
					+	" inner join micromedicao.hidrometro_capacidade hidcap on (hidcap.hicp_id = hidr.hicp_id) "
					+	" inner join micromedicao.hidrometro_diametro hidi on (hidi.hidm_id = hidr.hidm_id) "
					+	" where (hihi.hidi_dtretiradahidrometro is null or hihi.hidi_dtretiradahidrometro > :data ) "
					+	" ";
							
			if(helper.getIdGerenciaRegional() !=null 
					&& !helper.getIdGerenciaRegional().equals("")){
				
				consulta += " and ger.greg_id = " + helper.getIdGerenciaRegional() + " "; 
			}
			
			if(helper.getIdUnidadeNegocio() !=null 
					&& !helper.getIdUnidadeNegocio().equals("")){
				
				consulta += " and uneg.uneg_id = " + helper.getIdUnidadeNegocio() + " "; 
			}
			
			if(helper.getIdLocalidade() !=null 
					&& !helper.getIdLocalidade().equals("")){
				
				consulta += " and loca.loca_id = " + helper.getIdLocalidade() + " "; 
			}

			consulta +=	" ) A ";
			
			// AGRUPAMENTO POR TIPO DE TOTALIZAÇÃO
			  String orderBy = " order by";
			  String groupBy = " group by";
			  
			  if(tipoTotalizacao.equals("LOCALIDADE")){
				  groupBy+=" A.idGerRegional,A.nomeGerRegional,A.idUnidadeNeg,A.nomeUnidadeNeg,A.idLocalidade,A.nomeLocalidade,A.capacidadeHidr,A.diametroHidr,A.ordem ";	
				  orderBy+=" 1,3,5,13,8 ";	
			  }else if(tipoTotalizacao.equals("UNIDADE")){
				  groupBy+=" A.idGerRegional,A.nomeGerRegional,A.idUnidadeNeg,A.nomeUnidadeNeg,A.capacidadeHidr,A.diametroHidr,A.ordem ";	
				  orderBy+=" 1,3,11,6 ";
			  }else if(tipoTotalizacao.equals("GERENCIA")){
				  groupBy+=" A.idGerRegional,A.nomeGerRegional,A.capacidadeHidr,A.diametroHidr,A.ordem ";	
				  orderBy+=" 1,9,4 ";
			  }else if(tipoTotalizacao.equals("ESTADO")){
				  groupBy+=" A.capacidadeHidr,A.diametroHidr,A.ordem ";	
				  orderBy+=" 7,2 ";
			  }
			  // FIM DO AGRUPAMENTO
			  
			  consulta = consulta+groupBy+orderBy; 
			  
			  if(tipoTotalizacao.equals("LOCALIDADE")){
				  retorno = (Collection) session.createSQLQuery(consulta)
					.addScalar("idGerRegional1", Hibernate.INTEGER)
					.addScalar("nomeGerRegional1", Hibernate.STRING)
					.addScalar("idUnidadeNeg1", Hibernate.INTEGER)
					.addScalar("nomeUnidadeNeg1", Hibernate.STRING)
					.addScalar("idLocalidade1", Hibernate.INTEGER)
					.addScalar("nomeLocalidade1", Hibernate.STRING)
					.addScalar("capacidadeHidr1", Hibernate.STRING) 
					.addScalar("diametroHidr1", Hibernate.STRING) 
					.addScalar("qtdLigacoesHidr",Hibernate.INTEGER) 
					.addScalar("qtdEconomiasHidr", Hibernate.INTEGER) 
					.addScalar("qtdVolAguaMedidoHidr", Hibernate.INTEGER) 
					.addScalar("valorFaturadoAEMedidoHidr",Hibernate.BIG_DECIMAL)
					.setDate("data", helper.getMesAnoReferencia())
					.setInteger("anoMesFaturamento",helper.getAnoMesReferenciaAnterior()).list();  
			  }else if(tipoTotalizacao.equals("UNIDADE")){
				  retorno = (Collection) session.createSQLQuery(consulta)
					.addScalar("idGerRegional1", Hibernate.INTEGER)
					.addScalar("nomeGerRegional1", Hibernate.STRING)
					.addScalar("idUnidadeNeg1", Hibernate.INTEGER)
					.addScalar("nomeUnidadeNeg1", Hibernate.STRING)
					.addScalar("capacidadeHidr1", Hibernate.STRING) 
					.addScalar("diametroHidr1", Hibernate.STRING) 
					.addScalar("qtdLigacoesHidr",Hibernate.INTEGER) 
					.addScalar("qtdEconomiasHidr", Hibernate.INTEGER) 
					.addScalar("qtdVolAguaMedidoHidr", Hibernate.INTEGER) 
					.addScalar("valorFaturadoAEMedidoHidr",Hibernate.BIG_DECIMAL)
					.setDate("data", helper.getMesAnoReferencia())
					.setInteger("anoMesFaturamento",helper.getAnoMesReferenciaAnterior()).list();  
			  }else if(tipoTotalizacao.equals("GERENCIA")){
				  retorno = (Collection) session.createSQLQuery(consulta)
					.addScalar("idGerRegional1", Hibernate.INTEGER)
					.addScalar("nomeGerRegional1", Hibernate.STRING)
					.addScalar("capacidadeHidr1", Hibernate.STRING) 
					.addScalar("diametroHidr1", Hibernate.STRING) 
					.addScalar("qtdLigacoesHidr",Hibernate.INTEGER) 
					.addScalar("qtdEconomiasHidr", Hibernate.INTEGER) 
					.addScalar("qtdVolAguaMedidoHidr", Hibernate.INTEGER) 
					.addScalar("valorFaturadoAEMedidoHidr",Hibernate.BIG_DECIMAL)
					.setDate("data", helper.getMesAnoReferencia())
					.setInteger("anoMesFaturamento",helper.getAnoMesReferenciaAnterior()).list();
			  }else if(tipoTotalizacao.equals("ESTADO")){
				  retorno = (Collection) session.createSQLQuery(consulta)
					.addScalar("capacidadeHidr1", Hibernate.STRING) 
					.addScalar("diametroHidr1", Hibernate.STRING) 
					.addScalar("qtdLigacoesHidr",Hibernate.INTEGER) 
					.addScalar("qtdEconomiasHidr", Hibernate.INTEGER) 
					.addScalar("qtdVolAguaMedidoHidr", Hibernate.INTEGER) 
					.addScalar("valorFaturadoAEMedidoHidr",Hibernate.BIG_DECIMAL)
					.setDate("data", helper.getMesAnoReferencia())
					.setInteger("anoMesFaturamento",helper.getAnoMesReferenciaAnterior()).list();  
			  }
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	 /**
	 * [UC0997] Gerar Resumo de Ligações por Capacidade de Hidrômetro.
	 * 
	 * @author Hugo Leonardo
	 * @date 30/03/2010
	 * 
	 * @param RelatorioResumoLigacoesCapacidadeHidrometroHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer countRelatorioResumoLigacoesCapacidadeHidrometro(RelatorioResumoLigacoesCapacidadeHidrometroHelper helper) throws ErroRepositorioException {

		Integer retorno = 0;
		String consulta = "";
		Session session = HibernateUtil.getSession();
		
		try {
			
			consulta =  " SELECT ";

			consulta += " count (hidr.hidr_id) as contador"
					 +	" from    micromedicao.hidrometro_inst_hist hihi "
					 +	" inner join cadastro.imovel imo on (imo.imov_id = hihi.lagu_id ) "
					 +	" inner join cadastro.localidade loca on (loca.loca_id = imo.loca_id ) "
					 +	" inner join cadastro.unidade_negocio uneg on (uneg.uneg_id = loca.uneg_id) "
					 +	" inner join cadastro.gerencia_regional ger on (ger.greg_id = loca.greg_id) "
					 +	" inner join micromedicao.hidrometro hidr on( hidr.hidr_id = hihi.hidr_id) "
					 +	" inner join faturamento.conta conta on (hihi.lagu_id = conta.imov_id and conta.cnta_amreferenciaconta = :anoMesFaturamento ) "
					 +	" inner join micromedicao.hidrometro_capacidade hidcap on (hidcap.hicp_id = hidr.hicp_id) "
					 +	" inner join micromedicao.hidrometro_diametro hidi on (hidi.hidm_id = hidr.hidm_id) "
					 +	" where (hihi.hidi_dtretiradahidrometro is null or hihi.hidi_dtretiradahidrometro > :data ) "
					 +	" ";
							
			if(helper.getIdGerenciaRegional() !=null 
					&& !helper.getIdGerenciaRegional().equals("")){
				
				consulta += " and ger.greg_id = " + helper.getIdGerenciaRegional() + " "; 
			}
			
			if(helper.getIdUnidadeNegocio() !=null 
					&& !helper.getIdUnidadeNegocio().equals("")){
				
				consulta += " and uneg.uneg_id = " + helper.getIdUnidadeNegocio() + " "; 
			}
			
			if(helper.getIdLocalidade() !=null 
					&& !helper.getIdLocalidade().equals("")){
				
				consulta += " and loca.loca_id = " + helper.getIdLocalidade() + " "; 
			}

			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("contador", Hibernate.INTEGER)
				.setDate("data", helper.getMesAnoReferencia())
				.setInteger("anoMesFaturamento",helper.getAnoMesReferenciaAnterior())
				.setMaxResults(1).uniqueResult();
					
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * 
	 * [UC0091] Alterar Dados para Faturamento
	 * 
	 * 	[FS0015]  Verificar Imóvel Impressão Simultânea
	 * 
	 * @author Hugo Amorim
	 * @date 08/04/2010
	 */
	public boolean verificarExistenciaArquivoDeImpressao(Integer idImovel,
			Integer tipoMedicao)throws ErroRepositorioException{
		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// Retorno Consulta
		boolean retorno = false;

		// Cria a variável que vai conter o hql
		String consulta;

		try {
						
			
			consulta =
					  " SELECT mre.id "
					+ " FROM MovimentoRoteiroEmpresa mre "
					+ " INNER JOIN mre.imovel i" 
					+ " INNER JOIN i.quadra q" 
					+ " INNER JOIN q.rota r" 
					+ " INNER JOIN r.faturamentoGrupo fg"
					+ " WHERE fg.anoMesReferencia = mre.anoMesMovimento" 
					+ " AND mre.medicaoTipo = :medicaoTipo and mre.imovel = :idImovel" ;				
					
					
			Integer idRetorno 
				=  (Integer) session
				.createQuery(consulta)
				.setInteger("medicaoTipo",tipoMedicao)
				.setInteger("idImovel",idImovel)
				.setMaxResults(1).uniqueResult();
			
			if(idRetorno!=null){
				retorno = true;
			}

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Retorna uma coleção com os dados das medicoes e consumos da ligação de Esgoto do imóvel
	 * para impressão do relatorio na funionalidade Consultar Imóvel
	 * 
	 * @date 07/05/2010
	 */
	public Collection carregarDadosMedicaoConsumoHistoricoLigacaoEsgoto(Integer idImovel)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;
		try {
			consulta = "SELECT medicaoHistorico.mdhi_amleitura as mesAno," //0
					+ " medicaoHistorico.mdhi_dtleituraatualinformada as dataLeituraInformada," //1
					+ " medicaoHistorico.mdhi_nnleituraatualinformada as numeroLeituraInformada," //2
					+ " medicaoHistorico.mdhi_dtleituraatualfaturamento as dataLeituraFaturada," //3
					+ " medicaoHistorico.mdhi_nnleituraatualfaturamento as numeroLeituraFaturada," //4
					+ " leituraAnormalidadeInformada.ltan_dsleituraanormalidade as descricaoanormalidadeleiturain,"//5
					+ " leituraAnormalidadeFaturamento.ltan_dsleituraanormalidade as descricaoanormalidadeleiturafa,"//6
					+ " leituraSituacao.ltst_dsleiturasituacao as leituraSituacao," //7
					+ " medicaoHistorico.mdhi_nnconsumomediohidrometro as consumoMedioHidrometro,"//8
					+ " leituraAnormalidadeInformada.ltan_id as idAnormalidadeLeituraInformada,"//9
					+ " leituraAnormalidadeFaturamento.ltan_id as idAnormalidadeLeituraFaturada,"//10
					+ " leituraAnormalidadeFaturamento.ltan_dsleituraanormalidade as descricaoanormalidadeleiturafa,"//11
					+ " consumoHistorico.cshi_nnconsumocalculomedia as consumoCalculoMedia,"//12
					+ " consumoAnormalidade.csan_dsabrvconsanormalidade as consumoAnormalidadeAbreviada"//13
					
					+ " FROM micromedicao.medicao_historico medicaoHistorico "
					+ " left outer join micromedicao.consumo_historico consumoHistorico on consumoHistorico.imov_id = medicaoHistorico.imov_id and consumoHistorico.cshi_amfaturamento = medicaoHistorico.mdhi_amleitura and lgti_id = " + ConstantesSistema.NAO
					+ " left outer join micromedicao.leitura_situacao leituraSituacao on medicaoHistorico.ltst_idleiturasituacaoatual = leituraSituacao.ltst_id "
					+ "	left outer join micromedicao.leitura_anormalidade leituraAnormalidadeInformada on medicaohistorico.ltan_idleitanorminformada=leituraAnormalidadeInformada.ltan_id "
					+ "	left outer join micromedicao.leitura_anormalidade leituraAnormalidadeFaturamento on medicaohistorico.ltan_idleitanormfatmt=leituraAnormalidadeFaturamento.ltan_id "
					+ " left outer join micromedicao.consumo_anormalidade consumoAnormalidade on consumoHistorico.csan_id = consumoAnormalidade.csan_id "
					
					+ " WHERE medicaoHistorico.imov_id = " + idImovel
					+ " order by medicaoHistorico.mdhi_amleitura desc";
			

			retorno = session.createSQLQuery(consulta)
				.addScalar("mesAno", Hibernate.INTEGER)
				.addScalar("dataLeituraInformada", Hibernate.DATE)
				.addScalar("numeroLeituraInformada",Hibernate.INTEGER)
				.addScalar("dataLeituraFaturada", Hibernate.DATE)
				.addScalar("numeroLeituraFaturada", Hibernate.INTEGER)
				.addScalar("descricaoanormalidadeleiturain", Hibernate.STRING)
				.addScalar("descricaoanormalidadeleiturafa", Hibernate.STRING)
				.addScalar("leituraSituacao", Hibernate.STRING)
				.addScalar("consumoMedioHidrometro", Hibernate.INTEGER)
				.addScalar("idAnormalidadeLeituraInformada", Hibernate.INTEGER)
				.addScalar("idAnormalidadeLeituraFaturada", Hibernate.INTEGER)
				.addScalar("descricaoanormalidadeleiturafa", Hibernate.STRING)
				.addScalar("consumoCalculoMedia", Hibernate.INTEGER)
				.addScalar("consumoAnormalidadeAbreviada", Hibernate.STRING)
				.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}



	
	/**
	 * @author Arthur Carvalho
	 * @date 25/05/2010
	 */
	public Integer pesquisarMedicaoTipo(Integer idImovel, Integer anoMesReferencia)
		throws ErroRepositorioException{
		
		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		Integer retorno = null;
		
		// Cria a variável que vai conter o hql
		String consulta;

	try {
		
		
		consulta =" select medt.id from MovimentoRoteiroEmpresa mre"
				+ " inner join fetch mre.medicaoTipo medt " 
				+ " where mre.anoMesMovimento = :anoMesReferencia" 
				+ " and mre.imovel = :idImovel " ;				
				
		retorno =  ( Integer ) session
				.createQuery( consulta )
				.setInteger( "anoMesReferencia", anoMesReferencia )
				.setInteger( "idImovel", idImovel )
				.setMaxResults(1).uniqueResult();

		// Erro no hibernate
	} catch (HibernateException e) {
		// Levanta a exceção para a próxima camada
		throw new ErroRepositorioException(e, "Erro no Hibernate");
	} finally {
		// Fecha a sessão com o hibernate
		HibernateUtil.closeSession(session);
	}

	return retorno;
}
	
	
	/**
	 * [UC1022] Relatório de Notificação de Débitos para Impressão Simultânea	 
	 * 
	 * @author Daniel Alves
	 * @date 19/05/2010
	 */
	public Collection pesquisarNotificacaoDebitosImpressaoSimultanea(
			RelatorioNotificacaoDebitosImpressaoSimultaneaHelper filtro
			)throws ErroRepositorioException{
		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		Collection retorno = null;
		
		// Cria a variável que vai conter o hql
		String consulta;

		try {
									
			consulta = "SELECT cgcm.anoMesReferencia as referencia, " +
			                   "empresa.descricao as empresa, " +			                   
					           "cgcm.cobrancaGrupo.id as grupo_cobranca, " +					           
					           "loca.descricao as localidade, " +
					           "loca.id as localidade_id, " +
					           "setor.codigo as setor_comercial, " +
					           "rt.codigo as rota, " +
					           "count(cd.imovel.id) as quantidade " +
					      "FROM CobrancaDocumento cd " +
					     "INNER JOIN cd.cobrancaAcaoAtividadeCronograma caac " +
					     "INNER JOIN caac.cobrancaAcaoCronograma ccc " +
					     "INNER JOIN ccc.cobrancaGrupoCronogramaMes cgcm " +
					     "INNER JOIN cd.imovel im " +
					     "INNER JOIN im.localidade loca " +
					     "INNER JOIN im.setorComercial setor " +
					     "INNER JOIN im.quadra qdr " +
					     "INNER JOIN qdr.rota rt " +
					     "INNER JOIN rt.empresa empresa " +
					     
					     "WHERE caac.cobrancaAtividade.id = 2 " +
					     "AND caac.quantidadeDocumentos > 0 " +
					     "AND rt.leituraTipo.id = 3 " +
					     "AND ccc.cobrancaAcao.id = 1 " +
					     
					     "AND cgcm.cobrancaGrupo.id = :grupo " +
			             "AND rt.empresa.id = :empresa " +
			             "AND cgcm.anoMesReferencia = :referencia ";


			             if(filtro.getCabecalhoTipo() >= 1){
			            	 consulta = consulta +
				             "AND im.localidade.id = " + filtro.getLocalidade() +" ";
			            	 
			            	 if(filtro.getCabecalhoTipo() >= 2){
				            	 consulta = consulta +
					             "AND setor.codigo = " + filtro.getSetorComercial() + " ";
				            	
				            	 if(filtro.getCabecalhoTipo() == 3){
					            	 consulta = consulta +
						             "AND rt.codigo = " + filtro.getRota() + " ";			            	 
					             }
				             }
			             }
			             
			           consulta = consulta +
                       " GROUP BY  cgcm.anoMesReferencia,  empresa.descricao,  cgcm.cobrancaGrupo.id,  loca.descricao,  loca.id,  setor.codigo,  rt.codigo " +
			           "ORDER BY 5, 6, 7, 8";
									
			retorno	=  (Collection) session
				.createQuery(consulta)
				.setString("referencia",filtro.getAnoMesReferencia())
				.setString("empresa",filtro.getEmpresa())
				.setString("grupo",filtro.getGrupo())				
				.list();


			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
		
	
	/**
	 * @author Arthur Carvalho
	 * @date 25/05/2010
	 */
	public HidrometroInstalacaoHistorico verificaExistenciaDeHidrometroInstalado(Integer idImovel, MedicaoTipo medicaoTipo) throws ErroRepositorioException{
		
		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;
		String condicao = null;
		
		Query query = null;
		Map parameters = new HashMap();
		
		Collection retorno = null;
		// Cria a variável que vai conter o hql
		String consulta;
		
		//Caso seja ligação de água
		if (medicaoTipo.getId().intValue() == MedicaoTipo.LIGACAO_AGUA
				.intValue()) {
			condicao = "where hidInsHis.ligacaoAgua = :idImovel";
		} else if (medicaoTipo.getId().intValue() == MedicaoTipo.POCO
				.intValue()) {
			condicao = "where hidInsHis.imovel = :idImovel";
		}

		try {
						
			
			consulta =" select hidInsHis from HidrometroInstalacaoHistorico hidInsHis " 
					+ condicao 
					+ " and hidInsHis.dataRetirada in ( select max(hidInsHis.dataRetirada) from HidrometroInstalacaoHistorico hidInsHis "
					+ condicao + ")" ;
									
			parameters.put("idImovel", idImovel);
			
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
			
			retorno = query.list();
			
			if ( retorno != null ) {
				
				hidrometroInstalacaoHistorico = (HidrometroInstalacaoHistorico)
				Util.retonarObjetoDeColecao(retorno);
				
			}
			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return hidrometroInstalacaoHistorico;
	}
	
	/**
	 * 
	 * 	Pesquisar Capacidade do Hidrometro
	 * 
	 * @author Arthur Carvalho
	 * @date 17/06/2010
	 */
	public HidrometroCapacidade pesquisarCapacidadeHidrometro( String numeroHidrometro ) 
		throws ErroRepositorioException{
		
		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		HidrometroCapacidade retorno = null;
		
		// Cria a variável que vai conter o hql
		String consulta;
		

		try {
			
			consulta = "select hidCap from gcom.micromedicao.hidrometro.Hidrometro hid " 
					+ "inner join hid.hidrometroCapacidade  hidCap "
					+ "where hid.numero =:numeroHidrometro";
									

			retorno = (HidrometroCapacidade) session.createQuery(consulta)
				.setString("numeroHidrometro", numeroHidrometro)
				.uniqueResult();
			
			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
     * UC1027 - Exibir Consultar Arquivo Texto Leitura Divisão.
     *  
     * @author Hugo Leonardo
     * @created 04/06/2010
	 * 
	 * @param idArquivoTextoRoteiroEmpresaDivisao
	 * @param situacaoNova
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoTextoDivisaoEnviado(Integer idArquivoTextoRoteiroEmpresaDivisao, int situacaoNova) 
		throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();

		try {
			String sql = "update gcom.micromedicao.ArquivoTextoRoteiroEmpresaDivisao "
					+ " set sitl_id = :idSituacaoTransmissaoLeituraNova "
					+ ", tred_tmultimaalteracao = :data "
					+ " where tred_id = :id ";

			session.createQuery(sql).
			  setLong("id",idArquivoTextoRoteiroEmpresaDivisao).
			  setInteger("idSituacaoTransmissaoLeituraNova", situacaoNova).
			  setTimestamp("data", new Date()).
			  executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * UC1027 - Exibir Consultar Arquivo Texto Leitura Divisão.
     *  
     * @author Hugo Leonardo
     * @created 04/06/2010
	 * 
	 * @param ids
	 * @param situacaoNova
	 * 
	 * @param id
	 * @param situacaoNova
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoTextoDivisao(Integer id, Integer situacaoNova)
			throws ErroRepositorioException {
		
		ArquivoTextoRoteiroEmpresaDivisao arquivoTextoRoteiroDivisao = null;
		Session session = HibernateUtil.getSession();
		
		String hql = "FROM ArquivoTextoRoteiroEmpresaDivisao a where a.id = " + id;
		String hql2 = "FROM SituacaoTransmissaoLeitura s where s.id ="
				+ situacaoNova;
		try {
			
			arquivoTextoRoteiroDivisao = (ArquivoTextoRoteiroEmpresaDivisao) session
					.createQuery(hql).uniqueResult();
			arquivoTextoRoteiroDivisao
					.setSituacaoTransmissaoLeitura((SituacaoTransmissaoLeitura) session
							.createQuery(hql2).uniqueResult());
			arquivoTextoRoteiroDivisao.setUltimaAlteracao(new Date());
			
			session.update(arquivoTextoRoteiroDivisao);
			session.flush();
			
		} catch (HibernateException e) {
			
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			
			HibernateUtil.closeSession(session);
		}
	}
	
	
	/**
	 * [UC0103] - Efetuar Rateio de Consumo
	 *
	 * @author Raphael Rossiter
	 * @date 01/07/2010
	 *
	 * @param imovel
	 * @return RateioTipo
	 * @throws ErroRepositorioException
	 */
	public RateioTipo obterDadosRateioTipoParaLigacaoAgua(Imovel imovel)
			throws ErroRepositorioException {

		RateioTipo retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT rttp "// 0
					+ "From HidrometroInstalacaoHistorico hidi "
					+ "LEFT JOIN hidi.ligacaoAgua lagu "
					+ "LEFT JOIN hidi.rateioTipo rttp "
					+ "WHERE lagu.id = :idImovel "
					+ "AND hidi.dataRetirada is null ";

			retorno = (RateioTipo) session.createQuery(consulta).setInteger("idImovel",
					imovel.getId()).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 *	CRC 4821
	 * @author Arthur Carvalho
	 * @date 14/07/2010
	 *
	 * @throws ErroRepositorioException
	 */
	public boolean verificaExistenciaHidrometro(Integer idImovel)
			throws ErroRepositorioException {

		Integer hidrometro = null;
		boolean retorno = false;
		
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT lagu.hidrometroInstalacaoHistorico.id "// 0
					+ "From LigacaoAgua lagu "
					+ "WHERE lagu.id = :idImovel ";

			hidrometro = (Integer) session.createQuery(consulta).setInteger("idImovel",
					idImovel).setMaxResults(1).uniqueResult();

			if ( hidrometro != null ) {
				retorno = true;
			}
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
    
	/**
	 * Exclue todos os registros da tabela de atualização 
     * de sequencial de rota
     *  
	 * @author bruno
     * @date 09/08/2010
	 * 
	 * @param anoMesReferencia
	 * @param idRota
	 * @throws ErroRepositorioException
	 */
    public void deletarRotaAtualizacaoSequencial( 
            Integer anoMesReferencia, Integer idRota )
            throws ErroRepositorioException {

        Session session = HibernateUtil.getSession();
        try {
            String delete = "delete RotaAtualizacaoSeq as rotaAtualizacaoSeq "
                    + "where rotaAtualizacaoSeq.amFaturamento = :amFaturamento "
                    + " and rotaAtualizacaoSeq.rota.id = :idRota ";

            session.createQuery(delete)
                .setInteger( "amFaturamento", anoMesReferencia.intValue() )
                .setInteger( "idRota", idRota.intValue() )
                    .executeUpdate();

        } catch (HibernateException e) {
            // levanta a exceção para a próxima camada
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            // fecha a sessão
            HibernateUtil.closeSession(session);
        }
    }
    
    /**
     * 
     * Seleciona todos os imóveis de uma determinada
     * rota / ano mes.
     * 
     * @author Bruno Barros
     * @date 11/08/2010
     *  
     * @param idRota - Id da rota 
     * @param anoMesReferencia - Ano Mes de referencia do Grupo de faturamento
     * @throws ControladorException
     */
    public Collection<RotaAtualizacaoSeq> pesquisarRotaAtualizacao( Integer idRota, Integer anoMesReferenciaGrupoFaturamento )
            throws ErroRepositorioException {

        Collection<RotaAtualizacaoSeq> retorno = null;
        
        Session session = HibernateUtil.getSession();
        String consulta;

        try {
            consulta = 
                    "from RotaAtualizacaoSeq ras " +
                    "WHERE ras.rota.id = :idRota and ras.amFaturamento = :anoMesReferenciaGrupoFaturamento ";

            retorno = 
                (Collection) session.
                    createQuery(consulta).
                        setInteger("idRota",  idRota).
                        setInteger("anoMesReferenciaGrupoFaturamento",  anoMesReferenciaGrupoFaturamento ).list();
        } catch (HibernateException e) {
            // levanta a exceção para a próxima camada
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            // fecha a sessão
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }    
    
    /**
	 * 
	 * Obter rota do imóvel através do código da rota
	 * do sequencial e da localidade .
	 * 
	 * @author Breno Santos
	 * @date 13/08/2010
	 * 
	 * @param codRota, sequencial, localidade
	 * @throws ErroRepositorioException
	 */
	public Integer obterIdRotaPorSetorComercialELocalidade(Integer codRota, Integer setorComercial, Integer localidade)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
		
			consulta = 
				"SELECT " +
				"rot.id " +
				"FROM Rota rot " +
				"where " +
				"rot.setorComercial.localidade.id = :localidade and " +
				"rot.setorComercial.codigo = :setorComercial and " +
				"rot.codigo = :codRota";
			

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("localidade", localidade)
					.setInteger("setorComercial", setorComercial)
					.setInteger("codRota", codRota).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
    /**
	 * Obter rota do imóvel através do intervalor de localidade,setor e numero da quadra .
	 * 
	 * @author Rafael Pinto
	 * @date 16/05/2011
	 * 
	 * @param localidadeInicial, localidadeFinal,
			codigoSetorComercialIncial,codigoSetorComercialFinal,
			numeroQuadraInicial,numeroQuadraFinal
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> obterIdRotaPorQuadra(
		Integer localidadeInicial,
		Integer localidadeFinal,
		Integer codigoSetorComercialIncial, 
		Integer codigoSetorComercialFinal,
		Integer numeroQuadraInicial, 
		Integer numeroQuadraFinal)
		throws ErroRepositorioException {

		Collection<Integer> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
		
			consulta = 
				
				" SELECT distinct rot.rota_id idRota"
					+ " FROM micromedicao.rota rot "
					+ "INNER JOIN cadastro.setor_comercial setor on setor.stcm_id = rot.stcm_id "
					+ "INNER JOIN cadastro.quadra qua on qua.stcm_id = setor.stcm_id "
					+ "WHERE setor.loca_id BETWEEN :localidadeInicial and :localidadeFinal "
					+ "AND setor.stcm_cdsetorcomercial BETWEEN :setorComercialInicial and :setorComercialFinal "
					+ "AND qua.qdra_nnquadra BETWEEN :numeroQuadraInicial and :numeroQuadraFinal ";

			retorno = session.createSQLQuery(consulta)
				.addScalar("idRota",Hibernate.INTEGER)
				.setInteger("localidadeInicial", localidadeInicial)
				.setInteger("localidadeFinal", localidadeFinal)
				.setInteger("setorComercialInicial", codigoSetorComercialIncial)
				.setInteger("setorComercialFinal", codigoSetorComercialFinal)
				.setInteger("numeroQuadraInicial", numeroQuadraInicial)
				.setInteger("numeroQuadraFinal", numeroQuadraFinal).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * Remover todos os Itens de Servico do Contrato
	 * [UC1055] - Informar Valor de Item de Serviço Por Contrato
	 * 
	 * @author Hugo Leonardo
	 * @date 03/08/2010
	 * 
	 * @param idContratoEmpresaServico
	 * @return void
	 */
	public void removerItemServicoDoContrato(Integer idContratoEmpresaServico) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "delete "
					+ "ItemServicoContrato isc "
					+ "where isc.contratoEmpresaServico = :idEmpresaServicoContrato";

			session.createQuery(consulta).setInteger("idEmpresaServicoContrato", idContratoEmpresaServico)
					.executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1054] - Gerar Relatório Boletim de Medição
	 * 
	 * @author Hugo Leonardo
	 * @date 05/08/2010
	 * 
	 * @param FiltrarRelatorioBoletimMedicaoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioBoletimMedicao( FiltrarRelatorioBoletimMedicaoHelper helper) 
		throws ErroRepositorioException{
		
		Collection retorno = null;
		String consulta = "";
		Query query = null;
		Session session = HibernateUtil.getSession();
		
		try {	
			consulta += " SELECT     greg.greg_nmregional as gerencia, " //0 
				     +  "            empr.empr_nmempresa as empresa, " //1
				     +  "            cese.cese_dsnumerocontrato as contrato, " //2
				     +  "            loca.loca_nmlocalidade as localidade, " //3
				     +  "            itse.itse_cditem as codigoItem, " //4
				     +  "            itse.itse_dsitemservico as descricaoItem, " //5
				     +  "            sum(case when (itse.itse_cdconstantecalculo = 1 "
				     +  "                     and "
				     +  "                     ( ( exists        	(select  mcpf.imov_id "
				     +  "                                           from     faturamento.mov_conta_prefaturada mcpf "
				     +  "                                           where    (mrem.imov_id = mcpf.imov_id and "
				     +  "                                                    mrem.mrem_ammovimento = mcpf.mcpf_ammovimento and "
				     +  "                                                    mcpf.mcpf_icemissaoconta = 1 and "
				     +  "                                                    mcpf.medt_id = 1 and "
				     +  "                                                    ((mcpf.mcpf_nnleiturahidrometro is not null) or (mcpf.ltan_id is not null) ) ) ) ) "
				     +  "                       or "
				     +  "                       ( exists          (select  mcpf.imov_id "
				     +  "                                         from     faturamento.mov_conta_prefaturada mcpf "
				     +  "                                         where    (mrem.imov_id = mcpf.imov_id and "
				     +  "                                                  mrem.mrem_ammovimento = mcpf.mcpf_ammovimento and "
				     +  "                                                  mcpf.mcpf_icemissaoconta = 1 and "
				     +  "                                                  mcpf.medt_id = 2 and "
				     +  "                                                  ((mcpf.mcpf_nnleiturahidrometro is not null) or (mcpf.ltan_id is not null)) and "
				     +  "                                                  not exists   	(select   mcpf1.imov_id "
				     +  "                                                                 	from     	faturamento.mov_conta_prefaturada mcpf1 "
				     +  "                                                                	where       mrem.mrem_ammovimento = mcpf.mcpf_ammovimento and "
				     +  "                                                                               mcpf.mcpf_icemissaoconta = 1 and "
				     +  "                                                                           	mcpf.medt_id = 1 and "
				     +  "                                                                            ((mcpf.mcpf_nnleiturahidrometro is not null) or (mcpf.ltan_id is not null)) ) ) ) ) )  ) "
				     +  "                     then     1 "
				     
				     +  "                     when     (itse.itse_cdconstantecalculo = 2 and "
				     +  "                              exists          	(select   mcpf.imov_id "
				     +  "                                                  	from     	faturamento.mov_conta_prefaturada mcpf "
				     +  "                                                  	where     (mcpf.imov_id = mrem.imov_id and "
				     +  "                                                            	mrem.mrem_ammovimento = mcpf.mcpf_ammovimento and "
				     +  "                                                            	mcpf.mcpf_icemissaoconta = 1 and "
				     +  "                                                            	((mcpf.mcpf_nnleiturahidrometro is null) and (mcpf.ltan_id is null)) ) ) ) "
				     +  "                     then     1 "
				     
				     +  "                     when     (itse.itse_cdconstantecalculo = 3 and "
				     +  "                              mrem.mrem_tmprocessamento is null and "
				     +  "                              ((mrem.mrem_nnleiturahidrometro is not null) or (mrem.ltan_id is not null))) "
				     +  "                     then     1 "
				     
				     +  "                     when     (itse.itse_cdconstantecalculo = 4 and "
				     +  "                              (exists             (select    cnta.imov_id "
				     +  "                                                  from     	faturamento.conta cnta "
				     +  "                                                  inner     	join faturamento.conta_impressao cnti on (cnta.cnta_id = cnti.cnta_id) "
				     +  "                                                  where     	cnta.imov_id = mrem.imov_id and cnta.cnta_amreferenciaconta = mrem.mrem_ammovimento) "
				     +  "                              or "
				     +  "                              (exists             (select   	cnhi.imov_id "
				     +  "                                                  from     	faturamento.conta_historico cnhi "
				     +  "                                                  inner     	join faturamento.conta_impressao cnti on (cnhi.cnta_id = cnti.cnta_id) "
				     +  "                                                  where     	cnhi.imov_id = mrem.imov_id and cnhi.cnhi_amreferenciaconta = mrem.mrem_ammovimento))) ) "
				     +  "                    	then     1 "
				     
				     +  "                    	when     (itse.itse_cdconstantecalculo = 5 and "
				     +  "                                exists     	   (select     mcpf.imov_id "
				     +  "                                                  from        faturamento.mov_conta_prefaturada mcpf "
				     +  "                                                  where       (mcpf.imov_id = mrem.imov_id and "
				     +  "                                                          	   mcpf.cbdo_id is not null and "
				     +  "                                                          	   mrem.mrem_ammovimento = mcpf.mcpf_ammovimento and "
				     +  "                                                          	   mcpf.mcpf_icemissaoconta = 1) )) "
				     +  "                    	then	   1 "
				     
				     +  "                 		else       0 "
				     +  "            end) qtd, " // 6
				     +  "            itsc.itsc_vlitemservcontr valor " // 7
				     
				     +  " FROM       micromedicao.movimento_roteiro_empr mrem "
				     +  "            inner join micromedicao.contrato_empresa_servico cese on (mrem.empr_id = cese.empr_id) "
				     +  "            inner join micromedicao.item_servico_contrato itsc on (cese.cese_id = itsc.cese_id) "
				     +  "            inner join micromedicao.item_servico itse on (itsc.itse_id = itse.itse_id) "
				     +  "          	 inner join cadastro.localidade loca on (mrem.loca_id = loca.loca_id) "
				     +  "            inner join cadastro.gerencia_regional greg on (mrem.greg_id = greg.greg_id) "
				     +  "            inner join cadastro.empresa empr on (mrem.empr_id = empr.empr_id) "
				     +  " WHERE ";
				     
			// Empresa
			if(helper.getEmpresa() != null && !helper.getEmpresa().equals("")){
				
				consulta += " mrem.empr_id = " + helper.getEmpresa(); 
			}
			
			// Número Contrato
			if(helper.getNumeroContrato() != null && !helper.getNumeroContrato().equals("")){
				
				consulta += " and cese.cese_id = " + helper.getNumeroContrato(); 
			}
			
			// Gerência
			if(helper.getGerenciaRegional() != null && !helper.getGerenciaRegional().equals("")){
				
				consulta += " and mrem.greg_id = " + helper.getGerenciaRegional(); 
			}
			
			// AnoMes
			if(helper.getMesAnoReferencia() != null && !helper.getMesAnoReferencia().equals("")){
				
				consulta += " and mrem.mrem_ammovimento = " + helper.getMesAnoReferencia(); 
			}
			
			// Localidade
			if(helper.getLocalidadeInicial() != null && helper.getLocalidadeFinal() != null 
					&& !helper.getLocalidadeInicial().equals("") && !helper.getLocalidadeFinal().equals("")){
				
				consulta += " and (mrem.loca_id between " + helper.getLocalidadeInicial()
					 	 +  " and " + helper.getLocalidadeFinal() + ") ";
			}
			
			consulta += " "
				     +  " GROUP    BY   	empr.empr_nmempresa, " 
				     +  "               	greg.greg_nmregional, "
				     +  "               	cese.cese_dsnumerocontrato, " 
				     +  "               	loca.loca_nmlocalidade, "
				     +  "               	itse.itse_cditem, " 
				     +  "               	itse.itse_dsitemservico, " 
				     +  "               	itsc.itsc_vlitemservcontr "
				     
				     +  " ORDER    BY     	greg.greg_nmregional, "
				     +  "             	    loca.loca_nmlocalidade, "
				     +  "             		itse.itse_cditem ";
				
			query = (Query) session.createSQLQuery(consulta)
						.addScalar("gerencia", Hibernate.STRING)
						.addScalar("empresa",Hibernate.STRING)
						.addScalar("contrato",Hibernate.STRING)
						.addScalar("localidade",Hibernate.STRING)
						.addScalar("codigoItem",Hibernate.STRING)
						.addScalar("descricaoItem",Hibernate.STRING)
						.addScalar("qtd",Hibernate.INTEGER)
						.addScalar("valor",Hibernate.BIG_DECIMAL);
		
			retorno = query.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1054] - Gerar Relatório Boletim de Medição
	 * 
	 * @author Hugo Leonardo
	 * @date 06/08/2010
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEmpresasContratoServico( ) throws ErroRepositorioException {
		
		Collection empresas = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = " select distinct contrato.empresa "
					+ " from gcom.micromedicao.ContratoEmpresaServico as contrato "
					+ " where contrato.dataFimContrato >= :data "
					+ " or contrato.dataFimContrato is null "
					+ " order by contrato.empresa.descricao ";

			empresas = session.createQuery(consulta).setDate("data", new Date()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		// retorna a coleção de atividades pesquisada(s)
		return empresas;
	}
	
	/**
	 * 
 	 * [UC0629] Consultar Arquivo Texto para Leitura
 	 * 	
 	 * 	[FS0011 - Verificar Leituras];
 	 * 
	 * @author Hugo Amorim
	 * @date 20/08/2010
	 */
	//@Metodo sobrescrito na Classe RepositorioMoicromedicaoPostgresHBM
	public Collection pesquisarSituacaoLeitura(Integer anoMes,Integer idGrupo,Integer idRota)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;
		try {
			consulta = "SELECT \n"
				+" COALESCE(medidosEnviados.qt_medidos_enviados,0) as qt_medidos_enviados, \n"
				+" COALESCE(medidosRecebidos.qt_medidos_recebidos,0) as qt_medidos_recebidos, \n"
				+" ABS(COALESCE(medidosEnviados.qt_medidos_enviados,0) - COALESCE(medidosRecebidos.qt_medidos_recebidos,0)) as diferencaMedidos, \n"
				+" COALESCE(naoMedidosEnviados.qt_nao_medidos_enviados,0) as qt_nao_medidos_enviados, \n"
				+" COALESCE(naoMedidosRecebidos.qt_nao_medidos_recebidos,0) as qt_nao_medidos_recebidos, \n"
				+" ABS(COALESCE(naoMedidosEnviados.qt_nao_medidos_enviados,0) - COALESCE(naoMedidosRecebidos.qt_nao_medidos_recebidos,0)) as diferencaNaoMedidos, \n"
				+" COALESCE(medidosImpressos.qt_medidos_impressos,0) as qt_medidos_impressos, \n"
				+" COALESCE(medidosNaoImpressos.qt_medidos_nao_impressos,0) as qt_medidos_nao_impressos, \n"
				+" COALESCE(naoMedidosImpressos.qt_nao_medidos_impressos,0) as qt_nao_medidos_impressos, \n"
				+" COALESCE(naoMedidosNaoImpressos.qt_nao_medidos_nao_impressos,0) as qt_nao_medidos_nao_impressos, \n"				
				+" atre.txre_dsmotivofinalizacao as motivoFinalizacao, \n"
				+" COALESCE(anormalidades.qt_anormalidades,0) as qt_anormalidades \n"
				+" from micromedicao.arquivo_texto_rot_empr atre \n"
				+" inner join micromedicao.rota rt on (atre.rota_id = rt.rota_id) \n"
				//qt_medidos_enviados
				+" LEFT JOIN ( \n"
				+" SELECT rota_id rota,mrem_ammovimento as anomes, count(distinct(imov_id)) as qt_medidos_enviados \n"
				+" FROM micromedicao.movimento_roteiro_empr mre \n"
				+" WHERE trim(MREM_NNHIDROMETRO) is not null " 
				+" GROUP BY rota_id,mre.mrem_ammovimento) medidosEnviados \n"
				+" on (atre.rota_id = medidosEnviados.rota and atre.txre_amreferencia = medidosEnviados.anomes) \n"
				//qt_medidos_recebidos
				+" LEFT JOIN ( \n"
				+" SELECT rota_id as rota ,mcpf_ammovimento as anomes, count(distinct(imov_id)) as qt_medidos_recebidos \n"
				+" FROM faturamento.mov_conta_prefaturada where \n"
				+" mcpf_nnleiturahidrometro is not null or ltan_id is not null \n"
				+" group by rota_id,mcpf_ammovimento) medidosRecebidos \n"
				+" on (atre.rota_id = medidosRecebidos.rota and atre.txre_amreferencia = medidosRecebidos.anomes) \n"
				//qt_nao_medidos_enviados
				+" LEFT JOIN ( \n"
				+" SELECT rota_id as rota,mrem_ammovimento as anomes, count(distinct(imov_id)) as qt_nao_medidos_enviados \n" 
				+" FROM micromedicao.movimento_roteiro_empr mre \n"
				+" WHERE trim(MREM_NNHIDROMETRO) is null " 
				+" GROUP BY rota_id,mre.mrem_ammovimento) naoMedidosEnviados \n"
				+" on (atre.rota_id = naoMedidosEnviados.rota and atre.txre_amreferencia = naoMedidosEnviados.anomes) \n"
				//qt_nao_medidos_recebidos
				+" LEFT JOIN ( \n"
				+" SELECT rota_id as rota, mcpf_ammovimento as anomes, count(distinct(imov_id)) as qt_nao_medidos_recebidos \n"
				+" FROM faturamento.mov_conta_prefaturada mcp \n"
				+" WHERE \n"
				+" mcpf_nnleiturahidrometro is null and mcp.ltan_id is null  \n"
				+" group by rota_id,mcp.mcpf_ammovimento) naoMedidosRecebidos \n"
				+" on (atre.rota_id = naoMedidosRecebidos.rota and atre.txre_amreferencia = naoMedidosRecebidos.anomes) \n"
				//qt_medidos_impressos
				+" LEFT JOIN( \n"
				+" SELECT rota_id as rota, mcp.mcpf_ammovimento as anomes, count(distinct(imov_id)) as qt_medidos_impressos \n"
				+" FROM faturamento.mov_conta_prefaturada mcp where  \n"
				+" mcp.mcpf_icemissaoconta = 1 and (mcpf_nnleiturahidrometro is not null or ltan_id is not null)   \n"
				+" group by rota_id,mcp.mcpf_ammovimento) medidosImpressos on (atre.rota_id = medidosImpressos.rota and atre.txre_amreferencia = medidosImpressos.anomes) \n"
				//qt_medidos_nao_impressos
				+" LEFT JOIN( \n"
				+" SELECT rota_id as rota, mcp.mcpf_ammovimento as anomes, count(distinct(imov_id)) as qt_medidos_nao_impressos \n"
				+" FROM faturamento.mov_conta_prefaturada mcp where  \n"
				+" mcp.mcpf_icemissaoconta = 2 and (mcpf_nnleiturahidrometro is not null or ltan_id is not null)   \n"
				+" group by rota_id,mcp.mcpf_ammovimento) medidosNaoImpressos on (atre.rota_id = medidosNaoImpressos.rota and atre.txre_amreferencia = medidosNaoImpressos.anomes) \n"
				//qt_nao_medidos_impressos
				+" LEFT JOIN( \n"
				+" SELECT rota_id as rota, mcp.mcpf_ammovimento as anomes, count(distinct(mcp.imov_id)) as qt_nao_medidos_impressos \n"
				+" FROM faturamento.mov_conta_prefaturada mcp \n"
				+" INNER JOIN cadastro.imovel i  on i.imov_id = mcp.imov_id \n"
				+" LEFT JOIN  atendimentopublico.ligacao_agua la on la.lagu_id = mcp.imov_id \n"
				+" WHERE (la.hidi_id is null and i.hidi_id is null) and \n"
				+" mcp.mcpf_icemissaoconta = 1 and (mcpf_nnleiturahidrometro is null or mcp.ltan_id is null)  \n"
				+" group by rota_id,mcp.mcpf_ammovimento) naoMedidosImpressos on (atre.rota_id = naoMedidosImpressos.rota and atre.txre_amreferencia = naoMedidosImpressos.anomes) \n"
				//qt_nao_medidos_nao_impressos
				+" LEFT JOIN( \n"
				+" SELECT rota_id as rota, mcp.mcpf_ammovimento as anomes, count(distinct(mcp.imov_id)) as qt_nao_medidos_nao_impressos \n"
				+" FROM faturamento.mov_conta_prefaturada mcp \n"
				+" INNER JOIN cadastro.imovel i  on i.imov_id = mcp.imov_id \n"
				+" LEFT JOIN  atendimentopublico.ligacao_agua la on la.lagu_id = mcp.imov_id \n"
				+" WHERE (la.hidi_id is null and i.hidi_id is null) and \n"
				+" mcp.mcpf_icemissaoconta = 2 and (mcpf_nnleiturahidrometro is null or mcp.ltan_id is null)   \n"
				+" group by rota_id,mcp.mcpf_ammovimento) naoMedidosNaoImpressos on (atre.rota_id = naoMedidosNaoImpressos.rota and atre.txre_amreferencia = naoMedidosNaoImpressos.anomes) \n"				
				// qt_anormalidades
				+" LEFT JOIN( \n"
				+" SELECT rota_id as rota, mcp.mcpf_ammovimento as anomes, count(distinct(imov_id)) as qt_anormalidades \n"
				+" FROM faturamento.mov_conta_prefaturada mcp where  \n"
				+" ltan_id is not null  \n"
				+" group by rota_id,mcp.mcpf_ammovimento) anormalidades on (anormalidades.rota = atre.rota_id and anormalidades.anomes = atre.txre_amreferencia)  \n"
				+" WHERE txre_amreferencia = :anoMes and rt.rota_id = :idRota ";
			
			retorno = session.createSQLQuery(consulta)
				.addScalar("qt_medidos_enviados", Hibernate.INTEGER)
				.addScalar("qt_medidos_recebidos", Hibernate.INTEGER)
				.addScalar("diferencaMedidos", Hibernate.INTEGER)
				.addScalar("qt_nao_medidos_enviados", Hibernate.INTEGER)
				.addScalar("qt_nao_medidos_recebidos", Hibernate.INTEGER)
				.addScalar("diferencaNaoMedidos", Hibernate.INTEGER)
				.addScalar("qt_medidos_impressos", Hibernate.INTEGER)
				.addScalar("qt_medidos_nao_impressos", Hibernate.INTEGER)
				.addScalar("qt_nao_medidos_impressos", Hibernate.INTEGER)
				.addScalar("qt_nao_medidos_nao_impressos", Hibernate.INTEGER)								
				.addScalar("motivoFinalizacao", Hibernate.STRING)
				.addScalar("qt_anormalidades", Hibernate.INTEGER)
				.setInteger("anoMes", anoMes)
				.setInteger("idRota", idRota)
				.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	
	
	/**
	 * 
	 * Verifica se uma rota em questão está com
	 * os arquivos de impressão simultanea divididos
	 * 
	 * @autor Bruno Barros.
	 * @date 26/08/2010 
	 * 
	 * @param idRota - Id da rota a ser pesquisada
	 * 
	 * @return boolean - A rota está dividida 
	 */
	public boolean isRotaDividida( Integer idRota, Integer anoMesFaturamento ) 
		throws ErroRepositorioException{
		Integer quantidade = null;

		Session session = HibernateUtil.getSession();
		String consulta;
				
		try {
			consulta = 
				"select " +
				"  count( atred.tred_id ) as quantidade " +
				"from " +
				"  micromedicao.arquivo_texto_rot_empr atre " +
				"  inner join micromedicao.arq_txt_roteiro_emp_div atred on ( atred.txre_id = atre.txre_id ) " +
				"where " +
				"  atre.rota_id = :idRota and " +
				"  atre.txre_amreferencia = :anoMesFaturamento ";
			
			quantidade = (Integer)session.createSQLQuery(consulta)
				.addScalar("quantidade", Hibernate.INTEGER)
				.setInteger("idRota", idRota)
				.setInteger("anoMesFaturamento", anoMesFaturamento )
				.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return ( quantidade == null ) || ( quantidade > 0 );

	}	
	
	/**
	 * Verifica de imovel é medido ou não-medido
	 * 
	 * 	retorna true se medido
	 * 			false se não-medido
	 * 
	 * @author Hugo Amorim
	 * @date 26/08/2010
	 */
	public boolean verificarSituacaoMedicao(Integer idImovel)throws ErroRepositorioException{
		
			// Cria uma sessão com o hibernate
			Session session = HibernateUtil.getSession();
			boolean retorno = false;
			
			// Cria a variável que vai conter o hql
			String consulta;
	
		try {
			
			
			consulta =" SELECT"
				+" CASE "
				+" 	WHEN (lagu.hidi_id is not null or imovel.hidi_id is not null) THEN 1 "
				+" 	ELSE CASE  "
				+" 	WHEN (lagu.hidi_id is null and imovel.hidi_id is null) THEN 2 END  "
				+"   END as medido "
				+" FROM cadastro.imovel imovel "
				+" LEFT JOIN atendimentopublico.ligacao_agua lagu on lagu.lagu_id = imovel.imov_id "
				+" WHERE imovel.imov_id = :idImovel ";			
					
			
			Integer resultado = new Integer(2);
			resultado = (Integer) session
					.createSQLQuery( consulta )
					.addScalar("medido", Hibernate.INTEGER)
					.setInteger( "idImovel", idImovel )
					.setMaxResults(1).uniqueResult();
			
			if (resultado == new Integer(1)){
				retorno = true;
			} else {
				retorno = false;
			}
	
			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}
	
	/**
	 * Verificar se a leitura de para impressão
	 * simultanea de um determinado imóvel/ano mes chegou
	 * 
	 * 
	 * 	retorna 
	 * 		true Se chegou
	 * 		false Se não chegou
	 * 
	 * @author Bruno Barros
	 * @date 31/08/2010
	 * 
	 * @param String matricula - Matricula do imóvel a ser pesquisado
	 * @param Integer anoMesFaruramento - Ano mes do faturamento a ser pesquisado
	 */
	public boolean verificarExistenciaLeituraImpressaoSimultanea(String matricula,
			Integer anoMesFaturamento) throws ErroRepositorioException {

		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		Integer retorno = null;

		// Cria a variável que vai conter o hql
		String consulta;

		try {
			consulta = "select "
					+ "  mcpf.imovel.id as imovel "
					+ " from "
					+ "  MovimentoContaPrefaturada mcpf "
					+ " where "
					+ "  mcpf.imovel.id = :idImovel and "
					+ "  mcpf.anoMesReferenciaPreFaturamento = :anoMesFaturamento ";

			retorno = 
				(Integer) session.createQuery(consulta)
					.setString("idImovel",	matricula)
					.setInteger("anoMesFaturamento", anoMesFaturamento)
						.setMaxResults(1).uniqueResult();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return (retorno == null ? false : true);
	}
	
	/**
	 * 
	 * Verifica quais os imóveis para uma determinada
	 * cujo as releituras foram solicitadas, e que 
	 * ainda não foram enviadas, para um determinado
	 * ano mes de referencia
	 * 
	 * @author Bruno Barros
	 * @date 01/09/2010
	 * 
	 * @param idRota - Id da rota a ser pesquisada
	 * @param anoMesReferencia - Ano mes de referencia
	 * 
	 * @return Collection<ReleituraMobile> - Coleção com os registros
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<ReleituraMobile> pesquisarImoveisReleituraMobileSolicitada( Integer idRota, Integer anoMesReferencia )
		throws ErroRepositorioException{
		
		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		Collection<ReleituraMobile> retorno = null;

		// Cria a variável que vai conter o hql
		String consulta;

		try {
			consulta = 
				" from " +
				"   ReleituraMobile rm " +
				"   inner join fetch rm.imovel " +
				" where " +
				"   rm.imovel.quadra.rota.id = :idRota and " +
				"   rm.anoMesReferencia = :anoMesReferencia and " +
				"   rm.indicadorMensagemRecebida = :indicadorMensagemRecebida ";

			retorno = 
				(Collection) session.createQuery(consulta)
					.setInteger("idRota",	idRota )
					.setInteger("anoMesReferencia", anoMesReferencia )
					.setInteger("indicadorMensagemRecebida", ConstantesSistema.NAO )
					.list();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}	

	
	/**
	 * [UC0936] Informar Leitura por Rota 
	 * 
	 * @author Hugo Amorim
	 * @date 30/08/2010
	 * 
	 */
	public ArquivoTextoRoteiroEmpresa pesquisarArquivosTextoRoteiroEmpresa(
			Integer idLocalidade,Integer idRota, Integer idGrupo, Integer anoMesReferencia) 
			throws ErroRepositorioException {
		ArquivoTextoRoteiroEmpresa retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select atre "
					+ "from ArquivoTextoRoteiroEmpresa atre "
					+ "inner join atre.situacaoTransmissaoLeitura situacaoTransmissaoLeitura "
					+ "where atre.localidade.id = :idLocalidade "
					+ "and atre.rota.id = :idRota "
					+ "and atre.faturamentoGrupo.id = :idGrupo "
					+ "and atre.anoMesReferencia = :anoMesReferencia ";
			
			retorno = (ArquivoTextoRoteiroEmpresa) session.createQuery(consulta)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idRota", idRota)
					.setInteger("idGrupo", idGrupo)
					.setInteger("anoMesReferencia", anoMesReferencia)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1004] Processar Leitura via Telemetria
	 * 
	 * [SB0002] – Validar Dados Leitura
	 *
	 * @author Raphael Rossiter
	 * @date 09/09/2010
	 *
	 * @param idImovel
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelTelemetria(Integer idImovel) throws ErroRepositorioException {
		
		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select imov.id from Imovel imov where imov.id = :idImovel ";
			
			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idImovel", idImovel)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * 
	 * Pesquisa os imóveis para a tela de Filtrar Imoveis para Releitura
	 * 
	 * @author Bruno Barros
	 * @date 14/09/2010
	 * 
	 * @param anoMesReferencia 
	 * @param idGrupoFaturamento
	 * @param idRota
	 * @param idQuadra
	 * @param idSituacaoTrasmissaoLeitura
	 * @return
	 */
	public Collection<ReleituraMobile> pesquisarImovelParaReleitura(
			String anoMesReferencia,
			String idGrupoFaturamento,
			String idRota,
			String idQuadra,
			String idEmpresa) throws ErroRepositorioException{
		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		Collection<ReleituraMobile> retorno = null;

		// Cria a variável que vai conter o hql
		String consulta;

		try {
			consulta = 
				"select " +
				"  rm " +
				"from " +				
				"  ReleituraMobile rm " +
				"  inner join fetch rm.imovel imo " +
				"  inner join fetch imo.quadra qua " +
				"  inner join fetch imo.setorComercial setCom " +
				"  inner join fetch qua.rota" +
				"  inner join qua.rota.empresa em" +
				"  left join fetch rm.leituraAnormalidadeAnteriorAgua " +
				"  left join fetch rm.leituraAnormalidadeAnteriorPoco " +
				"  left join fetch rm.leituraAnormalidadeAtualAgua " +
				"  left join fetch rm.leituraAnormalidadeAtualPoco, " +
				"  ArquivoTextoRoteiroEmpresa atre " +								
				"where " +
				" imo.quadra.rota.id = atre.rota.id and " +
				" imo.id = rm.imovel.id and " +
				" rm.anoMesReferencia = atre.anoMesReferencia " +
				" ";
				
			
			// Verificamos quais os parametros foram passados
			if ( anoMesReferencia != null && !anoMesReferencia.equals( "" ) ){
				consulta += "  and atre.anoMesReferencia = :anoMesReferencia ";
			}
			
			if ( idGrupoFaturamento != null && !idGrupoFaturamento.equals( ConstantesSistema.NUMERO_NAO_INFORMADO+"" ) ){
				consulta += "  and atre.faturamentoGrupo.id = :idFaturamentoGrupo  ";
			}
			
			if ( idRota != null && !idRota.equals( "" ) ){
				consulta += "  and atre.rota.id = :idRota ";
			}
			
			if ( idEmpresa != null && !idEmpresa.equals( ConstantesSistema.NUMERO_NAO_INFORMADO+"" )){
				consulta += " and em.id = :idEmpresa ";
			}
			
			if ( idQuadra != null && !idQuadra.equals( "" ) ){
				consulta +=  " and imo.quadra.id = :idQuadra  ";
			}
			
			consulta += "order by imo.numeroSequencialRota, imo.localidade.id, imo.setorComercial.codigo, imo.quadra.numeroQuadra,imo.lote,imo.subLote, rm.ultimaAlteracao";
			
			Query query = session.createQuery( consulta );
			
			if ( anoMesReferencia != null && !anoMesReferencia.equals( "" ) ){
				query.setString( "anoMesReferencia", Util.formatarMesAnoParaAnoMesSemBarra( anoMesReferencia ) );
			}
			
			if ( idGrupoFaturamento != null && !idGrupoFaturamento.equals( ConstantesSistema.NUMERO_NAO_INFORMADO+"" ) ){
				query.setString( "idFaturamentoGrupo", idGrupoFaturamento );
			}
			
			if ( idRota != null && !idRota.equals( "" ) ){
				query.setString( "idRota", idRota );
			}
			
			if ( idQuadra != null && !idQuadra.equals( "" ) ){
				query.setString( "idQuadra", idQuadra);
			}
			
			if ( idEmpresa != null && !idEmpresa.equals( ConstantesSistema.NUMERO_NAO_INFORMADO+"" ) ){
				query.setString( "idEmpresa", idEmpresa);
			}
			
			retorno =
				(Collection<ReleituraMobile>) query.list();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}
		
		return retorno;		
	}
	
	/**
	 * [UC0113] Faturar Grupo de Faturamento
	 * [SB0006] - Gerar Dados da Conta 
	 * 
	 * @author Vivianne Sousa
	 * @date 20/09/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public  Object[] obterLeituraAnteriorEAtualFaturamentoMedicaoHistorico(
			Integer idImovel, Integer anoMesReferencia) throws ErroRepositorioException {

		 Object[] retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT mdhi.leituraAnteriorFaturamento," 
					+ " mdhi.leituraAtualFaturamento "
					+ "FROM MedicaoHistorico mdhi "
					+ "INNER JOIN mdhi.ligacaoAgua lagu "
					+ "WHERE lagu.id = :idImovel AND mdhi.anoMesReferencia = :anoMesReferencia ";

			retorno = ( Object[]) session.createQuery(consulta)
					.setInteger("idImovel", idImovel)
					.setInteger("anoMesReferencia",	anoMesReferencia)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * 
	 * [UC0629] Consultar Arquivo Texto para Leitura
	 * 
	 * @author Bruno Barros
	 * @date 27/09/2010
	 *
	 */
	public Collection<Object[]>  pesquisarImoveisFaltandoSituacaoLeitura(
			Integer idRota, Integer anoMesReferencia ) throws ErroRepositorioException {		
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = 
				"select " +
				"  distinct mre.imov_id, " +
				"  case when ( la.hidi_id is not null or imo.hidi_id is not null ) then "+
				"   'SIM' " +
				"  else " +
				"    'NÃO'" +
				"  end as medido, ";
			
			
			String empresa = RepositorioUtilHBM.getInstancia().pesquisarParametrosDoSistema().getNomeAbreviadoEmpresa();			
			
			if ( empresa.equals( "COMPESA" ) ) {
				consulta += 				
					"  mre.mrem_nnsequencialrota, " +
					"  mre.empr_id, " +
					"  mre.loca_id, " +
					"  mre.mrem_cdsetorcomercial, " +
					"  mre.mrem_nnquadra, " +
					"  mre.mrem_nnloteimovel, " +
					"  mre.mrem_nnsubloteimovel ";
			} else if (empresa.toUpperCase().equals("CAERN")) {
				consulta += 				
					"  mre.greg_id," +
					"  mre.loca_id, " +
					"  mre.mrem_cdsetorcomercial, " +
					"  mre.mrem_cdrota, " +
					"  mre.mrem_nnsequencialrota, " +
					"  mre.mrem_nnloteimovel, " +
					"  mre.mrem_nnsubloteimovel ";				
			}
			
			consulta +=
				" from  " +
				"  micromedicao.movimento_roteiro_empr mre " +
				"  inner join cadastro.imovel imo on ( imo.imov_id = mre.imov_id ) " +
				"  left join atendimentopublico.ligacao_agua la on ( la.lagu_id = imo.imov_id ) " +
				"  inner join cadastro.empresa empresa on ( mre.empr_id = empresa.empr_id )" +
				"  left join faturamento.mov_conta_prefaturada mcpfa on " +   
				"   ( mcpfa.imov_id = mre.imov_id and mcpfa.mcpf_ammovimento = mre.mrem_ammovimento ) " +
				"  where  " +
				"    mre.mrem_ammovimento = :anoMesReferencia and " +
				"    mre.rota_id = :idRota  and " +
			    "    mcpfa.mcpf_id is null ";
			
			if ( empresa.equals( "COMPESA" ) ) {
				consulta +=
					"order by " +
					"  mre.empr_id, " +
					"  mre.loca_id, " +
					"  mre.mrem_cdsetorcomercial, " +
					"  mre.mrem_nnquadra, " +
					"  mre.mrem_nnloteimovel, " +
					"  mre.mrem_nnsubloteimovel ";
				
				retorno = 
					( Collection<Object[]>)  
						session.createSQLQuery(consulta)
							.addScalar( "imov_id", Hibernate.INTEGER )
							.addScalar( "medido", Hibernate.STRING )
							.addScalar( "empr_id", Hibernate.INTEGER )
							.addScalar( "loca_id", Hibernate.INTEGER )
							.addScalar( "mrem_cdsetorcomercial", Hibernate.INTEGER )
							.addScalar( "mrem_nnquadra", Hibernate.INTEGER )
							.addScalar( "mrem_nnloteimovel", Hibernate.INTEGER )
							.addScalar( "mrem_nnsubloteimovel", Hibernate.INTEGER )
							.setInteger("idRota", idRota)
							.setInteger("anoMesReferencia",	anoMesReferencia).list();
				
			} else if (empresa.toUpperCase().equals("CAERN")) {
				consulta +=
					"order by " +
					"  mre.greg_id," +
					"  mre.loca_id, " +
					"  mre.mrem_cdsetorcomercial, " +
					"  mre.mrem_cdrota, " +
					"  mre.mrem_nnsequencialrota, " +
					"  mre.mrem_nnloteimovel, " +
					"  mre.mrem_nnsubloteimovel ";
				
				retorno = 
					( Collection<Object[]>)  
						session.createSQLQuery(consulta)
							.addScalar( "imov_id", Hibernate.INTEGER )
							.addScalar( "medido", Hibernate.STRING )
							.addScalar( "greg_id", Hibernate.INTEGER )
							.addScalar( "loca_id", Hibernate.INTEGER )
							.addScalar( "mrem_cdsetorcomercial", Hibernate.INTEGER )
							.addScalar( "mrem_cdrota", Hibernate.INTEGER )
							.addScalar( "mrem_nnsequencialrota", Hibernate.INTEGER )
							.addScalar( "mrem_nnloteimovel", Hibernate.INTEGER )							
							.addScalar( "mrem_nnsubloteimovel", Hibernate.INTEGER )
							.setInteger("idRota", idRota)
							.setInteger("anoMesReferencia",	anoMesReferencia).list();				
			}				
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}		
	
	/**
	 * [UC0932] Monitorar Leituras Transmitidas
	 * 
	 * Pesquisa as leituras que foram trasmitidas.
	 * 
	 * @author Bruno Barros
	 * @date 28/09/2010
	 * 
	 * @param idRota
	 * @param anoMesReferencia
	 * @param indicadorContaImpressa
	 * @param indicadorMedido
	 * @return Colecao com as informações necessarias para o prenchimento da tela.
	 * 
	 */
	public Collection<Object[]> pesquisarImoveisMonitorarLeiturasTransmitidas(
		Integer idRota, Integer anoMesReferencia, Short indicadorContaImpressa, Short indicadorMedido ) throws ErroRepositorioException{
		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = 
				"select distinct " +
				"  trim( to_char( mre.loca_id, '000' ) || '.' ) || " +
				"  trim( to_char( mre.mrem_cdsetorcomercial, '000' ) || '.' ) || " +
				"  trim( to_char( mre.mrem_nnquadra, '000' ) || '.' ) || " +
				"  trim( to_char( cast( mre.mrem_nnloteimovel as numeric ), '0000' ) ) || '.' || " +
				"  trim( to_char( cast( mre.mrem_nnsubloteimovel as numeric ), '000' ) ) as inscricao, " +
				"  mre.imov_id, " +
				"  mre.mrem_nnsequencialrota, " +
				"  mre.mrem_nnleituraanterior, " +
				"  mre.mrem_nnleiturahidrometro, " +
				"  mre.ltan_id, " +
				"  mre.mrem_tmleitura, " +
				"  mre.mrem_tmultimaalteracao, " +
				"  mcpf.mcpf_icemissaoconta, " +
				"  CASE  " +
				"		WHEN mcpf.mcpf_icemissaoconta = 2 THEN " + 
				"		  CASE WHEN ( cnta.CNTA_ID is not null ) THEN " +
				"             CASE " + 
				"				WHEN ( cnta.cnta_vlagua + cnta.cnta_vlesgoto + cnta.cnta_vldebitos + cnta.cnta_vlimpostos - cnta.cnta_vlcreditos ) >= sp.parm_vlminemitirboleto THEN  " +
				"					'Valor é maior que R$' || to_char( sp.parm_vlminemitirboleto, '9999' ) " +
				"				WHEN ( ( sp.parm_nmabreviadoempresa = 'COMPESA' and ( imo.icte_id is null or imo.icte_id in ( 1, 3 ) ) ) or " +
				"		       				 ( sp.parm_nmabreviadoempresa = 'CAERN' and ( imo.icte_id is null or imo.icte_id = 9 ) ) or " +
				/*
				 * TODO : COSANPA
				 * 
				 * Acréscimo de uma condição para empresa COSANPA
				 * Igual ao da CAERN
				 */
				"							( sp.parm_nmabreviadoempresa = 'COSANPA' and ( imo.icte_id is null or imo.icte_id = 9 ) ) ) THEN " +  
				"					'Endereço alternativo' " + 
				"				ELSE " + 
				"					'Normal' " + 
				"			END " + 
				"         ELSE "+
		        "          CASE WHEN ( cnta_histo.cnhi_vlagua + cnta_histo.cnhi_vlesgoto + cnta_histo.cnhi_vldebitos + cnta_histo.cnhi_vlimpostos - cnta_histo.cnhi_vlcreditos ) >= sp.parm_vlminemitirboleto THEN "+
		        "            'Valor é maior que R$' || to_char( sp.parm_vlminemitirboleto, '9999' ) "+
		        "          WHEN ( ( sp.parm_nmabreviadoempresa = 'COMPESA' and ( imo.icte_id is null or imo.icte_id in ( 1, 3 ) ) ) or ( sp.parm_nmabreviadoempresa = 'CAERN' and ( imo.icte_id is null or imo.icte_id = 9 ) ) ) THEN "+
		        "            'Endereço alternativo' "+
		        "          ELSE "+
		        "          'Normal' "+
		        "          END "+    
		        "         END "+
				"	END as motivoNaoEmissao, " +
			    "  CASE " +
			    " 		WHEN (lagu.hidi_id is not null or imo.hidi_id is not null) THEN " +
			    "			true " +
			    " 	ELSE CASE  " +
			    " 		WHEN (lagu.hidi_id is null and imo.hidi_id is null) THEN " +
			    "			false " +
			    "		END  " +
			    "   END as medido, ";	
			
			String empresa = RepositorioUtilHBM.getInstancia().pesquisarParametrosDoSistema().getNomeAbreviadoEmpresa();			
			
			if ( empresa.equals( "COMPESA" ) ) {
				consulta += 				
					"  mre.mrem_nnsequencialrota, " +
					"  mre.empr_id, " +
					"  mre.loca_id, " +
					"  mre.mrem_cdsetorcomercial, " +
					"  mre.mrem_nnquadra, " +
					"  mre.mrem_nnloteimovel, " +
					"  mre.mrem_nnsubloteimovel ";
			} else if (empresa.toUpperCase().equals("CAERN") || empresa.toUpperCase().equals("CAER") ) {
				consulta += 				
					"  mre.greg_id," +
					"  mre.loca_id, " +
					"  mre.mrem_cdsetorcomercial, " +
					"  mre.mrem_cdrota, " +
					"  mre.mrem_nnsequencialrota, " +
					"  mre.mrem_nnloteimovel, " +
					"  mre.mrem_nnsubloteimovel ";				
			} else {
				/*
				 * TODO : COSANPA
				 * 
				 * Acréscimo desses campos de retorno para empresas que seja diferente da CAERN e COMPESA
				 * Igual ao da CAERN
				 */
				consulta += 				
					"  mre.greg_id," +
					"  mre.loca_id, " +
					"  mre.mrem_cdsetorcomercial, " +
					"  mre.mrem_cdrota, " +
					"  mre.mrem_nnsequencialrota, " +
					"  mre.mrem_nnloteimovel, " +
					"  mre.mrem_nnsubloteimovel ";
			}		
			
			consulta +=
				"from " +
				"  micromedicao.movimento_roteiro_empr mre " +
				"  inner join faturamento.mov_conta_prefaturada mcpf on ( mre.imov_id = mcpf.imov_id and mre.mrem_ammovimento = mcpf.mcpf_ammovimento ) " +
				"  left join faturamento.conta cnta on ( cnta.cnta_id = mcpf.cnta_id ) " +
				"  left join faturamento.CONTA_HISTORICO cnta_histo on ( cnta_histo.cnta_id = mcpf.cnta_id ) "+
				"  inner join cadastro.imovel imo on ( imo.imov_id = mcpf.imov_id ) " +
				"  left join atendimentopublico.ligacao_agua lagu on ( lagu.lagu_id = imo.imov_id ), " +				
				"  cadastro.sistema_parametros sp " +
				"where " +
				"  mre.mrem_ammovimento = :anoMesReferencia and " +
				"  mre.rota_id = :idRota ";
			
			if ( indicadorContaImpressa != null ){
				consulta += " and mcpf.mcpf_icemissaoconta = :indicadorContaImpressa ";
			}
			
			if ( indicadorMedido != null ){
				consulta += " and " +
			    	"  CASE " +
			    	" 		WHEN (lagu.hidi_id is not null or imo.hidi_id is not null) THEN " +
			    	"			1 " +
			    	" 	ELSE CASE  " +
			    	" 		WHEN (lagu.hidi_id is null and imo.hidi_id is null) THEN " +
			    	"			2 " +
			    	"		END  " +
			    	"   END = :indicadorMedido ";
			}			
			
			Query query = null;			
			
			if ( empresa.equals( "COMPESA" ) ) {
				consulta +=
					"order by " +
					"  mre.empr_id, " +
					"  mre.loca_id, " +
					"  mre.mrem_cdsetorcomercial, " +
					"  mre.mrem_nnquadra, " +
					"  mre.mrem_nnloteimovel, " +
					"  mre.mrem_nnsubloteimovel ";
				
				query =		
						session.createSQLQuery(consulta)
							.addScalar("inscricao", Hibernate.STRING)
							.addScalar( "imov_id", Hibernate.INTEGER )
							.addScalar( "mrem_nnsequencialrota", Hibernate.INTEGER )
                            .addScalar( "mrem_nnleituraanterior", Hibernate.INTEGER )
                            .addScalar( "mrem_nnleiturahidrometro", Hibernate.INTEGER )
                            .addScalar( "ltan_id", Hibernate.INTEGER )
                            .addScalar( "mrem_tmleitura", Hibernate.TIMESTAMP )
                            .addScalar( "mrem_tmultimaalteracao", Hibernate.TIMESTAMP )
                            .addScalar( "mcpf_icemissaoconta", Hibernate.SHORT )
                            .addScalar( "motivoNaoEmissao", Hibernate.STRING )
                            .addScalar( "medido", Hibernate.BOOLEAN )
							.addScalar( "empr_id", Hibernate.INTEGER )
							.addScalar( "loca_id", Hibernate.INTEGER )
							.addScalar( "mrem_cdsetorcomercial", Hibernate.INTEGER )
							.addScalar( "mrem_nnquadra", Hibernate.INTEGER )
							.addScalar( "mrem_nnloteimovel", Hibernate.INTEGER )
							.addScalar( "mrem_nnsubloteimovel", Hibernate.INTEGER )
							.setInteger("idRota", idRota)
							.setInteger("anoMesReferencia",	anoMesReferencia);
				
				if ( indicadorContaImpressa != null ){
					query.setShort( "indicadorContaImpressa", indicadorContaImpressa );
				}
				
				if ( indicadorMedido != null ){
					query.setBoolean( "indicadorMedido", ( indicadorMedido == 1 ? Boolean.TRUE : Boolean.FALSE ) );
				}				
				
				query.setBoolean( "true", Boolean.TRUE);
				query.setBoolean( "false", Boolean.FALSE);
				  
			} else if (empresa.toUpperCase().equals("CAERN") || empresa.toUpperCase().equals("CAER") ) {
				consulta +=
					"order by " +
					"  mre.greg_id," +
					"  mre.loca_id, " +
					"  mre.mrem_cdsetorcomercial, " +
					"  mre.mrem_cdrota, " +
					"  mre.mrem_nnsequencialrota, " +
					"  mre.mrem_nnloteimovel, " +
					"  mre.mrem_nnsubloteimovel ";
				
					query = 
						session.createSQLQuery(consulta)
							.addScalar("inscricao", Hibernate.STRING)
							.addScalar( "imov_id", Hibernate.INTEGER )
							.addScalar( "mrem_nnsequencialrota", Hibernate.INTEGER )
                            .addScalar( "mrem_nnleituraanterior", Hibernate.INTEGER )
                            .addScalar( "mrem_nnleiturahidrometro", Hibernate.INTEGER )
                            .addScalar( "ltan_id", Hibernate.INTEGER )
                            .addScalar( "mrem_tmleitura", Hibernate.TIMESTAMP )
                            .addScalar( "mrem_tmultimaalteracao", Hibernate.TIMESTAMP )
                            .addScalar( "mcpf_icemissaoconta", Hibernate.SHORT )
                            .addScalar( "motivoNaoEmissao", Hibernate.STRING )
                            .addScalar( "medido", Hibernate.BOOLEAN )
							.addScalar( "greg_id", Hibernate.INTEGER )
							.addScalar( "loca_id", Hibernate.INTEGER )
							.addScalar( "mrem_cdsetorcomercial", Hibernate.INTEGER )
							.addScalar( "mrem_cdrota", Hibernate.INTEGER )
							.addScalar( "mrem_nnsequencialrota", Hibernate.INTEGER )
							.addScalar( "mrem_nnloteimovel", Hibernate.INTEGER )							
							.addScalar( "mrem_nnsubloteimovel", Hibernate.INTEGER )
							.setInteger("idRota", idRota)
							.setInteger("anoMesReferencia",	anoMesReferencia);
					
					if ( indicadorContaImpressa != null ){
						query.setShort( "indicadorContaImpressa", indicadorContaImpressa );
					}
					
					if ( indicadorMedido != null ){
						query.setBoolean( "indicadorMedido", ( indicadorMedido == 1 ? Boolean.TRUE : Boolean.FALSE ) );
					}				
					
					retorno = ( Collection<Object[]>)query.list();  
			} else {
				/*
				 * TODO : COSANPA
				 * 
				 * Acréscimo de uma condição para empresas que seja diferente da CAERN e COMPESA
				 * Igual ao da CAERN
				 */
				consulta +=
					"order by " +
					"  mre.greg_id," +
					"  mre.loca_id, " +
					"  mre.mrem_cdsetorcomercial, " +
					"  mre.mrem_cdrota, " +
					"  mre.mrem_nnsequencialrota, " +
					"  mre.mrem_nnloteimovel, " +
					"  mre.mrem_nnsubloteimovel ";
				
					query = 
						session.createSQLQuery(consulta)
							.addScalar("inscricao", Hibernate.STRING)
							.addScalar( "imov_id", Hibernate.INTEGER )
							.addScalar( "mrem_nnsequencialrota", Hibernate.INTEGER )
                            .addScalar( "mrem_nnleituraanterior", Hibernate.INTEGER )
                            .addScalar( "mrem_nnleiturahidrometro", Hibernate.INTEGER )
                            .addScalar( "ltan_id", Hibernate.INTEGER )
                            .addScalar( "mrem_tmleitura", Hibernate.TIMESTAMP )
                            .addScalar( "mrem_tmultimaalteracao", Hibernate.TIMESTAMP )
                            .addScalar( "mcpf_icemissaoconta", Hibernate.SHORT )
                            .addScalar( "motivoNaoEmissao", Hibernate.STRING )
                            .addScalar( "medido", Hibernate.BOOLEAN )
							.addScalar( "greg_id", Hibernate.INTEGER )
							.addScalar( "loca_id", Hibernate.INTEGER )
							.addScalar( "mrem_cdsetorcomercial", Hibernate.INTEGER )
							.addScalar( "mrem_cdrota", Hibernate.INTEGER )
							.addScalar( "mrem_nnsequencialrota", Hibernate.INTEGER )
							.addScalar( "mrem_nnloteimovel", Hibernate.INTEGER )							
							.addScalar( "mrem_nnsubloteimovel", Hibernate.INTEGER )
							.setInteger("idRota", idRota)
							.setInteger("anoMesReferencia",	anoMesReferencia);
			
				if ( indicadorContaImpressa != null ){
					query.setShort( "indicadorContaImpressa", indicadorContaImpressa );
				}
				
				if ( indicadorMedido != null ){
					query.setBoolean( "indicadorMedido", ( indicadorMedido == 1 ? Boolean.TRUE : Boolean.FALSE ) );
				}				
				
				retorno = ( Collection<Object[]>)query.list();
				
			}
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0933] Alterar Leiturista do Arquivo Texto para Leitura
	 * 
	 * @author Tiago Nascimento, Rômulo Aurélio
	 * @Data ??/??/????,  27/10/2010
	 *
	 */
	public void atualizarLeituristaMovimentoRoteiroEmpresa(Integer idGrupoFaturamento, Integer anoMesReferencia, 
			Leiturista  leiturista, ArquivoTextoRoteiroEmpresa arq)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {
			String sql = "update MovimentoRoteiroEmpresa "
					+ " set leit_id = :leiturista "
					+ ", mrem_tmultimaalteracao = :data "
					+ " where faturamentoGrupo = :idGrupoFaturamento " 
					+ " and mrem_ammovimento = :anoMesReferencia and "
					+ " rota_id = :idRota ";

			session.createQuery(sql).setTimestamp("data", new Date())
									.setInteger("leiturista", leiturista.getId())
									.setInteger("idGrupoFaturamento", idGrupoFaturamento)
									.setInteger("anoMesReferencia", anoMesReferencia)
									.setInteger("idRota", arq.getRota().getId())									
									.executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}


	/**
	 * [UC1004] Processar Leitura via Telemetria 
	 *
	 * @author Raphael Rossiter
	 * @date 27/09/2010
	 *
	 * @param telemetriaLog
	 * @throws ErroRepositorioException
	 */
	public void atualizarTelemtriaLogMovimentoRejeitado(TelemetriaLog telemetriaLog) throws ErroRepositorioException {
	
		Session session = HibernateUtil.getSession();
		
		try {
			String sql = "update gcom.micromedicao.TelemetriaLog "
					+ " set trmt_id = :idTelemetriaRetMot where tlog_id = :idTelemetriaLog";
		
			session.createQuery(sql)
			.setInteger("idTelemetriaRetMot", telemetriaLog.getTelemetriaRetMot().getId())
			.setInteger("idTelemetriaLog", telemetriaLog.getId()).executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	
	/**
	 * 
	 * Verifica se uma rota em questão está com
	 * os arquivos de impressão simultanea divididos com a 
	 * situação diferente da situação enviada como parâmetro 
	 * 
	 * @autor Sávio Luiz.
	 * @date 30/09/2010 
	 * 
	 * @param idRota - Id da rota a ser pesquisada
	 * 
	 * @return boolean - A rota está dividida 
	 */
	public boolean verificarExistenciaArquivosDivididosSituacaoDiferente( Integer idRota, Integer anoMesFaturamento, Integer[] idsSituacaoTransmissao ) 
		throws ErroRepositorioException{
		Integer quantidade = null;

		Session session = HibernateUtil.getSession();
		String consulta;
				
		try {
			consulta = 
				"select " +
				"  count( atred.tred_id ) as quantidade " +
				"from micromedicao.arq_txt_roteiro_emp_div atred " +
				"  inner join micromedicao.arquivo_texto_rot_empr atre on ( atre.txre_id = atred.txre_id ) " +
				"where " +
				"  atre.rota_id = :idRota and " +
				"  atre.txre_amreferencia = :anoMesFaturamento and "+
				"  atred.sitl_id not in (:idsSituacaoTransmissao)";
			
			quantidade = (Integer)session.createSQLQuery(consulta)
				.addScalar("quantidade", Hibernate.INTEGER)
				.setInteger("idRota", idRota)
				.setParameterList("idsSituacaoTransmissao",idsSituacaoTransmissao)
				.setInteger("anoMesFaturamento", anoMesFaturamento )
				.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return ( quantidade == null ) || ( quantidade > 0 );

	}

	
	/**
 	 * [UC1070] Filtrar Leituras Telemetria
 	 * 
 	 * @author Hugo Amorim
 	 * @date 27/09/2010
 	 * 
 	 */
	public Collection<TelemetriaMovReg> filtrarLeiturasTelemetria(
			FiltrarLeiturasTelemetriaHelper helper)throws ErroRepositorioException{
		Collection<TelemetriaMovReg> retorno = null;

		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta = "";

		try {
			 consulta = "select telemetriaMovReg from TelemetriaMovReg telemetriaMovReg"
				 +" inner join fetch telemetriaMovReg.telemetriaMov telemetriaMov"
				 +" where telemetriaMov.indicadorConsistenciaLeit = :indicadorConsistenciaLeit and ";
			 
			 	parameters.put("indicadorConsistenciaLeit", ConstantesSistema.SIM);
				
				if(helper.getSituacaoLeitura()!=null && !helper.getSituacaoLeitura().equals("3")){
					consulta+=" telemetriaMovReg.indicadorProcessado = :indicadorProcessado and ";
					parameters.put("indicadorProcessado", Short.parseShort(helper.getSituacaoLeitura()));
				}
			 				 
				if(helper.getPeriodoEnvioInicial()!=null && helper.getPeriodoEnvioFinal()!=null){
					consulta+=" to_date(to_char(tmov_tmenvio,'YYYY/MM/DD'),'YYYY/MM/DD') between :periodoEnvioInicial and :periodoEnvioFinal and ";
					parameters.put("periodoEnvioInicial", helper.getPeriodoEnvioInicial());
					parameters.put("periodoEnvioFinal", helper.getPeriodoEnvioFinal());
				}
				
				if(helper.getPeriodoLeituraInicial()!=null && helper.getPeriodoLeituraFinal()!=null){
					consulta+=" to_date(to_char(tmrg_tmleitura,'YYYY/MM/DD'),'YYYY/MM/DD') between :periodoLeituraInicial and :periodoLeituraFinal and ";
					parameters.put("periodoLeituraInicial", helper.getPeriodoLeituraInicial());
					parameters.put("periodoLeituraFinal", helper.getPeriodoLeituraFinal());
				}
				
				// Remove o ultimo "AND "
				consulta = Util.removerUltimosCaracteres(consulta, 4);
				
				consulta+=" ORDER BY telemetriaMov.envio desc,telemetriaMovReg.imovel ";
			
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
				
				if(helper.getNumeroPagina()!=null){
					
					retorno = query.setFirstResult(10 * helper.getNumeroPagina()).setMaxResults(10).list();
					
				}else{
					
					retorno = query.list();
					
				}
				
				
					
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
 	 * [UC1070] Filtrar Leituras Telemetria
 	 * 
 	 * @author Hugo Amorim
 	 * @date 27/09/2010
 	 * 
 	 */
	public Integer countFiltrarLeiturasTelemetria(
			FiltrarLeiturasTelemetriaHelper helper)throws ErroRepositorioException{
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta = "";

		try {
			 consulta = "select count(*) from TelemetriaMovReg telemetriaMovReg"
				 +" inner join telemetriaMovReg.telemetriaMov telemetriaMov"
				 +" where telemetriaMov.indicadorConsistenciaLeit = :indicadorConsistenciaLeit and ";
			 
			 	parameters.put("indicadorConsistenciaLeit", ConstantesSistema.SIM);
				
				if(helper.getSituacaoLeitura()!=null && !helper.getSituacaoLeitura().equals("3")){
					consulta+=" telemetriaMovReg.indicadorProcessado = :indicadorProcessado and ";
					parameters.put("indicadorProcessado", Short.parseShort(helper.getSituacaoLeitura()));
				}
			 				 
				if(helper.getPeriodoEnvioInicial()!=null && helper.getPeriodoEnvioFinal()!=null){
					consulta+=" to_date(to_char(tmov_tmenvio,'YYYY/MM/DD'),'YYYY/MM/DD') between :periodoEnvioInicial and :periodoEnvioFinal and ";
					parameters.put("periodoEnvioInicial", helper.getPeriodoEnvioInicial());
					parameters.put("periodoEnvioFinal", helper.getPeriodoEnvioFinal());
				}
				
				if(helper.getPeriodoLeituraInicial()!=null && helper.getPeriodoLeituraFinal()!=null){
					consulta+=" to_date(to_char(tmrg_tmleitura,'YYYY/MM/DD'),'YYYY/MM/DD') between :periodoLeituraInicial and :periodoLeituraFinal and ";
					parameters.put("periodoLeituraInicial", helper.getPeriodoLeituraInicial());
					parameters.put("periodoLeituraFinal", helper.getPeriodoLeituraFinal());
				}
				
				// Remove o ultimo "AND "
				consulta = Util.removerUltimosCaracteres(consulta, 4);
				
				//consulta+=" ORDER BY telemetriaMov.envio desc,telemetriaMovReg.imovel ";
			
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
				
				retorno = (Integer) query.setMaxResults(1).uniqueResult();
					
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
 	 * [UC1070] Filtrar Leituras Telemetria
 	 * 
 	 * @author Hugo Amorim
 	 * @date 27/09/2010
 	 * 
 	 */
	public boolean verificarLeiturasTelemetriaNaoProcessadas(
			FiltrarLeiturasTelemetriaHelper helper)throws ErroRepositorioException{
		Integer quantidade = 0;
		boolean retorno = false;
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta = "";

		try {
			 consulta = "select SUM(CASE WHEN telemetriaMovReg.indicadorProcessado = 1 THEN 0 ELSE 1 END) from TelemetriaMovReg telemetriaMovReg"
				 +" inner join telemetriaMovReg.telemetriaMov telemetriaMov"
				 +" where telemetriaMov.indicadorConsistenciaLeit = :indicadorConsistenciaLeit and ";
				// +" and telemetriaMovReg.indicadorProcessado = :indicadorProcessado and ";
			 
			 	parameters.put("indicadorConsistenciaLeit", ConstantesSistema.SIM);
			 	//parameters.put("indicadorProcessado", ConstantesSistema.NAO);
				
				if(helper.getSituacaoLeitura()!=null && !helper.getSituacaoLeitura().equals("3")){
					consulta+=" telemetriaMovReg.indicadorProcessado = :indicadorProcessado and ";
					parameters.put("indicadorProcessado", Short.parseShort(helper.getSituacaoLeitura()));
				}
			 				 
				if(helper.getPeriodoEnvioInicial()!=null && helper.getPeriodoEnvioFinal()!=null){
					consulta+=" to_date(to_char(tmov_tmenvio,'YYYY/MM/DD'),'YYYY/MM/DD') between :periodoEnvioInicial and :periodoEnvioFinal and ";
					parameters.put("periodoEnvioInicial", helper.getPeriodoEnvioInicial());
					parameters.put("periodoEnvioFinal", helper.getPeriodoEnvioFinal());
				}
				
				if(helper.getPeriodoLeituraInicial()!=null && helper.getPeriodoLeituraFinal()!=null){
					consulta+=" to_date(to_char(tmrg_tmleitura,'YYYY/MM/DD'),'YYYY/MM/DD') between :periodoLeituraInicial and :periodoLeituraFinal and ";
					parameters.put("periodoLeituraInicial", helper.getPeriodoLeituraInicial());
					parameters.put("periodoLeituraFinal", helper.getPeriodoLeituraFinal());
				}
				
				// Remove o ultimo "AND "
				consulta = Util.removerUltimosCaracteres(consulta, 4);
				
				//consulta+=" ORDER BY telemetriaMov.envio desc,telemetriaMovReg.imovel ";
			
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
				
				quantidade = (Integer) query.setMaxResults(1).uniqueResult();
				
				if(quantidade>0){
					retorno = true;
				}
					
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
 	 * [UC1070] Filtrar Leituras Telemetria
 	 * 
 	 * @author Hugo Amorim
 	 * @date 27/09/2010
 	 * 
 	 */
	public boolean verificarGruposDiferentesLeiturasTelemetria(
			String[] ids)throws ErroRepositorioException{
		Integer quantidade = 0;
		boolean retorno = false;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			 consulta = "select count(distinct faturamentoGrupo.id) from TelemetriaMovReg telemetriaMovReg"
				 +" inner join telemetriaMovReg.imovel imovel"
				 +" inner join imovel.quadra quadra"
				 +" inner join quadra.rota rota"
				 +" inner join rota.faturamentoGrupo faturamentoGrupo"
				 +" where telemetriaMovReg.id in ( :ids )";
			
				quantidade = (Integer) 
					session.createQuery(consulta)
					.setParameterList("ids", ids)
					.setMaxResults(1)
					.uniqueResult();
					
				if(quantidade>1){
					retorno = true;
				}
					
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
 	 * [UC1070] Filtrar Leituras Telemetria
 	 * 
 	 * @author Hugo Amorim
 	 * @date 27/09/2010
 	 * 
 	 */
	public Collection<TelemetriaMovReg> perquisarLeiturasTelemetriaPorId(
			String[] ids)throws ErroRepositorioException{
		Collection<TelemetriaMovReg> retorno;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			 consulta = "from TelemetriaMovReg telemetriaMovReg"
				+" where telemetriaMovReg.id in ( :ids )";
			
			 retorno = session.createQuery(consulta)
					.setParameterList("ids", ids)
					.list();
					
					
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * 
	 * UC1027 - Exibir Consultar Arquivo Texto Leitura Divisão.
	 * 
	 * Avalia se existe algum arquivo dividido liberado para o leiturista do arquivo
	 * passado no parametro.
	 * 
	 * @autor Sávio Luiz
	 * @date 04/10/2010
	 * 
	 * @param idArquivo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] temArquivoTextoDivididoLiberado(Integer idLeiturista)
			throws ErroRepositorioException {
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			StringBuffer hql = new StringBuffer(
					"SELECT at.anoMesReferencia,at.faturamentoGrupo.id " +
					"FROM ArquivoTextoRoteiroEmpresaDivisao a " +
					"INNER JOIN a.arquivoTextoRoteiroEmpresa at " +
					"where a.situacaoTransmissaoLeitura.id = 2");
			hql
					.append(" and a.leiturista.id = ");
			hql.append(idLeiturista);

			Collection arqs = session.createQuery(hql.toString()).list();

			if (arqs != null && !arqs.isEmpty()) {
				retorno = (Object[])Util.retonarObjetoDeColecao(arqs);
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}
	
	/**
	 * 
	 * UC1027 - Exibir Consultar Arquivo Texto Leitura Divisão.
	 * 
	 * Avalia se existe algum arquivo dividido liberado para o leiturista do arquivo
	 * passado no parametro.
	 * 
	 * @autor Sávio Luiz
	 * @date 04/10/2010
	 * 
	 * @param idArquivo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] temArquivoTextoLiberado(Integer idLeiturista)
			throws ErroRepositorioException {
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			StringBuffer hql = new StringBuffer(
					"SELECT a.anoMesReferencia,a.faturamentoGrupo.id FROM ArquivoTextoRoteiroEmpresa a " +
					"where a.situacaoTransmissaoLeitura.id = 2 and a.numeroImei is not null ");
			hql
					.append(" and a.leiturista.id = ");
			hql.append(idLeiturista);

			Collection arqs = session.createQuery(hql.toString()).list();

			if (arqs != null && !arqs.isEmpty()) {
				retorno = (Object[])Util.retonarObjetoDeColecao(arqs);
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}
	
	/**
	 * 
	 * UC1027 - Exibir Consultar Arquivo Texto Leitura Divisão.
	 * 
	 * Pesquisa o leiturista do arquivo que será liberado.
	 * 
	 * @autor Sávio Luiz
	 * @date 04/10/2010
	 * 
	 * @param idArquivo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer recuperaLeituristaArquivoTexto(Integer idArquivo,boolean arquivoDividido)
			throws ErroRepositorioException{
	
			Integer idLeiturista = null;
			Session session = HibernateUtil.getSession();
			try {
				StringBuffer hql = null;
				// caso o arquivo texto seja dividido, então consulta o leiturista pelo arquivo dividido
				if(arquivoDividido){
					hql = new StringBuffer(
						"select a2.leiturista.id from ArquivoTextoRoteiroEmpresaDivisao a2 where a2.id =");
				}else{
					hql = new StringBuffer(
					"select a2.leiturista.id from ArquivoTextoRoteiroEmpresa a2 where a2.id =");
				}
				hql.append(idArquivo);
				
				idLeiturista = (Integer)session.createQuery(hql.toString()).setMaxResults(1).uniqueResult();


			} catch (HibernateException e) {
				// levanta a exceção para a próxima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				// fecha a sessão
				HibernateUtil.closeSession(session);
			}

			return idLeiturista;
	}
	

	
	/**
	 * 
	 * [UC0091] Alterar Dados para Faturamento
	 * 
	 * 	[FS0015]  Verificar Imóvel Impressão Simultânea
	 * 
	 * @author Rômulo Aurélio
	 * @date 08/11/2010
	 */
	public boolean verificarExistenciaArquivoDeImpressaoRotaAlternativa(Integer idImovel,
			Integer tipoMedicao)throws ErroRepositorioException{
		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// Retorno Consulta
		boolean retorno = false;

		// Cria a variável que vai conter o hql
		String consulta;

		try {
						
			
			consulta =
					  " SELECT mre.id "
					+ " FROM MovimentoRoteiroEmpresa mre "
					+ " INNER JOIN mre.imovel i" 
					+ " INNER JOIN i.rotaAlternativa r" 
					+ " INNER JOIN r.faturamentoGrupo fg"
					+ " WHERE fg.anoMesReferencia = mre.anoMesMovimento" 
					+ " AND mre.medicaoTipo = :medicaoTipo and mre.imovel = :idImovel" ;				
					
					
			Integer idRetorno 
				=  (Integer) session
				.createQuery(consulta)
				.setInteger("medicaoTipo",tipoMedicao)
				.setInteger("idImovel",idImovel)
				.setMaxResults(1).uniqueResult();
			
			if(idRetorno!=null){
				retorno = true;
			}

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}


	/**
	 * [UCXXXX] - Obter Volume Médio Água ou Esgoto
	 * 
	 * @author Ivan Sergio
	 * @data 13/12/2010
	 * 
	 * @param idImovel
	 * @param amReferenciaInicial
	 * @param amReferenciaFinal
	 * @param idLigacaoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterVolumeMedioAguaOuEsgoto(
			Integer idImovel, 
			Integer amReferenciaInicial, 
			Integer amReferenciaFinal, 
			Integer idLigacaoTipo) throws ErroRepositorioException {
		
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String hql = 
				"select ch.referenciaFaturamento, ch.numeroConsumoCalculoMedia " +
				"from ConsumoHistorico ch " +
				"inner join ch.consumoTipo ct " +
				"left join ch.consumoAnormalidade ca " +
				"	with ca.indicadorCalcularMedia = 1 " +
				"where ch.imovel.id = :idImovel " +
				"and ch.ligacaoTipo.id = :idLigacao " +
				"and ch.referenciaFaturamento between :amReferenciaInicial and :amReferenciaFinal " +
				"and ct.indicadorCalculoMedia = 1 " +
				"order by ch.referenciaFaturamento desc";
			
			retorno = session.createQuery(hql)
				.setInteger("idImovel", idImovel)
				.setInteger("idLigacao", idLigacaoTipo)
				.setInteger("amReferenciaInicial", amReferenciaInicial)
				.setInteger("amReferenciaFinal", amReferenciaFinal)
				.list();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	 /**
	 * 
	 * Obter rota do imóvel através do código da rota
	 * do sequencial e da localidade .
	 * 
	 * @author Sávio Luiz
	 * @date 13/08/2010
	 * 
	 * @param codRota, sequencial, localidade
	 * @throws ErroRepositorioException
	 */
	public Integer obterIdRotaDeMovimentoRotEmpresaPelaLocalidade(Integer codRota, Integer setorComercial, Integer localidade)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
		
			consulta = 
				"SELECT " +
				"rot.id " +
				"FROM MovimentoRoteiroEmpresa movRotEmpresa " +
				"INNER JOIN movRotEmpresa.rota rot "+
				"INNER JOIN movRotEmpresa.faturamentoGrupo ft " +
				"where " +
				"movRotEmpresa.localidade.id = :localidade and " +
				"movRotEmpresa.codigoSetorComercial = :setorComercial and " +
				"movRotEmpresa.codigoRota = :codRota and " +
				"ft.anoMesReferencia = movRotEmpresa.anoMesMovimento ";
			

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("localidade", localidade)
					.setInteger("setorComercial", setorComercial)
					.setInteger("codRota", codRota).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * 
	 * [UC-1127] - Gerar RA e OS para Anormalidade Consumo
	 * Passo 2
	 * 
	 * @param anoMesFaturamento
	 * @param colRotas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection selecionarImoveisAnormalidadeConsumoGeradoraRA( int anoMesFaturamento, Rota rota ) 
			throws ErroRepositorioException{	
	
		Session session = HibernateUtil.getSession();
	
		Collection retorno = null;
		
		try {
	
			String sql = 
				"select \n" +
				"  ch.imov_id, \n" +
				"  imo.iper_id, \n" +
				"  ch.csan_id, \n" +
				"  imo.loca_id, \n" +
				"  imo.stcm_id, \n" +
				"  imo.qdra_id, \n" +
				"  imo.IMOV_NNCOORDENADAY, \n" +
				"  imo.IMOV_NNCOORDENADAX \n" +
				"from \n" +
				"  micromedicao.CONSUMO_HISTORICO ch \n" +
				"  inner join micromedicao.CONSUMO_ANORM_ACAO caa on ( ch.CSAN_ID = caa.CSAN_ID and ( caa.STEP_IDMES1 is not null or caa.STEP_IDMES2 is not null or caa.STEP_IDMES3 is not null ) ) \n" +
				"  inner join cadastro.imovel imo on ( imo.imov_id = ch.imov_id ) \n" +
				"  inner join cadastro.quadra qua on ( qua.qdra_id = imo.qdra_id ) \n" +
				"where \n" +
				"  ch.CSHI_AMFATURAMENTO = " + anoMesFaturamento + " and \n " +
				"  ch.lgti_id = " + LigacaoTipo.LIGACAO_AGUA + " and \n " +
				"  ( qua.ROTA_ID = " + rota.getId() + " or imo.ROTA_IDALTERNATIVA = " + rota.getId() + " ) \n";
	
			Collection colecaoConsulta = session.createSQLQuery(sql)
					.addScalar("imov_id", Hibernate.INTEGER)
					.addScalar("iper_id", Hibernate.INTEGER)
					.addScalar("csan_id", Hibernate.INTEGER)					
					.addScalar("loca_id", Hibernate.INTEGER)
					.addScalar("stcm_id", Hibernate.INTEGER)
					.addScalar("qdra_id", Hibernate.INTEGER)
					.addScalar("IMOV_NNCOORDENADAY", Hibernate.BIG_DECIMAL)
					.addScalar("IMOV_NNCOORDENADAX", Hibernate.BIG_DECIMAL)
					.list();
	
			retorno = colecaoConsulta;
	
		} catch (HibernateException e) {	
			throw new ErroRepositorioException(e, "Erro no Hibernate");	
		} finally {
			HibernateUtil.closeSession(session);	
		}
	
		return retorno;
	}
	
	/**
	 * [UC-1127] - Gerar RA e OS para Anormalidade Consumo
	 * 
	 * Pesquisamos a existencia de Consumo Anormalidade Acao para o imovel informado
	 * Corresponde ao passo 3 e ao FS0004 do UC
	 * 
	 * @author Bruno Barros
	 * @date 16/02/2011
	 * 
	 * @param idConsumoAnormalidadeAcao
	 * @param idCategoria
	 * @param idPerfilImovel
	 * @return
	 * @throws ControladorException
	 */	
	public Collection
		verificarExistenciaConsumoAnormalidadeAcaoImovel(
				Integer idConsumoAnormalidadeAcao,
				Integer idCategoria,
				Integer idPerfilImovel ) 
		throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		
		Collection retorno = null;
		
		try {
	
			String sql = 
				"select " +
				"  caa.CSAN_ID, " +
				"  caa.STEP_IDMES1, " +
				"  caa.STEP_IDMES2, " +
				"  caa.STEP_IDMES3 " +
				"from " +
				"  micromedicao.CONSUMO_ANORM_ACAO caa " +
				"  inner join micromedicao.CONSUMO_HISTORICO ch on ( caa.CSAN_ID = ch.CSAN_ID ) " +
				"  inner join cadastro.imovel imo on ( imo.IMOV_ID = ch.IMOV_ID ) " +
				"where " +
				"  ( caa.STEP_IDMES1 is not null or caa.STEP_IDMES2 is not null or caa.STEP_IDMES3 is not null ) and " +
				"  ch.lgti_id = " + LigacaoTipo.LIGACAO_AGUA + " \n ";
			
			if ( idConsumoAnormalidadeAcao != null ){
				sql += "  and caa.CSAN_ID = " + idConsumoAnormalidadeAcao;
			} else {
				sql += "  and caa.CSAN_ID is null ";
			}
			
			if ( idCategoria != null ){
				sql += "  and caa.CATG_ID = " + idCategoria;
			} else {
				sql += "  and caa.CATG_ID is null ";
			}
			
			if ( idCategoria != null ){
				sql += "  and caa.IPER_ID = " + idPerfilImovel;
			} else {
				sql += "  and caa.IPER_ID is null";
			}
			
			retorno = (Collection) session.createSQLQuery(sql)
					.addScalar("CSAN_ID", Hibernate.INTEGER)
					.addScalar("STEP_IDMES1", Hibernate.INTEGER)
					.addScalar("STEP_IDMES2", Hibernate.INTEGER)
					.addScalar("STEP_IDMES3", Hibernate.INTEGER)
					.list();
	
	
		} catch (HibernateException e) {	
			throw new ErroRepositorioException(e, "Erro no Hibernate");	
		} finally {
			HibernateUtil.closeSession(session);	
		}
	
		return retorno;
	}
	
	/**
	 * [UC-1127] - Gerar RA e OS para Anormalidade Consumo
	 * 
	 * atendimentopublico.LOCALID_SOLIC_TIPO_GRUPO para LOCA_ID=LOCA_ID 
	 * da tabela cadastro.IMOVEL para IMOV_ID=IMOV_ID da tabela 
	 * micromedicao.CONSUMO_HISTORICO e SOTG_ID=SOTG_ID da tabela 
	 * atendimentopublico.SOLICITACAO_TIPO para SOTP_ID=SOTP_ID da 
	 * tabela atendimentopublico.SOLICITACAO_TIPO_ESPEC para 
	 * STEP_ID=Especificação do Tipo de Solicitação
	 * 
	 * @author Bruno Barros
	 * @date 16/02/2011
	 * 
	 * @param idLocalidade
	 * @param idSolicitacaoTipoGrupo
	 * 
	 * @return
	 * @throws ControladorException
	 */	
	public UnidadeOrganizacional
		unidadeDestinoAssociadaLocalidadeImovelGrupoTipoSolicitacaoEspecificacao(
				Integer idLocalidade,
				Integer idSolicitacaoTipoGrupo ) 
			throws ErroRepositorioException{	
	
		UnidadeOrganizacional retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
		
			consulta = 
				"select " +
				"  uo " +
				"from " +
				"  gcom.atendimentopublico.registroatendimento.LocalidadeSolicTipoGrupo lstg, " +
				"  UnidadeOrganizacional uo " +
				"  left join fetch uo.unidadeCentralizadora " +
				"where " +
				"  lstg.localidade.id = :idLocalidade and " +
				"  lstg.unidadeOrganizacional.id = uo.id and " +
				"  lstg.solicitacaoTipoGrupo.id = :idSolicitacaoTipoGrupo ";
			
			retorno = (UnidadeOrganizacional) session.createQuery(consulta)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idSolicitacaoTipoGrupo", idSolicitacaoTipoGrupo)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}
	
	/**
	 * 
	 * Recuperar os ids dos imoveis vinculados a um determinado condominio
	 * 
	 * @author Tiago Moreno
	 * @date 22/02/2010
	 * 
	 * @param idImovelCondominio
	 * @throws ErroRepositorioException
	 */
	
	public Collection recuperarImoveisVinculadosAoCondominio(
			Integer idImovelCondominio) throws ErroRepositorioException {

		Collection retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			// gerando o hql dos ids do Imoveis
			consulta = 
				"select " + 
				"  imov.id " +
				"from " + 
				"  Imovel imov " + // imovel
				"  left join imov.imovelCondominio imovelCondominio " + // imovel condominio
			    "where " +
			    "  imovelCondominio.id = :idImovelCondominio ";

			retorno = session.createQuery(consulta).setInteger(
					"idImovelCondominio", idImovelCondominio).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * 
	 * [F0011] Verifica a existenica de imovel micro em processo de faturamento
	 * 
	 * @author Tiago Moreno
	 * @date 22/02/2010
	 * 
	 * @param idImoveis, anoMes
	 * @throws ErroRepositorioException
	 */
	
	public Collection verificarImoveisCicloFaturamento(
			Collection idImoveis, Integer anoMes) throws ErroRepositorioException {

		Collection retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			// gerando o hql dos ids do Imoveis
			consulta = 
				"select " + 
				"  cnta.id " +
				"from " + 
				"  Conta cnta " + // conta
				"  inner join cnta.imovel imov " + // imovel
				"  inner join cnta.faturamentoGrupo ftgr " + // faturamentoGrupo
				"  inner join cnta.debitoCreditoSituacaoAtual dcst " + // debito credito situacao atual
			    "where " +
			    "  imov.id in (:idImoveis) and dcst.id = 9 and cnta.referencia = :anoMes";

			retorno = session.createQuery(consulta).setParameterList(
					"idImoveis", idImoveis).setInteger("anoMes", anoMes).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	
	
	/**
	 * Pesquisa do relatorio consultar arquivo texto leitura
	 * 
	 * @author Rafael Pinto
	 * @date 11/03/2011
	 * 
	 * @param FiltroRelatorioLeituraConsultarArquivosTextoHelper
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> consultarRelatorioLeituraConsultarArquivosTexto(
			FiltroRelatorioLeituraConsultarArquivosTextoHelper helper)
		throws ErroRepositorioException {

		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// Retorno Consulta
		Collection<Object[]> retorno = null;

		// Cria a variável que vai conter o hql
		String consulta;

		try {
			Query query = null;
			Map parameters = new HashMap();
			
			consulta = "select arquivo.anoMesReferencia , " //0
				+ "arquivo.numeroSequenciaLeitura, " //1
				+ "arquivo.faturamentoGrupo.id , " //2
				+ "rot.id," //3
				+ "rot.codigo,"//4 
				+ "arquivo.codigoSetorComercial1," //5 
				+ "arquivo.quantidadeImovel," //6
				+ "arquivo.motivoFinalizacao," //7
				+ "arquivo.ultimaAlteracao," //8
				+ "servicoTipoCel.id," //9
				+ "servicoTipoCel.descricao," //10 
				+ "local.id," //11
				+ "local.descricao," //12 
				+ "situaTransmissaoLeitura.id," //13  
				+ "situaTransmissaoLeitura.descricaoSituacao," //14 
				+ "leiturista.id," //15
				+ "cliente.nome," //16
				+ "func.nome," //17
				+ "arquivo.numeroSequenciaLeitura " //18
				+ "from ArquivoTextoRoteiroEmpresa arquivo " 
				+ "inner join arquivo.localidade local "
				+ "inner join arquivo.situacaoTransmissaoLeitura situaTransmissaoLeitura "
				+ "left join arquivo.servicoTipoCelular servicoTipoCel "
				+ "left join arquivo.rota rot "
				+ "left join arquivo.leiturista leiturista "
				+ "left join leiturista.cliente cliente "
				+ "left join leiturista.funcionario func "
				+ "where arquivo.anoMesReferencia = :anoMesReferencia "
				+ "and arquivo.empresa = :idEmpresa "
				+ "and arquivo.servicoTipoCelular = :idServicoTipo ";
			
			parameters.put("anoMesReferencia", helper.getAnoMes() );
			parameters.put("idEmpresa", helper.getIdEmpresa() );
			parameters.put("idServicoTipo", helper.getIdServicoTipoCelular() );

				
			if(helper.getIdGrupoFaturamento() != null ){
				consulta = consulta + "and arquivo.faturamentoGrupo = :idGrupoFaturamento ";
				
				parameters.put("idGrupoFaturamento", helper.getIdGrupoFaturamento() );
			}

			if(helper.getIdLeiturista() != null ){
				consulta = consulta + "and arquivo.leiturista = :idLeiturista ";
				
				parameters.put("idLeiturista", helper.getIdLeiturista() );
			}
			
			if(helper.getIdSituacaoTransmissaoLeitura() != null ){
				consulta = consulta + "and arquivo.situacaoTransmissaoLeitura = :idSituacaoTransmissaoLeitura ";
				
				parameters.put("idSituacaoTransmissaoLeitura", helper.getIdSituacaoTransmissaoLeitura());
			}
			
			if(helper.getIdServicoTipoCelular() != null ){
				consulta = consulta + "and arquivo.servicoTipoCelular = :idServicoTipoCelular ";
				
				parameters.put("idServicoTipoCelular", helper.getIdServicoTipoCelular());
			}

			if(helper.getIdLocalidade() != null ){
				consulta = consulta + "and local.id = :idLocalidade ";
				
				parameters.put("idLocalidade", helper.getIdLocalidade());
			}

			
			consulta = consulta 
				+ "order by arquivo.numeroSequenciaLeitura," +
						"arquivo.leiturista," +
						"arquivo.situacaoTransmissaoLeitura ";
			
			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}	

	
	/**
	 * Pesquisa do relatorio consultar arquivo texto leitura
	 * 
	 * @author Rafael Pinto
	 * @date 11/03/2011
	 * 
	 * @param FiltroRelatorioLeituraConsultarArquivosTextoHelper
	 * @throws ErroRepositorioException
	 */
	public Integer consultarCountRelatorioLeituraConsultarArquivosTexto(
			FiltroRelatorioLeituraConsultarArquivosTextoHelper helper)
		throws ErroRepositorioException {

		// Cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// Retorno Consulta
		Integer retorno = null;

		// Cria a variável que vai conter o hql
		String consulta;

		try {
			Query query = null;
			Map parameters = new HashMap();
			
			consulta = "select count(*) from ArquivoTextoRoteiroEmpresa arquivo " 
				+ "where arquivo.anoMesReferencia = :anoMesReferencia "
				+ "and arquivo.empresa = :idEmpresa "
				+ "and arquivo.servicoTipoCelular = :idServicoTipo ";
			
			
			
			parameters.put("anoMesReferencia", helper.getAnoMes() );
			parameters.put("idEmpresa", helper.getIdEmpresa() );
			parameters.put("idServicoTipo", helper.getIdServicoTipoCelular() );
				
			if(helper.getIdGrupoFaturamento() != null ){
				consulta = consulta + "and arquivo.faturamentoGrupo = :idGrupoFaturamento ";
				
				parameters.put("idGrupoFaturamento", helper.getIdGrupoFaturamento() );
			}

			if(helper.getIdLeiturista() != null ){
				consulta = consulta + "and arquivo.leiturista = :idLeiturista ";
				
				parameters.put("idLeiturista", helper.getIdLeiturista() );
			}
			
			if(helper.getIdSituacaoTransmissaoLeitura() != null ){
				consulta = consulta + "and arquivo.situacaoTransmissaoLeitura = :idSituacaoTransmissaoLeitura ";
				
				parameters.put("idSituacaoTransmissaoLeitura", helper.getIdSituacaoTransmissaoLeitura());
			}
			
			if(helper.getIdServicoTipoCelular() != null ){
				consulta = consulta + "and arquivo.servicoTipoCelular = :idServicoTipoCelular ";
				
				parameters.put("idServicoTipoCelular", helper.getIdServicoTipoCelular());
			}
			
			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer) query.uniqueResult();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}	
	
	
	/**
	 * selecionar os movimentos roteiros empresas.
	 * 
	 * [UC0627] Gerar Arquivo Texto para Leituristas
	 * 
	 * @author Breno Santos
	 * @date 17/03/2011
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMovimentoRoteiroEmpresa(Integer idImovel) throws ErroRepositorioException {

		Object retorno = null;
		Integer valorResultado = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select mrem.faturamentoGrupo.id " 
					+ "from MovimentoRoteiroEmpresa mrem "
					+ "where mrem.imovel.id = :idImovel " + "";

			retorno = session.createQuery(consulta).setInteger("idImovel", idImovel).setMaxResults(1).uniqueResult();

			valorResultado = (Integer) retorno;

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return valorResultado;
	}


	/**
	 * [UC0157] Simular Cálculo da Conta
	 *
	 * [SB0005] - Determinar consumo mínimo da subcategoria por pontos de utilização
	 * 
	 * Obter o consumo mínimo associado ao ponto de utilização e a subcategoria informada
	 *
	 * @author Mariana Victor
	 * @date 23/05/2011
	 * 
	 * @param numeroParametro
	 * @param anoMesReferencia
	 * @param idSubcategoria
	 * @param idCategoria
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarConsumoMinimoParametro(BigDecimal numeroParametro,
			Integer anoMesReferencia, Integer idSubcategoria,
			Integer idCategoria) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT min(cmpc.cmpc_nnconsumo) AS consumoMinimo "
				+ " FROM micromedicao.consumo_minimo_parametro cmpc "
				+ " WHERE cmpc.cmpc_nnfinparametro >= :numParametro "
				+ "   AND cmpc.cmpc_amreferencia <= :anoMes ";

			if (idSubcategoria != null) {
				consulta = consulta
						+ "AND cmpc.scat_id = :idCategoriaOUSubcategoria ";
			} else {
				consulta = consulta
						+ "AND cmpc.catg_id = :idCategoriaOUSubcategoria ";
			}
			
			consulta = consulta
						+ "ORDER BY cmpc.cmpc_amreferencia DESC, cmpc.cmpc_nnfinparametro ";
			
			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"consumoMinimo", Hibernate.INTEGER).setBigDecimal(
					"numParametro", numeroParametro).setInteger(
					"anoMes", anoMesReferencia).setInteger(
					"idCategoriaOUSubcategoria",
					idSubcategoria != null ? idSubcategoria : idCategoria)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0000] Obter Consumo Não Medido por Parâmetro
	 *
	 * Obter o consumo mínimo associado ao ponto de utilização e a subcategoria informada
	 *
	 * @author Mariana Victor
	 * @date 23/05/2011
	 * 
	 * @param pontosUtilizacao
	 * @param anoMesReferencia
	 * @param idSubcategoria
	 * @param idCategoria
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarConsumoMinimoParametro(Short pontosUtilizacao,
			Integer anoMesReferencia, Integer idSubcategoria,
			Integer idCategoria) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT cmpc.cmpc_nnconsumo AS consumoMinimo "
				+ " FROM micromedicao.consumo_minimo_parametro cmpc "
				+ " WHERE cmpc.cmpc_nnfinparametro >= :pontosUtilizacao "
				+ "   AND cmpc.cmpc_amreferencia <= :anoMes ";

			if (idSubcategoria != null) {
				consulta = consulta
						+ "AND cmpc.scat_id = :idCategoriaOUSubcategoria ";
			} else {
				consulta = consulta
						+ "AND cmpc.catg_id = :idCategoriaOUSubcategoria ";
			}
			
			consulta = consulta
						+ "ORDER BY cmpc.cmpc_amreferencia DESC, cmpc.cmpc_nnfinparametro ";
			
			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"consumoMinimo", Hibernate.INTEGER).setShort(
					"pontosUtilizacao", pontosUtilizacao).setInteger(
					"anoMes", anoMesReferencia).setInteger(
					"idCategoriaOUSubcategoria",
					idSubcategoria != null ? idSubcategoria : idCategoria)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0000] Obter Consumo Não Medido por Parâmetro
	 *
	 * 2.1.1. , 2.2.1. - Caso exista consumo mínimo da subcategoria 
	 * 
	 * @author Mariana Victor
	 * @date 23/05/2011
	 * 
	 * @param pontosUtilizacao
	 * @param anoMesReferencia
	 * @param idSubcategoria
	 * @param idCategoria
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarConsumoMinimoSubCategoria(Integer idSubcategoria) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT count(cmpc.scat_id) AS subCategoria "
				+ " FROM micromedicao.consumo_minimo_parametro cmpc "
				+ " WHERE cmpc.scat_id = :idSubcategoria ";
			
			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"subCategoria", Hibernate.INTEGER).setInteger(
					"idSubcategoria", idSubcategoria)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * <b>[UC1180] Relatório Imóveis com Leituristas</b>:
	 *
	 * <ul>
	 * 		<li> 
	 * 			<b>[SB0001] Gerar Relatório do Tipo 1</b>: Quantitativo de imóveis com leituras através da WEB
	 * 		</li>
	 * 		<li> 
	 * 			<b>[SB0002] Gerar Relatório do Tipo 2</b>: Quantitativo de imóveis sem leituras através da ISC e WEB
	 * 		</li>
	 * 		<li> 
	 * 			<b>[SB0003] Gerar Relatório do Tipo 3</b>: Quantitativo de imóveis que estão na rota mas não foram recebidos através da ISC</p>
	 * 		</li>
	 * </ul>
	 * 
	 * @author Magno Gouveia
	 * @date 06/06/2011
	 * 
	 * @param FiltrarRelatorioImoveisComLeiturasHelper 
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioImoveisComLeiturasQuantitativos(FiltrarRelatorioImoveisComLeiturasHelper helper, int parametroPersistirRelatorio) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection<Object[]> retorno = null;
		
		String consulta;
		
		boolean filtrar = true;

		try {
			consulta = "SELECT ce.empr_nmempresa as empresa, " // 0
					 + 	   "loca.loca_id as idLocalidade, " // 1
					 + 	   "loca.loca_nmlocalidade as localidade, " // 2
					 + 	   "stcm.stcm_cdsetorcomercial as codSetorComercial, " // 3
					 + 	   "stcm.stcm_nmsetorcomercial as setorComercial, " // 4
					 + 	   "m.mrem_cdrota as codRota, " // 5
					 + 	   "clie.clie_nmcliente as clienteLeiturista, " // 6
					 + 	   "func.func_nmfuncionario as funcionarioLeiturista, " // 7
					 + 	   "COUNT(DISTINCT m.imov_id) AS qtdImoveis " // 8
					 + "FROM micromedicao.movimento_roteiro_empr m "
					 + "INNER JOIN micromedicao.rota rt ON rt.rota_id = m.rota_id "
					 + "INNER JOIN cadastro.localidade loca ON loca.loca_id = m.loca_id "
					 + "INNER JOIN cadastro.setor_comercial stcm ON (stcm.stcm_cdsetorcomercial = m.mrem_cdsetorcomercial "
					 +												"AND loca.loca_id = stcm.loca_id) "
					 + "INNER JOIN cadastro.empresa ce ON ce.empr_id = rt.empr_id "
					 + "LEFT JOIN micromedicao.leiturista leit ON leit.leit_id = m.leit_id "
					 + "LEFT JOIN cadastro.cliente clie ON clie.clie_id = leit.clie_id "
					 + "LEFT JOIN cadastro.funcionario func ON func.func_id = leit.func_id "
					 + "WHERE m.mrem_ammovimento = :anoMes "
					 +     "AND m.ftgr_id = :grupoFaturamento ";
			
			switch (parametroPersistirRelatorio) {
				case Relatorio.GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_1:
					 consulta+= "AND (m.mrem_nnleiturahidrometro IS NOT NULL "
							 +          "OR m.ltan_id IS NOT NULL) "
							 +     "AND m.mrem_tmleitura IS NOT NULL "
							 +     "AND NOT EXISTS "
							 +         "(SELECT mcp.imov_id "
							 +          "FROM faturamento.mov_conta_prefaturada mcp "
							 +          "WHERE mcp.mcpf_ammovimento = m.mrem_ammovimento "
							 +              "AND mcp.imov_id = m.imov_id) ";
					break;
				case Relatorio.GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_2:
					consulta += "AND m.mrem_nnleiturahidrometro IS NULL "
							 +	"AND m.ltan_id IS NULL "
							 +  "AND m.mrem_tmleitura IS NULL "
							 +	"AND NOT EXISTS "
						     +   	"(SELECT mcp.imov_id "
						     +		 "FROM faturamento.mov_conta_prefaturada mcp "
						     +		 "WHERE mcp.mcpf_ammovimento = m.mrem_ammovimento "
						     +		 	"AND mcp.imov_id = m.imov_id) ";
					break;
				case Relatorio.GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_3:
					// Inicia a consulta novamente
					consulta = this.criarConsultaRelatorioImoveisComLeiturasQuantitativosTipo3(helper);
					filtrar = false;
					break;
				default:
				break;
			}
			
			if(filtrar) {
				if(Util.verificarIdNaoVazio(String.valueOf(helper.getEmpresa()))){
					consulta += "AND ce.empr_id = :empresa ";
				}
				
				if(Util.verificarIdNaoVazio(String.valueOf(helper.getLeiturista()))){
					consulta += "AND leit.leit_id = :leiturista ";
				}
				
				if(Util.verificarIdNaoVazio(String.valueOf(helper.getLocalidadeInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getLocalidadeFinal()))){
					consulta += "AND loca.loca_id BETWEEN :localidadeInicial AND :localidadeFinal ";
				}
				
				if(Util.verificarIdNaoVazio(String.valueOf(helper.getSetorComercialInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getSetorComercialFinal()))){
					consulta += "AND stcm.stcm_cdsetorcomercial BETWEEN :setorInicial AND :setorFinal ";
				}
				
				if(Util.verificarIdNaoVazio(String.valueOf(helper.getRotaInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getRotaFinal()))){
					consulta += "AND rt.rota_cdrota BETWEEN :rotaInicial AND :rotaFinal ";
				}
				
				consulta += "GROUP BY ce.empr_nmempresa, loca.loca_id, loca.loca_nmlocalidade, stcm.stcm_cdsetorcomercial, stcm.stcm_nmsetorcomercial, m.mrem_cdrota, clie.clie_nmcliente, func.func_nmfuncionario "
						  + "ORDER BY ce.empr_nmempresa, loca.loca_id, loca.loca_nmlocalidade, stcm.stcm_cdsetorcomercial, stcm.stcm_nmsetorcomercial, m.mrem_cdrota, clie.clie_nmcliente, func.func_nmfuncionario";
			}
			
			Query query = session.createSQLQuery(consulta)
										.addScalar("empresa", Hibernate.STRING)
										.addScalar("idLocalidade", Hibernate.INTEGER)
										.addScalar("localidade", Hibernate.STRING)
										.addScalar("codSetorComercial", Hibernate.INTEGER)
										.addScalar("setorComercial", Hibernate.STRING)
										.addScalar("codRota", Hibernate.INTEGER)
										.addScalar("clienteLeiturista", Hibernate.STRING)
										.addScalar("funcionarioLeiturista", Hibernate.STRING)
										.addScalar("qtdImoveis", Hibernate.INTEGER)
										.setInteger("anoMes", Integer.parseInt(helper.getMesAnoReferencia()))
										.setInteger("grupoFaturamento", helper.getGrupoFaturamento());
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getEmpresa()))){
				query.setInteger("empresa", helper.getEmpresa());
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getLeiturista()))){
				query.setInteger("leiturista", helper.getLeiturista());
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getLocalidadeInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getLocalidadeFinal()))){
				query.setInteger("localidadeInicial", helper.getLocalidadeInicial())
					 .setInteger("localidadeFinal", helper.getLocalidadeFinal());
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getSetorComercialInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getSetorComercialFinal()))){
				query.setInteger("setorInicial", helper.getSetorComercialInicial())
					 .setInteger("setorFinal", helper.getSetorComercialFinal());
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getRotaInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getRotaFinal()))){
				query.setInteger("rotaInicial", helper.getRotaInicial())
					 .setInteger("rotaFinal", helper.getRotaFinal());
			}
			
			retorno = query.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * <b>[UC1180] Relatório Imóveis com Leituristas</b>:
	 *
	 * <ul>
	 * 		<li> 
	 * 			<b>[SB0003] Gerar Relatório do Tipo 3</b>: Quantitativo de imóveis que estão na rota mas não foram recebidos através da ISC</p>
	 * 		</li>
	 * </ul>
	 * 
	 * @author Magno Gouveia
	 * @date 10/06/2011
	 * 
	 * @param FiltrarRelatorioImoveisComLeiturasHelper 
	 * 
	 * @return O SQL para o tipo 3
	 */
	private String criarConsultaRelatorioImoveisComLeiturasQuantitativosTipo3(FiltrarRelatorioImoveisComLeiturasHelper helper){
		String consulta = "SELECT ce.empr_nmempresa as empresa, " // 0
						+ 	   "loca.loca_id as idLocalidade, " // 1
						+ 	   "loca.loca_nmlocalidade as localidade, " // 2
						+ 	   "stcm.stcm_cdsetorcomercial as codSetorComercial, " // 3
						+ 	   "stcm.stcm_nmsetorcomercial as setorComercial, " // 4
						+ 	   "rt.rota_cdrota as codRota, " // 5
						+ 	   "cl.clie_nmcliente as clienteLeiturista, " // 6
						+ 	   "fn.func_nmfuncionario as funcionarioLeiturista, " // 7
						+ 	   "COUNT(DISTINCT mcp.imov_id) AS qtdImoveis " // 8
						+ "FROM faturamento.mov_conta_prefaturada mcp "
						+ "INNER JOIN micromedicao.rota rt ON rt.rota_id = mcp.rota_id "
						+ "INNER JOIN cadastro.empresa ce ON ce.empr_id = rt.empr_id "
						+ "INNER JOIN cadastro.imovel im ON im.imov_id = mcp.imov_id "
						+ "INNER JOIN cadastro.localidade loca ON loca.loca_id = im.loca_id "
						+ "INNER JOIN faturamento.conta cn ON cn.imov_id = im.imov_id "
						+ 				"AND cn.cnta_amreferenciaconta = mcp.mcpf_ammovimento "
						+ "INNER JOIN cadastro.setor_comercial stcm ON stcm.stcm_id = im.stcm_id "
						+ "INNER JOIN faturamento.conta_impressao ci ON ci.cnta_id = cn.cnta_id "
						+ "LEFT JOIN micromedicao.leiturista lei ON lei.leit_id = rt.leit_id "
						+ "LEFT JOIN cadastro.cliente cl ON cl.clie_id = lei.clie_id "
						+ "LEFT JOIN cadastro.funcionario fn on fn.func_id = lei.func_id "
						+ "WHERE mcp.mcpf_ammovimento = :anoMes "
						+ 		"AND mcp.ftgr_id = :grupoFaturamento "
						+ 		"AND mcp.mcpf_icemissaoconta = 2 "
						+ 		"AND (ci.cnti_vlconta >= "
						+				"(SELECT parm_vlminemitirboleto "
						+ 			 	 "FROM cadastro.sistema_parametros) "
					    +	  		 "OR im.icte_id IN (1, 3)) ";
				  
		
		if(Util.verificarIdNaoVazio(String.valueOf(helper.getEmpresa()))){
			consulta += "AND ce.empr_id = :empresa ";
		}
		
		if(Util.verificarIdNaoVazio(String.valueOf(helper.getLeiturista()))){
			consulta += "AND lei.leit_id = :leiturista ";
		}
		
		if(Util.verificarIdNaoVazio(String.valueOf(helper.getLocalidadeInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getLocalidadeFinal()))){
			consulta += "AND loca.loca_id BETWEEN :localidadeInicial AND :localidadeFinal ";
		}
		
		if(Util.verificarIdNaoVazio(String.valueOf(helper.getSetorComercialInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getSetorComercialFinal()))){
			consulta += "AND stcm.stcm_cdsetorcomercial BETWEEN :setorInicial AND :setorFinal ";
		}
		
		if(Util.verificarIdNaoVazio(String.valueOf(helper.getRotaInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getRotaFinal()))){
			consulta += "AND rt.rota_cdrota BETWEEN :rotaInicial AND :rotaFinal ";
		}
		
		consulta += "GROUP BY ce.empr_nmempresa, loca.loca_id, loca.loca_nmlocalidade, stcm.stcm_cdsetorcomercial, stcm.stcm_nmsetorcomercial, rt.rota_cdrota, cl.clie_nmcliente, fn.func_nmfuncionario "
				  + "ORDER BY ce.empr_nmempresa, loca.loca_id, loca.loca_nmlocalidade, stcm.stcm_cdsetorcomercial, stcm.stcm_nmsetorcomercial, rt.rota_cdrota, cl.clie_nmcliente, fn.func_nmfuncionario";
		
		return consulta;
	}
	
	/**
	 * <b>[UC1180] Relatório Imóveis com Leituristas</b>:
	 *
	 * <ul>
	 * 		<li> 
	 * 			<b>[SB0004] Gerar Relatório do Tipo 4</b>: Relação de imóveis com leituras não recebidas através da ISC</b>
	 * 		</li>
	 * 		<li> 
	 * 			<b>[SB0005] Gerar Relatório do Tipo 5</b>: Relação de imóveis não medidos que não estão na rota de ISC</b>
	 * 		</li>
	 * 		<li> 
	 * 			<b>[SB0006] Gerar Relatório do Tipo 6</b>: Relação de imóveis medidos que não estão na rota de ISC</b>
	 * 		</li>
	 * </ul>
	 * 
	 * @author Magno Gouveia
	 * @date 10/06/2011
	 * 
	 * @param helper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioImoveisComLeiturasRelacao(FiltrarRelatorioImoveisComLeiturasHelper helper, int parametroPersistirRelatorio) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		Collection<Object[]> retorno = null;
		
		String consulta = "";

		try {
			switch (parametroPersistirRelatorio) {
			case Relatorio.GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_4:
				consulta = "SELECT DISTINCT ce.empr_nmempresa AS empresa, " // 0
						 + 	   "loca.loca_id AS idLocalidade, " // 1
						 + 	   "loca.loca_nmlocalidade AS localidade, " // 2
						 + 	   "stcm.stcm_cdsetorcomercial AS codSetorComercial, " // 3
						 + 	   "stcm.stcm_nmsetorcomercial AS setorComercial, " // 4
						 + 	   "rt.rota_cdrota AS codRota, " // 5
						 + 	   "cl.clie_nmcliente AS clienteLeiturista, " // 6
						 + 	   "fn.func_nmfuncionario AS funcionarioLeiturista, " // 7
						 + 	   "mcp.imov_id AS imovel " // 8
						 + "FROM faturamento.mov_conta_prefaturada mcp "
						 + "INNER JOIN micromedicao.rota rt ON rt.rota_id = mcp.rota_id "
						 + "INNER JOIN cadastro.imovel im ON im.imov_id = mcp.imov_id "
						 + "INNER JOIN cadastro.localidade loca ON loca.loca_id = im.loca_id "
						 + "INNER JOIN cadastro.empresa ce ON ce.empr_id = rt.empr_id "
						 + "INNER JOIN faturamento.conta cn ON cn.imov_id = im.imov_id "
 						 + 				"AND cn.cnta_amreferenciaconta = mcp.mcpf_ammovimento "
						 + "INNER JOIN cadastro.setor_comercial stcm ON stcm.stcm_id = im.stcm_id "
						 + "INNER JOIN faturamento.conta_impressao ci ON ci.cnta_id = cn.cnta_id "
						 + "LEFT  JOIN micromedicao.leiturista lei ON lei.leit_id = rt.leit_id "
						 + "LEFT  JOIN cadastro.cliente cl ON cl.clie_id = lei.clie_id "
						 + "LEFT  JOIN cadastro.funcionario fn ON fn.func_id = lei.func_id "
						 + "WHERE mcp.mcpf_ammovimento = :anoMes "
						 + 		"AND mcp.ftgr_id = :grupoFaturamento "
						 +		"AND mcp.mcpf_icemissaoconta = 2 "
						 + 		"AND ci.cnti_vlconta < "
						 +				"(SELECT parm_vlminemitirboleto "
						 + 			 	 "FROM cadastro.sistema_parametros) "
					     +	  	"AND im.icte_id NOT IN (1, 3) ";
				break;
			case Relatorio.GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_5:
				consulta = "SELECT DISTINCT ce.empr_nmempresa AS empresa, " // 0
						 + 	   "loca.loca_id AS idLocalidade, " // 1
						 + 	   "loca.loca_nmlocalidade AS localidade, " // 2
						 + 	   "stcm.stcm_cdsetorcomercial AS codSetorComercial, " // 3
						 + 	   "stcm.stcm_nmsetorcomercial AS setorComercial, " // 4
						 + 	   "rt.rota_cdrota AS codRota, " // 5
						 + 	   "cl.clie_nmcliente AS clienteLeiturista, " // 6
						 + 	   "fn.func_nmfuncionario AS funcionarioLeiturista, " // 7
						 + 	   "cn.imov_id AS imovel " // 8
						 + "FROM faturamento.conta cn "
						 + "INNER JOIN cadastro.imovel im ON im.imov_id = cn.imov_id "
						 + "INNER JOIN cadastro.localidade loca ON loca.loca_id = im.loca_id "
						 + "INNER JOIN cadastro.quadra qd ON qd.qdra_id = im.qdra_id "
					     + "INNER JOIN micromedicao.rota rt ON rt.rota_id = qd.rota_id "
					     + "INNER JOIN cadastro.setor_comercial stcm ON stcm.stcm_id = cn.cnta_cdsetorcomercial "
					     + "INNER JOIN cadastro.empresa ce ON ce.empr_id = rt.empr_id "
					     + "LEFT  JOIN micromedicao.leiturista lei ON lei.leit_id = rt.leit_id "
					     + "LEFT  JOIN cadastro.cliente cl ON cl.clie_id = lei.clie_id "  
					     + "LEFT  JOIN cadastro.funcionario fn ON fn.func_id = lei.func_id "
					     + "INNER JOIN atendimentopublico.ligacao_agua la ON la.lagu_id = im.imov_id "
					     + "WHERE cn.cnta_amreferenciaconta = :anoMes "
						 + 		"AND cn.ftgr_id = :grupoFaturamento "
						 + 		"AND (cn.dcst_idatual = 0 OR cn.dcst_idanterior = 0) "
						 + 		"AND im.hidi_id IS NULL "
						 + 		"AND la.hidi_id IS NULL "
						 + 		"AND im.icte_id NOT IN (1,3) "
						 + 		"AND cn.imov_id NOT IN "
						 + 		   "(SELECT imov_id "
						 + 		    "FROM micromedicao.movimento_roteiro_empr mre "
						 + 		    "WHERE mre.mrem_ammovimento = cn.cnta_amreferenciaconta " 
						 + 		        "AND mre.imov_id = cn.imov_id) ";
				break;
			case Relatorio.GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_6:
				consulta = "SELECT DISTINCT ce.empr_nmempresa AS empresa, " // 0
					 + 	   "loca.loca_id AS idLocalidade, " // 1
					 + 	   "loca.loca_nmlocalidade AS localidade, " // 2
					 + 	   "stcm.stcm_cdsetorcomercial AS codSetorComercial, " // 3
					 + 	   "stcm.stcm_nmsetorcomercial AS setorComercial, " // 4
					 + 	   "rt.rota_cdrota AS codRota, " // 5
					 + 	   "cl.clie_nmcliente AS clienteLeiturista, " // 6
					 + 	   "fn.func_nmfuncionario AS funcionarioLeiturista, " // 7
					 + 	   "cn.imov_id AS imovel " // 8
					 + "FROM faturamento.conta cn "
					 + "INNER JOIN cadastro.imovel im ON im.imov_id = cn.imov_id "
					 + "INNER JOIN cadastro.localidade loca ON loca.loca_id = im.loca_id "
					 + "INNER JOIN cadastro.quadra qd ON qd.qdra_id = im.qdra_id "
					 + "INNER JOIN micromedicao.rota rt ON rt.rota_id = qd.rota_id "
					 + "INNER JOIN cadastro.empresa ce ON ce.empr_id = rt.empr_id "
					 + "INNER JOIN cadastro.setor_comercial stcm ON stcm.stcm_id = cn.cnta_cdsetorcomercial "
				     + "LEFT  JOIN micromedicao.leiturista lei ON lei.leit_id = rt.leit_id "
					 + "LEFT  JOIN cadastro.cliente cl ON cl.clie_id = lei.clie_id "
					 + "LEFT  JOIN cadastro.funcionario fn ON fn.func_id = lei.func_id "
					 + "INNER JOIN atendimentopublico.ligacao_agua la ON la.lagu_id = im.imov_id "
					 + "WHERE cn.cnta_amreferenciaconta = :anoMes "
					 + 		"AND cn.ftgr_id = :grupoFaturamento "
					 + 		"AND (cn.dcst_idatual = 0 OR cn.dcst_idanterior = 0) "
					 +		"AND im.icte_id NOT IN (1,3) "
					 +		"AND (im.hidi_id IS NOT NULL "
					 +           "OR la.hidi_id IS NOT NULL) "
					 +		"AND cn.imov_id NOT IN "
					 +		    "(SELECT imov_id "
					 +		    "FROM micromedicao.movimento_roteiro_empr mre "
					 +		    "WHERE mre.mrem_ammovimento = cn.cnta_amreferenciaconta "
					 +		    	"AND mre.imov_id = cn.imov_id) ";
			}
	        
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getEmpresa()))){
				consulta += "AND rt.empr_id = :empresa ";
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getLeiturista()))){
				consulta += "AND lei.leit_id = :leiturista ";
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getLocalidadeInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getLocalidadeFinal()))){
				consulta += "AND loca.loca_id BETWEEN :localidadeInicial AND :localidadeFinal ";
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getSetorComercialInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getSetorComercialFinal()))){
				consulta += "AND stcm.stcm_cdsetorcomercial BETWEEN :setorInicial AND :setorFinal ";
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getRotaInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getRotaFinal()))){
				consulta += "AND rt.rota_cdrota BETWEEN :rotaInicial AND :rotaFinal ";
			}
	        
			consulta += "ORDER BY  ce.empr_nmempresa, loca.loca_id, stcm.stcm_cdsetorcomercial, rt.rota_cdrota, cl.clie_nmcliente, fn.func_nmfuncionario";
			
	        Query query = session.createSQLQuery(consulta)
										.addScalar("empresa", Hibernate.STRING)
										.addScalar("idLocalidade", Hibernate.INTEGER)
										.addScalar("localidade", Hibernate.STRING)
										.addScalar("codSetorComercial", Hibernate.INTEGER)
										.addScalar("setorComercial", Hibernate.STRING)
										.addScalar("codRota", Hibernate.INTEGER)
										.addScalar("clienteLeiturista", Hibernate.STRING)
										.addScalar("funcionarioLeiturista", Hibernate.STRING)
										.addScalar("imovel", Hibernate.STRING)
										.setInteger("anoMes", Integer.parseInt(helper.getMesAnoReferencia()))
										.setInteger("grupoFaturamento", helper.getGrupoFaturamento());
			
	        if(Util.verificarIdNaoVazio(String.valueOf(helper.getEmpresa()))){
				query.setInteger("empresa", helper.getEmpresa());
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getLeiturista()))){
				query.setInteger("leiturista", helper.getLeiturista());
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getLocalidadeInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getLocalidadeFinal()))){
				query.setInteger("localidadeInicial", helper.getLocalidadeInicial())
					 .setInteger("localidadeFinal", helper.getLocalidadeFinal());
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getSetorComercialInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getSetorComercialFinal()))){
				query.setInteger("setorInicial", helper.getSetorComercialInicial())
					 .setInteger("setorFinal", helper.getSetorComercialFinal());
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getRotaInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getRotaFinal()))){
				query.setInteger("rotaInicial", helper.getRotaInicial())
					 .setInteger("rotaFinal", helper.getRotaFinal());
			}
	        
	        retorno = query.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	/**
	 * <b>[UC1180] Relatório Imóveis com Leituristas</b>:
	 *
	 * <ul>
	 * 		<li> 
	 * 			<b>[SB0007] Gerar Relatório do Tipo 7</b>: Quantitativo de imóveis com leituras enviado e recebidos</b>
	 * 		</li>
	 * </ul>
	 * 
	 * @author Magno Gouveia
	 * @date 13/06/2011
	 * 
	 * @param helper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioImoveisComLeiturasTipo7(FiltrarRelatorioImoveisComLeiturasHelper helper) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Collection<Object[]> retorno = null;
		
		try {
			String consulta
			= "SELECT "
				+ "em.empr_nmempresa AS empresa, " // 0
				+ "rt.rota_cdrota AS rota, " // 1
				+ "CASE WHEN lei.clie_id IS NOT NULL THEN clie_nmcliente "
				+ 	   "WHEN lei.func_id IS NOT NULL THEN func_nmfuncionario "
				+ "END AS leiturista, " // 2
				+ "COALESCE(atre.txre_qtimovel, 0) AS qtdEnviados, " // 3
				+ "COALESCE(medidosRecebidos.qt_recebidos, 0) as qtdRecebidos, " // 4
				+ "ABS(COALESCE(atre.txre_qtimovel, 0) - COALESCE(medidosRecebidos.qt_recebidos, 0)) AS diferencaRecebidosEnviados, " // 5
				+ "COALESCE(medidosNaoImpressos.qt_medidos_nao_impressos, 0) AS qtdMedidosNaoImpressos, " // 6
				+ "COALESCE(naoMedidosNaoImpressos.qt_nao_medidos_nao_impressos, 0) AS qtdNaoMedidosNaoImpressos " // 7
			+ "FROM micromedicao.arquivo_texto_rot_empr atre "
			+ "INNER JOIN micromedicao.rota rt ON rt.rota_id = atre.rota_id "
			+ "LEFT  JOIN micromedicao.leiturista lei ON lei.leit_id = rt.leit_id "
			+ "LEFT  JOIN cadastro.cliente cl on cl.clie_id = lei.clie_id "
			+ "LEFT  JOIN cadastro.funcionario fn ON fn.func_id = lei.func_id "
			+ "LEFT  JOIN cadastro.empresa em ON em.empr_id = rt.empr_id ";
	
			// --qt_recebidos
			consulta += 
			"LEFT JOIN (SELECT rota_id AS rota, mcpf_ammovimento AS anomes, COUNT(DISTINCT(imov_id)) AS qt_recebidos "
					 + "FROM faturamento.mov_conta_prefaturada group by rota_id,mcpf_ammovimento) medidosRecebidos "
					 +		"ON (atre.rota_id = medidosRecebidos.rota AND atre.txre_amreferencia = medidosRecebidos.anomes) ";
				
			// --qt_medidos_nao_impressos
			consulta += 
			"LEFT JOIN (SELECT mcp.rota_id AS rota, mcp.mcpf_ammovimento AS anomes, COUNT(DISTINCT(mcp.imov_id)) AS qt_medidos_nao_impressos "
					 + "FROM faturamento.mov_conta_prefaturada mcp "
			         + "INNER JOIN cadastro.imovel im ON im.imov_id = mcp.imov_id "
			         + "INNER JOIN micromedicao.movimento_roteiro_empr mre ON mre.imov_id = im.imov_id "
			         + "INNER JOIN faturamento.conta cn ON cn.imov_id = im.imov_id AND cn.cnta_amreferenciaconta = mcp.mcpf_ammovimento "   
			         + "INNER JOIN faturamento.conta_impressao ci ON ci.cnta_id = cn.cnta_id "
			         + "WHERE ci.cnti_vlconta < (SELECT parm_vlminemitirboleto FROM cadastro.sistema_parametros) "
			         + 		"AND im.icte_id NOT IN (1,3) "
			         + 		"AND (mcp.mcpf_nnleiturahidrometro IS NOT NULL OR mcp.ltan_id IS NOT NULL) "
			         +		"AND TRIM(mre.mrem_nnhidrometro) IS NOT NULL "
			         + "GROUP BY mcp.rota_id, mcp.mcpf_ammovimento) medidosNaoImpressos "
			         +   "ON (atre.rota_id = medidosNaoImpressos.rota AND atre.txre_amreferencia = medidosNaoImpressos.anomes) ";
			
			// --qt_nao_medidos_nao_impressos
			consulta +=
			"LEFT JOIN (SELECT mcp.rota_id AS rota, mcp.mcpf_ammovimento AS anomes, COUNT(DISTINCT(mcp.imov_id)) AS qt_nao_medidos_nao_impressos "
			         + "FROM faturamento.mov_conta_prefaturada mcp "
			         + "INNER JOIN cadastro.imovel i ON i.imov_id = mcp.imov_id "
			         + "INNER JOIN micromedicao.movimento_roteiro_empr mre ON mre.imov_id = i.imov_id and mre.mrem_ammovimento = mcp.mcpf_ammovimento "
			         + "INNER JOIN faturamento.conta cn ON cn.imov_id = i.imov_id AND cn.cnta_amreferenciaconta = mcp.mcpf_ammovimento "
			         + "INNER JOIN faturamento.conta_impressao ci ON ci.cnta_id = cn.cnta_id "
			         + "WHERE ci.cnti_vlconta < (SELECT parm_vlminemitirboleto FROM cadastro.sistema_parametros) "
			         +       "AND i.icte_id NOT IN (1,3) "
			         +       "AND TRIM(mre.mrem_nnhidrometro) IS NULL "
			         +       "AND (mcpf_nnleiturahidrometro IS NULL AND mcp.ltan_id IS NULL) "
			         + "GROUP BY mcp.rota_id,mcp.mcpf_ammovimento) naoMedidosNaoImpressos " 
			         +	 "ON (atre.rota_id = naoMedidosNaoImpressos.rota AND atre.txre_amreferencia = naoMedidosNaoImpressos.anomes) ";
			
			consulta +=
			"WHERE txre_amreferencia = :anoMes "
			+	  "AND atre.ftgr_id = :grupoFaturamento "
			+ 	  "AND (COALESCE(medidosNaoImpressos.qt_medidos_nao_impressos, 0) > 0 "
			+	  "OR COALESCE(naoMedidosNaoImpressos.qt_nao_medidos_nao_impressos, 0) > 0 " 
			+	  "OR ABS(COALESCE(atre.txre_qtimovel, 0) - COALESCE(medidosRecebidos.qt_recebidos, 0)) > 0) ";
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getEmpresa()))){
				consulta += "AND em.empr_id = :empresa ";
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getLeiturista()))){
				consulta += "AND lei.leit_id = :leiturista ";
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getLocalidadeInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getLocalidadeFinal()))){
				consulta += "AND atre.loca_id BETWEEN :localidadeInicial AND :localidadeFinal ";
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getSetorComercialInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getSetorComercialFinal()))){
				consulta += "AND atre.txre_cdsetorcomercial1 BETWEEN :setorInicial AND :setorFinal ";
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getRotaInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getRotaFinal()))){
				consulta += "AND rt.rota_cdrota BETWEEN :rotaInicial AND :rotaFinal ";
			}
	        
			consulta += "ORDER BY em.empr_nmempresa, atre.rota_id, lei.leit_id ";
			
			Query query = session.createSQLQuery(consulta)
										.addScalar("empresa", Hibernate.STRING)
										.addScalar("rota", Hibernate.INTEGER)
										.addScalar("leiturista", Hibernate.STRING)
										.addScalar("qtdEnviados", Hibernate.INTEGER)
										.addScalar("qtdRecebidos", Hibernate.INTEGER)
										.addScalar("diferencaRecebidosEnviados", Hibernate.INTEGER)
										.addScalar("qtdMedidosNaoImpressos", Hibernate.INTEGER)
										.addScalar("qtdNaoMedidosNaoImpressos", Hibernate.INTEGER)
										.setInteger("anoMes", Integer.parseInt(helper.getMesAnoReferencia()))
										.setInteger("grupoFaturamento", helper.getGrupoFaturamento());
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getEmpresa()))){
				query.setInteger("empresa", helper.getEmpresa());
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getLeiturista()))){
				query.setInteger("leiturista", helper.getLeiturista());
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getLocalidadeInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getLocalidadeFinal()))){
				query.setInteger("localidadeInicial", helper.getLocalidadeInicial())
					 .setInteger("localidadeFinal", helper.getLocalidadeFinal());
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getSetorComercialInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getSetorComercialFinal()))){
				query.setInteger("setorInicial", helper.getSetorComercialInicial())
					 .setInteger("setorFinal", helper.getSetorComercialFinal());
			}
			
			if(Util.verificarIdNaoVazio(String.valueOf(helper.getRotaInicial())) && Util.verificarIdNaoVazio(String.valueOf(helper.getRotaFinal()))){
				query.setInteger("rotaInicial", helper.getRotaInicial())
					 .setInteger("rotaFinal", helper.getRotaFinal());
			}
	        
			retorno = query.list();
	        
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 * [UC0713] Emitir Ordem de Serviço Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 29/06/2011
	 * 
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarUltimoConsumoFaturadoImovel(Integer idImovel,Integer tipoLigacao)
			throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select cshi.numeroConsumoFaturadoMes "
					+ "from ConsumoHistorico cshi "
					+ "where cshi.imovel.id = :idImovel " 
					+ "and cshi.ligacaoTipo.id = :tipoLigacao "
					+ "order by cshi.referenciaFaturamento desc";

			retorno = (Integer) session.createQuery(consulta)
				.setInteger("idImovel", idImovel)
				.setInteger("tipoLigacao",tipoLigacao)
				.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}


	/**
	 * [UC0713] Emitir Ordem de Serviço Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 29/06/2011
	 * 
	 * @param idImovel
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosHidrometro(Integer idImovel)
			throws ErroRepositorioException {

		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select hidrometroMarca.descricao, " 
					+ "hidrometro.numero, "
					+ "hidrometroLocalInstalacao.descricao, " 
					+ "hidrometroInstalacaoHistorico.dataInstalacao, "
					+ "hidrometroProtecao.descricao, "
					+ "hidrometroInstalacaoHistorico.indicadorExistenciaCavalete, "
					+ "hidrometroCapacidade.descricao," 
					+ "hidrometroDiametro.descricao "
					+ "from "
					+ "gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico as hidrometroInstalacaoHistorico "
					+ "left join hidrometroInstalacaoHistorico.ligacaoAgua as ligacaoagua "
					+ "left join hidrometroInstalacaoHistorico.hidrometro as hidrometro "
					+ "left join hidrometro.hidrometroMarca as hidrometroMarca "
					+ "left join hidrometro.hidrometroCapacidade as hidrometroCapacidade "
					+ "left join hidrometro.hidrometroDiametro as hidrometroDiametro "
					+ "left join hidrometroInstalacaoHistorico.hidrometroLocalInstalacao as hidrometroLocalInstalacao "
					+ "left join hidrometroInstalacaoHistorico.hidrometroProtecao as hidrometroProtecao "
					+ "where ligacaoagua.id = :idImovel and hidrometroInstalacaoHistorico.dataRetirada is null";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;
	}
	
	/**
	 * [MA2011061011]
	 * 
	 * @author Paulo Diniz
	 * @date 02/07/2011
	 * 
	 * @param idHidrometro
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMaiorDataHidrometroMovimentado(Integer idHidrometro) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Date retorno = null;
		
		try {
			
			Criteria crit = session.createCriteria(HidrometroMovimentado.class);
			crit.setFetchMode("hidrometro", FetchMode.JOIN);
			crit.setFetchMode("hidrometroMovimentacao", FetchMode.JOIN);
			crit.createAlias("hidrometroMovimentacao", "hidrometroMovimentacao");
			crit.setProjection(Projections.max("hidrometroMovimentacao.data"));
			crit.add(Restrictions.eq("hidrometro.id",idHidrometro.intValue()));
			retorno = (Date) crit.uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	/**[MA2011061010]
	 * 
	 * @author Paulo Diniz
	 * @date 02/07/2011
	 * @param faixaInicial
	 *            Descricao do parametro
	 * @param faixaFinal
	 *            Descricao do parametro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Integer pesquisarNumeroHidrometroMovimentacaoPorFaixaCount(String Fixo,
			String faixaInicial, String faixaFinal) throws ErroRepositorioException {

			Integer retorno = null;
			Session session = HibernateUtil.getSession();
			String consulta;
			
			try {
				consulta = "select count(*) as total from micromedicao.hidrometro_movimentacao himv "
				+" inner join  micromedicao.hidrometro_movimentado ado on ado.himv_id = himv.himv_id"
  + " inner join micromedicao.hidrometro hidr on ado.hidr_id = hidr.hidr_id "
  + "  where hidr.hidr_nnhidrometro between  :fi and :ff ";
			
				Object resultado = session.createSQLQuery(consulta).addScalar("total", Hibernate.INTEGER).setString("fi",
						faixaInicial).setString("ff", faixaFinal).uniqueResult();
			
				if (resultado != null &&!resultado.toString().equals("0")) {
					retorno = (Integer) resultado;
				}else{
					consulta = "select count(*) as total from micromedicao.hidrometro_movimentacao himv "
						+" inner join  micromedicao.hidrometro_movimentado ado on ado.himv_id = himv.himv_id"
						  + " inner join micromedicao.hidrometro hidr on ado.hidr_id = hidr.hidr_id "
						  + "  where  hidr.hidr_nnhidrometro like :fi ";
					
					resultado = session.createSQLQuery(consulta).addScalar("total", Hibernate.INTEGER).setString("fi",
						"%"+faixaInicial+"%").uniqueResult();
					
					if (resultado != null){
						retorno = (Integer) resultado;
					}
				}
				
			} catch (HibernateException e) {
				// levanta a excecao para a proxima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				// fecha a sessao
				HibernateUtil.closeSession(session);
			}
			// retorna a colecao de atividades pesquisada(s)
		return retorno;
	}
	
	/** [MA2011061010]
	 * 
	 * pesquisa uma colecao de HidrometroMovimentacao
	 * 
	 * @author Paulo Diniz
	 * @date 02/07/2011
	 * 
	 * @param faixaInicial
	 *            Descricao do parametro
	 * @param faixaFinal
	 *            Descricao do parametro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarNumeroHidrometroMovimentacaoPorFaixaPaginacao(
			String faixaInicial, String faixaFinal, Integer numeroPagina)
			throws ErroRepositorioException {

		Collection hidrometrosMovimentacao = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			
			consulta = "select hidMovimentacao "
					+ " from HidrometroMovimentacao hidMovimentacao  "
					+ " inner join hidMovimentacao.hidrometroMovimentados as hidMovimentado " 
					+ " inner join hidMovimentado.hidrometro  hid " 
					+ " left join fetch hidMovimentacao.hidrometroMotivoMovimentacao motiv "
					+ " left join fetch hidMovimentacao.hidrometroLocalArmazenagemOrigem armaori "
					+ " left join fetch hidMovimentacao.hidrometroLocalArmazenagemDestino armades "
					+ " left join fetch hidMovimentacao.usuario usua "
					+ " where hid.numero between :fi and :ff "
					+ " order by hid.numero ";
			
			hidrometrosMovimentacao = session.createQuery(consulta).setString("fi",
					faixaInicial).setString("ff", faixaFinal).setFirstResult(
					10 * numeroPagina).setMaxResults(10).list();
			
			if(hidrometrosMovimentacao == null || hidrometrosMovimentacao.isEmpty()){
				consulta = "select hidMovimentacao "
					+ " from HidrometroMovimentacao hidMovimentacao  "
					+ " inner join hidMovimentacao.hidrometroMovimentados as hidMovimentado " 
					+ " inner join hidMovimentado.hidrometro  hid " 
					+ " left join fetch hidMovimentacao.hidrometroMotivoMovimentacao motiv "
					+ " left join fetch hidMovimentacao.hidrometroLocalArmazenagemOrigem armaori "
					+ " left join fetch hidMovimentacao.hidrometroLocalArmazenagemDestino armades "
					+ " left join fetch hidMovimentacao.usuario usua "
					+ " where hid.numero like :fi "
					+ " order by hid.numero ";
			
			hidrometrosMovimentacao = session.createQuery(consulta).setString("fi",
					"%"+faixaInicial+"%").setFirstResult(
					10 * numeroPagina).setMaxResults(10).list();
			}

		} catch (HibernateException e) {
			// levanta a excecao para a proxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessao
			HibernateUtil.closeSession(session);
		}
		// retorna a colecao de atividades pesquisada(s)
		// return retorno;
		return hidrometrosMovimentacao;
	}
	
	/** [MA2011061010]
	 * 
	 * pesquisa uma colecao de HidrometroMovimentacao
	 * 
	 * @author Paulo Diniz
	 * @date 02/07/2011
	 * 
	 * @param faixaInicial
	 *            Descricao do parametro
	 * @param faixaFinal
	 *            Descricao do parametro
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarNumeroHidrometroMovimentacaoPorFaixa(
			String faixaInicial, String faixaFinal)
			throws ErroRepositorioException {

		Collection hidrometrosMovimentacao = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			
			consulta = "select hidMovimentacao "
					+ " from HidrometroMovimentacao hidMovimentacao  "
					+ " inner join hidMovimentacao.hidrometroMovimentados as hidMovimentado " 
					+ " inner join hidMovimentado.hidrometro  hid " 
					+ " left join fetch hidMovimentacao.hidrometroMotivoMovimentacao motiv "
					+ " left join fetch hidMovimentacao.hidrometroLocalArmazenagemOrigem armaori "
					+ " left join fetch hidMovimentacao.hidrometroLocalArmazenagemDestino armades "
					+ " left join fetch hidMovimentacao.usuario usua "
					+ " where hid.numero between :fi and :ff "
					+ " order by hid.numero ";
			
			hidrometrosMovimentacao = session.createQuery(consulta).setString("fi",
					faixaInicial).setString("ff", faixaFinal).list();
			
			if(hidrometrosMovimentacao == null || hidrometrosMovimentacao.isEmpty()){
				consulta = "select hidMovimentacao "
					+ " from HidrometroMovimentacao hidMovimentacao  "
					+ " inner join hidMovimentacao.hidrometroMovimentados as hidMovimentado " 
					+ " inner join hidMovimentado.hidrometro  hid " 
					+ " left join fetch hidMovimentacao.hidrometroMotivoMovimentacao motiv "
					+ " left join fetch hidMovimentacao.hidrometroLocalArmazenagemOrigem armaori "
					+ " left join fetch hidMovimentacao.hidrometroLocalArmazenagemDestino armades "
					+ " left join fetch hidMovimentacao.usuario usua "
					+ " where hid.numero like :fi "
					+ " order by hid.numero ";
			
			hidrometrosMovimentacao = session.createQuery(consulta).setString("fi",
					"%"+faixaInicial+"%").list();
			}

		} catch (HibernateException e) {
			// levanta a excecao para a proxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessao
			HibernateUtil.closeSession(session);
		}
		// retorna a colecao de atividades pesquisada(s)
		// return retorno;
		return hidrometrosMovimentacao;
	}
	

	public LigacaoAguaSituacao obterDadosSituacaoLigacaoAgua(Integer idLigacao)
			throws ErroRepositorioException {

		LigacaoAguaSituacao retorno = new LigacaoAguaSituacao();
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			
			consulta = "select ligacaoAguaSituacao "
					+ " from LigacaoAguaSituacao ligacaoAguaSituacao "
					+ " where ligacaoAguaSituacao.id = :idLigacao ";
			
			retorno = (LigacaoAguaSituacao) session.createQuery(consulta).setInteger("idLigacao",idLigacao).setMaxResults(1).uniqueResult();
			

		} catch (HibernateException e) {
			// levanta a excecao para a proxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessao
			HibernateUtil.closeSession(session);
		}
		// retorna a colecao de atividades pesquisada(s)
		// return retorno;
		return retorno;
	}
    
    public Integer pesquisarAnoMesFaturamentoSituacaoInicio(Integer idImovel, Integer idFaturamentoSituacao) throws ErroRepositorioException {
        
        Integer retorno = null;
        Session session = HibernateUtil.getSession();
        StringBuilder query = new StringBuilder();
        try {
            query.append("SELECT ftsh.anoMesFaturamentoSituacaoInicio ");
            query.append("FROM FaturamentoSituacaoHistorico ftsh ");
            query.append("WHERE ftsh.imovel.id = :idImovel ");
            query.append(" AND ftsh.anoMesFaturamentoRetirada IS NULL");
            query.append(" AND ftsh.faturamentoSituacaoTipo.id = :idFaturamentoSituacao");

            retorno = (Integer) session.createQuery(query.toString())
                                       .setInteger("idImovel", idImovel)
                                       .setInteger("idFaturamentoSituacao", idFaturamentoSituacao)
                                       .setMaxResults(1)
                                       .uniqueResult();

        } catch (HibernateException e) {
            throw new ErroRepositorioException(e,
                                               "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }
	
	/*
	 * TODO - COSANPA
	 * Adição do método pesquisarImovelHidrometroInstaladoMaiorDataInstalacao()
	 * 
	 * Pesquisa o imóvel no qual o hidrômetro está instalado por maior data de instalação
	 * 
	 */
	
	public Object[] pesquisarImovelHidrometroInstaladoMaiorDataInstalacao(
			Integer idHidrometro) throws ErroRepositorioException {
		Object[] retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT imovel.id, lagu.id "
					+ "FROM HidrometroInstalacaoHistorico hidInsHis "
					+ "INNER JOIN hidInsHis.hidrometro hidrometro "
					+ "LEFT JOIN hidInsHis.imovel imovel "
					+ "LEFT JOIN hidInsHis.ligacaoAgua lagu "
					+ "WHERE hidrometro.id =:idHidrometro and "
					+ "hidInsHis.dataRetirada is null ORDER BY hidInsHis.dataInstalacao DESC";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idHidrometro", idHidrometro).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * TODO : Cosanpa
	 * Alteracao feita para a rota dividida não finalizar os imoveis por IMEI, 
	 * e sim, pelo numero do arquivo dividido.
	 */
	/**
	 * Metodo para atualizar a rota dividida por partes
	 * 
	 * @author Pamela Gatinho
	 * @date 04/02/2011
	 * 
	 * @param idRota
	 * @param anoMesFaturamento
	 * @param numeroSequenciaArquivo
	 * @param situacaoAnterior
	 * @param situacaoNova
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoTextoDividido(Integer idRota, Integer anoMesFaturamento, Integer numeroSequenciaArquivo, 
			int situacaoAnterior, int situacaoNova) throws ErroRepositorioException {

		
		Session session = HibernateUtil.getSession();
		
		Collection<Integer> idsArquivosDivididos = null;
		String consulta = null;

		try {
			
			//Obtem o arquivo texto da rota
			 consulta = "select id " 
			 		    + " from ArquivoTextoRoteiroEmpresa a "
						+ " where a.rota.id = :rota and "
						+ " a.anoMesReferencia = :anoMesFaturamento and "
						+ " a.situacaoTransmissaoLeitura.id = :idSituacaoTransmissaoLeituraAnterior";
				
			 Integer idArquivo = (Integer)session.createQuery(consulta)
			 				.setInteger("rota",idRota)
			 				.setInteger("anoMesFaturamento", anoMesFaturamento)
			 				.setInteger("idSituacaoTransmissaoLeituraAnterior",situacaoAnterior)			
			 				.setMaxResults(1).uniqueResult();
			
			//Obtem o arquivo texto dividido da rota
			 consulta = "select id " 
		 		    + " from ArquivoTextoRoteiroEmpresaDivisao a "
					+ " where a.arquivoTextoRoteiroEmpresa.id = :idArquivoTexto and "
					+ " a.numeroSequenciaArquivo = :numeroSequenciaArquivo and "
					+ " a.situacaoTransmissaoLeitura.id = :idSituacaoTransmissaoLeituraAnterior";
			
			 Integer idArquivoDividido = (Integer)session.createQuery(consulta)
		 				.setInteger("idArquivoTexto",idArquivo)
		 				.setInteger("numeroSequenciaArquivo", numeroSequenciaArquivo)
		 				.setInteger("idSituacaoTransmissaoLeituraAnterior",situacaoAnterior)			
		 				.setMaxResults(1).uniqueResult();
			
			 //Atualiza o arquivo dividido
			 if(idArquivoDividido != null){
				 String sql = "update gcom.micromedicao.ArquivoTextoRoteiroEmpresaDivisao "
						+ " set sitl_id = :idSituacaoTransmissaoLeituraNova "
						+ ", tred_tmultimaalteracao = :data "
						+ " where tred_id = :idArquivoDividido";

				session.createQuery(sql)
				  .setInteger("idArquivoDividido", idArquivoDividido)
				  .setInteger("idSituacaoTransmissaoLeituraNova",situacaoNova)
				  .setTimestamp("data", new Date())
						.executeUpdate();
			 }
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	
	/**
	 * TODO : COSANPA
	 * Método para buscar uma rota pelo id
	 * 
	 * @author Pamela Gatinho
	 * @date 14/04/2011
	 * 
	 * @param idRota 
	 * @throws ControladorException
	 */
	public Rota pesquisarRota(Integer idRota)

	throws ErroRepositorioException {

		// cria o objeto de retorno
		Rota rota = null;

		// Query
		String consulta = "";

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {
			 consulta = "SELECT rota " + " FROM Rota rota "
			 + " LEFT JOIN FETCH rota.empresa empresa "
			 + " LEFT JOIN FETCH rota.faturamentoGrupo grupo"
			 + " LEFT JOIN FETCH rota.leituraTipo leituraTipo"
			 + " LEFT JOIN FETCH rota.setorComercial setor"
			 + " LEFT JOIN FETCH rota.leiturista leiturista"
			 + " LEFT JOIN FETCH rota.leiturista.usuario usuario"
			 + " LEFT JOIN FETCH rota.setorComercial setorComercial"
			 + " LEFT JOIN FETCH rota.setorComercial.localidade localidade"
			 + " WHERE rota.id = :idRota";

			 rota = (Rota) session.createQuery(consulta).setInteger(
			 "idRota", idRota).setMaxResults(1).uniqueResult();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// retorna o imóvel
		return rota;
	}

	/*TODO:COSANPA
	 * 
	 * Atualizar campos em Medição Histórico e Consumo Histórico na retificação da conta
	 * */
	/**
	 * Método para atualizar a leitura atual faturada no medição histórico,
	 * na retificação da conta
	 * 
	 * 
	 * Autor: Adriana Muniz
	 * Data: 22/07/2011
	 * 
	 * @param leituraAtual
	 * @param anoMesReferencia
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarLeituraRetificaConta(Integer leituraAtual, 
			int anoMesReferencia, Integer idImovel) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		
		try {
			//Query
			String consulta = " update gcom.micromedicao.medicao.MedicaoHistorico " +
					" set mdhi_nnleituraatualfaturamento = :leituraAtual," +
					" mdhi_tmultimaalteracao = :data " +
					" where mdhi_amleitura = :anoMesReferencia " +
					" and lagu_id = :idImovel ";
			
			session.createQuery(consulta).setInteger("leituraAtual", leituraAtual)
										.setInteger("anoMesReferencia", anoMesReferencia)
										.setInteger("idImovel", idImovel)
										.setTimestamp("data", new Date())
										.executeUpdate();
			
		}catch(HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no hibernate");
		}finally {
			//fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * TODO : COSANPA
	 * Método para buscar uma rota pelo id
	 * da localidade e do setor comercial.
	 * 
	 * @author Pamela Gatinho
	 * @date 01/08/2011
	 * 
	 * @param idLocalidade
	 * @param codigoSetorComercial
	 * @throws ErroRepositorioException
	 */
	public Rota obterRotaPorLocalidadeSetorComercial(Integer idLocalidade, 
			Integer codigoSetorComercial) throws ErroRepositorioException {

		// cria o objeto de retorno
		Rota rota = null;

		// Query
		String consulta = "";

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {
			 consulta = "SELECT rota " + " FROM Rota rota "
			 + " LEFT JOIN FETCH rota.empresa empresa "
			 + " LEFT JOIN FETCH rota.faturamentoGrupo grupo"
			 + " LEFT JOIN FETCH rota.leituraTipo leituraTipo"
			 + " LEFT JOIN FETCH rota.setorComercial setor"
			 + " LEFT JOIN FETCH rota.leiturista leiturista"
			 + " LEFT JOIN FETCH rota.leiturista.usuario usuario"
			 + " LEFT JOIN FETCH rota.setorComercial setorComercial"
			 + " WHERE setor.codigo = :codigoSetorComercial "
			 + " AND setor.localidade.id = :idLocalidade";

			 rota = (Rota) session.createQuery(consulta)
			 .setInteger("idLocalidade", idLocalidade)
			 .setInteger("codigoSetorComercial", codigoSetorComercial)
			 .setMaxResults(1).uniqueResult();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// retorna o imóvel
		return rota;
	}

	/**
	 * TODO : COSANPA
	 * Retornar Arquivo Texto Roteiro Empresa completo
	 * 
	 * @author Felipe Santos
	 * @date 28/07/2011
	 */

	public Collection pesquisarArquivosTextoRoteiroEmpresaCompletoParaArquivoZip(
			String[] ids) throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select atre "
				+ "from ArquivoTextoRoteiroEmpresa atre "
				+ "inner join fetch atre.localidade loca "
				+ "inner join fetch atre.rota rota "
				+ "where atre.id in ( :ids) ";

			retorno = session.createQuery(consulta)
					.setParameterList("ids", ids).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * Retornar Arquivo Texto Roteiro Empresa por 
	 * Localidade, Id da Rota e Ano/Mês de Referência
	 * 
	 * @author Felipe Santos
	 * @date 05/08/2011
	 * 
	 */
	public ArquivoTextoRoteiroEmpresa pesquisarArquivosTextoRoteiroEmpresaTransmissaoOffline(
			Integer idLocalidade,Integer idRota, Integer anoMesReferencia) 
			throws ErroRepositorioException {
		ArquivoTextoRoteiroEmpresa retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select atre "
					+ "from ArquivoTextoRoteiroEmpresa atre "
					+ "inner join atre.situacaoTransmissaoLeitura situacaoTransmissaoLeitura "
					+ "where atre.localidade.id = :idLocalidade "
					+ "and atre.rota.id = :idRota "
					+ "and atre.anoMesReferencia = :anoMesReferencia ";
			
			retorno = (ArquivoTextoRoteiroEmpresa) session.createQuery(consulta)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idRota", idRota)
					.setInteger("anoMesReferencia", anoMesReferencia)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * Retornar Arquivo Texto Roteiro Empresa Divisão
	 * 
	 * @author Felipe Santos
	 * @date 05/08/2011
	 */
	public ArquivoTextoRoteiroEmpresaDivisao pesquisarArquivoTextoRoteiroEmpresaDivisao(Integer atreId, 
			Integer numeroSequenciaArquivo) throws ErroRepositorioException {
		ArquivoTextoRoteiroEmpresaDivisao retorno = null;

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "select tred "
				+ "from ArquivoTextoRoteiroEmpresaDivisao tred "
				+ "where tred.arquivoTextoRoteiroEmpresa = :arquivoTextoRoteiroEmpresa "
				+ "and tred.numeroSequenciaArquivo = :numeroSequenciaArquivo ";

			retorno = (ArquivoTextoRoteiroEmpresaDivisao) session.createQuery(consulta)
					.setInteger("arquivoTextoRoteiroEmpresa", atreId)
					.setInteger("numeroSequenciaArquivo", numeroSequenciaArquivo)
					.setMaxResults(1).uniqueResult();
			

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * TODO : COSANPA
	 * Pamela Gatinho - 08/09/2011
	 * 
	 * Gerar dados para o relatorio de leituras realizadas
	 * 
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @param tipoRelatorio
	 * @param usuarioLogado
	 * 
	 * @return
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarDadosRelatorioLeiturasRealizadas(
			int anoMesReferencia, Integer idFaturamentoGrupo) throws ErroRepositorioException {
		
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
		try {
			consulta = "select txre_amreferencia as referencia, " //0
						+ "re.ftgr_id as grupo, "  //1
						+ "emp.empr_nmempresa as empresa, " //2
						+ "re.loca_id as idLocalidade, " //3
						+ "loca_nmlocalidade as localidade, " //4
						+ "(select count(*) from micromedicao.arquivo_texto_roteiro_empresa re1 " //5
							+ "where sitl_id = :situacaoEmCampo and txre_amreferencia = :anoMesReferencia and re1.ftgr_id = re.ftgr_id "
							+ "and re1.loca_id = re.loca_id) as qtdRotasEmCampo, "
						+ "(select count(*) from micromedicao.arquivo_texto_roteiro_empresa re1 " //6
							+ "where txre_amreferencia = :anoMesReferencia and re1.ftgr_id = re.ftgr_id "
							+ "and re1.loca_id = re.loca_id and sitl_id = :situacaoTransmitido) as qtdRotasTransmitidas, "
						+ "(select count(*) from micromedicao.arquivo_texto_roteiro_empresa re1 " //7
							+ "where txre_amreferencia = :anoMesReferencia and re1.ftgr_id = re.ftgr_id "
							+ "and re1.loca_id = re.loca_id and sitl_id = :situacaoFinalizadoPeloUsuario) as qtdRotasFinalizadasPeloUsuario, " 
						+ "count(*) as qtdTotalLocal, " //8
						+ "(Select sum(txre_qtimovel) from micromedicao.arquivo_texto_roteiro_empresa re1 " //9
							+ "where txre_amreferencia = :anoMesReferencia and re1.ftgr_id = re.ftgr_id "
							+ "and re1.loca_id = re.loca_id and sitl_id = :situacaoEmCampo) as qtdImoveisEmCampo, "
						+ "(select count(distinct(mcp.imov_id)) from faturamento.mov_conta_prefaturada mcp, cadastro.imovel i " //10
							+ "where mcp.imov_id = i.imov_id and mcpf_ammovimento = re.txre_amreferencia and mcp.ftgr_id = re.ftgr_id "
							+ "and i.loca_id = re.loca_id and mcp.rota_id in "
									+ "(select rota_id from micromedicao.arquivo_texto_roteiro_empresa re2 "
									+ "where txre_amreferencia = :anoMesReferencia "
									+ "and re2.ftgr_id = re.ftgr_id "
									+ "and sitl_id = :situacaoEmCampo)) as qtdImoveisTransmitidosRotasParcial "
						+ "from micromedicao.arquivo_texto_roteiro_empresa re, "
							+ "micromedicao.situacao_transmissao_leitura stl, "
							+ "cadastro.localidade l, cadastro.empresa emp "
						+ "where re.sitl_id = stl.sitl_id "
							+ "and re.loca_id = l.loca_id "
							+ "and emp.empr_id = re.empr_id "
							+ "and txre_amreferencia = :anoMesReferencia "
							+ "and re.ftgr_id = :idFaturamentoGrupo "  
							+ "group by 1,2,3,4,5 " 
							+ "order by 1,2,3,4,5 ";

			retorno = session.createSQLQuery(consulta)
					.addScalar("referencia", Hibernate.STRING)
					.addScalar("grupo", Hibernate.STRING)
					.addScalar("empresa", Hibernate.STRING)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("localidade", Hibernate.STRING)
					.addScalar("qtdRotasEmCampo", Hibernate.INTEGER)
					.addScalar("qtdRotasTransmitidas", Hibernate.INTEGER)
					.addScalar("qtdRotasFinalizadasPeloUsuario", Hibernate.INTEGER)
					.addScalar("qtdTotalLocal", Hibernate.INTEGER)
					.addScalar("qtdImoveisEmCampo", Hibernate.INTEGER)
					.addScalar("qtdImoveisTransmitidosRotasParcial", Hibernate.INTEGER)
					
					.setInteger("idFaturamentoGrupo", idFaturamentoGrupo)
					.setInteger("situacaoEmCampo", SituacaoTransmissaoLeitura.EM_CAMPO)
					.setInteger("situacaoTransmitido", SituacaoTransmissaoLeitura.TRANSMITIDO)
					.setInteger("situacaoFinalizadoPeloUsuario", SituacaoTransmissaoLeitura.FINALIZADO_USUARIO)
					.setInteger("anoMesReferencia", new Integer(anoMesReferencia))
					.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**TODO:COSANPA
	 * 
	 * Utilizado na retificação de conta
	 * 
	 * Método para consultar se há arquivo de rota gerado para o proximo mês 
	 * a partir do grupo e da referência
	 * 
	 * Autor: Adriana Muniz
	 * Data:01/08/2011
	 * 
	 * @param idGrupo
	 * @param referencia
	 * @return
	 * @throws ErroRepositorioException
	 */	
	public boolean pesquisaArquivoRotaPorGrupoEReferencia(Integer idGrupo, Integer referencia)
		throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		boolean retorno = true;
		List<Integer> resultado = null;
		try {
			
			String consulta = " SELECT a.id " +
							" FROM ArquivoTextoRoteiroEmpresa a " +
							" INNER JOIN a.faturamentoGrupo f " +
							" WHERE a.anoMesReferencia = :referencia " +
							" AND f.id = :idGrupo ";

			resultado = session.createQuery(consulta).setInteger("referencia", referencia)
						.setInteger("idGrupo", idGrupo).list();
			
			if(resultado.isEmpty())
				retorno = false;
			else 
				retorno = true;

		}catch(HibernateException e){
			throw new ErroRepositorioException(e, "Erro no hibernate");
		}finally {
			//fehca sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}	
	
	/**TODO:COSANPA
	 * @author Adriana Muniz
	 * @date 26/02/2013
	 * 
	 * Retorna o valor do campo indicador analisado da tabea Medição Historico
	 * 
	 * @param idImovel
	 * @param anoMesReferenciaGrupoFaturamento
	 * @param idMedicaoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarMedicaoHistoricoLigacaoAguaAnalisado(Integer idImovel, 
			Integer anoMesReferenciaGrupoFaturamento, Integer idMedicaoTipo) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Short resultado = null;
		try {
			String query = "select indicadorAnalisado" +
							" from MedicaoHistorico m" +
							" where m.ligacaoAgua.id = :idImovel" +
							" and m.anoMesReferencia = :referencia" +
							" and m.medicaoTipo.id = :idMedicaoTipo";
			
			resultado = (Short)session.createQuery(query).setInteger("idImovel", idImovel)
									.setInteger("referencia", anoMesReferenciaGrupoFaturamento)
									.setInteger("idMedicaoTipo", idMedicaoTipo)
									.uniqueResult();
					
		}catch(HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no hibernate");
		}finally {
			HibernateUtil.closeSession(session);
		}
		
		return resultado;
	}
	
	/**
	 * COSANPA - Mantis 414 - Felipe Santos - 23/10/2013
	 * 
	 * Retorna quantidade de Hidrometros
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @param idLocalidade
	 * @param situacao
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeHidrometrosRelatorioBIG(
			Date dataInicial, Date dataFinal, Integer idLocalidade, 
			Integer situacao) throws ErroRepositorioException {
		
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT count(*) as qtd "
				+ "FROM micromedicao.hidrometro_inst_hist hidi "
				+ "INNER JOIN atendimentopublico.ligacao_agua lagu ON lagu.lagu_id = hidi.lagu_id "
				+ "INNER JOIN cadastro.imovel imov ON imov.imov_id = lagu.lagu_id "
				+ "WHERE imov.loca_id = :idLocalidade "
				+ "AND hidi_icinstalacaosubstituicao = :situacao "
				+ "AND hidi_dtinstalacaohidrometro between :dataInicial AND :dataFinal";
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("qtd", Hibernate.INTEGER)
				.setInteger("idLocalidade", idLocalidade)
				.setInteger("situacao", situacao)
				.setDate("dataInicial", dataInicial)
				.setDate("dataFinal", dataFinal)
				.setMaxResults(1)
				.uniqueResult();
	
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**TODO: COSANPA
     * @author Wellington Rocha
     * Data: 21/03/2012
     * 
     * Pesquisar todos os Hidrometro Protecao ativos
     * 
     * Geracao de Rotas para Recadastramento
     * 
     * @return Collection
     * @throws ControladorException
     *  
     */
     public Collection pesquisarHidrometroProtecao() throws ErroRepositorioException {
             Collection retorno = null;
             
         Session session = HibernateUtil.getSession();
         String consulta = null;
         
         try {
             consulta = " select hidrometroProtecao " 
            	 + " from HidrometroProtecao hidrometroProtecao "
            	 + " where hidrometroProtecao.indicadorUso = :indicadorUso ";
             
             retorno = (Collection) session.createQuery(consulta)
             		.setInteger("indicadorUso", ConstantesSistema.SIM.intValue())
             		.list();
                 
         } catch(HibernateException e) {
                 throw new ErroRepositorioException(e,"Erro no hibernate");
         } finally {
                 HibernateUtil.closeSession(session);
         }
         
         return retorno;
     }
     
     
     /**TODO: COSANPA
     * @author Wellington Rocha
     * Data: 30/04/2012
     * 
     * Pesquisar todos os Hidrometro Marca ativos
     * 
     * Geracao de Rotas para Recadastramento
     * 
     * @return Collection
     * @throws ControladorException
     *  
     */
     public Collection pesquisarHidrometroMarca() throws ErroRepositorioException {
             Collection retorno = null;
             
         Session session = HibernateUtil.getSession();
         String consulta = null;
         
         try {
             consulta = " select hidrometroMarca "
            	 + " from HidrometroMarca hidrometroMarca "
            	 + " where hidrometroMarca.indicadorUso = :indicadorUso ";
             
             retorno = (Collection) session.createQuery(consulta)
             		.setInteger("indicadorUso", ConstantesSistema.SIM.intValue())
             		.list();
             
         } catch(HibernateException e) {
                 throw new ErroRepositorioException(e,"Erro no hibernate");
         } finally {
                 HibernateUtil.closeSession(session);
         }
         
         return retorno;
     }
     
     public Collection<ImovelPorRotaHelper> buscarImoveisFaturamentoSeletivo(Integer matriculaImovel, Integer idRota, Integer anoMesFaturamento) throws ErroRepositorioException {
		Collection<ImovelPorRotaHelper> retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT new gcom.micromedicao.bean.ImovelPorRotaHelper(imovel, movimento) ");
			//consulta.append("SELECT imovel ");
			consulta.append("FROM MovimentoRoteiroEmpresa movimento ");
			consulta.append("   LEFT JOIN FETCH movimento.localidade localidade ");
			consulta.append("   LEFT JOIN FETCH movimento.imovel imovel ");
			consulta.append("   LEFT JOIN FETCH imovel.imovelPerfil imovelPerfil ");
			consulta.append("   LEFT JOIN FETCH imovel.ligacaoAgua ligacaoAgua ");
			consulta.append("   LEFT JOIN FETCH imovel.hidrometroInstalacaoHistorico hidInstHistoricoPoco ");
			consulta.append("   LEFT JOIN FETCH ligacaoAgua.hidrometroInstalacaoHistorico hidInstHistoricoAgua ");
			consulta.append("   LEFT JOIN FETCH hidInstHistoricoAgua.hidrometroProtecao ");
			consulta.append("   LEFT JOIN FETCH hidInstHistoricoAgua.hidrometroLocalInstalacao ");
			consulta.append("   LEFT JOIN FETCH hidInstHistoricoPoco.hidrometroProtecao ");
			consulta.append("   LEFT JOIN FETCH hidInstHistoricoPoco.hidrometroLocalInstalacao ");
			consulta.append("   LEFT JOIN FETCH hidInstHistoricoAgua.medicaoTipo medTipoAgua ");
			consulta.append("   LEFT JOIN FETCH hidInstHistoricoPoco.medicaoTipo medTipoPoco ");
			consulta.append("   LEFT JOIN FETCH movimento.rota rota ");
			consulta.append("   LEFT JOIN FETCH rota.empresa empresaRota ");
			consulta.append("   LEFT JOIN FETCH movimento.roteiroEmpresa roteiroEmpresa ");
			consulta.append("   LEFT JOIN FETCH rota.empresa empresa ");
			consulta.append("   LEFT JOIN FETCH rota.faturamentoGrupo faturamentoGrupo ");
			consulta.append("   LEFT JOIN FETCH imovel.ligacaoAguaSituacao ligacaoAguaSituacao ");
			consulta.append("   LEFT JOIN FETCH imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao ");
			consulta.append("   LEFT JOIN FETCH imovel.faturamentoSituacaoTipo faturamentoSituacaoTipo ");
			consulta.append("   LEFT JOIN FETCH rota.leituraTipo leituraTipo ");
			consulta.append("   LEFT JOIN FETCH localidade.gerenciaRegional gerenciaRegional ");
			consulta.append("   LEFT JOIN FETCH movimento.leituraAnormalidade leituraAnormalidade ");
			consulta.append("   LEFT JOIN FETCH imovel.setorComercial setorComercial ");
			consulta.append("   LEFT JOIN FETCH imovel.quadra quadra ");
			consulta.append("WHERE rota.id = :idRota AND movimento.anoMesMovimento = :anoMesFaturamento ");
			consulta.append(" and (movimento.numeroHidrometro is not null and movimento.numeroHidrometro <> '') ");
			consulta.append(" and imovel.id in (select c.imovel.id from Conta c where c.rota.id = :idRota and c.referencia = :anoMesFaturamento and c.debitoCreditoSituacaoAtual.id = :preFaturada ) ");
			
			if (matriculaImovel != null) {
				consulta.append(" and imovel.id = " + matriculaImovel);
			}
			consulta.append(" order by imovel.localidade.id, imovel.setorComercial.codigo, imovel.quadra.numeroQuadra, imovel.lote, imovel.subLote ");

			retorno = session.createQuery(consulta.toString())
					.setInteger("idRota", idRota)
					.setInteger("anoMesFaturamento", anoMesFaturamento)//.list();
					.setInteger("preFaturada", DebitoCreditoSituacao.PRE_FATURADA).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate - Buscar Imoveis faturamento seletivo.");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
     
     public LigacaoAgua obterLigacaoAgua(Integer idLigacao) throws ErroRepositorioException {

 		LigacaoAgua retorno = new LigacaoAgua();
 		Session session = HibernateUtil.getSession();
 		String consulta;
 		
 		try {
 			
 			consulta = "select ligacao from LigacaoAgua ligacao "
 					+ " where ligacao.id = :idLigacao ";
 			
 			retorno = (LigacaoAgua) session.createQuery(consulta).setInteger("idLigacao",idLigacao).setMaxResults(1).uniqueResult();
 			

 		} catch (HibernateException e) {
 			throw new ErroRepositorioException(e, "Erro no Hibernate");
 		} finally {
 			HibernateUtil.closeSession(session);
 		}

 		return retorno;
 	}
}
