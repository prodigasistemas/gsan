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
package gcom.arrecadacao;
 
import gcom.arrecadacao.bean.PesquisarAvisoBancarioPorContaCorrenteHelper;
import gcom.relatorio.arrecadacao.RelatorioAvisoBancarioPorContaCorrenteBean;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 * O repositório faz a comunicação com a base de dados através do hibernate. O
 * cliente usa o repositório como fonte de dados.
 * 
 * @author Sávio Luiz
 */
public class RepositorioArrecadacaoPostgresHBM extends RepositorioArrecadacaoHBM {

	
	/**
     * [UC0829] Gerar Relatório Avisos Bancarios Por Conta Corrente
     * 
     * @author Arthur Carvalho
     * @date 28/12/2010
     */
	@Override
    public List<RelatorioAvisoBancarioPorContaCorrenteBean> pesquisarAvisoBancarioPorContaCorrente(
    		PesquisarAvisoBancarioPorContaCorrenteHelper helper) throws ErroRepositorioException {
    	
    	List<RelatorioAvisoBancarioPorContaCorrenteBean> retorno = new ArrayList<RelatorioAvisoBancarioPorContaCorrenteBean>();
    	Session session = HibernateUtil.getSession();
    	
    	String where = "";
    	if (helper.getIdBanco() != null) {
    		where += " AND b.bnco_id = :idBanco ";
    	}
    	if (helper.getIdContaBancaria() != null) {
    		where += " AND cb.ctbc_id = :idContaBancaria ";
    	}
    	
    	String anoMes = String.valueOf(helper.getMesAno());
    	int ano = new Integer(anoMes.substring(0, 4));
    	int mes = new Integer(anoMes.substring(4, 6));
    	
    	String consulta =
    		"( " + 
    		"SELECT " + 
	    		"a.avbc_id as id_aviso, " + // 0 
	    		"a.avbc_dtrealizada as data_realizada,  " + // 1
	    		"a.arrc_id as id_arrecadador,  " + // 2
	    		"c.clie_nmcliente as descricao_arrecadador, " + // 3
	    		"a.arfm_id as id_arrecadacao_forma,  " + // 4
	    		"af.arfm_dsarrecadacaoforma as descricao_arrecadacao_forma, " + // 5
	    		"b.bnco_id as id_banco, " + // 6
	    		"b.bnco_nmbanco as descricao_banco, " + // 7
	    		"cb.ctbc_id as id_conta, " + // 8
	    		"cb.ctbc_nnconta as numero_conta, " + // 9
	    		"ag.agen_id as id_agencia, " + // 10
	    		"ag.agen_cdagencia as codigo_agencia, " + // 11
	    		"ag.agen_nmagencia as descricao_agencia, " + // 12
	    		"cb.ctbc_nncontacontabil as numero_conta_contabil, " + // 13
	    		"ag.agen_nnfone as numero_fone, " +// 14
	    		"ag.agen_nnfoneramal as numero_ramal, " + // 15
	    		"a.avbc_dtlancamento as data_lancamento,  " + // 16
	    		"a.avbc_nnsequencial as sequencial_aviso, " + // 17
	    		"a.avbc_nndocumento as numero_documento, " + // 18
	    		"( CASE WHEN a.avbc_iccreditodebito = 1 THEN a.avbc_vlrealizado ELSE 0 END ) as credito, " + // 19
	    		"( CASE WHEN a.avbc_iccreditodebito = 2 THEN a.avbc_vlrealizado ELSE 0 END ) as debito, " + // 20
	    		"a.avbc_amreferenciaarrecadacao as ano_mes_arrecadacao, " + // 21
	    		"1 " + 
	    	"FROM arrecadacao.aviso_bancario a " + 
		    	"LEFT JOIN arrecadacao.arrecadador ar ON (a.arrc_id = ar.arrc_id) " + 
		    	"LEFT JOIN cadastro.cliente c ON (ar.clie_id = c.clie_id) " + 
		    	"LEFT JOIN arrecadacao.arrecadacao_forma af ON (a.arfm_id = af.arfm_id) " + 
		    	"INNER JOIN arrecadacao.conta_bancaria cb ON (a.ctbc_id = cb.ctbc_id) " + 
		    	"INNER JOIN arrecadacao.agencia ag ON (cb.agen_id = ag.agen_id) " + 
		    	"INNER JOIN arrecadacao.banco b ON (ag.bnco_id = b.bnco_id) " + 
	    	"WHERE " + 
	    		"(a.avbc_amreferenciaarrecadacao = :anoMesReferencia OR (date_part('year', a.avbc_dtrealizada) = :ano AND date_part('month', a.avbc_dtrealizada) = :mes)) " + where + 
		    
		    ") UNION ALL ( " + 
		    
		    "SELECT " + 
				"0, " + // 0
				"ac.avac_dtacerto as data_realizada,  " + // 1
				"a.arrc_id as id_arrecadador,  " + // 2
				"c.clie_nmcliente as descricao_arrecadador,  " + // 3
				"a.arfm_id as arrecadacao_forma,  " + // 4
				"af.arfm_dsarrecadacaoforma as descricao_arrecadacao_forma,  " + // 5
	    		"b.bnco_id as id_banco, " + // 6
	    		"b.bnco_nmbanco as descricao_banco, " + // 7
	    		"cb.ctbc_id as id_conta, " + // 8
	    		"cb.ctbc_nnconta as numero_conta, " + // 9
	    		"ag.agen_id as id_agencia, " + // 10
	    		"ag.agen_cdagencia as codigo_agencia, " + // 11
	    		"ag.agen_nmagencia as descricao_agencia, " + // 12
	    		"cb.ctbc_nncontacontabil as numero_conta_contabil, " + // 13
	    		"ag.agen_nnfone as numero_fone, " +// 14
	    		"ag.agen_nnfoneramal as numero_ramal, " + // 15
				"now(), " + // 16
				"a.avbc_nnsequencial as sequencial_aviso, " + // 17
				"a.avbc_nndocumento as numero_documento, " + // 18
				"( CASE WHEN ((ac.avac_iccreditodebito = 1 AND ac.avac_icarrecadacaodevolucao = 1) OR (ac.avac_iccreditodebito = 2 AND ac.avac_icarrecadacaodevolucao = 2)) THEN ac.avac_vlacerto ELSE 0 END ) as credito, " + // 19
				"( CASE WHEN ((ac.avac_iccreditodebito = 1 AND ac.avac_icarrecadacaodevolucao = 2) OR (ac.avac_iccreditodebito = 2 AND ac.avac_icarrecadacaodevolucao = 1)) THEN ac.avac_vlacerto ELSE 0 END ) as debito, " + // 20
	    		"a.avbc_amreferenciaarrecadacao as ano_mes_arrecadacao, " +// 21
	    		"10 " + 
			"FROM arrecadacao.aviso_acertos ac " + 
				"INNER JOIN arrecadacao.aviso_bancario a ON (ac.avbc_id = a.avbc_id) " + 
				"LEFT JOIN arrecadacao.arrecadador ar ON (a.arrc_id = ar.arrc_id)  " + 
				"LEFT JOIN cadastro.cliente c ON (ar.clie_id = c.clie_id)  " + 
				"LEFT JOIN arrecadacao.arrecadacao_forma af ON (a.arfm_id = af.arfm_id)  " + 
		    	"INNER JOIN arrecadacao.conta_bancaria cb ON (a.ctbc_id = cb.ctbc_id) " + 
		    	"INNER JOIN arrecadacao.agencia ag ON (cb.agen_id = ag.agen_id) " + 
		    	"INNER JOIN arrecadacao.banco b ON (ag.bnco_id = b.bnco_id) " + 
			"WHERE " + 
				"(ac.avac_amreferenciaarrecadacao = :anoMesReferencia OR (date_part('year', ac.avac_dtacerto) = :ano AND date_part('month', ac.avac_dtacerto) = :mes)) " + where +
		    ") ORDER BY 7, 9, 2, 23";
    	
    	try {
    		SQLQuery q = session.createSQLQuery(consulta);
    		
    		q.addScalar("id_aviso", Hibernate.INTEGER);
    		q.addScalar("data_realizada", Hibernate.DATE);
    		q.addScalar("id_arrecadador", Hibernate.INTEGER);
    		q.addScalar("descricao_arrecadador", Hibernate.STRING);
    		q.addScalar("id_arrecadacao_forma", Hibernate.INTEGER);
    		q.addScalar("descricao_arrecadacao_forma", Hibernate.STRING);
    		q.addScalar("id_banco", Hibernate.INTEGER);
    		q.addScalar("descricao_banco", Hibernate.STRING);
    		q.addScalar("id_conta", Hibernate.INTEGER);
    		q.addScalar("numero_conta", Hibernate.STRING);
    		q.addScalar("id_agencia", Hibernate.INTEGER);
    		q.addScalar("codigo_agencia", Hibernate.STRING);
    		q.addScalar("descricao_agencia", Hibernate.STRING);
    		q.addScalar("numero_conta_contabil", Hibernate.INTEGER);
    		q.addScalar("numero_fone", Hibernate.STRING);
    		q.addScalar("numero_ramal", Hibernate.STRING);
    		q.addScalar("data_lancamento", Hibernate.DATE);
    		q.addScalar("sequencial_aviso", Hibernate.INTEGER);
    		q.addScalar("numero_documento", Hibernate.INTEGER);
    		q.addScalar("credito", Hibernate.BIG_DECIMAL);
    		q.addScalar("debito", Hibernate.BIG_DECIMAL);
    		q.addScalar("ano_mes_arrecadacao", Hibernate.INTEGER);
    		
    		q.setInteger("anoMesReferencia", helper.getMesAno());
    		q.setInteger("ano", ano);
    		q.setInteger("mes", mes);
        	if (helper.getIdBanco() != null) {
        		q.setInteger("idBanco", helper.getIdBanco());
        	}
        	if (helper.getIdContaBancaria() != null) {
        		q.setInteger("idContaBancaria", helper.getIdContaBancaria());
        	}
    		
    		for (Object[] linha : (List<Object[]>) q.list()) {
    			RelatorioAvisoBancarioPorContaCorrenteBean bean = new RelatorioAvisoBancarioPorContaCorrenteBean();
    			bean.setIdAviso((Integer) linha[0]);
    			bean.setDataRealizada((Date) linha[1]);
    			bean.setIdArrecadador((Integer) linha[2]);
    			bean.setDescricaoArrecadador((String) linha[3]);
    			bean.setIdArrecadacaoForma((Integer) linha[4]);
    			bean.setDescricaoArrecadacaoForma((String) linha[5]);
    			
    			bean.setIdBanco((Integer) linha[6]);
    			bean.setDescricaoBanco((String) linha[7]);
    			bean.setIdConta((Integer) linha[8]);
    			bean.setNumeroConta((String) linha[9]);
    			bean.setIdAgencia((Integer) linha[10]);
    			bean.setCodigoAgencia((String) linha[11]);
    			
    			bean.setDescricaoAgencia((String) linha[12]);
    			bean.setNumeroContaContabil((Integer) linha[13]);
    			bean.setNumeroFone((String) linha[14]);
    			bean.setNumeroRamal((String) linha[15]); 
    			
    			bean.setDataLancamento((Date) linha[16]);
    			bean.setSequencialAviso((Integer) linha[17]);
    			bean.setNumeroDocumento((Integer) linha[18]);
    			bean.setCredito((BigDecimal) linha [19]);
    			bean.setDebito((BigDecimal) linha[20]);
    			bean.setAnoMesArrecadacao((Integer) linha[21]);
    			
    			retorno.add(bean);
    		}
    		
    	} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
    }
	
}
