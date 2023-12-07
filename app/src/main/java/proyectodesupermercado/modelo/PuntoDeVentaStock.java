package proyectodesupermercado.modelo;

import proyectodesupermercado.lib.databaseUtils.annotations.Column;
import proyectodesupermercado.lib.databaseUtils.annotations.Table;
import proyectodesupermercado.lib.tableModel.TableModelColumn;

import java.sql.Timestamp;

@Table
public class PuntoDeVentaStock {
    @TableModelColumn(name = "ID", index = 1)
    private long id;
    private long usuarioId;
    @TableModelColumn(name = "Fecha de Creacion", index = 2)
    @Column(name = "fechaDeInicio")
    private Timestamp fechaDeCreacion;

    public PuntoDeVentaStock() {
    }

    public PuntoDeVentaStock(long usuarioId, Timestamp fechaDeCreacion) {
        this.usuarioId = usuarioId;
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public Timestamp getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
