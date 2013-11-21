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
* Anderson Italo Felinto de Lima
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
 * 
 * 
 * @author Anderson Italo
 * @created 25 de Novembro de 2009
 */

public class FiltroImovelNaoGerado extends Filtro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroCobrancaSituacao object
     */
    public FiltroImovelNaoGerado() {
    }
    
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroImovelNaoGerado(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    /**
     * Description of the Field
     */
    public final static String ID_IMOVEL = "imovel.id";
    
    public final static String IMOVEL = "imovel";
    
    public final static String LOCALIDADE = "imovel.localidade";
    
    public final static String LOCALIDADE_ID = "imovel.localidade.id";
    
    public final static String GERENCIA_REGIONAL = "imovel.localidade.gerenciaRegional";
    
    public final static String GERENCIA_REGIONAL_ID = "imovel.localidade.gerenciaRegional.id";
    
    public final static String UNIDADE_NEGOCIO = "imovel.localidade.unidadeNegocio";
    
    public final static String UNIDADE_NEGOCIO_ID = "imovel.localidade.unidadeNegocio.id";
    
    public final static String SETOR_COMERCIAL = "imovel.setorComercial";
    
    public final static String SETOR_COMERCIAL_ID = "imovel.setorComercial.id";
    
    public final static String SETOR_COMERCIAL_CODIGO = "imovel.setorComercial.codigo";
    
    public final static String QUADRA = "imovel.quadra";
    
    public final static String QUADRA_ID = "imovel.quadra.id";
    
    public final static String QUADRA_NUMERO = "imovel.quadra.numeroQuadra";
    
    
    
    /**
     * Description of the Field
     */
    public final static String ID_COBRANCA_ACAO_ATIVIDADE_CRONOGRAMA = "cobrancaAcaoAtividadeCronograma.id";
    
    /**
     * Description of the Field
     */
    public final static String MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA = "motivoNaoGeracaoDocCobranca";
    
    /**
     * Description of the Field
     */
    public final static String ID_COBRANCA_ACAO_ATIVIDADE_COMANDO = "cobrancaAcaoAtividadeComando.id";
    
    public final static String ID_MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA = "motivoNaoGeracaoDocCobranca.id";
    

}
