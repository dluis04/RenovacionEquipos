package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USUARIOS_CRONOGRAMA database table.
 * 
 */
@Entity
@Table(name="USUARIOS_CRONOGRAMA")
@NamedQuery(name="UsuariosCronograma.findAll", query="SELECT u FROM UsuariosCronograma u")
public class UsuariosCronograma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_USUARIO_CRONO")
	private int idUsuarioCrono;

	@Column(name="ID_ESTADO")
	private int idEstado;

	//bi-directional many-to-one association to Cronograma
	@ManyToOne
	@JoinColumn(name="ID_CRONOGRAMA")
	private Cronograma cronograma;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;

	public UsuariosCronograma() {
	}

	public int getIdUsuarioCrono() {
		return this.idUsuarioCrono;
	}

	public void setIdUsuarioCrono(int idUsuarioCrono) {
		this.idUsuarioCrono = idUsuarioCrono;
	}

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public Cronograma getCronograma() {
		return this.cronograma;
	}

	public void setCronograma(Cronograma cronograma) {
		this.cronograma = cronograma;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}