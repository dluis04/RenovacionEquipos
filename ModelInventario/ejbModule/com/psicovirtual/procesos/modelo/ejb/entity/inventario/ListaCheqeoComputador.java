package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the LISTA_CHEQEO_COMPUTADOR database table.
 * 
 */
@Entity
@Table(name="LISTA_CHEQEO_COMPUTADOR")
@NamedQuery(name="ListaCheqeoComputador.findAll", query="SELECT l FROM ListaCheqeoComputador l")
public class ListaCheqeoComputador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_LISTA")
	private int idLista;

	@Column(name="ITEM_LISTA")
	private String itemLista;

	@Column(name="ORDEN")
	private int orden;

	//bi-directional many-to-one association to DetalleListaComputo
	@OneToMany(mappedBy="listaCheqeoComputador")
	private List<DetalleListaComputo> detalleListaComputos;

	public ListaCheqeoComputador() {
	}

	public int getIdLista() {
		return this.idLista;
	}

	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}

	public String getItemLista() {
		return this.itemLista;
	}

	public void setItemLista(String itemLista) {
		this.itemLista = itemLista;
	}

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public List<DetalleListaComputo> getDetalleListaComputos() {
		return this.detalleListaComputos;
	}

	public void setDetalleListaComputos(List<DetalleListaComputo> detalleListaComputos) {
		this.detalleListaComputos = detalleListaComputos;
	}

	public DetalleListaComputo addDetalleListaComputo(DetalleListaComputo detalleListaComputo) {
		getDetalleListaComputos().add(detalleListaComputo);
		detalleListaComputo.setListaCheqeoComputador(this);

		return detalleListaComputo;
	}

	public DetalleListaComputo removeDetalleListaComputo(DetalleListaComputo detalleListaComputo) {
		getDetalleListaComputos().remove(detalleListaComputo);
		detalleListaComputo.setListaCheqeoComputador(null);

		return detalleListaComputo;
	}

}