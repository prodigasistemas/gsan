package gcom.cadastro.atualizacaocadastral.command;

import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;

public class EfetuarValidacoesAtualizacaoCadastralCommand extends AbstractAtualizacaoCadastralCommand {
	
	public EfetuarValidacoesAtualizacaoCadastralCommand(){
		super();
	}

	public EfetuarValidacoesAtualizacaoCadastralCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil,
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, ControladorImovelLocal controladorImovel,
			ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorImovel, controladorCliente);
	}

	@Override
	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		if (atualizacao.getImovelAtual().getDadosImovel().contemTipoEconomia(TipoEconomia.COMERCIAL, TipoEconomia.PUBLICO, TipoEconomia.INDUSTRIAL)){
			if (atualizacao.getImovelAtual().getDadosRamoAtividade().isEmpty()){
				atualizacao.getImovelAtual().addMensagemErro("Imovel do tipo comercial, público ou industrial deve possuir ramo de atividade");
			}
		}
	}
}
