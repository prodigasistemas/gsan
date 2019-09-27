package gcom.cadastro.atualizacaocadastral.validador;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.atualizacaocadastral.Visita;
import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.util.ControladorException;

public class ValidadorSituacaoImovelCommand extends ValidadorCommand {

	private ControladorAtualizacaoCadastralLocal controlador;
	private ArquivoTextoAtualizacaoCadastral arquivoTexto;

	public ValidadorSituacaoImovelCommand(AtualizacaoCadastralImovel cadastroImovel, ControladorAtualizacaoCadastralLocal controlador) {
		super(cadastroImovel);

		this.controlador = controlador;
	}

	@Override
	public void execute() throws Exception {
		ImovelControleAtualizacaoCadastral controle = controlador.obterImovelControle(cadastroImovel.getMatricula());

		arquivoTexto = cadastroImovel.getAtualizacaoArquivo().getArquivoTexto();

		if ((arquivoTexto.isArquivoRetornoTransmissao() || arquivoTexto.isArquivoRetornoRevisita()) && !imovelValidoTransmissao(controle))
			cadastroImovel.addMensagemErro("Imóvel não está em campo, transmitido ou em revisita.");

		if (arquivoTexto.isArquivoRetornoRevisao() && !imovelValidoRevisao(controle))
			cadastroImovel.addMensagemErro("Imóvel não está em revisão.");

		if (arquivoTexto.isArquivoRetornoFiscalizacao() && !imovelValidoFiscalizacao(controle))
			cadastroImovel.addMensagemErro("Imóvel não está em fiscalização.");

		if (imovelSuperouLimiteVisitas(controle))
			cadastroImovel.addMensagemErro(String.format("Imóvel não pode ter mais de %d visitas", Visita.QUANTIDADE_MAXIMA_SEM_PRE_AGENDAMENTO));
	}

	private boolean imovelValidoTransmissao(ImovelControleAtualizacaoCadastral controle) {
		return (arquivoTexto.isArquivoRetornoTransmissao() || arquivoTexto.isArquivoRetornoRevisita()) && (controle == null || situacaoValida(controle));
	}

	private boolean situacaoValida(ImovelControleAtualizacaoCadastral controle) {
		return controle.isImovelNovoOuNaSituacao(SituacaoAtualizacaoCadastral.EM_CAMPO) || 
			   controle.isImovelNovoOuNaSituacao(SituacaoAtualizacaoCadastral.TRANSMITIDO) || 
			   controle.isImovelNovoOuNaSituacao(SituacaoAtualizacaoCadastral.REVISITA);
	}

	private boolean imovelValidoRevisao(ImovelControleAtualizacaoCadastral controle) {
		return arquivoTexto.isArquivoRetornoRevisao() && (controle == null || controle.isImovelNovoOuNaSituacao(SituacaoAtualizacaoCadastral.EM_REVISAO));
	}

	private boolean imovelValidoFiscalizacao(ImovelControleAtualizacaoCadastral controle) {
		return arquivoTexto.isArquivoRetornoFiscalizacao() && (controle == null || controle.isImovelNovoOuNaSituacao(SituacaoAtualizacaoCadastral.EM_FISCALIZACAO));
	}

	private boolean imovelSuperouLimiteVisitas(ImovelControleAtualizacaoCadastral controle) throws ControladorException {
		Integer quantidadeVisitas = controle.getQuantidadeVisitaNaoExcluidas();

		return arquivoTexto.isArquivoRetornoRevisita() && quantidadeVisitas >= Visita.QUANTIDADE_MAXIMA_SEM_PRE_AGENDAMENTO;
	}
}
