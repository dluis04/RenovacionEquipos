package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNComputador;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNDetalleInventario;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNDetalleInventarioHistorial;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNUsuario;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Computador;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.DetalleInventario;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Usuario;

@ManagedBean(name = "MBRenovacionEquiposNuevos")
@SessionScoped
public class MBRenovacionEquiposNuevos implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNComputador dnComputadors;
	DNDetalleInventario dNDetalleInventario;
	DNDetalleInventarioHistorial dNDetalleInventarioHistorial;
	DNUsuario dNUsuario;

	List<DetalleInventario> listComputadorBase;
	ArrayList<DetalleInventario> listComputadoresRenovados;
	List<Computador> listComputadorNuevos;
	List<Usuario> listUsuarioAdministrativos;

	private Usuario usuarioSeleccionado;
	private DetalleInventario computadorBaseSeleccionado;
	private DetalleInventario computadorRenovadoSeleccionado;
	private Computador computadorNuevoSeleccionado;
	private Computador computador;
	private Usuario usuario;

	public MBRenovacionEquiposNuevos() {

		computadorBaseSeleccionado = new DetalleInventario();
		computadorRenovadoSeleccionado = new DetalleInventario();
		computadorNuevoSeleccionado = new Computador();
		computador = new Computador();
		usuarioSeleccionado = new Usuario();
		listComputadoresRenovados = new ArrayList<DetalleInventario>();
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		cargarListaCargarTodo();
	}

	public void cargarListaCargarTodo() {
		try {

			inicializarDelegados();
			listComputadorBase = dNDetalleInventario.consultarAllDetalleInventarioComputadorBase();
			listComputadorNuevos = dnComputadors.consultarComputadoresNuevosParaRenovacion();
			listUsuarioAdministrativos = dNUsuario.consultarAllUsuariosActivosAdministrativos();

		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaCargarTodo -->> " + e);
		}
	}

	public void guardarCambios() {
		try {

			if (listComputadoresRenovados.size() > 0) {

				for (DetalleInventario list : listComputadoresRenovados) {
					dNDetalleInventario.actualizarDetalleInventario(list);
				}

				listComputadoresRenovados = new ArrayList<DetalleInventario>();
				cargarListaCargarTodo();

				mensajes.mostrarMensaje("Se guardo los cambios correctamente", 1);

			} else {
				mensajes.mostrarMensaje("No hay equipos para renovar", 2);
			}
		} catch (Exception e) {
			System.out.println("Error en el metodo guardarCambios -->> " + e);
		}

	}

	public void asginarRenovacion() {
		try {
			Boolean isRepetido = false;
			for (DetalleInventario list : listComputadoresRenovados) {
				if (list.getIdInventario() == computadorBaseSeleccionado.getIdInventario()) {
					mensajes.mostrarMensaje("No se puede asignar un mismo registro", 3);
					isRepetido = true;
				}
			}

			if (!isRepetido) {
				inicializarDelegados();
				DetalleInventario detalleNuevo = dNDetalleInventario
						.consultarComputadoresInventarioById("" + computadorNuevoSeleccionado.getIdComputador());

				computadorBaseSeleccionado.setIdEstado(2);
				computadorBaseSeleccionado.setDetalleInventario(detalleNuevo);

				listComputadoresRenovados.add(computadorBaseSeleccionado);
			}

			limpiarComputadorBase();
			limpiarComputadorNuevo();

		} catch (Exception e) {
			System.out.println("Error en el metodo asginarRenovacion -->> " + e);
		}
	}

	public void cancelarAsignacion() {

		if (computadorRenovadoSeleccionado == null) {
			mensajes.mostrarMensaje("Debe seleccionar un registro para cancelar", 3);
			computadorRenovadoSeleccionado = new DetalleInventario();
		} else {
			listComputadoresRenovados.remove(computadorRenovadoSeleccionado);
			computadorRenovadoSeleccionado = new DetalleInventario();
		}

	}

	public void cambiarUsuario() {
		try {
			if (computadorBaseSeleccionado == null) {
				mensajes.mostrarMensaje("Seleccione un computador base", 3);
				computadorBaseSeleccionado = new DetalleInventario();
			} else {
				if (usuarioSeleccionado == null) {
					mensajes.mostrarMensaje("Seleccione un usuario", 3);
				} else {
					listComputadorBase.remove(computadorBaseSeleccionado);
					computadorBaseSeleccionado.setUsuario(usuarioSeleccionado);
					listComputadorBase.add(computadorBaseSeleccionado);
					computadorBaseSeleccionado = new DetalleInventario();
					mensajes.mostrarMensaje("El usuario fue cambiado con exito", 1);
				}
			}
			limpiarUsuario();
		} catch (Exception e) {
			System.out.println("Error en el metodo cambiarUsuario -->> " + e);
		}
	}

	public void limpiarComputadorNuevo() {
		computadorNuevoSeleccionado = new Computador();
	}

	public void limpiarComputadorBase() {
		computadorBaseSeleccionado = new DetalleInventario();
	}

	public void limpiarUsuario() {
		usuarioSeleccionado = new Usuario();
	}

	public void limpiar() {

		computador = new Computador();
		computadorBaseSeleccionado = new DetalleInventario();
		computadorNuevoSeleccionado = new Computador();

	}

	public void tabIsClosed() {

		System.out.println("Cerrando sesion por browser");

		FacesContext context = FacesContext.getCurrentInstance();

		ExternalContext externalContext = context.getExternalContext();

		Object session = externalContext.getSession(false);

		HttpSession httpSession = (HttpSession) session;

		httpSession.invalidate();
	}

	private void inicializarDelegados() throws Exception {

		if (dnComputadors == null) {
			dnComputadors = new DNComputador();
		}

		if (dNDetalleInventario == null) {
			dNDetalleInventario = new DNDetalleInventario();
		}

		if (dNDetalleInventarioHistorial == null) {
			dNDetalleInventarioHistorial = new DNDetalleInventarioHistorial();
		}

		if (dNUsuario == null) {
			dNUsuario = new DNUsuario();
		}

	}

	public ArrayList<DetalleInventario> getListComputadoresRenovados() {
		return listComputadoresRenovados;
	}

	public void setListComputadoresRenovados(ArrayList<DetalleInventario> listComputadoresRenovados) {
		this.listComputadoresRenovados = listComputadoresRenovados;
	}

	public DetalleInventario getComputadorRenovadoSeleccionado() {
		return computadorRenovadoSeleccionado;
	}

	public void setComputadorRenovadoSeleccionado(DetalleInventario computadorRenovadoSeleccionado) {
		this.computadorRenovadoSeleccionado = computadorRenovadoSeleccionado;
	}

	public List<Usuario> getListUsuarioAdministrativos() {
		return listUsuarioAdministrativos;
	}

	public void setListUsuarioAdministrativos(List<Usuario> listUsuarioAdministrativos) {
		this.listUsuarioAdministrativos = listUsuarioAdministrativos;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public DetalleInventario getComputadorBaseSeleccionado() {
		return computadorBaseSeleccionado;
	}

	public void setComputadorBaseSeleccionado(DetalleInventario computadorBaseSeleccionado) {
		this.computadorBaseSeleccionado = computadorBaseSeleccionado;
	}

	public List<DetalleInventario> getListComputadorBase() {
		return listComputadorBase;
	}

	public void setListComputadorBase(List<DetalleInventario> listComputadorBase) {
		this.listComputadorBase = listComputadorBase;
	}

	public List<Computador> getListComputadorNuevos() {
		return listComputadorNuevos;
	}

	public void setListComputadorNuevos(List<Computador> listComputadorNuevos) {
		this.listComputadorNuevos = listComputadorNuevos;
	}

	public Computador getComputadorNuevoSeleccionado() {
		return computadorNuevoSeleccionado;
	}

	public void setComputadorNuevoSeleccionado(Computador computadorNuevoSeleccionado) {
		this.computadorNuevoSeleccionado = computadorNuevoSeleccionado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Computador getComputador() {
		return computador;
	}

	public void setComputador(Computador computador) {
		this.computador = computador;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

}
