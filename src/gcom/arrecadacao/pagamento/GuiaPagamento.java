/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.arrecadacao.pagamento;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class GuiaPagamento extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	public static final int ATRIBUTOS_GUIA_PAGAMENTO_INSERIR = 115; //Operacao.OPERACAO_GUIA_PAGAMENTO_INSERIR
	public static final int ATRIBUTOS_GUIA_PAGAMENTO_CANCELAR = 116; //Operacao.OPERACAO_GUIA_PAGAMENTO_CANCELAR

	public Filtro retornaFiltro() {
		FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();

		filtroGuiaPagamento.adicionarParametro(new ParametroSimples(
				FiltroGuiaPagamento.ID, this.getId()));
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("parcelamento");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
		filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("ordemServico");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAnterior");
		filtroGuiaPagamento
				.adicionarCaminhoParaCarregamentoEntidade("usuario");

		return filtroGuiaPagamento;
	}

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private Integer anoMesReferenciaContabil;

	/** nullable persistent field */
	@ControleAlteracao(value=FiltroGuiaPagamento.DEBITO_TIPO,
			funcionalidade={ATRIBUTOS_GUIA_PAGAMENTO_INSERIR})
	private Date dataEmissao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_GUIA_PAGAMENTO_INSERIR,ATRIBUTOS_GUIA_PAGAMENTO_CANCELAR})
	private Date dataVencimento;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_GUIA_PAGAMENTO_INSERIR,ATRIBUTOS_GUIA_PAGAMENTO_CANCELAR})
	private BigDecimal valorDebito;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private Cliente cliente;

	/** persistent field */
	private Parcelamento parcelamento;

	/** persistent field */
	private DocumentoTipo documentoTipo;

	/** persistent field */
	@ControleAlteracao(value=FiltroGuiaPagamento.REGISTRO_ATENDIMENTO,
			funcionalidade={ATRIBUTOS_GUIA_PAGAMENTO_INSERIR,ATRIBUTOS_GUIA_PAGAMENTO_CANCELAR})
	private RegistroAtendimento registroAtendimento;

	/** persistent field */	
	private Imovel imovel;

	/** persistent field */
	private Localidade localidade;

	/** persistent field */
	private FinanciamentoTipo financiamentoTipo;

	/** persistent field */
	@ControleAlteracao(value=FiltroGuiaPagamento.DEBITO_TIPO,
			funcionalidade={ATRIBUTOS_GUIA_PAGAMENTO_INSERIR,ATRIBUTOS_GUIA_PAGAMENTO_CANCELAR})
	private DebitoTipo debitoTipo;

	/** persistent field */
	@ControleAlteracao(value=FiltroGuiaPagamento.ORDEM_SERVICO,
			funcionalidade={ATRIBUTOS_GUIA_PAGAMENTO_INSERIR,ATRIBUTOS_GUIA_PAGAMENTO_CANCELAR})
	private OrdemServico ordemServico;

	/** persistent field */
	private LancamentoItemContabil lancamentoItemContabil;

	/** persistent field */
	@ControleAlteracao(value=FiltroGuiaPagamento.DEBITO_CREDITO_SITUACAO_ATUAL,
			funcionalidade={ATRIBUTOS_GUIA_PAGAMENTO_CANCELAR})
	private DebitoCreditoSituacao debitoCreditoSituacaoAtual;

	/** persistent field */
	private DebitoCreditoSituacao debitoCreditoSituacaoAnterior;

	/** persistent field */
	private Short indicadoCobrancaMulta;
	
	/** persistent field */
	private Short numeroPrestacaoDebito;

	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_GUIA_PAGAMENTO_INSERIR,ATRIBUTOS_GUIA_PAGAMENTO_CANCELAR})
	private Short numeroPrestacaoTotal;
	
	/** persistent field */
	private Set clientesGuiaPagamento;
	
	/** persistent field */
	private GuiaPagamentoGeral guiaPagamentoGeral;
	
	private Integer numeroGuia;
	
	private Integer anoGuia;
	
	private Integer lotePagamento;
	
    /** persistent field */
    private Usuario usuario;
	
	private GuiaPagamentoGeral origem;
	
	private String observacao;
	
	private Short indicadorEmitirObservacao;
	
	/** nullable persistent field */
	private String numeroGuiaFatura;

	/** full constructor */
	public GuiaPagamento(Integer anoMesReferenciaContabil, Date dataEmissao,
			Date dataVencimento, BigDecimal valorDebito, Date ultimaAlteracao,
			Cliente cliente, Parcelamento parcelamento,
			DocumentoTipo documentoTipo,
			RegistroAtendimento registroAtendimento, Imovel imovel,
			Localidade localidade, FinanciamentoTipo financiamentoTipo,
			DebitoTipo debitoTipo, OrdemServico ordemServico,
			LancamentoItemContabil lancamentoItemContabil,
			DebitoCreditoSituacao debitoCreditoSituacaoAtual,
			DebitoCreditoSituacao debitoCreditoSituacaoAnterior,
			Short indicadoCobrancaMulta, Usuario usuario) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
		this.dataEmissao = dataEmissao;
		this.dataVencimento = dataVencimento;
		this.valorDebito = valorDebito;
		this.ultimaAlteracao = ultimaAlteracao;
		this.cliente = cliente;
		this.parcelamento = parcelamento;
		this.documentoTipo = documentoTipo;
		this.registroAtendimento = registroAtendimento;
		this.imovel = imovel;
		this.localidade = localidade;
		this.financiamentoTipo = financiamentoTipo;
		this.debitoTipo = debitoTipo;
		this.ordemServico = ordemServico;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
		this.indicadoCobrancaMulta = indicadoCobrancaMulta;
		this.usuario = usuario;
	}

	public GuiaPagamento(Integer anoMesReferenciaContabil, Date dataEmissao,
			Date dataVencimento, BigDecimal valorDebito, Date ultimaAlteracao,
			Cliente cliente, Parcelamento parcelamento,
			DocumentoTipo documentoTipo,
			RegistroAtendimento registroAtendimento, Imovel imovel,
			Localidade localidade, FinanciamentoTipo financiamentoTipo,
			DebitoTipo debitoTipo, OrdemServico ordemServico,
			LancamentoItemContabil lancamentoItemContabil,
			DebitoCreditoSituacao debitoCreditoSituacaoAtual,
			DebitoCreditoSituacao debitoCreditoSituacaoAnterior) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
		this.dataEmissao = dataEmissao;
		this.dataVencimento = dataVencimento;
		this.valorDebito = valorDebito;
		this.ultimaAlteracao = ultimaAlteracao;
		this.cliente = cliente;
		this.parcelamento = parcelamento;
		this.documentoTipo = documentoTipo;
		this.registroAtendimento = registroAtendimento;
		this.imovel = imovel;
		this.localidade = localidade;
		this.financiamentoTipo = financiamentoTipo;
		this.debitoTipo = debitoTipo;
		this.ordemServico = ordemServico;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
	}

	/** default constructor */
	public GuiaPagamento() {
	}

	/** minimal constructor */
	public GuiaPagamento(Cliente cliente, Parcelamento parcelamento,
			DocumentoTipo documentoTipo,
			RegistroAtendimento registroAtendimento, Imovel imovel,
			Localidade localidade, FinanciamentoTipo financiamentoTipo,
			DebitoTipo debitoTipo, OrdemServico ordemServico,
			LancamentoItemContabil lancamentoItemContabil,
			DebitoCreditoSituacao debitoCreditoSituacaoAtual,
			DebitoCreditoSituacao debitoCreditoSituacaoAnterior) {
		this.cliente = cliente;
		this.parcelamento = parcelamento;
		this.documentoTipo = documentoTipo;
		this.registroAtendimento = registroAtendimento;
		this.imovel = imovel;
		this.localidade = localidade;
		this.financiamentoTipo = financiamentoTipo;
		this.debitoTipo = debitoTipo;
		this.ordemServico = ordemServico;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnoMesReferenciaContabil() {
		return this.anoMesReferenciaContabil;
	}

	public void setAnoMesReferenciaContabil(Integer anoMesReferenciaContabil) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
	}

	public Date getDataEmissao() {
		return this.dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataVencimento() {
		return this.dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorDebito() {
		if(valorDebito != null){
			return valorDebito.setScale(2);
		}else{
			return valorDebito;	
		}		
	}
	
	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Parcelamento getParcelamento() {
		return this.parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public DocumentoTipo getDocumentoTipo() {
		return this.documentoTipo;
	}

	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return this.registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public FinanciamentoTipo getFinanciamentoTipo() {
		return this.financiamentoTipo;
	}

	public void setFinanciamentoTipo(FinanciamentoTipo financiamentoTipo) {
		this.financiamentoTipo = financiamentoTipo;
	}

	public DebitoTipo getDebitoTipo() {
		return this.debitoTipo;
	}

	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	public OrdemServico getOrdemServico() {
		return this.ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public LancamentoItemContabil getLancamentoItemContabil() {
		return this.lancamentoItemContabil;
	}

	public void setLancamentoItemContabil(
			LancamentoItemContabil lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	public DebitoCreditoSituacao getDebitoCreditoSituacaoAtual() {
		return this.debitoCreditoSituacaoAtual;
	}

	public void setDebitoCreditoSituacaoAtual(
			DebitoCreditoSituacao debitoCreditoSituacaoAtual) {
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
	}

	public DebitoCreditoSituacao getDebitoCreditoSituacaoAnterior() {
		return this.debitoCreditoSituacaoAnterior;
	}

	public void setDebitoCreditoSituacaoAnterior(
			DebitoCreditoSituacao debitoCreditoSituacaoAnterior) {
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
	}

	public Set getClientesGuiaPagamento() {
		return clientesGuiaPagamento;
	}

	public void setClientesGuiaPagamento(Set clientesGuiaPagamento) {
		this.clientesGuiaPagamento = clientesGuiaPagamento;
	}

	/**
	 * @return Retorna o campo indicadoCobrancaMulta.
	 */
	public Short getIndicadoCobrancaMulta() {
		return indicadoCobrancaMulta;
	}

	/**
	 * @param indicadoCobrancaMulta
	 *            O indicadoCobrancaMulta a ser setado.
	 */
	public void setIndicadoCobrancaMulta(Short indicadoCobrancaMulta) {
		this.indicadoCobrancaMulta = indicadoCobrancaMulta;
	}

	/**
	 * @return Retorna o campo debitoACobrarGeral.
	 */
	public GuiaPagamentoGeral getGuiaPagamentoGeral() {
		return guiaPagamentoGeral;
	}

	/**
	 * @param debitoACobrarGeral O debitoACobrarGeral a ser setado.
	 */
	public void setGuiaPagamentoGeral(GuiaPagamentoGeral guiaPagamentoGeral) {
		this.guiaPagamentoGeral = guiaPagamentoGeral;
	}
	
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public GuiaPagamentoGeral getOrigem() {
		return origem;
	}

	public void setOrigem(GuiaPagamentoGeral origem) {
		this.origem = origem;
	}

	public Short getNumeroPrestacaoDebito() {
		return numeroPrestacaoDebito;
	}

	public void setNumeroPrestacaoDebito(Short numeroPrestacaoDebito) {
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
	}

	public Short getNumeroPrestacaoTotal() {
		return numeroPrestacaoTotal;
	}

	public void setNumeroPrestacaoTotal(Short numeroPrestacaoTotal) {
		this.numeroPrestacaoTotal = numeroPrestacaoTotal;
	}
    
    public String getPrestacaoFormatada(){
        String prestacaoFormatada = "";
        
        if(getNumeroPrestacaoDebito() != null &&
                getNumeroPrestacaoTotal() != null){
            prestacaoFormatada = prestacaoFormatada + getNumeroPrestacaoDebito() + " / " + getNumeroPrestacaoTotal();
        }
        
        return  prestacaoFormatada ;
    }
	
	public String[] retornarAtributosInformacoesOperacaoEfetuada(){
		String []atributos = {
				"debitoTipo.descricao"
				, "valorDebito"
				};
			return atributos;		
	}
	
	public String[] retornarLabelsInformacoesOperacaoEfetuada(){
		String []labels = {"Tipo Debito"
				, "Valor"
				};
			return labels;		
	}

	public Integer getAnoGuia() {
		return anoGuia;
	}

	public void setAnoGuia(Integer anoGuia) {
		this.anoGuia = anoGuia;
	}

	public Integer getLotePagamento() {
		return lotePagamento;
	}

	public void setLotePagamento(Integer lotePagamento) {
		this.lotePagamento = lotePagamento;
	}

	public Integer getNumeroGuia() {
		return numeroGuia;
	}

	public void setNumeroGuia(Integer numeroGuia) {
		this.numeroGuia = numeroGuia;
	}


	/**
	 * @return Retorna o campo usuario.
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * @return Retorna o campo indicadorEmitirObservacao.
	 */
	public Short getIndicadorEmitirObservacao() {
		return indicadorEmitirObservacao;
	}

	/**
	 * @param indicadorEmitirObservacao O indicadorEmitirObservacao a ser setado.
	 */
	public void setIndicadorEmitirObservacao(Short indicadorEmitirObservacao) {
		this.indicadorEmitirObservacao = indicadorEmitirObservacao;
	}

	/**
	 * @return Retorna o campo observacao.
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacao O observacao a ser setado.
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param other
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof GuiaPagamento)) {
			return false;
		}
		
		GuiaPagamento castOther = (GuiaPagamento) other;

		return (this.getId().equals(castOther.getId()));
	}

	public String getNumeroGuiaFatura() {
		return numeroGuiaFatura;
	}

	public void setNumeroGuiaFatura(String numeroGuiaFatura) {
		this.numeroGuiaFatura = numeroGuiaFatura;
	}
	
	public String getFormatarAnoMesReferenciaGuia() {

		String anoMesRecebido = "" + this.getAnoGuia();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatado = mes + "/" + ano;

		return anoMesFormatado.toString();
	}
}