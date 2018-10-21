package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the PLANTILLA_PLAN database table.
 * 
 */
@Entity
@Table(name="PLANTILLA_PLAN")
@NamedQuery(name="PlantillaPlan.findAll", query="SELECT p FROM PlantillaPlan p")
public class PlantillaPlan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PLANTILLA_PLAN")
	private int idPlantillaPlan;

	@Column(name="FECHA_MOD")
	private Timestamp fechaMod;

	@Column(name="ID_ESTADO")
	private int idEstado;

	@Column(name="ID_USU_MOD")
	private int idUsuMod;

	@Column(name="ID_USU_REG")
	private int idUsuReg;

	@Column(name="ORDEN")
	private int orden;

	@Column(name="PLAN")
	private String plan;

	//bi-directional many-to-one association to PlantillaHito
	@OneToMany(mappedBy="plantillaPlan")
	private List<PlantillaHito> plantillaHitos;

	//bi-directional many-to-one association to Cronograma
	@ManyToOne
	@JoinColumn(name="ID_CRONOGRAMA")
	private Cronograma cronograma;

	public PlantillaPlan() {
	}

	public int getIdPlantillaPlan() {
		return this.idPlantillaPlan;
	}

	public void setIdPlantillaPlan(int idPlantillaPlan) {
		this.idPlantillaPlan = idPlantillaPlan;
	}

	public Timestamp getFechaMod() {
		return this.fechaMod;
	}

	public void setFechaMod(Timestamp fechaMod) {
		this.fechaMod = fechaMod;
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

	public String getPlan() {
		return this.plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public List<PlantillaHito> getPlantillaHitos() {
		return this.plantillaHitos;
	}

	public void setPlantillaHitos(List<PlantillaHito> plantillaHitos) {
		this.plantillaHitos = plantillaHitos;
	}

	public PlantillaHito addPlantillaHito(PlantillaHito plantillaHito) {
		getPlantillaHitos().add(plantillaHito);
		plantillaHito.setPlantillaPlan(this);

		return plantillaHito;
	}

	public PlantillaHito removePlantillaHito(PlantillaHito plantillaHito) {
		getPlantillaHitos().remove(plantillaHito);
		plantillaHito.setPlantillaPlan(null);

		return plantillaHito;
	}

	public Cronograma getCronograma() {
		return this.cronograma;
	}

	public void setCronograma(Cronograma cronograma) {
		this.cronograma = cronograma;
	}

}