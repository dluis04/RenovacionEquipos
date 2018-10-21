package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.SistemaOperativo;
import com.psicovirtual.procesos.modelo.ejb.session.SBSistemaOperativoLocal;

@ManagedBean(value = "DNSistemaOperativo")
@ApplicationScoped
public class DNSistemaOperativo {

	SBSistemaOperativoLocal sBSistemaOperativoLocal;

	public DNSistemaOperativo() throws Exception {
		sBSistemaOperativoLocal = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI
				+ "SBSistemaOperativo" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBSistemaOperativoLocal",
				SBSistemaOperativoLocal.class);
	}

	public SistemaOperativo crearSistemaOperativo(SistemaOperativo nuevo) throws Exception {
		return sBSistemaOperativoLocal.crearSistemaOperativo(nuevo);
	}

	public SistemaOperativo actualizarSistemaOperativo(SistemaOperativo update) throws Exception {
		return sBSistemaOperativoLocal.actualizarSistemaOperativo(update);
	}

	public SistemaOperativo consultarDetalleSistemaOperativo(String id) throws Exception {
		return sBSistemaOperativoLocal.consultarDetalleSistemaOperativo(id);
	}

	public List<SistemaOperativo> consultarAllSistemaOperativoActivos() throws Exception {
		return sBSistemaOperativoLocal.consultarAllSistemaOperativoActivos();

	}
}