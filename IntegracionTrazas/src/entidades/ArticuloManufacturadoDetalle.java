package entidades;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ArticuloManufacturadoDetalle {
    private Integer cantidad;
    private Long id;

    private ArticuloInsumo articuloInsumo;
}
