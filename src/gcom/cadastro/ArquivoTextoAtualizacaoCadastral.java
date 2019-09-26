package gcom.cadastro;

import gcom.cadastro.localidade.Localidade;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.Rota;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.util.Conversivel;

import java.io.Serializable;
import java.util.Date;

public class ArquivoTextoAtualizacaoCadastral implements Serializable, Conversivel {

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
	
	private Integer quantidadeEmCampo;
	
	private Integer quantidadeRevisita;
	
	private Integer quantidadeRevisao;
	
	private Integer quantidadeEmFiscalizacao;
	
	private Integer quantidadeDisponivel;

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
		if (quantidadeImovel == null) {
			return 0;
		}
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
		if (quantidadeImoveisTransmitidos == null) {
			return 0;
		}
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
	
	public Integer getQuantidadeEmCampo() {
		return quantidadeEmCampo;
	}

	public void setQuantidadeEmCampo(Integer quantidadeEmCampo) {
		this.quantidadeEmCampo = quantidadeEmCampo;
	}

	public Integer getQuantidadeRevisita() {
		if (quantidadeRevisita == null) {
			return 0;
		}
		return quantidadeRevisita;
	}

	public void setQuantidadeRevisita(Integer quantidadeRevisita) {
		this.quantidadeRevisita = quantidadeRevisita;
	}

	public Integer getQuantidadeRevisao() {
		if (quantidadeRevisao == null) {
			return 0;
		}
		return quantidadeRevisao;
	}

	public void setQuantidadeRevisao(Integer quantidadeRevisao) {
		this.quantidadeRevisao = quantidadeRevisao;
	}

	public Integer getQuantidadeEmFiscalizacao() {
		if (quantidadeEmFiscalizacao == null) {
			return 0;
		}
		return quantidadeEmFiscalizacao;
	}

	public void setQuantidadeEmFiscalizacao(Integer quantidadeEmFiscalizacao) {
		this.quantidadeEmFiscalizacao = quantidadeEmFiscalizacao;
	}
	
	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
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
	
	public String getDescricaoTipoRetorno() {
		String descricao = "";
		if (!tipoRetorno.equals(null)) 
			if (tipoRetorno.equals(ArquivoTextoAtualizacaoCadastral.TIPO_ARQUIVO_REVISITA))
					descricao = "Revisita";
			else if (tipoRetorno.equals(ArquivoTextoAtualizacaoCadastral.TIPO_ARQUIVO_FISCALIZACAO))
					descricao = "Fiscalização";
			else if (tipoRetorno.equals(ArquivoTextoAtualizacaoCadastral.TIPO_ARQUIVO_REVISAO))
				descricao = "Revisão";
			else if (tipoRetorno.equals(ArquivoTextoAtualizacaoCadastral.TIPO_ARQUIVO_TRANSMISSAO))
				descricao = "Transmissão";
			
		return descricao;
	}
	
	public Integer getQuantidadeImoveisSemBloqueados() {
			if (getQuantidadeEmCampo() > 0 ) {
				return getQuantidadeEmCampo() + getQuantidadeImoveisTransmitidos();
				}else {
				return getQuantidadeDisponivel();
				}
		}
}
