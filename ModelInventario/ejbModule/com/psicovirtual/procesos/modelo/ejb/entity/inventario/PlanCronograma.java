package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PLAN_CRONOGRAMA database table.
 * 
 */
@Entity
@Table(name="PLAN_CRONOGRAMA")
@NamedQuery(name="PlanCronograma.findAll", query="SELECT p FROM PlanCronograma p")
public class PlanCronograma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PLAN")
	private int idPlan;

	@Column(name="CANTIDAD_PLANEADO")
	private String cantidadPlaneado;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_FIN_SEMANA")
	private Date fechaFinSemana;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_INI_SEMANA")
	private Date fechaIniSemana;

	@Column(name="ID_ESTADO")
	private int idEstado;

	@Column(name="ID_USUARIO_REG")
	private String idUsuarioReg;

	//bi-directional many-to-one association to EjecucionCronograma
	@OneToMany(mappedBy="planCronograma")
	private List<EjecucionCronograma> ejecucionCronogramas;

	//bi-directional many-to-one association to Cronograma
	@ManyToOne
	@JoinColumn(name="ID_CRONOGRAMA")
	private Cronograma cronograma;

	public PlanCronograma() {
	}

	public int getIdPlan() {
		return this.idPlan;
	}

	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}

	public String getCantidadPlaneado() {
		return this.cantidadPlaneado;
	}

	public void setCantidadPlaneado(String cantidadPlaneado) {
		this.cantidadPlaneado = cantidadPlaneado;
	}

	public Date getFechaFinSemana() {
		return this.fechaFinSemana;
	}

	public void setFechaFinSemana(Date fechaFinSemana) {
		this.fechaFinSemana = fechaFinSemana;
	}

	public Date getFechaIniSemana() {
		return this.fechaIniSemana;
	}

	public void setFechaIniSemana(Date fechaIniSemana) {
		this.fechaIniSemana = fechaIniSemana;
	}

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getIdUsuarioReg() {
		return this.idUsuarioReg;
	}

	public void setIdUsuarioReg(String idUsuarioReg) {
		this.idUsuarioReg = idUsuarioReg;
	}

	public List<EjecucionCronograma> getEjecucionCronogramas() {
		return this.ejecucionCronogramas;
	}

	public void setEjecucionCronogramas(List<EjecucionCronograma> ejecucionCronogramas) {
		this.ejecucionCronogramas = ejecucionCronogramas;
	}

	public EjecucionCronograma addEjecucionCronograma(EjecucionCronograma ejecucionCronograma) {
		getEjecucionCronogramas().add(ejecucionCronograma);
		ejecucionCronograma.setPlanCronograma(this);

		return ejecucionCronograma;
	}

	public EjecucionCronograma removeEjecucionCronograma(EjecucionCronograma ejecucionCronograma) {
		getEjecucionCronogramas().remove(ejecucionCronograma);
		ejecucionCronograma.setPlanCronograma(null);

		return ejecucionCronograma;
	}

	public Cronograma getCronograma() {
		return this.cronograma;
	}

	public void setCronograma(Cronograma cronograma) {
		this.cronograma = cronograma;
	}

}