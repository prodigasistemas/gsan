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
package gcom.micromedicao.hidrometro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 5 de Setembro de 2005
 */
public class FiltroHidrometro extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroHidrometro object
	 */
	public FiltroHidrometro() {
	}

	/**
	 * Construtor da classe FiltroHidrometroCapacidade
	 * 
	 * @param campoOrderBy
	 *            Descrição do parâmetro
	 */
	public FiltroHidrometro(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String NUMERO_HIDROMETRO = "numero";

	/**
	 * Description of the Field
	 */
	public final static String DATA_AQUISICAO = "dataAquisicao";

	/**
	 * Description of the Field
	 */
	public final static String ANO_FABRICACAO = "anoFabricacao";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_MACROMEDIDOR = "indicadorMacromedidor";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_MOVIMENTACAO_ID = "hidrometroMovimentacao.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_CLASSE_METROLOGICA_ID = "hidrometroClasseMetrologica.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_MARCA_ID = "hidrometroMarca.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_DIAMETRO_ID = "hidrometroDiametro.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_CAPACIDADE_ID = "hidrometroCapacidade.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_TIPO_ID = "hidrometroTipo.id";
	
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_RELOJOARIA_ID = "hidrometroRelojoaria.id";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_LOCAL_ARMAZENAGEM_ID = "hidrometroLocalArmazenagem.id";

	public final static String HIDROMETRO_SITUACAO_ID = "hidrometroSituacao.id";

	public final static String HIDROMETRO_HIDROMETRO_MOVIMENTADO_HIDROMETRO_MOVIMENTACAO_DATA = "hidrometroMovimentado.hidrometroMovimentacao.data";

	public final static String HIDROMETRO_HIDROMETRO_MOVIMENTADO_HIDROMETRO_MOVIMENTACAO_HORA = "hidrometroMovimentado.hidrometroMovimentacao.hora";

	public final static String HIDROMETRO_HIDROMETRO_MOVIMENTADO_HIDROMETRO_MOVIMENTACAO_HIDROMETRO_LOCAL_ARMAZENAGEM_DESTINO_DESCRICAO = "hidrometroMovimentado.hidrometroMovimentacao.hidrometroLocalArmazenagemDestino.descricao";

	public final static String HIDROMETRO_HIDROMETRO_MOVIMENTADO_HIDROMETRO_MOVIMENTACAO_HIDROMETRO_LOCAL_ARMAZENAGEM_ORIGEM_DESCRICAO = "hidrometroMovimentado.hidrometroMovimentacao.hidrometroLocalArmazenagemOrigem.descricao";
	
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_TIPO = "hidrometroTipo";
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_SITUACAO = "hidrometroSituacao";
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_MARCA = "hidrometroMarca";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_DIAMETRO = "hidrometroDiametro";

	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_CAPACIDADE = "hidrometroCapacidade";
	
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_MOTIVO_BAIXA = "hidrometroMotivoBaixa";
	
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_LOCAL_ARMAZENAGEM = "hidrometroLocalArmazenagem";
	
	/**
	 * Description of the Field
	 */
	public final static String HIDROMETRO_CLASSE_METROLOGICA = "hidrometroClasseMetrologica";
	
	/**
	 * Description of the Field
	 */
	public final static String VAZAO_TRANSICAO = "vazaoTransicao";
	
	/**
	 * Description of the Field
	 */
	public final static String VAZAO_NOMINAL = "vazaoNominal";
	
	/**
	 * Description of the Field
	 */
	public final static String VAZAO_MINIMA = "vazaoMinima";
	
	/**
	 * Description of the Field
	 */
	public final static String NOTA_FISCAL = "notaFiscal";
	
	/**
	 * Description of the Field
	 */
	public final static String TEMPO_GARANTIA_ANOS = "tempoGarantiaAnos";

}
