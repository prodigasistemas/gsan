package gcom.util.filtro;

import gcom.util.ErroRepositorioException;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Funções para facilitar a manipulação de coleções no repositorio
 * 
 * @author rodrigo
 */
public class ColecaoUtil {

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
                                .getClass()
                                .getMethod("get" + nomeColecao, (Class[])null).invoke(
                                        objetoDado, (Object[])null));

                        Iterator iterator = colecao.iterator();

                        iterator.next();
                    } catch (NoSuchElementException ex) {
                        //Caso a coleção seja vazia

                        try {
                            objetoDado.getClass().getMethod(
                                    "set" + nomeColecao,
                                    new Class[] { Set.class }).invoke(
                                    objetoDado, (Object[])null);
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

}
