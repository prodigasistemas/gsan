package gcom.gerencial.cadastro;import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;import gcom.gerencial.cadastro.cliente.GClienteTipo;import gcom.gerencial.cadastro.cliente.GEsferaPoder;import gcom.gerencial.cadastro.imovel.GCategoria;import gcom.gerencial.cadastro.imovel.GImovelPerfil;import gcom.gerencial.cadastro.imovel.GSubcategoria;import gcom.gerencial.cadastro.localidade.GGerenciaRegional;import gcom.gerencial.cadastro.localidade.GLocalidade;import gcom.gerencial.cadastro.localidade.GQuadra;import gcom.gerencial.cadastro.localidade.GSetorComercial;import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;import gcom.gerencial.micromedicao.GRota;import gcom.gerencial.micromedicao.consumo.GConsumoTipo;import java.io.Serializable;import java.util.Date;/** @author Hibernate CodeGenerator */
//******************************************************************
//Alterado por: Ivan Sérgio
//Data: 19/01/2009
//Alteracao: CRC1012 - Adicionado indicadorLigacaoFaturada.
//******************************************************************
public class UnResumoConsumoAgua implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer recaId;        private int idVolumeExcedente;

    /** persistent field */
    private int referencia;

    /** persistent field */
    private int codigoSetorComercial;

    /** persistent field */
    private int numeroQuadra;

    /** persistent field */
    private int quantidadeEconomias;

    /** persistent field */
    private int consumoAgua;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private int consumoExcedente;

    /** persistent field */
    private int quantidadeLigacoes;
    
    /** persistent field */
    private int volumeConsumoFaturado;
    private int indicadorLigacaoFaturada;
    /** persistent field */
    private GSubcategoria gerSubcategoria;

    /** persistent field */
    private GClienteTipo gerClienteTipo;

    /** persistent field */
    private GLigacaoAguaSituacao gerLigacaoAguaSituacao;

    /** persistent field */
    private GUnidadeNegocio gerUnidadeNegocio;

    /** persistent field */
    private GLocalidade gerLocalidade;

    /** persistent field */
    private GLocalidade gerLocalidadeElo;

    /** persistent field */
    private GQuadra gerQuadra;

    /** persistent field */
    private GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

    /** persistent field */
    private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;

    /** persistent field */
    private GGerenciaRegional gerGerenciaRegional;

    /** persistent field */
    private GSetorComercial gerSetorComercial;

    /** persistent field */
    private GLigacaoAguaPerfil gerLigacaoAguaPerfil;

    /** persistent field */
    private GEsferaPoder gerEsferaPoder;

    /** persistent field */
    private GCategoria gerCategoria;

    /** persistent field */
    private GImovelPerfil gerImovelPerfil;        /** persistent field */    private GConsumoTipo gerConsumoTipo;    

    /** persistent field */
    private GRota gerRota;        private short indicadorConsumoReal;        private short indicadorConsumoMinimo;
    
    private short indicadorHidrometro;        //private short indicadorVolumeExcedente;
    

    protected short getIndicadorHidrometro() {
		return indicadorHidrometro;
	}

	protected void setIndicadorHidrometro(short indicadorHidrometro) {
		this.indicadorHidrometro = indicadorHidrometro;
	}

	/**
	 * @return Retorna o campo indicadorLigacaoFaturada.
	 */
	public int getIndicadorLigacaoFaturada() {
		return indicadorLigacaoFaturada;
	}

	/**
	 * @param indicadorLigacaoFaturada O indicadorLigacaoFaturada a ser setado.
	 */
	public void setIndicadorLigacaoFaturada(int indicadorLigacaoFaturada) {
		this.indicadorLigacaoFaturada = indicadorLigacaoFaturada;
	}

	/** full constructor */
    public UnResumoConsumoAgua(    		int referencia,     		GGerenciaRegional gGerenciaRegional,     		GUnidadeNegocio gUnidadeNegocio,    		GLocalidade gLocalidade,    		GLocalidade gLocalidadeElo,    		GSetorComercial gSetorComercial,    		GRota gRota,    		    		GQuadra gQuadra,    		    		int codigoSetorComercial,     		int numeroQuadra,    		GImovelPerfil gImovelPerfil,    		GEsferaPoder gEsferaPoder,    		GClienteTipo gClienteTipo,    		GLigacaoAguaSituacao gLigacaoAguaSituacao,    		     		GLigacaoEsgotoSituacao gLigacaoEsgotoSituacao,    		GCategoria gCategoria,    		GSubcategoria gSubcategoria,    		GLigacaoAguaPerfil gLigacaoAguaPerfil,     		GLigacaoEsgotoPerfil gLigacaoEsgotoPerfil,    		GConsumoTipo gConsumoTipo,    		int consumoAgua,    		int quantidadeLigacoes,    		int quantidadeEconomias,    		int consumoExcedente ){ 	    		    	    	
		this.referencia = referencia; 		this.gerGerenciaRegional = gGerenciaRegional; 		this.gerUnidadeNegocio = gUnidadeNegocio; 		this.gerLocalidade = gLocalidade;		this.gerLocalidadeElo = gLocalidadeElo;		this.gerSetorComercial = gSetorComercial;		this.gerRota = gRota;		this.gerLocalidadeElo = gLocalidadeElo;		this.gerQuadra = gQuadra;    				this.codigoSetorComercial = codigoSetorComercial; 		this.numeroQuadra = numeroQuadra;		this.gerImovelPerfil = gImovelPerfil;		this.gerEsferaPoder = gEsferaPoder;		this.gerClienteTipo = gClienteTipo;		this.gerLigacaoAguaSituacao = gLigacaoAguaSituacao;    		 		this.gerLigacaoEsgotoSituacao = gLigacaoEsgotoSituacao;		this.gerCategoria = gCategoria;		this.gerSubcategoria = gSubcategoria;		this.gerLigacaoAguaPerfil = gLigacaoAguaPerfil; 		this.gerLigacaoEsgotoPerfil = gLigacaoEsgotoPerfil;		this.gerConsumoTipo = gConsumoTipo;		this.consumoAgua = consumoAgua;		this.quantidadeLigacoes = quantidadeLigacoes;		this.quantidadeEconomias = quantidadeEconomias;		this.consumoExcedente = consumoExcedente;		this.ultimaAlteracao = new Date();    }
    /** default constructor */
    public UnResumoConsumoAgua() {
    }

	public int getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(int codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public int getConsumoAgua() {
		return consumoAgua;
	}

	public void setConsumoAgua(int consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	public int getConsumoExcedente() {
		return consumoExcedente;
	}

	public void setConsumoExcedente(int consumoExcedente) {
		this.consumoExcedente = consumoExcedente;
	}

	public GCategoria getGerCategoria() {
		return gerCategoria;
	}

	public void setGerCategoria(GCategoria gerCategoria) {
		this.gerCategoria = gerCategoria;
	}

	public GClienteTipo getGerClienteTipo() {
		return gerClienteTipo;
	}

	public void setGerClienteTipo(GClienteTipo gerClienteTipo) {
		this.gerClienteTipo = gerClienteTipo;
	}

	public GEsferaPoder getGerEsferaPoder() {
		return gerEsferaPoder;
	}

	public void setGerEsferaPoder(GEsferaPoder gerEsferaPoder) {
		this.gerEsferaPoder = gerEsferaPoder;
	}

	public GGerenciaRegional getGerGerenciaRegional() {
		return gerGerenciaRegional;
	}

	public void setGerGerenciaRegional(GGerenciaRegional gerGerenciaRegional) {
		this.gerGerenciaRegional = gerGerenciaRegional;
	}

	public GImovelPerfil getGerImovelPerfil() {
		return gerImovelPerfil;
	}

	public void setGerImovelPerfil(GImovelPerfil gerImovelPerfil) {
		this.gerImovelPerfil = gerImovelPerfil;
	}

	public GLigacaoAguaPerfil getGerLigacaoAguaPerfil() {
		return gerLigacaoAguaPerfil;
	}

	public void setGerLigacaoAguaPerfil(GLigacaoAguaPerfil gerLigacaoAguaPerfil) {
		this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
	}

	public GLigacaoAguaSituacao getGerLigacaoAguaSituacao() {
		return gerLigacaoAguaSituacao;
	}

	public void setGerLigacaoAguaSituacao(
			GLigacaoAguaSituacao gerLigacaoAguaSituacao) {
		this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
	}

	public GLigacaoEsgotoPerfil getGerLigacaoEsgotoPerfil() {
		return gerLigacaoEsgotoPerfil;
	}

	public void setGerLigacaoEsgotoPerfil(
			GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
		this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
	}

	public GLigacaoEsgotoSituacao getGerLigacaoEsgotoSituacao() {
		return gerLigacaoEsgotoSituacao;
	}

	public void setGerLigacaoEsgotoSituacao(
			GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao) {
		this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
	}

	public GLocalidade getGerLocalidade() {
		return gerLocalidade;
	}

	public void setGerLocalidade(GLocalidade gerLocalidade) {
		this.gerLocalidade = gerLocalidade;
	}

	public GLocalidade getGerLocalidadeElo() {
		return gerLocalidadeElo;
	}

	public void setGerLocalidadeElo(GLocalidade gerLocalidadeElo) {
		this.gerLocalidadeElo = gerLocalidadeElo;
	}

	public GQuadra getGerQuadra() {
		return gerQuadra;
	}

	public void setGerQuadra(GQuadra gerQuadra) {
		this.gerQuadra = gerQuadra;
	}

	public GRota getGerRota() {
		return gerRota;
	}

	public void setGerRota(GRota gerRota) {
		this.gerRota = gerRota;
	}

	public GSetorComercial getGerSetorComercial() {
		return gerSetorComercial;
	}

	public void setGerSetorComercial(GSetorComercial gerSetorComercial) {
		this.gerSetorComercial = gerSetorComercial;
	}

	public GSubcategoria getGerSubcategoria() {
		return gerSubcategoria;
	}

	public void setGerSubcategoria(GSubcategoria gerSubcategoria) {
		this.gerSubcategoria = gerSubcategoria;
	}

	public GUnidadeNegocio getGerUnidadeNegocio() {
		return gerUnidadeNegocio;
	}

	public void setGerUnidadeNegocio(GUnidadeNegocio gerUnidadeNegocio) {
		this.gerUnidadeNegocio = gerUnidadeNegocio;
	}

	public int getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public int getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(int quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public int getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}

	public void setQuantidadeLigacoes(int quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}

	public Integer getRecaId() {
		return recaId;
	}

	public void setRecaId(Integer recaId) {
		this.recaId = recaId;
	}

	public int getReferencia() {
		return referencia;
	}

	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}	public short getIndicadorConsumoMinimo() {		return indicadorConsumoMinimo;	}	public void setIndicadorConsumoMinimo(short indicadorConsumoMinimo) {		this.indicadorConsumoMinimo = indicadorConsumoMinimo;	}	public short getIndicadorConsumoReal() {		return indicadorConsumoReal;	}	public void setIndicadorConsumoReal(short indicadorConsumoReal) {		this.indicadorConsumoReal = indicadorConsumoReal;	}	public GConsumoTipo getGerConsumoTipo() {		return gerConsumoTipo;	}	public void setGerConsumoTipo(GConsumoTipo gerConsumoTipo) {		this.gerConsumoTipo = gerConsumoTipo;	}	public int getIdVolumeExcedente() {		return idVolumeExcedente;	}	public void setIdVolumeExcedente(int idVolumeExcedente) {		this.idVolumeExcedente = idVolumeExcedente;	}
	public int getVolumeConsumoFaturado() {
		return volumeConsumoFaturado;
	}
	public void setVolumeConsumoFaturado(int volumeConsumoFaturado) {
		this.volumeConsumoFaturado = volumeConsumoFaturado;
	}
}
