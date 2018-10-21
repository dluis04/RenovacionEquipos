package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the CRONOGRAMA database table.
 * 
 */
@Entity
@Table(name="CRONOGRAMA")
@NamedQuery(name="Cronograma.findAll", query="SELECT c FROM Cronograma c")
public class Cronograma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_CRONOGRAMA")
	private int idCronograma;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_FIN")
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_INI")
	private Date fechaIni;

	@Column(name="ID_ESTADO")
	private int idEstado;

	@Column(name="ID_USUARIO_REG")
	private int idUsuarioReg;

	@Column(name="NOMBRE")
	private String nombre;

	//bi-directional many-to-one association to PlantillaPlan
	@OneToMany(mappedBy="cronograma")
	private List<PlantillaPlan> plantillaPlans;

	//bi-directional many-to-one association to PlanCronograma
	@OneToMany(mappedBy="cronograma")
	private List<PlanCronograma> planCronogramas;

	//bi-directional many-to-one association to UsuariosCronograma
	@OneToMany(mappedBy="cronograma")
	private List<UsuariosCronograma> usuariosCronogramas;

	public Cronograma() {
	}

	public int getIdCronograma() {
		return this.idCronograma;
	}

	public void setIdCronograma(int idCronograma) {
		this.idCronograma = idCronograma;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaIni() {
		return this.fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public int getIdUsuarioReg() {
		return this.idUsuarioReg;
	}

	public void setIdUsuarioReg(int idUsuarioReg) {
		this.idUsuarioReg = idUsuarioReg;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PlantillaPlan> getPlantillaPlans() {
		return this.plantillaPlans;
	}

	public void setPlantillaPlans(List<PlantillaPlan> plantillaPlans) {
		this.plantillaPlans = plantillaPlans;
	}

	public PlantillaPlan addPlantillaPlan(PlantillaPlan plantillaPlan) {
		getPlantillaPlans().add(plantillaPlan);
		plantillaPlan.setCronograma(this);

		return plantillaPlan;
	}

	public PlantillaPlan removePlantillaPlan(PlantillaPlan plantillaPlan) {
		getPlantillaPlans().remove(plantillaPlan);
		plantillaPlan.setCronograma(null);

		return plantillaPlan;
	}

	public List<PlanCronograma> getPlanCronogramas() {
		return this.planCronogramas;
	}

	public void setPlanCronogramas(List<PlanCronograma> planCronogramas) {
		this.planCronogramas = planCronogramas;
	}

	public PlanCronograma addPlanCronograma(PlanCronograma planCronograma) {
		getPlanCronogramas().add(planCronograma);
		planCronograma.setCronograma(this);

		return planCronograma;
	}

	public PlanCronograma removePlanCronograma(PlanCronograma planCronograma) {
		getPlanCronogramas().remove(planCronograma);
		planCronograma.setCronograma(null);

		return planCronograma;
	}

	public List<UsuariosCronograma> getUsuariosCronogramas() {
		return this.usuariosCronogramas;
	}

	public void setUsuariosCronogramas(List<UsuariosCronograma> usuariosCronogramas) {
		this.usuariosCronogramas = usuariosCronogramas;
	}

	public UsuariosCronograma addUsuariosCronograma(UsuariosCronograma usuariosCronograma) {
		getUsuariosCronogramas().add(usuariosCronograma);
		usuariosCronograma.setCronograma(this);

		return usuariosCronograma;
	}

	public UsuariosCronograma removeUsuariosCronograma(UsuariosCronograma usuariosCronograma) {
		getUsuariosCronogramas().remove(usuariosCronograma);
		usuariosCronograma.setCronograma(null);

		return usuariosCronograma;
	}

}