package gcom.atualizacaocadastral;

import gcom.cadastro.imovel.IImovel;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

import java.util.Collection;
import java.util.Date;

public interface IControladorAtualizacaoCadastral {
	
	public Collection<IImovel> obterImoveisParaAtualizar() throws ControladorException;
	
	public void atualizarImoveisAprovados(Integer idFuncionalidade, Usuario usuarioLogado) throws ControladorException;
	
	public void apagarInformacoesRetornoImovelAtualizacaoCadastral(Integer idImovel) throws ControladorException;
	
	public Collection<Integer> pesquisarImoveisPorSituacaoPeriodo(Date dataInicial, Date dataFinal, Integer idSituacaoCadastral)
			throws ControladorException;
	
}
