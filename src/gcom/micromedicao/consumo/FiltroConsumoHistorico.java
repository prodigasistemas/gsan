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
package gcom.micromedicao.consumo;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroConsumoHistorico extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroConsumoHistorico(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroAreaConstruidaFaxia
     */
    public FiltroConsumoHistorico() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String ANO_MES_FATURAMENTO = "referenciaFaturamento";

    /**
     * Description of the Field
     */
    public final static String IMOVEL_ID = "imovel.id";
    
    public final static String IMOVEL_INDICADOR_EXCLUSAO = "imovel.indicadorExclusao";

    public final static String LIGACAO_TIPO_ID = "ligacaoTipo.id";
    
    public final static String ANORMALIDADE_CONSUMO = "consumoAnormalidade.id";
    
    public final static String CONSUMO_FATURADO_MEDIO= "numeroConsumoFaturadoMes";
    
    public final static String CONSUMO_MEDIDO = "consumoMedio"; 
    
    public final static String LOCALIDADE_IMOVEL = "imovel.localidade.id";
    
    public final static String LIGACAO_AGUA_SITUACAO_IMOVEL = "imovel.ligacaoAguaSituacao.id";
    
    public final static String LIGACAO_ESGOTO_SITUACAO_IMOVEL = "imovel.ligacaoEsgotoSituacao.id";
    
    public final static String SETOR_COMERCIAL_IMOVEL = "imovel.setorComercial.id";
    
    public final static String QUADRA_IMOVEL = "imovel.quadra.id";
    
    public final static String INDICADOR_IMOVEL_CONDOMINIO = "imovel.indicadorImovelCondominio";
    
    public final static String PERFIL_IMOVEL = "imovel.imovelPerfil.id";
    
    public final static String IMOVEL_CONDOMINIO_ID = "imovel.imovelCondominio.id";
    
    public final static String CONSUMO_EMPRESA = "imovel.quadra.rota.id";
    
    public final static String CONSUMO_IMOVEL_CONDOMINIO = "consumoImovelCondominio";
    
    public final static String CONSUMO_TIPO = "consumoTipo";
    
    public final static String SOLICITACAO_TIPO_ESPECIFICACAO = "solicitacaoTipoEspecificacao.id";
    
    public final static String CODIGO_SITUACAO = "codigoSituacao";
    
}
