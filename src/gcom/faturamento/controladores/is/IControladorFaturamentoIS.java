package gcom.faturamento.controladores.is;

import java.io.BufferedReader;
import java.util.Collection;

import gcom.faturamento.MovimentoContaPrefaturada;
import gcom.faturamento.bean.RetornoAtualizarFaturamentoMovimentoCelularHelper;
import gcom.micromedicao.ArquivoTextoRetornoIS;
import gcom.util.ControladorException;

public interface IControladorFaturamentoIS {
	
	public RetornoAtualizarFaturamentoMovimentoCelularHelper atualizarFaturamentoMovimentoCelular(BufferedReader buffer, String nomeArquivo, boolean offLine,
			boolean finalizarArquivo, Integer idRota, ArquivoTextoRetornoIS atualizarFaturamentoMovimentoCelular, BufferedReader bufferOriginal)
			throws ControladorException;

}
