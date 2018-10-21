package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the EJECUCION__CRONOGRAMA database table.
 * 
 */
@Entity
@Table(name="EJECUCION__CRONOGRAMA")
@NamedQuery(name="EjecucionCronograma.findAll", query="SELECT e FROM EjecucionCronograma e")
public class EjecucionCronograma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_EJECUCION")
	private int idEjecucion;

	@Column(name="FECHA_REALIZA")
	private Timestamp fechaRealiza;

	@Column(name="ID_ESTADO")
	private int idEstado;

	@Column(name="ID_USUARIO_REG")
	private int idUsuarioReg;

	@Column(name="OBSERVACIONES")
	private String observaciones;

	//bi-directional many-to-one association to DetalleInventario
	@ManyToOne
	@JoinColumn(name="ID_INVENTARIO")
	private DetalleInventario detalleInventario;

	//bi-directional many-to-one association to PlanCronograma
	@ManyToOne
	@JoinColumn(name="ID_PLAN")
	private PlanCronograma planCronograma;

	public EjecucionCronograma() {
	}

	public int getIdEjecucion() {
		return this.idEjecucion;
	}

	public void setIdEjecucion(int idEjecucion) {
		this.idEjecucion = idEjecucion;
	}

	public Timestamp getFechaRealiza() {
		return this.fechaRealiza;
	}

	public void setFechaRealiza(Timestamp fechaRealiza) {
		this.fechaRealiza = fechaRealiza;
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

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public DetalleInventario getDetalleInventario() {
		return this.detalleInventario;
	}

	public void setDetalleInventario(DetalleInventario detalleInventario) {
		this.detalleInventario = detalleInventario;
	}

	public PlanCronograma getPlanCronograma() {
		return this.planCronograma;
	}

	public void setPlanCronograma(PlanCronograma planCronograma) {
		this.planCronograma = planCronograma;
	}

}