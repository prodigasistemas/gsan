package gcom.atendimentopublico.registroatendimento;

import gcom.arrecadacao.pagamento.Pagamento;
import gcom.faturamento.conta.Conta;

import java.math.BigDecimal;
import java.util.Collection;

public class RALocalOcorrenciaHelper {

	private Integer idImovel;
	private Collection<Conta> colecaoContas;
	@SuppressWarnings("rawtypes")
	private Collection colecaoEndereco; 
	private String pontoReferenciaLocalOcorrencia;
	private BigDecimal nnCoordenadaNorte;
	private BigDecimal nnCoordenadaLeste;
	private Integer idBairroArea; 
	private Integer idLocalidade;
	private Integer idSetorComercial; 
	private Integer idQuadra;
	private Integer idDivisaoEsgoto; 
	private Integer idUnidadeDestino; 
	private String parecerUnidadeDestino;
	private Integer idLocalOcorrencia;
	private Integer idPavimentoRua; 
	private Integer idPavimentoCalcada;
	private String descricaoLocalOcorrencia; 
	private short indicCoordenadaSemLogradouro;
	private Collection<Pagamento> colecaoPagamentos;
	private BigDecimal nnDiametro;
	
	public Integer getIdImovel() {
		return idImovel;
	}
	
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	
	public RALocalOcorrenciaHelper idImovel(Integer idImovel) {
		setIdImovel(idImovel);
		return this;
	}
	
	public Collection<Conta> getColecaoContas() {
		return colecaoContas;
	}
	
	public void setColecaoContas(Collection<Conta> colecaoContas) {
		this.colecaoContas = colecaoContas;
	}
	
	public RALocalOcorrenciaHelper colecaoContas(Collection<Conta> colecaoContas) {
		setColecaoContas(colecaoContas);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public Collection getColecaoEndereco() {
		return colecaoEndereco;
	}

	@SuppressWarnings("rawtypes")
	public void setColecaoEndereco(Collection colecaoEndereco) {
		this.colecaoEndereco = colecaoEndereco;
	}

	@SuppressWarnings("rawtypes")
	public RALocalOcorrenciaHelper colecaoEndereco(Collection colecaoEndereco) {
		setColecaoEndereco(colecaoEndereco);
		return this;
	}
	
	public String getPontoReferenciaLocalOcorrencia() {
		return pontoReferenciaLocalOcorrencia;
	}
	
	public void setPontoReferenciaLocalOcorrencia(String pontoReferenciaLocalOcorrencia) {
		this.pontoReferenciaLocalOcorrencia = pontoReferenciaLocalOcorrencia;
	}
	
	public RALocalOcorrenciaHelper pontoReferenciaLocalOcorrencia(String pontoReferenciaLocalOcorrencia) {
		setPontoReferenciaLocalOcorrencia(pontoReferenciaLocalOcorrencia);
		return this;
	}
	
	public BigDecimal getNnCoordenadaNorte() {
		return nnCoordenadaNorte;
	}
	
	public void setNnCoordenadaNorte(BigDecimal nnCoordenadaNorte) {
		this.nnCoordenadaNorte = nnCoordenadaNorte;
	}
	
	public RALocalOcorrenciaHelper nnCoordenadaNorte(BigDecimal nnCoordenadaNorte) {
		setNnCoordenadaNorte(nnCoordenadaNorte);
		return this;
	}
	
	public BigDecimal getNnCoordenadaLeste() {
		return nnCoordenadaLeste;
	}
	
	public void setNnCoordenadaLeste(BigDecimal nnCoordenadaLeste) {
		this.nnCoordenadaLeste = nnCoordenadaLeste;
	}
	
	public RALocalOcorrenciaHelper nnCoordenadaLeste(BigDecimal nnCorrdenadaLeste) {
		setNnCoordenadaLeste(nnCorrdenadaLeste);
		return this;
	}
	
	public Integer getIdBairroArea() {
		return idBairroArea;
	}
	
	public void setIdBairroArea(Integer idBairroArea) {
		this.idBairroArea = idBairroArea;
	}
	
	public RALocalOcorrenciaHelper idBairroArea(Integer idBairroArea) {
		setIdBairroArea(idBairroArea);
		return this;
	}
	
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	
	public RALocalOcorrenciaHelper idLocalidade(Integer idLocalidade) {
		setIdLocalidade(idLocalidade);
		return this;
	}
	
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}
	
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	
	public RALocalOcorrenciaHelper idSetorComercial(Integer idSetorComercial) {
		setIdSetorComercial(idSetorComercial);
		return this;
	}
	
	public Integer getIdQuadra() {
		return idQuadra;
	}
	
	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}
	
	public RALocalOcorrenciaHelper idQuadra(Integer idQuadra) {
		setIdQuadra(idQuadra);
		return this;
	}
	
	public Integer getIdDivisaoEsgoto() {
		return idDivisaoEsgoto;
	}
	
	public void setIdDivisaoEsgoto(Integer idDivisaoEsgoto) {
		this.idDivisaoEsgoto = idDivisaoEsgoto;
	}
	
	public RALocalOcorrenciaHelper idDivisaoEsgoto(Integer idDivisaoEsgoto) {
		setIdDivisaoEsgoto(idDivisaoEsgoto);
		return this;
	}
	
	public Integer getIdUnidadeDestino() {
		return idUnidadeDestino;
	}
	
	public void setIdUnidadeDestino(Integer idUnidadeDestino) {
		this.idUnidadeDestino = idUnidadeDestino;
	}
	
	public RALocalOcorrenciaHelper idUnidadeDestino(Integer idUnidadeDestino) {
		setIdUnidadeDestino(idUnidadeDestino);
		return this;
	}
	
	public String getParecerUnidadeDestino() {
		return parecerUnidadeDestino;
	}
	
	public void setParecerUnidadeDestino(String parecerUnidadeDestino) {
		this.parecerUnidadeDestino = parecerUnidadeDestino;
	}
	
	public RALocalOcorrenciaHelper parecerUnidadeDestino(String parecerUnidadeDestino) {
		setParecerUnidadeDestino(parecerUnidadeDestino);
		return this;
	}
	
	public Integer getIdLocalOcorrencia() {
		return idLocalOcorrencia;
	}
	
	public void setIdLocalOcorrencia(Integer idLocalOcorrencia) {
		this.idLocalOcorrencia = idLocalOcorrencia;
	}
	
	public RALocalOcorrenciaHelper idLocalOcorrencia(Integer idLocalOcorrencia) {
		setIdLocalOcorrencia(idLocalOcorrencia);
		return this;
	}
	
	public Integer getIdPavimentoRua() {
		return idPavimentoRua;
	}
	
	public void setIdPavimentoRua(Integer idPavimentoRua) {
		this.idPavimentoRua = idPavimentoRua;
	}
	
	public RALocalOcorrenciaHelper idPavimentoRua(Integer idPavimentoRua) {
		setIdPavimentoRua(idPavimentoRua);
		return this;
	}

	public Integer getIdPavimentoCalcada() {
		return idPavimentoCalcada;
	}
	
	public void setIdPavimentoCalcada(Integer idPavimentoCalcada) {
		this.idPavimentoCalcada = idPavimentoCalcada;
	}
	
	public RALocalOcorrenciaHelper idPavimentoCalcada(Integer idPavimentoCalcada) {
		setIdPavimentoCalcada(idPavimentoCalcada);
		return this;
	}
	
	public String getDescricaoLocalOcorrencia() {
		return descricaoLocalOcorrencia;
	}
	
	public void setDescricaoLocalOcorrencia(String descricaoLocalOcorrencia) {
		this.descricaoLocalOcorrencia = descricaoLocalOcorrencia;
	}
	
	public RALocalOcorrenciaHelper descricaoLocalOcorrencia(String descricaoLocalOcorrencia) {
		setDescricaoLocalOcorrencia(descricaoLocalOcorrencia);
		return this;
	}
	
	public short getIndicCoordenadaSemLogradouro() {
		return indicCoordenadaSemLogradouro;
	}
	
	public void setIndicCoordenadaSemLogradouro(short indicCoordenadaSemLogradouro) {
		this.indicCoordenadaSemLogradouro = indicCoordenadaSemLogradouro;
	}
	
	public RALocalOcorrenciaHelper indicadorCoordenadaSemLogradouro(short indicadorCoordenadaSemLogradouro) {
		setIndicCoordenadaSemLogradouro(indicadorCoordenadaSemLogradouro);
		return this;
	}
	
	public Collection<Pagamento> getColecaoPagamentos() {
		return colecaoPagamentos;
	}
	
	public void setColecaoPagamentos(Collection<Pagamento> colecaoPagamentos) {
		this.colecaoPagamentos = colecaoPagamentos;
	}
	
	public RALocalOcorrenciaHelper colecaoPagamentos(Collection<Pagamento> colecaoPagamentos) {
		setColecaoPagamentos(colecaoPagamentos);
		return this;
	}
	
	public BigDecimal getNnDiametro() {
		return nnDiametro;
	}
	
	public void setNnDiametro(BigDecimal nnDiametro) {
		this.nnDiametro = nnDiametro;
	}
	
	public RALocalOcorrenciaHelper nnDiametro(BigDecimal nnDiametro) {
		setNnDiametro(nnDiametro);
		return this;
	}
	
}
