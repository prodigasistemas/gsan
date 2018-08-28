package gcom.cadastro;

import gcom.cadastro.localidade.Localidade;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.Rota;
import gcom.micromedicao.SituacaoTransmissaoLeitura;

import java.io.Serializable;
import java.util.Date;

public class ArquivoTextoAtualizacaoCadastral implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String EXIBIR_EM_REVISAO = "1";
	
	public static final String TIPO_ARQUIVO_TRANSMISSAO = " ";
	public static final String TIPO_ARQUIVO_REVISAO = "R";
	public static final String TIPO_ARQUIVO_FISCALIZACAO = "F";
	public static final String TIPO_ARQUIVO_REVISITA = "V";

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
	
	private String tipoRetorno;

	public ArquivoTextoAtualizacaoCadastral(Integer id, String descricaoArquivo, Integer codigoSetorComercial, Integer numeroQuadraInicial, Integer numeroQuadraFinal, Rota rota, Integer quantidadeImovel,
			byte[] arquivoTexto, Date ultimaAlteracao, Localidade localidade, Leiturista leiturista, SituacaoTransmissaoLeitura situacaoTransmissaoLeitura, Integer quantidadeImoveisTransmitidos) {
		
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
	
	public byte[] getArquivoTexto() {
		return arquivoTexto;
	}

	public void setArquivoTexto(byte[] arquivoTexto) {
		this.arquivoTexto = arquivoTexto;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getDescricaoArquivo() {
		return descricaoArquivo;
	}

	public void setDescricaoArquivo(String descricaoArquivo) {
		this.descricaoArquivo = descricaoArquivo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public gcom.micromedicao.Leiturista getLeiturista() {
		return leiturista;
	}

	public void setLeiturista(gcom.micromedicao.Leiturista leiturista) {
		this.leiturista = leiturista;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Integer getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}

	public void setNumeroQuadraFinal(Integer numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}

	public Integer getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}

	public void setNumeroQuadraInicial(Integer numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}

	public Integer getQuantidadeImovel() {
		return quantidadeImovel;
	}

	public void setQuantidadeImovel(Integer quantidadeImovel) {
		this.quantidadeImovel = quantidadeImovel;
	}

	public SituacaoTransmissaoLeitura getSituacaoTransmissaoLeitura() {
		return situacaoTransmissaoLeitura;
	}

	public void setSituacaoTransmissaoLeitura(SituacaoTransmissaoLeitura situacaoTransmissaoLeitura) {
		this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

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

	public String getTipoRetorno() {
		return tipoRetorno;
	}

	public void setTipoRetorno(String tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}
	
	public boolean isArquivoRetornoTransmissao() {
		return tipoRetorno.equals(null) || tipoRetorno.equals(ArquivoTextoAtualizacaoCadastral.TIPO_ARQUIVO_TRANSMISSAO);
	}
	
	public boolean isArquivoRetornoRevisao() {
		return !tipoRetorno.equals(null) && tipoRetorno.equals(ArquivoTextoAtualizacaoCadastral.TIPO_ARQUIVO_REVISAO);
	}
	
	public boolean isArquivoRetornoFiscalizacao() {
		return !tipoRetorno.equals(null) && tipoRetorno.equals(ArquivoTextoAtualizacaoCadastral.TIPO_ARQUIVO_FISCALIZACAO);
	}
	
	public boolean isArquivoRetornoRevisita() {
		return tipoRetorno.equals(null) || tipoRetorno.equals(ArquivoTextoAtualizacaoCadastral.TIPO_ARQUIVO_REVISITA);
	}
}
