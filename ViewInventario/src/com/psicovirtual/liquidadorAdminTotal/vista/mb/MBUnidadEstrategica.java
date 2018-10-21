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
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNCiudad;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNUnidadEstrategica;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Ciudad;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.UnidadEstrategicaServicio;

@ManagedBean(name = "MBUnidadEstrategica")
@SessionScoped
public class MBUnidadEstrategica implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNUnidadEstrategica dnUnidadEstrategicaServicios;
	DNCiudad dNCiudad;
	List<UnidadEstrategicaServicio> listUnidadEstrategicaServicios;
	List<SelectItem> listCiudad;
	String idCiudad;
	String idCiudadModif;

	private UnidadEstrategicaServicio unidadEstrategicaServicioSeleccionado;
	private UnidadEstrategicaServicio unidadEstrategicaServicioModificar;
	private UnidadEstrategicaServicio unidadEstrategicaServicio;

	public MBUnidadEstrategica() {
		unidadEstrategicaServicio = new UnidadEstrategicaServicio();
		unidadEstrategicaServicioSeleccionado = new UnidadEstrategicaServicio();
		unidadEstrategicaServicioModificar = new UnidadEstrategicaServicio();
		cargarListaUnidadEstrategicaServicios();
		idCiudad = "";
	}

	public void cargarListaUnidadEstrategicaServicios() {
		try {
			inicializarDelegados();

			listUnidadEstrategicaServicios = dnUnidadEstrategicaServicios
					.consultarAllUnidadEstrategicaServicioActivos();

			listCiudad = new ArrayList<>();
			for (Ciudad list : dNCiudad.consultarAllCiudadActivos()) {
				listCiudad.add(new SelectItem(list.getIdCiudad(), list.getNombre()));
			}

		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaUnidadEstrategicaServicios -->> " + e);
		}
	}

	public void registrarUnidadEstrategicaServicio() {
		try {
			inicializarDelegados();

			unidadEstrategicaServicio.setIdEstado(1);
			if (idCiudad.equals("")) {
				mensajes.mostrarMensaje("Debe seleccionar una ciudad", 2);
			} else {
				Ciudad ciudad = dNCiudad.consultarDetalleCiudad(idCiudad);
				unidadEstrategicaServicio.setCiudad(ciudad);

				if (dnUnidadEstrategicaServicios.crearUnidadEstrategicaServicio(unidadEstrategicaServicio) != null) {
					limpiar();
					cargarListaUnidadEstrategicaServicios();
					mensajes.mostrarMensaje("Registro Exitoso", 1);
				} else {
					mensajes.mostrarMensaje("El registro no se pudo realizar, valide con el administrador", 3);
				}
			}

		} catch (Exception e) {
			System.out.println("Error en el metodo registrarUnidadEstrategicaServicio -->> " + e);
		}
	}

	public void modificarUnidadEstrategicaServicio() {
		try {
			inicializarDelegados();

			if (idCiudad.equals("")) {
				mensajes.mostrarMensaje("Debe seleccionar una ciudad", 2);
			} else {

				Ciudad ciudad = dNCiudad.consultarDetalleCiudad(idCiudad);
				unidadEstrategicaServicioModificar.setCiudad(ciudad);

				if (dnUnidadEstrategicaServicios
						.actualizarUnidadEstrategicaServicio(unidadEstrategicaServicioModificar) != null) {
					cargarListaUnidadEstrategicaServicios();
					mensajes.mostrarMensaje("Modificación Exitosa", 1);
				} else {
					mensajes.mostrarMensaje("No se pudo realizar la modificación, valide con el administrador", 3);
				}

			}
		} catch (Exception e) {
			System.out.println("Error en el metodo registrarUnidadEstrategicaServicio -->> " + e);
		}
	}

	public void onRowSelect() {
		idCiudadModif = "" + unidadEstrategicaServicioSeleccionado.getCiudad().getIdCiudad();
		unidadEstrategicaServicioModificar = unidadEstrategicaServicioSeleccionado;
	}

	public void limpiar() {
		unidadEstrategicaServicio = new UnidadEstrategicaServicio();
		unidadEstrategicaServicioSeleccionado = new UnidadEstrategicaServicio();
		unidadEstrategicaServicioModificar = new UnidadEstrategicaServicio();
		idCiudad = "";
		idCiudadModif = "";
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
		if (dnUnidadEstrategicaServicios == null) {
			dnUnidadEstrategicaServicios = new DNUnidadEstrategica();
		}

		if (dNCiudad == null) {
			dNCiudad = new DNCiudad();
		}
	}

	public String getIdCiudadModif() {
		return idCiudadModif;
	}

	public void setIdCiudadModif(String idCiudadModif) {
		this.idCiudadModif = idCiudadModif;
	}

	public String getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(String idCiudad) {
		this.idCiudad = idCiudad;
	}

	public List<SelectItem> getListCiudad() {
		return listCiudad;
	}

	public void setListCiudad(List<SelectItem> listCiudad) {
		this.listCiudad = listCiudad;
	}

	public List<UnidadEstrategicaServicio> getListUnidadEstrategicaServicios() {
		return listUnidadEstrategicaServicios;
	}

	public void setListUnidadEstrategicaServicios(List<UnidadEstrategicaServicio> listUnidadEstrategicaServicios) {
		this.listUnidadEstrategicaServicios = listUnidadEstrategicaServicios;
	}

	public UnidadEstrategicaServicio getUnidadEstrategicaServicioSeleccionado() {
		return unidadEstrategicaServicioSeleccionado;
	}

	public void setUnidadEstrategicaServicioSeleccionado(
			UnidadEstrategicaServicio unidadEstrategicaServicioSeleccionado) {
		this.unidadEstrategicaServicioSeleccionado = unidadEstrategicaServicioSeleccionado;
	}

	public UnidadEstrategicaServicio getUnidadEstrategicaServicioModificar() {
		return unidadEstrategicaServicioModificar;
	}

	public void setUnidadEstrategicaServicioModificar(UnidadEstrategicaServicio unidadEstrategicaServicioModificar) {
		this.unidadEstrategicaServicioModificar = unidadEstrategicaServicioModificar;
	}

	public UnidadEstrategicaServicio getUnidadEstrategicaServicio() {
		return unidadEstrategicaServicio;
	}

	public void setUnidadEstrategicaServicio(UnidadEstrategicaServicio unidadEstrategicaServicio) {
		this.unidadEstrategicaServicio = unidadEstrategicaServicio;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

}
