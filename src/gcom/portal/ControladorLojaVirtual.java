package gcom.portal;

import gcom.cadastro.localidade.Localidade;
import gcom.gui.portal.LojaAtendimentoHelper;
import gcom.util.ControladorComum;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.List;

import javax.ejb.CreateException;

public class ControladorLojaVirtual extends ControladorComum implements IControladorLojaVirtual {

	private static final long serialVersionUID = 2638552511171256129L;

	protected IRepositorioLojaVirtualHBM repositorio;

	public void ejbCreate() throws CreateException {
		repositorio = RepositorioLojaVirtualHBM.getInstancia();
	}

	public List<Localidade> pesquisarLocalidades() throws ControladorException {
		try {
			return repositorio.pesquisarLocalidades();
		} catch (ErroRepositorioException erx) {
			erx.printStackTrace();
			throw new ControladorException("erro.sistema", erx);
		}
	}

	public List<LojaAtendimentoHelper> pesquisarLojasAtendimento(String localidade) throws ControladorException {
		try {
			return repositorio.pesquisarLojasAtendimento(localidade);
		} catch (ErroRepositorioException erx) {
			erx.printStackTrace();
			throw new ControladorException("erro.sistema", erx);
		}
	}

	public boolean isCpfCnpjCadastrado(String matricula, String cpfCnpj) throws ControladorException {
		try {
			return repositorio.isCpfCnpjCadastrado(matricula, cpfCnpj);
		} catch (ErroRepositorioException erx) {
			erx.printStackTrace();
			throw new ControladorException("erro.sistema", erx);
		}
	}
}
