package ec.edu.espe.apiproductos.exception;

public class ProductoNoEncontradoException extends RuntimeException {

    public ProductoNoEncontradoException(Long id) {
        super("No existe un producto con el ID: " + id);
    }
}