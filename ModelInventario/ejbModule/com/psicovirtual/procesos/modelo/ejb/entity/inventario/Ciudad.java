package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CIUDAD database table.
 * 
 */
@Entity
@Table(name="CIUDAD")
@NamedQuery(name="Ciudad.findAll", query="SELECT c FROM Ciudad c")
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_CIUDAD")
	private int idCiudad;

	@Column(name="ID_ESTADO")
	private int idEstado;

	@Column(name="NOMBRE")
	private String nombre;

	//bi-directional many-to-one association to UnidadEstrategicaServicio
	@OneToMany(mappedBy="ciudad")
	private List<UnidadEstrategicaServicio> unidadEstrategicaServicios;

	public Ciudad() {
	}

	public int getIdCiudad() {
		return this.idCiudad;
	}

	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
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

	public List<UnidadEstrategicaServicio> getUnidadEstrategicaServicios() {
		return this.unidadEstrategicaServicios;
	}

	public void setUnidadEstrategicaServicios(List<UnidadEstrategicaServicio> unidadEstrategicaServicios) {
		this.unidadEstrategicaServicios = unidadEstrategicaServicios;
	}

	public UnidadEstrategicaServicio addUnidadEstrategicaServicio(UnidadEstrategicaServicio unidadEstrategicaServicio) {
		getUnidadEstrategicaServicios().add(unidadEstrategicaServicio);
		unidadEstrategicaServicio.setCiudad(this);

		return unidadEstrategicaServicio;
	}

	public UnidadEstrategicaServicio removeUnidadEstrategicaServicio(UnidadEstrategicaServicio unidadEstrategicaServicio) {
		getUnidadEstrategicaServicios().remove(unidadEstrategicaServicio);
		unidadEstrategicaServicio.setCiudad(null);

		return unidadEstrategicaServicio;
	}

}