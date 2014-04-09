package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.Material;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Classe
 * 
 * @author compesa
 * @created 28 de Junho de 2004
 */
public class FiltrarTipoServicoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idTipoServico;
	
    private String descricaoTipoServico;

    private String abreviada;

    private String subgrupo;

    private String valorInicial;

    private String valorFinal;

    //removido para ser dividido em rua e calçada
    //private String pavimento;
    
    private String atualizacaoComercial;

    private String servicoTerceirizado;

    private String indicadorFiscalizacaoInfracao;

    private String indicadorVistoria;

    private String codigoServico;

    private String tempoMedioIncial;
    
    private String tempoMedioFinal;

    private String idTipoDebito;
    
    private String descricaoTipoDebito;

    private String idTipoCredito;

    private String idPrioridadeServico;

    private String perfilServico;
    
    private String descricaoPerfilServico;

    private String idTipoServicoReferencia;
    
    private String indicadorAtividadeUnica;

    private String indicadorUso;
    
    private String servicoTipoMaterialQuantidadePadrao;
    
    private String indicadorAtualizar;
    
    private String servicoTipoAtividadeOrdemExecucao;
    
    private String descricaoTipoServicoReferencia;
    
    private String atividadesTipoServico;
    private String dsAtividadesTipoServico;
    private String dsAtividadeCor;
    private String dsTipoServicoReferenciaCor;
    
    private String tipoServicoMaterial;
    private String dsTipoServicoMaterial;
    private String dsMaterialCor;
    private String dsPerfilServicoCor;
    
    //  Atributos de controle da Coleção 
    private Collection<Atividade> arrayAtividade = new ArrayList<Atividade>();
    private Collection<Material> arrayMateriais = new ArrayList<Material>();
    private Collection<Integer> atividades = new ArrayList<Integer>();
    private Collection<Integer> materiais = new ArrayList<Integer>();
    private String tipoServicoMaterialId;
    private String tipoServicoAtividadeId;
    private String tamanhoColecao = "0";
    private String method = "";
    private String deleteComponente;
    
    private String indicadorPavimentoRua;
    private String indicadorPavimentoCalcada;
    
    private String indicadorEmpresaCobranca;
    
    private String indicadorInspecaoAnormalidade;
    
    private String indicadorProgramacaoAutomatica;
    
    private String indicativoTipoSevicoEconomias;
    
    private String grauImportancia;
    
    private String indicadorEncAutomaticoRAQndEncOS;
    
    // Controle
    //private String validaTipoPerfilServico = "false";
    
    // Remove Atividade da coleção de IdAtividades e Atividade
    public void removeServicoTipoAtividade (Integer id, Collection atividades, Collection arratividades){
		Collection colecaoId = atividades;
		Collection<Atividade> colecaoAtividades = arratividades;
		
		if(colecaoId != null && !colecaoId.isEmpty()){
			Iterator itera = colecaoId.iterator();
			
			while (itera.hasNext()) {
				Integer stp = (Integer) itera.next();
				
				if(stp == id.intValue()){
					itera.remove();
					
				}
			}
		}
		if(colecaoAtividades != null && !colecaoAtividades.isEmpty()){
			Iterator itera = colecaoAtividades.iterator();
			
			while (itera.hasNext()) {
				Atividade stp = (Atividade) itera.next();
				
				if(stp.getId() == id.intValue()){
					itera.remove();
					
				}
			}
		}
		
		this.setArrayAtividade(colecaoAtividades);
	}
    //  Remove Atividade da coleção de IdMateriais e de MaterialUnidade
    public void removeServicoTipoMateriais (Integer id, Collection materiais,Collection arrayMateriais ){
		Collection<Material> colecaoArray = arrayMateriais;
		Collection colecao = arrayMateriais;
    	if(colecao != null && !colecao.isEmpty()){
			Iterator itera = colecao.iterator();
			
			while (itera.hasNext()) {
				Material stp = (Material) itera.next();
				
				if(stp.getId() == id.intValue()){
					itera.remove();
				}
			}
		}
    	if(colecaoArray != null && !colecaoArray.isEmpty()){
			Iterator itera = colecaoArray.iterator();
			
			while (itera.hasNext()) {
				Material stp = (Material) itera.next();
				
				if(stp.getId() == id.intValue()){
					itera.remove();
				}
			}
		}
    	
    	this.setArrayMateriais(colecaoArray);
	}
	
    /**
	 * @return Retorna o campo arrayAtividade.
	 */
	public Collection<Atividade> getArrayAtividade() {
		return arrayAtividade;
	}
	/**
	 * @param arrayAtividade O arrayAtividade a ser setado.
	 */
	public void setArrayAtividade(Collection<Atividade> arrayAtividade) {
		this.arrayAtividade = arrayAtividade;
	}
	/**
	 * @return Retorna o campo arrayMateriais.
	 */
	public Collection<Material> getArrayMateriais() {
		return arrayMateriais;
	}
	/**
	 * @param arrayMateriais O arrayMateriais a ser setado.
	 */
	public void setArrayMateriais(Collection<Material> arrayMateriais) {
		this.arrayMateriais = arrayMateriais;
	}
	/**
	 * @return Retorna o campo atividades.
	 */
	public Collection<Integer> getAtividades() {
		return atividades;
	}
	/**
	 * @param atividades O atividades a ser setado.
	 */
	public void setAtividades(Collection<Integer> atividades) {
		this.atividades = atividades;
	}
	/**
	 * @return Retorna o campo deleteComponente.
	 */
	public String getDeleteComponente() {
		return deleteComponente;
	}
	/**
	 * @param deleteComponente O deleteComponente a ser setado.
	 */
	public void setDeleteComponente(String deleteComponente) {
		this.deleteComponente = deleteComponente;
	}
	/**
	 * @return Retorna o campo materiais.
	 */
	public Collection<Integer> getMateriais() {
		return materiais;
	}
	/**
	 * @param materiais O materiais a ser setado.
	 */
	public void setMateriais(Collection<Integer> materiais) {
		this.materiais = materiais;
	}
	/**
	 * @return Retorna o campo method.
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method O method a ser setado.
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @return Retorna o campo tamanhoColecao.
	 */
	public String getTamanhoColecao() {
		return tamanhoColecao;
	}
	/**
	 * @param tamanhoColecao O tamanhoColecao a ser setado.
	 */
	public void setTamanhoColecao(String tamanhoColecao) {
		this.tamanhoColecao = tamanhoColecao;
	}
	/**
	 * @return Retorna o campo tipoServicoAtividadeId.
	 */
	public String getTipoServicoAtividadeId() {
		return tipoServicoAtividadeId;
	}
	/**
	 * @param tipoServicoAtividadeId O tipoServicoAtividadeId a ser setado.
	 */
	public void setTipoServicoAtividadeId(String tipoServicoAtividadeId) {
		this.tipoServicoAtividadeId = tipoServicoAtividadeId;
	}
	/**
	 * @return Retorna o campo tipoServicoMaterialId.
	 */
	public String getTipoServicoMaterialId() {
		return tipoServicoMaterialId;
	}
	/**
	 * @param tipoServicoMaterialId O tipoServicoMaterialId a ser setado.
	 */
	public void setTipoServicoMaterialId(String tipoServicoMaterialId) {
		this.tipoServicoMaterialId = tipoServicoMaterialId;
	}
	
	public void limparForm(){
      this.idTipoServico = null;
      this.descricaoTipoServico = null;
      this.tipoServicoAtividadeId = null;
      this.atividadesTipoServico = null;
      this.dsAtividadesTipoServico = null;
      this.tipoServicoMaterialId = null;
      this.tipoServicoMaterial = null;
      this.dsTipoServicoMaterial = null;
      this.abreviada = null;
      this.subgrupo = null;
      this.valorInicial = null;
      this.valorFinal = null;
      //this.pavimento = null;       
      this.atualizacaoComercial = null;
      this.servicoTerceirizado = null;
      this.indicadorFiscalizacaoInfracao = null;
      this.indicadorVistoria = null;
      this.codigoServico = null;
      this.tempoMedioIncial = null;       
      this.tempoMedioFinal = null;
      this.idTipoDebito = null;      
      this.descricaoTipoDebito = null;
      this.idTipoCredito = null;
      this.idPrioridadeServico = null;
      this.perfilServico = null;        
      this.descricaoPerfilServico = null;
      this.idTipoServicoReferencia = null;      
      this.indicadorAtividadeUnica = null;
      this.indicadorUso = null;    
      this.servicoTipoMaterialQuantidadePadrao = null;       
      this.indicadorAtualizar = null;        
      this.servicoTipoAtividadeOrdemExecucao = null;       
      this.descricaoTipoServicoReferencia = null;  
      this.indicadorPavimentoRua = null;
      this.indicadorPavimentoCalcada = null;
      this.indicativoTipoSevicoEconomias = null;
      this.indicadorEmpresaCobranca = null;
      this.indicadorInspecaoAnormalidade = null;
      this.indicadorProgramacaoAutomatica = null;
    }

	public String getAbreviada() {
		return abreviada;
	}

	public void setAbreviada(String abreviada) {
		this.abreviada = abreviada;
	}

	public String getAtualizacaoComercial() {
		return atualizacaoComercial;
	}

	public void setAtualizacaoComercial(String atualizacaoComercial) {
		this.atualizacaoComercial = atualizacaoComercial;
	}

	public String getCodigoServico() {
		return codigoServico;
	}

	public void setCodigoServico(String codigoServico) {
		this.codigoServico = codigoServico;
	}

	public String getIdPrioridadeServico() {
		return idPrioridadeServico;
	}

	public void setIdPrioridadeServico(String idPrioridadeServico) {
		this.idPrioridadeServico = idPrioridadeServico;
	}

	public String getIdTipoCredito() {
		return idTipoCredito;
	}

	public void setIdTipoCredito(String idTipoCredito) {
		this.idTipoCredito = idTipoCredito;
	}

	public String getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}

	public String getIdTipoServicoReferencia() {
		return idTipoServicoReferencia;
	}

	public void setIdTipoServicoReferencia(String idTipoServicoReferencia) {
		this.idTipoServicoReferencia = idTipoServicoReferencia;
	}

	public String getIndicadorAtividadeUnica() {
		return indicadorAtividadeUnica;
	}

	public void setIndicadorAtividadeUnica(String indicadorAtividadeUnica) {
		this.indicadorAtividadeUnica = indicadorAtividadeUnica;
	}

	public String getIndicadorFiscalizacaoInfracao() {
		return indicadorFiscalizacaoInfracao;
	}

	public void setIndicadorFiscalizacaoInfracao(
			String indicadorFiscalizacaoInfracao) {
		this.indicadorFiscalizacaoInfracao = indicadorFiscalizacaoInfracao;
	}

	public String getIndicadorVistoria() {
		return indicadorVistoria;
	}

	public void setIndicadorVistoria(String indicadorVistoria) {
		this.indicadorVistoria = indicadorVistoria;
	}

	/*public String getPavimento() {
		return pavimento;
	}

	public void setPavimento(String pavimento) {
		this.pavimento = pavimento;
	}*/

	public String getPerfilServico() {
		return perfilServico;
	}

	public void setPerfilServico(String perfilServico) {
		this.perfilServico = perfilServico;
	}

	public String getServicoTerceirizado() {
		return servicoTerceirizado;
	}

	public void setServicoTerceirizado(String servicoTerceirizado) {
		this.servicoTerceirizado = servicoTerceirizado;
	}

	public String getSubgrupo() {
		return subgrupo;
	}

	public void setSubgrupo(String subgrupo) {
		this.subgrupo = subgrupo;
	}

	public String getTempoMedioFinal() {
		return tempoMedioFinal;
	}

	public void setTempoMedioFinal(String tempoMedioFinal) {
		this.tempoMedioFinal = tempoMedioFinal;
	}

	public String getTempoMedioIncial() {
		return tempoMedioIncial;
	}

	public void setTempoMedioIncial(String tempoMedioIncial) {
		this.tempoMedioIncial = tempoMedioIncial;
	}

	public String getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(String valorFinal) {
		this.valorFinal = valorFinal;
	}

	public String getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(String valorInicial) {
		this.valorInicial = valorInicial;
	}

    public String getIndicadorUso() {
        return indicadorUso;
    }

    public void setIndicadorUso(String indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

	public String getDescricaoTipoDebito() {
		return descricaoTipoDebito;
	}

	public void setDescricaoTipoDebito(String descricaoTipoDebito) {
		this.descricaoTipoDebito = descricaoTipoDebito;
	}

	public String getDescricaoPerfilServico() {
		return descricaoPerfilServico;
	}

	public void setDescricaoPerfilServico(String descricaoPerfilServico) {
		this.descricaoPerfilServico = descricaoPerfilServico;
	}

	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}
	
/*	public void addServicoTipoAtividade() {
		Atividade atv;
		ServicoTipoAtividade stp;
		atv = new Atividade();
		atv.setId(Integer.parseInt(getIdAtividadeTipoServico()));
		atv.setDescricao(getDescricaoAtividadeTipoServico());
		stp = new ServicoTipoAtividade();
		stp.setNumeroExecucao(Short.parseShort(getServicoTipoAtividadeOrdemExecucao()));
		stp.setAtividade(atv);
		servicoTipoAtividades.add(stp);
	}
	
	public void removeServicoTipoAtividade() {
		for (Iterator iter = getServicoTipoAtividades().iterator(); iter.hasNext();) {
			ServicoTipoAtividade stp = (ServicoTipoAtividade) iter.next();
			String string = "$" + stp.getAtividade().getId() + "$" + stp.getNumeroExecucao() + "$";
			if (string.equals(getIdAtividadeTipoServico())) {
				iter.remove();
			}
		}		
	}
	
	public void removeAllServicoTipoAtividade() {
		for (Iterator iter = getServicoTipoAtividades().iterator(); iter.hasNext();) {
			//ServicoTipoAtividade stp = (ServicoTipoAtividade) iter.next();
			iter.remove();
		}		
	}	
	
	public void addServicoTipoMaterial() {
		Material mat;
		ServicoTipoMaterial stm;
		mat = new Material();
		mat.setId(Integer.parseInt(getIdMaterialTipoServico()));
		mat.setDescricao(getDescricaoMaterialTipoServico());
		stm = new ServicoTipoMaterial();
		try {
			stm.setQuantidadePadrao(new BigDecimal(getServicoTipoMaterialQuantidadePadrao()));
		} catch (NumberFormatException e) {			
		}
		stm.setMaterial(mat);
		servicoTipoMateriais.add(stm);
	}
	
	public void removeServicoTipoMaterial() {
		for (Iterator iter = getServicoTipoMateriais().iterator(); iter.hasNext();) {
			ServicoTipoMaterial stm = (ServicoTipoMaterial) iter.next();
			String string = "$" + stm.getMaterial().getId() + "$";
			if (string.equals(getIdMaterialTipoServico())) {
				iter.remove();
			}
		}		
	}*/


	public String getServicoTipoAtividadeOrdemExecucao() {
		return servicoTipoAtividadeOrdemExecucao;
	}

	public void setServicoTipoAtividadeOrdemExecucao(
			String servicoTipoAtividadeOrdemExecucao) {
		this.servicoTipoAtividadeOrdemExecucao = servicoTipoAtividadeOrdemExecucao;
	}

	public String getServicoTipoMaterialQuantidadePadrao() {
		return servicoTipoMaterialQuantidadePadrao;
	}

	public void setServicoTipoMaterialQuantidadePadrao(
			String servicoTipoMaterialQuantidadePadrao) {
		this.servicoTipoMaterialQuantidadePadrao = servicoTipoMaterialQuantidadePadrao;
	}

	public String getDescricaoTipoServicoReferencia() {
		return descricaoTipoServicoReferencia;
	}

	public void setDescricaoTipoServicoReferencia(
			String descricaoTipoServicoReferencia) {
		this.descricaoTipoServicoReferencia = descricaoTipoServicoReferencia;
	}
	/**
	 * @return Retorna o campo idTipoServico.
	 */
	public String getIdTipoServico() {
		return idTipoServico;
	}
	/**
	 * @param idTipoServico O idTipoServico a ser setado.
	 */
	public void setIdTipoServico(String idTipoServico) {
		this.idTipoServico = idTipoServico;
	}
	/**
	 * @return Retorna o campo atividadesTipoServico.
	 */
	public String getAtividadesTipoServico() {
		return atividadesTipoServico;
	}
	/**
	 * @param atividadesTipoServico O atividadesTipoServico a ser setado.
	 */
	public void setAtividadesTipoServico(String atividadesTipoServico) {
		this.atividadesTipoServico = atividadesTipoServico;
	}
	/**
	 * @return Retorna o campo dsAtividadeCor.
	 */
	public String getDsAtividadeCor() {
		return dsAtividadeCor;
	}
	/**
	 * @param dsAtividadeCor O dsAtividadeCor a ser setado.
	 */
	public void setDsAtividadeCor(String dsAtividadeCor) {
		this.dsAtividadeCor = dsAtividadeCor;
	}
	/**
	 * @return Retorna o campo dsAtividadesTipoServico.
	 */
	public String getDsAtividadesTipoServico() {
		return dsAtividadesTipoServico;
	}
	/**
	 * @param dsAtividadesTipoServico O dsAtividadesTipoServico a ser setado.
	 */
	public void setDsAtividadesTipoServico(String dsAtividadesTipoServico) {
		this.dsAtividadesTipoServico = dsAtividadesTipoServico;
	}
	/**
	 * @return Retorna o campo dsTipoServicoReferenciaCor.
	 */
	public String getDsTipoServicoReferenciaCor() {
		return dsTipoServicoReferenciaCor;
	}
	/**
	 * @param dsTipoServicoReferenciaCor O dsTipoServicoReferenciaCor a ser setado.
	 */
	public void setDsTipoServicoReferenciaCor(String dsTipoServicoReferenciaCor) {
		this.dsTipoServicoReferenciaCor = dsTipoServicoReferenciaCor;
	}
	/**
	 * @return Retorna o campo dsMaterialCor.
	 */
	public String getDsMaterialCor() {
		return dsMaterialCor;
	}
	/**
	 * @param dsMaterialCor O dsMaterialCor a ser setado.
	 */
	public void setDsMaterialCor(String dsMaterialCor) {
		this.dsMaterialCor = dsMaterialCor;
	}
	/**
	 * @return Retorna o campo dsPerfilServicoCor.
	 */
	public String getDsPerfilServicoCor() {
		return dsPerfilServicoCor;
	}
	/**
	 * @param dsPerfilServicoCor O dsPerfilServicoCor a ser setado.
	 */
	public void setDsPerfilServicoCor(String dsPerfilServicoCor) {
		this.dsPerfilServicoCor = dsPerfilServicoCor;
	}
	/**
	 * @return Retorna o campo dsTipoServicoMaterial.
	 */
	public String getDsTipoServicoMaterial() {
		return dsTipoServicoMaterial;
	}
	/**
	 * @param dsTipoServicoMaterial O dsTipoServicoMaterial a ser setado.
	 */
	public void setDsTipoServicoMaterial(String dsTipoServicoMaterial) {
		this.dsTipoServicoMaterial = dsTipoServicoMaterial;
	}
	/**
	 * @return Retorna o campo tipoServicoMaterial.
	 */
	public String getTipoServicoMaterial() {
		return tipoServicoMaterial;
	}
	/**
	 * @param tipoServicoMaterial O tipoServicoMaterial a ser setado.
	 */
	public void setTipoServicoMaterial(String tipoServicoMaterial) {
		this.tipoServicoMaterial = tipoServicoMaterial;
	}
	/**
	 * @return Retorna o campo descricaoTipoServico.
	 */
	public String getDescricaoTipoServico() {
		return descricaoTipoServico;
	}
	/**
	 * @param descricaoTipoServico O descricaoTipoServico a ser setado.
	 */
	public void setDescricaoTipoServico(String descricaoTipoServico) {
		this.descricaoTipoServico = descricaoTipoServico;
	}
	public String getIndicadorPavimentoCalcada() {
		return indicadorPavimentoCalcada;
	}
	public void setIndicadorPavimentoCalcada(String indicadorPavimentoCalcada) {
		this.indicadorPavimentoCalcada = indicadorPavimentoCalcada;
	}
	public String getIndicadorPavimentoRua() {
		return indicadorPavimentoRua;
	}
	public void setIndicadorPavimentoRua(String indicadorPavimentoRua) {
		this.indicadorPavimentoRua = indicadorPavimentoRua;
	}
	public String getIndicativoTipoSevicoEconomias() {
		return indicativoTipoSevicoEconomias;
	}
	public void setIndicativoTipoSevicoEconomias(
			String indicativoTipoSevicoEconomias) {
		this.indicativoTipoSevicoEconomias = indicativoTipoSevicoEconomias;
	}
	public String getIndicadorEmpresaCobranca() {
		return indicadorEmpresaCobranca;
	}
	public void setIndicadorEmpresaCobranca(String indicadorEmpresaCobranca) {
		this.indicadorEmpresaCobranca = indicadorEmpresaCobranca;
	}
	public String getIndicadorInspecaoAnormalidade() {
		return indicadorInspecaoAnormalidade;
	}
	public void setIndicadorInspecaoAnormalidade(
			String indicadorInspecaoAnormalidade) {
		this.indicadorInspecaoAnormalidade = indicadorInspecaoAnormalidade;
	}
	public String getIndicadorProgramacaoAutomatica() {
		return indicadorProgramacaoAutomatica;
	}
	public void setIndicadorProgramacaoAutomatica(
			String indicadorProgramacaoAutomatica) {
		this.indicadorProgramacaoAutomatica = indicadorProgramacaoAutomatica;
	}
	public String getGrauImportancia() {
		return grauImportancia;
	}
	public void setGrauImportancia(String grauImportancia) {
		this.grauImportancia = grauImportancia;
	}
	public String getIndicadorEncAutomaticoRAQndEncOS() {
		return indicadorEncAutomaticoRAQndEncOS;
	}
	public void setIndicadorEncAutomaticoRAQndEncOS(
			String indicadorEncAutomaticoRAQndEncOS) {
		this.indicadorEncAutomaticoRAQndEncOS = indicadorEncAutomaticoRAQndEncOS;
	}
	
}
