package gcom.atendimentopublico.registroatendimento;

import gcom.arrecadacao.pagamento.Pagamento;
import gcom.faturamento.conta.Conta;
import gcom.integracao.webservice.spc.ConsultaWebServiceStub.Endereco;

import java.math.BigDecimal;
import java.util.Collection;

public class RALocalOcorrenciaHelper {

	private Integer idImovel;
	private Collection<Conta> colecaoContas;
	private Collection<Endereco> colecaoEndereco; 
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
	public Collection<Conta> getColecaoContas() {
		return colecaoContas;
	}
	public void setColecaoContas(Collection<Conta> colecaoContas) {
		this.colecaoContas = colecaoContas;
	}
	public Collection<Endereco> getColecaoEndereco() {
		return colecaoEndereco;
	}
	public void setColecaoEndereco(Collection<Endereco> colecaoEndereco) {
		this.colecaoEndereco = colecaoEndereco;
	}
	public String getPontoReferenciaLocalOcorrencia() {
		return pontoReferenciaLocalOcorrencia;
	}
	public void setPontoReferenciaLocalOcorrencia(
			String pontoReferenciaLocalOcorrencia) {
		this.pontoReferenciaLocalOcorrencia = pontoReferenciaLocalOcorrencia;
	}
	public BigDecimal getNnCoordenadaNorte() {
		return nnCoordenadaNorte;
	}
	public void setNnCoordenadaNorte(BigDecimal nnCoordenadaNorte) {
		this.nnCoordenadaNorte = nnCoordenadaNorte;
	}
	public BigDecimal getNnCoordenadaLeste() {
		return nnCoordenadaLeste;
	}
	public void setNnCoordenadaLeste(BigDecimal nnCoordenadaLeste) {
		this.nnCoordenadaLeste = nnCoordenadaLeste;
	}
	public Integer getIdBairroArea() {
		return idBairroArea;
	}
	public void setIdBairroArea(Integer idBairroArea) {
		this.idBairroArea = idBairroArea;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public Integer getIdQuadra() {
		return idQuadra;
	}
	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}
	public Integer getIdDivisaoEsgoto() {
		return idDivisaoEsgoto;
	}
	public void setIdDivisaoEsgoto(Integer idDivisaoEsgoto) {
		this.idDivisaoEsgoto = idDivisaoEsgoto;
	}
	public Integer getIdUnidadeDestino() {
		return idUnidadeDestino;
	}
	public void setIdUnidadeDestino(Integer idUnidadeDestino) {
		this.idUnidadeDestino = idUnidadeDestino;
	}
	public String getParecerUnidadeDestino() {
		return parecerUnidadeDestino;
	}
	public void setParecerUnidadeDestino(String parecerUnidadeDestino) {
		this.parecerUnidadeDestino = parecerUnidadeDestino;
	}
	public Integer getIdLocalOcorrencia() {
		return idLocalOcorrencia;
	}
	public void setIdLocalOcorrencia(Integer idLocalOcorrencia) {
		this.idLocalOcorrencia = idLocalOcorrencia;
	}
	public Integer getIdPavimentoRua() {
		return idPavimentoRua;
	}
	public void setIdPavimentoRua(Integer idPavimentoRua) {
		this.idPavimentoRua = idPavimentoRua;
	}
	public Integer getIdPavimentoCalcada() {
		return idPavimentoCalcada;
	}
	public void setIdPavimentoCalcada(Integer idPavimentoCalcada) {
		this.idPavimentoCalcada = idPavimentoCalcada;
	}
	public String getDescricaoLocalOcorrencia() {
		return descricaoLocalOcorrencia;
	}
	public void setDescricaoLocalOcorrencia(String descricaoLocalOcorrencia) {
		this.descricaoLocalOcorrencia = descricaoLocalOcorrencia;
	}
	public short getIndicCoordenadaSemLogradouro() {
		return indicCoordenadaSemLogradouro;
	}
	public void setIndicCoordenadaSemLogradouro(short indicCoordenadaSemLogradouro) {
		this.indicCoordenadaSemLogradouro = indicCoordenadaSemLogradouro;
	}
	public Collection<Pagamento> getColecaoPagamentos() {
		return colecaoPagamentos;
	}
	public void setColecaoPagamentos(Collection<Pagamento> colecaoPagamentos) {
		this.colecaoPagamentos = colecaoPagamentos;
	}
	public BigDecimal getNnDiametro() {
		return nnDiametro;
	}
	public void setNnDiametro(BigDecimal nnDiametro) {
		this.nnDiametro = nnDiametro;
	}
	
}
