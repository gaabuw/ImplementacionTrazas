package entidades;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class Categoria {
    private String denominacion;
    private Long id;
    private boolean esInsumo; //Agregamos esta variable

    @Builder.Default
    @ToString.Exclude
    private Set<Articulo> articulos = new HashSet<>();

    //UNION CON EMPRESA
    @ToString.Exclude
    private Empresa empresa;

}
