/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.util.filtro;

import gcom.util.ErroRepositorioException;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.StringTokenizer;

import org.hibernate.Hibernate;

/**
 * Funções para facilitar a manipulação de coleções no repositorio
 * 
 * @author rodrigo
 */
public class PersistenciaUtil {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param nomeColecoes
	 *            Descrição do parâmetro
	 * @param colecaoDados
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public static Collection processaColecoesParaCarregamento(
			Collection nomeColecoes, Collection colecaoDados)
			throws ErroRepositorioException {
		Iterator iteratorNomes = nomeColecoes.iterator();

		if (!nomeColecoes.isEmpty()) {

			while (iteratorNomes.hasNext()) {
				String nomeColecao = (String) iteratorNomes.next();
				Iterator iteratorDados = colecaoDados.iterator();

				while (iteratorDados.hasNext()) {
					Object objetoDado = iteratorDados.next();

					try {
						nomeColecao = nomeColecao.substring(0, 1).toUpperCase()
								+ nomeColecao
										.substring(1, nomeColecao.length());

						Collection colecao = ((Collection) objetoDado
								.getClass().getMethod("get" + nomeColecao,
										(Class[]) null).invoke(objetoDado,
										(Object[]) null));

						Iterator iterator = colecao.iterator();

						iterator.next();
					} catch (NoSuchElementException ex) {
						// Caso a coleção seja vazia

						try {
							objetoDado.getClass()
									.getMethod("set" + nomeColecao,
											new Class[]{Set.class}).invoke(
											objetoDado, (Object[]) null);
						} catch (SecurityException ex2) {
							throw new ErroRepositorioException("erro.sistema");
						} catch (NoSuchMethodException ex2) {
							throw new ErroRepositorioException("erro.sistema");
						} catch (InvocationTargetException ex2) {
							throw new ErroRepositorioException("erro.sistema");
						} catch (IllegalArgumentException ex2) {
							throw new ErroRepositorioException("erro.sistema");
						} catch (IllegalAccessException ex2) {
							throw new ErroRepositorioException("erro.sistema");
						}
					} catch (SecurityException ex1) {
						throw new ErroRepositorioException("erro.sistema");
					} catch (NoSuchMethodException ex1) {
						throw new ErroRepositorioException("erro.sistema");
					} catch (InvocationTargetException ex1) {
						throw new ErroRepositorioException("erro.sistema");
					} catch (IllegalArgumentException ex1) {
						throw new ErroRepositorioException("erro.sistema");
					} catch (IllegalAccessException ex1) {
						throw new ErroRepositorioException("erro.sistema");
					}

				}

			}
		}
		return colecaoDados;
	}

	/**
	 * Este método carrega todos os objetos informados no filtro na hierarquia
	 * do objeto consultado no repositório
	 * 
	 * @param nomeObjetos
	 *            A lista de parametros que representa os objetos da hierarquia
	 *            do objeto consultado que devem ser carregados. EX.:
	 *            cep.cepTipo
	 * @param colecaoDados
	 *            O resultado da consulta
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public static void processaObjetosParaCarregamento(Collection nomeObjetos,
			Collection colecaoDados) throws ErroRepositorioException {

		// Verifica se o usuario informou algum objeto para ser carregado
		if (!nomeObjetos.isEmpty()) {
			Iterator iteratorNomes = nomeObjetos.iterator();

			// Percorre cada nome informado
			while (iteratorNomes.hasNext()) {
				String nomeColecao = (String) iteratorNomes.next();

				// monta a chamada do método
				// StringBuffer chamadaGet = new StringBuffer();

				// Para cada item da colecao inicializar os objetos informados
				Iterator iteratorDados = colecaoDados.iterator();

				while (iteratorDados.hasNext()) {
					int contadorIteracao = 0;
					Object objetoDado = iteratorDados.next();
					Object retorno = null;

					// Serve de separador para montar a hierarquia de objetos
					// informados
					StringTokenizer separador = new StringTokenizer(
							nomeColecao, ".");

					while (separador.hasMoreTokens()) {
						// Prepara a chamada
						// Ex.: getCep().getCepTipo()
						StringBuffer token = new StringBuffer(separador
								.nextToken());

						token.insert(0, "get");
						token
								.replace(3, 4, token.substring(3, 4)
										.toUpperCase());
						// token.append(token.substring(1, token.length()));

						try {

							// Inicializa o objeto informado para ser disponivel
							// no objeto consultado
							// Teste para ver se o carregamento do parametro
							// anterior foi nulo, impossibilitando
							// o proximo carregamento na hierarquia
							if (retorno == null && contadorIteracao != 0) {
								break;
							} else {
								objetoDado = ((retorno == null)
										? objetoDado
										: retorno);
								contadorIteracao++;
							}

							retorno = objetoDado.getClass().getMethod(
									token.toString(), (Class[]) null).invoke(
									objetoDado, (Object[]) null);
							Hibernate.initialize(retorno);

						} catch (NoSuchElementException ex) {
							throw new ErroRepositorioException("erro.sistema");
						} catch (SecurityException ex1) {
							throw new ErroRepositorioException("erro.sistema");
						} catch (NoSuchMethodException ex1) {
							System.out.print(ex1.getMessage());
							throw new ErroRepositorioException("erro.sistema");
							// throw new
							// ErroRepositorioException("erro.metodo.nao.econtrado",
							// null, ex1.getMessage() + "," + objetoDado);
						} catch (InvocationTargetException ex1) {
							throw new ErroRepositorioException("erro.sistema");
						} catch (IllegalArgumentException ex1) {
							throw new ErroRepositorioException("erro.sistema");
						} catch (IllegalAccessException ex1) {
							throw new ErroRepositorioException("erro.sistema");
						} catch (Exception e) {
							e.printStackTrace();

						}

					}

				}
			}
		}
		// return colecaoDados;
	}

	/*
	 * public static String processaObjetosParaCarregamentoJoinFetch( String
	 * aliasTabela, Collection nomeObjetos) throws ErroRepositorioException {
	 * 
	 * String resultadoJoinsMontados = ""; // Verifica se o usuario informou //
	 * algum objeto para ser carregado // // Armazena os joins que já foram //
	 * usados // // // [stringDoJoin -> aliasDoJoin] Map<String, String>
	 * joinExistentesQuery // = new HashMap<String, String>(); int
	 * contadorItensColecao = 0; if (!nomeObjetos.isEmpty()) { Iterator
	 * iteratorNomes = nomeObjetos.iterator(); // Percorre cada nome informado
	 * while (iteratorNomes.hasNext()) { contadorItensColecao++;
	 * 
	 * int contadorNomes = 0;
	 * 
	 * String nomeColecao = (String) iteratorNomes.next(); // Serve de //
	 * separador // para montar a hierarquia de objetos // informados
	 * StringTokenizer separador = new StringTokenizer(nomeColecao, ".");
	 * 
	 * String ultimoNomeJoin = ""; while (separador.hasMoreTokens()) {
	 * contadorNomes++; String join = ""; String aliasJoin = ""; if
	 * (contadorNomes == 1) {
	 * 
	 * join = aliasTabela + "." + separador.nextToken(); aliasJoin = "a" +
	 * contadorItensColecao + "" + contadorNomes; // Verificar se o join já //
	 * existe no Map if // (joinExistentesQuery.containsKey(join)) // { // Se já
	 * existir um // join igual, entao pegar o // alias // no lugar do join //
	 * join = joinExistentesQuery.get(join); ultimoNomeJoin = join; } else {
	 * 
	 * resultadoJoinsMontados = resultadoJoinsMontados + " left join fetch " +
	 * join + " " + aliasJoin + " "; if (!joinExistentesQuery.containsKey(join)) {
	 * joinExistentesQuery.put(join, aliasJoin); } ultimoNomeJoin = aliasJoin; } }
	 * else {
	 * 
	 * join = ultimoNomeJoin + "." + separador.nextToken(); aliasJoin = "a" +
	 * contadorItensColecao + "" + contadorNomes; // Verificar se o join já //
	 * existe no Map if // (joinExistentesQuery.containsKey(join)) // { // Se já
	 * existir um // join igual, entao pegar o // alias // no lugar do join //
	 * join = joinExistentesQuery.get(join); ultimoNomeJoin = join; } else {
	 * 
	 * resultadoJoinsMontados = resultadoJoinsMontados + " left join fetch " +
	 * join + " " + aliasJoin + " "; if (!joinExistentesQuery.containsKey(join)) {
	 * joinExistentesQuery.put(join, aliasJoin); } ultimoNomeJoin = aliasJoin; } } } } }
	 * return resultadoJoinsMontados; }
	 */

	public static String processaObjetosParaCarregamentoJoinFetch(
			String aliasTabela, Collection nomeObjetos)
			throws ErroRepositorioException {

		String resultadoJoinsMontados = "";

		// Verifica se o usuario informou algum objeto para ser carregado
		if (!nomeObjetos.isEmpty()) {
			Iterator iteratorNomes = nomeObjetos.iterator();

			// Percorre cada nome informado
			while (iteratorNomes.hasNext()) {

				String nomeColecao = (String) iteratorNomes.next();
				StringTokenizer separador = new StringTokenizer(nomeColecao,
						".");

				String elementoJoin = "";
				while (separador.hasMoreTokens()) {
					if (elementoJoin.equals("")) {
						elementoJoin = elementoJoin + aliasTabela + "."
								+ separador.nextToken();

					} else {
						elementoJoin = elementoJoin + "."
								+ separador.nextToken();
					}

					// O join com o comp_id não funciona
					// Elimina o join do comp_id
					if (!elementoJoin.endsWith(".comp_id")) {
						resultadoJoinsMontados = resultadoJoinsMontados
								+ " left join fetch " + elementoJoin;
					}

				}

			}
		}
		return resultadoJoinsMontados;
	}

}
