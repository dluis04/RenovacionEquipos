package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Cronograma;

@Local
public interface SBCronogramaLocal {

	public Cronograma crearCronograma(Cronograma nuevo) throws Exception;

	public Cronograma actualizarCronograma(Cronograma update) throws Exception;

	public Cronograma consultarDetalleCronograma(String id) throws Exception;

	public List<Cronograma> consultarAllCronogramaActivos() throws Exception;

}
