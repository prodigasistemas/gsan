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
 * Rômulo Aurélio de Melo Souza Filho
 * Rosana Carvalho Barbosa
 * Sávio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 * Yara Taciane de Souza
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

import java.util.Collection;

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;



/**
 * Classe criada para sobrescrever(override) os metodos no padrão da base de dados Postgres
 * 
 * @author Arthur Carvalho
 * @date 16/12/2010
 */
public class RepositorioMicromedicaoPostgresHBM extends RepositorioMicromedicaoHBM {

	
	/**
	 * 
 	 * [UC0629] Consultar Arquivo Texto para Leitura
 	 * 	
 	 * 	[FS0011 - Verificar Leituras];
 	 * 
	 * @author Hugo Amorim
	 * @date 20/08/2010
	 */
	public Collection pesquisarSituacaoLeitura(Integer anoMes,Integer idGrupo,Integer idRota)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;
		try {
			consulta = "SELECT \n"
				+" COALESCE(medidosEnviados.qt_medidos_enviados,0) as qt_medidos_enviados, \n"
				+" COALESCE(medidosRecebidos.qt_medidos_recebidos,0) as qt_medidos_recebidos, \n"
				+" ABS(COALESCE(medidosEnviados.qt_medidos_enviados,0) - COALESCE(medidosRecebidos.qt_medidos_recebidos,0)) as diferencaMedidos, \n"
				+" COALESCE(naoMedidosEnviados.qt_nao_medidos_enviados,0) as qt_nao_medidos_enviados, \n"
				+" COALESCE(naoMedidosRecebidos.qt_nao_medidos_recebidos,0) as qt_nao_medidos_recebidos, \n"
				+" ABS(COALESCE(naoMedidosEnviados.qt_nao_medidos_enviados,0) - COALESCE(naoMedidosRecebidos.qt_nao_medidos_recebidos,0)) as diferencaNaoMedidos, \n"
				+" COALESCE(medidosImpressos.qt_medidos_impressos,0) as qt_medidos_impressos, \n"
				+" COALESCE(medidosNaoImpressos.qt_medidos_nao_impressos,0) as qt_medidos_nao_impressos, \n"
				+" COALESCE(naoMedidosImpressos.qt_nao_medidos_impressos,0) as qt_nao_medidos_impressos, \n"
				+" COALESCE(naoMedidosNaoImpressos.qt_nao_medidos_nao_impressos,0) as qt_nao_medidos_nao_impressos, \n"				
				+" atre.txre_dsmotivofinalizacao as motivoFinalizacao, \n"
				+" COALESCE(anormalidades.qt_anormalidades,0) as qt_anormalidades \n"
				+" from micromedicao.arquivo_texto_rot_empr atre \n"
				+" inner join micromedicao.rota rt on (atre.rota_id = rt.rota_id) \n"
				//qt_medidos_enviados
				+" LEFT JOIN ( \n"
				+" SELECT r.rota_id as rota,mre.mrem_ammovimento as anomes, count(distinct(i.imov_id)) as qt_medidos_enviados \n"
				+" FROM micromedicao.movimento_roteiro_empr mre \n"
				+" INNER JOIN cadastro.imovel i  on i.imov_id = mre.imov_id \n"
				+" INNER JOIN cadastro.quadra q on q.qdra_id = i.qdra_id \n"
				+" INNER JOIN micromedicao.rota r on r.rota_id = q.rota_id \n"
				+" LEFT JOIN  atendimentopublico.ligacao_agua la on la.lagu_id = mre.imov_id \n"
				+" WHERE (la.hidi_id is not null or i.hidi_id is not null) and mre.mrem_ammovimento = :anoMes and mre.rota_id = :idRota " +
				" GROUP BY rota,anomes) medidosEnviados \n"
				+" on (atre.rota_id = medidosEnviados.rota and atre.txre_amreferencia = medidosEnviados.anomes) \n"
				//qt_medidos_recebidos
				+" LEFT JOIN ( \n"
				+" SELECT \n"
				+"   mov.rota_id as rota, \n"
				+"   mcpf_ammovimento as anomes, \n"
				+"   count(distinct(mov.imov_id)) as qt_medidos_recebidos \n"
				+" FROM \n"
				+"   faturamento.mov_conta_prefaturada mov \n"
				+"   inner join cadastro.imovel imo on ( mov.imov_id = imo.imov_id ) \n"
				+"   left join atendimentopublico.ligacao_agua la on ( imo.imov_id = la.lagu_id ) \n"
				+" where \n"
				+"   imo.hidi_id is not null or la.hidi_id is not null \n"
				+" GROUP BY rota,anomes ) medidosRecebidos \n" 
				+" on (atre.rota_id = medidosRecebidos.rota and atre.txre_amreferencia = medidosRecebidos.anomes) \n"
				//qt_nao_medidos_enviados
				+" LEFT JOIN ( \n"
				+" SELECT r.rota_id as rota,mre.mrem_ammovimento as anomes, count(distinct(i.imov_id)) as qt_nao_medidos_enviados \n" 
				+" FROM micromedicao.movimento_roteiro_empr mre \n"
				+" INNER JOIN cadastro.imovel i  on i.imov_id = mre.imov_id \n"
				+" INNER JOIN cadastro.quadra q on q.qdra_id = i.qdra_id \n"
				+" INNER JOIN micromedicao.rota r on r.rota_id = q.rota_id \n"
				+" LEFT JOIN  atendimentopublico.ligacao_agua la on la.lagu_id = mre.imov_id \n"
				+" WHERE (la.hidi_id is null and i.hidi_id is null) and mre.mrem_ammovimento = :anoMes and mre.rota_id = :idRota " 
				+" GROUP BY rota,anomes) naoMedidosEnviados \n"
				+" on (atre.rota_id = naoMedidosEnviados.rota and atre.txre_amreferencia = naoMedidosEnviados.anomes) \n"
				//qt_nao_medidos_recebidos
				+" LEFT JOIN ( \n"
				+" SELECT mcp.rota_id as rota, mcp.mcpf_ammovimento as anomes, count(distinct(mcp.imov_id)) as qt_nao_medidos_recebidos \n"
				+" FROM faturamento.mov_conta_prefaturada mcp \n"
				+" INNER JOIN cadastro.imovel i  on i.imov_id = mcp.imov_id \n"
				+" LEFT JOIN  atendimentopublico.ligacao_agua la on la.lagu_id = mcp.imov_id \n"
				+" WHERE (la.hidi_id is null and i.hidi_id is null) \n"
				+" group by rota,anomes) naoMedidosRecebidos \n"
				+" on (atre.rota_id = naoMedidosRecebidos.rota and atre.txre_amreferencia = naoMedidosRecebidos.anomes) \n"
				//qt_medidos_impressos
				+" LEFT JOIN( \n"
				+" SELECT rota_id as rota, mcp.mcpf_ammovimento as anomes, count(distinct(mcp.imov_id)) as qt_medidos_impressos \n"
				+" FROM faturamento.mov_conta_prefaturada mcp \n"
				+" INNER JOIN cadastro.imovel imo  on imo.imov_id = mcp.imov_id \n"
				+" left join atendimentopublico.ligacao_agua la on ( imo.imov_id = la.lagu_id ) \n"
				+" where \n"
				+"   ( imo.hidi_id is not null or la.hidi_id is not null ) and \n"
				+"     mcp.mcpf_icemissaoconta = 1 \n"
				+" group by rota,anomes) medidosImpressos on (atre.rota_id = medidosImpressos.rota and atre.txre_amreferencia = medidosImpressos.anomes) \n"
				// qt_medidos_nao_impressos
				+" LEFT JOIN( \n"
				+" SELECT rota_id as rota, mcp.mcpf_ammovimento as anomes, count(distinct(mcp.imov_id)) as qt_medidos_nao_impressos \n"
				+" FROM faturamento.mov_conta_prefaturada mcp \n"
				+" INNER JOIN cadastro.imovel imo  on imo.imov_id = mcp.imov_id \n"
				+" left join atendimentopublico.ligacao_agua la on ( imo.imov_id = la.lagu_id ) \n"
				+" where \n"
				+"   ( imo.hidi_id is not null or la.hidi_id is not null ) and \n"
				+"     mcp.mcpf_icemissaoconta = 2 \n"
				+" group by rota,anomes) medidosNaoImpressos on (atre.rota_id = medidosNaoImpressos.rota and atre.txre_amreferencia = medidosNaoImpressos.anomes) \n"				
				//qt_nao_medidos_impressos
				+" LEFT JOIN( \n"
				+" SELECT rota_id as rota, mcp.mcpf_ammovimento as anomes, count(distinct(mcp.imov_id)) as qt_nao_medidos_impressos \n"
				+" FROM faturamento.mov_conta_prefaturada mcp \n"
				+" INNER JOIN cadastro.imovel i  on i.imov_id = mcp.imov_id \n"
				+" LEFT JOIN  atendimentopublico.ligacao_agua la on la.lagu_id = mcp.imov_id \n"
				+" WHERE (la.hidi_id is null and i.hidi_id is null) and \n"
				+" mcp.mcpf_icemissaoconta = 1 and (mcpf_nnleiturahidrometro is null or mcp.ltan_id is null)  \n"
				+" group by rota,anomes) naoMedidosImpressos on (atre.rota_id = naoMedidosImpressos.rota and atre.txre_amreferencia = naoMedidosImpressos.anomes) \n"
				//qt_nao_medidos_nao_impressos
				+" LEFT JOIN( \n"
				+" SELECT rota_id as rota, mcp.mcpf_ammovimento as anomes, count(distinct(mcp.imov_id)) as qt_nao_medidos_nao_impressos \n"
				+" FROM faturamento.mov_conta_prefaturada mcp \n"
				+" INNER JOIN cadastro.imovel i  on i.imov_id = mcp.imov_id \n"
				+" LEFT JOIN  atendimentopublico.ligacao_agua la on la.lagu_id = mcp.imov_id \n"
				+" WHERE (la.hidi_id is null and i.hidi_id is null) and \n"
				+" mcp.mcpf_icemissaoconta = 2 and (mcpf_nnleiturahidrometro is null or mcp.ltan_id is null)   \n"
				+" group by rota,anomes) naoMedidosNaoImpressos on (atre.rota_id = naoMedidosNaoImpressos.rota and atre.txre_amreferencia = naoMedidosNaoImpressos.anomes) \n"				
				// qt_anormalidades
				+" LEFT JOIN( \n"
				+" SELECT rota_id as rota, mcp.mcpf_ammovimento as anomes, count(distinct(imov_id)) as qt_anormalidades \n"
				+" FROM faturamento.mov_conta_prefaturada mcp where  \n"
				+" csan_id is not null  \n"
				+" group by rota,anomes) anormalidades on (anormalidades.rota = medidosNaoImpressos.rota and anormalidades.anomes = medidosNaoImpressos.anomes) \n"
				

				+" WHERE txre_amreferencia = :anoMes and rt.rota_id = :idRota ";
			
			retorno = session.createSQLQuery(consulta)
				.addScalar("qt_medidos_enviados", Hibernate.INTEGER)
				.addScalar("qt_medidos_recebidos", Hibernate.INTEGER)
				.addScalar("diferencaMedidos", Hibernate.INTEGER)
				.addScalar("qt_nao_medidos_enviados", Hibernate.INTEGER)
				.addScalar("qt_nao_medidos_recebidos", Hibernate.INTEGER)
				.addScalar("diferencaNaoMedidos", Hibernate.INTEGER)
				.addScalar("qt_medidos_impressos", Hibernate.INTEGER)
				.addScalar("qt_medidos_nao_impressos", Hibernate.INTEGER)
				.addScalar("qt_nao_medidos_impressos", Hibernate.INTEGER)
				.addScalar("qt_nao_medidos_nao_impressos", Hibernate.INTEGER)								
				.addScalar("motivoFinalizacao", Hibernate.STRING)
				.addScalar("qt_anormalidades", Hibernate.INTEGER)
				.setInteger("anoMes", anoMes)
				.setInteger("idRota", idRota)
				.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	
}
