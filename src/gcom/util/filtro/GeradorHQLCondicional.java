package gcom.util.filtro;

import gcom.util.ErroRepositorioException;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;

/**
 * O GeradorHQLCondicional é a classe que é responsável por gerar a parte
 * condicional de uma query HQL dependendo dos parâmetros do filtro informado.
 */
public class GeradorHQLCondicional {

	public static Query gerarCondicionalQuery(Filtro filtro, String aliasTabela, String query, Session session)
			throws HibernateException, ErroRepositorioException {
		
		Collection parametros = filtro.getParametros();
		Iterator iteratorParametros = parametros.iterator();
		
		CondicionalQuery condicionalQueryRetorno = new CondicionalQuery();
		condicionalQueryRetorno.setConsultaSemLimites(filtro.isConsultaSemLimites());

		if (!query.contains("where")) {
			query = query + " " + PersistenciaUtil.processaObjetosParaCarregamentoJoinFetch(aliasTabela, filtro.getColecaoCaminhosParaCarregamentoEntidades());

		}

		StringBuffer queryCondicional = condicionalQueryRetorno.getQuery();

		int numeroArgumentosIsoladosConector = 0;

		// Se o filtro não tiver parâmetros, retorna uma string vazia
		if (!parametros.isEmpty()) {
			// Coloca o where no começo da string condicional
			queryCondicional.append(" where ");

			// Vai representar o nome de cada parâmetro que for inserido na query
			// O inteiro será convertido para caracter, para que o nome dos parametros
			// na query fique no formato :a , :b , :c e etc.
			// Isso é feito porque as versões mais novas do hibernate não lidam
			// muito bem com parametros posicionais nas queries
			int numeroNomeParametro = 97;

			while (iteratorParametros.hasNext()) {

				// Percorre todos os parâmetros do filtro
				FiltroParametro filtroParametro = (FiltroParametro) iteratorParametros.next();

				// Chama o método de geração de acordo com o tipo do parâmetro
				// Para cada parâmetro, os valores correspondentes são
				// adicionados na coleção

				// Se o numeroArgumentosIsoladosConector for maior que 0, então
				// existe algum parâmetro
				// que está isolando outros por Conector
				if (numeroArgumentosIsoladosConector > 0) {
					numeroArgumentosIsoladosConector = numeroArgumentosIsoladosConector - 1;
				}

				if (numeroArgumentosIsoladosConector == 0) {
					numeroArgumentosIsoladosConector = filtroParametro.getNumeroArgumentosIsoladosPeloConector();
				}

				if (filtroParametro instanceof Intervalo) {

					Intervalo intervalo = ((Intervalo) filtroParametro);

					geradorCondicional(condicionalQueryRetorno, intervalo, aliasTabela, numeroArgumentosIsoladosConector, numeroNomeParametro);
					condicionalQueryRetorno.getParametrosValores().add(intervalo.getIntervaloInicial());
					condicionalQueryRetorno.getParametrosValores().add(intervalo.getIntervaloFinal());

					numeroNomeParametro = numeroNomeParametro + 2;

				} else if (filtroParametro instanceof ParametroSimplesColecao) {
					ParametroSimplesColecao parametroSimplesColecao = (ParametroSimplesColecao) filtroParametro;

					geradorCondicional(condicionalQueryRetorno, parametroSimplesColecao, aliasTabela, numeroArgumentosIsoladosConector, numeroNomeParametro);
					condicionalQueryRetorno.getParametrosValores().add(parametroSimplesColecao.getValor());

					numeroNomeParametro++;

				} else if (filtroParametro instanceof ParametroSimplesColecaoDiferenteDe) {
					ParametroSimplesColecaoDiferenteDe parametroSimplesColecaoDiferenteDe = (ParametroSimplesColecaoDiferenteDe) filtroParametro;

					geradorCondicional(condicionalQueryRetorno, parametroSimplesColecaoDiferenteDe, aliasTabela, numeroArgumentosIsoladosConector,
							numeroNomeParametro);
					condicionalQueryRetorno.getParametrosValores().add(parametroSimplesColecaoDiferenteDe.getValor());

					numeroNomeParametro++;

				} else if (filtroParametro instanceof ParametroSimples) {

					ParametroSimples parametroSimples = ((ParametroSimples) filtroParametro);

					geradorCondicional(condicionalQueryRetorno, parametroSimples, aliasTabela, numeroArgumentosIsoladosConector, numeroNomeParametro);
					condicionalQueryRetorno.getParametrosValores().add(parametroSimples.getValor());

					numeroNomeParametro++;

				} else if (filtroParametro instanceof ComparacaoTexto) {

					ComparacaoTexto comparacaoTexto = ((ComparacaoTexto) filtroParametro);

					geradorCondicional(condicionalQueryRetorno, comparacaoTexto, aliasTabela, numeroArgumentosIsoladosConector, numeroNomeParametro);
					condicionalQueryRetorno.getParametrosValores().add(comparacaoTexto.getValor());

					numeroNomeParametro++;

				} else if (filtroParametro instanceof ComparacaoTextoCompleto) {

					ComparacaoTextoCompleto comparacaoTextoCompleto = ((ComparacaoTextoCompleto) filtroParametro);

					geradorCondicional(condicionalQueryRetorno, comparacaoTextoCompleto, aliasTabela, numeroArgumentosIsoladosConector, numeroNomeParametro);
					condicionalQueryRetorno.getParametrosValores().add(comparacaoTextoCompleto.getValor());

					numeroNomeParametro++;

				} else if (filtroParametro instanceof ParametroNaoNulo) {
					ParametroNaoNulo parametroNaoNulo = (ParametroNaoNulo) filtroParametro;

					geradorCondicional(condicionalQueryRetorno, parametroNaoNulo, aliasTabela, numeroArgumentosIsoladosConector);

				} else if (filtroParametro instanceof ParametroNulo) {
					ParametroNulo parametroNulo = (ParametroNulo) filtroParametro;

					geradorCondicional(condicionalQueryRetorno, parametroNulo, aliasTabela, numeroArgumentosIsoladosConector);

				} else if (filtroParametro instanceof MenorQue) {
					MenorQue menorQue = (MenorQue) filtroParametro;

					geradorCondicional(condicionalQueryRetorno, menorQue, aliasTabela, numeroArgumentosIsoladosConector, numeroNomeParametro);
					condicionalQueryRetorno.getParametrosValores().add(menorQue.getNumero());

					numeroNomeParametro++;

				} else if (filtroParametro instanceof Menor) {
					Menor menor = (Menor) filtroParametro;

					geradorCondicional(condicionalQueryRetorno, menor, aliasTabela, numeroArgumentosIsoladosConector, numeroNomeParametro);

					condicionalQueryRetorno.getParametrosValores().add(menor.getNumero());

					numeroNomeParametro++;

				} else if (filtroParametro instanceof MenorQueComparacaoColuna) {
					MenorQueComparacaoColuna menorQueComparacaoColuna = (MenorQueComparacaoColuna) filtroParametro;

					geradorCondicional(condicionalQueryRetorno, menorQueComparacaoColuna, aliasTabela, numeroArgumentosIsoladosConector, numeroNomeParametro);
					// condicionalQueryRetorno.getParametrosValores().add(
					// menorQueComparacaoColuna
					// .getNomeAtributoIntervaloComparacao());

					// numeroNomeParametro++;

				} else if (filtroParametro instanceof MaiorQue) {
					MaiorQue maiorQue = (MaiorQue) filtroParametro;

					geradorCondicional(condicionalQueryRetorno, maiorQue, aliasTabela, numeroArgumentosIsoladosConector, numeroNomeParametro);
					condicionalQueryRetorno.getParametrosValores().add(maiorQue.getNumero());

					numeroNomeParametro++;
				} else if (filtroParametro instanceof ComparacaoCampos) {
					ComparacaoCampos comparacaoCampos = (ComparacaoCampos) filtroParametro;

					geradorCondicional(condicionalQueryRetorno, comparacaoCampos, aliasTabela, numeroArgumentosIsoladosConector);

				} else if (filtroParametro instanceof ParametroSimplesDiferenteDe) {
					ParametroSimplesDiferenteDe parametroSimplesDiferenteDe = ((ParametroSimplesDiferenteDe) filtroParametro);

					geradorCondicional(condicionalQueryRetorno, parametroSimplesDiferenteDe, aliasTabela, numeroArgumentosIsoladosConector, numeroNomeParametro);
					condicionalQueryRetorno.getParametrosValores().add(parametroSimplesDiferenteDe.getValor());

					numeroNomeParametro++;

				} else if (filtroParametro instanceof ParametroSimplesIn) {
					ParametroSimplesIn parametroSimplesIn = ((ParametroSimplesIn) filtroParametro);

					if (!Util.isVazioOrNulo(parametroSimplesIn.getValor())) {
						geradorCondicional(condicionalQueryRetorno, parametroSimplesIn, aliasTabela, numeroArgumentosIsoladosConector, numeroNomeParametro);

						condicionalQueryRetorno.getParametrosValores().add(parametroSimplesIn.getValor());

						numeroNomeParametro++;
					}
				} else if (filtroParametro instanceof ParametroSimplesNotIn) {
					ParametroSimplesNotIn parametroSimplesNotIn = ((ParametroSimplesNotIn) filtroParametro);

					if (!Util.isVazioOrNulo(parametroSimplesNotIn.getValor())) {
						geradorCondicional(condicionalQueryRetorno, parametroSimplesNotIn, aliasTabela, numeroArgumentosIsoladosConector, numeroNomeParametro);

						condicionalQueryRetorno.getParametrosValores().add(parametroSimplesNotIn.getValor());

						numeroNomeParametro++;
					}
				} else if (filtroParametro instanceof Maior) {
					Maior maior = (Maior) filtroParametro;

					geradorCondicional(condicionalQueryRetorno, maior, aliasTabela, numeroArgumentosIsoladosConector, numeroNomeParametro);

					condicionalQueryRetorno.getParametrosValores().add(maior.getNumero());

					numeroNomeParametro++;
				}
			}

		}
		// tirar o último "and" da string condicional e montar a query completa
		if (queryCondicional.length() > 0) {

			condicionalQueryRetorno.setQuery(new StringBuffer(query + queryCondicional.substring(0, queryCondicional.length() - 4)));
		} else {
			condicionalQueryRetorno.setQuery(new StringBuffer(query + queryCondicional));
		}

		// Verifica se o campo distinct da query foi informado no filtro
		if (filtro.getCampoDistinct() != null && !filtro.getCampoDistinct().trim().equalsIgnoreCase("")) {
			// Verifica se o usuário já tem um select na query para desabilitar
			// o distinct
			if (!query.startsWith("select")) {
				condicionalQueryRetorno.setQuery(new StringBuffer("select distinct " + filtro.getCampoDistinct() + " " + condicionalQueryRetorno.getQuery()));

			}

		}

		// Se informado, seta o orderBy da query
		List<String> camposOrderBy = filtro.getCamposOrderBy();

		if (camposOrderBy != null && !camposOrderBy.isEmpty()) {
			Iterator<String> iteratorOrderBy = camposOrderBy.iterator();

			StringBuilder order = new StringBuilder("");
			while (iteratorOrderBy.hasNext()) {
				order.append(aliasTabela);
				order.append(".");
				order.append(iteratorOrderBy.next());
				order.append(", ");
			}

			order.replace((order.length() - 2), order.length(), " ");

			condicionalQueryRetorno.setQuery(new StringBuffer(condicionalQueryRetorno.getQuery() + " order by " + order));

		}
//		System.out.println(condicionalQueryRetorno.getQuery().toString());
		return efetuarQuery(condicionalQueryRetorno, session);
	}

	/**
	 * Método auxiliar para inserir parâmetros dinamicamente na query
	 */
	private static Query efetuarQuery(CondicionalQuery condicionalQuery, Session session) throws HibernateException {

		Query query = session.createQuery(condicionalQuery.getQuery().toString());

		Iterator iterator = condicionalQuery.getParametrosValores().iterator();
		int i = 97;

		// Percorre a coleção de parâmetros e faz a associação com a query
		while (iterator.hasNext()) {
			Object parametro = iterator.next();

			// Verifica se algum parâmetro possui caracteres com acento para
			// serem convertidos
			if (parametro instanceof String) {
				String parametroString = (String) parametro;
				// Verifica se existe algum caracter especial na String
				if (parametroString != null && !parametroString.trim().equals("")
						&& !parametroString.matches("[\\w&&[^ÃÕÁÉÍÓÚÀÂÊÔÜÇãõáéíóúàãêôüç]]*")) {

					// Faz o replace para tirar os caracteres especiais da String
					parametro = parametroString.trim()
							.replace('Ã', 'A')
							.replace('Õ', 'O')
							.replace('Á', 'A')
							.replace('É', 'E')
							.replace('Í', 'I')
							.replace('Ú', 'U')
							.replace('À', 'A')
							.replace('Â', 'A')
							.replace('Ê', 'E')
							.replace('Ô', 'O')
							.replace('Ü', 'U')
							.replace('Ç', 'C')
							.replace('ã', 'A')
							.replace('õ', 'O')
							.replace('á', 'A')
							.replace('é', 'E')
							.replace('í', 'I')
							.replace('ú', 'U')
							.replace('à', 'A')
							.replace('â', 'A')
							.replace('ê', 'E')
							.replace('ô', 'O')
							.replace('ü', 'U')
							.replace('ç', 'C');
				}
			}

			if (parametro instanceof String) {
				
				String parametroString = (String) parametro;
				
				if (!parametroString.startsWith("0")) {
					try {
						Integer parametroInteger = Integer.parseInt(parametroString);
						query.setInteger("a" + i, parametroInteger);
					} catch (NumberFormatException e) {
						query.setParameter("a" + i, parametro);
					} finally {
						i++;
					}
				} else {
					query.setString("a" + i, parametroString);
					i++;
				}
			} else if (parametro instanceof Collection) {
				Collection<? extends Object> parametroCollection = (Collection<? extends Object>) parametro;

				try {
					query.setParameterList("a" + i, parametroCollection);
				} catch (HibernateException e) {
					query.setParameter("a" + i, parametro);
				} finally {
					i++;
				}
			} else {
				query.setParameter("a" + i, parametro);
				i++;
			}
		}

		return query;
	}

	/**
	 * Gera a cláusula condicional de um intervalo na query
	 */
	private static void geradorCondicional(
			CondicionalQuery condicionalQueryRetorno, Intervalo intervalo,
			String aliasTabela, int numeroArgumentosIsoladosConector,
			int numeroNomeParametro) {

		condicionalQueryRetorno.getQuery().append(
				processarIsolamentoConector(aliasTabela + "."
						+ intervalo.getNomeAtributo() + " between " + ":" + "a"
						+ numeroNomeParametro + " and " + ":" + "a"
						+ (numeroNomeParametro + 1) + intervalo.getConector(),
						intervalo, numeroArgumentosIsoladosConector));

	}

	/**
	 * Gera a cláusula condicional para um parâmetro na query
	 */
	private static void geradorCondicional(
			CondicionalQuery condicionalQueryRetorno,
			ParametroSimples parametro, String aliasTabela,
			int numeroArgumentosIsoladosConector, int numeroNomeParametro) {
		condicionalQueryRetorno.getQuery().append(
				processarIsolamentoConector(aliasTabela + "."
						+ parametro.getNomeAtributo() + " = " + ":" + "a"
						+ numeroNomeParametro + parametro.getConector(),
						parametro, numeroArgumentosIsoladosConector));

	}

	/**
	 * Gera a cláusula condicional para um parâmetro na query
	 */
	private static void geradorCondicional(
			CondicionalQuery condicionalQueryRetorno,
			ParametroSimplesDiferenteDe parametro, String aliasTabela,
			int numeroArgumentosIsoladosConector, int numeroNomeParametro) {
		condicionalQueryRetorno.getQuery().append(
				processarIsolamentoConector(aliasTabela + "."
						+ parametro.getNomeAtributo() + " <> " + ":" + "a"
						+ numeroNomeParametro + parametro.getConector(),
						parametro, numeroArgumentosIsoladosConector));

	}

	private static void geradorCondicional(
			CondicionalQuery condicionalQueryRetorno,
			ParametroSimplesIn parametro, String aliasTabela,
			int numeroArgumentosIsoladosConector, int numeroNomeParametro) {
		condicionalQueryRetorno.getQuery().append(
				processarIsolamentoConector(aliasTabela + "."
						+ parametro.getNomeAtributo() + " in ( " + ":" + "a"
						+ numeroNomeParametro + " ) " + parametro.getConector(),
						parametro, numeroArgumentosIsoladosConector));

	}
	
	private static void geradorCondicional(
			CondicionalQuery condicionalQueryRetorno,
			ParametroSimplesNotIn parametro, String aliasTabela,
			int numeroArgumentosIsoladosConector, int numeroNomeParametro) {
		condicionalQueryRetorno.getQuery().append(
				processarIsolamentoConector(aliasTabela + "."
						+ parametro.getNomeAtributo() + " not in ( " + ":" + "a"
						+ numeroNomeParametro + " ) " + parametro.getConector(),
						parametro, numeroArgumentosIsoladosConector));

	}

	/**
	 * Gera a cláusula condicional para um parâmetro na query
	 */
	private static void geradorCondicional(
			CondicionalQuery condicionalQueryRetorno,
			ComparacaoCampos comparacaoCampos, String aliasTabela,
			int numeroArgumentosIsoladosConector) {
		condicionalQueryRetorno.getQuery().append(
				processarIsolamentoConector(aliasTabela + "."
						+ comparacaoCampos.getNomeAtributo() + " = "
						+ aliasTabela + "." + comparacaoCampos.getValor()
						+ comparacaoCampos.getConector(), comparacaoCampos,
						numeroArgumentosIsoladosConector));

	}

	private static void geradorCondicional(
			CondicionalQuery condicionalQueryRetorno, MenorQue menorQue,
			String aliasTabela, int numeroArgumentosIsoladosConector,
			int numeroNomeParametro) {
		condicionalQueryRetorno.getQuery().append(
				processarIsolamentoConector(aliasTabela + "."
						+ menorQue.getNomeAtributo() + " <= " + ":" + "a"
						+ numeroNomeParametro + menorQue.getConector(),
						menorQue, numeroArgumentosIsoladosConector));
	}
	
	private static void geradorCondicional(
			CondicionalQuery condicionalQueryRetorno, Menor menor,
			String aliasTabela, int numeroArgumentosIsoladosConector,
			int numeroNomeParametro) {
		condicionalQueryRetorno.getQuery().append(
				processarIsolamentoConector(aliasTabela + "."
						+ menor.getNomeAtributo() + " < " + ":" + "a"
						+ numeroNomeParametro + menor.getConector(),
						menor, numeroArgumentosIsoladosConector));
	}
	
	private static void geradorCondicional(
			CondicionalQuery condicionalQueryRetorno, Maior maior,
			String aliasTabela, int numeroArgumentosIsoladosConector,
			int numeroNomeParametro) {
		condicionalQueryRetorno.getQuery().append(
				processarIsolamentoConector(aliasTabela + "."
						+ maior.getNomeAtributo() + " > " + ":" + "a"
						+ numeroNomeParametro + maior.getConector(), 
						maior, numeroArgumentosIsoladosConector));
	}

	private static void geradorCondicional(
			CondicionalQuery condicionalQueryRetorno,
			MenorQueComparacaoColuna menorQueComparacaoColuna,
			String aliasTabela, int numeroArgumentosIsoladosConector,
			int numeroNomeParametro) {
		condicionalQueryRetorno.getQuery().append(
				processarIsolamentoConector(aliasTabela
						+ "."
						+ menorQueComparacaoColuna.getNomeAtributo()
						+ " < "
						+ aliasTabela
						+ "."
						+ menorQueComparacaoColuna
								.getNomeAtributoIntervaloComparacao()
						+ menorQueComparacaoColuna.getConector(),
						menorQueComparacaoColuna,
						numeroArgumentosIsoladosConector));

	}

	private static void geradorCondicional(
			CondicionalQuery condicionalQueryRetorno, MaiorQue maiorQue,
			String aliasTabela, int numeroArgumentosIsoladosConector,
			int numeroNomeParametro) {
		condicionalQueryRetorno.getQuery().append(
				processarIsolamentoConector(aliasTabela + "."
						+ maiorQue.getNomeAtributo() + " >= " + ":" + "a"
						+ numeroNomeParametro + maiorQue.getConector(),
						maiorQue, numeroArgumentosIsoladosConector));
	}

	/**
	 * Gera a cláusula condicional para uma comparação de texto na query
	 */
	private static void geradorCondicional(
			CondicionalQuery condicionalQueryRetorno,
			ComparacaoTexto comparacaoTexto, String aliasTabela,
			int numeroArgumentosIsoladosConector, int numeroNomeParametro) {
		condicionalQueryRetorno.getQuery().append(
				processarIsolamentoConector("upper(" + aliasTabela + "."
						+ comparacaoTexto.getNomeAtributo() + ")" + "  like "
						+ "upper(:" + "a" + numeroNomeParametro + ")"
						+ comparacaoTexto.getConector(), comparacaoTexto,
						numeroArgumentosIsoladosConector));

	}

	/**
	 * Gera a cláusula condicional para uma comparação de texto na query
	 */
	private static void geradorCondicional(
			CondicionalQuery condicionalQueryRetorno,
			ComparacaoTextoCompleto comparacaoTextoCompleto,
			String aliasTabela, int numeroArgumentosIsoladosConector,
			int numeroNomeParametro) {
		condicionalQueryRetorno.getQuery().append(
				processarIsolamentoConector("upper(" + aliasTabela + "."
						+ comparacaoTextoCompleto.getNomeAtributo() + ")"
						+ "  like " + "upper(:" + "a" + numeroNomeParametro
						+ ")" + comparacaoTextoCompleto.getConector(),
						comparacaoTextoCompleto,
						numeroArgumentosIsoladosConector));

	}

	private static void geradorCondicional(
			CondicionalQuery condicionalQueryRetorno,
			ParametroNulo parametroNulo,
			String aliasTabela,
			int numeroArgumentosIsoladosConector) {
		
		condicionalQueryRetorno.getQuery().append(
				processarIsolamentoConector(aliasTabela
				+ "."
				+ parametroNulo.getNomeAtributo()
				+ " is null"
				+ parametroNulo.getConector(),
				parametroNulo,
				numeroArgumentosIsoladosConector));

	}

	private static void geradorCondicional(
			CondicionalQuery condicionalQueryRetorno,
			ParametroNaoNulo parametroNaoNulo,
			String aliasTabela,
			int numeroArgumentosIsoladosConector) {
		
		condicionalQueryRetorno.getQuery().append(
				processarIsolamentoConector(aliasTabela
				+ "."
				+ parametroNaoNulo.getNomeAtributo()
				+ " is not null"
				+ parametroNaoNulo.getConector(),
				parametroNaoNulo,
				numeroArgumentosIsoladosConector));

	}

	private static void geradorCondicional(
			CondicionalQuery condicionalQueryRetorno,
			ParametroSimplesColecao parametroSimplesColecao,
			String aliasTabela,
			int numeroArgumentosIsoladosConector,
			int numeroNomeParametro) {
		
		condicionalQueryRetorno.getQuery().append(processarIsolamentoConector(
				parametroSimplesColecao.getNomeAtributo()
				+ " = "
				+ ":"
				+ "a"
				+ numeroNomeParametro
				+ parametroSimplesColecao.getConector(),
				parametroSimplesColecao,
				numeroArgumentosIsoladosConector));

	}

	private static void geradorCondicional(
			CondicionalQuery condicionalQueryRetorno,
			ParametroSimplesColecaoDiferenteDe parametroSimplesColecaoDiferenteDe,
			String aliasTabela,
			int numeroArgumentosIsoladosConector,
			int numeroNomeParametro) {
		
		condicionalQueryRetorno.getQuery().append(processarIsolamentoConector(
				parametroSimplesColecaoDiferenteDe.getNomeAtributo()
				+ " != "
				+ ":"
				+ "a"
				+ numeroNomeParametro
				+ parametroSimplesColecaoDiferenteDe.getConector(),
				parametroSimplesColecaoDiferenteDe,
				numeroArgumentosIsoladosConector));

	}

	public static String processarIsolamentoConector(String condicional, FiltroParametro filtro, int numeroArgumentosIsoladosConector) {
		String retorno = "";

		if (numeroArgumentosIsoladosConector > 0) {

			// Se o número de parâmetros for indicado como último do isolamento
			if (numeroArgumentosIsoladosConector > 0 && numeroArgumentosIsoladosConector == 1) {

				condicional = condicional.substring(0, condicional.length() - 5);

				retorno = condicional + ")" + filtro.getConector();

				// Se o número de parâmetros for indicado como o primeiro do isolamento
			} else if (numeroArgumentosIsoladosConector == filtro.getNumeroArgumentosIsoladosPeloConector()) {
				retorno = "(" + condicional;
			} else {
				retorno = condicional;
			}
		} else {
			retorno = condicional;
		}
		
		return retorno;
	}

	/**
	 * Este método gera uma query usando o padrão Criteria Queries do hibernate
	 */
	@SuppressWarnings("rawtypes")
	public static Collection gerarQueryCriteriaExpression(Session session,
			Filtro filtro, Class classe) throws HibernateException {

		Collection parametros = filtro.getParametros();
		Iterator iteratorParametros = parametros.iterator();

		Collection retorno = new ArrayList();

		// Se o filtro não tiver parâmetros, retorna uma coleção vazia
		if (!parametros.isEmpty()) {
			Criteria criteria = session.createCriteria(classe);

			// Parte que define quais coleções vão ser carregadas
			Collection colecaoCampos = filtro.getColecaoCaminhosParaCarregamentoEntidades();

			if (!colecaoCampos.isEmpty()) {
				Iterator iterator = colecaoCampos.iterator();

				while (iterator.hasNext()) {
					String campoColecao = (String) iterator.next();
					criteria.setFetchMode(campoColecao, FetchMode.DEFAULT);
				}
			}

			Criteria criteriaSubFiltro = null;

			while (iteratorParametros.hasNext()) {
				FiltroParametro filtroParametro = (FiltroParametro) iteratorParametros.next();

				// Cria condicionais na query para subFiltros
				if (filtroParametro instanceof SubFiltro) {
					SubFiltro subFiltro = (SubFiltro) filtroParametro;

					Iterator iteratorSubFiltro = subFiltro.getFiltro().getParametros().iterator();

					if (criteriaSubFiltro == null) {
						criteriaSubFiltro = criteria.createCriteria(subFiltro.getNomeAtributo());
					} else {
						criteriaSubFiltro = criteriaSubFiltro.createCriteria(subFiltro.getNomeAtributo());
					}

					// Percorre todos os parâmetros do subFiltro
					while (iteratorSubFiltro.hasNext()) {
						FiltroParametro filtroParametroSubFiltro = (FiltroParametro) iteratorSubFiltro.next();

						criteriaSubFiltro = criteriaSubFiltro.add(avaliarParametrosQueryCriteriaExpression(session, filtroParametroSubFiltro));
					}

				} else {
					criteria.add(avaliarParametrosQueryCriteriaExpression(session, filtroParametro));
				}

			}

			// Seta o número máximo de resultados que podem ser retornados
			// O valor está em 101 porque o sistema não pretende mostrar
			// mais do que 100 registros simultaneamente
			criteria.setMaxResults(101);
			criteria.setCacheable(true);

			retorno = criteria.list();
		} else {
			// Se nenhum parâmetro for informado, a busca traz todos os registros
			Criteria criteria = session.createCriteria(classe);

			// Seta o número máximo de resultados que podem ser retornados
			// O valor está em 101 porque o sistema não pretende mostrar
			// mais do que 100 registros simultaneamente
			criteria.setMaxResults(101);
			criteria.setCacheable(true);

			retorno = criteria.list();
		}

		return retorno;
	}

	/**
	 * Este método avalia cada parâmetro informado num filtro para ser adicionado
	 * como uma condicional de busca usando o padrão Criteria Queries do hibernate
	 */
	public static Criterion avaliarParametrosQueryCriteriaExpression(Session session,
			FiltroParametro filtroParametro) throws HibernateException {

		Criterion retorno = null;

		// Chama o método de geração de acordo com o tipo do parâmetro
		if (filtroParametro instanceof Intervalo) {

			Intervalo intervalo = ((Intervalo) filtroParametro);

			// Monta a condicional para a query
			retorno = Expression.between(intervalo.getNomeAtributo(), intervalo.getIntervaloInicial(), intervalo.getIntervaloFinal());

		} else if (filtroParametro instanceof ParametroSimples) {

			ParametroSimples parametroSimples = ((ParametroSimples) filtroParametro);

			// Monta a condicional para a query
			retorno = Expression.eq(parametroSimples.getNomeAtributo(), parametroSimples.getValor());

		} else if (filtroParametro instanceof ComparacaoTexto) {

			ComparacaoTexto comparacaoTexto = ((ComparacaoTexto) filtroParametro);

			// Monta a condicional para a query
			retorno = Expression.like(comparacaoTexto.getNomeAtributo(), comparacaoTexto.getValor()).ignoreCase();

		} else if (filtroParametro instanceof ParametroNaoNulo) {
			ParametroNaoNulo parametroNaoNulo = (ParametroNaoNulo) filtroParametro;

			// Monta a condicional para a query
			retorno = Expression.isNotNull(parametroNaoNulo.getNomeAtributo());

		} else if (filtroParametro instanceof ParametroNulo) {
			ParametroNulo parametroNulo = (ParametroNulo) filtroParametro;

			// Monta a condicional para a query
			retorno = Expression.isNull(parametroNulo.getNomeAtributo());

		} else if (filtroParametro instanceof ConectorAnd) {

			ConectorAnd conectorAnd = (ConectorAnd) filtroParametro;
			// Obtém os parâmetros
			FiltroParametro filtro1 = conectorAnd.getFiltro1();
			FiltroParametro filtro2 = conectorAnd.getFiltro2();

			// Monta a condicional para a query
			retorno = Expression.and(avaliarParametrosQueryCriteriaExpression(session, filtro1), avaliarParametrosQueryCriteriaExpression(session, filtro2));

		} else if (filtroParametro instanceof ConectorOr) {

			ConectorOr conectorOr = (ConectorOr) filtroParametro;
			// Obtém os parâmetros
			FiltroParametro filtro1 = conectorOr.getFiltro1();
			FiltroParametro filtro2 = conectorOr.getFiltro2();

			// Monta a condicional para a query
			retorno = Expression.or(avaliarParametrosQueryCriteriaExpression(session, filtro1), avaliarParametrosQueryCriteriaExpression(session, filtro2));

		}

		return retorno;
	}

	public static void main(String[] args) {
	}

}
