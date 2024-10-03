package sv.org.arrupw.www.managedBean;

import sv.org.arrupe.www.bodega_cepa.Producto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class ProductoBean implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(ProductoBean.class.getName());
    
    private List<Producto> productos;
    private Producto productoSeleccionado;
    private final String BASE_URL = "http://localhost:9090/Bodega_Cepa/api/productos";

    public ProductoBean() {
        productos = new ArrayList<>();
        productoSeleccionado = new Producto();
    }

    @PostConstruct
    public void init() {
        LOGGER.info("Inicializando ProductoBean");
        cargarProductos();
    }

    public void cargarProductos() {
        LOGGER.info("Intentando cargar productos");
        try (Client client = ClientBuilder.newClient()) {
            productos = client.target(BASE_URL)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Producto>>(){});
            LOGGER.info("Productos cargados exitosamente. Cantidad: " + productos.size());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al cargar productos", e);
            productos = new ArrayList<>();
        }
    }

    public List<Producto> getProductos() {
        if (productos.isEmpty()) {
            cargarProductos();
        }
        return productos;
    }

    public String agregarProducto() {
        LOGGER.info("Intentando agregar producto: " + productoSeleccionado.getNombre());
        try (Client client = ClientBuilder.newClient()) {
            Response response = client.target(BASE_URL)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(productoSeleccionado, MediaType.APPLICATION_JSON));
            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                LOGGER.info("Producto agregado exitosamente");
                cargarProductos();
                productoSeleccionado = new Producto(); // Reset the selected product
            } else {
                LOGGER.warning("Error al agregar producto. Código de respuesta: " + response.getStatus());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al agregar producto", e);
        }
        return "index?faces-redirect=true";
    }

    public String editarProducto() {
        LOGGER.info("Intentando editar producto: " + productoSeleccionado.getId());
        try (Client client = ClientBuilder.newClient()) {
            Response response = client.target(BASE_URL)
                    .path("/" + productoSeleccionado.getId())
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.entity(productoSeleccionado, MediaType.APPLICATION_JSON));
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                LOGGER.info("Producto editado exitosamente");
                cargarProductos();
                productoSeleccionado = new Producto(); // Reset the selected product
            } else {
                LOGGER.warning("Error al editar producto. Código de respuesta: " + response.getStatus());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al editar producto", e);
        }
        return "index?faces-redirect=true";
    }

    public String borrarProducto(Producto producto) {
        LOGGER.info("Intentando borrar producto: " + producto.getId());
        try (Client client = ClientBuilder.newClient()) {
            Response response = client.target(BASE_URL)
                    .path("/" + producto.getId())
                    .request()
                    .delete();
            if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {
                LOGGER.info("Producto borrado exitosamente");
                cargarProductos();
            } else {
                LOGGER.warning("Error al borrar producto. Código de respuesta: " + response.getStatus());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al borrar producto", e);
        }
        return "index?faces-redirect=true";
    }

    public String verDetalles(Producto producto) {
        this.productoSeleccionado = producto;
        return "detalleProducto?faces-redirect=true";
    }

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public String irAgregar() {
        this.productoSeleccionado = new Producto(); // Inicializa un nuevo producto
        return "agregarProducto?faces-redirect=true";
    }

    public String irEditar(Producto producto) {
        this.productoSeleccionado = producto;
        return "editarProducto?faces-redirect=true";
    }
}
