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
* Thiago Silva Toscano de Brito
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
import gcom.cadastro.CpfTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativadorMovimentoReg;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class NegativadorMovimentoReg extends ObjetoTransacao implements Serializable {
	
	public static final int ATRIBUTOS_EXCLUIR_NEGATIVACAO_ONLINE = 1132; // Operacao.OPERACAO_EXCLUIR_NEGATIVACAO_ONLINE

	public Filtro retornaFiltro() {
		FiltroNegativadorMovimentoReg filtroNegativadorExclusaoMotivo = new FiltroNegativadorMovimentoReg();
		filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoReg.ID,this.getId()));		
		return filtroNegativadorExclusaoMotivo;
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoReg.IMOVEL);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoReg.NEGATIVADOR_EXLUSAO_MOTIVO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoReg.USUARIO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoReg.COBRANCA_DEBITO_SITUACAO);
		return filtro;
	}
	
	private static int numeroProximoRegistro = 1;
	
	public static synchronized int getNumeroProximoRegistro() {
		return numeroProximoRegistro++;
	}
	
	public static void resetNumeroProximoRegistro(){
		numeroProximoRegistro = 1;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String conteudoRegistro;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Integer codigoExclusaoTipo;

    /** persistent field */
    private Short indicadorAceito;

    /** persistent field */
    private Short indicadorCorrecao;

    /** nullable persistent field */
    private BigDecimal valorDebito;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_EXCLUIR_NEGATIVACAO_ONLINE})
    private Date dataSituacaoDebito;

    /** nullable persistent field */
    private Integer codigoSetorComercial;

    /** nullable persistent field */
    private Integer numeroQuadra;

    /** nullable persistent field */
    private String numeroCpf;

    /** nullable persistent field */
    private String numeroCnpj;

    /** persistent field */
    private Quadra quadra;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private Imovel imovel;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_EXCLUIR_NEGATIVACAO_ONLINE})
    private gcom.cobranca.NegativadorExclusaoMotivo negativadorExclusaoMotivo;

    /** persistent field */
    private ImovelPerfil imovelPerfil;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_EXCLUIR_NEGATIVACAO_ONLINE})
    private gcom.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao;

    /** persistent field */
    private gcom.cobranca.NegativadorMovimentoReg negativadorMovimentoRegInclusao;

    /** persistent field */
    private gcom.cobranca.NegativacaoCriterio negativacaoCriterio;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_EXCLUIR_NEGATIVACAO_ONLINE})
    private Usuario usuario;

    /** persistent field */
    private Cliente cliente;

    /** persistent field */
    private Categoria categoria;

    /** persistent field */
    private gcom.cobranca.NegativadorRegistroTipo negativadorRegistroTipo;

    /** persistent field */
    private gcom.cobranca.NegativadorMovimento negativadorMovimento;

    /** persistent field */
    private Set negativadorMovimentoReg;
    
    /** persistent field */
    private short indicadorSituacaoDefinitiva;
    
    /** persistent field */
    private Integer numeroRegistro;
    
    private CpfTipo cpfTipo;
    
    private CobrancaSituacao cobrancaSituacao;
    //private BigDecimal valorParceladoEntrada;
    
    private Short indicadorItemAtualizado;
    
    private Usuario usuarioCorrecao;

    public Usuario getUsuarioCorrecao() {
		return usuarioCorrecao;
	}

	public void setUsuarioCorrecao(Usuario usuarioCorrecao) {
		this.usuarioCorrecao = usuarioCorrecao;
	}
	
	//************************************************************
	// RM3755
	// Autor: Ivan Sergio
	// Data: 10/01/2011
	//************************************************************
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	//************************************************************

	/** full constructor */
    public NegativadorMovimentoReg(Integer id, String conteudoRegistro, Date ultimaAlteracao, Integer codigoExclusaoTipo, Short indicadorAceito, Short indicadorCorrecao, BigDecimal valorDebito, Date dataSituacaoDebito, Integer codigoSetorComercial, Integer numeroQuadra, String numeroCpf, String numeroCnpj, Quadra quadra, Localidade localidade, Imovel imovel, gcom.cobranca.NegativadorExclusaoMotivo negativadorExclusaoMotivo, ImovelPerfil imovelPerfil, gcom.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao, gcom.cobranca.NegativadorMovimentoReg negativadorMovimentoRegInclusao, gcom.cobranca.NegativacaoCriterio negativacaoCriterio, Usuario usuario, Cliente cliente, Categoria categoria, gcom.cobranca.NegativadorRegistroTipo negativadorRegistroTipo, gcom.cobranca.NegativadorMovimento negativadorMovimento, Set negativadorMovimentoReg) {
        this.id = id;
        this.conteudoRegistro = conteudoRegistro;
        this.ultimaAlteracao = ultimaAlteracao;
        this.codigoExclusaoTipo = codigoExclusaoTipo;
        this.indicadorAceito = indicadorAceito;
        this.indicadorCorrecao = indicadorCorrecao;
        this.valorDebito = valorDebito;
        this.dataSituacaoDebito = dataSituacaoDebito;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.numeroCpf = numeroCpf;
        this.numeroCnpj = numeroCnpj;
        this.quadra = quadra;
        this.localidade = localidade;
        this.imovel = imovel;
        this.negativadorExclusaoMotivo = negativadorExclusaoMotivo;
        this.imovelPerfil = imovelPerfil;
        this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
        this.negativadorMovimentoRegInclusao = negativadorMovimentoRegInclusao;
        this.negativacaoCriterio = negativacaoCriterio;
        this.usuario = usuario;
        this.cliente = cliente;
        this.categoria = categoria;
        this.negativadorRegistroTipo = negativadorRegistroTipo;
        this.negativadorMovimento = negativadorMovimento;
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    /** default constructor */
    public NegativadorMovimentoReg() {
    }

    /** minimal constructor */
    public NegativadorMovimentoReg(Integer id, Short indicadorAceito, Short indicadorCorrecao, Quadra quadra, Localidade localidade, Imovel imovel, gcom.cobranca.NegativadorExclusaoMotivo negativadorExclusaoMotivo, ImovelPerfil imovelPerfil, gcom.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao, gcom.cobranca.NegativadorMovimentoReg negativadorMovimentoRegInclusao, gcom.cobranca.NegativacaoCriterio negativacaoCriterio, Usuario usuario, Cliente cliente, Categoria categoria, gcom.cobranca.NegativadorRegistroTipo negativadorRegistroTipo, gcom.cobranca.NegativadorMovimento negativadorMovimento, Set negativadorMovimentoReg) {
        this.id = id;
        this.indicadorAceito = indicadorAceito;
        this.indicadorCorrecao = indicadorCorrecao;
        this.quadra = quadra;
        this.localidade = localidade;
        this.imovel = imovel;
        this.negativadorExclusaoMotivo = negativadorExclusaoMotivo;
        this.imovelPerfil = imovelPerfil;
        this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
        this.negativadorMovimentoRegInclusao = negativadorMovimentoRegInclusao;
        this.negativacaoCriterio = negativacaoCriterio;
        this.usuario = usuario;
        this.cliente = cliente;
        this.categoria = categoria;
        this.negativadorRegistroTipo = negativadorRegistroTipo;
        this.negativadorMovimento = negativadorMovimento;
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConteudoRegistro() {
        return this.conteudoRegistro;
    }

    public void setConteudoRegistro(String ConteudoRegistro) {
        this.conteudoRegistro = ConteudoRegistro;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getCodigoExclusaoTipo() {
        return this.codigoExclusaoTipo;
    }

    public void setCodigoExclusaoTipo(Integer codigoExclusaoTipo) {
        this.codigoExclusaoTipo = codigoExclusaoTipo;
    }

    public Short getIndicadorAceito() {
        return this.indicadorAceito;
    }

    public void setIndicadorAceito(Short indicadorAceito) {
        this.indicadorAceito = indicadorAceito;
    }

    public Short getIndicadorCorrecao() {
        return this.indicadorCorrecao;
    }

    public void setIndicadorCorrecao(Short indicadorCorrecao) {
        this.indicadorCorrecao = indicadorCorrecao;
    }

    public BigDecimal getValorDebito() {
        return this.valorDebito;
    }

    public void setValorDebito(BigDecimal valorDebito) {
        this.valorDebito = valorDebito;
    }

    public Date getDataSituacaoDebito() {
        return this.dataSituacaoDebito;
    }

    public void setDataSituacaoDebito(Date dataSituacaoDebito) {
        this.dataSituacaoDebito = dataSituacaoDebito;
    }

    public Integer getCodigoSetorComercial() {
        return this.codigoSetorComercial;
    }

    public void setCodigoSetorComercial(Integer codigoSetorComercial) {
        this.codigoSetorComercial = codigoSetorComercial;
    }

    public Integer getNumeroQuadra() {
        return this.numeroQuadra;
    }

    public void setNumeroQuadra(Integer numeroQuadra) {
        this.numeroQuadra = numeroQuadra;
    }

    public String getNumeroCpf() {
        return this.numeroCpf;
    }

    public void setNumeroCpf(String numeroCpf) {
        this.numeroCpf = numeroCpf;
    }

    public String getNumeroCnpj() {
        return this.numeroCnpj;
    }

    public void setNumeroCnpj(String numeroCnpj) {
        this.numeroCnpj = numeroCnpj;
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

    public Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public gcom.cobranca.NegativadorExclusaoMotivo getNegativadorExclusaoMotivo() {
        return this.negativadorExclusaoMotivo;
    }

    public void setNegativadorExclusaoMotivo(gcom.cobranca.NegativadorExclusaoMotivo negativadorExclusaoMotivo) {
        this.negativadorExclusaoMotivo = negativadorExclusaoMotivo;
    }

    public ImovelPerfil getImovelPerfil() {
        return this.imovelPerfil;
    }

    public void setImovelPerfil(ImovelPerfil imovelPerfil) {
        this.imovelPerfil = imovelPerfil;
    }

    public gcom.cobranca.CobrancaDebitoSituacao getCobrancaDebitoSituacao() {
        return this.cobrancaDebitoSituacao;
    }

    public void setCobrancaDebitoSituacao(gcom.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao) {
        this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
    }

    public gcom.cobranca.NegativadorMovimentoReg getNegativadorMovimentoRegInclusao() {
        return this.negativadorMovimentoRegInclusao;
    }

    public void setNegativadorMovimentoRegInclusao(gcom.cobranca.NegativadorMovimentoReg negativadorMovimentoRegInclusao) {
        this.negativadorMovimentoRegInclusao = negativadorMovimentoRegInclusao;
    }

    public gcom.cobranca.NegativacaoCriterio getNegativacaoCriterio() {
        return this.negativacaoCriterio;
    }

    public void setNegativacaoCriterio(gcom.cobranca.NegativacaoCriterio negativacaoCriterio) {
        this.negativacaoCriterio = negativacaoCriterio;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public gcom.cobranca.NegativadorRegistroTipo getNegativadorRegistroTipo() {
        return this.negativadorRegistroTipo;
    }

    public void setNegativadorRegistroTipo(gcom.cobranca.NegativadorRegistroTipo negativadorRegistroTipo) {
        this.negativadorRegistroTipo = negativadorRegistroTipo;
    }

    public gcom.cobranca.NegativadorMovimento getNegativadorMovimento() {
        return this.negativadorMovimento;
    }

    public void setNegativadorMovimento(gcom.cobranca.NegativadorMovimento negativadorMovimento) {
        this.negativadorMovimento = negativadorMovimento;
    }

    public Set getNegativadorMovimentoReg() {
        return this.negativadorMovimentoReg;
    }

    public void setNegativadorMovimentoReg(Set negativadorMovimentoReg) {
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public short getIndicadorSituacaoDefinitiva() {
		return indicadorSituacaoDefinitiva;
	}

	public void setIndicadorSituacaoDefinitiva(short indicadorSituacaoDefinitiva) {
		this.indicadorSituacaoDefinitiva = indicadorSituacaoDefinitiva;
	}

	public Integer getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(Integer numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	/**
	 * @return Retorna o campo cpfTipo.
	 */
	public CpfTipo getCpfTipo() {
		return cpfTipo;
	}

	/**
	 * @param cpfTipo O cpfTipo a ser setado.
	 */
	public void setCpfTipo(CpfTipo cpfTipo) {
		this.cpfTipo = cpfTipo;
	}

	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String[] labels = {"imovel.id"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String[] labels = {"Matrícula"};
		return labels;		
	}

	public CobrancaSituacao getCobrancaSituacao() {
		return cobrancaSituacao;
	}

	public void setCobrancaSituacao(CobrancaSituacao cobrancaSituacao) {
		this.cobrancaSituacao = cobrancaSituacao;
	}

	public Short getIndicadorItemAtualizado() {
		return indicadorItemAtualizado;
	}

	public void setIndicadorItemAtualizado(Short indicadorItemAtualizado) {
		this.indicadorItemAtualizado = indicadorItemAtualizado;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	
}
