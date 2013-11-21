/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.cobranca.parcelamento;

import java.math.BigDecimal;
import java.util.Date;

import gcom.arrecadacao.Arrecadador;
import gcom.cadastro.cliente.Cliente;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

@ControleAlteracao()
public class ParcelamentoPagamentoCartaoCredito extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_PARCELAMENTO_PAGAMENTO_CARTAO_CREDITO_ATUALIZAR = 1594;

	private Integer id;

	private Parcelamento parcelamento;

	/** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_PARCELAMENTO_PAGAMENTO_CARTAO_CREDITO_ATUALIZAR})
	private String documentoCartaoCredito;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_PARCELAMENTO_PAGAMENTO_CARTAO_CREDITO_ATUALIZAR})
    private String numeroAutorizacao;

	/** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_PARCELAMENTO_PAGAMENTO_CARTAO_CREDITO_ATUALIZAR})
	private String numeroCartaoCredito;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_PARCELAMENTO_PAGAMENTO_CARTAO_CREDITO_ATUALIZAR})
    private Integer anoMesValidade;

    private Cliente cliente;

	private Arrecadador arrecadador;

	private Usuario usuarioConfirmacao;

	/** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_PARCELAMENTO_PAGAMENTO_CARTAO_CREDITO_ATUALIZAR})
	private String identificacaoTransacao;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_PARCELAMENTO_PAGAMENTO_CARTAO_CREDITO_ATUALIZAR})
    private BigDecimal valorParcelado;

	private Integer quantidadeParcelas;

	private Date ultimaAlteracao;
	
	private Date dataConfirmacao;
	
	private Short indicadorConfirmadoOperadora;
	
	private Date dataConfirmadoOperadora;
	
	private BigDecimal valorConfirmadoOperadora;

	public Integer getAnoMesValidade() {
		return anoMesValidade;
	}

	public void setAnoMesValidade(Integer anoMesValidade) {
		this.anoMesValidade = anoMesValidade;
	}

	public Arrecadador getArrecadador() {
		return arrecadador;
	}

	public void setArrecadador(Arrecadador arrecadador) {
		this.arrecadador = arrecadador;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentificacaoTransacao() {
		return identificacaoTransacao;
	}

	public void setIdentificacaoTransacao(String identificacaoTransacao) {
		this.identificacaoTransacao = identificacaoTransacao;
	}

	public String getDocumentoCartaoCredito() {
		return documentoCartaoCredito;
	}

	public void setDocumentoCartaoCredito(String documentoCartaoCredito) {
		this.documentoCartaoCredito = documentoCartaoCredito;
	}

	public String getNumeroAutorizacao() {
		return numeroAutorizacao;
	}

	public void setNumeroAutorizacao(String numeroAutorizacao) {
		this.numeroAutorizacao = numeroAutorizacao;
	}

	public String getNumeroCartaoCredito() {
		return numeroCartaoCredito;
	}

	public void setNumeroCartaoCredito(String numeroCartaoCredito) {
		this.numeroCartaoCredito = numeroCartaoCredito;
	}

	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Usuario getUsuarioConfirmacao() {
		return usuarioConfirmacao;
	}

	public void setUsuarioConfirmacao(Usuario usuarioConfirmacao) {
		this.usuarioConfirmacao = usuarioConfirmacao;
	}

	public BigDecimal getValorParcelado() {
		return valorParcelado;
	}

	public void setValorParcelado(BigDecimal valorParcelado) {
		this.valorParcelado = valorParcelado;
	}

	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public Date getDataConfirmacao() {
		return dataConfirmacao;
	}

	public void setDataConfirmacao(Date dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
	}

	public Date getDataConfirmadoOperadora() {
		return dataConfirmadoOperadora;
	}

	public void setDataConfirmadoOperadora(Date dataConfirmadoOperadora) {
		this.dataConfirmadoOperadora = dataConfirmadoOperadora;
	}

	public Short getIndicadorConfirmadoOperadora() {
		return indicadorConfirmadoOperadora;
	}

	public void setIndicadorConfirmadoOperadora(Short indicadorConfirmadoOperadora) {
		this.indicadorConfirmadoOperadora = indicadorConfirmadoOperadora;
	}
	
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId() + "";
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"parcelamento.id","valorParcelado"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Parcelamento", "Valor Transação"};
		return labels;		
	}
	
	public Filtro retornaFiltro() {
		
		FiltroParcelamentoPagamentoCartaoCredito filtroParcelamentoPagamentoCartaoCredito = new FiltroParcelamentoPagamentoCartaoCredito();
		
		filtroParcelamentoPagamentoCartaoCredito.adicionarParametro(new ParametroSimples(FiltroParcelamentoPagamentoCartaoCredito.ID, this.getId()));
		
		filtroParcelamentoPagamentoCartaoCredito.adicionarCaminhoParaCarregamentoEntidade("parcelamento");
		filtroParcelamentoPagamentoCartaoCredito.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroParcelamentoPagamentoCartaoCredito.adicionarCaminhoParaCarregamentoEntidade("arrecadador");
		filtroParcelamentoPagamentoCartaoCredito.adicionarCaminhoParaCarregamentoEntidade("usuarioConfirmacao");
		
		return filtroParcelamentoPagamentoCartaoCredito;
	}
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public BigDecimal getValorConfirmadoOperadora() {
		return valorConfirmadoOperadora;
	}

	public void setValorConfirmadoOperadora(BigDecimal valorConfirmadoOperadora) {
		this.valorConfirmadoOperadora = valorConfirmadoOperadora;
	}
}
