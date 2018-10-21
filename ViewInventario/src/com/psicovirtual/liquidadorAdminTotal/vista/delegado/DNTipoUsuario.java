package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.TipoUsuario;
import com.psicovirtual.procesos.modelo.ejb.session.SBTipoUsuarioLocal;

@ManagedBean(value = "DNTipoUsuario")
@ApplicationScoped
public class DNTipoUsuario {

	SBTipoUsuarioLocal sBTipoUsuarioLocal;

	public DNTipoUsuario() throws Exception {
		sBTipoUsuarioLocal = ServiceLocator.getInstance().obtenerServicio(
				Parametros.PREFIJO_JNDI + "SBTipoUsuario" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBTipoUsuarioLocal",
				SBTipoUsuarioLocal.class);
	}

	public TipoUsuario crearTipoUsuario(TipoUsuario nuevo) throws Exception {
		return sBTipoUsuarioLocal.crearTipoUsuario(nuevo);
	}

	public TipoUsuario actualizarTipoUsuario(TipoUsuario update) throws Exception {
		return sBTipoUsuarioLocal.actualizarTipoUsuario(update);
	}

	public TipoUsuario consultarDetalleTipoUsuario(String id) throws Exception {
		return sBTipoUsuarioLocal.consultarDetalleTipoUsuario(id);
	}

	public List<TipoUsuario> consultarAllTiposUsuariosActivos() throws Exception {
		return sBTipoUsuarioLocal.consultarAllTiposUsuariosActivos();
	}

}
