package gcom.micromedicao.hidrometro;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.RateioTipo;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao()
public class HidrometroInstalacaoHistorico extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	public static final int ATRIBUTOS_ATUALIZAR_INSTALACAO_HIDROMETRO = 281; 
	//Operacao.OPERACAO_INSTALACAO_HIDROMETRO_ATUALIZAR
	
	public final static Integer INDICADOR_CAVALETE_SIM = 1;

	public final static Integer INDICADOR_CAVALETE_NAO = 2;
	
	public static final int ATRIBUTOS_EFETUAR_INSTALACAO_HIDROMETRO = 49;
		// Operacao.OPERACAO_INSTALACAO_HIDROMETRO_EFETUAR
	
	public static final int ATRIBUTOS_EFETUAR_SUBSTITUICAO_HIDROMETRO = 335;
		// Operacao.OPERACAO_SUBSTITUICAO_HIDROMETRO_EFETUAR
	
	public static final int ATRIBUTOS_ATUALIZAR_TIPO_RATEIO = 313;
		// Operacao.OPERACAO_ATUALIZAR_TIPO_RATEIO
	

	/** identifier field */
	@ControleAlteracao
	private Integer id;

	/** persistent field */
	@ControleAlteracao(funcionalidade={LigacaoAgua.ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
			LigacaoAgua.ATRIBUTOS_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
			LigacaoAgua.ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA,
			ATRIBUTOS_EFETUAR_INSTALACAO_HIDROMETRO, ATRIBUTOS_EFETUAR_SUBSTITUICAO_HIDROMETRO,
			ATRIBUTOS_ATUALIZAR_INSTALACAO_HIDROMETRO})
	private Date dataInstalacao;

	/** persistent field */
	@ControleAlteracao(funcionalidade={LigacaoAgua.ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
			LigacaoAgua.ATRIBUTOS_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
			LigacaoAgua.ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA,
			ATRIBUTOS_EFETUAR_INSTALACAO_HIDROMETRO, ATRIBUTOS_EFETUAR_SUBSTITUICAO_HIDROMETRO,
			ATRIBUTOS_ATUALIZAR_INSTALACAO_HIDROMETRO})
	private Integer numeroLeituraInstalacao;

	/** persistent field */
	private Date dataRetirada;

	/** persistent field */
	@ControleAlteracao(funcionalidade=ATRIBUTOS_EFETUAR_SUBSTITUICAO_HIDROMETRO)
	private Integer numeroLeituraRetirada;

	/** persistent field */
	private Date dataImplantacaoSistema;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade=LigacaoAgua.ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA)
	private Integer numeroLeituraCorte;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={LigacaoAgua.ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
			LigacaoAgua.ATRIBUTOS_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
			LigacaoAgua.ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA,
			ATRIBUTOS_EFETUAR_INSTALACAO_HIDROMETRO, ATRIBUTOS_EFETUAR_SUBSTITUICAO_HIDROMETRO,
			ATRIBUTOS_ATUALIZAR_INSTALACAO_HIDROMETRO})
	private String numeroSelo;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={LigacaoAgua.ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
			LigacaoAgua.ATRIBUTOS_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
			LigacaoAgua.ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA,
			ATRIBUTOS_EFETUAR_INSTALACAO_HIDROMETRO, ATRIBUTOS_EFETUAR_SUBSTITUICAO_HIDROMETRO,
			ATRIBUTOS_ATUALIZAR_INSTALACAO_HIDROMETRO})
	private Short indicadorExistenciaCavalete;
	
	private Usuario usuarioRetirada;
	
	private Usuario usuarioInstalacao;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_INSTALACAO_HIDROMETRO})
	private String numeroLacre;

	/** nullable persistent field */
	private Short indicadorInstalcaoSubstituicao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={LigacaoAgua.ATRIBUTOS_EFETUAR_SUPRESSAO_LIGACAO_AGUA,
			LigacaoAgua.ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA})
	private Integer numeroLeituraSupressao;

	/** nullable persistent field */
	private Date ultimaAlteracao;
    
	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_INSTALACAO_HIDROMETRO, 
			ATRIBUTOS_EFETUAR_SUBSTITUICAO_HIDROMETRO})
    private Short indicadorTrocaProtecao;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_INSTALACAO_HIDROMETRO, 
			ATRIBUTOS_EFETUAR_SUBSTITUICAO_HIDROMETRO})
    private Short indicadorTrocaRegistro;

	/** persistent field */
	private Imovel imovel;

	/** persistent field */
	@ControleAlteracao(value=FiltroHidrometroInstalacaoHistorico.HIDROMETRO_PROTECAO,
			funcionalidade={LigacaoAgua.ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
			LigacaoAgua.ATRIBUTOS_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
			LigacaoAgua.ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA,
			ATRIBUTOS_EFETUAR_INSTALACAO_HIDROMETRO, ATRIBUTOS_ATUALIZAR_INSTALACAO_HIDROMETRO})		
	private gcom.micromedicao.hidrometro.HidrometroProtecao hidrometroProtecao;

	/** persistent field */
	@ControleAlteracao(value=FiltroHidrometroInstalacaoHistorico.HIDROMETRO,
			funcionalidade={LigacaoAgua.ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
				LigacaoAgua.ATRIBUTOS_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
				LigacaoAgua.ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA,
				ATRIBUTOS_EFETUAR_INSTALACAO_HIDROMETRO, ATRIBUTOS_ATUALIZAR_INSTALACAO_HIDROMETRO})	
	private gcom.micromedicao.hidrometro.Hidrometro hidrometro;

	/** persistent field */
	@ControleAlteracao(value=FiltroHidrometroInstalacaoHistorico.RATEIO_TIPO,
			funcionalidade={HidrometroInstalacaoHistorico.ATRIBUTOS_ATUALIZAR_TIPO_RATEIO})
	private RateioTipo rateioTipo;

	/** persistent field */
	@ControleAlteracao(value=FiltroHidrometroInstalacaoHistorico.HIDROMETRO_LOCAL_INSTALACAO,
			funcionalidade={LigacaoAgua.ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
				LigacaoAgua.ATRIBUTOS_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
				LigacaoAgua.ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA,
				ATRIBUTOS_EFETUAR_INSTALACAO_HIDROMETRO, ATRIBUTOS_EFETUAR_SUBSTITUICAO_HIDROMETRO,
				ATRIBUTOS_ATUALIZAR_INSTALACAO_HIDROMETRO})
	private gcom.micromedicao.hidrometro.HidrometroLocalInstalacao hidrometroLocalInstalacao;

	/** persistent field */
	private LigacaoAgua ligacaoAgua;

	/** persistent field */
	@ControleAlteracao(value=FiltroHidrometroInstalacaoHistorico.MEDICAO_TIPO,
			funcionalidade={ATRIBUTOS_EFETUAR_INSTALACAO_HIDROMETRO, ATRIBUTOS_EFETUAR_SUBSTITUICAO_HIDROMETRO,
			ATRIBUTOS_ATUALIZAR_INSTALACAO_HIDROMETRO})
	private MedicaoTipo medicaoTipo;
	
	public HidrometroInstalacaoHistorico(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public HidrometroInstalacaoHistorico(
			Date dataInstalacao,
			Integer numeroLeituraInstalacao,
			Date dataRetirada,
			Integer numeroLeituraRetirada,
			Date dataImplantacaoSistema,
			Integer numeroLeituraCorte,
			String numeroSelo,
			Short indicadorExistenciaCavalete,
			Short indicadorInstalcaoSubstituicao,
			Integer numeroLeituraSupressao,
			Date ultimaAlteracao,
			Imovel imovel,
			gcom.micromedicao.hidrometro.HidrometroProtecao hidrometroProtecao,
			gcom.micromedicao.hidrometro.Hidrometro hidrometro,
			RateioTipo rateioTipo,
			gcom.micromedicao.hidrometro.HidrometroLocalInstalacao hidrometroLocalInstalacao,
			LigacaoAgua ligacaoAgua, MedicaoTipo medicaoTipo) {
		this.dataInstalacao = dataInstalacao;
		this.numeroLeituraInstalacao = numeroLeituraInstalacao;
		this.dataRetirada = dataRetirada;
		this.numeroLeituraRetirada = numeroLeituraRetirada;
		this.dataImplantacaoSistema = dataImplantacaoSistema;
		this.numeroLeituraCorte = numeroLeituraCorte;
		this.numeroSelo = numeroSelo;
		this.indicadorExistenciaCavalete = indicadorExistenciaCavalete;
		this.indicadorInstalcaoSubstituicao = indicadorInstalcaoSubstituicao;
		this.numeroLeituraSupressao = numeroLeituraSupressao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.imovel = imovel;
		this.hidrometroProtecao = hidrometroProtecao;
		this.hidrometro = hidrometro;
		this.rateioTipo = rateioTipo;
		this.hidrometroLocalInstalacao = hidrometroLocalInstalacao;
		this.ligacaoAgua = ligacaoAgua;
		this.medicaoTipo = medicaoTipo;
	}

	/** default constructor */
	public HidrometroInstalacaoHistorico() {
	}

	/** minimal constructor */
	public HidrometroInstalacaoHistorico(
			Date dataInstalacao,
			Integer numeroLeituraInstalacao,
			Date dataRetirada,
			Integer numeroLeituraRetirada,
			Date dataImplantacaoSistema,
			Imovel imovel,
			gcom.micromedicao.hidrometro.HidrometroProtecao hidrometroProtecao,
			gcom.micromedicao.hidrometro.Hidrometro hidrometro,
			RateioTipo rateioTipo,
			gcom.micromedicao.hidrometro.HidrometroLocalInstalacao hidrometroLocalInstalacao,
			LigacaoAgua ligacaoAgua, MedicaoTipo medicaoTipo) {
		this.dataInstalacao = dataInstalacao;
		this.numeroLeituraInstalacao = numeroLeituraInstalacao;
		this.dataRetirada = dataRetirada;
		this.numeroLeituraRetirada = numeroLeituraRetirada;
		this.dataImplantacaoSistema = dataImplantacaoSistema;
		this.imovel = imovel;
		this.hidrometroProtecao = hidrometroProtecao;
		this.hidrometro = hidrometro;
		this.rateioTipo = rateioTipo;
		this.hidrometroLocalInstalacao = hidrometroLocalInstalacao;
		this.ligacaoAgua = ligacaoAgua;
		this.medicaoTipo = medicaoTipo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataInstalacao() {
		return this.dataInstalacao;
	}

	public void setDataInstalacao(Date dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}

	public Integer getNumeroLeituraInstalacao() {
		return this.numeroLeituraInstalacao;
	}

	public void setNumeroLeituraInstalacao(Integer numeroLeituraInstalacao) {
		this.numeroLeituraInstalacao = numeroLeituraInstalacao;
	}

	public Date getDataRetirada() {
		return this.dataRetirada;
	}

	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public Integer getNumeroLeituraRetirada() {
		return this.numeroLeituraRetirada;
	}

	public void setNumeroLeituraRetirada(Integer numeroLeituraRetirada) {
		this.numeroLeituraRetirada = numeroLeituraRetirada;
	}

	public Date getDataImplantacaoSistema() {
		return this.dataImplantacaoSistema;
	}

	public void setDataImplantacaoSistema(Date dataImplantacaoSistema) {
		this.dataImplantacaoSistema = dataImplantacaoSistema;
	}

	public Integer getNumeroLeituraCorte() {
		return this.numeroLeituraCorte;
	}

	public void setNumeroLeituraCorte(Integer numeroLeituraCorte) {
		this.numeroLeituraCorte = numeroLeituraCorte;
	}

	public String getNumeroSelo() {
		return this.numeroSelo;
	}

	public void setNumeroSelo(String numeroSelo) {
		this.numeroSelo = numeroSelo;
	}

	public Short getIndicadorExistenciaCavalete() {
		return this.indicadorExistenciaCavalete;
	}

	public void setIndicadorExistenciaCavalete(Short indicadorExistenciaCavalete) {
		this.indicadorExistenciaCavalete = indicadorExistenciaCavalete;
	}

	public Short getIndicadorInstalcaoSubstituicao() {
		return this.indicadorInstalcaoSubstituicao;
	}

	public void setIndicadorInstalcaoSubstituicao(
			Short indicadorInstalcaoSubstituicao) {
		this.indicadorInstalcaoSubstituicao = indicadorInstalcaoSubstituicao;
	}

	public Integer getNumeroLeituraSupressao() {
		return this.numeroLeituraSupressao;
	}

	public void setNumeroLeituraSupressao(Integer numeroLeituraSupressao) {
		this.numeroLeituraSupressao = numeroLeituraSupressao;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public gcom.micromedicao.hidrometro.HidrometroProtecao getHidrometroProtecao() {
		return this.hidrometroProtecao;
	}

	public void setHidrometroProtecao(
			gcom.micromedicao.hidrometro.HidrometroProtecao hidrometroProtecao) {
		this.hidrometroProtecao = hidrometroProtecao;
	}

	public gcom.micromedicao.hidrometro.Hidrometro getHidrometro() {
		return this.hidrometro;
	}

	public void setHidrometro(gcom.micromedicao.hidrometro.Hidrometro hidrometro) {
		this.hidrometro = hidrometro;
	}

	public RateioTipo getRateioTipo() {
		return this.rateioTipo;
	}

	public void setRateioTipo(RateioTipo rateioTipo) {
		this.rateioTipo = rateioTipo;
	}

	public gcom.micromedicao.hidrometro.HidrometroLocalInstalacao getHidrometroLocalInstalacao() {
		return this.hidrometroLocalInstalacao;
	}

	public void setHidrometroLocalInstalacao(
			gcom.micromedicao.hidrometro.HidrometroLocalInstalacao hidrometroLocalInstalacao) {
		this.hidrometroLocalInstalacao = hidrometroLocalInstalacao;
	}

	public LigacaoAgua getLigacaoAgua() {
		return this.ligacaoAgua;
	}

	public void setLigacaoAgua(LigacaoAgua ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}

	public MedicaoTipo getMedicaoTipo() {
		return this.medicaoTipo;
	}

	public void setMedicaoTipo(MedicaoTipo medicaoTipo) {
		this.medicaoTipo = medicaoTipo;
	}
    
    

	public Short getIndicadorTrocaProtecao() {
        return indicadorTrocaProtecao;
    }

    public void setIndicadorTrocaProtecao(Short indicadorTrocaProtecao) {
        this.indicadorTrocaProtecao = indicadorTrocaProtecao;
    }

    public Short getIndicadorTrocaRegistro() {
        return indicadorTrocaRegistro;
    }

    public void setIndicadorTrocaRegistro(Short indicadorTrocaRegistro) {
        this.indicadorTrocaRegistro = indicadorTrocaRegistro;
    }

    public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
		filtroHidrometroInstalacaoHistorico
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroInstalacaoHistorico.ID, this.getId()));

		filtroHidrometroInstalacaoHistorico
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroProtecao");
		filtroHidrometroInstalacaoHistorico
				.adicionarCaminhoParaCarregamentoEntidade("hidrometro");
		filtroHidrometroInstalacaoHistorico
				.adicionarCaminhoParaCarregamentoEntidade("rateioTipo");
		filtroHidrometroInstalacaoHistorico
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalInstalacao");
		filtroHidrometroInstalacaoHistorico
				.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
		filtroHidrometroInstalacaoHistorico
				.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroHidrometroInstalacaoHistorico
				.adicionarCaminhoParaCarregamentoEntidade("medicaoTipo");

		return filtroHidrometroInstalacaoHistorico;
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {	
		String []labels = {"dataInstalacao","hidrometro.id"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Data Instalacao","Hidrometro"};
		return labels;		
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		if (hidrometro != null){
			return hidrometro.getNumero();	
		} 
		return "";		
	}

	public String getNumeroLacre() {
		return numeroLacre;
	}

	public void setNumeroLacre(String numeroLacre) {
		this.numeroLacre = numeroLacre;
	}

	public Usuario getUsuarioRetirada() {
		return usuarioRetirada;
	}

	public void setUsuarioRetirada(Usuario usuarioRetirada) {
		this.usuarioRetirada = usuarioRetirada;
	}

	public Usuario getUsuarioInstalacao() {
		return usuarioInstalacao;
	}

	public void setUsuarioInstalacao(Usuario usuarioInstalacao) {
		this.usuarioInstalacao = usuarioInstalacao;
	}
}

