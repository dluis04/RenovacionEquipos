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
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNComputador;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNDetalleListaCompu;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNListaChequeo;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNUnidadEstrategica;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Computador;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.ListaCheqeoComputador;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.UnidadEstrategicaServicio;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Usuario;

@ManagedBean(name = "MBSeleccionarEquiposUES")
@SessionScoped
public class MBSeleccionarEquiposUES implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNComputador dnComputadors;
	DNListaChequeo dNListaChequeo;
	DNDetalleListaCompu dNDetalleListaCompu;
	DNUnidadEstrategica dNUnidadEstrategica;

	List<Computador> listComputadors;
	List<Computador> listComputadorUES;
	List<SelectItem> listUnidad;
	List<ListaCheqeoComputador> listChequeo;

	private ListaCheqeoComputador listaChequeoSeleccionado;
	private Computador computadorSeleccionado;
	private Computador computadorSeleccionadoUES;
	private Computador computador;
	private Usuario usuario;

	public MBSeleccionarEquiposUES() {

		computador = new Computador();
		computadorSeleccionado = new Computador();
		computadorSeleccionadoUES = new Computador();
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		cargarListaCargarTodo();
	}

	public void cargarListaCargarTodo() {
		try {

			inicializarDelegados();
			listComputadors = dnComputadors.consultarComputadoresNuevosSeleccionLista();

			listUnidad = new ArrayList<>();
			for (UnidadEstrategicaServicio list : dNUnidadEstrategica.consultarAllUnidadEstrategicaServicioActivos()) {
				listUnidad.add(new SelectItem(list.getIdUnidad(), list.getNombre()));
			}

			listChequeo = dNListaChequeo.consultarAllListaCheqeoComputadorActivosNuevos();

		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaComputadors -->> " + e);
		}
	}

	public void registrarComputadoresUES() {
		try {
			inicializarDelegados();

		} catch (Exception e) {
			System.out.println("Error en el metodo registrarComputador -->> " + e);
		}
	}

	public void seleccionarComputadorSinUES() {

		if (computadorSeleccionado == null) {
			computadorSeleccionado = new Computador();
			mensajes.mostrarMensaje("Debe seleccionar un computador", 2);
		} else {
			listComputadorUES.add(computadorSeleccionado);
			listComputadors.remove(computadorSeleccionado);
		}

	}

	public void seleccionarComputadorSinUES2() {

//		if (computadorSeleccionadoUES == null) {
//			computadorSeleccionadoUES = new Computador();
//			mensajes.mostrarMensaje("Debe seleccionar un computador", 2);
//		} else {
//			listComputadorUES.add(computadorSeleccionadoUES);
//			listComputadors.remove(computadorSeleccionadoUES);
//		}

	}

	public void cancelarComputadorUES2() {
		computadorSeleccionadoUES = null;
		computadorSeleccionadoUES = new Computador();
	}

	public void cancelarComputadorUES() {
		computadorSeleccionado = null;
		computadorSeleccionado = new Computador();
	}

	public void limpiar() {
		computador = new Computador();
		computadorSeleccionado = new Computador();

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

		if (dnComputadors == null) {
			dnComputadors = new DNComputador();
		}

		if (dNListaChequeo == null) {
			dNListaChequeo = new DNListaChequeo();
		}

		if (dNDetalleListaCompu == null) {
			dNDetalleListaCompu = new DNDetalleListaCompu();
		}

		if (dNUnidadEstrategica == null) {
			dNUnidadEstrategica = new DNUnidadEstrategica();
		}

	}

	public Computador getComputadorSeleccionadoUES() {
		return computadorSeleccionadoUES;
	}

	public void setComputadorSeleccionadoUES(Computador computadorSeleccionadoUES) {
		this.computadorSeleccionadoUES = computadorSeleccionadoUES;
	}

	public List<Computador> getListComputadorUES() {
		return listComputadorUES;
	}

	public void setListComputadorUES(List<Computador> listComputadorUES) {
		this.listComputadorUES = listComputadorUES;
	}

	public List<SelectItem> getListUnidad() {
		return listUnidad;
	}

	public void setListUnidad(List<SelectItem> listUnidad) {
		this.listUnidad = listUnidad;
	}

	public List<ListaCheqeoComputador> getListChequeo() {
		return listChequeo;
	}

	public void setListChequeo(List<ListaCheqeoComputador> listChequeo) {
		this.listChequeo = listChequeo;
	}

	public ListaCheqeoComputador getListaChequeoSeleccionado() {
		return listaChequeoSeleccionado;
	}

	public void setListaChequeoSeleccionado(ListaCheqeoComputador listaChequeoSeleccionado) {
		this.listaChequeoSeleccionado = listaChequeoSeleccionado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Computador> getListComputadors() {
		return listComputadors;
	}

	public void setListComputadors(List<Computador> listComputadors) {
		this.listComputadors = listComputadors;
	}

	public Computador getComputadorSeleccionado() {
		return computadorSeleccionado;
	}

	public void setComputadorSeleccionado(Computador computadorSeleccionado) {
		this.computadorSeleccionado = computadorSeleccionado;
	}

	public Computador getComputador() {
		return computador;
	}

	public void setComputador(Computador computador) {
		this.computador = computador;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

}
