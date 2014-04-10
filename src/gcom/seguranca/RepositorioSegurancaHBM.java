package gcom.seguranca;

import gcom.seguranca.transacao.TabelaAtualizacaoCadastral;
import gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.List;

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
					+ " INNER JOIN tabelaColuna.tabelaAtualizacaoCadastral tabela "
					+ " WHERE tabela.id = :idTabelaAtualizacaoCadastral ";
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
}
