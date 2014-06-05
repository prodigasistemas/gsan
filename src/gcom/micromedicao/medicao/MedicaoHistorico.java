package gcom.micromedicao.medicao;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.MotivoInterferenciaTipo;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraFaixaFalsa;
import gcom.micromedicao.leitura.LeituraFiscalizacao;
import gcom.micromedicao.leitura.LeituraSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao()
public class MedicaoHistorico extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ATUALIZA_LEITURA_CONSUMO_EXCECOES = 353;
	public static final int ALTERAR_DADOS_DO_FATURAMENTO = 436;
	
	public final static Short INDICADOR_CONFIRMACAO_LEITURA_ZERO = new Short("0");
	public final static Short INDICADOR_CONFIRMACAO_LEITURA_UM = new Short("1");
	public final static Short INDICADOR_ANALISADO_ATUALIZADO = new Short("1");
	public final static Short INDICADOR_ANALISADO_NAO = new Short("2");
	public final static Short INDICADOR_ANALISADO_SIM = new Short("3");

	private Integer id;
	private int anoMesReferencia;
	private Short numeroVezesConsecutivasOcorrenciaAnormalidade;

	@ControleAlteracao(funcionalidade={ATUALIZA_LEITURA_CONSUMO_EXCECOES, ALTERAR_DADOS_DO_FATURAMENTO})
	private Date dataLeituraAnteriorFaturamento;

	@ControleAlteracao(funcionalidade={ATUALIZA_LEITURA_CONSUMO_EXCECOES, ALTERAR_DADOS_DO_FATURAMENTO})
	private Integer leituraAnteriorFaturamento;

	private Integer leituraAnteriorInformada;

	@ControleAlteracao(funcionalidade={ATUALIZA_LEITURA_CONSUMO_EXCECOES, ALTERAR_DADOS_DO_FATURAMENTO})
	private Date dataLeituraAtualInformada;

	@ControleAlteracao(funcionalidade={ATUALIZA_LEITURA_CONSUMO_EXCECOES, ALTERAR_DADOS_DO_FATURAMENTO})
	private Integer leituraAtualInformada;

	private Date dataLeituraAtualFaturamento;
	private int leituraAtualFaturamento;
	private Integer numeroConsumoMes;

	@ControleAlteracao(funcionalidade={ATUALIZA_LEITURA_CONSUMO_EXCECOES, ALTERAR_DADOS_DO_FATURAMENTO})
	private Integer numeroConsumoInformado;

	@ControleAlteracao(funcionalidade={ATUALIZA_LEITURA_CONSUMO_EXCECOES})
	private Date ultimaAlteracao;

	private Integer consumoMedioHidrometro;
	private Integer leituraCampo;
	private Date dataLeituraCampo;
	private LeituraFaixaFalsa leituraFaixaFalsa;
	private LeituraFiscalizacao leituraFiscalizacao;
	private Imovel imovel;
	private LeituraSituacao leituraSituacaoAnterior;

	@ControleAlteracao(FiltroMedicaoHistorico.LEITURA_SITUACAO_ATUAL)
	private LeituraSituacao leituraSituacaoAtual;

	private HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico;

	@ControleAlteracao(FiltroMedicaoHistorico.FATURADA_ANORMALIDADE)
	private LeituraAnormalidade leituraAnormalidadeFaturamento;

	@ControleAlteracao(value=FiltroMedicaoHistorico.INFORMADA_ANORMALIDADE, funcionalidade={ALTERAR_DADOS_DO_FATURAMENTO})
	private LeituraAnormalidade leituraAnormalidadeInformada;

	private LigacaoAgua ligacaoAgua;
	private Funcionario funcionario;
	private MedicaoTipo medicaoTipo;

	private Date leituraProcessamentoMovimento;
	
	@ControleAlteracao(funcionalidade={ATUALIZA_LEITURA_CONSUMO_EXCECOES})
	private Short indicadorAnalisado;
	
	@ControleAlteracao(funcionalidade={ATUALIZA_LEITURA_CONSUMO_EXCECOES})
	private Usuario usuarioAlteracao;
	
	private Leiturista leiturista;
	
	@ControleAlteracao(FiltroMedicaoHistorico.MOTIVO_INTERFERENCIA_TIPO)
	private MotivoInterferenciaTipo motivoInterferenciaTipo;
	
	private String gerarRelatorio;
	private String dataLeituraParaRegistrar;

	@ControleAlteracao(funcionalidade={ALTERAR_DADOS_DO_FATURAMENTO})
	private String indicadorConfirmacaoLeitura;

	public MedicaoHistorico(int anoMesReferencia,
			Short numeroVezesConsecutivasOcorrenciaAnormalidade,
			Date dataLeituraAnteriorFaturamento,
			Integer leituraAnteriorFaturamento, Integer leituraAnteriorInformada,
			Date dataLeituraAtualInformada, Integer leituraAtualInformada,
			Date dataLeituraAtualFaturamento, int leituraAtualFaturamento,
			Integer numeroConsumoMes, Integer numeroConsumoInformado,
			Date ultimaAlteracao, Integer consumoMedioHidrometro,
			Date leituraProcessamentoMovimento,
			LeituraFaixaFalsa leituraFaixaFalsa,
			LeituraFiscalizacao leituraFiscalizacao, Imovel imovel,
			LeituraSituacao leituraSituacaoAnterior,
			LeituraSituacao leituraSituacaoAtual,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico,
			LeituraAnormalidade leituraAnormalidadeFaturamento,
			LeituraAnormalidade leituraAnormalidadeInformada,
			LigacaoAgua ligacaoAgua, Funcionario funcionario,
			gcom.micromedicao.medicao.MedicaoTipo medicaoTipo) {
		this.anoMesReferencia = anoMesReferencia;
		this.numeroVezesConsecutivasOcorrenciaAnormalidade = numeroVezesConsecutivasOcorrenciaAnormalidade;
		this.dataLeituraAnteriorFaturamento = dataLeituraAnteriorFaturamento;
		this.leituraAnteriorFaturamento = leituraAnteriorFaturamento;
		this.leituraAnteriorInformada = leituraAnteriorInformada;
		this.dataLeituraAtualInformada = dataLeituraAtualInformada;
		this.leituraAtualInformada = leituraAtualInformada;
		this.dataLeituraAtualFaturamento = dataLeituraAtualFaturamento;
		this.leituraAtualFaturamento = leituraAtualFaturamento;
		this.numeroConsumoMes = numeroConsumoMes;
		this.numeroConsumoInformado = numeroConsumoInformado;
		this.ultimaAlteracao = ultimaAlteracao;
		this.consumoMedioHidrometro = consumoMedioHidrometro;
		this.leituraFaixaFalsa = leituraFaixaFalsa;
		this.leituraFiscalizacao = leituraFiscalizacao;
		this.imovel = imovel;
		this.leituraSituacaoAnterior = leituraSituacaoAnterior;
		this.leituraSituacaoAtual = leituraSituacaoAtual;
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
		this.leituraAnormalidadeFaturamento = leituraAnormalidadeFaturamento;
		this.leituraAnormalidadeInformada = leituraAnormalidadeInformada;
		this.ligacaoAgua = ligacaoAgua;
		this.funcionario = funcionario;
		this.medicaoTipo = medicaoTipo;
		this.leituraProcessamentoMovimento = leituraProcessamentoMovimento;
	}

	public MedicaoHistorico() {
	}

	public MedicaoHistorico(int anoMesReferencia,
			Date dataLeituraAnteriorFaturamento,
			Integer leituraAnteriorFaturamento, Date dataLeituraAtualInformada,
			Integer leituraAtualInformada, Date dataLeituraAtualFaturamento,
			int leituraAtualFaturamento, Imovel imovel,
			LeituraSituacao leituraSituacaoAnterior,
			LeituraSituacao leituraSituacaoAtual,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico,
			LeituraAnormalidade leituraAnormalidadeFaturamento,
			LeituraAnormalidade leituraAnormalidadeInformada,
			LigacaoAgua ligacaoAgua, Funcionario funcionario,
			gcom.micromedicao.medicao.MedicaoTipo medicaoTipo) {
		this.anoMesReferencia = anoMesReferencia;
		this.dataLeituraAnteriorFaturamento = dataLeituraAnteriorFaturamento;
		this.leituraAnteriorFaturamento = leituraAnteriorFaturamento;
		this.dataLeituraAtualInformada = dataLeituraAtualInformada;
		this.leituraAtualInformada = leituraAtualInformada;
		this.dataLeituraAtualFaturamento = dataLeituraAtualFaturamento;
		this.leituraAtualFaturamento = leituraAtualFaturamento;
		this.imovel = imovel;
		this.leituraSituacaoAnterior = leituraSituacaoAnterior;
		this.leituraSituacaoAtual = leituraSituacaoAtual;
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
		this.leituraAnormalidadeFaturamento = leituraAnormalidadeFaturamento;
		this.leituraAnormalidadeInformada = leituraAnormalidadeInformada;
		this.ligacaoAgua = ligacaoAgua;
		this.funcionario = funcionario;
		this.medicaoTipo = medicaoTipo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAnoMesReferencia() {
		return this.anoMesReferencia;
	}

	public void setAnoMesReferencia(int anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Short getNumeroVezesConsecutivasOcorrenciaAnormalidade() {
		return this.numeroVezesConsecutivasOcorrenciaAnormalidade;
	}

	public void setNumeroVezesConsecutivasOcorrenciaAnormalidade(
			Short numeroVezesConsecutivasOcorrenciaAnormalidade) {
		this.numeroVezesConsecutivasOcorrenciaAnormalidade = numeroVezesConsecutivasOcorrenciaAnormalidade;
	}

	public Date getDataLeituraAnteriorFaturamento() {
		return this.dataLeituraAnteriorFaturamento;
	}

	public void setDataLeituraAnteriorFaturamento(
			Date dataLeituraAnteriorFaturamento) {
		this.dataLeituraAnteriorFaturamento = dataLeituraAnteriorFaturamento;
	}

	public Integer getLeituraAnteriorFaturamento() {
		return this.leituraAnteriorFaturamento;
	}

	public void setLeituraAnteriorFaturamento(Integer leituraAnteriorFaturamento) {
		this.leituraAnteriorFaturamento = leituraAnteriorFaturamento;
	}

	public Integer getLeituraAnteriorInformada() {
		return this.leituraAnteriorInformada;
	}

	public void setLeituraAnteriorInformada(Integer leituraAnteriorInformada) {
		this.leituraAnteriorInformada = leituraAnteriorInformada;
	}

	public Date getDataLeituraAtualInformada() {
		return this.dataLeituraAtualInformada;
	}

	public void setDataLeituraAtualInformada(Date dataLeituraAtualInformada) {
		this.dataLeituraAtualInformada = dataLeituraAtualInformada;
	}

	public Integer getLeituraAtualInformada() {
		return this.leituraAtualInformada;
	}

	public void setLeituraAtualInformada(Integer leituraAtualInformada) {
		this.leituraAtualInformada = leituraAtualInformada;
	}

	public Date getDataLeituraAtualFaturamento() {
		return this.dataLeituraAtualFaturamento;
	}

	public void setDataLeituraAtualFaturamento(Date dataLeituraAtualFaturamento) {
		this.dataLeituraAtualFaturamento = dataLeituraAtualFaturamento;
	}

	public int getLeituraAtualFaturamento() {
		return this.leituraAtualFaturamento;
	}

	public void setLeituraAtualFaturamento(int leituraAtualFaturamento) {
		this.leituraAtualFaturamento = leituraAtualFaturamento;
	}

	public Integer getNumeroConsumoMes() {
		return this.numeroConsumoMes;
	}

	public void setNumeroConsumoMes(Integer numeroConsumoMes) {
		this.numeroConsumoMes = numeroConsumoMes;
	}

	public Integer getNumeroConsumoInformado() {
		return this.numeroConsumoInformado;
	}

	public void setNumeroConsumoInformado(Integer numeroConsumoInformado) {
		this.numeroConsumoInformado = numeroConsumoInformado;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getConsumoMedioHidrometro() {
		return this.consumoMedioHidrometro;
	}

	public void setConsumoMedioHidrometro(Integer consumoMedioHidrometro) {
		this.consumoMedioHidrometro = consumoMedioHidrometro;
	}

	public LeituraFaixaFalsa getLeituraFaixaFalsa() {
		return this.leituraFaixaFalsa;
	}

	public void setLeituraFaixaFalsa(LeituraFaixaFalsa leituraFaixaFalsa) {
		this.leituraFaixaFalsa = leituraFaixaFalsa;
	}

	public LeituraFiscalizacao getLeituraFiscalizacao() {
		return this.leituraFiscalizacao;
	}

	public void setLeituraFiscalizacao(LeituraFiscalizacao leituraFiscalizacao) {
		this.leituraFiscalizacao = leituraFiscalizacao;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public LeituraSituacao getLeituraSituacaoAnterior() {
		return this.leituraSituacaoAnterior;
	}

	public void setLeituraSituacaoAnterior(
			LeituraSituacao leituraSituacaoAnterior) {
		this.leituraSituacaoAnterior = leituraSituacaoAnterior;
	}

	public LeituraSituacao getLeituraSituacaoAtual() {
		return this.leituraSituacaoAtual;
	}

	public void setLeituraSituacaoAtual(LeituraSituacao leituraSituacaoAtual) {
		this.leituraSituacaoAtual = leituraSituacaoAtual;
	}

	public HidrometroInstalacaoHistorico getHidrometroInstalacaoHistorico() {
		return this.hidrometroInstalacaoHistorico;
	}

	public void setHidrometroInstalacaoHistorico(
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico) {
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
	}

	public LeituraAnormalidade getLeituraAnormalidadeFaturamento() {
		return this.leituraAnormalidadeFaturamento;
	}

	public void setLeituraAnormalidadeFaturamento(
			LeituraAnormalidade leituraAnormalidadeFaturamento) {
		this.leituraAnormalidadeFaturamento = leituraAnormalidadeFaturamento;
	}

	public LeituraAnormalidade getLeituraAnormalidadeInformada() {
		return this.leituraAnormalidadeInformada;
	}

	public void setLeituraAnormalidadeInformada(
			LeituraAnormalidade leituraAnormalidadeInformada) {
		this.leituraAnormalidadeInformada = leituraAnormalidadeInformada;
	}

	public LigacaoAgua getLigacaoAgua() {
		return this.ligacaoAgua;
	}

	public void setLigacaoAgua(LigacaoAgua ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public gcom.micromedicao.medicao.MedicaoTipo getMedicaoTipo() {
		return this.medicaoTipo;
	}

	public void setMedicaoTipo(gcom.micromedicao.medicao.MedicaoTipo medicaoTipo) {
		this.medicaoTipo = medicaoTipo;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String getGerarRelatorio() {
		return gerarRelatorio;
	}

	public void setGerarRelatorio(String gerarRelatorio) {
		this.gerarRelatorio = gerarRelatorio;
	}

	public String getDataLeituraParaRegistrar() {
		return dataLeituraParaRegistrar;
	}

	public void setDataLeituraParaRegistrar(String dataLeituraParaRegistrar) {
		this.dataLeituraParaRegistrar = dataLeituraParaRegistrar;
	}

	public String getIndicadorConfirmacaoLeitura() {
		return indicadorConfirmacaoLeitura;
	}

	public void setIndicadorConfirmacaoLeitura(
			String indicadorConfirmacaoLeitura) {
		this.indicadorConfirmacaoLeitura = indicadorConfirmacaoLeitura;
	}

	public Date getLeituraProcessamentoMovimento() {
		return leituraProcessamentoMovimento;
	}

	public void setLeituraProcessamentoMovimento(
			Date leituraProcessamentoMovimento) {
		this.leituraProcessamentoMovimento = leituraProcessamentoMovimento;
	}
	
	public Short getIndicadorAnalisado() {
		return indicadorAnalisado;
	}

	public void setIndicadorAnalisado(Short indicadorAnalisado) {
		this.indicadorAnalisado = indicadorAnalisado;
	}

	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Leiturista getLeiturista() {
		return leiturista;
	}

	public void setLeiturista(Leiturista leiturista) {
		this.leiturista = leiturista;
	}

	public Integer getLeituraCampo() {
		return leituraCampo;
	}

	public void setLeituraCampo(Integer leituraCampo) {
		this.leituraCampo = leituraCampo;
	}

	public Date getDataLeituraCampo() {
		return dataLeituraCampo;
	}

	public void setDataLeituraCampo(Date dataLeituraCampo) {
		this.dataLeituraCampo = dataLeituraCampo;
	}

	public MotivoInterferenciaTipo getMotivoInterferenciaTipo() {
		return motivoInterferenciaTipo;
	}

	public void setMotivoInterferenciaTipo(
			MotivoInterferenciaTipo motivoInterferenciaTipo) {
		this.motivoInterferenciaTipo = motivoInterferenciaTipo;
	}
	
	public String getMesAno() {
		String mesAno = null;

		String mes = null;
		String ano = null;

		if (this.anoMesReferencia != 0) {
			String anoMes = "" + this.anoMesReferencia;

			mes = anoMes.substring(4, 6);
			ano = anoMes.substring(0, 4);
			mesAno = mes + "/" + ano;
		}
		return mesAno;
	}
	
    public boolean equals(Object other) {
    	if ((this == other)) {
			return true;
		}

		
        if (!(other instanceof MedicaoHistorico)) {
			return false;
		}

		boolean retorno = true;

		MedicaoHistorico castOther = (MedicaoHistorico) other;

		if (this.getId() != null) {

			if (!this.getId().equals(castOther.getId())) {
				retorno = false;
			}

		}

		if (this.getLigacaoAgua() != null && castOther.getLigacaoAgua() != null) {

			if (!this.getLigacaoAgua().getId().equals(
					castOther.getLigacaoAgua().getId())) {
				retorno = false;
			}

		}

		if (this.getImovel() != null && castOther.getImovel() != null) {

			if (!this.getImovel().getId().equals(castOther.getImovel().getId())) {
				retorno = false;
			}

		}

		if (this.getMedicaoTipo() != null) {

			if (!this.getMedicaoTipo().getId().equals(
					castOther.getMedicaoTipo().getId())) {
				retorno = false;
			}
		}

		if (this.getAnoMesReferencia() != 0) {
			if (!(this.getAnoMesReferencia() == castOther.getAnoMesReferencia())) {
				retorno = false;
			}
		}
        return retorno;
        
    }

    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();

		filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.ID,this.getId()));
		filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroMedicaoHistorico.LEITURA_SITUACAO_ATUAL);
		filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroMedicaoHistorico.MOTIVO_INTERFERENCIA_TIPO);
		filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroMedicaoHistorico.FATURADA_ANORMALIDADE);
		filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroMedicaoHistorico.INFORMADA_ANORMALIDADE);
		filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroMedicaoHistorico.USUARIO);
		
		return filtroMedicaoHistorico;
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		return filtro;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = { "leituraAnteriorFaturamento", 
							"leituraAtualInformada",
							"numeroConsumoInformado",
							"leituraSituacaoAtual.descricao", 
							"leituraAnormalidadeFaturamento.descricaoAbreviada",
							"leituraAnormalidadeInformada.descricaoAbreviada"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Leit. Ant. Fat.", 
						   "Leit. Atual Inf.",
						   "Nº Cons. Inf.",
						   "Sit. Leit. Atual", 
						   "Leit. Anorm. Fat.", 
						   "Leit. Anorm. Inf."};
		return labels;		
	}
	
}
