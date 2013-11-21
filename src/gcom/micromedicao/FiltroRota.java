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
package gcom.micromedicao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class FiltroRota extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String CODIGO_ROTA = "codigo";
	
	public static final String ID_ROTA = "id";

    public static final String FATURAMENTO_GRUPO_ID = "faturamentoGrupo.id";

    public static final String INDICADOR_USO = "indicadorUso";
    
    public static final String INDICADOR_ROTA_ALTERNATIVA = "indicadorRotaAlternativa";

    public static final String GERENCIA_REGIONAL_ID = "setorComercial.localidade.gerenciaRegional.id";

    public final static String GERENCIA_REGIONAL_NOME_ABREVIADO = "setorComercial.localidade.gerenciaRegional.nomeAbreviado";

    public static final String LOCALIDADE_ID = "setorComercial.localidade.id";
    
    public final static String LOCALIDADE_DESCRICAO = "setorComercial.localidade.descricao";

    public static final String SETOR_COMERCIAL_ID = "setorComercial.id";

    public static final String SETOR_COMERCIAL_CODIGO = "setorComercial.codigo";
    
    public static final String UNIDADE_NEGOCIO = "setorComercial.localidade.unidadeNegocio";
    
    public static final String UNIDADE_NEGOCIO_ID = "setorComercial.localidade.unidadeNegocio.id";
    
    public static final String UNIDADE_NEGOCIO_NOME_ABREVIADO = "setorComercial.localidade.unidadeNegocio.nomeAbreviado";
    
    public static final String COBRANCA_GRUPO_ID = "cobrancaGrupo.id";
    
    public static final String COBRANCA_CRITERIO = "cobrancaCriterio";
    
    public static final String EMPRESA_COBRANCA_ID = "empresaCobranca.id";
    
    public static final String EMPRESA = "empresa";
    
    public static final String FATURAMENTO_GRUPO = "faturamentoGrupo";
    
    public static final String LOCALIDADE = "setorComercial.localidade";
    
    public static final String SETOR_COMERCIAL = "setorComercial";
    
    public static final String LEITURA_TIPO = "leituraTipo";
    
    public static final String LEITURA_TIPO_ID = "leituraTipo.id";
    
    public static final String EMPRESA_ID = "empresa.id";
    
    public static final String LEITURISTA_ID = "leiturista.id";
    
    public static final String LEITURISTA = "leiturista";
    
    public static final String LEITURA_SEQUENCIA = "numeroSequenciaLeitura";
    
    public static final String CLIENTE = "leiturista.cliente";
    
    public static final String EMPRESA_ENTREGA_CONTAS = "empresaEntregaContas.id";
    
    public static final String COBRANCA_GRUPO = "cobrancaGrupo";
    
    public static final String EMPRESA_COBRANCA = "empresaCobranca";
    
    public static final String INDICADOR_TRANSMISSAO_OFFLINE = "indicadorTransmissaoOffline";
    

    public FiltroRota() {
    }

    public FiltroRota(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
