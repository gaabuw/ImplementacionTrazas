package entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public abstract class Articulo {
    private String denominacion;
    private Double precioVenta;
    private Long id;

    private Categoria categoria;
    private UnidadMedida unidadMedida;

    @Builder.Default
    private Set<Imagen> imagenes = new HashSet<>();
}
