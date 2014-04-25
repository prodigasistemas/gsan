package gcom.seguranca.transacao;

import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;
import gcom.gui.cadastro.atualizacaocadastral.ImovelRelatorioAtualizacaoCadastral;

abstract class RelatorioConsultaAtualizacaoCadastralCallBack {
	
	protected ImovelRelatorioAtualizacaoCadastral relatorio;
	protected FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro;
	protected ConsultarMovimentoAtualizacaoCadastralHelper imovel;
	
	public RelatorioConsultaAtualizacaoCadastralCallBack(ImovelRelatorioAtualizacaoCadastral relatorio, FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro, ConsultarMovimentoAtualizacaoCadastralHelper imovel) {
		this.relatorio = relatorio;
		this.filtro = filtro;
		this.imovel = imovel;
	}
	
	abstract void executa();
}


