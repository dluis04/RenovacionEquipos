package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNSistemaOperativo;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.SistemaOperativo;

@ManagedBean(name = "MBSistemaOperativo")
@SessionScoped
public class MBSistemaOperativo implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNSistemaOperativo dnSistemaOperativos;
	List<SistemaOperativo> listSistemaOperativos;
	private SistemaOperativo sistemaOperativoSeleccionado;
	private SistemaOperativo sistemaOperativoModificar;
	private SistemaOperativo sistemaOperativo;

	public MBSistemaOperativo() {
		sistemaOperativo = new SistemaOperativo();
		sistemaOperativoSeleccionado = new SistemaOperativo();
		sistemaOperativoModificar = new SistemaOperativo();
		cargarListaSistemaOperativos();
	}

	public void cargarListaSistemaOperativos() {
		try {
			inicializarDelegados();

			listSistemaOperativos = dnSistemaOperativos.consultarAllSistemaOperativoActivos();
		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaSistemaOperativos -->> " + e);
		}
	}

	public void registrarSistemaOperativo() {
		try {
			inicializarDelegados();

			sistemaOperativo.setIdEstado(1);

			if (dnSistemaOperativos.crearSistemaOperativo(sistemaOperativo) != null) {
				limpiar();
				cargarListaSistemaOperativos();
				mensajes.mostrarMensaje("Registro Exitoso", 1);
			} else {
				mensajes.mostrarMensaje("El registro no se pudo realizar, valide con el administrador", 3);
			}

		} catch (Exception e) {
			System.out.println("Error en el metodo registrarSistemaOperativo -->> " + e);
		}
	}

	public void modificarSistemaOperativo() {
		try {
			inicializarDelegados();

			if (dnSistemaOperativos.actualizarSistemaOperativo(sistemaOperativoModificar) != null) {
				cargarListaSistemaOperativos();
				mensajes.mostrarMensaje("Modificación Exitosa", 1);
			} else {
				mensajes.mostrarMensaje("No se pudo realizar la modificación, valide con el administrador", 3);
			}
		} catch (Exception e) {
			System.out.println("Error en el metodo registrarSistemaOperativo -->> " + e);
		}
	}

	public void onRowSelect() {
		sistemaOperativoModificar = sistemaOperativoSeleccionado;
	}

	public void limpiar() {
		sistemaOperativo = new SistemaOperativo();
		sistemaOperativoSeleccionado = new SistemaOperativo();
		sistemaOperativoModificar = new SistemaOperativo();
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
		if (dnSistemaOperativos == null) {
			dnSistemaOperativos = new DNSistemaOperativo();
		}
	}

	public List<SistemaOperativo> getListSistemaOperativos() {
		return listSistemaOperativos;
	}

	public void setListSistemaOperativos(List<SistemaOperativo> listSistemaOperativos) {
		this.listSistemaOperativos = listSistemaOperativos;
	}

	public SistemaOperativo getSistemaOperativoSeleccionado() {
		return sistemaOperativoSeleccionado;
	}

	public void setSistemaOperativoSeleccionado(SistemaOperativo sistemaOperativoSeleccionado) {
		this.sistemaOperativoSeleccionado = sistemaOperativoSeleccionado;
	}

	public SistemaOperativo getSistemaOperativoModificar() {
		return sistemaOperativoModificar;
	}

	public void setSistemaOperativoModificar(SistemaOperativo sistemaOperativoModificar) {
		this.sistemaOperativoModificar = sistemaOperativoModificar;
	}

	public SistemaOperativo getSistemaOperativo() {
		return sistemaOperativo;
	}

	public void setSistemaOperativo(SistemaOperativo sistemaOperativo) {
		this.sistemaOperativo = sistemaOperativo;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

}
