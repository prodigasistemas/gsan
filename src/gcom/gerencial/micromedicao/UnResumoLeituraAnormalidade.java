package gcom.gerencial.micromedicao;

import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.cadastro.GEmpresa;
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import gcom.gerencial.cadastro.localidade.GGerenciaRegional;
import gcom.gerencial.cadastro.localidade.GLocalidade;
import gcom.gerencial.cadastro.localidade.GQuadra;
import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import gcom.gerencial.faturamento.GConsumoTarifa;
import gcom.gerencial.faturamento.GFaturamentoGrupo;
import gcom.gerencial.micromedicao.leitura.GLeituraSituacao;
import gcom.gerencial.micromedicao.medicao.GMedicaoTipo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoLeituraAnormalidade implements Serializable {

	
	private static final long serialVersionUID = 1L;

	
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int referencia;

    /** persistent field */
    private int setorcomercial;

    /** persistent field */
    private int quadra;

    /** persistent field */
    private int quantidadeLeituras;

    /** persistent field */
    private int idLeituraAnormalidade;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private GGerenciaRegional gerGerenciaRegional;

    /** persistent field */
    private GSetorComercial gerSetorComercial;

    /** persistent field */
    private GSubcategoria gerSubcategoria;

    /** persistent field */
    private GLigacaoAguaPerfil gerLigacaoAguaPerfil;

    /** persistent field */
    private GEsferaPoder gerEsferaPoder;

    /** persistent field */
    private GClienteTipo gerClienteTipo;

    /** persistent field */
    private GUnidadeNegocio gerUnidadeNegocio;

    /** persistent field */
    private GLigacaoAguaSituacao gerLigacaoAguaSituacao;

    /** persistent field */
    private GMedicaoTipo gerMedicaoTipo;

    /** persistent field */
    private GCategoria gerCategoria;

    /** persistent field */
    private GLocalidade gerLocalidadeElo;

    /** persistent field */
    private GLocalidade gerLocalidade;

    /** persistent field */
    private GImovelPerfil gerImovelPerfil;

    /** persistent field */
    private gcom.gerencial.micromedicao.GRota gerRota;

    /** persistent field */
    private GQuadra gerQuadra;

    /** persistent field */
    private GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

    /** persistent field */
    private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;
    
    /** persistent field */
    private GEmpresa gerEmpresa;
    
    /** persistent field */
    private GLeituraSituacao gerLeituraSituacao;
    
    private GConsumoTarifa gerConsumoTarifa;
    
    private GFaturamentoGrupo gerFaturamentoGrupo;
    
    // CRC719
    private int idLeituraAnormalidadeInformada;
    private int quantidadeLeiturasInformada;

    /** full constructor */
    public UnResumoLeituraAnormalidade(
    		int referencia, 
    		GGerenciaRegional gerGerenciaRegional,
    		GUnidadeNegocio gerUnidadeNegocio, 
    		GLocalidade gerLocalidadeElo, 
    		GLocalidade gerLocalidade,
    		int setorcomercial, 
    		gcom.gerencial.micromedicao.GRota gerRota,
    		int quadra, 
    		GImovelPerfil gerImovelPerfil,
    		GEsferaPoder gerEsferaPoder,
    		GClienteTipo gerClienteTipo,
    		GLigacaoAguaSituacao gerLigacaoAguaSituacao,
    		GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao,
    		GCategoria gerCategoria,
    		GSubcategoria gerSubcategoria,
    		GLigacaoAguaPerfil gerLigacaoAguaPerfil,
    		GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil,
    		GMedicaoTipo gerMedicaoTipo,
    		int idLeituraAnormalidade,
    		GSetorComercial gerSetorComercial,
    		GQuadra gerQuadra,
    		int quantidadeLeituras,
    		GEmpresa gerEmpresa,
    		GLeituraSituacao gerLeituraSituacao) {
    	
    	this.referencia = referencia;
    	this.gerGerenciaRegional = gerGerenciaRegional;
    	this.gerUnidadeNegocio = gerUnidadeNegocio;
    	this.gerLocalidadeElo = gerLocalidadeElo;
    	this.gerLocalidade = gerLocalidade;
    	this.setorcomercial = setorcomercial;
    	this.gerRota = gerRota;
    	this.quadra = quadra;
    	this.gerImovelPerfil = gerImovelPerfil;
    	this.gerEsferaPoder = gerEsferaPoder;
    	this.gerClienteTipo = gerClienteTipo;
    	this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
    	this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
    	this.gerCategoria = gerCategoria;
    	this.gerSubcategoria = gerSubcategoria;
    	this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
    	this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    	this.gerMedicaoTipo = gerMedicaoTipo;
    	this.idLeituraAnormalidade = idLeituraAnormalidade;
    	this.gerSetorComercial = gerSetorComercial;
    	this.gerQuadra = gerQuadra;
    	this.quantidadeLeituras = quantidadeLeituras;
    	this.gerEmpresa = gerEmpresa;
    	this.gerLeituraSituacao = gerLeituraSituacao;
    	this.ultimaAlteracao = new Date();
    }

    /** default constructor */
    public UnResumoLeituraAnormalidade() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getReferencia() {
        return this.referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public int getSetorcomercial() {
        return this.setorcomercial;
    }

    public void setSetorcomercial(int setorcomercial) {
        this.setorcomercial = setorcomercial;
    }

    public int getQuadra() {
        return this.quadra;
    }

    public void setQuadra(int quadra) {
        this.quadra = quadra;
    }

    public int getQuantidadeLeituras() {
        return this.quantidadeLeituras;
    }

    public void setQuantidadeLeituras(int quantidadeLeituras) {
        this.quantidadeLeituras = quantidadeLeituras;
    }

    public int getIdLeituraAnormalidade() {
        return this.idLeituraAnormalidade;
    }

    public void setIdLeituraAnormalidade(int idLeituraAnormalidade) {
        this.idLeituraAnormalidade = idLeituraAnormalidade;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public GGerenciaRegional getGerGerenciaRegional() {
        return this.gerGerenciaRegional;
    }

    public void setGerGerenciaRegional(GGerenciaRegional gerGerenciaRegional) {
        this.gerGerenciaRegional = gerGerenciaRegional;
    }

    public GSetorComercial getGerSetorComercial() {
        return this.gerSetorComercial;
    }

    public void setGerSetorComercial(GSetorComercial gerSetorComercial) {
        this.gerSetorComercial = gerSetorComercial;
    }

    public GSubcategoria getGerSubcategoria() {
        return this.gerSubcategoria;
    }

    public void setGerSubcategoria(GSubcategoria gerSubcategoria) {
        this.gerSubcategoria = gerSubcategoria;
    }

    public GLigacaoAguaPerfil getGerLigacaoAguaPerfil() {
        return this.gerLigacaoAguaPerfil;
    }

    public void setGerLigacaoAguaPerfil(GLigacaoAguaPerfil gerLigacaoAguaPerfil) {
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
    }

    public GEsferaPoder getGerEsferaPoder() {
        return this.gerEsferaPoder;
    }

    public void setGerEsferaPoder(GEsferaPoder gerEsferaPoder) {
        this.gerEsferaPoder = gerEsferaPoder;
    }

    public GClienteTipo getGerClienteTipo() {
        return this.gerClienteTipo;
    }

    public void setGerClienteTipo(GClienteTipo gerClienteTipo) {
        this.gerClienteTipo = gerClienteTipo;
    }

    public GUnidadeNegocio getGerUnidadeNegocio() {
        return this.gerUnidadeNegocio;
    }

    public void setGerUnidadeNegocio(GUnidadeNegocio gerUnidadeNegocio) {
        this.gerUnidadeNegocio = gerUnidadeNegocio;
    }

    public GLigacaoAguaSituacao getGerLigacaoAguaSituacao() {
        return this.gerLigacaoAguaSituacao;
    }

    public void setGerLigacaoAguaSituacao(GLigacaoAguaSituacao gerLigacaoAguaSituacao) {
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
    }

    public GMedicaoTipo getGerMedicaoTipo() {
        return this.gerMedicaoTipo;
    }

    public void setGerMedicaoTipo(GMedicaoTipo gerMedicaoTipo) {
        this.gerMedicaoTipo = gerMedicaoTipo;
    }

    public GCategoria getGerCategoria() {
        return this.gerCategoria;
    }

    public void setGerCategoria(GCategoria gerCategoria) {
        this.gerCategoria = gerCategoria;
    }

    public GLocalidade getGerLocalidadeElo() {
        return this.gerLocalidadeElo;
    }

    public void setGerLocalidadeElo(GLocalidade gerLocalidadeElo) {
        this.gerLocalidadeElo = gerLocalidadeElo;
    }

    public GLocalidade getGerLocalidade() {
        return this.gerLocalidade;
    }

    public void setGerLocalidade(GLocalidade gerLocalidade) {
        this.gerLocalidade = gerLocalidade;
    }

    public GImovelPerfil getGerImovelPerfil() {
        return this.gerImovelPerfil;
    }

    public void setGerImovelPerfil(GImovelPerfil gerImovelPerfil) {
        this.gerImovelPerfil = gerImovelPerfil;
    }

    public gcom.gerencial.micromedicao.GRota getGerRota() {
        return this.gerRota;
    }

    public void setGerRota(gcom.gerencial.micromedicao.GRota gerRota) {
        this.gerRota = gerRota;
    }

    public GQuadra getGerQuadra() {
        return this.gerQuadra;
    }

    public void setGerQuadra(GQuadra gerQuadra) {
        this.gerQuadra = gerQuadra;
    }

    public GLigacaoEsgotoSituacao getGerLigacaoEsgotoSituacao() {
        return this.gerLigacaoEsgotoSituacao;
    }

    public void setGerLigacaoEsgotoSituacao(GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao) {
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
    }

    public GLigacaoEsgotoPerfil getGerLigacaoEsgotoPerfil() {
        return this.gerLigacaoEsgotoPerfil;
    }

    public void setGerLigacaoEsgotoPerfil(GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public GEmpresa getGerEmpresa() {
		return gerEmpresa;
	}

	public void setGerEmpresa(GEmpresa gerEmpresa) {
		this.gerEmpresa = gerEmpresa;
	}

	public GLeituraSituacao getGerLeituraSituacao() {
		return gerLeituraSituacao;
	}

	public void setGerLeituraSituacao(GLeituraSituacao gerLeituraSituacao) {
		this.gerLeituraSituacao = gerLeituraSituacao;
	}

	public GConsumoTarifa getGerConsumoTarifa() {
		return gerConsumoTarifa;
	}

	public void setGerConsumoTarifa(GConsumoTarifa gerConsumoTarifa) {
		this.gerConsumoTarifa = gerConsumoTarifa;
	}

	public GFaturamentoGrupo getGerFaturamentoGrupo() {
		return gerFaturamentoGrupo;
	}

	public void setGerFaturamentoGrupo(GFaturamentoGrupo gerFaturamentoGrupo) {
		this.gerFaturamentoGrupo = gerFaturamentoGrupo;
	}

	/**
	 * @return Retorna o campo idLeituraAnormalidadeInformada.
	 */
	public int getIdLeituraAnormalidadeInformada() {
		return idLeituraAnormalidadeInformada;
	}

	/**
	 * @param idLeituraAnormalidadeInformada O idLeituraAnormalidadeInformada a ser setado.
	 */
	public void setIdLeituraAnormalidadeInformada(int idLeituraAnormalidadeInformada) {
		this.idLeituraAnormalidadeInformada = idLeituraAnormalidadeInformada;
	}

	/**
	 * @return Retorna o campo quantidadeLeiturasInformada.
	 */
	public int getQuantidadeLeiturasInformada() {
		return quantidadeLeiturasInformada;
	}

	/**
	 * @param quantidadeLeiturasInformada O quantidadeLeiturasInformada a ser setado.
	 */
	public void setQuantidadeLeiturasInformada(int quantidadeLeiturasInformada) {
		this.quantidadeLeiturasInformada = quantidadeLeiturasInformada;
	}

}
