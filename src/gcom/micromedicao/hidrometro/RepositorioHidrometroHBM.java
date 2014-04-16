package gcom.micromedicao.hidrometro;

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.GeradorHQLCondicional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArraySet;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class RepositorioHidrometroHBM implements IRepositorioHidrometro {

	private static IRepositorioHidrometro instancia;

	/**
	 * Construtor da classe RepositorioMicromedicaoHBM
	 */
	private RepositorioHidrometroHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioHidrometro getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioHidrometroHBM();
		}

		return instancia;
	}

	public Collection pesquisarHidrometroPorHidrometroMovimentacao(Filtro filtro)
			throws ErroRepositorioException {
		// cria a coleção de retorno
		Collection retorno = null;
		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {
			// pesquisa a coleção de atividades e atribui a variável "retorno"
			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional
					.gerarCondicionalQuery(
							filtro,
							"hidrometroMovimentado",
							"from gcom.micromedicao.hidrometro.HidrometroMovimentado as hidrometroMovimentado ",
							session).list()));

			// Carrega os objetos informados no filtro
/*			if (!filtro.getColecaoCaminhosParaCarregamentoEntidades().isEmpty()) {
				PersistenciaUtil
						.processaObjetosParaCarregamento(filtro
								.getColecaoCaminhosParaCarregamentoEntidades(),
								retorno);
			}
*/		} catch (HibernateException e) {
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
	 * [UC0000] - Efetuar Retirada de Hidrômetro
	 * 
	 * Pesquisa todos os campos do Hidrometro e seus relacionamentos obrigatórios.
	 * @author Thiago Tenório
	 * @date 28/09/2006
	 * 
	 * @param idHidrometro
	 * @throws ErroRepositorioException
	 */
	
	public Object[] pesquisarHidrometroPeloId(Integer idHidrometro)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;
		Object[] retornoConsulta = null;
		try {
			consulta = "select h.id, h.numero, "
				+ "h.dataAquisicao, h.anoFabricacao, "
				+ "h.indicadorMacromedidor, h.dataUltimaRevisao, "
				+ "h.dataBaixa, h.numeroLeituraAcumulada, "
				+ "h.numeroDigitosLeitura, htp.id, "
				+ "hsit.id, hmarc.id, hcap.id, hcm.id, "
				+ "hdm.id "
				+ "from Hidrometro h "
				+ "inner join h.hidrometroCapacidade hcap "
				+ "inner join h.hidrometroMarca hmarc "
				+ "inner join h.hidrometroTipo htp "
				+ "inner join h.hidrometroDiametro hdm "
				+ "inner join h.hidrometroSituacao hsit "
				+ "inner join h.hidrometroClasseMetrologica hcm "
				+ "where h.id = :idHidrometro";

			retornoConsulta = (Object[]) session.createQuery(consulta)
					.setInteger("idHidrometro", idHidrometro).setMaxResults(1)
					.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retornoConsulta;
	}

}
