package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNOperacionServicio;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.OperacionServicio;

@ManagedBean(name = "MBOperacionServicio")
@SessionScoped
public class MBOperacionServicio implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNOperacionServicio dnOperacionServicios;

	List<OperacionServicio> listOperacionServicios;
	List<OperacionServicio> filterOperacion;

	private OperacionServicio operacionServicioSeleccionado;
	private OperacionServicio operacionServicioModificar;
	private OperacionServicio operacionServicio;

	public MBOperacionServicio() {
		operacionServicio = new OperacionServicio();
		operacionServicioSeleccionado = new OperacionServicio();
		operacionServicioModificar = new OperacionServicio();
		cargarListaOperacionServicios();
	}

	public void cargarListaOperacionServicios() {
		try {
			inicializarDelegados();
			listOperacionServicios = dnOperacionServicios.consultarAllOperacionServicioActivos();
		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaOperacionServicios -->> " + e);
		}
	}

	public void registrarOperacionServicio() {
		try {
			inicializarDelegados();
			operacionServicio.setIdEstado(1);

			if (dnOperacionServicios.crearOperacionServicio(operacionServicio) != null) {
				limpiar();
				cargarListaOperacionServicios();
				mensajes.mostrarMensaje("Registro Exitoso", 1);
			} else {
				mensajes.mostrarMensaje("El registro no se pudo realizar, valide con el administrador", 3);
			}

		} catch (Exception e) {
			System.out.println("Error en el metodo registrarOperacionServicio -->> " + e);
		}
	}

	public void modificarOperacionServicio() {
		try {
			inicializarDelegados();

			if (dnOperacionServicios.actualizarOperacionServicio(operacionServicioModificar) != null) {
				cargarListaOperacionServicios();
				mensajes.mostrarMensaje("Modificación Exitosa", 1);
			} else {
				mensajes.mostrarMensaje("No se pudo realizar la modificación, valide con el administrador", 3);
			}
		} catch (Exception e) {
			System.out.println("Error en el metodo registrarOperacionServicio -->> " + e);
		}
	}

	public void onRowSelect() {
		operacionServicioModificar = operacionServicioSeleccionado;
	}

	public void limpiar() {
		operacionServicio = new OperacionServicio();
		operacionServicioSeleccionado = new OperacionServicio();
		operacionServicioModificar = new OperacionServicio();
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
		if (dnOperacionServicios == null) {
			dnOperacionServicios = new DNOperacionServicio();
		}
	}

	public List<OperacionServicio> getFilterOperacion() {
		return filterOperacion;
	}

	public void setFilterOperacion(List<OperacionServicio> filterOperacion) {
		this.filterOperacion = filterOperacion;
	}

	public List<OperacionServicio> getListOperacionServicios() {
		return listOperacionServicios;
	}

	public void setListOperacionServicios(List<OperacionServicio> listOperacionServicios) {
		this.listOperacionServicios = listOperacionServicios;
	}

	public OperacionServicio getOperacionServicioSeleccionado() {
		return operacionServicioSeleccionado;
	}

	public void setOperacionServicioSeleccionado(OperacionServicio operacionServicioSeleccionado) {
		this.operacionServicioSeleccionado = operacionServicioSeleccionado;
	}

	public OperacionServicio getOperacionServicioModificar() {
		return operacionServicioModificar;
	}

	public void setOperacionServicioModificar(OperacionServicio operacionServicioModificar) {
		this.operacionServicioModificar = operacionServicioModificar;
	}

	public OperacionServicio getOperacionServicio() {
		return operacionServicio;
	}

	public void setOperacionServicio(OperacionServicio operacionServicio) {
		this.operacionServicio = operacionServicio;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

}
