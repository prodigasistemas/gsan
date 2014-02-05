package gcom.cadastro.atualizacaocadastral.bean;

import java.util.Date;

public class ConsultarMovimentoAtualizacaoCadastralHelper {

	private Integer icAutorizado;
	
	private Integer argumento;
	
	private Integer idImovel;
	
	private Long idCliente;
	
	private Integer qtdAlteracaoImovel;
	
	private Integer qtdAlteracaoCliente;
	
	private String nomeFuncionario;
	
	private String nomeCliente;
	
	private Date dataRealizacao;
	
	private String nomeEmpresa;
	
	private Integer idArquivo;
	
	private String inscricao;
	
	private String clienteNovo;
	
	private Long idRegistroAlterado;
	
	private Integer idTipoAlteracao;
	
	public ConsultarMovimentoAtualizacaoCadastralHelper(Integer icAutorizado, Integer argumento, Integer idImovel, Long idCliente, Integer qtdAlteracaoImovel, Integer qtdAlteracaoCliente, String nomeFuncionario, String nomeCliente, Date dataRealizacao, String nomeEmpresa, Integer idArquivo, String inscricao, String clienteNovo) {
		this.icAutorizado = icAutorizado;
		this.argumento = argumento;
		this.idImovel = idImovel;
		this.idCliente = idCliente;
		this.qtdAlteracaoImovel = qtdAlteracaoImovel;
		this.qtdAlteracaoCliente = qtdAlteracaoCliente;
		this.nomeFuncionario = nomeFuncionario;
		this.nomeCliente = nomeCliente;
		this.dataRealizacao = dataRealizacao;
		this.nomeEmpresa = nomeEmpresa;
		this.idArquivo = idArquivo;
		this.inscricao = inscricao;
		this.clienteNovo = clienteNovo;
	}

	public ConsultarMovimentoAtualizacaoCadastralHelper(){}

	public Integer getArgumento() {
		return argumento;
	}

	public void setArgumento(Integer argumento) {
		this.argumento = argumento;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public Integer getQtdAlteracaoCliente() {
		return qtdAlteracaoCliente;
	}

	public void setQtdAlteracaoCliente(Integer qtdAlteracaoCliente) {
		this.qtdAlteracaoCliente = qtdAlteracaoCliente;
	}

	public Integer getQtdAlteracaoImovel() {
		return qtdAlteracaoImovel;
	}

	public void setQtdAlteracaoImovel(Integer qtdAlteracaoImovel) {
		this.qtdAlteracaoImovel = qtdAlteracaoImovel;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}	
		
	public Integer getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(Integer idArquivo) {
		this.idArquivo = idArquivo;
	}

	public Integer getIcAutorizado() {
		return icAutorizado;
	}

	public void setIcAutorizado(Integer icAutorizado) {
		this.icAutorizado = icAutorizado;
	}
	
	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getClienteNovo() {
		if (idCliente != null){
			clienteNovo = String.valueOf(idCliente); 
			if (clienteNovo.length() >= 13){
				clienteNovo =  "NOVO";
			}
		}
		
		return clienteNovo;
	}

	public Long getIdRegistroAlterado() {
		return idRegistroAlterado;
	}

	public void setIdRegistroAlterado(Long idRegistroAlterado) {
		this.idRegistroAlterado = idRegistroAlterado;
	}

	public Integer getIdTipoAlteracao() {
		return idTipoAlteracao;
	}

	public void setIdTipoAlteracao(Integer idTipoAlteracao) {
		this.idTipoAlteracao = idTipoAlteracao;
	}
}
