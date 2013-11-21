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
package gcom.micromedicao.medicao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroMedicaoHistorico extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroMedicaoHistorico(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroPavimentoCalcada
     */
    public FiltroMedicaoHistorico() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    public final static String IMOVEL_ID = "imovel.id";
    
    public final static String IMOVEL_INDICADOR_EXCLUSAO = "imovel.indicadorExclusao";

    public final static String MEDICAO_TIPO_ID = "medicaoTipo.id";

    public final static String ANO_MES_REFERENCIA_FATURAMENTO = "anoMesReferencia";
    
    public final static String ANORMALIDADE_INFORMADA = "leituraAnormalidadeInformada.id";
    
    public final static String ANORMALIDADE_FATURADA = "leituraAnormalidadeFaturamento.id";
    
    public final static String LOCALIDADE_IMOVEL = "imovel.localidade.id"; 
    
    public final static String SETOR_COMERCIAL_IMOVEL = "imovel.setorComercial.id";
    
    public final static String QUADRA_IMOVEL = "imovel.quadra.id";
    
    public final static String GRUPO_FATURAMENTO = "imovel.quadra.rota.faturamentoGrupo.id";
    
    public final static String IMOVEL_EMPRESA = "imovel.quadra.rota.empresa.id";
    
    public final static String INDICADOR_IMOVEL_CONDOMINIO = "imovel.indicadorImovelCondominio";
    
    public final static String PERFIL_IMOVEL = "imovel.imovelPerfil.id";
    
    public final static String IMOVEL_CONDOMINIO_ID = "imovel.imovelCondominio.id";
    
    public final static String LIGACAO_AGUA_ID = "ligacaoAgua.id";
    
    //Filtrar analise excecoes leituras e consumo -- Constantes usadas    
    public final static String MEDICAO_EMPRESA = "imovel.quadra.rota.empresa.id";
    
    public final static String CONSUMO_HISTORICO_LIGACAO_TIPO = "ch.ligacaoTipo.id";
    
    public final static String CONSUMO_HISTORICO_ANORMALIDADE_CONSUMO = "ch.consumoAnormalidade.id";
    
    public final static String CONSUMO_HISTORICO_FATURADO_MES = "ch.numeroConsumoFaturadoMes";
    
    public final static String CONSUMO_HISTORICO_CONSUMO_MEDIDO = "ch.numeroConsumoMes";
    
    public final static String MH_IMOVEL_QTD_ECONOMIAS = "imovel.quantidadeEconomias";
    
    public final static String IMOVEL_CATEGORIA = "cat.id";
    
    public final static String MH_LIGACAO_AGUA_ID = "lagu.id";
    
    public final static String MH_ANORMALIDADE_INFORMADA = "mh.leituraAnormalidadeInformada.id";
    
    public final static String MH_ANORMALIDADE_FATURADA = "mh.leituraAnormalidadeFaturamento.id";
    
    public final static String MH_MEDICAO_TIPO_ID = "mh.medicaoTipo.id";
    
    public final static String CONSUMO_HISTORICO_CONSUMO_MEDIO = "ch.consumoMedioHidrometro";        

    public final static String SETOR_COMERCIAL_IMOVEL_CODIGO = "imovel.setorComercial.codigo";

    public final static String QUADRA_IMOVEL_NUMERO = "imovel.quadra.numeroQuadra";
    
    public final static String LIGACAO_AGUA_SITUACAO_IMOVEL = "imovel.ligacaoAguaSituacao.id";
    
    public final static String LIGACAO_ESGOTO_SITUACAO_IMOVEL = "imovel.ligacaoEsgotoSituacao.id";
    
    public final static String LEITURA_SITUACAO_ATUAL = "leituraSituacaoAtual";
    
    public final static String MOTIVO_INTERFERENCIA_TIPO = "motivoInterferenciaTipo";
    
    public final static String INFORMADA_ANORMALIDADE = "leituraAnormalidadeInformada";
    
    public final static String FATURADA_ANORMALIDADE = "leituraAnormalidadeFaturamento";
    
    public final static String USUARIO = "usuarioAlteracao";
    
    public final static String IMOVEL = "imovel";
    
    
       
}

