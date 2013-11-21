package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
 * 
 * @author Vivianne Sousa
 * @since 11/07/2011
 */
public class ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper implements
		Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idComando;

	private String descComando;

	private Date dataExecucao;

	private Date dataEncerramento;

	private String situacao;
	
	private Date dataEncerramentoPrevista;



	public ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper(Integer idComando, String descComando, Date dataExecucao, Date dataEncerramento, String situacao, Date dataEncerramentoPrevista) {
		super();
		// TODO Auto-generated constructor stub
		this.idComando = idComando;
		this.descComando = descComando;
		this.dataExecucao = dataExecucao;
		this.dataEncerramento = dataEncerramento;
		this.situacao = situacao;
		this.dataEncerramentoPrevista = dataEncerramentoPrevista;
	}

	public ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public String getDescComando() {
		return descComando;
	}

	public void setDescComando(String descComando) {
		this.descComando = descComando;
	}

	public Integer getIdComando() {
		return idComando;
	}

	public void setIdComando(Integer idComando) {
		this.idComando = idComando;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	public Date getDataEncerramentoPrevista() {
		return dataEncerramentoPrevista;
	}

	public void setDataEncerramentoPrevista(Date dataEncerramentoPrevista) {
		this.dataEncerramentoPrevista = dataEncerramentoPrevista;
	}
}
