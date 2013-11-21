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
package gcom.cadastro.cliente;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Description of the Class
 * 
 * @author Sávio Luiz
 * @created 17 de Agosto de 2005
 */

public class FiltroClienteImovelEconomia extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroClienteImovelEconomia
     */
    public FiltroClienteImovelEconomia() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroClienteImovelEconomia(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DATA_FIM_RELACAO = "dataFimRelacao";
    
    /**
     * Description of the Field
     */
    public final static String DATA_INICIO_RELACAO = "dataInicioRelacao";

    /**
     * Description of the Field
     */
    public final static String IMOVEL_ECONOMIA_ID = "imovelEconomia.id";
    
    public final static String IMOVEL_ECONOMIA = "imovelEconomia";

    /**
     * Description of the Field
     */
    public final static String IMOVEL_PERFIL = "imovelEconomia.imovelSubcategoria.comp_id.imovel.imovelPerfil.id";

    /**
     * Description of the Field
     */
    public final static String IMOVEL_PERFIL_INDICADOR_USO = "imovelEconomia.imovelSubcategoria.comp_id.imovel.imovelPerfil.indicadorUso";

    /**
     * Description of the Field
     */
    public final static String FIM_RELACAO_MOTIVO = "clienteImovelFimRelacaoMotivo.id";

    /**
     * Description of the Field
     */
    public final static String CLIENTE_RELACAO_TIPO = "clienteRelacaoTipo.id";

    /**
     * Description of the Field
     */
    public final static String CLIENTE_ID = "cliente.id";
    
    /**
     * Description of the Field
     */
    public final static String CLIENTE = "cliente";

    /**
     * Description of the Field
     */
    public final static String IMOVEL_ID = "imovelEconomia.imovelSubcategoria.comp_id.imovel.id";
    
    public final static String IMOVEL = "imovelEconomia.imovelSubcategoria.comp_id.imovel";

    public final static String SUBCATEGORIA_ID = "imovelEconomia.imovelSubcategoria.comp_id.subcategoria.id";

    public final static String IMOVEL_ECONOMIEA_AREA_CONSTRUIDA_FAIXA = "imovelEconomia.areaConstruidaFaixa";

    /**
     * Description of the Field
     */
    public final static String IMOVEL_ECONOMIEA_IMOVEL_SUB_CATEGORIA = "imovelEconomia.imovelSubcategoria";

    /**
     * Description of the Field
     */
    public final static String IMOVEL_ECONOMIEA_IMOVEL_CATEGORIA = "imovelEconomia.imovelSubcategoria.comp_id.subcategoria.categoria";

}