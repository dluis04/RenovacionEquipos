package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNModeloComputo;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.ModeloComputo;

@ManagedBean(name = "MBModeloComputo")
@SessionScoped
public class MBModeloComputo implements Serializable {


	MBMensajes mensajes = new MBMensajes();
	DNModeloComputo dnModeloComputos;
	List<ModeloComputo> listModeloComputos;
	private ModeloComputo modeloComputoSeleccionado;
	private ModeloComputo modeloComputoModificar;
	private ModeloComputo modeloComputo;

	public MBModeloComputo() {
		modeloComputo = new ModeloComputo();
		modeloComputoSeleccionado = new ModeloComputo();
		modeloComputoModificar = new ModeloComputo();
		cargarListaModeloComputos();
	}

	public void cargarListaModeloComputos() {
		try {
			inicializarDelegados();

			listModeloComputos = dnModeloComputos.consultarAllModeloComputoActivos();
		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaModeloComputos -->> " + e);
		}
	}

	public void registrarModeloComputo() {
		try {
			inicializarDelegados();

				modeloComputo.setIdEstado(1);

				if (dnModeloComputos.crearModeloComputo(modeloComputo) != null) {
					limpiar();
					cargarListaModeloComputos();
					mensajes.mostrarMensaje("Registro Exitoso", 1);
				} else {
					mensajes.mostrarMensaje("El registro no se pudo realizar, valide con el administrador", 3);
				}
			 

		} catch (Exception e) {
			System.out.println("Error en el metodo registrarModeloComputo -->> " + e);
		}
	}

	public void modificarModeloComputo() {
		try {
			inicializarDelegados();

			if (dnModeloComputos.actualizarModeloComputo(modeloComputoModificar) != null) {
				cargarListaModeloComputos();
				mensajes.mostrarMensaje("Modificación Exitosa", 1);
			} else {
				mensajes.mostrarMensaje("No se pudo realizar la modificación, valide con el administrador", 3);
			}
		} catch (Exception e) {
			System.out.println("Error en el metodo registrarModeloComputo -->> " + e);
		}
	}

	public void onRowSelect() {
		modeloComputoModificar = modeloComputoSeleccionado;
	}

	public void limpiar() {
		modeloComputo = new ModeloComputo();
		modeloComputoSeleccionado = new ModeloComputo();
		modeloComputoModificar = new ModeloComputo();
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
		if (dnModeloComputos == null) {
			dnModeloComputos = new DNModeloComputo();
		}
	}

	public List<ModeloComputo> getListModeloComputos() {
		return listModeloComputos;
	}

	public void setListModeloComputos(List<ModeloComputo> listModeloComputos) {
		this.listModeloComputos = listModeloComputos;
	}

	public ModeloComputo getModeloComputoSeleccionado() {
		return modeloComputoSeleccionado;
	}

	public void setModeloComputoSeleccionado(ModeloComputo modeloComputoSeleccionado) {
		this.modeloComputoSeleccionado = modeloComputoSeleccionado;
	}

	public ModeloComputo getModeloComputoModificar() {
		return modeloComputoModificar;
	}

	public void setModeloComputoModificar(ModeloComputo modeloComputoModificar) {
		this.modeloComputoModificar = modeloComputoModificar;
	}

	public ModeloComputo getModeloComputo() {
		return modeloComputo;
	}

	public void setModeloComputo(ModeloComputo modeloComputo) {
		this.modeloComputo = modeloComputo;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

}
