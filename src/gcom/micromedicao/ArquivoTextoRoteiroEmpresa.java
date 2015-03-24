package gcom.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.localidade.Localidade;
import gcom.faturamento.FaturamentoGrupo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ArquivoTextoRoteiroEmpresa implements Serializable {

	private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer anoMesReferencia;
    private Integer codigoSetorComercial1;
    private Integer numeroQuadraInicial1;
    private Integer numeroQuadraFinal1;
    private Integer codigoSetorComercial2;
    private Integer numeroQuadraInicial2;
    private Integer numeroQuadraFinal2;
    private Integer codigoSetorComercial3;
    private Integer numeroQuadraInicial3;
    private Integer numeroQuadraFinal3;
    private Integer quantidadeImovel;
    private String nomeArquivo;
    private String codigoLeiturista;
    private String numeroFoneLeiturista;
    private Date tempoEnvioEmpresa;
    private byte[] arquivoTexto;
    private byte[] arquivoTextoNaoRecebido;
    private Date ultimaAlteracao;
    private Long numeroImei;
    private Integer numeroSequenciaLeitura;
    private String motivoFinalizacao;
    private Integer leiturasRealizas;

    private Localidade localidade;
    private FaturamentoGrupo faturamentoGrupo;
    private Leiturista leiturista;
    private RoteiroEmpresa roteiroEmpresa;
    private Empresa empresa;
    private Rota rota;
    private SituacaoTransmissaoLeitura situacaoTransmissaoLeitura;
    private ServicoTipoCelular servicoTipoCelular;
    

    public final static int ARQUIVO_TEXTO_LIBERADO = 2;
    public final static int ARQUIVO_TEXTO_EM_CAMPO = 3;
    public final static int ARQUIVO_TEXTO_FINALIZADO = 4;
    
    public ArquivoTextoRoteiroEmpresa(Integer id, SituacaoTransmissaoLeitura situacaoTransmissaoLeitura) {
    	this.id = id;
    	this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
    }

    public ArquivoTextoRoteiroEmpresa(Integer id, Integer anoMesReferencia, Integer codigoSetorComercial1, Integer numeroQuadraInicial1, Integer numeroQuadraFinal1, Integer codigoSetorComercial2, Integer numeroQuadraInicial2, Integer numeroQuadraFinal2, Integer codigoSetorComercial3, Integer numeroQuadraInicial3, Integer numeroQuadraFinal3, Integer quantidadeImovel, String nomeArquivo, String codigoLeiturista, String numeroFoneLeiturista, Date tempoEnvioEmpresa, byte[] arquivoTexto, Date ultimaAlteracao, Localidade localidade, FaturamentoGrupo faturamentoGrupo, gcom.micromedicao.Leiturista leiturista, gcom.micromedicao.RoteiroEmpresa roteiroEmpresa, Empresa empresa, SituacaoTransmissaoLeitura situacaoTransmissaoLeitura) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.codigoSetorComercial1 = codigoSetorComercial1;
        this.numeroQuadraInicial1 = numeroQuadraInicial1;
        this.numeroQuadraFinal1 = numeroQuadraFinal1;
        this.codigoSetorComercial2 = codigoSetorComercial2;
        this.numeroQuadraInicial2 = numeroQuadraInicial2;
        this.numeroQuadraFinal2 = numeroQuadraFinal2;
        this.codigoSetorComercial3 = codigoSetorComercial3;
        this.numeroQuadraInicial3 = numeroQuadraInicial3;
        this.numeroQuadraFinal3 = numeroQuadraFinal3;
        this.quantidadeImovel = quantidadeImovel;
        this.nomeArquivo = nomeArquivo;
        this.codigoLeiturista = codigoLeiturista;
        this.numeroFoneLeiturista = numeroFoneLeiturista;
        this.tempoEnvioEmpresa = tempoEnvioEmpresa;
        this.arquivoTexto = arquivoTexto;
        this.ultimaAlteracao = ultimaAlteracao;
        this.localidade = localidade;
        this.faturamentoGrupo = faturamentoGrupo;
        this.leiturista = leiturista;
        this.roteiroEmpresa = roteiroEmpresa;
        this.empresa = empresa;
        this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
    }

    public ArquivoTextoRoteiroEmpresa() {
    }

    public ArquivoTextoRoteiroEmpresa(Integer id, Integer anoMesReferencia, Integer codigoSetorComercial1, Integer numeroQuadraInicial1, Integer numeroQuadraFinal1, Integer quantidadeImovel, String nomeArquivo, String codigoLeiturista, String numeroFoneLeiturista, byte[] arquivoTexto, Date ultimaAlteracao, Localidade localidade, FaturamentoGrupo faturamentoGrupo, gcom.micromedicao.Leiturista leiturista, gcom.micromedicao.RoteiroEmpresa roteiroEmpresa, Empresa empresa, SituacaoTransmissaoLeitura situacaoTransmissaoLeitura) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.codigoSetorComercial1 = codigoSetorComercial1;
        this.numeroQuadraInicial1 = numeroQuadraInicial1;
        this.numeroQuadraFinal1 = numeroQuadraFinal1;
        this.quantidadeImovel = quantidadeImovel;
        this.nomeArquivo = nomeArquivo;
        this.codigoLeiturista = codigoLeiturista;
        this.numeroFoneLeiturista = numeroFoneLeiturista;
        this.arquivoTexto = arquivoTexto;
        this.ultimaAlteracao = ultimaAlteracao;
        this.localidade = localidade;
        this.faturamentoGrupo = faturamentoGrupo;
        this.leiturista = leiturista;
        this.roteiroEmpresa = roteiroEmpresa;
        this.empresa = empresa;
        this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(Integer anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public Integer getCodigoSetorComercial1() {
        return this.codigoSetorComercial1;
    }

    public void setCodigoSetorComercial1(Integer codigoSetorComercial1) {
        this.codigoSetorComercial1 = codigoSetorComercial1;
    }

    public Integer getNumeroQuadraInicial1() {
        return this.numeroQuadraInicial1;
    }

    public void setNumeroQuadraInicial1(Integer numeroQuadraInicial1) {
        this.numeroQuadraInicial1 = numeroQuadraInicial1;
    }

    public Integer getNumeroQuadraFinal1() {
        return this.numeroQuadraFinal1;
    }

    public void setNumeroQuadraFinal1(Integer numeroQuadraFinal1) {
        this.numeroQuadraFinal1 = numeroQuadraFinal1;
    }

    public Integer getCodigoSetorComercial2() {
        return this.codigoSetorComercial2;
    }

    public void setCodigoSetorComercial2(Integer codigoSetorComercial2) {
        this.codigoSetorComercial2 = codigoSetorComercial2;
    }

    public Integer getNumeroQuadraInicial2() {
        return this.numeroQuadraInicial2;
    }

    public void setNumeroQuadraInicial2(Integer numeroQuadraInicial2) {
        this.numeroQuadraInicial2 = numeroQuadraInicial2;
    }

    public Integer getNumeroQuadraFinal2() {
        return this.numeroQuadraFinal2;
    }

    public void setNumeroQuadraFinal2(Integer numeroQuadraFinal2) {
        this.numeroQuadraFinal2 = numeroQuadraFinal2;
    }

    public Integer getCodigoSetorComercial3() {
        return this.codigoSetorComercial3;
    }

    public void setCodigoSetorComercial3(Integer codigoSetorComercial3) {
        this.codigoSetorComercial3 = codigoSetorComercial3;
    }

    public Integer getNumeroQuadraInicial3() {
        return this.numeroQuadraInicial3;
    }

    public void setNumeroQuadraInicial3(Integer numeroQuadraInicial3) {
        this.numeroQuadraInicial3 = numeroQuadraInicial3;
    }

    public Integer getNumeroQuadraFinal3() {
        return this.numeroQuadraFinal3;
    }

    public void setNumeroQuadraFinal3(Integer numeroQuadraFinal3) {
        this.numeroQuadraFinal3 = numeroQuadraFinal3;
    }

    public Integer getQuantidadeImovel() {
        return this.quantidadeImovel;
    }

    public void setQuantidadeImovel(Integer quantidadeImovel) {
        this.quantidadeImovel = quantidadeImovel;
    }

    public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getCodigoLeiturista() {
        return this.codigoLeiturista;
    }

    public void setCodigoLeiturista(String codigoLeiturista) {
        this.codigoLeiturista = codigoLeiturista;
    }

    public String getNumeroFoneLeiturista() {
        return this.numeroFoneLeiturista;
    }

    public void setNumeroFoneLeiturista(String numeroFoneLeiturista) {
        this.numeroFoneLeiturista = numeroFoneLeiturista;
    }

    public Date getTempoEnvioEmpresa() {
        return this.tempoEnvioEmpresa;
    }

    public void setTempoEnvioEmpresa(Date tempoEnvioEmpresa) {
        this.tempoEnvioEmpresa = tempoEnvioEmpresa;
    }

    public byte[] getArquivoTexto() {
        return this.arquivoTexto;
    }

    public void setArquivoTexto(byte[] arquivoTexto) {
        this.arquivoTexto = arquivoTexto;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public FaturamentoGrupo getFaturamentoGrupo() {
        return this.faturamentoGrupo;
    }

    public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
        this.faturamentoGrupo = faturamentoGrupo;
    }

    public gcom.micromedicao.Leiturista getLeiturista() {
        return this.leiturista;
    }

    public void setLeiturista(gcom.micromedicao.Leiturista leiturista) {
        this.leiturista = leiturista;
    }

    public gcom.micromedicao.RoteiroEmpresa getRoteiroEmpresa() {
        return this.roteiroEmpresa;
    }

    public void setRoteiroEmpresa(gcom.micromedicao.RoteiroEmpresa roteiroEmpresa) {
        this.roteiroEmpresa = roteiroEmpresa;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public SituacaoTransmissaoLeitura getSituacaoTransmissaoLeitura() {
        return this.situacaoTransmissaoLeitura;
    }

    public void setSituacaoTransmissaoLeitura(SituacaoTransmissaoLeitura situacaoTransmissaoLeitura) {
        this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Long getNumeroImei() {
		return numeroImei;
	}

	public void setNumeroImei(Long numeroImei) {
		this.numeroImei = numeroImei;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	/**
	 * @return Returns the leituraSequencia.
	 */
	public Integer getNumeroSequenciaLeitura() {
		return numeroSequenciaLeitura;
	}

	/**
	 * @param leituraSequencia The leituraSequencia to set.
	 */
	public void setNumeroSequenciaLeitura(Integer leituraSequencia) {
		this.numeroSequenciaLeitura = leituraSequencia;
	}

	public ServicoTipoCelular getServicoTipoCelular() {
		return servicoTipoCelular;
	}

	public void setServicoTipoCelular(ServicoTipoCelular servicoTipoCelular) {
		this.servicoTipoCelular = servicoTipoCelular;
	}

	public String getMotivoFinalizacao() {
		return motivoFinalizacao;
	}

	public void setMotivoFinalizacao(String motivoFinalizacao) {
		this.motivoFinalizacao = motivoFinalizacao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getLeiturasRealizas() {
		return leiturasRealizas;
	}

	public void setLeiturasRealizas(Integer leiturasRealizas) {
		this.leiturasRealizas = leiturasRealizas;
	}

	public byte[] getArquivoTextoNaoRecebido() {
		return arquivoTextoNaoRecebido;
	}

	public void setArquivoTextoNaoRecebido(byte[] arquivoTextoNaoRecebido) {
		this.arquivoTextoNaoRecebido = arquivoTextoNaoRecebido;
	}


	public boolean isArquivoNovoBatch() {
		return this.arquivoTexto == null;
	}
}
