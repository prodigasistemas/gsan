package gcom.cadastro.atualizacaocadastral.validador;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

public class ValidadorSituacaoImovelCommand extends ValidadorCommand {
	
	private ControladorAtualizacaoCadastralLocal controlador;
	
	public ValidadorSituacaoImovelCommand(AtualizacaoCadastralImovel cadastroImovel,
			ControladorAtualizacaoCadastralLocal controlador) {
		super(cadastroImovel);
		
		this.controlador = controlador;
	}

	@Override
	public void execute() throws Exception {
		ImovelControleAtualizacaoCadastral imovelControle = controlador.obterImovelControle(cadastroImovel.getMatricula());
		
		if (!imovelValidoTransmissao(imovelControle)) {
			cadastroImovel.addMensagemErro("Tipo de retorno inválido. Imóvel não está EM CAMPO, TRANSMITIDO OU EM REVISITA.");
		}
		
		if (cadastroImovel.getAtualizacaoArquivo().getArquivoTexto().isArquivoRetornoRevisao() && !imovelValidoRevisao(imovelControle)) {
			cadastroImovel.addMensagemErro("Tipo de retorno inválido. Imóvel não está em revisão.");
		}
		
		if (cadastroImovel.getAtualizacaoArquivo().getArquivoTexto().isArquivoRetornoFiscalizacao() && !imovelValidoFiscalizacao(imovelControle)) {
			cadastroImovel.addMensagemErro("Tipo de retorno inválido. Imóvel não está em fiscalização.");
		}
	}
	
	private boolean imovelValidoTransmissao(ImovelControleAtualizacaoCadastral imovelControle) {
		return (cadastroImovel.getAtualizacaoArquivo().getArquivoTexto().isArquivoRetornoTransmissao() 
				|| cadastroImovel.getAtualizacaoArquivo().getArquivoTexto().isArquivoRetornoRevisita())
				&& (imovelControle == null 
					|| (imovelControle.isImovelNovoOuNaSituacao(SituacaoAtualizacaoCadastral.TRANSMITIDO) 
					|| imovelControle.isImovelNovoOuNaSituacao(SituacaoAtualizacaoCadastral.EM_CAMPO)
					|| imovelControle.isImovelNovoOuNaSituacao(SituacaoAtualizacaoCadastral.REVISITA)));
	}
	
	private boolean imovelValidoRevisao(ImovelControleAtualizacaoCadastral imovelControle) {
		return cadastroImovel.getAtualizacaoArquivo().getArquivoTexto().isArquivoRetornoRevisao()
				&& (imovelControle == null || imovelControle.isImovelNovoOuNaSituacao(SituacaoAtualizacaoCadastral.EM_REVISAO));
	}
	
	private boolean imovelValidoFiscalizacao(ImovelControleAtualizacaoCadastral imovelControle) {
		return cadastroImovel.getAtualizacaoArquivo().getArquivoTexto().isArquivoRetornoFiscalizacao()
				&& (imovelControle == null || imovelControle.isImovelNovoOuNaSituacao(SituacaoAtualizacaoCadastral.EM_FISCALIZACAO));
	}
}
