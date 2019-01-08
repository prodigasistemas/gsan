package gcom.seguranca.transacao;

import gcom.cadastro.atualizacaocadastral.bean.ColunaAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionForm;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;

import java.util.List;

public class ValidadorAtualizacaoCadastral extends ValidadorAtualizacaoCadastralCallBack {
	
	public ValidadorAtualizacaoCadastral(FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro, ConsultarMovimentoAtualizacaoCadastralHelper imovel) {
		super(filtro, imovel);
	}
	
	boolean executa() {
		
		if (filtro.getSituacaoImoveis() == FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_PARA_APROVACAO_EM_LOTE) {
			return validaAprovacaoEmLote();
		} else {
			return validaNormal();
		}
	}

	public boolean validaNormal() {
		boolean passou = true;
		
		List<ColunaAtualizacaoCadastral> dados = imovel.getColunasAtualizacao();
		
		passou &= passouAlteracaoHidrometro(dados, filtro);
		passou &= passouAlteracaoSituacaoAgua(dados, filtro);
		passou &= passouAlteracaoSituacaoEsgoto(dados, filtro);
		passou &= passouAlteracaoCategoria(dados, filtro);
		
		return passou;
	}
	
	public boolean validaAprovacaoEmLote() {
		boolean aprovarEmLote = true;
		
		List<ColunaAtualizacaoCadastral> dados = imovel.getColunasAtualizacao();
		
		boolean passouHidroemtro = passouAlteracaoHidrometro(dados, filtro);
		boolean passouSituacaoAgua = passouAlteracaoSituacaoAgua(dados, filtro);
		boolean passouSituacaoEsgoto = passouAlteracaoSituacaoEsgoto(dados, filtro);
		boolean passouCategoria = passouAlteracaoCategoria(dados, filtro);
		
		if (passouHidroemtro || passouSituacaoAgua || passouSituacaoEsgoto || passouCategoria ) {
			aprovarEmLote = false;
		}
		
		return aprovarEmLote;
	}
	
	private boolean passouAlteracao(List<ColunaAtualizacaoCadastral> dados, Boolean filtro, String nomeColuna) {
		boolean passou = true;
		
		if (filtro != null){
			passou = false;
			
			boolean existeAlteracao = false;
			
			for (ColunaAtualizacaoCadastral item : dados) {
				if (item.getNomeColuna().contains(nomeColuna)){
					existeAlteracao = true;
					break;
				}
			}
			
			if ((filtro && existeAlteracao) || (!filtro && !existeAlteracao)){
				passou = true;
			}
		}
		
		return passou;
	}
	
	private boolean passouAlteracaoCategoria(List<ColunaAtualizacaoCadastral> dados, FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro) {
		return passouAlteracao(dados, filtro.isAlteracaoCategoria(), "isac_qteconomia");
	}

	private boolean passouAlteracaoSituacaoAgua(List<ColunaAtualizacaoCadastral> dados, FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro) {
		return passouAlteracao(dados, filtro.isAlteracaoSituacaoAgua(), "last_id");
	}

	private boolean passouAlteracaoSituacaoEsgoto(List<ColunaAtualizacaoCadastral> dados, FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro) {
		return passouAlteracao(dados, filtro.isAlteracaoSituacaoEsgoto(), "lest_id");
	}
	
	private boolean passouAlteracaoHidrometro(List<ColunaAtualizacaoCadastral> dados, FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro) {
		return passouAlteracao(dados, filtro.isAlteracaoHidrometro(), "imac_nnhidrometro");
	}
}
