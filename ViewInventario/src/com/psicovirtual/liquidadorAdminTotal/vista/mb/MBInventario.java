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
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNComputador;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Ciudad;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Computador;

@ManagedBean(name = "MBInventario")
@SessionScoped
public class MBInventario implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNCiudad dnCiudads;
	DNComputador dnComputador;

	List<Computador> listComputadorNuevos;

	private Computador computadorSeleccionado;

	public MBInventario() {
		computadorSeleccionado = new Computador();
		cargarListadoComputadoresNuevos();
	}

	public void cargarListadoComputadoresNuevos() {
		try {
			inicializarDelegados();
			listComputadorNuevos = dnComputador.consultarAllComputadorNuevos();

		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaCiudads -->> " + e);
		}
	}

	public void registrarCiudad() {
		try {
			inicializarDelegados();

		} catch (Exception e) {
			System.out.println("Error en el metodo registrarCiudad -->> " + e);
		}
	}

	public void modificarCiudad() {
		try {
			inicializarDelegados();

			mensajes.mostrarMensaje("Modificación Exitosa", 1);

		} catch (Exception e) {
			System.out.println("Error en el metodo registrarCiudad -->> " + e);
		}
	}

	public void onRowSelect() {

	}

	public void limpiar() {

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
		if (dnComputador == null) {
			dnComputador = new DNComputador();
		}
	}

	public Computador getComputadorSeleccionado() {
		return computadorSeleccionado;
	}

	public void setComputadorSeleccionado(Computador computadorSeleccionado) {
		this.computadorSeleccionado = computadorSeleccionado;
	}

	public List<Computador> getListComputadorNuevos() {
		return listComputadorNuevos;
	}

	public void setListComputadorNuevos(List<Computador> listComputadorNuevos) {
		this.listComputadorNuevos = listComputadorNuevos;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

}
