package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.UsuariosCronograma;
import com.psicovirtual.procesos.modelo.ejb.session.SBUsuariosCronogramaLocal;

@ManagedBean(value = "DNUsuarioCronograma")
@ApplicationScoped
public class DNUsuarioCronograma {

	SBUsuariosCronogramaLocal sBUsuariosCronogramaLocal;

	public DNUsuarioCronograma() throws Exception {
		sBUsuariosCronogramaLocal = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI
				+ "SBUsuariosCronograma" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBUsuariosCronogramaLocal",
				SBUsuariosCronogramaLocal.class);
	}

	public UsuariosCronograma crearUsuariosCronograma(UsuariosCronograma nuevo) throws Exception {
		return sBUsuariosCronogramaLocal.crearUsuariosCronograma(nuevo);
	}

	public UsuariosCronograma actualizarUsuariosCronograma(UsuariosCronograma update) throws Exception {
		return sBUsuariosCronogramaLocal.actualizarUsuariosCronograma(update);
	}

	public UsuariosCronograma consultarDetalleUsuariosCronograma(String id) throws Exception {
		return sBUsuariosCronogramaLocal.consultarDetalleUsuariosCronograma(id);
	}

	public List<UsuariosCronograma> consultarAllUsuariosCronogramaActivos() throws Exception {
		return sBUsuariosCronogramaLocal.consultarAllUsuariosCronogramaActivos();
	}

}
