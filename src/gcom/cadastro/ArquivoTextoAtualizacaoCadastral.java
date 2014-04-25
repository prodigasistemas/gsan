package gcom.cadastro;

import gcom.cadastro.localidade.Localidade;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.Rota;
import gcom.micromedicao.SituacaoTransmissaoLeitura;

import java.io.Serializable;
import java.util.Date;

public class ArquivoTextoAtualizacaoCadastral implements Serializable {

	private static final long serialVersionUID = 1L;

    private Integer id;
    
    private String descricaoArquivo;

    private Integer codigoSetorComercial;

    private Integer numeroQuadraInicial;

    private Integer numeroQuadraFinal;
    
    private Rota rota;

    private Integer quantidadeImovel;

    private byte[] arquivoTexto;

    private Date ultimaAlteracao;

    private Localidade localidade;

    private gcom.micromedicao.Leiturista leiturista;

    private SituacaoTransmissaoLeitura situacaoTransmissaoLeitura;
    
    private Integer quantidadeImoveisTransmitidos;

	/**
	 * @return Retorna o campo arquivoTexto.
	 */
	public byte[] getArquivoTexto() {
		return arquivoTexto;
	}

	/**
	 * @param arquivoTexto O arquivoTexto a ser setado.
	 */
	public void setArquivoTexto(byte[] arquivoTexto) {
		this.arquivoTexto = arquivoTexto;
	}

	/**
	 * @return Retorna o campo codigoRota.
	 */
	public Rota getRota() {
		return rota;
	}

	/**
	 * @param codigoRota O codigoRota a ser setado.
	 */
	public void setRota(Rota rota) {
		this.rota = rota;
	}

	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	/**
	 * @return Retorna o campo descricaoArquivo.
	 */
	public String getDescricaoArquivo() {
		return descricaoArquivo;
	}

	/**
	 * @param descricaoArquivo O descricaoArquivo a ser setado.
	 */
	public void setDescricaoArquivo(String descricaoArquivo) {
		this.descricaoArquivo = descricaoArquivo;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo leiturista.
	 */
	public gcom.micromedicao.Leiturista getLeiturista() {
		return leiturista;
	}

	/**
	 * @param leiturista O leiturista a ser setado.
	 */
	public void setLeiturista(gcom.micromedicao.Leiturista leiturista) {
		this.leiturista = leiturista;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public Localidade getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo numeroQuadraFinal.
	 */
	public Integer getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}

	/**
	 * @param numeroQuadraFinal O numeroQuadraFinal a ser setado.
	 */
	public void setNumeroQuadraFinal(Integer numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}

	/**
	 * @return Retorna o campo numeroQuadraInicial.
	 */
	public Integer getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}

	/**
	 * @param numeroQuadraInicial O numeroQuadraInicial a ser setado.
	 */
	public void setNumeroQuadraInicial(Integer numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}

	/**
	 * @return Retorna o campo quantidadeImovel.
	 */
	public Integer getQuantidadeImovel() {
		return quantidadeImovel;
	}

	/**
	 * @param quantidadeImovel O quantidadeImovel a ser setado.
	 */
	public void setQuantidadeImovel(Integer quantidadeImovel) {
		this.quantidadeImovel = quantidadeImovel;
	}

	/**
	 * @return Retorna o campo situacaoTransmissaoLeitura.
	 */
	public SituacaoTransmissaoLeitura getSituacaoTransmissaoLeitura() {
		return situacaoTransmissaoLeitura;
	}

	/**
	 * @param situacaoTransmissaoLeitura O situacaoTransmissaoLeitura a ser setado.
	 */
	public void setSituacaoTransmissaoLeitura(
			SituacaoTransmissaoLeitura situacaoTransmissaoLeitura) {
		this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getQuantidadeImoveisTransmitidos() {
		return quantidadeImoveisTransmitidos;
	}

	public void setQuantidadeImoveisTransmitidos(Integer quantidadeImoveisTransmitidos) {
		this.quantidadeImoveisTransmitidos = quantidadeImoveisTransmitidos;
	}

	public ArquivoTextoAtualizacaoCadastral() {
	}
	
	public ArquivoTextoAtualizacaoCadastral(Integer id, String descricaoArquivo, Integer codigoSetorComercial, 
			Integer numeroQuadraInicial, Integer numeroQuadraFinal, Rota rota, Integer quantidadeImovel,
			byte[] arquivoTexto, Date ultimaAlteracao, Localidade localidade, Leiturista leiturista, 
			SituacaoTransmissaoLeitura situacaoTransmissaoLeitura, Integer quantidadeImoveisTransmitidos) {
		this.id = id;
		this.descricaoArquivo = descricaoArquivo;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadraInicial = numeroQuadraInicial;
		this.numeroQuadraFinal = numeroQuadraFinal;
		this.rota = rota;
		this.quantidadeImovel = quantidadeImovel;
		this.arquivoTexto = arquivoTexto;
		this.ultimaAlteracao = ultimaAlteracao;
		this.localidade = localidade;
		this.leiturista = leiturista;
		this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
		this.quantidadeImoveisTransmitidos = quantidadeImoveisTransmitidos;
	}
   
    
}
