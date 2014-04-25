package gcom.relatorio.cadastro.micromedicao;

import java.io.Serializable;

/**
 * 
 * [UC0999] Gerar Relatório de Coleta de Medidor de Energia.
 * 
 * @author Hugo Leonardo
 * 
 * @date 09/03/2010
 */
public class RelatorioColetaMedidorEnergiaHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idFaturamentoGrupo;
	private String descricaoFaturamentoGrupo;
	private String idLocalidade;	
	private String descricaoLocalidade;
	private String inscricao;
	private String rota;
	private String sequencialRota;
	private String numeroQuadra;
	private String nomeCliente;
	private String endereco;
	private String matriculaImovel;
	
	public String getDescricaoFaturamentoGrupo() {
		return descricaoFaturamentoGrupo;
	}
	
	public void setDescricaoFaturamentoGrupo(String descricaoFaturamentoGrupo) {
		this.descricaoFaturamentoGrupo = descricaoFaturamentoGrupo;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}
	
	public void setIdFaturamentoGrupo(String idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}
	
	public String getInscricao() {
		return inscricao;
	}
	
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getSequencialRota() {
		return sequencialRota;
	}

	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}
	
}
