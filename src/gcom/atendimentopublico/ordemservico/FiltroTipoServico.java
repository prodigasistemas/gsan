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
package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de uma municipio
 * 
 * @author Sávio Luiz
 * @created 22 de Abril de 2005
 */

public class FiltroTipoServico extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroBairro object
     */
    public FiltroTipoServico() {
    }

    /**
     * Constructor for the Filtro object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroTipoServico(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Id do bairro
     */
    public final static String ID = "id";

    /**
     * Indicador uso
     */
    public final static String DESCRICAO = "descricao";
    
    /**
     * Indicador uso
     */
    public final static String ABREVIADA = "descricaoAbreviada";
    
    /**
     * Indicador uso
     */
    public final static String VALOR = "valor";
    
    /**
     * Indicador uso
     */
    public final static String SUBGRUPO = "servicoTipoSubgrupo.id";
    
    /**
     * Indicador uso
     */
    public final static String INDICADOR_PAVIMENTO = "indicadorPavimento";
    
    /**
     * Indicador uso
     */
    public final static String INDICADOR_ATUALIZACAO_COMERCIAL = "indicadorAtualizaComercial";
    
    /**
     * Indicador uso
     */
    public final static String INDICADOR_TERCEIRIZADO = "indicadorTerceirizado";
    
    /**
     * Indicador uso
     */
    public final static String INDICADOR_FISCALIZACAO_INFRACAO = "indicadorFiscalizacaoInfracao";
    
    /**
     * Indicador uso
     */
    public final static String INDICADOR_VISTORIA = "indicadorVistoria";
    
    /**
     * Indicador uso
     */
    public final static String CODIGO_TIPO_SERVICO = "codigoServicoTipo";
    
    /**
     * Indicador uso
     */
    public final static String TEMPO_MEDIO_ESECUCAO = "tempoMedioExecucao";
    
    /**
     * Indicador uso
     */
    public final static String DEBITO_TIPO_ID = "debitoTipo.id";
    
    /**
     * Indicador uso
     */
    public final static String CREDITO_TIPO_ID = "creditoTipo.id";
    
    /**
     * Indicador uso
     */
    public final static String PRIORIDADE_SERVICO = "servicoTipoPrioridade.id";
    
    /**
     * Indicador uso
     */
    public final static String PERFIL_TIPO = "servicoPerfilTipo.id";
    
    /**
     * Indicador uso
     */
    public final static String INDICADOR_USO = "indicadorUso";
    
    /**
     * Indicador pavimento de rua
     */
    public final static String INDICADOR_PAVIMENTO_RUA = "indicadorPavimentoRua";

    /**
     * Indicador pavimento de calçada
     */
    public final static String INDICADOR_PAVIMENTO_CALCADA = "indicadorPavimentoCalcada";
    
    /**
     * Indicador pavimento de calçada
     */
    public final static String INDICADOR_CONSIDERA_ECONOMIAS = "indicativoTipoSevicoEconomias";
    
    /**
     * Indicador de Empresa de cobrança
     */
    public final static String INDICADOR_EMPRESA_COBRANCA = "indicadorEmpresaCobranca";
    
    /**
     * Indicaodr de Inspeção de anormalidade
     */
    public final static String INDICADOR_INSPECAO_ANORMALIDADE = "indicadorInspecaoAnormalidade";
    
    /**
     * Indicador de Programação automática
     */
    public final static String INDICADOR_PROGRAMACAO_AUTOMATICA = "indicadorProgramacaoAutomatica";
    
    public static final String ID_OS_PROGRAMA_CALIBRAGEM = "programaCalibragem.id";
    
    public static final String OS_PROGRAMA_CALIBRAGEM = "programaCalibragem";
    
    /**
     * Indicador de encerramento automático do RA no encerramento de sua última/única OS pendente
     */
    public final static String INDICADOR_ENCERRAMENTO_AUTOMATICO_RA_QUANDO_ENCERRAR_ULTIMA_OS = "indicadorEncAutomaticoRAQndEncOS";
    
}
