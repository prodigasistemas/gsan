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
 * @created 08 de Fevereiro de 2006
 */

public class FiltroCobrancaAcao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroCobrancaAcao object
	 */
	public FiltroCobrancaAcao() {
	}

	/**
	 * Constructor for the FiltroCobrancaAcao object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroCobrancaAcao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String COBRANCA_ACAO_PRECEDENTE_ID = "cobrancaAcaoPredecessora.id";
	
	public final static String COBRANCA_ACAO_PREDECESSORA = "cobrancaAcaoPredecessora";
	
	public final static String SERVICO_TIPO = "servicoTipo";

	/**
	 * Description of the Field
	 */
	public final static String DOCUMENTO_TIPO = "documentoTipo";

	public final static String COBRANCAO_CRITERIO = "cobrancaCriterio";

	/**
	 * Description of the Field
	 */
	public final static String SERVICO_TIPO_PREDECESSORA = "cobrancaAcaoPredecessora.servicoTipo";

	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricaoCobrancaAcao";

	public final static String ORDEM_REALIZACAO = "ordemRealizacao";

	public final static String INDICADOR_USO = "indicadorUso";

	public final static String INDICADOR_CRONOGRAMA = "indicadorCronograma";

	public final static String INDICADOR_OBRIGATORIEDADE = "indicadorObrigatoriedade";

	public final static String INDICADOR_REPETICAO = "indicadorRepeticao";

	public final static String INDICADOR_SUSPENSAO_ABASTECIMENTO = "indicadorSuspensaoAbastecimento";

	public final static String NUMERO_DIAS_VALIDADE = "numeroDiasValidade";
	
	public final static String NUMERO_DIAS_MINIMO_ACAO_PRECEDENTE = "numeroDiasMinimoAcaoPrecedente";
	
	public final static String INDICADOR_COBRANCA_DEB_A_COBRAR = "indicadorCobrancaDebACobrar";
	
	public final static String INDICADOR_CREDITO_A_REALIZAR = "indicadorCreditosARealizar";

	public final static String INDICADOR_NOTAS_PROMISSORIA = "indicadorNotasPromissoria";
	
	public final static String INDICADOR_ORDENAR_MAIOR_VALOR = "indicadorOrdenarMaiorValor";
	
	public final static String INDICADOR_VALIDAR_ITEM = "indicadorValidarItem";
	
	public final static String INDICADOR_GERACAO_TAXA = "indicadorGeracaoTaxa";
	
	public final static String INDICADOR_METAS_CRONOGRAMA = "indicadorMetasCronograma";
	
	public final static String INDICADOR_ORDENAMENTO_CRONOGRAMA = "indicadorOrdenamentoCronograma";
	
	public final static String INDICADOR_ORDENAMENTO_EVENTUAL = "indicadorOrdenamentoEventual";
	
	public final static String INDICADOR_DEBITO_INTERFERE_ACAO = "indicadorDebitoInterfereAcao";
	
	public final static String NUMERO_DIAS_REMUNERACAO_TERCEIRO = "numeroDiasRemuneracaoTerceiro";
	
	
	public final static String INDICADOR_ACRESCIMO_IMPONTUALIDADE = "indicadorAcrescimoImpontualidade";
	
	public final static String LIGACAO_ESGOTO_SITUACAO_ID = "ligacaoEsgotoSituacao.id";
	
	public final static String LIGACAO_ESGOTO_SITUACAO = "ligacaoEsgotoSituacao";
	
	public final static String LIGACAO_AGUA_SITUACAO_ID = "ligacaoAguaSituacao.id";
	
	public final static String LIGACAO_AGUA_SITUACAO = "ligacaoAguaSituacao";
	
	public final static String DOCUMENTO_TIPO_ID = "documentoTipo.id";

	public final static String COBRANCAO_CRITERIO_ID = "cobrancaCriterio.id";
	
	public final static String INDICADOR_BOLETIM = "indicadorBoletim";
	
	public final static String INDICADOR_DEBITO = "indicadorDebito";
	
	public final static String SERVICO_TIPO_ID_ACAO_COBRANCA = "servicoTipo.id";
	

}
