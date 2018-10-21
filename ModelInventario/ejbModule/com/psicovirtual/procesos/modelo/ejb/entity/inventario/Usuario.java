package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the USUARIOS database table.
 * 
 */
@Entity
@Table(name="USUARIOS")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_USUARIO")
	private int idUsuario;

	@Column(name="CEDULA")
	private String cedula;

	@Column(name="CORREO")
	private String correo;

	@Column(name="EXTENSION")
	private String extension;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="PASSWORD")
	private String password;

	@Column(name="TELEFONO")
	private String telefono;

	@Column(name="USUARIO")
	private String usuario;
	
	@Column(name="ID_ESTADO")
	private int idEstado;

	//bi-directional many-to-one association to DetalleInventario
	@OneToMany(mappedBy="usuario")
	private List<DetalleInventario> detalleInventarios;

	//bi-directional many-to-one association to TipoUsuario
	@ManyToOne
	@JoinColumn(name="ID_TIPO")
	private TipoUsuario tipoUsuario;

	//bi-directional many-to-one association to UsuariosCronograma
	@OneToMany(mappedBy="usuario")
	private List<UsuariosCronograma> usuariosCronogramas;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public List<DetalleInventario> getDetalleInventarios() {
		return this.detalleInventarios;
	}

	public void setDetalleInventarios(List<DetalleInventario> detalleInventarios) {
		this.detalleInventarios = detalleInventarios;
	}

	public DetalleInventario addDetalleInventario(DetalleInventario detalleInventario) {
		getDetalleInventarios().add(detalleInventario);
		detalleInventario.setUsuario(this);

		return detalleInventario;
	}

	public DetalleInventario removeDetalleInventario(DetalleInventario detalleInventario) {
		getDetalleInventarios().remove(detalleInventario);
		detalleInventario.setUsuario(null);

		return detalleInventario;
	}

	public TipoUsuario getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public List<UsuariosCronograma> getUsuariosCronogramas() {
		return this.usuariosCronogramas;
	}

	public void setUsuariosCronogramas(List<UsuariosCronograma> usuariosCronogramas) {
		this.usuariosCronogramas = usuariosCronogramas;
	}

	public UsuariosCronograma addUsuariosCronograma(UsuariosCronograma usuariosCronograma) {
		getUsuariosCronogramas().add(usuariosCronograma);
		usuariosCronograma.setUsuario(this);

		return usuariosCronograma;
	}

	public UsuariosCronograma removeUsuariosCronograma(UsuariosCronograma usuariosCronograma) {
		getUsuariosCronogramas().remove(usuariosCronograma);
		usuariosCronograma.setUsuario(null);

		return usuariosCronograma;
	}

}