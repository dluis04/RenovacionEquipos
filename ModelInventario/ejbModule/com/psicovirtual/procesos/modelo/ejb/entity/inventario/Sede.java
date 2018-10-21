package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SEDE database table.
 * 
 */
@Entity
@Table(name="SEDE")
@NamedQuery(name="Sede.findAll", query="SELECT s FROM Sede s")
public class Sede implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_SEDE")
	private int idSede;

	@Column(name="ID_ESTADO")
	private int idEstado;

	@Column(name="NOMBRE")
	private String nombre;

	//bi-directional many-to-one association to UnidadEstrategicaServicio
	@ManyToOne
	@JoinColumn(name="ID_UNIDAD")
	private UnidadEstrategicaServicio unidadEstrategicaServicio;

	//bi-directional many-to-one association to Ubicacion
	@OneToMany(mappedBy="sede")
	private List<Ubicacion> ubicacions;

	public Sede() {
	}

	public int getIdSede() {
		return this.idSede;
	}

	public void setIdSede(int idSede) {
		this.idSede = idSede;
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

	public UnidadEstrategicaServicio getUnidadEstrategicaServicio() {
		return this.unidadEstrategicaServicio;
	}

	public void setUnidadEstrategicaServicio(UnidadEstrategicaServicio unidadEstrategicaServicio) {
		this.unidadEstrategicaServicio = unidadEstrategicaServicio;
	}

	public List<Ubicacion> getUbicacions() {
		return this.ubicacions;
	}

	public void setUbicacions(List<Ubicacion> ubicacions) {
		this.ubicacions = ubicacions;
	}

	public Ubicacion addUbicacion(Ubicacion ubicacion) {
		getUbicacions().add(ubicacion);
		ubicacion.setSede(this);

		return ubicacion;
	}

	public Ubicacion removeUbicacion(Ubicacion ubicacion) {
		getUbicacions().remove(ubicacion);
		ubicacion.setSede(null);

		return ubicacion;
	}

}