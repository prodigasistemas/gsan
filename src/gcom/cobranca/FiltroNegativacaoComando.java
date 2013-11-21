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
* Thiago Vieira
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
package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/**
 * Filtro Referente a Tabela NEGATIVACAO_COMANDO
 * 
 * @author Thiago Vieira
 * @created 27/12/2007
 */

public class FiltroNegativacaoComando extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroCobrancaAtividade object
     */
    public FiltroNegativacaoComando() {
    }

    /**
     * Constructor for the FiltroCobrancaAtividade object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroNegativacaoComando(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
 
    /** persistent field */
    public final static String INDICADOR_COMANDO_CRITERIO = "indicadorComandoCriterio";

    /** persistent field */
    public final static String DATA_PREVISTA = "dataPrevista";

    /** nullable persistent field */
    public final static String DATA_HORA_COMANDO = "dataHoraComando";

    /** nullable persistent field */
    public final static String DATA_HORA_REALIZACAO = "dataHoraRealizacao";

    /** nullable persistent field */
    public final static String QUANTIDADE_INCLUSOES = "quantidadeInclusoes";

    /** nullable persistent field */
    public final static String VALOR_DEBITO = "valorDebito";

    /** nullable persistent field */
    public final static String QUANTIDADE_ITENS_INCLUIDOS = "quantidadeItensIncluidos";

    /** nullable persistent field */
    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

    /** nullable persistent field */
    public final static String DESCRICAO_COMUNICACAO_INTERNA = "descricaoComunicacaoInterna";

    /** persistent field */
    public final static String INDICADOR_SIMULACAO = "indicadorSimulacao";

    /** persistent field */
    public final static String ID_USUARIO = "usuario.id";

    /** persistent field */
    public final static String ID_NEGATIVADOR = "negativador.id";
    
}
