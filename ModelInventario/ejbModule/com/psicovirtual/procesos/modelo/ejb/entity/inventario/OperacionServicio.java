package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the OPERACION_SERVICIO database table.
 * 
 */
@Entity
@Table(name="OPERACION_SERVICIO")
@NamedQuery(name="OperacionServicio.findAll", query="SELECT o FROM OperacionServicio o")
public class OperacionServicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_SERVICIO")
	private int idServicio;

	@Column(name="BANCO_PROPIO")
	private String bancoPropio;

	@Column(name="CODIGO_SERVICIO")
	private int codigoServicio;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_FIN")
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_INICIO")
	private Date fechaInicio;

	@Column(name="ID_ESTADO")
	private int idEstado;

	@Column(name="VALOR_MENSUAL_COMPU")
	private String valorMensualCompu;

	@Column(name="VALOR_MENSUAL_OPERACION")
	private String valorMensualOperacion;

	//bi-directional many-to-one association to Computador
	@OneToMany(mappedBy="operacionServicio")
	private List<Computador> computadors;

	public OperacionServicio() {
	}

	public int getIdServicio() {
		return this.idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public String getBancoPropio() {
		return this.bancoPropio;
	}

	public void setBancoPropio(String bancoPropio) {
		this.bancoPropio = bancoPropio;
	}

	public int getCodigoServicio() {
		return this.codigoServicio;
	}

	public void setCodigoServicio(int codigoServicio) {
		this.codigoServicio = codigoServicio;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getValorMensualCompu() {
		return this.valorMensualCompu;
	}

	public void setValorMensualCompu(String valorMensualCompu) {
		this.valorMensualCompu = valorMensualCompu;
	}

	public String getValorMensualOperacion() {
		return this.valorMensualOperacion;
	}

	public void setValorMensualOperacion(String valorMensualOperacion) {
		this.valorMensualOperacion = valorMensualOperacion;
	}

	public List<Computador> getComputadors() {
		return this.computadors;
	}

	public void setComputadors(List<Computador> computadors) {
		this.computadors = computadors;
	}

	public Computador addComputador(Computador computador) {
		getComputadors().add(computador);
		computador.setOperacionServicio(this);

		return computador;
	}

	public Computador removeComputador(Computador computador) {
		getComputadors().remove(computador);
		computador.setOperacionServicio(null);

		return computador;
	}

}