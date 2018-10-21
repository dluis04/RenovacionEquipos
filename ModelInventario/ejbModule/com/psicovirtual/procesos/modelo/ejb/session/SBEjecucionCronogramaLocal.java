package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.EjecucionCronograma;

@Local
public interface SBEjecucionCronogramaLocal {

	public EjecucionCronograma crearEjecucionCronograma(EjecucionCronograma nuevo) throws Exception;

	public EjecucionCronograma actualizarEjecucionCronograma(EjecucionCronograma update) throws Exception;

	public EjecucionCronograma consultarDetalleEjecucionCronograma(String id) throws Exception;

	public List<EjecucionCronograma> consultarAllEjecucionCronogramaActivos() throws Exception;

}
