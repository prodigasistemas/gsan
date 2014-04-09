package gcom.relatorio.cadastro.micromedicao;

import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

/**
 *  [UC0999] Gerar Relatório de Coleta de Medidor de Energia.
 * 
 * @author Hugo Leonardo
 * @date 09/03/2010
 * 
 */

public class RelatorioColetaMedidorEnergiaBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String faturamentoGrupo;
	private String localidade;
	private String rota;
	private String inscricaoImovel;
	private String nomeCliente;
	private String matriculaImovel;
	private String endereco;
	
	
	public RelatorioColetaMedidorEnergiaBean(RelatorioColetaMedidorEnergiaHelper helper) {
		
		this.faturamentoGrupo = helper.getIdFaturamentoGrupo() + " - " + helper.getDescricaoFaturamentoGrupo();
		this.localidade = helper.getIdLocalidade()+" - "+helper.getDescricaoLocalidade();
		
		if (helper.getRota() != null &&
				helper.getRota().equals("")){
			this.rota = Util.adicionarZerosEsquedaNumero(3,helper.getRota().toString())+"."+
				Util.adicionarZerosEsquedaNumero(3,helper.getSequencialRota().toString());
		}else{
			this.rota = Util.adicionarZerosEsquedaNumero(3,helper.getRota().toString());
		}

		this.nomeCliente = helper.getNomeCliente();
		this.matriculaImovel = Util.retornaMatriculaImovelFormatada(Integer.parseInt(helper.getMatriculaImovel()));
	}

	public String getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	public void setFaturamentoGrupo(String faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}
	
}
