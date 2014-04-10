package gcom.cadastro.atualizacaocadastral.command;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;

public class EfetuarValidacoesAtualizacaoCadastralCommand extends AbstractAtualizacaoCadastralCommand {
	
	public EfetuarValidacoesAtualizacaoCadastralCommand(){
		super();
	}

	public EfetuarValidacoesAtualizacaoCadastralCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil,
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, ControladorEnderecoLocal controladorEndereco,
			ControladorAtualizacaoCadastralLocal controladorImovel, ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorEndereco, controladorImovel, controladorCliente);
	}

	@Override
	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		if (atualizacao.getImovelAtual().getDadosImovel().contemTipoEconomia(TipoEconomia.COMERCIAL, TipoEconomia.PUBLICO, TipoEconomia.INDUSTRIAL)){
			if (atualizacao.getImovelAtual().getDadosRamoAtividade().isEmpty()){
				atualizacao.getImovelAtual().addMensagemErro("Imóvel do tipo comercial, público ou industrial deve possuir ramo de atividade.");
			}
		}
	}
}
