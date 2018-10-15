package gcom.cadastro.atualizacaocadastral.command;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorCPFsClientesCommand;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorCepClienteProprietarioResponsavel;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorCepImovelCommand;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorCoordenadasCommand;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorEconomiasCommand;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorHidrometroCommand;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorLogradouroCommand;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorRamoAtividadeCommand;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorSexoCommand;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorSituacaoImovelCommand;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorTipoClientesCommand;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorTipoEnderecoCommand;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorTipoLogradouroCommand;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorTipoOperacaoCommand;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorTipoPessoaCommand;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.IRepositorioClienteImovel;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;

public class EfetuarValidacoesAtualizacaoCadastralCommand extends AbstractAtualizacaoCadastralCommand {
	
	private AtualizacaoCadastralImovel imovelAtual;
	
	private IRepositorioClienteImovel repositorioClienteImovel;
	
	public EfetuarValidacoesAtualizacaoCadastralCommand() {
		super();
	}

	public EfetuarValidacoesAtualizacaoCadastralCommand(
			ParserUtil parser,
			IRepositorioCadastro repositorioCadastro,
			ControladorUtilLocal controladorUtil,
			ControladorTransacaoLocal controladorTransacao,
			IRepositorioImovel repositorioImovel,
			ControladorEnderecoLocal controladorEndereco,
			ControladorAtualizacaoCadastralLocal controladorAtualizacaoCadastral,
			ControladorClienteLocal controladorCliente,
			IRepositorioClienteImovel repositorioClienteImovel) {
		
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao,
				repositorioImovel, controladorEndereco,
				controladorAtualizacaoCadastral, controladorCliente);
		
		this.repositorioClienteImovel = repositorioClienteImovel;
	}

	@Override
	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		imovelAtual = atualizacao.getImovelAtual();

		CadastroOcorrencia ocorrencia = imovelAtual.getCadastroOcorrencia();

		new ValidadorSituacaoImovelCommand(imovelAtual, controladorAtualizacaoCadastral).execute();
		new ValidadorCoordenadasCommand(imovelAtual, controladorAtualizacaoCadastral).execute();

		if (ocorrencia != null && ocorrencia.getIndicadorValidacao().equals(ConstantesSistema.SIM)) {
			validarLinhaCliente();
			validarLinhaImovel();
			validarLinhaRamoAtividade();
			validarLinhaMedidor();
		}

		if (!imovelAtual.isErroLayout())
			new ValidadorCepImovelCommand(imovelAtual, imovelAtual.getLinhaImovel()).execute();
	}

	private void validarLinhaMedidor() {
		new ValidadorHidrometroCommand(imovelAtual, imovelAtual.getLinhaMedidor(), controladorUtil);
	}

	private void validarLinhaRamoAtividade() {
		new ValidadorRamoAtividadeCommand(imovelAtual, imovelAtual.getLinhaRamoAtividade(), repositorioCadastro);
	}

	private void validarLinhaImovel() throws Exception {
		new ValidadorTipoOperacaoCommand(imovelAtual, imovelAtual.getLinhaImovel(), repositorioImovel).execute();
		new ValidadorTipoLogradouroCommand(imovelAtual, imovelAtual.getLinhaImovel(), repositorioImovel).execute();
		new ValidadorLogradouroCommand(imovelAtual, imovelAtual.getLinhaImovel(), repositorioImovel).execute();
		new ValidadorEconomiasCommand(imovelAtual, imovelAtual.getLinhaImovel()).execute();
	}

	private void validarLinhaCliente() throws Exception {
		new ValidadorSexoCommand(imovelAtual, imovelAtual.getLinhaCliente()).execute();
		new ValidadorCPFsClientesCommand(imovelAtual, imovelAtual.getLinhaCliente(), repositorioClienteImovel).execute();
		new ValidadorCepClienteProprietarioResponsavel(imovelAtual, imovelAtual.getLinhaCliente()).execute();
		new ValidadorTipoPessoaCommand(imovelAtual, imovelAtual.getLinhaCliente(), repositorioClienteImovel).execute();
		new ValidadorTipoEnderecoCommand(imovelAtual, imovelAtual.getLinhaCliente()).execute();
		new ValidadorTipoClientesCommand(imovelAtual, imovelAtual.getLinhaCliente()).execute();
	}
}
