package gcom.seguranca.transacao;

import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;

abstract class ValidadorAtualizacaoCadastralCallBack {
	
	protected FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro;
	protected ConsultarMovimentoAtualizacaoCadastralHelper imovel;
	
	public ValidadorAtualizacaoCadastralCallBack(FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro, ConsultarMovimentoAtualizacaoCadastralHelper imovel) {
		this.filtro = filtro;
		this.imovel = imovel;
	}
	
	abstract boolean executa();
}


