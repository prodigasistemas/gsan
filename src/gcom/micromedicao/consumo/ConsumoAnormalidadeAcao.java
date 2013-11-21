/*
* Copyright (C) 2007-2007 the GSAN -Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place -Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN -Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.micromedicao.consumo;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ConsumoAnormalidadeAcao implements Serializable {
	private static final long serialVersionUID = 1L;
   
    private Integer id;

    private BigDecimal numerofatorConsumoMes1;

    private BigDecimal numerofatorConsumoMes2;

    private BigDecimal numerofatorConsumoMes3;

    private Short indicadorGeracaoCartaMes1;

    private Short indicadorGeracaoCartaMes2;

    private Short indicadorGeracaoCartaMes3;

    private Short indicadorUso;

    private Date ultimaAlteracao;

    private ConsumoAnormalidade consumoAnormalidade;	

    private Categoria categoria;

    private ImovelPerfil imovelPerfil;

    private LeituraAnormalidadeConsumo leituraAnormalidadeConsumoMes1;

    private LeituraAnormalidadeConsumo leituraAnormalidadeConsumoMes2;

    private LeituraAnormalidadeConsumo leituraAnormalidadeConsumoMes3;

    private ServicoTipo servicoTipoMes1;

    private ServicoTipo servicoTipoMes2;

    private ServicoTipo servicoTipoMes3;

    private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoMes1;

    private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoMes2;

    private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoMes3;
    
    private String descricaoContaMensagemMes1;
    
    private String descricaoContaMensagemMes2;
    
    private String descricaoContaMensagemMes3;

	public ConsumoAnormalidadeAcao() {
		super();
	}

	public ConsumoAnormalidadeAcao(Integer id, BigDecimal numerofatorConsumoMes1, BigDecimal numerofatorConsumoMes2, BigDecimal numerofatorConsumoMes3, Short indicadorGeracaoCartaMes1, Short indicadorGeracaoCartaMes2, Short indicadorGeracaoCartaMes3, Short indicadorUso, Date ultimaAlteracao, ConsumoAnormalidade consumoAnormalidade, Categoria categoria, ImovelPerfil imovelPerfil, LeituraAnormalidadeConsumo leituraAnormalidadeConsumoMes1, LeituraAnormalidadeConsumo leituraAnormalidadeConsumoMes2, LeituraAnormalidadeConsumo leituraAnormalidadeConsumoMes3, ServicoTipo servicoTipoMes1, ServicoTipo servicoTipoMes2, ServicoTipo servicoTipoMes3, SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoMes1, SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoMes2, SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoMes3) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.numerofatorConsumoMes1 = numerofatorConsumoMes1;
		this.numerofatorConsumoMes2 = numerofatorConsumoMes2;
		this.numerofatorConsumoMes3 = numerofatorConsumoMes3;
		this.indicadorGeracaoCartaMes1 = indicadorGeracaoCartaMes1;
		this.indicadorGeracaoCartaMes2 = indicadorGeracaoCartaMes2;
		this.indicadorGeracaoCartaMes3 = indicadorGeracaoCartaMes3;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.consumoAnormalidade = consumoAnormalidade;
		this.categoria = categoria;
		this.imovelPerfil = imovelPerfil;
		this.leituraAnormalidadeConsumoMes1 = leituraAnormalidadeConsumoMes1;
		this.leituraAnormalidadeConsumoMes2 = leituraAnormalidadeConsumoMes2;
		this.leituraAnormalidadeConsumoMes3 = leituraAnormalidadeConsumoMes3;
		this.servicoTipoMes1 = servicoTipoMes1;
		this.servicoTipoMes2 = servicoTipoMes2;
		this.servicoTipoMes3 = servicoTipoMes3;
		this.solicitacaoTipoEspecificacaoMes1 = solicitacaoTipoEspecificacaoMes1;
		this.solicitacaoTipoEspecificacaoMes2 = solicitacaoTipoEspecificacaoMes2;
		this.solicitacaoTipoEspecificacaoMes3 = solicitacaoTipoEspecificacaoMes3;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public ConsumoAnormalidade getConsumoAnormalidade() {
		return consumoAnormalidade;
	}

	public void setConsumoAnormalidade(ConsumoAnormalidade consumoAnormalidade) {
		this.consumoAnormalidade = consumoAnormalidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public Short getIndicadorGeracaoCartaMes1() {
		return indicadorGeracaoCartaMes1;
	}

	public void setIndicadorGeracaoCartaMes1(Short indicadorGeracaoCartaMes1) {
		this.indicadorGeracaoCartaMes1 = indicadorGeracaoCartaMes1;
	}

	public Short getIndicadorGeracaoCartaMes2() {
		return indicadorGeracaoCartaMes2;
	}

	public void setIndicadorGeracaoCartaMes2(Short indicadorGeracaoCartaMes2) {
		this.indicadorGeracaoCartaMes2 = indicadorGeracaoCartaMes2;
	}

	public Short getIndicadorGeracaoCartaMes3() {
		return indicadorGeracaoCartaMes3;
	}

	public void setIndicadorGeracaoCartaMes3(Short indicadorGeracaoCartaMes3) {
		this.indicadorGeracaoCartaMes3 = indicadorGeracaoCartaMes3;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public LeituraAnormalidadeConsumo getLeituraAnormalidadeConsumoMes1() {
		return leituraAnormalidadeConsumoMes1;
	}

	public void setLeituraAnormalidadeConsumoMes1(
			LeituraAnormalidadeConsumo leituraAnormalidadeConsumoMes1) {
		this.leituraAnormalidadeConsumoMes1 = leituraAnormalidadeConsumoMes1;
	}

	public LeituraAnormalidadeConsumo getLeituraAnormalidadeConsumoMes2() {
		return leituraAnormalidadeConsumoMes2;
	}

	public void setLeituraAnormalidadeConsumoMes2(
			LeituraAnormalidadeConsumo leituraAnormalidadeConsumoMes2) {
		this.leituraAnormalidadeConsumoMes2 = leituraAnormalidadeConsumoMes2;
	}

	public LeituraAnormalidadeConsumo getLeituraAnormalidadeConsumoMes3() {
		return leituraAnormalidadeConsumoMes3;
	}

	public void setLeituraAnormalidadeConsumoMes3(
			LeituraAnormalidadeConsumo leituraAnormalidadeConsumoMes3) {
		this.leituraAnormalidadeConsumoMes3 = leituraAnormalidadeConsumoMes3;
	}

	public BigDecimal getNumerofatorConsumoMes1() {
		return numerofatorConsumoMes1;
	}

	public void setNumerofatorConsumoMes1(BigDecimal numerofatorConsumoMes1) {
		this.numerofatorConsumoMes1 = numerofatorConsumoMes1;
	}

	public BigDecimal getNumerofatorConsumoMes2() {
		return numerofatorConsumoMes2;
	}

	public void setNumerofatorConsumoMes2(BigDecimal numerofatorConsumoMes2) {
		this.numerofatorConsumoMes2 = numerofatorConsumoMes2;
	}

	public BigDecimal getNumerofatorConsumoMes3() {
		return numerofatorConsumoMes3;
	}

	public void setNumerofatorConsumoMes3(BigDecimal numerofatorConsumoMes3) {
		this.numerofatorConsumoMes3 = numerofatorConsumoMes3;
	}

	public ServicoTipo getServicoTipoMes1() {
		return servicoTipoMes1;
	}

	public void setServicoTipoMes1(ServicoTipo servicoTipoMes1) {
		this.servicoTipoMes1 = servicoTipoMes1;
	}

	public ServicoTipo getServicoTipoMes2() {
		return servicoTipoMes2;
	}

	public void setServicoTipoMes2(ServicoTipo servicoTipoMes2) {
		this.servicoTipoMes2 = servicoTipoMes2;
	}

	public ServicoTipo getServicoTipoMes3() {
		return servicoTipoMes3;
	}

	public void setServicoTipoMes3(ServicoTipo servicoTipoMes3) {
		this.servicoTipoMes3 = servicoTipoMes3;
	}

	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacaoMes1() {
		return solicitacaoTipoEspecificacaoMes1;
	}

	public void setSolicitacaoTipoEspecificacaoMes1(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoMes1) {
		this.solicitacaoTipoEspecificacaoMes1 = solicitacaoTipoEspecificacaoMes1;
	}

	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacaoMes2() {
		return solicitacaoTipoEspecificacaoMes2;
	}

	public void setSolicitacaoTipoEspecificacaoMes2(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoMes2) {
		this.solicitacaoTipoEspecificacaoMes2 = solicitacaoTipoEspecificacaoMes2;
	}

	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacaoMes3() {
		return solicitacaoTipoEspecificacaoMes3;
	}

	public void setSolicitacaoTipoEspecificacaoMes3(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoMes3) {
		this.solicitacaoTipoEspecificacaoMes3 = solicitacaoTipoEspecificacaoMes3;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getDescricaoContaMensagemMes1() {
		return descricaoContaMensagemMes1;
	}

	public void setDescricaoContaMensagemMes1(String descricaoContaMensagemMes1) {
		this.descricaoContaMensagemMes1 = descricaoContaMensagemMes1;
	}

	public String getDescricaoContaMensagemMes2() {
		return descricaoContaMensagemMes2;
	}

	public void setDescricaoContaMensagemMes2(String descricaoContaMensagemMes2) {
		this.descricaoContaMensagemMes2 = descricaoContaMensagemMes2;
	}

	public String getDescricaoContaMensagemMes3() {
		return descricaoContaMensagemMes3;
	}

	public void setDescricaoContaMensagemMes3(String descricaoContaMensagemMes3) {
		this.descricaoContaMensagemMes3 = descricaoContaMensagemMes3;
	}

}
