package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNCiudad;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Ciudad;

@ManagedBean(name = "MBCiudad")
@SessionScoped
public class MBCiudad implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNCiudad dnCiudads;
	List<Ciudad> listCiudads;
	private Ciudad ciudadSeleccionado;
	private Ciudad ciudadModificar;
	private Ciudad ciudad;

	public MBCiudad() {
		ciudad = new Ciudad();
		ciudadSeleccionado = new Ciudad();
		ciudadModificar = new Ciudad();
		cargarListaCiudads();
	}

	public void cargarListaCiudads() {
		try {
			inicializarDelegados();

			listCiudads = dnCiudads.consultarAllCiudadActivos();
		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaCiudads -->> " + e);
		}
	}

	public void registrarCiudad() {
		try {
			inicializarDelegados();

			ciudad.setIdEstado(1);

			if (dnCiudads.crearCiudad(ciudad) != null) {
				limpiar();
				cargarListaCiudads();
				mensajes.mostrarMensaje("Registro Exitoso", 1);
			} else {
				mensajes.mostrarMensaje("El registro no se pudo realizar, valide con el administrador", 3);
			}

		} catch (Exception e) {
			System.out.println("Error en el metodo registrarCiudad -->> " + e);
		}
	}

	public void modificarCiudad() {
		try {
			inicializarDelegados();

			if (dnCiudads.actualizarCiudad(ciudadModificar) != null) {
				cargarListaCiudads();
				mensajes.mostrarMensaje("Modificación Exitosa", 1);
			} else {
				mensajes.mostrarMensaje("No se pudo realizar la modificación, valide con el administrador", 3);
			}
		} catch (Exception e) {
			System.out.println("Error en el metodo registrarCiudad -->> " + e);
		}
	}

	public void onRowSelect() {
		ciudadModificar = ciudadSeleccionado;
	}

	public void limpiar() {
		ciudad = new Ciudad();
		ciudadSeleccionado = new Ciudad();
		ciudadModificar = new Ciudad();
	}

	public void tabIsClosed() {

		System.out.println("Cerrando sesion por browser");

		FacesContext context = FacesContext.getCurrentInstance();

		ExternalContext externalContext = context.getExternalContext();

		Object session = externalContext.getSession(false);

		HttpSession httpSession = (HttpSession) session;

		httpSession.invalidate();
	}

	public List<Ciudad> getListCiudads() {
		return listCiudads;
	}

	public void setListCiudads(List<Ciudad> listCiudads) {
		this.listCiudads = listCiudads;
	}

	public Ciudad getCiudadSeleccionado() {
		return ciudadSeleccionado;
	}

	public void setCiudadSeleccionado(Ciudad ciudadSeleccionado) {
		this.ciudadSeleccionado = ciudadSeleccionado;
	}

	public Ciudad getCiudadModificar() {
		return ciudadModificar;
	}

	public void setCiudadModificar(Ciudad ciudadModificar) {
		this.ciudadModificar = ciudadModificar;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	private void inicializarDelegados() throws Exception {
		if (dnCiudads == null) {
			dnCiudads = new DNCiudad();
		}
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

}
