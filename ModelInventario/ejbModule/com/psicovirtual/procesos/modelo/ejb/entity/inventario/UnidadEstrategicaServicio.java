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

	//bi-directional many-to-one association to Computador
	@OneToMany(mappedBy="unidadEstrategicaServicio")
	private List<Computador> computadors;

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

	public List<Computador> getComputadors() {
		return this.computadors;
	}

	public void setComputadors(List<Computador> computadors) {
		this.computadors = computadors;
	}

	public Computador addComputador(Computador computador) {
		getComputadors().add(computador);
		computador.setUnidadEstrategicaServicio(this);

		return computador;
	}

	public Computador removeComputador(Computador computador) {
		getComputadors().remove(computador);
		computador.setUnidadEstrategicaServicio(null);

		return computador;
	}

}