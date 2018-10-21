package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.SistemaOperativo;

@Local
public interface SBSistemaOperativoLocal {

	public SistemaOperativo crearSistemaOperativo(SistemaOperativo nuevo) throws Exception;

	public SistemaOperativo actualizarSistemaOperativo(SistemaOperativo update) throws Exception;

	public SistemaOperativo consultarDetalleSistemaOperativo(String id) throws Exception;

	public List<SistemaOperativo> consultarAllSistemaOperativoActivos() throws Exception;

}
