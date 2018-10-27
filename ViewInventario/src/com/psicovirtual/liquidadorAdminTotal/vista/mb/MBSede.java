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
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNSede;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Ciudad;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Sede;

@ManagedBean(name = "MBSede")
@SessionScoped
public class MBSede implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNSede dnSedes;
	DNCiudad dNCiudad;
	List<Sede> listSedes;
	List<Ciudad> listCiudad;

	private Ciudad ciudadSeleccionado;
	private Ciudad ciudadModifica;
	private Sede sedeSeleccionado;
	private Sede sedeModificar;
	private Sede sede;

	public MBSede() {
		sede = new Sede();
		sedeSeleccionado = new Sede();
		sedeModificar = new Sede();
		ciudadModifica = new Ciudad();
		ciudadSeleccionado = new Ciudad();
		cargarListaSedes();
	}

	public void cargarListaSedes() {
		try {
			inicializarDelegados();
			listCiudad = dNCiudad.consultarAllCiudadActivos();
			listSedes = dnSedes.consultarAllSedeActivos();

		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaSedes -->> " + e);
		}
	}

	public void registrarSede() {
		try {
			inicializarDelegados();

			if (ciudadSeleccionado == null) {
				mensajes.mostrarMensaje("Debe seleccionar una Ciudad", 2);
			} else {
				sede.setIdEstado(1);
				sede.setCiudad(ciudadSeleccionado);

				if (dnSedes.crearSede(sede) != null) {
					limpiar();
					cargarListaSedes();
					mensajes.mostrarMensaje("Registro Exitoso", 1);
				} else {
					mensajes.mostrarMensaje("El registro no se pudo realizar, valide con el administrador", 3);
				}
			}
		} catch (Exception e) {
			System.out.println("Error en el metodo registrarSede -->> " + e);
		}
	}

	public void modificarSede() {
		try {
			inicializarDelegados();

			if (dnSedes.actualizarSede(sedeModificar) != null) {
				limpiar();
				cargarListaSedes();
				mensajes.mostrarMensaje("Modificación Exitosa", 1);

			} else {
				mensajes.mostrarMensaje("No se pudo realizar la modificación, valide con el administrador", 3);
			}

		} catch (Exception e) {
			System.out.println("Error en el metodo registrarSede -->> " + e);
		}
	}

	public void onRowSelect() {
		sedeModificar = sedeSeleccionado;
		ciudadModifica.setIdCiudad(sedeSeleccionado.getCiudad().getIdCiudad());
		ciudadModifica.setNombre(sedeSeleccionado.getCiudad().getNombre());
	}

	public void seleccionarCiudad() {
		if (ciudadSeleccionado != null) {
			mensajes.mostrarMensaje("Ciudad seleccionada exitosamente", 1);
		} else {
			mensajes.mostrarMensaje("Debe seleccionar una Ciudad", 2);
		}
		limpiarIsNull();
	}

	public void seleccionarCiudadModi() {
		if (ciudadModifica != null) {

			listSedes.remove(sedeSeleccionado);
			sedeSeleccionado.setCiudad(ciudadModifica);
			listSedes.add(sedeSeleccionado);

			mensajes.mostrarMensaje("Ciudad seleccionada exitosamente", 1);
		} else {
			mensajes.mostrarMensaje("Debe seleccionar una Ciudad", 2);
		}
		limpiarIsNull();
	}

	public void limpiarCiudad() {
		ciudadSeleccionado = null;
		ciudadSeleccionado = new Ciudad();
		ciudadModifica = null;
		ciudadModifica = new Ciudad();
	}

	public void limpiarIsNull() {
		if (ciudadSeleccionado == null) {
			ciudadSeleccionado = new Ciudad();
		}

		if (ciudadModifica == null) {
			ciudadModifica = new Ciudad();
		}
	}

	public void limpiar() {
		sede = new Sede();
		sedeSeleccionado = new Sede();
		sedeModificar = new Sede();
		ciudadModifica = new Ciudad();
		ciudadSeleccionado = new Ciudad();
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
		if (dnSedes == null) {
			dnSedes = new DNSede();
		}

		if (dNCiudad == null) {
			dNCiudad = new DNCiudad();
		}

	}

	public List<Ciudad> getListCiudad() {
		return listCiudad;
	}

	public void setListCiudad(List<Ciudad> listCiudad) {
		this.listCiudad = listCiudad;
	}

	public Ciudad getCiudadSeleccionado() {
		return ciudadSeleccionado;
	}

	public void setCiudadSeleccionado(Ciudad ciudadSeleccionado) {
		this.ciudadSeleccionado = ciudadSeleccionado;
	}

	public Ciudad getCiudadModifica() {
		return ciudadModifica;
	}

	public void setCiudadModifica(Ciudad ciudadModifica) {
		this.ciudadModifica = ciudadModifica;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public List<Sede> getListSedes() {
		return listSedes;
	}

	public void setListSedes(List<Sede> listSedes) {
		this.listSedes = listSedes;
	}

	public Sede getSedeSeleccionado() {
		return sedeSeleccionado;
	}

	public void setSedeSeleccionado(Sede sedeSeleccionado) {
		this.sedeSeleccionado = sedeSeleccionado;
	}

	public Sede getSedeModificar() {
		return sedeModificar;
	}

	public void setSedeModificar(Sede sedeModificar) {
		this.sedeModificar = sedeModificar;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

}
