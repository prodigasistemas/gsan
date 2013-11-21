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
package gcom.relatorio.cobranca.spcserasa;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Yara Taciane 
 * @created 16 de maio de 2008
 * @version 1.0
 */

public class RelatorioNegativadorResultadoSimulacaoBean implements RelatorioBean {
	   
	    private String idComando;
	    private String descricaoTitulo;
	    private String sequencial;
	    private String idImovel;
	    private BigDecimal valorDebito;
	    private String numeroCpf;
	    private String numeroCnpj;
	    

	    /**
	     * 
	     * Construtor de RelatorioManterNegativadorExclusaoMotivoBean 
	     * 
	     * @param id
	     * @param descricao
	     * @param codigoMotivo
	     * @param negativador	   
	     * @param indicadorUso
	     */
	    
		public RelatorioNegativadorResultadoSimulacaoBean(String idComando,
			String descricaoTitulo,String sequencial,String idImovel,BigDecimal valorDebito,String numeroCpf,String numeroCnpj  ) {
			super();
			// TODO Auto-generated constructor stub
			this.idComando=idComando;
			this.descricaoTitulo=descricaoTitulo;
		    this.sequencial=sequencial;
		    this.idImovel=idImovel;
		    this.valorDebito=valorDebito;
		    this.numeroCpf=numeroCpf;
		    this.numeroCnpj=numeroCnpj;					
		}


		
		/**
		 * @return Retorna o campo idImovel.
		 */
		public String getIdImovel() {
			return idImovel;
		}


		/**
		 * @param idImovel O idImovel a ser setado.
		 */
		public void setIdImovel(String idImovel) {
			this.idImovel = idImovel;
		}


		/**
		 * @return Retorna o campo numeroCnpj.
		 */
		public String getNumeroCnpj() {
			return numeroCnpj;
		}


		/**
		 * @param numeroCnpj O numeroCnpj a ser setado.
		 */
		public void setNumeroCnpj(String numeroCnpj) {
			this.numeroCnpj = numeroCnpj;
		}


		/**
		 * @return Retorna o campo numeroCpf.
		 */
		public String getNumeroCpf() {
			return numeroCpf;
		}


		/**
		 * @param numeroCpf O numeroCpf a ser setado.
		 */
		public void setNumeroCpf(String numeroCpf) {
			this.numeroCpf = numeroCpf;
		}


		/**
		 * @return Retorna o campo valorDebito.
		 */
		public BigDecimal getValorDebito() {
			return valorDebito;
		}


		/**
		 * @param valorDebito O valorDebito a ser setado.
		 */
		public void setValorDebito(BigDecimal valorDebito) {
			this.valorDebito = valorDebito;
		}



		/**
		 * @return Retorna o campo descricaoTitulo.
		 */
		public String getDescricaoTitulo() {
			return descricaoTitulo;
		}



		/**
		 * @param descricaoTitulo O descricaoTitulo a ser setado.
		 */
		public void setDescricaoTitulo(String descricaoTitulo) {
			this.descricaoTitulo = descricaoTitulo;
		}



		/**
		 * @return Retorna o campo sequencial.
		 */
		public String getSequencial() {
			return sequencial;
		}



		/**
		 * @param sequencial O sequencial a ser setado.
		 */
		public void setSequencial(String sequencial) {
			this.sequencial = sequencial;
		}



		/**
		 * @return Retorna o campo idComando.
		 */
		public String getIdComando() {
			return idComando;
		}



		/**
		 * @param idComando O idComando a ser setado.
		 */
		public void setIdComando(String idComando) {
			this.idComando = idComando;
		}

	


		
		
	
}
