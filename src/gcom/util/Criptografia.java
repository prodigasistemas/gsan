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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * Essa classe tem o papel de fornecer ao sistema serviços de criptografia da
 * biblioteca java.security
 * 
 * @author Rodrigo Silveira
 */
public final class Criptografia {
    //private static Criptografia instance;

    /**
     * Construtor da classe ServicosCriptografia
     */
    private Criptografia() {
    }

    /**
     * Esse método recebe uma senha digitada pelo usuário e aplica um algoritmo
     * de hash(SHA) para tornar a senha criptografada
     * 
     * @param plaintext
     *            Senha digitada pelo usuário
     * @return O hash da senha
     * @exception ErroCriptografiaException
     *                Ocorrência de algum erro no mecanismo de criptografia
     */
    public static synchronized String encriptarSenha(String plaintext)
            throws ErroCriptografiaException {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA");

        } catch (NoSuchAlgorithmException e) {
            throw new ErroCriptografiaException(e.getMessage());
        }
        try {
            md.update(plaintext.getBytes("UTF-8"));

        } catch (UnsupportedEncodingException e) {
            throw new ErroCriptografiaException(e.getMessage());
        }

        byte raw[] = md.digest();

        String hash = (new BASE64Encoder()).encode(raw);

        return hash;
    }

    /**
     * Metodo responsavel por encriptar as faixas de leitura
     *
     * [UC0627] Gerar Arquivo Texto Leiturista
     *
     * @author Pedro Alexandre
     * @date 21/09/2007
     *
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        int tab[] = {77,110,70,114,90,100,86,103,111,75};
        int i;
        int value = 0;
        int len = str.length();
        String response = "";

        for (i=0; i < len; i++) {
            value = (int) str.charAt(i);
            response += (char) tab[ (value - 48) ];
        }
       
        return response;
    }

    /**
     * The main program for the Criptografia class
     * 
     * @param args
     *            The command line arguments
     * @exception ErroCriptografiaException
     *                Descrição da exceção
     */
    public static void main(String[] args) throws ErroCriptografiaException {
        System.out.print(Criptografia.encriptarSenha("usuario"));
    }
}
