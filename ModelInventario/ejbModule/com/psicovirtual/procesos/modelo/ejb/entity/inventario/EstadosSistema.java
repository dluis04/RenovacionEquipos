package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ESTADOS_SISTEMA database table.
 * 
 */
@Entity
@Table(name="ESTADOS_SISTEMA")
@NamedQuery(name="EstadosSistema.findAll", query="SELECT e FROM EstadosSistema e")
public class EstadosSistema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_ESTADO")
	private int idEstado;

	@Column(name="DESCRIPCION")
	private String descripcion;

	@Column(name="MODULO")
	private String modulo;

	public EstadosSistema() {
	}

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getModulo() {
		return this.modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

}