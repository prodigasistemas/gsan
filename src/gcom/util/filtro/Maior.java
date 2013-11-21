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

/**
 * < <Descrição da Classe>>
 * 
 * @author Hugo Leonardo
 * @date 22/12/2010
 */
public class Maior extends FiltroParametro {
	private static final long serialVersionUID = 1L;
    private Object numero;

    /**
     * Construtor da classe MenorQue
     * 
     * @param nomeAtributoIntervalo
     *            Descrição do parâmetro
     * @param numero
     *            Descrição do parâmetro
     */
    public Maior(String nomeAtributoIntervalo, Object numero) {
        super(nomeAtributoIntervalo);
        this.numero = numero;
    }

    /**
     * Construtor da classe ParametroNulo
     * 
     * @param nomeAtributoIntervalo
     *            Descrição do parâmetro
     * @param numero
     *            Descrição do parâmetro
     * @param conector
     *            Conector da query
     */
    public Maior(String nomePrimeiroAtributo, Object numero, String conector) {
        super(nomePrimeiroAtributo, conector);
        this.numero = numero;
    }

    /**
     * Construtor da classe MenorQue
     * 
     * @param nomeAtributo
     *            Descrição do parâmetro
     * @param numero
     *            Descrição do parâmetro
     * @param conector
     *            Descrição do parâmetro
     * @param numeroParametrosIsoladosConector
     *            Descrição do parâmetro
     */
    public Maior(String nomeAtributo, Object numero, String conector,
            int numeroParametrosIsoladosConector) {
        super(nomeAtributo, conector, numeroParametrosIsoladosConector);
        this.numero = numero;
    }
    
    /**
     * Construtor da classe MenorQue
     * 
     * @param nomeAtributo
     *            Descrição do parâmetro
     * @param numero
     *            Descrição do parâmetro
     * @param numeroParametrosIsoladosConector
     *            Descrição do parâmetro
     */
    public Maior(String nomeAtributo, Object numero, int numeroParametrosIsoladosConector) {
        super(nomeAtributo, numeroParametrosIsoladosConector);
        this.numero = numero;
    }

	public Object getNumero() {
		return numero;
	}

	public void setNumero(Object numero) {
		this.numero = numero;
	}

	@Override
	public Object getValor() {
		// TODO Auto-generated method stub
		return null;
	}

}
