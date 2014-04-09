package gcom.micromedicao;

import gcom.micromedicao.SituacaoTransmissaoLeitura;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ArquivoTextoRoteiroEmpresaDivisao implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa;

    /** persistent field */
    private Integer quantidadeImovel;
    
    /** persistent field */
    private byte[] arquivoTexto;
    
    /** persistent field */
    private String nomeArquivo;
    
    /** persistent field */
    private Integer numeroSequenciaArquivo;
    
    /** persistent field */
    private SituacaoTransmissaoLeitura situacaoTransmissaoLeitura;
    
    /** persistent field */
    private Long numeroImei;
    
    /** persistent field */
    private gcom.micromedicao.Leiturista leiturista;

    /** persistent field */
    private Date ultimaAlteracao;
    
    /**
     * Description of the Field
     */
    public final static int DISPONIVEL = 1;
    public final static int ARQUIVO_TEXTO_LIBERADO = 2;
    public final static int ARQUIVO_TEXTO_EM_CAMPO = 3;
    public final static int ARQUIVO_TEXTO_FINALIZADO = 4;
    
    public ArquivoTextoRoteiroEmpresaDivisao(Integer id, SituacaoTransmissaoLeitura situacaoTransmissaoLeitura) {
    	this.id = id;
    	this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
    }

    /** full constructor */
    public ArquivoTextoRoteiroEmpresaDivisao(Integer id, 
    										 ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa, 
    										 Integer quantidadeImovel,  
    										 byte[] arquivoTexto, 
    										 String nomeArquivo, 
    										 Integer numeroSequenciaArquivo,
    										 SituacaoTransmissaoLeitura situacaoTransmissaoLeitura,
    										 Date ultimaAlteracao) {
        this.id = id;
        this.arquivoTextoRoteiroEmpresa = arquivoTextoRoteiroEmpresa;
        this.quantidadeImovel = quantidadeImovel;
        this.arquivoTexto = arquivoTexto;
        this.nomeArquivo = nomeArquivo;
        this.numeroSequenciaArquivo = numeroSequenciaArquivo;
        this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public ArquivoTextoRoteiroEmpresaDivisao() {
    }

	public byte[] getArquivoTexto() {
		return arquivoTexto;
	}

	public void setArquivoTexto(byte[] arquivoTexto) {
		this.arquivoTexto = arquivoTexto;
	}

	public ArquivoTextoRoteiroEmpresa getArquivoTextoRoteiroEmpresa() {
		return arquivoTextoRoteiroEmpresa;
	}

	public void setArquivoTextoRoteiroEmpresa(
			ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa) {
		this.arquivoTextoRoteiroEmpresa = arquivoTextoRoteiroEmpresa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Integer getNumeroSequenciaArquivo() {
		return numeroSequenciaArquivo;
	}

	public void setNumeroSequenciaArquivo(Integer numeroSequenciaArquivo) {
		this.numeroSequenciaArquivo = numeroSequenciaArquivo;
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

	public void setSituacaoTransmissaoLeitura(
			SituacaoTransmissaoLeitura situacaoTransmissaoLeitura) {
		this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public gcom.micromedicao.Leiturista getLeiturista() {
		return leiturista;
	}

	public void setLeiturista(gcom.micromedicao.Leiturista leiturista) {
		this.leiturista = leiturista;
	}

	public Long getNumeroImei() {
		return numeroImei;
	}

	public void setNumeroImei(Long numeroImei) {
		this.numeroImei = numeroImei;
	}
    
    
    
}
