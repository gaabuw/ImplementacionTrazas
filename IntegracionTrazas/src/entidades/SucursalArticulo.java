package entidades;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SucursalArticulo {
    private Long id;
    private Sucursal sucursal;
    private Articulo articulo;
    private Integer stockActual;
    private Integer stockMaximo;
}
