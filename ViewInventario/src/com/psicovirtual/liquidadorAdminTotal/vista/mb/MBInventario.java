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
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNSede;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Ciudad;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Computador;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.DetalleInventario;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.DetalleInventarioHistorial;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Sede;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Usuario;

@ManagedBean(name = "MBInventario")
@SessionScoped
public class MBInventario implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNComputador dnComputador;
	DNDetalleInventario dNDetalleInventario;
	DNDetalleInventarioHistorial dNDetalleInventarioHistorial;
	DNSede dNSede;

	List<Computador> listComputadorNuevos;
	List<Computador> filterComputadorNuevos;
	List<DetalleInventario> listInventarioNuevo;
	List<DetalleInventario> filterInventarioNuevo;
	List<Sede> listSedes;
	List<Sede> filterSede;

	private Sede sedeSeleccionado;
	private Sede sedeSeleccionadoM;
	private Computador computadorSeleccionado;
	private DetalleInventario inventario;
	private DetalleInventario inventarioSeleccionado;
	private DetalleInventario modificarInventario;
	private DetalleInventarioHistorial inventarioHis;
	private Usuario usuario;

	public MBInventario() {
		sedeSeleccionado = new Sede();
		sedeSeleccionadoM = new Sede();
		Ciudad ciudad = new Ciudad();
		sedeSeleccionado.setCiudad(ciudad);
		sedeSeleccionadoM.setCiudad(ciudad);
		inventarioHis = new DetalleInventarioHistorial();
		computadorSeleccionado = new Computador();
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
			listSedes = dNSede.consultarAllSedeActivos();
		} catch (Exception e) {
			System.out.println("Error en el metodo cargarTablas -->> " + e);
		}
	}

	public void registrarInventarioCompuNuevo() {
		try {

			inicializarDelegados();
			Boolean isComputador = false;
			Boolean isSede = false;

			if (computadorSeleccionado == null) {
				computadorSeleccionado = new Computador();
				mensajes.mostrarMensaje("Debe seleccionar un computador", 2);
			} else {
				isComputador = true;
			}

			if (sedeSeleccionado == null) {
				sedeSeleccionado = new Sede();
				mensajes.mostrarMensaje("Debe seleccionar una sede", 2);
			} else {
				isSede = true;
			}

			if (isComputador && isSede) {
				inventario.setIdUsuarioReg(usuario.getIdUsuario());
				inventario.setComputador(computadorSeleccionado);
				inventario.setSede(sedeSeleccionado);
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
		sedeSeleccionado = new Sede();
		sedeSeleccionadoM = new Sede();
		Ciudad ciudad = new Ciudad();
		sedeSeleccionado.setCiudad(ciudad);
		sedeSeleccionadoM.setCiudad(ciudad);
		inventario = null;
		inventario = new DetalleInventario();
		inventarioHis = new DetalleInventarioHistorial();
	}

	public void limpiarComputadorNuevo() {
		computadorSeleccionado = null;
		computadorSeleccionado = new Computador();
		limpiarIsNull();
	}

	public void limpiarIsNull() {

		if (computadorSeleccionado == null) {
			computadorSeleccionado = new Computador();
		}

		if (sedeSeleccionado == null) {
			sedeSeleccionado = new Sede();
			Ciudad ciudad = new Ciudad();
			sedeSeleccionado.setCiudad(ciudad);
			sedeSeleccionadoM.setCiudad(ciudad);
		}

		if (sedeSeleccionadoM == null) {
			sedeSeleccionadoM = new Sede();
			Ciudad ciudad = new Ciudad();
			sedeSeleccionado.setCiudad(ciudad);
			sedeSeleccionadoM.setCiudad(ciudad);
		}
	}

	public void seleccionarSede() {
		if (sedeSeleccionado != null) {
			mensajes.mostrarMensaje("Sede seleccionada exitosamente", 1);
		} else {
			mensajes.mostrarMensaje("Debe seleccionar una Sede", 2);
		}
		limpiarIsNull();
	}

	public void seleccionarSedeM() {
		if (sedeSeleccionadoM != null) {
			mensajes.mostrarMensaje("Sede seleccionada exitosamente", 1);
		} else {
			mensajes.mostrarMensaje("Debe seleccionar una Sede", 2);
		}
		limpiarIsNull();
	}

	public void limpiarSede() {
		sedeSeleccionado = null;
		sedeSeleccionadoM = null;
		sedeSeleccionado = new Sede();
		sedeSeleccionadoM = new Sede();
		Ciudad ciudad = new Ciudad();
		sedeSeleccionado.setCiudad(ciudad);
		sedeSeleccionadoM.setCiudad(ciudad);
		limpiarIsNull();
	}

	public void seleccionarCompu() {
		if (computadorSeleccionado != null) {
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

		if (dNSede == null) {
			dNSede = new DNSede();
		}

	}

	public List<DetalleInventario> getFilterInventarioNuevo() {
		return filterInventarioNuevo;
	}

	public void setFilterInventarioNuevo(List<DetalleInventario> filterInventarioNuevo) {
		this.filterInventarioNuevo = filterInventarioNuevo;
	}

	public List<Computador> getFilterComputadorNuevos() {
		return filterComputadorNuevos;
	}

	public void setFilterComputadorNuevos(List<Computador> filterComputadorNuevos) {
		this.filterComputadorNuevos = filterComputadorNuevos;
	}

	public List<Sede> getFilterSede() {
		return filterSede;
	}

	public void setFilterSede(List<Sede> filterSede) {
		this.filterSede = filterSede;
	}

	public List<Sede> getListSedes() {
		return listSedes;
	}

	public void setListSedes(List<Sede> listSedes) {
		this.listSedes = listSedes;
	}

	public Sede getSedeSeleccionado() {

		if (sedeSeleccionado == null) {
			sedeSeleccionado = new Sede();
		}

		return sedeSeleccionado;
	}

	public void setSedeSeleccionado(Sede sedeSeleccionado) {
		this.sedeSeleccionado = sedeSeleccionado;
	}

	public Sede getSedeSeleccionadoM() {
		return sedeSeleccionadoM;
	}

	public void setSedeSeleccionadoM(Sede sedeSeleccionadoM) {
		this.sedeSeleccionadoM = sedeSeleccionadoM;
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

		if (computadorSeleccionado == null) {
			computadorSeleccionado = new Computador();
		}
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
