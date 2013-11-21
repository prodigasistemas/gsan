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

package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class FiltroNegativacaoCriterio  extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
	public final static String ID = "id";

    /** persistent field */
	public final static String DESCRICAO_TITULO = "descricaoTitulo";

    /** persistent field */
	public final static String DESCRICAO_SOLICITACAO = "descricaoSolicitacao";

    /** nullable persistent field */
	public final static String CODIGO_SETOR_COMERCIAL_INICIAL = "codigoSetorComercialInicial";

    /** nullable persistent field */
	public final static String CODIGO_SETOR_COMERCIAL_FINAL = "codigoSetorComercialFinal";

    /** nullable persistent field */
	public final static String ANO_MES_REFERENCIA_CONTA_INICIAL = "anoMesReferenciaContaInicial";

    /** nullable persistent field */
	public final static String ANO_MES_REFERENCIA_CONTA_FINAL = "anoMesReferenciaContaFinal";

    /** nullable persistent field */
	public final static String DATA_VENCIMENTO_DEBITO_INICIAL = "dataVencimentoDebitoInicial";

    /** nullable persistent field */
	public final static String DATA_VENCIMENTO_DEBITO_FINAL = "dataVencimentoDebitoFinal";

    /** nullable persistent field */
	public final static String QUANTIDADE_MAXIMA_INCLUSOES = "quantidadeMaximaInclusoes";

    /** persistent field */
	public final static String INDICADOR_NEGATIVACAO_IMOVEL_PARALISACAO = "indicadorNegativacaoImovelParalisacao";

    /** persistent field */
	public final static String INDICADOR_NEGATIVACAO_IMOVEL_SITUACAO_COBRANCA = "indicadorNegativacaoImovelSituacaoCobranca";

    /** persistent field */
	public final static String INDICADOR_NEGATIVACAO_CONTA_REVISAO = "indicadorNegativacaoContaRevisao";

    /** persistent field */
	public final static String INDICADOR_NEGATIVACAO_GUIA_PAGAMENTO = "indicadorNegativacaoGuiaPagamento";

    /** persistent field */
	public final static String INDICADOR_NEGATIVACAO_INQUILINO_DEBITO_CONTA_MES = "indicadorNegativacaoInquilinoDebitoContaMes";

    /** persistent field */
	public final static String INDICADOR_NEGATIVACAO_RECEBIMENTO_CARTA_PARCELAMENTO = "indicadorNegativacaoRecebimentoCartaParcelamento";

    /** nullable persistent field */
	public final static String NUMERO_DIAS_ATRASO_RECEBIMENTO_CARTA_PARCELAMENTO = "numeroDiasAtrasoRecebimentoCartaParcelamento";

    /** persistent field */
	public final static String INDICADOR_USO = "indicadorUso";

    /** persistent field */
	public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

    /** persistent field */
	public final static String VALOR_MINIMO_DECIMAR = "valorMinimoDebito";

    /** persistent field */
	public final static String QUANTIDADE_MINIMA_CONTAS = "quantidadeMinimaContas";

    /** persistent field */
	public final static String VALOR_MAXIMO_DEBITO = "valorMaximoDebito";

    /** persistent field */
	public final static String QUANTIDADE_MAXIMA_CONTAS = "quantidadeMaximaContas";

    /** persistent field */
	public final static String INDICADOR_PARCELAMENTO_ATRASO = "indicadorParcelamentoAtraso";

    /** nullable persistent field */
	public final static String NUMERO_DIAS_PARCELAMENTO_ATRASO = "numeroDiasParcelamentoAtraso";

    /** persistent field */
	public final static String ID_LOCALIDADE_FINAL = "localidadeFinal.id";

    /** persistent field */
	public final static String ID_LOCALIDADE_INICIAL = "localidadeInicial.id";

    /** persistent field */
	public final static String ID_NEGATIVACAO_COMANDO = "negativacaoComando.id";

    /** persistent field */
	public final static String ID_CLIENTE = "cliente.id";

    /** persistent field */
	public final static String ID_CLIENTE_RELACAO_TIPO = "clienteRelacaoTipo.id";

    
    /** default constructor */
    public FiltroNegativacaoCriterio() {
    }

    /**
     * Constructor for the FiltroCobrancaAtividade object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroNegativacaoCriterio(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
