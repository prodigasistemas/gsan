package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.IRepositorioClienteImovel;
import gcom.util.Util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ValidadorTipoPessoaCommand extends ValidadorCommand {

	private final String MSG_TIPO_PESSOA_USUARIO = "Usuário não é pessoa física ou jurídica";
	private final String MSG_TIPO_PESSOA_PROPRIETARIO = "Proprietário não é pessoa física ou jurídica";
	private final String MSG_TIPO_PESSOA_RESPONSAVEL = "Responsável não é pessoa física ou jurídica";
	private final String MSG_CPF_CNPJ_INCONSISTENTE = "Cpf ou Cnpj incompatível com tipo do %s";
	private final String MSG_TIPO_PESSOA_INCONSISTENTE = "Pessoa física ou jurídica difere para %s";

	private IRepositorioClienteImovel repositorioClienteImovel;

	public ValidadorTipoPessoaCommand(AtualizacaoCadastralImovel cadastroImovel
			, Map<String, String> linha
			, IRepositorioClienteImovel repositorioClienteImovel) {
		super(cadastroImovel, linha);
		
		this.repositorioClienteImovel = repositorioClienteImovel;
	}
	
	private Map<Integer, Short> clienteTipoPessoa = new HashMap<Integer, Short>();

	public void execute() throws Exception {
		Collection<ClienteImovel> clientes = repositorioClienteImovel.pesquisarClienteImovel(cadastroImovel.getMatricula());
		
		for (ClienteImovel clienteImovel : clientes) {
			clienteTipoPessoa.put(clienteImovel.getClienteRelacaoTipo().getId(), clienteImovel.getCliente().getClienteTipo().getIndicadorPessoaFisicaJuridica());
		}
		
		if (StringUtils.isNotEmpty(linha.get("cnpjCpfUsuario"))){
			if (tipoPessoaValido(linha.get("tipoPessoaUsuario"), MSG_TIPO_PESSOA_USUARIO)){
				validarCpfCnpj("Usuario");
//				validarTipoCadastrado("Usuario", ClienteRelacaoTipo.USUARIO);
			}
			
		}
		
		if (StringUtils.isNotEmpty(linha.get("nomeProprietario"))){
			if (tipoPessoaValido(linha.get("tipoPessoaProprietario"), MSG_TIPO_PESSOA_PROPRIETARIO)){
				validarCpfCnpj("Proprietario");
//				validarTipoCadastrado("Proprietario", ClienteRelacaoTipo.PROPRIETARIO);
			}
		}

		if (StringUtils.isNotEmpty(linha.get("nomeResponsavel"))){
			if (tipoPessoaValido(linha.get("tipoPessoaResponsavel"), MSG_TIPO_PESSOA_RESPONSAVEL)){
				validarCpfCnpj("Responsavel");
//				validarTipoCadastrado("Responsavel", ClienteRelacaoTipo.RESPONSAVEL);
			}
		}
	}
	
	private void validarTipoCadastrado(String cliente, Short relacao) {
		String tipo = linha.get("tipoPessoa" + cliente);
		if (existeTipoRelacao(relacao) && tipoRelacao(relacao) != Short.valueOf(tipo)){
			cadastroImovel.addMensagemErro(String.format(MSG_TIPO_PESSOA_INCONSISTENTE, cliente));
		}
	}

	private boolean existeTipoRelacao(Short relacao) {
		return clienteTipoPessoa.get((int) relacao) != null;
	}

	private short tipoRelacao(Short relacao) {
		return clienteTipoPessoa.get((int) relacao).shortValue();
	}

	private boolean tipoPessoaValido(String tipo, String mensagem) {
		if (StringUtils.isEmpty(tipo) || !tipoFisicaOuJuridico(tipo.charAt(0))){
			cadastroImovel.addMensagemErro(mensagem);
			return false;
		}
		return true;
	}

	private void validarCpfCnpj(String cliente) {
		String tipo = linha.get("tipoPessoa" + cliente);
		String cpfCnpj = linha.get("cnpjCpf" + cliente);
		linha.put("cpf" + cliente, "");
		linha.put("cnpj" + cliente, "");
		
		if (StringUtils.isNotEmpty(cpfCnpj)){
			if (pessoaFisica(tipo.charAt(0))){
				if (Util.isCPF(cpfCnpj)){
					linha.put("cpf" + cliente, cpfCnpj.substring(3));
				}else{
					cadastroImovel.addMensagemErro(String.format(MSG_CPF_CNPJ_INCONSISTENTE, cliente));
				}
			}
			
			if (pessoaJuridica(tipo.charAt(0))){
				if (Util.isCNPJ(cpfCnpj)){
					linha.put("cnpj" + cliente, cpfCnpj);
				}else{
					cadastroImovel.addMensagemErro(String.format(MSG_CPF_CNPJ_INCONSISTENTE, cliente));
				}
			}
		}
	}
	
	private boolean tipoFisicaOuJuridico(char tipo){
		return tipo == '1' || tipo == '2';
	}

	private boolean pessoaFisica(char tipo){
		return tipo == '1';
	}

	private boolean pessoaJuridica(char tipo){
		return tipo == '2';
	}
}