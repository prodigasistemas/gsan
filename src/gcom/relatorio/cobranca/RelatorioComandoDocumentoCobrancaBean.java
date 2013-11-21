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
* Rômulo Aurélio de Melo Souza Filho
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
package gcom.relatorio.cobranca;

import gcom.cobranca.RelatorioComandoDocumentoCobrancaHelper;
import gcom.relatorio.RelatorioBean;

/**
* @author Rômulo Aurélio
* @date 20/10/2009
*/
public class RelatorioComandoDocumentoCobrancaBean implements RelatorioBean {

	
	
	private String numero1;
	private String nomeCliente1;
	private String endereco1;
	private String endereco1Linha2;
	private String endereco1Linha3;
	private String rotaGrupo1;
	private String matricula1;
	private String inscricao1;
	private String dataEmissao1;
	private String mesAno1;
	private String situacao1;
	private String executor1;
	private String dataCorte1;
	private String horaCorte1;
	private String numeroHidrometro1;
	private String leituraHidrometro1;
	private String localizacaoHidrometro1;
	private String dataVencimento1;
	private String valor1;
	private String representacaoNumericaCodBarraFormatada1;
	private String representacaoNumericaCodBarraSemDigito1;
	private String categoriaPrincipal1;
	private String situacaoLigacaoAgua1;
	private String situacaoLigacaoEsgoto1;
	private String numeroDocumento1;

	private String numero2;
	private String nomeCliente2;
	private String endereco2;
	private String endereco2Linha2;
	private String endereco2Linha3;
	private String rotaGrupo2;
	private String matricula2;
	private String inscricao2;
	private String dataEmissao2;
	private String mesAno2;
	private String situacao2;
	private String executor2;
	private String dataCorte2;
	private String horaCorte2;
	private String numeroHidrometro2;
	private String leituraHidrometro2;
	private String localizacaoHidrometro2;
	private String dataVencimento2;
	private String valor2;
	private String representacaoNumericaCodBarraFormatada2;
	private String representacaoNumericaCodBarraSemDigito2;
	private String categoriaPrincipal2;
	private String situacaoLigacaoAgua2;
	private String situacaoLigacaoEsgoto2;
	private String numeroDocumento2;
	
    public RelatorioComandoDocumentoCobrancaBean(RelatorioComandoDocumentoCobrancaHelper relatorioComandoDocumentoCobrancaHelper1,
    		RelatorioComandoDocumentoCobrancaHelper relatorioComandoDocumentoCobrancaHelper2) {
    	
    	numero1 = relatorioComandoDocumentoCobrancaHelper1.getNumero();
    	nomeCliente1 = relatorioComandoDocumentoCobrancaHelper1.getNomeCliente();
    	endereco1 = relatorioComandoDocumentoCobrancaHelper1.getEndereco();
    	endereco1Linha2 = relatorioComandoDocumentoCobrancaHelper1.getEnderecoLinha2();
    	endereco1Linha3 = relatorioComandoDocumentoCobrancaHelper1.getEnderecoLinha3();
    	rotaGrupo1 = relatorioComandoDocumentoCobrancaHelper1.getRotaGrupo();
    	matricula1 = relatorioComandoDocumentoCobrancaHelper1.getMatricula();
    	inscricao1 = relatorioComandoDocumentoCobrancaHelper1.getInscricao();
    	dataEmissao1 = relatorioComandoDocumentoCobrancaHelper1.getDataEmissao();
    	mesAno1 = relatorioComandoDocumentoCobrancaHelper1.getMesAno();
    	situacao1 = relatorioComandoDocumentoCobrancaHelper1.getSituacao();
    	executor1 = relatorioComandoDocumentoCobrancaHelper1.getExecutor();
    	dataCorte1 = relatorioComandoDocumentoCobrancaHelper1.getDataCorte();
    	horaCorte1 = relatorioComandoDocumentoCobrancaHelper1.getHoraCorte();
    	numeroHidrometro1 = relatorioComandoDocumentoCobrancaHelper1.getNumeroHidrometro();
    	leituraHidrometro1 = relatorioComandoDocumentoCobrancaHelper1.getLeituraHidrometro();
    	localizacaoHidrometro1 = relatorioComandoDocumentoCobrancaHelper1.getLocalizacaoHidrometro();
    	dataVencimento1 = relatorioComandoDocumentoCobrancaHelper1.getDataVencimento();
    	valor1 = relatorioComandoDocumentoCobrancaHelper1.getValor();
    	representacaoNumericaCodBarraFormatada1 = relatorioComandoDocumentoCobrancaHelper1.getRepresentacaoNumericaCodBarraFormatada();
    	representacaoNumericaCodBarraSemDigito1 = relatorioComandoDocumentoCobrancaHelper1.getRepresentacaoNumericaCodBarraSemDigito();
    	categoriaPrincipal1 = relatorioComandoDocumentoCobrancaHelper1.getCategoriaPrincipal();
    	situacaoLigacaoAgua1 = relatorioComandoDocumentoCobrancaHelper1.getSituacaoLigacaoAgua();
    	situacaoLigacaoEsgoto1 = relatorioComandoDocumentoCobrancaHelper1.getSituacaoLigacaoEsgoto();
    	numeroDocumento1 = relatorioComandoDocumentoCobrancaHelper1.getNumeroDocumento();
		
    	numero2 = relatorioComandoDocumentoCobrancaHelper2.getNumero();
    	nomeCliente2 = relatorioComandoDocumentoCobrancaHelper2.getNomeCliente();
    	endereco2 = relatorioComandoDocumentoCobrancaHelper2.getEndereco();
    	endereco2Linha2 = relatorioComandoDocumentoCobrancaHelper2.getEnderecoLinha2();
    	endereco2Linha3 = relatorioComandoDocumentoCobrancaHelper2.getEnderecoLinha3();
    	rotaGrupo2 = relatorioComandoDocumentoCobrancaHelper2.getRotaGrupo();
    	matricula2 = relatorioComandoDocumentoCobrancaHelper2.getMatricula();
    	inscricao2 = relatorioComandoDocumentoCobrancaHelper2.getInscricao();
    	dataEmissao2 = relatorioComandoDocumentoCobrancaHelper2.getDataEmissao();
    	mesAno2 = relatorioComandoDocumentoCobrancaHelper2.getMesAno();
    	situacao2 = relatorioComandoDocumentoCobrancaHelper2.getSituacao();
    	executor2 = relatorioComandoDocumentoCobrancaHelper2.getExecutor();
    	dataCorte2 = relatorioComandoDocumentoCobrancaHelper2.getDataCorte();
    	horaCorte2 = relatorioComandoDocumentoCobrancaHelper2.getHoraCorte();
    	numeroHidrometro2 = relatorioComandoDocumentoCobrancaHelper2.getNumeroHidrometro();
    	leituraHidrometro2 = relatorioComandoDocumentoCobrancaHelper2.getLeituraHidrometro();
    	localizacaoHidrometro2 = relatorioComandoDocumentoCobrancaHelper2.getLocalizacaoHidrometro();
    	dataVencimento2 = relatorioComandoDocumentoCobrancaHelper2.getDataVencimento();
    	valor2 = relatorioComandoDocumentoCobrancaHelper2.getValor();
    	representacaoNumericaCodBarraFormatada2 = relatorioComandoDocumentoCobrancaHelper2.getRepresentacaoNumericaCodBarraFormatada();
    	representacaoNumericaCodBarraSemDigito2 = relatorioComandoDocumentoCobrancaHelper2.getRepresentacaoNumericaCodBarraSemDigito();
    	categoriaPrincipal2 = relatorioComandoDocumentoCobrancaHelper2.getCategoriaPrincipal();
    	situacaoLigacaoAgua2 = relatorioComandoDocumentoCobrancaHelper2.getSituacaoLigacaoAgua();
    	situacaoLigacaoEsgoto2 = relatorioComandoDocumentoCobrancaHelper2.getSituacaoLigacaoEsgoto();
    	numeroDocumento2 = relatorioComandoDocumentoCobrancaHelper2.getNumeroDocumento();
   }

	public String getCategoriaPrincipal1() {
		return categoriaPrincipal1;
	}

	public void setCategoriaPrincipal1(String categoriaPrincipal1) {
		this.categoriaPrincipal1 = categoriaPrincipal1;
	}

	public String getCategoriaPrincipal2() {
		return categoriaPrincipal2;
	}

	public void setCategoriaPrincipal2(String categoriaPrincipal2) {
		this.categoriaPrincipal2 = categoriaPrincipal2;
	}

	public String getDataCorte1() {
		return dataCorte1;
	}

	public void setDataCorte1(String dataCorte1) {
		this.dataCorte1 = dataCorte1;
	}

	public String getDataCorte2() {
		return dataCorte2;
	}

	public void setDataCorte2(String dataCorte2) {
		this.dataCorte2 = dataCorte2;
	}

	public String getDataEmissao1() {
		return dataEmissao1;
	}

	public void setDataEmissao1(String dataEmissao1) {
		this.dataEmissao1 = dataEmissao1;
	}

	public String getDataEmissao2() {
		return dataEmissao2;
	}

	public void setDataEmissao2(String dataEmissao2) {
		this.dataEmissao2 = dataEmissao2;
	}

	public String getDataVencimento1() {
		return dataVencimento1;
	}

	public void setDataVencimento1(String dataVencimento1) {
		this.dataVencimento1 = dataVencimento1;
	}

	public String getDataVencimento2() {
		return dataVencimento2;
	}

	public void setDataVencimento2(String dataVencimento2) {
		this.dataVencimento2 = dataVencimento2;
	}

	public String getEndereco1() {
		return endereco1;
	}

	public void setEndereco1(String endereco1) {
		this.endereco1 = endereco1;
	}

	public String getEndereco1Linha2() {
		return endereco1Linha2;
	}

	public void setEndereco1Linha2(String endereco1Linha2) {
		this.endereco1Linha2 = endereco1Linha2;
	}

	public String getEndereco1Linha3() {
		return endereco1Linha3;
	}

	public void setEndereco1Linha3(String endereco1Linha3) {
		this.endereco1Linha3 = endereco1Linha3;
	}

	public String getEndereco2() {
		return endereco2;
	}

	public void setEndereco2(String endereco2) {
		this.endereco2 = endereco2;
	}

	public String getEndereco2Linha2() {
		return endereco2Linha2;
	}

	public void setEndereco2Linha2(String endereco2Linha2) {
		this.endereco2Linha2 = endereco2Linha2;
	}

	public String getEndereco2Linha3() {
		return endereco2Linha3;
	}

	public void setEndereco2Linha3(String endereco2Linha3) {
		this.endereco2Linha3 = endereco2Linha3;
	}

	public String getExecutor1() {
		return executor1;
	}

	public void setExecutor1(String executor1) {
		this.executor1 = executor1;
	}

	public String getExecutor2() {
		return executor2;
	}

	public void setExecutor2(String executor2) {
		this.executor2 = executor2;
	}

	public String getHoraCorte1() {
		return horaCorte1;
	}

	public void setHoraCorte1(String horaCorte1) {
		this.horaCorte1 = horaCorte1;
	}

	public String getHoraCorte2() {
		return horaCorte2;
	}

	public void setHoraCorte2(String horaCorte2) {
		this.horaCorte2 = horaCorte2;
	}

	public String getInscricao1() {
		return inscricao1;
	}

	public void setInscricao1(String inscricao1) {
		this.inscricao1 = inscricao1;
	}

	public String getInscricao2() {
		return inscricao2;
	}

	public void setInscricao2(String inscricao2) {
		this.inscricao2 = inscricao2;
	}

	public String getLeituraHidrometro1() {
		return leituraHidrometro1;
	}

	public void setLeituraHidrometro1(String leituraHidrometro1) {
		this.leituraHidrometro1 = leituraHidrometro1;
	}

	public String getLeituraHidrometro2() {
		return leituraHidrometro2;
	}

	public void setLeituraHidrometro2(String leituraHidrometro2) {
		this.leituraHidrometro2 = leituraHidrometro2;
	}

	public String getLocalizacaoHidrometro1() {
		return localizacaoHidrometro1;
	}

	public void setLocalizacaoHidrometro1(String localizacaoHidrometro1) {
		this.localizacaoHidrometro1 = localizacaoHidrometro1;
	}

	public String getLocalizacaoHidrometro2() {
		return localizacaoHidrometro2;
	}

	public void setLocalizacaoHidrometro2(String localizacaoHidrometro2) {
		this.localizacaoHidrometro2 = localizacaoHidrometro2;
	}

	public String getMatricula1() {
		return matricula1;
	}

	public void setMatricula1(String matricula1) {
		this.matricula1 = matricula1;
	}

	public String getMatricula2() {
		return matricula2;
	}

	public void setMatricula2(String matricula2) {
		this.matricula2 = matricula2;
	}

	public String getMesAno1() {
		return mesAno1;
	}

	public void setMesAno1(String mesAno1) {
		this.mesAno1 = mesAno1;
	}

	public String getMesAno2() {
		return mesAno2;
	}

	public void setMesAno2(String mesAno2) {
		this.mesAno2 = mesAno2;
	}

	public String getNomeCliente1() {
		return nomeCliente1;
	}

	public void setNomeCliente1(String nomeCliente1) {
		this.nomeCliente1 = nomeCliente1;
	}

	public String getNomeCliente2() {
		return nomeCliente2;
	}

	public void setNomeCliente2(String nomeCliente2) {
		this.nomeCliente2 = nomeCliente2;
	}

	public String getNumero1() {
		return numero1;
	}

	public void setNumero1(String numero1) {
		this.numero1 = numero1;
	}

	public String getNumero2() {
		return numero2;
	}

	public void setNumero2(String numero2) {
		this.numero2 = numero2;
	}

	public String getNumeroHidrometro1() {
		return numeroHidrometro1;
	}

	public void setNumeroHidrometro1(String numeroHidrometro1) {
		this.numeroHidrometro1 = numeroHidrometro1;
	}

	public String getNumeroHidrometro2() {
		return numeroHidrometro2;
	}

	public void setNumeroHidrometro2(String numeroHidrometro2) {
		this.numeroHidrometro2 = numeroHidrometro2;
	}

	public String getRepresentacaoNumericaCodBarraFormatada1() {
		return representacaoNumericaCodBarraFormatada1;
	}

	public void setRepresentacaoNumericaCodBarraFormatada1(
			String representacaoNumericaCodBarraFormatada1) {
		this.representacaoNumericaCodBarraFormatada1 = representacaoNumericaCodBarraFormatada1;
	}

	public String getRepresentacaoNumericaCodBarraFormatada2() {
		return representacaoNumericaCodBarraFormatada2;
	}

	public void setRepresentacaoNumericaCodBarraFormatada2(
			String representacaoNumericaCodBarraFormatada2) {
		this.representacaoNumericaCodBarraFormatada2 = representacaoNumericaCodBarraFormatada2;
	}

	public String getRepresentacaoNumericaCodBarraSemDigito1() {
		return representacaoNumericaCodBarraSemDigito1;
	}

	public void setRepresentacaoNumericaCodBarraSemDigito1(
			String representacaoNumericaCodBarraSemDigito1) {
		this.representacaoNumericaCodBarraSemDigito1 = representacaoNumericaCodBarraSemDigito1;
	}

	public String getRepresentacaoNumericaCodBarraSemDigito2() {
		return representacaoNumericaCodBarraSemDigito2;
	}

	public void setRepresentacaoNumericaCodBarraSemDigito2(
			String representacaoNumericaCodBarraSemDigito2) {
		this.representacaoNumericaCodBarraSemDigito2 = representacaoNumericaCodBarraSemDigito2;
	}

	public String getRotaGrupo1() {
		return rotaGrupo1;
	}

	public void setRotaGrupo1(String rotaGrupo1) {
		this.rotaGrupo1 = rotaGrupo1;
	}

	public String getRotaGrupo2() {
		return rotaGrupo2;
	}

	public void setRotaGrupo2(String rotaGrupo2) {
		this.rotaGrupo2 = rotaGrupo2;
	}

	public String getSituacao1() {
		return situacao1;
	}

	public void setSituacao1(String situacao1) {
		this.situacao1 = situacao1;
	}

	public String getSituacao2() {
		return situacao2;
	}

	public void setSituacao2(String situacao2) {
		this.situacao2 = situacao2;
	}

	public String getSituacaoLigacaoAgua1() {
		return situacaoLigacaoAgua1;
	}

	public void setSituacaoLigacaoAgua1(String situacaoLigacaoAgua1) {
		this.situacaoLigacaoAgua1 = situacaoLigacaoAgua1;
	}

	public String getSituacaoLigacaoAgua2() {
		return situacaoLigacaoAgua2;
	}

	public void setSituacaoLigacaoAgua2(String situacaoLigacaoAgua2) {
		this.situacaoLigacaoAgua2 = situacaoLigacaoAgua2;
	}

	public String getSituacaoLigacaoEsgoto1() {
		return situacaoLigacaoEsgoto1;
	}

	public void setSituacaoLigacaoEsgoto1(String situacaoLigacaoEsgoto1) {
		this.situacaoLigacaoEsgoto1 = situacaoLigacaoEsgoto1;
	}

	public String getSituacaoLigacaoEsgoto2() {
		return situacaoLigacaoEsgoto2;
	}

	public void setSituacaoLigacaoEsgoto2(String situacaoLigacaoEsgoto2) {
		this.situacaoLigacaoEsgoto2 = situacaoLigacaoEsgoto2;
	}

	public String getValor1() {
		return valor1;
	}

	public void setValor1(String valor1) {
		this.valor1 = valor1;
	}

	public String getValor2() {
		return valor2;
	}

	public void setValor2(String valor2) {
		this.valor2 = valor2;
	}

	public RelatorioComandoDocumentoCobrancaBean() {
		super();
	}

	public String getNumeroDocumento2() {
		return numeroDocumento2;
	}

	public void setNumeroDocumento2(String numeroDocumento2) {
		this.numeroDocumento2 = numeroDocumento2;
	}

	public String getNumeroDocumento1() {
		return numeroDocumento1;
	}

	public void setNumeroDocumento1(String numeroDocumento1) {
		this.numeroDocumento1 = numeroDocumento1;
	}
	
}
