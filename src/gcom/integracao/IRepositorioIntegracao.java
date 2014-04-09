package gcom.integracao;

import java.util.Collection;

import gcom.atendimentopublico.ordemservico.OrdemServicoMovimento;
import gcom.util.ErroRepositorioException;

public interface IRepositorioIntegracao {

	public Collection pesquisarOrdemServicoMovimentoParaEnvioSAM() throws ErroRepositorioException;

	public void exportarOrdemServicoMovimentos(Collection ordensServicoParaExportacao) throws ErroRepositorioException;

	public Collection pesquisarOrdensServicoParaRecebimentoUPA() throws ErroRepositorioException;

	public void importarOrdensServico(Collection ordensServicoParaImportacao) throws ErroRepositorioException;

	public void atualizarIndicadorMovimentoOrdemServicoMovimento(OrdemServicoMovimento movimento) throws ErroRepositorioException;
	
	public Object[] pesquisarHorarioProcessoIntegracaoUPA() throws ErroRepositorioException; 

}
