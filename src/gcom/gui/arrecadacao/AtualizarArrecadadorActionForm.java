package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC005] Manter Municipio [SB0001]Atualizar Municipio
 * 
 * @author Kássia Albuquerque
 * @date 08/01/2007
 */

public class AtualizarArrecadadorActionForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;

	private String id;

	private String idAgente;

	private String idCliente;

	private String nomeCliente;

	private String idImovel;

	private String inscricaoImovel;

	private String inscricaoEstadual;
	
	private String indicadorUso;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdAgente() {
		return idAgente;
	}

	public void setIdAgente(String idAgente) {
		this.idAgente = idAgente;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	// Esse método carrega todos os valores da base de dados
	// necesários para exibição da tela AtualizarArrecadador.
	public Arrecadador setFormValues(Arrecadador arrecadador) {
		Fachada fachada = Fachada.getInstancia();
		
		// Seta id do Arrecadador
		arrecadador.setId(new Integer(getId()));

		// Seta Objeto Cliente para Arrecadador
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, getIdCliente()));
		Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());		
		Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
		arrecadador.setCliente(cliente);
		if(getIdImovel() != null && !getIdImovel().equals("")){
			// Seta Objeto Imovel para Arrecadador
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, getIdImovel()));
			Collection colecaoImovel = fachada.pesquisar(filtroImovel,Imovel.class.getName());
			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
			arrecadador.setImovel(imovel);
		}
		
		// Seta Inscricao Estadual
		arrecadador.setNumeroInscricaoEstadual(getInscricaoEstadual());
		return arrecadador;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

}
