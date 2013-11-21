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

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class DocumentoTipo extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricaoDocumentoTipo;

	/** nullable persistent field */
	private String descricaoAbreviado;

	/** nullable persistent field */
	private Short indicadorPagavel;

	/** nullable persistent field */
	private Short indicadorUso;
	
	/** nullable persistent field */
	private Short indicadorAgregador;

	/** nullable persistent field */
	private Date ultimaAlteracao;


	//constantes
	public final static Integer CONTA = new Integer("1");
	public final static Integer GUIA_PAGAMENTO = new Integer("7");
	public final static Integer DEBITO_A_COBRAR = new Integer("6");
	public final static Integer FATURA_CLIENTE = new Integer("5");
	public final static Integer CREDITO_A_REALIZAR = new Integer("10");
	public final static Integer ENTRADA_DE_PARCELAMENTO = new Integer("2");
	public final static Integer DEVOLUCAO_VALOR = new Integer("8");
	public final static Integer EXTRATO_DE_DEBITO = new Integer("14");
	public final static Integer CARTA_COBRANCA_SUPRIMIDO = new Integer("21");
	public final static Integer CARTA_COBRANCA_CORTADO = new Integer("22");
	public final static Integer CARTA_COBRANCA_LIGADO = new Integer("25");
	public final static Integer CARTA_COBRANCA_TARIFA_SOCIAL_LIGADO = 23;
	public final static Integer CARTA_COBRANCA_TARIFA_SOCIAL_CORTADO = 24;
	public final static Integer DOCUMENTO_COBRANCA = new Integer("3");
	public final static int ORDEM_FISCALIZACAO_FACTIVEL = 27;
	public final static int ORDEM_FISCALIZACAO_SUPRIMIDO = 16;
	public final static int ORDEM_FISCALIZACAO_CORTADO = 17;
	public final static int ORDEM_FISCALIZACAO_TOTAL = 30;
	public final static int ORDEM_FISCALIZACAO_POTENCIAL = 28;
	public final static int ORDEM_FISCALIZACAO_LIGADO = 29;
	
	public final static int AVISO_CORTE = 12;
	public final static int CORTE_FISICO = 13;
	public final static int CORTE_ADMINISTRATIVO = 11; 
	
	public final static Integer CARTA_SOLIDARIEDADE_DA_CRIANCA = 31;
	public final static Integer INSPECAO_DE_LIGACOES = 32;

	public final static Integer CARTA_DE_FINAL_DE_ANO_2009 = 33;
	
	public final static int VISITA_COBRANCA = 17; 
	public final static int ORDEM_CORTE = 18; 
	public final static int ORDEM_FISCALIZACAO = 16;
	
	public final static Integer EXTRATO_CONTRATO_PARCELAMENTO = 34;
	
	public final static int[] DOCUMENTO_TIPO_AGREGADOR = {
		GUIA_PAGAMENTO, FATURA_CLIENTE, EXTRATO_DE_DEBITO, CARTA_COBRANCA_SUPRIMIDO,
		CARTA_COBRANCA_CORTADO, CARTA_COBRANCA_LIGADO, DOCUMENTO_COBRANCA,
		CARTA_COBRANCA_TARIFA_SOCIAL_CORTADO, CARTA_COBRANCA_TARIFA_SOCIAL_LIGADO,
		AVISO_CORTE
	};
	
	/** full constructor */
	public DocumentoTipo(String descricaoDocumentoTipo,
			String descricaoAbreviado, Short indicadorPagavel,
			Short indicadorUso, Date ultimaAlteracao) {
		this.descricaoDocumentoTipo = descricaoDocumentoTipo;
		this.descricaoAbreviado = descricaoAbreviado;
		this.indicadorPagavel = indicadorPagavel;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
	public DocumentoTipo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoDocumentoTipo() {
		return this.descricaoDocumentoTipo;
	}

	public void setDescricaoDocumentoTipo(String descricaoDocumentoTipo) {
		this.descricaoDocumentoTipo = descricaoDocumentoTipo;
	}

	public String getDescricaoAbreviado() {
		return this.descricaoAbreviado;
	}

	public void setDescricaoAbreviado(String descricaoAbreviado) {
		this.descricaoAbreviado = descricaoAbreviado;
	}

	public Short getIndicadorPagavel() {
		return this.indicadorPagavel;
	}

	public void setIndicadorPagavel(Short indicadorPagavel) {
		this.indicadorPagavel = indicadorPagavel;
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
	
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Retorna o campo indicadorAgregador.
	 */
	public Short getIndicadorAgregador() {
		return indicadorAgregador;
	}

	/**
	 * @param indicadorAgregador O indicadorAgregador a ser setado.
	 */
	public void setIndicadorAgregador(Short indicadorAgregador) {
		this.indicadorAgregador = indicadorAgregador;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricaoDocumentoTipo();
	}

	@Override
	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroDocumentoTipo();
		filtro.adicionarParametro(new ParametroSimples(FiltroDocumentoTipo.ID, this.getId()));		
		return filtro;
	}

}
