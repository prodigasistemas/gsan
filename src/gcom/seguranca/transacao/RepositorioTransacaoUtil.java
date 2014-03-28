package gcom.seguranca.transacao;

import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;

import java.util.ArrayList;
import java.util.Collection;

public class RepositorioTransacaoUtil {
	
	public Collection<ConsultarMovimentoAtualizacaoCadastralHelper> imoveisFiltrados(Collection<ConsultarMovimentoAtualizacaoCadastralHelper> imoveisAtualizacao, FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro) {
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> imoveis = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		for (ConsultarMovimentoAtualizacaoCadastralHelper imovel : imoveisAtualizacao) {
			
			if (new ValidadorAtualizacaoCadastral(filtro, imovel).executa()){
				imoveis.add(imovel);
			}
		}
		
		return imoveis;
	}


//	private boolean passouAlteracao(List<ColunaAtualizacaoCadastral> dados, Boolean filtro, String nomeColuna) {
//		boolean passou = true;
//		
//		if (filtro != null){
//			passou = false;
//			
//			boolean existeAlteracao = false;
//			
//			for (ColunaAtualizacaoCadastral item : dados) {
//				if (item.getNomeColuna().contains(nomeColuna)){
//					existeAlteracao = true;
//					break;
//				}
//			}
//			
//			if (filtro && existeAlteracao){
//				passou = true;
//			}
//			if (!filtro && !existeAlteracao){
//				passou = true;
//			}
//		}
//		
//		return passou;
//	}
//	
//	private boolean passouAlteracaoCategoria(List<ColunaAtualizacaoCadastral> dados, FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro) {
//		return passouAlteracao(dados, filtro.isAlteracaoCategoria(), "isac_qteconomia");
//	}
//
//	private boolean passouAlteracaoSituacaoAgua(List<ColunaAtualizacaoCadastral> dados, FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro) {
//		return passouAlteracao(dados, filtro.isAlteracaoSituacaoAgua(), "last_id");
//	}
//
//	private boolean passouAlteracaoSituacaoEsgoto(List<ColunaAtualizacaoCadastral> dados, FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro) {
//		return passouAlteracao(dados, filtro.isAlteracaoSituacaoEsgoto(), "lest_id");
//	}
	
//	private boolean passouAlteracaoHidrometro(String numeroHidrometro, List<ColunaAtualizacaoCadastral> dados, FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro) {
//		boolean passou = true;
//
//		if (filtro.isAlteracaoHidrometro() != null){
//			passou = false;
//			
//			boolean existeInformacaoHidrometro = false;
//			
//			for (ColunaAtualizacaoCadastral item : dados) {
//				if (item.getNomeColuna().contains("imac_nnhidrometro")){
//					existeInformacaoHidrometro = true;
//					break;
//				}
//			}
//			
//			if (!filtro.isAlteracaoHidrometro()){
//				if (StringUtils.isEmpty(numeroHidrometro) && !existeInformacaoHidrometro){
//					passou = true; 
//				}else if (StringUtils.isNotEmpty(numeroHidrometro) && existeInformacaoHidrometro){
//					passou = true; 
//				}
//			}
//			
//			if (filtro.isAlteracaoHidrometro()){
//				if (StringUtils.isEmpty(numeroHidrometro) && existeInformacaoHidrometro){
//					passou = true; 
//				}else if (StringUtils.isNotEmpty(numeroHidrometro) && !existeInformacaoHidrometro){
//					passou = true; 
//				}
//			}
//			
//		}
//		
//		return passou;
//	}
	
//	abstract class CallBack {
//		
//		protected String tipoFiltro;
//		
//		public CallBack(String tipoFiltro) {
//			this.tipoFiltro = tipoFiltro;
//		}
//		
//		abstract void executa(FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro,
//				ConsultarMovimentoAtualizacaoCadastralHelper imovel);
//	}
//	
//	class Validador extends CallBack {
//		public Validador(String tipoFiltro) {
//			super(tipoFiltro);
//		}
//		
//		void executa(FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro, ConsultarMovimentoAtualizacaoCadastralHelper imovel) {
//			
//			if (filtro.getExibirCampos().equals(FiltrarAlteracaoAtualizacaoCadastralActionForm.APROVACAO_EM_LOTE)) {
//				validaAprovacaoEmLote(filtro, imovel);
//			} else {
//				validaNormal(filtro, imovel);
//			}
//		}
//
//		public boolean validaNormal(FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro, ConsultarMovimentoAtualizacaoCadastralHelper imovel) {
//			boolean passou = true;
//			
//			List<ColunaAtualizacaoCadastral> dados = imovel.getColunasAtualizacao();
//			
//			passou &= passouAlteracaoHidrometro(imovel.getNumeroHidrometro(), dados, filtro);
//			passou &= passouAlteracaoSituacaoAgua(dados, filtro);
//			passou &= passouAlteracaoSituacaoEsgoto(dados, filtro);
//			passou &= passouAlteracaoCategoria(dados, filtro);
//			
//			return passou;
//		}
//		
//		public boolean validaAprovacaoEmLote(FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro, ConsultarMovimentoAtualizacaoCadastralHelper imovel) {
//			boolean passou = false;
//			
//			List<ColunaAtualizacaoCadastral> dados = imovel.getColunasAtualizacao();
//			
//			boolean passouHidroemtro = passouAlteracaoHidrometro(imovel.getNumeroHidrometro(), dados, filtro);
//			boolean passouSituacaoAgua = passouAlteracaoSituacaoAgua(dados, filtro);
//			boolean passouSituacaoEsgoto = passouAlteracaoSituacaoEsgoto(dados, filtro);
//			boolean passouCategoria = passouAlteracaoCategoria(dados, filtro);
//			
//			if (passouHidroemtro || passouSituacaoAgua || passouSituacaoEsgoto || passouCategoria ) {
//				passou = true;
//			}
//			
//			return passou;
//		}
//
//	}
}
