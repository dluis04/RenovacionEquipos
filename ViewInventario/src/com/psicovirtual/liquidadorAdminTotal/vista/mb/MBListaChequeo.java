package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNListaChequeo;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.ListaCheqeoComputador;

@ManagedBean(name = "MBListaChequeo")
@SessionScoped
public class MBListaChequeo implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNListaChequeo dnListaCheqeoComputadors;

	List<ListaCheqeoComputador> listListaChequeoComputadors;
	List<ListaCheqeoComputador> filterChequeo;

	private ListaCheqeoComputador listaChequeoComputadorSeleccionado;
	private ListaCheqeoComputador listaChequeoComputadorModificar;
	private ListaCheqeoComputador listaCheqeoComputador;
	private String tipoLista;
	private String tipoListaModi;

	public MBListaChequeo() {
		listaCheqeoComputador = new ListaCheqeoComputador();
		listaChequeoComputadorSeleccionado = new ListaCheqeoComputador();
		listaChequeoComputadorModificar = new ListaCheqeoComputador();
		cargarListaListaCheqeoComputadors();
	}

	public void cargarListaListaCheqeoComputadors() {
		try {
			inicializarDelegados();

			listListaChequeoComputadors = dnListaCheqeoComputadors.consultarAllListaCheqeoComputadorActivos();
		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaListaCheqeoComputadors -->> " + e);
		}

	}

	public void registrarListaCheqeoComputador() {
		try {
			inicializarDelegados();

			listaCheqeoComputador.setIdEstado(1);
			listaCheqeoComputador.setTipoLista(tipoLista);

			if (dnListaCheqeoComputadors.crearListaCheqeoComputador(listaCheqeoComputador) != null) {
				limpiar();
				cargarListaListaCheqeoComputadors();
				mensajes.mostrarMensaje("Registro Exitoso", 1);
			} else {
				mensajes.mostrarMensaje("El registro no se pudo realizar, valide con el administrador", 3);
			}

		} catch (Exception e) {
			System.out.println("Error en el metodo registrarListaCheqeoComputador -->> " + e);
		}
	}

	public void modificarListaCheqeoComputador() {
		try {
			inicializarDelegados();

			listaCheqeoComputador.setTipoLista(tipoListaModi);

			if (dnListaCheqeoComputadors.actualizarListaCheqeoComputador(listaChequeoComputadorModificar) != null) {
				cargarListaListaCheqeoComputadors();
				mensajes.mostrarMensaje("Modificación Exitosa", 1);
			} else {
				mensajes.mostrarMensaje("No se pudo realizar la modificación, valide con el administrador", 3);
			}
		} catch (Exception e) {
			System.out.println("Error en el metodo registrarListaCheqeoComputador -->> " + e);
		}
	}

	public void onRowSelect() {
		tipoListaModi = listaChequeoComputadorSeleccionado.getTipoLista();
		listaChequeoComputadorModificar = listaChequeoComputadorSeleccionado;
	}

	public void limpiar() {
		listaCheqeoComputador = new ListaCheqeoComputador();
		listaChequeoComputadorSeleccionado = new ListaCheqeoComputador();
		listaChequeoComputadorModificar = new ListaCheqeoComputador();
		tipoListaModi = "";
		tipoLista = "";
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
		if (dnListaCheqeoComputadors == null) {
			dnListaCheqeoComputadors = new DNListaChequeo();
		}
	}

	public List<ListaCheqeoComputador> getFilterChequeo() {
		return filterChequeo;
	}

	public void setFilterChequeo(List<ListaCheqeoComputador> filterChequeo) {
		this.filterChequeo = filterChequeo;
	}

	public String getTipoListaModi() {
		return tipoListaModi;
	}

	public void setTipoListaModi(String tipoListaModi) {
		this.tipoListaModi = tipoListaModi;
	}

	public String getTipoLista() {
		return tipoLista;
	}

	public void setTipoLista(String tipoLista) {
		this.tipoLista = tipoLista;
	}

	public List<ListaCheqeoComputador> getListListaChequeoComputadors() {
		return listListaChequeoComputadors;
	}

	public void setListListaChequeoComputadors(List<ListaCheqeoComputador> listListaChequeoComputadors) {
		this.listListaChequeoComputadors = listListaChequeoComputadors;
	}

	public ListaCheqeoComputador getListaChequeoComputadorSeleccionado() {
		return listaChequeoComputadorSeleccionado;
	}

	public void setListaChequeoComputadorSeleccionado(ListaCheqeoComputador listaChequeoComputadorSeleccionado) {
		this.listaChequeoComputadorSeleccionado = listaChequeoComputadorSeleccionado;
	}

	public ListaCheqeoComputador getListaChequeoComputadorModificar() {
		return listaChequeoComputadorModificar;
	}

	public void setListaChequeoComputadorModificar(ListaCheqeoComputador listaChequeoComputadorModificar) {
		this.listaChequeoComputadorModificar = listaChequeoComputadorModificar;
	}

	public ListaCheqeoComputador getListaCheqeoComputador() {
		return listaCheqeoComputador;
	}

	public void setListaCheqeoComputador(ListaCheqeoComputador listaCheqeoComputador) {
		this.listaCheqeoComputador = listaCheqeoComputador;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

}
