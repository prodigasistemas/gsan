package gcom.atendimentopublico.ordemservico;

import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.debito.DebitoTipo;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.Operacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ServicoTipo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	public static final int ATUALIZAR_IMPORTANCIA_TIPO_SERVICO = 1819;
	
	public Filtro retornaFiltro(){
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			
		filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, this.getId()));
		filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("programaCalibragem");
		return filtroServicoTipo;
	}

	public final static int TIPO_LIGACAO_AGUA = 619;
	
	/**
	 * Inclusão de constante de Corte de água por débito
	 * a ser utilizada na action encerrarOrdemServiçoVencidaAction
	 * @author Wellington Rocha
	 * @date 16/01/2012*/
	public final static int TIPO_CORTE_DE_AGUA_POR_DEBITO = 458;

	public final static int TIPO_LIGACAO_ESGOTO = 726;

	public final static int TIPO_CONFIRMAR_DADOS_LIGACAO_ESGOTO = 254;

	public final static int TIPO_CONFIRMAR_DADOS_LIGACAO_AGUA = 255;

	public final static int TIPO_CONFIRMAR_DADOS_CORTE_LIGACAO_AGUA = 252;

	public final static int TIPO_CORTE_LIGACAO_AGUA = 242;

	public final static int TIPO_CONFIRMAR_DADOS_SUPRESSAO_LIGACAO_AGUA = 253;

	public final static int TIPO_SUPRESSAO_LIGACAO_AGUA = 245;

	public final static int TIPO_RESTABELECIMENTO_LIGACAO_AGUA = 244;

	public final static int TIPO_RELIGACAO_AGUA = 243;

	public final static int TIPO_CORTE_ADMINISTRATIVO_LIGACAO_AGUA = 241;

	public final static int TIPO_CALCULAR_CONSUMO_MINIMO_AGUA = 690;

	//public final static int TIPO_SUBSTITUICAO_HIDROMETRO_POCO = 13;

	//public final static int TIPO_SUBSTITUICAO_HIDROMETRO_LIGACAO_AGUA = 14;

	public final static int TIPO_RETIRADA_HIDROMETRO = 307;

	//public final static int TIPO_RETIRADA_HIDROMETRO_POCO = 16;

	public final static int TIPO_REMANEJAMENTO_HIDROMETRO_LIGACAO_AGUA = 308;

	public final static int TIPO_REMANEJAMENTO_HIDROMETRO_POCO = 18;

	//public final static int TIPO_INSTALACAO_HIDROMETRO_POCO = 19;

	//public final static int TIPO_INSTALACAO_HIDROMETRO_LIGACAO_AGUA = 20;

	public final static int TIPO_EFETUAR_INSTALACAO_HIDROMETRO = 304;
	
	public final static int TIPO_EFETUAR_INSTALACAO_HIDROMETRO_POCO = 305;

	public final static int TIPO_CONFIRMAR_DADOS_INSTALACAO_HIDROMETRO = 313;

	public final static int TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO = 310;
	
	public final static int TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO_POCO = 1006;
	
    public final static int TIPO_EFETUAR_REMOCAO_HIDROMETRO = 308;
	
	public final static int TIPO_EFETUAR_REMOCAO_HIDROMETRO_POCO = 1005;	

	public final static int TIPO_CONFIRMAR_DADOS_SUBSTITUICAO_HIDROMETRO = 314;

	public final static int TIPO_CALCULAR_VOLUME_MINIMO_ESGOTO = 755;

	public final static short INDICADOR_PAVIMENTO_SIM = 1;
	
	public final static String INDICADOR_PAVIMENTO_NAO = "2";
	
	public final static short INDICADOR_ATUALIZA_COMERCIAL_SIM = 1;
	
	public final static short INDICADOR_ATUALIZA_COMERCIAL_NAO = 2;
	
	public final static int TIPO_TAMPONAMENTO_LIGACAO_ESGOTO = 743;
	
	public final static int TIPO_DESATIVACAO_LIGACAO_ESGOTO = 752;
	
	public final static int TIPO_RESTABELECIMENTO_LIGACAO_ESGOTO = 753;
	
	public final static int TIPO_REATIVACAO_LIGACAO_ESGOTO = 754;
	
	public final static int TIPO_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO = 1001;
	
	public final static String INDICADOR_VISTORIA_SERVICO_TIPO_NAO = "2";
	
	public final static int TIPO_ALTERACAO_SITUACAO_LIGACAO = 1004 ;
	
	public final static int TIPO_RESTABELECIMENTO_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO = 1005;
	
	public final static int TIPO_RELIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO = 1006;
	
	public final static int TIPO_ORDEM_SERVICO_FISCALIZACAO = 33;
	
	public final static int TIPO_FISCALIZACAO = 705;
	
	public final static int TIPO_INSPECAO_ANORMALIDADE = 350;
	
	public final static int TIPO_SUBSTITUICAO_COM_REMOCAO = 351;
	
	public final static int TIPO_INSTALACAO_RESERVACAO = 1417;
	
	public final static int TIPO_INSTALACAO_HIDROMETRO_CONTROLE_DE_PERDAS = 1418;
		
	public final static int TIPO_SUBSTITUICAO_HIDROMETRO_CONTROLE_DE_PERDAS = 1419;
	
	public final static int TIPO_INSTALACAO_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS = 1420;
	
	public final static int TIPO_SUBSTITUICAO_RAMAL_COM_SUBSTITUICAO_HDT_CONTROLE_DE_PERDAS = 1421;
	
	public final static int TIPO_SUBSTITUICAO_RAMAL_SEM_INST_HDT_CONTROLE_DE_PERDAS = 1422;
	
	public final static int TIPO_REFORMA_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS = 1425;
	
	public final static int TIPO_REFORMA_RAMAL_SEM_HINST_DT_CONTROLE_DE_PERDAS  = 1426;
	
	public final static int TIPO_INSTALACAO_RAMAL_SEM_INST_HDT_CONTROLE_DE_PERDAS = 1427;
	
	public final static int TIPO_RELIGACAO_E_SUBSTITUICAO_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS = 1428;
	
	public final static int TIPO_RELIGACAO_E_SUBSTITUICAO_RAMAL_SEM_INST_HDT_CONTROLE_DE_PERDAS = 1429;
	
	public final static int TIPO_RELIGACAO_E_INSTALACAO_HIDROMETRO_CONTROLE_DE_PERDAS= 1430;
	
	public final static int TIPO_RELIGACAO_E_SUBSTITUICAO_HIDROMETRO_CONTROLE_DE_PERDAS = 1431;
	
	public final static int TIPO_SUBSTITUICAO_RAMAL_COM_INST_HDT_CONTROLE_DE_PERDAS = 1432;
	
	public final static int TIPO_RELIGACAO_E_SUBSTITUICAO_RAMAL_COM_SUBSTITUICAO_HDT_CONTROLE_DE_PERDAS= 1433;
	
	public final static int TIPO_REFORMA_RAMAL_COM_SUBSTITUICAO_HDT_CONTROLE_DE_PERDAS = 1434;
	
	
	
	
	
	
	
	
	



	//--------------------------------------------------- [YTS - 30/05/2008]
	
	public final static short INDICADOR_PAVIMENTO_CALCADA_SIM = 1;
	
	public final static short INDICADOR_PAVIMENTO_CALCADA_NAO = 2;	
	
	public final static short INDICADOR_PAVIMENTO_RUA_SIM = 1;
	
	public final static short INDICADOR_PAVIMENTO_RUA_NAO = 2;
	
	//---------------------------------------------------------------------
	
	//public final static short INDICADOR_EMPRESA_COBRANCA_SIM = 1;
	
	//public final static short INDICADOR_EMPRESA_COBRANCA_NAO = 2;
	
	

	/** identifier field */
	private Integer id;

	/** persistent field */
	private String descricao;

	/** persistent field */
	private String descricaoAbreviada;

	/** persistent field */
	private BigDecimal valor;

	/** persistent field */
	// private short prioridade;
	/** persistent field */
	private short indicadorPavimento;

	/** persistent field */
	private short indicadorAtualizaComercial;

	/** persistent field */
	private short indicadorTerceirizado;

	/** persistent field */
	private String codigoServicoTipo;

	/** persistent field */
	private short tempoMedioExecucao;

	/** persistent field */
	private short indicadorUso;
	
	/** persistent field */
	private short indicadorVistoria;
	
	/** persistent field */
	private short indicadorPermiteAlterarValor;
	
	/** persistent field */
	private short indicadorIncluirDebito;
	
	/** persistent field */
	private short indicadorCobrarJuros;
	
	/** persistent field */
	private short indicadorFiscalizacaoInfracao;

	/** persistent field */
	private Short indicadorPavimentoRua;
	
	/** persistent field */
	private Short indicadorPavimentoCalcada;

	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private gcom.atendimentopublico.ordemservico.ServicoTipoReferencia servicoTipoReferencia;

	/** persistent field */
	private CreditoTipo creditoTipo;

	/** persistent field */
	private gcom.atendimentopublico.ordemservico.ServicoPerfilTipo servicoPerfilTipo;

	/** persistent field */
	private gcom.atendimentopublico.ordemservico.ServicoTipoSubgrupo servicoTipoSubgrupo;

	/** persistent field */
	private gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade servicoTipoPrioridade;

	/** persistent field */
	private DebitoTipo debitoTipo;

	
	private Operacao operacao;

	private Collection servicoTipoAtividades;

	private Collection servicoTipoMateriais;
	
	private Integer constanteFuncionalidadeTipoServico;
	
	/** persistent field */
	private Short indicativoTipoSevicoEconomias;
	
	private Short indicadorBoletim;
	
	private Short indicadorAtividade;
	
	public final static short INDICADOR_VISTORIA_SIM = 1;
	
	private short indicadorEmpresaCobranca;
	
	private short indicadorInspecaoAnormalidade;
	
	private short indicadorProgramacaoAutomatica;
	
	private Short indicadorEnvioPesquisaSatisfacao;

	private Short indicadorServicoOrdemSeletiva;
	
	private Short indicadorGerarOSInspecaoAnormalidade;
	
	private Short indicadorEncAutomaticoRAQndEncOS;
	
	private Collection servicoTipoMotivoEncerramentos;
	
	private Short indicadorPermiteAlteracaoImovelEmCampo;
	
	@ControleAlteracao(value=FiltroTipoServico.OS_PROGRAMA_CALIBRAGEM,funcionalidade={ATUALIZAR_IMPORTANCIA_TIPO_SERVICO,OrdemServico.ATRIBUTOS_MANTER_ORDEM_SERVICO})
	private OSProgramacaoCalibragem programaCalibragem;

	/** full constructor */
	public ServicoTipo(
			String descricao,
			String descricaoAbreviada,
			BigDecimal valor,
			short prioridade,
			short indicadorPavimento,
			short indicadorAtualizaComercial,
			short indicadorTerceirizado,
			String codigoServicoTipo,
			short tempoMedioExecucao,
			short indicadorUso,
			Date ultimaAlteracao,
			gcom.atendimentopublico.ordemservico.ServicoTipoReferencia servicoTipoReferencia,
			CreditoTipo creditoTipo,
			gcom.atendimentopublico.ordemservico.ServicoPerfilTipo servicoPerfilTipo,
			gcom.atendimentopublico.ordemservico.ServicoTipoSubgrupo servicoTipoSubgrupo,
			gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade servicoTipoPrioridade,
			DebitoTipo debitoTipo,
			Integer constanteFuncionalidadeTipoServico, 
			short indicadorEmpresaCobranca,
			short indicadorInspecaoAnormalidade,
			short indicadorProgramacaoAutomatica){
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.valor = valor;
		// this.prioridade = prioridade;
		this.indicadorPavimento = indicadorPavimento;
		this.indicadorAtualizaComercial = indicadorAtualizaComercial;
		this.indicadorTerceirizado = indicadorTerceirizado;
		this.codigoServicoTipo = codigoServicoTipo;
		this.tempoMedioExecucao = tempoMedioExecucao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.servicoTipoReferencia = servicoTipoReferencia;
		this.creditoTipo = creditoTipo;
		this.servicoPerfilTipo = servicoPerfilTipo;
		this.servicoTipoSubgrupo = servicoTipoSubgrupo;
		this.servicoTipoPrioridade = servicoTipoPrioridade;
		this.debitoTipo = debitoTipo;
		this.constanteFuncionalidadeTipoServico = constanteFuncionalidadeTipoServico;
		this.indicadorEmpresaCobranca = indicadorEmpresaCobranca;
		this.indicadorInspecaoAnormalidade = indicadorInspecaoAnormalidade;
		this.indicadorProgramacaoAutomatica = indicadorProgramacaoAutomatica;
	}

	/** default constructor */
	public ServicoTipo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OSProgramacaoCalibragem getProgramaCalibragem() {
		return programaCalibragem;
	}

	public void setProgramaCalibragem(OSProgramacaoCalibragem programaCalibragem) {
		this.programaCalibragem = programaCalibragem;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	// public short getPrioridade() {
	// return this.prioridade;
	// }
	//
	// public void setPrioridade(short prioridade) {
	// this.prioridade = prioridade;
	// }

	public short getIndicadorPavimento() {
		return this.indicadorPavimento;
	}

	public void setIndicadorPavimento(short indicadorPavimento) {
		this.indicadorPavimento = indicadorPavimento;
	}

	public short getIndicadorAtualizaComercial() {
		return this.indicadorAtualizaComercial;
	}

	public void setIndicadorAtualizaComercial(short indicadorAtualizaComercial) {
		this.indicadorAtualizaComercial = indicadorAtualizaComercial;
	}

	public short getIndicadorTerceirizado() {
		return this.indicadorTerceirizado;
	}

	public void setIndicadorTerceirizado(short indicadorTerceirizado) {
		this.indicadorTerceirizado = indicadorTerceirizado;
	}

	public String getCodigoServicoTipo() {
		return this.codigoServicoTipo;
	}

	public void setCodigoServicoTipo(String codigoServicoTipo) {
		this.codigoServicoTipo = codigoServicoTipo;
	}

	public short getTempoMedioExecucao() {
		return this.tempoMedioExecucao;
	}

	public void setTempoMedioExecucao(short tempoMedioExecucao) {
		this.tempoMedioExecucao = tempoMedioExecucao;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.atendimentopublico.ordemservico.ServicoTipoReferencia getServicoTipoReferencia() {
		return this.servicoTipoReferencia;
	}

	public void setServicoTipoReferencia(
			gcom.atendimentopublico.ordemservico.ServicoTipoReferencia servicoTipoReferencia) {
		this.servicoTipoReferencia = servicoTipoReferencia;
	}

	public CreditoTipo getCreditoTipo() {
		return this.creditoTipo;
	}

	public void setCreditoTipo(CreditoTipo creditoTipo) {
		this.creditoTipo = creditoTipo;
	}

	public gcom.atendimentopublico.ordemservico.ServicoPerfilTipo getServicoPerfilTipo() {
		return this.servicoPerfilTipo;
	}

	public void setServicoPerfilTipo(
			gcom.atendimentopublico.ordemservico.ServicoPerfilTipo servicoPerfilTipo) {
		this.servicoPerfilTipo = servicoPerfilTipo;
	}

	public gcom.atendimentopublico.ordemservico.ServicoTipoSubgrupo getServicoTipoSubgrupo() {
		return this.servicoTipoSubgrupo;
	}

	public void setServicoTipoSubgrupo(
			gcom.atendimentopublico.ordemservico.ServicoTipoSubgrupo servicoTipoSubgrupo) {
		this.servicoTipoSubgrupo = servicoTipoSubgrupo;
	}

	public gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade getServicoTipoPrioridade() {
		return this.servicoTipoPrioridade;
	}

	public void setServicoTipoPrioridade(
			gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade servicoTipoPrioridade) {
		this.servicoTipoPrioridade = servicoTipoPrioridade;
	}

	public DebitoTipo getDebitoTipo() {
		return this.debitoTipo;
	}

	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Collection getServicoTipoAtividades() {
		return servicoTipoAtividades;
	}

	public void setServicoTipoAtividades(Collection servicoTipoAtividades) {
		this.servicoTipoAtividades = servicoTipoAtividades;
	}

	public Collection getServicoTipoMateriais() {
		return servicoTipoMateriais;
	}

	public void setServicoTipoMateriais(Collection servicoTipoMateriais) {
		this.servicoTipoMateriais = servicoTipoMateriais;
	}

	public short getIndicadorFiscalizacaoInfracao() {
		return indicadorFiscalizacaoInfracao;
	}

	public void setIndicadorFiscalizacaoInfracao(short indicadorFiscalizacaoInfracao) {
		this.indicadorFiscalizacaoInfracao = indicadorFiscalizacaoInfracao;
	}

	public short getIndicadorVistoria() {
		return indicadorVistoria;
	}

	public void setIndicadorVistoria(short indicadorVistoria) {
		this.indicadorVistoria = indicadorVistoria;
	}


	public short getIndicadorPermiteAlterarValor() {
		return indicadorPermiteAlterarValor;
	}

	public void setIndicadorPermiteAlterarValor(short indicadorPermiteAlterarValor) {
		this.indicadorPermiteAlterarValor = indicadorPermiteAlterarValor;
	}

	public short getIndicadorCobrarJuros() {
		return indicadorCobrarJuros;
	}

	public void setIndicadorCobrarJuros(short indicadorCobrarJuros) {
		this.indicadorCobrarJuros = indicadorCobrarJuros;
	}

	public short getIndicadorIncluirDebito() {
		return indicadorIncluirDebito;
	}

	public void setIndicadorIncluirDebito(short indicadorIncluirDebito) {
		this.indicadorIncluirDebito = indicadorIncluirDebito;
	}

	public Short getIndicadorPavimentoCalcada() {
		return indicadorPavimentoCalcada;
	}

	public void setIndicadorPavimentoCalcada(Short indicadorPavimentoCalcada) {
		this.indicadorPavimentoCalcada = indicadorPavimentoCalcada;
	}

	public Short getIndicadorPavimentoRua() {
		return indicadorPavimentoRua;
	}

	public void setIndicadorPavimentoRua(Short indicadorPavimentoRua) {
		this.indicadorPavimentoRua = indicadorPavimentoRua;
	}

	public Integer getConstanteFuncionalidadeTipoServico() {
		return constanteFuncionalidadeTipoServico;
	}

	public void setConstanteFuncionalidadeTipoServico(
			Integer constanteFuncionalidadeTipoServico) {
		this.constanteFuncionalidadeTipoServico = constanteFuncionalidadeTipoServico;
	}

	public Short getIndicativoTipoSevicoEconomias() {
		return indicativoTipoSevicoEconomias;
	}

	public void setIndicativoTipoSevicoEconomias(Short indicativoTipoSevicoEconomias) {
		this.indicativoTipoSevicoEconomias = indicativoTipoSevicoEconomias;
	}

	public Short getIndicadorBoletim() {
		return indicadorBoletim;
	}

	public void setIndicadorBoletim(Short indicadorBoletim) {
		this.indicadorBoletim = indicadorBoletim;
	}

	public short getIndicadorEmpresaCobranca() {
		return indicadorEmpresaCobranca;
	}

	public void setIndicadorEmpresaCobranca(short indicadorEmpresaCobranca) {
		this.indicadorEmpresaCobranca = indicadorEmpresaCobranca;
	}
	
	public short getIndicadorInspecaoAnormalidade() {
		return indicadorInspecaoAnormalidade;
	}

	public void setIndicadorInspecaoAnormalidade(short indicadorInspecaoAnormalidade) {
		this.indicadorInspecaoAnormalidade = indicadorInspecaoAnormalidade;
	}
	
	public short getIndicadorProgramacaoAutomatica() {
		return indicadorProgramacaoAutomatica;
	}
	
	public void setIndicadorProgramacaoAutomatica(short indicadorProgramacaoAutomatica){
		this.indicadorProgramacaoAutomatica = indicadorProgramacaoAutomatica;
	}

	public Short getIndicadorAtividade() {
		return indicadorAtividade;
	}

	public void setIndicadorAtividade(Short indicadorAtividade) {
		this.indicadorAtividade = indicadorAtividade;
	}

	public Short getIndicadorServicoOrdemSeletiva() {
		return indicadorServicoOrdemSeletiva;
	}

	public void setIndicadorServicoOrdemSeletiva(Short indicadorServicoOrdemSeletiva) {
		this.indicadorServicoOrdemSeletiva = indicadorServicoOrdemSeletiva;
	}

	public Short getIndicadorEnvioPesquisaSatisfacao() {
		return indicadorEnvioPesquisaSatisfacao;
	}

	public void setIndicadorEnvioPesquisaSatisfacao(
			Short indicadorEnvioPesquisaSatisfacao) {
		this.indicadorEnvioPesquisaSatisfacao = indicadorEnvioPesquisaSatisfacao;
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID,getId()));
		filtro.adicionarCaminhoParaCarregamentoEntidade("programaCalibragem");
		return filtro;
	}

	public Short getIndicadorGerarOSInspecaoAnormalidade() {
		return indicadorGerarOSInspecaoAnormalidade;
	}

	public void setIndicadorGerarOSInspecaoAnormalidade(
			Short indicadorGerarOSInspecaoAnormalidade) {
		this.indicadorGerarOSInspecaoAnormalidade = indicadorGerarOSInspecaoAnormalidade;
	}

	public Collection getServicoTipoMotivoEncerramentos() {
		return servicoTipoMotivoEncerramentos;
	}

	public void setServicoTipoMotivoEncerramentos(Collection servicoTipoMotivoEncerramentos) {
		this.servicoTipoMotivoEncerramentos = servicoTipoMotivoEncerramentos;
	}

	public Short getIndicadorEncAutomaticoRAQndEncOS() {
		return indicadorEncAutomaticoRAQndEncOS;
	}

	public void setIndicadorEncAutomaticoRAQndEncOS(
			Short indicadorEncAutomaticoRAQndEncOS) {
		this.indicadorEncAutomaticoRAQndEncOS = indicadorEncAutomaticoRAQndEncOS;
	}

	public Short getIndicadorPermiteAlteracaoImovelEmCampo() {
		return indicadorPermiteAlteracaoImovelEmCampo;
	}

	public void setIndicadorPermiteAlteracaoImovelEmCampo(
			Short indicadorPermiteAlteracaoImovelEmCampo) {
		this.indicadorPermiteAlteracaoImovelEmCampo = indicadorPermiteAlteracaoImovelEmCampo;
	}

	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}
	
	
	
}
