package gcom.cadastro.atualizacaocadastral.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConsultarMovimentoAtualizacaoCadastralHelper implements Serializable{
	private static final long serialVersionUID = 8769003327603590172L;

	private Integer icAutorizado;
	
	private Integer idImovel;
	
	private String nomeFuncionario;
	
	private Date dataRealizacao;
	
	private Integer idArquivo;
	
	private String inscricao;
	
	private Long idRegistroAlterado;
	
	private Integer idTipoAlteracao;
	
	private String numeroHidrometro;
	
	private Integer idLigacaoAgua; 
	
	private Integer idLigacaoEsgoto;
	
	private List<ColunaAtualizacaoCadastral> colunasAtualizacao = new ArrayList<ColunaAtualizacaoCadastral>();
	
	private Set<CategoriaAtualizacaoCadastral> categorias = new HashSet<CategoriaAtualizacaoCadastral>();
	
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
	
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public Integer getIdLigacaoAgua() {
		return idLigacaoAgua;
	}

	public void setIdLigacaoAgua(Integer idLigacaoAgua) {
		this.idLigacaoAgua = idLigacaoAgua;
	}

	public Integer getIdLigacaoEsgoto() {
		return idLigacaoEsgoto;
	}

	public void setIdLigacaoEsgoto(Integer idLigacaoEsgoto) {
		this.idLigacaoEsgoto = idLigacaoEsgoto;
	}
	
	public List<ColunaAtualizacaoCadastral> getColunasAtualizacao() {
		return colunasAtualizacao;
	}

	public void addColunaAtualizacao(ColunaAtualizacaoCadastral colunaAtualizacao) {
		this.colunasAtualizacao.add(colunaAtualizacao);
	}

	public Set<CategoriaAtualizacaoCadastral> getCategorias() {
		return categorias;
	}

	public void addCategoria(CategoriaAtualizacaoCadastral categoria) {
		this.categorias.add(categoria);
	}

	@Override
	public String toString() {
		return "ConsultarMovimentoAtualizacaoCadastralHelper [idImovel=" + idImovel + ", idTipoAlteracao=" + idTipoAlteracao + ", numeroHidrometro="
				+ numeroHidrometro + ", idLigacaoAgua=" + idLigacaoAgua + ", idLigacaoEsgoto=" + idLigacaoEsgoto + "]";
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
