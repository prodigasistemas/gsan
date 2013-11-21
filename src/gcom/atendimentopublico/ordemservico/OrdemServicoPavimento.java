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


package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class OrdemServicoPavimento extends ObjetoTransacao {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public static final int OPERACAO_ATUALIZAR_ORDEM_REPAVIMENTACAO_PROCESSO_ACEITE = 1630;
	
	/** identifier field */
	@ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ORDEM_REPAVIMENTACAO_PROCESSO_ACEITE})
    private Integer id;

    /** nullable persistent field */
    private BigDecimal areaPavimentoRua;

    /** nullable persistent field */
    private BigDecimal areaPavimentoCalcada;

    /** nullable persistent field */
    private BigDecimal areaPavimentoRuaRetorno;

    /** nullable persistent field */
    private BigDecimal areaPavimentoCalcadaRetorno;

    /** nullable persistent field */
    private Date dataGeracao;
    
    private Date dataExecucao;
    
    private OrdemServico ordemServico; 

    private PavimentoRua pavimentoRua;
    
    private PavimentoCalcada pavimentoCalcada;
    
    private PavimentoRua pavimentoRuaRetorno;
    
    private PavimentoCalcada pavimentoCalcadaRetorno;
    
    private UnidadeOrganizacional unidadeRepavimentadora;
    
    private Short indicadorAceite;
    
    private Date dataAceite;
    
    private Usuario usuarioAceite;
    
    private String descricaoMotivoAceite;
    
    private String observacao;
    
    private String descricaoRejeicao;
    
    private Date dataRejeicao;
    
    private Usuario usuarioRejeicao;
    
    private MotivoRejeicao motivoRejeicao;
    
    
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ORDEM_REPAVIMENTACAO_PROCESSO_ACEITE})
    private Date ultimaAlteracao;
    
   
    public OrdemServicoPavimento( BigDecimal areaPavimentoRua, BigDecimal areaPavimentoCalcada, BigDecimal areaPavimentoRuaRetorno, BigDecimal areaPavimentoCalcadaRetorno, Date dataGeracao, OrdemServico ordemServico, PavimentoRua pavimentoRua, PavimentoCalcada pavimentoCalcada, PavimentoRua pavimentoRuaRetorno, PavimentoCalcada pavimentoCalcadaRetorno,Date dataExecucao) {
		this.areaPavimentoRua = areaPavimentoRua;
		this.areaPavimentoCalcada = areaPavimentoCalcada;
		this.areaPavimentoRuaRetorno = areaPavimentoRuaRetorno;
		this.areaPavimentoCalcadaRetorno = areaPavimentoCalcadaRetorno;
		this.dataGeracao = dataGeracao;
		this.ordemServico = ordemServico;
		this.pavimentoRua = pavimentoRua;
		this.pavimentoCalcada = pavimentoCalcada;
		this.pavimentoRuaRetorno = pavimentoRuaRetorno;
		this.pavimentoCalcadaRetorno = pavimentoCalcadaRetorno;
		this.dataExecucao = dataExecucao;
		
	}

	/** default constructor */
    public OrdemServicoPavimento() {
    }

   /** minimal constructor */
    public OrdemServicoPavimento(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }



	public BigDecimal getAreaPavimentoCalcada() {
		return areaPavimentoCalcada;
	}
	
	public void setAreaPavimentoCalcada(BigDecimal areaPavimentoCalcada) {
		this.areaPavimentoCalcada = areaPavimentoCalcada;
	}
	
	public BigDecimal getAreaPavimentoCalcadaRetorno() {
		return areaPavimentoCalcadaRetorno;
	}
	
	public void setAreaPavimentoCalcadaRetorno(BigDecimal areaPavimentoCalcadaRetorno) {
		this.areaPavimentoCalcadaRetorno = areaPavimentoCalcadaRetorno;
	}
	
	public BigDecimal getAreaPavimentoRua() {
		return areaPavimentoRua;
	}
	
	public void setAreaPavimentoRua(BigDecimal areaPavimentoRua) {
		this.areaPavimentoRua = areaPavimentoRua;
	}
	
	public BigDecimal getAreaPavimentoRuaRetorno() {
		return areaPavimentoRuaRetorno;
	}
	
	public void setAreaPavimentoRuaRetorno(BigDecimal areaPavimentoRuaRetorno) {
		this.areaPavimentoRuaRetorno = areaPavimentoRuaRetorno;
	}
	
	public Date getDataGeracao() {
		return dataGeracao;
	}
	
	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}
	
	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	
	public PavimentoCalcada getPavimentoCalcada() {
		return pavimentoCalcada;
	}
	
	public void setPavimentoCalcada(PavimentoCalcada pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}
	
	public PavimentoRua getPavimentoRua() {
		return pavimentoRua;
	}
	
	public void setPavimentoRua(PavimentoRua pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}
	
	
	
	public PavimentoCalcada getPavimentoCalcadaRetorno() {
		return pavimentoCalcadaRetorno;
	}
	
	public void setPavimentoCalcadaRetorno(PavimentoCalcada pavimentoCalcadaRetorno) {
		this.pavimentoCalcadaRetorno = pavimentoCalcadaRetorno;
	}
	
	/**
	 * @return Retorna o campo dataExecucao.
	 */
	public Date getDataExecucao() {
		return dataExecucao;
	}
	
	/**
	 * @param dataExecucao O dataExecucao a ser setado.
	 */
	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}
	
	public PavimentoRua getPavimentoRuaRetorno() {
		return pavimentoRuaRetorno;
	}
	
	public void setPavimentoRuaRetorno(PavimentoRua pavimentoRuaRetorno) {
		this.pavimentoRuaRetorno = pavimentoRuaRetorno;
	}
	
	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Returns the unidadeRepavimentadora.
	 */
	public UnidadeOrganizacional getUnidadeRepavimentadora() {
		return unidadeRepavimentadora;
	}

	/**
	 * @param unidadeRepavimentadora The unidadeRepavimentadora to set.
	 */
	public void setUnidadeRepavimentadora(
			UnidadeOrganizacional unidadeRepavimentadora) {
		this.unidadeRepavimentadora = unidadeRepavimentadora;
	}

	public Date getDataAceite() {
		return dataAceite;
	}

	public void setDataAceite(Date dataAceite) {
		this.dataAceite = dataAceite;
	}

	public Short getIndicadorAceite() {
		return indicadorAceite;
	}

	public void setIndicadorAceite(Short indicadorAceite) {
		this.indicadorAceite = indicadorAceite;
	}

	public Usuario getUsuarioAceite() {
		return usuarioAceite;
	}

	public void setUsuarioAceite(Usuario usuarioAceite) {
		this.usuarioAceite = usuarioAceite;
	}

	/**
	 * @return Returns the descricaoMotivoAceite.
	 */
	public String getDescricaoMotivoAceite() {
		return descricaoMotivoAceite;
	}

	/**
	 * @param descricaoMotivoAceite The descricaoMotivoAceite to set.
	 */
	public void setDescricaoMotivoAceite(String descricaoMotivoAceite) {
		this.descricaoMotivoAceite = descricaoMotivoAceite;
	}

	public Date getDataRejeicao() {
		return dataRejeicao;
	}

	public void setDataRejeicao(Date dataRejeicao) {
		this.dataRejeicao = dataRejeicao;
	}

	public String getDescricaoRejeicao() {
		return descricaoRejeicao;
	}

	public void setDescricaoRejeicao(String descricaoRejeicao) {
		this.descricaoRejeicao = descricaoRejeicao;
	}

	public MotivoRejeicao getMotivoRejeicao() {
		return motivoRejeicao;
	}

	public void setMotivoRejeicao(MotivoRejeicao motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}

	public Usuario getUsuarioRejeicao() {
		return usuarioRejeicao;
	}

	public void setUsuarioRejeicao(Usuario usuarioRejeicao) {
		this.usuarioRejeicao = usuarioRejeicao;
	}

	/**
	 * @return Returns the ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao The ultimaAlteracao to set.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroOrdemServicoPavimento filtroOrdemServicoPavimento = new FiltroOrdemServicoPavimento();
		
		filtroOrdemServicoPavimento.adicionarCaminhoParaCarregamentoEntidade("id");
		filtroOrdemServicoPavimento.adicionarCaminhoParaCarregamentoEntidade("ultimaAlteracao");
		filtroOrdemServicoPavimento.adicionarParametro(
				new ParametroSimples(FiltroOrdemServicoPavimento.ID, 
					this.getId()));
		
		return null;
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		FiltroOrdemServicoPavimento filtroOrdemServicoPavimento = new FiltroOrdemServicoPavimento();
		filtroOrdemServicoPavimento.adicionarParametro(new ParametroSimples(
				FiltroOrdemServicoPavimento.ID, this.getId()));
		
		return filtroOrdemServicoPavimento;
	}

	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}

	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"ultimaAlteracao", "id"};
		return labels;		
	}
	
		
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada(){
		String []labels = {"Ultima Alteracao" , "Codigo"
				};
			return labels;		
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
}




