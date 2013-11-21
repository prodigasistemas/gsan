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
package gcom.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Esta classe é usada para guardar todos os valores que devem ser constantes
 * durante a execução do sistema. O carregamento das constantes é feita
 * automaticamente durante o classloader desta classe. Após esta inicialização,
 * que é feita antes de qualquer acesso a classe, as constantes podem ser
 * consultadas de forma muito simples. Para o funcionamento desejado o usuário
 * deve pôr um arquivo chamado application.properties no mesmo diretório onde
 * localiza-se esta classe, este arquivo deve conter todos os nomes das
 * constantes do sistemas juntamente com os seus valores como pode ser visto
 * abaixo.
 * 
 * <pre>
 * 
 *  
 *  # Arquivo de Configurações das constantes do sistema
 *  SERVER = www.compesa.com.br
 *  PORT = 8080
 *  
 *  
 *  
 *  
 *  
 *  
 *  
 *   @author   Rodrigo Cabral
 * 
 * 
 */

public class ConstantesWebService {

    // Nome do Arquivo de propriedades
    private final static String NOME_ARQUIVO_PROPRIEDADES;

    // guarda as constantes contidas no application.properties juntamente com
    // seus valores
    private static Properties propriedades = null;

    // inicialização estática
    static {
        // Nome do Arquivo de propriedades
        NOME_ARQUIVO_PROPRIEDADES = "/webservice.properties";
        loadResources();
    }

    
    public static String SENHA_CDL = get("webservice.senha");
    

    public static String get(String key) {
        return propriedades.getProperty(key);
    }
    
    /**
     * Este método retorna o valor da constante passada como parâmetro.
     * 
     * @param key
     *            Nome da constante
     * @param paramtros lista de parametros a serem substuidos na string
     * @return Descrição do retorno
     */

    public static String get(String key, String... parametros) {
        String retorno = propriedades.getProperty(key);
        
        int i = 0;
        
        for ( String parametro : parametros ){        	
        	retorno = retorno.replace( "{" + i + "}", parametro );        	
        	++i;
        }
        
        return retorno;        
    }
    

    /**
     * Carrega o arquivo de properties do sistema
     */
    private static void loadResources() {
        propriedades = new Properties();

        InputStream stream;

        try {

            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            stream = classLoader.getResourceAsStream(NOME_ARQUIVO_PROPRIEDADES);

            // if system class loader not found try the this class classLoader
            if (stream == null) {
                stream = gcom.util.ConstantesWebService.class.getClassLoader()
                        .getResourceAsStream(NOME_ARQUIVO_PROPRIEDADES);
            }

            if (stream == null) {
                stream = gcom.util.ConstantesWebService.class
                        .getResourceAsStream(NOME_ARQUIVO_PROPRIEDADES);
            }

            propriedades.load(stream);

        } catch (IOException e) {
            e.printStackTrace();
            System.err
                    .println("Nao foi possivel localizar o arquivo de propriedades. Certifique-se "
                            + "de que o arquivo "
                            + NOME_ARQUIVO_PROPRIEDADES
                            + " esteja na raiz do CLASSPATH");

        }

    }

}
