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
package gcom.util.tabelaauxiliar;

import gcom.util.AtualizacaoInvalidaException;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.IRepositorioUtil;
import gcom.util.RepositorioUtilHBM;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;
import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;
import gcom.util.tabelaauxiliar.faixa.FiltroTabelaAuxiliarFaixa;
import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixa;
import gcom.util.tabelaauxiliar.tipo.FiltroTabelaAuxiliarTipo;
import gcom.util.tabelaauxiliar.tipo.TabelaAuxiliarTipo;

import java.util.Collection;
import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ControladorTabelaAuxiliarSEJB implements SessionBean {
	private static final long serialVersionUID = 1L;
	SessionContext sessionContext;

	private IRepositorioUtil repositorioUtil = null;

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {
		repositorioUtil = RepositorioUtilHBM.getInstancia();
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbPassivate() {
	}

	/**
	 * Seta o valor de sessionContext
	 * 
	 * @param sessionContext
	 *            O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param tabelaAuxiliarAbstrata
	 *            Descrição do parâmetro
	 * @throws ControladorException
	 */
	public void atualizarTabelaAuxiliar(
			TabelaAuxiliarAbstrata tabelaAuxiliarAbstrata)
			throws ControladorException {

		try {

			// -----VALIDAÇÃO DOS TIMESTAMP PARA ATUALIZAÇÃO DE CADASTRO

			// Validação para Tabela Auxiliar
			if (tabelaAuxiliarAbstrata instanceof TabelaAuxiliar) {
				// Cria o objeto
				TabelaAuxiliar tabelaAuxiliar = null;

				// Faz o casting
				tabelaAuxiliar = (TabelaAuxiliar) tabelaAuxiliarAbstrata;

				// Cria o filtro
				FiltroTabelaAuxiliar filtroTabelaAuxiliar = new FiltroTabelaAuxiliar();
				// Pega o nome do pacote do objeto
				String nomePacoteObjeto = tabelaAuxiliar.getClass().getName();

				// Seta os parametros do filtro
				filtroTabelaAuxiliar.adicionarParametro(new ParametroSimples(
						FiltroTabelaAuxiliar.ID, tabelaAuxiliar.getId()));

				// Pesquisa a coleção de acordo com o filtro passado
				Collection tabelasAuxiliares = repositorioUtil.pesquisar(
						filtroTabelaAuxiliar, nomePacoteObjeto);
				TabelaAuxiliar tabelaAuxiliarNaBase = (TabelaAuxiliar) tabelasAuxiliares
						.iterator().next();

				// Verifica se a data de alteração do objeto gravado na base é
				// maior que a na instancia
				if ((tabelaAuxiliarNaBase.getUltimaAlteracao()
						.after(tabelaAuxiliar.getUltimaAlteracao()))) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.atualizacao.timestamp");
				}
				// Faz uma referencia ao objeto
				tabelaAuxiliarAbstrata = tabelaAuxiliar;
			}

			// Validação para Tabela Auxiliar Abreviada
			if (tabelaAuxiliarAbstrata instanceof TabelaAuxiliarAbreviada) {
				// Cria o objeto
				TabelaAuxiliarAbreviada tabelaAuxiliarAbreviada = null;

				// Faz o casting
				tabelaAuxiliarAbreviada = (TabelaAuxiliarAbreviada) tabelaAuxiliarAbstrata;

				// Cria o filtro
				FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();
				// Pega o nome do pacote do objeto
				String nomePacoteObjeto = tabelaAuxiliarAbreviada.getClass()
						.getName();

				// Seta os parametros do filtro
				filtroTabelaAuxiliarAbreviada
						.adicionarParametro(new ParametroSimples(
								FiltroTabelaAuxiliarAbreviada.ID,
								tabelaAuxiliarAbreviada.getId()));

				// Pesquisa a coleção de acordo com o filtro passado
				Collection tabelasAuxiliaresAbreviadas = repositorioUtil
						.pesquisar(filtroTabelaAuxiliarAbreviada,
								nomePacoteObjeto);
				TabelaAuxiliar tabelaAuxiliarAbreviadaNaBase = (TabelaAuxiliar) tabelasAuxiliaresAbreviadas
						.iterator().next();

				// Verifica se a data de alteração do objeto gravado na base é
				// maior que a na instancia
				if ((tabelaAuxiliarAbreviadaNaBase.getUltimaAlteracao()
						.after(tabelaAuxiliarAbreviada.getUltimaAlteracao()))) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.atualizacao.timestamp");
				}
				// Faz uma referencia ao objeto
				tabelaAuxiliarAbstrata = tabelaAuxiliarAbreviada;
			}

			// Validação para Tabela Auxiliar Faixa
			if (tabelaAuxiliarAbstrata instanceof TabelaAuxiliarFaixa) {
				// Cria o objeto
				TabelaAuxiliarFaixa tabelaAuxiliarFaixa = null;

				// Faz o casting
				tabelaAuxiliarFaixa = (TabelaAuxiliarFaixa) tabelaAuxiliarAbstrata;

				// Cria o filtro
				FiltroTabelaAuxiliarFaixa filtroTabelaAuxiliarFaixa = new FiltroTabelaAuxiliarFaixa();
				// Pega o nome do pacote do objeto
				String nomePacoteObjeto = tabelaAuxiliarFaixa.getClass()
						.getName();

				// Seta os parametros do filtro
				filtroTabelaAuxiliarFaixa
						.adicionarParametro(new ParametroSimples(
								FiltroTabelaAuxiliarFaixa.ID,
								tabelaAuxiliarFaixa.getId()));

				// Pesquisa a coleção de acordo com o filtro passado
				Collection tabelasAuxiliaresFaixas = repositorioUtil.pesquisar(
						filtroTabelaAuxiliarFaixa, nomePacoteObjeto);
				TabelaAuxiliarFaixa tabelaAuxiliarFaixaNaBase = (TabelaAuxiliarFaixa) tabelasAuxiliaresFaixas
						.iterator().next();

				// Verifica se a data de alteração do objeto gravado na base é
				// maior que a na instancia
				if ((tabelaAuxiliarFaixaNaBase.getUltimaAlteracao()
						.after(tabelaAuxiliarFaixa.getUltimaAlteracao()))) {
					sessionContext.setRollbackOnly();
					throw new AtualizacaoInvalidaException();
				}
				// Faz uma referencia ao objeto
				tabelaAuxiliarAbstrata = tabelaAuxiliarFaixa;
			}

			// Validação para Tabela Auxiliar
			if (tabelaAuxiliarAbstrata instanceof TabelaAuxiliarTipo) {
				// Cria o objeto
				TabelaAuxiliarTipo tabelaAuxiliarTipo = null;

				// Faz o casting
				tabelaAuxiliarTipo = (TabelaAuxiliarTipo) tabelaAuxiliarAbstrata;

				// Cria o filtro
				FiltroTabelaAuxiliarTipo filtroTabelaAuxiliarTipo = new FiltroTabelaAuxiliarTipo();
				// Pega o nome do pacote do objeto
				String nomePacoteObjeto = tabelaAuxiliarTipo.getClass()
						.getName();

				// Seta os parametros do filtro
				filtroTabelaAuxiliarTipo
						.adicionarParametro(new ParametroSimples(
								FiltroTabelaAuxiliarTipo.ID, tabelaAuxiliarTipo
										.getId()));

				// Pesquisa a coleção de acordo com o filtro passado
				Collection tabelasAuxiliaresTipos = repositorioUtil.pesquisar(
						filtroTabelaAuxiliarTipo, nomePacoteObjeto);
				TabelaAuxiliarTipo tabelaAuxiliarTipoNaBase = (TabelaAuxiliarTipo) tabelasAuxiliaresTipos
						.iterator().next();

				// Verifica se a data de alteração do objeto gravado na base é
				// maior que a na instancia
				if ((tabelaAuxiliarTipoNaBase.getUltimaAlteracao()
						.after(tabelaAuxiliarTipo.getUltimaAlteracao()))) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.atualizacao.timestamp");
				}
				// Faz uma referencia ao objeto
				tabelaAuxiliarAbstrata = tabelaAuxiliarTipo;
			}

			// Seta a data/hora
			tabelaAuxiliarAbstrata.setUltimaAlteracao(new Date());
			// Atualiza objeto

			repositorioUtil.atualizar(tabelaAuxiliarAbstrata);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param objetoTeste
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @throws ControladorException
	 */
	public Object inserirTeste(Object objetoTeste) throws ControladorException {
		try {
			return repositorioUtil.inserir(objetoTeste);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param filtroTeste
	 *            Descrição do parâmetro
	 * @param nomePacoteObjeto
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @throws ControladorException
	 */
	public Collection pesquisarTeste(Filtro filtroTeste, String nomePacoteObjeto)
			throws ControladorException {
		try {
			return repositorioUtil.pesquisar(filtroTeste, nomePacoteObjeto);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

}
