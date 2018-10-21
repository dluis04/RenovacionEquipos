package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTipoUsuario;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNUsuario;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.TipoUsuario;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Usuario;

@ManagedBean(name = "MBUsuario")
@SessionScoped
public class MBUsuario implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNUsuario dnUsuarios;
	DNTipoUsuario dNTipoUsuario;
	String idTipoUsuario;
	String idTipoUsuarioModi;
	String confirmarPass;
	List<SelectItem> listTiposUsuario;
	List<Usuario> listUsuarios;
	private Usuario usuarioSeleccionado;
	private Usuario usuarioModificar;
	private Usuario usuario;
	private TipoUsuario tipoUsuario;

	public MBUsuario() {
		usuario = new Usuario();
		usuarioSeleccionado = new Usuario();
		usuarioModificar = new Usuario();
		cargarListaTiposUsuarios();
		cargarListaUsuarios();
		idTipoUsuario = "";
	}

	public void cargarListaTiposUsuarios() {
		try {
			inicializarDelegados();
			listTiposUsuario = new ArrayList<>();
			for (TipoUsuario list : dNTipoUsuario.consultarAllTiposUsuariosActivos()) {
				listTiposUsuario.add(new SelectItem(list.getIdTipo(), list.getTipo()));
			}

		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaTiposUsuarios -->> " + e);
		}
	}

	public void cargarListaUsuarios() {
		try {
			inicializarDelegados();

			listUsuarios = dnUsuarios.consultarAllUsuariosActivos();
		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaUsuarios -->> " + e);
		}
	}

	public void registrarUsuario() {
		try {
			inicializarDelegados();

			if (confirmarPass.equals(usuario.getPassword())) {
				usuario.setTipoUsuario(dNTipoUsuario.consultarDetalleTipoUsuario(idTipoUsuario));
				usuario.setIdEstado(1);

				if (dnUsuarios.crearUsuario(usuario) != null) {
					limpiar();
					cargarListaUsuarios();
					mensajes.mostrarMensaje("Registro Exitoso", 1);
				} else {
					mensajes.mostrarMensaje("El registro no se pudo realizar, valide con el administrador", 3);
				}
			} else {
				mensajes.mostrarMensaje("Contraseñas Invalidas", 2);
			}

		} catch (Exception e) {
			System.out.println("Error en el metodo registrarUsuario -->> " + e);
		}
	}

	public void modificarUsuario() {
		try {
			inicializarDelegados();

			if (dnUsuarios.actualizarUsuario(usuarioModificar) != null) {
				cargarListaUsuarios();
				mensajes.mostrarMensaje("Modificación Exitosa", 1);
			} else {
				mensajes.mostrarMensaje("No se pudo realizar la modificación, valide con el administrador", 3);
			}
		} catch (Exception e) {
			System.out.println("Error en el metodo registrarUsuario -->> " + e);
		}
	}

	public void onRowSelect() {
		idTipoUsuarioModi = "" + usuarioSeleccionado.getTipoUsuario().getIdTipo();
		usuarioModificar = usuarioSeleccionado;
	}

	public void limpiar() {
		usuario = new Usuario();
		usuarioSeleccionado = new Usuario();
		usuarioModificar = new Usuario();
		tipoUsuario = new TipoUsuario();
		idTipoUsuario = "";
		idTipoUsuarioModi = "";
		confirmarPass = "";
	}

	// public void cerrarSesion() {
	//
	// try {
	// inicializarDelegados();
	// if (dNSesionActiva.cerrarSesionActivaByUsuario(usuario) == 0) {
	//
	// FacesContext context = FacesContext.getCurrentInstance();
	//
	// ExternalContext externalContext = context.getExternalContext();
	//
	// Object session = externalContext.getSession(false);
	//
	// HttpSession httpSession = (HttpSession) session;
	//
	// httpSession.invalidate();
	//
	// String url2 = externalContext.encodeActionURL(
	// context.getApplication().getViewHandler().getActionURL(context,
	// "/index.xhtml"));
	// externalContext.redirect(url2);
	// }
	// } catch (Exception e) {
	// // TODO: Add catch code
	// e.printStackTrace();
	// }
	//
	// }

	public void tabIsClosed() {

		System.out.println("Cerrando sesion por browser");

		FacesContext context = FacesContext.getCurrentInstance();

		ExternalContext externalContext = context.getExternalContext();

		Object session = externalContext.getSession(false);

		HttpSession httpSession = (HttpSession) session;

		httpSession.invalidate();
	}

	private void inicializarDelegados() throws Exception {
		if (dnUsuarios == null) {
			dnUsuarios = new DNUsuario();
		}

		if (dNTipoUsuario == null) {
			dNTipoUsuario = new DNTipoUsuario();
		}
	}

	public String getConfirmarPass() {
		return confirmarPass;
	}

	public void setConfirmarPass(String confirmarPass) {
		this.confirmarPass = confirmarPass;
	}

	public Usuario getUsuarioModificar() {
		return usuarioModificar;
	}

	public void setUsuarioModificar(Usuario usuarioModificar) {
		this.usuarioModificar = usuarioModificar;
	}

	public String getIdTipoUsuarioModi() {
		return idTipoUsuarioModi;
	}

	public void setIdTipoUsuarioModi(String idTipoUsuarioModi) {
		this.idTipoUsuarioModi = idTipoUsuarioModi;
	}

	public String getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(String idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public List<Usuario> getListUsuarios() {
		return listUsuarios;
	}

	public void setListUsuarios(List<Usuario> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public List<SelectItem> getListTiposUsuario() {
		return listTiposUsuario;
	}

	public void setListTiposUsuario(List<SelectItem> listTiposUsuario) {
		this.listTiposUsuario = listTiposUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

}
