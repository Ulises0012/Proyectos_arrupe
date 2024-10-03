package sv.org.arrupe.www.bodega_cepa.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sv.org.arrupe.www.bodega_cepa.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("productos")
public class ProductoResource {
    private static final List<Producto> productos = new ArrayList<>();
    private static final AtomicLong idCounter = new AtomicLong(3); // Último ID utilizado

    static {
        productos.add(new Producto(1L, "Producto 1", "Descripción del Producto 1", 10.0, 100));
        productos.add(new Producto(2L, "Producto 2", "Descripción del Producto 2", 20.0, 200));
        productos.add(new Producto(3L, "Producto 3", "Descripción del Producto 3", 30.0, 300));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductos() {
        return Response.ok(productos).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducto(@PathParam("id") Long id) {
        Optional<Producto> producto = productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (producto.isPresent()) {
            return Response.ok(producto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarProducto(Producto producto) {
        // Generar un nuevo ID
        long newId = idCounter.incrementAndGet();
        producto.setId(newId);
        productos.add(producto);
        return Response.status(Response.Status.CREATED).build(); // Created, producto agregado
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarProducto(@PathParam("id") Long id, Producto producto) {
        Optional<Producto> existingProduct = productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (existingProduct.isPresent()) {
            // Actualiza el producto existente
            productos.removeIf(p -> p.getId().equals(id));
            producto.setId(id); // Asegúrate de mantener el mismo ID
            productos.add(producto);
            return Response.ok().build(); // OK, producto actualizado
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); // Not Found, producto no existe
        }
    }

    @DELETE
    @Path("{id}")
    public Response borrarProducto(@PathParam("id") Long id) {
        boolean removed = productos.removeIf(p -> p.getId().equals(id));
        if (removed) {
            return Response.noContent().build(); // No Content, producto eliminado
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); // Not Found, producto no existe
        }
    }
}
