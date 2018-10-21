package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CARACTERISTICAS_COMPUTO database table.
 * 
 */
@Entity
@Table(name="CARACTERISTICAS_COMPUTO")
@NamedQuery(name="CaracteristicasComputo.findAll", query="SELECT c FROM CaracteristicasComputo c")
public class CaracteristicasComputo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_CARACTERISTICAS")
	private int idCaracteristicas;

	@Column(name="DISCO_DURO")
	private String discoDuro;

	@Column(name="ID_ESTADO")
	private int idEstado;

	@Column(name="MEMORIA")
	private String memoria;

	@Column(name="PROCESADOR")
	private String procesador;

	//bi-directional many-to-one association to Computador
	@OneToMany(mappedBy="caracteristicasComputo")
	private List<Computador> computadors;

	public CaracteristicasComputo() {
	}

	public int getIdCaracteristicas() {
		return this.idCaracteristicas;
	}

	public void setIdCaracteristicas(int idCaracteristicas) {
		this.idCaracteristicas = idCaracteristicas;
	}

	public String getDiscoDuro() {
		return this.discoDuro;
	}

	public void setDiscoDuro(String discoDuro) {
		this.discoDuro = discoDuro;
	}

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getMemoria() {
		return this.memoria;
	}

	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}

	public String getProcesador() {
		return this.procesador;
	}

	public void setProcesador(String procesador) {
		this.procesador = procesador;
	}

	public List<Computador> getComputadors() {
		return this.computadors;
	}

	public void setComputadors(List<Computador> computadors) {
		this.computadors = computadors;
	}

	public Computador addComputador(Computador computador) {
		getComputadors().add(computador);
		computador.setCaracteristicasComputo(this);

		return computador;
	}

	public Computador removeComputador(Computador computador) {
		getComputadors().remove(computador);
		computador.setCaracteristicasComputo(null);

		return computador;
	}

}