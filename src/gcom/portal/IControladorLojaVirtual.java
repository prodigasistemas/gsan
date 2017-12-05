package gcom.portal;

import gcom.cadastro.localidade.Localidade;
import gcom.gui.portal.LojaAtendimentoHelper;
import gcom.util.ControladorException;

import java.util.List;

public interface IControladorLojaVirtual {

	public List<Localidade> pesquisarLocalidades() throws ControladorException;

	public List<LojaAtendimentoHelper> pesquisarLojasAtendimento(String localidade) throws ControladorException;

	public boolean isCpfCnpjCadastrado(String matricula, String cpfCnpj) throws ControladorException;
}
