package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the COMPUTADOR database table.
 * 
 */
@Entity
@Table(name="COMPUTADOR")
@NamedQuery(name="Computador.findAll", query="SELECT c FROM Computador c")
public class Computador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_COMPUTADOR")
	private int idComputador;

	@Column(name="DIRECCION_IP")
	private String direccionIp;

	@Column(name="FECHA_MOD")
	private Timestamp fechaMod;

	@Column(name="FECHA_REG")
	private Timestamp fechaReg;

	@Column(name="ID_ESTADO")
	private int idEstado;

	@Column(name="ID_ESTADO_COMPU")
	private int idEstadoCompu;

	@Column(name="ID_USUARIO_MOD")
	private int idUsuarioMod;

	@Column(name="ID_USUARIO_REG")
	private int idUsuarioReg;

	@Column(name="MAC")
	private String mac;

	@Column(name="NOMBRE_COMPUTO")
	private String nombreComputo;

	@Column(name="SERIAL__MONITOR")
	private String serialMonitor;

	@Column(name="SERIAL_MOUSE")
	private String serialMouse;

	@Column(name="SERIAL_TECLADO")
	private String serialTeclado;

	//bi-directional many-to-one association to CaracteristicasComputo
	@ManyToOne
	@JoinColumn(name="ID_CARACTERISTICAS")
	private CaracteristicasComputo caracteristicasComputo;

	//bi-directional many-to-one association to ModeloComputo
	@ManyToOne
	@JoinColumn(name="ID_MODELO")
	private ModeloComputo modeloComputo;

	//bi-directional many-to-one association to OperacionServicio
	@ManyToOne
	@JoinColumn(name="ID_SERVICIO")
	private OperacionServicio operacionServicio;

	//bi-directional many-to-one association to SistemaOperativo
	@ManyToOne
	@JoinColumn(name="ID_SISTEMA")
	private SistemaOperativo sistemaOperativo;

	//bi-directional many-to-one association to TipoComputo
	@ManyToOne
	@JoinColumn(name="ID_TIPO")
	private TipoComputo tipoComputo;

	//bi-directional many-to-one association to UnidadEstrategicaServicio
	@ManyToOne
	@JoinColumn(name="ID_UNIDAD")
	private UnidadEstrategicaServicio unidadEstrategicaServicio;

	//bi-directional many-to-one association to DetalleInventario
	@OneToMany(mappedBy="computador")
	private List<DetalleInventario> detalleInventarios;

	//bi-directional many-to-one association to DetalleListaComputo
	@OneToMany(mappedBy="computador")
	private List<DetalleListaComputo> detalleListaComputos;

	public Computador() {
	}

	public int getIdComputador() {
		return this.idComputador;
	}

	public void setIdComputador(int idComputador) {
		this.idComputador = idComputador;
	}

	public String getDireccionIp() {
		return this.direccionIp;
	}

	public void setDireccionIp(String direccionIp) {
		this.direccionIp = direccionIp;
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

	public int getIdEstadoCompu() {
		return this.idEstadoCompu;
	}

	public void setIdEstadoCompu(int idEstadoCompu) {
		this.idEstadoCompu = idEstadoCompu;
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

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getNombreComputo() {
		return this.nombreComputo;
	}

	public void setNombreComputo(String nombreComputo) {
		this.nombreComputo = nombreComputo;
	}

	public String getSerialMonitor() {
		return this.serialMonitor;
	}

	public void setSerialMonitor(String serialMonitor) {
		this.serialMonitor = serialMonitor;
	}

	public String getSerialMouse() {
		return this.serialMouse;
	}

	public void setSerialMouse(String serialMouse) {
		this.serialMouse = serialMouse;
	}

	public String getSerialTeclado() {
		return this.serialTeclado;
	}

	public void setSerialTeclado(String serialTeclado) {
		this.serialTeclado = serialTeclado;
	}

	public CaracteristicasComputo getCaracteristicasComputo() {
		return this.caracteristicasComputo;
	}

	public void setCaracteristicasComputo(CaracteristicasComputo caracteristicasComputo) {
		this.caracteristicasComputo = caracteristicasComputo;
	}

	public ModeloComputo getModeloComputo() {
		return this.modeloComputo;
	}

	public void setModeloComputo(ModeloComputo modeloComputo) {
		this.modeloComputo = modeloComputo;
	}

	public OperacionServicio getOperacionServicio() {
		return this.operacionServicio;
	}

	public void setOperacionServicio(OperacionServicio operacionServicio) {
		this.operacionServicio = operacionServicio;
	}

	public SistemaOperativo getSistemaOperativo() {
		return this.sistemaOperativo;
	}

	public void setSistemaOperativo(SistemaOperativo sistemaOperativo) {
		this.sistemaOperativo = sistemaOperativo;
	}

	public TipoComputo getTipoComputo() {
		return this.tipoComputo;
	}

	public void setTipoComputo(TipoComputo tipoComputo) {
		this.tipoComputo = tipoComputo;
	}

	public UnidadEstrategicaServicio getUnidadEstrategicaServicio() {
		return this.unidadEstrategicaServicio;
	}

	public void setUnidadEstrategicaServicio(UnidadEstrategicaServicio unidadEstrategicaServicio) {
		this.unidadEstrategicaServicio = unidadEstrategicaServicio;
	}

	public List<DetalleInventario> getDetalleInventarios() {
		return this.detalleInventarios;
	}

	public void setDetalleInventarios(List<DetalleInventario> detalleInventarios) {
		this.detalleInventarios = detalleInventarios;
	}

	public DetalleInventario addDetalleInventario(DetalleInventario detalleInventario) {
		getDetalleInventarios().add(detalleInventario);
		detalleInventario.setComputador(this);

		return detalleInventario;
	}

	public DetalleInventario removeDetalleInventario(DetalleInventario detalleInventario) {
		getDetalleInventarios().remove(detalleInventario);
		detalleInventario.setComputador(null);

		return detalleInventario;
	}

	public List<DetalleListaComputo> getDetalleListaComputos() {
		return this.detalleListaComputos;
	}

	public void setDetalleListaComputos(List<DetalleListaComputo> detalleListaComputos) {
		this.detalleListaComputos = detalleListaComputos;
	}

	public DetalleListaComputo addDetalleListaComputo(DetalleListaComputo detalleListaComputo) {
		getDetalleListaComputos().add(detalleListaComputo);
		detalleListaComputo.setComputador(this);

		return detalleListaComputo;
	}

	public DetalleListaComputo removeDetalleListaComputo(DetalleListaComputo detalleListaComputo) {
		getDetalleListaComputos().remove(detalleListaComputo);
		detalleListaComputo.setComputador(null);

		return detalleListaComputo;
	}

}