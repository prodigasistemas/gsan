package gcom.arrecadacao.bean;

import gcom.arrecadacao.ArrecadacaoDadosDiarios;
import gcom.arrecadacao.DevolucaoDadosDiarios;

import java.io.Serializable;

/**
 * Classe que engloba os objetos de arrecadacao e devolucao dados diarios
 * @author Francisco do Nascimento
 * @date   03/09/08	
 *
 */
public class ArrecadacaoDadosDiariosValoresDiariosHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrecadacaoDadosDiarios arrecadacaoDadosDiarios;
	
	private DevolucaoDadosDiarios devolucaoDadosDiarios;

	public ArrecadacaoDadosDiariosValoresDiariosHelper(ArrecadacaoDadosDiarios arrecadacaoDadosDiarios, DevolucaoDadosDiarios devolucaoDadosDiarios) {
		super();
		// TODO Auto-generated constructor stub
		this.arrecadacaoDadosDiarios = arrecadacaoDadosDiarios;
		this.devolucaoDadosDiarios = devolucaoDadosDiarios;
	}

	public ArrecadacaoDadosDiariosValoresDiariosHelper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrecadacaoDadosDiarios getArrecadacaoDadosDiarios() {
		return arrecadacaoDadosDiarios;
	}

	public void setArrecadacaoDadosDiarios(
			ArrecadacaoDadosDiarios arrecadacaoDadosDiarios) {
		this.arrecadacaoDadosDiarios = arrecadacaoDadosDiarios;
	}

	public DevolucaoDadosDiarios getDevolucaoDadosDiarios() {
		return devolucaoDadosDiarios;
	}

	public void setDevolucaoDadosDiarios(DevolucaoDadosDiarios devolucaoDadosDiarios) {
		this.devolucaoDadosDiarios = devolucaoDadosDiarios;
	}

	/**
	 * Verifica se o objeto arrecadacao que esta sendo passado possui os mesmos argumentos de
	 * agrupamento existente no devolucao. 
	 * Usado para juntar uma linha de arrecadacaoDadosDiarios com DevolucaoDadosDiarios 
	 * para a tela de consultar Dados Diarios da Arrecadacao
	 * 
	 * Agrupamento verificado eh o mesmo usado no select do metodo: filtrarDadosDiariosArrecadacao
	 * 
		anoMesReferencia, arrecadador.id, gerenciaRegional.id, localidade.id, 
		localidade.localidade.id, imovelPerfil.id, categoria.id, documentoTipo.id, 
		dataDevolucao, unidadeNegocio.id 
	 * @param arrecadacao
	 * @return
	 */
	public boolean equalsAgrupamento(ArrecadacaoDadosDiarios arrecadacaoAVerificar){
		return equalsAgrupamento(arrecadacaoAVerificar, this.devolucaoDadosDiarios);
	}
	
	public boolean equalsAgrupamento(DevolucaoDadosDiarios devolucaoAVerificar){
		return equalsAgrupamento(this.arrecadacaoDadosDiarios, devolucaoAVerificar);
	}
	
	public static boolean equalsAgrupamento(ArrecadacaoDadosDiarios arrecadacaoAVerificar,
			DevolucaoDadosDiarios devolucaoAVerificar){
		if (devolucaoAVerificar == null || arrecadacaoAVerificar == null){
			return false;
		}
		if (arrecadacaoAVerificar.getAnoMesReferenciaArrecadacao() == null ||
				arrecadacaoAVerificar.getAnoMesReferenciaArrecadacao().intValue() != 
					devolucaoAVerificar.getAnoMesReferencia()){
			return false;
		}
		if (!(arrecadacaoAVerificar.getDataPagamento() != null && 
				devolucaoAVerificar.getDataDevolucao() != null &&
				arrecadacaoAVerificar.getDataPagamento().equals(
				devolucaoAVerificar.getDataDevolucao()))){
			return false;
		}		
		if (!(arrecadacaoAVerificar.getArrecadador() != null && 
				devolucaoAVerificar.getArrecadador() != null &&
				arrecadacaoAVerificar.getArrecadador().getId() != null &&
				devolucaoAVerificar.getArrecadador().getId() != null &&
				arrecadacaoAVerificar.getArrecadador().getId().equals(
				devolucaoAVerificar.getArrecadador().getId()))){
			return false;
		}
		if (!(arrecadacaoAVerificar.getArrecadacaoForma() != null && 
				devolucaoAVerificar.getArrecadacaoForma() != null &&
				arrecadacaoAVerificar.getArrecadacaoForma().getId() != null &&
				devolucaoAVerificar.getArrecadacaoForma().getId() != null &&
				arrecadacaoAVerificar.getArrecadacaoForma().getId().equals(
				devolucaoAVerificar.getArrecadacaoForma().getId()))){
			return false;
		}
		if (!(arrecadacaoAVerificar.getGerenciaRegional() != null && 
				devolucaoAVerificar.getGerenciaRegional() != null &&
				arrecadacaoAVerificar.getGerenciaRegional().getId() != null &&
				devolucaoAVerificar.getGerenciaRegional().getId() != null &&
				arrecadacaoAVerificar.getGerenciaRegional().getId().equals(
				devolucaoAVerificar.getGerenciaRegional().getId()))){
			return false;
		}
		if (!(arrecadacaoAVerificar.getUnidadeNegocio() != null && 
				devolucaoAVerificar.getUnidadeNegocio() != null &&
				arrecadacaoAVerificar.getUnidadeNegocio().getId() != null &&
				devolucaoAVerificar.getUnidadeNegocio().getId() != null &&				
				arrecadacaoAVerificar.getUnidadeNegocio().getId().equals(
				devolucaoAVerificar.getUnidadeNegocio().getId()))){
			return false;
		}		
		if (!(arrecadacaoAVerificar.getLocalidade() != null && 
				devolucaoAVerificar.getLocalidade() != null &&
				arrecadacaoAVerificar.getLocalidade().getId() != null &&
				devolucaoAVerificar.getLocalidade().getId() != null &&
				arrecadacaoAVerificar.getLocalidade().getId().equals(
				devolucaoAVerificar.getLocalidade().getId()))){
			return false;
		}
//		if (!(arrecadacaoAVerificar.getLocalidade() != null && 
//				devolucaoAVerificar.getLocalidade() != null &&
//				arrecadacaoAVerificar.getLocalidade().getLocalidade() != null &&
//				devolucaoAVerificar.getLocalidade().getLocalidade() != null &&
//				arrecadacaoAVerificar.getLocalidade().getLocalidade().getId() != null &&
//				devolucaoAVerificar.getLocalidade().getLocalidade().getId() != null &&				
//				arrecadacaoAVerificar.getLocalidade().getLocalidade().getId().equals(
//				devolucaoAVerificar.getLocalidade().getLocalidade().getId()))){
//			return false;
//		}	
		if (!(arrecadacaoAVerificar.getImovelPerfil() != null && 
				devolucaoAVerificar.getImovelPerfil() != null &&
				arrecadacaoAVerificar.getImovelPerfil().getId() != null &&
				devolucaoAVerificar.getImovelPerfil().getId() != null &&
				arrecadacaoAVerificar.getImovelPerfil().getId().equals(
				devolucaoAVerificar.getImovelPerfil().getId()))){
			return false;
		}
		if (!(arrecadacaoAVerificar.getCategoria() != null && 
				devolucaoAVerificar.getCategoria() != null &&
				arrecadacaoAVerificar.getCategoria().equals(
				devolucaoAVerificar.getCategoria()))){
			return false;
		}
		if (!(arrecadacaoAVerificar.getDocumentoTipo() != null && 
				devolucaoAVerificar.getDocumentoTipo() != null &&
				arrecadacaoAVerificar.getDocumentoTipo().getId() != null &&
				devolucaoAVerificar.getDocumentoTipo().getId() != null &&				
				arrecadacaoAVerificar.getDocumentoTipo().getId().equals(
				devolucaoAVerificar.getDocumentoTipo().getId()))){
			return false;
		}
		if (!(arrecadacaoAVerificar.getDocumentoTipoAgregador() != null && 
				devolucaoAVerificar.getDocumentoTipoAgregador() != null &&
				arrecadacaoAVerificar.getDocumentoTipoAgregador().getId() != null &&
				devolucaoAVerificar.getDocumentoTipoAgregador().getId() != null &&				
				arrecadacaoAVerificar.getDocumentoTipoAgregador().getId().equals(
				devolucaoAVerificar.getDocumentoTipoAgregador().getId()))){
			return false;
		}
		return true;
	}

}
