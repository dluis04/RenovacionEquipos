package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Computador;
import com.psicovirtual.procesos.modelo.ejb.session.SBComputadorLocal;

@ManagedBean(value = "DNComputador")
@ApplicationScoped
public class DNComputador {

	SBComputadorLocal sBComputadorLocal;

	public DNComputador() throws Exception {
		sBComputadorLocal = ServiceLocator.getInstance().obtenerServicio(
				Parametros.PREFIJO_JNDI + "SBComputador" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBComputadorLocal",
				SBComputadorLocal.class);
	}

	public Computador crearComputador(Computador nuevo) throws Exception {
		return sBComputadorLocal.crearComputador(nuevo);
	}

	public Computador actualizarComputador(Computador update) throws Exception {
		return sBComputadorLocal.actualizarComputador(update);
	}

	public Computador consultarDetalleComputador(String id) throws Exception {
		return sBComputadorLocal.consultarDetalleComputador(id);
	}

	public List<Computador> consultarAllComputadorActivos() throws Exception {
		return sBComputadorLocal.consultarAllComputadorActivos();
	}

	public List<Computador> consultarAllComputadorNuevos() throws Exception {
		return sBComputadorLocal.consultarAllComputadorNuevos();
	}

	public List<Computador> consultarComputadoresNuevosSeleccionLista() throws Exception {
		return sBComputadorLocal.consultarComputadoresNuevosSeleccionLista();
	}

}
