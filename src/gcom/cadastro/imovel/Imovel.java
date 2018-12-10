package gcom.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.CobrancaSituacaoTipo;
import gcom.enums.Status;
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FaturamentoTipo;
import gcom.faturamento.conta.Conta;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.Rota;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.model.IAtualizacaoCadastro;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;

@ControleAlteracao()
public class Imovel extends ObjetoTransacao implements IImovel, IAtualizacaoCadastro{
	
	
	private static final long serialVersionUID = 1L;

	public static final int ATRIBUTOS_IMOVEL_INSERIR = 9; //Operacao.OPERACAO_IMOVEL_INSERIR
	public static final int ATRIBUTOS_IMOVEL_ATUALIZAR = 17; //Operacao.OPERACAO_IMOVEL_ATUALIZAR
	public static final int ATRIBUTOS_IMOVEL_TRANSFERIR = 1831; //Operacao.OPERACAO_TRANSFERIR_IMOVEL_LOGRADOURO
	public static final int ATRIBUTOS_IMOVEL_REMOVER = 292; //Operacao.OPERACAO_IMOVEL_REMOVER
	public static final int ATRIBUTOS_EXCLUIR_NEGATIVACAO_ONLINE = 1132; // Operacao.OPERACAO_EXCLUIR_NEGATIVACAO_ONLINE
	public static final int ATRIBUTOS_ATUALIZAR_DADOS_IMOVEL_PROMAIS = 1681; //Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_PROMAIS
	public static final int ALTERAR_IMOVEL_FATURAMENTO = 1705;
	public static final int OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL = 1509;
	
	private Integer id;
	private Short numeroReparcelamentoConsecutivos;
	private Short indicadorImovelCondominio;
	private Date dataSupressaoParcial;
	private Date dataInfracao;
	private Short numeroRetificacao;
	private Short numeroLeituraExcecao;
	private Short numeroParcelamento;
	private Short numeroReparcelamento;
	private Short indicadorConta;
	private ImovelContaEnvio imovelContaEnvio;
	private Short indicadorDebitoConta;
	private ImovelEnderecoAnterior imovelEnderecoAnterior;
	private LigacaoEsgoto ligacaoEsgoto;
	private gcom.cadastro.imovel.Imovel imovelCondominio;
	private HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico;
	private gcom.cadastro.imovel.EloAnormalidade eloAnormalidade;
	private FaturamentoSituacaoTipo faturamentoSituacaoTipo;
	private gcom.faturamento.consumotarifa.ConsumoTarifa consumoTarifa;
	private Date ultimaAlteracao;
	private Set<ImovelSubcategoria> imovelSubcategorias;
	@SuppressWarnings("rawtypes")
	private Set clienteImoveis;
	private Set<MedicaoHistorico> medicaoHistoricos;
	private FaturamentoTipo faturamentoTipo;
	private FaturamentoSituacaoMotivo faturamentoSituacaoMotivo;
	private Set<ConsumoHistorico> consumosHistoricos;
	private Short indicadorSuspensaoAbastecimento;
	private Short indicadorVencimentoMesSeguinte;
    private SituacaoAtualizacaoCadastral situacaoAtualizacaoCadastral;
    private Set<Conta> contas;
    private String informacoesComplementares;
    private Integer indicadorReincidenciaInfracao;
    private Integer codigoDebitoAutomatico;
    private Integer numeroQuadraEntrega;
    private Integer anoMesExclusaoTarifaSocial;
    private Short indicadorNivelInstalacaoEsgoto;
    private Short indicadorImovelAreaComum;
    private Integer categoriaPrincipalId;
    private Integer subCategoriaPrincipalId;
    private Integer idCapacidadeHidrometro;
    private Integer idMarcaHidrometro;
    private Integer idProtecaoHidrometro;
    private String numeroHidrometro;
    private String tipoEntrevistado;
	private Integer idMunicipio;
	private String nomeMunicipio;
	private String dsUFSiglaMunicipio;
	private CobrancaSituacaoTipo cobrancaSituacaoTipo;
	private transient boolean validarSeImovelEmCampo;
	private String observacaoCategoria;

	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private short lote;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private short subLote;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private Short testadaLote;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private String numeroImovel;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_DADOS_IMOVEL_PROMAIS,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private String nomeImovel;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private String complementoEndereco;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL,
			TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,
			TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})
	private BigDecimal areaConstruida;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private BigDecimal volumeReservatorioSuperior;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private BigDecimal volumeReservatorioInferior;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER, OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private BigDecimal volumePiscina;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private Short numeroPontosUtilizacao;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,
			TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,
			TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private Short numeroMorador;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private Short diaVencimento;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,
			TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})
	private String numeroIptu;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,
			TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})
	private Long numeroCelpe;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private Short indicadorEmissaoExtratoFaturamento;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private Short indicadorExclusao;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private String coordenadaX;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private String coordenadaY;

	@ControleAlteracao(value=FiltroImovel.LIGACAO_AGUA)
	private LigacaoAgua ligacaoAgua;

	@ControleAlteracao(value=FiltroImovel.IMOVEL_PRINCIPAL, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR})
	private gcom.cadastro.imovel.Imovel imovelPrincipal;

	@ControleAlteracao(value=FiltroImovel.LIGACAO_ESGOTO_SITUACAO,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR, LigacaoEsgoto.ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO, 
				LigacaoEsgoto.ATRIBUTOS_EFETUAR_MUDANCA_SITUACAO_FATURAMENTO_LIGACAO_ESGOTO,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})	
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

	@ControleAlteracao(value=FiltroImovel.ANORMALIDADE_INFORMADA, funcionalidade={ALTERAR_IMOVEL_FATURAMENTO})
	private LeituraAnormalidade leituraAnormalidade;

	@ControleAlteracao(value=FiltroImovel.SETOR_COMERCIAL, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private SetorComercial setorComercial;

	@ControleAlteracao(value=FiltroImovel.AREA_CONSTRUIDA_FAIXA,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,
			TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL, TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})
	private gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa;

	@ControleAlteracao(value=FiltroImovel.PAVIMENTO_CALCADA, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.PavimentoCalcada pavimentoCalcada;

	@ControleAlteracao(value=FiltroImovel.IMOVEL_PERFIL, funcionalidade={OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private gcom.cadastro.imovel.ImovelPerfil imovelPerfil;

	@ControleAlteracao(value=FiltroImovel.RESERVATORIO_VOLUME_FAIXA_SUPERIOR, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaSuperior;

	@ControleAlteracao(value=FiltroImovel.RESERVATORIO_VOLUME_FAIXA_INFERIOR, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaInferior;

	@ControleAlteracao(value=FiltroImovel.LOCALIDADE, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private Localidade localidade;

	@ControleAlteracao(value=FiltroImovel.QUADRA, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private Quadra quadra;

    @ControleAlteracao(value=FiltroImovel.COBRANCA_SITUACAO, funcionalidade={ATRIBUTOS_EXCLUIR_NEGATIVACAO_ONLINE})
	private CobrancaSituacao cobrancaSituacao;

	@ControleAlteracao(value=FiltroImovel.PAVIMENTO_RUA, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.PavimentoRua pavimentoRua;

	@ControleAlteracao(value=FiltroImovel.ENDERECO_REFERENCIA, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private EnderecoReferencia enderecoReferencia;

	@ControleAlteracao(value=FiltroImovel.CADASTRO_OCORRENCIA, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private gcom.cadastro.imovel.CadastroOcorrencia cadastroOcorrencia;

	@ControleAlteracao(value=FiltroImovel.POCO_TIPO, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.PocoTipo pocoTipo;

	@ControleAlteracao(value=FiltroImovel.LIGACAO_AGUA_SITUACAO,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,LigacaoAgua.ATRIBUTOS_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA, 
				LigacaoAgua.ATRIBUTOS_EFETUAR_RELIGACAO_AGUA, LigacaoAgua.ATRIBUTOS_EFETUAR_SUPRESSAO_LIGACAO_AGUA,
				LigacaoAgua.ATRIBUTOS_RELIGAR_IMOVEIS_CORTADOS_COM_CONSUMO_REAL,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private LigacaoAguaSituacao ligacaoAguaSituacao;

	@ControleAlteracao(value=FiltroImovel.DESPEJO, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.Despejo despejo;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.FonteAbastecimento fonteAbastecimento;
	
	@ControleAlteracao(value=FiltroImovel.PISCINA_VOLUME_FAIXA, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.PiscinaVolumeFaixa piscinaVolumeFaixa;
	
	@ControleAlteracao(value=FiltroImovel.IMOVEL_TIPO_HABITACAO, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.ImovelTipoHabitacao imovelTipoHabitacao;
	
	@ControleAlteracao(value=FiltroImovel.IMOVEL_TIPO_PROPRIEDADE, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.ImovelTipoPropriedade imovelTipoPropriedade;
	
	@ControleAlteracao(value=FiltroImovel.IMOVEL_TIPO_CONSTRUCAO, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.ImovelTipoConstrucao imovelTipoConstrucao;
	
	@ControleAlteracao(value=FiltroImovel.IMOVEL_TIPO_COBERTURA, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.ImovelTipoCobertura imovelTipoCobertura;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private Short quantidadeEconomias;

	@ControleAlteracao(value=FiltroImovel.BAIRRO, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,ATRIBUTOS_IMOVEL_TRANSFERIR})
	private LogradouroBairro logradouroBairro;
	
	@ControleAlteracao(value=FiltroImovel.LOGRADOURO_CEP, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,ATRIBUTOS_IMOVEL_TRANSFERIR})
	private LogradouroCep logradouroCep;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Short indicadorJardim;
    
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Integer numeroSequencialRota;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Integer numeroSequencialRotaEntrega;
	
	@ControleAlteracao(value=FiltroImovel.ROTA_ENTREGA, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Rota rotaEntrega;
	
	@ControleAlteracao(value=FiltroImovel.ROTA_ALTERNATIVA, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Rota rotaAlternativa;
    
	@ControleAlteracao(value=FiltroImovel.FUNCIONARIO, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Funcionario funcionario;
    
    @ControleAlteracao(value=FiltroImovel.LOGRADOURO_TIPO_PERIMETRO_INICIAL, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR})
    private Logradouro perimetroInicial;
    
    @ControleAlteracao(value=FiltroImovel.LOGRADOURO_TIPO_PERIMETRO_FINAL, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR})
    private Logradouro perimetroFinal;
    
	@ControleAlteracao(value=FiltroImovel.QUADRA_FACE,funcionalidade={ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private QuadraFace quadraFace;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private String numeroMedidorEnergia;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Date dataVisitaComercial;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Short classeSocial;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Short quantidadeAnimaisDomesticos;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private BigDecimal volumeCisterna;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private BigDecimal volumeCaixaDagua;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Short tipoUso;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Short acessoHidrometro;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Integer quantidadeEconomiasSocial;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Integer quantidadeEconomiasOutra;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Short percentualAbastecimento;    
    
    private Collection<ImovelTipoOcupanteQuantidade> quantidadesOcupantes;

	public final static Short INDICADOR_CONTA_RESPONSAVEL = new Short("1");
	public final static Short INDICADOR_CONTA_IMOVEL = new Short("2");
	public final static Short INDICADOR_CONTA_NAO_PAGAVEL_PARA_IMOVEL_PAGAVEL_PARA_RESPONSAVEL = new Short("3");
	public final static Short IMOVEL_CONDOMINIO = new Short("1");
	public final static Short IMOVEL_NAO_CONDOMINIO = new Short("2");
	public final static Short IMOVEL_EXCLUIDO = new Short("1");
	public final static Short INDICADOR_JARDIM_SIM = new Short("1");
	public final static Short INDICADOR_DEBITO_AUTOMATICO = new Short("1");
	public final static Short INDICADOR_NAO_DEBITO_AUTOMATICO = new Short("2");

	public Imovel(
			short lote,
			short subLote,
			Short testadaLote,
			String numeroImovel,
			String complementoEndereco,
			BigDecimal areaConstruida,
			Short indicadorImovelCondominio,
			BigDecimal volumeReservatorioSuperior,
			BigDecimal volumeReservatorioInferior,
			BigDecimal volumePiscina,
			Date dataSupressaoParcial,
			Date dataInfracao,
			Short numeroPontosUtilizacao,
			Short numeroMorador,
			Short numeroRetificacao,
			Short numeroLeituraExcecao,
			Short numeroParcelamento,
			Short numeroReparcelamento,
			Short diaVencimento,
			String numeroIptu,
			Long numeroCelpe,
			Short indicadorConta,
			Short indicadorEmissaoExtratoFaturamento,
			Short indicadorDebitoConta,
			Short indicadorExclusao,
			String coordenadaX,
			String coordenadaY,
			LigacaoEsgoto ligacaoEsgoto,
			LigacaoAgua ligacaoAgua,
			ImovelEnderecoAnterior imovelEnderecoAnterior,
			gcom.cadastro.imovel.Imovel imovelPrincipal,
			gcom.cadastro.imovel.Imovel imovelCondominio,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico,
			LeituraAnormalidade leituraAnormalidade,
			gcom.cadastro.imovel.EloAnormalidade eloAnormalidade,
			SetorComercial setorComercial,
			gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa,
			gcom.cadastro.imovel.PavimentoCalcada pavimentoCalcada,
			gcom.cadastro.imovel.ImovelPerfil imovelPerfil,
			gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaSuperior,
			gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaInferior,
			Localidade localidade, Quadra quadra,
			CobrancaSituacaoTipo cobrancaSituacaoTipo,
			gcom.cadastro.imovel.PavimentoRua pavimentoRua,
			EnderecoReferencia enderecoReferencia,
			gcom.cadastro.imovel.CadastroOcorrencia cadastroOcorrencia,
			gcom.cadastro.imovel.PocoTipo pocoTipo,
			LigacaoAguaSituacao ligacaoAguaSituacao,
			gcom.cadastro.imovel.Despejo despejo,
			FaturamentoSituacaoTipo faturamentoSituacaoTipo,
			gcom.cadastro.imovel.FonteAbastecimento fonteAbastecimento,
			gcom.cadastro.imovel.PiscinaVolumeFaixa piscinaVolumeFaixa,
			Date ultimaAlteracao, LogradouroCep logradouroCep) {
		this.lote = lote;
		this.subLote = subLote;
		this.testadaLote = testadaLote;
		this.numeroImovel = numeroImovel;
		this.complementoEndereco = complementoEndereco;
		this.areaConstruida = areaConstruida;
		this.indicadorImovelCondominio = indicadorImovelCondominio;
		this.volumeReservatorioSuperior = volumeReservatorioSuperior;
		this.volumeReservatorioInferior = volumeReservatorioInferior;
		this.volumePiscina = volumePiscina;
		this.dataSupressaoParcial = dataSupressaoParcial;
		this.dataInfracao = dataInfracao;
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
		this.numeroMorador = numeroMorador;
		this.numeroRetificacao = numeroRetificacao;
		this.numeroLeituraExcecao = numeroLeituraExcecao;
		this.numeroParcelamento = numeroParcelamento;
		this.numeroReparcelamento = numeroReparcelamento;
		this.diaVencimento = diaVencimento;
		this.numeroIptu = numeroIptu;
		this.numeroCelpe = numeroCelpe;
		this.indicadorConta = indicadorConta;
		this.indicadorEmissaoExtratoFaturamento = indicadorEmissaoExtratoFaturamento;
		this.indicadorDebitoConta = indicadorDebitoConta;
		this.indicadorExclusao = indicadorExclusao;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.ligacaoEsgoto = ligacaoEsgoto;
		this.ligacaoAgua = ligacaoAgua;
		this.imovelEnderecoAnterior = imovelEnderecoAnterior;
		this.imovelPrincipal = imovelPrincipal;
		this.imovelCondominio = imovelCondominio;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
		this.leituraAnormalidade = leituraAnormalidade;
		this.eloAnormalidade = eloAnormalidade;
		this.setorComercial = setorComercial;
		this.areaConstruidaFaixa = areaConstruidaFaixa;
		this.pavimentoCalcada = pavimentoCalcada;
		this.imovelPerfil = imovelPerfil;
		this.reservatorioVolumeFaixaSuperior = reservatorioVolumeFaixaSuperior;
		this.reservatorioVolumeFaixaInferior = reservatorioVolumeFaixaInferior;
		this.localidade = localidade;
		this.quadra = quadra;
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
		this.pavimentoRua = pavimentoRua;
		this.enderecoReferencia = enderecoReferencia;
		this.cadastroOcorrencia = cadastroOcorrencia;
		this.pocoTipo = pocoTipo;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.despejo = despejo;
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
		this.fonteAbastecimento = fonteAbastecimento;
		this.piscinaVolumeFaixa = piscinaVolumeFaixa;
		this.ultimaAlteracao = ultimaAlteracao;
		this.logradouroCep = logradouroCep;
	}

	public Imovel(Integer id, String numeroImovel, LogradouroCep logradouroCep,
			Quadra quadra, EnderecoReferencia enderecoReferencia,
			String complementoEndereco, SetorComercial setorComercial,
			Localidade localidade) {
		this.id = id;
		this.numeroImovel = numeroImovel;
		this.logradouroCep = logradouroCep;
		this.quadra = quadra;
		this.enderecoReferencia = enderecoReferencia;
		this.complementoEndereco = complementoEndereco;
		this.setorComercial = setorComercial;
		this.localidade = localidade;
	}
	
	public Imovel(Integer id) {
		this.id = id;
	}

	public Imovel(Integer id, String numeroImovel, LogradouroCep logradouroCep,
			LogradouroBairro logradouroBairro, Quadra quadra,
			EnderecoReferencia enderecoReferencia, String complementoEndereco,
			SetorComercial setorComercial, Localidade localidade) {
		this.id = id;
		this.numeroImovel = numeroImovel;
		this.logradouroCep = logradouroCep;
		this.logradouroBairro = logradouroBairro;
		this.quadra = quadra;
		this.enderecoReferencia = enderecoReferencia;
		this.complementoEndereco = complementoEndereco;
		this.setorComercial = setorComercial;
		this.localidade = localidade;
	}

	public Imovel(Integer id, String numeroImovel, LogradouroCep logradouroCep,
			LogradouroBairro logradouroBairro, Quadra quadra,
			EnderecoReferencia enderecoReferencia, String complementoEndereco,
			SetorComercial setorComercial, Localidade localidade,
			Date ultimaAlteracao) {
		this.id = id;
		this.numeroImovel = numeroImovel;
		this.logradouroCep = logradouroCep;
		this.logradouroBairro = logradouroBairro;
		this.quadra = quadra;
		this.enderecoReferencia = enderecoReferencia;
		this.complementoEndereco = complementoEndereco;
		this.setorComercial = setorComercial;
		this.localidade = localidade;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Imovel(
			short lote,
			short subLote,
			Short testadaLote,
			Short numeroReparcelamentoConsecutivos,
			String numeroImovel,
			String complementoEndereco,
			BigDecimal areaConstruida,
			Short indicadorImovelCondominio,
			BigDecimal volumeReservatorioSuperior,
			BigDecimal volumeReservatorioInferior,
			BigDecimal volumePiscina,
			Date dataSupressaoParcial,
			Date dataInfracao,
			Short numeroPontosUtilizacao,
			Short numeroMorador,
			Short numeroRetificacao,
			Short numeroLeituraExcecao,
			Short numeroParcelamento,
			Short numeroReparcelamento,
			Short diaVencimento,
			String numeroIptu,
			Long numeroCelpe,
			Short indicadorConta,
			Short indicadorEmissaoExtratoFaturamento,
			Short indicadorDebitoConta,
			Short indicadorExclusao,
			String coordenadaX,
			String coordenadaY,
			LigacaoEsgoto ligacaoEsgoto,
			LigacaoAgua ligacaoAgua,
			ImovelEnderecoAnterior imovelEnderecoAnterior,
			gcom.cadastro.imovel.Imovel imovelPrincipal,
			gcom.cadastro.imovel.Imovel imovelCondominio,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico,
			LeituraAnormalidade leituraAnormalidade,
			gcom.cadastro.imovel.EloAnormalidade eloAnormalidade,
			SetorComercial setorComercial,
			gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa,
			gcom.cadastro.imovel.PavimentoCalcada pavimentoCalcada,
			gcom.cadastro.imovel.ImovelPerfil imovelPerfil,
			gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaSuperior,
			gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaInferior,
			Localidade localidade, Quadra quadra,
			CobrancaSituacaoTipo cobrancaSituacaoTipo,
			gcom.cadastro.imovel.PavimentoRua pavimentoRua,
			EnderecoReferencia enderecoReferencia,
			gcom.cadastro.imovel.CadastroOcorrencia cadastroOcorrencia,
			gcom.cadastro.imovel.PocoTipo pocoTipo,
			LigacaoAguaSituacao ligacaoAguaSituacao,
			gcom.cadastro.imovel.Despejo despejo,
			FaturamentoSituacaoTipo faturamentoSituacaoTipo,
			gcom.cadastro.imovel.FonteAbastecimento fonteAbastecimento,
			gcom.cadastro.imovel.PiscinaVolumeFaixa piscinaVolumeFaixa,
			FaturamentoTipo faturamentoTipo, Date ultimaAlteracao,
			LogradouroCep logradouroCep) {
		this.lote = lote;
		this.subLote = subLote;
		this.testadaLote = testadaLote;
		this.numeroImovel = numeroImovel;
		this.numeroReparcelamentoConsecutivos = numeroReparcelamentoConsecutivos;
		this.complementoEndereco = complementoEndereco;
		this.areaConstruida = areaConstruida;
		this.indicadorImovelCondominio = indicadorImovelCondominio;
		this.volumeReservatorioSuperior = volumeReservatorioSuperior;
		this.volumeReservatorioInferior = volumeReservatorioInferior;
		this.volumePiscina = volumePiscina;
		this.dataSupressaoParcial = dataSupressaoParcial;
		this.dataInfracao = dataInfracao;
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
		this.numeroMorador = numeroMorador;
		this.numeroRetificacao = numeroRetificacao;
		this.numeroLeituraExcecao = numeroLeituraExcecao;
		this.numeroParcelamento = numeroParcelamento;
		this.numeroReparcelamento = numeroReparcelamento;
		this.diaVencimento = diaVencimento;
		this.numeroIptu = numeroIptu;
		this.numeroCelpe = numeroCelpe;
		this.indicadorConta = indicadorConta;
		this.indicadorEmissaoExtratoFaturamento = indicadorEmissaoExtratoFaturamento;
		this.indicadorDebitoConta = indicadorDebitoConta;
		this.indicadorExclusao = indicadorExclusao;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.ligacaoEsgoto = ligacaoEsgoto;
		this.ligacaoAgua = ligacaoAgua;
		this.imovelEnderecoAnterior = imovelEnderecoAnterior;
		this.imovelPrincipal = imovelPrincipal;
		this.imovelCondominio = imovelCondominio;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
		this.leituraAnormalidade = leituraAnormalidade;
		this.eloAnormalidade = eloAnormalidade;
		this.setorComercial = setorComercial;
		this.areaConstruidaFaixa = areaConstruidaFaixa;
		this.pavimentoCalcada = pavimentoCalcada;
		this.imovelPerfil = imovelPerfil;
		this.reservatorioVolumeFaixaSuperior = reservatorioVolumeFaixaSuperior;
		this.reservatorioVolumeFaixaInferior = reservatorioVolumeFaixaInferior;
		this.localidade = localidade;
		this.quadra = quadra;
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
		this.pavimentoRua = pavimentoRua;
		this.enderecoReferencia = enderecoReferencia;
		this.cadastroOcorrencia = cadastroOcorrencia;
		this.pocoTipo = pocoTipo;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.despejo = despejo;
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
		this.fonteAbastecimento = fonteAbastecimento;
		this.piscinaVolumeFaixa = piscinaVolumeFaixa;
		this.faturamentoTipo = faturamentoTipo;
		this.ultimaAlteracao = ultimaAlteracao;
		this.logradouroCep = logradouroCep;
	}

	public Imovel(Integer id, String numeroImovel, Quadra quadra,
			EnderecoReferencia enderecoReferencia, String complementoEndereco,
			FaturamentoTipo faturamentoTipo) {
		this.id = id;
		this.numeroImovel = numeroImovel;
		this.quadra = quadra;
		this.enderecoReferencia = enderecoReferencia;
		this.complementoEndereco = complementoEndereco;
		this.faturamentoTipo = faturamentoTipo;
	}

	public Imovel() {
	}

	public Imovel(Localidade localidade, SetorComercial setor, Quadra quadra, short lote, short sublote ) {
		this.localidade = localidade;
		this.setorComercial = setor;
		this.quadra = quadra;
		this.lote = lote;
		this.subLote = sublote;
	}
	public Imovel(
			short lote,
			short subLote,
			Short testadaLote,
			String numeroImovel,
			String complementoEndereco,
			Short numeroPontosUtilizacao,
			Short numeroMorador,
			gcom.cadastro.imovel.Imovel imovelPrincipal,
			gcom.cadastro.imovel.Imovel imovelCondominio,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico,
			LeituraAnormalidade leituraAnormalidade,
			gcom.cadastro.imovel.EloAnormalidade eloAnormalidade,
			SetorComercial setorComercial,
			gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa,
			gcom.cadastro.imovel.PavimentoCalcada pavimentoCalcada,
			gcom.cadastro.imovel.ImovelPerfil imovelPerfil,
			gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaSuperior,
			gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaInferior,
			Localidade localidade, Quadra quadra,
			CobrancaSituacaoTipo cobrancaSituacaoTipo,
			gcom.cadastro.imovel.PavimentoRua pavimentoRua,
			EnderecoReferencia enderecoImovelReferencia,
			gcom.cadastro.imovel.CadastroOcorrencia cadastroOcorrencia,
			gcom.cadastro.imovel.PocoTipo pocoTipo,
			LigacaoAguaSituacao ligacaoAguaSituacao,
			gcom.cadastro.imovel.Despejo despejo,
			FaturamentoSituacaoTipo faturamentoSituacaoTipo,
			gcom.cadastro.imovel.FonteAbastecimento fonteAbastecimento,
			gcom.cadastro.imovel.PiscinaVolumeFaixa piscinaVolumeFaixa) {
		this.lote = lote;
		this.subLote = subLote;
		this.testadaLote = testadaLote;
		this.numeroImovel = numeroImovel;
		this.complementoEndereco = complementoEndereco;
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
		this.numeroMorador = numeroMorador;
		this.imovelPrincipal = imovelPrincipal;
		this.imovelCondominio = imovelCondominio;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
		this.leituraAnormalidade = leituraAnormalidade;
		this.eloAnormalidade = eloAnormalidade;
		this.setorComercial = setorComercial;
		this.areaConstruidaFaixa = areaConstruidaFaixa;
		this.pavimentoCalcada = pavimentoCalcada;
		this.imovelPerfil = imovelPerfil;
		this.reservatorioVolumeFaixaSuperior = reservatorioVolumeFaixaSuperior;
		this.reservatorioVolumeFaixaInferior = reservatorioVolumeFaixaInferior;
		this.localidade = localidade;
		this.quadra = quadra;
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
		this.pavimentoRua = pavimentoRua;
		this.enderecoReferencia = enderecoImovelReferencia;
		this.cadastroOcorrencia = cadastroOcorrencia;
		this.pocoTipo = pocoTipo;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.despejo = despejo;
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
		this.fonteAbastecimento = fonteAbastecimento;
		this.piscinaVolumeFaixa = piscinaVolumeFaixa;

	}

	public Imovel(Integer id, gcom.cadastro.localidade.Localidade localidade,
			gcom.cadastro.localidade.SetorComercial setorComercial,
			gcom.cadastro.localidade.Quadra quadra, short lote, short subLote,
			FaturamentoSituacaoTipo faturamentoSituacaoTipo) {

		this.id = id;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.quadra = quadra;
		this.lote = lote;
		this.subLote = subLote;
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getLote() {
		return this.lote;
	}

	public void setLote(short lote) {
		this.lote = lote;
	}

	public short getSubLote() {
		return this.subLote;
	}

	public void setSubLote(short subLote) {
		this.subLote = subLote;
	}

	public Short getTestadaLote() {
		return this.testadaLote;
	}

	public void setTestadaLote(Short testadaLote) {
		this.testadaLote = testadaLote;
	}

	public String getNumeroImovel() {
		return this.numeroImovel;
	}

	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	public String getComplementoEndereco() {
		return this.complementoEndereco;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public BigDecimal getAreaConstruida() {
		return this.areaConstruida;
	}

	public void setAreaConstruida(BigDecimal areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	public Short getIndicadorImovelCondominio() {
		return this.indicadorImovelCondominio;
	}

	public void setIndicadorImovelCondominio(Short indicadorImovelCondominio) {
		this.indicadorImovelCondominio = indicadorImovelCondominio;
	}

	public BigDecimal getVolumeReservatorioSuperior() {
		return this.volumeReservatorioSuperior;
	}

	public void setVolumeReservatorioSuperior(
			BigDecimal volumeReservatorioSuperior) {
		this.volumeReservatorioSuperior = volumeReservatorioSuperior;
	}

	public BigDecimal getVolumeReservatorioInferior() {
		return this.volumeReservatorioInferior;
	}

	public void setVolumeReservatorioInferior(
			BigDecimal volumeReservatorioInferior) {
		this.volumeReservatorioInferior = volumeReservatorioInferior;
	}

	public BigDecimal getVolumePiscina() {
		return this.volumePiscina;
	}

	public void setVolumePiscina(BigDecimal volumePiscina) {
		this.volumePiscina = volumePiscina;
	}

	public Date getDataSupressaoParcial() {
		return this.dataSupressaoParcial;
	}

	public void setDataSupressaoParcial(Date dataSupressaoParcial) {
		this.dataSupressaoParcial = dataSupressaoParcial;
	}

	public Date getDataInfracao() {
		return this.dataInfracao;
	}

	public void setDataInfracao(Date dataInfracao) {
		this.dataInfracao = dataInfracao;
	}

	public Short getNumeroPontosUtilizacao() {
		return this.numeroPontosUtilizacao;
	}

	public void setNumeroPontosUtilizacao(Short numeroPontosUtilizacao) {
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
	}

	public Short getNumeroMorador() {
		return this.numeroMorador;
	}

	public void setNumeroMorador(Short numeroMorador) {
		this.numeroMorador = numeroMorador;
	}

	public Short getNumeroRetificacao() {
		return this.numeroRetificacao;
	}

	public void setNumeroRetificacao(Short numeroRetificacao) {
		this.numeroRetificacao = numeroRetificacao;
	}

	public Short getNumeroLeituraExcecao() {
		return this.numeroLeituraExcecao;
	}

	public void setNumeroLeituraExcecao(Short numeroLeituraExcecao) {
		this.numeroLeituraExcecao = numeroLeituraExcecao;
	}

	public Short getNumeroParcelamento() {
		return this.numeroParcelamento;
	}

	public void setNumeroParcelamento(Short numeroParcelamento) {
		this.numeroParcelamento = numeroParcelamento;
	}

	public Short getNumeroReparcelamento() {
		return this.numeroReparcelamento;
	}

	public void setNumeroReparcelamento(Short numeroReparcelamento) {
		this.numeroReparcelamento = numeroReparcelamento;
	}

	public Short getDiaVencimento() {
		return this.diaVencimento;
	}

	public void setDiaVencimento(Short diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public String getNumeroIptu() {
		return this.numeroIptu;
	}

	public void setNumeroIptu(String numeroIptu) {
		this.numeroIptu = numeroIptu;
	}

	public Long getNumeroCelpe() {
		return this.numeroCelpe;
	}

	public void setNumeroCelpe(Long numeroCelpe) {
		this.numeroCelpe = numeroCelpe;
	}

	public Short getIndicadorConta() {
		return this.indicadorConta;
	}

	public void setIndicadorConta(Short indicadorConta) {
		this.indicadorConta = indicadorConta;
	}

	public Short getIndicadorEmissaoExtratoFaturamento() {
		return this.indicadorEmissaoExtratoFaturamento;
	}

	public void setIndicadorEmissaoExtratoFaturamento(
			Short indicadorEmissaoExtratoFaturamento) {
		this.indicadorEmissaoExtratoFaturamento = indicadorEmissaoExtratoFaturamento;
	}

	public Short getIndicadorDebitoConta() {
		return this.indicadorDebitoConta;
	}

	public void setIndicadorDebitoConta(Short indicadorDebitoConta) {
		this.indicadorDebitoConta = indicadorDebitoConta;
	}

	public Short getIndicadorExclusao() {
		return this.indicadorExclusao;
	}

	public void setIndicadorExclusao(Short indicadorExclusao) {
		this.indicadorExclusao = indicadorExclusao;
	}

	public String getCoordenadaX() {
		return this.coordenadaX;
	}

	public void setCoordenadaX(String coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	public String getCoordenadaY() {
		return this.coordenadaY;
	}

	public void setCoordenadaY(String coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

	public LigacaoEsgoto getLigacaoEsgoto() {
		return this.ligacaoEsgoto;
	}

	public void setLigacaoEsgoto(LigacaoEsgoto ligacaoEsgoto) {
		this.ligacaoEsgoto = ligacaoEsgoto;
	}

	public LigacaoAgua getLigacaoAgua() {
		return this.ligacaoAgua;
	}

	public void setLigacaoAgua(LigacaoAgua ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}

	public ImovelEnderecoAnterior getImovelEnderecoAnterior() {
		return this.imovelEnderecoAnterior;
	}

	public void setImovelEnderecoAnterior(
			ImovelEnderecoAnterior imovelEnderecoAnterior) {
		this.imovelEnderecoAnterior = imovelEnderecoAnterior;
	}

	public gcom.cadastro.imovel.Imovel getImovelPrincipal() {
		return this.imovelPrincipal;
	}

	public void setImovelPrincipal(gcom.cadastro.imovel.Imovel imovelPrincipal) {
		this.imovelPrincipal = imovelPrincipal;
	}

	public gcom.cadastro.imovel.Imovel getImovelCondominio() {
		return this.imovelCondominio;
	}

	public void setImovelCondominio(gcom.cadastro.imovel.Imovel imovelCondominio) {
		this.imovelCondominio = imovelCondominio;
	}

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return this.ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public HidrometroInstalacaoHistorico getHidrometroInstalacaoHistorico() {
		return this.hidrometroInstalacaoHistorico;
	}

	public void setHidrometroInstalacaoHistorico(
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico) {
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
	}

	public LeituraAnormalidade getLeituraAnormalidade() {
		return this.leituraAnormalidade;
	}

	public void setLeituraAnormalidade(LeituraAnormalidade leituraAnormalidade) {
		this.leituraAnormalidade = leituraAnormalidade;
	}

	public gcom.cadastro.imovel.EloAnormalidade getEloAnormalidade() {
		return this.eloAnormalidade;
	}

	public void setEloAnormalidade(
			gcom.cadastro.imovel.EloAnormalidade eloAnormalidade) {
		this.eloAnormalidade = eloAnormalidade;
	}

	public SetorComercial getSetorComercial() {
		return this.setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public gcom.cadastro.imovel.AreaConstruidaFaixa getAreaConstruidaFaixa() {
		return this.areaConstruidaFaixa;
	}

	public void setAreaConstruidaFaixa(
			gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa) {
		this.areaConstruidaFaixa = areaConstruidaFaixa;
	}

	public gcom.cadastro.imovel.PavimentoCalcada getPavimentoCalcada() {
		return this.pavimentoCalcada;
	}

	public void setPavimentoCalcada(
			gcom.cadastro.imovel.PavimentoCalcada pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	public gcom.cadastro.imovel.ImovelPerfil getImovelPerfil() {
		return this.imovelPerfil;
	}

	public void setImovelPerfil(gcom.cadastro.imovel.ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public gcom.cadastro.imovel.ReservatorioVolumeFaixa getReservatorioVolumeFaixaSuperior() {
		return this.reservatorioVolumeFaixaSuperior;
	}

	public void setReservatorioVolumeFaixaSuperior(
			gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaSuperior) {
		this.reservatorioVolumeFaixaSuperior = reservatorioVolumeFaixaSuperior;
	}

	public gcom.cadastro.imovel.ReservatorioVolumeFaixa getReservatorioVolumeFaixaInferior() {
		return this.reservatorioVolumeFaixaInferior;
	}

	public void setReservatorioVolumeFaixaInferior(
			gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaInferior) {
		this.reservatorioVolumeFaixaInferior = reservatorioVolumeFaixaInferior;
	}

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Quadra getQuadra() {
		return this.quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public CobrancaSituacaoTipo getCobrancaSituacaoTipo() {
		return this.cobrancaSituacaoTipo;
	}

	public void setCobrancaSituacaoTipo(
			CobrancaSituacaoTipo cobrancaSituacaoTipo) {
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
	}

	public gcom.cadastro.imovel.PavimentoRua getPavimentoRua() {
		return this.pavimentoRua;
	}

	public void setPavimentoRua(gcom.cadastro.imovel.PavimentoRua pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}

	public EnderecoReferencia getEnderecoReferencia() {
		return this.enderecoReferencia;
	}

	public void setEnderecoReferencia(EnderecoReferencia enderecoReferencia) {
		this.enderecoReferencia = enderecoReferencia;
	}

	public gcom.cadastro.imovel.CadastroOcorrencia getCadastroOcorrencia() {
		return this.cadastroOcorrencia;
	}

	public void setCadastroOcorrencia(
			gcom.cadastro.imovel.CadastroOcorrencia cadastroOcorrencia) {
		this.cadastroOcorrencia = cadastroOcorrencia;
	}

	public gcom.cadastro.imovel.PocoTipo getPocoTipo() {
		return this.pocoTipo;
	}

	public void setPocoTipo(gcom.cadastro.imovel.PocoTipo pocoTipo) {
		this.pocoTipo = pocoTipo;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return this.ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public gcom.cadastro.imovel.Despejo getDespejo() {
		return this.despejo;
	}

	public void setDespejo(gcom.cadastro.imovel.Despejo despejo) {
		this.despejo = despejo;
	}

	public FaturamentoSituacaoTipo getFaturamentoSituacaoTipo() {
		return this.faturamentoSituacaoTipo;
	}

	public void setFaturamentoSituacaoTipo(
			FaturamentoSituacaoTipo faturamentoSituacaoTipo) {
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
	}

	public gcom.cadastro.imovel.FonteAbastecimento getFonteAbastecimento() {
		return this.fonteAbastecimento;
	}

	public void setFonteAbastecimento(
			gcom.cadastro.imovel.FonteAbastecimento fonteAbastecimento) {
		this.fonteAbastecimento = fonteAbastecimento;
	}

	public gcom.cadastro.imovel.PiscinaVolumeFaixa getPiscinaVolumeFaixa() {
		return this.piscinaVolumeFaixa;
	}

	public void setPiscinaVolumeFaixa(
			gcom.cadastro.imovel.PiscinaVolumeFaixa piscinaVolumeFaixa) {
		this.piscinaVolumeFaixa = piscinaVolumeFaixa;
	}

	public FaturamentoSituacaoMotivo getFaturamentoSituacaoMotivo() {
		return this.faturamentoSituacaoMotivo;
	}

	public void setFaturamentoSituacaoMotivo(
			FaturamentoSituacaoMotivo faturamentoSituacaoMotivo) {
		this.faturamentoSituacaoMotivo = faturamentoSituacaoMotivo;
	}

	public Short getIndicadorSuspensaoAbastecimento() {
		return this.indicadorSuspensaoAbastecimento;
	}

	public void setIndicadorSuspensaoAbastecimento(
			Short indicadorSuspensaoAbastecimento) {
		this.indicadorSuspensaoAbastecimento = indicadorSuspensaoAbastecimento;
	}

	public String getEnderecoFormatado() {
		String endereco = null;

		// verifica se o logradouro do imovel é diferente de null
		if (this.getLogradouroCep() != null
				&& this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(
						new Integer("0"))) {

			// verifica se o logradouro tipo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().equals("")) {
				// concatena o logradouro tipo do imovel
				endereco = this.getLogradouroCep().getLogradouro()
						.getLogradouroTipo().getDescricao().trim();
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTitulo().equals("")) {
				// concatena o logradouro titulo do imovel
				endereco = endereco
						+ " "
						+ this.getLogradouroCep().getLogradouro()
								.getLogradouroTitulo().getDescricao().trim();
			}

			// concatena o logradouro do imovel
			endereco = endereco + " "
					+ this.getLogradouroCep().getLogradouro().getNome().trim();

			if (this.getEnderecoReferencia() != null
					&& !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricao() != null
						&& !this.getEnderecoReferencia().getDescricao().equals(
								"")) {
					endereco = endereco
							+ " - "
							+ this.getEnderecoReferencia().getDescricao()
									.trim();
				}
			}
			if (this.getNumeroImovel() != null
					&& !this.getNumeroImovel().equals("")) {
				// concate o numero do imovel
				endereco = endereco + " - " + this.getNumeroImovel().trim();
			}

			if (this.getComplementoEndereco() != null
					&& !this.getComplementoEndereco().equalsIgnoreCase("")) {
				endereco = endereco + " - "
						+ this.getComplementoEndereco().trim();
			}

			if (this.getLogradouroBairro() != null
					&& this.getLogradouroBairro().getBairro() != null
					&& this.getLogradouroBairro().getBairro().getId()
							.intValue() != 0) {
				endereco = endereco
						+ " - "
						+ this.getLogradouroBairro().getBairro().getNome()
								.trim();

				if (this.getLogradouroBairro().getBairro().getMunicipio() != null
						&& this.getLogradouroBairro().getBairro()
								.getMunicipio().getId().intValue() != 0) {
					endereco = endereco
							+ " "
							+ this.getLogradouroBairro().getBairro()
									.getMunicipio().getNome().trim();
				}

				if (this.getLogradouroBairro().getBairro().getMunicipio()
						.getUnidadeFederacao() != null
						&& this.getLogradouroBairro().getBairro()
								.getMunicipio().getUnidadeFederacao().getId()
								.intValue() != 0) {
					endereco = endereco
							+ " "
							+ this.getLogradouroBairro().getBairro()
									.getMunicipio().getUnidadeFederacao()
									.getSigla().trim();
				}
			}

			if (this.getLogradouroCep() != null
					&& this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado do imóvel
				endereco = endereco
						+ " "
						+ this.getLogradouroCep().getCep().getCepFormatado()
								.trim();
			}
			
			if (this.getPerimetroInicial() != null) {
				endereco = endereco + " ENTRE " + this.getPerimetroInicial().getDescricaoFormatada() + " E " 
						+ this.getPerimetroFinal().getDescricaoFormatada();
			}

		}

		return endereco;
	}

	public String getEnderecoFormatadoAbreviado() {
		String endereco = null;

		// verifica se o logradouro do imovel é diferente de null
		if (this.getLogradouroCep() != null
				&& this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(
						new Integer("0"))) {

			// verifica se o logradouro tipo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().equals("")) {
				if (this.getLogradouroCep().getLogradouro().getLogradouroTipo()
						.getDescricaoAbreviada() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTipo().getDescricaoAbreviada()
								.equals("")) {
					// concatena o logradouro tipo do imovel
					endereco = this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().getDescricaoAbreviada().trim();
				}
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTitulo().equals("")) {
				if (this.getLogradouroCep().getLogradouro()
						.getLogradouroTitulo().getDescricaoAbreviada() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTitulo().getDescricaoAbreviada()
								.equals("")) {
					// concatena o logradouro titulo do imovel
					endereco = endereco
							+ " "
							+ this.getLogradouroCep().getLogradouro()
									.getLogradouroTitulo()
									.getDescricaoAbreviada().trim();
				}
			}

			// concatena o logradouro do imovel
			endereco = endereco + " "
					+ this.getLogradouroCep().getLogradouro().getNome().trim();

			if (this.getEnderecoReferencia() != null
					&& !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricaoAbreviada() != null
						&& !this.getEnderecoReferencia()
								.getDescricaoAbreviada().equals("")) {
					endereco = endereco
							+ ", "
							+ this.getEnderecoReferencia()
									.getDescricaoAbreviada().trim();
				}
			}

			// concate o numero do imovel
			endereco = endereco + " " + this.getNumeroImovel().trim();

			if (this.getComplementoEndereco() != null
					&& !this.getComplementoEndereco().equalsIgnoreCase("")) {
				endereco = endereco + " - "
						+ this.getComplementoEndereco().trim();
			}

			if (this.getLogradouroBairro() != null
					&& this.getLogradouroBairro().getBairro() != null
					&& this.getLogradouroBairro().getBairro().getId()
							.intValue() != 0) {
				endereco = endereco
						+ " - "
						+ this.getLogradouroBairro().getBairro().getNome()
								.trim();

				if (this.getLogradouroBairro().getBairro().getMunicipio() != null
						&& this.getLogradouroBairro().getBairro()
								.getMunicipio().getId().intValue() != 0) {
					endereco = endereco
							+ " "
							+ this.getLogradouroBairro().getBairro()
									.getMunicipio().getNome().trim();
				}

				if (this.getLogradouroBairro().getBairro().getMunicipio()
						.getUnidadeFederacao() != null
						&& this.getLogradouroBairro().getBairro()
								.getMunicipio().getUnidadeFederacao().getId()
								.intValue() != 0) {
					endereco = endereco
							+ " "
							+ this.getLogradouroBairro().getBairro()
									.getMunicipio().getUnidadeFederacao()
									.getSigla().trim();
				}
			}

			if (this.getLogradouroCep() != null
					&& this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado do imóvel
				endereco = endereco
						+ " "
						+ this.getLogradouroCep().getCep().getCepFormatado()
								.trim();
			}
			
			if (this.getPerimetroInicial() != null) {
				endereco = endereco + " ENTRE " + this.getPerimetroInicial().getDescricaoFormatada() + " E " 
						+ this.getPerimetroFinal().getDescricaoFormatada();
			}

		}

		return endereco;
	}
	
	public String getEnderecoTipoTituloLogradouro() {
		String endereco = null;

		// verifica se o logradouro do imovel é diferente de null
		if (this.getLogradouroCep() != null
				&& this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(
						new Integer("0"))) {
			
			// verifica se o logradouro tipo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().equals("")) {
				if (this.getLogradouroCep().getLogradouro().getLogradouroTipo()
						.getDescricao() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTipo().getDescricao()
								.equals("")) {
					// concatena o logradouro tipo do imovel
					endereco = this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().getDescricao().trim();
				}
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTitulo().equals("")) {
				if (this.getLogradouroCep().getLogradouro()
						.getLogradouroTitulo().getDescricao() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTitulo().getDescricao()
								.equals("")) {
					// concatena o logradouro titulo do imovel
					endereco = endereco
							+ " "
							+ this.getLogradouroCep().getLogradouro()
									.getLogradouroTitulo()
									.getDescricao().trim();
				}
			}

			// concatena o logradouro do imovel
				endereco = endereco + " "
						+ this.getLogradouroCep().getLogradouro().getNome().trim();
			
			if (this.getEnderecoReferencia() != null
					&& !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricaoAbreviada() != null
						&& !this.getEnderecoReferencia().getDescricao().equals(
								"")) {
					endereco = endereco + ", "
							+ this.getEnderecoReferencia().getDescricao().trim();
				}
			}

		}

		return endereco;
	}

	public String getInscricaoFormatada() {
		String inscricao = null;

		String zeroUm = "0";
		String zeroDois = "00";
		String zeroTres = "000";

		String localidade, setorComercial, quadra, lote, subLote;

		localidade = String.valueOf(this.getLocalidade().getId().intValue());
		setorComercial = String.valueOf(this.getSetorComercial().getCodigo());
		quadra = String.valueOf(this.getQuadra().getNumeroQuadra());
		lote = String.valueOf(this.getLote());
		subLote = String.valueOf(this.getSubLote());

		if (String.valueOf(this.getLocalidade().getId().intValue()).length() < 3
				&& String.valueOf(this.getLocalidade().getId().intValue())
						.length() > 1) {
			localidade = zeroUm + this.getLocalidade().getId().intValue();
		} else if (String.valueOf(this.getLocalidade().getId().intValue())
				.length() < 3) {
			localidade = zeroDois + this.getLocalidade().getId().intValue();
		}

		if (String.valueOf(this.getSetorComercial().getCodigo()).length() < 3
				&& String.valueOf(this.getSetorComercial().getCodigo())
						.length() > 1) {
			setorComercial = zeroUm + this.getSetorComercial().getCodigo();
		} else if (String.valueOf(this.getSetorComercial().getCodigo())
				.length() < 3) {
			setorComercial = zeroDois + this.getSetorComercial().getCodigo();
		}
		
		if (String.valueOf(this.getQuadra().getNumeroQuadra()).length() < 3
				&& String.valueOf(this.getQuadra().getNumeroQuadra())
						.length() > 1) {
			quadra = zeroUm + this.getQuadra().getNumeroQuadra();
		} else if (String.valueOf(this.getQuadra().getNumeroQuadra())
				.length() < 3) {
			quadra = zeroDois + this.getQuadra().getNumeroQuadra();
		}

		if (String.valueOf(this.getLote()).length() < 4
				&& String.valueOf(this.getLote()).length() > 2) {
			lote = zeroUm + this.getLote();
		} else if (String.valueOf(this.getLote()).length() < 3
				&& String.valueOf(this.getLote()).length() > 1) {
			lote = zeroDois + this.getLote();
		} else if (String.valueOf(this.getLote()).length() < 2) {
			lote = zeroTres + this.getLote();
		}

		if (String.valueOf(this.getSubLote()).length() < 3
				&& String.valueOf(this.getSubLote()).length() > 1) {
			subLote = zeroUm + this.getSubLote();
		} else if (String.valueOf(this.getSubLote()).length() < 3) {
			subLote = zeroDois + this.getSubLote();
		}

		inscricao = localidade + "." + setorComercial + "." + quadra + "."
				+ lote + "." + subLote;

		return inscricao;
	}
	
	public String getInscricaoFormatadaSemPonto() {
		String inscricao = null;

		String zeroUm = "0";
		String zeroDois = "00";
		String zeroTres = "000";

		String localidade, setorComercial, quadra, lote, subLote;

		localidade = String.valueOf(this.getLocalidade().getId().intValue());
		setorComercial = String.valueOf(this.getSetorComercial().getCodigo());
		quadra = String.valueOf(this.getQuadra().getNumeroQuadra());
		lote = String.valueOf(this.getLote());
		subLote = String.valueOf(this.getSubLote());

		if (String.valueOf(this.getLocalidade().getId().intValue()).length() < 3
				&& String.valueOf(this.getLocalidade().getId().intValue())
						.length() > 1) {
			localidade = zeroUm + this.getLocalidade().getId().intValue();
		} else if (String.valueOf(this.getLocalidade().getId().intValue())
				.length() < 3) {
			localidade = zeroDois + this.getLocalidade().getId().intValue();
		}

		if (String.valueOf(this.getSetorComercial().getCodigo()).length() < 3
				&& String.valueOf(this.getSetorComercial().getCodigo())
						.length() > 1) {
			setorComercial = zeroUm + this.getSetorComercial().getCodigo();
		} else if (String.valueOf(this.getSetorComercial().getCodigo())
				.length() < 3) {
			setorComercial = zeroDois + this.getSetorComercial().getCodigo();
		}

		if (String.valueOf(this.getQuadra().getNumeroQuadra()).length() < 3
				&& String.valueOf(this.getQuadra().getNumeroQuadra())
						.length() > 1) {
			quadra = zeroUm + this.getQuadra().getNumeroQuadra();
		} else if (String.valueOf(this.getQuadra().getNumeroQuadra())
				.length() < 3) {
			quadra = zeroDois + this.getQuadra().getNumeroQuadra();
		}

		if (String.valueOf(this.getLote()).length() < 4
				&& String.valueOf(this.getLote()).length() > 2) {
			lote = zeroUm + this.getLote();
		} else if (String.valueOf(this.getLote()).length() < 3
				&& String.valueOf(this.getLote()).length() > 1) {
			lote = zeroDois + this.getLote();
		} else if (String.valueOf(this.getLote()).length() < 2) {
			lote = zeroTres + this.getLote();
		}

		if (String.valueOf(this.getSubLote()).length() < 3
				&& String.valueOf(this.getSubLote()).length() > 1) {
			subLote = zeroUm + this.getSubLote();
		} else if (String.valueOf(this.getSubLote()).length() < 3) {
			subLote = zeroDois + this.getSubLote();
		}

		inscricao = localidade + setorComercial + quadra + lote + subLote;

		return inscricao;
	}

	public String toString() {
		return "Imovel [id=" + id + "]";
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getLogradouroCep().getLogradouro())
				.append(getLocalidade()).append(getNumeroImovel()).append(
						getQuadra()).append(getSetorComercial()).append(
						getComplementoEndereco()).append(
						getLogradouroCep().getCep()).toHashCode();
	}

	public Set<ImovelSubcategoria> getImovelSubcategorias() {
		return imovelSubcategorias;
	}

	public void setImovelSubcategorias(Set<ImovelSubcategoria> imovelSubcategorias) {
		this.imovelSubcategorias = imovelSubcategorias;
	}

	@SuppressWarnings("rawtypes")
	public Set getClienteImoveis() {
		return clienteImoveis;
	}

	@SuppressWarnings("rawtypes")
	public void setClienteImoveis(Set clienteImoveis) {
		this.clienteImoveis = clienteImoveis;
	}

	public Short getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(Short quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public gcom.faturamento.consumotarifa.ConsumoTarifa getConsumoTarifa() {
		return consumoTarifa;
	}

	public void setConsumoTarifa(
			gcom.faturamento.consumotarifa.ConsumoTarifa consumoTarifa) {
		this.consumoTarifa = consumoTarifa;
	}

	public Set<MedicaoHistorico> getMedicaoHistoricos() {
		return medicaoHistoricos;
	}

	public void setMedicaoHistoricos(Set<MedicaoHistorico> medicaoHistoricos) {
		this.medicaoHistoricos = medicaoHistoricos;
	}

	public FaturamentoTipo getFaturamentoTipo() {
		return faturamentoTipo;
	}

	public void setFaturamentoTipo(FaturamentoTipo faturamentoTipo) {
		this.faturamentoTipo = faturamentoTipo;
	}

	public Short getNumeroReparcelamentoConsecutivos() {
		return numeroReparcelamentoConsecutivos;
	}

	public void setNumeroReparcelamentoConsecutivos(
			Short numeroReparcelamentoConsecutivos) {
		this.numeroReparcelamentoConsecutivos = numeroReparcelamentoConsecutivos;
	}

	public Set<ConsumoHistorico> getConsumosHistoricos() {
		return consumosHistoricos;
	}

	public void setConsumosHistoricos(Set<ConsumoHistorico> consumosHistoricos) {
		this.consumosHistoricos = consumosHistoricos;
	}

	public LogradouroBairro getLogradouroBairro() {
		return logradouroBairro;
	}

	public void setLogradouroBairro(LogradouroBairro logradouroBairro) {
		this.logradouroBairro = logradouroBairro;
	}

	public LogradouroCep getLogradouroCep() {
		return logradouroCep;
	}

	public void setLogradouroCep(LogradouroCep logradouroCep) {
		this.logradouroCep = logradouroCep;
	}

	public gcom.cadastro.imovel.ImovelTipoCobertura getImovelTipoCobertura() {
		return imovelTipoCobertura;
	}

	public void setImovelTipoCobertura(
			gcom.cadastro.imovel.ImovelTipoCobertura imovelTipoCobertura) {
		this.imovelTipoCobertura = imovelTipoCobertura;
	}

	public gcom.cadastro.imovel.ImovelTipoConstrucao getImovelTipoConstrucao() {
		return imovelTipoConstrucao;
	}

	public void setImovelTipoConstrucao(
			gcom.cadastro.imovel.ImovelTipoConstrucao imovelTipoConstrucao) {
		this.imovelTipoConstrucao = imovelTipoConstrucao;
	}

	public gcom.cadastro.imovel.ImovelTipoHabitacao getImovelTipoHabitacao() {
		return imovelTipoHabitacao;
	}

	public void setImovelTipoHabitacao(
			gcom.cadastro.imovel.ImovelTipoHabitacao imovelTipoHabitacao) {
		this.imovelTipoHabitacao = imovelTipoHabitacao;
	}

	public gcom.cadastro.imovel.ImovelTipoPropriedade getImovelTipoPropriedade() {
		return imovelTipoPropriedade;
	}

	public void setImovelTipoPropriedade(
			gcom.cadastro.imovel.ImovelTipoPropriedade imovelTipoPropriedade) {
		this.imovelTipoPropriedade = imovelTipoPropriedade;
	}

	public Filtro retornaFiltro() {
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,this.getId()));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelEnderecoAnterior");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelCondominio");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("eloAnormalidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("areaConstruidaFaixa");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("pavimentoCalcada");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("reservatorioVolumeFaixaSuperior");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("reservatorioVolumeFaixaInferior");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadraFace");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacaoTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("pavimentoRua");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("cadastroOcorrencia");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("pocoTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("despejo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("fonteAbastecimento");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("piscinaVolumeFaixa");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("consumoTarifa");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("faturamentoTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoMotivo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("rotaAlternativa");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("rotaEntrega");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
		return filtroImovel;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public ImovelContaEnvio getImovelContaEnvio() {
		return imovelContaEnvio;
	}

	public void setImovelContaEnvio(ImovelContaEnvio imovelContaEnvio) {
		this.imovelContaEnvio = imovelContaEnvio;
	}

	public CobrancaSituacao getCobrancaSituacao() {
		return cobrancaSituacao;
	}

	public void setCobrancaSituacao(CobrancaSituacao cobrancaSituacao) {
		this.cobrancaSituacao = cobrancaSituacao;
	}

    public Short getIndicadorJardim() {
        return indicadorJardim;
    }

    public void setIndicadorJardim(Short indicadorJardim) {
        this.indicadorJardim = indicadorJardim;
    }

    public Integer getNumeroSequencialRota() {
        return numeroSequencialRota;
    }

    public void setNumeroSequencialRota(Integer numeroSequencialRota) {
        this.numeroSequencialRota = numeroSequencialRota;
    }

	public String getNomeImovel() {
		return nomeImovel;
	}

	public void setNomeImovel(String nomeImovel) {
		this.nomeImovel = nomeImovel;
	}

	public Set<Conta> getContas() {
		return contas;
	}

	public void setContas(Set<Conta> contas) {
		this.contas = contas;
	}

	@Override
	public void initializeLazy() {
		initilizarCollectionLazy(this.getImovelSubcategorias());
		initilizarCollectionLazy(this.getClienteImoveis());
		getInscricaoFormatada();
		if (getLogradouroCep() != null)	logradouroCep.initializeLazy();
		if (getLogradouroBairro() != null) logradouroBairro.initializeLazy();
		if (getAreaConstruidaFaixa() != null) areaConstruidaFaixa.initializeLazy();
		if (getImovelPerfil() != null) imovelPerfil.initializeLazy();
		if (getImovelContaEnvio() != null) imovelContaEnvio.initializeLazy();
		if (getLocalidade() != null) localidade.initializeLazy();
		if (getQuadra() != null) quadra.initializeLazy();
		if (getQuadraFace() != null) quadraFace.initializeLazy();
		if (getRotaAlternativa() != null) this.rotaAlternativa.initializeLazy();
		if (getRotaEntrega() != null) this.rotaEntrega.initializeLazy();
		
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"localidade.descricao","inscricaoFormatada", "ligacaoAgua.dataReligacao"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Localidade", "Inscricao", "Data Rel. Agua"};
		return labels;		
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId() + "";
	}

    @SuppressWarnings("rawtypes")
	public Cliente getClienteUsuario(){
    	if (clienteImoveis != null){
    		for (Iterator iterator = clienteImoveis.iterator(); iterator.hasNext();) {
				ClienteImovel clienteImovel = (ClienteImovel) iterator.next();
				if (clienteImovel != null && 
					clienteImovel.getClienteRelacaoTipo().getId().shortValue() == ClienteRelacaoTipo.USUARIO.shortValue()){
					return clienteImovel.getCliente(); 
				}				
			} 
    	}
    	return null;
    }

    public Integer getNumeroSequencialRotaEntrega() {
		return numeroSequencialRotaEntrega;
	}

	public void setNumeroSequencialRotaEntrega(Integer numeroSequencialRotaEntrega) {
		this.numeroSequencialRotaEntrega = numeroSequencialRotaEntrega;
	}

	public Rota getRotaEntrega() {
		return rotaEntrega;
	}

	public void setRotaEntrega(Rota rotaEntrega) {
		this.rotaEntrega = rotaEntrega;
	}
	
	public String getMatriculaFormatada() {

		String matriculaImovelFormatada = "";
		Integer matriculaImovel = this.id;
		matriculaImovelFormatada = "" + matriculaImovel;
		
		int quantidadeCaracteresImovel = matriculaImovel.toString()
					.length();
			matriculaImovelFormatada = matriculaImovelFormatada.substring(0,
					quantidadeCaracteresImovel - 1)
					+ "."
					+ matriculaImovelFormatada.substring(
							quantidadeCaracteresImovel - 1,
							quantidadeCaracteresImovel);

		return matriculaImovelFormatada;
	}
	
	public SituacaoAtualizacaoCadastral getSituacaoAtualizacaoCadastral() {
		return situacaoAtualizacaoCadastral;
	}

	public void setSituacaoAtualizacaoCadastral(
			SituacaoAtualizacaoCadastral situacaoAtualizacaoCadastral) {
		this.situacaoAtualizacaoCadastral = situacaoAtualizacaoCadastral;
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovelTipoHabitacao");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovelTipoPropriedade");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovelTipoConstrucao");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovelTipoCobertura");		
		return filtro;
	}

	public QuadraFace getQuadraFace() {
		return quadraFace;
	}

	public void setQuadraFace(QuadraFace quadraFace) {
		this.quadraFace = quadraFace;
	}

	public Logradouro getPerimetroFinal() {
		return perimetroFinal;
	}

	public void setPerimetroFinal(Logradouro perimetroFinal) {
		this.perimetroFinal = perimetroFinal;
	}

	public Logradouro getPerimetroInicial() {
		return perimetroInicial;
	}

	public void setPerimetroInicial(Logradouro perimetroInicial) {
		this.perimetroInicial = perimetroInicial;
	}

	public Rota getRotaAlternativa() {
		return rotaAlternativa;
	}	
	
	public void setRotaAlternativa(Rota rotaAlternativa) {
		this.rotaAlternativa = rotaAlternativa;
	}

	public String getNumeroMedidorEnergia() {
		return numeroMedidorEnergia;
	}

	public void setNumeroMedidorEnergia(String numeroMedidorEnergia) {
		this.numeroMedidorEnergia = numeroMedidorEnergia;
	}

	public Date getDataVisitaComercial() {
		return dataVisitaComercial;
	}

	public void setDataVisitaComercial(Date dataVisitaComercial) {
		this.dataVisitaComercial = dataVisitaComercial;
	}
	
	public Short getIndicadorVencimentoMesSeguinte() {
		return indicadorVencimentoMesSeguinte;
	}

	public void setIndicadorVencimentoMesSeguinte(
			Short indicadorVencimentoMesSeguinte) {
		this.indicadorVencimentoMesSeguinte = indicadorVencimentoMesSeguinte;
	}

	public String getInformacoesComplementares() {
		return informacoesComplementares;
	}

	public void setInformacoesComplementares(String informacoesComplementares) {
		this.informacoesComplementares = informacoesComplementares;
	}


	public Integer getIndicadorReincidenciaInfracao() {
		return indicadorReincidenciaInfracao;
	}

	public void setIndicadorReincidenciaInfracao(
			Integer indicadorReincidenciaInfracao) {
		this.indicadorReincidenciaInfracao = indicadorReincidenciaInfracao;
	}

	public Integer getCodigoDebitoAutomatico() {
		return codigoDebitoAutomatico;
	}

	public void setCodigoDebitoAutomatico(Integer codigoDebitoAutomatico) {
		this.codigoDebitoAutomatico = codigoDebitoAutomatico;
	}
	
	public boolean isImovelCondominio() {
		System.out.println(this.id + " indicador: " + this.getIndicadorImovelCondominio() + " imovel condomini null " + this.getImovelCondominio() != null);
		if (this.getIndicadorImovelCondominio().shortValue() == ConstantesSistema.NAO
				&& this.getImovelCondominio() != null) {
			return true;
		} else {
			return false;
		}
	}

	public Integer getNumeroQuadraEntrega() {
		return numeroQuadraEntrega;
	}

	public void setNumeroQuadraEntrega(Integer numeroQuadraEntrega) {
		this.numeroQuadraEntrega = numeroQuadraEntrega;
	}

	public Integer getAnoMesExclusaoTarifaSocial() {
		return anoMesExclusaoTarifaSocial;
	}

	public void setAnoMesExclusaoTarifaSocial(Integer anoMesExclusaoTarifaSocial) {
		this.anoMesExclusaoTarifaSocial = anoMesExclusaoTarifaSocial;
	}

	public Short getIndicadorNivelInstalacaoEsgoto() {
		return indicadorNivelInstalacaoEsgoto;
	}

	public void setIndicadorNivelInstalacaoEsgoto(
			Short indicadorNivelInstalacaoEsgoto) {
		this.indicadorNivelInstalacaoEsgoto = indicadorNivelInstalacaoEsgoto;
	}

	public Short getIndicadorImovelAreaComum() {
		return indicadorImovelAreaComum;
	}

	public void setIndicadorImovelAreaComum(Short indicadorImovelAreaComum) {
		this.indicadorImovelAreaComum = indicadorImovelAreaComum;
	}

	public Integer getCategoriaPrincipalId() {
		return categoriaPrincipalId;
	}

	public void setCategoriaPrincipalId(Integer categoriaPrincipalId) {
		this.categoriaPrincipalId = categoriaPrincipalId;
	}

	public Integer getSubCategoriaPrincipalId() {
		return subCategoriaPrincipalId;
	}

	public void setSubCategoriaPrincipalId(Integer subCategoriaPrincipalId) {
		this.subCategoriaPrincipalId = subCategoriaPrincipalId;
	}

	public int getNumeroQuadra() {
		return this.quadra.getNumeroQuadra();
	}

	public void setNumeroQuadra(int numeroQuadra) {
		this.quadra.setNumeroQuadra(numeroQuadra);
	}

	public Integer getIdCapacidadeHidrometro() {
		return idCapacidadeHidrometro;
	}

	public void setIdCapacidadeHidrometro(Integer idCapacidadeHidrometro) {
		this.idCapacidadeHidrometro = idCapacidadeHidrometro;
	}

	public Integer getIdMarcaHidrometro() {
		return idMarcaHidrometro;
	}

	public void setIdMarcaHidrometro(Integer idMarcaHidrometro) {
		this.idMarcaHidrometro = idMarcaHidrometro;
	}

	public Integer getIdProtecaoHidrometro() {
		return idProtecaoHidrometro;
	}

	public void setIdProtecaoHidrometro(Integer idProtecaoHidrometro) {
		this.idProtecaoHidrometro = idProtecaoHidrometro;
	}

	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public String getTipoEntrevistado() {
		return tipoEntrevistado;
	}

	public void setTipoEntrevistado(String tipoEntrevistado) {
		this.tipoEntrevistado = tipoEntrevistado;
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getDsUFSiglaMunicipio() {
		return dsUFSiglaMunicipio;
	}

	public void setDsUFSiglaMunicipio(String dsUFSiglaMunicipio) {
		this.dsUFSiglaMunicipio = dsUFSiglaMunicipio;
	}

	public SetorComercial getSetorComercia() {
		return setorComercial;
	}

	public void setSetorComercia(SetorComercial setorComercia) {
		this.setorComercial = setorComercia;
	}
	
	public Integer getIdLigacaoAguaSituacao() {
		return null;
	}

	public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
	}

	public Integer getIdFonteAbastecimento() {
		return null;
	}

	public void setIdFonteAbastecimento(Integer idFonteAbastecimento) {
	}

	public Integer getIdImovel() {
		return id;
	}

	public void setIdImovel(Integer idImovel) {
		this.id = idImovel;
	}

	public HidrometroProtecao getHidrometroProtecao() {
		return null;
	}

	public void setHidrometroProtecao(HidrometroProtecao hidrometroProtecao) {
	}

	public Integer getTipoOperacao() {
		return null;
	}

	public void setTipoOperacao(Integer tipoOperacao) {
	}

	public Integer getCodigoMunicipio() {
		if(logradouroBairro != null && logradouroBairro.getLogradouro() != null && logradouroBairro.getLogradouro().getMunicipio() != null){
			return logradouroBairro.getLogradouro().getMunicipio().getId();
		}
		return null;
	}

	public void setCodigoMunicipio(Integer codigoMunicipio) {
	}

	public Integer getLogradouroTipo() {
		if(logradouroBairro != null && logradouroBairro.getLogradouro() != null && logradouroBairro.getLogradouro().getLogradouroTipo() != null){
			return logradouroBairro.getLogradouro().getLogradouroTipo().getId();
		}
		return null;
	}

	public void setLogradouroTipo(Integer logradouroTipo) {
	}

	public String getCodigoLogradouro() {
		if(logradouroBairro != null && logradouroBairro.getLogradouro() != null){
			return logradouroBairro.getLogradouro().getId().toString();
		}
		return null;
	}

	public void setCodigoLogradouro(String codigoLogradouro) {
		
	}

	public String getDescricaoLogradouro() {
		if(logradouroBairro != null && logradouroBairro.getLogradouro() != null){
			return logradouroBairro.getLogradouro().getNome();
		}
		return null;
	}

	public void setDescricaoLogradouro(String descricaoLogradouro) {
		
	}

	public String getNomeBairro() {
		if(logradouroBairro != null && logradouroBairro.getBairro() != null){
			return logradouroBairro.getBairro().getNome();
		}
		return null;
	}

	public void setNomeBairro(String nomeBairro) {
	}

	public Integer getCodigoCep() {
		if(logradouroCep != null && logradouroCep.getCep() != null){
			return logradouroCep.getCep().getCodigo();
		}
		return null;
	}

	public void setCodigoCep(Integer codigoCep) {
	}

	public Integer getIdLocalidade() {
		return localidade.getId();
	}

	public void setIdLocalidade(Integer idLocalidade) {
	}

	public int getCodigoSetorComercial() {
		return setorComercial.getId();
	}

	public void setCodigoSetorComercial(int idSetorComercial) {
	}

	public boolean isImovelNovo() {
		return this.getId().equals(null);
	}

	public void setIdRota(Integer idRota) {
		
	}

	public Integer getIdRota() {
		return null;
	}

	public Integer getIdLigacaoEsgotoSituacao() {
		return null;
	}

	public void setIdLigacaoEsgotoSituacao(Integer idLigacaoEsgotoSituacao) {
	}

	public Integer getIdLocalInstalacaoRamal() {
		return null;
	}

	public void setIdLocalInstalacaoRamal(Integer idRamalLocalInstalacao) {
		
	}

	public HidrometroCapacidade getHidrometroCapacidade() {
		return null;
	}

	public HidrometroMarca getHidrometroMarca() {
		return null;
	}

	public void setHidrometroCapacidade(
			HidrometroCapacidade hidrometroCapacidade) {
		
	}

	public void setHidrometroMarca(HidrometroMarca hidrometroMarca) {
	}
	
    public boolean existeHidrometro() {
        return existeHidrometroAgua() || existeHidrometroPoco();
    }

    public boolean existeHidrometroAgua() {
        return ligacaoAgua != null && ligacaoAgua.getHidrometroInstalacaoHistorico() != null && ligacaoAgua.existeHidrometroInstalado();
    }
    
    public boolean existeHidrometroPoco() {
        return hidrometroInstalacaoHistorico != null;
    }
	
    public boolean possuiEsgoto() {
        return ligacaoEsgoto != null;
    }

    public boolean esgotoLigado() {
        return ligacaoEsgotoSituacao.getId().equals(LigacaoEsgotoSituacao.LIGADO);
    }

    public boolean pertenceACondominio() {
        return imovelCondominio != null;
    }

    public boolean isCondominio() {
        return indicadorImovelCondominio != null && indicadorImovelCondominio == Status.ATIVO.getId();
    }

    public boolean faturamentoEsgotoAtivo() {
        return ligacaoEsgotoSituacao != null && ligacaoEsgotoSituacao.getIndicadorFaturamentoSituacao() != null
                && ligacaoEsgotoSituacao.getIndicadorFaturamentoSituacao().shortValue() == Status.ATIVO.getId();
    }

    public boolean faturamentoAguaAtivo() {
        return ligacaoAguaSituacao != null && ligacaoAguaSituacao.getIndicadorFaturamentoSituacao() != null
                && ligacaoAguaSituacao.getIndicadorFaturamentoSituacao().shortValue() == Status.ATIVO.getId();
    }

    public boolean paralisacaoFaturamento() {
        return faturamentoSituacaoTipo != null && faturamentoSituacaoTipo.getIndicadorParalisacaoFaturamento() == Status.ATIVO.getId();
    }

    public boolean faturamentoAguaValido() {
        return faturamentoSituacaoTipo != null && faturamentoSituacaoTipo.getIndicadorValidoAgua() == Status.ATIVO.getId();
    }
	
    public boolean aguaLigada() {
        return ligacaoAguaSituacao.getId().equals(LigacaoAguaSituacao.LIGADO);
    }
    
    public Short getClasseSocial() {
        return classeSocial;
    }

    public void setClasseSocial(Short classeSocial) {
        this.classeSocial = classeSocial;
    }

    public Short getQuantidadeAnimaisDomesticos() {
        return quantidadeAnimaisDomesticos;
    }

    public void setQuantidadeAnimaisDomesticos(Short quantidadeAnimaisDomesticos) {
        this.quantidadeAnimaisDomesticos = quantidadeAnimaisDomesticos;
    }

    public BigDecimal getVolumeCisterna() {
        return volumeCisterna;
    }

    public void setVolumeCisterna(BigDecimal volumeCisterna) {
        this.volumeCisterna = volumeCisterna;
    }

    public BigDecimal getVolumeCaixaDagua() {
        return volumeCaixaDagua;
    }

    public void setVolumeCaixaDagua(BigDecimal volumeCaixaDagua) {
        this.volumeCaixaDagua = volumeCaixaDagua;
    }

    public Short getTipoUso() {
        return tipoUso;
    }

    public void setTipoUso(Short tipoUso) {
        this.tipoUso = tipoUso;
    }

    public Short getAcessoHidrometro() {
        return acessoHidrometro;
    }

    public void setAcessoHidrometro(Short acessoHidrometro) {
        this.acessoHidrometro = acessoHidrometro;
    }

    public Collection<ImovelTipoOcupanteQuantidade> getQuantidadesOcupantes() {
        return quantidadesOcupantes;
    }

    public void setQuantidadesOcupantes(Collection<ImovelTipoOcupanteQuantidade> quantidadesOcupantes) {
        this.quantidadesOcupantes = quantidadesOcupantes;
    }

    public Integer getQuantidadeEconomiasSocial() {
        return quantidadeEconomiasSocial;
    }

    public void setQuantidadeEconomiasSocial(Integer quantidadeEconomiasSocial) {
        this.quantidadeEconomiasSocial = quantidadeEconomiasSocial;
    }

    public Integer getQuantidadeEconomiasOutra() {
        return quantidadeEconomiasOutra;
    }

    public void setQuantidadeEconomiasOutra(Integer quantidadeEconomiasOutra) {
        this.quantidadeEconomiasOutra = quantidadeEconomiasOutra;
    }

    public Short getPercentualAbastecimento() {
		return percentualAbastecimento;
	}

	public void setPercentualAbastecimento(Short percentualAbastecimento) {
		this.percentualAbastecimento = percentualAbastecimento;
	}

	public boolean useNovaChecagemGerarConta(){
        int [] ids = new int[]{7814933,7814950
         ,7815085,7815093,7816723,7814852,7814836,7815735,7815530,7815549
         ,7815557,7814259,7815611,7815654,7814291,7814321,7815026,7814410
         ,7815034,7814488,7814798,7814283,7814305,7814313,7814364,7814330
         ,7814380,7814356,7814402,7814461,7814470,7814542,7814550,7814810
         ,7814860,7814879,7814887,7814925,7815042,7814968,7814976,7815000
         ,7815050,7815522,7815620,7815670,7815689,7815743,7814275,7814607
         ,7814429,7814496,7814526,7814569,7814941,7814984,7815069,7815484
         ,7815492,7815506,7816634,7815565,7815697,7815719,7816626,7814437
         ,7815514,7814828,7814844,7816618,7815573,7816642,7816650,7814909
         ,7814453,7814917,7814267,7814240,7815727,7814348,7814399,7814534
         ,7814895,7814500,
         7627750 ,7629028,7629737,7628200,7629400,7629672,7629150,7629699
         ,7628137,7628129,7630034,7630000,7629974,7629958,7628960,7627718
         ,7627530,7627521,7627742,7628773,7628714,7628676,7628668,7628978
         ,7629524,7627513,7629427,7629451,7629494,7629540,7629575,7629591
         ,7629605,7629613,7629320,7629346,7629362,7628234,7629753,7629761
         ,7629770,7629796,7629818,7629842,7629877,7629745,7629907,7629923
         ,7628927,7628900,7628889,7629559,7629621,7629630,7629648,7629664
         ,7627483,7629702,7629710,7629729,7629087,7629109,7629168,7629370
         ,7630050,7629460,7629486,7627475,7629516,7629044,7628994,7628838
         ,7628862,7628811,7628803,7629303,7628242,7628226,7628218,7628188
         ,7628170,7628161,7628153,7628145,7628943,7627700,7628609,7629443
         ,7627491,7629311};
        
        for (int i =0; i< ids.length; i++){
            if (ids[i] == id.intValue()){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isTarifaSocial() {
    	return this.imovelPerfil.getId().equals(ConstantesSistema.INDICADOR_TARIFA_SOCIAL);
    }

	public Class getClasseOrigem(){
		return Imovel.class;
	}
	
    public Class getClasseHistorico() {
		return ImovelHistorico.class;
	}
    
    public boolean validarSeImovelEmCampo() {
		return validarSeImovelEmCampo;
	}

	public void setValidarSeImovelEmCampo(boolean validarSeImovelEmCampo) {
		this.validarSeImovelEmCampo = validarSeImovelEmCampo;
	}

	public void definirEnvioContaQuandoNaoHaResponsavel() {
		if(this.getImovelContaEnvio() == null ||
				(this.getImovelContaEnvio().getId().intValue() != ImovelContaEnvio.ENVIAR_PARA_EMAIL.intValue() &&
				this.getImovelContaEnvio().getId().intValue() != ImovelContaEnvio.ENVIAR_PARA_IMOVEL_E_PARA_EMAIL.intValue()) ){
			
			ImovelContaEnvio imovelContaEnvio = new ImovelContaEnvio();
			imovelContaEnvio.setId(ImovelContaEnvio.ENVIAR_IMOVEL);
			
			this.setImovelContaEnvio(imovelContaEnvio);
		}
		
		this.setIndicadorConta(new Short("2"));
		this.setIndicadorEmissaoExtratoFaturamento(new Short("2"));		
	}

	public boolean paralizadoSemRealizacaoLeitura() {
		return this.getFaturamentoSituacaoTipo() != null && 
			   this.getFaturamentoSituacaoTipo().getId().intValue() == FaturamentoSituacaoTipo.INDICADOR_PARALIZACAO_LEITURA_NAO_REALIZADA.intValue();
	}
	
	public boolean isImovelHidrometrado() {
		return hidrometroInstalacaoHistorico != null || (ligacaoAgua != null && ligacaoAgua.getHidrometroInstalacaoHistorico() != null);
	}
	
	public Rota obterRota() {
		Rota rota = new Rota();
		if (this.rotaAlternativa != null) {
			rota = this.rotaAlternativa;

		} else {
			rota = this.quadra.getRota();
		}
		return rota;
	}
	
	public void setObservacaoCategoria (String observacaoCategoria) {
		this.observacaoCategoria = observacaoCategoria;
	}
	
	public String getObservacaoCategoria() {
			return observacaoCategoria;
	}
}
