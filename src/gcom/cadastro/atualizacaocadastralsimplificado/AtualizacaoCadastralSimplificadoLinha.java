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

package gcom.cadastro.atualizacaocadastralsimplificado;

import gcom.cadastro.imovel.Imovel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class AtualizacaoCadastralSimplificadoLinha {

	private Integer id;
	private Integer numeroLinha;
	private AtualizacaoCadastralSimplificado arquivo;
	private Collection<AtualizacaoCadastralSimplificadoCritica> criticas;

	private String situacaoNova;
	private Imovel imovel;

	private String nomeMunicipio;
	private String nomeLogradouro;
	private String nomeBairro;
	private String numeroImovel;
	private String descricaoComplemento;
	private String nomeCliente;
	private String numeroCPF;
	private String numeroCNPJ;
	private String numeroRG;
	private String numeroTelefone;
	private Integer situacaoAgua;
	private Integer situacaoImovel;
	private Integer situacaoEsgoto;
	private String descricaoEconomia;
	private ArrayList<String> categorias = new ArrayList<String>();
	private ArrayList<String> economias = new ArrayList<String>();
	private Integer tipoLocalizacaoCavelete;
	private String numeroMedidor;
	private Integer tipoCapacidadeMedidor;
	private Integer situacaoMedidor;
	private String descricaoZonaInfluencia;
	private String descricaoBaciaColetora;
	private Integer codigoCEP;
	private String numeroInscricao;
	private String numeroMedidorEnergia;
	private String recadastroRealizado;
	
	private Date ultimaAlteracao;

	public AtualizacaoCadastralSimplificadoLinha() {
	}
	
	public AtualizacaoCadastralSimplificadoLinha(String[] dados, Integer numeroLinha) {
		if (numeroLinha != null)
			this.numeroLinha = numeroLinha;
		
		if (dados[0] != null && !"".equals(dados[0].trim()))
			situacaoNova = dados[0];
		if (dados[1] != null && !"".equals(dados[1].trim())) {
			if (imovel == null)
				imovel = new Imovel();

			imovel.setId(Integer.parseInt(dados[1]));
		}
		if (dados[2] != null && !"".equals(dados[2].trim()))
			nomeMunicipio = dados[2];
		if (dados[3] != null && !"".equals(dados[3].trim()))
			nomeLogradouro = dados[3];
		if (dados[4] != null && !"".equals(dados[4].trim()))
			nomeBairro = dados[4];
		if (dados[5] != null && !"".equals(dados[5].trim()))
			numeroImovel = dados[5];
		if (dados[6] != null && !"".equals(dados[6].trim()))
			descricaoComplemento = dados[6];
		if (dados[7] != null && !"".equals(dados[7].trim()))
			nomeCliente = dados[7];
		if (dados[8] != null && !"".equals(dados[8].trim()))
			numeroCPF = removerCharInvalidos(dados[8]);
		if (dados[9] != null && !"".equals(dados[9].trim()))
			numeroCNPJ = removerCharInvalidos(dados[9]);
		if (dados[10] != null && !"".equals(dados[10].trim()))
			numeroRG = dados[10];
		if (dados[11] != null && !"".equals(dados[11].trim()))
			numeroTelefone = removerCharInvalidos(dados[11]);
		if (dados[12] != null && !"".equals(dados[12].trim()))
			situacaoAgua = Integer.parseInt(dados[12]);
		if (dados[13] != null && !"".equals(dados[13].trim()))
			situacaoImovel = Integer.parseInt(dados[13]);
		if (dados[14] != null && !"".equals(dados[14].trim()))
			situacaoEsgoto = Integer.parseInt(dados[14]);
		if (dados[15] != null && !"".equals(dados[15].trim())) {
			descricaoEconomia = dados[15];
			separaCategoriaEconomias();
		}
		if (dados[16] != null && !"".equals(dados[16].trim()))
			tipoLocalizacaoCavelete = Integer.parseInt(dados[16]);
		if (dados[17] != null && !"".equals(dados[17].trim()))
			numeroMedidor = dados[17];
		if (dados[18] != null && !"".equals(dados[18].trim()))
			tipoCapacidadeMedidor = Integer.parseInt(dados[18]);
		if (dados[19] != null && !"".equals(dados[19].trim()))
			situacaoMedidor = Integer.parseInt(dados[19]);
		if (dados[20] != null && !"".equals(dados[20].trim()))
			descricaoZonaInfluencia = dados[20];
		if (dados[21] != null && !"".equals(dados[21].trim()))
			descricaoBaciaColetora = dados[21];
		if (dados[22] != null && !"".equals(dados[22].trim()))
			codigoCEP = Integer.parseInt(removerCharInvalidos(dados[22]));
		if (dados[23] != null && !"".equals(dados[23].trim()))
			numeroInscricao = dados[23];
		if (dados[24] != null && !"".equals(dados[24].trim()))
			numeroMedidorEnergia = dados[24];
		if (dados[25] != null && !"".equals(dados[25].trim()))
			recadastroRealizado = dados[25];
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroLinha() {
		return numeroLinha;
	}

	public void setNumeroLinha(Integer numeroLinha) {
		this.numeroLinha = numeroLinha;
	}
	
	public AtualizacaoCadastralSimplificado getArquivo() {
		return arquivo;
	}

	public void setArquivo(AtualizacaoCadastralSimplificado arquivo) {
		this.arquivo = arquivo;
	}

	public Collection<AtualizacaoCadastralSimplificadoCritica> getCriticas() {
		return criticas;
	}

	public void setCriticas(Collection<AtualizacaoCadastralSimplificadoCritica> criticas) {
		this.criticas = criticas;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public String getSituacaoNova() {
		return situacaoNova;
	}

	public void setSituacaoNova(String situacaoNova) {
		this.situacaoNova = situacaoNova;
	}

	public Integer getNumeroLigacao() {
		return imovel.getId();
	}

	public void setNumeroLigacao(Integer numeroLigacao) {
		if (imovel == null)
			imovel = new Imovel();

		imovel.setId(numeroLigacao);
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getNumeroImovel() {
		return numeroImovel;
	}

	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	public String getDescricaoComplemento() {
		return descricaoComplemento;
	}

	public void setDescricaoComplemento(String descricaoComplemento) {
		this.descricaoComplemento = descricaoComplemento;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNumeroCPF() {
		return numeroCPF;
	}

	public void setNumeroCPF(String numeroCPF) {
		this.numeroCPF = numeroCPF;
	}

	public String getNumeroCNPJ() {
		return numeroCNPJ;
	}

	public void setNumeroCNPJ(String numeroCNPJ) {
		this.numeroCNPJ = numeroCNPJ;
	}

	public String getNumeroRG() {
		return numeroRG;
	}

	public void setNumeroRG(String numeroRG) {
		this.numeroRG = numeroRG;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public Integer getSituacaoAgua() {
		return situacaoAgua;
	}

	public void setSituacaoAgua(Integer situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	public Integer getSituacaoImovel() {
		return situacaoImovel;
	}

	public void setSituacaoImovel(Integer situacaoImovel) {
		this.situacaoImovel = situacaoImovel;
	}

	public Integer getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	public void setSituacaoEsgoto(Integer situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	public String getDescricaoEconomia() {
		return descricaoEconomia;
	}

	public void setDescricaoEconomia(String descricaoEconomia) {
		this.descricaoEconomia = descricaoEconomia;
	}

	public ArrayList<String> getCategorias() {
		return categorias;
	}

	public void setCategorias(ArrayList<String> categorias) {
		this.categorias = categorias;
	}

	public ArrayList<String> getEconomias() {
		return economias;
	}

	public void setEconomias(ArrayList<String> economias) {
		this.economias = economias;
	}

	public Integer getTipoLocalizacaoCavelete() {
		return tipoLocalizacaoCavelete;
	}

	public void setTipoLocalizacaoCavelete(Integer tipoLocalizacaoCavelete) {
		this.tipoLocalizacaoCavelete = tipoLocalizacaoCavelete;
	}

	public String getNumeroMedidor() {
		return numeroMedidor;
	}

	public void setNumeroMedidor(String numeroMedidor) {
		this.numeroMedidor = numeroMedidor;
	}

	public Integer getTipoCapacidadeMedidor() {
		return tipoCapacidadeMedidor;
	}

	public void setTipoCapacidadeMedidor(Integer tipoCapacidadeMedidor) {
		this.tipoCapacidadeMedidor = tipoCapacidadeMedidor;
	}

	public Integer getSituacaoMedidor() {
		return situacaoMedidor;
	}

	public void setSituacaoMedidor(Integer situacaoMedidor) {
		this.situacaoMedidor = situacaoMedidor;
	}

	public String getDescricaoZonaInfluencia() {
		return descricaoZonaInfluencia;
	}

	public void setDescricaoZonaInfluencia(String descricaoZonaInfluencia) {
		this.descricaoZonaInfluencia = descricaoZonaInfluencia;
	}

	public String getDescricaoBaciaColetora() {
		return descricaoBaciaColetora;
	}

	public void setDescricaoBaciaColetora(String descricaoBaciaColetora) {
		this.descricaoBaciaColetora = descricaoBaciaColetora;
	}

	public Integer getCodigoCEP() {
		return codigoCEP;
	}

	public void setCodigoCEP(Integer codigoCEP) {
		this.codigoCEP = codigoCEP;
	}

	public String getNumeroInscricao() {
		return numeroInscricao;
	}

	public void setNumeroInscricao(String numeroInscricao) {
		this.numeroInscricao = numeroInscricao;
	}

	public String getNumeroMedidorEnergia() {
		return numeroMedidorEnergia;
	}

	public void setNumeroMedidorEnergia(String numeroMedidorEnergia) {
		this.numeroMedidorEnergia = numeroMedidorEnergia;
	}

	public String getRecadastroRealizado() {
		return recadastroRealizado;
	}

	public void setRecadastroRealizado(String recadastroRealizado) {
		this.recadastroRealizado = recadastroRealizado;
	}
	
	

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * Separa as categorias e economias em suas respectivas coleções.
	 */
	private void separaCategoriaEconomias() {
		for (int i = 0; i < 29; i += 3) {
			String subStringCategoria = descricaoEconomia.substring(i, i + 3);
			i += 3;
			String subStringEconomia = descricaoEconomia.substring(i, i + 3);

			if (subStringCategoria.equals("000")
					|| subStringEconomia.equals("000")) {
				break;
			} else {
				categorias.add(subStringCategoria);
				economias.add(subStringEconomia);
			}
		}

	}

	/**
	 * Remove caracteres inválidos.
	 * 
	 * @param str
	 *            String analisada e modificada
	 * @return String sem caracteres inválidos.
	 */
	public String removerCharInvalidos(String str) {

		if (str != null) {

			str = str.replace(" ", "");
			str = str.replace("-", "");
			str = str.replace(".", "");

		}

		return str;

	}
	
	public String getEconomiasFormatadas() {
		StringBuilder sb = new StringBuilder();
		
		if (categorias.size() == 0)
			separaCategoriaEconomias();
		
		for (int i = 0; i < categorias.size(); i++) {
			if (i > 0)
				sb.append(" ");
			sb.append(categorias.get(i) + "/" +economias.get(i));
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb
				.append("------------------------------------------------------------\n");
		sb.append("Nova situação: 			" + getSituacaoNova() + "\n");
		sb.append("Numero Ligação: 		" + getNumeroLigacao() + "\n");
		sb.append("Municipio: 			    " + getNomeMunicipio() + "\n");
		sb.append("Logradouro: 			" + getNomeLogradouro() + "\n");
		sb.append("Bairro: 			    " + getNomeBairro() + "\n");
		sb.append("Numero Imovel: 			" + getNumeroImovel() + "\n");
		sb.append("Complemento: 			" + getDescricaoComplemento() + "\n");
		sb.append("Nome Cliente: 			" + getNomeCliente() + "\n");
		sb.append("Numero CPF: 			" + getNumeroCPF() + "\n");
		sb.append("Numero CNPJ: 			" + getNumeroCNPJ() + "\n");
		sb.append("Numero RG: 			    " + getNumeroRG() + "\n");
		sb.append("Numero Telefone: 		" + getNumeroTelefone() + "\n");
		sb.append("Situação Agua: 			" + getSituacaoAgua() + "\n");
		sb.append("Situação Imovel: 		" + getSituacaoImovel() + "\n");
		sb.append("Situação Esgoto: 		" + getSituacaoEsgoto() + "\n");
		sb.append("Descrição Economia: 	" + getDescricaoEconomia() + "\n");
		for (int i = 0; i < categorias.size(); i++)
			sb.append("Categoria/Economia:	    " + categorias.get(i) + "/"
					+ economias.get(i) + " " + "\n");
		sb.append("\nLocalização Cavalete: 	" + getTipoLocalizacaoCavelete()
				+ "\n");
		sb.append("Numero Medidor: 		" + getNumeroMedidor() + "\n");
		sb.append("Capacidade Medidor: 	" + getTipoCapacidadeMedidor() + "\n");
		sb.append("Situação Medidor: 		" + getSituacaoMedidor() + "\n");
		sb.append("Zona Influencia: 		" + getDescricaoZonaInfluencia() + "\n");
		sb.append("Bacia Coletora: 		" + getDescricaoBaciaColetora() + "\n");
		sb.append("Numero CEP: 			" + getCodigoCEP() + "\n");
		sb.append("Numero Inscrição: 		" + getNumeroInscricao() + "\n");
		sb.append("Numero Medidor Energia: 	" + getNumeroMedidorEnergia() + "\n");
		sb.append("Recadastro Realizado: 	" + getRecadastroRealizado() + "\n");
		sb
				.append("------------------------------------------------------------\n");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((numeroLinha == null) ? 0 : numeroLinha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtualizacaoCadastralSimplificadoLinha other = (AtualizacaoCadastralSimplificadoLinha) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numeroLinha == null) {
			if (other.numeroLinha != null)
				return false;
		} else if (!numeroLinha.equals(other.numeroLinha))
			return false;
		return true;
	}

	
	

}
