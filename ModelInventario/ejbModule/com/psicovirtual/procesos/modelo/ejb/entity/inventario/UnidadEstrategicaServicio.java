package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the UNIDAD_ESTRATEGICA_SERVICIO database table.
 * 
 */
@Entity
@Table(name="UNIDAD_ESTRATEGICA_SERVICIO")
@NamedQuery(name="UnidadEstrategicaServicio.findAll", query="SELECT u FROM UnidadEstrategicaServicio u")
public class UnidadEstrategicaServicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_UNIDAD")
	private int idUnidad;

	@Column(name="ID_ESTADO")
	private int idEstado;

	@Column(name="NOMBRE")
	private String nombre;

	//bi-directional many-to-one association to Sede
	@OneToMany(mappedBy="unidadEstrategicaServicio")
	private List<Sede> sedes;

	//bi-directional many-to-one association to Ciudad
	@ManyToOne
	@JoinColumn(name="ID_CIUDAD")
	private Ciudad ciudad;

	public UnidadEstrategicaServicio() {
	}

	public int getIdUnidad() {
		return this.idUnidad;
	}

	public void setIdUnidad(int idUnidad) {
		this.idUnidad = idUnidad;
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

	public List<Sede> getSedes() {
		return this.sedes;
	}

	public void setSedes(List<Sede> sedes) {
		this.sedes = sedes;
	}

	public Sede addSede(Sede sede) {
		getSedes().add(sede);
		sede.setUnidadEstrategicaServicio(this);

		return sede;
	}

	public Sede removeSede(Sede sede) {
		getSedes().remove(sede);
		sede.setUnidadEstrategicaServicio(null);

		return sede;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

}