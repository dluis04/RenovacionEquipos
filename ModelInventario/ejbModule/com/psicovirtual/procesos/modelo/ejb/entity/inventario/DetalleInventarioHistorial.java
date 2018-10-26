package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the DETALLE_INVENTARIO_HISTORIAL database table.
 * 
 */
@Entity
@Table(name="DETALLE_INVENTARIO_HISTORIAL")
@NamedQuery(name="DetalleInventarioHistorial.findAll", query="SELECT d FROM DetalleInventarioHistorial d")
public class DetalleInventarioHistorial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_INVENTARIO_HIST")
	private int idInventarioHist;

	@Column(name="CAUSA_TECNICA")
	private String causaTecnica;

	@Column(name="FECHA_MOD")
	private Timestamp fechaMod;

	@Column(name="FECHA_REG")
	private Timestamp fechaReg;

	@Column(name="ID_COMPUTADOR")
	private int idComputador;

	@Column(name="ID_ESTADO")
	private int idEstado;

	@Column(name="ID_INVENTARIO_RENO")
	private int idInventarioReno;

	@Column(name="ID_UBICACION")
	private int idUbicacion;

	@Column(name="ID_USUARIO")
	private int idUsuario;

	@Column(name="ID_USUARIO_MOD")
	private int idUsuarioMod;

	@Column(name="ID_USUARIO_REG")
	private int idUsuarioReg;

	@Column(name="OBSERVACION")
	private String observacion;

	@Column(name="PLACA_INVENTARIO")
	private String placaInventario;

	@Column(name="SEGUNDA_PLACA")
	private String segundaPlaca;

	@Column(name="SERIAL_INVENTARIO")
	private String serialInventario;

	public DetalleInventarioHistorial() {
	}

	public int getIdInventarioHist() {
		return this.idInventarioHist;
	}

	public void setIdInventarioHist(int idInventarioHist) {
		this.idInventarioHist = idInventarioHist;
	}

	public String getCausaTecnica() {
		return this.causaTecnica;
	}

	public void setCausaTecnica(String causaTecnica) {
		this.causaTecnica = causaTecnica;
	}

	public Timestamp getFechaMod() {
		return this.fechaMod;
	}

	public void setFechaMod(Timestamp fechaMod) {
		this.fechaMod = fechaMod;
	}

	public Timestamp getFechaReg() {
		return this.fechaReg;
	}

	public void setFechaReg(Timestamp fechaReg) {
		this.fechaReg = fechaReg;
	}

	public int getIdComputador() {
		return this.idComputador;
	}

	public void setIdComputador(int idComputador) {
		this.idComputador = idComputador;
	}

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public int getIdInventarioReno() {
		return this.idInventarioReno;
	}

	public void setIdInventarioReno(int idInventarioReno) {
		this.idInventarioReno = idInventarioReno;
	}

	public int getIdUbicacion() {
		return this.idUbicacion;
	}

	public void setIdUbicacion(int idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdUsuarioMod() {
		return this.idUsuarioMod;
	}

	public void setIdUsuarioMod(int idUsuarioMod) {
		this.idUsuarioMod = idUsuarioMod;
	}

	public int getIdUsuarioReg() {
		return this.idUsuarioReg;
	}

	public void setIdUsuarioReg(int idUsuarioReg) {
		this.idUsuarioReg = idUsuarioReg;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getPlacaInventario() {
		return this.placaInventario;
	}

	public void setPlacaInventario(String placaInventario) {
		this.placaInventario = placaInventario;
	}

	public String getSegundaPlaca() {
		return this.segundaPlaca;
	}

	public void setSegundaPlaca(String segundaPlaca) {
		this.segundaPlaca = segundaPlaca;
	}

	public String getSerialInventario() {
		return this.serialInventario;
	}

	public void setSerialInventario(String serialInventario) {
		this.serialInventario = serialInventario;
	}

}