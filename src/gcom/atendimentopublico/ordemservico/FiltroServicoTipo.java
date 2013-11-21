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
 * FiltroUnidadeOrganizacional 
 *
 * @author Sávio Luiz
 * @date 27/07/2006
 */
public class FiltroServicoTipo extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroUnidadeOrganizacional object
     */
    public FiltroServicoTipo() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroServicoTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Id da Unidade
     */
    public final static String ID = "id";
    
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
    public final static String DESCRICAO = "descricao";
    public final static String DSABREVIADA = "descricaoAbreviada";
    public final static String VALORSERVICO = "valor";
    public final static String INDICADORPAVIMENTO = "indicadorPavimento";
    public final static String INDICADORATUALIZARCOMERCIAL = "indicadorAtualizaComercial";
    public final static String INDICADORTERCEIRIZADO = "indicadorTerceirizado";
    public final static String CODIGOSERVICOTIPO = "codigoServicoTipo";
    public final static String TEMPOMEDIOEXECUCAO = "tempoMedioExecucao";
    public final static String ULTIMAALTERACAO = "ultimaAlteracao";
    public final static String CREDITOTIPO = "creditoTipo.id";
    public final static String SERVICO_TIPO_REFERENCIA = "servicoTipoReferencia"; 
    public final static String SERVICOPERFILTIPO = "servicoPerfilTipo.id"; 
    public final static String SERVICOTIPOSUBGRUPO = "servicoTipoSubgrupo.id"; 
    public final static String SERVICOTIPOPRIORIDADE = "servicoTipoPrioridade.id"; 
    public final static String DEBITOTIPO = "debitoTipo.id"; 
    public final static String ATIVIDADETIPOSERVICO = "debitoTipo.id"; 
    public final static String INDICADOR_USO = "indicadorUso";
    public final static String INDICADOR_DIAGNOSTICO_SERVICO_TIPO_REF = "servicoTipoReferencia.indicadorDiagnostico";
    public final static String INDICADOR_FISCALIZACAO_SERVICO_TIPO_REF = "servicoTipoReferencia.indicadorFiscalizacao";
    public final static String CONSTANTE_FUNCIONALIDADE_TIPO_SERVICO = "constanteFuncionalidadeTipoServico";
    public final static String INDICADOR_EMPRESA_COBRANCA = "indicadorEmpresaCobranca";
    public final static String INDICADOR_SERVICO_ORDEM_SELETIVA = "indicadorServicoOrdemSeletiva";
    public static final String ID_OS_PROGRAMA_CALIBRAGEM = "programaCalibragem.id";
    public static final String INDICADOR_PERMITE_ALTERACAO_IMOVEL_EM_CAMPO = "indicadorPermiteAlteracaoImovelEmCampo";
    
    public static final String OS_PROGRAMA_CALIBRAGEM = "programaCalibragem";
    
    public static final String INDICADOR_GERAR_OS_INSP_ANORMALIDADE = "indicadorInspecaoAnormalidade";
    public static final String INDICADOR_PROGRAMACAO_AUTOMATICA = "indicadorProgramacaoAutomatica";
    
}
