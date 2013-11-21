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

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LigacaoEsgotoSituacao extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private String descricaoAbreviado;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	/** nullable persistent field */
	private Short indicadorFaturamentoSituacao;
	
	/** nullable persistent field */
	private Integer volumeMinimoFaturamento;
	
	private Short indicadorExistenciaRede;
	
	private Short indicadorExistenciaLigacao;
	
	/**
	 * @since 19/09/2007
	 */
	private String descricaoComId;

	// ---CONSTANTES SISTEMA

	public final static Integer POTENCIAL = new Integer(1);

	public final static Integer FACTIVEL = new Integer(2);

	public final static Integer LIGADO = new Integer(3);

	public final static Integer EM_FISCALIZACAO = new Integer(4);

	public final static Integer LIG_FORA_DE_USO = new Integer(5);

	public final static Integer TAMPONADO = new Integer(6);

	// Descrição Tipo de Serviços

	public final static String SITUACAO_LIGADO_FORA_DE_USO = new String(
			"LIGADO FORA DE USO");

	public final static String SITUACAO_LIGADO = new String("LIGADO");

	// Descrição Tipo de Serviços
	public final static Integer SITUACAO_TAMPONADO = new Integer(1);

	public final static Integer SITUACAO_DESATIVACAO = new Integer(2);

	public final static Integer SITUACAO_RESTABELECIMENTO = new Integer(3);

	public final static Integer SITUACAO_REATIVACAO = new Integer(4);
	
	public final static Short FATURAMENTO_ATIVO = new Short("1");
	
	public final static Short INDICADOR_EXISTENCIA_REDE_SIM = new Short("1");
    
    public final static Short INDICADOR_EXISTENCIA_REDE_NAO = new Short("2");
    
    public final static Short INDICADOR_EXISTENCIA_LIGACAO_SIM = new Short("1");
    
    public final static Short INDICADOR_EXISTENCIA_LIGACAO_NAO = new Short("2");

	/** full constructor */
	public LigacaoEsgotoSituacao(String descricao, Short indicadorUso,
			Date ultimaAlteracao, Short indicadorFaturamentoSituacao, Integer volumeMinimoFaturamento) {
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorFaturamentoSituacao = indicadorFaturamentoSituacao;
		this.volumeMinimoFaturamento = volumeMinimoFaturamento;
	}

	/** full constructor */
	public LigacaoEsgotoSituacao(String descricao, String descricaoAbreviado,
			Short indicadorUso, Date ultimaAlteracao, Short indicadorFaturamentoSituacao, Integer volumeMinimoFaturamento) {
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.descricaoAbreviado = descricaoAbreviado;
		this.indicadorFaturamentoSituacao = indicadorFaturamentoSituacao;
		this.volumeMinimoFaturamento = volumeMinimoFaturamento;
	}

	/** default constructor */
	public LigacaoEsgotoSituacao() {
	}

	/**
	 * @return Retorna o campo descricaoAbreviado.
	 */
	public String getDescricaoAbreviado() {
		return descricaoAbreviado;
	}

	public Short getIndicadorFaturamentoSituacao() {
		return indicadorFaturamentoSituacao;
	}

	public void setIndicadorFaturamentoSituacao(Short indicadorFaturamentoSituacao) {
		this.indicadorFaturamentoSituacao = indicadorFaturamentoSituacao;
	}

	public Integer getVolumeMinimoFaturamento() {
		return volumeMinimoFaturamento;
	}

	public void setVolumeMinimoFaturamento(Integer volumeMinimoFaturamento) {
		this.volumeMinimoFaturamento = volumeMinimoFaturamento;
	}

	/**
	 * @param descricaoAbreviado
	 *            O descricaoAbreviado a ser setado.
	 */
	public void setDescricaoAbreviado(String descricaoAbreviado) {
		this.descricaoAbreviado = descricaoAbreviado;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroLigacaoEsgotoSituacao filtro = new FiltroLigacaoEsgotoSituacao();

		filtro.adicionarParametro(new ParametroSimples(
				FiltroLigacaoEsgoto.ID, this.getId()));
		return filtro;
	}
	
	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 19/09/2007
	 *
	 * @return
	 */
	public String getDescricaoComId() {
		
		if(this.getId().compareTo(10) == -1){
			descricaoComId = "0" + getId()+ " - " + getDescricao();
		}else{
			descricaoComId = getId()+ " - " + getDescricao();
		}
		
		return descricaoComId;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getDescricao();
	}
	
	@Override
	public void initializeLazy() {
		getDescricaoParaRegistroTransacao();
	}

	/**
	 * @return Retorna o campo indicadorExistenciaLigacao.
	 */
	public Short getIndicadorExistenciaLigacao() {
		return indicadorExistenciaLigacao;
	}

	/**
	 * @param indicadorExistenciaLigacao O indicadorExistenciaLigacao a ser setado.
	 */
	public void setIndicadorExistenciaLigacao(Short indicadorExistenciaLigacao) {
		this.indicadorExistenciaLigacao = indicadorExistenciaLigacao;
	}

	/**
	 * @return Retorna o campo indicadorExistenciaRede.
	 */
	public Short getIndicadorExistenciaRede() {
		return indicadorExistenciaRede;
	}

	/**
	 * @param indicadorExistenciaRede O indicadorExistenciaRede a ser setado.
	 */
	public void setIndicadorExistenciaRede(Short indicadorExistenciaRede) {
		this.indicadorExistenciaRede = indicadorExistenciaRede;
	}
}
