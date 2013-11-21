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
package gcom.micromedicao;

import java.io.Serializable;
import java.util.Date;

/**
 * Descrição da classe 
 *
 * @author Rômulo Aurélio
 * @date 04/05/2009
 */
public class FiltrarHidrometroHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private String idHidrometro;

	private Date dataAquisicao;

	private String faixaFinal;

	private String faixaInicial;

	private String fixo;

	private String idHidrometroCapacidade;

	private String idHidrometroClasseMetrologica;

	private String idHidrometroDiametro;

	private String idHidrometroTipo;
	
	private String idHidrometroRelojoaria;

	private String idLocalArmazenagem;

	private String localArmazenagemDescricao;

	private String localArmazenagemDescricaoOrigem;

	private String localArmazenagemDescricaoDestino;

	private String idHidrometroMarca;

	private String idNumeroDigitosLeitura;
	
	//private Integer idTeste;

	private String indicadorMacromedidor;

	private String numeroHidrometro;

	private String[] hidrometroSelectID;
	
	private String[] idRegistrosRemocao;

	private String dataMovimentacaoInicial;

	private String dataMovimentacaoFinal;

	private String horaMovimentacaoInicial;

	private String horaMovimentacaoFinal;

	private String motivoMovimentacao;

	private String localArmazenagemDestino;

	private String localArmazenagemOrigem;

	private String usuario;
	
	private String nomeUsuario;

	private String idHidrometroSituacao;
	
	private String finalidade;
	
	private String idLocalidade;
	
	private String nomeLocalidade;
	
	private String idSetorComercial;
	
	private String codigoSetorComercial;
	
	private String nomeSetorComercial;
	
	private String numeroQuadra;
	
	private String idQuadra;
	
	private String quadraMensagem;
	
	private String vazaoTransicao;
	
	private String vazaoNominal;
	
	private String vazaoMinima;
	
	private String notaFiscal;
	
	private String tempoGarantiaAnos;
	
	private String anoFabricacao;

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Date getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public String getDataMovimentacaoFinal() {
		return dataMovimentacaoFinal;
	}

	public void setDataMovimentacaoFinal(String dataMovimentacaoFinal) {
		this.dataMovimentacaoFinal = dataMovimentacaoFinal;
	}

	public String getDataMovimentacaoInicial() {
		return dataMovimentacaoInicial;
	}

	public void setDataMovimentacaoInicial(String dataMovimentacaoInicial) {
		this.dataMovimentacaoInicial = dataMovimentacaoInicial;
	}

	public String getFaixaFinal() {
		return faixaFinal;
	}

	public void setFaixaFinal(String faixaFinal) {
		this.faixaFinal = faixaFinal;
	}

	public String getFaixaInicial() {
		return faixaInicial;
	}

	public void setFaixaInicial(String faixaInicial) {
		this.faixaInicial = faixaInicial;
	}

	public String getFinalidade() {
		return finalidade;
	}

	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}

	public String getFixo() {
		return fixo;
	}

	public void setFixo(String fixo) {
		this.fixo = fixo;
	}

	public String[] getHidrometroSelectID() {
		return hidrometroSelectID;
	}

	public void setHidrometroSelectID(String[] hidrometroSelectID) {
		this.hidrometroSelectID = hidrometroSelectID;
	}

	public String getHoraMovimentacaoFinal() {
		return horaMovimentacaoFinal;
	}

	public void setHoraMovimentacaoFinal(String horaMovimentacaoFinal) {
		this.horaMovimentacaoFinal = horaMovimentacaoFinal;
	}

	public String getHoraMovimentacaoInicial() {
		return horaMovimentacaoInicial;
	}

	public void setHoraMovimentacaoInicial(String horaMovimentacaoInicial) {
		this.horaMovimentacaoInicial = horaMovimentacaoInicial;
	}

	public String getIdHidrometro() {
		return idHidrometro;
	}

	public void setIdHidrometro(String idHidrometro) {
		this.idHidrometro = idHidrometro;
	}

	public String getIdHidrometroCapacidade() {
		return idHidrometroCapacidade;
	}

	public void setIdHidrometroCapacidade(String idHidrometroCapacidade) {
		this.idHidrometroCapacidade = idHidrometroCapacidade;
	}

	public String getIdHidrometroClasseMetrologica() {
		return idHidrometroClasseMetrologica;
	}

	public void setIdHidrometroClasseMetrologica(
			String idHidrometroClasseMetrologica) {
		this.idHidrometroClasseMetrologica = idHidrometroClasseMetrologica;
	}

	public String getIdHidrometroDiametro() {
		return idHidrometroDiametro;
	}

	public void setIdHidrometroDiametro(String idHidrometroDiametro) {
		this.idHidrometroDiametro = idHidrometroDiametro;
	}

	public String getIdHidrometroMarca() {
		return idHidrometroMarca;
	}

	public void setIdHidrometroMarca(String idHidrometroMarca) {
		this.idHidrometroMarca = idHidrometroMarca;
	}

	public String getIdHidrometroRelojoaria() {
		return idHidrometroRelojoaria;
	}

	public void setIdHidrometroRelojoaria(String idHidrometroRelojoaria) {
		this.idHidrometroRelojoaria = idHidrometroRelojoaria;
	}

	public String getIdHidrometroSituacao() {
		return idHidrometroSituacao;
	}

	public void setIdHidrometroSituacao(String idHidrometroSituacao) {
		this.idHidrometroSituacao = idHidrometroSituacao;
	}

	public String getIdHidrometroTipo() {
		return idHidrometroTipo;
	}

	public void setIdHidrometroTipo(String idHidrometroTipo) {
		this.idHidrometroTipo = idHidrometroTipo;
	}

	public String getIdLocalArmazenagem() {
		return idLocalArmazenagem;
	}

	public void setIdLocalArmazenagem(String idLocalArmazenagem) {
		this.idLocalArmazenagem = idLocalArmazenagem;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdNumeroDigitosLeitura() {
		return idNumeroDigitosLeitura;
	}

	public void setIdNumeroDigitosLeitura(String idNumeroDigitosLeitura) {
		this.idNumeroDigitosLeitura = idNumeroDigitosLeitura;
	}

	public String getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}

	public String[] getIdRegistrosRemocao() {
		return idRegistrosRemocao;
	}

	public void setIdRegistrosRemocao(String[] idRegistrosRemocao) {
		this.idRegistrosRemocao = idRegistrosRemocao;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getIndicadorMacromedidor() {
		return indicadorMacromedidor;
	}

	public void setIndicadorMacromedidor(String indicadorMacromedidor) {
		this.indicadorMacromedidor = indicadorMacromedidor;
	}

	public String getLocalArmazenagemDescricao() {
		return localArmazenagemDescricao;
	}

	public void setLocalArmazenagemDescricao(String localArmazenagemDescricao) {
		this.localArmazenagemDescricao = localArmazenagemDescricao;
	}

	public String getLocalArmazenagemDescricaoDestino() {
		return localArmazenagemDescricaoDestino;
	}

	public void setLocalArmazenagemDescricaoDestino(
			String localArmazenagemDescricaoDestino) {
		this.localArmazenagemDescricaoDestino = localArmazenagemDescricaoDestino;
	}

	public String getLocalArmazenagemDescricaoOrigem() {
		return localArmazenagemDescricaoOrigem;
	}

	public void setLocalArmazenagemDescricaoOrigem(
			String localArmazenagemDescricaoOrigem) {
		this.localArmazenagemDescricaoOrigem = localArmazenagemDescricaoOrigem;
	}

	public String getLocalArmazenagemDestino() {
		return localArmazenagemDestino;
	}

	public void setLocalArmazenagemDestino(String localArmazenagemDestino) {
		this.localArmazenagemDestino = localArmazenagemDestino;
	}

	public String getLocalArmazenagemOrigem() {
		return localArmazenagemOrigem;
	}

	public void setLocalArmazenagemOrigem(String localArmazenagemOrigem) {
		this.localArmazenagemOrigem = localArmazenagemOrigem;
	}

	public String getMotivoMovimentacao() {
		return motivoMovimentacao;
	}

	public void setMotivoMovimentacao(String motivoMovimentacao) {
		this.motivoMovimentacao = motivoMovimentacao;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}

	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getQuadraMensagem() {
		return quadraMensagem;
	}

	public void setQuadraMensagem(String quadraMensagem) {
		this.quadraMensagem = quadraMensagem;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getVazaoMinima() {
		return vazaoMinima;
	}

	public void setVazaoMinima(String vazaoMinima) {
		this.vazaoMinima = vazaoMinima;
	}

	public String getVazaoNominal() {
		return vazaoNominal;
	}

	public void setVazaoNominal(String vazaoNominal) {
		this.vazaoNominal = vazaoNominal;
	}

	public String getVazaoTransicao() {
		return vazaoTransicao;
	}

	public void setVazaoTransicao(String vazaoTransicao) {
		this.vazaoTransicao = vazaoTransicao;
	}

	public String getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public String getTempoGarantiaAnos() {
		return tempoGarantiaAnos;
	}

	public void setTempoGarantiaAnos(String tempoGarantiaAnos) {
		this.tempoGarantiaAnos = tempoGarantiaAnos;
	}

	public String getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

}
