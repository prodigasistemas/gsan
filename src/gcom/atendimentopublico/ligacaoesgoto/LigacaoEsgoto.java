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
package gcom.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.LigacaoOrigem;
import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao
public class LigacaoEsgoto extends ObjetoTransacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_ATUALIZAR_VOLUME_MINIMO = 277; 
		// Operacao.OPERACAO_VOLUME_MINIMO_LIGACAO_ESGOTO_ATUALIZAR
	public static final int ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO = 272;
		// Operacao.OPERACAO_LIGACAO_ESGOTO_EFETUAR 
	public static final int ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO_SEM_RA = 1002;
		// Operacao.OPERACAO_LIGACAO_ESGOTO__SEM_RA_EFETUAR
	public static final int ATRIBUTOS_EFETUAR_MUDANCA_SITUACAO_FATURAMENTO_LIGACAO_ESGOTO = 247;
		// Operacao.OPERACAO_MUDANCA_SITUACAO_FATURAMENTO_LIGACAO_ESGOTO
	public static final int ATRIBUTOS_ATUALIZAR_LIGACAO_ESGOTO = 430;
		// Operação.OPERACAO_LIGACAO_ESGOTO_ATUALIZAR
	
	public Filtro retornaFiltro() {
		FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();

		filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(
				FiltroUsuario.ID, this.getId()));
		filtroLigacaoEsgoto
				.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoPerfil");
		filtroLigacaoEsgoto
				.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoDiametro");
		filtroLigacaoEsgoto
				.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoMaterial");
		filtroLigacaoEsgoto
		.adicionarCaminhoParaCarregamentoEntidade("imovel");

		return filtroLigacaoEsgoto;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO, ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO_SEM_RA,ATRIBUTOS_ATUALIZAR_LIGACAO_ESGOTO})
	private Date dataLigacao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade=ATRIBUTOS_ATUALIZAR_VOLUME_MINIMO)
	private Integer consumoMinimo;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO, ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO_SEM_RA,ATRIBUTOS_ATUALIZAR_LIGACAO_ESGOTO})
	private BigDecimal percentual;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO, ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO_SEM_RA,ATRIBUTOS_ATUALIZAR_LIGACAO_ESGOTO})	
	private BigDecimal percentualAguaConsumidaColetada;

	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO, ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO_SEM_RA,ATRIBUTOS_ATUALIZAR_LIGACAO_ESGOTO})
	private Short indicadorCaixaGordura;
    
	private Short indicadorLigacaoEsgoto;
	
    private BigDecimal percentualAlternativo;
    
    private Integer numeroConsumoPercentualAlternativo;

	/** nullable persistent field */
	private Imovel imovel;

	/** persistent field */
	@ControleAlteracao(value=FiltroLigacaoEsgoto.LIGACAO_ESGOTO_PERFIL,
			funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO, ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO_SEM_RA,ATRIBUTOS_ATUALIZAR_LIGACAO_ESGOTO})	
	private gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil ligacaoEsgotoPerfil;

	/** persistent field */
	@ControleAlteracao(value=FiltroLigacaoEsgoto.LIGACAO_ESGOTO_DIAMETRO,
			funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO, ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO_SEM_RA,ATRIBUTOS_ATUALIZAR_LIGACAO_ESGOTO})	
	private gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro ligacaoEsgotoDiametro;

	/** persistent field */
	@ControleAlteracao(value=FiltroLigacaoEsgoto.LIGACAO_ESGOTO_MATERIAL,
			funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO, ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO_SEM_RA,ATRIBUTOS_ATUALIZAR_LIGACAO_ESGOTO})
	private gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial ligacaoEsgotoMaterial;
	
	private LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento;
	
	private LigacaoEsgotoDestinoDejetos ligacaoEsgotoDestinoDejetos;
	
	private LigacaoEsgotoCaixaInspecao ligacaoEsgotoCaixaInspecao;
	
	private LigacaoEsgotoDestinoAguasPluviais ligacaoEsgotoDestinoAguasPluviais;
	
	/** nullable persistent field */
	private LigacaoOrigem ligacaoOrigem;
	
	public final static Short INDICADOR_COM_CAIXA_GORDURA = new Short("1");
	
	public final static Short INDICADOR_SEM_CAIXA_GORDURA = new Short("2");

	/** full constructor */
	public LigacaoEsgoto(
			Date dataLigacao,
			Integer consumoMinimo,
			BigDecimal percentual,
			BigDecimal percentualAguaConsumidaColetada,
			Date ultimaAlteracao,
			Short indicadorCaixaGordura,
			Short indicadorLigacaoEsgoto,
			Imovel imovel,
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil ligacaoEsgotoPerfil,
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro ligacaoEsgotoDiametro,
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial ligacaoEsgotoMaterial) {
		this.dataLigacao = dataLigacao;
		this.consumoMinimo = consumoMinimo;
		this.percentual = percentual;
		this.percentualAguaConsumidaColetada = percentualAguaConsumidaColetada;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorCaixaGordura = indicadorCaixaGordura;
		this.indicadorLigacaoEsgoto = indicadorLigacaoEsgoto;
		this.imovel = imovel;
		this.ligacaoEsgotoPerfil = ligacaoEsgotoPerfil;
		this.ligacaoEsgotoDiametro = ligacaoEsgotoDiametro;
		this.ligacaoEsgotoMaterial = ligacaoEsgotoMaterial;
	}

	/** default constructor */
	public LigacaoEsgoto() {
	}

	/** minimal constructor */
	public LigacaoEsgoto(
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil ligacaoEsgotoPerfil,
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro ligacaoEsgotoDiametro,
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial ligacaoEsgotoMaterial) {
		this.ligacaoEsgotoPerfil = ligacaoEsgotoPerfil;
		this.ligacaoEsgotoDiametro = ligacaoEsgotoDiametro;
		this.ligacaoEsgotoMaterial = ligacaoEsgotoMaterial;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataLigacao() {
		return this.dataLigacao;
	}

	public void setDataLigacao(Date dataLigacao) {
		this.dataLigacao = dataLigacao;
	}

	public Integer getConsumoMinimo() {
		return this.consumoMinimo;
	}

	public void setConsumoMinimo(Integer consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public BigDecimal getPercentual() {
		return this.percentual;
	}

	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}

	public BigDecimal getPercentualAguaConsumidaColetada() {
		return this.percentualAguaConsumidaColetada;
	}

	public void setPercentualAguaConsumidaColetada(
			BigDecimal percentualAguaConsumidaColetada) {
		this.percentualAguaConsumidaColetada = percentualAguaConsumidaColetada;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil getLigacaoEsgotoPerfil() {
		return this.ligacaoEsgotoPerfil;
	}

	public void setLigacaoEsgotoPerfil(
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil ligacaoEsgotoPerfil) {
		this.ligacaoEsgotoPerfil = ligacaoEsgotoPerfil;
	}

	public gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro getLigacaoEsgotoDiametro() {
		return this.ligacaoEsgotoDiametro;
	}

	public void setLigacaoEsgotoDiametro(
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro ligacaoEsgotoDiametro) {
		this.ligacaoEsgotoDiametro = ligacaoEsgotoDiametro;
	}

	public gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial getLigacaoEsgotoMaterial() {
		return this.ligacaoEsgotoMaterial;
	}

	public void setLigacaoEsgotoMaterial(
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial ligacaoEsgotoMaterial) {
		this.ligacaoEsgotoMaterial = ligacaoEsgotoMaterial;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Retorna o campo indicadorCaixaGordura.
	 */
	public Short getIndicadorCaixaGordura() {
		return indicadorCaixaGordura;
	}

	/**
	 * @param indicadorCaixaGordura O indicadorCaixaGordura a ser setado.
	 */
	public void setIndicadorCaixaGordura(Short indicadorCaixaGordura) {
		this.indicadorCaixaGordura = indicadorCaixaGordura;
	}

    public Integer getNumeroConsumoPercentualAlternativo() {
        return numeroConsumoPercentualAlternativo;
    }

    public void setNumeroConsumoPercentualAlternativo(Integer numeroConsumoPercentualAlternativo) {
        this.numeroConsumoPercentualAlternativo = numeroConsumoPercentualAlternativo;
    }

    public BigDecimal getPercentualAlternativo() {
        return percentualAlternativo;
    }

    public void setPercentualAlternativo(BigDecimal percentualAlternativo) {
        this.percentualAlternativo = percentualAlternativo;
    }

	
	public LigacaoEsgotoDestinoAguasPluviais getLigacaoEsgotoDestinoAguasPluviais() {
	
		return ligacaoEsgotoDestinoAguasPluviais;
	}

	
	public void setLigacaoEsgotoDestinoAguasPluviais(
			LigacaoEsgotoDestinoAguasPluviais ligacaoEsgotoDestinoAguasPluviais) {
	
		this.ligacaoEsgotoDestinoAguasPluviais = ligacaoEsgotoDestinoAguasPluviais;
	}

	
	public LigacaoEsgotoCaixaInspecao getLigacaoEsgotoCaixaInspecao() {
	
		return ligacaoEsgotoCaixaInspecao;
	}

	
	public void setLigacaoEsgotoCaixaInspecao(
			LigacaoEsgotoCaixaInspecao ligacaoEsgotoCaixaInspecao) {
	
		this.ligacaoEsgotoCaixaInspecao = ligacaoEsgotoCaixaInspecao;
	}

	
	public LigacaoEsgotoDestinoDejetos getLigacaoEsgotoDestinoDejetos() {
	
		return ligacaoEsgotoDestinoDejetos;
	}

	
	public void setLigacaoEsgotoDestinoDejetos(
			LigacaoEsgotoDestinoDejetos ligacaoEsgotoDestinoDejetos) {
	
		this.ligacaoEsgotoDestinoDejetos = ligacaoEsgotoDestinoDejetos;
	}

	
	public LigacaoEsgotoEsgotamento getLigacaoEsgotoEsgotamento() {
	
		return ligacaoEsgotoEsgotamento;
	}

	
	public void setLigacaoEsgotoEsgotamento(
			LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento) {
	
		this.ligacaoEsgotoEsgotamento = ligacaoEsgotoEsgotamento;
	}

	public LigacaoOrigem getLigacaoOrigem() {
		return ligacaoOrigem;
	}

	public void setLigacaoOrigem(LigacaoOrigem ligacaoOrigem) {
		this.ligacaoOrigem = ligacaoOrigem;
	}

	public Short getIndicadorLigacaoEsgoto() {
		return indicadorLigacaoEsgoto;
	}

	public void setIndicadorLigacaoEsgoto(Short indicadorLigacaoEsgoto) {
		this.indicadorLigacaoEsgoto = indicadorLigacaoEsgoto;
	}
}
