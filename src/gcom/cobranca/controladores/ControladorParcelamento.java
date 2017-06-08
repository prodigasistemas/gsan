package gcom.cobranca.controladores;

import gcom.cobranca.IRepositorioCobranca;
import gcom.cobranca.RepositorioCobrancaHBM;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.util.ControladorComum;

import java.util.List;

import javax.ejb.CreateException;

import org.apache.log4j.Logger;

public class ControladorParcelamento extends ControladorComum {

	private static final long serialVersionUID = -2063305788601928963L;

	private static Logger log = Logger.getLogger(ControladorParcelamento.class);

	protected IRepositorioCobranca repositorioCobranca;

	public void ejbCreate() throws CreateException {
		repositorioCobranca = RepositorioCobrancaHBM.getInstancia();
	}

	public void cancelarParcelamento(List<Parcelamento> parcelamentos) {
		
	}
}