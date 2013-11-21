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
 * @author Pedro Alexandre 
 * @created 01 de Fevereiro de 2006
 */

public class FiltroCobrancaAcaoAtividadeComando extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroCobrancaAcaoAtividadeComando object
     */
    public FiltroCobrancaAcaoAtividadeComando() {
    }

    /**
     * Constructor for the FiltroCobrancaAcaoAtividadeComando object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCobrancaAcaoAtividadeComando(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String COMANDO = "comando";
    
    /**
     * Description of the Field
     */
    public final static String REALIZACAO = "realizacao";
    
    /**
     * Description of the Field
     */
    public final static String ID_COBRANCA_ACAO = "cobrancaAcao.id";
    /**
     * Description of the Field
     */
    public final static String ID_COBRANCA_ATIVIDADE = "cobrancaAtividade.id";
    
    /**
     * Description of the Field
     */
    public final static String ID_COBRANCA_GRUPO = "cobrancaGrupo.id";
    /**
     * Description of the Field
     */
    public final static String ID_GERENCIA_REGIONAL = "gerenciaRegional.id";
    
    /**
     * Description of the Field
     */
    public final static String ID_LOCALIDADE_INICIAL = "localidadeInicial.id";
    
    /**
     * Description of the Field
     */
    public final static String ID_LOCALIDADE_FINAL = "localidadeFinal.id";

    /**
     * Description of the Field
     */
    public final static String CODIGO_SETOR_COMERCIAL_INICIAL = "codigoSetorComercialInicial";

    /**
     * Description of the Field
     */
    public final static String CODIGO_SETOR_COMERCIAL_FINAL = "codigoSetorComercialFinal";
    
    /**
     * Description of the Field
     */
    public final static String NUMERO_QUADRA_INICIAL = "numeroQuadraInicial";

    /**
     * Description of the Field
     */
    public final static String NUMERO_QUADRA_FINAL = "numeroQuadraFinal";
    
    
   
    /**
     * Description of the Field
     */
    public final static String ID_CLIENTE = "cliente.id";    
    
    /**
     * Description of the Field
     */
    public final static String ID_CLIENTE_SUPERIOR = "superior.id"; 
    
    /**
     * Description of the Field
     */
    public final static String ID_CLIENTE_RELACAO_TIPO = "clienteRelacaoTipo.id";
    
    /**
     * Description of the Field
     */
    public final static String ANO_MES_REFERENCIA_CONTA_INICIAL = "anoMesReferenciaContaInicial";
    
    /**
     * Description of the Field
     */
    public final static String ANO_MES_REFERENCIA_CONTA_FINAL = "anoMesReferenciaContaFinal";
    
    
    /**
     * Description of the Field
     */
    public final static String DATA_VENCIMENTO_CONTA_INICIAL = "dataVencimentoContaInicial";

    /**
     * Description of the Field
     */
    public final static String DATA_VENCIMENTO_CONTA_FINAL = "dataVencimentoContaFinal";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_CRITERIO  = "indicadorCriterio";

    /**
     * Description of the Field
     */
    public final static String ID_COBRANCA_CRITERIO = "cobrancaCriterio.id";

    /**
     * Description of the Field
     */
    public final static String ID_USUARIO = "usuario.id";

    /**
     * Description of the Field
     */
    public final static String COBRANCA_ACAO = "cobrancaAcao";

    /**
     * Description of the Field
     */
    public final static String COBRANCA_ATIVIDADE = "cobrancaAtividade";

    /**
     * Description of the Field
     */
    public final static String USUARIO = "usuario";
    
    /**
     * Description of the Field
     */
    public final static String COBRANCA_GRUPO = "cobrancaGrupo";
    
    /**
     * Description of the Field
     */
    public final static String GERENCIAL_REGIONAL = "gerenciaRegional";

    /**
     * Description of the Field
     */
    public final static String LOCALIDADE_INICIAL = "localidadeInicial";

    /**
     * Description of the Field
     */
    public final static String LOCALIDADE_FINAL = "localidadeFinal";    
    
    /**
     * Description of the Field
     */
    public final static String SETOR_COMERCIAL = "codigoSetorComercialInicial";
    
    /**
     * Description of the Field
     */
    public final static String ROTA_INICIAL = "rotaInicial";
    
    /**
     * Description of the Field
     */
    public final static String ROTA_FINAL = "rotaFinal";
    /**
     * Description of the Field
     */
    public final static String CODIGO_ROTA_INICIAL = "rotaInicial.codigo";
    
    /**
     * Description of the Field
     */
    public final static String CODIGO_ROTA_FINAL = "rotaFinal.codigo";
    
    /**
     * Description of the Field
     */
    public final static String CLIENTE = "cliente";
    
    /**
     * Description of the Field
     */
    public final static String CLIENTE_SUPERIOR = "superior";
    
    /**
     * Description of the Field
     */
    public final static String CLIENTE_RELACAO_TIPO = "clienteRelacaoTipo";
    

    /**
     * Description of the Field
     */
    public final static String COBRANCA_CRITERIO = "cobrancaCriterio";


    /**
     * Description of the Field
     */
    public final static String EMPRESA = "empresa";

    /**
     * Description of the Field
     */
    public final static String ID_ROTA_INICIAL = "rotaInicial.id";
    
    /**
     * Description of the Field
     */
    public final static String ID_ROTA_FINAL = "rotaFinal.id";
    
    
    public final static String VALOR_DOCUMENTOS = "valorDocumentos";
    
    public final static String QUANTIDADE_DOCUMENTOS = "quantidadeDocumentos";

    public final static String QUANTIDADE_ITENS_COBRADOS = "quantidadeItensCobrados";
    
    /**
     * Description of the Field
     */
    public final static String ID_UNIDADE_NEGOCIO = "unidadeNegocio.id";
    
    /**
     * Description of the Field
     */
    public final static String NEGOCIO_UNIDADE = "unidadeNegocio";
    
    /**
     * Description of the Field
     */
    public final static String DESCRICAO_TITULO = "descricaoTitulo";
    
    /**
     * Description of the Field
     */
    public final static String DATA_ENCERRAMENTO_REALIZADA = "dataEncerramentoRealizada";
    
    /**
     * Description of the Field
     */
    public final static String VALOR_LIMITE_OBRIGATORIA = "valorLimiteObrigatoria";
    /**
     * Description of the Field
     */
    public final static String CONSUMO_MEDIO_INICIAL = "consumoMedioInicial";
    /**
     * Description of the Field
     */
    public final static String CONSUMO_MEDIO_FINAL = "consumoMedioFinal";
    /**
     * Description of the Field
     */
    public final static String TIPO_CONSUMO = "tipoConsumo";
    /**
     * Description of the Field
     */
    public final static String PERIODO_FINAL_FISCALIZACAO = "periodoFinalFiscalizacao";
    /**
     * Description of the Field
     */
    public final static String PERIODO_INICIAL_FISCALIZACAO = "periodoInicialFiscalizacao";

    
}
