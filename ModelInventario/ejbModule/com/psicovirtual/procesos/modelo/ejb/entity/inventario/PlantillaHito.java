package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the PLANTILLA_HITO database table.
 * 
 */
@Entity
@Table(name="PLANTILLA_HITO")
@NamedQuery(name="PlantillaHito.findAll", query="SELECT p FROM PlantillaHito p")
public class PlantillaHito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PLANTI_HITO")
	private int idPlantiHito;

	@Column(name="FECHA_MOD")
	private Timestamp fechaMod;

	@Column(name="HITO")
	private String hito;

	@Column(name="ID_ESTADO")
	private int idEstado;

	@Column(name="ID_USU_MOD")
	private int idUsuMod;

	@Column(name="ID_USU_REG")
	private int idUsuReg;

	@Column(name="ORDEN")
	private int orden;

	//bi-directional many-to-one association to PlantillaPlan
	@ManyToOne
	@JoinColumn(name="ID_PLANTILLA_PLAN")
	private PlantillaPlan plantillaPlan;

	public PlantillaHito() {
	}

	public int getIdPlantiHito() {
		return this.idPlantiHito;
	}

	public void setIdPlantiHito(int idPlantiHito) {
		this.idPlantiHito = idPlantiHito;
	}

	public Timestamp getFechaMod() {
		return this.fechaMod;
	}

	public void setFechaMod(Timestamp fechaMod) {
		this.fechaMod = fechaMod;
	}

	public String getHito() {
		return this.hito;
	}

	public void setHito(String hito) {
		this.hito = hito;
	}

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public int getIdUsuMod() {
		return this.idUsuMod;
	}

	public void setIdUsuMod(int idUsuMod) {
		this.idUsuMod = idUsuMod;
	}

	public int getIdUsuReg() {
		return this.idUsuReg;
	}

	public void setIdUsuReg(int idUsuReg) {
		this.idUsuReg = idUsuReg;
	}

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public PlantillaPlan getPlantillaPlan() {
		return this.plantillaPlan;
	}

	public void setPlantillaPlan(PlantillaPlan plantillaPlan) {
		this.plantillaPlan = plantillaPlan;
	}

}