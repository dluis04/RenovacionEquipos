package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the MODELO_COMPUTO database table.
 * 
 */
@Entity
@Table(name="MODELO_COMPUTO")
@NamedQuery(name="ModeloComputo.findAll", query="SELECT m FROM ModeloComputo m")
public class ModeloComputo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_MODELO")
	private int idModelo;

	@Column(name="FABRICANTE")
	private String fabricante;

	@Column(name="ID_ESTADO")
	private int idEstado;

	@Column(name="MODELO")
	private String modelo;

	@Column(name="TIPO")
	private String tipo;

	//bi-directional many-to-one association to Computador
	@OneToMany(mappedBy="modeloComputo")
	private List<Computador> computadors;

	public ModeloComputo() {
	}

	public int getIdModelo() {
		return this.idModelo;
	}

	public void setIdModelo(int idModelo) {
		this.idModelo = idModelo;
	}

	public String getFabricante() {
		return this.fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Computador> getComputadors() {
		return this.computadors;
	}

	public void setComputadors(List<Computador> computadors) {
		this.computadors = computadors;
	}

	public Computador addComputador(Computador computador) {
		getComputadors().add(computador);
		computador.setModeloComputo(this);

		return computador;
	}

	public Computador removeComputador(Computador computador) {
		getComputadors().remove(computador);
		computador.setModeloComputo(null);

		return computador;
	}

}