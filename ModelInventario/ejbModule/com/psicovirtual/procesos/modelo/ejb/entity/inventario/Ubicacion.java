package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the UBICACION database table.
 * 
 */
@Entity
@Table(name="UBICACION")
@NamedQuery(name="Ubicacion.findAll", query="SELECT u FROM Ubicacion u")
public class Ubicacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_UBICACION")
	private int idUbicacion;

	@Column(name="ID_ESTADO")
	private int idEstado;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="PISO")
	private String piso;

	//bi-directional many-to-one association to DetalleInventario
	@OneToMany(mappedBy="ubicacion")
	private List<DetalleInventario> detalleInventarios;

	//bi-directional many-to-one association to Sede
	@ManyToOne
	@JoinColumn(name="ID_SEDE")
	private Sede sede;

	public Ubicacion() {
	}

	public int getIdUbicacion() {
		return this.idUbicacion;
	}

	public void setIdUbicacion(int idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPiso() {
		return this.piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public List<DetalleInventario> getDetalleInventarios() {
		return this.detalleInventarios;
	}

	public void setDetalleInventarios(List<DetalleInventario> detalleInventarios) {
		this.detalleInventarios = detalleInventarios;
	}

	public DetalleInventario addDetalleInventario(DetalleInventario detalleInventario) {
		getDetalleInventarios().add(detalleInventario);
		detalleInventario.setUbicacion(this);

		return detalleInventario;
	}

	public DetalleInventario removeDetalleInventario(DetalleInventario detalleInventario) {
		getDetalleInventarios().remove(detalleInventario);
		detalleInventario.setUbicacion(null);

		return detalleInventario;
	}

	public Sede getSede() {
		return this.sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

}