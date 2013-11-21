/**
 * 
 */
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
package gcom.gui.micromedicao.leitura;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author Rômulo Aurélio
 *
 */
public class InformarLeituraFiscalizacaoActionForm extends ValidatorActionForm {
	

	/**
	 * @since 23/05/2007
	 */
	private static final long serialVersionUID = 1L;


	private String matricula;
	
	private String inscricaoImovel;
	
	private String dataLeituraNormal;
	
	private String medicaoTipo;
	
	private String mesAnoReferencia;
	
	private String leituraNormal;
	
	private String anormalidadeNormal;
	
	private String matriculaLeituristaNormal;
	
	private String leituraFiscalizacao;
	
	private String dataLeituraFiscalizacao;
	
	private String anormalidadeFiscalizacao;
	
	private String matriculaLeituristaFiscalizacao;

	public String getAnormalidadeFiscalizacao() {
		return anormalidadeFiscalizacao;
	}

	public void setAnormalidadeFiscalizacao(String anormalidadeFiscalizacao) {
		this.anormalidadeFiscalizacao = anormalidadeFiscalizacao;
	}

	public String getAnormalidadeNormal() {
		return anormalidadeNormal;
	}

	public void setAnormalidadeNormal(String anormalidadeNormal) {
		this.anormalidadeNormal = anormalidadeNormal;
	}

	public String getDataLeituraFiscalizacao() {
		return dataLeituraFiscalizacao;
	}

	public void setDataLeituraFiscalizacao(String dataLeituraFiscalizacao) {
		this.dataLeituraFiscalizacao = dataLeituraFiscalizacao;
	}

	public String getDataLeituraNormal() {
		return dataLeituraNormal;
	}

	public void setDataLeituraNormal(String dataLeituraNormal) {
		this.dataLeituraNormal = dataLeituraNormal;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getLeituraFiscalizacao() {
		return leituraFiscalizacao;
	}

	public void setLeituraFiscalizacao(String leituraFiscalizacao) {
		this.leituraFiscalizacao = leituraFiscalizacao;
	}

	public String getLeituraNormal() {
		return leituraNormal;
	}

	public void setLeituraNormal(String leituraNormal) {
		this.leituraNormal = leituraNormal;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMatriculaLeituristaFiscalizacao() {
		return matriculaLeituristaFiscalizacao;
	}

	public void setMatriculaLeituristaFiscalizacao(
			String matriculaLeituristaFiscalizacao) {
		this.matriculaLeituristaFiscalizacao = matriculaLeituristaFiscalizacao;
	}

	public String getMatriculaLeituristaNormal() {
		return matriculaLeituristaNormal;
	}

	public void setMatriculaLeituristaNormal(String matriculaLeituristaNormal) {
		this.matriculaLeituristaNormal = matriculaLeituristaNormal;
	}

	public String getMedicaoTipo() {
		return medicaoTipo;
	}

	public void setMedicaoTipo(String medicaoTipo) {
		this.medicaoTipo = medicaoTipo;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}
	
	

}
