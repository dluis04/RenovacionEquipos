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

@ManagedBean(name = "MBConfigurarEquiposLista")
@SessionScoped
public class MBConfigurarEquiposLista implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNComputador dnComputadors;
	DNDetalleListaCompu dNDetalleListaCompu;

	List<Computador> listComputadors;
	List<DetalleListaComputo> listDetalleLista;

	private DetalleListaComputo detalleSeleccionado;
	private Computador computadorSeleccionado;
	private Computador computador;
	private Usuario usuario;

	public MBConfigurarEquiposLista() {
		computador = new Computador();
		computadorSeleccionado = new Computador();
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		cargarListaCargarTodo();
	}

	public void onRowSelect() {
		try {
			listDetalleLista = dNDetalleListaCompu
					.consultarAllDetalleListaComputo("" + computadorSeleccionado.getIdComputador());
		} catch (Exception e) {
			System.out.println("Error en el metodo onRowSelect configuracionEquipos -->> " + e);
		}
		
	}

	public void cargarListaCargarTodo() {
		try {
			inicializarDelegados();

			listComputadors = dnComputadors.consultarComputadoresNuevosUES();

		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaCargarTodo -->> " + e);
		}
	}

	public void registrarComputadoresUES() {
		try {
			inicializarDelegados();

		} catch (Exception e) {
			System.out.println("Error en el metodo registrarComputadoresUES -->> " + e);
		}
	}

	public void seleccionarComputadorSinUES() {
		try {

		} catch (Exception e) {
			System.out.println("Error en el metodo seleccionarComputadorSinUES -->> " + e);
		}

	}

	public void cancelarComputadorUES() {
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

		if (dNDetalleListaCompu == null) {
			dNDetalleListaCompu = new DNDetalleListaCompu();
		}

	}

	public List<DetalleListaComputo> getListDetalleLista() {
		return listDetalleLista;
	}

	public void setListDetalleLista(List<DetalleListaComputo> listDetalleLista) {
		this.listDetalleLista = listDetalleLista;
	}

	public DetalleListaComputo getDetalleSeleccionado() {
		return detalleSeleccionado;
	}

	public void setDetalleSeleccionado(DetalleListaComputo detalleSeleccionado) {
		this.detalleSeleccionado = detalleSeleccionado;
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
