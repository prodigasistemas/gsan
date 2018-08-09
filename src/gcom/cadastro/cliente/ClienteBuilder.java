package gcom.cadastro.cliente;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.fachada.Fachada;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;

public abstract class ClienteBuilder {

	protected AtualizacaoCadastralImovel atualizacaoCadastralImovel;
	protected IClienteAtualizacaoCadastral clienteTxt = null;

	public final static String USUARIO = "Usuario";
	public final static String PROPRIETARIO = "Proprietario";
	public final static String RESPONSAVEL = "Responsavel";
	
	public abstract IClienteAtualizacaoCadastral buildCliente(Short clienteRelacaoTipo);

	public ClienteBuilder(AtualizacaoCadastralImovel atualizacaoCadastralImovel) {
		this.atualizacaoCadastralImovel = atualizacaoCadastralImovel;
		clienteTxt = new ClienteAtualizacaoCadastral();
	}

	protected void buildCliente(String tipoCliente, Short clienteRelacaoTipo) {
		clienteTxt.setNome(atualizacaoCadastralImovel.getLinhaCliente("nome" + tipoCliente));

		String cnpjCpf = atualizacaoCadastralImovel.getLinhaCliente("cnpjCpf" + tipoCliente);
		if (cnpjCpf.length() == 11) {
			clienteTxt.setCpf(cnpjCpf);
		} else if (cnpjCpf.length() == 14) {
			clienteTxt.setCnpj(cnpjCpf);
		}

		clienteTxt.setRg(atualizacaoCadastralImovel.getLinhaCliente("rg" + tipoCliente));

		clienteTxt.setUnidadeFederacao(getUnidadeFederecao(atualizacaoCadastralImovel.getLinhaCliente("ufRg" + tipoCliente)));
		
		clienteTxt.setDsUFSiglaOrgaoExpedidorRg(atualizacaoCadastralImovel.getLinhaCliente("ufRg" + tipoCliente));

		String campo = atualizacaoCadastralImovel.getLinhaCliente("sexo" + tipoCliente);
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)) {
			int idSexo = Integer.parseInt(campo);

			if (idSexo == 1 || idSexo == 2) {
				PessoaSexo sexo = new PessoaSexo(idSexo);
				clienteTxt.setPessoaSexo(sexo);
			}
		}

		Integer idCliente = new Integer(atualizacaoCadastralImovel.getLinhaCliente("matricula" + tipoCliente));
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("tipoPessoa" + tipoCliente);
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)) {
			clienteTxt.setIdClienteTipo(getIdClienteTipo(new Short(campo), idCliente));
		}

		clienteTxt.setEmail(atualizacaoCadastralImovel.getLinhaCliente("email" + tipoCliente));
		clienteTxt.setIdCliente(idCliente);

		clienteTxt.setIdClienteRelacaoTipo(new Integer(clienteRelacaoTipo));

		campo = atualizacaoCadastralImovel.getLinhaCliente("matriculaImovelCliente");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)) {
			clienteTxt.setIdImovel(Integer.parseInt(campo));
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private UnidadeFederacao getUnidadeFederecao(String uf) {
		UnidadeFederacao retorno = null;

		Filtro filtro = new FiltroUnidadeFederacao();
		filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeFederacao.SIGLA, uf));
		Collection pesquisa = Fachada.getInstancia().pesquisar(filtro, UnidadeFederacao.class.getName());
		if (pesquisa != null && !pesquisa.isEmpty()) {
			retorno = (UnidadeFederacao) Util.retonarObjetoDeColecao(pesquisa);
		}

		return retorno;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Integer getIdClienteTipo(Short tipoPessoa, Integer idCliente) {
		Filtro filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.CLIENTE_TIPO);

		Collection pesquisa = Fachada.getInstancia().pesquisar(filtroCliente, Cliente.class.getName());
		Cliente cliente = null;
		if (pesquisa != null && !pesquisa.isEmpty()) {
			cliente = (Cliente) Util.retonarObjetoDeColecao(pesquisa);
			
			Short indicadorFisicoJuridico = cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica();
			
			if (indicadorFisicoJuridico.equals(tipoPessoa))
				return cliente.getClienteTipo().getId();
		}

		return pesquisarClienteTipo(tipoPessoa);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Integer pesquisarClienteTipo(Short tipoPessoa) {
		Filtro filtro = new FiltroClienteTipo();
		if (tipoPessoa.shortValue() == ClienteTipo.INDICADOR_PESSOA_JURIDICA.shortValue()) {
			filtro.adicionarParametro(new ParametroSimples(FiltroClienteTipo.INDICADOR_PESSOA_FISICA_JURIDICA, ClienteTipo.INDICADOR_PESSOA_JURIDICA));
			filtro.adicionarParametro(new ParametroSimples(FiltroClienteTipo.DESCRICAO, "NAO INFORMADO"));
		} else {
			filtro.adicionarParametro(new ParametroSimples(FiltroClienteTipo.INDICADOR_PESSOA_FISICA_JURIDICA, ClienteTipo.INDICADOR_PESSOA_FISICA));
			filtro.adicionarParametro(new ParametroSimples(FiltroClienteTipo.DESCRICAO, "PARTICULARES"));
		}

		ClienteTipo clienteTipo = null;
		
		Collection pesquisa = Fachada.getInstancia().pesquisar(filtro, ClienteTipo.class.getName());
		if (pesquisa != null && !pesquisa.isEmpty()) {
			clienteTipo = (ClienteTipo) Util.retonarObjetoDeColecao(pesquisa);
		}
		
		return clienteTipo.getId();
	}
}
