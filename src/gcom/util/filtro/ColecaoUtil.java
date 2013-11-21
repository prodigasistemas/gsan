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
