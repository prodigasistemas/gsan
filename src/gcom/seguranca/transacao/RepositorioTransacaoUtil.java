package gcom.seguranca.transacao;

import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;
import gcom.gui.cadastro.atualizacaocadastral.ImovelRelatorioAtualizacaoCadastral;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

	public List<ImovelRelatorioAtualizacaoCadastral> relatorioConsultaAtualizacaoCadastral(Collection<ConsultarMovimentoAtualizacaoCadastralHelper> imoveisAtualizacao, FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro) {
		
		List<ImovelRelatorioAtualizacaoCadastral> relatorio = new ArrayList<ImovelRelatorioAtualizacaoCadastral>();
		
		for (ConsultarMovimentoAtualizacaoCadastralHelper imovel : imoveisAtualizacao) {
			ImovelRelatorioAtualizacaoCadastral imovelRelatorio = new ImovelRelatorioAtualizacaoCadastral();
			relatorio.add(imovelRelatorio);
			imovelRelatorio.setDescImovel(qualDescricao(imovel));

			new AdicionaAlteracaoHidrometro(imovelRelatorio, filtro, imovel).executa();
			new AdicionaAlteracaoSituacaoAgua(imovelRelatorio, filtro, imovel).executa();
			new AdicionaAlteracaoSituacaoEsgoto(imovelRelatorio, filtro, imovel).executa();
			new AdicionaAlteracaoSubCategoria(imovelRelatorio, filtro, imovel).executa();
		}
		
		return relatorio;
	}

	private String qualDescricao(ConsultarMovimentoAtualizacaoCadastralHelper imovel) {
		if (imovel.getIdTipoAlteracao().intValue() == AlteracaoTipo.ALTERACAO.intValue()){
			return String.valueOf(imovel.getIdImovel());
		}
		if (imovel.getIdTipoAlteracao().intValue() == AlteracaoTipo.INCLUSAO.intValue()){
			return "Novo" ;
		}
		if (imovel.getIdTipoAlteracao().intValue() == AlteracaoTipo.EXCLUSAO.intValue()){
			return "Exclusão" ;
		}
		return null;
	}
}
