package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNUsuario;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Usuario;

@ManagedBean(name = "MBLogin")
@SessionScoped
public class MBLogin implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNUsuario dnUsuarios;

	private Usuario usuario;
	private String cedula;

	private String usuarioLogin;
	private String password;

	public MBLogin() {
		usuario = new Usuario();
	}

	public void navegarControl() {

		try {

			// HttpServletRequest request = (HttpServletRequest)
			// FacesContext.getCurrentInstance().getExternalContext()
			// .getRequest();
			//
			// String usuarioLogin = request.getParameter("usuarioLogin");
			//
			// String usuarioContrasena = request.getParameter("usuarioContrasena");
			//
			// use the value in txtProperty as you want...
			// Note: don't use System.out.println in production, use a logger instead
			//
			// System.out.println(usuarioLogin + "/" + usuarioContrasena);
			//
			// if (usuarioLogin != null && usuarioContrasena != null) {

			usuario.setUsuario(usuarioLogin);
			usuario.setPassword(password);
			inicializarDelegados();

			if (dnUsuarios.consultarUsuarioInicio(usuario) == 1) {

				Usuario logueado = dnUsuarios.consultarDetalleUsuario(usuarioLogin);

				FacesContext context = FacesContext.getCurrentInstance();
				ExternalContext extContext = context.getExternalContext();

				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", logueado);

				String url2 = extContext.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context,
						"/xhtml/gestion/bienvenido.xhtml"));
				extContext.redirect(url2);
			} else {
				mensajes.mostrarMensajeAlerta("Identificacion o correo invalidos");

			}
			// }

		} catch (

		Exception exception) {
			exception.printStackTrace();
		}
	}

	public void recuperarPassword() {
		try {

			inicializarDelegados();

			Usuario usuarioTemp = dnUsuarios.consultarDetalleUsuario(usuarioLogin);

			if (usuarioTemp != null) {
				if (dnUsuarios.recuperarContrasena(usuarioTemp)) {
					usuarioLogin = "";
					password = "";
					mensajes.mostrarMensaje("Contraseña enviada al correo", 1);
				} else {
					mensajes.mostrarMensaje("No se pudo realiza la operación, valide con el administrador", 3);
				}
			} else {
				mensajes.mostrarMensaje("Usuario Invalido", 3);
			}

			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext extContext = context.getExternalContext();

			String url2 = extContext
					.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context, "/index.xhtml"));
			extContext.redirect(url2);

		} catch (Exception e) {
			System.out.println("Error en el metodo recuperarPassword --->> " + e);
		}
	}

	public void cerrarSesion() {

		try {
			inicializarDelegados();

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
			Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("usuario");

			if (us == null) {
				usuarioLogin = "";
				FacesContext.getCurrentInstance().getExternalContext().redirect("/ViewInventario/faces/index.xhtml");
			}

			Object session = FacesContext.getCurrentInstance().getExternalContext().getSession(false);

			HttpSession httpSession = (HttpSession) session;

			httpSession.invalidate();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void verificarSesion() {

		try {

			Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("usuario");

			if (us == null) {

				FacesContext.getCurrentInstance().getExternalContext().redirect("/ViewInventario/faces/index.xhtml");
			}

		} catch (Exception e) {
		}

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
		if (dnUsuarios == null) {
			dnUsuarios = new DNUsuario();
		}
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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

	public DNUsuario getDnUsuarios() {
		return dnUsuarios;
	}

	public void setDnUsuarios(DNUsuario dnUsuarios) {
		this.dnUsuarios = dnUsuarios;
	}

	public String getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
