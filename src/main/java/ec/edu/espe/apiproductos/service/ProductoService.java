package ec.edu.espe.apiproductos.service;

import ec.edu.espe.apiproductos.exception.ProductoInvalidoException;
import ec.edu.espe.apiproductos.exception.ProductoNoEncontradoException;
import ec.edu.espe.apiproductos.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    private final List<Producto> productos = new ArrayList<>();
    private Long siguienteId = 1L;

    public ProductoService() {
        productos.add(new Producto(siguienteId++, "Cuaderno", 2.50, 20));
        productos.add(new Producto(siguienteId++, "Esfero azul", 0.75, 50));
    }

    public List<Producto> listarProductos() {
        return productos;
    }

    public Producto buscarPorId(Long id) {
        return productos.stream()
                .filter(producto -> producto.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductoNoEncontradoException(id));
    }

    public Producto registrarProducto(Producto producto) {
        if (producto.getPrecio() <= 0) {
            throw new ProductoInvalidoException(
                    "El precio del producto debe ser mayor que cero");
        }

        if (producto.getStock() < 0) {
            throw new ProductoInvalidoException(
                    "El stock del producto no puede ser negativo");
        }

        producto.setId(siguienteId++);
        productos.add(producto);

        return producto;
    }

    public void eliminarProducto(Long id) {
        Producto producto = buscarPorId(id);
        productos.remove(producto);
    }
}