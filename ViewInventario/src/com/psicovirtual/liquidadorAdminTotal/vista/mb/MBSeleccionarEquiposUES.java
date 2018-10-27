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
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.DetalleListaComputo;
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
	ArrayList<Computador> listComputadorUES;
	List<SelectItem> listUnidad;
	List<ListaCheqeoComputador> listChequeo;

	private ListaCheqeoComputador listaChequeoSeleccionado;
	private Computador computadorSeleccionado;
	private Computador computadorSeleccionadoUES;
	private Computador computador;
	private Usuario usuario;
	private String idUnidad;

	public MBSeleccionarEquiposUES() {

		idUnidad = "";
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
			listComputadorUES = new ArrayList<>();
			listUnidad = new ArrayList<>();
			for (UnidadEstrategicaServicio list : dNUnidadEstrategica.consultarAllUnidadEstrategicaServicioActivos()) {
				listUnidad.add(new SelectItem(list.getIdUnidad(), list.getNombre()));
			}

		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaComputadors -->> " + e);
		}
	}

	public void registrarComputadoresUES() {
		try {
			inicializarDelegados();
			Boolean isUES = false;
			for (Computador computadorUES : listComputadorUES) {
				dnComputadors.actualizarComputador(computadorUES);

				listChequeo = dNListaChequeo.consultarAllListaChequeoUESOrdenASCNuevo(
						"" + computadorUES.getUnidadEstrategicaServicio().getIdUnidad());
				DetalleListaComputo detalle = null;
				for (ListaCheqeoComputador listaChequeo : listChequeo) {
					detalle = new DetalleListaComputo();
					detalle.setActividad("NUEVO");
					detalle.setListaCheqeoComputador(listaChequeo);
					detalle.setCheckList(0);
					detalle.setComputador(computadorUES);

					dNDetalleListaCompu.crearDetalleListaComputo(detalle);
				}

				isUES = true;
			}

			if (isUES) {
				limpiar();
				cargarListaCargarTodo();
				mensajes.mostrarMensaje("Cambios guardados exitosamente", 1);
			}

		} catch (Exception e) {
			System.out.println("Error en el metodo registrarComputadoresUES -->> " + e);
		}
	}

	public void seleccionarComputadorSinUES() {
		try {
			if (computadorSeleccionado == null) {
				computadorSeleccionado = new Computador();
				mensajes.mostrarMensaje("Debe seleccionar un computador", 2);
			} else {
				if (!(idUnidad == null)) {
					computadorSeleccionado.setUnidadEstrategicaServicio(
							dNUnidadEstrategica.consultarDetalleUnidadEstrategicaServicio(idUnidad));
					listComputadorUES.add(computadorSeleccionado);
					listComputadors.remove(computadorSeleccionado);
					computadorSeleccionado = new Computador();
				} else {
					mensajes.mostrarMensaje("Debe seleccionar una unidad estrategica de servicio", 1);
				}
			}

		} catch (Exception e) {
			System.out.println("Error en el metodo seleccionarComputadorSinUES -->> " + e);
		}

	}

	public void cancelarComputadorUES() {

		if (computadorSeleccionadoUES == null) {
			computadorSeleccionadoUES = new Computador();
			mensajes.mostrarMensaje("Debe seleccionar un computador para cancelar", 2);
		} else {
			listComputadorUES.remove(computadorSeleccionadoUES);
			listComputadors.add(computadorSeleccionadoUES);
			computadorSeleccionadoUES = null;
			computadorSeleccionadoUES = new Computador();
		}
	}

	public void limpiar() {
		computador = new Computador();
		computadorSeleccionado = new Computador();
		computadorSeleccionadoUES = new Computador();
		idUnidad = "";

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

	public String getIdUnidad() {
		return idUnidad;
	}

	public void setIdUnidad(String idUnidad) {
		this.idUnidad = idUnidad;
	}

	public Computador getComputadorSeleccionadoUES() {
		return computadorSeleccionadoUES;
	}

	public void setComputadorSeleccionadoUES(Computador computadorSeleccionadoUES) {
		this.computadorSeleccionadoUES = computadorSeleccionadoUES;
	}

	public ArrayList<Computador> getListComputadorUES() {
		return listComputadorUES;
	}

	public void setListComputadorUES(ArrayList<Computador> listComputadorUES) {
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
