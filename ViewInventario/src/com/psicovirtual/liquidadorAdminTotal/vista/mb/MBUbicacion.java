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
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNSede;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNUbicacion;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Sede;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Ubicacion;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.UnidadEstrategicaServicio;

@ManagedBean(name = "MBUbicacion")
@SessionScoped
public class MBUbicacion implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNUbicacion dnUbicacion;
	DNSede DNSede;
	List<Ubicacion> listUbicacions;
	List<SelectItem> listSede;
	String idSede;
	String idSedeModif;

	private Ubicacion ubicacionSeleccionado;
	private Ubicacion ubicacionModificar;
	private Ubicacion ubicacion;

	public MBUbicacion() {
		ubicacion = new Ubicacion();
		ubicacionSeleccionado = new Ubicacion();
		ubicacionModificar = new Ubicacion();
		cargarListaUbicacion();
		idSede = "";
	}

	public void cargarListaUbicacion() {
		try {
			inicializarDelegados();

			listUbicacions = dnUbicacion.consultarAllUbicacionActivos();

			listSede = new ArrayList<>();
			for (Sede list : DNSede.consultarAllSedeActivos()) {
				listSede.add(new SelectItem(list.getIdSede(), list.getNombre()));
			}

		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaUbicacion -->> " + e);
		}
	}

	public void registrarUbicacion() {
		try {
			inicializarDelegados();

			ubicacion.setIdEstado(1);
			if (idSede.equals("")) {
				mensajes.mostrarMensaje("Debe seleccionar una Sede", 2);
			} else {
				Sede sede = DNSede.consultarDetalleSede(idSede);
				ubicacion.setSede(sede);

				if (dnUbicacion.crearUbicacion(ubicacion) != null) {
					limpiar();
					cargarListaUbicacion();
					mensajes.mostrarMensaje("Registro Exitoso", 1);
				} else {
					mensajes.mostrarMensaje("El registro no se pudo realizar, valide con el administrador", 3);
				}
			}

		} catch (Exception e) {
			System.out.println("Error en el metodo registrarUbicacion -->> " + e);
		}
	}

	public void modificarUbicacion() {
		try {
			inicializarDelegados();

			if (idSede.equals("")) {
				mensajes.mostrarMensaje("Debe seleccionar una Sede", 2);
			} else {

				Sede sede = DNSede.consultarDetalleSede(idSede);
				ubicacion.setSede(sede);

				if (dnUbicacion.actualizarUbicacion(ubicacionModificar) != null) {
					cargarListaUbicacion();
					mensajes.mostrarMensaje("Modificación Exitosa", 1);
				} else {
					mensajes.mostrarMensaje("No se pudo realizar la modificación, valide con el administrador", 3);
				}

			}
		} catch (Exception e) {
			System.out.println("Error en el metodo registrarUbicacion -->> " + e);
		}
	}

	public void onRowSelect() {
		idSedeModif = "" + ubicacionSeleccionado.getSede().getIdSede();
		ubicacionModificar = ubicacionSeleccionado;
	}

	public void limpiar() {
		ubicacion = new Ubicacion();
		ubicacionSeleccionado = new Ubicacion();
		ubicacionModificar = new Ubicacion();
		idSede = "";
		idSedeModif = "";
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
		if (dnUbicacion == null) {
			dnUbicacion = new DNUbicacion();
		}

		if (DNSede == null) {
			DNSede = new DNSede();
		}
	}

	public List<Ubicacion> getListUbicacions() {
		return listUbicacions;
	}

	public void setListUbicacions(List<Ubicacion> listUbicacions) {
		this.listUbicacions = listUbicacions;
	}

	public List<SelectItem> getListSede() {
		return listSede;
	}

	public void setListSede(List<SelectItem> listSede) {
		this.listSede = listSede;
	}

	public String getIdSede() {
		return idSede;
	}

	public void setIdSede(String idSede) {
		this.idSede = idSede;
	}

	public String getIdSedeModif() {
		return idSedeModif;
	}

	public void setIdSedeModif(String idSedeModif) {
		this.idSedeModif = idSedeModif;
	}

	public Ubicacion getUbicacionSeleccionado() {
		return ubicacionSeleccionado;
	}

	public void setUbicacionSeleccionado(Ubicacion ubicacionSeleccionado) {
		this.ubicacionSeleccionado = ubicacionSeleccionado;
	}

	public Ubicacion getUbicacionModificar() {
		return ubicacionModificar;
	}

	public void setUbicacionModificar(Ubicacion ubicacionModificar) {
		this.ubicacionModificar = ubicacionModificar;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

}
