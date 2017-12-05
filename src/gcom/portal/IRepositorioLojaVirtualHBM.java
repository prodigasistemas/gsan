package gcom.portal;

import gcom.cadastro.localidade.Localidade;
import gcom.gui.portal.LojaAtendimentoHelper;
import gcom.util.ErroRepositorioException;

import java.util.List;

public interface IRepositorioLojaVirtualHBM {

	public List<Localidade> pesquisarLocalidades() throws ErroRepositorioException;

	public List<LojaAtendimentoHelper> pesquisarLojasAtendimento(String localidade) throws ErroRepositorioException;

	public boolean isCpfCnpjCadastrado(String matricula, String cpfCnpj) throws ErroRepositorioException;
}
