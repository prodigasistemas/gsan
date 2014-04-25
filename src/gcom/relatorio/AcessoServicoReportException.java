package gcom.relatorio;

import gcom.util.exception.BaseRuntimeException;

public class AcessoServicoReportException extends BaseRuntimeException {
	private static final long serialVersionUID = 5695022343055278826L;

	public AcessoServicoReportException() {
		super("erro_acesso_servico_report");
	}
}
