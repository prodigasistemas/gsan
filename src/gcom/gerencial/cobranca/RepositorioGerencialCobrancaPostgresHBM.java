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

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class RepositorioGerencialCobrancaPostgresHBM extends
		RepositorioGerencialCobrancaHBM {

	/**
	 * Atualiza os dados na tabela un_resumo_indicadores_cobranca
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da Cobrança
	 * 
	 * @author Rafael Corrêa
	 * @date 25/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	@Override
	public void atualizarDadosResumoIndicadoresCobranca(
			Integer anoMesReferenciaIndicador, Integer anoMesReferenciaTabelas) throws ErroRepositorioException {
		
		Connection con = null;
		Statement stmt = null;

		Session session = HibernateUtil.getSessionGerencial();
		String consulta;

		try {

			con = session.connection();
			stmt = con.createStatement();

			consulta = "INSERT INTO cobranca.un_res_ind_cob "
					+ " select cobranca.seq_un_res_ind_cob.nextval, "
					+ " rpen_amreferencia, rpen_anoreferencia, rpen_mesreferencia, "
					+ " greg_id, uneg_id, loca_id, loca_cdelo, stcm_id, qdra_id, rota_id, rpen_cdsetorcomercial, rpen_nnquadra, "
					+ " iper_id, last_id, lest_id, catg_id, scat_id, epod_id, cltp_id, lapf_id, lepf_id, "
					+ " rpen_qtcontaspendentesma, rpen_vlpendente_contama, rpen_qtligacoes, rpen_qtligacoesativas, "
					+ " rpen_qtdocumentos, rpen_qtcontaspendentes, rpen_vlpendente_total, rpen_vlpendente_conta, rpen_vlpendente_servicos, "
					+ " rpen_vlpendente_parcelamento, rele_qtligacoesativasagua, rele_qtligacoesinativasagua, "
					+ " rele_qtligacoessuprimidas, rele_qtligacoestotaisagua, "
					+ " rear_qtcontasrecebidas, rear_vlarrecadado, rear_vlarrecacadomesatevencimento, "
					+ " rear_vlarrecacadomesaposvencimento, rear_vlarrecacado2mes, rear_vlarrecacado3mes, rear_vlarrecacadoacumuladoate3mes, "
					+ " rear_vlarrecadado_parcelamento, refa_qtcontasfaturamentoliquido, refa_qtcontasfaturamentoliquidoma, refa_vlfaturamentoliquido, "
					+ " refa_vlfaturamentoliquidoma, repa_qtparcelamentos, repa_qtcontaseguias, repa_vlnegociado, repa_vlfinanciado, repa_vlparcelado, "
					+ " rlig_qtcortes, rlig_qtsupressoes, rlig_qtreligacoes, rlig_qtreestabelecimentos,  now() "
					+ " FROM cobranca.vw_un_res_ind_cob ";

			if (anoMesReferenciaIndicador != null) {
				consulta = consulta + " WHERE rpen_amreferencia > "
						+ anoMesReferenciaIndicador + " and rpen_amreferencia <= "
						+ anoMesReferenciaTabelas;
			} else {
				consulta = consulta + " WHERE rpen_amreferencia <= "
						+ anoMesReferenciaTabelas;
			}

//			consulta += "\n limit 1";
			stmt.executeUpdate(consulta);

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexões");
			}
		}

	}
	
}


