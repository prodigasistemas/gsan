package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.seguranca.transacao.AlteracaoTipo;

import java.util.Map;

public class ValidadorTipoOperacaoCommand extends ValidadorCommand {
	
	private IRepositorioImovel repositorioImovel;

	public ValidadorTipoOperacaoCommand(
			AtualizacaoCadastralImovel cadastroImovel,
			Map<String, String> linha, IRepositorioImovel repositorioImovel) {
		super(cadastroImovel, linha);
		
		this.repositorioImovel = repositorioImovel;
	}

	@Override
	public void execute() throws Exception {

		String tipoOperacao = linha.get("tipoOperacao");

		if (campoNumericoInvalido(tipoOperacao)) {
			cadastroImovel.addMensagemErro("Tipo da operação inválida");
		} else {
			Integer tipo = Integer.valueOf(tipoOperacao);
			
			if (tipo == AlteracaoTipo.INCLUSAO) {
				String codigoLogradouro = linha.get("codigoLogradouro");
				String codigoMunicipio = linha.get("codigoMunicipio");
				String nomeBairro = linha.get("bairro");
				String municipio = linha.get("municipio");
				String cep = linha.get("cep");

				boolean codigosInvalidos = false;
				
				if (campoNumericoInvalido(codigoLogradouro)) {
					cadastroImovel.addMensagemErro("Código do logradouro inválido");
					codigosInvalidos = true;
				}
				
				if (campoNumericoInvalido(codigoMunicipio)) {
					cadastroImovel.addMensagemErro("Código do município inválido");
					codigosInvalidos = true;
				}

				if (campoNumericoInvalido(cep)) {
					cadastroImovel.addMensagemErro("CEP inválido");
					codigosInvalidos = true;
				}

				if (!codigosInvalidos) {
					Integer codigo = Integer.valueOf(codigoLogradouro);
					
					Logradouro logradouro = repositorioImovel.pesquisarLogradouro(codigo);
					
					boolean bairroInvalido = true;
					
					for (LogradouroBairro logrBairro : logradouro.getLogradouroBairros()) {
						if (logrBairro.getBairro().getNome().equalsIgnoreCase(nomeBairro)) {
							bairroInvalido = false;
						}
					}

					if (bairroInvalido) {
						cadastroImovel.addMensagemErro("Bairro inválido");
					}

					if (!logradouro.getMunicipio().getNome().equalsIgnoreCase(municipio)) {
						cadastroImovel.addMensagemErro("Município inválido");
					}

					boolean cepInvalido = true;
					
					for (LogradouroCep logrCep : logradouro.getLogradouroCeps()) {
						if (logrCep.getCep().getCodigo() == Integer.valueOf(cep)) {
							cepInvalido = false;
						}
					}

					if (cepInvalido) {
						cadastroImovel.addMensagemErro("CEP inválido");
					}
				}

				String inscricao = linha.get("inscricao");
				String lote = inscricao.substring(10, 14);
				String sublote = inscricao.substring(14);

				if (campoNumericoInvalido(lote)) {
					cadastroImovel.addMensagemErro("Lote inválido");
				}
				if (campoNumericoInvalido(sublote)) {
					cadastroImovel.addMensagemErro("Sublote inválido");
				}

				int qtdInscricao = 0;
				for (AtualizacaoCadastralImovel itemAtualizacao : 
					cadastroImovel.getAtualizacaoArquivo().getImoveisComErro()) {
					
					if (itemAtualizacao.getLinhaImovel("inscricao").equals(inscricao)) {
						qtdInscricao++;
					}
				}

				if (qtdInscricao > 1) {
					cadastroImovel.addMensagemErro("Número de inscrição repetido");
				}

				cadastroImovel.limparDadosProprietario();
				cadastroImovel.limparDadosResponsavel();
			}
		}
	}
}
