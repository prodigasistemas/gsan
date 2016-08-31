package gcom.cadastro.imovel;

import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacaoAgua;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacaoEsgoto;
import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.bean.ClienteImovelEconomiaHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.bean.FiltrarImovelOutrosCriteriosHelper;
import gcom.cadastro.imovel.bean.ImovelEconomiaHelper;
import gcom.cadastro.imovel.bean.ImovelRelatorioHelper;
import gcom.cadastro.imovel.bean.ImovelSubcategoriaHelper;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.tarifasocial.TarifaSocialCarta;
import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.bean.EmitirDocumentoCobrancaBoletimCadastroHelper;
import gcom.cobranca.bean.SituacaoEspecialCobrancaHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.bean.SituacaoEspecialFaturamentoHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.faturamento.bean.FiltrarImovelInserirManterContaHelper;
import gcom.micromedicao.MovimentoRoteiroEmpresa;
import gcom.micromedicao.Rota;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.relatorio.cadastro.GerarRelatorioImoveisDoacoesHelper;
import gcom.relatorio.cadastro.RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisProgramasEspeciaisHelper;
import gcom.relatorio.micromedicao.FiltrarAnaliseExcecoesLeiturasHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.CollectionUtil;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.RemocaoInvalidaException;
import gcom.util.Util;
import gcom.util.filtro.GeradorHQLCondicional;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.exception.DataException;
import org.jboss.logging.Logger;

public class RepositorioImovelHBM implements IRepositorioImovel {

	private static IRepositorioImovel instancia;
	
	private Logger logger = Logger.getLogger(RepositorioImovelHBM.class);

	public RepositorioImovelHBM() {

	}

	public static IRepositorioImovel getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioImovelHBM();
		}
		return instancia;
	}
	
	public Imovel obterImovelPorId(Integer idImovel) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		
		try {
			return (Imovel) session.createQuery("select i from Imovel i where i.id = :id")
			.setParameter("id", idImovel)
			.uniqueResult();
		} catch (Exception e) {
			throw new ErroRepositorioException(e, "Erro ao recuperar imovel pelo id");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public void inserirImovel(Imovel imovel) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			session.save(imovel);

			session.flush();

		} catch (HibernateException e) {

			e.printStackTrace();

			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

			// session.close();

		}

	}

	public void atualizarImovel(Imovel imovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			session.update(imovel);
			session.flush();
		} catch (HibernateException e) {
			logger.error("Erro ao atualizar imovel", e);
			throw new ErroRepositorioException(e, "Erro ao atualizar imovel");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public Collection pesquisarImovel(FiltroImovel filtroImovel)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			retorno = GeradorHQLCondicional.gerarQueryCriteriaExpression(

			session, filtroImovel, Imovel.class);

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * 
	 * 
	 * 
	 * UC-0355] - Efetuar Corte de Ligaçã de Àgua [SB001] Atualizar Imóvel
	 * 
	 * Campos LEST_ID e IMOV _ TMULTIMAALTERACAO
	 * 
	 * 
	 * 
	 * @param imovel
	 * 
	 * Descrição do parâmetro
	 * 
	 * @exception ErroRepositorioExceptions
	 * 
	 * Descrição da exceção
	 * 
	 * 
	 * 
	 * @date 07/07/2006
	 * 
	 * @author Leandro Cavalcanti
	 * 
	 * @param imovel
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public void atualizarImovelLigacaoAgua(Imovel imovel,

	Integer idLigacaoAguaSituacao) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String update;

		try {

			// Atualizar os campos LAST_ID e IMOV _ TMULTIMAALTERACAO

			update = "update gcom.cadastro.imovel.Imovel set "

					+ "last_id = :ligSitAguaId, imov_tmultimaalteracao = :datahoracorrente "

					+ "where imov_id = :imovelId ";

			session.createQuery(update).setInteger("imovelId",

			imovel.getId().intValue()).setInteger("ligSitAguaId",

			idLigacaoAguaSituacao).setDate("datahoracorrente",

			new Date()).executeUpdate();

			// session.save(imovel);

			// session.flush();

		} catch (HibernateException e) {

			e.printStackTrace();

			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

			// session.close();

		}

	}

	/**
	 * 
	 * altera um imovel na base
	 * 
	 * 
	 * 
	 * @param imovel
	 * 
	 * Descrição do parâmetro
	 * 
	 * @exception ErroRepositorioExceptions
	 * 
	 * Descrição da exceção
	 * 
	 * 
	 * 
	 * @date 14/06/2006
	 * 
	 * @author Leandro Cavalcanti
	 * 
	 * @param imovel
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public void atualizarImovelExecucaoOrdemServicoLigacaoAgua(Imovel imovel,

	LigacaoAguaSituacao situacaoAgua) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String update;

		try {

			// Atualizar os campos LAST_ID e IMOV _ TMULTIMAALTERACAO

			update = "update gcom.cadastro.imovel.Imovel set "

					+ "last_id = :ligSitAguaId, imov_tmultimaalteracao = :datahoracorrente "

					+ "where imov_id = :imovelId";

			session.createQuery(update).setInteger("imovelId",

			imovel.getId().intValue()).setInteger("ligSitAguaId",

			situacaoAgua.getId()).setTimestamp("datahoracorrente",

			new Date()).executeUpdate();

			// session.save(imovel);

			session.flush();

			// //item 1.3

			// /*if(imovel.getLigacaoEsgotoSituacao()!= null &&

			// imovel.getLigacaoEsgotoSituacao().getId().intValue() ==

			// LigacaoEsgotoSituacao.LIG_FORA_DE_USO.intValue()){

			// */

			// //Atualizar os compos LAST_ID e IMOV _ TMULTIMAALTERACAO

			// String updateImovel = "update gcom.cadastro.imovel.Imovel set "

			// +"lest_id = :ligSitEsgotoId, imov_tmultimaalteracao =

			// :datahoracorrente, "

			// +"where imovel.id = :imovelId";

			//						

			// session.createQuery(updateImovel)

			// .setInteger("imovelId",imovel.getId().intValue())

			// .setInteger("ligSitEsgotoId",situacaoAgua.getId())

			// .setDate("datahoracorrente", new Date()).executeUpdate();

			//

			// session.save(imovel);

			// session.flush();

			//				

		} catch (HibernateException e) {

			e.printStackTrace();

			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

			// session.close();

		}

	}

	/**
	 * 
	 * altera um imovel na base
	 * 
	 * 
	 * 
	 * @param imovel
	 * 
	 * Descrição do parâmetro
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Descrição da exceção
	 * 
	 * 
	 * 
	 * @date 27/06/2006
	 * 
	 * @author Leandro Cavalcanti
	 * 
	 * @param imovel
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public void atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(Imovel imovel,

	LigacaoEsgotoSituacao situacaoEsgoto)

	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String update;

		try {

			// Atualizar os campos LAST_ID e IMOV _ TMULTIMAALTERACAO

			update = "update gcom.cadastro.imovel.Imovel set "

					+ "lest_id = :ligSitEsgId, imov_tmultimaalteracao = :datahoracorrente "

					+ "where imov_id = :imovelId";

			session.createQuery(update).setInteger("imovelId",

			imovel.getId().intValue()).setInteger("ligSitEsgId",

			situacaoEsgoto.getId()).setTimestamp("datahoracorrente",

			new Date()).executeUpdate();

			// session.save(imovel);

			session.flush();

		} catch (HibernateException e) {

			e.printStackTrace();

			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

			// session.close();

		}

	}

	/**
	 * 
	 * Remove um cliente imovel economia
	 * 
	 * 
	 * 
	 * @param id
	 * 
	 * Description of the Parameter
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Description of the Exception
	 * 
	 */

	public void removerClienteImovelEconomia(Integer id)

	throws ErroRepositorioException {

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			// remove uma cliente imovel na base da base

			Iterator iterator = session.createQuery(

			"from gcom.cadastro.cliente.ClienteImovelEconomia "

			+ "where imovelEconomia = :clienteImovelEconomia "

			+ "and dataFimRelacao is null").setInteger(

			"clienteImovelEconomia", id).iterate();

			while (iterator.hasNext()) {

				iterator.next();

				iterator.remove();

			}

			session.flush();

			// restrições no sistema

		} catch (JDBCException e) {

			// e.printStackTrace();

			// levanta a exceção para a próxima camada

			throw new RemocaoInvalidaException(e);

			// erro no hibernate

		} catch (HibernateException e) {

			e.printStackTrace();

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate: ");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

	}

	/**
	 * < <Descrição do método>>
	 * 
	 * 
	 * 
	 * @param objeto
	 * 
	 * Descrição do parâmetro
	 * 
	 * @param condicional
	 * 
	 * Descrição do parâmetro
	 * 
	 * @param id
	 * 
	 * Descrição do parâmetro
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Descrição da exceção
	 * 
	 */

	public void removerTodos(String objeto, String condicional, Integer id)

	throws ErroRepositorioException {

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			Iterator iterator = session.createQuery(

			"select distinct objeto from " + objeto

			+ " as objeto where objeto." + condicional + "= "

			+ id).iterate();

			while (iterator.hasNext()) {

				iterator.next();

				iterator.remove();

			}

			session.flush();

			// restrições no sistema

		} catch (JDBCException e) {

			// e.printStackTrace();

			// levanta a exceção para a próxima camada

			throw new RemocaoInvalidaException(e);

			// erro no hibernate

		} catch (HibernateException e) {

			e.printStackTrace();

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate: ");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

	}

	/**
	 * 
	 * Pesquisa uma coleção de imóveis com uma query especifica
	 * 
	 * 
	 * 
	 * @param idLocalidade
	 * 
	 * parametros para a consulta
	 * 
	 * @param idSetorComercial
	 * 
	 * parametros para a consulta
	 * 
	 * @param idQuadra
	 * 
	 * parametros para a consulta
	 * 
	 * @param lote
	 * 
	 * Descrição do parâmetro
	 * 
	 * @return Description of the Return Value
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Descrição da exceção
	 * 
	 */

	public Collection pesquisarImovel(Integer idLocalidade,

	Integer idSetorComercial, Integer idQuadra, Short lote,

	int indicadorExclusao) throws ErroRepositorioException {

		// cria a coleção de retorno

		Collection retorno = null;

		// Query

		String consulta;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			// pesquisa a coleção de atividades e atribui a variável "retorno"

			consulta = "SELECT imovel.id, imovel.localidade, "

			+ "imovel.setorComercial, imovel.quadra, "

			+ "imovel.lote, imovel.subLote, rota.id, ftgr.id, "

			+ "ftst.id , cbst.id " 
			
			+ "FROM gcom.cadastro.imovel.Imovel  imovel "

			+ "INNER JOIN imovel.quadra quadra "

			+ "LEFT JOIN imovel.faturamentoSituacaoTipo ftst "
			
			+ "LEFT JOIN imovel.cobrancaSituacao cbst "

			+ "INNER JOIN quadra.rota rota "

			+ "INNER JOIN rota.faturamentoGrupo ftgr ";

			consulta = consulta

					+ "where (imovel.indicadorExclusao IS NULL or imovel.indicadorExclusao != "

					+ indicadorExclusao + ")";

			if (idLocalidade != null) {

				consulta = consulta + "and imovel.localidade.id = "

				+ idLocalidade.intValue();

			}

			if (idSetorComercial != null && idLocalidade != null) {

				consulta = consulta + " and imovel.setorComercial.id = "

				+ idSetorComercial.intValue();

			} else if (idSetorComercial != null && idLocalidade == null) {

				consulta = consulta + "and imovel.setorComercial.id = "

				+ idSetorComercial.intValue();

			}

			if (idQuadra != null && idSetorComercial != null) {

				consulta = consulta + " and imovel.quadra.id = "

				+ idQuadra.intValue();

			} else if (idQuadra != null && idSetorComercial == null) {

				consulta = consulta + "and imovel.quadra.id = "

				+ idQuadra.intValue();

			}

			if (lote != null && idQuadra != null) {

				consulta = consulta + " and imovel.lote = " + lote.intValue();

			} else if (lote != null && idQuadra == null) {

				consulta = consulta + "and imovel.lote = " + lote.intValue();

			}

			retorno = session.createQuery(consulta).list();

			// erro no hibernate

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
	 * Atualiza apenas os dados (Localidade, Setor, Quadra e lote) do imóvel
	 * 
	 * 
	 * 
	 * @param imovel
	 * 
	 * parametros para a consulta
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Descrição da exceção
	 * 
	 */

	public void atualizarImovelInscricao(Imovel imovel)

	throws ErroRepositorioException {

		// Query

		String update;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			// Atualiza apenas os dados (Localidade, Setor, Quadra e lote) do

			// imóvel

			update = "update gcom.cadastro.imovel.Imovel set "

					+ "loca_id = :loca, stcm_id = :stcm, qdra_id = :qdra, imov_nnlote = :nnlote, "

					+ "imov_nnsublote = :nnsublote where imov_id = :imov";

			session.createQuery(update).setInteger("loca",

			imovel.getLocalidade().getId().intValue()).setInteger(

			"stcm", imovel.getSetorComercial().getId().intValue())

			.setInteger("qdra", imovel.getQuadra().getId().intValue())

			.setInteger("nnlote",

			new Short(imovel.getLote()).intValue()).setInteger(

			"nnsublote",

			new Short(imovel.getSubLote()).intValue())

			.setInteger("imov", imovel.getId().intValue())

			.executeUpdate();

			// erro no hibernate

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

	}

	/**
	 * < <Descrição do método>>
	 * 
	 * 
	 * 
	 * @param imovelSubcategoria
	 * 
	 * Descrição do parâmetro
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Descrição da exceção
	 * 
	 */

	public void atualizarImovelSubCategoria(

	ImovelSubcategoria imovelSubcategoria)

	throws ErroRepositorioException {

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			session.saveOrUpdate(imovelSubcategoria);

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
	 * < <Descrição do método>>
	 * 
	 * 
	 * 
	 * @param imovel
	 * 
	 * Descrição do parâmetro
	 * 
	 * @return Descrição do retorno
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Descrição da exceção
	 * 
	 */

	public Object pesquisarObterQuantidadeEconomias(Imovel imovel)

	throws ErroRepositorioException {

		Object retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta;

		try {

			consulta = "select sum(imsc.quantidadeEconomias) "

			+ "from gcom.cadastro.imovel.ImovelSubcategoria as imsc "

			+ "inner join imsc.comp_id.imovel "

			+ "where imsc.comp_id.imovel.id = :imovelId ";

			retorno = session.createQuery(consulta).setInteger("imovelId",

			imovel.getId().intValue()).uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}
	
	/**
	 * Retorna o cep do imóvel
	 * 
	 * @param imovel
	 * 
	 * @return Descrição do retorno
	 * 
	 * @exception ErroRepositorioException
	 * 
	 */

	public Cep pesquisarCepImovel(Imovel imovel)

	throws ErroRepositorioException {

		Cep retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta;

		try {

			consulta = "select cep "
			+ "from Imovel imov "
			+ "inner join imov.logradouroCep.cep cep "
			+ "where imov.id = :imovelId ";

			retorno = (Cep) session.createQuery(consulta).setInteger("imovelId",
			imovel.getId().intValue()).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	@SuppressWarnings("rawtypes")
	public Collection pesquisarObterQuantidadeEconomiasCategoria(Integer imovel) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "select c.id, c.descricao, c.consumoEstouro, "
					+ "c.vezesMediaEstouro, sum(isb.quantidadeEconomias), "
					+ "isb.comp_id.imovel.id, "
					+ "c.consumoAlto, "
					+ "c.mediaBaixoConsumo, "
					+ "c.vezesMediaAltoConsumo, "
					+ "c.porcentagemMediaBaixoConsumo,"
					+ "c.descricaoAbreviada, "
					+ "c.numeroConsumoMaximoEc, "
					+ "c.indicadorCobrancaAcrescimos, "
					+ "c.fatorEconomias, "
					+ "c.categoriaTipo.id, "
					+ "c.categoriaTipo.descricao, "
					+ "c.numeroConsumoMaximoEc "
					+ "from ImovelSubcategoria isb "
					+ "inner join isb.comp_id.subcategoria sb "
					+ "inner join sb.categoria c "
					+ "inner join c.categoriaTipo ct "
					+ "where isb.comp_id.imovel.id = :imovelId "
					+ "group by c.id, c.descricao, c.consumoEstouro, c.vezesMediaEstouro, "
					+ "isb.comp_id.imovel.id, c.consumoAlto, c.mediaBaixoConsumo, c.vezesMediaAltoConsumo, "
					+ "c.porcentagemMediaBaixoConsumo,c.descricaoAbreviada,c.numeroConsumoMaximoEc, " 
					+ "c.indicadorCobrancaAcrescimos, c.fatorEconomias, c.categoriaTipo.id, c.categoriaTipo.descricao ";

			retorno = session.createQuery(consulta).setInteger("imovelId", imovel.intValue()).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public Collection obterQuantidadeEconomiasCategoria(Integer conta)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "select c.id, c.descricao, c.consumoEstouro, "

					+ "c.vezesMediaEstouro, sum(isb.quantidadeEconomia), "

					+ "isb.comp_id.conta.id, "

					+ "c.consumoAlto, "

					+ "c.mediaBaixoConsumo, "

					+ "c.vezesMediaAltoConsumo, "

					+ "c.porcentagemMediaBaixoConsumo,"

					+ "c.descricaoAbreviada, c.fatorEconomias, "
					
					+ "c.numeroConsumoMaximoEc "

					+ "from ContaCategoria isb "

					+ "inner join isb.comp_id.conta sb "

					+ "inner join isb.comp_id.categoria c "
					
					+ "where isb.comp_id.conta.id = :contaId "

					// + "inner join sb.categoria c "

					+ "group by c.id, c.descricao, c.consumoEstouro, c.vezesMediaEstouro, isb.comp_id.conta.id," 
					
					+ "c.consumoAlto, c.mediaBaixoConsumo, c.vezesMediaAltoConsumo, c.porcentagemMediaBaixoConsumo," 
					
					+ "c.descricaoAbreviada, c.fatorEconomias, c.numeroConsumoMaximoEc "

					//+ "having isb.comp_id.conta.id = :contaId ";
			
					+ "ORDER BY c.descricao";

			retorno = session.createQuery(consulta).setInteger("contaId",

			conta.intValue()).list();

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
	 * Pesquisa uma coleção de imóveis com uma query especifica
	 * 
	 * 
	 * 
	 * @param idLocalidade
	 * 
	 * parametros para a consulta
	 * 
	 * @param idSetorComercial
	 * 
	 * parametros para a consulta
	 * 
	 * @param idQuadra
	 * 
	 * parametros para a consulta
	 * 
	 * @param lote
	 * 
	 * Descrição do parâmetro
	 * 
	 * @return Description of the Return Value
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Descrição da exceção
	 * 
	 */

	public Collection pesquisarImovelParametrosClienteImovel(

	Integer idLocalidade, Integer idSetorComercial, Integer idQuadra,

	Short lote) throws ErroRepositorioException {

		// cria a coleção de retorno

		Collection retorno = null;

		// Query

		String consulta;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			// pesquisa a coleção de atividades e atribui a variável "retorno"

			consulta = "select new gcom.cadastro.imovel.Imovel(imovel.id, imovel.localidade,"

					+ "imovel.setorComercial, imovel.quadra, imovel.lote, imovel.subLote,"

					+ "imovel.faturamentoSituacaoTipo) "

					+ "from gcom.cadastro.imovel.Imovel as imovel left join "

					+ "imovel.faturamentoSituacaoTipo " ;
//							"left join imovel.cobrancaSituacao ";

			if (idLocalidade != null) {

				consulta = consulta + "where imovel.localidade.id = "

				+ idLocalidade.intValue();

			}

			if (idSetorComercial != null && idLocalidade != null) {

				consulta = consulta + " and imovel.setorComercial.id = "

				+ idSetorComercial.intValue();

			} else if (idSetorComercial != null && idLocalidade == null) {

				consulta = consulta + "where imovel.setorComercial.id = "

				+ idSetorComercial.intValue();

			}

			if (idQuadra != null && idSetorComercial != null) {

				consulta = consulta + " and imovel.quadra.id = "

				+ idQuadra.intValue();

			} else if (idQuadra != null && idSetorComercial == null) {

				consulta = consulta + "where imovel.quadra.id = "

				+ idQuadra.intValue();

			}

			if (lote != null && idQuadra != null) {

				consulta = consulta + " and imovel.lote = " + lote.intValue();

			} else if (lote != null && idQuadra == null) {

				consulta = consulta + "where imovel.lote = " + lote.intValue();

			}

			retorno = session.createQuery(consulta).list();

			// erro no hibernate

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
	 * 
	 * 
	 * @param imovel
	 * 
	 * Descrição do parâmetro
	 * 
	 * @return Descrição do retorno
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Descrição da exceção
	 * 
	 */

	public Collection<Imovel> pesquisarImovelParametrosClienteImovel(

	FiltroClienteImovel filtroClienteImovel)

	throws ErroRepositorioException {

		Collection<Imovel> retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			retorno = new ArrayList(

					new CopyOnWriteArraySet<Imovel>(

							GeradorHQLCondicional

									.gerarCondicionalQuery(

											filtroClienteImovel,

											"imovel",

											"from gcom.cadastro.imovel.Imovel as imovel join imovel.medicaoHistoricos medicaoHistoricos",

											session).list()));

		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	public Collection obterDescricoesCategoriaImovel(Imovel imovel)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "select isc.comp_id.subcategoria.categoria.id," + // 0

					"isc.comp_id.subcategoria.categoria.descricao," + // 1

					"isc.comp_id.subcategoria.categoria.descricaoAbreviada "// 2

					+ "from ImovelSubcategoria isc "

					+ "left join isc.comp_id.subcategoria.categoria "

					+ "where isc.comp_id.imovel.id = :idImovel";

			retorno = session.createQuery(consulta).setInteger("idImovel",

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
	 * 
	 * [UC0164] - filtrar imovel outros criterios --- pesquisa
	 * 
	 */
//	 public Collection pesquisarImovelOutrosCriterios(FiltrarImovelOutrosCriteriosHelper filtrarImovelOutrosCriteriosHelper)
//	 		throws ErroRepositorioException {

	/*--<merge>--

	 Collection retorno = null;
	 Collection idsImovel = null;
	 String selectImovel = "";
	 String condicionais = "";

	 if ( filtrarImovelOutrosCriteriosHelper.getTipoRelatorio().trim().equalsIgnoreCase("RelatorioTarifaSocial")
			 || filtrarImovelOutrosCriteriosHelper.getTipoRelatorio().trim().equalsIgnoreCase("consultarTarifaExcluida") ) {

	 selectImovel = "select distinct im.id from TarifaSocialDadoEconomia tsde"
	 + " inner join tsde.imovelEconomia ie"
	 // + " inner join ie.clienteImovelEconomia cie"
	 // + " inner join cie.clienteRelacaoTipo crt"
	 + " inner join tsde.imovel im"
	 + " inner join im.localidade lo"
	 + " inner join lo.gerenciaRegional gr"
	 + " inner join im.setorComercial sc"
	 + " inner join im.quadra qu"
	 + " inner join sc.municipio mu"
//	 + " inner join qu.bairro ba"
	 + " left join im.logradouroCep logCep"
	 + " left join logCep.cep cep"
//	 + " left join im.logradouro lg"
	 // + " inner join cie.cliente cl"
	 // + " inner join cl.clienteTipo ct"
	 + " left join im.imovelCondominio ic"
	 + " left join im.imovelPrincipal ipri"
//	 + " left join im.nomeConta nc"
	 + " inner join im.ligacaoAguaSituacao las"
	 + " inner join im.ligacaoEsgotoSituacao les"
	 + " inner join im.imovelPerfil ip"
	 + " inner join im.pocoTipo pt"
	 + " left join im.faturamentoSituacaoTipo ft"
	 + " left join im.cobrancaSituacaoTipo cst"
	 + " left join im.eloAnormalidade ea"
	 + " left join im.cadastroOcorrencia co"
	 + " left join im.areaConstruidaFaixa acf"
	 + " left join im.consumoTarifa ct "
	 
	 + "where ";

	 }

	 condicionais = this.criaCondicionaisHqlImovelOutrosCriterios(filtrarImovelOutrosCriteriosHelper);	

	 String hqlConcatenado = selectImovel + condicionais;
	 String hqlFim = hqlConcatenado.substring(0, hqlConcatenado.length()-4);
	 
	 Session session = HibernateUtil.getSession();
	
	 try {

	 idsImovel = session.createQuery(hqlFim).list();

	 if( (filtrarImovelOutrosCriteriosHelper.getTipoRelatorio().trim().equalsIgnoreCase("RelatorioTarifaSocial")) ||
			  (filtrarImovelOutrosCriteriosHelper.getTipoRelatorio().trim().equalsIgnoreCase("consultarTarifaExcluida"))) { 
		 
		  if (!idsImovel.isEmpty()) { 
			  retorno = interarPesquisaIdsImoveisTarifaSocial(idsImovel); 
		  }
	 }

	 } catch (HibernateException e) { // levanta a exceção para a próxima camada
	  
		 throw new ErroRepositorioException(e, "Erro no Hibernate"); 
	 }finally { 
	 		HibernateUtil.closeSession(session); 
	 } 
	  
	  return retorno; 
	  
	 }
	  

	// filtro outros criterios - carregamento de tarifa social
	public Collection interarPesquisaIdsImoveisTarifaSocial(Collection idsImovel)

	throws ErroRepositorioException {

		Collection retorno = new ArrayList();

		// Object[] arrayImoveis = null;

		Iterator iteratorImoveis = idsImovel.iterator();

		Session session = HibernateUtil.getSession();

		try {

			while (iteratorImoveis.hasNext()) {

				// arrayImoveis = (Object[]) iteratorImoveis.next();

				Integer idImovel = (Integer) iteratorImoveis.next();

				// ---- referente a imovel

				String consultaImovel = "select" + " gr.id," // 0

						+ " gr.nome,"// 1

						+ " lo.id,"// 2

						+ " lo.descricao,"// 3

						+ " sc.codigo," // 4

						+ " sc.descricao,"// 5

						+ " im.id," // 6

						+ " mu.id," // 7

						+ " mu.nome," // 8

						// ---------Campos de Bairro

						+ " ba.codigo," // 9

						+ " ba.nome," // 10

						// ---------Campos de logradouro

						+ " lg.nome," // 11

						+ " lgtitulo.descricaoAbreviada," // 12

						+ " lgtipo.descricaoAbreviada," // 13

						// ---------Campos de quadra

						+ " qu.numeroQuadra," // 14

						// ---------Campos de cep

						+ " cep.codigo," // 15

						+ " cep.logradouro," // 16

						+ " cep.bairro," // 17

						+ " cep.municipio,"// 18

						+ " cep.descricaoTipoLogradouro,"// 19

						+ " cep.sigla,"// 20

						+ " tsd.dataImplantacao,"// 21

						+ " tsd.dataExclusao,"// 22

						+ " tsme.descricao," // 23

						+ " tsd.dataRecadastramento,"// 24

						+ " tsd.quantidadeRecadastramento"// 25

						+ " from TarifaSocialDado tsd"

						+ " inner join tsd.imovel im"

						+ " left join im.localidade lo"

						+ " left join im.setorComercial sc"

						+ " left join im.quadra qu" + " left join im.cep cep"

						+ " left join im.logradouro lg"

						+ " left join sc.municipio mu"

						+ " left join qu.bairro ba"

						+ " left join lg.logradouroTipo lgtipo"

						+ " left join lg.logradouroTitulo lgtitulo"

						+ " left join lo.gerenciaRegional gr"

						+ " left join tsd.tarifaSocialExclusaoMotivo tsme"

						+ " where im.id = " + idImovel;

				// ------------tarifa social dados economia

				String consultaTarifaEconomia = "select"

				+ " tre.numeroCartaoProgramaSocial," // 0

						+ " tsct.descricao,"// 1

						+ " tre.dataValidadeCartao,"// 2

						+ " tre.numeroMesesAdesao,"// 3

						+ " tre.consumoCelpe," // 4

						+ " tre.valorRendaFamiliar,"// 5

						+ " rt.descricao," // 6

						+ " ie.areaConstruida,"// 7

						+ " ie.numeroIptu," // 8

						+ " ie.numeroCelpe,"// 9

						+ " tsd.quantidadeRecadastramento" // 10

						+ " from TarifaSocialDadoEconomia tre"

						+ " left join tre.tarifaSocialCartaoTipo tsct"

						+ " left join tre.rendaTipo rt"

						+ " inner join tre.imovelEconomia ie"

						+ " inner join ie.imovelSubcategoria isub"

						+ " inner join tre.tarifaSocialDado tsd"

						+ " where isub.comp_id.imovel.id = " + idImovel;

				// ------------Cliente Imovel

				String consultaClienteImovel = "select" + " cl.nome," // 0

						+ " cl.cpf," // 1

						+ " rt.descricao," // 2

						+ " cl.rg," // 3

						+ " cl.dataEmissaoRg," // 4

						+ " org.descricaoAbreviada," // 5

						+ " uf.sigla" // 6

						+ " from ClienteImovel ci" + " inner join ci.imovel im"

						+ " inner join ci.cliente cl"

						+ " inner join ci.clienteRelacaoTipo rt"

						+ " left join cl.orgaoExpedidorRg org"

						+ " left join cl.unidadeFederacao uf"

						+ " where im.id =" + idImovel;

				System.out.println(consultaImovel);

				Collection colecaoImovel = session.createQuery(consultaImovel)

				.list();

				System.out.println(consultaTarifaEconomia);

				Collection colecaoTarifaEconomia = session.createQuery(

				consultaTarifaEconomia).list();

				System.out.println(consultaClienteImovel);

				Collection colecaoClienteImovel = session.createQuery(

				consultaClienteImovel).list();

				ImovelRelatorioHelper imovelRelatorioHelper = new ImovelRelatorioHelper();

				if (!colecaoImovel.isEmpty()) {

					Object[] arrayImovel = (Object[]) colecaoImovel.iterator()

					.next();

					imovelRelatorioHelper

					.setIdGerenciaRegional(arrayImovel[0] != null

					&& !arrayImovel[0].toString().trim()

					.equalsIgnoreCase("") ? (Integer) arrayImovel[0]

					: null);

					imovelRelatorioHelper

					.setDescricaoGerenciaRegional(arrayImovel[1] != null

					&& !arrayImovel[1].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[1]

					: null);

					imovelRelatorioHelper

					.setIdLocalidade(arrayImovel[2] != null

					&& !arrayImovel[2].toString().trim()

					.equalsIgnoreCase("") ? (Integer) arrayImovel[2]

					: null);

					imovelRelatorioHelper

					.setDescricaoLocalidade(arrayImovel[3] != null

					&& !arrayImovel[3].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[3]

					: null);

					imovelRelatorioHelper

					.setCodigoSetorComercial(arrayImovel[4] != null

					&& !arrayImovel[4].toString().trim()

					.equalsIgnoreCase("") ? (Integer) arrayImovel[4]

					: null);

					imovelRelatorioHelper

					.setDescricaoSetorComercial(arrayImovel[5] != null

					&& !arrayImovel[5].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[5]

					: null);

					imovelRelatorioHelper

					.setMatriculaImovel(arrayImovel[6] != null

					&& !arrayImovel[6].toString().trim()

					.equalsIgnoreCase("") ? (Integer) arrayImovel[6]

					: null);

					imovelRelatorioHelper

					.setIdMunicipio(arrayImovel[7] != null

					&& !arrayImovel[7].toString().trim()

					.equalsIgnoreCase("") ? (Integer) arrayImovel[7]

					: null);

					imovelRelatorioHelper

					.setNomeMunicipio(arrayImovel[8] != null

					&& !arrayImovel[8].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[8]

					: null);

					imovelRelatorioHelper

					.setIdBairro(arrayImovel[9] != null

					&& !arrayImovel[9].toString().trim()

					.equalsIgnoreCase("") ? (Integer) arrayImovel[9]

					: null);

					imovelRelatorioHelper

					.setNomeBairro(arrayImovel[10] != null

					&& !arrayImovel[10].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[10]

					: null);

					imovelRelatorioHelper

					.setNomeLogradouro(arrayImovel[11] != null

					&& !arrayImovel[11].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[11]

					: null);

					imovelRelatorioHelper

					.setTituloLogradouro(arrayImovel[12] != null

					&& !arrayImovel[12].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[12]

					: null);

					imovelRelatorioHelper

					.setTipoLogradouro(arrayImovel[13] != null

					&& !arrayImovel[13].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[13]

					: null);

					imovelRelatorioHelper

					.setNumeroQuadra(arrayImovel[14] != null

					&& !arrayImovel[14].toString().trim()

					.equalsIgnoreCase("") ? (Integer) arrayImovel[14]

					: null);

					imovelRelatorioHelper

					.setCepCodigo(arrayImovel[15] != null

					&& !arrayImovel[15].toString().trim()

					.equalsIgnoreCase("") ? (Integer) arrayImovel[15]

					: null);

					imovelRelatorioHelper

					.setCepLogradouro(arrayImovel[16] != null

					&& !arrayImovel[16].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[16]

					: null);

					imovelRelatorioHelper

					.setCepBairro(arrayImovel[17] != null

					&& !arrayImovel[17].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[17]

					: null);

					imovelRelatorioHelper

					.setCepMunicipio(arrayImovel[18] != null

					&& !arrayImovel[18].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[18]

					: null);

					imovelRelatorioHelper

					.setCepTipoLogradouro(arrayImovel[19] != null

					&& !arrayImovel[19].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[19]

					: null);

					imovelRelatorioHelper

					.setCepSigla(arrayImovel[20] != null

					&& !arrayImovel[20].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[20]

					: null);

					imovelRelatorioHelper

					.setDataImplantacao(arrayImovel[21] != null

					&& !arrayImovel[21].toString().trim()

					.equalsIgnoreCase("") ? (Date) arrayImovel[21]

					: null);

					imovelRelatorioHelper

					.setDataExclusao(arrayImovel[22] != null

					&& !arrayImovel[22].toString().trim()

					.equalsIgnoreCase("") ? (Date) arrayImovel[22]

					: null);

					imovelRelatorioHelper

					.setMotivoExclusao(arrayImovel[23] != null

					&& !arrayImovel[23].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[23]

					: null);

					imovelRelatorioHelper

					.setUltimoRecadastramento(arrayImovel[24] != null

					&& !arrayImovel[24].toString().trim()

					.equalsIgnoreCase("") ? (Date) arrayImovel[24]

					: null);

					imovelRelatorioHelper

					.setNumeroRecadastramento(arrayImovel[20] != null

					&& !arrayImovel[20].toString().trim()

					.equalsIgnoreCase("") ? ((Short) arrayImovel[20])

					.shortValue()

					: 0);

				}

				if (!colecaoTarifaEconomia.isEmpty()) {

					Object[] arrayTarifaEconomia = null;

					Iterator iterator = colecaoTarifaEconomia.iterator();

					ImovelRelatorioHelper imovelRelatorioHelperTarifa = null;

					Collection tarifasEconomias = new ArrayList();

					while (iterator.hasNext()) {

						arrayTarifaEconomia = (Object[]) iterator.next();

						imovelRelatorioHelperTarifa = new ImovelRelatorioHelper();

						imovelRelatorioHelperTarifa

								.setNumeroCartaoTarifaSocial(arrayTarifaEconomia[0] != null

										&& !arrayTarifaEconomia[0].toString()

										.trim().equalsIgnoreCase("") ? (Long) arrayTarifaEconomia[0]

										: null);

						imovelRelatorioHelperTarifa

								.setTipoCartaoTarifaSocial(arrayTarifaEconomia[1] != null

										&& !arrayTarifaEconomia[1].toString()

										.trim().equalsIgnoreCase("") ? (String) arrayTarifaEconomia[1]

										: null);

						imovelRelatorioHelperTarifa

								.setValidadeCartao(arrayTarifaEconomia[2] != null

										&& !arrayTarifaEconomia[2].toString()

										.trim().equalsIgnoreCase("") ? (Date) arrayTarifaEconomia[2]

										: null);

						imovelRelatorioHelperTarifa

								.setNumeroMesesAdesao(arrayTarifaEconomia[3] != null

										&& !arrayTarifaEconomia[3].toString()

										.trim().equalsIgnoreCase("") ? ((Short) arrayTarifaEconomia[3])

								.shortValue()

										: 0);

						imovelRelatorioHelperTarifa

								.setConsumoCelpe(arrayTarifaEconomia[4] != null

								&& !arrayTarifaEconomia[4].toString()

								.trim().equalsIgnoreCase("") ? (Integer) arrayTarifaEconomia[4]

										: null);

						imovelRelatorioHelperTarifa

								.setValorRendaFamiliar(arrayTarifaEconomia[5] != null

										&& !arrayTarifaEconomia[5].toString()

										.trim().equalsIgnoreCase("") ? (BigDecimal) arrayTarifaEconomia[5]

										: null);

						imovelRelatorioHelperTarifa

								.setRendaTipo(arrayTarifaEconomia[6] != null

								&& !arrayTarifaEconomia[6].toString()

								.trim().equalsIgnoreCase("") ? (String) arrayTarifaEconomia[6]

										: null);

						imovelRelatorioHelperTarifa

								.setAreaConstruida(arrayTarifaEconomia[7] != null

										&& !arrayTarifaEconomia[7].toString()

										.trim().equalsIgnoreCase("") ? ((Short) arrayTarifaEconomia[7])

								.shortValue()

										: 0);

						imovelRelatorioHelperTarifa

								.setNumeroIptu(arrayTarifaEconomia[8] != null

								&& !arrayTarifaEconomia[8].toString()

								.trim().equalsIgnoreCase("") ? (BigDecimal) arrayTarifaEconomia[8]

										: null);

						imovelRelatorioHelperTarifa

								.setNumeroCelpe(arrayTarifaEconomia[9] != null

								&& !arrayTarifaEconomia[9].toString()

								.trim().equalsIgnoreCase("") ? (Long) arrayTarifaEconomia[9]

										: null);

						imovelRelatorioHelperTarifa

								.setNumeroRecadastramento(arrayTarifaEconomia[10] != null

										&& !arrayTarifaEconomia[10].toString()

										.trim().equalsIgnoreCase("") ? ((Short) arrayTarifaEconomia[10])

								.shortValue()

										: 0);

						tarifasEconomias.add(imovelRelatorioHelperTarifa);

					}

					imovelRelatorioHelper.setTarifasEconomias(tarifasEconomias);

				}

				if (!colecaoClienteImovel.isEmpty()) {

					Object[] arrayCliente = null;

					Iterator iteratorCliente = colecaoClienteImovel.iterator();

					ImovelRelatorioHelper imovelRelatorioHelperCliente = null;

					Collection clientes = new ArrayList();

					while (iteratorCliente.hasNext()) {

						arrayCliente = (Object[]) iteratorCliente.next();

						imovelRelatorioHelperCliente = new ImovelRelatorioHelper();

						imovelRelatorioHelperCliente

						.setClienteNome(arrayCliente[0] != null

						&& !arrayCliente[0].toString().trim()

						.equalsIgnoreCase("") ? (String) arrayCliente[0]

						: null);

						imovelRelatorioHelperCliente

						.setClienteCpf(arrayCliente[1] != null

						&& !arrayCliente[1].toString().trim()

						.equalsIgnoreCase("") ? (String) arrayCliente[1]

						: null);

						imovelRelatorioHelperCliente

						.setClienteRelacaoTipo(arrayCliente[2] != null

						&& !arrayCliente[2].toString().trim()

						.equalsIgnoreCase("") ? (String) arrayCliente[2]

						: null);

						imovelRelatorioHelperCliente

						.setClienteRg(arrayCliente[3] != null

						&& !arrayCliente[3].toString().trim()

						.equalsIgnoreCase("") ? (String) arrayCliente[3]

						: null);

						imovelRelatorioHelperCliente

						.setClienteDataEmissaoOrgaoRg(arrayCliente[4] != null

						&& !arrayCliente[4].toString().trim()

						.equalsIgnoreCase("") ? (Date) arrayCliente[4]

						: null);

						imovelRelatorioHelperCliente

						.setClienteEmissaoOrgaoRg(arrayCliente[5] != null

						&& !arrayCliente[5].toString().trim()

						.equalsIgnoreCase("") ? (String) arrayCliente[5]

						: null);

						imovelRelatorioHelperCliente

						.setClienteUf(arrayCliente[6] != null

						&& !arrayCliente[6].toString().trim()

						.equalsIgnoreCase("") ? (String) arrayCliente[6]

						: null);

						clientes.add(imovelRelatorioHelperCliente);

					}

					imovelRelatorioHelper.setClientes(clientes);

				}

				retorno.add(imovelRelatorioHelper);

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
	 * Metodo para continuar a pesquisa utilizando os ids de imoveis filtrados
	 * 
	 */

	public Collection interarPesquisarIdsImoveis(Collection idsImovel)

	throws ErroRepositorioException {

		Collection colecaoImovelRelatorioHelper = new ArrayList();

		Collection colecaoligacaoAgua = new ArrayList();

		Collection colecaoligacaoEsgoto = new ArrayList();

		Collection colecaoConsumoHistorico = new ArrayList();

		Collection colecaoImovel = new ArrayList();

		Collection colecaoSubcategoria = new ArrayList();

		Collection colecaoClienteUsuarioResponsavel = new ArrayList();

		Iterator iterator = idsImovel.iterator();

		String idImovel = "";

		Session session = HibernateUtil.getSession();

		Integer arrayImovel = null;

		try {

			while (iterator.hasNext()) {

				arrayImovel = (Integer) iterator.next();

				idImovel = arrayImovel.toString();

				// ------------------Referente a Imovel

				String consultaImovel = "select"

						// ------Campos de imovel

						+ " im.id," // 0

						+ " im.lote," // 1

						+ " im.subLote," // 2

						+ " im.volumeReservatorioSuperior," // 3

						+ " im.volumeReservatorioInferior," // 4

						+ " im.volumePiscina," // 5

						+ " im.numeroMorador," // 6

						+ " im.numeroPontosUtilizacao," // 7

						+ " im.indicadorImovelCondominio," // 8

						+ " im.areaConstruida," // 9

						// ---------Campos de imovel Principal

						+ " ipri.id," // 10

						// ---------Campos de imovel Condominio

						+ " ic.id," // 11

						// ---------Campos de Cliente

						+ " cl.id," // 12

						+ " cl.nome," // 13

						// ---------Campos de Cliente relacao tipo

						+ " crt.descricao," // 14

						// ---------Campos localidade

						+ " lo.id," // 15

						+ " lo.descricao," // 16

						// ---------Campos Setor comercial

						+ " sc.codigo," // 17

						+ " sc.descricao," // 18

						// ---------Campos de Municipio

						+ " mu.id," // 19

						+ " mu.nome," // 20

						// ---------Campos de Bairro

						+ " ba.codigo," // 21

						+ " ba.nome," // 22

						// ---------Campos de logradouro

						+ " lg.nome," // 23

						+ " lgtitulo.descricaoAbreviada," // 24

						+ " lgtipo.descricaoAbreviada," // 25

						// ---------Campos de quadra

						+ " qu.numeroQuadra," // 26

						// ---------Campos de cep

						+ " cep.codigo," // 27

						+ " cep.logradouro," // 28

						+ " cep.bairro," // 29

						+ " cep.municipio," // 30

						+ " cep.descricaoTipoLogradouro," // 31

						+ " cep.sigla," // 32

						// ---------Campos de ligacao agua situacao

						+ " las.descricao," // 33

						// ---------Campos de ligacao esgoto situacao

						+ " les.descricao," // 34

						// ---------Campos de pavimento calcada

						+ " pavc.descricao," // 35

						// ---------Campos de pavimento rua

						+ " pavr.descricao," // 36

						// ---------Campos de despejo

						+ "desp.descricaoAbreviada," // 37

						// ---------Campos de poço tipo

						+ " pt.descricao," // 38

						// ---------Campos Hidrometro Poco(hidrometro historico

						// imovel)

						+ " hid.numero," // 39

						+ " hid.anoFabricacao," // 40

						+ " hic.descricaoAbreviada," // 41

						+ " him.descricaoAbreviada," // 42

						+ " hidiametro.descricaoAbreviada," // 43

						+ " hit.descricaoAbreviada," // 44

						+ " hih.dataInstalacao," // 45

						+ " hli.descricaoAbreviada," // 46

						+ " hip.descricaoAbreviada," // 47

						+ " hih.indicadorExistenciaCavalete," // 48

						+ " gr.nomeAbreviado,"// 49

						+ " gr.id"// 50

						// ---------Continua select

						+ " from ClienteImovel ci"

						+ " inner join ci.clienteRelacaoTipo crt"

						+ " inner join ci.imovel im"

						+ " inner join im.localidade lo"

						+ " inner join im.setorComercial sc"

						+ " inner join im.quadra qu"

						+ " left outer join im.logradouroBairro logradouroBairro "

						+ " left outer join logradouroBairro.bairro ba "

						+ " left outer join ba.municipio mu "

						// + " left join sc.municipio mu"

						// + " inner join qu.bairro ba"

						+ " left join im.pavimentoCalcada pavc"

						+ " left join im.pavimentoRua pavr"

						+ " left join im.despejo desp"

						+ " inner join lo.gerenciaRegional gr"

						+ " left  join im.logradouroCep logradouroCep "

						+ " left  join logradouroCep.cep cep "

						+ " left  join logradouroCep.logradouro lg "

						// + " left join im.cep cep"

						// + " left join im.logradouro lg"

						+ " left join lg.logradouroTipo lgtipo"

						+ " left join lg.logradouroTitulo lgtitulo"

						+ " inner join ci.cliente cl"

						+ " left join cl.clienteTipo ct"

						+ " left join im.imovelCondominio ic"

						+ " left join im.imovelPrincipal ipri"

						// + " left join im.nomeConta nc"

						+ " left join im.ligacaoAguaSituacao las"

						+ " left join im.ligacaoEsgotoSituacao les"

						+ " left join im.imovelPerfil ip"

						+ " left join im.pocoTipo pt"

						+ " left join im.faturamentoSituacaoTipo ft"

						+ " left join im.cobrancaSituacaoTipo cst"

						+ " left join im.eloAnormalidade ea"

						+ " left join im.cadastroOcorrencia co"

						+ " left join im.areaConstruidaFaixa acf"

						+ " left join im.hidrometroInstalacaoHistorico hih"

						+ " left join hih.hidrometro hid"

						+ " left join hid.hidrometroCapacidade hic"

						+ " left join hid.hidrometroMarca him"

						+ " left join hid.hidrometroDiametro hidiametro"

						+ " left join hid.hidrometroTipo hit"

						+ " left join hih.hidrometroLocalInstalacao hli"

						+ " left join hih.hidrometroProtecao hip"

						+ " left join im.consumoTarifa ct where im.id = "

						+ idImovel;

				System.out.println(consultaImovel);

				// ------------Referente a consumo historico

				String consultaConsumoHistorico = "select"

				+ " ch.consumoMedio," // 0

						+ " im.id"// 1

						+ " from ConsumoHistorico ch"

						+ " inner join ch.imovel im" + " where im.id = "

						+ idImovel;

				System.out.println(consultaConsumoHistorico);

				// ------------Referente a ligacao agua

				String consultaLigacaoAgua = "select" + " la.dataLigacao," // 0

						+ " lad.descricao," // 1

						+ " lam.descricao," // 2

						+ " la.numeroConsumoMinimoAgua," // 3

						// --------Campos Hidrometro Instalacao

						+ " hid.numero," // 0

						+ " hid.anoFabricacao," // 1

						+ " hic.descricaoAbreviada," // 2

						+ " him.descricaoAbreviada," // 3

						+ " hidiametro.descricaoAbreviada," // 4

						+ " hit.descricaoAbreviada," // 5

						+ " hih.dataInstalacao," // 6

						+ " hli.descricaoAbreviada," // 7

						+ " hip.descricaoAbreviada," // 8

						+ " hih.indicadorExistenciaCavalete" // 9

						// --------Continuação select

						+ " from LigacaoAgua la"

						+ " left join la.hidrometroInstalacaoHistorico hih"

						+ " left join hih.hidrometro hid"

						+ " left join hid.hidrometroCapacidade hic"

						+ " left join hid.hidrometroMarca him"

						+ " left join hid.hidrometroDiametro hidiametro"

						+ " left join hid.hidrometroTipo hit"

						+ " left join hih.hidrometroLocalInstalacao hli"

						+ " left join hih.hidrometroProtecao hip"

						+ " left join la.ligacaoAguaDiametro lad"

						+ " left join la.ligacaoAguaMaterial lam"

						+ " where la.id = " + idImovel;

				System.out.println(consultaLigacaoAgua);

				// ------------Referente a ligacao esgoto

				String consultaLigacaoEsgoto = "select" + " le.dataLigacao," // 0

						+ " led.descricao," // 1

						+ " lem.descricao," // 2

						+ " le.percentualAguaConsumidaColetada," // 3

						+ " le.percentual," // 4

						+ " le.consumoMinimo" // 5

						+ " from LigacaoEsgoto le"

						+ " left join le.ligacaoEsgotoDiametro led"

						+ " left join le.ligacaoEsgotoMaterial lem"

						+ " where le.id = " + idImovel;

				System.out.println(consultaLigacaoEsgoto);

				// --------------Referente a subcategorias

				String consultaSubcategoria = "select" + " sub.descricao,"

				+ " isc.quantidadeEconomias"

				+ " from ImovelSubcategoria isc"

				+ " inner join isc.comp_id.imovel im"

				+ " inner join isc.comp_id.subcategoria sub"

				+ " where im.id = " + idImovel;

				System.out.println(consultaSubcategoria);

				// --- Clientes Usuario e responsavel

				String consultaClienteUsuarioResponsavel = "select" + " cl.id,"// 0

						+ " cl.nome,"// 1

						+ " crt.id"// 2

						+ " from ClienteImovel ci"

						+ " inner join ci.cliente cl"

						+ " inner join ci.clienteRelacaoTipo crt"

						+ " inner join ci.imovel im" + " where im.id = "

						+ idImovel + " and (crt.id = "

						+ ClienteRelacaoTipo.USUARIO + " or crt.id = "

						+ ClienteRelacaoTipo.RESPONSAVEL + ")" + " and im.id ="

						+ idImovel;

				System.out.println(consultaClienteUsuarioResponsavel);

				// ----preenche colecao imovel

				colecaoImovel = session.createQuery(consultaImovel).list();

				// ----preenche colecao ligacao agua

				colecaoligacaoAgua = session.createQuery(consultaLigacaoAgua)

				.list();

				// ----preenche colecao ligacao esgoto

				colecaoligacaoEsgoto = session.createQuery(

				consultaLigacaoEsgoto).list();

				// ----preenche colecao consumo historico

				colecaoConsumoHistorico = session.createQuery(

				consultaConsumoHistorico).list();

				// ----preenche colecao subcategorias

				colecaoSubcategoria = session.createQuery(consultaSubcategoria)

				.list();

				colecaoClienteUsuarioResponsavel = session.createQuery(

				consultaClienteUsuarioResponsavel).list();

				colecaoImovelRelatorioHelper.add(carregaIomvelRelatorioHelper(

				colecaoImovel, colecaoligacaoAgua,

				colecaoligacaoEsgoto, colecaoConsumoHistorico,

				colecaoSubcategoria, colecaoClienteUsuarioResponsavel));

			}

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return colecaoImovelRelatorioHelper;

	}

	// filtrar imovel outros criterios - carrega colecao de clientes

	public Collection popularClienteUsuarioResponsavel(

	Collection colecaoClienteUsuarioResponsavel) {

		Object[] arrayCliente = null;

		ClienteImovel clienteImovel = null;

		Cliente cliente = null;

		ClienteRelacaoTipo clienteRelacaoTipo = null;

		Collection retorno = new ArrayList();

		Iterator iterator = colecaoClienteUsuarioResponsavel.iterator();

		while (!iterator.hasNext()) {

			clienteImovel = new ClienteImovel();

			cliente = new Cliente();

			clienteRelacaoTipo = new ClienteRelacaoTipo();

			arrayCliente = (Object[]) iterator.next();

			cliente

			.setId(arrayCliente[0] != null

			&& !arrayCliente[0].toString().trim()

			.equalsIgnoreCase("") ? (Integer) arrayCliente[0]

			: null);

			cliente

			.setNome(arrayCliente[1] != null

			&& !arrayCliente[1].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayCliente[1]

			: null);

			clienteRelacaoTipo

			.setId(arrayCliente[2] != null

			&& !arrayCliente[2].toString().trim()

			.equalsIgnoreCase("") ? (Integer) arrayCliente[2]

			: null);

			clienteImovel.setCliente(cliente);

			clienteImovel.setClienteRelacaoTipo(clienteRelacaoTipo);

			retorno.add(clienteImovel);

		}

		return null;

	}

	// filtro imove outros criterios - carrega objetos interando as colecoes

	public Collection iterarPesquisaIdImoveisEconomias(Collection idImoveis)

	throws ErroRepositorioException {

		String idImovel = "";

		Collection retorno = new ArrayList();

		Iterator iterator = idImoveis.iterator();

		String consultaImovel = "";

		// String consultaClienteImovelEconomia = "";

		String consultaSubcategroias = "";

		String consultaImovelEconomia = "";

		Collection colecaoSubcategorias = null;

		Collection colecaoImovelEconomia = null;

		Collection colecaoImovel = null;

		// ---colecoes para os retornos dos metodos

		Collection retornoImovelEconomia = null;

		Collection retornoSubcategoria = new ArrayList();

		Object[] arrayImovel = null;

		Object[] arraySubcategorias = null;

		// Object[] arrayImovelEconomia = null;

		Session session = HibernateUtil.getSession();

		try {

			ImovelRelatorioHelper imovelRelatorioHelper = null;

			while (iterator.hasNext()) {

				Integer idarrayImovel = (Integer) iterator.next();

				retornoSubcategoria = new ArrayList();

				idImovel = idarrayImovel.toString();

				consultaImovel = "select" + " im.id,"// 0

						+ " gr.nomeAbreviado,"// 1

						+ " lo.id,"// 2

						+ " lo.descricao,"// 3

						+ " sc.codigo,"// 4

						+ " sc.descricao,"// 5

						// ---------Campos de Municipio

						+ " mu.id," // 6

						+ " mu.nome," // 7

						// ---------Campos de Bairro

						+ " ba.codigo," // 8

						+ " ba.nome," // 9

						// ---------Campos de logradouro

						+ " lg.nome," // 10

						+ " lgtitulo.descricaoAbreviada," // 11

						+ " lgtipo.descricaoAbreviada," // 12

						// ---------Campos de quadra

						+ " qu.numeroQuadra," // 13

						// ---------Campos de cep

						+ " cep.codigo," // 14

						+ " cep.logradouro," // 15

						+ " cep.bairro," // 16

						+ " cep.municipio," // 17

						+ " cep.descricaoTipoLogradouro," // 18

						+ " cep.sigla" // 19

						+ " from Imovel im" + " inner join im.localidade lo"

						+ " inner join im.setorComercial sc"

						+ " inner join im.quadra qu"

						+ " inner join sc.municipio mu"

						+ " inner join qu.bairro ba"

						+ " left join im.logradouro lg"

						+ " left join lg.logradouroTipo lgtipo"

						+ " left join lg.logradouroTitulo lgtitulo"

						+ " left join im.cep cep"

						+ " inner join lo.gerenciaRegional gr"

						+ " where im.id = " + idImovel;

				// ----preenche imovel

				System.out.println(consultaImovel);

				colecaoImovel = session.createQuery(consultaImovel).list();

				if (!colecaoImovel.isEmpty()) {

					imovelRelatorioHelper = new ImovelRelatorioHelper();

					arrayImovel = (Object[]) colecaoImovel.iterator().next();

					imovelRelatorioHelper

					.setMatriculaImovel(arrayImovel[0] != null

					&& !arrayImovel[0].toString().trim()

					.equalsIgnoreCase("") ? (Integer) arrayImovel[0]

					: null);

					imovelRelatorioHelper

					.setDescricaoGerenciaRegional(arrayImovel[1] != null

					&& !arrayImovel[1].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[1]

					: null);

					imovelRelatorioHelper

					.setIdLocalidade(arrayImovel[2] != null

					&& !arrayImovel[2].toString().trim()

					.equalsIgnoreCase("") ? (Integer) arrayImovel[2]

					: null);

					imovelRelatorioHelper

					.setDescricaoLocalidade(arrayImovel[3] != null

					&& !arrayImovel[3].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[3]

					: null);

					imovelRelatorioHelper

					.setCodigoSetorComercial(arrayImovel[4] != null

					&& !arrayImovel[4].toString().trim()

					.equalsIgnoreCase("") ? (Integer) arrayImovel[4]

					: null);

					imovelRelatorioHelper

					.setDescricaoSetorComercial(arrayImovel[5] != null

					&& !arrayImovel[5].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[5]

					: null);

					imovelRelatorioHelper

					.setIdMunicipio(arrayImovel[6] != null

					&& !arrayImovel[6].toString().trim()

					.equalsIgnoreCase("") ? (Integer) arrayImovel[6]

					: null);

					imovelRelatorioHelper

					.setNomeMunicipio(arrayImovel[7] != null

					&& !arrayImovel[7].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[7]

					: null);

					imovelRelatorioHelper

					.setIdBairro(arrayImovel[8] != null

					&& !arrayImovel[8].toString().trim()

					.equalsIgnoreCase("") ? (Integer) arrayImovel[8]

					: null);

					imovelRelatorioHelper

					.setNomeBairro(arrayImovel[9] != null

					&& !arrayImovel[9].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[9]

					: null);

					imovelRelatorioHelper

					.setNomeLogradouro(arrayImovel[10] != null

					&& !arrayImovel[10].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[10]

					: null);

					imovelRelatorioHelper

					.setTituloLogradouro(arrayImovel[11] != null

					&& !arrayImovel[11].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[11]

					: null);

					imovelRelatorioHelper

					.setTipoLogradouro(arrayImovel[12] != null

					&& !arrayImovel[12].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[12]

					: null);

					imovelRelatorioHelper

					.setNumeroQuadra(arrayImovel[13] != null

					&& !arrayImovel[13].toString().trim()

					.equalsIgnoreCase("") ? (Integer) arrayImovel[13]

					: null);

					imovelRelatorioHelper

					.setCepCodigo(arrayImovel[14] != null

					&& !arrayImovel[14].toString().trim()

					.equalsIgnoreCase("") ? (Integer) arrayImovel[14]

					: null);

					imovelRelatorioHelper

					.setCepLogradouro(arrayImovel[15] != null

					&& !arrayImovel[15].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[15]

					: null);

					imovelRelatorioHelper

					.setCepBairro(arrayImovel[16] != null

					&& !arrayImovel[16].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[16]

					: null);

					imovelRelatorioHelper

					.setCepMunicipio(arrayImovel[17] != null

					&& !arrayImovel[17].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[17]

					: null);

					imovelRelatorioHelper

					.setCepTipoLogradouro(arrayImovel[18] != null

					&& !arrayImovel[18].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[18]

					: null);

					imovelRelatorioHelper

					.setCepSigla(arrayImovel[19] != null

					&& !arrayImovel[19].toString().trim()

					.equalsIgnoreCase("") ? (String) arrayImovel[19]

					: null);

					// ----Subcategorias

					consultaSubcategroias = "select" + " su.id,"// 0

							+ " su.descricao,"// 1

							+ " ca.descricao,"// 2

							+ " isub.quantidadeEconomias"// 3

							+ " from ImovelSubcategoria isub"

							+ " inner join isub.comp_id.subcategoria su"

							+ " inner join isub.comp_id.imovel im"

							+ " inner join su.categoria ca" + " where im.id = "

							+ idImovel;

					System.out.println(consultaSubcategroias);

					colecaoSubcategorias = session.createQuery(

					consultaSubcategroias).list();

					if (!colecaoSubcategorias.isEmpty()) {

						Iterator iteratorSubcategoria = colecaoSubcategorias

						.iterator();

						ImovelSubcategoriaHelper imovelSubcategoriaHelper = null;

						while (iteratorSubcategoria.hasNext()) {

							retornoImovelEconomia = new ArrayList();

							arraySubcategorias = (Object[]) iteratorSubcategoria

							.next();

							// ---popula imovelEconomiaHelper

							imovelSubcategoriaHelper = new ImovelSubcategoriaHelper();

							imovelSubcategoriaHelper

									.setSubcategoria(arraySubcategorias[1] != null

											&& !arraySubcategorias[1]

											.toString().trim()

											.equalsIgnoreCase("") ? (String) arraySubcategorias[1]

											: null);

							imovelSubcategoriaHelper

									.setCategoria(arraySubcategorias[2] != null

									&& !arraySubcategorias[2]

									.toString().trim()

									.equalsIgnoreCase("") ? (String) arraySubcategorias[2]

											: null);

							imovelSubcategoriaHelper

									.setQuantidadeEconomias(arraySubcategorias[3] != null

											&& !arraySubcategorias[3]

											.toString().trim()

											.equalsIgnoreCase("") ? ((Short) arraySubcategorias[3])

									.shortValue()

											: 0);

							// ----ImovelClienteImovel

							consultaImovelEconomia = "select"

									+ " ie.id,"// 0

									+ " ie.numeroPontosUtilizacao,"// 1

									+ " ie.complementoEndereco,"// 2

									+ " ie.numeroMorador,"// 3

									+ " ie.numeroIptu,"// 4

									+ " ie.numeroCelpe,"// 5

									+ " ie.areaConstruida,"// 6

									+ " ac.menorFaixa,"// 7

									+ " ac.maiorFaixa"// 8

									+ " from ImovelEconomia ie"

									+ " left join ie.areaConstruidaFaixa ac"

									+ " inner join ie.imovelSubcategoria isub"

									+ " inner join isub.comp_id.imovel im"

									+ " inner join isub.comp_id.subcategoria su"

									+ " where im.id = " + idImovel + " and"

									+ " su.id = "

									+ arraySubcategorias[0].toString();

							// ----preenche colecao clienteImovelEconomia

							System.out.println(consultaImovelEconomia);

							colecaoImovelEconomia = session.createQuery(

							consultaImovelEconomia).list();

							// --- metodo para carregar objeto e setalo na

							// colecao do superior

							retornoImovelEconomia = popularImovelEconomia(

							colecaoImovelEconomia, idImovel);

							imovelSubcategoriaHelper

							.setColecaoImovelEconomia(retornoImovelEconomia);

							retornoSubcategoria.add(imovelSubcategoriaHelper);

						}

						imovelRelatorioHelper

						.setSubcategorias(retornoSubcategoria);

					}

				}

				retorno.add(imovelRelatorioHelper);

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

	// filtrar imovel outros criterios - carrega cliente imovel

	public Collection populaClienteImovelRelatorioImovel(

	Collection colecaoClienteImovelEconomia) {

		Collection retorno = new ArrayList();

		ClienteImovelEconomiaHelper clienteImovelEconomiaHelper = null;

		Iterator iterator = colecaoClienteImovelEconomia.iterator();

		Object[] arrayClienteImovelEconomia = null;

		while (iterator.hasNext()) {

			clienteImovelEconomiaHelper = new ClienteImovelEconomiaHelper();

			arrayClienteImovelEconomia = (Object[]) iterator.next();

			clienteImovelEconomiaHelper

			.setClienteNome(arrayClienteImovelEconomia[0] != null

			&& !arrayClienteImovelEconomia[0].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayClienteImovelEconomia[0]

			: null);

			clienteImovelEconomiaHelper

			.setRelacaoTipo(arrayClienteImovelEconomia[1] != null

			&& !arrayClienteImovelEconomia[1].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayClienteImovelEconomia[1]

			: null);

			clienteImovelEconomiaHelper

			.setCpf(arrayClienteImovelEconomia[2] != null

			&& !arrayClienteImovelEconomia[2].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayClienteImovelEconomia[2]

			: null);

			clienteImovelEconomiaHelper

			.setCnpj(arrayClienteImovelEconomia[3] != null

			&& !arrayClienteImovelEconomia[3].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayClienteImovelEconomia[3]

			: null);

			clienteImovelEconomiaHelper

			.setRelacaoDataInicio(arrayClienteImovelEconomia[4] != null

			&& !arrayClienteImovelEconomia[4].toString().trim()

			.equalsIgnoreCase("") ? (Date) arrayClienteImovelEconomia[4]

			: null);

			clienteImovelEconomiaHelper

			.setRelacaoDataFim(arrayClienteImovelEconomia[5] != null

			&& !arrayClienteImovelEconomia[5].toString().trim()

			.equalsIgnoreCase("") ? (Date) arrayClienteImovelEconomia[5]

			: null);

			clienteImovelEconomiaHelper

			.setMotivoFimRelacao(arrayClienteImovelEconomia[6] != null

			&& !arrayClienteImovelEconomia[6].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayClienteImovelEconomia[6]

			: null);

			retorno.add(clienteImovelEconomiaHelper);

		}

		return retorno;

	}

	// filtrar imovel outros criterios - carrega imovel economia

	public Collection popularImovelEconomia(Collection colecaoImovelEconomia,

	String idImovel) throws ErroRepositorioException {

		Collection retorno = new ArrayList();

		Iterator iterator = colecaoImovelEconomia.iterator();

		Object[] arrayImovelEconomia = null;

		ImovelEconomiaHelper imovelEconomiaHelper = null;

		String consultaClienteImovelEconomia = "";

		Collection colecaoClienteImovelEconomia = new ArrayList();

		Collection retornoClienteImovelEconomia = new ArrayList();

		while (iterator.hasNext()) {

			imovelEconomiaHelper = new ImovelEconomiaHelper();

			arrayImovelEconomia = (Object[]) iterator.next();

			imovelEconomiaHelper

			.setNumeroPontosUtilizacao(arrayImovelEconomia[1] != null

			&& !arrayImovelEconomia[1].toString().trim()

			.equalsIgnoreCase("") ? ((Short) arrayImovelEconomia[1])

			.shortValue()

			: 0);

			imovelEconomiaHelper

			.setComplementoEndereco(arrayImovelEconomia[2] != null

			&& !arrayImovelEconomia[2].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovelEconomia[2]

			: null);

			imovelEconomiaHelper

			.setNumeroMoradores(arrayImovelEconomia[3] != null

			&& !arrayImovelEconomia[3].toString().trim()

			.equalsIgnoreCase("") ? ((Short) arrayImovelEconomia[3])

			.shortValue()

			: 0);

			imovelEconomiaHelper

			.setNumeroIptu(arrayImovelEconomia[4] != null

			&& !arrayImovelEconomia[4].toString().trim()

			.equalsIgnoreCase("") ? (BigDecimal) arrayImovelEconomia[4]

			: null);

			imovelEconomiaHelper

			.setNumeroContratoCelpe(arrayImovelEconomia[5] != null

			&& !arrayImovelEconomia[5].toString().trim()

			.equalsIgnoreCase("") ? (Long) arrayImovelEconomia[5]

			: null);

			imovelEconomiaHelper

			.setAreaConstruida(arrayImovelEconomia[6] != null

			&& !arrayImovelEconomia[6].toString().trim()

			.equalsIgnoreCase("") ? ((Short) arrayImovelEconomia[6])

			.shortValue()

			: 0);

			consultaClienteImovelEconomia = "select"

					+ " cl.nome," // 0

					+ " crt.descricao," // 1

					+ " cl.cpf," // 2

					+ " cl.cnpj," // 3

					+ " cie.dataInicioRelacao," // 4

					+ " cie.dataFimRelacao," // 5

					+ " cifrm.descricao" // 6

					+ " from ClienteImovelEconomia cie" // 7

					+ " inner join cie.cliente cl"

					+ " inner join cie.imovelEconomia.imovelSubcategoria.comp_id.imovel im"

					+ " inner join cie.clienteRelacaoTipo crt"

					+ " left join cie.clienteImovelFimRelacaoMotivo cifrm"

					+ " left join cie.imovelEconomia ie" + " where im.id = "

					+ idImovel + " and" + " ie.id = "

					+ arrayImovelEconomia[0].toString();

			Session session = HibernateUtil.getSession();

			try {

				System.out.println(consultaClienteImovelEconomia);

				colecaoClienteImovelEconomia = session.createQuery(

				consultaClienteImovelEconomia).list();

				// ---- popula objeto e coloca-o na colecao

				retornoClienteImovelEconomia = populaClienteImovelRelatorioImovel(colecaoClienteImovelEconomia);

				imovelEconomiaHelper

				.setClienteImovelEconomiaHelper(retornoClienteImovelEconomia);

				retorno.add(imovelEconomiaHelper);

			} catch (HibernateException e) {

				// levanta a exceção para a próxima camada

				throw new ErroRepositorioException(e, "Erro no Hibernate");

			} finally {

				// fecha a sessão

				HibernateUtil.closeSession(session);

			}

		}

		return retorno;

	}

	// --filtrar imovel outros criterios - carrega objeto helper para relatorio

	public ImovelRelatorioHelper carregaIomvelRelatorioHelper(

	Collection colecaoImovel, Collection colecaoLigacaoAgua,

	Collection colecaoLigacaoEsgoto,

	Collection colecaoConsumoHistorico, Collection colecaoSubcategoria,

	Collection colecaoClienteUsuarioResponsavel) {

		ImovelRelatorioHelper imovelRelatorioHelper = new ImovelRelatorioHelper();

		// ------imoveis

		if (!colecaoImovel.isEmpty()) {

			Object[] arrayImovel = (Object[]) colecaoImovel.iterator().next();

			imovelRelatorioHelper

			.setMatriculaImovel(arrayImovel[0] != null

			&& !arrayImovel[0].toString().trim()

			.equalsIgnoreCase("") ? (Integer) arrayImovel[0]

			: null);

			imovelRelatorioHelper

			.setNumeroLote(arrayImovel[1] != null

			&& !arrayImovel[1].toString().trim()

			.equalsIgnoreCase("") ? ((Short) arrayImovel[1])

			.shortValue()

			: 0);

			imovelRelatorioHelper

			.setNumeroSubLote(arrayImovel[2] != null

			&& !arrayImovel[2].toString().trim()

			.equalsIgnoreCase("") ? ((Short) arrayImovel[2])

			.shortValue()

			: 0);

			imovelRelatorioHelper

			.setVolumeReservatorioSuperior(arrayImovel[3] != null

			&& !arrayImovel[3].toString().trim()

			.equalsIgnoreCase("") ? (BigDecimal) arrayImovel[3]

			: null);

			imovelRelatorioHelper

			.setVolumeReservatorioInferior(arrayImovel[4] != null

			&& !arrayImovel[4].toString().trim()

			.equalsIgnoreCase("") ? (BigDecimal) arrayImovel[4]

			: null);

			imovelRelatorioHelper

			.setVolumePiscina(arrayImovel[5] != null

			&& !arrayImovel[5].toString().trim()

			.equalsIgnoreCase("") ? (BigDecimal) arrayImovel[5]

			: null);

			imovelRelatorioHelper

			.setNumeroMoradores(arrayImovel[6] != null

			&& !arrayImovel[6].toString().trim()

			.equalsIgnoreCase("") ? ((Short) arrayImovel[6])

			.shortValue()

			: 0);

			imovelRelatorioHelper

			.setNumeroPontosUtilzacao(arrayImovel[7] != null

			&& !arrayImovel[7].toString().trim()

			.equalsIgnoreCase("") ? ((Short) arrayImovel[7])

			.shortValue()

			: 0);

			imovelRelatorioHelper

			.setIndicadorImovelCondominio(arrayImovel[8] != null

			&& !arrayImovel[8].toString().trim()

			.equalsIgnoreCase("") ? ((Short) arrayImovel[8])

			.shortValue()

			: 0);

			if (arrayImovel[8] != null

			&& !arrayImovel[8].toString().trim().equalsIgnoreCase("")) {

				if (imovelRelatorioHelper.getIndicadorImovelCondominio() == 1) {

					imovelRelatorioHelper

					.setIndicadorImovelCondominioDescricao("Sim");

				} else {

					imovelRelatorioHelper

					.setIndicadorImovelCondominioDescricao("Não");

				}

			}

			imovelRelatorioHelper

			.setAreaConstruida(arrayImovel[9] != null

			&& !arrayImovel[9].toString().trim()

			.equalsIgnoreCase("") ? ((Short) arrayImovel[9])

			.shortValue()

			: null);

			imovelRelatorioHelper

			.setMatriculaImovelPrincipal(arrayImovel[10] != null

			&& !arrayImovel[10].toString().trim()

			.equalsIgnoreCase("") ? (Integer) arrayImovel[10]

			: null);

			imovelRelatorioHelper

			.setMatriculaImovelCondominio(arrayImovel[11] != null

			&& !arrayImovel[11].toString().trim()

			.equalsIgnoreCase("") ? (Integer) arrayImovel[11]

			: null);

			imovelRelatorioHelper

			.setClienteId(arrayImovel[12] != null

			&& !arrayImovel[12].toString().trim()

			.equalsIgnoreCase("") ? (Integer) arrayImovel[12]

			: null);

			imovelRelatorioHelper

			.setClienteNome(arrayImovel[13] != null

			&& !arrayImovel[13].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[13]

			: null);

			imovelRelatorioHelper

			.setClienteRelacaoTipo(arrayImovel[14] != null

			&& !arrayImovel[14].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[14]

			: null);

			imovelRelatorioHelper

			.setIdLocalidade(arrayImovel[15] != null

			&& !arrayImovel[15].toString().trim()

			.equalsIgnoreCase("") ? (Integer) arrayImovel[15]

			: null);

			imovelRelatorioHelper

			.setDescricaoLocalidade(arrayImovel[16] != null

			&& !arrayImovel[16].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[16]

			: null);

			imovelRelatorioHelper

			.setCodigoSetorComercial(arrayImovel[17] != null

			&& !arrayImovel[17].toString().trim()

			.equalsIgnoreCase("") ? (Integer) arrayImovel[17]

			: null);

			imovelRelatorioHelper

			.setDescricaoSetorComercial(arrayImovel[18] != null

			&& !arrayImovel[18].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[18]

			: null);

			imovelRelatorioHelper

			.setIdMunicipio(arrayImovel[19] != null

			&& !arrayImovel[19].toString().trim()

			.equalsIgnoreCase("") ? new Integer(

			arrayImovel[19].toString()) : null);

			imovelRelatorioHelper

			.setNomeMunicipio(arrayImovel[20] != null

			&& !arrayImovel[20].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[20]

			: null);

			imovelRelatorioHelper

			.setIdBairro(arrayImovel[21] != null

			&& !arrayImovel[21].toString().trim()

			.equalsIgnoreCase("") ? (Integer) arrayImovel[21]

			: null);

			imovelRelatorioHelper

			.setNomeBairro(arrayImovel[22] != null

			&& !arrayImovel[22].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[22]

			: null);

			imovelRelatorioHelper

			.setNomeLogradouro(arrayImovel[23] != null

			&& !arrayImovel[23].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[23]

			: null);

			imovelRelatorioHelper

			.setTituloLogradouro(arrayImovel[24] != null

			&& !arrayImovel[24].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[24]

			: null);

			imovelRelatorioHelper

			.setTipoLogradouro(arrayImovel[25] != null

			&& !arrayImovel[25].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[25]

			: null);

			imovelRelatorioHelper

			.setNumeroQuadra(arrayImovel[26] != null

			&& !arrayImovel[26].toString().trim()

			.equalsIgnoreCase("") ? (Integer) arrayImovel[26]

			: null);

			imovelRelatorioHelper

			.setCepCodigo(arrayImovel[27] != null

			&& !arrayImovel[27].toString().trim()

			.equalsIgnoreCase("") ? (Integer) arrayImovel[27]

			: null);

			imovelRelatorioHelper

			.setCepLogradouro(arrayImovel[28] != null

			&& !arrayImovel[28].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[28]

			: null);

			imovelRelatorioHelper

			.setCepBairro(arrayImovel[29] != null

			&& !arrayImovel[29].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[29]

			: null);

			imovelRelatorioHelper

			.setCepMunicipio(arrayImovel[30] != null

			&& !arrayImovel[30].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[30]

			: null);

			imovelRelatorioHelper

			.setCepTipoLogradouro(arrayImovel[31] != null

			&& !arrayImovel[31].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[31]

			: null);

			imovelRelatorioHelper

			.setCepSigla(arrayImovel[32] != null

			&& !arrayImovel[32].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[32]

			: null);

			imovelRelatorioHelper

			.setLigacaoAguaSituacao(arrayImovel[33] != null

			&& !arrayImovel[33].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[33]

			: null);

			imovelRelatorioHelper

			.setLigacaoEsgotoSituacao(arrayImovel[34] != null

			&& !arrayImovel[34].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[34]

			: null);

			imovelRelatorioHelper

			.setTipoPavimentoCalcada(arrayImovel[35] != null

			&& !arrayImovel[35].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[35]

			: null);

			imovelRelatorioHelper

			.setTipoPavimentoRua(arrayImovel[36] != null

			&& !arrayImovel[36].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[36]

			: null);

			imovelRelatorioHelper

			.setTipoDespejo(arrayImovel[37] != null

			&& !arrayImovel[37].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[37]

			: null);

			imovelRelatorioHelper

			.setDescricaoTipoPoco(arrayImovel[38] != null

			&& !arrayImovel[38].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[38]

			: null);

			imovelRelatorioHelper

			.setNumeroHidrometroPoco(arrayImovel[39] != null

			&& !arrayImovel[39].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[39]

			: null);

			imovelRelatorioHelper

			.setAnoFabricacaoHidrometroPoco(arrayImovel[40] != null

			&& !arrayImovel[40].toString().trim()

			.equalsIgnoreCase("") ? ((Short) arrayImovel[40])

			.shortValue()

			: 0);

			imovelRelatorioHelper

			.setCapacidadeHidrometroPoco(arrayImovel[41] != null

			&& !arrayImovel[41].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[41]

			: null);

			imovelRelatorioHelper

			.setMarcaHidrometroPoco(arrayImovel[42] != null

			&& !arrayImovel[42].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[42]

			: null);

			imovelRelatorioHelper

			.setDiametroHidrometroPoco(arrayImovel[43] != null

			&& !arrayImovel[43].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[43]

			: null);

			imovelRelatorioHelper

			.setTipoHidrometroPoco(arrayImovel[44] != null

			&& !arrayImovel[44].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[44]

			: null);

			imovelRelatorioHelper

			.setDataInstalacaoHidrometroPoco(arrayImovel[45] != null

			&& !arrayImovel[45].toString().trim()

			.equalsIgnoreCase("") ? (Date) arrayImovel[45]

			: null);

			imovelRelatorioHelper

			.setLocalIstalacaoHidrometroPoco(arrayImovel[46] != null

			&& !arrayImovel[46].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[46]

			: null);

			imovelRelatorioHelper

			.setTipoProtecaoHidrometroPoco(arrayImovel[47] != null

			&& !arrayImovel[47].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[47]

			: null);

			imovelRelatorioHelper

			.setIndicadorExistenciaCavaletePoco(arrayImovel[48] != null

			&& !arrayImovel[48].toString().trim()

			.equalsIgnoreCase("") ? ((Short) arrayImovel[48])

			.shortValue()

			: 0);

			if (imovelRelatorioHelper.getIndicadorExistenciaCavaletePoco() == 1) {

				imovelRelatorioHelper

				.setIndicadorExistenciaCavaletePocoDescricao("Sim");

			} else {

				imovelRelatorioHelper

				.setIndicadorExistenciaCavaletePocoDescricao("Não");

			}

			imovelRelatorioHelper

			.setDescricaoGerenciaRegional(arrayImovel[49] != null

			&& !arrayImovel[49].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayImovel[48]

			: null);

			imovelRelatorioHelper

			.setIdGerenciaRegional(arrayImovel[49] != null

			&& !arrayImovel[49].toString().trim()

			.equalsIgnoreCase("") ? (Integer) arrayImovel[48]

			: null);

		}

		// ------ligacao agua

		if (!colecaoLigacaoAgua.isEmpty()) {

			Object[] arrayLigacaoAgua = (Object[]) colecaoLigacaoAgua

			.iterator().next();

			imovelRelatorioHelper

			.setDataLigacaoAgua(arrayLigacaoAgua[0] != null

			&& !arrayLigacaoAgua[0].toString().trim()

			.equalsIgnoreCase("") ? (Date) arrayLigacaoAgua[0]

			: null);

			imovelRelatorioHelper

			.setDiametroLigacaoAgua(arrayLigacaoAgua[1] != null

			&& !arrayLigacaoAgua[1].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayLigacaoAgua[1]

			: null);

			imovelRelatorioHelper

			.setMaterialLigacaoAgua(arrayLigacaoAgua[2] != null

			&& !arrayLigacaoAgua[2].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayLigacaoAgua[2]

			: null);

			imovelRelatorioHelper

			.setConsumoMinimoFixadoAgua(arrayLigacaoAgua[3] != null

			&& !arrayLigacaoAgua[3].toString().trim()

			.equalsIgnoreCase("") ? (Integer) arrayLigacaoAgua[3]

			: null);

			imovelRelatorioHelper

			.setNumeroHidrometroAgua(arrayLigacaoAgua[4] != null

			&& !arrayLigacaoAgua[4].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayLigacaoAgua[4]

			: null);

			imovelRelatorioHelper

			.setAnoFabricaocaHidrometroAgua(arrayLigacaoAgua[5] != null

			&& !arrayLigacaoAgua[5].toString().trim()

			.equalsIgnoreCase("") ? ((Short) arrayLigacaoAgua[5])

			.shortValue()

			: 0);

			imovelRelatorioHelper

			.setCapacidadeHidrometroAgua(arrayLigacaoAgua[6] != null

			&& !arrayLigacaoAgua[6].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayLigacaoAgua[6]

			: null);

			imovelRelatorioHelper

			.setMarcaHidrometroAgua(arrayLigacaoAgua[7] != null

			&& !arrayLigacaoAgua[7].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayLigacaoAgua[7]

			: null);

			imovelRelatorioHelper

			.setDiametroHidrometroAgua(arrayLigacaoAgua[8] != null

			&& !arrayLigacaoAgua[8].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayLigacaoAgua[8]

			: null);

			imovelRelatorioHelper

			.setTipoHidrometroAgua(arrayLigacaoAgua[9] != null

			&& !arrayLigacaoAgua[9].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayLigacaoAgua[9]

			: null);

			imovelRelatorioHelper

			.setDataInstalacaoHidrometroAgua(arrayLigacaoAgua[10] != null

			&& !arrayLigacaoAgua[10].toString().trim()

			.equalsIgnoreCase("") ? (Date) arrayLigacaoAgua[10]

			: null);

			imovelRelatorioHelper

			.setLocalInstalacaoHidrometroAgua(arrayLigacaoAgua[11] != null

			&& !arrayLigacaoAgua[11].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayLigacaoAgua[11]

			: null);

			imovelRelatorioHelper

			.setTipoProtecaoHidrometroAgua(arrayLigacaoAgua[12] != null

			&& !arrayLigacaoAgua[12].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayLigacaoAgua[12]

			: null);

			imovelRelatorioHelper

			.setIndicadorExistenciaCavaleteAgua(arrayLigacaoAgua[13] != null

			&& !arrayLigacaoAgua[13].toString().trim()

			.equalsIgnoreCase("") ? ((Short) arrayLigacaoAgua[13])

			.shortValue()

			: 0);

			if (imovelRelatorioHelper.getIndicadorExistenciaCavaleteAgua() == 1) {

				imovelRelatorioHelper

				.setIndicadorExistenciaCavaleteAguaDescricao("Sim");

			} else {

				imovelRelatorioHelper

				.setIndicadorExistenciaCavaleteAguaDescricao("Não");

			}

		}

		// ------ligacao esgoto

		if (!colecaoLigacaoEsgoto.isEmpty()) {

			Object[] arrayLigacaoEsgoto = (Object[]) colecaoLigacaoEsgoto

			.iterator().next();

			imovelRelatorioHelper

			.setDataLigacaoEsgoto(arrayLigacaoEsgoto[0] != null

			&& !arrayLigacaoEsgoto[0].toString().trim()

			.equalsIgnoreCase("") ? (Date) arrayLigacaoEsgoto[0]

			: null);

			imovelRelatorioHelper

			.setDiametroLigacaoEsgoto(arrayLigacaoEsgoto[1] != null

			&& !arrayLigacaoEsgoto[1].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayLigacaoEsgoto[1]

			: null);

			imovelRelatorioHelper

			.setMaterialLigacaoEsgoto(arrayLigacaoEsgoto[2] != null

			&& !arrayLigacaoEsgoto[2].toString().trim()

			.equalsIgnoreCase("") ? (String) arrayLigacaoEsgoto[2]

			: null);

			imovelRelatorioHelper

			.setPercentualAguaConsumidaColetada(arrayLigacaoEsgoto[3] != null

			&& !arrayLigacaoEsgoto[3].toString().trim()

			.equalsIgnoreCase("") ? (BigDecimal) arrayLigacaoEsgoto[3]

			: null);

			imovelRelatorioHelper

			.setPercentual(arrayLigacaoEsgoto[4] != null

			&& !arrayLigacaoEsgoto[4].toString().trim()

			.equalsIgnoreCase("") ? (BigDecimal) arrayLigacaoEsgoto[4]

			: null);

			imovelRelatorioHelper

			.setConsumoMinimoFixadoLigacaoEsgoto(arrayLigacaoEsgoto[5] != null

			&& !arrayLigacaoEsgoto[5].toString().trim()

			.equalsIgnoreCase("") ? (Integer) arrayLigacaoEsgoto[5]

			: null);

		}

		// ------consumo historico

		if (!colecaoConsumoHistorico.isEmpty()) {

			Object[] arrayConsumoHistorico = (Object[]) colecaoConsumoHistorico

			.iterator().next();

			imovelRelatorioHelper

			.setConsumoMedioImovel(arrayConsumoHistorico[0] != null

			&& !arrayConsumoHistorico[0].toString().trim()

			.equalsIgnoreCase("") ? (Integer) arrayConsumoHistorico[0]

			: null);

		}

		// ------subcategoria

		String[] arraySubcatgegoriasQtdEconomias = new String[colecaoSubcategoria

		.size()];

		if (!colecaoSubcategoria.isEmpty()) {

			Iterator iterator = colecaoSubcategoria.iterator();

			int i = 0;

			while (iterator.hasNext()) {

				Object[] arraySubcategoria = (Object[]) iterator.next();

				arraySubcatgegoriasQtdEconomias[i] = arraySubcategoria[0]

				.toString()

				+ "/" + arraySubcategoria[1].toString();

				i++;

			}

			imovelRelatorioHelper

			.setSubcategoriaQtdEconomia(arraySubcatgegoriasQtdEconomias);

		}

		imovelRelatorioHelper

				.setClienteUsuarioResponsavel(popularClienteUsuarioResponsavel(colecaoClienteUsuarioResponsavel));

		return imovelRelatorioHelper;

	}

	/**
	 * 
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * 
	 * 
	 * @author Rhawi Dantas
	 * 
	 * @created 09/01/2006
	 * 
	 * 
	 * 
	 */

	public Collection pesquisarImovelSituacaoEspecialFaturamento(String valor,

	SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper)

	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String consulta = null;

		// Escolha de que tipo de consulta será

		if (valor.equals("COM")
				&& ((situacaoEspecialFaturamentoHelper.getIndicadorComercial() == null 
						|| situacaoEspecialFaturamentoHelper
						.getIndicadorComercial().toString().equals(
								"" + ConstantesSistema.NAO))
				&& (situacaoEspecialFaturamentoHelper.getIndicadorIndustrial() == null 
						|| situacaoEspecialFaturamentoHelper
						.getIndicadorIndustrial().toString().equals(
								"" + ConstantesSistema.NAO))
				&& (situacaoEspecialFaturamentoHelper.getIndicadorPublico() == null 
						|| situacaoEspecialFaturamentoHelper
						.getIndicadorPublico().toString().equals(
								"" + ConstantesSistema.NAO))				
				&& (situacaoEspecialFaturamentoHelper.getIndicadorResidencial() == null 
						|| situacaoEspecialFaturamentoHelper
						.getIndicadorResidencial().toString().equals(
								"" + ConstantesSistema.NAO)))
								
		) {

			consulta = "select imovel.id, imovel.ultimaAlteracao  " 
					
					+ "from Imovel imovel "

					+ "inner join imovel.localidade lo "

					+ "inner join imovel.setorComercial sc "

					+ "inner join imovel.quadra qu "

					+ "INNER JOIN qu.rota rota "

					+ "left join imovel.faturamentoSituacaoTipo fst "
					
					+ "left join imovel.ligacaoAgua lagu "

					+ "where fst.id is not null and (imovel.indicadorExclusao <> :idExclusao OR imovel.indicadorExclusao is null) and ";
			
			if(situacaoEspecialFaturamentoHelper.getIndicadorConsumoImovel().equals("1")){
				consulta = consulta + " lagu.hidrometroInstalacaoHistorico.id is not null and ";
			}else if(situacaoEspecialFaturamentoHelper.getIndicadorConsumoImovel().equals("2")){
				consulta = consulta + " lagu.hidrometroInstalacaoHistorico.id is null and ";
			}

		} else if (!valor.equals("COM")
				&& ((situacaoEspecialFaturamentoHelper.getIndicadorComercial() == null 
						|| situacaoEspecialFaturamentoHelper
						.getIndicadorComercial().toString().equals(
								"" + ConstantesSistema.NAO))
				&& (situacaoEspecialFaturamentoHelper.getIndicadorIndustrial() == null 
						|| situacaoEspecialFaturamentoHelper
						.getIndicadorIndustrial().toString().equals(
								"" + ConstantesSistema.NAO))
				&& (situacaoEspecialFaturamentoHelper.getIndicadorPublico() == null 
						|| situacaoEspecialFaturamentoHelper
						.getIndicadorPublico().toString().equals(
								"" + ConstantesSistema.NAO))				
				&& (situacaoEspecialFaturamentoHelper.getIndicadorResidencial() == null 
						|| situacaoEspecialFaturamentoHelper
						.getIndicadorResidencial().toString().equals(
								"" + ConstantesSistema.NAO)))) {

			consulta = "select imovel.id, imovel.ultimaAlteracao  " 
					+ "from Imovel imovel "

					+ "inner join imovel.localidade lo "

					+ "inner join imovel.setorComercial sc "

					+ "inner join imovel.quadra qu "

					+ "INNER JOIN qu.rota rota "

					+ "left join imovel.faturamentoSituacaoTipo fst "
					
					+ "left join imovel.ligacaoAgua lagu "

					+ "where fst.id is null and (imovel.indicadorExclusao <> :idExclusao OR imovel.indicadorExclusao is null) and ";
			
		
			if(situacaoEspecialFaturamentoHelper.getIndicadorConsumoImovel().equals("1")){
				consulta = consulta + " lagu.hidrometroInstalacaoHistorico.id is not null and ";
			}else if(situacaoEspecialFaturamentoHelper.getIndicadorConsumoImovel().equals("2")){
				consulta = consulta + " lagu.hidrometroInstalacaoHistorico.id is null and ";
			}
		
		} else if (valor.equals("COM")
				&& ((situacaoEspecialFaturamentoHelper.getIndicadorComercial() != null 
						&& !situacaoEspecialFaturamentoHelper
						.getIndicadorComercial().toString().equals(
								"" + ConstantesSistema.NAO))
				||(situacaoEspecialFaturamentoHelper.getIndicadorIndustrial() != null 
						&& !situacaoEspecialFaturamentoHelper
						.getIndicadorIndustrial().toString().equals(
								"" + ConstantesSistema.NAO))
				|| (situacaoEspecialFaturamentoHelper.getIndicadorPublico() != null 
						&& !situacaoEspecialFaturamentoHelper
						.getIndicadorPublico().toString().equals(
								"" + ConstantesSistema.NAO))			
				|| (situacaoEspecialFaturamentoHelper.getIndicadorResidencial() != null 
						&& !situacaoEspecialFaturamentoHelper
						.getIndicadorResidencial().toString().equals(
								"" + ConstantesSistema.NAO)))) {

			consulta = "select distinct imovelSubcategoria.comp_id.imovel.id , imovel.ultimaAlteracao  " 
					
				+ "from ImovelSubcategoria imovelSubcategoria "

					+ "left  join  imovelSubcategoria.comp_id.subcategoria subcategoria "

					+ "inner join imovelSubcategoria.comp_id.imovel imovel "

					+ "inner join imovel.localidade lo "

					+ "inner join imovel.setorComercial sc "

					+ "inner join imovel.quadra qu "

					+ "INNER JOIN qu.rota rota "

					+ "left join imovel.faturamentoSituacaoTipo fst "

					+ "where subcategoria.categoria.id in ( ";
			
			boolean aux = false;
			
			if (situacaoEspecialFaturamentoHelper.getIndicadorComercial() != null
					&& situacaoEspecialFaturamentoHelper
							.getIndicadorComercial().toString().equals(
									"" + ConstantesSistema.SIM)) {
				consulta = consulta + " " + Categoria.COMERCIAL;
				aux = true;
			}
			if (situacaoEspecialFaturamentoHelper.getIndicadorIndustrial() != null
					&& situacaoEspecialFaturamentoHelper
							.getIndicadorIndustrial().toString().equals(
									"" + ConstantesSistema.SIM) && aux) {
				consulta = consulta + ", " + Categoria.INDUSTRIAL ;
				
			
			} else if (situacaoEspecialFaturamentoHelper
					.getIndicadorIndustrial() != null
					&& situacaoEspecialFaturamentoHelper
							.getIndicadorIndustrial().toString().equals(
									"" + ConstantesSistema.SIM) && !aux) {
				consulta = consulta + " " + Categoria.INDUSTRIAL;
				aux = true;
			}
			if (situacaoEspecialFaturamentoHelper.getIndicadorPublico() != null
					&& situacaoEspecialFaturamentoHelper.getIndicadorPublico()
							.toString().equals("" + ConstantesSistema.SIM) && aux) {
				
				consulta = consulta + ", " + Categoria.PUBLICO ;
				
			}else if (situacaoEspecialFaturamentoHelper.getIndicadorPublico() != null
					&& situacaoEspecialFaturamentoHelper.getIndicadorPublico()
					.toString().equals("" + ConstantesSistema.SIM) && !aux) {
				
				consulta = consulta + " " + Categoria.PUBLICO ;
				aux = true;
		
			}
			if (situacaoEspecialFaturamentoHelper.getIndicadorResidencial() != null
					&& situacaoEspecialFaturamentoHelper
							.getIndicadorResidencial().toString().equals(
									"" + ConstantesSistema.SIM)&& aux) {
				consulta = consulta + ", " + Categoria.RESIDENCIAL ;
				
			}else if (situacaoEspecialFaturamentoHelper.getIndicadorResidencial() != null
					&& situacaoEspecialFaturamentoHelper
					.getIndicadorResidencial().toString().equals(
							"" + ConstantesSistema.SIM)&& !aux) {
				consulta = consulta + " " + Categoria.RESIDENCIAL + " ";
			}
					
					
					consulta = consulta + ") and fst.id is not null and (imovel.indicadorExclusao <> :idExclusao OR imovel.indicadorExclusao is null) and";

		} else if (!valor.equals("COM")
				&& ((situacaoEspecialFaturamentoHelper.getIndicadorComercial() != null 
						&& !situacaoEspecialFaturamentoHelper
						.getIndicadorComercial().toString().equals(
								"" + ConstantesSistema.NAO))
				||(situacaoEspecialFaturamentoHelper.getIndicadorIndustrial() != null 
						&& !situacaoEspecialFaturamentoHelper
						.getIndicadorIndustrial().toString().equals(
								"" + ConstantesSistema.NAO))
				|| (situacaoEspecialFaturamentoHelper.getIndicadorPublico() != null 
						&& !situacaoEspecialFaturamentoHelper
						.getIndicadorPublico().toString().equals(
								"" + ConstantesSistema.NAO))			
				|| (situacaoEspecialFaturamentoHelper.getIndicadorResidencial() != null 
						&& !situacaoEspecialFaturamentoHelper
						.getIndicadorResidencial().toString().equals(
								"" + ConstantesSistema.NAO)))) {
			consulta = "select distinct imovelSubcategoria.comp_id.imovel.id , imovel.ultimaAlteracao  " 
					
					+ "from ImovelSubcategoria imovelSubcategoria "

					+ "left  join  imovelSubcategoria.comp_id.subcategoria subcategoria "

					+ "inner join imovelSubcategoria.comp_id.imovel imovel "

					+ "inner join imovel.localidade lo "

					+ "inner join imovel.setorComercial sc "

					+ "inner join imovel.quadra qu "

					+ "INNER JOIN qu.rota rota "

					+ "left join imovel.faturamentoSituacaoTipo fst "

					+ "where subcategoria.categoria.id in (  ";
			
			boolean aux = false;
			
			if (situacaoEspecialFaturamentoHelper.getIndicadorComercial() != null
					&& situacaoEspecialFaturamentoHelper
							.getIndicadorComercial().toString().equals(
									"" + ConstantesSistema.SIM)) {
				consulta = consulta + " " + Categoria.COMERCIAL;
				aux = true;
			}
			if (situacaoEspecialFaturamentoHelper.getIndicadorIndustrial() != null
					&& situacaoEspecialFaturamentoHelper
							.getIndicadorIndustrial().toString().equals(
									"" + ConstantesSistema.SIM) && aux) {
				consulta = consulta + ", " + Categoria.INDUSTRIAL ;
				
			
			} else if (situacaoEspecialFaturamentoHelper
					.getIndicadorIndustrial() != null
					&& situacaoEspecialFaturamentoHelper
							.getIndicadorIndustrial().toString().equals(
									"" + ConstantesSistema.SIM) && !aux) {
				consulta = consulta + " " + Categoria.INDUSTRIAL;
				aux = true;
			}
			if (situacaoEspecialFaturamentoHelper.getIndicadorPublico() != null
					&& situacaoEspecialFaturamentoHelper.getIndicadorPublico()
							.toString().equals("" + ConstantesSistema.SIM) && aux) {
				
				consulta = consulta + ", " + Categoria.PUBLICO ;
				
			}else if (situacaoEspecialFaturamentoHelper.getIndicadorPublico() != null
					&& situacaoEspecialFaturamentoHelper.getIndicadorPublico()
					.toString().equals("" + ConstantesSistema.SIM) && !aux) {
				
				consulta = consulta + " " + Categoria.PUBLICO ;
				aux = true;
		
			}
			if (situacaoEspecialFaturamentoHelper.getIndicadorResidencial() != null
					&& situacaoEspecialFaturamentoHelper
							.getIndicadorResidencial().toString().equals(
									"" + ConstantesSistema.SIM)&& aux) {
				consulta = consulta + ", " + Categoria.RESIDENCIAL ;
				
			}else if (situacaoEspecialFaturamentoHelper.getIndicadorResidencial() != null
					&& situacaoEspecialFaturamentoHelper
					.getIndicadorResidencial().toString().equals(
							"" + ConstantesSistema.SIM)&& !aux) {
				consulta = consulta + " " + Categoria.RESIDENCIAL + " ";
			}
					
					
				consulta = consulta + ") and fst.id is null and (imovel.indicadorExclusao <> :idExclusao OR imovel.indicadorExclusao is null) and ";

		}

		String idImovel = situacaoEspecialFaturamentoHelper.getIdImovel();

		String idLocalidadeOrigem = situacaoEspecialFaturamentoHelper

		.getLocalidadeOrigemID();

		String idLocalidadeDestino = situacaoEspecialFaturamentoHelper

		.getLocalidadeDestinoID();

		String setorComercialOrigemCD = situacaoEspecialFaturamentoHelper

		.getSetorComercialOrigemCD();

		String setorComercialDestinoCD = situacaoEspecialFaturamentoHelper

		.getSetorComercialDestinoCD();

		String quadraOrigemNM = situacaoEspecialFaturamentoHelper

		.getQuadraOrigemNM();
		
		String quadraDestinoNM = situacaoEspecialFaturamentoHelper

		.getQuadraDestinoNM();
		
		String loteOrigem = situacaoEspecialFaturamentoHelper.getLoteOrigem();

		String loteDestino = situacaoEspecialFaturamentoHelper.getLoteDestino();

		String subLoteOrigem = situacaoEspecialFaturamentoHelper

		.getSubloteOrigem();

		String subLoteDestino = situacaoEspecialFaturamentoHelper

		.getSubloteDestino();

		String codigoRotaInicial = situacaoEspecialFaturamentoHelper

		.getCodigoRotaInicial();

		String codigoRotaFinal = situacaoEspecialFaturamentoHelper

		.getCodigoRotaFinal();

		String sequencialRotaInicial = situacaoEspecialFaturamentoHelper

		.getSequencialRotaInicial();

		String sequencialRotaFinal = situacaoEspecialFaturamentoHelper

		.getSequencialRotaFinal();

		if (idImovel != null && !idImovel.equals("")) {

			consulta += " imovel.id = " + idImovel + " and";

		}

		if (!idLocalidadeOrigem.equalsIgnoreCase("")

		&& !idLocalidadeDestino.equalsIgnoreCase(""))

			consulta += " lo.id between " + idLocalidadeOrigem + " and "

			+ idLocalidadeDestino + " and";

		if (!setorComercialOrigemCD.equalsIgnoreCase("")

		&& !setorComercialDestinoCD.equalsIgnoreCase(""))

			consulta += " sc.codigo between " + setorComercialOrigemCD + " and "

			+ setorComercialDestinoCD + " and";

		if (!quadraOrigemNM.equalsIgnoreCase("")

		&& !quadraDestinoNM.equalsIgnoreCase(""))

			consulta += " qu.numeroQuadra between " + quadraOrigemNM + " and "

			+ quadraDestinoNM + " and";

		if (!loteOrigem.equalsIgnoreCase("")

		&& !loteDestino.equalsIgnoreCase(""))

			consulta += " imovel.lote between " + new Integer(loteOrigem)
					+ " and "

					+ new Integer(loteDestino) + " and";

		if (!subLoteOrigem.equalsIgnoreCase("")

		&& !subLoteDestino.equalsIgnoreCase(""))

			consulta += " imovel.subLote between " + new Integer(subLoteOrigem)
					+ " and "

					+ new Integer(subLoteDestino) + " and";

		if ((codigoRotaInicial != null && !codigoRotaInicial.equals(""))

		&& (codigoRotaFinal != null && !codigoRotaFinal.equals(""))) {

			consulta = consulta + " rota.codigo between "
					+ new Integer(codigoRotaInicial)

					+ " and " + new Integer(codigoRotaFinal) + " and";

		}

		if ((sequencialRotaInicial != null && !sequencialRotaInicial.equals(""))

				&& (sequencialRotaFinal != null && !sequencialRotaFinal

				.equals(""))) {

			consulta = consulta + " imovel.numeroSequencialRota between "

			+ new Integer(sequencialRotaInicial) + " and "
					+ new Integer(sequencialRotaFinal)

					+ " and";

		}

		System.out.println(Util.removerUltimosCaracteres(consulta, 4));

		// Consulta

		try {

			return session.createQuery(Util.removerUltimosCaracteres(consulta, 4)).setShort(

			"idExclusao", Imovel.IMOVEL_EXCLUIDO).list();

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
	 * [UC0177] Informar Situacao Especial de Cobrança
	 * 
	 * 
	 * 
	 * @author Sávio Luiz
	 * 
	 * @created 07/03/2006
	 * 
	 * 
	 * 
	 */

	public Collection pesquisarImovelSituacaoEspecialCobranca(String valor,

	SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper)

	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String consulta = null;

		// Escolha de que tipo de consulta será

		if (valor.equals("COM")
				&& (situacaoEspecialCobrancaHelper.getIdCategoria() == null 
								&& ((situacaoEspecialCobrancaHelper.getIndicadorComercial() == null 
						|| situacaoEspecialCobrancaHelper
						.getIndicadorComercial().toString().equals(
								"" + ConstantesSistema.NAO))
				&& (situacaoEspecialCobrancaHelper.getIndicadorIndustrial() == null 
						|| situacaoEspecialCobrancaHelper
						.getIndicadorIndustrial().toString().equals(
								"" + ConstantesSistema.NAO))
				&& (situacaoEspecialCobrancaHelper.getIndicadorPublico() == null 
						|| situacaoEspecialCobrancaHelper
						.getIndicadorPublico().toString().equals(
								"" + ConstantesSistema.NAO))				
				&& (situacaoEspecialCobrancaHelper.getIndicadorResidencial() == null 
						|| situacaoEspecialCobrancaHelper
						.getIndicadorResidencial().toString().equals(
								"" + ConstantesSistema.NAO))))
		) {

			consulta = "select imovel.id, imovel.ultimaAlteracao " 
					
					+ "from Imovel imovel "

					+ "inner join imovel.localidade lo "

					+ "inner join imovel.setorComercial sc "

					+ "inner join imovel.quadra qu "

					+ "INNER JOIN qu.rota rota "

					+ "left join imovel.cobrancaSituacaoTipo cst "

					+ "where cst.id is not null and (imovel.indicadorExclusao <> :idExclusao OR imovel.indicadorExclusao is null) and ";
		
		} else if (!valor.equals("COM")
				&& ((situacaoEspecialCobrancaHelper.getIndicadorComercial() == null 
						|| situacaoEspecialCobrancaHelper
						.getIndicadorComercial().toString().equals(
								"" + ConstantesSistema.NAO))
				&& (situacaoEspecialCobrancaHelper.getIndicadorIndustrial() == null 
						|| situacaoEspecialCobrancaHelper
						.getIndicadorIndustrial().toString().equals(
								"" + ConstantesSistema.NAO))
				&& (situacaoEspecialCobrancaHelper.getIndicadorPublico() == null 
						|| situacaoEspecialCobrancaHelper
						.getIndicadorPublico().toString().equals(
								"" + ConstantesSistema.NAO))				
				&& (situacaoEspecialCobrancaHelper.getIndicadorResidencial() == null 
						|| situacaoEspecialCobrancaHelper
						.getIndicadorResidencial().toString().equals(
								"" + ConstantesSistema.NAO)))) {

			consulta = "select imovel.id, imovel.ultimaAlteracao " 
				
					+ "from Imovel imovel "

					+ "inner join imovel.localidade lo "

					+ "inner join imovel.setorComercial sc "

					+ "inner join imovel.quadra qu "

					+ "INNER JOIN qu.rota rota "

					+ "left join imovel.cobrancaSituacaoTipo cst "

					+ "where cst.id is null and (imovel.indicadorExclusao <> :idExclusao OR imovel.indicadorExclusao is null) and ";
		
		} else if (valor.equals("COM")
				&& ((situacaoEspecialCobrancaHelper.getIndicadorComercial() != null 
						&& !situacaoEspecialCobrancaHelper
						.getIndicadorComercial().toString().equals(
								"" + ConstantesSistema.NAO))
				||(situacaoEspecialCobrancaHelper.getIndicadorIndustrial() != null 
						&& !situacaoEspecialCobrancaHelper
						.getIndicadorIndustrial().toString().equals(
								"" + ConstantesSistema.NAO))
				|| (situacaoEspecialCobrancaHelper.getIndicadorPublico() != null 
						&& !situacaoEspecialCobrancaHelper
						.getIndicadorPublico().toString().equals(
								"" + ConstantesSistema.NAO))			
				|| (situacaoEspecialCobrancaHelper.getIndicadorResidencial() != null 
						&& !situacaoEspecialCobrancaHelper
						.getIndicadorResidencial().toString().equals(
								"" + ConstantesSistema.NAO))))  {

			consulta = "select distinct imovelSubcategoria.comp_id.imovel.id, imovel.ultimaAlteracao " 
				
					+ "from ImovelSubcategoria imovelSubcategoria "

					+ "left  join  imovelSubcategoria.comp_id.subcategoria subcategoria "

					+ "inner join imovelSubcategoria.comp_id.imovel imovel "

					+ "inner join imovel.localidade lo "

					+ "inner join imovel.setorComercial sc "

					+ "inner join imovel.quadra qu "

					+ "INNER JOIN qu.rota rota "

					+ "left join imovel.cobrancaSituacaoTipo cst "

					+ "where subcategoria.categoria.id in ( ";
					
					boolean aux = false;
					
					if (situacaoEspecialCobrancaHelper.getIndicadorComercial() != null
							&& situacaoEspecialCobrancaHelper
									.getIndicadorComercial().toString().equals(
											"" + ConstantesSistema.SIM)) {
						consulta = consulta + " " + Categoria.COMERCIAL;
						aux = true;
					}
					if (situacaoEspecialCobrancaHelper.getIndicadorIndustrial() != null
							&& situacaoEspecialCobrancaHelper
									.getIndicadorIndustrial().toString().equals(
											"" + ConstantesSistema.SIM) && aux) {
						consulta = consulta + ", " + Categoria.INDUSTRIAL ;
						
					
					} else if (situacaoEspecialCobrancaHelper
							.getIndicadorIndustrial() != null
							&& situacaoEspecialCobrancaHelper
									.getIndicadorIndustrial().toString().equals(
											"" + ConstantesSistema.SIM) && !aux) {
						consulta = consulta + " " + Categoria.INDUSTRIAL;
						aux = true;
					}
					if (situacaoEspecialCobrancaHelper.getIndicadorPublico() != null
							&& situacaoEspecialCobrancaHelper.getIndicadorPublico()
									.toString().equals("" + ConstantesSistema.SIM) && aux) {
						
						consulta = consulta + ", " + Categoria.PUBLICO ;
						
					}else if (situacaoEspecialCobrancaHelper.getIndicadorPublico() != null
							&& situacaoEspecialCobrancaHelper.getIndicadorPublico()
							.toString().equals("" + ConstantesSistema.SIM) && !aux) {
						
						consulta = consulta + " " + Categoria.PUBLICO ;
						aux = true;
				
					}
					if (situacaoEspecialCobrancaHelper.getIndicadorResidencial() != null
							&& situacaoEspecialCobrancaHelper
									.getIndicadorResidencial().toString().equals(
											"" + ConstantesSistema.SIM)&& aux) {
						consulta = consulta + ", " + Categoria.RESIDENCIAL ;
						
					}else if (situacaoEspecialCobrancaHelper.getIndicadorResidencial() != null
							&& situacaoEspecialCobrancaHelper
							.getIndicadorResidencial().toString().equals(
									"" + ConstantesSistema.SIM)&& !aux) {
						consulta = consulta + " " + Categoria.RESIDENCIAL + " ";
					}
							
							
					consulta = consulta + ") and cst.id is not null and (imovel.indicadorExclusao <> :idExclusao OR imovel.indicadorExclusao is null) and ";

		} else if (!valor.equals("COM")
				&& ((situacaoEspecialCobrancaHelper.getIndicadorComercial() != null 
						&& !situacaoEspecialCobrancaHelper
						.getIndicadorComercial().toString().equals(
								"" + ConstantesSistema.NAO))
				||(situacaoEspecialCobrancaHelper.getIndicadorIndustrial() != null 
						&& !situacaoEspecialCobrancaHelper
						.getIndicadorIndustrial().toString().equals(
								"" + ConstantesSistema.NAO))
				|| (situacaoEspecialCobrancaHelper.getIndicadorPublico() != null 
						&& !situacaoEspecialCobrancaHelper
						.getIndicadorPublico().toString().equals(
								"" + ConstantesSistema.NAO))			
				|| (situacaoEspecialCobrancaHelper.getIndicadorResidencial() != null 
						&& !situacaoEspecialCobrancaHelper
						.getIndicadorResidencial().toString().equals(
								"" + ConstantesSistema.NAO)))) {

			consulta = "select distinct imovelSubcategoria.comp_id.imovel.id, imovel.ultimaAlteracao " 
				
					+ "from ImovelSubcategoria imovelSubcategoria "

					+ "left  join  imovelSubcategoria.comp_id.subcategoria subcategoria "

					+ "inner join imovelSubcategoria.comp_id.imovel imovel "

					+ "inner join imovel.localidade lo "

					+ "inner join imovel.setorComercial sc "

					+ "inner join imovel.quadra qu "

					+ "INNER JOIN qu.rota rota "

					+ "left join imovel.cobrancaSituacaoTipo cst "

					+ "where subcategoria.categoria.id in (  ";
					
					boolean aux = false;
					
					if (situacaoEspecialCobrancaHelper.getIndicadorComercial() != null
							&& situacaoEspecialCobrancaHelper
									.getIndicadorComercial().toString().equals(
											"" + ConstantesSistema.SIM)) {
						consulta = consulta + " " + Categoria.COMERCIAL;
						aux = true;
					}
					if (situacaoEspecialCobrancaHelper.getIndicadorIndustrial() != null
							&& situacaoEspecialCobrancaHelper
									.getIndicadorIndustrial().toString().equals(
											"" + ConstantesSistema.SIM) && aux) {
						consulta = consulta + ", " + Categoria.INDUSTRIAL ;
						
					
					} else if (situacaoEspecialCobrancaHelper
							.getIndicadorIndustrial() != null
							&& situacaoEspecialCobrancaHelper
									.getIndicadorIndustrial().toString().equals(
											"" + ConstantesSistema.SIM) && !aux) {
						consulta = consulta + " " + Categoria.INDUSTRIAL;
						aux = true;
					}
					if (situacaoEspecialCobrancaHelper.getIndicadorPublico() != null
							&& situacaoEspecialCobrancaHelper.getIndicadorPublico()
									.toString().equals("" + ConstantesSistema.SIM) && aux) {
						
						consulta = consulta + ", " + Categoria.PUBLICO ;
						
					}else if (situacaoEspecialCobrancaHelper.getIndicadorPublico() != null
							&& situacaoEspecialCobrancaHelper.getIndicadorPublico()
							.toString().equals("" + ConstantesSistema.SIM) && !aux) {
						
						consulta = consulta + " " + Categoria.PUBLICO ;
						aux = true;
				
					}
					if (situacaoEspecialCobrancaHelper.getIndicadorResidencial() != null
							&& situacaoEspecialCobrancaHelper
									.getIndicadorResidencial().toString().equals(
											"" + ConstantesSistema.SIM)&& aux) {
						consulta = consulta + ", " + Categoria.RESIDENCIAL ;
						
					}else if (situacaoEspecialCobrancaHelper.getIndicadorResidencial() != null
							&& situacaoEspecialCobrancaHelper
							.getIndicadorResidencial().toString().equals(
									"" + ConstantesSistema.SIM)&& !aux) {
						consulta = consulta + " " + Categoria.RESIDENCIAL + " ";
					}
							
							
					consulta = consulta + ") and cst.id is null and (imovel.indicadorExclusao <> :idExclusao OR imovel.indicadorExclusao is null) and ";
		}

		String idImovel = situacaoEspecialCobrancaHelper.getIdImovel();

		String idLocalidadeOrigem = situacaoEspecialCobrancaHelper

		.getLocalidadeOrigemID();

		String idLocalidadeDestino = situacaoEspecialCobrancaHelper

		.getLocalidadeDestinoID();

		String setorComercialOrigemCD = situacaoEspecialCobrancaHelper

		.getSetorComercialOrigemCD();

		String setorComercialDestinoCD = situacaoEspecialCobrancaHelper

		.getSetorComercialDestinoCD();

		String quadraOrigemNM = situacaoEspecialCobrancaHelper

		.getQuadraOrigemNM();

		String quadraDestinoNM = situacaoEspecialCobrancaHelper

		.getQuadraDestinoNM();

		String loteOrigem = situacaoEspecialCobrancaHelper.getLoteOrigem();

		String loteDestino = situacaoEspecialCobrancaHelper.getLoteDestino();

		String subLoteOrigem = situacaoEspecialCobrancaHelper

		.getSubloteOrigem();

		String subLoteDestino = situacaoEspecialCobrancaHelper

		.getSubloteDestino();

		String codigoRotaInicial = situacaoEspecialCobrancaHelper

		.getCodigoRotaInicial();

		String codigoRotaFinal = situacaoEspecialCobrancaHelper

		.getCodigoRotaFinal();

		String sequencialRotaInicial = situacaoEspecialCobrancaHelper

		.getSequencialRotaInicial();

		String sequencialRotaFinal = situacaoEspecialCobrancaHelper

		.getSequencialRotaFinal();

		if (!idImovel.equalsIgnoreCase("") && !idImovel.equalsIgnoreCase(""))

			consulta += " imovel.id = " + idImovel + " and";

		if (!idLocalidadeOrigem.equalsIgnoreCase("")

		&& !idLocalidadeDestino.equalsIgnoreCase(""))

			consulta += " lo.id between " + idLocalidadeOrigem + " and "

			+ idLocalidadeDestino + " and";

		if (!setorComercialOrigemCD.equalsIgnoreCase("")

		&& !setorComercialOrigemCD.equalsIgnoreCase(""))

			consulta += " sc.codigo between " + setorComercialOrigemCD + " and "

			+ setorComercialDestinoCD + " and";

		if (!quadraOrigemNM.equalsIgnoreCase("")

		&& !quadraOrigemNM.equalsIgnoreCase(""))

			consulta += " qu.numeroQuadra between " + quadraOrigemNM + " and "

			+ quadraDestinoNM + " and";

		if (!loteOrigem.equalsIgnoreCase("")

		&& !loteOrigem.equalsIgnoreCase(""))

			consulta += " imovel.lote between " + loteOrigem + " and "

			+ loteDestino + " and";

		if (!subLoteOrigem.equalsIgnoreCase("")

		&& !subLoteOrigem.equalsIgnoreCase(""))

			consulta += " imovel.subLote between " + subLoteOrigem + " and "

			+ subLoteDestino + " and";

		if ((codigoRotaInicial != null && !codigoRotaInicial.equals(""))

		&& (codigoRotaFinal != null && !codigoRotaFinal.equals(""))) {

			consulta = consulta + " rota.codigo between " + codigoRotaInicial

			+ " and " + codigoRotaFinal + " and";

		}

		if ((sequencialRotaInicial != null && !sequencialRotaInicial.equals(""))

				&& (sequencialRotaFinal != null && !sequencialRotaFinal

				.equals(""))) {

			consulta = consulta + " imovel.numeroSequencialRota between "

			+ sequencialRotaInicial + " and " + sequencialRotaFinal

			+ " and";

		}

		System.out.println(Util.removerUltimosCaracteres(consulta, 4));

		// Consulta

		try {

			return session.createQuery(Util.removerUltimosCaracteres(consulta, 4)).setShort(

			"idExclusao", Imovel.IMOVEL_EXCLUIDO).list();

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

	}

	public Integer verificarExistenciaImovel(Integer idImovel)

	throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "select count(imovel.id) "

					+ "from Imovel imovel "

					+ "where imovel.id = :idImovel and (imovel.indicadorExclusao is null OR imovel.indicadorExclusao <> :idExclusao)";

			retorno = (Integer) session.createQuery(consulta).setInteger(

			"idImovel", idImovel.intValue()).setShort("idExclusao",

			Imovel.IMOVEL_EXCLUIDO).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	public Integer recuperarMatriculaImovel(Integer idImovel)

	throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "select imovel.id "

					+ "from Imovel imovel "

					+ "where imovel.id = :idImovel and (imovel.indicadorExclusao is null OR imovel.indicadorExclusao <> :idExclusao)";

			retorno = (Integer) session.createQuery(consulta).setInteger(

			"idImovel", idImovel.intValue()).setShort("idExclusao",

			Imovel.IMOVEL_EXCLUIDO).setMaxResults(1).uniqueResult();

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
	 * [UC0457] - Encerrar Ordem de Serviço.
	 * [SB0009] - Verificar Situação Especial de Faturamento.
	 * 
	 * Verifica se um imóvel está em situação especial de faturamento
	 * para um dado imovel (idImovel). 
	 * A situação especial de faturamento tem o ftst_id = 2
	 * 
	 * @param idImovel
	 * @return Imovel
	 * @throws ErroRepositorioException
	 */
	public Imovel pesquisarImovelSituacaoEspecialFaturamento(Integer idImovel) throws ErroRepositorioException {

		Imovel imovel = null;
		Session session = HibernateUtil.getSession();
		StringBuilder sb = new StringBuilder();
		
		try {

			sb.append("SELECT historico.imovel ");
			sb.append("FROM gcom.faturamento.FaturamentoSituacaoHistorico historico ");
			sb.append("INNER JOIN historico.faturamentoSituacaoMotivo motivo ");
			sb.append("INNER JOIN historico.imovel imovel ");
			sb.append("INNER JOIN imovel.faturamentoSituacaoTipo sitTipo ");
			sb.append("WHERE imovel.id = :idImovel AND sitTipo.id = 2 AND motivo.id = 14 ");

			List<Imovel> imoveis = (List<Imovel>) session.createQuery(sb.toString()).setInteger("idImovel", idImovel).list();
			if(!Util.isVazioOrNulo(imoveis)){
				imovel = imoveis.get(0);
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return imovel;
	}
	
	// --------------rhawi e flavio

	public String criaCondicionaisHqlImovelOutrosCriterios(

	FiltrarImovelOutrosCriteriosHelper filtrarImovelOutrosCriteriosHelper) {

		String retorno = "";

		String situacaoCobrancaID = filtrarImovelOutrosCriteriosHelper.getSituacaoCobranca();
		// Ligacao de Agua Esgoto Consumo
		String intervaloMediaMinimaHidrometroInicial = filtrarImovelOutrosCriteriosHelper.getIntervaloMediaMinimaHidrometroInicio();
		String intervaloMediaMinimaHidrometroFinal = filtrarImovelOutrosCriteriosHelper.getIntervaloMediaMinimaHidrometroFinal();
		String medicaoTipo = filtrarImovelOutrosCriteriosHelper.getTipoMedicao();
		String intervaloMediaMinimaImovelInicio = filtrarImovelOutrosCriteriosHelper.getIntervaloMediaMinimaImovelInicio();
		String intervaloMediaMinimaImovelFinal = filtrarImovelOutrosCriteriosHelper.getIntervaloMediaMinimaImovelFinal();
		// Ligacao de Agua Esgoto Consumo
		// Caracteristicas do Imovel
		String consumoMinimoInicial = filtrarImovelOutrosCriteriosHelper.getConsumoMinimoInicial();
		String consumoMinimoFinal = filtrarImovelOutrosCriteriosHelper.getConsumoMinimoFinal();
		// Caracteristicas do Imovel
		// Dados Faturamento Cobranca
		String intervaloPercentualEsgotoInicial = filtrarImovelOutrosCriteriosHelper.getIntervaloPercentualEsgotoInicial();
		String intervaloPercentualEsgotoFinal = filtrarImovelOutrosCriteriosHelper.getIntervaloPercentualEsgotoFinal();
		String consumoMinimoFixadoEsgotoInicial = filtrarImovelOutrosCriteriosHelper.getAreaConstruidaInicial();
		String consumoMinimoFixadoEsgotoFinal = filtrarImovelOutrosCriteriosHelper.getAreaConstruidaFinal();
		// Dados Faturamento Cobranca
		String idClienteTipo = filtrarImovelOutrosCriteriosHelper.getIdClienteTipo();
		// String tipoRelatorio = filtrarImovelOutrosCriteriosHelper
		// .getTipoRelatorio();
		String idLocalidadeOrigem = filtrarImovelOutrosCriteriosHelper.getLocalidadeOrigemID();
		String idLocalidadeDestino = filtrarImovelOutrosCriteriosHelper.getLocalidadeDestinoID();
		String idGerenciaRegional = filtrarImovelOutrosCriteriosHelper.getIdGerenciaRegional();
		String setorComercialOrigemID = filtrarImovelOutrosCriteriosHelper.getSetorComercialOrigemID();
		String setorComercialDestinoID = filtrarImovelOutrosCriteriosHelper.getSetorComercialDestinoID();
		String quadraOrigemID = filtrarImovelOutrosCriteriosHelper.getQuadraOrigemID();
		String quadraDestinoID = filtrarImovelOutrosCriteriosHelper.getQuadraDestinoID();
		String idMunicipio = filtrarImovelOutrosCriteriosHelper.getIdMunicipio();
		String idBairro = filtrarImovelOutrosCriteriosHelper.getIdMunicipio();
		String CEP = filtrarImovelOutrosCriteriosHelper.getCEP();
		String idLogradouro = filtrarImovelOutrosCriteriosHelper.getIdLogradouro();
		String idCliente = filtrarImovelOutrosCriteriosHelper.getIdCliente();
		String idImovelCondominio = filtrarImovelOutrosCriteriosHelper.getIdImovelCondominio();
		String idImovelPrincipal = filtrarImovelOutrosCriteriosHelper.getIdImovelPrincipal();
		// String idNomeConta = filtrarImovelOutrosCriteriosHelper
		// .getIdNomeConta();
		String situacaoAgua = filtrarImovelOutrosCriteriosHelper.getSituacaoAgua();
		String situacaoLigacaoEsgoto = filtrarImovelOutrosCriteriosHelper.getSituacaoLigacaoEsgoto();
		String perfilImovel = filtrarImovelOutrosCriteriosHelper.getPerfilImovel();
		String tipoPoco = filtrarImovelOutrosCriteriosHelper.getTipoPoco();
		String tipoSituacaoEspecialFaturamento = filtrarImovelOutrosCriteriosHelper.getTipoSituacaoEspecialFaturamento();
		String situacaoCobranca = filtrarImovelOutrosCriteriosHelper.getSituacaoCobranca();
		String tipoSituacaoEspecialCobranca = filtrarImovelOutrosCriteriosHelper.getTipoSituacaoEspecialCobranca();
		String anormalidadeElo = filtrarImovelOutrosCriteriosHelper.getAnormalidadeElo();
		String ocorrenciaCadastro = filtrarImovelOutrosCriteriosHelper.getOcorrenciaCadastro();
		String tarifaConsumo = filtrarImovelOutrosCriteriosHelper.getTarifaConsumo();
		String diaVencimento = filtrarImovelOutrosCriteriosHelper.getDiaVencimentoAlternativo();
		String idCategoria = filtrarImovelOutrosCriteriosHelper.getCategoriaImovel();
		String idSubcategoria = filtrarImovelOutrosCriteriosHelper.getSubcategoria();
		// String qtdEconomiasInicial = filtrarImovelOutrosCriteriosHelper
		// .getQuantidadeEconomiasInicial();
		// String qtdEconomiasFinal = filtrarImovelOutrosCriteriosHelper
		// .getQuantidadeEconomiasFinal();
		String numeroPontosInicial = filtrarImovelOutrosCriteriosHelper.getNumeroPontosInicial();
		String numeroPontosFinal = filtrarImovelOutrosCriteriosHelper.getNumeroPontosFinal();
		String numeroMoradoresInicial = filtrarImovelOutrosCriteriosHelper.getNumeroMoradoresInicial();
		String numeroMoradoresFinal = filtrarImovelOutrosCriteriosHelper.getNumeroMoradoresFinal();
		String areaConstruidaInicial = filtrarImovelOutrosCriteriosHelper.getAreaConstruidaInicial();
		String areaConstruidaFinal = filtrarImovelOutrosCriteriosHelper.getAreaConstruidaFinal();
		String areaConstruidaFaixa = filtrarImovelOutrosCriteriosHelper.getAreaConstruidaFaixa();
		String pocoTipo = filtrarImovelOutrosCriteriosHelper.getTipoPoco();
		String composicaoHql = "";

		if (idGerenciaRegional != null && !idGerenciaRegional.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")
				&& !idGerenciaRegional.equals("")) {

			composicaoHql += " gr.id = " + idGerenciaRegional + " and ";
		}
		
		if (idLocalidadeOrigem != null && !idLocalidadeDestino.equalsIgnoreCase("")) {

			composicaoHql += " lo.id between " + idLocalidadeOrigem + " and "

			+ idLocalidadeDestino + " and";
		}
		
		if (setorComercialOrigemID != null && !setorComercialOrigemID.equalsIgnoreCase("")) {

			composicaoHql += " sc.id between " + setorComercialOrigemID

			+ " and " + setorComercialDestinoID + " and";
		}
		
		if (quadraOrigemID != null && !quadraOrigemID.equalsIgnoreCase("")) {

			composicaoHql += " qu.id between " + quadraOrigemID + " and "

			+ quadraDestinoID + " and";
		}
		
		if (idMunicipio != null && !idMunicipio.equalsIgnoreCase(""))

			composicaoHql += " mu.id = " + idMunicipio + " and ";

		if (idBairro != null && !idBairro.equalsIgnoreCase(""))

			composicaoHql += " ba.id = " + idBairro + " and";

		if (CEP != null && !CEP.equalsIgnoreCase(""))

			composicaoHql += " cep.id = " + CEP + " and ";

		if (idLogradouro != null && !idLogradouro.equalsIgnoreCase(""))

			composicaoHql += " lg.id = " + idLogradouro + " and ";

		if (idImovelCondominio != null

		&& !idImovelCondominio.equalsIgnoreCase(""))

			composicaoHql += " ic.id = " + idImovelCondominio + " and";

		if (idImovelPrincipal != null

		&& !idImovelPrincipal.equalsIgnoreCase(""))

			composicaoHql += " ipri.id = " + idImovelPrincipal + " and";

		// Descobrir por que esta retornando 0

		// Descobrir por que esta retornando 0

		// Descobrir por que esta retornando 0

		// Descobrir por que esta retornando 0

		// Descobrir por que esta retornando 0

		// Descobrir por que esta retornando 0

		/*
		 * 
		 * if (!idNomeConta.equalsIgnoreCase("") &&
		 * 
		 * !idNomeConta.equalsIgnoreCase("")) composicaoHql += "nc.id = " +
		 * 
		 * idNomeConta + " and ";
		 * 
		 */

		// Descobrir por que esta retornando 0
		// Descobrir por que esta retornando 0
		// Descobrir por que esta retornando 0
		// Descobrir por que esta retornando 0
		// Descobrir por que esta retornando 0
		// Descobrir por que esta retornando 0
		if (situacaoAgua != null

		&& !situacaoAgua.equals(ConstantesSistema.NUMERO_NAO_INFORMADO

		+ "") && !situacaoAgua.equals(""))

			composicaoHql += " las.id = " + situacaoAgua + " and";

		if (situacaoLigacaoEsgoto != null

		&& !situacaoLigacaoEsgoto

		.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")

		&& !situacaoLigacaoEsgoto.equals(""))

			composicaoHql += " les.id = " + situacaoLigacaoEsgoto + " and";

		if (perfilImovel != null

		&& !perfilImovel.equals(ConstantesSistema.NUMERO_NAO_INFORMADO

		+ "") && !perfilImovel.equals(""))

			composicaoHql += " ip.id = " + perfilImovel + " and";

		if (tipoPoco != null

		&& !tipoPoco

		.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")

		&& !tipoPoco.equals(""))

			composicaoHql += " pt.id = " + tipoPoco + " and";

		if (tipoSituacaoEspecialFaturamento != null

		&& !tipoSituacaoEspecialFaturamento

		.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")

		&& !tipoSituacaoEspecialFaturamento.equals(""))

			composicaoHql += " ft.id = " + tipoSituacaoEspecialFaturamento;

		if (situacaoCobranca != null

		&& !situacaoCobranca

		.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")

		&& !situacaoCobranca.equals(""))

			composicaoHql += " sc.id = " + situacaoCobranca + " and";

		if (!diaVencimento.equalsIgnoreCase("")

		&& diaVencimento.equalsIgnoreCase("sim"))

			composicaoHql += " and im.diaVencimento is not NULL and";

		if (tipoSituacaoEspecialCobranca != null

		&& !tipoSituacaoEspecialCobranca

		.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")

		&& !tipoSituacaoEspecialCobranca.equals(""))

			composicaoHql += " cst.id = " + tipoSituacaoEspecialCobranca

			+ " and";

		if (anormalidadeElo != null

		&& !anormalidadeElo

		.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")

		&& !anormalidadeElo.equals(""))

			composicaoHql += " ea.id = " + anormalidadeElo + " and";

		if (ocorrenciaCadastro != null

		&& !ocorrenciaCadastro

		.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")

		&& !ocorrenciaCadastro.equals(""))

			composicaoHql += " co.id = " + ocorrenciaCadastro + " and";

		if (tarifaConsumo != null

		&& !tarifaConsumo.equals(ConstantesSistema.NUMERO_NAO_INFORMADO

		+ "") && !tarifaConsumo.equals(""))

			composicaoHql += " ct.id = " + tarifaConsumo + " and";

		String clienteTipo = "";

		String cliente = "";

		if (filtrarImovelOutrosCriteriosHelper.getTipoRelatorio().trim()

		.equalsIgnoreCase("RelatorioTarifaSocial")) {

			if (idClienteTipo != null

			&& !idClienteTipo

			.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")

			&& !idClienteTipo.equals("")) {

				clienteTipo = "and im.id in (select im.id from ClienteImovelEconomia cie "

						+ " inner join cie.cliente cl "

						+ " where cl.clienteTipo.id = " + idClienteTipo;

			}

			if (idCliente != null && !idCliente.equalsIgnoreCase("")) {

				cliente = " im.id in (select im.id from ClienteImovelEconomia cie "

						+ " inner join cie.cliente cl "

						+ " where cl.id = "

						+ idCliente;

			}

		} else if (filtrarImovelOutrosCriteriosHelper.getTipoRelatorio().trim()

		.equalsIgnoreCase("consultarTarifaExcluida")) {

			composicaoHql += " tsd.dataExclusao is not NULL and";

			if (idClienteTipo != null

			&& !idClienteTipo

			.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")

			&& !idClienteTipo.equals("")) {

				clienteTipo = " im.id in (select im.id from ClienteImovelEconomia cie "

						+ " inner join cie.cliente cl "

						+ " where cl.clienteTipo.id = "

						+ idClienteTipo

						+ " and";

			}

			if (idCliente != null

			&& !idCliente.equals(ConstantesSistema.NUMERO_NAO_INFORMADO

			+ "") && !idCliente.equals("")) {

				cliente = " im.id in (select im.id from ClienteImovelEconomia cie "

						+ " inner join cie.cliente cl "

						+ " where cl.id = "

						+ idCliente;

			}

		} else {

			if (idClienteTipo != null

			&& !idClienteTipo

			.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")

			&& !idClienteTipo.equals(""))

				composicaoHql += " ct.id = " + idClienteTipo

				+ "ci.dataFimRelacao is NULL" + " and";

			if (idCliente != null && !idCliente.equals(""))

				composicaoHql += " cl.id = " + idCliente

				+ " and ci.dataFimRelacao is NULL" + " and ";

		}

		// String selectImovelCobrancaSituacao = null;

		String subSelectImovelCobrancaSituacao = "";

		// Inicio Situacao Cobranca

		if (situacaoCobrancaID != null

		&& !situacaoCobrancaID

		.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")

		&& !situacaoCobrancaID.equals("")) {

			subSelectImovelCobrancaSituacao = " im.id in (select im.id from ImovelCobrancaSituacao ics "

					+ " inner join ics.imovel im "

					+ " inner join ics.cobrancaSituacao cs "

					+ " where cs.id = "

					+ situacaoCobrancaID

					+ " and ics.dataRetiradaCobranca is null) and";

		}

		// Fim Situacao Cobranca

		// Select Medicao Historico

		String subSelectMedicaoHistorico = "";

		if (((intervaloMediaMinimaHidrometroInicial != null) && (!intervaloMediaMinimaHidrometroInicial

		.equals("")))

				&& ((intervaloMediaMinimaHidrometroFinal != null) && (!intervaloMediaMinimaHidrometroFinal

				.equals("")))

				&& (medicaoTipo != null

				&& !medicaoTipo

				.equals(ConstantesSistema.NUMERO_NAO_INFORMADO

				+ "") && !medicaoTipo.equals(""))

				&& (intervaloMediaMinimaImovelInicio != null && !intervaloMediaMinimaImovelInicio

				.equals(""))

				&& (intervaloMediaMinimaImovelFinal != null && !intervaloMediaMinimaImovelFinal

				.equals(""))) {

			subSelectMedicaoHistorico = " im.id in "

			+ "	(select la.id from MedicaoHistorico mh "

			+ "	inner join mh.ligacaoAgua la "

			+ "	inner join mh.medicaoTipo mt "

			+ "	where mh.consumoMedioHidrometro " + "	between  "

			+ intervaloMediaMinimaHidrometroInicial + "   and "

			+ intervaloMediaMinimaHidrometroFinal + "	and mt.id = "

			+ medicaoTipo + "	and la.numeroConsumoMinimoAgua "

			+ "	between " + intervaloMediaMinimaImovelInicio + "   and"

			+ intervaloMediaMinimaImovelFinal + " and";

		}

		// Select Medicao Historico

		// String subSelectCaracteristicasImovel = "";

		if ((consumoMinimoInicial != null && !consumoMinimoInicial.equals(""))

		&& (consumoMinimoFinal != null && !consumoMinimoFinal

		.equals(""))) {

			/*
			 * 
			 * subSelectCaracteristicasImovel = " im.id in (" + "select im.id
			 * 
			 * from ConsumoHistorico ch " + "inner join ch.imovel im where
			 * 
			 * ch.consumoMedio " + "between " + consumoMinimoInicial + "and " +
			 * 
			 * consumoMinimoInicial + " and";
			 * 
			 */

		}

		// Dados Faturamento Cobranca

		// String subSelectDadosFaturamentoCobranca = "";

		if ((intervaloPercentualEsgotoInicial != null && !intervaloPercentualEsgotoInicial

		.equals(""))

				&& ((intervaloPercentualEsgotoFinal != null) && !intervaloPercentualEsgotoFinal

				.equals(""))

				&& (consumoMinimoFixadoEsgotoInicial != null && !consumoMinimoFixadoEsgotoInicial

				.equals(""))

				&& (consumoMinimoFixadoEsgotoFinal != null && !consumoMinimoFixadoEsgotoFinal

				.equals(""))) {

			/*
			 * 
			 * subSelectDadosFaturamentoCobranca = " im.id in " + "(select le.id
			 * 
			 * from LigacaoEsgoto le " + "where le.percentual " + "between " +
			 * 
			 * intervaloPercentualEsgotoInicial + "and " +
			 * 
			 * intervaloPercentualEsgotoFinal + "and le.consumoMinimo " +
			 * 
			 * "between " + consumoMinimoFixadoEsgotoInicial + "and " +
			 * 
			 * consumoMinimoFixadoEsgotoFinal + " and";
			 * 
			 */

		}

		// String subSelectCategoriaImovel = "";

		// --------------Feito por flavio

		if (idSubcategoria != null

		&& !idSubcategoria.trim().equalsIgnoreCase("")

		&& !idSubcategoria.trim().equalsIgnoreCase(

		ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {

			if (idCategoria != null

			&& !idCategoria.trim().equalsIgnoreCase("")

			&& !idCategoria.trim().equalsIgnoreCase(

			ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {

				/*
				 * 
				 * subSelectCategoriaImovel = " im.id in " + "(select is.imovel
				 * 
				 * from ImovelSubcategoria is " + " inner join
				 * 
				 * is.comp_id.subcategoria su" + " inner join su.categoria ca" + "
				 * 
				 * where su.id = " + idSubcategoria + " and ca.id = " +
				 * 
				 * idCategoria + " and";
				 * 
				 */

			} else {

				/*
				 * 
				 * subSelectCategoriaImovel = " im.id in " + "(select is.imovel
				 * 
				 * from ImovelSubcategoria is " + " inner join is.subcategoria
				 * 
				 * su" + " where su.id = " + idSubcategoria + " and";
				 * 
				 */

			}

		} else {

			if (idCategoria != null

			&& !idCategoria.trim().equalsIgnoreCase("")

			&& !idCategoria.trim().equalsIgnoreCase(

			ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {

				/*
				 * 
				 * subSelectCategoriaImovel = " im.id in " + "(select is.imovel
				 * 
				 * from ImovelSubcategoria is " + " inner join
				 * 
				 * is.subcategoria.categoriaca" + " where ca.id = " +
				 * 
				 * idCategoria + " and";
				 * 
				 */

			}

		}

		// ----------------------Falta Campo quantidade de aconomias em imovel

		// --------------------Criar com fatima

		/*
		 * 
		 * String subSelectQtdEconomias = "";
		 * 
		 * 
		 * 
		 * if(qtdEconomiasInicial != null &&
		 * 
		 * !qtdEconomiasInicial.trim().equalsIgnoreCase("") && qtdEconomiasFinal !=
		 * 
		 * null && !qtdEconomiasFinal.trim().equalsIgnoreCase("")){
		 * 
		 * 
		 * 
		 * subSelectQtdEconomias = " im.id in " + " (select )"
		 * 
		 * 
		 * 
		 * subSelectDadosFaturamentoCobranca = " im.id in " + "(select le.id
		 * 
		 * from LigacaoEsgoto le " + "where le.percentual " + "between " +
		 * 
		 * intervaloPercentualEsgotoInicial + "and " +
		 * 
		 * intervaloPercentualEsgotoFinal + "and le.consumoMinimo " + "between " +
		 * 
		 * consumoMinimoFixadoEsgotoInicial + "and " +
		 * 
		 * consumoMinimoFixadoEsgotoFinal + " and"; }
		 * 
		 */

		if (numeroPontosInicial != null

		&& !numeroPontosInicial.trim().equalsIgnoreCase("")

		&& numeroPontosFinal != null

		&& !numeroPontosFinal.trim().equalsIgnoreCase("")) {

			composicaoHql = " im.numeroPontosUtilizacao between "

			+ numeroPontosInicial + " and " + numeroPontosFinal

			+ " and";

		}

		if (numeroMoradoresInicial != null

		&& !numeroMoradoresInicial.trim().equalsIgnoreCase("")

		&& numeroMoradoresFinal != null

		&& !numeroMoradoresFinal.trim().equalsIgnoreCase("")) {

			composicaoHql = " im.numeroMorador between "

			+ numeroMoradoresInicial + " and " + numeroMoradoresFinal

			+ " and";

		}

		if (areaConstruidaInicial != null

		&& !areaConstruidaInicial.trim().equalsIgnoreCase("")

		&& areaConstruidaFinal != null

		&& !areaConstruidaFinal.trim().equalsIgnoreCase("")) {

			composicaoHql = " im.areaConstruida between "

			+ areaConstruidaInicial + " and " + areaConstruidaFinal

			+ " and";

		}

		if (areaConstruidaFaixa != null

		&& !areaConstruidaFaixa.trim().equalsIgnoreCase("")) {

			composicaoHql = " acf.id = " + areaConstruidaFaixa + " and";

		}

		if (pocoTipo != null

		&& !pocoTipo.trim().equalsIgnoreCase("")

		&& !pocoTipo.trim().equalsIgnoreCase(

		ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {

			composicaoHql = " pt.id = " + pocoTipo + " and";

		}

		retorno = composicaoHql + subSelectImovelCobrancaSituacao

		+ subSelectMedicaoHistorico;

		if (filtrarImovelOutrosCriteriosHelper.getTipoRelatorio().trim()

		.equalsIgnoreCase("RelatorioTarifaSocial")) {

			retorno = retorno + clienteTipo + cliente;

		}

		return retorno;

	}

	// ----------------------- rhawi

	public Integer validarMesAnoReferencia(

	SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper)

	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String consulta = null;

		// Escolha de que tipo de consulta será

		if (situacaoEspecialFaturamentoHelper != null

		&& !situacaoEspecialFaturamentoHelper.getIdImovel().equals(""))

			consulta = "select max(fg.anoMesReferencia) from Imovel im"

					+ " inner join im.localidade lo"

					+ " inner join im.setorComercial sc"

					+ " inner join im.quadra qu"

					+ " inner join qu.rota rt"

					+ " inner join rt.faturamentoGrupo fg"

					+ " where im.id = "

					+ situacaoEspecialFaturamentoHelper.getIdImovel()

					+ " and"

					+ " (im.indicadorExclusao <> :idExclusao OR im.indicadorExclusao is null) and ";

		else {

			consulta = "select max(fg.anoMesReferencia) from Imovel im"

					+ " inner join im.localidade lo"

					+ " inner join im.setorComercial sc"

					+ " inner join im.quadra qu"

					+ " inner join qu.rota rt"

					+ " inner join rt.faturamentoGrupo fg"

					+ " where (im.indicadorExclusao <> :idExclusao OR im.indicadorExclusao is null) and ";

			// String idImovel =

			// situacaoEspecialFaturamentoHelper.getIdImovel();

			String idLocalidadeOrigem = situacaoEspecialFaturamentoHelper

			.getLocalidadeOrigemID();

			String idLocalidadeDestino = situacaoEspecialFaturamentoHelper

			.getLocalidadeDestinoID();

			String setorComercialOrigemID = situacaoEspecialFaturamentoHelper

			.getSetorComercialOrigemID();

			String setorComercialDestinoID = situacaoEspecialFaturamentoHelper

			.getSetorComercialDestinoID();

			String quadraOrigemID = situacaoEspecialFaturamentoHelper

			.getQuadraOrigemID();

			String quadraDestinoID = situacaoEspecialFaturamentoHelper

			.getQuadraDestinoID();

			String loteOrigem = situacaoEspecialFaturamentoHelper

			.getLoteOrigem();

			String loteDestino = situacaoEspecialFaturamentoHelper

			.getLoteDestino();

			String subLoteOrigem = situacaoEspecialFaturamentoHelper

			.getSubloteOrigem();

			String subLoteDestino = situacaoEspecialFaturamentoHelper

			.getSubloteDestino();

			/*
			 * 
			 * if (!idImovel.equalsIgnoreCase("") &&
			 * 
			 * !idImovel.equalsIgnoreCase("")) consulta += "imovel.id " +
			 * 
			 * idImovel;
			 * 
			 */

			if (idLocalidadeOrigem != null && !idLocalidadeOrigem.equals("")

			&& idLocalidadeDestino != null

			&& !idLocalidadeDestino.equals(""))

				consulta += " lo.id between " + idLocalidadeOrigem + " and "

				+ idLocalidadeDestino + " and ";

			if (setorComercialOrigemID != null

			&& !setorComercialOrigemID.equalsIgnoreCase("")

			&& !setorComercialOrigemID.equalsIgnoreCase(""))

				consulta += "sc.id between " + setorComercialOrigemID + " and "

				+ setorComercialDestinoID + " and ";

			if (!quadraOrigemID.equalsIgnoreCase("")

			&& !quadraOrigemID.equalsIgnoreCase(""))

				consulta += "qu.id between " + quadraOrigemID + " and "

				+ quadraDestinoID + " and ";

			if (!loteOrigem.equalsIgnoreCase("")

			&& !loteOrigem.equalsIgnoreCase(""))

				consulta += "im.lote between " + new Integer(loteOrigem) + " and "

				+ new Integer(loteDestino) + " and ";

			if (!subLoteOrigem.equalsIgnoreCase("")

			&& !subLoteOrigem.equalsIgnoreCase(""))

				consulta += "im.subLote between " + new Integer(subLoteOrigem) + " and "

				+ new Integer(subLoteDestino) + " and ";

			System.out.println(Util.removerUltimosCaracteres(consulta, 4));

		}

		// Consulta

		try {

			Integer i = (Integer) session.createQuery(

			Util.removerUltimosCaracteres(consulta, 4)).setShort("idExclusao",

			Imovel.IMOVEL_EXCLUIDO).uniqueResult();

			return i;

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
	 * Pesquisa o maior ano mes de referencia da tabela de faturamento grupo
	 * 
	 * 
	 * 
	 * [UC0177] Informar Situacao Especial de Cobrança
	 * 
	 * 
	 * 
	 * @author Sávio Luiz
	 * 
	 * @date 18/03/2006
	 * 
	 * 
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * 
	 * @return
	 * 
	 */

	public Integer validarMesAnoReferenciaCobranca(

	SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper)

	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String consulta = null;

		// Escolha de que tipo de consulta será

		if (situacaoEspecialCobrancaHelper != null

		&& !situacaoEspecialCobrancaHelper.getIdImovel().equals(""))

			consulta = "select max(cg.anoMesReferencia) from Imovel im"

					+ " inner join im.localidade lo"

					+ " inner join im.setorComercial sc"

					+ " inner join im.quadra qu"

					+ " inner join qu.rota rt"

					+ " inner join rt.cobrancaGrupo cg"

					+ " where im.id = "

					+ situacaoEspecialCobrancaHelper.getIdImovel()

					+ " and"

					+ " (im.indicadorExclusao <> :idExclusao OR im.indicadorExclusao is null) and";

		else {

			consulta = "select max(cg.anoMesReferencia) from Imovel im"

					+ " inner join im.localidade lo"

					+ " inner join im.setorComercial sc"

					+ " inner join im.quadra qu"

					+ " inner join qu.rota rt"

					+ " inner join rt.cobrancaGrupo cg"

					+ " where (im.indicadorExclusao <> :idExclusao OR im.indicadorExclusao is null) and ";

			// String idImovel =

			// situacaoEspecialFaturamentoHelper.getIdImovel();

			String idLocalidadeOrigem = situacaoEspecialCobrancaHelper

			.getLocalidadeOrigemID();

			String idLocalidadeDestino = situacaoEspecialCobrancaHelper

			.getLocalidadeDestinoID();

			String setorComercialOrigemID = situacaoEspecialCobrancaHelper

			.getSetorComercialOrigemID();

			String setorComercialDestinoID = situacaoEspecialCobrancaHelper

			.getSetorComercialDestinoID();

			String quadraOrigemID = situacaoEspecialCobrancaHelper

			.getQuadraOrigemID();

			String quadraDestinoID = situacaoEspecialCobrancaHelper

			.getQuadraDestinoID();

			String loteOrigem = situacaoEspecialCobrancaHelper.getLoteOrigem();

			String loteDestino = situacaoEspecialCobrancaHelper

			.getLoteDestino();

			String subLoteOrigem = situacaoEspecialCobrancaHelper

			.getSubloteOrigem();

			String subLoteDestino = situacaoEspecialCobrancaHelper

			.getSubloteDestino();

			if (idLocalidadeOrigem != null && !idLocalidadeOrigem.equals("")

			&& idLocalidadeDestino != null

			&& !idLocalidadeDestino.equals(""))

				consulta += " lo.id between " + idLocalidadeOrigem + " and "

				+ idLocalidadeDestino + " and ";

			if (setorComercialOrigemID != null

			&& !setorComercialOrigemID.equalsIgnoreCase("")

			&& !setorComercialOrigemID.equalsIgnoreCase(""))

				consulta += "sc.id between " + setorComercialOrigemID + " and "

				+ setorComercialDestinoID + " and ";

			if (!quadraOrigemID.equalsIgnoreCase("")

			&& !quadraOrigemID.equalsIgnoreCase(""))

				consulta += "qu.id between " + quadraOrigemID + " and "

				+ quadraDestinoID + " and ";

			if (!loteOrigem.equalsIgnoreCase("")

			&& !loteOrigem.equalsIgnoreCase(""))

				consulta += "im.lote between " + loteOrigem + " and "

				+ loteDestino + " and ";

			if (!subLoteOrigem.equalsIgnoreCase("")

			&& !subLoteOrigem.equalsIgnoreCase(""))

				consulta += "im.subLote between " + subLoteOrigem + " and "

				+ subLoteDestino + " and ";

			System.out.println(Util.removerUltimosCaracteres(consulta, 4));

		}

		// Consulta

		try {

			Integer i = (Integer) session.createQuery(

			Util.removerUltimosCaracteres(consulta, 4)).setShort("idExclusao",

			Imovel.IMOVEL_EXCLUIDO).uniqueResult();

			return i;

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
	 * Atualiza os ids do faturamento situação tipo da tabela imóvel com o id do
	 * 
	 * faturamento escolhido pelo usuario
	 * 
	 * 
	 * 
	 * [UC0156] Informar Situacao Especial de Faturamento
	 * 
	 * 
	 * 
	 * @author Sávio Luiz
	 * 
	 * @date 18/03/2006
	 * 
	 * 
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * 
	 * @return
	 * 
	 */

	public void atualizarFaturamentoSituacaoTipo(Collection colecaoIdsImoveis, Integer idFaturamentoTipo) throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {

			if (colecaoIdsImoveis != null && !colecaoIdsImoveis.isEmpty()) {
				logger.info("Atualizando " + colecaoIdsImoveis.size() +" Imoveis para o idFaturamentoTipo = " + idFaturamentoTipo);

				consulta = "update gcom.cadastro.imovel.Imovel set "
						+ "ftst_id = :idFaturamentoSituacao,imov_tmultimaalteracao = :ultimaAlteracao where imov_id IN (:ids)";

				if(colecaoIdsImoveis.size() > 999){
					List<List<Integer>> particoes = CollectionUtil.particao((List<Integer>) colecaoIdsImoveis, 999);

					int qtdQuebras = 999;
					int indice = colecaoIdsImoveis.size() / qtdQuebras;
					
					if (colecaoIdsImoveis.size() % qtdQuebras !=0){
						indice ++;
					}

					for (int i = 0; i < indice; i++) {
						session.createQuery(consulta).setInteger("idFaturamentoSituacao", idFaturamentoTipo.intValue())
								.setParameterList("ids", particoes.get(i)).setTimestamp("ultimaAlteracao", new Date())
								.executeUpdate();
					}
				}
				else{
					session.createQuery(consulta).setInteger("idFaturamentoSituacao", idFaturamentoTipo.intValue())
							.setParameterList("ids", colecaoIdsImoveis)
							.setTimestamp("ultimaAlteracao", new Date())
							.executeUpdate();
				}

			}

		} catch (HibernateException e) {
			logger.error("Problemas ao atualizar o faturamento situacao tipo.");
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * 
	 * Atualiza o id da cobrança situação tipo da tabela imóvel com o id da
	 * 
	 * situação escolhido pelo usuario
	 * 
	 * 
	 * 
	 * [UC0177] Informar Situacao Especial de Cobrança
	 * 
	 * 
	 * 
	 * @author Sávio Luiz
	 * 
	 * @date 18/03/2006
	 * 
	 * 
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * 
	 * @return
	 * 
	 */

	public void atualizarCobrancaSituacaoTipo(Collection colecaoIdsImoveis,

	Integer idCobrancaTipo) throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {

			if (colecaoIdsImoveis != null && !colecaoIdsImoveis.isEmpty()) {

				consulta = "update gcom.cadastro.imovel.Imovel set "

						+ "cbsp_id = :idCobrancaSituacao,imov_tmultimaalteracao = :ultimaAlteracao where imov_id IN (:ids)";

				session.createQuery(consulta).setInteger("idCobrancaSituacao",

				idCobrancaTipo.intValue()).setParameterList("ids",

				colecaoIdsImoveis).setTimestamp("ultimaAlteracao",

				new Date()).executeUpdate();

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
	 * Seta para null o id da cobrança situação tipo da tabela imóvel
	 * 
	 * 
	 * 
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * 
	 * 
	 * @author Sávio Luiz
	 * 
	 * @date 18/03/2006
	 * 
	 * 
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * 
	 * @return
	 * 
	 */

	public void retirarSituacaoEspecialFaturamento(Collection colecaoIdsImoveis) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {
			if (colecaoIdsImoveis != null && !colecaoIdsImoveis.isEmpty()) {

				String consulta = "update gcom.cadastro.imovel.Imovel set "
						+ "ftst_id = null,ftsm_id = null,imov_tmultimaalteracao = :ultimaAlteracao " 
						+ "where imov_id IN (:ids)";

				session.createQuery(consulta)
				.setParameterList("ids",colecaoIdsImoveis)
				.setTimestamp("ultimaAlteracao",	new Date())
				.executeUpdate();
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * 
	 * Seta para null o id da cobrança situação tipo da tabela imóvel
	 * 
	 * 
	 * 
	 * [UC0177] Informar Situacao Especial de Cobrança
	 * 
	 * 
	 * 
	 * @author Sávio Luiz
	 * 
	 * @date 18/03/2006
	 * 
	 * 
	 * 
	 * @param situacaoEspecialCobrancaHelper
	 * 
	 * @return
	 * 
	 */

	public void retirarSituacaoEspecialCobranca(Collection colecaoIdsImoveis)

	throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {

			if (colecaoIdsImoveis != null && !colecaoIdsImoveis.isEmpty()) {

				consulta = "update gcom.cadastro.imovel.Imovel set "

						+ "cbsp_id = null,imov_tmultimaalteracao = :ultimaAlteracao where imov_id IN (:ids)";

				session.createQuery(consulta).setParameterList("ids",

				colecaoIdsImoveis).setTimestamp("ultimaAlteracao",

				new Date()).executeUpdate();

			}

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

	}

	public Collection<Integer> pesquisarImoveisIds(Integer rotaId)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "select im.id from Imovel im "

					+ "inner join im.quadra qd "

					+ "inner join qd.rota rota where rota.id = :rotaId and im.indicadorImovelCondominio <> 1";

			retorno = session.createQuery(consulta).setInteger("rotaId",

			rotaId.intValue()).list();

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	public Object pesquisarImovelIdComConta(Integer imovelId,

	Integer anoMesReferencia) throws ErroRepositorioException {

		Object retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "select ct.id from Conta ct "

			+ "inner join ct.imovel im "

			+ "where im.id = :imovelId and ct.referencia = :anoMesReferencia";

			retorno = session.createQuery(consulta).setInteger("imovelId",

			imovelId.intValue()).setInteger("anoMesReferencia",

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
	 * 
	 * Obtém o indicador de existência de hidrômetro para o imóvel, caso exista
	 * 
	 * retorna 1(um) indicando SIM caso contrário retorna 2(dois) indicando NÃO
	 * 
	 * 
	 * 
	 * [UC0307] Obter Indicador de Existência de Hidrômetro no Imóvel
	 * 
	 * 
	 * 
	 * @author Thiago Toscano
	 * 
	 * @date 02/06/2006
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * @return
	 * 
	 * @throws ControladorException
	 * 
	 */

	public Integer obterIndicadorExistenciaHidrometroImovel(Integer idImovel)

	throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = " 	select "

					+ "		case when "

					+ "		(((imovel.ligacaoAguaSituacao.id in( "

					+ LigacaoAguaSituacao.LIGADO +", "

					+ LigacaoAguaSituacao.CORTADO +", "
					
					+ LigacaoAguaSituacao.LIGADO_EM_ANALISE +")"

					+ ") "

					+ "		and imovel.ligacaoAgua.hidrometroInstalacaoHistorico is not null)  "

					+ "		or  "

					+ "		(imovel.ligacaoEsgotoSituacao.id = "

					+ LigacaoEsgotoSituacao.LIGADO

					+ " and imovel.hidrometroInstalacaoHistorico is not null)) "

					+ "		then 1 else 2 end "

					+ "	from  "

					+ "		gcom.cadastro.imovel.Imovel as imovel "

					+ "		left join imovel.ligacaoAgua as ligacaoAgua "

					+ "		left join imovel.ligacaoAguaSituacao as ligacaoAguaSituacao "

					+ "		left join imovel.ligacaoEsgoto as ligacaoEsgoto "

					+ "		left join imovel.ligacaoEsgotoSituacao as ligacaoEsgotoSituacao "

					+ "	where  " + "		imovel.id = :idImovel " + " ";

			retorno = (Integer) session.createQuery(consulta).setInteger(

			"idImovel", idImovel.intValue()).uniqueResult();

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	// /**

	// *

	// * Gerar Relatório de Imóvel Outros Critérios

	// *

	// * @author Rafael Corrêa

	// * @date 24/07/2006

	// *

	// */

	// public Collection gerarRelatorioImovelOutrosCriterios(

	// String idImovelCondominio, String idImovelPrincipal,

	// String idNomeConta, String idSituacaoLigacaoAgua,

	// String consumoMinimoInicialAgua, String consumoMinimoFinalAgua,

	// String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto,

	// String consumoMinimoFinalEsgoto,

	// String intervaloValorPercentualEsgotoInicial,

	// String intervaloValorPercentualEsgotoFinal,

	// String intervaloMediaMinimaImovelInicial,

	// String intervaloMediaMinimaImovelFinal,

	// String intervaloMediaMinimaHidrometroInicial,

	// String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,

	// String idPocoTipo, String idFaturamentoSituacaoTipo,

	// String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,

	// String idEloAnormalidade, String areaConstruidaInicial,

	// String areaConstruidaFinal, String idCadastroOcorrencia,

	// String idConsumoTarifa, String idGerenciaRegional,

	// String idLocalidadeInicial, String idLocalidadeFinal,

	// String setorComercialInicial, String setorComercialFinal,

	// String quadraInicial, String quadraFinal, String loteOrigem,

	// String loteDestno, String cep, String logradouro, String bairro,

	// String municipio, String idTipoMedicao, String indicadorMedicao,

	// String idSubCategoria, String idCategoria,

	// String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,

	// String diaVencimento, String idCliente, String idClienteTipo,

	// String idClienteRelacaoTipo, String numeroPontosInicial,

	// String numeroPontosFinal, String numeroMoradoresInicial,

	// String numeroMoradoresFinal, String idAreaConstruidaFaixa)

	// throws ErroRepositorioException {

	//

	// Collection retorno = null;

	//

	// Session session = HibernateUtil.getSession();

	// String consulta;

	//

	// try {

	// consulta = "select distinct "

	// + "gerenciaRegional.id,"

	// + "gerenciaRegional.nomeAbreviado,"

	// + "localidade.id,"

	// + "localidade.descricao,"

	// + "imovel.id,"

	// + "imovel.quantidadeEconomias,"

	// + "setorComercial.codigo,"

	// + "setorComercial.descricao,"

	// + "quadra.numeroQuadra,"

	// + "imovel.lote,"

	// + "imovel.subLote,"

	// + "ligacaoAguaSituacao.descricaoAbreviado,"

	// + "ligacaoEsgotoSituacao.descricaoAbreviado,"

	// + "ligacaoEsgoto.percentual,"

	// + "ligacaoEsgoto.dataLigacao, "

	// + "ligacaoAgua.dataLigacao, "

	// //OK

	// + "clienteUsuario.id, "

	// + "clienteUsuario.nome, "

	// + "clienteResponsavel.id, "

	// + "clienteResponsavel.nome, "

	//					

	// + "logradouro.nome, "

	// + "logradouroTipo.descricao, "

	// + "logradouroTitulo.descricao, "

	// + "cep.codigo, "

	// + "enderecoReferencia.descricao, "

	// + "imovel.complementoEndereco, "

	// + "imovel.numeroImovel, "

	// + "bairro.nome, "

	// + "municipio.nome, "

	// + "unidadeFederacao.sigla, "

	//					

	// + "imovel.indicadorImovelCondominio, "

	// + "imovel.numeroMorador, "

	// + "imovelCondominio.id, "

	// + "imovelPrincipal.id, "

	// + "imovel.numeroPontosUtilizacao, "

	// + "imovelPerfil.descricao, "

	// + "areaConstruidaFaixa.maiorFaixa, "

	// + "areaConstruidaFaixa.menorFaixa, "

	// + "imovel.areaConstruida, "

	// + "pavimentoCalcada.descricao, "

	// + "pavimentoRua.descricao, "

	// + "despejo.descricao, "

	// + "reservatorioVolumeFaixaSuperior.volumeMenorFaixa, "

	// + "reservatorioVolumeFaixaSuperior.volumeMaiorFaixa, "

	//					

	// + "imovel.volumeReservatorioSuperior, "

	//					

	// + "reservatorioVolumeFaixaInferior.volumeMenorFaixa, "

	// + "reservatorioVolumeFaixaInferior.volumeMaiorFaixa, "

	// + "imovel.volumeReservatorioInferior, "

	// + "piscinaVolumeFaixa.volumeMenorFaixa, "

	// + "piscinaVolumeFaixa.volumeMaiorFaixa, "

	// + "imovel.volumePiscina, "

	// + "pocoTipo.descricao, "

	//					

	// + "ligacaoAguaDiametro.descricao, "

	// + "ligacaoAguaMaterial.descricao, "

	// + "ligacaoEsgotoDiametro.descricao, "

	// + "ligacaoEsgotoMaterial.descricao, "

	// + "ligacaoEsgoto.consumoMinimo, "

	// + "ligacaoEsgoto.percentualAguaConsumidaColetada, "

	// + "ligacaoEsgoto.percentual, "

	//					

	// + "hidrometroAgua.numero, "

	// + "hidrometroAgua.anoFabricacao, "

	// + "hidrometroCapacidadeAgua.descricao, "

	// + "hidrometroMarcaAgua.descricao, "

	// + "hidrometroDiametroAgua.descricao, "

	// + "hidrometroTipoAgua.descricao, "

	// + "hidrometroInstalacaoHistorico.dataInstalacao, "

	// + "hidrometroLocalInstalacaoAgua.descricao, "

	// + "hidrometroProtecaoAgua.descricao, "

	// + "hidrometroInstalacaoHistorico.indicadorExistenciaCavalete, "

	//					

	// + "hidrometroEsgoto.numero, "

	// + "hidrometroEsgoto.anoFabricacao, "

	// + "hidrometroCapacidadeEsgoto.descricao, "

	// + "hidrometroMarcaEsgoto.descricao, "

	// + "hidrometroDiametroEsgoto.descricao, "

	// + "hidrometroTipoEsgoto.descricao, "

	// + "hidrometroInstalacaoHistoricoImovel.dataInstalacao, "

	// + "hidrometroLocalInstalacaoEsgoto.descricao, "

	// + "hidrometroProtecaoEsgoto.descricao, "

	// + "hidrometroInstalacaoHistoricoImovel.indicadorExistenciaCavalete, "

	//					

	// + "ligacaoAgua.numeroConsumoMinimoAgua, "

	// + "ligacaoEsgoto.consumoMinimo "

	//

	// + " from ImovelSubcategoria imovelSubcategoria "

	// + " inner join imovelSubcategoria.comp_id.imovel imovel "

	// + " left join imovelSubcategoria.comp_id.subcategoria subcategoria "

	// + " left join subcategoria.categoria categoria "

	// + " inner join imovel.localidade localidade "

	// + " inner join localidade.gerenciaRegional gerenciaRegional "

	// + " inner join imovel.setorComercial setorComercial "

	// + " left join imovel.logradouroBairro logradouroBairro "

	// + " left join logradouroBairro.bairro bairro "

	// + " left join bairro.municipio municipio "

	// + " left join logradouroBairro.bairro bairro "

	// + " inner join imovel.quadra quadra "

	// + " left join imovel.logradouroCep logradouroCep "

	// + " left join logradouroCep.cep cep "

	// + " left join imovel.logradouroCep logradouroCep "

	// + " left join logradouroCep.logradouro logradouro "

	// + " left join imovel.imovelCondominio imovelCondominio "

	// + " left join imovel.imovelPrincipal imovelPrincipal "

	// + " left join imovel.nomeConta nomeConta "

	//

	// + " left join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "

	// + " left join imovel.ligacaoAgua ligacaoAgua "

	//					

	// + " left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "

	// + " left join imovel.ligacaoEsgoto ligacaoEsgoto "

	//					

	// + " left join imovel.imovelPerfil imovelPerfil "

	// + " left join imovel.pocoTipo pocoTipo "

	// + " left join imovel.faturamentoTipo faturamentoTipo "

	// + " left join imovel.cobrancaSituacaoTipo cobrancaSituacaoTipo "

	// + " left join imovel.faturamentoSituacaoTipo faturamentoSituacaoTipo "

	// + " left join imovel.eloAnormalidade eloAnormalidade "

	// + " left join imovel.cadastroOcorrencia cadastroOcorrencia "

	// + " left join imovel.areaConstruidaFaixa areaConstruidaFaixa "

	// + " left join imovel.consumoTarifa consumoTarifa "

	// + " left join ligacaoAgua.hidrometroInstalacaoHistorico

	// hidrometroInstalacaoHistorico "

	// + " left join imovel.hidrometroInstalacaoHistorico

	// hidrometroInstalacaoHistoricoImovel "

	// + " left join imovel.consumosHistoricos consumosHistorico "

	// + " left join imovel.medicaoHistoricos medicaoHistorico "

	//

	//					

	//

	// //FIM QUERY RAFAEL SANTOS

	//					

	// + " left join logradouro.logradouroTitulo logradouroTitulo "

	// + " left join logradouro.logradouroTipo logradouroTipo "

	// + " left join imovel.enderecoReferencia enderecoReferencia "

	// + " left join municipio.unidadeFederacao unidadeFederacao "

	// + " left join imovel.reservatorioVolumeFaixaSuperior

	// reservatorioVolumeFaixaSuperior "

	// + " left join imovel.reservatorioVolumeFaixaInferior

	// reservatorioVolumeFaixaInferior "

	// + " left join imovel.piscinaVolumeFaixa piscinaVolumeFaixa "

	// + " left join imovel.pavimentoCalcada pavimentoCalcada "

	// + " left join imovel.pavimentoRua pavimentoRua "

	// + " left join imovel.despejo despejo "

	// + " left join ligacaoAgua.ligacaoAguaDiametro ligacaoAguaDiametro "

	// + " left join ligacaoAgua.ligacaoAguaMaterial ligacaoAguaMaterial "

	// + " left join ligacaoEsgoto.ligacaoEsgotoDiametro ligacaoEsgotoDiametro "

	// + " left join ligacaoEsgoto.ligacaoEsgotoMaterial ligacaoEsgotoMaterial "

	// + " left join hidrometroInstalacaoHistorico.hidrometro hidrometroAgua "

	// + " left join hidrometroAgua.hidrometroCapacidade

	// hidrometroCapacidadeAgua "

	// + " left join hidrometroAgua.hidrometroMarca hidrometroMarcaAgua "

	// + " left join hidrometroAgua.hidrometroDiametro hidrometroDiametroAgua "

	// + " left join hidrometroAgua.hidrometroTipo hidrometroTipoAgua "

	// + " left join hidrometroInstalacaoHistorico.hidrometroLocalInstalacao

	// hidrometroLocalInstalacaoAgua "

	// + " left join hidrometroInstalacaoHistorico.hidrometroProtecao

	// hidrometroProtecaoAgua "

	// + " left join hidrometroInstalacaoHistoricoImovel.hidrometro

	// hidrometroEsgoto "

	// + " left join hidrometroEsgoto.hidrometroCapacidade

	// hidrometroCapacidadeEsgoto "

	// + " left join hidrometroEsgoto.hidrometroMarca hidrometroMarcaEsgoto "

	// + " left join hidrometroEsgoto.hidrometroDiametro

	// hidrometroDiametroEsgoto "

	// + " left join hidrometroEsgoto.hidrometroTipo hidrometroTipoEsgoto "

	// + " left join

	// hidrometroInstalacaoHistoricoImovel.hidrometroLocalInstalacao

	// hidrometroLocalInstalacaoEsgoto "

	// + " left join hidrometroInstalacaoHistoricoImovel.hidrometroProtecao

	// hidrometroProtecaoEsgoto "

	//					

	//

	// //REVER

	// + " left join imovel.clienteImoveis clienteImoveis "

	// + " left join clienteImoveis.clienteRelacaoTipo clienteRelacaoTipo "

	// + " left join clienteImoveis.cliente cliente "

	// + " left join cliente.clienteTipo clienteTipo "

	// //+ " left join imovel.clienteImoveis clienteImoveisUsuario "

	//					

	// + " left outer join imovel.clienteImoveis clienteImoveisUsuario with

	// (clienteImoveisUsuario.clienteRelacaoTipo.id = "

	// + ClienteRelacaoTipo.USUARIO.toString()

	// + ") and clienteImoveisUsuario.dataFimRelacao is null "

	// + " left outer join clienteImoveisUsuario.cliente clienteUsuario "

	// +

	//

	// " left outer join imovel.clienteImoveis clienteImoveisReposanvel with

	// (clienteImoveisReposanvel.clienteRelacaoTipo.id = "

	// + ClienteRelacaoTipo.RESPONSAVEL.toString()

	// + ") and clienteImoveisReposanvel.dataFimRelacao is null "

	// + " left outer join clienteImoveisReposanvel.cliente clienteResponsavel

	// ";

	//

	// consulta = consulta

	// + montarCondicaoWhereFiltrarImovelOutrosCriterio(

	// idImovelCondominio, idImovelPrincipal, idNomeConta,

	// idSituacaoLigacaoAgua, consumoMinimoInicialAgua,

	// consumoMinimoFinalAgua, idSituacaoLigacaoEsgoto,

	// consumoMinimoInicialEsgoto,

	// consumoMinimoFinalEsgoto,

	// intervaloValorPercentualEsgotoInicial,

	// intervaloValorPercentualEsgotoFinal,

	// intervaloMediaMinimaImovelInicial,

	// intervaloMediaMinimaImovelFinal,

	// intervaloMediaMinimaHidrometroInicial,

	// intervaloMediaMinimaHidrometroFinal,

	// idImovelPerfil, idPocoTipo,

	// idFaturamentoSituacaoTipo, idCobrancaSituacaoTipo,

	// idSituacaoEspecialCobranca, idEloAnormalidade,

	// areaConstruidaInicial, areaConstruidaFinal,

	// idCadastroOcorrencia, idConsumoTarifa,

	// idGerenciaRegional, idLocalidadeInicial,

	// idLocalidadeFinal, setorComercialInicial,

	// setorComercialFinal, quadraInicial, quadraFinal,

	// loteOrigem, loteDestno, cep, logradouro, bairro,

	// municipio, idTipoMedicao, indicadorMedicao,

	// idSubCategoria, idCategoria,

	// quantidadeEconomiasInicial,

	// quantidadeEconomiasFinal, diaVencimento, idCliente,

	// idClienteTipo, idClienteRelacaoTipo,

	// numeroPontosInicial, numeroPontosFinal,

	// numeroMoradoresInicial, numeroMoradoresFinal,

	// idAreaConstruidaFaixa);

	//

	// /*

	// * # COLOCANDO O VALOR NAS CONDIÇÕES#

	// */

	//

	// /*

	// * consulta = consulta + " consumosHistorico.referenciaFaturamento = " +

	// * "(select max(consumoHistorico.referenciaFaturamento) from

	// * ConsumoHistorico consumoHistorico " + " left join

	// * consumoHistorico.imovel imovelConsumoHistorico " + "where

	// * imovelConsumoHistorico.id = imovel.id) and ";

	// */

	//

	// Query query = session.createQuery(consulta.substring(0, (consulta

	// .length() - 5)));

	//

	// informarDadosQueryFiltrarImovelOutrosCriterio(query,

	// idImovelCondominio, idImovelPrincipal, idNomeConta,

	// idSituacaoLigacaoAgua, consumoMinimoInicialAgua,

	// consumoMinimoFinalAgua, idSituacaoLigacaoEsgoto,

	// consumoMinimoInicialEsgoto, consumoMinimoFinalEsgoto,

	// intervaloValorPercentualEsgotoInicial,

	// intervaloValorPercentualEsgotoFinal,

	// intervaloMediaMinimaImovelInicial,

	// intervaloMediaMinimaImovelFinal,

	// intervaloMediaMinimaHidrometroInicial,

	// intervaloMediaMinimaHidrometroFinal, idImovelPerfil,

	// idPocoTipo, idFaturamentoSituacaoTipo,

	// idCobrancaSituacaoTipo, idSituacaoEspecialCobranca,

	// idEloAnormalidade, areaConstruidaInicial,

	// areaConstruidaFinal, idCadastroOcorrencia, idConsumoTarifa,

	// idGerenciaRegional, idLocalidadeInicial, idLocalidadeFinal,

	// setorComercialInicial, setorComercialFinal, quadraInicial,

	// quadraFinal, loteOrigem, loteDestno, cep, logradouro,

	// bairro, municipio, idTipoMedicao, indicadorMedicao,

	// idSubCategoria, idCategoria, quantidadeEconomiasInicial,

	// quantidadeEconomiasFinal, diaVencimento, idCliente,

	// idClienteTipo, idClienteRelacaoTipo, numeroPontosInicial,

	// numeroPontosFinal, numeroMoradoresInicial,

	// numeroMoradoresFinal, idAreaConstruidaFaixa);

	//

	// retorno = query.list();

	//

	// } catch (HibernateException e) {

	// // levanta a exceção para a próxima camada

	// throw new ErroRepositorioException(e, "Erro no Hibernate");

	// } finally {

	// // fecha a sessão

	// HibernateUtil.closeSession(session);

	// }

	//

	// return retorno;

	// }

	/**
	 * 
	 * 
	 * 
	 * [UC0164]Gerar Relatório de Imóvel Outros Critérios
	 * 
	 * 
	 * 
	 * Monta a Condição do where do Filtrar Imoveis Outros Criterios
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 24/07/2006
	 * 
	 * @deprecated
	 * 
	 * 
	 * 
	 */

	public String montarCondicaoWhereFiltrarImovelOutrosCriterio(

	String idImovelCondominio, String idImovelPrincipal,

	String idNomeConta, String idSituacaoLigacaoAgua,

	String consumoMinimoInicialAgua, String consumoMinimoFinalAgua,

	String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto,

	String consumoMinimoFinalEsgoto,

	String intervaloValorPercentualEsgotoInicial,

	String intervaloValorPercentualEsgotoFinal,

	String intervaloMediaMinimaImovelInicial,

	String intervaloMediaMinimaImovelFinal,

	String intervaloMediaMinimaHidrometroInicial,

	String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,

	String idPocoTipo, String idFaturamentoSituacaoTipo,

	String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,

	String idEloAnormalidade, String areaConstruidaInicial,

	String areaConstruidaFinal, String idCadastroOcorrencia,

	String idConsumoTarifa, String idGerenciaRegional,

	String idLocalidadeInicial, String idLocalidadeFinal,

	String setorComercialInicial, String setorComercialFinal,

	String quadraInicial, String quadraFinal, String loteOrigem,

	String loteDestno, String cep, String logradouro, String bairro,

	String municipio, String idTipoMedicao, String indicadorMedicao,

	String idSubCategoria, String idCategoria,

	String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,

	String diaVencimento, String idCliente, String idClienteTipo,

	String idClienteRelacaoTipo, String numeroPontosInicial,

	String numeroPontosFinal, String numeroMoradoresInicial,

	String numeroMoradoresFinal, String idAreaConstruidaFaixa) {

		String consulta = "";

		/*
		 * ## CONDIÇÕES ##
		 * 
		 */

		consulta = consulta + " where imovel.indicadorExclusao != "

		+ Imovel.IMOVEL_EXCLUIDO + " and  ";

		// cliente

		if (idCliente != null

		&& !idCliente.equals("")

		&& !idCliente.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta + " cliente.id = :idCliente  and  ";

		}

		// cliente tipo

		if (idClienteTipo != null

		&& !idClienteTipo.equals("")

		&& !idClienteTipo.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta + " clienteTipo.id = :idClienteTipo  and  ";

		}

		// cliente relacao tipo

		if (idClienteRelacaoTipo != null

		&& !idClienteRelacaoTipo.equals("")

		&& !idClienteRelacaoTipo.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta

			+ " clienteRelacaoTipo.id = :idClienteRelacaoTipo  and  ";

		}

		// gerencia regional

		if (idGerenciaRegional != null

		&& !idGerenciaRegional.equals("")

		&& !idGerenciaRegional.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta

			+ " gerenciaRegional.id = :idGerenciaRegional and ";

		}

		// localidade inicial e final

		if (((idLocalidadeInicial != null && !idLocalidadeInicial.equals("") && !idLocalidadeInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) && (idLocalidadeFinal != null

		&& !idLocalidadeFinal.equals("") && !idLocalidadeFinal.trim()

		.equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())))) {

			consulta = consulta

					+ " localidade.id >= :idLocalidadeInicial and localidade.id <= :idLocalidadeFinal  and  ";

		}

		// setor comercial inicial e final

		if (((setorComercialInicial != null

		&& !setorComercialInicial.equals("") && !setorComercialInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) && (setorComercialFinal != null

		&& !setorComercialFinal.equals("") && !setorComercialFinal

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())))) {

			consulta = consulta

					+ " setorComercial.codigo >= :setorComercialInicial and setorComercial.codigo <= :setorComercialFinal  and  ";

		}

		// quadra

		if ((quadraInicial != null && !quadraInicial.equals("") && !quadraInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

				&& (quadraFinal != null && !quadraFinal.equals("") && !quadraFinal

				.trim().equalsIgnoreCase(

				new Integer(

				ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

			consulta = consulta

					+ " quadra.numeroQuadra >= :quadraInicial and quadra.numeroQuadra <= :quadraFinal and  ";

		}

		// lote

		if ((loteOrigem != null && !loteOrigem.equals("") && !loteOrigem.trim()

		.equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

		&& (loteDestno != null && !loteDestno.equals("") && !loteDestno

		.trim().equalsIgnoreCase(

		new Integer(

		ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))) {

			consulta = consulta

			+ " imovel.lote >= :loteOrigem  and  imovel.lote <= :loteDestino";

		}

		// cep

		if (cep != null

		&& !cep.equals("")

		&& !cep.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta + " cep.codigo = :cep  and  ";

		}

		// logradouro

		if (logradouro != null

		&& !logradouro.equals("")

		&& !logradouro.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta + " logradouro.id = :logradouro  and  ";

		}

		// bairro

		if (bairro != null

		&& !bairro.equals("")

		&& !bairro.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta + " bairro.codigo = :bairro  and  ";

		}

		// municipio

		if (municipio != null

		&& !municipio.equals("")

		&& !municipio.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta + " municipio.id = :municipio  and  ";

		}

		// consumo minimo agua inicial e final

		if ((consumoMinimoInicialAgua != null

		&& !consumoMinimoInicialAgua.equals("") && !consumoMinimoInicialAgua

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

		&& (consumoMinimoFinalAgua != null

		&& !consumoMinimoFinalAgua.equals("") && !consumoMinimoFinalAgua

		.trim().equalsIgnoreCase(

		new Integer(

		ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))) {

			consulta = consulta

					+ "ligacaoAgua.numeroConsumoMinimoAgua >= :consumoMinimoInicialAgua and ligacaoAgua.numeroConsumoMinimoAgua <= :consumoMinimoFinalAgua  and  ";

		}

		// consumo minimo esgoto inicial e final

		if ((consumoMinimoInicialEsgoto != null

		&& !consumoMinimoInicialEsgoto.equals("") && !consumoMinimoInicialEsgoto

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

				&& (consumoMinimoFinalEsgoto != null

				&& !consumoMinimoFinalEsgoto.equals("") && !consumoMinimoFinalEsgoto

				.trim().equalsIgnoreCase(

				new Integer(

				ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

			consulta = consulta

					+ "ligacaoEsgoto.consumoMinimo >= :consumoMinimoInicialEsgoto and ligacaoEsgoto.consumoMinimo <= :consumoMinimoFinalEsgoto  and  ";

		}

		// percentual esgoto inicial e final

		if ((intervaloValorPercentualEsgotoInicial != null

		&& !intervaloValorPercentualEsgotoInicial.equals("") && !intervaloValorPercentualEsgotoInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

				&& (intervaloValorPercentualEsgotoFinal != null

				&& !intervaloValorPercentualEsgotoFinal.equals("") && !intervaloValorPercentualEsgotoFinal

				.trim().equalsIgnoreCase(

				new Integer(

				ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

			consulta = consulta

					+ "ligacaoEsgoto.percentual >= :intervaloValorPercentualEsgotoInicial and ligacaoEsgoto.percentual <= :intervaloValorPercentualEsgotoFinal  and  ";

		}

		// indicador medição

		if (indicadorMedicao != null && indicadorMedicao.equals("comMedicao")) {

			// tipo medicao

			if (idTipoMedicao != null

			&& idTipoMedicao

			.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {

				consulta = consulta

				+ "hidrometroInstalacaoHistorico.id is not null  and  ";

			} else if (idTipoMedicao != null

			&& idTipoMedicao.equals(MedicaoTipo.POCO.toString())) {

				consulta = consulta

				+ "hidrometroInstalacaoHistoricoImovel.id is not null and  ";

			} else {

				consulta = consulta

				+ "(hidrometroInstalacaoHistorico.id is not null or ";

				consulta = consulta

				+ "hidrometroInstalacaoHistoricoImovel.id is not null) and  ";

			}

		} else if (indicadorMedicao != null

		&& indicadorMedicao.equals("semMedicao")) {

			consulta = consulta

			+ "(hidrometroInstalacaoHistorico.id is null and";

			consulta = consulta

			+ "hidrometroInstalacaoHistoricoImovel.id is null) and  ";

		}

		// imovel condominio

		if (idImovelCondominio != null

		&& !idImovelCondominio.equals("")

		&& !idImovelCondominio.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta + " imovelCondominio.id = :idImovelCondominio ";

		}

		// imovel principal

		if (idImovelPrincipal != null

		&& !idImovelPrincipal.equals("")

		&& !idImovelPrincipal.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta + " imovelPrincipal.id: = idImovelPrincipal ";

		}

		// nome conta

		// if (idNomeConta != null

		// && !idNomeConta.equals("")

		// && !idNomeConta.trim().equalsIgnoreCase(

		// new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		// .toString())) {

		// consulta = consulta + " nomeConta.id = :idNomeConta and ";

		// }

		// situação da ligação de agua

		if (idSituacaoLigacaoAgua != null

		&& !idSituacaoLigacaoAgua.equals("")

		&& !idSituacaoLigacaoAgua.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta

			+ " ligacaoAguaSituacao.id = :idSituacaoLigacaoAgua  and  ";

		}

		// situação ligação de esgoto

		if (idSituacaoLigacaoEsgoto != null

		&& !idSituacaoLigacaoEsgoto.equals("")

		&& !idSituacaoLigacaoEsgoto.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta

			+ " ligacaoEsgotoSituacao.id = :idSituacaoLigacaoEsgoto  and  ";

		}

		// imovel Perfil

		if (idImovelPerfil != null

		&& !idImovelPerfil.equals("")

		&& !idImovelPerfil.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta + " imovelPerfil.id = :idImovelPerfil  and  ";

		}

		// poço tipo

		if (idPocoTipo != null

		&& !idPocoTipo.equals("")

		&& !idPocoTipo.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta + " pocoTipo.id = :idPocoTipo  and  ";

		}

		// faturamento situacao tipo

		if (idFaturamentoSituacaoTipo != null

		&& !idFaturamentoSituacaoTipo.equals("")

		&& !idFaturamentoSituacaoTipo.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta

			+ " faturamentoTipo.id = :idFaturamentoSituacaoTipo  and  ";

		}

		// cobranca situacao tipo

		if (idCobrancaSituacaoTipo != null

		&& !idCobrancaSituacaoTipo.equals("")

		&& !idCobrancaSituacaoTipo.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta

			+ " cobrancaSituacaoTipo.id = :idCobrancaSituacaoTipo  and  ";

		}

		// Situacao Especial Cobranca

		if (idSituacaoEspecialCobranca != null

		&& !idSituacaoEspecialCobranca.equals("")

		&& !idSituacaoEspecialCobranca.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta

			+ " faturamentoSituacaoTipo.id = :idSituacaoEspecialCobranca ";

		}

		// elo anormalidade

		if (idEloAnormalidade != null

		&& !idEloAnormalidade.equals("")

		&& !idEloAnormalidade.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta

			+ "eloAnormalidade.id = :idEloAnormalidade  and  ";

		}

		// cadastro ocorrencia

		if (idCadastroOcorrencia != null

		&& !idCadastroOcorrencia.equals("")

		&& !idCadastroOcorrencia.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta

			+ " cadastroOcorrencia.id = :idCadastroOcorrencia  and  ";

		}

		// area construida inicial e final

		if ((areaConstruidaInicial != null && !areaConstruidaInicial.equals("") && !areaConstruidaInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

				&& (areaConstruidaFinal != null

				&& !areaConstruidaFinal.equals("") && !areaConstruidaFinal

				.trim().equalsIgnoreCase(

				new Integer(

				ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

			consulta = consulta

					+ " imovel.areaConstruida >= :areaConstruidaInicial and imovel.areaConstruida <= :areaConstruidaFinal  and  ";

		}

		// consumo tarifa

		if (idConsumoTarifa != null

		&& !idConsumoTarifa.equals("")

		&& !idConsumoTarifa.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta

			+ " consumoTarifa.id = :idConsumoTarifa   and  ";

		}

		// intervalo Media Minima Imovel Inicial e Final

		if ((intervaloMediaMinimaImovelInicial != null

		&& !intervaloMediaMinimaImovelInicial.equals("") && !intervaloMediaMinimaImovelInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

				&& (intervaloMediaMinimaImovelFinal != null

				&& !intervaloMediaMinimaImovelFinal.equals("") && !intervaloMediaMinimaImovelFinal

				.trim().equalsIgnoreCase(

				new Integer(

				ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

			consulta = consulta

					+ " consumosHistorico.consumoMedio >= :intervaloMediaMinimaImovelInicial and consumosHistorico.consumoMedio <= :intervaloMediaMinimaImovelFinal  and  ";

		}

		// intervalo MediaMinima Hidrometro Inicial e Final

		if ((intervaloMediaMinimaHidrometroInicial != null

		&& !intervaloMediaMinimaHidrometroInicial.equals("") && !intervaloMediaMinimaHidrometroInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

				&& (intervaloMediaMinimaHidrometroFinal != null

				&& !intervaloMediaMinimaHidrometroFinal.equals("") && !intervaloMediaMinimaHidrometroFinal

				.trim().equalsIgnoreCase(

				new Integer(

				ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

			consulta = consulta

					+ " medicaoHistorico.consumoMedioHidrometro >= :intervaloMediaMinimaHidrometroInicial and medicaoHistorico.consumoMedioHidrometro <= :intervaloMediaMinimaHidrometroFinal  and  ";

		}

		// categoria

		if (idCategoria != null

		&& !idCategoria.equals("")

		&& !idCategoria.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta + " categoria.id = :idCategoria  and  ";

		}

		// sub categoria

		if (idSubCategoria != null

		&& !idSubCategoria.equals("")

		&& !idSubCategoria.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta + " subCategoria.id = :idSubCategoria  and  ";

		}

		// quantidade economias inicial e final

		if ((quantidadeEconomiasInicial != null

		&& !quantidadeEconomiasInicial.equals("") && !quantidadeEconomiasInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

				&& (quantidadeEconomiasFinal != null

				&& !quantidadeEconomiasFinal.equals("") && !quantidadeEconomiasFinal

				.trim().equalsIgnoreCase(

				new Integer(

				ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

			consulta = consulta

					+ "imovelSubcategoria.quantidadeEconomias >= :quantidadeEconomiasInicial and imovelSubcategoria.quantidadeEconomias <= :quantidadeEconomiasFinal  and  ";

		}

		// dia Vencimento

		if (diaVencimento != null

		&& !diaVencimento.equals("")

		&& !diaVencimento.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			if (diaVencimento.equals("1")) {// sim

				consulta = consulta + " imovel.diaVencimento  is not null ";

			}

		}

		// numero prontos inicial e final

		if ((numeroPontosInicial != null && !numeroPontosInicial.equals("") && !numeroPontosInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

				&& (numeroPontosFinal != null && !numeroPontosFinal.equals("") && !numeroPontosFinal

				.trim().equalsIgnoreCase(

				new Integer(

				ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

			consulta = consulta

					+ " imove.numeroPontosUtilizacao >= :numeroPontosInicial and imovel.numeroPontosUtilizacao <= :numeroPontosFinal and ";

		}

		// numero moradores inicial e final

		if ((numeroMoradoresInicial != null

		&& !numeroMoradoresInicial.equals("") && !numeroMoradoresInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

		&& (numeroMoradoresFinal != null

		&& !numeroMoradoresFinal.equals("") && !numeroMoradoresFinal

		.trim().equalsIgnoreCase(

		new Integer(

		ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))) {

			consulta = consulta

					+ " imovel.numeroMorador >= :numeroMoradoresInicial and imovel.numeroMorador <= :numeroMoradoresFinal and ";

		}

		// area construida faixa

		if (idAreaConstruidaFaixa != null

		&& !idAreaConstruidaFaixa.equals("")

		&& !idAreaConstruidaFaixa.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			consulta = consulta

			+ " areaConstruidaFaixa.id = :idAreaConstruidaFaixa and ";

		}

		return consulta;

	}

	/**
	 * 
	 * 
	 * 
	 * [Uc0164]Gerar Relatório de Imóvel Outros Critérios
	 * 
	 * 
	 * 
	 * Monta a Query do Filtrar Imoveis Outros Criterios
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 24/07/2006
	 * 
	 * 
	 * 
	 */

	public void informarDadosQueryFiltrarImovelOutrosCriterio(SQLQuery query,

	String idImovelCondominio, String idImovelPrincipal,

	String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,

	String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,

	String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,

	String intervaloValorPercentualEsgotoInicial,

	String intervaloValorPercentualEsgotoFinal,

	String intervaloMediaMinimaImovelInicial,

	String intervaloMediaMinimaImovelFinal,

	String intervaloMediaMinimaHidrometroInicial,

	String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,

	String idPocoTipo, String idFaturamentoSituacaoTipo,

	String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,

	String idEloAnormalidade, String areaConstruidaInicial,

	String areaConstruidaFinal, String idCadastroOcorrencia,

	String idConsumoTarifa, String idGerenciaRegional,

	String idLocalidadeInicial, String idLocalidadeFinal,

	String setorComercialInicial, String setorComercialFinal,

	String quadraInicial, String quadraFinal, String loteOrigem,

	String loteDestno, String cep, String logradouro, String bairro,

	String municipio, String idTipoMedicao, String indicadorMedicao,

	String idSubCategoria, String idCategoria,

	String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,

	String diaVencimento, String idCliente, String idClienteTipo,

	String idClienteRelacaoTipo, String numeroPontosInicial,

	String numeroPontosFinal, String numeroMoradoresInicial,

	String numeroMoradoresFinal, String idAreaConstruidaFaixa,

	String idUnidadeNegocio, String cdRotaInicial, String cdRotaFinal,
			String sequencialRotaInicial, String sequencialRotaFinal, String[] pocoTipoListIds, String cpfCnpj) {

		// gerencia regional

		if (idGerenciaRegional != null

		&& !idGerenciaRegional.equals("")

		&& !idGerenciaRegional.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idGerenciaRegional", new Integer(

			idGerenciaRegional).intValue());

		}

		// unidade negocio

		if (idUnidadeNegocio != null

		&& !idUnidadeNegocio.equals("")

		&& !idUnidadeNegocio.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idUnidadeNegocio", new Integer(idUnidadeNegocio)

			.intValue());

		}

		// localidade inicial e final

		if (((idLocalidadeInicial != null && 
				!idLocalidadeInicial.equals("") && 
				!idLocalidadeInicial.trim().equalsIgnoreCase(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) && 
						(idLocalidadeFinal != null && !idLocalidadeFinal.equals("") && !idLocalidadeFinal.trim()
								.equalsIgnoreCase(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())))) {

			query.setInteger("idLocalidadeInicial", new Integer(idLocalidadeInicial).intValue());

			query.setInteger("idLocalidadeFinal",new Integer(idLocalidadeFinal).intValue());

		}

		// setor comercial inicial e final

		if (((setorComercialInicial != null

		&& !setorComercialInicial.equals("") && !setorComercialInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) && (setorComercialFinal != null

		&& !setorComercialFinal.equals("") && !setorComercialFinal

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())))) {

			query.setInteger("setorComercialInicial", new Integer(

			setorComercialInicial).intValue());

			query.setInteger("setorComercialFinal", new Integer(

			setorComercialFinal).intValue());

		}

		// quadra inicial e final

		if ((quadraInicial != null && !quadraInicial.equals("") && !quadraInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

				&& (quadraFinal != null && !quadraFinal.equals("") && !quadraFinal

				.trim().equalsIgnoreCase(

				new Integer(

				ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

			query.setInteger("quadraInicial", new Integer(quadraInicial)

			.intValue());

			query

			.setInteger("quadraFinal", new Integer(quadraFinal)

			.intValue());

		}

		/*
		 * Alterado por Sávio Luiz Data: 14/09/2007 (Analista:Rosana) Inserir os
		 * parametros de código de rota e sequencial de rota
		 */

		// código de rota inicial e final
		if ((cdRotaInicial != null && !cdRotaInicial.equals("") && !cdRotaInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

				&& (cdRotaFinal != null && !cdRotaFinal.equals("") && !cdRotaFinal

				.trim().equalsIgnoreCase(

				new Integer(

				ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

			query.setShort("cdRotaInicial", new Short(cdRotaInicial));

			query.setShort("cdRotaFinal", new Short(cdRotaFinal));

		}

		// sequencial de rota inicial e final
		if ((sequencialRotaInicial != null && !sequencialRotaInicial.equals("") && !sequencialRotaInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

				&& (sequencialRotaFinal != null
						&& !sequencialRotaFinal.equals("") && !sequencialRotaFinal

				.trim().equalsIgnoreCase(

				new Integer(

				ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

			query.setInteger("sequencialRotaInicial", new Integer(
					sequencialRotaInicial)

			.intValue());

			query

			.setInteger("sequencialRotaFinal", new Integer(sequencialRotaFinal)

			.intValue());

		}

		// lote

		if ((loteOrigem != null && !loteOrigem.equals("") && !loteOrigem.trim()

		.equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

		&& (loteDestno != null && !loteDestno.equals("") && !loteDestno

		.trim().equalsIgnoreCase(

		new Integer(

		ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))) {

			query.setInteger("loteOrigem", new Integer(loteOrigem).intValue());

			query.setInteger("loteDestino", new Integer(loteDestno).intValue());

		}

		// cep

		if (cep != null

		&& !cep.equals("")

		&& !cep.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("cep", new Integer(cep).intValue());

		}

		// logradouro

		if (logradouro != null

		&& !logradouro.equals("")

		&& !logradouro.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("logradouro", new Integer(logradouro).intValue());

		}

		// bairro

		if (bairro != null

		&& !bairro.equals("")

		&& !bairro.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("bairro", new Integer(bairro).intValue());

		}

		// municipio

		if (municipio != null

		&& !municipio.equals("")

		&& !municipio.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("municipio", new Integer(municipio).intValue());

		}

		// consumo minimo agua inicial e final

		if ((consumoMinimoInicialAgua != null

		&& !consumoMinimoInicialAgua.equals("") && !consumoMinimoInicialAgua

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

		&& (consumoMinimoFinalAgua != null

		&& !consumoMinimoFinalAgua.equals("") && !consumoMinimoFinalAgua

		.trim().equalsIgnoreCase(

		new Integer(

		ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))) {

			query.setInteger("consumoMinimoInicialAgua", new Integer(

			consumoMinimoInicialAgua).intValue());

			query.setInteger("consumoMinimoFinalAgua", new Integer(

			consumoMinimoFinalAgua).intValue());

		}

		// consumo minimo esgoto inicial e final

		if ((consumoMinimoInicialEsgoto != null

		&& !consumoMinimoInicialEsgoto.equals("") && !consumoMinimoInicialEsgoto

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

				&& (consumoMinimoFinalEsgoto != null

				&& !consumoMinimoFinalEsgoto.equals("") && !consumoMinimoFinalEsgoto

				.trim().equalsIgnoreCase(

				new Integer(

				ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

			query.setInteger("consumoMinimoInicialEsgoto", new Integer(

			consumoMinimoInicialEsgoto).intValue());

			query.setInteger("consumoMinimoFinalEsgoto", new Integer(

			consumoMinimoFinalEsgoto).intValue());

		}

		// percentual esgoto inicial e final

		if ((intervaloValorPercentualEsgotoInicial != null

		&& !intervaloValorPercentualEsgotoInicial.equals("") && !intervaloValorPercentualEsgotoInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

				&& (intervaloValorPercentualEsgotoFinal != null

				&& !intervaloValorPercentualEsgotoFinal.equals("") && !intervaloValorPercentualEsgotoFinal

				.trim().equalsIgnoreCase(

				new Integer(

				ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

			query.setInteger("intervaloValorPercentualEsgotoInicial",

			new BigDecimal(intervaloValorPercentualEsgotoInicial)

			.intValue());

			query.setInteger("intervaloValorPercentualEsgotoFinal",

			new BigDecimal(intervaloValorPercentualEsgotoFinal)

			.intValue());

		}
		
		if (idTipoMedicao != null && !idTipoMedicao.equals("")&&
				!idTipoMedicao.equals(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			query.setInteger("idTipoMedicao", new Integer(

					idTipoMedicao).intValue());

		}

		// imovel condominio

		if (idImovelCondominio != null

		&& !idImovelCondominio.equals("")

		&& !idImovelCondominio.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idImovelCondominio", new Integer(

			idImovelCondominio).intValue());

		}

		// imovel principal

		if (idImovelPrincipal != null

		&& !idImovelPrincipal.equals("")

		&& !idImovelPrincipal.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idImovelPrincipal",

			new Integer(idImovelPrincipal).intValue());

		}

		// Situacao Ligacao Agua

		if (idSituacaoLigacaoAgua != null

		&& !idSituacaoLigacaoAgua.equals("")

		&& !idSituacaoLigacaoAgua.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idSituacaoLigacaoAgua", new Integer(

			idSituacaoLigacaoAgua).intValue());

		}

		// situação ligação de esgoto

		if (idSituacaoLigacaoEsgoto != null

		&& !idSituacaoLigacaoEsgoto.equals("")

		&& !idSituacaoLigacaoEsgoto.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idSituacaoLigacaoEsgoto", new Integer(

			idSituacaoLigacaoEsgoto).intValue());

		}

		// imovel Perfil

		if (idImovelPerfil != null

		&& !idImovelPerfil.equals("")

		&& !idImovelPerfil.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idImovelPerfil", new Integer(idImovelPerfil)

			.intValue());

		}

		// poço tipo

		if (idPocoTipo != null

		&& !idPocoTipo.equals("")

		&& !idPocoTipo.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idPocoTipo", new Integer(idPocoTipo).intValue());

		}

		// faturamento situacao tipo

		if (idFaturamentoSituacaoTipo != null

		&& !idFaturamentoSituacaoTipo.equals("")

		&& !idFaturamentoSituacaoTipo.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idFaturamentoSituacaoTipo", new Integer(

			idFaturamentoSituacaoTipo).intValue());

		}

		// cobranca situacao tipo

		if (idCobrancaSituacaoTipo != null

		&& !idCobrancaSituacaoTipo.equals("")

		&& !idCobrancaSituacaoTipo.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idCobrancaSituacaoTipo", new Integer(

			idCobrancaSituacaoTipo).intValue());

		}

		// Situacao Especial Cobranca

		if (idSituacaoEspecialCobranca != null

		&& !idSituacaoEspecialCobranca.equals("")

		&& !idSituacaoEspecialCobranca.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idSituacaoEspecialCobranca", new Integer(

			idSituacaoEspecialCobranca).intValue());

		}

		// elo anormalidade

		if (idEloAnormalidade != null

		&& !idEloAnormalidade.equals("")

		&& !idEloAnormalidade.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idEloAnormalidade",

			new Integer(idEloAnormalidade).intValue());

		}

		// cadastro ocorrencia

		if (idCadastroOcorrencia != null

		&& !idCadastroOcorrencia.equals("")

		&& !idCadastroOcorrencia.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idCadastroOcorrencia", new Integer(

			idCadastroOcorrencia).intValue());

		}

		// area construida inicial e final

		if ((areaConstruidaInicial != null && !areaConstruidaInicial.equals("") && !areaConstruidaInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

				&& (areaConstruidaFinal != null

				&& !areaConstruidaFinal.equals("") && !areaConstruidaFinal

				.trim().equalsIgnoreCase(

				new Integer(

				ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

			query.setBigDecimal("areaConstruidaInicial", Util

			.formatarMoedaRealparaBigDecimal(areaConstruidaInicial));

			query.setBigDecimal("areaConstruidaFinal", Util

			.formatarMoedaRealparaBigDecimal(areaConstruidaFinal));

		}

		// consumo tarifa

		if (idConsumoTarifa != null

		&& !idConsumoTarifa.equals("")

		&& !idConsumoTarifa.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idConsumoTarifa", new Integer(idConsumoTarifa)

			.intValue());

		}

		// intervalo Media Minima Imovel Inicial e Final

		if ((intervaloMediaMinimaImovelInicial != null

		&& !intervaloMediaMinimaImovelInicial.equals("") && !intervaloMediaMinimaImovelInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

				&& (intervaloMediaMinimaImovelFinal != null

				&& !intervaloMediaMinimaImovelFinal.equals("") && !intervaloMediaMinimaImovelFinal

				.trim().equalsIgnoreCase(

				new Integer(

				ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

			query.setInteger("intervaloMediaMinimaImovelInicial", new Integer(

			intervaloMediaMinimaImovelInicial).intValue());

			query.setInteger("intervaloMediaMinimaImovelFinal", new Integer(

			intervaloMediaMinimaImovelFinal).intValue());

		}

		// intervalo MediaMinima Hidrometro Inicial e Final

		if ((intervaloMediaMinimaHidrometroInicial != null

		&& !intervaloMediaMinimaHidrometroInicial.equals("") && !intervaloMediaMinimaHidrometroInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

				&& (intervaloMediaMinimaHidrometroFinal != null

				&& !intervaloMediaMinimaHidrometroFinal.equals("") && !intervaloMediaMinimaHidrometroFinal

				.trim().equalsIgnoreCase(

				new Integer(

				ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

			query.setInteger("intervaloMediaMinimaHidrometroInicial",

			new Integer(intervaloMediaMinimaHidrometroInicial)

			.intValue());

			query

			.setInteger("intervaloMediaMinimaHidrometroFinal",

			new Integer(intervaloMediaMinimaHidrometroFinal)

			.intValue());

		}

		// quantidade economias inicial e final

		if ((quantidadeEconomiasInicial != null

		&& !quantidadeEconomiasInicial.equals("") && !quantidadeEconomiasInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

				&& (quantidadeEconomiasFinal != null

				&& !quantidadeEconomiasFinal.equals("") && !quantidadeEconomiasFinal

				.trim().equalsIgnoreCase(

				new Integer(

				ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

			query.setShort("quantidadeEconomiasInicial", new Short(

			quantidadeEconomiasInicial).shortValue());

			query.setShort("quantidadeEconomiasFinal", new Short(

			quantidadeEconomiasFinal).shortValue());

		}

		// categoria

		if (idCategoria != null

		&& !idCategoria.equals("")

		&& !idCategoria.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query

			.setInteger("idCategoria", new Integer(idCategoria)

			.intValue());

		}

		// sub categoria

		if (idSubCategoria != null

		&& !idSubCategoria.equals("")

		&& !idSubCategoria.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idSubCategoria", new Integer(idSubCategoria)

			.intValue());

		}

		// numero prontos inicial e final

		if ((numeroPontosInicial != null && !numeroPontosInicial.equals("") && !numeroPontosInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

				&& (numeroPontosFinal != null && !numeroPontosFinal.equals("") && !numeroPontosFinal

				.trim().equalsIgnoreCase(

				new Integer(

				ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

			query.setShort("numeroPontosInicial",

			new Short(numeroPontosInicial).shortValue());

			query.setShort("numeroPontosFinal", new Short(numeroPontosFinal)

			.shortValue());

		}

		// numero moradores inicial e final

		if ((numeroMoradoresInicial != null

		&& !numeroMoradoresInicial.equals("") && !numeroMoradoresInicial

		.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))

		&& (numeroMoradoresFinal != null

		&& !numeroMoradoresFinal.equals("") && !numeroMoradoresFinal

		.trim().equalsIgnoreCase(

		new Integer(

		ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString()))) {

			query.setShort("numeroMoradoresInicial", new Short(

			numeroMoradoresInicial).shortValue());

			query.setShort("numeroMoradoresFinal", new Short(

			numeroMoradoresFinal).shortValue());

		}

		// area construida faixa

		if (idAreaConstruidaFaixa != null

		&& !idAreaConstruidaFaixa.equals("")

		&& !idAreaConstruidaFaixa.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idAreaConstruidaFaixa", new Integer(

			idAreaConstruidaFaixa).intValue());

		}

		// cliente

		if (idCliente != null

		&& !idCliente.equals("")

		&& !idCliente.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idCliente", new Integer(idCliente).intValue());

		}

		// cliente tipo

		if (idClienteTipo != null

		&& !idClienteTipo.equals("")

		&& !idClienteTipo.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idClienteTipo", new Integer(idClienteTipo)

			.intValue());

		}

		// cliente relacao tipo

		if (idClienteRelacaoTipo != null

		&& !idClienteRelacaoTipo.equals("")

		&& !idClienteRelacaoTipo.trim().equalsIgnoreCase(

		new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

		.toString())) {

			query.setInteger("idClienteRelacaoTipo", new Integer(

			idClienteRelacaoTipo).intValue());

		}
		
		// poço tipo list 
		
		if (pocoTipoListIds != null &&

				(
						((pocoTipoListIds[0].equals("-1") && pocoTipoListIds.length > 1)) || 
						( !pocoTipoListIds[0].equals("-1")))) 

		{
			
			query.setParameterList("pocoTipoListIds", pocoTipoListIds);
			
		} 
		
		if(cpfCnpj!=null && !cpfCnpj.equals("")){
			query.setString("cpfCnpj", cpfCnpj);
		}

		
	}

	public Collection pesquisarSubcategoriasImovelRelatorio(Integer idImovel)

	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno = null;

		try {

			String consultaSubcategoria = "select" + " sub.descricao,"

			+ " isc.quantidadeEconomias"

			+ " from ImovelSubcategoria isc"

			+ " inner join isc.comp_id.imovel im"

			+ " inner join isc.comp_id.subcategoria sub"

			+ " where im.id = " + idImovel;

			retorno = session.createQuery(consultaSubcategoria).list();

		} catch (HibernateException e) {

			e.printStackTrace();

			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

			// session.close();

		}

		return retorno;

	}

	/**
	 * [UC0164] Filtrar Imoveis por Outros Criterios
	 * Filtra para saber a quantidade de imoveis antes de executar o filtro
	 *
	 * @author Rafael Santos
	 * @date 01/08/2006
	 */

	public Integer obterQuantidadaeRelacaoImoveisDebitos(
			String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,
			String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia,
			String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String setorComercialInicial, String setorComercialFinal,
			String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro,
			String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			String idUnidadeNegocio, Integer relatorio,String cdRotaInicial,
			String cdRotaFinal, String sequencialRotaInicial,
			String sequencialRotaFinal) throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select count(distinct imovel.imov_id) as quantidade " 
				+	"from cadastro.imovel_subcategoria imovelSubcategoria "// From
				+	"inner join cadastro.imovel on imovelSubcategoria.imov_id = cadastro.imovel.imov_id "
				+ 	"inner join cadastro.localidade on imovel.loca_id = cadastro.localidade.loca_id "
				+ 	"inner join cadastro.gerencia_regional on cadastro.localidade.greg_id = cadastro.gerencia_regional.greg_id "
				+ 	"inner join cadastro.setor_comercial on cadastro.imovel.stcm_id = cadastro.setor_comercial.stcm_id "
				+ 	"left join cadastro.logradouro_bairro on cadastro.imovel.lgbr_id = cadastro.logradouro_bairro.lgbr_id "// Logradouro Bairro
				+ 	"left join cadastro.bairro on cadastro.logradouro_bairro.bair_id = cadastro.bairro.bair_id "
				+ 	"left join cadastro.municipio on cadastro.bairro.muni_id = cadastro.municipio.muni_id "
				+ 	"inner join cadastro.quadra on cadastro.imovel.qdra_id = cadastro.quadra.qdra_id "
				+ 	"inner join micromedicao.rota on cadastro.quadra.rota_id = micromedicao.rota.rota_id "
				+ 	"left join cadastro.logradouro_cep on cadastro.imovel.lgcp_id = cadastro.logradouro_cep.lgcp_id "// Logradouro Cep
				+ 	"left join cadastro.cep on cadastro.logradouro_cep.cep_id = cadastro.cep.cep_id "
				+ 	"left join cadastro.logradouro on cadastro.logradouro_cep.logr_id = cadastro.logradouro.logr_id "
				+ 	"left join atendimentopublico.ligacao_agua_situacao on cadastro.imovel.last_id = atendimentopublico.ligacao_agua_situacao.last_id "// AGUA
				+ 	"left join atendimentopublico.ligacao_agua on cadastro.imovel.imov_id = atendimentopublico.ligacao_agua.lagu_id "
				+	"left join atendimentopublico.ligacao_esgoto_situacao on cadastro.imovel.lest_id = atendimentopublico.ligacao_esgoto_situacao.lest_id "// ESGOTO
				+ 	"left join atendimentopublico.ligacao_esgoto on cadastro.imovel.imov_id = atendimentopublico.ligacao_esgoto.lesg_id "
				+	"left join cadastro.imovel_perfil on cadastro.imovel.iper_id = cadastro.imovel_perfil.iper_id "
				+	"left join cadastro.poco_tipo on cadastro.imovel.poco_id = cadastro.poco_tipo.poco_id "
				+	"left join cadastro.area_construida_faixa on cadastro.imovel.acon_id = area_construida_faixa.acon_id "
				+	"left join micromedicao.hidrometro_inst_hist on atendimentopublico.ligacao_agua.hidi_id =  micromedicao.hidrometro_inst_hist.hidi_id "// AGUA
				+	"left join micromedicao.hidrometro_inst_hist hidrometro_instalacao_historic on cadastro.imovel.hidi_id =  hidrometro_instalacao_historic.hidi_id ";// ESGOTO

			consulta = consulta + 
				montarInnerJoinFiltrarImoveisOutrosCriterios(
					intervaloMediaMinimaImovelInicial,
					intervaloMediaMinimaImovelFinal,
					intervaloMediaMinimaHidrometroInicial,
					intervaloMediaMinimaHidrometroFinal,
					idFaturamentoSituacaoTipo, 
					idCobrancaSituacaoTipo,
					idSituacaoEspecialCobranca, 
					idEloAnormalidade,
					idCadastroOcorrencia, 
					idConsumoTarifa,
					idTipoMedicao, 
					indicadorMedicao, 
					idSubCategoria,
					idCategoria, 
					idCliente, 
					idClienteTipo,
					idClienteRelacaoTipo, 
					relatorio, null);

			consulta = consulta + 
				montarCondicaoSQLWhereFiltrarImovelOutrosCriterio(
					idImovelCondominio, 
					idImovelPrincipal,
					idSituacaoLigacaoAgua, 
					consumoMinimoInicialAgua,
					consumoMinimoFinalAgua, 
					idSituacaoLigacaoEsgoto,
					consumoMinimoInicialEsgoto,
					consumoMinimoFinalEsgoto,
					intervaloValorPercentualEsgotoInicial,
					intervaloValorPercentualEsgotoFinal,
					intervaloMediaMinimaImovelInicial,
					intervaloMediaMinimaImovelFinal,
					intervaloMediaMinimaHidrometroInicial,
					intervaloMediaMinimaHidrometroFinal,
					idImovelPerfil, idPocoTipo,
					idFaturamentoSituacaoTipo, 
					idCobrancaSituacaoTipo,
					idSituacaoEspecialCobranca, 
					idEloAnormalidade,
					areaConstruidaInicial, 
					areaConstruidaFinal,
					idCadastroOcorrencia, 
					idConsumoTarifa,
					idGerenciaRegional, 
					idLocalidadeInicial,
					idLocalidadeFinal, 
					setorComercialInicial,
					setorComercialFinal, 
					quadraInicial, 
					quadraFinal,
					loteOrigem, 
					loteDestno, 
					cep, 
					logradouro,
					bairro,
					municipio, 
					idTipoMedicao, 
					indicadorMedicao,
					idSubCategoria,
					idCategoria,
					quantidadeEconomiasInicial,
					quantidadeEconomiasFinal, 
					diaVencimento, 
					idCliente,
					idClienteTipo, 
					idClienteRelacaoTipo,
					numeroPontosInicial, 
					numeroPontosFinal,
					numeroMoradoresInicial, 
					numeroMoradoresFinal,
					idAreaConstruidaFaixa, 
					idUnidadeNegocio,
					relatorio, 
					cdRotaInicial,
					cdRotaFinal,
					sequencialRotaInicial,
					sequencialRotaFinal,
					null, null , null);

			/*
			 * # COLOCANDO O VALOR NAS CONDIÇÕES#
			 */

			SQLQuery query = session.createSQLQuery(consulta.substring(0, (consulta.length() - 5)));

			informarDadosQueryFiltrarImovelOutrosCriterio(query,
				idImovelCondominio, 
				idImovelPrincipal,
				idSituacaoLigacaoAgua, 
				consumoMinimoInicialAgua,
				consumoMinimoFinalAgua, 
				idSituacaoLigacaoEsgoto,
				consumoMinimoInicialEsgoto, 
				consumoMinimoFinalEsgoto,
				intervaloValorPercentualEsgotoInicial,
				intervaloValorPercentualEsgotoFinal,
				intervaloMediaMinimaImovelInicial,
				intervaloMediaMinimaImovelFinal,
				intervaloMediaMinimaHidrometroInicial,
				intervaloMediaMinimaHidrometroFinal, 
				idImovelPerfil,
				idPocoTipo, 
				idFaturamentoSituacaoTipo,
				idCobrancaSituacaoTipo, 
				idSituacaoEspecialCobranca,
				idEloAnormalidade, 
				areaConstruidaInicial,
				areaConstruidaFinal, 
				idCadastroOcorrencia, 
				idConsumoTarifa,
				idGerenciaRegional, 
				idLocalidadeInicial, 
				idLocalidadeFinal,
				setorComercialInicial, 
				setorComercialFinal,
				quadraInicial,
				quadraFinal, 
				loteOrigem, 
				loteDestno, 
				cep, 
				logradouro,
				bairro, 
				municipio, 
				idTipoMedicao, 
				indicadorMedicao,
				idSubCategoria, 
				idCategoria, 
				quantidadeEconomiasInicial,
				quantidadeEconomiasFinal, 
				diaVencimento, 
				idCliente,
				idClienteTipo, 
				idClienteRelacaoTipo, 
				numeroPontosInicial,
				numeroPontosFinal, 
				numeroMoradoresInicial,
				numeroMoradoresFinal, 
				idAreaConstruidaFaixa,
				idUnidadeNegocio, 
				cdRotaInicial,
				cdRotaFinal,
				sequencialRotaInicial,
				sequencialRotaFinal, null, null);

			retorno = 
				(Integer) query.addScalar("quantidade", Hibernate.INTEGER).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * 
	 * 
	 * [UC0164]Gerar Relatório de Imóvel Outros Critérios
	 * 
	 * 
	 * 
	 * Monta a Condição do where do Filtrar Imoveis Outros Criterios
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 24/07/2006
	 * 
	 * 
	 * 
	 */

	private String montarCondicaoSQLWhereFiltrarImovelOutrosCriterio(String idImovelCondominio, 
										String idImovelPrincipal,
										String idSituacaoLigacaoAgua, 
										String consumoMinimoInicialAgua,
										String consumoMinimoFinalAgua, 
										String idSituacaoLigacaoEsgoto,
										String consumoMinimoInicialEsgoto, 
										String consumoMinimoFinalEsgoto,
										String intervaloValorPercentualEsgotoInicial,
										String intervaloValorPercentualEsgotoFinal,
										String intervaloMediaMinimaImovelInicial,
										String intervaloMediaMinimaImovelFinal,
										String intervaloMediaMinimaHidrometroInicial,
										String intervaloMediaMinimaHidrometroFinal, 
										String idImovelPerfil,
										String idPocoTipo, 
										String idFaturamentoSituacaoTipo,
										String idCobrancaSituacaoTipo, 
										String idSituacaoEspecialCobranca,
										String idEloAnormalidade, 
										String areaConstruidaInicial,
										String areaConstruidaFinal, 
										String idCadastroOcorrencia,
										String idConsumoTarifa, 
										String idGerenciaRegional,
										String idLocalidadeInicial, 
										String idLocalidadeFinal,
										String setorComercialInicial, 
										String setorComercialFinal,
										String quadraInicial, 
										String quadraFinal, 
										String loteOrigem,
										String loteDestno, 
										String cep, 
										String logradouro, 
										String bairro,
										String municipio, 
										String idTipoMedicao, 
										String indicadorMedicao,
										String idSubCategoria, 
										String idCategoria,
										String quantidadeEconomiasInicial, 
										String quantidadeEconomiasFinal,
										String diaVencimento, 
										String idCliente, 
										String idClienteTipo,
										String idClienteRelacaoTipo, 
										String numeroPontosInicial,
										String numeroPontosFinal, 
										String numeroMoradoresInicial,
										String numeroMoradoresFinal, 
										String idAreaConstruidaFaixa,
										String idUnidadeNegocio, 
										Integer relatorio, 
										String cdRotaInicial,
										String cdRotaFinal, 
										String sequencialRotaInicial,
										String sequencialRotaFinal,
										String[] pocoTipoListIds,
										String indicadorCpfCnpj, 
										String cpfCnpj) {

		String consulta = "";
		
		consulta = consulta + " where imovel.imov_icexclusao != "
							+ Imovel.IMOVEL_EXCLUIDO + " and  ";
		// cliente
		if (idCliente != null && !idCliente.equals("") 
				&& !idCliente.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {
			consulta = consulta + " cliente.clie_id = :idCliente  and  ";
			}

		// cliente tipo
		if (idClienteTipo != null && !idClienteTipo.equals("") 
				&& !idClienteTipo.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {
			consulta = consulta + " cliente.cltp_id = :idClienteTipo  and  ";
		}
		
		// cliente relacao tipo

		if (idClienteRelacaoTipo != null && !idClienteRelacaoTipo.equals("")
				&& !idClienteRelacaoTipo.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {
			// Relatorio de Economias do Imovel
			if (relatorio != null && relatorio.intValue() 
					== ConstantesSistema.GERAR_RELATORIO_DADOS_ECONOMIA_IMOVEL.intValue()) {
				
				consulta = consulta + " cliente_imovel_economia.crtp_id = :idClienteRelacaoTipo  and  ";

			} else {

				consulta = consulta + " cliente_imovel.crtp_id = :idClienteRelacaoTipo  and  ";

			}

		}

		// gerencia regional
		if (idGerenciaRegional != null && !idGerenciaRegional.equals("")
				&& !idGerenciaRegional.trim()
						.equalsIgnoreCase(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta + " gerencia_regional.greg_id = :idGerenciaRegional and ";

		}

		// unidade negocio
		if (idUnidadeNegocio != null && !idUnidadeNegocio.equals("") 
				&& !idUnidadeNegocio.trim()
						.equalsIgnoreCase(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta + " localidade.uneg_id = :idUnidadeNegocio and ";

		}

		// localidade inicial e final
		if (((idLocalidadeInicial != null && !idLocalidadeInicial.equals("") 
				&& !idLocalidadeInicial.trim()
					.equalsIgnoreCase(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) 
					&& (idLocalidadeFinal != null && !idLocalidadeFinal.equals("") 
							&& !idLocalidadeFinal.trim()
								.equalsIgnoreCase(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())))) {

			consulta = consulta	+ " localidade.loca_id >= :idLocalidadeInicial and localidade.loca_id <= :idLocalidadeFinal  and  ";

		}

		// setor comercial inicial e final
		if (((setorComercialInicial != null	&& !setorComercialInicial.equals("") 
				&& !setorComercialInicial.trim()
					.equalsIgnoreCase(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) 
					&& (setorComercialFinal != null	&& !setorComercialFinal.equals("") 
							&& !setorComercialFinal.trim()
							.equalsIgnoreCase(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())))) {

			consulta = consulta	+ " setor_comercial.stcm_cdsetorcomercial >= :setorComercialInicial and setor_comercial.stcm_cdsetorcomercial <= :setorComercialFinal  and  ";

		}

		// quadra
		if ((quadraInicial != null && !quadraInicial.equals("") 
				&& !quadraInicial.trim()
					.equalsIgnoreCase(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))
					&& (quadraFinal != null && !quadraFinal.equals("") 
							&& !quadraFinal.trim()
								.equalsIgnoreCase(
										new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

			consulta = consulta	+ " quadra.qdra_nnquadra >= :quadraInicial and quadra.qdra_nnquadra <= :quadraFinal and  ";

		}

		/*
		 * Alterado por Sávio Luiz Data: 14/09/2007 (Analista:Rosana) Inserir os
		 * parametros de código de rota e sequencial de rota
		 */

		// código de rota inicial e final
		if (((cdRotaInicial != null && !cdRotaInicial.equals("") 
				&& !cdRotaInicial.trim()
					.equalsIgnoreCase(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) 
					&& (cdRotaFinal != null	&& !cdRotaFinal.equals("") 
							&& !cdRotaFinal.trim()	
								.equalsIgnoreCase(
										new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())))) {

			consulta = consulta + " rota.rota_cdrota >= :cdRotaInicial and rota.rota_cdrota <= :cdRotaFinal  and  ";

		}

		// sequencial de rota inicial e final

		if (((sequencialRotaInicial != null	&& !sequencialRotaInicial.equals("") 
				&& !sequencialRotaInicial.trim()
					.equalsIgnoreCase(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) 
					&& (sequencialRotaFinal != null	&& !sequencialRotaFinal.equals("") 
							&& !sequencialRotaFinal.trim().equalsIgnoreCase(
									new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)	.toString())))) {

			consulta = consulta	+ " imovel.imov_nnsequencialrota >= :sequencialRotaInicial and imovel.imov_nnsequencialrota <= :sequencialRotaFinal  and  ";

		}

		// lote
		if ((loteOrigem != null && !loteOrigem.equals("") 
				&& !loteOrigem.trim().equalsIgnoreCase(	
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))
					&& (loteDestno != null && !loteDestno.equals("") 
							&& !loteDestno.trim().equalsIgnoreCase(
									new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

			consulta = consulta	+ " imovel.imov_nnlote >= :loteOrigem  and  imovel.imov_nnlote <= :loteDestino and ";

		}

		// cep
		if (cep != null	&& !cep.equals("") 
				&& !cep.trim().equalsIgnoreCase(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta + " cep.cep_cdcep = :cep  and  ";

		}

		// logradouro
		if (logradouro != null && !logradouro.equals("")
				&& !logradouro.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta + " logradouro.logr_id = :logradouro  and  ";

		}

		// bairro
		if (bairro != null	&& !bairro.equals("")
				&& !bairro.trim().equalsIgnoreCase(	
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta + " bairro.bair_cdbairro = :bairro  and  ";

		}

		// municipio
		if (municipio != null && !municipio.equals("")
				&& !municipio.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta + " municipio.muni_id = :municipio  and  ";

		}

		// consumo minimo agua inicial e final

		if ((consumoMinimoInicialAgua != null && !consumoMinimoInicialAgua.equals("") 
				&& !consumoMinimoInicialAgua.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))
					&& (consumoMinimoFinalAgua != null 	&& !consumoMinimoFinalAgua.equals("") 
							&& !consumoMinimoFinalAgua.trim().equalsIgnoreCase(
									new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

			consulta = consulta	+ " ligacao_agua.lagu_nnconsumominimoagua >= :consumoMinimoInicialAgua and ligacao_agua.lagu_nnconsumominimoagua <= :consumoMinimoFinalAgua  and  ";

		}

		// consumo minimo esgoto inicial e final

		if ((consumoMinimoInicialEsgoto != null && !consumoMinimoInicialEsgoto.equals("") 
				&& !consumoMinimoInicialEsgoto.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))
						&& (consumoMinimoFinalEsgoto != null && !consumoMinimoFinalEsgoto.equals("") 
								&& !consumoMinimoFinalEsgoto.trim().equalsIgnoreCase(
										new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

			consulta = consulta	+ " ligacao_esgoto.lesg_nnconsumominimoesgoto >= :consumoMinimoInicialEsgoto and ligacao_esgoto.lesg_nnconsumominimoesgoto <= :consumoMinimoFinalEsgoto  and  ";

		}

		// percentual esgoto inicial e final

		if ((intervaloValorPercentualEsgotoInicial != null && !intervaloValorPercentualEsgotoInicial.equals("") 
				&& !intervaloValorPercentualEsgotoInicial.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))
						&& (intervaloValorPercentualEsgotoFinal != null && !intervaloValorPercentualEsgotoFinal.equals("") 
								&& !intervaloValorPercentualEsgotoFinal.trim().equalsIgnoreCase(
										new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

			consulta = consulta	+ " ligacao_esgoto.lesg_pcesgoto >= :intervaloValorPercentualEsgotoInicial and ligacao_esgoto.lesg_pcesgoto <= :intervaloValorPercentualEsgotoFinal  and  ";

		}

		// indicador medição
		if (indicadorMedicao != null && indicadorMedicao.equals("comMedicao")) {
			// tipo medicao
			if (idTipoMedicao != null && idTipoMedicao.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {
				consulta = consulta + " hidrometro_inst_hist.hidi_id is not null  and  ";

			} else if (idTipoMedicao != null && idTipoMedicao.equals(MedicaoTipo.POCO.toString())) {

				consulta = consulta	+ " hidrometro_instalacao_historic.hidi_id is not null and  ";

			} else {

				consulta = consulta + " (hidrometro_inst_hist.hidi_id is not null or ";

				consulta = consulta + " hidrometro_instalacao_historic.hidi_id is not null) and  ";

			}

		} else if (indicadorMedicao != null && indicadorMedicao.equals("semMedicao")) {

			consulta = consulta + " (hidrometro_inst_hist.hidi_id is null and ";

			consulta = consulta + " hidrometro_instalacao_historic.hidi_id is null) and  ";

		}
		
		if (idTipoMedicao != null && !idTipoMedicao.equals("")&&
				!idTipoMedicao.equals(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

				consulta = consulta + " medicao_historico.medt_id = :idTipoMedicao and ";

		}

		// imovel condominio
		if (idImovelCondominio != null && !idImovelCondominio.equals("") 
				&& !idImovelCondominio.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta + " imovel.imov_idimovelcondominio = :idImovelCondominio  and  ";

		}

		// imovel principal
		if (idImovelPrincipal != null && !idImovelPrincipal.equals("")
				&& !idImovelPrincipal.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta	+ " imovel.imov_idimovelprincipal = :idImovelPrincipal  and  ";

		}

		// situação da ligação de agua
		if (idSituacaoLigacaoAgua != null && !idSituacaoLigacaoAgua.equals("")
				&& !idSituacaoLigacaoAgua.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta	+ " ligacao_agua_situacao.last_id = :idSituacaoLigacaoAgua  and  ";

		}

		// situação ligação de esgoto
		if (idSituacaoLigacaoEsgoto != null	&& !idSituacaoLigacaoEsgoto.equals("")
				&& !idSituacaoLigacaoEsgoto.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta	+ " ligacao_esgoto_situacao.lest_id = :idSituacaoLigacaoEsgoto  and  ";

		}

		// imovel Perfil
		if (idImovelPerfil != null && !idImovelPerfil.equals("") && !idImovelPerfil.trim().equalsIgnoreCase(
				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta + " imovel_perfil.iper_id = :idImovelPerfil  and  ";

		}

		// poço tipo
		if (idPocoTipo != null && !idPocoTipo.equals("")
				&& !idPocoTipo.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta + " imovel.poco_id = :idPocoTipo  and  ";

		}

		// faturamento situacao tipo
		if (idFaturamentoSituacaoTipo != null && !idFaturamentoSituacaoTipo.equals("")
				&& !idFaturamentoSituacaoTipo.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta + " fatur_situacao_tipo.ftst_id = :idFaturamentoSituacaoTipo  and  ";

		}

		// Situacao Especial Cobranca
		if (idSituacaoEspecialCobranca != null && !idSituacaoEspecialCobranca.equals("")
				&& !idSituacaoEspecialCobranca.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta	+ " cobranca_situacao_tipo.cbsp_id = :idSituacaoEspecialCobranca  and  ";

		}

		// cobranca situacao tipo
		if (idCobrancaSituacaoTipo != null && !idCobrancaSituacaoTipo.equals("")
				&& !idCobrancaSituacaoTipo.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta + " cobranca_situacao.cbst_id = :idCobrancaSituacaoTipo  and  ";

		}

		// elo anormalidade
		if (idEloAnormalidade != null && !idEloAnormalidade.equals("")
				&& !idEloAnormalidade.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta	+ " elo_anormalidade.eanm_id = :idEloAnormalidade  and  ";

		}

		// cadastro ocorrencia
		if (idCadastroOcorrencia != null && !idCadastroOcorrencia.equals("")
				&& !idCadastroOcorrencia.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta	+ " cadastro_ocorrencia.cocr_id = :idCadastroOcorrencia  and  ";

		}

		// consumo tarifa
		if (idConsumoTarifa != null	&& !idConsumoTarifa.equals("")
				&& !idConsumoTarifa.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta + " consumo_tarifa.cstf_id = :idConsumoTarifa   and  ";

		}

		// intervalo Media Minima Imovel Inicial e Final
		if ((intervaloMediaMinimaImovelInicial != null && !intervaloMediaMinimaImovelInicial.equals("") 
				&& !intervaloMediaMinimaImovelInicial.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))
				&& (intervaloMediaMinimaImovelFinal != null && !intervaloMediaMinimaImovelFinal.equals("") 
						&& !intervaloMediaMinimaImovelFinal.trim().equalsIgnoreCase(
								new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

			consulta = consulta	+ " consumo_historico.cshi_nnconsumomedio >= :intervaloMediaMinimaImovelInicial and consumo_historico.cshi_nnconsumomedio <= :intervaloMediaMinimaImovelFinal  and  ";

		}

		// intervalo MediaMinima Hidrometro Inicial e Final
		if ((intervaloMediaMinimaHidrometroInicial != null && !intervaloMediaMinimaHidrometroInicial.equals("") 
				&& !intervaloMediaMinimaHidrometroInicial.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))
						&& (intervaloMediaMinimaHidrometroFinal != null	
								&& !intervaloMediaMinimaHidrometroFinal.equals("") 
								&& !intervaloMediaMinimaHidrometroFinal.trim().equalsIgnoreCase(
										new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

			if (idTipoMedicao != null && !idTipoMedicao.equals("")) {
				if (idTipoMedicao.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {
					
					consulta = consulta	+ " medicao_historico.mdhi_nnconsumomediohidrometro >= :intervaloMediaMinimaHidrometroInicial and medicao_historico.mdhi_nnconsumomediohidrometro <= :intervaloMediaMinimaHidrometroFinal  and  ";

				} else if (idTipoMedicao.equals(MedicaoTipo.POCO.toString())) {

					consulta = consulta	+ " medicao_historico_poco.mdhi_nnconsumomediohidrometro >= :intervaloMediaMinimaHidrometroInicial and medicao_historico_poco.mdhi_nnconsumomediohidrometro <= :intervaloMediaMinimaHidrometroFinal  and  ";

				}

			}

		}

		// categoria
		if (idCategoria != null && !idCategoria.equals("")
				&& !idCategoria.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta + " categoria.catg_id = :idCategoria  and  ";

		}

		// sub categoria
		if (idSubCategoria != null && !idSubCategoria.equals("")
				&& !idSubCategoria.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			consulta = consulta + " subCategoria.scat_id = :idSubCategoria  and  ";

		}

		// Relatorio de Economias do Imovel

		if (relatorio != null
				&& relatorio.intValue() == ConstantesSistema.GERAR_RELATORIO_DADOS_ECONOMIA_IMOVEL.intValue()) {
			// numero prontos inicial e final
			if ((numeroPontosInicial != null && !numeroPontosInicial.equals("") 
					&& !numeroPontosInicial.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))
							&& (numeroPontosFinal != null && !numeroPontosFinal.equals("") 
									&& !numeroPontosFinal.trim().equalsIgnoreCase(
											new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

				consulta = consulta	+ " imovel_economia.imec_nnpontosutilizacao >= :numeroPontosInicial and imovel_economia.imec_nnpontosutilizacao <= :numeroPontosFinal and ";

			}

			// numero moradores inicial e final
			if ((numeroMoradoresInicial != null && !numeroMoradoresInicial.equals("") 
					&& !numeroMoradoresInicial.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))
							&& (numeroMoradoresFinal != null && !numeroMoradoresFinal.equals("") 
									&& !numeroMoradoresFinal.trim().equalsIgnoreCase(
											new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

				consulta = consulta	+ " imovel_economia.imec_nnmorador >= :numeroMoradoresInicial and imovel_economia.imec_nnmorador <= :numeroMoradoresFinal and ";

			}

			// area construida faixa

			if (idAreaConstruidaFaixa != null && !idAreaConstruidaFaixa.equals("") 
					&& !idAreaConstruidaFaixa.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

				consulta = consulta	+ " imovel_economia.acon_id = :idAreaConstruidaFaixa and ";

			}

			// area construida inicial e final

			if ((areaConstruidaInicial != null && !areaConstruidaInicial.equals("") 
					&& !areaConstruidaInicial.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))

			&& (areaConstruidaFinal != null	&& !areaConstruidaFinal.equals("") 
					&& !areaConstruidaFinal.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

				consulta = consulta	+ " imovel_economia.imec_nnareaconstruida >= :areaConstruidaInicial and imovel_economia.imec_nnareaconstruida <= :areaConstruidaFinal  and  ";

			}

		} else {

			// numero prontos inicial e final
			if ((numeroPontosInicial != null && !numeroPontosInicial.equals("") 
					&& !numeroPontosInicial.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))
							&& (numeroPontosFinal != null && !numeroPontosFinal.equals("") 
									&& !numeroPontosFinal.trim().equalsIgnoreCase(
											new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

				consulta = consulta	+ " imovel.imov_nnpontosutilizacao >= :numeroPontosInicial and imovel.imov_nnpontosutilizacao <= :numeroPontosFinal and ";

			}

			// numero moradores inicial e final
			if ((numeroMoradoresInicial != null && !numeroMoradoresInicial.equals("") 
					&& !numeroMoradoresInicial.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))
							&& (numeroMoradoresFinal != null && !numeroMoradoresFinal.equals("") 
									&& !numeroMoradoresFinal.trim().equalsIgnoreCase(
											new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

				consulta = consulta + " imovel.imov_nnmorador >= :numeroMoradoresInicial and imovel.imov_nnmorador <= :numeroMoradoresFinal and ";

			}

			// area construida faixa
			if (idAreaConstruidaFaixa != null && !idAreaConstruidaFaixa.equals("")
					&& !idAreaConstruidaFaixa.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

				consulta = consulta + " imovel.acon_id = :idAreaConstruidaFaixa and ";

			}

			// area construida inicial e final
			if ((areaConstruidaInicial != null && !areaConstruidaInicial.equals("") 
					&& !areaConstruidaInicial.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))
							&& (areaConstruidaFinal != null	&& !areaConstruidaFinal.equals("") 
									&& !areaConstruidaFinal.trim().equalsIgnoreCase(
											new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

				consulta = consulta + " imovel.imov_nnareaconstruida >= :areaConstruidaInicial and imovel.imov_nnareaconstruida <= :areaConstruidaFinal  and  ";

			}

		}

		// quantidade economias inicial e final

		if ((quantidadeEconomiasInicial != null && !quantidadeEconomiasInicial.equals("") 
				&& !quantidadeEconomiasInicial .trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))
						&& (quantidadeEconomiasFinal != null && !quantidadeEconomiasFinal.equals("") 
								&& !quantidadeEconomiasFinal.trim().equalsIgnoreCase(
										new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

			consulta = consulta	+ "imovel.imov_qteconomia >= :quantidadeEconomiasInicial and imovel.imov_qteconomia <= :quantidadeEconomiasFinal  and  ";

		}

		// dia Vencimento
		if (diaVencimento != null && !diaVencimento.equals("")
				&& !diaVencimento.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			if (diaVencimento.equals("1")) {// sim

				consulta = consulta	+ " imovel.imov_ddvencimento  is not null and ";

			}

		}
		
		// poço tipo lista
		if (pocoTipoListIds != null && ((
				(pocoTipoListIds[0].equals("-1") && pocoTipoListIds.length > 1)) || 
				( !pocoTipoListIds[0].equals("-1")))){

			consulta = consulta + " imovel.poco_id in (:pocoTipoListIds)  and  ";
			
		} 
		
		if(indicadorCpfCnpj!=null && indicadorCpfCnpj.equals("2")){
			consulta = consulta
			+ " cliente_usuario.clie_nncpf is null and cliente_usuario.clie_nncnpj is null and  ";
			
		}
		
		if(cpfCnpj!= null
				&& !cpfCnpj.equals("")){
			consulta = consulta
			+ " (cliente_usuario.clie_nncpf = :cpfCnpj or cliente_usuario.clie_nncnpj = :cpfCnpj) and  ";
			
			if (idClienteRelacaoTipo != null
					&& idClienteRelacaoTipo.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())){
				consulta = consulta
				+ " cliente_imovel_usuario.crtp_id = " + ClienteRelacaoTipo.USUARIO +" and  ";
			}
			
		}
		
		
		return consulta;

	}

	/**
	 * 
	 * 
	 * 
	 * Gerar Relatório de Imóvel Outros Critérios
	 * 
	 * 
	 * 
	 * @author Rafael Corrêa,Rafael Santos
	 * 
	 * @date 24/07/2006,01/08/2006
	 * 
	 * 
	 * 
	 */

	public Collection gerarRelatorioImovelOutrosCriterios(String idImovelCondominio, 
							String idImovelPrincipal,						
							String idSituacaoLigacaoAgua, 
							String consumoMinimoInicialAgua,						
							String consumoMinimoFinalAgua, 
							String idSituacaoLigacaoEsgoto,						
							String consumoMinimoInicialEsgoto, 
							String consumoMinimoFinalEsgoto,
							String intervaloValorPercentualEsgotoInicial,
							String intervaloValorPercentualEsgotoFinal,
							String intervaloMediaMinimaImovelInicial,
							String intervaloMediaMinimaImovelFinal,
							String intervaloMediaMinimaHidrometroInicial,
							String intervaloMediaMinimaHidrometroFinal, 
							String idImovelPerfil,
							String idPocoTipo, 
							String idFaturamentoSituacaoTipo,
							String idCobrancaSituacaoTipo, 
							String idSituacaoEspecialCobranca,
							String idEloAnormalidade, 
							String areaConstruidaInicial,
							String areaConstruidaFinal, 
							String idCadastroOcorrencia,
							String idConsumoTarifa, 
							String idGerenciaRegional,
							String idLocalidadeInicial, 
							String idLocalidadeFinal,
							String setorComercialInicial, 
							String setorComercialFinal,
							String quadraInicial, 
							String quadraFinal, 
							String loteOrigem,
							String loteDestno, 
							String cep, 
							String logradouro, 
							String bairro,
							String municipio, 
							String idTipoMedicao, 
							String indicadorMedicao,
							String idSubCategoria, 
							String idCategoria,
							String quantidadeEconomiasInicial, 
							String quantidadeEconomiasFinal,
							String diaVencimento, 
							String idCliente, 
							String idClienteTipo,
							String idClienteRelacaoTipo, 
							String numeroPontosInicial,
							String numeroPontosFinal, 
							String numeroMoradoresInicial,
							String numeroMoradoresFinal, 
							String idAreaConstruidaFaixa,
							String idUnidadeNegocio, 
							String cdRotaInicial, 
							String cdRotaFinal,
							String sequencialRotaInicial, 
							String sequencialRotaFinal)
							throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = consulta	+ "select distinct "
					+ "gerencia_regional.greg_id, "
					+ "gerencia_regional.greg_nmabreviado, "
					+ "localidade.loca_id, "
					+ "localidade.loca_nmlocalidade, "
					+ "imovel.imov_id, "
					+ "imovel.imov_qteconomia, "
					+ "setor_comercial.stcm_cdsetorcomercial, "
					+ "setor_comercial.stcm_nmsetorcomercial, "
					+ "quadra.qdra_nnquadra, "
					+ "imovel.imov_nnlote, "
					+ "imovel.imov_nnsublote, "
					+ "ligacao_agua_situacao.last_dsabreviado, "
					+ "ligacao_esgoto_situacao.lest_dsabreviado, "
					+ "ligacao_esgoto.lesg_pcesgoto, "
					+ "ligacao_esgoto.lesg_dtligacao, "
					+ "ligacao_agua.lagu_dtligacaoagua, "
					// Informações do Cliente Usuasrio e Resposanvel
					+ "cliente_usuario.clie_id as idClienteUsuario, "
					+ "cliente_usuario.clie_nmcliente as nomeClienteUsuario, "
					+ "cliente_responsavel.clie_id as idClienteResponsavel, "
					+ "cliente_responsavel.clie_nmcliente as nomeClienteResponsavel, "
					+ "logradouro.logr_nmlogradouro, "
					+ "logradouro_tipo.lgtp_dslogradourotipo, "
					+ "logradouro_titulo.lgtt_dslogradourotitulo, "
					+ "cep.cep_cdcep, "
					+ "endereco_referencia.edrf_dsenderecoreferencia, "
					+ "imovel.imov_dscomplementoendereco, "
					+ "imovel.imov_nnimovel, "
					+ "bairro.bair_nmbairro, "
					+ "municipio.muni_nmmunicipio, "
					+ "unidade_federacao.unfe_dsufsigla, "
					+ "imovel.imov_icimovelcondominio, "
					+ "imovel.imov_nnmorador, "
					+ "imovel.imov_idimovelcondominio, "
					+ "imovel.imov_idimovelprincipal, "
					+ "imovel.imov_nnpontosutilizacao, "
					+ "imovel_perfil.iper_dsimovelperfil, "
					+ "area_construida_faixa.acon_nnmaiorfaixa, "
					+ "area_construida_faixa.acon_nnmenorfaixa, "
					+ "imovel.imov_nnareaconstruida, "
					+ "pavimento_calcada.pcal_dspavimentocalcada, "
					+ "pavimento_rua.prua_dspavimentorua, "
					+ "despejo.depj_dsdespejo, "
					+ "reservatorio_volume_fx.resv_vomenorfaixa, "
					+ "reservatorio_volume_fx.resv_vomaiorfaixa, "
					+ "imovel.imov_voreservatoriosuperior, "
					+ "reservatorio_volume_fx_infe.resv_vomenorfaixa, "
					+ "reservatorio_volume_fx_infe.resv_vomaiorfaixa, "
					+ "imovel.imov_voreservatorioinferior, "
					+ "piscina_volume_faixa.pisc_vomenorfaixa, "
					+ "piscina_volume_faixa.pisc_vomaiorfaixa, "
					+ "imovel.imov_vopiscina, "
					+ "poco_tipo.poco_dspocotipo, "
					+ "ligacao_agua_diametro.lagd_dsligacaoaguadiametro as descricaoligacaoaguadiametroag, "
					+ "ligacao_agua_material.lagm_dsligacaoaguamaterial as descricaoligacaoaguamaterialag, "
					+ "ligacao_esgoto_diametro.legd_dsligacaoesgotodiametro as descricaoligacaoesgotodiametro, "
					+ "ligacao_esgoto_material.legm_dsligacaoesgotomaterial as descricaoligacaoesgotomaterial, "
					+ "ligacao_esgoto.lesg_nnconsumominimoesgoto, "
					+ "ligacao_esgoto.lesg_pccoleta, "
					+ "ligacao_esgoto.lesg_pcesgoto, "
					// Agua
					+ "hidrometro.hidr_nnhidrometro as idHidrometroAgua, "
					+ "hidrometro.hidr_nnanofabricacao as anoFabricancaoHidrometroAgua, "
					+ "hidrometro_capacidade.hicp_dshidrometrocapacidade as descricaohidrometrocapacidadea, "
					+ "hidrometro_marca.himc_dshidrometromarca as descricaoHidrometroMarcaAgua, "
					+ "hidrometro_diametro.hidm_dshidrometrodiametro as descricaohidrometrodiametroagu, "
					+ "hidrometro_tipo.hitp_dshidrometrotipo as descricaoHidrometroTipoAgua, "
					+ "hidrometro_inst_hist.hidi_dtinstalacaohidrometro as datahidrometroinstalacaohisto2, "
					+ "hidrometro_local_inst.hili_dshidmtlocalinstalacao as descricaohidrometrolocalinstal, "
					+ "hidrometro_protecao.hipr_dshidrometroprotecao as descricaohidrometroprotecaoagu, "
					+ "hidrometro_inst_hist.hidi_iccavalete as indicadorhidrometroinstalacaoh, "
					// Esgoto
					+ "hidrometro_esgoto.hidr_nnhidrometro as idHidrometroEsgoto, "
					+ "hidrometro_esgoto.hidr_nnanofabricacao as anoFabricacaoHidrometroEsgoto, "
					+ "hidrometro_capacidade_esgoto.hicp_dshidrometrocapacidade as descricaohidrometrocapacidadee, "
					+ "hidrometro_marca_esgoto.himc_dshidrometromarca as descricaoHidrometroMarcaEsgoto, "
					+ "hidrometro_diametro_esgoto.hidm_dshidrometrodiametro as descricaohidrometrodiametroesg, "
					+ "hidrometro_tipo_esgoto.hitp_dshidrometrotipo as descricaoHidrometroTipoEsgoto, "
					+ "hidrometro_instalacao_historic.hidi_dtinstalacaohidrometro as datahidrometroinstalacaohistor, "
					+ "hidrometro_local_inst_es.hili_dshidmtlocalinstalacao as descricaohidtrometrolocalinsta, "
					+ "hidrometro_protecao_esgoto.hipr_dshidrometroprotecao as descricaohidrometroprotecaoesg, "
					+ "hidrometro_instalacao_historic.hidi_iccavalete as indicadorhidrometroinstalacaoe, "
					// Ligacao Agua
					+ "ligacao_agua.lagu_nnconsumominimoagua, "
					// Ligacao Esgoto
					+ "ligacao_esgoto.lesg_nnconsumominimoesgoto, "
					// Jardim
					+ "imovel.imov_icjardim, "
					// Rota
					+ "rota.rota_cdrota, "
					// Sequencial Rota
					+ "imovel.imov_nnsequencialrota, "
					// Logradouro
					+ "logradouro.logr_id "
					//Consumo Medio
//					+ "consumo_historico.cshi_nnconsumomedio as consumoMedio "
					// From
					+ "from cadastro.imovel_subcategoria imovelSubcategoria "
					+ "inner join cadastro.imovel on imovelSubcategoria.imov_id = cadastro.imovel.imov_id "
					+ "inner join cadastro.localidade on imovel.loca_id = cadastro.localidade.loca_id "
					+ "inner join cadastro.gerencia_regional on cadastro.localidade.greg_id = cadastro.gerencia_regional.greg_id "
					+ "inner join cadastro.setor_comercial on cadastro.imovel.stcm_id = cadastro.setor_comercial.stcm_id "
					// Logradouro Bairro
					+ "left join cadastro.logradouro_bairro on cadastro.imovel.lgbr_id = cadastro.logradouro_bairro.lgbr_id "
					+ "left join cadastro.bairro on cadastro.logradouro_bairro.bair_id = cadastro.bairro.bair_id "
					+ "left join cadastro.municipio on cadastro.bairro.muni_id = cadastro.municipio.muni_id "
					+ "inner join cadastro.quadra on cadastro.imovel.qdra_id = cadastro.quadra.qdra_id "
					+ "inner join micromedicao.rota on cadastro.quadra.rota_id = micromedicao.rota.rota_id "
					// Logradouro Cep
					+ "left join cadastro.logradouro_cep on cadastro.imovel.lgcp_id = cadastro.logradouro_cep.lgcp_id "
					+ "left join cadastro.cep on cadastro.logradouro_cep.cep_id = cadastro.cep.cep_id "
					+ "left join cadastro.logradouro on cadastro.logradouro_cep.logr_id = cadastro.logradouro.logr_id "
					// AGUA
					+ "left join atendimentopublico.ligacao_agua_situacao on cadastro.imovel.last_id = atendimentopublico.ligacao_agua_situacao.last_id "
					+ "left join atendimentopublico.ligacao_agua on cadastro.imovel.imov_id = atendimentopublico.ligacao_agua.lagu_id "
					// ESGOTO
					+ "left join atendimentopublico.ligacao_esgoto_situacao on cadastro.imovel.lest_id = atendimentopublico.ligacao_esgoto_situacao.lest_id "
					+ "left join atendimentopublico.ligacao_esgoto on cadastro.imovel.imov_id = atendimentopublico.ligacao_esgoto.lesg_id "
					+ "left join cadastro.imovel_perfil on cadastro.imovel.iper_id = cadastro.imovel_perfil.iper_id "
					+ "left join cadastro.poco_tipo on cadastro.imovel.poco_id = cadastro.poco_tipo.poco_id "
					+ "left join cadastro.area_construida_faixa on cadastro.imovel.acon_id = area_construida_faixa.acon_id "
					// Cliente Usuario
					+ "left outer join cadastro.cliente_imovel as cliente_imovel_usuario on cadastro.imovel.imov_id = cliente_imovel_usuario.imov_id  "
					+ " and (cliente_imovel_usuario.crtp_id=2 and cliente_imovel_usuario.clim_dtrelacaofim is null) "
					+ "left outer join cadastro.cliente as cliente_usuario on cliente_imovel_usuario.clie_id = cliente_usuario.clie_id "
					// Cliente Resposanvel
					+ "left outer join cadastro.cliente_imovel as cliente_imovel_responsavel on cadastro.imovel.imov_id = cliente_imovel_responsavel.imov_id "
					+ " and (cliente_imovel_responsavel.crtp_id=3 and cliente_imovel_responsavel.clim_dtrelacaofim is null) "
					+ "left outer join cadastro.cliente as cliente_responsavel on cliente_imovel_responsavel.clie_id = cliente_responsavel.clie_id "
					// AGUA
					+ "left join micromedicao.hidrometro_inst_hist on atendimentopublico.ligacao_agua.hidi_id =  micromedicao.hidrometro_inst_hist.hidi_id "
					// ESGOTO
					+"left join micromedicao.hidrometro_inst_hist hidrometro_instalacao_historic on cadastro.imovel.hidi_id =  hidrometro_instalacao_historic.hidi_id "
					// Relacionamento para o Relatorio de Imovel
					+ "left join cadastro.logradouro_titulo on cadastro.logradouro.lgtt_id = cadastro.logradouro_titulo.lgtt_id "
					+ "left join cadastro.logradouro_tipo on cadastro.logradouro.lgtp_id = cadastro.logradouro_tipo.lgtp_id "
					+ "left join cadastro.endereco_referencia on cadastro.imovel.edrf_id = cadastro.endereco_referencia.edrf_id "
					+ "left join cadastro.unidade_federacao on cadastro.municipio.unfe_id = cadastro.unidade_federacao.unfe_id "
					+ "left join cadastro.reservatorio_volume_fx on cadastro.imovel.resv_idreservatoriosuperior = cadastro.reservatorio_volume_fx.resv_id "
					+ "left join cadastro.reservatorio_volume_fx reservatorio_volume_fx_infe on cadastro.imovel.resv_idreservatorioinferior = reservatorio_volume_fx_infe.resv_id "
					+ "left join cadastro.piscina_volume_faixa on cadastro.imovel.pisc_id = cadastro.piscina_volume_faixa.pisc_id "
					+ "left join cadastro.pavimento_calcada on cadastro.imovel.pcal_id = cadastro.pavimento_calcada.pcal_id "
					+ "left join cadastro.pavimento_rua on cadastro.imovel.prua_id = cadastro.pavimento_rua.prua_id "
					+ "left join cadastro.despejo on cadastro.imovel.depj_id = cadastro.despejo.depj_id "
					// AGUA
					+ "left join atendimentopublico.ligacao_agua_diametro on atendimentopublico.ligacao_agua.lagd_id = atendimentopublico.ligacao_agua_diametro.lagd_id "
					+ "left join atendimentopublico.ligacao_agua_material on atendimentopublico.ligacao_agua.lagm_id = atendimentopublico.ligacao_agua_material.lagm_id "
					// ESGOTO
					+ "left join atendimentopublico.ligacao_esgoto_diametro on atendimentopublico.ligacao_esgoto.legd_id = atendimentopublico.ligacao_esgoto_diametro.legd_id "
					+ "left join atendimentopublico.ligacao_esgoto_material on atendimentopublico.ligacao_esgoto.legm_id = atendimentopublico.ligacao_esgoto_material.legm_id "
					// AGUA
					+ "left join micromedicao.hidrometro on micromedicao.hidrometro_inst_hist.hidr_id = micromedicao.hidrometro.hidr_id "
					+ "left join micromedicao.hidrometro_capacidade on micromedicao.hidrometro.hicp_id = micromedicao.hidrometro_capacidade.hicp_id "
					+ "left join micromedicao.hidrometro_marca on micromedicao.hidrometro.himc_id = micromedicao.hidrometro_marca.himc_id "
					+ "left join micromedicao.hidrometro_diametro on micromedicao.hidrometro.hidm_id = micromedicao.hidrometro_diametro.hidm_id "
					+ "left join micromedicao.hidrometro_tipo on micromedicao.hidrometro.hitp_id = micromedicao.hidrometro_tipo.hitp_id "
					+ "left join micromedicao.hidrometro_local_inst on micromedicao.hidrometro_inst_hist.hili_id = micromedicao.hidrometro_local_inst.hili_id "
					+ "left join micromedicao.hidrometro_protecao on micromedicao.hidrometro_inst_hist.hipr_id = micromedicao.hidrometro_protecao.hipr_id "
					// ESGOTO
					+ "left join micromedicao.hidrometro as hidrometro_esgoto on hidrometro_instalacao_historic.hidr_id = hidrometro_esgoto.hidr_id "
					+ "left join micromedicao.hidrometro_capacidade as hidrometro_capacidade_esgoto on hidrometro_esgoto.hicp_id = hidrometro_capacidade_esgoto.hicp_id "
					+ "left join micromedicao.hidrometro_marca as hidrometro_marca_esgoto on hidrometro_esgoto.himc_id = hidrometro_marca_esgoto.himc_id "
					+ "left join micromedicao.hidrometro_diametro as hidrometro_diametro_esgoto on hidrometro_esgoto.hidm_id = hidrometro_diametro_esgoto.hidm_id "
					+ "left join micromedicao.hidrometro_tipo as hidrometro_tipo_esgoto on hidrometro_esgoto.hitp_id = hidrometro_tipo_esgoto.hitp_id "
					+ "left join micromedicao.hidrometro_local_inst as hidrometro_local_inst_es on hidrometro_instalacao_historic.hili_id = hidrometro_local_inst.hili_id "
					+ "left join micromedicao.hidrometro_protecao as hidrometro_protecao_esgoto on hidrometro_instalacao_historic.hipr_id = hidrometro_protecao_esgoto.hipr_id ";

			consulta = consulta + montarInnerJoinFiltrarImoveisOutrosCriterios(
									intervaloMediaMinimaImovelInicial,
									intervaloMediaMinimaImovelFinal,
									intervaloMediaMinimaHidrometroInicial,
									intervaloMediaMinimaHidrometroFinal,
									idFaturamentoSituacaoTipo, idCobrancaSituacaoTipo,
									idSituacaoEspecialCobranca, idEloAnormalidade,
									idCadastroOcorrencia, idConsumoTarifa,
									idTipoMedicao, indicadorMedicao, idSubCategoria,
									idCategoria, idCliente, idClienteTipo,
									idClienteRelacaoTipo,
									ConstantesSistema.GERAR_RELATORIO_IMOVEL, 
									null
									);

			consulta = consulta + montarCondicaoSQLWhereFiltrarImovelOutrosCriterio(
									idImovelCondominio, 
									idImovelPrincipal,
									idSituacaoLigacaoAgua, 
									consumoMinimoInicialAgua,
									consumoMinimoFinalAgua, 
									idSituacaoLigacaoEsgoto,
									consumoMinimoInicialEsgoto,
									consumoMinimoFinalEsgoto,
									intervaloValorPercentualEsgotoInicial,
									intervaloValorPercentualEsgotoFinal,
									intervaloMediaMinimaImovelInicial,
									intervaloMediaMinimaImovelFinal,
									intervaloMediaMinimaHidrometroInicial,
									intervaloMediaMinimaHidrometroFinal,
									idImovelPerfil, 
									idPocoTipo,
									idFaturamentoSituacaoTipo, 
									idCobrancaSituacaoTipo,
									idSituacaoEspecialCobranca, 
									idEloAnormalidade,
									areaConstruidaInicial, 
									areaConstruidaFinal,
									idCadastroOcorrencia, 
									idConsumoTarifa,
									idGerenciaRegional, 
									idLocalidadeInicial,
									idLocalidadeFinal, 
									setorComercialInicial,
									setorComercialFinal, 
									quadraInicial, 
									quadraFinal,
									loteOrigem, 
									loteDestno, 
									cep, 
									logradouro,
									bairro,
									municipio, 
									idTipoMedicao, 
									indicadorMedicao,
									idSubCategoria, 
									idCategoria,
									quantidadeEconomiasInicial,
									quantidadeEconomiasFinal, 
									diaVencimento, 
									idCliente,
									idClienteTipo, 
									idClienteRelacaoTipo,
									numeroPontosInicial, 
									numeroPontosFinal,
									numeroMoradoresInicial, 
									numeroMoradoresFinal,
									idAreaConstruidaFaixa, 
									idUnidadeNegocio,
									ConstantesSistema.GERAR_RELATORIO_IMOVEL, 
									cdRotaInicial,
									cdRotaFinal, 
									sequencialRotaInicial,
									sequencialRotaFinal,
									null, null , null);

			consulta  = consulta.substring(0,(consulta.length() - 5));
			
//			consulta = consulta + " order by loca_id, stcm_cdsetorcomercial, qdra_nnquadra, imov_nnlote, imov_nnsublote ";
			
			SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
			
			//RM 330
			if (sistemaParametro.getNomeAbreviadoEmpresa() != null
					&& sistemaParametro.getNomeAbreviadoEmpresa().equals(
					"COMPESA")) {
					consulta = consulta
					+ " ORDER BY loca_id, stcm_cdsetorcomercial, qdra_nnquadra, imov_nnlote, imov_nnsublote ";

			} else {
					consulta = consulta
					+ " ORDER BY imov_nnsequencialrota, imov_nnlote, imov_nnsublote ";
			}

			
			SQLQuery query = session.createSQLQuery(consulta);

			informarDadosQueryFiltrarImovelOutrosCriterio(query,
									idImovelCondominio, 
									idImovelPrincipal,
									idSituacaoLigacaoAgua, 
									consumoMinimoInicialAgua,
									consumoMinimoFinalAgua, 
									idSituacaoLigacaoEsgoto,
									consumoMinimoInicialEsgoto, 
									consumoMinimoFinalEsgoto,
									intervaloValorPercentualEsgotoInicial,
									intervaloValorPercentualEsgotoFinal,
									intervaloMediaMinimaImovelInicial,
									intervaloMediaMinimaImovelFinal,
									intervaloMediaMinimaHidrometroInicial,
									intervaloMediaMinimaHidrometroFinal, 
									idImovelPerfil,
									idPocoTipo, 
									idFaturamentoSituacaoTipo,
									idCobrancaSituacaoTipo, 
									idSituacaoEspecialCobranca,
									idEloAnormalidade, 
									areaConstruidaInicial,
									areaConstruidaFinal, 
									idCadastroOcorrencia, 
									idConsumoTarifa,
									idGerenciaRegional, 
									idLocalidadeInicial, 
									idLocalidadeFinal,
									setorComercialInicial, 
									setorComercialFinal, 
									quadraInicial,
									quadraFinal, 
									loteOrigem, 
									loteDestno, 
									cep, 
									logradouro,
									bairro, 
									municipio, 
									idTipoMedicao, 
									indicadorMedicao,
									idSubCategoria, 
									idCategoria, 
									quantidadeEconomiasInicial,
									quantidadeEconomiasFinal, 
									diaVencimento, 
									idCliente,
									idClienteTipo, 
									idClienteRelacaoTipo, 
									numeroPontosInicial,
									numeroPontosFinal, 
									numeroMoradoresInicial,
									numeroMoradoresFinal, 
									idAreaConstruidaFaixa,
									idUnidadeNegocio, 
									cdRotaInicial, 
									cdRotaFinal,
									sequencialRotaInicial, 
									sequencialRotaFinal,
									null, null);

			retorno = query.addScalar("greg_id", Hibernate.INTEGER)
					.addScalar("greg_nmabreviado", Hibernate.STRING)
					.addScalar("loca_id", Hibernate.INTEGER)
					.addScalar("loca_nmlocalidade", Hibernate.STRING)
					.addScalar("imov_id", Hibernate.INTEGER)
					.addScalar("imov_qteconomia", Hibernate.SHORT)
					.addScalar("stcm_cdsetorcomercial", Hibernate.INTEGER)
					.addScalar("stcm_nmsetorcomercial", Hibernate.STRING)
					.addScalar("qdra_nnquadra", Hibernate.INTEGER)
					.addScalar("imov_nnlote", Hibernate.SHORT)
					.addScalar("imov_nnsublote", Hibernate.SHORT)
					.addScalar("last_dsabreviado", Hibernate.STRING)
					.addScalar("lest_dsabreviado", Hibernate.STRING)
					.addScalar("lesg_pcesgoto", Hibernate.BIG_DECIMAL)
					.addScalar("lesg_dtligacao", Hibernate.DATE)
					.addScalar("lagu_dtligacaoagua", Hibernate.DATE)
					.addScalar("idClienteUsuario", Hibernate.INTEGER)
					.addScalar("nomeClienteUsuario", Hibernate.STRING)
					.addScalar("idClienteResponsavel", Hibernate.INTEGER)
					.addScalar("nomeClienteResponsavel", Hibernate.STRING)
					.addScalar("logr_nmlogradouro", Hibernate.STRING)
					.addScalar("lgtp_dslogradourotipo", Hibernate.STRING)
					.addScalar("lgtt_dslogradourotitulo", Hibernate.STRING)
					.addScalar("cep_cdcep", Hibernate.INTEGER)
					.addScalar("edrf_dsenderecoreferencia", Hibernate.STRING)
					.addScalar("imov_dscomplementoendereco", Hibernate.STRING)
					.addScalar("imov_nnimovel", Hibernate.STRING)
					.addScalar("bair_nmbairro", Hibernate.STRING)
					.addScalar("muni_nmmunicipio", Hibernate.STRING)
					.addScalar("unfe_dsufsigla", Hibernate.STRING)
					.addScalar("imov_icimovelcondominio", Hibernate.INTEGER)
					.addScalar("imov_nnmorador", Hibernate.INTEGER)
					.addScalar("imov_idimovelcondominio", Hibernate.INTEGER)
					.addScalar("imov_idimovelprincipal", Hibernate.INTEGER)
					.addScalar("imov_nnpontosutilizacao", Hibernate.INTEGER)
					.addScalar("iper_dsimovelperfil", Hibernate.STRING)
					.addScalar("acon_nnmaiorfaixa", Hibernate.INTEGER)
					.addScalar("acon_nnmenorfaixa", Hibernate.INTEGER)
					.addScalar("imov_nnareaconstruida", Hibernate.BIG_DECIMAL)
					.addScalar("pcal_dspavimentocalcada", Hibernate.STRING)
					.addScalar("prua_dspavimentorua", Hibernate.STRING)
					.addScalar("depj_dsdespejo", Hibernate.STRING)
					.addScalar("resv_vomenorfaixa", Hibernate.BIG_DECIMAL)
					.addScalar("resv_vomaiorfaixa", Hibernate.BIG_DECIMAL)
					.addScalar("imov_voreservatoriosuperior",Hibernate.BIG_DECIMAL)
					.addScalar("resv_vomenorfaixa", Hibernate.BIG_DECIMAL)
					.addScalar("resv_vomaiorfaixa", Hibernate.BIG_DECIMAL)
					.addScalar("imov_voreservatorioinferior",Hibernate.BIG_DECIMAL)
					.addScalar("pisc_vomenorfaixa", Hibernate.BIG_DECIMAL)
					.addScalar("pisc_vomaiorfaixa", Hibernate.BIG_DECIMAL)
					.addScalar("imov_vopiscina", Hibernate.BIG_DECIMAL)
					.addScalar("poco_dspocotipo", Hibernate.STRING)
					.addScalar("descricaoligacaoaguadiametroag",	Hibernate.STRING)
					.addScalar("descricaoligacaoaguamaterialag",	Hibernate.STRING)
					.addScalar("descricaoligacaoesgotodiametro",	Hibernate.STRING)
					.addScalar("descricaoligacaoesgotomaterial",	Hibernate.STRING)
					.addScalar("lesg_nnconsumominimoesgoto", Hibernate.INTEGER)
					.addScalar("lesg_pccoleta", Hibernate.BIG_DECIMAL)
					.addScalar("lesg_pcesgoto", Hibernate.BIG_DECIMAL)
					.addScalar("idHidrometroAgua", Hibernate.STRING)
					.addScalar("anoFabricancaoHidrometroAgua", Hibernate.INTEGER)
					.addScalar("descricaohidrometrocapacidadea",	Hibernate.STRING)
					.addScalar("descricaoHidrometroMarcaAgua", Hibernate.STRING)
					.addScalar("descricaohidrometrodiametroagu",Hibernate.STRING)
					.addScalar("descricaoHidrometroTipoAgua", Hibernate.STRING)
					.addScalar("datahidrometroinstalacaohisto2",	Hibernate.DATE)
					.addScalar("descricaohidrometrolocalinstal",Hibernate.STRING)
					.addScalar("descricaohidrometroprotecaoagu",	Hibernate.STRING)
					.addScalar("indicadorhidrometroinstalacaoh",Hibernate.INTEGER)
					.addScalar("idHidrometroEsgoto", Hibernate.STRING)
					.addScalar("anoFabricacaoHidrometroEsgoto",	Hibernate.INTEGER)
					.addScalar("descricaohidrometrocapacidadee",	Hibernate.STRING)
					.addScalar("descricaoHidrometroMarcaEsgoto", Hibernate.STRING)
					.addScalar("descricaohidrometrodiametroesg",	Hibernate.STRING)
					.addScalar("descricaoHidrometroTipoEsgoto", Hibernate.STRING)
					.addScalar("datahidrometroinstalacaohistor",Hibernate.DATE)
					.addScalar("descricaohidtrometrolocalinsta",Hibernate.STRING)
					.addScalar("descricaohidrometroprotecaoesg",Hibernate.STRING)
					.addScalar("indicadorhidrometroinstalacaoe",Hibernate.INTEGER)
					.addScalar("lagu_nnconsumominimoagua", Hibernate.INTEGER)
					.addScalar("lesg_nnconsumominimoesgoto", Hibernate.INTEGER)
					.addScalar("imov_icjardim", Hibernate.SHORT)
					.addScalar("rota_cdrota", Hibernate.SHORT)
					.addScalar("imov_nnsequencialrota", Hibernate.INTEGER)
					.addScalar("logr_id", Hibernate.INTEGER)
//					.addScalar("consumoMedio", Hibernate.INTEGER)
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
	 * O método abaixo realiza uma pesquisa em imovel e retorna os campos
	 * 
	 * necessários para a criação da inscrição para exibição.
	 * 
	 * 
	 * 
	 * acima no controlador será montada a inscrição
	 * 
	 */

	public Object[] pesquisarInscricaoImovel(Integer idImovel)

	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Object[] retorno = null;

		try {

			String sql = "select loc.loca_id as idLocalidade, "

			+ " sc.stcm_cdsetorcomercial as codigoSetorComercial, "

			+ "	qd.qdra_nnquadra as numeroQuadra, "

			+ "	im.imov_nnlote as lote, "

			+ "	im.imov_nnsublote as subLote "

			+ " from cadastro.imovel im, "

			+ "	cadastro.localidade loc, "

			+ "	cadastro.setor_comercial sc, " + "	cadastro.quadra qd "

			+ " where loc.loca_id = im.loca_id and "

			+ "	im.stcm_id = sc.stcm_id and "

			+ "	im.qdra_id = qd.qdra_id and " + "	im.imov_id = "

			+ idImovel + "	and im.imov_icexclusao = "

			+ ConstantesSistema.INDICADOR_IMOVEL_ATIVO;

			Collection colecaoConsulta = session.createSQLQuery(sql).addScalar(

			"idLocalidade", Hibernate.INTEGER).addScalar(

			"codigoSetorComercial", Hibernate.INTEGER).addScalar(

			"numeroQuadra", Hibernate.INTEGER).addScalar("lote",

			Hibernate.SHORT).addScalar("subLote", Hibernate.SHORT)

			.list();

			retorno = (Object[]) Util.retonarObjetoDeColecao(colecaoConsulta);

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
	 * O método abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necessários para a criação da inscrição para exibição, independente do
	 * imóvel ter sido excluído ou não.
	 * 
	 * acima no controlador será montada a inscrição
	 */
	public Object[] pesquisarInscricaoImovelExcluidoOuNao(Integer idImovel)
		throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Object[] retorno = null;

		try {

			String sql = "select loc.loca_id as idLocalidade, "
				+ " sc.stcm_cdsetorcomercial as codigoSetorComercial, "
				+ "	qd.qdra_nnquadra as numeroQuadra, "
				+ "	im.imov_nnlote as lote, "
				+ "	im.imov_nnsublote as subLote "
				+ " from cadastro.imovel im, "
				+ "	cadastro.localidade loc, "
				+ "	cadastro.setor_comercial sc, " + "	cadastro.quadra qd "
				+ " where loc.loca_id = im.loca_id and "
				+ "	im.stcm_id = sc.stcm_id and "
				+ "	im.qdra_id = qd.qdra_id and " + "	im.imov_id = "
				+ idImovel;

			Collection colecaoConsulta = session.createSQLQuery(sql).addScalar(
			"idLocalidade", Hibernate.INTEGER).addScalar(
			"codigoSetorComercial", Hibernate.INTEGER).addScalar(
			"numeroQuadra", Hibernate.INTEGER).addScalar("lote",
			Hibernate.SHORT).addScalar("subLote", Hibernate.SHORT)
			.list();

			retorno = (Object[]) Util.retonarObjetoDeColecao(colecaoConsulta);

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
	 * O método abaixo realiza uma pesquisa em imovel e retorna os campos
	 * 
	 * necessários para a criação da inscrição para exibição.
	 * 
	 * 
	 * 
	 * acima no controlador será montada a inscrição
	 * 
	 */

	public Object[] pesquisarLocalidadeSetorImovel(Integer idImovel)

	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Object[] retorno = null;

		try {

			String sql = "select loc.loca_nmlocalidade as descricaoLocalidade, "

					+ " sc.stcm_cdsetorcomercial as codigoSetorComercial "

					+ " from cadastro.imovel im, "

					+ "	cadastro.localidade loc, "

					+ "	cadastro.setor_comercial sc "

					+ " where loc.loca_id = im.loca_id and "

					+ "	im.stcm_id = sc.stcm_id and "

					+ "	im.imov_id = "

					+ idImovel + "	and im.imov_icexclusao = "

					+ ConstantesSistema.INDICADOR_IMOVEL_ATIVO;

			Collection colecaoConsulta = session.createSQLQuery(sql).addScalar(

			"descricaoLocalidade", Hibernate.STRING).addScalar(

			"codigoSetorComercial", Hibernate.INTEGER)

			.list();

			retorno = (Object[]) Util.retonarObjetoDeColecao(colecaoConsulta);

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
	 * 
	 * 
	 * Gerar Relatório de Dados de Economias do Imóvel
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 02/08/2006
	 * 
	 * 
	 * 
	 */

	public Collection gerarRelatorioDadosEconomiasImovelOutrosCriterios(

	String idImovelCondominio, String idImovelPrincipal,

	String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,

	String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,

	String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,

	String intervaloValorPercentualEsgotoInicial,

	String intervaloValorPercentualEsgotoFinal,

	String intervaloMediaMinimaImovelInicial,

	String intervaloMediaMinimaImovelFinal,

	String intervaloMediaMinimaHidrometroInicial,

	String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,

	String idPocoTipo, String idFaturamentoSituacaoTipo,

	String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,

	String idEloAnormalidade, String areaConstruidaInicial,

	String areaConstruidaFinal, String idCadastroOcorrencia,

	String idConsumoTarifa, String idGerenciaRegional,

	String idLocalidadeInicial, String idLocalidadeFinal,

	String setorComercialInicial, String setorComercialFinal,

	String quadraInicial, String quadraFinal, String loteOrigem,

	String loteDestno, String cep, String logradouro, String bairro,

	String municipio, String idTipoMedicao, String indicadorMedicao,

	String idSubCategoria, String idCategoria,

	String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,

	String diaVencimento, String idCliente, String idClienteTipo,

	String idClienteRelacaoTipo, String numeroPontosInicial,

	String numeroPontosFinal, String numeroMoradoresInicial,

	String numeroMoradoresFinal, String idAreaConstruidaFaixa,

	String idUnidadeNegocio) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = consulta

					+ "select distinct "

					+

					" imovel.imov_id,"

					+ " gerencia_regional.greg_id, "

					+ " gerencia_regional.greg_nmabreviado, "

					+ " localidade.loca_id ,"

					+ " localidade.loca_nmlocalidade, "

					+ " setor_comercial.stcm_cdsetorcomercial, "

					+ " setor_comercial.stcm_nmsetorcomercial "

					+

					// From

					"from cadastro.imovel_subcategoria imovelSubcategoria "

					+

					"inner join cadastro.imovel on imovelSubcategoria.imov_id = cadastro.imovel.imov_id "

					+ "inner join cadastro.localidade on imovel.loca_id = cadastro.localidade.loca_id "

					+ "inner join cadastro.gerencia_regional on cadastro.localidade.greg_id = cadastro.gerencia_regional.greg_id "

					+ "inner join cadastro.setor_comercial on cadastro.imovel.stcm_id = cadastro.setor_comercial.stcm_id "

					+

					// Logradouro Bairro

					"left join cadastro.logradouro_bairro on cadastro.imovel.lgbr_id = cadastro.logradouro_bairro.lgbr_id "

					+ "left join cadastro.bairro on cadastro.logradouro_bairro.bair_id = cadastro.bairro.bair_id "

					+ "left join cadastro.municipio on cadastro.bairro.muni_id = cadastro.municipio.muni_id "

					+ "inner join cadastro.quadra on cadastro.imovel.qdra_id = cadastro.quadra.qdra_id "

					+

					// Logradouro Cep

					"left join cadastro.logradouro_cep on cadastro.imovel.lgcp_id = cadastro.logradouro_cep.lgcp_id "

					+ "left join cadastro.cep on cadastro.logradouro_cep.cep_id = cadastro.cep.cep_id "

					+ "left join cadastro.logradouro on cadastro.logradouro_cep.logr_id = cadastro.logradouro.logr_id "

					+

					// AGUA

					"left join atendimentopublico.ligacao_agua_situacao on cadastro.imovel.last_id = atendimentopublico.ligacao_agua_situacao.last_id "

					+ "left join atendimentopublico.ligacao_agua on cadastro.imovel.imov_id = atendimentopublico.ligacao_agua.lagu_id "

					+

					// ESGOTO

					"left join atendimentopublico.ligacao_esgoto_situacao on cadastro.imovel.lest_id = atendimentopublico.ligacao_esgoto_situacao.lest_id "

					+ "left join atendimentopublico.ligacao_esgoto on cadastro.imovel.imov_id = atendimentopublico.ligacao_esgoto.lesg_id "

					+

					"left join cadastro.imovel_perfil on cadastro.imovel.iper_id = cadastro.imovel_perfil.iper_id "

					+

					"left join cadastro.poco_tipo on cadastro.imovel.poco_id = cadastro.poco_tipo.poco_id "

					+

					"left join cadastro.area_construida_faixa on cadastro.imovel.acon_id = area_construida_faixa.acon_id "

					+

					// AGUA

					"left join micromedicao.hidrometro_inst_hist on atendimentopublico.ligacao_agua.hidi_id =  micromedicao.hidrometro_inst_hist.hidi_id "

					+

					// ESGOTO

					"left join micromedicao.hidrometro_inst_hist hidrometro_instalacao_historic on cadastro.imovel.hidi_id =  hidrometro_instalacao_historic.hidi_id ";

			consulta = consulta

			+ montarInnerJoinFiltrarImoveisOutrosCriterios(

			intervaloMediaMinimaImovelInicial,

			intervaloMediaMinimaImovelFinal,

			intervaloMediaMinimaHidrometroInicial,

			intervaloMediaMinimaHidrometroFinal,

			idFaturamentoSituacaoTipo,

			idCobrancaSituacaoTipo,

			idSituacaoEspecialCobranca,

			idEloAnormalidade,

			idCadastroOcorrencia,

			idConsumoTarifa,

			idTipoMedicao,

			indicadorMedicao,

			idSubCategoria,

			idCategoria,

			idCliente,

			idClienteTipo,

			idClienteRelacaoTipo,

			ConstantesSistema.GERAR_RELATORIO_DADOS_ECONOMIA_IMOVEL, 
			null);

			consulta = consulta

					+ montarCondicaoSQLWhereFiltrarImovelOutrosCriterio(

					idImovelCondominio,

					idImovelPrincipal,

					idSituacaoLigacaoAgua,

					consumoMinimoInicialAgua,

					consumoMinimoFinalAgua,

					idSituacaoLigacaoEsgoto,

					consumoMinimoInicialEsgoto,

					consumoMinimoFinalEsgoto,

					intervaloValorPercentualEsgotoInicial,

					intervaloValorPercentualEsgotoFinal,

					intervaloMediaMinimaImovelInicial,

					intervaloMediaMinimaImovelFinal,

					intervaloMediaMinimaHidrometroInicial,

					intervaloMediaMinimaHidrometroFinal,

					idImovelPerfil,

					idPocoTipo,

					idFaturamentoSituacaoTipo,

					idCobrancaSituacaoTipo,

					idSituacaoEspecialCobranca,

					idEloAnormalidade,

					areaConstruidaInicial,

					areaConstruidaFinal,

					idCadastroOcorrencia,

					idConsumoTarifa,

					idGerenciaRegional,

					idLocalidadeInicial,

					idLocalidadeFinal,

					setorComercialInicial,

					setorComercialFinal,

					quadraInicial,

					quadraFinal,

					loteOrigem,

					loteDestno,

					cep,

					logradouro,

					bairro,

					municipio,

					idTipoMedicao,

					indicadorMedicao,

					idSubCategoria,

					idCategoria,

					quantidadeEconomiasInicial,

					quantidadeEconomiasFinal,

					diaVencimento,

					idCliente,

					idClienteTipo,

					idClienteRelacaoTipo,

					numeroPontosInicial,

					numeroPontosFinal,

					numeroMoradoresInicial,

					numeroMoradoresFinal,

					idAreaConstruidaFaixa,

					idUnidadeNegocio,

					ConstantesSistema.GERAR_RELATORIO_DADOS_ECONOMIA_IMOVEL,
							null, null, null, null, null, null , null);

			/*
			 * # COLOCANDO O VALOR NAS CONDIÇÕES#
			 * 
			 */

			SQLQuery query = session.createSQLQuery(consulta.substring(0,

			(consulta.length() - 5)));

			informarDadosQueryFiltrarImovelOutrosCriterio(query,

			idImovelCondominio, idImovelPrincipal,

			idSituacaoLigacaoAgua, consumoMinimoInicialAgua,

			consumoMinimoFinalAgua, idSituacaoLigacaoEsgoto,

			consumoMinimoInicialEsgoto, consumoMinimoFinalEsgoto,

			intervaloValorPercentualEsgotoInicial,

			intervaloValorPercentualEsgotoFinal,

			intervaloMediaMinimaImovelInicial,

			intervaloMediaMinimaImovelFinal,

			intervaloMediaMinimaHidrometroInicial,

			intervaloMediaMinimaHidrometroFinal, idImovelPerfil,

			idPocoTipo, idFaturamentoSituacaoTipo,

			idCobrancaSituacaoTipo, idSituacaoEspecialCobranca,

			idEloAnormalidade, areaConstruidaInicial,

			areaConstruidaFinal, idCadastroOcorrencia, idConsumoTarifa,

			idGerenciaRegional, idLocalidadeInicial, idLocalidadeFinal,

			setorComercialInicial, setorComercialFinal, quadraInicial,

			quadraFinal, loteOrigem, loteDestno, cep, logradouro,

			bairro, municipio, idTipoMedicao, indicadorMedicao,

			idSubCategoria, idCategoria, quantidadeEconomiasInicial,

			quantidadeEconomiasFinal, diaVencimento, idCliente,

			idClienteTipo, idClienteRelacaoTipo, numeroPontosInicial,

			numeroPontosFinal, numeroMoradoresInicial,

			numeroMoradoresFinal, idAreaConstruidaFaixa,

			idUnidadeNegocio, null, null, null, null, null, null);

			retorno = query.addScalar("imov_id", Hibernate.INTEGER).addScalar(

			"greg_id", Hibernate.INTEGER).addScalar("greg_nmabreviado",

			Hibernate.STRING).addScalar("loca_id", Hibernate.INTEGER)

			.addScalar("loca_nmlocalidade", Hibernate.STRING)

			.addScalar("stcm_cdsetorcomercial", Hibernate.INTEGER)

			.addScalar("stcm_nmsetorcomercial", Hibernate.STRING)

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
	 * 
	 * 
	 * Obtem os dados da Subcategoria do Imovel para o Relatorio de Dados
	 * 
	 * Economias para o Imovel
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 02/08/2006
	 * 
	 * 
	 * 
	 */

	public Collection gerarRelatorioDadosEconomiasImovelSubcategoria(

	String idImovel) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = consulta

					+ "select distinct "

					+ " subcategoria.scat_dssubcategoria, "

					+ " categoria.catg_dscategoria, "

					+ " imovelSubcategoria.imsb_qteconomia, "

					+ " subcategoria.scat_id "

					+ "from cadastro.imovel_subcategoria imovelSubcategoria "

					+ "left join cadastro.imovel on imovelSubcategoria.imov_id = cadastro.imovel.imov_id "

					+ "left join cadastro.subcategoria on imovelSubcategoria.scat_id = cadastro.subcategoria.scat_id "

					+ "left join cadastro.categoria on subcategoria.catg_id = cadastro.categoria.catg_id";

			consulta = consulta + " where imovel.imov_id = :idImovel ";

			SQLQuery query = session.createSQLQuery(consulta);

			query.setInteger("idImovel", new Integer(idImovel).intValue());

			retorno = query.addScalar("scat_dssubcategoria", Hibernate.STRING)

			.addScalar("catg_dscategoria", Hibernate.STRING).addScalar(

			"imsb_qteconomia", Hibernate.SHORT).addScalar(

			"scat_id", Hibernate.INTEGER)

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
	 * 
	 * 
	 * Obtem os dados do Imovel Economia do Imovel para o Relatorio de Dados
	 * 
	 * Economias para o Imovel
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 02/08/2006
	 * 
	 * 
	 * 
	 */

	public Collection gerarRelatorioDadosEconomiasImovelEconomia(

	String idImovel, String idSubCategoria)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = consulta

					+ "select distinct "

					+ " imovelEconomia.imec_dscomplementoendereco, "

					+ " imovelEconomia.imec_nnpontosutilizacao, "

					+ " imovelEconomia.imec_nnmorador, "

					+ " imovelEconomia.imec_nniptu, "

					+ " imovelEconomia.imec_nncontratoenergia, "

					+ " imovelEconomia.imec_nnareaconstruida, "

					+ " imovelEconomia.imec_id "

					+

					" from cadastro.imovel_economia imovelEconomia "

					+ " left join cadastro.imovel on imovelEconomia.imov_id = cadastro.imovel.imov_id "

					+ " left join cadastro.imovel_subcategoria on imovelEconomia.scat_id = imovel_subcategoria.scat_id ";

			consulta = consulta

					+ " where imovelEconomia.imov_id = :idImovel and imovelEconomia.scat_id = :idSubcategoria ";

			SQLQuery query = session.createSQLQuery(consulta);

			query.setInteger("idImovel", new Integer(idImovel).intValue());

			query.setInteger("idSubcategoria", new Integer(idSubCategoria)

			.intValue());

			retorno = query.addScalar("imec_dscomplementoendereco",

			Hibernate.STRING).addScalar("imec_nnpontosutilizacao",

			Hibernate.SHORT).addScalar("imec_nnmorador",

			Hibernate.SHORT).addScalar("imec_nniptu",

			Hibernate.BIG_DECIMAL).addScalar("imec_nncontratoenergia",

			Hibernate.LONG).addScalar("imec_nnareaconstruida",

			Hibernate.BIG_DECIMAL).addScalar("imec_id",

			Hibernate.INTEGER)

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
	 * 
	 * 
	 * Obtem os dados do Cliente Imovel Economia do Imovel para o Relatorio de
	 * 
	 * Dados Economias para o Imovel
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 02/08/2006
	 * 
	 * 
	 * 
	 */

	public Collection gerarRelatorioDadosEconomiasImovelClienteEconomia(

	String idImovelEconomia) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = consulta

					+ "select distinct "

					+ " cliente.clie_nmcliente, "

					+ " cliente_relacao_tipo.crtp_dsclienterelacaotipo, "

					+ " cliente.clie_nncpf, "

					+ " cliente.clie_nncnpj, "

					+ " clienteImovelEconomia.cime_dtrelacaoinicio, "

					+ " clienteImovelEconomia.cime_dtrelacaofim, "

					+ " clim_fim_relacao_motivo.cifr_dsfimrelacaomotivo "

					+ " from cadastro.cliente_imovel_economia clienteImovelEconomia "

					+ " left join cadastro.imovel_economia on clienteImovelEconomia.imec_id = cadastro.imovel_economia.imec_id "

					+ " left join cadastro.cliente on clienteImovelEconomia.clie_id = cadastro.cliente.clie_id "

					+ " left join cadastro.cliente_relacao_tipo on clienteImovelEconomia.crtp_id = cadastro.cliente_relacao_tipo.crtp_id "

					+ " left join cadastro.clim_fim_relacao_motivo on clienteImovelEconomia.cifr_id = cadastro.clim_fim_relacao_motivo.cifr_id ";

			consulta = consulta

			+ " where clienteImovelEconomia.imec_id = :idImovelEconomia ";

			SQLQuery query = session.createSQLQuery(consulta);

			query.setInteger("idImovelEconomia", new Integer(idImovelEconomia)

			.intValue());

			retorno = query.addScalar("clie_nmcliente", Hibernate.STRING)

			.addScalar("crtp_dsclienterelacaotipo", Hibernate.STRING)

			.addScalar("clie_nncpf", Hibernate.STRING).addScalar(

			"clie_nncnpj", Hibernate.STRING).addScalar(

			"cime_dtrelacaoinicio", Hibernate.DATE).addScalar(

			"cime_dtrelacaofim", Hibernate.DATE).addScalar(

			"cifr_dsfimrelacaomotivo", Hibernate.STRING).list();

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
	 * 
	 * 
	 * Esse método é usado para fzazer uma pesquisa na tabela imóvel e confirmar
	 * 
	 * se o id passado é de um imóvel excluído(idExclusao)
	 * 
	 * 
	 * 
	 * Flávio Cordeiro
	 * 
	 */

	public Boolean confirmarImovelExcluido(Integer idImovel)

	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		boolean retorno = false;

		try {

			String sql = "select im.imov_id as idImovel "

			+ " from cadastro.imovel im " + " where im.imov_id = "

			+ idImovel + " and im.imov_icexclusao = "

			+ ConstantesSistema.INDICADOR_IMOVEL_EXCLUIDO;

			Collection colecaoConsulta = session.createSQLQuery(sql).addScalar(

			"idImovel", Hibernate.INTEGER).list();

			if (!colecaoConsulta.isEmpty()) {

				retorno = true;

			} else {

				retorno = false;

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
	 * 
	 * 
	 * [UC164] Filtrar Imoveis Outros Criterios
	 * 
	 * 
	 * 
	 * Monta os inner join da query de acordo com os parametros informados
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 03/08/2006
	 * 
	 * 
	 * 
	 * @return
	 * 
	 */

	public String montarInnerJoinFiltrarImoveisOutrosCriterios(
						String intervaloMediaMinimaImovelInicial,
						String intervaloMediaMinimaImovelFinal,
						String intervaloMediaMinimaHidrometroInicial,
						String intervaloMediaMinimaHidrometroFinal,
						String idFaturamentoSituacaoTipo, 
						String idCobrancaSituacaoTipo,
						String idSituacaoEspecialCobranca, 
						String idEloAnormalidade,
						String idCadastroOcorrencia, 
						String idConsumoTarifa,
						String idTipoMedicao, 
						String indicadorMedicao,
						String idSubCategoria, 
						String idCategoria, 
						String idCliente,
						String idClienteTipo, 
						String idClienteRelacaoTipo, 
						Integer relatorio, 
						String cpfCnpj) {

		String innerJoin = "";

		// Relatorio de Economias do Imovel

		if (relatorio != null
				&& relatorio.intValue() == ConstantesSistema.GERAR_RELATORIO_DADOS_ECONOMIA_IMOVEL.intValue()) {

			innerJoin = innerJoin + " left join cadastro.imovel_economia on cadastro.imovel.imov_id = cadastro.imovel_economia.imov_id ";

		}

		// faturamento situacao tipo
		if (idFaturamentoSituacaoTipo != null && !idFaturamentoSituacaoTipo.equals("")
				&& !idFaturamentoSituacaoTipo.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			innerJoin = innerJoin + " left join faturamento.fatur_situacao_tipo on cadastro.imovel.ftst_id = faturamento.fatur_situacao_tipo.ftst_id ";

		}

		// Situacao Especial Cobranca
		if (idSituacaoEspecialCobranca != null && !idSituacaoEspecialCobranca.equals("")
				&& !idSituacaoEspecialCobranca.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			innerJoin = innerJoin + " left join cobranca.cobranca_situacao_tipo on cadastro.imovel.cbsp_id = cobranca.cobranca_situacao_tipo.cbsp_id ";

		}

		// cobranca situacao tipo
		if (idCobrancaSituacaoTipo != null && !idCobrancaSituacaoTipo.equals("")
				&& !idCobrancaSituacaoTipo.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			innerJoin = innerJoin + " left join cobranca.cobranca_situacao on cadastro.imovel.cbst_id = cobranca.cobranca_situacao.cbst_id ";

		}

		// elo anormalidade
		if (idEloAnormalidade != null && !idEloAnormalidade.equals("")
				&& !idEloAnormalidade.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			innerJoin = innerJoin + " left join cadastro.elo_anormalidade on cadastro.imovel.eanm_id = cadastro.elo_anormalidade.eanm_id ";

		}

		// cadastro ocorrencia
		if (idCadastroOcorrencia != null && !idCadastroOcorrencia.equals("")
				&& !idCadastroOcorrencia.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			innerJoin = innerJoin + " left join cadastro.cadastro_ocorrencia on cadastro.imovel.cocr_id = cadastro.cadastro_ocorrencia.cocr_id ";

		}

		// consumo tarifa
		if (idConsumoTarifa != null	&& !idConsumoTarifa.equals("")
				&& !idConsumoTarifa.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			innerJoin = innerJoin + " left join faturamento.consumo_tarifa on cadastro.imovel.cstf_id = faturamento.consumo_tarifa.cstf_id ";

		}

		// sub categoria
		if (idSubCategoria != null && !idSubCategoria.equals("")
				&& !idSubCategoria.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			innerJoin = innerJoin + " left join cadastro.subcategoria on imovelSubcategoria.scat_id = cadastro.subcategoria.scat_id ";

		}

		// categoria

		if (idCategoria != null	&& !idCategoria.equals("")
				&& !idCategoria.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			if (!(idSubCategoria != null && !idSubCategoria.equals("") 
					&& !idSubCategoria.trim().equalsIgnoreCase(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

				innerJoin = innerJoin	+ " left join cadastro.subcategoria on imovelSubcategoria.scat_id = cadastro.subcategoria.scat_id ";

			}

			innerJoin = innerJoin	+ " left join cadastro.categoria on subcategoria.catg_id = cadastro.categoria.catg_id ";

		}

		// cliente imovel

		if (((idClienteRelacaoTipo != null && !idClienteRelacaoTipo.equals("") 
				&& !idClienteRelacaoTipo.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())))
				|| ((idCliente != null && !idCliente.equals("") 
						&& !idCliente.trim().equalsIgnoreCase(
								new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())))) {

			// Relatorio de Economias do Imovel

			if (relatorio != null
					&& relatorio.intValue() == ConstantesSistema.GERAR_RELATORIO_DADOS_ECONOMIA_IMOVEL.intValue()) {

				innerJoin = innerJoin

						+ " left join cadastro.cliente_imovel_economia on cadastro.imovel_economia.imec_id = cadastro.cliente_imovel_economia.imec_id and cadastro.cliente_imovel_economia.cime_dtrelacaofim is null ";

			} else {

				innerJoin = innerJoin

						+ " left join cadastro.cliente_imovel on cadastro.imovel.imov_id = cadastro.cliente_imovel.imov_id  and cadastro.cliente_imovel.clim_dtrelacaofim is null ";

			}

		}

		// cliente relacao tipo

		if (idClienteRelacaoTipo != null && !idClienteRelacaoTipo.equals("")
				&& !idClienteRelacaoTipo.trim().equalsIgnoreCase(		
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)	.toString())) {

			// Relatorio de Economias do Imovel

			if (relatorio != null
					&& relatorio.intValue() == ConstantesSistema.GERAR_RELATORIO_DADOS_ECONOMIA_IMOVEL
					.intValue()) {

				innerJoin = innerJoin

						+ " left join cadastro.cliente_relacao_tipo on cadastro.cliente_imovel_economia.crtp_id = cadastro.cliente_relacao_tipo.crtp_id ";

			} else {

				innerJoin = innerJoin

						+ " left join cadastro.cliente_relacao_tipo on cadastro.cliente_imovel.crtp_id = cadastro.cliente_relacao_tipo.crtp_id ";

			}

		}
		
		
		// cliente

		if (idCliente != null && !idCliente.equals("")
				&& !idCliente.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

			// Relatorio de Economias do Imovel

			if (relatorio != null
					&& relatorio.intValue() == ConstantesSistema.GERAR_RELATORIO_DADOS_ECONOMIA_IMOVEL.intValue()) {

				innerJoin = innerJoin

						+ " left join cadastro.cliente on cadastro.cliente_imovel_economia.clie_id = cadastro.cliente.clie_id ";

			} else {

				innerJoin = innerJoin

						+ " left join cadastro.cliente on cadastro.cliente_imovel.clie_id = cadastro.cliente.clie_id ";

			}

		}

		// cliente tipo
		
		if (idClienteTipo != null && !idClienteTipo.equals("") &&
			!idClienteTipo.trim().equalsIgnoreCase(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {
			if (!(idCliente != null && !idCliente.equals("") && 
				  !idCliente.trim().equalsIgnoreCase(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

				// Relatorio de Economias do Imovel

				if (relatorio != null
						&& relatorio.intValue() == ConstantesSistema.GERAR_RELATORIO_DADOS_ECONOMIA_IMOVEL
						.intValue()) {

					if (!(idClienteRelacaoTipo != null && !idClienteRelacaoTipo.equals("") && 
							  !idClienteRelacaoTipo.trim().equalsIgnoreCase(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {
						innerJoin = innerJoin
							+ " left join cadastro.cliente_imovel_economia on cadastro.imovel_economia.imec_id = cadastro.cliente_imovel_economia.imec_id  and cadastro.cliente_imovel_economia.cime_dtrelacaofim is null ";
					}

					innerJoin = innerJoin
							+ " left join cadastro.cliente on cadastro.cliente_imovel_economia.clie_id = cadastro.cliente.clie_id ";

				} else {

					if (!(idClienteRelacaoTipo != null && !idClienteRelacaoTipo.equals("") && 
							  !idClienteRelacaoTipo.trim().equalsIgnoreCase(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {
						innerJoin = innerJoin
							+ " left join cadastro.cliente_imovel on cadastro.imovel.imov_id = cadastro.cliente_imovel.imov_id   and cadastro.cliente_imovel.clim_dtrelacaofim is null ";
					}

					innerJoin = innerJoin
							+ " left join cadastro.cliente on cadastro.cliente_imovel.clie_id = cadastro.cliente.clie_id ";

				}

			} 
				
			innerJoin = innerJoin

					+ " left join cadastro.cliente_tipo on cadastro.cliente.cltp_id = cadastro.cliente.cltp_id ";

		}

		// intervalo Media Minima Imovel Inicial e Final

		if ((intervaloMediaMinimaImovelInicial != null && !intervaloMediaMinimaImovelInicial.equals("") 
				&& !intervaloMediaMinimaImovelInicial.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))
						&& (intervaloMediaMinimaImovelFinal != null	&& !intervaloMediaMinimaImovelFinal.equals("") 
								&& !intervaloMediaMinimaImovelFinal.trim().equalsIgnoreCase(
										new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

			innerJoin = innerJoin

					+ " left join micromedicao.consumo_historico on cadastro.imovel.imov_id = micromedicao.consumo_historico.imov_id ";

		}

	

			if (idTipoMedicao != null && !idTipoMedicao.equals("")) {

				if (idTipoMedicao.equals(MedicaoTipo.LIGACAO_AGUA.toString())) {

					innerJoin = innerJoin

							+ " inner join micromedicao.medicao_historico on cadastro.imovel.imov_id = micromedicao.medicao_historico.lagu_id ";

				} else if (idTipoMedicao.equals(MedicaoTipo.POCO.toString())) {

					innerJoin = innerJoin

							+ " inner join micromedicao.medicao_historico on cadastro.imovel.imov_id = micromedicao.medicao_historico.imov_id ";

				}

			}

		//}

		return innerJoin;

	}

	/**
	 * 
	 * Permite pesquisar entidade beneficente [UC0389] Inserir Autorização para
	 * 
	 * Doação Mensal
	 * 
	 * 
	 * 
	 * @author César Araújo
	 * 
	 * @date 30/08/2006
	 * 
	 * @param filtroEntidadeBeneficente -
	 * 
	 * Filtro com os valores para pesquisa
	 * 
	 * @return Collection<EntidadeBeneficente> - Coleção de entidade(s)
	 * 
	 * beneficente(s)
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Collection<EntidadeBeneficente> pesquisarEntidadeBeneficente(

	FiltroEntidadeBeneficente filtroEntidadeBeneficente)

	throws ErroRepositorioException {

		/** * Declara varáveis locais ** */

		Collection retorno = null;

		Session session = null;

		/** * Obtém a instância da sessão ** */

		session = HibernateUtil.getSession();

		try {

			/**
			 * * executa o método para pesquisa de pesquisa de Entidade
			 * 
			 * Beneficente **
			 * 
			 */

			retorno = GeradorHQLCondicional.gerarQueryCriteriaExpression(

			session, filtroEntidadeBeneficente,

			EntidadeBeneficente.class);

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * 
	 * Permite pesquisar imóvel doação [UC0389] Inserir Autorização para Doação
	 * 
	 * Mensal
	 * 
	 * 
	 * 
	 * @author César Araújo
	 * 
	 * @date 30/08/2006
	 * 
	 * @param filtroImoveldoacao -
	 * 
	 * Filtro com os valores para pesquisa
	 * 
	 * @return Collection<ImovelDoacao> - Coleção de imóvei(s) doação
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Collection<ImovelDoacao> pesquisarImovelDoacao(

	FiltroImovelDoacao filtroImovelDoacao)

	throws ErroRepositorioException {

		/** * Declara variáveis locais ** */

		Collection<ImovelDoacao> retorno = null;

		Session session = null;

		/** * Obtém a sessão ** */

		session = HibernateUtil.getSession();

		try {

			/** * executa o método para pesquisa de pesquisa de Imovel Doação ** */

			retorno = new ArrayList(

			new CopyOnWriteArraySet<ImovelDoacao>(

			GeradorHQLCondicional

			.gerarCondicionalQuery(

			filtroImovelDoacao,

			"imovelDoacao",

			"from gcom.cadastro.imovel.ImovelDoacao as imovelDoacao",

			session).list()));

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * 
	 * Permite atualizar as informações referentes ao imóvel doação [UC0390]
	 * 
	 * Manter Autorização para Doação Mensal
	 * 
	 * 
	 * 
	 * @author César Araújo
	 * 
	 * @date 30/08/2006
	 * 
	 * @param imovelDoacao -
	 * 
	 * instância de imóvel doação que servirá de base para a
	 * 
	 * atualição
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public void atualizarImovelDoacao(ImovelDoacao imovelDoacao)

	throws ErroRepositorioException {

		/** * Declara a variável local ** */

		Session session = null;

		/** * Obtém a sessão ** */

		session = HibernateUtil.getSession();

		try {

			/** * Atualiza as informações do imóvel doação ** */

			session.update(imovelDoacao);

			session.flush();

		} catch (HibernateException e) {

			e.printStackTrace();

			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

	}

	/**
	 * 
	 * Pesquisa um imóvel a partir do seu id.Retorna os dados que compõem a
	 * 
	 * inscrição e o endereço do mesmo
	 * 
	 * 
	 * 
	 * @author Raphael Rossiter
	 * 
	 * @date 01/08/2006
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * @return Collection
	 * 
	 * @throws ControladorException
	 * 
	 */

	public Collection pesquisarImovelRegistroAtendimento(Integer idImovel)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "SELECT logradouro.nome,"

					+ // 0

					" logradouroTipo.descricaoAbreviada,"

					+ // 1

					" logradouroTitulo.descricaoAbreviada,"

					+ // 2

					" bairro.id,"

					+ // 3

					" bairro.nome,"

					+ // 4

					" municipio.id,"

					+ // 5

					" municipio.nome,"

					+ // 6

					" unidadeFederacao.id,"

					+ // 7

					" unidadeFederacao.sigla,"

					+ // 8

					" enderecoReferencia.descricaoAbreviada,"

					+ // 9

					" cep.cepId,"

					+ // 10

					" cep.logradouro,"

					+ // 11

					" cep.descricaoTipoLogradouro,"

					+ // 12

					" cep.bairro,"

					+ // 13

					" cep.municipio,"

					+ // 14

					" cep.sigla, "

					+ // 15

					" cep.codigo, "

					+ // 16

					" imovel.numeroImovel,"

					+ // 17

					" imovel.complementoEndereco,"

					+ // 18

					" logradouro.id,"

					+ // 19

					" logradouroCep.id,"

					+ // 20

					" logradouroBairro.id,"

					+ // 21

					" logradouroTipo.descricao,"

					+ // 22

					" logradouroTitulo.descricao,"

					+ // 23

					" enderecoReferencia.descricao, "

					+ // 24

					" localidade.id, "

					+ // 25

					" setorComercial.codigo, "

					+ // 26

					" quadra.numeroQuadra, "

					+ // 27

					" imovel.lote, "

					+ // 28

					" imovel.subLote, "

					+ // 29

					" divisaoEsgoto.id, "

					+ // 30

					" pavimentoRua.id, "

					+ // 31

					" pavimentoCalcada.id, "

					+ // 32

					" localidade.descricao, "

					+ // 33

					" setorComercial.descricao, "

					+ // 34

					" setorComercial.id, "

					+ // 35

					" quadra.id, "

					+ // 36

					" bairro.codigo, "

					+ // 37

					" ligacaoAguaSituacao.id, "

					+ // 38

					" ligacaoAguaSituacao.descricao, "

					+ // 39

					" ligacaoEsgotoSituacao.id, "

					+ // 40

					" ligacaoEsgotoSituacao.descricao, "

					+ // 41

					" enderecoReferencia.id, "

					+ // 42

					" imovelPerfil.id, " 
					
					+ // 43
					
					" imovel.coordenadaX, "
					
					+ //44
					
					" imovel.coordenadaY, "//45
					
					+ " municipioPrincipal.id, "//46
					
					+ " municipioPrincipal.nome "//47
					
					+ "from Imovel imovel "

					+ "left join imovel.logradouroCep logradouroCep "

					+ "left join logradouroCep.cep cep "

					+ "left join logradouroCep.logradouro logradouro "

					+ "left join logradouro.logradouroTipo logradouroTipo "

					+ "left join logradouro.logradouroTitulo logradouroTitulo "

					+ "left join imovel.logradouroBairro logradouroBairro "

					+ "left join logradouroBairro.bairro bairro "

					+ "left join bairro.municipio municipio "

					+ "left join municipio.unidadeFederacao unidadeFederacao "

					+ "left join imovel.enderecoReferencia enderecoReferencia "

					+ "left join imovel.localidade localidade "

					+ "left join imovel.setorComercial setorComercial "

					+ "left join imovel.quadra quadra "

					+ "left join quadra.bacia bacia "

					+ "left join bacia.sistemaEsgoto sistemaEsgoto "

					+ "left join sistemaEsgoto.divisaoEsgoto divisaoEsgoto "

					+ "left join imovel.pavimentoRua pavimentoRua "

					+ "left join imovel.pavimentoCalcada pavimentoCalcada "

					+ "left join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "

					+ "left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "

					+ "left join imovel.imovelPerfil imovelPerfil "

					+ "left join localidade.municipio municipioPrincipal "
					
					+ "where imovel.id = :idImovel and imovel.indicadorExclusao != 1";

			retorno = session.createQuery(consulta).setInteger("idImovel",

			idImovel.intValue()).list();

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
	 * Consutlar os Dados Cadastrais do Imovel [UC0472] Consultar Imóvel
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 07/09/2006
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * @return Collection
	 * 
	 * @throws ControladorException
	 * 
	 */

	public Collection consultarImovelDadosCadastrais(Integer idImovel)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "SELECT ligacaoAguaSituacao.descricao, "

					+ // 0

					" ligacaoEsgotoSituacao.descricao, "

					+ // 1

					" imovelPerfil.descricao, "

					+ // 2

					" despejo.descricao, "

					+ // 3

					" imovel.areaConstruida, "

					+ // 4

					" areaConstruidaFaixa.menorFaixa, "

					+ // 5

					" areaConstruidaFaixa.maiorFaixa, "

					+ // 6

					" imovel.testadaLote, "

					+ // 7

					" imovel.volumeReservatorioInferior, "

					+ // 8

					" reservatorioVolumeFaixaInferior.volumeMenorFaixa, "

					+ // 9

					" reservatorioVolumeFaixaInferior.volumeMaiorFaixa, "

					+ // 10

					" imovel.volumeReservatorioSuperior, "

					+ // 11

					" reservatorioVolumeFaixaSuperior.volumeMenorFaixa, "

					+ // 12

					" reservatorioVolumeFaixaSuperior.volumeMaiorFaixa, "

					+ // 13

					" imovel.volumePiscina, "

					+ // 14

					" piscinaVolumeFaixa.volumeMenorFaixa, "

					+ // 15

					" piscinaVolumeFaixa.volumeMaiorFaixa, "

					+ // 16

					" fonteAbastecimento.descricao, "

					+ // 17

					" pocoTipo.descricao, "

					+ // 18

					" distritoOperacional.descricao, "

					+ // 19

					" pavimentoRua.descricao, "

					+ // 20

					" pavimentoCalcada.descricao, "

					+ // 21

					" imovel.numeroIptu, "

					+ // 22

					" imovel.numeroCelpe, "

					+ // 23

					" imovel.coordenadaX, "

					+ // 24

					" imovel.coordenadaY, "

					+ // 25

					" cadastroOcorrencia.descricao, "

					+ // 26

					" eloAnormalidade.descricao, "

					+ // 27

					" imovel.indicadorImovelCondominio, "

					+ // 28

					" imovelCondominio.id, "

					+ // 29

					" imovelPrincipal.id, "

					+ // 30

					" imovel.numeroPontosUtilizacao, "

					+ // 31

					" imovel.numeroMorador, "

					+ // 32

					" imovel.indicadorJardim, "

					+ // 33

					" bacia.descricao, "

					+ // 34

					" cobrancaSituacao.descricao, "

					+ // 35
					" divisaoEsgoto.descricao, "+ // 36

					" tipoHabitacao.descricao, "+ // 37
					" tipoPropriedade.descricao, "+ // 38
					" tipoConstrucao.descricao, "+ // 39
					" tipoCobertura.descricao, "+ // 40
					" imovel.indicadorExclusao, "+ // 41
					" imovel.nomeImovel, "+ // 42
					" localidade.municipio, " + //43
					" imovel.indicadorNivelInstalacaoEsgoto " + //44
					"from Imovel imovel "

					+ "left join imovel.imovelPerfil imovelPerfil "

					+ "left join imovel.despejo despejo "

					+ "left join imovel.areaConstruidaFaixa areaConstruidaFaixa "

					+ "left join imovel.reservatorioVolumeFaixaInferior reservatorioVolumeFaixaInferior "

					+ "left join imovel.reservatorioVolumeFaixaSuperior reservatorioVolumeFaixaSuperior "

					+ "left join imovel.piscinaVolumeFaixa piscinaVolumeFaixa "

					+ "left join imovel.fonteAbastecimento fonteAbastecimento "

					+ "left join imovel.quadra quadra "

					+ "left join quadra.distritoOperacional distritoOperacional "

					+ "left join quadra.bacia bacia "

					+ "left join bacia.sistemaEsgoto sistemaEsgoto "

					+ "left join sistemaEsgoto.divisaoEsgoto divisaoEsgoto "

					+ "left join imovel.pavimentoRua pavimentoRua "

					+ "left join imovel.pavimentoCalcada pavimentoCalcada "

					+ "left join imovel.cadastroOcorrencia cadastroOcorrencia "

					+ "left join imovel.eloAnormalidade eloAnormalidade "

					+ "left join imovel.imovelCondominio imovelCondominio "

					+ "left join imovel.imovelPrincipal imovelPrincipal "

					+ "left join imovel.pocoTipo pocoTipo "

					+ "left join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "

					+ "left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "

					+ "left join imovel.cobrancaSituacao cobrancaSituacao "
					
					+ "left join imovel.imovelTipoHabitacao tipoHabitacao "
					+ "left join imovel.imovelTipoPropriedade tipoPropriedade "
					+ "left join imovel.imovelTipoConstrucao tipoConstrucao "
					+ "left join imovel.imovelTipoCobertura tipoCobertura "
					+ "left join imovel.localidade localidade "
					+ "left join localidade.municipio municipio "
					+ "where imovel.id = :idImovel ";

			retorno = session.createQuery(consulta).setInteger("idImovel",

			idImovel.intValue()).list();

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
	 * Pesquisa a coleção de clientes do imovel [UC0472] Consultar Imovel
	 * 
	 * @param filtroClienteImovel
	 * parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 * Description of the Exception
	 */
	public Collection pesquisarClientesImovel(Integer idImovel) throws ErroRepositorioException {

		// cria a coleção de retorno
		Collection retorno = null;

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		String consulta = null;

		// pesquisa a coleção de atividades e atribui a variável "retorno"
		try {

			consulta = "SELECT cliente.id,"
					+ // 0
					"cliente.nome,"
					+ // 1
					"clienteRelacaoTipo.descricao,"
					+ // 2
					"clienteImovel.dataInicioRelacao,"
					+ // 3
					"clienteFone.telefone,"
					+ // 4
					"cliente.cnpj,"
					+ // 5
					"cliente.cpf, "
					+ // 6
					"clienteFone.ddd, "
					+ // 7
					"clienteRelacaoTipo.id, "
					+ // 8
					"clienteImovel.id, "
					+//9
					"cliente.rg, "
					+//10
					"cliente.indicadorUso "
					+//11
					"from ClienteImovel clienteImovel "
					+
					// " left join clienteImovel.imovel imovel "
					"left join clienteImovel.cliente cliente "
					+ "left join clienteImovel.clienteRelacaoTipo clienteRelacaoTipo "
					+ "left join cliente.clienteFones clienteFone with(clienteFone.indicadorTelefonePadrao = 1) "
					+ "where clienteImovel.imovel.id = :idImovel and clienteImovel.dataFimRelacao is null " 
					+ " and clienteImovel.imovel.indicadorExclusao = :indicadorExclusao";

			retorno = session.createQuery(consulta).setInteger("idImovel",
			idImovel.intValue()).setInteger("indicadorExclusao", 2).list();

			// erro no hibernate
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
	 * Pesquisa a coleção de clientes do imovel [UC0472] Consultar Imovel
	 * 
	 * @param filtroClienteImovel
	 * parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 * Description of the Exception
	 */
	public Collection pesquisarClientesImovelDataMax(Integer idImovel) throws ErroRepositorioException {

		// cria a coleção de retorno
		Collection retorno = null;

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		String consulta = null;

		// pesquisa a coleção de atividades e atribui a variável "retorno"
		try {

			consulta = "SELECT cliente.id,"
					+ // 0
					"cliente.nome,"
					+ // 1
					"clienteRelacaoTipo.descricao,"
					+ // 2
					"clienteImovel.dataInicioRelacao,"
					+ // 3
					"clienteFone.telefone,"
					+ // 4
					"cliente.cnpj,"
					+ // 5
					"cliente.cpf, "
					+ // 6
					"clienteFone.ddd, "
					+ // 7
					"clienteRelacaoTipo.id, "
					+ // 8
					"clienteImovel.id, "
					+//9
					"cliente.rg, "
					+//10
					"cliente.indicadorUso "
					+//11
					"from ClienteImovel clienteImovel "
					+
					// " left join clienteImovel.imovel imovel "
					"left join clienteImovel.cliente cliente "
					+ "left join clienteImovel.clienteRelacaoTipo clienteRelacaoTipo "
					+ "left join cliente.clienteFones clienteFone with(clienteFone.indicadorTelefonePadrao = 1) "
					+ "where clienteImovel.imovel.id = :idImovel and clienteImovel.dataFimRelacao = "
					+ "(SELECT MAX(clienteImovel.dataFimRelacao) FROM clienteImovel WHERE clienteImovel.imovel.id = :idImovel)"
					+ " and clienteImovel.imovel.indicadorExclusao = :indicadorExclusao";

			retorno = session.createQuery(consulta).setInteger("idImovel",
			idImovel.intValue()).setInteger("indicadorExclusao", ConstantesSistema.SIM).list();

			// erro no hibernate
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
	 * Pesquisa a coleção de clientes do imovel mesmo que o imóvel já tenha sido excluído [UC0472] Consultar Imovel
	 * 
	 * @param filtroClienteImovel
	 * parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 * Description of the Exception
	 */
	public Collection pesquisarClientesImovelExcluidoOuNao(Integer idImovel) throws ErroRepositorioException {

		// cria a coleção de retorno
		Collection retorno = null;

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		String consulta = null;

		// pesquisa a coleção de atividades e atribui a variável "retorno"
		try {

			consulta = "SELECT cliente.id,"
					+ // 0
					"cliente.nome,"
					+ // 1
					"clienteRelacaoTipo.descricao,"
					+ // 2
					"clienteImovel.dataInicioRelacao,"
					+ // 3
					"clienteFone.telefone,"
					+ // 4
					"cliente.cnpj,"
					+ // 5
					"cliente.cpf, "
					+ // 6
					"clienteFone.ddd, "
					+ // 7
					"clienteRelacaoTipo.id, "
					+ // 8
					"clienteImovel.id, "
					+//9
					"cliente.rg, "
					+//10
					"cliente.indicadorUso "
					+//11
					"from ClienteImovel clienteImovel "
					+
					// " left join clienteImovel.imovel imovel "
					"left join clienteImovel.cliente cliente "
					+ "left join clienteImovel.clienteRelacaoTipo clienteRelacaoTipo "
					+ "left join cliente.clienteFones clienteFone with(clienteFone.indicadorTelefonePadrao = 1) "
					+ "where clienteImovel.imovel.id = :idImovel and clienteImovel.dataFimRelacao is null ";

			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel.intValue()).list();

			// erro no hibernate
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
	 * Pesquisa a coleção de categorias do imovel [UC0472] Consultar Imovel
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @since 07/09/2006
	 * 
	 * 
	 * 
	 * @param filtroClienteImovel
	 * 
	 * parametros para a consulta
	 * 
	 * @return Description of the Return Value
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Description of the Exception
	 * 
	 */

	public Collection pesquisarCategoriasImovel(Integer idImovel)

	throws ErroRepositorioException {

		// cria a coleção de retorno

		Collection retorno = null;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		String consulta = null;

		// pesquisa a coleção de atividades e atribui a variável "retorno"

		try {

			consulta = "select categoria.descricao,"

					+ // 0

					"subcategoria.descricao,"

					+ // 1

					"imovelSubcategoria.quantidadeEconomias, "

					+ // 2

					"categoria, "

					+ // 3
					
					"subcategoria.id "

					+ // 4

					"from ImovelSubcategoria imovelSubcategoria "

					+ "left join imovelSubcategoria.comp_id.subcategoria subcategoria "

					+ "left join subcategoria.categoria categoria "

					+ "where imovelSubcategoria.comp_id.imovel.id = :idImovel ";

			retorno = session.createQuery(consulta).setInteger("idImovel",

			idImovel.intValue()).list();

			// erro no hibernate

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
	 * [UC0475] Consultar Perfil Imovel
	 * 
	 * 
	 * 
	 * @author Leonardo Regis
	 * 
	 * @date 09/09/2006
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * @return Perfil do Imóvel
	 * 
	 * @exception ErroRepositorioException
	 * 
	 */

	public ImovelPerfil obterImovelPerfil(Integer idImovel)

	throws ErroRepositorioException {

		ImovelPerfil retorno = null;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "SELECT i.imovelPerfil " + "FROM Imovel i "

			+ "WHERE i.id = :idImovel ";

			retorno = (ImovelPerfil) session.createQuery(consulta).setInteger(

			"idImovel", idImovel).setMaxResults(1).uniqueResult();

			// erro no hibernate

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		// retorna o perfil do imóvel

		return retorno;

	}

	/**
	 * 
	 * Consultar os Dados Complementares do Imovel 
	 * 
	 * [UC0473] Consultar Imóvel
	 * 
	 * @author Rafael Santos
	 * @date 11/09/2006
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection consultarImovelDadosComplementares(Integer idImovel)
		throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT ligacaoAguaSituacao.descricao, " + // 0
				"ligacaoEsgotoSituacao.descricao, " + // 1
				"consumoTarifa.descricao, " + // 2
				"imovel.numeroRetificacao, " + // 3
				"imovel.numeroParcelamento, " + // 4
				"imovel.numeroReparcelamento, " + // 5
				"imovel.numeroReparcelamentoConsecutivos, " + // 6
				"cobrancaSituacao.descricao, " + // 7
				"cadastroOcorrencia.descricao, " + // 8
				"eloAnormalidade.descricao, " + // 9
				"funcionario.id, " + // 10
				"funcionario.nome, " + // 11
				"imovel.indicadorExclusao, " +// 12
				"imovel.informacoesComplementares " + //13
				"FROM Imovel imovel " + 
				"left join imovel.consumoTarifa consumoTarifa " + 
				"left join imovel.cobrancaSituacao cobrancaSituacao " + 
				"left join imovel.cadastroOcorrencia cadastroOcorrencia " + 
				"left join imovel.eloAnormalidade eloAnormalidade " + 
				"left join imovel.ligacaoAguaSituacao ligacaoAguaSituacao " + 
				"left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao " + 
				"left join imovel.funcionario funcionario " +
				"where imovel.id = :idImovel ";

			retorno = session.createQuery(consulta).setInteger("idImovel",
			idImovel.intValue()).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * Pesquisa a coleção de vencimento alternativos do imovel [UC0473]
	 * 
	 * Consultar Imovel Dados Complementares
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 11/09/2006
	 * 
	 * @return Description of the Return Value
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Description of the Exception
	 * 
	 */

	public Collection pesquisarVencimentoAlternativoImovel(Integer idImovel)

	throws ErroRepositorioException {

		// cria a coleção de retorno

		Collection retorno = null;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		String consulta = null;

		// pesquisa a coleção de atividades e atribui a variável "retorno"

		try {

			consulta = "SELECT vencimentoAlternativo.dateVencimento," + // 0

					"vencimentoAlternativo.dataImplantacao," + // 1

					"vencimentoAlternativo.dateExclusao " + // 2

					"from VencimentoAlternativo vencimentoAlternativo "

					+ "where vencimentoAlternativo.imovel.id = :idImovel ";

			retorno = session.createQuery(consulta).setInteger("idImovel",

			idImovel.intValue()).list();

			// erro no hibernate

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
	 * Pesquisa a coleção de Debitos Automaticos do imovel [UC0473] Consultar
	 * 
	 * Imovel Dados Complementares
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 11/09/2006
	 * 
	 * @return Description of the Return Value
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Description of the Exception
	 * 
	 */

	public Collection pesquisarDebitosAutomaticosImovel(Integer idImovel)

	throws ErroRepositorioException {

		// cria a coleção de retorno

		Collection retorno = null;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		String consulta = null;

		// pesquisa a coleção de atividades e atribui a variável "retorno"

		try {

			consulta = "SELECT banco.descricaoAbreviada," + // 0

					"agencia.codigoAgencia," + // 1

					"debitoAutomatico.identificacaoClienteBanco, " + // 2

					"debitoAutomatico.dataOpcaoDebitoContaCorrente, " + // 3

					"debitoAutomatico.dataInclusaoNovoDebitoAutomatico, " + // 4

					"debitoAutomatico.dataExclusao " + // 5

					"from DebitoAutomatico debitoAutomatico "

					+ "left join debitoAutomatico.agencia agencia "

					+ "left join agencia.banco banco "

					+ "where debitoAutomatico.imovel.id = :idImovel ";

			retorno = session.createQuery(consulta).setInteger("idImovel",

			idImovel.intValue()).list();

			// erro no hibernate

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
	 * Pesquisa a coleção de Faturamento Situação Historico do Imovel [UC0473]
	 * 
	 * Consultar Imovel Dados Complementares
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 11/09/2006
	 * 
	 * @return Description of the Return Value
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Description of the Exception
	 * 
	 */

	public Collection pesquisarFaturamentosSituacaoHistorico(Integer idImovel)

	throws ErroRepositorioException {

		// cria a coleção de retorno

		Collection retorno = null;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		String consulta = null;

		// pesquisa a coleção de atividades e atribui a variável "retorno"

		try {

			consulta = "SELECT faturamentoSituacaoTipo.id,"

					+ // 0

					"faturamentoSituacaoTipo.descricao,"

					+ // 1

					"faturamentoSituacaoMotivo.id,"

					+ // 2

					"faturamentoSituacaoMotivo.descricao,"

					+ // 3

					"faturamentoSituacaoHistorico.anoMesFaturamentoSituacaoInicio, "

					+ // 4

					"faturamentoSituacaoHistorico.anoMesFaturamentoSituacaoFim, "

					+ // 5

					"faturamentoSituacaoHistorico.anoMesFaturamentoRetirada, "

					+ // 6

					"case when (usuario  is null ) then "
					+ "  (case when (usuarioRetirada  is not null ) then"
					+ "   usuarioRetirada.nomeUsuario "
					+ "  else " 
					+ "   usuarioInforma.nomeUsuario "
					+ "  end ) "
					+ "else " 
					+ "usuario.nomeUsuario "
					+ "end, " 
					+ // 7
					
					"faturamentoSituacaoHistorico.id, " // 8
					
					+"faturamentoSituacaoHistorico.dataInclusao " // 9
					
					

					+"from FaturamentoSituacaoHistorico faturamentoSituacaoHistorico "

					+ "left join faturamentoSituacaoHistorico.faturamentoSituacaoTipo faturamentoSituacaoTipo "

					+ "left join faturamentoSituacaoHistorico.faturamentoSituacaoMotivo faturamentoSituacaoMotivo "

					+ "left join faturamentoSituacaoHistorico.usuario usuario "
					
					+ "left join faturamentoSituacaoHistorico.faturamentoSituacaoComandoInforma faturamentoSituacaoComandoInforma "
					
					+ "left join faturamentoSituacaoHistorico.faturamentoSituacaoComandoRetirada faturamentoSituacaoComandoRetirada "
					
					+ "left join faturamentoSituacaoComandoInforma.usuario usuarioInforma "
					
					+ "left join faturamentoSituacaoComandoRetirada.usuario usuarioRetirada "

					+ "where faturamentoSituacaoHistorico.imovel.id = :idImovel ";

			retorno = session.createQuery(consulta).setInteger("idImovel",

			idImovel.intValue()).list();

			// erro no hibernate

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
	 * Pesquisa a coleção de cobranças Situação Historico do Imovel [UC0473]
	 * 
	 * Consultar Imovel Dados Complementares
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 11/09/2006
	 * 
	 * @return Description of the Return Value
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Description of the Exception
	 * 
	 */

	public Collection pesquisarCobrancasSituacaoHistorico(Integer idImovel)

	throws ErroRepositorioException {

		// cria a coleção de retorno

		Collection retorno = null;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		String consulta = null;

		// pesquisa a coleção de atividades e atribui a variável "retorno"

		try {

			consulta = "SELECT cobrancaSituacaoTipo.descricao,"

					+ // 0

					"cobrancaSituacaoMotivo.descricao,"

					+ // 1

					"cobrancaSituacaoHistorico.anoMesCobrancaSituacaoInicio, "

					+ // 2

					"cobrancaSituacaoHistorico.anoMesCobrancaSituacaoFim, "

					+ // 3

					"cobrancaSituacaoHistorico.anoMesCobrancaRetirada, "

					+ // 4

					"usario.nomeUsuario, "

					+ // 5
					
					"cobrancaSituacaoHistorico.id "

					+ // 6

					"from CobrancaSituacaoHistorico cobrancaSituacaoHistorico "

					+ "left join cobrancaSituacaoHistorico.cobrancaSituacaoTipo cobrancaSituacaoTipo "

					+ "left join cobrancaSituacaoHistorico.cobrancaSituacaoMotivo cobrancaSituacaoMotivo "

					+ "left join cobrancaSituacaoHistorico.usuario usario "

					+ "where cobrancaSituacaoHistorico.imovel.id = :idImovel ";

			retorno = session.createQuery(consulta).setInteger("idImovel",

			idImovel.intValue()).list();

			// erro no hibernate

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
	 * Consutlar os Dados de Analise da Medição e Consumo do Imovel [UC0473]
	 * 
	 * Consultar Imóvel
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 12/09/2006
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * @return Collection
	 * 
	 * @throws ControladorException
	 * 
	 */

	public Collection consultarImovelAnaliseMedicaoConsumo(Integer idImovel)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

					    // 0
			consulta = "SELECT ligacaoAguaSituacao.descricao, "
					+ // 1		
					
					" ligacaoEsgotoSituacao.descricao, "
					
					+ // 2
					" imovel.hidrometroInstalacaoHistorico.id, "

					+ // 3

					" ligacaoAgua.hidrometroInstalacaoHistorico.id, "
					
					+ // 4					
					" corteTipo.descricao, "
					
					+ // 5					
					" motivoCorte.descricao, "

					+ // 6					
					" ligacaoAgua.dataCorte, "
					
					+ // 7
					" ligacaoAgua.numeroSeloCorte, "
					
					+ // 8
					" ligacaoAguaSituacao.id, "
					
					+ // 9					
					" supressaoTipo.descricao, "
					
					+ // 10					
					" supressaoMotivo.descricao, "

					+ // 11				
					" ligacaoAgua.dataSupressao, "
					
					+ // 12
					" ligacaoAgua.numeroSeloSupressao, "
					+ // 13
					" imovel.dataSupressaoParcial, "
					+ // 14
					" imovel.indicadorExclusao "
					+
					
					" from Imovel imovel "

					+ "left join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "

					+ "left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "

					+ "left join imovel.ligacaoAgua ligacaoAgua "
					
					+ "left join imovel.ligacaoAgua.corteTipo corteTipo "					
					
					+ "left join imovel.ligacaoAgua.motivoCorte motivoCorte "
					
					+ "left join imovel.ligacaoAgua.supressaoTipo supressaoTipo "
					
					+ "left join imovel.ligacaoAgua.supressaoMotivo supressaoMotivo "

					+ "where imovel.id = :idImovel ";

			retorno = session.createQuery(consulta).setInteger("idImovel",

			idImovel.intValue()).list();

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	// ----------Savio

	public void atualizarImovelLeituraAnormalidade(

	Map<Integer, MedicaoHistorico> mapAtualizarLeituraAnormalidadeImovel,

	Date dataAtual) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection colecaoIdsImoveis = mapAtualizarLeituraAnormalidadeImovel

		.keySet();

		Iterator iteIdsImoveis = colecaoIdsImoveis.iterator();

		try {

			while (iteIdsImoveis.hasNext()) {

				Integer idImovel = (Integer) iteIdsImoveis.next();

				MedicaoHistorico medicaoHistoricoTxt = mapAtualizarLeituraAnormalidadeImovel

				.get(idImovel);

				String atualizarImovel = null;

				Integer idLeituraAnormalidade = null;

				if (medicaoHistoricoTxt.getLeituraAnormalidadeInformada() != null

						&& !medicaoHistoricoTxt

						.getLeituraAnormalidadeInformada().equals("")) {

					idLeituraAnormalidade = medicaoHistoricoTxt

					.getLeituraAnormalidadeInformada().getId();

					atualizarImovel = "update gcom.cadastro.imovel.Imovel "

							+ "set ltan_id = :idLeituraAnormalidade,imov_tmultimaalteracao = :ultimaAlteracao where imov_id = :idImovel";

					session.createQuery(atualizarImovel).setInteger(

					"idLeituraAnormalidade",

					(Integer) idLeituraAnormalidade).setInteger(

					"idImovel", idImovel).setTimestamp(

					"ultimaAlteracao", dataAtual).executeUpdate();

				} else {

					atualizarImovel = "update gcom.cadastro.imovel.Imovel "

							+ "set ltan_id is null,imov_tmultimaalteracao = :ultimaAlteracao where imov_id = :idImovel";

					session.createQuery(atualizarImovel).setInteger("idImovel",

					idImovel)

					.setTimestamp("ultimaAlteracao", dataAtual)

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
	 * 
	 * Consutlar os Dados do Historico de Faturamento [UC0473] Consultar Imóvel
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 13/09/2006
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * @return Collection
	 * 
	 * @throws ControladorException
	 * 
	 */

	public Collection consultarImovelHistoricoFaturamento(Integer idImovel)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "SELECT ligacaoAguaSituacao.descricao, "

					+ // 0

					" ligacaoEsgotoSituacao.descricao, "

					+ // 1
					
					" imovel.indicadorExclusao, "
					
					+ // 2
					
					" ligacaoAguaSituacao.id, "

					+ // 3

					" ligacaoEsgotoSituacao.id " 
					
					+ //4

					"from Imovel imovel "

					+ "left join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "

					+ "left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "

					+ "where imovel.id = :idImovel ";

			retorno = session.createQuery(consulta).setInteger("idImovel",

			idImovel.intValue()).list();

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
	 * Consutlar o cliente usuário do Imovel [UC0473] Consultar Imóvel
	 * 
	 * 
	 * 
	 * @author Bruno Barros, Ivan Sérgio
	 * 
	 * @date 27/04/2007, 21/11/2007
	 * 
	 * @alteracao: Adicionado o ClienteFone
	 * 
	 * @param imovel
	 * 
	 * @return Cliente
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Cliente consultarClienteUsuarioImovel(Imovel imovel)

	throws ErroRepositorioException {

		Cliente resultadoConsultar = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "SELECT cli "

					+ "from ClienteImovel cliimo "

					+ "left join cliimo.cliente cli "
					
					+ "left join fetch cli.clienteFones cliFones "

					+ "left join fetch cli.clienteTipo cliTipo "

					+ "left join fetch cliTipo.esferaPoder esfPoder "

					+ "where cliimo.imovel.id = :idImovel and cliimo.imovel.indicadorExclusao != 1 and "

					+ "cliimo.clienteRelacaoTipo.id = :idClienteUsuario and cliimo.dataFimRelacao is null  ";

			resultadoConsultar = (Cliente) session.createQuery(consulta)

			.setInteger("idImovel", imovel.getId()).setShort(

			"idClienteUsuario",

			ClienteRelacaoTipo.USUARIO.shortValue())

			.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return resultadoConsultar;

	}

	/**
	 * 
	 * Consutlar o cliente usuário do Imovel [UC0473] Consultar Imóvel
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 13/09/2006
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * @return Collection
	 * 
	 * @throws ControladorException
	 * 
	 */

	public String consultarClienteUsuarioImovel(Integer idImovel)

	throws ErroRepositorioException {

		String retorno = null;

		Object resultadoConsultar = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "SELECT cliente.nome "

					+ "from ClienteImovel clienteImovel "

					+ "left join clienteImovel.cliente cliente "

					+ "where clienteImovel.imovel.id = :idImovel and clienteImovel.imovel.indicadorExclusao != 1 and "

					+ "clienteImovel.clienteRelacaoTipo.id = :idClienteUsuario and clienteImovel.dataFimRelacao is null ";

			resultadoConsultar = session.createQuery(consulta).setInteger(

			"idImovel", idImovel.intValue())

			.setShort("idClienteUsuario",

			ClienteRelacaoTipo.USUARIO.shortValue())

			.uniqueResult();

			if (resultadoConsultar != null) {

				retorno = (String) resultadoConsultar;

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

	// Alteração para não carregar contas na situação ERRO_PROCESSAMENTO
	public Collection consultarContasImovel(Integer idImovel)

	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno;

		String consulta;

		try {

			consulta = "select conta.id, "  // 0

					+ "conta.referencia,"  // 1

					+ "conta.dataVencimentoConta," // 2

					+ "conta.valorAgua,"  // 3

					+ "conta.valorEsgoto," // 4

					+ "conta.debitos," // 5

					+ "conta.valorCreditos," // 6 

					+ "debitoCreditoSituacaoAtual.descricaoAbreviada, " // 7 

					+ "contaMotivoRevisao.id, " // 8
					
					+ "debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao , " // 9
					 
					+ "conta.valorImposto " // 10

					+ "from Conta conta "

					+ "left join conta.debitoCreditoSituacaoAtual debitoCreditoSituacaoAtual "

					+ "left join conta.contaMotivoRevisao contaMotivoRevisao "

					+ "where conta.imovel.id = :idImovel order by conta.referencia desc";

			retorno = session.createQuery(consulta).setInteger("idImovel",

			idImovel.intValue()).list();

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
	 * Consutlar as contas Historicos do Imovel [UC0473] Consultar Imóvel
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 13/09/2006
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * @return Collection
	 * 
	 * @throws ControladorException
	 * 
	 */

	public Collection consultarContasHistoricosImovel(Integer idImovel)

	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno;

		String consulta;

		try {

			consulta = "select contaHistorico.id, " // 0

					+ "contaHistorico.anoMesReferenciaConta," // 1

					+ "contaHistorico.dataVencimentoConta," // 2

					+ "contaHistorico.valorAgua," // 3

					+ "contaHistorico.valorEsgoto," // 4

					+ "contaHistorico.valorDebitos," // 5

					+ "contaHistorico.valorCreditos," // 6

					+ "debitoCreditoSituacaoAtual.descricaoAbreviada, " // 7

					+ "contaMotivoRevisao.id, " // 8
					
					+ "debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao,  " // 9
					
					+ "contaHistorico.valorImposto " // 10

					+ "from ContaHistorico contaHistorico "

					+ "left join contaHistorico.debitoCreditoSituacaoAtual debitoCreditoSituacaoAtual "

					+ "left join contaHistorico.contaMotivoRevisao contaMotivoRevisao "

					+ "where contaHistorico.imovel.id = :idImovel" 
					
					+" and contaHistorico.debitoCreditoSituacaoAtual.id <> " + DebitoCreditoSituacao.ERRO_PROCESSAMENTO 
					
					+" order by contaHistorico.anoMesReferenciaConta desc";

			retorno = session.createQuery(consulta).setInteger("idImovel",

			idImovel.intValue()).list();

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
	 * Consultar os dados de parcelamentos do Imovel [UC0473] Consultar Imóvel
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 20/09/2006
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * @return Collection
	 * 
	 * @throws ControladorException
	 * 
	 */

	public Collection consultarParcelamentosDebitosImovel(Integer idImovel)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "SELECT ligacaoAguaSituacao.descricao, "

					+ // 0

					" ligacaoEsgotoSituacao.descricao, " // 1

					+ " imovel.numeroParcelamento, " // 2

					+ " imovel.numeroReparcelamento, " // 3

					+ " imovel.numeroReparcelamentoConsecutivos, " // 4

					+ " imovel.indicadorExclusao " // 5 

					+ "from Imovel imovel "

					+ "left join imovel.ligacaoAguaSituacao ligacaoAguaSituacao "

					+ "left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao "

					+ "where imovel.id = :idImovel ";

			retorno = session.createQuery(consulta).setInteger("idImovel",

			idImovel.intValue()).list();

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
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * 
	 * 
	 * @author Raphael Rossiter
	 * 
	 * @date 27/09/2006
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * @return Collection
	 * 
	 * @throws ControladorException
	 * 
	 */

	public Collection pesquisarClienteUsuarioImovel(Integer idImovel)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "SELECT cliente.id, cliente.nome, cliente.cpf, cliente.cnpj " //0, 1, 2, 3

					+ "from ClienteImovel clienteImovel "

					+ "left join clienteImovel.cliente cliente "

					+ "where clienteImovel.imovel.id = :idImovel and clienteImovel.imovel.indicadorExclusao != 1 and "

					+ "clienteImovel.clienteRelacaoTipo.id = :idClienteUsuario and clienteImovel.dataFimRelacao is null ";

			retorno = (Collection) session.createQuery(consulta).setInteger(

			"idImovel", idImovel.intValue())

			.setShort("idClienteUsuario",

			ClienteRelacaoTipo.USUARIO.shortValue())

			.setMaxResults(1).list();

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
	 * [UCXXXX] Consultar Imóvel
	 * 
	 * @author Rafael Corrêa
	 * @date 22/05/2009
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteUsuarioImovelExcluidoOuNao(Integer idImovel)
		throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "SELECT cliente.id, cliente.nome, cliente.cpf, cliente.cnpj " //0, 1, 2, 3
					+ "from ClienteImovel clienteImovel "
					+ "left join clienteImovel.cliente cliente "
					+ "where clienteImovel.imovel.id = :idImovel and "
					+ "clienteImovel.clienteRelacaoTipo.id = :idClienteUsuario and clienteImovel.dataFimRelacao is null ";

			retorno = (Collection) session.createQuery(consulta).setInteger(
			"idImovel", idImovel.intValue())
			.setShort("idClienteUsuario",
			ClienteRelacaoTipo.USUARIO.shortValue())
			.setMaxResults(1).list();

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
	 * Atualiza apenas os dados (numeroParcelamento,
	 * 
	 * numeroParcelamentoConsecutivo, numeroReparcelamentoConsecutivo) do imóvel
	 * 
	 * 
	 * 
	 * @param imovel
	 * 
	 * parametros para a consulta
	 * 
	 * 
	 * 
	 * Author: Fernanda Paiva
	 * 
	 * 
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Descrição da exceção
	 * 
	 */

	public void atualizarDadosImovel(Integer codigoImovel,

	Integer numeroParcelamento,

	Integer numeroReparcelamentoConsecutivo,

	Integer numeroReparcelamento) throws ErroRepositorioException {

		// Query

		String update;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			update = "update gcom.cadastro.imovel.Imovel set "

					+ "imov_nnparcelamento = :numeroParcelamento, imov_nnreparcelamento = :numeroReparcelamento, "

					+ "imov_nnreparcmtconsec = :numeroReparcelamentoConsecutivo "

					+ "where imov_id = :codigoImovel";

			session.createQuery(update).setInteger("codigoImovel",

			codigoImovel.intValue()).setInteger("numeroParcelamento",

			numeroParcelamento.intValue()).setInteger(

			"numeroReparcelamentoConsecutivo",

			numeroReparcelamentoConsecutivo.intValue()).setInteger(

			"numeroReparcelamento", numeroReparcelamento.intValue())

			.executeUpdate();

			// erro no hibernate

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
	 * Permite Pesquisar as categorias do Imóvel [UC0394] Gerar Débitos a Cobrar
	 * 
	 * de Doações
	 * 
	 * 
	 * 
	 * @author César Araújo
	 * 
	 * @date 10/09/2006
	 * 
	 * @param Imovel
	 * 
	 * imovel - objeto imovel
	 * 
	 * @return Collection<Categoria> - Coleção de categorias
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Collection<Categoria> pesquisarCategoriasImovel(Imovel imovel)

	throws ErroRepositorioException {

		// cria a coleção de retorno

		Collection retorno = null;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		String consulta = null;

		// pesquisa a coleção de categorias e atribui a variável "retorno"

		try {

			consulta = "select "

					+ "  categoria "

					+ "from "

					+ "  ImovelSubcategoria imovelSubcategoria "

					+ "    left join imovelSubcategoria.comp_id.subcategoria subcategoria "

					+ "    left join subcategoria.categoria categoria "

					+ "where "

					+ "  imovelSubcategoria.comp_id.imovel.id = :idImovel ";

			retorno = session.createQuery(consulta).setInteger("idImovel",

			imovel.getId().intValue()).list();

			// erro no hibernate

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
	 * 
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscalização [SB0002] Atualizar
	 * 
	 * Imóvel/Ligação de Água
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @date 14/11/2006
	 * 
	 * @author Sávio Luiz
	 * 
	 * @param imovel
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public void atualizarImovelLigacaoAguaEsgoto(Integer idImovel,

	Integer idLigacaoAguaSituacao, Integer idLigacaoEsgotoSituacao)

	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String update;

		try {

			update = "update gcom.cadastro.imovel.Imovel set "

			+ "imov_tmultimaalteracao = :datahoracorrente";

			if (idLigacaoEsgotoSituacao != null

			&& !idLigacaoEsgotoSituacao.equals("")) {

				update = update + ",lest_id = " + idLigacaoEsgotoSituacao;

			}

			if (idLigacaoAguaSituacao != null

			&& !idLigacaoAguaSituacao.equals("")) {

				update = update + ",last_id = " + idLigacaoAguaSituacao;

			}

			update = update + " where imov_id = :imovelId ";

			session.createQuery(update).setInteger("imovelId", idImovel)

			.setTimestamp("datahoracorrente", new Date())

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
	 * 
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscalização [SB0002] Atualizar
	 * 
	 * Imóvel/Ligação de Água
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @date 14/11/2006
	 * 
	 * @author Sávio Luiz
	 * 
	 * @param imovel
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public void atualizarLigacaoAgua(Integer idLigacaoAgua,

	Integer consumoMinimo, short indiacadorConsumoFixado)

	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String update;

		try {

			update = "update gcom.atendimentopublico.ligacaoagua.LigacaoAgua set "

					+ "lagu_tmultimaalteracao = :datahoracorrente,lagu_dtligacaoagua = :dataAtual ";

			if (indiacadorConsumoFixado == FiscalizacaoSituacaoAgua.INDICADOR_SIM) {

				if (consumoMinimo != null && !consumoMinimo.equals("")) {

					update = update + ",lagu_nnconsumominimoagua = " + consumoMinimo + " ";

				}

			}

			update = update + "where lagu_id = :idLigacaoAgua";

			session.createQuery(update).setInteger("idLigacaoAgua",

			idLigacaoAgua).setTimestamp("datahoracorrente", new Date())

			.setDate("dataAtual", new Date()).executeUpdate();

		} catch (HibernateException e) {

			e.printStackTrace();

			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

	}

	/**
	 * 
	 * 
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscalização [SB0002] Atualizar
	 * 
	 * Imóvel/Ligação de Água
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @date 14/11/2006
	 * 
	 * @author Sávio Luiz
	 * 
	 * @param imovel
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public void atualizarLigacaoEsgoto(Integer idLigacaoEsgoto,

	Integer consumoMinimo, short indicadorVolumeFixado)

	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String update;

		try {

			update = "gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto set "

					+ "lesg_tmultimaalteracao = :datahoracorrente,lesg_dtligacao = :dataAtual ";

			if (indicadorVolumeFixado == FiscalizacaoSituacaoEsgoto.INDICADOR_SIM) {

				if (consumoMinimo != null && !consumoMinimo.equals("")) {

					update = update + ",lesg_nnconsumominimoesgoto = " + consumoMinimo + " ";

				}

			}

			update = update + "where lesg_id = :idLigacaoEsgoto";

			session.createQuery(update).setInteger("idLigacaoEsgoto",

			idLigacaoEsgoto).setTimestamp("datahoracorrente",

			new Date()).setDate("dataAtual", new Date())

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
	 * 
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * 
	 * @date 20/11/2006
	 * 
	 * @author Sávio Luiz
	 * 
	 * @param imovel
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Integer pesquisarConsumoTarifa(Integer idImovel)

	throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta;

		try {

			consulta = "select consumoTarifa.id "

			+ "from gcom.cadastro.imovel.Imovel imovel "

			+ "left join imovel.consumoTarifa consumoTarifa "

			+ "where imovel.id = :imovelId ";

			retorno = (Integer) session.createQuery(consulta).setInteger(

			"imovelId", idImovel).setMaxResults(1).uniqueResult();

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
	 * 
	 * 
	 * Filtrar o Imovel pelos parametros informados
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 24/11/2006
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Collection pesquisarImovel(String idImovel, String idLocalidade,

	String codigoSetorComercial, String numeroQuadra, String lote,

	String subLote, String codigoCliente, String idMunicipio,

	String cep, String idBairro, String idLogradouro,
	
	String numeroImovelInicial, String numeroImovelFinal,

	boolean pesquisarImovelManterVinculo,

	boolean pesquisarImovelCondominio, Integer numeroPagina)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {
			
			consulta = "select distinct logradouro.logr_nmlogradouro as nomeLogradouro, " //+ " logradouro.nome," + // 0
			+ " logradouroTipo.lgtp_dsabreviado as logradouroTipoAbreviado, " //"logradouroTipo.descricaoAbreviada," // 1
			+ " logradouroTitulo.lgtt_dsabreviado as logradouroTituloAbreviado, "//" logradouroTitulo.descricaoAbreviada," // 2
			+ " bairro.bair_id as idBairro, " //"bairro.id," // 3
			+ " bairro.bair_nmBairro as nomeBairro, " //"bairro.nome," // 4
			+ " municipio.muni_id as idMunicipio, " // "municipio.id," // 5
			+ " municipio.muni_nmmunicipio as nomeMunicipio, "//"municipio.nome," // 6
			+ " unidadeFederacao.unfe_id as idUnidadeFederacao, "//"unidadeFederacao.id," // 7
			+ " unidadeFederacao.unfe_dsufsigla as siglaUnidadeFederacao, "//"unidadeFederacao.sigla," // 8
			+ " enderecoReferencia.edrf_dsabreviado as enderecoReferenciaAbreviado, "//"enderecoReferencia.descricaoAbreviada," // 9
			+ " cep.cep_id as idCep, " //"cep.cepId," // 10
			+ " cep.cep_nmlogradouro as nomeLogradouroCep, " // 11
			+ " cep.cep_dslogradourotipo as logradouroTipoCep, " // 12
			+ " cep.cep_nmbairro as bairroCep, " // 13
			+ " cep.cep_nmmunicipio as nomeMunicipioCep, " // 14 
			+ " cep.cep_dsufsigla as siglaUnidadeFederacaoCep, " // 15
			+ " cep.cep_cdcep as codigoCep, " // 16
			
			+ " imovel.imov_nnimovel as numeroImovel, " // "imovel.numeroImovel," // 17
			+ " imovel.imov_dscomplementoendereco as complementoEndereco, " //"imovel.complementoEndereco," // 18
			+ " logradouro.logr_id as idLogradouro, " //"logradouro.id," // 19
			+ " logradouroCep.lgcp_id as idLogradouroCep, "//"logradouroCep.id," // 20
			+ " logradouroBairro.lgbr_id as idLogradouroBairro, " //"logradouroBairro.id," // 21
			+ " logradouroTipo.lgtp_dslogradourotipo as logradouroTipo, " //"logradouroTipo.descricao," // 22
			+ " logradouroTitulo.lgtt_dslogradourotitulo as logradouroTitulo, " //"logradouroTitulo.descricao," // 23
			+ " enderecoReferencia.edrf_dsenderecoreferencia as enderecoReferencia, " //"enderecoReferencia.descricao, " // 24
			+ " imovel.imov_id as idImovel, "; //"imovel.id, " // 25
			
			if (pesquisarImovelManterVinculo) {

				consulta = consulta + " cliente.clie_nmcliente as nomeCliente, "; // 26

			}

			consulta = consulta 
			+ " imovel.imov_nnlote as lote, " //"imovel.lote, " // 27
			+ " imovel.imov_nnsublote as sublote, " //"imovel.subLote, " // 28
			+ " localidade.loca_id as idLocalidade, " //"localidade.id, " // 29
			+ " setorComercial.stcm_cdsetorcomercial as codigoSetorComercial, " //"setorComercial.codigo, " // 30
			+ " quadra.qdra_nnquadra as numeroQuadra, " //"quadra.numeroQuadra, " // 31
			+ " imovel.imov_tmultimaalteracao as ultimaAlteracao, " // 32
			+ " perimetroInicial.logr_id as idPerimetroInicial, " // 33
			+ " perimetroInicial.logr_nmlogradouro as nomePerimetroInicial, " // 34
			+ " logradouroTipoPerimetroInicial.lgtp_dsabreviado as logradouroTipoPerimetroInicial, " // 35
			+ " logradourotituloperimetroinici.lgtt_dsabreviado as logradourotituloperimetroinici, " // 36
			+ " perimetroFinal.logr_id as idPerimetroFinal, " // 37
			+ " perimetroFinal.logr_nmlogradouro as nomePerimetroFinal, " // 38
			+ " logradouroTipoPerimetroFinal.lgtp_dsabreviado as logradouroTipoPerimetroFinal, " // 39
			+ " logradouroTituloPerimetroFinal.lgtt_dsabreviado as logradouroTituloPerimetroFinal " // 40

			+ " from cadastro.cliente_imovel clienteImovel "
					/*
					 * ## JOIN ##
					 * 
					 */

					// join necessários
			
			+ "inner join cadastro.imovel imovel "
			+ "on imovel.imov_id = clienteImovel.imov_id "

			+ "inner join cadastro.quadra quadra "
			+ "on quadra.qdra_id = imovel.qdra_id "

			+ "inner join cadastro.localidade localidade "
			+ "on localidade.loca_id = imovel.loca_id "

			+ "inner join cadastro.setor_comercial setorComercial "
			+ "on setorComercial.stcm_id = imovel.stcm_id "

			+ "left join cadastro.logradouro_cep logradouroCep "
			+ "on logradouroCep.lgcp_id = imovel.lgcp_id "

			+ "left join cadastro.cep cep "
			+ "on cep.cep_id = logradouroCep.cep_id "

			+ "left join cadastro.logradouro logradouro "
			+ "on logradouro.logr_id = logradouroCep.logr_id "

			+ "left join cadastro.logradouro_tipo logradouroTipo "
			+ "on logradouroTipo.lgtp_id = logradouro.lgtp_id "

			+ "left join cadastro.logradouro_titulo logradouroTitulo "
			+ "on logradouroTitulo.lgtt_id = logradouro.lgtt_id "

			+ "left join cadastro.logradouro_bairro logradouroBairro "
			+ "on logradouroBairro.lgbr_id = imovel.lgbr_id "

			+ "left join cadastro.bairro bairro "
			+ "on bairro.bair_id = logradouroBairro.bair_id "

			+ "left join cadastro.municipio municipio "
			+ "on municipio.muni_id = bairro.muni_id "

			+ "left join cadastro.unidade_federacao unidadeFederacao "
			+ "on unidadeFederacao.unfe_id = municipio.unfe_id "

			+ "left join cadastro.endereco_referencia enderecoReferencia "
			+ "on enderecoReferencia.edrf_id = imovel.edrf_id "
			
			+ "left join cadastro.logradouro perimetroInicial "
			+ "on perimetroInicial.logr_id = imovel.logr_idinicioperimetro "
			
			+ "left join cadastro.logradouro_tipo logradouroTipoPerimetroInicial "
			+ "on logradouroTipoPerimetroInicial.lgtp_id = perimetroInicial.lgtp_id "
			
			+ "left join cadastro.logradouro_titulo logradourotituloperimetroinici "
			+ "on logradourotituloperimetroinici.lgtt_id = perimetroInicial.lgtt_id "
			
			+ "left join cadastro.logradouro perimetroFinal "
			+ "on perimetroFinal.logr_id = imovel.logr_idfimperimetro "
			
			+ "left join cadastro.logradouro_tipo logradouroTipoPerimetroFinal "
			+ "on logradouroTipoPerimetroFinal.lgtp_id = perimetroFinal.lgtp_id "
			
			+ "left join cadastro.logradouro_titulo logradouroTituloPerimetroFinal "
			+ "on logradouroTituloPerimetroFinal.lgtt_id = perimetroFinal.lgtt_id ";
			
			// cliente

			if (pesquisarImovelManterVinculo) {

				consulta = consulta
						+ " left outer join cadastro.cliente_imovel clienteImovelUsuario "
						+ " on imovel.imov_id=clienteImovelUsuario.imov_id and "
						+ " clienteImovelUsuario.crtp_id = "+ClienteRelacaoTipo.USUARIO.toString()
						+ " and clienteImovelUsuario.clim_dtrelacaofim is null "
						+ " left outer join cadastro.cliente cliente "
						+ " on cliente.clie_id = clienteImovelUsuario.clie_id ";
				
//						+ " left outer join imovel.clienteImoveis clienteImoveisUsuario with 
//						+ "(clienteImoveisUsuario.clienteRelacaoTipo.id = "
//						+ ClienteRelacaoTipo.USUARIO.toString()
//						+ ") and clienteImoveisUsuario.dataFimRelacao is null "
//						+ " left outer join clienteImoveisUsuario.cliente cliente ";

			} else {

				consulta = consulta
				+ " left outer join cadastro.cliente cliente "
				+ " on cliente.clie_id = clienteImovel.clie_id ";
				
//				+ " left join clienteImovel.cliente cliente ";

				// + " left join clienteImoveis.clienteRelacaoTipo

				// clienteRelacaoTipo "

				// + " left join clienteImoveis.cliente cliente ";

			}

			/*
			 * ## CONDIÇÕES ##
			 * 
			 */

			consulta = consulta + " where imovel.imov_icexclusao <> "

			+ Imovel.IMOVEL_EXCLUIDO + " and  ";
			
			// verifica se o cliente foi desvinculado do imovel
			
			consulta = consulta + " clienteImovel.clim_dtrelacaofim is null and ";

			// pesquisar imovel condominio

			if (pesquisarImovelCondominio) {

				consulta = consulta + " imovel.imov_icimovelcondominio = "

				+ Imovel.IMOVEL_CONDOMINIO + "  and  ";

			}

			// imovel

			if (idImovel != null

			&& !idImovel.equals("")

			&& !idImovel.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " imovel.imov_id = :idImovel and  ";

			}

			// localidade

			if ((idLocalidade != null && !idLocalidade.equals("") && !idLocalidade

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta + " localidade.loca_id = :idLocalidade  and  ";

			}

			// setor comercial

			if ((codigoSetorComercial != null

			&& !codigoSetorComercial.equals("") && !codigoSetorComercial

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta

				+ " setorComercial.stcm_cdsetorcomercial = :codigoSetorComercial  and  ";

			}

			// quadra

			if ((numeroQuadra != null && !numeroQuadra.equals("") && !numeroQuadra

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta

				+ " quadra.qdra_nnquadra = :numeroQuadra and  ";

			}

			// lote

			if ((lote != null && !lote.equals("") && !lote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta + " imovel.imov_nnlote = :lote  and  ";

			}

			// sublote

			if ((subLote != null && !subLote.equals("") && !subLote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta + " imovel.imov_nnsublote = :subLote  and  ";

			}

			// cliente

			if (codigoCliente != null

			&& !codigoCliente.equals("")

			&& !codigoCliente.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " cliente.clie_id = :codigoCliente  and  ";

			}

			// municipio

			if (idMunicipio != null

			&& !idMunicipio.equals("")

			&& !idMunicipio.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " municipio.muni_id = :idMunicipio  and  ";

			}

			// cep

			if (cep != null

			&& !cep.equals("")

			&& !cep.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " cep.cep_cdcep = :cep  and  ";

			}

			// bairro

			if (idBairro != null

			&& !idBairro.equals("")

			&& !idBairro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " bairro.bair_cdbairro = :idBairro  and  ";

			}

			// logradouro

			if (idLogradouro != null

			&& !idLogradouro.equals("")

			&& !idLogradouro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " logradouro.logr_id = :idLogradouro  and  ";

			}
			
			if (numeroImovelInicial != null && !numeroImovelInicial.trim().equals("")) {
				
				consulta = consulta + 
				"RTRIM(LTRIM(translate(imovel.imov_nnimovel, '" + ConstantesSistema.CARACTERES_ALFANUMERICOS +"', ''))) <> '' and " 
				+ "to_number(RTRIM(LTRIM(translate(imovel.imov_nnimovel, '" + ConstantesSistema.CARACTERES_ALFANUMERICOS +"', ''))), 99999) between '" 
				+ numeroImovelInicial.trim() +  "' and '" + numeroImovelFinal.toString() + "' and ";
			}
			
			
			consulta = consulta.substring(0, (consulta.length() - 5));
			
			consulta = consulta + " order by localidade.loca_id, setorComercial.stcm_cdsetorcomercial, " +
					              " quadra.qdra_nnquadra, imovel.imov_nnlote , imovel.imov_nnsublote  ";
			
			Query query = null;
			if (pesquisarImovelManterVinculo) {
				query = session.createSQLQuery(consulta)
						.addScalar("nomeLogradouro", Hibernate.STRING) // 0
						.addScalar("logradouroTipoAbreviado", Hibernate.STRING) // 1
						.addScalar("logradouroTituloAbreviado", Hibernate.STRING) // 2
						.addScalar("idBairro", Hibernate.INTEGER) // 3
						.addScalar("nomeBairro", Hibernate.STRING) // 4
						.addScalar("idMunicipio", Hibernate.INTEGER) // 5
						.addScalar("nomeMunicipio",	Hibernate.STRING) // 6
						.addScalar("idUnidadeFederacao", Hibernate.INTEGER) // 7
						.addScalar("siglaUnidadeFederacao",	Hibernate.STRING) // 8 
						.addScalar("enderecoReferenciaAbreviado", Hibernate.STRING) // 9
						.addScalar("idCep", Hibernate.INTEGER) // 10
						.addScalar("nomeLogradouroCep", Hibernate.STRING) // 11
						.addScalar("logradouroTipoCep", Hibernate.STRING) // 12
						.addScalar("bairroCep", Hibernate.STRING) // 13
						.addScalar("nomeMunicipioCep", Hibernate.STRING) // 14
						.addScalar("siglaUnidadeFederacaoCep", Hibernate.STRING) // 15
						.addScalar("codigoCep", Hibernate.INTEGER) // 16
						.addScalar("numeroImovel", Hibernate.STRING) // 17
						.addScalar("complementoEndereco", Hibernate.STRING) // 18
						.addScalar("idLogradouro", Hibernate.INTEGER) // 19
						.addScalar("idLogradouroCep", Hibernate.INTEGER) // 20
						.addScalar("idLogradouroBairro", Hibernate.INTEGER) // 21
						.addScalar("logradouroTipo", Hibernate.STRING) // 22
						.addScalar("logradouroTitulo", Hibernate.STRING) // 23
						.addScalar("enderecoReferencia", Hibernate.STRING) // 24
						.addScalar("idImovel", Hibernate.INTEGER) // 25
						.addScalar("nomeCliente", Hibernate.STRING) // 26 
						.addScalar("lote", Hibernate.SHORT) // 27 
						.addScalar("sublote", Hibernate.SHORT) //28
						.addScalar("idLocalidade", Hibernate.INTEGER) // 29
						.addScalar("codigoSetorComercial", Hibernate.INTEGER) // 30
						.addScalar("numeroQuadra", Hibernate.INTEGER) // 31
						.addScalar("ultimaAlteracao", Hibernate.TIMESTAMP) // 32
						.addScalar("idPerimetroInicial", Hibernate.INTEGER) // 33
						.addScalar("nomePerimetroInicial", Hibernate.STRING) // 34
						.addScalar("logradouroTipoPerimetroInicial", Hibernate.STRING) // 35
						.addScalar("logradourotituloperimetroinici", Hibernate.STRING) // 36
						.addScalar("idPerimetroFinal", Hibernate.INTEGER) // 37
						.addScalar("nomePerimetroFinal", Hibernate.STRING) // 38
						.addScalar("logradouroTipoPerimetroFinal", Hibernate.STRING) // 39
						.addScalar("logradouroTituloPerimetroFinal", Hibernate.STRING);  // 40
			} else {
			
			query = session.createSQLQuery(consulta)
					.addScalar("nomeLogradouro", Hibernate.STRING) // 0
					.addScalar("logradouroTipoAbreviado", Hibernate.STRING) // 1
					.addScalar("logradouroTituloAbreviado", Hibernate.STRING) // 2
					.addScalar("idBairro", Hibernate.INTEGER) // 3
					.addScalar("nomeBairro", Hibernate.STRING) // 4
					.addScalar("idMunicipio", Hibernate.INTEGER) // 5
					.addScalar("nomeMunicipio",	Hibernate.STRING) // 6
					.addScalar("idUnidadeFederacao", Hibernate.INTEGER) // 7
					.addScalar("siglaUnidadeFederacao",	Hibernate.STRING) // 8 
					.addScalar("enderecoReferenciaAbreviado", Hibernate.STRING) // 9
					.addScalar("idCep", Hibernate.INTEGER) // 10
					.addScalar("nomeLogradouroCep", Hibernate.STRING) // 11
					.addScalar("logradouroTipoCep", Hibernate.STRING) // 12
					.addScalar("bairroCep", Hibernate.STRING) // 13
					.addScalar("nomeMunicipioCep", Hibernate.STRING) // 14
					.addScalar("siglaUnidadeFederacaoCep", Hibernate.STRING) // 15
					.addScalar("codigoCep", Hibernate.INTEGER) // 16
					.addScalar("numeroImovel", Hibernate.STRING) // 17
					.addScalar("complementoEndereco", Hibernate.STRING) // 18
					.addScalar("idLogradouro", Hibernate.INTEGER) // 19
					.addScalar("idLogradouroCep", Hibernate.INTEGER) // 20
					.addScalar("idLogradouroBairro", Hibernate.INTEGER) // 21
					.addScalar("logradouroTipo", Hibernate.STRING) // 22
					.addScalar("logradouroTitulo", Hibernate.STRING) // 23
					.addScalar("enderecoReferencia", Hibernate.STRING) // 24
					.addScalar("idImovel", Hibernate.INTEGER) // 25
					.addScalar("lote", Hibernate.SHORT) // 26 
					.addScalar("sublote", Hibernate.SHORT) //27
					.addScalar("idLocalidade", Hibernate.INTEGER) // 28
					.addScalar("codigoSetorComercial", Hibernate.INTEGER) // 29
					.addScalar("numeroQuadra", Hibernate.INTEGER) // 30
					.addScalar("ultimaAlteracao", Hibernate.TIMESTAMP) // 31
					.addScalar("idPerimetroInicial", Hibernate.INTEGER) // 32
					.addScalar("nomePerimetroInicial", Hibernate.STRING) // 33
					.addScalar("logradouroTipoPerimetroInicial", Hibernate.STRING) // 34
					.addScalar("logradourotituloperimetroinici", Hibernate.STRING) // 35
					.addScalar("idPerimetroFinal", Hibernate.INTEGER) // 36
					.addScalar("nomePerimetroFinal", Hibernate.STRING) // 37
					.addScalar("logradouroTipoPerimetroFinal", Hibernate.STRING) // 38
					.addScalar("logradouroTituloPerimetroFinal", Hibernate.STRING);  // 39
			
			}
			
//			Query query = session.createSQLQuery(consulta.substring(0, (consulta
//
//			.length() - 5)));

			// seta os valores na condição where

			// imovel principal

			if (idImovel != null

			&& !idImovel.equals("")

			&& !idImovel.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idImovel", new Integer(idImovel).intValue());

			}

			// localidade

			if ((idLocalidade != null && !idLocalidade.equals("") && !idLocalidade

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("idLocalidade", new Integer(idLocalidade)

				.intValue());

			}

			// setor

			if ((codigoSetorComercial != null

			&& !codigoSetorComercial.equals("") && !codigoSetorComercial

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("codigoSetorComercial", new Integer(

				codigoSetorComercial).intValue());

			}

			// quadra

			if ((numeroQuadra != null && !numeroQuadra.equals("") && !numeroQuadra

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("numeroQuadra", new Integer(numeroQuadra)

				.intValue());

			}

			// lote

			if ((lote != null && !lote.equals("") && !lote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("lote", new Integer(lote).intValue());

			}

			// subLote

			if ((subLote != null && !subLote.equals("") && !subLote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("subLote", new Integer(subLote).intValue());

			}

			// cliente

			if (codigoCliente != null

			&& !codigoCliente.equals("")

			&& !codigoCliente.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("codigoCliente", new Integer(codigoCliente)

				.intValue());

			}

			// municipio

			if (idMunicipio != null

			&& !idMunicipio.equals("")

			&& !idMunicipio.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idMunicipio", new Integer(idMunicipio)

				.intValue());

			}

			// cep

			if (cep != null

			&& !cep.equals("")

			&& !cep.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("cep", new Integer(cep).intValue());

			}

			// bairro

			if (idBairro != null

			&& !idBairro.equals("")

			&& !idBairro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idBairro", new Integer(idBairro).intValue());

			}

			// logradouro

			if (idLogradouro != null

			&& !idLogradouro.equals("")

			&& !idLogradouro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idLogradouro", new Integer(idLogradouro)

				.intValue());

			}

			retorno = query.setFirstResult(10 * numeroPagina).setMaxResults(10)

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
	 * 
	 * 
	 * Filtrar o Imovel pelos parametros informados, para saber a quantidade de
	 * 
	 * imoveis
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 24/11/2006
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Object pesquisarQuantidadeImovel(String idImovel, 

	String idLocalidade, String codigoSetorComercial,

	String numeroQuadra, String lote, String subLote,

	String codigoCliente, String idMunicipio, String cep,

	String idBairro, String idLogradouro, String numeroImovelInicial, String numeroImovelFinal,

	boolean pesquisarImovelManterVinculo,

	boolean pesquisarImovelCondominio) throws ErroRepositorioException {

		Object retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "select count(distinct imovel.imov_id) as qtd "

			+ "from cadastro.cliente_imovel clienteImovel ";

			/*
			 * ## JOIN ##
			 * 
			 */

			consulta = consulta + " left outer join cadastro.imovel imovel "
			+ "on imovel.imov_id = clienteImovel.imov_id "
			+ "left outer join cadastro.logradouro_cep logradouroCep "
			+ "on logradouroCep.lgcp_id = imovel.lgcp_id "
			+ "left join cadastro.logradouro_bairro logradouroBairro "
			+ "on logradouroBairro.lgbr_id = imovel.lgbr_id "
			+ "left join cadastro.bairro bairro "
			+ "on bairro.bair_id = logradouroBairro.bair_id ";

			// join facultativos

			// join necessários

			// cep

			if (cep != null

			&& !cep.equals("")

			&& !cep.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta

				+ "left join cadastro.cep cep "
				+ "on cep.cep_id = logradouroCep.cep_id ";

			}

			// logradouro

			if (idLogradouro != null

			&& !idLogradouro.equals("")

			&& !idLogradouro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta


				+ "left join cadastro.logradouro logradouro "
				+ "on logradouro.logr_id = logradouroCep.logr_id ";

			}

			// municipio

			if (idMunicipio != null

			&& !idMunicipio.equals("")

			&& !idMunicipio.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta

				+ "left join cadastro.municipio municipio "
				+ "on municipio.muni_id = bairro.muni_id ";

			}

			// localidade

			if ((idLocalidade != null && !idLocalidade.equals("") && !idLocalidade

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta

				+ "inner join cadastro.localidade localidade "
				+ "on localidade.loca_id = imovel.loca_id ";

			}

			// setor comercial

			if ((codigoSetorComercial != null

			&& !codigoSetorComercial.equals("") && !codigoSetorComercial

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta

				+ "inner join cadastro.setor_comercial setorComercial "
				+ "on setorComercial.stcm_id = imovel.stcm_id ";

			}

			// quadra

			if ((numeroQuadra != null && !numeroQuadra.equals("") && !numeroQuadra

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta + "inner join cadastro.quadra quadra "
				+ "on quadra.qdra_id = imovel.qdra_id ";

			}

			// cliente

			if (codigoCliente != null

			&& !codigoCliente.equals("")

			&& !codigoCliente.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				if (pesquisarImovelManterVinculo) {

					consulta = consulta

							+ " left outer join cadastro.cliente_imovel clienteImovelUsuario "
							+ " on clienteImovelUsuario.crtp_id = "
							+ ClienteRelacaoTipo.USUARIO.toString()
							+ " and clienteImovelUsuario.clim_dtrelacaofim is null "
							+ " left outer join cadastro.cliente cliente "
							+ " on cliente.clie_id = clienteImovelUsuario.clie_id ";
					
//							+ " left outer join imovel.clienteImoveis clienteImoveisUsuario with "
	//
//							+ "(clienteImoveisUsuario.clienteRelacaoTipo.id = "
	//
//							+ ClienteRelacaoTipo.USUARIO.toString()
	//
//							+ ") and clienteImoveisUsuario.dataFimRelacao is null "
	//
//							+ " left outer join clienteImoveisUsuario.cliente cliente ";

				} else {

					consulta = consulta
					+ " left outer join cadastro.cliente cliente "
					+ " on cliente.clie_id = clienteImovel.clie_id ";
					
//					+ " left join clienteImovel.cliente cliente ";

					// + " left join clienteImoveis.clienteRelacaoTipo

					// clienteRelacaoTipo "

					// + " left join clienteImoveis.cliente cliente ";

				}

			}

			/*
			 * ## CONDIÇÕES ##
			 * 
			 */

			consulta = consulta

					+ " where clienteImovel.clim_dtrelacaofim is null and imovel.imov_icexclusao <> "

					+ Imovel.IMOVEL_EXCLUIDO + " and  ";

			// pesquisar imovel condominio

			if (pesquisarImovelCondominio) {

				consulta = consulta + " imovel.imov_icimovelcondominio = "

				+ Imovel.IMOVEL_CONDOMINIO + "  and  ";

			}

			// imovel

			if (idImovel != null

			&& !idImovel.equals("")

			&& !idImovel.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " imovel.imov_id = :idImovel and  ";

			}

			// localidade

			if ((idLocalidade != null && !idLocalidade.equals("") && !idLocalidade

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta + " localidade.loca_id = :idLocalidade  and  ";

			}

			// setor comercial

			if ((codigoSetorComercial != null

			&& !codigoSetorComercial.equals("") && !codigoSetorComercial

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta

				+ " setorComercial.stcm_cdsetorcomercial = :codigoSetorComercial  and  ";

			}

			// quadra

			if ((numeroQuadra != null && !numeroQuadra.equals("") && !numeroQuadra

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta

				+ " quadra.qdra_nnquadra = :numeroQuadra and  ";

			}

			// lote

			if ((lote != null && !lote.equals("") && !lote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta + " imovel.imov_nnlote = :lote  and  ";

			}

			// sublote

			if ((subLote != null && !subLote.equals("") && !subLote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta + " imovel.imov_nnsublote = :subLote  and  ";

			}
			//
			if (numeroImovelInicial != null && !numeroImovelInicial.trim().equals("")) {
				
				consulta = consulta + 
						"RTRIM(LTRIM(translate(imovel.imov_nnimovel, '" + ConstantesSistema.CARACTERES_ALFANUMERICOS +"', ''))) <> '' and " 
						+ "to_number(RTRIM(LTRIM(translate(imovel.imov_nnimovel, '" + ConstantesSistema.CARACTERES_ALFANUMERICOS +"', ''))), 99999) between '" 
						+ numeroImovelInicial.trim() +  "' and '" + numeroImovelFinal.toString() + "' and ";
			}

			// cliente

			if (codigoCliente != null

			&& !codigoCliente.equals("")

			&& !codigoCliente.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " cliente.clie_id = :codigoCliente  and  ";

			}

			// municipio

			if (idMunicipio != null

			&& !idMunicipio.equals("")

			&& !idMunicipio.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " municipio.muni_id = :idMunicipio  and  ";

			}

			// cep

			if (cep != null

			&& !cep.equals("")

			&& !cep.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " cep.cep_cdcep = :cep  and  ";

			}

			// bairro

			if (idBairro != null

			&& !idBairro.equals("")

			&& !idBairro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " bairro.bair_cdbairro = :idBairro  and  ";

			}

			// logradouro

			if (idLogradouro != null

			&& !idLogradouro.equals("")

			&& !idLogradouro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " logradouro.logr_id = :idLogradouro  and  ";

			}

			Query query = session.createSQLQuery(consulta.substring(0, (consulta

			.length() - 5))).addScalar("qtd", Hibernate.INTEGER);

			// seta os valores na condição where

			// imovel principal

			if (idImovel != null

			&& !idImovel.equals("")

			&& !idImovel.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {
				
				query.setInteger("idImovel", new Integer(idImovel).intValue());

			}

			// localidade

			if ((idLocalidade != null && !idLocalidade.equals("") && !idLocalidade

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("idLocalidade", new Integer(idLocalidade)

				.intValue());

			}

			// setor

			if ((codigoSetorComercial != null

			&& !codigoSetorComercial.equals("") && !codigoSetorComercial

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("codigoSetorComercial", new Integer(

				codigoSetorComercial).intValue());

			}

			// quadra

			if ((numeroQuadra != null && !numeroQuadra.equals("") && !numeroQuadra

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("numeroQuadra", new Integer(numeroQuadra)

				.intValue());

			}

			// lote

			if ((lote != null && !lote.equals("") && !lote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("lote", new Integer(lote).intValue());

			}

			// subLote

			if ((subLote != null && !subLote.equals("") && !subLote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("subLote", new Integer(subLote).intValue());

			}

			// cliente

			if (codigoCliente != null

			&& !codigoCliente.equals("")

			&& !codigoCliente.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("codigoCliente", new Integer(codigoCliente)

				.intValue());

			}

			// municipio

			if (idMunicipio != null

			&& !idMunicipio.equals("")

			&& !idMunicipio.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idMunicipio", new Integer(idMunicipio)

				.intValue());

			}

			// cep

			if (cep != null

			&& !cep.equals("")

			&& !cep.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("cep", new Integer(cep).intValue());

			}

			// bairro

			if (idBairro != null

			&& !idBairro.equals("")

			&& !idBairro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idBairro", new Integer(idBairro).intValue());

			}

			// logradouro

			if (idLogradouro != null

			&& !idLogradouro.equals("")

			&& !idLogradouro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idLogradouro", new Integer(idLogradouro)

				.intValue());

			}

			/**
			* autor: Adriana Muniz
			* data: 17/09/2012
			* tratamento de exceção, caso seja exceção devido a conversão de alfanumerico para numerico */
			try {
				retorno = query.uniqueResult();
			}catch (DataException ex) {
				//caso seja exceção devido a conversão de alfanumerico para numerico
				//levanta a exceção para a próxima camada
				throw new ErroRepositorioException(ex, ex.getSQLState());
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
	 * 
	 * 
	 * Usado pelo Pesquisar Imovel Retorno o Imovel, com o Nome do Cliente,
	 * 
	 * Matricula e Endereço
	 * 
	 * 
	 * 
	 * @author Rafael Santos, Ivan Sergio
	 * 
	 * @date 27/11/2006, 26/02/2010
	 * 
	 * @alteracao: 26/02/2010 - CRC3755 - Adicionado a condicao de apenas clientes com relacao do tipo USUARIO;
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Collection pesquisarImovelInscricaoNew(String idImovel,

	String idLocalidade, String codigoSetorComercial,

	String numeroQuadra, String lote, String subLote,

	String codigoCliente, String idMunicipio, String cep,

	String idBairro, String idLogradouro, String numeroImovelInicial, String numeroImovelFinal,

	boolean pesquisarImovelCondominio, Integer numeroPagina)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "select distinct logradouro.logr_nmlogradouro as nomeLogradouro, "//" logradouro.nome," // 0
					+ " logradouroTipo.lgtp_dsabreviado as logradouroTipoAbreviado, " //"logradouroTipo.descricaoAbreviada," // 1
					+ " logradouroTitulo.lgtt_dsabreviado as logradouroTituloAbreviado, "//" logradouroTitulo.descricaoAbreviada," // 2
					+ " bairro.bair_id as idBairro, " //"bairro.id," // 3
					+ " bairro.bair_nmBairro as nomeBairro, " //"bairro.nome," // 4
					+ " municipio.muni_id as idMunicipio, " // "municipio.id," // 5
					+ " municipio.muni_nmmunicipio as nomeMunicipio, "//"municipio.nome," // 6
					+ " unidadeFederacao.unfe_id as idUnidadeFederacao, "//"unidadeFederacao.id," // 7
					+ " unidadeFederacao.unfe_dsufsigla as siglaUnidadeFederacao, "//"unidadeFederacao.sigla," // 8
					+ " enderecoReferencia.edrf_dsabreviado as enderecoReferenciaAbreviado, "//"enderecoReferencia.descricaoAbreviada," // 9
					+ " cep.cep_id as idCep, " //"cep.cepId," // 10
					+ " cep.cep_nmlogradouro as nomeLogradouroCep, " // 11
					+ " cep.cep_dslogradourotipo as logradouroTipoCep, " // 12
					+ " cep.cep_nmbairro as bairroCep, " // 13
					+ " cep.cep_nmmunicipio as nomeMunicipioCep, " // 14 
					+ " cep.cep_dsufsigla as siglaUnidadeFederacaoCep, " // 15
					+ " cep.cep_cdcep as codigoCep, " // 16
//					+ " cep.logradouro," // 11
//					+ " cep.descricaoTipoLogradouro," // 12
//					+ " cep.bairro," // 13
//					+ " cep.municipio," // 14
//					+ " cep.sigla, " // 15
//					+ " cep.codigo, " // 16
					+ " imovel.imov_nnimovel as numeroImovel, " // "imovel.numeroImovel," // 17
					+ " imovel.imov_dscomplementoendereco as complementoEndereco, " //"imovel.complementoEndereco," // 18
					+ " logradouro.logr_id as idLogradouro, " //"logradouro.id," // 19
					+ " logradouroCep.lgcp_id as idLogradouroCep, "//"logradouroCep.id," // 20
					+ " logradouroBairro.lgbr_id as idLogradouroBairro, " //"logradouroBairro.id," // 21
					+ " logradouroTipo.lgtp_dslogradourotipo as logradouroTipo, " //"logradouroTipo.descricao," // 22
					+ " logradouroTitulo.lgtt_dslogradourotitulo as logradouroTitulo, " //"logradouroTitulo.descricao," // 23
					+ " enderecoReferencia.edrf_dsenderecoreferencia as enderecoReferencia, " //"enderecoReferencia.descricao, " // 24
					+ " imovel.imov_id as idImovel, " //"imovel.id, " // 25
					+ " imovel.imov_nnlote as lote, " //"imovel.lote, " // 26
					+ " imovel.imov_nnsublote as sublote, " //"imovel.subLote, " // 27
					+ " localidade.loca_id as idLocalidade, " //"localidade.id, " // 28
					+ " setorComercial.stcm_cdsetorcomercial as codigoSetorComercial, " //"setorComercial.codigo, " // 29
					+ " quadra.qdra_nnquadra as numeroQuadra, " //"quadra.numeroQuadra, " // 30
					+ " clienteUsuario.clie_nmcliente as nomeUsuario, " //"clienteUsuario.nome " // 31
					+ " perimetroInicial.logr_id as idPerimetroInicial, " // 32
					+ " perimetroInicial.logr_nmlogradouro as nomePerimetroInicial, " // 33
					+ " logradouroTipoPerimetroInicial.lgtp_dsabreviado as logradouroTipoPerimetroInicial, " // 34
					+ " logradourotituloperimetroinici.lgtt_dsabreviado as logradourotituloperimetroinici, " // 35
					+ " perimetroFinal.logr_id as idPerimetroFinal, " // 36
					+ " perimetroFinal.logr_nmlogradouro as nomePerimetroFinal, " // 37
					+ " logradouroTipoPerimetroFinal.lgtp_dsabreviado as logradouroTipoPerimetroFinal, " // 38
					+ " logradouroTituloPerimetroFinal.lgtt_dsabreviado as logradouroTituloPerimetroFinal " // 39
					+ " from cadastro.cliente_imovel clienteImovel "

					/*
					 * ## JOIN ##
					 * 
					 */

					// join necessários
					+ "inner join cadastro.imovel imovel "
					+ "on imovel.imov_id = clienteImovel.imov_id "

					+ "inner join cadastro.quadra quadra "
					+ "on quadra.qdra_id = imovel.qdra_id "

					+ "inner join cadastro.localidade localidade "
					+ "on localidade.loca_id = imovel.loca_id "

					+ "inner join cadastro.setor_comercial setorComercial "
					+ "on setorComercial.stcm_id = imovel.stcm_id "

					+ "left join cadastro.logradouro_cep logradouroCep "
					+ "on logradouroCep.lgcp_id = imovel.lgcp_id "

					+ "left join cadastro.cep cep "
					+ "on cep.cep_id = logradouroCep.cep_id "

					+ "left join cadastro.logradouro logradouro "
					+ "on logradouro.logr_id = logradouroCep.logr_id "

					+ "left join cadastro.logradouro_tipo logradouroTipo "
					+ "on logradouroTipo.lgtp_id = logradouro.lgtp_id "

					+ "left join cadastro.logradouro_titulo logradouroTitulo "
					+ "on logradouroTitulo.lgtt_id = logradouro.lgtt_id "

					+ "left join cadastro.logradouro_bairro logradouroBairro "
					+ "on logradouroBairro.lgbr_id = imovel.lgbr_id "

					+ "left join cadastro.bairro bairro "
					+ "on bairro.bair_id = logradouroBairro.bair_id "

					+ "left join cadastro.municipio municipio "
					+ "on municipio.muni_id = bairro.muni_id "

					+ "left join cadastro.unidade_federacao unidadeFederacao "
					+ "on unidadeFederacao.unfe_id = municipio.unfe_id "

					+ "left join cadastro.endereco_referencia enderecoReferencia "
					+ "on enderecoReferencia.edrf_id = imovel.edrf_id "
					
					+ "left join cadastro.logradouro perimetroInicial "
					+ "on perimetroInicial.logr_id = imovel.logr_idinicioperimetro "
					
					+ "left join cadastro.logradouro_tipo logradouroTipoPerimetroInicial "
					+ "on logradouroTipoPerimetroInicial.lgtp_id = perimetroInicial.lgtp_id "
					
					+ "left join cadastro.logradouro_titulo logradourotituloperimetroinici "
					+ "on logradourotituloperimetroinici.lgtt_id = perimetroInicial.lgtt_id "
					
					+ "left join cadastro.logradouro perimetroFinal "
					+ "on perimetroFinal.logr_id = imovel.logr_idfimperimetro "
					
					+ "left join cadastro.logradouro_tipo logradouroTipoPerimetroFinal "
					+ "on logradouroTipoPerimetroFinal.lgtp_id = perimetroFinal.lgtp_id "
					
					+ "left join cadastro.logradouro_titulo logradouroTituloPerimetroFinal "
					+ "on logradouroTituloPerimetroFinal.lgtt_id = perimetroFinal.lgtt_id "

					+ "inner join cadastro.cliente_relacao_tipo clienteRelacaoTipo "
					+ "on clienteRelacaoTipo.crtp_id = clienteImovel.crtp_id "
					//	CRC 3928 -- Retirado por Rômulo Aurélio a pedido de Aryed Lins 05/03/2010
					//+ "and clienteImovel.crtp_id = " + ClienteRelacaoTipo.USUARIO + " "
					// Fim da Alteracao CRC 3928
					+ "inner join cadastro.cliente clienteUsuario "
					+ "on clienteUsuario.clie_id = clienteImovel.clie_id ";

			// join facultativos

			// cep

//			if (cep != null
//
//			&& !cep.equals("")
//
//			&& !cep.trim().equalsIgnoreCase(
//
//			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
//
//			.toString())) {
//
//				consulta = consulta
//
//				+ " left join imovel.logradouroCep logradouroCep "
//
//				+ "left join logradouroCep.cep cep ";
//
//			}

			// bairro

//			if (idBairro != null
//
//			&& !idBairro.equals("")
//
//			&& !idBairro.trim().equalsIgnoreCase(
//
//			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
//
//			.toString())) {
//
//				consulta = consulta
//
//				+ " left join imovel.logradouroBairro logradouroBairro "
//
//				+ " left join logradouroBairro.bairro bairro ";
//
//			}

			// logradouro

//			if (idLogradouro != null
//
//			&& !idLogradouro.equals("")
//
//			&& !idLogradouro.trim().equalsIgnoreCase(
//
//			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
//
//			.toString())) {
//
//				consulta = consulta
//
//				+ " left join imovel.logradouroCep logradouroCep "
//
//				+ " left join logradouroCep.logradouro logradouro ";
//
//			}

			// municipio

//			if (idMunicipio != null
//
//			&& !idMunicipio.equals("")
//
//			&& !idMunicipio.trim().equalsIgnoreCase(
//
//			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
//
//			.toString())) {
//
//				consulta = consulta
//
//				+ " left join imovel.logradouroBairro logradouroBairro "
//
//				+ " left join logradouroBairro.bairro bairro "
//
//				+ "left join bairro.municipio municipio ";
//
//			}

			/*
			 * ## CONDIÇÕES ##
			 * 
			 */

			consulta = consulta

			+ " where clienteImovel.clim_dtrelacaofim is null "

			+ " and imovel.imov_icexclusao <> "

			+ Imovel.IMOVEL_EXCLUIDO + " and  ";

			// pesquisar imovel condominio

			if (pesquisarImovelCondominio) {

				consulta = consulta + " imovel.imov_icimovelcondominio = "

				+ Imovel.IMOVEL_CONDOMINIO + "  and  ";

			}

			// imovel

			if (idImovel != null

			&& !idImovel.equals("")

			&& !idImovel.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " imovel.imov_id = :idImovel  and  ";

			}

			// localidade

			if ((idLocalidade != null && !idLocalidade.equals("") && !idLocalidade

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta + " localidade.loca_id = :idLocalidade  and  ";

			}

			// setor comercial

			if ((codigoSetorComercial != null

			&& !codigoSetorComercial.equals("") && !codigoSetorComercial

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta

				+ " setorComercial.stcm_cdsetorcomercial = :codigoSetorComercial  and  ";

			}

			// quadra

			if ((numeroQuadra != null && !numeroQuadra.equals("") && !numeroQuadra

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta

				+ " quadra.qdra_nnquadra = :numeroQuadra and  ";

			}

			// lote

			if ((lote != null && !lote.equals("") && !lote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta + " imovel.imov_nnlote = :lote  and  ";

			}

			// sublote

			if ((subLote != null && !subLote.equals("") && !subLote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta + " imovel.imov_nnsublote = :subLote  and  ";

			}
			
			
			
			if (numeroImovelInicial != null && !numeroImovelInicial.trim().equals("")
					&& numeroImovelFinal != null && !numeroImovelFinal.trim().equals("")) {
				consulta += " RTRIM(LTRIM(translate(imovel.imov_nnimovel, '" + ConstantesSistema.CARACTERES_ALFANUMERICOS + "', ''))) <> '' and ";
				
				consulta += " to_number(RTRIM(LTRIM(translate(imovel.imov_nnimovel, '" + ConstantesSistema.CARACTERES_ALFANUMERICOS + "', ''))), 99999) between '" + numeroImovelInicial.trim() + "' and '" + numeroImovelFinal.trim() + "' and ";
			}

			// cliente

			if (codigoCliente != null

			&& !codigoCliente.equals("")

			&& !codigoCliente.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta

				+ " clienteUsuario.clie_id = :codigoCliente  and  ";

			}

			// municipio

			if (idMunicipio != null

			&& !idMunicipio.equals("")

			&& !idMunicipio.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " municipio.muni_id = :idMunicipio  and  ";

			}

			// cep

			if (cep != null

			&& !cep.equals("")

			&& !cep.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " cep.cep_cdcep = :cep  and  ";

			}

			// bairro

			if (idBairro != null

			&& !idBairro.equals("")

			&& !idBairro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " bairro.bair_cdbairro = :idBairro  and  ";

			}

			// logradouro

			if (idLogradouro != null

			&& !idLogradouro.equals("")

			&& !idLogradouro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " logradouro.logr_id = :idLogradouro  and  ";

			}

			Query query = session.createSQLQuery(
					consulta.substring(0, (consulta.length() - 5)))
					.addScalar("nomeLogradouro", Hibernate.STRING) // 0
					.addScalar("logradouroTipoAbreviado", Hibernate.STRING) // 1
					.addScalar("logradouroTituloAbreviado", Hibernate.STRING) // 2
					.addScalar("idBairro", Hibernate.INTEGER) // 3
					.addScalar("nomeBairro", Hibernate.STRING) // 4
					.addScalar("idMunicipio", Hibernate.INTEGER) // 5
					.addScalar("nomeMunicipio",	Hibernate.STRING) // 6
					.addScalar("idUnidadeFederacao", Hibernate.INTEGER) // 7
					.addScalar("siglaUnidadeFederacao",	Hibernate.STRING) // 8 
					.addScalar("enderecoReferenciaAbreviado", Hibernate.STRING) // 9
					.addScalar("idCep", Hibernate.INTEGER) // 10
					.addScalar("nomeLogradouroCep", Hibernate.STRING) // 11
					.addScalar("logradouroTipoCep", Hibernate.STRING) // 12
					.addScalar("bairroCep", Hibernate.STRING) // 13
					.addScalar("nomeMunicipioCep", Hibernate.STRING) // 14
					.addScalar("siglaUnidadeFederacaoCep", Hibernate.STRING) // 15
					.addScalar("codigoCep", Hibernate.INTEGER) // 16
					.addScalar("numeroImovel", Hibernate.STRING) // 17
					.addScalar("complementoEndereco", Hibernate.STRING) // 18
					.addScalar("idLogradouro", Hibernate.INTEGER) // 19
					.addScalar("idLogradouroCep", Hibernate.INTEGER) // 20
					.addScalar("idLogradouroBairro", Hibernate.INTEGER) // 21
					.addScalar("logradouroTipo", Hibernate.STRING) // 22
					.addScalar("logradouroTitulo", Hibernate.STRING) // 23
					.addScalar("enderecoReferencia", Hibernate.STRING) // 24
					.addScalar("idImovel", Hibernate.INTEGER) // 25
					.addScalar("lote", Hibernate.SHORT) // 26 
					.addScalar("sublote", Hibernate.SHORT) //27
					.addScalar("idLocalidade", Hibernate.INTEGER) // 28
					.addScalar("codigoSetorComercial", Hibernate.INTEGER) // 29
					.addScalar("numeroQuadra", Hibernate.INTEGER) // 30
					.addScalar("nomeUsuario", Hibernate.STRING) // 31
					.addScalar("idPerimetroInicial", Hibernate.INTEGER) // 32
					.addScalar("nomePerimetroInicial", Hibernate.STRING) // 33
					.addScalar("logradouroTipoPerimetroInicial", Hibernate.STRING) // 34
					.addScalar("logradourotituloperimetroinici", Hibernate.STRING) // 35
					.addScalar("idPerimetroFinal", Hibernate.INTEGER) // 36
					.addScalar("nomePerimetroFinal", Hibernate.STRING) // 37
					.addScalar("logradouroTipoPerimetroFinal", Hibernate.STRING) // 38
					.addScalar("logradouroTituloPerimetroFinal", Hibernate.STRING);  // 39
			
			// seta os valores na condição where

			// imovel principal

			if (idImovel != null

			&& !idImovel.equals("")

			&& !idImovel.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idImovel", new Integer(idImovel).intValue());

			}

			// localidade

			if ((idLocalidade != null && !idLocalidade.equals("") && !idLocalidade

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("idLocalidade", new Integer(idLocalidade)

				.intValue());

			}

			// setor

			if ((codigoSetorComercial != null

			&& !codigoSetorComercial.equals("") && !codigoSetorComercial

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("codigoSetorComercial", new Integer(

				codigoSetorComercial).intValue());

			}

			// quadra

			if ((numeroQuadra != null && !numeroQuadra.equals("") && !numeroQuadra

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("numeroQuadra", new Integer(numeroQuadra)

				.intValue());

			}

			// lote

			if ((lote != null && !lote.equals("") && !lote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("lote", new Integer(lote).intValue());

			}

			// subLote

			if ((subLote != null && !subLote.equals("") && !subLote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("subLote", new Integer(subLote).intValue());

			}

			// cliente

			if (codigoCliente != null

			&& !codigoCliente.equals("")

			&& !codigoCliente.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("codigoCliente", new Integer(codigoCliente)

				.intValue());

			}

			// municipio

			if (idMunicipio != null

			&& !idMunicipio.equals("")

			&& !idMunicipio.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idMunicipio", new Integer(idMunicipio)

				.intValue());

			}

			// cep

			if (cep != null

			&& !cep.equals("")

			&& !cep.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("cep", new Integer(cep).intValue());

			}

			// bairro

			if (idBairro != null

			&& !idBairro.equals("")

			&& !idBairro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idBairro", new Integer(idBairro).intValue());

			}

			// logradouro

			if (idLogradouro != null

			&& !idLogradouro.equals("")

			&& !idLogradouro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idLogradouro", new Integer(idLogradouro)

				.intValue());

			}

			try{
				retorno = query.setFirstResult(10 * numeroPagina).setMaxResults(10)
				.list();
			}catch (DataException ex) {
				//caso seja exceção devido a conversão de alfanumerico para numerico
				//levanta a exceção para a próxima camada
				throw new ErroRepositorioException(ex, ex.getSQLState());
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
	 * 
	 * 
	 * Usado pelo Pesquisar Imovel Retorno o Imovel, com o Nome do Cliente,
	 * 
	 * Matricula e Endereço
	 * 
	 * 
	 * 
	 * @author Rafael Santos
	 * 
	 * @date 27/11/2006
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Collection pesquisarImovelInscricao(String idImovel,

	String idLocalidade, String codigoSetorComercial,

	String numeroQuadra, String lote, String subLote,

	String codigoCliente, String idMunicipio, String cep,

	String idBairro, String idLogradouro,

	boolean pesquisarImovelCondominio, Integer numeroPagina)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "select distinct logradouro.nome,"

					+ // 0

					" logradouroTipo.descricaoAbreviada,"

					+ // 1

					" logradouroTitulo.descricaoAbreviada,"

					+ // 2

					" bairro.id,"

					+ // 3

					" bairro.nome,"

					+ // 4

					" municipio.id,"

					+ // 5

					" municipio.nome,"

					+ // 6

					" unidadeFederacao.id,"

					+ // 7

					" unidadeFederacao.sigla,"

					+ // 8

					" enderecoReferencia.descricaoAbreviada,"

					+ // 9

					" cep.cepId,"

					+ // 10

					" cep.logradouro,"

					+ // 11

					" cep.descricaoTipoLogradouro,"

					+ // 12

					" cep.bairro,"

					+ // 13

					" cep.municipio,"

					+ // 14

					" cep.sigla, "

					+ // 15

					" cep.codigo, "

					+ // 16

					" imovel.numeroImovel,"

					+ // 17

					" imovel.complementoEndereco,"

					+ // 18

					" logradouro.id,"

					+ // 19

					" logradouroCep.id,"

					+ // 20

					" logradouroBairro.id,"

					+ // 21

					" logradouroTipo.descricao,"

					+ // 22

					" logradouroTitulo.descricao,"

					+ // 23

					" enderecoReferencia.descricao, "

					+ // 24

					" imovel.id, "

					+ // 25

					" imovel.lote, "

					+ // 26

					" imovel.subLote, "

					+ // 27

					" localidade.id, "

					+ // 28

					" setorComercial.codigo, "

					+ // 29

					" quadra.numeroQuadra, "

					+ // 30

					" clienteUsuario.nome " // 31

					+ "from ClienteImovel clienteImovel "

					/*
					 * ## JOIN ##
					 * 
					 */

					// join necessários
					+ "left join clienteImovel.imovel imovel "

					+ "inner join imovel.quadra quadra "

					+ "inner join imovel.localidade localidade "

					+ "inner join imovel.setorComercial setorComercial "

					+ "left join imovel.logradouroCep logradouroCep "

					+ "left join logradouroCep.cep cep "

					+ "left join logradouroCep.logradouro logradouro "

					+ "left join logradouro.logradouroTipo logradouroTipo "

					+ "left join logradouro.logradouroTitulo logradouroTitulo "

					+ "left join imovel.logradouroBairro logradouroBairro "

					+ "left join logradouroBairro.bairro bairro "

					+ "left join bairro.municipio municipio "

					+ "left join municipio.unidadeFederacao unidadeFederacao "

					+ "left join imovel.enderecoReferencia enderecoReferencia "

					+ "inner join clienteImovel.clienteRelacaoTipo clienteRelacaoTipo "

					+ "inner join clienteImovel.cliente clienteUsuario ";

			// join facultativos

			// cep

			if (cep != null

			&& !cep.equals("")

			&& !cep.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta

				+ " left join imovel.logradouroCep logradouroCep "

				+ "left join logradouroCep.cep cep ";

			}

			// bairro

			if (idBairro != null

			&& !idBairro.equals("")

			&& !idBairro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta

				+ " left join imovel.logradouroBairro logradouroBairro "

				+ " left join logradouroBairro.bairro bairro ";

			}

			// logradouro

			if (idLogradouro != null

			&& !idLogradouro.equals("")

			&& !idLogradouro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta

				+ " left join imovel.logradouroCep logradouroCep "

				+ " left join logradouroCep.logradouro logradouro ";

			}

			// municipio

			if (idMunicipio != null

			&& !idMunicipio.equals("")

			&& !idMunicipio.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta

				+ " left join imovel.logradouroBairro logradouroBairro "

				+ " left join logradouroBairro.bairro bairro "

				+ "left join bairro.municipio municipio ";

			}

			/*
			 * ## CONDIÇÕES ##
			 * 
			 */

			consulta = consulta + " where " + "clienteRelacaoTipo.id = "

			+ ClienteRelacaoTipo.USUARIO

			+ " and clienteImovel.dataFimRelacao is null "

			+ " and imovel.indicadorExclusao != "

			+ Imovel.IMOVEL_EXCLUIDO + " and  ";

			// pesquisar imovel condominio

			if (pesquisarImovelCondominio) {

				consulta = consulta + " imovel.indicadorImovelCondominio = "

				+ Imovel.IMOVEL_CONDOMINIO + "  and  ";

			}

			// imovel

			if (idImovel != null

			&& !idImovel.equals("")

			&& !idImovel.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " imovel.id = :idImovel  and  ";

			}

			// localidade

			if ((idLocalidade != null && !idLocalidade.equals("") && !idLocalidade

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta + " localidade.id = :idLocalidade  and  ";

			}

			// setor comercial

			if ((codigoSetorComercial != null

			&& !codigoSetorComercial.equals("") && !codigoSetorComercial

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta

				+ " setorComercial.codigo = :codigoSetorComercial  and  ";

			}

			// quadra

			if ((numeroQuadra != null && !numeroQuadra.equals("") && !numeroQuadra

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta

				+ " quadra.numeroQuadra = :numeroQuadra and  ";

			}

			// lote

			if ((lote != null && !lote.equals("") && !lote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta + " imovel.lote = :lote  and  ";

			}

			// sublote

			if ((subLote != null && !subLote.equals("") && !subLote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				consulta = consulta + " imovel.subLote = :subLote  and  ";

			}

			// cliente

			if (codigoCliente != null

			&& !codigoCliente.equals("")

			&& !codigoCliente.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta

				+ " clienteUsuario.id = :codigoCliente  and  ";

			}

			// municipio

			if (idMunicipio != null

			&& !idMunicipio.equals("")

			&& !idMunicipio.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " municipio.id = :idMunicipio  and  ";

			}

			// cep

			if (cep != null

			&& !cep.equals("")

			&& !cep.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " cep.codigo = :cep  and  ";

			}

			// bairro

			if (idBairro != null

			&& !idBairro.equals("")

			&& !idBairro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " bairro.codigo = :idBairro  and  ";

			}

			// logradouro

			if (idLogradouro != null

			&& !idLogradouro.equals("")

			&& !idLogradouro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " logradouro.id = :idLogradouro  and  ";

			}

			Query query = session.createQuery(consulta.substring(0, (consulta

			.length() - 5)));

			// seta os valores na condição where

			// imovel principal

			if (idImovel != null

			&& !idImovel.equals("")

			&& !idImovel.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idImovel", new Integer(idImovel).intValue());

			}

			// localidade

			if ((idLocalidade != null && !idLocalidade.equals("") && !idLocalidade

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("idLocalidade", new Integer(idLocalidade)

				.intValue());

			}

			// setor

			if ((codigoSetorComercial != null

			&& !codigoSetorComercial.equals("") && !codigoSetorComercial

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("codigoSetorComercial", new Integer(

				codigoSetorComercial).intValue());

			}

			// quadra

			if ((numeroQuadra != null && !numeroQuadra.equals("") && !numeroQuadra

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("numeroQuadra", new Integer(numeroQuadra)

				.intValue());

			}

			// lote

			if ((lote != null && !lote.equals("") && !lote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("lote", new Integer(lote).intValue());

			}

			// subLote

			if ((subLote != null && !subLote.equals("") && !subLote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString()))) {

				query.setInteger("subLote", new Integer(subLote).intValue());

			}

			// cliente

			if (codigoCliente != null

			&& !codigoCliente.equals("")

			&& !codigoCliente.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("codigoCliente", new Integer(codigoCliente)

				.intValue());

			}

			// municipio

			if (idMunicipio != null

			&& !idMunicipio.equals("")

			&& !idMunicipio.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idMunicipio", new Integer(idMunicipio)

				.intValue());

			}

			// cep

			if (cep != null

			&& !cep.equals("")

			&& !cep.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("cep", new Integer(cep).intValue());

			}

			// bairro

			if (idBairro != null

			&& !idBairro.equals("")

			&& !idBairro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idBairro", new Integer(idBairro).intValue());

			}

			// logradouro

			if (idLogradouro != null

			&& !idLogradouro.equals("")

			&& !idLogradouro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idLogradouro", new Integer(idLogradouro)

				.intValue());

			}

			retorno = query.setFirstResult(10 * numeroPagina).setMaxResults(10)

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
	 * [UC0367] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * 
	 * Recupera o id da situação da ligação de esgoto
	 * 
	 * 
	 * 
	 * @author Sávio Luiz
	 * 
	 * @date 04/12/2006
	 * 
	 * 
	 * 
	 * @param idOS
	 * 
	 * @return OrdemServico
	 * 
	 * @throws ControladorException
	 * 
	 */

	public Integer pesquisaridLigacaoEsgotoSituacao(Integer idImovel)

	throws ErroRepositorioException {

		// cria a coleção de retorno

		Integer retorno = null;

		// Query

		String consulta;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			// pesquisa a coleção de atividades e atribui a variável "retorno"

			consulta = "SELECT ligEsgSit.id "

			+ "FROM gcom.cadastro.imovel.Imovel  imovel "

			+ "INNER JOIN imovel.ligacaoEsgotoSituacao ligEsgSit "

			+ "WHERE imovel.id = :idImovel";

			retorno = (Integer) session.createQuery(consulta).setInteger(

			"idImovel", idImovel).setMaxResults(1).uniqueResult();

			// erro no hibernate

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
	 * [UC0367] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * 
	 * Recupera o id da situação da ligacao de agua
	 * 
	 * 
	 * 
	 * @author Sávio Luiz
	 * 
	 * @date 04/12/2006
	 * 
	 * 
	 * 
	 * @param idOS
	 * 
	 * @return OrdemServico
	 * 
	 * @throws ControladorException
	 * 
	 */

	public Integer pesquisaridLigacaoAguaSituacao(Integer idImovel)

	throws ErroRepositorioException {

		// cria a coleção de retorno

		Integer retorno = null;

		// Query

		String consulta;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			// pesquisa a coleção de atividades e atribui a variável "retorno"

			consulta = "SELECT ligAguaSit.id "

			+ "FROM gcom.cadastro.imovel.Imovel  imovel "

			+ "INNER JOIN imovel.ligacaoAguaSituacao ligAguaSit "

			+ "WHERE imovel.id = :idImovel";

			retorno = (Integer) session.createQuery(consulta).setInteger(

			"idImovel", idImovel).setMaxResults(1).uniqueResult();

			// erro no hibernate

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
	 * [UC0367] Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * 
	 * Recupera o id da situação da ligacao de agua
	 * 
	 * 
	 * 
	 * @author Sávio Luiz
	 * 
	 * @date 04/12/2006
	 * 
	 * 
	 * 
	 * @param idOS
	 * 
	 * @return OrdemServico
	 * 
	 * @throws ControladorException
	 * 
	 */

	public Integer pesquisarTarifaImovel(Integer idImovel)

	throws ErroRepositorioException {

		// cria a coleção de retorno

		Integer retorno = null;

		// Query

		String consulta;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			consulta = "SELECT consTarifa.id "

			+ "FROM gcom.cadastro.imovel.Imovel  imovel "

			+ "INNER JOIN imovel.consumoTarifa consTarifa "

			+ "WHERE imovel.id = :idImovel";

			retorno = (Integer) session.createQuery(consulta).setInteger(

			"idImovel", idImovel).setMaxResults(1).uniqueResult();

			// erro no hibernate

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
	 * Pesquisa os imóveis do cliente de acordo com o tipo de relação
	 * 
	 * 
	 * 
	 * [UC0251] Gerar Atividade de Ação de Cobrança [SB0001] Gerar Atividade de
	 * 
	 * Ação de Cobrança para os Imóveis do Cliente
	 * 
	 * 
	 * 
	 * @author Leonardo Vieira
	 * 
	 * @created 12/12/2006
	 * 
	 * 
	 * 
	 * @param cliente
	 * 
	 * @param relacaoClienteImovel
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Collection pesquisarImoveisClientesRelacao(Cliente cliente,

	ClienteRelacaoTipo relacaoClienteImovel,Integer numeroInicial)

	throws ErroRepositorioException {

		// cria a coleção de retorno

		Collection retorno = null;

		// Query

		String consulta;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			String stringRelacaoClienteImovel = "";

			if (relacaoClienteImovel != null

			&& relacaoClienteImovel.getId() != null) {

				stringRelacaoClienteImovel = " and crt.id = "

				+ relacaoClienteImovel.getId();

			}

			consulta = "select distinct im.id, rt.id, "

			+ "im.ligacaoAguaSituacao.id, " // 2

					+ "im.ligacaoEsgotoSituacao.id, " // 3

					+ "im.imovelPerfil.id, " // 4

					+ "rt.empresa.id, " // 5

					+ "im.localidade.id, " // 6

					+ "scm.codigo, " // 7

					+ "qd.numeroQuadra, " // 8

					+ "im.lote, " // 9

					+ "im.subLote, " // 10

					+ "qd.id, " // 11

					+ "im.cobrancaSituacaoTipo.id, " // 12

					+ "im.indicadorDebitoConta, " // 13

					+ "rt.empresaCobranca.id " // 14

					+ "from ClienteImovel ci " + "inner join ci.imovel im "

					+ "inner join ci.cliente cl "

					+ "inner join ci.clienteRelacaoTipo crt "

					+ "inner join im.quadra qd " + "inner join qd.rota rt "

					+ "inner join im.setorComercial scm "

					+ "where cl.id = :idCliente and ci.dataFimRelacao is null " + stringRelacaoClienteImovel
					
					+ " order by im.id";

			retorno = (Collection) session.createQuery(consulta).setInteger(

			"idCliente", cliente.getId()).setFirstResult(numeroInicial)

			.setMaxResults(500).list();

			// erro no hibernate

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

	public Integer pesquisarExistenciaImovelSubCategoria(Integer idImovel,

	Integer idCategoria) throws ErroRepositorioException {

		// cria a coleção de retorno

		Integer retorno = null;

		// Query

		String consulta;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			consulta = "select imovelSubcategoria.comp_id.imovel.id "

					+ "from ImovelSubcategoria imovelSubcategoria "

					+ "inner join imovelSubcategoria.comp_id.subcategoria subcategoria "

					+ "inner join subcategoria.categoria categoria "

					+ "where imovelSubcategoria.comp_id.imovel.id = :idImovel "

					+ "and categoria.id = :idCategoria ";

			retorno = (Integer) session.createQuery(consulta).setInteger(

			"idImovel", idImovel)

			.setInteger("idCategoria", idCategoria).setMaxResults(1)

			.uniqueResult();

			// erro no hibernate

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

	public Collection pesquisarImoveisPorRota(Integer idRota)

	throws ErroRepositorioException {

		// cria a coleção de retorno

		Collection retorno = null;

		// Query

		String consulta;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			consulta = "select im.id," // 0

					+ "im.ligacaoAguaSituacao.id, " // 1

					+ "im.ligacaoEsgotoSituacao.id, " // 2

					+ "im.imovelPerfil.id, " // 3

					+ "rt.empresa.id, " // 4

					+ "im.localidade.id, " // 5

					+ "im.setorComercial.codigo, " // 6

					+ "im.quadra.numeroQuadra, " // 7

					+ "im.lote, " // 8

					+ "im.subLote, " // 9

					+ "im.quadra.id " // 10

					+ "from Imovel im " + "inner join im.quadra.rota rt "

					+ "where rt.id = :idRota ";

			retorno = (Collection) session.createQuery(consulta).setInteger(

			"idRota", idRota).list();

			// erro no hibernate

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

	public Collection pesquisarImoveisPorRotaComPaginacaoSemRotaAlternativa(Rota rota,
			Collection idsSituacaoLigacaoAgua, Collection idsSituacaoLigacaoEsgoto,
			Integer numeroInicial, int numeroMaximo) throws ErroRepositorioException { 

		
		Integer idRota = rota.getId();
		
		// cria a coleção de retorno

		Collection retorno = null;

		// Query

		String consulta;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			consulta = "select im.id," // 0

					+ "im.ligacaoAguaSituacao.id, " // 1

					+ "im.ligacaoEsgotoSituacao.id, " // 2

					+ "im.imovelPerfil.id, " // 3

					+ "rt.empresa.id, " // 4

					+ "im.localidade.id, " // 5

					+ "scm.codigo, " // 6

					+ "im.quadra.numeroQuadra, " // 7

					+ "im.lote, " // 8

					+ "im.subLote, " // 9

					+ "im.quadra.id, " // 10

					+ "im.cobrancaSituacaoTipo.id, " // 11

					+ "im.indicadorDebitoConta, " // 12

					+ "rt.empresaCobranca.id " // 13

					+ "from Imovel im " + "inner join im.quadra.rota rt "

					+ "inner join im.setorComercial scm "

					+ "where rt.id = :idRota " +
							"and im.indicadorExclusao = " + ConstantesSistema.INDICADOR_IMOVEL_ATIVO;
			
			consulta += " and im.rotaAlternativa IS NULL ";
			
			if (idsSituacaoLigacaoAgua != null && !idsSituacaoLigacaoAgua.isEmpty()){
				consulta += " and im.ligacaoAguaSituacao.id in (:idsLast)  ";	
			}
			if (idsSituacaoLigacaoEsgoto != null && !idsSituacaoLigacaoEsgoto.isEmpty()){
				consulta += " and im.ligacaoEsgotoSituacao.id in (:idsLest)  " ;	
			}

			Query q = session.createQuery(consulta).setInteger("idRota", idRota);
			if (idsSituacaoLigacaoAgua != null && !idsSituacaoLigacaoAgua.isEmpty()){
				q.setParameterList("idsLast", idsSituacaoLigacaoAgua);	
			}
			if (idsSituacaoLigacaoEsgoto != null && !idsSituacaoLigacaoEsgoto.isEmpty()){
				q.setParameterList("idsLest", idsSituacaoLigacaoEsgoto);	
			}
			
			retorno = (Collection) q
				.setFirstResult(numeroInicial)
				.setMaxResults(numeroMaximo).list();

			// erro no hibernate

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
	
	public Collection pesquisarImoveisPorRotaComPaginacaoComRotaAlternativa(Rota rota,
			Collection idsSituacaoLigacaoAgua, Collection idsSituacaoLigacaoEsgoto,
			Integer numeroInicial, int numeroMaximo) throws ErroRepositorioException { 

		
		Integer idRota = rota.getId();
		
		// cria a coleção de retorno

		Collection retorno = null;

		// Query

		String consulta;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			consulta = "select im.id," // 0

					+ "im.ligacaoAguaSituacao.id, " // 1

					+ "im.ligacaoEsgotoSituacao.id, " // 2

					+ "im.imovelPerfil.id, " // 3

					+ "rt.empresa.id, " // 4

					+ "im.localidade.id, " // 5

					+ "scm.codigo, " // 6

					+ "im.quadra.numeroQuadra, " // 7

					+ "im.lote, " // 8

					+ "im.subLote, " // 9

					+ "im.quadra.id, " // 10

					+ "im.cobrancaSituacaoTipo.id, " // 11

					+ "im.indicadorDebitoConta, " // 12

					+ "rt.empresaCobranca.id " // 13

					+ "from Imovel im " + "inner join im.quadra.rota rt "

					+ "inner join im.setorComercial scm "

					+ "where im.rotaAlternativa.id = :idRota " +
							"and im.indicadorExclusao = " + ConstantesSistema.INDICADOR_IMOVEL_ATIVO;

			
			if (idsSituacaoLigacaoAgua != null && !idsSituacaoLigacaoAgua.isEmpty()){
				consulta += " and im.ligacaoAguaSituacao.id in (:idsLast)  ";	
			}
			if (idsSituacaoLigacaoEsgoto != null && !idsSituacaoLigacaoEsgoto.isEmpty()){
				consulta += " and im.ligacaoEsgotoSituacao.id in (:idsLest)  " ;	
			}

			Query q = session.createQuery(consulta).setInteger("idRota", idRota);
			if (idsSituacaoLigacaoAgua != null && !idsSituacaoLigacaoAgua.isEmpty()){
				q.setParameterList("idsLast", idsSituacaoLigacaoAgua);	
			}
			if (idsSituacaoLigacaoEsgoto != null && !idsSituacaoLigacaoEsgoto.isEmpty()){
				q.setParameterList("idsLest", idsSituacaoLigacaoEsgoto);	
			}
			
			retorno = (Collection) q
				.setFirstResult(numeroInicial)
				.setMaxResults(numeroMaximo).list();

			// erro no hibernate

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
	 * 
	 * 
	 * [UC0978] Associar Tarifa de Consumo a Imóveis
	 * 
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * 
	 * @created 19/12/2006
	 * 
	 * 
	 * 
	 * @param idLocalidadeInicial,
	 * 
	 * idLocalidadeFinal, codigoSetorComercialInicial,
	 * 
	 * codigoSetorComercialFinal, quadraInicial, quadraFinal,
	 * 
	 * loteInicial, loteFinal, subLoteInicial, subLoteFinal,
	 * 
	 * idTarifaAnterior
	 * 
	 * 
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public String criarCondicionaisImovelTarifaConsumo(

	String idLocalidadeInicial, String idLocalidadeFinal,

	String codigoSetorComercialInicial,

	String codigoSetorComercialFinal, String quadraInicial,

	String quadraFinal, String loteInicial, String loteFinal,

	String subLoteInicial, String subLoteFinal, String idTarifaAnterior) {

		String hql = " WHERE ";

		if (idLocalidadeInicial != null && !idLocalidadeInicial.equals("")) {

			hql = hql + " loc.id >= " + idLocalidadeInicial + " and ";

		}

		if (idLocalidadeFinal != null && !idLocalidadeFinal.equals("")) {

			hql = hql + " loc.id <= " + idLocalidadeFinal + " and ";

		}

		if (codigoSetorComercialInicial != null

		&& !codigoSetorComercialInicial.equals("")) {

			hql = hql + " sc.codigo >= " + codigoSetorComercialInicial

			+ " and ";

		}

		if (codigoSetorComercialFinal != null

		&& !codigoSetorComercialFinal.equals("")) {

			hql = hql + " sc.codigo <= " + codigoSetorComercialFinal + " and ";

		}

		if (quadraInicial != null && !quadraInicial.equals("")) {

			hql = hql + " quadra.id >= " + quadraInicial + " and ";

		}

		if (quadraFinal != null && !quadraFinal.equals("")) {

			hql = hql + " quadra.id >= " + quadraFinal + " and ";

		}

		if (loteInicial != null && !loteInicial.equals("")) {

			hql = hql + " imov.lote >= " + loteInicial + " and ";

		}

		if (loteFinal != null && !loteFinal.equals("")) {

			hql = hql + " imov.lote >= " + loteFinal + " and ";

		}

		if (subLoteInicial != null && !subLoteInicial.equals("")) {

			hql = hql + " imov.subLote >= " + subLoteInicial + " and ";

		}

		if (subLoteFinal != null && !subLoteFinal.equals("")) {

			hql = hql + " imov.subLote >= " + subLoteFinal + " and ";

		}

		if (idTarifaAnterior != null && !idTarifaAnterior.equals("")) {

			hql = hql + " tarifaConsumo = " + idTarifaAnterior;

		}

		return hql;

	}

	public Collection pesquisarImoveisTarifaConsumo(String idLocalidadeInicial,

	String idLocalidadeFinal, String codigoSetorComercialInicial,

	String codigoSetorComercialFinal, String quadraInicial,

	String quadraFinal, String loteInicial, String loteFinal,

	String subLoteInicial, String subLoteFinal, String idTarifaAnterior)

	throws ErroRepositorioException {

		// cria a coleção de retorno

		Collection retorno = null;

		// Query

		String consulta = "";

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		String condicionais = this.criarCondicionaisImovelTarifaConsumo(

		idLocalidadeInicial, idLocalidadeFinal,

		codigoSetorComercialInicial, codigoSetorComercialFinal,

		quadraInicial, quadraFinal, loteInicial, loteFinal,

		subLoteInicial, subLoteFinal, idTarifaAnterior);

		try {

			consulta = "SELECT imov.id " + " FROM Imovel imov "

			+ " INNER JOIN imov.localidade loc "

			+ " INNER JOIN imov.setorComercial sc "

			+ " INNER JOIN imov.quadra quadra "

			+ " INNER JOIN imov.consumoTarifa tarifaConsumo "

			+ condicionais;

			retorno = (Collection) session.createQuery(consulta).list();

			// erro no hibernate

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
	 * Atualiza a tarifa de consumo de um ou mais imoveis
	 * 
	 * 
	 * 
	 * [UC0378] Associar Tarifa de Consumo a Imóveis
	 * 
	 * 
	 * 
	 * @author Rômulo Aurelio
	 * 
	 * @created 19/12/2006
	 * 
	 * 
	 * 
	 * @param matricula,
	 * 
	 * tarifaAtual, colecaoImoveis
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public void atualizarImoveisTarifaConsumo(String matricula,

	String tarifaAtual, Collection colecaoImoveis)

	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String update;

		List colecaoAuxiliar = new ArrayList();

		colecaoAuxiliar.addAll(colecaoImoveis);

		int i = 0;

		try {

			Collection colecao = new ArrayList();

			while (i <= colecaoImoveis.size()) {

				if (colecaoImoveis.size() - i >= 1000) {

					colecao = colecaoAuxiliar.subList(i, i + 1000);

				} else {

					colecao = colecaoAuxiliar.subList(i, colecaoImoveis.size());

				}

				i = i + 1000;

				update = "UPDATE Imovel imov "

				+ "SET imov.consumoTarifa.id = :idTarifaConsumo "

				+ "WHERE imov.id in (:idsImovel)";

				session.createQuery(update).setInteger("idTarifaConsumo",

				new Integer(tarifaAtual)).setParameterList("idsImovel",

				colecao).executeUpdate();

			}

			// erro no hibernate

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
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * 
	 * 
	 * Atualiza o perfil do imóvel para o perfil normal
	 * 
	 * 
	 * 
	 * @date 04/01/2007
	 * 
	 * @author Rafael Corrêa
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public void atualizarImovelPerfilNormal(Integer idImovel)

	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String update;

		try {

			update = "UPDATE Imovel imov " + " SET imov.imovelPerfil.id = "

			+ ImovelPerfil.NORMAL.toString() + ","

			+ " imov.consumoTarifa.id = "

			+ ConsumoTarifa.CONSUMO_NORMAL.toString()

			+ "where imov.id = :idImovel ";

			session.createQuery(update).setInteger("idImovel", idImovel)

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
	 * [UC0490] - Informar Situação de Cobrança
	 * 
	 * 
	 * 
	 * Pesquisa o imóvel com a situação da ligação de água e a de esgoto
	 * 
	 * 
	 * 
	 * @date 13/01/2007
	 * 
	 * @author Rafael Corrêa
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Imovel pesquisarImovelComSituacaoAguaEsgoto(Integer idImovel)

	throws ErroRepositorioException {

		// cria a coleção de retorno

		Imovel imovel = null;

		// Query

		String consulta = "";

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			consulta = "SELECT imov "

			+ " FROM Imovel imov "

			+ " INNER JOIN FETCH imov.ligacaoAguaSituacao ligAguaSit "

			+ " INNER JOIN FETCH imov.ligacaoEsgotoSituacao ligEsgotoSit "

			+ " WHERE " + " imov.id = :idImovel";

			imovel = (Imovel) session.createQuery(consulta).setInteger(

			"idImovel", idImovel).setMaxResults(1).uniqueResult();

			// erro no hibernate

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		// retorna o imóvel

		return imovel;

	}

	/**
	 * 
	 * [UC0069] - Manter Dados da Tarifa Social
	 * 
	 * 
	 * 
	 * [FS0006] - Verificar número de IPTU
	 * 
	 * 
	 * 
	 * Verifica se já existe outro imóvel ou economia com o mesmo número de IPTU
	 * 
	 * no mesmo município
	 * 
	 * 
	 * 
	 * @date 13/01/2007
	 * 
	 * @author Rafael Corrêa
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Integer verificarNumeroIptu(String numeroIptu, Integer idImovel,

	Integer idImovelEconomia, Integer idMunicipio)

	throws ErroRepositorioException {

		// cria a coleção de retorno

		Integer idImovelRetorno = null;

		// Query

		String consulta = "";

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			if (idImovel != null) {

				consulta = "SELECT imov.imov_id as idImovel "

				+ " FROM cadastro.imovel imov " + " INNER JOIN "

				+ " cadastro.setor_comercial sc "

				+ " on imov.stcm_id = sc.stcm_id " + " WHERE "

				+ " imov.imov_nniptu = :numeroIptu "

				+ " AND sc.muni_id = :idMunicipio "

				+ " AND imov.imov_id <> :idImovel " + " UNION "

				+ " SELECT imovel.imov_id as idImovel "

				+ " FROM cadastro.imovel_economia imovEcon "

				+ " INNER JOIN " + " cadastro.imovel imovel "

				+ " on imovEcon.imov_id = imovel.imov_id "

				+ " INNER JOIN " + " cadastro.setor_comercial sc "

				+ " on imovel.stcm_id = sc.stcm_id " + " WHERE "

				+ " imovEcon.imec_nniptu = :numeroIptu "

				+ " AND sc.muni_id = :idMunicipio ";

				idImovelRetorno = (Integer) session.createSQLQuery(consulta)

				.addScalar("idImovel", Hibernate.INTEGER)

				.setString("numeroIptu", numeroIptu).setInteger(

				"idImovel", idImovel).setInteger("idMunicipio",

				idMunicipio).setMaxResults(1).uniqueResult();

			} else {

				consulta = "SELECT imovel.imov_id as idImovel "

				+ " FROM cadastro.imovel_economia imovEcon "

				+ " INNER JOIN " + " cadastro.imovel imovel "

				+ " on imovEcon.imov_id = imovel.imov_id "

				+ " INNER JOIN " + " cadastro.setor_comercial sc "

				+ " on imovel.stcm_id = sc.stcm_id " + " WHERE "

				+ " imovEcon.imec_nniptu = :numeroIptu "

				+ " AND sc.muni_id = :idMunicipio "

				+ " AND imovEcon.imec_id <> :idImovelEconomia "

				+ " UNION " + " SELECT imov.imov_id as idImovel "

				+ " FROM cadastro.imovel imov " + " INNER JOIN "

				+ " cadastro.setor_comercial sc "

				+ " on imov.stcm_id = sc.stcm_id " + " WHERE "

				+ " imov.imov_nniptu = :numeroIptu "

				+ " AND sc.muni_id = :idMunicipio ";

				idImovelRetorno = (Integer) session.createSQLQuery(consulta)

				.addScalar("idImovel", Hibernate.INTEGER)

				.setString("numeroIptu", numeroIptu).setInteger(

				"idImovelEconomia", idImovelEconomia)

				.setInteger("idMunicipio", idMunicipio)

				.setMaxResults(1).uniqueResult();

			}

			// erro no hibernate

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		// retorna o id do imóvel

		return idImovelRetorno;

	}

	/**
	 * 
	 * [UC0069] - Manter Dados da Tarifa Social
	 * 
	 * 
	 * 
	 * [FS0007] - Verificar número de contrato da companhia de energia elétrica
	 * 
	 * 
	 * 
	 * Verifica se já existe outro imóvel ou economia com o mesmo número de
	 * 
	 * contrato da companhia elétrica
	 * 
	 * 
	 * 
	 * @date 18/01/2007
	 * 
	 * @author Rafael Corrêa
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Integer verificarNumeroCompanhiaEletrica(

	Long numeroCompanhiaEletrica, Integer idImovel,

	Integer idImovelEconomia) throws ErroRepositorioException {

		// cria a coleção de retorno

		Integer idImovelRetorno = null;

		// Query

		String consulta = "";

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			if (idImovel != null) {

				consulta = "SELECT imov.imov_id as idImovel "
						+ " FROM cadastro.imovel imov "
						+ " WHERE "
						+ " imov.imov_nncontratoenergia = :numeroCompanhiaEletrica "
						+ " AND imov.imov_id <> :idImovel "
						+ " UNION "
						+ " SELECT imovel.imov_id as idImovel "
						+ " FROM cadastro.imovel_economia imovEcon "
						+ " INNER JOIN cadastro.imovel imovel "
						+ " on imovEcon.imov_id = imovel.imov_id "
						+ " WHERE "
						+ " imovEcon.imec_nncontratoenergia = :numeroCompanhiaEletrica ";

				idImovelRetorno = (Integer) session.createSQLQuery(consulta)
						.addScalar("idImovel", Hibernate.INTEGER).setLong(
								"numeroCompanhiaEletrica",
								numeroCompanhiaEletrica).setInteger("idImovel",
								idImovel).setMaxResults(1).uniqueResult();

			} else {

				consulta = " SELECT imovel.imov_id as idImovel "
						+ " FROM cadastro.imovel_economia imovEcon "
						+ " INNER JOIN cadastro.imovel imovel "
						+ " on imovEcon.imov_id = imovel.imov_id "
						+ " WHERE "
						+ " imovEcon.imec_nncontratoenergia = :numeroCompanhiaEletrica "
						+ " AND imovEcon.imec_id <> :idImovelEconomia "
						+ " UNION "
						+ " SELECT imov.imov_id as idImovel "
						+ " FROM cadastro.imovel imov "
						+ " WHERE "
						+ " imov.imov_nncontratoenergia = :numeroCompanhiaEletrica ";

				idImovelRetorno = (Integer) session.createSQLQuery(consulta)
						.addScalar("idImovel", Hibernate.INTEGER).setLong(
								"numeroCompanhiaEletrica",
								numeroCompanhiaEletrica).setInteger(
								"idImovelEconomia", idImovelEconomia)
						.setMaxResults(1).uniqueResult();

			}

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		// retorna o id do imóvel

		return idImovelRetorno;

	}

	/**
	 * 
	 * Obtém o quantidade de economias da subCategoria por imovel
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * O identificador do imóvel
	 * 
	 * @return Coleção de SubCategorias
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Descrição da exceção
	 * 
	 */

	public Collection obterQuantidadeEconomiasSubCategoria(Integer idImovel)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "select sb.id, " // 0

					+ "sb.codigo," // 1

					+ "sb.descricao, "// 2

					+ "sum(isb.quantidadeEconomias), "// 3

					+ "sb.codigoTarifaSocial, "// 4

					+ "sb.numeroFatorFiscalizacao, "// 5

					+ "sb.indicadorTarifaConsumo, "//6 
					
					+ "c.id, " //7
					
					+ "c.descricao, "//8
					
					+ "c.fatorEconomias, "//9
					
					+ "sb.indicadorSazonalidade, "//10
					
					+ "c.descricaoAbreviada, "//11
					
					+ "sb.descricaoAbreviada "// 12"

					+ "from ImovelSubcategoria isb "

					+ "inner join isb.comp_id.subcategoria sb "

					+ "inner join sb.categoria c "

					+ "inner join sb.categoria  "
					
					+ "where isb.comp_id.imovel.id = :idImovel "
					
					+ "group by sb.id, "

					+ "sb.codigo," + "sb.descricao, "

					+ "sb.codigoTarifaSocial, "

					+ "sb.numeroFatorFiscalizacao, "

					+ "sb.indicadorTarifaConsumo, " + "c.id, " + "c.descricao,"
					
					+ "isb.comp_id.imovel.id, "
					
					+ "c.fatorEconomias, sb.indicadorSazonalidade, "
					
					+ "c.descricaoAbreviada,sb.descricaoAbreviada ";

					//+ "having isb.comp_id.imovel.id = :idImovel ";

			retorno = session.createQuery(consulta).setInteger("idImovel",

			idImovel).list();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * 
	 * Obtém o quantidade de economias da subCategoria por imovel
	 * 
	 * 
	 * 
	 * @autor Rômulo Aurélio
	 * 
	 * @param idImovel
	 * 
	 * O identificador do imóvel
	 * 
	 * @return Coleção de imovelSubCategorias
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Descrição da exceção
	 * 
	 * @date 08/02/2007
	 * 
	 */

	public Collection pesquisarImovelSubcategorias(Integer idImovel)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String consulta = "select distinct imovSub "

			+ "from ImovelSubcategoria imovSub "

			+ "left join fetch imovSub.imovelEconomias "

			+ "left join fetch imovSub.comp_id.imovel imovel "

			+ "left join fetch imovel.imovelPerfil imovelPerfil "

			+ "left join fetch imovSub.comp_id.subcategoria subcategoria "

			+ "left join fetch subcategoria.categoria "

			+ "where imovel.id = :idImovel";

			retorno = new ArrayList(new CopyOnWriteArraySet(session

			.createQuery(consulta).setInteger("idImovel", idImovel)

			.list()));

			// ((ImovelSubcategoria)retorno.iterator().next()).getImovelEconomias().size();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}
	
	/**
	 * 
	 * recupera os ImovelSubcategoria, com 5 resultados, ordenados por Qt economias desc
	 *
	 * @author Tiago Moreno
	 * @date 08/04/2008
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	public Collection pesquisarImovelSubcategoriasMaxCinco(Integer idImovel)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String consulta = "select distinct imovSub "

			+ "from ImovelSubcategoria imovSub "

			+ "left join fetch imovSub.imovelEconomias "

			+ "left join fetch imovSub.comp_id.imovel imovel "

			+ "left join fetch imovel.imovelPerfil imovelPerfil "

			+ "left join fetch imovSub.comp_id.subcategoria subcategoria "

			+ "left join fetch subcategoria.categoria "

			+ "where imovel.id = :idImovel "
			
			+ "order by imovSub.quantidadeEconomias desc ";

			retorno = new ArrayList(new CopyOnWriteArraySet(session

			.createQuery(consulta).setInteger("idImovel", idImovel)

			.setMaxResults(5).list()));

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}
	
	public Imovel pesquisarImovel(Integer idImovel) throws ErroRepositorioException {

		Imovel imovel = null;
		StringBuilder consulta = new StringBuilder();

		Session session = HibernateUtil.getSession();

		try {
			consulta.append("SELECT imov FROM Imovel imov ")
		      .append(" LEFT JOIN FETCH imov.imovelPerfil imovelPerfil ")
		      .append(" LEFT JOIN FETCH imov.localidade lo")
		      .append(" LEFT JOIN FETCH lo.gerenciaRegional geRe")
		      .append(" LEFT JOIN FETCH imov.setorComercial sc")
		      .append(" LEFT JOIN FETCH imov.quadra qu")
		      .append(" LEFT JOIN FETCH imov.quadraFace qdfa")
		      .append(" LEFT JOIN FETCH qu.rota rota")
		      .append(" LEFT JOIN FETCH imov.logradouroBairro logBairro")
		      .append(" LEFT JOIN FETCH logBairro.bairro bairro")
		      .append(" LEFT JOIN FETCH bairro.municipio")
		      .append(" LEFT JOIN FETCH imov.ligacaoEsgoto lesg")
		      .append(" LEFT JOIN FETCH imov.ligacaoEsgotoSituacao lest")
		      .append(" LEFT JOIN FETCH imov.ligacaoAgua lagu")
		      .append(" LEFT JOIN FETCH imov.ligacaoAguaSituacao last")
		      .append(" LEFT JOIN FETCH imov.fonteAbastecimento ftab")
		      .append(" LEFT JOIN FETCH lagu.hidrometroInstalacaoHistorico hih")
		      .append(" LEFT JOIN FETCH hih.hidrometroLocalInstalacao hil")
		      .append(" LEFT JOIN FETCH hih.hidrometroProtecao hip")
		      .append(" LEFT JOIN FETCH hih.hidrometro hid")
		      .append(" LEFT JOIN FETCH hid.hidrometroMarca him")
		      .append(" LEFT JOIN FETCH hid.hidrometroCapacidade hic")
		      .append(" LEFT JOIN FETCH imov.cadastroOcorrencia co")
		      .append(" LEFT JOIN FETCH imov.cobrancaSituacaoTipo cbst")
		      .append(" LEFT JOIN FETCH imov.imovelSubcategorias subcategorias ")
		      .append(" INNER JOIN FETCH imov.imovelContaEnvio icte ")
		      .append(" LEFT JOIN FETCH hih.hidrometro")
		      .append(" WHERE  imov.id = :idImovel");
			
			imovel = (Imovel) session.createQuery(consulta.toString()).setInteger("idImovel", idImovel).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return imovel;
	}

	/**
	 * 
	 * Atualiza logradouroBairro de um ou mais imóveis
	 * 
	 * 
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * 
	 * 
	 * @author Raphael Rossiter
	 * 
	 * @date 22/02/2007
	 * 
	 * 
	 * 
	 * @param
	 * 
	 * @return void
	 * 
	 */

	public void atualizarLogradouroBairro(

	LogradouroBairro logradouroBairroAntigo,

	LogradouroBairro logradouroBairroNovo)

	throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {

			consulta = "UPDATE gcom.cadastro.imovel.Imovel SET "

					+ "lgbr_id = :idLogradouroBairroNovo, imov_tmultimaalteracao = :ultimaAlteracao "

					+ "WHERE lgbr_id = :idLogradouroBairroAntigo ";

			session.createQuery(consulta).setInteger("idLogradouroBairroNovo",

			logradouroBairroNovo.getId()).setTimestamp(

			"ultimaAlteracao", new Date()).setInteger(

			"idLogradouroBairroAntigo", logradouroBairroAntigo.getId())

			.executeUpdate();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

	}

	/**
	 * 
	 * Atualiza logradouroCep de um ou mais imóveis
	 * 
	 * 
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * 
	 * 
	 * @author Raphael Rossiter
	 * 
	 * @date 22/02/2007
	 * 
	 * 
	 * 
	 * @param
	 * 
	 * @return void
	 * 
	 */

	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo,

	LogradouroCep logradouroCepNovo) throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {

			consulta = "UPDATE gcom.cadastro.imovel.Imovel SET "

					+ "lgcp_id = :idLogradouroCepNovo, imov_tmultimaalteracao = :ultimaAlteracao "

					+ "WHERE lgcp_id = :idLogradouroCepAntigo ";

			session.createQuery(consulta).setInteger("idLogradouroCepNovo",

			logradouroCepNovo.getId()).setTimestamp("ultimaAlteracao",

			new Date()).setInteger("idLogradouroCepAntigo",

			logradouroCepAntigo.getId()).executeUpdate();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

	}

	/**
	 * 
	 * [UC0302] Gerar Débitos a Cobrar de Acréscimos por Impontualidade
	 * 
	 * 
	 * 
	 * Pequisa o identificador de cobranca de acréscimo pro impontualidade para
	 * 
	 * o imóvel do cliente responsável.
	 * 
	 * 
	 * 
	 * @author Pedro Alexandre
	 * 
	 * @date 26/02/2007
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Short obterIndicadorGeracaoAcrescimosClienteImovel(Integer idImovel)

	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Short retorno = null;

		try {

			String consulta = "select clie.indicadorCobrancaAcrescimos "

			+ "from ClienteImovel clim " + "inner join clim.imovel imov "

			+ "inner join clim.cliente clie "

			+ "where imov.id = :idImovel "

			+ "and clim.clienteRelacaoTipo.id = :idRelacao "

			+ "and clim.dataFimRelacao is null";

			retorno = (Short) session.createQuery(consulta).setParameter(

			"idImovel", idImovel).setParameter("idRelacao",

			ClienteRelacaoTipo.RESPONSAVEL).uniqueResult();

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

//	public Integer verificarExistenciaImovelParaCliente(Integer idImovel)
//
//	throws ErroRepositorioException {
//
//		// cria a coleção de retorno
//
//		Integer retorno = null;
//
//		// Query
//
//		String consulta;
//
//		// obtém a sessão
//
//		Session session = HibernateUtil.getSession();
//
//		try {
//
//			// pesquisa a coleção de atividades e atribui a variável "retorno"
//
//			consulta = "select imov.id " + "from ClienteImovel clienteImovel "
//
//			+ "inner join clienteImovel.cliente cli "
//
//			+ "inner join clienteImovel.imovel imov "
//
//			+ "inner join clienteImovel.clienteRelacaoTipo crt "
//
//			+ "where crt.id = :idProprietario AND "
//
//			+ "imov.id = :idImovel AND " + "cli.id = :idCliente";
//
//			retorno = (Integer) session.createQuery(consulta).setInteger(
//
//			"idProprietario", ClienteRelacaoTipo.PROPRIETARIO)
//
//			.setInteger("idImovel", idImovel).setInteger("idCliente",
//
//			Cliente.CODIGO_CLIENTE_MARIO_GOUVEIA)
//
//			.setMaxResults(1).uniqueResult();
//
//			// erro no hibernate
//
//		} catch (HibernateException e) {
//
//			// levanta a exceção para a próxima camada
//
//			throw new ErroRepositorioException(e, "Erro no Hibernate");
//
//		} finally {
//
//			// fecha a sessão
//
//			HibernateUtil.closeSession(session);
//
//		}
//
//		// retorna a coleção de atividades pesquisada(s)
//
//		return retorno;
//
//	}

	/**
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscalização
	 * 
	 * 
	 * 
	 * Obter o consumo médio como não medido para o imóvel passado
	 * 
	 * 
	 * 
	 * @author Raphael Rossiter
	 * 
	 * @date 06/03/2007
	 * 
	 */

	public Integer obterConsumoMedioNaoMedidoImovel(Imovel imovel)

	throws ErroRepositorioException {

		Integer retorno = 0;

		Short consumoNaoMedido = null;

		Session session = HibernateUtil.getSession();

		String consulta;

		try {

			consulta = "select sum(imsb.quantidadeEconomias * scat.numeroFatorFiscalizacao) "

					+ "from gcom.cadastro.imovel.ImovelSubcategoria as imsb "

					+ "inner join imsb.comp_id.imovel imov "

					+ "inner join imsb.comp_id.subcategoria scat "

					+ "where imov.id = :idImovel ";

			consumoNaoMedido = (Short) session.createQuery(consulta)

			.setInteger("idImovel", imovel.getId().intValue())

			.setMaxResults(1).uniqueResult();

			if (consumoNaoMedido != null) {

				retorno = consumoNaoMedido.intValue();

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
	 * Obter a situação de cobrança para o imóvel passado
	 * 
	 * 
	 * 
	 * @author Vivianne Sousa
	 * 
	 * @date 07/03/2007
	 * 
	 */

	public String obterSituacaoCobrancaImovel(Integer idImovel)

	throws ErroRepositorioException {

		String retorno = "";

		Session session = HibernateUtil.getSession();

		String consulta;

		try {

			consulta = "select cobSit.descricao "

			+ "from ImovelCobrancaSituacao imovCobSit "

			+ "left join imovCobSit.cobrancaSituacao cobSit "

			+ "left join imovCobSit.imovel imovel "

			+ "where imovel.id = :idImovel ";

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
	 * 
	 * Pesquisa uma coleção de imóveis
	 * 
	 * 
	 * 
	 * @param idLocalidade
	 * 
	 * parametros para a consulta
	 * 
	 * @param idSetorComercial
	 * 
	 * parametros para a consulta
	 * 
	 * @param idQuadra
	 * 
	 * parametros para a consulta
	 * 
	 * @param lote
	 * 
	 * Descrição do parâmetro
	 * 
	 * @return Description of the Return Value
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Descrição da exceção
	 * 
	 */

	public Collection pesquisarColecaoImovel(

	FiltrarImovelInserirManterContaHelper filtro)

	throws ErroRepositorioException {

		Integer localidadeOrigemID = filtro.getLocalidadeOrigemID();

		Integer setorComercialOrigemID = filtro.getSetorComercialOrigemID();

		Integer quadraOrigemID = filtro.getQuadraOrigemID();

		Short loteOrigem = filtro.getLoteOrigem();

		Short subLoteOrigem = filtro.getSubLoteOrigem();

		Integer localidadeDestinoID = filtro.getLocalidadeDestinoID();

		Integer setorComercialDestinoID = filtro.getSetorComercialDestinoID();

		Integer quadraDestinoID = filtro.getQuadraDestinoID();

		Short loteDestino = filtro.getLoteDestino();

		Short subLoteDestino = filtro.getSubLoteDestino();

		Collection colecaoQuadraSelecionada = filtro

		.getColecaoQuadraSelecionada();

		Integer codigoRotaOrigem = filtro.getCodigoRotaOrigem();

		Integer codigoRotaDestino = filtro.getCodigoRotaDestino();

		Integer sequencialRotaOrigem = filtro.getSequencialRotaOrigem();

		Integer sequencialRotaDestino = filtro.getSequencialRotaDestino();
		
		Boolean verificarImovelPerfilBloqueado = filtro.getVerificarImovelPerfilBloqueado();
		
		/***/
		Integer[] esferasPoder = filtro.getEsferasPoder();

		/*
		 * 
		 * Colocado por Raphael Rossiter em 02/08/2007
		 * 
		 * 
		 * 
		 * OBJETIVO: Acrescentar o parâmetro grupo de faturamento para o filtro
		 * 
		 * de manutenção de várias contas.
		 * 
		 */

		Integer idGrupoFaturamento = filtro.getIdGrupoFaturamento();

		// cria a coleção de retorno

		Collection retorno = null;

		// Query

		String consulta;

		// obtém a sessão

		Session session = HibernateUtil.getSession();
		
		/**
		 * @autor: Adriana Muniz
		 * @date: 24/11/2011
		 * Acréscimo de atributo esfera de poder na consulta
		 * */
		try {

			consulta = "select distinct(imovel.id) "

			+ "FROM gcom.cadastro.cliente.ClienteImovel ci "
			
			+ "INNER JOIN ci.imovel imovel "
			
			+ "INNER JOIN imovel.imovelPerfil iper "

			+ "INNER JOIN imovel.quadra quadra "

			+ "LEFT JOIN imovel.faturamentoSituacaoTipo ftst "

			+ "INNER JOIN quadra.rota rota "

			+ "INNER JOIN rota.faturamentoGrupo ftgr " 
			
			+ "INNER JOIN ci.cliente c "
			
			+ "INNER JOIN c.clienteTipo ct "
			
			
			
			+ "WHERE 1 = 1 " 
			
			+ " AND ci.dataFimRelacao is null ";
			
			if (localidadeOrigemID != null && localidadeDestinoID != null) {

				consulta = consulta + "and imovel.localidade.id between "

				+ localidadeOrigemID.intValue() + " and "

				+ localidadeDestinoID.intValue();

			}

			if (setorComercialOrigemID != null

			&& setorComercialDestinoID != null) {

				consulta = consulta + " and imovel.setorComercial.id between "

				+ setorComercialOrigemID.intValue() + " and "

				+ setorComercialDestinoID.intValue();

			}

			if (colecaoQuadraSelecionada != null

			&& !colecaoQuadraSelecionada.isEmpty()) {

				consulta = consulta

				+ " and imovel.quadra.id in (:colecaoQuadraSelecionada)";

			} else if (quadraOrigemID != null && quadraDestinoID != null) {

				consulta = consulta + " and imovel.quadra.numeroQuadra between "

				+ quadraOrigemID.intValue() + " and "

				+ quadraDestinoID.intValue();

			}

			if (loteOrigem != 0 && loteDestino != 0) {

				consulta = consulta + " and imovel.lote between "

				+ loteOrigem.intValue() + " and "

				+ loteDestino.intValue();

			}

			if (subLoteOrigem != 0 && subLoteDestino != 0) {

				consulta = consulta + " and imovel.subLote between "

				+ subLoteOrigem.intValue() + " and "

				+ subLoteDestino.intValue();

			}

			if (codigoRotaOrigem != null && codigoRotaDestino != null) {

				consulta = consulta + " and rota.codigo between "

				+ codigoRotaOrigem.shortValue() + " and "

				+ codigoRotaDestino.shortValue();

			}

			if (sequencialRotaOrigem != null && sequencialRotaDestino != null) {

				consulta = consulta

				+ " and imovel.numeroSequencialRota between "

				+ sequencialRotaOrigem.intValue() + " and "

				+ sequencialRotaDestino.intValue();

			}

			/*
			 * 
			 * Colocado por Raphael Rossiter em 02/08/2007
			 * 
			 * 
			 * 
			 * OBJETIVO: Acrescentar o parâmetro grupo de faturamento para o
			 * 
			 * filtro de manutenção de várias contas.
			 * 
			 */

			if (idGrupoFaturamento != null) {

				consulta = consulta + " and ftgr.id = "

				+ idGrupoFaturamento.intValue();

			}
			
			if(verificarImovelPerfilBloqueado){
				consulta = consulta + " and iper.indicadorBloquearRetificacao = 2";	
			}
			
			if(esferasPoder != null) {
				consulta += " AND ct.esferaPoder in (:esferasPoder) ";
				
			}
			//Query

			if ((colecaoQuadraSelecionada != null && !colecaoQuadraSelecionada.isEmpty())
					
					&& esferasPoder != null) {

				retorno = session.createQuery(consulta)
				
				.setParameterList("colecaoQuadraSelecionada", colecaoQuadraSelecionada)
				
				.setParameterList("esferasPoder", esferasPoder)

				.list();

			}else if (colecaoQuadraSelecionada != null && !colecaoQuadraSelecionada.isEmpty()) {

				retorno = session.createQuery(consulta)
				
				.setParameterList("colecaoQuadraSelecionada", colecaoQuadraSelecionada)

				.list();

			}else if (esferasPoder != null) {

				retorno = session.createQuery(consulta)
				
				.setParameterList("esferasPoder", esferasPoder)

				.list();

			}
			else {

				retorno = session.createQuery(consulta).list();

			}

			// erro no hibernate

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
	 * Pesquisa uma coleção de imóveis com perfil bloqueado
	 * 
	 * @return Boolean
	 * @exception ErroRepositorioException
	 * 
	 */
	public Integer pesquisarColecaoImovelPerfilBloqueado(FiltrarImovelInserirManterContaHelper filtro)
		throws ErroRepositorioException {

		Integer localidadeOrigemID = filtro.getLocalidadeOrigemID();
		Integer setorComercialOrigemID = filtro.getSetorComercialOrigemID();
		Integer quadraOrigemID = filtro.getQuadraOrigemID();
		Short loteOrigem = filtro.getLoteOrigem();
		Short subLoteOrigem = filtro.getSubLoteOrigem();
		Integer localidadeDestinoID = filtro.getLocalidadeDestinoID();
		Integer setorComercialDestinoID = filtro.getSetorComercialDestinoID();
		Integer quadraDestinoID = filtro.getQuadraDestinoID();
		Short loteDestino = filtro.getLoteDestino();
		Short subLoteDestino = filtro.getSubLoteDestino();
		Collection colecaoQuadraSelecionada = filtro.getColecaoQuadraSelecionada();
		Integer codigoRotaOrigem = filtro.getCodigoRotaOrigem();
		Integer codigoRotaDestino = filtro.getCodigoRotaDestino();
		Integer sequencialRotaOrigem = filtro.getSequencialRotaOrigem();
		Integer sequencialRotaDestino = filtro.getSequencialRotaDestino();

		Integer idGrupoFaturamento = filtro.getIdGrupoFaturamento();
		Integer retorno = 0;
		String consulta;

		Session session = HibernateUtil.getSession();

		try {

			consulta = "select count(imovel.id) "
				     + "FROM gcom.cadastro.imovel.Imovel  imovel "
				     + "INNER JOIN imovel.quadra quadra "
				     + "LEFT JOIN imovel.faturamentoSituacaoTipo ftst "
				     + "INNER JOIN quadra.rota rota "
				     + "INNER JOIN rota.faturamentoGrupo ftgr " 
				     + "INNER JOIN imovel.imovelPerfil iper "
				     + "WHERE iper.indicadorBloquearRetificacao = 1 ";

			if (localidadeOrigemID != null && localidadeDestinoID != null) {
				consulta = consulta + "and imovel.localidade.id between " + localidadeOrigemID.intValue() + " and " + localidadeDestinoID.intValue();
			}

			if (setorComercialOrigemID != null && setorComercialDestinoID != null) {
				consulta = consulta + " and imovel.setorComercial.id between "+ setorComercialOrigemID.intValue() + " and "+ setorComercialDestinoID.intValue();
			}

			if (colecaoQuadraSelecionada != null && !colecaoQuadraSelecionada.isEmpty()) {
				consulta = consulta + " and imovel.quadra.id in (:colecaoQuadraSelecionada)";
			} else if (quadraOrigemID != null && quadraDestinoID != null) {
				consulta = consulta + " and imovel.quadra.id between "+ quadraOrigemID.intValue() + " and "+ quadraDestinoID.intValue();
			}

			if (loteOrigem != 0 && loteDestino != 0) {
				consulta = consulta + " and imovel.lote between "+ loteOrigem.intValue() + " and "+ loteDestino.intValue();
			}

			if (subLoteOrigem != 0 && subLoteDestino != 0) {
				consulta = consulta + " and imovel.subLote between "+ subLoteOrigem.intValue() + " and "+ subLoteDestino.intValue();
			}

			if (codigoRotaOrigem != null && codigoRotaDestino != null) {
				consulta = consulta + " and rota.codigo between "
				+ codigoRotaOrigem.shortValue() + " and " + codigoRotaDestino.shortValue();
			}

			if (sequencialRotaOrigem != null && sequencialRotaDestino != null) {
				consulta = consulta
				+ " and imovel.numeroSequencialRota between " + sequencialRotaOrigem.intValue() + " and " + sequencialRotaDestino.intValue();
			}

			if (idGrupoFaturamento != null) {
				consulta = consulta + " and ftgr.id = " + idGrupoFaturamento.intValue();
			}

			if (colecaoQuadraSelecionada != null && !colecaoQuadraSelecionada.isEmpty()) {
				retorno = (Integer)session.createQuery(consulta).setParameterList(
						"colecaoQuadraSelecionada", colecaoQuadraSelecionada).uniqueResult();
			} else {
				retorno = (Integer)session.createQuery(consulta).uniqueResult();
			}
			

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}
	
	/**
	 * Pesquisa uma coleção de imóveis do cliente
	 * 
	 * @author Ana Maria
	 * @date 20/03/2007
	 */
	public Collection pesquisarColecaoImovelCliente(Integer codigoCliente,
	Integer relacaoTipo, Boolean verificarImovelPerfilBloqueado) throws ErroRepositorioException {

		Collection retorno = null;
		String consulta;

		Session session = HibernateUtil.getSession();

		try {

			consulta = "select distinct(imov.id) " 
					 + "from ClienteImovel clim "
					 + "inner join clim.cliente clie "
					 + "inner join clim.imovel imov "
					 + "inner join imov.imovelPerfil iper "
					 + "WHERE clim.dataFimRelacao is null and " ;
			
			if(relacaoTipo != null && relacaoTipo.equals(99)){
				consulta = consulta + "(clie.id =:codigoCliente or clie.cliente.id = :codigoCliente) ";
			}else{
			    consulta = consulta + "clie.id = :codigoCliente ";	
			}

			if (relacaoTipo != null && !relacaoTipo.equals(99)) {
				consulta = consulta + " and clim.clienteRelacaoTipo = "+ relacaoTipo.intValue();
			}

			if(verificarImovelPerfilBloqueado){
				consulta = consulta + " and iper.indicadorBloquearRetificacao = 2";
			}
			retorno = session.createQuery(consulta).setInteger("codigoCliente",

			codigoCliente).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}
	
	/**
	 * Pesquisa quantidade de imóveis do cliente com perfil bloqueado
	 * 
	 * @author Ana Maria
	 * @date 20/04/2009
	 */
	public Integer pesquisarColecaoImovelClienteBloqueadoPerfil(Integer codigoCliente,
			Integer relacaoTipo) throws ErroRepositorioException {
		
		String consulta;
		Collection resultado = null;
		Integer retorno = 0;

		Session session = HibernateUtil.getSession();

		try {

			consulta = "select distinct(imov.id) " 
					 + "from ClienteImovel clim "
					 + "inner join clim.cliente clie "
					 + "inner join clim.imovel imov "
					 + "inner join imov.imovelPerfil iper "
					 + "WHERE clim.dataFimRelacao is null and " 
					 + "iper.indicadorBloquearRetificacao = 1 and ";
			
			if(relacaoTipo != null && relacaoTipo.equals(99)){
				consulta = consulta + "(clie.id =:codigoCliente or clie.cliente.id = :codigoCliente) ";
			}else{
			    consulta = consulta + "clie.id = :codigoCliente ";	
			}

			if (relacaoTipo != null && !relacaoTipo.equals(99)) {
				consulta = consulta + " and clim.clienteRelacaoTipo = "+ relacaoTipo.intValue();
			}

			resultado = session.createQuery(consulta).setInteger("codigoCliente",
					codigoCliente).list();
			
			if(resultado != null && !resultado.isEmpty()){
				retorno = resultado.size();
			}
			

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	public Integer pesquisarContaMotivoRevisao(Integer idImovel)

	throws ErroRepositorioException {

		Integer retorno = null;

		// Query

		String consulta;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			consulta = "select cmrv.id from ImovelCobrancaSituacao ics "

			+ "inner join ics.imovel im "

			+ "inner join ics.contaMotivoRevisao cmrv "

			+ "where im.id = :idImovel and ics.dataRetiradaCobranca is null";

			retorno = (Integer) session.createQuery(consulta).setInteger(

			"idImovel", idImovel).setMaxResults(1).uniqueResult();

			// erro no hibernate

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

	public Collection obterSubCategoriasPorCategoria(Integer idCategoria,

	Integer idImovel) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String hql = "select "

			+ " new gcom.cadastro.imovel.ImovelSubcategoria("

			+ "  sub.id,"

			+ "  sub.quantidadeEconomias,"

			+ "  sub.ultimaAlteracao ) "

			+ "from"

			+ "  gcom.cadastro.imovel.ImovelSubcategoria sub "

			+ "where"

			+ "  sub.comp_id.subcategoria.categoria.id = :idCategoria and "

			+ "  sub.comp_id.imovel.id = :idImovel";

			retorno = session.createQuery(hql).setInteger("idCategoria",

			idCategoria).setInteger("idImovel", idImovel).list();

		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * 
	 * [UC0623] - GERAR RESUMO DE METAS EXECUTDO NO MÊS(CAERN)
	 * 
	 * 
	 * 
	 * @author Sávio Luiz
	 * 
	 * @date 08/08/2007
	 * 
	 */

	public Object[] obterSubCategoriasComCodigoGrupoPorCategoria(

	Integer idCategoria, Integer idImovel)

	throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String hql = "select "

			+ "  sub.comp_id.subcategoria.id,"

			+ "  sub.quantidadeEconomias,"

			+ "  sub.ultimaAlteracao,  "

			+ "  sub.comp_id.subcategoria.codigoGrupoSubcategoria "

			+ "from"

			+ "  gcom.cadastro.imovel.ImovelSubcategoria sub "

			+ "where"

			+ "  sub.comp_id.subcategoria.categoria.id = :idCategoria and "

			+ "  sub.comp_id.imovel.id = :idImovel"

			+ "  order by 2,4 desc";

			retorno = (Object[]) session.createQuery(hql).setInteger(
					"idCategoria",

					idCategoria).setInteger("idImovel", idImovel)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * 
	 * [UC0150] - Retificar Conta
	 * 
	 * 
	 * 
	 * @author Raphael Rossiter
	 * 
	 * @date 16/04/2007
	 * 
	 */

	public Collection obterQuantidadeEconomiasCategoriaPorSubcategoria(

	Integer conta) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "SELECT scat.id, scat.codigo, scat.descricao, "

					+ "scat.codigoTarifaSocial, sum(ctcg.quantidadeEconomia), "

					+ "scat.numeroFatorFiscalizacao, scat.indicadorTarifaConsumo, "

					+ "cnta.id, "

					+ "catg.id, catg.descricao, catg.descricaoAbreviada, catg.fatorEconomias, "
					
					+ "scat.descricaoAbreviada "

					+ "FROM ContaCategoria ctcg "

					+ "INNER JOIN ctcg.comp_id.conta cnta "

					+ "INNER JOIN ctcg.comp_id.categoria catg "

					+ "INNER JOIN ctcg.comp_id.subcategoria scat "
					
					+ "WHERE  cnta.id = :contaId "

					+ "GROUP BY scat.id, scat.codigo, scat.descricao, scat.codigoTarifaSocial, "

					+ "scat.numeroFatorFiscalizacao, scat.indicadorTarifaConsumo, cnta.id, catg.id, catg.descricao, "

					+ "catg.descricaoAbreviada, catg.fatorEconomias,scat.descricaoAbreviada " 
					
					//+ "HAVING cnta.id = :contaId "

					+ "ORDER BY catg.descricao, scat.descricao";

			retorno = session.createQuery(consulta).setInteger("contaId",

			conta.intValue()).list();

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
	 * [UC0591] - Gerar Relatório de Clientes Especiais
	 * 
	 * 
	 * 
	 * Pesquisas os imóveis de acordo com os parâmetros da pesquisa
	 * 
	 * 
	 * 
	 * @author Rafael Corrêa
	 * 
	 * @date 31/05/2007
	 * 
	 */

	public Collection pesquisarImovelClientesEspeciaisRelatorio(

	String idUnidadeNegocio, String idGerenciaRegional,

	String idLocalidadeInicial, String idLocalidadeFinal,
	
	String codigoSetorInicial, String codigoSetorFinal,
	
	String codigoRotaInicial, String codigoRotaFinal,

	String[] idsPerfilImovel, String[] idsCategoria,

	String[] idsSubcategoria, String idSituacaoAgua,

	String idSituacaoEsgoto, String qtdeEconomiasInicial,

	String qtdeEconomiasFinal, String intervaloConsumoAguaInicial,

	String intervaloConsumoAguaFinal,

	String intervaloConsumoEsgotoInicial,

	String intervaloConsumoEsgotoFinal, String idClienteResponsavel,

	String intervaloConsumoResponsavelInicial,

	String intervaloConsumoResponsavelFinal,

	Date dataInstalacaoHidrometroInicial,

	Date dataInstalacaoHidrometroFinal,

	String[] idsCapacidadesHidrometro, String[] idsTarifasConsumo,

	Integer anoMesFaturamento, String idLeituraAnormalidade,

	String leituraAnormalidade, String idConsumoAnormalidade,

	String consumoAnormalidade) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {
			
			consulta = "SELECT greg.greg_id as idGerencia, greg.greg_nmregional as nomeGerencia, " // 0, 1

					+ " loc.loca_id as idLocalidade, loc.loca_nmlocalidade as nomeLocalidade, " // 2, 3

					+ " setor.stcm_cdsetorcomercial as codSetor, quadra.qdra_nnquadra as numeroQuadra, " // 4, 5

					+ " imov.imov_nnlote as lote, imov.imov_nnsublote as subLote, " // 6, 7

					+ " imov.imov_id as idImovel, clieUsuario.clie_id as idClienteUsuario, " // 8, 9

					+ " clieUsuario.clie_nmcliente as nomeClienteUsuario, imov.imov_qteconomia as qtdeEconomias, " // 10,

					+ " ligAguaSit.last_dsligacaoaguasituacao as sitLigAgua, " // 12

					+ " ligEsgSit.lest_dsligacaoesgotosituacao as sitLigEsg, " // 13

					+ " hidrCap.hicp_dsabreviadahidrcapacidade as hidrCapacidade, " // 14

					+ " hidrInstHist.hidi_dtinstalacaohidrometro as dtInstalacao, " // 15

					+ " clieResponsavel.clie_id as idClienteResponsavel, " // 16

					+ " clieResponsavel.clie_nmcliente as nomeClienteResponsavel, " // 17

					+ " consHistAgua.cshi_nnconsumofaturadomes as consumoAgua, " // 18

					+ " consHistEsgoto.cshi_nnconsumofaturadomes as consumoEsgoto, " // 19

					+ " ligEsgoto.lesg_nnconsumominimoesgoto as consumoMinimoEsgoto, " // 20

					+ " consTar.cstf_dsconsumotarifa as consumoTarifa, " // 21

					+ " conta.cnta_vlagua as valorAgua, conta.cnta_vlesgoto as valorEsgoto, " // 22, 23

					+ " conta.cnta_vldebitos as valorDebitos, conta.cnta_vlcreditos as valorCreditos, " // 24, 25

					+ " setor.stcm_nmsetorcomercial as nomeSetor, rota.rota_cdrota as codRota, "//26,27
					
					+ " conta.cnta_iccobrancamulta as iccobrancamulta " //28

					+ " FROM cadastro.imovel imov "

					+ " INNER JOIN faturamento.consumo_tarifa consTar "

					+ " on consTar.cstf_id = imov.cstf_id "

					+ " INNER JOIN atendimentopublico.ligacao_agua_situacao ligAguaSit "

					+ " on ligAguaSit.last_id = imov.last_id "

					+ " INNER JOIN atendimentopublico.ligacao_esgoto_situacao ligEsgSit "

					+ " on ligEsgSit.lest_id = imov.lest_id "

					+ " INNER JOIN cadastro.setor_comercial setor "

					+ " on setor.stcm_id = imov.stcm_id "
					
					+ " INNER JOIN cadastro.quadra quadra "

					+ " on quadra.qdra_id = imov.qdra_id "

					+ " INNER JOIN micromedicao.rota rota "

					+ " on quadra.rota_id = rota.rota_id "

					+ " INNER JOIN cadastro.localidade loc "

					+ " on loc.loca_id = imov.loca_id "

					+ " INNER JOIN cadastro.gerencia_regional greg "

					+ " on greg.greg_id = loc.greg_id "

					+ " INNER JOIN cadastro.cliente_imovel clieImovUsuario "

					+ " on clieImovUsuario.imov_id = imov.imov_id "

					+ " and clieImovUsuario.crtp_id = " + ClienteRelacaoTipo.USUARIO

					+ " and clieImovUsuario.clim_dtrelacaofim is null "

					+ " INNER JOIN cadastro.cliente clieUsuario "

					+ " on clieUsuario.clie_id = clieImovUsuario.clie_id ";

			if ( !Util.isVazioOrNulo(idsCategoria) || !Util.isVazioOrNulo(idsSubcategoria) ) {

				consulta = consulta

				+ " INNER JOIN cadastro.imovel_subcategoria imovSub "

				+ " on imovSub.imov_id = imov.imov_id "

				+ " INNER JOIN cadastro.subcategoria subcategoria "

				+ " on subcategoria.scat_id = imovSub.scat_id ";

			}

			String tipoJoin = " LEFT OUTER ";
			
			if (Util.verificarNaoVazio(idClienteResponsavel)
					|| Util.verificarNaoVazio(intervaloConsumoResponsavelInicial)) {
				
				tipoJoin = " INNER ";
			} 
			
			consulta = consulta

			+ tipoJoin + " JOIN cadastro.cliente_imovel clieImovResponsavel "

			+ " on clieImovResponsavel.imov_id = imov.imov_id "

			+ " and clieImovResponsavel.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL

			+ " and clieImovResponsavel.clim_dtrelacaofim is null "

			+ tipoJoin + " JOIN cadastro.cliente clieResponsavel "

			+ " on clieResponsavel.clie_id = clieImovResponsavel.clie_id ";

			
			tipoJoin = " LEFT OUTER ";
			
			if ( !Util.isVazioOrNulo(idsCapacidadesHidrometro)) {
				
				tipoJoin = " INNER ";
			} 
			
			consulta = consulta

			+ tipoJoin + " JOIN atendimentopublico.ligacao_agua ligAgua "

			+ " on ligAgua.lagu_id = imov.imov_id "

			+ tipoJoin + " JOIN micromedicao.hidrometro_inst_hist hidrInstHist "

			+ " on hidrInstHist.hidi_id = ligAgua.hidi_id "

			+ tipoJoin + " JOIN micromedicao.hidrometro hidr "

			+ " on hidr.hidr_id = hidrInstHist.hidr_id "

			+ tipoJoin + " JOIN micromedicao.hidrometro_capacidade hidrCap "

			+ " on hidrCap.hicp_id =  hidr.hicp_id ";

			
			tipoJoin = " LEFT OUTER ";

			if ( Util.verificarNaoVazio(intervaloConsumoAguaInicial)) {
				tipoJoin = " INNER ";
			} 
			
			consulta = consulta

			+ tipoJoin + " JOIN micromedicao.consumo_historico consHistAgua "

			+ " on consHistAgua.imov_id = imov.imov_id "

			+ " and consHistAgua.cshi_amfaturamento = :anoMesFaturamento "

			+ " and consHistAgua.lgti_id = " + LigacaoTipo.LIGACAO_AGUA;

			
			tipoJoin = " LEFT OUTER ";
			
			if (Util.verificarNaoVazio(intervaloConsumoEsgotoInicial)) {
				tipoJoin = " LEFT OUTER ";
			} 
			
			consulta = consulta

			+  tipoJoin + " JOIN micromedicao.consumo_historico consHistEsgoto "

			+ " on consHistEsgoto.imov_id =  imov.imov_id "

			+ " and consHistEsgoto.cshi_amfaturamento = :anoMesFaturamento "

			+ " and consHistEsgoto.lgti_id = " + LigacaoTipo.LIGACAO_ESGOTO;

			
			
			
			if (Util.verificarNaoVazio(leituraAnormalidade) || Util.verificarNaoVazio(idLeituraAnormalidade)) {

				consulta = consulta

				+ " INNER JOIN micromedicao.medicao_historico medHist "

				+ " on medHist.lagu_id = imov.imov_id "

				+ " and medHist.mdhi_amleitura = :anoMesFaturamento ";

			}

			consulta = consulta

			+ " LEFT OUTER JOIN faturamento.conta conta "

			+ " on conta.imov_id = imov.imov_id "

			+ " and cnta_amreferenciaconta = :anoMesFaturamento and dcst_idatual in (0,1,2) "

			+ " LEFT OUTER JOIN atendimentopublico.ligacao_esgoto ligEsgoto "

			+ " on ligEsgoto.lesg_id = imov.imov_id ";

			String condicionais = this

			.criarCondicionaisImovelClientesEspeciais(idUnidadeNegocio,idGerenciaRegional, 
					
			idLocalidadeInicial, idLocalidadeFinal, 
			
			codigoSetorInicial, codigoSetorFinal,
			
			codigoRotaInicial, codigoRotaFinal,
			
			idsPerfilImovel, idsCategoria,

			idsSubcategoria, idSituacaoAgua, idSituacaoEsgoto,

			qtdeEconomiasInicial, qtdeEconomiasFinal,

			intervaloConsumoAguaInicial,

			intervaloConsumoAguaFinal,

			intervaloConsumoEsgotoInicial,

			intervaloConsumoEsgotoFinal, idClienteResponsavel,

			intervaloConsumoResponsavelInicial,

			intervaloConsumoResponsavelFinal,

			dataInstalacaoHidrometroInicial,

			dataInstalacaoHidrometroFinal,

			idsCapacidadesHidrometro, idsTarifasConsumo,

			anoMesFaturamento, idLeituraAnormalidade,

			leituraAnormalidade, idConsumoAnormalidade,

			consumoAnormalidade);

			
			consulta = consulta + condicionais;			
			//a ordenação está sendo feita através de comparator
			//pois alguns campos só são obtidos após a consulta
			
			retorno = session.createSQLQuery(consulta).addScalar("idGerencia",

			Hibernate.INTEGER).addScalar("nomeGerencia",

			Hibernate.STRING).addScalar("idLocalidade",

			Hibernate.INTEGER).addScalar("nomeLocalidade",

			Hibernate.STRING).addScalar("codSetor", Hibernate.INTEGER)

			.addScalar("numeroQuadra", Hibernate.INTEGER).addScalar(

			"lote", Hibernate.SHORT).addScalar("subLote",

			Hibernate.SHORT).addScalar("idImovel",

			Hibernate.INTEGER).addScalar("idClienteUsuario",

			Hibernate.INTEGER).addScalar("nomeClienteUsuario",

			Hibernate.STRING).addScalar("qtdeEconomias",

			Hibernate.SHORT).addScalar("sitLigAgua",

			Hibernate.STRING).addScalar("sitLigEsg",

			Hibernate.STRING).addScalar("hidrCapacidade",

			Hibernate.STRING).addScalar("dtInstalacao",

			Hibernate.DATE).addScalar("idClienteResponsavel",

			Hibernate.INTEGER).addScalar(

			"nomeClienteResponsavel", Hibernate.STRING)

			.addScalar("consumoAgua", Hibernate.INTEGER).addScalar(

			"consumoEsgoto", Hibernate.INTEGER).addScalar(

			"consumoMinimoEsgoto", Hibernate.INTEGER)

			.addScalar("consumoTarifa", Hibernate.STRING).addScalar(

			"valorAgua", Hibernate.BIG_DECIMAL).addScalar(

			"valorEsgoto", Hibernate.BIG_DECIMAL).addScalar(

			"valorDebitos", Hibernate.BIG_DECIMAL).addScalar(

			"valorCreditos", Hibernate.BIG_DECIMAL).addScalar(

			"nomeSetor", Hibernate.STRING).addScalar(

			"codRota", Hibernate.INTEGER).addScalar(
			
			"iccobrancamulta", Hibernate.SHORT).setInteger(

			"anoMesFaturamento", anoMesFaturamento).list();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}
	
	/**  
	 * [UC0591] - Gerar Relatório de Clientes Especiais
	 * 
	 * 					Count
	 * 
	 * @author Hugo Amorim
	 * @date 11/05/2010
	 */
	public Integer pesquisarImovelClientesEspeciaisRelatorioCount(
			String idUnidadeNegocio, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,			
			String codigoSetorInicial, String codigoSetorFinal,			
			String codigoRotaInicial, String codigoRotaFinal,
			String[] idsPerfilImovel, String[] idsCategoria,
			String[] idsSubcategoria, String idSituacaoAgua,
			String idSituacaoEsgoto, String qtdeEconomiasInicial,
			String qtdeEconomiasFinal, String intervaloConsumoAguaInicial,
			String intervaloConsumoAguaFinal,
			String intervaloConsumoEsgotoInicial,
			String intervaloConsumoEsgotoFinal, String idClienteResponsavel,
			String intervaloConsumoResponsavelInicial,
			String intervaloConsumoResponsavelFinal,
			Date dataInstalacaoHidrometroInicial,
			Date dataInstalacaoHidrometroFinal,
			String[] idsCapacidadesHidrometro, String[] idsTarifasConsumo,
			Integer anoMesFaturamento, String idLeituraAnormalidade,
			String leituraAnormalidade, String idConsumoAnormalidade,
			String consumoAnormalidade) throws ErroRepositorioException {

				Collection colecaoRetorno = null;
				
				Integer retorno = null;

				Session session = HibernateUtil.getSession();

				String consulta = null;

				try {
					
					consulta = "SELECT count(*) as contador"

							+ " FROM cadastro.imovel imov "

							+ " INNER JOIN faturamento.consumo_tarifa consTar "

							+ " on consTar.cstf_id = imov.cstf_id "

							+ " INNER JOIN atendimentopublico.ligacao_agua_situacao ligAguaSit "

							+ " on ligAguaSit.last_id = imov.last_id "

							+ " INNER JOIN atendimentopublico.ligacao_esgoto_situacao ligEsgSit "

							+ " on ligEsgSit.lest_id = imov.lest_id "

							+ " INNER JOIN cadastro.setor_comercial setor "

							+ " on setor.stcm_id = imov.stcm_id "

							+ " INNER JOIN micromedicao.rota rota "

							+ " on setor.stcm_id = rota.stcm_id "

							+ " INNER JOIN cadastro.quadra quadra "

							+ " on quadra.qdra_id = imov.qdra_id "

							+ " INNER JOIN cadastro.localidade loc "

							+ " on loc.loca_id = imov.loca_id "

							+ " INNER JOIN cadastro.gerencia_regional greg "

							+ " on greg.greg_id = loc.greg_id "

							+ " INNER JOIN cadastro.cliente_imovel clieImovUsuario "

							+ " on clieImovUsuario.imov_id = imov.imov_id "

							+ " and clieImovUsuario.crtp_id = " + ClienteRelacaoTipo.USUARIO

							+ " and clieImovUsuario.clim_dtrelacaofim is null "

							+ " INNER JOIN cadastro.cliente clieUsuario "

							+ " on clieUsuario.clie_id = clieImovUsuario.clie_id ";

					if ( !Util.isVazioOrNulo(idsCategoria) || !Util.isVazioOrNulo(idsSubcategoria) ) {

						consulta = consulta

						+ " INNER JOIN cadastro.imovel_subcategoria imovSub "

						+ " on imovSub.imov_id = imov.imov_id "

						+ " INNER JOIN cadastro.subcategoria subcategoria "

						+ " on subcategoria.scat_id = imovSub.scat_id ";

					}

					String tipoJoin = " LEFT OUTER ";
					
					if (Util.verificarNaoVazio(idClienteResponsavel)
							|| Util.verificarNaoVazio(intervaloConsumoResponsavelInicial)) {
						
						tipoJoin = " INNER ";
					} 
					
					consulta = consulta

					+ tipoJoin + " JOIN cadastro.cliente_imovel clieImovResponsavel "

					+ " on clieImovResponsavel.imov_id = imov.imov_id "

					+ " and clieImovResponsavel.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL

					+ " and clieImovResponsavel.clim_dtrelacaofim is null "

					+ tipoJoin + " JOIN cadastro.cliente clieResponsavel "

					+ " on clieResponsavel.clie_id = clieImovResponsavel.clie_id ";

					
					tipoJoin = " LEFT OUTER ";
					
					if ( !Util.isVazioOrNulo(idsCapacidadesHidrometro)) {
						
						tipoJoin = " INNER ";
					} 
					
					consulta = consulta

					+ tipoJoin + " JOIN atendimentopublico.ligacao_agua ligAgua "

					+ " on ligAgua.lagu_id = imov.imov_id "

					+ tipoJoin + " JOIN micromedicao.hidrometro_inst_hist hidrInstHist "

					+ " on hidrInstHist.hidi_id = ligAgua.hidi_id "

					+ tipoJoin + " JOIN micromedicao.hidrometro hidr "

					+ " on hidr.hidr_id = hidrInstHist.hidr_id "

					+ tipoJoin + " JOIN micromedicao.hidrometro_capacidade hidrCap "

					+ " on hidrCap.hicp_id =  hidr.hicp_id ";

					
					tipoJoin = " LEFT OUTER ";

					if ( Util.verificarNaoVazio(intervaloConsumoAguaInicial)) {
						tipoJoin = " INNER ";
					} 
					
					consulta = consulta

					+ tipoJoin + " JOIN micromedicao.consumo_historico consHistAgua "

					+ " on consHistAgua.imov_id = imov.imov_id "

					+ " and consHistAgua.cshi_amfaturamento = :anoMesFaturamento "

					+ " and consHistAgua.lgti_id = " + LigacaoTipo.LIGACAO_AGUA;

					
					tipoJoin = " LEFT OUTER ";
					
					if (Util.verificarNaoVazio(intervaloConsumoEsgotoInicial)) {
						tipoJoin = " LEFT OUTER ";
					} 
					
					consulta = consulta

					+  tipoJoin + " JOIN micromedicao.consumo_historico consHistEsgoto "

					+ " on consHistEsgoto.imov_id =  imov.imov_id "

					+ " and consHistEsgoto.cshi_amfaturamento = :anoMesFaturamento "

					+ " and consHistEsgoto.lgti_id = " + LigacaoTipo.LIGACAO_ESGOTO;

					
					
					
					if (Util.verificarNaoVazio(leituraAnormalidade) || Util.verificarNaoVazio(idLeituraAnormalidade)) {

						consulta = consulta

						+ " INNER JOIN micromedicao.medicao_historico medHist "

						+ " on medHist.lagu_id = imov.imov_id "

						+ " and medHist.mdhi_amleitura = :anoMesFaturamento ";

					}

					consulta = consulta

					+ " LEFT OUTER JOIN faturamento.conta conta "

					+ " on conta.imov_id = imov.imov_id "

					+ " and cnta_amreferenciaconta = :anoMesFaturamento "

					+ " LEFT OUTER JOIN atendimentopublico.ligacao_esgoto ligEsgoto "

					+ " on ligEsgoto.lesg_id = imov.imov_id ";

					String condicionais = this

					.criarCondicionaisImovelClientesEspeciais(idUnidadeNegocio,idGerenciaRegional, 
							
					idLocalidadeInicial, idLocalidadeFinal, 
					
					codigoSetorInicial, codigoSetorFinal,
					
					codigoRotaInicial, codigoRotaFinal,
					
					idsPerfilImovel, idsCategoria,

					idsSubcategoria, idSituacaoAgua, idSituacaoEsgoto,

					qtdeEconomiasInicial, qtdeEconomiasFinal,

					intervaloConsumoAguaInicial,

					intervaloConsumoAguaFinal,

					intervaloConsumoEsgotoInicial,

					intervaloConsumoEsgotoFinal, idClienteResponsavel,

					intervaloConsumoResponsavelInicial,

					intervaloConsumoResponsavelFinal,

					dataInstalacaoHidrometroInicial,

					dataInstalacaoHidrometroFinal,

					idsCapacidadesHidrometro, idsTarifasConsumo,

					anoMesFaturamento, idLeituraAnormalidade,

					leituraAnormalidade, idConsumoAnormalidade,

					consumoAnormalidade);

					
					consulta = consulta + condicionais;			
					//a ordenação está sendo feita através de comparator
					//pois alguns campos só são obtidos após a consulta
					
					colecaoRetorno = session.createSQLQuery(consulta)
						.addScalar("contador", Hibernate.INTEGER)
						.setInteger("anoMesFaturamento", anoMesFaturamento).list();
						
					if(colecaoRetorno!=null){
						retorno = (Integer) colecaoRetorno.iterator().next();
					}	

				} catch (HibernateException e) {

					throw new ErroRepositorioException(e, "Erro no Hibernate");

				} finally {

					HibernateUtil.closeSession(session);

				}

				return retorno;

		}

	public String criarCondicionaisImovelClientesEspeciais(

	String idUnidadeNegocio, String idGerenciaRegional,

	String idLocalidadeInicial, String idLocalidadeFinal,

	String codigoSetorInicial, String codigoSetorFinal,

	String codigoRotaInicial, String codigoRotaFinal,

	String[] idsPerfilImovel, String[] idsCategoria,

	String[] idsSubcategoria, String idSituacaoAgua,

	String idSituacaoEsgoto, String qtdeEconomiasInicial,

	String qtdeEconomiasFinal, String intervaloConsumoAguaInicial,

	String intervaloConsumoAguaFinal,

	String intervaloConsumoEsgotoInicial,

	String intervaloConsumoEsgotoFinal, String idClienteResponsavel,

	String intervaloConsumoResponsavelInicial,

	String intervaloConsumoResponsavelFinal,

	Date dataInstalacaoHidrometroInicial,

	Date dataInstalacaoHidrometroFinal,

	String[] idsCapacidadesHidrometro, String[] idsTarifasConsumo,

	Integer anoMesFaturamento, String idLeituraAnormalidade,

	String leituraAnormalidade, String idConsumoAnormalidade,

	String consumoAnormalidade) {

		String sql = " WHERE ";

		if (Util.isCampoComboboxInformado(idUnidadeNegocio)){
		
			sql = sql + " loc.uneg_id = " + idUnidadeNegocio + " and ";
		}


		if (Util.isCampoComboboxInformado(idGerenciaRegional)) {

			sql = sql + " loc.greg_id = " + idGerenciaRegional + " and ";
		}

		if ( Util.verificarNaoVazio(idLocalidadeInicial)) {

			sql = sql + " loc.loca_id between " + idLocalidadeInicial + " and " + idLocalidadeFinal + " and ";
		}

		if ( Util.verificarNaoVazio(codigoSetorInicial)) {

			sql = sql + " setor.stcm_cdsetorcomercial between " + codigoSetorInicial + " and " + codigoSetorFinal + " and ";
		}

		if ( Util.verificarNaoVazio(codigoRotaInicial)) {

			sql = sql + " rota.rota_cdrota between " + codigoRotaInicial + " and " + codigoRotaFinal + " and ";
		}

		if (Util.isCampoComboboxMultiploInformado(idsPerfilImovel)) {

			String valoresIn = "";

			for (int i = 0; i < idsPerfilImovel.length; i++) {

				if ( Util.isCampoComboboxInformado(idsPerfilImovel[i])) {

					valoresIn = valoresIn + idsPerfilImovel[i] + ",";
				}
			}

			if (!valoresIn.equals("")) {

				sql = sql + " imov.iper_id in (" + valoresIn;

				sql = Util.removerUltimosCaracteres(sql, 1);

				sql = sql + ") and ";

			}

		}

		if (Util.isCampoComboboxMultiploInformado(idsCategoria)) {

			String valoresIn = "";

			for (int i = 0; i < idsCategoria.length; i++) {

				if (Util.isCampoComboboxInformado(idsCategoria[i])) {

					valoresIn = valoresIn + idsCategoria[i] + ",";
				}

			}

			if (!valoresIn.equals("")) {

				sql = sql + " subcategoria.catg_id in (" + valoresIn;

				sql = Util.removerUltimosCaracteres(sql, 1);

				sql = sql + ") and ";

			}

		}

		// Subcategoria

		if (Util.isCampoComboboxMultiploInformado(idsSubcategoria)) {

			String valoresIn = "";

			for (int i = 0; i < idsSubcategoria.length; i++) {

				if (Util.isCampoComboboxInformado(idsSubcategoria[i])) {

					valoresIn = valoresIn + idsSubcategoria[i] + ",";
				}

			}

			if (!valoresIn.equals("")) {

				sql = sql + " subcategoria.scat_id in (" + valoresIn;

				sql = Util.removerUltimosCaracteres(sql, 1);

				sql = sql + ") and ";
			}

		}

		// Situação de Água

		if (Util.isCampoComboboxInformado(idSituacaoAgua)) {

			sql = sql + " imov.last_id = " + idSituacaoAgua + " and ";
		}

		// Situação de Esgoto

		if (Util.isCampoComboboxInformado(idSituacaoEsgoto)) {

			sql = sql + " imov.lest_id = " + idSituacaoEsgoto + " and ";
		}

		// Quantidade de Economias

		if (Util.verificarNaoVazio(qtdeEconomiasInicial)) {

			sql = sql + " imov.imov_qteconomia between " + qtdeEconomiasInicial

			+ " and " + qtdeEconomiasFinal + " and ";
		}

		// Intervalo de Consumo de Água

		if (Util.verificarNaoVazio(intervaloConsumoAguaInicial)) {

			sql = sql + " consHistAgua.cshi_nnconsumofaturadomes between "

			+ intervaloConsumoAguaInicial + " and "

			+ intervaloConsumoAguaFinal + " and ";
		}

		// Intervalo de Consumo de Esgoto

		if (Util.verificarNaoVazio(intervaloConsumoEsgotoInicial)) {

			sql = sql + " consHistEsgoto.cshi_nnconsumofaturadomes between "

			+ intervaloConsumoEsgotoInicial + " and "

			+ intervaloConsumoEsgotoFinal + " and ";
		}

		// Cliente Responsável

		if (Util.verificarNaoVazio(idClienteResponsavel)) {

			sql = sql + " clieImovResponsavel.clie_id = " + idClienteResponsavel + " and ";
		}

		// Intervalo de Consumo por Responsável

		if (Util.verificarNaoVazio(intervaloConsumoResponsavelInicial)) {

			sql = sql

					+ " ((SELECT sum(CASE WHEN consHistAgua.cshi_id is not null "

					+ " THEN consHistAgua.cshi_nnconsumofaturadomes "

					+ " ELSE consHistEsgoto.cshi_nnconsumofaturadomes END) "

					+ " FROM    cadastro.cliente_imovel clieImov "

					+ " INNER JOIN cadastro.imovel imov "

					+ " on imov.imov_id = clieImov.imov_id "

					+ " INNER JOIN cadastro.localidade loc "

					+ " on loc.loca_id = imov.loca_id "

					+ " LEFT OUTER JOIN micromedicao.consumo_historico consHistAgua "

					+ " on clieImov.imov_id = consHistAgua.imov_id and consHistAgua.lgti_id = 1 and consHistAgua.cshi_amfaturamento = "

					+ anoMesFaturamento.toString()

					+ " LEFT OUTER JOIN micromedicao.consumo_historico consHistEsgoto "

					+ " on clieImov.imov_id = consHistEsgoto.imov_id and consHistEsgoto.lgti_id = 2 and consHistEsgoto.cshi_amfaturamento = "

					+ anoMesFaturamento.toString();

			if (idsCategoria != null || idsSubcategoria != null) {

				sql = sql + " INNER JOIN cadastro.imovel_subcategoria imovSub "

				+ " on imovSub.imov_id = imov.imov_id "

				+ " INNER JOIN cadastro.subcategoria subcategoria "

				+ " on subcategoria.scat_id = imovSub.scat_id ";

			}

			if (idsCapacidadesHidrometro != null) {

				sql = sql

						+ " INNER JOIN atendimentopublico.ligacao_agua ligAgua "

						+ " on ligAgua.lagu_id = imov.imov_id "

						+ " INNER JOIN micromedicao.hidrometro_inst_hist hidrInstHist "

						+ " on hidrInstHist.hidi_id = ligAgua.hidi_id "

						+ " INNER JOIN micromedicao.hidrometro hidr "

						+ " on hidr.hidr_id = hidrInstHist.hidr_id ";

			} else {

				sql = sql

						+ " LEFT OUTER JOIN atendimentopublico.ligacao_agua ligAgua "

						+ " on ligAgua.lagu_id = imov.imov_id "

						+ " LEFT OUTER JOIN micromedicao.hidrometro_inst_hist hidrInstHist "

						+ " on hidrInstHist.hidi_id = ligAgua.hidi_id "

						+ " LEFT OUTER JOIN micromedicao.hidrometro hidr "

						+ " on hidr.hidr_id = hidrInstHist.hidr_id ";

			}

			if ((leituraAnormalidade != null && !leituraAnormalidade.trim()

			.equals(""))

			|| (idLeituraAnormalidade != null && !idLeituraAnormalidade

			.trim().equals(""))) {

				sql = sql

				+ " INNER JOIN micromedicao.medicao_historico medHist "

				+ " on medHist.lagu_id = imov.imov_id "

				+ " and medHist.medt_id = "

				+ MedicaoTipo.LIGACAO_AGUA.toString()

				+ " and medHist.mdhi_amleitura = "

				+ anoMesFaturamento.toString();

			}

			sql = sql + " WHERE ";

			// Unidade de Negócio

			if (idUnidadeNegocio != null

			&& !idUnidadeNegocio.trim().equals(

			"" + ConstantesSistema.NUMERO_NAO_INFORMADO)

			&& !idUnidadeNegocio.trim().equals("")) {

				sql = sql + " loc.uneg_id = " + idUnidadeNegocio + " and ";

			}

			// Gerência Regional

			if (idGerenciaRegional != null

			&& !idGerenciaRegional.trim().equals(

			"" + ConstantesSistema.NUMERO_NAO_INFORMADO)

			&& !idGerenciaRegional.trim().equals("")) {

				sql = sql + " loc.greg_id = " + idGerenciaRegional + " and ";

			}

			// Localidade

			if (idLocalidadeInicial != null

			&& !idLocalidadeInicial.trim().equals("")) {

				sql = sql + " loc.loca_id between " + idLocalidadeInicial

				+ " and " + idLocalidadeFinal + " and ";

			}

			// Perfil do Imóvel

			if (idsPerfilImovel != null && !idsPerfilImovel.equals("")) {

				String valoresIn = "";

				for (int i = 0; i < idsPerfilImovel.length; i++) {

					if (!idsPerfilImovel[i].equals("")

					&& !idsPerfilImovel[i].equals(""

					+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

						valoresIn = valoresIn + idsPerfilImovel[i] + ",";

					}

				}

				if (!valoresIn.equals("")) {

					sql = sql + " imov.iper_id in (" + valoresIn;

					sql = Util.removerUltimosCaracteres(sql, 1);

					sql = sql + ") and ";

				}

			}

			// Categoria

			if (idsCategoria != null && !idsCategoria.equals("")) {

				String valoresIn = "";

				for (int i = 0; i < idsCategoria.length; i++) {

					if (!idsCategoria[i].equals("")

					&& !idsCategoria[i].equals(""

					+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

						valoresIn = valoresIn + idsCategoria[i] + ",";

					}

				}

				if (!valoresIn.equals("")) {

					sql = sql + " subcategoria.catg_id in (" + valoresIn;

					sql = Util.removerUltimosCaracteres(sql, 1);

					sql = sql + ") and ";

				}

			}

			// Subcategoria

			if (idsSubcategoria != null && !idsSubcategoria.equals("")) {

				String valoresIn = "";

				for (int i = 0; i < idsSubcategoria.length; i++) {

					if (!idsSubcategoria[i].equals("")

					&& !idsSubcategoria[i].equals(""

					+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

						valoresIn = valoresIn + idsSubcategoria[i] + ",";

					}

				}

				if (!valoresIn.equals("")) {

					sql = sql + " subcategoria.scat_id in (" + valoresIn;

					sql = Util.removerUltimosCaracteres(sql, 1);

					sql = sql + ") and ";

				}

			}

			// Situação de Água

			if (idSituacaoAgua != null

			&& !idSituacaoAgua.trim().equals(

			"" + ConstantesSistema.NUMERO_NAO_INFORMADO)

			&& !idSituacaoAgua.trim().equals("")) {

				sql = sql + " imov.last_id = " + idSituacaoAgua + " and ";

			}

			// Situação de Esgoto

			if (idSituacaoEsgoto != null

			&& !idSituacaoEsgoto.trim().equals(

			"" + ConstantesSistema.NUMERO_NAO_INFORMADO)

			&& !idSituacaoEsgoto.trim().equals("")) {

				sql = sql + " imov.lest_id = " + idSituacaoEsgoto + " and ";

			}

			// Quantidade de Economias

			if (qtdeEconomiasInicial != null

			&& !qtdeEconomiasInicial.trim().equals("")) {

				sql = sql + " imov.imov_qteconomia between "

				+ qtdeEconomiasInicial + " and " + qtdeEconomiasFinal

				+ " and ";

			}

			// Intervalo de Consumo de Água

			if (intervaloConsumoAguaInicial != null

			&& !intervaloConsumoAguaInicial.trim().equals("")) {

				sql = sql + " consHistAgua.cshi_nnconsumofaturadomes between "

				+ intervaloConsumoAguaInicial + " and "

				+ intervaloConsumoAguaFinal + " and ";

			}

			// Intervalo de Consumo de Esgoto

			if (intervaloConsumoEsgotoInicial != null

			&& !intervaloConsumoEsgotoInicial.trim().equals("")) {

				sql = sql

				+ " consHistEsgoto.cshi_nnconsumofaturadomes between "

				+ intervaloConsumoEsgotoInicial + " and "

				+ intervaloConsumoEsgotoFinal + " and ";

			}

			// Data Instalação do Hidrômetro

			if (dataInstalacaoHidrometroInicial != null) {

				String dataInicial = Util

				.recuperaDataInvertida(dataInstalacaoHidrometroInicial);

				String dataFinal = Util

				.recuperaDataInvertida(dataInstalacaoHidrometroFinal);

				dataInicial = dataInicial.substring(0, 4) + "-"

				+ dataInicial.substring(4, 6) + "-"

				+ dataInicial.substring(6, 8);

				dataFinal = dataFinal.substring(0, 4) + "-"

				+ dataFinal.substring(4, 6) + "-"

				+ dataFinal.substring(6, 8);

				sql = sql

				+ " hidrInstHist.hidi_dtinstalacaohidrometro between to_date('"

				+ dataInicial + "','YYYY-MM-DD HH24:MI:SS') and to_date('" + dataFinal + "','YYYY-MM-DD HH24:MI:SS') and ";

			}

			// Capacidade do Hidrômetro

			if (idsCapacidadesHidrometro != null

			&& !idsCapacidadesHidrometro.equals("")) {

				String valoresIn = "";

				for (int i = 0; i < idsCapacidadesHidrometro.length; i++) {

					if (!idsCapacidadesHidrometro[i].equals("")

					&& !idsCapacidadesHidrometro[i].equals(""

					+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

						valoresIn = valoresIn + idsCapacidadesHidrometro[i]

						+ ",";

					}

				}

				if (!valoresIn.equals("")) {

					sql = sql + " hidr.hicp_id in (" + valoresIn;

					sql = Util.removerUltimosCaracteres(sql, 1);

					sql = sql + ") and ";

				}

			}

			// Tarifa de Consumo

			if (idsTarifasConsumo != null && !idsTarifasConsumo.equals("")) {

				String valoresIn = "";

				for (int i = 0; i < idsTarifasConsumo.length; i++) {

					if (!idsTarifasConsumo[i].equals("")

					&& !idsTarifasConsumo[i].equals(""

					+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

						valoresIn = valoresIn + idsTarifasConsumo[i] + ",";

					}

				}

				if (!valoresIn.equals("")) {

					sql = sql + " imov.cstf_id in (" + valoresIn;

					sql = Util.removerUltimosCaracteres(sql, 1);

					sql = sql + ") and ";

				}

			}

			// Anormalidade de Leitura

			if (idLeituraAnormalidade != null

			&& !idLeituraAnormalidade.trim().equals("")) {

				sql = sql + " medHist.ltan_idleitanormfatmt = "

				+ idLeituraAnormalidade + " and ";

			}

			if (leituraAnormalidade != null

			&& !leituraAnormalidade.trim().equals("")) {

				if (leituraAnormalidade.trim().equals("1")) {

					sql = sql

							+ " medHist.ltan_idleitanormfatmt is not null and ";

				} else {

					sql = sql

							+ " medHist.ltan_idleitanormfatmt is null and ";

				}

			}

			// Anormalidade de Consumo

			if (idConsumoAnormalidade != null

			&& !idConsumoAnormalidade.trim().equals(

			"" + ConstantesSistema.NUMERO_NAO_INFORMADO)

			&& !idConsumoAnormalidade.trim().equals("")) {

				sql = sql + " consHistAgua.csan_id = " + idConsumoAnormalidade

				+ " and ";

			}

			if (consumoAnormalidade != null

			&& !consumoAnormalidade.trim().equals("")) {

				if (consumoAnormalidade.trim().equals("1")) {

					sql = sql + " consHistAgua.csan_id is not null and ";

				} else {

					sql = sql + " consHistAgua.csan_id is null and ";

				}

			}

			sql = sql + " clieImov.clie_id = clieImovResponsavel.clie_id "

			+ " and clieImov.crtp_id = "

			+ ClienteRelacaoTipo.RESPONSAVEL.toString()

			+ " and clieImov.clim_dtrelacaofim is null" + " ) between "

			+ intervaloConsumoResponsavelInicial + " and "

			+ intervaloConsumoResponsavelFinal + " ) and ";

		}

		// Data Instalação do Hidrômetro

		if (dataInstalacaoHidrometroInicial != null) {

			String dataInicial = Util

			.recuperaDataInvertida(dataInstalacaoHidrometroInicial);

			String dataFinal = Util

			.recuperaDataInvertida(dataInstalacaoHidrometroFinal);

			dataInicial = dataInicial.substring(0, 4) + "-"

			+ dataInicial.substring(4, 6) + "-"

			+ dataInicial.substring(6, 8);

			dataFinal = dataFinal.substring(0, 4) + "-"

			+ dataFinal.substring(4, 6) + "-"

			+ dataFinal.substring(6, 8);

			sql = sql + " hidrInstHist.hidi_dtinstalacaohidrometro between to_date('"

			+ dataInicial + "','YYYY-MM-DD HH24:MI:SS') and to_date('" + dataFinal + "','YYYY-MM-DD HH24:MI:SS') and ";

		}

		// Capacidade do Hidrômetro

		if (idsCapacidadesHidrometro != null

		&& !idsCapacidadesHidrometro.equals("")) {

			String valoresIn = "";

			for (int i = 0; i < idsCapacidadesHidrometro.length; i++) {

				if (!idsCapacidadesHidrometro[i].equals("")

				&& !idsCapacidadesHidrometro[i].equals(""

				+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					valoresIn = valoresIn + idsCapacidadesHidrometro[i] + ",";

				}

			}

			if (!valoresIn.equals("")) {

				sql = sql + " hidr.hicp_id in (" + valoresIn;

				sql = Util.removerUltimosCaracteres(sql, 1);

				sql = sql + ") and ";

			}

		}

		// Tarifa de Consumo

		if (idsTarifasConsumo != null && !idsTarifasConsumo.equals("")) {

			String valoresIn = "";

			for (int i = 0; i < idsTarifasConsumo.length; i++) {

				if (!idsTarifasConsumo[i].equals("")

				&& !idsTarifasConsumo[i].equals(""

				+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					valoresIn = valoresIn + idsTarifasConsumo[i] + ",";

				}

			}

			if (!valoresIn.equals("")) {

				sql = sql + " imov.cstf_id in (" + valoresIn;

				sql = Util.removerUltimosCaracteres(sql, 1);

				sql = sql + ") and ";

			}

		}

		// Anormalidade de Leitura

		if (idLeituraAnormalidade != null

		&& !idLeituraAnormalidade.trim().equals("")) {

			sql = sql + " medHist.ltan_idleitanormfatmt = "

			+ idLeituraAnormalidade + " and ";

		}

		if (leituraAnormalidade != null

		&& !leituraAnormalidade.trim().equals("")) {

			if (leituraAnormalidade.trim().equals("1")) {

				sql = sql

						+ " medHist.ltan_idleitanormfatmt is not null and ";

			} else {

				sql = sql

				+ " medHist.ltan_idleitanormfatmt is null and ";

			}

		}

		// Anormalidade de Consumo

		if (idConsumoAnormalidade != null

		&& !idConsumoAnormalidade.trim().equals(

		"" + ConstantesSistema.NUMERO_NAO_INFORMADO)

		&& !idConsumoAnormalidade.trim().equals("")) {

			sql = sql + " consHistAgua.csan_id = " + idConsumoAnormalidade

			+ " and ";

		}

		if (consumoAnormalidade != null

		&& !consumoAnormalidade.trim().equals("")) {

			if (consumoAnormalidade.trim().equals("1")) {

				sql = sql + " consHistAgua.csan_id is not null and ";

			} else {

				sql = sql + " consHistAgua.csan_id is null and ";

			}

		}

		// retira o " and " q fica sobrando no final da query

		sql = Util.removerUltimosCaracteres(sql, 4);

		return sql;

	}

	/**
	 * 
	 * Pesquisa todos os ids do perfil do imóvel.
	 * 
	 * 
	 * 
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 * 
	 * 
	 * 
	 * @author Pedro Alexandre
	 * 
	 * @date 25/04/2007
	 * 
	 * 
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Collection<Integer> pesquisarTodosIdsPerfilImovel()

	throws ErroRepositorioException {

		Collection<Integer> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "select iper.id from ImovelPerfil iper";

			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * 
	 * Obtém a descrição de uma categoria
	 * 
	 * 
	 * 
	 * @author Rafael Corrêa
	 * 
	 * @date 04/06/2007
	 * 
	 * 
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public String obterDescricaoSubcategoria(Integer idSubcategoria)

	throws ErroRepositorioException {

		String descricao = null;

		// Query

		String consulta;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			consulta = "select sub.descricao from Subcategoria sub "

			+ " where sub.id = :idSubcategoria";

			descricao = (String) session.createQuery(consulta).setInteger(

			"idSubcategoria", idSubcategoria).setMaxResults(1)

			.uniqueResult();

			// erro no hibernate

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		// retorna a coleção de atividades pesquisada(s)

		return descricao;

	}

	/**
	 * 
	 * Pesquisar Rota do imovel
	 * 
	 */

	public FaturamentoGrupo pesquisarGrupoImovel(Integer idImovel) throws ErroRepositorioException {

		FaturamentoGrupo retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			//Realiza a consulta atraves da rota alternativa
			consulta = "select faturamentoGrupo from Imovel imovel"
				+ " left join imovel.rotaAlternativa rotaAlternativa "
				+ " left join rotaAlternativa.faturamentoGrupo faturamentoGrupo"
				+ " where imovel.id =:idImovel";

			retorno = (FaturamentoGrupo) session.createQuery(consulta)
				.setInteger("idImovel", idImovel).setMaxResults(1).uniqueResult();
			
			//Caso o imovel nao tenha rota alternativa retorna o grupo atraves da quadra
			if ( retorno == null ) {
			
				consulta = "select faturamentoGrupo from Imovel imovel"
					+ " inner join imovel.quadra quadra"
					+ " inner join quadra.rota rota"
					+ " inner join rota.faturamentoGrupo faturamentoGrupo"
					+ " where imovel.id =:idImovel";
	
				retorno = (FaturamentoGrupo) session.createQuery(consulta)
					.setInteger("idImovel", idImovel).setMaxResults(1).uniqueResult();
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
	 * 
	 * 
	 * Gerar Relatório de Imóvel Outros Critérios
	 * 
	 * 
	 * 
	 * @author Rafael Corrêa,Rafael Santos
	 * 
	 * @date 24/07/2006,01/08/2006
	 * 
	 * 
	 * 
	 */

	public Collection gerarRelatorioImovelEnderecoOutrosCriterios(
							String idImovelCondominio, 
							String idImovelPrincipal,
							String idSituacaoLigacaoAgua, 
							String consumoMinimoInicialAgua,
							String consumoMinimoFinalAgua, 
							String idSituacaoLigacaoEsgoto,
							String consumoMinimoInicialEsgoto, 
							String consumoMinimoFinalEsgoto,
							String intervaloValorPercentualEsgotoInicial,
							String intervaloValorPercentualEsgotoFinal,
							String intervaloMediaMinimaImovelInicial,
							String intervaloMediaMinimaImovelFinal,
							String intervaloMediaMinimaHidrometroInicial,
							String intervaloMediaMinimaHidrometroFinal, 
							String idImovelPerfil,
							String idPocoTipo, 
							String idFaturamentoSituacaoTipo,
							String idCobrancaSituacaoTipo, 
							String idSituacaoEspecialCobranca,
							String idEloAnormalidade, 
							String areaConstruidaInicial,
							String areaConstruidaFinal, 
							String idCadastroOcorrencia,
							String idConsumoTarifa, 
							String idGerenciaRegional,
							String idLocalidadeInicial, 
							String idLocalidadeFinal,
							String setorComercialInicial, 
							String setorComercialFinal,
							String quadraInicial, 
							String quadraFinal, 
							String loteOrigem,
							String loteDestno, 
							String cep, 
							String logradouro, 
							String bairro,
							String municipio, 
							String idTipoMedicao, 
							String indicadorMedicao,
							String idSubCategoria, 
							String idCategoria,
							String quantidadeEconomiasInicial, 
							String quantidadeEconomiasFinal,
							String diaVencimento, 
							String idCliente, 
							String idClienteTipo,
							String idClienteRelacaoTipo, 
							String numeroPontosInicial,
							String numeroPontosFinal, 
							String numeroMoradoresInicial,
							String numeroMoradoresFinal, 
							String idAreaConstruidaFaixa,
							String idUnidadeNegocio, String seqRotaInicial,
							String seqRotaFinal, 
							String rotaInicial, String rotaFinal,
							String ordenacaoRelatorio) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = consulta	+ "select distinct "
					/* 00 */+ "gerencia_regional.greg_id, "
					/* 01 */+ "gerencia_regional.greg_nmabreviado, "
					/* 02 */+ "localidade.loca_id, "
					/* 03 */+ "localidade.loca_nmlocalidade, "
					/* 04 */+ "imovel.imov_id, "
					/* 05 */+ "imovel.imov_qteconomia, "
					/* 06 */+ "setor_comercial.stcm_cdsetorcomercial, "
					/* 07 */+ "setor_comercial.stcm_nmsetorcomercial, "
					/* 08 */+ "quadra.qdra_nnquadra, "
					/* 09 */+ "imovel.imov_nnlote, "
					/* 10 */+ "imovel.imov_nnsublote, "
					/* 11 */+ "ligacao_agua_situacao.last_dsabreviado, "
					/* 12 */+ "ligacao_esgoto_situacao.lest_dsabreviado, "
					/* 13 */+ "ligacao_esgoto.lesg_pcesgoto, "
					/* 14 */+ "ligacao_esgoto.lesg_dtligacao, "
					/* 15 */+ "ligacao_agua.lagu_dtligacaoagua, "
					// Informações do Cliente Usuasrio e Resposanvel
					/* 16 */+ "cliente_usuario.clie_id as idClienteUsuario, "
					/* 17 */+ "cliente_usuario.clie_nmcliente as nomeClienteUsuario, "
					/* 18 */+ "cliente_responsavel.clie_id as idClienteResponsavel, "
					/* 19 */+ "cliente_responsavel.clie_nmcliente as nomeClienteResponsavel, "
					/* 20 */+ "logradouro.logr_nmlogradouro, "
					/* 21 */+ "logradouro_tipo.lgtp_dslogradourotipo, "
					/* 22 */+ "logradouro_titulo.lgtt_dslogradourotitulo, "
					/* 23 */+ "cep.cep_cdcep, "
					/* 24 */+ "endereco_referencia.edrf_dsenderecoreferencia, "
					/* 25 */+ "imovel.imov_dscomplementoendereco, "
					/* 26 */+ "imovel.imov_nnimovel, "
					/* 27 */+ "bairro.bair_nmbairro, "
					/* 28 */+ "municipio.muni_nmmunicipio, "
					/* 29 */+ "unidade_federacao.unfe_dsufsigla, "
					/* 30 */+ "imovel.imov_icimovelcondominio, "
					/* 31 */+ "imovel.imov_nnmorador, "
					/* 32 */+ "imovel.imov_idimovelcondominio, "
					/* 33 */+ "imovel.imov_idimovelprincipal, "
					/* 34 */+ "imovel.imov_nnpontosutilizacao, "
					/* 35 */+ "imovel_perfil.iper_dsimovelperfil, "
					/* 36 */+ "area_construida_faixa.acon_nnmaiorfaixa, "
					/* 37 */+ "area_construida_faixa.acon_nnmenorfaixa, "
					/* 38 */+ "imovel.imov_nnareaconstruida, "
					/* 39 */+ "pavimento_calcada.pcal_dspavimentocalcada, "
					/* 40 */+ "pavimento_rua.prua_dspavimentorua, "
					/* 41 */+ "despejo.depj_dsdespejo, "
					/* 42 */+ "reservatorio_volume_fx.resv_vomenorfaixa, "
					/* 43 */+ "reservatorio_volume_fx.resv_vomaiorfaixa, "
					/* 44 */+ "imovel.imov_voreservatoriosuperior, "
					/* 45 */+ "reservatorio_volume_fx_infe.resv_vomenorfaixa, "
					/* 46 */+ "reservatorio_volume_fx_infe.resv_vomaiorfaixa, "
					/* 47 */+ "imovel.imov_voreservatorioinferior, "
					/* 48 */+ "piscina_volume_faixa.pisc_vomenorfaixa, "
					/* 49 */+ "piscina_volume_faixa.pisc_vomaiorfaixa, "
					/* 50 */+ "imovel.imov_vopiscina, "
					/* 51 */+ "poco_tipo.poco_dspocotipo, "
					/* 52 */+ "ligacao_agua_diametro.lagd_dsligacaoaguadiametro as descricaoligacaoaguadiametroag, "
					/* 53 */+ "ligacao_agua_material.lagm_dsligacaoaguamaterial as descricaoligacaoaguamaterialag, "
					/* 54 */+ "ligacao_esgoto_diametro.legd_dsligacaoesgotodiametro as descricaoligacaoesgotodiametro, "
					/* 55 */+ "ligacao_esgoto_material.legm_dsligacaoesgotomaterial as descricaoligacaoesgotomaterial, "
					/* 56 */+ "ligacao_esgoto.lesg_nnconsumominimoesgoto, "
					/* 57 */+ "ligacao_esgoto.lesg_pccoleta, "
					/* 58 */+ "ligacao_esgoto.lesg_pcesgoto, "
					// Agua
					/* 59 */+ "hidrometro.hidr_nnhidrometro as idHidrometroAgua, "
					/* 61 */+ "hidrometro.hidr_nnanofabricacao as anoFabricancaoHidrometroAgua, "
					/* 62 */+ "hidrometro_capacidade.hicp_dshidrometrocapacidade as descricaohidrometrocapacidadea, "
					/* 63 */+ "hidrometro_marca.himc_dshidrometromarca as descricaoHidrometroMarcaAgua, "
					/* 64 */+ "hidrometro_diametro.hidm_dshidrometrodiametro as descricaohidrometrodiametroagu, "
					/* 65 */+ "hidrometro_tipo.hitp_dshidrometrotipo as descricaoHidrometroTipoAgua, "
					/* 66 */+ "hidrometro_inst_hist.hidi_dtinstalacaohidrometro as datahidrometroinstalacaohisto2, "
					/* 67 */+ "hidrometro_local_inst.hili_dshidmtlocalinstalacao as descricaohidrometrolocalinstal, "
					/* 68 */+ "hidrometro_protecao.hipr_dshidrometroprotecao as descricaohidrometroprotecaoagu, "
					/* 69 */+ "hidrometro_inst_hist.hidi_iccavalete as indicadorhidrometroinstalacaoh, "
					// Esgoto
					/* 70 */+ "hidrometro_esgoto.hidr_nnhidrometro as idHidrometroEsgoto, "
					/* 71 */+ "hidrometro_esgoto.hidr_nnanofabricacao as anoFabricacaoHidrometroEsgoto, "
					/* 72 */+ "hidrometro_capacidade_esgoto.hicp_dshidrometrocapacidade as descricaohidrometrocapacidadee, "
					/* 73 */+ "hidrometro_marca_esgoto.himc_dshidrometromarca as descricaoHidrometroMarcaEsgoto, "
					/* 74 */+ "hidrometro_diametro_esgoto.hidm_dshidrometrodiametro as descricaohidrometrodiametroesg, "
					/* 75 */+ "hidrometro_tipo_esgoto.hitp_dshidrometrotipo as descricaoHidrometroTipoEsgoto, "
					/* 76 */+ "hidrometro_instalacao_historic.hidi_dtinstalacaohidrometro as datahidrometroinstalacaohistor, "
					/* 77 */+ "hidrometro_local_inst_es.hili_dshidmtlocalinstalacao as descricaohidtrometrolocalinsta, "
					/* 78 */+ "hidrometro_protecao_esgoto.hipr_dshidrometroprotecao as descricaohidrometroprotecaoesg, "
					/* 79 */+ "hidrometro_instalacao_historic.hidi_iccavalete as indicadorhidrometroinstalacaoe, "
					// Ligacao Agua
					/* 80 */+ "ligacao_agua.lagu_nnconsumominimoagua, "
					// Ligacao Esgoto
					/* 81 */+ "ligacao_esgoto.lesg_nnconsumominimoesgoto, "
					// Jardim
					/* 82 */+ "imovel.imov_icjardim, "
					// Rota
					/* 83 */+ "rota.rota_cdrota, "
					// Sequencial Rota
					/* 84 */+ "imovel.imov_nnsequencialrota "
					// From
					+ "from cadastro.imovel_subcategoria imovelSubcategoria "
					+ "inner join cadastro.imovel on imovelSubcategoria.imov_id = cadastro.imovel.imov_id "
					+ "inner join cadastro.localidade on imovel.loca_id = cadastro.localidade.loca_id "
					+ "inner join cadastro.gerencia_regional on cadastro.localidade.greg_id = cadastro.gerencia_regional.greg_id "
					+ "inner join cadastro.setor_comercial on cadastro.imovel.stcm_id = cadastro.setor_comercial.stcm_id "
					// Logradouro Bairro
					+ "left join cadastro.logradouro_bairro on cadastro.imovel.lgbr_id = cadastro.logradouro_bairro.lgbr_id "
					+ "left join cadastro.bairro on cadastro.logradouro_bairro.bair_id = cadastro.bairro.bair_id "
					+ "left join cadastro.municipio on cadastro.bairro.muni_id = cadastro.municipio.muni_id "
					+ "inner join cadastro.quadra on cadastro.imovel.qdra_id = cadastro.quadra.qdra_id "
					+ "inner join micromedicao.rota on cadastro.quadra.rota_id = micromedicao.rota.rota_id "
					// Logradouro Cep
					+ "left join cadastro.logradouro_cep on cadastro.imovel.lgcp_id = cadastro.logradouro_cep.lgcp_id "
					+ "left join cadastro.cep on cadastro.logradouro_cep.cep_id = cadastro.cep.cep_id "
					+ "left join cadastro.logradouro on cadastro.logradouro_cep.logr_id = cadastro.logradouro.logr_id "
					// AGUA
					+ "left join atendimentopublico.ligacao_agua_situacao on cadastro.imovel.last_id = atendimentopublico.ligacao_agua_situacao.last_id "
					+ "left join atendimentopublico.ligacao_agua on cadastro.imovel.imov_id = atendimentopublico.ligacao_agua.lagu_id "
					// ESGOTO
					+ "left join atendimentopublico.ligacao_esgoto_situacao on cadastro.imovel.lest_id = atendimentopublico.ligacao_esgoto_situacao.lest_id "
					+ "left join atendimentopublico.ligacao_esgoto on cadastro.imovel.imov_id = atendimentopublico.ligacao_esgoto.lesg_id "
					+ "left join cadastro.imovel_perfil on cadastro.imovel.iper_id = cadastro.imovel_perfil.iper_id "
					+ "left join cadastro.poco_tipo on cadastro.imovel.poco_id = cadastro.poco_tipo.poco_id "
					+ "left join cadastro.area_construida_faixa on cadastro.imovel.acon_id = area_construida_faixa.acon_id "
					// Cliente Usuario
					+ "left outer join cadastro.cliente_imovel as cliente_imovel_usuario on cadastro.imovel.imov_id = cliente_imovel_usuario.imov_id  "
					+ " and (cliente_imovel_usuario.crtp_id=2 and cliente_imovel_usuario.clim_dtrelacaofim is null) "
					+ "left outer join cadastro.cliente as cliente_usuario on cliente_imovel_usuario.clie_id = cliente_usuario.clie_id "
					// Cliente Resposanvel
					+ "left outer join cadastro.cliente_imovel as cliente_imovel_responsavel on cadastro.imovel.imov_id = cliente_imovel_responsavel.imov_id "
					+ " and (cliente_imovel_responsavel.crtp_id=3 and cliente_imovel_responsavel.clim_dtrelacaofim is null) "
					+ "left outer join cadastro.cliente as cliente_responsavel on cliente_imovel_responsavel.clie_id = cliente_responsavel.clie_id "
					// AGUA
					+ "left join micromedicao.hidrometro_inst_hist on atendimentopublico.ligacao_agua.hidi_id =  micromedicao.hidrometro_inst_hist.hidi_id "
					// ESGOTO
					+ "left join micromedicao.hidrometro_inst_hist hidrometro_instalacao_historic on cadastro.imovel.hidi_id =  hidrometro_instalacao_historic.hidi_id "
					// Relacionamento para o Relatorio de Imovel
					+ "left join cadastro.logradouro_titulo on cadastro.logradouro.lgtt_id = cadastro.logradouro_titulo.lgtt_id "
					+ "left join cadastro.logradouro_tipo on cadastro.logradouro.lgtp_id = cadastro.logradouro_tipo.lgtp_id "
					+ "left join cadastro.endereco_referencia on cadastro.imovel.edrf_id = cadastro.endereco_referencia.edrf_id "
					+ "left join cadastro.unidade_federacao on cadastro.municipio.unfe_id = cadastro.unidade_federacao.unfe_id "
					+ "left join cadastro.reservatorio_volume_fx on cadastro.imovel.resv_idreservatoriosuperior = cadastro.reservatorio_volume_fx.resv_id "
					+ "left join cadastro.reservatorio_volume_fx reservatorio_volume_fx_infe on cadastro.imovel.resv_idreservatorioinferior = reservatorio_volume_fx_infe.resv_id "
					+ "left join cadastro.piscina_volume_faixa on cadastro.imovel.pisc_id = cadastro.piscina_volume_faixa.pisc_id "
					+ "left join cadastro.pavimento_calcada on cadastro.imovel.pcal_id = cadastro.pavimento_calcada.pcal_id "
					+ "left join cadastro.pavimento_rua on cadastro.imovel.prua_id = cadastro.pavimento_rua.prua_id "
					+ "left join cadastro.despejo on cadastro.imovel.depj_id = cadastro.despejo.depj_id "
					// AGUA
					+ "left join atendimentopublico.ligacao_agua_diametro on atendimentopublico.ligacao_agua.lagd_id = atendimentopublico.ligacao_agua_diametro.lagd_id "
					+ "left join atendimentopublico.ligacao_agua_material on atendimentopublico.ligacao_agua.lagm_id = atendimentopublico.ligacao_agua_material.lagm_id "
					// ESGOTO
					+ "left join atendimentopublico.ligacao_esgoto_diametro on atendimentopublico.ligacao_esgoto.legd_id = atendimentopublico.ligacao_esgoto_diametro.legd_id "
					+ "left join atendimentopublico.ligacao_esgoto_material on atendimentopublico.ligacao_esgoto.legm_id = atendimentopublico.ligacao_esgoto_material.legm_id "
					// AGUA
					+ "left join micromedicao.hidrometro on micromedicao.hidrometro_inst_hist.hidr_id = micromedicao.hidrometro.hidr_id "
					+ "left join micromedicao.hidrometro_capacidade on micromedicao.hidrometro.hicp_id = micromedicao.hidrometro_capacidade.hicp_id "
					+ "left join micromedicao.hidrometro_marca on micromedicao.hidrometro.himc_id = micromedicao.hidrometro_marca.himc_id "
					+ "left join micromedicao.hidrometro_diametro on micromedicao.hidrometro.hidm_id = micromedicao.hidrometro_diametro.hidm_id "
					+ "left join micromedicao.hidrometro_tipo on micromedicao.hidrometro.hitp_id = micromedicao.hidrometro_tipo.hitp_id "
					+ "left join micromedicao.hidrometro_local_inst on micromedicao.hidrometro_inst_hist.hili_id = micromedicao.hidrometro_local_inst.hili_id "
					+ "left join micromedicao.hidrometro_protecao on micromedicao.hidrometro_inst_hist.hipr_id = micromedicao.hidrometro_protecao.hipr_id "
					// ESGOTO
					+ "left join micromedicao.hidrometro as hidrometro_esgoto on hidrometro_instalacao_historic.hidr_id = hidrometro_esgoto.hidr_id "
					+ "left join micromedicao.hidrometro_capacidade as hidrometro_capacidade_esgoto on hidrometro_esgoto.hicp_id = hidrometro_capacidade_esgoto.hicp_id "
					+ "left join micromedicao.hidrometro_marca as hidrometro_marca_esgoto on hidrometro_esgoto.himc_id = hidrometro_marca_esgoto.himc_id "
					+ "left join micromedicao.hidrometro_diametro as hidrometro_diametro_esgoto on hidrometro_esgoto.hidm_id = hidrometro_diametro_esgoto.hidm_id "
					+ "left join micromedicao.hidrometro_tipo as hidrometro_tipo_esgoto on hidrometro_esgoto.hitp_id = hidrometro_tipo_esgoto.hitp_id "
					+ "left join micromedicao.hidrometro_local_inst as hidrometro_local_inst_es on hidrometro_instalacao_historic.hili_id = hidrometro_local_inst.hili_id "
					+ "left join micromedicao.hidrometro_protecao as hidrometro_protecao_esgoto on hidrometro_instalacao_historic.hipr_id = hidrometro_protecao_esgoto.hipr_id ";

			consulta = consulta + montarInnerJoinFiltrarImoveisOutrosCriterios(
												intervaloMediaMinimaImovelInicial,
												intervaloMediaMinimaImovelFinal,
												intervaloMediaMinimaHidrometroInicial,
												intervaloMediaMinimaHidrometroFinal,
												idFaturamentoSituacaoTipo, idCobrancaSituacaoTipo,
												idSituacaoEspecialCobranca, idEloAnormalidade,
												idCadastroOcorrencia, idConsumoTarifa,
												idTipoMedicao, indicadorMedicao, idSubCategoria,
												idCategoria, idCliente, idClienteTipo,
												idClienteRelacaoTipo,
												ConstantesSistema.GERAR_RELATORIO_IMOVEL, 
												null);
			
			consulta = consulta + montarCondicaoSQLWhereFiltrarImovelOutrosCriterio(
										idImovelCondominio, 
										idImovelPrincipal,
										idSituacaoLigacaoAgua, 
										consumoMinimoInicialAgua,
										consumoMinimoFinalAgua, 
										idSituacaoLigacaoEsgoto,
										consumoMinimoInicialEsgoto,
										consumoMinimoFinalEsgoto,
										intervaloValorPercentualEsgotoInicial,
										intervaloValorPercentualEsgotoFinal,
										intervaloMediaMinimaImovelInicial,
										intervaloMediaMinimaImovelFinal,
										intervaloMediaMinimaHidrometroInicial,
										intervaloMediaMinimaHidrometroFinal,
										idImovelPerfil, 
										idPocoTipo,
										idFaturamentoSituacaoTipo, 
										idCobrancaSituacaoTipo,
										idSituacaoEspecialCobranca, 
										idEloAnormalidade,
										areaConstruidaInicial, 
										areaConstruidaFinal,
										idCadastroOcorrencia, 
										idConsumoTarifa,
										idGerenciaRegional, 
										idLocalidadeInicial,
										idLocalidadeFinal,
										setorComercialInicial,
										setorComercialFinal, 
										quadraInicial, 
										quadraFinal,
										loteOrigem, 
										loteDestno, 
										cep, 
										logradouro, 
										bairro,
										municipio, 
										idTipoMedicao, 
										indicadorMedicao,
										idSubCategoria, 
										idCategoria,
										quantidadeEconomiasInicial,
										quantidadeEconomiasFinal, 
										diaVencimento, 
										idCliente,
										idClienteTipo, 
										idClienteRelacaoTipo,
										numeroPontosInicial, 
										numeroPontosFinal,
										numeroMoradoresInicial, 
										numeroMoradoresFinal,
										idAreaConstruidaFaixa, 
										idUnidadeNegocio,
										ConstantesSistema.GERAR_RELATORIO_IMOVEL, rotaInicial, rotaFinal, 
										seqRotaInicial, seqRotaFinal, null, null , null);

			
			SQLQuery query = null;
			if(ordenacaoRelatorio != null && ordenacaoRelatorio.equalsIgnoreCase("endereco")){
				
				query = session.createSQLQuery((consulta.substring(0,
						(consulta.length() - 5)).concat(" order by 22, 21 , 27 , 26 ")));
			
			}else if ( ordenacaoRelatorio != null && !ordenacaoRelatorio.equals("") ) {

				query = session.createSQLQuery((consulta.substring(0,
						(consulta.length() - 5)).concat(" order by 2, 6, 83, 84")));

			} else {
				//loca, setor, rota , seq da rota, numero do lote e sub lote
				query = session.createSQLQuery((consulta.substring(0,
						(consulta.length() - 5)).concat(" order by 2, 6, 83, 84 , 9, 10 ")));
			}		
			
			
			
			informarDadosQueryFiltrarImovelOutrosCriterio(query,
					idImovelCondominio, 
					idImovelPrincipal,
					idSituacaoLigacaoAgua, 
					consumoMinimoInicialAgua,
					consumoMinimoFinalAgua, 
					idSituacaoLigacaoEsgoto,
					consumoMinimoInicialEsgoto, 
					consumoMinimoFinalEsgoto,
					intervaloValorPercentualEsgotoInicial,
					intervaloValorPercentualEsgotoFinal,
					intervaloMediaMinimaImovelInicial,
					intervaloMediaMinimaImovelFinal,
					intervaloMediaMinimaHidrometroInicial,
					intervaloMediaMinimaHidrometroFinal, 
					idImovelPerfil,
					idPocoTipo, 
					idFaturamentoSituacaoTipo,
					idCobrancaSituacaoTipo, 
					idSituacaoEspecialCobranca,
					idEloAnormalidade, 
					areaConstruidaInicial,
					areaConstruidaFinal, 
					idCadastroOcorrencia, 
					idConsumoTarifa,
					idGerenciaRegional, 
					idLocalidadeInicial, 
					idLocalidadeFinal,
					setorComercialInicial, 
					setorComercialFinal, 
					quadraInicial,
					quadraFinal, 
					loteOrigem, 
					loteDestno, 
					cep, 
					logradouro,
					bairro, 
					municipio, 
					idTipoMedicao, 
					indicadorMedicao,
					idSubCategoria, 
					idCategoria, 
					quantidadeEconomiasInicial,
					quantidadeEconomiasFinal, 
					diaVencimento, 
					idCliente,
					idClienteTipo, 
					idClienteRelacaoTipo, 
					numeroPontosInicial,
					numeroPontosFinal, 
					numeroMoradoresInicial,
					numeroMoradoresFinal, 
					idAreaConstruidaFaixa,
					idUnidadeNegocio, rotaInicial, rotaFinal, seqRotaInicial, seqRotaFinal, null, null);
			
			retorno = query
			// .addScalar("imov_id",Hibernate.INTEGER)
					.addScalar("greg_id", Hibernate.INTEGER)
					.addScalar("greg_nmabreviado", Hibernate.STRING)
					.addScalar("loca_id", Hibernate.INTEGER)
					.addScalar("loca_nmlocalidade", Hibernate.STRING)
					.addScalar("imov_id", Hibernate.INTEGER)
					.addScalar("imov_qteconomia", Hibernate.SHORT)
					.addScalar("stcm_cdsetorcomercial", Hibernate.INTEGER)
					.addScalar("stcm_nmsetorcomercial", Hibernate.STRING)
					.addScalar("qdra_nnquadra", Hibernate.INTEGER)
					.addScalar("imov_nnlote", Hibernate.SHORT)
					.addScalar("imov_nnsublote", Hibernate.SHORT)
					.addScalar("last_dsabreviado", Hibernate.STRING)
					.addScalar("lest_dsabreviado", Hibernate.STRING)
					.addScalar("lesg_pcesgoto", Hibernate.BIG_DECIMAL)
					.addScalar("lesg_dtligacao", Hibernate.DATE)
					.addScalar("lagu_dtligacaoagua", Hibernate.DATE)
					.addScalar("idClienteUsuario", Hibernate.INTEGER)
					.addScalar("nomeClienteUsuario", Hibernate.STRING)
					.addScalar("idClienteResponsavel", Hibernate.INTEGER)
					.addScalar("nomeClienteResponsavel", Hibernate.STRING)
					.addScalar("logr_nmlogradouro", Hibernate.STRING)
					.addScalar("lgtp_dslogradourotipo", Hibernate.STRING)
					.addScalar("lgtt_dslogradourotitulo", Hibernate.STRING)
					.addScalar("cep_cdcep", Hibernate.INTEGER)
					.addScalar("edrf_dsenderecoreferencia", Hibernate.STRING)
					.addScalar("imov_dscomplementoendereco", Hibernate.STRING)
					.addScalar("imov_nnimovel", Hibernate.STRING)
					.addScalar("bair_nmbairro", Hibernate.STRING)
					.addScalar("muni_nmmunicipio", Hibernate.STRING)
					.addScalar("unfe_dsufsigla", Hibernate.STRING)
					.addScalar("imov_icimovelcondominio", Hibernate.INTEGER)
					.addScalar("imov_nnmorador", Hibernate.INTEGER)
					.addScalar("imov_idimovelcondominio", Hibernate.INTEGER)
					.addScalar("imov_idimovelprincipal", Hibernate.INTEGER)
					.addScalar("imov_nnpontosutilizacao", Hibernate.INTEGER)
					.addScalar("iper_dsimovelperfil", Hibernate.STRING)
					.addScalar("acon_nnmaiorfaixa", Hibernate.INTEGER)
					.addScalar("acon_nnmenorfaixa", Hibernate.INTEGER)
					.addScalar("imov_nnareaconstruida", Hibernate.BIG_DECIMAL)
					.addScalar("pcal_dspavimentocalcada", Hibernate.STRING)
					.addScalar("prua_dspavimentorua", Hibernate.STRING)
					.addScalar("depj_dsdespejo", Hibernate.STRING)
					.addScalar("resv_vomenorfaixa", Hibernate.BIG_DECIMAL)
					.addScalar("resv_vomaiorfaixa", Hibernate.BIG_DECIMAL)
					.addScalar("imov_voreservatoriosuperior",Hibernate.BIG_DECIMAL)
					.addScalar("resv_vomenorfaixa", Hibernate.BIG_DECIMAL)
					.addScalar("resv_vomaiorfaixa", Hibernate.BIG_DECIMAL)
					.addScalar("imov_voreservatorioinferior",Hibernate.BIG_DECIMAL)
					.addScalar("pisc_vomenorfaixa", Hibernate.BIG_DECIMAL)
					.addScalar("pisc_vomaiorfaixa", Hibernate.BIG_DECIMAL)
					.addScalar("imov_vopiscina", Hibernate.BIG_DECIMAL)
					.addScalar("poco_dspocotipo", Hibernate.STRING)
					.addScalar("descricaoligacaoaguadiametroag",Hibernate.STRING)
					.addScalar("descricaoligacaoaguamaterialag",Hibernate.STRING)
					.addScalar("descricaoligacaoesgotodiametro",Hibernate.STRING)
					.addScalar(	"descricaoligacaoesgotomaterial",Hibernate.STRING)
					.addScalar(	"lesg_nnconsumominimoesgoto", Hibernate.INTEGER)
					.addScalar("lesg_pccoleta", Hibernate.BIG_DECIMAL)
					.addScalar("lesg_pcesgoto", Hibernate.BIG_DECIMAL)
					.addScalar("idHidrometroAgua", Hibernate.STRING)
					.addScalar("anoFabricancaoHidrometroAgua", Hibernate.INTEGER)
					.addScalar("descricaohidrometrocapacidadea",	Hibernate.STRING)
					.addScalar("descricaoHidrometroMarcaAgua", Hibernate.STRING)
					.addScalar("descricaohidrometrodiametroagu",Hibernate.STRING)
					.addScalar("descricaoHidrometroTipoAgua", Hibernate.STRING)
					.addScalar("datahidrometroinstalacaohisto2",	Hibernate.DATE)
					.addScalar("descricaohidrometrolocalinstal",Hibernate.STRING)
					.addScalar("descricaohidrometroprotecaoagu",Hibernate.STRING)
					.addScalar("indicadorhidrometroinstalacaoh",Hibernate.INTEGER)
					.addScalar("idHidrometroEsgoto", Hibernate.STRING)
					.addScalar("anoFabricacaoHidrometroEsgoto",	Hibernate.INTEGER)
					.addScalar("descricaohidrometrocapacidadee",	Hibernate.STRING)
					.addScalar("descricaoHidrometroMarcaEsgoto", Hibernate.STRING)
					.addScalar("descricaohidrometrodiametroesg",	Hibernate.STRING)
					.addScalar("descricaoHidrometroTipoEsgoto", Hibernate.STRING)
					.addScalar("datahidrometroinstalacaohistor",Hibernate.DATE)
					.addScalar("descricaohidtrometrolocalinsta",	Hibernate.STRING)
					.addScalar("descricaohidrometroprotecaoesg",	Hibernate.STRING)
					.addScalar("indicadorhidrometroinstalacaoe",Hibernate.INTEGER)
					.addScalar("lagu_nnconsumominimoagua", Hibernate.INTEGER)
					.addScalar("lesg_nnconsumominimoesgoto", Hibernate.INTEGER)
					.addScalar("imov_icjardim", Hibernate.SHORT)
					.addScalar("rota_cdrota", Hibernate.SHORT)
					.addScalar("imov_nnsequencialrota", Hibernate.INTEGER)
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
	 * [UC00609] Transferencia de Debitos/Creditos
	 * 
	 * 
	 * 
	 * Recupera a situação da ligação de esgoto
	 * 
	 * 
	 * 
	 * @author Raphael Rossiter
	 * 
	 * @date 07/06/2007
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * @return LigacaoEsgotoSituacao
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public LigacaoEsgotoSituacao pesquisarLigacaoEsgotoSituacao(Integer idImovel)

	throws ErroRepositorioException {

		LigacaoEsgotoSituacao retorno = null;

		String consulta;

		Session session = HibernateUtil.getSession();

		try {

			consulta = "SELECT ligEsgSit "

			+ "FROM gcom.cadastro.imovel.Imovel  imovel "

			+ "INNER JOIN imovel.ligacaoEsgotoSituacao ligEsgSit "

			+ "WHERE imovel.id = :idImovel";

			retorno = (LigacaoEsgotoSituacao) session.createQuery(consulta)

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
	 * 
	 * [UC00609] Transferencia de Debitos/Creditos
	 * 
	 * 
	 * 
	 * Recupera a situação da ligação de agua
	 * 
	 * 
	 * 
	 * @author Raphael Rossiter
	 * 
	 * @date 07/06/2007
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * @return LigacaoAguaSituacao
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public LigacaoAguaSituacao pesquisarLigacaoAguaSituacao(Integer idImovel)

	throws ErroRepositorioException {

		LigacaoAguaSituacao retorno = null;

		String consulta;

		Session session = HibernateUtil.getSession();

		try {

			consulta = "SELECT ligAguaSit "

			+ "FROM gcom.cadastro.imovel.Imovel  imovel "

			+ "INNER JOIN imovel.ligacaoAguaSituacao ligAguaSit "

			+ "WHERE imovel.id = :idImovel";

			retorno = (LigacaoAguaSituacao) session.createQuery(consulta)

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
	 * 
	 * 
	 * 
	 * Gerar Relatório de Imóvel Outros Critérios
	 * 
	 * 
	 * 
	 * @author Rafael Corrêa,Rafael Santos
	 * 
	 * @date 24/07/2006,01/08/2006
	 * 
	 * 
	 * 
	 */

	public Collection gerarRelatorioCadastroConsumidoreInscricao(String idImovelCondominio, 
			String idImovelPrincipal,
			String idSituacaoLigacaoAgua, 
			String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, 
			String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, 
			String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, 
			String idImovelPerfil,
			String idPocoTipo, 
			String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, 
			String idSituacaoEspecialCobranca,
			String idEloAnormalidade, 
			String areaConstruidaInicial,
			String areaConstruidaFinal, 
			String idCadastroOcorrencia,
			String idConsumoTarifa, 
			String idGerenciaRegional,
			String idLocalidadeInicial, 
			String idLocalidadeFinal,
			String setorComercialInicial, 
			String setorComercialFinal,
			String quadraInicial, 
			String quadraFinal, 
			String loteOrigem,
			String loteDestno, 
			String cep, 
			String logradouro, 
			String bairro,
			String municipio, 
			String idTipoMedicao, 
			String indicadorMedicao,
			String idSubCategoria, 
			String idCategoria,
			String quantidadeEconomiasInicial, 
			String quantidadeEconomiasFinal,
			String diaVencimento, 
			String idCliente, 
			String idClienteTipo,
			String idClienteRelacaoTipo, 
			String numeroPontosInicial,
			String numeroPontosFinal,
			String numeroMoradoresInicial,
			String numeroMoradoresFinal,
			String idAreaConstruidaFaixa,
			String idUnidadeNegocio,
			String cdRotaInicial,
			String cdRotaFinal,
			String sequencialRotaInicial,
			String sequencialRotaFinal, 
			String ordenacao, 
			String[] pocoTipoListIds ) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = consulta + "select distinct "
								+ "gerencia_regional.greg_id, "
								+ "gerencia_regional.greg_nmabreviado, "
								+ "localidade.loca_id, "
								+ "localidade.loca_nmlocalidade, "
								+ "imovel.imov_id, "

					+ "imovel.imov_qteconomia, "

					+ "setor_comercial.stcm_cdsetorcomercial, "

					+ "setor_comercial.stcm_nmsetorcomercial, "

					+ "quadra.qdra_nnquadra, "

					+ "imovel.imov_nnlote, "

					+ "imovel.imov_nnsublote, "

					+ "ligacao_agua_situacao.last_id, "

					+ "ligacao_esgoto_situacao.lest_id, "

					+ "ligacao_esgoto.lesg_pcesgoto, "

					+ "ligacao_esgoto.lesg_dtligacao, "

					+ "ligacao_agua.lagu_dtligacaoagua, "

					

					// Informações do Cliente Usuasrio e Resposanvel

					+ "cliente_usuario.clie_id as idClienteUsuario, "

					+ "cliente_usuario.clie_nmcliente as nomeClienteUsuario, "

					+ "cliente_responsavel.clie_id as idClienteResponsavel, "

					+ "cliente_responsavel.clie_nmcliente as nomeClienteResponsavel, "

					

					+ "logradouro.logr_nmlogradouro, "

					+ "logradouro_tipo.lgtp_dslogradourotipo, "

					+ "logradouro_titulo.lgtt_dslogradourotitulo, "

					+ "cep.cep_cdcep, "

					+ "endereco_referencia.edrf_dsenderecoreferencia, "

					+ "imovel.imov_dscomplementoendereco, "

					+ "imovel.imov_nnimovel, "

					+ "bairro.bair_nmbairro, "

					+ "municipio.muni_nmmunicipio, "

					+ "unidade_federacao.unfe_dsufsigla, "

					+ "imovel.imov_icimovelcondominio, "

					+ "imovel.imov_nnmorador, "

					+ "imovel.imov_idimovelcondominio, "

					+ "imovel.imov_idimovelprincipal, "

					+ "imovel.imov_nnpontosutilizacao, "

					+ "imovel_perfil.iper_dsimovelperfil, "

					+ "area_construida_faixa.acon_nnmaiorfaixa, "

					+ "area_construida_faixa.acon_nnmenorfaixa, "

					+ "imovel.imov_nnareaconstruida, "

					+ "pavimento_calcada.pcal_id, "

					+ "pavimento_rua.prua_id, "

					+ "despejo.depj_id, "

					+ "reservatorio_volume_fx.resv_vomenorfaixa, "

					+ "reservatorio_volume_fx.resv_vomaiorfaixa, "

					+ "imovel.resv_idreservatoriosuperior, "

					+ "reservatorio_volume_fx_infe.resv_vomenorfaixa, "

					+ "reservatorio_volume_fx_infe.resv_vomaiorfaixa, "

					+ "imovel.resv_idreservatorioinferior, "

					+ "piscina_volume_faixa.pisc_vomenorfaixa, "

					+ "piscina_volume_faixa.pisc_vomaiorfaixa, "

					+ "imovel.pisc_id, "

					+ "poco_tipo.poco_dspocotipo, "

					+ "ligacao_agua_diametro.lagd_id as descricaoligacaoaguadiametroag, "

					+ "ligacao_agua_material.lagm_dsligacaoaguamaterial as descricaoligacaoaguamaterialag, "

					+ "ligacao_esgoto_diametro.legd_id as descricaoligacaoesgotodiametro, "

					+ "ligacao_esgoto_material.legm_dsligacaoesgotomaterial as descricaoligacaoesgotomaterial, "

					+ "ligacao_esgoto.lesg_nnconsumominimoesgoto, "

					+ "ligacao_esgoto.lesg_pccoleta, "

					+ "ligacao_esgoto.lesg_pcesgoto, "

					

					// Agua

					+ "hidrometro.hidr_nnhidrometro as idHidrometroAgua, "

					+ "hidrometro.hidr_nnanofabricacao as anoFabricancaoHidrometroAgua, "

					+ "hidrometro_capacidade.hicp_id as descricaohidrometrocapacidadea, "

					+ "hidrometro_marca.himc_id as descricaoHidrometroMarcaAgua, "

					+ "hidrometro_diametro.hidm_id as descricaohidrometrodiametroagu, "

					+ "hidrometro_tipo.hitp_id as descricaoHidrometroTipoAgua, "

					+ "hidrometro_inst_hist.hidi_dtinstalacaohidrometro as datahidrometroinstalacaohisto2, "

					+ "hidrometro_local_inst.hili_id as hidrometroLocalInstalacaoAgua, "

					// +

					// "hidrometro_local_inst.hili_dshidmtlocalinstalacao

					// as descricaohidrometrolocalinstal, "

					+ "hidrometro_protecao.hipr_id as descricaohidrometroprotecaoagu, "

					+ "hidrometro_inst_hist.hidi_iccavalete as indicadorhidrometroinstalacaoh, "

					

					// Esgoto

					+ "hidrometro_esgoto.hidr_nnhidrometro as idHidrometroEsgoto, "

					+ "hidrometro_esgoto.hidr_nnanofabricacao as anoFabricacaoHidrometroEsgoto, "

					+ "hidrometro_capacidade_esgoto.hicp_dshidrometrocapacidade as descricaohidrometrocapacidadee, "

					+ "hidrometro_marca_esgoto.himc_dshidrometromarca as descricaoHidrometroMarcaEsgoto, "

					+ "hidrometro_diametro_esgoto.hidm_dshidrometrodiametro as descricaohidrometrodiametroesg, "

					+ "hidrometro_tipo_esgoto.hitp_dshidrometrotipo as descricaoHidrometroTipoEsgoto, "

					+ "hidrometro_instalacao_historic.hidi_dtinstalacaohidrometro as datahidrometroinstalacaohistor, "

					+ "hidrometro_local_inst_es.hili_dshidmtlocalinstalacao as descricaohidtrometrolocalinsta, "

					+ "hidrometro_protecao_esgoto.hipr_dshidrometroprotecao as descricaohidrometroprotecaoesg, "

					+ "hidrometro_instalacao_historic.hidi_iccavalete as indicadorhidrometroinstalacaoe, "

					

					// Ligacao Agua

					+ "ligacao_agua.lagu_nnconsumominimoagua, "

					

					// Ligacao Esgoto

					+ "ligacao_esgoto.lesg_nnconsumominimoesgoto, "

					

					// Jardim

					+ "imovel.imov_icjardim, "

					

					// Rota

					+ "rota.rota_cdrota as codigoRota, "

					

					// Sequencial Rota

					+ "imovel.imov_nnsequencialrota as sequencialRota, "

					

					// Tipo Faturamento

					+ "imovel.fttp_id, "

					

					// Logradouro id

					+ "logradouro_cep.logr_id, "

					

					// DDD

					+ "cliente_fone.cfon_cdddd, "

					

					// Fone

					+ "cliente_fone.cfon_nnfone, "

					
					
					// Testada Lote

					+ "imovel.imov_nntestadalote "

					
					// Id da Rota
//					+ "rota.rota_id as idRota "
					

					// From

					+ "from cadastro.imovel_subcategoria imovelSubcategoria "

					

					+ "inner join cadastro.imovel on imovelSubcategoria.imov_id = cadastro.imovel.imov_id "

					+ "inner join cadastro.localidade on imovel.loca_id = cadastro.localidade.loca_id "

					+ "inner join cadastro.gerencia_regional on cadastro.localidade.greg_id = cadastro.gerencia_regional.greg_id "

					+ "inner join cadastro.setor_comercial on cadastro.imovel.stcm_id = cadastro.setor_comercial.stcm_id "

					

					// Logradouro Bairro

					+ "left join cadastro.logradouro_bairro on cadastro.imovel.lgbr_id = cadastro.logradouro_bairro.lgbr_id "

					+ "left join cadastro.bairro on cadastro.logradouro_bairro.bair_id = cadastro.bairro.bair_id "

					+ "left join cadastro.municipio on cadastro.bairro.muni_id = cadastro.municipio.muni_id "

					+ "inner join cadastro.quadra on cadastro.imovel.qdra_id = cadastro.quadra.qdra_id "

					+ "inner join micromedicao.rota on cadastro.quadra.rota_id = micromedicao.rota.rota_id "

					

					// Logradouro Cep

					+ "left join cadastro.logradouro_cep on cadastro.imovel.lgcp_id = cadastro.logradouro_cep.lgcp_id "

					+ "left join cadastro.cep on cadastro.logradouro_cep.cep_id = cadastro.cep.cep_id "

					+ "left join cadastro.logradouro on cadastro.logradouro_cep.logr_id = cadastro.logradouro.logr_id "

					

					// AGUA

					+ "left join atendimentopublico.ligacao_agua_situacao on cadastro.imovel.last_id = atendimentopublico.ligacao_agua_situacao.last_id "

					+ "left join atendimentopublico.ligacao_agua on cadastro.imovel.imov_id = atendimentopublico.ligacao_agua.lagu_id "

					

					// ESGOTO

					+ "left join atendimentopublico.ligacao_esgoto_situacao on cadastro.imovel.lest_id = atendimentopublico.ligacao_esgoto_situacao.lest_id "

					+ "left join atendimentopublico.ligacao_esgoto on cadastro.imovel.imov_id = atendimentopublico.ligacao_esgoto.lesg_id "

					

					+ "left join cadastro.imovel_perfil on cadastro.imovel.iper_id = cadastro.imovel_perfil.iper_id "

					+ "left join cadastro.poco_tipo on cadastro.imovel.poco_id = cadastro.poco_tipo.poco_id "

					+ "left join cadastro.area_construida_faixa on cadastro.imovel.acon_id = area_construida_faixa.acon_id "

					

					// Cliente Usuario

					+ "left outer join cadastro.cliente_imovel as cliente_imovel_usuario on cadastro.imovel.imov_id = cliente_imovel_usuario.imov_id  "

					+ " and (cliente_imovel_usuario.crtp_id=2 and cliente_imovel_usuario.clim_dtrelacaofim is null) "

					+ "left outer join cadastro.cliente as cliente_usuario on cliente_imovel_usuario.clie_id = cliente_usuario.clie_id "

					+ "left outer join cadastro.cliente_fone as cliente_fone on cliente_usuario.clie_id = cliente_fone.clie_id and (cliente_fone.cfon_icfonepadrao = 1) "

					

					// Cliente Resposanvel

					+ "left outer join cadastro.cliente_imovel as cliente_imovel_responsavel on cadastro.imovel.imov_id = cliente_imovel_responsavel.imov_id "

					+ " and (cliente_imovel_responsavel.crtp_id=3 and cliente_imovel_responsavel.clim_dtrelacaofim is null) "

					+ "left outer join cadastro.cliente as cliente_responsavel on cliente_imovel_responsavel.clie_id = cliente_responsavel.clie_id "

					

					// AGUA

					+ "left join micromedicao.hidrometro_inst_hist on atendimentopublico.ligacao_agua.hidi_id =  micromedicao.hidrometro_inst_hist.hidi_id "

					

					// ESGOTO

					+ "left join micromedicao.hidrometro_inst_hist hidrometro_instalacao_historic on cadastro.imovel.hidi_id =  hidrometro_instalacao_historic.hidi_id "

					

					// Relacionamento para o Relatorio de Imovel

					+ "left join cadastro.logradouro_titulo on cadastro.logradouro.lgtt_id = cadastro.logradouro_titulo.lgtt_id "

					+ "left join cadastro.logradouro_tipo on cadastro.logradouro.lgtp_id = cadastro.logradouro_tipo.lgtp_id "

					+ "left join cadastro.endereco_referencia on cadastro.imovel.edrf_id = cadastro.endereco_referencia.edrf_id "

					+ "left join cadastro.unidade_federacao on cadastro.municipio.unfe_id = cadastro.unidade_federacao.unfe_id "

					

					+ "left join cadastro.reservatorio_volume_fx on cadastro.imovel.resv_idreservatoriosuperior = cadastro.reservatorio_volume_fx.resv_id "

					+ "left join cadastro.reservatorio_volume_fx reservatorio_volume_fx_infe on cadastro.imovel.resv_idreservatorioinferior = reservatorio_volume_fx_infe.resv_id "

					

					+ "left join cadastro.piscina_volume_faixa on cadastro.imovel.pisc_id = cadastro.piscina_volume_faixa.pisc_id "

					+ "left join cadastro.pavimento_calcada on cadastro.imovel.pcal_id = cadastro.pavimento_calcada.pcal_id "

					+ "left join cadastro.pavimento_rua on cadastro.imovel.prua_id = cadastro.pavimento_rua.prua_id "

					+ "left join cadastro.despejo on cadastro.imovel.depj_id = cadastro.despejo.depj_id "

					

					// AGUA

					+ "left join atendimentopublico.ligacao_agua_diametro on atendimentopublico.ligacao_agua.lagd_id = atendimentopublico.ligacao_agua_diametro.lagd_id "

					+ "left join atendimentopublico.ligacao_agua_material on atendimentopublico.ligacao_agua.lagm_id = atendimentopublico.ligacao_agua_material.lagm_id "

					

					// ESGOTO

					+ "left join atendimentopublico.ligacao_esgoto_diametro on atendimentopublico.ligacao_esgoto.legd_id = atendimentopublico.ligacao_esgoto_diametro.legd_id "

					+ "left join atendimentopublico.ligacao_esgoto_material on atendimentopublico.ligacao_esgoto.legm_id = atendimentopublico.ligacao_esgoto_material.legm_id "

					

					// AGUA

					+ "left join micromedicao.hidrometro on micromedicao.hidrometro_inst_hist.hidr_id = micromedicao.hidrometro.hidr_id "

					+ "left join micromedicao.hidrometro_capacidade on micromedicao.hidrometro.hicp_id = micromedicao.hidrometro_capacidade.hicp_id "

					+ "left join micromedicao.hidrometro_marca on micromedicao.hidrometro.himc_id = micromedicao.hidrometro_marca.himc_id "

					+ "left join micromedicao.hidrometro_diametro on micromedicao.hidrometro.hidm_id = micromedicao.hidrometro_diametro.hidm_id "

					+ "left join micromedicao.hidrometro_tipo on micromedicao.hidrometro.hitp_id = micromedicao.hidrometro_tipo.hitp_id "

					+ "left join micromedicao.hidrometro_local_inst on micromedicao.hidrometro_inst_hist.hili_id = micromedicao.hidrometro_local_inst.hili_id "

					+ "left join micromedicao.hidrometro_protecao on micromedicao.hidrometro_inst_hist.hipr_id = micromedicao.hidrometro_protecao.hipr_id "

					

					// ESGOTO

					+ "left join micromedicao.hidrometro as hidrometro_esgoto on hidrometro_instalacao_historic.hidr_id = hidrometro_esgoto.hidr_id "

					+ "left join micromedicao.hidrometro_capacidade as hidrometro_capacidade_esgoto on hidrometro_esgoto.hicp_id = hidrometro_capacidade_esgoto.hicp_id "

					+ "left join micromedicao.hidrometro_marca as hidrometro_marca_esgoto on hidrometro_esgoto.himc_id = hidrometro_marca_esgoto.himc_id "

					+ "left join micromedicao.hidrometro_diametro as hidrometro_diametro_esgoto on hidrometro_esgoto.hidm_id = hidrometro_diametro_esgoto.hidm_id "

					+ "left join micromedicao.hidrometro_tipo as hidrometro_tipo_esgoto on hidrometro_esgoto.hitp_id = hidrometro_tipo_esgoto.hitp_id "

					+ "left join micromedicao.hidrometro_local_inst hidrometro_local_inst_es on hidrometro_instalacao_historic.hili_id = hidrometro_local_inst.hili_id "
					
					/**
					 * @autor Adriana Muniz
					 * @date 06/08/2013
					 * 
					 * Correção da nomenclatura do hidrometro_instalacao_historic (anterior hidrometro_instalacao_historico_esgoto) 
					 */
					+ "left join micromedicao.hidrometro_protecao as hidrometro_protecao_esgoto on hidrometro_instalacao_historic.hipr_id = hidrometro_protecao_esgoto.hipr_id ";

			consulta = consulta

			+ montarInnerJoinFiltrarImoveisOutrosCriterios(

			intervaloMediaMinimaImovelInicial,

			intervaloMediaMinimaImovelFinal,

			intervaloMediaMinimaHidrometroInicial,

			intervaloMediaMinimaHidrometroFinal,

			idFaturamentoSituacaoTipo, idCobrancaSituacaoTipo,

			idSituacaoEspecialCobranca, idEloAnormalidade,

			idCadastroOcorrencia, idConsumoTarifa,

			idTipoMedicao, indicadorMedicao, idSubCategoria,

			idCategoria, idCliente, idClienteTipo,

			idClienteRelacaoTipo,

			ConstantesSistema.GERAR_RELATORIO_IMOVEL, 
			null);

			consulta = consulta

			+ montarCondicaoSQLWhereFiltrarImovelOutrosCriterio(

			idImovelCondominio, idImovelPrincipal,

			idSituacaoLigacaoAgua, consumoMinimoInicialAgua,

			consumoMinimoFinalAgua, idSituacaoLigacaoEsgoto,

			consumoMinimoInicialEsgoto,

			consumoMinimoFinalEsgoto,

			intervaloValorPercentualEsgotoInicial,

			intervaloValorPercentualEsgotoFinal,

			intervaloMediaMinimaImovelInicial,

			intervaloMediaMinimaImovelFinal,

			intervaloMediaMinimaHidrometroInicial,

			intervaloMediaMinimaHidrometroFinal,

			idImovelPerfil, idPocoTipo,

			idFaturamentoSituacaoTipo, idCobrancaSituacaoTipo,

			idSituacaoEspecialCobranca, idEloAnormalidade,

			areaConstruidaInicial, areaConstruidaFinal,

			idCadastroOcorrencia, idConsumoTarifa,

			idGerenciaRegional, idLocalidadeInicial,

			idLocalidadeFinal, setorComercialInicial,

			setorComercialFinal, quadraInicial, quadraFinal,

			loteOrigem, loteDestno, cep, logradouro, bairro,

			municipio, idTipoMedicao, indicadorMedicao,

			idSubCategoria, idCategoria,

			quantidadeEconomiasInicial,

			quantidadeEconomiasFinal, diaVencimento, idCliente,

			idClienteTipo, idClienteRelacaoTipo,

			numeroPontosInicial, numeroPontosFinal,

			numeroMoradoresInicial, numeroMoradoresFinal,

			idAreaConstruidaFaixa, idUnidadeNegocio,

			ConstantesSistema.GERAR_RELATORIO_IMOVEL, cdRotaInicial,
			cdRotaFinal, sequencialRotaInicial,
			sequencialRotaFinal, pocoTipoListIds, null , null);

			/*
			 * # COLOCANDO O VALOR NAS CONDIÇÕES#
			 * 
			 */

			/*
			 * 
			 * consulta = consulta + " consumosHistorico.referenciaFaturamento = " +
			 * 
			 * "(select max(consumoHistorico.referenciaFaturamento) from
			 * 
			 * ConsumoHistorico consumoHistorico " + " left join
			 * 
			 * consumoHistorico.imovel imovelConsumoHistorico " + "where
			 * 
			 * imovelConsumoHistorico.id = imovel.id) and ";
			 * 
			 */

			SQLQuery query = null;
			
			consulta = consulta.substring(0, (consulta.length() - 5));
			
			if (ordenacao != null && !ordenacao.trim().equals("") && !ordenacao.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				if (ordenacao.trim().equals("rota")) {
					query = session.createSQLQuery(consulta.concat(" order by 1, 3, 7,codigoRota, imovel.imov_nnsequencialrota"));
//					query = session.createSQLQuery(consulta.concat(" order by idRota, sequencialRota"));
				}
			} else {
				query = session.createSQLQuery(consulta.concat(" order by 1, 3, 7, 83, 9, 10, 11"));
			}

			informarDadosQueryFiltrarImovelOutrosCriterio(query,

			idImovelCondominio, idImovelPrincipal,

			idSituacaoLigacaoAgua, consumoMinimoInicialAgua,

			consumoMinimoFinalAgua, idSituacaoLigacaoEsgoto,

			consumoMinimoInicialEsgoto, consumoMinimoFinalEsgoto,

			intervaloValorPercentualEsgotoInicial,

			intervaloValorPercentualEsgotoFinal,

			intervaloMediaMinimaImovelInicial,

			intervaloMediaMinimaImovelFinal,

			intervaloMediaMinimaHidrometroInicial,

			intervaloMediaMinimaHidrometroFinal, idImovelPerfil,

			idPocoTipo, idFaturamentoSituacaoTipo,

			idCobrancaSituacaoTipo, idSituacaoEspecialCobranca,

			idEloAnormalidade, areaConstruidaInicial,

			areaConstruidaFinal, idCadastroOcorrencia, idConsumoTarifa,

			idGerenciaRegional, idLocalidadeInicial, idLocalidadeFinal,

			setorComercialInicial, setorComercialFinal, quadraInicial,

			quadraFinal, loteOrigem, loteDestno, cep, logradouro,

			bairro, municipio, idTipoMedicao, indicadorMedicao,

			idSubCategoria, idCategoria, quantidadeEconomiasInicial,

			quantidadeEconomiasFinal, diaVencimento, idCliente,

			idClienteTipo, idClienteRelacaoTipo, numeroPontosInicial,

			numeroPontosFinal, numeroMoradoresInicial,

			numeroMoradoresFinal, idAreaConstruidaFaixa,

			idUnidadeNegocio, cdRotaInicial, cdRotaFinal, sequencialRotaInicial, 
			sequencialRotaFinal, pocoTipoListIds, null);

			retorno = query

					// .addScalar("imov_id",Hibernate.INTEGER)

					.addScalar("greg_id", Hibernate.INTEGER)

					.addScalar("greg_nmabreviado", Hibernate.STRING)

					.addScalar("loca_id", Hibernate.INTEGER)

					.addScalar("loca_nmlocalidade", Hibernate.STRING)

					.addScalar("imov_id", Hibernate.INTEGER)

					.addScalar("imov_qteconomia", Hibernate.SHORT)

					.addScalar("stcm_cdsetorcomercial", Hibernate.INTEGER)

					.addScalar("stcm_nmsetorcomercial", Hibernate.STRING)

					.addScalar("qdra_nnquadra", Hibernate.INTEGER)

					.addScalar("imov_nnlote", Hibernate.SHORT)

					.addScalar("imov_nnsublote", Hibernate.SHORT)

					.addScalar("last_id", Hibernate.INTEGER)

					.addScalar("lest_id", Hibernate.INTEGER)

					.addScalar("lesg_pcesgoto", Hibernate.BIG_DECIMAL)

					.addScalar("lesg_dtligacao", Hibernate.DATE)

					.addScalar("lagu_dtligacaoagua", Hibernate.DATE)

					.addScalar("idClienteUsuario", Hibernate.INTEGER)

					.addScalar("nomeClienteUsuario", Hibernate.STRING)

					.addScalar("idClienteResponsavel", Hibernate.INTEGER)

					.addScalar("nomeClienteResponsavel", Hibernate.STRING)

					.addScalar("logr_nmlogradouro", Hibernate.STRING)

					.addScalar("lgtp_dslogradourotipo", Hibernate.STRING)

					.addScalar("lgtt_dslogradourotitulo", Hibernate.STRING)

					.addScalar("cep_cdcep", Hibernate.INTEGER)

					.addScalar("edrf_dsenderecoreferencia", Hibernate.STRING)

					.addScalar("imov_dscomplementoendereco", Hibernate.STRING)

					.addScalar("imov_nnimovel", Hibernate.STRING)

					.addScalar("bair_nmbairro", Hibernate.STRING)

					.addScalar("muni_nmmunicipio", Hibernate.STRING)

					.addScalar("unfe_dsufsigla", Hibernate.STRING)

					.addScalar("imov_icimovelcondominio", Hibernate.INTEGER)

					.addScalar("imov_nnmorador", Hibernate.INTEGER)

					.addScalar("imov_idimovelcondominio", Hibernate.INTEGER)

					.addScalar("imov_idimovelprincipal", Hibernate.INTEGER)

					.addScalar("imov_nnpontosutilizacao", Hibernate.INTEGER)

					.addScalar("iper_dsimovelperfil", Hibernate.STRING)

					.addScalar("acon_nnmaiorfaixa", Hibernate.INTEGER)

					.addScalar("acon_nnmenorfaixa", Hibernate.INTEGER)

					.addScalar("imov_nnareaconstruida", Hibernate.BIG_DECIMAL)

					.addScalar("pcal_id", Hibernate.INTEGER)

					.addScalar("prua_id", Hibernate.INTEGER)

					.addScalar("depj_id", Hibernate.INTEGER)

					.addScalar("resv_vomenorfaixa", Hibernate.BIG_DECIMAL)

					.addScalar("resv_vomaiorfaixa", Hibernate.BIG_DECIMAL)

					.addScalar("resv_idreservatoriosuperior", Hibernate.INTEGER)

					.addScalar("resv_vomenorfaixa", Hibernate.BIG_DECIMAL)

					.addScalar("resv_vomaiorfaixa", Hibernate.BIG_DECIMAL)

					.addScalar("resv_idreservatorioinferior", Hibernate.INTEGER)

					.addScalar("pisc_vomenorfaixa", Hibernate.BIG_DECIMAL)

					.addScalar("pisc_vomaiorfaixa", Hibernate.BIG_DECIMAL)

					.addScalar("pisc_id", Hibernate.INTEGER).addScalar(

					"poco_dspocotipo", Hibernate.STRING).addScalar(

					"descricaoligacaoaguadiametroag",

					Hibernate.INTEGER).addScalar(

					"descricaoligacaoaguamaterialag",

					Hibernate.STRING).addScalar(

					"descricaoligacaoesgotodiametro",

					Hibernate.INTEGER).addScalar(

					"descricaoligacaoesgotomaterial",

					Hibernate.STRING).addScalar(

					"lesg_nnconsumominimoesgoto", Hibernate.INTEGER)

					.addScalar("lesg_pccoleta", Hibernate.BIG_DECIMAL)

					.addScalar("lesg_pcesgoto", Hibernate.BIG_DECIMAL)

					.addScalar("idHidrometroAgua", Hibernate.STRING).addScalar(

					"anoFabricancaoHidrometroAgua", Hibernate.INTEGER)

					.addScalar("descricaohidrometrocapacidadea",

					Hibernate.INTEGER).addScalar(

					"descricaoHidrometroMarcaAgua", Hibernate.INTEGER)

					.addScalar("descricaohidrometrodiametroagu",

					Hibernate.INTEGER).addScalar(

					"descricaoHidrometroTipoAgua", Hibernate.INTEGER)

					.addScalar("datahidrometroinstalacaohisto2",

					Hibernate.DATE).addScalar(

					"hidrometroLocalInstalacaoAgua", Hibernate.INTEGER)

					.addScalar("descricaohidrometroprotecaoagu",

					Hibernate.INTEGER).addScalar(

					"indicadorhidrometroinstalacaoh",

					Hibernate.INTEGER)

					.addScalar("idHidrometroEsgoto", Hibernate.STRING)

					.addScalar("anoFabricacaoHidrometroEsgoto",

					Hibernate.INTEGER).addScalar(

					"descricaohidrometrocapacidadee",

					Hibernate.STRING).addScalar(

					"descricaoHidrometroMarcaEsgoto", Hibernate.STRING)

					.addScalar("descricaohidrometrodiametroesg",

					Hibernate.STRING).addScalar(

					"descricaoHidrometroTipoEsgoto", Hibernate.STRING)

					.addScalar("datahidrometroinstalacaohistor",

					Hibernate.DATE).addScalar(

					"descricaohidtrometrolocalinsta",

					Hibernate.STRING).addScalar(

					"descricaohidrometroprotecaoesg",

					Hibernate.STRING).addScalar(

					"indicadorhidrometroinstalacaoe",

					Hibernate.INTEGER).addScalar(

					"lagu_nnconsumominimoagua", Hibernate.INTEGER)

					.addScalar("lesg_nnconsumominimoesgoto", Hibernate.INTEGER)

					.addScalar("imov_icjardim", Hibernate.SHORT).addScalar(

					"codigoRota", Hibernate.SHORT).addScalar(

					"sequencialRota", Hibernate.INTEGER)

					.addScalar("fttp_id", Hibernate.INTEGER).addScalar(

					"logr_id", Hibernate.INTEGER).addScalar(

					"cfon_cdddd", Hibernate.STRING).addScalar(

					"cfon_nnfone", Hibernate.STRING).addScalar("imov_nntestadalote", Hibernate.SHORT)
					
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
	 * Filtra o Pagamento Historico pelo seu id carregando os dados necessários
	 * 
	 * 
	 * 
	 * [UC0549] Consultar Dados do Pagamento
	 * 
	 * 
	 * 
	 * @author Kássia Albuquerque
	 * 
	 * @date 12/07/2007
	 * 
	 * 
	 * 
	 * @param idPagamentoHistorico
	 * 
	 * @return Collection<PagamentoHistorico>
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoPeloId(

	Integer idPagamentoHistorico) throws ErroRepositorioException {

		Collection<PagamentoHistorico> retorno = null;

		String hql = "";

		Session session = HibernateUtil.getSession();

		try {

			hql = " select distinct pagamentoHistorico "

					+ " from PagamentoHistorico pagamentoHistorico "

					+ " INNER JOIN FETCH pagamentoHistorico.localidade loc "

					+ " INNER JOIN FETCH pagamentoHistorico.documentoTipo doctoTp "

					+ " INNER JOIN FETCH pagamentoHistorico.pagamentoSituacaoAtual pagtoSitAtual "

					+ " LEFT JOIN FETCH pagamentoHistorico.imovel imov "

					+ " LEFT JOIN FETCH pagamentoHistorico.cliente cliente "

					+ " LEFT JOIN FETCH pagamentoHistorico.debitoTipo debTp "

					+ " LEFT JOIN FETCH pagamentoHistorico.pagamentoSituacaoAnterior pagtoSitAnterior "

					+ " LEFT JOIN FETCH pagamentoHistorico.avisoBancario avbc "

					+ " LEFT JOIN FETCH avbc.arrecadador arrec "

					+ " LEFT JOIN FETCH arrec.cliente clienteArrec "

					+ " LEFT JOIN FETCH pagamentoHistorico.arrecadadorMovimentoItem arrecMovItem "

					+ " LEFT JOIN FETCH arrecMovItem.registroCodigo regCod "

					+ " LEFT JOIN FETCH arrecMovItem.arrecadadorMovimento arrecMov "

					+ " WHERE pagamentoHistorico.id = :idPagamentoHistorico ";

			retorno = session.createQuery(hql).setInteger(

			"idPagamentoHistorico", idPagamentoHistorico).list();

		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * 
	 * Filtra o Pagamento pelo seu id carregando os dados necessários
	 * 
	 * 
	 * 
	 * [UC0549] Consultar Dados do Pagamento
	 * 
	 * 
	 * 
	 * @author Kássia Albuquerque
	 * 
	 * @date 12/07/2007
	 * 
	 * 
	 * 
	 * @param idPagamentoHistorico
	 * 
	 * @return Collection<PagamentoHistorico>
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Collection<Pagamento> pesquisarPagamentoPeloId(Integer idPagamento)

	throws ErroRepositorioException {

		Collection<Pagamento> retorno = null;

		String hql = "";

		Session session = HibernateUtil.getSession();

		try {

			hql = " select distinct pagamento "

					+ " from Pagamento pagamento "

					+ " INNER JOIN FETCH pagamento.localidade loc "

					+ " INNER JOIN FETCH pagamento.documentoTipo doctoTp "

					+ " INNER JOIN FETCH pagamento.avisoBancario avbc "

					+ " INNER JOIN FETCH avbc.arrecadador arrec "

					+ " INNER JOIN FETCH arrec.cliente clienteArrec "

					+ " LEFT JOIN FETCH pagamento.imovel imov "

					+ " LEFT JOIN FETCH pagamento.cliente cliente "

					+ " LEFT JOIN FETCH pagamento.debitoTipo debTp "

					+ " LEFT JOIN FETCH pagamento.pagamentoSituacaoAnterior pagtoSitAnterior "

					+ " LEFT JOIN FETCH pagamento.pagamentoSituacaoAtual pagtoSitAtual "

					+ " LEFT JOIN FETCH pagamento.arrecadadorMovimentoItem arrecMovItem "

					+ " LEFT JOIN FETCH arrecMovItem.registroCodigo regCod "

					+ " LEFT JOIN FETCH arrecMovItem.arrecadadorMovimento arrecMov "
					
					+ " LEFT JOIN FETCH pagamento.guiaPagamento guiaPgmt "

					+ " WHERE pagamento.id = :idPagamento ";

			retorno = session.createQuery(hql).setInteger("idPagamento",

			idPagamento).list();

		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * 
	 * Obtém a o nome do cliente
	 * 
	 * 
	 * 
	 * @author Kassia Albuquerque
	 * 
	 * @date 05/07/2007
	 * 
	 * 
	 * 
	 * @return Object[]
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Object[] obterDescricaoIdCliente(Integer idImovel)

	throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String consulta = "select c.clie_id as id ,c.clie_nmcliente as nome "

					+ "from cadastro.cliente_imovel cimovel "

					+ "inner join cadastro.cliente c on cimovel.clie_id = c.clie_id "

					+ "where cimovel.imov_id ="

					+ idImovel

					+ " and cimovel.crtp_id= 2 and cimovel.clim_dtrelacaofim is null ";

			Collection colecaoConsulta = session.createSQLQuery(consulta)

			.addScalar("id", Hibernate.INTEGER).addScalar("nome",

			Hibernate.STRING).list();

			retorno = (Object[]) Util.retonarObjetoDeColecao(colecaoConsulta);

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * 
	 * Obtém o nome do Arrecadador
	 * 
	 * 
	 * 
	 * @author Kassia Albuquerque
	 * 
	 * @date 09/07/2007
	 * 
	 * 
	 * 
	 * @return String
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public String pesquisarNomeAgenteArrecadador(Integer idPagamentoHistorico)

	throws ErroRepositorioException {

		String retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String consulta = "select c.clie_nmcliente as nome "

					+ "from cadastro.cliente c,arrecadacao.arrecadador a,arrecadacao.pagamento_historico p "

					+ "where a.clie_id = c.clie_id and a.arrc_cdagente = p.pghi_cdagente "

					+ "and p.pghi_id =" + idPagamentoHistorico;

			Collection colecaoConsulta = session.createSQLQuery(consulta)

			.addScalar("nome", Hibernate.STRING).list();

			retorno = (String) Util.retonarObjetoDeColecao(colecaoConsulta);

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * 
	 * Obtém a o 117º caracter de uma String
	 * 
	 * 
	 * 
	 * @author Kassia Albuquerque
	 * 
	 * @date 20/07/2007
	 * 
	 * 
	 * 
	 * @return String
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public String pesquisarCaracterRetorno(Integer idConteudoRegistro)

	throws ErroRepositorioException {

		String retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String consulta = "select substr(amit.amit_cnregistro, 117,1) as caracterRetorno"

					+ " from arrecadacao.arrecadador_mov_item amit"

					+ " where amit.amit_id =" + idConteudoRegistro;

			Collection colecaoConsulta = session.createSQLQuery(consulta)

			.addScalar("caracterRetorno", Hibernate.STRING).list();

			retorno = (String) Util.retonarObjetoDeColecao(colecaoConsulta);

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * 
	 * 
	 * 
	 * @author Sávio Luiz
	 * 
	 * @date 24/08/2007
	 * 
	 * 
	 * 
	 * @return String
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Integer pesquisarSequencialRota(Integer idImovel)

	throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String consulta = "select imov.numeroSequencialRota "

			+ " from Imovel imov "

			+ " where imov.id = :idImovel";

			retorno = (Integer) session.createQuery(consulta)

			.setInteger("idImovel", idImovel).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * [UC0582] Emitir Boletim de Cadastro
	 * 
	 * 
	 * @author Rafael Corrêa
	 * @data 16/05/2007
	 * 
	 * @return Collection<EmitirDocumentoCobrancaBoletimCadastroHelper>
	 */

	public Collection<EmitirDocumentoCobrancaBoletimCadastroHelper> pesquisarBoletimCadastro(
		String idImovelCondominio, String idImovelPrincipal,
		String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
		String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
		String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
		String intervaloValorPercentualEsgotoInicial,
		String intervaloValorPercentualEsgotoFinal,
		String intervaloMediaMinimaImovelInicial,
		String intervaloMediaMinimaImovelFinal,
		String intervaloMediaMinimaHidrometroInicial,
		String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,
		String idPocoTipo, String idFaturamentoSituacaoTipo,
		String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
		String idEloAnormalidade, String areaConstruidaInicial,
		String areaConstruidaFinal, String idCadastroOcorrencia,
		String idConsumoTarifa, String idGerenciaRegional,
		String idLocalidadeInicial, String idLocalidadeFinal,
		String setorComercialInicial, String setorComercialFinal,
		String quadraInicial, String quadraFinal, String loteOrigem,
		String loteDestno, String cep, String logradouro, String bairro,
		String municipio, String idTipoMedicao, String indicadorMedicao,
		String idSubCategoria, String idCategoria,
		String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
		String diaVencimento, String idCliente, String idClienteTipo,
		String idClienteRelacaoTipo, String numeroPontosInicial,
		String numeroPontosFinal, String numeroMoradoresInicial,
		String numeroMoradoresFinal, String idAreaConstruidaFaixa,
		String idUnidadeNegocio, int quantidadeCobrancaDocumentoInicio,
		String indicadorCpfCnpj, String cpfCnpj) 
		throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String consulta = "";

		Collection<EmitirDocumentoCobrancaBoletimCadastroHelper> emitirDocumentoCobrancaBoletimCadastroHelper = 
			new ArrayList();

		try {

			consulta = consulta
					+ "select distinct imovel.imov_id, localidade.loca_id, "// 0,1
					+ "setor_comercial.stcm_cdsetorcomercial, "// 2
					+ "quadra.qdra_nnquadra, imovel.imov_nnlote, " // 3,4
					+ "imovel.imov_nnsublote, rota.cbgr_id, "// 5,6
					+ "ligacao_agua_situacao.last_id, ligacao_esgoto_situacao.lest_id, "// 7,8
					+ "imovel.imov_nnmorador, imovel.imov_nnareaconstruida, logradouro.logr_id , "// 9,10,11
					+ "cep.cep_cdcep, bairro.bair_cdbairro, imovel.edrf_id, imovel.imov_nnimovel, "// 12,13,14,15
					+ "imovel.imov_dscomplementoendereco, imovel.resv_idreservatorioinferior, "// 16,17
					+ "imovel.resv_idreservatoriosuperior, imovel.pisc_id, "// 18,19
					+ "imovel.imov_icjardim, imovel.prua_id,imovel.pcal_id, "// 20,21,22
					+ "imovel.imov_nnpontosutilizacao, imovel.iper_id, imovel.depj_id, "// 23,24,25
					+ "imovel.poco_id, imovel.ftab_id, imovel.imov_nniptu, imovel.imov_nncontratoenergia, "// 26,27,28,29
					+ "rota.rota_cdrota,imovel.imov_nnsequencialrota " //30,31
					+

					// From
					"from cadastro.imovel_subcategoria imovelSubcategoria "
					+ "inner join cadastro.imovel on imovelSubcategoria.imov_id = cadastro.imovel.imov_id "
					+ "inner join cadastro.localidade on imovel.loca_id = cadastro.localidade.loca_id "
					+ "inner join cadastro.gerencia_regional on cadastro.localidade.greg_id = cadastro.gerencia_regional.greg_id "
					+ "inner join cadastro.setor_comercial on cadastro.imovel.stcm_id = cadastro.setor_comercial.stcm_id "
					+

					// Logradouro Bairro
					"left join cadastro.logradouro_bairro on cadastro.imovel.lgbr_id = cadastro.logradouro_bairro.lgbr_id "
					+ "left join cadastro.bairro on cadastro.logradouro_bairro.bair_id = cadastro.bairro.bair_id "
					+ "left join cadastro.municipio on cadastro.bairro.muni_id = cadastro.municipio.muni_id "
					+ "inner join cadastro.quadra on cadastro.imovel.qdra_id = cadastro.quadra.qdra_id "
					+ "inner join micromedicao.rota on cadastro.quadra.rota_id = micromedicao.rota.rota_id "
					+

					// Logradouro Cep
					"left join cadastro.logradouro_cep on cadastro.imovel.lgcp_id = cadastro.logradouro_cep.lgcp_id "
					+ "left join cadastro.cep on cadastro.logradouro_cep.cep_id = cadastro.cep.cep_id "
					+ "left join cadastro.logradouro on cadastro.logradouro_cep.logr_id = cadastro.logradouro.logr_id "
					+

					// AGUA
					"left join atendimentopublico.ligacao_agua_situacao on cadastro.imovel.last_id = atendimentopublico.ligacao_agua_situacao.last_id "
					+ "left join atendimentopublico.ligacao_agua on cadastro.imovel.imov_id = atendimentopublico.ligacao_agua.lagu_id "
					+

					// ESGOTO
					"left join atendimentopublico.ligacao_esgoto_situacao on cadastro.imovel.lest_id = atendimentopublico.ligacao_esgoto_situacao.lest_id "
					+ "left join atendimentopublico.ligacao_esgoto on cadastro.imovel.imov_id = atendimentopublico.ligacao_esgoto.lesg_id "
					+ "left join cadastro.imovel_perfil on cadastro.imovel.iper_id = cadastro.imovel_perfil.iper_id "
					+ "left join cadastro.poco_tipo on cadastro.imovel.poco_id = cadastro.poco_tipo.poco_id "
					+ "left join cadastro.area_construida_faixa on cadastro.imovel.acon_id = area_construida_faixa.acon_id "
					+

					// Cliente Usuario
					"left outer join cadastro.cliente_imovel as cliente_imovel_usuario on cadastro.imovel.imov_id = cliente_imovel_usuario.imov_id  "
					+ " and (cliente_imovel_usuario.crtp_id=2 and cliente_imovel_usuario.clim_dtrelacaofim is null) "
					+ "left outer join cadastro.cliente as cliente_usuario on cliente_imovel_usuario.clie_id = cliente_usuario.clie_id "
					+

					// Cliente Resposanvel
					"left outer join cadastro.cliente_imovel as cliente_imovel_responsavel on cadastro.imovel.imov_id = cliente_imovel_responsavel.imov_id "
					+ " and (cliente_imovel_responsavel.crtp_id=3 and cliente_imovel_responsavel.clim_dtrelacaofim is null) "
					+ "left outer join cadastro.cliente as cliente_responsavel on cliente_imovel_responsavel.clie_id = cliente_responsavel.clie_id "
					+

					// AGUA
					"left join micromedicao.hidrometro_inst_hist on atendimentopublico.ligacao_agua.hidi_id =  micromedicao.hidrometro_inst_hist.hidi_id "
					+

					// ESGOTO
					"left join micromedicao.hidrometro_inst_hist hidrometro_instalacao_historic on cadastro.imovel.hidi_id =  hidrometro_instalacao_historic.hidi_id "
					+

					// Relacionamento para o Relatorio de Imovel
					"left join cadastro.logradouro_titulo on cadastro.logradouro.lgtt_id = cadastro.logradouro_titulo.lgtt_id "
					+ "left join cadastro.logradouro_tipo on cadastro.logradouro.lgtp_id = cadastro.logradouro_tipo.lgtp_id "
					+ "left join cadastro.endereco_referencia on cadastro.imovel.edrf_id = cadastro.endereco_referencia.edrf_id "
					+ "left join cadastro.unidade_federacao on cadastro.municipio.unfe_id = cadastro.unidade_federacao.unfe_id "
					+ "left join cadastro.reservatorio_volume_fx on cadastro.imovel.resv_idreservatoriosuperior = cadastro.reservatorio_volume_fx.resv_id "
					+ "left join cadastro.reservatorio_volume_fx reservatorio_volume_fx_infe on cadastro.imovel.resv_idreservatorioinferior = reservatorio_volume_fx_infe.resv_id "
					+ "left join cadastro.piscina_volume_faixa on cadastro.imovel.pisc_id = cadastro.piscina_volume_faixa.pisc_id "
					+ "left join cadastro.pavimento_calcada on cadastro.imovel.pcal_id = cadastro.pavimento_calcada.pcal_id "
					+ "left join cadastro.pavimento_rua on cadastro.imovel.prua_id = cadastro.pavimento_rua.prua_id "
					+ "left join cadastro.despejo on cadastro.imovel.depj_id = cadastro.despejo.depj_id "
					+

					// AGUA
					"left join atendimentopublico.ligacao_agua_diametro on atendimentopublico.ligacao_agua.lagd_id = atendimentopublico.ligacao_agua_diametro.lagd_id "
					+ "left join atendimentopublico.ligacao_agua_material on atendimentopublico.ligacao_agua.lagm_id = atendimentopublico.ligacao_agua_material.lagm_id "
					+

					// ESGOTO
					"left join atendimentopublico.ligacao_esgoto_diametro on atendimentopublico.ligacao_esgoto.legd_id = atendimentopublico.ligacao_esgoto_diametro.legd_id "
					+ "left join atendimentopublico.ligacao_esgoto_material on atendimentopublico.ligacao_esgoto.legm_id = atendimentopublico.ligacao_esgoto_material.legm_id "
					+

					// AGUA
					"left join micromedicao.hidrometro on micromedicao.hidrometro_inst_hist.hidr_id = micromedicao.hidrometro.hidr_id "
					+ "left join micromedicao.hidrometro_capacidade on micromedicao.hidrometro.hicp_id = micromedicao.hidrometro_capacidade.hicp_id "
					+ "left join micromedicao.hidrometro_marca on micromedicao.hidrometro.himc_id = micromedicao.hidrometro_marca.himc_id "
					+ "left join micromedicao.hidrometro_diametro on micromedicao.hidrometro.hidm_id = micromedicao.hidrometro_diametro.hidm_id "
					+ "left join micromedicao.hidrometro_tipo on micromedicao.hidrometro.hitp_id = micromedicao.hidrometro_tipo.hitp_id "
					+ "left join micromedicao.hidrometro_local_inst on micromedicao.hidrometro_inst_hist.hili_id = micromedicao.hidrometro_local_inst.hili_id "
					+ "left join micromedicao.hidrometro_protecao on micromedicao.hidrometro_inst_hist.hipr_id = micromedicao.hidrometro_protecao.hipr_id "
					+

					// ESGOTO
					"left join micromedicao.hidrometro as hidrometro_esgoto on hidrometro_instalacao_historic.hidr_id = hidrometro_esgoto.hidr_id "
					+ "left join micromedicao.hidrometro_capacidade as hidrometro_capacidade_esgoto on hidrometro_esgoto.hicp_id = hidrometro_capacidade_esgoto.hicp_id "
					+ "left join micromedicao.hidrometro_marca as hidrometro_marca_esgoto on hidrometro_esgoto.himc_id = hidrometro_marca_esgoto.himc_id "
					+ "left join micromedicao.hidrometro_diametro as hidrometro_diametro_esgoto on hidrometro_esgoto.hidm_id = hidrometro_diametro_esgoto.hidm_id "
					+ "left join micromedicao.hidrometro_tipo as hidrometro_tipo_esgoto on hidrometro_esgoto.hitp_id = hidrometro_tipo_esgoto.hitp_id "
					+ "left join micromedicao.hidrometro_local_inst as hidrometro_local_inst_es on hidrometro_instalacao_historic.hili_id = hidrometro_local_inst.hili_id "
					+ "left join micromedicao.hidrometro_protecao as hidrometro_protecao_esgoto on hidrometro_instalacao_historic.hipr_id = hidrometro_protecao_esgoto.hipr_id ";

			consulta = consulta + 
				montarInnerJoinFiltrarImoveisOutrosCriterios(
					intervaloMediaMinimaImovelInicial,
					intervaloMediaMinimaImovelFinal,
					intervaloMediaMinimaHidrometroInicial,
					intervaloMediaMinimaHidrometroFinal,
					idFaturamentoSituacaoTipo, 
					idCobrancaSituacaoTipo,
					idSituacaoEspecialCobranca, 
					idEloAnormalidade,
					idCadastroOcorrencia, 
					idConsumoTarifa,
					idTipoMedicao, 
					indicadorMedicao, 
					idSubCategoria,
					idCategoria, 
					idCliente, 
					idClienteTipo,
					idClienteRelacaoTipo,
					ConstantesSistema.GERAR_RELATORIO_IMOVEL, 
					cpfCnpj);

			consulta = consulta + 
				montarCondicaoSQLWhereFiltrarImovelOutrosCriterio(
					idImovelCondominio, idImovelPrincipal,
					idSituacaoLigacaoAgua, consumoMinimoInicialAgua,
					consumoMinimoFinalAgua, idSituacaoLigacaoEsgoto,
					consumoMinimoInicialEsgoto,
					consumoMinimoFinalEsgoto,
					intervaloValorPercentualEsgotoInicial,
					intervaloValorPercentualEsgotoFinal,
					intervaloMediaMinimaImovelInicial,
					intervaloMediaMinimaImovelFinal,
					intervaloMediaMinimaHidrometroInicial,
					intervaloMediaMinimaHidrometroFinal,
					idImovelPerfil, idPocoTipo,
					idFaturamentoSituacaoTipo, idCobrancaSituacaoTipo,
					idSituacaoEspecialCobranca, idEloAnormalidade,
					areaConstruidaInicial, areaConstruidaFinal,
					idCadastroOcorrencia, idConsumoTarifa,
					idGerenciaRegional, idLocalidadeInicial,
					idLocalidadeFinal, setorComercialInicial,
					setorComercialFinal, quadraInicial, quadraFinal,
					loteOrigem, loteDestno, cep, logradouro, bairro,
					municipio, idTipoMedicao, indicadorMedicao,
					idSubCategoria, idCategoria,
					quantidadeEconomiasInicial,
					quantidadeEconomiasFinal, diaVencimento, idCliente,
					idClienteTipo, idClienteRelacaoTipo,
					numeroPontosInicial, numeroPontosFinal,
					numeroMoradoresInicial, numeroMoradoresFinal,
					idAreaConstruidaFaixa, idUnidadeNegocio,
					ConstantesSistema.GERAR_RELATORIO_IMOVEL, null, null, null, null, null, indicadorCpfCnpj, cpfCnpj);

			consulta = consulta.substring(0,(consulta.length() - 5));

			consulta = consulta + " ORDER BY localidade.loca_id, "
				+ "setor_comercial.stcm_cdsetorcomercial, "
				+ "quadra.qdra_nnquadra, imovel.imov_nnlote, "
				+ "imovel.imov_nnsublote";

			SQLQuery query = session.createSQLQuery(consulta);
			
			informarDadosQueryFiltrarImovelOutrosCriterio(
				query,
				idImovelCondominio, idImovelPrincipal,
				idSituacaoLigacaoAgua, consumoMinimoInicialAgua,
				consumoMinimoFinalAgua, idSituacaoLigacaoEsgoto,
				consumoMinimoInicialEsgoto, consumoMinimoFinalEsgoto,
				intervaloValorPercentualEsgotoInicial,
				intervaloValorPercentualEsgotoFinal,
				intervaloMediaMinimaImovelInicial,
				intervaloMediaMinimaImovelFinal,
				intervaloMediaMinimaHidrometroInicial,
				intervaloMediaMinimaHidrometroFinal, idImovelPerfil,
				idPocoTipo, idFaturamentoSituacaoTipo,
				idCobrancaSituacaoTipo, idSituacaoEspecialCobranca,
				idEloAnormalidade, areaConstruidaInicial,
				areaConstruidaFinal, idCadastroOcorrencia, idConsumoTarifa,
				idGerenciaRegional, idLocalidadeInicial, idLocalidadeFinal,
				setorComercialInicial, setorComercialFinal, quadraInicial,
				quadraFinal, loteOrigem, loteDestno, cep, logradouro,
				bairro, municipio, idTipoMedicao, indicadorMedicao,
				idSubCategoria, idCategoria, quantidadeEconomiasInicial,
				quantidadeEconomiasFinal, diaVencimento, idCliente,
				idClienteTipo, idClienteRelacaoTipo, numeroPontosInicial,
				numeroPontosFinal, numeroMoradoresInicial,
				numeroMoradoresFinal, idAreaConstruidaFaixa,
				idUnidadeNegocio, null, null, null, null, null, cpfCnpj);
			Collection colecaoImovel = query
				.addScalar("imov_id", Hibernate.INTEGER)
				.addScalar("loca_id", Hibernate.INTEGER)
				.addScalar("stcm_cdsetorcomercial", Hibernate.INTEGER)
				.addScalar("qdra_nnquadra", Hibernate.INTEGER)
				.addScalar("imov_nnlote", Hibernate.SHORT)
				.addScalar("imov_nnsublote", Hibernate.SHORT)
				.addScalar("cbgr_id", Hibernate.INTEGER)
				.addScalar("last_id", Hibernate.INTEGER)
				.addScalar("lest_id", Hibernate.INTEGER)
				.addScalar("imov_nnmorador", Hibernate.SHORT)
				.addScalar("imov_nnareaconstruida", Hibernate.BIG_DECIMAL)
				.addScalar("logr_id", Hibernate.INTEGER)
				.addScalar("cep_cdcep", Hibernate.INTEGER)
				.addScalar("bair_cdbairro", Hibernate.INTEGER)
				.addScalar("edrf_id", Hibernate.INTEGER)
				.addScalar("imov_nnimovel", Hibernate.STRING)
				.addScalar("imov_dscomplementoendereco", Hibernate.STRING)
				.addScalar("resv_idreservatorioinferior", Hibernate.INTEGER)
				.addScalar("resv_idreservatoriosuperior", Hibernate.INTEGER)
				.addScalar("pisc_id", Hibernate.INTEGER)
				.addScalar("imov_icjardim", Hibernate.SHORT)
				.addScalar("prua_id", Hibernate.INTEGER)
				.addScalar("pcal_id", Hibernate.INTEGER)
				.addScalar("imov_nnpontosutilizacao", Hibernate.SHORT)
				.addScalar("iper_id", Hibernate.INTEGER)
				.addScalar("depj_id", Hibernate.INTEGER)
				.addScalar("poco_id", Hibernate.INTEGER)
				.addScalar("ftab_id", Hibernate.INTEGER)
				.addScalar("imov_nniptu", Hibernate.BIG_DECIMAL)
				.addScalar("imov_nncontratoenergia", Hibernate.LONG)
				.addScalar("rota_cdrota", Hibernate.SHORT)
				.addScalar("imov_nnsequencialrota", Hibernate.INTEGER)
				.setFirstResult(quantidadeCobrancaDocumentoInicio)
				.setMaxResults(500).list();
			
			EmitirDocumentoCobrancaBoletimCadastroHelper helper = null;

			if (!colecaoImovel.isEmpty()) {

				Iterator iteratorColecaoImovel = colecaoImovel.iterator();

				while (iteratorColecaoImovel.hasNext()) {

					Object[] imovel = (Object[]) iteratorColecaoImovel.next();

					helper = new EmitirDocumentoCobrancaBoletimCadastroHelper();

					if (imovel[0] != null) {
						helper.setIdImovel((Integer) imovel[0]);
					}

					if (imovel[1] != null) {
						helper.setIdLocalidade((Integer) imovel[1]);
					}

					if (imovel[2] != null) {
						helper.setCodigoSetorComercial((Integer) imovel[2]);
					}

					if (imovel[3] != null) {
						helper.setNumeroQuadra((Integer) imovel[3]);
					}

					if (imovel[4] != null) {
						helper.setLote((Short) imovel[4]);
					}

					if (imovel[5] != null) {
						helper.setSubLote((Short) imovel[5]);
					}

					if (imovel[6] != null) {
						helper.setIdCobrancaGrupo((Integer) imovel[6]);
					}

					if (imovel[7] != null) {
						helper.setIdLigacaoAguaSituacao((Integer) imovel[7]);
					}

					if (imovel[8] != null) {
						helper.setIdLigacaoEsgotoSituacao((Integer) imovel[8]);
					}

					if (imovel[9] != null) {
						helper.setNumeroMorador((Short) imovel[9]);
					}

					if (imovel[10] != null) {
						helper.setAreaConstruida((BigDecimal) imovel[10]);
					}

					if (imovel[11] != null) {
						helper.setIdLogradouro((Integer) imovel[11]);
					}

					if (imovel[12] != null) {
						helper.setCodigoCep((Integer) imovel[12]);
					}

					if (imovel[13] != null) {
						helper.setCodigoBairro((Integer) imovel[13]);
					}

					if (imovel[14] != null) {
						helper.setReferencia((Integer) imovel[14]);
					}

					if (imovel[15] != null) {
						helper.setNumeroImovel((String) imovel[15]);
					}

					if (imovel[16] != null) {
						helper.setComplemento((String) imovel[16]);
					}

					if (imovel[17] != null) {
						helper.setVolumeReservatorioInferior((Integer) imovel[17]);
					}

					if (imovel[18] != null) {
						helper.setVolumeReservatorioSuperior((Integer) imovel[18]);
					}

					if (imovel[19] != null) {
						helper.setVolumePiscina((Integer) imovel[19]);
					}

					if (imovel[20] != null) {
						helper.setJardim((Short) imovel[20]);
					}

					if (imovel[21] != null) {
						helper.setIdPavimentoRua((Integer) imovel[21]);
					}

					if (imovel[22] != null) {
						helper.setIdPavimentoCalcada((Integer) imovel[22]);
					}

					if (imovel[23] != null) {
						helper.setNumeroPontosUtilizacao((Short) imovel[23]);
					}

					if (imovel[24] != null) {
						helper.setIdImovelPerfil((Integer) imovel[24]);
					}

					if (imovel[25] != null) {
						helper.setIdDespejo((Integer) imovel[25]);
					}

					if (imovel[26] != null) {
						helper.setIdPoco((Integer) imovel[26]);
					}

					if (imovel[27] != null) {
						helper.setIdFonteAbastecimento((Integer) imovel[27]);
					}

					if (imovel[28] != null) {
						helper.setNumeroIptu((BigDecimal) imovel[28]);
					}

					if (imovel[29] != null) {
						helper.setNumeroCelpe((Long) imovel[29]);
					}
					if (imovel[30] != null) {
						helper.setCodigoRota((Short) imovel[30]);
					}
					
					if (imovel[31] != null) {
						helper.setNumeroSequencialRota((Integer) imovel[31]);
					}
					

					emitirDocumentoCobrancaBoletimCadastroHelper.add(helper);

				}

			}

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return emitirDocumentoCobrancaBoletimCadastroHelper;

	}

	
	/**
	 * @author Vivianne Sousa
	 * @date 19/09/2007
	 * 
	 * @return ImovelCobrancaSituacao
	 * @throws ErroRepositorioException
	 */

	public CobrancaSituacao pesquisarImovelCobrancaSituacao(Integer idImovel) throws ErroRepositorioException {

		CobrancaSituacao retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String consulta = "select iCS.cobrancaSituacao "
							+ " from ImovelCobrancaSituacao iCS "
							+ " inner join iCS.imovel imovel "
							+ " where imovel.id = :idImovel" 
							+ " and iCS.dataRetiradaCobranca is null ";

			retorno = (CobrancaSituacao) session.createQuery(consulta)
			.setInteger("idImovel", idImovel).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

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
	public Short pesquisarObterQuantidadeEconomias(Imovel imovel, Categoria categoria)
			throws ErroRepositorioException {

		Short retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select sum( imsc.quantidadeEconomias ) " + 
					   "from ImovelSubcategoria as imsc " + 
					   "where " + 
					   "  imsc.comp_id.imovel.id = :imovelId and " + 
					   "  imsc.comp_id.subcategoria.categoria.id = :categoriaId ";

			retorno = (Short)session.createQuery(consulta).
					setInteger("imovelId", imovel.getId().intValue()).
					setInteger("categoriaId", categoria.getId().intValue()).
					uniqueResult();

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
     * [UC0541] Emitir 2 Via de Conta Internet
     * 
     * @author Vivianne Sousa
     * @date 02/09/2007
     * 
     * @throws ErroRepositorioException
     */

    public Imovel pesquisarDadosImovel(Integer idImovel) throws ErroRepositorioException {

        Imovel imovel = null;
        String consulta = "";
        Session session = HibernateUtil.getSession();

        try {

            consulta = "SELECT imov "
                + " FROM Imovel imov "
                + " LEFT JOIN FETCH imov.localidade loc "
                + " LEFT JOIN FETCH imov.setorComercial setorCom "
                + " LEFT JOIN FETCH imov.quadra quad "
                + " INNER JOIN FETCH imov.ligacaoAguaSituacao ligAguaSit "
                + " INNER JOIN FETCH imov.ligacaoEsgotoSituacao ligEsgotoSit "
                + " WHERE " + " imov.id = :idImovel";

            imovel = (Imovel) session.createQuery(consulta).setInteger(
            "idImovel", idImovel).setMaxResults(1).uniqueResult();

            // erro no hibernate
        } catch (HibernateException e) {
            // levanta a exceção para a próxima camada
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            // fecha a sessão
            HibernateUtil.closeSession(session);
        }

        // retorna o imóvel
        return imovel;
    }
    
    /**
	 * O método abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necessários para a criação da inscrição e de rota para exibição.
	 * 
	 * @author Vivianne Sousa
     * @date 06/11/2007
	 */

	public Collection pesquisarInscricaoImoveleRota(String idsImovel)
	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Collection retorno = null;

		try {
			
			 consulta = "SELECT imov.id, "
				    + " loc.id, "
				    + " setorCom.codigo, "
				    + " quad.numeroQuadra, "
				    + " imov.lote, "
				    + " imov.subLote, "
				    + " imov.numeroSequencialRota, "
				    + " rot.codigo, "
				    + " imov.quantidadeEconomias "
	                + " FROM Imovel imov "
	                + " LEFT JOIN imov.localidade loc "
	                + " LEFT JOIN imov.setorComercial setorCom "
	                + " LEFT JOIN imov.quadra quad "
	                + " LEFT JOIN quad.rota rot "
	                + " WHERE " 
	                + " imov.id in ("+idsImovel+") "
	                + " ORDER BY "
	                + " loc.id, "
				    + " setorCom.codigo, "
				    + " rot.codigo, "
				    + " imov.numeroSequencialRota, "
				    + " quad.numeroQuadra, "
				    + " imov.lote, "
				    + " imov.subLote ";
			
			retorno = session.createQuery(consulta)
				//.setString("idsImovel",idsImovel)
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
	 * O método abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necessários para a criação da inscrição e de rota para exibição.
	 * 
	 * @author Vivianne Sousa
     * @date 06/11/2007
	 */

	public Collection pesquisarInscricaoImoveleRota(Collection colecaoIdsImovel)
	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Collection retorno = null;

		try {
			
			 consulta = "SELECT imov.id, "
				    + " loc.id, "
				    + " setorCom.codigo, "
				    + " quad.numeroQuadra, "
				    + " imov.lote, "
				    + " imov.subLote, "
				    + " imov.numeroSequencialRota, "
				    + " rot.codigo, "
				    + " imov.quantidadeEconomias "
	                + " FROM Imovel imov "
	                + " LEFT JOIN imov.localidade loc "
	                + " LEFT JOIN imov.setorComercial setorCom "
	                + " LEFT JOIN imov.quadra quad "
	                + " LEFT JOIN quad.rota rot "
	                + " WHERE " 
	                + " imov.id in (:idsImovel) "
	                + " ORDER BY "
	                + " loc.id, "
				    + " setorCom.codigo, "
				    + " rot.codigo, "
				    + " imov.numeroSequencialRota, "
				    + " quad.numeroQuadra, "
				    + " imov.lote, "
				    + " imov.subLote ";
			
			retorno = session.createQuery(consulta)
				.setParameterList("idsImovel",colecaoIdsImovel)
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
	 * O método abaixo realiza uma pesquisa em imovel e retorna os campos
	 * necessários para a criação da inscrição e de rota para exibição.
	 * 
	 * @author Vivianne Sousa
     * @date 06/11/2007
	 */

	public Collection pesquisarInscricaoImoveleRota(FiltrarAnaliseExcecoesLeiturasHelper filtrarAnaliseExcecoesLeiturasHelper, Integer anoMes)
		throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String sql = "";
		Collection retorno = null;

		try {
			
			 sql = "SELECT imovel.imov_id as idImovel, "
					+ " imovel.loca_id as idLocalidade, "
					+ " setorComercial.stcm_cdsetorcomercial as codigoSetorComercial, "
					+ " quadra.qdra_nnquadra as numeroQuadra, "
					+ " imovel.imov_nnlote as lote, "
					+ " imovel.imov_nnsublote as subLote, "
					+ " imovel.imov_nnsequencialrota as seqRota, "
					+ " rota.rota_cdrota as codigoRota, "
					+ " imovel.imov_qteconomia as qtdEconomias, "
					+ " clieUsuario.clie_nmcliente as nomeUsuario "
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
							+ " on leitAnormFatAgua.ltan_id = medHistAgua.ltan_idleitanormfatmt ";
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
					+ " on municipio.muni_id = bairro.muni_id " 
					+ " WHERE "
					+ criarCondicionalPesquisarAnaliseConsumoRelatorio(filtrarAnaliseExcecoesLeiturasHelper)
	                + " GROUP BY imovel.imov_id,imovel.loca_id,setorcomercial.stcm_cdsetorcomercial,quadra.qdra_nnquadra,imovel.imov_nnlote,imovel.imov_nnsublote,imovel.imov_nnsequencialrota,rota.rota_cdrota,imovel.imov_qteconomia,clieusuario.clie_nmcliente "
					+ " ORDER BY "
	                + " imovel.loca_id, "
				    + " setorComercial.stcm_cdsetorcomercial, "
				    + " rota.rota_cdrota, "
				    + " imovel.imov_nnsequencialrota, "
				    + " quadra.qdra_nnquadra, "
				    + " imovel.imov_nnlote, "
				    + " imovel.imov_nnsublote ";
			
			retorno = session.createSQLQuery(sql).addScalar("idImovel",
					Hibernate.INTEGER).addScalar("idLocalidade",
					Hibernate.INTEGER).addScalar("codigoSetorComercial",
					Hibernate.INTEGER).addScalar("numeroQuadra",
					Hibernate.INTEGER).addScalar("lote", Hibernate.SHORT)
					.addScalar("subLote", Hibernate.SHORT).addScalar("seqRota",
							Hibernate.INTEGER).addScalar("codigoRota",
							Hibernate.SHORT).addScalar("qtdEconomias",
							Hibernate.SHORT).addScalar("nomeUsuario",
									Hibernate.STRING).setInteger("anoMes", anoMes)
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
	
	private String criarCondicionalPesquisarAnaliseConsumoRelatorio(FiltrarAnaliseExcecoesLeiturasHelper filtrarAnaliseExcecoesLeiturasHelper) {

		
//		String retorno = " (medHistAgua.mdhi_id is not null or medHistEsgoto.mdhi_id is not null) and "
//			+ " (consHistAgua.cshi_id is not null or consHistEsgoto.cshi_id is not null) and "
//			+ " imovel.imov_icexclusao = " + ConstantesSistema.INDICADOR_IMOVEL_ATIVO;
		
		String retorno = "";
		
		if(filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper
				.getIdMedicaoTipo().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){	
			
			if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo().trim().equals("" + MedicaoTipo.LIGACAO_AGUA)){
				 retorno = retorno + " medHistAgua.mdhi_id is not null  and " ;			 	
			}else{
				 retorno = retorno + " medHistEsgoto.mdhi_id is not null and " ;			 		
			}
			
		}	
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo() != null
				&& !filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo().trim()
						.equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo().trim().equals("" + LigacaoTipo.LIGACAO_AGUA)) {
				 retorno = retorno + " consHistAgua.cshi_id is not null  and" ;
			} else {
				 retorno = retorno + " consHistEsgoto.cshi_id is not null and " ;
			}

		} 
		
		 retorno = retorno + " imovel.imov_icexclusao = " + ConstantesSistema.INDICADOR_IMOVEL_ATIVO;
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getIdImovel() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdImovel().trim().equals("")) {
			retorno = retorno + " and imovel.imov_id = " + filtrarAnaliseExcecoesLeiturasHelper.getIdImovel(); 
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getIdImovelCondominio() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdImovelCondominio().trim().equals("")) {
			retorno = retorno + " and imovel.imov_idimovelcondominio = " + filtrarAnaliseExcecoesLeiturasHelper.getIdImovelCondominio() 
			+ " or imovel.imov_id = " + filtrarAnaliseExcecoesLeiturasHelper.getIdImovelCondominio();  
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getIdFaturamentoGrupo() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdFaturamentoGrupo().trim().equals("")) {
			retorno = retorno + " and rota.ftgr_id = " + filtrarAnaliseExcecoesLeiturasHelper.getIdFaturamentoGrupo();
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getIdEmpresa() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdEmpresa().trim().equals("")) {
			retorno = retorno + " and rota.empr_id = " + filtrarAnaliseExcecoesLeiturasHelper.getIdEmpresa();
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getIdLocalidade() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdLocalidade().trim().equals("")) {
			retorno = retorno + " and imovel.loca_id = " + filtrarAnaliseExcecoesLeiturasHelper.getIdLocalidade();
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getCodigoSetorComercial() != null && !filtrarAnaliseExcecoesLeiturasHelper.getCodigoSetorComercial().trim().equals("")) {
			retorno = retorno + " and setorComercial.stcm_cdsetorcomercial = " + filtrarAnaliseExcecoesLeiturasHelper.getCodigoSetorComercial();
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getNumeroQuadraInicial() != null && !filtrarAnaliseExcecoesLeiturasHelper.getNumeroQuadraInicial().trim().equals("")) {
			retorno = retorno + " and quadra.qdra_nnquadra between " + filtrarAnaliseExcecoesLeiturasHelper.getNumeroQuadraInicial() + " and " + filtrarAnaliseExcecoesLeiturasHelper.getNumeroQuadraFinal(); 
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getCodigoRota() != null && !filtrarAnaliseExcecoesLeiturasHelper.getCodigoRota().trim().equals("")) {
			retorno = retorno + " and rota.rota_cdrota = " + filtrarAnaliseExcecoesLeiturasHelper.getCodigoRota(); 
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getIdUsuarioAlteracao() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdUsuarioAlteracao().trim().equals("")) {
			
			String idUsuario = filtrarAnaliseExcecoesLeiturasHelper.getIdUsuarioAlteracao();
			
			if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo().trim().equals("" + MedicaoTipo.LIGACAO_AGUA)) {
					retorno = retorno + " and medHistAgua.usur_idalteracao = " + idUsuario;
				} else {
					retorno = retorno + " and medHistEsgoto.usur_idalteracao = " + idUsuario;
				}
			} else {
				retorno = retorno + " and (medHistAgua.usur_idalteracao = " + idUsuario + " or medHistEsgoto.usur_idalteracao = " + idUsuario + ") ";
			}
			
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getIndicadorImovelCondominio() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIndicadorImovelCondominio().trim().equals("")) {
			retorno = retorno + " and imovel.imov_icimovelcondominio = " + filtrarAnaliseExcecoesLeiturasHelper.getIndicadorImovelCondominio(); 
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getIndicadorDebitoAutomatico() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIndicadorDebitoAutomatico().trim().equals("")) {
			retorno = retorno + " and imovel.imov_icdebitoconta = " + filtrarAnaliseExcecoesLeiturasHelper.getIndicadorDebitoAutomatico();
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getIndicadorAnalisado() != null) {
			String[] indicadorAnalisado = filtrarAnaliseExcecoesLeiturasHelper.getIndicadorAnalisado();
			String valores = "";
			
			for (int i = 0; i < indicadorAnalisado.length; i++) {
				if (indicadorAnalisado[i] != null && !indicadorAnalisado[i].trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					valores = valores + indicadorAnalisado[i] + ", ";
				}
			}
			
			valores = Util.removerUltimosCaracteres(valores, 2); 
			
			if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo().trim().equals("" + MedicaoTipo.LIGACAO_AGUA)) {
					retorno = retorno + " and medHistAgua.mdhi_icanalisado in ( " + valores + ") ";
				} else {
					retorno = retorno + " and medHistEsgoto.mdhi_icanalisado in ( " + valores + ") ";
				}
				
			} else {
				retorno = retorno + " and ( medHistAgua.mdhi_icanalisado in ( " + valores + ") or medHistEsgoto.mdhi_icanalisado in ( " + valores + " ) ) ";
			}
			
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getIdsImovelPerfil() != null) {
			String[] idsImovelPerfil = filtrarAnaliseExcecoesLeiturasHelper.getIdsImovelPerfil();
			String valores = "";
			
			for (int i = 0; i < idsImovelPerfil.length; i++) {
				if (idsImovelPerfil[i] != null && !idsImovelPerfil[i].trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					valores = valores + idsImovelPerfil[i] + ", ";
				}
			}
			
			valores = Util.removerUltimosCaracteres(valores, 2); 
			
			retorno = retorno + " and imovel.iper_id in ( " + valores + ") ";
			
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getIdCategoria() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdCategoria().trim().equals("")) {
			retorno = retorno + " and subcategoria.catg_id = " + filtrarAnaliseExcecoesLeiturasHelper.getIdCategoria();
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getQuantidadeEconomias() != null && !filtrarAnaliseExcecoesLeiturasHelper.getQuantidadeEconomias().trim().equals("")) {
			retorno = retorno + " and imovel.imov_qteconomia = " + filtrarAnaliseExcecoesLeiturasHelper.getQuantidadeEconomias();
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getIdTipoAnormalidade() != null) {
			
			Integer idTipoAnormalidade = filtrarAnaliseExcecoesLeiturasHelper.getIdTipoAnormalidade();
			
			if (idTipoAnormalidade.equals(FiltrarAnaliseExcecoesLeiturasHelper.ANORMALIDADE_LEITURA_INFORMADA)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo().trim().equals("" + MedicaoTipo.LIGACAO_AGUA)) {
						retorno = retorno + " and medHistAgua.ltan_idleitanorminformada is not null ";
					} else {
						retorno = retorno + " and medHistEsgoto.ltan_idleitanorminformada is not null ";
					}
				} else {
					retorno = retorno + " and (medHistAgua.ltan_idleitanorminformada is not null or medHistEsgoto.ltan_idleitanorminformada is not null) ";
				}
			} else if (idTipoAnormalidade.equals(FiltrarAnaliseExcecoesLeiturasHelper.ANORMALIDADE_LEITURA_FATURADA)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo().trim().equals("" + MedicaoTipo.LIGACAO_AGUA)) {
						retorno = retorno + " and medHistAgua.ltan_idleitanormfatmt is not null ";
					} else {
						retorno = retorno + " and medHistEsgoto.ltan_idleitanormfatmt is not null ";
					}
				} else {
					retorno = retorno + " and (medHistAgua.ltan_idleitanormfatmt is not null or medHistEsgoto.ltan_idleitanormfatmt is not null) ";
				}
			} else {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo().trim().equals("" + LigacaoTipo.LIGACAO_AGUA)) {
						retorno = retorno + " and consHistAgua.csan_id is not null ";
					} else {
						retorno = retorno + " and consHistEsgoto.csan_id is not null ";
					}
				} else {
					retorno = retorno + " and (consHistAgua.csan_id is not null or consHistEsgoto.csan_id is not null) ";
				}
			}
				
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getIdsAnormalidadeLeituraInformada() != null) {
			
			String[] idsAnormalidade = filtrarAnaliseExcecoesLeiturasHelper.getIdsAnormalidadeLeituraInformada();
			String valor = "";
			
			for (int i = 0; i < idsAnormalidade.length; i++) {
				String idAnormalidade = idsAnormalidade[i];
				
				if (idAnormalidade != null && !idAnormalidade.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					valor = valor + idAnormalidade + ", ";
				}
			}
			
			if (valor != null && !valor.equals("")) {
				
				valor = Util.removerUltimosCaracteres(valor, 2);
				
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo().trim().equals("" + MedicaoTipo.LIGACAO_AGUA)) {
						retorno = retorno + " and medHistAgua.ltan_idleitanorminformada in (" + valor + ")";
					} else {
						retorno = retorno + " and medHistEsgoto.ltan_idleitanorminformada in (" + valor + ")";
					}
				} else {
					retorno = retorno + " and (medHistAgua.ltan_idleitanorminformada in (" + valor + ")" + " or medHistEsgoto.ltan_idleitanorminformada in (" + valor + ") ) ";
				}
			}
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getIdsAnormalidadeLeituraFaturada() != null) {
			
			String[] idsAnormalidade = filtrarAnaliseExcecoesLeiturasHelper.getIdsAnormalidadeLeituraFaturada();
			String valor = "";
			
			for (int i = 0; i < idsAnormalidade.length; i++) {
				String idAnormalidade = idsAnormalidade[i];
				
				if (idAnormalidade != null && !idAnormalidade.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					valor = valor + idAnormalidade + ", ";
				}
			}
			
			if (valor != null && !valor.equals("")) {
				
				valor = Util.removerUltimosCaracteres(valor, 2);
				
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo().trim().equals("" + MedicaoTipo.LIGACAO_AGUA)) {
						retorno = retorno + " and medHistAgua.ltan_idleitanormfatmt in (" + valor + ")";
					} else {
						retorno = retorno + " and medHistEsgoto.ltan_idleitanormfatmt in (" + valor + ")";
					}
				} else {
					retorno = retorno + " and (medHistAgua.ltan_idleitanormfatmt in (" + valor + ")" + " or medHistEsgoto.ltan_idleitanormfatmt in (" + valor + ") ) ";
				}
			}
			
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getIdsAnormalidadeConsumo() != null) {
			
			String[] idsAnormalidade = filtrarAnaliseExcecoesLeiturasHelper.getIdsAnormalidadeConsumo();
			String valor = "";
			
			for (int i = 0; i < idsAnormalidade.length; i++) {
				String idAnormalidade = idsAnormalidade[i];
				
				if (idAnormalidade != null && !idAnormalidade.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					valor = valor + idAnormalidade + ", ";
				}
			}
			
			if (valor != null && !valor.equals("")) {
				
				valor = Util.removerUltimosCaracteres(valor, 2);
				
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo().trim().equals("" + LigacaoTipo.LIGACAO_AGUA)) {
						retorno = retorno + " and consHistAgua.csan_id in (" + valor + ")";
					} else {
						retorno = retorno + " and consHistEsgoto.csan_id in (" + valor + ")";
					}
				} else {
					retorno = retorno + " and (consHistAgua.csan_id in (" + valor + ")" + " or consHistEsgoto.csan_id in (" + valor + ") ) ";
				}
			}
			
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getConsumoFaturadoInicial() != null && !filtrarAnaliseExcecoesLeiturasHelper.getConsumoFaturadoInicial().trim().equals("")) {
			
			String consumoInicial = filtrarAnaliseExcecoesLeiturasHelper.getConsumoFaturadoInicial();
			String consumoFinal = filtrarAnaliseExcecoesLeiturasHelper.getConsumoFaturadoFinal();
			
			if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo().trim().equals("" + LigacaoTipo.LIGACAO_AGUA)) {
					retorno = retorno + " and consHistAgua.cshi_nnconsumofaturadomes >= " + consumoInicial 
									  + " and consHistAgua.cshi_nnconsumofaturadomes <= " + consumoFinal;
				} else {
					retorno = retorno + " and consHistEsgoto.cshi_nnconsumofaturadomes >= " + consumoInicial
									  + " and consHistEsgoto.cshi_nnconsumofaturadomes <= " + consumoFinal;
				}
			} else {
				retorno = retorno + " and ( ( consHistAgua.cshi_nnconsumofaturadomes >= " + consumoInicial
								  + " and consHistAgua.cshi_nnconsumofaturadomes <= " + consumoFinal + " ) "
								  + " or ( consHistEsgoto.cshi_nnconsumofaturadomes >= " + consumoInicial 
								  + " and consHistEsgoto.cshi_nnconsumofaturadomes <= " + consumoFinal + " ) ) ";
			}
			
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getConsumoMedidoInicial() != null && !filtrarAnaliseExcecoesLeiturasHelper.getConsumoMedidoInicial().trim().equals("")) {
			
			String consumoInicial = filtrarAnaliseExcecoesLeiturasHelper.getConsumoMedidoInicial();
			String consumoFinal = filtrarAnaliseExcecoesLeiturasHelper.getConsumoMedidoFinal();
			
			if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo().trim().equals("" + MedicaoTipo.LIGACAO_AGUA)) {
					retorno = retorno + " and medHistAgua.mdhi_nnconsumomedidomes >= " + consumoInicial 
									  + " and medHistAgua.mdhi_nnconsumomedidomes <= " + consumoFinal;
				} else {
					retorno = retorno + " and medHistEsgoto.mdhi_nnconsumomedidomes >= " + consumoInicial 
									  + " and medHistEsgoto.mdhi_nnconsumomedidomes <= " + consumoFinal;
				}
			} else {
				retorno = retorno + " and ( ( medHistAgua.mdhi_nnconsumomedidomes >= " + consumoInicial 
								  + " and medHistAgua.mdhi_nnconsumomedidomes <= " + consumoFinal + " ) " 
								  + " or ( medHistEsgoto.mdhi_nnconsumomedidomes >= " + consumoInicial 
								  + " and medHistEsgoto.mdhi_nnconsumomedidomes <= " + consumoFinal + " ) ) ";
			}
			
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getConsumoMedioInicial() != null && !filtrarAnaliseExcecoesLeiturasHelper.getConsumoMedioInicial().trim().equals("")) {
			
			String consumoInicial = filtrarAnaliseExcecoesLeiturasHelper.getConsumoMedioInicial();
			String consumoFinal = filtrarAnaliseExcecoesLeiturasHelper.getConsumoMedioFinal();
			
			if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdLigacaoTipo().trim().equals("" + LigacaoTipo.LIGACAO_AGUA)) {
					retorno = retorno + " and consHistAgua.cshi_nnconsumomedio >= " + consumoInicial
									  + " and consHistAgua.cshi_nnconsumomedio <= " + consumoFinal;
				} else {
					retorno = retorno + " and consHistEsgoto.cshi_nnconsumomedio >= " + consumoInicial
									  + " and consHistEsgoto.cshi_nnconsumomedio <= " + consumoFinal;
				}
			} else {
				retorno = retorno + " and ( ( consHistAgua.cshi_nnconsumomedio >= " + consumoInicial 
								  + " and consHistAgua.cshi_nnconsumomedio <= " + consumoFinal + " ) "
								  + " or ( consHistEsgoto.cshi_nnconsumomedio >= " + consumoInicial 
								  + " and consHistEsgoto.cshi_nnconsumomedio <= " + consumoFinal + " ) ) ";
			}
			
		}
		
		if (filtrarAnaliseExcecoesLeiturasHelper.getIdLeituraSituacaoAtual() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdLeituraSituacaoAtual().trim().equals("")) {
			
			String idLeituraSituacao = filtrarAnaliseExcecoesLeiturasHelper.getIdLeituraSituacaoAtual();
			
			if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo() != null && !filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				if (filtrarAnaliseExcecoesLeiturasHelper.getIdMedicaoTipo().trim().equals("" + MedicaoTipo.LIGACAO_AGUA)) {
					retorno = retorno + " and medHistAgua.ltst_idleiturasituacaoatual = " + idLeituraSituacao;
				} else {
					retorno = retorno + " and medHistEsgoto.ltst_idleiturasituacaoatual = " + idLeituraSituacao;
				}
			} else {
				retorno = retorno + " and (medHistAgua.ltst_idleiturasituacaoatual = " + idLeituraSituacao + " or medHistEsgoto.ltst_idleiturasituacaoatual >= " + idLeituraSituacao + ") ";
			}
			
		}
		
		return retorno;
	}
	

  
	
	/**
	 * 
	 * Pesquisa os imóveis do cliente de acordo com o tipo de relação
	 * 
	 * 
	 * 
	 * [UC0251] Gerar Atividade de Ação de Cobrança [SB0001] Gerar Atividade de
	 * 
	 * Ação de Cobrança para os Imóveis do Cliente
	 * 
	 * 
	 * 
	 * @author Sávio Luiz
	 * 
	 * @created 23/11/2007
	 * 
	 * 
	 * 
	 * @param cliente
	 * 
	 * @param relacaoClienteImovel
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Collection pesquisarImoveisClientesRelacao(Collection idsCliente,

			Integer numeroInicial)

	throws ErroRepositorioException {

		// cria a coleção de retorno

		Collection retorno = null;

		// Query

		String consulta;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			consulta = "select distinct im.id, rt.id, "

			+ "im.ligacaoAguaSituacao.id, " // 2

					+ "im.ligacaoEsgotoSituacao.id, " // 3

					+ "im.imovelPerfil.id, " // 4

					+ "rt.empresa.id, " // 5

					+ "im.localidade.id, " // 6

					+ "scm.codigo, " // 7

					+ "qd.numeroQuadra, " // 8

					+ "im.lote, " // 9

					+ "im.subLote, " // 10

					+ "qd.id, " // 11

					+ "im.cobrancaSituacaoTipo.id, " // 12

					+ "im.indicadorDebitoConta, " // 13

					+ "rt.empresaCobranca.id " // 14

					+ "from ClienteImovel ci " + "inner join ci.imovel im "

					+ "inner join ci.cliente cl "

					+ "inner join im.quadra qd " + "inner join qd.rota rt "

					+ "inner join im.setorComercial scm "

					+ "where cl.id in (:idsClientes) and ci.dataFimRelacao is null"
					+ " order by im.id";

			retorno = (Collection) session.createQuery(consulta).setParameterList(

			"idsClientes", idsCliente).setFirstResult(numeroInicial)

			.setMaxResults(500).list();

			// erro no hibernate

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
	 * Buscar Empresa apatir da Matrícula de um Imóvel .
	 * 
	 * 
	 * @author Thiago Nascimento
	 * @date 21/01/2008
	 * 
	 * @param dados
	 * 
	 * @throws ControladorException
	 */
	public Empresa buscarEmpresaPorMatriculaImovel(Integer imovel) throws ErroRepositorioException{
		Empresa empresa = null;
		Session session = HibernateUtil.getSession();
		
		try {

			StringBuffer hql =new StringBuffer("select r.empresa FROM Rota r where r.id = ");
			hql.append("(select q.rota.id FROM Quadra q where q.id =  ");
			hql.append(" ( select i.quadra.id FROM Imovel i where i.id =");
			hql.append(imovel);
			hql.append("))");
			
			empresa = (Empresa) session.createQuery(hql.toString()).uniqueResult();
			// erro no hibernate

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}
		
		return empresa;
	}
	
	/**
	 * 
	 * Atualiza a situação de cobrança do imóvel
	 * 
	 */

	public void atualizarSituacaoCobrancaImovel(Integer idSituacaoCobranca, Integer idImovel)
		throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {

				consulta = "update gcom.cadastro.imovel.Imovel set "

						+ "cbst_id = :idSituacaoCobranca, imov_tmultimaalteracao = :ultimaAlteracao " 
						
						+ "where imov_id = :idImovel ";

				session.createQuery(consulta).
				   setInteger("idSituacaoCobranca",idSituacaoCobranca).
				   setTimestamp("ultimaAlteracao",new Date()).
				   setInteger("idImovel",idImovel).
				   executeUpdate();


		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

	}

	
	/**
	 * Filtrar o Imovel pelos parametros informados, para saber a quantidade de imoveis.
	 * Utilizado para corrigir o erro da listagem de Imoveis: o metodo pesquisarQuantidadeImovel nao
	 * traz a mesma quantidade de imovel do metodo pesquisarImovelInscricaoNew.  
	 * 
	 * @author Ivan Sérgio
	 * @date 11/03/2008
	 * 
	 * @param idImovel
	 * @param idLocalidade
	 * @param codigoSetorComercial
	 * @param numeroQuadra
	 * @param lote
	 * @param subLote
	 * @param codigoCliente
	 * @param idMunicipio
	 * @param cep
	 * @param idBairro
	 * @param idLogradouro
	 * @param pesquisarImovelCondominio
	 * @param numeroPagina
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarQuantidadeImovelInscricao(
		String idImovel, String idLocalidade, String codigoSetorComercial,
		String numeroQuadra, String lote, String subLote,
		String codigoCliente, String idMunicipio, String cep,
		String idBairro, String idLogradouro, String numeroImovelInicial, String numeroImovelFinal, 
		boolean pesquisarImovelCondominio) throws ErroRepositorioException {

			Object retorno = null;
			Session session = HibernateUtil.getSession();
			String consulta = null;

			try {
				consulta = "select count(distinct imovel.imov_id) as qtd "
				
//				consulta = "select distinct logradouro.nome," + // 0
//						  " logradouroTipo.descricaoAbreviada," + // 1
//	 					  " logradouroTitulo.descricaoAbreviada," + // 2
//						  " bairro.id," + // 3
//						  " bairro.nome," + // 4
//						  " municipio.id," + // 5
//						  " municipio.nome," + // 6
//						  " unidadeFederacao.id," + // 7
//						  " unidadeFederacao.sigla," + // 8
//						  " enderecoReferencia.descricaoAbreviada," + // 9
//						  " cep.cepId,"	+ // 10
//						  " cep.logradouro," + // 11
//						  " cep.descricaoTipoLogradouro," + // 12
//						  " cep.bairro," + // 13
//						  " cep.municipio," + // 14
//					      " cep.sigla, " + // 15
//						  " cep.codigo, " + // 16
//						  " imovel.numeroImovel," + // 17
//	  					  " imovel.complementoEndereco," + // 18
//						  " logradouro.id," + // 19
//						  " logradouroCep.id," + // 20
//						  " logradouroBairro.id," + // 21
//						  " logradouroTipo.descricao," + // 22
//						  " logradouroTitulo.descricao," + // 23
//						  " enderecoReferencia.descricao, " + // 24
//					      " imovel.id, " + // 25
//						  " imovel.lote, " + // 26
//					      " imovel.subLote, " + // 27
//						  " localidade.id, " + // 28
//						  " setorComercial.codigo, " + // 29
//						  " quadra.numeroQuadra, " + // 30
//						  " clienteUsuario.nome " // 31
					
					+ "from cadastro.cliente_imovel clienteImovel "
					
					+ "inner join cadastro.imovel imovel "
					+ "on imovel.imov_id = clienteImovel.imov_id "

					+ "inner join cadastro.quadra quadra "
					+ "on quadra.qdra_id = imovel.qdra_id "

					+ "inner join cadastro.localidade localidade "
					+ "on localidade.loca_id = imovel.loca_id "

					+ "inner join cadastro.setor_comercial setorComercial "
					+ "on setorComercial.stcm_id = imovel.stcm_id "

					+ "left join cadastro.logradouro_cep logradouroCep "
					+ "on logradouroCep.lgcp_id = imovel.lgcp_id "

					+ "left join cadastro.cep cep "
					+ "on cep.cep_id = logradouroCep.cep_id "

					+ "left join cadastro.logradouro logradouro "
					+ "on logradouro.logr_id = logradouroCep.logr_id "

					+ "left join cadastro.logradouro_tipo logradouroTipo "
					+ "on logradouroTipo.lgtp_id = logradouro.lgtp_id "

					+ "left join cadastro.logradouro_titulo logradouroTitulo "
					+ "on logradouroTitulo.lgtt_id = logradouro.lgtt_id "

					+ "left join cadastro.logradouro_bairro logradouroBairro "
					+ "on logradouroBairro.lgbr_id = imovel.lgbr_id "

					+ "left join cadastro.bairro bairro "
					+ "on bairro.bair_id = logradouroBairro.bair_id "

					+ "left join cadastro.municipio municipio "
					+ "on municipio.muni_id = bairro.muni_id "

					+ "left join cadastro.unidade_federacao unidadeFederacao "
					+ "on unidadeFederacao.unfe_id = municipio.unfe_id "

					+ "left join cadastro.endereco_referencia enderecoReferencia "
					+ "on enderecoReferencia.edrf_id = imovel.edrf_id "

					+ "inner join cadastro.cliente_relacao_tipo clienteRelacaoTipo "
					+ "on clienteRelacaoTipo.crtp_id = clienteImovel.crtp_id "

					+ "inner join cadastro.cliente clienteUsuario "
					+ "on clienteUsuario.clie_id = clienteImovel.clie_id ";



						/* * ## JOIN ## *  */

						// join necessários
//						+ "left join clienteImovel.imovel imovel "
//						+ "inner join imovel.quadra quadra "
//						+ "inner join imovel.localidade localidade "
//						+ "inner join imovel.setorComercial setorComercial "
//						+ "left join imovel.logradouroCep logradouroCep "
//						+ "left join logradouroCep.cep cep "
//						+ "left join logradouroCep.logradouro logradouro "
//						+ "left join logradouro.logradouroTipo logradouroTipo "
//						+ "left join logradouro.logradouroTitulo logradouroTitulo "
//						+ "left join imovel.logradouroBairro logradouroBairro "
//						+ "left join logradouroBairro.bairro bairro "
//						+ "left join bairro.municipio municipio "
//						+ "left join municipio.unidadeFederacao unidadeFederacao "
//						+ "left join imovel.enderecoReferencia enderecoReferencia "
//						+ "inner join clienteImovel.clienteRelacaoTipo clienteRelacaoTipo "
//						+ "inner join clienteImovel.cliente clienteUsuario ";
				// join facultativos

				// cep
//				if (cep != null	&& !cep.equals("") && !cep.trim().equalsIgnoreCase(
//					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {
//
//					consulta = consulta
//						+ " left join imovel.logradouroCep logradouroCep "
//						+ "left join logradouroCep.cep cep ";
//				}

				// bairro
//				if (idBairro != null && !idBairro.equals("") && !idBairro.trim().equalsIgnoreCase(
//					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {
//
//					consulta = consulta
//						+ " left join imovel.logradouroBairro logradouroBairro "
//						+ " left join logradouroBairro.bairro bairro ";
//				}

				// logradouro
//				if (idLogradouro != null && !idLogradouro.equals("") && !idLogradouro.trim().equalsIgnoreCase(
//					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {
//
//					consulta = consulta
//						+ " left join imovel.logradouroCep logradouroCep "
//						+ " left join logradouroCep.logradouro logradouro ";
//				}

				// municipio
//				if (idMunicipio != null && !idMunicipio.equals("") && !idMunicipio.trim().equalsIgnoreCase(
//					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {
//
//					consulta = consulta
//						+ " left join imovel.logradouroBairro logradouroBairro "
//						+ " left join logradouroBairro.bairro bairro "
//						+ "left join bairro.municipio municipio ";
//				}

				/* * ## CONDIÇÕES ## *  */

				consulta = consulta

				+ " where clienteImovel.clim_dtrelacaofim is null "

				+ " and imovel.imov_icexclusao <> "

				+ Imovel.IMOVEL_EXCLUIDO + " and  ";

				// pesquisar imovel condominio

				if (pesquisarImovelCondominio) {

					consulta = consulta + " imovel.imov_icimovelcondominio = "

					+ Imovel.IMOVEL_CONDOMINIO + "  and  ";

				}

				// imovel

				if (idImovel != null

				&& !idImovel.equals("")

				&& !idImovel.trim().equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString())) {

					consulta = consulta + " imovel.imov_id = :idImovel  and  ";

				}

				// localidade

				if ((idLocalidade != null && !idLocalidade.equals("") && !idLocalidade

				.trim().equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

					consulta = consulta + " localidade.loca_id = :idLocalidade  and  ";

				}

				// setor comercial

				if ((codigoSetorComercial != null

				&& !codigoSetorComercial.equals("") && !codigoSetorComercial

				.trim().equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

					consulta = consulta

					+ " setorComercial.stcm_cdsetorcomercial = :codigoSetorComercial  and  ";

				}

				// quadra

				if ((numeroQuadra != null && !numeroQuadra.equals("") && !numeroQuadra

				.trim().equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

					consulta = consulta

					+ " quadra.qdra_nnquadra = :numeroQuadra and  ";

				}

				// lote

				if ((lote != null && !lote.equals("") && !lote.trim()

				.equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

					consulta = consulta + " imovel.imov_nnlote = :lote  and  ";

				}

				// sublote

				if ((subLote != null && !subLote.equals("") && !subLote.trim()

				.equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString()))) {

					consulta = consulta + " imovel.imov_nnsublote = :subLote  and  ";

				}
				
				
				
				if (numeroImovelInicial != null && !numeroImovelInicial.trim().equals("")
						&& numeroImovelFinal != null && !numeroImovelFinal.trim().equals("")) {
					
					consulta += " RTRIM(LTRIM(translate(imovel.imov_nnimovel, '" + ConstantesSistema.CARACTERES_ALFANUMERICOS + "', ''))) <> '' and ";
					
					consulta += " to_number(RTRIM(LTRIM(translate(imovel.imov_nnimovel, '" + ConstantesSistema.CARACTERES_ALFANUMERICOS + "', ''))), 99999) between '" + numeroImovelInicial.trim() + "' and '" + numeroImovelFinal.trim() + "' and ";
				}

				// cliente

				if (codigoCliente != null

				&& !codigoCliente.equals("")

				&& !codigoCliente.trim().equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString())) {

					consulta = consulta

					+ " clienteUsuario.clie_id = :codigoCliente  and  ";

				}

				// municipio

				if (idMunicipio != null

				&& !idMunicipio.equals("")

				&& !idMunicipio.trim().equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString())) {

					consulta = consulta + " municipio.muni_id = :idMunicipio  and  ";

				}

				// cep

				if (cep != null

				&& !cep.equals("")

				&& !cep.trim().equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString())) {

					consulta = consulta + " cep.cep_cdcep = :cep  and  ";

				}

				// bairro

				if (idBairro != null

				&& !idBairro.equals("")

				&& !idBairro.trim().equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString())) {

					consulta = consulta + " bairro.bair_cdbairro = :idBairro  and  ";

				}

				// logradouro

				if (idLogradouro != null

				&& !idLogradouro.equals("")

				&& !idLogradouro.trim().equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString())) {

					consulta = consulta + " logradouro.logr_id = :idLogradouro  and  ";

				}

				Query query = session.createSQLQuery(consulta.substring(0, (consulta.length() - 5))).addScalar("qtd", Hibernate.INTEGER);
				// seta os valores na condição where
				// imovel principal
				if (idImovel != null && !idImovel.equals("") && !idImovel.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

					query.setInteger("idImovel", new Integer(idImovel).intValue());
				}

				// localidade
				if ((idLocalidade != null && !idLocalidade.equals("") && !idLocalidade.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

					query.setInteger("idLocalidade", new Integer(idLocalidade).intValue());
				}

				// setor
				if ((codigoSetorComercial != null && !codigoSetorComercial.equals("") && !codigoSetorComercial.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

					query.setInteger("codigoSetorComercial", new Integer(codigoSetorComercial).intValue());
				}

				// quadra
				if ((numeroQuadra != null && !numeroQuadra.equals("") && !numeroQuadra.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

					query.setInteger("numeroQuadra", new Integer(numeroQuadra).intValue());
				}

				// lote
				if ((lote != null && !lote.equals("") && !lote.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

					query.setInteger("lote", new Integer(lote).intValue());
				}

				// subLote
				if ((subLote != null && !subLote.equals("") && !subLote.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString()))) {

					query.setInteger("subLote", new Integer(subLote).intValue());
				}
				
				// cliente
				if (codigoCliente != null && !codigoCliente.equals("") && !codigoCliente.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

					query.setInteger("codigoCliente", new Integer(codigoCliente).intValue());
				}

				// municipio
				if (idMunicipio != null && !idMunicipio.equals("") && !idMunicipio.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

					query.setInteger("idMunicipio", new Integer(idMunicipio).intValue());
				}

				// cep
				if (cep != null && !cep.equals("") && !cep.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

					query.setInteger("cep", new Integer(cep).intValue());
				}
				// bairro
				if (idBairro != null && !idBairro.equals("") && !idBairro.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

					query.setInteger("idBairro", new Integer(idBairro).intValue());
				}

				// logradouro
				if (idLogradouro != null && !idLogradouro.equals("") && !idLogradouro.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

					query.setInteger("idLogradouro", new Integer(idLogradouro).intValue());
				}
				
				try{
					retorno = query.uniqueResult();
				}catch (DataException ex) {
					//caso seja exceção devido a conversão de alfanumerico para numerico
					//levanta a exceção para a próxima camada
					throw new ErroRepositorioException(ex, ex.getSQLState());
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
	 * [UC0800] - Obter Consumo Não Medido
	 *
	 * [FS0001] - Verificar Área Não Informada 
	 *
	 * @author Raphael Rossiter
	 * @date 22/05/2008
	 *
	 * @param idImovel
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarAreaConstruida(Integer idImovel) throws ErroRepositorioException {

		Object[] retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "SELECT imov.areaConstruida, acon.menorFaixa "//0, 1
					+ "FROM Imovel imov "
					+ "LEFT JOIN imov.areaConstruidaFaixa acon "
					+ "WHERE imov.id = :idImovel "; 
		
			retorno = (Object[]) session.createQuery(consulta)
					.setInteger("idImovel", idImovel).setMaxResults(1).uniqueResult();
		
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
	 * Método para obter o id da esfera de poder de um imovel
	 * @param idImovel
	 * @return
	 * 
	 * @author Francisco do Nascimento
	 * @date 22-05-2008
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer obterIdEsferaPoder(Integer idImovel)
		throws ErroRepositorioException {
	
		Collection retorno = null;
		Integer idEsferaPoder = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
	
		try {
	
			consulta = "SELECT ct.epod_id as idEsferaPoder FROM cadastro.cliente_imovel ci, " +
					" cadastro.cliente c, cadastro.cliente_tipo ct " +
					" where ci.clie_id = c.clie_id and c.cltp_id = ct.cltp_id " +
					" and ci.crtp_id = " + ClienteRelacaoTipo.USUARIO +
					" and ci.imov_id = " + idImovel;
	
			retorno = session.createSQLQuery(consulta)
				.addScalar("idEsferaPoder", Hibernate.INTEGER).setMaxResults(1).list();
			
			idEsferaPoder = (Integer) Util.retonarObjetoDeColecao(retorno);
				
		} catch (HibernateException e) {
	
			// levanta a exceção para a próxima camada
	
			throw new ErroRepositorioException(e, "Erro no Hibernate");
	
		} finally {
	
			// fecha a sessão
	
			HibernateUtil.closeSession(session);
	
		}
	
		return idEsferaPoder;
	
	}

	/**
	 * Consulta o ID da categoria principal, considerando que a categoria principal é a quem tem
	 * maior soma de qtd de economias em suas subcategorias e tenha o 
	 * idCategoria menor (ou maior caso exija ordemDecrescente seja true)
	 * 
	 *  @author Francisco do Nascimento
	 *  @date 22/05/2008
	 *  
	 */
	public Integer obterIdCategoriaPrincipal(Integer idImovel, boolean ordemDecrescente)
		throws ErroRepositorioException {

		Collection retorno = null;
		Integer idCategoria = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT sc.catg_id as idCategoria, count(isc.imsb_qteconomia) as quantidade FROM " +
					"cadastro.subcategoria sc, " +
					"cadastro.categoria c , " +
					"cadastro.imovel_subcategoria isc " +
					"where sc.catg_id = c.catg_id and isc.scat_id = sc.scat_id " +
					"and isc.imov_id = :idImovel group by sc.catg_id " +
					"order by quantidade DESC, idCategoria " +
					(ordemDecrescente ? "DESC" : "");

			retorno = session.createSQLQuery(consulta).addScalar("idCategoria", Hibernate.INTEGER)
				.addScalar("quantidade", Hibernate.INTEGER).setInteger("idImovel", idImovel.intValue()).list();

			if (retorno != null && !retorno.isEmpty()){
				Iterator iterator = retorno.iterator();
				Object[] retorno2 = (Object[]) iterator.next();
				idCategoria = (Integer) retorno2[0];
			}
			
		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return idCategoria;

	}

    
    /**
     * [UC0011] Inserir Imovel
     * 
     * @author Vivianne Sousa
     * @date 23/05/2008
     */
    public Collection pesquisarImovelPerfilDiferenteCorporativo() throws ErroRepositorioException {

        Collection retorno = null;
        Session session = HibernateUtil.getSession();
        String consulta = null;

        try {

            consulta = " SELECT iper " + 
            " FROM ImovelPerfil iper " + 
            " WHERE iper.indicadorUso = :indicadorUso " + 
            " AND iper.indicadorGeracaoAutomatica = :indicadorGeracaoAutomatica " + 
            " AND iper.id NOT IN(:corporativo, :grandeTelemedido, :corporativoTelemed) " +
            " ORDER BY iper.descricao " ;

            retorno = (Collection) session.createQuery(consulta)
            .setShort("indicadorUso", ConstantesSistema.INDICADOR_USO_ATIVO)
            .setShort("indicadorGeracaoAutomatica",ImovelPerfil.NAO)
            .setInteger("corporativo",ImovelPerfil.CORPORATIVO)
            .setInteger("grandeTelemedido", ImovelPerfil.GRANDE_TELEMEDIDO)
            .setInteger("corporativoTelemed", ImovelPerfil.CORPORATIVO_TELEMED)
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
     * [UC0011] Inserir Imovel
     * 
     * @author Vivianne Sousa
     * @date 23/05/2008
     */
    public Collection pesquisarImovelPerfilTarifaSocialDiferenteCorporativo() throws ErroRepositorioException {

        Collection retorno = null;
        Session session = HibernateUtil.getSession();
        String consulta = null;

        try {

            consulta = " SELECT iper " + 
            " FROM ImovelPerfil iper " + 
            " WHERE iper.indicadorUso = :indicadorUso " + 
            " AND iper.id NOT IN(:corporativo, :grandeTelemedido, :corporativoTelemed) " +
            " ORDER BY iper.descricao " ;

            retorno = (Collection) session.createQuery(consulta)
            .setShort("indicadorUso", ConstantesSistema.INDICADOR_USO_ATIVO)
            .setInteger("corporativo",ImovelPerfil.CORPORATIVO)
            .setInteger("grandeTelemedido", ImovelPerfil.GRANDE_TELEMEDIDO)
            .setInteger("corporativoTelemed", ImovelPerfil.CORPORATIVO_TELEMED)
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
	 * Consulta os Negativadores para a geracao do resumo diario da negativacao
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * Fluxo principal Item 1.0
	 *
	 * @author Thiago Toscano
	 * @date 07/01/2008
	 */
	public List pesquisarLocalidadeComImoveisComSituacaoLigadoEmAnalise()
		throws ErroRepositorioException {
		
		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try {
			String hql = " select distinct(l.id) "
					 + " from gcom.cadastro.imovel.Imovel imov "
					 + " left join imov.localidade l "		
					 + " where " 					
					 + " imov.ligacaoAguaSituacao.id = 4 ";					
					
			retorno = (List) session.createQuery(hql).list();
		
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
     * [UC0823] Atualiza Ligação de Água de Ligado em Análise para Ligado
     * Seleciona a lista de imóveis que esteja com a situação de água ligado em análise.
     * @author Yara Taciane
     * @date 23/05/2008
     */
    public Collection pesquisarImoveisComSituacaoLigadoEmAnalise(Integer idLocalidade) throws ErroRepositorioException {
    	
    	List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();

			try {
				String sql = " select imov.imov_id as idImovel"					
					   + " from cadastro.imovel imov "
					   + " where  imov.last_id in "
					   + "( "  +  LigacaoAguaSituacao.LIGADO_EM_ANALISE  + ","
					   +  LigacaoAguaSituacao.CORTADO + ")" +  "and" 
					   + " imov.loca_id = " + idLocalidade;					  
				
				Collection coll =(Collection) session.createSQLQuery(sql).
				addScalar("idImovel" , Hibernate.INTEGER).list();

				if (coll != null) {
					Iterator it = coll.iterator();
					while(it.hasNext()) {
						Integer idImovel = (Integer) it.next();

						Imovel imovel = new Imovel();
						imovel.setId(idImovel);						
					
						retorno.add(imovel);

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
	 * [UC0823] Atualizar Situação Água Para Ligado Auhtor: Yara Taciane
	 * Data: 07/07/2008
	 * 
	 * @param id
	 *            Matricula do Imovel
	 * @param situacaoAguaLigado
	 *            Situação Agua
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void atualizarSituacaoAguaPorImovel(String id, String situacaoAguaLigado) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String atualizarImovel;	

		try {
			System.out.println("Entrou.....");
			atualizarImovel = "update gcom.cadastro.imovel.Imovel "
					+ "set last_id = :situacaoAgua , imov_tmultimaalteracao = :ultimaAlteracao  where imov_id = :id";

			session.createQuery(atualizarImovel).
			setInteger("situacaoAgua",new Integer(situacaoAguaLigado).intValue()).
			setTimestamp("ultimaAlteracao",new Date()).
			setInteger("id", new Integer(id).intValue()).executeUpdate();
	
		} catch (Exception e) {
			System.out.println("Erro");
			throw new ErroRepositorioException(e, "Erro no Hibernate");
			
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}
    
	/**
	 * [UC0101] - Consistir Leituras e Calcular Consumos
	 *
	 * Pesquisar as contas pertencentes ao imovel e anoMes informados que não estejam com a situação
	 * atual igual a "PRÉ-FATURADA" 
	 *
	 * @author Raphael Rossiter
	 * @date 15/08/2008
	 *
	 * @param imovelId
	 * @param anoMesReferencia
	 * @return Object
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarImovelIdComContaNaoPreFaturada(Integer imovelId,

	Integer anoMesReferencia) throws ErroRepositorioException {

		Object retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "select ct.id from Conta ct "

			+ "inner join ct.imovel im "
			+ "inner join ct.debitoCreditoSituacaoAtual situacaoAtual "

			+ "where im.id = :imovelId and ct.referencia = :anoMesReferencia "
			+ "and situacaoAtual.id <> :preFaturada ";

			retorno = session.createQuery(consulta)
			.setInteger("imovelId", imovelId.intValue())
			.setInteger("anoMesReferencia", anoMesReferencia)
			.setInteger("preFaturada", DebitoCreditoSituacao.PRE_FATURADA).setMaxResults(1).uniqueResult();

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
	 * Pesquisar as contas pertencentes ao imovel e anoMes informados que não estejam com a situação
	 * atual igual a "PRÉ-FATURADA" 
	 *
	 * @author Raphael Rossiter
	 * @date 15/08/2008
	 *
	 * @param imovelId
	 * @param anoMesReferencia
	 * @return Object
	 * @throws ErroRepositorioException
	 */
	public Object pesquisarImovelCondominioIdComContaNaoPreFaturada(Integer imovelId,

	Integer anoMesReferencia) throws ErroRepositorioException {

		Object retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "select ct.id from Conta ct "

			+ "inner join ct.imovel im "
			+ "inner join ct.debitoCreditoSituacaoAtual situacaoAtual "

			+ "where im.imovelCondominio.id = :imovelId and ct.referencia = :anoMesReferencia "
			+ "and situacaoAtual.id <> :preFaturada ";

			retorno = session.createQuery(consulta)
			.setInteger("imovelId", imovelId.intValue())
			.setInteger("anoMesReferencia", anoMesReferencia)
			.setInteger("preFaturada", DebitoCreditoSituacao.PRE_FATURADA).setMaxResults(1).uniqueResult();

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
	 * Consultar Perfil Quadra
	 * 
	 * @param idImovel
	 * 
	 * @return Perfil da Quadra
	 * 
	 * @exception ErroRepositorioException
	 * 
	 */

	public Integer obterQuadraPerfil(Integer idImovel)
	throws ErroRepositorioException {
	
		Integer retorno = null;
	
	Session session = HibernateUtil.getSession();
	String consulta;
	
	try {
		consulta = " select qdpf_id as perfilQuadra"
				 + " from cadastro.imovel i"
				 + " inner join cadastro.quadra q on(i.qdra_id = q.qdra_id)"
				 + " where i.imov_id = "+idImovel;
		
		retorno =(Integer) session.createSQLQuery(consulta)
					.addScalar("perfilQuadra" , Hibernate.INTEGER)
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
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 22/09/2008
	 */
	public void atualizarImovelSituacaoAtualizacaoCadastral(Integer idImovel,
			Integer idSituacaoAtualizacaoCadastral) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String update;

		try {

			update = "update gcom.cadastro.imovel.Imovel set "
					+ "siac_id = :idSituacaoAtualizacaoCadastral, " 
					+ "imov_tmultimaalteracao = :datahoracorrente "
					+ "where imov_id = :idImovel ";

			session.createQuery(update)
			.setInteger("idImovel", idImovel)
			.setInteger("idSituacaoAtualizacaoCadastral", idSituacaoAtualizacaoCadastral)
			.setTimestamp("datahoracorrente", new Date()).executeUpdate();


		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

	}
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 22/09/2008
	 */
	public void atualizarImovelAtualizacaoCadastralSituacaoAtualizacaoCadastral(Integer idImovel,
			Integer idSituacaoAtualizacaoCadastral, Integer idEmpresa) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String update;

		try {

			update = "update gcom.cadastro.imovel.ImovelAtualizacaoCadastral set "
					+ "siac_id = :idSituacaoAtualizacaoCadastral, " 
					+ "imac_tmultimaalteracao = :datahoracorrente ";
					

			if(idEmpresa != null){
				update = update + ",empr_id = :idEmpresa ";
			}
			
			update = update + "where imov_id = :idImovel ";
			
			if(idEmpresa != null && !idEmpresa.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
				session.createQuery(update)
				.setInteger("idImovel", idImovel)
				.setInteger("idSituacaoAtualizacaoCadastral", idSituacaoAtualizacaoCadastral)
				.setTimestamp("datahoracorrente", new Date())
				.setInteger("idEmpresa",idEmpresa).executeUpdate();
			}else{
				session.createQuery(update)
				.setInteger("idImovel", idImovel)
				.setInteger("idSituacaoAtualizacaoCadastral", idSituacaoAtualizacaoCadastral)
				.setDate("datahoracorrente", new Date()).executeUpdate();
			}
			


		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

	}
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 26/09/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer verificaExistenciaImovelAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Integer retornoConsulta = null;

		try {

			consulta = "select imov_id as id " + 
					   "from cadastro.imovel_atlz_cadastral " + 
					   "where imov_id = :idImovel  " ;
				
			retornoConsulta = (Integer)session.createSQLQuery(consulta)
					.addScalar("id", Hibernate.INTEGER)
					.setInteger("idImovel",idImovel)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);

		}

		return retornoConsulta;

	}
	
	/**
	 * Informar Economia
	 * 
	 * @author Vivianne Sousa
	 * @date 23/10/2008
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelEconomia(Integer idImovel, Integer idSubcategoria) 
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
	
		Collection retorno = null;
		String consulta = null;
	
		try {
			
			consulta = 	" select imec " +
						" from ImovelEconomia imec " +
						" left join fetch imec.areaConstruidaFaixa acon " +
						" left join fetch imec.imovelSubcategoria imsb " +
						" left join fetch imsb.comp_id.imovel imov " +
						" left join fetch imov.imovelPerfil iper " +
						" left join fetch imsb.comp_id.subcategoria scat " +
						" left join fetch scat.categoria " +
						" where imov.id = :idImovel " +
						" and scat.id = :idSubcategoria ";
					
			retorno = session.createQuery(consulta)
					.setInteger("idImovel", idImovel)
					.setInteger("idSubcategoria", idSubcategoria)
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
     * Pesquisar Imóvel Atualização Cadastral(Dados da Inscrição)
     * 
     * @author Ana Maria
     * @date 17/09/2008
     * 
     * @throws ErroRepositorioException
     */
    public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastralInscricao(Integer idImovel, Integer idEmpresa) 
    	throws ErroRepositorioException {

    	ImovelAtualizacaoCadastral imovelAtualizacaoCadastral= null;
        String consulta = "";
        Session session = HibernateUtil.getSession();

        try {

            consulta = " SELECT imov"
            		 + " FROM ImovelAtualizacaoCadastral imov"
            		 + " WHERE imov.idImovel = :idImovel"
            		 + " AND imov.idSituacaoAtualizacaoCadastral="+SituacaoAtualizacaoCadastral.DISPONIVEL
            		 + " AND imov.idEmpresa = "+idEmpresa;

            imovelAtualizacaoCadastral = (ImovelAtualizacaoCadastral) session.createQuery(consulta).setInteger(
            "idImovel", idImovel).setMaxResults(1).uniqueResult();

            // erro no hibernate
        } catch (HibernateException e) {
            // levanta a exceção para a próxima camada
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            // fecha a sessão
            HibernateUtil.closeSession(session);
        }

        // retorna o imóvel
        return imovelAtualizacaoCadastral;
    }

	/**
	 * Consultar Imóveis Atualização Cadastral por Quadra
	 * 
	 * @param idSetorComercial
	 * @param quadraInicial
	 * @param quadraFinal
	 * @param idEmpresa
	 * @return Collection<Imovel> - Coleção de imóveis.
	 * 
	 * @author Ana Maria
     * @date 18/09/2008
	 * @exception ErroRepositorioException
	 */
	public Collection obterImoveisAtualizacaoCadastral(Integer idLocalidade, String codigoSetor,
			Integer quadraInicial, Integer quadraFinal, Integer idEmpresa, Integer idRota)throws ErroRepositorioException {
	
		Collection retorno = null;
	
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = " SELECT imov.id "
					 + " FROM ImovelAtualizacaoCadastral imov"
					 + " WHERE imov.idEmpresa ="+idEmpresa
					 + " AND imov.idSituacaoAtualizacaoCadastral="+SituacaoAtualizacaoCadastral.DISPONIVEL
					 + " AND imov.idLocalidade = "+idLocalidade;
					 
					 if(codigoSetor != null){
						 consulta = consulta + " AND imov.codigoSetorComercial="+codigoSetor;
					 }
					 if(quadraInicial != null){
						consulta = consulta + " AND imov.numeroQuadra between "+ quadraInicial+" AND "+quadraFinal+")";  
					 }else if(idRota != null){
						 consulta = consulta + " AND imov.numeroQuadra in"
						 					 + "           (SELECT qdra.numeroQuadra"
						 					 + "            FROM Quadra qdra"
						 					 + "            WHERE qdra.rota.id = "+ idRota+")";
					 }
					 
					 consulta = consulta + " order by imov.idLocalidade, imov.codigoSetorComercial, imov.numeroQuadra, imov.lote, imov.subLote";
			
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
	 * Pesquisar existência de imóvel economia
	 * 
	 * @author Ana Maria
	 * @date 05/12/2008
	 * 
	 * @return Boolean
	 * @throws ErroRepositorioException
	 */
	public Boolean pesquisarExistenciaImovelEconomia(Integer idImovel, Integer idSubcategoria) 
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
	
		Boolean retorno = false;
		String consulta = null;
	
		try {
			
			consulta = 	" select imec.id " +
						" from ImovelEconomia imec " +
						" left join imec.imovelSubcategoria imsb " +
						" left join imsb.comp_id.imovel imov " +
						" left join imsb.comp_id.subcategoria scat " +
						" where imov.id = :idImovel " +
						" and scat.id = :idSubcategoria ";
					
		    Collection colecaoConsulta = session.createQuery(consulta)
											.setInteger("idImovel", idImovel)
											.setInteger("idSubcategoria", idSubcategoria).list();
			
			if (!colecaoConsulta.isEmpty()) {
				retorno = true;
			} else {
				retorno = false;
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
	 * Validar a(s) categoria(s) do imovel com a(s) respectivas tarifas 
	 * 
	 * @author Rômulo Aurélio
	 * @date 19/12/2008
	 * 
	 * @return Boolean
	 * @throws ErroRepositorioException
	 */
	public Boolean verificarExistenciaTarifaConsumoCategoriaParaCategoriaImovel(Integer idImovel, Integer idCategoria) 
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
	
		Boolean retorno = false;
		String consulta = null;
	
		try {
			
			consulta = 	"select cstc.catg_id as idCategoria " + 
			"from cadastro.imovel imov " +
			"left join faturamento.consumo_tarifa cstf on cstf.cstf_id = imov.cstf_id " + 
			"left join faturamento.consumo_tarifa_vigencia cstv on cstv.cstf_id = cstf.cstf_id " +
			"left join faturamento.consumo_tarifa_categoria cstc on cstc.cstv_id = cstv.cstv_id " +
			"where imov.imov_id = :idImovel and cstv.cstv_dtvigencia = (select max(cstv_dtvigencia) " +
			"from faturamento.consumo_tarifa_vigencia " +	
			"where cstf_id = imov.cstf_id) and cstc.catg_id = :idCategoria " +
			"group by cstc.catg_id ";
					
			Collection colecaoConsulta = session.createSQLQuery(consulta).addScalar("idCategoria", Hibernate.INTEGER)
					.setInteger("idImovel", idImovel).setInteger("idCategoria",
							idCategoria).list();
		    
		 /*
			 * Collection colecaoConsulta =
			 * session.createSQLQuery(consulta).addScalar(
			 * "idClienteResponsavel", Hibernate.INTEGER).addScalar( "idImovel",
			 * Hibernate.INTEGER).addScalar( "situacaoLigacaoAgua",
			 * Hibernate.STRING).setInteger( "idImovel", idImovel).list();
			 */
		    
			
			if (!colecaoConsulta.isEmpty()) {
				retorno = true;
			} else {
				retorno = false;
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
	 * Remover Imóvel Subcategoria
	 *  
	 * @author Ana Maria
	 * @date 10/02/2009
	 * 
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public void removerImovelSubcategoria(Integer idImovel)
		throws ErroRepositorioException{
		String remocao = null;

		Session session = HibernateUtil.getSession();

		try {
			remocao = "delete ImovelSubcategoria"
					+ " where imov_id = :idImovel";

			session.createQuery(remocao).setInteger(
					"idImovel", idImovel).executeUpdate();			
			

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}
	
	public void removerImovelRamoAtividade(Integer idImovel)
		throws ErroRepositorioException{
	String remocao = null;

	Session session = HibernateUtil.getSession();

	try {

		remocao = "delete ImovelRamoAtividade"
				+ " where imov_id = :idImovel";

		session.createQuery(remocao).setInteger(
				"idImovel", idImovel).executeUpdate();			
		

	} catch (HibernateException e) {
		// levanta a exceção para a próxima camada
		throw new ErroRepositorioException(e, "Erro no Hibernate");
	} finally {
		// fecha a sessão
		HibernateUtil.closeSession(session);
	}
}
	/**
	 * [UC0011] Inserir Imóvel
	 * 
	 * @author Victor Cisneiros
	 * @date 18/02/2009
	 */
    public Integer pesquisarMaiorNumeroLoteDaQuadra(Integer idQuadra) throws ErroRepositorioException {
    	
    	Integer retorno = 0;
    	
        String consulta;
        Session session = HibernateUtil.getSession();
        
        try {
            consulta = 
            	"SELECT max(imov_nnlote) as max FROM cadastro.imovel WHERE qdra_id = :idQuadra";
        
            Integer resultado = (Integer) session.createSQLQuery(consulta)
            	.addScalar("max", Hibernate.INTEGER)
            	.setInteger("idQuadra", idQuadra)
            	.uniqueResult();
            
            if (resultado != null) {
            	retorno = resultado;
            }
            
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }
    	
    	return retorno;
    }
    
    /**
	 * Consultar os dodos cliente usuário do Imovel 
	 * 
	 * dados[0] = Nome
	 * dados[1] = Cpf
	 * dados[2] = Cnpj
	 * 
	 * @author Arthur Carvalho
	 * @date 12/03/2009
	 * 
	 * @param idImovel
	 * @return Collection
	 * @throws ControladorException
	 */

	public Object[] consultarDadosClienteUsuarioImovel(
			String idImovel) throws ErroRepositorioException {

		Object[] retorno = null;
		String consulta = null;

		Session session = HibernateUtil.getSession();
		try {

			consulta = "SELECT cliente.nome, cliente.cpf, cliente.cnpj "// 0, 1, 2
					+ "from ClienteImovel clienteImovel "
					+ "left join clienteImovel.cliente cliente "
					+ "where clienteImovel.imovel.id = :idImovel and clienteImovel.imovel.indicadorExclusao != 1 and "
					+ "clienteImovel.clienteRelacaoTipo.id = :idClienteUsuario and clienteImovel.dataFimRelacao is null ";

			retorno = ( Object[] ) session.createQuery(consulta).setInteger("idImovel",
					new Integer(idImovel)).setShort("idClienteUsuario",
					ClienteRelacaoTipo.USUARIO.shortValue()).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
    
	/**
	 * 
	 * Atualiza a situação de cobrança e a situação do tipo de cobrança
	 * 
	 * @author Sávio Luiz, Mariana Victor
	 * @date 18/02/2009, 03/05/2011
	 */
	public void atualizarSituacaoCobrancaETipoIdsImoveis(Integer idSituacaoCobranca,Integer idSituacaoCobrancaTipo, Collection<Integer> idsImovel)
		throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();
		
		try {
			
		if(idsImovel != null && idsImovel.size() > 1000){
			Collection<Integer> idsImovelMenor = new ArrayList();
			for (Integer idImovel :idsImovel){
				idsImovelMenor.add(idImovel);
				if(idsImovelMenor.size() >= 1000){
					consulta = "update gcom.cadastro.imovel.Imovel set "

						+ "cbst_id = :idSituacaoCobranca,  cbsp_id = :idSituacaoCobrancaTipo, imov_tmultimaalteracao = :ultimaAlteracao " 
						
						+ "where imov_id IN (:ids) ";

				   session.createQuery(consulta).
				   setInteger("idSituacaoCobranca",idSituacaoCobranca).
				   setInteger("idSituacaoCobrancaTipo",idSituacaoCobrancaTipo).
				   setTimestamp("ultimaAlteracao",new Date()).
				   setParameterList("ids",idsImovelMenor).
				   executeUpdate();
				
				idsImovelMenor.clear();
				}
			}
			
			if(idsImovelMenor != null && !idsImovelMenor.isEmpty()){
				consulta = "update gcom.cadastro.imovel.Imovel set "

					+ "cbst_id = :idSituacaoCobranca, cbsp_id = :idSituacaoCobrancaTipo, imov_tmultimaalteracao = :ultimaAlteracao " 
					
					+ "where imov_id IN (:ids) ";

			   session.createQuery(consulta).
			   setInteger("idSituacaoCobranca",idSituacaoCobranca).
			   setInteger("idSituacaoCobrancaTipo",idSituacaoCobrancaTipo).
			   setTimestamp("ultimaAlteracao",new Date()).
			   setParameterList("ids",idsImovelMenor).
			   executeUpdate();
			}
			
			
		}else{
			if(idsImovel != null){
				consulta = "update gcom.cadastro.imovel.Imovel set "
	
					+ "cbst_id = :idSituacaoCobranca, cbsp_id = :idSituacaoCobrancaTipo, imov_tmultimaalteracao = :ultimaAlteracao " 
					
					+ "where imov_id IN (:ids) ";
	
			session.createQuery(consulta).
			   setInteger("idSituacaoCobranca",idSituacaoCobranca).
			   setInteger("idSituacaoCobrancaTipo",idSituacaoCobrancaTipo).
			   setTimestamp("ultimaAlteracao",new Date()).
			   setParameterList("ids",idsImovel).
			   executeUpdate();
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
	 * 
	 * 
	 * Gerar Relatório de Imóvel Outros Critérios
	 * 
	 * 
	 * 
	 * @author Rafael Corrêa,Rafael Santos
	 * 
	 * @date 24/07/2006,01/08/2006
	 * 
	 * 
	 * 
	 */

	public Integer gerarRelatorioCadastroConsumidoresInscricaoCount(String idImovelCondominio, 
											String idImovelPrincipal,
											String idSituacaoLigacaoAgua, 
											String consumoMinimoInicialAgua,
											String consumoMinimoFinalAgua, 
											String idSituacaoLigacaoEsgoto,
											String consumoMinimoInicialEsgoto, 
											String consumoMinimoFinalEsgoto,
											String intervaloValorPercentualEsgotoInicial,
											String intervaloValorPercentualEsgotoFinal,
											String intervaloMediaMinimaImovelInicial,
											String intervaloMediaMinimaImovelFinal,
											String intervaloMediaMinimaHidrometroInicial,
											String intervaloMediaMinimaHidrometroFinal, 
											String idImovelPerfil,
											String idPocoTipo, 
											String idFaturamentoSituacaoTipo,
											String idCobrancaSituacaoTipo, 
											String idSituacaoEspecialCobranca,
											String idEloAnormalidade, 
											String areaConstruidaInicial,
											String areaConstruidaFinal, 
											String idCadastroOcorrencia,
											String idConsumoTarifa, 
											String idGerenciaRegional,
											String idLocalidadeInicial, 
											String idLocalidadeFinal,
											String setorComercialInicial, 
											String setorComercialFinal,
											String quadraInicial, 
											String quadraFinal, 
											String loteOrigem,
											String loteDestno, 
											String cep, 
											String logradouro, 
											String bairro,
											String municipio, 
											String idTipoMedicao, 
											String indicadorMedicao,
											String idSubCategoria, 
											String idCategoria,
											String quantidadeEconomiasInicial, 
											String quantidadeEconomiasFinal,
											String diaVencimento, 
											String idCliente, 
											String idClienteTipo,
											String idClienteRelacaoTipo, 
											String numeroPontosInicial,
											String numeroPontosFinal, 
											String numeroMoradoresInicial,
											String numeroMoradoresFinal, 
											String idAreaConstruidaFaixa,
											String idUnidadeNegocio, 
											String cdRotaInicial,
											String cdRotaFinal, 
											String sequencialRotaInicial,
											String sequencialRotaFinal,
											String[] pocoTipoList) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = consulta

					+ "select count(distinct "
					+ "imovel.imov_id ) as qtde "
					+ "from cadastro.imovel_subcategoria imovelSubcategoria "
					+ "inner join cadastro.imovel on imovelSubcategoria.imov_id = cadastro.imovel.imov_id "
					+ "inner join cadastro.localidade on imovel.loca_id = cadastro.localidade.loca_id "
					+ "inner join cadastro.gerencia_regional on cadastro.localidade.greg_id = cadastro.gerencia_regional.greg_id "
					+ "inner join cadastro.setor_comercial on cadastro.imovel.stcm_id = cadastro.setor_comercial.stcm_id "
					// Logradouro Bairro
					+ "left join cadastro.logradouro_bairro on cadastro.imovel.lgbr_id = cadastro.logradouro_bairro.lgbr_id "
					+ "left join cadastro.bairro on cadastro.logradouro_bairro.bair_id = cadastro.bairro.bair_id "
					+ "left join cadastro.municipio on cadastro.bairro.muni_id = cadastro.municipio.muni_id "
					+ "inner join cadastro.quadra on cadastro.imovel.qdra_id = cadastro.quadra.qdra_id "
					+ "inner join micromedicao.rota on cadastro.quadra.rota_id = micromedicao.rota.rota_id "
					// Logradouro Cep
					+ "left join cadastro.logradouro_cep on cadastro.imovel.lgcp_id = cadastro.logradouro_cep.lgcp_id "
					+ "left join cadastro.cep on cadastro.logradouro_cep.cep_id = cadastro.cep.cep_id "
					+ "left join cadastro.logradouro on cadastro.logradouro_cep.logr_id = cadastro.logradouro.logr_id "
					// AGUA
					+ "left join atendimentopublico.ligacao_agua_situacao on cadastro.imovel.last_id = atendimentopublico.ligacao_agua_situacao.last_id "
					+ "left join atendimentopublico.ligacao_agua on cadastro.imovel.imov_id = atendimentopublico.ligacao_agua.lagu_id "
					// ESGOTO
					+ "left join atendimentopublico.ligacao_esgoto_situacao on cadastro.imovel.lest_id = atendimentopublico.ligacao_esgoto_situacao.lest_id "
					+ "left join atendimentopublico.ligacao_esgoto on cadastro.imovel.imov_id = atendimentopublico.ligacao_esgoto.lesg_id "
					+ "left join cadastro.imovel_perfil on cadastro.imovel.iper_id = cadastro.imovel_perfil.iper_id "
					+ "left join cadastro.poco_tipo on cadastro.imovel.poco_id = cadastro.poco_tipo.poco_id "
					+ "left join cadastro.area_construida_faixa on cadastro.imovel.acon_id = area_construida_faixa.acon_id "
					// Cliente Usuario
					+ "left outer join cadastro.cliente_imovel as cliente_imovel_usuario on cadastro.imovel.imov_id = cliente_imovel_usuario.imov_id  "
					+ " and (cliente_imovel_usuario.crtp_id=2 and cliente_imovel_usuario.clim_dtrelacaofim is null) "
					+ "left outer join cadastro.cliente as cliente_usuario on cliente_imovel_usuario.clie_id = cliente_usuario.clie_id "
					+ "left outer join cadastro.cliente_fone as cliente_fone on cliente_usuario.clie_id = cliente_fone.clie_id and (cliente_fone.cfon_icfonepadrao = 1) "
					// Cliente Resposanvel
					+ "left outer join cadastro.cliente_imovel as cliente_imovel_responsavel on cadastro.imovel.imov_id = cliente_imovel_responsavel.imov_id "
					+ " and (cliente_imovel_responsavel.crtp_id=3 and cliente_imovel_responsavel.clim_dtrelacaofim is null) "
					+ "left outer join cadastro.cliente as cliente_responsavel on cliente_imovel_responsavel.clie_id = cliente_responsavel.clie_id "
					// AGUA
					+ "left join micromedicao.hidrometro_inst_hist on atendimentopublico.ligacao_agua.hidi_id =  micromedicao.hidrometro_inst_hist.hidi_id "
					// ESGOTO
					+ "left join micromedicao.hidrometro_inst_hist hidrometro_instalacao_historic on cadastro.imovel.hidi_id =  hidrometro_instalacao_historic.hidi_id "
					// Relacionamento para o Relatorio de Imovel
					+ "left join cadastro.logradouro_titulo on cadastro.logradouro.lgtt_id = cadastro.logradouro_titulo.lgtt_id "
					+ "left join cadastro.logradouro_tipo on cadastro.logradouro.lgtp_id = cadastro.logradouro_tipo.lgtp_id "
					+ "left join cadastro.endereco_referencia on cadastro.imovel.edrf_id = cadastro.endereco_referencia.edrf_id "
					+ "left join cadastro.unidade_federacao on cadastro.municipio.unfe_id = cadastro.unidade_federacao.unfe_id "
					+ "left join cadastro.reservatorio_volume_fx on cadastro.imovel.resv_idreservatoriosuperior = cadastro.reservatorio_volume_fx.resv_id "
					+ "left join cadastro.reservatorio_volume_fx reservatorio_volume_fx_infe on cadastro.imovel.resv_idreservatorioinferior = reservatorio_volume_fx_infe.resv_id "
					+ "left join cadastro.piscina_volume_faixa on cadastro.imovel.pisc_id = cadastro.piscina_volume_faixa.pisc_id "
					+ "left join cadastro.pavimento_calcada on cadastro.imovel.pcal_id = cadastro.pavimento_calcada.pcal_id "
					+ "left join cadastro.pavimento_rua on cadastro.imovel.prua_id = cadastro.pavimento_rua.prua_id "
					+ "left join cadastro.despejo on cadastro.imovel.depj_id = cadastro.despejo.depj_id "
					// AGUA
					+ "left join atendimentopublico.ligacao_agua_diametro on atendimentopublico.ligacao_agua.lagd_id = atendimentopublico.ligacao_agua_diametro.lagd_id "
					+ "left join atendimentopublico.ligacao_agua_material on atendimentopublico.ligacao_agua.lagm_id = atendimentopublico.ligacao_agua_material.lagm_id "
					// ESGOTO
					+ "left join atendimentopublico.ligacao_esgoto_diametro on atendimentopublico.ligacao_esgoto.legd_id = atendimentopublico.ligacao_esgoto_diametro.legd_id "
					+ "left join atendimentopublico.ligacao_esgoto_material on atendimentopublico.ligacao_esgoto.legm_id = atendimentopublico.ligacao_esgoto_material.legm_id "
					// AGUA
					+ "left join micromedicao.hidrometro on micromedicao.hidrometro_inst_hist.hidr_id = micromedicao.hidrometro.hidr_id "
					+ "left join micromedicao.hidrometro_capacidade on micromedicao.hidrometro.hicp_id = micromedicao.hidrometro_capacidade.hicp_id "
					+ "left join micromedicao.hidrometro_marca on micromedicao.hidrometro.himc_id = micromedicao.hidrometro_marca.himc_id "
					+ "left join micromedicao.hidrometro_diametro on micromedicao.hidrometro.hidm_id = micromedicao.hidrometro_diametro.hidm_id "
					+ "left join micromedicao.hidrometro_tipo on micromedicao.hidrometro.hitp_id = micromedicao.hidrometro_tipo.hitp_id "
					+ "left join micromedicao.hidrometro_local_inst on micromedicao.hidrometro_inst_hist.hili_id = micromedicao.hidrometro_local_inst.hili_id "
					+ "left join micromedicao.hidrometro_protecao on micromedicao.hidrometro_inst_hist.hipr_id = micromedicao.hidrometro_protecao.hipr_id "
					// ESGOTO
					+ "left join micromedicao.hidrometro as hidrometro_esgoto on hidrometro_instalacao_historic.hidr_id = hidrometro_esgoto.hidr_id "
					+ "left join micromedicao.hidrometro_capacidade as hidrometro_capacidade_esgoto on hidrometro_esgoto.hicp_id = hidrometro_capacidade_esgoto.hicp_id "
					+ "left join micromedicao.hidrometro_marca as hidrometro_marca_esgoto on hidrometro_esgoto.himc_id = hidrometro_marca_esgoto.himc_id "
					+ "left join micromedicao.hidrometro_diametro as hidrometro_diametro_esgoto on hidrometro_esgoto.hidm_id = hidrometro_diametro_esgoto.hidm_id "
					+ "left join micromedicao.hidrometro_tipo as hidrometro_tipo_esgoto on hidrometro_esgoto.hitp_id = hidrometro_tipo_esgoto.hitp_id "
					+ "left join micromedicao.hidrometro_local_inst as hidrometro_local_inst_es on hidrometro_instalacao_historic.hili_id = hidrometro_local_inst.hili_id "
					+ "left join micromedicao.hidrometro_protecao as hidrometro_protecao_esgoto on hidrometro_instalacao_historic.hipr_id = hidrometro_protecao_esgoto.hipr_id ";

			consulta = consulta	+ montarInnerJoinFiltrarImoveisOutrosCriterios(
									intervaloMediaMinimaImovelInicial,
									intervaloMediaMinimaImovelFinal,
									intervaloMediaMinimaHidrometroInicial,
									intervaloMediaMinimaHidrometroFinal,
									idFaturamentoSituacaoTipo, idCobrancaSituacaoTipo,
									idSituacaoEspecialCobranca, idEloAnormalidade,
									idCadastroOcorrencia, idConsumoTarifa,
									idTipoMedicao, indicadorMedicao, idSubCategoria,
									idCategoria, idCliente, idClienteTipo,
									idClienteRelacaoTipo,
									ConstantesSistema.GERAR_RELATORIO_IMOVEL, 
									null
									);

			consulta = consulta + montarCondicaoSQLWhereFiltrarImovelOutrosCriterio(
										idImovelCondominio, idImovelPrincipal,
										idSituacaoLigacaoAgua, consumoMinimoInicialAgua,
										consumoMinimoFinalAgua, idSituacaoLigacaoEsgoto,
										consumoMinimoInicialEsgoto,
										consumoMinimoFinalEsgoto,
										intervaloValorPercentualEsgotoInicial,
										intervaloValorPercentualEsgotoFinal,
										intervaloMediaMinimaImovelInicial,
										intervaloMediaMinimaImovelFinal,
										intervaloMediaMinimaHidrometroInicial,
										intervaloMediaMinimaHidrometroFinal,
										idImovelPerfil, idPocoTipo,
										idFaturamentoSituacaoTipo, idCobrancaSituacaoTipo,
										idSituacaoEspecialCobranca, idEloAnormalidade,
										areaConstruidaInicial, areaConstruidaFinal,
										idCadastroOcorrencia, idConsumoTarifa,
										idGerenciaRegional, idLocalidadeInicial,
										idLocalidadeFinal, setorComercialInicial,
										setorComercialFinal, quadraInicial, quadraFinal,
										loteOrigem, loteDestno, cep, logradouro, bairro,
										municipio, idTipoMedicao, indicadorMedicao,
										idSubCategoria, idCategoria,
										quantidadeEconomiasInicial,
										quantidadeEconomiasFinal, diaVencimento, idCliente,
										idClienteTipo, idClienteRelacaoTipo,
										numeroPontosInicial, numeroPontosFinal,
										numeroMoradoresInicial, numeroMoradoresFinal,
										idAreaConstruidaFaixa, idUnidadeNegocio,
										ConstantesSistema.GERAR_RELATORIO_IMOVEL, 
										cdRotaInicial,
										cdRotaFinal, sequencialRotaInicial,
										sequencialRotaFinal,
										pocoTipoList, null , null);

			SQLQuery query = session.createSQLQuery(
								consulta.substring(0, (consulta.length() - 5))
								.concat(" ")
								);

			informarDadosQueryFiltrarImovelOutrosCriterio(
									query,
									idImovelCondominio, 
									idImovelPrincipal,
									idSituacaoLigacaoAgua, 
									consumoMinimoInicialAgua,
									consumoMinimoFinalAgua, 
									idSituacaoLigacaoEsgoto,
									consumoMinimoInicialEsgoto, 
									consumoMinimoFinalEsgoto,
									intervaloValorPercentualEsgotoInicial,
									intervaloValorPercentualEsgotoFinal,
									intervaloMediaMinimaImovelInicial,
									intervaloMediaMinimaImovelFinal,
									intervaloMediaMinimaHidrometroInicial,
									intervaloMediaMinimaHidrometroFinal, 
									idImovelPerfil,
									idPocoTipo, 
									idFaturamentoSituacaoTipo,
									idCobrancaSituacaoTipo, 
									idSituacaoEspecialCobranca,
									idEloAnormalidade, 
									areaConstruidaInicial,
									areaConstruidaFinal, 
									idCadastroOcorrencia, 
									idConsumoTarifa,
									idGerenciaRegional, 
									idLocalidadeInicial, 
									idLocalidadeFinal,
									setorComercialInicial, 
									setorComercialFinal, 
									quadraInicial,
									quadraFinal, 
									loteOrigem, 
									loteDestno, 
									cep, 
									logradouro,
									bairro, 
									municipio, 
									idTipoMedicao, 
									indicadorMedicao,
									idSubCategoria, 
									idCategoria, 
									quantidadeEconomiasInicial,
									quantidadeEconomiasFinal, 
									diaVencimento, 
									idCliente,
									idClienteTipo, 
									idClienteRelacaoTipo, 
									numeroPontosInicial,
									numeroPontosFinal, 
									numeroMoradoresInicial,
									numeroMoradoresFinal, 
									idAreaConstruidaFaixa,
									idUnidadeNegocio, 
									cdRotaInicial, 
									cdRotaFinal, 
									sequencialRotaInicial, 
									sequencialRotaFinal,
									pocoTipoList, null);

				retorno = (Integer) query
								.addScalar("qtde", Hibernate.INTEGER)
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
	 * 
	 * 
	 * Gerar Relatório de Imóvel Outros Critérios
	 * 
	 * 
	 * 
	 * @author Rafael Corrêa,Rafael Santos
	 * 
	 * @date 24/07/2006,01/08/2006
	 * 
	 * 
	 * 
	 */

	public Integer gerarRelatorioImovelEnderecoOutrosCriteriosCount(
							String idImovelCondominio, 
							String idImovelPrincipal,
							String idSituacaoLigacaoAgua, 
							String consumoMinimoInicialAgua,
							String consumoMinimoFinalAgua, 
							String idSituacaoLigacaoEsgoto,
							String consumoMinimoInicialEsgoto, 
							String consumoMinimoFinalEsgoto,
							String intervaloValorPercentualEsgotoInicial,
							String intervaloValorPercentualEsgotoFinal,
							String intervaloMediaMinimaImovelInicial,
							String intervaloMediaMinimaImovelFinal,
							String intervaloMediaMinimaHidrometroInicial,
							String intervaloMediaMinimaHidrometroFinal, 
							String idImovelPerfil,
							String idPocoTipo, 
							String idFaturamentoSituacaoTipo,
							String idCobrancaSituacaoTipo, 
							String idSituacaoEspecialCobranca,
							String idEloAnormalidade, 
							String areaConstruidaInicial,
							String areaConstruidaFinal, 
							String idCadastroOcorrencia,
							String idConsumoTarifa, 
							String idGerenciaRegional,
							String idLocalidadeInicial, 
							String idLocalidadeFinal,
							String setorComercialInicial, 
							String setorComercialFinal,
							String quadraInicial, 
							String quadraFinal, 
							String loteOrigem,
							String loteDestno, 
							String cep, 
							String logradouro, 
							String bairro,
							String municipio, 
							String idTipoMedicao, 
							String indicadorMedicao,
							String idSubCategoria, 
							String idCategoria,
							String quantidadeEconomiasInicial, 
							String quantidadeEconomiasFinal,
							String diaVencimento, 
							String idCliente, 
							String idClienteTipo,
							String idClienteRelacaoTipo, 
							String numeroPontosInicial,
							String numeroPontosFinal, 
							String numeroMoradoresInicial,
							String numeroMoradoresFinal, 
							String idAreaConstruidaFaixa,
							String idUnidadeNegocio,
							String rotaInicial, 
							String rotaFinal,
							String ordenacaoRelatorio,
							String seqRotaInicial, 
							String seqRotaFinal) throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {

			consulta = consulta	+ "select count(distinct "
								+ "imovel.imov_id )as qtde "
					+ "from cadastro.imovel_subcategoria imovelSubcategoria "
					+ "inner join cadastro.imovel on imovelSubcategoria.imov_id = cadastro.imovel.imov_id "
					+ "inner join cadastro.localidade on imovel.loca_id = cadastro.localidade.loca_id "
					+ "inner join cadastro.gerencia_regional on cadastro.localidade.greg_id = cadastro.gerencia_regional.greg_id "
					+ "inner join cadastro.setor_comercial on cadastro.imovel.stcm_id = cadastro.setor_comercial.stcm_id "
					// Logradouro Bairro
					+ "left join cadastro.logradouro_bairro on cadastro.imovel.lgbr_id = cadastro.logradouro_bairro.lgbr_id "
					+ "left join cadastro.bairro on cadastro.logradouro_bairro.bair_id = cadastro.bairro.bair_id "
					+ "left join cadastro.municipio on cadastro.bairro.muni_id = cadastro.municipio.muni_id "
					+ "inner join cadastro.quadra on cadastro.imovel.qdra_id = cadastro.quadra.qdra_id "
					+ "inner join micromedicao.rota on cadastro.quadra.rota_id = micromedicao.rota.rota_id "
					// Logradouro Cep
					+ "left join cadastro.logradouro_cep on cadastro.imovel.lgcp_id = cadastro.logradouro_cep.lgcp_id "
					+ "left join cadastro.cep on cadastro.logradouro_cep.cep_id = cadastro.cep.cep_id "
					+ "left join cadastro.logradouro on cadastro.logradouro_cep.logr_id = cadastro.logradouro.logr_id "
					// AGUA
					+ "left join atendimentopublico.ligacao_agua_situacao on cadastro.imovel.last_id = atendimentopublico.ligacao_agua_situacao.last_id "
					+ "left join atendimentopublico.ligacao_agua on cadastro.imovel.imov_id = atendimentopublico.ligacao_agua.lagu_id "
					// ESGOTO
					+ "left join atendimentopublico.ligacao_esgoto_situacao on cadastro.imovel.lest_id = atendimentopublico.ligacao_esgoto_situacao.lest_id "
					+ "left join atendimentopublico.ligacao_esgoto on cadastro.imovel.imov_id = atendimentopublico.ligacao_esgoto.lesg_id "
					+ "left join cadastro.imovel_perfil on cadastro.imovel.iper_id = cadastro.imovel_perfil.iper_id "
					+ "left join cadastro.poco_tipo on cadastro.imovel.poco_id = cadastro.poco_tipo.poco_id "
					+ "left join cadastro.area_construida_faixa on cadastro.imovel.acon_id = area_construida_faixa.acon_id "
					// Cliente Usuario
					+ "left outer join cadastro.cliente_imovel as cliente_imovel_usuario on cadastro.imovel.imov_id = cliente_imovel_usuario.imov_id  "
					+ " and (cliente_imovel_usuario.crtp_id=2 and cliente_imovel_usuario.clim_dtrelacaofim is null) "
					+ "left outer join cadastro.cliente as cliente_usuario on cliente_imovel_usuario.clie_id = cliente_usuario.clie_id "
					// Cliente Resposanvel
					+ "left outer join cadastro.cliente_imovel as cliente_imovel_responsavel on cadastro.imovel.imov_id = cliente_imovel_responsavel.imov_id "
					+ " and (cliente_imovel_responsavel.crtp_id=3 and cliente_imovel_responsavel.clim_dtrelacaofim is null) "
					+ "left outer join cadastro.cliente as cliente_responsavel on cliente_imovel_responsavel.clie_id = cliente_responsavel.clie_id "
					// AGUA
					+ "left join micromedicao.hidrometro_inst_hist on atendimentopublico.ligacao_agua.hidi_id =  micromedicao.hidrometro_inst_hist.hidi_id "
					// ESGOTO
					+ "left join micromedicao.hidrometro_inst_hist hidrometro_instalacao_historic on cadastro.imovel.hidi_id =  hidrometro_instalacao_historic.hidi_id "
					// Relacionamento para o Relatorio de Imovel
					+ "left join cadastro.logradouro_titulo on cadastro.logradouro.lgtt_id = cadastro.logradouro_titulo.lgtt_id "
					+ "left join cadastro.logradouro_tipo on cadastro.logradouro.lgtp_id = cadastro.logradouro_tipo.lgtp_id "
					+ "left join cadastro.endereco_referencia on cadastro.imovel.edrf_id = cadastro.endereco_referencia.edrf_id "
					+ "left join cadastro.unidade_federacao on cadastro.municipio.unfe_id = cadastro.unidade_federacao.unfe_id "
					+ "left join cadastro.reservatorio_volume_fx on cadastro.imovel.resv_idreservatoriosuperior = cadastro.reservatorio_volume_fx.resv_id "
					+ "left join cadastro.reservatorio_volume_fx reservatorio_volume_fx_infe on cadastro.imovel.resv_idreservatorioinferior = reservatorio_volume_fx_infe.resv_id "
					+ "left join cadastro.piscina_volume_faixa on cadastro.imovel.pisc_id = cadastro.piscina_volume_faixa.pisc_id "
					+ "left join cadastro.pavimento_calcada on cadastro.imovel.pcal_id = cadastro.pavimento_calcada.pcal_id "
					+ "left join cadastro.pavimento_rua on cadastro.imovel.prua_id = cadastro.pavimento_rua.prua_id "
					+ "left join cadastro.despejo on cadastro.imovel.depj_id = cadastro.despejo.depj_id "
					// AGUA
					+ "left join atendimentopublico.ligacao_agua_diametro on atendimentopublico.ligacao_agua.lagd_id = atendimentopublico.ligacao_agua_diametro.lagd_id "
					+ "left join atendimentopublico.ligacao_agua_material on atendimentopublico.ligacao_agua.lagm_id = atendimentopublico.ligacao_agua_material.lagm_id "
					// ESGOTO
					+ "left join atendimentopublico.ligacao_esgoto_diametro on atendimentopublico.ligacao_esgoto.legd_id = atendimentopublico.ligacao_esgoto_diametro.legd_id "
					+ "left join atendimentopublico.ligacao_esgoto_material on atendimentopublico.ligacao_esgoto.legm_id = atendimentopublico.ligacao_esgoto_material.legm_id "
					// AGUA
					+ "left join micromedicao.hidrometro on micromedicao.hidrometro_inst_hist.hidr_id = micromedicao.hidrometro.hidr_id "
					+ "left join micromedicao.hidrometro_capacidade on micromedicao.hidrometro.hicp_id = micromedicao.hidrometro_capacidade.hicp_id "
					+ "left join micromedicao.hidrometro_marca on micromedicao.hidrometro.himc_id = micromedicao.hidrometro_marca.himc_id "
					+ "left join micromedicao.hidrometro_diametro on micromedicao.hidrometro.hidm_id = micromedicao.hidrometro_diametro.hidm_id "
					+ "left join micromedicao.hidrometro_tipo on micromedicao.hidrometro.hitp_id = micromedicao.hidrometro_tipo.hitp_id "
					+ "left join micromedicao.hidrometro_local_inst on micromedicao.hidrometro_inst_hist.hili_id = micromedicao.hidrometro_local_inst.hili_id "
					+ "left join micromedicao.hidrometro_protecao on micromedicao.hidrometro_inst_hist.hipr_id = micromedicao.hidrometro_protecao.hipr_id "
					// ESGOTO
					+ "left join micromedicao.hidrometro as hidrometro_esgoto on hidrometro_instalacao_historic.hidr_id = hidrometro_esgoto.hidr_id "
					+ "left join micromedicao.hidrometro_capacidade as hidrometro_capacidade_esgoto on hidrometro_esgoto.hicp_id = hidrometro_capacidade_esgoto.hicp_id "
					+ "left join micromedicao.hidrometro_marca as hidrometro_marca_esgoto on hidrometro_esgoto.himc_id = hidrometro_marca_esgoto.himc_id "
					+ "left join micromedicao.hidrometro_diametro as hidrometro_diametro_esgoto on hidrometro_esgoto.hidm_id = hidrometro_diametro_esgoto.hidm_id "
					+ "left join micromedicao.hidrometro_tipo as hidrometro_tipo_esgoto on hidrometro_esgoto.hitp_id = hidrometro_tipo_esgoto.hitp_id "
					+ "left join micromedicao.hidrometro_local_inst as hidrometro_local_inst_es on hidrometro_instalacao_historic.hili_id = hidrometro_local_inst.hili_id "
					+ "left join micromedicao.hidrometro_protecao as hidrometro_protecao_esgoto on hidrometro_instalacao_historic.hipr_id = hidrometro_protecao_esgoto.hipr_id ";

			consulta = consulta + montarInnerJoinFiltrarImoveisOutrosCriterios(
												intervaloMediaMinimaImovelInicial,
												intervaloMediaMinimaImovelFinal,
												intervaloMediaMinimaHidrometroInicial,
												intervaloMediaMinimaHidrometroFinal,
												idFaturamentoSituacaoTipo, idCobrancaSituacaoTipo,
												idSituacaoEspecialCobranca, idEloAnormalidade,
												idCadastroOcorrencia, idConsumoTarifa,
												idTipoMedicao, indicadorMedicao, idSubCategoria,
												idCategoria, idCliente, idClienteTipo,
												idClienteRelacaoTipo,
												ConstantesSistema.GERAR_RELATORIO_IMOVEL, 
												null);
			
			consulta = consulta + montarCondicaoSQLWhereFiltrarImovelOutrosCriterio(
										idImovelCondominio, 
										idImovelPrincipal,
										idSituacaoLigacaoAgua, 
										consumoMinimoInicialAgua,
										consumoMinimoFinalAgua, 
										idSituacaoLigacaoEsgoto,
										consumoMinimoInicialEsgoto,
										consumoMinimoFinalEsgoto,
										intervaloValorPercentualEsgotoInicial,
										intervaloValorPercentualEsgotoFinal,
										intervaloMediaMinimaImovelInicial,
										intervaloMediaMinimaImovelFinal,
										intervaloMediaMinimaHidrometroInicial,
										intervaloMediaMinimaHidrometroFinal,
										idImovelPerfil, 
										idPocoTipo,
										idFaturamentoSituacaoTipo, 
										idCobrancaSituacaoTipo,
										idSituacaoEspecialCobranca, 
										idEloAnormalidade,
										areaConstruidaInicial, 
										areaConstruidaFinal,
										idCadastroOcorrencia, 
										idConsumoTarifa,
										idGerenciaRegional, 
										idLocalidadeInicial,
										idLocalidadeFinal,
										setorComercialInicial,
										setorComercialFinal, 
										quadraInicial, 
										quadraFinal,
										loteOrigem, 
										loteDestno, 
										cep, 
										logradouro, 
										bairro,
										municipio, 
										idTipoMedicao, 
										indicadorMedicao,
										idSubCategoria, 
										idCategoria,
										quantidadeEconomiasInicial,
										quantidadeEconomiasFinal, 
										diaVencimento, 
										idCliente,
										idClienteTipo, 
										idClienteRelacaoTipo,
										numeroPontosInicial, 
										numeroPontosFinal,
										numeroMoradoresInicial, 
										numeroMoradoresFinal,
										idAreaConstruidaFaixa, 
										idUnidadeNegocio,
										ConstantesSistema.GERAR_RELATORIO_IMOVEL,
										rotaInicial,
										rotaFinal,
										seqRotaInicial, 
										seqRotaFinal,
										null, null , null);

			SQLQuery query = session.createSQLQuery(consulta.substring(0,
					(consulta.length() - 5)).concat(" "));
					
			informarDadosQueryFiltrarImovelOutrosCriterio(query,
					idImovelCondominio, 
					idImovelPrincipal,
					idSituacaoLigacaoAgua, 
					consumoMinimoInicialAgua,
					consumoMinimoFinalAgua, 
					idSituacaoLigacaoEsgoto,
					consumoMinimoInicialEsgoto, 
					consumoMinimoFinalEsgoto,
					intervaloValorPercentualEsgotoInicial,
					intervaloValorPercentualEsgotoFinal,
					intervaloMediaMinimaImovelInicial,
					intervaloMediaMinimaImovelFinal,
					intervaloMediaMinimaHidrometroInicial,
					intervaloMediaMinimaHidrometroFinal, 
					idImovelPerfil,
					idPocoTipo, 
					idFaturamentoSituacaoTipo,
					idCobrancaSituacaoTipo, 
					idSituacaoEspecialCobranca,
					idEloAnormalidade, 
					areaConstruidaInicial,
					areaConstruidaFinal, 
					idCadastroOcorrencia, 
					idConsumoTarifa,
					idGerenciaRegional, 
					idLocalidadeInicial, 
					idLocalidadeFinal,
					setorComercialInicial, 
					setorComercialFinal, 
					quadraInicial,
					quadraFinal, 
					loteOrigem, 
					loteDestno, 
					cep, 
					logradouro,
					bairro, 
					municipio, 
					idTipoMedicao, 
					indicadorMedicao,
					idSubCategoria, 
					idCategoria, 
					quantidadeEconomiasInicial,
					quantidadeEconomiasFinal, 
					diaVencimento, 
					idCliente,
					idClienteTipo, 
					idClienteRelacaoTipo, 
					numeroPontosInicial,
					numeroPontosFinal, 
					numeroMoradoresInicial,
					numeroMoradoresFinal, 
					idAreaConstruidaFaixa,
					idUnidadeNegocio, rotaInicial, rotaFinal, seqRotaInicial, seqRotaFinal, null, null);
			
			retorno = (Integer) query
					.addScalar("qtde", Hibernate.INTEGER)
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
	 * Verificar se o imóvel perfil está bloqueado
	 * 
	 * @author Ana Maria
	 * @date 22/04/2009
	 * 
	 * @return Boolean
	 * @throws ErroRepositorioException
	 */
	public Boolean verificarImovelPerfilBloqueado(Integer idImovel) 
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
	
		Boolean retorno = false;
		String consulta = null;
	
		try {
			
			consulta = 	" select imov.imov_id as id "
					  + " from cadastro.imovel imov"
					  + " inner join cadastro.imovel_perfil iper on(imov.iper_id = iper.iper_id)"
					  + " where imov.imov_id = :idImovel and iper_icbloquearetificacao = 1 ";
					
			Integer resultado = (Integer)session.createSQLQuery(consulta).addScalar("id" , Hibernate.INTEGER)
					.setInteger("idImovel", idImovel).uniqueResult();		    
			
			if (resultado != null && !resultado.equals("")) {
				retorno = true;
			} else {
				retorno = false;
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
	 * Verificar se a ultima alteracao do imóvel 
	 * 
	 * @author Rômulo Aurélio
	 * @date 27/05/2009
	 * 
	 * @return Date
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarUltimaAlteracaoImovel(Integer idImovel) 
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		
		Date retorno = null;
		
		String consulta = null;
	
		try {
			
			consulta = 	" select imov.imov_tmultimaalteracao as ultimaAlteracao "
					  + " from cadastro.imovel imov"
					  + " where imov.imov_id = :idImovel ";
					
			retorno = (Date)session.createSQLQuery(consulta)
					.addScalar("ultimaAlteracao" , Hibernate.DATE)
					.setInteger("idImovel", idImovel).uniqueResult();		    
			
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
	 * Verifica se existe imovel com número do Medidor de Energia informado
	 * 
	 * @author Rômulo Aurélio
	 * @date 08/06/2009
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelComNumeroMedidorEnergiaImovel(String numeroMedidorEnergia) 
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		
		Integer retorno = null;
		
		String consulta = null;
	
		try {
			
			consulta = 	" select imov.imov_id as idImovel "
					  + " from cadastro.imovel imov"
					  + " where imov.imov_nnmedidorenergia = :numeroMedidorEnergia ";
					
			retorno = (Integer)session.createSQLQuery(consulta)
					.addScalar("idImovel" , Hibernate.INTEGER)
					.setString("numeroMedidorEnergia", numeroMedidorEnergia).uniqueResult();		    
			
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
	 * Pesquisa a coleção de categorias do imovel [UC0472] Consultar Imovel
	 * 
	 * 
	 * 
	 * @author Sávio Luiz
	 * 
	 * @since 07/09/2006
	 * 
	 * 
	 * 
	 * @param filtroClienteImovel
	 * 
	 * parametros para a consulta
	 * 
	 * @return Description of the Return Value
	 * 
	 * @exception ErroRepositorioException
	 * 
	 * Description of the Exception
	 * 
	 */

	public Collection pesquisarCategoriaSubcategoriaImovel(Integer idImovel)

	throws ErroRepositorioException {

		// cria a coleção de retorno

		Collection retorno = null;

		// obtém a sessão

		Session session = HibernateUtil.getSession();

		String consulta = null;

		// pesquisa a coleção de atividades e atribui a variável "retorno"

		try {

			consulta = "select categoria.id, " 
					    
				    +"subcategoria.id "
				    
					+"from ImovelSubcategoria imovelSubcategoria "

					+ "left join imovelSubcategoria.comp_id.subcategoria subcategoria "

					+ "left join subcategoria.categoria categoria "

					+ "where imovelSubcategoria.comp_id.imovel.id = :idImovel "
					+ "order by categoria.id";

			retorno = session.createQuery(consulta).setInteger("idImovel",

			idImovel.intValue()).list();

			// erro no hibernate

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
	 * Pesquisa qual foi o consumo faturado do imovel
	 * 
	 * @author Hugo Amorim
	 * @date 18/12/2009
	 * @return consumoFaturadoMes
	 * @throws ControladorException
	 */
	public Integer obterConsumoFaturadoImovelNoMes(Integer idImovel,
			Integer mesAnoReferencia) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		
		Integer retorno = null;
		
		String consulta = null;
	
		try {
			
			consulta = 	" select ch.numeroConsumoFaturadoMes "
				  + " from ConsumoHistorico ch "
				  + " inner join ch.ligacaoTipo lt"
				  + " where ch.imovel = :idImovel" 
				  +	" and ch.referenciaFaturamento = :mesAnoReferencia and lt.id = :ligacaoAgua";
				
		retorno = (Integer)session.createQuery(consulta)
				.setParameter("idImovel", idImovel)
				.setParameter("mesAnoReferencia", mesAnoReferencia)
				.setParameter("ligacaoAgua",LigacaoTipo.LIGACAO_AGUA)
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
	 * Este método está de acordo com o [UC0979] Gerar Relatório de Imóveis em Programas Especiais.
	 * 
	 * @author Hugo Leonardo
	 * @date 18/01/2010
	 *
	 * @param FiltrarRelatorioImoveisProgramasEspeciaisHelper filtro
	 * @return List
	 * @throws ErroRepositorioException
	 */
	public List filtrarImoveisProgramasEspeciais(FiltrarRelatorioImoveisProgramasEspeciaisHelper filtro) throws ErroRepositorioException {
		List retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "";

			// mês/Ano referência
			if(filtro.getMesAnoReferencia() != null && filtro.getMesAnoReferencia().equals("")){
				consulta += "" 
					+ filtro.getMesAnoReferencia() 
					+ " and ";
			}
			
			//perfilImovel
			if (filtro.getPerfilImovel() != null && !filtro.getPerfilImovel().equals("")){
				consulta += "" 
					+ filtro.getPerfilImovel() 
					+ " and ";
			}
			/*
			// unidadeNegocio
			if ( filtro.getIdUnidadeNegocio() !=null && !filtro.getIdUnidadeNegocio().equals("")){
				consulta += "e.id = " 
					+ filtro.getIdUnidadeNegocio() 
					+ " and ";
			}
			*/
			
			// região de Desenvolvimento
			if ( filtro.getIdRegiaoDesenvolvimento() !=null && !filtro.getIdRegiaoDesenvolvimento().equals("")){
				consulta += "e.id = " 
					+ filtro.getIdRegiaoDesenvolvimento() 
					+ " and ";
			}
			
			//Localidade
			if (filtro.getIdLocalidade() != null 
					&& !filtro.getIdLocalidade().equals("")){
				consulta += "b.id = " 
					+ filtro.getIdLocalidade() 
					+ " and ";
			}
		
			//retira o and do final se houver
			if (consulta.substring(consulta.length() - 4, consulta.length()).equals("and ")){
				consulta =  consulta.substring(0, consulta.length() - 4);
			}
			
			consulta += " group by "
			+ "d.nomeAbreviado, d.nome, "
			+ "e.nome, b.descricao "
			+ "order by "
			+ "d.nomeAbreviado, d.nome, "
			+ "e.nome, b.descricao";

			
			retorno = session.createQuery(consulta).list();

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
	 * Pesquisa qual foi o consumo faturado do imovel
	 * 
	 * @author Rômulo Aurélio
	 * @date 03/03/2010
	 * @throws ControladorException
	 */
	public ImovelPerfil recuperaPerfilImovel(Integer idImovel) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		
		ImovelPerfil retorno = null;
		
		String consulta = null;
	
		try {
			
			consulta = 	" select imovelPerfil "
				  + " from gcom.cadastro.imovel.Imovel imovel, gcom.cadastro.imovel.ImovelPerfil imovelPerfil "
				  + " where imovel.imovelPerfil.id = imovelPerfil.id " 
				  +	" and imovel.id = :idImovel ";
				
		retorno = (ImovelPerfil)session.createQuery(consulta)
				.setParameter("idImovel", idImovel).setMaxResults(1)
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
	 * [UC0820] Atualizar Faturamento do Movimento Celular
     * 
     * Método criado para atualizar apenas os campos necessários para
     * imovel.
     * 	 
     * @author Bruno Barros
     * @date 31/03/2010
     * @param imovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelLeituraAnormalidadeProcessoMOBILE( Imovel imovel ) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;

		try {
			
			Connection jdbcCon = session.connection();
			
			String update = 		
				"update " +
				" cadastro.imovel " +
				" set ltan_id = ?, " + // 1
				" imov_tmultimaalteracao = ? " + // 2
				" where imov_id = ? ";// 3
			
			st = 
				jdbcCon.prepareStatement(update);
			
			st.setInt( 1, imovel.getLeituraAnormalidade().getId() );
			st.setTimestamp( 2, new Timestamp( new Date().getTime() ) );
			st.setInt( 3, imovel.getId() );

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
	 * [UC1005] Determinar Confirmação da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 11/03/2010
	 */
	
	public void atualizarDataRetiradaImovelSituacaoCobranca(
			Integer idImovelSituacaoCobranca, Date dataRetirada) throws ErroRepositorioException {
		String consulta = "";
		Session session = HibernateUtil.getSession();
	
		try {
	
				consulta = "update gcom.cadastro.imovel.ImovelCobrancaSituacao set "
						+ "iscb_dtretiradacobranca = :dataRetirada , "
						+" iscb_tmultimaalteracao = :ultimaAlteracao " 
						+ "where iscb_id = :idImovelSituacaoCobranca ";
	
				session.createQuery(consulta).
				   setDate("dataRetirada", dataRetirada).
				   setTimestamp("ultimaAlteracao",new Date()).
				   setInteger("idImovelSituacaoCobranca",idImovelSituacaoCobranca).
				   executeUpdate();
	
	
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC0672] - Registrar Movimento de Retorno dos Negativadores
	 *
	 * @author Vivianne Sousa
	 * @date 12/03/2010
	 */
	public void atualizarSituacaoCobrancaImovel(Integer idSituacaoCobrancaNova, 
			Integer idSituacaoCobrancaBanco, Integer idImovel)	throws ErroRepositorioException {

		String consulta = "";
		Session session = HibernateUtil.getSession();
		try {
	
				consulta = "update gcom.cadastro.imovel.Imovel set "
						+ "cbst_id = :idSituacaoCobrancaNova, imov_tmultimaalteracao = :ultimaAlteracao " 
						+ "where imov_id = :idImovel and " 
						+ "cbst_id = :idSituacaoCobrancaBanco ";
	
				session.createQuery(consulta).
				   setInteger("idSituacaoCobrancaNova",idSituacaoCobrancaNova).
				   setInteger("idSituacaoCobrancaBanco",idSituacaoCobrancaBanco).
				   setTimestamp("ultimaAlteracao",new Date()).
				   setInteger("idImovel",idImovel).
				   executeUpdate();
	
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}

	/** 
	 * [UC1000] Informar Medidor de Energia por Rota.
	 * 
	 * Atualizar Número Medidor de Energia do Imóvel.
	 * 
	 * @author Hugo Leonardo
	 * @date 15/03/2010
	 *
	 */
	public void atualizarNumeroMedidorEnergiaImovel(Integer matricula, String medidorEnergia)
		throws ErroRepositorioException{

		String consulta = "";

		Session session = HibernateUtil.getSession();
		
		try {
			
			consulta += " update gcom.cadastro.imovel.Imovel "
					 +  " set imov_nnmedidorenergia = :medidorEnergia " 
 					 +  " where imov_id = :matricula ";
	
		   session.createQuery(consulta).setInteger("matricula",matricula)
		   		.setString("medidorEnergia", medidorEnergia)
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
	 * @author Vivianne Sousa
	 * @date 11/05/2010
	 */
	public Collection pesquisarImovelCobrancaSituacaoPorImovel(
			String[] idsImovelCobrancaSituacao) throws ErroRepositorioException {

		Collection retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try {
			String hql = " select ics"
				 + " from gcom.cadastro.imovel.ImovelCobrancaSituacao as ics"
				 + " inner join fetch ics.cobrancaSituacao as cbst " 
				 + " where ics.id in (:idsImovelCobrancaSituacao) ";
			
			retorno = (List) session.createQuery(hql).setParameterList(
					"idsImovelCobrancaSituacao", idsImovelCobrancaSituacao).list();
		
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
	 * [UC0490] Informar Situação de Cobrança
	 * [SB0004] – Selecionar Situações de Cobrança
	 * 
	 * seleciona as situações de cobrança 
	 * (a partir da tabela COBRANÇA_SITUACAO com CBST_ICUSO=1 
	 * e CBST_ICBLOQUEIOINCLUSAO=2)retirando as ocorrências 
	 * com CBST_ID=CBST_ID da tabela IMOVEL_COBRANCA_SITUACAO 
	 * para IMOV_ID=Id do imóvel recebido e 
	 * ISCB_DTRETIRADACOBRANCA com valor igual a nulo
	 * 
	 * @author Vivianne Sousa
	 * @date 12/05/2010
	 */
	public Collection pesquisarCobrancaSituacao(
			Integer idImovel, boolean temPermissaoEspecial) throws ErroRepositorioException {

		Collection retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try {
			String hql = " select cbst "
				 + " from gcom.cobranca.CobrancaSituacao as cbst " 
				 + " where cbst.indicadorUso = :indicadorUso " 
				 + " and cbst.indicadorBloqueioInclusao = :indicadorBloqueioInclusao " 
				 + " and cbst.id not in " 
				 + "(select cbs.id "
				 + " from gcom.cadastro.imovel.ImovelCobrancaSituacao as ics"
				 + " inner join ics.cobrancaSituacao as cbs " 
				 + " where ics.imovel.id = :idImovel " 
				 + " and ics.dataRetiradaCobranca is null ) ";
			
			if(!temPermissaoEspecial){
				hql = hql + " and cbst.indicadorSelecaoApenasComPermissao = 2 ";
			}
			
			retorno = (List) session.createQuery(hql)
			.setShort("indicadorUso", ConstantesSistema.SIM)
			.setShort("indicadorBloqueioInclusao", ConstantesSistema.NAO)
			.setInteger("idImovel",idImovel)
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
	 * O método abaixo realiza uma pesquisa em imovel e retorna os campos
	 * 
	 * id localidade e codigo do setor
	 * 
	 *  Object[0] = idLocalidade
	 *  Object[1] = codigoSetor
	 * 
	 * @author Hugo Amorim
	 * @date 01/06/2010
	 */
	public Object[] pesquisarLocalidadeCodigoSetorImovel(Integer idImovel)
		throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Object[] retorno = null;

		try {

			String sql = "SELECT l.id,sc.codigo FROM Imovel as i "
					+" INNER JOIN i.localidade as l "
					+" INNER JOIN i.setorComercial as sc "
					+" WHERE i.id = :idImovel ";
					
		    retorno = (Object[]) session.createQuery(sql)
		    	.setInteger("idImovel", idImovel).setMaxResults(1).uniqueResult();


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
	 * Inserir e Atualizar Imovel 
	 *
	 * @author Raphael Rossiter
	 * @date 02/06/2010
	 *
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarClienteImovelUsuario(Integer idImovel) throws ErroRepositorioException{

		String update = "";
	
		Session session = HibernateUtil.getSession();
		
		try {
			
			update += " update gcom.cadastro.cliente.ClienteImovel "
					 +  " set clim_icnomeconta = 1 " 
					 +  " where imov_id = :idImovel and crtp_id = " + ClienteRelacaoTipo.USUARIO + " and clim_dtrelacaofim is null";
	
		   session.createQuery(update).setInteger("idImovel", idImovel).executeUpdate();
					
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
	
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}

	/**
 	 * @author Rômulo Aurélio
	 * @date 23/06/2010
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer  pesquisarDebitoTipoImovelDoacao(
			Integer idImovel) throws ErroRepositorioException {

		/** * Declara variáveis locais ** */
		Session session = null;
		Integer retorno = null;

		session = HibernateUtil.getSession();

		/**
		 * * Script HQL que já monta uma coleção de ImovelCobrarDoacaoHelper com
		 * tudo que é necessário **
		 */
		try {
			String consulta = "select "
					+ "    imovelDoacao.entidadeBeneficente.debitoTipo.id "
					+ "from gcom.cadastro.imovel.ImovelDoacao imovelDoacao "
					+ "where" + " imovelDoacao.imovel.id = :idImovel"
					+ " AND imovelDoacao.imovel.indicadorImovelCondominio <> 1"
					+ " AND imovelDoacao.imovel.indicadorExclusao <> 1"
					+ " AND imovelDoacao.dataCancelamento is null";

			retorno = (Integer)session.createQuery(consulta)
					.setInteger("idImovel", idImovel).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			/** * levanta a exceção para a próxima camada ** */
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			/** * fecha a sessão ** */
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
 	 * @author Daniel Alves
	 * @date 20/07/2010
	 * @param idImovelPerfil
	 * @return ImovelPerfil
	 * @throws ErroRepositorioException
	 */
	public ImovelPerfil  pesquisarImovelPerfil(
			Integer idImovelPerfil) throws ErroRepositorioException {

		/** * Declara variáveis locais ** */
		Session session = null;
		ImovelPerfil retorno = null;

		session = HibernateUtil.getSession();

		
		try {
			String consulta = " select imovelPerfil "
				              + " from gcom.cadastro.imovel.ImovelPerfil imovelPerfil "
				             + " where imovelPerfil.id = :idImovelPerfil ";
				
		    retorno = (ImovelPerfil)session.createQuery(consulta)
				.setParameter("idImovelPerfil", idImovelPerfil).setMaxResults(1)
				.uniqueResult();

		} catch (HibernateException e) {
			/** * levanta a exceção para a próxima camada ** */
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			/** * fecha a sessão ** */
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * @author Daniel Alves
	 * @date 28/07/2010
	 * @param idImovel
	 * @return Colecao de imovelSubcategoriaHelper
	 * @throws ErroRepositorioException
	 */
	public Collection<ImovelSubcategoriaHelper> consultaSubcategorias(int idImovel)
		throws ErroRepositorioException {

		Collection<ImovelSubcategoriaHelper> retorno = new ArrayList<ImovelSubcategoriaHelper>();

		Collection colecaoSubcategorias = null;

		Object[] arraySubcategorias = null;

		Session session = HibernateUtil.getSession();

		try {

			String 
			consulta = "select" + " su.id,"// 0

							+ " su.descricao,"// 1

							+ " ca.descricao,"// 2

							+ " isub.quantidadeEconomias"// 3

							+ " from ImovelSubcategoria isub"

							+ " inner join isub.comp_id.subcategoria su"

							+ " inner join isub.comp_id.imovel im"

							+ " inner join su.categoria ca" + " where im.id = "

							+ idImovel;
					

					colecaoSubcategorias = session.createQuery(consulta).list();

					if (!colecaoSubcategorias.isEmpty()) {

						Iterator iteratorSubcategoria = colecaoSubcategorias.iterator();

						ImovelSubcategoriaHelper imovelSubcategoriaHelper = null;

						while (iteratorSubcategoria.hasNext()) {

							arraySubcategorias = (Object[]) iteratorSubcategoria.next();

							imovelSubcategoriaHelper = new ImovelSubcategoriaHelper();

							imovelSubcategoriaHelper.setSubcategoria(arraySubcategorias[1] != null
								&& !arraySubcategorias[1].toString().trim()
									.equalsIgnoreCase("") ? (String) arraySubcategorias[1] : null);

							imovelSubcategoriaHelper
									.setCategoria(arraySubcategorias[2] != null
									&& !arraySubcategorias[2].toString().trim()
									.equalsIgnoreCase("") ? (String) arraySubcategorias[2] : null);

							imovelSubcategoriaHelper.setQuantidadeEconomias(arraySubcategorias[3] != null
								&& !arraySubcategorias[3].toString().trim()
									.equalsIgnoreCase("") ? ((Short) arraySubcategorias[3]).shortValue() : 0);

							retorno.add(imovelSubcategoriaHelper);

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
	 * Caso o retorno seja TRUE, quer dizer que a alteração da inscrição acarretou na mudança do grupo de faturamento,
	 * e este novo grupo do imóvel ja foi faturado.
	 * 
	 * 
	 * @author Arthur Carvalho
	 * @date 16/09/2010
	 */
	public FaturamentoGrupo[] verificaInscricaoAlteradaAcarretaMudancaDoGrupoFaturamento(Integer idQuadraAnterior, Integer idQuadraAtual) throws ErroRepositorioException {
		
		/** * Declara variáveis locais ** */
		Session session = null;
		FaturamentoGrupo[] faturamentoGrupos = new FaturamentoGrupo[2];
		FaturamentoGrupo faturamentoGrupoOrigem = new FaturamentoGrupo();
		FaturamentoGrupo faturamentoGrupoDestino = new FaturamentoGrupo();
		
		session = HibernateUtil.getSession();
		
		
		try {
			//Consulta o faturamento grupo Origem
			String consultaFaturamentoGrupoOrigem = " select ftgr "
				              + " from gcom.cadastro.localidade.Quadra qdra"
				              + " inner join qdra.rota rota"
				              + " inner join rota.faturamentoGrupo ftgr"
				              + " where qdra.id = :idQuadraAnterior ";
				
				faturamentoGrupoOrigem = (FaturamentoGrupo) session.createQuery(consultaFaturamentoGrupoOrigem)
					.setParameter("idQuadraAnterior", idQuadraAnterior).setMaxResults(1)
					.uniqueResult();
			
			//Consulta o faturamento grupo Destino do imovel
			String consultaFaturamentoGrupoDestino= " select ftgr "
	              + " from gcom.cadastro.localidade.Quadra qdra"
	              + " inner join qdra.rota rota"
	              + " inner join rota.faturamentoGrupo ftgr"
	              + " where qdra.id = :idQuadraAtual ";
	
				faturamentoGrupoDestino = (FaturamentoGrupo) session.createQuery(consultaFaturamentoGrupoDestino)
					.setParameter("idQuadraAtual", idQuadraAtual).setMaxResults(1)
					.uniqueResult();
			faturamentoGrupos[0] = faturamentoGrupoOrigem;
			faturamentoGrupos[1] = faturamentoGrupoDestino;
			
		} catch (HibernateException e) {
			/** * levanta a exceção para a próxima camada ** */
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			/** * fecha a sessão ** */
			HibernateUtil.closeSession(session);
		}

		return faturamentoGrupos;
		
	}

	/**
	 * 	@author Hugo Leonardo
	 *  @date 21/09/2010
	 *  
	 * 	UC_0009 - Manter Cliente
	 *  	[FS0008] ? Verificar permissão especial para cliente de imóvel público
	 *  
	 * 	Verifica se o Cliente possui algum imóvel, cujo o tipo da categoria 
	 *  em subcategoria seja igual a PUBLICO.
	 *  
	 * 	Caso o cliente possua algum imóvel, retornar a quantidade de imoveis nesta situação
	 * 	Caso contrário retorna zero. 
	 * 
	 *  @param idCliente
	 *  @return Integer
	 *  
	 *  @throws RepositorioException
	 */
	public Integer obterQuantidadeImoveisPublicosAssociadoAoCliente(Integer idCliente) throws ErroRepositorioException {

		Integer retorno = null;
		//Integer contador = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = " select count (ci.id) "
					 + " from ClienteImovel ci "
					 + " inner join ci.imovel imo "
					 + " inner join imo.imovelSubcategorias imoSub "
					 + " inner join imoSub.comp_id.subcategoria sbCat "
					 + " inner join sbCat.categoria cat "
					 + " where ci.cliente = :idCliente "
					 + " and cat.categoriaTipo = :tipoCategoria ";
			
			retorno = (Integer) session.createQuery(consulta)
				.setParameter("idCliente", idCliente)
				.setParameter("tipoCategoria", CategoriaTipo.PUBLICO)
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
	 * @author Arthur Carvalho
	 */
	public boolean verificaGeracaoDadosLeituraGrupoFaturamento(FaturamentoGrupo faturamentoGrupo) throws ErroRepositorioException {
		
		/** * Declara variáveis locais ** */
		Session session = null;
		boolean retorno = false;
		MovimentoRoteiroEmpresa movimentoRoteiroEmpresa = new MovimentoRoteiroEmpresa();
		session = HibernateUtil.getSession();
		
		
		try {
			//Consulta o faturamento grupo Origem
			String consulta = " select mvre "
				              + " from gcom.micromedicao.MovimentoRoteiroEmpresa mvre"
				              + " where mvre.faturamentoGrupo = :idFaturamentoGrupo "
				              + " and mvre.anoMesMovimento = :anoMesReferenciaFaturamentoGrupo";
				
			movimentoRoteiroEmpresa = (MovimentoRoteiroEmpresa) session.createQuery(consulta)
					.setParameter("idFaturamentoGrupo", faturamentoGrupo.getId())
					.setParameter("anoMesReferenciaFaturamentoGrupo", faturamentoGrupo.getAnoMesReferencia())
					.setMaxResults(1)
					.uniqueResult();
			
			if ( movimentoRoteiroEmpresa != null ) {
				retorno = true;
			}
			
		} catch (HibernateException e) {
			/** * levanta a exceção para a próxima camada ** */
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			/** * fecha a sessão ** */
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}
	
	
	/**
	 * [UC0074] Alterar Inscrição de Imóvel
	 * @author Arthur Carvalho
	 * @date 19/09/2010
	 * @param idLocalidade
	 * @param idSetorComercial
	 * @param idQuadra
	 * @param idQuadraFace
	 * @param subLote
	 * @param lote
	 * @return imovelInscricaoAlterada
	 * @throws ErroRepositorioException
	 */
	public ImovelInscricaoAlterada verificarDuplicidadeImovelInscricaoAlterada(Integer idLocalidade, Integer idSetorComercial, Integer idQuadra,
			Integer idQuadraFace, Integer lote, Integer subLote ) throws ErroRepositorioException {
		
		/** * Declara variáveis locais ** */
		Session session = null;
		ImovelInscricaoAlterada imovelInscricaoAlterada = new ImovelInscricaoAlterada();
		session = HibernateUtil.getSession();
		
		
		try {
			//Consulta o faturamento grupo Origem
			String consulta = " select imia "
				              + " from gcom.cadastro.imovel.ImovelInscricaoAlterada imia"
				              + " where imia.indicadorAtualizado = :indicadorAtualizado "
				              + " and imia.indicadorAtualizacaoExcluida = :indicadorAtualizacaoExcluida"
				              + " and (imia.indicadorErroAlteracao is null)"
				              + " and imia.localidadeAtual.id = :idLocalidade"
				              + " and imia.setorComercialAtual.id = :idSetorComercial"
				              + " and imia.quadraAtual.id = :idQuadra"
				              + " and imia.loteAtual = :lote"
				              + " and imia.subLoteAtual = :subLote";
			
							  if ( idQuadraFace != null ) {
				            	  consulta = consulta + " and imia.quadraFaceAtual.id = :idQuadraFaceAtual";
				              }
				              
				if ( idQuadraFace != null ) {
					imovelInscricaoAlterada = (ImovelInscricaoAlterada) session.createQuery(consulta)
							.setParameter( "indicadorAtualizado", ConstantesSistema.NAO )
							.setParameter( "indicadorAtualizacaoExcluida", ConstantesSistema.NAO )
							.setParameter( "idLocalidade", idLocalidade )
							.setParameter( "idSetorComercial", idSetorComercial )
							.setParameter( "idQuadra", idQuadra )
							.setParameter( "lote" , lote )
							.setParameter( "subLote", subLote )
							.setParameter( "idQuadraFaceAtual", idQuadraFace )
							.setMaxResults(1)
							.uniqueResult();
				} else {
					imovelInscricaoAlterada = (ImovelInscricaoAlterada) session.createQuery(consulta)
							.setParameter( "indicadorAtualizado", ConstantesSistema.NAO )
							.setParameter( "indicadorAtualizacaoExcluida", ConstantesSistema.NAO )
							.setParameter( "idLocalidade", idLocalidade )
							.setParameter( "idSetorComercial", idSetorComercial )
							.setParameter( "idQuadra", idQuadra )
							.setParameter( "lote" , lote )
							.setParameter( "subLote", subLote )
							.setMaxResults(1)
							.uniqueResult();
				}
			
			
			
		} catch (HibernateException e) {
			/** * levanta a exceção para a próxima camada ** */
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			/** * fecha a sessão ** */
			HibernateUtil.closeSession(session);
		}

		return imovelInscricaoAlterada;
		
	}
	
	
	/**
	 * [UC0995] Emitir Declaração Transferência de Débitos/Créditos
	 * @author Daniel Alves
	 * @date 23/09/2010
	 * @return Municipio
	 */
	public Municipio pesquisarMunicipioImovel(Integer idImovel) throws ErroRepositorioException {
		
		/** * Declara variáveis locais ** */
		Session session = null;
		Municipio retorno = new Municipio();
		
		session = HibernateUtil.getSession();
		
		
		try {
			//Consulta o municipio apartir do imovel
			String consulta = " select municipio "
				              + " from gcom.cadastro.imovel.Imovel imovel"
				              + " Inner Join imovel.logradouroBairro logradouroBairro" 
				              + " Inner Join logradouroBairro.logradouro logradouro" 
				              + " Inner Join logradouro.municipio municipio"
				              + " where imovel.id = :idImovel";
				
			retorno = (Municipio) session.createQuery(consulta)
					.setParameter("idImovel", idImovel)
					.setMaxResults(1)
					.uniqueResult();	
			
		} catch (HibernateException e) {
			/** * levanta a exceção para a próxima camada ** */
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			/** * fecha a sessão ** */
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}
	
	/**
	 * 
	 * [UC0630] Solicitar Emissão do Extrato de Débitos
	 * 
	 * [SB0001] – Calcular valor dos descontos pagamento à vista.
	 * 
	 * @author Vivianne Sousa
	 * @date 21/10/2010
	 * 
	 * @throws ControladorException
	 */
	public Short consultarNumeroReparcelamentoConsecutivosImovel(Integer idImovel)
		throws ErroRepositorioException {

		Short retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT imovel.numeroReparcelamentoConsecutivos " + 
				"FROM Imovel imovel " + 
				"where imovel.id = :idImovel ";

			retorno = (Short)session.createQuery(consulta).setInteger("idImovel",
			idImovel.intValue()).setMaxResults(1).uniqueResult();	

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * autor: Adriana Muniz
	 * Data: 12/05/2011
	 * Pesquisa o id da quadra pelo id do imóvel - Gerar
	 * Arquivo da declaração de quitação anual de débito
	 */
	public Integer pesquisaIdQuadraImovel(Integer idImovel) throws ErroRepositorioException{
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try{
			consulta = "select i.quadra.id " 
					+ " from Imovel i " 
					+ " where i.id = :idImovel ";
			
			retorno = (Integer) session.createQuery(consulta).setInteger("idImovel", idImovel)
			.setMaxResults(1).uniqueResult();
		}catch(HibernateException e){
			throw new ErroRepositorioException(e, "Erro no hibernate");
		}finally {
			HibernateUtil.closeSession(session);
		}
		return retorno; 
	}
	
	/**
	 * 
	 * 
	 * Metodo para verificar se a rota que o imovel pertence
	 * ja foi gerada para o mes de faturamento do grupo 
	 * 
	 * @author Pamela Gatinho
	 * @date 01/08/2011
	 * @return boolean
	 */
	public boolean verificaGeracaoDadosLeituraRota(FaturamentoGrupo faturamentoGrupo, Rota rota) throws ErroRepositorioException {
		
		/** * Declara variáveis locais ** */
		Session session = null;
		boolean retorno = false;
		MovimentoRoteiroEmpresa movimentoRoteiroEmpresa = new MovimentoRoteiroEmpresa();
		session = HibernateUtil.getSession();
		
		
		try {
			//Consulta o faturamento grupo Origem
			String consulta = " select mvre "
				              + " from gcom.micromedicao.MovimentoRoteiroEmpresa mvre"
				              + " where mvre.faturamentoGrupo.id = :idFaturamentoGrupo "
				              + " and mvre.rota.id = :idRota "
				              + " and mvre.anoMesMovimento = :anoMesReferenciaFaturamentoGrupo";
				
			movimentoRoteiroEmpresa = (MovimentoRoteiroEmpresa) session.createQuery(consulta)
					.setParameter("idFaturamentoGrupo", faturamentoGrupo.getId())
					.setParameter("idRota", rota.getId())
					.setParameter("anoMesReferenciaFaturamentoGrupo", faturamentoGrupo.getAnoMesReferencia())
					.setMaxResults(1)
					.uniqueResult();
			
			if ( movimentoRoteiroEmpresa != null ) {
				retorno = true;
			}
			
		} catch (HibernateException e) {
			/** * levanta a exceção para a próxima camada ** */
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			/** * fecha a sessão ** */
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}
	
	/**
	 * @author Adriana Muniz
	 * @date 06/12/2011
	 * 
	 * Pesquisa o perfil do imovel pelo id do imóvel
	 * 
	 * @param idImovelPerfil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ImovelPerfil  pesquisarImovelPerfilIdImovel(
			Integer idImovel) throws ErroRepositorioException {

		/** * Declara variáveis locais ** */
		Session session = null;
		ImovelPerfil retorno = null;

		session = HibernateUtil.getSession();

		
		try {
			
			String consulta = " SELECT ip "
							+ " FROM gcom.cadastro.imovel.Imovel i "
							+ " INNER JOIN i.imovelPerfil ip "
							+ " WHERE i.id = :idImovel ";
				
		    retorno = (ImovelPerfil)session.createQuery(consulta)
				.setParameter("idImovel", idImovel).setMaxResults(1)
				.uniqueResult();

		} catch (HibernateException e) {
			/** * levanta a exceção para a próxima camada ** */
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			/** * fecha a sessão ** */
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	
	/**
	 * [UC1122] Automatizar Perfis de Grandes Consumidores
	 * 
	 * @author Mariana Victor
	 * @date 07/02/2011
	 * 
	 * @param anoMesFaturamentoSistemaParametro
	 * @param idSetorComercial
	 * @throws ControladorException
	 */
	public Collection<Imovel> consultarImovelLocalidade(Integer idLocalidade)
			throws ErroRepositorioException {

		Collection<Imovel> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT imov.imov_id AS idImov, loca.loca_nnconsumograndeusuario AS consumo, " +
					" imov.iper_id AS perfil" +
					" FROM cadastro.imovel imov " +
					" INNER JOIN cadastro.localidade loca ON loca.loca_id = :idLocalidade " +
					" AND imov.loca_id = loca.loca_id ";

			Collection colecao = (Collection) session.createSQLQuery(consulta).addScalar(
					"idImov", Hibernate.INTEGER).addScalar(
							"consumo", Hibernate.INTEGER).addScalar(
									"perfil", Hibernate.INTEGER).setInteger("idLocalidade",
						idLocalidade).list();
			
			if (!colecao.isEmpty()) {

				Iterator iteratorColecaoImovel = colecao.iterator();
				retorno = new ArrayList<Imovel>();
				while (iteratorColecaoImovel.hasNext()) {

					Object[] objeto = (Object[]) iteratorColecaoImovel.next();
					Imovel imovel = new Imovel();
					
					if (objeto[0] != null) {
						imovel.setId((Integer) objeto[0]);
					}

					if (objeto[1] != null) {
						Localidade localidade = new Localidade();
						localidade.setConsumoGrandeUsuario(((Integer)objeto[1]).intValue());
						imovel.setLocalidade(localidade);
					}
					
					if (objeto[2] != null) {
						ImovelPerfil imovelPerfil = new ImovelPerfil();
						imovelPerfil.setId(((Integer)objeto[2]).intValue());
						imovel.setImovelPerfil(imovelPerfil);
					}
					
					retorno.add(imovel);
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
	 * [UC1122] Automatizar Perfis de Grandes Consumidores
	 * 
	 * @author Mariana Victor
	 * @date 07/02/2011
	 * 
	 * @param anoMesFaturamentoSistemaParametro
	 * @param idSetorComercial
	 * @throws ControladorException
	 */
	public void atualizarImovelPerfil(Integer idImovel, Integer idPerfil)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = " UPDATE gcom.cadastro.imovel.Imovel " +
					" SET iper_id = :idPerfil " +
					" WHERE imov_id = :idImovel ";

			session.createQuery(consulta)
				.setInteger("idPerfil", idPerfil).setInteger("idImovel",
						idImovel).executeUpdate();
			
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection consultarImovel(Integer idLocalidade,Integer idGerenciaRegional,
			Integer idUnidadeNegocio)throws ErroRepositorioException {


		Session session = HibernateUtil.getSession();

		Collection retorno = null;
		Query query = null;
		Map parameters = new HashMap();
		try {

			String sql = "SELECT imov " 
					+" FROM Imovel as imov "
					+" INNER JOIN fetch imov.localidade as loca ";
			
			String sqlParte2 = " WHERE imov.imovelPerfil.id = :imovelPerfil and " +
			"( imov.indicadorExclusao IS NULL or imov.indicadorExclusao != :indicadorExclusao ) " ;
			parameters.put("imovelPerfil", ImovelPerfil.TARIFA_SOCIAL);
			parameters.put("indicadorExclusao", ConstantesSistema.SIM);
			
			if(idLocalidade != null){
				sqlParte2 = sqlParte2 + " AND loca.id = :idLocalidade ";	
				parameters.put("idLocalidade", idLocalidade);
			}
			
			if(idGerenciaRegional != null){
				sqlParte2 = sqlParte2 + " AND loca.unidadeNegocio.gerenciaRegional.id = :idGerenciaRegional ";	
				parameters.put("idGerenciaRegional", idGerenciaRegional);
			}
			
			if(idUnidadeNegocio != null){
				sqlParte2 = sqlParte2 + " AND loca.unidadeNegocio.id = :idUnidadeNegocio ";			
				parameters.put("idUnidadeNegocio", idUnidadeNegocio);
			}
				
			sql = sql + sqlParte2;
			
			
			query = session.createQuery(sql);
			
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
			
			retorno = (Collection)query.list();
			
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
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection consultarImovelCadastro(Integer idLocalidade,Integer idGerenciaRegional,
			Integer idUnidadeNegocio, Integer anoMesPesquisaInicial,Integer anoMesPesquisaFinal)throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno = null;
		Query query = null;
		Map parameters = new HashMap();
		try {

			String sql = "SELECT distinct(imov) " 
					+" FROM TarifaSocialDadoEconomia tsde "
					+" INNER JOIN tsde.imovel as imov "
					+" INNER JOIN fetch imov.localidade as loca ";
			
			String sqlParte2 = " WHERE imov.imovelPerfil.id = :imovelPerfil  and " +
					"( imov.indicadorExclusao IS NULL or imov.indicadorExclusao != :indicadorExclusao ) " ;
			parameters.put("imovelPerfil", ImovelPerfil.TARIFA_SOCIAL);
			parameters.put("indicadorExclusao", ConstantesSistema.SIM);
			
			if(anoMesPesquisaInicial != null){
				
				sqlParte2 = sqlParte2 + " and  tsde.dataImplantacao >= to_date('" 
				+ Util.formatarDataComTracoAAAAMMDD(Util.gerarDataInicialApartirAnoMesRefencia(anoMesPesquisaInicial))
				+ "','YYYY-MM-DD') ";
				
//				sqlParte2 = sqlParte2 + " and tsde.dataImplantacao >= :anoMesPesquisaInicial ";
//				parameters.put("anoMesPesquisaInicial", Util.gerarDataInicialApartirAnoMesRefencia(anoMesPesquisaInicial));
			}

			if(anoMesPesquisaFinal != null){
				
				sqlParte2 = sqlParte2 + " and  tsde.dataImplantacao <= to_date('" 
				+ Util.formatarDataComTracoAAAAMMDD(Util.gerarDataApartirAnoMesRefencia(anoMesPesquisaFinal))
				+ "','YYYY-MM-DD') ";
//				sqlParte2 = sqlParte2 + " and tsde.dataImplantacao <= :anoMesPesquisaFinal ";
//				parameters.put("anoMesPesquisaFinal", Util.gerarDataApartirAnoMesRefencia(anoMesPesquisaFinal));
			}
			
			if(idLocalidade != null){
				sqlParte2 = sqlParte2 + " AND loca.id = :idLocalidade ";	
				parameters.put("idLocalidade", idLocalidade);
			}
			
			if(idGerenciaRegional != null){
				sqlParte2 = sqlParte2 + " AND loca.unidadeNegocio.gerenciaRegional.id = :idGerenciaRegional ";	
				parameters.put("idGerenciaRegional", idGerenciaRegional);
			}
			
			if(idUnidadeNegocio != null){
				sqlParte2 = sqlParte2 + " AND loca.unidadeNegocio.id = :idUnidadeNegocio ";			
				parameters.put("idUnidadeNegocio", idUnidadeNegocio);
			}
				
			sql = sql + sqlParte2;
			
			
			query = session.createQuery(sql);
			
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
			
			retorno = (Collection)query.list();
			
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
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarImovelEconomia(Integer idImovel) 
		throws ErroRepositorioException {
	
	Session session = HibernateUtil.getSession();
	
	Collection retorno = null;
	String consulta = null;
	
	try {
		
		consulta = 	" select imec " +
			" FROM TarifaSocialDadoEconomia tsde " +
			" inner join tsde.imovelEconomia imec " +
			" inner join tsde.imovel imov " +
			" where imov.id = :idImovel " +
			" and imec.numeroCelpe is not null ";
				
		retorno = session.createQuery(consulta)
				.setInteger("idImovel", idImovel)
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
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialDadoEconomia pesquisarTarifaSocialDadoEconomia(Integer idImovel) 
		throws ErroRepositorioException {
	
	Session session = HibernateUtil.getSession();
	
	TarifaSocialDadoEconomia retorno = null;
	String consulta = null;
	
	try {
		
		consulta = 	" select tsde " +
					" FROM TarifaSocialDadoEconomia tsde " +
					" inner join fetch tsde.tarifaSocialCartaoTipo " +
					" inner join tsde.imovel imov " +
					" where imov.id = :idImovel ";
				
		retorno = (TarifaSocialDadoEconomia)session.createQuery(consulta)
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
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 25/03/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialCarta pesquisarTarifaSocialCarta(Integer idImovel,Integer codigoTipoCarta) 
		throws ErroRepositorioException {
	
	Session session = HibernateUtil.getSession();
	
	TarifaSocialCarta retorno = null;
	String consulta = null;
	
	try {
		
		consulta = 	" select tscr " +
					" FROM TarifaSocialCarta tscr " +
					" inner join tscr.imovel imov " +
					" inner join fetch tscr.tarifaSocialComandoCarta tscc " +
					" where imov.id = :idImovel " +
					" and tscc.dataExecucao is null " +
					" and tscc.dataGeracao is not null " +
					" and tscc.codigoTipoCarta = :codigoTipoCarta ";
				
		retorno = (TarifaSocialCarta)session.createQuery(consulta)
				.setInteger("idImovel", idImovel)
				.setInteger("codigoTipoCarta",codigoTipoCarta)
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
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarQuantidadeImoveisTarifaSocialCarta(Integer idTarifaSocialComandoCarta) 
		throws ErroRepositorioException {
	
	Session session = HibernateUtil.getSession();
	
	Integer retorno = null;
	String consulta = null;
	
	try {
		
		consulta = 	" select count(imov.id) " +
					" FROM TarifaSocialCarta tscr " +
					" inner join tscr.imovel imov " +
					" inner join tscr.tarifaSocialComandoCarta tscc " +
					" where tscc.id = :idTarifaSocialComandoCarta ";
				
		retorno = (Integer)session.createQuery(consulta)
				.setInteger("idTarifaSocialComandoCarta", idTarifaSocialComandoCarta)
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
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public void atualizarTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta, Integer qtdeImoveis)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = " UPDATE gcom.cadastro.tarifasocial.TarifaSocialComandoCarta " +
					" SET tscc_qtcartasgeradas = :qtdeImoveis, " +
					" tscc_dtprocessamento = :dataAtual " +
					" WHERE tscc_id = :idTarifaSocialComandoCarta ";

			session.createQuery(consulta)
				.setInteger("qtdeImoveis", qtdeImoveis)
				.setInteger("idTarifaSocialComandoCarta",idTarifaSocialComandoCarta)
				.setTimestamp("dataAtual", new Date())
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
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarTarifaSocialCarta(Integer idTarifaSocialComandoCarta) 
		throws ErroRepositorioException {
	
		Session session = HibernateUtil.getSession();
		
		Collection retorno = null;
		String consulta = null;
		
		try {
			
			consulta = 	" select tscr " +
						" FROM TarifaSocialCarta tscr " +
						" inner join fetch tscr.cliente clie " +
						" inner join tscr.tarifaSocialComandoCarta tscc " +
						" inner join tscr.imovel imov " +
					    " inner join imov.quadra quadra " +
					    " inner join quadra.rota rota " +
					    " inner join rota.faturamentoGrupo ftgr " +
						" where tscc.id = :idTarifaSocialComandoCarta " +
						" order by ftgr.id,imov.localidade.id,imov.setorComercial.codigo,quadra.numeroQuadra,imov.lote,imov.subLote ";
					
			retorno = (Collection)session.createQuery(consulta)
					.setInteger("idTarifaSocialComandoCarta", idTarifaSocialComandoCarta)
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
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public BigDecimal pesquisarValorContaTarifaSocialCartaDebito(Integer idTarifaSocialComandoCarta,Integer idImovel) 
		throws ErroRepositorioException {
	
	Session session = HibernateUtil.getSession();
	
	BigDecimal retorno = null;
	String consulta = null;
	
	try {
		
		consulta = 	" select sum(tscd.valorConta) " +
					" FROM TarifaSocialCartaDebito tscd" +
					" inner join tscd.imovel imov " +
					" inner join tscd.tarifaSocialComandoCarta tscc " +
					" where tscc.id = :idTarifaSocialComandoCarta and " +
					" imov.id = :idImovel ";
				
		retorno = (BigDecimal)session.createQuery(consulta)
				.setInteger("idTarifaSocialComandoCarta", idTarifaSocialComandoCarta)
				.setInteger("idImovel",idImovel)
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
	 * [UC1157] Seleciona Comando para Retirar Imóvel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 31/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarTarifaSocialComandoCarta(Integer codigoTipoCarta, String situacao,Integer numeroPagina) 
		throws ErroRepositorioException {
	
		Session session = HibernateUtil.getSession();
		
		Collection retorno = null;
		String consulta = null;
		
		try {
			
			consulta = 	" select tscc " +
						" FROM TarifaSocialComandoCarta tscc " +
						" where  ";
			
			if(situacao.equals("1")){
				consulta = consulta + "tscc.dataGeracao is null ";
				
			}else if(situacao.equals("2")){
				consulta = consulta + "tscc.dataGeracao is not null and tscc.dataExecucao is null ";
				
			}else if(situacao.equals("3")){
				consulta = consulta + "tscc.dataExecucao is not null ";
			}
			
			if (numeroPagina.intValue() == -1) {
				
				if(!codigoTipoCarta.equals(new Integer(3))){
					consulta = consulta + "and  tscc.codigoTipoCarta = :codigoTipoCarta ";
					
					retorno = (Collection)session.createQuery(consulta)
					.setInteger("codigoTipoCarta", codigoTipoCarta)
					.list();
					
				}else{
					
					retorno = (Collection)session.createQuery(consulta)
					.list();
				}
				
			} else {
				
				if(!codigoTipoCarta.equals(new Integer(3))){
					consulta = consulta + "and  tscc.codigoTipoCarta = :codigoTipoCarta ";
					
					retorno = (Collection)session.createQuery(consulta)
					.setInteger("codigoTipoCarta", codigoTipoCarta)
					.setFirstResult(10 * numeroPagina).setMaxResults(10).list();
					
				}else{
					
					retorno = (Collection)session.createQuery(consulta)
					.setFirstResult(10 * numeroPagina).setMaxResults(10).list();
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
	 * [UC1157] Seleciona Comando para Retirar Imóvel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 31/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarQtdeTarifaSocialComandoCarta(Integer codigoTipoCarta, String situacao) 
		throws ErroRepositorioException {
	
		Session session = HibernateUtil.getSession();
		
		Integer retorno = null;
		String consulta = null;
		
		try {
			
			consulta = 	" select count(tscc.id) " +
						" FROM TarifaSocialComandoCarta tscc " +
						" where  ";
			
			if(situacao.equals("1")){
				consulta = consulta + "tscc.dataGeracao is null ";
				
			}else if(situacao.equals("2")){
				consulta = consulta + "tscc.dataGeracao is not null and tscc.dataExecucao is null ";
				
			}else if(situacao.equals("3")){
				consulta = consulta + "tscc.dataExecucao is not null ";
			}
			
			if(!codigoTipoCarta.equals(new Integer(3))){
				consulta = consulta + "and  tscc.codigoTipoCarta = :codigoTipoCarta ";
				
				retorno = (Integer)session.createQuery(consulta)
				.setInteger("codigoTipoCarta", codigoTipoCarta)
				.setMaxResults(1).uniqueResult();	
				
			}else{
				
				retorno = (Integer)session.createQuery(consulta)
				.setMaxResults(1).uniqueResult();	
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
	 * [UC1157] Seleciona Comando para Retirar Imóvel da Tarifa Social
	 * [SB0003] Excluir Comando Selecionado
	 *  
	 * @author Vivianne Sousa
	 * @date 31/03/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void removerComando(Integer idTarifaSocialComandoCarta)
		throws ErroRepositorioException{

		Session session = HibernateUtil.getSession();

		try {
			String remocaoTarifaSocialCartaDebito = "delete TarifaSocialCartaDebito where tscc_id = :idTarifaSocialComandoCarta";
			session.createQuery(remocaoTarifaSocialCartaDebito).setInteger("idTarifaSocialComandoCarta", idTarifaSocialComandoCarta).executeUpdate();
			
			String remocaoTarifaSocialCarta = "delete TarifaSocialCarta where tscc_id = :idTarifaSocialComandoCarta";
			session.createQuery(remocaoTarifaSocialCarta).setInteger("idTarifaSocialComandoCarta", idTarifaSocialComandoCarta).executeUpdate();
			
			String remocaoTarifaSocialComandoCarta = "delete TarifaSocialComandoCarta where tscc_id = :idTarifaSocialComandoCarta";
			session.createQuery(remocaoTarifaSocialComandoCarta).setInteger("idTarifaSocialComandoCarta", idTarifaSocialComandoCarta).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Imóvel da Tarifa Social
	 * [SB0003] Excluir Comando Selecionado
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarImoveisTarifaSocialCarta(Integer idTarifaSocialComandoCarta, Integer codigoTipoCarta) 
		throws ErroRepositorioException {
	
	Session session = HibernateUtil.getSession();
	
	Collection retorno = null;
	String consulta = null;
	
	try {
		
		consulta = 	" select imov " +
					" FROM TarifaSocialCarta tscr " +
					" inner join tscr.imovel imov " +
					" inner join tscr.tarifaSocialComandoCarta tscc " +
					" where tscc.id = :idTarifaSocialComandoCarta ";
		
		if(codigoTipoCarta.equals(new Integer(1))){
			consulta = consulta + "and tscr.dataComparecimento is null ";
		}
				
		retorno = (Collection)session.createQuery(consulta)
				.setInteger("idTarifaSocialComandoCarta", idTarifaSocialComandoCarta)
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
	 * [UC1157] Seleciona Comando para Retirar Imóvel da Tarifa Social
	 * [SB0003] Excluir Comando Selecionado 
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarContasTarifaSocialCartaDebito(Integer idTarifaSocialComandoCarta,Integer idImovel) 
		throws ErroRepositorioException {
	
		Session session = HibernateUtil.getSession();
		
		Collection retorno = null;
		String consulta = null;
		
		try {
			
			consulta = 	" select tscd.conta.id " +
						" FROM TarifaSocialCartaDebito tscd" +
						" inner join tscd.imovel imov " +
						" inner join tscd.tarifaSocialComandoCarta tscc " +
						" where tscc.id = :idTarifaSocialComandoCarta and " +
						" imov.id = :idImovel ";
					
			retorno = (Collection)session.createQuery(consulta)
					.setInteger("idTarifaSocialComandoCarta", idTarifaSocialComandoCarta)
					.setInteger("idImovel",idImovel)
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
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * [SB0004]–Retirar Imóvel tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 04/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void retirarImovelTarifaSocial(Integer motivoExclusao, Integer idImovel)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = " UPDATE gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia " +
					" SET tsde_dtexclusao = :dataExclusao, " +
					" etsm_id = :motivoExclusao, " +
					" tsde_dtrevisao = null, " +
					" rtsm_id = null " +
					" WHERE imov_id = :idImovel ";

			session.createQuery(consulta)
				.setInteger("motivoExclusao", motivoExclusao)
				.setInteger("idImovel",idImovel)
				.setTimestamp("dataExclusao", new Date())
				.executeUpdate();
			
//			consulta = " UPDATE gcom.cadastro.imovel.Imovel " +
//			" SET iper_id = :imovelPerfil " +
//			" WHERE imov_id = :idImovel ";
//
//			session.createQuery(consulta)
//				.setInteger("imovelPerfil", ImovelPerfil.NORMAL)
//				.setInteger("idImovel",idImovel)
//				.executeUpdate();
			
			
			consulta = " UPDATE gcom.cadastro.tarifasocial.TarifaSocialCarta " +
			" SET tscr_icexcluidotarifasocial = :indicadorExcluidoTarifaSocial " +
			" WHERE imov_id = :idImovel ";

			session.createQuery(consulta)
				.setShort("indicadorExcluidoTarifaSocial", ConstantesSistema.SIM)
				.setInteger("idImovel",idImovel)
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
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 04/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void atualizarDataExecucaoTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = " UPDATE gcom.cadastro.tarifasocial.TarifaSocialComandoCarta " +
					" SET tscc_dtexecucao = :dataExecucao " +
					" WHERE tscc_id = :idTarifaSocialComandoCarta ";

			session.createQuery(consulta)
				.setInteger("idTarifaSocialComandoCarta",idTarifaSocialComandoCarta)
				.setTimestamp("dataExecucao", new Date())
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
	 * [UC1157] Seleciona Comando para Retirar Imóvel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarImoveisTarifaSocial(Integer idLocalidade) 
		throws ErroRepositorioException {
	
		Session session = HibernateUtil.getSession();
		
		Collection retorno = null;
		String consulta = null;
		
		try {
			
			consulta = "SELECT imov " 
				+" FROM Imovel as imov "
				+" INNER JOIN imov.localidade as loca " 
				+" INNER JOIN imov.ligacaoAgua as lagu "
				+" WHERE imov.imovelPerfil.id = :imovelPerfil "
				+" AND loca.id = :idLocalidade "
				+" AND lagu.hidrometroInstalacaoHistorico is not null " 
				+" AND lagu.id = imov.id " 
				+" AND ( imov.indicadorExclusao IS NULL or imov.indicadorExclusao != :indicadorExclusao ) " ;

			retorno = (Collection)session.createQuery(consulta)
					.setInteger("idLocalidade",idLocalidade)
					.setInteger("imovelPerfil", ImovelPerfil.TARIFA_SOCIAL)
					.setShort("indicadorExclusao", ConstantesSistema.SIM)
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
	 * [UC1157] Seleciona Comando para Retirar Imóvel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 05/04/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialComandoCarta pesquisarTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta) 
		throws ErroRepositorioException {
	
		Session session = HibernateUtil.getSession();
		
		TarifaSocialComandoCarta retorno = null;
		String consulta = null;
		
		try {
			
			consulta = 	" select tscc " +
						" FROM TarifaSocialComandoCarta tscc " +
						" where  tscc.id = :idTarifaSocialComandoCarta";
			
		
			retorno = (TarifaSocialComandoCarta)session.createQuery(consulta)
			.setInteger("idTarifaSocialComandoCarta", idTarifaSocialComandoCarta)
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
	 * [UC1164] Gerar Resumo dos Imóveis Excluídos da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarQtdeImoveisExcluidostarifaSocial(
			Integer codigoTipoCarta,Integer idGerencia,Integer idUnidade,Integer idLocalidade, 
			Integer referenciaInicial, Integer refereciaFinal)throws ErroRepositorioException {
	
		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta = null;
		Query query = null;
		Map parameters = new HashMap();
		
		try {
			
			consulta = 	" select count(tscr.imovel.id)," +//0
						" tscr.gerenciaRegional.id," +//1
						" tscr.unidadeNegocio.id," +//2
						" tscr.localidade.id," +//3
						" tscr.codigoMotivo, " +//4
						" tscr.gerenciaRegional.nome," +//5
						" tscr.unidadeNegocio.nome," +//6
						" tscr.localidade.descricao " +//7
						" FROM TarifaSocialCarta tscr " +
						" inner join tscr.tarifaSocialComandoCarta tscc " +
						" where tscc.codigoTipoCarta = :codigoTipoCarta " +
						" and  tscc.dataExecucao >= to_date('" 
						+ Util.formatarDataComTracoAAAAMMDD(Util.gerarDataInicialApartirAnoMesRefencia(referenciaInicial))
						+ "','YYYY-MM-DD') " +
						" and  tscc.dataExecucao <= to_date('" 
						+ Util.formatarDataComTracoAAAAMMDD(Util.gerarDataApartirAnoMesRefencia(refereciaFinal))
						+ "','YYYY-MM-DD') ";
			
			parameters.put("codigoTipoCarta", codigoTipoCarta);
			
//			if(referenciaInicial != null){
//				consulta = consulta + " and tscc.dataExecucao >= :referenciaInicial ";
//				parameters.put("referenciaInicial", Util.gerarDataInicialApartirAnoMesRefencia(referenciaInicial));
//			}
//
//			if(refereciaFinal != null){
//				consulta = consulta + " and tscc.dataExecucao <= :refereciaFinal ";
//				parameters.put("refereciaFinal", Util.gerarDataApartirAnoMesRefencia(refereciaFinal));
//			}
			
			if(idGerencia != null){
				consulta = consulta + " and tscr.gerenciaRegional.id = :idGerencia"; 
				parameters.put("idGerencia", idGerencia);
			}
			if(idUnidade != null){
				consulta = consulta + " and tscr.unidadeNegocio.id = :idUnidade"; 
				parameters.put("idUnidade", idUnidade);
			}
			if(idLocalidade != null){
				consulta = consulta + " and tscr.localidade.id = :idLocalidade"; 
				parameters.put("idLocalidade", idLocalidade);
			}
					
			consulta = consulta + 
			" group by tscr.gerenciaRegional.id,tscr.unidadeNegocio.id,tscr.localidade.id,tscr.codigoMotivo,tscr.gerenciaRegional.nome,tscr.unidadeNegocio.nome,tscr.localidade.descricao "+
			" order by tscr.gerenciaRegional.id,tscr.unidadeNegocio.id,tscr.localidade.id,tscr.codigoMotivo,tscr.gerenciaRegional.nome,tscr.unidadeNegocio.nome,tscr.localidade.descricao ";
			
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
			
			retorno = (Collection)query.list();
		
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
	 * [UC1164] Gerar Resumo dos Imóveis Excluídos da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarQtdeImoveisExcluidostarifaSocial(Integer referenciaInicial, Integer refereciaFinal,
			Integer codigoTipoCarta,RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper helper) 
		throws ErroRepositorioException {
	
		Session session = HibernateUtil.getSession();
		
		Integer retorno = null;
		String consulta = null;

		try {
			
			consulta = 	" select count(tscr.imovel.id)" +
						" FROM TarifaSocialCarta tscr " +
						" inner join tscr.tarifaSocialComandoCarta tscc " +
						" where tscc.codigoTipoCarta = :codigoTipoCarta " +
						" and tscr.indicadorExcluidoTarifaSocial = :indicadorExcluidoTarifaSocial " +
						" and tscr.localidade.id = :idLocalidade " +
						" and tscr.codigoMotivo = :motivo " +
//						" and tscc.dataExecucao >= :referenciaInicial " +
//						" and tscc.dataExecucao <= :refereciaFinal";
						" and  tscc.dataExecucao >= to_date('" 
						+ Util.formatarDataComTracoAAAAMMDD(Util.gerarDataInicialApartirAnoMesRefencia(referenciaInicial))
						+ "','YYYY-MM-DD') " +
						" and  tscc.dataExecucao <= to_date('" 
						+ Util.formatarDataComTracoAAAAMMDD(Util.gerarDataApartirAnoMesRefencia(refereciaFinal))
						+ "','YYYY-MM-DD') ";
			
			retorno = (Integer)session.createQuery(consulta)
					.setInteger("codigoTipoCarta", codigoTipoCarta)
					.setShort("indicadorExcluidoTarifaSocial", ConstantesSistema.SIM)
					.setInteger("idLocalidade", helper.getIdLocalidade())
					.setInteger("motivo", helper.getMotivoExclusao())
//					.setDate("referenciaInicial", Util.gerarDataApartirAnoMesRefencia(referenciaInicial))
//					.setDate("refereciaFinal", Util.gerarDataApartirAnoMesRefencia(refereciaFinal))
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
	 * [UC1164] Gerar Resumo dos Imóveis Excluídos da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 08/04/2011
	 * 
	 * @throws ControladorException
	 */
	public String pesquisarDescricaoMotivoCarta(Integer idCodigoMotivo) 
		throws ErroRepositorioException {
	
		Session session = HibernateUtil.getSession();
		
		String retorno = null;
		String consulta = null;

		try {
			
			consulta = 	" select tsmc.descricaoMotivoCarta " +
						" FROM TarifaSocialMotivoCarta tsmc " +
						" where tsmc.id = :idCodigoMotivo ";
			
			retorno = (String)session.createQuery(consulta)
					.setInteger("idCodigoMotivo", idCodigoMotivo)
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
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 08/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarQtdeTarifaSocialDadoEconomia(Integer idtarifaSocialExclusaoMotivo,
			Integer referenciaInicial, Integer refereciaFinal,Integer idGerencia,Integer idUnidade,Integer idLocalidade) 
		throws ErroRepositorioException {
	
	Session session = HibernateUtil.getSession();
	
	Collection retorno = null;
	String consulta = null;
	
	Query query = null;
	Map parameters = new HashMap();
	
	try {
		
		consulta = 	" select count(distinct imov.id), " +//0
					" greg.id," +//1
					" uneg.id," +//2
					" loca.id," +//3
					" greg.nome," +//4
					" uneg.nome," +//5
					" loca.descricao " +//6
					" FROM TarifaSocialDadoEconomia tsde " +
					" inner join tsde.imovel imov " +
					" left join imov.localidade loca " +
					" left join loca.gerenciaRegional greg " +
					" left join loca.unidadeNegocio uneg " +
					" where tsde.tarifaSocialExclusaoMotivo.id = :idtarifaSocialExclusaoMotivo " +
//					" and  tsde.dataExclusao >= :referenciaInicial " +
//					" and tsde.dataExclusao <= :refereciaFinal" ;
					" and  tsde.dataExclusao >= to_date('" 
					+ Util.formatarDataComTracoAAAAMMDD(Util.gerarDataInicialApartirAnoMesRefencia(referenciaInicial))
					+ "','YYYY-MM-DD') " +
					" and  tsde.dataExclusao <= to_date('" 
					+ Util.formatarDataComTracoAAAAMMDD(Util.gerarDataApartirAnoMesRefencia(refereciaFinal))
					+ "','YYYY-MM-DD') ";
		
		parameters.put("idtarifaSocialExclusaoMotivo", idtarifaSocialExclusaoMotivo);
//		parameters.put("referenciaInicial", Util.gerarDataInicialApartirAnoMesRefencia(referenciaInicial));
//		parameters.put("refereciaFinal", Util.gerarDataApartirAnoMesRefencia(refereciaFinal));

		if(idGerencia != null){
			consulta = consulta + " and greg.id = :idGerencia"; 
			parameters.put("idGerencia", idGerencia);
		}
		if(idUnidade != null){
			consulta = consulta + " and uneg.id = :idUnidade"; 
			parameters.put("idUnidade", idUnidade);
		}
		if(idLocalidade != null){
			consulta = consulta + " and loca.id = :idLocalidade"; 
			parameters.put("idLocalidade", idLocalidade);
		}
				
		consulta = consulta + 
		" group by greg.id, uneg.id, loca.id, greg.nome, uneg.nome, loca.descricao " +
		" order by greg.id, uneg.id, loca.id, greg.nome, uneg.nome, loca.descricao ";
		
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
		
		retorno = (Collection)query.list();
	
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
	 * [UC1161] Retirar Imóvel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 11/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void atualizarDadosFaturamentoSituacaoHistorico(Integer idImovel,
			Integer referenciaFaturamentoGrupo,String observacaoRetira)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = " UPDATE gcom.faturamento.FaturamentoSituacaoHistorico " +
					" SET ftsh_tmultimaalteracao = :ultimaAlteracao , " +
					" ftsh_amfaturamentoretirada = :referenciaFaturamentoGrupo , " +
					" ftsh_dsobservacaoretira = :observacaoRetira , " +
					" usur_idretira = :idUsuario " +
					" WHERE imov_id = :idImovel and " +
					" ftsh_amfaturamentoretirada is null ";
			
			session.createQuery(consulta)
				.setInteger("idImovel",idImovel)
				.setTimestamp("ultimaAlteracao", new Date())
				.setInteger("idUsuario",(Usuario.USUARIO_BATCH).getId())
				.setInteger("referenciaFaturamentoGrupo",referenciaFaturamentoGrupo)
				.setString("observacaoRetira",observacaoRetira)
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
	 * [UC1161] Retirar Imóvel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 11/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void atualizarDadosImovel(Integer idImovel, Integer referencia)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = " UPDATE gcom.cadastro.imovel.Imovel " +
					" SET iper_id = :imovelPerfil , " +
					" ftst_id = null , " +
					" cstf_id = :consumoTarifa, " +
					" imov_amrefexclusaotarifasocial = :referencia" +
					" WHERE imov_id = :idImovel ";
			
			session.createQuery(consulta)
				.setInteger("idImovel",idImovel)
				.setInteger("imovelPerfil",ImovelPerfil.NORMAL)
				.setInteger("consumoTarifa",ConsumoTarifa.CONSUMO_NORMAL)
				.setInteger("referencia",referencia)
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
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * [SB0008]-Verificar carta para o comando
	 *  
	 * @author Vivianne Sousa
	 * @date 19/04/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void removerCartasComando(Integer idTarifaSocialComandoCarta, 
			Integer idLocalidade, Integer tipoCarta)throws ErroRepositorioException{

		Session session = HibernateUtil.getSession();

		try {
			
			if(tipoCarta.equals(new Integer("2"))){
				String consulta = " select imov.id " +
				" FROM TarifaSocialCarta tscr " +
				" inner join tscr.imovel imov " +
				" where tscr.tarifaSocialComandoCarta.id = :idTarifaSocialComandoCarta " +
				" and tscr.localidade.id = :idLocalidade ";
			
				Collection colecaoIdsImovel = (Collection)session.createQuery(consulta)
						.setInteger("idTarifaSocialComandoCarta", idTarifaSocialComandoCarta)
						.setInteger("idLocalidade", idLocalidade)
						.list();	
				
				if(colecaoIdsImovel != null && !colecaoIdsImovel.isEmpty()){
					String remocaoTarifaSocialCartaDebito = "delete TarifaSocialCartaDebito " +
					"where tscc_id = :idTarifaSocialComandoCarta and " +
					"imov_id in (:colecaoIdsImovel) ";
					session.createQuery(remocaoTarifaSocialCartaDebito)
					.setParameterList("colecaoIdsImovel",colecaoIdsImovel).executeUpdate();
				}
			}
			
			String remocaoTarifaSocialCarta = "delete TarifaSocialCarta " +
					"where tscc_id = :idTarifaSocialComandoCarta " +
					" and loca_id = :idLocalidade ";
			session.createQuery(remocaoTarifaSocialCarta)
			.setInteger("idTarifaSocialComandoCarta", idTarifaSocialComandoCarta)
			.setInteger("idLocalidade", idLocalidade).executeUpdate();
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0352] Emitir Contas e Cartas
	 * [SB0017] – Obter Mensagem da Conta em 3 Partes
	 * 
	 * @author Vivianne Sousa
	 * @date 29/04/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarAnoMesExclusaoTarifaSocialImovel(Integer idImovel) 
		throws ErroRepositorioException {
	
		Session session = HibernateUtil.getSession();
		
		Integer retorno = null;
		String consulta = null;

		try {
			
			consulta = 	" select imov.anoMesExclusaoTarifaSocial " +
						" FROM Imovel imov " +
						" where imov.id = :idImovel ";
			
			retorno = (Integer)session.createQuery(consulta)
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
	 *  [UC1168] Encerrar Comandos de Cobrança por Empresa
	 *
	 * @author Mariana Victor
	 * @created 10/05/2011
	 */
	public void retirarSituacaoCobrancaImovel(Integer idImovel, Integer idCobrancaSituacao)
		throws ErroRepositorioException {

		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {

				consulta = "update gcom.cadastro.imovel.Imovel set "

						+ "cbst_id = null, imov_tmultimaalteracao = :ultimaAlteracao " 
						
						+ "where imov_id = :idImovel ";

				session.createQuery(consulta).
				   setTimestamp("ultimaAlteracao",new Date()).
				   setInteger("idImovel",idImovel).
				   executeUpdate();
				
				
				consulta =  " update gcom.cadastro.imovel.ImovelCobrancaSituacao "
					+ " set iscb_dtretiradacobranca = :data" 
					+ " where imov_id = :imovel and cbst_id = :idCobrancaSituacao " 
					+ " and iscb_dtretiradacobranca is null ";
				
				session.createQuery(consulta)
					.setDate("data", new Date())
					.setInteger("imovel", idImovel)
					.setInteger("idCobrancaSituacao", idCobrancaSituacao)
					.executeUpdate();


		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}


	/**
	 * [UC1169] Movimentar Ordens de Serviço de Cobrança por Resultado
	 * 
	 * Emitir OS de Empresa de Cobrança - 
	 * 
	 * @author Mariana Victor
	 * @data 18/05/2011
	 */
	public Collection<Integer[]> pesquisarIdsImoveis(String[] idsOrdemServico) throws ErroRepositorioException {
		
		Collection<Integer[]> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = 
				    " select orse.orse_id as idOS, orse.imov_id as idImovel "
					+ " from atendimentopublico.ordem_servico orse "
					+ " where orse.orse_id in( ";

			for (int i = 0; i < idsOrdemServico.length; i++) {
				consulta += idsOrdemServico[i] + ", ";
			}
			//remove a virgula do final e coloca o parêntese
			consulta = consulta.substring(0, consulta.length() - 2) + ")";
			
			consulta = consulta + " group by orse.orse_id, orse.imov_id";
			
			retorno = session.createSQLQuery(consulta)
			.addScalar("idOS", Hibernate.INTEGER)
			.addScalar("idImovel", Hibernate.INTEGER).list();
			
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return retorno;
		
	}


	/**
	 * [UC0000] Obter Consumo Não Medido por Parâmetro
	 * [FS0001] - Verificar "Pontos de Utilização" Não Informado.
	 * 
	 * @author Mariana Victor
	 * @date 23/05/2011
	 * 
	 * @throws ControladorException
	 */
	public Short pesquisarPontosUtilizacaoImovel(Integer idImovel) 
		throws ErroRepositorioException {
	
	
	
		Session session = HibernateUtil.getSession();
		
		Short retorno = null;
		String consulta = null;

		try {
			
			consulta = 	" select imov.imov_nnpontosutilizacao as pontosUtilizacao " +
						" FROM cadastro.imovel imov " +
						" where imov.imov_id = :idImovel ";
			
			retorno = (Short)session.createSQLQuery(consulta)
					.addScalar("pontosUtilizacao", Hibernate.SHORT)
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
	 * [UC0000] Obter Consumo Não Medido por Parâmetro
	 * [FS0002] - Verificar “Número de Moradores” Não Informado.
	 * 
	 * @author Mariana Victor
	 * @date 23/05/2011
	 * 
	 * @throws ControladorException
	 */
	public Short pesquisarNumeroMoradoresImovel(Integer idImovel) 
		throws ErroRepositorioException {
	
		Session session = HibernateUtil.getSession();
		
		Short retorno = null;
		String consulta = null;

		try {
			
			consulta = 	" select imov.imov_nnmorador as numeroMoradores " +
						" FROM cadastro.imovel imov " +
						" where imov.imov_id = :idImovel ";
			
			retorno = (Short)session.createSQLQuery(consulta)
					.addScalar("numeroMoradores", Hibernate.SHORT)
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
	 * [UC1174] Gerar Relatório Imóveis com Doações
	 * 
	 * Quantidade de imoveis com doações - 
	 * 
	 * @author Erivan Sousa	
	 * @data 13/06/2011
	 */
	public Integer pesquisarQuantidadeImoveisDoacoes(GerarRelatorioImoveisDoacoesHelper filtro)throws ErroRepositorioException{
		Integer quantidade;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		Map parametros = new HashMap();
		Query query = null;
		
		try{
			consulta = "SELECT COUNT(*) FROM gcom.cadastro.imovel.ImovelDoacao imov " 
				+ "LEFT JOIN imov.usuarioCancelamento usurCancel " 
				+ "INNER JOIN imov.usuarioAdesao usuAdesao "
				+ "WHERE 1=1 ";
			
			//Identificador da Entidade Beneficente
			if(filtro.getIdEntidade() != null && filtro.getIdEntidade() != ConstantesSistema.NUMERO_NAO_INFORMADO ){
				consulta += "and imov.entidadeBeneficente = :idEntidade " ; 
				parametros.put("idEntidade", filtro.getIdEntidade());
			}
			//Identificador d Usuário de Adesão
			if(filtro.getLoginUsuarioAdesao() != null){
				consulta += "and usuAdesao.login = :loginUsuarioAdesao ";
				parametros.put("loginUsuarioAdesao", filtro.getLoginUsuarioAdesao());
			}
			//Identificador do Usuário de Cancelmaneto
			if(filtro.getLoginUsuarioCancelamento() != null){
				consulta += "and usurCancel.login = :loginUsuarioCancelamento ";
				parametros.put("loginUsuarioCancelamento", filtro.getLoginUsuarioCancelamento());
			}
			//Datas do período de adesão
			if(filtro.getDataAdesaoInicio() != null && filtro.getDataAdesaoFinal() != null){
				consulta += "and imov.dataAdesao between :dataAdesaoInicio and :dataAdesaoFinal ";
				
				parametros.put("dataAdesaoInicio", filtro.getDataAdesaoInicio());
				parametros.put("dataAdesaoFinal", filtro.getDataAdesaoFinal());
			}
			//Datas do período de cancelamento
			if(filtro.getDataCancelamentoInicio() != null && filtro.getDataCancelamentoFinal() != null){
				consulta += "and imov.dataCancelamento between :dataCancelamentoInicio and :dataCancelamentoFinal ";
				
				parametros.put("dataCancelamentoInicio", filtro.getDataCancelamentoInicio());
				parametros.put("dataCancelamentoFinal", filtro.getDataCancelamentoFinal());
			}
			//Ano/Mes referencia de inicio da doacao
			if(filtro.getRefInicioDoacaoInicio() != null && filtro.getRefInicioDoacaoFinal() != null){
				consulta += "and imov.anoMesReferenciaInicial between :refInicioInicial and :refInicioFinal ";
				
				parametros.put("refInicioInicial", filtro.getRefInicioDoacaoInicio());
				parametros.put("refInicioFinal", filtro.getRefInicioDoacaoFinal());
			}
			//Ano/Mes referencia fim da doação
			if(filtro.getRefFimDoacaoInicio() != null && filtro.getRefFimDoacaoFinal() != null){
				consulta += "and imov.anoMesReferenciaFinal between :refFimInicial and :refFimFinal ";
				
				parametros.put("refFimInicial", filtro.getRefFimDoacaoInicio());
				parametros.put("refFimFinal", filtro.getRefFimDoacaoFinal());
			}
			
			query = session.createQuery(consulta);
			
			Set set = parametros.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				
				if (parametros.get(key) instanceof Set) {
					Set setList = (Set) parametros.get(key);
					query.setParameterList(key, setList);
				} else if (parametros.get(key) instanceof Collection) {
					Collection collection = (Collection) parametros.get(key);
					query.setParameterList(key, collection);
				} else if (parametros.get(key) instanceof Integer[]) {
					Integer[] collection = (Integer[]) parametros.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parametros.get(key));
				}
			}
			quantidade = (Integer)query.uniqueResult();
			
		}catch(HibernateException e){
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally {
			HibernateUtil.closeSession(session);
		}	
		
		
		return quantidade;
	}
	
	/**
	 * [UC1174] Gerar Relatório Imóveis com Doações
	 * 
	 * Pesquisar Imoveis com Doações - 
	 * 
	 * @author Erivan Sousa	
	 * @data 13/06/2011
	 */
	public Collection pesquisarImoveisDoacoes(GerarRelatorioImoveisDoacoesHelper filtro)throws ErroRepositorioException{
		Collection imoveis;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		Map parametros = new HashMap();
		Query query = null;
		
		try{
			
			consulta = "SELECT " 
				+ "imovelDoacao.entidadeBeneficente.cliente.nome, " 
				+ "imovel.id, " 
				+ "imovelDoacao.dataAdesao, " 
				+ "imovelDoacao.dataCancelamento, " 
				+ "usuAdesao.nomeUsuario, " 
				+ "usurCancel.nomeUsuario, " 
				+ "imovelDoacao.anoMesReferenciaInicial, " 
				+ "imovelDoacao.anoMesReferenciaFinal, " 
				+ "imovelDoacao.valorDoacao, "
				+ "clienteUsuario.nome "
				+ "FROM gcom.cadastro.imovel.ImovelDoacao imovelDoacao " 
				+ "LEFT JOIN imovelDoacao.usuarioCancelamento usurCancel " 
				+ "INNER JOIN imovelDoacao.usuarioAdesao usuAdesao "
				+ "INNER JOIN imovelDoacao.imovel imovel "
				+ "INNER JOIN imovel.clienteImoveis clienteImoveis WITH (clienteImoveis.clienteRelacaoTipo.id = :relacaoTipo) "
				+ "and clienteImoveis.dataFimRelacao is null "
				+ "INNER JOIN clienteImoveis.cliente clienteUsuario "
				+ "WHERE 1=1 ";
			
			parametros.put("relacaoTipo", ClienteRelacaoTipo.USUARIO);
			
			//Identificador da Entidade Beneficente
			if(filtro.getIdEntidade() != null && filtro.getIdEntidade() != ConstantesSistema.NUMERO_NAO_INFORMADO ){
				consulta += "and imovelDoacao.entidadeBeneficente.id = :idEntidade " ; 
				parametros.put("idEntidade", filtro.getIdEntidade());
			}
			//Identificador d Usuário de Adesão
			if(filtro.getLoginUsuarioAdesao() != null){
				consulta += "and usuAdesao.login = :loginUsuarioAdesao ";
				parametros.put("loginUsuarioAdesao", filtro.getLoginUsuarioAdesao());
			}
			//Identificador do Usuário de Cancelmaneto
			if(filtro.getLoginUsuarioCancelamento() != null){
				consulta += "and usurCancel.login = :loginUsuarioCancelamento ";
				parametros.put("loginUsuarioCancelamento", filtro.getLoginUsuarioCancelamento());
			}
			//Datas do período de adesão
			if(filtro.getDataAdesaoInicio() != null && filtro.getDataAdesaoFinal() != null){
				consulta += "and imovelDoacao.dataAdesao between :dataAdesaoInicio and :dataAdesaoFinal ";
				
				parametros.put("dataAdesaoInicio", filtro.getDataAdesaoInicio());
				parametros.put("dataAdesaoFinal", filtro.getDataAdesaoFinal());
			}
			//Datas do período de cancelamento
			if(filtro.getDataCancelamentoInicio() != null && filtro.getDataCancelamentoFinal() != null){
				consulta += "and imovelDoacao.dataCancelamento between :dataCancelamentoInicio and :dataCancelamentoFinal ";
				
				parametros.put("dataCancelamentoInicio", filtro.getDataCancelamentoInicio());
				parametros.put("dataCancelamentoFinal", filtro.getDataCancelamentoFinal());
			}
			//Ano/Mes referencia de inicio da doacao
			if(filtro.getRefInicioDoacaoInicio() != null && filtro.getRefInicioDoacaoFinal() != null){
				consulta += "and imovelDoacao.anoMesReferenciaInicial between :refInicioInicial and :refInicioFinal ";
				
				parametros.put("refInicioInicial", filtro.getRefInicioDoacaoInicio());
				parametros.put("refInicioFinal", filtro.getRefInicioDoacaoFinal());
			}
			//Ano/Mes referencia fim da doação
			if(filtro.getRefFimDoacaoInicio() != null && filtro.getRefFimDoacaoFinal() != null){
				consulta += "and imovelDoacao.anoMesReferenciaFinal between :refFimInicial and :refFimFinal ";
				
				parametros.put("refFimInicial", filtro.getRefFimDoacaoInicio());
				parametros.put("refFimFinal", filtro.getRefFimDoacaoFinal());
			}
			consulta += " order by imovelDoacao.entidadeBeneficente.id, imovel.id , imovelDoacao.dataAdesao ";
			
			
			query = session.createQuery(consulta);
			
			Set set = parametros.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				
				if (parametros.get(key) instanceof Set) {
					Set setList = (Set) parametros.get(key);
					query.setParameterList(key, setList);
				} else if (parametros.get(key) instanceof Collection) {
					Collection collection = (Collection) parametros.get(key);
					query.setParameterList(key, collection);
				} else if (parametros.get(key) instanceof Integer[]) {
					Integer[] collection = (Integer[]) parametros.get(key);
					query.setParameterList(key, collection);
				} else if (parametros.get(key) instanceof Date) {
					Date data = (Date) parametros.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parametros.get(key));
				}
			}
			imoveis = query.list();
			
		}catch(HibernateException e){
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally {
			HibernateUtil.closeSession(session);
		}	
		
		
		return imoveis;
	}
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 09/08/2011
	 */
	public void atualizarSituacaoEspecialFaturamentoImovel(Integer idImovel,
			Integer idFaturamentoSituacaoTipo, Integer idFaturamentoSituacaoMotivo)	throws ErroRepositorioException {

		String consulta = "";
		Session session = HibernateUtil.getSession();

		try {
			consulta = "update gcom.cadastro.imovel.Imovel set "
					+ "ftst_id = :idFaturamentoSituacaoTipo," 
					+ "ftsm_id = :idFaturamentoSituacaoMotivo," 
					+ "imov_tmultimaalteracao = :ultimaAlteracao " 
					+ "where imov_id = :idImovel";

			session.createQuery(consulta)
			.setInteger("idImovel",idImovel)
			.setInteger("idFaturamentoSituacaoTipo",idFaturamentoSituacaoTipo)
			.setInteger("idFaturamentoSituacaoMotivo",idFaturamentoSituacaoMotivo)
			.setTimestamp("ultimaAlteracao",new Date())
			.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * Método que retorna o id do imóvel área comum
	 * 
	 * [UC0098] Manter Vínculos de Imóveis para Rateio de Consumo
	 * [SB0001] Atualizar Tipo de Rateio
	 * 
	 * @author Magno Gouveia
	 * @since 17/08/2011
	 * 
	 * @param idImovelCondomio
	 * @return imovel.id
	 */
	public Integer pesquisarImovelAreaComum(Integer idImovelCondominio) throws ErroRepositorioException {

		Integer retorno;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "SELECT imov.id " 
					 + "FROM Imovel imov " // imovel
					 + "  LEFT JOIN imov.imovelCondominio AS imovelCondominio " // imovel condominio
					 + "WHERE imovelCondominio.id = :idImovelCondominio " 
					 + "  AND imov.indicadorImovelAreaComum = :indicadorImovelAreaComum";
			
			retorno = (Integer) session.createQuery(consulta)
							 		   .setInteger("idImovelCondominio", idImovelCondominio)
						 			   .setShort("indicadorImovelAreaComum", ConstantesSistema.SIM)
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
	 * <p>
	 * [UC0098] Manter Vínculos de Imóveis para Rateio Comum
	 * </p>
	 * <p>
	 * [SB0001] - [FS0012] - Caso a matrícula do imóvel para área comum
	 * informada não exista na tabela IMOVEL, exibir a mensagem "Matrícula
	 * inexistente no cadastro" e retornar para o passo correspondente no fluxo
	 * principal
	 * </p>
	 * 
	 * @author Magno Gouveia
	 * @since 19/08/2011
	 * @param idImovel
	 * @return
	 */
	public Short verificarExistenciaDoImovel(Integer idImovel) throws ErroRepositorioException {

		Short retorno = null;

		String consulta;

		Session session = HibernateUtil.getSession();

		try {
			consulta = "SELECT imovel.indicadorExclusao "			
					 + "FROM Imovel imovel "
					 + "WHERE imovel.id = :idImovel";
			
			retorno = (Short)session.createQuery(consulta)
							 		.setInteger("idImovel", idImovel)
						 			.setMaxResults(1)
						 			.uniqueResult();	
							 			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * Método que retorna o id do imóvel condominio
	 * 
	 * [UC0098] Manter Vínculos de Imóveis para Rateio de Consumo
	 * [SB0001] Atualizar Tipo de Rateio
	 * 
	 * @author Magno Gouveia
	 * @since 17/08/2011
	 * 
	 * @param idImovel
	 * @return imovel.id
	 */
	public Integer pesquisarImovelCondominio(Integer idImovel) throws ErroRepositorioException {

		Integer retorno;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "SELECT imov.imovelCondominio.id " 
					 + "FROM Imovel imov "
					 + "WHERE imov.id = :idImovel";
			
			retorno = (Integer) session.createQuery(consulta)
							 		   .setInteger("idImovel", idImovel)
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
	 * Método que retorna o id do imóvel condominio
	 * 
	 * [UC0098] Manter Vínculos de Imóveis para Rateio de Consumo
	 * [SB0001] Atualizar Tipo de Rateio
	 * 
	 * @author Magno Gouveia
	 * @since 19/08/2011
	 * 
	 * @param idImovel, indicadorImovelAreaComum
	 */
	public void atualizarIndicadorImovelAreaComumDoImovel(Integer idImovel,
			Short indicadorImovelAreaComum)	throws ErroRepositorioException {

		String consulta = "";
		Session session = HibernateUtil.getSession();

		try {
			consulta = "UPDATE gcom.cadastro.imovel.Imovel SET "
					 +	"imov_icimovelareacomum = :indicadorImovelAreaComumDoImovel, " 
					 +	"imov_tmultimaalteracao = :ultimaAlteracao " 
					 + "WHERE imov_id = :idImovel";

			session.createQuery(consulta).setInteger("idImovel", idImovel)
										 .setInteger("indicadorImovelAreaComumDoImovel", indicadorImovelAreaComum)
										 .setTimestamp("ultimaAlteracao", new Date())
										 .executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * @param idImovel
	 * 
	 * @author Wellington Rocha
     * @date 21/03/2012
	 * @exception ErroRepositorioException
	 */
	public Collection<ImovelRamoAtividade> pesquisarRamoAtividadeDoImovel(Integer idImovel) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Collection<ImovelRamoAtividade> colecaoRamoAtividadeImovel = new ArrayList<ImovelRamoAtividade>();

		try {
			consulta = "from ImovelRamoAtividade ramoAtividade "
					+ "where ramoAtividade.comp_id.imovel.id = :idImovel";

			colecaoRamoAtividadeImovel = session.createQuery(consulta)
					.setInteger("idImovel", idImovel)
					.list();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return colecaoRamoAtividadeImovel;
	}
	
	public ImovelControleAtualizacaoCadastral pesquisarImovelControleAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {

			String consulta = "SELECT icac "
					+ "FROM ImovelControleAtualizacaoCadastral icac "
					+ "LEFT JOIN FETCH icac.imovel imovel "
					+ "LEFT JOIN FETCH icac.situacaoAtualizacaoCadastral situacao "
					+ "LEFT JOIN FETCH icac.cadastroOcorrencia cadastroOcorrencia "
					+ "WHERE icac.imovel.id = :idImovel ";

			return (ImovelControleAtualizacaoCadastral) session.createQuery(consulta)
					.setInteger("idImovel", idImovel).uniqueResult();
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar controle de atualizacao cadastral");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public Collection<Integer> pesquisarIdImoveisAprovados() throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			String consulta = "SELECT imovel.id "
					+ "FROM ImovelControleAtualizacaoCadastral icac "
					+ "INNER JOIN icac.imovel imovel "
					+ "INNER JOIN icac.situacaoAtualizacaoCadastral situacao "
					+ "WHERE situacao.id = :idSituacao ";

			return (Collection<Integer>) session.createQuery(consulta)
					.setInteger("idSituacao", SituacaoAtualizacaoCadastral.APROVADO).list();
		}catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public LogradouroTipo pesquisarTipoLogradouro(Integer idTipoLogradouro) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		try {
			String consulta = "SELECT tipo "
					+ "FROM LogradouroTipo tipo "
					+ "WHERE tipo.id = :idTipo ";

			return (LogradouroTipo) session.createQuery(consulta)
					.setInteger("idTipo", idTipoLogradouro).uniqueResult();
		}catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public Logradouro pesquisarLogradouro(Integer codigoLogradouro) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		try {
			String consulta = "SELECT logr "
					+ "FROM Logradouro logr "
					+ "WHERE logr.id = :codigo ";
			
			return (Logradouro) session.createQuery(consulta)
					.setInteger("codigo", codigoLogradouro).uniqueResult();
		}catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public Integer pesquisarLogradouroImovelAtualizacaoCadastral(Integer matriculaImovel) throws ErroRepositorioException {
        Session session = HibernateUtil.getSession();
        try {
                String consulta = " SELECT imovel.idLogradouro "
                                + " FROM ImovelAtualizacaoCadastral imovel "
                                + " WHERE imovel.idImovel = :idImovel ";
                return (Integer) session.createQuery(consulta).setInteger("idImovel", matriculaImovel).uniqueResult();
        } catch (HibernateException e) {
                throw new ErroRepositorioException("Erro no Hibernate");
        } finally {
                HibernateUtil.closeSession(session);
        }
	}
	
	public ImovelCobrancaSituacao obterImovelCobrancaSituacao(Integer idImovelSituacaoCobranca) throws ErroRepositorioException {
		ImovelCobrancaSituacao retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select iscb "
					+ "from ImovelCobrancaSituacao iscb "
					+ "where iscb.id = :idImovelSituacaoCobranca ";

			retorno = (ImovelCobrancaSituacao) session.createQuery(consulta)
					.setInteger("idImovelSituacaoCobranca", idImovelSituacaoCobranca)
					.setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public DebitoAutomatico pesquisarDebitoAutomaticoAtivoImovel(Integer idImovel) throws ErroRepositorioException {
		
		DebitoAutomatico retorno = null;

		Session session = HibernateUtil.getSession();

		StringBuilder consulta = new StringBuilder();

		try {
			consulta.append("SELECT debitoAutomatico ")
					.append("from DebitoAutomatico debitoAutomatico ")
					.append("where debitoAutomatico.imovel.id = :idImovel ")
					.append("and debitoAutomatico.dataExclusao is null ");

			retorno = (DebitoAutomatico) session.createQuery(consulta.toString()).setInteger("idImovel", idImovel.intValue()).uniqueResult();

		} catch (NonUniqueResultException e) {
			return null;
		}catch (Exception e){
		    throw new ErroRepositorioException(e);
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

}
