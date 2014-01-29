package gcom.atualizacaocadastral;

import gcom.cadastro.imovel.IImovel;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

import java.util.Collection;

public interface IControladorAtualizacaoCadastral {
	
	public Collection<IImovel> obterImoveisParaAtualizar() throws ControladorException;
	
	public void atualizarImoveisAprovados(Integer idFuncionalidade, Usuario usuarioLogado) throws ControladorException;
	
}
