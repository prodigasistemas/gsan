package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.Cliente;
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
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

/**
 * Pré-processamento para a página de filtro de cliente
 * 
 * @author Rafael Corrêa
 * @created 23 de Julho de 2005
 */

public class ExibirFiltrarClienteOutrosCriteriosAction extends GcomAction {
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

		ActionForward retorno = actionMapping
				.findForward("filtrarClienteOutrosCriterios");

		DynaValidatorActionForm pesquisarActionForm = (DynaValidatorActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// Carregar a data corrente do sistema
		// ====================================
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataCorrente = new GregorianCalendar();

		// Data Corrente
		httpServletRequest.setAttribute("dataAtual", formatoData
				.format(dataCorrente.getTime()));

		// Parte que pega as coleções da sessão

		// Cliente Tipo
		FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
		filtroClienteTipo.adicionarParametro(new ParametroSimples(
				FiltroClienteTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroClienteTipo.setCampoOrderBy(FiltroClienteTipo.DESCRICAO);
		Collection clienteTipos = fachada.pesquisar(filtroClienteTipo,
				ClienteTipo.class.getName());
		if (clienteTipos.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"tipo de cliente");
		} else {
			sessao.setAttribute("clienteTipos", clienteTipos);
		}

		// Órgão Expeditor
		FiltroOrgaoExpedidorRg filtroOrgaoExpedidorRg = new FiltroOrgaoExpedidorRg();
		filtroOrgaoExpedidorRg.adicionarParametro(new ParametroSimples(
				FiltroOrgaoExpedidorRg.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroOrgaoExpedidorRg
				.setCampoOrderBy(FiltroOrgaoExpedidorRg.DESCRICAO);
		Collection orgaosExpeditores = fachada.pesquisar(
				filtroOrgaoExpedidorRg, OrgaoExpedidorRg.class.getName());
		if (orgaosExpeditores.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"órgão expeditor");
		} else {
			sessao.setAttribute("orgaosExpeditores", orgaosExpeditores);
		}

		// Ramo de Atividade
		FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade();
		filtroRamoAtividade.adicionarParametro(new ParametroSimples(
				FiltroRamoAtividade.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroRamoAtividade.setCampoOrderBy(FiltroRamoAtividade.DESCRICAO);
		Collection ramosAtividades = fachada.pesquisar(filtroRamoAtividade,
				RamoAtividade.class.getName());
		if (ramosAtividades.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"ramo de atividade");
		} else {
			sessao.setAttribute("ramosAtividades", ramosAtividades);
		}

		// Profissão
		FiltroProfissao filtroProfissao = new FiltroProfissao();
		filtroProfissao.adicionarParametro(new ParametroSimples(
				FiltroProfissao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroProfissao.setCampoOrderBy(FiltroProfissao.DESCRICAO);
		Collection profissoes = fachada.pesquisar(filtroProfissao,
				Profissao.class.getName());
		if (profissoes.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"profissão");
		} else {
			sessao.setAttribute("profissoes", profissoes);
		}

		// Sexo
		FiltroPessoaSexo filtroPessoaSexo = new FiltroPessoaSexo();
		filtroPessoaSexo.adicionarParametro(new ParametroSimples(
				FiltroPessoaSexo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroPessoaSexo.setCampoOrderBy(FiltroPessoaSexo.DESCRICAO);
		Collection sexos = fachada.pesquisar(filtroPessoaSexo, PessoaSexo.class
				.getName());
		if (sexos.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"sexos");
		} else {
			sessao.setAttribute("sexos", sexos);
		}

		// Verificação de Tipo Cliente

		String idTipoCliente = (String) pesquisarActionForm.get("tipoCliente");

		if (idTipoCliente != null
				&& !idTipoCliente.equals(""
						+ (ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& !idTipoCliente.equals("")) {
			FiltroClienteTipo filtroClienteTipoPesquisado = new FiltroClienteTipo();
			filtroClienteTipoPesquisado
					.adicionarParametro(new ParametroSimples(
							FiltroClienteTipo.ID, idTipoCliente));

			Collection tiposClientePesquisados = fachada.pesquisar(
					filtroClienteTipoPesquisado, ClienteTipo.class.getName());

			if (tiposClientePesquisados != null
					&& !tiposClientePesquisados.isEmpty()) {
				ClienteTipo clienteTipo = (ClienteTipo) ((List) tiposClientePesquisados)
						.get(0);

				// Verificar se pessoa física ou jurídica
				if (clienteTipo.getIndicadorPessoaFisicaJuridica().equals(
						ClienteTipo.INDICADOR_PESSOA_FISICA)) {
					httpServletRequest.setAttribute("pessoaFisica", "true");
					pesquisarActionForm.set("cnpj", "");
					pesquisarActionForm.set("idClienteResponsavel", "");
					pesquisarActionForm.set("nomeClienteResponsavel", "");
					pesquisarActionForm.set("ramoAtividade", ""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO);

				} else {
					httpServletRequest.setAttribute("pessoaJuridica", "true");
					pesquisarActionForm.set("cpf", "");
					pesquisarActionForm.set("rg", "");
					pesquisarActionForm.set("dataEmissao", "");
					pesquisarActionForm.set("dataNascimento", "");
					pesquisarActionForm.set("orgaoEmissor", ""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO);
					pesquisarActionForm.set("profissao", ""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO);
					pesquisarActionForm.set("sexo", ""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO);

				}

			}
		}

		// -------Parte que trata do código quando o usuário tecla enter

		// Imóvel
		String idImovel = (String) pesquisarActionForm.get("idImovel");

		if (idImovel != null && !idImovel.equals("")) {
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial.municipio.unidadeFederacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
			
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, idImovel));

			Collection imoveis = fachada.pesquisar(filtroImovel, Imovel.class
					.getName());

			if (imoveis != null && !imoveis.isEmpty()) {
				Imovel imovel = (Imovel) ((List) imoveis).get(0);
				pesquisarActionForm.set("inscricao", imovel
						.getInscricaoFormatada());
				httpServletRequest.setAttribute("imovel", imovel);
			} else {
				httpServletRequest.setAttribute("matriculaInexistente", true);
				pesquisarActionForm.set("idImovel", "");
				pesquisarActionForm.set("inscricao",
						"MATRÍCULA DO IMÓVEL INEXISTENTE");
			}
		}

		// Cliente
		String idCliente = (String) pesquisarActionForm.get("idCliente");

		if (idCliente != null && !idCliente.equals("")) {
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idCliente));

			Collection clientes = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (clientes != null && !clientes.isEmpty()) {
				Cliente cliente = (Cliente) ((List) clientes).get(0);
				pesquisarActionForm.set("nomeCliente", cliente.getNome());
			} else {
				httpServletRequest.setAttribute("clienteInexistente", "true");
			}
		}

		// Cliente Responsável Superior
		String idClienteResponsavel = (String) pesquisarActionForm
				.get("idClienteResponsavel");

		if (idClienteResponsavel != null && !idClienteResponsavel.equals("")) {
			FiltroCliente filtroClienteResponsavel = new FiltroCliente();
			filtroClienteResponsavel.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idClienteResponsavel));

			filtroClienteResponsavel.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
			
			Collection clientesResponsaveis = fachada.pesquisar(
					filtroClienteResponsavel, Cliente.class.getName());

			if (clientesResponsaveis != null && !clientesResponsaveis.isEmpty()) {
				Cliente clienteReponsavel = (Cliente) ((List) clientesResponsaveis)
						.get(0);

				if (clienteReponsavel.getClienteTipo().getIndicadorPessoaFisicaJuridica().equals(
						ClienteTipo.INDICADOR_PESSOA_JURIDICA)) {
					pesquisarActionForm.set("nomeClienteResponsavel",
							clienteReponsavel.getNome());
				} else {
					throw new ActionServletException(
							"atencao.cliente_responsavel.informado.pessoa_fisica");
				}
			} else {
				pesquisarActionForm.set("idClienteResponsavel", "");
				pesquisarActionForm.set("nomeClienteResponsavel",
						"Cliente Inexistente");
				httpServletRequest.setAttribute("clienteNaoEncontrado", "true");
			}
		}

		// Cep
		String codigoCep = (String) pesquisarActionForm.get("cep");

		if (codigoCep != null && !codigoCep.equals("")) {
			FiltroCep filtroCep = new FiltroCep();
			filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.CODIGO,
					codigoCep));

			Collection ceps = fachada.pesquisar(filtroCep, Cep.class.getName());

			if (ceps != null && !ceps.isEmpty()) {
				Cep cep = (Cep) ((List) ceps).get(0);
				pesquisarActionForm.set("cep", "" + cep.getCodigo());
				pesquisarActionForm.set("descricaoCep", cep.getDescricaoLogradouroFormatada());
			} else {
				pesquisarActionForm.set("cep", "");
				httpServletRequest.setAttribute("cepInexistente",
						true);
				pesquisarActionForm.set("descricaoCep", "CEP Inexistente");
			}
		}

		// Código do Município
		String codigoDigitadoMunicipioEnter = (String) pesquisarActionForm
				.get("idMunicipio");

		// Verifica se o código foi digitado
		if (codigoDigitadoMunicipioEnter != null
				&& !codigoDigitadoMunicipioEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoMunicipioEnter) > 0) {
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, codigoDigitadoMunicipioEnter));
			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection municipioEncontrado = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
				// O municipio foi encontrado
				pesquisarActionForm.set("idMunicipio", ""
						+ ((Municipio) ((List) municipioEncontrado).get(0))
								.getId());
				pesquisarActionForm.set("descricaoMunicipioCliente",
						((Municipio) ((List) municipioEncontrado).get(0))
								.getNome());
				// httpServletRequest.setAttribute("municipioNaoEncontrado",
				// "true");

			} else {
				pesquisarActionForm.set("idMunicipio", "");
				httpServletRequest.setAttribute("municipioNaoEncontrado",
						"exception");
				pesquisarActionForm.set("descricaoMunicipioCliente",
						"Município inexistente");
			}
		}

		// Código do Bairro
		String codigoDigitadoBairroEnter = (String) pesquisarActionForm
				.get("codigoBairro");
		// Verifica se o código foi digitado
		if (codigoDigitadoBairroEnter != null
				&& !codigoDigitadoBairroEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoBairroEnter) > 0) {
			FiltroBairro filtroBairro = new FiltroBairro();

			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.CODIGO, codigoDigitadoBairroEnter));
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			// Adiciona a busca por município se ele foi digitado na página
			if (codigoDigitadoMunicipioEnter != null
					&& !codigoDigitadoMunicipioEnter.equalsIgnoreCase("")) {
				filtroBairro
						.adicionarParametro(new ParametroSimples(
								FiltroBairro.MUNICIPIO_ID,
								codigoDigitadoMunicipioEnter));
			}

			Collection bairroEncontrado = fachada.pesquisar(filtroBairro,
					Bairro.class.getName());

			if (bairroEncontrado != null && !bairroEncontrado.isEmpty()) {
				// O bairro foi encontrado
				pesquisarActionForm.set("codigoBairro", ""
						+ ((Bairro) ((List) bairroEncontrado).get(0))
								.getCodigo());
				pesquisarActionForm.set("descricaoBairroCliente",
						((Bairro) ((List) bairroEncontrado).get(0)).getNome());
				// httpServletRequest.setAttribute("bairroNaoEncontrado",
				// "true");

			} else {
				pesquisarActionForm.set("codigoBairro", "");
				httpServletRequest.setAttribute("bairroNaoEncontrado",
						"exception");
				pesquisarActionForm.set("descricaoBairroCliente",
						"Bairro inexistente");
			}
		}

		// Código do Logradouro
		String codigoDigitadoLogradouroEnter = (String) pesquisarActionForm
				.get("idLogradouro");

		// Verifica se o código foi digitado
		if (codigoDigitadoLogradouroEnter != null
				&& !codigoDigitadoLogradouroEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoLogradouroEnter) > 0) {
			FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
			filtroLogradouro.adicionarParametro(new ParametroSimples(
					FiltroLogradouro.ID, codigoDigitadoLogradouroEnter));
			filtroLogradouro.adicionarParametro(new ParametroSimples(
					FiltroLogradouro.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			// Adiciona a busca por município se ele foi digitado na página
			// if (codigoDigitadoMunicipioEnter != null
			// && !codigoDigitadoMunicipioEnter.equalsIgnoreCase("")) {
			// filtroLogradouro.adicionarParametro(new
			// ParametroSimples(FiltroLogradouro.ID_MUNICIPIO,
			// codigoDigitadoMunicipioEnter));
			// }

			Collection logradouroEncontrado = fachada.pesquisar(
					filtroLogradouro, Logradouro.class.getName());

			if (logradouroEncontrado != null && !logradouroEncontrado.isEmpty()) {
				// O logradouro foi encontrado
				pesquisarActionForm.set("idLogradouro", ""
						+ ((Logradouro) ((List) logradouroEncontrado).get(0))
								.getId());
				pesquisarActionForm.set("descricaoLogradouroCliente",
						((Logradouro) ((List) logradouroEncontrado).get(0))
								.getNome());
				// httpServletRequest.setAttribute("bairroNaoEncontrado",
				// "true");

			} else {
				pesquisarActionForm.set("idLogradouro", "");
				httpServletRequest.setAttribute("logradouroNaoEncontrado",
						"exception");
				pesquisarActionForm.set("descricaoLogradouroCliente",
						"Logradouro inexistente");
			}
		}

		return retorno;
	}

}
