package gcom.cobranca.controladores;

import gcom.cobranca.IRepositorioCobranca;
import gcom.cobranca.RepositorioCobrancaHBM;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.util.ControladorComum;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.CreateException;

import org.apache.log4j.Logger;

public class ControladorParcelamento extends ControladorComum {

	private static final long serialVersionUID = -2063305788601928963L;

	private static Logger logger = Logger.getLogger(ControladorParcelamento.class);

	protected IRepositorioCobranca repositorioCobranca;

	public void ejbCreate() throws CreateException {
		repositorioCobranca = RepositorioCobrancaHBM.getInstancia();
	}

	public void cancelarParcelamento(List<Parcelamento> parcelamentos) {

	}

	private void cancelarDebitoACobrar() {

	}

	private void cancelarCreditoARealizar() {

	}

	private BigDecimal calcularSaldoDevedor() {
		return null;
	}

	private BigDecimal calcularAcrescimosPorImpontualidade() {
		return null;
	}

	private void gerarContaIncluida() {

	}
}