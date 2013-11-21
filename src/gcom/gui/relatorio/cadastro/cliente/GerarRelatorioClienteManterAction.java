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
package gcom.gui.relatorio.cadastro.cliente;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.cliente.FiltroOrgaoExpedidorRg;
import gcom.cadastro.cliente.FiltroPessoaSexo;
import gcom.cadastro.cliente.FiltroProfissao;
import gcom.cadastro.cliente.FiltroRamoAtividade;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.cliente.PessoaSexo;
import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.cadastro.cliente.FiltrarClienteActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.cliente.RelatorioManterCliente;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @created 13 de Outubro de 2005
 * @version 1.0
 */

public class GerarRelatorioClienteManterAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
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
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String indicadorTela = (String) sessao.getAttribute("indicadorTela");

		Collection clientes = (Collection) sessao.getAttribute("clientes");

		// Collection clientesOutrosCriterios = (Collection) sessao
		// .getAttribute("clientesNovos");

		FiltroCliente filtroCliente = (FiltroCliente) sessao
				.getAttribute("filtroCliente");

		// ClienteEndereco clienteEndereco = null;

		Municipio municipio = null;

		Cliente clienteParametros = new Cliente();

		ClienteEndereco clienteEnderecoParametros = new ClienteEndereco();

		ClienteImovel clienteImovelParametros = new ClienteImovel();

		if (sessao.getAttribute("clienteOutrosCriterios") == null
				|| sessao.getAttribute("clienteOutrosCriterios").equals("")) {

			// Iterator colecaoClienteSessao = clientes.iterator();
			// Cliente cliente;
			//
			// while (colecaoClienteSessao.hasNext()) {
			// cliente = (Cliente) colecaoClienteSessao.next();
			//
			// if (cliente.getId() != null && !cliente.getId().equals("")) {
			//
			// FiltroClienteEndereco filtroClienteEndereco = new
			// FiltroClienteEndereco();
			//
			// filtroClienteEndereco
			// .adicionarParametro(new ParametroSimples(
			// FiltroClienteEndereco.CLIENTE_ID, cliente
			// .getId()));
			//
			// Collection clientesEnderecos = fachada.pesquisar(
			// filtroClienteEndereco, ClienteEndereco.class
			// .getName());
			//
			// if (clientesEnderecos != null
			// && !clientesEnderecos.isEmpty()) {
			// // O Endereço foi encontrado
			// Iterator clienteEnderecoIterator = clientesEnderecos
			// .iterator();
			// while (clienteEnderecoIterator.hasNext()) {
			// clienteEndereco = (ClienteEndereco) clienteEnderecoIterator
			// .next();
			//
			// if (clienteEndereco
			// .getIndicadorEnderecoCorrespondencia()
			// .equals(new Short("1"))) {
			// break;
			// }
			// }
			// }
			// }
			// }

			// Inicio da parte que vai mandar os parametros para o relatório

			FiltrarClienteActionForm filtrarClienteActionForm = (FiltrarClienteActionForm) actionForm;

			String cpf = null;
			String cpfPesquisado = (String) filtrarClienteActionForm
					.getCpfClienteFiltro();

			if (cpfPesquisado != null && !cpfPesquisado.equals("")) {
				cpf = cpfPesquisado;
				// filtroCliente.adicionarParametro(new ParametroSimples(
				// FiltroCliente.CPF, cpf));
			}

			String rg = null;
			String rgPesquisado = filtrarClienteActionForm.getRgClienteFiltro();

			if (rgPesquisado != null && !rgPesquisado.equals("")) {
				rg = rgPesquisado;
				// filtroCliente.adicionarParametro(new ParametroSimples(
				// FiltroCliente.RG, rg));
			}

			String cnpj = null;
			String cnpjPesquisado = (String) filtrarClienteActionForm
					.getCnpjClienteFiltro();

			if (cnpjPesquisado != null && !cnpjPesquisado.equals("")) {
				cnpj = cnpjPesquisado;
				// filtroCliente.adicionarParametro(new ParametroSimples(
				// FiltroCliente.CNPJ, cnpj));
			}

			String codigo = null;
			String codigoPesquisado = (String) filtrarClienteActionForm
					.getCodigoClienteFiltro();

			if (codigoPesquisado != null && !codigoPesquisado.equals("")) {
				codigo = codigoPesquisado;
				// filtroCliente.adicionarParametro(new ParametroSimples(
				// FiltroCliente.ID, codigo));
			}

			String nome = null;
			String nomePesquisado = (String) filtrarClienteActionForm
					.getNomeClienteFiltro();

			if (nomePesquisado != null && !nomePesquisado.equals("")) {
				nome = nomePesquisado;
				// filtroCliente.adicionarParametro(new ComparacaoTexto(
				// FiltroCliente.NOME, nome));
			}

			Cep cep = new Cep();
			String numeroCep = null;
			String cepPesquisado = (String) filtrarClienteActionForm
					.getCepClienteFiltro();

			if (cepPesquisado != null && !cepPesquisado.equals("")) {
				numeroCep = cepPesquisado;
				cep.setCodigo(new Integer(numeroCep));

				// filtroCliente.adicionarParametro(new ParametroSimplesColecao(
				// FiltroCliente.CEP, cep));
			}

			Logradouro logradouro = null;
			String idLogradouro = (String) filtrarClienteActionForm
					.getLogradouroClienteFiltro();

			if (idLogradouro != null && !idLogradouro.equals("")) {

				// filtroCliente.adicionarParametro(new ParametroSimplesColecao(
				// FiltroCliente.LOGRADOURO, idLogradouro));

				FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

				filtroLogradouro.adicionarParametro(new ParametroSimples(
						FiltroLogradouro.ID, idLogradouro));

				Collection logradouros = fachada.pesquisar(filtroLogradouro,
						Logradouro.class.getName());

				if (logradouros != null && !logradouros.isEmpty()) {
					// O municipio foi encontrado
					Iterator logradouroIterator = logradouros.iterator();

					logradouro = (Logradouro) logradouroIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Logradouro");
				}

			} else {
				logradouro = new Logradouro();
			}

			String idMunicipio = (String) filtrarClienteActionForm
					.getMunicipioClienteFiltro();

			if (idMunicipio != null && !idMunicipio.equals("")) {

				// filtroCliente.adicionarParametro(new ParametroSimplesColecao(
				// FiltroCliente.MUNICIPIO_ID, idMunicipio));

				FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.ID, idMunicipio));

				Collection municipios = fachada.pesquisar(filtroMunicipio,
						Municipio.class.getName());

				if (municipios != null && !municipios.isEmpty()) {
					// O municipio foi encontrado
					Iterator municipioIterator = municipios.iterator();

					municipio = (Municipio) municipioIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Município");
				}

			} else {
				municipio = new Municipio();
			}

			String idBairro = (String) filtrarClienteActionForm
					.getBairroClienteFiltro();

			Bairro bairro = null;

			if (idBairro != null && !idBairro.equals("")) {

				// filtroCliente.adicionarParametro(new ParametroSimplesColecao(
				// FiltroCliente.BAIRRO_CODIGO, idBairro));
				FiltroBairro filtroBairro = new FiltroBairro();

				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.CODIGO, idBairro));
				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.MUNICIPIO_ID, idMunicipio));

				Collection bairros = fachada.pesquisar(filtroBairro,
						Bairro.class.getName());

				if (bairros != null && !bairros.isEmpty()) {
					// O bairro foi encontrado
					Iterator bairroIterator = bairros.iterator();

					bairro = (Bairro) bairroIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Bairro");
				}

			} else {
				bairro = new Bairro();
			}

			Short indicadorDeUso = null;

			if (filtrarClienteActionForm.getIndicadorUsoClienteFiltro() != null
					&& !filtrarClienteActionForm.getIndicadorUsoClienteFiltro()
							.equals("")) {

				indicadorDeUso = new Short(""
						+ filtrarClienteActionForm
								.getIndicadorUsoClienteFiltro());
				// filtroCliente.adicionarParametro(new ParametroSimples(
				// FiltroCliente.INDICADOR_USO, indicadorDeUso));
			}

			// seta os parametros que serão mostrados no relatório

			clienteParametros.setCpf(cpf);
			clienteParametros.setRg(rg);
			clienteParametros.setCnpj(cnpj);
			clienteParametros
					.setId(codigo == null ? null : new Integer(codigo));
			clienteParametros.setNome(nome);

			LogradouroCep logradouroCep = new LogradouroCep();

			if (cep.getCepId() != null && logradouro.getId() != null) {

				logradouroCep = fachada.pesquisarAssociacaoLogradouroCep(cep
						.getCepId(), logradouro.getId());

			}

			LogradouroBairro logradouroBairro = new LogradouroBairro();

			if (bairro.getId() != null && logradouro.getId() != null) {

				logradouroBairro = fachada.pesquisarAssociacaoLogradouroBairro(
						bairro.getId(), logradouro.getId());

			}

			clienteEnderecoParametros.setLogradouroCep(logradouroCep);
			clienteEnderecoParametros.setLogradouroBairro(logradouroBairro);
			clienteParametros.setIndicadorUso(indicadorDeUso);

			// Fim da parte que vai mandar os parametros para o relatório

		} else {

			DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

			// Como só vai precisar de uma coleção de clientes passar o valor da
			// coleção de clientesOutrosCriterios para clientes

			// clientes = clientesOutrosCriterios;
			//
			// Iterator colecaoClienteSessao = clientes.iterator();
			// Cliente cliente;
			//
			// while (colecaoClienteSessao.hasNext()) {
			// cliente = (Cliente) colecaoClienteSessao.next();
			//
			// if (cliente.getId() != null && !cliente.getId().equals("")) {
			// FiltroClienteEndereco filtroClienteEndereco = new
			// FiltroClienteEndereco();
			//
			// filtroClienteEndereco
			// .adicionarParametro(new ParametroSimples(
			// FiltroClienteEndereco.CLIENTE_ID, cliente
			// .getId()));
			//
			// Collection clientesEnderecos = fachada.pesquisar(
			// filtroClienteEndereco, ClienteEndereco.class
			// .getName());
			//
			// if (clientesEnderecos != null
			// && !clientesEnderecos.isEmpty()) {
			// // O Endereço foi encontrado
			// Iterator clienteEnderecoIterator = clientesEnderecos
			// .iterator();
			// while (clienteEnderecoIterator.hasNext()) {
			// clienteEndereco = (ClienteEndereco) clienteEnderecoIterator
			// .next();
			//
			// if (clienteEndereco
			// .getIndicadorEnderecoCorrespondencia()
			// .equals(new Short("1"))) {
			// break;
			// }
			// }
			// }
			// }
			// }
			// Inicio da parte que vai mandar os parametros para o relatório

			String cpf = null;
			String cpfPesquisado = (String) pesquisarActionForm.get("cpf");

			if (cpfPesquisado != null && !cpfPesquisado.equals("")) {
				cpf = cpfPesquisado;
			}

			String rg = null;
			String rgPesquisado = (String) pesquisarActionForm.get("rg");

			if (rgPesquisado != null && !rgPesquisado.equals("")) {
				rg = rgPesquisado;
			}

			String cnpj = null;
			String cnpjPesquisado = (String) pesquisarActionForm.get("cnpj");

			if (cnpjPesquisado != null && !cnpjPesquisado.equals("")) {
				cnpj = cnpjPesquisado;
			}

			String codigo = null;
			String codigoPesquisado = (String) pesquisarActionForm
					.get("idCliente");

			if (codigoPesquisado != null && !codigoPesquisado.equals("")) {
				codigo = codigoPesquisado;
			}

			String nome = null;
			String nomePesquisado = (String) pesquisarActionForm
					.get("nomeCliente");

			if (nomePesquisado != null && !nomePesquisado.equals("")) {
				nome = nomePesquisado;
			}

			String nomeAbreviado = null;
			String nomeAbreviadoPesquisado = (String) pesquisarActionForm
					.get("nomeAbreviadoCliente");

			if (nomeAbreviadoPesquisado != null
					&& !nomeAbreviadoPesquisado.equals("")) {
				nomeAbreviado = nomeAbreviadoPesquisado;
			}

			String idMunicipio = (String) pesquisarActionForm
					.get("idMunicipio");

			if (idMunicipio != null && !idMunicipio.equals("")) {
				FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.ID, idMunicipio));
				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection municipios = fachada.pesquisar(filtroMunicipio,
						Municipio.class.getName());

				if (municipios != null && !municipios.isEmpty()) {
					// O municipio foi encontrado
					Iterator municipioIterator = municipios.iterator();

					municipio = (Municipio) municipioIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Município");
				}

			} else {
				municipio = new Municipio();
			}

			Cep cep = new Cep();
			String numeroCep = null;
			String cepPesquisado = (String) pesquisarActionForm.get("cep");

			if (cepPesquisado != null && !cepPesquisado.equals("")) {
				numeroCep = cepPesquisado;
				cep.setCodigo(new Integer(numeroCep));
			}

			String idLogradouro = (String) pesquisarActionForm
					.get("idLogradouro");

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

			String idClienteTipo = (String) pesquisarActionForm
					.get("tipoCliente");

			ClienteTipo clienteTipo = null;

			if (idClienteTipo != null
					&& !idClienteTipo.equals(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO)
					&& !idClienteTipo.equals("")) {
				FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();

				filtroClienteTipo.adicionarParametro(new ParametroSimples(
						FiltroClienteTipo.ID, idClienteTipo));
				filtroClienteTipo.adicionarParametro(new ParametroSimples(
						FiltroClienteTipo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection clientesTipos = fachada.pesquisar(filtroClienteTipo,
						ClienteTipo.class.getName());

				if (clientesTipos != null && !clientesTipos.isEmpty()) {
					// O cliente tipo foi encontrado
					Iterator clienteTipoIterator = clientesTipos.iterator();

					clienteTipo = (ClienteTipo) clienteTipoIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Tipo de Cliente");
				}

			} else {
				clienteTipo = new ClienteTipo();
			}

			String idRamoAtividade = (String) pesquisarActionForm
					.get("ramoAtividade");

			RamoAtividade ramoAtividade = null;

			if (idRamoAtividade != null
					&& !idRamoAtividade.equals(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO)
					&& !idRamoAtividade.equals("")) {
				FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade();

				filtroRamoAtividade.adicionarParametro(new ParametroSimples(
						FiltroRamoAtividade.ID, idRamoAtividade));
				filtroRamoAtividade.adicionarParametro(new ParametroSimples(
						FiltroRamoAtividade.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection ramosAtividades = fachada.pesquisar(
						filtroRamoAtividade, RamoAtividade.class.getName());

				if (ramosAtividades != null && !ramosAtividades.isEmpty()) {
					// O ramo atividade foi encontrado
					Iterator ramoAtividadeIterator = ramosAtividades.iterator();

					ramoAtividade = (RamoAtividade) ramoAtividadeIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Ramo de Atividade");
				}

			} else {
				ramoAtividade = new RamoAtividade();
			}

			String idOrgaoExpedidorRg = (String) pesquisarActionForm
					.get("orgaoEmissor");

			OrgaoExpedidorRg orgaoExpedidorRg = null;

			if (idOrgaoExpedidorRg != null
					&& !idOrgaoExpedidorRg.equals(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO)
					&& !idOrgaoExpedidorRg.equals("")) {
				FiltroOrgaoExpedidorRg filtroOrgaoExpedidorRg = new FiltroOrgaoExpedidorRg();

				filtroOrgaoExpedidorRg.adicionarParametro(new ParametroSimples(
						FiltroOrgaoExpedidorRg.ID, idOrgaoExpedidorRg));
				filtroOrgaoExpedidorRg.adicionarParametro(new ParametroSimples(
						FiltroOrgaoExpedidorRg.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection orgaosExpedidoresRg = fachada.pesquisar(
						filtroOrgaoExpedidorRg, OrgaoExpedidorRg.class
								.getName());

				if (orgaosExpedidoresRg != null
						&& !orgaosExpedidoresRg.isEmpty()) {
					// O órgão expedidor foi encontrado
					Iterator orgaoExpedidorRgIterator = orgaosExpedidoresRg
							.iterator();

					orgaoExpedidorRg = (OrgaoExpedidorRg) orgaoExpedidorRgIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Órgão Expedidor do Rg");
				}

			} else {
				orgaoExpedidorRg = new OrgaoExpedidorRg();
			}

			String idProfissao = (String) pesquisarActionForm.get("profissao");

			Profissao profissao = null;

			if (idProfissao != null
					&& !idProfissao.equals(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO)
					&& !idProfissao.equals("")) {
				FiltroProfissao filtroProfissao = new FiltroProfissao();

				filtroProfissao.adicionarParametro(new ParametroSimples(
						FiltroProfissao.ID, idProfissao));
				filtroProfissao.adicionarParametro(new ParametroSimples(
						FiltroProfissao.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection profissoes = fachada.pesquisar(filtroProfissao,
						Profissao.class.getName());

				if (profissoes != null && !profissoes.isEmpty()) {
					// A profissão foi encontrado
					Iterator profissaoIterator = profissoes.iterator();

					profissao = (Profissao) profissaoIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Profissão");
				}

			} else {
				profissao = new Profissao();
			}

			String idPessoaSexo = (String) pesquisarActionForm.get("sexo");

			PessoaSexo pessoaSexo = null;

			if (idPessoaSexo != null
					&& !idPessoaSexo.equals(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO)
					&& !idPessoaSexo.equals("")) {
				FiltroPessoaSexo filtroPessoaSexo = new FiltroPessoaSexo();

				filtroPessoaSexo.adicionarParametro(new ParametroSimples(
						FiltroPessoaSexo.ID, idPessoaSexo));
				filtroPessoaSexo.adicionarParametro(new ParametroSimples(
						FiltroPessoaSexo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection sexos = fachada.pesquisar(filtroPessoaSexo,
						PessoaSexo.class.getName());

				if (sexos != null && !sexos.isEmpty()) {
					// O sexo foi encontrado
					Iterator pessoaSexoIterator = sexos.iterator();

					pessoaSexo = (PessoaSexo) pessoaSexoIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Profissão");
				}

			} else {
				pessoaSexo = new PessoaSexo();
			}

			String idBairro = (String) pesquisarActionForm.get("codigoBairro");

			Bairro bairro = null;

			if (idBairro != null && !idBairro.equals("")) {
				FiltroBairro filtroBairro = new FiltroBairro();

				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.CODIGO, idBairro));

				Collection bairros = fachada.pesquisar(filtroBairro,
						Bairro.class.getName());

				if (bairros != null && !bairros.isEmpty()) {
					// O bairro foi encontrado
					Iterator bairroIterator = bairros.iterator();

					bairro = (Bairro) bairroIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Bairro");
				}

			} else {
				bairro = new Bairro();
			}

			Cliente clienteResponsavel = null;
			String idClienteResponsavel = (String) pesquisarActionForm
					.get("idClienteResponsavel");

			if (idClienteResponsavel != null
					&& !idClienteResponsavel.equals("")) {
				FiltroCliente filtroClienteTipo = new FiltroCliente();

				filtroClienteTipo.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, idClienteResponsavel));

				Collection clientesResponsaveis = fachada.pesquisar(
						filtroClienteTipo, Cliente.class.getName());

				if (clientesResponsaveis != null
						&& !clientesResponsaveis.isEmpty()) {
					// O Cliente foi encontrado
					Iterator clienteResponsavelIterator = clientesResponsaveis
							.iterator();

					clienteResponsavel = (Cliente) clienteResponsavelIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Cliente");
				}

			} else {
				clienteResponsavel = new Cliente();
			}

			Imovel imovel = null;
			String idImovel = (String) pesquisarActionForm.get("idImovel");

			if (idImovel != null && !idImovel.equals("")) {
				FiltroImovel filtroImovel = new FiltroImovel();

				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, idImovel));

				Collection imoveis = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());

				if (imoveis != null && !imoveis.isEmpty()) {
					// O imóvel foi encontrado
					Iterator imovelIterator = imoveis.iterator();

					imovel = (Imovel) imovelIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Imóvel");
				}

			} else {
				imovel = new Imovel();
			}

			Date dataEmissao = null;
			String dataEmissaoPesquisada = (String) pesquisarActionForm
					.get("dataEmissao");

			if (dataEmissaoPesquisada != null
					&& !dataEmissaoPesquisada.equals("")) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

				try {
					dataEmissao = format.parse(dataEmissaoPesquisada);
				} catch (ParseException ex) {
					throw new ActionServletException("erro.sistema");
				}

			}

			Date dataNascimento = null;
			String dataNascimentoPesquisada = (String) pesquisarActionForm
					.get("dataNascimento");

			if (dataNascimentoPesquisada != null
					&& !dataNascimentoPesquisada.equals("")) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

				try {
					dataNascimento = format.parse(dataNascimentoPesquisada);
				} catch (ParseException ex) {
					throw new ActionServletException("erro.sistema");
				}

			}

			String email = null;
			String emailPesquisado = (String) pesquisarActionForm.get("email");

			if (emailPesquisado != null && !emailPesquisado.equals("")) {
				email = emailPesquisado;
			}

			Short indicadorDeUso = null;

			if (pesquisarActionForm.get("indicadorUso") != null
					&& !pesquisarActionForm.get("indicadorUso").equals("")) {

				indicadorDeUso = new Short(""
						+ pesquisarActionForm.get("indicadorUso"));
			}

			// seta os parametros que serão mostrados no relatório

			clienteParametros.setCpf(cpf);
			clienteParametros.setClienteTipo(clienteTipo);
			clienteParametros.setEmail(email);
			clienteParametros.setDataEmissaoRg(dataEmissao);
			clienteParametros.setDataNascimento(dataNascimento);
			clienteParametros.setRg(rg);
			clienteParametros.setCnpj(cnpj);
			clienteParametros
					.setId(codigo == null ? null : new Integer(codigo));
			clienteParametros.setRamoAtividade(ramoAtividade);
			clienteParametros.setOrgaoExpedidorRg(orgaoExpedidorRg);
			clienteParametros.setProfissao(profissao);
			clienteParametros.setNome(nome);
			clienteParametros.setNomeAbreviado(nomeAbreviado);
			clienteParametros.setCliente(clienteResponsavel);

			LogradouroCep logradouroCep = new LogradouroCep();
			
			if (logradouro.getId() != null) {
				logradouroCep.setLogradouro(logradouro);
			} 
			
			if (cep.getCodigo() != null) {
				logradouroCep.setCep(cep);
			}

			LogradouroBairro logradouroBairro = new LogradouroBairro();
			
			if (bairro.getCodigo() != 0) {
				logradouroBairro.setBairro(bairro);
			} 

			clienteEnderecoParametros.setLogradouroCep(logradouroCep);
			clienteEnderecoParametros.setLogradouroBairro(logradouroBairro);
			clienteParametros.setIndicadorUso(indicadorDeUso);
			clienteParametros.setPessoaSexo(pessoaSexo);
			clienteImovelParametros.setImovel(imovel);

			// Fim da parte que vai mandar os parametros para o relatório

		}

		// cria uma instância da classe do relatório
		RelatorioManterCliente relatorioManterCliente = new RelatorioManterCliente(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		relatorioManterCliente.addParametro("clientes", clientes);

		relatorioManterCliente.addParametro("filtroCliente", filtroCliente);
		relatorioManterCliente.addParametro("clienteParametros",
				clienteParametros);
		relatorioManterCliente.addParametro("clienteEnderecoParametros",
				clienteEnderecoParametros);
		relatorioManterCliente.addParametro("municipio", municipio);
		relatorioManterCliente.addParametro("indicadorTela", indicadorTela);
		relatorioManterCliente.addParametro("clienteImovelParametros",
				clienteImovelParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterCliente.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterCliente,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
