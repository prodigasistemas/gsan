package gcom.relatorio.cadastro.cliente;

import gcom.batch.Relatorio;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.cadastro.cliente.FiltroClienteFone;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

public class RelatorioManterCliente extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioManterCliente object
	 */
	public RelatorioManterCliente(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CLIENTE_MANTER);
	}
	
	@Deprecated
	public RelatorioManterCliente() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param clientes
	 *            Description of the Parameter
	 * @param clienteParametros
	 *            Description of the Parameter
	 * @param clienteEnderecoParametros
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		FiltroCliente filtroCliente = (FiltroCliente) getParametro("filtroCliente");
		Cliente clienteParametros = (Cliente) getParametro("clienteParametros");
		ClienteEndereco clienteEnderecoParametros = (ClienteEndereco) getParametro("clienteEnderecoParametros");
		Municipio municipio = (Municipio) getParametro("municipio");
		String indicadorTela = (String) getParametro("indicadorTela");
		ClienteImovel clienteImovelParametros = (ClienteImovel) getParametro("clienteImovelParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterClienteBean relatorioBean = null;

		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
		filtroCliente
				.adicionarCaminhoParaCarregamentoEntidade("orgaoExpedidorRg");
		filtroCliente
				.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("profissao");
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("pessoaSexo");
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("ramoAtividade");
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("cliente");

		filtroCliente.setConsultaSemLimites(true);

		Collection clientesNovos = fachada
				.pesquisarClienteDadosClienteEnderecoRelatorio(filtroCliente);

		if (clientesNovos != null && !clientesNovos.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			Iterator clienteNovoIterator = clientesNovos.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (clienteNovoIterator.hasNext()) {

				Cliente clienteNovo = (Cliente) clienteNovoIterator.next();

				SimpleDateFormat dataFormatada = new SimpleDateFormat(
						"dd/MM/yyyy");

				ClienteEndereco clienteEndereco = null;

				if (clienteNovo.getId() != null
						&& !clienteNovo.getId().equals("")) {
					FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();

					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.municipio");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("cliente");

					filtroClienteEndereco
							.adicionarParametro(new ParametroSimples(
									FiltroClienteEndereco.CLIENTE_ID,
									clienteNovo.getId()));

					Collection clientesEnderecos = fachada.pesquisar(
							filtroClienteEndereco, ClienteEndereco.class
									.getName());

					if (clientesEnderecos != null
							&& !clientesEnderecos.isEmpty()) {
						// O Endereço foi encontrado
						Iterator clienteEnderecoIterator = clientesEnderecos
								.iterator();

						while (clienteEnderecoIterator.hasNext()) {
							clienteEndereco = (ClienteEndereco) clienteEnderecoIterator
									.next();

							if (clienteEndereco
									.getIndicadorEnderecoCorrespondencia()
									.equals(new Short("1"))) {
								break;
							}
						}
					}
				}

				ClienteFone clienteFone = null;

				if (clienteNovo.getId() != null
						&& !clienteNovo.getId().equals("")) {
					FiltroClienteFone filtroClienteFone = new FiltroClienteFone();
					filtroClienteFone
							.adicionarCaminhoParaCarregamentoEntidade("foneTipo");

					filtroClienteFone.adicionarParametro(new ParametroSimples(
							FiltroClienteFone.CLIENTE_ID, clienteNovo.getId()));

					Collection clientesFones = fachada.pesquisar(
							filtroClienteFone, ClienteFone.class.getName());

					if (clientesFones != null && !clientesFones.isEmpty()) {
						// O telefone foi encontrado
						Iterator clienteFoneIterator = clientesFones.iterator();

						while (clienteFoneIterator.hasNext()) {
							clienteFone = (ClienteFone) clienteFoneIterator
									.next();

							if (clienteFone.getIndicadorTelefonePadrao() != null
									&& clienteFone.getIndicadorTelefonePadrao()
											.equals(new Short("1"))) {
								break;
							}
						}
					}

				}

				if (clienteNovo.getClienteTipo()
						.getIndicadorPessoaFisicaJuridica().equals(
								new Short("1"))) {

					relatorioBean = new RelatorioManterClienteBean(

							//Código					
							clienteNovo.getId().toString(),
							
							// Nome
							clienteNovo.getNome(),

							// Tipo do Cliente
							clienteNovo.getClienteTipo() == null ? ""
									: clienteNovo.getClienteTipo()
											.getDescricao(),

							// Email
							clienteNovo.getEmail() == null ? "" : clienteNovo
									.getEmail(),

							// CPF
							clienteNovo.getCpfFormatado() == null ? ""
									: clienteNovo.getCpfFormatado(),

							// RG
							clienteNovo.getRg() == null ? "" : clienteNovo
									.getRg(),

							// Data Emissão do RG
							clienteNovo.getDataEmissaoRg() == null ? ""
									: dataFormatada.format(clienteNovo
											.getDataEmissaoRg()),

							// Órgão Expedidor RG
							clienteNovo.getOrgaoExpedidorRg() == null ? ""
									: clienteNovo.getOrgaoExpedidorRg()
											.getDescricaoAbreviada().trim()
											.equalsIgnoreCase("ninf") ? ""
											: clienteNovo.getOrgaoExpedidorRg()
													.getDescricaoAbreviada(),

							// Unidade Federação
							clienteNovo.getUnidadeFederacao() == null ? ""
									: clienteNovo.getUnidadeFederacao()
											.getSigla(),

							// Data de Nascimento
							clienteNovo.getDataNascimento() == null ? ""
									: dataFormatada.format(clienteNovo
											.getDataNascimento()),

							// Profissão
							clienteNovo.getProfissao() == null ? ""
									: clienteNovo.getProfissao().getDescricao(),

							// Pessoa Sexo
							clienteNovo.getPessoaSexo() == null ? ""
									: clienteNovo.getPessoaSexo()
											.getDescricao(),

							// Endereço
							clienteEndereco == null ? "" : clienteEndereco
									.getEnderecoFormatado(),

							// Telefone
							clienteFone == null ? "" : clienteFone
									.getTelefone() == null ? "" : clienteFone
									.getTelefone(),

							// Ramal
							clienteFone == null ? ""
									: clienteFone.getRamal() == null ? ""
											: clienteFone.getRamal(),

							// Tipo do Fone
							clienteFone == null ? "" : clienteFone
									.getFoneTipo() == null ? "" : clienteFone
									.getFoneTipo().getDescricao(),

							// Município
							clienteEndereco == null ? "MUNICÍPIO NÃO INFORMADO"
									: clienteEndereco.getLogradouroBairro() == null ? "MUNICÍPIO NÃO INFORMADO"
											: clienteEndereco
													.getLogradouroBairro()
													.getBairro() == null ? "MUNICÍPIO NÃO INFORMADO"
													: clienteEndereco
															.getLogradouroCep() == null ? "MUNICÍPIO NÃO INFORMADO"
															: clienteEndereco
																	.getLogradouroCep()
																	.getLogradouro() == null ? clienteEndereco
																	.getLogradouroCep() == null ? "MUNICÍPIO NÃO INFORMADO"
																	: clienteEndereco
																			.getLogradouroCep()
																			.getCep() == null ? "MUNICÍPIO NÃO INFORMADO"
																			: clienteEndereco
																					.getLogradouroCep()
																					.getCep()
																					.getMunicipio()
																	: clienteEndereco
																			.getLogradouroCep()
																			.getLogradouro()
																			.getMunicipio() == null ? "MUNICÍPIO NÃO INFORMADO"
																			: clienteEndereco
																					.getLogradouroCep()
																					.getLogradouro()
																					.getMunicipio()
																					.getNome(),

							// Indicador de Uso
							"" + clienteNovo.getIndicadorUso(), clienteNovo
									.getClienteTipo() == null ? "" : ""
									+ clienteNovo.getClienteTipo()
											.getIndicadorPessoaFisicaJuridica());
					// adiciona o bean a coleção
					relatorioBeans.add(relatorioBean);
				} else {
					relatorioBean = new RelatorioManterClienteBean(
							clienteNovo.getId().toString(),
							clienteNovo.getNome(),
							clienteNovo.getClienteTipo() == null ? ""
									: clienteNovo.getClienteTipo()
											.getDescricao(),
							clienteNovo.getEmail() == null ? "" : clienteNovo
									.getEmail(),
							clienteNovo.getCnpjFormatado() == null ? ""
									: clienteNovo.getCnpjFormatado(),
							clienteNovo.getRamoAtividade() == null ? ""
									: clienteNovo.getRamoAtividade()
											.getDescricao(),
							clienteNovo.getCliente() == null ? "" : ""
									+ clienteNovo.getCliente().getId(),
							clienteNovo.getCliente() == null ? "" : clienteNovo
									.getCliente().getNome(),
							clienteEndereco == null ? "" : clienteEndereco
									.getEnderecoFormatado(),
							clienteFone == null ? "" : clienteFone
									.getTelefone() == null ? "" : clienteFone
									.getTelefone(),
							clienteFone == null ? ""
									: clienteFone.getRamal() == null ? ""
											: clienteFone.getRamal(),
							clienteFone == null ? "" : clienteFone
									.getFoneTipo() == null ? "" : clienteFone
									.getFoneTipo().getDescricao(),
							clienteEndereco == null ? "MUNICÍPIO NÃO INFORMADO"
									: clienteEndereco.getLogradouroBairro() == null ? "MUNICÍPIO NÃO INFORMADO"
											: clienteEndereco
													.getLogradouroBairro()
													.getBairro() == null ? "MUNICÍPIO NÃO INFORMADO"
													: clienteEndereco
															.getLogradouroCep() == null ? "MUNICÍPIO NÃO INFORMADO"
															: clienteEndereco
																	.getLogradouroCep()
																	.getLogradouro() == null ? clienteEndereco
																	.getLogradouroCep() == null ? "MUNICÍPIO NÃO INFORMADO"
																	: clienteEndereco
																			.getLogradouroCep()
																			.getCep() == null ? "MUNICÍPIO NÃO INFORMADO"
																			: clienteEndereco
																					.getLogradouroCep()
																					.getCep()
																					.getMunicipio()
																	: clienteEndereco
																			.getLogradouroCep()
																			.getLogradouro()
																			.getMunicipio() == null ? "MUNICÍPIO NÃO INFORMADO"
																			: clienteEndereco
																					.getLogradouroCep()
																					.getLogradouro()
																					.getMunicipio()
																					.getNome(),

							"" + clienteNovo.getIndicadorUso(), clienteNovo
									.getClienteTipo() == null ? "" : ""
									+ clienteNovo.getClienteTipo()
											.getIndicadorPessoaFisicaJuridica());
					// adiciona o bean a coleção
					relatorioBeans.add(relatorioBean);
				}

			}

			// Organizar a coleção

			Collections.sort((List) relatorioBeans, new Comparator() {
				public int compare(Object a, Object b) {
					String municipio1 = ((RelatorioManterClienteBean) a)
							.getMunicipio() == null ? ""
							: ((RelatorioManterClienteBean) a)
									.getMunicipio()
									.equalsIgnoreCase("MUNICÍPIO NÃO INFORMADO") ? ""
									: ((RelatorioManterClienteBean) a)
											.getMunicipio();
					String municipio2 = ((RelatorioManterClienteBean) b)
							.getMunicipio() == null ? ""
							: ((RelatorioManterClienteBean) b)
									.getMunicipio()
									.equalsIgnoreCase("MUNICÍPIO NÃO INFORMADO") ? ""
									: ((RelatorioManterClienteBean) b)
											.getMunicipio();

					return municipio1.compareTo(municipio2);

				}
			});

			// Parâmetros do relatório
			Map parametros = new HashMap();

			// adiciona os parâmetros do relatório
			// adiciona o laudo da análise
			
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			parametros.put("imagem", sistemaParametro.getImagemRelatorio());

			if (indicadorTela == null || indicadorTela.equals("")) {

				parametros.put("cpf",
						clienteParametros.getCpfFormatado() == null ? ""
								: clienteParametros.getCpfFormatado());

				parametros.put("rg", clienteParametros.getRg() == null ? ""
						: clienteParametros.getRg());

				parametros.put("cnpj",
						clienteParametros.getCnpjFormatado() == null ? ""
								: clienteParametros.getCnpjFormatado());

				parametros.put("codigo", clienteParametros.getId() == null ? ""
						: "" + clienteParametros.getId());

				parametros.put("nome", clienteParametros.getNome());

				parametros.put("cep", clienteEnderecoParametros
						.getLogradouroCep().getCep() == null ? ""
						: clienteEnderecoParametros.getLogradouroCep().getCep()
								.getCodigo() == null ? "" : ""
								+ clienteEnderecoParametros.getLogradouroCep()
										.getCep().getCodigo());

				parametros.put("idMunicipio", municipio.getId() == null ? ""
						: "" + municipio.getId());

				parametros.put("nomeMunicipio",
						municipio.getNome() == null ? "" : municipio.getNome());

				parametros.put("idBairro", clienteEnderecoParametros
						.getLogradouroBairro().getBairro() == null ? ""
						: clienteEnderecoParametros.getLogradouroBairro()
								.getBairro().getId() == null ? "" : ""
								+ clienteEnderecoParametros
										.getLogradouroBairro().getBairro()
										.getCodigo());

				parametros.put("nomeBairro", clienteEnderecoParametros
						.getLogradouroBairro().getBairro() == null ? ""
						: clienteEnderecoParametros.getLogradouroBairro()
								.getBairro().getNome());

				parametros.put("nomeLogradouro", clienteEnderecoParametros
						.getLogradouroCep().getLogradouro() == null ? ""
						: clienteEnderecoParametros.getLogradouroCep()
								.getLogradouro().getNome());

				String indicadorUso = "";

				if (clienteParametros.getIndicadorUso() != null
						&& !clienteParametros.getIndicadorUso().equals("")) {
					if (clienteParametros.getIndicadorUso().equals(
							new Short("1"))) {
						indicadorUso = "Ativo";
					} else {
						indicadorUso = "Inativo";
					}
				}

				parametros.put("indicadorUso", indicadorUso);

				RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

				retorno = this.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_CLIENTE_MANTER,
						parametros, ds, tipoFormatoRelatorio);

			} else {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				String dataEmissaoRg = "";
				if (clienteParametros.getDataEmissaoRg() != null
						&& !clienteParametros.getDataEmissaoRg().equals("")) {
					dataEmissaoRg = format.format(clienteParametros
							.getDataEmissaoRg());
				}

				String dataNascimento = "";
				if (clienteParametros.getDataNascimento() != null
						&& !clienteParametros.getDataNascimento().equals("")) {
					dataNascimento = format.format(clienteParametros
							.getDataNascimento());
				}

				parametros.put("dataEmissao", dataEmissaoRg);

				parametros.put("dataNascimento", dataNascimento);

				parametros.put("cpf",
						clienteParametros.getCpfFormatado() == null ? ""
								: clienteParametros.getCpfFormatado());

				parametros.put("rg", clienteParametros.getRg() == null ? ""
						: clienteParametros.getRg());

				parametros.put("cnpj",
						clienteParametros.getCnpjFormatado() == null ? ""
								: clienteParametros.getCnpjFormatado());

				parametros.put("codigo", clienteParametros.getId() == null ? ""
						: "" + clienteParametros.getId());

				parametros.put("codigoClienteResponsavel", clienteParametros
						.getCliente() == null ? "" : clienteParametros
						.getCliente().getId() == null ? "" : ""
						+ clienteParametros.getCliente().getId());

				parametros.put("nome", clienteParametros.getNome());

				parametros.put("nomeAbreviado", clienteParametros
						.getNomeAbreviado());

				parametros.put("sexo",
						clienteParametros.getPessoaSexo() == null ? ""
								: clienteParametros.getPessoaSexo()
										.getDescricao());

				parametros.put("tipoCliente", clienteParametros
						.getClienteTipo() == null ? "" : clienteParametros
						.getClienteTipo().getDescricao());

				parametros.put("orgaoEmissor", clienteParametros
						.getOrgaoExpedidorRg() == null ? "" : clienteParametros
						.getOrgaoExpedidorRg().getDescricaoAbreviada());

				parametros.put("profissao",
						clienteParametros.getProfissao() == null ? ""
								: clienteParametros.getProfissao()
										.getDescricao());

				parametros.put("ramoAtividade", clienteParametros
						.getRamoAtividade() == null ? "" : clienteParametros
						.getRamoAtividade().getDescricao());

				parametros.put("email", clienteParametros.getEmail());

				parametros.put("cep", clienteEnderecoParametros
						.getLogradouroCep().getCep() == null ? ""
						: clienteEnderecoParametros.getLogradouroCep().getCep()
								.getCodigo() == null ? "" : ""
								+ clienteEnderecoParametros.getLogradouroCep()
										.getCep().getCodigo());

				parametros.put("idMunicipio", municipio.getId() == null ? ""
						: "" + municipio.getId());

				parametros.put("nomeMunicipio",
						municipio.getNome() == null ? "" : municipio.getNome());

				parametros.put("idBairro", clienteEnderecoParametros
						.getLogradouroBairro() == null ? ""
						: clienteEnderecoParametros.getLogradouroBairro()
								.getBairro() == null ? "" : ""
								+ clienteEnderecoParametros
										.getLogradouroBairro().getBairro()
										.getId() == null ? "" : ""
								+ clienteEnderecoParametros
										.getLogradouroBairro().getBairro()
										.getId());

				parametros.put("nomeBairro", clienteEnderecoParametros
						.getLogradouroBairro() == null? "": clienteEnderecoParametros
								.getLogradouroBairro().getBairro() == null ? ""
						: clienteEnderecoParametros.getLogradouroBairro()
								.getBairro().getNome());

				parametros.put("codigoLogradouro", clienteEnderecoParametros
						.getLogradouroCep().getLogradouro() == null ? ""
						: clienteEnderecoParametros.getLogradouroCep()
								.getLogradouro().getId() == null ? "" : ""
								+ clienteEnderecoParametros.getLogradouroCep()
										.getLogradouro().getId());

				parametros.put("nomeLogradouro", clienteEnderecoParametros
						.getLogradouroCep().getLogradouro() == null ? ""
						: clienteEnderecoParametros.getLogradouroCep()
								.getLogradouro().getNome());

				parametros.put("matriculaImovel", clienteImovelParametros
						.getImovel() == null ? "" : clienteImovelParametros
						.getImovel().getId() == null ? "" : ""
						+ clienteImovelParametros.getImovel().getId());

				String indicadorUso = "";

				if (clienteParametros.getIndicadorUso() != null
						&& !clienteParametros.getIndicadorUso().equals("")) {
					if (clienteParametros.getIndicadorUso().equals(
							new Short("1"))) {
						indicadorUso = "Ativo";
					} else if (clienteParametros.getIndicadorUso().equals(
							new Short("2"))) {
						indicadorUso = "Inativo";
					}
				}

				parametros.put("indicadorUso", indicadorUso);
				
				parametros.put("tipoFormatoRelatorio", "R0160"); 

				RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

				retorno = this
						.gerarRelatorio(
								ConstantesRelatorios.RELATORIO_CLIENTE_OUTROS_CRITERIOS_MANTER,
								parametros, ds, tipoFormatoRelatorio);
			}

			// cria uma instância do dataSource do relatório

		}
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_CLIENTE,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {

		int retorno = Fachada.getInstancia()
				.pesquisarClienteDadosClienteEnderecoCount(
						(FiltroCliente) getParametro("filtroCliente"));

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterCliente", this);
	}
}
