package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNComputador;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNDetalleInventario;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNDetalleInventarioHistorial;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Computador;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.DetalleInventario;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.DetalleInventarioHistorial;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Usuario;

@ManagedBean(name = "MBInventario")
@SessionScoped
public class MBInventario implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNComputador dnComputador;
	DNDetalleInventario dNDetalleInventario;
	DNDetalleInventarioHistorial dNDetalleInventarioHistorial;

	List<Computador> listComputadorNuevos;
	List<DetalleInventario> listInventarioNuevo;

	private Computador computadorSeleccionado;
	private Computador computadorSeleccionadoM;
	private DetalleInventario inventario;
	private DetalleInventario inventarioSeleccionado;
	private DetalleInventario modificarInventario;
	private DetalleInventarioHistorial inventarioHis;
	private Usuario usuario;

	public MBInventario() {
		inventarioHis = new DetalleInventarioHistorial();
		computadorSeleccionado = new Computador();
		computadorSeleccionadoM = new Computador();
		modificarInventario = new DetalleInventario();
		inventarioSeleccionado = new DetalleInventario();
		inventario = new DetalleInventario();
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		cargarTablas();
	}

	public void cargarTablas() {
		try {
			inicializarDelegados();
			listComputadorNuevos = dnComputador.consultarAllComputadorNuevos();
			listInventarioNuevo = dNDetalleInventario.consultarAllDetalleInventarioComputadorNuevos();
		} catch (Exception e) {
			System.out.println("Error en el metodo cargarTablas -->> " + e);
		}
	}

	public void registrarInventarioCompuNuevo() {
		try {

			inicializarDelegados();
			Boolean isUbicacion = false;
			Boolean isComputador = false;

			if (computadorSeleccionado == null) {
				computadorSeleccionado = new Computador();
			} else {
				isComputador = true;
			}

			if (isComputador && isUbicacion) {
				inventario.setIdUsuarioReg(usuario.getIdUsuario());
				inventario.setComputador(computadorSeleccionado);
				inventario.setIdEstado(1);
				dNDetalleInventario.crearDetalleInventario(inventario);
				registrarHistorialInventario(inventario);
				limpiar();
				mensajes.mostrarMensaje("Regisro Exitosa", 1);
			}

		} catch (Exception e) {
			System.out.println("Error en el metodo registrarInventarioCompuNuevo -->> " + e);
		}
	}

	public void registrarHistorialInventario(DetalleInventario inventario) {
		inventarioHis.setIdComputador(inventario.getComputador().getIdComputador());
		inventarioHis.setIdUsuarioReg(inventario.getIdUsuarioReg());
		inventarioHis.setCausaTecnica(inventario.getCausaTecnica());
		inventarioHis.setIdEstado(1);
		inventarioHis.setSerialInventario(inventario.getSerialInventario());
		inventarioHis.setPlacaInventario(inventario.getPlacaInventario());
		inventarioHis.setSegundaPlaca(inventario.getSegundaPlaca());
		inventarioHis.setObservacion(inventario.getObservacion());

		try {
			dNDetalleInventarioHistorial.crearDetalleInventarioHistorial(inventarioHis);
		} catch (Exception e) {
			System.out.println("Error en el metodo registrarHistorialInventario -->> " + e);
		}
	}

	public void modificarInventarioCompuNuevo() {
		try {
			inicializarDelegados();
			dNDetalleInventario.actualizarDetalleInventario(modificarInventario);
			registrarHistorialInventario(modificarInventario);
			inventarioSeleccionado = new DetalleInventario();
			modificarInventario = new DetalleInventario();
			mensajes.mostrarMensaje("Modificación Exitosa", 1);
			limpiar();
		} catch (Exception e) {
			System.out.println("Error en el metodo modificarInventarioCompuNuevo -->> " + e);
		}
	}

	public void onRowSelect() {
		modificarInventario = inventarioSeleccionado;
	}

	public void limpiar() {
		cargarTablas();
		computadorSeleccionado = new Computador();
		computadorSeleccionadoM = new Computador();

		inventario = null;
		inventario = new DetalleInventario();
		inventarioHis = new DetalleInventarioHistorial();
	}

	public void limpiarComputadorNuevo() {
		computadorSeleccionado = null;
		computadorSeleccionado = new Computador();
		computadorSeleccionadoM = null;
		computadorSeleccionadoM = new Computador();
		limpiarIsNull();
	}

	public void limpiarIsNull() {

		if (computadorSeleccionado == null) {
			computadorSeleccionado = new Computador();
		}

		if (computadorSeleccionadoM == null) {
			computadorSeleccionadoM = new Computador();
		}

	}

	public void seleccionarCompu() {
		if (computadorSeleccionado != null || computadorSeleccionadoM != null) {
			mensajes.mostrarMensaje("Computador seleccionado exitosamente", 1);
		} else {
			mensajes.mostrarMensaje("Debe seleccionar un computador", 2);
		}
		limpiarIsNull();
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

		if (dNDetalleInventario == null) {
			dNDetalleInventario = new DNDetalleInventario();
		}

		if (dNDetalleInventarioHistorial == null) {
			dNDetalleInventarioHistorial = new DNDetalleInventarioHistorial();
		}

	}

	public DetalleInventario getInventarioSeleccionado() {
		return inventarioSeleccionado;
	}

	public void setInventarioSeleccionado(DetalleInventario inventarioSeleccionado) {
		this.inventarioSeleccionado = inventarioSeleccionado;
	}

	public List<DetalleInventario> getListInventarioNuevo() {
		return listInventarioNuevo;
	}

	public void setListInventarioNuevo(List<DetalleInventario> listInventarioNuevo) {
		this.listInventarioNuevo = listInventarioNuevo;
	}

	public DetalleInventarioHistorial getInventarioHis() {
		return inventarioHis;
	}

	public void setInventarioHis(DetalleInventarioHistorial inventarioHis) {
		this.inventarioHis = inventarioHis;
	}

	public Computador getComputadorSeleccionadoM() {
		return computadorSeleccionadoM;
	}

	public void setComputadorSeleccionadoM(Computador computadorSeleccionadoM) {
		this.computadorSeleccionadoM = computadorSeleccionadoM;
	}

	public DetalleInventario getModificarInventario() {
		return modificarInventario;
	}

	public void setModificarInventario(DetalleInventario modificarInventario) {
		this.modificarInventario = modificarInventario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public DetalleInventario getInventario() {
		return inventario;
	}

	public void setInventario(DetalleInventario inventario) {
		this.inventario = inventario;
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
