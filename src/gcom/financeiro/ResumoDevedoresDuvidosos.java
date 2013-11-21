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
package gcom.financeiro;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.financeiro.lancamento.LancamentoItem;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoTipo;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ResumoDevedoresDuvidosos extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferenciaContabil;

    /** nullable persistent field */
    private Integer anoMesReferenciaArrecadacao;

    /** nullable persistent field */
    private Short sequencialTipoLancamento;

    /** nullable persistent field */
    private Short sequencialItemTipoLancamento;

    /** persistent field */
    private BigDecimal valorBaixado;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private GerenciaRegional gerenciaRegional;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private Categoria categoria;

    /** persistent field */
    private LancamentoItemContabil lancamentoItemContabil;

    /** persistent field */
    private LancamentoTipo lancamentoTipo;

    /** persistent field */
    private LancamentoItem lancamentoItem;

    /** default constructor */
    public ResumoDevedoresDuvidosos() {
    }
    
    public ResumoDevedoresDuvidosos(int anoMesReferenciaContabil, Integer anoMesReferenciaArrecadacao, Short sequencialTipoLancamento, Short sequencialItemTipoLancamento, BigDecimal valorBaixado, Date ultimaAlteracao, GerenciaRegional gerenciaRegional, Localidade localidade, Categoria categoria, LancamentoItemContabil lancamentoItemContabil, LancamentoTipo lancamentoTipo, LancamentoItem lancamentoItem) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
		this.sequencialTipoLancamento = sequencialTipoLancamento;
		this.sequencialItemTipoLancamento = sequencialItemTipoLancamento;
		this.valorBaixado = valorBaixado;
		this.ultimaAlteracao = ultimaAlteracao;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.categoria = categoria;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.lancamentoTipo = lancamentoTipo;
		this.lancamentoItem = lancamentoItem;
	}

	/** constructor para [UC00485] Gerar Resumo dos Devedores Duvidosos */
    public ResumoDevedoresDuvidosos(BigDecimal valorBaixado, 
    	Localidade localidade, GerenciaRegional gerenciaRegional, Categoria categoria) {
        
    	this.valorBaixado = valorBaixado;        
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.categoria = categoria;
    }

    /** constructor para [UC00485] Gerar Resumo dos Devedores Duvidosos */
    public ResumoDevedoresDuvidosos(BigDecimal valorBaixado, Short sequenciaItemTipoLancamento, 
    	LancamentoItemContabil lancamentoItemContabil, Localidade localidade, 
    	GerenciaRegional gerenciaRegional, Categoria categoria) {
    	
    	this.valorBaixado = valorBaixado;        
        this.sequencialItemTipoLancamento = sequenciaItemTipoLancamento;        
        this.lancamentoItemContabil = lancamentoItemContabil;              
        this.localidade = localidade;
        this.gerenciaRegional = gerenciaRegional;
        this.categoria = categoria;
    }
    
    public Integer getAnoMesReferenciaArrecadacao() {
		return anoMesReferenciaArrecadacao;
	}

	public void setAnoMesReferenciaArrecadacao(Integer anoMesReferenciaArrecadacao) {
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
	}

	public int getAnoMesReferenciaContabil() {
		return anoMesReferenciaContabil;
	}

	public void setAnoMesReferenciaContabil(int anoMesReferenciaContabil) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LancamentoItem getLancamentoItem() {
		return lancamentoItem;
	}

	public void setLancamentoItem(LancamentoItem lancamentoItem) {
		this.lancamentoItem = lancamentoItem;
	}

	public LancamentoItemContabil getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}

	public void setLancamentoItemContabil(
			LancamentoItemContabil lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	public LancamentoTipo getLancamentoTipo() {
		return lancamentoTipo;
	}

	public void setLancamentoTipo(LancamentoTipo lancamentoTipo) {
		this.lancamentoTipo = lancamentoTipo;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Short getSequencialItemTipoLancamento() {
		return sequencialItemTipoLancamento;
	}

	public void setSequencialItemTipoLancamento(Short sequencialItemTipoLancamento) {
		this.sequencialItemTipoLancamento = sequencialItemTipoLancamento;
	}

	public Short getSequencialTipoLancamento() {
		return sequencialTipoLancamento;
	}

	public void setSequencialTipoLancamento(Short sequencialTipoLancamento) {
		this.sequencialTipoLancamento = sequencialTipoLancamento;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorBaixado() {
		return valorBaixado;
	}

	public void setValorBaixado(BigDecimal valorBaixado) {
		this.valorBaixado = valorBaixado;
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

		FiltroResumoDevedoresDuvidosos filtro = new FiltroResumoDevedoresDuvidosos();
		filtro.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		filtro.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtro.adicionarCaminhoParaCarregamentoEntidade("categoria");
		filtro.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");		
		filtro.adicionarCaminhoParaCarregamentoEntidade("lancamentoTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("lancamentoItem");
		
		filtro.adicionarParametro(
			new ParametroSimples(FiltroResumoDevedoresDuvidosos.ID, this.getId()));
		
		return filtro;
	}	

}
