package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FlowEvent;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNCaracteristicasComputo;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNComputador;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNModeloComputo;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNOperacionServicio;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNSistemaOperativo;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTipoComputo;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.CaracteristicasComputo;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Computador;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.ModeloComputo;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.OperacionServicio;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.SistemaOperativo;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.TipoComputo;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Usuario;

@ManagedBean(name = "MBComputador")
@SessionScoped
public class MBComputador implements Serializable {

	MBMensajes mensajes = new MBMensajes();
	DNComputador dnComputadors;
	DNModeloComputo dNModeloComputo;
	DNTipoComputo dNTipoComputo;
	DNCaracteristicasComputo dNCaracteristicasComputo;
	DNSistemaOperativo dNSistemaOperativo;
	DNOperacionServicio dnOperacionServicios;

	List<Computador> listComputadors;
	List<ModeloComputo> listModeloComputo;
	List<CaracteristicasComputo> listCaracteristicas;
	List<TipoComputo> listTipoComputo;
	List<SistemaOperativo> listSistemaOperativo;
	List<OperacionServicio> listOperacionServicios;

	String idEstadoCompuModi;
	String idEstadoCompu;

	private ModeloComputo modeloComputoSeleccionado;
	private OperacionServicio operacionServicioSeleccionado;
	private CaracteristicasComputo caracteristicasComputoSeleccionado;
	private TipoComputo tipoComputoSeleccionado;
	private SistemaOperativo sistemaOperativoSeleccionado;
	private Computador computadorSeleccionado;
	private Computador computadorModificar;
	private Computador computador;

	private boolean skip;
	private Usuario usuario;

	public MBComputador() {

		computador = new Computador();
		computadorSeleccionado = new Computador();
		computadorModificar = new Computador();

		modeloComputoSeleccionado = new ModeloComputo();
		caracteristicasComputoSeleccionado = new CaracteristicasComputo();
		tipoComputoSeleccionado = new TipoComputo();
		sistemaOperativoSeleccionado = new SistemaOperativo();
		operacionServicioSeleccionado = new OperacionServicio();
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

		cargarListaCargarTodo();
		cargarListaComputadors();

	}

	public void cargarListaComputadors() {
		try {
			inicializarDelegados();
			listComputadors = dnComputadors.consultarAllComputadorActivos();
		} catch (Exception e) {
			listComputadors = new ArrayList<Computador>();
			System.out.println("Error en el metodo cargarListaComputadors -->> " + e);
		}
	}

	public void cargarListaCargarTodo() {
		try {
			inicializarDelegados();
			listModeloComputo = dNModeloComputo.consultarAllModeloComputoActivos();
			listTipoComputo = dNTipoComputo.consultarAllTipoComputoActivos();
			listCaracteristicas = dNCaracteristicasComputo.consultarAllCaracteristicasComputoActivos();
			listSistemaOperativo = dNSistemaOperativo.consultarAllSistemaOperativoActivos();
			listOperacionServicios = dnOperacionServicios.consultarAllOperacionServicioActivos();
		} catch (Exception e) {
			System.out.println("Error en el metodo cargarListaComputadors -->> " + e);
		}
	}

	public void registrarComputador() {
		try {
			inicializarDelegados();

			Date fecha = new Date();
			Timestamp time = new Timestamp(fecha.getTime());

			computador.setIdEstado(1);

			if (modeloComputoSeleccionado.getIdModelo() != 0 || operacionServicioSeleccionado.getIdServicio() != 0
					|| caracteristicasComputoSeleccionado.getIdCaracteristicas() != 0
					|| tipoComputoSeleccionado.getIdTipo() != 0 || sistemaOperativoSeleccionado.getIdSistema() != 0) {

				computador.setIdEstadoCompu(Integer.parseInt(idEstadoCompu));
				computador.setOperacionServicio(operacionServicioSeleccionado);
				computador.setModeloComputo(modeloComputoSeleccionado);
				computador.setCaracteristicasComputo(caracteristicasComputoSeleccionado);
				computador.setTipoComputo(tipoComputoSeleccionado);
				computador.setSistemaOperativo(sistemaOperativoSeleccionado);
				computador.setIdUsuarioReg(usuario.getIdUsuario());
				computador.setFechaReg(time);

				if (dnComputadors.crearComputador(computador) != null) {
					limpiar();
					cargarListaComputadors();
					mensajes.mostrarMensaje("Registro Exitoso", 1);
				} else {
					mensajes.mostrarMensaje("El registro no se pudo realizar, valide con el administrador", 3);
				}

			} else {
				mensajes.mostrarMensaje("Debe de seleccionar la información complentaria del computador", 2);
			}

		} catch (Exception e) {
			System.out.println("Error en el metodo registrarComputador -->> " + e);
		}
	}

	public void modificarComputador() {
		try {
			inicializarDelegados();

			Date fecha = new Date();
			Timestamp time = new Timestamp(fecha.getTime());

			computador.setIdEstadoCompu(Integer.parseInt(idEstadoCompuModi));
			computador.setIdUsuarioReg(usuario.getIdUsuario());
			computador.setFechaMod(time);
			if (dnComputadors.actualizarComputador(computadorModificar) != null) {
				cargarListaComputadors();
				mensajes.mostrarMensaje("Modificación Exitosa", 1);
			} else {
				mensajes.mostrarMensaje("No se pudo realizar la modificación, valide con el administrador", 3);
			}
		} catch (Exception e) {
			System.out.println("Error en el metodo registrarComputador -->> " + e);
		}
	}

	public void onRowSelect() {
		idEstadoCompuModi = "" + computadorSeleccionado.getIdEstadoCompu();
		computadorModificar = computadorSeleccionado;
	}

	public void limpiar() {
		computador = new Computador();
		computadorSeleccionado = new Computador();
		computadorModificar = new Computador();
		modeloComputoSeleccionado = new ModeloComputo();
		caracteristicasComputoSeleccionado = new CaracteristicasComputo();
		tipoComputoSeleccionado = new TipoComputo();
		sistemaOperativoSeleccionado = new SistemaOperativo();
		operacionServicioSeleccionado = new OperacionServicio();
		idEstadoCompuModi = "";
		idEstadoCompu = "";
	}

	public void limpiarIsNull() {

		if (modeloComputoSeleccionado == null) {
			modeloComputoSeleccionado = new ModeloComputo();
		}

		if (caracteristicasComputoSeleccionado == null) {
			caracteristicasComputoSeleccionado = new CaracteristicasComputo();
		}

		if (tipoComputoSeleccionado == null) {
			tipoComputoSeleccionado = new TipoComputo();
		}

		if (sistemaOperativoSeleccionado == null) {
			sistemaOperativoSeleccionado = new SistemaOperativo();
		}

	}

	public void limpiarTipoModelo() {
		modeloComputoSeleccionado = null;
		modeloComputoSeleccionado = new ModeloComputo();
		limpiarIsNull();
	}

	public void limpiarCaracteristica() {
		caracteristicasComputoSeleccionado = null;
		caracteristicasComputoSeleccionado = new CaracteristicasComputo();
		limpiarIsNull();
	}

	public void limpiarTipo() {
		tipoComputoSeleccionado = null;
		tipoComputoSeleccionado = new TipoComputo();
		limpiarIsNull();
	}

	public void limpiarSistema() {
		sistemaOperativoSeleccionado = null;
		sistemaOperativoSeleccionado = new SistemaOperativo();
		limpiarIsNull();
	}

	public void limpiarOperacion() {
		operacionServicioSeleccionado = null;
		operacionServicioSeleccionado = new OperacionServicio();
	}

	public void seleccionarSistema() {
		if (sistemaOperativoSeleccionado != null) {
			mensajes.mostrarMensaje("Sistema Operativo seleccionado exitosamente", 1);
		} else {
			mensajes.mostrarMensaje("Debe seleccionar un Sistema Operativo", 2);
		}
		limpiarIsNull();
	}

	public void seleccionarTipoCompu() {
		if (tipoComputoSeleccionado != null) {
			mensajes.mostrarMensaje("Tipo computador seleccionado exitosamente", 1);
		} else {
			mensajes.mostrarMensaje("Debe seleccionar un Tipo computador", 2);
		}
		limpiarIsNull();
	}

	public void seleccionarTipoModelo() {
		if (modeloComputoSeleccionado != null) {
			mensajes.mostrarMensaje("Tipo modelo seleccionado exitosamente", 1);
		} else {
			mensajes.mostrarMensaje("Debe seleccionar un Tipo modelo", 2);
		}
		limpiarIsNull();
	}

	public void seleccionarCaracteristica() {

		if (caracteristicasComputoSeleccionado != null) {
			mensajes.mostrarMensaje("Caracteristica seleccionada exitosamente", 1);
		} else {
			mensajes.mostrarMensaje("Debe seleccionar una caracteristica", 2);
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
		if (dnComputadors == null) {
			dnComputadors = new DNComputador();
		}

		if (dNModeloComputo == null) {
			dNModeloComputo = new DNModeloComputo();
		}

		if (dNTipoComputo == null) {
			dNTipoComputo = new DNTipoComputo();
		}

		if (dNCaracteristicasComputo == null) {
			dNCaracteristicasComputo = new DNCaracteristicasComputo();
		}

		if (dNSistemaOperativo == null) {
			dNSistemaOperativo = new DNSistemaOperativo();
		}

		if (dnOperacionServicios == null) {
			dnOperacionServicios = new DNOperacionServicio();
		}

	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	public String getIdEstadoCompuModi() {
		return idEstadoCompuModi;
	}

	public void setIdEstadoCompuModi(String idEstadoCompuModi) {
		this.idEstadoCompuModi = idEstadoCompuModi;
	}

	public String getIdEstadoCompu() {
		return idEstadoCompu;
	}

	public void setIdEstadoCompu(String idEstadoCompu) {
		this.idEstadoCompu = idEstadoCompu;
	}

	public List<OperacionServicio> getListOperacionServicios() {
		return listOperacionServicios;
	}

	public void setListOperacionServicios(List<OperacionServicio> listOperacionServicios) {
		this.listOperacionServicios = listOperacionServicios;
	}

	public OperacionServicio getOperacionServicioSeleccionado() {
		return operacionServicioSeleccionado;
	}

	public void setOperacionServicioSeleccionado(OperacionServicio operacionServicioSeleccionado) {
		this.operacionServicioSeleccionado = operacionServicioSeleccionado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public CaracteristicasComputo getCaracteristicasComputoSeleccionado() {
		return caracteristicasComputoSeleccionado;
	}

	public void setCaracteristicasComputoSeleccionado(CaracteristicasComputo caracteristicasComputoSeleccionado) {
		this.caracteristicasComputoSeleccionado = caracteristicasComputoSeleccionado;
	}

	public ModeloComputo getModeloComputoSeleccionado() {
		return modeloComputoSeleccionado;
	}

	public void setModeloComputoSeleccionado(ModeloComputo modeloComputoSeleccionado) {
		this.modeloComputoSeleccionado = modeloComputoSeleccionado;
	}

	public TipoComputo getTipoComputoSeleccionado() {
		return tipoComputoSeleccionado;
	}

	public void setTipoComputoSeleccionado(TipoComputo tipoComputoSeleccionado) {
		this.tipoComputoSeleccionado = tipoComputoSeleccionado;
	}

	public SistemaOperativo getSistemaOperativoSeleccionado() {
		return sistemaOperativoSeleccionado;
	}

	public void setSistemaOperativoSeleccionado(SistemaOperativo sistemaOperativoSeleccionado) {
		this.sistemaOperativoSeleccionado = sistemaOperativoSeleccionado;
	}

	public List<ModeloComputo> getListModeloComputo() {
		return listModeloComputo;
	}

	public void setListModeloComputo(List<ModeloComputo> listModeloComputo) {
		this.listModeloComputo = listModeloComputo;
	}

	public List<CaracteristicasComputo> getListCaracteristicas() {
		return listCaracteristicas;
	}

	public void setListCaracteristicas(List<CaracteristicasComputo> listCaracteristicas) {
		this.listCaracteristicas = listCaracteristicas;
	}

	public List<TipoComputo> getListTipoComputo() {
		return listTipoComputo;
	}

	public void setListTipoComputo(List<TipoComputo> listTipoComputo) {
		this.listTipoComputo = listTipoComputo;
	}

	public List<SistemaOperativo> getListSistemaOperativo() {
		return listSistemaOperativo;
	}

	public void setListSistemaOperativo(List<SistemaOperativo> listSistemaOperativo) {
		this.listSistemaOperativo = listSistemaOperativo;
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

	public Computador getComputadorModificar() {
		return computadorModificar;
	}

	public void setComputadorModificar(Computador computadorModificar) {
		this.computadorModificar = computadorModificar;
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
