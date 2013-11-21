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
package gcom.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.faturamento.conta.MotivoNaoEntregaDocumento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CobrancaDocumento implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private int numeroSequenciaDocumento;

    /** nullable persistent field */
    private Date emissao;

    /** nullable persistent field */
    private BigDecimal valorDesconto;

    /** nullable persistent field */
    private Integer numeroQuadra;

    /** nullable persistent field */
    private BigDecimal valorDocumento;

    /** nullable persistent field */
    private BigDecimal valorTaxa;

    /** nullable persistent field */
    private Integer codigoSetorComercial;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.DocumentoEmissaoForma documentoEmissaoForma;

    /** persistent field */
    private gcom.cobranca.CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando;

    /** persistent field */
    private Imovel imovel;

    /** persistent field */
    private Empresa empresa;

    /** persistent field */
    private gcom.cobranca.DocumentoTipo documentoTipo;

    /** persistent field */
    private ImovelPerfil imovelPerfil;

    /** persistent field */
    private Quadra quadra;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private gcom.cobranca.CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma;

    /** persistent field */
    private MotivoNaoEntregaDocumento motivoNaoEntregaDocumento;
    
    private CobrancaCriterio cobrancaCriterio;
    
    private CobrancaAcao cobrancaAcao;
    
    /** nullable persistent field */
    private BigDecimal valorAcrescimos;
    
    /** nullable persistent field */
    private Date dataSituacaoAcao;

    /** nullable persistent field */
    private Date dataSituacaoDebito;
    
    /** nullable persistent field */
    private Integer sequencialImpressao;
    
    /** nullable persistent field */
    private Short indicadorAntesApos;
    
    /** nullable persistent field */
    private Short indicadorLimite;
    
    /** persistent field */
    private CobrancaDebitoSituacao cobrancaDebitoSituacao;

    /** persistent field */
    private CobrancaAcaoSituacao cobrancaAcaoSituacao;
    
    
    /** persistent field */
    private Cliente cliente;
    
    /** persistent field */
    private Categoria categoria;
    
    /** persistent field */
    private EsferaPoder esferaPoder;
    
    /** persistent field */
    private FiscalizacaoSituacao fiscalizacaoSituacao;
    
    private AtendimentoMotivoEncerramento motivoEncerramento;

    private ResolucaoDiretoria resolucaoDiretoria;
    
    private LigacaoAguaSituacao ligacaoAguaSituacao;
    
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
    
    private Date dataEmissaoPredecessor;
    
    private String numeroDocumentoFatura;
    
    /** nullable persistent field */
    private BigDecimal valorImpostos;
    
    // Constantes
    public final static String INCLUIR_ACRESCIMOS = "1";
    public final static String NAO_INCLUIR_ACRESCIMOS = "2";
    public final static String INCLUIR_ACRESCIMOS_COM_DESCONTO = "3";
    
    
    /**
	 * Construtor de CobrancaDocumento 
	 * 
	 * @param id
	 * @param numeroSequenciaDocumento
	 * @param emissao
	 * @param valorDesconto
	 * @param numeroQuadra
	 * @param valorDocumento
	 * @param valorTaxa
	 * @param codigoSetorComercial
	 * @param ultimaAlteracao
	 * @param documentoEmissaoForma
	 * @param cobrancaAcaoAtividadeComando
	 * @param imovel
	 * @param empresa
	 * @param documentoTipo
	 * @param imovelPerfil
	 * @param quadra
	 * @param localidade
	 * @param cobrancaAcaoAtividadeCronograma
	 * @param motivoNaoEntregaDocumento
	 * @param cobrancaCriterio
	 * @param cobrancaAcao
	 * @param valorAcrescimos
	 */
	public CobrancaDocumento(Integer id, int numeroSequenciaDocumento, Date emissao, BigDecimal valorDesconto, Integer numeroQuadra, BigDecimal valorDocumento, BigDecimal valorTaxa, Integer codigoSetorComercial, Date ultimaAlteracao, DocumentoEmissaoForma documentoEmissaoForma, CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando, Imovel imovel, Empresa empresa, DocumentoTipo documentoTipo, ImovelPerfil imovelPerfil, Quadra quadra, Localidade localidade, CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma, MotivoNaoEntregaDocumento motivoNaoEntregaDocumento, CobrancaCriterio cobrancaCriterio, CobrancaAcao cobrancaAcao, BigDecimal valorAcrescimos) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.numeroSequenciaDocumento = numeroSequenciaDocumento;
		this.emissao = emissao;
		this.valorDesconto = valorDesconto;
		this.numeroQuadra = numeroQuadra;
		this.valorDocumento = valorDocumento;
		this.valorTaxa = valorTaxa;
		this.codigoSetorComercial = codigoSetorComercial;
		this.ultimaAlteracao = ultimaAlteracao;
		this.documentoEmissaoForma = documentoEmissaoForma;
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
		this.imovel = imovel;
		this.empresa = empresa;
		this.documentoTipo = documentoTipo;
		this.imovelPerfil = imovelPerfil;
		this.quadra = quadra;
		this.localidade = localidade;
		this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
		this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
		this.cobrancaCriterio = cobrancaCriterio;
		this.cobrancaAcao = cobrancaAcao;
		this.valorAcrescimos = valorAcrescimos;
	}

	/** full constructor */
    public CobrancaDocumento(int numeroSequenciaDocumento, Date emissao, BigDecimal valorDesconto, Integer numeroQuadra, BigDecimal valorDocumento, BigDecimal valorTaxa, Integer codigoSetorComercial, Date ultimaAlteracao, gcom.cobranca.DocumentoEmissaoForma documentoEmissaoForma, gcom.cobranca.CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando, Imovel imovel, Empresa empresa, gcom.cobranca.DocumentoTipo documentoTipo, ImovelPerfil imovelPerfil, Quadra quadra, Localidade localidade, gcom.cobranca.CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma, MotivoNaoEntregaDocumento motivoNaoEntregaDocumento,CobrancaCriterio cobrancaCriterio) {
        this.numeroSequenciaDocumento = numeroSequenciaDocumento;
        this.emissao = emissao;
        this.valorDesconto = valorDesconto;
        this.numeroQuadra = numeroQuadra;
        this.valorDocumento = valorDocumento;
        this.valorTaxa = valorTaxa;
        this.codigoSetorComercial = codigoSetorComercial;
        this.ultimaAlteracao = ultimaAlteracao;
        this.documentoEmissaoForma = documentoEmissaoForma;
        this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
        this.imovel = imovel;
        this.empresa = empresa;
        this.documentoTipo = documentoTipo;
        this.imovelPerfil = imovelPerfil;
        this.quadra = quadra;
        this.localidade = localidade;
        this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
        this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
        this.cobrancaCriterio = cobrancaCriterio;
    }

    /** default constructor */
    public CobrancaDocumento() {
    }

    /** minimal constructor */
    public CobrancaDocumento(int numeroSequenciaDocumento, gcom.cobranca.DocumentoEmissaoForma documentoEmissaoForma, gcom.cobranca.CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando, Imovel imovel, Empresa empresa, gcom.cobranca.DocumentoTipo documentoTipo, ImovelPerfil imovelPerfil, Quadra quadra, Localidade localidade, gcom.cobranca.CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma, MotivoNaoEntregaDocumento motivoNaoEntregaDocumento) {
        this.numeroSequenciaDocumento = numeroSequenciaDocumento;
        this.documentoEmissaoForma = documentoEmissaoForma;
        this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
        this.imovel = imovel;
        this.empresa = empresa;
        this.documentoTipo = documentoTipo;
        this.imovelPerfil = imovelPerfil;
        this.quadra = quadra;
        this.localidade = localidade;
        this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
        this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumeroSequenciaDocumento() {
        return this.numeroSequenciaDocumento;
    }

    public void setNumeroSequenciaDocumento(int numeroSequenciaDocumento) {
        this.numeroSequenciaDocumento = numeroSequenciaDocumento;
    }

    public Date getEmissao() {
        return this.emissao;
    }

    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    public BigDecimal getValorDesconto() {
        return this.valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Integer getNumeroQuadra() {
        return this.numeroQuadra;
    }

    public void setNumeroQuadra(Integer numeroQuadra) {
        this.numeroQuadra = numeroQuadra;
    }

    public BigDecimal getValorDocumento() {
        return this.valorDocumento;
    }

    public void setValorDocumento(BigDecimal valorDocumento) {
        this.valorDocumento = valorDocumento;
    }

    public BigDecimal getValorTaxa() {
        return this.valorTaxa;
    }

    public void setValorTaxa(BigDecimal valorTaxa) {
        this.valorTaxa = valorTaxa;
    }

    public Integer getCodigoSetorComercial() {
        return this.codigoSetorComercial;
    }

    public void setCodigoSetorComercial(Integer codigoSetorComercial) {
        this.codigoSetorComercial = codigoSetorComercial;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.DocumentoEmissaoForma getDocumentoEmissaoForma() {
        return this.documentoEmissaoForma;
    }

    public void setDocumentoEmissaoForma(gcom.cobranca.DocumentoEmissaoForma documentoEmissaoForma) {
        this.documentoEmissaoForma = documentoEmissaoForma;
    }

    public gcom.cobranca.CobrancaAcaoAtividadeComando getCobrancaAcaoAtividadeComando() {
        return this.cobrancaAcaoAtividadeComando;
    }

    public void setCobrancaAcaoAtividadeComando(gcom.cobranca.CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
        this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
    }

    public Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public gcom.cobranca.DocumentoTipo getDocumentoTipo() {
        return this.documentoTipo;
    }

    public void setDocumentoTipo(gcom.cobranca.DocumentoTipo documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public ImovelPerfil getImovelPerfil() {
        return this.imovelPerfil;
    }

    public void setImovelPerfil(ImovelPerfil imovelPerfil) {
        this.imovelPerfil = imovelPerfil;
    }

    public Quadra getQuadra() {
        return this.quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public gcom.cobranca.CobrancaAcaoAtividadeCronograma getCobrancaAcaoAtividadeCronograma() {
        return this.cobrancaAcaoAtividadeCronograma;
    }

    public void setCobrancaAcaoAtividadeCronograma(gcom.cobranca.CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma) {
        this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
    }

    public MotivoNaoEntregaDocumento getMotivoNaoEntregaDocumento() {
        return this.motivoNaoEntregaDocumento;
    }

    public void setMotivoNaoEntregaDocumento(MotivoNaoEntregaDocumento motivoNaoEntregaDocumento) {
        this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
    }

    public CobrancaCriterio getCobrancaCriterio() {
		return cobrancaCriterio;
	}

	public void setCobrancaCriterio(CobrancaCriterio cobrancaCriterio) {
		this.cobrancaCriterio = cobrancaCriterio;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public CobrancaAcao getCobrancaAcao() {
		return cobrancaAcao;
	}

	public void setCobrancaAcao(CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}
	
    public BigDecimal getValorAcrescimos() {
		return valorAcrescimos;
	}

	public void setValorAcrescimos(BigDecimal valorAcrescimos) {
		this.valorAcrescimos = valorAcrescimos;
	}

//	public Date getDataValidade(){
//		
//		Date dataValidade = this.getEmissao();
//		
//		if (dataValidade != null && this.getCobrancaAcao() != null && 
//			this.getCobrancaAcao().getNumeroDiasValidade() != null){
//			
//			Calendar dataCalendar = Calendar.getInstance();
//			dataCalendar.setTime(dataValidade);
//			dataCalendar.add(Calendar.DAY_OF_MONTH, this.getCobrancaAcao().getNumeroDiasValidade().intValue());
//		
//			dataValidade = dataCalendar.getTime();
//		}else{
//			dataValidade = Util.adicionarNumeroDiasDeUmaData(dataValidade, 7);
//		}
//		
//		return dataValidade;
//	}

	/**
	 * @return Retorna o campo cobrancaAcaoSituacao.
	 */
	public CobrancaAcaoSituacao getCobrancaAcaoSituacao() {
		return cobrancaAcaoSituacao;
	}

	/**
	 * @param cobrancaAcaoSituacao O cobrancaAcaoSituacao a ser setado.
	 */
	public void setCobrancaAcaoSituacao(CobrancaAcaoSituacao cobrancaAcaoSituacao) {
		this.cobrancaAcaoSituacao = cobrancaAcaoSituacao;
	}

	/**
	 * @return Retorna o campo cobrancaDebitoSituacao.
	 */
	public CobrancaDebitoSituacao getCobrancaDebitoSituacao() {
		return cobrancaDebitoSituacao;
	}

	/**
	 * @param cobrancaDebitoSituacao O cobrancaDebitoSituacao a ser setado.
	 */
	public void setCobrancaDebitoSituacao(
			CobrancaDebitoSituacao cobrancaDebitoSituacao) {
		this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
	}

	/**
	 * @return Retorna o campo dataSituacaoAcao.
	 */
	public Date getDataSituacaoAcao() {
		return dataSituacaoAcao;
	}

	/**
	 * @param dataSituacaoAcao O dataSituacaoAcao a ser setado.
	 */
	public void setDataSituacaoAcao(Date dataSituacaoAcao) {
		this.dataSituacaoAcao = dataSituacaoAcao;
	}

	/**
	 * @return Retorna o campo dataSituacaoDebito.
	 */
	public Date getDataSituacaoDebito() {
		return dataSituacaoDebito;
	}

	/**
	 * @param dataSituacaoDebito O dataSituacaoDebito a ser setado.
	 */
	public void setDataSituacaoDebito(Date dataSituacaoDebito) {
		this.dataSituacaoDebito = dataSituacaoDebito;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public EsferaPoder getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(EsferaPoder esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return fiscalizacaoSituacao;
	}

	public void setFiscalizacaoSituacao(FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}

	public Short getIndicadorAntesApos() {
		return indicadorAntesApos;
	}

	public void setIndicadorAntesApos(Short indicadorAntesApos) {
		this.indicadorAntesApos = indicadorAntesApos;
	}

	public Short getIndicadorLimite() {
		return indicadorLimite;
	}

	public void setIndicadorLimite(Short indicadorLimite) {
		this.indicadorLimite = indicadorLimite;
	}

	public Integer getSequencialImpressao() {
		return sequencialImpressao;
	}

	public void setSequencialImpressao(Integer sequencialImpressao) {
		this.sequencialImpressao = sequencialImpressao;
	}

	public AtendimentoMotivoEncerramento getMotivoEncerramento() {
		return motivoEncerramento;
	}

	public void setMotivoEncerramento(
			AtendimentoMotivoEncerramento motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}

	public ResolucaoDiretoria getResolucaoDiretoria() {
		return resolucaoDiretoria;
	}

	public void setResolucaoDiretoria(ResolucaoDiretoria resolucaoDiretoria) {
		this.resolucaoDiretoria = resolucaoDiretoria;
	}

	/**
	 * @return Retorna o campo ligacaoAguaSituacao.
	 */
	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	/**
	 * @param ligacaoAguaSituacao O ligacaoAguaSituacao a ser setado.
	 */
	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	/**
	 * @return Retorna o campo ligacaoEsgotoSituacao.
	 */
	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	/**
	 * @param ligacaoEsgotoSituacao O ligacaoEsgotoSituacao a ser setado.
	 */
	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	/**
	 * @return Retorna o campo dataEmissaoPredecessor.
	 */
	public Date getDataEmissaoPredecessor() {
		return dataEmissaoPredecessor;
	}

	/**
	 * @param dataEmissaoPredecessor O dataEmissaoPredecessor a ser setado.
	 */
	public void setDataEmissaoPredecessor(Date dataEmissaoPredecessor) {
		this.dataEmissaoPredecessor = dataEmissaoPredecessor;
	}

	public String getNumeroDocumentoFatura() {
		return numeroDocumentoFatura;
	}

	public void setNumeroDocumentoFatura(String numeroDocumentoFatura) {
		this.numeroDocumentoFatura = numeroDocumentoFatura;
	}

	public BigDecimal getValorImpostos() {
		return valorImpostos;
	}

	public void setValorImpostos(BigDecimal valorImpostos) {
		this.valorImpostos = valorImpostos;
	}
	
}