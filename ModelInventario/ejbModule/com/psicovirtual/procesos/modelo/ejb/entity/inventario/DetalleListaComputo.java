package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the DETALLE_LISTA_COMPUTO database table.
 * 
 */
@Entity
@Table(name = "DETALLE_LISTA_COMPUTO")
@NamedQuery(name = "DetalleListaComputo.findAll", query = "SELECT d FROM DetalleListaComputo d")
public class DetalleListaComputo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_DETA_LIST_COMPU")
	private int idDetaListCompu;

	@Column(name = "ACTIVIDAD")
	private String actividad;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	@Column(name = "CHECK_LIST")
	private int checkList;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_CHECK")
	private Date fechaCheck;

	// bi-directional many-to-one association to Computador
	@ManyToOne
	@JoinColumn(name = "ID_COMPUTADOR")
	private Computador computador;

	// bi-directional many-to-one association to ListaCheqeoComputador
	@ManyToOne
	@JoinColumn(name = "ID_LISTA")
	private ListaCheqeoComputador listaCheqeoComputador;

	public DetalleListaComputo() {
	}

	public int getIdDetaListCompu() {
		return this.idDetaListCompu;
	}

	public void setIdDetaListCompu(int idDetaListCompu) {
		this.idDetaListCompu = idDetaListCompu;
	}

	public String getActividad() {
		return this.actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getCheckList() {
		return this.checkList;
	}

	public void setCheckList(int checkList) {
		this.checkList = checkList;
	}

	public Date getFechaCheck() {
		return this.fechaCheck;
	}

	public void setFechaCheck(Date fechaCheck) {
		this.fechaCheck = fechaCheck;
	}

	public Computador getComputador() {
		return this.computador;
	}

	public void setComputador(Computador computador) {
		this.computador = computador;
	}

	public ListaCheqeoComputador getListaCheqeoComputador() {
		return this.listaCheqeoComputador;
	}

	public void setListaCheqeoComputador(ListaCheqeoComputador listaCheqeoComputador) {
		this.listaCheqeoComputador = listaCheqeoComputador;
	}

}