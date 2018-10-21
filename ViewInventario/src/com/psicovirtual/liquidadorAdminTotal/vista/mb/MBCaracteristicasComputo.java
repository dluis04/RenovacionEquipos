package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNCaracteristicasComputo;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.CaracteristicasComputo;

@ManagedBean(name = "MBCaracteristicasComputo")
@SessionScoped
public class MBCaracteristicasComputo implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNCaracteristicasComputo dnCaracteristicasComputos;
	List<CaracteristicasComputo> listCaracteristicasComputos;
	private CaracteristicasComputo caracteristicasComputoSeleccionado;
	private CaracteristicasComputo caracteristicasComputoModificar;
	private CaracteristicasComputo caracteristicasComputo;

	public MBCaracteristicasComputo() {
		caracteristicasComputo = new CaracteristicasComputo();
		caracteristicasComputoSeleccionado = new CaracteristicasComputo();
		caracteristicasComputoModificar = new CaracteristicasComputo();
		cargarListaCaracteristicasComputos();
	}

	public void cargarListaCaracteristicasComputos() {
		try {
			inicializarDelegados();

			listCaracteristicasComputos = dnCaracteristicasComputos.consultarAllCaracteristicasComputoActivos();
		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaCaracteristicasComputos -->> " + e);
		}
	}

	public void registrarCaracteristicasComputo() {
		try {
			inicializarDelegados();

			caracteristicasComputo.setIdEstado(1);

			if (dnCaracteristicasComputos.crearCaracteristicasComputo(caracteristicasComputo) != null) {
				limpiar();
				cargarListaCaracteristicasComputos();
				mensajes.mostrarMensaje("Registro Exitoso", 1);
			} else {
				mensajes.mostrarMensaje("El registro no se pudo realizar, valide con el administrador", 3);
			}
			
			
		} catch (Exception e) {
			System.out.println("Error en el metodo registrarCaracteristicasComputo -->> " + e);
		}
	}

	public void modificarCaracteristicasComputo() {
		try {
			inicializarDelegados();

			if (dnCaracteristicasComputos.actualizarCaracteristicasComputo(caracteristicasComputoModificar) != null) {
				cargarListaCaracteristicasComputos();
				mensajes.mostrarMensaje("Modificación Exitosa", 1);
			} else {
				mensajes.mostrarMensaje("No se pudo realizar la modificación, valide con el administrador", 3);
			}
		} catch (Exception e) {
			System.out.println("Error en el metodo registrarCaracteristicasComputo  -->> " + e);
		}
	}

	public void onRowSelect() {
		caracteristicasComputoModificar = caracteristicasComputoSeleccionado;
	}

	public void limpiar() {
		caracteristicasComputo = new CaracteristicasComputo();
		caracteristicasComputoSeleccionado = new CaracteristicasComputo();
		caracteristicasComputoModificar = new CaracteristicasComputo();
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
		if (dnCaracteristicasComputos == null) {
			dnCaracteristicasComputos = new DNCaracteristicasComputo();
		}
	}

	public List<CaracteristicasComputo> getListCaracteristicasComputos() {
		return listCaracteristicasComputos;
	}

	public void setListCaracteristicasComputos(List<CaracteristicasComputo> listCaracteristicasComputos) {
		this.listCaracteristicasComputos = listCaracteristicasComputos;
	}

	public CaracteristicasComputo getCaracteristicasComputoSeleccionado() {
		return caracteristicasComputoSeleccionado;
	}

	public void setCaracteristicasComputoSeleccionado(CaracteristicasComputo caracteristicasComputoSeleccionado) {
		this.caracteristicasComputoSeleccionado = caracteristicasComputoSeleccionado;
	}

	public CaracteristicasComputo getCaracteristicasComputoModificar() {
		return caracteristicasComputoModificar;
	}

	public void setCaracteristicasComputoModificar(CaracteristicasComputo caracteristicasComputoModificar) {
		this.caracteristicasComputoModificar = caracteristicasComputoModificar;
	}

	public CaracteristicasComputo getCaracteristicasComputo() {
		return caracteristicasComputo;
	}

	public void setCaracteristicasComputo(CaracteristicasComputo caracteristicasComputo) {
		this.caracteristicasComputo = caracteristicasComputo;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

}
