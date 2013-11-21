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
package gcom.gerencial.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de resumo de faturamento
 * @author Pedro Alexandre
 * @created 09 de Janeiro de 2006
 */
public class FiltroResumoPendencia extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroResumoFaturamento object
	 */
	public FiltroResumoPendencia() {
	}

	/**
	 * Constructor for the FiltroResumoFaturamento object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroResumoPendencia(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id"; 

	public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

	public final static String ANO_MES_REFERENCIA = "anoMesReferencia";
    
	public final static String CODIGO_SETOR_COMERCIAL = "codigoSetorComercial";
    
	public final static String NUMERO_QUADRA = "numeroQuadra";
    
	public final static String INDICADOR_HIDROMETRO = "indicadorHidrometro";

	public final static String ANO_MES_REFERENCIA_DOCUEMTNO = "anoMesReferenciaDocumento";
   
	public final static String QUANTIDADE_LIGACOES = "quantidadeLigacoes";
    
	public final static String QUANTIDADE_DOCUMENTOS = "quantidadeDocumentos";

	public final static String VALOR_DEBITO = "valorDebito";

	public final static String GERENCIA_REGIONAL = "gerenciaRegional.id";

	public final static String LOCALIDADE = "localidade.id";

	public final static String SETOR_COMERCIAL = "setorComercial.id";
    
	public final static String ROTA = "rota.id";
    
	public final static String QUADRA = "quadra.id";

	public final static String IMOVEL_PERFIL = "imovelPerfil.id";

	public final static String LIGACAO_AGUA_SITUACAO = "ligacaoAguaSituacao.id";

	public final static String LIGACAO_ESGOTO_SITUACAO = "ligacaoEsgotoSituacao.id";

	public final static String CATEGORIA = "categoria.id";
    
	public final static String ESFERA_PODER = "esferaPoder.id";
    
	public final static String DOCUMENTO_TIPO = "documentoTipo.id";
}