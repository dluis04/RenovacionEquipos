package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the DETALLE_INVENTARIO database table.
 * 
 */
@Entity
@Table(name="DETALLE_INVENTARIO")
@NamedQuery(name="DetalleInventario.findAll", query="SELECT d FROM DetalleInventario d")
public class DetalleInventario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_INVENTARIO")
	private int idInventario;

	@Column(name="CAUSA_TECNICA")
	private String causaTecnica;

	@Column(name="FECHA_MOD")
	private Timestamp fechaMod;

	@Column(name="FECHA_REG")
	private Timestamp fechaReg;

	@Column(name="ID_ESTADO")
	private int idEstado;

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

	//bi-directional many-to-one association to Computador
	@ManyToOne
	@JoinColumn(name="ID_COMPUTADOR")
	private Computador computador;

	//bi-directional many-to-one association to DetalleInventario
	@ManyToOne
	@JoinColumn(name="ID_INVENTARIO_RENO")
	private DetalleInventario detalleInventario;

	//bi-directional many-to-one association to DetalleInventario
	@OneToMany(mappedBy="detalleInventario")
	private List<DetalleInventario> detalleInventarios;

	//bi-directional many-to-one association to Ubicacion
	@ManyToOne
	@JoinColumn(name="ID_UBICACION")
	private Ubicacion ubicacion;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;

	//bi-directional many-to-one association to EjecucionCronograma
	@OneToMany(mappedBy="detalleInventario")
	private List<EjecucionCronograma> ejecucionCronogramas;

	public DetalleInventario() {
	}

	public int getIdInventario() {
		return this.idInventario;
	}

	public void setIdInventario(int idInventario) {
		this.idInventario = idInventario;
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

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
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

	public Computador getComputador() {
		return this.computador;
	}

	public void setComputador(Computador computador) {
		this.computador = computador;
	}

	public DetalleInventario getDetalleInventario() {
		return this.detalleInventario;
	}

	public void setDetalleInventario(DetalleInventario detalleInventario) {
		this.detalleInventario = detalleInventario;
	}

	public List<DetalleInventario> getDetalleInventarios() {
		return this.detalleInventarios;
	}

	public void setDetalleInventarios(List<DetalleInventario> detalleInventarios) {
		this.detalleInventarios = detalleInventarios;
	}

	public DetalleInventario addDetalleInventario(DetalleInventario detalleInventario) {
		getDetalleInventarios().add(detalleInventario);
		detalleInventario.setDetalleInventario(this);

		return detalleInventario;
	}

	public DetalleInventario removeDetalleInventario(DetalleInventario detalleInventario) {
		getDetalleInventarios().remove(detalleInventario);
		detalleInventario.setDetalleInventario(null);

		return detalleInventario;
	}

	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<EjecucionCronograma> getEjecucionCronogramas() {
		return this.ejecucionCronogramas;
	}

	public void setEjecucionCronogramas(List<EjecucionCronograma> ejecucionCronogramas) {
		this.ejecucionCronogramas = ejecucionCronogramas;
	}

	public EjecucionCronograma addEjecucionCronograma(EjecucionCronograma ejecucionCronograma) {
		getEjecucionCronogramas().add(ejecucionCronograma);
		ejecucionCronograma.setDetalleInventario(this);

		return ejecucionCronograma;
	}

	public EjecucionCronograma removeEjecucionCronograma(EjecucionCronograma ejecucionCronograma) {
		getEjecucionCronogramas().remove(ejecucionCronograma);
		ejecucionCronograma.setDetalleInventario(null);

		return ejecucionCronograma;
	}

}