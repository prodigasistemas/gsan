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
 * < <Descrição da Classe>>
 * 
 * @author Thiago Tenório
 * @created 06 de agosto de 2007
 */
public class FiltroArquivoTextoRoteiroEmpresa extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe FiltroGerenciaRegional
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroArquivoTextoRoteiroEmpresa(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLeiturista
     */
    public FiltroArquivoTextoRoteiroEmpresa() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String NUMERO_ARQUIVO = "numeroArquivo";

    /**
     * Description of the Field
     */
    public final static String QT_IMOVEL = "quantidadeImovel";
    
    /**
     * Description of the Field
     */
    public final static String LOCALIDADE = "localidade";
    
    /**
     * Description of the Field
     */
    public final static String FONE_LEITURISTA = "numeroFoneLeiturista";
    
    /**
     * Description of the Field
     */
    public final static String TEMPO_ENVIO_EMPRESA = "tempoEnvioEmpresa";
    
    /**
     * Description of the Field
     */
    public final static String EMPRESA = "empresa";
    
    /**
     * Description of the Field
     */
    public final static String GRUPO_FATURAMENTO = "faturamentoGrupo";
    
    /**
     * Description of the Field
     */
    public final static String SITUACAO_TRANS_LEITURA = "situacaoTransmissaoLeitura";
    
    /**
     * Description of the Field
     */
    public final static String LEITURISTA = "leiturista";
    
    /**
     * Description of the Field
     */
    public final static String LEITURISTA_CLIENTE = "leiturista.cliente";
    
    /**
     * Description of the Field
     */
    public final static String LEITURISTA_FUNCIONARIO = "leiturista.funcionario";
    
    /**
     * Description of the Field
     */
    public final static String ANO_MES_REFERENCIA = "anoMesReferencia";
    
    /**
     * Description of the Field
     */
    public final static String NUMERO_SEQUENCIA_LEITURA = "numeroSequenciaLeitura";
    
    /**
     * Description of the Field
     */
    public final static String LEITURISTA_ID = "leiturista.id";
    
    /**
     * Description of the Field
     */
    public final static String SITUACAO_TRANS_LEITURA_ID = "situacaoTransmissaoLeitura.id";
    
    /**
     * Description of the Field
     */
    public final static String ROTA = "rota";
    
    /**
     * Description of the Field
     */
    public final static String IMEI = "numeroImei";
    
    /**
     * Description of the Field
     */
    public final static String ROTA_ID = "rota.id";
   

    /**
     * Description of the Field
     */
    public final static String LOCALIDADE_ID = "localidade.id";
    
    public final static String SERVICO_TIPO_CELULAR = "servicoTipoCelular.id";
    
}
