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
package gcom.gui.relatorio.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.EloAnormalidade;
import gcom.cadastro.imovel.FiltroCadastroOcorrencia;
import gcom.cadastro.imovel.FiltroEloAnormalidade;
import gcom.cadastro.imovel.FiltroPocoTipo;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.PocoTipo;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.CobrancaSituacaoTipo;
import gcom.cobranca.FiltroCobrancaSituacaoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.gui.ActionServletException;
import gcom.gui.cadastro.imovel.ImovelOutrosCriteriosActionForm;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.medicao.FiltroMedicaoTipo;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.imovel.EmissaoBoletimCadastro;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


 /** 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @version 1.0
 */

public class GerarEmissaoBoletimCadastroAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * <<Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		ImovelOutrosCriteriosActionForm form = (ImovelOutrosCriteriosActionForm) actionForm;

		Imovel imovelParametrosInicial = new Imovel();
		Imovel imovelParametrosFinal = new Imovel();
		ClienteImovel clienteImovelParametros = new ClienteImovel();
		LigacaoAgua ligacaoAguaParametrosInicial = new LigacaoAgua();
		LigacaoAgua ligacaoAguaParametrosFinal = new LigacaoAgua();
		LigacaoEsgoto ligacaoEsgotoParametrosInicial = new LigacaoEsgoto();
		LigacaoEsgoto ligacaoEsgotoParametrosFinal = new LigacaoEsgoto();
		ConsumoHistorico consumoHistoricoParametrosInicial = new ConsumoHistorico();
		ConsumoHistorico consumoHistoricoParametrosFinal = new ConsumoHistorico();
		MedicaoHistorico medicaoHistoricoParametrosInicial = new MedicaoHistorico();
		MedicaoHistorico medicaoHistoricoParametrosFinal = new MedicaoHistorico();

			// Gerência Regional

/*		String idGerenciaRegional = (String) form
					.getIdGerenciaRegional();

			GerenciaRegional gerenciaRegional = new GerenciaRegional();

			if (idGerenciaRegional != null
					&& !idGerenciaRegional.equals("")
					&& !idGerenciaRegional.equals(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				gerenciaRegional = fachada
						.pesquisarObjetoGerenciaRegionalRelatorio(new Integer(
								idGerenciaRegional));

			}*/

			// Quadra Inicial

			String numeroQuadraOrigem = (String) form
					.getQuadraOrigemNM();

			Quadra quadraOrigem = new Quadra();

			if (numeroQuadraOrigem != null && !numeroQuadraOrigem.equals("")) {

				quadraOrigem.setNumeroQuadra(new Integer(numeroQuadraOrigem)
						.intValue());

			}

			// Quadra Final

			String numeroQuadraDestino = (String) form
					.getQuadraDestinoNM();

			Quadra quadraDestino = new Quadra();

			if (numeroQuadraDestino != null && !numeroQuadraDestino.equals("")) {

				quadraDestino.setNumeroQuadra(new Integer(numeroQuadraDestino)
						.intValue());

			}

			// Lote Inicial

			String loteOrigem = null;
			String loteOrigemPesquisado = (String) form
					.getLoteOrigem();

			if (loteOrigemPesquisado != null
					&& !loteOrigemPesquisado.equals("")) {
				loteOrigem = loteOrigemPesquisado;
			}

			// Lote Final

			String loteDestino = null;
			String loteDestinoPesquisado = (String) form
					.getLoteDestino();

			if (loteDestinoPesquisado != null
					&& !loteDestinoPesquisado.equals("")) {
				loteDestino = loteDestinoPesquisado;
			}

			// Cep

			Cep cep = new Cep();
			String numeroCep = null;
			String cepPesquisado = (String) form
					.getCEP();

			if (cepPesquisado != null && !cepPesquisado.equals("")) {
				numeroCep = cepPesquisado;
				cep.setCodigo(new Integer(numeroCep));
			}

			// Localidade Inicial

			String idLocalidadeOrigem = (String) form
					.getLocalidadeOrigemID();

			Localidade localidadeOrigem = new Localidade();

			if (idLocalidadeOrigem != null && !idLocalidadeOrigem.equals("")) {

				localidadeOrigem = fachada
						.pesquisarObjetoLocalidadeRelatorio(new Integer(
								idLocalidadeOrigem));

			}

			// Localidade Final

			String idLocalidadeDestino = (String) form
					.getLocalidadeDestinoID();

			Localidade localidadeDestino = new Localidade();

			if (idLocalidadeDestino != null && !idLocalidadeDestino.equals("")) {

				localidadeDestino = fachada
						.pesquisarObjetoLocalidadeRelatorio(new Integer(
								idLocalidadeDestino));

			}

			// Setor Comercial Inicial

			String codigoSetorComercialOrigem = (String) form
					.getSetorComercialOrigemCD();

			SetorComercial setorComercialOrigem = new SetorComercial();

			if (codigoSetorComercialOrigem != null
					&& !codigoSetorComercialOrigem.equals("")) {

				setorComercialOrigem = fachada
						.pesquisarObjetoSetorComercialRelatorio(new Integer(
								codigoSetorComercialOrigem), new Integer(
								idLocalidadeOrigem));

			}

			// Setor Comercial Final

			String codigoSetorComercialDestino = (String) form
					.getSetorComercialDestinoCD();

			SetorComercial setorComercialDestino = new SetorComercial();

			if (codigoSetorComercialDestino != null
					&& !codigoSetorComercialDestino.equals("")) {

				setorComercialDestino = fachada
						.pesquisarObjetoSetorComercialRelatorio(new Integer(
								codigoSetorComercialDestino), new Integer(
								idLocalidadeDestino));

			}

			// Cliente

			String idCliente = (String) form
					.getIdCliente();

			Cliente cliente = new Cliente();

			if (idCliente != null && !idCliente.equals("")) {

				cliente = fachada.pesquisarObjetoClienteRelatorio(new Integer(
						idCliente));

			}

			// Município

	/*			String idMunicipio = (String) form
					.getIdMunicipio();

			Municipio municipio = new Municipio();

			if (idMunicipio != null && !idMunicipio.equals("")) {

				municipio = fachada
						.pesquisarObjetoMunicipioRelatorio(new Integer(
								idMunicipio));

			}*/

			// Bairro

/*			String idBairro = (String) form
					.getIdBairro();

			Bairro bairro = new Bairro();

			if (idBairro != null && !idBairro.equals("")) {

				bairro = fachada.pesquisarObjetoBairroRelatorio(new Integer(
						idBairro), new Integer(idMunicipio));

			}*/

			// Logradouro

			String idLogradouro = (String) form
					.getIdLogradouro();

			Logradouro logradouro = null;

			if (idLogradouro != null && !idLogradouro.equals("")) {
				FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

				filtroLogradouro.adicionarParametro(new ParametroSimples(
						FiltroLogradouro.ID, idLogradouro));
				filtroLogradouro.adicionarParametro(new ParametroSimples(
						FiltroLogradouro.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection logradouros = fachada.pesquisar(filtroLogradouro,
						Logradouro.class.getName());

				if (logradouros != null && !logradouros.isEmpty()) {
					// O logradouro foi encontrado
					Iterator logradouroIterator = logradouros.iterator();

					logradouro = (Logradouro) logradouroIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Logradouro");
				}

			} else {
				logradouro = new Logradouro();
			}

			// Tipo da Relação

			String idRelacaoTipo = null;
			ClienteRelacaoTipo clienteRelacaoTipo = null;

			if (idRelacaoTipo != null
					&& !idRelacaoTipo.equals("")
					&& !idRelacaoTipo
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();

				filtroClienteRelacaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID,
								idRelacaoTipo));
				filtroClienteRelacaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroClienteRelacaoTipo.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoClientesRelacoesTipo = fachada.pesquisar(
						filtroClienteRelacaoTipo, ClienteRelacaoTipo.class
								.getName());

				if (colecaoClientesRelacoesTipo != null
						&& !colecaoClientesRelacoesTipo.isEmpty()) {
					// O Tipo da Relação do Cliente foi encontrada
					Iterator clienteRelacaoTipoIterator = colecaoClientesRelacoesTipo
							.iterator();

					clienteRelacaoTipo = (ClienteRelacaoTipo) clienteRelacaoTipoIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Logradouro");
				}

			} else {
				clienteRelacaoTipo = new ClienteRelacaoTipo();
			}

			// Tipo de Cliente

			String idClienteTipo = (String) form
					.getIdClienteTipo();

			ClienteTipo clienteTipo = null;

			if (idClienteTipo != null
					&& !idClienteTipo.equals("")
					&& !idClienteTipo
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();

				filtroClienteTipo.adicionarParametro(new ParametroSimples(
						FiltroClienteTipo.ID, idClienteTipo));
				filtroClienteTipo.adicionarParametro(new ParametroSimples(
						FiltroClienteTipo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoClientesTipos = fachada.pesquisar(
						filtroClienteTipo, ClienteTipo.class.getName());

				if (colecaoClientesTipos != null
						&& !colecaoClientesTipos.isEmpty()) {
					// O Tipo do cliente foi encontrado
					Iterator clienteTipoIterator = colecaoClientesTipos
							.iterator();

					clienteTipo = (ClienteTipo) clienteTipoIterator.next();
					cliente.setClienteTipo(clienteTipo);

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Tipo de Cliente");
				}

			} else {
				clienteTipo = new ClienteTipo();
				cliente.setClienteTipo(clienteTipo);
			}

			// Imóvel Condomínio

			String idImovelCondominio = (String) form
					.getIdImovelCondominio();

			Imovel imovelCondominio = new Imovel();

			if (idImovelCondominio != null && !idImovelCondominio.equals("")) {

				imovelCondominio.setId(new Integer(idImovelCondominio));

			}

			// Imóvel Principal

			String idImovelPrincipal = (String) form
					.getIdImovelPrincipal();

			Imovel imovelPrincipal = new Imovel();

			if (idImovelPrincipal != null && !idImovelPrincipal.equals("")) {

				imovelPrincipal.setId(new Integer(idImovelPrincipal));

			}

			// Situação Ligação Água

			String idSituacaoLigacaoAgua = (String) form
					.getSituacaoAgua();

			LigacaoAguaSituacao ligacaoAguaSituacao = null;

			if (idSituacaoLigacaoAgua != null
					&& !idSituacaoLigacaoAgua.equals("")
					&& !idSituacaoLigacaoAgua
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

				filtroLigacaoAguaSituacao
						.adicionarParametro(new ParametroSimples(
								FiltroLigacaoAguaSituacao.ID,
								idSituacaoLigacaoAgua));
				filtroLigacaoAguaSituacao
						.adicionarParametro(new ParametroSimples(
								FiltroLigacaoAguaSituacao.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoSituacoesLigacoesAgua = fachada.pesquisar(
						filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class
								.getName());

				if (colecaoSituacoesLigacoesAgua != null
						&& !colecaoSituacoesLigacoesAgua.isEmpty()) {
					// A Situação da Ligação de Água foi encontrada
					Iterator situacaoLigacaoAguaIterator = colecaoSituacoesLigacoesAgua
							.iterator();

					ligacaoAguaSituacao = (LigacaoAguaSituacao) situacaoLigacaoAguaIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Situação da Ligação de Água");
				}

			} else {
				ligacaoAguaSituacao = new LigacaoAguaSituacao();
			}

			// Situação Ligação Esgoto

			String idSituacaoLigacaoEsgoto = (String) form
					.getSituacaoLigacaoEsgoto();

			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;

			if (idSituacaoLigacaoEsgoto != null
					&& !idSituacaoLigacaoEsgoto.equals("")
					&& !idSituacaoLigacaoEsgoto
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();

				filtroLigacaoEsgotoSituacao
						.adicionarParametro(new ParametroSimples(
								FiltroLigacaoEsgotoSituacao.ID,
								idSituacaoLigacaoEsgoto));
				filtroLigacaoEsgotoSituacao
						.adicionarParametro(new ParametroSimples(
								FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoSituacoesLigacoesEsgoto = fachada.pesquisar(
						filtroLigacaoEsgotoSituacao,
						LigacaoEsgotoSituacao.class.getName());

				if (colecaoSituacoesLigacoesEsgoto != null
						&& !colecaoSituacoesLigacoesEsgoto.isEmpty()) {
					// A Situação da Ligação de Esgoto foi encontrada
					Iterator situacaoLigacaoEsgotoIterator = colecaoSituacoesLigacoesEsgoto
							.iterator();

					ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) situacaoLigacaoEsgotoIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Situação da Ligação de Esgoto");
				}

			} else {
				ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
			}

			// Intervalo Consumo Mínimo Fixado de Água

			String consumoFixadoAguaInicial = null;
			String consumoFixadoAguaInicialPesquisado = form
					.getConsumoMinimoInicial();

			if (consumoFixadoAguaInicialPesquisado != null
					&& !consumoFixadoAguaInicialPesquisado.equals("")) {
				consumoFixadoAguaInicial = consumoFixadoAguaInicialPesquisado;
			}

			String consumoFixadoAguaFinal = null;
			String consumoFixadoAguaFinalPesquisado = form
					.getConsumoMinimoFinal();

			if (consumoFixadoAguaFinalPesquisado != null
					&& !consumoFixadoAguaFinalPesquisado.equals("")) {
				consumoFixadoAguaFinal = consumoFixadoAguaFinalPesquisado;
			}

			// Intervalo Consumo Mínimo Fixado de Esgoto

			String consumoFixadoEsgotoInicial = null;
			String consumoFixadoEsgotoInicialPesquisado = form
					.getConsumoMinimoFixadoEsgotoInicial();

			if (consumoFixadoEsgotoInicialPesquisado != null
					&& !consumoFixadoEsgotoInicialPesquisado.equals("")) {
				consumoFixadoEsgotoInicial = consumoFixadoEsgotoInicialPesquisado;
			}

			String consumoFixadoEsgotoFinal = null;
			String consumoFixadoEsgotoFinalPesquisado = form
					.getConsumoMinimoFixadoEsgotoFinal();

			if (consumoFixadoEsgotoFinalPesquisado != null
					&& !consumoFixadoEsgotoFinalPesquisado.equals("")) {
				consumoFixadoEsgotoFinal = consumoFixadoEsgotoFinalPesquisado;
			}

			// Intervalo Percentual Esgoto

			String percentualEsgotoInicial = null;
			String percentualEsgotoInicialPesquisado = form
					.getIntervaloPercentualEsgotoInicial();

			if (percentualEsgotoInicialPesquisado != null
					&& !percentualEsgotoInicialPesquisado.equals("")) {
				percentualEsgotoInicial = percentualEsgotoInicialPesquisado;
			}

			String percentualEsgotoFinal = null;
			String percentualEsgotoFinalPesquisado = form
					.getIntervaloPercentualEsgotoFinal();

			if (percentualEsgotoFinalPesquisado != null
					&& !percentualEsgotoFinalPesquisado.equals("")) {
				percentualEsgotoFinal = percentualEsgotoFinalPesquisado;
			}

			// Indicador Medição
//			String indicadorMedicao = null;
//			String indicadorMedicaoPesquisado = form
//					.getIndicadorMedicao();
//
//			if (indicadorMedicaoPesquisado != null
//					&& !indicadorMedicaoPesquisado.equals("")) {
//				indicadorMedicao = indicadorMedicaoPesquisado;
//			}

			// Medição Tipo

			String idMedicaoTipo = (String) form
					.getTipoMedicao();

			MedicaoTipo medicaoTipo = null;

			if (idMedicaoTipo != null
					&& !idMedicaoTipo.equals("")
					&& !idMedicaoTipo
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				FiltroMedicaoTipo filtroMedicaoTipo = new FiltroMedicaoTipo();

				filtroMedicaoTipo.adicionarParametro(new ParametroSimples(
						FiltroMedicaoTipo.ID, idMedicaoTipo));
				filtroMedicaoTipo.adicionarParametro(new ParametroSimples(
						FiltroMedicaoTipo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoMedicoesTipos = fachada.pesquisar(
						filtroMedicaoTipo, MedicaoTipo.class.getName());

				if (colecaoMedicoesTipos != null
						&& !colecaoMedicoesTipos.isEmpty()) {
					// O Tipo de Medição foi encontrado
					Iterator medicaoTipoIterator = colecaoMedicoesTipos
							.iterator();

					medicaoTipo = (MedicaoTipo) medicaoTipoIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Tipo de Medição");
				}

			} else {
				medicaoTipo = new MedicaoTipo();
			}

			// Intervalo Média Mínima Imóvel

			String mediaMinimaImovelInicial = null;
			String mediaMinimaImovelInicialPesquisado = form
					.getIntervaloMediaMinimaImovelInicio();

			if (mediaMinimaImovelInicialPesquisado != null
					&& !mediaMinimaImovelInicialPesquisado.equals("")) {
				mediaMinimaImovelInicial = mediaMinimaImovelInicialPesquisado;
			}

			String mediaMinimaImovelFinal = null;
			String mediaMinimaImovelFinalPesquisado = form
					.getIntervaloMediaMinimaImovelFinal();

			if (mediaMinimaImovelFinalPesquisado != null
					&& !mediaMinimaImovelFinalPesquisado.equals("")) {
				mediaMinimaImovelFinal = mediaMinimaImovelFinalPesquisado;
			}

			// Intervalo Média Mínima Hidrômetro

			String mediaMinimahidrometroInicial = null;
			String mediaMinimahidrometroInicialPesquisado = form
					.getIntervaloMediaMinimaHidrometroInicio();

			if (mediaMinimahidrometroInicialPesquisado != null
					&& !mediaMinimahidrometroInicialPesquisado.equals("")) {
				mediaMinimahidrometroInicial = mediaMinimahidrometroInicialPesquisado;
			}

			String mediaMinimahidrometroFinal = null;
			String mediaMinimahidrometroFinalPesquisado = form
					.getIntervaloMediaMinimaHidrometroFinal();

			if (mediaMinimahidrometroFinalPesquisado != null
					&& !mediaMinimahidrometroFinalPesquisado.equals("")) {
				mediaMinimahidrometroFinal = mediaMinimahidrometroFinalPesquisado;
			}

			// Perfil do Imóvel
			ImovelPerfil imovelPerfil = new ImovelPerfil();

			// if (idImovelPerfil != null
			// && !idImovelPerfil.equals("")
			// && !idImovelPerfil
			// .equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
			// FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			//
			// filtroImovelPerfil.adicionarParametro(new ParametroSimples(
			// FiltroImovelPerfil.ID, idMedicaoTipo));
			// filtroImovelPerfil.adicionarParametro(new ParametroSimples(
			// FiltroImovelPerfil.INDICADOR_USO,
			// ConstantesSistema.INDICADOR_USO_ATIVO));
			//
			// Collection colecaoImoveisPerfis = fachada.pesquisar(
			// filtroImovelPerfil, ImovelPerfil.class.getName());
			//
			// if (colecaoImoveisPerfis != null
			// && !colecaoImoveisPerfis.isEmpty()) {
			// // O Perfil do Imóvel foi encontrado
			// Iterator imovelPerfilIterator = colecaoImoveisPerfis
			// .iterator();
			//
			// imovelPerfil = (ImovelPerfil) imovelPerfilIterator.next();
			//
			// } else {
			// throw new ActionServletException(
			// "atencao.pesquisa_inexistente", null,
			// "Perfil do Imóvel");
			// }
			//
			// } else {
			// imovelPerfil = new ImovelPerfil();
			// }

			// Categoria

/*			String idCategoria = (String) form
					.getCategoriaImovel();

			Categoria categoria = null;

			if (idCategoria != null
					&& !idCategoria.equals("")
					&& !idCategoria
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				FiltroCategoria filtroCategoria = new FiltroCategoria();

				filtroCategoria.adicionarParametro(new ParametroSimples(
						FiltroCategoria.CODIGO, idCategoria));
				filtroCategoria.adicionarParametro(new ParametroSimples(
						FiltroCategoria.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoCategorias = fachada.pesquisar(
						filtroCategoria, Categoria.class.getName());

				if (colecaoCategorias != null && !colecaoCategorias.isEmpty()) {
					// A categoria foi encontrado
					Iterator categoriaIterator = colecaoCategorias.iterator();

					categoria = (Categoria) categoriaIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Categoria");
				}

			} else {
				categoria = new Categoria();
			}

			// SubCategoria

			String idSubCategoria = (String) form
					.getSubcategoria();

			Subcategoria subcategoria = null;

			if (idSubCategoria != null
					&& !idSubCategoria.equals("")
					&& !idSubCategoria
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();

				filtroSubCategoria.adicionarParametro(new ParametroSimples(
						FiltroSubCategoria.ID, idSubCategoria));

				Collection colecaoSubCategorias = fachada.pesquisar(
						filtroSubCategoria, Subcategoria.class.getName());

				if (colecaoSubCategorias != null
						&& !colecaoSubCategorias.isEmpty()) {
					// A subcategoria foi encontrado
					Iterator subCategoriaIterator = colecaoSubCategorias
							.iterator();

					subcategoria = (Subcategoria) subCategoriaIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Subcategoria");
				}

			} else {
				subcategoria = new Subcategoria();
			}*/

			// Intervalo Qtde de economias

			Integer qtdeEconomiasInicial = null;
			String qtdeEconomiasInicialPesquisado = form
					.getQuantidadeEconomiasInicial();

			if (qtdeEconomiasInicialPesquisado != null
					&& !qtdeEconomiasInicialPesquisado.equals("")) {
				qtdeEconomiasInicial = new Integer(
						qtdeEconomiasInicialPesquisado);
			}

			Integer qtdeEconomiasFinal = null;
			String qtdeEconomiasFinalPesquisado = form
					.getQuantidadeEconomiasFinal();

			if (qtdeEconomiasFinalPesquisado != null
					&& !qtdeEconomiasFinalPesquisado.equals("")) {
				qtdeEconomiasFinal = new Integer(qtdeEconomiasFinalPesquisado);
			}

			// Intervalo Número de Pontos

			Short numeroPontosInicial = null;
			String numeroPontosInicialPesquisado = form
					.getNumeroPontosInicial();

			if (numeroPontosInicialPesquisado != null
					&& !numeroPontosInicialPesquisado.equals("")) {
				numeroPontosInicial = new Short(numeroPontosInicialPesquisado);
			}

			Short numeroPontosFinal = null;
			String numeroPontosFinalPesquisado = form
					.getNumeroPontosFinal();

			if (numeroPontosFinalPesquisado != null
					&& !numeroPontosFinalPesquisado.equals("")) {
				numeroPontosFinal = new Short(numeroPontosFinalPesquisado);
			}

			// Intervalo Número de Moradores

			Short numeroMoradoresInicial = null;
			String numeroMoradoresInicialPesquisado = form
					.getNumeroMoradoresInicial();

			if (numeroMoradoresInicialPesquisado != null
					&& !numeroMoradoresInicialPesquisado.equals("")) {
				numeroMoradoresInicial = new Short(
						numeroMoradoresInicialPesquisado);
			}

			Short numeroMoradoresFinal = null;
			String numeroMoradoresFinalPesquisado = form
					.getNumeroMoradoresFinal();

			if (numeroMoradoresFinalPesquisado != null
					&& !numeroMoradoresFinalPesquisado.equals("")) {
				numeroMoradoresFinal = new Short(numeroMoradoresFinalPesquisado);
			}

			// Intervalo Área Construída

			BigDecimal areaConstruidaInicial = null;
			String areaConstruidaInicialPesquisado = form
					.getAreaConstruidaInicial();

			if (areaConstruidaInicialPesquisado != null
					&& !areaConstruidaInicialPesquisado.equals("")) {
				areaConstruidaInicial = Util
						.formatarMoedaRealparaBigDecimal(areaConstruidaInicialPesquisado);
			}

			BigDecimal areaConstruidaFinal = null;
			String areaConstruidaFinalPesquisado = form
					.getAreaConstruidaFinal();

			if (areaConstruidaFinalPesquisado != null
					&& !areaConstruidaFinalPesquisado.equals("")) {
				areaConstruidaFinal = Util
						.formatarMoedaRealparaBigDecimal(areaConstruidaFinalPesquisado);
			}

			// Tipo de Poço

			String idPocoTipo = (String) form
					.getTipoPoco();

			PocoTipo pocoTipo = null;

			if (idPocoTipo != null
					&& !idPocoTipo.equals("")
					&& !idPocoTipo
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				FiltroPocoTipo filtroPocoTipo = new FiltroPocoTipo();

				filtroPocoTipo.adicionarParametro(new ParametroSimples(
						FiltroPocoTipo.ID, idPocoTipo));
				filtroPocoTipo.adicionarParametro(new ParametroSimples(
						FiltroPocoTipo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoPocosTipos = fachada.pesquisar(
						filtroPocoTipo, PocoTipo.class.getName());

				if (colecaoPocosTipos != null && !colecaoPocosTipos.isEmpty()) {
					// O Tipo do Poço foi encontrado
					Iterator pocoTipoIterator = colecaoPocosTipos.iterator();

					pocoTipo = (PocoTipo) pocoTipoIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Tipo de Poço");
				}

			} else {
				pocoTipo = new PocoTipo();
			}

			// Tipo Situação Especial Faturamento

			String idTipoSituacaoFaturamento = (String) form
					.getTipoSituacaoEspecialFaturamento();

			FaturamentoSituacaoTipo faturamentoSituacaoTipo = null;

			if (idTipoSituacaoFaturamento != null
					&& !idTipoSituacaoFaturamento.equals("")
					&& !idTipoSituacaoFaturamento
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();

				filtroFaturamentoSituacaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroFaturamentoSituacaoTipo.ID,
								idTipoSituacaoFaturamento));
				filtroFaturamentoSituacaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroFaturamentoSituacaoTipo.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoFaturamentosSituacoesTipo = fachada
						.pesquisar(filtroFaturamentoSituacaoTipo,
								FaturamentoSituacaoTipo.class.getName());

				if (colecaoFaturamentosSituacoesTipo != null
						&& !colecaoFaturamentosSituacoesTipo.isEmpty()) {
					// O Tipo do Faturamento Situacao Especial foi
					// encontrado
					Iterator faturamentoSituacaoTipoIterator = colecaoFaturamentosSituacoesTipo
							.iterator();

					faturamentoSituacaoTipo = (FaturamentoSituacaoTipo) faturamentoSituacaoTipoIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Tipo do Faturamento Situacao Especial");
				}

			} else {
				faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
			}

			// Tipo Situação Especial Cobrança

			String idTipoSituacaoCobranca = (String) form
					.getTipoSituacaoEspecialCobranca();

			CobrancaSituacaoTipo cobrancaSituacaoTipo = null;

			if (idTipoSituacaoCobranca != null
					&& !idTipoSituacaoCobranca.equals("")
					&& !idTipoSituacaoCobranca
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				FiltroCobrancaSituacaoTipo filtroCobrancaSituacaoTipo = new FiltroCobrancaSituacaoTipo();

				filtroCobrancaSituacaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroCobrancaSituacaoTipo.ID,
								idTipoSituacaoCobranca));

				Collection colecaoCobrancasSituacoesTipo = fachada.pesquisar(
						filtroCobrancaSituacaoTipo, CobrancaSituacaoTipo.class
								.getName());

				if (colecaoCobrancasSituacoesTipo != null
						&& !colecaoCobrancasSituacoesTipo.isEmpty()) {
					// O Tipo da Cobrança Situacao Especial foi encontrado
					Iterator cobrancaSituacaoTipoIterator = colecaoCobrancasSituacoesTipo
							.iterator();

					cobrancaSituacaoTipo = (CobrancaSituacaoTipo) cobrancaSituacaoTipoIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Tipo da Cobrança Situacao Especial");
				}

			} else {
				cobrancaSituacaoTipo = new CobrancaSituacaoTipo();
			}

			// Situação Cobrança

			String idSituacaoCobranca = (String) form
					.getSituacaoCobranca();

		/*	CobrancaSituacao cobrancaSituacao = null;

			if (idSituacaoCobranca != null
					&& !idSituacaoCobranca.equals("")
					&& !idSituacaoCobranca
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();

				filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
						FiltroCobrancaSituacao.ID, idSituacaoCobranca));

				Collection colecaoCobrancasSituacoes = fachada.pesquisar(
						filtroCobrancaSituacao, CobrancaSituacao.class
								.getName());

				if (colecaoCobrancasSituacoes != null
						&& !colecaoCobrancasSituacoes.isEmpty()) {
					// A Cobrança Situacao foi encontrada
					Iterator cobrancaSituacaoIterator = colecaoCobrancasSituacoes
							.iterator();

					cobrancaSituacao = (CobrancaSituacao) cobrancaSituacaoIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Cobrança Situacao");
				}

			} else {
				cobrancaSituacaoTipo = new CobrancaSituacaoTipo();
			}*/

			// Dia Vencimento Alternativo

			Short diaVencimentoAlternativo = null;
			String diaVencimentoAlternativoPesquisado = form
					.getDiaVencimentoAlternativo();

			if (diaVencimentoAlternativoPesquisado != null
					&& !diaVencimentoAlternativoPesquisado.equals("")) {
				diaVencimentoAlternativo = new Short(
						diaVencimentoAlternativoPesquisado);
			}

			// Ocorrência de Cadastro

			String idCadastroOcorrencia = (String) form
					.getOcorrenciaCadastro();

			CadastroOcorrencia cadastroOcorrencia = null;

			if (idCadastroOcorrencia != null
					&& !idCadastroOcorrencia.equals("")
					&& !idCadastroOcorrencia
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				FiltroCadastroOcorrencia filtroCadastroOcorrencia = new FiltroCadastroOcorrencia();

				filtroCadastroOcorrencia
						.adicionarParametro(new ParametroSimples(
								FiltroCadastroOcorrencia.ID, idSituacaoCobranca));

				Collection colecaoCadastrosOcorrencias = fachada.pesquisar(
						filtroCadastroOcorrencia, CadastroOcorrencia.class
								.getName());

				if (colecaoCadastrosOcorrencias != null
						&& !colecaoCadastrosOcorrencias.isEmpty()) {
					// A Ocorrência de Cadastro foi encontrada
					Iterator cadastroOcorrenciaIterator = colecaoCadastrosOcorrencias
							.iterator();

					cadastroOcorrencia = (CadastroOcorrencia) cadastroOcorrenciaIterator
							.next();

				} else {
					///throw new ActionServletException(
					//		"atencao.pesquisa_inexistente", null,
					//		"Ocorrência de Cadastro");
				}

			} else {
				cadastroOcorrencia = new CadastroOcorrencia();
			}

			// Tarifa de Consumo

			String idTarifaConsumo = (String) form
					.getTarifaConsumo();

			ConsumoTarifa consumoTarifa = null;

			if (idTarifaConsumo != null
					&& !idTarifaConsumo.equals("")
					&& !idTarifaConsumo
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();

				filtroConsumoTarifa.adicionarParametro(new ParametroSimples(
						FiltroConsumoTarifa.ID, idTarifaConsumo));

				Collection colecaoConsumosTarifas = fachada.pesquisar(
						filtroConsumoTarifa, ConsumoTarifa.class.getName());

				if (colecaoConsumosTarifas != null
						&& !colecaoConsumosTarifas.isEmpty()) {
					// A Tarifa de Consumo foi encontrada
					Iterator consumoTarifaIterator = colecaoConsumosTarifas
							.iterator();

					consumoTarifa = (ConsumoTarifa) consumoTarifaIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Tarifa de Consumo");
				}

			} else {
				consumoTarifa = new ConsumoTarifa();
			}

			// Anormalidade do Elo

			String idEloAnormalidade = (String) form
					.getAnormalidadeElo();

			EloAnormalidade eloAnormalidade = null;

			if (idEloAnormalidade != null
					&& !idEloAnormalidade.equals("")
					&& !idEloAnormalidade
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				FiltroEloAnormalidade filtroEloAnormalidade = new FiltroEloAnormalidade();

				filtroEloAnormalidade.adicionarParametro(new ParametroSimples(
						FiltroEloAnormalidade.ID, idEloAnormalidade));

				Collection colecaoElosAnormalidades = fachada.pesquisar(
						filtroEloAnormalidade, EloAnormalidade.class.getName());

				if (colecaoElosAnormalidades != null
						&& !colecaoElosAnormalidades.isEmpty()) {
					// A Anormalidade do Elo foi encontrada
					Iterator eloAnormalidadeIterator = colecaoElosAnormalidades
							.iterator();

					eloAnormalidade = (EloAnormalidade) eloAnormalidadeIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Anormalidade do Elo");
				}

			} else {
				eloAnormalidade = new EloAnormalidade();
			}

			// seta os parametros que serão mostrados no relatório

			clienteImovelParametros.setCliente(cliente);
			clienteImovelParametros.setClienteRelacaoTipo(clienteRelacaoTipo);
			ligacaoAguaParametrosInicial
					.setNumeroConsumoMinimoAgua(consumoFixadoAguaInicial == null ? null
							: new Integer(consumoFixadoAguaInicial));
			ligacaoAguaParametrosFinal
					.setNumeroConsumoMinimoAgua(consumoFixadoAguaFinal == null ? null
							: new Integer(consumoFixadoAguaFinal));
			ligacaoEsgotoParametrosInicial
					.setConsumoMinimo(consumoFixadoEsgotoInicial == null ? null
							: new Integer(consumoFixadoEsgotoInicial));
			ligacaoEsgotoParametrosInicial
					.setPercentual(percentualEsgotoInicial == null ? null
							: new BigDecimal(percentualEsgotoInicial));
			ligacaoEsgotoParametrosFinal
					.setConsumoMinimo(consumoFixadoEsgotoFinal == null ? null
							: new Integer(consumoFixadoEsgotoFinal));
			ligacaoEsgotoParametrosFinal
					.setPercentual(percentualEsgotoFinal == null ? null
							: new BigDecimal(percentualEsgotoFinal));
			consumoHistoricoParametrosInicial
					.setConsumoMedio(mediaMinimaImovelInicial == null ? null
							: new Integer(mediaMinimaImovelInicial));
			consumoHistoricoParametrosFinal
					.setConsumoMedio(mediaMinimaImovelFinal == null ? null
							: new Integer(mediaMinimaImovelFinal));
			medicaoHistoricoParametrosInicial
					.setConsumoMedioHidrometro(mediaMinimahidrometroInicial == null ? null
							: new Integer(mediaMinimahidrometroInicial));
			medicaoHistoricoParametrosInicial.setMedicaoTipo(medicaoTipo);
			medicaoHistoricoParametrosFinal
					.setConsumoMedioHidrometro(mediaMinimahidrometroFinal == null ? null
							: new Integer(mediaMinimahidrometroFinal));
			imovelParametrosInicial.setLocalidade(localidadeOrigem);
			imovelParametrosInicial.setSetorComercial(setorComercialOrigem);
			imovelParametrosInicial.setQuadra(quadraOrigem);
			imovelParametrosInicial.setLote(loteOrigem == null ? 0 : new Short(
					loteOrigem));

			LogradouroCep logradouroCep = null;

			if (cep.getCepId() != null && logradouro.getId() != null) {

				logradouroCep = fachada.pesquisarAssociacaoLogradouroCep(cep
						.getCepId(), logradouro.getId());

			} else {
				logradouroCep = new LogradouroCep();
				if (cep.getCodigo() != null) {
					logradouroCep.setCep(cep);
				}
				if (logradouro.getId() != null) {
					logradouroCep.setLogradouro(logradouro);
				}
			}

			imovelParametrosInicial.setLogradouroCep(logradouroCep);
			imovelParametrosInicial.setImovelCondominio(imovelCondominio);
			imovelParametrosInicial.setImovelPrincipal(imovelPrincipal);
			imovelParametrosInicial.setLigacaoAguaSituacao(ligacaoAguaSituacao);
			imovelParametrosInicial
					.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
			imovelParametrosInicial.setConsumoTarifa(consumoTarifa);
			imovelParametrosInicial
					.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
			imovelParametrosInicial
					.setCobrancaSituacaoTipo(cobrancaSituacaoTipo);
			imovelParametrosInicial.setDiaVencimento(diaVencimentoAlternativo);
			imovelParametrosInicial.setEloAnormalidade(eloAnormalidade);
			imovelParametrosInicial.setCadastroOcorrencia(cadastroOcorrencia);
			imovelParametrosInicial.setImovelPerfil(imovelPerfil);
			imovelParametrosInicial.setPocoTipo(pocoTipo);
//			imovelParametrosInicial.setNomeConta(nomeConta);
			imovelParametrosInicial
					.setNumeroPontosUtilizacao(numeroPontosInicial == null ? 0
							: numeroPontosInicial);
			imovelParametrosInicial
					.setNumeroMorador(numeroMoradoresInicial == null ? 0
							: numeroMoradoresInicial);
			imovelParametrosInicial
					.setAreaConstruida(areaConstruidaInicial == null ? new BigDecimal(
							0)
							: areaConstruidaInicial);
			imovelParametrosInicial
					.setQuantidadeEconomias(qtdeEconomiasInicial == null ? 0
							: qtdeEconomiasInicial.shortValue());
			imovelParametrosInicial
					.setLigacaoEsgoto(ligacaoEsgotoParametrosInicial);
			imovelParametrosInicial
					.setLigacaoAgua(ligacaoAguaParametrosInicial);
			imovelParametrosFinal.setLocalidade(localidadeDestino);
			imovelParametrosFinal.setSetorComercial(setorComercialDestino);
			imovelParametrosFinal.setQuadra(quadraDestino);
			imovelParametrosFinal.setLote(loteDestino == null ? 0 : new Short(
					loteDestino));
			imovelParametrosFinal
					.setNumeroPontosUtilizacao(numeroPontosFinal == null ? 0
							: numeroPontosFinal);
			imovelParametrosFinal
					.setNumeroMorador(numeroMoradoresFinal == null ? 0
							: numeroMoradoresFinal);
			imovelParametrosFinal
					.setAreaConstruida(areaConstruidaFinal == null ? new BigDecimal(
							0)
							: areaConstruidaFinal);
			imovelParametrosFinal
					.setQuantidadeEconomias(qtdeEconomiasFinal == null ? 0
							: qtdeEconomiasFinal.shortValue());
			imovelParametrosFinal.setLigacaoAgua(ligacaoAguaParametrosFinal);
			imovelParametrosFinal
					.setLigacaoEsgoto(ligacaoEsgotoParametrosFinal);
			
			EmissaoBoletimCadastro boletimCadastro = new EmissaoBoletimCadastro((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

//			relatorio.addParametro(
//					"imovelParametrosInicial", imovelParametrosInicial);
//			relatorio.addParametro(
//					"imovelParametrosFinal", imovelParametrosFinal);
//			relatorio.addParametro(
//					"clienteImovelParametros", clienteImovelParametros);
//			relatorio.addParametro("municipio",
//					municipio);
//			relatorio.addParametro("bairro", bairro);
//			relatorio.addParametro(
//					"medicaoHistoricoParametrosInicial",
//					medicaoHistoricoParametrosInicial);
//			relatorio.addParametro(
//					"medicaoHistoricoParametrosFinal",
//					medicaoHistoricoParametrosFinal);
//			relatorio.addParametro(
//					"consumoHistoricoParametrosInicial",
//					consumoHistoricoParametrosInicial);
//			relatorio.addParametro(
//					"consumoHistoricoParametrosFinal",
//					consumoHistoricoParametrosFinal);
//			relatorio.addParametro(
//					"gerenciaRegionalParametro", gerenciaRegional);
//			relatorio.addParametro("categoria",
//					categoria);
//			relatorio.addParametro("subcategoria",
//					subcategoria);
//			relatorio.addParametro(
//					"cobrancaSituacao", cobrancaSituacao);
//			boletimCadastro.addParametro(
//					"indicadorMedicaoParametro", indicadorMedicao);

			// Parâmetros para formação da query

			// id da genrencia regional
			boletimCadastro.addParametro(
					"gerenciaRegional", form
							.getIdGerenciaRegional());
			// id da genrencia regional
			boletimCadastro.addParametro(
					"idUnidadeNegocio", form
							.getUnidadeNegocio());
			// numero da quadra origem
			boletimCadastro.addParametro("quadraOrigem",
					form.getQuadraOrigemNM());
			// numero quadra destino
			boletimCadastro.addParametro("quadraDestino",
					form.getQuadraDestinoNM());
			// lote origem
			boletimCadastro.addParametro("loteOrigem",
					form.getLoteOrigem());
			// lote destino
			boletimCadastro.addParametro("loteDestino",
					form.getLoteDestino());
			// cep
			boletimCadastro.addParametro("cep",
					form.getCEP());
			// id localidade origem
			boletimCadastro.addParametro(
					"localidadeOrigem", form
							.getLocalidadeOrigemID());
			// id localidade destino
			boletimCadastro.addParametro(
					"localidadeDestino", form
							.getLocalidadeDestinoID());
			// setor comercial origem ID
			boletimCadastro.addParametro(
					"setorComercialOrigemCD", form
							.getSetorComercialOrigemCD());
			// setor comercial destino ID
			boletimCadastro.addParametro(
					"setorComercialDestinoCD", form
							.getSetorComercialDestinoCD());
			// cliente ID
			boletimCadastro.addParametro("clienteID",
					form.getIdCliente());
			// municipio ID
			boletimCadastro.addParametro("municipioID",
					form.getIdMunicipio());
			// bairro ID
			boletimCadastro.addParametro("bairroID",
					form.getIdBairro());
			// logradouro ID
			boletimCadastro.addParametro("logradouroID",
					form.getIdLogradouro());

			// cliente tipo ID
			boletimCadastro.addParametro("clienteTipoID",
					form.getDescricao());

			// cliente relacao tipo ID
			boletimCadastro.addParametro(
					"clienteRelacaoTipoID", form
							.getIndicadorUso());

			// imovel condominio ID
			boletimCadastro.addParametro(
					"imovelCondominioID", form
							.getIdImovelCondominio());
			// imovel Principal ID
			boletimCadastro.addParametro(
					"imovelPrincipalID", form
							.getIdImovelPrincipal());
			// nome Conta ID
			boletimCadastro.addParametro("nomeContaID",
					form.getIdNomeConta());
			// situacao ligacao Agua
			boletimCadastro.addParametro("situacaoAgua",
					form.getSituacaoAgua());
			// consumo Minimo Inicial agua
			boletimCadastro.addParametro(
					"consumoMinimoInicial", form
							.getConsumoMinimoInicial());
			// consumo Minimo Final agua
			boletimCadastro.addParametro(
					"consumoMinimoFinal", form
							.getConsumoMinimoFinal());

			// situacao Ligacao Esgoto
			boletimCadastro.addParametro(
					"situacaoLigacaoEsgoto", form
							.getSituacaoLigacaoEsgoto());
			// consumo Minimo Fixado Esgoto Inicial
			boletimCadastro.addParametro(
					"consumoMinimoFixadoEsgotoInicial",
					form
							.getConsumoMinimoFixadoEsgotoInicial());
			// consumo Minimo Fixado Esgoto Final
			boletimCadastro.addParametro(
					"consumoMinimoFixadoEsgotoFinal",
					form
							.getConsumoMinimoFixadoEsgotoFinal());

			// intervalo Percentual Esgoto Inicial
			boletimCadastro.addParametro(
					"intervaloPercentualEsgotoInicial",
					form
							.getIntervaloPercentualEsgotoInicial());
			// intervalor Percentual Esgoto Final
			boletimCadastro.addParametro(
					"intervaloPercentualEsgotoFinal",
					form
							.getIntervaloPercentualEsgotoFinal());
			// indicador Medicao
			boletimCadastro.addParametro(
					"indicadorMedicao", form
							.getIndicadorMedicao());
			// tipo Medicao ID
			boletimCadastro.addParametro("tipoMedicaoID",
					form.getTipoMedicao());
			// intervalo Media Minima Imovel Inicial
			boletimCadastro.addParametro(
					"intervaloMediaMinimaImovelInicial",
					form
							.getIntervaloMediaMinimaImovelInicio());
			// intervalo Media Minima Imovel Final
			boletimCadastro.addParametro(
					"intervaloMediaMinimaImoveFinal",
					form
							.getIntervaloMediaMinimaImovelFinal());
			// intervalo Media Minima Hidrometro Inicial
			boletimCadastro.addParametro(
					"intervaloMediaMinimaHidrometroInicial",
					form
							.getIntervaloMediaMinimaHidrometroInicio());
			// intervalo Media Minima Hidrometro Final
			boletimCadastro.addParametro(
					"intervaloMediaMinimaHidrometroFinal",
					form
							.getIntervaloMediaMinimaHidrometroFinal());
			// perfil Imovel ID
			boletimCadastro.addParametro("perfilImovelID",
					form.getPerfilImovel());
			// categoria Imovel ID
			boletimCadastro.addParametro(
					"categoriaImovelID", form
							.getCategoriaImovel());
			// sub categoria ID
			boletimCadastro.addParametro("subCategoriaID",
					form.getSubcategoria());
			// quantidade Economias Inicial
			boletimCadastro.addParametro(
					"quantidadeEconomiasInicial",
					form
							.getQuantidadeEconomiasInicial());
			// quantidade Economias Final
			boletimCadastro.addParametro(
					"quantidadeEconomiasFinal", form
							.getQuantidadeEconomiasFinal());
			// numero Pontos Inicial
			boletimCadastro.addParametro(
					"numeroPontosInicial", form
							.getNumeroPontosInicial());
			// numero Pontos Final
			boletimCadastro.addParametro(
					"numeroPontosFinal", form
							.getNumeroPontosFinal());
			// numero Moradores Inicial
			boletimCadastro.addParametro(
					"numeroMoradoresInicial", form
							.getNumeroMoradoresInicial());
			// numero Moradoras Final
			boletimCadastro.addParametro(
					"numeroMoradoresFinal", form
							.getNumeroMoradoresFinal());
			// area Construida Inicial
			boletimCadastro.addParametro(
					"areaConstruidaInicial", form
							.getAreaConstruidaInicial());
			// area Construida Final
			boletimCadastro.addParametro(
					"areaConstruidaFinal", form
							.getAreaConstruidaFinal());
			// area Construida Faixa
			boletimCadastro.addParametro(
					"areaConstruidaFaixa", form
							.getAreaConstruidaFaixa());
			// poco Tipo ID
			boletimCadastro.addParametro("pocoTipoID",
					form.getTipoPoco());
			// tipo Situacao Faturamento ID
			boletimCadastro.addParametro(
					"tipoSituacaoFaturamentoID",
					form
							.getTipoSituacaoEspecialFaturamento());
			// tipo Situacao Especial Cobranca ID
			boletimCadastro.addParametro(
					"tipoSituacaoEspecialCobrancaID",
					form
							.getTipoSituacaoEspecialCobranca());
			// situacao Cobranca ID
			boletimCadastro.addParametro(
					"situacaoCobrancaID", form
							.getSituacaoCobranca());
			// dia Vencimento Alternativo
			boletimCadastro.addParametro(
					"diaVencimentoAlternativo", form
							.getDiaVencimentoAlternativo());
			// ocorrencia Cadastro
			boletimCadastro.addParametro(
					"ocorrenciaCadastro", form
							.getOcorrenciaCadastro());
			// tarifa Consumo
			boletimCadastro.addParametro("tarifaConsumo",
					form.getTarifaConsumo());
			// anormalidade Elo
			boletimCadastro.addParametro(
					"anormalidadeElo", form
							.getAnormalidadeElo());
			boletimCadastro.addParametro("tipoFormatoRelatorio", Integer
					.parseInt("" + TarefaRelatorio.TIPO_HTML));
			
			boletimCadastro.addParametro("indicadorCpfCnpj", 
					form.getIndicadorCpfCnpjInformado());
			boletimCadastro.addParametro("cpfCnpj",
					form.getCpfCnpj());
			
			String tipoRelatorio = TarefaRelatorio.TIPO_HTML + "";
			
			retorno = processarExibicaoRelatorio(boletimCadastro, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);

//			montarPaginaSucesso(httpServletRequest, "Boletim de Cadastro emitido com sucesso.",
//					"Realizar outra Manutenção", "exibirFiltrarUnidadeOrganizacionalAction.do?menu=sim");


		return retorno;
	}
}
