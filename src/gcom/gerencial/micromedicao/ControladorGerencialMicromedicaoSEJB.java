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
package gcom.gerencial.micromedicao;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gerencial.cadastro.ControladorGerencialCadastroLocal;
import gcom.gerencial.cadastro.ControladorGerencialCadastroLocalHome;
import gcom.gerencial.cadastro.GEmpresa;
import gcom.gerencial.cadastro.IRepositorioGerencialCadastro;
import gcom.gerencial.cadastro.RepositorioGerencialCadastroHBM;
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.geografico.GMicrorregiao;
import gcom.gerencial.cadastro.geografico.GMunicipio;
import gcom.gerencial.cadastro.geografico.GRegiao;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import gcom.gerencial.cadastro.localidade.GGerenciaRegional;
import gcom.gerencial.cadastro.localidade.GLocalidade;
import gcom.gerencial.cadastro.localidade.GQuadra;
import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import gcom.gerencial.cobranca.IRepositorioGerencialCobranca;
import gcom.gerencial.cobranca.RepositorioGerencialCobrancaHBM;
import gcom.gerencial.faturamento.GConsumoTarifa;
import gcom.gerencial.faturamento.GFaturamentoGrupo;
import gcom.gerencial.micromedicao.bean.ResumoAnormalidadeConsumoHelper;
import gcom.gerencial.micromedicao.bean.ResumoAnormalidadeHelper;
import gcom.gerencial.micromedicao.bean.ResumoInstalacaoHidrometroHelper;
import gcom.gerencial.micromedicao.bean.ResumoInstalacaoHidrometroPorAnoHelper;
import gcom.gerencial.micromedicao.bean.UnResumoLeituraAnormalidadeHelper;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroCapacidade;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroClasseMetrologica;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroDiametro;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroLocalArmazenagem;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroMarca;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroMotivoBaixa;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroSituacao;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroTipo;
import gcom.gerencial.micromedicao.leitura.GLeituraSituacao;
import gcom.gerencial.micromedicao.medicao.GMedicaoTipo;
import gcom.gerencial.operacional.GDistritoOperacional;
import gcom.micromedicao.ResumoAnormalidadeLeitura;
import gcom.micromedicao.Rota;
import gcom.micromedicao.bean.ResumoHidrometroHelper;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.consumo.ResumoAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.relatorio.gerencial.micromedicao.FiltrarRelatorioResumoDistritoOperacionalHelper;
import gcom.relatorio.gerencial.micromedicao.RelatorioResumoDistritoOperacionalHelper;
import gcom.relatorio.gerencial.micromedicao.RelatorioResumoZonaAbastecimentoHelper;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * 
 * 
 * @author Thiago Toscano
 * @created 19/04/2006
 */
public class ControladorGerencialMicromedicaoSEJB implements SessionBean {
	private static final long serialVersionUID = 1L;

	private IRepositorioGerencialMicromedicao repositorioGerencial = null;

	// private IRepositorioUtil repositorioUtil = null;

	private IRepositorioGerencialMicromedicao repositorioGerencialMicromedicao = null;

	private IRepositorioGerencialCobranca repositorioGerencialCobranca = null;

	private IRepositorioGerencialCadastro repositorioGerencialCadastro = null;

	SessionContext sessionContext;

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {
		// repositorioUtil = RepositorioUtilHBM.getInstancia();
		repositorioGerencial = RepositorioGerencialMicromedicaoHBM
				.getInstancia();
		repositorioGerencialMicromedicao = RepositorioGerencialMicromedicaoHBM
				.getInstancia();
		repositorioGerencialCobranca = RepositorioGerencialCobrancaHBM
				.getInstancia();
		repositorioGerencialCadastro = RepositorioGerencialCadastroHBM
				.getInstancia();
	}

	/**
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	/**
	 * Retorna a interface remota de ControladorImovel
	 * 
	 * @return A interface remota do controlador de parâmetro
	 */
	private ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	/*
	private ControladorGerencialImovelLocal getControladorGerencialImovel() {
		ControladorGerencialImovelLocalHome localHome = null;
		ControladorGerencialImovelLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorGerencialImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_IMOVEL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	*/

	/**
	 * Retorna a interface remota de ControladorImovel
	 * 
	 * @return A interface remota do controlador de parâmetro
	 */
	/*
	 * private ControladorGerencialCobrancaLocal
	 * getControladorGerencialCobranca() { ControladorGerencialCobrancaLocalHome
	 * localHome = null; ControladorGerencialCobrancaLocal local = null; // pega
	 * a instância do ServiceLocator.
	 * 
	 * ServiceLocator locator = null;
	 * 
	 * try { locator = ServiceLocator.getInstancia();
	 * 
	 * localHome = (ControladorGerencialCobrancaLocalHome) locator
	 * .getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_COBRANCA_SEJB); //
	 * guarda a referencia de um objeto capaz de fazer chamadas à // objetos
	 * remotamente local = localHome.create();
	 * 
	 * return local; } catch (CreateException e) { throw new
	 * SistemaException(e); } catch (ServiceLocatorException e) { throw new
	 * SistemaException(e); } }
	 */

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

	private ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorBatchLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	/**
	 * 
	 * Método que consulta os ResumoAnormalidadeHelper
	 * 
	 * @author Flávio Cordeiro
	 * @date 17/05/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void gerarResumoAnormalidadeLeitura(int idLocalidade,
			int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.LOCALIDADE, (idLocalidade));

		// Logger logger = LogUtil.inicializarLogger("GerarResumoAtividade",
		// this.getClass());

		try {

			// filtro usado para pesquisar o sistema parametro e pegar o anoMes
			// de referencia do registro retornado
			FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

			List<ResumoAnormalidadeHelper> listaSimplificada = new ArrayList<ResumoAnormalidadeHelper>();
			// faz a pesquisa em sistema parametros
			Collection<SistemaParametro> colecaoSistemaParametro = getControladorUtil()
					.pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());

			SistemaParametro sistemaParametro = colecaoSistemaParametro.iterator().next();

			// seleciona as medicoes apartir da tabela medicao historico como
			// descrito no caso de uso
			// [UC0343] Gerar Resumo de Anormalidades
			List<Object[]> colecaoRetorno = this.repositorioGerencial
					.getResumoAnormalidadeHelper(sistemaParametro.getAnoMesFaturamento().toString(), idLocalidade);
			// logger.debug("colecaoRetorno: "+colecaoRetorno.size());

			// objetos para auxiliar a montagem do resumoAnormalidade, serao
			// usada para pegar os valores retornados
			// pela consulta acima e setalos no objeto resumoAnormalidadeHelper
			ResumoAnormalidadeHelper resumoAnormalidadeHelper = null;

			Object[] objeto = null;
			Object[] objectImovel = null;
			List<ResumoAnormalidadeHelper> listResumoAnormalidade = new ArrayList<ResumoAnormalidadeHelper>();

			Iterator<Object[]> iteratorColecaotRetorno = colecaoRetorno.iterator();

			// intera a colecao retornada pela pesquisa e monta objetos
			// resumoAnormalidadeHelper
			// e os coloca em uma colecao para serem tratados e inseridos no
			// banco nos fluxos abaixo

			while (iteratorColecaotRetorno.hasNext()) {
				resumoAnormalidadeHelper = new ResumoAnormalidadeHelper();

				objeto = iteratorColecaotRetorno.next();

				// for (int i = 0; i < objeto.length; i++) {

				// se o objeto tiver o id de imovel entra nesse trecho e ja
				// monta o objeto direto so pelo resultado retornado do banco
				if (objeto[0] != null) {
					resumoAnormalidadeHelper
							.setIdImovel(objeto[0] != null ? (Integer) objeto[0]
									: null);
					resumoAnormalidadeHelper
							.setIdLigacaoAgua(objeto[1] != null ? (Integer) objeto[1]
									: null);
					resumoAnormalidadeHelper
							.setIdLeituraAnormalidadeFaturada(objeto[2] != null ? (Integer) objeto[2]
									: null);
					resumoAnormalidadeHelper
							.setIdGerenciaRegional(objeto[3] != null ? (Integer) objeto[3]
									: null);
					resumoAnormalidadeHelper
							.setIdLocalidade(objeto[4] != null ? (Integer) objeto[4]
									: null);
					resumoAnormalidadeHelper
							.setIdSetorComercial(objeto[5] != null ? (Integer) objeto[5]
									: null);
					resumoAnormalidadeHelper
							.setCodigoSetorComercial(objeto[6] != null ? (Integer) objeto[6]
									: null);
					resumoAnormalidadeHelper
							.setIdQuadra(objeto[7] != null ? (Integer) objeto[7]
									: null);
					resumoAnormalidadeHelper
							.setNumeroQuadra(objeto[8] != null ? (Integer) objeto[8]
									: null);
					resumoAnormalidadeHelper
							.setIdRota(objeto[9] != null ? (Integer) objeto[9]
									: null);
					resumoAnormalidadeHelper
							.setIdPerfilImovel(objeto[10] != null ? (Integer) objeto[10]
									: null);
					resumoAnormalidadeHelper
							.setIdSituacaoLigacaoAgua(objeto[11] != null ? (Integer) objeto[11]
									: null);
					resumoAnormalidadeHelper
							.setIdSituacaoLigacaoEsgoto(objeto[12] != null ? (Integer) objeto[12]
									: null);
					resumoAnormalidadeHelper
							.setIdEsfera(objeto[13] != null ? (Integer) objeto[13]
									: null);
					resumoAnormalidadeHelper
							.setIdMedicaoTipo(objeto[14] != null ? (Integer) objeto[14]
									: null);
					resumoAnormalidadeHelper
							.setDescricaoLeituraAnormalidadeFaturada(objeto[15] != null ? (String) objeto[15]
									: null);

					// se o id imovel for nulo é pq ele tem o id ligacaoAguao
					// para esta situacao ele ira fazer uma pesquisa atraves da
					// tabela imovel
					// usando o id ligacao Agua como id imovel trazendo assim o
					// imovel e suas ligacoes
					// as quais sao necessarias para o preenchimento do objeto
					// resumoAnormalidadeLeitura
				} else if (objeto[1] != null) {
					objectImovel = this.repositorioGerencial
							.pesquisarImovelPorIdLigacaoAgua((Integer) objeto[1]);

					resumoAnormalidadeHelper
							.setIdImovel(objectImovel[0] != null ? (Integer) objectImovel[0]
									: null);
					resumoAnormalidadeHelper
							.setIdGerenciaRegional(objectImovel[1] != null ? (Integer) objectImovel[1]
									: null);
					resumoAnormalidadeHelper
							.setIdLocalidade(objectImovel[2] != null ? (Integer) objectImovel[2]
									: null);
					resumoAnormalidadeHelper
							.setIdSetorComercial(objectImovel[3] != null ? (Integer) objectImovel[3]
									: null);
					resumoAnormalidadeHelper
							.setCodigoSetorComercial(objectImovel[4] != null ? (Integer) objectImovel[4]
									: null);
					resumoAnormalidadeHelper
							.setIdQuadra(objectImovel[5] != null ? (Integer) objectImovel[5]
									: null);
					resumoAnormalidadeHelper
							.setNumeroQuadra(objectImovel[6] != null ? (Integer) objectImovel[6]
									: null);
					resumoAnormalidadeHelper
							.setIdRota(objectImovel[7] != null ? (Integer) objectImovel[7]
									: null);
					resumoAnormalidadeHelper
							.setIdPerfilImovel(objectImovel[8] != null ? (Integer) objectImovel[8]
									: null);
					resumoAnormalidadeHelper
							.setIdEsfera(objectImovel[9] != null ? (Integer) objectImovel[9]
									: null);

					resumoAnormalidadeHelper
							.setIdLigacaoAgua(objeto[1] != null ? (Integer) objeto[1]
									: null);
					resumoAnormalidadeHelper
							.setIdLeituraAnormalidadeFaturada(objeto[2] != null ? (Integer) objeto[2]
									: null);
					resumoAnormalidadeHelper
							.setIdSituacaoLigacaoAgua(objectImovel[10] != null ? (Integer) objectImovel[10]
									: null);
					resumoAnormalidadeHelper
							.setIdSituacaoLigacaoEsgoto(objectImovel[11] != null ? (Integer) objectImovel[11]
									: null);
					resumoAnormalidadeHelper
							.setIdMedicaoTipo(objeto[14] != null ? (Integer) objeto[14]
									: null);
					resumoAnormalidadeHelper
							.setDescricaoLeituraAnormalidadeFaturada(objeto[15] != null ? (String) objeto[15]
									: null);

				}
				listResumoAnormalidade.add(resumoAnormalidadeHelper);
				// }
			}

			// organiza a listaResumoAnormalidade gerando uma outra lista
			// esta nova lista q sera simplificada ja estara organizada e com
			// o atributo de qtd de imoveis preenchidos para cada objeto

			// logger.debug("listResumoAnormalidade:
			// "+listResumoAnormalidade.size());
			for (int i = 0; i < listResumoAnormalidade.size(); i++) {
				Object obj = listResumoAnormalidade.get(i);

				if (obj instanceof ResumoAnormalidadeHelper) {
					ResumoAnormalidadeHelper irleh = (ResumoAnormalidadeHelper) obj;
					Integer idImovel = irleh.getIdImovel();

					// pesquisando a categoria
					// [UC0306] - Obtter principal categoria do imóvel
					Categoria categoria = null;
					categoria = this.getControladorImovel()
							.obterPrincipalCategoriaImovel(idImovel);

					if (categoria != null)
						irleh.setIdCategoria(categoria.getId());

					// se ja existe um objeto igual a ele entao soma as ligacoes
					// e as economias no ja existente
					// um objeto eh igual ao outro se ele tem todos as
					// informacos iguals ( excecao idImovel, quantidadeEconomia,
					// quantidadeLigacoes)
					if (listaSimplificada.contains(irleh)) {
						int posicao = listaSimplificada.indexOf(irleh);
						ResumoAnormalidadeHelper jaCadastrado =listaSimplificada.get(posicao);
						jaCadastrado.setQuantidadeImovel(jaCadastrado
								.getQuantidadeImovel() + 1);
					} else {
						listaSimplificada.add(irleh);
					}
				}
			}
			ResumoAnormalidadeLeitura resumoAnormalidadeLeitura = null;
			// ResumoAnormalidadeHelper resumoAnormalidadeHelperInserir = null;

			// -----Objetos pra auxilio na inserção de ResumoAnormalidadeLeitura
			GerenciaRegional gerenciaRegional = null;
			Localidade localidade = null;
			SetorComercial setorComercial = null;
			Quadra quadra = null;
			Rota rota = null;
			ImovelPerfil imovelPerfil = null;
			LigacaoAguaSituacao ligacaoAguaSituacao = null;
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
			Categoria categoria = null;
			MedicaoTipo medicaoTipo = null;
			LeituraAnormalidade leituraAnormalidade = null;
			Imovel imovel = null;
			EsferaPoder esferaPoder = null;

			Iterator<ResumoAnormalidadeHelper> iteratorListaSimplificada = listaSimplificada.iterator();
			while (iteratorListaSimplificada.hasNext()) {
				resumoAnormalidadeLeitura = new ResumoAnormalidadeLeitura();
				resumoAnormalidadeHelper = iteratorListaSimplificada.next();

				// testa os valores q estão preenchidos no objeto
				// resumoAnormalidadeHelper,
				// caso o atributo não esteja vazio e seja um id, monta o objeto
				// correspondente ao id
				// e seta o id neste objeto. ex: se o id de localidade
				// preenchido em resumoAnormalidadeHelper,
				// monta o objeto localidade e seta o id contido no
				// resumoAnoramlidadeHelper na localidade

				gerenciaRegional = null;
				if (resumoAnormalidadeHelper.getIdGerenciaRegional() != null) {
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(resumoAnormalidadeHelper
							.getIdGerenciaRegional());
				}

				localidade = null;
				if (resumoAnormalidadeHelper.getIdLocalidade() != null) {
					localidade = new Localidade();
					localidade
							.setId(resumoAnormalidadeHelper.getIdLocalidade());
				}

				setorComercial = null;
				if (resumoAnormalidadeHelper.getIdSetorComercial() != null) {
					setorComercial = new SetorComercial();
					setorComercial.setId(resumoAnormalidadeHelper
							.getIdSetorComercial());
				}

				quadra = null;
				if (resumoAnormalidadeHelper.getIdQuadra() != null) {
					quadra = new Quadra();
					quadra.setId(resumoAnormalidadeHelper.getIdQuadra());
				}

				rota = null;
				if (resumoAnormalidadeHelper.getIdRota() != null) {
					rota = new Rota();
					rota.setId(resumoAnormalidadeHelper.getIdRota());
				}

				imovelPerfil = null;
				if (resumoAnormalidadeHelper.getIdImovel() != null) {
					imovelPerfil = new ImovelPerfil();
					imovelPerfil.setId(resumoAnormalidadeHelper
							.getIdPerfilImovel());
				}

				ligacaoAguaSituacao = null;
				if (resumoAnormalidadeHelper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new LigacaoAguaSituacao();
					ligacaoAguaSituacao.setId(resumoAnormalidadeHelper
							.getIdSituacaoLigacaoAgua());
				}

				ligacaoEsgotoSituacao = null;
				if (resumoAnormalidadeHelper.getIdSituacaoLigacaoEsgoto() != null) {
					ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(resumoAnormalidadeHelper
							.getIdSituacaoLigacaoEsgoto());
				}

				categoria = null;
				if (resumoAnormalidadeHelper.getIdCategoria() != null) {
					categoria = new Categoria();
					categoria.setId(resumoAnormalidadeHelper.getIdCategoria());
				}

				medicaoTipo = null;
				if (resumoAnormalidadeHelper.getIdMedicaoTipo() != null) {
					medicaoTipo = new MedicaoTipo();
					medicaoTipo.setId(resumoAnormalidadeHelper
							.getIdMedicaoTipo());
				}

				leituraAnormalidade = null;
				if (resumoAnormalidadeHelper.getIdLeituraAnormalidadeFaturada() != null) {
					leituraAnormalidade = new LeituraAnormalidade();
					leituraAnormalidade.setId(resumoAnormalidadeHelper
							.getIdLeituraAnormalidadeFaturada());
					leituraAnormalidade.setDescricao(resumoAnormalidadeHelper
							.getDescricaoLeituraAnormalidadeFaturada());
				}

				imovel = null;
				if (resumoAnormalidadeHelper.getIdImovel() != null) {
					imovel = new Imovel();
					imovel.setId(resumoAnormalidadeHelper.getIdImovel());
				}

				esferaPoder = null;
				if (resumoAnormalidadeHelper.getIdEsfera() != null) {
					esferaPoder = new EsferaPoder();
					esferaPoder.setId(resumoAnormalidadeHelper.getIdEsfera());
				}

				// atribui os valores no objeto resumo anormalidade
				resumoAnormalidadeLeitura.setAnoMesReferencia(sistemaParametro
						.getAnoMesFaturamento());
				resumoAnormalidadeLeitura.setGerenciaRegional(gerenciaRegional);
				resumoAnormalidadeLeitura.setLocalidade(localidade);
				resumoAnormalidadeLeitura.setSetorComercial(setorComercial);
				resumoAnormalidadeLeitura.setQuadra(quadra);
				resumoAnormalidadeLeitura.setRota(rota);
				resumoAnormalidadeLeitura.setImovelPerfil(imovelPerfil);
				resumoAnormalidadeLeitura
						.setLigacaoAguaSituacao(ligacaoAguaSituacao);
				resumoAnormalidadeLeitura
						.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
				resumoAnormalidadeLeitura.setCategoria(categoria);
				resumoAnormalidadeLeitura.setMedicaoTipo(medicaoTipo);
				resumoAnormalidadeLeitura
						.setLeituraAnormalidade(leituraAnormalidade);
				resumoAnormalidadeLeitura
						.setQuantidadeMedicao(resumoAnormalidadeHelper
								.getQuantidadeImovel());
				resumoAnormalidadeLeitura.setEsferaPoder(esferaPoder);
				resumoAnormalidadeLeitura
						.setNumeroQuadra(resumoAnormalidadeHelper
								.getNumeroQuadra());
				resumoAnormalidadeLeitura
						.setCodigoSetorComercial(resumoAnormalidadeHelper
								.getCodigoSetorComercial());
				resumoAnormalidadeLeitura.setUltimaAlteracao(new Date());

				// inseri o objeto na base atraves do metodo inserir do Util
				getControladorUtil().inserir(resumoAnormalidadeLeitura);
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {
			// Este catch serve para interceptar qualquer exceção que o processo
			// batch venha a lançar e garantir que a unidade de processamento do
			// batch será atualizada com o erro ocorrido

			e.printStackTrace();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);

			sessionContext.setRollbackOnly();

			throw new EJBException(e);
		}

	}

	/**
	 * 
	 * Método que consulta os ResumoAnormalidadeConsumoHelper
	 * 
	 * @author Flávio Cordeiro
	 * @date 21/05/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void gerarResumoAnormalidadeConsumo() throws ControladorException {
		try {
			List<ResumoAnormalidadeConsumoHelper> listaSimplificada = new ArrayList<ResumoAnormalidadeConsumoHelper>();
			// List<ResumoAnormalidadeConsumo> listaResumoLigacoesEconomia = new
			// ArrayList();

			FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

			Collection<SistemaParametro> colecaoSistemaParametro = getControladorUtil()
					.pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());

			SistemaParametro sistemaParametro =colecaoSistemaParametro.iterator().next();

			List<Object[]> imoveisResumoAnormalidadeConsumo = this.repositorioGerencial
					.getResumoAnormalidadeConsumoHelper(sistemaParametro
							.getAnoMesFaturamento().toString());
			// pra cada objeto obter a categoria e o indicador de existência de
			// hidrômetro
			// caso ja tenha um igual soma a quantidade de economias e a
			// quantidade de ligacoes
			for (int i = 0; i < imoveisResumoAnormalidadeConsumo.size(); i++) {
				Object[] retorno = imoveisResumoAnormalidadeConsumo.get(i);

				// if (imoveisResumoLigacaoEconomias != null &&
				// imoveisResumoLigacaoEconomias.get(0) != null) {
				// Object obj = imoveisResumoLigacaoEconomias.get(0);

				// if (obj instanceof ResumoAnormalidadeConsumo) {

				ResumoAnormalidadeConsumoHelper irleh = new ResumoAnormalidadeConsumoHelper(
						(Integer) retorno[0], (Integer) retorno[1],
						(Integer) retorno[2], (Integer) retorno[3],
						(Integer) retorno[4], (Integer) retorno[5],
						(Integer) retorno[6], (Integer) retorno[7],
						(Integer) retorno[8], (Integer) retorno[9],
						(Integer) retorno[10], (Integer) retorno[11],
						(Integer) retorno[12], (Integer) retorno[13]);

				Integer idImovel = irleh.getIdImovel();

				// pesquisando a categoria
				// [UC0306] - Obtter principal categoria do imóvel
				Categoria categoria = null;
				categoria = this.getControladorImovel()
						.obterPrincipalCategoriaImovel(idImovel);

				if (categoria != null)
					irleh.setIdCategoria(categoria.getId());

				// se ja existe um objeto igual a ele entao soma as ligacoes e
				// as economias no ja existente
				// um objeto eh igual ao outro se ele tem todos as informacos
				// iguals ( excecao idImovel, quantidadeEconomia,
				// quantidadeLigacoes)
				if (listaSimplificada.contains(irleh)) {
					int posicao = listaSimplificada.indexOf(irleh);
					ResumoAnormalidadeConsumoHelper jaCadastrado =listaSimplificada.get(posicao);
					jaCadastrado.setQuantidadeImovel(jaCadastrado
							.getQuantidadeImovel() + 1);
				} else {
					listaSimplificada.add(irleh);
				}
			}
			// }

			// organiza a lista gerando uma outra lista
			// esta nova lista q sera simplificada ja estara organizada e com
			// o atributo de qtd de imoveis preenchidos para cada objeto
			for (int i = 0; i < listaSimplificada.size(); i++) {
				ResumoAnormalidadeConsumoHelper irleh =listaSimplificada.get(i);

				Integer anoMesReferencia = sistemaParametro
						.getAnoMesFaturamento();

				Integer codigoSetorComercial = null;
				if (irleh.getCodigoSetorComercial() != null) {
					codigoSetorComercial = (irleh.getCodigoSetorComercial());
				}

				Integer numeroQuadra = null;
				if (irleh.getNumeroQuadra() != null) {
					numeroQuadra = (irleh.getNumeroQuadra());
				}

				int quantidadeImovel = (irleh.getQuantidadeImovel());

				GerenciaRegional gerenciaRegional = null;
				if (irleh.getIdGerenciaRegional() != null) {
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(irleh.getIdGerenciaRegional());
				}

				Localidade localidade = null;
				if (irleh.getIdLocalidade() != null) {
					localidade = new Localidade();
					localidade.setId(irleh.getIdLocalidade());
				}

				SetorComercial setorComercial = null;
				if (irleh.getIdSetorComercial() != null) {
					setorComercial = new SetorComercial();
					setorComercial.setId(irleh.getIdSetorComercial());
				}

				Rota rota = null;
				if (irleh.getIdRota() != null) {
					rota = new Rota();
					rota.setId(irleh.getIdRota());
				}

				Quadra quadra = null;
				if (irleh.getIdQuadra() != null) {
					quadra = new Quadra();
					quadra.setId(irleh.getIdQuadra());
				}

				ImovelPerfil imovelPerfil = null;
				if (irleh.getIdPerfilImovel() != null) {
					imovelPerfil = new ImovelPerfil();
					imovelPerfil.setId(irleh.getIdPerfilImovel());
				}

				LigacaoAguaSituacao ligacaoAguaSituacao = null;
				if (irleh.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new LigacaoAguaSituacao();
					ligacaoAguaSituacao.setId(irleh.getIdSituacaoLigacaoAgua());
				}

				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
				if (irleh.getIdSituacaoLigacaoEsgoto() != null) {
					ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(irleh
							.getIdSituacaoLigacaoEsgoto());
				}

				Categoria categoria = null;
				if (irleh.getIdCategoria() != null) {
					categoria = new Categoria();
					categoria.setId(irleh.getIdCategoria());
				}

				EsferaPoder esferaPoder = null;
				if (irleh.getIdEsfera() != null) {
					esferaPoder = new EsferaPoder();
					esferaPoder.setId(irleh.getIdEsfera());
				}

				ConsumoAnormalidade consumoAnormalidade = null;
				if (irleh.getConsumoAnormalidade() != null) {
					consumoAnormalidade = new ConsumoAnormalidade();
					consumoAnormalidade.setId(irleh.getConsumoAnormalidade());
				}

				LigacaoTipo ligacaoTipo = null;
				if (irleh.getIdLigacaoTipo() != null) {
					ligacaoTipo = new LigacaoTipo();
					ligacaoTipo.setId(irleh.getIdLigacaoTipo());
				}

				ResumoAnormalidadeConsumo resumoAnormalidadeConsumo = new ResumoAnormalidadeConsumo(
						anoMesReferencia, codigoSetorComercial, numeroQuadra,
						quantidadeImovel, gerenciaRegional, localidade,
						setorComercial, rota, quadra, imovelPerfil,
						ligacaoAguaSituacao, ligacaoEsgotoSituacao, categoria,
						esferaPoder, consumoAnormalidade, ligacaoTipo);

				resumoAnormalidadeConsumo.setUltimaAlteracao(new Date());

				getControladorUtil().inserir(resumoAnormalidadeConsumo);
			}

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0344] Consultar Resumo Anormalidade
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoAnormalidadeAgua(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException {
		try {
			// [FS0001] Verificar existência de dados para o ano/mês de
			// referência retornado
			String resumo = ResumoAnormalidadeLeitura.class.getName();
			Integer countResumoPendencia = repositorioGerencialCobranca
					.verificarExistenciaAnoMesReferenciaResumo(
							informarDadosGeracaoRelatorioConsultaHelper
									.getAnoMesReferencia(), resumo);

			if (countResumoPendencia == null || countResumoPendencia == 0) {
				throw new ControladorException(
						"atencao.pesquisa.nenhumresultado");
			}

			List retorno = this.repositorioGerencialMicromedicao
					.consultarResumoAnormalidadeAgua(informarDadosGeracaoRelatorioConsultaHelper);

			// [FS0007] Nenhum registro encontrado
			if (retorno == null || retorno.isEmpty()) {
				throw new ControladorException(
						"atencao.pesquisa.nenhumresultado");
			}

			return retorno;
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0344] Consultar Resumo Anormalidade
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoAnormalidadePoco(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException {
		try {
			// [FS0001] Verificar existência de dados para o ano/mês de
			// referência retornado
			String resumo = ResumoAnormalidadeLeitura.class.getName();
			Integer countResumoPendencia = repositorioGerencialCobranca
					.verificarExistenciaAnoMesReferenciaResumo(
							informarDadosGeracaoRelatorioConsultaHelper
									.getAnoMesReferencia(), resumo);

			if (countResumoPendencia == null || countResumoPendencia == 0) {
				throw new ControladorException(
						"atencao.nao_existe_resumo_pendencia",
						null,
						Util
								.formatarAnoMesParaMesAno(informarDadosGeracaoRelatorioConsultaHelper
										.getAnoMesReferencia()));
			}

			List retorno = this.repositorioGerencialMicromedicao
					.consultarResumoAnormalidadePoco(informarDadosGeracaoRelatorioConsultaHelper);

			return retorno;
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0344] Consultar Resumo Anormalidade Consumo
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoAnormalidadeConsumo(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException {
		try {
			// [FS0001] Verificar existência de dados para o ano/mês de
			// referência retornado
			String resumo = ResumoAnormalidadeConsumo.class.getName();
			Integer countResumoPendencia = repositorioGerencialCobranca
					.verificarExistenciaAnoMesReferenciaResumo(
							informarDadosGeracaoRelatorioConsultaHelper
									.getAnoMesReferencia(), resumo);

			if (countResumoPendencia == null || countResumoPendencia == 0) {
				throw new ControladorException(
						"atencao.nao_existe_resumo_pendencia",
						null,
						Util
								.formatarAnoMesParaMesAno(informarDadosGeracaoRelatorioConsultaHelper										.getAnoMesReferencia()));
			}

			List retorno = this.repositorioGerencialMicromedicao
					.consultarResumoAnormalidadeConsumo(informarDadosGeracaoRelatorioConsultaHelper);

			// [FS0007] Nenhum registro encontrado
			if (retorno == null || retorno.isEmpty()) {
				throw new ControladorException(
						"atencao.pesquisa.nenhumresultado");
			}

			return retorno;
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Gera o resumo das ligações de hidrômetro.
	 * 
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 * 
	 * @author Pedro Alexandre
	 * @date 24/04/2007
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idSetorComercial
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoInstalacoesHidrometros(
			Integer anoMesReferenciaFaturamento, Integer idSetorComercial,
			int idFuncionalidadeIniciada) throws ControladorException {

		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		int idUnidadeIniciada = 0;

		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.SETOR_COMERCIAL, idSetorComercial);

		try {

			System.out.println("INICIO SETOR COMERCIAL " + idSetorComercial);

			
			//FS0001 - Verificar existencia de dados para o ano/mes referencia informado
	   		getControladorGerencialCadastro().excluirResumoGerencial(
	   				getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
	   				UnResumoInstalacaoHidrometro.class.getName(), "referencia", idSetorComercial );				
			
			//Collection colecaoResumoInstalacaoHidrometroInserir = new ArrayList();
			Collection<UnResumoInstalacaoHidrometro> colecaoResumoInstalacaoHidrometro = null;
			
			// [FS0001 - Verificar existência de dados para o ano/mês de
			// referência da arrecadação]
			//boolean flagExisteDados = repositorioGerencialMicromedicao.verificarExistenciaResumoInstalacaoHidrometroParaAnoMesReferenciaArrecadacao(anoMesReferenciaFaturamento, idSetorComercial);

			/* caso já exista resumo de instalação para o ano mês de referência */
			//if (flagExisteDados) {
			//	throw new EJBException();
			//}

			/*
			 * pesquisa os dados do setor comercial 
			 * 
			 * 0 - ID Gerencia Regional 
			 * 1 - ID Unidade Negócio 
			 * 2 - ID Elo 
			 * 3 - ID Localidade 
			 * 4 - ID Municipio
			 * 5 - ID Microrregião 
			 * 6 - ID Região
			 * 7 - Codigo setor comercial
			 */
			Object[] dadosSetorComercial = repositorioGerencialMicromedicao.pesquisarDadosSetorComercial(idSetorComercial);

			/** Resumo das instalações de hidrômetros pela REGIONAL */
			GGerenciaRegional gGerenciaRegional = new GGerenciaRegional();
			gGerenciaRegional.setId((Integer) dadosSetorComercial[0]);

			GUnidadeNegocio gUnidadeNegocio = new GUnidadeNegocio();
			gUnidadeNegocio.setId((Integer) dadosSetorComercial[1]);
			gUnidadeNegocio.setGerGerenciaRegional(gGerenciaRegional);

			GLocalidade gElo = new GLocalidade();
			gElo.setId((Integer) dadosSetorComercial[2]);
			gElo.setGerUnidadeNegocio(gUnidadeNegocio);

			GLocalidade gLocalidade = new GLocalidade();
			gLocalidade.setId((Integer) dadosSetorComercial[3]);
			gLocalidade.setGerLocalidade(gElo);
			/** fim regional */

			/** Resumo das instalações de hidrômetros pela REGIAO */
			GMunicipio gMunicipio = new GMunicipio();
			gMunicipio.setId((Integer) dadosSetorComercial[4]);

			GMicrorregiao gMicrorregiao = new GMicrorregiao();
			gMicrorregiao.setId((Integer) dadosSetorComercial[5]);
			gMunicipio.setGerMicrorregiao(gMicrorregiao);

			GRegiao gRegiao = new GRegiao();
			gRegiao.setId((Integer) dadosSetorComercial[6]);
			gMicrorregiao.setGerRegiao(gRegiao);
			
			Integer codigoSetorComercial = (Integer) dadosSetorComercial[7];
			
			/** fim região */

			/** monta o setor comercial */
			GSetorComercial gSetorComercial = new GSetorComercial();
			gSetorComercial.setId(idSetorComercial);
			gSetorComercial.setGerLocalidade(gLocalidade);
			gSetorComercial.setCodigo(codigoSetorComercial);
			// gSetorComercial.setGERMunicipio(gMunicipio);// CORRIGI O
			// MAPEAMENTO
			// DE GSetorComercial

			Integer ano = new Integer ((anoMesReferenciaFaturamento + "").substring(0,4));
			Integer mes = new Integer ((anoMesReferenciaFaturamento + "").substring(4,6));
			Calendar dataInicio = new GregorianCalendar();
			dataInicio.set(Calendar.YEAR,ano);
			dataInicio.set(Calendar.MONTH, mes - 1);
			dataInicio.set(Calendar.DATE, 1);
			dataInicio.set(Calendar.HOUR, 0);
			dataInicio.set(Calendar.MINUTE, 0);
			dataInicio.set(Calendar.SECOND, 0);
			dataInicio.set(Calendar.MILLISECOND, 000);
			
			Calendar dataFim = new GregorianCalendar();
			dataFim.set(Calendar.YEAR,ano);
			dataFim.set(Calendar.MONTH, mes - 1);
			dataFim.set(Calendar.DATE, 31);
			dataFim.set(Calendar.HOUR, 23);
			dataFim.set(Calendar.MINUTE, 59);
			dataFim.set(Calendar.SECOND, 59);
			dataFim.set(Calendar.MILLISECOND, 999);
			
			/**
			 * pesquisa os ids das quadras pertencentes ao setor comercial
			 * informado
			 */

			Collection<ResumoInstalacaoHidrometroHelper> colecaoResumoInstalacaoHidrometroHelper = gerarColecaoResumoInstalacaoHidrometroHelperPorQuadra(
					gSetorComercial, dataInicio.getTime(), dataFim.getTime(), anoMesReferenciaFaturamento);

			if (colecaoResumoInstalacaoHidrometroHelper != null	&& !colecaoResumoInstalacaoHidrometroHelper.isEmpty()) {
				// [SB0001A] - Gerar Resumo das Instalações de
				// hidrômetros Regional
				colecaoResumoInstalacaoHidrometro  = gerarResumoInstalacoesHidrometrosRegional(gSetorComercial,
						colecaoResumoInstalacaoHidrometroHelper,
						anoMesReferenciaFaturamento);

				getControladorBatch().inserirColecaoObjetoParaBatchGerencial(colecaoResumoInstalacaoHidrometro);
				
				//colecaoResumoInstalacaoHidrometroInserir.addAll(colecaoResumoInstalacaoHidrometro);
				// [SB0001B] - Gerar Resumo das Instalações de
				// hidrômetros Região
				// gerarResumoInstalacoesHidrometrosRegiao(gQuadra,
				// colecaoResumoInstalacaoHidrometroHelper,
				// anoMesReferenciaFaturamento);
			}
					
			System.out.println("FIM SETOR COMERCIAL " + idSetorComercial);

			/** fechar unidade de processamento iniciada com sucesso */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);

		} catch (Exception e) {

			/** fechar unidade de processamento iniciada com erro */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,idUnidadeIniciada, true);

			throw new EJBException(e);
		}
	}

	/**
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 * 
	 * [SB0001A] - Acumular total das instalações de hidrômetros Regional
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * @author Pedro Alexandre, Ivan Sérgio
	 * @date 24/04/2007, 03/12/2008 
	 * @alteracao 03/12/2008 - CRC556 - Adicionado os campos: idConsumoTarifa, idMarcaHidrometro, idTipoHidrometro,
     *						   			idCapacidadeHidrometro, idDiametroHidrometro, idClasseHidrometro
	 * 
	 * @param gSetorComercial
	 * @param colecaoResumoInstalacaoHidrometroHelper
	 * @param anoMesReferenciaFaturamento
	 * 
	 * @throws ControladorException
	 */
	private Collection<UnResumoInstalacaoHidrometro> gerarResumoInstalacoesHidrometrosRegional(
			GSetorComercial gSetorComercial,
			Collection<ResumoInstalacaoHidrometroHelper> colecaoResumoInstalacaoHidrometroHelper,
			Integer anoMesReferenciaFaturamento)
			throws ControladorException {

		Collection<UnResumoInstalacaoHidrometro> colecaoInserirResumoInstalacaoHidrometro = new ArrayList<UnResumoInstalacaoHidrometro>();

		for (ResumoInstalacaoHidrometroHelper resumoInstalacaoHidrometroHelper : colecaoResumoInstalacaoHidrometroHelper) {

			//GSetorComercial gSetorComercial = gQuadra.getGerSetorComercial();
			GLocalidade gLocalidade = gSetorComercial.getGerLocalidade();
			GLocalidade gElo = gLocalidade.getGerLocalidade();
			GUnidadeNegocio gUnidadeNegocio = gElo.getGerUnidadeNegocio();
			GGerenciaRegional gGerenciaRegional = gUnidadeNegocio.getGerGerenciaRegional();
			//GRota gRota = gQuadra.getGerRota();

			int qtdHidrometrosInstaladosRamal = resumoInstalacaoHidrometroHelper.getQtdHidrometroInstaladoRamal();
			int qtdHidrometrosSubstituidosRamal = resumoInstalacaoHidrometroHelper.getQtdHidrometroSubstituidoRamal();
			int qtdHidrometrosRemanejadosRamal = resumoInstalacaoHidrometroHelper.getQtdHidrometroRemanejadosRamal();
			int qtdHidrometrosRetiradosRamal = resumoInstalacaoHidrometroHelper.getQtdHidrometroRetiradoRamal();

			int qtdHidrometrosInstaladosPoco = resumoInstalacaoHidrometroHelper.getQtdHidrometroInstaladoPoco();
			int qtdHidrometrosSubstituidosPoco = resumoInstalacaoHidrometroHelper.getQtdHidrometroSubstituidoPoco();
			int qtdHidrometrosRemanejadosPoco = resumoInstalacaoHidrometroHelper.getQtdHidrometroRemanejadosPoco();
			int qtdHidrometrosRetiradosPoco = resumoInstalacaoHidrometroHelper.getQtdHidrometroRetiradoPoco();
			int qtdHidrometroAtualmenteInstaladosRamal = resumoInstalacaoHidrometroHelper.getQtdHidrometroAtualmenteInstaladosRamal();
			int qtdHidrometroAtualmenteInstaladosPoco = resumoInstalacaoHidrometroHelper.getQtdHidrometroAtualmenteInstaladosPoco();

			Integer qtdHidrometrosDadosAtualizados = 0;

			GQuadra gQuadra = null;
			Integer idGQuadra = resumoInstalacaoHidrometroHelper.getIdQuadra();
			Integer numeroGQuadra = resumoInstalacaoHidrometroHelper.getNumeroQuadra();
			
			if (idGQuadra != null) {
				gQuadra = new GQuadra();
				gQuadra.setId(idGQuadra);
				gQuadra.setNumeroQuadra(numeroGQuadra);
			}
			
			GRota gRota = null;
			Integer idGRota = resumoInstalacaoHidrometroHelper.getIdRota();
			
			if (idGRota != null) {
				gRota = new GRota();
				gRota.setId(idGRota);
			}
			
			GSubcategoria gSubcategoria = null;
			Integer idGSubcategoria = resumoInstalacaoHidrometroHelper
					.getIdPrincipalSubCategoria();

			if (idGSubcategoria != null) {
				gSubcategoria = new GSubcategoria();
				gSubcategoria.setId(idGSubcategoria);
			}

			GClienteTipo gClienteTipo = null;
			Integer idGClienteTipo = resumoInstalacaoHidrometroHelper
					.getIdTipoClienteResponsavel();

			if (idGClienteTipo != null) {
				gClienteTipo = new GClienteTipo();
				gClienteTipo.setId(idGClienteTipo);
			}

			GLigacaoAguaSituacao gLigacaoAguaSituacao = null;
			Integer idGLigacaoAguaSituacao = resumoInstalacaoHidrometroHelper
					.getIdLigacaoAguaSituacao();

			if (idGLigacaoAguaSituacao != null) {
				gLigacaoAguaSituacao = new GLigacaoAguaSituacao();
				gLigacaoAguaSituacao.setId(idGLigacaoAguaSituacao);
			}

			GLigacaoAguaPerfil gLigacaoAguaPerfil = null;
			Integer idGLigacaoAguaPerfil = resumoInstalacaoHidrometroHelper
					.getIdPerfilLigacaoAgua();

			if (idGLigacaoAguaPerfil != null) {
				gLigacaoAguaPerfil = new GLigacaoAguaPerfil();
				gLigacaoAguaPerfil.setId(idGLigacaoAguaPerfil);
			}

			GLigacaoEsgotoSituacao gLigacaoEsgotoSituacao = null;
			Integer idGLigacaoEsgotoSituacao = resumoInstalacaoHidrometroHelper
					.getIdLigacaoEsgotoSituacao();

			if (idGLigacaoEsgotoSituacao != null) {
				gLigacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
				gLigacaoEsgotoSituacao.setId(idGLigacaoEsgotoSituacao);
			}

			GLigacaoEsgotoPerfil gLigacaoEsgotoPerfil = null;
			Integer idGLigacaoEsgotoPerfil = resumoInstalacaoHidrometroHelper
					.getIdPerfilLigacaoEsgoto();

			if (idGLigacaoEsgotoPerfil != null) {
				gLigacaoEsgotoPerfil = new GLigacaoEsgotoPerfil();
				gLigacaoEsgotoPerfil.setId(idGLigacaoEsgotoPerfil);
			}

			GEsferaPoder gEsferaPoder = null;
			Integer idGEsferaPoder = resumoInstalacaoHidrometroHelper
					.getIdEsferaPoder();

			if (idGEsferaPoder != null) {
				gEsferaPoder = new GEsferaPoder();
				gEsferaPoder.setId(idGEsferaPoder);
			}

			GCategoria gCategoria = null;
			Integer idGCategoria = resumoInstalacaoHidrometroHelper
					.getIdPrincipalCategoria();

			if (idGCategoria != null) {
				gCategoria = new GCategoria();
				gCategoria.setId(idGCategoria);
			}

			GImovelPerfil gImovelPerfil = null;
			Integer idGImovelPerfil = resumoInstalacaoHidrometroHelper
					.getIdPerfilImovel();

			if (idGImovelPerfil != null) {
				gImovelPerfil = new GImovelPerfil();
				gImovelPerfil.setId(idGImovelPerfil);
			}
			
			//************************************************************
			// CRC556 - Recuperando os novos campos
			//************************************************************
			GConsumoTarifa gConsumoTarifa = null;
			Integer idConsumoTarifa = resumoInstalacaoHidrometroHelper.getIdConsumoTarifa();
			
			if (idConsumoTarifa != null) {
				gConsumoTarifa = new GConsumoTarifa();
				gConsumoTarifa.setId(idConsumoTarifa);
			}
			
			GHidrometroMarca gHidrometroMarca = null;
			Integer idMarcaHidrometro = resumoInstalacaoHidrometroHelper.getIdMarcaHidrometro();
			
			if (idMarcaHidrometro != null) {
				gHidrometroMarca = new GHidrometroMarca();
				gHidrometroMarca.setId(idMarcaHidrometro);
			}
			
			GHidrometroTipo gHidrometroTipo = null;
			Integer idTipoHidrometro = resumoInstalacaoHidrometroHelper.getIdTipoHidrometro();
			
			if (idTipoHidrometro != null) {
				gHidrometroTipo = new GHidrometroTipo();
				gHidrometroTipo.setId(idTipoHidrometro);
			}
			
			GHidrometroCapacidade gHidrometroCapacidade = null;
			Integer idCapacidadeHidrometro = resumoInstalacaoHidrometroHelper.getIdCapacidadeHidrometro();
			
			if (idCapacidadeHidrometro != null) {
				gHidrometroCapacidade = new GHidrometroCapacidade();
				gHidrometroCapacidade.setId(idCapacidadeHidrometro);
			}
			
			GHidrometroDiametro gHidrometroDiametro = null;
			Integer idDiametroHidrometro = resumoInstalacaoHidrometroHelper.getIdDiametroHidrometro();
			
			if (idDiametroHidrometro != null) {
				gHidrometroDiametro = new GHidrometroDiametro();
				gHidrometroDiametro.setId(idDiametroHidrometro);
			}
			
			GHidrometroClasseMetrologica gHidrometroClasse = null;
			Integer idClasseHidrometro = resumoInstalacaoHidrometroHelper.getIdClasseHidrometro();
			
			if (idClasseHidrometro != null) {
				gHidrometroClasse = new GHidrometroClasseMetrologica();
				gHidrometroClasse.setId(idClasseHidrometro);
			}

			UnResumoInstalacaoHidrometro resumoInstalacaoHidrometro = new UnResumoInstalacaoHidrometro(
					anoMesReferenciaFaturamento, gSetorComercial.getCodigo(),
					gQuadra.getNumeroQuadra(), qtdHidrometrosInstaladosRamal,
					qtdHidrometrosSubstituidosRamal, new Date(),
					qtdHidrometrosRemanejadosRamal,
					qtdHidrometrosRetiradosRamal,
					qtdHidrometrosDadosAtualizados, gSubcategoria,
					gClienteTipo, gLigacaoAguaSituacao, gUnidadeNegocio,
					gLocalidade, gElo, gQuadra, gLigacaoEsgotoSituacao,
					gLigacaoEsgotoPerfil, gGerenciaRegional, gSetorComercial,
					gLigacaoAguaPerfil, gEsferaPoder, gCategoria,
					gImovelPerfil, gRota, qtdHidrometrosInstaladosPoco,
					qtdHidrometrosSubstituidosPoco,
					qtdHidrometrosRemanejadosPoco, qtdHidrometrosRetiradosPoco, 
					qtdHidrometroAtualmenteInstaladosRamal, 
					qtdHidrometroAtualmenteInstaladosPoco,
					resumoInstalacaoHidrometroHelper.getCodigoRota());
			
			//************************************************************
			// CRC556 - Adicinando os novos campos
			//************************************************************
			resumoInstalacaoHidrometro.setGerConsumoTarifa(gConsumoTarifa);
			resumoInstalacaoHidrometro.setGerHidrometroMarca(gHidrometroMarca);
			resumoInstalacaoHidrometro.setGerHidrometroTipo(gHidrometroTipo);
			resumoInstalacaoHidrometro.setGerHidrometroCapacidade(gHidrometroCapacidade);
			resumoInstalacaoHidrometro.setGerHidrometroDiametro(gHidrometroDiametro);
			resumoInstalacaoHidrometro.setGerHidrometroClasseMetrologica(gHidrometroClasse);

			colecaoInserirResumoInstalacaoHidrometro.add(resumoInstalacaoHidrometro);
			
			resumoInstalacaoHidrometroHelper = null;
		}

		return colecaoInserirResumoInstalacaoHidrometro;
	}

	/**
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * @author Pedro Alexandre
	 * @date 24/04/2007
	 * 
	 * @param setorComercial
	 * @param colecaoIdsQuadras
	 * @throws ErroRepositorioException
	 * @throws ControladorException
	 */
	/*
	private void gerarResumoInstalacoesHidrometrosRegiao(
			GQuadra gQuadra,
			Collection<ResumoInstalacaoHidrometroHelper> colecaoResumoInstalacaoHidrometroHelper,
			Integer anoMesReferenciaFaturamento)
			throws ErroRepositorioException, ControladorException {

	}
	*/

	/**
	 * [UC0564] Gerar Resumo das Instalações de Hidrômetros
	 * 
	 * @author Pedro Alexandre, Pedro Alexandre, Ivan Sérgio
	 * @date 27/04/2007, 26/07/2007, 03/12/2008
	 * @alteracao 03/12/2008 - CRC556 - Adicionado os campos: idConsumoTarifa, idMarcaHidrometro, idTipoHidrometro,
     *						   			idCapacidadeHidrometro, idDiametroHidrometro, idClasseHidrometro
	 * 
	 * @param gSetorComercial
	 * @param dataInicio
	 * @param dataFim
	 * @param anoMesReferenciaFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 * @throws ControladorException
	 */
	private Collection<ResumoInstalacaoHidrometroHelper> gerarColecaoResumoInstalacaoHidrometroHelperPorQuadra(
			GSetorComercial gSetorComercial, Date dataInicio, Date dataFim, Integer anoMesReferenciaFaturamento)
			throws ErroRepositorioException, ControladorException {

		Collection<Object[]> colecaoDadosImoveis = this.repositorioGerencialMicromedicao.pesquisarDadosImovelResumoInstalacaoHidrometroPorQuadra(gSetorComercial.getId(), dataInicio, dataFim);

		List<ResumoInstalacaoHidrometroHelper> colecaoRetorno = new ArrayList<ResumoInstalacaoHidrometroHelper>();

		if (colecaoDadosImoveis != null && !colecaoDadosImoveis.isEmpty()) {

			for (Object[] dadosImovel : colecaoDadosImoveis) {

				int qtdHidrometroInstaladoPoco = 0;
				int qtdHidrometroInstaladoRamal = 0;
				int qtdHidrometroRetiradoPoco = 0;
				int qtdHidrometroRetiradoRamal = 0;
				int qtdHidrometroSubstituidoPoco = 0;
				int qtdHidrometroSubstituidoRamal = 0;
				int qtdHidrometroRemanejadoPoco = 0;
				int qtdHidrometroRemanejadoRamal = 0;
				int qtdHidrometroAtualmenteInstaladosRamal = 0;
				int qtdHidrometroAtualmenteInstaladosPoco = 0;
				
				/*
				 * Dados do imóvel :
				 * 
				 * 0 - ID IMOVEL 
				 * 1 - ID PERFIL DO IMOVEL 
				 * 2 - ID SITUACAO DA LIGACAO AGUA 
				 * 3 - ID SITUACAO DA LIGACAO ESGOTO 
				 * 4 - ID PERFIL LIGACAO AGUA 
				 * 5 - ID PERFIL LIGACAO ESGOTO
				 * 6 - ID QUADRA
				 * 7 - NUMERO DA QUADRA
				 * 8 - ID DA ROTA
				 * 9 - INDICADOR SE EXISTE HIDROMETRO INSTALADO RAMAL
				 * 10 - INDICADOR SE EXISTE HIDROMETRO INSTALADO POCO 
				 * 11 - ID DO IMOVEL DO HIDROMETRO
				 * 12 - ID DA LIGACAO DE AGUA DO HIDROMETRO(LAGU_ID)
				 * 13 - INDICADOR DA INSTALACAO OU SUBSTITUICAO
				 * 14 - ANO/MES DE REFERENCIA DA INSTALACAO DO HIDROMETRO
				 * 15 - ANO/MES DE REFERENCIA DA RETIRADA DO HIDROMETRO
				 * 16 - ID DO HIDROMETRO DO IMOVEL
				 * 17 - ID DO HIDROMETRO DA LIGACAO DE AGUA
				 */
				Integer idImovel = (Integer) dadosImovel[0];
				Integer idPerfilImovel = (Integer) dadosImovel[1];
				Integer idLigacaoAguaSituacao = (Integer) dadosImovel[2];
				Integer idLigacaoEsgotoSituacao = (Integer) dadosImovel[3];
				Integer idPerfilLigacaoAgua = (Integer) dadosImovel[4];
				Integer idPerfilLigacaoEsgoto = (Integer) dadosImovel[5];
				Integer idQuadra = (Integer) dadosImovel[6];
				Integer numeroQuadra = (Integer) dadosImovel[7];
				Integer idRota = (Integer) dadosImovel[8];
				Integer flagRamal  = (Integer) dadosImovel[9];
				Integer flagPoco  = (Integer) dadosImovel[10];
				
				
				Integer idImovelHidrometro = (Integer) dadosImovel[11];
				Integer idLigacaoAguaHidrometro = (Integer) dadosImovel[12];
				Short indicadorInstalacaoSubstituicao = (Short) dadosImovel[13];
				String anoMesInstalacao = (String) dadosImovel[14];
				String anoMesRetirada = (String) dadosImovel[15];
				
				Integer idHidrometroImovel = (Integer) dadosImovel[16];
				Integer idHidrometroLigacaoAgua = (Integer) dadosImovel[17];
				Short   codigoRota = (Short) dadosImovel[18];
				
				Integer idConsumoTarifa = (Integer) dadosImovel[19];
				Integer idMarcaHidrometro = (Integer) dadosImovel[20];
				Integer idTipoHidrometro = (Integer) dadosImovel[21];
			    Integer idCapacidadeHidrometro = (Integer) dadosImovel[22];
			    Integer idDiametroHidrometro = (Integer) dadosImovel[23];
			    Integer idClasseHidrometro = (Integer) dadosImovel[24];
			    
				
				if (idPerfilLigacaoAgua == null) {
					idPerfilLigacaoAgua = 0;
				}

				if (idPerfilLigacaoEsgoto == null) {
					idPerfilLigacaoEsgoto = 0;
				}

				System.out.println("Imovel " + idImovel);
						
				if (anoMesInstalacao.equals(anoMesReferenciaFaturamento+ "")) {
					
					if (idImovelHidrometro != null) {
						
						if(indicadorInstalacaoSubstituicao != null){
							if(indicadorInstalacaoSubstituicao.equals(ConstantesSistema.SIM)){
								qtdHidrometroInstaladoPoco++;
							}
								
							if(indicadorInstalacaoSubstituicao.equals(ConstantesSistema.NAO)){
								qtdHidrometroSubstituidoPoco++;
							}
						}
					}

					if (idLigacaoAguaHidrometro != null) {
						
						if(indicadorInstalacaoSubstituicao != null){
						
							if(indicadorInstalacaoSubstituicao.equals(ConstantesSistema.SIM)){
								qtdHidrometroInstaladoRamal++;
							}
							if(indicadorInstalacaoSubstituicao.equals(ConstantesSistema.NAO)){
								qtdHidrometroSubstituidoRamal++;
							}
						}
					}
				}
						
						
				if(anoMesRetirada != null){
					
					if (anoMesRetirada.equals(anoMesReferenciaFaturamento + "")) {
						if(idImovelHidrometro != null){
							if(idHidrometroImovel == null){
								qtdHidrometroRetiradoPoco++;
							}
						}
						
						if(idLigacaoAguaHidrometro != null){
							if(idHidrometroLigacaoAgua == null){
								qtdHidrometroRetiradoRamal++;
							}
						}
					}
				}
						
				// [UC0306] - Obter principal categoria do imóvel
				Categoria categoria = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);
				Integer idPrincipalCategoria = null;
				if (categoria != null) {
					idPrincipalCategoria = categoria.getId();
				}

				Integer idPrincipalSubCategoria = null;

				if (idPrincipalCategoria != null) {

					// Pesquisando a principal subcategoria
					ImovelSubcategoria imovelSubcategoria = this.getControladorImovel().obterPrincipalSubcategoria(categoria.getId(),idImovel);

					if (imovelSubcategoria != null) {
						idPrincipalSubCategoria = imovelSubcategoria.getComp_id().getSubcategoria().getId();
					}
				}

				/*
				 * Dados do cliente responsável do imóvel informado 0 - ID
				 * DA ESFERA DE PODER DO TIPO DO CLIENTE RESPONSÁVEL 1 - ID
				 * DO TIPO DO CLIENTE RESPONSÁVEL
				 */
				Object[] dadosClienteResponsavel = this.repositorioGerencialMicromedicao.pesquisarDadosClienteResponsavelPorImovel(idImovel);

				Integer idEsferaPoder = 0;
				Integer idTipoClienteResponsavel = 0;

				if (dadosClienteResponsavel != null) {

					idEsferaPoder = (Integer) dadosClienteResponsavel[0];

					if (idEsferaPoder == null) {
						idEsferaPoder = 0;
					}

					idTipoClienteResponsavel = (Integer) dadosClienteResponsavel[1];

					if (idTipoClienteResponsavel == null) {
						idTipoClienteResponsavel = 0;
					}
				}

				//caso exista hidrometro atualmente instalado ramal
				if(flagRamal.intValue() == ConstantesSistema.SIM.intValue()){
					qtdHidrometroAtualmenteInstaladosRamal = 1;
				} else{
					qtdHidrometroAtualmenteInstaladosRamal = 0;
				}

				//caso exista hidrometro atualmente instalado poço
				if(flagPoco.intValue() == ConstantesSistema.SIM.intValue()){
					qtdHidrometroAtualmenteInstaladosPoco = 1;
				} else{
					qtdHidrometroAtualmenteInstaladosPoco = 0;
				}

				ResumoInstalacaoHidrometroHelper resumoInstalacaoHidrometroHelper = new ResumoInstalacaoHidrometroHelper(
						idQuadra,
						idPrincipalCategoria, 
						idPrincipalSubCategoria,
						idEsferaPoder, idTipoClienteResponsavel,
						idPerfilLigacaoAgua, idPerfilLigacaoEsgoto,
						qtdHidrometroInstaladoPoco,
						qtdHidrometroInstaladoRamal,
						qtdHidrometroRetiradoPoco,
						qtdHidrometroRetiradoRamal,
						qtdHidrometroSubstituidoPoco,
						qtdHidrometroSubstituidoRamal,
						qtdHidrometroRemanejadoPoco,
						qtdHidrometroRemanejadoRamal,
						idLigacaoAguaSituacao, idLigacaoEsgotoSituacao,
						idPerfilImovel,
						qtdHidrometroAtualmenteInstaladosRamal,
						qtdHidrometroAtualmenteInstaladosPoco,
						numeroQuadra,
						idRota,
						codigoRota);
				
				// CRC556 - Adiciona os novos campos
				resumoInstalacaoHidrometroHelper.setIdConsumoTarifa(idConsumoTarifa);
				resumoInstalacaoHidrometroHelper.setIdMarcaHidrometro(idMarcaHidrometro);
				resumoInstalacaoHidrometroHelper.setIdTipoHidrometro(idTipoHidrometro);
				resumoInstalacaoHidrometroHelper.setIdCapacidadeHidrometro(idCapacidadeHidrometro);
				resumoInstalacaoHidrometroHelper.setIdDiametroHidrometro(idDiametroHidrometro);
				resumoInstalacaoHidrometroHelper.setIdClasseHidrometro(idClasseHidrometro);

				if (colecaoRetorno.contains(resumoInstalacaoHidrometroHelper)) {

					int posicao = colecaoRetorno.indexOf(resumoInstalacaoHidrometroHelper);

					ResumoInstalacaoHidrometroHelper jaCadastrado = colecaoRetorno.get(posicao);

					jaCadastrado.setQtdHidrometroInstaladoPoco(jaCadastrado.getQtdHidrometroInstaladoPoco()+ qtdHidrometroInstaladoPoco);
					jaCadastrado.setQtdHidrometroInstaladoRamal(jaCadastrado.getQtdHidrometroInstaladoRamal() + qtdHidrometroInstaladoRamal);
					jaCadastrado.setQtdHidrometroSubstituidoPoco(jaCadastrado.getQtdHidrometroSubstituidoPoco() + qtdHidrometroSubstituidoPoco);
					jaCadastrado.setQtdHidrometroSubstituidoRamal(jaCadastrado.getQtdHidrometroSubstituidoRamal()	+ qtdHidrometroSubstituidoRamal);
					jaCadastrado.setQtdHidrometroRetiradoPoco(jaCadastrado.getQtdHidrometroRetiradoPoco()	+ qtdHidrometroRetiradoPoco);
					jaCadastrado.setQtdHidrometroRetiradoRamal(jaCadastrado.getQtdHidrometroRetiradoRamal() + qtdHidrometroRetiradoRamal);
					jaCadastrado.setQtdHidrometroAtualmenteInstaladosRamal(jaCadastrado.getQtdHidrometroAtualmenteInstaladosRamal() + qtdHidrometroAtualmenteInstaladosRamal);
					jaCadastrado.setQtdHidrometroAtualmenteInstaladosPoco(jaCadastrado.getQtdHidrometroAtualmenteInstaladosPoco() + qtdHidrometroAtualmenteInstaladosPoco);

				} else {
					colecaoRetorno.add(resumoInstalacaoHidrometroHelper);
				}

				dadosImovel = null;
				
			}

		}
		return colecaoRetorno;
	}

	/**
	 * [UC0564] Gerar Resumo das Instalações de Hidrômetros
	 * 
	 * Pesquisa os ids dos setores comercias dos imóveis que tem hidrometro
	 * instalado no histórico
	 * 
	 * @author Pedro Alexandre
	 * @date 08/05/2007
	 * 
	 * @param anoMesFaturamento
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsSetorComercialParaGerarResumoInstalacaoHidrometro(
			Integer anoMesFaturamento) throws ControladorException {
		try {
			return repositorioGerencialMicromedicao
					.pesquisarIdsSetorComercialParaGerarResumoInstalacaoHidrometro(anoMesFaturamento);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Método que gera o resumo da Leituras Anormalidades
	 * 
	 * [UC0551] - Gerar Resumo da Leitura Anormalidade
	 * 
	 * @author Ivan Sérgio
	 * @date 26/04/2007, 28/05/2007, 21/06/2007, 27/07/2007, 15/08/2007, 18/02/2008, 05/12/2008
	 * @alteracao: 28/05/2007 - Troca do parametro Localidade para Setor Comercial;
	 * @alteracao: 21/06/2007 - Duas quebras novas: Empresa(EMPR_ID), Situacao Leitura(LTST_ID);
	 * @alteracao: 27/07/2007 - Trocar o parametro AMREFERENCIAARRECADACAO para AMREFERENCIAFATURAMENTO;
	 * 							Alteracao na forma de consulta para o campo LAST_ID. Agora por CONTA ou IMOVEL;
	 * @alteracao: 15/08/2007 - Nova quebra adicionada, FATURAMENTOGRUPO;
	 * @alteracao: 18/02/2008 - O código do setor comercial(RELT_CDSETORCOMERICIAL) está recebendo o id do setor comercial(STCM_ID)
	 * @Alteracao: 05/12/2008 - CRC719 - Adicionado o campo idLeituraAnormalidadeInformada e quantidadeLeituraInformada
	 */
	public void gerarResumoLeituraAnormalidade(int idSetorComercial, int anoMesLeitura, int idFuncionalidadeIniciada)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.SETOR_COMERCIAL, idSetorComercial);

		try {
			List<Object[]> imoveisResumoLeituraAnormalidade = this.repositorioGerencialMicromedicao
					.getImoveisResumoLeituraAnormalidade(idSetorComercial,anoMesLeitura);

			List<UnResumoLeituraAnormalidadeHelper> listaSimplificada = new ArrayList<UnResumoLeituraAnormalidadeHelper>();
			List<UnResumoLeituraAnormalidade> listaResumoLeituraAnormalidade = new ArrayList<UnResumoLeituraAnormalidade>();
			
			// Declaracao dos Objetos
			Object obj = null;
			Object[] retorno = null;
			Object[] retorno2 = null;
			UnResumoLeituraAnormalidadeHelper helper = null;
			Categoria categoria = null;
			ImovelSubcategoria subcategoria = null;
			Imovel imovel = null;
			Cliente clienteTemp = null;
			UnResumoLeituraAnormalidadeHelper jaCadastrado = null;
			List<Object[]> imoveisResumoLeituraAnormalidadeComplementar = null;
			//Integer tipoPesquisaPrincipalCategoria = null;
			
			//FS0001 - Verificar existencia de dados para o ano/mes referencia informado	   		
			getControladorGerencialCadastro().excluirResumoGerencial( 
					getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
					UnResumoLeituraAnormalidade.class.getName(), "referencia", idSetorComercial );
			
			// pra cada objeto obter a categoria
			for (int i = 0; i < imoveisResumoLeituraAnormalidade.size(); i++) {
				obj = imoveisResumoLeituraAnormalidade.get(i);

				if (obj instanceof Object[]) {
					retorno = (Object[]) obj;

					// Montamos um objeto de resumo, com as informacoes do
					// retorno
					/*
					helper = new UnResumoLeituraAnormalidadeHelper(
							(Integer) retorno[1],   // Gerencia Regional
							(Integer) retorno[2],   // Unidade de Negocio
							(Integer) retorno[3],   // Codigo do Elo
							(Integer) retorno[4],   // Localidade
							(Integer) retorno[5],   // Setor Comercial
							(Integer) retorno[6],   // Rota
							(Integer) retorno[7],   // Quadra
							(Integer) retorno[8],   // Perfil Imovel
							(Integer) retorno[9],  // Situação Ligação Esgoto
							(Integer) retorno[10],  // Perfil da Ligação da Água
							(Integer) retorno[11],  // Perfil da Ligação do Esgoto
							(Integer) retorno[12],  // Tipo da Medição
							(Integer) retorno[13],  // Leitura Anormalidade
							(Integer) retorno[14],  // Código do Setor Comercial
							(Integer) retorno[15],  // Número da Quadra
							(Integer) retorno[16],  // Empresa
							(Integer) retorno[17]); // Situacao da Leitura
                    */
					
					helper = new UnResumoLeituraAnormalidadeHelper();
					
					Integer idImovel = (Integer) retorno[0]; // Codigo do imovel que esta sendo processado
					helper.setIdPerfilLigacaoAgua( (Integer) retorno[1]); // Perfil da Ligação da Água
					helper.setIdPerfilLigacaoEsgoto( (Integer) retorno[2]); // Perfil da Ligação de Esgoto
					helper.setIdTipoMedicao( (Integer) retorno[3]); // Tipo da Medição
					helper.setIdLeituraAnormalidade( (Integer) retorno[4]); // Leitura Anormalidade
					helper.setIdEmpresa( (Integer) retorno[5]); // Empresa
					helper.setIdSituacaoLeitura( (Integer) retorno[6]); // Situacao da Leitura
					
					//*****************************************
					// Alteracao CRC719
					//*****************************************
					helper.setIdLeituraAnormalidadeInformada((Integer) retorno[7]); // Leitura Anormalidade Informada
					helper.setQuantidadeLeituraInformada((Integer) retorno[8]); // Quantidade Leitura Informada
					//*****************************************
					
					// Pesquisamos os outros campos
					// [FS0003] - Verificar Existencia de conta para o mes de faturamento
					imoveisResumoLeituraAnormalidadeComplementar = this.repositorioGerencialMicromedicao
						.pesquisarLeituraAnormalidadeComplementar(idImovel, anoMesLeitura);
										
					retorno2 = imoveisResumoLeituraAnormalidadeComplementar.get(0);
					
					//tipoPesquisaPrincipalCategoria = (Integer) retorno2[0];
					
					helper.setIdGerenciaRegional( (Integer) retorno2[2]); // Gerencia Regional
					helper.setIdUnidadeNegocio( (Integer) retorno2[3]); // Unidade Negocio
					helper.setIdCodigoElo( (Integer) retorno2[4]); // Elo
					helper.setIdLocalidade( (Integer) retorno2[5]); // Localidade
					helper.setIdSetorComercial( (Integer) retorno2[6]); // Setor Comercial
					helper.setIdRota( (Integer) retorno2[7]); // Rota
					helper.setIdQuadra( (Integer) retorno2[8]); // Quadra
					helper.setIdPerfilImovel( (Integer) retorno2[9]); // Perfil Imovel
					
					// Pesquisamos a esfera de poder do cliente responsavel
					helper.setIdEsfera(repositorioGerencialCadastro
									.pesquisarEsferaPoderClienteResponsavelImovel(idImovel));
					// Pesquisamos o tipo de cliente responsavel do imovel
					helper.setIdTipoClienteResponsavel(repositorioGerencialCadastro
									.pesquisarTipoClienteClienteResponsavelImovel(idImovel));

					helper.setIdSituacaoLigacaoAgua( (Integer) retorno2[10]); // Situacao Ligacao Agua
					helper.setIdSituacaoLigacaoEsgoto( (Integer) retorno2[11]); // Situacao Ligacao Esgoto
					
					// Verifica se pesquisa a Principal Categoria pela Conta ou Imovel
					// de acordo com o indicador do [pesquisarLeituraAnormalidadeComplementar]
					// 0 - conta; 1 - imovel
					/*
					if (tipoPesquisaPrincipalCategoria == 0) {
						// Obtter principal categoria da Conta
						categoria = this.getControladorImovel().
										obterPrincipalCategoriaConta( (Integer) retorno2[1]);
					}else if (tipoPesquisaPrincipalCategoria == 1) {
						// Obtter principal categoria do Imovel
						categoria = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);
					}
					*/
					categoria = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);
					
					if (categoria != null) {
						helper.setIdPrincipalCategoria(categoria.getId());
						
						// Pesquisando a principal subcategoria
						subcategoria = this.getControladorImovel()
								.obterPrincipalSubcategoria(categoria.getId(), idImovel);
						
						if (subcategoria != null) {
							helper.setIdPrincipalSubCategoria(subcategoria
									.getComp_id().getSubcategoria().getId());
						}else {
							System.out.println("@@@@ERRO---> SUBCATEGORIA");
							System.out.println("@@@@ERRO---> Setor: " + idSetorComercial);
							System.out.println("@@@@ERRO---> Conta: " + retorno2[1]);
							System.out.println("@@@@ERRO---> Imovel: " + idImovel);
							System.out.println("@@@@ERRO---> Categoria: " + categoria.getId());
						}
						
					}else {
						System.out.println("@@@@ERRO---> CATEGORIA");
						System.out.println("@@@@ERRO---> Setor: " + idSetorComercial);
						System.out.println("@@@@ERRO---> Conta: " + retorno2[1]);
						System.out.println("@@@@ERRO---> Imovel: " + idImovel);
					}
					
					helper.setCodigoSetorComercial( (Integer) retorno2[12]); // Codigo Setor Comercial
					helper.setNumeroQuadra( (Integer) retorno2[13]); // Numero Quadra
					helper.setIdConsumoTarifa( (Integer) retorno2[14]); // Consumo Tarifa
					helper.setIdFaturamentoGrupo( (Integer) retorno2[15]); // Faturamento Grupo
					
					// Verificamos se a esfera de poder foi encontrada
					// [FS0002] Verificar existencia de cliente responsavel
					if (helper.getIdEsfera().equals(0)) {
						imovel = new Imovel();
						imovel.setId(idImovel);
						clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helper.setIdEsfera(clienteTemp.getClienteTipo()
									.getEsferaPoder().getId());
						}
					}
					if (helper.getIdTipoClienteResponsavel().equals(0)) {
						imovel = new Imovel();
						imovel.setId(idImovel);
						clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helper.setIdTipoClienteResponsavel(clienteTemp
									.getClienteTipo().getId());
						}
					}

					// se ja existe um objeto igual a ele entao soma as
					// Quantidades de Leituras
					// um objeto eh igual ao outro se ele tem todos as
					// informacoes iguais
					if (listaSimplificada.contains(helper)) {
						int posicao = listaSimplificada.indexOf(helper);
						jaCadastrado = listaSimplificada.get(posicao);

						// Incrementamos as Leituras
						jaCadastrado.setQuantidadeLeitura(jaCadastrado
								.getQuantidadeLeitura() + 1);

						//*********************************************
						// Alteracao CRC719
						//*********************************************
						jaCadastrado.setQuantidadeLeituraInformada(
								jaCadastrado.getQuantidadeLeituraInformada() + 
								helper.getQuantidadeLeituraInformada());
						//*********************************************
					} else {
						// Incrementamos as Leituras
						helper.setQuantidadeLeitura(helper
								.getQuantidadeLeitura() + 1);

						listaSimplificada.add(helper);
					}
				}
			}
			
			imoveisResumoLeituraAnormalidade.clear();
			imoveisResumoLeituraAnormalidade = null;

			/**
			 * para todas as UnImovelResumoLeituraAnormalidadeHelper cria
			 * UnResumoLeituraAnormalidade
			 */
			
			// Declaracao dos Objetos
			Integer anoMesReferencia = null;
			GGerenciaRegional gerenciaRegional = null;
			GUnidadeNegocio unidadeNegocio = null;
			GLocalidade elo = null;
			GLocalidade localidade = null;
			Integer setorComercial = null;
			GRota rota = null;
			Integer quadra = null;
			GImovelPerfil imovelPerfil = null;
			GEsferaPoder esferaPoder = null;
			GClienteTipo clienteTipo = null;
			GLigacaoAguaSituacao ligacaoAguaSituacao = null;
			GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
			GCategoria gCategoria = null;
			GSubcategoria gSubcategoria = null;
			GLigacaoAguaPerfil ligacaoAguaPerfil = null;
			GLigacaoEsgotoPerfil ligacaoEsgotoPerfil = null;
			GMedicaoTipo medicaoTipo = null;
			Integer leituraAnormalidade = null;
			GSetorComercial codigoSetorComercial = null;
			GQuadra numeroQuadra = null;
			Integer quantidadeLeitura = null;
			GEmpresa empresa = null;
			GLeituraSituacao situacaoLeitura = null;
			GConsumoTarifa consumoTarifa = null;
			GFaturamentoGrupo faturamentoGrupo = null;
			UnResumoLeituraAnormalidade resumoLeituraAnormalidade = null;
			
			//******************************************
			// Alteracao CRC719
			//******************************************
			Integer leituraAnormalidadeInformada = null;
			Integer quantidadeLeituraInformada = null;
			//******************************************
					
			for (int i = 0; i < listaSimplificada.size(); i++) {
				helper = listaSimplificada.get(i);

				// Montamos todo o agrupamento necessario
				// Mes ano de referencia
				//Integer anoMesReferencia = Util
				//		.getAnoMesComoInteger(new Date());
				anoMesReferencia = anoMesLeitura;

				// Gerencia Regional
				if (helper.getIdGerenciaRegional() != null) {
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId(helper.getIdGerenciaRegional());
				}

				// Unidade de Negocio
				if (helper.getIdUnidadeNegocio() != null) {
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId(helper.getIdUnidadeNegocio());
				}

				// Elo
				if (helper.getIdCodigoElo() != null) {
					elo = new GLocalidade();
					elo.setId(helper.getIdCodigoElo());
				}

				// Localidade
				if (helper.getIdLocalidade() != null) {
					localidade = new GLocalidade();
					localidade.setId(helper.getIdLocalidade());
				}

				// Codigo do Setor Comercial
				if (helper.getCodigoSetorComercial() != null) {
					setorComercial = (helper.getCodigoSetorComercial());
				}
				//if (helper.getIdSetorComercial() != null) {
				//	setorComercial = (helper.getIdSetorComercial());
				//}

				// Rota
				if (helper.getIdRota() != null) {
					rota = new GRota();
					rota.setId(helper.getIdRota());
				}

				// Quadra
				if (helper.getIdQuadra() != null) {
					quadra = (helper.getIdQuadra());
				}

				// Perfil do Imovel
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new GImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}

				// Esfera de Poder
				if (helper.getIdEsfera() != null) {
					esferaPoder = new GEsferaPoder();
					esferaPoder.setId(helper.getIdEsfera());
				}

				// Tipo do Cliente Responsável
				if (helper.getIdTipoClienteResponsavel() != null) {
					clienteTipo = new GClienteTipo();
					clienteTipo.setId(helper.getIdTipoClienteResponsavel());
				}

				// Situacao da Ligação da Água
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new GLigacaoAguaSituacao();
					ligacaoAguaSituacao
							.setId(helper.getIdSituacaoLigacaoAgua());
				}

				// Situação da Ligação do Esgoto
				if (helper.getIdSituacaoLigacaoEsgoto() != null) {
					ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper
							.getIdSituacaoLigacaoEsgoto());
				}

				// Principal Categoria do Imovel
				if (helper.getIdPrincipalCategoria() != null) {
					gCategoria = new GCategoria();
					gCategoria.setId(helper.getIdPrincipalCategoria());
				}

				// Principal SubCategoria da Principal Categoria do Imovel
				if (helper.getIdPrincipalSubCategoria() != null) {
					gSubcategoria = new GSubcategoria();
					gSubcategoria.setId(helper.getIdPrincipalSubCategoria());
				}

				// Perfil da Ligação da Água
				if (helper.getIdPerfilLigacaoAgua() != null) {
					ligacaoAguaPerfil = new GLigacaoAguaPerfil();
					ligacaoAguaPerfil.setId(helper.getIdPerfilLigacaoAgua());
				}

				// Perfil da Ligação do Esgoto
				if (helper.getIdPerfilLigacaoEsgoto() != null) {
					ligacaoEsgotoPerfil = new GLigacaoEsgotoPerfil();
					ligacaoEsgotoPerfil.setId(helper.getIdPerfilLigacaoEsgoto());
				}

				// Tipo da Medição
				if (helper.getIdTipoMedicao() != null) {
					medicaoTipo = new GMedicaoTipo();
					medicaoTipo.setId(helper.getIdTipoMedicao());
				}

				// Leitura Anormalidade
				if (helper.getIdLeituraAnormalidade() != null) {
					leituraAnormalidade = (helper.getIdLeituraAnormalidade());
				}

				// Setor comercial
				if (helper.getCodigoSetorComercial() != null) {
					codigoSetorComercial = new GSetorComercial();
					codigoSetorComercial.setId(helper.getIdSetorComercial());
					codigoSetorComercial.setCodigo(helper.getCodigoSetorComercial());
				}

				// Numero da quadra
				if (helper.getNumeroQuadra() != null) {
					numeroQuadra = new GQuadra();
					numeroQuadra.setId(helper.getIdQuadra());
					numeroQuadra.setNumeroQuadra(helper.getNumeroQuadra());
				}
				
				// Quantidade Quantidade das Leitura
				quantidadeLeitura = helper.getQuantidadeLeitura();
				
				// Empresa
				if (helper.getIdEmpresa() != null) {
					empresa = new GEmpresa();
					empresa.setId(helper.getIdEmpresa());
				}
				
				// Leitura Situacao
				if (helper.getIdSituacaoLeitura() != null) {
					situacaoLeitura = new GLeituraSituacao();
					situacaoLeitura.setId(helper.getIdSituacaoLeitura());
				}
				
				// Consumo Tarifa
				if (helper.getIdConsumoTarifa() != null) {
					consumoTarifa = new GConsumoTarifa();
					consumoTarifa.setId(helper.getIdConsumoTarifa());
				}
				
				// Faturamento Grupo
				if (helper.getIdFaturamentoGrupo() != null) {
					faturamentoGrupo = new GFaturamentoGrupo();
					faturamentoGrupo.setId(helper.getIdFaturamentoGrupo());
				}
				
				//******************************************
				// Alteracao CRC719
				//******************************************
				leituraAnormalidadeInformada = helper.getIdLeituraAnormalidadeInformada();
				quantidadeLeituraInformada = helper.getQuantidadeLeituraInformada();
				//******************************************
				
				// Criamos um resumo de Leitura Anormalidade
				resumoLeituraAnormalidade = new UnResumoLeituraAnormalidade(
						anoMesReferencia, gerenciaRegional, unidadeNegocio,
						elo, localidade, setorComercial, rota, quadra,
						imovelPerfil, esferaPoder, clienteTipo,
						ligacaoAguaSituacao, ligacaoEsgotoSituacao, gCategoria,
						gSubcategoria, ligacaoAguaPerfil, ligacaoEsgotoPerfil,
						medicaoTipo, leituraAnormalidade, codigoSetorComercial,
						numeroQuadra, quantidadeLeitura, empresa, situacaoLeitura);
				
				resumoLeituraAnormalidade.setGerConsumoTarifa(consumoTarifa);
				resumoLeituraAnormalidade.setGerFaturamentoGrupo(faturamentoGrupo);
				
				resumoLeituraAnormalidade.setIdLeituraAnormalidadeInformada(leituraAnormalidadeInformada);
				resumoLeituraAnormalidade.setQuantidadeLeiturasInformada(quantidadeLeituraInformada);
				
				// Adicionamos a lista que deve ser inserida
				listaResumoLeituraAnormalidade.add(resumoLeituraAnormalidade);
			}

			// Insere o resumo na UnResumoLeituraAnormalidade
			// this.repositorioGerencialCadastro
			// .inserirResumoLeituraAnormalidadeBatch(listaResumoLeituraAnormalidade);
			System.out.print("Início do Inserir");
			this.getControladorBatch().inserirColecaoObjetoParaBatchGerencial(listaResumoLeituraAnormalidade);

			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exceção que o processo
			// batch venha a lançar e garantir que a unidade de processamento do
			// batch será atualizada com o erro ocorrido

			ex.printStackTrace();
			// sessionContext.setRollbackOnly();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);

			throw new EJBException(ex);
		}
	}// gerarResumoLeituraAnormalidade

	/**
	 * Método que gera o resumo de Hidrômetros
	 * 
	 * [UC0567] - Gerar Resumo de Hidrometros
	 * 
	 * @author Thiago Tenório, Ivan Sérgio
	 * @date 24/04/2007, 08/08/2007
	 * @Alteracao: Dois campos adicionados a quebra: Motivo Baixa e Classe Metrologica;
	 * 
	 */
	public void gerarResumoHidrometros(Integer idHidrometroMarca, int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;

		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.HIDROMETRO_MARCA,
				idHidrometroMarca);

		try {

			int indice = 0;
			int qtRegistros = 500;

			// FS0001 - Verificar existencia de dados para o ano/mes referencia
			// informado
			getControladorGerencialCadastro().excluirResumoGerencialC(getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(),
					"micromedicao.un_resumo_hidrometro", "rehi_amreferencia", "himc_id", idHidrometroMarca);

			boolean flagTerminou = false;

			while (!flagTerminou) {

				Collection<Object[]> hidrometros = this.repositorioGerencialMicromedicao.getHidrometrosResumoHidrometro(idHidrometroMarca, indice, qtRegistros);

				if (qtRegistros > hidrometros.size()) {
					flagTerminou = true;
				} else {
					indice += qtRegistros;
				}

				List<ResumoHidrometroHelper> listaSimplificada = new ArrayList<ResumoHidrometroHelper>();
				List<UnResumoHidrometro> listaResumoHidrometro = new ArrayList<UnResumoHidrometro>();

				Iterator<Object[]> imoveisHidrometrosIterator = hidrometros.iterator();

				// pra cada objeto obter a categoria
				// caso ja tenha um igual soma a quantidade hidrometros
				while (imoveisHidrometrosIterator.hasNext()) {
					Object obj = imoveisHidrometrosIterator.next();

					if (obj instanceof Object[]) {
						Object[] retorno = (Object[]) obj;

						// Montamos um objeto de resumo, com as informacoes do
						// retorno
						ResumoHidrometroHelper helper = new ResumoHidrometroHelper((Integer) retorno[0], // idHidrometroMotivoBaixa
								(Integer) retorno[1], // idLocalArmazenagem
								(Integer) retorno[2], // idHidrometroTipo
								(Integer) retorno[3], // idHidrometroSituacao
								(Short) retorno[4], // numeroAnoFabricacao
								(Integer) retorno[5], // idHidrometroMarca
								(Integer) retorno[6], // idHidrometroDiametro
								(Integer) retorno[7], // idHidrometroCapacidade
								(Short) retorno[8],// indicadorMacro
								(Integer) retorno[9]);

						helper.setIdMotivoBaixa((Integer) retorno[10]);
						helper.setIdClasseMetrologica((Integer) retorno[11]);

						// pesquisando a categoria
						// [UC0306] - Obtter principal categoria do imóvel
						// Integer idImovel = (Integer) retorno[0];

						listaSimplificada.add(helper);

						/**
						 * para todas os ImovelResumoHidrometroHelper cria
						 * ResumoHidrometrocount
						 */

						Integer anoMesReferencia = Fachada.getInstancia().pesquisarParametrosDoSistema().getAnoMesFaturamento();

						// Local Armazenagem
						GHidrometroLocalArmazenagem hidrometroLocalArmazenagem = null;
						if (helper.getIdLocalArmazenagem() != null) {
							hidrometroLocalArmazenagem = new GHidrometroLocalArmazenagem();
							hidrometroLocalArmazenagem.setId(helper.getIdLocalArmazenagem());
						}

						// Tipo de Hidrometro
						GHidrometroTipo hidrometroTipo = null;
						if (helper.getIdHidrometroTipo() != null) {
							hidrometroTipo = new GHidrometroTipo();
							hidrometroTipo.setId(helper.getIdHidrometroTipo());
						}

						// Situação do Hidrometro
						GHidrometroSituacao hidrometroSituacao = null;
						if (helper.getIdHidrometroSituacao() != null) {
							hidrometroSituacao = new GHidrometroSituacao();
							hidrometroSituacao.setId(helper.getIdHidrometroSituacao());
						}

						// Ano de fabricação
						Short numeroAnoFabricacao = 0;
						if (helper.getNumeroAnoFabricacao() != 0) {
							numeroAnoFabricacao = (helper.getNumeroAnoFabricacao());
						}

						GHidrometroMarca hidrometroMarcaG = new GHidrometroMarca();
						hidrometroMarcaG.setId(idHidrometroMarca);
						
						

						// Diametro do Hidrometro
						GHidrometroDiametro hidrometroDiametro = null;
						if (helper.getIdHidrometroDiametro() != null) {
							hidrometroDiametro = new GHidrometroDiametro();
							hidrometroDiametro.setId(helper.getIdHidrometroDiametro());
						}

						// Capacidade do Hidrometro
						GHidrometroCapacidade hidrometroCapacidade = null;
						if (helper.getIdHidrometroCapacidade() != null) {
							hidrometroCapacidade = new GHidrometroCapacidade();
							hidrometroCapacidade.setId(helper.getIdHidrometroCapacidade());
						}

						// Indicador de Macro
						Short indicadorMacro = null;
						if (helper.getIndicadorMacro() != null) {
							indicadorMacro = (helper.getIndicadorMacro());
						}

						// // Quantidade de Hidrometros Acumulada
						// Motivo Baixa
						GHidrometroMotivoBaixa motivoBaixa = null;
						if (helper.getIdMotivoBaixa() != null) {
							motivoBaixa = new GHidrometroMotivoBaixa();
							motivoBaixa.setId(helper.getIdMotivoBaixa());
						}

						// Classe Metrologica
						GHidrometroClasseMetrologica classeMetrologica = null;
						if (helper.getIdClasseMetrologica() != null) {
							classeMetrologica = new GHidrometroClasseMetrologica();
							classeMetrologica.setId(helper.getIdClasseMetrologica());
						}
						
						// Criamos um resumo de Hidrometros (***UFA***)
						UnResumoHidrometro resumoHidrometro = new UnResumoHidrometro(null, anoMesReferencia.intValue(), numeroAnoFabricacao, indicadorMacro,
								helper.getCount(), new Date(), hidrometroMarcaG, hidrometroSituacao, hidrometroLocalArmazenagem, hidrometroTipo,
								hidrometroDiametro, hidrometroCapacidade);

						resumoHidrometro.setGerHidrometroMotivoBaixa(motivoBaixa);
						resumoHidrometro.setGerHidrometroClasseMetrologica(classeMetrologica);

						// Adicionamos a lista que deve ser inserida
						listaResumoHidrometro.add(resumoHidrometro);

					}

				}

				if (listaResumoHidrometro != null && !listaResumoHidrometro.isEmpty()) {
					getControladorBatch().inserirColecaoObjetoParaBatchGerencial(listaResumoHidrometro);
				}

				listaResumoHidrometro.clear();
				listaSimplificada.clear();
				listaResumoHidrometro = null;
				listaSimplificada = null;
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);

			System.out.println("Finalizado");

		} catch (Exception ex) {
			ex.printStackTrace();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);

			throw new EJBException(ex);
		}
	}
	
	/**
	 * Retorna o menor dos maiores mês/ano das tabelas utilizadas para atualizar o resumo
	 * 
	 * [UC0573] - Gerar Resumo Indicadores da Micromedição
	 * 
	 * @author Rafael Corrêa
	 * @date 11/03/2008
	 * 
	 */
	private Integer obterMenorEntreMaioresAnoMesTabelasParaResumoIndicadoresMicromedicao()
			throws ControladorException {
	
		Integer retorno = null;

		try {

			Collection<Integer> colecaoAnosMeses = new ArrayList<Integer>();
			
			Integer anoMesResumoConsumoAgua = this.repositorioGerencialMicromedicao
					.pesquisarMaiorAnoMesResumoConsumoAgua();
			colecaoAnosMeses.add(anoMesResumoConsumoAgua);

			Integer anoMesResumoColetaEsgoto = this.repositorioGerencialMicromedicao
					.pesquisarMaiorAnoMesResumoColetaEsgoto();
			colecaoAnosMeses.add(anoMesResumoColetaEsgoto);
			
			Integer anoMesResumoLeituraAnormalidade = this.repositorioGerencialMicromedicao
					.pesquisarMaiorAnoMesResumoLeituraAnormalidade();
			colecaoAnosMeses.add(anoMesResumoLeituraAnormalidade);
			
			Integer anoMesResumoInstalacaoHidrometro = this.repositorioGerencialMicromedicao
					.pesquisarMaiorAnoMesResumoInstalacaoHidrometro();
			colecaoAnosMeses.add(anoMesResumoInstalacaoHidrometro);

			if (colecaoAnosMeses != null && !colecaoAnosMeses.isEmpty()) {
				for (Integer anoMes : colecaoAnosMeses) {
					if (anoMes != null && (retorno == null || retorno.intValue() > anoMes.intValue())) {
						retorno = anoMes;
					}
				}
			}

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}
	
	/**
	 * Método que gera resumo indicadores da micromedição
	 * 
	 * [UC0573] - Gerar Resumo Indicadores da Micromedição
	 * 
	 * @author Rafael Corrêa
	 * @date 11/03/2008
	 * 
	 */
	public void gerarResumoIndicadoresMicromedicao(int idFuncionalidadeIniciada)
			throws ControladorException {
		
		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o inï¿½cio do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE, 0);

		
		
		try {
			Integer anoMesTabelas = this.obterMenorEntreMaioresAnoMesTabelasParaResumoIndicadoresMicromedicao();
			Integer anoMesResumoIndicadorMicromedicao = this.repositorioGerencialMicromedicao
					.pesquisarMaiorAnoMesResumoIndicadorDesempenhoMicromedicao();
			
//			if (anoMesTabelas == null
//					|| (anoMesResumoIndicadorMicromedicao != null && anoMesTabelas
//							.intValue() <= anoMesResumoIndicadorMicromedicao
//							.intValue())) {
//				throw new ControladorException(
//						"atencao.ano.mes.indicador.maior.igual.ano.mes.resumo", null, "da Micromedição");
//			}
			
			if (anoMesTabelas != null
					&& (anoMesResumoIndicadorMicromedicao == null || anoMesTabelas
							.intValue() > anoMesResumoIndicadorMicromedicao
							.intValue())) {
				this.repositorioGerencialMicromedicao.atualizarDadosResumoIndicadorDesempenhoMicromedicao(anoMesResumoIndicadorMicromedicao, anoMesTabelas);
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exceï¿½ï¿½o que o processo
			// batch venha a lanï¿½ar e garantir que a unidade de processamento do
			// batch serï¿½ atualizada com o erro ocorrido
			ex.printStackTrace();
			// sessionContext.setRollbackOnly();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);

			throw new EJBException(ex);
		}
		
	}

	
	/**
	 * Retorna o valor de controladorCadastro
	 * 
	 * @return O valor de controladorCadastro
	 */
	private ControladorGerencialCadastroLocal getControladorGerencialCadastro() {

		ControladorGerencialCadastroLocalHome localHome = null;
		ControladorGerencialCadastroLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorGerencialCadastroLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_CADASTRO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}	
	
	
	/**
	 * 
	 * [UC0892]Consulta os registros do Relatorio Resumo Distrito Operacional
	 * 
	 * @author Hugo Amorim
	 * @date 15/04/2009
	 * 
	 * @return Collection<RelatorioResumoDistritoOperacionalHelper>
	 * @throws ControladorException
	 * 
	 */
	public Collection<RelatorioResumoDistritoOperacionalHelper> pesquisarRelatorioResumoDistritoOperacional(
			FiltrarRelatorioResumoDistritoOperacionalHelper filtro)
			throws ControladorException {
		Collection<RelatorioResumoDistritoOperacionalHelper> retorno = new ArrayList<RelatorioResumoDistritoOperacionalHelper>();

		Collection<GDistritoOperacional> colecaoPesquisa = null;

		try {
			colecaoPesquisa = this.repositorioGerencialMicromedicao
						.pesquisarRelatorioResumoDistritoOperacional(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			colecaoPesquisa.size();
			Iterator<GDistritoOperacional> itera = colecaoPesquisa.iterator();
			while (itera.hasNext()) {
				GDistritoOperacional gdist = itera.next();
				RelatorioResumoDistritoOperacionalHelper helper = new RelatorioResumoDistritoOperacionalHelper();
				try{
					helper.setDistritoOperacional(gdist.getId().toString());
					helper.setDescDistritoOperacional(gdist.getDescricao());
					
					Integer  comHidrometro = 
						this.repositorioGerencialMicromedicao.pesquisarQuantidadeHidrometros(
							filtro,	
							ConstantesSistema.INDICADOR_USO_ATIVO,
							gdist.getId(),
							"distrito",
							filtro.getMesAno(),
							"0",
							"0");
					helper.setComHidrometro(comHidrometro.toString());
					
					Integer semHidrometro = 
						this.repositorioGerencialMicromedicao.pesquisarQuantidadeHidrometros(
							filtro,
							ConstantesSistema.INDICADOR_USO_DESATIVO,
							gdist.getId(),
							"distrito",
							filtro.getMesAno(),
							"0",
							"0");
					helper.setSemHidrometro(semHidrometro.toString());
					
					Integer ligadas = 
						this.repositorioGerencialMicromedicao.pesquisarSituacaoAgua(
								filtro,
								"3",
								gdist.getId(),
								"distrito",
								filtro.getMesAno(),"0","0");
					helper.setSituacaoLigadas(ligadas.toString());
					
					Integer cortadas = 
						this.repositorioGerencialMicromedicao.pesquisarSituacaoAgua(
								filtro,
								"5",
								gdist.getId(),
								"distrito",
								filtro.getMesAno(),"0","0");
					helper.setSituacaoCortadas(cortadas.toString());
					
					Integer total = 
						this.repositorioGerencialMicromedicao.pesquisarSituacaoAguaTotal(
								filtro,
								gdist.getId(),
								"distrito",
								filtro.getMesAno(),"0","0");
					helper.setSituacaoTotal(total.toString());
					
					Integer factiveis = 
						this.repositorioGerencialMicromedicao.pesquisarSituacaoFactivelPotencial(
								filtro,
								gdist.getId(),
								"distrito",
								"2",
								filtro.getMesAno(),"0","0");
								
					helper.setSituacaoFactiveis(factiveis.toString());
					
					Integer potenciais = this.repositorioGerencialMicromedicao.pesquisarSituacaoFactivelPotencial(
							filtro,
							gdist.getId(),
							"distrito",
							"1",
							filtro.getMesAno(),"0","0");
					helper.setSituacaoPotenciais(potenciais.toString());
					
					
					Integer economias = 
						this.repositorioGerencialMicromedicao.pesquisarEconomias(
								"RelatorioResumoDistritoOperacional",
								filtro,
								gdist.getId(),
								"distrito",
								filtro.getMesAno(),"0","0");
					helper.setEconomias(economias.toString());
					
					Integer volumeReal = 
						this.repositorioGerencialMicromedicao.pesquisarVolumeRealMedido(
								filtro,
								gdist.getId(),
								"distrito",
								filtro.getMesAno(),"0","0");
					helper.setVolumeReal(volumeReal.toString());
					
					Integer faturadosComHidrometro =
						this.repositorioGerencialMicromedicao.pesquisarVolumesFaturados(
								filtro,
								"1",
								gdist.getId(),
								"distrito",
								filtro.getMesAno(),"0","0");
					helper.setFaturadosComHidro(faturadosComHidrometro.toString());
					
					Integer faturadosSemHidrometro = this.repositorioGerencialMicromedicao.pesquisarVolumesFaturados(
							filtro,
							"2",
							gdist.getId(),
							"distrito",
							filtro.getMesAno(),"0","0");
					helper.setFaturadosSemHidro(faturadosSemHidrometro.toString());
					
					Integer faturadosTotal = 
						this.repositorioGerencialMicromedicao.pesquisarVolumesFaturadosTotal(
								filtro,
								gdist.getId(),
								"distrito",
								filtro.getMesAno(),"0","0");
					helper.setFaturadosTotal(faturadosTotal.toString());
					
				}catch (ErroRepositorioException e) {
					throw new ControladorException("Erro no Repositorio.");
				}
				retorno.add(helper);
				
			}
		}

		return retorno;
	}

	
	/**
	 * 
	 * [UC0892]Consulta os registros do Relatorio Resumo Zona Abastecimento
	 * 
	 * @author Hugo Amorim
	 * @date 23/04/2009
	 * 
	 * @return Collection<RelatorioResumoDistritoOperacionalHelper>
	 * 
	 */
	public Collection<RelatorioResumoZonaAbastecimentoHelper> pesquisarRelatorioResumoZonaAbastecimento(FiltrarRelatorioResumoDistritoOperacionalHelper filtro)
			throws ControladorException{
		Collection<RelatorioResumoZonaAbastecimentoHelper> retorno = new ArrayList<RelatorioResumoZonaAbastecimentoHelper>();

		Collection<Object[]> colecaoPesquisa = null;

		try {
			colecaoPesquisa = this.repositorioGerencialMicromedicao
						.pesquisarRelatorioResumoZonaAbastecimento(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			colecaoPesquisa.size();
			Iterator<Object[]> itera = colecaoPesquisa.iterator();
			while (itera.hasNext()) {
				Object[] object = itera.next();
				RelatorioResumoZonaAbastecimentoHelper helper = new RelatorioResumoZonaAbastecimentoHelper();
				try{
					helper.setCodigoSetor(object[4].toString());
					helper.setIdSetor(object[9].toString());
					helper.setIdLocalidade(((Integer) object[2]).toString());
					helper.setNomeLocalidade((String)object[3]);
					helper.setIdDistrito(((Integer) object[7]).toString());
					helper.setNomeDistrito((String) object[8]);
					helper.setIdGerencia(((Integer) object[5]).toString());
					helper.setNomeGerencia((String) object[6]);				
					helper.setRota((Short) object[1]);
					Integer idRota = (Integer) object[0];
					
					Integer  comHidrometro = 
						this.repositorioGerencialMicromedicao.pesquisarQuantidadeHidrometros(
						    filtro,
							1,
							idRota,
							"rota",
							filtro.getMesAno(),
							helper.getIdDistrito(),
							helper.getIdGerencia()
							);
					helper.setComHidrometro(comHidrometro);
				
					Integer semHidrometro = 
						this.repositorioGerencialMicromedicao.pesquisarQuantidadeHidrometros(
						    filtro,
							2,
							idRota,
							"rota",
							filtro.getMesAno(),
							helper.getIdDistrito(),
							helper.getIdGerencia());
					helper.setSemHidrometro(semHidrometro);
					
					Integer ligadas = 
						this.repositorioGerencialMicromedicao.pesquisarSituacaoAgua(
								filtro,
								"3",
								idRota,
								"rota",
								filtro.getMesAno(),
								helper.getIdDistrito(),
								helper.getIdGerencia());
					helper.setSituacaoLigadas(ligadas);
					
					Integer cortadas = 
						this.repositorioGerencialMicromedicao.pesquisarSituacaoAgua(
								filtro,
								"5",
								idRota,
								"rota",
								filtro.getMesAno(),
								helper.getIdDistrito(),
								helper.getIdGerencia());
					helper.setSituacaoCortadas(cortadas);
					
					Integer total = 
						this.repositorioGerencialMicromedicao.pesquisarSituacaoAguaTotal(
								filtro,
								idRota,
								"rota",
								filtro.getMesAno(),
								helper.getIdDistrito(),
								helper.getIdGerencia());
					helper.setSituacaoTotal(total);
				
					Integer factiveis = 
						this.repositorioGerencialMicromedicao.pesquisarSituacaoFactivelPotencial(
								filtro,
								idRota,
								"rota",
								"2",
								filtro.getMesAno(),
								helper.getIdDistrito(),
								helper.getIdGerencia());
					helper.setSituacaoFactiveis(factiveis);
					
					Integer potenciais = this.repositorioGerencialMicromedicao.pesquisarSituacaoFactivelPotencial(
							filtro,
							idRota,
							"rota",
							"1",
							filtro.getMesAno(),
							helper.getIdDistrito(),
							helper.getIdGerencia());
					helper.setSituacaoPotenciais(potenciais);
				
					Integer economias = 
						this.repositorioGerencialMicromedicao.pesquisarEconomias(
								"RelatorioResumoZonaAbastecimento",
								filtro,
								idRota,
								"rota",
								filtro.getMesAno(),
								helper.getIdDistrito(),
								helper.getIdGerencia());
					helper.setEconomias(economias);
					
					Integer volumeReal = 
						this.repositorioGerencialMicromedicao.pesquisarVolumeRealMedido(
								filtro,
								idRota,
								"rota",
								filtro.getMesAno(),
								helper.getIdDistrito(),
								helper.getIdGerencia());
					helper.setVolumeReal(volumeReal);
					
					Integer faturadosComHidrometro =
						this.repositorioGerencialMicromedicao.pesquisarVolumesFaturados(
								filtro,
								"1",
								idRota,
								"rota",
								filtro.getMesAno(),
								helper.getIdDistrito(),
								helper.getIdGerencia());
					helper.setFaturadosComHidro(faturadosComHidrometro);
				
					Integer faturadosSemHidrometro = this.repositorioGerencialMicromedicao.pesquisarVolumesFaturados(
							filtro,
							"2",
							idRota,
							"rota",
							filtro.getMesAno(),
							helper.getIdDistrito(),
							helper.getIdGerencia());
					helper.setFaturadosSemHidro(faturadosSemHidrometro);
					
					Integer faturadosTotal = 
						this.repositorioGerencialMicromedicao.pesquisarVolumesFaturadosTotal(
								filtro,
								idRota,
								"rota",
								filtro.getMesAno(),
								helper.getIdDistrito(),
								helper.getIdGerencia());
					helper.setFaruradosTotal(faturadosTotal);
				}catch (ErroRepositorioException e) {
					throw new ControladorException("Erro no Repositorio.");
				}
				retorno.add(helper);
				
			}
		}

		return retorno;
	}
	/**
	 * 
	 * [UC0892]Consulta total dos registros do Relatorio Resumo Distrito
	 * Operacional
	 * 
	 * @author Hugo Amorim
	 * @date 15/04/2009
	 * 
	 * @return Integer
	 * @throws ControladorException
	 * 
	 */
	public Integer pesquisarTotalRegistroRelatorioResumoDistritoOperacional(
			FiltrarRelatorioResumoDistritoOperacionalHelper helper)
			throws ControladorException {
		Integer retorno = null;

		try {
			retorno = this.repositorioGerencialMicromedicao
					.pesquisarTotalRegistroRelatorioResumoDistritoOperacional(helper);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}
	
	/**
	 * Método que gera resumo indicadores da micromedição Por Ano
	 * 
	 * 
	 * @author Fernando Fontelles
	 * @date 17/05/2010
	 * 
	 */
	public void gerarResumoIndicadoresMicromedicaoPorAno(int idFuncionalidadeIniciada)
			throws ControladorException {
		
		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE, 0);
		
		try {
			Integer anoMesTabelas = this.obterMenorEntreMaioresAnoMesTabelasParaResumoIndicadoresMicromedicao();
			Integer anoMesResumoIndicadorMicromedicao = this.repositorioGerencialMicromedicao
					.pesquisarMaiorAnoMesResumoIndicadorDesempenhoMicromedicaoPorAno();
						
			if (anoMesTabelas != null
					&& (anoMesResumoIndicadorMicromedicao == null || anoMesTabelas
							.intValue() > anoMesResumoIndicadorMicromedicao
							.intValue())) {
				
				this.repositorioGerencialMicromedicao.
					atualizarDadosResumoIndicadorDesempenhoMicromedicaoPorAno
					(anoMesResumoIndicadorMicromedicao, anoMesTabelas);
			
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exceï¿½ï¿½o que o processo
			// batch venha a lanï¿½ar e garantir que a unidade de processamento do
			// batch serï¿½ atualizada com o erro ocorrido
			ex.printStackTrace();
			// sessionContext.setRollbackOnly();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);

			throw new EJBException(ex);
		}
		
	}
	
	/**
	 * Gera o resumo das ligações de hidrômetro por ano.
	 * 
	 * Gerar Resumo das Instalações de Hidrômetros Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 17/06/2010
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idSetorComercial
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoInstalacoesHidrometrosPorAno(
			Integer anoMesReferenciaFaturamento, Integer idSetorComercial,
			int idFuncionalidadeIniciada) throws ControladorException {

		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		int idUnidadeIniciada = 0;

		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.SETOR_COMERCIAL, idSetorComercial);

		try {

			System.out.println("INICIO SETOR COMERCIAL " + idSetorComercial);

			
			//FS0001 - Verificar existencia de dados para o ano/mes referencia informado
	   		getControladorGerencialCadastro().excluirResumoGerencialSQL( 
	   				getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
	   					"micromedicao.un_resumo_instalacao_hidrometro_ref_2010", 
	   						"reih_amreferencia", idSetorComercial );				
			
			//Collection colecaoResumoInstalacaoHidrometroInserir = new ArrayList();
			Collection<UnResumoInstalacaoHidrometroPorAno> colecaoResumoInstalacaoHidrometro = null;

			/*
			 * pesquisa os dados do setor comercial 
			 * 
			 * 0 - ID Gerencia Regional 
			 * 1 - ID Unidade Negócio 
			 * 2 - ID Elo 
			 * 3 - ID Localidade 
			 * 4 - ID Municipio
			 * 5 - ID Microrregião 
			 * 6 - ID Região
			 * 7 - Codigo setor comercial
			 */
			Object[] dadosSetorComercial = repositorioGerencialMicromedicao.
												pesquisarDadosSetorComercial(idSetorComercial);

			/** Resumo das instalações de hidrômetros pela REGIONAL */
			GGerenciaRegional gGerenciaRegional = new GGerenciaRegional();
			gGerenciaRegional.setId((Integer) dadosSetorComercial[0]);

			GUnidadeNegocio gUnidadeNegocio = new GUnidadeNegocio();
			gUnidadeNegocio.setId((Integer) dadosSetorComercial[1]);
			gUnidadeNegocio.setGerGerenciaRegional(gGerenciaRegional);

			GLocalidade gElo = new GLocalidade();
			gElo.setId((Integer) dadosSetorComercial[2]);
			gElo.setGerUnidadeNegocio(gUnidadeNegocio);

			GLocalidade gLocalidade = new GLocalidade();
			gLocalidade.setId((Integer) dadosSetorComercial[3]);
			gLocalidade.setGerLocalidade(gElo);
			/** fim regional */

			/** Resumo das instalações de hidrômetros pela REGIAO */
			GMunicipio gMunicipio = new GMunicipio();
			gMunicipio.setId((Integer) dadosSetorComercial[4]);

			GMicrorregiao gMicrorregiao = new GMicrorregiao();
			gMicrorregiao.setId((Integer) dadosSetorComercial[5]);
			gMunicipio.setGerMicrorregiao(gMicrorregiao);

			GRegiao gRegiao = new GRegiao();
			gRegiao.setId((Integer) dadosSetorComercial[6]);
			gMicrorregiao.setGerRegiao(gRegiao);
			
			Integer codigoSetorComercial = (Integer) dadosSetorComercial[7];
			
			/** fim região */

			/** monta o setor comercial */
			GSetorComercial gSetorComercial = new GSetorComercial();
			gSetorComercial.setId(idSetorComercial);
			gSetorComercial.setGerLocalidade(gLocalidade);
			gSetorComercial.setCodigo(codigoSetorComercial);

			Integer ano = new Integer ((anoMesReferenciaFaturamento + "").substring(0,4));
			Integer mes = new Integer ((anoMesReferenciaFaturamento + "").substring(4,6));
			Calendar dataInicio = new GregorianCalendar();
			dataInicio.set(Calendar.YEAR,ano);
			dataInicio.set(Calendar.MONTH, mes - 1);
			dataInicio.set(Calendar.DATE, 1);
			dataInicio.set(Calendar.HOUR, 0);
			dataInicio.set(Calendar.MINUTE, 0);
			dataInicio.set(Calendar.SECOND, 0);
			dataInicio.set(Calendar.MILLISECOND, 000);
			
			Calendar dataFim = new GregorianCalendar();
			dataFim.set(Calendar.YEAR,ano);
			dataFim.set(Calendar.MONTH, mes - 1);
			dataFim.set(Calendar.DATE, 31);
			dataFim.set(Calendar.HOUR, 23);
			dataFim.set(Calendar.MINUTE, 59);
			dataFim.set(Calendar.SECOND, 59);
			dataFim.set(Calendar.MILLISECOND, 999);
			
			/**
			 * pesquisa os ids das quadras pertencentes ao setor comercial
			 * informado
			 */

			Collection<ResumoInstalacaoHidrometroPorAnoHelper> colecaoResumoInstalacaoHidrometroHelper = 
				gerarColecaoResumoInstalacaoHidrometroPorAnoHelper(
					gSetorComercial, dataInicio.getTime(), dataFim.getTime(), anoMesReferenciaFaturamento);

			if (colecaoResumoInstalacaoHidrometroHelper != null	
					&& !colecaoResumoInstalacaoHidrometroHelper.isEmpty()) {
				// [SB0001A] - Gerar Resumo das Instalações de
				// hidrômetros Regional
				colecaoResumoInstalacaoHidrometro  = gerarResumoInstalacoesHidrometrosRegionalPorAno
														(gSetorComercial, 
															colecaoResumoInstalacaoHidrometroHelper,
															anoMesReferenciaFaturamento);

				getControladorBatch().inserirColecaoObjetoParaBatchGerencial(colecaoResumoInstalacaoHidrometro);
				
			}
					
			System.out.println("FIM SETOR COMERCIAL " + idSetorComercial);

			/** fechar unidade de processamento iniciada com sucesso */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);

		} catch (Exception e) {

			/** fechar unidade de processamento iniciada com erro */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,idUnidadeIniciada, true);

			throw new EJBException(e);
		}
	}
	
	/**
	 * Gerar Resumo das Instalações de Hidrômetros Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 17/06/2010
	 * 
	 * @param gSetorComercial
	 * @param dataInicio
	 * @param dataFim
	 * @param anoMesReferenciaFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 * @throws ControladorException
	 */
	private Collection<ResumoInstalacaoHidrometroPorAnoHelper> gerarColecaoResumoInstalacaoHidrometroPorAnoHelper(
			GSetorComercial gSetorComercial, Date dataInicio, Date dataFim, Integer anoMesReferenciaFaturamento)
			throws ErroRepositorioException, ControladorException {

		Collection<Object[]> colecaoDadosImoveis = 
			this.repositorioGerencialMicromedicao.pesquisarDadosImovelResumoInstalacaoHidrometroPorAno
														(gSetorComercial.getId(), dataInicio, dataFim);

		List<ResumoInstalacaoHidrometroPorAnoHelper> colecaoRetorno = 
				new ArrayList<ResumoInstalacaoHidrometroPorAnoHelper>();

		if (colecaoDadosImoveis != null && !colecaoDadosImoveis.isEmpty()) {

			for (Object[] dadosImovel : colecaoDadosImoveis) {

				int qtdHidrometroInstaladoPoco = 0;
				int qtdHidrometroInstaladoRamal = 0;
				int qtdHidrometroRetiradoPoco = 0;
				int qtdHidrometroRetiradoRamal = 0;
				int qtdHidrometroSubstituidoPoco = 0;
				int qtdHidrometroSubstituidoRamal = 0;
				int qtdHidrometroRemanejadoPoco = 0;
				int qtdHidrometroRemanejadoRamal = 0;
				int qtdHidrometroAtualmenteInstaladosRamal = 0;
				int qtdHidrometroAtualmenteInstaladosPoco = 0;
				
				/*
				 * Dados do imóvel :
				 * 
				 * 0 - ID IMOVEL 
				 * 1 - ID PERFIL DO IMOVEL 
				 * 2 - ID SITUACAO DA LIGACAO AGUA 
				 * 3 - ID SITUACAO DA LIGACAO ESGOTO 
				 * 4 - ID PERFIL LIGACAO AGUA 
				 * 5 - ID PERFIL LIGACAO ESGOTO
				 * 6 - INDICADOR SE EXISTE HIDROMETRO INSTALADO RAMAL
				 * 7 - INDICADOR SE EXISTE HIDROMETRO INSTALADO POCO 
				 * 8 - ID DO IMOVEL DO HIDROMETRO
				 * 9 - ID DA LIGACAO DE AGUA DO HIDROMETRO(LAGU_ID)
				 * 10 - INDICADOR DA INSTALACAO OU SUBSTITUICAO
				 * 11 - ANO/MES DE REFERENCIA DA INSTALACAO DO HIDROMETRO
				 * 12 - ANO/MES DE REFERENCIA DA RETIRADA DO HIDROMETRO
				 * 13 - ID DO HIDROMETRO DO IMOVEL
				 * 14 - ID DO HIDROMETRO DA LIGACAO DE AGUA
				 */
				Integer idImovel = (Integer) dadosImovel[0];
				Integer idPerfilImovel = (Integer) dadosImovel[1];
				Integer idLigacaoAguaSituacao = (Integer) dadosImovel[2];
				Integer idLigacaoEsgotoSituacao = (Integer) dadosImovel[3];
				Integer idPerfilLigacaoAgua = (Integer) dadosImovel[4];
				Integer idPerfilLigacaoEsgoto = (Integer) dadosImovel[5];
				Integer flagRamal  = (Integer) dadosImovel[6];
				Integer flagPoco  = (Integer) dadosImovel[7];
				
				
				Integer idImovelHidrometro = (Integer) dadosImovel[8];
				Integer idLigacaoAguaHidrometro = (Integer) dadosImovel[9];
				Short indicadorInstalacaoSubstituicao = (Short) dadosImovel[10];
				String anoMesInstalacao = (String) dadosImovel[11];
				String anoMesRetirada = (String) dadosImovel[12];
				
				Integer idHidrometroImovel = (Integer) dadosImovel[13];
				Integer idHidrometroLigacaoAgua = (Integer) dadosImovel[14];
				
				Integer idConsumoTarifa = (Integer) dadosImovel[15];
				Integer idMarcaHidrometro = (Integer) dadosImovel[16];
				Integer idTipoHidrometro = (Integer) dadosImovel[17];
			    Integer idCapacidadeHidrometro = (Integer) dadosImovel[18];
			    Integer idDiametroHidrometro = (Integer) dadosImovel[19];
			    Integer idClasseHidrometro = (Integer) dadosImovel[20];
			    
				
				if (idPerfilLigacaoAgua == null) {
					idPerfilLigacaoAgua = 0;
				}

				if (idPerfilLigacaoEsgoto == null) {
					idPerfilLigacaoEsgoto = 0;
				}

				System.out.println("Imovel " + idImovel);
						
				if (anoMesInstalacao.equals(anoMesReferenciaFaturamento+ "")) {
					
					if (idImovelHidrometro != null) {
						
						if(indicadorInstalacaoSubstituicao != null){
							if(indicadorInstalacaoSubstituicao.equals(ConstantesSistema.SIM)){
								qtdHidrometroInstaladoPoco++;
							}
								
							if(indicadorInstalacaoSubstituicao.equals(ConstantesSistema.NAO)){
								qtdHidrometroSubstituidoPoco++;
							}
						}
					}

					if (idLigacaoAguaHidrometro != null) {
						
						if(indicadorInstalacaoSubstituicao != null){
						
							if(indicadorInstalacaoSubstituicao.equals(ConstantesSistema.SIM)){
								qtdHidrometroInstaladoRamal++;
							}
							if(indicadorInstalacaoSubstituicao.equals(ConstantesSistema.NAO)){
								qtdHidrometroSubstituidoRamal++;
							}
						}
					}
				}
						
						
				if(anoMesRetirada != null){
					
					if (anoMesRetirada.equals(anoMesReferenciaFaturamento + "")) {
						if(idImovelHidrometro != null){
							if(idHidrometroImovel == null){
								qtdHidrometroRetiradoPoco++;
							}
						}
						
						if(idLigacaoAguaHidrometro != null){
							if(idHidrometroLigacaoAgua == null){
								qtdHidrometroRetiradoRamal++;
							}
						}
					}
				}
						
				// [UC0306] - Obter principal categoria do imóvel
				Categoria categoria = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);
				Integer idPrincipalCategoria = null;
				if (categoria != null) {
					idPrincipalCategoria = categoria.getId();
				}

				Integer idPrincipalSubCategoria = null;

				if (idPrincipalCategoria != null) {

					// Pesquisando a principal subcategoria
					ImovelSubcategoria imovelSubcategoria = this.getControladorImovel().obterPrincipalSubcategoria(categoria.getId(),idImovel);

					if (imovelSubcategoria != null) {
						idPrincipalSubCategoria = imovelSubcategoria.getComp_id().getSubcategoria().getId();
					}
				}

				/*
				 * Dados do cliente responsável do imóvel informado 0 - ID
				 * DA ESFERA DE PODER DO TIPO DO CLIENTE RESPONSÁVEL 1 - ID
				 * DO TIPO DO CLIENTE RESPONSÁVEL
				 */
				Object[] dadosClienteResponsavel = this.repositorioGerencialMicromedicao.
													pesquisarDadosClienteResponsavelPorImovel(idImovel);

				Integer idEsferaPoder = 0;
				Integer idTipoClienteResponsavel = 0;

				if (dadosClienteResponsavel != null) {

					idEsferaPoder = (Integer) dadosClienteResponsavel[0];

					if (idEsferaPoder == null) {
						idEsferaPoder = 0;
					}

					idTipoClienteResponsavel = (Integer) dadosClienteResponsavel[1];

					if (idTipoClienteResponsavel == null) {
						idTipoClienteResponsavel = 0;
					}
				}

				//caso exista hidrometro atualmente instalado ramal
				if(flagRamal.intValue() == ConstantesSistema.SIM.intValue()){
					qtdHidrometroAtualmenteInstaladosRamal = 1;
				} else{
					qtdHidrometroAtualmenteInstaladosRamal = 0;
				}

				//caso exista hidrometro atualmente instalado poço
				if(flagPoco.intValue() == ConstantesSistema.SIM.intValue()){
					qtdHidrometroAtualmenteInstaladosPoco = 1;
				} else{
					qtdHidrometroAtualmenteInstaladosPoco = 0;
				}

				ResumoInstalacaoHidrometroPorAnoHelper resumoInstalacaoHidrometroHelper = 
					new ResumoInstalacaoHidrometroPorAnoHelper(
//						idQuadra,
						idPrincipalCategoria, 
						idPrincipalSubCategoria,
						idEsferaPoder, idTipoClienteResponsavel,
						idPerfilLigacaoAgua, idPerfilLigacaoEsgoto,
						qtdHidrometroInstaladoPoco,
						qtdHidrometroInstaladoRamal,
						qtdHidrometroRetiradoPoco,
						qtdHidrometroRetiradoRamal,
						qtdHidrometroSubstituidoPoco,
						qtdHidrometroSubstituidoRamal,
						qtdHidrometroRemanejadoPoco,
						qtdHidrometroRemanejadoRamal,
						idLigacaoAguaSituacao, idLigacaoEsgotoSituacao,
						idPerfilImovel,
						qtdHidrometroAtualmenteInstaladosRamal,
						qtdHidrometroAtualmenteInstaladosPoco
//						numeroQuadra,
//						idRota,
//						codigoRota
						);
				
				resumoInstalacaoHidrometroHelper.setIdConsumoTarifa(idConsumoTarifa);
				resumoInstalacaoHidrometroHelper.setIdMarcaHidrometro(idMarcaHidrometro);
				resumoInstalacaoHidrometroHelper.setIdTipoHidrometro(idTipoHidrometro);
				resumoInstalacaoHidrometroHelper.setIdCapacidadeHidrometro(idCapacidadeHidrometro);
				resumoInstalacaoHidrometroHelper.setIdDiametroHidrometro(idDiametroHidrometro);
				resumoInstalacaoHidrometroHelper.setIdClasseHidrometro(idClasseHidrometro);

				if (colecaoRetorno.contains(resumoInstalacaoHidrometroHelper)) {

					int posicao = colecaoRetorno.indexOf(resumoInstalacaoHidrometroHelper);

					ResumoInstalacaoHidrometroPorAnoHelper jaCadastrado = colecaoRetorno.get(posicao);

					jaCadastrado.setQtdHidrometroInstaladoPoco(jaCadastrado.getQtdHidrometroInstaladoPoco()+ qtdHidrometroInstaladoPoco);
					jaCadastrado.setQtdHidrometroInstaladoRamal(jaCadastrado.getQtdHidrometroInstaladoRamal() + qtdHidrometroInstaladoRamal);
					jaCadastrado.setQtdHidrometroSubstituidoPoco(jaCadastrado.getQtdHidrometroSubstituidoPoco() + qtdHidrometroSubstituidoPoco);
					jaCadastrado.setQtdHidrometroSubstituidoRamal(jaCadastrado.getQtdHidrometroSubstituidoRamal()	+ qtdHidrometroSubstituidoRamal);
					jaCadastrado.setQtdHidrometroRetiradoPoco(jaCadastrado.getQtdHidrometroRetiradoPoco()	+ qtdHidrometroRetiradoPoco);
					jaCadastrado.setQtdHidrometroRetiradoRamal(jaCadastrado.getQtdHidrometroRetiradoRamal() + qtdHidrometroRetiradoRamal);
					jaCadastrado.setQtdHidrometroAtualmenteInstaladosRamal(jaCadastrado.getQtdHidrometroAtualmenteInstaladosRamal() + qtdHidrometroAtualmenteInstaladosRamal);
					jaCadastrado.setQtdHidrometroAtualmenteInstaladosPoco(jaCadastrado.getQtdHidrometroAtualmenteInstaladosPoco() + qtdHidrometroAtualmenteInstaladosPoco);

				} else {
					colecaoRetorno.add(resumoInstalacaoHidrometroHelper);
				}

				dadosImovel = null;
				
			}

		}
		return colecaoRetorno;
	}
	
	/**
	 * Gerar Resumo das Instalações de Hidrômetros Por Ano
	 * 
	 * [SB0001A] - Acumular total das instalações de hidrômetros Regional
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * @author Fernando Fontelles
	 * @date 17/06/2010
	 * 
	 * @param gSetorComercial
	 * @param colecaoResumoInstalacaoHidrometroHelper
	 * @param anoMesReferenciaFaturamento
	 * 
	 * @throws ControladorException
	 */
	private Collection<UnResumoInstalacaoHidrometroPorAno> gerarResumoInstalacoesHidrometrosRegionalPorAno(
			GSetorComercial gSetorComercial,
			Collection<ResumoInstalacaoHidrometroPorAnoHelper> colecaoResumoInstalacaoHidrometroHelper,
			Integer anoMesReferenciaFaturamento)
			throws ControladorException {

		Collection<UnResumoInstalacaoHidrometroPorAno> colecaoInserirResumoInstalacaoHidrometro = 
			new ArrayList<UnResumoInstalacaoHidrometroPorAno>();

		for (ResumoInstalacaoHidrometroPorAnoHelper resumoInstalacaoHidrometroHelper : 
				colecaoResumoInstalacaoHidrometroHelper) {

			//GSetorComercial gSetorComercial = gQuadra.getGerSetorComercial();
			GLocalidade gLocalidade = gSetorComercial.getGerLocalidade();
			GLocalidade gElo = gLocalidade.getGerLocalidade();
			GUnidadeNegocio gUnidadeNegocio = gElo.getGerUnidadeNegocio();
			GGerenciaRegional gGerenciaRegional = gUnidadeNegocio.getGerGerenciaRegional();

			int qtdHidrometrosInstaladosRamal = resumoInstalacaoHidrometroHelper.getQtdHidrometroInstaladoRamal();
			int qtdHidrometrosSubstituidosRamal = resumoInstalacaoHidrometroHelper.getQtdHidrometroSubstituidoRamal();
			int qtdHidrometrosRemanejadosRamal = resumoInstalacaoHidrometroHelper.getQtdHidrometroRemanejadosRamal();
			int qtdHidrometrosRetiradosRamal = resumoInstalacaoHidrometroHelper.getQtdHidrometroRetiradoRamal();

			int qtdHidrometrosInstaladosPoco = resumoInstalacaoHidrometroHelper.getQtdHidrometroInstaladoPoco();
			int qtdHidrometrosSubstituidosPoco = resumoInstalacaoHidrometroHelper.getQtdHidrometroSubstituidoPoco();
			int qtdHidrometrosRemanejadosPoco = resumoInstalacaoHidrometroHelper.getQtdHidrometroRemanejadosPoco();
			int qtdHidrometrosRetiradosPoco = resumoInstalacaoHidrometroHelper.getQtdHidrometroRetiradoPoco();
			int qtdHidrometroAtualmenteInstaladosRamal = resumoInstalacaoHidrometroHelper.getQtdHidrometroAtualmenteInstaladosRamal();
			int qtdHidrometroAtualmenteInstaladosPoco = resumoInstalacaoHidrometroHelper.getQtdHidrometroAtualmenteInstaladosPoco();

			Integer qtdHidrometrosDadosAtualizados = 0;

//			GQuadra gQuadra = null;
//			Integer idGQuadra = resumoInstalacaoHidrometroHelper.getIdQuadra();
//			Integer numeroGQuadra = resumoInstalacaoHidrometroHelper.getNumeroQuadra();
			
//			if (idGQuadra != null) {
//				gQuadra = new GQuadra();
//				gQuadra.setId(idGQuadra);
//				gQuadra.setNumeroQuadra(numeroGQuadra);
//			}
			
//			GRota gRota = null;
//			Integer idGRota = resumoInstalacaoHidrometroHelper.getIdRota();
//			
//			if (idGRota != null) {
//				gRota = new GRota();
//				gRota.setId(idGRota);
//			}
			
			GSubcategoria gSubcategoria = null;
			Integer idGSubcategoria = resumoInstalacaoHidrometroHelper
					.getIdPrincipalSubCategoria();

			if (idGSubcategoria != null) {
				gSubcategoria = new GSubcategoria();
				gSubcategoria.setId(idGSubcategoria);
			}

			GClienteTipo gClienteTipo = null;
			Integer idGClienteTipo = resumoInstalacaoHidrometroHelper
					.getIdTipoClienteResponsavel();

			if (idGClienteTipo != null) {
				gClienteTipo = new GClienteTipo();
				gClienteTipo.setId(idGClienteTipo);
			}

			GLigacaoAguaSituacao gLigacaoAguaSituacao = null;
			Integer idGLigacaoAguaSituacao = resumoInstalacaoHidrometroHelper
					.getIdLigacaoAguaSituacao();

			if (idGLigacaoAguaSituacao != null) {
				gLigacaoAguaSituacao = new GLigacaoAguaSituacao();
				gLigacaoAguaSituacao.setId(idGLigacaoAguaSituacao);
			}

			GLigacaoAguaPerfil gLigacaoAguaPerfil = null;
			Integer idGLigacaoAguaPerfil = resumoInstalacaoHidrometroHelper
					.getIdPerfilLigacaoAgua();

			if (idGLigacaoAguaPerfil != null) {
				gLigacaoAguaPerfil = new GLigacaoAguaPerfil();
				gLigacaoAguaPerfil.setId(idGLigacaoAguaPerfil);
			}

			GLigacaoEsgotoSituacao gLigacaoEsgotoSituacao = null;
			Integer idGLigacaoEsgotoSituacao = resumoInstalacaoHidrometroHelper
					.getIdLigacaoEsgotoSituacao();

			if (idGLigacaoEsgotoSituacao != null) {
				gLigacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
				gLigacaoEsgotoSituacao.setId(idGLigacaoEsgotoSituacao);
			}

			GLigacaoEsgotoPerfil gLigacaoEsgotoPerfil = null;
			Integer idGLigacaoEsgotoPerfil = resumoInstalacaoHidrometroHelper
					.getIdPerfilLigacaoEsgoto();

			if (idGLigacaoEsgotoPerfil != null) {
				gLigacaoEsgotoPerfil = new GLigacaoEsgotoPerfil();
				gLigacaoEsgotoPerfil.setId(idGLigacaoEsgotoPerfil);
			}

			GEsferaPoder gEsferaPoder = null;
			Integer idGEsferaPoder = resumoInstalacaoHidrometroHelper
					.getIdEsferaPoder();

			if (idGEsferaPoder != null) {
				gEsferaPoder = new GEsferaPoder();
				gEsferaPoder.setId(idGEsferaPoder);
			}

			GCategoria gCategoria = null;
			Integer idGCategoria = resumoInstalacaoHidrometroHelper
					.getIdPrincipalCategoria();

			if (idGCategoria != null) {
				gCategoria = new GCategoria();
				gCategoria.setId(idGCategoria);
			}

			GImovelPerfil gImovelPerfil = null;
			Integer idGImovelPerfil = resumoInstalacaoHidrometroHelper
					.getIdPerfilImovel();

			if (idGImovelPerfil != null) {
				gImovelPerfil = new GImovelPerfil();
				gImovelPerfil.setId(idGImovelPerfil);
			}
			
			//************************************************************
			// CRC556 - Recuperando os novos campos
			//************************************************************
			GConsumoTarifa gConsumoTarifa = null;
			Integer idConsumoTarifa = resumoInstalacaoHidrometroHelper.getIdConsumoTarifa();
			
			if (idConsumoTarifa != null) {
				gConsumoTarifa = new GConsumoTarifa();
				gConsumoTarifa.setId(idConsumoTarifa);
			}
			
			GHidrometroMarca gHidrometroMarca = null;
			Integer idMarcaHidrometro = resumoInstalacaoHidrometroHelper.getIdMarcaHidrometro();
			
			if (idMarcaHidrometro != null) {
				gHidrometroMarca = new GHidrometroMarca();
				gHidrometroMarca.setId(idMarcaHidrometro);
			}
			
			GHidrometroTipo gHidrometroTipo = null;
			Integer idTipoHidrometro = resumoInstalacaoHidrometroHelper.getIdTipoHidrometro();
			
			if (idTipoHidrometro != null) {
				gHidrometroTipo = new GHidrometroTipo();
				gHidrometroTipo.setId(idTipoHidrometro);
			}
			
			GHidrometroCapacidade gHidrometroCapacidade = null;
			Integer idCapacidadeHidrometro = resumoInstalacaoHidrometroHelper.getIdCapacidadeHidrometro();
			
			if (idCapacidadeHidrometro != null) {
				gHidrometroCapacidade = new GHidrometroCapacidade();
				gHidrometroCapacidade.setId(idCapacidadeHidrometro);
			}
			
			GHidrometroDiametro gHidrometroDiametro = null;
			Integer idDiametroHidrometro = resumoInstalacaoHidrometroHelper.getIdDiametroHidrometro();
			
			if (idDiametroHidrometro != null) {
				gHidrometroDiametro = new GHidrometroDiametro();
				gHidrometroDiametro.setId(idDiametroHidrometro);
			}
			
			GHidrometroClasseMetrologica gHidrometroClasse = null;
			Integer idClasseHidrometro = resumoInstalacaoHidrometroHelper.getIdClasseHidrometro();
			
			if (idClasseHidrometro != null) {
				gHidrometroClasse = new GHidrometroClasseMetrologica();
				gHidrometroClasse.setId(idClasseHidrometro);
			}

			UnResumoInstalacaoHidrometroPorAno resumoInstalacaoHidrometro = 
				new UnResumoInstalacaoHidrometroPorAno(
					anoMesReferenciaFaturamento, gSetorComercial.getCodigo(),
//					gQuadra.getNumeroQuadra(), 
					qtdHidrometrosInstaladosRamal,
					qtdHidrometrosSubstituidosRamal, new Date(),
					qtdHidrometrosRemanejadosRamal,
					qtdHidrometrosRetiradosRamal,
					qtdHidrometrosDadosAtualizados, gSubcategoria,
					gClienteTipo, gLigacaoAguaSituacao, gUnidadeNegocio,
					gLocalidade, gElo, 
//					gQuadra, 
					gLigacaoEsgotoSituacao,
					gLigacaoEsgotoPerfil, gGerenciaRegional, gSetorComercial,
					gLigacaoAguaPerfil, gEsferaPoder, gCategoria,
					gImovelPerfil, 
//					gRota, 
					qtdHidrometrosInstaladosPoco,
					qtdHidrometrosSubstituidosPoco,
					qtdHidrometrosRemanejadosPoco, qtdHidrometrosRetiradosPoco, 
					qtdHidrometroAtualmenteInstaladosRamal, 
					qtdHidrometroAtualmenteInstaladosPoco
//					resumoInstalacaoHidrometroHelper.getCodigoRota()
					);
			
			//************************************************************
			// CRC556 - Adicinando os novos campos
			//************************************************************
			resumoInstalacaoHidrometro.setGerConsumoTarifa(gConsumoTarifa);
			resumoInstalacaoHidrometro.setGerHidrometroMarca(gHidrometroMarca);
			resumoInstalacaoHidrometro.setGerHidrometroTipo(gHidrometroTipo);
			resumoInstalacaoHidrometro.setGerHidrometroCapacidade(gHidrometroCapacidade);
			resumoInstalacaoHidrometro.setGerHidrometroDiametro(gHidrometroDiametro);
			resumoInstalacaoHidrometro.setGerHidrometroClasseMetrologica(gHidrometroClasse);

			colecaoInserirResumoInstalacaoHidrometro.add(resumoInstalacaoHidrometro);
			
			resumoInstalacaoHidrometroHelper = null;
		}

		return colecaoInserirResumoInstalacaoHidrometro;
	}
	
}