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
package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Thiago Tenório
 * @created 29/03/2007
 */
public class InformarNaoEntregaDocumentosActionForm extends ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idCodigo;

	private String idCodigo1;

	private String idCodigo2;

	private String idCodigo3;

	private String idCodigo4;

	private String idCodigo5;

	private String idCodigo6;

	private String idCodigo7;

	private String idCodigo8;

	private String idCodigo9;

	private String idCodigo10;

	private String idCodigo11;

	private String idCodigo12;

	private String idCodigo13;

	private String idCodigo14;

	private String idCodigo15;

	private String idCodigo16;

	private String idCodigo17;

	private String idCodigo18;

	private String idCodigo19;

	private String idCodigo20;

	private String matriculaOuNumeroDocumento1;
	
	private String matriculaOuNumeroDocumento2;
	
	private String matriculaOuNumeroDocumento3;
	
	private String matriculaOuNumeroDocumento4;
	
	private String matriculaOuNumeroDocumento5;
	
	private String matriculaOuNumeroDocumento6;
	
	private String matriculaOuNumeroDocumento7;
	
	private String matriculaOuNumeroDocumento8;
	
	private String matriculaOuNumeroDocumento9;
	
	private String matriculaOuNumeroDocumento10;
	
	private String matriculaOuNumeroDocumento11;
	
	private String matriculaOuNumeroDocumento12;
	
	private String matriculaOuNumeroDocumento13;
	
	private String matriculaOuNumeroDocumento14;
	
	private String matriculaOuNumeroDocumento15;
	
	private String matriculaOuNumeroDocumento16;
	
	private String matriculaOuNumeroDocumento17;
	
	private String matriculaOuNumeroDocumento18;
	
	private String matriculaOuNumeroDocumento19;
	
	private String matriculaOuNumeroDocumento20;

	private String qtTentativas;

	private String qtTentativas1;

	private String qtTentativas2;

	private String qtTentativas3;

	private String qtTentativas4;

	private String qtTentativas5;

	private String qtTentativas6;

	private String qtTentativas7;

	private String qtTentativas8;

	private String qtTentativas9;

	private String qtTentativas10;

	private String qtTentativas11;

	private String qtTentativas12;

	private String qtTentativas13;

	private String qtTentativas14;

	private String qtTentativas15;

	private String qtTentativas16;

	private String qtTentativas17;

	private String qtTentativas18;

	private String qtTentativas19;

	private String qtTentativas20;

	private String dataDevolucao;

	private String idResponsavelEntrega;

	private String descricaoResponsavelEntrega;

	private String tipoDocumento;

	private String mesAnoConta;
	
	private String mesAnoDocumentoCobranca;
	
	private String mesAnoGuiaPagamento;
	
	private String mesAnoFatura;

	private String codigoMotivoDevolucao;

	private String motivo;
	
	private String tipoCampo;
	
	public static final String MATRICULA = "MATRICULA";
	public static final String NUMERO_DOCUMENTO = "NUMERO DOCUMENTO";

	public String getCodigoMotivoDevolucao() {
		return codigoMotivoDevolucao;
	}

	public void setCodigoMotivoDevolucao(String codigoMotivoDevolucao) {
		this.codigoMotivoDevolucao = codigoMotivoDevolucao;
	}

	public String getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getMesAnoConta() {
		return mesAnoConta;
	}

	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}

	public String getQtTentativas() {
		return qtTentativas;
	}

	public void setQtTentativas(String qtTentativas) {
		this.qtTentativas = qtTentativas;
	}

	public String getIdResponsavelEntrega() {
		return idResponsavelEntrega;
	}

	public void setIdResponsavelEntrega(String idResponsavelEntrega) {
		this.idResponsavelEntrega = idResponsavelEntrega;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDescricaoResponsavelEntrega() {
		return descricaoResponsavelEntrega;
	}

	public void setDescricaoResponsavelEntrega(
			String descricaoResponsavelEntrega) {
		this.descricaoResponsavelEntrega = descricaoResponsavelEntrega;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	public String getIdCodigo1() {
		return idCodigo1;
	}

	public void setIdCodigo1(String idCodigo1) {
		this.idCodigo1 = idCodigo1;
	}

	public String getIdCodigo10() {
		return idCodigo10;
	}

	public void setIdCodigo10(String idCodigo10) {
		this.idCodigo10 = idCodigo10;
	}

	public String getIdCodigo11() {
		return idCodigo11;
	}

	public void setIdCodigo11(String idCodigo11) {
		this.idCodigo11 = idCodigo11;
	}

	public String getIdCodigo12() {
		return idCodigo12;
	}

	public void setIdCodigo12(String idCodigo12) {
		this.idCodigo12 = idCodigo12;
	}

	public String getIdCodigo13() {
		return idCodigo13;
	}

	public void setIdCodigo13(String idCodigo13) {
		this.idCodigo13 = idCodigo13;
	}

	public String getIdCodigo14() {
		return idCodigo14;
	}

	public void setIdCodigo14(String idCodigo14) {
		this.idCodigo14 = idCodigo14;
	}

	public String getIdCodigo15() {
		return idCodigo15;
	}

	public void setIdCodigo15(String idCodigo15) {
		this.idCodigo15 = idCodigo15;
	}

	public String getIdCodigo16() {
		return idCodigo16;
	}

	public void setIdCodigo16(String idCodigo16) {
		this.idCodigo16 = idCodigo16;
	}

	public String getIdCodigo17() {
		return idCodigo17;
	}

	public void setIdCodigo17(String idCodigo17) {
		this.idCodigo17 = idCodigo17;
	}

	public String getIdCodigo18() {
		return idCodigo18;
	}

	public void setIdCodigo18(String idCodigo18) {
		this.idCodigo18 = idCodigo18;
	}

	public String getIdCodigo19() {
		return idCodigo19;
	}

	public void setIdCodigo19(String idCodigo19) {
		this.idCodigo19 = idCodigo19;
	}

	public String getIdCodigo2() {
		return idCodigo2;
	}

	public void setIdCodigo2(String idCodigo2) {
		this.idCodigo2 = idCodigo2;
	}

	public String getIdCodigo20() {
		return idCodigo20;
	}

	public void setIdCodigo20(String idCodigo20) {
		this.idCodigo20 = idCodigo20;
	}

	public String getIdCodigo3() {
		return idCodigo3;
	}

	public void setIdCodigo3(String idCodigo3) {
		this.idCodigo3 = idCodigo3;
	}

	public String getIdCodigo4() {
		return idCodigo4;
	}

	public void setIdCodigo4(String idCodigo4) {
		this.idCodigo4 = idCodigo4;
	}

	public String getIdCodigo5() {
		return idCodigo5;
	}

	public void setIdCodigo5(String idCodigo5) {
		this.idCodigo5 = idCodigo5;
	}

	public String getIdCodigo6() {
		return idCodigo6;
	}

	public void setIdCodigo6(String idCodigo6) {
		this.idCodigo6 = idCodigo6;
	}

	public String getIdCodigo7() {
		return idCodigo7;
	}

	public void setIdCodigo7(String idCodigo7) {
		this.idCodigo7 = idCodigo7;
	}

	public String getIdCodigo8() {
		return idCodigo8;
	}

	public void setIdCodigo8(String idCodigo8) {
		this.idCodigo8 = idCodigo8;
	}

	public String getIdCodigo9() {
		return idCodigo9;
	}

	public void setIdCodigo9(String idCodigo9) {
		this.idCodigo9 = idCodigo9;
	}

	public String getQtTentativas1() {
		return qtTentativas1;
	}

	public void setQtTentativas1(String qtTentativas1) {
		this.qtTentativas1 = qtTentativas1;
	}

	public String getQtTentativas10() {
		return qtTentativas10;
	}

	public void setQtTentativas10(String qtTentativas10) {
		this.qtTentativas10 = qtTentativas10;
	}

	public String getQtTentativas11() {
		return qtTentativas11;
	}

	public void setQtTentativas11(String qtTentativas11) {
		this.qtTentativas11 = qtTentativas11;
	}

	public String getQtTentativas12() {
		return qtTentativas12;
	}

	public void setQtTentativas12(String qtTentativas12) {
		this.qtTentativas12 = qtTentativas12;
	}

	public String getQtTentativas13() {
		return qtTentativas13;
	}

	public void setQtTentativas13(String qtTentativas13) {
		this.qtTentativas13 = qtTentativas13;
	}

	public String getQtTentativas14() {
		return qtTentativas14;
	}

	public void setQtTentativas14(String qtTentativas14) {
		this.qtTentativas14 = qtTentativas14;
	}

	public String getQtTentativas15() {
		return qtTentativas15;
	}

	public void setQtTentativas15(String qtTentativas15) {
		this.qtTentativas15 = qtTentativas15;
	}

	public String getQtTentativas16() {
		return qtTentativas16;
	}

	public void setQtTentativas16(String qtTentativas16) {
		this.qtTentativas16 = qtTentativas16;
	}

	public String getQtTentativas17() {
		return qtTentativas17;
	}

	public void setQtTentativas17(String qtTentativas17) {
		this.qtTentativas17 = qtTentativas17;
	}

	public String getQtTentativas18() {
		return qtTentativas18;
	}

	public void setQtTentativas18(String qtTentativas18) {
		this.qtTentativas18 = qtTentativas18;
	}

	public String getQtTentativas19() {
		return qtTentativas19;
	}

	public void setQtTentativas19(String qtTentativas19) {
		this.qtTentativas19 = qtTentativas19;
	}

	public String getQtTentativas2() {
		return qtTentativas2;
	}

	public void setQtTentativas2(String qtTentativas2) {
		this.qtTentativas2 = qtTentativas2;
	}

	public String getQtTentativas20() {
		return qtTentativas20;
	}

	public void setQtTentativas20(String qtTentativas20) {
		this.qtTentativas20 = qtTentativas20;
	}

	public String getQtTentativas3() {
		return qtTentativas3;
	}

	public void setQtTentativas3(String qtTentativas3) {
		this.qtTentativas3 = qtTentativas3;
	}

	public String getQtTentativas4() {
		return qtTentativas4;
	}

	public void setQtTentativas4(String qtTentativas4) {
		this.qtTentativas4 = qtTentativas4;
	}

	public String getQtTentativas5() {
		return qtTentativas5;
	}

	public void setQtTentativas5(String qtTentativas5) {
		this.qtTentativas5 = qtTentativas5;
	}

	public String getQtTentativas6() {
		return qtTentativas6;
	}

	public void setQtTentativas6(String qtTentativas6) {
		this.qtTentativas6 = qtTentativas6;
	}

	public String getQtTentativas7() {
		return qtTentativas7;
	}

	public void setQtTentativas7(String qtTentativas7) {
		this.qtTentativas7 = qtTentativas7;
	}

	public String getQtTentativas8() {
		return qtTentativas8;
	}

	public void setQtTentativas8(String qtTentativas8) {
		this.qtTentativas8 = qtTentativas8;
	}

	public String getQtTentativas9() {
		return qtTentativas9;
	}

	public void setQtTentativas9(String qtTentativas9) {
		this.qtTentativas9 = qtTentativas9;
	}

	public String getIdCodigo() {
		return idCodigo;
	}

	public void setIdCodigo(String idCodigo) {
		this.idCodigo = idCodigo;
	}

	public String getMatriculaOuNumeroDocumento1() {
		return matriculaOuNumeroDocumento1;
	}

	public void setMatriculaOuNumeroDocumento1(String numeroDocumento1) {
		this.matriculaOuNumeroDocumento1 = numeroDocumento1;
	}

	public String getMatriculaOuNumeroDocumento10() {
		return matriculaOuNumeroDocumento10;
	}

	public void setMatriculaOuNumeroDocumento10(String numeroDocumento10) {
		this.matriculaOuNumeroDocumento10 = numeroDocumento10;
	}

	public String getMatriculaOuNumeroDocumento11() {
		return matriculaOuNumeroDocumento11;
	}

	public void setMatriculaOuNumeroDocumento11(String numeroDocumento11) {
		this.matriculaOuNumeroDocumento11 = numeroDocumento11;
	}

	public String getMatriculaOuNumeroDocumento12() {
		return matriculaOuNumeroDocumento12;
	}

	public void setMatriculaOuNumeroDocumento12(String numeroDocumento12) {
		this.matriculaOuNumeroDocumento12 = numeroDocumento12;
	}

	public String getMatriculaOuNumeroDocumento13() {
		return matriculaOuNumeroDocumento13;
	}

	public void setMatriculaOuNumeroDocumento13(String numeroDocumento13) {
		this.matriculaOuNumeroDocumento13 = numeroDocumento13;
	}

	public String getMatriculaOuNumeroDocumento14() {
		return matriculaOuNumeroDocumento14;
	}

	public void setMatriculaOuNumeroDocumento14(String numeroDocumento14) {
		this.matriculaOuNumeroDocumento14 = numeroDocumento14;
	}

	public String getMatriculaOuNumeroDocumento15() {
		return matriculaOuNumeroDocumento15;
	}

	public void setMatriculaOuNumeroDocumento15(String numeroDocumento15) {
		this.matriculaOuNumeroDocumento15 = numeroDocumento15;
	}

	public String getMatriculaOuNumeroDocumento16() {
		return matriculaOuNumeroDocumento16;
	}

	public void setMatriculaOuNumeroDocumento16(String numeroDocumento16) {
		this.matriculaOuNumeroDocumento16 = numeroDocumento16;
	}

	public String getMatriculaOuNumeroDocumento17() {
		return matriculaOuNumeroDocumento17;
	}

	public void setMatriculaOuNumeroDocumento17(String numeroDocumento17) {
		this.matriculaOuNumeroDocumento17 = numeroDocumento17;
	}

	public String getMatriculaOuNumeroDocumento18() {
		return matriculaOuNumeroDocumento18;
	}

	public void setMatriculaOuNumeroDocumento18(String numeroDocumento18) {
		this.matriculaOuNumeroDocumento18 = numeroDocumento18;
	}

	public String getMatriculaOuNumeroDocumento19() {
		return matriculaOuNumeroDocumento19;
	}

	public void setMatriculaOuNumeroDocumento19(String numeroDocumento19) {
		this.matriculaOuNumeroDocumento19 = numeroDocumento19;
	}

	public String getMatriculaOuNumeroDocumento2() {
		return matriculaOuNumeroDocumento2;
	}

	public void setMatriculaOuNumeroDocumento2(String numeroDocumento2) {
		this.matriculaOuNumeroDocumento2 = numeroDocumento2;
	}

	public String getMatriculaOuNumeroDocumento20() {
		return matriculaOuNumeroDocumento20;
	}

	public void setMatriculaOuNumeroDocumento20(String numeroDocumento20) {
		this.matriculaOuNumeroDocumento20 = numeroDocumento20;
	}

	public String getMatriculaOuNumeroDocumento3() {
		return matriculaOuNumeroDocumento3;
	}

	public void setMatriculaOuNumeroDocumento3(String numeroDocumento3) {
		this.matriculaOuNumeroDocumento3 = numeroDocumento3;
	}

	public String getMatriculaOuNumeroDocumento4() {
		return matriculaOuNumeroDocumento4;
	}

	public void setMatriculaOuNumeroDocumento4(String numeroDocumento4) {
		this.matriculaOuNumeroDocumento4 = numeroDocumento4;
	}

	public String getMatriculaOuNumeroDocumento5() {
		return matriculaOuNumeroDocumento5;
	}

	public void setMatriculaOuNumeroDocumento5(String numeroDocumento5) {
		this.matriculaOuNumeroDocumento5 = numeroDocumento5;
	}

	public String getMatriculaOuNumeroDocumento6() {
		return matriculaOuNumeroDocumento6;
	}

	public void setMatriculaOuNumeroDocumento6(String numeroDocumento6) {
		this.matriculaOuNumeroDocumento6 = numeroDocumento6;
	}

	public String getMatriculaOuNumeroDocumento7() {
		return matriculaOuNumeroDocumento7;
	}

	public void setMatriculaOuNumeroDocumento7(String numeroDocumento7) {
		this.matriculaOuNumeroDocumento7 = numeroDocumento7;
	}

	public String getMatriculaOuNumeroDocumento8() {
		return matriculaOuNumeroDocumento8;
	}

	public void setMatriculaOuNumeroDocumento8(String numeroDocumento8) {
		this.matriculaOuNumeroDocumento8 = numeroDocumento8;
	}

	public String getMatriculaOuNumeroDocumento9() {
		return matriculaOuNumeroDocumento9;
	}

	public void setMatriculaOuNumeroDocumento9(String numeroDocumento9) {
		this.matriculaOuNumeroDocumento9 = numeroDocumento9;
	}

	public String getMesAnoDocumentoCobranca() {
		return mesAnoDocumentoCobranca;
	}

	public void setMesAnoDocumentoCobranca(String mesAnoDocumentoCobranca) {
		this.mesAnoDocumentoCobranca = mesAnoDocumentoCobranca;
	}

	public String getMesAnoFatura() {
		return mesAnoFatura;
	}

	public void setMesAnoFatura(String mesAnoFatura) {
		this.mesAnoFatura = mesAnoFatura;
	}

	public String getMesAnoGuiaPagamento() {
		return mesAnoGuiaPagamento;
	}

	public void setMesAnoGuiaPagamento(String mesAnoGuiaPagamento) {
		this.mesAnoGuiaPagamento = mesAnoGuiaPagamento;
	}

	public String getTipoCampo() {
		return tipoCampo;
	}

	public void setTipoCampo(String tipoCampo) {
		this.tipoCampo = tipoCampo;
	}

}
