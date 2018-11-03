package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.ListaCheqeoComputador;
import com.psicovirtual.procesos.modelo.ejb.session.SBListaChequeoComputadorLocal;

@ManagedBean(value = "DNListaChequeo")
@ApplicationScoped
public class DNListaChequeo {

	SBListaChequeoComputadorLocal sBListaCheqeoComputadorLocal;

	public DNListaChequeo() throws Exception {
		sBListaCheqeoComputadorLocal = ServiceLocator.getInstance()
				.obtenerServicio(Parametros.PREFIJO_JNDI + "SBListaChequeoComputador"
						+ Parametros.PREFIJO_ADICIONAL_JNDI + "SBListaChequeoComputadorLocal",
						SBListaChequeoComputadorLocal.class);
	}

	public ListaCheqeoComputador crearListaCheqeoComputador(ListaCheqeoComputador nuevo) throws Exception {
		return sBListaCheqeoComputadorLocal.crearListaCheqeoComputador(nuevo);
	}

	public ListaCheqeoComputador actualizarListaCheqeoComputador(ListaCheqeoComputador update) throws Exception {
		return sBListaCheqeoComputadorLocal.actualizarListaCheqeoComputador(update);
	}

	public ListaCheqeoComputador consultarDetalleListaCheqeoComputador(String id) throws Exception {
		return sBListaCheqeoComputadorLocal.consultarDetalleListaCheqeoComputador(id);
	}

	public List<ListaCheqeoComputador> consultarAllListaCheqeoComputadorActivos() throws Exception {
		return sBListaCheqeoComputadorLocal.consultarAllListaCheqeoComputadorActivos();
	}

	public List<ListaCheqeoComputador> consultarAllListaCheqeoComputadorActivosNuevos() throws Exception {
		return sBListaCheqeoComputadorLocal.consultarAllListaCheqeoComputadorActivosNuevos();
	}

	public List<ListaCheqeoComputador> consultarAllListaChequeoUESOrdenASCNuevo() throws Exception {
		return sBListaCheqeoComputadorLocal.consultarAllListaChequeoUESOrdenASCNuevo();
	}

}
