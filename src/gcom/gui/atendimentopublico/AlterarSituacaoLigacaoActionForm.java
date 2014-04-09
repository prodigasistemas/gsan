package gcom.gui.atendimentopublico;
import org.apache.struts.validator.ValidatorActionForm;
public class AlterarSituacaoLigacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;	private String idOrdemServico;	private String nomeOrdemServico;	private String matriculaImovel;	private String inscricaoImovel;	private String clienteUsuario;	private String cpfCnpjCliente;	private String indicadorTipoLigacao;	private String situacaoLigacaoAguaAtual;	private String situacaoLigacaoEsgotoAtual;	private String situacaoLigacaoAguaNova;	private String situacaoLigacaoEsgotoNova;
	public String getClienteUsuario() {		return clienteUsuario;	}
	public void setClienteUsuario(String clienteUsuario) {		this.clienteUsuario = clienteUsuario;	}
	public String getCpfCnpjCliente() {		return cpfCnpjCliente;	}	public void setCpfCnpjCliente(String cpfCnpjCliente) {		this.cpfCnpjCliente = cpfCnpjCliente;	}
	public String getIdOrdemServico() {		return idOrdemServico;	}
	public void setIdOrdemServico(String idOrdemServico) {		this.idOrdemServico = idOrdemServico;	}
	public String getIndicadorTipoLigacao() {		return indicadorTipoLigacao;	}
	public void setIndicadorTipoLigacao(String indicadorTipoLigacao) {		this.indicadorTipoLigacao = indicadorTipoLigacao;	}
	public String getInscricaoImovel() {		return inscricaoImovel;	}
	public void setInscricaoImovel(String inscricaoImovel) {		this.inscricaoImovel = inscricaoImovel;	}
	public String getMatriculaImovel() {		return matriculaImovel;	}
	public void setMatriculaImovel(String matriculaImovel) {		this.matriculaImovel = matriculaImovel;	}
	public String getNomeOrdemServico() {		return nomeOrdemServico;	}
	public void setNomeOrdemServico(String nomeOrdemServico) {		this.nomeOrdemServico = nomeOrdemServico;	}
	public String getSituacaoLigacaoAguaAtual() {		return situacaoLigacaoAguaAtual;	}
	public void setSituacaoLigacaoAguaAtual(String situacaoLigacaoAguaAtual) {		this.situacaoLigacaoAguaAtual = situacaoLigacaoAguaAtual;	}
	public String getSituacaoLigacaoAguaNova() {		return situacaoLigacaoAguaNova;	}
	public void setSituacaoLigacaoAguaNova(String situacaoLigacaoAguaNova) {		this.situacaoLigacaoAguaNova = situacaoLigacaoAguaNova;	}
	public String getSituacaoLigacaoEsgotoAtual() {		return situacaoLigacaoEsgotoAtual;	}
	public void setSituacaoLigacaoEsgotoAtual(String situacaoLigacaoEsgotoAtual) {		this.situacaoLigacaoEsgotoAtual = situacaoLigacaoEsgotoAtual;	}
	public String getSituacaoLigacaoEsgotoNova() {		return situacaoLigacaoEsgotoNova;	}
	public void setSituacaoLigacaoEsgotoNova(String situacaoLigacaoEsgotoNova) {		this.situacaoLigacaoEsgotoNova = situacaoLigacaoEsgotoNova;	}}
