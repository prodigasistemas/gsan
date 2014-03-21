package gcom.seguranca.transacao;

import gcom.cadastro.atualizacaocadastral.bean.ColunaAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class RepositorioTransacaoUtil {
	
	public Collection<ConsultarMovimentoAtualizacaoCadastralHelper> imoveisFiltrados(Collection<ConsultarMovimentoAtualizacaoCadastralHelper> imoveisAtualizacao, FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro) {
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> imoveis = new ArrayList<ConsultarMovimentoAtualizacaoCadastralHelper>();
		
		for (ConsultarMovimentoAtualizacaoCadastralHelper imovel : imoveisAtualizacao) {
			boolean passou = true;
			
			if (imovel.getIdTipoAlteracao().intValue() == AlteracaoTipo.ALTERACAO.intValue()){
				List<ColunaAtualizacaoCadastral> dados = imovel.getColunasAtualizacao();
				
				passou &= passouAlteracaoHidrometro(imovel.getNumeroHidrometro(), dados, filtro);
				passou &= passouAlteracaoSituacaoAgua(dados, filtro);
				passou &= passouAlteracaoSituacaoEsgoto(dados, filtro);
				passou &= passouAlteracaoCategoria(dados, filtro);
			}
			
			if (passou){
				imoveis.add(imovel);
			}
		}
		
		return imoveis;
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
