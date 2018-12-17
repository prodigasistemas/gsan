package gcom.seguranca;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.transacao.TabelaAtualizacaoCadastral;
import gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class RepositorioSegurancaHBM implements IRepositorioSeguranca {
	
	private static IRepositorioSeguranca instancia;
	
	private RepositorioSegurancaHBM() {
	}
	
	public static IRepositorioSeguranca getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioSegurancaHBM();
		}
		return instancia;
	}

	public List<TabelaAtualizacaoCadastral> pesquisaTabelaAtualizacaoCadastralPorImovel(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = " SELECT tabela "
					+ " FROM TabelaAtualizacaoCadastral tabela "
					+ " WHERE tabela.codigoImovel = :idImovel";
			return (List<TabelaAtualizacaoCadastral>) session.createQuery(consulta)
					.setInteger("idImovel", idImovel).list();
		}catch(HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public List<TabelaColunaAtualizacaoCadastral> pesquisaTabelaColunaAtualizacaoCadastral(Integer idTabelaAtualizacaoCadastral) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			String consulta = " SELECT tabelaColuna "
					+ " FROM TabelaColunaAtualizacaoCadastral tabelaColuna "
					+ " INNER JOIN fetch tabelaColuna.tabelaAtualizacaoCadastral tabelaAtualizacao "
					+ " INNER JOIN fetch tabelaAtualizacao.tabela tabela "
					+ " WHERE tabelaAtualizacao.id = :idTabelaAtualizacaoCadastral ";
			return (List<TabelaColunaAtualizacaoCadastral>) session.createQuery(consulta)
					.setInteger("idTabelaAtualizacaoCadastral", idTabelaAtualizacaoCadastral).list();
		}catch(HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public void autorizarAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE TabelaAtualizacaoCadastral t ")
				.append(" SET t.indicadorAutorizado = :autorizacao ")
				.append(" WHERE t.codigoImovel = :idImovel ");
			session.createQuery(sql.toString())
				.setShort("autorizacao", ConstantesSistema.SIM)
				.setInteger("idImovel", idImovel)
				.executeUpdate();
		}catch(HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public Integer pesquisarIdUsuarioAutorizadorImoveis(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT distinct(tcac.usur_id) as usuarioLogado");
			sql.append(" FROM seguranca.tab_col_atlz_cadastral tcac");
			sql.append(" inner join seguranca.tab_atlz_cadastral tac on (tac.tatc_id = tcac.tatc_id)");
			sql.append(" where tac.tatc_cdimovel = :idImovel");
			sql.append(" and tcac.usur_id is not null ");

			return (Integer) (session.createSQLQuery(sql.toString())
									.addScalar("usuarioLogado", Hibernate.INTEGER)
									.setInteger("idImovel", idImovel).uniqueResult());
		}catch(HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
}
