package gcom.seguranca.transacao;

import gcom.cadastro.atualizacaocadastral.bean.ColunaAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionForm;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;

import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ValidadorAtualizacaoCadastral extends ValidadorAtualizacaoCadastralCallBack {
	
	public ValidadorAtualizacaoCadastral(FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro, ConsultarMovimentoAtualizacaoCadastralHelper imovel) {
		super(filtro, imovel);
	}
	
	boolean executa() {
		
		if (filtro.getExibirCampos().equals(FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_APROVACAO_EM_LOTE.toString())) {
			return validaAprovacaoEmLote();
		} else {
			return validaNormal();
		}
	}

	public boolean validaNormal() {
		boolean passou = true;
		
		List<ColunaAtualizacaoCadastral> dados = imovel.getColunasAtualizacao();
		
		passou &= passouAlteracaoHidrometro(imovel.getNumeroHidrometro(), dados, filtro);
		passou &= passouAlteracaoSituacaoAgua(dados, filtro);
		passou &= passouAlteracaoSituacaoEsgoto(dados, filtro);
		passou &= passouAlteracaoCategoria(dados, filtro);
		
		return passou;
	}
	
	public boolean validaAprovacaoEmLote() {
		boolean aprovarEmLote = true;
		
		List<ColunaAtualizacaoCadastral> dados = imovel.getColunasAtualizacao();
		
		boolean passouHidroemtro = passouAlteracaoHidrometro(imovel.getNumeroHidrometro(), dados, filtro);
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
			
			if (filtro && existeAlteracao){
				passou = true;
			}
			if (!filtro && !existeAlteracao){
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
	
	private boolean passouAlteracaoHidrometro(String numeroHidrometro, List<ColunaAtualizacaoCadastral> dados, FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro) {
		boolean passou = true;

		if (filtro.isAlteracaoHidrometro() != null){
			passou = false;
			
			boolean existeInformacaoHidrometro = false;
			
			for (ColunaAtualizacaoCadastral item : dados) {
				if (item.getNomeColuna().contains("imac_nnhidrometro")){
					existeInformacaoHidrometro = true;
					break;
				}
			}
			
			if (!filtro.isAlteracaoHidrometro()){
				if (StringUtils.isEmpty(numeroHidrometro) && !existeInformacaoHidrometro){
					passou = true; 
				}else if (StringUtils.isNotEmpty(numeroHidrometro) && existeInformacaoHidrometro){
					passou = true; 
				}
			}
			
			if (filtro.isAlteracaoHidrometro()){
				if (StringUtils.isEmpty(numeroHidrometro) && existeInformacaoHidrometro){
					passou = true; 
				}else if (StringUtils.isNotEmpty(numeroHidrometro) && !existeInformacaoHidrometro){
					passou = true; 
				}
			}
			
		}
		
		return passou;
	}

}
