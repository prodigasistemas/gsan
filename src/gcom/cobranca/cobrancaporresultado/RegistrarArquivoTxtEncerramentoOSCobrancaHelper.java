package gcom.cobranca.cobrancaporresultado;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cobranca.ComandoEmpresaCobrancaConta;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
 * 
 * @author Mariana Victor
 * @date 20/06/2011
 */
public class RegistrarArquivoTxtEncerramentoOSCobrancaHelper implements
		Serializable {
	
	private static final long serialVersionUID = 1L;

	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;
	
	private OrdemServico ordemServico;

	private Integer motivoEncerramento;

	private Date dataEncerramento;

	private Integer idUsuario;
	
	
	public RegistrarArquivoTxtEncerramentoOSCobrancaHelper() {
		super();
	}

	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}

	public void setComandoEmpresaCobrancaConta(
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getMotivoEncerramento() {
		return motivoEncerramento;
	}

	public void setMotivoEncerramento(Integer motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}


}
