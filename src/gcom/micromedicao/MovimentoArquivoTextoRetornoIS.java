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
 * Alteração realizada pela COSANPA
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

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.faturamento.FaturamentoGrupo;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Pamela Gatinho */
@ControleAlteracao()
public class MovimentoArquivoTextoRetornoIS extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	/** identifier field */
	private Integer id;

	private Integer anoMesMovimento;

	private Integer codigoSetorComercial;

	private Integer codigoRota;

	private String nomeArquivo;

	private Date tempoRetornoArquivo;

	private String arquivoTexto;

	private int leituraHidrometro;
	
	private Date ultimaAlteracao;

	private Localidade localidade;

	private FaturamentoGrupo faturamentoGrupo;
	
	private Imovel imovel;
	
	private ArquivoTextoRetornoIS arquivoTextoRetornoIS;
	
	private MedicaoTipo medicaoTipo;
	
	private LeituraAnormalidade leituraAnormalidade;
	
	private ConsumoAnormalidade consumoAnormalidade;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnoMesMovimento() {
		return anoMesMovimento;
	}

	public void setAnoMesMovimento(Integer anoMesMovimento) {
		this.anoMesMovimento = anoMesMovimento;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Integer codigoRota) {
		this.codigoRota = codigoRota;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Date getTempoRetornoArquivo() {
		return tempoRetornoArquivo;
	}

	public void setTempoRetornoArquivo(Date tempoRetornoArquivo) {
		this.tempoRetornoArquivo = tempoRetornoArquivo;
	}

	public String getArquivoTexto() {
		return arquivoTexto;
	}

	public void setArquivoTexto(String arquivoTexto) {
		this.arquivoTexto = arquivoTexto;
	}

	public int getLeituraHidrometro() {
		return leituraHidrometro;
	}

	public void setLeituraHidrometro(int leituraHidrometro) {
		this.leituraHidrometro = leituraHidrometro;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public FaturamentoGrupo getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public ArquivoTextoRetornoIS getArquivoTextoRetornoIS() {
		return arquivoTextoRetornoIS;
	}

	public void setArquivoTextoRetornoIS(ArquivoTextoRetornoIS arquivoTextoRetornoIS) {
		this.arquivoTextoRetornoIS = arquivoTextoRetornoIS;
	}

	public MedicaoTipo getMedicaoTipo() {
		return medicaoTipo;
	}

	public void setMedicaoTipo(MedicaoTipo medicaoTipo) {
		this.medicaoTipo = medicaoTipo;
	}

	public LeituraAnormalidade getLeituraAnormalidade() {
		return leituraAnormalidade;
	}

	public void setLeituraAnormalidade(LeituraAnormalidade leituraAnormalidade) {
		this.leituraAnormalidade = leituraAnormalidade;
	}

	public ConsumoAnormalidade getConsumoAnormalidade() {
		return consumoAnormalidade;
	}

	public void setConsumoAnormalidade(ConsumoAnormalidade consumoAnormalidade) {
		this.consumoAnormalidade = consumoAnormalidade;
	}

	@Override
	public Date getUltimaAlteracao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		// TODO Auto-generated method stub
		
	}

	public Filtro retornaFiltro() {
		FiltroMovimentoArquivoTextoRetornoIS filtroMovimentoArquivoTextoRetornoIS = new FiltroMovimentoArquivoTextoRetornoIS();

		filtroMovimentoArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoArquivoTextoRetornoIS.ANO_MES_MOVIMENTO);
		filtroMovimentoArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoArquivoTextoRetornoIS.CODIGO_ROTA);
		filtroMovimentoArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoArquivoTextoRetornoIS.CODIGO_SETOR);
		filtroMovimentoArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoArquivoTextoRetornoIS.NOME_ARQUIVO);
		filtroMovimentoArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoArquivoTextoRetornoIS.ARQUIVO_TEXTO_RETORNO_IS_ID);
		filtroMovimentoArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoArquivoTextoRetornoIS.TEMPO_RETORNO_ARQUIVO);
		filtroMovimentoArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoArquivoTextoRetornoIS.FATURAMENTO_GRUPO_ID);
		filtroMovimentoArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoArquivoTextoRetornoIS.LOCALIDADE_ID);
		filtroMovimentoArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoArquivoTextoRetornoIS.CONSUMO_ANORMALIDADE_ID);
		filtroMovimentoArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoArquivoTextoRetornoIS.IMOVEL_ID);
		filtroMovimentoArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoArquivoTextoRetornoIS.LEITURA_ANORMALIDADE_ID);
		filtroMovimentoArquivoTextoRetornoIS.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoArquivoTextoRetornoIS.MEDICAO_TIPO_ID);
		filtroMovimentoArquivoTextoRetornoIS.adicionarParametro(new ParametroSimples(FiltroMovimentoArquivoTextoRetornoIS.ID_MOVIMENTO_ARQUIVO_TEXTO_RETORNO_IS,
				this.getId()));
		return filtroMovimentoArquivoTextoRetornoIS;
	}

	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoArquivoTextoRetornoIS.FATURAMENTO_GRUPO_ID);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoArquivoTextoRetornoIS.IMOVEL_ID);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoArquivoTextoRetornoIS.LOCALIDADE_ID);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoArquivoTextoRetornoIS.CODIGO_SETOR);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoArquivoTextoRetornoIS.CODIGO_ROTA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoArquivoTextoRetornoIS.ANO_MES_MOVIMENTO);
		return filtro;
	}
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"imovel.id", "localidade.descricao","codigoSetorComercial", "codigoRota"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Imovel", "Localidade","Setor Comercial", "Código da Rota"};
		return labels;		
	}

}
