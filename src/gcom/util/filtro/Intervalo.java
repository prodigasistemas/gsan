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
 * Representa um parâmetro de intervalo para um filtro
 * 
 * @author rodrigo
 */
public class Intervalo extends FiltroParametro {
	private static final long serialVersionUID = 1L;
	private Object IntervaloInicial;

    private Object IntervaloFinal;

    /**
     * Construtor da classe Intervalo
     * 
     * @param nomeAtributoIntervalo
     *            Nome do atributo de que será feita a filtragem
     * @param IntervaloInicial
     *            Data inicial do intervalo
     * @param dataIntervaloFinal
     *            Data final do intervalo
     */
    public Intervalo(String nomeAtributoIntervalo, Object IntervaloInicial,
            Object dataIntervaloFinal) {
        super(nomeAtributoIntervalo);
        this.IntervaloInicial = IntervaloInicial;
        this.IntervaloFinal = dataIntervaloFinal;

    }

    /**
     * Construtor da classe Intervalo
     * 
     * @param nomeAtributoIntervalo
     *            Descrição do parâmetro
     * @param IntervaloInicial
     *            Descrição do parâmetro
     * @param IntervaloFinal
     *            Descrição do parâmetro
     * @param conector
     *            Descrição do parâmetro
     */
    public Intervalo(String nomeAtributoIntervalo, Object IntervaloInicial,
            Object IntervaloFinal, String conector) {
        super(nomeAtributoIntervalo, conector);
        this.IntervaloInicial = IntervaloInicial;
        this.IntervaloFinal = IntervaloFinal;

    }

    /**
     * Construtor da classe Intervalo
     * 
     * @param nomeAtributoIntervalo
     *            Descrição do parâmetro
     * @param IntervaloInicial
     *            Descrição do parâmetro
     * @param IntervaloFinal
     *            Descrição do parâmetro
     * @param conector
     *            Descrição do parâmetro
     * @param numeroParametrosIsoladosConector
     *            Descrição do parâmetro
     */
    public Intervalo(String nomeAtributoIntervalo, Object IntervaloInicial,
            Object IntervaloFinal, String conector,
            int numeroParametrosIsoladosConector) {
        super(nomeAtributoIntervalo, conector, numeroParametrosIsoladosConector);
        this.IntervaloInicial = IntervaloInicial;
        this.IntervaloFinal = IntervaloFinal;

    }

    /**
     * Retorna o valor de IntervaloFinal
     * 
     * @return O valor de IntervaloFinal
     */
    public Object getIntervaloFinal() {
        return IntervaloFinal;
    }

    /**
     * Retorna o valor de IntervaloInicial
     * 
     * @return O valor de IntervaloInicial
     */
    public Object getIntervaloInicial() {
        return IntervaloInicial;
    }

    /**
     * Seta o valor de IntervaloFinal
     * 
     * @param IntervaloFinal
     *            O novo valor de IntervaloFinal
     */
    public void setIntervaloFinal(Object IntervaloFinal) {
        this.IntervaloFinal = IntervaloFinal;
    }

    /**
     * Seta o valor de IntervaloInicial
     * 
     * @param IntervaloInicial
     *            O novo valor de IntervaloInicial
     */
    public void setIntervaloInicial(Object IntervaloInicial) {
        this.IntervaloInicial = IntervaloInicial;
    }

	@Override
	public Object getValor() {
		// TODO Auto-generated method stub
		return null;
	}

}
