CREATE TABLE IF NOT EXISTS Rol (
       id int not null primary key,
       nombre char(2) not null
);
CREATE TABLE IF NOT EXISTS Usuario (
       id int not null primary key,
       nombre varchar(128) not null,
       contraseña blob not null,
       rolId int not null references Rol(id),
       sesionActiva boolean default false
);
CREATE TABLE IF NOT EXISTS LimiteDeInventario (
       idUsuario int not null,
       limite int not null,
       primary key (idUsuario),
       foreign key (idUsuario)
       references Usuario(id)
       on delete cascade
);
CREATE TABLE IF NOT EXISTS Suplidor (
       id int not null primary key,
       nombre varchar(255) not null,
       direccion varchar(64) not null,
       telefono varchar(15) not null
);
CREATE TABLE IF NOT EXISTS ProductoRegistro (
       id int not null primary key,
       nombre varchar(255) not null,
       precioPorUnidad float(2) not null,
       idSuplidor int,
       foreign key (idSuplidor)
       references Suplidor(id)
);
CREATE TABLE IF NOT EXISTS InventarioProducto (
       id int not null primary key,
       cantidad int default 0,
       idProductoRegistro int not null,
       foreign key (idProductoRegistro)
       references ProductoRegistro(id)
);
CREATE TABLE IF NOT EXISTS NotificacionDeCompra (
       idInventarioProducto int not null,
       idUsuario int not null,
       fechaDeCreacion Timestamp,
       primary key (idInventarioProducto, idUsuario),
       foreign key (idInventarioProducto)
       references InventarioProducto(id)
       on delete cascade,
       foreign key (idUsuario)
       references Usuario(id)
       on delete cascade
);
CREATE TABLE IF NOT EXISTS PuntoDeVentaStock (
       id int not null primary key,
       usuarioId int not null references Usuario(id),
       fechaDeInicio timestamp not null
);
CREATE TABLE IF NOT EXISTS PuntoDeVentaProducto (
       id int not null primary key,
       cantidad int default 0,
       idProductoRegistro int not null,
       idPuntoDeVentaStock int not null,
       foreign key (idProductoRegistro)
       references ProductoRegistro(id),
       foreign key (idPuntoDeVentaStock)
       references PuntoDeVentaStock(id)
);
CREATE TABLE IF NOT EXISTS Solicitud (
       id int not null primary key,
       fechaDeCreacion timestamp,
       aceptado boolean default false
);
CREATE TABLE IF NOT EXISTS SolicitudProducto (
       id int not null primary key,
       cantidad int default 0,
       precioCompraPorUnidad float(2),
       idProductoRegistro int not null,
       idSolicitud int not null,
       foreign key (idProductoRegistro)
       references ProductoRegistro(id),
       foreign key (idSolicitud)
       references Solicitud(id)
);
CREATE TABLE IF NOT EXISTS Transaccion (
       id int not null primary key,
       fechaDeCreacion timestamp
);
CREATE TABLE IF NOT EXISTS TransaccionProducto (
       id int not null primary key,
       cantidad int default 0,
       impuestosAñadidos float(2),
       precioPorUnidad float(2),
       idProductoRegistro int not null,
       idTransaccion int not null,
       foreign key (idProductoRegistro)
       references ProductoRegistro(id),
       foreign key (idTransaccion)
       references Transaccion(id)
);
