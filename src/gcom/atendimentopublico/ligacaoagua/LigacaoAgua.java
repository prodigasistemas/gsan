package gcom.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.ordemservico.SupressaoMotivo;
import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao
public class LigacaoAgua extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	public static final int ATRIBUTOS_EFETUAR_LIGACAO = 257; 
	public static final int ATRIBUTOS_ATUALIZAR_CONSUMO_MINIMO = 393; 
	public static final int ATRIBUTOS_EFETUAR_CORTE_LIGACAO_AGUA = 48;
	public static final int ATRIBUTOS_EFETUAR_CORTE_ADMINISTRATIVO_LIGACAO_AGUA = 358; 	
	public static final int ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO = 706;
	public static final int ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA = 882;
	public static final int ATRIBUTOS_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_INSTALACAO_HIDROMETRO = 879;
	public static final int ATRIBUTOS_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA = 51;
	public static final int ATRIBUTOS_EFETUAR_SUPRESSAO_LIGACAO_AGUA = 685;
	public static final int ATRIBUTOS_EFETUAR_RELIGACAO_AGUA = 50;
	public static final int ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA = 422;
	public static final int ATRIBUTOS_RELIGAR_IMOVEIS_CORTADOS_COM_CONSUMO_REAL = 1685; 
	
	private Integer id;
	private Date dataImplantacao;

	@ControleAlteracao(funcionalidade=ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA)
	private Date dataLigacao;

	@ControleAlteracao(funcionalidade=ATRIBUTOS_EFETUAR_SUPRESSAO_LIGACAO_AGUA)	
	private Date dataSupressao;

	private Date dataCorte;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_RELIGACAO_AGUA, ATRIBUTOS_RELIGAR_IMOVEIS_CORTADOS_COM_CONSUMO_REAL})	
	private Date dataReligacao;

	@ControleAlteracao(funcionalidade=ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA)	
	private Integer numeroSeloCorte;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_SUPRESSAO_LIGACAO_AGUA, ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA})	
	private Integer numeroSeloSupressao;

	private Date ultimaAlteracao;
	private Short laguIcemissaocortesupressao;
	private Imovel imovel;
	private gcom.atendimentopublico.ligacaoagua.EmissaoOrdemCobrancaTipo emissaoOrdemCobrancaTipo;
	private HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico;
	private Date dataCorteAdministrativo;
	private LigacaoOrigem ligacaoOrigem;

	@ControleAlteracao(funcionalidade=ATRIBUTOS_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_INSTALACAO_HIDROMETRO)	
	private Date dataRestabelecimentoAgua;

	@ControleAlteracao(funcionalidade=ATRIBUTOS_ATUALIZAR_CONSUMO_MINIMO)	
	private Integer numeroConsumoMinimoAgua;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_SUPRESSAO_LIGACAO_AGUA,ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA})	
	private SupressaoTipo supressaoTipo;


	@ControleAlteracao(value=FiltroLigacaoAgua.LIGACAO_AGUA_PERFIL,funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO, ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
				ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA, ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA})		
	private LigacaoAguaPerfil ligacaoAguaPerfil;

	@ControleAlteracao(value=FiltroLigacaoAgua.LIGACAO_AGUA_DIAMETRO, funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO, ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
				ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA, ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA})	
	private LigacaoAguaDiametro ligacaoAguaDiametro;

	@ControleAlteracao(value=FiltroLigacaoAgua.CORTE_TIPO, funcionalidade={ATRIBUTOS_EFETUAR_CORTE_LIGACAO_AGUA,ATRIBUTOS_EFETUAR_CORTE_ADMINISTRATIVO_LIGACAO_AGUA,
				ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA})		
	private CorteTipo corteTipo;

	@ControleAlteracao(value=FiltroLigacaoAgua.LIGACAO_AGUA_MATERIAL, funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO,ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
				ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA, ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA})	
	private LigacaoAguaMaterial ligacaoAguaMaterial;

	@ControleAlteracao(value=FiltroLigacaoAgua.CORTE_MOTIVO, funcionalidade={ATRIBUTOS_EFETUAR_CORTE_LIGACAO_AGUA, ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA})		
	private MotivoCorte motivoCorte;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_SUPRESSAO_LIGACAO_AGUA,ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA})	
	private SupressaoMotivo supressaoMotivo;

	@ControleAlteracao(value=FiltroLigacaoAgua.RAMAL_LOCAL_LIGACAO, funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO,ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
				ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA, ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA})
	private RamalLocalInstalacao ramalLocalInstalacao;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO, ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,ATRIBUTOS_ATUALIZAR_LIGACAO_AGUA,
			ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO_SEM_RA})			
	private String numeroLacre;
	
	@SuppressWarnings("rawtypes")
	public Set medicaoHistoricos;
	
	public LigacaoAgua() {
	}
	
	public LigacaoAgua(
			Date dataImplantacao,
			Date dataLigacao,
			Date dataSupressao,
			Date dataCorte,
			Date dataReligacao,
			Integer numeroSeloCorte,
			Integer numeroSeloSupressao,
			Date ultimaAlteracao,
			Date dataRestabelecimentoAgua,
			Date dataCorteAdministrativo,
			Integer numeroConsumoMinimoAgua,
			Short laguIcemissaocortesupressao,
			Imovel imovel,
			gcom.atendimentopublico.ligacaoagua.SupressaoTipo supressaoTipo,
			gcom.atendimentopublico.ligacaoagua.EmissaoOrdemCobrancaTipo emissaoOrdemCobrancaTipo,
			gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil ligacaoAguaPerfil,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico,
			gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro ligacaoAguaDiametro,
			gcom.atendimentopublico.ligacaoagua.CorteTipo corteTipo,
			gcom.atendimentopublico.ligacaoagua.LigacaoAguaMaterial ligacaoAguaMaterial,
			gcom.atendimentopublico.ligacaoagua.MotivoCorte motivoCorte,
			SupressaoMotivo supressaoMotivo) {
		this.dataImplantacao = dataImplantacao;
		this.dataLigacao = dataLigacao;
		this.dataSupressao = dataSupressao;
		this.dataCorte = dataCorte;
		this.dataReligacao = dataReligacao;
		this.numeroSeloCorte = numeroSeloCorte;
		this.numeroSeloSupressao = numeroSeloSupressao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.dataRestabelecimentoAgua = dataRestabelecimentoAgua;
		this.dataCorteAdministrativo = dataCorteAdministrativo;
		this.numeroConsumoMinimoAgua = numeroConsumoMinimoAgua;
		this.laguIcemissaocortesupressao = laguIcemissaocortesupressao;
		this.imovel = imovel;
		this.supressaoTipo = supressaoTipo;
		this.emissaoOrdemCobrancaTipo = emissaoOrdemCobrancaTipo;
		this.ligacaoAguaPerfil = ligacaoAguaPerfil;
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
		this.ligacaoAguaDiametro = ligacaoAguaDiametro;
		this.corteTipo = corteTipo;
		this.ligacaoAguaMaterial = ligacaoAguaMaterial;
		this.motivoCorte = motivoCorte;
		this.supressaoMotivo = supressaoMotivo;
	}

	public LigacaoAgua(
			gcom.atendimentopublico.ligacaoagua.SupressaoTipo supressaoTipo,
			gcom.atendimentopublico.ligacaoagua.EmissaoOrdemCobrancaTipo emissaoOrdemCobrancaTipo,
			gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil ligacaoAguaPerfil,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico,
			gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro ligacaoAguaDiametro,
			gcom.atendimentopublico.ligacaoagua.CorteTipo corteTipo,
			gcom.atendimentopublico.ligacaoagua.LigacaoAguaMaterial ligacaoAguaMaterial) {
		this.supressaoTipo = supressaoTipo;
		this.emissaoOrdemCobrancaTipo = emissaoOrdemCobrancaTipo;
		this.ligacaoAguaPerfil = ligacaoAguaPerfil;
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
		this.ligacaoAguaDiametro = ligacaoAguaDiametro;
		this.corteTipo = corteTipo;
		this.ligacaoAguaMaterial = ligacaoAguaMaterial;
	}

	public Date getDataCorteAdministrativo() {
		return dataCorteAdministrativo;
	}

	public void setDataCorteAdministrativo(Date dataCorteAdministrativo) {
		this.dataCorteAdministrativo = dataCorteAdministrativo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataImplantacao() {
		return this.dataImplantacao;
	}

	public void setDataImplantacao(Date dataImplantacao) {
		this.dataImplantacao = dataImplantacao;
	}

	public Date getDataLigacao() {
		return this.dataLigacao;
	}

	public void setDataLigacao(Date dataLigacao) {
		this.dataLigacao = dataLigacao;
	}

	public Date getDataSupressao() {
		return this.dataSupressao;
	}

	public void setDataSupressao(Date dataSupressao) {
		this.dataSupressao = dataSupressao;
	}

	public Date getDataCorte() {
		return this.dataCorte;
	}

	public void setDataCorte(Date dataCorte) {
		this.dataCorte = dataCorte;
	}

	public Date getDataReligacao() {
		return this.dataReligacao;
	}

	public void setDataReligacao(Date dataReligacao) {
		this.dataReligacao = dataReligacao;
	}

	public Integer getNumeroSeloCorte() {
		return this.numeroSeloCorte;
	}

	public void setNumeroSeloCorte(Integer numeroSeloCorte) {
		this.numeroSeloCorte = numeroSeloCorte;
	}

	public Integer getNumeroSeloSupressao() {
		return this.numeroSeloSupressao;
	}

	public void setNumeroSeloSupressao(Integer numeroSeloSupressao) {
		this.numeroSeloSupressao = numeroSeloSupressao;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getNumeroConsumoMinimoAgua() {
		return this.numeroConsumoMinimoAgua;
	}

	public void setNumeroConsumoMinimoAgua(Integer numeroConsumoMinimoAgua) {
		this.numeroConsumoMinimoAgua = numeroConsumoMinimoAgua;
	}

	public Short getLaguIcemissaocortesupressao() {
		return this.laguIcemissaocortesupressao;
	}

	public void setLaguIcemissaocortesupressao(Short laguIcemissaocortesupressao) {
		this.laguIcemissaocortesupressao = laguIcemissaocortesupressao;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public SupressaoTipo getSupressaoTipo() {
		return this.supressaoTipo;
	}

	public void setSupressaoTipo(SupressaoTipo supressaoTipo) {
		this.supressaoTipo = supressaoTipo;
	}

	public EmissaoOrdemCobrancaTipo getEmissaoOrdemCobrancaTipo() {
		return this.emissaoOrdemCobrancaTipo;
	}

	public void setEmissaoOrdemCobrancaTipo(EmissaoOrdemCobrancaTipo emissaoOrdemCobrancaTipo) {
		this.emissaoOrdemCobrancaTipo = emissaoOrdemCobrancaTipo;
	}

	public LigacaoAguaPerfil getLigacaoAguaPerfil() {
		return this.ligacaoAguaPerfil;
	}

	public void setLigacaoAguaPerfil(LigacaoAguaPerfil ligacaoAguaPerfil) {
		this.ligacaoAguaPerfil = ligacaoAguaPerfil;
	}

	public HidrometroInstalacaoHistorico getHidrometroInstalacaoHistorico() {
		return this.hidrometroInstalacaoHistorico;
	}

	public void setHidrometroInstalacaoHistorico(HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico) {
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
	}

	public gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro getLigacaoAguaDiametro() {
		return this.ligacaoAguaDiametro;
	}

	public void setLigacaoAguaDiametro(LigacaoAguaDiametro ligacaoAguaDiametro) {
		this.ligacaoAguaDiametro = ligacaoAguaDiametro;
	}

	public CorteTipo getCorteTipo() {
		return this.corteTipo;
	}

	public void setCorteTipo(CorteTipo corteTipo) {
		this.corteTipo = corteTipo;
	}

	public LigacaoAguaMaterial getLigacaoAguaMaterial() {
		return this.ligacaoAguaMaterial;
	}

	public void setLigacaoAguaMaterial(LigacaoAguaMaterial ligacaoAguaMaterial) {
		this.ligacaoAguaMaterial = ligacaoAguaMaterial;
	}

	public MotivoCorte getMotivoCorte() {
		return this.motivoCorte;
	}

	public void setMotivoCorte(MotivoCorte motivoCorte) {
		this.motivoCorte = motivoCorte;
	}

	public SupressaoMotivo getSupressaoMotivo() {
		return this.supressaoMotivo;
	}

	public void setSupressaoMotivo(SupressaoMotivo supressaoMotivo) {
		this.supressaoMotivo = supressaoMotivo;
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
		
		FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();

		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("supressaoTipo");
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("emissaoOrdemCobrancaTipo");
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaPerfil");
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico");
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaDiametro");
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("corteTipo");
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaMaterial");
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("motivoCorte");
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("supressaoMotivo");

		filtroLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroLigacaoAgua.ID, this.getId()));
		
		return filtroLigacaoAgua;
	}

	public Date getDataRestabelecimentoAgua() {
		return dataRestabelecimentoAgua;
	}

	public void setDataRestabelecimentoAgua(Date dataRestabelecimentoAgua) {
		this.dataRestabelecimentoAgua = dataRestabelecimentoAgua;
	}

	public RamalLocalInstalacao getRamalLocalInstalacao() {
		return ramalLocalInstalacao;
	}

	public void setRamalLocalInstalacao(RamalLocalInstalacao ramalLocalInstalacao) {
		this.ramalLocalInstalacao = ramalLocalInstalacao;
	}
	
	public LigacaoOrigem getLigacaoOrigem() {
		return ligacaoOrigem;
	}

	public void setLigacaoOrigem(LigacaoOrigem ligacaoOrigem) {
		this.ligacaoOrigem = ligacaoOrigem;
	}

	public String getNumeroLacre() {
		return numeroLacre;
	}

	public void setNumeroLacre(String numeroLacre) {
		this.numeroLacre = numeroLacre;
	}
	
	@SuppressWarnings("rawtypes")
	public Set getMedicaoHistoricos() {
		return medicaoHistoricos;
	}
	
	@SuppressWarnings("rawtypes")
	public void setMedicaoHistoricos(Set medicaoHistoricos) {
		this.medicaoHistoricos = medicaoHistoricos;
	}
}
