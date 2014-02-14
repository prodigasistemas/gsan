package gcom.cadastro.atualizacaocadastral.bean;

import java.util.Date;

public class ConsultarMovimentoAtualizacaoCadastralHelper {

	private Integer icAutorizado;
	
	private Integer idImovel;
	
	private String nomeFuncionario;
	
	private Date dataRealizacao;
	
	private Integer idArquivo;
	
	private String inscricao;
	
	private Long idRegistroAlterado;
	
	private Integer idTipoAlteracao;
	
	public ConsultarMovimentoAtualizacaoCadastralHelper(Integer icAutorizado, Integer idImovel, String nomeFuncionario, Date dataRealizacao, Integer idArquivo, String inscricao) {
		this.icAutorizado = icAutorizado;
		this.idImovel = idImovel;
		this.nomeFuncionario = nomeFuncionario;
		this.dataRealizacao = dataRealizacao;
		this.idArquivo = idArquivo;
		this.inscricao = inscricao;
	}

	public ConsultarMovimentoAtualizacaoCadastralHelper(){}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
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

	public String toString() {
		return "ConsultarMovimentoAtualizacaoCadastralHelper [idImovel=" + idImovel + ", idTipoAlteracao=" + idTipoAlteracao + "]";
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idImovel == null) ? 0 : idImovel.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsultarMovimentoAtualizacaoCadastralHelper other = (ConsultarMovimentoAtualizacaoCadastralHelper) obj;
		if (idImovel == null) {
			if (other.idImovel != null)
				return false;
		} else if (!idImovel.equals(other.idImovel))
			return false;
		return true;
	}
}
