package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.micromedicao.Rota;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OsSeletivaComando implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private Date tempoComando;

    /** nullable persistent field */
    private Date tempoRealizacao;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Short quantidadeMaximaOrdens;

    /** nullable persistent field */
    private Integer codigoElo;

    /** nullable persistent field */
    private Integer codigoSetorComercialInicial;

    /** nullable persistent field */
    private Integer codigoSetorComercialFinal;

    /** nullable persistent field */
    private Integer numeroQuadraInicial;

    /** nullable persistent field */
    private Integer numeroQuadraFinal;

    /** nullable persistent field */
    private Short indicadorImovelCondominio;

    /** nullable persistent field */
    private Integer anoMesInstalacaoHidrometro;

    /** nullable persistent field */
    private Short quantidadeGerada;

    /** nullable persistent field */
    private Integer numeroConsumoMedio;

    /** nullable persistent field */
    private Integer numeroConsumoEconomia;

    /** nullable persistent field */
    private Short quantidadeEconomiasInicial;

    /** nullable persistent field */
    private Short quantidadeEconomiasFinal;

    /** nullable persistent field */
    private Short quantidadeDocumentosInicial;

    /** nullable persistent field */
    private Short quantidadeDocumentosFinal;

    /** nullable persistent field */
    private Short quantidadeOcorrenciasConsecutivas;

    /** nullable persistent field */
    private BigDecimal numeroAreaConstruidaInicial;

    /** nullable persistent field */
    private BigDecimal numeroAreaConstruidaFinal;

    /** nullable persistent field */
    private Short numeroMoradoresInicial;

    /** nullable persistent field */
    private Short numeroMoradoresFinal;

    /** persistent field */
    private LeituraAnormalidade leituraAnormalidade;

    /** persistent field */
    private Localidade localidadeFinal;

    /** persistent field */
    private Localidade localidadeInicial;

    /** persistent field */
    private Quadra quadraInicial;

    /** persistent field */
    private Quadra quadraFinal;

    /** persistent field */
    private ImovelPerfil imovelPerfil;

    /** persistent field */
    private HidrometroMarca hidrometroMarca;

    /** persistent field */
    private Rota rotaFinal;

    /** persistent field */
    private Rota rotaInicial;

    /** persistent field */
    private SetorComercial setorComercialFinal;

    /** persistent field */
    private SetorComercial setorComercialInicial;

    /** persistent field */
    private Usuario usuario;

    /** persistent field */
    private Empresa empresa;

    /** persistent field */
    private Categoria categoria;

    /** persistent field */
    private HidrometroCapacidade hidrometroCapacidade;

    /** persistent field */
    private MedicaoTipo medicaoTipo;

    /** persistent field */
    private Subcategoria subcategoria;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo;

    /** full constructor */
    public OsSeletivaComando(Integer id, Date tempoComando, Date tempoRealizacao, Date ultimaAlteracao, Short quantidadeMaximaOrdens, Integer codigoElo, Integer codigoSetorComercialInicial, Integer codigoSetorComercialFinal, Integer numeroQuadraInicial, Integer numeroQuadraFinal, Short indicadorImovelCondominio, Integer anoMesInstalacaoHidrometro, Short quantidadeGerada, Integer numeroConsumoMedio, Integer numeroConsumoEconomia, Short quantidadeEconomiasInicial, Short quantidadeEconomiasFinal, Short quantidadeDocumentosInicial, Short quantidadeDocumentosFinal, Short quantidadeOcorrenciasConsecutivas, BigDecimal numeroAreaConstruidaInicial, BigDecimal numeroAreaConstruidaFinal, Short numeroMoradoresInicial, Short numeroMoradoresFinal, LeituraAnormalidade leituraAnormalidade, Localidade localidadeFinal, Localidade localidadeInicial, Quadra quadraInicial, Quadra quadraFinal, ImovelPerfil imovelPerfil, HidrometroMarca hidrometroMarca, Rota rotaFinal, Rota rotaInicial, SetorComercial setorComercialFinal, SetorComercial setorComercialInicial, Usuario usuario, Empresa empresa, Categoria categoria, HidrometroCapacidade hidrometroCapacidade, MedicaoTipo medicaoTipo, Subcategoria subcategoria, gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
        this.id = id;
        this.tempoComando = tempoComando;
        this.tempoRealizacao = tempoRealizacao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.quantidadeMaximaOrdens = quantidadeMaximaOrdens;
        this.codigoElo = codigoElo;
        this.codigoSetorComercialInicial = codigoSetorComercialInicial;
        this.codigoSetorComercialFinal = codigoSetorComercialFinal;
        this.numeroQuadraInicial = numeroQuadraInicial;
        this.numeroQuadraFinal = numeroQuadraFinal;
        this.indicadorImovelCondominio = indicadorImovelCondominio;
        this.anoMesInstalacaoHidrometro = anoMesInstalacaoHidrometro;
        this.quantidadeGerada = quantidadeGerada;
        this.numeroConsumoMedio = numeroConsumoMedio;
        this.numeroConsumoEconomia = numeroConsumoEconomia;
        this.quantidadeEconomiasInicial = quantidadeEconomiasInicial;
        this.quantidadeEconomiasFinal = quantidadeEconomiasFinal;
        this.quantidadeDocumentosInicial = quantidadeDocumentosInicial;
        this.quantidadeDocumentosFinal = quantidadeDocumentosFinal;
        this.quantidadeOcorrenciasConsecutivas = quantidadeOcorrenciasConsecutivas;
        this.numeroAreaConstruidaInicial = numeroAreaConstruidaInicial;
        this.numeroAreaConstruidaFinal = numeroAreaConstruidaFinal;
        this.numeroMoradoresInicial = numeroMoradoresInicial;
        this.numeroMoradoresFinal = numeroMoradoresFinal;
        this.leituraAnormalidade = leituraAnormalidade;
        this.localidadeFinal = localidadeFinal;
        this.localidadeInicial = localidadeInicial;
        this.quadraInicial = quadraInicial;
        this.quadraFinal = quadraFinal;
        this.imovelPerfil = imovelPerfil;
        this.hidrometroMarca = hidrometroMarca;
        this.rotaFinal = rotaFinal;
        this.rotaInicial = rotaInicial;
        this.setorComercialFinal = setorComercialFinal;
        this.setorComercialInicial = setorComercialInicial;
        this.usuario = usuario;
        this.empresa = empresa;
        this.categoria = categoria;
        this.hidrometroCapacidade = hidrometroCapacidade;
        this.medicaoTipo = medicaoTipo;
        this.subcategoria = subcategoria;
        this.servicoTipo = servicoTipo;
    }

    /** default constructor */
    public OsSeletivaComando() {
    }

    /** minimal constructor */
    public OsSeletivaComando(Integer id, Date tempoComando, Date ultimaAlteracao, LeituraAnormalidade leituraAnormalidade, Localidade localidadeFinal, Localidade localidadeInicial, Quadra quadraInicial, Quadra quadraFinal, ImovelPerfil imovelPerfil, HidrometroMarca hidrometroMarca, Rota rotaFinal, Rota rotaInicial, SetorComercial setorComercialFinal, SetorComercial setorComercialInicial, Usuario usuario, Empresa empresa, Categoria categoria, HidrometroCapacidade hidrometroCapacidade, MedicaoTipo medicaoTipo, Subcategoria subcategoria, gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
        this.id = id;
        this.tempoComando = tempoComando;
        this.ultimaAlteracao = ultimaAlteracao;
        this.leituraAnormalidade = leituraAnormalidade;
        this.localidadeFinal = localidadeFinal;
        this.localidadeInicial = localidadeInicial;
        this.quadraInicial = quadraInicial;
        this.quadraFinal = quadraFinal;
        this.imovelPerfil = imovelPerfil;
        this.hidrometroMarca = hidrometroMarca;
        this.rotaFinal = rotaFinal;
        this.rotaInicial = rotaInicial;
        this.setorComercialFinal = setorComercialFinal;
        this.setorComercialInicial = setorComercialInicial;
        this.usuario = usuario;
        this.empresa = empresa;
        this.categoria = categoria;
        this.hidrometroCapacidade = hidrometroCapacidade;
        this.medicaoTipo = medicaoTipo;
        this.subcategoria = subcategoria;
        this.servicoTipo = servicoTipo;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTempoComando() {
        return this.tempoComando;
    }

    public void setTempoComando(Date tempoComando) {
        this.tempoComando = tempoComando;
    }

    public Date getTempoRealizacao() {
        return this.tempoRealizacao;
    }

    public void setTempoRealizacao(Date tempoRealizacao) {
        this.tempoRealizacao = tempoRealizacao;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Short getQuantidadeMaximaOrdens() {
        return this.quantidadeMaximaOrdens;
    }

    public void setQuantidadeMaximaOrdens(Short quantidadeMaximaOrdens) {
        this.quantidadeMaximaOrdens = quantidadeMaximaOrdens;
    }

    public Integer getCodigoElo() {
        return this.codigoElo;
    }

    public void setCodigoElo(Integer codigoElo) {
        this.codigoElo = codigoElo;
    }

    public Integer getCodigoSetorComercialInicial() {
        return this.codigoSetorComercialInicial;
    }

    public void setCodigoSetorComercialInicial(Integer codigoSetorComercialInicial) {
        this.codigoSetorComercialInicial = codigoSetorComercialInicial;
    }

    public Integer getCodigoSetorComercialFinal() {
        return this.codigoSetorComercialFinal;
    }

    public void setCodigoSetorComercialFinal(Integer codigoSetorComercialFinal) {
        this.codigoSetorComercialFinal = codigoSetorComercialFinal;
    }

    public Integer getNumeroQuadraInicial() {
        return this.numeroQuadraInicial;
    }

    public void setNumeroQuadraInicial(Integer numeroQuadraInicial) {
        this.numeroQuadraInicial = numeroQuadraInicial;
    }

    public Integer getNumeroQuadraFinal() {
        return this.numeroQuadraFinal;
    }

    public void setNumeroQuadraFinal(Integer numeroQuadraFinal) {
        this.numeroQuadraFinal = numeroQuadraFinal;
    }

    public Short getIndicadorImovelCondominio() {
        return this.indicadorImovelCondominio;
    }

    public void setIndicadorImovelCondominio(Short indicadorImovelCondominio) {
        this.indicadorImovelCondominio = indicadorImovelCondominio;
    }

    public Integer getAnoMesInstalacaoHidrometro() {
        return this.anoMesInstalacaoHidrometro;
    }

    public void setAnoMesInstalacaoHidrometro(Integer anoMesInstalacaoHidrometro) {
        this.anoMesInstalacaoHidrometro = anoMesInstalacaoHidrometro;
    }

    public Short getQuantidadeGerada() {
        return this.quantidadeGerada;
    }

    public void setQuantidadeGerada(Short quantidadeGerada) {
        this.quantidadeGerada = quantidadeGerada;
    }

    public Integer getNumeroConsumoMedio() {
        return this.numeroConsumoMedio;
    }

    public void setNumeroConsumoMedio(Integer numeroConsumoMedio) {
        this.numeroConsumoMedio = numeroConsumoMedio;
    }

    public Integer getNumeroConsumoEconomia() {
        return this.numeroConsumoEconomia;
    }

    public void setNumeroConsumoEconomia(Integer numeroConsumoEconomia) {
        this.numeroConsumoEconomia = numeroConsumoEconomia;
    }

    public Short getQuantidadeEconomiasInicial() {
        return this.quantidadeEconomiasInicial;
    }

    public void setQuantidadeEconomiasInicial(Short quantidadeEconomiasInicial) {
        this.quantidadeEconomiasInicial = quantidadeEconomiasInicial;
    }

    public Short getQuantidadeEconomiasFinal() {
        return this.quantidadeEconomiasFinal;
    }

    public void setQuantidadeEconomiasFinal(Short quantidadeEconomiasFinal) {
        this.quantidadeEconomiasFinal = quantidadeEconomiasFinal;
    }

    public Short getQuantidadeDocumentosInicial() {
        return this.quantidadeDocumentosInicial;
    }

    public void setQuantidadeDocumentosInicial(Short quantidadeDocumentosInicial) {
        this.quantidadeDocumentosInicial = quantidadeDocumentosInicial;
    }

    public Short getQuantidadeDocumentosFinal() {
        return this.quantidadeDocumentosFinal;
    }

    public void setQuantidadeDocumentosFinal(Short quantidadeDocumentosFinal) {
        this.quantidadeDocumentosFinal = quantidadeDocumentosFinal;
    }

    public Short getQuantidadeOcorrenciasConsecutivas() {
        return this.quantidadeOcorrenciasConsecutivas;
    }

    public void setQuantidadeOcorrenciasConsecutivas(Short quantidadeOcorrenciasConsecutivas) {
        this.quantidadeOcorrenciasConsecutivas = quantidadeOcorrenciasConsecutivas;
    }

    public BigDecimal getNumeroAreaConstruidaInicial() {
        return this.numeroAreaConstruidaInicial;
    }

    public void setNumeroAreaConstruidaInicial(BigDecimal numeroAreaConstruidaInicial) {
        this.numeroAreaConstruidaInicial = numeroAreaConstruidaInicial;
    }

    public BigDecimal getNumeroAreaConstruidaFinal() {
        return this.numeroAreaConstruidaFinal;
    }

    public void setNumeroAreaConstruidaFinal(BigDecimal numeroAreaConstruidaFinal) {
        this.numeroAreaConstruidaFinal = numeroAreaConstruidaFinal;
    }

    public Short getNumeroMoradoresInicial() {
        return this.numeroMoradoresInicial;
    }

    public void setNumeroMoradoresInicial(Short numeroMoradoresInicial) {
        this.numeroMoradoresInicial = numeroMoradoresInicial;
    }

    public Short getNumeroMoradoresFinal() {
        return this.numeroMoradoresFinal;
    }

    public void setNumeroMoradoresFinal(Short numeroMoradoresFinal) {
        this.numeroMoradoresFinal = numeroMoradoresFinal;
    }

    public LeituraAnormalidade getLeituraAnormalidade() {
        return this.leituraAnormalidade;
    }

    public void setLeituraAnormalidade(LeituraAnormalidade leituraAnormalidade) {
        this.leituraAnormalidade = leituraAnormalidade;
    }

    public Localidade getLocalidadeFinal() {
        return this.localidadeFinal;
    }

    public void setLocalidadeFinal(Localidade localidadeFinal) {
        this.localidadeFinal = localidadeFinal;
    }

    public Localidade getLocalidadeInicial() {
        return this.localidadeInicial;
    }

    public void setLocalidadeInicial(Localidade localidadeInicial) {
        this.localidadeInicial = localidadeInicial;
    }

    public Quadra getQuadraInicial() {
        return this.quadraInicial;
    }

    public void setQuadraInicial(Quadra quadraInicial) {
        this.quadraInicial = quadraInicial;
    }

    public Quadra getQuadraFinal() {
        return this.quadraFinal;
    }

    public void setQuadraFinal(Quadra quadraFinal) {
        this.quadraFinal = quadraFinal;
    }

    public ImovelPerfil getImovelPerfil() {
        return this.imovelPerfil;
    }

    public void setImovelPerfil(ImovelPerfil imovelPerfil) {
        this.imovelPerfil = imovelPerfil;
    }

    public HidrometroMarca getHidrometroMarca() {
        return this.hidrometroMarca;
    }

    public void setHidrometroMarca(HidrometroMarca hidrometroMarca) {
        this.hidrometroMarca = hidrometroMarca;
    }

    public Rota getRotaFinal() {
        return this.rotaFinal;
    }

    public void setRotaFinal(Rota rotaFinal) {
        this.rotaFinal = rotaFinal;
    }

    public Rota getRotaInicial() {
        return this.rotaInicial;
    }

    public void setRotaInicial(Rota rotaInicial) {
        this.rotaInicial = rotaInicial;
    }

    public SetorComercial getSetorComercialFinal() {
        return this.setorComercialFinal;
    }

    public void setSetorComercialFinal(SetorComercial setorComercialFinal) {
        this.setorComercialFinal = setorComercialFinal;
    }

    public SetorComercial getSetorComercialInicial() {
        return this.setorComercialInicial;
    }

    public void setSetorComercialInicial(SetorComercial setorComercialInicial) {
        this.setorComercialInicial = setorComercialInicial;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public HidrometroCapacidade getHidrometroCapacidade() {
        return this.hidrometroCapacidade;
    }

    public void setHidrometroCapacidade(HidrometroCapacidade hidrometroCapacidade) {
        this.hidrometroCapacidade = hidrometroCapacidade;
    }

    public MedicaoTipo getMedicaoTipo() {
        return this.medicaoTipo;
    }

    public void setMedicaoTipo(MedicaoTipo medicaoTipo) {
        this.medicaoTipo = medicaoTipo;
    }

    public Subcategoria getSubcategoria() {
        return this.subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public gcom.atendimentopublico.ordemservico.ServicoTipo getServicoTipo() {
        return this.servicoTipo;
    }

    public void setServicoTipo(gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
        this.servicoTipo = servicoTipo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
