package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTipoComputo;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.TipoComputo;

@ManagedBean(name = "MBTipoComputo")
@SessionScoped
public class MBTipoComputo implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNTipoComputo dnTipoComputos;
	List<TipoComputo> listTipoComputos;
	private TipoComputo tipoComputoSeleccionado;
	private TipoComputo tipoComputoModificar;
	private TipoComputo tipoComputo;

	public MBTipoComputo() {
		tipoComputo = new TipoComputo();
		tipoComputoSeleccionado = new TipoComputo();
		tipoComputoModificar = new TipoComputo();
		cargarListaTipoComputos();
	}

	public void cargarListaTipoComputos() {
		try {
			inicializarDelegados();

			listTipoComputos = dnTipoComputos.consultarAllTipoComputoActivos();
		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaTipoComputos -->> " + e);
		}
	}

	public void registrarTipoComputo() {
		try {
			inicializarDelegados();

			tipoComputo.setIdEstado(1);

			if (dnTipoComputos.crearTipoComputo(tipoComputo) != null) {
				limpiar();
				cargarListaTipoComputos();
				mensajes.mostrarMensaje("Registro Exitoso", 1);
			} else {
				mensajes.mostrarMensaje("El registro no se pudo realizar, valide con el administrador", 3);
			}

		} catch (Exception e) {
			System.out.println("Error en el metodo registrarTipoComputo -->> " + e);
		}
	}

	public void modificarTipoComputo() {
		try {
			inicializarDelegados();

			if (dnTipoComputos.actualizarTipoComputo(tipoComputoModificar) != null) {
				cargarListaTipoComputos();
				mensajes.mostrarMensaje("Modificación Exitosa", 1);
			} else {
				mensajes.mostrarMensaje("No se pudo realizar la modificación, valide con el administrador", 3);
			}
		} catch (Exception e) {
			System.out.println("Error en el metodo registrarTipoComputo -->> " + e);
		}
	}

	public void onRowSelect() {
		tipoComputoModificar = tipoComputoSeleccionado;
	}

	public void limpiar() {
		tipoComputo = new TipoComputo();
		tipoComputoSeleccionado = new TipoComputo();
		tipoComputoModificar = new TipoComputo();
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
		if (dnTipoComputos == null) {
			dnTipoComputos = new DNTipoComputo();
		}
	}

	public List<TipoComputo> getListTipoComputos() {
		return listTipoComputos;
	}

	public void setListTipoComputos(List<TipoComputo> listTipoComputos) {
		this.listTipoComputos = listTipoComputos;
	}

	public TipoComputo getTipoComputoSeleccionado() {
		return tipoComputoSeleccionado;
	}

	public void setTipoComputoSeleccionado(TipoComputo tipoComputoSeleccionado) {
		this.tipoComputoSeleccionado = tipoComputoSeleccionado;
	}

	public TipoComputo getTipoComputoModificar() {
		return tipoComputoModificar;
	}

	public void setTipoComputoModificar(TipoComputo tipoComputoModificar) {
		this.tipoComputoModificar = tipoComputoModificar;
	}

	public TipoComputo getTipoComputo() {
		return tipoComputo;
	}

	public void setTipoComputo(TipoComputo tipoComputo) {
		this.tipoComputo = tipoComputo;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

}
