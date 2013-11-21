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

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.GeradorHQLCondicional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArraySet;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class RepositorioHidrometroHBM implements IRepositorioHidrometro {

	private static IRepositorioHidrometro instancia;

	/**
	 * Construtor da classe RepositorioMicromedicaoHBM
	 */
	private RepositorioHidrometroHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioHidrometro getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioHidrometroHBM();
		}

		return instancia;
	}

	public Collection pesquisarHidrometroPorHidrometroMovimentacao(Filtro filtro)
			throws ErroRepositorioException {
		// cria a coleção de retorno
		Collection retorno = null;
		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {
			// pesquisa a coleção de atividades e atribui a variável "retorno"
			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional
					.gerarCondicionalQuery(
							filtro,
							"hidrometroMovimentado",
							"from gcom.micromedicao.hidrometro.HidrometroMovimentado as hidrometroMovimentado ",
							session).list()));

			// Carrega os objetos informados no filtro
/*			if (!filtro.getColecaoCaminhosParaCarregamentoEntidades().isEmpty()) {
				PersistenciaUtil
						.processaObjetosParaCarregamento(filtro
								.getColecaoCaminhosParaCarregamentoEntidades(),
								retorno);
			}
*/		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;
	}

	/**
	 * [UC0000] - Efetuar Retirada de Hidrômetro
	 * 
	 * Pesquisa todos os campos do Hidrometro e seus relacionamentos obrigatórios.
	 * @author Thiago Tenório
	 * @date 28/09/2006
	 * 
	 * @param idHidrometro
	 * @throws ErroRepositorioException
	 */
	
	public Object[] pesquisarHidrometroPeloId(Integer idHidrometro)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;
		Object[] retornoConsulta = null;
		try {
			consulta = "select h.id, h.numero, "
				+ "h.dataAquisicao, h.anoFabricacao, "
				+ "h.indicadorMacromedidor, h.dataUltimaRevisao, "
				+ "h.dataBaixa, h.numeroLeituraAcumulada, "
				+ "h.numeroDigitosLeitura, htp.id, "
				+ "hsit.id, hmarc.id, hcap.id, hcm.id, "
				+ "hdm.id "
				+ "from Hidrometro h "
				+ "inner join h.hidrometroCapacidade hcap "
				+ "inner join h.hidrometroMarca hmarc "
				+ "inner join h.hidrometroTipo htp "
				+ "inner join h.hidrometroDiametro hdm "
				+ "inner join h.hidrometroSituacao hsit "
				+ "inner join h.hidrometroClasseMetrologica hcm "
				+ "where h.id = :idHidrometro";

			retornoConsulta = (Object[]) session.createQuery(consulta)
					.setInteger("idHidrometro", idHidrometro).setMaxResults(1)
					.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retornoConsulta;
	}

}
