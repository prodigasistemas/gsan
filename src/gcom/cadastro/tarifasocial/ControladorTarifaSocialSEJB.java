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
package gcom.cadastro.tarifasocial;

import gcom.atendimentopublico.ligacaoagua.ControladorLigacaoAguaLocal;
import gcom.atendimentopublico.ligacaoagua.ControladorLigacaoAguaLocalHome;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocal;
import gcom.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocalHome;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovelEconomia;
import gcom.cadastro.cliente.IRepositorioImovelTarifaSocial;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.cliente.RepositorioImovelTarifaSocialHBM;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaPK;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoHistorico;
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoHistorico;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.medicao.FiltroMedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
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
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * < <Descrição da Classe>>
 * 
 * @author rodrigo
 */

public class ControladorTarifaSocialSEJB implements SessionBean {

	private static final long serialVersionUID = 1L;

	private IRepositorioImovelTarifaSocial repositorioImovelTarifaSocial = null;

	SessionContext sessionContext;

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {

		repositorioImovelTarifaSocial = RepositorioImovelTarifaSocialHBM
				.getInstancia();
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
	 * Faz verificações da inserção de dados de tarifa social de um imóvel
	 * 
	 * [UC00054] Inserir Tarifa Social
	 * 
	 * [FS0004] Verificar o cliente proprietario do imovel
	 * 
	 * @param idImovel
	 *            Código do Imóvel
	 * @throws ControladorException
	 */
	public void verificarProprietarioImovel(Integer idImovel)
			throws ControladorException {

		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		// Objetos que serão retornados pelo hibernate
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");

		/*
		 * filtroClienteImovel
		 * .adicionarCaminhoParaCarregamentoEntidade("cliente.rg");
		 * filtroClienteImovel
		 * .adicionarCaminhoParaCarregamentoEntidade("cliente.cpf");
		 */

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID, idImovel));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.FIM_RELACAO_MOTIVO));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
				ClienteRelacaoTipo.PROPRIETARIO));

		// Realiza uma pesquisa pelos parametros fornecidos
		Collection clienteImoveis = getControladorUtil().pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());

		// verifica se existe cliente proprietário
		if (clienteImoveis == null || clienteImoveis.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.tarifa_social.obrigatoria.existente.proprietario");
		}

		// Iterator para percorrer a coleção de ClienteImoveis
		Iterator clienteImovelIterator = clienteImoveis.iterator();

		// Objeto ClienteImovel
		ClienteImovel clienteImovelObj = new ClienteImovel();

		while (clienteImovelIterator.hasNext()) {

			clienteImovelObj = (ClienteImovel) clienteImovelIterator.next();

			Cliente cliente = clienteImovelObj.getCliente();

			if ((cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica()
					.shortValue() == 1)
					&& ((clienteImovelObj.getCliente().getRg() == null || clienteImovelObj
							.getCliente().getRg().equalsIgnoreCase(""))

					&& (clienteImovelObj.getCliente().getCpf() == null || clienteImovelObj
							.getCliente().getCpf().equalsIgnoreCase("")))) {
				throw new ControladorException(
						"atencao.proprietario.rg_cpf_nao_cadastrado");
			}

			if (cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica()
					.shortValue() == 2
					&& cliente.getCnpj() == null) {
				throw new ControladorException(
						"atencao.proprietario.cnpj_nao_cadastrado");
			}

		}

	}

	/**
	 * Faz verificações da inserção de dados de tarifa social de um imóvel
	 * 
	 * @param idImovel
	 *            Código do Imóvel
	 * @throws ControladorException
	 */
	public Cliente verificarUsuarioImovel(Integer idImovel)
			throws ControladorException {
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		// Objetos que serão retornados pelo hibernate
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID, idImovel));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.FIM_RELACAO_MOTIVO));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
				ClienteRelacaoTipo.USUARIO));

		// Realiza uma pesquisa pelos parametros fornecidos
		Collection clienteImoveis = getControladorUtil().pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());

		// Iterator para percorrer a coleção de ClienteImoveis
		Iterator clienteImovelIterator = clienteImoveis.iterator();

		// Objeto ClienteImovel
		ClienteImovel clienteImovelObj = new ClienteImovel();

		clienteImovelObj = (ClienteImovel) clienteImovelIterator.next();

		Cliente cliente = clienteImovelObj.getCliente();

		if ((cliente.getRg() == null || clienteImovelObj.getCliente().getRg()
				.equalsIgnoreCase(""))
				&& (cliente.getCpf() == null || clienteImovelObj.getCliente()
						.getCpf().equalsIgnoreCase(""))) {

			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.tarifa_social.obrigatoria.existente.usuario.tenham.cpf_rg");
		}

		return cliente;
	}

	/**
	 * Método que verifica se o usuario esta cadastrado em outro imovel que
	 * esteja na tarifa social e verifica se ja esta cadastrado como usuario de
	 * algum imovel economia,
	 * 
	 * Caso o idImovel seja diferente de nula ele verifa se o usuario esta
	 * cadastrado num imovel diferente do id passado.
	 * 
	 * Caso o idImovelEconomia seja diferente de nula ele verifaca se o usuario
	 * esta cadastrado num imovel economia do idImovelEconomia passado.
	 * 
	 * @param idImovel
	 * @param idImovelEconomia
	 * @param idEconomiaAtual
	 * @param idClienteUsuario
	 */
	public void verificarClienteUsuarioEmOutroEconomia(Integer idImovel,
			Integer idImovelEconomia, Integer idClienteUsuario)
			throws ControladorException {

		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		// Adiciona novos parametros para pesquisa de usuarios existente em
		// tarifa social, na tabela CLIENTE_IMOVEL
		if (idImovel != null) {
			filtroClienteImovel
					.adicionarParametro(new ParametroSimplesDiferenteDe(
							FiltroClienteImovel.IMOVEL_ID, idImovel));
		}
		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_PERFIL, ImovelPerfil.TARIFA_SOCIAL));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_ID, idClienteUsuario));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
				ClienteRelacaoTipo.USUARIO));
		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.FIM_RELACAO_MOTIVO));
		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.DATA_FIM_RELACAO));

		// Carrega a coleção com os registros encontrados
		Collection clienteImoveis = getControladorUtil().pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());

		if (clienteImoveis != null && !clienteImoveis.isEmpty()) {
			// caso ele ja seja usuario de algum imovel
			ClienteImovel clienteImovelObj = (ClienteImovel) clienteImoveis
					.iterator().next();
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.usuario.ja_cadastrado_tarifasocial", null,
					clienteImovelObj.getImovel().getId().toString());
		}

		// Instancia do filtro de ClienteImovelEconomia
		FiltroClienteImovelEconomia filtroClienteImovelEconomia = new FiltroClienteImovelEconomia();

		// Adiciona novos parametros para pesquisa de usuarios existente em
		// tarifa social, na tabela CLIENTE_IMOVEL_ECONOMIA
		if (idImovelEconomia != null) {
			filtroClienteImovelEconomia
					.adicionarParametro(new ParametroSimplesDiferenteDe(
							FiltroClienteImovelEconomia.ID, idImovelEconomia));
		}

		filtroClienteImovelEconomia
				.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.IMOVEL);

		filtroClienteImovelEconomia.adicionarParametro(new ParametroSimples(
				FiltroClienteImovelEconomia.IMOVEL_PERFIL,
				ImovelPerfil.TARIFA_SOCIAL));
		filtroClienteImovelEconomia.adicionarParametro(new ParametroSimples(
				FiltroClienteImovelEconomia.CLIENTE_ID, idClienteUsuario));
		filtroClienteImovelEconomia.adicionarParametro(new ParametroSimples(
				FiltroClienteImovelEconomia.CLIENTE_RELACAO_TIPO,
				ClienteRelacaoTipo.USUARIO));
		filtroClienteImovelEconomia.adicionarParametro(new ParametroNulo(
				FiltroClienteImovelEconomia.FIM_RELACAO_MOTIVO));
		filtroClienteImovelEconomia.adicionarParametro(new ParametroNulo(
				FiltroClienteImovelEconomia.DATA_FIM_RELACAO));

		// Coleção de Cliente_Imovel_Economia
		Collection clienteImovelEconomias = getControladorUtil().pesquisar(
				filtroClienteImovelEconomia,
				ClienteImovelEconomia.class.getName());

		if (clienteImovelEconomias != null && !clienteImovelEconomias.isEmpty()) {

			ClienteImovelEconomia clienteImovelEconomiaObj = (ClienteImovelEconomia) clienteImovelEconomias
					.iterator().next();

			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.usuario.ja_cadastrado_tarifasocial", null,
					clienteImovelEconomiaObj.getImovelEconomia()
							.getImovelSubcategoria().getComp_id().getImovel()
							.getId().toString());
		}

	}

	/**
	 * Verificar os pré-requisitos para o cadastramento de um imóvel na tarifa
	 * social
	 * 
	 * @param idImovel
	 *            Código do imovel
	 * @throws ControladorException
	 */
	public String[] verificarPreRequisitosCadastramentoTarifaSocial(
			Integer idImovel) throws ControladorException {

		String[] retorno = new String[2];

		// Procura o Imóvel para fazer as verificações
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("quadra.rota.faturamentoGrupo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");

		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				idImovel));
		Imovel imovel = (Imovel) ((List) getControladorUtil().pesquisar(
				filtroImovel, Imovel.class.getName())).get(0);

		// CATEGORIA RESIDENCIAL - E
		// Verificar se o imóvel possui alguma categoria que não seja
		// RESIDENCIAL
		FiltroImovelSubCategoria filtroImovelSubcategoria = new FiltroImovelSubCategoria();
		filtroImovelSubcategoria.adicionarParametro(new ParametroSimples(
				FiltroImovelSubCategoria.IMOVEL_ID, idImovel));
		filtroImovelSubcategoria
				.adicionarParametro(new ParametroSimplesDiferenteDe(
						FiltroImovelSubCategoria.SUBCATEGORIA_CATEGORIA_ID,
						Categoria.RESIDENCIAL));
		Collection imovelCategoriasNaoResidencial = getControladorUtil()
				.pesquisar(filtroImovelSubcategoria,
						ImovelSubcategoria.class.getName());

		// CATEGORIA RESIDENCIAL - E
		if (!imovelCategoriasNaoResidencial.isEmpty()) {
			retorno[0] = "1";
			return retorno;
		}

		// SUBCATEGORIA CADA DE VERANEIO - E
		// Verificar se o imóvel possui alguma categoria que não seja
		// SUBCATEGORIA CADA DE VERANEIO - E
		filtroImovelSubcategoria = new FiltroImovelSubCategoria();
		filtroImovelSubcategoria.limparListaParametros();
		filtroImovelSubcategoria.adicionarParametro(new ParametroSimples(
				FiltroImovelSubCategoria.IMOVEL_ID, idImovel));
		filtroImovelSubcategoria.adicionarParametro(new ParametroSimples(
				FiltroImovelSubCategoria.SUBCATEGORIA_CATEGORIA_ID,
				Categoria.RESIDENCIAL));
		filtroImovelSubcategoria.adicionarParametro(new ParametroSimples(
				FiltroImovelSubCategoria.CODIGO_TARIFA_SOCIAL, "E"));

		filtroImovelSubcategoria
				.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelSubCategoria.SUBCATEGORIA);

		imovelCategoriasNaoResidencial = getControladorUtil().pesquisar(
				filtroImovelSubcategoria, ImovelSubcategoria.class.getName());

		// SUBCATEGORIA CADA DE VERANEIO - E
		if (!imovelCategoriasNaoResidencial.isEmpty()) {
			retorno[0] = "2";

			retorno[1] = "";

			Iterator imovelCategoriasNaoResidencialIterator = imovelCategoriasNaoResidencial
					.iterator();

			while (imovelCategoriasNaoResidencialIterator.hasNext()) {

				ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) imovelCategoriasNaoResidencialIterator
						.next();

				if (!retorno[1].equals("")) {
					retorno[1] = retorno[1]
							+ ", "
							+ imovelSubcategoria.getComp_id().getSubcategoria()
									.getDescricao();
				} else {
					retorno[1] = imovelSubcategoria.getComp_id()
							.getSubcategoria().getDescricao();
				}

			}

			return retorno;
		}

		boolean existeMedicaoHistoricoMesAtual = false;
		Integer anoMesReferenciaFaturamentoAnterior = null;
		Integer anoMesReferenciaFaturamento = null;

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;

		FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
		filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAgua.ID, imovel.getId()));
		filtroLigacaoAgua
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico");

		Collection colecaoLigaAgua = getControladorUtil().pesquisar(
				filtroLigacaoAgua, LigacaoAgua.class.getName());

		LigacaoAgua ligacaoAgua = null;
		if (colecaoLigaAgua != null && !colecaoLigaAgua.isEmpty()) {
			ligacaoAgua = (LigacaoAgua) (colecaoLigaAgua).iterator().next();
		}

		if (ligacaoAgua != null) {
			hidrometroInstalacaoHistorico = ligacaoAgua
					.getHidrometroInstalacaoHistorico();
		}

		// Pesquisa a categoria Residencial
		FiltroCategoria filtroCategoria = new FiltroCategoria();

		filtroCategoria.adicionarParametro(new ParametroSimples(
				FiltroCategoria.CODIGO, Categoria.RESIDENCIAL));

		Categoria categoriaResidencial = (Categoria) ((List) getControladorUtil()
				.pesquisar(filtroCategoria, Categoria.class.getName())).get(0);

		// CONSUMO MÉDIO MAIOR QUE 10M3 - E
		// Hidrômetro na ligação
		if (hidrometroInstalacaoHistorico != null) {

			// caso não tenha medicao historico no mes atual
			if (!existeMedicaoHistoricoMesAtual) {

				// Monta o Medicao Tipo para obterConsumoMedioHidrometro
				MedicaoTipo medicaoTipoLigacaoAgua = new MedicaoTipo();

				medicaoTipoLigacaoAgua.setId(MedicaoTipo.LIGACAO_AGUA);

				Collection consumoAtual = null;

				try {
					consumoAtual = repositorioImovelTarifaSocial
							.pesquisarConsumoHistoricoImovel(imovel.getId());
				} catch (ErroRepositorioException ex) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", ex);
				}

				if (consumoAtual != null && !consumoAtual.isEmpty()) {
					Iterator iterator = consumoAtual.iterator();

					while (iterator.hasNext()) {
						ConsumoHistorico consumoHistorico = (ConsumoHistorico) iterator
								.next();
						// Tarifa social só é permitida para imóveis com consumo
						// médio por
						// economia dos últimos seis meses inferior a xm3
						 
						if (new BigDecimal(categoriaResidencial
								.getConsumoMinimo().intValue())
								.compareTo(new BigDecimal(consumoHistorico
										.getConsumoMedio().intValue())
										.divide(new BigDecimal(imovel
												.getQuantidadeEconomias()
												.intValue()),BigDecimal.ROUND_UP)) < 0) {
							
							sessionContext.setRollbackOnly();

							retorno[0] = "8";

							retorno[1] = categoriaResidencial
									.getConsumoMinimo().toString();

							return retorno;
						}
					}

				}

			}

		}

		// Consumo Fixado Maior que xm³
		Integer consumoFixado = getControladorLigacaoAgua()
				.pesquisarConsumoMinimoFixado(imovel.getId());

		if (consumoFixado != null) {

			// Tarifa social só é permitida para imóveis com consumo
			// mínimo fixado inferior a xm3
			if (new BigDecimal(categoriaResidencial.getConsumoMinimo()
					.intValue()).compareTo(new BigDecimal(consumoFixado
					.intValue()).divide(new BigDecimal(imovel
					.getQuantidadeEconomias().intValue()))) < 0) {
				sessionContext.setRollbackOnly();

				retorno[0] = "9";

				retorno[1] = categoriaResidencial.getConsumoMinimo().toString();

				return retorno;
			}
		}

		// SUBCATEGORIA IGREJA, CHAFARIZ, TERRENO - T
		// Verificar se o imóvel possui alguma categoria que não seja
		// SUBCATEGORIA IGREJA, CHAFARIZ, TERRENO - T
		filtroImovelSubcategoria = new FiltroImovelSubCategoria();
		filtroImovelSubcategoria.limparListaParametros();
		filtroImovelSubcategoria.adicionarParametro(new ParametroSimples(
				FiltroImovelSubCategoria.IMOVEL_ID, idImovel));
		filtroImovelSubcategoria
				.adicionarCaminhoParaCarregamentoEntidade("comp_id.subcategoria");
		filtroImovelSubcategoria.adicionarParametro(new ParametroSimples(
				FiltroImovelSubCategoria.SUBCATEGORIA_CATEGORIA_ID,
				Categoria.RESIDENCIAL));
		filtroImovelSubcategoria.adicionarParametro(new ParametroSimples(
				FiltroImovelSubCategoria.CODIGO_TARIFA_SOCIAL, "T"));

		imovelCategoriasNaoResidencial = getControladorUtil().pesquisar(
				filtroImovelSubcategoria, ImovelSubcategoria.class.getName());

		// SUBCATEGORIA IGREJA, CHAFARIZ, TERRENO - T
		if (!imovelCategoriasNaoResidencial.isEmpty()) {
			retorno[0] = "3";

			retorno[1] = "";

			Iterator imovelCategoriasNaoResidencialIterator = imovelCategoriasNaoResidencial
					.iterator();

			while (imovelCategoriasNaoResidencialIterator.hasNext()) {

				ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) imovelCategoriasNaoResidencialIterator
						.next();

				if (!retorno[1].equals("")) {
					retorno[1] = retorno[1]
							+ ", "
							+ imovelSubcategoria.getComp_id().getSubcategoria()
									.getDescricao();
				} else {
					retorno[1] = imovelSubcategoria.getComp_id()
							.getSubcategoria().getDescricao();
				}

			}

			return retorno;
		}

		// PERFIL GRANDE CONSUMIDOR
		if (imovel.getImovelPerfil().getId().intValue() == ImovelPerfil.GRANDE
				.intValue()) {

			retorno[0] = "4";

			return retorno;
		}

		// Verificar se a situação da ligação de água do imóvel não seja ligada
		// ou cortadas
		if (!(imovel.getLigacaoAguaSituacao().getIndicadorFaturamentoSituacao().equals(LigacaoAguaSituacao.FATURAMENTO_ATIVO))
				&& (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.SUPRIMIDO
						.intValue())
				&& (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.SUPR_PARC
						.intValue())
				&& (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.SUPR_PARC_PEDIDO
						.intValue())) {

			retorno[0] = "5";

			return retorno;
		}

		// ANORMALIDADE DE LEITURA - T
		// Hidrômetro na ligação
		if (hidrometroInstalacaoHistorico != null) {
			// Anormalidade de leitura

			anoMesReferenciaFaturamento = imovel.getQuadra().getRota()
					.getFaturamentoGrupo().getAnoMesReferencia();

			// Setar o mês anterior da referência
			anoMesReferenciaFaturamentoAnterior = Util
					.subtrairData(anoMesReferenciaFaturamento);

			FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();

			// Objetos que serão retornados pelo hibernate
			filtroMedicaoHistorico
					.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeFaturamento");

			filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistorico.LIGACAO_AGUA_ID, imovel.getId()));

			filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistorico.MEDICAO_TIPO_ID,
					MedicaoTipo.LIGACAO_AGUA));

			filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO,
					anoMesReferenciaFaturamento, ConectorOr.CONECTOR_OR, 2));

			filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO,
					anoMesReferenciaFaturamentoAnterior));

			Collection medicao = (Collection) getControladorUtil().pesquisar(
					filtroMedicaoHistorico, MedicaoHistorico.class.getName());

			if (medicao != null && !medicao.isEmpty()) {
				Iterator iterator = medicao.iterator();

				while (iterator.hasNext()) {
					MedicaoHistorico medicaoHistorico = (MedicaoHistorico) iterator
							.next();
					existeMedicaoHistoricoMesAtual = true;
					if (medicaoHistorico.getLeituraAnormalidadeFaturamento() != null) {
						// Foi detectada anomalidade

						if (medicaoHistorico
								.getLeituraAnormalidadeFaturamento()
								.getIndicadorPerdaTarifaSocial().intValue() == LeituraAnormalidade.INDICADOR_PERDA_TARIFA_SOCIAL
								.intValue()) {

							retorno[0] = "6";

							retorno[1] = medicaoHistorico
									.getLeituraAnormalidadeFaturamento()
									.getDescricao();

							return retorno;
						}
					}
				}
			}
		}

		// data inicio vencimento debito
//		Calendar dataInicioVencimentoDebito = new GregorianCalendar();
//		dataInicioVencimentoDebito.set(Calendar.YEAR, new Integer("0001")
//				.intValue());
//		dataInicioVencimentoDebito.set(Calendar.MONTH, 0);
//		dataInicioVencimentoDebito.set(Calendar.DATE, new Integer("01")
//				.intValue());
//
//		// data final de vencimento de debito
//		Calendar dataFimVencimentoDebito = new GregorianCalendar();
//		dataFimVencimentoDebito.add(Calendar.DATE, -45);
//
//		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
//
//		// Objetos que serão retornados pelo hibernate
//		filtroClienteImovel
//				.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
//		/*
//		 * filtroClienteImovel
//		 * .adicionarCaminhoParaCarregamentoEntidade("cliente.rg");
//		 * filtroClienteImovel
//		 * .adicionarCaminhoParaCarregamentoEntidade("cliente.cpf");
//		 */
//		filtroClienteImovel.adicionarParametro(new ParametroSimples(
//				FiltroClienteImovel.IMOVEL_ID, idImovel));
//
//		filtroClienteImovel.adicionarParametro(new ParametroSimples(
//				FiltroClienteImovel.INDICADOR_USO,
//				ConstantesSistema.INDICADOR_USO_ATIVO));
//
//		filtroClienteImovel.adicionarParametro(new ParametroNulo(
//				FiltroClienteImovel.FIM_RELACAO_MOTIVO));
//		filtroClienteImovel.adicionarParametro(new ParametroSimples(
//				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
//				ClienteRelacaoTipo.USUARIO));
//
//		// Realiza uma pesquisa pelos parametros fornecidos
//		Collection clienteImoveis = getControladorUtil().pesquisar(
//				filtroClienteImovel, ClienteImovel.class.getName());
//
//		// Iterator para percorrer a coleção de ClienteImoveis
//		Iterator clienteImovelIterator = clienteImoveis.iterator();
//
//		// Objeto ClienteImovel
//		ClienteImovel clienteImovelObj = new ClienteImovel();
//		clienteImovelObj = (ClienteImovel) clienteImovelIterator.next();
//		Cliente cliente = clienteImovelObj.getCliente();
//
//		ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = getControladorCobranca()
//				.obterDebitoImovelOuCliente(2, null,
//						cliente.getId().toString(), null, "000101", "999912",
//						dataInicioVencimentoDebito.getTime(),
//						dataFimVencimentoDebito.getTime(), 1, 2, 2, 2, 1, 1, 2,
//						null);
//
//		boolean existeDebito = false;
//		if (obterDebitoImovelOuClienteHelper != null) {
//			// contas
//			if (obterDebitoImovelOuClienteHelper.getColecaoContasValores() != null
//					&& !obterDebitoImovelOuClienteHelper
//							.getColecaoContasValores().isEmpty()) {
//				existeDebito = true;
//			} else
//			// credito a realizar
//			if (obterDebitoImovelOuClienteHelper.getColecaoCreditoARealizar() != null
//					&& !obterDebitoImovelOuClienteHelper
//							.getColecaoCreditoARealizar().isEmpty()) {
//				existeDebito = true;
//			} else
//			// debito a cobrar
//			if (obterDebitoImovelOuClienteHelper.getColecaoDebitoACobrar() != null
//					&& !obterDebitoImovelOuClienteHelper
//							.getColecaoDebitoACobrar().isEmpty()) {
//				existeDebito = true;
//			} else
//			// guias pagamento
//			if (obterDebitoImovelOuClienteHelper
//					.getColecaoGuiasPagamentoValores() != null
//					&& !obterDebitoImovelOuClienteHelper
//							.getColecaoGuiasPagamentoValores().isEmpty()) {
//				existeDebito = true;
//			}
//
//		}
//
//		// Se existir debito para o imovel
//		if (existeDebito) {
//			retorno[0] = "7";
//
//			return retorno;
//		}
//
//		FiltroImovelEconomia filtroImovelEconomia = new FiltroImovelEconomia();
//		filtroImovelEconomia.adicionarParametro(new ParametroSimples(
//				FiltroImovelEconomia.IMOVEL_ID, idImovel));
//		// filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.);
//
//		// Realiza uma pesquisa pelos parametros fornecidos
//		Collection colecaoImovelEconomia = getControladorUtil().pesquisar(
//				filtroImovelEconomia, ImovelEconomia.class.getName());
//
//		if (colecaoImovelEconomia != null && !colecaoImovelEconomia.isEmpty()) {
//
//			ImovelEconomia imovelEconomia = (ImovelEconomia) colecaoImovelEconomia
//					.iterator().next();
//
//			FiltroClienteImovelEconomia filtroClienteImovelEconomia = new FiltroClienteImovelEconomia();
//			filtroClienteImovelEconomia
//					.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.CLIENTE);
//
//			filtroClienteImovelEconomia
//					.adicionarParametro(new ParametroSimples(
//							FiltroClienteImovelEconomia.IMOVEL_ECONOMIA_ID,
//							imovelEconomia.getId()));
//
//			filtroClienteImovelEconomia
//					.adicionarParametro(new ParametroSimples(
//							FiltroClienteImovelEconomia.CLIENTE_RELACAO_TIPO,
//							ClienteRelacaoTipo.USUARIO));
//
//			filtroClienteImovelEconomia.adicionarParametro(new ParametroNulo(
//					FiltroClienteImovelEconomia.DATA_FIM_RELACAO));
//
//			// Coleção de Cliente_Imovel_Economia
//			Collection colecaoClienteImovelEconomias = getControladorUtil()
//					.pesquisar(filtroClienteImovelEconomia,
//							ClienteImovelEconomia.class.getName());
//
//			if (colecaoClienteImovelEconomias != null
//					&& !colecaoClienteImovelEconomias.isEmpty()) {
//
//				ClienteImovelEconomia clienteImovelEconomia = (ClienteImovelEconomia) colecaoClienteImovelEconomias
//						.iterator().next();
//				cliente = clienteImovelEconomia.getCliente();
//				obterDebitoImovelOuClienteHelper = getControladorCobranca()
//						.obterDebitoImovelOuCliente(2, null,
//								cliente.getId().toString(), null, "000101",
//								"999912", dataInicioVencimentoDebito.getTime(),
//								dataFimVencimentoDebito.getTime(), 1, 2, 2, 2,
//								1, 1, 2, null);
//
//				existeDebito = false;
//				if (obterDebitoImovelOuClienteHelper != null) {
//					// contas
//					if (obterDebitoImovelOuClienteHelper
//							.getColecaoContasValores() != null
//							&& !obterDebitoImovelOuClienteHelper
//									.getColecaoContasValores().isEmpty()) {
//						existeDebito = true;
//					} else
//					// credito a realizar
//					if (obterDebitoImovelOuClienteHelper
//							.getColecaoCreditoARealizar() != null
//							&& !obterDebitoImovelOuClienteHelper
//									.getColecaoCreditoARealizar().isEmpty()) {
//						existeDebito = true;
//					} else
//					// debito a cobrar
//					if (obterDebitoImovelOuClienteHelper
//							.getColecaoDebitoACobrar() != null
//							&& !obterDebitoImovelOuClienteHelper
//									.getColecaoDebitoACobrar().isEmpty()) {
//						existeDebito = true;
//					} else
//					// guias pagamento
//					if (obterDebitoImovelOuClienteHelper
//							.getColecaoGuiasPagamentoValores() != null
//							&& !obterDebitoImovelOuClienteHelper
//									.getColecaoGuiasPagamentoValores()
//									.isEmpty()) {
//						existeDebito = true;
//					}
//				}
//
//				// Se existir debito para o imovel
//				if (existeDebito) {
//					retorno[0] = "7";
//
//					return retorno;
//				}
//			}
//		}

		// caso não ocorra nenhum situação
		retorno[0] = "-1";

		return retorno;
	}

	/**
	 * Atualiza um cliente no sistema
	 * 
	 * @param tarifaSocialCartaoTipo
	 *            Descrição do parâmetro
	 * @throws ControladorException
	 */
	public void atualizarTarifaSocialCartaoTipo(
			TarifaSocialCartaoTipo tarifaSocialCartaoTipo)
			throws ControladorException {

		// Cria o filtro
		FiltroTarifaSocialCartaoTipo filtroTarifaSocialCartaoTipo = new FiltroTarifaSocialCartaoTipo();

		// -------------Parte que atualiza um bairro na
		// base----------------------

		// Parte de Validacao com Timestamp

		// Seta o filtro para buscar o cliente na base
		filtroTarifaSocialCartaoTipo
				.adicionarParametro(new ParametroSimples(
						FiltroTarifaSocialCartaoTipo.ID, tarifaSocialCartaoTipo
								.getId()));

		// Procura o filtro na base
		TarifaSocialCartaoTipo tarifaSocialCartaoTipoNaBase = (TarifaSocialCartaoTipo) ((List) (getControladorUtil()
				.pesquisar(filtroTarifaSocialCartaoTipo,
						TarifaSocialCartaoTipo.class.getName()))).get(0);

		// Verificar se o cliente já foi atualizado por outro usuário
		// durante
		// esta atualização
		if (tarifaSocialCartaoTipoNaBase.getUltimaAlteracao().after(
				tarifaSocialCartaoTipo.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		// Atualiza a data de última alteração
		tarifaSocialCartaoTipo.setUltimaAlteracao(new Date());

		// Atualiza o cliente
		getControladorUtil().atualizar(tarifaSocialCartaoTipo);

		// -------------Fim da parte que atualiza um cliente na
		// base---------------
	}

	/**
	 * Atualiza um tarifaSocialDadoEconomia
	 * 
	 * @param tarifaSocialDadoEconomia
	 *            Descrição do parâmetro
	 * @throws ControladorException
	 */
	public void atualizarTarifaSocialDadoEconomia(
			TarifaSocialDadoEconomia tarifaSocialDadoEconomia)
			throws ControladorException {

		// Cria o filtro
		FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = new FiltroTarifaSocialDadoEconomia();

		// -------------Parte que atualiza um bairro na
		// base----------------------

		// Parte de Validacao com Timestamp

		// Seta o filtro para buscar o cliente na base
		filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroSimples(
				FiltroTarifaSocialDadoEconomia.ID, tarifaSocialDadoEconomia
						.getId()));

		// Procura o filtro na base
		TarifaSocialDadoEconomia tarifaSocialDadoEconomiaNaBase = (TarifaSocialDadoEconomia) ((List) (getControladorUtil()
				.pesquisar(filtroTarifaSocialDadoEconomia,
						TarifaSocialDadoEconomia.class.getName()))).get(0);

		// Verificar se o registro já foi atualizado por outro usuário
		// durante
		// esta atualização
		if (tarifaSocialDadoEconomiaNaBase.getUltimaAlteracao().after(
				tarifaSocialDadoEconomia.getUltimaAlteracao())) {
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		// Atualiza a data de última alteração
		tarifaSocialDadoEconomia.setUltimaAlteracao(new Date());

		// Atualiza o cliente
		getControladorUtil().atualizar(tarifaSocialDadoEconomia);

		// -------------Fim da parte que atualiza um cliente na
		// base---------------
	}

	/**
	 * Verificar o preenchimento dos campos para uma economia
	 * 
	 * @param clienteImovel
	 *            Descrição do parâmetro
	 * @param numeroCartaoSocial
	 *            Descrição do parâmetro
	 * @param dataValidadeCartaoSocial
	 *            Descrição do parâmetro
	 * @param numeroParcelasCartaoSocial
	 *            Descrição do parâmetro
	 * @param valorRendaFamiliar
	 *            Descrição do parâmetro
	 * @throws ControladorException
	 */
	public String[] verificarPreenchimentoInserirDadosTarifaSocial(
			Long numeroCelpe, BigDecimal areaConstruida, BigDecimal numeroIPTU,
			Integer idImovel, String numeroCartaoSocial,
			String dataValidadeCartaoSocial, String numeroParcelasCartaoSocial,
			Integer consumoMedio, BigDecimal valorRendaFamiliar,
			String tarifaSocialCartaoTipo, String tipoRenda)
			throws ControladorException {

		String[] retorno = new String[2];

		/*
		 * Long numeroCelpe = clienteImovel.getImovel().getNumeroCelpe();
		 * BigDecimal areaConstruida =
		 * clienteImovel.getImovel().getAreaConstruida(); BigDecimal numeroIPTU =
		 * clienteImovel.getImovel().getNumeroIptu(); Integer idImovel =
		 * clienteImovel.getImovel().getId();
		 */
		FiltroTarifaSocialCartaoTipo filtroTarifaSocialCartaoTipo = new FiltroTarifaSocialCartaoTipo();
		boolean dadosCartaoSocialPreenchidos = false;

		// Verifica se o usuário deixou de informar os dados do Cartão do
		// Programa Social
		if ((numeroCartaoSocial != null && !numeroCartaoSocial.trim()
				.equals(""))
				|| (numeroParcelasCartaoSocial != null && !numeroParcelasCartaoSocial
						.trim().equals(""))
				|| (dataValidadeCartaoSocial != null && !dataValidadeCartaoSocial
						.trim().equals(""))
				|| (tarifaSocialCartaoTipo != null && !tarifaSocialCartaoTipo
						.equals(String
								.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)))) {

			if (numeroCartaoSocial == null
					|| numeroCartaoSocial.trim().equals("")) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.preencher.tarifa_social.numero");
			} else if (tarifaSocialCartaoTipo == null
					|| tarifaSocialCartaoTipo.equals(String
							.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.preencher.tarifa_social.tipo_cartao");
			} else {

				filtroTarifaSocialCartaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroTarifaSocialCartaoTipo.ID,
								tarifaSocialCartaoTipo));

				Collection colecaoTarifaSocialCartaoTipo = getControladorUtil()
						.pesquisar(filtroTarifaSocialCartaoTipo,
								TarifaSocialCartaoTipo.class.getName());

				TarifaSocialCartaoTipo objetoCartaTipo = null;
				if (Util.isVazioOrNulo(colecaoTarifaSocialCartaoTipo)) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.pesquisa.tarifa_social.tipo_cartao.inexistente");
				}
				objetoCartaTipo = (TarifaSocialCartaoTipo) Util
						.retonarObjetoDeColecao(colecaoTarifaSocialCartaoTipo);

				if ((dataValidadeCartaoSocial == null || dataValidadeCartaoSocial
						.trim().equals(""))
						&& objetoCartaTipo
								.getIndicadorValidade()
								.equals(
										TarifaSocialCartaoTipo.INDICADOR_EXISTENCIA_VALIDADE_SIM)) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.preencher.tarifa_social.data.validade");
				} else if ((numeroParcelasCartaoSocial == null || numeroParcelasCartaoSocial
						.trim().equals(""))
						&& objetoCartaTipo.getNumeroMesesAdesao() != null) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.preencher.tarifa_social.numero.parcelas");
				} else {
					dadosCartaoSocialPreenchidos = true;
				}
				
			}
		}

		// Verifica o preenchimento dos campos referentes a renda familiar
		if (dadosCartaoSocialPreenchidos) {
			if (valorRendaFamiliar.intValue() != 0
					|| (tipoRenda != null && !tipoRenda.equals(String
							.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)))) {

				if (valorRendaFamiliar.intValue() == 0) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.preencher.tarifa_social.valor_renda_familiar");
				} else if (tipoRenda == null
						|| tipoRenda
								.equals(String
										.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.preencher.tarifa_social.tipo_renda");
				}

			}
		} else {

			if (valorRendaFamiliar.intValue() == 0) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.preencher.tarifa_social.valor_renda_familiar");
			} else if (tipoRenda == null
					|| tipoRenda.equals(String
							.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.preencher.tarifa_social.tipo_renda");
			}

		}

		// ==========================================================
		// NOVA VERSÃO
		// ==========================================================

		SistemaParametro sistemaParametro = getControladorUtil()
				.pesquisarParametrosDoSistema();

		// Valor da Renda
		// Caso o valor da renda tenha sido informado e seja maior que um
		// salário mínimo
		if (valorRendaFamiliar.doubleValue() > sistemaParametro
				.getValorSalarioMinimo().doubleValue()) {

			retorno[0] = "10";
			retorno[1] = Util.formatarMoedaReal(sistemaParametro
					.getValorSalarioMinimo());

			return retorno;

			// sessionContext.setRollbackOnly();
			// throw new ControladorException(
			// "atencao.tarifa_social.renda_familiar.maior.salario_minimo",
			// null, Util.formatarMoedaReal(sistemaParametro
			// .getValorSalarioMinimo()));
		}

		// [FS0015] - Verificar informação do número do contrato da companhia
		// elétrica
		if (consumoMedio != null && consumoMedio != 0 && numeroCelpe == null) {

			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.preencher.tarifa_social.numero_contrato_celpe");
		}

		// Consumo Energia
		/*
		 * Caso o consumo médio de energia do imóvel ou da economia do imóvel
		 * seja superior ao consumo médio de energia permitido para o
		 * cadastramento na tarifa sociual
		 */
		if (consumoMedio != null
				&& consumoMedio != 0
				&& consumoMedio > sistemaParametro
						.getConsumoEnergiaMaximoTarifaSocial().intValue()) {

			retorno[0] = "11";
			retorno[1] = sistemaParametro.getConsumoEnergiaMaximoTarifaSocial()
					+ "";

			return retorno;
			// sessionContext.setRollbackOnly();
			// throw new ControladorException(
			// "atencao.preencher.tarifa_social.valor.consumo_energia",
			// null, ""
			// + sistemaParametro
			// .getConsumoEnergiaMaximoTarifaSocial());
		}

		// 1 - O usuário não informou os dados do cartão do programa social
		if (!dadosCartaoSocialPreenchidos) {

			if (valorRendaFamiliar.intValue() == 0) {

				retorno[0] = "12";
				retorno[1] = "" + sistemaParametro.getAreaMaximaTarifaSocial();

				return retorno;

				// sessionContext.setRollbackOnly();
				// throw new ControladorException(
				// "atencao.preencher.tarifa_social.requisitos", null, ""
				// + sistemaParametro.getAreaMaximaTarifaSocial());
			}
			// 2 - O valor da renda foi informado e o tipo é renda COMPROVADA
			else if (!RendaTipo.COMPROVADA.equals(new Integer(tipoRenda))) {

				/*
				 * 3 - O valor da renda foi informado e o tipo é renda DECLARADA
				 * e existir a informação da área construída sendo menor ou
				 * igual ao máximo permitido
				 */
				if (RendaTipo.DECLARADA.equals(new Integer(tipoRenda))
						&& (areaConstruida == null || areaConstruida.intValue() > sistemaParametro
								.getAreaMaximaTarifaSocial().intValue())) {

					// sessionContext.setRollbackOnly();
					// throw new ControladorException(
					// "atencao.preencher.tarifa_social.requisitos", null,
					// "" + sistemaParametro.getAreaMaximaTarifaSocial());
					retorno[0] = "12";
					retorno[1] = ""
							+ sistemaParametro.getAreaMaximaTarifaSocial();

					return retorno;
				}
			}

			/*
			 * Caso o usuário não informe os dados do cartão do programa social,
			 * o tipo de renda não corresponda a renda comprovada e não existam
			 * as informações número do IPTU e não exista uma ordem de serviço
			 * de vistoria associado ao RA de cadastramentona tarifa social para
			 * o imóvel
			 */
			if (!RendaTipo.COMPROVADA.equals(new Integer(tipoRenda))
					&& numeroIPTU == null) {

				// Collection colecaoOS = null;
				//
				// try {
				//
				// colecaoOS = repositorioImovelTarifaSocial
				// .verificarOSVistoriaImovel(idImovel);
				//
				// } catch (ErroRepositorioException ex) {
				// sessionContext.setRollbackOnly();
				// throw new ControladorException("erro.sistema", ex);
				// }
				//
				// if (colecaoOS == null || colecaoOS.isEmpty()) {
				//
				// sessionContext.setRollbackOnly();
				// throw new ControladorException(
				// "atencao.preencher.tarifa_social.valor.numero_celpe");
				//
				// }
			}
		} else {

			// [FS0014] - Verificar duplicidade do cartão do programa social
			Long numeroCartaoProgramaSocial = new Long(numeroCartaoSocial);
			TarifaSocialCartaoTipo objetoTarifaSocialCartaoTipo = new TarifaSocialCartaoTipo();
			objetoTarifaSocialCartaoTipo.setId(new Integer(
					tarifaSocialCartaoTipo));

			this.verificarDuplicidadeCartaoProgramaSocial(
					numeroCartaoProgramaSocial, objetoTarifaSocialCartaoTipo,
					idImovel);
		}

		retorno[0] = "9";
		retorno[1] = "";

		return retorno;

		// ==========================================================
		// FIM NOVA VERSÃO
		// ==========================================================

	}

	/**
	 * Verificar o preenchimento dos campos para uma economia
	 * 
	 * @param clienteImovel
	 *            Descrição do parâmetro
	 * @param numeroCartaoSocial
	 *            Descrição do parâmetro
	 * @param dataValidadeCartaoSocial
	 *            Descrição do parâmetro
	 * @param numeroParcelasCartaoSocial
	 *            Descrição do parâmetro
	 * @param valorRendaFamiliar
	 *            Descrição do parâmetro
	 * @throws ControladorException
	 */

	public String[] verificarPreenchimentoInserirDadosTarifaSocialMultiplas(
			Long numeroCelpe, BigDecimal areaConstruida, BigDecimal numeroIPTU,
			Integer idImovelEconomia, String numeroCartaoSocial,
			String dataValidadeCartaoSocial, String numeroParcelasCartaoSocial,
			Integer consumoMedio, BigDecimal valorRendaFamiliar,
			String tarifaSocialCartaoTipo, String tipoRenda)
			throws ControladorException {

		String[] retorno = new String[2];

		FiltroTarifaSocialCartaoTipo filtroTarifaSocialCartaoTipo = new FiltroTarifaSocialCartaoTipo();
		boolean dadosCartaoSocialPreenchidos = false;

		// Verifica se o usuário deixou de informar os dados do Cartão do
		// Programa Social
		if ((numeroCartaoSocial != null && !numeroCartaoSocial.trim()
				.equals(""))
				|| (numeroParcelasCartaoSocial != null && !numeroParcelasCartaoSocial
						.trim().equals(""))
				|| (dataValidadeCartaoSocial != null && !dataValidadeCartaoSocial
						.trim().equals(""))
				|| (tarifaSocialCartaoTipo != null && !tarifaSocialCartaoTipo
						.equals(String
								.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)))) {

			if (numeroCartaoSocial == null
					|| numeroCartaoSocial.trim().equals("")) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.preencher.tarifa_social.numero");
			} else if (tarifaSocialCartaoTipo == null
					|| tarifaSocialCartaoTipo.equals(String
							.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.preencher.tarifa_social.tipo_cartao");
			} else {

				filtroTarifaSocialCartaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroTarifaSocialCartaoTipo.ID,
								tarifaSocialCartaoTipo));

				Collection colecaoTarifaSocialCartaoTipo = getControladorUtil()
						.pesquisar(filtroTarifaSocialCartaoTipo,
								TarifaSocialCartaoTipo.class.getName());

				TarifaSocialCartaoTipo objetoCartaTipo = null;
				if (Util.isVazioOrNulo(colecaoTarifaSocialCartaoTipo)) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.pesquisa.tarifa_social.tipo_cartao.inexistente");
				}
				objetoCartaTipo = (TarifaSocialCartaoTipo) Util
						.retonarObjetoDeColecao(colecaoTarifaSocialCartaoTipo);

				if ((dataValidadeCartaoSocial == null || dataValidadeCartaoSocial
						.trim().equals(""))
						&& objetoCartaTipo
								.getIndicadorValidade()
								.equals(
										TarifaSocialCartaoTipo.INDICADOR_EXISTENCIA_VALIDADE_SIM)) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.preencher.tarifa_social.data.validade");
				} else if ((numeroParcelasCartaoSocial == null || numeroParcelasCartaoSocial
						.trim().equals(""))
						&& objetoCartaTipo.getNumeroMesesAdesao() != null) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.preencher.tarifa_social.numero.parcelas");
				} else {
					dadosCartaoSocialPreenchidos = true;
				}				
			}
		}

		// Verifica o preenchimento dos campos referentes a renda familiar
		if (dadosCartaoSocialPreenchidos) {
			if (valorRendaFamiliar.intValue() != 0
					|| (tipoRenda != null && !tipoRenda.equals(String
							.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)))) {

				if (valorRendaFamiliar.intValue() == 0) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.preencher.tarifa_social.valor_renda_familiar");
				} else if (tipoRenda == null
						|| tipoRenda
								.equals(String
										.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.preencher.tarifa_social.tipo_renda");
				}

			}
		} else {

			if (valorRendaFamiliar.intValue() == 0) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.preencher.tarifa_social.valor_renda_familiar");
			} else if (tipoRenda == null
					|| tipoRenda.equals(String
							.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.preencher.tarifa_social.tipo_renda");
			}

		}

		// ==========================================================
		// NOVA VERSÃO
		// ==========================================================

		SistemaParametro sistemaParametro = getControladorUtil()
				.pesquisarParametrosDoSistema();

		// Valor da Renda
		// Caso o valor da renda tenha sido informado e seja maior que um
		// salário mínimo
		if (valorRendaFamiliar.doubleValue() > sistemaParametro
				.getValorSalarioMinimo().doubleValue()) {

			retorno[0] = "10";
			retorno[1] = Util.formatarMoedaReal(sistemaParametro
					.getValorSalarioMinimo());

			return retorno;

		}

		// [FS0015] - Verificar informação do número do contrato da companhia
		// elétrica
		if (consumoMedio != null && consumoMedio != 0 && numeroCelpe == null) {

			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.preencher.tarifa_social.numero_contrato_celpe");
		}

		// Consumo Energia
		/*
		 * Caso o consumo médio de energia do imóvel ou da economia do imóvel
		 * seja superior ao consumo médio de energia permitido para o
		 * cadastramento na tarifa sociual
		 */
		if (consumoMedio != null
				&& consumoMedio != 0
				&& consumoMedio > sistemaParametro
						.getConsumoEnergiaMaximoTarifaSocial().intValue()) {

			retorno[0] = "11";
			retorno[1] = sistemaParametro.getConsumoEnergiaMaximoTarifaSocial()
					+ "";

			return retorno;

		}

		// 1 - O usuário não informou os dados do cartão do programa social
		if (!dadosCartaoSocialPreenchidos) {

			if (valorRendaFamiliar.intValue() == 0) {

				retorno[0] = "12";
				retorno[1] = "" + sistemaParametro.getAreaMaximaTarifaSocial();

				return retorno;

			}
			// 2 - O valor da renda foi informado e o tipo é renda COMPROVADA
			else if (!RendaTipo.COMPROVADA.equals(new Integer(tipoRenda))) {

				/*
				 * 3 - O valor da renda foi informado e o tipo é renda DECLARADA
				 * e existir a informação da área construída sendo menor ou
				 * igual ao máximo permitido
				 */
				if (RendaTipo.DECLARADA.equals(new Integer(tipoRenda))
						&& (areaConstruida == null || areaConstruida.intValue() > sistemaParametro
								.getAreaMaximaTarifaSocial().intValue())) {

					retorno[0] = "12";
					retorno[1] = ""
							+ sistemaParametro.getAreaMaximaTarifaSocial();

					return retorno;
				}
			}

			/*
			 * Caso o usuário não informe os dados do cartão do programa social,
			 * o tipo de renda não corresponda a renda comprovada e não existam
			 * as informações número do IPTU e não exista uma ordem de serviço
			 * de vistoria associado ao RA de cadastramentona tarifa social para
			 * o imóvel
			 */
			if (!RendaTipo.COMPROVADA.equals(new Integer(tipoRenda))
					&& numeroIPTU == null) {

				// Collection colecaoOS = null;
				//
				// try {
				//
				// colecaoOS = repositorioImovelTarifaSocial
				// .verificarOSVistoriaImovelEconomia(idImovelEconomia);
				//
				// } catch (ErroRepositorioException ex) {
				// sessionContext.setRollbackOnly();
				// throw new ControladorException("erro.sistema", ex);
				// }
				//
				// if (colecaoOS == null || colecaoOS.isEmpty()) {
				//
				// sessionContext.setRollbackOnly();
				// throw new ControladorException(
				// "atencao.preencher.tarifa_social.valor.numero_celpe");
				//
				// }
			}
		} else {

			// [FS0014] - Verificar duplicidade do cartão do programa social
			Long numeroCartaoProgramaSocial = new Long(numeroCartaoSocial);
			TarifaSocialCartaoTipo objetoTarifaSocialCartaoTipo = new TarifaSocialCartaoTipo();
			objetoTarifaSocialCartaoTipo.setId(new Integer(
					tarifaSocialCartaoTipo));

			this.verificarDuplicidadeCartaoProgramaSocialImovelEconomia(
					numeroCartaoProgramaSocial, objetoTarifaSocialCartaoTipo,
					idImovelEconomia);
		}

		retorno[0] = "9";
		retorno[1] = "";

		return retorno;

	}

	/**
	 * [UC0054] - Inserir Dados Tarifa Social [FS0014] - Verificar duplicidade
	 * do cartão do programa social
	 * 
	 * @param numeroCartao
	 * @throws ControladorException
	 */
	public void verificarDuplicidadeCartaoProgramaSocial(Long numeroCartao,
			TarifaSocialCartaoTipo tipoCartao, Integer idImovel)
			throws ControladorException {

		FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = new FiltroTarifaSocialDadoEconomia();

		filtroTarifaSocialDadoEconomia
				.adicionarCaminhoParaCarregamentoEntidade("imovel");

		filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroSimples(
				FiltroTarifaSocialDadoEconomia.NUMERO_CARTAO_PROGRAMA_SOCIAL,
				numeroCartao));

		filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroSimples(
				FiltroTarifaSocialDadoEconomia.TARIFA_SOCIAL_CARTAO_TIPO_ID,
				tipoCartao.getId()));

		filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroNulo(
				FiltroTarifaSocialDadoEconomia.DATA_EXCLUSAO));

		filtroTarifaSocialDadoEconomia
				.adicionarParametro(new ParametroSimplesDiferenteDe(
						FiltroTarifaSocialDadoEconomia.IMOVEL_ID, idImovel,
						ConectorOr.CONECTOR_OR, 2));

		filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroNulo(
				FiltroTarifaSocialDadoEconomia.IMOVEL_ID));

		Collection colecaoPesquisa = this.getControladorUtil().pesquisar(
				filtroTarifaSocialDadoEconomia,
				TarifaSocialDadoEconomia.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {

			TarifaSocialDadoEconomia resgatarImovel = (TarifaSocialDadoEconomia) Util
					.retonarObjetoDeColecao(colecaoPesquisa);
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.tarifa_social.numero_cartao_programa_social.existente",
					null, "" + resgatarImovel.getImovel().getId());
		}
	}

	/**
	 * [UC0054] - Inserir Dados Tarifa Social [FS0014] - Verificar duplicidade
	 * do cartão do programa social
	 * 
	 * @param numeroCartao
	 * @throws ControladorException
	 */
	public void verificarDuplicidadeCartaoProgramaSocialImovelEconomia(
			Long numeroCartao, TarifaSocialCartaoTipo tipoCartao,
			Integer idImovelEconomia) throws ControladorException {

		FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = new FiltroTarifaSocialDadoEconomia();

		filtroTarifaSocialDadoEconomia
				.adicionarCaminhoParaCarregamentoEntidade("imovel");

		filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroSimples(
				FiltroTarifaSocialDadoEconomia.NUMERO_CARTAO_PROGRAMA_SOCIAL,
				numeroCartao));

		filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroSimples(
				FiltroTarifaSocialDadoEconomia.TARIFA_SOCIAL_CARTAO_TIPO_ID,
				tipoCartao.getId()));

		filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroNulo(
				FiltroTarifaSocialDadoEconomia.DATA_EXCLUSAO));

		filtroTarifaSocialDadoEconomia
				.adicionarParametro(new ParametroSimplesDiferenteDe(
						FiltroTarifaSocialDadoEconomia.IMOVEL_ECONOMIA_ID,
						idImovelEconomia.intValue(), ConectorOr.CONECTOR_OR, 2));

		filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroNulo(
				FiltroTarifaSocialDadoEconomia.IMOVEL_ECONOMIA_ID));

		Collection colecaoPesquisa = this.getControladorUtil().pesquisar(
				filtroTarifaSocialDadoEconomia,
				TarifaSocialDadoEconomia.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {

			TarifaSocialDadoEconomia resgatarImovel = (TarifaSocialDadoEconomia) Util
					.retonarObjetoDeColecao(colecaoPesquisa);
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.tarifa_social.numero_cartao_programa_social.existente",
					null, "" + resgatarImovel.getImovel().getId());
		}
	}

	/**
	 * Enquadra um imovel no regime de tarifa social
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param tarifaSocialDado
	 *            Descrição do parâmetro
	 * @param tarifaSocialDadoEconomia
	 *            Descrição do parâmetro
	 */
	/*
	 * public void inserirDadosTarifaSocialImovel( TarifaSocialDado
	 * tarifaSocialDado, TarifaSocialDadoEconomia tarifaSocialDadoEconomia)
	 * throws ControladorException {
	 * 
	 * FiltroTarifaSocialDado filtroTarifaSocialDado = new
	 * FiltroTarifaSocialDado();
	 * 
	 * filtroTarifaSocialDado.adicionarParametro(new ParametroSimples(
	 * FiltroTarifaSocialDado.ID, tarifaSocialDado.getId()));
	 * 
	 * Collection colecaoTarifaSocialDado = getControladorUtil().pesquisar(
	 * filtroTarifaSocialDado, TarifaSocialDado.class.getName());
	 * 
	 * if (colecaoTarifaSocialDado == null || colecaoTarifaSocialDado.isEmpty()) {
	 * getControladorUtil().inserir(tarifaSocialDado); }
	 * 
	 * if (tarifaSocialDadoEconomia.getId() == null) {
	 * getControladorUtil().inserir(tarifaSocialDadoEconomia); } }
	 */

	/**
	 * Atualiza o perfil do imóvel para tarifa social
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void atualizarImovelPerfilTarifaSocial(Imovel imovel)
			throws ControladorException {

		// Atualizar o Perfil do Imovel
		ImovelPerfil imovelPerfil = new ImovelPerfil();
		imovelPerfil.setId(ImovelPerfil.TARIFA_SOCIAL);
		ConsumoTarifa consumoTarifa = new ConsumoTarifa();
		consumoTarifa.setId(ConsumoTarifa.CONSUMO_SOCIAL);
		imovel.setImovelPerfil(imovelPerfil);
		imovel.setConsumoTarifa(consumoTarifa);

		getControladorUtil().atualizar(imovel);
	}

	/**
	 * Atualiza o enquadramento de um imovel no regime de tarifa social
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param tarifaSocialDado
	 *            Descrição do parâmetro
	 * @param tarifaSocialDadoEconomia
	 *            Descrição do parâmetro
	 */
	public void atualizarDadosTarifaSocialImovel(
			TarifaSocialDadoEconomia tarifaSocialDadoEconomia)
			throws ControladorException {

		// Atualizar a tarifaSocialDadoEconomia
		getControladorUtil().atualizar(tarifaSocialDadoEconomia);

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
	 * 
	 * Retorna o valor de controladorImovel
	 * 
	 * @return O valor de controladorImovel
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

	/**
	 * 
	 * Retorna o valor de controladorMicroMedicao
	 * 
	 * @return O valor de controladorMicroMedicao
	 */
	private ControladorRegistroAtendimentoLocal getControladorRegistroAtendimento() {

		ControladorRegistroAtendimentoLocalHome localHome = null;
		ControladorRegistroAtendimentoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorRegistroAtendimentoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_REGISTRO_ATENDIMENTO_SEJB);
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
	 * 
	 * Retorna o valor de controladorLigacaoAgua
	 * 
	 * @return O valor de controladorLigacaoAgua
	 */
	private ControladorLigacaoAguaLocal getControladorLigacaoAgua() {

		ControladorLigacaoAguaLocalHome localHome = null;
		ControladorLigacaoAguaLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorLigacaoAguaLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_LIGACAO_AGUA_SEJB);
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
	 * Pesquisa uma coleção de Tarifa Social Dado Economia.
	 * 
	 * @param filtroTarifaSocialDadoEconomia
	 *            Description of the Parameter
	 * @author Thiago
	 * @date 12/12/2005
	 * @return Description of the Return Value
	 */

	public Collection pesquisarTarifaSocialDadoEconomia(
			FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia)
			throws ControladorException {

		Collection tarifasocialDadoEconomeia = getControladorUtil().pesquisar(
				filtroTarifaSocialDadoEconomia,
				TarifaSocialDadoEconomia.class.getSimpleName());
		/*
		 * if (!tarifasocialDadoEconomeia.isEmpty()) { Iterator it =
		 * tarifasocialDadoEconomeia.iterator(); TarifaSocialDadoEconomia tarifa =
		 * (TarifaSocialDadoEconomia) it.next();
		 * 
		 * System.out.print('r'); FiltroClienteImovel filtroClienteImovel = new
		 * FiltroClienteImovel(); filtroClienteImovel.adicionarParametro(new
		 * ParametroSimples(FiltroClienteImovel.IMOVEL_ID,tarifa.getTarifaSocialDado().getId()));
		 * Collection clienteImovel =
		 * Fachada.getInstancia().pesquisarClienteImovel(filtroClienteImovel); }
		 */

		return tarifasocialDadoEconomeia;
	}

	/**
	 * Método que remover o imover da tarifa social
	 * 
	 * @param idImovel
	 * @param idMotivoTarifaSocial
	 * @throws ControladorException
	 */
	public void removerImovelTarfiaSocial(Integer idImovel,
			Integer idMotivoTarifaSocial) throws ControladorException {

		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				idImovel));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("tarifaSocialDado");
		Collection coll = Fachada.getInstancia().pesquisar(filtroImovel,
				Imovel.class.getName());

		Imovel imovel = null;
		if (coll != null && !coll.isEmpty()) {
			imovel = (Imovel) coll.iterator().next();

			imovel.getImovelPerfil().setId(ImovelPerfil.NORMAL);

			getControladorUtil().atualizar(imovel);

			TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo = new TarifaSocialExclusaoMotivo();
			tarifaSocialExclusaoMotivo.setId(idMotivoTarifaSocial);

			// TarifaSocialDado tarifasocial = imovel.getTarifaSocialDado();
			// tarifasocial.setDataExclusao(new
			// Date(System.currentTimeMillis()));
			// tarifasocial
			// .setTarifaSocialExclusaoMotivo(tarifaSocialExclusaoMotivo);
			// TarifaSocialDado tarifasocial = imovel.getTarifaSocialDado();
			/*
			 * tarifasocial.setDataExclusao(new
			 * Date(System.currentTimeMillis())); tarifasocial
			 * .setTarifaSocialExclusaoMotivo(tarifaSocialExclusaoMotivo);
			 * >>>>>>> 1.5 <<<<<<< ControladorTarifaSocialSEJB.java
			 * //getControladorUtil().atualizar(tarifasocial); =======
			 * getControladorUtil().atualizar(tarifasocial);
			 */

		}
	}

	/**
	 * Método que pesquisa uma tarifa social com o parametros do fitlro passado
	 * 
	 * @param filtroClienteImovel
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarImovelTarfiaSocial(
			FiltroClienteImovel filtroClienteImovel, Integer numeroPagina)
			throws ControladorException {
		try {
			return repositorioImovelTarifaSocial.pesquisarImovelTarfiaSocial(
					filtroClienteImovel, numeroPagina);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Método que pesquisa a quantidade de tarifa social
	 * 
	 * @author Rafael Santos
	 * @since 05/09/2006
	 * 
	 * @param filtroClienteImovel
	 * @return
	 * @throws ControladorException
	 */
	public int pesquisarQuantidadeImovelTarfiaSocial(
			FiltroClienteImovel filtroClienteImovel)
			throws ControladorException {
		try {
			return repositorioImovelTarifaSocial
					.pesquisarQuantidadeImovelTarfiaSocial(filtroClienteImovel);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Retorna o valor de controladorCobranca
	 * 
	 * @return O valor de controladorCobranca
	 */
	private ControladorCobrancaLocal getControladorCobranca() {

		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorCobrancaLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
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
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Pesquisa as Tarifas Sociais Dado Economia pelo id do Imóvel carregando a
	 * Tarifa Social Revisao Motivo
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarTarifaSocialDadoEconomia(Integer idImovel)
			throws ControladorException {

		try {
			return repositorioImovelTarifaSocial
					.pesquisarTarifaSocialDadoEconomia(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Pesquisa as Tarifas Sociais Dado Economia pelo id do Imóvel carregando a
	 * Tarifa Social Revisao Motivo
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarTarifaSocialDadoEconomiaImovelEconomia(
			Integer idImovelEconomia) throws ControladorException {

		try {
			return repositorioImovelTarifaSocial
					.pesquisarTarifaSocialDadoEconomiaImovelEconomia(idImovelEconomia);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verifica se o cliente usuário do imóvel já está relacionado em outro
	 * imóvel na tarifa social
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 02/01/2007
	 */
	public Collection verificarClienteCadastradoTarifaSocial(Integer idCliente)
			throws ControladorException {

		Collection colecaoImovel = new ArrayList();

		Collection colecaoIdsImovel = null;

		try {
			colecaoIdsImovel = repositorioImovelTarifaSocial
					.verificarClienteCadastradoTarifaSocial(idCliente);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoIdsImovel != null && !colecaoIdsImovel.isEmpty()) {

			Iterator colecaoIdsImovelIterator = colecaoIdsImovel.iterator();

			while (colecaoIdsImovelIterator.hasNext()) {

				Integer idImovel = (Integer) colecaoIdsImovelIterator.next();

				Imovel imovel = new Imovel();
				imovel.setId(idImovel);

				colecaoImovel.add(imovel);

			}

		}

		return colecaoImovel;

	}

	/**
	 * [UC0009] - Manter Cliente
	 * 
	 * Verifica se o cliente usuário está na tarifa social
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 16/02/2007
	 */
	public Collection verificarClienteUsuarioCadastradoTarifaSocial(
			Integer idCliente) throws ControladorException {

		Collection colecaoImovel = new ArrayList();

		Collection colecaoIdsImovel = null;

		try {
			colecaoIdsImovel = repositorioImovelTarifaSocial
					.verificarClienteUsuarioCadastradoTarifaSocial(idCliente);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoIdsImovel != null && !colecaoIdsImovel.isEmpty()) {

			Iterator colecaoIdsImovelIterator = colecaoIdsImovel.iterator();

			while (colecaoIdsImovelIterator.hasNext()) {

				Integer idImovel = (Integer) colecaoIdsImovelIterator.next();

				Imovel imovel = new Imovel();
				imovel.setId(idImovel);

				colecaoImovel.add(imovel);

			}

		}

		return colecaoImovel;

	}

	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Verifica se o cliente usuário do imóvel já está relacionado em outro
	 * imóvel na tarifa social
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 30/01/2007
	 */
	public Collection verificarClienteCadastradoManterTarifaSocialUmaEconomia(
			Integer idCliente, Integer idImovel) throws ControladorException {

		Collection colecaoImovel = new ArrayList();

		Collection colecaoIdsImovel = null;

		try {
			colecaoIdsImovel = repositorioImovelTarifaSocial
					.verificarClienteCadastradoManterTarifaSocialUmaEconomia(
							idCliente, idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoIdsImovel != null && !colecaoIdsImovel.isEmpty()) {

			Iterator colecaoIdsImovelIterator = colecaoIdsImovel.iterator();

			while (colecaoIdsImovelIterator.hasNext()) {

				Integer idImovelPesquisado = (Integer) colecaoIdsImovelIterator
						.next();

				Imovel imovel = new Imovel();
				imovel.setId(idImovelPesquisado);

				colecaoImovel.add(imovel);

			}

		}

		return colecaoImovel;

	}

	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Verifica se o cliente usuário da economia do imóvel já está relacionado
	 * em outro imóvel na tarifa social
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 30/01/2007
	 */
	public Collection verificarClienteCadastradoManterTarifaSocialMultiplasEconomias(
			Integer idCliente, Integer idImovelEconomia)
			throws ControladorException {

		Collection colecaoImovel = new ArrayList();

		Collection colecaoIdsImovel = null;

		try {
			colecaoIdsImovel = repositorioImovelTarifaSocial
					.verificarClienteCadastradoManterTarifaSocialMultiplasEconomias(
							idCliente, idImovelEconomia);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoIdsImovel != null && !colecaoIdsImovel.isEmpty()) {

			Iterator colecaoIdsImovelIterator = colecaoIdsImovel.iterator();

			while (colecaoIdsImovelIterator.hasNext()) {

				Integer idImovelPesquisado = (Integer) colecaoIdsImovelIterator
						.next();

				Imovel imovel = new Imovel();
				imovel.setId(idImovelPesquisado);

				colecaoImovel.add(imovel);

			}

		}

		return colecaoImovel;

	}

	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verifica se o mesmo cliente está vinculado a mais de uma economia como
	 * usuário
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 03/01/2007
	 */
	public int pesquisarClienteImovelEconomiaCount(Integer idImovel,
			Integer idCliente) throws ControladorException {

		try {
			return this.repositorioImovelTarifaSocial
					.pesquisarClienteImovelEconomiaCount(idImovel, idCliente);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorna os clientes usuários das economias do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 03/01/2007
	 */
	public Collection pesquisarClientesUsuariosImovelEconomia(Integer idImovel)
			throws ControladorException {

		Collection colecaoClientes = new ArrayList();

		Collection colecaoDadosClientesImovelEconomia = null;

		try {
			colecaoDadosClientesImovelEconomia = repositorioImovelTarifaSocial
					.pesquisarClientesUsuariosImovelEconomia(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoDadosClientesImovelEconomia != null
				&& !colecaoDadosClientesImovelEconomia.isEmpty()) {

			Iterator colecaoDadosClientesImovelEconomiaIterator = colecaoDadosClientesImovelEconomia
					.iterator();

			while (colecaoDadosClientesImovelEconomiaIterator.hasNext()) {

				Integer idCliente = (Integer) colecaoDadosClientesImovelEconomiaIterator
						.next();

				Cliente cliente = new Cliente();

				cliente.setId(idCliente);

				colecaoClientes.add(cliente);

			}

		}

		return colecaoClientes;

	}

	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verifica se o mesmo cliente está vinculado a mais de uma economia como
	 * usuário
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 03/01/2007
	 */
	public int verificarExistenciaDebitosCliente(Integer idCliente)
			throws ControladorException {

		// data inicio vencimento debito
		Calendar dataInicioVencimentoDebito = new GregorianCalendar();
		dataInicioVencimentoDebito.set(Calendar.YEAR, new Integer("0001")
				.intValue());
		dataInicioVencimentoDebito.set(Calendar.MONTH, 0);
		dataInicioVencimentoDebito.set(Calendar.DATE, new Integer("01")
				.intValue());

		// data final de vencimento de debito
		Calendar dataFimVencimentoDebito = new GregorianCalendar();
		dataFimVencimentoDebito.add(Calendar.DATE, -45);

		ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = getControladorCobranca()
				.obterDebitoImovelOuCliente(2, null, idCliente.toString(),
						null, "000101", "999912",
						dataInicioVencimentoDebito.getTime(),
						dataFimVencimentoDebito.getTime(), 1, 2, 2, 2, 1, 1, 2,
						null);

		boolean existeDebito = false;

		if (obterDebitoImovelOuClienteHelper != null) {
			// contas
			if (obterDebitoImovelOuClienteHelper.getColecaoContasValores() != null
					&& !obterDebitoImovelOuClienteHelper
							.getColecaoContasValores().isEmpty()) {
				existeDebito = true;
			} else
			// credito a realizar
			if (obterDebitoImovelOuClienteHelper.getColecaoCreditoARealizar() != null
					&& !obterDebitoImovelOuClienteHelper
							.getColecaoCreditoARealizar().isEmpty()) {
				existeDebito = true;
			} else
			// debito a cobrar
			if (obterDebitoImovelOuClienteHelper.getColecaoDebitoACobrar() != null
					&& !obterDebitoImovelOuClienteHelper
							.getColecaoDebitoACobrar().isEmpty()) {
				existeDebito = true;
			} else
			// guias pagamento
			if (obterDebitoImovelOuClienteHelper
					.getColecaoGuiasPagamentoValores() != null
					&& !obterDebitoImovelOuClienteHelper
							.getColecaoGuiasPagamentoValores().isEmpty()) {
				existeDebito = true;
			}
		}

		// Se existir debito para o imovel
		if (existeDebito) {
			return 7;
		}
		return -1;
	}

	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verifica se o cliente usuário está vinculado na tarifa social a outro
	 * imóvel ou economia com motivo de revisão que permita recadastramento
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 04/01/2007
	 */
	public Collection pesquisarClientesUsuarioExistenteTarifaSocial(
			Integer idCliente) throws ControladorException {

		Collection colecaoTarifaSocialDadoEconomia = new ArrayList();

		Collection colecaoDadosTarifaSocialDadoEconomia = null;

		try {
			colecaoDadosTarifaSocialDadoEconomia = repositorioImovelTarifaSocial
					.pesquisarClientesUsuarioExistenteTarifaSocial(idCliente);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoDadosTarifaSocialDadoEconomia != null
				&& !colecaoDadosTarifaSocialDadoEconomia.isEmpty()) {

			Iterator colecaoDadosTarifaSocialDadoEconomiaIterator = colecaoDadosTarifaSocialDadoEconomia
					.iterator();

			while (colecaoDadosTarifaSocialDadoEconomiaIterator.hasNext()) {

				Object[] dadosTarifaSocial = (Object[]) colecaoDadosTarifaSocialDadoEconomiaIterator
						.next();

				TarifaSocialDadoEconomia tarifaSocialDadoEconomia = new TarifaSocialDadoEconomia();

				// Id do Imóvel
				if (dadosTarifaSocial[0] != null) {
					Imovel imovel = new Imovel();
					imovel.setId((Integer) dadosTarifaSocial[0]);
					tarifaSocialDadoEconomia.setImovel(imovel);
				}

				// Descrição do Motivo da Revisão da Tarifa Social
				if (dadosTarifaSocial[1] != null) {
					TarifaSocialRevisaoMotivo tarifaSocialRevisaoMotivo = new TarifaSocialRevisaoMotivo();
					tarifaSocialRevisaoMotivo
							.setDescricao((String) dadosTarifaSocial[1]);
					tarifaSocialDadoEconomia
							.setTarifaSocialRevisaoMotivo(tarifaSocialRevisaoMotivo);
				}

				// Id da Tarifa Social Dado Economia
				if (dadosTarifaSocial[2] != null) {
					tarifaSocialDadoEconomia
							.setId((Integer) dadosTarifaSocial[2]);
				}

				// Id e Descrição do Motivo de Exclusão da Tarifa Social
				// if (dadosTarifaSocial[2] != null) {
				// TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo = new
				// TarifaSocialExclusaoMotivo();
				// tarifaSocialExclusaoMotivo.setId((Integer)
				// dadosTarifaSocial[2]);
				// tarifaSocialExclusaoMotivo.setDescricao((String)
				// dadosTarifaSocial[3]);
				// tarifaSocialDadoEconomia.setTarifaSocialExclusaoMotivo(tarifaSocialExclusaoMotivo);
				// }

				colecaoTarifaSocialDadoEconomia.add(tarifaSocialDadoEconomia);

			}

		}

		return colecaoTarifaSocialDadoEconomia;

	}

	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verificar se existe um motivo de exclusão para o cliente que não permite
	 * recadastramento na tarifa social
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 05/01/2007
	 */
	public void verificarClienteMotivoExclusaoRecadastramento(Integer idCliente)
			throws ControladorException {

		Collection colecaoDadosExclusao = null;

		try {
			colecaoDadosExclusao = repositorioImovelTarifaSocial
					.verificarClienteMotivoExclusaoRecadastramento(idCliente);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoDadosExclusao != null && !colecaoDadosExclusao.isEmpty()) {

			Iterator colecaoDadosExclusaoIterator = colecaoDadosExclusao
					.iterator();

			while (colecaoDadosExclusaoIterator.hasNext()) {

				Short indicadorExclusaoPermiteRecadastramento = (Short) colecaoDadosExclusaoIterator
						.next();

				if (indicadorExclusaoPermiteRecadastramento == 2) {
					throw new ControladorException(
							"atencao.motivo_exclusao_cliente_nao_permite_recadastramento");
				}

			}
		}

	}

	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorna os cliente a partir do id do clienteImovelEconomia
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 08/01/2007
	 */
	public Integer pesquisarClienteImovelEconomia(
			Integer idClienteImovelEconomia) throws ControladorException {

		try {
			return this.repositorioImovelTarifaSocial
					.pesquisarClienteImovelEconomia(idClienteImovelEconomia);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa os dados da tarifa social e do cliente usuário
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 15/01/2007
	 */
	public Collection pesquisarDadosClienteTarifaSocial(Integer idImovel)
			throws ControladorException {
		Collection colecaoTarifaSocialHelper = new ArrayList();

		Collection colecaoDadosTarifaSocial = null;

		try {
			colecaoDadosTarifaSocial = repositorioImovelTarifaSocial
					.pesquisarDadosClienteTarifaSocial(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoDadosTarifaSocial != null
				&& !colecaoDadosTarifaSocial.isEmpty()) {

			Iterator colecaoDadosTarifaSocialIterator = colecaoDadosTarifaSocial
					.iterator();

			while (colecaoDadosTarifaSocialIterator.hasNext()) {

				Object[] dadosTarifaSocial = (Object[]) colecaoDadosTarifaSocialIterator
						.next();

				TarifaSocialHelper tarifaSocialHelper = new TarifaSocialHelper();

				ClienteImovel clienteImovel = new ClienteImovel();

				TarifaSocialDadoEconomia tarifaSocialDadoEconomia = new TarifaSocialDadoEconomia();

				Imovel imovel = new Imovel();

				Cliente cliente = new Cliente();

				TarifaSocialCartaoTipo tarifaSocialCartaoTipo = null;

				RendaTipo rendaTipo = null;

				// Id do Tarifa Social Dado Economia
				if (dadosTarifaSocial[0] != null) {
					tarifaSocialDadoEconomia
							.setId((Integer) dadosTarifaSocial[0]);
				}

				// Nome do Cliente
				if (dadosTarifaSocial[1] != null) {
					cliente.setNome((String) dadosTarifaSocial[1]);
				}

				// Complemento Endereço
				if (dadosTarifaSocial[2] != null) {
					imovel
							.setComplementoEndereco((String) dadosTarifaSocial[2]);
				}

				// CPF
				if (dadosTarifaSocial[3] != null) {
					cliente.setCpf((String) dadosTarifaSocial[3]);
				}

				// RG
				if (dadosTarifaSocial[4] != null) {
					cliente.setRg((String) dadosTarifaSocial[4]);
				}

				// Órgão Expedidor do RG
				if (dadosTarifaSocial[5] != null) {
					OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
					orgaoExpedidorRg
							.setDescricaoAbreviada((String) dadosTarifaSocial[5]);
					cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
				}

				// Unidade da Federação
				if (dadosTarifaSocial[6] != null) {
					UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
					unidadeFederacao.setSigla((String) dadosTarifaSocial[6]);
					cliente.setUnidadeFederacao(unidadeFederacao);
				}

				// Número do Cartão do Programa Social
				if (dadosTarifaSocial[7] != null) {
					tarifaSocialDadoEconomia
							.setNumeroCartaoProgramaSocial((Long) dadosTarifaSocial[7]);
				}

				// Id do Tipo do Cartão do Programa Social
				if (dadosTarifaSocial[8] != null) {
					tarifaSocialCartaoTipo = new TarifaSocialCartaoTipo();
					tarifaSocialCartaoTipo
							.setId((Integer) dadosTarifaSocial[8]);
				}

				// Renda Familiar
				if (dadosTarifaSocial[9] != null) {
					tarifaSocialDadoEconomia
							.setValorRendaFamiliar((BigDecimal) dadosTarifaSocial[9]);
				}

				// Id do Tipo da Renda
				if (dadosTarifaSocial[10] != null) {
					rendaTipo = new RendaTipo();
					rendaTipo.setId((Integer) dadosTarifaSocial[10]);
				}

				// Motivo de Exclusão
				if (dadosTarifaSocial[11] != null) {
					TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo = new TarifaSocialExclusaoMotivo();
					tarifaSocialExclusaoMotivo
							.setId((Integer) dadosTarifaSocial[11]);
					tarifaSocialExclusaoMotivo
							.setDescricao((String) dadosTarifaSocial[32]);
					tarifaSocialDadoEconomia
							.setTarifaSocialExclusaoMotivo(tarifaSocialExclusaoMotivo);
				}

				// Descriçao do Tipo do Cartão do Programa Social
				if (dadosTarifaSocial[12] != null) {
					tarifaSocialCartaoTipo
							.setDescricao((String) dadosTarifaSocial[12]);
				}

				// Data de Validade do Cartão
				if (dadosTarifaSocial[13] != null) {
					tarifaSocialDadoEconomia
							.setDataValidadeCartao((Date) dadosTarifaSocial[13]);
				}

				// Número de Parcelas
				if (dadosTarifaSocial[14] != null) {
					tarifaSocialDadoEconomia
							.setNumeroMesesAdesao((Short) dadosTarifaSocial[14]);
				}

				// Número do Contrato da Companhia Elétrica
				if (dadosTarifaSocial[15] != null) {
					imovel.setNumeroCelpe((Long) dadosTarifaSocial[15]);
				}

				// Consumo Médio
				if (dadosTarifaSocial[16] != null) {
					tarifaSocialDadoEconomia
							.setConsumoCelpe((Integer) dadosTarifaSocial[16]);
				}

				// Número do IPTU
				if (dadosTarifaSocial[17] != null) {
					imovel.setNumeroIptu((BigDecimal) dadosTarifaSocial[17]);
				}

				// Área Construída
				if (dadosTarifaSocial[18] != null) {
					imovel
							.setAreaConstruida((BigDecimal) dadosTarifaSocial[18]);
				}

				// Área Construída Faixa
				if (dadosTarifaSocial[19] != null) {
					AreaConstruidaFaixa areaConstruidaFaixa = new AreaConstruidaFaixa();
					areaConstruidaFaixa.setId((Integer) dadosTarifaSocial[19]);
				}

				// Descrição do Tipo da Renda
				if (dadosTarifaSocial[20] != null) {
					rendaTipo.setDescricao((String) dadosTarifaSocial[20]);
				}

				// Motivo de Revisão
				if (dadosTarifaSocial[21] != null) {
					TarifaSocialRevisaoMotivo tarifaSocialRevisaoMotivo = new TarifaSocialRevisaoMotivo();
					tarifaSocialRevisaoMotivo
							.setId((Integer) dadosTarifaSocial[21]);
					tarifaSocialRevisaoMotivo
							.setDescricao((String) dadosTarifaSocial[33]);
					tarifaSocialDadoEconomia
							.setTarifaSocialRevisaoMotivo(tarifaSocialRevisaoMotivo);
				}

				// Id do Imóvel
				if (dadosTarifaSocial[22] != null) {
					imovel.setId((Integer) dadosTarifaSocial[22]);
				}

				// Id do Município
				if (dadosTarifaSocial[23] != null) {
					SetorComercial setorComercial = new SetorComercial();
					Municipio municipio = new Municipio();
					setorComercial.setId((Integer) dadosTarifaSocial[23]);
					municipio.setId((Integer) dadosTarifaSocial[24]);
					setorComercial.setMunicipio(municipio);
					imovel.setSetorComercial(setorComercial);
				}
				
				if (dadosTarifaSocial[25] != null) {
					Quadra quadra = new Quadra();
					quadra.setId((Integer) dadosTarifaSocial[25]);
					imovel.setQuadra(quadra);
				}

				// Data de Exclusão
				if (dadosTarifaSocial[26] != null) {
					tarifaSocialDadoEconomia
							.setDataExclusao((Date) dadosTarifaSocial[26]);
				}

				// Data de Implantação
				if (dadosTarifaSocial[27] != null) {
					tarifaSocialDadoEconomia
							.setDataImplantacao((Date) dadosTarifaSocial[27]);
				}

				// Data de Revisão
				if (dadosTarifaSocial[28] != null) {
					tarifaSocialDadoEconomia
							.setDataRevisao((Date) dadosTarifaSocial[28]);
				}

				// Quantidade de Recadastramentos
				if (dadosTarifaSocial[29] != null) {
					tarifaSocialDadoEconomia
							.setQuantidadeRecadastramento((Short) dadosTarifaSocial[29]);
				}

				// Data do Recadastramento
				if (dadosTarifaSocial[30] != null) {
					tarifaSocialDadoEconomia
							.setDataRecadastramento((Date) dadosTarifaSocial[30]);
				}

				// Número de Moradores
				if (dadosTarifaSocial[31] != null) {
					imovel.setNumeroMorador((Short) dadosTarifaSocial[31]);
				}

				// Última Alteração
				if (dadosTarifaSocial[34] != null) {
					tarifaSocialDadoEconomia
							.setUltimaAlteracao((Date) dadosTarifaSocial[34]);
				}

				clienteImovel.setImovel(imovel);
				clienteImovel.setCliente(cliente);

				tarifaSocialDadoEconomia
						.setTarifaSocialCartaoTipo(tarifaSocialCartaoTipo);
				tarifaSocialDadoEconomia.setImovel(imovel);
				tarifaSocialDadoEconomia.setRendaTipo(rendaTipo);

				tarifaSocialHelper
						.setTarifaSocialDadoEconomia(tarifaSocialDadoEconomia);
				tarifaSocialHelper.setClienteImovel(clienteImovel);

				colecaoTarifaSocialHelper.add(tarifaSocialHelper);

			}

		}

		return colecaoTarifaSocialHelper;
	}

	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna a tarifa social a partir do seu id
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 16/01/2007
	 */
	public TarifaSocialDadoEconomia pesquisarTarifaSocial(Integer idTarifaSocial)
			throws ControladorException {

		try {
			return this.repositorioImovelTarifaSocial
					.pesquisarTarifaSocial(idTarifaSocial);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Verifica se existe tarifa social para o imóvel que não tenha sido
	 * excluído
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 16/01/2007
	 */
	public Collection pesquisarTarifaSocialImovel(Integer idImovel)
			throws ControladorException {

		Collection colecaoTarifaSocial = new ArrayList();

		Collection colecaoIdsTarifaSocial = null;

		try {
			colecaoIdsTarifaSocial = repositorioImovelTarifaSocial
					.pesquisarTarifaSocialImovel(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoIdsTarifaSocial != null && !colecaoIdsTarifaSocial.isEmpty()) {

			Iterator colecaoIdsTarifaSocialIterator = colecaoIdsTarifaSocial
					.iterator();

			while (colecaoIdsTarifaSocialIterator.hasNext()) {

				TarifaSocialDadoEconomia tarifaSocialDadoEconomia = new TarifaSocialDadoEconomia();

				Integer idTarifaSocial = (Integer) colecaoIdsTarifaSocialIterator
						.next();

				tarifaSocialDadoEconomia.setId(idTarifaSocial);

				colecaoTarifaSocial.add(tarifaSocialDadoEconomia);

			}

		}

		return colecaoTarifaSocial;
	}

	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * [FS0008] - Verificar Preenchimento dos Campos
	 * 
	 * Verificar o preenchimento dos campos para uma economia
	 * 
	 * @date 18/01/2007
	 * @author Rafael Corrêa
	 * @throws ControladorException
	 * 
	 * @param clienteImovel
	 *            Descrição do parâmetro
	 * @param numeroCartaoSocial
	 *            Descrição do parâmetro
	 * @param dataValidadeCartaoSocial
	 *            Descrição do parâmetro
	 * @param numeroParcelasCartaoSocial
	 *            Descrição do parâmetro
	 * @param valorRendaFamiliar
	 *            Descrição do parâmetro
	 * @throws ControladorException
	 */
	public void verificarPreenchimentoManterDadosTarifaSocial(Long numeroCelpe,
			BigDecimal areaConstruida, BigDecimal numeroIPTU, Integer idImovel,
			String numeroCartaoSocial, String dataValidadeCartaoSocial,
			String numeroParcelasCartaoSocial, Integer consumoMedio,
			BigDecimal valorRendaFamiliar, String tarifaSocialCartaoTipo,
			String tipoRenda) throws ControladorException {

		FiltroTarifaSocialCartaoTipo filtroTarifaSocialCartaoTipo = new FiltroTarifaSocialCartaoTipo();
		boolean dadosCartaoSocialPreenchidos = false;

		// Verifica se o usuário deixou de informar os dados do Cartão do
		// Programa Social
		if ((numeroCartaoSocial != null && !numeroCartaoSocial.trim()
				.equals(""))
				|| (numeroParcelasCartaoSocial != null && !numeroParcelasCartaoSocial
						.trim().equals(""))
				|| (dataValidadeCartaoSocial != null && !dataValidadeCartaoSocial
						.trim().equals(""))
				|| (tarifaSocialCartaoTipo != null && !tarifaSocialCartaoTipo
						.equals(String
								.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)))) {

			if (numeroCartaoSocial == null
					|| numeroCartaoSocial.trim().equals("")) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.preencher.tarifa_social.numero");
			} else if (tarifaSocialCartaoTipo == null
					|| tarifaSocialCartaoTipo.equals(String
							.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.preencher.tarifa_social.tipo_cartao");
			} else {

				filtroTarifaSocialCartaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroTarifaSocialCartaoTipo.ID,
								tarifaSocialCartaoTipo));

				Collection colecaoTarifaSocialCartaoTipo = getControladorUtil()
						.pesquisar(filtroTarifaSocialCartaoTipo,
								TarifaSocialCartaoTipo.class.getName());

				TarifaSocialCartaoTipo objetoCartaoTipo = null;
				if (Util.isVazioOrNulo(colecaoTarifaSocialCartaoTipo)) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.pesquisa.tarifa_social.tipo_cartao.inexistente");
				}
				objetoCartaoTipo = (TarifaSocialCartaoTipo) Util
						.retonarObjetoDeColecao(colecaoTarifaSocialCartaoTipo);

				// Informar Data de Validade
				if ((dataValidadeCartaoSocial == null || dataValidadeCartaoSocial
						.trim().equals(""))
						&& objetoCartaoTipo
								.getIndicadorValidade()
								.equals(
										TarifaSocialCartaoTipo.INDICADOR_EXISTENCIA_VALIDADE_SIM)) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.preencher.tarifa_social.data.validade");
				}
				// Informar Número de Parcelas
				else if ((numeroParcelasCartaoSocial == null || numeroParcelasCartaoSocial
						.trim().equals(""))
						&& objetoCartaoTipo.getNumeroMesesAdesao() != null) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.preencher.tarifa_social.numero.parcelas");
				}
				// Número de Parcelas maior que Número de Meses Adesão
				else if ((numeroParcelasCartaoSocial != null && !numeroParcelasCartaoSocial
						.trim().equals(""))
						&& objetoCartaoTipo.getNumeroMesesAdesao() != null
						&& new Integer(numeroParcelasCartaoSocial)
								.intValue() > objetoCartaoTipo
								.getNumeroMesesAdesao().intValue()) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.numero.parcelas.maior.que.numero.maximo.meses.adesao",
							null, objetoCartaoTipo.getNumeroMesesAdesao()
									.toString());
				}
				// Não Informar Data de Validade
				else if ((dataValidadeCartaoSocial != null && !dataValidadeCartaoSocial
						.trim().equals(""))
						&& objetoCartaoTipo
								.getIndicadorValidade()
								.equals(
										TarifaSocialCartaoTipo.INDICADOR_EXISTENCIA_VALIDADE_NAO)) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.nao_preencher.tarifa_social.data.validade");
				}
				// Não Informar Número de Parcelas
				else if ((numeroParcelasCartaoSocial != null && !numeroParcelasCartaoSocial
						.trim().equals(""))
						&& objetoCartaoTipo.getNumeroMesesAdesao() == null) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.nao_preencher.tarifa_social.numero.parcelas");
				} else {
					dadosCartaoSocialPreenchidos = true;
				}

			}
		}

		// Verifica o preenchimento dos campos referentes a renda familiar
		if (!dadosCartaoSocialPreenchidos) {
			if ((valorRendaFamiliar == null || valorRendaFamiliar.intValue() == 0)
					|| (tipoRenda != null && !tipoRenda.equals(String
							.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)))) {

				if (valorRendaFamiliar == null
						|| valorRendaFamiliar.intValue() == 0) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.preencher.tarifa_social.valor_renda_familiar");
				} else if (tipoRenda == null
						|| tipoRenda
								.equals(String
										.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.preencher.tarifa_social.tipo_renda");
				}

			}
		}

		SistemaParametro sistemaParametro = getControladorUtil()
				.pesquisarParametrosDoSistema();

		// Valor da Renda
		// Caso o valor da renda tenha sido informado e seja maior que um
		// salário mínimo
		if (valorRendaFamiliar != null
				&& valorRendaFamiliar.doubleValue() > sistemaParametro
						.getValorSalarioMinimo().doubleValue()) {

			String valorSalario = Util.formatarMoedaReal(sistemaParametro
					.getValorSalarioMinimo());

			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.tarifa_social.renda_familiar.maior.salario_minimo.sem.encerramento",
					null, valorSalario);

		}

		// [FS0015] - Verificar informação do número do contrato da companhia
		// elétrica
		if (consumoMedio != null && consumoMedio != 0 && numeroCelpe == null) {

			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.preencher.tarifa_social.numero_contrato_celpe");
		}

		// Consumo Energia
		/*
		 * Caso o consumo médio de energia do imóvel ou da economia do imóvel
		 * seja superior ao consumo médio de energia permitido para o
		 * cadastramento na tarifa sociual
		 */
		if (consumoMedio != null
				&& consumoMedio != 0
				&& consumoMedio > sistemaParametro
						.getConsumoEnergiaMaximoTarifaSocial().intValue()) {

			String valorMaximoEnergia = sistemaParametro
					.getConsumoEnergiaMaximoTarifaSocial().toString();

			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.preencher.tarifa_social.valor.consumo_energia.sem.encerramento",
					null, valorMaximoEnergia);

		}

		// 1 - O usuário não informou os dados do cartão do programa social
		if (!dadosCartaoSocialPreenchidos) {

			if (valorRendaFamiliar.intValue() == 0) {

				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.preencher.tarifa_social.requisitos", null,
						sistemaParametro.getAreaMaximaTarifaSocial().toString());
			}
			// 2 - O valor da renda foi informado e o tipo é renda COMPROVADA
			else if (!RendaTipo.COMPROVADA.equals(new Integer(tipoRenda))) {

				/*
				 * 3 - O valor da renda foi informado e o tipo é renda DECLARADA
				 * e existir a informação da área construída sendo menor ou
				 * igual ao máximo permitido
				 */
				if (RendaTipo.DECLARADA.equals(new Integer(tipoRenda))
						&& (areaConstruida == null || areaConstruida.intValue() > sistemaParametro
								.getAreaMaximaTarifaSocial().intValue())) {

					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.preencher.tarifa_social.requisitos_sem_encerramento",
							null, sistemaParametro.getAreaMaximaTarifaSocial()
									.toString());
				}
			}

			/*
			 * Caso o usuário não informe os dados do cartão do programa social,
			 * o tipo de renda não corresponda a renda comprovada e não existam
			 * as informações número do IPTU ou número do contrato da companhia
			 * de energia elétrica e não exista uma ordem de serviço de vistoria
			 * associado ao RA de cadastramentona tarifa social para o imóvel
			 */
			if (!RendaTipo.COMPROVADA.equals(new Integer(tipoRenda))
					&& numeroCelpe == null && numeroIPTU == null) {

				// Collection colecaoOS = null;
				//
				// try {
				//
				// colecaoOS = repositorioImovelTarifaSocial
				// .verificarOSVistoriaImovel(idImovel);
				//
				// } catch (ErroRepositorioException ex) {
				// sessionContext.setRollbackOnly();
				// throw new ControladorException("erro.sistema", ex);
				// }
				//
				// if (colecaoOS == null || colecaoOS.isEmpty()) {
				//
				// sessionContext.setRollbackOnly();
				// throw new ControladorException(
				// "atencao.preencher.tarifa_social.valor.numero_celpe");
				//
				// }
			}
		} else {

			// [FS0014] - Verificar duplicidade do cartão do programa social
			Long numeroCartaoProgramaSocial = new Long(numeroCartaoSocial);
			TarifaSocialCartaoTipo objetoTarifaSocialCartaoTipo = new TarifaSocialCartaoTipo();
			objetoTarifaSocialCartaoTipo.setId(new Integer(
					tarifaSocialCartaoTipo));

			this.verificarDuplicidadeCartaoProgramaSocial(
					numeroCartaoProgramaSocial, objetoTarifaSocialCartaoTipo,
					idImovel);
		}

	}

	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * [FS0008] - Verificar Preenchimento dos Campos
	 * 
	 * Verificar o preenchimento dos campos para múltiplas economias
	 * 
	 * @date 18/01/2007
	 * @author Rafael Corrêa
	 * @throws ControladorException
	 * 
	 * @param clienteImovel
	 *            Descrição do parâmetro
	 * @param numeroCartaoSocial
	 *            Descrição do parâmetro
	 * @param dataValidadeCartaoSocial
	 *            Descrição do parâmetro
	 * @param numeroParcelasCartaoSocial
	 *            Descrição do parâmetro
	 * @param valorRendaFamiliar
	 *            Descrição do parâmetro
	 * @throws ControladorException
	 */
	public void verificarPreenchimentoManterDadosTarifaSocialMultiplasEconomias(
			Long numeroCelpe, BigDecimal areaConstruida, BigDecimal numeroIPTU,
			Integer idImovelEconomia, String numeroCartaoSocial,
			String dataValidadeCartaoSocial, String numeroParcelasCartaoSocial,
			Integer consumoMedio, BigDecimal valorRendaFamiliar,
			String tarifaSocialCartaoTipo, String tipoRenda)
			throws ControladorException {

		FiltroTarifaSocialCartaoTipo filtroTarifaSocialCartaoTipo = new FiltroTarifaSocialCartaoTipo();
		boolean dadosCartaoSocialPreenchidos = false;

		// Verifica se o usuário deixou de informar os dados do Cartão do
		// Programa Social
		if ((numeroCartaoSocial != null && !numeroCartaoSocial.trim()
				.equals(""))
				|| (numeroParcelasCartaoSocial != null && !numeroParcelasCartaoSocial
						.trim().equals(""))
				|| (dataValidadeCartaoSocial != null && !dataValidadeCartaoSocial
						.trim().equals(""))
				|| (tarifaSocialCartaoTipo != null && !tarifaSocialCartaoTipo
						.equals(String
								.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)))) {

			if (numeroCartaoSocial == null
					|| numeroCartaoSocial.trim().equals("")) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.preencher.tarifa_social.numero");
			} else if (tarifaSocialCartaoTipo == null
					|| tarifaSocialCartaoTipo.equals(String
							.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.preencher.tarifa_social.tipo_cartao");
			} else {

				filtroTarifaSocialCartaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroTarifaSocialCartaoTipo.ID,
								tarifaSocialCartaoTipo));

				Collection colecaoTarifaSocialCartaoTipo = getControladorUtil()
						.pesquisar(filtroTarifaSocialCartaoTipo,
								TarifaSocialCartaoTipo.class.getName());

				TarifaSocialCartaoTipo objetoCartaoTipo = null;
				if (Util.isVazioOrNulo(colecaoTarifaSocialCartaoTipo)) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.pesquisa.tarifa_social.tipo_cartao.inexistente");
				}
				objetoCartaoTipo = (TarifaSocialCartaoTipo) Util
						.retonarObjetoDeColecao(colecaoTarifaSocialCartaoTipo);

				// Informar Data de Validade
				if ((dataValidadeCartaoSocial == null || dataValidadeCartaoSocial
						.trim().equals(""))
						&& objetoCartaoTipo
								.getIndicadorValidade()
								.equals(
										TarifaSocialCartaoTipo.INDICADOR_EXISTENCIA_VALIDADE_SIM)) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.preencher.tarifa_social.data.validade");
				}
				// Informar Número de Parcelas
				else if ((numeroParcelasCartaoSocial == null || numeroParcelasCartaoSocial
						.trim().equals(""))
						&& objetoCartaoTipo.getNumeroMesesAdesao() != null) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.preencher.tarifa_social.numero.parcelas");
				}
				// Número de Parcelas maior que Número de Meses Adesão
				else if ((numeroParcelasCartaoSocial != null && !numeroParcelasCartaoSocial
						.trim().equals(""))
						&& objetoCartaoTipo.getNumeroMesesAdesao() != null
						&& new Integer(numeroParcelasCartaoSocial)
								.intValue() > objetoCartaoTipo
								.getNumeroMesesAdesao().intValue()) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.numero.parcelas.maior.que.numero.maximo.meses.adesao",
							null, objetoCartaoTipo.getNumeroMesesAdesao()
									.toString());
				}
				// Não Informar Data de Validade
				else if ((dataValidadeCartaoSocial != null && !dataValidadeCartaoSocial
						.trim().equals(""))
						&& objetoCartaoTipo
								.getIndicadorValidade()
								.equals(
										TarifaSocialCartaoTipo.INDICADOR_EXISTENCIA_VALIDADE_NAO)) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.nao_preencher.tarifa_social.data.validade");
				}
				// Não Informar Número de Parcelas
				else if ((numeroParcelasCartaoSocial != null && !numeroParcelasCartaoSocial
						.trim().equals(""))
						&& objetoCartaoTipo.getNumeroMesesAdesao() == null) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.nao_preencher.tarifa_social.numero.parcelas");
				} else {
					dadosCartaoSocialPreenchidos = true;
				}
				
			}
		}

		// Verifica o preenchimento dos campos referentes a renda familiar
		if (!dadosCartaoSocialPreenchidos) {
			if ((valorRendaFamiliar == null || valorRendaFamiliar.intValue() == 0)
					|| (tipoRenda != null && !tipoRenda.equals(String
							.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)))) {

				if (valorRendaFamiliar == null
						|| valorRendaFamiliar.intValue() == 0) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.preencher.tarifa_social.valor_renda_familiar");
				} else if (tipoRenda == null
						|| tipoRenda
								.equals(String
										.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.preencher.tarifa_social.tipo_renda");
				}

			}
		}

		SistemaParametro sistemaParametro = getControladorUtil()
				.pesquisarParametrosDoSistema();

		// Valor da Renda
		// Caso o valor da renda tenha sido informado e seja maior que um
		// salário mínimo
		if (valorRendaFamiliar != null
				&& valorRendaFamiliar.doubleValue() > sistemaParametro
						.getValorSalarioMinimo().doubleValue()) {

			String valorSalario = Util.formatarMoedaReal(sistemaParametro
					.getValorSalarioMinimo());

			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.tarifa_social.renda_familiar.maior.salario_minimo.sem.encerramento",
					null, valorSalario);

		}

		// [FS0015] - Verificar informação do número do contrato da companhia
		// elétrica
		if (consumoMedio != null && consumoMedio != 0 && numeroCelpe == null) {

			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.preencher.tarifa_social.numero_contrato_celpe");
		}

		// Consumo Energia
		/*
		 * Caso o consumo médio de energia do imóvel ou da economia do imóvel
		 * seja superior ao consumo médio de energia permitido para o
		 * cadastramento na tarifa sociual
		 */
		if (consumoMedio != null
				&& consumoMedio != 0
				&& consumoMedio > sistemaParametro
						.getConsumoEnergiaMaximoTarifaSocial().intValue()) {

			String valorMaximoEnergia = sistemaParametro
					.getConsumoEnergiaMaximoTarifaSocial().toString();

			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.preencher.tarifa_social.valor.consumo_energia.sem.encerramento",
					null, valorMaximoEnergia);

		}

		// 1 - O usuário não informou os dados do cartão do programa social
		if (!dadosCartaoSocialPreenchidos) {

			if (valorRendaFamiliar.intValue() == 0) {

				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.preencher.tarifa_social.requisitos", null,
						sistemaParametro.getAreaMaximaTarifaSocial().toString());
			}
			// 2 - O valor da renda foi informado e o tipo é renda COMPROVADA
			else if (!RendaTipo.COMPROVADA.equals(new Integer(tipoRenda))) {

				/*
				 * 3 - O valor da renda foi informado e o tipo é renda DECLARADA
				 * e existir a informação da área construída sendo menor ou
				 * igual ao máximo permitido
				 */
				if (RendaTipo.DECLARADA.equals(new Integer(tipoRenda))
						&& (areaConstruida == null || areaConstruida.intValue() > sistemaParametro
								.getAreaMaximaTarifaSocial().intValue())) {

					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.preencher.tarifa_social.requisitos_sem_encerramento",
							null, sistemaParametro.getAreaMaximaTarifaSocial()
									.toString());
				}
			}

			/*
			 * Caso o usuário não informe os dados do cartão do programa social,
			 * o tipo de renda não corresponda a renda comprovada e não existam
			 * as informações número do IPTU e não exista uma ordem de serviço
			 * de vistoria associado ao RA de cadastramentona tarifa social para
			 * o imóvel
			 */
			if (!RendaTipo.COMPROVADA.equals(new Integer(tipoRenda))
					&& numeroIPTU == null) {

				// Collection colecaoOS = null;
				//
				// try {
				//
				// colecaoOS = repositorioImovelTarifaSocial
				// .verificarOSVistoriaImovelEconomia(idImovelEconomia);
				//
				// } catch (ErroRepositorioException ex) {
				// sessionContext.setRollbackOnly();
				// throw new ControladorException("erro.sistema", ex);
				// }
				//
				// if (colecaoOS == null || colecaoOS.isEmpty()) {
				//
				// sessionContext.setRollbackOnly();
				// throw new ControladorException(
				// "atencao.preencher.tarifa_social.valor.numero_celpe");
				//
				// }
			}
		} else {

			// [FS0014] - Verificar duplicidade do cartão do programa social
			Long numeroCartaoProgramaSocial = new Long(numeroCartaoSocial);
			TarifaSocialCartaoTipo objetoTarifaSocialCartaoTipo = new TarifaSocialCartaoTipo();
			objetoTarifaSocialCartaoTipo.setId(new Integer(
					tarifaSocialCartaoTipo));

			this.verificarDuplicidadeCartaoProgramaSocialImovelEconomia(
					numeroCartaoProgramaSocial, objetoTarifaSocialCartaoTipo,
					idImovelEconomia);
		}

	}

	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna os clientes do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 19/01/2007
	 */
	public Collection pesquisarClientesImovelTarifaSocial(Integer idImovel)
			throws ControladorException {
		Collection colecaoClienteImovel = new ArrayList();

		Collection colecaoDadosClienteImovel = null;

		try {
			colecaoDadosClienteImovel = repositorioImovelTarifaSocial
					.pesquisarClientesImovelTarifaSocial(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoDadosClienteImovel != null
				&& !colecaoDadosClienteImovel.isEmpty()) {

			Iterator colecaoDadosClienteImovelIterator = colecaoDadosClienteImovel
					.iterator();

			while (colecaoDadosClienteImovelIterator.hasNext()) {

				Object[] dadosClienteImovel = (Object[]) colecaoDadosClienteImovelIterator
						.next();

				ClienteImovel clienteImovel = new ClienteImovel();

				Imovel imovel = new Imovel();

				Cliente cliente = new Cliente();

				ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();

				// Id do Cliente
				if (dadosClienteImovel[0] != null) {
					cliente.setId((Integer) dadosClienteImovel[0]);
				}

				// Nome do Cliente
				if (dadosClienteImovel[1] != null) {
					cliente.setNome((String) dadosClienteImovel[1]);
				}

				// Id do Tipo da Relação
				if (dadosClienteImovel[2] != null) {
					clienteRelacaoTipo.setId((Integer) dadosClienteImovel[2]);
				}

				// Descrição do Tipo da Relação
				if (dadosClienteImovel[3] != null) {
					clienteRelacaoTipo
							.setDescricao((String) dadosClienteImovel[3]);
				}

				// CPF
				if (dadosClienteImovel[4] != null) {
					cliente.setCpf((String) dadosClienteImovel[4]);
				}

				// RG
				if (dadosClienteImovel[5] != null) {
					cliente.setRg((String) dadosClienteImovel[5]);
				}

				// Órgão Expedidor do RG
				if (dadosClienteImovel[6] != null) {
					OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
					orgaoExpedidorRg
							.setDescricaoAbreviada((String) dadosClienteImovel[6]);
					cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
				}

				// Unidade da Federação
				if (dadosClienteImovel[7] != null) {
					UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
					unidadeFederacao.setSigla((String) dadosClienteImovel[7]);
					cliente.setUnidadeFederacao(unidadeFederacao);
				}

				// Data de Início da Relação
				if (dadosClienteImovel[8] != null) {
					clienteImovel
							.setDataInicioRelacao((Date) dadosClienteImovel[8]);
				}

				// Id do Cliente Imóvel
				if (dadosClienteImovel[9] != null) {
					clienteImovel.setId((Integer) dadosClienteImovel[9]);
				}

				// CNPJ do Cliente
				if (dadosClienteImovel[10] != null) {
					cliente.setCnpj((String) dadosClienteImovel[10]);
				}

				imovel.setId(idImovel);

				clienteImovel.setImovel(imovel);
				clienteImovel.setCliente(cliente);
				clienteImovel.setClienteRelacaoTipo(clienteRelacaoTipo);

				colecaoClienteImovel.add(clienteImovel);

			}

		}

		return colecaoClienteImovel;
	}

	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna os clientes do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 19/01/2007
	 */
	public Collection pesquisarClientesImovelEconomiaTarifaSocial(
			Integer idImovelEconomia) throws ControladorException {
		Collection colecaoClienteImovelEconomia = new ArrayList();

		Collection colecaoDadosClienteImovelEconomia = null;

		try {
			colecaoDadosClienteImovelEconomia = repositorioImovelTarifaSocial
					.pesquisarClientesImovelEconomiaTarifaSocial(idImovelEconomia);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoDadosClienteImovelEconomia != null
				&& !colecaoDadosClienteImovelEconomia.isEmpty()) {

			Iterator colecaoDadosClienteImovelEconomiaIterator = colecaoDadosClienteImovelEconomia
					.iterator();

			while (colecaoDadosClienteImovelEconomiaIterator.hasNext()) {

				Object[] dadosClienteImovelEconomia = (Object[]) colecaoDadosClienteImovelEconomiaIterator
						.next();

				ClienteImovelEconomia clienteImovelEconomia = new ClienteImovelEconomia();

				ImovelEconomia imovelEconomia = new ImovelEconomia();

				Cliente cliente = new Cliente();

				ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();

				// Id do Cliente
				if (dadosClienteImovelEconomia[0] != null) {
					cliente.setId((Integer) dadosClienteImovelEconomia[0]);
				}

				// Nome do Cliente
				if (dadosClienteImovelEconomia[1] != null) {
					cliente.setNome((String) dadosClienteImovelEconomia[1]);
				}

				// Id do Tipo da Relação
				if (dadosClienteImovelEconomia[2] != null) {
					clienteRelacaoTipo
							.setId((Integer) dadosClienteImovelEconomia[2]);
				}

				// Descrição do Tipo da Relação
				if (dadosClienteImovelEconomia[3] != null) {
					clienteRelacaoTipo
							.setDescricao((String) dadosClienteImovelEconomia[3]);
				}

				// CPF
				if (dadosClienteImovelEconomia[4] != null) {
					cliente.setCpf((String) dadosClienteImovelEconomia[4]);
				}

				// RG
				if (dadosClienteImovelEconomia[5] != null) {
					cliente.setRg((String) dadosClienteImovelEconomia[5]);
				}

				// Órgão Expedidor do RG
				if (dadosClienteImovelEconomia[6] != null) {
					OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
					orgaoExpedidorRg
							.setDescricaoAbreviada((String) dadosClienteImovelEconomia[6]);
					cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
				}

				// Unidade da Federação
				if (dadosClienteImovelEconomia[7] != null) {
					UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
					unidadeFederacao
							.setSigla((String) dadosClienteImovelEconomia[7]);
					cliente.setUnidadeFederacao(unidadeFederacao);
				}

				// Data de Início da Relação
				if (dadosClienteImovelEconomia[8] != null) {
					clienteImovelEconomia
							.setDataInicioRelacao((Date) dadosClienteImovelEconomia[8]);
				}

				// Id do Cliente Imóvel Economia
				if (dadosClienteImovelEconomia[9] != null) {
					clienteImovelEconomia
							.setId((Integer) dadosClienteImovelEconomia[9]);
				}

				// CNPJ do Cliente
				if (dadosClienteImovelEconomia[10] != null) {
					cliente.setCnpj((String) dadosClienteImovelEconomia[10]);
				}

				imovelEconomia.setId(idImovelEconomia);

				clienteImovelEconomia.setImovelEconomia(imovelEconomia);
				clienteImovelEconomia.setCliente(cliente);
				clienteImovelEconomia.setClienteRelacaoTipo(clienteRelacaoTipo);

				colecaoClienteImovelEconomia.add(clienteImovelEconomia);

			}

		}

		return colecaoClienteImovelEconomia;
	}

	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa o cliente pelo seu id carregando o seu tipo
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 22/01/2007
	 */
	public Cliente pesquisarClienteComClienteTipoTarifaSocial(Integer idCliente)
			throws ControladorException {

		Cliente cliente = null;

		Collection colecaoDadosCliente = null;

		try {
			colecaoDadosCliente = repositorioImovelTarifaSocial
					.pesquisarClienteComClienteTipoTarifaSocial(idCliente);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoDadosCliente != null && !colecaoDadosCliente.isEmpty()) {

			Iterator colecaoDadosClienteIterator = colecaoDadosCliente
					.iterator();

			while (colecaoDadosClienteIterator.hasNext()) {

				Object[] dadosCliente = (Object[]) colecaoDadosClienteIterator
						.next();

				cliente = new Cliente();

				// Id do Cliente
				if (dadosCliente[0] != null) {
					cliente.setId((Integer) dadosCliente[0]);
				}

				// Nome do Cliente
				if (dadosCliente[1] != null) {
					cliente.setNome((String) dadosCliente[1]);
				}

				// CPF
				if (dadosCliente[2] != null) {
					cliente.setCpf((String) dadosCliente[2]);
				}

				// RG
				if (dadosCliente[3] != null) {
					cliente.setRg((String) dadosCliente[3]);
				}

				// CNPJ
				if (dadosCliente[4] != null) {
					cliente.setCnpj((String) dadosCliente[4]);
				}

				// Data de Nascimento
				if (dadosCliente[5] != null) {
					cliente.setDataNascimento((Date) dadosCliente[5]);
				}

				// Cliente Tipo
				if (dadosCliente[6] != null) {
					ClienteTipo clienteTipo = new ClienteTipo();
					clienteTipo.setId((Integer) dadosCliente[6]);
					clienteTipo
							.setIndicadorPessoaFisicaJuridica((Short) dadosCliente[7]);
					cliente.setClienteTipo(clienteTipo);
				}

				// Data de Emissão do RG
				if (dadosCliente[8] != null) {
					cliente.setDataEmissaoRg((Date) dadosCliente[8]);
				}

				// Orgão Expedidor RG
				if (dadosCliente[9] != null) {
					OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
					orgaoExpedidorRg
							.setDescricaoAbreviada((String) dadosCliente[9]);
					cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
				}

				// Unidade da Federação
				if (dadosCliente[10] != null) {
					UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
					unidadeFederacao.setSigla((String) dadosCliente[10]);
					cliente.setUnidadeFederacao(unidadeFederacao);
				}
			}

		}

		return cliente;
	}

	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa os dados da tarifa social e do cliente usuário para cada
	 * economia
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 25/01/2007
	 */
	public Collection pesquisarDadosClienteEconomiaTarifaSocial(Integer idImovel)
			throws ControladorException {
		Collection colecaoTarifaSocialHelper = new ArrayList();

		Collection colecaoDadosTarifaSocial = null;

		try {
			colecaoDadosTarifaSocial = repositorioImovelTarifaSocial
					.pesquisarDadosClienteEconomiaTarifaSocial(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoDadosTarifaSocial != null
				&& !colecaoDadosTarifaSocial.isEmpty()) {

			Iterator colecaoDadosTarifaSocialIterator = colecaoDadosTarifaSocial
					.iterator();

			while (colecaoDadosTarifaSocialIterator.hasNext()) {

				Object[] dadosTarifaSocial = (Object[]) colecaoDadosTarifaSocialIterator
						.next();

				TarifaSocialHelper tarifaSocialHelper = new TarifaSocialHelper();

				ClienteImovelEconomia clienteImovelEconomia = new ClienteImovelEconomia();

				TarifaSocialDadoEconomia tarifaSocialDadoEconomia = new TarifaSocialDadoEconomia();

				ImovelEconomia imovelEconomia = new ImovelEconomia();

				Imovel imovel = new Imovel();

				Cliente cliente = new Cliente();

				TarifaSocialCartaoTipo tarifaSocialCartaoTipo = null;

				RendaTipo rendaTipo = null;

				// Id do Tarifa Social Dado Economia
				if (dadosTarifaSocial[0] != null) {
					tarifaSocialDadoEconomia
							.setId((Integer) dadosTarifaSocial[0]);
				}

				// Nome do Cliente
				if (dadosTarifaSocial[1] != null) {
					cliente.setNome((String) dadosTarifaSocial[1]);
				}

				// Complemento Endereço
				if (dadosTarifaSocial[2] != null) {
					imovelEconomia
							.setComplementoEndereco((String) dadosTarifaSocial[2]);
				}

				// CPF
				if (dadosTarifaSocial[3] != null) {
					cliente.setCpf((String) dadosTarifaSocial[3]);
				}

				// RG
				if (dadosTarifaSocial[4] != null) {
					cliente.setRg((String) dadosTarifaSocial[4]);
				}

				// Órgão Expedidor do RG
				if (dadosTarifaSocial[5] != null) {
					OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
					orgaoExpedidorRg
							.setDescricaoAbreviada((String) dadosTarifaSocial[5]);
					cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
				}

				// Unidade da Federação
				if (dadosTarifaSocial[6] != null) {
					UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
					unidadeFederacao.setSigla((String) dadosTarifaSocial[6]);
					cliente.setUnidadeFederacao(unidadeFederacao);
				}

				// Número do Cartão do Programa Social
				if (dadosTarifaSocial[7] != null) {
					tarifaSocialDadoEconomia
							.setNumeroCartaoProgramaSocial((Long) dadosTarifaSocial[7]);
				}

				// Id do Tipo do Cartão do Programa Social
				if (dadosTarifaSocial[8] != null) {
					tarifaSocialCartaoTipo = new TarifaSocialCartaoTipo();
					tarifaSocialCartaoTipo
							.setId((Integer) dadosTarifaSocial[8]);
				}

				// Renda Familiar
				if (dadosTarifaSocial[9] != null) {
					tarifaSocialDadoEconomia
							.setValorRendaFamiliar((BigDecimal) dadosTarifaSocial[9]);
				}

				// Id do Tipo da Renda
				if (dadosTarifaSocial[10] != null) {
					rendaTipo = new RendaTipo();
					rendaTipo.setId((Integer) dadosTarifaSocial[10]);
				}

				// Motivo de Exclusão
				if (dadosTarifaSocial[11] != null) {
					TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo = new TarifaSocialExclusaoMotivo();
					tarifaSocialExclusaoMotivo
							.setId((Integer) dadosTarifaSocial[11]);
					tarifaSocialExclusaoMotivo
							.setDescricao((String) dadosTarifaSocial[33]);
					tarifaSocialDadoEconomia
							.setTarifaSocialExclusaoMotivo(tarifaSocialExclusaoMotivo);
				}

				// Descriçao do Tipo do Cartão do Programa Social
				if (dadosTarifaSocial[12] != null) {
					tarifaSocialCartaoTipo
							.setDescricao((String) dadosTarifaSocial[12]);
				}

				// Data de Validade do Cartão
				if (dadosTarifaSocial[13] != null) {
					tarifaSocialDadoEconomia
							.setDataValidadeCartao((Date) dadosTarifaSocial[13]);
				}

				// Número de Parcelas
				if (dadosTarifaSocial[14] != null) {
					tarifaSocialDadoEconomia
							.setNumeroMesesAdesao((Short) dadosTarifaSocial[14]);
				}

				// Número do Contrato da Companhia Elétrica
				if (dadosTarifaSocial[15] != null) {
					imovelEconomia.setNumeroCelpe((Long) dadosTarifaSocial[15]);
				}

				// Consumo Médio
				if (dadosTarifaSocial[16] != null) {
					tarifaSocialDadoEconomia
							.setConsumoCelpe((Integer) dadosTarifaSocial[16]);
				}

				// Número do IPTU
				if (dadosTarifaSocial[17] != null) {
					imovelEconomia
							.setNumeroIptu((BigDecimal) dadosTarifaSocial[17]);
				}

				// Área Construída
				if (dadosTarifaSocial[18] != null) {
					imovelEconomia
							.setAreaConstruida((BigDecimal) dadosTarifaSocial[18]);
				}

				// Área Construída Faixa
				if (dadosTarifaSocial[19] != null) {
					AreaConstruidaFaixa areaConstruidaFaixa = new AreaConstruidaFaixa();
					areaConstruidaFaixa.setId((Integer) dadosTarifaSocial[19]);
					imovelEconomia.setAreaConstruidaFaixa(areaConstruidaFaixa);
				}

				// Descrição do Tipo da Renda
				if (dadosTarifaSocial[20] != null) {
					rendaTipo.setDescricao((String) dadosTarifaSocial[20]);
				}

				// Motivo de Revisão
				if (dadosTarifaSocial[21] != null) {
					TarifaSocialRevisaoMotivo tarifaSocialRevisaoMotivo = new TarifaSocialRevisaoMotivo();
					tarifaSocialRevisaoMotivo
							.setId((Integer) dadosTarifaSocial[21]);
					tarifaSocialRevisaoMotivo
							.setDescricao((String) dadosTarifaSocial[34]);
					tarifaSocialDadoEconomia
							.setTarifaSocialRevisaoMotivo(tarifaSocialRevisaoMotivo);
				}

				// Id da Economia do Imóvel
				if (dadosTarifaSocial[22] != null) {
					imovelEconomia.setId((Integer) dadosTarifaSocial[22]);
				}

				// Id do Município
				if (dadosTarifaSocial[23] != null) {
					// Cria os objeto necessários para setar o imóvel dentro de
					// imóvel economia
					SetorComercial setorComercial = new SetorComercial();
					ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
					ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
					Municipio municipio = new Municipio();
					Subcategoria subcategoria = new Subcategoria();
					setorComercial.setId((Integer) dadosTarifaSocial[23]);
					municipio.setId((Integer) dadosTarifaSocial[24]);
					setorComercial.setMunicipio(municipio);
					imovel.setSetorComercial(setorComercial);
					imovel.setId(idImovel);
					subcategoria.setId((Integer) dadosTarifaSocial[32]);
					imovelSubcategoriaPK.setImovel(imovel);
					imovelSubcategoriaPK.setSubcategoria(subcategoria);
					imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
					imovelEconomia.setImovelSubcategoria(imovelSubcategoria);
				}

				// Data de Exclusão
				if (dadosTarifaSocial[25] != null) {
					tarifaSocialDadoEconomia
							.setDataExclusao((Date) dadosTarifaSocial[25]);
				}

				// Data de Implantação
				if (dadosTarifaSocial[26] != null) {
					tarifaSocialDadoEconomia
							.setDataImplantacao((Date) dadosTarifaSocial[26]);
				}

				// Data de Revisão
				if (dadosTarifaSocial[27] != null) {
					tarifaSocialDadoEconomia
							.setDataRevisao((Date) dadosTarifaSocial[27]);
				}

				// Quantidade de Recadastramentos
				if (dadosTarifaSocial[28] != null) {
					tarifaSocialDadoEconomia
							.setQuantidadeRecadastramento((Short) dadosTarifaSocial[28]);
				}

				// Data do Recadastramento
				if (dadosTarifaSocial[29] != null) {
					tarifaSocialDadoEconomia
							.setDataRecadastramento((Date) dadosTarifaSocial[29]);
				}

				// Id do Cliente
				if (dadosTarifaSocial[30] != null) {
					cliente.setId((Integer) dadosTarifaSocial[30]);
				}

				// Número de Moradores
				if (dadosTarifaSocial[31] != null) {
					imovelEconomia
							.setNumeroMorador((Short) dadosTarifaSocial[31]);
				}

				// Última Alteração
				if (dadosTarifaSocial[35] != null) {
					tarifaSocialDadoEconomia
							.setUltimaAlteracao((Date) dadosTarifaSocial[35]);
				}

				clienteImovelEconomia.setImovelEconomia(imovelEconomia);
				clienteImovelEconomia.setCliente(cliente);

				tarifaSocialDadoEconomia
						.setTarifaSocialCartaoTipo(tarifaSocialCartaoTipo);
				tarifaSocialDadoEconomia.setRendaTipo(rendaTipo);
				tarifaSocialDadoEconomia.setImovel(imovel);
				tarifaSocialDadoEconomia.setImovelEconomia(imovelEconomia);

				tarifaSocialHelper
						.setTarifaSocialDadoEconomia(tarifaSocialDadoEconomia);
				tarifaSocialHelper
						.setClienteImovelEconomia(clienteImovelEconomia);

				colecaoTarifaSocialHelper.add(tarifaSocialHelper);

			}

		}

		return colecaoTarifaSocialHelper;
	}

	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa a economia do imóvel pelo seu id
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 01/02/2007
	 */
	public ImovelEconomia pesquisarImovelEconomiaPeloId(Integer idImovelEconomia)
			throws ControladorException {

		try {
			return this.repositorioImovelTarifaSocial
					.pesquisarImovelEconomiaPeloId(idImovelEconomia);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Seta o indicador do nome da conta para 2 nos clientes proprietário e
	 * usuários
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 01/02/2007
	 */
	public void atualizarNomeContaClienteImovelTarifaSocial(Integer idImovel)
			throws ControladorException {

		try {
			this.repositorioImovelTarifaSocial
					.atualizarNomeContaClienteImovelTarifaSocial(idImovel);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna o id cliente usuário da economias do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 19/01/2007
	 */
	public Integer pesquisarClienteUsuarioImovelEconomiaTarifaSocial(
			Integer idImovelEconomia) throws ControladorException {

		try {
			return this.repositorioImovelTarifaSocial
					.pesquisarClienteUsuarioImovelEconomiaTarifaSocial(idImovelEconomia);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Recadastrar, atualiza ou remove a tarifa social
	 * 
	 * Autor: Rafael Corrêa,Vivianne Sousa
	 * 
	 * Data: 13/02/2007,13/03/2008
	 */
	public AtendimentoMotivoEncerramento manterTarifaSocial(Imovel imovelSessao,
			Collection colecaoTarifaSocialHelperAtualizar,
			Collection colecaoImoveisExcluidosTarifaSocial,
			Collection colecaoTarifaSocialExcluida,
			Collection colecaoTarifasSociaisRecadastradas, Usuario usuarioLogado)
			throws ControladorException {
		
		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
		
		// ------------ REGISTRAR TRANSação----------------------------

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_MANTER_TARIFA_SOCIAL,
				imovelSessao.getId(), imovelSessao.getId(),  
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		// --------FIM---- REGISTRAR TRANSação----------------------------
        
        // Cria booleanos para verificar o tipo de encerramento do RA
        boolean recadastramentoTarifaSocial = false;
        boolean atualizacaoTarifaSocial = false;
        boolean exclusaoTarifaSocial = false;
        
        AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
        
        // Recadastramento
        // Coleção de tarifas sociais que estavam excluídas e foram recadastradas
		if (colecaoTarifasSociaisRecadastradas != null
				&& !colecaoTarifasSociaisRecadastradas.isEmpty()) {
            
            recadastramentoTarifaSocial = true;

			Iterator colecaoTarifasSociaisRecadastradasIterator = colecaoTarifasSociaisRecadastradas.iterator();

			while (colecaoTarifasSociaisRecadastradasIterator.hasNext()) {

				TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) colecaoTarifasSociaisRecadastradasIterator.next();

				FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = new FiltroTarifaSocialDadoEconomia();
				filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroSimples(
								FiltroTarifaSocialDadoEconomia.ID, tarifaSocialDadoEconomia.getId()));

				Collection colecaoTarifaSocialBase = getControladorUtil().pesquisar(
                        filtroTarifaSocialDadoEconomia,	TarifaSocialDadoEconomia.class.getName());

				TarifaSocialDadoEconomia tarifaSocialDadoEconomiaBase = (TarifaSocialDadoEconomia) Util
						.retonarObjetoDeColecao(colecaoTarifaSocialBase);

				tarifaSocialDadoEconomia.setDataImplantacao(tarifaSocialDadoEconomiaBase.getDataImplantacao());

				// Verifica se o usuário não excluiu o imóvel anterior da tarifa social
				Integer idClienteUsuario = null;

				if (tarifaSocialDadoEconomia.getImovelEconomia() != null) {
					idClienteUsuario = this.pesquisarClienteUsuarioImovelEconomiaTarifaSocial(
                            tarifaSocialDadoEconomia.getImovelEconomia().getId());
				}

				Collection colecaoTarifaSocialDadoEconomiaOutroImovel = null;

				if (idClienteUsuario != null) {
					colecaoTarifaSocialDadoEconomiaOutroImovel = this
							.pesquisarClientesUsuarioExistenteTarifaSocial(idClienteUsuario);
				}

                // Verifica para casos de múltiplas economias se existe outra
                // economia na tarifa social, caso não exista retira o imóvel da
                // tarifa social
				if (colecaoTarifaSocialDadoEconomiaOutroImovel != null
						&& !colecaoTarifaSocialDadoEconomiaOutroImovel.isEmpty()) {
                    
                    exclusaoTarifaSocial = true;

					Iterator colecaoImoveisExcluidosTarifaSocialIterator = colecaoImoveisExcluidosTarifaSocial.iterator();

					while (colecaoImoveisExcluidosTarifaSocialIterator.hasNext()) {
						TarifaSocialHelper tarifaSocialHelperImovelAnterior = (TarifaSocialHelper) colecaoImoveisExcluidosTarifaSocialIterator.next();

						if (tarifaSocialHelperImovelAnterior.getClienteImovel().getCliente().getId().equals(idClienteUsuario)) {
							TarifaSocialDadoEconomia tarifaSocialDadoEconomiaImovelAnterior = 
                                tarifaSocialHelperImovelAnterior.getTarifaSocialDadoEconomia();

							tarifaSocialDadoEconomiaImovelAnterior.setDataExclusao(new Date());
							tarifaSocialDadoEconomiaImovelAnterior.setUltimaAlteracao(new Date());
							
							// ------------ REGISTRAR TRANSação----------------------------
							Imovel imovelExcluidoTarifaSocial = tarifaSocialDadoEconomiaImovelAnterior.getImovel(); 
							RegistradorOperacao registradorOperacao2 = new RegistradorOperacao(
									Operacao.OPERACAO_MANTER_TARIFA_SOCIAL,
									imovelExcluidoTarifaSocial.getId(), imovelExcluidoTarifaSocial.getId(),  
									new UsuarioAcaoUsuarioHelper(usuarioLogado,
											UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

							registradorOperacao2.registrarOperacao(tarifaSocialDadoEconomiaImovelAnterior);
							// --------FIM---- REGISTRAR TRANSação----------------------------							
							
							getControladorUtil().atualizar(tarifaSocialDadoEconomiaImovelAnterior);

							Collection colecaoTarifaSocialNaoExcluidasImovel = this
									.pesquisarTarifaSocialImovel(tarifaSocialDadoEconomia.getImovel().getId());

							if (colecaoTarifaSocialNaoExcluidasImovel == null
									|| colecaoTarifaSocialNaoExcluidasImovel.isEmpty()) {
								
								getControladorImovel().atualizarImovelPerfilNormal(
										tarifaSocialDadoEconomia.getImovel(), registradorOperacao2);

							}

							break;

						}
					}
				}

				if (colecaoTarifaSocialDadoEconomiaOutroImovel != null && !colecaoTarifaSocialDadoEconomiaOutroImovel.isEmpty()) {
					if (colecaoImoveisExcluidosTarifaSocial == null	|| colecaoImoveisExcluidosTarifaSocial.isEmpty()) {
						sessionContext.setRollbackOnly();
						throw new ControladorException("atencao.tarifa_social_motivo_exclusao_nao_informado");
					}
				}
				// ----- REGISTRAR TRANSação -------
				registradorOperacao.registrarOperacao(tarifaSocialDadoEconomia);
				// ---- REGISTRAR TRANSação-------

				getControladorUtil().atualizar(tarifaSocialDadoEconomia);

			}
		}

        // Coleção de tarifas socias que tiveram seus dados atualizados
		if (colecaoTarifaSocialHelperAtualizar != null	&& !colecaoTarifaSocialHelperAtualizar.isEmpty()) {

			Iterator colecaoTarifaSocialHelperAtualizarIterator = colecaoTarifaSocialHelperAtualizar.iterator();

			while (colecaoTarifaSocialHelperAtualizarIterator.hasNext()) {

				TarifaSocialHelper tarifaSocialHelper = (TarifaSocialHelper) colecaoTarifaSocialHelperAtualizarIterator.next();
				TarifaSocialDadoEconomia tarifaSocialDadoEconomiaAtualizar = tarifaSocialHelper.getTarifaSocialDadoEconomia();

				tarifaSocialDadoEconomiaAtualizar.setUltimaAlteracao(new Date());
				
				// Verifica se o usuário fez alguma alteração cadastral
				boolean alterou = false;

				FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = new FiltroTarifaSocialDadoEconomia();
				filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroSimples(
								FiltroTarifaSocialDadoEconomia.ID,tarifaSocialDadoEconomiaAtualizar.getId()));
				filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade("tarifaSocialRevisaoMotivo");
				filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade("tarifaSocialExclusaoMotivo");
				filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade("rendaTipo");
				filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade("tarifaSocialCartaoTipo");

				Collection colecaoTarifaSocialDadoEconomiaBase = getControladorUtil()
						.pesquisar(filtroTarifaSocialDadoEconomia,TarifaSocialDadoEconomia.class.getName());

				TarifaSocialDadoEconomia tarifaSocialDadoEconomiaBase = (TarifaSocialDadoEconomia) Util
						.retonarObjetoDeColecao(colecaoTarifaSocialDadoEconomiaBase);

				tarifaSocialDadoEconomiaAtualizar.setDataImplantacao(tarifaSocialDadoEconomiaBase.getDataImplantacao());

				boolean recadastrou = false;

				TarifaSocialDadoEconomia tarifaSocialDadoEconomiaRecadastrada = null;

                // Verifica se a tarifa social atualizada também estava excluída e foi recadastrada
				if (colecaoTarifasSociaisRecadastradas != null	&& !colecaoTarifasSociaisRecadastradas.isEmpty()) {
					Iterator colecaoTarifasSociaisRecadastradasIterator = colecaoTarifasSociaisRecadastradas.iterator();
					while (colecaoTarifasSociaisRecadastradasIterator.hasNext()) {
						tarifaSocialDadoEconomiaRecadastrada = (TarifaSocialDadoEconomia) colecaoTarifasSociaisRecadastradasIterator.next();

						if (tarifaSocialDadoEconomiaRecadastrada.getId().equals(tarifaSocialDadoEconomiaAtualizar.getId())) {
							recadastrou = true;
							break;
						}

					}

				}

                // Caso esta tarifa social não tenha sido recadastrada verifica
                // se houve alteração cadastral para ser considerado um
                // recadastramento
				if (!recadastrou) {

					boolean igual = false;

					if (tarifaSocialDadoEconomiaAtualizar.getTarifaSocialRevisaoMotivo() == null
							&& tarifaSocialDadoEconomiaBase.getTarifaSocialRevisaoMotivo() == null) {
						igual = true;
					} else if (tarifaSocialDadoEconomiaAtualizar.getTarifaSocialRevisaoMotivo() != null
							&& tarifaSocialDadoEconomiaBase.getTarifaSocialRevisaoMotivo() != null
							&& tarifaSocialDadoEconomiaAtualizar.getTarifaSocialRevisaoMotivo().getId()
									.equals(tarifaSocialDadoEconomiaBase.getTarifaSocialRevisaoMotivo().getId())) {
						igual = true;
					}

					if (igual) {
						alterou = true;
					} else {

						ImovelEconomia imovelEconomiaBase = null;
						Imovel imovelBase = null;

						if (tarifaSocialHelper.getClienteImovelEconomia() != null) {
							imovelEconomiaBase = this.pesquisarImovelEconomiaPeloId(tarifaSocialDadoEconomiaAtualizar
											.getImovelEconomia().getId());
						} else {
							imovelBase = imovelSessao;
						}

						// Seta os valores do campo dentro de um array para ser
						// comparado se houve alguma alteração
						Object[][] dados = new Object[11][11];

						// Número Cartão
						dados[0][0] = tarifaSocialDadoEconomiaAtualizar.getNumeroCartaoProgramaSocial();
						dados[1][0] = tarifaSocialDadoEconomiaBase.getNumeroCartaoProgramaSocial();

						// Tipo do Cartão
						if (tarifaSocialDadoEconomiaAtualizar.getTarifaSocialCartaoTipo() != null) {
							dados[0][1] = tarifaSocialDadoEconomiaAtualizar.getTarifaSocialCartaoTipo().getId();
						} else {
							dados[0][1] = null;
						}
						if (tarifaSocialDadoEconomiaBase.getTarifaSocialCartaoTipo() != null) {
							dados[1][1] = tarifaSocialDadoEconomiaBase.getTarifaSocialCartaoTipo().getId();
						} else {
							dados[1][1] = null;
						}

						// Data de Validade do Cartão
						dados[0][2] = tarifaSocialDadoEconomiaAtualizar.getDataValidadeCartao();
						dados[1][2] = tarifaSocialDadoEconomiaBase.getDataValidadeCartao();

						// Número de Parcelas
						dados[0][3] = tarifaSocialDadoEconomiaAtualizar.getNumeroMesesAdesao();
						dados[1][3] = tarifaSocialDadoEconomiaBase.getNumeroMesesAdesao();

						// Número de Moradores
						if (tarifaSocialHelper.getClienteImovelEconomia() != null) {
							dados[0][4] = tarifaSocialHelper.getClienteImovelEconomia().getImovelEconomia().getNumeroMorador();
							dados[1][4] = imovelEconomiaBase.getNumeroMorador();
						} else {
							dados[0][4] = tarifaSocialHelper.getClienteImovel().getImovel().getNumeroMorador();
							dados[1][4] = imovelBase.getNumeroMorador();
						}

						// Número Contrato Energia
						if (tarifaSocialHelper.getClienteImovelEconomia() != null) {
							dados[0][5] = tarifaSocialHelper.getClienteImovelEconomia().getImovelEconomia().getNumeroCelpe();
							dados[1][5] = imovelEconomiaBase.getNumeroCelpe();
						} else {
							dados[0][5] = tarifaSocialHelper.getClienteImovel().getImovel().getNumeroCelpe();
							dados[1][5] = imovelBase.getNumeroCelpe();
						}

						// Consumo Médio
						dados[0][6] = tarifaSocialDadoEconomiaAtualizar.getConsumoCelpe();
						dados[1][6] = tarifaSocialDadoEconomiaBase.getConsumoCelpe();

						// IPTU
						if (tarifaSocialHelper.getClienteImovelEconomia() != null) {
							dados[0][7] = tarifaSocialHelper.getClienteImovelEconomia()
                                    .getImovelEconomia().getNumeroIptu();
							dados[1][7] = imovelEconomiaBase.getNumeroIptu();
						} else {
							dados[0][7] = tarifaSocialHelper.getClienteImovel().getImovel().getNumeroIptu();
							dados[1][7] = imovelBase.getNumeroIptu();
						}

						// Área Construída
						if (tarifaSocialHelper.getClienteImovelEconomia() != null) {
							dados[0][8] = tarifaSocialHelper.getClienteImovelEconomia()
									.getImovelEconomia().getAreaConstruida();
							dados[1][8] = imovelEconomiaBase.getAreaConstruida();
						} else {
							dados[0][8] = tarifaSocialHelper.getClienteImovel().getImovel().getAreaConstruida();
							dados[1][8] = imovelBase.getAreaConstruida();
						}

						// Valor Renda
						dados[0][9] = tarifaSocialDadoEconomiaAtualizar.getValorRendaFamiliar();
						dados[1][9] = tarifaSocialDadoEconomiaBase.getValorRendaFamiliar();

						// Tipo Renda
						if (tarifaSocialDadoEconomiaAtualizar.getRendaTipo() != null) {
							dados[0][10] = tarifaSocialDadoEconomiaAtualizar.getRendaTipo().getId();
						} else {
							dados[0][10] = null;
						}
						if (tarifaSocialDadoEconomiaBase.getRendaTipo() != null) {
							dados[1][10] = tarifaSocialDadoEconomiaBase.getRendaTipo().getId();
						} else {
							dados[1][10] = null;
						}

						alterou = this.verificarAlteracaoDadosTarifaSocial(dados);

					}

				}

                // Caso tenha ocorrido alteração cadastral atualiza o valor da quantidade de recadastramento
				if (alterou) {
                    
                    recadastramentoTarifaSocial = true;
                    
					int qtdeRecadastramentos = 1;

					if (tarifaSocialDadoEconomiaBase.getQuantidadeRecadastramento() != null) {
						qtdeRecadastramentos = tarifaSocialDadoEconomiaBase
								.getQuantidadeRecadastramento().shortValue() + 1;
					}

					tarifaSocialDadoEconomiaAtualizar
							.setQuantidadeRecadastramento(new Short("" + qtdeRecadastramentos));
					tarifaSocialDadoEconomiaAtualizar.setDataRecadastramento(new Date());

				} else {
                    
					if (!recadastrou) {
                        atualizacaoTarifaSocial = true;
                        
						tarifaSocialDadoEconomiaAtualizar.setQuantidadeRecadastramento(
                                tarifaSocialDadoEconomiaBase.getQuantidadeRecadastramento());
						tarifaSocialDadoEconomiaAtualizar.setDataRecadastramento(
                                tarifaSocialDadoEconomiaBase.getDataRecadastramento());
					} else {
						tarifaSocialDadoEconomiaAtualizar.setQuantidadeRecadastramento(
                                tarifaSocialDadoEconomiaRecadastrada.getQuantidadeRecadastramento());
						tarifaSocialDadoEconomiaAtualizar.setDataRecadastramento(
                                tarifaSocialDadoEconomiaRecadastrada.getDataRecadastramento());
					}
				}

				// ----- REGISTRAR TRANSação -------
				registradorOperacao.registrarOperacao(tarifaSocialDadoEconomiaAtualizar);
				// ---- REGISTRAR TRANSação-------

				getControladorUtil().atualizar(tarifaSocialDadoEconomiaAtualizar);
				
				
				
		        FiltroTarifaSocialCarta filtro = new FiltroTarifaSocialCarta();
		        filtro.adicionarParametro(
		        	new ParametroSimples(
		        		FiltroTarifaSocialCarta.IMOVEL, imovelSessao.getId()));
		        
		        filtro.adicionarParametro(
		            new ParametroNulo(
	            		FiltroTarifaSocialCarta.DATA_EXECUCAO_COMANDO));
		        
		        filtro.adicionarParametro(
	            	new ParametroNaoNulo(
	                	FiltroTarifaSocialCarta.DATA_GERACAO_COMANDO));
		        
		        
		        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialCarta.TARIFA_SOCIAL_COMANDO_CARTA);
				
		        Collection<TarifaSocialCarta> colecaoTarifaSocialCarta  = 
		        	this.getControladorUtil().pesquisar(filtro,TarifaSocialCarta.class.getName());
		        
		        if(colecaoTarifaSocialCarta != null && !colecaoTarifaSocialCarta.isEmpty()){
		        	
		        	TarifaSocialCarta tarifaSocialCarta = (TarifaSocialCarta) Util.retonarObjetoDeColecao(colecaoTarifaSocialCarta);
		        	
		        	tarifaSocialCarta.setDataComparecimento(tarifaSocialHelper.getDataComparecimentoCarta());
		        	
		        	getControladorUtil().atualizar(tarifaSocialCarta);
		        }


                // Verifica se a tarifa social é referente a um imóvel com mais
                // de uma economia, caso seja atualiza a economia do imóvel e
                // caso contrário atualiza o imóvel propriamente dito
				if (tarifaSocialDadoEconomiaAtualizar.getImovelEconomia() != null) {

					ImovelEconomia imovelEconomia = tarifaSocialDadoEconomiaAtualizar.getImovelEconomia();
					imovelEconomia.setUltimaAlteracao(new Date());
					
					// Setando a colecao de clienteimoveleconomias do imoveleconomia,
					// para evitar que o registrar transacao identifique uma colecao vazia no objeto novo
					// registrando como uma exclusao dos antigos
					// isso foi necessario, por conta que quando é entrada a matricula do imovel
					// nao é possivel abrir a tela de alteracao da tarifa social e eh justamente nesta tela que 
					// sao carregados os clientesimoveleconomias de uma economia. 
					if (imovelEconomia.getClienteImovelEconomias() == null){
						Collection colecaoClienteImovelEconomia = 
							this.pesquisarClientesImovelEconomiaTarifaSocial(imovelEconomia.getId());						
						imovelEconomia.setClienteImovelEconomias(new HashSet(colecaoClienteImovelEconomia));						
					}
					
					// ----- REGISTRAR TRANSação -------
					registradorOperacao.registrarOperacao(imovelEconomia);
					// ---- REGISTRAR TRANSação-------

					getControladorUtil().atualizar(imovelEconomia);

				} else {

					Imovel imovel = tarifaSocialDadoEconomiaAtualizar.getImovel();
					imovel.setUltimaAlteracao(new Date());

					// ----- REGISTRAR TRANSação -------
					registradorOperacao.registrarOperacao(imovel);
					// ---- REGISTRAR TRANSação-------

					getControladorUtil().atualizar(imovel);

				}
				
				// Remover os clientes selecionados
				Collection colecaoClienteImovelRemovidos = tarifaSocialHelper.getColecaoClientesRemovidos();
				Collection colecaoClienteImovelEconomiaRemovidos = tarifaSocialHelper.getColecaoClientesEconomiaRemovidos();

				if (colecaoClienteImovelRemovidos != null && !colecaoClienteImovelRemovidos.isEmpty()) {

					Iterator colecaoClienteImovelRemovidosIterator = colecaoClienteImovelRemovidos
							.iterator();

					while (colecaoClienteImovelRemovidosIterator.hasNext()) {
						ClienteImovel clienteImovelRemovido = (ClienteImovel) colecaoClienteImovelRemovidosIterator.next();
						clienteImovelRemovido.setUltimaAlteracao(new Date());
						clienteImovelRemovido.setIndicadorNomeConta(new Short("2"));

						// ----- REGISTRAR TRANSação -------
						registradorOperacao.registrarOperacao(clienteImovelRemovido);
						// ---- REGISTRAR TRANSação-------

						getControladorUtil().atualizar(clienteImovelRemovido);

					}

				} else if (colecaoClienteImovelEconomiaRemovidos != null
						&& !colecaoClienteImovelEconomiaRemovidos.isEmpty()) {

					Iterator colecaoClienteImovelEconomiaRemovidosIterator = colecaoClienteImovelEconomiaRemovidos.iterator();

					while (colecaoClienteImovelEconomiaRemovidosIterator.hasNext()) {
						ClienteImovelEconomia clienteImovelEconomiaRemovido = (ClienteImovelEconomia) colecaoClienteImovelEconomiaRemovidosIterator								.next();
						clienteImovelEconomiaRemovido.setUltimaAlteracao(new Date());

						// ----- REGISTRAR TRANSação -------
						registradorOperacao.registrarOperacao(clienteImovelEconomiaRemovido);
						// ---- REGISTRAR TRANSação-------

						getControladorUtil().atualizar(clienteImovelEconomiaRemovido);

					}

				}

				
				// Inserir os clientes selecionados
				Collection colecaoClienteImovelInseridos = tarifaSocialHelper.getColecaoClientesInseridos();

				Collection colecaoClienteImovelEconomiaInseridos = tarifaSocialHelper.getColecaoClientesEconomiaInseridos();

				// Uma Economia
				if (colecaoClienteImovelInseridos != null && !colecaoClienteImovelInseridos.isEmpty()) {

					Iterator colecaoClienteImovelInseridosIterator = colecaoClienteImovelInseridos.iterator();

					while (colecaoClienteImovelInseridosIterator.hasNext()) {
						ClienteImovel clienteImovelInserido = (ClienteImovel) colecaoClienteImovelInseridosIterator.next();

						if (clienteImovelInserido.getClienteRelacaoTipo().getId().toString()
                                .equals(ClienteRelacaoTipo.USUARIO.toString())) {

							clienteImovelInserido.setIndicadorNomeConta(new Short("1"));

							Collection colecaoClientesUsuarioExistenteTarifaSocial = this
									.pesquisarClientesUsuarioExistenteTarifaSocial(clienteImovelInserido
											.getCliente().getId());

							if (colecaoClientesUsuarioExistenteTarifaSocial != null
									&& !colecaoClientesUsuarioExistenteTarifaSocial.isEmpty()) {

								Iterator colecaoImoveisExcluidosTarifaSocialIterator = colecaoImoveisExcluidosTarifaSocial.iterator();

								while (colecaoImoveisExcluidosTarifaSocialIterator.hasNext()) {
									TarifaSocialHelper tarifaSocialHelperImovelAnterior = (TarifaSocialHelper) colecaoImoveisExcluidosTarifaSocialIterator.next();

									if (tarifaSocialHelperImovelAnterior.getClienteImovel().getCliente()
											.getId().equals(clienteImovelInserido.getCliente().getId())) {
										TarifaSocialDadoEconomia tarifaSocialDadoEconomiaImovelAnterior = tarifaSocialHelperImovelAnterior.getTarifaSocialDadoEconomia();

										tarifaSocialDadoEconomiaImovelAnterior.setDataExclusao(new Date());
										tarifaSocialDadoEconomiaImovelAnterior.setUltimaAlteracao(new Date());
										// ----- REGISTRAR TRANSação -------
										registradorOperacao.registrarOperacao(tarifaSocialDadoEconomiaImovelAnterior);
										// ---- REGISTRAR TRANSação-------

										getControladorUtil().atualizar(tarifaSocialDadoEconomiaImovelAnterior);
										break;
									}
								}
							}
						} else {
							clienteImovelInserido.setIndicadorNomeConta(new Short("2"));
						}

						clienteImovelInserido.setUltimaAlteracao(new Date());
						this.atualizarNomeContaClienteImovelTarifaSocial(imovelSessao.getId());

						// ----- REGISTRAR TRANSação -------
						registradorOperacao.registrarOperacao(clienteImovelInserido);
						// ---- REGISTRAR TRANSação-------

						getControladorUtil().inserir(clienteImovelInserido);

					}

				}
				// Múltiplas Economias
				else if (colecaoClienteImovelEconomiaInseridos != null
						&& !colecaoClienteImovelEconomiaInseridos.isEmpty()) {

					Iterator colecaoClienteImovelEconomiaInseridosIterator = colecaoClienteImovelEconomiaInseridos.iterator();

					while (colecaoClienteImovelEconomiaInseridosIterator.hasNext()) {

						ClienteImovelEconomia clienteImovelEconomiaInserido = (ClienteImovelEconomia) colecaoClienteImovelEconomiaInseridosIterator.next();

						if (clienteImovelEconomiaInserido.getClienteRelacaoTipo().getId().toString()
								.equals(ClienteRelacaoTipo.USUARIO.toString())) {
                            
							Collection colecaoClientesUsuarioExistenteTarifaSocial = this
							    .pesquisarClientesUsuarioExistenteTarifaSocial(
                                        clienteImovelEconomiaInserido.getCliente().getId());

							if (colecaoClientesUsuarioExistenteTarifaSocial != null
									&& !colecaoClientesUsuarioExistenteTarifaSocial.isEmpty()) {

								Iterator colecaoImoveisExcluidosTarifaSocialIterator = colecaoImoveisExcluidosTarifaSocial.iterator();

								while (colecaoImoveisExcluidosTarifaSocialIterator.hasNext()) {
									TarifaSocialHelper tarifaSocialHelperImovelAnterior = (TarifaSocialHelper) colecaoImoveisExcluidosTarifaSocialIterator.next();

									if (tarifaSocialHelperImovelAnterior.getClienteImovel().getCliente()
											.getId().equals(clienteImovelEconomiaInserido.getCliente().getId())) {
                                        
										
										
										TarifaSocialDadoEconomia tarifaSocialDadoEconomiaImovelAnterior = 
                                            tarifaSocialHelperImovelAnterior.getTarifaSocialDadoEconomia();

										tarifaSocialDadoEconomiaImovelAnterior.setDataExclusao(new Date());
										tarifaSocialDadoEconomiaImovelAnterior.setUltimaAlteracao(new Date());

										// ------------ REGISTRAR TRANSação----------------------------

										Imovel imovelExcluidoTarifaSocial = tarifaSocialDadoEconomiaImovelAnterior.getImovel();
										RegistradorOperacao registradorOperacao2 = new RegistradorOperacao(
												Operacao.OPERACAO_MANTER_TARIFA_SOCIAL,
												imovelExcluidoTarifaSocial.getId(), imovelExcluidoTarifaSocial.getId(),  
												new UsuarioAcaoUsuarioHelper(usuarioLogado,
														UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

										registradorOperacao2.registrarOperacao(tarifaSocialDadoEconomiaImovelAnterior);
										// --------FIM---- REGISTRAR TRANSação----------------------------							

										getControladorUtil().atualizar(tarifaSocialDadoEconomiaImovelAnterior);
										
										// no caso de o imovel anterior ser de 1 economia
										if (tarifaSocialDadoEconomiaImovelAnterior.getImovelEconomia() == null){
											getControladorImovel().atualizarImovelPerfilNormal(imovelSessao, registradorOperacao2);
										}
										
										break;
									}
								}
							}
						}

						clienteImovelEconomiaInserido.setUltimaAlteracao(new Date());

						// ----- REGISTRAR TRANSação -------
						registradorOperacao.registrarOperacao(clienteImovelEconomiaInserido);
						// ---- REGISTRAR TRANSação-------
						getControladorUtil().inserir(clienteImovelEconomiaInserido);

					}

				}

			}

		}

		// Coleção referente a tarifa social de uma economia que foram
        // excluída, assim, será mudado o perfil do imóvel para normal, pois
        // este não estará mais na tarifa social
		if (colecaoTarifaSocialExcluida != null && !colecaoTarifaSocialExcluida.isEmpty()) {
            
            exclusaoTarifaSocial = true;

			Iterator colecaoTarifaSocialExcluidaIterator = colecaoTarifaSocialExcluida.iterator();

			while (colecaoTarifaSocialExcluidaIterator.hasNext()) {

				TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) colecaoTarifaSocialExcluidaIterator.next();

				tarifaSocialDadoEconomia.setDataExclusao(new Date());
				tarifaSocialDadoEconomia.setUltimaAlteracao(new Date());
				tarifaSocialDadoEconomia.setTarifaSocialRevisaoMotivo(null);
				tarifaSocialDadoEconomia.setDataRevisao(null);
				tarifaSocialDadoEconomia.setImovel(imovelSessao);

				// ----- REGISTRAR TRANSação -------
				registradorOperacao.registrarOperacao(tarifaSocialDadoEconomia);
				// ---- REGISTRAR TRANSação-------

				getControladorUtil().atualizar(tarifaSocialDadoEconomia);
				
				// Author: Hugo Amorim  Analista:Jeferson Pedrosa  Data:21/05/2010
				//
				//16.4.	Atualizar a situação especial de faturamento do imovel (ftst_id na tabela cadastro.imovel)
				//com o valor correspondente a PARALIZAR FATURAMENTO DE ESGOTO da tabela 
				//faturamento.faturamento_situacao_tipo;
				//
				FaturamentoSituacaoTipo faturamentoSituacaoTipo =  new FaturamentoSituacaoTipo();				
				faturamentoSituacaoTipo.setId(FaturamentoSituacaoTipo.FATURAR_NORMAL);			
				imovelSessao.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
					
				getControladorUtil().atualizar(imovelSessao);
				
				// Author: Hugo Amorim  Analista:Jeferson Pedrosa  Data:21/05/2010
				//
				//4.1.1.1.4.	Registrar a retirada da tarifa social na tabela de faturamento_situacao_historico 
				//	[SB0004 Registrar Retirada da Tarifa Social];
				//						
				FaturamentoSituacaoHistorico faturamentoSituacaoHistorico = null;
				
				FiltroFaturamentoSituacaoHistorico filtroFaturamentoSituacaoHistorico
					= new FiltroFaturamentoSituacaoHistorico();
				
				filtroFaturamentoSituacaoHistorico.adicionarParametro(
						new ParametroSimples(FiltroFaturamentoSituacaoHistorico.ID_IMOVEL, imovelSessao.getId()));
				
				Collection<FaturamentoSituacaoHistorico> colecaoFaturamentoSituacaoHistorico =
					getControladorUtil().pesquisar(filtroFaturamentoSituacaoHistorico,
							FaturamentoSituacaoHistorico.class.getName());
				
				if(colecaoFaturamentoSituacaoHistorico!=null
						&& !colecaoFaturamentoSituacaoHistorico.isEmpty()){
					
					faturamentoSituacaoHistorico = 
						(FaturamentoSituacaoHistorico) 
							Util.retonarObjetoDeColecao(colecaoFaturamentoSituacaoHistorico);
					
					faturamentoSituacaoHistorico.setUltimaAlteracao(new Date());				
					faturamentoSituacaoHistorico
						.setAnoMesFaturamentoRetirada(sistemaParametro.getAnoMesFaturamento());				
					faturamentoSituacaoHistorico.setUsuario(usuarioLogado);					
					faturamentoSituacaoHistorico.setObservacaoRetira(
							"RETIRADA ATRAVES DA FUNCIONALIDADE MANTER DADOS DA TARIFA SOCIAL.");					
					faturamentoSituacaoHistorico.setUsuarioRetira(usuarioLogado);
					
					getControladorUtil().atualizar(faturamentoSituacaoHistorico);
					
					
				}
				
				
				
			}

			Collection colecaoTarifaSocialNaoExcluidasImovel = this.pesquisarTarifaSocialImovel(imovelSessao.getId());

			if (colecaoTarifaSocialNaoExcluidasImovel == null || colecaoTarifaSocialNaoExcluidasImovel.isEmpty()) {
				
				getControladorImovel().atualizarImovelPerfilNormal(imovelSessao, registradorOperacao);
			}

		}
        
        if (recadastramentoTarifaSocial && !atualizacaoTarifaSocial && !exclusaoTarifaSocial) {
            atendimentoMotivoEncerramento.setId(AtendimentoMotivoEncerramento.TARIFA_SOCIAL_IMPLANTADA);
        } else if (atualizacaoTarifaSocial && !recadastramentoTarifaSocial && !exclusaoTarifaSocial) {
            atendimentoMotivoEncerramento.setId(AtendimentoMotivoEncerramento.TARIFA_SOCIAL_ALTERADA);
        } else if (exclusaoTarifaSocial && !recadastramentoTarifaSocial && !atualizacaoTarifaSocial) {
            atendimentoMotivoEncerramento.setId(AtendimentoMotivoEncerramento.TARIFA_SOCIAL_EXCLUIDA);
        } else {
            atendimentoMotivoEncerramento.setId(
                    AtendimentoMotivoEncerramento.ATUALIZAR_EXCLUIR_RECADASTRAR_TARIFA_SOCIAL);
        }
        
        return atendimentoMotivoEncerramento;

	}

	private boolean verificarAlteracaoDadosTarifaSocial(Object[][] dados) {

		boolean alterou = false;

		for (int i = 0; dados.length > i; i++) {
			if (dados[0][i] != null && dados[1][i] == null) {
				alterou = true;
				break;
			} else if (dados[0][i] == null && dados[1][i] != null) {
				alterou = true;
				break;
			} else if (dados[0][i] != null && dados[1][i] != null
					&& !dados[0][i].equals(dados[1][i])) {
				alterou = true;
				break;
			}
		}

		return alterou;
	}

	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Recadastrar ou insere a tarifa social
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 13/02/2007
	 */
	public void inserirTarifaSocial(Imovel imovelSessao,
			ClienteImovel clienteImovel,
			RegistroAtendimento registroAtendimento,
			RegistroAtendimentoUnidade registroAtendimentoUnidade,
			Usuario usuarioLogado, Integer idTarifaSocialDadoEconomiaExcluida,
			Collection colecaoTarifaSocialDadoEconomia,
			Collection colecaoClienteImovelEconomia,
			Collection colecaoTarifaSocialRecadastrar, Imovel imovelAtualizar,
			Collection colecaoImovelEconomiaAtualizar)
			throws ControladorException {
		
		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
		
		// Identificar a realização de alguma manipulação de registros
		boolean manipulacaoRegistro = false;

		// Variável utilizada para recuperar o id do imóvel da tarifa social que
		// será excluída, caso seja necessário
		Integer idImovel = null;

		// ------------ REGISTRAR
		// TRANSação----------------------------

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_TARIFA_SOCIAL,
				imovelSessao.getId(),imovelSessao.getId(),				
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// -----------------------------------------

		// Verifica a existência de alguma Tarifa Social para ser inserida ou
		// alterada
		// PARA UMA ECONOMIA
		if (colecaoTarifaSocialDadoEconomia != null
				&& !colecaoTarifaSocialDadoEconomia.isEmpty()) {

			TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) Util
					.retonarObjetoDeColecao(colecaoTarifaSocialDadoEconomia);

			Imovel imovel = tarifaSocialDadoEconomia.getImovel();

			// INSERIR
			if (colecaoTarifaSocialRecadastrar != null) {

				TarifaSocialDadoEconomia tarifaSocialDadoEconomiaRecadastrar = (TarifaSocialDadoEconomia) Util
						.retonarObjetoDeColecao(colecaoTarifaSocialRecadastrar);

				if (tarifaSocialDadoEconomiaRecadastrar.getDataExclusao() != null) {
					// ATUALIZAR - RECADASTRAMENTO

					Integer idTarifaSocial = tarifaSocialDadoEconomiaRecadastrar
							.getId();

					Collection colecaoTarifaSocialDadoEconomiaOutroImovel = this
							.pesquisarClientesUsuarioExistenteTarifaSocial(clienteImovel
									.getCliente().getId());

					if (colecaoTarifaSocialDadoEconomiaOutroImovel != null
							&& !colecaoTarifaSocialDadoEconomiaOutroImovel
									.isEmpty()) {
						if (idTarifaSocialDadoEconomiaExcluida == null) {
							throw new ControladorException(
									"atencao.tarifa_social_motivo_exclusao_nao_informado");
						} else {
							TarifaSocialDadoEconomia tarifaSocialDadoEconomiaExcluir = (TarifaSocialDadoEconomia) Util
									.retonarObjetoDeColecao(colecaoTarifaSocialDadoEconomiaOutroImovel);

							// Verifica se o cliente está em revisão e em caso
							// afirmativo
							// recupera o id da antiga tarifa social
							// FiltroTarifaSocialDadoEconomia
							// filtroTarifaSocialDadoEconomia = new
							// FiltroTarifaSocialDadoEconomia();
							// filtroTarifaSocialDadoEconomia
							// .adicionarParametro(new ParametroSimples(
							// FiltroTarifaSocialDadoEconomia.ID,
							// tarifaSocialDadoEconomiaExcluir.getId()));
							//                    		
							// Collection colecaoTarifaSocialDadoEconomiaExcluir
							// = getControladorUtil()
							// .pesquisar(filtroTarifaSocialDadoEconomia,
							// TarifaSocialDadoEconomia.class.getName());
							//                    		
							// if (colecaoTarifaSocialDadoEconomiaExcluir !=
							// null &&
							// !colecaoTarifaSocialDadoEconomiaExcluir.isEmpty())
							// {
							// tarifaSocialDadoEconomiaExcluir =
							// (TarifaSocialDadoEconomia)
							// colecaoTarifaSocialDadoEconomiaExcluir.iterator().next();

							tarifaSocialDadoEconomiaExcluir = this
									.pesquisarTarifaSocial(tarifaSocialDadoEconomiaExcluir
											.getId());

							tarifaSocialDadoEconomiaExcluir
									.setTarifaSocialExclusaoMotivo(tarifaSocialDadoEconomia
											.getTarifaSocialExclusaoMotivo());
							tarifaSocialDadoEconomiaExcluir
									.setTarifaSocialRevisaoMotivo(null);
							tarifaSocialDadoEconomiaExcluir
									.setDataRevisao(null);
							tarifaSocialDadoEconomiaExcluir
									.setDataExclusao(new Date());
							tarifaSocialDadoEconomiaExcluir
									.setUltimaAlteracao(new Date());
							idImovel = tarifaSocialDadoEconomiaExcluir
									.getImovel().getId();

							// }

							if (tarifaSocialDadoEconomiaExcluir != null) {

//								 ------------ REGISTRAR TRANSação----------------------------
								registradorOperacao.registrarOperacao(tarifaSocialDadoEconomiaExcluir);
								// --------FIM---- REGISTRAR TRANSação----------------------------					

								getControladorUtil().atualizar(tarifaSocialDadoEconomiaExcluir);

								Collection colecaoTarifaSocialNaoExcluidasImovel = this
										.pesquisarTarifaSocialImovel(idImovel);

								if (colecaoTarifaSocialNaoExcluidasImovel == null
										|| colecaoTarifaSocialNaoExcluidasImovel
												.isEmpty()) {
									getControladorImovel()
											.atualizarImovelPerfilNormal(tarifaSocialDadoEconomiaExcluir
													.getImovel(), registradorOperacao);
								}

							}

						}
					}

					int quantidade = 1;
					if (tarifaSocialDadoEconomiaRecadastrar
							.getQuantidadeRecadastramento() != null) {
						quantidade = tarifaSocialDadoEconomiaRecadastrar
								.getQuantidadeRecadastramento().shortValue() + 1;
					}

					// atualizar a quantidade e data recadastramento
					tarifaSocialDadoEconomia.setId(idTarifaSocial);
					tarifaSocialDadoEconomia
							.setQuantidadeRecadastramento(new Short(quantidade
									+ ""));
					tarifaSocialDadoEconomia.setDataRecadastramento(new Date());
					tarifaSocialDadoEconomia.setDataExclusao(null);
					tarifaSocialDadoEconomia
							.setTarifaSocialExclusaoMotivo(null);
					
//					 ------------ REGISTRAR TRANSação----------------------------
					registradorOperacao.registrarOperacao(tarifaSocialDadoEconomia);
					// --------FIM---- REGISTRAR TRANSação----------------------------					
					this.atualizarDadosTarifaSocialImovel(tarifaSocialDadoEconomia);

					// atualizar cliente usuario para indicador de nome conta
					clienteImovel.setIndicadorNomeConta(new Short("1"));

					// ------------ REGISTRAR
					// TRANSação----------------------------
//					registradorOperacao.registrarOperacao(clienteImovel);
					// --------FIM---- REGISTRAR
					// TRANSação----------------------------

					getControladorUtil().atualizar(clienteImovel);

					this.atualizarNomeContaClienteImovelTarifaSocial(imovel
							.getId());

					// ------------ REGISTRAR TRANSação----------------------------
					registradorOperacao.registrarOperacao(imovel);
					// --------FIM---- REGISTRAR TRANSação----------------------------					
					
					// Author: Hugo Amorim  Analista:Jeferson Pedrosa  Data:21/05/2010
					//
					//16.4.	Atualizar a situação especial de faturamento do imovel (ftst_id na tabela cadastro.imovel)
					//com o valor correspondente a PARALIZAR FATURAMENTO DE ESGOTO da tabela 
					//faturamento.faturamento_situacao_tipo;
					//
						FaturamentoSituacaoTipo faturamentoSituacaoTipo =  new FaturamentoSituacaoTipo();				
						faturamentoSituacaoTipo.setId(FaturamentoSituacaoTipo.PARALISAR_FATURAMENTO_DE_ESGOTO);			
						imovel.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);						
					
					// atualizar o perfil para Tarifa Social
					this.atualizarImovelPerfilTarifaSocial(imovel);
					
					// Author: Hugo Amorim  Analista:Jeferson Pedrosa  Data:21/05/2010
					//
					//16.5.	Incluir os dados da tarifa social na tabela de faturamento_situacao_historico
					//	[SB0001 Incluir FATURAMENTO_SITUACAO_HISTORICO];
					//						
						FaturamentoSituacaoHistorico faturamentoSituacaoHistorico
							= new FaturamentoSituacaoHistorico();
						faturamentoSituacaoHistorico.setImovel(imovel);
						faturamentoSituacaoHistorico.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
						
						FaturamentoSituacaoMotivo faturamentoSituacaoMotivo = new FaturamentoSituacaoMotivo();
						faturamentoSituacaoMotivo.setId(FaturamentoSituacaoMotivo.ISENCAO_TARIFA_ESGOTO_DECRETO_18251_94);			
						
						faturamentoSituacaoHistorico.setFaturamentoSituacaoMotivo(faturamentoSituacaoMotivo);						
						faturamentoSituacaoHistorico.setUltimaAlteracao(new Date());
						
						faturamentoSituacaoHistorico.setAnoMesFaturamentoSituacaoInicio(sistemaParametro.getAnoMesFaturamento());
						faturamentoSituacaoHistorico.setUsuario(usuarioLogado);
						faturamentoSituacaoHistorico.setAnoMesFaturamentoSituacaoFim(new Integer(201910));
						faturamentoSituacaoHistorico
							.setObservacaoInforma("ESGOTO SUSPENSO POR ORDEM DO GOVERNADOR PARA IMOVEL TAR. SOCIAL, A PARTIR DA FATURA 10/2009");
						faturamentoSituacaoHistorico.setObservacaoRetira("");
						faturamentoSituacaoHistorico.setUsuarioInforma(usuarioLogado);
						
						this.getControladorUtil().inserir(faturamentoSituacaoHistorico);

				}
			} else {

				TarifaSocialDadoEconomia tarifaSocialDadoEconomiaExcluir = null;

				// Verifica se o cliente está em revisão e em caso afirmativo
				// recupera o id da antiga tarifa social
				if (tarifaSocialDadoEconomia.getTarifaSocialExclusaoMotivo() != null) {

					// FiltroTarifaSocialDadoEconomia
					// filtroTarifaSocialDadoEconomia = new
					// FiltroTarifaSocialDadoEconomia();
					// filtroTarifaSocialDadoEconomia
					// .adicionarParametro(new ParametroSimples(
					// FiltroTarifaSocialDadoEconomia.ID,
					// tarifaSocialDadoEconomia.getId()));
					//            		
					// Collection colecaoTarifaSocialDadoEconomiaExcluir =
					// getControladorUtil()
					// .pesquisar(filtroTarifaSocialDadoEconomia,
					// TarifaSocialDadoEconomia.class.getName());
					//            		
					// if (colecaoTarifaSocialDadoEconomiaExcluir != null &&
					// !colecaoTarifaSocialDadoEconomiaExcluir.isEmpty()) {

					tarifaSocialDadoEconomiaExcluir = this
							.pesquisarTarifaSocial(tarifaSocialDadoEconomia
									.getId());

					// tarifaSocialDadoEconomiaExcluir =
					// (TarifaSocialDadoEconomia)
					// colecaoTarifaSocialDadoEconomiaExcluir.iterator().next();

					tarifaSocialDadoEconomiaExcluir
							.setTarifaSocialExclusaoMotivo(tarifaSocialDadoEconomia
									.getTarifaSocialExclusaoMotivo());
					tarifaSocialDadoEconomiaExcluir
							.setTarifaSocialRevisaoMotivo(null);
					tarifaSocialDadoEconomiaExcluir.setDataRevisao(null);
					tarifaSocialDadoEconomiaExcluir.setDataExclusao(new Date());
					tarifaSocialDadoEconomiaExcluir
							.setUltimaAlteracao(new Date());
					idImovel = tarifaSocialDadoEconomiaExcluir.getImovel()
							.getId();

					// }

					tarifaSocialDadoEconomia.setId(null);
					tarifaSocialDadoEconomia
							.setTarifaSocialExclusaoMotivo(null);

				}

				imovel.setQuadra(imovelSessao.getQuadra());

				// ------------ REGISTRAR
				// TRANSação----------------------------
				registradorOperacao.registrarOperacao(tarifaSocialDadoEconomia);

				// --------FIM---- REGISTRAR
				// TRANSação----------------------------

				tarifaSocialDadoEconomia.setDataImplantacao(new Date());

				getControladorUtil().inserir(tarifaSocialDadoEconomia);
				// getControladorRegistroAtendimento()
				// .encerrarRegistroAtendimento(registroAtendimento,
				// registroAtendimentoUnidade, usuarioLogado);

				clienteImovel.setIndicadorNomeConta(new Short("1"));

				// ------------ REGISTRAR
				// TRANSação----------------------------

				registradorOperacao.registrarOperacao(clienteImovel);

				// --------FIM---- REGISTRAR
				// TRANSação----------------------------

				getControladorUtil().atualizar(clienteImovel);

				// ------------ REGISTRAR Transação----------------------------
				//no registrar transacao estava dando erro pela ausencia da quadra
				registradorOperacao.registrarOperacao(imovel);
				// --------FIM---- REGISTRAR TRANSação----------------------------

				
				this.atualizarNomeContaClienteImovelTarifaSocial(imovel.getId());
				
				// Author: Hugo Amorim  Analista:Jeferson Pedrosa  Data:21/05/2010
				//
				//16.4.	Atualizar a situação especial de faturamento do imovel (ftst_id na tabela cadastro.imovel)
				//com o valor correspondente a PARALIZAR FATURAMENTO DE ESGOTO da tabela 
				//faturamento.faturamento_situacao_tipo;
				//
					FaturamentoSituacaoTipo faturamentoSituacaoTipo =  new FaturamentoSituacaoTipo();				
					faturamentoSituacaoTipo.setId(FaturamentoSituacaoTipo.PARALISAR_FATURAMENTO_DE_ESGOTO);			
					imovel.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);						

				this.atualizarImovelPerfilTarifaSocial(imovel);
				
				// Author: Hugo Amorim  Analista:Jeferson Pedrosa  Data:21/05/2010
				//
				//16.5.	Incluir os dados da tarifa social na tabela de faturamento_situacao_historico
				//	[SB0001 Incluir FATURAMENTO_SITUACAO_HISTORICO];
				//						
					FaturamentoSituacaoHistorico faturamentoSituacaoHistorico
						= new FaturamentoSituacaoHistorico();
					faturamentoSituacaoHistorico.setImovel(imovel);
					faturamentoSituacaoHistorico.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
					
					FaturamentoSituacaoMotivo faturamentoSituacaoMotivo = new FaturamentoSituacaoMotivo();
					faturamentoSituacaoMotivo.setId(FaturamentoSituacaoMotivo.ISENCAO_TARIFA_ESGOTO_DECRETO_18251_94);			
					
					faturamentoSituacaoHistorico.setFaturamentoSituacaoMotivo(faturamentoSituacaoMotivo);						
					faturamentoSituacaoHistorico.setUltimaAlteracao(new Date());
					
					faturamentoSituacaoHistorico.setAnoMesFaturamentoSituacaoInicio(sistemaParametro.getAnoMesFaturamento());
					faturamentoSituacaoHistorico.setUsuario(usuarioLogado);
					faturamentoSituacaoHistorico.setAnoMesFaturamentoSituacaoFim(new Integer(201910));
					faturamentoSituacaoHistorico
						.setObservacaoInforma("ESGOTO SUSPENSO POR ORDEM DO GOVERNADOR PARA IMOVEL TAR. SOCIAL, A PARTIR DA FATURA 10/2009");
					faturamentoSituacaoHistorico.setObservacaoRetira("");
					faturamentoSituacaoHistorico.setUsuarioInforma(usuarioLogado);
					
					this.getControladorUtil().inserir(faturamentoSituacaoHistorico);

				if (tarifaSocialDadoEconomiaExcluir != null) {

					getControladorUtil().atualizar(
							tarifaSocialDadoEconomiaExcluir);

					Collection colecaoTarifaSocialNaoExcluidasImovel = this
							.pesquisarTarifaSocialImovel(idImovel);

					if (colecaoTarifaSocialNaoExcluidasImovel == null
							|| colecaoTarifaSocialNaoExcluidasImovel.isEmpty()) {
																	
						getControladorImovel().atualizarImovelPerfilNormal(
								imovel, registradorOperacao);
					}

				}
			}

			if (imovelAtualizar != null) {
				imovelAtualizar.setUltimaAlteracao(new Date());

				// ------------ REGISTRAR
				// TRANSação----------------------------

				registradorOperacao.registrarOperacao(imovelAtualizar);

				// --------FIM---- REGISTRAR
				// TRANSação----------------------------
				getControladorUtil().atualizar(imovelAtualizar);
			}

			manipulacaoRegistro = true;

			// PARA MAIS DE UMA ECONOMIA
		} else if (colecaoClienteImovelEconomia != null
				&& !colecaoClienteImovelEconomia.isEmpty()) {

			Iterator colecaoClienteImovelEconomiaIterator = colecaoClienteImovelEconomia
					.iterator();
			ClienteImovelEconomia clienteImovelEconomia = null;
			Collection colecaoTarifaSocialDadoMultiplasEconomia = null;

			int qtdEconomiaTarifaSocial = 0;

			while (colecaoClienteImovelEconomiaIterator.hasNext()) {

				clienteImovelEconomia = (ClienteImovelEconomia) colecaoClienteImovelEconomiaIterator
						.next();
				colecaoTarifaSocialDadoMultiplasEconomia = clienteImovelEconomia
						.getImovelEconomia().getTarifaSocialDadoEconomias();

				if (colecaoTarifaSocialDadoMultiplasEconomia != null
						&& !colecaoTarifaSocialDadoMultiplasEconomia.isEmpty()) {

					qtdEconomiaTarifaSocial++;
				}
			}

			// Todas as economias terão que ser informadas
			// =============================================================================
			if (qtdEconomiaTarifaSocial != getControladorImovel()
					.obterQuantidadeEconomias(imovelSessao)) {
				throw new ControladorException(
						"atencao.informar.tarifa_social.economias");
			}
			// =============================================================================

			colecaoClienteImovelEconomiaIterator = colecaoClienteImovelEconomia
					.iterator();
			clienteImovelEconomia = null;
			colecaoTarifaSocialDadoMultiplasEconomia = null;

			while (colecaoClienteImovelEconomiaIterator.hasNext()) {

				clienteImovelEconomia = (ClienteImovelEconomia) colecaoClienteImovelEconomiaIterator
						.next();
				colecaoTarifaSocialDadoMultiplasEconomia = clienteImovelEconomia
						.getImovelEconomia().getTarifaSocialDadoEconomias();

				if (colecaoTarifaSocialDadoMultiplasEconomia != null
						&& !colecaoTarifaSocialDadoMultiplasEconomia.isEmpty()) {

					manipulacaoRegistro = true;

					TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) Util
							.retonarObjetoDeColecao(colecaoTarifaSocialDadoMultiplasEconomia);

					Collection colecaoTarifaSocialDadoEconomiaOutroImovel = this
							.pesquisarClientesUsuarioExistenteTarifaSocial(clienteImovelEconomia
									.getCliente().getId());

					if (colecaoTarifaSocialDadoEconomiaOutroImovel != null
							&& !colecaoTarifaSocialDadoEconomiaOutroImovel
									.isEmpty()) {
						if (idTarifaSocialDadoEconomiaExcluida == null) {
							throw new ControladorException(
									"atencao.tarifa_social_motivo_exclusao_nao_informado");
						} else {
							TarifaSocialDadoEconomia tarifaSocialDadoEconomiaExcluir = (TarifaSocialDadoEconomia) Util
									.retonarObjetoDeColecao(colecaoTarifaSocialDadoEconomiaOutroImovel);

							// Verifica se o cliente está em revisão e em caso
							// afirmativo
							// recupera o id da antiga tarifa social

							tarifaSocialDadoEconomiaExcluir = this
									.pesquisarTarifaSocial(tarifaSocialDadoEconomiaExcluir
											.getId());

							tarifaSocialDadoEconomiaExcluir
									.setTarifaSocialExclusaoMotivo(tarifaSocialDadoEconomia
											.getTarifaSocialExclusaoMotivo());
							tarifaSocialDadoEconomiaExcluir
									.setTarifaSocialRevisaoMotivo(null);
							tarifaSocialDadoEconomiaExcluir
									.setDataRevisao(null);
							tarifaSocialDadoEconomiaExcluir
									.setDataExclusao(new Date());
							tarifaSocialDadoEconomiaExcluir
									.setUltimaAlteracao(new Date());
							idImovel = tarifaSocialDadoEconomiaExcluir
									.getImovel().getId();

							if (tarifaSocialDadoEconomiaExcluir != null) {

								// ------------ REGISTRAR
								// TRANSação----------------------------

								registradorOperacao
										.registrarOperacao(tarifaSocialDadoEconomiaExcluir);

								// --------FIM---- REGISTRAR
								// TRANSação----------------------------

								getControladorUtil().atualizar(
										tarifaSocialDadoEconomiaExcluir);

								Collection colecaoTarifaSocialNaoExcluidasImovel = this
										.pesquisarTarifaSocialImovel(idImovel);

								if (colecaoTarifaSocialNaoExcluidasImovel == null
										|| colecaoTarifaSocialNaoExcluidasImovel
												.isEmpty()) {
									getControladorImovel()
											.atualizarImovelPerfilNormal(tarifaSocialDadoEconomiaExcluir
													.getImovel(), registradorOperacao);
								}

							}

						}
					}

					// Para mais de uma economia é obrigatório informar o
					// imovelEconomia
					tarifaSocialDadoEconomia
							.setImovelEconomia(clienteImovelEconomia
									.getImovelEconomia());
					tarifaSocialDadoEconomia.setImovel(imovelSessao);

					// Carregando o imóvel
					FiltroImovel filtroImovel = new FiltroImovel();

					// Objetos que serão retornados pelo hibernate
					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");

					// Inseri ou atualiza na base.
					if (tarifaSocialDadoEconomia.getId() != null) {

						FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = new FiltroTarifaSocialDadoEconomia();
						filtroTarifaSocialDadoEconomia.setInitializeLazy(true);
						filtroTarifaSocialDadoEconomia
								.adicionarParametro(new ParametroSimples(
										FiltroTarifaSocialDadoEconomia.ID,
										tarifaSocialDadoEconomia.getId()));

						Collection colecaoTarifaSocialAtualizar = getControladorUtil()
								.pesquisar(
										filtroTarifaSocialDadoEconomia,
										TarifaSocialDadoEconomia.class
												.getName());

						TarifaSocialDadoEconomia tarifaSocialDadoEconomiaAtualizar = (TarifaSocialDadoEconomia) Util
								.retonarObjetoDeColecao(colecaoTarifaSocialAtualizar);

						int qtdeRecadastramento = 1;

						if (tarifaSocialDadoEconomiaAtualizar
								.getQuantidadeRecadastramento() != null) {
							qtdeRecadastramento = tarifaSocialDadoEconomiaAtualizar
									.getQuantidadeRecadastramento()
									.shortValue() + 1;
						}

						tarifaSocialDadoEconomia.setUltimaAlteracao(new Date());
						tarifaSocialDadoEconomia.setDataExclusao(null);
						tarifaSocialDadoEconomia
								.setTarifaSocialExclusaoMotivo(null);
						tarifaSocialDadoEconomia
								.setQuantidadeRecadastramento(new Short(""
										+ qtdeRecadastramento));
						tarifaSocialDadoEconomia
								.setDataRecadastramento(new Date());
						
						// ------------ REGISTRAR Transação----------------------------

						registradorOperacao.registrarOperacao(tarifaSocialDadoEconomia);

						// --------FIM---- REGISTRAR TRANSação----------------------------
						
						this
								.atualizarDadosTarifaSocialImovel(tarifaSocialDadoEconomia);
						// getControladorRegistroAtendimento()
						// .encerrarRegistroAtendimento(
						// registroAtendimento,
						// registroAtendimentoUnidade,
						// usuarioLogado);
					} else {
						tarifaSocialDadoEconomia.setUltimaAlteracao(new Date());
						tarifaSocialDadoEconomia.setDataExclusao(null);
						tarifaSocialDadoEconomia
								.setTarifaSocialExclusaoMotivo(null);
						tarifaSocialDadoEconomia.setDataImplantacao(new Date());
						
						// ------------ REGISTRAR Transação----------------------------
						registradorOperacao.registrarOperacao(tarifaSocialDadoEconomia);
						// --------FIM---- REGISTRAR TRANSação----------------------------
						
						getControladorUtil().inserir(tarifaSocialDadoEconomia);
						// getControladorRegistroAtendimento()
						// .encerrarRegistroAtendimento(
						// registroAtendimento,
						// registroAtendimentoUnidade,
						// usuarioLogado);
					}
				}
			}

			if (colecaoImovelEconomiaAtualizar != null
					&& !colecaoImovelEconomiaAtualizar.isEmpty()) {

				Iterator colecaoImovelEconomiaAtualizarIterator = colecaoImovelEconomiaAtualizar
						.iterator();

				while (colecaoImovelEconomiaAtualizarIterator.hasNext()) {
					ImovelEconomia imovelEconomiaAtualizar = (ImovelEconomia) colecaoImovelEconomiaAtualizarIterator
							.next();

					imovelEconomiaAtualizar.setUltimaAlteracao(new Date());

					// ------------ REGISTRAR
					// TRANSação----------------------------

					registradorOperacao
							.registrarOperacao(imovelEconomiaAtualizar);

					// --------FIM---- REGISTRAR
					// TRANSação----------------------------

					getControladorUtil().atualizar(imovelEconomiaAtualizar);

				}
			}

		} else {
			// Nenhuma tarifa social foi encontrada
			throw new ControladorException(
					"atencao.nenhuma.tarifa_social.encontrada");
		}

		if (!manipulacaoRegistro) {
			// Nenhuma tarifa social foi encontrada
			throw new ControladorException(
					"atencao.nenhuma.tarifa_social.encontrada");
		}

		if (imovelAtualizar != null) {
			// ------------ REGISTRAR Transação----------------------------
			registradorOperacao.registrarOperacao(imovelAtualizar);
			// --------FIM---- REGISTRAR TRANSação----------------------------			
			this.atualizarImovelPerfilTarifaSocial(imovelAtualizar);
		} else {
			// ------------ REGISTRAR Transação----------------------------
			registradorOperacao.registrarOperacao(imovelSessao);
			// --------FIM---- REGISTRAR TRANSação----------------------------			
			this.atualizarImovelPerfilTarifaSocial(imovelSessao);
		}

		//Colocado por Raphael Rossiter em 10/03/2008
		ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = 
		getControladorRegistroAtendimento().obterDadosRegistroAtendimento(registroAtendimento.getId());
		
		SolicitacaoTipoEspecificacao especificacao = registroAtendimentoHelper
		.getRegistroAtendimento().getSolicitacaoTipoEspecificacao();
		
		if (especificacao.getDebitoTipo() != null){			
			getControladorRegistroAtendimento().encerrarRegistroAtendimento(registroAtendimento,registroAtendimentoUnidade, 
			usuarioLogado, especificacao.getDebitoTipo().getId(), especificacao.getValorDebito(), 1, "100", false,null,false);
		}
		else{
			getControladorRegistroAtendimento().encerrarRegistroAtendimento(registroAtendimento,registroAtendimentoUnidade, 
			usuarioLogado, null, null, null, null, false,null,false);
		}

	}

	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorna a economia do imóvel a partir do id do clienteImovelEconomia
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 15/02/2007
	 */
	public ImovelEconomia pesquisarImovelEconomiaPeloCliente(
			Integer idClienteImovelEconomia) throws ControladorException {

		try {
			return this.repositorioImovelTarifaSocial
					.pesquisarImovelEconomiaPeloCliente(idClienteImovelEconomia);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}



	
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Autor: Vivianne Sousa
	 * 
	 * Data: 27/10/2008
	 */
	public Collection pesquisarImovelEconomia(Integer idImovel) 
		throws ControladorException {

		try {
			return this.repositorioImovelTarifaSocial
					.pesquisarImovelEconomia(idImovel);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
}
