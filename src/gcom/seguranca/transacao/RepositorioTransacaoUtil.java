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


}
