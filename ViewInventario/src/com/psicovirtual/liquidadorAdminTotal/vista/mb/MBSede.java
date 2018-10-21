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
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNUnidadEstrategica;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Sede;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.UnidadEstrategicaServicio;

@ManagedBean(name = "MBSede")
@SessionScoped
public class MBSede implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNSede dnSedes;
	DNUnidadEstrategica dNUnidadEstrategica;
	List<Sede> listSedes;
	List<SelectItem> listUnidadEstrategica;
	String idUnidadEstrategica;
	String idUnidadEstrategicaModif;

	private Sede sedeSeleccionado;
	private Sede sedeModificar;
	private Sede sede;

	public MBSede() {
		sede = new Sede();
		sedeSeleccionado = new Sede();
		sedeModificar = new Sede();
		cargarListaSedes();
		idUnidadEstrategica = "";
	}

	public void cargarListaSedes() {
		try {
			inicializarDelegados();

			listSedes = dnSedes.consultarAllSedeActivos();

			listUnidadEstrategica = new ArrayList<>();
			for (UnidadEstrategicaServicio list : dNUnidadEstrategica.consultarAllUnidadEstrategicaServicioActivos()) {
				listUnidadEstrategica.add(new SelectItem(list.getIdUnidad(), list.getNombre()));
			}

		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaSedes -->> " + e);
		}
	}

	public void registrarSede() {
		try {
			inicializarDelegados();

			sede.setIdEstado(1);
			if (idUnidadEstrategica.equals("")) {
				mensajes.mostrarMensaje("Debe seleccionar una UnidadEstrategica", 2);
			} else {
				UnidadEstrategicaServicio UnidadEstrategica = dNUnidadEstrategica
						.consultarDetalleUnidadEstrategicaServicio(idUnidadEstrategica);
				sede.setUnidadEstrategicaServicio(UnidadEstrategica);

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

			if (idUnidadEstrategica.equals("")) {
				mensajes.mostrarMensaje("Debe seleccionar una UnidadEstrategica", 2);
			} else {

				UnidadEstrategicaServicio UnidadEstrategica = dNUnidadEstrategica
						.consultarDetalleUnidadEstrategicaServicio(idUnidadEstrategica);
				sedeModificar.setUnidadEstrategicaServicio(UnidadEstrategica);

				if (dnSedes.actualizarSede(sedeModificar) != null) {
					cargarListaSedes();
					mensajes.mostrarMensaje("Modificación Exitosa", 1);
				} else {
					mensajes.mostrarMensaje("No se pudo realizar la modificación, valide con el administrador", 3);
				}

			}
		} catch (Exception e) {
			System.out.println("Error en el metodo registrarSede -->> " + e);
		}
	}

	public void onRowSelect() {
		idUnidadEstrategicaModif = "" + sedeSeleccionado.getUnidadEstrategicaServicio().getIdUnidad();
		sedeModificar = sedeSeleccionado;
	}

	public void limpiar() {
		sede = new Sede();
		sedeSeleccionado = new Sede();
		sedeModificar = new Sede();
		idUnidadEstrategica = "";
		idUnidadEstrategicaModif = "";
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

		if (dNUnidadEstrategica == null) {
			dNUnidadEstrategica = new DNUnidadEstrategica();
		}
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

	public List<SelectItem> getListUnidadEstrategica() {
		return listUnidadEstrategica;
	}

	public void setListUnidadEstrategica(List<SelectItem> listUnidadEstrategica) {
		this.listUnidadEstrategica = listUnidadEstrategica;
	}

	public String getIdUnidadEstrategica() {
		return idUnidadEstrategica;
	}

	public void setIdUnidadEstrategica(String idUnidadEstrategica) {
		this.idUnidadEstrategica = idUnidadEstrategica;
	}

	public String getIdUnidadEstrategicaModif() {
		return idUnidadEstrategicaModif;
	}

	public void setIdUnidadEstrategicaModif(String idUnidadEstrategicaModif) {
		this.idUnidadEstrategicaModif = idUnidadEstrategicaModif;
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
