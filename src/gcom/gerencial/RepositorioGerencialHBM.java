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
package gcom.gerencial;

import gcom.gerencial.bean.FiltrarRelatorioOrcamentoSINPHelper;
import gcom.gerencial.bean.FiltrarRelatorioQuadroMetasAcumuladoHelper;
import gcom.gerencial.bean.FiltrarRelatorioQuadroMetasExercicioHelper;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.GeradorSqlRelatorio;
import gcom.util.HibernateUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * 
 * 
 * @author Raphael Rossiter
 * @created 20/05/2006
 */
public class RepositorioGerencialHBM implements IRepositorioGerencial {
	
	/**
	 * Constantes do Relatorio de Quadro de Metas Aculumado
	 */
	// Ligacoes Cadastradas
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_SUBCATEGORIA_101 = 0;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_SUBCATEGORIA_102 = 1;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_SUBCATEGORIA_103 = 2;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_SUBCATEGORIA_200 = 3;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_SUBCATEGORIA_300 = 4;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_SUBCATEGORIA_400 = 5;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_SUBCATEGORIA_116 = 6;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_SUBCATEGORIA_115 = 7;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS = 8;
	
	// Ligacoes Cortadas
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_SUBCATEGORIA_101 = 9;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_SUBCATEGORIA_102 = 10;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_SUBCATEGORIA_103 = 11;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_SUBCATEGORIA_200 = 12;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_SUBCATEGORIA_300 = 13;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_SUBCATEGORIA_400 = 14;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_SUBCATEGORIA_116 = 15;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_SUBCATEGORIA_115 = 16;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS = 17;
	
	// Ligacoes Suprimidas
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_SUBCATEGORIA_101 = 18;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_SUBCATEGORIA_102 = 19;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_SUBCATEGORIA_103 = 20;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_SUBCATEGORIA_200 = 21;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_SUBCATEGORIA_300 = 22;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_SUBCATEGORIA_400 = 23;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_SUBCATEGORIA_116 = 24;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_SUBCATEGORIA_115 = 25;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS = 26;
	
	// Ligacoes Ativas
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_SUBCATEGORIA_101 = 27;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_SUBCATEGORIA_102 = 28;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_SUBCATEGORIA_103 = 29;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_SUBCATEGORIA_200 = 30;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_SUBCATEGORIA_300 = 31;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_SUBCATEGORIA_400 = 32;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_SUBCATEGORIA_116 = 33;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_SUBCATEGORIA_115 = 34;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS = 35;
	
	// Ligacoes Ativas com débitos a mais de 3 meses
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_SUBCATEGORIA_101 = 36;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_SUBCATEGORIA_102 = 37;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_SUBCATEGORIA_103 = 38;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_SUBCATEGORIA_200 = 39;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_SUBCATEGORIA_300 = 40;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_SUBCATEGORIA_400 = 41;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_SUBCATEGORIA_116 = 42;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_SUBCATEGORIA_115 = 43;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M = 44;
	
	// Ligacoes Consumo Medido
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_SUBCATEGORIA_101 = 45;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_SUBCATEGORIA_102 = 46;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_SUBCATEGORIA_103 = 47;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_SUBCATEGORIA_200 = 48;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_SUBCATEGORIA_300 = 49;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_SUBCATEGORIA_400 = 50;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_SUBCATEGORIA_116 = 51;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_SUBCATEGORIA_115 = 52;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO = 53;
	
	// Ligacoes Consumo 5m3
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_SUBCATEGORIA_101 = 54;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_SUBCATEGORIA_102 = 55;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_SUBCATEGORIA_103 = 56;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_SUBCATEGORIA_200 = 57;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_SUBCATEGORIA_300 = 58;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_SUBCATEGORIA_400 = 59;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_SUBCATEGORIA_116 = 60;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_SUBCATEGORIA_115 = 61;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3 = 62;
	
	// Ligacoes Consumo Não Medido
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_SUBCATEGORIA_101 = 63;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_SUBCATEGORIA_102 = 64;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_SUBCATEGORIA_103 = 65;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_SUBCATEGORIA_200 = 66;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_SUBCATEGORIA_300 = 67;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_SUBCATEGORIA_400 = 68;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_SUBCATEGORIA_116 = 69;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_SUBCATEGORIA_115 = 70;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO = 71;
	
	// Ligacoes Consumo Media
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_SUBCATEGORIA_101 = 72;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_SUBCATEGORIA_102 = 73;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_SUBCATEGORIA_103 = 74;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_SUBCATEGORIA_200 = 75;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_SUBCATEGORIA_300 = 76;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_SUBCATEGORIA_400 = 77;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_SUBCATEGORIA_116 = 78;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_SUBCATEGORIA_115 = 79;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA = 80;
	
	// Ligacoes Quantidade Economias
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_SUBCATEGORIA_101 = 81;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_SUBCATEGORIA_102 = 82;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_SUBCATEGORIA_103 = 83;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_SUBCATEGORIA_200 = 84;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_SUBCATEGORIA_300 = 85;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_SUBCATEGORIA_400 = 86;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_SUBCATEGORIA_116 = 87;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_SUBCATEGORIA_115 = 88;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS = 89;
	
	public final static Integer INDEX_ID_GERENCIA = 90;
	public final static Integer INDEX_ID_UNIDADE_NEGOCIO = 91;
	public final static Integer INDEX_ID_LOCALIDADE = 92;
	public final static Integer INDEX_CODIGO_CENTRO_CUSTO = 93;
	
	public final static Integer TOTALIZACAO_ESTADO = 1;
	public final static Integer TOTALIZACAO_ESTADO_GERENCIA_REGIONAL = 2;
	public final static Integer TOTALIZACAO_ESTADO_UNIDADE_NEGOCIO = 3;
	public final static Integer TOTALIZACAO_ESTADO_LOCALIDADE = 5;
	public final static Integer TOTALIZACAO_GERENCIA_REGIONAL = 6;
	public final static Integer TOTALIZACAO_UNIDADE_NEGOCIO = 10;
	public final static Integer TOTALIZACAO_LOCALIDADE = 16;
	
	// Constantes do quadro de metas acumulado por exercicio
	// Ligacoes Cadastradas
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_DEZEMBRO = 0;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_JANEIRO = 1;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_FEVEREIRO = 2;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_MARCO = 3;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_ABRIL = 4;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_MAIO = 5;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_JUNHO = 6;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_JULHO = 7;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_AGOSTO = 8;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_SETEMBRO = 9;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_OUTUBRO = 10;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CADASTRADAS_NOVEMBRO = 11;
	
	// Ligacoes Cortadas
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_DEZEMBRO = 12;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_JANEIRO = 13;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_FEVEREIRO = 14;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_MARCO = 15;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_ABRIL = 16;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_MAIO = 17;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_JUNHO = 18;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_JULHO = 19;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_AGOSTO = 20;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_SETEMBRO = 21;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_OUTUBRO = 22;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CORTADAS_NOVEMBRO = 23;
	
	// Ligacoes Suprimidas
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_DEZEMBRO = 24;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_JANEIRO = 25;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_FEVEREIRO = 26;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_MARCO = 27;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_ABRIL = 28;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_MAIO = 29;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_JUNHO = 30;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_JULHO = 31;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_AGOSTO = 32;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_SETEMBRO = 33;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_OUTUBRO = 34;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_SUPRIMIDAS_NOVEMBRO = 35;
	
	// Ligacoes Ativas
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEZEMBRO = 36;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_JANEIRO = 37;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_FEVEREIRO = 38;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_MARCO = 39;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_ABRIL = 40;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_MAIO = 41;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_JUNHO = 42;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_JULHO = 43;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_AGOSTO = 44;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_SETEMBRO = 45;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_OUTUBRO = 46;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_NOVEMBRO = 47;
	
	// Ligacoes Ativas com débitos a mais de 3 meses
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_DEZEMBRO = 48;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_JANEIRO = 49;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_FEVEREIRO = 50;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_MARCO = 51;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_ABRIL = 52;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_MAIO = 53;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_JUNHO = 54;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_JULHO = 55;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_AGOSTO = 56;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_SETEMBRO = 57;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_OUTUBRO = 58;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_ATIVAS_DEBITOS_3M_NOVEMBRO = 59;
	
	// Ligacoes Consumo Medido
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_DEZEMBRO = 60;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_JANEIRO = 61;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_FEVEREIRO = 62;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_MARCO = 63;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_ABRIL = 64;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_MAIO = 65;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_JUNHO = 66;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_JULHO = 67;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_AGOSTO = 68;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_SETEMBRO = 69;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_OUTUBRO = 70;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIDO_NOVEMBRO = 71;
	
	// Ligacoes Consumo 5m3
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_DEZEMBRO = 72;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_JANEIRO = 73;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_FEVEREIRO = 74;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_MARCO = 75;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_ABRIL = 76;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_MAIO = 77;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_JUNHO = 78;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_JULHO = 79;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_AGOSTO = 80;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_SETEMBRO = 81;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_OUTUBRO = 82;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_5M3_NOVEMBRO = 83;
	
	// Ligacoes Consumo Não Medido
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_DEZEMBRO = 84;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_JANEIRO = 85;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_FEVEREIRO = 86;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_MARCO = 87;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_ABRIL = 88;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_MAIO = 89;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_JUNHO = 90;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_JULHO = 91;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_AGOSTO = 92;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_SETEMBRO = 93;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_OUTUBRO = 94;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_NAO_MEDIDO_NOVEMBRO = 95;
	
	// Ligacoes Consumo Media
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_DEZEMBRO = 96;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_JANEIRO = 97;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_FEVEREIRO = 98;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_MARCO = 99;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_ABRIL = 100;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_MAIO = 101;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_JUNHO = 102;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_JULHO = 103;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_AGOSTO = 104;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_SETEMBRO = 105;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_OUTUBRO = 106;
	public final static Integer INDEX_QUANTIDADE_LIGACOES_CONSUMO_MEDIA_NOVEMBRO = 107;
	
	// Ligacoes Quantidade Economias
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_DEZEMBRO = 108;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_JANEIRO = 109;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_FEVEREIRO = 110;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_MARCO = 111;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_ABRIL = 112;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_MAIO = 113;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_JUNHO = 114;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_JULHO = 115;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_AGOSTO = 116;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_SETEMBRO = 117;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_OUTUBRO = 118;
	public final static Integer INDEX_QUANTIDADE_ECONOMIAS_NOVEMBRO = 119;
	
	public final static Integer INDEX_ID_GERENCIA_EXERCICIO = 120;
	public final static Integer INDEX_ID_UNIDADE_NEGOCIO_EXERCICIO = 121;
	public final static Integer INDEX_ID_LOCALIDADE_EXERCICIO = 122;
	public final static Integer INDEX_CODIGO_CENTRO_CUSTO_EXERCICIO = 123;
	
	private static IRepositorioGerencial instancia;

	/**
	 * Construtor da classe RepositorioMicromedicaoHBM
	 */
	private RepositorioGerencialHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioGerencial getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioGerencialHBM();
		}

		return instancia;
	}
	
	
	
	
	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 09/06/2006
	 *
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List consultarComparativoResumosFaturamentoArrecadacaoPendencia(InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) throws ErroRepositorioException{
		
		//Integer anoMesReferencia = informarDadosGeracaoRelatorioConsultaHelper.getAnoMesReferencia();
		
		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try{/*
			String sqlTabelaEstrutura = "select "+
					  "'ESTADO'as estado, "+ 
					   "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, "+ 
					   "d.loca_id as elo_id, lpad(d.loca_id,3,0)||' - '||d.loca_nmlocalidade as elo, "+ 
					   "c.loca_id as loca_id, lpad(c.loca_id,3,0)||' - '||c.loca_nmlocalidade as localidade, "+ 
					   "f.stcm_id as stcm_id, lpad(f.stcm_cdsetorcomercial,3,0)||' - '||stcm_nmsetorcomercial as setor, "+ 
					   "g.qdra_id as qdra_id, lpad(g.qdra_nnquadra,3,0) as quadra "+
					"into TEMP table estrutura "+
					"from  cadastro.localidade c, cadastro.localidade d, cadastro.gerencia_regional e, cadastro.setor_comercial f, cadastro.quadra g "+
					"where c.loca_cdelo = d.loca_id "+ 
					"and   c.greg_id = e.greg_id "+
					"and   d.loca_id = f.loca_id "+
					"and   f.stcm_id = g.stcm_id "+ 
					"group by ,e.greg_id,LPAD(E.GREG_ID,3,0)||' - '||E.GREG_NMABREVIADO||' - '||E.GREG_NMREGIONAL,d.loca_id,LPAD(D.LOCA_ID,3,0)||' - '||D.LOCA_NMLOCALIDADE,c.loca_id,LPAD(C.LOCA_ID,3,0)||' - '||C.LOCA_NMLOCALIDADE,f.stcm_id,LPAD(F.STCM_CDSETORCOMERCIAL,3,0)||' - '||STCM_NMSETORCOMERCIAL,g.qdra_id,lpad(g.qdra_nnquadra,3,0) "+
					"union "+
					"select "+ 
					  "'ESTADO'as estado, "+ 
					  "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, "+ 
					  "d.loca_id as elo_id, lpad(d.loca_id,3,0)||' - '||d.loca_nmlocalidade as elo, "+ 
					  "c.loca_id as loca_id, lpad(c.loca_id,3,0)||' - '||c.loca_nmlocalidade as localidade, "+ 
					  "f.stcm_id as stcm_id, lpad(f.stcm_cdsetorcomercial,3,0)||' - '||stcm_nmsetorcomercial as setor, "+ 
					  "null,'TOTAL' as quadra "+
					"from  cadastro.localidade c, cadastro.localidade d, cadastro.gerencia_regional e, cadastro.setor_comercial f "+
					"where c.loca_cdelo = d.loca_id "+
					"and   c.greg_id = e.greg_id "+
					"and   d.loca_id = f.loca_id "+
					"union "+
					"select "+  
					  "'ESTADO'as estado, "+ 
					  "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, "+ 
					  "d.loca_id as elo_id, lpad(d.loca_id,3,0)||' - '||d.loca_nmlocalidade as elo, "+
					  "c.loca_id as loca_id, lpad(c.loca_id,3,0)||' - '||c.loca_nmlocalidade as localidade, "+ 
					  "null,'TOTAL' as setor, "+ 
					  "null,'TOTAL' as quadra "+
					"from  cadastro.localidade c, cadastro.localidade d, cadastro.gerencia_regional e "+
					"where c.loca_cdelo = d.loca_id "+
					"and   c.greg_id = e.greg_id "+
					"union "+
					"select "+ 
					  "'ESTADO'as estado, "+ 
					  "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, "+ 
					  "d.loca_id as elo_id, lpad(d.loca_id,3,0)||' - '||d.loca_nmlocalidade as elo, "+
					  "null,'TOTAL' as localidade, "+ 
					  "null,'TOTAL' as setor, "+ 
					  "null,'TOTAL' as quadra "+
					"from  cadastro.localidade c, cadastro.localidade d, cadastro.gerencia_regional e "+
					"where c.loca_cdelo = d.loca_id "+ 
					"and c.greg_id = e.greg_id "+
					"union "+
					"select "+ 
					  "'ESTADO'as estado, "+ 
					  "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, "+ 
					  "null,'TOTAL' as elo, "+ 
					  "null,'TOTAL' as localidade, "+ 
					  "null,'TOTAL' as setor, "+ 
					  "null,'TOTAL' as quadra "+
					"from  cadastro.gerencia_regional e "+
					"union "+
					"select "+ 
					  "'ESTADO'as estado, "+ 
					  "null,'TOTAL' as gerencia, "+ 
					  "null,'TOTAL' as elo, "+ 
					  "null,'TOTAL' as localidade, "+ 
					  "null,'TOTAL' as setor, "+ 
					  "null,'TOTAL' as quadra "+
					"from  cadastro.gerencia_regional e "+
					"order by 1,2,3,4,5,6,7,8,9,10,11";
			
			session.createSQLQuery(sqlTabelaEstrutura).executeUpdate();

			
			String sqlTabelaFaturamento = "select " + 
				  "'ESTADO'as estado,  " +
				   "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, " + 
				   "d.loca_id as elo_id, lpad(d.loca_id,3,0)||' - '||d.loca_nmlocalidade as elo,  " +
				   "c.loca_id as loca_id, lpad(c.loca_id,3,0)||' - '||c.loca_nmlocalidade as localidade, " + 
				   "f.stcm_id as stcm_id, lpad(f.stcm_cdsetorcomercial,3,0)||' - '||stcm_nmsetorcomercial as setor, " + 
				   "g.qdra_id as qdra_id, lpad(g.qdra_nnquadra,3,0) as quadra, " +
				"sum(a.rfts_qtcontas) as FatContas, sum(a.rfts_vlagua + a.rfts_vlesgoto + a.rfts_vldebitos - a.rfts_vlcreditos) as FatValor " +
				"into TEMP table faturamento " +
				"from  faturamento.resumo_faturamento_simulacao a, cadastro.localidade c, cadastro.localidade d, cadastro.gerencia_regional e, cadastro.setor_comercial f, cadastro.quadra g " +
				"where a.loca_id = c.loca_id  " +
				"and   c.loca_cdelo = d.loca_id  " +
				"and   a.greg_id = e.greg_id " +
				"and   a.stcm_id = f.stcm_id " +
				"and   a.qdra_id = g.qdra_id  " +
				"and   a.rfts_amreferencia="+anoMesReferencia+" "+
				"group by 1,2,3,4,5,6,7,8,9,10,11 " +
				"union " +
				"select  " +
				  "'ESTADO'as estado, " + 
				  "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, " + 
				  "d.loca_id as elo_id, lpad(d.loca_id,3,0)||' - '||d.loca_nmlocalidade as elo,  " +
				  "c.loca_id as loca_id, lpad(c.loca_id,3,0)||' - '||c.loca_nmlocalidade as localidade, " + 
				  "f.stcm_id as stcm_id, lpad(f.stcm_cdsetorcomercial,3,0)||' - '||stcm_nmsetorcomercial as setor, " + 
				  "null,'TOTAL' as quadra, " +
				"sum(a.rfts_qtcontas) as FatContas, sum(a.rfts_vlagua + a.rfts_vlesgoto + a.rfts_vldebitos - a.rfts_vlcreditos) as FatValor " +
				"from  faturamento.resumo_faturamento_simulacao a, cadastro.localidade c, cadastro.localidade d, cadastro.gerencia_regional e, cadastro.setor_comercial f " +
				"where a.loca_id = c.loca_id  " +
				"and   c.loca_cdelo = d.loca_id  " +
				"and   a.greg_id = e.greg_id " +
				"and   a.stcm_id = f.stcm_id " +
				"and   a.rfts_amreferencia ="+anoMesReferencia+ " "+
				"group by 1,2,3,4,5,6,7,8,9 " +
				"union " +
				"select  " +
				  "'ESTADO'as estado, " + 
				  "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, " + 
				  "d.loca_id as elo_id, lpad(d.loca_id,3,0)||' - '||d.loca_nmlocalidade as elo,  " +
				  "c.loca_id as loca_id, lpad(c.loca_id,3,0)||' - '||c.loca_nmlocalidade as localidade, " + 
				  "null,'LOCAL' as setor,  " +
				  "null,'LOCAL' as quadra, " +
				"sum(a.rfts_qtcontas) as FatContas, sum(a.rfts_vlagua + a.rfts_vlesgoto + a.rfts_vldebitos - a.rfts_vlcreditos) as FatValor " +
				"from  faturamento.resumo_faturamento_simulacao a, cadastro.localidade c, cadastro.localidade d, cadastro.gerencia_regional e " +
				"where a.loca_id = c.loca_id  " +
				"and   c.loca_cdelo = d.loca_id  " +
				"and   a.greg_id = e.greg_id " +
				"and   a.rfts_amreferencia ="+anoMesReferencia+" "+ 
				"group by 1,2,3,4,5,6,7 " +
				"union " +
				"select " +
				  "'ESTADO'as estado, " + 
				  "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, " + 
				  "d.loca_id as elo_id, lpad(d.loca_id,3,0)||' - '||d.loca_nmlocalidade as elo,  " +
				  "null,'LOCAL' as localidade,  " +
				  "null,'LOCAL' as setor,  " +
				  "null,'LOCAL' as quadra, " +
				"sum(a.rfts_qtcontas) as FatContas, sum(a.rfts_vlagua + a.rfts_vlesgoto + a.rfts_vldebitos - a.rfts_vlcreditos) as FatValor " +
				"from  faturamento.resumo_faturamento_simulacao a, cadastro.localidade c, cadastro.localidade d, cadastro.gerencia_regional e " +
				"where a.loca_id = c.loca_id  " +
				"and   c.loca_cdelo = d.loca_id  " +
				"and   a.greg_id = e.greg_id " +
				"and   a.rfts_amreferencia ="+anoMesReferencia+ " "+
				"group by 1,2,3,4,5 " +
				"union " +
				"select " +
				  "'ESTADO'as estado, " + 
				  "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, " + 
				  "null,'LOCAL' as elo,  " +
				  "null,'LOCAL' as localidade, " + 
				  "null,'LOCAL' as setor,  " +
				  "null,'LOCAL' as quadra, " +
				"sum(a.rfts_qtcontas) as FatContas, sum(a.rfts_vlagua + a.rfts_vlesgoto + a.rfts_vldebitos - a.rfts_vlcreditos) as FatValor " +
				"from  faturamento.resumo_faturamento_simulacao a, cadastro.gerencia_regional e " +
				"where a.greg_id = e.greg_id " +
				"and   a.rfts_amreferencia = "+anoMesReferencia+ " "+
				"group by 1,2,3 " +
				"union " +
				"select  " +
				  "'ESTADO'as estado, " + 
				  "null,'TOTAL' as gerencia, " + 
				  "null,'TOTAL' as elo,  " +
				  "null,'TOTAL' as localidade, " + 
				  "null,'TOTAL' as setor,  " +
				  "null,'TOTAL' as quadra, " +
				"sum(a.rfts_qtcontas) as FatContas, sum(a.rfts_vlagua + a.rfts_vlesgoto + a.rfts_vldebitos - a.rfts_vlcreditos) as FatValor " +
				"from  faturamento.resumo_faturamento_simulacao a, cadastro.gerencia_regional e " +
				"where a.greg_id = e.greg_id " +
				"and   a.rfts_amreferencia = "+anoMesReferencia+ " "+
				"group by 1 " +
				"order by 1,2,3,4,5,6,7,8,9,10,11 "; 
		
			session.createSQLQuery(sqlTabelaFaturamento).executeUpdate();
			
			String sqlTabelaArrecadacao = "select " + 
					  "'ESTADO' as estado," + 
					   "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, " + 
					   "d.loca_id as elo_id, lpad(d.loca_id,3,0)||' - '||d.loca_nmlocalidade as elo,  " +
					   "c.loca_id as loca_id, lpad(c.loca_id,3,0)||' - '||c.loca_nmlocalidade as localidade, " + 
					   "f.stcm_id as stcm_id, lpad(f.stcm_cdsetorcomercial,3,0)||' - '||stcm_nmsetorcomercial as setor, " + 
					   "g.qdra_id as qdra_id, lpad(g.qdra_nnquadra,3,0) as quadra, " +
					"sum(a.ardd_qtpagamentos) as ArrContas, sum(a.ardd_vlpagamentos) as ArrValor " +
					"into TEMP table arrecadacao " +
					"from  arrecadacao.arrecadacao_dados_diarios a, cadastro.localidade c, cadastro.localidade d, cadastro.gerencia_regional e, cadastro.setor_comercial f, cadastro.quadra g " +
					"where a.loca_id = c.loca_id  " +
					"and   c.loca_cdelo = d.loca_id  " +
					"and   a.greg_id = e.greg_id " +
					"and   a.stcm_id = f.stcm_id " +
					"and   a.qdra_id = g.qdra_id  " +
					"and   a.ardd_amreferenciaarrecadacao = "+anoMesReferencia+ " and a.dotp_id = 1 " +
					"group by 1,2,3,4,5,6,7,8,9,10,11 " +
					"union " +
					"select  " +
					  "'ESTADO'as estado, " + 
					  "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, " + 
					  "d.loca_id as elo_id, lpad(d.loca_id,3,0)||' - '||d.loca_nmlocalidade as elo,  " +
					  "c.loca_id as loca_id, lpad(c.loca_id,3,0)||' - '||c.loca_nmlocalidade as localidade, " + 
					  "f.stcm_id as stcm_id, lpad(f.stcm_cdsetorcomercial,3,0)||' - '||stcm_nmsetorcomercial as setor, " + 
					  "null,'TOTAL' as quadra, " +
					"sum(a.ardd_qtpagamentos) as ArrContas, sum(a.ardd_vlpagamentos) as ArrValor " +
					"from  arrecadacao.arrecadacao_dados_diarios a, cadastro.localidade c, cadastro.localidade d, cadastro.gerencia_regional e, cadastro.setor_comercial f " +
					"where a.loca_id = c.loca_id  " +
					"and   c.loca_cdelo = d.loca_id  " +
					"and   a.greg_id = e.greg_id " +
					"and   a.stcm_id = f.stcm_id " +
					"and   a.ardd_amreferenciaarrecadacao = "+anoMesReferencia+ " and a.dotp_id = 1 " +
					"group by 1,2,3,4,5,6,7,8,9 " +
					"union " +
					"select  " +
					  "'ESTADO'as estado, " + 
					  "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, " + 
					  "d.loca_id as elo_id, lpad(d.loca_id,3,0)||' - '||d.loca_nmlocalidade as elo,  " +
					  "c.loca_id as loca_id, lpad(c.loca_id,3,0)||' - '||c.loca_nmlocalidade as localidade, " + 
					  "null,'TOTAL' as setor,  " +
					  "null,'TOTAL' as quadra, " +
					"sum(a.ardd_qtpagamentos) as ArrContas, sum(a.ardd_vlpagamentos) as ArrValor " +
					"from  arrecadacao.arrecadacao_dados_diarios a, cadastro.localidade c, cadastro.localidade d, cadastro.gerencia_regional e " +
					"where a.loca_id = c.loca_id  " +
					"and   c.loca_cdelo = d.loca_id  " +
					"and   a.greg_id = e.greg_id " +
					"and   a.ardd_amreferenciaarrecadacao = "+anoMesReferencia+ " and a.dotp_id = 1 " +
					"group by 1,2,3,4,5,6,7 " +
					"union " +
					"select  " +
					  "'ESTADO'as estado, " + 
					  "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, " + 
					  "d.loca_id as elo_id, lpad(d.loca_id,3,0)||' - '||d.loca_nmlocalidade as elo,  " +
					  "null,'TOTAL' as localidade,  " +
					  "null,'TOTAL' as setor,  " +
					  "null,'TOTAL' as quadra, " +
					"sum(a.ardd_qtpagamentos) as ArrContas, sum(a.ardd_vlpagamentos) as ArrValor " +
					"from  arrecadacao.arrecadacao_dados_diarios a, cadastro.localidade c, cadastro.localidade d, cadastro.gerencia_regional e " +
					"where a.loca_id = c.loca_id  " +
					"and   c.loca_cdelo = d.loca_id  " +
					"and   a.greg_id = e.greg_id " +
					"and   a.ardd_amreferenciaarrecadacao = "+anoMesReferencia+ " and a.dotp_id = 1 " +
					"group by 1,2,3,4,5 " +
					"union " +
					"select  " +
					  "'ESTADO'as estado, " + 
					  "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, " + 
					  "null,'TOTAL' as elo,  " +
					  "null,'TOTAL' as localidade, " + 
					  "null,'TOTAL' as setor,  " +
					  "null,'TOTAL' as quadra, " +
					"sum(a.ardd_qtpagamentos) as ArrContas, sum(a.ardd_vlpagamentos) as ArrValor " +
					"from  arrecadacao.arrecadacao_dados_diarios a, cadastro.gerencia_regional e " +
					"where a.greg_id = e.greg_id " +
					"and   a.ardd_amreferenciaarrecadacao = "+anoMesReferencia+ " and a.dotp_id = 1 " +
					"group by 1,2,3 " +
					"union " +
					"select " +
					 "'ESTADO'as estado, " + 
					  "null,'TOTAL' as gerencia, " + 
					  "null,'TOTAL' as elo,  " +
					  "null,'TOTAL' as localidade, " + 
					  "null,'TOTAL' as setor,  " +
					  "null,'TOTAL' as quadra, " +
					"sum(a.ardd_qtpagamentos) as ArrContas, sum(a.ardd_vlpagamentos) as ArrValor " +
					"from  arrecadacao.arrecadacao_dados_diarios a, cadastro.gerencia_regional e " +
					"where a.greg_id = e.greg_id " +
					"and a.ardd_amreferenciaarrecadacao = "+anoMesReferencia+ " and a.dotp_id = 1 " +
					"group by 1 " +
					"order by 1,2,3,4,5,6,7,8,9,10,11 "; 

			session.createSQLQuery(sqlTabelaArrecadacao).executeUpdate();
				
				
			String sqlTabelaPendencia = "select "+ 
				  "'ESTADO'as estado, "+ 
				   "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, "+ 
				   "d.loca_id as elo_id, lpad(d.loca_id,3,0)||' - '||d.loca_nmlocalidade as elo, "+
				   "c.loca_id as loca_id, lpad(c.loca_id,3,0)||' - '||c.loca_nmlocalidade as localidade, "+ 
				   "f.stcm_id as stcm_id, lpad(f.stcm_cdsetorcomercial,3,0)||' - '||stcm_nmsetorcomercial as setor, "+ 
				   "g.qdra_id as qdra_id, lpad(g.qdra_nnquadra,3,0) as quadra, "+
				"sum(a.rpen_qtdocumentos) as PenContas, sum(a.rpen_vldebito) as PenValor "+
				"into TEMP table pendencia "+
				"from  cobranca.resumo_pendencia a, cadastro.localidade c, cadastro.localidade d, cadastro.gerencia_regional e, cadastro.setor_comercial f, cadastro.quadra g "+
				"where a.loca_id = c.loca_id  "+
				"and   c.loca_cdelo = d.loca_id  "+
				"and   a.greg_id = e.greg_id "+
				"and   a.stcm_id = f.stcm_id "+
				"and   a.qdra_id = g.qdra_id  "+
				"and   a.rpen_amreferencia = "+anoMesReferencia+ " and a.dotp_id = 1 "+
				"group by 1,2,3,4,5,6,7,8,9,10,11 "+
				"union "+
				"select  "+
				  "'ESTADO'as estado, "+ 
				   "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, "+ 
				   "d.loca_id as elo_id, lpad(d.loca_id,3,0)||' - '||d.loca_nmlocalidade as elo,  "+
				   "c.loca_id as loca_id, lpad(c.loca_id,3,0)||' - '||c.loca_nmlocalidade as localidade, "+ 
				   "f.stcm_id as stcm_id, lpad(f.stcm_cdsetorcomercial,3,0)||' - '||stcm_nmsetorcomercial as setor, "+ 
				   "null,'TOTAL' as quadra, "+
				"sum(a.rpen_qtdocumentos) as PenContas, sum(a.rpen_vldebito) as PenValor "+
				"from  cobranca.resumo_pendencia a, cadastro.localidade c, cadastro.localidade d, cadastro.gerencia_regional e, cadastro.setor_comercial f "+
				"where a.loca_id = c.loca_id  "+
				"and   c.loca_cdelo = d.loca_id  "+
				"and   a.greg_id = e.greg_id "+
				"and   a.stcm_id = f.stcm_id "+
				"and   a.rpen_amreferencia = "+anoMesReferencia+ " and a.dotp_id = 1 "+
				"group by 1,2,3,4,5,6,7,8,9 "+
				"union "+
				"select  "+
				  "'ESTADO'as estado, "+ 
				   "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, "+ 
				   "d.loca_id as elo_id, lpad(d.loca_id,3,0)||' - '||d.loca_nmlocalidade as elo,  "+
				   "c.loca_id as loca_id, lpad(c.loca_id,3,0)||' - '||c.loca_nmlocalidade as localidade, "+ 
				   "null,'TOTAL' as setor,  "+
				   "null,'TOTAL' as quadra, "+
				"sum(a.rpen_qtdocumentos) as PenContas, sum(a.rpen_vldebito) as PenValor "+
				"from  cobranca.resumo_pendencia a, cadastro.localidade c, cadastro.localidade d, cadastro.gerencia_regional e "+
				"where a.loca_id = c.loca_id  "+
				"and   c.loca_cdelo = d.loca_id  "+
				"and   a.greg_id = e.greg_id "+
				"and   a.rpen_amreferencia = "+anoMesReferencia+ " and a.dotp_id = 1 "+
				"group by 1,2,3,4,5,6,7 "+
				"union "+
				"select  "+
				  "'ESTADO'as estado, "+ 
				   "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, "+ 
				   "d.loca_id as elo_id, lpad(d.loca_id,3,0)||' - '||d.loca_nmlocalidade as elo,  "+
				   "null,'TOTAL' as localidade,  "+
				   "null,'TOTAL' as setor,  "+
				   "null,'TOTAL' as quadra, "+
				"sum(a.rpen_qtdocumentos) as PenContas, sum(a.rpen_vldebito) as PenValor "+
				"from  cobranca.resumo_pendencia a, cadastro.localidade c, cadastro.localidade d, cadastro.gerencia_regional e "+
				"where a.loca_id = c.loca_id  "+
				"and   c.loca_cdelo = d.loca_id  "+
				"and   a.greg_id = e.greg_id "+
				"and   a.rpen_amreferencia = "+anoMesReferencia+ " and a.dotp_id = 1 "+
				"group by 1,2,3,4,5 "+
				"union "+
				"select  "+
				  "'ESTADO'as estado, "+ 
				   "e.greg_id as greg_id, lpad(e.greg_id,3,0)||' - '||e.greg_nmabreviado||' - '||e.greg_nmregional as gerencia, "+ 
				   "null,'LOCAL' as elo,  "+
				   "null,'LOCAL' as localidade, "+ 
				   "null,'LOCAL' as setor,  "+
				   "null,'LOCAL' as quadra, "+
				"sum(a.rpen_qtdocumentos) as PenContas, sum(a.rpen_vldebito) as PenValor "+
				"from  cobranca.resumo_pendencia a, cadastro.gerencia_regional e "+
				"where a.greg_id = e.greg_id "+
				"and   a.rpen_amreferencia = "+anoMesReferencia+ " and a.dotp_id = 1 "+
				"group by 1,2,3 "+
				"union "+
				"select  "+
				  "'ESTADO'as estado, "+ 
				  "null,'LOCAL' as gerencia, "+ 
				  "null,'LOCAL' as elo,  "+
				  "null,'LOCAL' as localidade, "+ 
				  "null,'LOCAL' as setor,  "+
				  "null,'LOCAL' as quadra, "+
				"sum(a.rpen_qtdocumentos) as PenContas, sum(a.rpen_vldebito) as PenValor "+
				"from  cobranca.resumo_pendencia a, cadastro.gerencia_regional e "+
				"where a.greg_id = e.greg_id "+
				"and   a.rpen_amreferencia ="+anoMesReferencia+ " and a.dotp_id = 1 "+
				"group by 1 "+
				"order by 1,2,3,4,5,6,7,8,9,10,11 ";
	
			session.createSQLQuery(sqlTabelaPendencia).executeUpdate();
				
			
			String resultadoSemPercentuais = "select a.estado, "+ 
							   "a.greg_id, "+
						       "a.gerencia, "+
						       "a.elo_id, "+        
						       "a.elo, "+ 
						       "a.loca_id, "+
						       "a.localidade, "+ 
						       "a.stcm_id, "+
						       "a.setor, "+ 
						       "a.qdra_id, "+
						       "a.quadra, "+
						"b.fatcontas, b.fatvalor, c.arrcontas, c.arrvalor, d.pencontas, d.penvalor "+
						"into temp resultado "+
						"from estrutura a "+
						"left join faturamento b on a.estado = b.estado "+ 
						                       "and a.gerencia = b.gerencia "+ 
						                       "and a.elo = b.elo  "+
						                       "and a.localidade = b.localidade "+
						                       "and a.setor = b.setor "+
						                       "and a.quadra = b.quadra  "+ 
						"left  join arrecadacao c on a.estado = c.estado "+
						                       "and a.gerencia = c.gerencia "+ 
						                       "and a.elo = c.elo "+   
						                       "and a.localidade = c.localidade "+
						                       "and a.setor = c.setor "+
						                       "and a.quadra = c.quadra "+  
						"left  join pendencia d on a.estado = d.estado "+
						                       "and a.gerencia = d.gerencia "+ 
						                       "and a.elo = d.elo "+   
						                       "and a.localidade = d.localidade "+
						                       "and a.setor = d.setor "+
						                       "and a.quadra = d.quadra ";  

			session.createSQLQuery(resultadoSemPercentuais).executeUpdate();
				
			*/
			String resultadoComPercentuais = "select a.estado, "+ 
						    "a.greg_id, "+
					       "a.gerencia, "+
					       "a.elo_id, "+        
					       "a.elo, "+ 
					       "a.loca_id, "+
					       "a.localidade, "+ 
					       "a.stcm_id, "+
					       "a.setor,  "+
					       "a.qdra_id, "+
					       "a.quadra, "+
					"a.fatcontas, a.fatvalor, a.arrcontas, a.arrvalor,(a.arrcontas/a.fatcontas)* 100 , (a.arrvalor/a.fatvalor) * 100, a.pencontas, a.penvalor, "+
					"(a.pencontas/a.fatcontas), (a.penvalor/a.fatvalor) "+
					"into temp resultadocompercentual "+
					"from resultado a "+
					"where (a.fatcontas is not null "+
					"and    a.fatcontas > 0) "+
					"and   (a.fatvalor is not null "+
					"and    a.fatvalor  > 0) "+
					"union "+
					"select a.estado, "+
							"a.greg_id, "+
					       "a.gerencia, "+
					       "a.elo_id, "+        
					       "a.elo, "+ 
					       "a.loca_id, "+
					       "a.localidade, "+ 
					       "a.stcm_id, "+
					       "a.setor, "+ 
					       "a.qdra_id, "+
					       "a.quadra, "+
					"a.fatcontas, a.fatvalor, a.arrcontas, a.arrvalor,0 , 0, a.pencontas, a.penvalor, "+
					"0, 0 "+
					"from resultado a "+
					"where (a.fatcontas is  null "+
					"or a.fatcontas = 0) "+
					"or (a.fatvalor is  null "+
					"or a.fatvalor  = 0) "+
					"order by 1,2,3,4,5,6,7,8,9,10,11";
				
			session.createSQLQuery(resultadoComPercentuais).executeUpdate();
			
			
			//OPÇÕES DE TOTALIZADOR
			String sqlConsultar = "select fatcontas,fatvalor,arrcontas,arrvalor,percontas,pervalor,pencontas,penvalor,nvezesfatcontas,nvezesfatvalor from resultadocompercentual ";
			switch (informarDadosGeracaoRelatorioConsultaHelper.getOpcaoTotalizacao().intValue()) {
			
			case ConstantesSistema.CODIGO_GRUPO_FATURAMENTO:
				sqlConsultar = sqlConsultar + "where " ;
				break;
			case ConstantesSistema.CODIGO_GERENCIA_REGIONAL:
				sqlConsultar = sqlConsultar + "where greg_id="+informarDadosGeracaoRelatorioConsultaHelper.getGerenciaRegional()+"and elo_id is null" ;
				break;
			case ConstantesSistema.CODIGO_ELO_POLO:
				sqlConsultar = sqlConsultar + "where " ;
				break;
			case ConstantesSistema.CODIGO_LOCALIDADE:
				sqlConsultar = sqlConsultar + "where " ;
				break;
			case ConstantesSistema.CODIGO_SETOR_COMERCIAL:
				sqlConsultar = sqlConsultar + "where " ;
				break;
			case ConstantesSistema.CODIGO_QUADRA:
				sqlConsultar = sqlConsultar + "where " ;
				break;
				
			case ConstantesSistema.CODIGO_ESTADO:
				sqlConsultar = sqlConsultar + "where greg_id is null" ;
				break;
		}
			
			
			retorno = session.createSQLQuery(resultadoComPercentuais)
			.addScalar("fatcontas", Hibernate.INTEGER)
			.addScalar("fatvalor", Hibernate.BIG_DECIMAL)
			.addScalar("arrcontas", Hibernate.INTEGER)
			.addScalar("arrvalor", Hibernate.BIG_DECIMAL)
			.addScalar("percontas", Hibernate.DOUBLE)
			.addScalar("pervalor", Hibernate.DOUBLE)
			.addScalar("pencontas", Hibernate.INTEGER)
			.addScalar("penvalor", Hibernate.BIG_DECIMAL)
			.addScalar("nvezesfatcontas", Hibernate.DOUBLE)
			.addScalar("nvezesfatvalor", Hibernate.DOUBLE).list();
			
			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção com os resultados da pesquisa
		return retorno;
		
	}
	
	
	/**
	 * Pesquisa o valor e a quantidade de contas do resumo da faturamento
	 *
	 * [UC0350] - Consultar Comparativo entre os Resumos do Faturamento, Arrecadação e da Pendência.
	 *
	 * @author Pedro Alexandre
	 * @date 09/06/2006
	 *
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List consultarResumoFaturamento(InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) throws ErroRepositorioException{
		// Cria a coleção de retorno
		List retorno = new ArrayList();
		
		// Obtém a sessão
		Session session = HibernateUtil.getSession();
		
		// A query abaixo realiza uma consulta a tabela de ResumoAnaliseFaturamento
		try{
			//Gera o sql para acumular o valor e a quantidade das contas 
			GeradorSqlRelatorio geradorSqlRelatorio = new GeradorSqlRelatorio(GeradorSqlRelatorio.RESUMO_FATURAMENTO,informarDadosGeracaoRelatorioConsultaHelper);
			
			//Concatena o sql gerado
			String sql = geradorSqlRelatorio.sqlNivelUm(geradorSqlRelatorio
					.getNomeCampoFixo(), geradorSqlRelatorio
					.getNomeTabelaFixo(), geradorSqlRelatorio
					.getNomeTabelaFixoTotal(), "'"+informarDadosGeracaoRelatorioConsultaHelper.getDescricaoOpcaoTotalizacao()+"'",
					"",
					"",
					"", false, false);
			
			//Faz a pesquisa
			retorno = session.createSQLQuery(sql)			
		    .addScalar("fatcontas", Hibernate.INTEGER)
		    .addScalar("fatvalor", Hibernate.BIG_DECIMAL).list();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// Retorna a coleção com os resultados da pesquisa
		return retorno;
	}
	
	
	/**
	 * Pesquisa o valor e a quantidade de contas do resumo da arrecadação
	 *
	 * [UC0350] - Consultar Comparativo entre os Resumos do Faturamento, Arrecadação e da Pendência.
	 *
	 * @author Pedro Alexandre
	 * @date 09/06/2006
	 *
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List consultarResumoArrecadacao(InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) throws ErroRepositorioException{
		// Cria a coleção de retorno
		List retorno = new ArrayList();
		
		// Obtém a sessão
		Session session = HibernateUtil.getSession();
		
		// A query abaixo realiza uma consulta a tabela de ResumoAnaliseFaturamento
		try{
			//Gera o sql para acumular o valor e a quantidade das contas 
			GeradorSqlRelatorio geradorSqlRelatorio = new GeradorSqlRelatorio(GeradorSqlRelatorio.RESUMO_ARRECADACAO,informarDadosGeracaoRelatorioConsultaHelper);
			
			//Concatena o sql gerado
			String sql = geradorSqlRelatorio.sqlNivelUm(geradorSqlRelatorio
					.getNomeCampoFixo(), geradorSqlRelatorio
					.getNomeTabelaFixo(), geradorSqlRelatorio
					.getNomeTabelaFixoTotal(), "'"+informarDadosGeracaoRelatorioConsultaHelper.getDescricaoOpcaoTotalizacao()+"'",
					"",
					"",
					"", false, false);
			
			//Faz a pesquisa
			retorno = session.createSQLQuery(sql)			
		    .addScalar("arrcontas", Hibernate.INTEGER)
		    .addScalar("arrvalor", Hibernate.BIG_DECIMAL).list();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// Retorna a coleção com os resultados da pesquisa
		return retorno;
	}

	
	/**
	 * Pesquisa o valor e a quantidade de contas do resumo da pendência.
	 *
	 * [UC0350] - Consultar Comparativo entre os Resumos do Faturamento, Arrecadação e da Pendência.
	 *
	 * @author Pedro Alexandre
	 * @date 09/06/2006
	 *
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List consultarComparativoResumoPendencia(InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) throws ErroRepositorioException{
		// Cria a coleção de retorno
		List retorno = new ArrayList();
		
		// Obtém a sessão
		Session session = HibernateUtil.getSessionGerencial();
		
		// A query abaixo realiza uma consulta a tabela de ResumoAnaliseFaturamento
		try{
			//Gera o sql para acumular o valor e a quantidade das contas 
			GeradorSqlRelatorio geradorSqlRelatorio = new GeradorSqlRelatorio(GeradorSqlRelatorio.RESUMO_PENDENCIA,informarDadosGeracaoRelatorioConsultaHelper);
			
			//Concatena o sql gerado
			String sql = geradorSqlRelatorio.sqlNivelUm(geradorSqlRelatorio
					.getNomeCampoFixo(), geradorSqlRelatorio
					.getNomeTabelaFixo(), geradorSqlRelatorio
					.getNomeTabelaFixoTotal(), "'"+informarDadosGeracaoRelatorioConsultaHelper.getDescricaoOpcaoTotalizacao()+"'",
					"",
					"",
					"", false, false);
			
			//Faz a pesquisa
			retorno = session.createSQLQuery(sql)			
		    .addScalar("pencontas", Hibernate.INTEGER)
		    .addScalar("penvalor", Hibernate.BIG_DECIMAL).list();

			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// Retorna a coleção com os resultados da pesquisa
		return retorno;
	}
	
	
	/**
	 * Pesquisa os valores necessarios na tabela un_resumo_ligacao_economia.
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Rafael Pinto
	 * @date 20/11/2006
	 *
	 * @param FiltrarRelatorioOrcamentoSINPHelper
	 * @return Collection<Object[35]>
	 * 
	 *  Object[0] - aguaTotalLigacoesCadastradas
	 *  Object[1] - esgotoTotalLigacoesCadastradas
	 *  Object[2] - esgotoTotalLigacoesCadastradasConvencional
	 *  Object[3] - aguaTotalLigacoesAtivas
	 *  Object[4] - esgotoTotalLigCadasCond
	 *  Object[5] - aguaTotalLigacoesMedidas
	 *  Object[6] - esgotoTotalLigacoesAtivasConvencional
	 *  Object[7] - aguaTotalLigacoesComHidrometro
	 *  Object[8] - esgotoTotalLigacoesAtivasCondominial
	 *  Object[9] - aguaTotalLigacoesResidencialCadastradas
	 *  Object[10] - esgotoTotalLigacoesResidencialCadastradas
	 *  Object[11] - aguaTotalLigacoesDesligadas
	 *  Object[12] - aguaTotalEconomiasCadastradas
	 *  Object[13] - esgotoTotalEconomiasCadastradasConverncional
	 *  Object[14] - aguaTotalEconomiasAtivas
	 *  Object[15] - aguaTotalEconomiasCadastradasCondominial
	 *  Object[16] - aguaTotalEconomiasAtivasMedidas
	 *  Object[17] - esgotoTotalEconomiasAtivasConvencional
	 *  Object[18] - aguaTotalEconomiasResidencialCadastradas
	 *  Object[19] - esgotoTotalEconomiasAtivasCondominial
	 *  Object[20] - aguaTotalEconomiasResidencialAtivasMicromedidas
	 *  Object[21] - esgotoTotalEconomiasResidencialCadastradas
	 *  Object[22] - aguaTotalEconomiasResidencialAtivas
	 *  Object[23] - esgotoTotalEconomiasResidencialAtivas
	 *  Object[24] - aguaTotalEconomiasComercialAtivas
	 *  Object[25] - esgotoTotalEconomiasComercialAtivas
	 *  Object[26] - aguaTotalEconomiasIndustrialAtivas
	 *  Object[27] - esgotoTotalEconomiasIndustrialAtivas
	 *  Object[28] - aguaTotalEconomiasPublicoAtivas
	 *  Object[29] - esgotoTotalEconomiasPublicoAtivas
	 *  Object[30] - aguaTotalEconomiasRuralAtivas
	 *  Object[31] - aguaTotalLigacoesSuprimidas
	 *  Object[32] - campoSelecionado pode ser (loca_id,uneg_id,greg_id)
	 *   
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarRelatorioOrcamentoSINPResumoLigacaoEconomia(FiltrarRelatorioOrcamentoSINPHelper filtrarRelatorioOrcamentoSINPHelper) 
		throws ErroRepositorioException {
		
		Object[] retorno = null;
		
		int anoMes = filtrarRelatorioOrcamentoSINPHelper.getAnoMesReferencia();
		
		Integer localidade = filtrarRelatorioOrcamentoSINPHelper.getLocalidade();
		Integer unidade = filtrarRelatorioOrcamentoSINPHelper.getUnidadeNegocio();
		Integer gerencia = filtrarRelatorioOrcamentoSINPHelper.getGerenciaRegional();
		Integer municipio = filtrarRelatorioOrcamentoSINPHelper.getMunicipio();
		Integer opcaoTotalizacao = filtrarRelatorioOrcamentoSINPHelper.getOpcaoTotalizacao();
		String joinLocalidadeMunicipio = "  left join cadastro.g_localidade loc on ( rle.loca_id = loc.loca_id ) " + 
			"  left join cadastro.g_municipio muni on ( muni.muni_id = loc.muni_idprincipal ) ";
		if(opcaoTotalizacao == 26 || opcaoTotalizacao == 19){
			joinLocalidadeMunicipio = "  inner join cadastro.g_localidade loc on ( rle.loca_id = loc.loca_id ) " + 
				"  inner join cadastro.g_municipio muni on ( muni.muni_id = loc.muni_idprincipal ) ";
		}
		String groupBy = filtrarRelatorioOrcamentoSINPHelper.getGroupBy();
		String campoSelecionado = null;
		
		Session session = HibernateUtil.getSessionGerencial();

		try{ 
			String sql = 
				"SELECT " +
				"  SUM(CASE WHEN las.last_iccadastradaagua = 1 THEN rele_qtligacoes ELSE 0 END) AS aguaTotalLigacoesCadastradas, " +
				"  SUM(CASE WHEN les.lest_iccadastradaesgoto = 1 THEN rele_qtligacoes ELSE 0 END) AS esgotoTotalLigacoesCadastradas, " +
				"  SUM(CASE WHEN lepf_id <> 2 AND les.lest_iccadastradaesgoto = 1 THEN rele_qtligacoes ELSE 0 END) AS esgotototalligacoescadastrada2, " +
				"  SUM(CASE WHEN las.last_icativaagua = 1 THEN rele_qtligacoes ELSE 0 END) AS aguaTotalLigacoesAtivas, " +
				"  SUM(CASE WHEN lepf_id = 2 AND les.lest_iccadastradaesgoto = 1 THEN rele_qtligacoes ELSE 0 END) AS esgotoTotalLigCadasCond, " +
				"  SUM(CASE WHEN rele_ichidrometro=1 AND las.last_icativaagua = 1 THEN rele_qtligacoes ELSE 0 END) AS aguaTotalLigacoesMedidas, " +
				"  SUM(CASE WHEN lepf_id <> 2 and les.lest_icativaesgoto = 1 THEN rele_qtligacoes ELSE 0 END) AS esgotototalligacoesativasconve, " +
				"  SUM(CASE WHEN rele_ichidrometro=1 THEN rele_qtligacoes ELSE 0 END) AS aguaTotalLigacoesComHidrometro, " +
				"  SUM(CASE WHEN lepf_id = 2 AND les.lest_icativaesgoto = 1 THEN rele_qtligacoes ELSE 0 END) AS esgotototalligacoesativascondo, " +
				"  SUM(CASE WHEN rle.catg_id = 1 AND las.last_iccadastradaagua = 1 THEN rele_qtligacoes ELSE 0 END) AS aguatotalligacoesresidencialca, " +
				"  SUM(CASE WHEN rle.catg_id = 1 AND les.lest_iccadastradaesgoto = 1 THEN rele_qtligacoes ELSE 0 END) AS esgotototalligacoesresidencial, " +
				"  SUM(CASE WHEN las.last_icdesligadaagua = 1 THEN rele_qtligacoes ELSE 0 END) AS aguaTotalLigacoesDesligadas, " +
				"  SUM(CASE WHEN las.last_iccadastradaagua = 1 THEN rele_qteconomias ELSE 0 END) AS aguaTotalEconomiasCadastradas, " +
				"  SUM(CASE WHEN lepf_id <> 2 AND les.lest_iccadastradaesgoto = 1 THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiascadastrad2, " +
				"  SUM(CASE WHEN las.last_icativaagua = 1 THEN rele_qteconomias ELSE 0 END) AS aguaTotalEconomiasAtivas, " +
				"  SUM(CASE WHEN lepf_id = 2 and les.lest_iccadastradaesgoto = 1 THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiascadastrada, " +
				"  SUM(CASE WHEN rele_ichidrometro=1 and las.last_icativaagua = 1 THEN rele_qteconomias ELSE 0 END) AS aguatotaleconomiasativasmedida, " +
				"  SUM(CASE WHEN lepf_id <> 2 AND rle.lest_id = 3 THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiasativasconv, " +
				"  SUM(CASE WHEN lepf_id = 2 AND rle.lest_id = 3 THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiasativascond, " +
				"  SUM(CASE WHEN rle.catg_id=1 AND sub.scat_icrural = 2 AND las.last_iccadastradaagua = 1 THEN rele_qteconomias ELSE 0 END) AS aguatotaleconomiasresidencialc, " +
				"  SUM(CASE WHEN lepf_id = 2 AND rle.lest_id = 3 THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiasativascond, " +
				"  SUM(CASE WHEN rele_ichidrometro = 1 AND rle.catg_id=1 and sub.scat_icrural = 2 and las.last_icativaagua = 1 THEN rele_qteconomias ELSE 0 END) AS aguatotaleconomiasresidencial2, " +
				"  SUM(CASE WHEN rle.catg_id=1 and sub.scat_icrural = 2 and les.lest_iccadastradaesgoto = 1 THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiasresidenci2, " +
				"  SUM(CASE WHEN rle.catg_id=1 and sub.scat_icrural = 2 and las.last_icativaagua = 1 THEN rele_qteconomias ELSE 0 END) AS aguatotaleconomiasresidenciala, " +
				"  SUM(CASE WHEN rle.catg_id=1 and sub.scat_icrural = 2 and les.lest_icativaesgoto = 1 THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiasresidencia, " +
				"  SUM(CASE WHEN rle.catg_id=2 and las.last_icativaagua = 1 THEN rele_qteconomias ELSE 0 END) AS aguatotaleconomiascomercialati, " +
				"  SUM(CASE WHEN rle.catg_id=2 and les.lest_icativaesgoto = 1 THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiascomerciala, " +
				"  SUM(CASE WHEN rle.catg_id=3 and las.last_icativaagua = 1 THEN rele_qteconomias ELSE 0 END) AS aguatotaleconomiasindustrialat, " +
				"  SUM(CASE WHEN rle.lest_id = 3 AND rle.catg_id=3 THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiasindustrial, " +
				"  SUM(CASE WHEN rle.catg_id=4 and las.last_icativaagua = 1 THEN rele_qteconomias ELSE 0 END) AS aguatotaleconomiaspublicoativa, " +
				"  SUM(CASE WHEN rle.catg_id=4 and les.lest_icativaesgoto = 1 THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiaspublicoati, " +
				"  SUM(CASE WHEN rle.catg_id=1 and las.last_icativaagua = 1 and sub.scat_icrural = 1 THEN rele_qteconomias ELSE 0 END) AS aguaTotalEconomiasRuralAtivas, " +
				"  SUM(CASE WHEN rle.last_id in (6,7,8) THEN rele_qtligacoes ELSE 0 END) AS aguaTotalLigacoesSuprimidas, " +
				"  SUM(CASE WHEN rle.catg_id=1 and les.lest_icativaesgoto = 1 and sub.scat_icrural = 1 THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiasruralativa, " +
                "  SUM( rle.rele_qtligacoesnovasagua ) AS aguaTotalLigacoesNovas, " +
                "  SUM( rle.rele_qtligacoesnovasesgoto ) AS esgotoTotalLigacoesNovas " +
				"FROM " +
				"  cadastro.un_res_lig_econ rle " +
				joinLocalidadeMunicipio +
				"  left join atendimentopublico.g_ligacao_agua_situacao las on ( rle.last_id = las.last_id ) " +
				"  left join atendimentopublico.g_lig_esgoto_sit les on ( rle.lest_id = les.lest_id ) " +
				"  left join cadastro.g_subcategoria sub on ( rle.scat_id = sub.scat_id ) " +
				"WHERE rele_amreferencia = :anoMes ";				
			
			// Localidade
			if (localidade != null) {
				sql += " AND rle.loca_id = " + localidade;
				campoSelecionado = "rle.loca_id";
			}

			// Unidade de Negocio
			if (unidade != null) {
				sql += " AND rle.uneg_id = " + unidade;
				campoSelecionado = "rle.uneg_id";
			}

			// Gerencia Regional
			if (gerencia != null) {
				sql += " AND rle.greg_id = " + gerencia;
				campoSelecionado = "rle.greg_id";
			}

			//Caso a pesquisa seja agrupada por município
			if(municipio != null){
				sql += " AND muni.muni_id = " + municipio;
				campoSelecionado = " muni.muni_id";
			}
			
			if(!groupBy.equals("")){
				sql += " GROUP BY "+campoSelecionado
					+ " ORDER BY "+campoSelecionado;
			}
			
			//Faz a pesquisa
			retorno = (Object[]) session.createSQLQuery(sql).
				addScalar("aguaTotalLigacoesCadastradas", Hibernate.INTEGER).
				addScalar("esgotoTotalLigacoesCadastradas", Hibernate.INTEGER).
				addScalar("esgotototalligacoescadastrada2", Hibernate.INTEGER).
				addScalar("aguaTotalLigacoesAtivas", Hibernate.INTEGER).
				addScalar("esgotoTotalLigCadasCond", Hibernate.INTEGER).
				addScalar("aguaTotalLigacoesMedidas", Hibernate.INTEGER).
				addScalar("esgotototalligacoesativasconve", Hibernate.INTEGER).
				addScalar("aguaTotalLigacoesComHidrometro", Hibernate.INTEGER).
				addScalar("esgotototalligacoesativascondo", Hibernate.INTEGER).
				addScalar("aguatotalligacoesresidencialca", Hibernate.INTEGER).
				addScalar("esgotototalligacoesresidencial", Hibernate.INTEGER).
				addScalar("aguaTotalLigacoesDesligadas", Hibernate.INTEGER).
				addScalar("aguaTotalEconomiasCadastradas", Hibernate.INTEGER).
				addScalar("esgotototaleconomiascadastrad2", Hibernate.INTEGER).
				addScalar("aguaTotalEconomiasAtivas", Hibernate.INTEGER).
				addScalar("esgotototaleconomiascadastrada", Hibernate.INTEGER).
				addScalar("aguatotaleconomiasativasmedida", Hibernate.INTEGER).
				addScalar("esgotototaleconomiasativasconv", Hibernate.INTEGER).
				addScalar("aguatotaleconomiasresidencialc", Hibernate.INTEGER).
				addScalar("esgotototaleconomiasativascond", Hibernate.INTEGER).
				addScalar("aguatotaleconomiasresidencial2", Hibernate.INTEGER).
				addScalar("esgotototaleconomiasresidenci2", Hibernate.INTEGER).
				addScalar("aguatotaleconomiasresidenciala", Hibernate.INTEGER).
				addScalar("esgotototaleconomiasresidencia", Hibernate.INTEGER).
				addScalar("aguatotaleconomiascomercialati", Hibernate.INTEGER).
				addScalar("esgotototaleconomiascomerciala", Hibernate.INTEGER).
				addScalar("aguatotaleconomiasindustrialat", Hibernate.INTEGER).
				addScalar("esgotototaleconomiasindustrial", Hibernate.INTEGER).
				addScalar("aguatotaleconomiaspublicoativa", Hibernate.INTEGER).
				addScalar("esgotototaleconomiaspublicoati", Hibernate.INTEGER).
				addScalar("aguaTotalEconomiasRuralAtivas", Hibernate.INTEGER).
				addScalar("aguaTotalLigacoesSuprimidas", Hibernate.INTEGER).
				addScalar("esgotototaleconomiasruralativa", Hibernate.INTEGER).
                addScalar("aguaTotalLigacoesNovas", Hibernate.INTEGER).
                addScalar("esgotoTotalLigacoesNovas", Hibernate.INTEGER).
				setInteger("anoMes",anoMes).
				uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}
	
	/**
	 * Pesquisa os valores necessarios na tabela un_resumo_faturamento
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Rafael Pinto
	 * @date 20/11/2006
	 *
	 * @param FiltrarRelatorioOrcamentoSINPHelper
	 * @return Object[29]
	 * 
	 *  Object[0] - aguaTotalVolumeFaturadoMedido
	 *  Object[1] - esgotoTotalVolumeFaturadoResidencial
	 *  Object[2] - esgotoTotalVolumeFaturadoComercial
	 *  Object[3] - aguaTotalVolumeFaturadoEstimado
	 *  Object[4] - esgotoTotalVolumeFaturadoIndustrial
	 *  Object[5] - esgotoTotalVolumeFaturadoPublico
	 *  Object[6] - aguaTotalVolumeFaturadoResidencial
	 *  Object[7] - esgotoTotalVolumeFaturadoGeral
	 *  Object[8] - aguaTotalVolumeFaturadoComercial
	 *  Object[9] - aguaTotalVolumeFaturadoIndustrial
	 *  Object[10] - aguaTotalVolumeFaturadoPublico
	 *  Object[11] - aguaTotalVolumeFaturadoRural
	 *  Object[12] - aguaTotalVolumeFaturadoGeral
	 *  Object[13] - aguaTotalFaturadoResidencial
	 *  Object[14] - esgotoTotalFaturadoResidencial
	 *  Object[15] - aguaTotalFaturadoComercial
	 *  Object[16] - esgotoTotalFaturadoComercial
	 *  Object[17] - aguaTotalFaturadoIndustrial
	 *  Object[18] - esgotoTotalFaturadoIndustrial
	 *  Object[19] - aguaTotalFaturadoPublico
	 *  Object[20] - esgotoTotalFaturadoPublico
	 *  Object[21] - aguaTotalFaturadoDireto
	 *  Object[22] - esgotoTotalFaturadoDireto
	 *  Object[23] - aguaTotalFaturadoIndireto
	 *  Object[24] - esgotoTotalFaturadoIndireto
	 *  Object[25] - devolucao
	 *  Object[26] - campoSelecionado pode ser (loca_id,uneg_id,greg_id)
	 *   
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarRelatorioOrcamentoSINPResumoFaturamento(
		FiltrarRelatorioOrcamentoSINPHelper filtrarRelatorioOrcamentoSINPHelper) 
		throws ErroRepositorioException {
		Object[] retorno = null;
		int anoMes = filtrarRelatorioOrcamentoSINPHelper.getAnoMesReferencia();
		
		Integer localidade = filtrarRelatorioOrcamentoSINPHelper.getLocalidade();
		Integer unidade = filtrarRelatorioOrcamentoSINPHelper.getUnidadeNegocio();
		Integer gerencia = filtrarRelatorioOrcamentoSINPHelper.getGerenciaRegional();
		Integer municipio = filtrarRelatorioOrcamentoSINPHelper.getMunicipio();
		boolean possuiCampo = false;
		if(localidade != null || unidade != null || gerencia != null || municipio != null){
			possuiCampo = true;
		}
		Integer opcaoTotalizacao = filtrarRelatorioOrcamentoSINPHelper.getOpcaoTotalizacao();
		String joinMunicipio = "  left join cadastro.g_municipio muni on ( muni.muni_id = loc.muni_idprincipal ) ";
		if(opcaoTotalizacao == 26 || opcaoTotalizacao == 19){
			joinMunicipio = "  inner join cadastro.g_municipio muni on ( muni.muni_id = loc.muni_idprincipal ) ";
		}
		
		Session session = HibernateUtil.getSessionGerencial();
		String groupBy = filtrarRelatorioOrcamentoSINPHelper.getGroupBy();
		String campoSelecionado = null;

		try{
			
			String sql = "SELECT " 
				+ "SUM(CASE WHEN rfat.refa_ichidrometro = 1 THEN rfat.refa_vofaturadoagua ELSE 0 END) AS aguaTotalVolumeFaturadoMedido,"
				+ "SUM(CASE WHEN rfat.catg_id = 1 THEN rfat.refa_vofaturadoesgoto ELSE 0 END) AS esgotototalvolumefaturadoresid,"
				+ "SUM(CASE WHEN rfat.catg_id = 2 THEN rfat.refa_vofaturadoesgoto ELSE 0 END) AS esgotototalvolumefaturadocomer,"
				+ "SUM(CASE WHEN rfat.refa_ichidrometro = 2 THEN rfat.refa_vofaturadoagua ELSE 0 END) AS aguatotalvolumefaturadoestimad,"
				+ "SUM(CASE WHEN rfat.catg_id = 3 THEN rfat.refa_vofaturadoesgoto ELSE 0 END) AS esgotototalvolumefaturadoindus,"
				+ "SUM(CASE WHEN rfat.catg_id = 4 THEN rfat.refa_vofaturadoesgoto ELSE 0 END) AS esgotototalvolumefaturadopubli,"
				+ "SUM(CASE WHEN rfat.catg_id = 1 AND scat_id not in (13,62,63,64,70,71,77,78) THEN rfat.refa_vofaturadoagua ELSE 0 END) AS aguatotalvolumefaturadoresiden,"
				+ "SUM(COALESCE(rfat.refa_vofaturadoesgoto,0)) AS esgotoTotalVolumeFaturadoGeral,"
				+ "SUM(CASE WHEN rfat.catg_id = 2 THEN rfat.refa_vofaturadoagua ELSE 0 END) AS aguatotalvolumefaturadocomerci,"
				+ "SUM(CASE WHEN rfat.catg_id = 3 THEN rfat.refa_vofaturadoagua ELSE 0 END) AS aguatotalvolumefaturadoindustr,"
				+ "SUM(CASE WHEN rfat.catg_id = 4 THEN rfat.refa_vofaturadoagua ELSE 0 END) AS aguaTotalVolumeFaturadoPublico,"
				+ "SUM(CASE WHEN rfat.catg_id = 1 AND scat_id in (13,62,63,64,70,71,77,78) THEN rfat.refa_vofaturadoagua ELSE 0 END) AS aguaTotalVolumeFaturadoRural,"
				+ "SUM(COALESCE(rfat.refa_vofaturadoagua,0)) AS aguaTotalVolumeFaturadoGeral,"
				+ "SUM(CASE WHEN rfat.catg_id = 1 THEN rfat.refa_vlfaturadoagua ELSE 0 END) AS aguaTotalFaturadoResidencial,"
				+ "SUM(CASE WHEN rfat.catg_id = 1 THEN rfat.refa_vlfaturadoesgoto ELSE 0 END) AS esgotoTotalFaturadoResidencial,"
				+ "SUM(CASE WHEN rfat.catg_id = 2 THEN rfat.refa_vlfaturadoagua ELSE 0 END) AS aguaTotalFaturadoComercial,"
				+ "SUM(CASE WHEN rfat.catg_id = 2 THEN rfat.refa_vlfaturadoesgoto ELSE 0 END) AS esgotoTotalFaturadoComercial,"
				+ "SUM(CASE WHEN rfat.catg_id = 3 THEN rfat.refa_vlfaturadoagua ELSE 0 END) AS aguaTotalFaturadoIndustrial,"
				+ "SUM(CASE WHEN rfat.catg_id = 3 THEN rfat.refa_vlfaturadoesgoto ELSE 0 END) AS esgotoTotalFaturadoIndustrial,"
				+ "SUM(CASE WHEN rfat.catg_id = 4 THEN rfat.refa_vlfaturadoagua ELSE 0 END) AS aguaTotalFaturadoPublico,"
				+ "SUM(CASE WHEN rfat.catg_id = 4 THEN rfat.refa_vlfaturadoesgoto ELSE 0 END) AS esgotoTotalFaturadoPublico,"
				+ "SUM(COALESCE(rfat.refa_vlfaturadoagua,0)) AS aguaTotalFaturadoDireto,"
				+ "SUM(COALESCE(rfat.refa_vlfaturadoesgoto,0)) AS esgotoTotalFaturadoDireto,"
				+ "SUM(CASE WHEN rfat.fntp_id = 1 AND rfat.lict_id in (1,2,3,4,5,6,10,12) THEN rfat.refa_vldocsfaturadosoutros ELSE 0 END) AS aguaTotalFaturadoIndireto , "
				+ "SUM(CASE WHEN rfat.fntp_id = 1 AND rfat.lict_id in (7,8,9,11) THEN rfat.refa_vldocsfaturadosoutros ELSE 0 END) AS esgotoTotalFaturadoIndireto, "
				+ "SUM(COALESCE(rfat.refa_vldocsfaturadoscredito,0)) AS devolucao, "
				+ "SUM(CASE WHEN rfat.refa_ichidrometro = 1 THEN rfat.refa_qtcontasemitidas ELSE 0 END) as totalligacoesfaturadasmedidasa, "
				+ "SUM(CASE WHEN rfat.refa_ichidrometro = 1 and rfat.refa_vlfaturadoesgoto>0 THEN rfat.refa_qtcontasemitidas ELSE 0 END) as totalligacoesfaturadasmedidase, "
				+ "SUM(CASE WHEN rfat.refa_ichidrometro = 2 THEN rfat.refa_qtcontasemitidas ELSE 0 END) as totalligacoesfaturadasnaomedid, "
				+ "SUM(CASE WHEN rfat.refa_ichidrometro = 2 and rfat.refa_vlfaturadoesgoto>0 THEN rfat.refa_qtcontasemitidas ELSE 0 END) as totalligacoesfaturadasnaomedi2, "
				+ "SUM(CASE WHEN rfat.refa_ichidrometro = 1 THEN rfat.refa_qteconomiasfaturadas ELSE 0 END) as totaleconomiasfaturadasmedidas, "
				+ "SUM(CASE WHEN rfat.refa_ichidrometro = 1 and rfat.refa_vlfaturadoesgoto>0 THEN rfat.refa_qteconomiasfaturadas ELSE 0 END) as totaleconomiasfaturadasmedida2, "
				+ "SUM(CASE WHEN rfat.refa_ichidrometro = 2 THEN rfat.refa_qteconomiasfaturadas ELSE 0 END) as totaleconomiasfaturadasnaomedi, "
				+ "SUM(CASE WHEN rfat.refa_ichidrometro = 2 and rfat.refa_vlfaturadoesgoto>0 THEN rfat.refa_qteconomiasfaturadas ELSE 0 END) as totaleconomiasfaturadasnaomed2, "
				+ "SUM(CASE WHEN rfat.refa_ichidrometro = 1 THEN rfat.refa_vlfaturadoagua ELSE 0 END) as TotalFaturamentoMedidoAgua, "
				+ "SUM(CASE WHEN rfat.refa_ichidrometro = 1 THEN rfat.refa_vlfaturadoesgoto ELSE 0 END) as TotalFaturamentoMedidoEsgoto, "
				+ "SUM(CASE WHEN rfat.refa_ichidrometro = 2 THEN rfat.refa_vlfaturadoagua ELSE 0 END) as TotalFaturamentoNaoMedidoAgua, " 	
				+ "SUM(CASE WHEN rfat.refa_ichidrometro = 2 THEN rfat.refa_vlfaturadoesgoto ELSE 0 END) as totalfaturamentonaomedidoesgot "
				+ (possuiCampo ? groupBy + " AS idCampoSelecionado" : "")
				+ " FROM faturamento.un_resumo_faturamento rfat INNER JOIN cadastro.g_localidade loc on (rfat.loca_id = loc.loca_id) "
				+ joinMunicipio
				+ "WHERE rfat.refa_amreferencia = :anoMes";
			
			// Localidade
			if (localidade != null) {
				sql += " AND loc.loca_id = " + localidade;
				campoSelecionado = "loc.loca_id";
			}

			// Unidade de Negocio
			if (unidade != null) {
				sql += " AND rfat.uneg_id = " + unidade;
				campoSelecionado = "rfat.uneg_id";
			}

			// Gerencia Regional
			if (gerencia != null) {
				sql += " AND rfat.greg_id = " + gerencia;
				campoSelecionado = "rfat.greg_id";
			}

			//Município
			if(municipio != null){
				sql += " AND loc.muni_idprincipal = " + municipio;
				campoSelecionado = "loc.muni_idprincipal";
			}
			
			if(!groupBy.equals("")){
				sql += " GROUP BY "+groupBy.substring(1)
					+ " ORDER BY "+groupBy.substring(1);
			}
			
			if(campoSelecionado != null){

				//Faz a pesquisa
				retorno = (Object[]) session.createSQLQuery(sql).
					addScalar("aguaTotalVolumeFaturadoMedido", Hibernate.INTEGER).
					addScalar("esgotototalvolumefaturadoresid", Hibernate.INTEGER).
					addScalar("esgotototalvolumefaturadocomer", Hibernate.INTEGER).
					addScalar("aguatotalvolumefaturadoestimad", Hibernate.INTEGER).
					addScalar("esgotototalvolumefaturadoindus", Hibernate.INTEGER).
					addScalar("esgotototalvolumefaturadopubli", Hibernate.INTEGER).
					addScalar("aguatotalvolumefaturadoresiden", Hibernate.INTEGER).
					addScalar("esgotoTotalVolumeFaturadoGeral", Hibernate.INTEGER).
					addScalar("aguatotalvolumefaturadocomerci", Hibernate.INTEGER).
					addScalar("aguatotalvolumefaturadoindustr", Hibernate.INTEGER).
					addScalar("aguaTotalVolumeFaturadoPublico", Hibernate.INTEGER).
					addScalar("aguaTotalVolumeFaturadoRural", Hibernate.INTEGER).
					addScalar("aguaTotalVolumeFaturadoGeral", Hibernate.INTEGER).
					addScalar("aguaTotalFaturadoResidencial", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoResidencial", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoComercial", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoComercial", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoIndustrial", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoIndustrial", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoPublico", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoPublico", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoDireto", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoDireto", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoIndireto", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoIndireto", Hibernate.BIG_DECIMAL).
					addScalar("devolucao", Hibernate.BIG_DECIMAL).
					addScalar("totalligacoesfaturadasmedidasa", Hibernate.INTEGER).
					addScalar("totalligacoesfaturadasmedidase", Hibernate.INTEGER).
					addScalar("totalligacoesfaturadasnaomedid", Hibernate.INTEGER).
					addScalar("totalligacoesfaturadasnaomedi2", Hibernate.INTEGER).
					addScalar("totaleconomiasfaturadasmedidas", Hibernate.INTEGER).
					addScalar("totaleconomiasfaturadasmedida2", Hibernate.INTEGER).
					addScalar("totaleconomiasfaturadasnaomedi", Hibernate.INTEGER).				
					addScalar("totaleconomiasfaturadasnaomed2", Hibernate.INTEGER).
					addScalar("TotalFaturamentoMedidoAgua", Hibernate.BIG_DECIMAL).
					addScalar("TotalFaturamentoMedidoEsgoto", Hibernate.BIG_DECIMAL).
					addScalar("TotalFaturamentoNaoMedidoAgua", Hibernate.BIG_DECIMAL).					
					addScalar("totalfaturamentonaomedidoesgot", Hibernate.BIG_DECIMAL).
					addScalar("idCampoSelecionado", Hibernate.INTEGER).
					setInteger("anoMes",anoMes).
					setMaxResults(1).
					uniqueResult();
				
			}else{
				
				//Faz a pesquisa
				retorno = (Object[]) session.createSQLQuery(sql).
					addScalar("aguaTotalVolumeFaturadoMedido", Hibernate.INTEGER).
					addScalar("esgotototalvolumefaturadoresid", Hibernate.INTEGER).
					addScalar("esgotototalvolumefaturadocomer", Hibernate.INTEGER).
					addScalar("aguatotalvolumefaturadoestimad", Hibernate.INTEGER).
					addScalar("esgotototalvolumefaturadoindus", Hibernate.INTEGER).
					addScalar("esgotototalvolumefaturadopubli", Hibernate.INTEGER).
					addScalar("aguatotalvolumefaturadoresiden", Hibernate.INTEGER).
					addScalar("esgotoTotalVolumeFaturadoGeral", Hibernate.INTEGER).
					addScalar("aguatotalvolumefaturadocomerci", Hibernate.INTEGER).
					addScalar("aguatotalvolumefaturadoindustr", Hibernate.INTEGER).
					addScalar("aguaTotalVolumeFaturadoPublico", Hibernate.INTEGER).
					addScalar("aguaTotalVolumeFaturadoRural", Hibernate.INTEGER).
					addScalar("aguaTotalVolumeFaturadoGeral", Hibernate.INTEGER).
					addScalar("aguaTotalFaturadoResidencial", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoResidencial", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoComercial", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoComercial", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoIndustrial", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoIndustrial", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoPublico", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoPublico", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoDireto", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoDireto", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoIndireto", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoIndireto", Hibernate.BIG_DECIMAL).
					addScalar("devolucao", Hibernate.BIG_DECIMAL).
					addScalar("totalligacoesfaturadasmedidasa", Hibernate.INTEGER).
					addScalar("totalligacoesfaturadasmedidase", Hibernate.INTEGER).
					addScalar("totalligacoesfaturadasnaomedid", Hibernate.INTEGER).
					addScalar("totalligacoesfaturadasnaomedi2", Hibernate.INTEGER).
					addScalar("totaleconomiasfaturadasmedidas", Hibernate.INTEGER).
					addScalar("totaleconomiasfaturadasmedida2", Hibernate.INTEGER).
					addScalar("totaleconomiasfaturadasnaomedi", Hibernate.INTEGER).					
					addScalar("totaleconomiasfaturadasnaomed2", Hibernate.INTEGER).
					addScalar("TotalFaturamentoMedidoAgua", Hibernate.BIG_DECIMAL).
					addScalar("TotalFaturamentoMedidoEsgoto", Hibernate.BIG_DECIMAL).
					addScalar("TotalFaturamentoNaoMedidoAgua", Hibernate.BIG_DECIMAL).
					addScalar("totalfaturamentonaomedidoesgot", Hibernate.BIG_DECIMAL).
					setInteger("anoMes",anoMes).
					setMaxResults(1).
					uniqueResult();
					
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}
	
	/**
	 * Pesquisa o total das ligaçoes tabela un_resumo_ligacao_economia
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Rafael Pinto
	 * @date 20/11/2006
	 *
	 * @param FiltrarRelatorioOrcamentoSINPHelper
	 * @return Object[2]
	 * 
	 *  Object[0] - quantidadeLigacoesAgua
	 *  Object[1] - quantidadeLigacoesEsgoto
	 *  
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarRelatorioOrcamentoSINPTotalLigacoesResumoLigacaoEconomia(
		FiltrarRelatorioOrcamentoSINPHelper filtrarRelatorioOrcamentoSINPHelper) 
		throws ErroRepositorioException {
		
		Object[] retorno = null;
		
		int anoMesAnterior = filtrarRelatorioOrcamentoSINPHelper.getAnoMesReferenciaAnterior();
		
		Integer localidade = filtrarRelatorioOrcamentoSINPHelper.getLocalidade();
		Integer unidade = filtrarRelatorioOrcamentoSINPHelper.getUnidadeNegocio();
		Integer gerencia = filtrarRelatorioOrcamentoSINPHelper.getGerenciaRegional();
		Collection<Integer> idsLocalidadesMunicipio = filtrarRelatorioOrcamentoSINPHelper.getChavesLocalidadesMunicipio();
		

		Session session = HibernateUtil.getSessionGerencial();

		try{
			
			String sql = "SELECT " 
				+ "SUM(CASE WHEN las.last_iccadastradaagua = 1 THEN rele_qtligacoes ELSE 0 END) AS quantidadeLigacoesAgua, "
				+ "SUM(CASE WHEN les.lest_iccadastradaesgoto = 1 THEN rele_qtligacoes ELSE 0 END) AS quantidadeLigacoesEsgoto "
				+ "FROM cadastro.un_res_lig_econ rle "
                + " left join atendimentopublico.g_ligacao_agua_situacao las on ( rle.last_id = las.last_id ) " 
                + " left join atendimentopublico.g_lig_esgoto_sit les on ( rle.lest_id = les.lest_id ) "                
				+ "WHERE rele_amreferencia = :anoMesAnterior";
			
			// Localidade
			if (localidade != null) {
				sql += " AND loca_id = " + localidade;
			}
			
			//	Localidades do Município
			if (idsLocalidadesMunicipio != null) {
				sql += " AND loca_id in " + idsLocalidadesMunicipio;
				sql = sql.replace("[","(");
				sql = sql.replace("]",")");
			}

			// Unidade de Negocio
			if (unidade != null) {
				sql += " AND uneg_id = " + unidade;
			}

			// Gerencia Regional
			if (gerencia != null) {
				sql += " AND greg_id = " + gerencia;
			}

			//Faz a pesquisa
			retorno = (Object[])session.createSQLQuery(sql).
				addScalar("quantidadeLigacoesAgua", Hibernate.INTEGER).
				addScalar("quantidadeLigacoesEsgoto", Hibernate.INTEGER).
				setInteger("anoMesAnterior",anoMesAnterior).
				uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}
	
	/**
	 * Pesquisa o total dos consumos tabela un_resumo_consumo_agua
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Rafael Pinto
	 * @date 20/11/2006
	 *
	 * @param FiltrarRelatorioOrcamentoSINPHelper
	 * @return Object[2]
	 * 
	 *  Object[0] - volumeFaturadoMicroMedido
	 *  Object[1] - volumeFaturadoEconomiasResidenciasAtivasMicroMedido
	 *  
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarRelatorioOrcamentoSINPTotalComumoResumoConsumoAgua(
		FiltrarRelatorioOrcamentoSINPHelper filtrarRelatorioOrcamentoSINPHelper) 
		throws ErroRepositorioException {
		Object[] retorno = null;
		int anoMes = filtrarRelatorioOrcamentoSINPHelper.getAnoMesReferencia();
		Integer localidade = filtrarRelatorioOrcamentoSINPHelper.getLocalidade();
		Integer unidade = filtrarRelatorioOrcamentoSINPHelper.getUnidadeNegocio();
		Integer gerencia = filtrarRelatorioOrcamentoSINPHelper.getGerenciaRegional();
		Integer municipio = filtrarRelatorioOrcamentoSINPHelper.getMunicipio();
		Integer opcaoTotalizacao = filtrarRelatorioOrcamentoSINPHelper.getOpcaoTotalizacao();
		String joinMunicipio = "  left join cadastro.g_municipio muni on ( muni.muni_id = loc.muni_idprincipal ) ";
		if(opcaoTotalizacao == 26 || opcaoTotalizacao == 19){
			joinMunicipio = "  inner join cadastro.g_municipio muni on ( muni.muni_id = loc.muni_idprincipal ) ";
		}
		Session session = HibernateUtil.getSessionGerencial();

		try{
			
			String sql = "SELECT " 
				+ "SUM(COALESCE(reca_consumoagua,0)) AS volumeFaturadoMicroMedido, "
				+ "SUM(CASE WHEN catg_id = 1 THEN reca_consumoagua ELSE 0 END) AS volumefaturadoeconomiasresiden "
				+ "FROM micromedicao.un_resumo_consumo_agua cagua INNER JOIN cadastro.g_localidade loc on (cagua.loca_id = loc.loca_id) "
				+ joinMunicipio
				+ "WHERE reca_amreferencia = :anoMes "
				+ "AND cstp_id NOT IN(5,7)";
			
			// Localidade
			if (localidade != null) {
				sql += " AND loc.loca_id = " + localidade;
			}

			// Unidade de Negocio
			if (unidade != null) {
				sql += " AND cagua.uneg_id = " + unidade;
			}

			// Gerencia Regional
			if (gerencia != null) {
				sql += " AND cagua.greg_id = " + gerencia;
			}

			if(municipio != null){
				sql += " AND loc.muni_idprincipal = " + municipio;
			}
			
			//Faz a pesquisa
			retorno = (Object[])session.createSQLQuery(sql).
				addScalar("volumeFaturadoMicroMedido", Hibernate.INTEGER).
				addScalar("volumefaturadoeconomiasresiden", Hibernate.INTEGER).
				setInteger("anoMes",anoMes).
				uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}
	
	/**
	 * Pesquisa o total dos consumos tabela un_resumo_arrecadacao
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Rafael Pinto, Hugo Leonardo	
	 * @date 20/11/2006, 06/10/2010
	 *
	 * @param FiltrarRelatorioOrcamentoSINPHelper
	 * @return BigDecimal
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarRelatorioOrcamentoSINPTotalArrecadacaoResumoArrecadacao(
		FiltrarRelatorioOrcamentoSINPHelper filtrarRelatorioOrcamentoSINPHelper,
		boolean ehAnterior) throws ErroRepositorioException {
		BigDecimal retorno = null;
		Integer localidade = filtrarRelatorioOrcamentoSINPHelper.getLocalidade();
		Integer unidade = filtrarRelatorioOrcamentoSINPHelper.getUnidadeNegocio();
		Integer gerencia = filtrarRelatorioOrcamentoSINPHelper.getGerenciaRegional();
		Integer municipio = filtrarRelatorioOrcamentoSINPHelper.getMunicipio();
		Integer opcaoTotalizacao = filtrarRelatorioOrcamentoSINPHelper.getOpcaoTotalizacao();
		String joinMunicipio = "  left join cadastro.g_municipio muni on ( muni.muni_id = loc.muni_idprincipal ) ";
		if(opcaoTotalizacao == 26 || opcaoTotalizacao == 19){
			joinMunicipio = "  inner join cadastro.g_municipio muni on ( muni.muni_id = loc.muni_idprincipal ) ";
		}
		Session session = HibernateUtil.getSessionGerencial();
		
		int anoMes = filtrarRelatorioOrcamentoSINPHelper.getAnoMesReferencia();
		
		String and = "AND rear_amreferenciadocumento >= :anoMes ";
		
		if(ehAnterior){
			and = "AND (rear_amreferenciadocumento < :anoMes OR rear_amreferenciadocumento is null ) ";
		}
		

		try{
			
			String sql = "SELECT " 
				+ "SUM( " 
				+ "COALESCE(rear_vlagua,0) +" 
				+ "COALESCE(rear_vlesgoto,0) +" 
				+ "COALESCE(rear_vlnaoidentificado,0) +" 
				+ "COALESCE(rear_vldocsrecebidosoutros,0) - " 
				+ "(COALESCE(rear_vldocsrecebidoscredito,0) + "
				+ "COALESCE(rear_vlimpostos,0) + " 
				+ "COALESCE(rear_vldevolucoesclassificadas,0) + " 
				+ "COALESCE(rear_vldevolucoesnaoclassif,0)) "
				+ ") AS totalArrecadacao "
				+ "FROM arrecadacao.un_resumo_arrecadacao arr INNER JOIN cadastro.g_localidade loc on (arr.loca_id = loc.loca_id) " 
				+ joinMunicipio
				+ "WHERE rear_amreferencia = :anoMes "
				+ and;
			
			// Localidade
			if (localidade != null) {
				sql += " AND loc.loca_id = " + localidade;
			}

			// Unidade de Negocio
			if (unidade != null) {
				sql += " AND arr.uneg_id = " + unidade;
			}

			// Gerencia Regional
			if (gerencia != null) {
				sql += " AND arr.greg_id = " + gerencia;
			}

			if(municipio != null){
				sql += " AND loc.muni_idprincipal = " + municipio;
			}
			
			//Faz a pesquisa
			retorno = (BigDecimal) session.createSQLQuery(sql).
				addScalar("totalArrecadacao", Hibernate.BIG_DECIMAL).
				setInteger("anoMes",anoMes).
				uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}
	
	
	/**
	 * Pesquisa o total de contas a receber consumos tabela un_resumo_pendencia
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Rafael Pinto
	 * @date 30/11/2006
	 *
	 * @param FiltrarRelatorioOrcamentoSINPHelper
	 * @return BigDecimal
	 * 
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarRelatorioOrcamentoSINPTotalContasResumoPendencia(
			FiltrarRelatorioOrcamentoSINPHelper filtrarRelatorioOrcamentoSINPHelper) 
		throws ErroRepositorioException {
		Object[] retorno = null;
		Integer localidade = filtrarRelatorioOrcamentoSINPHelper.getLocalidade();
		Integer unidade = filtrarRelatorioOrcamentoSINPHelper.getUnidadeNegocio();
		Integer gerencia = filtrarRelatorioOrcamentoSINPHelper.getGerenciaRegional();
		Integer municipio = filtrarRelatorioOrcamentoSINPHelper.getMunicipio();
		Integer opcaoTotalizacao = filtrarRelatorioOrcamentoSINPHelper.getOpcaoTotalizacao();
		String joinMunicipio = "  left join cadastro.g_municipio muni on ( muni.muni_id = loc.muni_idprincipal ) ";
		if(opcaoTotalizacao == 26 || opcaoTotalizacao == 19){
			joinMunicipio = "  inner join cadastro.g_municipio muni on ( muni.muni_id = loc.muni_idprincipal ) ";
		}
		int anoMes = filtrarRelatorioOrcamentoSINPHelper.getAnoMesReferencia();
		
		Session session = HibernateUtil.getSessionGerencial();

		try{
			
			String sql = "SELECT " 
				+ "SUM(  " 
					+ "(COALESCE(rpen_vlpendente_agua,0) + " 
					+ "COALESCE(rpen_vlpendente_esgoto,0) + " 
					+ "COALESCE(rpen_vlpendente_debitos,0) ) - " 
					+ "(COALESCE(rpen_vlpendente_creditos,0) + " 
					+ "COALESCE(rpen_vlpendente_impostos,0) )  " 
				+ ") AS total, "
				+ "SUM(CASE WHEN catg_id <> 4 THEN(  " 
				+ "(COALESCE(rpen_vlpendente_agua,0) + " 
				+ "COALESCE(rpen_vlpendente_esgoto,0) + " 
				+ "COALESCE(rpen_vlpendente_debitos,0) ) - " 
				+ "(COALESCE(rpen_vlpendente_creditos,0) + " 
				+ "COALESCE(rpen_vlpendente_impostos,0) )  " 
			    + ")ELSE 0 END) AS particular, "
			    + "SUM(CASE WHEN catg_id = 4 THEN(  " 
				+ "(COALESCE(rpen_vlpendente_agua,0) + " 
				+ "COALESCE(rpen_vlpendente_esgoto,0) + " 
				+ "COALESCE(rpen_vlpendente_debitos,0) ) - " 
				+ "(COALESCE(rpen_vlpendente_creditos,0) + " 
				+ "COALESCE(rpen_vlpendente_impostos,0) )  " 
			    + ")ELSE 0 END) AS publico "
				+ "FROM cobranca.un_resumo_pendencia pend INNER JOIN cadastro.g_localidade loc on (pend.loca_id = loc.loca_id) "
				+ joinMunicipio
				+ "WHERE rpen_amreferencia = :anoMes "
				+ "AND rpen_icvencido = 1 ";
			
			// Localidade
			if (localidade != null) {
				sql += " AND loc.loca_id = " + localidade;
			}
			
			// Unidade de Negocio
			if (unidade != null) {
				sql += " AND pend.uneg_id = " + unidade;
			}

			// Gerencia Regional
			if (gerencia != null) {
				sql += " AND pend.greg_id = " + gerencia;
			}

			if(municipio != null){
				sql += " AND loc.muni_idprincipal = " + municipio;
			}
			
			//Faz a pesquisa
			retorno = (Object[]) session.createSQLQuery(sql).
				addScalar("total", Hibernate.BIG_DECIMAL).
				addScalar("particular", Hibernate.BIG_DECIMAL).
				addScalar("publico", Hibernate.BIG_DECIMAL).
				setInteger("anoMes",anoMes).
				uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}
	
	/**
	 * Pesquisa todas as localidades da tabela g_localidade
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Rafael Pinto
	 * @date 21/11/2006
	 *
	 * @return Collection<Integer>
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarLocalidades(Integer idUnidade) 
		throws ErroRepositorioException {
		
		Collection<Integer> retorno = null;
		
		Session session = HibernateUtil.getSessionGerencial();

		try{
			
			String consulta = "SELECT  localidade.id "
				+ "FROM GLocalidade as localidade "
				+ "WHERE localidade.gerUnidadeNegocio.id = :idUnidade "
				+ "AND localidade.indicadorUso = :indicadorUso "
				+ "ORDER BY localidade.codigoCentroCusto";

			retorno = session.createQuery(consulta).
				setInteger("idUnidade",idUnidade).
				setInteger("indicadorUso",ConstantesSistema.INDICADOR_USO_ATIVO).
				list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}	
	
	/**
	 * Pesquisa todas as unidades da tabela g_unidade_negocio
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Rafael Pinto
	 * @date 21/11/2006
	 *
	 * @return Collection<Integer>
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarUnidadesNegocios(Integer idGerencia) 
		throws ErroRepositorioException {
		
		Collection<Integer> retorno = null;
		
		Session session = HibernateUtil.getSessionGerencial();

		try{
			
			String consulta = "SELECT unidade.id "
				+ "FROM GUnidadeNegocio unidade "
				+ "WHERE unidade.gerGerenciaRegional.id = :idGerencia "
				+ "ORDER BY unidade.id";

			retorno = session.createQuery(consulta)
			.setInteger("idGerencia",idGerencia)
			.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}
	
	/**
	 * Pesquisa todas as unidades da tabela g_gerencia_regional
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Rafael Pinto
	 * @date 21/11/2006
	 *
	 * @return Collection<Integer>
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarGerenciasRegionais() 
		throws ErroRepositorioException {
		
		Collection<Integer> retorno = null;
		
		Session session = HibernateUtil.getSessionGerencial();

		try{
			
			String consulta = "SELECT gerencia.id "
				+ "FROM GGerenciaRegional gerencia "
				+ "ORDER BY gerencia.id";

			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}	
	
	/**
	 * Pesquisa todas os municípios associados à localidade da tabela g_municipio
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Diogo Peixoto
	 * @date 02/05/2011
	 *
	 * @return Collection<Integer>
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarMunicipiosAssociadosLocalidade()throws ErroRepositorioException {
		
		Collection<Integer> retorno = null;
		
		Session session = HibernateUtil.getSessionGerencial();

		try{
			String consulta = "SELECT DISTINCT(muni.id) "
				+ "FROM GLocalidade as localidade " 
				+ "INNER JOIN localidade.gerMunicipio as muni "
				+ "ORDER BY muni.id";

			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}	
	
	/**
	 * 
	 * [UC0733] Gerar Quadro de metas Acumulado
	 * 
	 * @author Bruno Barros
	 * @param filtrarRelatorioQuadroMetasAcumuladoHelper
	 * @return
	 */
	public List pesquisarRelatorioQuadroMetasAcumulado(
			FiltrarRelatorioQuadroMetasAcumuladoHelper filtrarRelatorioQuadroMetasAcumuladoHelper) 
		throws ErroRepositorioException{

		List retorno = null;
		
		Session session = HibernateUtil.getSessionGerencial();
		
		Integer qtdGrupos = 0;
		
		String select = "";
		String where  = "";
		String groupby = "group by \n";
		String orderby = "order by \n";
		
		// opções de totalização
		if ( filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao() == TOTALIZACAO_GERENCIA_REGIONAL  ||
			 filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao() == TOTALIZACAO_UNIDADE_NEGOCIO 	||
			 filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao() == TOTALIZACAO_LOCALIDADE 		||
			 filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao() == TOTALIZACAO_ESTADO_GERENCIA_REGIONAL  ||
			 filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao() == TOTALIZACAO_ESTADO_UNIDADE_NEGOCIO  ||
			 filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao() == TOTALIZACAO_ESTADO_LOCALIDADE ){
			select  += "   ,r.gerGerenciaRegional.id \n";
			groupby += "   r.gerGerenciaRegional.id \n";
			orderby += "   r.gerGerenciaRegional.id \n"; 
			
			qtdGrupos++;			
		}

		if ( filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao() == TOTALIZACAO_UNIDADE_NEGOCIO 	||
			 filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao() == TOTALIZACAO_LOCALIDADE 		||
			 filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao() == TOTALIZACAO_ESTADO_UNIDADE_NEGOCIO  ||
			 filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao() == TOTALIZACAO_ESTADO_LOCALIDADE ){		
			select  += "   ,r.gerUnidadeNegocio.id \n";			
			groupby += "   " + ( qtdGrupos > 0 ? "," : "" ) + "r.gerUnidadeNegocio.id \n";
			orderby += "   " + ( qtdGrupos > 0 ? "," : "" ) + "r.gerUnidadeNegocio.id \n";			
			
			qtdGrupos++;
		}
		
		if ( filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao() == TOTALIZACAO_LOCALIDADE 		||
			 filtrarRelatorioQuadroMetasAcumuladoHelper.getOpcaoTotalizacao() == TOTALIZACAO_ESTADO_LOCALIDADE ){		
				select  += "   ,r.gerLocalidade.id, r.gerLocalidade.codigoCentroCusto \n";
				groupby += "   " + ( qtdGrupos > 0 ? "," : "" ) + "r.gerLocalidade.id, r.gerLocalidade.codigoCentroCusto \n";
				orderby += "   " + ( qtdGrupos > 0 ? "," : "" ) + "r.gerLocalidade.id \n, r.gerLocalidade.codigoCentroCusto";
				qtdGrupos++;		
		}
		
		if ( qtdGrupos == 0 ){
			select  = "";
			groupby += " r.anoMesReferencia";
			orderby = "";			
		}
		
		// Montamos a clausula where
		if ( filtrarRelatorioQuadroMetasAcumuladoHelper.getGerenciaRegional() != null && 
			 !filtrarRelatorioQuadroMetasAcumuladoHelper.getGerenciaRegional().equals( 0 )  ){
			where += "   and r.gerGerenciaRegional.id = " + filtrarRelatorioQuadroMetasAcumuladoHelper.getGerenciaRegional(); 
		}
		
		if ( filtrarRelatorioQuadroMetasAcumuladoHelper.getUnidadeNegocio() != null && 
			!filtrarRelatorioQuadroMetasAcumuladoHelper.getUnidadeNegocio().equals( 0 )  ){
			where += "   and r.gerUnidadeNegocio.id = " + filtrarRelatorioQuadroMetasAcumuladoHelper.getUnidadeNegocio(); 
		}
		
		if ( filtrarRelatorioQuadroMetasAcumuladoHelper.getLocalidade() != null && 
    		!filtrarRelatorioQuadroMetasAcumuladoHelper.getLocalidade().equals( 0 )  ){
			where += "   and r.gerLocalidade.id = " + filtrarRelatorioQuadroMetasAcumuladoHelper.getLocalidade(); 
		}	

		try{
			
			String consulta = 
				" select \n" +
				// INDICE 0 - Ligacoes Cadastradas, Grupo Subcategoria 101
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 101 ) then \n" +
				"       r.quantidadeLigacoesCadastradas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 1 - Ligacoes Cadastradas, Grupo Subcategoria 102
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 102 ) then \n" +
				"       r.quantidadeLigacoesCadastradas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 2 - Ligacoes Cadastradas, Grupo Subcategoria 103
				"   sum( \n" + 
				"    case when ( r.codigoGrupoSubcategoria = 103 ) then \n" +
				"    r.quantidadeLigacoesCadastradas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 3 - Ligacoes Cadastradas, Grupo Subcategoria 200
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 200 ) then \n" +
				"       r.quantidadeLigacoesCadastradas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 4 - Ligacoes Cadastradas, Grupo Subcategoria 300				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 300 ) then \n" +
				"       r.quantidadeLigacoesCadastradas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 5 - Ligacoes Cadastradas, Grupo Subcategoria 400				    
				"   sum(0), \n" + 
//				"     case when ( r.codigoGrupoSubcategoria = 400 ) then \n" +
//				"       r.quantidadeLigacoesCadastradas \n" +  
//				"     else 0 end ), \n" +
//				 INDICE 6 - Ligacoes Cadastradas, Grupo Subcategoria 116				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 116 ) then \n" +
				"       r.quantidadeLigacoesCadastradas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 7 - Ligacoes Cadastradas, Grupo Subcategoria 115				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 115 ) then \n" +
				"       r.quantidadeLigacoesCadastradas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 8 - Ligacoes Cadastradas
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria <> 400 ) then \n" +
				"       r.quantidadeLigacoesCadastradas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 9 - Ligacoes Cortadas, Grupo Subcategoria 101
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 101 ) then \n" +
				"       r.quantidadeLigacoesCortadas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 10 - Ligacoes Cortadas, Grupo Subcategoria 102
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 102 ) then \n" +
				"       r.quantidadeLigacoesCortadas   \n" +
				"     else 0 end ), \n" +
//				 INDICE 11 - Ligacoes Cortadas, Grupo Subcategoria 103
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 103 ) then \n" +
				"       r.quantidadeLigacoesCortadas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 12 - Ligacoes Cortadas, Grupo Subcategoria 200
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 200 ) then \n" +
				"       r.quantidadeLigacoesCortadas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 13 - Ligacoes Cortadas, Grupo Subcategoria 300				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 300 ) then \n" +
				"       r.quantidadeLigacoesCortadas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 14 - Ligacoes Cortadas, Grupo Subcategoria 400				    
				"   sum(0), \n" + 
//				"     case when ( r.codigoGrupoSubcategoria = 400 ) then \n" +
//				"       r.quantidadeLigacoesCortadas \n" +  
//				"     else 0 end ), \n" +
//				 INDICE 15 - Ligacoes Cortadas, Grupo Subcategoria 116				    
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 116 ) then \n" +
				"       r.quantidadeLigacoesCortadas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 16 - Ligacoes Cortadas, Grupo Subcategoria 115				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 115 ) then \n" +
				"       r.quantidadeLigacoesCortadas   \n" +
				"     else 0 end ), \n" +
//				 INDICE 17 - Ligacoes Cortadas				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria <> 400 ) then \n" +
				"       r.quantidadeLigacoesCortadas   \n" +
				"     else 0 end ), \n" +
//				 INDICE 18 - Ligacoes Suprimidas, Grupo Subcategoria 101				  
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 101 ) then \n" +
				"       r.quantidadeLigacoesSuprimidas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 19 - Ligacoes Suprimidas, Grupo Subcategoria 102
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 102 ) then \n" +
				"       r.quantidadeLigacoesSuprimidas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 20 - Ligacoes Suprimidas, Grupo Subcategoria 103
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 103 ) then \n" +
				"       r.quantidadeLigacoesSuprimidas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 21 - Ligacoes Suprimidas, Grupo Subcategoria 200
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 200 ) then \n" +
				"       r.quantidadeLigacoesSuprimidas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 22 - Ligacoes Suprimidas, Grupo Subcategoria 300
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 300 ) then \n" +
				"       r.quantidadeLigacoesSuprimidas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 23 - Ligacoes Suprimidas, Grupo Subcategoria 400				    
				"   sum(0),  \n" +
//				"     case when ( r.codigoGrupoSubcategoria = 400 ) then \n" +
//				"       r.quantidadeLigacoesSuprimidas \n" +  
//				"     else 0 end ), \n" +
//				 INDICE 24 - Ligacoes Suprimidas, Grupo Subcategoria 116				    
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 116 ) then \n" +
				"       r.quantidadeLigacoesSuprimidas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 25 - Ligacoes Suprimidas, Grupo Subcategoria 115				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 115 ) then \n" +
				"       r.quantidadeLigacoesSuprimidas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 26 - Ligacoes Suprimidas				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria <> 400 ) then \n" +
				"       r.quantidadeLigacoesSuprimidas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 27 - Ligacoes Ativas,  Grupo Subcategoria 101
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 101 ) then \n" +
				"       r.quantidadeLigacoesAtivas   \n" +
				"     else 0 end ), \n" +
//				 INDICE 28 - Ligacoes Ativas,  Grupo Subcategoria 102
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 102 ) then \n" +
				"       r.quantidadeLigacoesAtivas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 29 - Ligacoes Ativas,  Grupo Subcategoria 103
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 103 ) then \n" +
				"       r.quantidadeLigacoesAtivas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 30 - Ligacoes Ativas,  Grupo Subcategoria 200
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 200 ) then \n" +
				"       r.quantidadeLigacoesAtivas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 31 - Ligacoes Ativas,  Grupo Subcategoria 300				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 300 ) then \n" +
				"       r.quantidadeLigacoesAtivas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 32 - Ligacoes Ativas,  Grupo Subcategoria 400				    
				"   sum(0),  \n" +
//				"     case when ( r.codigoGrupoSubcategoria = 400 ) then \n" +
//				"       r.quantidadeLigacoesAtivas \n" +  
//				"     else 0 end ), \n" +
//				 INDICE 33 - Ligacoes Ativas,  Grupo Subcategoria 116				    
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 116 ) then \n" +
				"       r.quantidadeLigacoesAtivas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 34 - Ligacoes Ativas,  Grupo Subcategoria 115				    
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 115 ) then \n" +
				"       r.quantidadeLigacoesAtivas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 35 - Ligacoes Ativas				    
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria <> 400 ) then \n" +
				"       r.quantidadeLigacoesAtivas \n" +  
				"     else 0 end ), \n" +
//				 INDICE 36 - Ligacoes Ativas debito 3m,  Grupo Subcategoria 101				  
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 101 ) then \n" +
				"       r.quantidadeLigacoesAtivasDebito3m \n" +  
				"     else 0 end ), \n" +
//				 INDICE 37 - Ligacoes Ativas debito 3m,  Grupo Subcategoria 102
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 102 ) then \n" +
				"       r.quantidadeLigacoesAtivasDebito3m \n" +  
				"     else 0 end ), \n" +
//				 INDICE 38 - Ligacoes Ativas debito 3m,  Grupo Subcategoria 103
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 103 ) then \n" +
				"       r.quantidadeLigacoesAtivasDebito3m \n" +  
				"     else 0 end ), \n" +
//				 INDICE 39 - Ligacoes Ativas debito 3m,  Grupo Subcategoria 200				  
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 200 ) then \n" +
				"       r.quantidadeLigacoesAtivasDebito3m \n" +  
				"     else 0 end ), \n" +
//				 INDICE 40 - Ligacoes Ativas debito 3m,  Grupo Subcategoria 300				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 300 ) then \n" +
				"       r.quantidadeLigacoesAtivasDebito3m \n" +  
				"     else 0 end ), \n" +
//				 INDICE 41 - Ligacoes Ativas debito 3m,  Grupo Subcategoria 400				    
				"   sum(0),  \n" +
//				"     case when ( r.codigoGrupoSubcategoria = 400 ) then \n" +
//				"       r.quantidadeLigacoesAtivasDebito3m   \n" +
//				"     else 0 end ), \n" +
//				 INDICE 42 - Ligacoes Ativas debito 3m,  Grupo Subcategoria 116				    
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 116 ) then \n" +
				"       r.quantidadeLigacoesAtivasDebito3m \n" +  
				"     else 0 end ), \n" +
//				 INDICE 43 - Ligacoes Ativas debito 3m,  Grupo Subcategoria 115				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria <> 115 ) then \n" +
				"       r.quantidadeLigacoesAtivasDebito3m \n" +  
				"     else 0 end ), \n" +
//				 INDICE 44 - Ligacoes Ativas debito 3m				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria <> 400 ) then \n" +
				"       r.quantidadeLigacoesAtivasDebito3m \n" +  
				"     else 0 end ), \n" +
//				 INDICE 45 - Ligacoes Consumo Medido, Grupo Subcategoria 101
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 101 ) then \n" +
				"       r.quantidadeLigacoesConsumoMedido \n" +  
				"     else 0 end ), \n" +
//				 INDICE 46 - Ligacoes Consumo Medido, Grupo Subcategoria 102
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 102 ) then \n" +
				"       r.quantidadeLigacoesConsumoMedido \n" +  
				"     else 0 end ), \n" +
//				 INDICE 47 - Ligacoes Consumo Medido, Grupo Subcategoria 103
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 103 ) then \n" +
				"       r.quantidadeLigacoesConsumoMedido \n" +  
				"     else 0 end ), \n" +
//				 INDICE 48 - Ligacoes Consumo Medido, Grupo Subcategoria 200
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 200 ) then \n" +
				"       r.quantidadeLigacoesConsumoMedido \n" +  
				"     else 0 end ), \n" +
//				 INDICE 49 - Ligacoes Consumo Medido, Grupo Subcategoria 300				    
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 300 ) then \n" +
				"       r.quantidadeLigacoesConsumoMedido \n" +  
				"     else 0 end ), \n" +
//				 INDICE 50 - Ligacoes Consumo Medido, Grupo Subcategoria 400				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 400 ) then \n" +
				"       r.quantidadeLigacoesConsumoMedido \n" +  
				"     else 0 end ), \n" +
//				 INDICE 51 - Ligacoes Consumo Medido, Grupo Subcategoria 116				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 116 ) then \n" +
				"       r.quantidadeLigacoesConsumoMedido \n" +  
				"     else 0 end ), \n" +
//				 INDICE 52 - Ligacoes Consumo Medido, Grupo Subcategoria 115				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 115 ) then \n" +
				"       r.quantidadeLigacoesConsumoMedido \n" +  
				"     else 0 end ), \n" +
//				 INDICE 53 - Ligacoes Consumo Medido				    
				"   sum( r.quantidadeLigacoesConsumoMedido ), \n" +				
//				 INDICE 54 - Ligacoes Consumo ate 5m3, Grupo Subcategoria 101				  
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 101 ) then \n" +
				"       r.quantidadeLigacoesConsumoAte5m3 \n" +  
				"     else 0 end ), \n" +
//				 INDICE 55 - Ligacoes Consumo ate 5m3, Grupo Subcategoria 102
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 102 ) then \n" +
				"       r.quantidadeLigacoesConsumoAte5m3 \n" +  
				"     else 0 end ), \n" +
//				 INDICE 56 - Ligacoes Consumo ate 5m3, Grupo Subcategoria 103
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 103 ) then \n" +
				"       r.quantidadeLigacoesConsumoAte5m3 \n" +  
				"     else 0 end ), \n" +
//				 INDICE 57 - Ligacoes Consumo ate 5m3, Grupo Subcategoria 200
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 200 ) then \n" +
				"       r.quantidadeLigacoesConsumoAte5m3 \n" +  
				"     else 0 end ), \n" +
//				 INDICE 58 - Ligacoes Consumo ate 5m3, Grupo Subcategoria 300				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 300 ) then \n" +
				"       r.quantidadeLigacoesConsumoAte5m3 \n" +  
				"     else 0 end ), \n" +
//				 INDICE 59 - Ligacoes Consumo ate 5m3, Grupo Subcategoria 400				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 400 ) then \n" +
				"       r.quantidadeLigacoesConsumoAte5m3 \n" +  
				"     else 0 end ), \n" +
//				 INDICE 60 - Ligacoes Consumo ate 5m3, Grupo Subcategoria 116				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 116 ) then \n" +
				"       r.quantidadeLigacoesConsumoAte5m3 \n" +  
				"     else 0 end ), \n" +
//				 INDICE 61 - Ligacoes Consumo ate 5m3, Grupo Subcategoria 115				    
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 115 ) then \n" +
				"       r.quantidadeLigacoesConsumoAte5m3 \n" +  
				"     else 0 end ), \n" +
//				 INDICE 62 - Ligacoes Consumo ate 5m3				    
				"   sum( r.quantidadeLigacoesConsumoAte5m3 ), \n" +
//				 INDICE 63 - Ligacoes Consumo Nao Medido, Grupo Subcategoria 101				  
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 101 ) then \n" +
				"       r.quantidadeLigacoesConsumoNaoMedido \n" +  
				"     else 0 end ), \n" +
//				 INDICE 64 - Ligacoes Consumo Nao Medido, Grupo Subcategoria 102
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 102 ) then \n" +
				"       r.quantidadeLigacoesConsumoNaoMedido \n" +  
				"     else 0 end ), \n" +
//				 INDICE 65 - Ligacoes Consumo Nao Medido, Grupo Subcategoria 103
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 103 ) then \n" +
				"       r.quantidadeLigacoesConsumoNaoMedido \n" +  
				"     else 0 end ), \n" +
//				 INDICE 66 - Ligacoes Consumo Nao Medido, Grupo Subcategoria 200
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 200 ) then \n" +
				"       r.quantidadeLigacoesConsumoNaoMedido \n" +  
				"     else 0 end ), \n" +
//				 INDICE 67 - Ligacoes Consumo Nao Medido, Grupo Subcategoria 300				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 300 ) then \n" +
				"       r.quantidadeLigacoesConsumoNaoMedido \n" +  
				"     else 0 end ), \n" +
//				 INDICE 68 - Ligacoes Consumo Nao Medido, Grupo Subcategoria 400				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 400 ) then \n" +
				"       r.quantidadeLigacoesConsumoNaoMedido \n" +  
				"     else 0 end ), \n" +
//				 INDICE 69 - Ligacoes Consumo Nao Medido, Grupo Subcategoria 116				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 116 ) then \n" +
				"       r.quantidadeLigacoesConsumoNaoMedido \n" +  
				"     else 0 end ), \n" +
//				 INDICE 70 - Ligacoes Consumo Nao Medido, Grupo Subcategoria 115				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 115 ) then \n" +
				"       r.quantidadeLigacoesConsumoNaoMedido \n" +  
				"     else 0 end ), \n" +
//				 INDICE 71 - Ligacoes Consumo Nao Medido				    
				"   sum( r.quantidadeLigacoesConsumoNaoMedido ), \n" +
//				 INDICE 72 - Ligacoes Consumo Media, Grupo Subcategoria 101
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 101 ) then \n" +
				"       r.quantidadeLigacoesConsumoMedia \n" +  
				"     else 0 end ), \n" +
//				 INDICE 73 - Ligacoes Consumo Media, Grupo Subcategoria 102
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 102 ) then \n" +
				"       r.quantidadeLigacoesConsumoMedia \n" +  
				"     else 0 end ), \n" +
//				 INDICE 74 - Ligacoes Consumo Media, Grupo Subcategoria 103
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 103 ) then \n" +
				"      r.quantidadeLigacoesConsumoMedia \n" +  
				"     else 0 end ), \n" +
//				 INDICE 75 - Ligacoes Consumo Media, Grupo Subcategoria 200
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 200 ) then \n" +
				"       r.quantidadeLigacoesConsumoMedia \n" +  
				"     else 0 end ), \n" +
//				 INDICE 76 - Ligacoes Consumo Media, Grupo Subcategoria 300				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 300 ) then \n" +
				"       r.quantidadeLigacoesConsumoMedia \n" +  
				"     else 0 end ), \n" +
//				 INDICE 77 - Ligacoes Consumo Media, Grupo Subcategoria 400				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 400 ) then \n" +
				"       r.quantidadeLigacoesConsumoMedia \n" +  
				"     else 0 end ), \n" +
//				 INDICE 78 - Ligacoes Consumo Media, Grupo Subcategoria 116				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 116 ) then \n" +
				"       r.quantidadeLigacoesConsumoMedia \n" +  
				"     else 0 end ), \n" +
//				 INDICE 79 - Ligacoes Consumo Media, Grupo Subcategoria 115				    
				"   sum( \n" + 
				"     case when ( r.codigoGrupoSubcategoria = 115 ) then \n" +
				"       r.quantidadeLigacoesConsumoMedia \n" +  
				"     else 0 end ), \n" +
//				 INDICE 80 - Ligacoes Consumo Media				    
				"   sum( r.quantidadeLigacoesConsumoMedia ), \n" +
//				 INDICE 81 - Quantidade Economias, Grupo Subcategoria 101				  
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 101 ) then \n" +
				"       r.quantidadeEconomias \n" +  
				"     else 0 end ), \n" +
//				 INDICE 82 - Quantidade Economias, Grupo Subcategoria 102
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 102 ) then \n" +
				"       r.quantidadeEconomias \n" +  
				"     else 0 end ), \n" +
//				 INDICE 83 - Quantidade Economias, Grupo Subcategoria 103
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 103 ) then \n" +
				"       r.quantidadeEconomias \n" +  
				"     else 0 end ), \n" +
//				 INDICE 84 - Quantidade Economias, Grupo Subcategoria 200
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 200 ) then \n" +
				"       r.quantidadeEconomias   \n" +
				"     else 0 end ), \n" +
//				 INDICE 85 - Quantidade Economias, Grupo Subcategoria 300				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 300 ) then \n" +
				"       r.quantidadeEconomias \n" +  
				"     else 0 end ), \n" +
//				 INDICE 86 - Quantidade Economias, Grupo Subcategoria 400				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 400 ) then \n" +
				"       r.quantidadeEconomias \n" +  
				"     else 0 end ), \n" +
//				 INDICE 87 - Quantidade Economias, Grupo Subcategoria 116				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 116 ) then \n" +
				"       r.quantidadeEconomias \n" +  
				"     else 0 end ), \n" +
//				 INDICE 88 - Quantidade Economias, Grupo Subcategoria 115				    
				"   sum(  \n" +
				"     case when ( r.codigoGrupoSubcategoria = 115 ) then \n" +
				"       r.quantidadeEconomias   \n" +
				"     else 0 end ), \n" +
//				 INDICE 89 - Quantidade Economias, Grupo Subcategoria 101				    
				"   sum( r.quantidadeEconomias ) \n" +
				select +
				" from \n" +
				"   UnResumoMetasAcumulado r \n" +
				" where \n " +
				"   r.anoMesReferencia = :amReferencia \n" +
				where +
				groupby +
				orderby;
		
			retorno = session.createQuery(consulta).
						setInteger( "amReferencia", filtrarRelatorioQuadroMetasAcumuladoHelper.getMesAnoReferencia() ).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;		
	}
	
	/**
	 * Pesquisa todas as tabelas de resumo para o Orcamento
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Rafael Pinto
	 * @date 11/01/2008
	 *
	 * @return anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public boolean existeDadosUnResumoParaOrcamentoSINP(int anoMesReferencia) 
		throws ErroRepositorioException {
		
		boolean existeDados = true;
		Integer retorno = null;
		
		Session session = HibernateUtil.getSessionGerencial();

		try{
			
			String consulta = "SELECT count(*) "
				+ "FROM UnResumoLigacaoEconomia resumo "
				+ "WHERE resumo.referencia = :referencia ";

			retorno = (Integer) session.createQuery(consulta).
				setInteger("referencia", anoMesReferencia).
				setMaxResults(1).
				uniqueResult();
			
			if(retorno == null || retorno == 0){
				existeDados = false;
			}

			if(existeDados){
				
				consulta = "SELECT count(*) "
					+ "FROM UnResumoConsumoAgua resumo "
					+ "WHERE resumo.referencia = :referencia ";

				retorno = (Integer) session.createQuery(consulta).
					setInteger("referencia", anoMesReferencia).
					setMaxResults(1).
					uniqueResult();
				
				if(retorno == null || retorno == 0){
					existeDados = false;
				}
				
				if(existeDados){
					
					consulta = "SELECT count(*) "
						+ "FROM UnResumoPendencia resumo "
						+ "WHERE resumo.anoMesReferencia = :referencia ";

					retorno = (Integer) session.createQuery(consulta).
						setInteger("referencia", anoMesReferencia).
						setMaxResults(1).
						uniqueResult();
					
					if(retorno == null || retorno == 0){
						existeDados = false;
					}
					
					if(existeDados){
						consulta = "SELECT count(*) "
							+ "FROM UnResumoFaturamento resumo "
							+ "WHERE resumo.referencia = :referencia ";

						retorno = (Integer) session.createQuery(consulta).
							setInteger("referencia", anoMesReferencia).
							setMaxResults(1).
							uniqueResult();
						
						if(retorno == null || retorno == 0){
							existeDados = false;
						}
						
						if(existeDados){
							consulta = "SELECT count(*) "
								+ "FROM UnResumoArrecadacao resumo "
								+ "WHERE resumo.anoMesReferencia = :referencia ";

							retorno = (Integer) session.createQuery(consulta).
								setInteger("referencia", anoMesReferencia).
								setMaxResults(1).
								uniqueResult();
							
							if(retorno == null || retorno == 0){
								existeDados = false;
							}
						}
					}
				}
			}
			


		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return existeDados;
	}	
	
	/**
	 * Pesquisa todas as tabelas de resumo para o Orcamento sem a tabela de resumo pendencia e arrecadação
	 *
	 * [UC0750] - Gerar Arquivo Texto para Orçamento e SINP
	 *
	 * @author Sávio Luiz
	 * @date 12/02/2008
	 *
	 * @return anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public boolean existeDadosUnResumoParcialParaOrcamentoSINP(int anoMesReferencia) 
		throws ErroRepositorioException {
		
		boolean existeDados = true;
		Integer retorno = null;
		
		Session session = HibernateUtil.getSessionGerencial();

		try{
			
			String consulta = "SELECT count(*) "
				+ "FROM UnResumoLigacaoEconomia resumo "
				+ "WHERE resumo.referencia = :referencia ";

			retorno = (Integer) session.createQuery(consulta).
				setInteger("referencia", anoMesReferencia).
				setMaxResults(1).
				uniqueResult();
			
			if(retorno == null || retorno == 0){
				existeDados = false;
			}

			if(existeDados){
				
				consulta = "SELECT count(*) "
					+ "FROM UnResumoConsumoAgua resumo "
					+ "WHERE resumo.referencia = :referencia ";

				retorno = (Integer) session.createQuery(consulta).
					setInteger("referencia", anoMesReferencia).
					setMaxResults(1).
					uniqueResult();
				
				if(retorno == null || retorno == 0){
					existeDados = false;
				}
				
				if(existeDados){
					consulta = "SELECT count(*) "
						+ "FROM UnResumoFaturamento resumo "
						+ "WHERE resumo.referencia = :referencia ";

					retorno = (Integer) session.createQuery(consulta).
						setInteger("referencia", anoMesReferencia).
						setMaxResults(1).
						uniqueResult();
					
					if(retorno == null || retorno == 0){
						existeDados = false;
					}
					
				}
				
			}
			


		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return existeDados;
	}
	
	/**
	 * Recupera todos os Centro de Custos para totalizacoes do Arquivo Texto.
	 * 
	 * [UC0750] - Gerar Arquivo Texto para Orçamento e SINP
	 * 
	 * @author Tiago Moreno
	 * @date 15/02/2008
	 * @return Collection (String)
	 * @throws ErroRepositorioException
	 */
	
	public Collection<String> pesquisarCentroCusto() 
	throws ErroRepositorioException {
	
		Collection<String> retorno = null;
		
		Session session = HibernateUtil.getSessionGerencial();
	
		try{
			
			String consulta = "SELECT  localidade.codigoCentroCusto "
				+ "FROM GLocalidade as localidade " 
				+ "GROUP BY localidade.codigoCentroCusto ";
	
			retorno = session.createQuery(consulta).list();
	
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}
	
	/**
	 * Pesquisa os valores necessarios na tabela un_resumo_ligacao_economia.
	 *
	 * [UC0750] - Gerar Arquivo Texto para Orçamento e SINP
	 *
	 * @author Tiago Moreno
	 * @date 18/02/2008
	 *
	 * @param Ano/Mes e Centro de Custo
	 * @return Collection<Object[35]>
	 * 
	 *  Object[0] - aguaTotalLigacoesCadastradas
	 *  Object[1] - esgotoTotalLigacoesCadastradas
	 *  Object[2] - esgotoTotalLigacoesCadastradasConvencional
	 *  Object[3] - aguaTotalLigacoesAtivas
	 *  Object[4] - esgotoTotalLigacoesCadastradasCondominial
	 *  Object[5] - aguaTotalLigacoesMedidas
	 *  Object[6] - esgotoTotalLigacoesAtivasConvencional
	 *  Object[7] - aguaTotalLigacoesComHidrometro
	 *  Object[8] - esgotoTotalLigacoesAtivasCondominial
	 *  Object[9] - aguaTotalLigacoesResidencialCadastradas
	 *  Object[10] - esgotoTotalLigacoesResidencialCadastradas
	 *  Object[11] - aguaTotalLigacoesDesligadas
	 *  Object[12] - aguaTotalEconomiasCadastradas
	 *  Object[13] - esgotoTotalEconomiasCadastradasConverncional
	 *  Object[14] - aguaTotalEconomiasAtivas
	 *  Object[15] - aguaTotalEconomiasCadastradasCondominial
	 *  Object[16] - aguaTotalEconomiasAtivasMedidas
	 *  Object[17] - esgotoTotalEconomiasAtivasConvencional
	 *  Object[18] - aguaTotalEconomiasResidencialCadastradas
	 *  Object[19] - esgotoTotalEconomiasAtivasCondominial
	 *  Object[20] - aguaTotalEconomiasResidencialAtivasMicromedidas
	 *  Object[21] - esgotoTotalEconomiasResidencialCadastradas
	 *  Object[22] - aguaTotalEconomiasResidencialAtivas
	 *  Object[23] - esgotoTotalEconomiasResidencialAtivas
	 *  Object[24] - aguaTotalEconomiasComercialAtivas
	 *  Object[25] - esgotoTotalEconomiasComercialAtivas
	 *  Object[26] - aguaTotalEconomiasIndustrialAtivas
	 *  Object[27] - esgotoTotalEconomiasIndustrialAtivas
	 *  Object[28] - aguaTotalEconomiasPublicoAtivas
	 *  Object[29] - esgotoTotalEconomiasPublicoAtivas
	 *  Object[30] - aguaTotalEconomiasRuralAtivas
	 *  Object[31] - aguaTotalLigacoesSuprimidas
	 *  
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarRelatorioOrcamentoSINPResumoLigacaoEconomiaArquivoTexto(String centroCusto, String anoMes) 
		throws ErroRepositorioException {
		
		Object[] retorno = null;
		
		Session session = HibernateUtil.getSessionGerencial();

		try{
			String sql = "SELECT  "
				+ "SUM(CASE WHEN last_id <> 1 AND last_id <> 2 THEN rele_qtligacoes ELSE 0 END) AS aguaTotalLigacoesCadastradas,"
				+ "SUM(CASE WHEN lest_id <> 1 AND lest_id <> 2 THEN rele_qtligacoes ELSE 0 END) AS esgotoTotalLigacoesCadastradas,"
				+ "SUM(CASE WHEN lest_id <> 1 AND lest_id <> 2 AND lepf_id <> 2 THEN rele_qtligacoes ELSE 0 END) AS esgotototalligacoescadastrada2,"
				+ "SUM(CASE WHEN last_id = 3 THEN rele_qtligacoes ELSE 0 END) AS aguaTotalLigacoesAtivas,"
				+ "SUM(CASE WHEN lest_id <> 1 AND lest_id <> 2 AND lepf_id = 2 THEN rele_qtligacoes ELSE 0 END) AS esgotototalligacoescadastradas,"
				+ "SUM(CASE WHEN last_id = 3 AND rele_ichidrometro=1 THEN rele_qtligacoes ELSE 0 END) AS aguaTotalLigacoesMedidas, "
				+ "SUM(CASE WHEN lepf_id <> 2 AND lest_id = 3 THEN rele_qtligacoes ELSE 0 END) AS esgotototalligacoesativasconve,"
				+ "SUM(CASE WHEN rele_ichidrometro=1 THEN rele_qtligacoes ELSE 0 END) AS aguaTotalLigacoesComHidrometro,"
				+ "SUM(CASE WHEN lepf_id = 2 AND lest_id = 3 THEN rele_qtligacoes ELSE 0 END) AS esgotototalligacoesativascondo,"
				+ "SUM(CASE WHEN last_id <> 1 AND last_id <> 2 AND catg_id = 1 THEN rele_qtligacoes ELSE 0 END) AS aguatotalligacoesresidencialca,"
				+ "SUM(CASE WHEN lest_id <> 1 AND lest_id <> 2 AND catg_id = 1 THEN rele_qtligacoes ELSE 0 END) AS esgotototalligacoesresidencial,"
				+ "SUM(CASE WHEN last_id = 5 THEN rele_qtligacoes ELSE 0 END) AS aguaTotalLigacoesDesligadas,"
				+ "SUM(CASE WHEN last_id <> 1 AND last_id <> 2 THEN rele_qteconomias ELSE 0 END) AS aguaTotalEconomiasCadastradas,"
				+ "SUM(CASE WHEN lest_id <> 1 AND lest_id <> 2 AND lepf_id <> 2 THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiascadastrad2,"
				+ "SUM(CASE WHEN last_id = 3 THEN rele_qteconomias ELSE 0 END) AS aguaTotalEconomiasAtivas,"
				+ "SUM(CASE WHEN lest_id <> 1 AND lest_id <> 2 AND lepf_id = 2 THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiascadastrada,"
				+ "SUM(CASE WHEN last_id = 3 AND rele_ichidrometro=1 THEN rele_qteconomias ELSE 0 END) AS aguatotaleconomiasativasmedida,"
				+ "SUM(CASE WHEN lepf_id <> 2 AND lest_id = 3 THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiasativasconv,"
				+ "SUM(CASE WHEN last_id <> 1 AND last_id <> 2 AND catg_id=1 and scat_id not in (13,62,63,64,70,71,77,78) THEN rele_qteconomias ELSE 0 END) AS aguatotaleconomiasresidencialc,"
				+ "SUM(CASE WHEN lepf_id = 2 AND lest_id = 3 THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiasativascond,"
				+ "SUM(CASE WHEN last_id = 3 AND rele_ichidrometro = 1 AND catg_id=1 and scat_id not in (13,62,63,64,70,71,77,78) THEN rele_qteconomias ELSE 0 END) AS aguatotaleconomiasresidencial2,"
				+ "SUM(CASE WHEN lest_id <> 1 AND lest_id <> 2 AND catg_id=1 and scat_id not in (13,62,63,64,70,71,77,78) THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiasresidenci2,"
				+ "SUM(CASE WHEN last_id = 3 AND catg_id=1 and scat_id not in (13,62,63,64,70,71,77,78) THEN rele_qteconomias ELSE 0 END) AS aguatotaleconomiasresidenciala,"
				+ "SUM(CASE WHEN lest_id = 3 AND catg_id=1 and scat_id not in (13,62,63,64,70,71,77,78) THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiasresidencia,"
				+ "SUM(CASE WHEN last_id = 3 AND catg_id=2 THEN rele_qteconomias ELSE 0 END) AS aguatotaleconomiascomercialati,"
				+ "SUM(CASE WHEN lest_id = 3 AND catg_id=2 THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiascomerciala,"
				+ "SUM(CASE WHEN last_id = 3 AND catg_id=3 THEN rele_qteconomias ELSE 0 END) AS aguatotaleconomiasindustrialat,"
				+ "SUM(CASE WHEN lest_id = 3 AND catg_id=3 THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiasindustrial,"
				+ "SUM(CASE WHEN last_id = 3 AND catg_id=4 THEN rele_qteconomias ELSE 0 END) AS aguatotaleconomiaspublicoativa,"
				+ "SUM(CASE WHEN lest_id = 3 AND catg_id=4 THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiaspublicoati,"
				+ "SUM(CASE WHEN last_id = 3 AND catg_id=1 and scat_id in (13,62,63,64,70,71,77,78)THEN rele_qteconomias ELSE 0 END) AS aguaTotalEconomiasRuralAtivas, "
				+ "SUM(CASE WHEN last_id in (6,7,8) THEN rele_qtligacoes ELSE 0 END) AS aguaTotalLigacoesSuprimidas, "
				+ "SUM(CASE WHEN lest_id = 3 AND catg_id=1 and scat_id in (13,62,63,64,70,71,77,78)THEN rele_qteconomias ELSE 0 END) AS esgotototaleconomiasruralativa "
				+ "FROM cadastro.un_res_lig_econ rlec, cadastro.g_localidade loca "
				+ "WHERE rlec.rele_amreferencia = :anoMes " 
				+ "AND rlec.loca_id = loca.loca_id AND loca.loca_cdcentrocusto = :centroCusto";
			
				
			//Faz a pesquisa
			retorno = (Object[]) session.createSQLQuery(sql).
				addScalar("aguaTotalLigacoesCadastradas", Hibernate.INTEGER).
				addScalar("esgotoTotalLigacoesCadastradas", Hibernate.INTEGER).
				addScalar("esgotototalligacoescadastrada2", Hibernate.INTEGER).
				addScalar("aguaTotalLigacoesAtivas", Hibernate.INTEGER).
				addScalar("esgotototalligacoescadastradas", Hibernate.INTEGER).
				addScalar("aguaTotalLigacoesMedidas", Hibernate.INTEGER).
				addScalar("esgotototalligacoesativasconve", Hibernate.INTEGER).
				addScalar("aguaTotalLigacoesComHidrometro", Hibernate.INTEGER).
				addScalar("esgotototalligacoesativascondo", Hibernate.INTEGER).
				addScalar("aguatotalligacoesresidencialca", Hibernate.INTEGER).
				addScalar("esgotototalligacoesresidencial", Hibernate.INTEGER).
				addScalar("aguaTotalLigacoesDesligadas", Hibernate.INTEGER).
				addScalar("aguaTotalEconomiasCadastradas", Hibernate.INTEGER).
				addScalar("esgotototaleconomiascadastrad2", Hibernate.INTEGER).
				addScalar("aguaTotalEconomiasAtivas", Hibernate.INTEGER).
				addScalar("esgotototaleconomiascadastrada", Hibernate.INTEGER).
				addScalar("aguatotaleconomiasativasmedida", Hibernate.INTEGER).
				addScalar("esgotototaleconomiasativasconv", Hibernate.INTEGER).
				addScalar("aguatotaleconomiasresidencialc", Hibernate.INTEGER).
				addScalar("esgotototaleconomiasativascond", Hibernate.INTEGER).
				addScalar("aguatotaleconomiasresidencial2", Hibernate.INTEGER).
				addScalar("esgotototaleconomiasresidenci2", Hibernate.INTEGER).
				addScalar("aguatotaleconomiasresidenciala", Hibernate.INTEGER).
				addScalar("esgotototaleconomiasresidencia", Hibernate.INTEGER).
				addScalar("aguatotaleconomiascomercialati", Hibernate.INTEGER).
				addScalar("esgotototaleconomiascomerciala", Hibernate.INTEGER).
				addScalar("aguatotaleconomiasindustrialat", Hibernate.INTEGER).
				addScalar("esgotototaleconomiasindustrial", Hibernate.INTEGER).
				addScalar("aguatotaleconomiaspublicoativa", Hibernate.INTEGER).
				addScalar("esgotototaleconomiaspublicoati", Hibernate.INTEGER).
				addScalar("aguaTotalEconomiasRuralAtivas", Hibernate.INTEGER).
				addScalar("aguaTotalLigacoesSuprimidas", Hibernate.INTEGER).
				addScalar("esgotototaleconomiasruralativa", Hibernate.INTEGER).
				setString("centroCusto", centroCusto).
				setString("anoMes",anoMes).
				setMaxResults(1).
				uniqueResult();
	

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}
	
	/**
	 * Pesquisa os valores necessarios na tabela un_resumo_faturamento
	 *
	 * [UC0750] - Gerar Arquivo Texto para Orçamento e SINP
	 *
	 * @author Tiago Moreno
	 * @date 18/02/2008
	 *
	 * @param Ano/Mes e Centro de Custo
	 * @return Object[29]
	 * 
	 *  Object[0] - aguaTotalVolumeFaturadoMedido
	 *  Object[1] - esgotoTotalVolumeFaturadoResidencial
	 *  Object[2] - esgotoTotalVolumeFaturadoComercial
	 *  Object[3] - aguaTotalVolumeFaturadoEstimado
	 *  Object[4] - esgotoTotalVolumeFaturadoIndustrial
	 *  Object[5] - esgotoTotalVolumeFaturadoPublico
	 *  Object[6] - aguaTotalVolumeFaturadoResidencial
	 *  Object[7] - esgotoTotalVolumeFaturadoGeral
	 *  Object[8] - aguaTotalVolumeFaturadoComercial
	 *  Object[9] - aguaTotalVolumeFaturadoIndustrial
	 *  Object[10] - aguaTotalVolumeFaturadoPublico
	 *  Object[11] - aguaTotalVolumeFaturadoRural
	 *  Object[12] - aguaTotalVolumeFaturadoGeral
	 *  Object[13] - aguaTotalFaturadoResidencial
	 *  Object[14] - esgotoTotalFaturadoResidencial
	 *  Object[15] - aguaTotalFaturadoComercial
	 *  Object[16] - esgotoTotalFaturadoComercial
	 *  Object[17] - aguaTotalFaturadoIndustrial
	 *  Object[18] - esgotoTotalFaturadoIndustrial
	 *  Object[19] - aguaTotalFaturadoPublico
	 *  Object[20] - esgotoTotalFaturadoPublico
	 *  Object[21] - aguaTotalFaturadoDireto
	 *  Object[22] - esgotoTotalFaturadoDireto
	 *  Object[23] - aguaTotalFaturadoIndireto
	 *  Object[24] - esgotoTotalFaturadoIndireto
	 *  Object[25] - devolucao
	 *  Object[26] - esgotoTotalFaturadoRural
	 *   
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarRelatorioOrcamentoSINPResumoFaturamentoArquivoTexto(String centroCusto, String anoMes) 
		throws ErroRepositorioException {
		
		Object[] retorno = null;
		
		Session session = HibernateUtil.getSessionGerencial();
		
		try{
			
			String sql = "SELECT " 
				+ "SUM(CASE WHEN refa_ichidrometro = 1 THEN refa_vofaturadoagua ELSE 0 END) AS aguaTotalVolumeFaturadoMedido,"
				+ "SUM(CASE WHEN catg_id = 1 THEN refa_vofaturadoesgoto ELSE 0 END) AS esgotototalvolumefaturadoresid,"
				+ "SUM(CASE WHEN catg_id = 2 THEN refa_vofaturadoesgoto ELSE 0 END) AS esgotototalvolumefaturadocomer,"
				+ "SUM(CASE WHEN refa_ichidrometro = 2 THEN refa_vofaturadoagua ELSE 0 END) AS aguatotalvolumefaturadoestimad,"
				+ "SUM(CASE WHEN catg_id = 3 THEN refa_vofaturadoesgoto ELSE 0 END) AS esgotototalvolumefaturadoindus,"
				+ "SUM(CASE WHEN catg_id = 4 THEN refa_vofaturadoesgoto ELSE 0 END) AS esgotototalvolumefaturadopubli,"
				+ "SUM(CASE WHEN catg_id = 1 AND scat_id not in (13,62,63,64,70,71,77,78) THEN refa_vofaturadoagua ELSE 0 END) AS aguatotalvolumefaturadoresiden,"
				+ "SUM(COALESCE(refa_vofaturadoesgoto,0)) AS esgotoTotalVolumeFaturadoGeral,"
				+ "SUM(CASE WHEN catg_id = 2 THEN refa_vofaturadoagua ELSE 0 END) AS aguatotalvolumefaturadocomerci,"
				+ "SUM(CASE WHEN catg_id = 3 THEN refa_vofaturadoagua ELSE 0 END) AS aguatotalvolumefaturadoindustr,"
				+ "SUM(CASE WHEN catg_id = 4 THEN refa_vofaturadoagua ELSE 0 END) AS aguaTotalVolumeFaturadoPublico,"
				+ "SUM(CASE WHEN catg_id = 1 AND scat_id in (13,62,63,64,70,71,77,78) THEN refa_vofaturadoagua ELSE 0 END) AS aguaTotalVolumeFaturadoRural,"
				+ "SUM(COALESCE(refa_vofaturadoagua,0)) AS aguaTotalVolumeFaturadoGeral,"
				+ "SUM(CASE WHEN catg_id = 1 THEN refa_vlfaturadoagua ELSE 0 END) AS aguaTotalFaturadoResidencial,"
				+ "SUM(CASE WHEN catg_id = 1 THEN refa_vlfaturadoesgoto ELSE 0 END) AS esgotoTotalFaturadoResidencial,"
				+ "SUM(CASE WHEN catg_id = 2 THEN refa_vlfaturadoagua ELSE 0 END) AS aguaTotalFaturadoComercial,"
				+ "SUM(CASE WHEN catg_id = 2 THEN refa_vlfaturadoesgoto ELSE 0 END) AS esgotoTotalFaturadoComercial,"
				+ "SUM(CASE WHEN catg_id = 3 THEN refa_vlfaturadoagua ELSE 0 END) AS aguaTotalFaturadoIndustrial,"
				+ "SUM(CASE WHEN catg_id = 3 THEN refa_vlfaturadoesgoto ELSE 0 END) AS esgotoTotalFaturadoIndustrial,"
				+ "SUM(CASE WHEN catg_id = 4 THEN refa_vlfaturadoagua ELSE 0 END) AS aguaTotalFaturadoPublico,"
				+ "SUM(CASE WHEN catg_id = 4 THEN refa_vlfaturadoesgoto ELSE 0 END) AS esgotoTotalFaturadoPublico,"
				+ "SUM(COALESCE(refa_vlfaturadoagua,0)) AS aguaTotalFaturadoDireto,"
				+ "SUM(COALESCE(refa_vlfaturadoesgoto,0)) AS esgotoTotalFaturadoDireto,"
				+ "SUM(CASE WHEN fntp_id = 1 AND lict_id in (1,2,3,4,5,6,10,12) THEN refa_vldocsfaturadosoutros ELSE 0 END) AS aguaTotalFaturadoIndireto, "
				+ "SUM(CASE WHEN fntp_id = 1 AND lict_id in (7,8,9,11) THEN refa_vldocsfaturadosoutros ELSE 0 END) AS esgotoTotalFaturadoIndireto, "
				+ "SUM(COALESCE(refa_vldocsfaturadoscredito,0)) AS devolucao, " 
				+ "SUM(CASE WHEN catg_id = 1 AND scat_id in (13,62,63,64,70,71,77,78) THEN refa_vlfaturadoagua ELSE 0 END) AS aguaTotalFaturadoRural "
				+ "FROM faturamento.un_resumo_faturamento rfa, cadastro.g_localidade loca "
				+ "WHERE rfa.refa_amreferencia = :anoMes "
				+ "AND rfa.loca_id = loca.loca_id AND loca.loca_cdcentrocusto = :centroCusto";
			
		    //Faz a pesquisa
			retorno = (Object[]) session.createSQLQuery(sql).
				addScalar("aguaTotalVolumeFaturadoMedido", Hibernate.INTEGER).
				addScalar("esgotototalvolumefaturadoresid", Hibernate.INTEGER).
				addScalar("esgotototalvolumefaturadocomer", Hibernate.INTEGER).
				addScalar("aguatotalvolumefaturadoestimad", Hibernate.INTEGER).
				addScalar("esgotototalvolumefaturadoindus", Hibernate.INTEGER).
				addScalar("esgotototalvolumefaturadopubli", Hibernate.INTEGER).
				addScalar("aguatotalvolumefaturadoresiden", Hibernate.INTEGER).
				addScalar("esgotoTotalVolumeFaturadoGeral", Hibernate.INTEGER).
				addScalar("aguatotalvolumefaturadocomerci", Hibernate.INTEGER).
				addScalar("aguatotalvolumefaturadoindustr", Hibernate.INTEGER).
				addScalar("aguaTotalVolumeFaturadoPublico", Hibernate.INTEGER).
				addScalar("aguaTotalVolumeFaturadoRural", Hibernate.INTEGER).
				addScalar("aguaTotalVolumeFaturadoGeral", Hibernate.INTEGER).
				addScalar("aguaTotalFaturadoResidencial", Hibernate.BIG_DECIMAL).
				addScalar("esgotoTotalFaturadoResidencial", Hibernate.BIG_DECIMAL).
				addScalar("aguaTotalFaturadoComercial", Hibernate.BIG_DECIMAL).
				addScalar("esgotoTotalFaturadoComercial", Hibernate.BIG_DECIMAL).
				addScalar("aguaTotalFaturadoIndustrial", Hibernate.BIG_DECIMAL).
				addScalar("esgotoTotalFaturadoIndustrial", Hibernate.BIG_DECIMAL).
				addScalar("aguaTotalFaturadoPublico", Hibernate.BIG_DECIMAL).
				addScalar("esgotoTotalFaturadoPublico", Hibernate.BIG_DECIMAL).
				addScalar("aguaTotalFaturadoDireto", Hibernate.BIG_DECIMAL).
				addScalar("esgotoTotalFaturadoDireto", Hibernate.BIG_DECIMAL).
				addScalar("aguaTotalFaturadoIndireto", Hibernate.BIG_DECIMAL).
				addScalar("esgotoTotalFaturadoIndireto", Hibernate.BIG_DECIMAL).
				addScalar("devolucao", Hibernate.BIG_DECIMAL).
				addScalar("aguaTotalFaturadoRural", Hibernate.BIG_DECIMAL).
				setString("centroCusto", centroCusto).
				setString("anoMes",anoMes).
				setMaxResults(1).
				uniqueResult();
				

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}

	
	/**
	 * Pesquisa todas as localidades da tabela g_localidade
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Sávio Luiz
	 * @date 21/11/2006
	 *
	 * @return Collection<Integer>
	 * 
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarNomesUnidadeGerencia(Integer idLocalidade) 
		throws ErroRepositorioException {
		
		Object[] retorno = null;
		
		Session session = HibernateUtil.getSessionGerencial();

		try{
			
			String consulta = "SELECT  gerencia.nome,unidade.nome "
				+ "FROM GLocalidade as localidade "
				+ "INNER JOIN localidade.gerGerenciaRegional as gerencia "
				+ "INNER JOIN localidade.gerUnidadeNegocio as unidade "
				+ "WHERE localidade.id = :idLocalidade ";

			retorno = (Object[])session.createQuery(consulta)
			.setInteger("idLocalidade",idLocalidade)
			.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}
	
	/**
	 * 
	 * [UC0752] Gerar Quadro de metas por Exercicio
	 * 
	 * @author Bruno Barros
	 * @param filtrarRelatorioQuadroMetasExercicioHelper
	 * @return
	 */
	public List pesquisarRelatorioQuadroMetasExercicio(
			FiltrarRelatorioQuadroMetasExercicioHelper filtrarRelatorioQuadroMetasExercicioHelper) 
		throws ErroRepositorioException{

		List retorno = null;
		
		Session session = HibernateUtil.getSessionGerencial();
		
		Integer qtdGrupos = 0;
		
		String select = "";
		String where  = "";
		String groupby = "group by \n";
		String orderby = "order by \n";
		
		// opções de totalização
		if ( filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao() == TOTALIZACAO_GERENCIA_REGIONAL  ||
			 filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao() == TOTALIZACAO_UNIDADE_NEGOCIO 	||
			 filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao() == TOTALIZACAO_LOCALIDADE 		||
			 filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao() == TOTALIZACAO_ESTADO_GERENCIA_REGIONAL  ||
			 filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao() == TOTALIZACAO_ESTADO_UNIDADE_NEGOCIO  ||
			 filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao() == TOTALIZACAO_ESTADO_LOCALIDADE ){
			select  += "   ,res.gerGerenciaRegional.id \n";
			groupby += "   res.gerGerenciaRegional.id \n";
			orderby += "   res.gerGerenciaRegional.id \n"; 
			
			qtdGrupos++;			
		}

		if ( filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao() == TOTALIZACAO_UNIDADE_NEGOCIO 	||
			 filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao() == TOTALIZACAO_LOCALIDADE 		||
			 filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao() == TOTALIZACAO_ESTADO_UNIDADE_NEGOCIO  ||
			 filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao() == TOTALIZACAO_ESTADO_LOCALIDADE ){		
			select  += "   ,res.gerUnidadeNegocio.id \n";			
			groupby += "   " + ( qtdGrupos > 0 ? "," : "" ) + "res.gerUnidadeNegocio.id \n";
			orderby += "   " + ( qtdGrupos > 0 ? "," : "" ) + "res.gerUnidadeNegocio.id \n";			
			
			qtdGrupos++;
		}
		
		if ( filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao() == TOTALIZACAO_LOCALIDADE 		||
			 filtrarRelatorioQuadroMetasExercicioHelper.getOpcaoTotalizacao() == TOTALIZACAO_ESTADO_LOCALIDADE ){		
				select  += "   ,res.gerLocalidade.id, res.gerLocalidade.codigoCentroCusto \n";
				groupby += "   " + ( qtdGrupos > 0 ? "," : "" ) + "res.gerLocalidade.id, res.gerLocalidade.codigoCentroCusto \n";
				orderby += "   " + ( qtdGrupos > 0 ? "," : "" ) + "res.gerLocalidade.id \n, res.gerLocalidade.codigoCentroCusto";
				qtdGrupos++;		
		}
		
		if ( qtdGrupos == 0 ){
			select  = "";
			groupby = "";
			orderby = "";			
		}
		
		// Montamos a clausula where
		if ( filtrarRelatorioQuadroMetasExercicioHelper.getGerenciaRegional() != null && 
			 !filtrarRelatorioQuadroMetasExercicioHelper.getGerenciaRegional().equals( 0 )  ){
			where += "   and res.gerGerenciaRegional.id = " + filtrarRelatorioQuadroMetasExercicioHelper.getGerenciaRegional(); 
		}
		
		if ( filtrarRelatorioQuadroMetasExercicioHelper.getUnidadeNegocio() != null && 
			!filtrarRelatorioQuadroMetasExercicioHelper.getUnidadeNegocio().equals( 0 )  ){
			where += "   and res.gerUnidadeNegocio.id = " + filtrarRelatorioQuadroMetasExercicioHelper.getUnidadeNegocio(); 
		}
		
		if ( filtrarRelatorioQuadroMetasExercicioHelper.getLocalidade() != null && 
    		!filtrarRelatorioQuadroMetasExercicioHelper.getLocalidade().equals( 0 )  ){
			where += "   and res.gerLocalidade.id = " + filtrarRelatorioQuadroMetasExercicioHelper.getLocalidade(); 
		}	

		try{
			
			String consulta = 
				"select " +
				// Ligacoes Cadastradas - DEZEMBRO - 0
				"  sum ( case when (  res.anoMesReferencia = :anoMesInicial and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCadastradas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cadastradas - JANEIRO - 1
				"  sum ( case when (  res.anoMesReferencia = :anoMes1 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCadastradas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cadastradas - FEVEREIRO - 2
				"  sum ( case when (  res.anoMesReferencia = :anoMes2 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCadastradas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cadastradas - MARCO - 3
				"  sum ( case when (  res.anoMesReferencia = :anoMes3 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCadastradas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cadastradas - ABRIL - 4
				"  sum ( case when (  res.anoMesReferencia = :anoMes4 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCadastradas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cadastradas - MAIO - 5
				"  sum ( case when (  res.anoMesReferencia = :anoMes5 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCadastradas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cadastradas - JUNHO - 6
				"  sum ( case when (  res.anoMesReferencia = :anoMes6 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCadastradas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cadastradas - JULHO - 7
				"  sum ( case when (  res.anoMesReferencia = :anoMes7 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCadastradas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cadastradas - AGOSTO - 8
				"  sum ( case when (  res.anoMesReferencia = :anoMes8 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCadastradas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cadastradas - SETEMBRO - 9
				"  sum ( case when (  res.anoMesReferencia = :anoMes9 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCadastradas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cadastradas - OUTUBRO - 10
				"  sum ( case when (  res.anoMesReferencia = :anoMes10 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCadastradas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cadastradas - NOVEMBRO - 11
				"  sum ( case when (  res.anoMesReferencia = :anoMesFinal and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCadastradas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cortadas - DEZEMBRO - 12
				"  sum ( case when (  res.anoMesReferencia = :anoMesInicial and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCortadas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cortadas - JANEIRO - 13
				"  sum ( case when (  res.anoMesReferencia = :anoMes1 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCortadas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cortadas - FEVEREIRO - 14
				"  sum ( case when (  res.anoMesReferencia = :anoMes2 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCortadas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cortadas - MARCO - 15
				"  sum ( case when (  res.anoMesReferencia = :anoMes3 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCortadas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cortadas - ABRIL - 16
				"  sum ( case when (  res.anoMesReferencia = :anoMes4 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCortadas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cortadas - MAIO - 17
				"  sum ( case when (  res.anoMesReferencia = :anoMes5 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCortadas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cortadas - JUNHO - 18
				"  sum ( case when (  res.anoMesReferencia = :anoMes6 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCortadas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cortadas - JULHO - 19
				"  sum ( case when (  res.anoMesReferencia = :anoMes7 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCortadas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cortadas - AGOSTO - 20
				"  sum ( case when (  res.anoMesReferencia = :anoMes8 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCortadas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cortadas - SETEMBRO - 21
				"  sum ( case when (  res.anoMesReferencia = :anoMes9 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCortadas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cortadas - OUTUBRO - 22
				"  sum ( case when (  res.anoMesReferencia = :anoMes10 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCortadas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Cortadas - NOVEMBRO - 23
				"  sum ( case when (  res.anoMesReferencia = :anoMesFinal and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesCortadas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Suprimidas - DEZEMBRO - 24
				"  sum ( case when (  res.anoMesReferencia = :anoMesInicial and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesSuprimidas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Suprimidas - JANEIRO - 25
				"  sum ( case when (  res.anoMesReferencia = :anoMes1 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesSuprimidas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Suprimidas - FEVEREIRO - 26
				"  sum ( case when (  res.anoMesReferencia = :anoMes2 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesSuprimidas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Suprimidas - MARCO - 27
				"  sum ( case when (  res.anoMesReferencia = :anoMes3 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesSuprimidas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Suprimidas - ABRIL - 28
				"  sum ( case when (  res.anoMesReferencia = :anoMes4 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesSuprimidas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Suprimidas - MAIO - 29
				"  sum ( case when (  res.anoMesReferencia = :anoMes5 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesSuprimidas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Suprimidas - JUNHO - 30
				"  sum ( case when (  res.anoMesReferencia = :anoMes6 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesSuprimidas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Suprimidas - JULHO - 31
				"  sum ( case when (  res.anoMesReferencia = :anoMes7 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesSuprimidas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Suprimidas - AGOSTO - 32
				"  sum ( case when (  res.anoMesReferencia = :anoMes8 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesSuprimidas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Suprimidas - SETEMBRO - 33
				"  sum ( case when (  res.anoMesReferencia = :anoMes9 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesSuprimidas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Suprimidas - OUTUBRO - 34
				"  sum ( case when (  res.anoMesReferencia = :anoMes10 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesSuprimidas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Suprimidas - NOVEMBRO - 35
				"  sum ( case when (  res.anoMesReferencia = :anoMesFinal and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesSuprimidas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Ativas - DEZEMBRO - 36
				"  sum ( case when (  res.anoMesReferencia = :anoMesInicial and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Ativas - JANEIRO - 37
				"  sum ( case when (  res.anoMesReferencia = :anoMes1 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Ativas - FEVEREIRO - 38
				"  sum ( case when (  res.anoMesReferencia = :anoMes2 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Ativas - MARCO - 39
				"  sum ( case when (  res.anoMesReferencia = :anoMes3 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Ativas - ABRIL - 40
				"  sum ( case when (  res.anoMesReferencia = :anoMes4 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Ativas - MAIO - 41
				"  sum ( case when (  res.anoMesReferencia = :anoMes5 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Ativas - JUNHO - 42
				"  sum ( case when (  res.anoMesReferencia = :anoMes6 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Ativas - JULHO - 43
				"  sum ( case when (  res.anoMesReferencia = :anoMes7 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Ativas - AGOSTO - 44
				"  sum ( case when (  res.anoMesReferencia = :anoMes8 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Ativas - SETEMBRO - 45
				"  sum ( case when (  res.anoMesReferencia = :anoMes9 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Ativas - OUTUBRO - 46
				"  sum ( case when (  res.anoMesReferencia = :anoMes10 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Ativas - NOVEMBRO - 47
				"  sum ( case when (  res.anoMesReferencia = :anoMesFinal and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivas, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Debito3m - DEZEMBRO - 48
				"  sum ( case when (  res.anoMesReferencia = :anoMesInicial and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivasDebito3m, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Debito3m - JANEIRO - 49
				"  sum ( case when (  res.anoMesReferencia = :anoMes1 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivasDebito3m, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Debito3m - FEVEREIRO - 50
				"  sum ( case when (  res.anoMesReferencia = :anoMes2 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivasDebito3m, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Debito3m - MARCO - 51
				"  sum ( case when (  res.anoMesReferencia = :anoMes3 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivasDebito3m, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Debito3m - ABRIL - 52
				"  sum ( case when (  res.anoMesReferencia = :anoMes4 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivasDebito3m, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Debito3m - MAIO - 53
				"  sum ( case when (  res.anoMesReferencia = :anoMes5 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivasDebito3m, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Debito3m - JUNHO - 54
				"  sum ( case when (  res.anoMesReferencia = :anoMes6 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivasDebito3m, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Debito3m - JULHO - 55
				"  sum ( case when (  res.anoMesReferencia = :anoMes7 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivasDebito3m, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Debito3m - AGOSTO - 56
				"  sum ( case when (  res.anoMesReferencia = :anoMes8 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivasDebito3m, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Debito3m - SETEMBRO - 57
				"  sum ( case when (  res.anoMesReferencia = :anoMes9 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivasDebito3m, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Debito3m - OUTUBRO - 58
				"  sum ( case when (  res.anoMesReferencia = :anoMes10 and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivasDebito3m, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Debito3m - NOVEMBRO - 59
				"  sum ( case when (  res.anoMesReferencia = :anoMesFinal and res.codigoGrupoSubcategoria <> 400 ) then " +
				"          coalesce (res.quantidadeLigacoesAtivasDebito3m, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Medio - DEZEMBRO - 60
				"  sum ( case when (  res.anoMesReferencia = :anoMesInicial and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Medio - JANEIRO - 61
				"  sum ( case when (  res.anoMesReferencia = :anoMes1 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Medio - FEVEREIRO - 62
				"  sum ( case when (  res.anoMesReferencia = :anoMes2 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Medio - MARCO - 63
				"  sum ( case when (  res.anoMesReferencia = :anoMes3 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Medio - ABRIL - 64
				"  sum ( case when (  res.anoMesReferencia = :anoMes4 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Medio - MAIO - 65
				"  sum ( case when (  res.anoMesReferencia = :anoMes5 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Medio - JUNHO - 66
				"  sum ( case when (  res.anoMesReferencia = :anoMes6 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Medio - JULHO - 67
				"  sum ( case when (  res.anoMesReferencia = :anoMes7 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Medio - AGOSTO - 68
				"  sum ( case when (  res.anoMesReferencia = :anoMes8 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Medio - SETEMBRO - 69
				"  sum ( case when (  res.anoMesReferencia = :anoMes9 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Medio - OUTUBRO - 70
				"  sum ( case when (  res.anoMesReferencia = :anoMes10 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Medio - NOVEMBRO - 71
				"  sum ( case when (  res.anoMesReferencia = :anoMesFinal and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Ate 5m3 - DEZEMBRO - 72
				"  sum ( case when (  res.anoMesReferencia = :anoMesInicial and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoAte5m3, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Ate 5m3 - JANEIRO - 73
				"  sum ( case when (  res.anoMesReferencia = :anoMes1 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoAte5m3, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Ate 5m3 - FEVEREIRO - 74
				"  sum ( case when (  res.anoMesReferencia = :anoMes2 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoAte5m3, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Ate 5m3 - MARCO - 75
				"  sum ( case when (  res.anoMesReferencia = :anoMes3 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoAte5m3, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Ate 5m3 - ABRIL - 76
				"  sum ( case when (  res.anoMesReferencia = :anoMes4 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoAte5m3, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Ate 5m3 - MAIO - 77
				"  sum ( case when (  res.anoMesReferencia = :anoMes5 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoAte5m3, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Ate 5m3 - JUNHO - 78
				"  sum ( case when (  res.anoMesReferencia = :anoMes6 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoAte5m3, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Ate 5m3 - JULHO - 79
				"  sum ( case when (  res.anoMesReferencia = :anoMes7 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoAte5m3, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Ate 5m3 - AGOSTO - 80
				"  sum ( case when (  res.anoMesReferencia = :anoMes8 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoAte5m3, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Ate 5m3 - SETEMBRO - 81
				"  sum ( case when (  res.anoMesReferencia = :anoMes9 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoAte5m3, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Ate 5m3 - OUTUBRO - 82
				"  sum ( case when (  res.anoMesReferencia = :anoMes10 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoAte5m3, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Ate 5m3 - NOVEMBRO - 83
				"  sum ( case when (  res.anoMesReferencia = :anoMesFinal and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoAte5m3, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Não Medido - DEZEMBRO - 84
				"  sum ( case when (  res.anoMesReferencia = :anoMesInicial and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoNaoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Não Medido - JANEIRO - 85
				"  sum ( case when (  res.anoMesReferencia = :anoMes1 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoNaoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Não Medido - FEVEREIRO - 86
				"  sum ( case when (  res.anoMesReferencia = :anoMes2 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoNaoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Não Medido - MARCO - 87
				"  sum ( case when (  res.anoMesReferencia = :anoMes3 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoNaoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Não Medido - ABRIL - 88
				"  sum ( case when (  res.anoMesReferencia = :anoMes4 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoNaoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Não Medido - MAIO - 89
				"  sum ( case when (  res.anoMesReferencia = :anoMes5 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoNaoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Não Medido - JUNHO - 90
				"  sum ( case when (  res.anoMesReferencia = :anoMes6 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoNaoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Não Medido - JULHO - 91
				"  sum ( case when (  res.anoMesReferencia = :anoMes7 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoNaoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Não Medido - AGOSTO - 92
				"  sum ( case when (  res.anoMesReferencia = :anoMes8 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoNaoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Não Medido - SETEMBRO - 93
				"  sum ( case when (  res.anoMesReferencia = :anoMes9 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoNaoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Não Medido - OUTUBRO - 94
				"  sum ( case when (  res.anoMesReferencia = :anoMes10 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoNaoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Não Medido - NOVEMBRO - 95
				"  sum ( case when (  res.anoMesReferencia = :anoMesFinal and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoNaoMedido, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Media - DEZEMBRO - 96
				"  sum ( case when (  res.anoMesReferencia = :anoMesInicial and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedia, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Media - JANEIRO - 97
				"  sum ( case when (  res.anoMesReferencia = :anoMes1 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedia, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Media - FEVEREIRO - 98
				"  sum ( case when (  res.anoMesReferencia = :anoMes2 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedia, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Media - MARCO - 99
				"  sum ( case when (  res.anoMesReferencia = :anoMes3 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedia, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Media - ABRIL - 100
				"  sum ( case when (  res.anoMesReferencia = :anoMes4 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedia, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Media - MAIO - 101
				"  sum ( case when (  res.anoMesReferencia = :anoMes5 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedia, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Media - JUNHO - 102
				"  sum ( case when (  res.anoMesReferencia = :anoMes6 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedia, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Media - JULHO - 103
				"  sum ( case when (  res.anoMesReferencia = :anoMes7 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedia, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Media - AGOSTO - 104
				"  sum ( case when (  res.anoMesReferencia = :anoMes8 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedia, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Media - SETEMBRO - 105
				"  sum ( case when (  res.anoMesReferencia = :anoMes9 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedia, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Media - OUTUBRO - 106
				"  sum ( case when (  res.anoMesReferencia = :anoMes10 and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedia, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Consumo Media - NOVEMBRO - 107
				"  sum ( case when (  res.anoMesReferencia = :anoMesFinal and res.codigoGrupoSubcategoria not in ( 101,102, 116, 115 ) ) then " +
				"          coalesce (res.quantidadeLigacoesConsumoMedia, 0 )" +
				"        else" +
				"          0" +
				"  		 end ), " +
				// Ligacoes Quantidade de Economias - DEZEMBRO - 108
				"  sum ( case when (  res.anoMesReferencia = :anoMesInicial ) then " +
				"          coalesce (res.quantidadeEconomias, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Quantidade de Economias - JANEIRO - 109
				"  sum ( case when (  res.anoMesReferencia = :anoMes1 ) then " +
				"          coalesce (res.quantidadeEconomias, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Quantidade de Economias - FEVEREIRO - 110
				"  sum ( case when (  res.anoMesReferencia = :anoMes2 ) then " +
				"          coalesce (res.quantidadeEconomias, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Quantidade de Economias - MARCO - 111
				"  sum ( case when (  res.anoMesReferencia = :anoMes3 ) then " +
				"          coalesce (res.quantidadeEconomias, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Quantidade de Economias - ABRIL - 112
				"  sum ( case when (  res.anoMesReferencia = :anoMes4 ) then " +
				"          coalesce (res.quantidadeEconomias, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Quantidade de Economias - MAIO - 113
				"  sum ( case when (  res.anoMesReferencia = :anoMes5 ) then " +
				"          coalesce (res.quantidadeEconomias, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Quantidade de Economias - JUNHO - 114
				"  sum ( case when (  res.anoMesReferencia = :anoMes6 ) then " +
				"          coalesce (res.quantidadeEconomias, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Quantidade de Economias - JULHO - 115
				"  sum ( case when (  res.anoMesReferencia = :anoMes7 ) then " +
				"          coalesce (res.quantidadeEconomias, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Quantidade de Economias - AGOSTO - 116
				"  sum ( case when (  res.anoMesReferencia = :anoMes8 ) then " +
				"          coalesce (res.quantidadeEconomias, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Quantidade de Economias - SETEMBRO - 117
				"  sum ( case when (  res.anoMesReferencia = :anoMes9 ) then " +
				"          coalesce (res.quantidadeEconomias, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Quantidade de Economias - OUTUBRO - 118
				"  sum ( case when (  res.anoMesReferencia = :anoMes10 ) then " +
				"          coalesce (res.quantidadeEconomias, 0 )" +
				"        else" +
				"          0" +
				"        end ), " +
				// Ligacoes Quantidade de Economias - NOVEMBRO - 119
				"  sum ( case when (  res.anoMesReferencia = :anoMesFinal ) then " +
				"          coalesce (res.quantidadeEconomias, 0 )" +
				"        else" +
				"          0" +
				"  	 	end ) " +				
				select +
				"from " +
				"  UnResumoMetasAcumulado res " +
				"where " +
				"  res.anoMesReferencia between :anoMesInicial and :anoMesFinal " +
				where +
				groupby +
				orderby;
		
			retorno = session.createQuery(consulta).
						setInteger( "anoMesInicial", Integer.parseInt( ( filtrarRelatorioQuadroMetasExercicioHelper.getAnoExercicio() - 1 ) + "12" ) ).
						setInteger( "anoMesFinal", Integer.parseInt( filtrarRelatorioQuadroMetasExercicioHelper.getAnoExercicio() + "11" ) ).
						setInteger( "anoMes1", Integer.parseInt( filtrarRelatorioQuadroMetasExercicioHelper.getAnoExercicio() + "01" ) ).
						setInteger( "anoMes2", Integer.parseInt( filtrarRelatorioQuadroMetasExercicioHelper.getAnoExercicio() + "02" ) ).
						setInteger( "anoMes3", Integer.parseInt( filtrarRelatorioQuadroMetasExercicioHelper.getAnoExercicio() + "03" ) ).
						setInteger( "anoMes4", Integer.parseInt( filtrarRelatorioQuadroMetasExercicioHelper.getAnoExercicio() + "04" ) ).
						setInteger( "anoMes5", Integer.parseInt( filtrarRelatorioQuadroMetasExercicioHelper.getAnoExercicio() + "05" ) ).
						setInteger( "anoMes6", Integer.parseInt( filtrarRelatorioQuadroMetasExercicioHelper.getAnoExercicio() + "06" ) ).
						setInteger( "anoMes7", Integer.parseInt( filtrarRelatorioQuadroMetasExercicioHelper.getAnoExercicio() + "07" ) ).
						setInteger( "anoMes8", Integer.parseInt( filtrarRelatorioQuadroMetasExercicioHelper.getAnoExercicio() + "08" ) ).
						setInteger( "anoMes9", Integer.parseInt( filtrarRelatorioQuadroMetasExercicioHelper.getAnoExercicio() + "09" ) ).
						setInteger( "anoMes10", Integer.parseInt( filtrarRelatorioQuadroMetasExercicioHelper.getAnoExercicio() + "10" ) ).				
						list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;		
	}
	
	/**
	 * Pesquisa os valores necessarios na tabela un_resumo_coleta_esgoto
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Hugo Amorim
	 * @date 31/08/2010
	 *
	 * @param FiltrarRelatorioOrcamentoSINPHelper
	 * @return Object[4]
	 * 
	 *  Object[0] - aguaTotalVolumeFaturadoMedido
	 *  Object[1] - esgotoTotalVolumeFaturadoResidencial
	 *  Object[2] - esgotoTotalVolumeFaturadoComercial
	 *  Object[3] - aguaTotalVolumeFaturadoEstimado
	 *   
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarRelatorioOrcamentoSINPResumoColetaEsgoto(
		FiltrarRelatorioOrcamentoSINPHelper filtrarRelatorioOrcamentoSINPHelper) 
		throws ErroRepositorioException {
		Object[] retorno = null;
		int anoMes = filtrarRelatorioOrcamentoSINPHelper.getAnoMesReferencia();
		
		Integer localidade = filtrarRelatorioOrcamentoSINPHelper.getLocalidade();
		Integer unidade = filtrarRelatorioOrcamentoSINPHelper.getUnidadeNegocio();
		Integer gerencia = filtrarRelatorioOrcamentoSINPHelper.getGerenciaRegional();
		Integer municipio = filtrarRelatorioOrcamentoSINPHelper.getMunicipio();
		boolean possuiCampo = false;
		if(localidade != null || unidade != null || gerencia != null || municipio != null){
			possuiCampo = true;
		}
		Integer opcaoTotalizacao = filtrarRelatorioOrcamentoSINPHelper.getOpcaoTotalizacao();
		String joinMunicipio = "  left join cadastro.g_municipio muni on ( muni.muni_id = loc.muni_idprincipal ) ";
		if(opcaoTotalizacao == 26 || opcaoTotalizacao == 19){
			joinMunicipio = "  inner join cadastro.g_municipio muni on ( muni.muni_id = loc.muni_idprincipal ) ";
		}
		
		Session session = HibernateUtil.getSessionGerencial();
		String groupBy = filtrarRelatorioOrcamentoSINPHelper.getGroupBy();
		String campoSelecionado = null;

		try{
			
			String sql = "SELECT " 
			
				+ "SUM(CASE WHEN rfat.rece_ichidrometro = 1 THEN rfat.rece_qtligacoes ELSE 0 END) as totalligacoesfaturadasmedidase, "
				+ "SUM(CASE WHEN rfat.rece_ichidrometro = 2 THEN rfat.rece_qtligacoes ELSE 0 END) as totalligacoesfaturadasnaomedi2, "
				+ "SUM(CASE WHEN rfat.rece_ichidrometro = 1 THEN rfat.rece_qteconomias ELSE 0 END) as totaleconomiasfaturadasmedida2, "			
				+ "SUM(CASE WHEN rfat.rece_ichidrometro = 2 THEN rfat.rece_qteconomias ELSE 0 END) as totaleconomiasfaturadasnaomed2 "
				+ (possuiCampo ? groupBy + " AS idCampoSelecionado" : "")
				+ " FROM micromedicao.un_resumo_coleta_esgoto rfat INNER JOIN cadastro.g_localidade loc on (rfat.loca_id = loc.loca_id) "
				+ joinMunicipio
				+ "WHERE rfat.rece_amreferencia = :anoMes";
			
			// Localidade
			if (localidade != null) {
				sql += " AND loc.loca_id = " + localidade;
				campoSelecionado = "loc.loca_id";
			}

			// Unidade de Negocio
			if (unidade != null) {
				sql += " AND rfat.uneg_id = " + unidade;
				campoSelecionado = "rfat.uneg_id";
			}

			// Gerencia Regional
			if (gerencia != null) {
				sql += " AND rfat.greg_id = " + gerencia;
				campoSelecionado = "rfat.greg_id";
			}

			//Município
			if(municipio != null){
				sql += " AND loc.muni_idprincipal = " + municipio;
				campoSelecionado = "loc.muni_idPrincipal";
			}
			
			if(!groupBy.equals("")){
				sql += " GROUP BY "+groupBy.substring(1)
					+ " ORDER BY "+groupBy.substring(1);
			}
			
			if(campoSelecionado != null){

				//Faz a pesquisa
				retorno = (Object[]) session.createSQLQuery(sql).
					addScalar("totalligacoesfaturadasmedidase", Hibernate.INTEGER).
					addScalar("totalligacoesfaturadasnaomedi2", Hibernate.INTEGER).
					addScalar("totaleconomiasfaturadasmedida2", Hibernate.INTEGER).		
					addScalar("totaleconomiasfaturadasnaomed2", Hibernate.INTEGER).
					addScalar("idCampoSelecionado", Hibernate.INTEGER).
					setInteger("anoMes",anoMes).
					setMaxResults(1).
					uniqueResult();
				
			}else{
				
				//Faz a pesquisa
				retorno = (Object[]) session.createSQLQuery(sql).
				addScalar("totalligacoesfaturadasmedidase", Hibernate.INTEGER).
				addScalar("totalligacoesfaturadasnaomedi2", Hibernate.INTEGER).
				addScalar("totaleconomiasfaturadasmedida2", Hibernate.INTEGER).		
				addScalar("totaleconomiasfaturadasnaomed2", Hibernate.INTEGER).
				setInteger("anoMes",anoMes).
					setInteger("anoMes",anoMes).
					setMaxResults(1).
					uniqueResult();
					
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}
	
	/**
	 * Pesquisa os valores necessarios na tabela financeiro.resumo_faturamento
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Hugo Leonardo
	 * @date 23/02/2011
	 *
	 * @param FiltrarRelatorioOrcamentoSINPHelper
	 * 
	 * @return 
	 *  Object[0] - esgotoTotalFaturadoResidencial
	 *  Object[1] - esgotoTotalFaturadoComercial
	 *  Object[2] - esgotoTotalFaturadoIndustrial
	 *  Object[3] - esgotoTotalFaturadoPublico
	 *  Object[4] - esgotoTotalFaturadoDireto
	 *  Object[5] - aguaTotalFaturadoResidencial
	 *  Object[6] - aguaTotalFaturadoComercial
	 *  Object[7] - aguaTotalFaturadoIndustrial
	 *  Object[8] - aguaTotalFaturadoPublico
	 *  Object[9] - aguaTotalFaturadoDireto
	 *  Object[10] - aguaTotalFaturadoIndireto
	 *	Object[11] - receitaOperacionalTotal
	 *  Object[12] - receitaOperacionalDireta
	 *  Object[13] - receitaOperacionalIndireta
	 *  Object[14] - aguaTotalFaturamentoGeralDI
	 *  Object[15] - totalFaturamentoLiquido
	 *   
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarRelatorioOrcamentoSINPFinanceiroResumoFaturamento(
		FiltrarRelatorioOrcamentoSINPHelper filtrarRelatorioOrcamentoSINPHelper) 
		throws ErroRepositorioException {
		
		Object[] retorno = null;
		
		int anoMes = filtrarRelatorioOrcamentoSINPHelper.getAnoMesReferencia();
		Integer localidade = filtrarRelatorioOrcamentoSINPHelper.getLocalidade();
		Integer unidade = filtrarRelatorioOrcamentoSINPHelper.getUnidadeNegocio();
		Integer gerencia = filtrarRelatorioOrcamentoSINPHelper.getGerenciaRegional();
		Integer municipio = filtrarRelatorioOrcamentoSINPHelper.getMunicipio();
		boolean possuiCampo = false;
		if(localidade != null || unidade != null || gerencia != null || municipio != null){
			possuiCampo = true;
		}
		Integer opcaoTotalizacao = filtrarRelatorioOrcamentoSINPHelper.getOpcaoTotalizacao();
		String joinMunicipio = "  left join cadastro.municipio muni on ( muni.muni_id = loc.muni_idprincipal ) ";
		if(opcaoTotalizacao == 26 || opcaoTotalizacao == 19){
			joinMunicipio = "  inner join cadastro.municipio muni on ( muni.muni_id = loc.muni_idprincipal ) ";
		}
		Session session = HibernateUtil.getSession();
		String groupBy = filtrarRelatorioOrcamentoSINPHelper.getGroupBy();
		String campoSelecionado = null;

		try{
			
			String sql = "SELECT " 
				
				+ "SUM(CASE WHEN (rfat.catg_id = 1 AND rfat.rfat_nnsequenciatipolancamento = 1120) THEN rfat.rfat_vlitemfaturamento ELSE 0 END) AS esgotoTotalFaturadoResidencial, " // 0
				+ "SUM(CASE WHEN (rfat.catg_id = 2 AND rfat.rfat_nnsequenciatipolancamento = 1120) THEN rfat.rfat_vlitemfaturamento ELSE 0 END) AS esgotoTotalFaturadoComercial, " // 1
				+ "SUM(CASE WHEN (rfat.catg_id = 3 AND rfat.rfat_nnsequenciatipolancamento = 1120) THEN rfat.rfat_vlitemfaturamento ELSE 0 END) AS esgotoTotalFaturadoIndustrial, " // 2
				+ "SUM(CASE WHEN (rfat.catg_id = 4 AND rfat.rfat_nnsequenciatipolancamento = 1120) THEN rfat.rfat_vlitemfaturamento ELSE 0 END) AS esgotoTotalFaturadoPublico, " // 3
				+ "SUM(CASE WHEN (rfat.catg_id in (1,2,3,4) AND rfat.rfat_nnsequenciatipolancamento = 1120) THEN rfat.rfat_vlitemfaturamento ELSE 0 END) AS esgotoTotalFaturadoDireto, " // 4
				+ "SUM(CASE WHEN (rfat.catg_id = 1 AND rfat.rfat_nnsequenciatipolancamento = 1110) THEN rfat.rfat_vlitemfaturamento ELSE 0 END) AS aguaTotalFaturadoResidencial, " // 5
				+ "SUM(CASE WHEN (rfat.catg_id = 2 AND rfat.rfat_nnsequenciatipolancamento = 1110) THEN rfat.rfat_vlitemfaturamento ELSE 0 END) AS aguaTotalFaturadoComercial, " // 6
				+ "SUM(CASE WHEN (rfat.catg_id = 3 AND rfat.rfat_nnsequenciatipolancamento = 1110) THEN rfat.rfat_vlitemfaturamento ELSE 0 END) AS aguaTotalFaturadoIndustrial, " // 7
				+ "SUM(CASE WHEN (rfat.catg_id = 4 AND rfat.rfat_nnsequenciatipolancamento = 1110) THEN rfat.rfat_vlitemfaturamento ELSE 0 END) AS aguaTotalFaturadoPublico, " // 8
				+ "SUM(CASE WHEN (rfat.catg_id in (1,2,3,4) AND rfat.rfat_nnsequenciatipolancamento = 1110) THEN rfat.rfat_vlitemfaturamento ELSE 0 END) AS aguaTotalFaturadoDireto, " // 9
				+ "SUM(CASE WHEN (rfat.catg_id in (1,2,3,4) AND rfat.rfat_nnsequenciatipolancamento = 1130) THEN rfat.rfat_vlitemfaturamento ELSE 0 END) AS aguaTotalFaturadoIndireto, " // 10
				+ "SUM(CASE WHEN (rfat.catg_id in (1,2,3,4) AND rfat.rfat_nnsequenciatipolancamento in (1110,1120,1130)) THEN rfat.rfat_vlitemfaturamento ELSE 0 END) AS receitaOperacionalTotal, " // 11
				+ "SUM(CASE WHEN (rfat.catg_id in (1,2,3,4) AND rfat.rfat_nnsequenciatipolancamento in (1110,1120)) THEN rfat.rfat_vlitemfaturamento ELSE 0 END) AS receitaOperacionalDireta, " // 12
				+ "SUM(CASE WHEN (rfat.catg_id in (1,2,3,4) AND rfat.rfat_nnsequenciatipolancamento = 1130) THEN rfat.rfat_vlitemfaturamento ELSE 0 END) AS receitaOperacionalIndireta, " // 13
				+ "SUM(CASE WHEN (rfat.catg_id in (1,2,3,4) AND rfat.rfat_nnsequenciatipolancamento in (1110,1130)) THEN rfat.rfat_vlitemfaturamento ELSE 0 END) AS aguaTotalFaturamentoGeralDI, " // 14
				+ "SUM(CASE WHEN (rfat.catg_id in (1,2,3,4) AND rfat.rfat_nnsequenciatipolancamento = 1200) THEN rfat.rfat_vlitemfaturamento ELSE 0 END) AS totalFaturamentoLiquido " // 15
				+ (possuiCampo  ? groupBy + " AS idCampoSelecionado" : "")
				+ " FROM financeiro.resumo_faturamento rfat INNER JOIN cadastro.localidade loc on (rfat.loca_id = loc.loca_id) "
				+ joinMunicipio
				+ "WHERE rfat.rfat_amreferencia = :anoMes";
			
			// Localidade
			if (localidade != null) {
				sql += " AND loc.loca_id = " + localidade;
				campoSelecionado = "loc.loca_id";
			}

			// Unidade de Negocio
			if (unidade != null) {
				sql += " AND rfat.uneg_id = " + unidade;
				campoSelecionado = "rfat.uneg_id";
			}

			// Gerencia Regional
			if (gerencia != null) {
				sql += " AND rfat.greg_id = " + gerencia;
				campoSelecionado = "rfat.greg_id";
			}

			if(municipio != null){
				sql += " AND loc.muni_idprincipal = " + municipio;
				campoSelecionado = "loc.muni_idprincipal";
			}
			
			if(!groupBy.equals("")){
				sql += " GROUP BY "+ groupBy.substring(1)
					+ " ORDER BY "+groupBy.substring(1);
			}
			
			if(campoSelecionado != null){

				//Faz a pesquisa
				retorno = (Object[]) session.createSQLQuery(sql).
					
					addScalar("esgotoTotalFaturadoResidencial", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoComercial", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoIndustrial", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoPublico", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoDireto", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoResidencial", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoComercial", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoIndustrial", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoPublico", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoDireto", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoIndireto", Hibernate.BIG_DECIMAL).
					addScalar("receitaOperacionalTotal", Hibernate.BIG_DECIMAL).
					addScalar("receitaOperacionalDireta", Hibernate.BIG_DECIMAL).
					addScalar("receitaOperacionalIndireta", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturamentoGeralDI", Hibernate.BIG_DECIMAL).
					addScalar("totalFaturamentoLiquido", Hibernate.BIG_DECIMAL).
					addScalar("idCampoSelecionado", Hibernate.INTEGER).
					setInteger("anoMes",anoMes).
					setMaxResults(1).
					uniqueResult();
			}else{
				
				//Faz a pesquisa
				retorno = (Object[]) session.createSQLQuery(sql).
					addScalar("esgotoTotalFaturadoResidencial", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoComercial", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoIndustrial", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoPublico", Hibernate.BIG_DECIMAL).
					addScalar("esgotoTotalFaturadoDireto", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoResidencial", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoComercial", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoIndustrial", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoPublico", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoDireto", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturadoIndireto", Hibernate.BIG_DECIMAL).
					addScalar("receitaOperacionalTotal", Hibernate.BIG_DECIMAL).
					addScalar("receitaOperacionalDireta", Hibernate.BIG_DECIMAL).
					addScalar("receitaOperacionalIndireta", Hibernate.BIG_DECIMAL).
					addScalar("aguaTotalFaturamentoGeralDI", Hibernate.BIG_DECIMAL).
					addScalar("totalFaturamentoLiquido", Hibernate.BIG_DECIMAL).
					setInteger("anoMes",anoMes).
					setMaxResults(1).
					uniqueResult();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}
	
	/**
	 * Pesquisa todas as tabelas de resumo Comercial para o Orcamento
	 *
	 * [UC0722] - Gerar Relatorio para Orçamento e SINP
	 *
	 * @author Hugo Leonardo
	 * @date 24/02/2011
	 *
	 * @return anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public boolean existeDadosComercialParaOrcamentoSINP(int anoMesReferencia) 
		throws ErroRepositorioException {
		
		boolean existeDados = true;
		Integer retorno = null;
		
		Session session = HibernateUtil.getSession();

		try{
			
			String consulta = " SELECT count(*) "
							+ " FROM ResumoFaturamento resumo "
							+ " WHERE resumo.anoMesReferencia = :referencia ";

			retorno = (Integer) session.createQuery(consulta).
				setInteger("referencia", anoMesReferencia).
				setMaxResults(1).
				uniqueResult();
			
			if(retorno == null || retorno == 0){
				existeDados = false;
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return existeDados;
	}
}
